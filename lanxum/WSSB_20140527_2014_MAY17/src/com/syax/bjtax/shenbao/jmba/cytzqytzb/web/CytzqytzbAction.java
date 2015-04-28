package com.syax.bjtax.shenbao.jmba.cytzqytzb.web;

import com.syax.common.web.action.DcBaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import java.util.List;
import com.ttsoft.common.model.UserData;
import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13AVO;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import java.text.SimpleDateFormat;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.syax.frame.exception.ApplicationException;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13BVO;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;

public class CytzqytzbAction extends DcBaseAction{
    public CytzqytzbAction() {
    }

    /**
     * ¼�뱸������ ��ʼ������
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {
        List codeTable1 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+codeTable1.get(0).getClass());
        List codeTable2 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);

        // ȡ��userdata
        UserData userdata = (UserData)this.getUserData(request);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYDETAIL);
        voPackage.setUserData(userdata);
        //@todo �ӵڶ�����תҳ���request�л�ȡ
        // @todo �ӵڶ�����תҳ���request�л�ȡ
        JmbamxBo mxbo = (JmbamxBo) request.getSession().getAttribute(
                JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);

        String BASQWSH = mxbo.getBasqwsh();
        Map djMap = (Map)FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
        Map map = new HashMap();
        map.put("BASQWSH", BASQWSH);
        map.put("jbsj",jbsj);
        map.put("jsjdm", jbsj.getJsjdm());
        map.put("BAND", (Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
        map.put("type","SHOW");
        // @todo �ӵڶ�����תҳ���request�л�ȡ
        map.put("mxbo", mxbo);

        voPackage.setData(map);

        // ���ú�̨������ȡ�÷���ֵ
        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
            voPackage);

        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"");
        JmbaZbVO zbvo = (JmbaZbVO) vo.getJmsbajl().get(0);

        //��������
        String strXml = vo.toXML();
        Debug.out(strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
        request.getSession(false).setAttribute("codeTable1", codeTable1);
        request.getSession(false).setAttribute("codeTable2", codeTable2);
        request.getSession(false).setAttribute("basqwsh", BASQWSH);
        request.getSession(false).setAttribute("buss", "Save");
        int size =  zbvo.getQysdsjmba().size();
        System.out.println("mxvo size = "+size);
        request.getSession(false).setAttribute("size", size+"");
        request.getSession(false).setAttribute("zcbashbj", "1");
        // ת����ʾҳ��
        return CAConstants.ADDSHOW;

    }

    /**
     * ¼�뱸������ ��ʼ������
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doAdd(HttpServletRequest request,
                        HttpServletResponse response) throws BaseException {

        List codeTable1 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+codeTable1.get(0).getClass());
        List codeTable2 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);


        // ȡ��userdata
        UserData userdata = (UserData)this.getUserData(request);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYDETAIL);
        voPackage.setUserData(userdata);
        //@todo �ӵڶ�����תҳ���request�л�ȡ
        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
        //test if
        if(BASQWSH == null)
            BASQWSH = "062008000002";
        Map djMap = (Map)FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
        Map map = new HashMap();
        map.put("BASQWSH", BASQWSH);
        map.put("jsjdm", jbsj.getJsjdm());
        map.put("type","ADD");
        map.put("zcbashbj","1");

        voPackage.setData(map);

        // ���ú�̨������ȡ�÷���ֵ
        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
            voPackage);

        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"ADD");
        JmbaZbVO zbvo = (JmbaZbVO) vo.getJmsbajl().get(0);

        //��������
        String strXml = vo.toXML();
        Debug.out(strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
        request.getSession(false).setAttribute("codeTable1", codeTable1);
        request.getSession(false).setAttribute("codeTable2", codeTable2);
        request.getSession(false).setAttribute("basqwsh", BASQWSH);
        request.getSession(false).setAttribute("buss", "Save");
        int size =  zbvo.getQysdsjmba().size();
        System.out.println("mxvo size = "+size);
        request.getSession(false).setAttribute("size", size+"");
        request.getSession(false).setAttribute("zcbashbj", "1");
        // ת����ʾҳ��
        return CAConstants.ADDSHOW;
    }


    /**
     * ¼�뱸������ ��ʼ������
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doDel(HttpServletRequest request,
                        HttpServletResponse response) throws BaseException {

        String swjdjzh =request.getParameter("swdjzh");
        String lxdm =request.getParameter("lxdm");
        System.out.println("swjdjzh = "+swjdjzh);
        System.out.println("lxdm = "+lxdm);

        // ȡ��userdata
        UserData userdata = (UserData)this.getUserData(request);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
        voPackage.setUserData(userdata);
        //@todo �ӵڶ�����תҳ���request�л�ȡ
        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
        //test if
        if(BASQWSH == null)
            BASQWSH = "062008000002";
        Map djMap = (Map)FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
        Map map = new HashMap();
        map.put("BASQWSH", BASQWSH);
        map.put("swjdjzh", swjdjzh);
        map.put("lxdm", lxdm);
        map.put("type","DEL");

        voPackage.setData(map);

        // ���ú�̨������ȡ�÷���ֵ
        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
            voPackage);


        // ת����ʾҳ��
        return  doShow(request,response);
    }


    /**
     * ¼�뱸������ ��ʼ������
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doEditor(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException {
        List codeTable1 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);
        List codeTable2 = CodeTableUtil.getCodeTableList(request,
            CodeTable.JMBA_GXJSLY_LIST);
        // 1 editor 0 zcba editor
        String zcbashbj = (String)request.getParameter("zcbashbj");

        String wnwsh = (String)request.getParameter("wnwsh");
        String btzjsj = (String)request.getParameter("btzjsjdm");
        String zcbabs = (String)request.getParameter("zcbabs");
        String lxdm = (String)request.getParameter("lxdm");
        String swdjzh = (String)request.getParameter("swdjzh");
        // ȡ��userdata
        UserData userdata = (UserData)this.getUserData(request);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
        voPackage.setUserData(userdata);
        //@todo �ӵڶ�����תҳ���request�л�ȡ
        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");

        //test if
        if(BASQWSH == null)
            BASQWSH = "062008000002";
        Map djMap = (Map)FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");

        Map hm = new HashMap();
        hm.put("zcbashbj", zcbashbj);
        hm.put("wnwsh", wnwsh);
        hm.put("swdjzh", swdjzh);
        hm.put("btzjsj", btzjsj);
        hm.put("zcbabs", zcbabs);
        hm.put("lxdm", lxdm);
        hm.put("BASQWSH", BASQWSH);
        hm.put("type", "EDITOR");
        voPackage.setData(hm);
       voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
       voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYDETAIL);
        voPackage.setData(hm);

        // ���ú�̨������ȡ�÷���ֵ
        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
            voPackage);
        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"");
        //��������
        String strXml = vo.toXML();
        Debug.out(strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
        request.getSession(false).setAttribute("codeTable1", codeTable1);
        request.getSession(false).setAttribute("codeTable2", codeTable2);
        request.getSession(false).setAttribute("basqwsh", BASQWSH);
        JmbaZbVO zbvo = (JmbaZbVO) vo.getJmsbajl().get(0);
        int size = zbvo.getQysdsjmba().size();
        if(zcbashbj.equals("0")){
            request.getSession(false).setAttribute("buss", "Save");

        }else{
            request.getSession(false).setAttribute("buss", "Update");
        }

        System.out.println("mxvo size = "+size);
        request.getSession(false).setAttribute("size", size+"");

        request.getSession(false).setAttribute("wnwsh", wnwsh);

        request.getSession(false).setAttribute("zcbashbj", zcbashbj);

        // ת����ʾҳ��
        return CAConstants.ADDSHOW;
    }



    private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj, String BASQWSH,String type) {
        //1 ���ϲ�VO
        JmbaVO vo = new JmbaVO();
        //2 ��˰��VO 1.set 2
        NsrxxVO nsrxx = new NsrxxVO();

        nsrxx.setJsjdm(jbsj.getJsjdm());
        nsrxx.setNsrmc(jbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

        //1.set 2
        vo.setNsrxx(nsrxx);
        //3 ����VO 1-n ��������� 1.list.add 3s
        JmbaZbVO zbvo = (JmbaZbVO)map.get("JmbaZbVO");
        zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM13B);
        zbvo.setBasqwsh(BASQWSH);
        if(zbvo.getQysdsjmba().isEmpty()&& type.equals("ADD")) {
            zbvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
            getNewBandVo(ud,jbsj,BASQWSH,zbvo.getQysdsjmba());
        }
        vo.getJmsbajl().add(zbvo);
        vo.setNsrxx(nsrxx);
        vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
        vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
        vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA14);

        return vo;
    }


    // ���ݲɼ���Ļ�ȡ��
    public String doCheckBtzqyJsjdm(HttpServletRequest request,
                           HttpServletResponse response)
            throws BaseException {
        // ���ݼ�������뽫��˰�˵Ļ�����Ϣ��ѯ����������ҳ���ϡ�
        String jsjdm = (String)request.getParameter("jsjdm");
        System.out.println("jsjdm = "+ jsjdm);
        SWDJJBSJ jbsj = null;
        try{
            jbsj = FriendHelper.getDjJbsj(jsjdm);
        }catch(Exception ex){
            ex.printStackTrace();
            jbsj = null;
        }
        System.out.println("jbsj = "+ jbsj);
        StringBuffer buff = new StringBuffer();

        buff.append("<?xml version='1.0' encoding='GB2312' ?>");
        buff.append("<btznsrxx>");
        if(jbsj == null){
            buff.append("<sfcz><![CDATA[0]]></sfcz>");
            buff.append("<rrturnmsg><![CDATA[û�и�Ͷ����ҵ����,�������ʡ����ҵ��¼��˰��Ǽ�֤�ż���]]></rrturnmsg>");
            buff.append("<btzjsjdm><![CDATA["+jsjdm+"]]></btzjsjdm>");
            buff.append("<btznsrmc><![CDATA[]]></btznsrmc>");
            buff.append("<btzswdjzh><![CDATA[]]></btzswdjzh>");

        }else{
            buff.append("<sfcz><![CDATA[1]]></sfcz>");
            buff.append("<rrturnmsg><![CDATA[������ҵ]]></rrturnmsg>");
            buff.append("<btzjsjdm><![CDATA[" + jbsj.getJsjdm() + "]]></btzjsjdm>");
            buff.append("<btznsrmc><![CDATA[" + jbsj.getNsrmc() + "]]></btznsrmc>");
            buff.append("<btzswdjzh><![CDATA[" + jbsj.getSwdjzh() + "]]></btzswdjzh>");
        }
        buff.append("</btznsrxx>");
        response.setContentType("text/xml;charset=GB2312");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter responseOut = null;
        try {
            responseOut = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        responseOut.write(buff.toString());
        responseOut.flush();

        return CAConstants.ADDSHOW;
    }


    // ���ݲɼ���Ļ�ȡ��
    public String doCheck(HttpServletRequest request,
                           HttpServletResponse response)
            throws BaseException {
        // ���ݼ�������뽫��˰�˵Ļ�����Ϣ��ѯ����������ҳ���ϡ�
        String jsjdm = (String)request.getParameter("jsjdm");
        String lxdm = (String)request.getParameter("lxdm");
        String swdjzh = (String)request.getParameter("swdjzh");
        System.out.println("jsjdm = "+ jsjdm);
        SWDJJBSJ jbsj = null;
        try{
            jbsj = FriendHelper.getDjJbsj(jsjdm);
        }catch(Exception ex){
            ex.printStackTrace();
            jbsj = null;
        }
       VOPackage voPackage = new VOPackage();
       HashMap hm = new HashMap();
       //@todo �ӵڶ�����תҳ���request�л�ȡ
       String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
       hm.put("swdjzh", swdjzh);
       hm.put("jsjdm", jsjdm);
       hm.put("lxdm", lxdm);
       hm.put("BASQWSH", BASQWSH);
       voPackage.setData(hm);
       voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
       voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
       voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

       hm = (HashMap) voPackage.getData();
       String checked = (String) hm.get("checked");

        StringBuffer buff = new StringBuffer();

        buff.append("<?xml version='1.0' encoding='GB2312' ?>");
        buff.append("<btznsrxx>");
        if(jbsj == null){
            if(checked.equals("1")){
                buff.append("<sfcz><![CDATA[2]]></sfcz>");
                buff.append("<rrturnmsg><![CDATA[ͬһ��Ͷ����ҵֻ���걨һ�����¼�������ҵ������,������ѡ����¼�������������ı�Ͷ����ҵ��Ϣ]]></rrturnmsg>");
            }else{
                buff.append("<sfcz><![CDATA[0]]></sfcz>");
                buff.append("<rrturnmsg><![CDATA[û�и�Ͷ����ҵ����,�������ʡ����ҵ��¼��˰��Ǽ�֤�ż���]]></rrturnmsg>");
            }
        }else{
            if(checked.equals("1")){
                buff.append("<sfcz><![CDATA[2]]></sfcz>");
                buff.append("<rrturnmsg><![CDATA[ͬһ��Ͷ����ҵֻ���걨һ�����¼�������ҵ������,������ѡ����¼�������������ı�Ͷ����ҵ��Ϣ]]></rrturnmsg>");
            }else{
                buff.append("<sfcz><![CDATA[0]]></sfcz>");
                buff.append("<rrturnmsg><![CDATA[������ҵ]]></rrturnmsg>");
            }
        }
        buff.append("</btznsrxx>");
        response.setContentType("text/xml;charset=GB2312");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter responseOut = null;
        try {
            responseOut = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        responseOut.write(buff.toString());
        responseOut.flush();

        return CAConstants.ADDSHOW;
    }



    private void getNewBandVo(UserData ud, SWDJJBSJ jbsj, String BASQWSH,List zbList){

        int band = (Integer.parseInt(DateUtilPro.getCurYearStr4())-1);
        /**
         * @todo ��һ����� �Զ����� 2006�� �� ��ǰ���-1 VO 06��07ֻ��Ͷ�ʶ�
         */
        Jmba13BVO mxvo = null;
        for(int i=2006;i<=band;i++){

            mxvo = new Jmba13BVO();
            mxvo.setBAND((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
            mxvo.setTZND(i+"");
            mxvo.setBASQWSH(BASQWSH);
            mxvo.setSWJGZZJGDM(jbsj.getSwjgzzjgdm());
            mxvo.setCJR(jbsj.getJsjdm());
            mxvo.setCJRQ(DateUtilPro.getCurYearStr4());
            mxvo.setLRR(jbsj.getJsjdm());
            mxvo.setLRRQ(DateUtilPro.getCurYearStr4());
            System.out.println("mxvo "+i+"�� "+mxvo.toXML());
            zbList.add(mxvo);
        }
        System.out.println("list size = "+zbList.size());
    }


    public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
  {

      if (!isTokenValid(request))
      {
          return CAConstants.INVALIDTOKEN;
      }

      String zcbashbj  = (String) request.getSession(false).getAttribute("zcbashbj");
      System.out.println("zcbashbj = "+zcbashbj);
      String wnwsh = (String) request.getSession(false).getAttribute("wnwsh");
      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
      // ����VOPackage
      VOPackage voPackage = new VOPackage();
      UserData ud = (UserData) this.getUserData(request);
      DzyjHelper dh = new DzyjHelper();
      System.out.println("jmba xmldata" + xmldata);
      DzyjsjVO dzyj =  new DzyjsjVO();
      JmbaVO jmbavo = new JmbaVO();

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

              System.out.println(xmldata);
              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
          }
          catch (ApplicationException e)
          {
              throw ExceptionUtil.getBaseException(e);
          }


               dzyj.setYwczlx(jmbavo.getYwczlx());
              dzyj.setYwdm(jmbavo.getYwlx());
              // ȡ�õǼ�����
              Map djMap = (Map) FriendHelper.getDjInfo(request);
              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

              Timestamp now = new Timestamp(System.currentTimeMillis());
              voPackage.setUserData(ud);
              HashMap hm = new HashMap();

              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
              hm.put("type","Save");
              hm.put("zcbashbj",zcbashbj);

              hm.put("wnwsh",wnwsh);
              voPackage.setData(hm);
              voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
              voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
              voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "xxxxxxxxxxxxxxxxx����ɹ���");
              LogUtil.getInstance().log(FriendHelper.getUserData(request), "xxxxxxxxxxxxxxxxxxxxxxx����",
                                        (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");

              return  doShow(request,response);

      }
      catch (Exception e)
      {
          // /3.9.���ÿ�xml��ת��ʧ��ҳ��
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
          System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());

          throw ExceptionUtil.getBaseException(e);
      }

  }



  public String doUpdate(HttpServletRequest request, HttpServletResponse response) throws BaseException
  {

      if (!isTokenValid(request))
      {
          return CAConstants.INVALIDTOKEN;
      }

      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
      // ����VOPackage
      VOPackage voPackage = new VOPackage();
      UserData ud = (UserData) this.getUserData(request);
      DzyjHelper dh = new DzyjHelper();
      System.out.println("jmba xmldata" + xmldata);
      DzyjsjVO dzyj =  new DzyjsjVO();
      JmbaVO jmbavo = new JmbaVO();

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

              System.out.println(jmbavo.toString());
              System.out.println("-------------------");
              System.out.println(xmldata);
              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
          }
          catch (ApplicationException e)
          {
              throw ExceptionUtil.getBaseException(e);
          }

              dzyj.setYwczlx(jmbavo.getYwczlx());
              dzyj.setYwdm(jmbavo.getYwlx());
              // ȡ�õǼ�����
              Map djMap = (Map) FriendHelper.getDjInfo(request);
              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

              Timestamp now = new Timestamp(System.currentTimeMillis());
              voPackage.setUserData(ud);
              HashMap hm = new HashMap();

              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
              hm.put("type","Update");
              voPackage.setData(hm);
              voPackage.setProcessor(ProcessorNames.CYTZQYTZB14_PROCESSOR);
              voPackage.setAction(JmbaActionConstant.INTI_ACTION_UPDATE);

              voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "xxxxxxxxxxxxxxxxx����ɹ���");
              LogUtil.getInstance().log(FriendHelper.getUserData(request), "xxxxxxxxxxxxxxxxxxxxxxx����",
                                        (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");

              return doShow(request, response);

      }
      catch (Exception e)
      {
          // /3.9.���ÿ�xml��ת��ʧ��ҳ��
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
          System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());

          throw ExceptionUtil.getBaseException(e);
      }

  }



    /**
     * �������Ȩ�޽��м��
     */
    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response) {
        // ���Ȩ�� ������û�м������
        // System.out.println("beforePerform");
//        if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
//        {
//
//            return CAConstants.NOPERMISSION;
//        }
        return null;
    }


}
