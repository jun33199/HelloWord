package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.JspUtil;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Jks02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Nsrxx02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbxx02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Smitem02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Zhsb02VO;
import com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo.NsrxxVO;
import com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo.NxxmVO;
import com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo.SbsjVO;
import com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo.ZyJks02VO;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

public class ListJks02Action extends DcBaseAction
{

    public ListJks02Action()
    {
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        // 检查权限
        if (!SbzlAccess.getAuthority(SbzlAccess.LISTJKS, request))
        {
 	           return CAConstants.NOPERMISSION;
        } 

        return null;
    }

    protected int getActionID()
    {
        return SbzlAccess.LISTJKS;
    }

    public String doListJks(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        request.getSession().removeAttribute(SessionKey.YPDS_MAP);
        request.getSession().removeAttribute(SessionKey.YPDS_LIST);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, request.getSession().getAttribute(SessionKey.CA_XML_DATA));
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, request.getSession().getAttribute(SessionKey.CA_XML_XSLT_VERSION));
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, request.getSession().getAttribute(
                SessionKey.CA_XML_SCHEMA_VERSION));
        log("back list jks:" + request.getAttribute("CA_XML_DATA"));

        return "Show";
    }

    public String doPrint(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {

        String sbbh = request.getParameter("sbbhIndex");
        HttpSession session = request.getSession();
        ZyJks02VO zyjks = new ZyJks02VO();
        HashMap ypdsMap = (HashMap)session.getAttribute(SessionKey.YPDS_MAP);
        if (ypdsMap == null)
        {
            try
            {
                VOPackage vo = new VOPackage();
                vo.setAction(ActionConstant.INT_ACTION_PRINT);
                vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
                HashMap map = new HashMap(1);
                map.put(ZhsbMapConstant.SBBH, sbbh);
                map.put("WSSB_SWJGZZJGDM", FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());
                vo.setData(map);
                ypdsMap = (HashMap) ShenbaoProxy.getInstance().process(vo);


                session.setAttribute(SessionKey.YPDS_MAP, ypdsMap);
            }
            catch (Exception ex)
            {
                // /3.9.设置空xml，转向失败页面
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, zyjks.toXML());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, zyjks.getXsltVersion());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, zyjks.getSchemaVersion());
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
        }
        
        UserData userData = (UserData) session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
        // 取得登记Map
        Map djMap = null;
        try
        {
            djMap = com.ttsoft.bjtax.shenbao.util.FriendHelper.getDjInfo(request);
        }
        catch (com.ttsoft.framework.exception.BaseException e)
        {
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, zyjks.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, zyjks.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, zyjks.getSchemaVersion());
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        // 登记基本数据
        com.ttsoft.bjtax.dj.model.SWDJJBSJ jbsj = (com.ttsoft.bjtax.dj.model.SWDJJBSJ) djMap.get("JBSJ");
        zyjks = convert2XMLVO(request, ypdsMap, jbsj, userData);
        String tmpxml = zyjks.toXML();
        log(tmpxml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, zyjks.getXsltVersion());
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, zyjks.getSchemaVersion());

        return "PrintYpds";
    }

    private ZyJks02VO convert2XMLVO(HttpServletRequest request, HashMap ypdsMap, SWDJJBSJ jbsj, UserData userData)
    {
        ZyJks02VO zyjks = new ZyJks02VO();
        NsrxxVO nsrxx = new NsrxxVO();
        SbsjVO sbsj = new SbsjVO();

        List ypdsList = (List) ypdsMap.get(ZhsbMapConstant.SBSJ);
        List szList = this.getPrintList(ypdsList, request);
        List nxxmlist = new ArrayList();
        List sbsjlist = new ArrayList();
        Map swjgzzjgMap = (Map) CodeTableUtil.getCodeTableMap(request, CodeTable.SWJJZZJG_MAP);
        // 填充VO。
        // 纳税人信息。

        nsrxx.setJsjdm(userData.yhid);
        nsrxx.setNsrmc(jbsj.getNsrmc());

        String zzjgdm = jbsj.getSwjgzzjgdm();
        Map zzjgMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SWJJZZJG_MAP);
        Swjgzzjg swjgzzjg = (Swjgzzjg) zzjgMap.get(zzjgdm);

        sbsj.setZwbs((String) ypdsMap.get(ZhsbMapConstant.ZWBS));
        sbsj.setYhmc((String) ypdsMap.get(ZhsbMapConstant.YHMC));
        sbsj.setZh((String) ypdsMap.get(ZhsbMapConstant.YHZH));
        sbsj.setXjrq((String) ypdsMap.get("xjrq"));
        String sbrq = com.ttsoft.bjtax.shenbao.util.TinyTools.Date2String((java.sql.Timestamp) ypdsMap
                .get(ZhsbMapConstant.SBRQ), "yyyyMMdd");
        String sbrqPrint = sbrq.substring(0, 4) + "年" + sbrq.substring(4, 6) + "月" + sbrq.substring(6, 8)
                + "日";
        sbsj.setSbrq(sbrqPrint);
        sbsj.setSbbh((String) ypdsMap.get(ZhsbMapConstant.SBBH));
        sbsj.setSkgk(swjgzzjg.getSkgk());
        sbsj.setGkzzjgdm(((Swjgzzjg) swjgzzjgMap.get(jbsj.getSwjgzzjgdm())).getGkjhh());
        sbsj.setSwjgzzjgmc(swjgzzjg.getSwjgzzjgmc());
        sbsj.setDjzclxmc(jbsj.getDjzclxmc());
        sbsj.setLxdh(jbsj.getJydzlxdm());
        sbsj.setLsgxmc(jbsj.getLsgxmc());

        java.math.BigDecimal hjsjje = new java.math.BigDecimal(0.00);
        for (int i = 0; i < szList.size(); i++)
        {
            NxxmVO nxxm = new NxxmVO();
            Map szMap = (Map) szList.get(i);
            nxxm.setNxxmmc((String) szMap.get("NSXM"));
            // nxxm.setSjje(StringUtils.bigDeciaml2String((java.math.BigDecimal)szMap.get("SJJE"),
            // "0.00"));
            java.math.BigDecimal sjje = (java.math.BigDecimal) szMap.get("SJJE");
            nxxm.setSjje(JspUtil.format(sjje));
            hjsjje = sjje.add(hjsjje);
            nxxmlist.add(nxxm);
        }
        sbsj.setHjsjje(JspUtil.format(hjsjje));
        
        String hjjjedx = com.ttsoft.framework.util.Currency.convert(Double.parseDouble(JspUtil.format(hjsjje))); 
        if(hjjjedx!=null && hjjjedx.length()>0 && hjjjedx.startsWith("计")){
        	hjjjedx = "人民币"+hjjjedx.substring(1);
        }
        sbsj.setHjsjjedx(hjjjedx);

        sbsj.setNxxm(nxxmlist);
        sbsjlist.add(sbsj);

        zyjks.setNsrxx(nsrxx);
        zyjks.setSbsj(sbsjlist);
        
        //此处的xslt和schema没用，随便写。
        zyjks.setSchemaVersion("20060401");
        zyjks.setXsltVersion("20060401");
        zyjks.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        zyjks.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        return zyjks;

    }

    private List getPrintList(List ypdsList, HttpServletRequest request)
    {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
        List tempPrintList = new ArrayList();
        for (int i = 0; i < ypdsList.size(); i++)
        {
            List tempList = (List) ypdsList.get(i);
            for (int j = 0; j < tempList.size(); j++)
            {
                Sbjkmx mx = (Sbjkmx) tempList.get(j);
                tempPrintList.add(mx);
            }
        }
        String szdm = "";
        String yskmdm = "";
        List szdmList = new ArrayList();
        for (int i = 0; i < tempPrintList.size(); i++)
        {
            Sbjkmx mx = (Sbjkmx) tempPrintList.get(i);
            String tempSzdm = mx.getSzsmdm().substring(0, 2);
            String tempYskmdm = mx.getYskmdm();
            if (!(szdm.equals(tempSzdm) && yskmdm.equals(tempYskmdm)))
            {
                szdmList.add(tempSzdm + tempYskmdm);
            }
            szdm = tempSzdm;
            yskmdm = tempYskmdm;
        }
        List retList = new ArrayList();
        for (int i = 0; i < szdmList.size(); i++)
        {
            String szdmStr = (String) szdmList.get(i);
            java.math.BigDecimal sjje = new java.math.BigDecimal(0.00);
            String nsxm = "";
            for (int j = 0; j < tempPrintList.size(); j++)
            {
                Sbjkmx mx = (Sbjkmx) tempPrintList.get(j);
                String tempSzdm = mx.getSzsmdm().substring(0, 2);
                //if (szdmStr != null && szdmStr.equals(tempSzdm))
                String tempYskmdm = mx.getYskmdm();
                //System.out.println("tempSzdm == : " + tempSzdm);
                if (szdmStr != null && szdmStr.equals(tempSzdm + tempYskmdm))
                {
                    sjje = sjje.add(mx.getSjse());
                    Szsm szsmTmp = (Szsm) szsmMap.get(tempSzdm);
                    nsxm = tempSzdm + " " + szsmTmp.getSzsmmc();
                    if (tempSzdm.endsWith("91") || tempSzdm.endsWith("92"))
                    {
                        nsxm += "(滞纳金、罚款)";
                    }
                }
            }
            Map printData = new HashMap();
            printData.put("NSXM", nsxm);
            printData.put("SJJE", sjje);
            retList.add(printData);
        }
        return retList;
    }

    public String doViewOne(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        DeclareInfor declareInfor = null;
        int i;
        Zhsb02VO zhsb02vo = new Zhsb02VO();

        try
        {
            String jksh = request.getParameter("jkshIndex");
            String sbbh = request.getParameter("sbbhIndex");
            HashMap jksMap = (HashMap) request.getSession().getAttribute(SessionKey.JKS);
            HashMap jks = (HashMap) (jksMap.get(sbbh));
            
            Integer printTag = (Integer) jks.get(ZhsbMapConstant.PRINTTAG);
            if (printTag.intValue() == CodeConstant.PRINT_YPYS)
            {
                HashMap jks_jksh = (HashMap) ((HashMap) jksMap.get(sbbh)).get(jksh);
                declareInfor = (DeclareInfor) jks_jksh.get(ZhsbMapConstant.SBSJ);
            }
            else
            {
                HashMap jks_jksh = (HashMap) ((HashMap) jksMap.get(sbbh)).get(jksh.substring(0,jksh.length()-1));
                List jkslist = (List)jks_jksh.get(ZhsbMapConstant.SBSJ);
                for (i = 0; i < jkslist.size(); i++)
                {
                    declareInfor = (DeclareInfor) jkslist.get(i);
                    if (declareInfor.getSbjkzb().getJkpzh().equals(jksh))
                    {
                        break;
                    }
                }
                if (i > jkslist.size())
                {
                    throw new ApplicationException("找不到缴款书：" + jksh);
                }
            }
            Sbjkzb zb = declareInfor.getSbjkzb();
            JspUtil.completeZbInfo(request, zb);
            List mxList = declareInfor.getSbjkmxInfo();
            
            // 一票一税
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            zhsb02vo = convert2XMLVO(request, zb, djJbsj, mxList, false);
            String tmpxml = zhsb02vo.toXML();
            log(tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, zhsb02vo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, zhsb02vo.getSchemaVersion());
            return "ViewYPYS";

        }
        catch (Exception ex)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, zhsb02vo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, zhsb02vo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, zhsb02vo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {

        Zhsb02VO xvo = new Zhsb02VO();
        try
        {
            // 查询本期申报未缴款的数据
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
            String jsjdm = ud.yhid;
            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // 临时修改
            HashMap map = new HashMap();
            map.put(ZhsbMapConstant.JSJDM, jsjdm);
            map.put(ZhsbMapConstant.WHRQ, (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            map.put("WSSB_SWJGZZJGDM", FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());
            vo.setData(map);
            HashMap jks = (HashMap) ShenbaoProxy.getInstance().process(vo);
            request.getSession().setAttribute(SessionKey.JKS, jks);

            xvo = convert2XMLListVO(request, (Map) jks, jbsj, Boolean.valueOf(false));
            String tmpxml = xvo.toXML();
            //log(tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            request.getSession().setAttribute(SessionKey.CA_XML_DATA, tmpxml);
            request.getSession().setAttribute(SessionKey.CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.getSession().setAttribute(SessionKey.CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());

            return CAConstants.SHOW;
        }
        catch (Exception ex)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 将旧的VO对象转换为XML-VO对象。
     * 
     * @param qysdsjd
     * @param djJbsj
     * @return
     * @throws BaseException
     */
    private Zhsb02VO convert2XMLVO(HttpServletRequest request, Sbjkzb zb, SWDJJBSJ djJbsj, List mxList, boolean lwFlag)
            throws BaseException
    {
        Zhsb02VO zhsb02vo = new Zhsb02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Jks02VO jks = new Jks02VO();
        List jkslist = new ArrayList();
        List smlist = new ArrayList();
        List sbsjlist = new ArrayList();
        // 获得系统当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
        YPDSJKS ypdsjks = new YPDSJKS();
        // 信息
        sbxx.setFsdm(zb.getFsdm());
        // 申报数据
        sbsj.setLwzt(lwFlag + "");
        sbsj.setYhdm(zb.getYhdm());
        sbsj.setYhmc(zb.getYhmc());
        sbsj.setZh(zb.getZh());
        sbsj.setSbrq(sdf.format(zb.getSbrq()));
        sbsj.setSbbh(zb.getSbbh());
        sbsj.setSklxdm(zb.getSklxdm());
        if(zb.getSklxdm() !=null && zb.getSklxdm().equals(CodeConstant.SKLXDM_SDJJ))
        {
        	sbsj.setSklx(CodeConstant.SKLXMC_DZJK);
        }else{
        	sbsj.setSklx(JspUtil.getSklxmc(request, zb.getSklxdm()));
        }
        // sbsj.setSklx(zb.getSklxmc());
        sbsj.setGkzzjgmc(zb.getGkzzjgmc());
        sbsj.setGkzzjgdm(zb.getGkzzjgdm());
        sbsj.setZsswjgzzjgdm(zb.getZsswjgzzjgdm());
        sbsj.setZsswjgzzjgmc(zb.getZsswjgzzjgmc());
        sbsj.setSwjgzzjgdm(zb.getSwjgzzjgdm());
        sbsj.setSwjgzzjgmc(zb.getSwjgzzjgmc());
        sbsj.setDjzclxmc(zb.getDjzclxmc());
        sbsj.setLxdh(zb.getJydzlxdm());
        sbsj.setLsgxmc(zb.getLsgxmc());
        sbsj.setSbrqn(sdf.format(zb.getSbrq()).substring(0, 4));
        sbsj.setSbrqy(sdf.format(zb.getSbrq()).substring(4, 6));
        sbsj.setSbrqr(sdf.format(zb.getSbrq()).substring(6, 8));

        jks.setJkpzh(zb.getJkpzh());
        jks.setSzdm(zb.getSzdm());
        jks.setSzmc(zb.getSzmc());
        jks.setYskmdm(zb.getYskmdm());
        jks.setYskmmc(zb.getYskmmc());
        jks.setYsjcmc(zb.getYsjcmc());
        jks.setKkzt(zb.getFsdm());
        jks.setSjje(StringUtils.bigDeciaml2String(zb.getSjje(), "0.00"));
        jks.setSjjedx(com.ttsoft.framework.util.Currency.convert(zb.getSjje()));
        jks.setSkssjsrq(sdf.format(zb.getSkssjsrq()));
        jks.setSkssksrq(sdf.format(zb.getSkssksrq()));
        jks.setXjrq(sdf.format(zb.getXjrq()));
        jks.setBz(zb.getBz());
        jkslist.add(jks);
        sbsj.setJks(jkslist);
        for (int i = 0; i < mxList.size(); i++)
        {
            Sbjkmx mx = (Sbjkmx) mxList.get(i);
            JspUtil.completeMxInfo(request, mx);

            Smitem02VO sm = new Smitem02VO();

            sm.setJkpzh(mx.getJkpzh());
            sm.setSzsmdm(mx.getSzsmdm());
            sm.setSzsmmc(JspUtil.format(mx.getSzsmmc()));

            sm.setJsje(StringUtils.bigDeciaml2String(mx.getJsje(), "0.00"));
            sm.setKssl(StringUtils.bigDeciaml2String(mx.getKssl(), "0.00"));
            sm.setSjse(StringUtils.bigDeciaml2String(mx.getSjse(), "0.00"));
            sm.setSl(StringUtils.bigDeciaml2WholeString(mx.getSl(), "0.00"));
            sm.setYskmbm(zb.getYskmdm());
            sm.setYskmmc(zb.getYskmmc());
            sm.setYskmjc(zb.getYsjcmc());
            sm.setSksssq(sdf.format(zb.getSkssksrq())+"-"+sdf.format(zb.getSkssjsrq()));
            
            /***************************gaoyh added by 20131220 begin***********************/
            /**
        	 * 根据收入规划核算处要求，重新查询预算级次名称，预算级次显示为中央级、市级、区级
        	 */
            String yskmFcblmc = "";
            try {
				yskmFcblmc = ServiceProxy.getYskmFcblmc(mx.getYskmdm(), mx.getSzsmdm(), mx.getSwjgzzjgdm());
			} catch (com.ttsoft.framework.exception.BaseException e) {
				e.printStackTrace();
				throw new ApplicationException("重新生成预算科目分成比例失败！！！");
			}
            
			sm.setYskmjc(yskmFcblmc);
			/***************************gaoyh added by 20131220 end*************************/
			
            smlist.add(sm);
        }
        sbsj.setSmitem(smlist);

        sbsjlist.add(sbsj);

        zhsb02vo.setNsrxx(nsrxx);
        zhsb02vo.setSbxx(sbxx);
        zhsb02vo.setSbsj(sbsjlist);

        zhsb02vo.setSchemaVersion("20060401");
        zhsb02vo.setXsltVersion("20060401");
        zhsb02vo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB);
        zhsb02vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        return zhsb02vo;
    }

    /**
     * 将旧的VO对象转换为XML-VO对象。
     * 
     * @param qysdsjd
     * @param djJbsj
     * @return
     * @throws BaseException
     */
    private Zhsb02VO convert2XMLListVO(HttpServletRequest request, Map jkdata, SWDJJBSJ djJbsj, Boolean sskk) throws BaseException
    {
        Zhsb02VO vo = new Zhsb02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        // 获得系统当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            FriendHelper.getYhkkSfxy(request);
        }
        catch (com.ttsoft.framework.exception.BaseException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

        // XML文档信息
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0, 8));
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
        sbxx.setSbxgrq(sdf.format(new Date()));

        // 企业所得税季报
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();
        if (jkdata != null)
        {
            for (Iterator keyset = jkdata.keySet().iterator(); keyset.hasNext();)
            {
                String key = (String) keyset.next();
                HashMap aDeclaration = (HashMap) jkdata.get(key);
                Boolean tmp = (Boolean) aDeclaration.get(ZhsbMapConstant.ZFBZ);
                int printTag = ((Integer) aDeclaration.get(ZhsbMapConstant.PRINTTAG)).intValue();
                Sbjkzb sbzb = getSbjkzb(aDeclaration, printTag);
                JspUtil.completeZbInfo(request, sbzb);
                List smlist = new ArrayList();
                List jkslist = new ArrayList();
                sbsj = new Sbsj02VO();
                sbsj.setSklxdm(sbzb.getSklxdm());
                if(sbzb.getSklxdm() !=null && sbzb.getSklxdm().equals(CodeConstant.SKLXDM_SDJJ))
                {
                	sbsj.setSklx(CodeConstant.SKLXMC_DZJK);
                }else{
                	sbsj.setSklx(JspUtil.getSklxmc(request, sbzb.getSklxdm()));
                }

                sbsj.setSbrq(sdf.format(sbzb.getSbrq()));
                sbsj.setYhmc(sbzb.getYhmc());
                sbsj.setZh(sbzb.getZh());
                sbsj.setYhdm(sbzb.getYhdm());
                sbsj.setSskkbz(CAUtils.bool2Str(sskk.booleanValue()));
                
                //boolean lwFlag = LWUtil.isLW(request.getSession().getServletContext(),sbzb.getSwjgzzjgdm(),sbzb.getYhdm());
                //sbsj.setLwzt(CAUtils.bool2Str(lwFlag));
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
                sbsj.setLwzt(CAUtils.bool2Str(true));
                if (printTag == CodeConstant.PRINT_YPYS)
                { // 一票一税
                    sbsj.setLwzt(CAUtils.bool2Str(false));
                }
                boolean zfFlag = true;
                if (tmp != null)
                {
                    zfFlag = tmp.booleanValue();
                }
                else
                {
                    String zwbs = (String) aDeclaration.get(ZhsbMapConstant.ZWBS);
                    String subFirstZwbs = zwbs.substring(0, 1);
                    String subEndZwbs = zwbs.substring(19);
                    int flag = Integer.parseInt(subFirstZwbs + subEndZwbs);
                    zfFlag = (flag == 0);
                }
                sbsj.setSfkyzf(CAUtils.bool2Str(zfFlag));

                Set jkshSet = aDeclaration.keySet();
                Iterator jkshIter = jkshSet.iterator();

                int count = 0;
                while (jkshIter.hasNext())
                {
                    count++;
                    String jksh = (String) jkshIter.next();

                    if (jksh.equals(ZhsbMapConstant.SBRQ) || jksh.equals(ZhsbMapConstant.SKLX)
                            || jksh.equals(ZhsbMapConstant.PRINTTAG) || jksh.equals(ZhsbMapConstant.ZWBS)
                            || jksh.equals(ZhsbMapConstant.ZFBZ))
                    {
                        continue;
                    }
                    
                    Map decMap = (Map) aDeclaration.get(jksh);
                    if (printTag == CodeConstant.PRINT_YPYS)
                    { // 一票一税
                        Jks02VO ajks = new Jks02VO();

                        Sbjkzb zb = (Sbjkzb) ((DeclareInfor) decMap.get(ZhsbMapConstant.SBSJ)).getSbjkzb();
                        List mxList = ((DeclareInfor) decMap.get(ZhsbMapConstant.SBSJ)).getSbjkmxInfo();

                        JspUtil.completeZbInfo(request, zb);
                        ajks.setJkpzh(zb.getJkpzh());
                        ajks.setSzdm(zb.getSzdm());
                        ajks.setSzmc(zb.getSzmc());
                        ajks.setYskmdm(zb.getYskmdm());
                        ajks.setYskmmc(zb.getYskmmc());
                        ajks.setYsjcmc(zb.getYsjcmc());
                        ajks.setSjje(StringUtils.bigDeciaml2String(zb.getSjje(), "0.00"));
                        ajks.setSkssksrq(sdf.format(zb.getSkssksrq()));
                        ajks.setSkssjsrq(sdf.format(zb.getSkssjsrq()));
                        ajks.setXjrq(sdf.format(zb.getXjrq()));
                        ajks.setBz(zb.getBz());
                        String zbzwbs = zb.getZwbs();
                        String subFirstZwbs = zbzwbs.substring(0, 1);
                        String subEndZwbs = zbzwbs.substring(19);
                        int flag = Integer.parseInt(subFirstZwbs + subEndZwbs);
                        ajks.setSfkyzf(CAUtils.bool2Str(false));
                        if (flag == 0)
                        {
                            ajks.setSfkyzf(CAUtils.bool2Str(true));
                            ajks.setKkzt(CodeConstant.KKZT_ZF);

                        }
                        else if (flag > 0 && flag <= 2)
                        {
                            ajks.setKkzt(CodeConstant.KKZT_YJK);
                        }
                        else if (flag == 9)
                        {
                            ajks.setKkzt(CodeConstant.KKZT_DDKK);
                        }
                        else if (flag >= 10)
                        {
                            ajks.setKkzt(CodeConstant.KKZT_YRK);
                        }
                        else
                        {
                            ajks.setKkzt(CodeConstant.KKZT_QT);
                        }
                        
                        jkslist.add(ajks);
                        for (int i = 0; i < mxList.size(); i++)
                        {
                            Sbjkmx mxData = (Sbjkmx) mxList.get(i);
                            Smitem02VO sm = new Smitem02VO();
                            sm.setJkpzh(mxData.getJkpzh());
                            sm.setSzsmdm(mxData.getSzsmdm());
                            sm.setSzmc(zb.getSzmc());

                            sm.setSzsmmc(getSzsm(mxData.getSzsmdm(), request).getSzsmmc());
                            sm.setJsje(StringUtils.bigDeciaml2String(mxData.getJsje(), "0.00"));
                            sm.setKssl(StringUtils.bigDeciaml2String(mxData.getKssl(), "0.00"));
                            sm.setSjse(StringUtils.bigDeciaml2String(mxData.getSjse(), "0.00"));
                            sm.setSl(StringUtils.bigDeciaml2WholeString(mxData.getSl(), "0.00"));
                            smlist.add(sm);
                        }

                    }
                    else
                    {// 一票多税
                        List tempList = (List) decMap.get(ZhsbMapConstant.SBSJ);
                        for (int j = 0; j < tempList.size(); j++)
                        {
                            Jks02VO ajks = new Jks02VO();
                            DeclareInfor tempObj = (DeclareInfor) tempList.get(j); // 获得一条科目对应的申报数据
                            Sbjkzb zb = (Sbjkzb) tempObj.getSbjkzb();
                            List mxList = tempObj.getSbjkmxInfo();

                            JspUtil.completeZbInfo(request, zb);
                            ajks.setJkpzh(zb.getJkpzh());
                            ajks.setSzdm(zb.getSzdm());
                            ajks.setSzmc(zb.getSzmc());
                            ajks.setYskmdm(zb.getYskmdm());
                            ajks.setYskmmc(zb.getYskmmc());
                            ajks.setYsjcmc(zb.getYsjcmc());
                            ajks.setSjje(StringUtils.bigDeciaml2String(zb.getSjje(), "0.00"));
                            ajks.setSkssksrq(sdf.format(zb.getSkssksrq()));
                            ajks.setSkssjsrq(sdf.format(zb.getSkssjsrq()));
                            ajks.setXjrq(sdf.format(zb.getXjrq()));
                            ajks.setBz(zb.getBz());
                            String zbzwbs = zb.getZwbs();
                            String subFirstZwbs = zbzwbs.substring(0, 1);
                            String subEndZwbs = zbzwbs.substring(19);
                            int flag = Integer.parseInt(subFirstZwbs + subEndZwbs);
                            ajks.setSfkyzf(CAUtils.bool2Str(false));
                            if (flag == 0)
                            {
                                ajks.setSfkyzf(CAUtils.bool2Str(true));
                                ajks.setKkzt(CodeConstant.KKZT_ZF);

                            }
                            else if (flag > 0 && flag <= 2)
                            {
                                ajks.setKkzt(CodeConstant.KKZT_YJK);
                            }
                            else if (flag == 9)
                            {
                                ajks.setKkzt(CodeConstant.KKZT_DDKK);
                            }
                            else if (flag >= 10)
                            {
                                ajks.setKkzt(CodeConstant.KKZT_YRK);
                            }
                            else
                            {
                                ajks.setKkzt(CodeConstant.KKZT_QT);
                            }

                            jkslist.add(ajks);
                            for (int i = 0; i < mxList.size(); i++)
                            {
                                Sbjkmx mxData = (Sbjkmx) mxList.get(i);
                                Smitem02VO sm = new Smitem02VO();
                                sm.setJkpzh(mxData.getJkpzh());
                                sm.setSzsmdm(mxData.getSzsmdm());
                                sm.setSzmc(zb.getSzmc());

                                sm.setSzsmmc(getSzsm(mxData.getSzsmdm(), request).getSzsmmc());
                                sm.setJsje(StringUtils.bigDeciaml2String(mxData.getJsje(), "0.00"));
                                sm.setKssl(StringUtils.bigDeciaml2String(mxData.getKssl(), "0.00"));
                                sm.setSjse(StringUtils.bigDeciaml2String(mxData.getSjse(), "0.00"));
                                sm.setSl(StringUtils.bigDeciaml2WholeString(mxData.getSl(), "0.00"));
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
     * 根据税种税目代码取得税种税目对象
     * 
     * @param szsmdm
     *            String
     * @param request
     *            HttpServletRequest
     * @return Szsm
     */
    protected Szsm getSzsm(String szsmdm, HttpServletRequest request)
    {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);

        return (Szsm) szsmMap.get(szsmdm);
    }

    protected Sbjkzb getSbjkzb(HashMap aDeclaration, int printTag)
    {
        Sbjkzb zb = null;
        Set jkshSet = aDeclaration.keySet();
        Iterator jkshIter = jkshSet.iterator();

        int count = 0;
        while (jkshIter.hasNext())
        {
            count++;
            String jksh = (String) jkshIter.next();

            if (jksh.equals(ZhsbMapConstant.SBRQ) || jksh.equals(ZhsbMapConstant.SKLX)
                    || jksh.equals(ZhsbMapConstant.PRINTTAG) || jksh.equals(ZhsbMapConstant.ZWBS)
                    || jksh.equals(ZhsbMapConstant.ZFBZ))
            {
                continue;
            }

            Map decMap = (Map) aDeclaration.get(jksh);
            if (printTag == CodeConstant.PRINT_YPYS)
            { // 一票一税
                zb = (Sbjkzb) ((DeclareInfor) decMap.get(ZhsbMapConstant.SBSJ)).getSbjkzb();
                return zb;
            }
            else
            {// 一票多税
                List tempList = (List) decMap.get(ZhsbMapConstant.SBSJ);
                DeclareInfor tempObj = (DeclareInfor) tempList.get(0); // 获得一条科目对应的申报数据
                zb = (Sbjkzb) tempObj.getSbjkzb();
                return zb;
            }
        }
        return zb;
    }

    public String doReturn(HttpServletRequest request, HttpServletResponse response)
    {
        // clearSession(request);

        return "Return";
    }

    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        DzyjsjVO dzyj = new DzyjsjVO();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        // 检查token
        if (!isTokenValid(request))
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, CAConstants.ERROR_TRANSACTION_TOKEN);
            return CAConstants.INVALIDTOKEN;
        }
//        if (ud != null)
//            throw new ApplicationException("test exep;");
        Zhsb02VO xvo = new Zhsb02VO();
        try
        {
            String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
            log(xmldata);

            // 验证电子元件签名
            DzyjHelper dh = new DzyjHelper();

            // 获得页面提交数据
            try
            {
                //XMLParseHelper.parseXMLString(xvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
               // xvo = new Zhsb02VO();
                XMLParseHelper.parseXMLString(xvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
            }
            catch (ApplicationException e)
            {
                throw ExceptionUtil.getBaseException(e);
            }

            if (ud.getCaflag())
            {
                try
                {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1)
                {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }

            String ajksh = ""; // 缴款书号,一票多税只能删除整张申报表
            Sbsj02VO sbsj = (Sbsj02VO) xvo.getSbsj().get(0);
            String sbbh = sbsj.getSbbh();
            List jks = sbsj.getJks();
            HashMap jksMap = (HashMap) request.getSession().getAttribute(SessionKey.JKS);
            if (xvo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) && jks != null && jks.size() > 0)
            {
                HashMap aDeclaration = (HashMap) jksMap.get(sbbh);
                int printTag = ((Integer) aDeclaration.get(ZhsbMapConstant.PRINTTAG)).intValue();
                if (printTag == CodeConstant.PRINT_YPYS)
                { // 一票一税，可以删除单张缴款书

                    Set jkshSet = aDeclaration.keySet();
                    if (jks.size() != jkshSet.size())
                    {
                       Iterator jkshIter = jkshSet.iterator();
                        boolean find = false;
                        while (jkshIter.hasNext())
                        {
                            find = false;
                            String jksh = (String) jkshIter.next();

                            if (jksh.equals(ZhsbMapConstant.SBRQ) || jksh.equals(ZhsbMapConstant.SKLX)
                                    || jksh.equals(ZhsbMapConstant.PRINTTAG) || jksh.equals(ZhsbMapConstant.ZWBS)
                                    || jksh.equals(ZhsbMapConstant.ZFBZ))
                            {
                                continue;
                            }
                            // 找到要删除的缴款书号，不在列表里的
                            for (int i = 0; i < jks.size(); i++)
                            {
                                Jks02VO jvo = (Jks02VO) jks.get(i);
                                if (jvo.getJkpzh().equalsIgnoreCase(jksh))
                                {
                                    find = true;
                                }
                            }

                            if (!find)
                            {
                                ajksh = jksh;
                                break;
                            }
                        }
                    }
                }
            }

            	dzyj.setYwczlx(xvo.getYwczlx());
                dzyj.setYwdm(xvo.getYwlx());

            // started added by qianchao 2005-11-24
            log("delete sbbh=" + sbbh + "  jksh=" + ajksh);

            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // 临时修改

            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_DELETE);
            HashMap map = new HashMap(3);
            map.put(ZhsbMapConstant.SBBH, sbbh);
            map.put(ZhsbMapConstant.JKSH, ajksh);
            map.put("WSSB_SWJGZZJGDM", jbsj.getSwjgzzjgdm());
            map.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// 签名原件
            vo.setData(map);
            vo.setUserData(ud);

            Map reMap=(Map)ShenbaoProxy.getInstance().process(vo);

            if (ajksh.trim().length() != 0)
            {
                ((HashMap) jksMap.get(sbbh)).remove(ajksh); // 如果按缴款书作废，则清除该缴款书
                if (((HashMap) jksMap.get(sbbh)).size() == 3) // 如果该申报编号的数据为空，则清空该申报编号
                    jksMap.remove(sbbh);
            }
            else
            {
                jksMap.remove(sbbh); // 如果按照申报编号作废，则清除申报编号数据
            }

            Zhsb02VO xvo1 = new Zhsb02VO();
            xvo1 = convert2XMLListVO(request, jksMap, jbsj, Boolean.valueOf(false));
            String tmpxml = xvo1.toXML();
            log(tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo1.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo1.getSchemaVersion());
            request.getSession().setAttribute(SessionKey.CA_XML_DATA, tmpxml);
            request.getSession().setAttribute(SessionKey.CA_XML_XSLT_VERSION, xvo1.getXsltVersion());
            request.getSession().setAttribute(SessionKey.CA_XML_SCHEMA_VERSION, xvo1.getSchemaVersion());
            // 转向缴款书列表
            //设置回执下载页面的后续操作。
            if (ud.getCaflag() && dzyj.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))
            {
                dzyj = (DzyjsjVO) reMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
            }
            else
            {
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,"");
            }
			request.setAttribute("QYSBSuccess", "删除成功！");
            request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,"综合申报删除成功！");
            request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_ZHSB_DELETE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "综合申报", ((Sbsj02VO)xvo.getSbsj().get(0)).getSbrq(),
                    "成功!");

            //ca的修改操作转向回执下载页面，非ca转向缴款书列表
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.DELETE,CAConstants.SUCCESSSB);

        }
        catch (Exception ex)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            throw ExceptionUtil.getBaseException(ex);
        }
    }
	
    protected void log(String str) {
		if (ApplicationConstant.DEBUG_FLAG) {
			System.out.println("[WSSB DEBUG]" + (new Date()) + str);
		}
	}

}
