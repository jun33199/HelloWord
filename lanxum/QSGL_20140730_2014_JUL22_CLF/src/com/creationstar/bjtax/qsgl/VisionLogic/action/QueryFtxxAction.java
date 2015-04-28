package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryFtxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryFtxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QueryFtxxAction extends BaseAction {
    /**
     * 获取查询条件，调用后台接口，返回查询结果（QueryFtxxBo）。将查询结果赋值给form（调用form.setData()）
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryFtxxForm queryFtxxForm = (QueryFtxxForm) form;
        String tdfwid = queryFtxxForm.getTdfwid();
        // 保存Token;
        saveToken(request);

        try {
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            Tufwxx tufwxx = new Tufwxx();
            tufwxx.tdfwid = tdfwid;
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY_USAGE);
            vo.setProcessor(prop.getProperty(tufwxx.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(tufwxx);
            Debug.out("tdfwid:" + tdfwid);
            Object obj = QsglProxy.getInstance().process(vo);
            Debug.out("aaa: " + obj);
            QueryFtxxBo bo = (QueryFtxxBo) obj;
            queryFtxxForm.setAfterQuery(true);
            if (bo != null) {
                queryFtxxForm.setData(bo);
                queryFtxxForm.setExist(true);
                request.setAttribute(Constants.MESSAGE_KEY, "查询土地房屋使用情况成功！");
            } else {
                queryFtxxForm.clearResult();
                queryFtxxForm.setExist(false);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "不存在指定土地、房屋唯一标识的土地房屋使用信息！");
            }
        } catch (BaseException ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "查询土地房屋使用情况失败！");
        }

        return mapping.findForward("show");

    }

}
