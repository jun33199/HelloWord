package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PlslForm2;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.Dom4jXMLTool;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
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
 *
 * 说明：该action是针对页面中"批量受理(税务人员)"模块
 */
public class ViewPlslAction2 extends BaseAction {
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm actionform,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");

        // aForm.createViewForm();
        String sfesf = request.getParameter("sfesf");
        String setz = request.getParameter("setz");

        /*
                 System.out.println("///////////////////////////");
                 System.out.println("导入类型:"+aForm.getDrlx());
                 System.out.println("新导入的数据:"+aForm.getDrsj().size());
                 System.out.println("重复导入的数据:"+aForm.getResultList().size());
                 System.out.println("税额调整："+setz);
                 System.out.println("是否二手房："+sfesf);
                 System.out.println("///////////////////////////");
         */

        if (sfesf == null || sfesf.equals("")) {
            sfesf = "00";
        }
        Drzb zb = null;
        //0:新导入1：重复导入
        if (aForm.getDrlx().equals("0")) {
            aForm.createViewForm();
            zb = (Drzb) aForm.getViewDataDetail();
        } else {
            ArrayList list = aForm.getResultList();
            zb = (Drzb) list.get(aForm.getViewIndex());
        }
        Dom4jXMLTool xmlTool = new Dom4jXMLTool();
        xmlTool.openXML(zb.getDrsjnr());
        String[] attrname = {"fieldName", "maxLength"};
        String[] attrvalue1 = {"SFESF", "20"};
        String[] attrvalue2 = {"SETZ", "20"};
        xmlTool.appendFinalNode("//row[@tableName='QSDB.QS_JL_TUFWXX']",
                                "Field", attrname, attrvalue1, sfesf);
        xmlTool.appendFinalNode("//row[@tableName='QSDB.QS_JL_TUFWXX']",
                                "Field", attrname, attrvalue2, setz);
        //System.out.println(xmlTool.toXMLString());
        zb.setDrsjnr(xmlTool.toXMLString());

        session.setAttribute("queryPlslForm2", aForm);
        return mapping.findForward("Save");
    }


    /**
     * 批量受理--察看受理详细信息(显示重复导入的数据)
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO C 增加保存修改导入信息的方法，导入信息修改后，返回后台，后台获得返回的数据，更新后修改QueryPlslForm中的对应的缓存数据，
     * 并返回到plslDetail.jsp plslDetail.jsp 页面中定义的每一个字段在PlslForm中均需要重新定义
     */
    public ActionForward doShowPage(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        PlslForm2 pform = (PlslForm2) form;
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");
        /*
                         System.out.println("///////////////////////////");
                         System.out.println("导入类型:"+aForm.getDrlx());
                         System.out.println("新导入的数据:"+aForm.getDrsj().size());
         System.out.println("重复导入的数据:"+aForm.getResultList().size());
                         System.out.println("///////////////////////////");
         */
        //aForm.createViewForm();
        //pform = (PlslForm) aForm.getViewForm();

        //构造要显示的信息
        ArrayList list = aForm.getResultList();
        Drzb zb = (Drzb) list.get(aForm.getViewIndex());
        PldrBo bo = new PldrBo();
        try {
            bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.getDrsjnr());
        } catch (Exception ex) {
            Debug.out("queryPlslForm中获取viewform时解析xml出错");
            ex.printStackTrace();
        }

        pform.setCqxxList(bo.getCqxxList());
        //非个人多产权人
        //bo.getFgrxx().setNsrmc(bo.getFgrnsrmc());
        //bo.getFgrxx().setJsjdm(bo.getFgrjsjdm());
        pform.setFgrxx(bo.getFgrxx());

        pform.setFwjhxxList(bo.getFwjhxxList());
        //个人多产权人时拼串
        bo.getGrxx().setNsrmc(bo.getNsrmc());
        bo.getGrxx().setSfzjhm(bo.getZjhm());
        bo.getGrxx().setLxdh(bo.getLxdh());
        pform.setGrxx(bo.getGrxx());

        pform.setGyzfxxList(bo.getGyzfxxList());
        pform.setSpjgxx(bo.getSpjgxx());
        pform.setTufwxx(bo.getTufwxx());
        //税额调整类型
        pform.setSetz(bo.getSetz());
        //System.out.println("税额调整类型:" + bo.getSetz());
        if (bo.getGrxx() != null) {
            //个人
            pform.setPerson("1");
        } else {
            //非个人
            pform.setPerson("0");
        }
        if (bo.getSpjgxx() != null) {
            //有审批
            pform.setSp("1");
        } else {
            //无审批
            pform.setSp("0");
        }
        if (bo.getCqxxList() != null && bo.getCqxxList().size() > 0) {
            //有拆迁
            pform.setCq("1");
        } else {
            //无拆迁
            pform.setCq("0");
        }
        if (bo.getGyzfxxList() != null && bo.getGyzfxxList().size() > 0) {
            //有公房
            pform.setGf("1");
        } else {
            //无公房
            pform.setGf("0");
        }
        if (bo.getFwjhxxList() != null && bo.getFwjhxxList().size() > 0) {
            //有房交
            pform.setFj("1");
        } else {
            //无房交
            pform.setFj("0");
        }
        if (bo.getTufwxx().getFwtdbmdm() != null &&
            !bo.getTufwxx().getFwtdbmdm().equals("")) {
            pform.setTdbm("1");
        } else {
            pform.setTdbm("0");
        }
        pform.setBack(aForm.getFrom());
        if (aForm.getFrom().equals("dr")) {
            pform.setDel("0");
        }
        if (aForm.getFrom().equals("cx") && aForm.getSl().equals("unreceived")) {
            pform.setDel("1");
        }

        request.setAttribute("plslForm", pform);
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "详细信息显示成功！");
        return mapping.findForward("show");

    }


    /**
     * 批量受理--察看受理详细信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO C 增加保存修改导入信息的方法，导入信息修改后，返回后台，后台获得返回的数据，更新后修改QueryPlslForm中的对应的缓存数据，
     * 并返回到plslDetail.jsp plslDetail.jsp 页面中定义的每一个字段在PlslForm中均需要重新定义
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        PlslForm2 pform = (PlslForm2) form;
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");
        aForm.createViewForm();
        pform = (PlslForm2) aForm.getViewForm();
        ArrayList list = pform.getGrxxList();
        //个人多产权人的情况
        if (list.size() > 1) {
            Grxx grxx = null;
            String nsrmc = "";
            String zjhm = "";
            String lxdh = "";
            for (int i = 0; i < list.size(); i++) {
                grxx = (Grxx) list.get(i);
                if (nsrmc.length() > 2) {
                    nsrmc = nsrmc + "," + grxx.getNsrmc();
                } else {
                    nsrmc = nsrmc + grxx.getNsrmc();
                }
                if (zjhm.length() > 2) {
                    zjhm = zjhm + "," + grxx.getSfzjhm();
                } else {
                    zjhm = zjhm + grxx.getSfzjhm();
                }
                if (lxdh.length() > 2) {
                    lxdh = lxdh + "," + grxx.getLxdh();
                } else {
                    lxdh = lxdh + grxx.getLxdh();
                }
            }
            pform.getGrxx().setNsrmc(nsrmc);
            pform.getGrxx().setSfzjhm(zjhm);
            pform.getGrxx().setLxdh(lxdh);
        }
        pform.setBack(aForm.getFrom());
        if (aForm.getFrom().equals("dr")) {
            pform.setDel("0");

        }
        if (aForm.getFrom().equals("cx") && aForm.getSl().equals("unreceived")) {
            pform.setDel("1");
        }
        request.setAttribute("plslForm2", pform);
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "详细信息显示成功！");
        return mapping.findForward("show");

    }


    /**
     * 删除选中的记录
     *
     *  1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     *  2. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  3. 获取选中的记录。
     *      ArrayList delList = queryForm.getSelectedData();
     *  4. 调用BaseProxy的delete方法删除。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      BaseProxy.delete(delList,userData);
     *  5. 删除Cache中的结果。
     *      queryForm.removeSeletedData(delList);
     *  6. 转向删除后的结果页面。
     *     return mapping.findForward("delete");
     *
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
            HttpSession session = request.getSession(false);
            QueryPlslForm2 queryForm = (QueryPlslForm2) session.getAttribute(
                    "queryPlslForm2");

            // 将参数中的Form构型为QueryBaseForm。
            PlslForm2 pForm = (PlslForm2) form;
            // 获取选中的记录。
            ArrayList delList = new ArrayList();
            delList.add(queryForm.getPlBo());
            Debug.out("delList size: " + delList.size());
            // 调用BaseProxy的delete方法删除。
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);
            VOPackage vo = new VOPackage();
            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.DELETE);
            vo.setData(delList);
            vo.setUserData(userData);
            vo.setProcessor(prop.getProperty(delList.get(0).getClass().getName()));

            QsglProxy.getInstance().process(vo);
//            QsglProxy.getInstance().delete(delList, userData);
            // 删除Cache中的结果。
            queryForm.removeSelectedData(delList);
            // 转向删除后的结果页面。
            return mapping.findForward("delete");
        } catch (BaseException te) {
            request.setAttribute(RequestKey.MESSAGE_KEY, te.getMessage());
            saveToken(request);
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
    }


    /**
     * 取消当前操作
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向返回后的页面。
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturncx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 删除form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);

        //转向返回后的页面。
        return mapping.findForward("returncx");
    }
}
