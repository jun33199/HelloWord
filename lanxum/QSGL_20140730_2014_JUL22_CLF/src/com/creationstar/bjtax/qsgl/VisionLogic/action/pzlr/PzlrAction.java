package com.creationstar.bjtax.qsgl.VisionLogic.action.pzlr;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Pzlr;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PzlrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PzlrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DESUtil;
import com.creationstar.bjtax.qsgl.util.MakeXMLUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 批量软件配置录入action
 */
public class PzlrAction extends BaseAction {
    /**
     *页面初始化
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
        HttpSession session = request.getSession(false);
        PzlrForm pzlrForm = (PzlrForm) form;
        UserData ud = this.getUserData();
        //初始化操作人员
        pzlrForm.setCzry(ud.getYhid());
        //初始化所属税务机关名称
        pzlrForm.setSwjgzzjgmc(ud.getSsdwmc());
        //初始化所属税务机关代码
        pzlrForm.setSwjgzzjgdm(ud.getSsdwdm());
        //初始化页面单选框的值，为否
        pzlrForm.setCzcq("1");
        pzlrForm.setCzcsxx("1");
        pzlrForm.setCzfwjyxx("1");
        //设置页面删除与生成按钮的状态为不显示
        pzlrForm.setDel_flag("false");
        //设置所在区域下拉集合
        pzlrForm.setTdjcList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.SZQY));
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 根据计算机代码查询基本信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetNsrxx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        PzlrForm aForm = (PzlrForm) form;
        // 保存Token;
        saveToken(request);
        try {
            String jsjdm = aForm.getJsjdm();

            PzlrBo bo = new PzlrBo();
            bo.setJsjdm(jsjdm);
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());

            bo = (PzlrBo) QsglProxy.getInstance().process(vo);
            //设置纳税人名称
            aForm.setNsrmc(bo.getNsrmc());
            //设置纳税人税务机关组织机构
            aForm.setZzjgmc(bo.getSsdwmc());
            request.setAttribute(Constants.MESSAGE_KEY, "取得纳税人信息成功！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "取得纳税人信息失败！");

        }

        return mapping.findForward("show");

    }

    /**
     * 根据房地产项目名称查询
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQueryFwxx(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        // 保存Token;
        saveToken(request);
        try {

            String fdcxmmc = aForm.getFdcxmmc();
            String jsjdm = aForm.getJsjdm();
            PzlrBo bo = new PzlrBo();

            bo.setJsjdm(jsjdm);
            bo.setFdcxmmc(fdcxmmc);
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            List list = new ArrayList();
            //查询结果以vo为对象放入list中
            list = (List) QsglProxy.getInstance().process(vo);
            //判断是否有房屋信息，有则显示，并且出现删除按钮，没有则不现实删除按钮；
            if (list.size() > 0) {
                setForm(list, aForm);

                request.setAttribute(Constants.MESSAGE_KEY, "该项目信息已经存在！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "未有该项目信息！");
            }

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "获取项目信息失败！");

        }

        return mapping.findForward("show");

    }

    /**
     * 插入数据方法
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
        System.out.println("save action");
        PzlrForm aForm = (PzlrForm) form;
        //调用保存数据方法
        saveDate(aForm, request);
        return mapping.findForward("show");

    }

    /**
     * 删除调用方法
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);

        try {
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.DELETE);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "删除项目信息成功！");
            clearForm(aForm);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "删除项目信息失败！");

        }
        return mapping.findForward("show");
    }

    /**
     * 设置form属性
     * @param list List
     * @param form PzlrForm
     */
    private void setForm(List list, PzlrForm form) {
        Pzlr vo = (Pzlr) list.get(0);
        System.out.println("id===" + vo.getId());
        form.setId(vo.getId());
        form.setCzcq(vo.getCzcqxx());
        form.setCzcsxx(vo.getCzcsxx());
        form.setCzfwjyxx(vo.getCzfwjyxx());
        form.setRjl(vo.getRjl());
        form.setQsrq(vo.getQsrq());
        form.setJzrq(vo.getJsrq());
        form.setJzmj(vo.getJzmj());
        form.setPjjyjg(vo.getPjjyjg());
        /*added by gaoyh to 20141016*/
        //房屋每套价格
        form.setFwmtjg(vo.getFwmtjg());
        form.setTdjb(vo.getTdjb());
        form.setDel_flag("true");
    }

    /**
     * 清楚form属性
     * @param list List
     * @param form PzlrForm
     */
    private void clearForm(PzlrForm form) {
        form.setFdcxmmc("");
        form.setJzmj("");
        form.setRjl("");
        form.setQsrq("");
        form.setJzrq("");
        form.setPjjyjg("");
        /*added by gaoyh to 20141016*/
        //房屋每套价格
        form.setFwmtjg("");
        form.setCzcq("0");
        form.setCzcsxx("0");
        form.setCzfwjyxx("0");
        //清空纳税人信息
        form.setJsjdm("");
        form.setNsrmc("");
        form.setZzjgmc("");
        form.setDel_flag("false");
    }

    /**
     * 设置PzlrBo属性
     * @param list List
     * @param form PzlrForm
     */
    private PzlrBo setPzlrBo(PzlrBo bo, PzlrForm form) {

        //主键id
        bo.setId(form.getId());
        //计算机代码
        bo.setJsjdm(form.getJsjdm());
        //纳税人名称
        bo.setNsrmc(form.getNsrmc());
        //纳税人税务机关组织机构
        bo.setSsdwmc(form.getZzjgmc());
        //房地产项目名称
        bo.setFdcxmmc(form.getFdcxmmc());
        //土地级别
        bo.setTdjb(form.getTdjb());
        //容积率
        bo.setRjl(form.getRjl());
        //建筑面积
        bo.setJzmj(form.getJzmj());
        //平均交易价格
        bo.setPjjyjg(form.getPjjyjg());
        /*added by gaoyh to 20141016*/
        //房屋每套价格
        bo.setFwmtjg(form.getFwmtjg());
        //使用期限起
        bo.setQsrq(form.getQsrq());
        //使用期限止
        bo.setJzrq(form.getJzrq());

        //是否可操作拆迁信息
        bo.setCzcq(form.getCzcq());
        //是否可操作已购公有住房信息
        bo.setCzcsxx(form.getCzcsxx());
        //是否可操作房屋交易信息
        bo.setCzfwjyxx(form.getCzfwjyxx());
        //操作人员
        bo.setCzry(form.getCzry());
        //操作时间
        bo.setXtdqsj(form.getXtdqsj());

        return bo;
    }

//    /**
//     * 根据id查询房地产项目名称
//     * @param id String
//     * @return String
//     */
//    private String getFdcxmmc(String id ,HttpServletRequest request){
//    PzlrBo bo = new PzlrBo();
//    bo.setId(id);
//    String fdcxmmc="";
//    try{
//              //获取存放在ServletContext中的processor-map.properties的数据
//              Properties prop = (Properties) request.getSession(false).
//                                getServletContext().getAttribute(Constants.
//                      PROCESSOR_MAP_FILE_NAME);
//
//              //构造向后台传输用的VoPackage
//              VOPackage vo = new VOPackage();
//              vo.setAction(ActionType.QUERY_DRZB);
//              vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//              vo.setData(bo);
//              vo.setUserData(this.getUserData());
//              fdcxmmc=(String)QsglProxy.getInstance().process(vo);
//
//
//          }catch(Exception ex){
//                  // 保存Token;
//                       saveToken(request);
//                       ex.printStackTrace();
//
//
//          }
//          return fdcxmmc;
//
//}
    /**
     * 生成xml文件
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doMakeXML(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        //生成配置文件之前先进行保存操作，modify by fujx，20081217
        saveDate(aForm, request);

        // String xmlName=aForm.getJsjdm()+"pzxx";
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);
        List list = bo.getItemList();
        //拼接文件名称字符串
        String fileName = aForm.getJsjdm() + "_" + aForm.getFdcxmmc() +
                          "_pzxx.dat";

//         for(int i=0;i<2;i++){
//             PzxxXMLItem item = (PzxxXMLItem)list.get(i);
//             System.out.println("name==="+item.getItemName());
//             System.out.println("value==="+item.getItemValue());
//         }
        try {
            //设置文件名称为'iso-8859-1'编码格式
            response.setHeader("Content-Disposition",
                               "attachment;filename=" +
                               new String(fileName.getBytes("GBK"),
                                          "ISO-8859-1"));
            response.setContentType("text/html;charset=gb2312");
            //response.setchar
            //response.setCharacterEncoding("gbk");
            OutputStream servletOutput = response.getOutputStream();
            //FileOutputStream servletOutput = new FileOutputStream(xmlName);
            PrintWriter printWrite = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(new BufferedOutputStream(
                            servletOutput))));
            //获取xml字符串
            String buff = MakeXMLUtil.createXML("pzxx", list);
            System.out.println("配置字符串=" + buff);
            //把字符串加密
            DESUtil des = new DESUtil();
            buff = des.encrypt(buff);
            //输出
            printWrite.println(buff);
            printWrite.flush();
            printWrite.close();
            servletOutput.flush();
            servletOutput.close();
            request.setAttribute(Constants.MESSAGE_KEY, "生成xml文件成功！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "生成xml文件错误！");

        }
        return null;
    }

//    public static void main(){
//
//    }

    /**
     * 保存数据的公有方法，方便生成配置文件之前进行进行保存的调用
     * @param aForm PzlrForm
     * @param request HttpServletRequest
     */
    private void saveDate(PzlrForm aForm, HttpServletRequest request) {
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);
        try {
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            String result = (String) QsglProxy.getInstance().process(vo);
            aForm.setDel_flag("true");
            //设置页面的id属性
            aForm.setId(result);
            if (!result.equals("false")) {
                request.setAttribute(Constants.MESSAGE_KEY, "保存项目信息成功！");
            }
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "保存项目信息失败！");

        }

    }


}
