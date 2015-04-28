package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 存量房税务机关核定信息录入 Processor
 * 
 * @author
 * 
 */
public class ClfswjghdxxlrProcessor extends CommonProcessor {

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {

		case ActionType.QUERY:
			result = doQuery(vo); // 查询存量房信息
			break;

		case ActionType.INSERT:
			result = doInsert(vo); // 保存税务人员确认信息
			break;
			
		case ActionType.DELETE:
			doDelete(vo); // 删除税务人员确认信息
			break;
			
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}

		return result;
	}
	
	
	private void doDelete(VOPackage vo)throws BaseException{
		ClfswjghdxxlrBo bvo = (ClfswjghdxxlrBo) vo.getData();
		UserData ud = vo.getUserData();
		String sbbh = SecurityUtil.dealwithStringPara(bvo.getSbbh());
		Connection conn = null;
		int oklevel = 0;
		
		try{
			System.out.println("in ClfswjghdxxlrProcessor delete ###########执行删除操作，删除申报编号为："+sbbh);
			if(sbbh == null || "".equals(sbbh)){
				throw new ApplicationException("删除核定信息出错，无申报编号！");
				
			}
			
			//构造核定信息
			oklevel = 1;
			Fwhdxx fwhdxx = getFwhdxx(bvo, ud);
			if(fwhdxx == null ){
				throw new ApplicationException("删除核定信息出错，无法构造核定信息！");
			}
			
			//获得数据库连接
			oklevel = 2;
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("删除核定信息出错，无法获得数据库连接！");
			}
			
			//执行删除
			oklevel = 3;
			DAOFactory.getInstance().getFwhdxxbDAO().delete(fwhdxx, conn);
		}catch(Exception e){
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(),"税务人员核定信息删除－ClfswjghdxxlrProcessor，操作出错",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");
			
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}else{
				switch (oklevel) {						
				case 1:
					throw new ApplicationException("删除核定信息出错，无法构造核定信息！");				
				case 2:
					throw new ApplicationException("删除核定信息出错，无法获得数据库连接！");
				case 3:
					throw new ApplicationException("删除核定信息出错！");							
				}				
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}
	}
	
	
	

	private Object doInsert(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo bvo = (ClfswjghdxxlrBo) vo.getData();
		UserData ud = vo.getUserData();
		Connection conn = null;
		int oklevel = 0;
		
		String yhid = SecurityUtil.dealwithStringPara(vo.getUserData().getYhid());
		try {
				// 判断必填项
				oklevel = 1; 
				String[] checkDadaRes = checkData(bvo);
				if (!Boolean.valueOf(checkDadaRes[0]).booleanValue()) {
					throw new ApplicationException("保存税务人员核定信息出错!，验证信息有误:"+ checkDadaRes[1]);
				}
			
				//获得数据库连接
				oklevel = 2;
				conn = QSDBUtil.getConnection();
				if(conn == null){
					throw new ApplicationException("保存核定信息出错，无法获得数据库连接！");
				}				
				//构造房屋核定信息
				oklevel = 3;
				Fwhdxx hdxxVo = getFwhdxx(bvo, ud);
				
				//执行保存操作
				oklevel = 4;
				DAOFactory.getInstance().getFwhdxxbDAO().insert(hdxxVo, conn);

				
				//获得修改和删除权限
				oklevel = 5;
				boolean hasAuth = this.getAuth(conn," and dyz='"+yhid+"' ");
				bvo.setHasMAuthorise(hasAuth);
				
				// 设置保存成功标志
				bvo.setIsSaved("true");				
				//获取所在楼层和总楼层的值
				ClfswjghdxxlrBo cbo=this.getCjxx(vo);
				bvo.setSzlc_show(cbo.getSzlc_show());
				bvo.setZlc_show(cbo.getZlc_show());
			} catch (Exception e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),"保存核定信息－ClfswjghdxxlrProcessor，操作出错",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");				
				
				//如果是特定抛出的异常
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}else{
					switch (oklevel) {						
					case 1:
						throw new ApplicationException("保存核定信息出错，审核保存数据不通过，请检查所填数据是否有误！");				
					case 2:
						throw new ApplicationException("保存核定信息出错，无法获得数据库连接，请退出从新登陆，或者和管理员联系！");
					case 3:
						throw new ApplicationException("保存核定信息出错，构造核定信息出错，请检查所填数据是否有误！");	
					case 4:
						throw new ApplicationException("保存核定信息出错，构造核定信息出错，请检查所填数据是否有误！");		
					case 5:
						throw new ApplicationException("保存核定信息出错，获得操作人员修改删除权限出错！");						
					}
				}	
			} finally {
				QSDBUtil.freeConnection(conn);
			}
		return bvo;
	}

	private String[] checkData(ClfswjghdxxlrBo bvo) {

		String isSuccess = "false";

		String[] checkDadaRes = new String[2];
		checkDadaRes[0] = isSuccess;

		// 申报编号不能为空
		if (bvo.getSbbh() == null || "".equals(bvo.getSbbh())) {
			checkDadaRes[1] = "申报编号不能为空";
			return checkDadaRes;
		}

		// 合同编号不能为空
		if (bvo.getHtbh() == null || "".equals(bvo.getHtbh())) {
			checkDadaRes[1] = "合同编号不能为空";
			return checkDadaRes;
		}

		// 房屋容积率([0]：1.0以下;[1]: 1.0(含)以上)
		if (bvo.getFwrjl() == null || "".equals(bvo.getFwrjl())) {
			checkDadaRes[1] = "房屋容积率不能为空";
			return checkDadaRes;

		} else if (!"0".equals(bvo.getFwrjl()) && !"1".equals(bvo.getFwrjl())) {
			checkDadaRes[1] = "房屋容积率选择错误，应为：1.0以下 或 1.0以下";
			return checkDadaRes;

		}

		// 住房使用时间分类（[2]：3年（含）以下;[1]:5年以下3年以上;[0]:5年（含）以上）

		if (bvo.getZfsjsjfl() == null || "".equals(bvo.getZfsjsjfl())) {
			checkDadaRes[1] = "住房使用时间分类不能为空!";
			return checkDadaRes;
		} else if (!"0".equals(bvo.getZfsjsjfl())
				&& !"1".equals(bvo.getZfsjsjfl())
				&& !"2".equals(bvo.getZfsjsjfl())) {
			checkDadaRes[1] = "住房使用时间分类填写不正确，应为：3年（含）以下   或  5年以下3年以上  或 5年（含）以上）!";
			return checkDadaRes;

		}

		// 营业税征收方式(0:免征营业税;1:全额征收营业税:2:税差额征收营业

		if (bvo.getYyszsfsSubmit() == null || "".equals(bvo.getYyszsfsSubmit())) {
			checkDadaRes[1] = "住营业税征收方式不能为空!";
			return checkDadaRes;

		} else if (!"0".equals(bvo.getYyszsfsSubmit())
				&& !"1".equals(bvo.getYyszsfsSubmit())
				&& !"2".equals(bvo.getYyszsfsSubmit())) {
			checkDadaRes[1] = "住营业税征收方式填写不正确，应为：免征营业税  或 全额征收营业税  或  税差额征收营业!";
			return checkDadaRes;
		}

		// 土地增值税征收方式(0:不征收土地增值税;1:免征土地增值税;2:全额征收土地增值税)
		bvo.setTdzsszsfs(bvo.getTdzsszsfsSubmit());
		  if(bvo.getTdzsszsfs() == null || "".equals(bvo.getTdzsszsfs())){
			  checkDadaRes[1]="土地增值税征收方式不能为空!"; 
			  return checkDadaRes; 
		  }else if(!"0".equals(bvo.getTdzsszsfs()) && !"1".equals(bvo.getTdzsszsfs())&&!"2".equals(bvo.getTdzsszsfs())
				 &&!"4".equals(bvo.getTdzsszsfs())&&!"5".equals(bvo.getTdzsszsfs())&&!"6".equals(bvo.getTdzsszsfs())){
			  checkDadaRes[1]="土地增值税征收方式填写不正确,应为:不征收土地增值税  或  免征土地增值税 或 全额征收土地增值税 或 提供购房发票征收土地增值税 或 核定征收土地增值税 或 提供评估报告征收土地增值税"; 
			  return checkDadaRes; 
		  }
		 

		// 计税收入确认方式（0:合同价格;1:核定计税价格）

		if (bvo.getJssrqrfs() == null || "".equals(bvo.getJssrqrfs())) {
			checkDadaRes[1] = "计税收入确认方式不能为空!";
			return checkDadaRes;
		} else if (!"0".equals(bvo.getJssrqrfs())
				&& !"1".equals(bvo.getJssrqrfs())) {
			checkDadaRes[1] = "计税收入确认方式填写不正确,应为：合同价格  或 合同价格";
			return checkDadaRes;
		}

		// 验证正确
		checkDadaRes[0] = "true";
		checkDadaRes[1] = "";

		return checkDadaRes;

	}

	/**
	 * 执行查询操作
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfswjghdxxlrBo doQuery(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo resBo = null;
		Connection conn = null;
		
		int oklevel =0;
		
		try {
			//获得数据库连接
			oklevel =1;
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("查询信息报错，无法获得数据库连接，请退出从新登陆，或者和系统管理员联系！");
			}			
			// 获取存量房采集信息
			oklevel =2;
			resBo = this.getCjxx(vo);
			if(resBo == null ){
				throw new ApplicationException("查询不到同采集信息，请检查合同编号是否正确！");
			}
			
			
			String sbbh = SecurityUtil.dealwithStringPara(resBo.getSbbh());
			String yhid = SecurityUtil.dealwithStringPara(vo.getUserData().getYhid());
			
			
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("查询不到同采集信息，请检查合同编号是否正确！");
			}

			//查询条件
			String condition = "";
			if (sbbh != null && !"".equals(sbbh)) {
				condition = " where sbbh='" + sbbh + "'";
			}
			// 获得税务人员核定信息
			oklevel = 3;
			ArrayList hdxxList = this.getSwryhdxx(conn,condition);
			
			//获得卖方税款征收信息
			oklevel = 4;
			ArrayList mfskxxList = this.getMfskxx(conn,condition);
			//获得发票代开信息
			oklevel = 5;
			ArrayList mffpdkxxList = this.getMfFpdkxx(conn,condition);
			//获得权限信息
			oklevel = 6;
			boolean hasAuth = this.getAuth(conn," and dyz='"+yhid+"' ");
			//获得卖方申报明细信息 （打印用）
			oklevel = 7;
			ArrayList mfsbxxList = this.getMfsbxx(conn,sbbh);
			//获得实缴金额合计
			oklevel = 8;
			String sjhjje = this.getSjhjje(conn, sbbh);

			// 如果信息只有大于一条，则报错
			if (hdxxList != null && hdxxList.size() > 1) {
				throw new ApplicationException("查询税务人员核定信息出错!，同一申报编号存在核定表中存在多条信息，申报编号为："+ resBo.getSbbh());
			}

			//返回结果
			oklevel = 9;
			if (hdxxList != null && hdxxList.size() == 1) {
				Fwhdxx hdxxVo = (Fwhdxx) hdxxList.get(0);

				//@@1 设置核定信息
				if (hdxxVo != null && resBo != null) {
					this.getClfswjghdxxlrBoByHdxxVo(hdxxVo, resBo);
					resBo.setIsSaved("true");// 该合合同信息已经存在于合同编号
				}
				
				//@@2 设置卖方税款征收标志
				if(mfskxxList != null && mfskxxList.size() > 0){
					resBo.setHasMfSkzsxx("true");
				}
				
				//@@2 设置卖方发票代开标志
				if(mffpdkxxList != null && mffpdkxxList.size() > 0){
					resBo.setHasMfFpdkxx("true");
				}
				
				//@@3 设置修改权限
				resBo.setHasMAuthorise(hasAuth);
				
				//@@4 获得卖方申报信息
				if(mfsbxxList != null && mfsbxxList.size() != 0){
					resBo.setMfsbxxList(mfsbxxList);
					
				//@@5 获得合计金额
					resBo.setSjhjje(sjhjje);
				}
			} else {
				// 购房证明日期 初始值为 房屋所有权证填发日期
				if (resBo.getGfzmrq() == null || "".equals(resBo.getGfzmrq())) {
					resBo.setGfzmrq(resBo.getFwsyqztfrq());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(),"税务人员核定信息删除－ClfswjghdxxlrProcessor，操作出错",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");
			
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}else{
				switch (oklevel) {						
				case 1:
					throw new ApplicationException("查询核定信息出错，无法获得数据库连接！");				
				case 2:
					throw new ApplicationException("查询核定信息出错，无法获取存量房采集信息！");
				case 3:
					throw new ApplicationException("查询核定信息出错，无法获得税务人员核定信息！");			
				case 4:
					throw new ApplicationException("查询核定信息出错，无法获得卖方税款征收信息！");	
				case 5:
					throw new ApplicationException("查询核定信息出错，无法获得发票代开信息！");	
				case 6:
					throw new ApplicationException("查询核定信息出错，无法获得权限信息！");	
				case 7:
					throw new ApplicationException("查询核定信息出错，无法获得卖方申报明细信息 ！");	
				case 8:
					throw new ApplicationException("查询核定信息出错，无法获得实缴金额合计！");	
				case 9:
					throw new ApplicationException("查询核定信息出错，无法构造显示信息！");						
				}				
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}
		return resBo;
	}

	/**
	 * 获得税务人员核定信息
	 * 
	 * @param condition
	 * @return
	 * @throws BaseException
	 */
	public ArrayList getSwryhdxx(Connection conn,String condition) throws BaseException {
		System.out.println("+++++++++++获得税务人员核定信息getSwryhdxx ++++++++++++");
		ArrayList hdxxList = new ArrayList();
		// 判断必填项
		if (condition != null && !"".equals(condition)) {
			try {
				hdxxList = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, condition);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					throw new ApplicationException("获取税务人员核定信息出错！");
				} catch (ApplicationException e1) {
					e1.printStackTrace();
					// 处理失败信息:控制台 ＋ 日志
					Debug.printException(e1);
					throw ExceptionUtil.getBaseException(e1);

				}
			}
		} else {
			throw new ApplicationException("无法获取税务人员核定信息，请输入查询条件！");
		}
		return hdxxList;
	}
	
	/**
	 * 获得卖方税款信息
	 * @return
	 */
	private  ArrayList getMfskxx(Connection conn,String condition)throws BaseException{
		ArrayList skxxList = new ArrayList();
		System.out.println("+++++++++++获得卖方税款信息getMfskxx ++++++++++++");
		// 判断必填项
		if (condition != null && !"".equals(condition)) {
			try {
				skxxList = DAOFactory.getInstance().getMfsbxxzbSellerDAO().queryMfsbxxzbAllList(conn, condition);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("获取卖方税款信息出错！");
			}
		} else {
			throw new ApplicationException("无法获取卖方税款信息，请输入查询条件！");
		}
		return skxxList;
	}
	
	
	/**
	 * 获得发票代开信息
	 * @return
	 */
	private  ArrayList getMfFpdkxx(Connection conn,String condition)throws BaseException{
		ArrayList fpdkxxList = new ArrayList();
		System.out.println("+++++++++++获得发票代开信息getMfFpdkxx ++++++++++++");
		// 判断必填项
		if (condition != null && !"".equals(condition)) {
			try {
				fpdkxxList = DAOFactory.getInstance().getHtypzdzgxbDAO().query(condition, conn);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("获取发票代开信息出错！");
			}
		} else {
			throw new ApplicationException("无法获取发票代开信息，请输入查询条件！");
		}		
		return fpdkxxList;
	}
	
	
	/**
	 * 获得修改和删除权限
	 * @return
	 */
	private  boolean getAuth(Connection conn,String condition)throws BaseException{
		System.out.println("+++++++++++获得修改和删除权限getAuth ++++++++++++");
		boolean hasAuth = false;
		// 判断必填项
		if (condition != null && !"".equals(condition)) {
			try {
				hasAuth = DAOFactory.getInstance().getFwxxDAO().queryAuth2UpdateAndDelete(conn, condition);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("获取修改删除权限失败！");
			}
		} else {
			throw new ApplicationException("无法获取修改删除权限！");
		}		
		return hasAuth;
	}
	
	/**
	 * 卖方申报信息，打印显示用
	 * @return
	 */
	private ArrayList getMfsbxx(Connection conn,String sbbh)throws BaseException{
		ArrayList mfsbxxList = new ArrayList();
		// 判断必填项
		if (sbbh != null && !"".equals(sbbh)) {
			try {
				mfsbxxList = DAOFactory.getInstance().getFwhdxxbDAO().queryMfsbxx(conn, sbbh);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("获取卖方申报信息失败！");
			}
		} else {
			throw new ApplicationException("无法获取卖方申报信息！");
		}		
		return mfsbxxList;
	}
	
	
	/**
	 * 卖方申报信息，实缴金额合计
	 * @return
	 */
	private String getSjhjje(Connection conn,String sbbh)throws BaseException{
		String sjhjje = "";
		// 判断必填项
		if (sbbh != null && !"".equals(sbbh)) {
			try {
				sjhjje = DAOFactory.getInstance().getFwhdxxbDAO().queryMfsbSJHJJE(conn, sbbh);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("获取卖方申报信息失败！");
			}
		} else {
			throw new ApplicationException("无法获取卖方申报信息！");
		}		
		return sjhjje;
	}

	/**
	 * 获得存量房采集信息
	 * 
	 * @methodName:getCjxx
	 * @function:
	 * 
	 * @param sbbh
	 *            申报编号
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:唐昌富
	 * @create date：2013-5-16 下午03:18:28
	 * @version 1.1
	 * 
	 * 
	 */
	public ClfswjghdxxlrBo getCjxx(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		ClfswjghdxxlrBo resBo = null;
		System.out.println("sbbh................."+sbbh);
		System.out.println("htbh................."+htbh);
		if ((sbbh == null || "".equals(sbbh))
				&& (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("获取采集出错，无查询条件!");
		}

		// 拼接查询SQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");

		if (sbbh != null && !"".equals(sbbh)) {
			sqlBuff.append("and sbbh ='" + sbbh + "'");
		}

		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			
			// 增加数据权限控制
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(vo.getUserData(),
	                "QSDB", "QS_JL_FWXXB" );
	        Debug.out("datafilter: " + datafilter);
	        
			// 获得房屋信息
			List fwxxList = DAOFactory.getInstance().getFwxxDAO()
					.queryFwList(conn, sqlBuff.toString() +" and "+datafilter);

			if (fwxxList.size() > 0) {
				resBo = new ClfswjghdxxlrBo();
			} else {
				throw new ApplicationException("无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
			}

			for (int index = 0; index < fwxxList.size(); index++) {
				Fwxx fwxx = (Fwxx) fwxxList.get(index);

				resBo.sbbh = fwxx.sbbh;
				resBo.UNEpiccode = fwxx.ewmsj;
				resBo.bbbs = fwxx.bbbs;
				resBo.htbh = fwxx.htbh;
				resBo.fwcqzh = fwxx.fwcqzh;
				resBo.fwsyqztfrq = DataConvert.TimeStamp2String(fwxx.fwsyqztfrq);
				resBo.fwzlqx = fwxx.fwzlqx;
				resBo.fwzldz = fwxx.fwzldz;
				resBo.fwjzmj = fwxx.fwjzmj + "";
				resBo.jzjgdm = fwxx.jzjgdm;
				resBo.ghyt = fwxx.ghyt;
				resBo.fwqszylx = fwxx.fwqszylx;
				resBo.fwqszylxmc = ActionUtil.getFwqszylxmc(fwxx.fwqszylx);
				resBo.szlc = fwxx.szlc + "/" + fwxx.zcs;
				resBo.szlc_show = fwxx.szlc + "";
				resBo.zlc_show = fwxx.zcs + "";
				resBo.htwsqyrq = DataConvert.TimeStamp2String(fwxx.htwsqyrq);
				resBo.sfwscsssggf = fwxx.sfwscsssggf;
				resBo.htzj = fwxx.htzj + "";
				resBo.bzdm = fwxx.bzdm;
				resBo.bzmc = fwxx.bzmc;
				resBo.hl = fwxx.hl + "";
				resBo.wbje = fwxx.wbje + "";
				resBo.fdczjjgmc = fwxx.fdczjjgmc;
			}
			
			// 获得卖方信息
			List seller_List = DAOFactory.getInstance().getMfgrxxSellerDAO()
					.queryMfgrxxSellerList(conn, sqlBuff.toString());
			StringBuffer sellerBuf = new StringBuffer();
			StringBuffer allSellerNames4jyxxcxBuf = new StringBuffer();
			for (int index = 0; index < seller_List.size(); index++) {
				MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
				mfgrxxSellerItem = (MfgrxxSeller) seller_List.get(index);
				if (index > 0) {
					sellerBuf.append("^");
				}
				sellerBuf.append(mfgrxxSellerItem.getNsrmc());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getLb());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getZjlxdm());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getZjhm());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getQlrfe());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getLxdh());

				// 所有卖方姓名，多个用“/”隔开，给存量房交易信息查询用
				if (index == 0) {
					allSellerNames4jyxxcxBuf
							.append(mfgrxxSellerItem.getNsrmc());
				} else {
					allSellerNames4jyxxcxBuf.append("/"
							+ mfgrxxSellerItem.getNsrmc());

				}

			}
			System.out.println("卖方信息:::::" + sellerBuf.toString());
			resBo.setAll_sellerInfo(sellerBuf.toString());
			resBo.setSellerList(seller_List);
			resBo.setAllSellerNames4jyxxcx(allSellerNames4jyxxcxBuf.toString());

			// 获得买方信息
			List buyer_list = DAOFactory.getInstance().getMfgrxxBuyerDAO()
					.queryMfgrxxBuyerList(conn, sqlBuff.toString());
			StringBuffer buyerBuf = new StringBuffer();
			StringBuffer allBuyerNames4jyxxcxBuf = new StringBuffer();
			for (int index = 0; index < buyer_list.size(); index++) {
				MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
				mfgrxxBuyerItem = (MfgrxxBuyer) buyer_list.get(index);
				if (index > 0) {
					buyerBuf.append("^");
				}
				buyerBuf.append(mfgrxxBuyerItem.getNsrmc());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLb());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjlxdm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjhm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getQlrfe());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLxdh());

				// 所有卖方姓名，多个用“/”隔开，给存量房交易信息查询用
				if (index == 0) {
					allBuyerNames4jyxxcxBuf.append(mfgrxxBuyerItem.getNsrmc());
				} else {
					allBuyerNames4jyxxcxBuf.append("/"
							+ mfgrxxBuyerItem.getNsrmc());

				}
			}
			System.out.println("买方信息：：：：" + buyerBuf.toString());
			resBo.setAll_buyerInfo(buyerBuf.toString());
			resBo.setBuyerList(buyer_list);
			resBo.setAllBuyerNames4jyxxcx(allBuyerNames4jyxxcxBuf.toString());

		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "存量房信息采集－ClfxxcjProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	private Fwhdxx getFwhdxx(ClfswjghdxxlrBo bvo, UserData ud) {
		Fwhdxx hdxxVo = new Fwhdxx();
		hdxxVo.setSbbh(bvo.getSbbh());
		hdxxVo.setHtbh(bvo.getHtbh());
		hdxxVo.setSqrxzdz(bvo.getSqrxzdz());
		hdxxVo.setJtwyshyhbz(bvo.getJtwyshyhbz());
		hdxxVo.setFwlxdm(bvo.getFwlxdm());
		hdxxVo.setJcnd(bvo.getJcnd());
		// hdxxVo.setZlc(bvo.getZlc());
		// fwjzmj
		System.out.println("房屋建筑面积++++++++++" + bvo.getFwjzmj());
		System.out.println("合理费用++++++++++" + bvo.getHlfy());
		hdxxVo.setFwjzmj(string2BigDecimal(bvo.getFwjzmj()));
		hdxxVo.setYgffpje(string2BigDecimal(bvo.getYgffpje()));
		hdxxVo.setGfzmrq(DataConvert.String2Timestamp(bvo.getGfzmrq()));
		hdxxVo.setTdzzssbfs(bvo.getTdzzssbfs());
		hdxxVo.setQdfcqsje(string2BigDecimal(bvo.getQdfcqsje()));
		hdxxVo.setQdfcyhsje(string2BigDecimal(bvo.getQdfcyhsje()));
		hdxxVo.setQdtdsyqzfje(string2BigDecimal(bvo.getQdtdsyqzfje()));
		hdxxVo.setJfpgjg(string2BigDecimal(bvo.getJfpgjg()));
		hdxxVo.setJgpgfy(string2BigDecimal(bvo.getJgpgfy()));
		hdxxVo.setFdczjjsjdm(bvo.getFdczjjsjdm());
		hdxxVo.setFdczjswdjzh(bvo.getFdczjswdjzh());
		hdxxVo.setFdczjlxdh(bvo.getFdczjlxdh());
		hdxxVo.setFdczjjjr(bvo.getFdczjjjr());
		hdxxVo.setFdczjjjrlxdh(bvo.getFdczjjjrlxdh());
		hdxxVo.setFdczjjjrzjhm(bvo.getFdczjjjrzjhm());
		hdxxVo.setFdczjjjrzgzsh(bvo.getFdczjjjrzgzsh());
		hdxxVo.setCqzbzjzmjfl(bvo.getCqzbzjzmjflSubmit());
		hdxxVo.setMpmjydj(string2BigDecimal(bvo.getMpmjydj()));
		hdxxVo.setPtzfzgxj(string2BigDecimal(bvo.getPtzfzgxj()));
		hdxxVo.setFwrjl(bvo.getFwrjl());
		hdxxVo.setHfbz(bvo.getHfbzSubmit());
		hdxxVo.setZfsjsjfl(bvo.getZfsjsjfl());
		hdxxVo.setYyszsfs(bvo.getYyszsfsSubmit());
		hdxxVo.setGrsdszsfs(bvo.getGrsdszsfsSubmit());
		hdxxVo.setTdzsszsfs(bvo.getTdzsszsfsSubmit());
		hdxxVo.setJssrqrfs(bvo.getJssrqrfs());
		hdxxVo.setJsje(string2BigDecimal(bvo.getJsjeSubmit()));
		hdxxVo.setZfpgjg(string2BigDecimal(bvo.getZfpgjg()));
		hdxxVo.setZfzxfy(string2BigDecimal(bvo.getZfzxfy()));
		hdxxVo.setZfdklx(string2BigDecimal(bvo.getZfdklx()));
		hdxxVo.setSxf(string2BigDecimal(bvo.getSxf()));
		hdxxVo.setGzf(string2BigDecimal(bvo.getGzf()));
		hdxxVo.setHlfy(string2BigDecimal(bvo.getHlfy()));
		hdxxVo.setTdjcdm(bvo.getTdjcdm());
		// hdxxVo.setTdjcmc(string2BigDecimal(bvo.getTdjcmc()));
		hdxxVo.setFwcqzbzzflxdm(bvo.getFwcqzbzzflxdm());
		// hdxxVo.setFwcqzbzzflxmc(bvo.getFwcqzbzzflxmc());
		// hdxxVo.setCqzbzjzmjflSubmit(bvo.getCqzbzjzmjflSubmit());
		// hdxxVo.setHfbzSubmit(bvo.getHfbzSubmit());
		// hdxxVo.setGrsdszsfsSubmit(bvo.getGrsdszsfsSubmit());
		// hdxxVo.setYyszsfsSubmit(bvo.getYyszsfsSubmit());
		// hdxxVo.setJsjeSubmit(bvo.getJsjeSubmit());
		hdxxVo.setLrr(ud.getYhid());
		hdxxVo.setCjr(ud.getYhid());

		hdxxVo.setMpmhdjg(string2BigDecimal(bvo.getMpmhdjg()));// 每平米核定价格
		hdxxVo.setTdcrj(string2BigDecimal(bvo.getTdcrj()));// 土地出让金
		// hdxxVo.setJwcxfwyz();//建委查询房屋原值
		hdxxVo.setCjssl(string2BigDecimal(bvo.getCjssl()));// 城建税税率
		hdxxVo.setFpszrq(DataConvert.String2Timestamp(bvo.getFpszrq()));// 发票所载日期
		hdxxVo.setAnjjse(string2BigDecimal(bvo.getAnjjse()));// 按年加计数额

		hdxxVo.setFwszqydm(bvo.getFwszqydm());// 房屋所在区域代码
		hdxxVo.setFwhdlxdm(bvo.getFwhdlxdm());// 房屋核定类型代码
		hdxxVo.setQszyxsmxdm(bvo.getQszyxsmxdm());// 
		hdxxVo.setYqspfwjsjg(string2BigDecimal(bvo.getYqspfwjsjg()));// 
		hdxxVo.setYhszsfs(bvo.getYhszsfsSubmit());// 
		hdxxVo.setFwszqyje(string2BigDecimal(bvo.getFwszqyje()));//房屋所在区域金额，added by zhangj,2014.10.15

		return hdxxVo;
	}

	/**
	 * 通过 Fwhdxx 获得 ClfswjghdxxlrBo
	 * 
	 * @param hdxxVo
	 * @param bo
	 * @return
	 */
	private ClfswjghdxxlrBo getClfswjghdxxlrBoByHdxxVo(Fwhdxx hdxxVo,
			ClfswjghdxxlrBo bo) {
		DecimalFormat deFormat_00 = new DecimalFormat("#0.00");// 合计金额格式
		DecimalFormat deFormat_0000 = new DecimalFormat("#0.0000");// 合计金额格式

		bo.setSbbh(hdxxVo.getSbbh());
		bo.setHtbh(hdxxVo.getHtbh());
		bo.setSqrxzdz(hdxxVo.getSqrxzdz());
		bo.setJtwyshyhbz(hdxxVo.getJtwyshyhbz());
		bo.setFwlxdm(hdxxVo.getFwlxdm());
		bo.setJcnd(hdxxVo.getJcnd());
		// bo.setZlc(hdxxVo.getZlc());
		// fwjzmj
		System.out.println("房屋建筑面积++++++++++" + hdxxVo.getFwjzmj());
		System.out.println("合理费用++++++++++" + hdxxVo.getHlfy());
		bo.setFwjzmj(deFormat_0000.format(hdxxVo.getFwjzmj()));
		bo.setYgffpje(deFormat_00.format(hdxxVo.getYgffpje()));

		if (hdxxVo.getGfzmrq() != null || !"".equals(hdxxVo.getGfzmrq())) {
			bo.setGfzmrq(DateUtils.getDate(hdxxVo.getGfzmrq()));
		}

		bo.setTdzzssbfs(hdxxVo.getTdzzssbfs());
		bo.setQdfcqsje(deFormat_00.format(hdxxVo.getQdfcqsje()));
		bo.setQdfcyhsje(deFormat_00.format(hdxxVo.getQdfcyhsje()));
		bo.setQdtdsyqzfje(deFormat_00.format(hdxxVo.getQdtdsyqzfje()));
		bo.setJfpgjg(deFormat_00.format(hdxxVo.getJfpgjg()));
		bo.setJgpgfy(deFormat_00.format(hdxxVo.getJgpgfy()));
		bo.setFdczjjsjdm(hdxxVo.getFdczjjsjdm());
		bo.setFdczjswdjzh(hdxxVo.getFdczjswdjzh());
		bo.setFdczjlxdh(hdxxVo.getFdczjlxdh());
		bo.setFdczjjjr(hdxxVo.getFdczjjjr());
		bo.setFdczjjjrlxdh(hdxxVo.getFdczjjjrlxdh());
		bo.setFdczjjjrzjhm(hdxxVo.getFdczjjjrzjhm());
		bo.setFdczjjjrzgzsh(hdxxVo.getFdczjjjrzgzsh());
		bo.setCqzbzjzmjfl(hdxxVo.getCqzbzjzmjfl());
		bo.setMpmjydj(deFormat_00.format(hdxxVo.getMpmjydj()));
		bo.setPtzfzgxj(deFormat_00.format(hdxxVo.getPtzfzgxj()));
		bo.setFwrjl(hdxxVo.getFwrjl());
		bo.setHfbz(hdxxVo.getHfbz());
		bo.setZfsjsjfl(hdxxVo.getZfsjsjfl());
		bo.setYyszsfs(hdxxVo.getYyszsfs());
		bo.setGrsdszsfs(hdxxVo.getGrsdszsfs());
		bo.setTdzsszsfs(hdxxVo.getTdzsszsfs());
		bo.setYhszsfs(hdxxVo.getYhszsfs());
		bo.setJssrqrfs(hdxxVo.getJssrqrfs());
		bo.setJsje(deFormat_00.format(hdxxVo.getJsje()));
		bo.setZfpgjg(deFormat_00.format(hdxxVo.getZfpgjg()));
		bo.setZfzxfy(deFormat_00.format(hdxxVo.getZfzxfy()));
		bo.setZfdklx(deFormat_00.format(hdxxVo.getZfdklx()));
		bo.setSxf(deFormat_00.format(hdxxVo.getSxf()));
		bo.setGzf(deFormat_00.format(hdxxVo.getGzf()));
		bo.setHlfy(deFormat_00.format(hdxxVo.getHlfy()));
		bo.setTdjcdm(hdxxVo.getTdjcdm());
		// bo.setTdjcmc(string2BigDecimal(hdxxVo.getTdjcmc()));
		bo.setFwcqzbzzflxdm(hdxxVo.getFwcqzbzzflxdm());
		// bo.setFwcqzbzzflxmc(hdxxVo.getFwcqzbzzflxmc());
		// bo.setCqzbzjzmjflSubmit(hdxxVo.getCqzbzjzmjflSubmit());
		// bo.setHfbzSubmit(hdxxVo.getHfbzSubmit());
		// bo.setGrsdszsfsSubmit(hdxxVo.getGrsdszsfsSubmit());
		// bo.setYyszsfsSubmit(hdxxVo.getYyszsfsSubmit());
		// bo.setJsjeSubmit(hdxxVo.getJsjeSubmit());

		bo.setMpmhdjg(deFormat_00.format(hdxxVo.mpmhdjg));// 每平米核定价格
		bo.setTdcrj(deFormat_00.format(hdxxVo.tdcrj));// 土地出让金
		// bo.setJwcxfwyz(deFormat_00.format(hdxxVo.jwcxfwyz);//建委查询房屋原值
		bo.setCjssl(deFormat_00.format(hdxxVo.cjssl));// 城建税税率

		/**
		 * 发票所载日期
		 */
		if (hdxxVo.fpszrq != null && !"".equals(hdxxVo.fpszrq)) {
			bo.setFpszrq(DateUtils.getDate(hdxxVo.fpszrq));
		}
		bo.setAnjjse(deFormat_00.format(hdxxVo.anjjse));// 按年加计数额
		bo.setFwszqydm(hdxxVo.fwszqydm);// 房屋所在区域代码

		bo.setFwhdlxdm(hdxxVo.getFwhdlxdm());// 房屋核定类型代码
		bo.setQszyxsmxdm(hdxxVo.getQszyxsmxdm());// 
		bo.setYqspfwjsjg(deFormat_00.format(hdxxVo.getYqspfwjsjg()));// 
		bo.setFwszqyje(deFormat_00.format(hdxxVo.getFwszqyje()));//ADDED BY ZHANGJ,2014.10.15
		
		return bo;
	}

	public BigDecimal string2BigDecimal(String StrJe) {

		if (StrJe == null || "".equals(StrJe)) {
			StrJe = "0.00";

		}
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式

		BigDecimal zje = new BigDecimal(StrJe);// 获得增加前的合计金额信息

		return zje;
	}
}
