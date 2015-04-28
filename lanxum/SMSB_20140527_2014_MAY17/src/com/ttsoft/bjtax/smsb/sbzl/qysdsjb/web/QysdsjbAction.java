/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.QysdsjbHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbAction
    extends SmsbAction
{
//    private UserData userData = null;

  /**
   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
   * @param mapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param response HttpServletResponse
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�꼾����˰�걨��</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "��ҵ����˰�꼾����˰�걨��");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/sbzl/qysdsjb/qysdsjb-000.htm");

  }

  /**
   * ȡҵ����Ϣ
   * 1��ȡ��QysdsjbForm������һ��VOPackage��
   * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
   * 3������voPackage��Processor��Ϊ
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor��
   *         voPackage��Data��ΪQysdsjbForm
   * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
   * 	����SbzlProxy��process������
   * 4������doShow��������ȡҵ�����ݵĽ����
   * 5��ת��ҵ��ҳ�档
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward����תĿ��
   * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
   */

  public ActionForward doShow(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //��ǰ��ActionForm---QysdsjbForm
    try
    {
      QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
      //��ʼ�����ݴ�������
      UserData userData = this.getUserData(httpServletRequest);
      // Debug.out(userData.getYhid());
      qysdsjbForm.setLrr(userData.getYhid());

      VOPackage vo = new VOPackage();
      //���ú�̨����Actionֵ---SHOWACTION
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      //�����������Data����,������CzzsjdForm
      vo.setData(qysdsjbForm);
      vo.setUserData(userData);
      //����ProxyҪ���õ�processor����---QysdsjbProcessor
      vo.setProcessor(
          "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");

      //����Proxy����ʼ��CzzsjdForm��ֵ
      qysdsjbForm = (QysdsjbForm) SbzlProxy.getInstance().process(vo);

      //Debug.out(qysdsjbForm.getDataList());
      //��ת
      //���Ԥװ�ص���Ϣ
      //��õ�ǰ��userData����
      //��õ�ǰ��½���û�id����Ϊ¼����id
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      //form.setLrr(userData.);
      return actionMapping.findForward("Show");
    }
    catch (Exception ex)
    {
      //ϵͳ��׽�쳣�������쳣�����׳�
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ȡҵ����Ϣ
   * 1��ȡ��QysdsjbForm������һ��VOPackage��
   * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
   * 3������voPackage��Processor��Ϊ
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor��
   *         voPackage��Data��ΪQysdsjbForm
   * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
   * 	����SbzlProxy��process������
   * 4������doSave��������ȡҵ�����ݵĽ����
   * 5��ת��ҵ��ҳ�档
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward����תĿ��
   * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
   *
   */

  public ActionForward doQuery(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //��ǰ��ActionForm---QysdsjbForm
    Debug.out("Query");
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    UserData userData = this.getUserData(httpServletRequest);
    qysdsjbForm.setLrr(userData.getYhid());
    //��ʼ�����ݴ�������
    VOPackage vo = new VOPackage();
    //���ú�̨����Actionֵ----QUERYACTION
    vo.setAction(CodeConstant.SMSB_QUERYACTION);
    //�����������Data����,������CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //����ProxyҪ���õ�processor������
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //����Proxy����ʼ��CzzsjdForm��ֵ
      qysdsjbForm = (QysdsjbForm) SbzlProxy.getInstance().process(vo);

      Debug.out("���ﴫ�ݵ���DataList�е�����" + qysdsjbForm.getDataList());
      //��ת
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      return actionMapping.findForward("Query");
    }
    catch (Exception ex)
    {
      //ϵͳ��׽�쳣�������쳣�����׳�
      qysdsjbForm.reset(actionMapping, httpServletRequest);

      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ȡҵ����Ϣ
   * 1��ȡ��QysdsjbForm������һ��VOPackage��
   * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
   * 3������voPackage��Processor��Ϊ
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor��
   *         voPackage��Data��ΪQysdsjbForm
   * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
   * 	����SbzlProxy��process������
   * 4������doSave��������ȡҵ�����ݵĽ����
   * 5��ת��ҵ��ҳ�档
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward����תĿ��
   * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
   *
   */

  public ActionForward doSave(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null)
    {
      return forward;
    }

    //��ǰ��ActionForm---------QysdsjbForm
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    UserData userData = this.getUserData(httpServletRequest);
    qysdsjbForm.setLrr(userData.getYhid());
    //����columns�е��ֶ���ȡ����Ҫǰ̨�б����ݸ���czzsjdForm��DataList�У�
    String columns[] = qysdsjbForm.getColumns();
    qysdsjbForm.setDataList(getValuesToList(httpServletRequest, columns));
    Debug.out("���ﴫ�ݵ���DataList�е�����" + qysdsjbForm.getDataList());

    //��ʼ�����ݴ�������
    VOPackage vo = new VOPackage();
    //���ú�̨����Actionֵ-----SAVEACTION
    vo.setAction(CodeConstant.SMSB_SAVEACTION);
    //�����������Data����,�������������CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //����ProxyҪ���õ�processor������
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //����Proxy����ʼ��CzzsjdForm��ֵ
      SbzlProxy.getInstance().process(vo);
      //����ɹ�,��ת
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setJsjdm("");
      List dataList = QysdsjbHelper.getShowList();
      qysdsjbForm.setDataList(dataList);
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                      "����ɹ���");
      return actionMapping.findForward("Save");
    }
    catch (Exception ex)
    {
      //ϵͳ��׽�쳣�������쳣�����׳�
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ȡҵ����Ϣ
   * 1��ȡ��QysdsjbForm������һ��VOPackage��
   * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
   * 3������voPackage��Processor��Ϊ
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor��
   *        voPackage��Data��ΪQysdsjbForm
   * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
   * 	����SbzlProxy��process������
   * 4������doDelete��������ȡҵ�����ݵĽ����
   * 5��ת��ҵ��ҳ�档
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward����תĿ��
   * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
   *
   */

  public ActionForward doDelete(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null)
    {
      return forward;
    }

    //��ǰ��ActionForm---------QysdsjbForm
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    //��ʼ�����ݴ�������
    UserData userData = this.getUserData(httpServletRequest);
    //��ʼ�����ݴ�������
    VOPackage vo = new VOPackage();
    //���ú�̨����Actionֵ-----SAVEACTION
    vo.setAction(CodeConstant.SMSB_DELETEACTION);
    //�����������Data����,�������������CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //����ProxyҪ���õ�processor������
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //����Proxy����ʼ��CzzsjdForm��ֵ
      SbzlProxy.getInstance().process(vo);
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setJsjdm("");
      List dataList = QysdsjbHelper.getShowList();
      qysdsjbForm.setDataList(dataList);
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                      "ɾ���ɹ���");
      //����ɹ�,��ת
      return actionMapping.findForward("Delete");
    }
    catch (Exception ex)
    {
      //ϵͳ��׽�쳣�������쳣�����׳�
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

}
