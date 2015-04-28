package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.creationstar.bjtax.qsgl.util.StringUtils;
import com.ttsoft.common.exceptions.TtsoftMessage;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.QueryArrayList;
import com.ttsoft.common.util.QueryCache;
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
public class PlslAction2 extends QueryBaseAction {
    /**
     * 即时办理--查询申报相关信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO 新增方法，获得ReceviePlslAction整理返回的数据（一个list），对这个list进行重新审核。
     *
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            QueryPlslForm2 queryForm = (QueryPlslForm2) form;
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            Connection conn = null;
            boolean flag;
            //新添加的数据
            ArrayList newdata = new ArrayList();
            //已经存在的旧数据
            ArrayList olddata = new ArrayList();

            //获取数据
            ArrayList list = new ArrayList();
            list = queryForm.getDrsj();
            if (list == null || list.size() == 0) {
                request.setAttribute(Constants.MESSAGE_KEY, "没有导入数据！");
                return mapping.findForward("back");
            }
            //查询已经存在性
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Drzb zb = (Drzb) list.get(i);
                    try {
                        conn = QSDBUtil.getConnection();
                        //个人的情况
                        if (zb.getNsrjsjdm() == null) {
                            //个人时，取证件类型、证件号码
                            PldrBo bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.
                                    getDrsjnr());
                            Grxx gr = bo.getGrxx();
                            flag = DAOFactory.getInstance().getDrzbDAO().
                                   querydrGrxx2(zb.getFdcxmmc(), zb.getFdcdz(),
                                                gr.getSfzjlx(), gr.getSfzjhm(),
                                                gr.getNsrmc(), conn);
                            //非个人
                        } else {
                            flag = DAOFactory.getInstance().getDrzbDAO().
                                   querydrFgrxx2(zb.getFdcxmmc(), zb.getFdcdz(),
                                                 zb.getNsrjsjdm(), zb.getNsrmc(),
                                                 conn);
                        }

                        if (!flag) {
                            newdata.add(zb);
                        } else {
                            olddata.add(zb);
                        }
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        QSDBUtil.freeConnection(conn);
                    }
                }
            }
            // 将结果构造成通用的QueryCache对象，以便翻页使用。
            QueryCache cache = new QueryCache(newdata, userData.myxszds);
            queryForm.setQueryCache(cache);
            //将页面显示状态设定为显示查询结果
            queryForm.setStatus("Result");
            queryForm.setSize(olddata.size());
            //新数据
            queryForm.setDrsj(newdata);
            //曾导入过的数据
            queryForm.setResultList(olddata);
            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }
    }

    /**
     * 查询--显示详细信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doView(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        QueryPlslForm2 aForm = (QueryPlslForm2) form;
        request.setAttribute(Constants.MESSAGE_KEY, "详细页面显示成功！");
        if (aForm.getDrlx().equals("0")) {
            //显示的是新导入的数据
            return mapping.findForward("view");
        } else {
            //显示的是重复导入的数据
            return mapping.findForward("viewOld");
        }
    }

    /**
     * 受理记录
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doCheck(ActionMapping mapping,
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
            QueryPlslForm2 queryForm = (QueryPlslForm2) form;
            ArrayList list = queryForm.getDrsj();
            ArrayList olddat = queryForm.getResultList();

            String selstr = queryForm.getSelcfdr();
            //重新构造链表
            if (StringUtils.killNull2(selstr) != null) {
                if (selstr.length() >= 2) {
                    selstr = selstr.substring(0, selstr.length() - 1);
                    String[] arr = selstr.split(",");
                    if (arr.length > 0) {
                        for (int i = 0; i < arr.length; i++) {
                            Drzb zb = (Drzb) olddat.get(Integer.parseInt(arr[i]));
                            list.add(zb);
                        }
                    }
                }
            }
            ArrayList inputList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Drzb dz = (Drzb) list.get(i);
                    PldrBo2 bo = new PldrBo2();
                    bo = (PldrBo2) QsglPcclXmlUtil.getRecord2(dz.getDrsjnr());
                    bo.setPch(dz.getDrpch());
                    bo.setXh(dz.getXh());
                    inputList.add(bo);
                }
            }
            //调用QsglProxy的check方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.CHECK);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(inputList);

            HashMap errMap = (HashMap) QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);
            request.setAttribute("map", errMap);
            request.setAttribute("from", "dr");
            // 删除session中的form
            removeForm(mapping, request);
            // 转向成功后的界面。
            request.setAttribute(Constants.MESSAGE_KEY, "数据验证完成！");
            return mapping.findForward("receive");
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

}
