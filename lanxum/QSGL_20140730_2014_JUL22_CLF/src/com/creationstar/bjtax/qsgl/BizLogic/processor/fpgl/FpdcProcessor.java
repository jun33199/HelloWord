package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
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
 * <p>Description: 发票管理模块的发票导出功能Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FpdcProcessor implements Processor{

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpdcProcessor.process(vo)");

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
		FpdcBO bo = (FpdcBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //合同编号
		String swjgzzjgdm = bo.getSwjgzzjgdm();  //税务机关组织机构代码
		String fpkfdm = bo.getFpkfdm();  //发票库房代码
		String fpzldm = bo.getFpzldm();  //发票种类代码
		String qshm = SecurityUtil.dealwithStringPara(bo.getQshm());  //发票种类代码
		String qsrq = bo.getQsrq();  // 起始时间
        String jzrq = bo.getJzrq();  // 截止时间
        String dczt = bo.getDczt();  // 导出状态
        
		Connection conn = null;
		try 
		{	
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			//1.判断合同编号是否存在
			if((htbh != null) && !htbh.equals(""))
			{
				String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
				String fwxxconditions = " where htbh = '" + htbh+ "' and"+fwxxfilter;
				
				ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, fwxxconditions);
				
				if(fwxxList == null || fwxxList.size() == 0)
				{
					bo.setMessagefwx("无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
					return bo;
				}
			}
			
			//2.查询发票信息
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
                    "FPDB", "FP_JL_FPKPZB" );
            Debug.out("datafilter: " + datafilter);
			
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//合同编号
            if (htbh != null && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "' and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' ";
            }

            //发票号码
            if (qshm != null && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" + qshm + "' ";
            }
            
            if ((qsrq != null) && !qsrq.equals(""))
            {
        		conditions = conditions + " and b.kprq>=to_date('" + qsrq +" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            }

            if ((jzrq != null) && !jzrq.equals(""))
            {
            	conditions = conditions + " and b.kprq<=to_date('" + jzrq +" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
            }
            
            if ((dczt != null && !dczt.equals("")) && !dczt.equals("-1"))
            {
            	conditions = conditions + " and b.dcbz = '" + dczt + "'  ";
            }
            
            if((htbh == null || htbh.equals("")) && (qshm == null || qshm.equals("")) && (qsrq == null || qsrq.equals("")) && (jzrq == null || jzrq.equals("")) && (dczt != null && !dczt.equals("") && dczt.equals("0")))
            {
            	conditions = conditions + " and b.kprq >= trunc(trunc(sysdate, 'month') - 1, 'month') and b.kprq < trunc(sysdate, 'month') " ;
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFpdc(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
				}
				
				Debug.out("发票导出管理：查询发票导出信息成功....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("发票导出管理：查询发票导出信息出错！");
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
	 * doSave
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException
	{
		// 从VOPackage中获得数据
		FpdcBO bo = (FpdcBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//得到当前时间
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String swjgzzjgdm = bo.getSwjgzzjgdm();
			String yhr = bo.getYhid() + bo.getYhmc(); //使用人员
			
			ArrayList cxlist = bo.getCxList();
			
			//更新原发票操作信息（设置导出标识）
			for(int i =0; i <cxlist.size(); i++)
			{
				Fpczz cxItem = (Fpczz) cxlist.get(i);
				String fpzldm = cxItem.getFpzldm();  //发票种类代码
				String fphm = cxItem.getFphm();  //发票号码
				
				//System.out.println(">>>fpkfdm:"+fpkfdm+" fpzldm:"+fpzldm+" fphm:"+fphm+" yhr:"+yhr);
				
				if(fphm != null && !fphm.equals(""))
				{
					Fpczz fpczzLs = new Fpczz();
					
					fpczzLs.setFpkfdm(fpkfdm);
					fpczzLs.setFpzldm(fpzldm);
					fpczzLs.setFphm(fphm);
					
					fpczzLs.setDcbz(Constants.FP_DCBZ_YTP);
					fpczzLs.setLrr(yhr);
					fpczzLs.setLrrq(now);
					
					try 
					{
						DAOFactory.getInstance().getFpczzDAO().updateDc(fpczzLs, conn);
						Debug.out("发票导出管理：已经将导出标志更新到原发票开票主表中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("更新到原发票开票主表出错！");
					}
					
					//（5）获取数据导出使用
					try 
					{
						// 增加数据权限控制
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
			            
						//拼接SQL
						String conditions = " and b.fpkfdm = '"+fpkfdm+"' and b.fpzldm = '"+fpzldm+"'  and b.fphm ='"+fphm+"'  and b.dcbz='"+Constants.FP_DCBZ_YTP+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
						
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpdcData(conditions, conn);
						bo.getFpkpList().addAll(fpczAllList);
						
						bo.setFpkfdm(fpkfdm);
						bo.setSwjgzzjgdm(swjgzzjgdm);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("查询发票导出信息出错！");
					}
				}
				else
				{
					throw new ApplicationException("发票导出信息保存失败！");
				}
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

}
