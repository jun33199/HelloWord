package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.HtypzdzgxbLs;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
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
 * <p>Description: 发票管理模块的发票重打功能Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FpcdProcessor implements Processor {

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpcdProcessor.process(vo)");

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
		FpcdBO bo = (FpcdBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //合同编号
		String swjgzzjgdm = bo.getSwjgzzjgdm();
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
			
			//（1）获取查询结果
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//合同编号
            if ((htbh != null) && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "' and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' ";
            }
            
            //发票号码
            if ((qshm != null) && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" +qshm+ "' and b.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"' " +
            			"and b.kplxdm <>'"+Constants.FP_KPLX_FP+"' ";
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFpcd(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
				}
				
				Debug.out("发票重打管理：查询发票重打信息成功....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("发票重打管理：查询发票重打信息出错！");
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
		FpcdBO bo = (FpcdBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//得到当前时间
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String cxfpzldm = bo.getCxfpzldm(); //重打发票种类代码
			String cxqshm = bo.getCxqshm();  //重打发票号码
			String yhr = bo.getYhid() + bo.getYhmc(); //使用人员
			String swjgzzjgdm = bo.getSwjgzzjgdm();  //税务机关组织结构代码
			
			if(cxqshm != null && !cxqshm.equals(""))
			{
				//(1)根据合同编号插入对照关系历史表
				HtypzdzgxbLs htypzdzgxbLs = new HtypzdzgxbLs();
				
				htypzdzgxbLs.setHtbh(bo.getHtypzdzgxb().getHtbh());
				htypzdzgxbLs.setSbbh(bo.getHtypzdzgxb().getSbbh());
				htypzdzgxbLs.setMmfbz(bo.getHtypzdzgxb().getMmfbz());
				htypzdzgxbLs.setPzfldm(Constants.FP_PZFLDM_FP);
				htypzdzgxbLs.setPizzldm(cxfpzldm);
				htypzdzgxbLs.setPzhm(cxqshm);
				htypzdzgxbLs.setCjr(yhr);
				htypzdzgxbLs.setCjrq(now);
				htypzdzgxbLs.setLrr(yhr);
				htypzdzgxbLs.setLrrq(now);
				htypzdzgxbLs.setCzr(yhr);
				htypzdzgxbLs.setCzrq(now);
				htypzdzgxbLs.setSwjgzzjgdm(swjgzzjgdm);
				htypzdzgxbLs.setBz("|重打(90)");
				
				try 
				{
					DAOFactory.getInstance().getHtypzdzgxbLsDAO().insert(htypzdzgxbLs, conn);
					Debug.out("发票重打管理：已经将到对照关系表数据插入到对照关系历史表中....");
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("插入对照关系历史表出错！");
				}
			}
			else
			{
				throw new ApplicationException("发票重打信息保存失败！");
			}
			
			//（5）获取数据打印使用
			try 
			{
				// 增加数据权限控制
	            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
	                    "FPDB", "FP_JL_FPKPZB" );
	            Debug.out("datafilter: " + datafilter);
	            
				//拼接SQL
				String conditions = " and a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+cxfpzldm+"'  and a.fphm ='"+cxqshm+"'  " +
									"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
				ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpcdPrint(conditions, conn);
				bo.getFpkpList().addAll(fpczAllList);
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("查询发票信息出错！");
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
