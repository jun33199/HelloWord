package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DESUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ResourceLocator;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.creationstar.bjtax.qsgl.util.Constants;
/**
 *
 * <p>Title: 导入文件的action类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 魏永清
 * @version 1.0
 */
public class ImportFileAction extends BaseAction {
    public ActionForward doUpload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm aForm = (QueryPlslForm) form;
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        int liDrMaxBs = 200; //导入最大笔数
        HashMap loData = new HashMap();
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setUserData(ud);
        FormFile loFf = aForm.getMofile();
        vo.setAction(ActionType.UPLOAD_FILE);
        vo.setProcessor(prop.getProperty("uploadFileProcessor"));
        Connection conn = null;
        boolean flag = false;
        try {
            aForm.setJkfsmc(StringUtil.ISO2GBK(aForm.getJkfsmc()));
            String lsPch;
            int aiLen = loFf.getFileSize();
            InputStream in = loFf.getInputStream();
            byte[] lbBB = new byte[aiLen];
            in.read(lbBB);
            in.close();

            String lsS = new String(lbBB, "GBK");
            //解密文件
            DESUtil des = new DESUtil();
            lsS = des.decrypt(lsS.toString());
           System.out.println("解密文件:"+lsS);
            
            //版本信息
            int versionLocation=0;
            versionLocation=lsS.indexOf("<data version=\"");
            String version="";
            if(versionLocation>=0){
            	version=lsS.substring(versionLocation+"<data version=\"".length(), lsS.indexOf("\">", versionLocation+"<data version=\"".length()));
            }
            //比较版本
            if(version.equals(Constants.XML_VERSION)){
            	
            
            QsglPcclXmlUtil util = new QsglPcclXmlUtil();
            ArrayList testList = util.getPcclMx(lsS);
            //判断配置信息是否合法
            Map pzmap = (HashMap) util.getPcclPzxx(lsS);
            try {
                conn = QSDBUtil.getConnection();
                String jsjdm = (String) pzmap.get("jsjdm");
                System.out.println("=====批量导入中的===jsjdm========>>>>>>" + jsjdm);
                
                //由于判断配置信息取值前后不一致，故此修改 moidfied by gaoyh to 20141017
                /****************************begin************************************/
//                String nsrmc = (String) pzmap.get("nsrmc");
//                System.out.println("=====批量导入中的===nsrmc========>>>>>>" + nsrmc);
//                flag = DAOFactory.getInstance().getDrzbDAO().queryPzxx(jsjdm,
//                        nsrmc, conn);
                
                
                String fdcxmmc = (String) pzmap.get("fdcxmmc");
                System.out.println("=====批量导入中的===fdcxmmc========>>>>>>" + fdcxmmc);
                
                flag = DAOFactory.getInstance().getDrzbDAO().queryPzxx(jsjdm,
                		fdcxmmc, conn);
                System.out.println("=====批量导入中的===flag========>>>>>>" + flag);
                /****************************end************************************/
            } catch (Exception ex) {
                throw ex;
            } finally {
                QSDBUtil.freeConnection(conn);
            }

            //判断是否超过200条的最大限制
            if (testList.size() > liDrMaxBs + 1) {
                throw new Exception("导入的笔数不能大于200条！");
            }
            //若为追加
            if (aForm.getMsBLDrpch().length() > 0) {

                lsPch = aForm.getMsBLDrpch();
                loData.put("pch", lsPch);
                loData.put("append", testList);
                //在session中放置导入批次号
                request.setAttribute("drpch", aForm.getMsBLDrpch());
            } else {

                //设置导入批次号
                lsPch = aForm.getMsDrpch();
                loData.put("pch", lsPch);
                loData.put("jsfsdm", aForm.getJkfsdm());
                loData.put("jsfsmc", aForm.getJkfsmc());
                loData.put("new", testList);
                //在session中放置导入批次号
                request.setAttribute("drpch", aForm.getMsDrpch());
            }

            vo.setData(loData);
            Map lmReturnedData = (HashMap) QsglProxy.getInstance().
                                 process(vo);
            ArrayList llErrrecords = (ArrayList) lmReturnedData.get("Error");
            ArrayList llSucceedRecords = (ArrayList) lmReturnedData.get(
                    "Succeed");

            //request.getSession().setAttribute("drpch",request.getAttribute("drpch"));
            aForm.setDrsj(llSucceedRecords);
            aForm.setSize(llSucceedRecords.size());
            if (!flag) {
                //System.out.println("111111111111111111111111111");
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "文件配置信息有误！");
                aForm.setMbIsSucceed(true);
                aForm.setSize(0);
                aForm.setDrbs("1");
                request.getSession().setAttribute("llErrrecords", llErrrecords);
                request.getSession().setAttribute("llSucceedRecords",
                                                  llSucceedRecords);
                //request.getSession().setAttribute("FormFile",loFf);
            } else if (llErrrecords.size() > 0) {
                //System.out.println("222222222222222222222222");
                aForm.setDrbs("0");
                aForm.setMbIsSucceed(false);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "文件格式错误");
                aForm.setMlErrRecords(llErrrecords);

            } else {
                //System.out.println("33333333333333333333333333");
                aForm.setDrbs("0");
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "文件上传成功");
                //return mapping.findForward("continue");
                //modify by CA program
                response.sendRedirect(ResourceLocator.getServerURL(ud.getCaflag()) +
                                      "/qsgl/plsl/plsl.do?operationType=Show");
                return null;
            }
            }else{
            	 aForm.setDrbs("0");
                 aForm.setMbIsSucceed(false);
                 request.setAttribute(Constants.MESSAGE_KEY,
                                      "文件版本号错误");
                 aForm.setMlErrRecords(new ArrayList());
            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        return mapping.findForward("show");

    }


    public ActionForward doReUpload(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm aForm = (QueryPlslForm) form;
        UserData ud = this.getUserData();
        try {
            ArrayList llErrrecords = (ArrayList) request.getSession().
                                     getAttribute("llErrrecords");
            ArrayList llSucceedRecords = (ArrayList) request.getSession().
                                         getAttribute("llSucceedRecords");
            //request.setAttribute("drpch", (String)request.getSession().getAttribute("drpch"));
            aForm.setDrsj(llSucceedRecords);
            aForm.setSize(llSucceedRecords.size());

            if (llErrrecords.size() > 0) {
                //System.out.println("44444444444444444");
                aForm.setMlErrRecords(llErrrecords);
                aForm.setMbIsSucceed(false);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "文件格式错误");
                aForm.setDrbs("0");
            } else {
                //System.out.println("55555555555555555555");
                aForm.setDrbs("0");
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "文件上传成功");
                //return mapping.findForward("continue");
                //modify by CA program
                response.sendRedirect(ResourceLocator.getServerURL(ud.getCaflag()) +
                                      "/qsgl/plsl/plsl.do?operationType=Show");
                return null;
            }

        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        return mapping.findForward("show");

    }


    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm aForm = (QueryPlslForm) form;
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        aForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        aForm.setMofile(null);
        //构造后台需要的条件数据对象
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setUserData(ud);
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty("uploadFileProcessor"));
        try {
//            String[] lsBLDrpchs = (String[]) QsglProxy.getInstance().process(vo);
            vo.setAction(ActionType.GET);
            String plh = (String) QsglProxy.getInstance().process(vo);
//            aForm.setMsBLDrpchs(lsBLDrpchs);
            aForm.setMsDrpch(plh);

        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        return mapping.findForward("show");

    }

    public ActionForward doContinue(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        Debug.out("ImportFileAction continue");
        HttpSession session = request.getSession(false);
        QueryPlslForm aForm = (QueryPlslForm) form;
        Debug.out("continue");
        return mapping.findForward("continue");

    }
}
