package com.ttsoft.bjtax.smsb.jkcx.web;

import java.math.*;
import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

public class JkcxAction extends SmsbAction {
    public JkcxAction() {
    }

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
                                  HttpServletResponse response) {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">�ɿ��ѯ</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "�ɿ��ѯ");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/gtgsh/Gwszhz-000.htm");

    }

    public ActionForward doQuery(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {

        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        JkcxForm form = (JkcxForm) actionForm;

        //�������ݰ�
        VOPackage vo = new VOPackage();
        //����
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //����processor
        vo.setProcessor(CodeConstant.JKCX_PROCESSOR);
        //����actionForm
        vo.setData(form.getData());
        //����userDate
        vo.setUserData(ud);
        try {
            List retList = (List) ZhsbProxy.getInstance().process(vo);
            for (int i = 0; i < retList.size(); i++) {
                Sbjkzb zb = (Sbjkzb) retList.get(i);
                String sklxdm = zb.getSklxdm();
                if(sklxdm.startsWith("1"))
                {
                    zb.setSklxmc("����");
                }else if(sklxdm.startsWith("2"))
                {
                    zb.setSklxmc("�Ĵ����");
                }else if(sklxdm.startsWith("3"))
                {
                    zb.setSklxmc("����˰��");
                }else if(sklxdm.startsWith("4"))
                {
                    zb.setSklxmc("�Բ鲹˰");
                }else if(sklxdm.startsWith("5"))
                {
                    zb.setSklxmc("��鲹˰");
                }else if(sklxdm.startsWith("6"))
                {
                    zb.setSklxmc("������˰");
                }else if(sklxdm.startsWith("7"))
                {
                    zb.setSklxmc("����Ƿ˰");
                }else if(sklxdm.startsWith("8"))
                {
                    zb.setSklxmc("��������˰��");
                }
            }
            form.setDataList(retList);
            httpServletRequest.setAttribute("jkcxForm", form);
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.setAttribute("jkcxForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");
    }

    public ActionForward doDetail(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        UserData ud = this.getUserData(httpServletRequest);
        JkcxForm form = (JkcxForm) httpServletRequest.getAttribute("jkcxForm");

        //�������ݰ�
        VOPackage vo = new VOPackage();
        //����
        vo.setAction(CodeConstant.SMSB_PRINTACTION);
        //����processor
        vo.setProcessor(CodeConstant.JKCX_PROCESSOR);
        //����actionForm
        HashMap dataMap = new HashMap();
        dataMap.put("sbbh",form.getSbbh());
        vo.setData(dataMap);
        //����userDate
        vo.setUserData(ud);
        try {
            HashMap retMap = (HashMap) ZhsbProxy.getInstance().process(vo);
            List mxList = (List)retMap.get("mxList");
            Sbjkzb zb = (Sbjkzb)retMap.get("sbjkzb");
            form.setMxList(mxList);
            form.setZb(zb);
            BigDecimal hj = new BigDecimal(0);
            for(int i=0;i<mxList.size();i++)
            {
                Sbjkmx mx = (Sbjkmx)mxList.get(i);
                mx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM",mx.getSzsmdm()));
                hj = hj.add(mx.getSjse());
            }
            form.setHj(hj);
            if(zb.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
                ZRRJBSJ zrrJbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
                form.setNarmc(zrrJbsj.getNsrmc());
            }else{
                SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(form.getJsjdm());
                form.setNarmc(dj.getNsrmc());
            }
            httpServletRequest.setAttribute("jkcxForm", form);
        }catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.setAttribute("jkcxForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Detail");
    }

    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            JkcxForm yForm = (JkcxForm) form;
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
                 pf.setH_sbbh(yForm.getSbbh()); // �걨���
                 pf.setSbbh(yForm.getSbbh());
                 pf.setJsjdm(yForm.getJsjdm());

                 pf.setHeadSjly(yForm.getSjly()); //������Դ
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
                 pf.setHeadSjly(yForm.getSjly()); //������Դ
                 pf.setActionType("Show");
                 request.setAttribute("jksPrintForm", pf);
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
         return mapping.findForward("Print");
     }
}
