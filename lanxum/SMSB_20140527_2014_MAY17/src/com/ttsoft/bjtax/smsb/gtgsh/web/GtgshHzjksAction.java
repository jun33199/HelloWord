/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻���˰֤���ܽɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshHzjksAction extends SmsbAction {

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">���幤�̻�</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "���幤�̻����ܽɿ���");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/gtgsh/Gwszhz-000.htm");

    }

    /**
     * ҳ���ʼ��
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

        GtgshHzjksForm pf = (GtgshHzjksForm) form;
        try {
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            // ȡ�Ǽǽӿ�
//            ServiceProxy proxy = new ServiceProxy();
//            // ȡ��������Ϣ
//            YHZH yhzh = new YHZH();
//            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
//            Map djMap = proxy.getDjInfo(dsJsjdm);
//            if (djMap != null) {
//                List yhList = (List) djMap.get("YHZH");
//                if (yhList != null && yhList.size() > 0) {
//                    yhzh = (YHZH) yhList.get(0);
//                }
//            }
            // �ж��Ƿ�����
            if (LWUtil.isZsjgLW(getServlet().getServletContext(),
                                ud.getSsdwdm())) {
                pf.setLw("01");
            }
            else
            {
                pf.setLw("00");
            }

            pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            //����Ż�ñ����ļ��������
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��֯���ؼ��������

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_HZJKS_PROCESSOR);
            //����processor
            pf = (GtgshHzjksForm) ZhsbProxy.getInstance().process(vo);

            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Show");
    }

    /**
     * ��ѯ
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

        GtgshHzjksForm pf = (GtgshHzjksForm) form;
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(request);
        pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
        pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��ñ����ļ��������

        try {

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_HZJKS_PROCESSOR);
            //����processor
            pf = (GtgshHzjksForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }

    /**
     * ����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(request);
        GtgshHzjksForm pf = (GtgshHzjksForm) form;

        //��Ԥװ�ص���Ϣ��ò���д
        try {
            String columns[] = pf.getColumns();
            pf.setDataList(getValuesToList(request, columns));

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_HZJKS_PROCESSOR);

            //����processor
            pf = (GtgshHzjksForm) ZhsbProxy.getInstance().process(vo);

            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Save");
    }

    /**
     * һ˰һƱ������
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpys(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            GtgshHzjksForm pf1 = (GtgshHzjksForm) form;
            GtgshJksYpysForm pf = new GtgshJksYpysForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh()); //�ɿ�ƾ֤��
            pf.setSbhzdh(pf1.getSbhzdh()); //�걨���ܵ���
            pf.setHzlx(pf1.getHzlx()); //��������
            pf.setHzjsrq(pf1.getHzjsrq()); //���ܽ�������
            pf.setHzksrq(pf1.getHzksrq()); //���ܿ�ʼ����
            pf.setYpys(pf1.getYpys()); //һƱһ˰��ʶ

            pf.setFhbs("GtgshHz"); //���÷��ر�ʶ
            request.setAttribute("gtgshJksYpysForm", pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypys");
    }

    /**
     * һ˰��Ʊ������
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpds(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            GtgshHzjksForm pf1 = (GtgshHzjksForm) form;
            GtgshJksYpdsForm pf = new GtgshJksYpdsForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh());
            pf.setSbhzdh(pf1.getSbhzdh()); //�걨���ܵ���
            pf.setHzlx(pf1.getHzlx()); //��������
            pf.setHzjsrq(pf1.getHzjsrq()); //���ܽ�������
            pf.setHzksrq(pf1.getHzksrq()); //���ܿ�ʼ����
            pf.setYpys(pf1.getYpys()); //һƱһ˰��ʶ

            pf.setFhbs("GtgshHz"); //���÷��ر�ʶ
            request.setAttribute("gtgshJksYpdsForm", pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypds");
    }

    /**
     * ��ӡ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            GtgshHzjksForm yForm = (GtgshHzjksForm) form;
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            // ȡ�Ǽǽӿ�
            ServiceProxy proxy = new ServiceProxy();
            // ȡ��������Ϣ
            YHZH yhzh = new YHZH();
            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
            Map djMap = proxy.getDjInfo(dsJsjdm);
            if (djMap != null) {
                List yhList = (List) djMap.get("YHZH");
                if (yhList != null && yhList.size() > 0) {
                    yhzh = (YHZH) yhList.get(0);
                }
            }

            if(LWUtil.isZsjgLW(getServlet().getServletContext(),
                        ud.getSsdwdm()))
             {
                 //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                 JksqdPrintForm pf = new JksqdPrintForm();

                 pf.setH_jsjdm(yForm.getJsjdm()); // ���۵�λ���������
                 pf.setH_sbbh(yForm.getJkpzh()); // �걨���
                 pf.setSbbh(yForm.getJkpzh());
                 pf.setJsjdm(yForm.getJsjdm());

                 pf.setHeadSjly(CodeConstant.SMSB_SJLY_GTGSHHZ); //������Դ
                 pf.setLrr(ud.getYhid()); //¼����
                 pf.setYhdm(yhzh.getYhdm()); //���д���
                 pf.setYhmc(yhzh.getKhyhmc()); //��������
                 pf.setZh(yhzh.getZh()); //�����˺�
                 pf.setSwjgzzjgdm(ud.getSsdwdm()); //˰�������֯��������
                 pf.setActionType("Show");
                 request.setAttribute("jksqdPrintForm", pf);
                 return mapping.findForward("PrintJksqd");
             }
             else {
                 //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                 JksPrintForm pf = new JksPrintForm();
                 pf.setHeadJkpzh(yForm.getJkpzh());
                 pf.setHeadJsjdm(yForm.getJsjdm());
                 pf.setHeadSjly(CodeConstant.SMSB_SJLY_GTGSHHZ); //������Դ
                 pf.setActionType("Show");
                 request.setAttribute("jksPrintForm", pf);
             }
         } catch (Exception ex) {
         }
         return mapping.findForward("Print");
     }
 }
