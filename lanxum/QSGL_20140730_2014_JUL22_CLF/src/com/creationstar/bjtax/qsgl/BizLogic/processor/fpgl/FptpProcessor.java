package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.HtypzdzgxbLs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票管理模块的发票退票功能Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FptpProcessor implements Processor {

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FptpProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       		
       	   case ActionType.QUERY:
       		   result = doQuery(vo);
       		   break;
   		   
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
	 * 查询
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException 
	{
		// 从VOPackage中获得数据
		FptpBO bo = (FptpBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //合同编号
		String fpkfdm = bo.getFpkfdm();  //发票库房代码
		String fpzldm = bo.getFpzldm();  //发票种类代码
		String qshm = SecurityUtil.dealwithStringPara(bo.getQshm());  //发票种类代码
		
		Connection conn = null;
		try 
		{	
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			//1.判断合同编号是否存在
			if((htbh != null) && !htbh.equals(""))
			{
				String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
				String fwxxconditions = " where htbh = '" + htbh + "' and"+fwxxfilter;
				
				ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, fwxxconditions);
				
				if(fwxxList == null || fwxxList.size() == 0)
				{
					bo.setMessagefwx("无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
					return bo;
				}
			}
			
			//2.查询发票信息
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud, "FPDB", "FP_JL_FPKPZB" );
            Debug.out("datafilter: " + datafilter);
			
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//合同编号
            if ((htbh != null) && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "'";
            }

            //发票号码
            if ((qshm != null) && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" + qshm + "' and b.kplxdm ='"+Constants.FP_KPLX_KP+"' ";
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFptp(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
				}
				
				Debug.out("发票退票管理：查询发票退票信息成功....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("发票退票管理：查询发票退票信息出错！");
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

	private Object doSave(VOPackage vo) throws BaseException
	{
		// 从VOPackage中获得数据
		FptpBO bo = (FptpBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//得到当前时间
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //税务机关组织机构名称
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //税务机关组织机构名称
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String cxfpzldm = bo.getCxfpzldm(); //开票发票种类代码
			String cxqshm = bo.getCxqshm();  //开票发票号码
			String tpfpzldm = bo.getTpfpzldm(); //退票发票种类代码
			String tpqshm = bo.getTpqshm();  //退票发票号码
			String kpr =bo.getKpr();  //开票人
			String yhr = bo.getYhid() + bo.getYhmc(); //使用人员

			Fpczz fpczzb = null;
			Fpczmx fpczmxb = null;
			Fpkc fpkcb =null;
			
			if((cxqshm != null && !cxqshm.equals("")) && (tpqshm != null && !tpqshm.equals("")))
			{
				
				//判断是否有库存
				ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,tpfpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(kcList != null && kcList.size() != 0)
				{

					//(1)根据发票号码入对照关系历史表
					HtypzdzgxbLs htypzdzgxbLs = new HtypzdzgxbLs();
					
					htypzdzgxbLs.setYbxh(bo.getHtypzdzgxb().getXh());
					htypzdzgxbLs.setHtbh(bo.getHtypzdzgxb().getHtbh());
					htypzdzgxbLs.setSbbh(bo.getHtypzdzgxb().getSbbh());
					htypzdzgxbLs.setMmfbz(bo.getHtypzdzgxb().getMmfbz());
					htypzdzgxbLs.setPzfldm(bo.getHtypzdzgxb().getPzfldm());
					htypzdzgxbLs.setPizzldm(bo.getHtypzdzgxb().getPizzldm());
					htypzdzgxbLs.setPzhm(bo.getHtypzdzgxb().getPzhm());
					htypzdzgxbLs.setPzndzb(bo.getHtypzdzgxb().getPzndzb());
					htypzdzgxbLs.setCjr(bo.getHtypzdzgxb().getCjr());
					htypzdzgxbLs.setCjrq(bo.getHtypzdzgxb().getCjrq());
					htypzdzgxbLs.setLrr(bo.getHtypzdzgxb().getLrr());
					htypzdzgxbLs.setLrrq(bo.getHtypzdzgxb().getLrrq());
					htypzdzgxbLs.setCzr(yhr);
					htypzdzgxbLs.setCzrq(now);
					htypzdzgxbLs.setSwjgzzjgdm(swjgzzjgdm);
					htypzdzgxbLs.setBz("|退票(2)");
					
					try 
					{
						DAOFactory.getInstance().getHtypzdzgxbLsDAO().insert(htypzdzgxbLs, conn);
						Debug.out("发票退票管理：已经将到对照关系表数据插入到对照关系历史表中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入对照关系历史表出错！");
					}
					
					//（2）插入发票开票主表
					fpczzb = new Fpczz();
					
					fpczzb.setFpzldm(tpfpzldm);
					fpczzb.setFphm(tpqshm);
					fpczzb.setFpkfdm(fpkfdm);
					fpczzb.setKprq(now);
					fpczzb.setFkdw(bo.getFpczz().getFkdw());
					fpczzb.setSkdw(bo.getFpczz().getSkdw());
					fpczzb.setDkdwmc(swjgzzjgmc);
					fpczzb.setKplxdm(Constants.FP_KPLX_TP);
					fpczzb.setSphm(bo.getFpczz().getSphm());
					fpczzb.setKpr(kpr);
					fpczzb.setSfyjbltp(Constants.FP_TPBZ_WTP);
					fpczzb.setCjr(yhr);
					fpczzb.setCjrq(now);
					fpczzb.setLrr(yhr);
					fpczzb.setLrrq(now);
					fpczzb.setSwjgzzjgdm(swjgzzjgdm);
					fpczzb.setBz(bo.getFpczz().getBz());
					fpczzb.setDcbz(Constants.FP_DCBZ_WTP);
					fpczzb.setHyfl(bo.getFpczz().getHyfl());
					fpczzb.setFwcqzh(bo.getFpczz().getFwcqzh());
					fpczzb.setFwzldz(bo.getFpczz().getFwzldz());
					
					try
					{
						DAOFactory.getInstance().getFpczzDAO().insert(fpczzb, conn);
						Debug.out("发票退票管理：已经将fpkpzb的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入发票开票主表出错！");
					}
					
					//（3）插入发票开票明细
					fpczmxb = new Fpczmx();
					
					fpczmxb.setFpkfdm(fpczzb.getFpkfdm());
					fpczmxb.setFpzldm(fpczzb.getFpzldm());
					fpczmxb.setFphm(fpczzb.getFphm());
					fpczmxb.setKprq(fpczzb.getKprq());
					fpczmxb.setPm(bo.getFpczmx().getPm());
					fpczmxb.setDj(new BigDecimal("-"+bo.getFpczmx().getDj()));
					fpczmxb.setSl(bo.getFpczmx().getSl());
					fpczmxb.setJe(new BigDecimal("-"+bo.getFpczmx().getJe()));
					fpczmxb.setCjr(fpczzb.getCjr());
					fpczmxb.setCjrq(fpczzb.getCjrq());
					fpczmxb.setLrr(fpczzb.getLrr());
					fpczmxb.setLrrq(fpczzb.getLrrq());
					fpczmxb.setSwjgzzjgdm(fpczzb.getSwjgzzjgdm());
					
					try
					{
						DAOFactory.getInstance().getFpczmxDAO().insert(fpczmxb, conn);
						Debug.out("发票退票管理：已经将fpkpmx的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入发票开票明细表出错！");
					}
					
					//（4）变更库存表
					for (int i = 0; i < kcList.size(); i++)
					{
						fpkcb = new Fpkc();
						Fpkc fpkcItem = (Fpkc) kcList.get(i);
						fpkcb.setQshm(fpkcItem.getQshm());
						fpkcb.setJzhm(fpkcItem.getJzhm());
						fpkcb.setJcsj(fpkcItem.getJcsj());
						
						String kcqshm = fpkcb.getQshm();
						String kcjzhm = fpkcb.getJzhm();
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
						
						int timeInt = df.format(fpkcb.getJcsj()).compareTo(df.format(now));
						
						//判断时间（若当前系统时间 <= 最新结存时间，插入时间为：最新结存时间+1秒）
						if(!(timeInt <0))
						{
							now = DateUtils.getTimestamp(addSecond(fpkcb.getJcsj(),1));
						}
						
						//未使用List（库存起始号码>起始号码 或者 库存截止号码<起始号码）
						//if((Long.parseLong(kcqshm) - Long.parseLong(cxqshm) > 0) || (Long.parseLong(kcjzhm) - Long.parseLong(cxqshm) < 0))
						if(compareHm(kcqshm,tpqshm) || compareHm(tpqshm,kcjzhm))	
						{
							Fpkc fpkcWY =new Fpkc();
							
							fpkcWY.setFpkfdm(fpkcItem.getFpkfdm());
							fpkcWY.setFpzldm(fpkcItem.getFpzldm());
							fpkcWY.setJcsj(now);
							fpkcWY.setQshm(fpkcItem.getQshm());
							fpkcWY.setJzhm(fpkcItem.getJzhm());
							fpkcWY.setSl(fpkcItem.getSl());
							
							fpkcNewList.add(fpkcWY);
						}
						else
                        {
							// 库存起始号码 < 起始号码
                            if (compareHm(tpqshm, kcqshm))
                            {
                            	Fpkc fpkcYY =new Fpkc();
                            	
                            	fpkcYY.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYY.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYY.setJcsj(now);
                            	fpkcYY.setQshm(fpkcItem.getQshm());
                            	fpkcYY.setJzhm(this.calculateHm(tpqshm, -1));
                            	fpkcYY.setSl(new BigDecimal(this.calculateSl(tpqshm, fpkcItem.getQshm())));
    							
    							fpkcNewList.add(fpkcYY);
                            }
                            
                            // 库存截止号码 > 起始号码
                            if (compareHm(kcjzhm, tpqshm))
                            {
                            	Fpkc fpkcYYJ =new Fpkc();
                            	
                            	fpkcYYJ.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYYJ.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYYJ.setJcsj(now);
                            	fpkcYYJ.setQshm(this.calculateHm(tpqshm,1));
    							fpkcYYJ.setJzhm(fpkcItem.getJzhm());
    							fpkcYYJ.setSl(new BigDecimal(this.calculateSl(fpkcItem.getJzhm(), tpqshm)));
    				        	
    							fpkcNewList.add(fpkcYYJ);
                            }
                            
                            // 起始号码 == 库存起始、截止号码
                            if ( equalHm(tpqshm, kcqshm) && equalHm(tpqshm, kcjzhm) )
                            {
                            	Fpkc fpkcYW =new Fpkc();
                            	
                            	fpkcYW.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYW.setFpzldm(fpkcItem.getFpzldm());
    							fpkcYW.setJcsj(now);
    							fpkcYW.setQshm("00000000");
    							fpkcYW.setJzhm("00000000");
    							fpkcYW.setSl(new BigDecimal("0"));
    				        	
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
						fpkcNew.setSwjgzzjgdm(swjgzzjgdm);
						fpkcNew.setCjr(yhr);
						fpkcNew.setCjrq(now);
						fpkcNew.setLrr(yhr);
						fpkcNew.setLrrq(now);
						fpkcNew.setBz("");
						fpkcNew.setRkbs(Constants.FP_RKBZ_FRK);
						
						//System.out.println("Fpkfdm:"+fpkcNew.getFpkfdm()+" Fpzldm:"+fpkcNew.getFpzldm()+" Jcsj:"+fpkcNew.getJcsj()+" Qshm:"+fpkcNew.getQshm());
						try 
						{
							DAOFactory.getInstance().getFpkcDAO().insert(fpkcNew, conn);
							Debug.out("发票退票管理：已经将fpkc的数据更新到数据库中....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("更新发票库存表出错！");
						}
					}
					
					
					//（5）更新原发票操作信息（设置已退票标识）
					
					Fpczz fpczzLs = new Fpczz();
					
					fpczzLs.setFpkfdm(fpkfdm);
					fpczzLs.setFpzldm(bo.getHtypzdzgxb().getPizzldm());
					fpczzLs.setFphm(bo.getHtypzdzgxb().getPzhm());
					
					fpczzLs.setTpfpzldm(tpfpzldm);
					fpczzLs.setTpfphm(tpqshm);
					fpczzLs.setSfyjbltp(Constants.FP_TPBZ_YTP);
					fpczzLs.setLrr(yhr);
					fpczzLs.setLrrq(now);
					
					try 
					{
						DAOFactory.getInstance().getFpczzDAO().updateTp(fpczzLs, conn);
						Debug.out("发票退票管理：已经将退票数据更新到原发票开票主表中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("更新到原发票开票主表出错！");
					}
					
					//（6）删除合同与凭证对照关系表数据
					try 
					{
						String condition = " where pzfldm = '"+Constants.FP_PZFLDM_FP+"' and pizzldm = '"+bo.getHtypzdzgxb().getPizzldm()+"' and pzhm ='"+bo.getHtypzdzgxb().getPzhm()+"' ";
						DAOFactory.getInstance().getHtypzdzgxbDAO().delete(condition, conn);
						Debug.out("发票退票管理：已经删除合同与凭证对照关系表数据....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("删除合同与凭证对照关系表数据出错！");
					}
					
					//（7）获取数据打印使用
					try 
					{
						// 增加数据权限控制
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
						
						//拼接SQL
						String conditions = " and b.fpkfdm = '"+fpkfdm+"' and b.fpzldm = '"+tpfpzldm+"'  and b.fphm ='"+tpqshm+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFptpPrint(conditions, conn);
						bo.getFpkpList().addAll(fpczAllList);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("查询发票信息出错！");
					}
				}
				else{
					throw new ApplicationException("无相关库存！");
				}
				
			}
			else
			{
				throw new ApplicationException("发票退票信息保存失败！");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally 
		{
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
    
    /**
	 * 加秒
	 * 
	 * @param Timestamp 时间
	 * @param int 加的秒数
	 * @return DATE  返回结果
	 */
	public java.util.Date addSecond(Timestamp timestamp, int seconds) 
	{ 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(timestamp); 
		calendar.add(Calendar.SECOND, seconds); 
		return calendar.getTime(); 
	} 
	
}	