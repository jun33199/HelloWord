package com.ttsoft.bjtax.shenbao.wsksb.web;

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
import javax.servlet.http.HttpSession;
import org.apache.struts.util.MessageResources;
import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Jmfl;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.model.domain.Wssbyy;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.Nsrxx02VO;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.Sbsj02VO;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.Sbxx02VO;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class Wsksb02Action extends DcBaseAction
{

    protected static MessageResources messages = MessageResources.getMessageResources("ApplicationResources");

    static int CANDO = 0;

    static int CANNOTDO = 1;

    static int CANALLNOT = 2;

    public Wsksb02Action()
    {
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        // 检查权限
        // System.out.println("beforePerform");
        if (!SbzlAccess.getAuthority(SbzlAccess.WSKSB, request))
        {

            return CAConstants.NOPERMISSION;
        }
        return null;
    }

    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {

        // System.out.println("doDelete");
        if (!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }

        UserData ud = (UserData) this.getUserData(request);
        DzyjsjVO dzyj =  new DzyjsjVO();

        // 验证电子元件签名
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        DzyjHelper dh = new DzyjHelper();
        WsksbVO wsksbvo = new WsksbVO();

        if (ud.getCaflag())
        {
            try
            {
                dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
            }
            catch (ApplicationException e)
            {
                throw ExceptionUtil.getBaseException(e);
            }
        }
        try
        {
           // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
          //  wsksbvo = new WsksbVO();
            XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
        }
        catch (ApplicationException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        try
        {
            dzyj.setYwczlx(wsksbvo.getYwczlx());
            dzyj.setYwdm(wsksbvo.getYwlx());
            if (Integer.parseInt(wsksbvo.getSbsj().getSbcz()) == CANNOTDO)
            {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Wynsksb wynsksb = new Wynsksb();
                wynsksb.setJsjdm(wsksbvo.getNsrxx().getJsjdm());
                wynsksb.setLrrmc(wsksbvo.getNsrxx().getNsrmc());
                wynsksb.setSbbh(wsksbvo.getSbsj().getSbbh());
                wynsksb.setNd((new SimpleDateFormat("yyyy")).format(now));
                wynsksb.setQxdm(wsksbvo.getNsrxx().getSwjgzzjgdm().substring(0, 2));
                VOPackage voPackage = new VOPackage();
                voPackage.setUserData(ud);
                HashMap hm = new HashMap();
                hm.put(ZhsbMapConstant.SBSJ, wynsksb);
                hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
                hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, wsksbvo);
                voPackage.setData(hm);
                voPackage.setAction(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.INT_ACTION_DELETE);
                voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);

                ShenbaoProxy.getInstance().process(voPackage);
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
                request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "无税申报删除成功！");
                request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "无税申报删除",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
            }
           
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
        }
        catch (Exception e)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, wsksbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, wsksbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, wsksbvo.getSchemaVersion());
            throw ExceptionUtil.getBaseException(e);
        }
    }

    public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
//        if (request != null)
//            throw new com.ttsoft.framework.exception.ApplicationException("ws exception");
            
        if (!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }
        
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        // 生成VOPackage
        VOPackage voPackage = new VOPackage();
        UserData ud = (UserData) this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        System.out.println("xmldata" + xmldata);
        DzyjsjVO dzyj =  new DzyjsjVO();
        WsksbVO wsksbvo = new WsksbVO();

        try
        {
        	
            if (ud.getCaflag())
            {
                try
                {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e)
                {
                    throw ExceptionUtil.getBaseException(e);
                }
            }
            try
            {
               // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
                //wsksbvo = new WsksbVO();
                XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
            }
            catch (ApplicationException e)
            {
                throw ExceptionUtil.getBaseException(e);
            }

            if (Integer.parseInt(wsksbvo.getSbsj().getSbcz()) == CANDO)
            {
                dzyj.setYwczlx(wsksbvo.getYwczlx());
                dzyj.setYwdm(wsksbvo.getYwlx());
                // 取得登记数据
                Map djMap = (Map) FriendHelper.getDjInfo(request);
                SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

                Timestamp now = new Timestamp(System.currentTimeMillis());
                Wynsksb wynsksb = getWynsksb(jbsj, now, wsksbvo);
                voPackage.setUserData(ud);
                HashMap hm = new HashMap();
                hm.put(ZhsbMapConstant.SBSJ, wynsksb);
                hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

                hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
                hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, wsksbvo);
                voPackage.setData(hm);
                voPackage.setAction(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.INT_ACTION_SAVE);
                voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);
                voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
                wynsksb = (Wynsksb) (((HashMap) voPackage.getData()).get(ZhsbMapConstant.SBSJ));
                dzyj = (DzyjsjVO) ((HashMap) voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                // 如果是CA户则进行签名回写

                wsksbvo = convert2XMLVOResult(request, jbsj, wynsksb, now);
                String tmpxml = wsksbvo.toXML();
                request.setAttribute("CA_XML_DATA", tmpxml);
                request.setAttribute("CA_XML_XSLT_VERSION", wsksbvo.getXsltVersion());
                request.setAttribute("CA_XML_SCHEMA_VERSION", wsksbvo.getSchemaVersion());

                // 设置回执下载页面的后续操作。
                if (ud.getCaflag())
                {
                    request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, Long.toString(dzyj.getLsh()));
                }
                else
                {
                    request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
                }
                request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "无税申报成功！");
                request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "无税申报保存",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");

            }
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
        }
        catch (Exception e)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, wsksbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, wsksbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, wsksbvo.getSchemaVersion());
            System.out.println("网上综合申报异常信息: ===== " + e.getMessage());

            throw ExceptionUtil.getBaseException(e);
        }

    }

    private WsksbVO convert2XMLVOResult(HttpServletRequest request, SWDJJBSJ jbsj, Wynsksb wynsksb, Date now)
            throws BaseException, ApplicationException
    {
        WsksbVO wsksbvo = new WsksbVO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        nsrxx.setJsjdm(jbsj.getJsjdm());
        nsrxx.setNsrmc(jbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

        sbxx.setFsdm(wynsksb.getFsdm());
        sbxx.setSbrq(sdf.format(wynsksb.getSbrq()));
        String skssksrq = sdf.format(wynsksb.getSkssksrq());
        String skssjsrq = sdf.format(wynsksb.getSkssjsrq());
        sbxx.setSkssksrq(skssksrq);
        sbxx.setSkssjsrq(skssjsrq);
        sbsj.setSbbh(wynsksb.getSbbh());
        sbsj.setWssbyydm(wynsksb.getWssbyydm());
        sbsj.setQtWssbyymc(wynsksb.getWssbyynr());

        List msgList = new ArrayList();
        sbsj.setSbcz(Integer.toString(CANNOTDO));
        msgList.add(getMessage("message.web.wsksb.savesuccess"));
        msgList.add(getMessage("message.web.wsksb.yssbts"));
        request.setAttribute("Msg", msgList);
        // sbsj.setSbbh(wynsksb.getSbbh());

        wsksbvo.setNsrxx(nsrxx);
        wsksbvo.setSbxx(sbxx);
        wsksbvo.setSbsj(sbsj);

        wsksbvo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB)
                .substring(8));
        wsksbvo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB)
                .substring(0, 8));
        wsksbvo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB);
        wsksbvo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);

        return wsksbvo;
    }

    private Wynsksb getWynsksb(SWDJJBSJ jbsj, Timestamp now, WsksbVO wsksbvo) throws Exception
    {
        Wynsksb wynsksb = new Wynsksb();
        long currentTime = now.getTime();
        wynsksb.setJsjdm(jbsj.getJsjdm());
        wynsksb.setLrrmc(jbsj.getNsrmc());
        wynsksb.setSbrq(now);
        // wynsksb.setBz()
        wynsksb.setFsdm(CodeConstant.FSDM_WSSB);
        wynsksb.setLrr(jbsj.getJsjdm());
        wynsksb.setLrrq(now);
        wynsksb.setCjrq(now);
        wynsksb.setNd((new SimpleDateFormat("yyyy")).format(now));
        wynsksb.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
        wynsksb.setSjly(CodeConstant.SJLY_SB_SBLR);
        wynsksb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        wynsksb.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
        wynsksb.setSkssksrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSKSRQ));
        wynsksb.setSkssjsrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ));
        if (wynsksb.getSkssjsrq() == null)
        {
            // 税款所属截止日期，默认为上个月的最后一天
            wynsksb.setSkssjsrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ));
        }
        wynsksb.setWssbyydm(wsksbvo.getSbsj().getWssbyydm());
        wynsksb.setWssbyynr(wsksbvo.getSbsj().getQtWssbyymc());
        
        return wynsksb;
    }

    protected void log(String str)
    {
        if (ApplicationConstant.DEBUG_FLAG)
        {
            System.out.println("[WSSB DEBUG]" + (new Date()) + str);
        }
    }

    /**
     * 无税申报时判断
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doBeforeShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
    	UserData userData = (UserData) request.getSession(false).getAttribute(SessionKey.USER_DATA);
    	
    	 VOPackage voPackage = new VOPackage();
         voPackage.setAction(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.INT_ACTION_QUERYGZSX);
         voPackage.setUserData(userData);
         voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);
         String text = (String) ShenbaoProxy.getInstance().process(voPackage);
         //text=null;
         if(text==null||"".equals(text)){
        	 return "GoShow";
         }
         String[] arraytext = text.split("</div>");
         
         String tmpxml ="";
         if(arraytext.length>1)
         {
        	 tmpxml ="<?xml version='1.0' encoding='UTF-8'?><taxdoc><gzsxtitle><![CDATA["+(arraytext[0]==null?"":arraytext[0])+"]]></gzsxtitle><gzsxtext><![CDATA["+(arraytext[1]==null?"":arraytext[1])+"]]></gzsxtext></taxdoc>";
         }else{
        	 tmpxml ="<?xml version='1.0' encoding='UTF-8'?><taxdoc><gzsxtitle></gzsxtitle><gzsxtext><![CDATA["+(text==null?"":text)+"]]></gzsxtext></taxdoc>";
         }
         System.out.print("tmpxml:" + text);
        
         request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
        
         return "BeforeShow";
    	
    }
    
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
       
        WsksbVO wsksbvo = new WsksbVO();
        try
        {
        	// 取得登记数据
            Map djMap = (Map) FriendHelper.getDjInfo(request);
            UserData userData = (UserData) request.getSession(false).getAttribute(SessionKey.USER_DATA);
            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
            List wsyyList = CodeTableUtil.getCodeTableList(request, CodeTable.WSYY_BASX_LIST);
            // 取税费管理数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Calendar cc = new GregorianCalendar();
            cc.add(Calendar.MONTH, -1);
            int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
            cc.set(Calendar.DAY_OF_MONTH, maxday);

            // 上个月最后一天
            Date dd = cc.getTime();
            List dqdeList = sfglProxy.getYnsje(userData.yhid, dd, dd);
            boolean dqde = false;
            for (int i = 0; i < dqdeList.size(); i++)
            {
                Dqdedlmx1 dqdedl = (Dqdedlmx1) dqdeList.get(i);
                if (dqdedl != null)
                {
                    String zsfsdm = dqdedl.getZsfsdm();
                    if (zsfsdm != null && zsfsdm.equals("01")) // 定额征收
                    {
                        dqde = true;
                        break;
                    }
                }
            }

            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Map data = new HashMap();
            data.put(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.JSJDM, userData.yhid);
            data.put("WSSB_SWJGZZJGDM", jbsj.getSwjgzzjgdm());
            data.put("WSSB_DJZCLXDM", jbsj.getDjzclxdm());
            data.put(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.WHRQ, sdf.format(now));
            data.put(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.SKSSKSRQ, sdf.format((Timestamp) Skssrq.monthSkssrq(now)
                    .get(Skssrq.SKSSKSRQ)));
            data.put(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.SKSSJSRQ, sdf.format((Timestamp) Skssrq.monthSkssrq(now)
                    .get(Skssrq.SKSSJSRQ)));

            VOPackage voPackage = new VOPackage();
            voPackage.setData(data);
            voPackage.setAction(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.INT_ACTION_QUERYWSKJL);
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);
            Map wskMap = (Map) ShenbaoProxy.getInstance().process(voPackage);
            Wynsksb wynsksb = (Wynsksb) wskMap.get(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.WSKJKS);
            String zqstr = TinyTools.getProperty(com.ttsoft.bjtax.shenbao.wsksb.SessionKey.WSKSBZQRLPROPERTY
                    + (new SimpleDateFormat("MM")).format(now));
           
            boolean withinzq = withinZq(zqstr, now);
            // 测试用!!!!!
            // withinzq = true;//测试用

            wsksbvo = convert2XMLVO(request, jbsj, wynsksb, dqde,  withinzq, now);
            String tmpxml = wsksbvo.toXML();
            System.out.print("tmpxml:" + tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, wsksbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, wsksbvo.getSchemaVersion());
            
            HttpSession session = request.getSession();
            session.setAttribute("wssbyyArray", wsyyList);

            return CAConstants.SHOW;
        }
        catch (Exception ex)
        {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, wsksbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, wsksbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, wsksbvo.getSchemaVersion());

            throw ExceptionUtil.getBaseException(ex);
        }
    }

    private WsksbVO convert2XMLVO(HttpServletRequest request, SWDJJBSJ jbsj, Wynsksb wynsksb, boolean dqde, 
            boolean withinzq, Date now) throws BaseException, ApplicationException
    {
       
        WsksbVO wsksbvo = new WsksbVO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        //取得无税减免原因代码表 减免税项目  tujb 200404
    	HttpSession session = request.getSession(false);
    	if (session.getAttribute("wssbyy_Array") == null) 
    	{
    		List wssbyyList = CodeTableUtil.getCodeTableList(request, CodeTable.WSYY_BASX_LIST);
  		  	String[][] wssbyy = new String[wssbyyList.size()][2];
  		  	for(int i =0; i< wssbyyList.size(); i++)
		  	{
  		  		Wssbyy wsyy = (Wssbyy)wssbyyList.get(i);
  		  		wssbyy[i][0] = wsyy.getWssbyydm();
  		  		wssbyy[i][1] = wsyy.getWssbyymc();
		  	}
  		  session.setAttribute("wssbyy_Array", wssbyy);
    	}
        
        nsrxx.setJsjdm(jbsj.getJsjdm());
        nsrxx.setNsrmc(jbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

        long currentTime = now.getTime();
        wsksbvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        if (wynsksb == null)
        {
            sbxx.setFsdm(CodeConstant.FSDM_WSSB);
            sbxx.setSbrq(sdf.format(now));
            sbxx.setSkssksrq(sdf.format((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSKSRQ)));
            sbxx.setSkssjsrq(sdf.format((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ)));
            sbsj.setSbbh("");
        }
        else
        {
            wsksbvo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
            sbxx.setFsdm(wynsksb.getFsdm());
            sbxx.setSbrq(sdf.format(wynsksb.getSbrq()));
            String skssksrq = sdf.format(wynsksb.getSkssksrq());
            String skssjsrq = sdf.format(wynsksb.getSkssjsrq());
            sbxx.setSkssksrq(skssksrq);
            sbxx.setSkssjsrq(skssjsrq);
            sbsj.setSbbh(wynsksb.getSbbh());
            sbsj.setWssbyydm(wynsksb.getWssbyydm());
            sbsj.setQtWssbyymc(wynsksb.getWssbyynr());
        }
        List msgList = new ArrayList();
        // 定期定额征收方式不需要做无应纳税款申报
        if (dqde)
        {
            // request.getSession().getAttribute(CANNOTDO);
            // request.getSession().setAttribute(SessionKey.JKS, reObject);
            sbsj.setSbcz(Integer.toString(CANALLNOT));
            msgList.add(getMessage("message.web.wsksb.dqde"));
        }
        else if (!withinzq)// 征期已过，有关申报事宜请与主管税务机关联系
        {
            sbsj.setSbcz(Integer.toString(CANALLNOT));
            msgList.add(getMessage("message.web.wsksb.zqyg"));
        }
        else if (wynsksb == null)
        {// 如果您本期没有税款，请单击保存按钮进行无应纳税款申报

            sbsj.setSbcz(Integer.toString(CANDO));
            msgList.add(getMessage("message.web.wsksb.cansave"));
            // 您本期已经做了无应纳税款申报,可以单击删除按钮，删除本期无应纳税款申报书
        }
        else if (wynsksb.getFsdm() != null && wynsksb.getFsdm().equals(CodeConstant.FSDM_WSSB))
        {
            sbsj.setSbcz(Integer.toString(CANNOTDO));
            msgList.add(getMessage("message.web.wsksb.candel"));
            // 您本期已经做了无应纳税款申报,上门
        }
        else
        {
            sbsj.setSbcz(Integer.toString(CANALLNOT));
            msgList.add(getMessage("message.web.wsksb.notwssb"));
        }
        msgList.add(getMessage("message.web.wsksb.yssbts"));
        request.setAttribute("Msg", msgList);
        // sbsj.setSbbh(wynsksb.getSbbh());

        wsksbvo.setNsrxx(nsrxx);
        wsksbvo.setSbxx(sbxx);
        wsksbvo.setSbsj(sbsj);
        // wsksbvo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB).substring(8));
        // wsksbvo.setXsltVersion("20060401");
        wsksbvo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB)
                .substring(8));
        wsksbvo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB)
                .substring(0, 8));
        wsksbvo.setYwlx(CAcodeConstants.YWDM_SB_WS_ZHSB_WYNSSB);
        
        //wsksbvo.setGzsxtext(gzsxText);
        // wsksbvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        return wsksbvo;
    }

    // 判断当前日期是否在征期内
    private boolean withinZq(String zqstr, Date now)
    {
        try
        {
            int fromDate = Integer.parseInt(zqstr.substring(0, 4));
            int toDate = Integer.parseInt(zqstr.substring(5));
            int nowDate = Integer.parseInt((new SimpleDateFormat("MMdd")).format(now));

            if (nowDate <= toDate && nowDate >= fromDate)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            return false;
        }
        finally
        {

        }
    }


    
    private String getMessage(String msgName)
    {
        return TinyTools.toGBK(messages.getMessage(msgName));
    }

    public String doReturn(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        // 转向返回后的页面。
        return "Return";
    }
}
