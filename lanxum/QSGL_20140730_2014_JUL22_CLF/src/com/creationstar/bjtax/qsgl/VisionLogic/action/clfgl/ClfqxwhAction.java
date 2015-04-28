package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfqxwhForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title:存量房修改权限维护ACTION
 * </p>
 * <p>
 * Description: action
 * </p>
 * 
 * @author tujb
 * @version 1.1
 */
public class ClfqxwhAction extends AddBaseAction{
	
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
		Debug.out("into ClfqxwhAction's doShow Method....");
		
		UserData userData = (UserData) request.getSession().getAttribute(SessionKey.USER_DATA);
		//获得客户端提交的数据
		ClfqxwhForm qx = (ClfqxwhForm)form;
		// 清空FORM
		qx.clearForm();
		// 分页初始化
		qx.resetPage();
		// 每页行数
		qx.setMyhs(String.valueOf(userData.getMyxszds()));
		
	    ClfqxwhBo bo = new ClfqxwhBo();
	    
	    Debug.out("ClfqxwhAction doQuery form.getData() bo.class is：" + bo.getClass().getName());
	    
	    //获取存放在ServletContext中的processor-map.properties的数据
	    
	    Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	    VOPackage vo = new VOPackage();
	    vo.setAction(ActionType.QUERY);
	    vo.setProcessor(prop.getProperty(bo.getClass().getName()));      
	    vo.setUserData(this.getUserData());
	    vo.setData(bo);
	    try
		{
	    	bo = (ClfqxwhBo) QsglProxy.getInstance().process(vo);
	    	
	    	ArrayList jycsList = bo.getJycsList();
	    	
	    	if ((jycsList == null) || (jycsList.size() == 0))
            {
        		request.setAttribute(Constants.MESSAGE_KEY, "无查询结果!");
            }
	    	
	    	qx.setJycsList(jycsList);
            
            //设置当前登录人信息
            String lrrq;
            try 
            {
            	lrrq = DateTimeUtil.getCurrentDate();
            	UserData ud = super.getUserData();
            	Debug.out("lrr = " + ud.getYhmc()+"    lrrq = " + lrrq);
            	qx.setLrr(ud.getYhmc());
            	qx.setLrrq(lrrq);
            } 
            catch (BaseException e) 
            {
            	e.printStackTrace();
            }
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("show");
	}
    
    /**
     * confirm the user's infomation of Zhwh
     *
     * @param mapping the ActionMapping.
     * @param form the ActionForm.
     * @param request the HttpServletRequest.
     * @param response the HttpServletResponse.
     * @return ActionForward the ActionForward.
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
    {
        Debug.out("into ClfqxwhAction's doSave Method....");

        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
        System.out.println("新增人员信息+++++++++++++++++++++++++");
        ActionErrors errors = new ActionErrors();

        try
        {
            // 从SESSION获取FORM
        	ClfqxwhForm qx = (ClfqxwhForm)form;
            ClfqxwhBo bo = (ClfqxwhBo)qx.getData();
            
            System.out.println("新增人员信息+++++++++++++++++++++++++"+bo.getAllNewAddInfo());
            
            Debug.out("ClfqxwhAction doQuery form.getData() bo.class is：" +
    				bo.getClass().getName());

            //获取存放在ServletContext中的processor-map.properties的数据
            VOPackage vo = new VOPackage();
            vo.setData(bo);
            Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
            vo.setAction(ActionType.UPDATE);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));      
            vo.setUserData(this.getUserData());
            QsglProxy.getInstance().process(vo);
            
            
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(e.getMessage()));
            saveErrors(request, errors);
            
            this.doShow(mapping, form, request, response);
            
            request.setAttribute(Constants.MESSAGE_KEY, e.getMessage());

            return mapping.findForward("show");
        }

        this.saveToken(request);

        return this.doShow(mapping, form, request, response);
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
            HttpServletResponse response) 
    {
    	Debug.out("into ClfqxwhAction's doBack Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("back");
    }


        public ActionForward doDelete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        {
    	try{
    		
        	ClfqxwhForm qx = (ClfqxwhForm)form;
    		ClfqxwhBo bo = (ClfqxwhBo)qx.getData();
    		
    		System.out.println("对应值+++++++++++++++++++++++++++"+qx.getDyz());
    		
            //获取存放在ServletContext中的processor-map.properties的数据
            VOPackage vo = new VOPackage();
            vo.setData(bo);
            Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
            vo.setAction(ActionType.DELETE);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));      
            vo.setUserData(this.getUserData());   		
            QsglProxy.getInstance().process(vo);
            
            
    		
    		request.setAttribute(Constants.MESSAGE_KEY, "删除成功!");
    	}catch(Exception e){
    		
    		e.printStackTrace();
    	}
    	return doShow(mapping, form, request, response);
        }

}
