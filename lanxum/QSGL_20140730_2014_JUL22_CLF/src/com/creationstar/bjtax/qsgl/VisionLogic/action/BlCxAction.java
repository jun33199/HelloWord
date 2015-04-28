package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
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
 */
public class BlCxAction extends BaseAction {

    /**
     * 从页面通过完税证号获得完税证的汇总信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        BlJksForm aForm = (BlJksForm) form;

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        QueryBlJksBo bo = (QueryBlJksBo) aForm.getBo();

        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.GET);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QueryBlJksBo resultBo = (QueryBlJksBo) QsglProxy.getInstance().
                                    process(vo);

            if (resultBo != null) {
                aForm.setResultBo(resultBo);
                Sbjkzb sbjkzb = resultBo.getSbjkzb();
                ArrayList aList = new ArrayList();
                aList.add(sbjkzb);
                QueryCache cache = new QueryCache(aList,
                                                  this.getUserData().myxszds);
                aForm.setQueryCache(cache);
                //将页面显示状态设定为显示查询结果
                aForm.setStatus("Result");
                request.setAttribute(Constants.MESSAGE_KEY, "已经从库中获得了所要的缴款书数据！");
            } else {
                QueryCache cache = aForm.getQueryCache();
                cache.removeAll();
                aForm.setStatus("Query");
                request.setAttribute(Constants.MESSAGE_KEY, "没有符合条件的缴款书数据！");
            }
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    /**
     * 撤销缴款书（包含汇总方式、非汇总方式）
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCxJks(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        HttpSession session = request.getSession(false);
        BlJksForm aForm = (BlJksForm) form;
        VOPackage vo = new VOPackage();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //如果是汇总完税证方式生成的缴款书，调用wszProcessor的撤销方法
        QueryBlJksBo bo = aForm.getResultBo();
        bo.setSjly(aForm.getScfs());

        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.BL_REMOVECONNECT);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY,
                                 "成功撤销缴款书号为" + aForm.getJkpzh() + "的缴款书！");
            QueryCache cache = aForm.getQueryCache();
            cache.removeAll();
            aForm.setStatus("Query");

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("cx");
    }

}
