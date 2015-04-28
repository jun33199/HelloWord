package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpzfForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpzfBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票作废功能Action类:FpzfAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpzfAction extends AddBaseAction {
	/**
	 * doShow
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doShow(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpzfAction's doShow Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpzfForm fpzfForm = (FpzfForm)form;
		session.setAttribute(mapping.getName(), fpzfForm);
		try
		{
			//清空页面
			fpzfForm.resetPage();
			
			fpzfForm.setYhid(userData.yhid);
			fpzfForm.setYhmc(userData.yhmc);
			fpzfForm.setZhdm(userData.xtsbm1);
			fpzfForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpzfForm.getYhid();
			String zhdm =fpzfForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpzfForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpzfForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpzfForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//获得发票种类列表fpzlList
			fpzfForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//获得发票种类列表cxfpzlList
			ArrayList tempList = new ArrayList();
			
			if (fpzfForm.getFpzlList() != null && fpzfForm.getFpzlList().size() > 0)
			{
				for(int i =0 ;i<fpzfForm.getFpzlList().size(); i++)
				{
					FpzfForm tempForm = new FpzfForm();
					Fpzl tempfpzl = (Fpzl)fpzfForm.getFpzlList().get(i);
					tempForm.setCxfpzldm(tempfpzl.getFpzldm());
					tempForm.setCxfpzlmc(tempfpzl.getFpzlmc());
					
					tempList.add(tempForm);
					
				}
				fpzfForm.setCxfpzlList(tempList);
			}
			
			//获得录入人和录入时间
			fpzfForm.setLrr(userData.getYhmc());
			fpzfForm.setLrrq(DateTimeUtil.getCurrentDate());
			//Swjjzzlgmc
			fpzfForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpzfForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票页面显示成功！");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票作废页面显示失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("show");
	}
	
	/**
	 * doSave
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doSave(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpzfAction's doSave Method....");
        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpzfForm fpzfForm = (FpzfForm)form;
		session.setAttribute(mapping.getName(), fpzfForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			
			String swjgzzjgdm = fpzfForm.getSwjgzzjgdm(); //税务机关组织机构代码
			String fpkfdm = fpzfForm.getFpkfdm();  //发票库房代码
			String fpzldm = fpzfForm.getFpzldm(); //发票种类代码
			String qshm = fpzfForm.getQshm();  //起始号码
			
			FpzfBO bo = new FpzfBO();
			
			//判断发票号码是否有库存
			if(qshm != null && !qshm.equals(""))
			{
				//判断是否有库存
				fpzfForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fpzfForm.getFpkfdm(), fpzfForm.getFpzldm(), fpzfForm.getCxqshm()));
				if(fpzfForm.getFpbcxx() != null && !fpzfForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpzfForm);
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码不存在："+ fpzfForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}

			bo.setFpkfdm(fpkfdm);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setYhid(fpzfForm.getYhid());  //用户ID
			bo.setYhmc(fpzfForm.getYhmc());  //用户名称
			bo.setSwjgzzjgdm(fpzfForm.getSwjgzzjgdm()); //税务机关组织机构代码
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //税务机关组织机构名称
			
			Debug.out("FpzfAction doSave form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpzfBO)QsglProxy.getInstance().process(vo);
			fpzfForm.resetPage();
			request.setAttribute(Constants.MESSAGE_KEY, "发票作废信息保存成功！");
			
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票作废信息保存失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("save");
	}
	
	/**
	 * doBack
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doBack(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
    
    /**
	 * doClear
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doClear(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("clear");
    }
    
    /**
	 * doFpdk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doFpdk(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}
