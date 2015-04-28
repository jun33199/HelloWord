package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
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
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: XXXXX Processor
 * </p>
 * 
 * @author 唐昌富
 * @version 1.1
 */
public class ClfxxcjProcessor extends CommonProcessor {
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case ActionType.INSERT:
			result = doInsertCjxx(vo); // 保存采集信息
			break;
		case ActionType.QUERY:
			result = getCjxx(vo); // 获得采集信息
			break;	
		case ActionType.UPDATE:
			result = updateCjxx(vo); // 修改采集信息
			break;	
		case ActionType.DELETE:
			deleteCjxx(vo); // 删除采集信息	
			break;	
		case ActionType.LOADCODES:
			result = doGetAuth(vo); // 获得修改和删除权限	
			break;			
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}

		return result;
	}
	
	/**
	 * 检查登陆用户是否有修改和删除权限
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public Object doGetAuth(VOPackage vo) throws BaseException {
		Connection conn = null;
		// 获得前台传递过来的数据
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		UserData ud = vo.getUserData();	
		
		try{
			conn = QSDBUtil.getConnection();
			
			if(conn == null ){
				throw new ApplicationException("查询修改权限时，获得数据库连接失败!");
			}
			String condition =" and dyz='"+SecurityUtil.dealwithStringPara(ud.getYhid())+"' ";
			
			boolean hasAuth = DAOFactory.getInstance().getFwxxDAO().queryAuth2UpdateAndDelete(conn, condition);
			
			System.out.println("是否含有修改权限+++++++++1111111111"+hasAuth);
		    data.setHasMAuthorise(hasAuth);
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		return data;
		
	}
	

	/**
	 * 保存采集信息（包括房屋信息和买卖方信息）
	 * @methodName:doInsertCjxx
	 * @function:
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 * @author:唐昌富
	 * @create date：2013-5-14 下午02:45:43
	 * @version 1.1
	 * 
	 * 
	 */

	private Object doInsertCjxx(VOPackage vo) throws BaseException {
		System.out.println("二维码数据1111111+++++++++++++++++");
		Connection conn = null;
		int oklevel = 0;

		// 获得前台传递过来的数据
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		HashMap zjMap = data.getZjMap();
		UserData ud = vo.getUserData();

		try {
			conn = QSDBUtil.getConnection();
			String sbbh = getSbbh(ud, conn);
			String htbh = SecurityUtil.dealwithStringPara(data.htbh);

			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("保存出错，无法获得申报编号!");
			}

			if (htbh == null || "".equals(htbh)) {
				throw new ApplicationException("保存出错，合同编号为空!");
			}
			
			//如果合同编号已经存在，则抛出提示，不能保存
			List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, "where htbh ='"+htbh+"'");
			if(fwxxList.size()!=0){
				throw new ApplicationException("合同编号已存在!");
			}		
			
			//如果合同编号已经存在，则抛出提示，不能保存
			fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, "where sbbh ='"+SecurityUtil.dealwithStringPara(sbbh)+"'");
			if(fwxxList.size()!=0){
				throw new ApplicationException("申报编号已存在!");
			}			

			//获得并构造房屋信息
			oklevel = 1;
			Fwxx fwxx = createFwxx(data, ud, sbbh);
			System.out.println("二维码数据1111111+++++++++++++++++"+fwxx.ewmsj);

			// @@2构造卖方信息
			oklevel = 2;
			List sellerList = createSellersInfo(data, zjMap, ud, sbbh);

			// @@3构造买方信息
			oklevel = 3;
			List buyyerList = createBuyersInfo(data, zjMap, ud, sbbh);

			// 访问DAO层，写入数据库
			// @@1写入房屋信息表
			oklevel = 4;
			DAOFactory.getInstance().getFwxxDAO().insert(fwxx, conn);
			// @@2写入卖方信息表
			oklevel = 5;
			DAOFactory.getInstance().getMfgrxxSellerDAO()
					.InsertMfgrxxSellerList(conn, sellerList);
			// @@3写入买方信息表
			oklevel = 6;
			DAOFactory.getInstance().getMfgrxxBuyerDAO()
					.InsertMfgrxxBuyerList(conn, buyyerList);
			
			//设置申报编号，查询用
			data.setSbbh(sbbh);
			vo.setData(data);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
				case 0:
					throw new ApplicationException(ex.getMessage());					
				case 1:
					throw new ApplicationException("保存失败，构造房屋信息出错！");				
				case 2:
					throw new ApplicationException("保存失败，构造卖方信息出错！");
				case 3:
					throw new ApplicationException("保存失败，构造买方信息出错！");							
				case 4:
					throw new ApplicationException("保存失败，插入房屋信息表出错!");
				case 5:
					throw new ApplicationException("保存失败，插入卖方表出错!");
				case 6:
						throw new ApplicationException("保存失败，插入买方信息表出错!");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),
						"存量房信息采集－ClfxxcjProcessor，操作出错", new StackMsg2StringUtil(
								e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");
				throw ExceptionUtil.getBaseException(e);
			}

		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		//获取返回显示信息
		ClfxxcjBo res = getCjxx(vo);
		res.setSaveIsSuccess("1");//保存成功标志 0--失败  1--成功
		System.out.println("二维码数据2222222222+++++++++++++++++");
		return res;
	}
	/**
	 * @@1获得并构造房屋信息
	 * @param data
	 * @param ud
	 * @param sbbh
	 * @return
	 */
	private Fwxx createFwxx(ClfxxcjBo data, UserData ud, String sbbh) {
		// @@1获得并构造房屋信息
		Fwxx fwxx = new Fwxx();

		fwxx.sbbh = sbbh;
		fwxx.htbh = data.htbh;
		fwxx.fwcqzh = data.fwcqzh;
		fwxx.fwsyqztfrq = DataConvert.String2Timestamp(data.fwsyqztfrq);

		if (data.fwzlqx != null && !"".equals(data.fwzlqx)) {
			fwxx.fwzlqx = data.fwzlqx;
			if (data.fwzlqx.indexOf("undefined") > 0) {
				fwxx.fwzlqx = data.fwzlqx.replaceAll("undefined", "");
			}
		}

		fwxx.fwzldz = data.fwzldz;
		fwxx.fwjzmj = string2BigDecimal(data.fwjzmj);
		fwxx.jzjgdm = data.jzjgdm;
		fwxx.ghyt = data.ghyt;
		fwxx.fwqszylx = data.fwqszylx;

		if (data != null && data.szlc != null && !"".equals(data.szlc)) {
			String[] tempLcArr = new String[] {};
			tempLcArr = data.szlc.split("/");
			//fwxx.szlc = string2BigDecimal(tempLcArr[0]);// 所在楼层
			fwxx.szlc = tempLcArr[0];// 所在楼层
			fwxx.zcs = string2BigDecimal(tempLcArr[1]);// 总层数
		}

		fwxx.htwsqyrq = DataConvert.String2Timestamp(data.htwsqyrq);
		fwxx.sfwscsssggf = data.sfwscsssggf;
		fwxx.htzj = string2BigDecimal(data.htzj);
		fwxx.bzdm = data.bzdm;
		fwxx.bzmc = data.bzmc;
		fwxx.hl = string2BigDecimal(data.hl);
		fwxx.wbje = string2BigDecimal(data.wbje);
		fwxx.fdczjjgmc = data.fdczjjgmc;
		fwxx.bbbs = data.bbbs;
		fwxx.swjgzzjgdm = ud.getSsdwdm();
		if (fwxx.getBbbs() != null && !"".equals(fwxx.getBbbs())) {
			fwxx.sfesf = fwxx.getBbbs().substring(
					fwxx.getBbbs().lastIndexOf("_") - 2,
					fwxx.getBbbs().lastIndexOf("_"));
		}else{
			//默认为二手房
			fwxx.sfesf = "02";
		}
		fwxx.cjr = ud.getYhid();
		fwxx.lrr = ud.getYhid();
		fwxx.ewmsj = data.UNEpiccode;
		fwxx.fwxzdm = data.fwxzdm;
		fwxx.xxly = data.xxly;
		return fwxx;
	}

	/**
	 * @@2构造卖方信息
	 * @param data
	 * @param zjMap
	 * @param ud
	 * @param sbbh
	 * @return
	 * @throws ApplicationException
	 */
	private List createSellersInfo(ClfxxcjBo data, HashMap zjMap, UserData ud,
			String sbbh) throws ApplicationException {
		//@@2构造卖方信息
		String regEx ="[\\^]";//定义多组用^分开
		String allSellerInfo = data.getAll_sellerInfo();
		if (allSellerInfo == null || "".equals(allSellerInfo)) {
			throw new ApplicationException("卖方信息为空，保存失败!");
		}
		List sellerList = new ArrayList();

		System.out.println("构造卖方信息：："+allSellerInfo);
		String[] splitAllSellerInfoArr = allSellerInfo.split(regEx);

		for (int index = 0; index < splitAllSellerInfoArr.length; index++) {

			String[] oneSellerInfoArr = new String[] {};
			if (splitAllSellerInfoArr[index] != null) {
				System.out.println("构造卖方信息：："+splitAllSellerInfoArr[index]);

				oneSellerInfoArr = splitAllSellerInfoArr[index].split("~");
				if (oneSellerInfoArr.length != 0) {
					MfgrxxSeller oneSellerInfo = new MfgrxxSeller();

					oneSellerInfo.sbbh = sbbh;
					oneSellerInfo.htbh = data.htbh;
					oneSellerInfo.sxh = String.valueOf(index + 1);
					oneSellerInfo.nsrmc = oneSellerInfoArr[0];
					oneSellerInfo.lb = oneSellerInfoArr[1];
					oneSellerInfo.zjlxdm = oneSellerInfoArr[2];
					oneSellerInfo.zjhm = oneSellerInfoArr[3];
					oneSellerInfo.qlrfe = oneSellerInfoArr[4];
					oneSellerInfo.lxdh = oneSellerInfoArr[5];
					
					oneSellerInfo.zjlxmc =((Zjlx)zjMap.get(oneSellerInfo.zjlxdm)).getZjlxmc();
					oneSellerInfo.cjr = ud.getYhid();
					oneSellerInfo.lrr = ud.getYhid();
					oneSellerInfo.setSwjgzzjgdm(ud.getSsdwdm());

					sellerList.add(oneSellerInfo);
				}
			}
		}
		return sellerList;
	}
	
	/**
	 * @@3构造买方信息
	 * @param data
	 * @param zjMap
	 * @param ud
	 * @param sbbh
	 * @return
	 * @throws ApplicationException
	 */

	private List createBuyersInfo(ClfxxcjBo data, HashMap zjMap, UserData ud,
			String sbbh) throws ApplicationException {
		//@@3构造买方信息
		String regEx ="[\\^]";//定义多组用^分开
		String allBuyerInfo = data.getAll_buyerInfo();
		List buyyerList = new ArrayList();
		if (allBuyerInfo == null || "".equals(allBuyerInfo)) {
			throw new ApplicationException("买方信息为空，保存失败!");
		}

		System.out.println("构造买方信息"+allBuyerInfo);
		String[] splitAllBuyerInfoArr = allBuyerInfo.split(regEx);

		for (int index = 0; index < splitAllBuyerInfoArr.length; index++) {

			String[] oneBuyerInfoArr = new String[] {};
			if (splitAllBuyerInfoArr[index] != null) {
				System.out.println("构造买方信息"+index+"-->"+splitAllBuyerInfoArr[index]);

				oneBuyerInfoArr = splitAllBuyerInfoArr[index].split("~");
				if (oneBuyerInfoArr.length != 0) {
					MfgrxxBuyer oneBuyerInfo = new MfgrxxBuyer();

					oneBuyerInfo.sbbh = sbbh;
					oneBuyerInfo.htbh = data.htbh;
					oneBuyerInfo.sxh = String.valueOf(index + 1);
					oneBuyerInfo.nsrmc = oneBuyerInfoArr[0];
					oneBuyerInfo.lb = oneBuyerInfoArr[1];
					oneBuyerInfo.zjlxdm = oneBuyerInfoArr[2];
					oneBuyerInfo.zjhm = oneBuyerInfoArr[3];
					oneBuyerInfo.qlrfe = oneBuyerInfoArr[4];
					oneBuyerInfo.lxdh = oneBuyerInfoArr[5];
					oneBuyerInfo.zjlxmc = ((Zjlx)zjMap.get(oneBuyerInfo.zjlxdm)).getZjlxmc();
					oneBuyerInfo.cjr = ud.getYhid();
					oneBuyerInfo.lrr = ud.getYhid();
					oneBuyerInfo.setSwjgzzjgdm(ud.getSsdwdm());
					buyyerList.add(oneBuyerInfo);
				}
			}
		}
		return buyyerList;
	}


	public BigDecimal string2BigDecimal(String StrJe) {

		if (StrJe == null || "".equals(StrJe)) {
			StrJe = "0.00";

		}
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式

		BigDecimal zje = new BigDecimal(StrJe);// 获得增加前的合计金额信息

		return zje;
	}

	/**
	 * 获得采集信息
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
	public ClfxxcjBo getCjxx(VOPackage vo) throws BaseException {
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		ClfxxcjBo resBo = new ClfxxcjBo();
		UserData ud = vo.getUserData();
		
		if ((sbbh == null || "".equals(sbbh)) && (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("获取采集出错，无查询条件!");
		}
		
		//拼接查询SQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");
		
		if (sbbh != null && !"".equals(sbbh)){
			sqlBuff.append("and sbbh ='" + sbbh + "'");
		}

		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			//如果无法获取实例
			if(dao == null){
				throw new ApplicationException("获得采集信息出错，无法创建DAOFactory实例!");
				
			}	
			
			//System.out.println("++++++++++++所属单位代码++++++++++"+vo.getUserData().getSsdwdm());
			// 增加数据权限控制
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"QSDB", "QS_JL_FWXXB" );
	        //Debug.out("datafilter: " + datafilter);
	        
			
			// 获得房屋信息
			List fwxxList = dao.getFwxxDAO().queryFwList(conn, sqlBuff.toString()+" and "+datafilter);
			
			
			if(fwxxList == null || fwxxList.size() == 0){
				throw new ApplicationException("无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
			}
			
			if(fwxxList.size() > 1){
				throw new ApplicationException("查询出错，同时存在多条相同合同编号或者申报编号的房屋信息!");
			}
			
			//获得房屋信息
				Fwxx fwxx = (Fwxx) fwxxList.get(0);
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
				resBo.fwqszylxmc=ActionUtil.getFwqszylxmc(fwxx.fwqszylx);
				resBo.szlc = fwxx.szlc + "/" + fwxx.zcs;
				resBo.htwsqyrq = DataConvert.TimeStamp2String(fwxx.htwsqyrq);
				resBo.sfwscsssggf = fwxx.sfwscsssggf;
				resBo.htzj = fwxx.htzj + "";
				resBo.bzdm = fwxx.bzdm;
				resBo.bzmc = fwxx.bzmc;
				resBo.hl = fwxx.hl + "";
				resBo.wbje = fwxx.wbje + "";
				resBo.fdczjjgmc = fwxx.fdczjjgmc;
				resBo.fwxzdm = fwxx.fwxzdm;
				resBo.xxly = fwxx.xxly;


			// 获得卖方信息
			List seller_List = dao.getMfgrxxSellerDAO().queryMfgrxxSellerList(conn, sqlBuff.toString());
			StringBuffer sellerBuf = new StringBuffer();
			StringBuffer allSellerNames4jyxxcxBuf = new StringBuffer();
			for(int index =0; index < seller_List.size();index ++){
				MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
				mfgrxxSellerItem = (MfgrxxSeller)seller_List.get(index);
				if(index >0){
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
				
				//所有卖方姓名，多个用“/”隔开，给存量房交易信息查询用
				if(index == 0){
					allSellerNames4jyxxcxBuf.append(mfgrxxSellerItem.getNsrmc());
				}else{
					allSellerNames4jyxxcxBuf.append("/"+mfgrxxSellerItem.getNsrmc());
					
				}
				
			}
			//System.out.println("卖方信息:::::"+sellerBuf.toString());
			resBo.setAll_sellerInfo(sellerBuf.toString());
			resBo.setSellerList(seller_List);
			resBo.setAllSellerNames4jyxxcx(allSellerNames4jyxxcxBuf.toString());
			
			// 获得买方信息
			List buyer_list = dao.getMfgrxxBuyerDAO().queryMfgrxxBuyerList(conn, sqlBuff.toString());
			StringBuffer buyerBuf = new StringBuffer();
			StringBuffer allBuyerNames4jyxxcxBuf = new StringBuffer();
			for(int index =0;index < buyer_list.size();index ++){
				MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
				mfgrxxBuyerItem = (MfgrxxBuyer)buyer_list.get(index);
				if(index >0){
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
				
				//所有卖方姓名，多个用“/”隔开，给存量房交易信息查询用
				if(index == 0){
					allBuyerNames4jyxxcxBuf.append(mfgrxxBuyerItem.getNsrmc());
				}else{
					allBuyerNames4jyxxcxBuf.append("/"+mfgrxxBuyerItem.getNsrmc());
					
				}
			}
			//System.out.println("买方信息：：：："+buyerBuf.toString());
			resBo.setAll_buyerInfo(buyerBuf.toString());
			resBo.setBuyerList(buyer_list);
			resBo.setAllBuyerNames4jyxxcx(allBuyerNames4jyxxcxBuf.toString());
			
			//是否含有税务人员核定信息
			if(dao.getFwhdxxbDAO().hasExists(fwxx.sbbh, conn)){
				resBo.setHasHdxx("Y");
				//System.out.println("######@@@@@@@@@@@@@@@@@@@@@@@@@有核定信息");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "存量房信息查询－ClfxxcjProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	

	
	/**
	 * 修改
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfxxcjBo updateCjxx(VOPackage vo)throws BaseException{
		Connection conn = null;
		int oklevel = 0;

		// 获得前台传递过来的数据
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		HashMap zjMap = data.getZjMap();
		UserData ud = vo.getUserData();

		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			String sbbh = SecurityUtil.dealwithStringPara(data.sbbh);
			String htbh = SecurityUtil.dealwithStringPara(data.htbh);

			//如果无法获取实例
			if(dao == null){
				throw new ApplicationException("删除采集信息出错，无法创建DAOFactory实例!");
				
			}
			
			//合同编号为空
			if (htbh == null || "".equals(htbh)) {
				throw new ApplicationException("修改出错，合同编号为空!");
			}
			
			//申报编号为空
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("修改出错，申报编号为空!");
			}			
			
			//修改前获取原始信息
			List fwxxList = dao.getFwxxDAO().queryFwList(conn, "where sbbh ='"+sbbh+"'");
			if(fwxxList != null && fwxxList.size()!=0){
				if(fwxxList.size() == 1){
					//如果用申报编号只查询到一条记录，再用输入合同编号进行查询
					fwxxList = dao.getFwxxDAO().queryFwList(conn, "where htbh ='"+htbh+"'");
					//只有一条
					if(fwxxList != null && fwxxList.size() ==1){
						Fwxx fwxx = new Fwxx();
						fwxx = (Fwxx)fwxxList.get(0);
						
						//获得记录的申报编号
						String tempSbbh = fwxx.getSbbh();
						
						//如果tempSbbh 和  data.sbbh 不一致，则报错
						if(tempSbbh != null && !"".equals(tempSbbh) && !tempSbbh.equals(sbbh)){
							throw new ApplicationException("修改失败，该合同编号在系统中已经存在，请检查!");
						}
					}else if(fwxxList.size() > 1){
						throw new ApplicationException("修改前获取原始采集信息出错，合同编号重复,重复合同编号为："+data.htbh+"!");
					}
				}else{
					throw new ApplicationException("修改前获取原始采集信息出错，申报编号重复，重复申报编号为:"+data.sbbh+"!");
				}
			}else{
				throw new ApplicationException("修改采集信息出错，无法获得原始采集信息!");
				
			}
			
			//查看是否有核定信息，如果有则提示不允许修改
			if(dao.getFwhdxxbDAO().hasExists(sbbh, conn)){
				throw new ApplicationException("已经存在核定信息，不能进行修改!");
			}
			
			

			//@@1.获得并构造房屋信息
			oklevel = 1;
			Fwxx fwxx = createFwxx(data, ud, sbbh);

			//@@2.构造卖方信息
			oklevel = 2;
			List sellerList = createSellersInfo(data, zjMap, ud, sbbh);

			//@@3.构造买方信息
			oklevel = 3;
			List buyyerList = createBuyersInfo(data, zjMap, ud, sbbh);
			
			//@@4.更新前把修改信息保存到历史表
			oklevel = 4;
			saveCjxx2his(sbbh, "", dao, conn);
			
			//@@5.更新房屋信息表
			oklevel = 5;
			dao.getFwxxDAO().update(fwxx, conn);
			
			//@@6.删除买方信息
			oklevel = 6;
			dao.getMfgrxxBuyerDAO().delBuyyerInfo(sbbh, conn);
			//@@7.删除卖方信息
			oklevel = 7;
			dao.getMfgrxxSellerDAO().delSellerInfo(sbbh, conn);
			
			
			//@@8.写入卖方信息表
			oklevel = 8;
			dao.getMfgrxxSellerDAO().InsertMfgrxxSellerList(conn, sellerList);
			// @@9写入买方信息表
			oklevel = 9;
			dao.getMfgrxxBuyerDAO().InsertMfgrxxBuyerList(conn, buyyerList);
			
			//设置申报编号，查询用
			data.setSbbh(sbbh);
			vo.setData(data);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
				case 0:
					throw new ApplicationException(ex.getMessage());	
				case 1:
					throw new ApplicationException("修改采集信息出错，无法构造房屋信息!");					
				case 2:
					throw new ApplicationException("修改采集信息出错，无法构造卖方信息!");
				case 3:
					throw new ApplicationException("修改采集信息出错，无法构造买方信息");
				case 4:
					throw new ApplicationException("修改采集信息出错，无法保存修改信息到历史表!");					
				case 5:
					throw new ApplicationException("修改采集信息出错，无法修改房屋信息!");
				case 6:
					throw new ApplicationException("修改采集信息出错，无法修改买方信息!");
				case 7:
					throw new ApplicationException("修改采集信息出错，无法修改卖方信息!");					
				case 8:
					throw new ApplicationException("修改采集信息出错，无法修改卖方信息!");
				case 9:
					throw new ApplicationException("修改采集信息出错，无法修改买方信息!");	
				default:
					throw new ApplicationException(ex.getMessage());
						
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),
						"存量房信息修改－ClfxxcjProcessor，操作出错", new StackMsg2StringUtil(
								e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");
				throw ExceptionUtil.getBaseException(e);
			}

		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		//获取返回显示信息
		ClfxxcjBo res = getCjxx(vo);
		res.setSaveIsSuccess("1");//保存成功标志 0--失败  1--成功
		return res;
	}
	
	
	/**
	 * 删除采集信息
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public void deleteCjxx(VOPackage vo)throws BaseException{
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		
		if ((sbbh == null || "".equals(sbbh)) && (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("删除前获取采集出错，无查询条件!");
		}
		
		Connection conn = null;
		int oklevel = 0;
		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			//如果无法获取实例
			if(dao == null){
				throw new ApplicationException("删除采集信息出错，无法创建DAOFactory实例!");
				
			}
			
			//查看是否有核定信息，如果有则提示不允许删除
			if(dao.getFwhdxxbDAO().hasExists(sbbh, conn)){
				throw new ApplicationException("已经存在核定信息，不能进行删除!");
			}
			
			
			//保存信息到历史表
			saveCjxx2his(sbbh,vo.getUserData().getYhid(), dao, conn);
			
			//执行删除
			//@@1.删除买方信息
			oklevel = 1;
			dao.getMfgrxxBuyerDAO().delBuyyerInfo(sbbh, conn);
			
			//@@2.删除卖方信息
			oklevel = 2;
			dao.getMfgrxxSellerDAO().delSellerInfo(sbbh, conn);
			
			//@@3.删除房屋信息
			oklevel = 3;
			dao.getFwxxDAO().delFwxx(sbbh, conn);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
					case 0:
						throw new ApplicationException(ex.getMessage());				
					case 1:
						throw new ApplicationException("删除买方信息出错!");
					case 2:
						throw new ApplicationException("删除卖方信息出错!");
					case 3:
						throw new ApplicationException("删除房屋信息出错!");						
				}
			} catch (ApplicationException e) {
				
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(), "存量房信息删除－ClfxxcjProcessor，操作出错",
						new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
				.getStackMsg(), "失败");
				throw ExceptionUtil.getBaseException(e);
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}			
	}
	
	/**
	 * 把采集信息存放到历史表（包括房屋信息和买卖房信息）
	 * @param sbbh
	 * @param htbh
	 * @param conn
	 * @param scrydm 删除人员代码
	 * @return
	 */
	private boolean saveCjxx2his(String sbbh,String scrydm,DAOFactory dao, Connection conn)throws BaseException{
		boolean isSuccess = false;
		int oklevel = 0;
		try {
			//防止SQL注入
			sbbh = SecurityUtil.dealwithStringPara(sbbh);
			scrydm = SecurityUtil.dealwithStringPara(scrydm);
		
		//@@1.保存买方信息到历史表
			dao.getMfgrxxBuyerDAO().saveBuyyerInfo2his(sbbh, scrydm, conn);
		
		//@@2.保存卖方信息到历史表
			oklevel = 1;
			dao.getMfgrxxSellerDAO().saveSellerInfo2his(sbbh, scrydm, conn);
		
		//@@3.保存房屋信息到历史表
			oklevel = 2;
			dao.getFwxxDAO().saveFwxx2his(sbbh, scrydm, conn);
			
			isSuccess = true;	
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				switch (oklevel) {	
					case 0:
						throw new ApplicationException("保存买方信息到历史表出错!");				
					case 1:
						throw new ApplicationException("保存卖方信息到历史表出错!");
					case 2:
						throw new ApplicationException("保存房屋信息到历史表出错!");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				throw ExceptionUtil.getBaseException(e);
			}
		}
		return isSuccess;
	}
	
	

	/*
	 * public BigDecimal string2BigDecimal4MJ(String StrMj) {
	 * 
	 * if (StrMj == null || "".equals(StrMj)) { StrMj = "0.000";
	 * 
	 * } DecimalFormat deFormat = new DecimalFormat("#0.000");// 合计金额格式
	 * 
	 * BigDecimal zje = new BigDecimal(deFormat.format(StrMj));// 获得增加前的合计金额信息
	 * 
	 * return zje; }
	 */

	private String getSbbh(UserData ud, Connection conn) throws Exception {
		if (ud == null || conn == null) {
			throw new ApplicationException("获取申报编号出错!");
		}

		// 获得计算税务机关的计算机代码
		String swjgzzjgdm = SecurityUtil.dealwithStringPara(ud.getSsdwdm());
		String swjgJsjdm = null;

		PreparedStatement ps = null;
		CallableStatement proc = null;

		String sbbh = null;

		try {
			// 获取计算机代码
			String sql = "select jsjdm from DMDB.GY_DM_SWJGZZJG where (zxbs is null or zxbs ='0') and SWJGZZJGDM ='"
					+ swjgzzjgdm + "'";
			Debug.out("查询单位计算机代码SQL:" + sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				swjgJsjdm = rs.getString("jsjdm");
			}
			rs = null;

			// 如果查询不到计算机代码
			if (swjgJsjdm == null) {
				throw new ApplicationException("获取单位计算机代码出错，该单位未登记或者已经注销!");
			}

			/*
			 * //获取申报编号 proc =
			 * conn.prepareCall("{?=call jkdb.SB_PKG_TOOLS.getSBBH(?) }");
			 * proc.registerOutParameter(1, Types.VARCHAR); proc.setString(2,
			 * swjgJsjdm);
			 * 
			 * //执行 proc.execute();
			 * 
			 * //rs = proc.getResultSet();
			 * System.out.println("++++++++++++++++++++++++"+proc.getString(1));
			 */

			String sbbhSql = "select jkdb.SB_PKG_TOOLS.getSBBH('" + swjgJsjdm
					+ "') from dual";

			ps = conn.prepareStatement(sbbhSql);
			rs = ps.executeQuery();

			while (rs.next()) {
				sbbh = rs.getString(1);
				//System.out.println("++本次申报编号为+++" + sbbh);

			}

			// sbbh = proc.getString("");

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ApplicationException("获取申报编号出错!");
		} finally {
			// proc.close();
			ps.close();
		}

		return sbbh;

	}

}
