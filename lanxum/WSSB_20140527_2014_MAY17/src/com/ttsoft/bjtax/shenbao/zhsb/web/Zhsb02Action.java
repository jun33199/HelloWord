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
 * 综合申报
 */
public abstract class Zhsb02Action
    extends DcBaseAction {
    protected class BankInfo {
        public String accountNumber; // 帐号

        public String bankID; // 银行代码

        public String bankName; // 银行名称
    }

    private String errorMessage = "";

    protected boolean validate(Zhsb02VO vo) {
        if (!vo.getYwlx().equals(CAcodeConstants.DADM_SB_WS_ZHSB)) {
            errorMessage = "业务类型错误，不能执行业务操作。";
            return false;
        }
        if (! (vo.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) ||
               vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
            errorMessage = "业务操作类型错误，不能执行业务操作。";
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
     * 验证两个数据是否一致 如果不一致会抛出前台用户操作数据XXX与后台数据不匹配的异常
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
            throw ExceptionUtil.getBaseException(new Exception("用户提交数据\"" +
                errorStr + "\"与后台数据不匹配!"));
        }

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
    protected DeclareInfor createDeclareInfor(List mxDataList, String sklx,
                                            HttpServletRequest request,
                                            ZhsbForm form,
                                            Timestamp now, Zhsb02VO xvo) throws
        Exception {
    	if (mxDataList == null || mxDataList.size() == 0) {
            throw new Exception("明细条目为空，无法生成申报缴款数据。");
        }

        String[] szsmdm = getSzsmdm(xvo, sklx);
        List voMxDataList = new ArrayList();
        if (szsmdm == null || szsmdm.length == 0) {
            throw new Exception("税种税目为空，无法生成申报缴款数据。");
        }
        if (mxDataList.size() != szsmdm.length) {
            // 用户删除了税目
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

        // ?是否可以优化
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
                        // 如果按课税数量计的话，设置课税数量
                        mxData.setKssl(new BigDecimal(kssl[j]));
                    }
                    mxData.setJsje(new BigDecimal(jsje[j])); // 计税金额
                    mxData.setSjse(new BigDecimal(sjse[j])); // 实缴税额
                    mxData.setSbfs(sbfs[j]);
                    sum = sum.add(mxData.getSjse()); // 求合计
                    break;
                }
            }
        }

        List sbjkmxList = new ArrayList(size);

        // 将前台值对象转换为后台值对象
        convert(voMxDataList, sbjkmxList, now, request);
        form.setJkyhzh( ( (Sbsj02VO) xvo.getSbsj().get(0)).getZh());
        form.setYhdm( ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhdm());
        form.setYhmc( ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhmc());
        // **************************************************************************

        // 取税务机关组织机构代码表
        Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.SWJJZZJG_MAP);

        // 取得登记信息
        SWDJJBSJ djjbsj = FriendHelper.getSWDJJBSJ(request);

        // 生成缴款主表
        Sbjkzb zb = new Sbjkzb();

        // 对主表进赋值

        BankInfo bankInfo = getPaymentBankInfo(request, form); // 检测银行信息
        if (bankInfo == null) { // 用户提交的数据中没有银行信息
            zb.setYhdm(null);
            zb.setYhmc(null);
            zb.setZh(null);
        }
        else {
            String yhdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhdm();
            zb.setYhdm(yhdm); // 设置银行代码
            String yhmc = ( (Sbsj02VO) xvo.getSbsj().get(0)).getYhmc();
            zb.setYhmc(yhmc); // 设置银行名称
            String zh = ( (Sbsj02VO) xvo.getSbsj().get(0)).getZh();
            zb.setZh(zh); // 设置帐号
        }

        zb.setSjly(CodeConstant.SJLY_SB_SBLR); // 数据来源

        String jsjdm = ( (Nsrxx02VO) xvo.getNsrxx()).getJsjdm(); // 获得前台传递过来的计算机代码
        if (checkValues(jsjdm, ( (UserData) getUserData(request)).yhid, "计算机代码")) {
            zb.setJsjdm(jsjdm); // 设置计算机代码
        }

        String swjgzzjgdm = ( (Nsrxx02VO) xvo.getNsrxx()).getSwjgzzjgdm(); // 获得前台传递过来的税务机关组织机构代码
        if (checkValues(swjgzzjgdm, djjbsj.getSwjgzzjgdm(), "税务机关组织机构代码")) {
            zb.setSwjgzzjgdm(swjgzzjgdm); // 设置税务机关组织机构代码
        }

        String sklxdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getSklxdm(); // 获得前台传递过来的税款类型代码
        if (checkValues(sklxdm, sklx, "税款类型代码")) {
            zb.setSklxdm(sklxdm); // 设置税款类型代码
        }

        String zsswjgzzjgdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).
            getZsswjgzzjgdm(); // 获得前台传递过来的征收机关代码
        if (checkValues(zsswjgzzjgdm, djjbsj.getSwjgzzjgdm(), "征收机关代码")) {
            zb.setZsswjgzzjgdm(zsswjgzzjgdm); // 设置征收机关代码
        }

        String gkzzjgdm = ( (Sbsj02VO) xvo.getSbsj().get(0)).getGkzzjgdm(); // 获得前台传递过来的国库组织机构代码

        if (checkValues(gkzzjgdm,
                        ( (Swjgzzjg) swjgzzjgMap.get(zb.getSwjgzzjgdm())).getGkjhh(),
                        "国库组织机构代码")) {
            zb.setGkzzjgdm(gkzzjgdm); // 设置国库组织机构代码
        }

        String lxdh = ( (Sbsj02VO) xvo.getSbsj().get(0)).getLxdh(); // 获得前台传递过来的联系电话
        zb.setJydzlxdm(lxdh); // 设置联系电话

        zb.setFsdm(xvo.getSbxx().getFsdm()); // 方式代码
        zb.setLsgxdm(djjbsj.getLsgxdm()); // 隶属关系代码
        zb.setDjzclxdm(djjbsj.getDjzclxdm()); // 登记注册类型代码
        zb.setGjbzhydm(djjbsj.getGjbzhydm()); // 国家标准行业代码
        zb.setLrrq(now); // 录入日期
        zb.setSbrq(TinyTools.second2Day(now)); // 申报日期
        zb.setZyrq(zb.getSbrq()); // 帐页日期
        zb.setSjje(sum); // 实缴金额
        zb.setRkje(sum); // 入库金额
        zb.setLrr(jsjdm); // 录入人代码
        zb.setCjrq(now); // 创建时间
        zb.setNd( (new SimpleDateFormat("yyyy")).format(now));
        zb.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));

        return setDeclareInforDetail(new DeclareInfor(zb, sbjkmxList), request);
    }

    abstract protected DeclareInfor setDeclareInforDetail(DeclareInfor
        declareInfor, HttpServletRequest request);

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
                         HttpServletRequest request) throws
        BaseException {
        String jsjdm = ( (UserData) getUserData(request)).yhid;

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
        }
        else {
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
    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) {
        clearSession(request);

        return "Return";
    }

    /**
     * 清session
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
     * 将旧的VO对象转换为XML-VO对象。
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
        // 获得系统当前日期
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);
        Map lsgxMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.LSGX_MAP);
        Map djzclxMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.DJZCLX_MAP);

        Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request,
            CodeTable.SWJJZZJG_MAP);

        // XML文档信息
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0, 8));
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        // 信息
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        // 企业所得税季报
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();
        List smlist = new ArrayList();
        sbsj = new Sbsj02VO();
        //sbsj.setSklx("正常");
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
        // 三方协议的设置银行信息，非三方协议的不用设置
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
     * 将旧的VO对象转换为XML-VO对象。
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
        // 获得系统当前日期
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // XML文档信息
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
            CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0, 8));
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        // 信息
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        sbxx.setSbxgrq(sdf.format(curDate));

        // 企业所得税季报
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
                    if (printTag == CodeConstant.PRINT_YPYS) { // 一票一税
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
                    else { // 一票多税
                        List tempList = (List) decMap.get(ZhsbMapConstant.SBSJ);
                        for (int j = 0; j < tempList.size(); j++) {
                            Jks02VO ajks = new Jks02VO();
                            DeclareInfor tempObj = (DeclareInfor) tempList.get(
                                j); // 获得一条科目对应的申报数据
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
                // 转向申报录入页面
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
            
            // 获得计算机代码
            String jsjdm = ( (UserData) getUserData(request)).yhid;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            ZhsbForm myForm = new ZhsbWithoutSfxyForm();
            // 设置银行信息（由子类实现）
            setBankInfo(request, myForm);

            // 设置计算机代码
            myForm.setJsjdm(jsjdm);

            myForm.setDwmc(jbsj.getNsrmc()); // 设置纳税人名称
            myForm.setSbrq(sdf.format(new Date()));
            // 取税费管理数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.
                bjtax.sfgl.proxy.ServiceProxy();
            Calendar cc = new GregorianCalendar();
            cc.add(Calendar.MONTH, -1);
            int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
            cc.set(Calendar.DAY_OF_MONTH, maxday);
            // 上个月最后一天
            Date dd = cc.getTime();
            Map sfMap = sfglProxy.getCDFSet(jsjdm, dd, dd, dd);
            // 定期定额数据
            Map dqdeInfo = getDqdeInfo( (List) sfMap.get(com.ttsoft.bjtax.sfgl.
                common.Constant.SFGL_SB_DQDE));
            // 附加税信息
            Map fjsInfo = getFjsInfo( (List) sfMap.get(com.ttsoft.bjtax.sfgl.
                common.Constant.SFGL_SB_FJS));

            // 车房土定额
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
                log("request中无zcjk数据。\r\n" + tmpstr);
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
                //System.out.println("!@####################。。。:"+request.getSession().getAttribute(SessionKey.SDJJ_FJS_INFO));
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
                log("request中无sdjj数据。\r\n" + tmpstr);
            }

            xvo = convert2XMLVO(request, myForm, jbsj);
            String tmpxml = xvo.toXML();
            log(tmpxml);
            // tmpxml = "<?xml version=\"1.0\"

            // 查询已申报数据
            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_QUERYYSB);
            vo.setData(null);
            UserData ud = (UserData)this.getUserData(request);
            vo.setUserData(ud);
            ArrayList reList = null;
            // //3.6.1.调用Processor

            // 异常处理全部由最后的catch实现。
            reList = (ArrayList) ShenbaoProxy.getInstance().process(vo);
            if (reList.size() > 0) {
                myForm.setByysbList(reList);
                session.setAttribute("ZHSBFORM", myForm);
                // 转向已申报数据页面
                return CAConstants.QUERY;
            }
            else {
                // 转向申报录入页面
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml); // encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[010002]]></ywlx><ywczlx><![CDATA[1]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[北京市面粉八厂2]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbxx><fsdm><![CDATA[5]]></fsdm></sbxx><sbsj><sskkbz><![CDATA[0]]></sskkbz><lwzt><![CDATA[0]]></lwzt><yhdm><![CDATA[01]]></yhdm><yhmc><![CDATA[testyhzh]]></yhmc><zh><![CDATA[12345631]]></zh><sbrq><![CDATA[2006-09-13]]></sbrq><sbbh><![CDATA[]]></sbbh><sklxdm><![CDATA[100]]></sklxdm><sklx><![CDATA[正常]]></sklx><gkzzjgdm><![CDATA[045]]></gkzzjgdm><gkzzjgmc><![CDATA[北京市海淀代理支库]]></gkzzjgmc><zsswjgzzjgdm><![CDATA[0601]]></zsswjgzzjgdm><zsswjgzzjgmc><![CDATA[海淀区地方税务局知春里所]]></zsswjgzzjgmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[海淀区地方税务局知春里所]]></swjgzzjgmc><djzclxmc><![CDATA[国有企业]]></djzclxmc><lxdh><![CDATA[68181330]]></lxdh><lsgxmc><![CDATA[市属]]></lsgxmc><sfkyzf><![CDATA[1]]></sfkyzf><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[021101]]></szsmdm><szmc><![CDATA[营业税]]></szmc><szsmmc><![CDATA[铁路运输]]></szsmmc><sl><![CDATA[0.03]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[30.00]]></jsje><sjse><![CDATA[0.90]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[100010]]></szsmdm><szmc><![CDATA[城市维护建设税]]></szmc><szsmmc><![CDATA[营业税]]></szsmmc><sl><![CDATA[0.07]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[40.90]]></jsje><sjse><![CDATA[2.86]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem><smitem><jkpzh><![CDATA[]]></jkpzh><aksslj><![CDATA[0]]></aksslj><szsmdm><![CDATA[510010]]></szsmdm><szmc><![CDATA[教育费附加]]></szmc><szsmmc><![CDATA[营业税]]></szsmmc><sl><![CDATA[0.03]]></sl><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[50.90]]></jsje><sjse><![CDATA[1.53]]></sjse><asljbs><![CDATA[0]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem></sbsj></taxdoc>";
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                     xvo.getXsltVersion());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                     xvo.getSchemaVersion());
                return CAConstants.SHOW;
            }
        }
        catch (Exception ex) {
            // /3.9.设置空xml，转向失败页面
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
     *            正常
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
                        }
                        else {
                            mxData.setJsje(dqde.getSjrd()); // 税基
                        }
                        mxData.setSjse(dqde.getYnsrd()); // 税额
                        mxData.setFromDqde(true);
                    }
                    else if (zsfsdm != null && zsfsdm.equals("02")) { // 定率征收
                        mxData.setSl(dqde.getZsl());
                    }
                }
            }

            // 处理税费管理中的附加税税率（特殊征收率）
            // if (mxData.isIsFjs() && fjsMap != null)
            if ( (szdm.equals("10") || szdm.equals("51")) && fjsMap != null) {
                Tszslmx tszslmx = (Tszslmx) fjsMap.get(szsmdm.substring(0, 2));
                if (tszslmx != null) {
                    mxData.setSl(tszslmx.getSl());
                }
            }

            // 处理个税定额
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
            if ( (SzsmdmConstant.TDSYF + SzsmdmConstant.FCS +
                  SzsmdmConstant.CCSYF).indexOf(szdm) >= 0) {
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy = new com.ttsoft.
                    bjtax.sfgl.proxy.ServiceProxy();

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
                    Date today = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                    if (szdm.equals("15") &&
                        sdf.format(today).substring(0, 4).equals("2007")) {
                        jncs = 0;
                    }
                    if (jncs > 0) {
                        // 如果有缴纳次数的话，实缴税额要乘以系数0.5
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
    private void filterFjs(List mxDataList, HttpServletRequest request) throws
        BaseException {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request,
            CodeTable.SZSM_MAP_AVAILABLE);

        List children = new ArrayList(); // 存放要过滤调的税目
        for (int i = 0; i < mxDataList.size(); i++) {
            SbjkmxData mxData = (SbjkmxData) mxDataList.get(i);
            Szsm szsm = (Szsm) szsmMap.get(mxData.getSzsmdm());

            if (szsm != null && szsm.getSffjs() != null &&
                szsm.getSffjs().equals("2")) { // 如果是附加税的话
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
    private List getFather(String fjs, HttpServletRequest request) throws
        BaseException {
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
        }
        else {
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

    /**
     * 根据税种税目代码取得税种税目对象
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
                log("request中无法取得税种税目数据。\r\n" + tmpstr);
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
