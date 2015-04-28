package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbGrBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.ParameterUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddSbgrAction extends AddBaseAction {
    /**
     *
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
    	//added by zhangj start
    	SbGrForm sForm=(SbGrForm) form;
    	
    	//added by zhangj end
        HttpSession session = request.getSession(false);
        SbGrForm sbGrForm = new SbGrForm();
        session.setAttribute(mapping.getName(), sbGrForm);
        sbGrForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        Debug.out("sbGrForm.getJkfsList().size = " +
                  sbGrForm.getJkfsList().size());
        sbGrForm.setSfzjlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.ZJLX));
        sbGrForm.setGjdqList(ActionUtil.getCodeTables(session.getServletContext(),
                Constants.GJ));
        //判断来自补录还是正常申报
        String bl = (String) request.getParameter("bl");
        if (bl != null && bl.equals("1")) {
            sbGrForm.setBl(true);
        } else {
            sbGrForm.setBl(false);
        }

        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "个人申报显示成功！");
        if(sForm.getHtbh()!=null&&sForm.getHtbh()!=""){
    		//sForm.setSbbh("");
        	 sbGrForm.setHtbh(sForm.getHtbh());
    		 doQuery(mapping,sbGrForm,request,response);
    		
    	}
        return mapping.findForward("show");

    }

    /**
     * 根据输入的核定通知书字号获取减免税金额
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetJmsje(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbGrForm sbGrForm = (SbGrForm) form;

        // 保存Token;
        saveToken(request);
        try {
            HashMap map = CommonUtil.getZcspjg(sbGrForm.getHdtzszh());
            if (map == null) {
                request.setAttribute(Constants.MESSAGE_KEY, "没有指定审批结果表编号的减免信息！");
            } else {
                sbGrForm.setJmsje((String) map.get("jmse"));
                sbGrForm.setJmlydm((String) map.get("qtjmly"));
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "指定审批结果表编号的减免信息获取成功！");
            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "获取审批结果表减免信息失败!");
            return new ActionForward(mapping.getInput());
        }
        return mapping.findForward("show");

    }

    /**
     * 保存记录
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((BaseForm)form).getData();
     * 3. 调用BaseProxy的add方法，向数据库中增加一条记录。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. 保存Token;
     *       saveToken();
     * 5. 转向成功后的界面。
     *     return mapping.findForward("add");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // 从Form中获取对象。
            String[][] dataSource_grxx = ParameterUtil.
                                         getTableDataSource(request,
                    "dataSource_gm", 9);

            List l = ActionUtil.getGrData(dataSource_grxx,
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.ZJLXMAP),
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.GJMAP));
            Object obj = ((SbGrForm) form).getData(l);
            Debug.out("AddSbGrAction doAdd form.getData() obj.class is " +
                      obj.getClass().getName());
            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            //      Debug.out("form bo nsrmc: " + ((SbGrBo) obj).nsrmc);

            String sbbh = (String) QsglProxy.getInstance().process(vo);
            Debug.out("after add sbbh: " + sbbh);
            ((SbGrForm) form).setSbbh(sbbh);
            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            request.setAttribute(Constants.MESSAGE_KEY, "个人申报保存成功！");
            return mapping.findForward("save");
        } catch (BaseException te) {
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }


    public ActionForward doRedirect(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        String subaction = request.getParameter("subAction");

        return mapping.findForward(subaction);
    }

    /**
     * 取消当前操作。
     * 如果没有录土地房屋基本信息,删除这条申报数据
     *
     * 转向取消后应去的页面。
     *
     *
     *  1. 保存Token;
     *     saveToken();
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // 从Form中获取对象。
        SbGrForm sbForm = (SbGrForm) form;
        
        String sbbh = sbForm.getSbbh();
        if (!sbForm.isFwjbxxAdded()) {

            VOPackage vo = new VOPackage();
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.DELETE);
            vo.setUserData(this.getUserData());
            ArrayList delList = new ArrayList();
            delList.add(sbbh);
            vo.setData(delList);
            SbxxBo bo = new SbxxBo();
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            try {
                QsglProxy.getInstance().process(vo);
            } catch (Exception ex) {
                Debug.printException(ex);
                request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
                return new ActionForward(mapping.getInput());
            }
        }
        // 删除session中的form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    
    /**
     * 根据输入的合同编号获取纳税人信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
        
    	 //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        SbGrForm sbGrForm = (SbGrForm) form;
        sbGrForm.setAll_buyerInfo("");
        sbGrForm.setSfesf("00");
        SbGrBo bo=new SbGrBo();
        bo.setHtbh(sbGrForm.getHtbh());
        try {
             //获取存放在ServletContext中的processor-map.properties的数据
             Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
             //构造向后台传输用的VoPackage
             VOPackage vo = new VOPackage();
             vo.setAction(ActionType.QUERY);
             vo.setProcessor(prop.getProperty(bo.getClass().getName()));
             vo.setUserData(this.getUserData());
             vo.setData(bo);
             bo=(SbGrBo)QsglProxy.getInstance().process(vo);
             
             sbGrForm.setHtbh(bo.getHtbh());
             sbGrForm.setTime(bo.getTime());
             sbGrForm.setAddress(bo.getAddress());
             sbGrForm.setDivertType(bo.getDivertType());
             sbGrForm.setArea(bo.getArea());
             sbGrForm.setRmbPrice(bo.getRmbPrice());
             sbGrForm.setAll_buyerInfo(bo.getAll_buyerInfo());
             sbGrForm.setSfesf("01");
             //sbGrForm.setSmbs("1");
             
      
             request.setAttribute(Constants.MESSAGE_KEY, "获取房屋相关信息成功");
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");

    }

}
