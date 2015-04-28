package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SbjkmxData;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Szsmyfjs;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * 综合申报
 */
public abstract class ZhsbAction extends ShenbaoAction {
	protected class BankInfo {
		public String accountNumber; // 帐号

		public String bankID; // 银行代码

		public String bankName; // 银行名称
	}

	/**
	 * 生成申报信息
	 *
	 * @param mxDataList
	 *            前台明细值对象
	 * @param sklx
	 *            税款类型
	 * @param request
	 *            HttpServletRequest
	 * @param form
	 *            ZhsbForm
	 * @param now
	 *            Timestamp
	 * @return DeclareInfor
	 * @throws BaseException
	 */
	private DeclareInfor createDeclareInfor(List mxDataList, String sklx,
			HttpServletRequest request, ZhsbForm form, Timestamp now)
			throws BaseException {
		if (mxDataList == null || mxDataList.size() == 0) {
			return null;
		}

		String[] szsmdm = form.getSzsmdm(sklx);
        List voMxDataList = new ArrayList();
		if (szsmdm == null || szsmdm.length == 0) {
			return null;
		}
		if (mxDataList.size() != szsmdm.length) {
			boolean found = false;
			// 用户删除了税目
			for (int i = mxDataList.size() - 1; i >= 0; i--) {
				for (int j = 0; j < szsmdm.length; j++) {
                    SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
					System.out.println(mxData.getSzsmdm());
					System.out.println(szsmdm[j]);
					if (mxData.getSzsmdm().equals(
							szsmdm[j])) {
						voMxDataList.add(mxData);
						break;
					}
				}
			}
		}
        else
        {
            for(int i=0;i<mxDataList.size();i++)
            {
                voMxDataList.add(mxDataList.get(i));
            }
        }

		String[] kssl = form.getKssl(sklx);
		String[] jsje = form.getJsje(sklx);
		String[] sjse = form.getSjse(sklx);

		BigDecimal sum = new BigDecimal("0");

		int size = voMxDataList.size();
		for (int i = 0; i < size; i++) {
			SbjkmxData mxData = (SbjkmxData) voMxDataList.get(i);
            for(int j=0;j<kssl.length;j++)
            {
                if(mxData.getSzsmdm().equals(szsmdm[j]))
                {
                    if(mxData.isAksslj())
                    {
                        // 如果按课税数量计的话，设置课税数量
                        mxData.setKssl(new BigDecimal(kssl[j]));
                    }
                    mxData.setJsje(new BigDecimal(jsje[j])); // 计税金额
                    mxData.setSjse(new BigDecimal(sjse[j])); // 实缴税额

                    sum = sum.add(mxData.getSjse()); // 求合计
                    break;
                }
            }
		}

		List sbjkmxList = new ArrayList(size);

		// 将前台值对象转换为后台值对象
		convert(voMxDataList, sbjkmxList, now, request);

		// 取得银行信息
		BankInfo bankInfo = getPaymentBankInfo(request, form);

		// 取税务机关组织机构代码表
		Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
				CodeTable.SWJJZZJG_MAP);

		// 取得登记信息
		SWDJJBSJ djjbsj = FriendHelper.getSWDJJBSJ(request);

		// 生成缴款主表
		Sbjkzb zb = new Sbjkzb();

		// 对主表进赋值
		zb.setSjly(CodeConstant.SJLY_SB_SBLR); // 数据来源
		zb.setJsjdm(getUserData(request).yhid); // 计算机代码
		zb.setSklxdm(sklx); // 税款类型代码
		zb.setFsdm(CodeConstant.FSDM_WSSB); // 方式代码
		zb.setLsgxdm(djjbsj.getLsgxdm()); // 隶属关系代码
		zb.setYhdm(bankInfo.bankID); // 银行代码
		zb.setYhmc(bankInfo.bankName); // 银行名称
		zb.setZh(bankInfo.accountNumber); // 帐号
		zb.setDjzclxdm(djjbsj.getDjzclxdm()); // 登记注册类型代码
		zb.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // 税务机关组织机构代码
		zb.setZsswjgzzjgdm(djjbsj.getSwjgzzjgdm()); // 征收机关代码
		zb.setGjbzhydm(djjbsj.getGjbzhydm()); // 国家标准行业代码
		zb.setGkzzjgdm(((Swjgzzjg) swjgzzjgMap.get(zb.getSwjgzzjgdm()))
				.getGkjhh()); // 国库组织机构代码
		zb.setLrrq(now); // 录入日期
		zb.setSbrq(TinyTools.second2Day(now)); // 申报日期
		zb.setZyrq(zb.getSbrq()); // 帐页日期
		zb.setSjje(sum); // 实缴金额
		zb.setRkje(sum); // 入库金额
		zb.setLrr(getUserData(request).yhid); // 录入人代码
		zb.setCjrq(now); // 创建时间
		zb.setJydzlxdm(djjbsj.getJydzlxdm()); // 联系电话
		zb.setNd((new SimpleDateFormat("yyyy")).format(now));
		zb.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));

		return setDeclareInforDetail(new DeclareInfor(zb, sbjkmxList), request);
	}

	abstract protected DeclareInfor setDeclareInforDetail(
			DeclareInfor declareInfor, HttpServletRequest request);

	/**
	 * 转换前台值对象成后台值对象
	 *
	 * @param mxDataList
	 *            List 前台缴款明细值对象
	 * @param sbjkmxList
	 *            List 后台缴款明细值对象
	 * @param now
	 *            申报日期
	 * @param request
	 *            HttpServletRequest
	 * @throws BaseException
	 */
	private void convert(List mxDataList, List sbjkmxList, Timestamp now,
			HttpServletRequest request) throws BaseException {
		String jsjdm = getUserData(request).yhid;

		String swjgzzjgdm = FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String nd = sdf.format(now);
		int size = mxDataList.size();
		for (int i = 0; i < size; i++) {
			SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);

			// 由前台值对象成后台值对象
			Sbjkmx mx = mxData.getSbjkmx();

			Szsm szsm = this.getSzsm(mx.getSzsmdm(), request);

			mx.setJsjdm(jsjdm); // 计算机代码
			mx.setZqlxdm(szsm.getZqlxdm()); // 征期类型代码
			mx.setSwjgzzjgdm(swjgzzjgdm);
			mx.setNd(nd);
			mx.setCjrq(now);
			mx.setLrrq(now);
			mx.setQxdm(swjgzzjgdm.substring(0, 2));

			sbjkmxList.add(mx);
		}
	}

	// /**
	// * 进行申报
	// * @param mapping ActionMapping
	// * @param form ActionForm
	// * @param request HttpServletRequest
	// * @param response HttpServletResponse
	// * @return ActoinForward
	// * @throws BaseException
	// */
	// public ActionForward doSave(ActionMapping mapping,
	// ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response) throws
	// BaseException {
	// // 检查token
	// ActionForward forward = doHandleToken(mapping, request);
	// if (forward != null) {
	// return forward;
	// }
	// String showMsg = "";
	// List zcjkList = null;
	// List bjqsList = null;
	// List sdjjList = null;
	// Map declareMap = new HashMap(3);
	// ZhsbForm myForm = (ZhsbForm) form;
	//
	// try {
	// // 从session取得缴款信息
	// zcjkList = (List)
	// request.getSession().getAttribute(SessionKey.ZCJK_LIST);
	// bjqsList = (List)
	// request.getSession().getAttribute(SessionKey.BJQS_LIST);
	// sdjjList = (List)
	// request.getSession().getAttribute(SessionKey.SDJJ_LIST);
	//
	// UserData userData = getUserData(request);
	//
	// Timestamp now = new Timestamp(System.currentTimeMillis());
	// // start added by qianchao 2005.10.6
	// boolean callYyaqSignFlag = userData.caflag;
	// long calsh = 0;
	// if (callYyaqSignFlag) {
	// System.out.println("============保存签名数据开始==============");
	// ///检测签名并保存
	// String clientSignData = request.getParameter("secClientSignData");
	// String secActionSign = request.getParameter("secActionSign");
	// String secOrginData = request.getParameter("secOrginData");
	// System.out.println("clientSignData=======:" + clientSignData);
	// System.out.println("secActionSign=======:" + secActionSign);
	// System.out.println("secOrginData=======:" + secOrginData);
	// SecureManager sm = CAManagerLocator.getBJCASecureManager();
	// try {
	// sm.setUserCertByString(userData.getCert().getCert(), "");
	// if (!sm.verifySignature(secOrginData, clientSignData)) {
	// throw new ApplicationException("验证签名失败");
	// }
	// calsh = ( (Long) YYAQProxy.getInstance().saveSignedData(
	// userData, secOrginData,
	// clientSignData)).longValue();
	// System.out.println(" lsh:" + calsh);
	// }
	// catch (Exception ex) {
	// ex.printStackTrace();
	// throw ExceptionUtil.getBaseException(ex);
	// }
	// System.out.println("============保存签名数据结束==============");
	// }
	// // end added by qianchao 2005.10.6 */
	// // 生成正常缴款数据
	// DeclareInfor declareInforZcjk =
	// createDeclareInfor(zcjkList, CodeConstant.SKLXDM_ZCJK, request,
	// myForm, now);
	// if (declareInforZcjk != null) {
	// declareMap.put("0", declareInforZcjk);
	// }
	//
	// // 生成补缴欠税数据
	// DeclareInfor declareInforBjqs =
	// createDeclareInfor(bjqsList, CodeConstant.SKLXDM_BJQS, request,
	// myForm, now);
	// if (declareInforBjqs != null) {
	// declareMap.put("1", declareInforBjqs);
	// }
	//
	// // 生成三代解缴数据
	// DeclareInfor declareInforSdjj =
	// createDeclareInfor(sdjjList, CodeConstant.SKLXDM_SDJJ, request,
	// myForm, now);
	// if (declareInforSdjj != null) {
	// declareMap.put("2", declareInforSdjj);
	// }
	//
	// VOPackage vo = new VOPackage();
	// vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo.setAction(ActionConstant.INT_ACTION_SAVE);
	// vo.setData(declareMap);
	// vo.setUserData(userData);
	//
	// Object reObject = ShenbaoProxy.getInstance().process(vo);
	// showMsg += "申报成功";
	//
	// /**
	// * Modified by Ha Zhengze 20051008 20:59 为部分区县试运行进行一票多税的调整 银行扣款 Start
	// */
	// // 从税费获得三方协议
	// Sfxy sfxy = FriendHelper.getSfxy(request);
	// if (sfxy == null || sfxy.getZh() == null) {
	// this.log("无三方协议：" + userData.getYhid());
	// //do nothing
	// }
	// else { //三方协议户
	// this.log("三方协议:" + userData.getYhid());
	// String yhdm = sfxy.getYhdm();
	// Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
	// Yh yh = (Yh) yhMap.get(yhdm);
	// if (LWUtil.isLW(request.getSession().getServletContext(),
	// userData.getSsdwdm(),
	// yh.getYhdm())) { // 区县银行已联网，一票多税
	// //计算扣款金额
	// double hjzse = 0.00; //最终进行扣款的金额总数
	// String[] sjses;
	// sjses = myForm.getSjse_zcjk();
	// if (sjses != null) {
	// for (int i = 0; i < sjses.length; i++) {
	// hjzse += Double.parseDouble(sjses[i]);
	// }
	// }
	// sjses = myForm.getSjse_bjqs();
	// if (sjses != null) {
	// for (int i = 0; i < sjses.length; i++) {
	// hjzse += Double.parseDouble(sjses[i]);
	// }
	// }
	// sjses = myForm.getSjse_sdjj();
	// if (sjses != null) {
	// for (int i = 0; i < sjses.length; i++) {
	// hjzse += Double.parseDouble(sjses[i]);
	// }
	// }
	// System.out.println("网上申报调用税库银扣款金额[" + hjzse + "]");
	// try {
	// //生成税票号码并更新至数据库
	// System.out.println("============生成税票号码并更新至数据库开始==============");
	// VOPackage vo1 = new VOPackage();
	// vo1.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo1.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
	// vo1.setData(reObject);
	// vo1.setUserData(userData);
	// List ypdsJkss = (List) ShenbaoProxy.getInstance().process(vo1);
	// showMsg += ",生成一票多税缴款书成功";
	// System.out.println("============生成税票号码并更新至数据库结束==============");
	// //网上申报调用税库银扣款接口
	// System.out.println("========网上申报调用税库银扣款接口开始[" + userData.getYhid() +
	// "|" +
	// sfxy.getYhdm() + "|" + sfxy.getZh() +
	// "]===========");
	// try {
	// SKHAdaptor sa = new SKHAdaptor();
	// SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);//临时修改
	// sa.transferMoneyFromNsrZhToGk(sfxy.getYhdm()
	// , sfxy.getZh()
	// , jbsj.getSwjgzzjgdm().substring(0, 2)
	// , hjzse
	// , ypdsJkss
	// ,jbsj.getSwjgzzjgdm()
	// , userData);
	// if (callYyaqSignFlag) {
	// System.out.println("============回填申报编号到签名开始==============");
	// for (int ii = 0; ii < ypdsJkss.size(); ii++) {
	// YYAQProxy.getInstance().updateSignedDataWithSBBH(calsh,
	// ( (YPDSJKS) ypdsJkss.get(ii)).getSbbh());
	// }
	// System.out.println("============回填申报编号到签名结束==============");
	// }
	// }
	// catch (Exception e) {
	// //删除数据
	// System.out.println(
	// "===========税库银扣款失败，调用后台清除申报数据开始===============");
	// VOPackage vo3 = null;
	// Map obje = (Map) reObject;
	// Iterator keyset = obje.keySet().iterator();
	// Map declares = null;
	// Map voMap = null;
	// String sbbh = null;
	// while (keyset.hasNext()) {
	// sbbh = (String) keyset.next();
	// declares = (Map) obje.get(sbbh);
	// System.out.println("删除申报编号为" + sbbh + "的申报数据开始");
	// voMap = new HashMap();
	// voMap.put(ZhsbMapConstant.SBBH, sbbh);
	// vo3 = new VOPackage();
	// vo3.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo3.setAction(ActionConstant.INT_ACTION_DELETEALL);
	// vo3.setData(voMap);
	// vo3.setUserData(userData);
	// ShenbaoProxy.getInstance().process(vo3);
	// System.out.println("删除申报编号为" + sbbh + "的申报数据结束");
	// }
	// log("===========税库银扣款失败，调用后台清除申报数据结束===============");
	// e.printStackTrace();
	// throw ExceptionUtil.getBaseException(e);
	// }
	// showMsg += ",您是三方协议户即时扣款已成功";
	// System.out.println("========网上申报调用税库银扣款接口完毕[" + userData.getYhid() +
	// "|" +
	// sfxy.getYhdm() + "|" + sfxy.getZh() +
	// "]===========");
	// //置账务标志第二十位扣款成功标志位
	// log("===========调用后台更新账务标志开始===============");
	// VOPackage vo2 = new VOPackage();
	// vo2.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo2.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
	// vo2.setData(reObject);
	// vo2.setUserData(userData);
	// reObject = ShenbaoProxy.getInstance().process(vo2);
	// showMsg += ",申报缴款成功";
	// log("===========调用后台更新账务标志完毕===============");
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// throw ExceptionUtil.getBaseException(e);
	// }
	// }
	// }
	// /**
	// * Modified by Ha Zhengze 20051008 20:59 为部分区县试运行进行一票多税的调整 End
	// */
	// clearSession(request);
	// request.getSession().setAttribute(SessionKey.JKS, reObject);
	// request.setAttribute("QYSBSuccess", showMsg);
	// return mapping.findForward("Save");
	// }
	// catch (Exception e) {
	// // 重新放到session中,使界面保留用户填过的信息
	// request.getSession().setAttribute(SessionKey.ZCJK_LIST, zcjkList);
	// request.getSession().setAttribute(SessionKey.BJQS_LIST, bjqsList);
	// request.getSession().setAttribute(SessionKey.SDJJ_LIST, sdjjList);
	// myForm.setZcjkList(zcjkList);
	// myForm.setBjqsList(bjqsList);
	// myForm.setSdjjList(sdjjList);
	// setBankInfo(request, (ZhsbForm) form);
	//
	// throw ExceptionUtil.getBaseException(e);
	// }
	// }

	/**
	 * 进行申报
	 *
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActoinForward
	 * @throws BaseException
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// 0.检查token,防止重复提交
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// 1.句柄申明
		String showMsg = "";
		List zcjkList = null;
		List bjqsList = null;
		List sdjjList = null;
		Map declareMap = new HashMap(3);
		// 2.初始化
        ZhsbForm myForm = (ZhsbForm)form;
        SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // 临时修改
        // 3.业务过程
        
        //start modified code by qianchao 2006-2-15
        
        // /3.1.获取用户基本账户
        UserData userData = getUserData(request);
        
        boolean callYyaqSignFlag = userData.caflag;
        DzyjsjVO dzyj = null;
        //end   modified code by qianchao 2006-2-15
        
        try
        {
            // /3.0.从session取得缴款信息(包括正常缴款数据、补交欠税和三代解缴数ju)
            zcjkList = (List)request.getSession().getAttribute(
                SessionKey.ZCJK_LIST);
            bjqsList = (List)request.getSession().getAttribute(
                SessionKey.BJQS_LIST);
            sdjjList = (List)request.getSession().getAttribute(
                SessionKey.SDJJ_LIST);
            // /3.2.获取当前时间标签
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// /3.3.CA户进行签名

			// start added by qianchao 2005.10.6
            //started modified by qianchao 2006-2-7

            String strOrginData = request.getParameter("SecX_OrginData");
            String signData = request.getParameter("SecX_SignData");
            if (callYyaqSignFlag){
                String SecX_Error = request.getParameter("SecX_Error");
                if (! "0".equals(SecX_Error))
                {
                    String tempstr;
                    tempstr = "解密验签名错误!Error:" + SecX_Error
                    + " SecX_OD " + userData.getYhid() + ":" + strOrginData
                    + "-----SecX_SD:" + signData  + "-----";
                    System.out.println(tempstr);
                    
                    ActionErrors errors = new ActionErrors();
                    errors.add("", new ActionError("error.server.custom", "解密验签名错误！Error:" + SecX_Error));
                    saveErrors(request, errors);
                    return (new ActionForward(mapping.getInput()));
                }
				System.out.println("============保存签名数据开始==============");
				// /检测签名并保存
				try {
//                    dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(
//							userData, strOrginData, signData,
//							codeConstants.YWDM_SB_WS_ZHSB,"0",codeConstants.YWCZLX_NEW);
                    log("=签名=yh：" + userData.yhid + " lsh:" + dzyj.getLsh());
				} catch (Exception ex) {
					request.getSession().setAttribute(SessionKey.ZCJK_LIST,
							zcjkList);
					request.getSession().setAttribute(SessionKey.BJQS_LIST,
							bjqsList);
					request.getSession().setAttribute(SessionKey.SDJJ_LIST,
							sdjjList);
					myForm.setZcjkList(zcjkList);
					myForm.setBjqsList(bjqsList);
					myForm.setSdjjList(sdjjList);
					setBankInfo(request, (ZhsbForm) form);

					ex.printStackTrace();
					throw ExceptionUtil.getBaseException(ex);
				}
				System.out.println("============保存签名数据结束==============");
			}
            //ended   modified by qianchao 2006-2-7
			// end added by qianchao 2005.10.6 */
			// /3.4.生成缴款数据
			// //3.4.0.正常缴款数据生成
			DeclareInfor declareInforZcjk = createDeclareInfor(zcjkList,
					CodeConstant.SKLXDM_ZCJK, request, myForm, now);
			if (declareInforZcjk != null) {
				declareMap.put("0", declareInforZcjk);
			}
			// //3.4.1.补缴欠税数据生成
			DeclareInfor declareInforBjqs = createDeclareInfor(bjqsList,
					CodeConstant.SKLXDM_BJQS, request, myForm, now);
			if (declareInforBjqs != null) {
				declareMap.put("1", declareInforBjqs);
			}
			// //3.4.2.三代解缴数据生成
            DeclareInfor declareInforSdjj = createDeclareInfor(sdjjList,
                CodeConstant.SKLXDM_SDJJ, request, myForm, now);
            if(declareInforSdjj != null)
            {
                System.out.println("declareInforSdjj mx size============" +
                                   declareInforSdjj.getSbjkmxInfo().size());
                declareMap.put("2", declareInforSdjj);
            }
			// /3.5.通过税费接口获取三方协议
			Sfxy sfxy = FriendHelper.getYhkkSfxy(request);
			String yhdm = null;
			Map yhMap = null;
			Yh yh = null;

			if (sfxy != null) {
				yhdm = sfxy.getYhdm();
				yhMap = CodeTableUtil
						.getCodeTableMap(request, CodeTable.YH_MAP);
				yh = (Yh) yhMap.get(yhdm);
			}
			// /3.6.调用后台业务进行处理
			// //3.6.0.生成值对象
			VOPackage vo = new VOPackage();
			HashMap dataMap = new HashMap();
			dataMap.put("sfxy", sfxy);// 三方协议
			dataMap.put("declareMap", declareMap);// 申报信息
			dataMap.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// 签名原件
			dataMap.put("yh", yh);// 银行对象
			dataMap.put("jbsj", jbsj);// 登记数据
			vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_SAVE);
			vo.setData(dataMap);
			vo.setUserData(userData);
			HashMap reMap = null;
			// //3.6.1.调用Processor
            
            //start added code by qianchao 2006-2-15
            //异常处理全部由最后的catch实现。
			reMap = (HashMap) ShenbaoProxy.getInstance().process(vo);
            //end   added code by qianchao 2006-2-15

            
            // //3.6.2.获取并整理返回数据
			Object reObject = reMap.get("reObject");
			showMsg = (String) reMap.get("showMsg");
            Boolean sskk = (Boolean)reMap.get("sskk");
			// /3.7.清理内存对象
			clearSession(request);
			request.getSession().setAttribute(SessionKey.JKS, reObject);
			request.setAttribute("QYSBSuccess", showMsg);
            request.setAttribute("IsSskk",sskk);
			// /3.8.返回
			return mapping.findForward("Save");
		} catch (Exception e) {
			// /3.9.重新放到session中,使界面保留用户填过的信息
            if (callYyaqSignFlag) {
                log("============出错删除签名开始==============" + dzyj.getLsh());
                try
                {
                    CAProxy.getInstance().deleteSignedData(dzyj);
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
                log("============出错删除签名结束==============" + dzyj.getLsh());
            }
            System.out.println("网上综合申报异常信息: ===== " +  e.getMessage());
            
			request.getSession().setAttribute(SessionKey.ZCJK_LIST, zcjkList);
			request.getSession().setAttribute(SessionKey.BJQS_LIST, bjqsList);
			request.getSession().setAttribute(SessionKey.SDJJ_LIST, sdjjList);
			myForm.setZcjkList(zcjkList);
			myForm.setBjqsList(bjqsList);
			myForm.setSdjjList(sdjjList);
			setBankInfo(request, (ZhsbForm) form);
			// /3.10.继续抛出异常到页面
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 取得税种税目的附加税
	 *
	 * @param mxDataList
	 *            List
	 * @param fjsInfo
	 *            Map
	 * @param request
	 *            HttpServletRequest
	 * @return List
	 * @throws BaseException
	 */
	private List getAdditionalTax(List mxDataList, Map fjsInfo,
			HttpServletRequest request) throws BaseException {
		ArrayList result = new ArrayList();

		int size = mxDataList.size();
		for (int i = 0; i < size; i++) {
			String aSzsmdm = ((SbjkmxData) mxDataList.get(i)).getSzsmdm();
			List fjs = processFjs(aSzsmdm, result, size, request);

			if (fjs.size() != 0) {
				fjsInfo.put(new Integer(i), fjs);
			}
		}

		return result;
	}

	/**
	 * 处理附加税
	 *
	 * @param aSzsmdm
	 *            String
	 * @param result
	 *            List
	 * @param offset
	 *            int
	 * @param request
	 *            HttpServletRequest
	 * @return List
	 * @throws BaseException
	 */
	private List processFjs(String aSzsmdm, List result, int offset,
			HttpServletRequest request) throws BaseException {
		List additionTax = null;

		String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
		Djzclx djzclx = (Djzclx) CodeTableUtil.getCodeTableMap(request,
				CodeTable.DJZCLX_MAP).get(djzclxdm);
		if (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT)
				|| djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_WZ)) {
			// 取外资企业附加税
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_WQ_LIST);
		} else {
			// 取内资企业附加税
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_LIST);
		}

		List fjsList = new ArrayList();

		int j = 0;
		for (; j < additionTax.size(); j++) {
			Szsmyfjs fjs = (Szsmyfjs) additionTax.get(j);

			if (fjs.getSzsmdm().equals(aSzsmdm)) {
				// 此税有附加税
				boolean found = false;
				int k = 0;
				for (; k < result.size(); k++) {
					if (((SbjkmxData) result.get(k)).getSzsmdm().equals(
							fjs.getFjsszsmdm())) {
						found = true;
						break;
					}
				}
				if (!found) {
					SbjkmxData mx = getMxData(fjs.getFjsszsmdm(), request);
					mx.setIsFjs(true);
					result.add(mx);
				}
				fjsList.add(new Integer(k + offset));
			}
		}

		return fjsList;
	}

	/**
	 * 返回
	 *
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		clearSession(request);

		return mapping.findForward("Return");
	}

	/**
	 * 清session
	 *
	 * @param request
	 *            HttpServletRequest
	 */
	private void clearSession(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionKey.ZCJK_LIST);
		request.getSession().removeAttribute(SessionKey.ZCJK_FJS_INFO);
		request.getSession().removeAttribute(SessionKey.BJQS_LIST);
		request.getSession().removeAttribute(SessionKey.BJQS_FJS_INFO);
		request.getSession().removeAttribute(SessionKey.SDJJ_LIST);
		request.getSession().removeAttribute(SessionKey.SDJJ_FJS_INFO);
	}

	abstract protected void setBankInfo(HttpServletRequest request,
			ZhsbForm form) throws BaseException;

	abstract protected BankInfo getPaymentBankInfo(HttpServletRequest request,
			ZhsbForm form) throws BaseException;

	/**
	 * 显示
	 *
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 * @throws BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			// 获得计算机代码
			String jsjdm = getUserData(request).yhid;

			ZhsbForm myForm = (ZhsbForm) form;

			// 设置银行信息（由子类实现）
			setBankInfo(request, myForm);

			// 设置计算机代码
			myForm.setJsjdm(jsjdm);

			SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);
			myForm.setDwmc(jbsj.getNsrmc()); // 设置纳税人名称
			// 设置申报日期（当前时间）
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			myForm.setSbrq(sdf.format(new Date()));
			// 取税费管理数据
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			Calendar cc = new GregorianCalendar();
			cc.add(Calendar.MONTH, -1);
			int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
			cc.set(Calendar.DAY_OF_MONTH, maxday);
			// 上个月最后一天
			Date dd = cc.getTime();
			Map sfMap = sfglProxy.getCDFSet(jsjdm, dd, dd, dd);
			// 定期定额数据
			Map dqdeInfo = getDqdeInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_DQDE));
			// 附加税信息
			Map fjsInfo = getFjsInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_FJS));
            
         
			// 车房土定额
			Map cftInfo = getCftInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_CFT));

			// 处理正常缴款
			String[] zcjk = myForm.getZcjk();
			if (zcjk != null) {
				Map zcjkFjsInfo = new HashMap();
				List zcjkList = dealwithSzsm(zcjk, zcjkFjsInfo, request);
				dealwithSfgl(dqdeInfo, cftInfo, fjsInfo, zcjkList, jsjdm);
				myForm.setZcjkList(zcjkList);
				request.getSession().setAttribute(SessionKey.ZCJK_FJS_INFO,
						zcjkFjsInfo);
				request.getSession().setAttribute(SessionKey.ZCJK_LIST,
						zcjkList);
                System.out.println("+++++++" + zcjkList);
			}
			// 处理补缴欠税
			String[] bjqs = myForm.getBjqs();
			if (bjqs != null) {
				Map bjqsFjsInfo = new HashMap();
				List bjqsList = dealwithSzsm(bjqs, bjqsFjsInfo, request);
				dealwithSfgl(null, null, fjsInfo, bjqsList, jsjdm);

				myForm.setBjqsList(bjqsList);
				request.getSession().setAttribute(SessionKey.BJQS_FJS_INFO,
						bjqsFjsInfo);
				request.getSession().setAttribute(SessionKey.BJQS_LIST,
						bjqsList);
			}

			// 处理三代解缴
			String[] sdjj = myForm.getSdjj();
			if (sdjj != null) {
				Map sdjjFjsInfo = new HashMap();
				List sdjjList = dealwithSzsm(sdjj, sdjjFjsInfo, request);
				dealwithSfgl(null, null, fjsInfo, sdjjList, jsjdm);

				myForm.setSdjjList(sdjjList);
				request.getSession().setAttribute(SessionKey.SDJJ_FJS_INFO,
						sdjjFjsInfo);
				request.getSession().setAttribute(SessionKey.SDJJ_LIST,
						sdjjList);
			}
			return mapping.findForward("Show");
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private Map getCftInfo(List cftList) {
		Map cftMap = new HashMap();
		for (int i = 0; i < cftList.size(); i++) {
			Cftsyhd cftsyhd = (Cftsyhd) cftList.get(i);
			cftMap.put(cftsyhd.getSzsmdm(), cftsyhd);
		}

		if (cftMap.size() == 0) {
			return cftMap = null;
		}
		return cftMap;
	}

	private Map getFjsInfo(List fjsInfo) {
		Map fjsMap = new HashMap();
		for (int i = 0; i < fjsInfo.size(); i++) {
			Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
			fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
		}

		if (fjsMap.size() == 0) {
			return null;
		}

		return fjsMap;
	}

	// 取定期定额数据
	private Map getDqdeInfo(List dqdeInfo) {
		Map dqdeMap = new HashMap();
		for (int i = 0; i < dqdeInfo.size(); i++) {
			Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
			dqdeMap.put(dqde.getSzsmdm(), dqde);
		}

		if (dqdeMap.size() == 0) {
			return null;
		}

		return dqdeMap;
	}

	/**
	 * 处理税费管理的定期定额数据
	 *
	 * @param dqdeMap
	 *            定期定额数据
	 * @param cftMap
	 *            车房土
	 * @param fjsMap
	 *            附加税
	 * @param zcjkList
	 *            正常缴款
	 * @param jsjdm
	 *            计算机代码
	 */
	private void dealwithSfgl(Map dqdeMap, Map cftMap, Map fjsMap,
			List zcjkList, String jsjdm) {
		for (int i = 0; i < zcjkList.size(); i++) {
			SbjkmxData mxData = (SbjkmxData) zcjkList.get(i);
			String szsmdm = mxData.getSzsmdm();

			String szdm = szsmdm.substring(0, 2);

			// 处理税费管理中的定期定额
			if (dqdeMap != null) {
				Dqdedlmx1 dqde = (Dqdedlmx1) dqdeMap.get(szsmdm);
				if (dqde != null) {
					String zsfsdm = dqde.getZsfsdm();
					if (zsfsdm != null && zsfsdm.equals("01")) { // 定额征收
						if (dqde.getSjrd() == null) {
							mxData.setJsje(dqde.getYnsrd()); // 税基
						} else {
							mxData.setJsje(dqde.getSjrd()); // 税基
						}
						mxData.setSjse(dqde.getYnsrd()); // 税额
						mxData.setFromDqde(true);
					} else if (zsfsdm != null && zsfsdm.equals("02")) { // 定率征收
						mxData.setSl(dqde.getZsl());
					}
				}
			}

			// 处理税费管理中的附加税税率（特殊征收率）
			// if (mxData.isIsFjs() && fjsMap != null)
			if ((szdm.equals("10") || szdm.equals("51")) && fjsMap != null) {
				Tszslmx tszslmx = (Tszslmx) fjsMap.get(szsmdm.substring(0, 2));
				if (tszslmx != null) {
					mxData.setSl(tszslmx.getSl());
				}
			}

			// 处理个税定额
			if (szsmdm.equals(SzsmdmConstant.GRSDS_GXDE)) {
				try {
					com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

					Calendar cc = new GregorianCalendar();
					cc.add(Calendar.MONTH, -1);
					cc.set(Calendar.DAY_OF_MONTH, cc
							.getActualMaximum(Calendar.DAY_OF_MONTH));

					BigDecimal gshj = proxy
							.getGrtszygsdeHj(jsjdm, cc.getTime());
					if (gshj != null) {
						mxData.setJsje(gshj);
						mxData.setSjse(gshj);
						mxData.setFromDqde(true);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			// 处理车房土
			if (cftMap != null) {
				Cftsyhd cftsyhd = (Cftsyhd) cftMap.get(szsmdm);
				if (cftsyhd != null) {
					mxData.setKssl(cftsyhd.getKssl());
					mxData.setSjse(cftsyhd.getJsje());
					mxData.setJsje(cftsyhd.getSjje());
				}
			}

			// 处理车房土的计算系数
			if ((SzsmdmConstant.TDSYF + SzsmdmConstant.FCS + SzsmdmConstant.CCSYF)
					.indexOf(szdm) >= 0) {
				com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

				try {
					int jncs = proxy.getJncs(jsjdm, szdm);
					/**
                     * 城镇土地使用税2007年10月征期征收限制
                     * 因2007年上半年未征收土地税,下半年征期须征收全年税款
                     * 故特作如下控制:
                     * 2007年城镇土地使用税缴纳次数统一为全年征收,即缴纳次数为0
                     * 2007.10征期过后,按税费认定缴纳次数征收
                     * 
                     * 王志民 2007-8-15日备注
                     */
					Date today=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                    
                    if(szdm.equals("15")&&sdf.format(today).substring(0,4).equals("2007")){
                    	jncs=0;
                    }
					if (jncs > 0) {
						// 如果有缴纳次数的话，实缴税额要乘以系数0.5
						mxData.setCoefficient("0.5");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * 处理
	 *
	 * @param szsmdms
	 *            String[] 税种税目代码
	 * @param fjsInfo
	 *            Map 附加税信息，输出参数
	 * @param request
	 *            HttpServletRequest
	 * @return List
	 * @throws BaseException
	 */
	private List dealwithSzsm(String[] szsmdms, Map fjsInfo,
			HttpServletRequest request) throws BaseException {
		if (szsmdms == null) {
			return new ArrayList(0);
		}

		List mxDataList = new ArrayList();

		int length = szsmdms.length;

		// 处理主税（用户选择的税目）
		for (int i = 0; i < length; i++) {
			String szsmdm = szsmdms[i];

			SbjkmxData mxData = getMxData(szsmdm, request); // 生成前台值对象SbjkmxData

			mxDataList.add(mxData);
		}

		filterFjs(mxDataList, request);

		// 取得附加税信息
		List additionalTax = getAdditionalTax(mxDataList, fjsInfo, request);

		if (additionalTax != null) {
			mxDataList.addAll(additionalTax);
		}

		return mxDataList;
	}

	/**
	 * 如果用户选择的附加税的主税也在用户选择之列的话，过滤调
	 *
	 * @param mxDataList
	 *            用户选择的税目明细数据
	 * @param request
	 *            HttpServletRequest
	 * @throws BaseException
	 */
	private void filterFjs(List mxDataList, HttpServletRequest request)
			throws BaseException {
		Map szsmMap = CodeTableUtil.getCodeTableMap(request,
				CodeTable.SZSM_MAP_AVAILABLE);

		List children = new ArrayList(); // 存放要过滤调的税目
		for (int i = 0; i < mxDataList.size(); i++) {
			SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
			Szsm szsm = (Szsm) szsmMap.get(mxData.getSzsmdm());

			if (szsm != null && szsm.getSffjs() != null
					&& szsm.getSffjs().equals("2")) { // 如果是附加税的话
				// 找出这个附加税的主税s
				List fathers = getFather(szsm.getSzsmdm(), request);
				for (int j = 0; j < mxDataList.size(); j++) {
					SbjkmxData mx = (SbjkmxData) mxDataList.get(j);
					if (fathers.contains(mx.getSzsmdm())) {
						// 如果主税在mxDataList中，将这个孩子存起来
						children.add(szsm.getSzsmdm());
					}
				}
			}
		}

		// 将上面记录下来的孩子都删掉
		for (int i = mxDataList.size() - 1; i >= 0; i--) {
			SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
			String szsmdm = mxData.getSzsmdm();
			if (children.contains(szsmdm)) {
				mxDataList.remove(i);
			}
		}
	}

	/**
	 * 取出附加税的所有主税
	 *
	 * @param fjs
	 *            附加税的税目代码
	 * @param request
	 *            HttpServletRequest
	 * @return 主税List
	 * @throws BaseException
	 */
	private List getFather(String fjs, HttpServletRequest request)
			throws BaseException {
		List additionTax = null;

		List fathers = new ArrayList();

		// 根据内、外资企业取附加税
		String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
		Djzclx djzclx = (Djzclx) CodeTableUtil.getCodeTableMap(request,
				CodeTable.DJZCLX_MAP).get(djzclxdm);
		if (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT)
				|| djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_WZ)) {
			// 外资
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_WQ_LIST);
		} else {
			// 内资
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_LIST);
		}

		for (int i = 0; i < additionTax.size(); i++) {
			Szsmyfjs szsmyfjs = (Szsmyfjs) additionTax.get(i);
			if (szsmyfjs.getFjsszsmdm().equals(fjs)) {
				fathers.add(szsmyfjs.getSzsmdm());
			}
		}

		return fathers;
	}

	/**
	 * 取得明细数据
	 *
	 * @param szsmdm
	 *            String 税种税目代码
	 * @param request
	 *            HttpServletRequest
	 * @return SbjkmxData
	 */
	private SbjkmxData getMxData(String szsmdm, HttpServletRequest request) {
		SbjkmxData mxData = new SbjkmxData();
		Szsm sz = getSzsm(szsmdm.substring(0, 2), request);
		Szsm szsm = getSzsm(szsmdm, request);

		mxData.setSzsmdm(szsmdm);
		mxData.setSzmc(sz.getSzsmmc());
		mxData.setSzsmmc(szsm.getSzsmmc());
		mxData.setSl(szsm.getSl());
		mxData.setAsljbs(szsm.getAsljbs());

		return mxData;
	}

	protected void log(String str) {
		if (ApplicationConstant.DEBUG_FLAG) {
			System.out.println("[WSSB DEBUG]" + (new Date()) + str);
		}
	}

}
