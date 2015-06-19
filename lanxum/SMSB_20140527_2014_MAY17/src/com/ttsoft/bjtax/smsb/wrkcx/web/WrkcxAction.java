package com.ttsoft.bjtax.smsb.wrkcx.web;

import java.util.*;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import java.io.OutputStream;
import com.ttsoft.bjtax.smsb.wrkcx.WrkVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WrkcxAction
    extends SmsbAction {
  /**
   * ���ݼ����󣨰���Form��UserData����
   */
  private VOPackage vo = new VOPackage();
   int rowNum = 0;

  public WrkcxAction() {
  }



  /**
   * doShow
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WrkcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    ArrayList datalist = new ArrayList();

    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WrkcxActionForm) form;
      if (pf == null) {
        pf = new WrkcxActionForm();
      }
//      //����EJB
//      vo.setAction(CodeConstant.SMSB_SHOWACTION);
//      vo.setUserData(ud);
//      vo.setData(pf);
//      vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
      WrkcxActionForm pf1 = pf;
//      pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      if (pf1 == pf)
      {
        System.out.println("true================");
      }
      else
      {
        System.out.println("false================");
      }


      Map zgswjg = CodeTableUtil.getZgswjg(ud);
           // ����˰����
           //�û�����
           pf.setYhjb(ud.getYhjb());

           pf.setDataList(datalist);
           //����˰����
           Map zgsws = CodeTableUtil.getZgsws(ud);
           Map scjx_map=CodeTableUtil.getScjx(ud);

           pf.setZgswjg(zgswjg);
           System.out.println("zgswjg:"+zgswjg);
           pf.setZgsws(zgsws);
           System.out.println("zgsws:"+zgsws);
           pf.setScjx(scjx_map);
           pf.setSwjgdm(ud.getSsdwdm().substring(0, 2).concat("00"));
            if (ud.getYhjb().equals(CodeConstants.JBDM_FJ) || ud.getYhjb().equals(CodeConstants.JBDM_SJ)){
                 pf.setSwsdm("");
              }else{
                  pf.setSwsdm(ud.getSsdwdm());
              }



           //����˰�����
           String filterDm = null;
           Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
           List l = new ArrayList();
           l.addAll(zsswjg);

           request.getSession().setAttribute("zsswjg", l);
           //����
          Collection scjx=CodeTableUtil.getScjx(ud).values();
          List list= new ArrayList();
           list.addAll(zsswjg);

           request.getSession().setAttribute("scjx", list);


      List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                                              com.ttsoft.bjtax.sfgl.common.
                                              constant.CodeConstants.
                                              CODE_MAP_BEANLIST).getRecords();
      request.getSession().setAttribute("sklx_list", sklxList);
      //�Ǽ�ע������
            List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
            request.getSession().setAttribute("djzclx_list", djzclx);

            //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);

    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }

  /**
   * doShow
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQuery(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WrkcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WrkcxActionForm) form;
      if (pf == null) {
        pf = new WrkcxActionForm();
      }
      //����EJB
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setUserData(ud);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
      pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      Map zgswjg = CodeTableUtil.getZgswjg(ud);
          // ����˰����
          //�û�����
          pf.setYhjb(ud.getYhjb());
          //����˰����
          Map zgsws = CodeTableUtil.getZgsws(ud);
          Map scjx_map=CodeTableUtil.getScjx(ud);

          pf.setZgswjg(zgswjg);
          System.out.println("zgswjg:"+zgswjg);
          pf.setZgsws(zgsws);
          System.out.println("zgsws:"+zgsws);
          pf.setScjx(scjx_map);
          pf.setSwjgdm(ud.getSsdwdm().substring(0, 2).concat("00"));
           if (ud.getYhjb().equals(CodeConstants.JBDM_FJ) || ud.getYhjb().equals(CodeConstants.JBDM_SJ)){
                pf.setSwsdm("");
             }else{
                 pf.setSwsdm(ud.getSsdwdm());
             }



          //����˰�����
          String filterDm = null;
          Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
          List l = new ArrayList();
          l.addAll(zsswjg);

          request.getSession().setAttribute("zsswjg", l);
          //����
         Collection scjx=CodeTableUtil.getScjx(ud).values();
         List list= new ArrayList();
          list.addAll(zsswjg);

          request.getSession().setAttribute("scjx", list);


     List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                                             com.ttsoft.bjtax.sfgl.common.
                                             constant.CodeConstants.
                                             CODE_MAP_BEANLIST).getRecords();
     request.getSession().setAttribute("sklx_list", sklxList);
     //�Ǽ�ע������
           List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
           request.getSession().setAttribute("djzclx_list", djzclx);

      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setUserData(ud);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
      try {
        pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex1) {
        ex1.printStackTrace();
      }
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);

      throw new ApplicationException("��ѯ�걨��ⲻһ����Ϣ����ʧ��");

    }
    return mapping.findForward("Show");
  }

  /**
   * ����Excel�ļ�����
   * @param mapping The ActionMapping used to select this instance
   * @param aFrom The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */

  public ActionForward doSaveExcel(ActionMapping mapping, ActionForm aForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws
      BaseException {
    WrkcxActionForm pf = (WrkcxActionForm) aForm;
     UserData ud = this.getUserData(request);
    try {
      vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
      vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
      vo.setData(pf);
      vo.setUserData(this.getUserData(request));
      pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      Map zgswjg = CodeTableUtil.getZgswjg(ud);
         // ����˰����
         //�û�����
         pf.setYhjb(ud.getYhjb());
         //����˰����
         Map zgsws = CodeTableUtil.getZgsws(ud);
         Map scjx_map=CodeTableUtil.getScjx(ud);

         pf.setZgswjg(zgswjg);
         System.out.println("zgswjg:"+zgswjg);
         pf.setZgsws(zgsws);
         System.out.println("zgsws:"+zgsws);
         pf.setScjx(scjx_map);
         pf.setSwjgdm(ud.getSsdwdm().substring(0, 2).concat("00"));
          if (ud.getYhjb().equals(CodeConstants.JBDM_FJ) || ud.getYhjb().equals(CodeConstants.JBDM_SJ)){
               pf.setSwsdm("");
            }else{
                pf.setSwsdm(ud.getSsdwdm());
            }



         //����˰�����
         String filterDm = null;
         Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
         List l = new ArrayList();
         l.addAll(zsswjg);

         request.getSession().setAttribute("zsswjg", l);
         //����
        Collection scjx=CodeTableUtil.getScjx(ud).values();
        List list= new ArrayList();
         list.addAll(zsswjg);

         request.getSession().setAttribute("scjx", list);


    List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                                            com.ttsoft.bjtax.sfgl.common.
                                            constant.CodeConstants.
                                            CODE_MAP_BEANLIST).getRecords();
    request.getSession().setAttribute("sklx_list", sklxList);
    //�Ǽ�ע������
          List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
          request.getSession().setAttribute("djzclx_list", djzclx);

     //��дsession����
     request.setAttribute(mapping.getAttribute(), pf);


      if (pf.getDataList() == null || pf.getDataList().isEmpty()) {
        doShow(mapping, aForm, request, response);
        throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
      }
      else {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "�걨��ⲻһ��".concat(currDate).concat("��ѯ.xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
            fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        createMapCell(sheet, "����:", pf.getBs());
        System.out.println("pf.getBs():" + pf.getBs());
        createMapCell(sheet, "����:", pf.getHs());
        createMapCell(sheet, "�걨���ϼ�:", pf.getSbjehj());
        System.out.println("pf.getSbjehj():" + pf.getSbjehj());
        createMapCell(sheet, "�����ϼ�:", pf.getRkjehj());
        System.out.println("pf.getRkjehj():" + pf.getRkjehj());
        createMapCell(sheet, "���ϼ�:", pf.getCehj());
        System.out.println("pf.getCehj():" + pf.getCehj());
        HSSFRow row = null;
        ArrayList al = pf.getDataList();
        int length = al.size();
        row = sheet.createRow( (short) 7);
        createCell(wb, row, (short) 0, HSSFCellStyle.ALIGN_CENTER, "���������");
        createCell(wb, row, (short) 1, HSSFCellStyle.ALIGN_CENTER, "��˰������");
        createCell(wb, row, (short) 2, HSSFCellStyle.ALIGN_CENTER, "˰������");
        createCell(wb, row, (short) 3, HSSFCellStyle.ALIGN_CENTER, "Ԥ���Ŀ");
        createCell(wb, row, (short) 4, HSSFCellStyle.ALIGN_CENTER, "˰������");
        createCell(wb, row, (short) 5, HSSFCellStyle.ALIGN_CENTER, "�걨����");
        createCell(wb, row, (short) 6, HSSFCellStyle.ALIGN_CENTER,
                   "�����տ�����");
        createCell(wb, row, (short) 7, HSSFCellStyle.ALIGN_CENTER,
                   "�޽�����");
        createCell(wb, row, (short) 8, HSSFCellStyle.ALIGN_CENTER, "�걨���");
        createCell(wb, row, (short) 9, HSSFCellStyle.ALIGN_CENTER,
                   "�������");
        createCell(wb, row, (short) 10, HSSFCellStyle.ALIGN_CENTER,
                   "�����");
        createCell(wb, row, (short) 11, HSSFCellStyle.ALIGN_CENTER,
                   "˰��������ʼ����");
        createCell(wb, row, (short) 12, HSSFCellStyle.ALIGN_CENTER,
                   "˰��������������");
        createCell(wb, row, (short) 13, HSSFCellStyle.ALIGN_CENTER, "������");
        createCell(wb, row, (short) 14, HSSFCellStyle.ALIGN_CENTER, "�����ʶ");
        createCell(wb, row, (short) 15, HSSFCellStyle.ALIGN_CENTER, "������Ա");

        createCell(wb, row, (short) 16, HSSFCellStyle.ALIGN_CENTER, "¼����");
        createCell(wb, row, (short) 17, HSSFCellStyle.ALIGN_CENTER, "¼������");
        createCell(wb, row, (short) 18, HSSFCellStyle.ALIGN_CENTER, "��������");
        createCell(wb, row, (short) 19, HSSFCellStyle.ALIGN_CENTER, "������Դ");

        createCell(wb, row, (short) 20, HSSFCellStyle.ALIGN_CENTER, "���ջ���");

        for (int i = 1; i <= length; i++) {
          WrkVo kvo = (WrkVo) al.get(i - 1);
          row = sheet.createRow( (short) (i + 7));
          createCell(wb, row, (short) 0, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getJsjdm());
          createCell(wb, row, (short) 1, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getNsrmc());
          createCell(wb, row, (short) 2, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSklx());
          createCell(wb, row, (short) 3, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getYskmmc());
          createCell(wb, row, (short) 4, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSzmc());
          createCell(wb, row, (short) 5, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSbrq());
          createCell(wb, row, (short) 6, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getYhsksj());
          createCell(wb, row, (short) 7, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getXjrq());
          createCell(wb, row, (short) 8, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSjje());
          createCell(wb, row, (short) 9, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getZyrq());
          createCell(wb, row, (short) 10, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getRkje());
          createCell(wb, row, (short) 11, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSkssksrq());
          createCell(wb, row, (short) 12, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSkssjsrq());
          createCell(wb, row, (short) 13, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getClbj());
          createCell(wb, row, (short) 14, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getZwbs());
          createCell(wb, row, (short) 15, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getHxrmc());
          createCell(wb, row, (short) 16, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getLrr());
          createCell(wb, row, (short) 17, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getLrrq());
          createCell(wb, row, (short) 18, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getHxrq());
          createCell(wb, row, (short) 19, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getSjly());
          createCell(wb, row, (short) 20, HSSFCellStyle.ALIGN_CENTER,
                     kvo.getZsswjg());
        }

        // Write the output to a file
        //FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
         rowNum = 0;

        return null;
      }
    }
    catch (Exception e) {
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
        vo.setData(pf);
        vo.setUserData(this.getUserData(request));
        pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //����form
      request.setAttribute(mapping.getAttribute(), pf);
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * doChangePage
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doChangePage(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WrkcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WrkcxActionForm) form;
      if (pf == null) {
        pf = new WrkcxActionForm();
      }
      //����EJB
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setUserData(ud);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.WRKCX_PROCESSOR);
      pf = (WrkcxActionForm) ZhsbProxy.getInstance().process(vo);
      Map zgswjg = CodeTableUtil.getZgswjg(ud);
          // ����˰����
          //�û�����
          pf.setYhjb(ud.getYhjb());
          //����˰����
          Map zgsws = CodeTableUtil.getZgsws(ud);
          Map scjx_map=CodeTableUtil.getScjx(ud);

          pf.setZgswjg(zgswjg);
          System.out.println("zgswjg:"+zgswjg);
          pf.setZgsws(zgsws);
          System.out.println("zgsws:"+zgsws);
          pf.setScjx(scjx_map);
          pf.setSwjgdm(ud.getSsdwdm().substring(0, 2).concat("00"));
           if (ud.getYhjb().equals(CodeConstants.JBDM_FJ) || ud.getYhjb().equals(CodeConstants.JBDM_SJ)){
                pf.setSwsdm("");
             }else{
                 pf.setSwsdm(ud.getSsdwdm());
             }



          //����˰�����
          String filterDm = null;
          Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
          List l = new ArrayList();
          l.addAll(zsswjg);

          request.getSession().setAttribute("zsswjg", l);
          //����
         Collection scjx=CodeTableUtil.getScjx(ud).values();
         List list= new ArrayList();
          list.addAll(zsswjg);

          request.getSession().setAttribute("scjx", list);


     List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                                             com.ttsoft.bjtax.sfgl.common.
                                             constant.CodeConstants.
                                             CODE_MAP_BEANLIST).getRecords();
     request.getSession().setAttribute("sklx_list", sklxList);
     //�Ǽ�ע������
           List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
           request.getSession().setAttribute("djzclx_list", djzclx);

      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);


    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }

  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
        "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>δ����ѯ ");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "");
  }

  /**
   * doShow
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQuery_DjInfo(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
      BaseException {
    System.out.println("-------wrkcxAction--------------doQuery_DjInfo");

    return mapping.findForward("Query_DjInfo");
  }

  /**
   * Creates a cell and aligns it a certain way.
   *
   * @param wb        the workbook
   * @param row       the row to create the cell in
   * @param column    the column number to create the cell in
   * @param align     the alignment for the cell.
   */
  private static void createCell(HSSFWorkbook wb, HSSFRow row, short column,
                                 short align,
                                 String value) {
    HSSFCell cell = row.createCell(column);
    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(value);
  }

  /**
   * �ɶ�����Excel cell
   * @param sheet
   * @param Name ����
   * @param value  ֵ
   */
  private void createMapCell(HSSFSheet sheet, String Name, String value) {

    HSSFRow row = sheet.createRow( (short) (++rowNum));
    HSSFCell cell;
    cell = row.createCell( (short) 0);
    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(Name);
    cell = row.createCell( (short) 1);
    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(value);
  }

}