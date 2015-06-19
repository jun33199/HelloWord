package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.*;

import com.syax.bjtax.ca.proxy.*;
import com.syax.bjtax.ca.util.*;
import com.syax.bjtax.ca.vo.*;
import com.syax.bjtax.ca.xml.util.*;
import com.syax.common.util.*;
import com.syax.common.web.action.*;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.model.*;
import com.ttsoft.bjtax.sfgl.model.orobj.*;
import com.ttsoft.bjtax.shenbao.codetable.web.*;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.bjtax.shenbao.model.client.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.bjtax.shenbao.util.JspUtil;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.zhsb.*;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.*;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * �ۺ��걨
 */
public abstract class Zhsb02Action
    extends DcBaseAction {
    protected class BankInfo {
        public String accountNumber; // �ʺ�

        public String bankID; // ���д���

        public String bankName; // ��������
    }

    private String errorMessage = "";

    protected boolean validate(Zhsb02VO vo) {
        if (!vo.getYwlx().equals(CAcodeConstants.DADM_SB_WS_ZHSB)) {
            errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
            return false;
        }
        if (! (vo.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) ||
               vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
            errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
            return false;
        }

        return true;
    }

    private String[] getSzsmdm(Zhsb02VO xvo, String sklx) {
        Sbsj02VO sbsj = (Sbsj02VO) xvo.getSbsj().get(0);
        List l = sbsj.getSmitem();
        String[] smdm = new String[l.size()];

        if (sklx.equals(CodeConstant.SKLXDM_ZCJK)) {
            for (int i = 0; i < l.size(); i++) {
                smdm[i] = ( (Smitem02VO) l.get(i)).getSzsmdm();
            }
            return smdm;
        }
        
        if (sklx.equals(CodeConstant.SKLXDM_SDJJ)) {
            for (int i = 0; i < l.size(); i++) {
                smdm[i] = ( (Smitem02VO) l.get(i)).getSzsmdm();
            }
            return smdm;
        }

        return null;
    }

    public String[] getValues(Zhsb02VO xvo, String vname, String sklx) {
        Sbsj02VO sbsj = (Sbsj02VO) xvo.getSbsj().get(0);
        List l = sbsj.getSmitem();
        String[] va = new String[l.size()];
        
        if (sklx.equals(CodeConstant.SKLXDM_ZCJK)) 
        {
        	if (vname.equals("kssl")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getKssl();
                }
            }
            else if (vname.equals("jsje")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getJsje();
                }
            }
            else if (vname.equals("sjse")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getSjse();
                }
            }

            return va;
        }
        
        if (sklx.equals(CodeConstant.SKLXDM_SDJJ)) 
        {
        	if (vname.equals("kssl")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getKssl();
                }
            }
            else if (vname.equals("jsje")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getJsje();
                }
            }
            else if (vname.equals("sjse")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getSjse();
                }
            }
            else if (vname.equals("sbfs")) {
                for (int i = 0; i < l.size(); i++) {
                    va[i] = ( (Smitem02VO) l.get(i)).getSbfs();
                }
            }

            return va;
        }
        
        return null;
    }
    
    public String[] getValuesOld(Zhsb02VO xvo, String vname, String sklx) {
        Sbsj02VO sbsj = (Sbsj02VO) xvo.getSbsj().get(0);
        List l = sbsj.getSmitem();
        String[] va = new String[l.size()];
        if (vname.equals("kssl")) {
            for (int i = 0; i < l.size(); i++) {
                va[i] = ( (Smitem02VO) l.get(i)).getKssl();
            }
        }
        else if (vname.equals("jsje")) {
            for (int i = 0; i < l.size(); i++) {
                va[i] = ( (Smitem02VO) l.get(i)).getJsje();
            }
        }
        else if (vname.equals("sjse")) {
            for (int i = 0; i < l.size(); i++) {
                va[i] = ( (Smitem02VO) l.get(i)).getSjse();
            }
        }

        return va;
    }

    /**
     * ��֤���������Ƿ�һ�� �����һ�»��׳�ǰ̨�û���������XXX���̨���ݲ�ƥ����쳣
     *
     */

    private boolean checkValues(String str1, String str2, String errorStr) throws
        BaseException {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if ( (str1.trim()).equals( (str2.trim()))) {
            return true;
        }
        else {
            throw ExceptionUtil.getBaseException(new Exception("�û��ύ����\"" +
                errorStr + "\"���̨���ݲ�ƥ��!"));
        }

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
    protected DeclareInfor createDeclareInfor(List mxDataList, String sklx,
                                            HttpServletRequest request,
                                            ZhsbForm form,
                                            Timestamp now, Zhsb02VO xvo) throws
        Exception {
    	if (mxDataList == null || mxDataList.size() == 0) {
            throw new Exception("��ϸ��ĿΪ�գ��޷������걨�ɿ����ݡ�");
        }

        String[] szsmdm = getSzsmdm(xvo, sklx);
        List voMxDataList = new ArrayList();
        if (szsmdm == null || szsmdm.length == 0) {
            throw new Exception("˰��˰ĿΪ�գ��޷������걨�ɿ����ݡ�");
        }
        if (mxDataList.size() != szsmdm.length) {
            // �û�ɾ����˰Ŀ
            for (int i = mxDataList.size() - 1; i >= 0; i--) {
                for (int j = 0; j < szsmdm.length; j++) {
                    SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
                    if (mxData.getSzsmdm().equals(szsmdm[j])) {
                        voMxDataList.add(mxData);
                        break;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < mxDataList.size(); i++) {
                voMxDataList.add(mxDataList.get(i));
            }
        }

        // ?�Ƿ�����Ż�
        String[] kssl = getValues(xvo, "kssl", sklx);
        String[] jsje = getValues(xvo, "jsje", sklx);
        String[] sjse = getValues(xvo, "sjse", sklx);
        String[] sbfs = getValues(xvo, "sbfs", sklx);

//        for(int i=0; i<kssl.length; i++)
//        {System.out.println("[doShow]----------------A------------------:"+kssl[i]);}
//        System.out.println("[doShow>>>>>>>>>]");
//        for(int i=0; i<jsje.length; i++)
//        {System.out.println("[doShow]----------------B------------------:"+jsje[i]);}
//        System.out.println("[doShow]>>>>>>>>");
//        for(int i=0; i<sjse.length; i++)
//        {System.out.println("[doShow]----------------C------------------:"+sjse[i]);}
        
        BigDecimal sum = new BigDecimal("0");

        int size = voMxDataList.size();
        for (int i = 0; i < size; i++) {
            SbjkmxData mxData = (SbjkmxData) voMxDataList.get(i);
            for (int j = 0; j < kssl.length; j++) {
                if (mxData.getSzsmdm().equals(szsmdm[j])) {
                    if (mxData.isAksslj()) {
                        // �������˰�����ƵĻ������ÿ�˰����
                        mxData.setKssl(new BigDecimal(kssl[j]));
                    }
                    mxData.setJsje(new BigDecimal(jsje[j])); // ��˰���
                    mxData.setSjse(new BigDecimal(sjse[j])); // ʵ��˰��
                    mxData.setSbfs(sbfs[j]);
                    sum = sum.add(mxData.getSjse()); // ��ϼ�
                    break;
                }
            }
        }

        List sbjkmxList = new ArrayList(size);

        // ��ǰֵ̨����ת��Ϊ��ֵ̨����
        convert(voMxDataList, sbjkmxList, now, request);
        form.setJkyhzh( ( (Sbsj02VO) xvo.getSbsj().get(0)).getZh());
        form.setYhdm( ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhdm());
        form.setYhmc( ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhmc());
        // **************************************************************************

        // ȡ˰�������֯���������
        Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.SWJJZZJG_MAP);

        // ȡ�õǼ���Ϣ
        SWDJJBSJ djjbsj = FriendHelper.getSWDJJBSJ(request);

        // ���ɽɿ�����
        Sbjkzb zb = new Sbjkzb();

        // ���������ֵ

        BankInfo bankInfo = getPaymentBankInfo(request, form); // ���������Ϣ
        if (bankInfo == null) { // �û��ύ��������û��������Ϣ
            zb.setYhdm(null);
            zb.setYhmc(null);
            zb.setZh(null);
        }
        else {
            String yhdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhdm();
            zb.setYhdm(yhdm); // �������д���
            String yhmc = ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhmc();
            zb.setYhmc(yhmc); // ������������
            String zh = ( (Sbsj02VO) xvo.getSbsj().get(0)).getZh();
            zb.setZh(zh); // �����ʺ�
        }

        zb.setSjly(CodeConstant.SJLY_SB_SBLR); // ������Դ

        String jsjdm = ( (Nsrxx02VO) xvo.getNsrxx()).getJsjdm(); // ���ǰ̨���ݹ����ļ��������
        if (checkValues(jsjdm, ( (UserData) getUserData(request)).yhid, "���������")) {
            zb.setJsjdm(jsjdm); // ���ü��������
        }

        String swjgzzjgdm = ( (Nsrxx02VO) xvo.getNsrxx()).getSwjgzzjgdm(); // ���ǰ̨���ݹ�����˰�������֯��������
        if (checkValues(swjgzzjgdm, djjbsj.getSwjgzzjgdm(), "˰�������֯��������")) {
            zb.setSwjgzzjgdm(swjgzzjgdm); // ����˰�������֯��������
        }

        String sklxdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getSklxdm(); // ���ǰ̨���ݹ�����˰�����ʹ���
        if (checkValues(sklxdm, sklx, "˰�����ʹ���")) {
            zb.setSklxdm(sklxdm); // ����˰�����ʹ���
        }

        String zsswjgzzjgdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).
            getZsswjgzzjgdm(); // ���ǰ̨���ݹ��������ջ��ش���
        if (checkValues(zsswjgzzjgdm, djjbsj.getSwjgzzjgdm(), "���ջ��ش���")) {
            zb.setZsswjgzzjgdm(zsswjgzzjgdm); // �������ջ��ش���
        }

        String gkzzjgdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getGkzzjgdm(); // ���ǰ̨���ݹ����Ĺ�����֯��������

        if (checkValues(gkzzjgdm,
                        ( (Swjgzzjg) swjgzzjgMap.get(zb.getSwjgzzjgdm())).getGkjhh(),
                        "������֯��������")) {
            zb.setGkzzjgdm(gkzzjgdm); // ���ù�����֯��������
        }

        String lxdh = ( (Sbsj02VO) xvo.getSbsj().get(0)).getLxdh(); // ���ǰ̨���ݹ�������ϵ�绰
        zb.setJydzlxdm(lxdh); // ������ϵ�绰

        zb.setFsdm(xvo.getSbxx().getFsdm()); // ��ʽ����
        zb.setLsgxdm(djjbsj.getLsgxdm()); // ������ϵ����
        zb.setDjzclxdm(djjbsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
        zb.setGjbzhydm(djjbsj.getGjbzhydm()); // ���ұ�׼��ҵ����
        zb.setLrrq(now); // ¼������
        zb.setSbrq(TinyTools.second2Day(now)); // �걨����
        zb.setZyrq(zb.getSbrq()); // ��ҳ����
        zb.setSjje(sum); // ʵ�ɽ��
        zb.setRkje(sum); // �����
        zb.setLrr(jsjdm); // ¼���˴���
        zb.setCjrq(now); // ����ʱ��
        zb.setNd( (new SimpleDateFormat("yyyy")).format(now));
        zb.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));

        return setDeclareInforDetail(new DeclareInfor(zb, sbjkmxList), request);
    }

    abstract protected DeclareInfor setDeclareInforDetail(DeclareInfor
        declareInfor, HttpServletRequest request);

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
                         HttpServletRequest request) throws
        BaseException {
        String jsjdm = ( (UserData) getUserData(request)).yhid;

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
                                  HttpServletRequest request) throws
        BaseException {
        ArrayList result = new ArrayList();

        int size = mxDataList.size();
        for (int i = 0; i < size; i++) {
            String aSzsmdm = ( (SbjkmxData) mxDataList.get(i)).getSzsmdm();
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
        }
        else {
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
                    if ( ( (SbjkmxData) result.get(k)).getSzsmdm().equals(fjs.
                        getFjsszsmdm())) {
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
    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) {
        clearSession(request);

        return "Return";
    }

    /**
     * ��session
     *
     * @param request
     *            HttpServletRequest
     */
    protected void clearSession(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionKey.ZCJK_LIST);
        request.getSession().removeAttribute(SessionKey.ZCJK_FJS_INFO);
        request.getSession().removeAttribute(SessionKey.BJQS_LIST);
        request.getSession().removeAttribute(SessionKey.BJQS_FJS_INFO);
        request.getSession().removeAttribute(SessionKey.SDJJ_LIST);
        request.getSession().removeAttribute(SessionKey.SDJJ_FJS_INFO);
        request.getSession().removeAttribute("ZHSBFORM");
    }

    abstract protected void setBankInfo(HttpServletRequest request,
                                        ZhsbForm form) throws BaseException;

    abstract protected BankInfo getPaymentBankInfo(HttpServletRequest request,
        ZhsbForm form) throws BaseException;

    /**
     * ���ɵ�VO����ת��ΪXML-VO����
     *
     * @param qysdsjd
     * @param djJbsj
     * @return
     * @throws BaseException
     * @throws ApplicationException
     * @throws ApplicationException
     */
    protected Zhsb02VO convert2XMLVO(HttpServletRequest request, ZhsbForm vo1,
                                   SWDJJBSJ djJbsj) throws BaseException,
        ApplicationException {
    	
        List zcjkList = vo1.getZcjkList();
        List sdjjList = vo1.getSdjjList();
        
        List jkList = new ArrayList();
        
        if(zcjkList != null && zcjkList.size() >0)
        {
        	jkList.addAll(zcjkList);
        }
        if(sdjjList != null && sdjjList.size() >0)
        {
        	jkList.addAll(sdjjList);
        }

        Zhsb02VO vo = new Zhsb02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        // ���ϵͳ��ǰ����
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);
        Map lsgxMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.LSGX_MAP);
        Map djzclxMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.DJZCLX_MAP);

        Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.SWJJZZJG_MAP);

        // XML�ĵ���Ϣ
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0, 8));
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // ���VO��
        // ��˰����Ϣ��
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        // ��Ϣ
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        // ��ҵ����˰����
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();
        List smlist = new ArrayList();
        sbsj = new Sbsj02VO();
        //sbsj.setSklx("����");
        //sbsj.setSklxdm("100");
        
        if(zcjkList != null && zcjkList.size() >0)
        {
        	sbsj.setSklx(CodeConstant.SKLXMC_ZCJK);
        	sbsj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
        }
        if(sdjjList != null && sdjjList.size() >0)
        {
        	sbsj.setSklx(CodeConstant.SKLXMC_DZJK);
        	sbsj.setSklxdm(CodeConstant.SKLXDM_SDJJ);
        }
        sbsj.setSbrq(vo1.getSbrq());
        sbsj.setYhmc(vo1.getYhmc());
        sbsj.setYhdm(vo1.getYhdm());
        sbsj.setZh(vo1.getJkyhzh());
        sbsj.setZsswjgzzjgdm(djJbsj.getSwjgzzjgdm());
        sbsj.setZsswjgzzjgmc( ( (Swjgzzjg) swjgzzjgMap.get(djJbsj.getSwjgzzjgdm())).
                             getSwjgzzjgmc());
        sbsj.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        sbsj.setSwjgzzjgmc( ( (Swjgzzjg) swjgzzjgMap.get(djJbsj.getSwjgzzjgdm())).
                           getSwjgzzjgmc());
        sbsj.setDjzclxmc( ( (Djzclx) djzclxMap.get(djJbsj.getDjzclxdm())).
                         getDjzclxmc());
        sbsj.setLxdh(djJbsj.getJydzlxdm());

        sbsj.setLsgxmc( ( (Lsgx) lsgxMap.get(djJbsj.getLsgxdm())).getLsgxmc());
        sbsj.setGkzzjgdm( ( (Swjgzzjg) swjgzzjgMap.get(djJbsj.getSwjgzzjgdm())).
                         getGkjhh());
        sbsj.setGkzzjgmc( ( (Swjgzzjg) swjgzzjgMap.get(djJbsj.getSwjgzzjgdm())).
                         getSkgk());
        // ����Э�������������Ϣ��������Э��Ĳ�������
        // Sfxy sfxy=FriendHelper.getYhkkSfxy(request);
        UserData ud = (UserData) request.getSession(false).getAttribute(
            "UserData");
        boolean isCA = ud.caflag;
        boolean isZhOK = false;
        boolean lwFlag = false;

        sbsj.setSskkbz(CAUtils.bool2Str(false));
        if (sfxy != null) {
            isZhOK = ud.getSsdwdm().equals(sfxy.getSwjgzzjgdm());
            lwFlag = LWUtil.isLW(request.getSession().getServletContext(),
                                 ud.getSsdwdm(), sfxy.getYhdm());
            if (lwFlag && isCA) {
                if (isZhOK) {
                    sbsj.setSskkbz(CAUtils.bool2Str(true));
                }
            }
        }
        sbsj.setSfkyzf(CAUtils.bool2Str(true));
        sbsj.setLwzt(CAUtils.bool2Str(LWUtil.isLW(request.getSession().
                                                  getServletContext(),
                                                  ud.getSsdwdm(), vo1
                                                  .getYhdm())));

        for (int i = 0; i < jkList.size(); i++) {
            SbjkmxData mxData = (SbjkmxData) jkList.get(i);
            Smitem02VO sm = new Smitem02VO();
            sm.setAksslj(CAUtils.bool2Str(mxData.isAksslj()));
            sm.setAsljbs(mxData.getAsljbs());
            sm.setCoefficient(mxData.getCoefficient());
            // System.out.println("zcjk:"+ i + " =" + mxData.getJsje() + " =" +
            // mxData.getKssl() + " =" + mxData.getSjse()+ " =" );
            sm.setJsje(StringUtils.bigDeciaml2String(mxData.getJsje(), "0.00"));
            sm.setKssl(StringUtils.bigDeciaml2String(mxData.getKssl(), "0.00"));
            sm.setSjse(StringUtils.bigDeciaml2String(mxData.getSjse(), "0.00"));
            sm.setSl(StringUtils.bigDeciaml2WholeString(mxData.getSl(), "0.00"));

            sm.setSzmc(mxData.getSzmc());
            sm.setSzsmdm(mxData.getSzsmdm());
            sm.setSzsmmc(mxData.getSzsmmc());
            smlist.add(sm);
        }
        sbsj.setSmitem(smlist);
        sbsjlist.add(sbsj);
        vo.setSbsj(sbsjlist);

        return vo;
    }

    /**
     * ���ɵ�VO����ת��ΪXML-VO����
     *
     * @param qysdsjd
     * @param djJbsj
     * @return
     * @throws BaseException
     * @throws ApplicationException
     */
    protected Zhsb02VO convert2XMLJksVO(HttpServletRequest request, Map jkdata,
                                      SWDJJBSJ djJbsj, Boolean sskk,
                                      Sbjkzb sbzb, boolean lwFlag, String sklxdm) throws
        BaseException, ApplicationException {
        Zhsb02VO vo = new Zhsb02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        // ���ϵͳ��ǰ����
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // XML�ĵ���Ϣ
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0, 8));
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // ���VO��
        // ��˰����Ϣ��
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        // ��Ϣ
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        sbxx.setSbxgrq(sdf.format(curDate));

        // ��ҵ����˰����
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();
        if (jkdata != null) {
            for (Iterator keyset = jkdata.keySet().iterator(); keyset.hasNext(); ) {
                String key = (String) keyset.next();
                HashMap aDeclaration = (HashMap) jkdata.get(key);
                Boolean tmp = (Boolean) aDeclaration.get(ZhsbMapConstant.ZFBZ);
                int printTag = ( (Integer) aDeclaration.get(ZhsbMapConstant.
                    PRINTTAG)).intValue();

                JspUtil.completeZbInfo(request, sbzb);
                List smlist = new ArrayList();
                List jkslist = new ArrayList();
                sbsj = new Sbsj02VO();
                if(sklxdm != null && sklxdm.endsWith(CodeConstant.SKLXDM_ZCJK))
                {
                	sbsj.setSklx(CodeConstant.SKLXMC_ZCJK);
                    sbsj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
                }
                if(sklxdm != null && sklxdm.endsWith(CodeConstant.SKLXDM_SDJJ))
                {
                	sbsj.setSklx(CodeConstant.SKLXMC_DZJK);
                    sbsj.setSklxdm(CodeConstant.SKLXDM_SDJJ);
                }
                sbsj.setSbrq(sdf.format(sbzb.getSbrq()));
                sbsj.setYhmc(sbzb.getYhmc());
                sbsj.setZh(sbzb.getZh());
                sbsj.setYhdm(sbzb.getYhdm());
                sbsj.setSskkbz(CAUtils.bool2Str(sskk.booleanValue()));
                sbsj.setLwzt(CAUtils.bool2Str(lwFlag));
                sbsj.setSbbh(key);
                sbsj.setGkzzjgdm(sbzb.getGkzzjgdm());
                sbsj.setGkzzjgmc(sbzb.getGkzzjgmc());
                sbsj.setZsswjgzzjgdm(sbzb.getZsswjgzzjgdm());
                sbsj.setZsswjgzzjgmc(sbzb.getZsswjgzzjgmc());
                sbsj.setSwjgzzjgdm(sbzb.getSwjgzzjgdm());
                sbsj.setSwjgzzjgmc(sbzb.getSwjgzzjgmc());
                sbsj.setDjzclxmc(sbzb.getDjzclxmc());
                sbsj.setLxdh(sbzb.getJydzlxdm());
                sbsj.setLsgxmc(sbzb.getLsgxmc());

                boolean zfFlag = true;
                if (tmp != null) {
                    zfFlag = tmp.booleanValue();
                }
                else {
                    String zwbs = (String) aDeclaration.get(ZhsbMapConstant.
                        ZWBS);
                    String subFirstZwbs = zwbs.substring(0, 1);
                    String subEndZwbs = zwbs.substring(19);
                    int flag = Integer.parseInt(subFirstZwbs + subEndZwbs);
                    zfFlag = (flag == 0);
                }
                sbsj.setSfkyzf(CAUtils.bool2Str(zfFlag));

                Set jkshSet = aDeclaration.keySet();
                Iterator jkshIter = jkshSet.iterator();

                int count = 0;
                while (jkshIter.hasNext()) {
                    count++;
                    String jksh = (String) jkshIter.next();

                    if (jksh.equals(ZhsbMapConstant.SBRQ) ||
                        jksh.equals(ZhsbMapConstant.SKLX)
                        || jksh.equals(ZhsbMapConstant.PRINTTAG) ||
                        jksh.equals(ZhsbMapConstant.ZWBS)
                        || jksh.equals(ZhsbMapConstant.ZFBZ)) {
                        continue;
                    }

                    Map decMap = (Map) aDeclaration.get(jksh);
                    if (printTag == CodeConstant.PRINT_YPYS) { // һƱһ˰
                        Jks02VO ajks = new Jks02VO();

                        Sbjkzb zb = (Sbjkzb) ( (DeclareInfor) decMap.get(
                            ZhsbMapConstant.SBSJ)).getSbjkzb();
                        List mxList = ( (DeclareInfor) decMap.get(
                            ZhsbMapConstant.SBSJ)).getSbjkmxInfo();

                        JspUtil.completeZbInfo(request, zb);
                        ajks.setJkpzh(zb.getJkpzh());
                        ajks.setSzdm(zb.getSzdm());
                        ajks.setSzmc(zb.getSzmc());
                        ajks.setYskmdm(zb.getYskmdm());
                        ajks.setYskmmc(zb.getYskmmc());
                        ajks.setYsjcmc(zb.getYsjcmc());
                        ajks.setSjje(StringUtils.bigDeciaml2String(zb.getSjje(),
                            "0.00"));
                        ajks.setSkssksrq(sdf.format(zb.getSkssksrq()));
                        ajks.setSkssjsrq(sdf.format(zb.getSkssjsrq()));
                        ajks.setXjrq(sdf.format(zb.getXjrq()));
                        ajks.setBz(zb.getBz());
                        String zbzwbs = zb.getZwbs();
                        String subFirstZwbs = zbzwbs.substring(0, 1);
                        String subEndZwbs = zbzwbs.substring(19);
                        int flag = Integer.parseInt(subFirstZwbs + subEndZwbs);
                        ajks.setSfkyzf(CAUtils.bool2Str(false));
                        if (flag == 0) {
                            ajks.setSfkyzf(CAUtils.bool2Str(true));
                            ajks.setKkzt(CodeConstant.KKZT_ZF);

                        }
                        else if (flag > 0 && flag <= 2) {
                            ajks.setKkzt(CodeConstant.KKZT_YJK);
                        }
                        else if (flag == 9) {
                            ajks.setKkzt(CodeConstant.KKZT_DDKK);
                        }
                        else if (flag >= 10) {
                            ajks.setKkzt(CodeConstant.KKZT_YRK);
                        }
                        else {
                            ajks.setKkzt(CodeConstant.KKZT_QT);
                        }

                        jkslist.add(ajks);

                        for (int i = 0; i < mxList.size(); i++) {
                            Sbjkmx mxData = (Sbjkmx) mxList.get(i);
                            Smitem02VO sm = new Smitem02VO();
                            sm.setJkpzh(mxData.getJkpzh());
                            sm.setSzsmdm(mxData.getSzsmdm());
                            sm.setSzmc(zb.getSzmc());

                            sm.setSzsmmc(getSzsm(mxData.getSzsmdm(), request).
                                         getSzsmmc());
                            sm.setJsje(StringUtils.bigDeciaml2String(mxData.
                                getJsje(), "0.00"));
                            sm.setKssl(StringUtils.bigDeciaml2String(mxData.
                                getKssl(), "0.00"));
                            sm.setSjse(StringUtils.bigDeciaml2String(mxData.
                                getSjse(), "0.00"));
                            sm.setSl(StringUtils.bigDeciaml2WholeString(mxData.
                                getSl(), "0.00"));
                            smlist.add(sm);
                        }

                    }
                    else { // һƱ��˰
                        List tempList = (List) decMap.get(ZhsbMapConstant.SBSJ);
                        for (int j = 0; j < tempList.size(); j++) {
                            Jks02VO ajks = new Jks02VO();
                            DeclareInfor tempObj = (DeclareInfor) tempList.get(
                                j); // ���һ����Ŀ��Ӧ���걨����
                            Sbjkzb zb = (Sbjkzb) tempObj.getSbjkzb();
                            List mxList = tempObj.getSbjkmxInfo();

                            JspUtil.completeZbInfo(request, zb);
                            ajks.setJkpzh(zb.getJkpzh());
                            ajks.setSzdm(zb.getSzdm());
                            ajks.setSzmc(zb.getSzmc());
                            ajks.setYskmdm(zb.getYskmdm());
                            ajks.setYskmmc(zb.getYskmmc());
                            ajks.setYsjcmc(zb.getYsjcmc());
                            ajks.setSjje(StringUtils.bigDeciaml2String(zb.
                                getSjje(), "0.00"));
                            ajks.setSkssksrq(sdf.format(zb.getSkssksrq()));
                            ajks.setSkssjsrq(sdf.format(zb.getSkssjsrq()));
                            ajks.setXjrq(sdf.format(zb.getXjrq()));
                            ajks.setBz(zb.getBz());
                            String zbzwbs = zb.getZwbs();
                            String subFirstZwbs = zbzwbs.substring(0, 1);
                            String subEndZwbs = zbzwbs.substring(19);
                            int flag = Integer.parseInt(subFirstZwbs +
                                subEndZwbs);
                            ajks.setSfkyzf(CAUtils.bool2Str(false));
                            if (flag == 0) {
                                ajks.setSfkyzf(CAUtils.bool2Str(true));
                                ajks.setKkzt(CodeConstant.KKZT_ZF);

                            }
                            else if (flag > 0 && flag <= 2) {
                                ajks.setKkzt(CodeConstant.KKZT_YJK);
                            }
                            else if (flag == 9) {
                                ajks.setKkzt(CodeConstant.KKZT_DDKK);
                            }
                            else if (flag >= 10) {
                                ajks.setKkzt(CodeConstant.KKZT_YRK);
                            }
                            else {
                                ajks.setKkzt(CodeConstant.KKZT_QT);
                            }

                            jkslist.add(ajks);

                            for (int i = 0; i < mxList.size(); i++) {
                                Sbjkmx mxData = (Sbjkmx) mxList.get(i);
                                Smitem02VO sm = new Smitem02VO();
                                sm.setJkpzh(mxData.getJkpzh());
                                sm.setSzsmdm(mxData.getSzsmdm());
                                sm.setSzmc(zb.getSzmc());

                                sm.setSzsmmc(getSzsm(mxData.getSzsmdm(),
                                    request).getSzsmmc());
                                sm.setJsje(StringUtils.bigDeciaml2String(mxData.
                                    getJsje(), "0.00"));
                                sm.setKssl(StringUtils.bigDeciaml2String(mxData.
                                    getKssl(), "0.00"));
                                sm.setSjse(StringUtils.bigDeciaml2String(mxData.
                                    getSjse(), "0.00"));
                                sm.setSl(StringUtils.bigDeciaml2WholeString(
                                    mxData.getSl(), "0.00"));
                                smlist.add(sm);
                            }
                        }
                    }
                }

                sbsj.setSmitem(smlist);
                sbsj.setJks(jkslist);

                sbsjlist.add(sbsj);
            }
        }
        vo.setSbsj(sbsjlist);

        return vo;
    }

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
    public String doShow(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {
        Zhsb02VO xvo = new Zhsb02VO();
        try {
            System.out.println("[doShow]----------------------------------");
            //System.out.println("[doShow]----------------------------------:"+request.getParameterValues("sklxdm")[0]);
            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);
            HttpSession session = request.getSession();
            if (session.getAttribute("ZHSBFORM") != null) {
                ZhsbForm form = (ZhsbWithoutSfxyForm) session.getAttribute(
                    "ZHSBFORM");
                xvo = convert2XMLVO(request, form, jbsj);
                String tmpxml = xvo.toXML();
                log(tmpxml);
                // ת���걨¼��ҳ��
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                     xvo.getXsltVersion());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                     xvo.getSchemaVersion());
                session.removeAttribute("ZHSBFORM");
                return CAConstants.SHOW;
            }
            
            List zcjkList = null;
            List sdjjList = null;
            
            // ��ü��������
            String jsjdm = ( (UserData) getUserData(request)).yhid;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            ZhsbForm myForm = new ZhsbWithoutSfxyForm();
            // ����������Ϣ��������ʵ�֣�
            setBankInfo(request, myForm);

            // ���ü��������
            myForm.setJsjdm(jsjdm);

            myForm.setDwmc(jbsj.getNsrmc()); // ������˰������
            myForm.setSbrq(sdf.format(new Date()));
            // ȡ˰�ѹ�������
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.
                bjtax.sfgl.proxy.ServiceProxy();
            Calendar cc = new GregorianCalendar();
            cc.add(Calendar.MONTH, -1);
            int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
            cc.set(Calendar.DAY_OF_MONTH, maxday);
            // �ϸ������һ��
            Date dd = cc.getTime();
            Map sfMap = sfglProxy.getCDFSet(jsjdm, dd, dd, dd);
            // ���ڶ�������
            Map dqdeInfo = getDqdeInfo( (List) sfMap.get(com.ttsoft.bjtax.sfgl.
                common.Constant.SFGL_SB_DQDE));
            // ����˰��Ϣ
            Map fjsInfo = getFjsInfo( (List) sfMap.get(com.ttsoft.bjtax.sfgl.
                common.Constant.SFGL_SB_FJS));

            // ����������
            Map cftInfo = getCftInfo( (List) sfMap.get(com.ttsoft.bjtax.sfgl.
                common.Constant.SFGL_SB_CFT));

            // String[] zcjk = myForm.getZcjk();
            String[] zcjk = request.getParameterValues("zcjk");
            String[] sdjj = request.getParameterValues("sdjj");
            
            myForm.setZcjk(zcjk);
            myForm.setSdjj(sdjj);
            if (zcjk != null) {
//           	System.out.println("!@####################:"+request.getParameterValues("zcjk").length);
//            	for(int i=0; i<zcjk.length; i++)
//                {System.out.println("[doShow]----------------1------------------:"+zcjk[i]);}
            	
                Map zcjkFjsInfo = new HashMap();
                zcjkList = dealwithSzsm(zcjk, zcjkFjsInfo, request);
                dealwithSfgl(dqdeInfo, cftInfo, fjsInfo, zcjkList, jsjdm);
                myForm.setZcjkList(zcjkList);
                request.getSession().setAttribute(SessionKey.ZCJK_FJS_INFO,
                                                  zcjkFjsInfo);
                request.getSession().setAttribute(SessionKey.ZCJK_LIST,
                                                  zcjkList);
            }
            else {
                StringBuffer tmpstr = new StringBuffer();
                String namestr = "";
                String pvs[] = null;
                Enumeration em = request.getParameterNames();
                while (em.hasMoreElements()) {
                    namestr = (String) em.nextElement();
                    pvs = request.getParameterValues(namestr);
                    for (int i = 0; i < pvs.length; i++) {
                        tmpstr.append(namestr).append(" : ").append(pvs[i]).
                            append("\r\n");
                    }
                }
                tmpstr.append("jsjdm : ").append(jsjdm).append("\r\n");
                log("request����zcjk���ݡ�\r\n" + tmpstr);
            }
            
            if (sdjj != null) {
//            	System.out.println("!@####################:"+request.getParameterValues("sdjj").length);
//            	for(int i=0; i<sdjj.length; i++)
//                {System.out.println("[doShow]----------------2------------------:"+sdjj[i]);}
            	
                Map sdjjFjsInfo = new HashMap();
                sdjjList = dealwithSzsm(sdjj, sdjjFjsInfo, request);
                dealwithSfgl(dqdeInfo, cftInfo, fjsInfo, sdjjList, jsjdm);
                myForm.setSdjjList(sdjjList);
                request.getSession().setAttribute(SessionKey.SDJJ_FJS_INFO,
                		sdjjFjsInfo);
                request.getSession().setAttribute(SessionKey.SDJJ_LIST,
                		sdjjList);
                //System.out.println("!@####################������:"+request.getSession().getAttribute(SessionKey.SDJJ_FJS_INFO));
            }
            else {
                StringBuffer tmpstr = new StringBuffer();
                String namestr = "";
                String pvs[] = null;
                Enumeration em = request.getParameterNames();
                while (em.hasMoreElements()) {
                    namestr = (String) em.nextElement();
                    pvs = request.getParameterValues(namestr);
                    for (int i = 0; i < pvs.length; i++) {
                        tmpstr.append(namestr).append(" : ").append(pvs[i]).
                            append("\r\n");
                    }
                }
                tmpstr.append("jsjdm : ").append(jsjdm).append("\r\n");
                log("request����sdjj���ݡ�\r\n" + tmpstr);
            }

            xvo = convert2XMLVO(request, myForm, jbsj);
            String tmpxml = xvo.toXML();
            log(tmpxml);
            // tmpxml = "<?xml version=\"1.0\"

            // ��ѯ���걨����
            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_QUERYYSB);
            vo.setData(null);
            UserData ud = (UserData)this.getUserData(request);
            vo.setUserData(ud);
            ArrayList reList = null;
            // //3.6.1.����Processor

            // �쳣����ȫ��������catchʵ�֡�
            reList = (ArrayList) ShenbaoProxy.getInstance().process(vo);
            if (reList.size() > 0) {
                myForm.setByysbList(reList);
                session.setAttribute("ZHSBFORM", myForm);
                // ת�����걨����ҳ��
                return CAConstants.QUERY;
            }
            else {
                // ת���걨¼��ҳ��
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml); // encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[010002]]></ywlx><ywczlx><![CDATA[1]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[��������۰˳�2]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbxx><fsdm><![CDATA[5]]></fsdm></sbxx><sbsj><sskkbz><![CDATA[0]]></sskkbz><lwzt><![CDATA[0]]></lwzt><yhdm><![CDATA[01]]></yhdm><yhmc><![CDATA[testyhzh]]></yhmc><zh><![CDATA[12345631]]></zh><sbrq><![CDATA[2006-09-13]]></sbrq><sbbh><![CDATA[]]></sbbh><sklxdm><![CDATA[100]]></sklxdm><sklx><![CDATA[����]]></sklx><gkzzjgdm><![CDATA[045]]></gkzzjgdm><gkzzjgmc><![CDATA[�����к������֧��]]></gkzzjgmc><zsswjgzzjgdm><![CDATA[0601]]></zsswjgzzjgdm><zsswjgzzjgmc><![CDATA[�������ط�˰���֪������]]></zsswjgzzjgmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[�������ط�˰���֪������]]></swjgzzjgmc><djzclxmc><![CDATA[������ҵ]]></djzclxmc><lxdh><![CDATA[68181330]]></lxdh><lsgxmc><![CDATA[����]]></lsgxmc><sfkyzf><![CDATA[1]]></sfkyzf><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[021101]]></szsmdm><szmc><![CDATA[Ӫҵ˰]]></szmc><szsmmc><![CDATA[��·����]]></szsmmc><sl><![CDATA[0.03]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[30.00]]></jsje><sjse><![CDATA[0.90]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[100010]]></szsmdm><szmc><![CDATA[����ά������˰]]></szmc><szsmmc><![CDATA[Ӫҵ˰]]></szsmmc><sl><![CDATA[0.07]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[40.90]]></jsje><sjse><![CDATA[2.86]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[510010]]></szsmdm><szmc><![CDATA[�����Ѹ���]]></szmc><szsmmc><![CDATA[Ӫҵ˰]]></szsmmc><sl><![CDATA[0.03]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[50.90]]></jsje><sjse><![CDATA[1.53]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem></sbsj></taxdoc>";
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                     xvo.getXsltVersion());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                     xvo.getSchemaVersion());
                return CAConstants.SHOW;
            }
        }
        catch (Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 xvo.getSchemaVersion());
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
     *            ����
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
                        }
                        else {
                            mxData.setJsje(dqde.getSjrd()); // ˰��
                        }
                        mxData.setSjse(dqde.getYnsrd()); // ˰��
                        mxData.setFromDqde(true);
                    }
                    else if (zsfsdm != null && zsfsdm.equals("02")) { // ��������
                        mxData.setSl(dqde.getZsl());
                    }
                }
            }

            // ����˰�ѹ����еĸ���˰˰�ʣ����������ʣ�
            // if (mxData.isIsFjs() && fjsMap != null)
            if ( (szdm.equals("10") || szdm.equals("51")) && fjsMap != null) {
                Tszslmx tszslmx = (Tszslmx) fjsMap.get(szsmdm.substring(0, 2));
                if (tszslmx != null) {
                    mxData.setSl(tszslmx.getSl());
                }
            }

            // �����˰����
            if (szsmdm.equals(SzsmdmConstant.GRSDS_GXDE)) {
                try {
                    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.
                        ttsoft.bjtax.sfgl.proxy.ServiceProxy();

                    Calendar cc = new GregorianCalendar();
                    cc.add(Calendar.MONTH, -1);
                    cc.set(Calendar.DAY_OF_MONTH,
                           cc.getActualMaximum(Calendar.DAY_OF_MONTH));

                    BigDecimal gshj = proxy.getGrtszygsdeHj(jsjdm, cc.getTime());
                    if (gshj != null) {
                        mxData.setJsje(gshj);
                        mxData.setSjse(gshj);
                        mxData.setFromDqde(true);
                    }
                }
                catch (Exception ex) {
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
            if ( (SzsmdmConstant.TDSYF + SzsmdmConstant.FCS +
                  SzsmdmConstant.CCSYF).indexOf(szdm) >= 0) {
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.ttsoft.
                    bjtax.sfgl.proxy.ServiceProxy();

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
                    Date today = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                    if (szdm.equals("15") &&
                        sdf.format(today).substring(0, 4).equals("2007")) {
                        jncs = 0;
                    }
                    if (jncs > 0) {
                        // ����н��ɴ����Ļ���ʵ��˰��Ҫ����ϵ��0.5
                        mxData.setCoefficient("0.5");
                    }
                }
                catch (Exception ex) {
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
    private void filterFjs(List mxDataList, HttpServletRequest request) throws
        BaseException {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request,
            CodeTable.SZSM_MAP_AVAILABLE);

        List children = new ArrayList(); // ���Ҫ���˵���˰Ŀ
        for (int i = 0; i < mxDataList.size(); i++) {
            SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
            Szsm szsm = (Szsm) szsmMap.get(mxData.getSzsmdm());

            if (szsm != null && szsm.getSffjs() != null &&
                szsm.getSffjs().equals("2")) { // ����Ǹ���˰�Ļ�
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
    private List getFather(String fjs, HttpServletRequest request) throws
        BaseException {
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
        }
        else {
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

    /**
     * ����˰��˰Ŀ����ȡ��˰��˰Ŀ����
     *
     * @param szsmdm
     *            String
     * @param request
     *            HttpServletRequest
     * @return Szsm
     */
    protected Szsm getSzsm(String szsmdm, HttpServletRequest request) {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
        Szsm tmpsz = (Szsm) szsmMap.get(szsmdm);
        if (tmpsz == null) {
            try {
                StringBuffer tmpstr = new StringBuffer();
                String namestr = "";
                String pvs[] = null;
                Enumeration em = request.getParameterNames();
                while (em.hasMoreElements()) {
                    namestr = (String) em.nextElement();
                    pvs = request.getParameterValues(namestr);
                    for (int i = 0; i < pvs.length; i++) {
                        tmpstr.append(namestr).append(" : ").append(pvs[i]).
                            append("\r\n");
                    }
                }
                tmpstr.append("szsmdm : ").append(szsmdm).append("\r\n");
                String jsjdm = ( (UserData) getUserData(request)).getYhid();
                tmpstr.append("jsjdm : ").append(jsjdm).append("\r\n");
                log("request���޷�ȡ��˰��˰Ŀ���ݡ�\r\n" + tmpstr);
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        }
        return tmpsz;
    }

    protected void log(String str) {
        if (ApplicationConstant.DEBUG_FLAG) {
            System.out.println("[WSSB DEBUG]" + (new Date()) + str);
        }
    }

}
