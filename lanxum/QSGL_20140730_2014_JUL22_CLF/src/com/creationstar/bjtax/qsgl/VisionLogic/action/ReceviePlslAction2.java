package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ReceivePlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 * TODO C 1、增加方法，跳转到审核出错的错误数据修改页面
 * 2、增加方法，审核出错的信息修改后，返回后台，后台获得返回的数据，
 * 		更新后根据页面操作合并所有的页面数据为一个list，跳转到plslAction，
 * 		在其中新建方法，把合并后的数据重新审核一遍，调用doCheck方法类似的过程
 *
 * 说明：该action是针对页面中"批量受理(税务人员)"模块
 */
public class ReceviePlslAction2 extends BaseAction {
    /**
     * 受理记录
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReceive(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // 从Request中获取对象。
            Object obj = pForm.getOkList();

            //调用QsglProxy的check方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.RECEIVE_DRZB);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            QsglProxy.getInstance().process(vo);
            // 保存Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
            if (pForm.getBack().equals("query")) {
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
                removeForm(mapping, request);
                return mapping.findForward("receivequery");
            }

        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }


    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            HashMap map = (HashMap) request.getAttribute("map");
            String from = (String) request.getAttribute("from");
            pForm.setBack(from);

            pForm.setCqbceList((ArrayList) map.get("cqbce"));
            pForm.setCqsyeList((ArrayList) map.get("cqsye"));
            pForm.setGfcjjgList((ArrayList) map.get("gfcjjg"));
            pForm.setGfsyeList((ArrayList) map.get("gfsye"));
            pForm.setNsrfgrdmList((ArrayList) map.get("nsrfgrdm"));
            pForm.setNsrfgrmcList((ArrayList) map.get("nsrfgrmc"));
            pForm.setNsrgrList((ArrayList) map.get("nsrgr"));
            pForm.setSpjeList((ArrayList) map.get("spje"));
            pForm.setSpyyList((ArrayList) map.get("spyy"));
            pForm.setSptsList((ArrayList) map.get("spts"));
            pForm.setFgrspList((ArrayList) map.get("fgrsp"));
            pForm.setTufwxxList((ArrayList) map.get("tufwxx"));
            pForm.setOkList((ArrayList) map.get("ok"));

            // 保存Token;
            saveToken(request);

            // 转向成功后的界面。
            if (pForm.getOkList() == null || pForm.getOkList().size() == 0) {
                request.setAttribute(Constants.MESSAGE_KEY, "数据验证完成，没有数据可以受理！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "数据验证完成，开始受理！");
            }

            return mapping.findForward("show");
        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }


    /**
     * 退出受理记录
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
        try {
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // 保存Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
            if (pForm.getBack().equals("query")) {
                // 转向成功后的界面。
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
                removeForm(mapping, request);
                return mapping.findForward("receivequery");
            }

        }

        catch (Exception ex) {
            removeForm(mapping, request);
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }


    /**
     * 不保存导入数据退出
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        try {
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // 从Request中获取对象。
            ArrayList list = pForm.getOkList();
            String pch = new String();
            if (list != null && list.size() > 0) {
                PldrBo2 bo = (PldrBo2) list.get(0);
                pch = bo.getPch();
            }
            Object obj = pch;
            //调用QsglProxy的check方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.DELETE_ALLDR);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            QsglProxy.getInstance().process(vo);
            // 保存Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
//             if (pForm.getBack().equals("query"))
//             {
//                 // 转向成功后的界面。
//                 request.setAttribute(Constants.MESSAGE_KEY, "受理完成！");
//                 removeForm(mapping, request);
//                 return mapping.findForward("receivequery");
//             }

        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }

}
