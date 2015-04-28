/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ����Ӧ��˰�ѿ��걨ģ�顣</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbAction
    extends SmsbAction {
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                         "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>��Ӧ��˰���ѣ����걨 ");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "help/smsb/wynsk/wynsk-001.htm");
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

    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);

    try {
      //��Ԥװ�ص���Ϣ��ò���д
      WynsksbActionForm pf = (WynsksbActionForm) form;
      pf.setNsrmc("");
      pf.setZcdzlxdh("");
      pf.setSwjgzzjgdm("");
      pf.setSwjgzzjgmc("");

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
      //��õ�ǰ��userData����
      /* start added by huxiaofeng 2005.8.16*/
      //HashMap djMap = InterfaceDj.getDjInfo(pf.getJsjdm(), ud);
      HashMap djMap = InterfaceDj.getDjInfo_New(pf.getJsjdm(), ud);
      /* end added by huxiaofeng 2005.8.16*/

      //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ

      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

      pf.setNsrmc(jbsj.getNsrmc());
      pf.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
      pf.setZcdzlxdh(jbsj.getZcdzlxdh());
      pf.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
      //����processor
      pf = (WynsksbActionForm) ZhsbProxy.getInstance().process(vo);
      //����˰����������
      pf.setSkssrqArr(this.getSkssrq(new Date()));
      /* start added by huxiaofeng 2005.8.16*/
      //������˰��״̬
      if ("false".equals( (String) request.getAttribute("flag"))) {
         pf.setNsrzt("0");
      }
      else {
        pf.setNsrzt(jbsj.getNsrzt());
      }

      /* end added by huxiaofeng 2005.8.16*/

      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Query");
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
  public ActionForward doInit(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);

    try {
      //��Ԥװ�ص���Ϣ��ò���д
      WynsksbActionForm pf = (WynsksbActionForm) form;
      pf.setSbrq(SfDateUtil.getDate());
      //˰���������ڣ��������շ�ʽ
      HashMap skssrq = (HashMap) Skssrq.monthSkssrq(new Date());
      pf.setSkssksrq(SfDateUtil.getDate( (Timestamp) skssrq.get(Skssrq.SKSSKSRQ)));
      pf.setSkssjsrq(SfDateUtil.getDate( (Timestamp) skssrq.get(Skssrq.SKSSJSRQ)));
      //����˰����������
      pf.setSkssrqArr(this.getSkssrq(new Date()));
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Init");
  }

  /**
   * �����걨����
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doSave(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException {
    //��ֹrefresh
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null) {
      return forward;
    }

    WynsksbActionForm form = (WynsksbActionForm) actionForm;

    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_SAVEACTION);
    vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
    vo.setData(form);
    //����ͨ���ܿصõ����û���Ϣ
    vo.setUserData(this.getUserData(httpServletRequest));
    try {
      WynsksbActionForm returnForm = (WynsksbActionForm) ZhsbProxy.getInstance().
          process(vo);

//      return actionMapping.findForward("Init");
      /* start added by huxiaofeng 2005.8.16*/
      httpServletRequest.setAttribute("flag", "false");
      /* end added by huxiaofeng 2005.8.16*/
      return this.doQuery(actionMapping, actionForm, httpServletRequest,
                          httpServletResponse);
    }
    catch (Exception ex) {

      throw ExceptionUtil.getBaseException(ex, "�����걨����ʧ��!");
    }
  }

  /**
   * �����걨���ݲ����ɽɿ���
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doDelete(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
      BaseException {
    //��ֹrefresh
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null) {
      return forward;
    }

    WynsksbActionForm form = (WynsksbActionForm) actionForm;
    //�õ��б�����

    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_DELETEACTION);
    vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
    vo.setData(form);
    //����ͨ���ܿصõ����û���Ϣ
    vo.setUserData(this.getUserData(httpServletRequest));
    try {
      WynsksbActionForm returnForm = (WynsksbActionForm) ZhsbProxy.getInstance().
          process(vo);

      //ת�Ƶ��ɿ���ά�����棬ֻ��ʾ�������ɵĽɿ���
//      return actionMapping.findForward("Delete");
      /* start added by huxiaofeng 2005.8.16*/
      httpServletRequest.setAttribute("flag", "false");
      /* end added by huxiaofeng 2005.8.16*/

      return this.doQuery(actionMapping, actionForm, httpServletRequest,
                          httpServletResponse);
    }
    catch (Exception ex) {

      throw ExceptionUtil.getBaseException(ex, "ɾ���걨����ʧ��!");
    }
  }

  /**
   * �õ�ǰ̨��ת���걨����ʹ�õ�˰����������
   * @param  rq �걨����
   * @return  js����['��','','����','��','']
   */
  private String getSkssrq(Date rq) {
    StringBuffer ret = new StringBuffer();
    Map m = Skssrq.monthSkssrq(rq);
    //�·ݵ�˰����������
    ret.append("var skssrqArr = ['").append(
        SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSKSRQ))
        ).append("','").append(
        SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSJSRQ))
        ).append("',");
    //���ȵ�˰����������
    Map q = Skssrq.quarterSkssrq(TinyTools.addMonth( -1, rq));
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) q.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) q.get(
        Skssrq.SKSSJSRQ))).append("',");
    //��
    Map y = Skssrq.yearSkssrq(rq);
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) y.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) y.get(
        Skssrq.SKSSJSRQ))).
        append("',");
    //��������
    Map qq = Skssrq.otherSkssrq(rq);
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) qq.get(
        Skssrq.SKSSJSRQ))).
        append("']");
    return ret.toString();

  }

}
