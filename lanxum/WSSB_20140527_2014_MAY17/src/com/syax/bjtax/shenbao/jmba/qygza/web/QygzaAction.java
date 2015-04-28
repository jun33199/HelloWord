//package com.syax.bjtax.shenbao.jmba.qygza.web;
//
//import com.syax.common.web.action.DcBaseAction;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
//import com.ttsoft.framework.util.VOPackage;
//import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
//import com.ttsoft.bjtax.shenbao.constant.CAConstants;
//import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
//import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
//import com.ttsoft.bjtax.shenbao.util.Debug;
//import com.ttsoft.framework.exception.BaseException;
//import java.util.List;
//import com.ttsoft.common.model.UserData;
//import java.util.Map;
//import java.util.HashMap;
//import com.syax.bjtax.shenbao.model.common.NsrxxVO;
//import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
//import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
//import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
//import java.text.SimpleDateFormat;
//import com.ttsoft.framework.exception.ExceptionUtil;
//import com.syax.common.util.CAcodeConstants;
//import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
//import com.syax.bjtax.ca.util.DzyjHelper;
//import com.ttsoft.bjtax.shenbao.constant.CodeTable;
//import com.syax.bjtax.ca.xml.util.XMLParseHelper;
//import com.syax.bjtax.ca.vo.DzyjsjVO;
//import java.sql.Timestamp;
//import com.ttsoft.bjtax.shenbao.util.FriendHelper;
//import com.syax.frame.exception.ApplicationException;
//import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
//import com.ttsoft.bjtax.shenbao.util.LogUtil;
//import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14AVO;
//
//public class QygzaAction extends DcBaseAction{
//    public QygzaAction() {
//    }
//
//    /**
//     * ¼�뱸������ ��ʼ������
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public String doShow(HttpServletRequest request,
//                         HttpServletResponse response) throws BaseException {
//
//        // ȡ��userdata
//        UserData userdata = (UserData)this.getUserData(request);
//        // ����VOPackage
//        VOPackage voPackage = new VOPackage();
//        // �趨vopackage����
//        voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
//        voPackage.setUserData(userdata);
//        //@todo �ӵڶ�����תҳ���request�л�ȡ
//        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
//        //test if
//        if(BASQWSH == null)
//            BASQWSH = "062008000002";
//        Map djMap = (Map)FriendHelper.getDjInfo(request);
//        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
//        Map map = new HashMap();
//        map.put("BASQWSH", BASQWSH);
//        map.put("jsjdm", jbsj.getJsjdm());
//        map.put("type","SHOW");
//        voPackage.setData(map);
//
//        // ���ú�̨������ȡ�÷���ֵ
//        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
//            voPackage);
//
//        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"");
//        //��������
//        String strXml = vo.toXML();
//        Debug.out(strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
////        request.setAttribute("basqwsh", BASQWSH);
//
//        // ת����ʾҳ��
//        return CAConstants.SHOW;
//    }
//
//    /**
//     * ¼�뱸������ ��ʼ������
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public String doAdd(HttpServletRequest request,
//                        HttpServletResponse response) throws BaseException {
//
//        List codeTable1 = CodeTableUtil.getCodeTableList(request,
//            CodeTable.JMBA_ZYSBLX_LIST);
//        List codeTable2 = CodeTableUtil.getCodeTableList(request,
//            CodeTable.JMBA_ZYSBLX_LIST);
//        for(int i=0,c=codeTable1.size();i<c;i++){
//            System.out.println(codeTable1.get(i).getClass());
//        }
//
//
//        // ȡ��userdata
//        UserData userdata = (UserData)this.getUserData(request);
//        // ����VOPackage
//        VOPackage voPackage = new VOPackage();
//        // �趨vopackage����
//        voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
//        voPackage.setUserData(userdata);
//        //@todo �ӵڶ�����תҳ���request�л�ȡ
//        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
//        //test if
//        if(BASQWSH == null)
//            BASQWSH = "062008000002";
//        Map djMap = (Map)FriendHelper.getDjInfo(request);
//        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
//        Map map = new HashMap();
//        map.put("BASQWSH", BASQWSH);
//        map.put("jsjdm", jbsj.getJsjdm());
//        map.put("type","ADD");
//
//        voPackage.setData(map);
//
//        // ���ú�̨������ȡ�÷���ֵ
//        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
//            voPackage);
//
//        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"ADD");
//        //��������
//        String strXml = vo.toXML();
//        Debug.out(strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
//        request.getSession(false).setAttribute("codeTable1", codeTable1);
//        request.getSession(false).setAttribute("codeTable2", codeTable2);
//        request.getSession(false).setAttribute("buss", "Save");
//        // ת����ʾҳ��
//        return CAConstants.ADDSHOW;
//    }
//
//
//    /**
//     * ¼�뱸������ ��ʼ������
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public String doDel(HttpServletRequest request,
//                        HttpServletResponse response) throws BaseException {
//
//        String selIndex =request.getParameter("selIndex");
//        System.out.println("selIndex = "+selIndex);
//
//        // ȡ��userdata
//        UserData userdata = (UserData)this.getUserData(request);
//        // ����VOPackage
//        VOPackage voPackage = new VOPackage();
//        // �趨vopackage����
//        voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//        voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
//        voPackage.setUserData(userdata);
//        //@todo �ӵڶ�����תҳ���request�л�ȡ
//        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
//        //test if
//        if(BASQWSH == null)
//            BASQWSH = "062008000002";
//        Map djMap = (Map)FriendHelper.getDjInfo(request);
//        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
//        Map map = new HashMap();
//        map.put("BASQWSH", BASQWSH);
//        map.put("selIndex", selIndex);
//        map.put("type","DEL");
//
//        voPackage.setData(map);
//
//        // ���ú�̨������ȡ�÷���ֵ
//        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
//            voPackage);
//
//
//        // ת����ʾҳ��
//        return  doShow(request,response);
//    }
//
//
//    /**
//     * ¼�뱸������ ��ʼ������
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public String doEditor(HttpServletRequest request,
//                           HttpServletResponse response) throws BaseException {
//        List codeTable1 = CodeTableUtil.getCodeTableList(request,
//            CodeTable.JMBA_ZYSBLX_LIST);
//        List codeTable2 = CodeTableUtil.getCodeTableList(request,
//            CodeTable.JMBA_ZYSBLX_LIST);
//        String selIndex = request.getParameter("selIndex");
////        System.out.println("selIndex = "+selIndex);
//
//        // ȡ��userdata
//        UserData userdata = (UserData)this.getUserData(request);
//        // ����VOPackage
//        VOPackage voPackage = new VOPackage();
//        // �趨vopackage����
//        voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
//        voPackage.setUserData(userdata);
//        //@todo �ӵڶ�����תҳ���request�л�ȡ
//        String BASQWSH = (String)request.getSession(false).getAttribute("basqwsh");
//        //test if
//        if(BASQWSH == null)
//            BASQWSH = "062008000002";
//        Map djMap = (Map)FriendHelper.getDjInfo(request);
//        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
//        Map map = new HashMap();
//        map.put("BASQWSH", BASQWSH);
//        map.put("selIndex", selIndex);
//        map.put("type", "EDITOR");
//
//        voPackage.setData(map);
//
//        // ���ú�̨������ȡ�÷���ֵ
//        voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
//            voPackage);
//        JmbaVO vo = this.convertToXmlVO( (Map)voPackage.getData(), userdata, jbsj, BASQWSH,"");
//        //��������
//        String strXml = vo.toXML();
//        Debug.out(strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
//        request.getSession(false).setAttribute("codeTable1", codeTable1);
//        request.getSession(false).setAttribute("codeTable2", codeTable2);
//       request.getSession(false).setAttribute("buss", "Update");
//       request.getSession(false).setAttribute("selIndex", selIndex);
//
//        // ת����ʾҳ��
//        return CAConstants.ADDSHOW;
//    }
//
//
//
//    private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj, String BASQWSH,String type) {
//        //1 ���ϲ�VO
//        JmbaVO vo = new JmbaVO();
//        //2 ��˰��VO 1.set 2
//        NsrxxVO nsrxx = new NsrxxVO();
//
//        nsrxx.setJsjdm(jbsj.getJsjdm());
//        nsrxx.setNsrmc(jbsj.getNsrmc());
//        nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
//
//        //1.set 2
//        vo.setNsrxx(nsrxx);
//        //3 ����VO 1-n ��������� 1.list.add 3s
//        JmbaZbVO zbvo = (JmbaZbVO)map.get("JmbaZbVO");
//        zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM14A);
//        zbvo.setBasqwsh(BASQWSH);
//        if(zbvo.getQysdsjmba().isEmpty()&& type.equals("ADD")) {
//            Jmba14AVO mxvo = new Jmba14AVO();
//            mxvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
//            mxvo.setCjr(jbsj.getJsjdm());
//            mxvo.setCjrq(DateUtilPro.getCurYearStr4());
//            mxvo.setLrr(jbsj.getJsjdm());
//            mxvo.setLrrq(DateUtilPro.getCurYearStr4());
//            zbvo.getQysdsjmba().add(mxvo);
//        }
//        //db query result,set zbvo data
//        //zbvo.setJmbasxdm
//
//        //SF_JL_QYSDSJMSBAJLZLQD
////        List zlqdVOs = (List) map.get("JmbaZlqdVO");
//        //4 ��ϸ����VO 1-ns   3.list.add 4s
////        Jmba13AVO vo13a = (Jmba13AVO)map.get("Jmba13VO");
////
////        //3.list.add 4s
////        zbvo.getQysdsjmba().add(vo13a);
////        �����嵥��ѯVO����,����ҳ����ʱ������
////        zbvo.getBajlzlqd().add(zlqdVOs);
//        //1.list.add 3s
//        vo.getJmsbajl().add(zbvo);
//        vo.setNsrxx(nsrxx);
//        vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
//        vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
//        vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA15);
//
//        return vo;
//    }
//
//
//    public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
//  {
//
//      if (!isTokenValid(request))
//      {
//          return CAConstants.INVALIDTOKEN;
//      }
//
//      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
//      // ����VOPackage
//      VOPackage voPackage = new VOPackage();
//      UserData ud = (UserData) this.getUserData(request);
//      DzyjHelper dh = new DzyjHelper();
//      System.out.println("jmba xmldata" + xmldata);
//      DzyjsjVO dzyj =  new DzyjsjVO();
//      JmbaVO jmbavo = new JmbaVO();
//
//      try
//      {
//
//          if (ud.getCaflag())
//          {
//              try
//              {
//                  dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
//              }
//              catch (ApplicationException e)
//              {
//                  throw ExceptionUtil.getBaseException(e);
//              }
//          }
//          System.out.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++" );
//          try
//          {
//             // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
//              //wsksbvo = new WsksbVO();
//              System.out.println(jmbavo.toString());
//              System.out.println("-------------------");
//              System.out.println(xmldata);
//              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
//          }
//          catch (ApplicationException e)
//          {
//              throw ExceptionUtil.getBaseException(e);
//          }
//          System.out.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++" );
//
//              dzyj.setYwczlx(jmbavo.getYwczlx());
//              dzyj.setYwdm(jmbavo.getYwlx());
//              // ȡ�õǼ�����
//              Map djMap = (Map) FriendHelper.getDjInfo(request);
//              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
//
//              Timestamp now = new Timestamp(System.currentTimeMillis());
//              voPackage.setUserData(ud);
//              HashMap hm = new HashMap();
//
//              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
//              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
//              hm.put("type","Save");
//              voPackage.setData(hm);
//              voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//              voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
//              System.out.println("ִ�в���֮ǰҪ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//              voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
//              System.out.println("ִ�в���֮��Ҫ+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//
//              System.out.println("ִ�в���֮��Ҫ++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "xxxxxxxxxxxxxxxxx����ɹ���");
//              LogUtil.getInstance().log(FriendHelper.getUserData(request), "xxxxxxxxxxxxxxxxxxxxxxx����",
//                      (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
//              System.out.println("ִ�в���֮��Ҫ+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//
//              return  doShow(request,response);
//
//      }
//      catch (Exception e)
//      {
//          // /3.9.���ÿ�xml��ת��ʧ��ҳ��
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
//          System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());
//
//          throw ExceptionUtil.getBaseException(e);
//      }
//
//  }
//
//
//
//  public String doUpdate(HttpServletRequest request, HttpServletResponse response) throws BaseException
//  {
//
//      if (!isTokenValid(request))
//      {
//          return CAConstants.INVALIDTOKEN;
//      }
//      String selIndex = (String) request.getSession(false).getAttribute("selIndex");
//      System.out.println("selIndex !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!= "+selIndex);
//
//      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
//      // ����VOPackage
//      VOPackage voPackage = new VOPackage();
//      UserData ud = (UserData) this.getUserData(request);
//      DzyjHelper dh = new DzyjHelper();
//      System.out.println("jmba xmldata" + xmldata);
//      DzyjsjVO dzyj =  new DzyjsjVO();
//      JmbaVO jmbavo = new JmbaVO();
//
//      try
//      {
//
//          if (ud.getCaflag())
//          {
//              try
//              {
//                  dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
//              }
//              catch (ApplicationException e)
//              {
//                  throw ExceptionUtil.getBaseException(e);
//              }
//          }
//          System.out.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++" );
//          try
//          {
//             // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
//              //wsksbvo = new WsksbVO();
//              System.out.println(jmbavo.toString());
//              System.out.println("-------------------");
//              System.out.println(xmldata);
//              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
//          }
//          catch (ApplicationException e)
//          {
//              throw ExceptionUtil.getBaseException(e);
//          }
//          System.out.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++" );
//
//              dzyj.setYwczlx(jmbavo.getYwczlx());
//              dzyj.setYwdm(jmbavo.getYwlx());
//              // ȡ�õǼ�����
//              Map djMap = (Map) FriendHelper.getDjInfo(request);
//              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
//
//              Timestamp now = new Timestamp(System.currentTimeMillis());
//              voPackage.setUserData(ud);
//              HashMap hm = new HashMap();
//
//              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
//              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
//              hm.put("type","Update");
//              hm.put("selIndex",selIndex);
//              voPackage.setData(hm);
//              voPackage.setProcessor(ProcessorNames.QYGZA15_PROCESSOR);
//              voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
//              System.out.println("ִ�в���֮ǰҪ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//              voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
//              System.out.println("ִ�в���֮��Ҫ+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//
//              System.out.println("ִ�в���֮��Ҫ++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "xxxxxxxxxxxxxxxxx����ɹ���");
//              LogUtil.getInstance().log(FriendHelper.getUserData(request), "xxxxxxxxxxxxxxxxxxxxxxx����",
//                      (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
//              System.out.println("ִ�в���֮��Ҫ+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
//
//              return  doShow(request,response);
//
//      }
//      catch (Exception e)
//      {
//          // /3.9.���ÿ�xml��ת��ʧ��ҳ��
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
//          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
//          System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());
//
//          throw ExceptionUtil.getBaseException(e);
//      }
//
//  }
//
//
//
//    /**
//     * �������Ȩ�޽��м��
//     */
//    protected String beforePerform(HttpServletRequest request,
//            HttpServletResponse response) {
//        // ���Ȩ�� ������û�м������
//        // System.out.println("beforePerform");
////        if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
////        {
////
////            return CAConstants.NOPERMISSION;
////        }
//        return null;
//    }
//
//}
