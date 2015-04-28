package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.QysdsBbjcUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�˶��걨��Ϣ
 * </p>
 * 
 * @author yugw
 * @version 1.1
 */

public class QysdsBbjcAction extends SmsbAction{
	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QysdsnbForm
	 * @param httpServletRequest
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�˶��걨��Ϣ</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ����˰��Ȼ�����Ϣ��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}
	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsBbjcForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		System.out.println("��ҵ����˰�걨��ϢdoShow start");
		//��ȡ������Ϣ
		UserData userData = null;
		QysdsBbjcForm qysdsBbjcForm = (QysdsBbjcForm) form;
		qysdsBbjcForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qysdsBbjcForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor.QysdsBbjcProcessor");
		vo.setUserData(userData);
		try {
			//����process
			qysdsBbjcForm= (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			qysdsBbjcForm.setTbrq(sdf.format(curTime));
			List qysdssbfsList=qysdsBbjcForm.getQysdssbfsList();
			//���������list����session����ҳ�����
			request.getSession(false).setAttribute("qysdsbbjcList", qysdssbfsList);
			//���ò�ѯ���
			request.setAttribute(mapping.getAttribute(), qysdsBbjcForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
     * ��ѯ
     * @param mapping struts.action.ActionMapping
     * @param form QysdsbbjcForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping mapping, ActionForm form,
    HttpServletRequest request,HttpServletResponse response)throws BaseException{
        HttpSession session=request.getSession(false);
		//��ѯǰ���Session
		session.removeAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
    	UserData userData = null;
    	QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) form;
        userData = this.getUserData(request);
        SWDJJBSJ djsj = null;
		try {
			// �����ҵ�Ǽǻ�����Ϣ
			djsj = InterfaceDj.getJBSJ_New(qysdsbbjcForm.getJsjdm(), userData);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(qysdsbbjcForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor.QysdsBbjcProcessor");
        vo.setUserData(userData);
        try
        {
        	qysdsbbjcForm = (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
			//��ѯ�ɹ�
        	qysdsbbjcForm.setIsQuery("1");
            request.setAttribute(mapping.getAttribute(), qysdsbbjcForm);
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	qysdsbbjcForm.reset(mapping, request);
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }
	
	/**
	 * ҳ����ת,ȷ���걨��ʽ
	 * @param mapping
	 * @param QysdsBbjcForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doNextStep(ActionMapping mapping, ActionForm form,
	   HttpServletRequest request,HttpServletResponse response)throws BaseException{
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm)form;
		//QysdsBbjcUtil2014 util=new QysdsBbjcUtil2014();
		//QysdsBbjcForm xsQysdsbbjcForm=util.getAccount(qysdsbbjcForm);
		qysdsbbjcForm.setNsrmc((qysdsbbjcForm.getNsrmcSubmit()));
		qysdsbbjcForm.setNsrsbh(qysdsbbjcForm.getNsrsbhSubmit());
		qysdsbbjcForm.setSshymc(qysdsbbjcForm.getSshymcSubmit());
		qysdsbbjcForm.setSsjjlxmc(qysdsbbjcForm.getSsjjlxmcSubmit());
		System.out.println("��˰������22222"+(qysdsbbjcForm.getNsrmc()));
		//QysdsBbjcForm qysdsbbjcForm2 = qysdsbbjcForm;
		UserData userData=new UserData();
		VOPackage vo = new VOPackage();
		//���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_NEXTSTEPACTION);
		// �����������Data����,������QysdsbbjcForm����
		vo.setData(qysdsbbjcForm);
		// ���ñ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		// ����ProxyҪ���õ�processor����---QysdssbxxProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDS_BBJC_PROCESSOR);
		try {
		  // ����Proxy����ʼ��QysdsbbjcForm��ֵ
		  qysdsbbjcForm= (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
		  // ��QysdsbjcForm�Ķ����ֵ����request��
		  request.setAttribute(mapping.getAttribute(), qysdsbbjcForm);
		} catch (Exception ex) {
			System.out.println("�쳣��Ϣ��"+ex.getMessage());
			System.out.println("qysdsbbjcForm����˰������"+qysdsbbjcForm.getNsrmc());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		    System.out.println("��תǰ��"+qysdsbbjcForm.getSkssksrq());
		    String path=qysdsbbjcForm.getPath();
		    return new ActionForward(path);
	 }
	
	
}
