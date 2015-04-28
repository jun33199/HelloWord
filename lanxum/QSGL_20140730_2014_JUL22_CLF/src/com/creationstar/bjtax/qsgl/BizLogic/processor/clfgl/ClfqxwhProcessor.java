package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.sql.Connection;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Clfjycs;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhBo;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class ClfqxwhProcessor implements Processor 
{
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) 
		{

		case ActionType.QUERY:
			result = getQxwh(vo); // 获得权限维护信息
			break;	
		case ActionType.UPDATE:
			result = updateQxwh(vo); // 修改权限维护信息
			break;	
		case ActionType.DELETE:
			result = deleteQxwh(vo); // 删除权限维护信息	
			break;	
		
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}

		return result;
	}

	/**
     * 存量房修改权限维护的查询
     *
     * @param vo VOPackage
     * @return Object 业务对象
     * @throws BaseException 可能抛出的异常
     */
	private Object getQxwh(VOPackage vo) throws BaseException
	{
		Debug.out("--Debug-- Info : Here is ClfqxwhProcessor.doQuery(vo)");
		Connection conn = null;
        
        ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
        UserData ud = vo.getUserData();
        
        try 
		{	
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			
			// 增加数据权限控制
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"DMDB", "QS_DM_CLFJYCSB" );
	        Debug.out("datafilter: " + datafilter);
	        
			
			//（1）获取查询结果
			String conditions = " where cslx ='02' and "+datafilter;
			
            try
			{
				
				bo.setJycsList(DAOFactory.getInstance().getClfjycsDAO().queryCsList(conn, conditions));
				System.out.println(bo.getJycsList().size());
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
	
	
	/**
     * 存量房修改权限维护的删除
     *
     * @param vo VOPackage
     * @return Object 业务对象
     * @throws BaseException 可能抛出的异常
     */
	private Object deleteQxwh(VOPackage vo) throws BaseException{
		Connection conn = null;
		try{
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			//获得前台提交的数据
			ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
			
			String dyz = bo.getDyz();
			if(dyz == null || "".equals(dyz)){
				throw new ApplicationException("删除出错，请重试，或者和管理员联系！");
			}
			
			
		//执行保存
		DAOFactory.getInstance().getClfjycsDAO().delete(conn, dyz);
				
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);			
		}finally {
	           QSDBUtil.freeConnection(conn);
	       }
		return this.getQxwh(vo);
	}

	
	/**
     * 存量房修改权限维护的修改
     *
     * @param vo VOPackage
     * @return Object 业务对象
     * @throws BaseException 可能抛出的异常
     */
	private Object updateQxwh(VOPackage vo) throws BaseException{
		//获得前台提交的数据
		ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
		
		//保存新增信息
		String allNewAddInfo = bo.getAllNewAddInfo();
		if(allNewAddInfo != null && !"".equals(allNewAddInfo)){
			this.SaveNewAdd(vo);
		}
		
		
		
		return null;
	}
	
	
	/**
	 * 保存新增人员信息
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private boolean SaveNewAdd(VOPackage vo) throws BaseException{
		Connection conn = null;
		UserData ud = vo.getUserData();
		try{
			String isExistStr = "";
			
			
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			//获得前台提交的数据
			ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
			
			
			String allNewAddInfo = bo.getAllNewAddInfo();
			
			if(allNewAddInfo == null || "".equals(allNewAddInfo)){
				throw new ApplicationException("保存新增人员权限信息出错，无法获得提交数据，请重试，或者和管理员联系！");
			}
			
			//构造提交信息
			ArrayList sellerList = new ArrayList();
			String regEx ="[\\^]";//定义多组用^分开
			String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

			for (int index = 0; index < splitAllNewAddInfo.length; index++) {

				String[] oneSellerInfoArr = new String[] {};
				if (splitAllNewAddInfo[index] != null) {
					System.out.println("新增人员信息：："+splitAllNewAddInfo[index]);

					oneSellerInfoArr = splitAllNewAddInfo[index].split("~");
					if (oneSellerInfoArr.length != 0) {
						Clfjycs cs = new Clfjycs();

						cs.dyz = oneSellerInfoArr[0];
						cs.cslxms=oneSellerInfoArr[1];
						cs.zxbs = oneSellerInfoArr[2];
						cs.cjr = ud.getYhid();
						cs.lrr = ud.getYhid();
						cs.swjgzzjgdm =ud.getSsdwdm();
						
						
						//检查是否已经存在
						boolean isExist = DAOFactory.getInstance().getClfjycsDAO().isExist(conn, cs.dyz);
						
						if(isExist){
							isExistStr+="("+cs.dyz+":"+cs.cslxms+")";
						}else{
							sellerList.add(cs);
						}
					}
				}
			}
			
			if(isExistStr != null && !"".equals(isExistStr)){
				throw new ApplicationException("保存新增人员权限信息出错，人员已经存在，不能重复新增,请删除后重试："+isExistStr);
			}
			
			
			
			
		//执行保存
			if(sellerList != null && sellerList.size() != 0){
				DAOFactory.getInstance().getClfjycsDAO().saveNewAdd(conn, sellerList);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);			
		}finally {
	           QSDBUtil.freeConnection(conn);
	       }
		return true;
	}
	
	
	

}
