package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Clfjycs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxmxSeller;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxprintSeller;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxzbSeller;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qscxdyrz;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.MfskzsBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;

import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.code.*;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 卖方税款征收Processor
 * </p>
 * 
 * @author 张一军
 * @version 1.1
 */
public class MfskzsProcessor extends CommonProcessor {
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
//		case ActionType.INITSZSMLIST:
//			result = doInitList(vo); // 税种税目初始化
//			break;		
		case ActionType.QUERY:
			result = doQuery(vo); // 查询
			break;
		case ActionType.Query_SBXXHISBYZJHM:
			result = doQueryByZjhm(vo); // 依据证件号码查询
			break;
		case ActionType.Query_SBXXHISBYSBBH:
			result = doQueryBySbbh(vo); // 依据申报编号查询
			break;
		case ActionType.INSERT:
			result = doSaveMfsbxx(vo); // 保存卖方申报信息
			break;
		case ActionType.CLF_MFSBXXWH:
			result = doMfsbxxwh(vo); // 卖方申报信息维护
			break;
		case ActionType.Query_SBXXBYHTBH:
			result = doQueryByHtbh(vo); // 依据合同编号查询申报数据
			break;
		case ActionType.DataSynchronism:
			result = doDataSynchronism(vo); // 依据合同编号数据同步
			break;
		case ActionType.CREATE_WSZ:
			result = doSaveWsz(vo); // 生成完税证
			break;
		case ActionType.CREATE_JKS:
			result = doSaveJks(vo); // 生成缴款书
			break;
		case ActionType.DELETE:
			result = doDelete(vo); // 删除卖方申报信息
			break;			
		case ActionType.PRINT_WSZ:
			result = doPrintWsz(vo); // 打印完税证
			break;
		case ActionType.PRINT_JKS:
			result = doPrintJks(vo); // 打印缴款书
			break;	
		case ActionType.QUERY_WSZ:
			result=doQueryWsz(vo);//查询完税证
			break;
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}

		return result;
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
	 * 获得历史申报信息
	 * 
	 * @methodName:doQuery
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doQuery(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		String tfrq = data.getTfrq();
		String zsjg = data.getZsjg();
		MfskzsBo resBo = new MfskzsBo();

		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer csSqlBuff = new StringBuffer();
		csSqlBuff.append(" WHERE ZXBS = '0' AND CSLX = '01'");
		
		if (htbh == null || "".equals(htbh)) {
			throw new ApplicationException("查询条件出错，合同编号为空!");
		} else {
			sqlBuff.append(" where htbh ='" + htbh + "'");
		}
		
		Connection conn = null;
		try {
			
            // 增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
                    "QSDB", "QS_JL_FWXXB");
//            Debug.out("datafilter: " + datafilter);
            sqlBuff.append(" and "+ datafilter);
			
			conn = QSDBUtil.getConnection();
			
			//获得存量房交易参数表
			List csList = DAOFactory.getInstance().getClfjycsDAO().queryCsList(conn, csSqlBuff.toString());
			for(int index = 0; index < csList.size(); index++) {
				Clfjycs cs = (Clfjycs) csList.get(index);
				resBo.setRqcs(cs.dyz);
			}
			
			// 获得房屋信息
			List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, sqlBuff.toString());
			for (int index = 0; index < fwxxList.size(); index++) {
				Fwxx fwxx = (Fwxx) fwxxList.get(index);
				resBo.setSbbh(fwxx.sbbh);
				//是否含有税务人员核定信息
				if(DAOFactory.getInstance().getFwhdxxbDAO().hasExists(SecurityUtil.dealwithStringPara(fwxx.sbbh), conn))
				{
					resBo.setHasHdxx("Y");
				}
				
				resBo.setHtwsqyrq(DataConvert.TimeStamp2String(fwxx.htwsqyrq));
			}

			// 获得卖方信息
			List seller_List = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSellerList(conn, sqlBuff.toString());
			StringBuffer sellerNameBuf = new StringBuffer();
			StringBuffer sellerZjhmBuf = new StringBuffer();
			for(int index =0; index < seller_List.size();index ++){
				MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
				mfgrxxSellerItem = (MfgrxxSeller)seller_List.get(index);
				if(index == 0)
				{
					resBo.setCxzjhm(mfgrxxSellerItem.getZjhm());
					resBo.setZjhm(mfgrxxSellerItem.getZjlxmc()+"  "+mfgrxxSellerItem.getZjhm());
					sellerZjhmBuf.append("and a.sfzjhm in ('"+mfgrxxSellerItem.getZjhm()+"'");
				}
					
				if(index >0){
					sellerNameBuf.append("  ");
					sellerZjhmBuf.append(",'"+mfgrxxSellerItem.getZjhm()+"'");
				}
				if(index==seller_List.size()-1)
				{
					sellerZjhmBuf.append(")");	
				}
				sellerNameBuf.append(mfgrxxSellerItem.getNsrmc());				
			}
			resBo.setNsrmc(sellerNameBuf.toString());		
			resBo.setTfrq(tfrq);
			resBo.setZsjg(zsjg);
			resBo.setHtbh(htbh);

			// 获得合同中所有证件号码对应的契税缴纳税款历史信息
			ArrayList sbxxHisList = new ArrayList();
			if(!sellerZjhmBuf.toString().equals(""))
			{
				sbxxHisList=DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSbxxHisList(conn, sellerZjhmBuf.toString());
			}
			resBo.setSbxxHisList(sbxxHisList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 获得历史申报信息
	 * 
	 * @methodName:doQueryDh
	 * @function:
	 * 
	 * @param cxzjhm
	 *            查询证件号码
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doQueryByZjhm(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String cxzjhm = SecurityUtil.dealwithStringPara(data.getCxzjhm());
		String htbh = data.getHtbh();
		String sbbh = data.getSbbh();
		String tfrq = data.getTfrq();
		String zsjg = data.getZsjg();
		String nsrmc = data.getNsrmc();
		String zjhm = data.getZjhm();		
		String htwsqyrq = data.getHtwsqyrq();
		String rqcs = data.getRqcs();
		
		//new add yugw
		String cxnsrmc=SecurityUtil.dealwithStringPara(data.getNsrmc_his());
		String tdfwzldz=SecurityUtil.dealwithStringPara(data.getTdfwzldz_his());
		
		MfskzsBo resBo = new MfskzsBo();

		StringBuffer sellerZjhmBuf = new StringBuffer();
		if (cxzjhm == null || "".equals(cxzjhm)) {
			sellerZjhmBuf.append("");
			//throw new ApplicationException("查询条件出错，证件号码为空!");
		} else {
			sellerZjhmBuf.append("and a.sfzjhm ='" + cxzjhm + "'");
		}
		//new add  yugw
		if(cxnsrmc ==null||"".equals(cxnsrmc)){
			sellerZjhmBuf.append("");
		}else{
			sellerZjhmBuf.append("and a.nsrmc='" + cxnsrmc + "'");
		}if(tdfwzldz==null||"".equals(tdfwzldz)){
			sellerZjhmBuf.append("");
		}else{
			sellerZjhmBuf.append("and c.tdfwzldz like '"+'%'+tdfwzldz+'%' + "'");
		}
		//System.out.println("dh................\n"+sellerZjhmBuf.toString());

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();

			// 获得合同中所有证件号码对应的契税缴纳税款历史信息
			ArrayList sbxxHisList = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSbxxHisList(conn, sellerZjhmBuf.toString());
			resBo.setSbxxHisList(sbxxHisList);//契税缴纳税款历史信息
			resBo.setCxzjhm(cxzjhm);//查询卖方证件号码
			resBo.setTfrq(tfrq);//填发日期
			resBo.setZsjg(zsjg);//征收机关
			resBo.setHtbh(htbh);
			resBo.setSbbh(sbbh);
			resBo.setNsrmc(nsrmc);
			resBo.setZjhm(zjhm);			
			resBo.setHtwsqyrq(htwsqyrq);//买卖双方合同签订日期
			resBo.setRqcs(rqcs);//日期参数
			//new add by yugw
			resBo.setTdfwzldz_his(tdfwzldz);
			resBo.setNsrmc_his(cxnsrmc);
            //	resBo.setScriptStr(this.getSzsmInfo());
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 获得历史申报信息
	 * 
	 * @methodName:doQueryBySbbh
	 * @function:
	 * 
	 * @param sbbh
	 *            申报编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doQueryBySbbh(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
//		UserData userData=vo.getUserData();	
		String sbbh_his = data.getSbbh_his();
		String tfrq = data.getTfrq();
		String zsjg = data.getZsjg();
		String htbh = data.getHtbh();
		String nsrmc = data.getNsrmc();
		String zjhm = data.getZjhm();
		String sbbh = data.getSbbh();
		String yhid = data.getYhid();
		String yhxm = data.getYhxm();
		String htbh1 = data.getHtbh1();		
		String htwsqyrq = data.getHtwsqyrq();
		String rqcs = data.getRqcs();
		MfskzsBo resBo = new MfskzsBo();
		resBo.setTfrq(tfrq);
		resBo.setZsjg(zsjg);
		resBo.setHtbh(htbh);
		resBo.setNsrmc(nsrmc);
		resBo.setZjhm(zjhm);
		resBo.setSbbh(sbbh);
		resBo.setSbbh_his(sbbh_his);
		resBo.setHtbh1(htbh1);
		resBo.setHtwsqyrq(htwsqyrq);
		resBo.setRqcs(rqcs);
		resBo.setScriptStr(this.getSzsmInfo(sbbh,sbbh_his));
		
		//System.out.println(">>> Htbh："+data.getHtbh()+"  Sbbh："+data.getSbbh()+"  Sbbh_his："+data.getSbbh_his()+"  Yhid："+data.getYhid()+"  Yhxm："+data.getYhxm());
		
		Qscxdyrz qscxdyrzVo = new Qscxdyrz();
		qscxdyrzVo.htbh = htbh;
		qscxdyrzVo.sbbh_clf = SecurityUtil.dealwithStringPara(sbbh);
		qscxdyrzVo.sbbh_qs = SecurityUtil.dealwithStringPara(sbbh_his);
		qscxdyrzVo.cjr = yhid + ":" +yhxm;

		StringBuffer sellerZjhmBuf = new StringBuffer();
		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			
			//是否含有税务人员核定信息
			if(DAOFactory.getInstance().getFwhdxxbDAO().hasExists(sbbh, conn))
			{
				resBo.setHasHdxx("Y");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Debug.printException(e);
			throw ExceptionUtil.getBaseException(e);
		}
		
		if (sbbh_his == null || "".equals(sbbh_his)) {
			System.out.println("======无申报数据========");
		} else {
			sellerZjhmBuf.append("and a.sbbh ='" + sbbh_his + "'");

			
			try {
				conn = QSDBUtil.getConnection();
				
				//得到当前时间
				Timestamp now = CommonUtil.getDBtime(conn);
				qscxdyrzVo.cjrq = now;

				// 获得合同中所有证件号码对应的契税缴纳税款历史信息
				ArrayList sbxxHisList = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSbxxHisList(conn, sellerZjhmBuf.toString());
				resBo.setSbxxHisList(sbxxHisList);
				
				//插入打印日志
				DAOFactory.getInstance().getQscxdyrzDAO().insert(qscxdyrzVo, conn);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
						new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
								.getStackMsg(), "失败");
				throw ExceptionUtil.getBaseException(e);
			} finally {
				QSDBUtil.freeConnection(conn);
			}			
		}

		return resBo;

	}
	
	/**
	 * 初始化税种税目数据
	 * 
	 * @methodName:doInitList
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
//	public MfskzsBo doInitList(VOPackage vo) throws BaseException {
////		MfskzsBo data = (MfskzsBo) vo.getData();
////		UserData userData=vo.getUserData();
//		MfskzsBo resBo = new MfskzsBo();
//		resBo.setScriptStr(this.getSzsmInfo());
//		return resBo;
//
//	}
	
	public String getSzsmInfo(String sbbh,String sbbh_his) 
	{
		Connection conn = null;
		StringBuffer returnJsStr = new StringBuffer();
		String tempJsStr="var szsmlist_add_fields = [\"szsmdm\",\"fjsszsmdm\"];";
		String szsmdm_hd_name = "var szsmdm_hd_name = ['sbbh','htbh','yyszsfs','grsdszsfs','tdzzszsfs','hdjsje','qdfcqsje','qdfcyhsje','hlfy','ygffpje','tdzzssbfs','qdtdsyqzfje','jfpgjg','jgpgfy','anjjse','cjssl','htzj','fwhdlxdm','yhszsfs'];\n";
		StringBuffer  szsmdm_hd_value = new StringBuffer();  	
		try {
			conn = QSDBUtil.getConnection();
			
			//查询sbbh对应存量房核定数据
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" where sbbh ='"+SecurityUtil.dealwithStringPara(sbbh)+"'");
			ArrayList list = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, sqlBuff.toString());
			String htbh="";
			String yyszsfs="";
			String grsdszsfs="";
			String tdzzszsfs="";
			String hdjsje="";
			String qdfcqsje="";
			String qdfcyhsje="";				
			String hlfy="";
			String ygffpje="";	
			String tdzzssbfs="";
			String qdtdsyqzfje="";
			String jfpgjg="";
			String jgpgfy="";
			String anjjse="";
			String cjssl="";
			String htzj="";
			String fwhdlxdm="";
			String yhszsfs="";
			
			szsmdm_hd_value.append("var szsmdm_hd_value = [");
			for(int index =0; index < list.size();index ++){
				Fwhdxx fwdhxx = new Fwhdxx();
				fwdhxx = (Fwhdxx)list.get(index);
				
				htbh=fwdhxx.getHtbh();
				yyszsfs=fwdhxx.getYyszsfs();
				grsdszsfs=fwdhxx.getGrsdszsfs();
				tdzzszsfs=fwdhxx.getTdzsszsfs();
				hdjsje=DataConvert.BigDecimal2String(fwdhxx.getJsje(),2,false);
				qdfcqsje=DataConvert.BigDecimal2String(fwdhxx.getQdfcqsje(),2,false);
				qdfcyhsje=DataConvert.BigDecimal2String(fwdhxx.getQdfcyhsje(),2,false);				
				hlfy=DataConvert.BigDecimal2String(fwdhxx.getHlfy(),2,false);
				ygffpje=DataConvert.BigDecimal2String(fwdhxx.getYgffpje(),2,false);	
				tdzzssbfs=fwdhxx.getTdzzssbfs();
				qdtdsyqzfje=DataConvert.BigDecimal2String(fwdhxx.getQdtdsyqzfje(),2,false);	
				jfpgjg=DataConvert.BigDecimal2String(fwdhxx.getJfpgjg(),2,false);	
				jgpgfy=DataConvert.BigDecimal2String(fwdhxx.getJgpgfy(),2,false);	
				anjjse=DataConvert.BigDecimal2String(fwdhxx.getAnjjse(),2,false);
				cjssl=DataConvert.BigDecimal2String(fwdhxx.getCjssl(),2,false);
				//added by zhangj
				ClfswjghdxxlrBo cjxx=getCjxx(fwdhxx.getHtbh(),fwdhxx.getSbbh());
				htzj=cjxx.getHtzj();	
				fwhdlxdm=fwdhxx.getFwhdlxdm();
				yhszsfs=fwdhxx.getYhszsfs();
				
				szsmdm_hd_value.append("[");
            	szsmdm_hd_value.append("'"+sbbh+"',");
            	szsmdm_hd_value.append("'"+htbh+"',");
            	
            	if(yyszsfs == null || "null".equals(yyszsfs) || "".equals(yyszsfs)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+yyszsfs+"',");
            	}
            	if(grsdszsfs == null || "null".equals(grsdszsfs) || "".equals(grsdszsfs)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+grsdszsfs+"',");
            	}
            	if(tdzzszsfs == null || "null".equals(tdzzszsfs) || "".equals(tdzzszsfs)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+tdzzszsfs+"',");
            	}
            	if(hdjsje == null || "null".equals(hdjsje) || "".equals(hdjsje)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+hdjsje+"',");
            	}
            	if(qdfcqsje == null || "null".equals(qdfcqsje) || "".equals(qdfcqsje)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+qdfcqsje+"',");
            	}
            	if(qdfcyhsje == null || "null".equals(qdfcyhsje) || "".equals(qdfcyhsje)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+qdfcyhsje+"',");
            	}
            	if(hlfy == null || "null".equals(hlfy) || "".equals(hlfy)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+hlfy+"',");
            	}
            	if(ygffpje == null || "null".equals(ygffpje) || "".equals(ygffpje)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+ygffpje+"',");
            	}
            	if(tdzzssbfs == null || "null".equals(tdzzssbfs) || "".equals(tdzzssbfs)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+tdzzssbfs+"',");
            	}
            	if(qdtdsyqzfje == null || "null".equals(qdtdsyqzfje) || "".equals(qdtdsyqzfje)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+qdtdsyqzfje+"',");
            	}
            	if(jfpgjg == null || "null".equals(jfpgjg) || "".equals(jfpgjg)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+jfpgjg+"',");
            	}
            	if(jgpgfy == null || "null".equals(jgpgfy) || "".equals(jgpgfy)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+jgpgfy+"',");
            	}
            	if(anjjse == null || "null".equals(anjjse) || "".equals(anjjse)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+anjjse+"',");
            	}
            	if(cjssl == null || "null".equals(cjssl) || "".equals(cjssl)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+cjssl+"',");
            	}
            	if(htzj == null || "null".equals(htzj) || "".equals(htzj)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+htzj+"',");
            	}
            	if(fwhdlxdm == null || "null".equals(fwhdlxdm) || "".equals(fwhdlxdm)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+fwhdlxdm+"',");
            	}
            	if(yhszsfs == null || "null".equals(yhszsfs) || "".equals(yhszsfs)){
            		szsmdm_hd_value.append("'',");
            	}else{
            		szsmdm_hd_value.append("'"+yhszsfs+"',");
            	}

            	szsmdm_hd_value.append("],");
				
				//System.out.println("========营业税征收方式(0:免征营业税;1:全额征收营业税:2:差额征收营业税)=========="+yyszsfs);
				//System.out.println("========个人所得税征收方式(0:免征个人所得税;1:征收个人所得税)=========="+grsdszsfs);
				//System.out.println("========土地增值税征收方式(0:不征收土地增值税;1:免征土地增值税;2:全额征收土地增值税)=========="+tdzzszsfs);
				//System.out.println("========计税金额(即计税收入)=========="+hdjsje);
				//System.out.println("========取得房产契税金额(即取得房地产时所缴纳的契税金额)=========="+qdfcqsje);
				//System.out.println("========取得房产印花税金额(即取得房地产时所缴纳的印花税金额)=========="+qdfcyhsje);
				//System.out.println("========合理费用=========="+hlfy);
				//System.out.println("========原购房发票金额=========="+ygffpje);
				
			}			
			
			szsmdm_hd_value.append(" ];");         
            String repalceStr_old = ", ];";
            String repalceStr_new = "];";
            
            if(szsmdm_hd_value.toString().endsWith(repalceStr_old)){
            	int index = szsmdm_hd_value.lastIndexOf(repalceStr_old);
            	szsmdm_hd_value = szsmdm_hd_value.replace(index, szsmdm_hd_value.length(), repalceStr_new);
            }
            returnJsStr.append(szsmdm_hd_name);
            returnJsStr.append(szsmdm_hd_value);

			if (!(sbbh_his == null || "".equals(sbbh_his))){
				StringBuffer sellersbbh = new StringBuffer();
				sellersbbh.append("and a.sbbh ='" + sbbh_his + "'");

					conn = QSDBUtil.getConnection();

					// 获得合同中所有证件号码对应的契税缴纳税款历史信息
					ArrayList sbxxHisList = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSbxxHisList(conn, sellersbbh.toString());

		         	
					for(int index =0; index < sbxxHisList.size();index ++){
			         	MfskzsBo bo = new MfskzsBo();
			         	bo = (MfskzsBo)sbxxHisList.get(index);
			         	BigDecimal jsje_his=DataConvert.String2BigDecimal(bo.jsje_his);				
						//System.out.println("========卖方契税查询选定契税计税金额=========="+jsje_his);						
					}							
			}
			
			//
			String skssksrq="";
			String skssjrq="";

			Timestamp now = CommonUtil.getDBtime(conn);
			Map map = DateUtils.monthSkssrq(now);
			skssksrq=map.get("SKSSKSRQ").toString();
			skssjrq=map.get("SKSSJSRQ").toString();		
			StringBuffer grsdsszsm = new StringBuffer();

			//int r=ygffpje.compareTo(new String("0.00"));
			if(grsdszsfs.equals(Constants.GRSDSZSFS_FREE))
			{
				//如果核定个人所得税征收方式为0:免征个人所得税，则核定税种税目应排除个人所得税税目
				grsdsszsm.append(" and a.szsmdm not like '05%'");
			}
			/*else{
				//如果原购房发票金额等于零 并且 卖方契税税款没有查询到,则核定个税税目为核定征收
				if((ygffpje == "0" || ygffpje == "0.00" || "0".equals(ygffpje) || "0.00".equals(ygffpje))&&(sbbh_his == null || "".equals(sbbh_his)))
				{
					grsdsszsm.append(" and a.szsmdm != '050801'");					
				}
				else //如果原购房发票金额大于零 或者 卖方税款查询选定契税记录,则核定个税税目为据实征收
				{
					grsdsszsm.append(" and a.szsmdm != '050802'");
				}
				
			}*/
			
			if(tdzzszsfs.equals(Constants.TDZZSZSFS_NOT))
			{
				//如果【土地增值税征收方式】选择“不征收土地增值税”，则不需要计算土地增值税
				grsdsszsm.append(" and a.szsmdm not like '08%'");
			}
			//added by zhangj
//			if(Constants.FWHDLX_HOUSING.equals(fwhdlxdm))
//			{
//				//如果房屋核定类型为[住房]，则不需要计算印花税
//				grsdsszsm.append(" and a.szsmdm not like '16%'");
//			}
			
			// 获得可申报税目对应附加税
			tempJsStr += DAOFactory.getInstance().getSzsmInitDAO().queryFjs(conn);
			// 获得可申报税目name及value
			tempJsStr += DAOFactory.getInstance().getSzsmInitDAO().querySzsm(conn,grsdsszsm.toString());
			
			tempJsStr +="\n var szsmdm_name_del = new Array(); \n var szsmdm_value_del = new Array();";
			// 获得可申报税目页面显示数据
			tempJsStr += DAOFactory.getInstance().getSzsmInitDAO().querySzsmlist(conn,skssksrq,skssjrq,grsdsszsm.toString());			
			
			tempJsStr+="\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\",\"jmje\",\"yjje\"];";
			//System.out.println("======tempJsStr====="+tempJsStr);
			tempJsStr += "\n"+returnJsStr.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return tempJsStr;

	}
	
	/**
	 * 保存卖方申报信息
	 * 
	 * @methodName:doSaveMfsbxx
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doSaveMfsbxx(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		UserData ud= vo.getUserData();
		String htbh = data.getHtbh();
		String sbbh = data.getSbbh();
		String sbbh_his = data.getSbbh_his();		
		String jwcxfwyz = data.getFwyz();
		String tdzzssl = data.getTdzzssl();
		int oklevel = 0;
		
		MfskzsBo resBo = new MfskzsBo();

		StringBuffer sqlBuff = new StringBuffer();
		MfsbxxzbSeller msvo=new MfsbxxzbSeller();
		//保存成功后将数据存入sbxxHisList
		ArrayList sbxxHisList = new ArrayList();
		
		msvo.sbbh=sbbh;
		msvo.qs_sbbh=sbbh_his;
		msvo.htbh=htbh;
		
		sqlBuff.append(" where sbbh='"+SecurityUtil.dealwithStringPara(sbbh)+"'");

		Connection conn = null;
		try {
			
			conn = QSDBUtil.getConnection();
			
			//查询sbbh是否已经有卖方申报数据
			List mfsbxx_List = DAOFactory.getInstance().getMfsbxxzbSellerDAO().queryMfsbxxzbAllList(conn, sqlBuff.toString());
			
			if(mfsbxx_List.size()==0)
			{
				
				//获取城建税税率
				String cjssl="";
				ArrayList fwhdxx_List = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, sqlBuff.toString());
				for(int index =0; index < fwhdxx_List.size();index ++)
				{
					Fwhdxx fwdhxx = new Fwhdxx();
					fwdhxx = (Fwhdxx)fwhdxx_List.get(index);
					
					cjssl=DataConvert.BigDecimal2String(fwdhxx.getCjssl(),2,false);
				}	

				// 构造卖方申报主表数据
				List seller_List = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSellerList(conn, sqlBuff.toString());
				StringBuffer sellerNameBuf = new StringBuffer();
				for(int index =0; index < seller_List.size();index ++){
					MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
					mfgrxxSellerItem = (MfgrxxSeller)seller_List.get(index);
					if(index == 0)
					{
						msvo.zjlxdm=mfgrxxSellerItem.getZjlxdm();
						msvo.zjlxmc=mfgrxxSellerItem.getZjlxmc();
						msvo.zjhm=mfgrxxSellerItem.getZjhm();
						sellerNameBuf.append(mfgrxxSellerItem.getNsrmc());
					}else
					{
						sellerNameBuf.append("  "+mfgrxxSellerItem.getNsrmc());					
					}		
				}
				msvo.nsrmc = sellerNameBuf.toString();
				
				String jsjdm=CommonUtil.getWszJsjdm(vo.getUserData(), conn);//操作用户所属代码jsjdm
				msvo.jsjdm=jsjdm;
				msvo.djzclxdm=Constants.WSZ_DJZCLX;//登记注册类型--41代表其他服务业
				msvo.gjbzhydm=Constants.WSZ_GJBZHYDM;//国家标准行业名称--8190代表个人
				
	            //得到纳税人的登记基本数据
	            SWDJJBSJ jbsj = null;
	            try {
	                jbsj = CommonUtil.getFgrJBSJ(jsjdm);
	            } catch (Exception ex1) {
	                Debug.out(ex1);
	                throw ExceptionUtil.getBaseException(ex1, "获取法人的登记信息出错！");
	            }
	            if (jbsj == null) {
	                throw new ApplicationException("获取法人登记信息出错！");
	            }
	            msvo.lsgxdm=jbsj.getLsgxdm();
	            msvo.swjgzzjgdm=ud.ssdwdm;
	            
	            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	            //限缴日期--取下月15日
	            msvo.skxjrq=DataConvert.String2Timestamp(DataConvert.TimeStamp2String(Timestamp.valueOf(DateUtil.addDatetimeByMonth(nowTime.toString(), 1))).substring(0,6)+"15");            
	            msvo.sjhjje=string2BigDecimal(data.getHjyjje());
	            msvo.ynhjje=string2BigDecimal(data.getHjsjse());
	            msvo.jmhjje=string2BigDecimal(data.getHjjmje());
	            msvo.cjr=ud.yhid+":"+ud.yhmc;
	            msvo.lrr=ud.yhid+":"+ud.yhmc;
	            
	            //构造卖方申报明细表数据
	        	//String[] columns ={"szsmdm", "szsmmc", "kssl", "jsje", "sjse","skssksrq", "skssjsrq", "szdm", "szmc", "sl"};
	        	ArrayList mfsbxxmxList = data.getDataList();
	        	ArrayList list = new ArrayList();
	            for(int i=0;i<mfsbxxmxList.size();i++)
	            {
	            		Map map = new HashMap();
	                	MfsbxxmxSeller msmxvo=new MfsbxxmxSeller();
	                	
	            		map=(Map)mfsbxxmxList.get(i);
	            		
	            		msmxvo.sbbh=sbbh;            		
	                	msmxvo.szsmdm=map.get("szsmdm").toString();
	                	if(map.get("kssl")!=null)
	                	{
	                    	msmxvo.kssl=DataConvert.String2BigDecimal(map.get("kssl").toString());                		
	                	}
	                	if(map.get("jsje")!=null)
	                	{                	
	                		msmxvo.jsje=DataConvert.String2BigDecimal(map.get("jsje").toString());
	                	}
	                	if(map.get("jmje")!=null)
	                	{                	
	                		msmxvo.jmse=DataConvert.String2BigDecimal(map.get("jmje").toString());
	                	}
	                	if(map.get("yjje")!=null)
	                	{                	
	                		msmxvo.sjje=DataConvert.String2BigDecimal(map.get("yjje").toString());
	                	}
	                	if(map.get("sjse")!=null)
	                	{                	
	                		msmxvo.ynse=DataConvert.String2BigDecimal(map.get("sjse").toString());
	                	}
	                	if(map.get("sl")!=null)
	                	{
	                		if(map.get("szsmdm")!=null && map.get("szsmdm").toString().equals(Constants.CJS_SZSMDM))
		                	{
	                			msmxvo.sl=DataConvert.String2BigDecimal(cjssl);  
		                	}
	                		else if(map.get("szsmdm")!=null && map.get("szsmdm").toString().equals(Constants.TDZZS_SZSMDM))
	                		{
	                			msmxvo.sl=DataConvert.String2BigDecimal(tdzzssl);  
	                		}
	                		else
	                		{
	                			msmxvo.sl=DataConvert.String2BigDecimal(map.get("sl").toString());
	                		}
	                	}
	                	msmxvo.skssksrq=DataConvert.String2Timestamp(map.get("skssksrq").toString());
	                	msmxvo.skssjsrq=DataConvert.String2Timestamp(map.get("skssjsrq").toString());
	                	msmxvo.swjgzzjgdm=ud.ssdwdm;
	                	msmxvo.cjr=ud.yhid+":"+ud.yhmc;
	                	msmxvo.lrr=ud.yhid+":"+ud.yhmc;
	                	msmxvo.xh=String.valueOf(i+1);
	                	list.add(msmxvo);
	            }       
	            
				oklevel = 0;
				//0写入卖方申报信息主表
				DAOFactory.getInstance().getMfsbxxzbSellerDAO().insert(msvo, conn);
				//1写入卖方申报信息明细表
				DAOFactory.getInstance().getMfsbxxmxSellerDAO().InsertList(conn, list);
				//2建委查询房屋原值更新至核定信息表
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				if(jwcxfwyz == null || "".equals(jwcxfwyz))
				{
					jwcxfwyz = "0.00";
				}
				BigDecimal jwyz = new BigDecimal(jwcxfwyz);
				if(deFormat.format(jwyz).toString().equals("0.00"))
				{
					Fwhdxx fwhdvo = new Fwhdxx();
					fwhdvo.jwcxfwyz = jwyz;
					fwhdvo.sbbh=sbbh;
					DAOFactory.getInstance().getFwhdxxbDAO().updateFwyz(fwhdvo, conn);
				}
				
				
	        	MfsbxxzbSeller mfsbxxzbSeller = new MfsbxxzbSeller();

	        	mfsbxxzbSeller.setHtbh(htbh);
	        	mfsbxxzbSeller.setSbbh(sbbh);
	        	mfsbxxzbSeller.setNsrmc(msvo.getNsrmc());
	        	mfsbxxzbSeller.setSjhjje(string2BigDecimal(data.getHjyjje()));
	        	
	        	sbxxHisList.add(mfsbxxzbSeller);
				resBo.setSbxxHisList(sbxxHisList);
				resBo.setZsjg(ud.ssdwmc);
				resBo.setPzzhdm(ud.getXtsbm1());
				resBo.setHasHdxx(data.getHasHdxx());
				resBo.setHtbh(data.getHtbh());
			}
			else
			{
				resBo.setTfrq(data.getTfrq());
				resBo.setZsjg(data.getZsjg());
				resBo.setHtbh(data.getHtbh());
				resBo.setSbbh(data.getSbbh());
				resBo.setSbbh_his(data.getSbbh_his());
				resBo.setScriptStr(this.getSzsmInfo(data.getSbbh(),data.getSbbh_his()));
			}

						
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
				case -1:
					throw new ApplicationException("合同编号已存在!");				
				case 0:
					throw new ApplicationException("插入卖方申报信息表主表出错!");
				case 1:
					throw new ApplicationException("插入卖方申报信息表明细表出错!");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// 处理失败信息:控制台 ＋ 日志
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),
						"契税申报征收－MfskzsProcessor，操作出错", new StackMsg2StringUtil(
								e, Constants.STACK_MSG_CAP).getStackMsg(), "失败");
				throw ExceptionUtil.getBaseException(e);
			}

		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	
	/**
	 * 查询指定合同编号或者登录用户权限范围内的所有不存在合同与凭证对照关系表的申报信息页面
	 * 
	 * @methodName:doMfsbxxwh
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doMfsbxxwh(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();

		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where ");
		if (htbh == null || "".equals(htbh)) {
			System.out.println("合同编号为空,只查询本单位未打印申报信息");
		} else {
			sqlBuff.append(" htbh ='" + htbh + "' and ");
		}

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			resBo.setZsjg(ud.ssdwmc);
			resBo.setHtbh(htbh);
			
			//1.获得房屋信息
			StringBuffer sql = new StringBuffer();
			sql.append(" where ");
			sql.append(" htbh ='" + htbh + "' and ");
			// 增加数据权限控制
            String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
            sql.append(fwxxfilter);
			
			List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, sql.toString());
			if(fwxxList == null || fwxxList.size() == 0)
			{
				resBo.setHasHdxx("O");
				return resBo;
			}
			for (int index = 0; index < fwxxList.size(); index++) 
			{
				Fwxx fwxx = (Fwxx) fwxxList.get(index);
				//2.是否含有税务人员核定信息
				if(DAOFactory.getInstance().getFwhdxxbDAO().hasExists(fwxx.sbbh, conn))
				{
					resBo.setHasHdxx("Y");
				}
				else
				{
					return resBo;
				}
			}
			
			//3.查询信息
			// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
			ArrayList sbxxHisList = new ArrayList();

			sbxxHisList=DAOFactory.getInstance().getMfsbxxzbSellerDAO().queryMfsbxxzbList(conn, sqlBuff.toString());

			resBo.setSbxxHisList(sbxxHisList);
			resBo.setPzzhdm(ud.getXtsbm1());
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}	

	
	/**
	 * 查询指定合同编号所有未打印的申报信息页面
	 * 
	 * @methodName:doQueryByHtbh
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doQueryByHtbh(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();

		StringBuffer sqlBuff = new StringBuffer();

		if (htbh == null || "".equals(htbh)) {
			throw new ApplicationException("查询条件出错，合同编号为空!");
		} else {
			sqlBuff.append(" where htbh ='" + htbh + "'");
		}

		
		Connection conn = null;
		try {
			resBo.setZsjg(ud.ssdwmc);
			resBo.setHtbh(htbh);
			conn = QSDBUtil.getConnection();	
			
			//1.获得房屋信息
			StringBuffer sql = new StringBuffer();
			sql.append(" where ");
			sql.append(" htbh ='" + htbh + "' and ");
			// 增加数据权限控制
            String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
            sql.append(fwxxfilter);
			
			List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, sql.toString());
			if(fwxxList == null || fwxxList.size() == 0)
			{
				resBo.setHasHdxx("O");
				return resBo;
			}
			for (int index = 0; index < fwxxList.size(); index++) 
			{
				Fwxx fwxx = (Fwxx) fwxxList.get(index);
				//2.是否含有税务人员核定信息
				if(DAOFactory.getInstance().getFwhdxxbDAO().hasExists(fwxx.sbbh, conn))
				{
					resBo.setHasHdxx("Y");
				}
				else
				{
					return resBo;
				}
			}
			
			// 3. 查询数据
			// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
			ArrayList sbxxHisList = new ArrayList();

			sbxxHisList=DAOFactory.getInstance().getMfsbxxzbSellerDAO().queryMfsbxxzbAllList(conn, sqlBuff.toString());

			resBo.setSbxxHisList(sbxxHisList);
			resBo.setHtbh(htbh);
			resBo.setPzzhdm(ud.getXtsbm1());
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 依据指定合同编号同步申报数据
	 * 
	 * @methodName:doDataSynchronism
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doDataSynchronism(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = data.getHtbh();
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();

		// 定义结果
		String bz = "";
		// 定义返回信息
		String message = "";
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = QSDBUtil.getConnection();
			resBo.setZsjg(ud.ssdwmc);

			proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.shuJuTongBuGengXinWSPZH(?,?,?,?) }");
			int k = 1;
			// 输入参数
			proc.setString(k++,htbh);
			proc.setString(k++,ud.yhid+":"+ud.yhmc);
			
			// 出口参数
			proc.registerOutParameter(k++, Types.VARCHAR);
			proc.registerOutParameter(k++, Types.VARCHAR);
			// 调用存储过程
			proc.execute();
			
			// 获取结果
			bz = proc.getString(3);
			message = proc.getString(4);
			

			resBo.setMessage(message);			
			resBo.setBz(bz);
			if(proc != null) 
			{
				try 
				{
					proc.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			resBo.setHtbh(htbh);
			resBo.setPzzhdm(ud.getXtsbm1());
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}	

	/**
	 * 生成完税证
	 * 
	 * @methodName:doPrintWsz
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doSaveWsz(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = data.getHtbh1();
		String sbbh = data.getSbbh();
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();
		//ADDED BY ZHANGJ START
		resBo.setHtbh(htbh);
		resBo.setSbbh(sbbh);
		//ADDED BY ZHANGJ END
		// 定义结果
		String bz = "";
		// 定义返回信息
		String message = "";
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = QSDBUtil.getConnection();		

			proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.shengChengWSZjks(?,?,?,?,?,?,?) }");
			int k = 1;
			// 输入参数
			proc.setString(k++,htbh);
			proc.setString(k++,sbbh);
			proc.setString(k++,ud.getXtsbm1());
			proc.setString(k++,"1");
			proc.setString(k++,ud.yhid+":"+ud.yhmc);
			
			// 出口参数
			proc.registerOutParameter(k++, Types.VARCHAR);
			proc.registerOutParameter(k++, Types.VARCHAR);
			// 调用存储过程
			proc.execute();
			
			// 获取结果
			bz = proc.getString(6);
			message = proc.getString(7);
			

			resBo.setMessage(message);
			resBo.setBz(bz);
			if(proc != null) 
			{
				try 
				{
					proc.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			//如果返回值为，代表成功，进入本次申报数据显示页面（可一次生成多笔完税证）
			if(bz.equals("1"))
			{
				//首先查询本次申报生成的对应完税证数据：计算机代码、完税证序号、年度子别、完税证号、金额

				// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
				StringBuffer sqlBuff = new StringBuffer();
				sqlBuff.append(" and a.htbh ='"+htbh+"' and a.sbbh ='"+sbbh+"'");
				ArrayList sbxxList = new ArrayList();

				sbxxList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryMfsbxxWszList(conn, sqlBuff.toString());	
				
				resBo.setSbxxList(sbxxList);
	        	resBo.setDybs("0");//设置打印标识为不打印
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	
	/**
	 * 生成缴款书
	 * 
	 * @methodName:doPrintJks
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doSaveJks(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = data.getHtbh1();
		String sbbh = data.getSbbh();
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();

		// 定义结果
		String bz = "";
		// 定义返回信息
		String message = "";
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = QSDBUtil.getConnection();		

			proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.shengChengWSZjks(?,?,?,?,?,?,?) }");
			int k = 1;
			// 输入参数
			proc.setString(k++,htbh);
			proc.setString(k++,sbbh);
			proc.setString(k++,ud.getXtsbm1());
			proc.setString(k++,"0");
			proc.setString(k++,ud.yhid+":"+ud.yhmc);
			
			// 出口参数
			proc.registerOutParameter(k++, Types.VARCHAR);
			proc.registerOutParameter(k++, Types.VARCHAR);
			// 调用存储过程
			proc.execute();
			
			// 获取结果
			bz = proc.getString(6);
			message = proc.getString(7);
			
			resBo.setMessage(message);			
			resBo.setBz(bz);
			if(proc != null) 
			{
				try 
				{
					proc.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			//如果返回值为，代表成功，进入本次申报数据显示页面（可一次生成多笔缴款书）
			if(bz.equals("1"))
			{
				//首先查询本次申报生成的对应缴款书数据：计算机代码、申报编号、入库金额合计

				// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
				StringBuffer sqlBuff = new StringBuffer();
				sqlBuff.append(" and a.htbh ='"+htbh+"' and a.sbbh ='"+sbbh+"'");
				ArrayList sbxxList = new ArrayList();

				sbxxList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryMfsbxxJksList(conn, sqlBuff.toString());	
				
				resBo.setSbxxList(sbxxList);
	        	resBo.setDybs("0");//设置打印标识为不打印
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 删除契税申报数据
	 * 
	 * @methodName:doDelete
	 * @function:
	 * 
	 * @param htbh
	 *            合同编号
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doDelete(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = data.getHtbh1();
		String sbbh = data.getSbbh();
		UserData ud =vo.getUserData();
		MfskzsBo resBo = new MfskzsBo();

		// 定义结果
		String bz = "";
		// 定义返回信息
		String message = "";
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = QSDBUtil.getConnection();		

			proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.invoke(?,?,?,?,?,?,?,?,?,?) }");
			int k = 1;
			// 输入参数
			proc.setString(k++,"5");
			proc.setString(k++,htbh);
			proc.setString(k++,sbbh);
			proc.setString(k++,ud.yhid+":"+ud.yhmc);
			proc.setString(k++,"1");
			proc.setString(k++,ud.getXtsbm1());
			proc.setString(k++,"");
			proc.setString(k++,"");
			
			// 出口参数
			proc.registerOutParameter(k++, Types.VARCHAR);
			proc.registerOutParameter(k++, Types.VARCHAR);
			// 调用存储过程
			proc.execute();
			
			// 获取结果
			bz = proc.getString(9);
			message = proc.getString(10);

			resBo.setMessage(message);			
			resBo.setBz(bz);
			
			if(proc != null) 
			{
				try 
				{
					proc.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			resBo.setHtbh(htbh);
			resBo.setPzzhdm(ud.getXtsbm1());
			
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 打印完税证
	 * 
	 * @methodName:doPrintWsz
	 * @function:
	 * 
	 * @param
	 *            
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doPrintWsz(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String wszxh = data.getWszxh();
		MfskzsBo resBo = new MfskzsBo();
		//ADDED BY ZHANGJ START
		resBo.setHtbh(data.getHtbh());
		resBo.setSbbh(data.getSbbh());
		//ADDED BY ZHANGJ END
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        
        String jsjdm= "";
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq = "";
	
		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			
	        //得到纳税人的登记基本数据
	        SWDJJBSJ jbsj = null;
	        try {
				jsjdm=CommonUtil.getWszJsjdm(vo.getUserData(), conn);//操作用户所属代码jsjdm
	            jbsj = CommonUtil.getFgrJBSJ(jsjdm);
	        } catch (Exception ex1) {
	            Debug.out(ex1);
	            throw ExceptionUtil.getBaseException(ex1, "获取法人的登记信息出错！");
	        }
	        if (jbsj == null) {
	            throw new ApplicationException("获取法人登记信息出错！");
	        }
			
			// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" where a.jsjdm ='"+jsjdm+"' and a.wszxh ='"+wszxh+"'");
			//查询完税证主表数据
			ArrayList wszzbList = new ArrayList();
			wszzbList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryWszzbList(conn, sqlBuff.toString());
			
			for (int index = 0; index < wszzbList.size(); index++) {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	mfsbxxprintSellerItem = (MfsbxxprintSeller)wszzbList.get(index);
            	resBo.setHeadWszh(mfsbxxprintSellerItem.getWszh());
            	resBo.setZclx("");//注册类型，暂时写死
            	resBo.setTfrq((String.valueOf(mfsbxxprintSellerItem.getCjrq())).substring(0, 10));
            	resBo.setJsjdm(mfsbxxprintSellerItem.getJsjdm());//单位的计算机代码
            	resBo.setNsrmc(mfsbxxprintSellerItem.getNsrmc());//纳税人姓名           	
                if (mfsbxxprintSellerItem.getDz()== null)
                {
                	resBo.setDz("");
                }
                else
                {
                	resBo.setDz(mfsbxxprintSellerItem.getDz());//地址
                }
            	resBo.setWszbz(mfsbxxprintSellerItem.getNsrmc());//备注
            	resBo.setHjje(deFormat.format(mfsbxxprintSellerItem.getHjsjje()));//合计金额
            	resBo.setHjjedx(Currency.convert(mfsbxxprintSellerItem.getHjsjje()));//合计金额大写
			}
	        resBo.setZsjg(jbsj.getSwjgzzjgmc()); //证收机关名称			

			//查询完税证明细表数据
			ArrayList wszmxList = new ArrayList();
			wszmxList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryWszmxList(conn, sqlBuff.toString());
			for (int index = 0; index < wszmxList.size(); index++) {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	mfsbxxprintSellerItem = (MfsbxxprintSeller)wszmxList.get(index);
            	
             	HashMap map = new HashMap();
             	
                if (mfsbxxprintSellerItem.getKssl()== null)
                {
                    map.put("kssl", " ");
                }
                else
                {
                    map.put("kssl", String.valueOf(mfsbxxprintSellerItem.getKssl()));
                }
                if (mfsbxxprintSellerItem.getJsje()== null)
                {
                    map.put("jsje", " ");
                }
                else
                {
                    map.put("jsje", deFormat.format(StringUtil.getDouble(String.valueOf(mfsbxxprintSellerItem.getJsje()), 0.00)));
                }
                if (mfsbxxprintSellerItem.getSl()== null)
                {
                    map.put("sl", " ");
                }
                else
                {
                    map.put("sl", String.valueOf(mfsbxxprintSellerItem.getSl()));
                }
                if (mfsbxxprintSellerItem.getYjhkc()== null)
                {
                    map.put("yjhkc", " ");
                }
                else
                {
                    map.put("yjhkc", deFormat.format(StringUtil.getDouble(String.valueOf(mfsbxxprintSellerItem.getYjhkc()), 0.00)));
                }
                if (mfsbxxprintSellerItem.getSjse()== null)
                {
                    map.put("sjse", " ");
                }
                else
                {
                    map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(mfsbxxprintSellerItem.getSjse()), 0.00)));
                }
                
                if (mfsbxxprintSellerItem.getSkssksrq() == null)
                {
                	map.put("skssksrq", " ");
                }
                else
                {
                	map.put("skssksrq",
                    		String.valueOf(mfsbxxprintSellerItem.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                }
                
                if (mfsbxxprintSellerItem.getSkssjsrq() == null)
                {
                	map.put("skssjsrq", " ");
                }
                else
                {
                	map.put("skssjsrq",
                    		String.valueOf(mfsbxxprintSellerItem.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                }
                
                map.put("szmc",CodeUtils.getCodeBeanLabel("DM_SZSM", mfsbxxprintSellerItem.getSzdm()));
                map.put("szsmmc",CodeUtils.getCodeBeanLabel("DM_SZSM", mfsbxxprintSellerItem.getSzsmdm()));

                
                mxSz += map.get("szmc") + ";;";
                mxPmmc += map.get("szsmmc") + ";;";
                //mxKssl += map.get("kssl") + ";;";
                mxKssl += " "+";;";
                mxJsje += map.get("jsje") + ";;";
                mxSl += map.get("sl") + ";;";
                mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                mxYjhkc += map.get("yjhkc") + ";;";
                mxSjse += map.get("sjse") + ";;";
                
            	resBo.setSkssksrq(String.valueOf(mfsbxxprintSellerItem.getSkssksrq()).substring(0,10));
            	resBo.setSkssjsrq(String.valueOf(mfsbxxprintSellerItem.getSkssjsrq()).substring(0,10));                       
			}
			
        	resBo.setMxSz(mxSz);
        	resBo.setMxPmmc(mxPmmc);
        	resBo.setMxKssl(mxKssl);
        	resBo.setMxJsje(mxJsje);
        	resBo.setMxSl(mxSl);
        	resBo.setMxSkssrq(mxSkssrq);
        	resBo.setMxYjhkc(mxYjhkc);
        	resBo.setMxSjse(mxSjse);
        	
        	resBo.setDybs("1");//设置打印标识为打印
        	
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	/**
	 * 打印缴款书
	 * 
	 * @methodName:doPrintWsz
	 * @function:
	 * 
	 * @param
	 *            
	 * @return
	 * @author:张一军
	 * @create date：
	 * @version 1.1
	 * 
	 * 
	 */
	public MfskzsBo doPrintJks(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String sbbh = data.getSbbh();
		MfskzsBo resBo = new MfskzsBo();

        DecimalFormat deFormat = new DecimalFormat("#0.00");
        
        String jsjdm= "";
        List dataList = new ArrayList();
        String tempstr;
        BigDecimal hjje = new BigDecimal("0.00");
        BigDecimal tmpdd;
		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			
	        //得到纳税人的登记基本数据
	        SWDJJBSJ jbsj = null;
	        try {
				jsjdm=CommonUtil.getWszJsjdm(vo.getUserData(), conn);//操作用户所属代码jsjdm
	            jbsj = CommonUtil.getFgrJBSJ(jsjdm);
	        } catch (Exception ex1) {
	            Debug.out(ex1);
	            throw ExceptionUtil.getBaseException(ex1, "获取法人的登记信息出错！");
	        }
	        if (jbsj == null) {
	            throw new ApplicationException("获取法人登记信息出错！");
	        }
			
			// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" WHERE mx.sbbh='"+sbbh+"' AND zb.sbbh='"+sbbh+"' AND zb.jsjdm='"+jsjdm+"' ");
			//查询缴款书数据
			ArrayList jksList = new ArrayList();
			jksList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryJksList(conn, sqlBuff.toString());

            Map preZB = null;
            Map curZB = null;
            int isz;
			
			for (int index = 0; index < jksList.size(); index++) {
				
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	mfsbxxprintSellerItem = (MfsbxxprintSeller)jksList.get(index);
            	
            	
            	resBo.setNsrmc(mfsbxxprintSellerItem.getBz().substring(0, mfsbxxprintSellerItem.getBz().indexOf(" #$# ")));//纳税人名称
            	resBo.setSbrq(new SimpleDateFormat("yyyyMMdd").format(mfsbxxprintSellerItem.getSbrq()));
            	resBo.setYhmc(mfsbxxprintSellerItem.getYhmc());
            	resBo.setZh(mfsbxxprintSellerItem.getYhzh());
            	
                // 征收机关名称（税务所的名称），需要根据代码查名称
            	resBo.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",mfsbxxprintSellerItem.getSwjgzzjgdm()));

                // 国库名称
            	resBo.setGkzzjgdm(mfsbxxprintSellerItem.getGkzzjgdm());
            	resBo.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG",mfsbxxprintSellerItem.getGkzzjgdm()));

                preZB = null;
                for(isz = 0;isz < dataList.size();isz++)
                {
                    preZB = (Map)dataList.get(isz);
                	
                    if (((String)preZB.get("szdm")).equals(mfsbxxprintSellerItem.getSzdm()) &&
                        ((String)preZB.get("yskmdm")).equals(mfsbxxprintSellerItem.getYskmdm()))
                    {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size()))
                {
                    curZB = new HashMap();
                    curZB.put("szdm",mfsbxxprintSellerItem.getSzdm());
                    curZB.put("sjje",mfsbxxprintSellerItem.getSjse());
                    curZB.put("yskmdm",mfsbxxprintSellerItem.getYskmdm());
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM",mfsbxxprintSellerItem.getSzdm());
                    if (mfsbxxprintSellerItem.getSzsmdm().endsWith("91") || mfsbxxprintSellerItem.getSzsmdm().endsWith("92"))
                    {
                        tempstr += "(滞纳金、罚款)";    
                    }
                    curZB.put("szmc",tempstr);
                    dataList.add(curZB);
                }
                else
                {
                    tmpdd = (BigDecimal)preZB.get("sjje"); 
                    tmpdd = tmpdd.add(mfsbxxprintSellerItem.getSjse());
                    preZB.put("sjje",tmpdd);
                }
                hjje = hjje.add(mfsbxxprintSellerItem.getSjse());

                for (int i = 0;i < dataList.size();i ++)
                {
                    curZB = (HashMap)dataList.get(i);
                    tmpdd = DataConvert.String2BigDecimal(curZB.get("sjje").toString());
                    curZB.put("sjje",deFormat.format(tmpdd));
                }
                resBo.setHjje(deFormat.format(hjje)); //合计金额
                //Debug.out("合计金额小写：" + deFormat.format(hjje));
                resBo.setHjjedx(Currency.convert(hjje)); //把合计金额转换为大写
                //Debug.out("合计金额大写：" + Currency.convert(hjje));
            }
	        resBo.setZsjg(jbsj.getSwjgzzjgmc()); //证收机关名称
	        resBo.setSbbh(sbbh);
	        resBo.setJsjdm(jsjdm);	        

	    	String szstr = "",jestr = "";
	    	Map sz;
	    	for (int i = 0;i < dataList.size();i++)
	    	{
	    	    sz = (Map)dataList.get(i);
	    	    szstr += sz.get("szdm");
	    	    szstr += " ";
	    	    szstr += sz.get("szmc");
	    	    szstr += ";;";
	    	    
	    	    jestr += sz.get("sjje");
	    	    jestr += ";;";
	    	}
	    	if (szstr.length() > 0)
	    	{
	    	    szstr = szstr.substring(0,szstr.length() - 2);
	    	    jestr = jestr.substring(0,jestr.length() - 2);
	    	}
	    	resBo.setMxSz(szstr);
	    	resBo.setMxSjse(jestr);
	    	resBo.setSbrqn(resBo.getSbrq().substring(0,4));
	    	resBo.setSbrqy(resBo.getSbrq().substring(4,6));
	    	resBo.setSbrqr(resBo.getSbrq().substring(6));
	        
        	resBo.setDybs("1");//设置打印标识为打印	        
        	
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "卖方契税查询－MfskzsProcessor，操作出错",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "失败");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	
	public static void main(String args[]){
		double ss = 777777777.87;
		int s_ = 99999999;
		int pageNum = 0;
		
		System.out.println("打印张数::"+ss/s_);
		
		pageNum   = (int)ss/s_;
		
		System.out.println("打印张数 pageNum::"+pageNum);
				
		double temp = pageNum;
		
		if( ss/s_ >temp){
			System.out.println("打印张数 pageNum::"+(pageNum+1));
		}		
		//=====		
		int s = (int)ss%99999999;		
//		
//		System.out.println(100/33);
//		System.out.println(100%33);		
		if(s%1 != 0){
			s =  (int)ss%99999999;	
		}		
	}
	//获得房屋采集信息
	public ClfswjghdxxlrBo getCjxx(String htbh,String sbbh) throws BaseException {
//		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo) vo.getData();
//		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
//		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		ClfswjghdxxlrBo resBo = null;
		System.out.println("sbbh................."+sbbh);
		System.out.println("htbh................."+htbh);
		if ((sbbh == null || "".equals(sbbh))
				&& (htbh == null || "".equals(htbh))) {
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
			
	        
			// 获得房屋信息
			List fwxxList = DAOFactory.getInstance().getFwxxDAO()
					.queryFwList(conn, sqlBuff.toString());

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
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	/**
	    * 查询完税证
	    * @author zhangj
	    */	
	public MfskzsBo doQueryWsz(VOPackage vo) throws BaseException {
		MfskzsBo data = (MfskzsBo) vo.getData();
		String htbh = data.getHtbh();
		String sbbh = data.getSbbh();
		MfskzsBo resBo = new MfskzsBo();
		resBo.setHtbh(htbh);
		resBo.setSbbh(sbbh);

		ArrayList sbxxList = new ArrayList();
		Connection conn = null;
			try {
				conn = QSDBUtil.getConnection();


			//首先查询本次申报生成的对应完税证数据：计算机代码、完税证序号、年度子别、完税证号、金额

			// 获得指定合同编号或者登录用户所在所的所有未打印的申报信息
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" and a.htbh ='"+htbh+"' and a.sbbh ='"+sbbh+"'");
			try {
				sbxxList = DAOFactory.getInstance().getMfsbxxprintSellerDAO().queryMfsbxxWszList(conn, sqlBuff.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
			} catch (BaseException e) {
				e.printStackTrace();
			}
			resBo.setSbxxList(sbxxList);
        	resBo.setDybs("0");//设置打印标识为不打印
        	return resBo;
	}

}
