package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpzfBO;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票管理模块的发票作废功能Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FpzfProcessor implements Processor{

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpzfProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       
       	   case ActionType.INSERT:
       		   result = doSave(vo);
       		   break;
       		   
       	   default:
           throw new ApplicationException(
                   "ActionType有错误，processor中找不到相应的方法.");
       }

       return result;
   }

   /**
	 * 保存
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException  
	{
		// 从VOPackage中获得数据
		FpzfBO bo = (FpzfBO)vo.getData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//得到当前时间
			Timestamp now = CommonUtil.getDBtime(conn);
			
			//fpdkForm 中的 发票相关信息
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //税务机关组织机构代码
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //税务机关组织机构代码
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String fpzldm = bo.getFpzldm();  //发票种类代码
			String qshm = bo.getQshm();  //起始号码
			String kpr = bo.getKpr();  //开票人
			String yhr = bo.getYhid() + bo.getYhmc(); //使用人员
			
			
			Fpkc fpkc =null;
			
			//获得存量房信息
			if ( qshm != null && !qshm.equals(""))
			{
				//判断是否有库存
				ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,fpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(kcList != null && kcList.size() != 0)
				{
					//（1）插入发票开票主表
					Fpczz fpczz = new Fpczz();
					
					fpczz.setFpzldm(fpzldm);
					fpczz.setFphm(qshm);
					fpczz.setFpkfdm(fpkfdm);
					fpczz.setKprq(now);
					fpczz.setDkdwmc(swjgzzjgmc);
					fpczz.setKplxdm(Constants.FP_KPLX_FP);
					fpczz.setKpr(yhr);
					fpczz.setCjr(yhr);
					fpczz.setCjrq(now);
					fpczz.setLrr(yhr);
					fpczz.setLrrq(now);
					fpczz.setSwjgzzjgdm(swjgzzjgdm);
					fpczz.setBz("废票");
					fpczz.setDcbz(Constants.FP_DCBZ_WTP);
					
					try
					{
						DAOFactory.getInstance().getFpczzDAO().insert(fpczz, conn);
						Debug.out("发票代开管理：已经将fpkpzb的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入发票开票主表出错！");
					}
					
					//（2）变更库存表
					for (int i = 0; i < kcList.size(); i++)
					{
						fpkc = new Fpkc();
						Fpkc fpkcItem = (Fpkc) kcList.get(i);
						fpkc.setQshm(fpkcItem.getQshm());
						fpkc.setJzhm(fpkcItem.getJzhm());
						
						String kcqshm = fpkc.getQshm();
						String kcjzhm = fpkc.getJzhm();
						
						//未使用List（库存起始号码>起始号码 或者 库存截止号码<起始号码）
						//if((Long.parseLong(kcqshm) - Long.parseLong(qshm) > 0) || (Long.parseLong(kcjzhm) - Long.parseLong(qshm) < 0))
						if(compareHm(kcqshm,qshm) || compareHm(qshm,kcjzhm))	
						{
							Fpkc fpkcWY =new Fpkc();
							
							fpkcWY.setFpkfdm(fpkcItem.getFpkfdm());
							fpkcWY.setFpzldm(fpkcItem.getFpzldm());
							fpkcWY.setJcsj(now);
							fpkcWY.setQshm(fpkcItem.getQshm());
							fpkcWY.setJzhm(fpkcItem.getJzhm());
							fpkcWY.setSl(fpkcItem.getSl());
							fpkcWY.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
							fpkcWY.setCjr(yhr);
							fpkcWY.setCjrq(now);
							fpkcWY.setLrr(yhr);
							fpkcWY.setLrrq(now);
							fpkcWY.setBz("");
							fpkcWY.setRkbs(Constants.FP_RKBZ_FRK);
							
							fpkcNewList.add(fpkcWY);
						}
						else
                       {
							// 库存起始号码 < 起始号码
                           if (compareHm(qshm, kcqshm))
                           {
                           	Fpkc fpkcYY =new Fpkc();
                           	
                           	fpkcYY.setFpkfdm(fpkcItem.getFpkfdm());
                           	fpkcYY.setFpzldm(fpkcItem.getFpzldm());
                           	fpkcYY.setJcsj(now);
                           	fpkcYY.setQshm(fpkcItem.getQshm());
                           	fpkcYY.setJzhm(this.calculateHm(qshm, -1));
                           	fpkcYY.setSl(new BigDecimal(this.calculateSl(qshm, fpkcItem.getQshm())));
                           	fpkcYY.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
                           	fpkcYY.setCjr(yhr);
                           	fpkcYY.setCjrq(now);
                           	fpkcYY.setLrr(yhr);
                           	fpkcYY.setLrrq(now);
                           	fpkcYY.setBz("");
   							fpkcYY.setRkbs(Constants.FP_RKBZ_FRK);
   							
   							fpkcNewList.add(fpkcYY);
                           }
                           
                           // 库存截止号码 > 起始号码
                           if (compareHm(kcjzhm, qshm))
                           {
                           	Fpkc fpkcYYJ =new Fpkc();
                           	
                           	fpkcYYJ.setFpkfdm(fpkcItem.getFpkfdm());
                           	fpkcYYJ.setFpzldm(fpkcItem.getFpzldm());
                           	fpkcYYJ.setJcsj(now);
                           	fpkcYYJ.setQshm(this.calculateHm(qshm,1));
   							fpkcYYJ.setJzhm(fpkcItem.getJzhm());
   							fpkcYYJ.setSl(new BigDecimal(this.calculateSl(fpkcItem.getJzhm(), qshm)));
   							fpkcYYJ.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
   							fpkcYYJ.setCjr(yhr);
   							fpkcYYJ.setCjrq(now);
   							fpkcYYJ.setLrr(yhr);
   							fpkcYYJ.setLrrq(now);
   							fpkcYYJ.setBz("");
   							fpkcYYJ.setRkbs(Constants.FP_RKBZ_FRK);
   				        	
   							fpkcNewList.add(fpkcYYJ);
                           }
                           
                           // 起始号码 == 库存起始、截止号码
                           if ( equalHm(qshm, kcqshm) && equalHm(qshm, kcjzhm) )
                           {
                           	Fpkc fpkcYW =new Fpkc();
                           	
                           	fpkcYW.setFpkfdm(fpkcItem.getFpkfdm());
                           	fpkcYW.setFpzldm(fpkcItem.getFpzldm());
   							fpkcYW.setJcsj(now);
   							fpkcYW.setQshm("00000000");
   							fpkcYW.setJzhm("00000000");
   							fpkcYW.setSl(new BigDecimal("0"));
   							fpkcYW.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
   							fpkcYW.setCjr(yhr);
   							fpkcYW.setCjrq(now);
   							fpkcYW.setLrr(yhr);
   							fpkcYW.setLrrq(now);
   							fpkcYW.setBz("");
   							fpkcYW.setRkbs(Constants.FP_RKBZ_FRK);
   				        	
   							fpkcNewList.add(fpkcYW);
                           }
                       }
							
					}
					
					//通过插入库存表List 与现有库存中List 的集合 将数据写入到库存表。
					for(int index =0; index < fpkcNewList.size() ; index ++)
					{
						Fpkc fpkcNew = new Fpkc();
						Fpkc fpkcNewItem = (Fpkc) fpkcNewList.get(index);
						
						fpkcNew.setFpkfdm(fpkcNewItem.getFpkfdm());
						fpkcNew.setFpzldm(fpkcNewItem.getFpzldm());
						fpkcNew.setJcsj(fpkcNewItem.getJcsj());
						fpkcNew.setQshm(fpkcNewItem.getQshm());
						fpkcNew.setJzhm(fpkcNewItem.getJzhm());
						fpkcNew.setSl(fpkcNewItem.getSl());
						fpkcNew.setSwjgzzjgdm(fpkcNewItem.getSwjgzzjgdm());
						fpkcNew.setCjr(fpkcNewItem.getCjr());
						fpkcNew.setCjrq(fpkcNewItem.getCjrq());
						fpkcNew.setLrr(fpkcNewItem.getLrr());
						fpkcNew.setLrrq(fpkcNewItem.getLrrq());
						fpkcNew.setBz(fpkcNewItem.getBz());
						fpkcNew.setRkbs(fpkcNewItem.getRkbs());
						
						try 
						{
							DAOFactory.getInstance().getFpkcDAO().insert(fpkcNew, conn);
							Debug.out("发票代开管理：已经将fpkc的数据插入到数据库中....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("插入发票库存表出错！");
						}
					}
				}
				else{
					throw new ApplicationException("发票代开信息保存失败！");
				}
			}else{
				throw new ApplicationException("发票代开信息保存失败！");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally {
           QSDBUtil.freeConnection(conn);
       }	
		return bo;
	}
	
	
   /**
    * 计算库起始库存号码
    *
    * @param hm 票证号码
    * @param sl  数量
    * @return  String 起始库存号码
    */
   private String calculateHm(String hm, long sl)
   {
       int size = hm.length();
       long qs = Long.parseLong(hm);
       qs = qs + sl;

       String newQshm = (String.valueOf(qs));
       int newsize = (String.valueOf(qs)).length();
       int zeroCount = size - newsize;

       for (int i = 0; i < zeroCount; i++)
       {
           newQshm = "0" + newQshm;
       }

       return newQshm;
   }

   /**
    * 计算两个号码的差
    *
    * @param hm1 票证号码1
    * @param hm2 票证号码2
    * @return long 两个号码的差
    */
   private long calculateSl(String hm1, String hm2)
   {
       return (Long.parseLong(hm1) - Long.parseLong(hm2));
   }

   /**
    * 比较号码大小
    *
    * @param hm1 票证号码1
    * @param hm2 票证号码2
    * @return boolean  比较号码大小的结果
    */
   private boolean compareHm(String hm1, String hm2)
   {
       return (Long.parseLong(hm1) > Long.parseLong(hm2));
   }
   
   /**
    * 比较号码大小（相等）
    *
    * @param hm1 票证号码1
    * @param hm2 票证号码2
    * @return boolean  比较号码大小的结果
    */
   private boolean equalHm(String hm1, String hm2)
   {
       return (Long.parseLong(hm1) == Long.parseLong(hm2));
   }
}
