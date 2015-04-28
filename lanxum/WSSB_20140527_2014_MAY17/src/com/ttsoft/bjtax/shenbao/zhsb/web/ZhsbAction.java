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
 * �ۺ��걨
 */
public abstract class ZhsbAction extends ShenbaoAction {
	protected class BankInfo {
		public String accountNumber; // �ʺ�

		public String bankID; // ���д���

		public String bankName; // ��������
	}

	/**
	 * �����걨��Ϣ
	 *
	 * @param mxDataList
	 *            ǰ̨��ϸֵ����
	 * @param sklx
	 *            ˰������
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
			// �û�ɾ����˰Ŀ
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
                        // �������˰�����ƵĻ������ÿ�˰����
                        mxData.setKssl(new BigDecimal(kssl[j]));
                    }
                    mxData.setJsje(new BigDecimal(jsje[j])); // ��˰���
                    mxData.setSjse(new BigDecimal(sjse[j])); // ʵ��˰��

                    sum = sum.add(mxData.getSjse()); // ��ϼ�
                    break;
                }
            }
		}

		List sbjkmxList = new ArrayList(size);

		// ��ǰֵ̨����ת��Ϊ��ֵ̨����
		convert(voMxDataList, sbjkmxList, now, request);

		// ȡ��������Ϣ
		BankInfo bankInfo = getPaymentBankInfo(request, form);

		// ȡ˰�������֯���������
		Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
				CodeTable.SWJJZZJG_MAP);

		// ȡ�õǼ���Ϣ
		SWDJJBSJ djjbsj = FriendHelper.getSWDJJBSJ(request);

		// ���ɽɿ�����
		Sbjkzb zb = new Sbjkzb();

		// ���������ֵ
		zb.setSjly(CodeConstant.SJLY_SB_SBLR); // ������Դ
		zb.setJsjdm(getUserData(request).yhid); // ���������
		zb.setSklxdm(sklx); // ˰�����ʹ���
		zb.setFsdm(CodeConstant.FSDM_WSSB); // ��ʽ����
		zb.setLsgxdm(djjbsj.getLsgxdm()); // ������ϵ����
		zb.setYhdm(bankInfo.bankID); // ���д���
		zb.setYhmc(bankInfo.bankName); // ��������
		zb.setZh(bankInfo.accountNumber); // �ʺ�
		zb.setDjzclxdm(djjbsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
		zb.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // ˰�������֯��������
		zb.setZsswjgzzjgdm(djjbsj.getSwjgzzjgdm()); // ���ջ��ش���
		zb.setGjbzhydm(djjbsj.getGjbzhydm()); // ���ұ�׼��ҵ����
		zb.setGkzzjgdm(((Swjgzzjg) swjgzzjgMap.get(zb.getSwjgzzjgdm()))
				.getGkjhh()); // ������֯��������
		zb.setLrrq(now); // ¼������
		zb.setSbrq(TinyTools.second2Day(now)); // �걨����
		zb.setZyrq(zb.getSbrq()); // ��ҳ����
		zb.setSjje(sum); // ʵ�ɽ��
		zb.setRkje(sum); // �����
		zb.setLrr(getUserData(request).yhid); // ¼���˴���
		zb.setCjrq(now); // ����ʱ��
		zb.setJydzlxdm(djjbsj.getJydzlxdm()); // ��ϵ�绰
		zb.setNd((new SimpleDateFormat("yyyy")).format(now));
		zb.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));

		return setDeclareInforDetail(new DeclareInfor(zb, sbjkmxList), request);
	}

	abstract protected DeclareInfor setDeclareInforDetail(
			DeclareInfor declareInfor, HttpServletRequest request);

	/**
	 * ת��ǰֵ̨����ɺ�ֵ̨����
	 *
	 * @param mxDataList
	 *            List ǰ̨�ɿ���ϸֵ����
	 * @param sbjkmxList
	 *            List ��̨�ɿ���ϸֵ����
	 * @param now
	 *            �걨����
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

			// ��ǰֵ̨����ɺ�ֵ̨����
			Sbjkmx mx = mxData.getSbjkmx();

			Szsm szsm = this.getSzsm(mx.getSzsmdm(), request);

			mx.setJsjdm(jsjdm); // ���������
			mx.setZqlxdm(szsm.getZqlxdm()); // �������ʹ���
			mx.setSwjgzzjgdm(swjgzzjgdm);
			mx.setNd(nd);
			mx.setCjrq(now);
			mx.setLrrq(now);
			mx.setQxdm(swjgzzjgdm.substring(0, 2));

			sbjkmxList.add(mx);
		}
	}

	// /**
	// * �����걨
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
	// // ���token
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
	// // ��sessionȡ�ýɿ���Ϣ
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
	// System.out.println("============����ǩ�����ݿ�ʼ==============");
	// ///���ǩ��������
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
	// throw new ApplicationException("��֤ǩ��ʧ��");
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
	// System.out.println("============����ǩ�����ݽ���==============");
	// }
	// // end added by qianchao 2005.10.6 */
	// // ���������ɿ�����
	// DeclareInfor declareInforZcjk =
	// createDeclareInfor(zcjkList, CodeConstant.SKLXDM_ZCJK, request,
	// myForm, now);
	// if (declareInforZcjk != null) {
	// declareMap.put("0", declareInforZcjk);
	// }
	//
	// // ���ɲ���Ƿ˰����
	// DeclareInfor declareInforBjqs =
	// createDeclareInfor(bjqsList, CodeConstant.SKLXDM_BJQS, request,
	// myForm, now);
	// if (declareInforBjqs != null) {
	// declareMap.put("1", declareInforBjqs);
	// }
	//
	// // ���������������
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
	// showMsg += "�걨�ɹ�";
	//
	// /**
	// * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� ���пۿ� Start
	// */
	// // ��˰�ѻ������Э��
	// Sfxy sfxy = FriendHelper.getSfxy(request);
	// if (sfxy == null || sfxy.getZh() == null) {
	// this.log("������Э�飺" + userData.getYhid());
	// //do nothing
	// }
	// else { //����Э�黧
	// this.log("����Э��:" + userData.getYhid());
	// String yhdm = sfxy.getYhdm();
	// Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
	// Yh yh = (Yh) yhMap.get(yhdm);
	// if (LWUtil.isLW(request.getSession().getServletContext(),
	// userData.getSsdwdm(),
	// yh.getYhdm())) { // ����������������һƱ��˰
	// //����ۿ���
	// double hjzse = 0.00; //���ս��пۿ�Ľ������
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
	// System.out.println("�����걨����˰�����ۿ���[" + hjzse + "]");
	// try {
	// //����˰Ʊ���벢���������ݿ�
	// System.out.println("============����˰Ʊ���벢���������ݿ⿪ʼ==============");
	// VOPackage vo1 = new VOPackage();
	// vo1.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo1.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
	// vo1.setData(reObject);
	// vo1.setUserData(userData);
	// List ypdsJkss = (List) ShenbaoProxy.getInstance().process(vo1);
	// showMsg += ",����һƱ��˰�ɿ���ɹ�";
	// System.out.println("============����˰Ʊ���벢���������ݿ����==============");
	// //�����걨����˰�����ۿ�ӿ�
	// System.out.println("========�����걨����˰�����ۿ�ӿڿ�ʼ[" + userData.getYhid() +
	// "|" +
	// sfxy.getYhdm() + "|" + sfxy.getZh() +
	// "]===========");
	// try {
	// SKHAdaptor sa = new SKHAdaptor();
	// SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);//��ʱ�޸�
	// sa.transferMoneyFromNsrZhToGk(sfxy.getYhdm()
	// , sfxy.getZh()
	// , jbsj.getSwjgzzjgdm().substring(0, 2)
	// , hjzse
	// , ypdsJkss
	// ,jbsj.getSwjgzzjgdm()
	// , userData);
	// if (callYyaqSignFlag) {
	// System.out.println("============�����걨��ŵ�ǩ����ʼ==============");
	// for (int ii = 0; ii < ypdsJkss.size(); ii++) {
	// YYAQProxy.getInstance().updateSignedDataWithSBBH(calsh,
	// ( (YPDSJKS) ypdsJkss.get(ii)).getSbbh());
	// }
	// System.out.println("============�����걨��ŵ�ǩ������==============");
	// }
	// }
	// catch (Exception e) {
	// //ɾ������
	// System.out.println(
	// "===========˰�����ۿ�ʧ�ܣ����ú�̨����걨���ݿ�ʼ===============");
	// VOPackage vo3 = null;
	// Map obje = (Map) reObject;
	// Iterator keyset = obje.keySet().iterator();
	// Map declares = null;
	// Map voMap = null;
	// String sbbh = null;
	// while (keyset.hasNext()) {
	// sbbh = (String) keyset.next();
	// declares = (Map) obje.get(sbbh);
	// System.out.println("ɾ���걨���Ϊ" + sbbh + "���걨���ݿ�ʼ");
	// voMap = new HashMap();
	// voMap.put(ZhsbMapConstant.SBBH, sbbh);
	// vo3 = new VOPackage();
	// vo3.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo3.setAction(ActionConstant.INT_ACTION_DELETEALL);
	// vo3.setData(voMap);
	// vo3.setUserData(userData);
	// ShenbaoProxy.getInstance().process(vo3);
	// System.out.println("ɾ���걨���Ϊ" + sbbh + "���걨���ݽ���");
	// }
	// log("===========˰�����ۿ�ʧ�ܣ����ú�̨����걨���ݽ���===============");
	// e.printStackTrace();
	// throw ExceptionUtil.getBaseException(e);
	// }
	// showMsg += ",��������Э�黧��ʱ�ۿ��ѳɹ�";
	// System.out.println("========�����걨����˰�����ۿ�ӿ����[" + userData.getYhid() +
	// "|" +
	// sfxy.getYhdm() + "|" + sfxy.getZh() +
	// "]===========");
	// //�������־�ڶ�ʮλ�ۿ�ɹ���־λ
	// log("===========���ú�̨���������־��ʼ===============");
	// VOPackage vo2 = new VOPackage();
	// vo2.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
	// vo2.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
	// vo2.setData(reObject);
	// vo2.setUserData(userData);
	// reObject = ShenbaoProxy.getInstance().process(vo2);
	// showMsg += ",�걨�ɿ�ɹ�";
	// log("===========���ú�̨���������־���===============");
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// throw ExceptionUtil.getBaseException(e);
	// }
	// }
	// }
	// /**
	// * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� End
	// */
	// clearSession(request);
	// request.getSession().setAttribute(SessionKey.JKS, reObject);
	// request.setAttribute("QYSBSuccess", showMsg);
	// return mapping.findForward("Save");
	// }
	// catch (Exception e) {
	// // ���·ŵ�session��,ʹ���汣���û��������Ϣ
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
	 * �����걨
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
		// 0.���token,��ֹ�ظ��ύ
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// 1.�������
		String showMsg = "";
		List zcjkList = null;
		List bjqsList = null;
		List sdjjList = null;
		Map declareMap = new HashMap(3);
		// 2.��ʼ��
        ZhsbForm myForm = (ZhsbForm)form;
        SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // ��ʱ�޸�
        // 3.ҵ�����
        
        //start modified code by qianchao 2006-2-15
        
        // /3.1.��ȡ�û������˻�
        UserData userData = getUserData(request);
        
        boolean callYyaqSignFlag = userData.caflag;
        DzyjsjVO dzyj = null;
        //end   modified code by qianchao 2006-2-15
        
        try
        {
            // /3.0.��sessionȡ�ýɿ���Ϣ(���������ɿ����ݡ�����Ƿ˰�����������ju)
            zcjkList = (List)request.getSession().getAttribute(
                SessionKey.ZCJK_LIST);
            bjqsList = (List)request.getSession().getAttribute(
                SessionKey.BJQS_LIST);
            sdjjList = (List)request.getSession().getAttribute(
                SessionKey.SDJJ_LIST);
            // /3.2.��ȡ��ǰʱ���ǩ
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// /3.3.CA������ǩ��

			// start added by qianchao 2005.10.6
            //started modified by qianchao 2006-2-7

            String strOrginData = request.getParameter("SecX_OrginData");
            String signData = request.getParameter("SecX_SignData");
            if (callYyaqSignFlag){
                String SecX_Error = request.getParameter("SecX_Error");
                if (! "0".equals(SecX_Error))
                {
                    String tempstr;
                    tempstr = "������ǩ������!Error:" + SecX_Error
                    + " SecX_OD " + userData.getYhid() + ":" + strOrginData
                    + "-----SecX_SD:" + signData  + "-----";
                    System.out.println(tempstr);
                    
                    ActionErrors errors = new ActionErrors();
                    errors.add("", new ActionError("error.server.custom", "������ǩ������Error:" + SecX_Error));
                    saveErrors(request, errors);
                    return (new ActionForward(mapping.getInput()));
                }
				System.out.println("============����ǩ�����ݿ�ʼ==============");
				// /���ǩ��������
				try {
//                    dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(
//							userData, strOrginData, signData,
//							codeConstants.YWDM_SB_WS_ZHSB,"0",codeConstants.YWCZLX_NEW);
                    log("=ǩ��=yh��" + userData.yhid + " lsh:" + dzyj.getLsh());
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
				System.out.println("============����ǩ�����ݽ���==============");
			}
            //ended   modified by qianchao 2006-2-7
			// end added by qianchao 2005.10.6 */
			// /3.4.���ɽɿ�����
			// //3.4.0.�����ɿ���������
			DeclareInfor declareInforZcjk = createDeclareInfor(zcjkList,
					CodeConstant.SKLXDM_ZCJK, request, myForm, now);
			if (declareInforZcjk != null) {
				declareMap.put("0", declareInforZcjk);
			}
			// //3.4.1.����Ƿ˰��������
			DeclareInfor declareInforBjqs = createDeclareInfor(bjqsList,
					CodeConstant.SKLXDM_BJQS, request, myForm, now);
			if (declareInforBjqs != null) {
				declareMap.put("1", declareInforBjqs);
			}
			// //3.4.2.���������������
            DeclareInfor declareInforSdjj = createDeclareInfor(sdjjList,
                CodeConstant.SKLXDM_SDJJ, request, myForm, now);
            if(declareInforSdjj != null)
            {
                System.out.println("declareInforSdjj mx size============" +
                                   declareInforSdjj.getSbjkmxInfo().size());
                declareMap.put("2", declareInforSdjj);
            }
			// /3.5.ͨ��˰�ѽӿڻ�ȡ����Э��
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
			// /3.6.���ú�̨ҵ����д���
			// //3.6.0.����ֵ����
			VOPackage vo = new VOPackage();
			HashMap dataMap = new HashMap();
			dataMap.put("sfxy", sfxy);// ����Э��
			dataMap.put("declareMap", declareMap);// �걨��Ϣ
			dataMap.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// ǩ��ԭ��
			dataMap.put("yh", yh);// ���ж���
			dataMap.put("jbsj", jbsj);// �Ǽ�����
			vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_SAVE);
			vo.setData(dataMap);
			vo.setUserData(userData);
			HashMap reMap = null;
			// //3.6.1.����Processor
            
            //start added code by qianchao 2006-2-15
            //�쳣����ȫ��������catchʵ�֡�
			reMap = (HashMap) ShenbaoProxy.getInstance().process(vo);
            //end   added code by qianchao 2006-2-15

            
            // //3.6.2.��ȡ������������
			Object reObject = reMap.get("reObject");
			showMsg = (String) reMap.get("showMsg");
            Boolean sskk = (Boolean)reMap.get("sskk");
			// /3.7.�����ڴ����
			clearSession(request);
			request.getSession().setAttribute(SessionKey.JKS, reObject);
			request.setAttribute("QYSBSuccess", showMsg);
            request.setAttribute("IsSskk",sskk);
			// /3.8.����
			return mapping.findForward("Save");
		} catch (Exception e) {
			// /3.9.���·ŵ�session��,ʹ���汣���û��������Ϣ
            if (callYyaqSignFlag) {
                log("============����ɾ��ǩ����ʼ==============" + dzyj.getLsh());
                try
                {
                    CAProxy.getInstance().deleteSignedData(dzyj);
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
                log("============����ɾ��ǩ������==============" + dzyj.getLsh());
            }
            System.out.println("�����ۺ��걨�쳣��Ϣ: ===== " +  e.getMessage());
            
			request.getSession().setAttribute(SessionKey.ZCJK_LIST, zcjkList);
			request.getSession().setAttribute(SessionKey.BJQS_LIST, bjqsList);
			request.getSession().setAttribute(SessionKey.SDJJ_LIST, sdjjList);
			myForm.setZcjkList(zcjkList);
			myForm.setBjqsList(bjqsList);
			myForm.setSdjjList(sdjjList);
			setBankInfo(request, (ZhsbForm) form);
			// /3.10.�����׳��쳣��ҳ��
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * ȡ��˰��˰Ŀ�ĸ���˰
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
	 * ������˰
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
			// ȡ������ҵ����˰
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_WQ_LIST);
		} else {
			// ȡ������ҵ����˰
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_LIST);
		}

		List fjsList = new ArrayList();

		int j = 0;
		for (; j < additionTax.size(); j++) {
			Szsmyfjs fjs = (Szsmyfjs) additionTax.get(j);

			if (fjs.getSzsmdm().equals(aSzsmdm)) {
				// ��˰�и���˰
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
	 * ����
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
	 * ��session
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
	 * ��ʾ
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
			// ��ü��������
			String jsjdm = getUserData(request).yhid;

			ZhsbForm myForm = (ZhsbForm) form;

			// ����������Ϣ��������ʵ�֣�
			setBankInfo(request, myForm);

			// ���ü��������
			myForm.setJsjdm(jsjdm);

			SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);
			myForm.setDwmc(jbsj.getNsrmc()); // ������˰������
			// �����걨���ڣ���ǰʱ�䣩
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			myForm.setSbrq(sdf.format(new Date()));
			// ȡ˰�ѹ�������
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			Calendar cc = new GregorianCalendar();
			cc.add(Calendar.MONTH, -1);
			int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
			cc.set(Calendar.DAY_OF_MONTH, maxday);
			// �ϸ������һ��
			Date dd = cc.getTime();
			Map sfMap = sfglProxy.getCDFSet(jsjdm, dd, dd, dd);
			// ���ڶ�������
			Map dqdeInfo = getDqdeInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_DQDE));
			// ����˰��Ϣ
			Map fjsInfo = getFjsInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_FJS));
            
         
			// ����������
			Map cftInfo = getCftInfo((List) sfMap
					.get(com.ttsoft.bjtax.sfgl.common.Constant.SFGL_SB_CFT));

			// ���������ɿ�
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
			// ������Ƿ˰
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

			// �����������
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

	// ȡ���ڶ�������
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
	 * ����˰�ѹ���Ķ��ڶ�������
	 *
	 * @param dqdeMap
	 *            ���ڶ�������
	 * @param cftMap
	 *            ������
	 * @param fjsMap
	 *            ����˰
	 * @param zcjkList
	 *            �����ɿ�
	 * @param jsjdm
	 *            ���������
	 */
	private void dealwithSfgl(Map dqdeMap, Map cftMap, Map fjsMap,
			List zcjkList, String jsjdm) {
		for (int i = 0; i < zcjkList.size(); i++) {
			SbjkmxData mxData = (SbjkmxData) zcjkList.get(i);
			String szsmdm = mxData.getSzsmdm();

			String szdm = szsmdm.substring(0, 2);

			// ����˰�ѹ����еĶ��ڶ���
			if (dqdeMap != null) {
				Dqdedlmx1 dqde = (Dqdedlmx1) dqdeMap.get(szsmdm);
				if (dqde != null) {
					String zsfsdm = dqde.getZsfsdm();
					if (zsfsdm != null && zsfsdm.equals("01")) { // ��������
						if (dqde.getSjrd() == null) {
							mxData.setJsje(dqde.getYnsrd()); // ˰��
						} else {
							mxData.setJsje(dqde.getSjrd()); // ˰��
						}
						mxData.setSjse(dqde.getYnsrd()); // ˰��
						mxData.setFromDqde(true);
					} else if (zsfsdm != null && zsfsdm.equals("02")) { // ��������
						mxData.setSl(dqde.getZsl());
					}
				}
			}

			// ����˰�ѹ����еĸ���˰˰�ʣ����������ʣ�
			// if (mxData.isIsFjs() && fjsMap != null)
			if ((szdm.equals("10") || szdm.equals("51")) && fjsMap != null) {
				Tszslmx tszslmx = (Tszslmx) fjsMap.get(szsmdm.substring(0, 2));
				if (tszslmx != null) {
					mxData.setSl(tszslmx.getSl());
				}
			}

			// �����˰����
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

			// ��������
			if (cftMap != null) {
				Cftsyhd cftsyhd = (Cftsyhd) cftMap.get(szsmdm);
				if (cftsyhd != null) {
					mxData.setKssl(cftsyhd.getKssl());
					mxData.setSjse(cftsyhd.getJsje());
					mxData.setJsje(cftsyhd.getSjje());
				}
			}

			// ���������ļ���ϵ��
			if ((SzsmdmConstant.TDSYF + SzsmdmConstant.FCS + SzsmdmConstant.CCSYF)
					.indexOf(szdm) >= 0) {
				com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

				try {
					int jncs = proxy.getJncs(jsjdm, szdm);
					/**
                     * ��������ʹ��˰2007��10��������������
                     * ��2007���ϰ���δ��������˰,�°�������������ȫ��˰��
                     * ���������¿���:
                     * 2007���������ʹ��˰���ɴ���ͳһΪȫ������,�����ɴ���Ϊ0
                     * 2007.10���ڹ���,��˰���϶����ɴ�������
                     * 
                     * ��־�� 2007-8-15�ձ�ע
                     */
					Date today=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                    
                    if(szdm.equals("15")&&sdf.format(today).substring(0,4).equals("2007")){
                    	jncs=0;
                    }
					if (jncs > 0) {
						// ����н��ɴ����Ļ���ʵ��˰��Ҫ����ϵ��0.5
						mxData.setCoefficient("0.5");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * ����
	 *
	 * @param szsmdms
	 *            String[] ˰��˰Ŀ����
	 * @param fjsInfo
	 *            Map ����˰��Ϣ���������
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

		// ������˰���û�ѡ���˰Ŀ��
		for (int i = 0; i < length; i++) {
			String szsmdm = szsmdms[i];

			SbjkmxData mxData = getMxData(szsmdm, request); // ����ǰֵ̨����SbjkmxData

			mxDataList.add(mxData);
		}

		filterFjs(mxDataList, request);

		// ȡ�ø���˰��Ϣ
		List additionalTax = getAdditionalTax(mxDataList, fjsInfo, request);

		if (additionalTax != null) {
			mxDataList.addAll(additionalTax);
		}

		return mxDataList;
	}

	/**
	 * ����û�ѡ��ĸ���˰����˰Ҳ���û�ѡ��֮�еĻ������˵�
	 *
	 * @param mxDataList
	 *            �û�ѡ���˰Ŀ��ϸ����
	 * @param request
	 *            HttpServletRequest
	 * @throws BaseException
	 */
	private void filterFjs(List mxDataList, HttpServletRequest request)
			throws BaseException {
		Map szsmMap = CodeTableUtil.getCodeTableMap(request,
				CodeTable.SZSM_MAP_AVAILABLE);

		List children = new ArrayList(); // ���Ҫ���˵���˰Ŀ
		for (int i = 0; i < mxDataList.size(); i++) {
			SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
			Szsm szsm = (Szsm) szsmMap.get(mxData.getSzsmdm());

			if (szsm != null && szsm.getSffjs() != null
					&& szsm.getSffjs().equals("2")) { // ����Ǹ���˰�Ļ�
				// �ҳ��������˰����˰s
				List fathers = getFather(szsm.getSzsmdm(), request);
				for (int j = 0; j < mxDataList.size(); j++) {
					SbjkmxData mx = (SbjkmxData) mxDataList.get(j);
					if (fathers.contains(mx.getSzsmdm())) {
						// �����˰��mxDataList�У���������Ӵ�����
						children.add(szsm.getSzsmdm());
					}
				}
			}
		}

		// �������¼�����ĺ��Ӷ�ɾ��
		for (int i = mxDataList.size() - 1; i >= 0; i--) {
			SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
			String szsmdm = mxData.getSzsmdm();
			if (children.contains(szsmdm)) {
				mxDataList.remove(i);
			}
		}
	}

	/**
	 * ȡ������˰��������˰
	 *
	 * @param fjs
	 *            ����˰��˰Ŀ����
	 * @param request
	 *            HttpServletRequest
	 * @return ��˰List
	 * @throws BaseException
	 */
	private List getFather(String fjs, HttpServletRequest request)
			throws BaseException {
		List additionTax = null;

		List fathers = new ArrayList();

		// �����ڡ�������ҵȡ����˰
		String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
		Djzclx djzclx = (Djzclx) CodeTableUtil.getCodeTableMap(request,
				CodeTable.DJZCLX_MAP).get(djzclxdm);
		if (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT)
				|| djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_WZ)) {
			// ����
			additionTax = CodeTableUtil.getCodeTableList(request,
					CodeTable.SZSMFJS_WQ_LIST);
		} else {
			// ����
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
	 * ȡ����ϸ����
	 *
	 * @param szsmdm
	 *            String ˰��˰Ŀ����
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
