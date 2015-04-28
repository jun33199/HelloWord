package com.ttsoft.bjtax.shenbao.fangtu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuZhengceBVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class FangtuZhengceAction extends ShenbaoAction {

	Logger logger = Logger.getLogger(FangtuZhengceAction.class.getName());
	
	/**
	 * 初始化页面数据
	 */
	public ActionForward doInit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		try {

			FangtuZhengceForm form = (FangtuZhengceForm) actionForm;
			VOPackage vo = new VOPackage();
			
			FangtuZhengceBVO newForm = new FangtuZhengceBVO();
			newForm.setJmzc(form.getJmzc());
			
			vo.setProcessor(ConstantFangtu.JMZC_PROCESSOR);
			vo.setData(newForm);
			vo.setUserData(this.getUserData(httpServletRequest));

			newForm = (FangtuZhengceBVO) ShenbaoProxy.getInstance().process(vo);
			BeanUtils.copyProperties(form, newForm);
			
			return actionMapping.findForward("Init");
			
		} catch ( ApplicationException ae ) {
				logger.error(ae.getMessage(), ae);
				ActionErrors errors = new ActionErrors();
	            errors.add("err1", new ActionError("error.server.custom", ae.getMessage()));
				saveErrors(httpServletRequest, errors);
				
				return actionMapping.findForward("Init");
			
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}


}
