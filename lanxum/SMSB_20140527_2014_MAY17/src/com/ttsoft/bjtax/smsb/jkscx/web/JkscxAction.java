package com.ttsoft.bjtax.smsb.jkscx.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.util.*;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.jkscx.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.common.util.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JkscxAction extends SmsbAction {

    static final String[] TITLE = {"���", "���������", "�ɿ�ƾ֤��", "˰������", "�걨��ʽ", "����",
                                  "�Ǽ�ע������", "����˰�����", "���ջ���", "���ұ�׼��ҵ", "Ԥ���Ŀ",
                                  "Ԥ�㼶��",
                                  "˰��", "ʵ�ɽ��", "˰Ŀ", "��˰����", "��˰���", "ʵ��˰��",
                                  "�걨����", "˰��������ʼ����", "˰��������������", "��˰������",
                                  "����ʶ"
    };

    static final String[] COLUMS = {"index", "jsjdm", "jkpzh", "sklx", "sbfs",
                                   "yh",
                                   "djzclx", "zgswjg", "zsswjg", "gjbzhy",
                                   "yskmmc", "ysjcmc",
                                   "szmc", "zbsjje", "szsmmc", "kssl", "jsje",
                                   "sjje",
                                   "sbrq", "skssksrq", "skssjsrq", "nsrmc",
                                   "rkbs"
    };

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
        UserData userData = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //ִ��Processor
        try {
            //��ʼ��
            userData = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.reset(mapping, request);
            request.getSession().removeAttribute("initlist");
// ����˰�����
            Map zgswjg = CodeTableUtil.getZgswjg(userData);
            // ����˰����
            //�û�����
            pf.setYhjb(userData.getYhjb());
            //����˰����
            Map zgsws = CodeTableUtil.getZgsws(userData);

            pf.setZgswjg(zgswjg);
            pf.setZgsws(zgsws);
            pf.setSwjgdm(userData.getSsdwdm().substring(0, 2).concat("00"));
            if (userData.getYhjb().equals(CodeConstants.JBDM_FJ) ||
                userData.getYhjb().equals(CodeConstants.JBDM_SJ)) {
                pf.setSwsdm("");
            } else {
                pf.setSwsdm(userData.getSsdwdm());
            }
            //����˰�����
            String filterDm = null;
            Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
            List l = new ArrayList();
            l.addAll(zsswjg);

            request.getSession().setAttribute("zsswjg", l);
            pf.setSzdm("02");
//SZDM_ALL:select SZSMDM , SZSMDM||SZSMMC from DMDB.SB_DM_SZSM where length(szsmdm)=2 and zxbs='0' order by SZSMDM
            //˰��˰Ŀ����
            SfHashList szList = CodeManager.getCodeList("SZDM_ALL",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_MAPLIST);

//SMDM_ALL:select SZSMDM , SZSMDM||SZSMMC  from dmdb.SB_DM_SZSM where length(szsmdm)=6  and zxbs='0'  order by SZSMDM
            SfHashList smList = CodeManager.getCodeList("SMDM_ALL",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_MAPLIST);
            pf.setSzList(szList);
            pf.setSmList(smList);
            //�Ǽ�ע������
            List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
            request.getSession().setAttribute("djzclx", djzclx);
            //���ұ�׼��ҵ
            List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
            request.getSession().setAttribute("gjbzhy", gjbzhy);

            List yh = CodeTableUtil.getCodeTableList(CodeTableKey.YH);
            request.getSession().setAttribute("yh", yh);

            List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("sklx", sklxList);
            List yskmList = CodeManager.getCodeList("DM_YSKM",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("yskm", yskmList);
            List ysjcList = CodeManager.getCodeList("DM_YSJC",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("ysjc", ysjcList);
            List sbfsList = CodeManager.getCodeList("DM_SBFS",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("sbfs", sbfsList);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Show");

    }

    /**
     * doQuery
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

        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //ִ��Processor
        try {
            //��ʼ��
            ud = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.setCurPage("0");
            pf.setNextPage("1");
            pf.setTotalpage("0");
            pf.setPageSize(CodeConstant.JKS_PG_LENGTH + "");
            //PageRoll pr = new PageRoll();
            // pr.setPageSize(CodeConstant.JKS_PG_LENGTH);
            // pr.setNewQuery(true);

            pf.setMessage("");
            pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "��ѯ�����걨������ֹʱ��β��Ϸ�����������Ϊ�ջ�ɿ�ƾ֤��Ϊ�գ����޸ĺ����²�ѯ.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            Map result = (Map) ZhsbProxy.getInstance().process(vo);
            List tmpList = (List) result.get("DATALIST");
            Map mxMap = (HashMap)result.get("MXDATAMAP");
            Integer totalCount = (Integer) result.get("TOTALCOUNT");
            //request.getSession().setAttribute("initlist", tmpList);

            //pf.setInitList(tmpList);
            ///3.5.��ȡ��ҳ������
            pf.setTotalpage(getPageTotalCount(totalCount.intValue()));
            ///3.6.�����ѯ���
            HttpSession session = request.getSession();
            session.setAttribute("DATALIST",tmpList);
            session.setAttribute("MXDATAMAP",mxMap);
            pf.setDataList(tmpList);
            pf.setMxMap(mxMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");

    }

    /**
     * doChangePageA
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
        //��õ�ǰ��userData����
        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //ִ��Processor
        try {
            //��ʼ��
            ud = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "��ѯ�����걨������ֹʱ��β��Ϸ�����������Ϊ�ջ�ɿ�ƾ֤��Ϊ�գ����޸ĺ����²�ѯ.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            Map result = (Map) ZhsbProxy.getInstance().process(vo);
            List tmpList = (List) result.get("DATALIST");
            Map mxMap = (HashMap)result.get("MXDATAMAP");
            Integer totalCount = (Integer) result.get("TOTALCOUNT");

            ///3.5.��ȡ��ҳ������
//          pf.setTotalpage(getPageTotalCount(totalCount.intValue()));
            int nextPage = Integer.parseInt(pf.getNextPage());
            int totalPage = Integer.parseInt(pf.getTotalpage());
            if (nextPage > totalPage) nextPage = totalPage;
            pf.setNextPage(String.valueOf(nextPage));
            System.out.println(pf.getCurPage() + "  " + nextPage + "  " +
                               totalPage);
            ///3.6.�����ѯ���
            HttpSession session = request.getSession();
            session.setAttribute("DATALIST",tmpList);
            session.setAttribute("MXDATAMAP",mxMap);
            pf.setDataList(tmpList);
            pf.setMxMap(mxMap);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("ChangePage");

    }

    /**
     * doExport
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doExport(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {

        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //ִ��Processor
        try {
            //��ʼ��
            ud = this.getUserData(request);
            pf = (JkscxForm) form;

            //List tmpList = (List)request.getSession().getAttribute("initlist");

            pf.setMessage("");
            // pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "��ѯ�����걨������ֹʱ��β��Ϸ�����������Ϊ�ջ�ɿ�ƾ֤��Ϊ�գ����޸ĺ����²�ѯ.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            List tmpList = (List) ZhsbProxy.getInstance().process(vo);

            //����ļ�
            System.out.println("���Excel�ļ���¼����" + tmpList.size());
            if (tmpList == null || tmpList.size() <= 0) {
                pf.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
                return mapping.findForward("Export");
            }
            String currDate = TinyTools.Date2String(new Date(System.
                    currentTimeMillis()));
            String fileName = "�걨��ѯ���".concat(currDate).concat(".xls");
            String encodeFileName = com.ttsoft.framework.util.StringUtil.
                                    GBK2ISO(
                                            fileName);
            response.resetBuffer();
            response.setHeader("Content-disposition",
                               "attachment; filename=" + encodeFileName);
            response.setContentType("application/vnd.ms-excel");

            ExcelUtil.generateExcel(response.getOutputStream(), TITLE, COLUMS,
                                    tmpList);

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        //return mapping.findForward("Export");

    }

    public ActionForward doViewMx(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
        JkscxForm jkscxForm = (JkscxForm) form;
        jkscxForm.setDataList((List)request.getSession().getAttribute("DATALIST"));
        jkscxForm.setMxMap((Map)request.getSession().getAttribute("MXDATAMAP"));
        System.out.println("data list size:"+jkscxForm.getDataList());
        String sphm = request.getParameter("mxsphm");
        jkscxForm.makeMxDate(sphm);
        return mapping.findForward("Show");
    }

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>�걨�ɿ����ѯ ");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "");
    }

    /**
     * ��������ѯ�����Ϸ���
     * @param sbrqq �걨������
     * @param sbrqz �걨����ֹ
     * @return �Ϸ���־
     */
    private boolean checkInputValid(String sbrqq, String sbrqz) throws
            BaseException {
        //1.�������
        boolean returnFlag = true;
        int offset = -1;
        //2.��ʼ����ֵ
        Calendar calendar = new GregorianCalendar();
        //System.out.println("��ѯ�����걨������" + sbrqq + "|");
        //System.out.println("��ѯ�����걨����ֹ��" + sbrqz + "|");
        sbrqq = SBStringUtils.trim(sbrqq);
        sbrqz = SBStringUtils.trim(sbrqz);
        Date dsbrqq = SBStringUtils.getDateValue(sbrqq, "yyyyMMdd");
        Date dsbrqz = SBStringUtils.getDateValue(sbrqz, "yyyyMMdd");
        calendar.setTime(dsbrqq);
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);
        int maxday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        calendar.setTime(dsbrqz);
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH);
        int endDay = calendar.get(Calendar.DAY_OF_MONTH);

//3.��ʼҵ��
        offset = (endYear * 12 + endMonth) - (startYear * 12 + startMonth);
        if (offset > 1 || offset < 0) {
            returnFlag = false;
        } else {
            int offsetday = maxday * offset + endDay - startDay;
            //    System.out.println("��ѯ�����걨����offsetday��" + offsetday+":"+startDay+":"+maxday);
            if (offsetday > 10 || offsetday < 0) {
                returnFlag = false;
            }
        }
        //99.����ֵ


        return returnFlag;
    }

    /**
     * ��ȡҳ��
     * @param rsCount ��ѯ�����build
     * @return ҳ��
     */
    private String getPageTotalCount(int rsCount) {
        //1.�������
        String countTotal = "0";
        //2.��ʼҵ��
        int pageCount = 0;
        if ((rsCount % CodeConstant.JKS_PG_LENGTH) == 0) {
            pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH);
        } else {
            pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH) + 1;
        }
        countTotal = String.valueOf(pageCount);
        //99.����ֵ
        return countTotal;
    }
}
