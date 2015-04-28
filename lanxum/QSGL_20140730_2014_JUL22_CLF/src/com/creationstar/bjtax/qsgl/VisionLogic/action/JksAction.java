package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JksForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryWszForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;


/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 赵博
 * @version 1.0
 */
public class JksAction extends BaseAction {
    /**
     * 直接通过申报信息受理（审核及收款）而生成的缴款书
     * 生成和打印的功能
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doIntoPrint(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        JksForm jksForm = new JksForm();
        jksForm = (JksForm) form;

        HttpSession session = request.getSession(false);
        ShskForm shskForm = (ShskForm) session.getAttribute("shskForm");

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        JksBo bo = new JksBo();
        bo.setSbbh(shskForm.getSbbh());
        String jsjdm = shskForm.getJsjdm();
        bo.setJsjdm(jsjdm);
        bo.setNsrmc(ActionUtil.getNsrmc(shskForm.getNsrList(),
                                        shskForm.getXsjsjdm(), jsjdm));
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CREATE_JKS);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection(); //为了取得国库交换号
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(this.getUserData(), conn);

            Sbjkzb sbjkzb = (Sbjkzb) QsglProxy.getInstance().process(vo);

            ArrayList mxList = sbjkzb.getMxList();

            ////////////////////////////////
            //在契税完税证和契税缴款书的右上角空白处打印“合同编号”，
            //数据取自申报界面中的“合同编号”。
            //zzb 20090731 add begin
            String sbbh = shskForm.getSbbh();
            System.out.println("============sbbh================="+ sbbh);
            //创建一个Sbzb实例sbzb1,将该实例的申报表号设置为当前申报表号,然后将该sbzb1作为
            //参数传递给SbzbDAO.java的get()方法来查找对应与该申报表号记录的合同编号.
            Sbzb sbzb1 = new Sbzb();
            sbzb1.setSbbh(sbbh);
            Sbzb sbzb2 = (Sbzb)DAOFactory.getInstance().getSbzbDAO().get(sbzb1, conn);
            String htbh = sbzb2.getHtbh();
            if(htbh==null){
                System.out.println("================htbh=============null");
                htbh="";
            }else{
                System.out.println("================htbh============="+ htbh);
            }
            //向打印缴款书页面传送合同编号
            jksForm.setHtbh(htbh);
            //zzb 20090731 add end
            /////////////////////////////////


            jksForm.setSkgk(sbjkzb.getGkzzjgmc());
            jksForm.setSklx(sbjkzb.getSklxmc());
            jksForm.setSksskssq(DataConvert.TimeStamp2String(sbjkzb.getSkssksrq()));
            jksForm.setSkssjssq(DataConvert.TimeStamp2String(sbjkzb.getSkssjsrq()));
            jksForm.setSkxjrq(DataConvert.TimeStamp2String(sbjkzb.getXjrq()));
            jksForm.setSwjg(sbjkzb.getSwjgzzjgmc());
            jksForm.setSzdm(sbjkzb.getSzmc());
            jksForm.setTfrq(DataConvert.TimeStamp2String(sbjkzb.getCjrq()));
            jksForm.setTfrqn(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(0, 4));
            jksForm.setTfrqy(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(4, 6));
            jksForm.setTfrqr(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(6, 8));
            jksForm.setYskmbm(sbjkzb.getYskmdm());
            jksForm.setYskmjc(sbjkzb.getYsjcmc());
            jksForm.setYskmmc(sbjkzb.getYskmmc());
            jksForm.setZclx(sbjkzb.getDjzclxmc());
            jksForm.setJkdw(sbjkzb.getNsrmc()); //纳税人计算机代码
            jksForm.setJkdwdh(sbjkzb.getJydzlxdm());
            jksForm.setJkdwkhyh(sbjkzb.getYhmc());
            jksForm.setJkdwqc(sbjkzb.getNsrmc());
            jksForm.setJkdwdm(sbjkzb.getJsjdm());
            jksForm.setJkdwzh(sbjkzb.getZh());
            jksForm.setJkjehj(Currency.convert(sbjkzb.getSjje().setScale(2,
                    BigDecimal.ROUND_HALF_UP)));
            jksForm.setJkjehj_nu("￥" +
                                 DataConvert.BigDecimal2String(sbjkzb.getSjje(),
                    2));
            jksForm.setJkpzh(sbjkzb.getJkpzh());
            jksForm.setLsgx(sbjkzb.getLsgxmc());
            jksForm.setZsjg(sbjkzb.getZsswjgzzjgmc());
            jksForm.setNsrmc(sbjkzb.getNsrmc());
            jksForm.setJhh(swjgzzjg.getGkjhh());
            jksForm.setFdcwz(sbjkzb.getFdcwz());
            jksForm.setBz(sbjkzb.getBz());
            Debug.out("征收机关名称:" + sbjkzb.getZsswjgzzjgmc());
            jksForm.setZsjg(sbjkzb.getZsswjgzzjgmc());
            Debug.out("征收机关名称:" + jksForm.getZsjg());
            jksForm.setMxList(mxList);

            String xmmc = "";
            String kssl = "";
            String jsje = "￥";
            String sjse = "￥";
            String fcbl = "";

            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Sbjkmx sbjkmx = (Sbjkmx) iter.next();
                fcbl = fcbl + sbjkmx.getYskmfcbl() +";;";
                xmmc = xmmc + sbjkmx.getSzsmmc() + ";;";
                kssl = kssl + DataConvert.BigDecimal2String(sbjkmx.getKssl(), 0) +
                       ";;";
                jsje = jsje + DataConvert.BigDecimal2String(sbjkmx.getJsje(), 2) +
                       ";;￥";
                sjse = sjse + DataConvert.BigDecimal2String(sbjkmx.getSjse(), 2) +
                       ";;￥";
            }

            //去掉最后的;;
            jksForm.setJkmxxmmc(xmmc.substring(0, (xmmc.length() - 2)));
            jksForm.setJkmxkssl(kssl.substring(0, (kssl.length() - 2)));
            jksForm.setJkmxjsje(jsje.substring(0, (jsje.length() - 3)));
            jksForm.setJkmxsjse(sjse.substring(0, (sjse.length() - 3)));
            jksForm.setJkmxfcbl(fcbl.substring(0, (fcbl.length() - 2)));

            jksForm.setMxList(mxList);

            //清除页面处理过得申报数据
            QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            aForm.removeData(aForm.getViewIndex());
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("error");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "已经生成缴款书！");
        return mapping.findForward("show");
    }


    /**
     * 显示缴款书查询结果的明细
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShowJks(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        JksForm jksForm = (JksForm) form;
        int index = jksForm.getViewIndex();
        QueryCache cache = jksForm.getQueryCache();
        JksBo bo = (JksBo) cache.getDataDetail(index);

        jksForm.setJksBo(bo);

        return mapping.findForward("show");
    }


    /**
     * 汇总完税证后的打印缴款书
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintHzWsz(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        int index = aForm.getViewIndex();
        QueryCache cache = aForm.getQueryCache();

        Sbjkzb sbjkzb = (Sbjkzb) cache.getDataDetail(index);
        String lw = null;

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection(); //为了取得国库交换号
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(this.getUserData(), conn);
            lw = swjgzzjg.getLwzt();

            ArrayList mxList = sbjkzb.getMxList();

            aForm.setSkgk(sbjkzb.getGkzzjgmc());
            aForm.setSklx(sbjkzb.getSklxmc());
            aForm.setSksskssq(DataConvert.TimeStamp2String(sbjkzb.getSkssksrq()));
            aForm.setSkssjssq(DataConvert.TimeStamp2String(sbjkzb.getSkssjsrq()));
            aForm.setSkxjrq(DataConvert.TimeStamp2String(sbjkzb.getXjrq()));
            aForm.setSwjg(sbjkzb.getSwjgzzjgmc());
            aForm.setSzdm(sbjkzb.getSzmc());
            aForm.setTfrq(DataConvert.TimeStamp2String(sbjkzb.getCjrq()));
            aForm.setTfrqn(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(0,4));
            aForm.setTfrqy(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(4,6));
            aForm.setTfrqr(DataConvert.TimeStamp2String(sbjkzb.getCjrq()).substring(6,8));
            aForm.setYskmbm(sbjkzb.getYskmdm());
            aForm.setYskmjc(sbjkzb.getYsjcmc());
            aForm.setYskmmc(sbjkzb.getYskmmc());
            aForm.setZclx(sbjkzb.getDjzclxmc());
            aForm.setJkdw(sbjkzb.getNsrmc()); //纳税人计算机代码
            aForm.setJkdwdh(sbjkzb.getJydzlxdm());
            aForm.setJkdwkhyh(sbjkzb.getYhmc());
            aForm.setJkdwqc(sbjkzb.getNsrmc());
            aForm.setJkdwdm(sbjkzb.getJsjdm());
            aForm.setJkdwzh(sbjkzb.getZh());

            aForm.setJkjehj_nu("￥" +
                               DataConvert.BigDecimal2String(sbjkzb.getSjje(),
                    2, false));
            aForm.setJkjehj(Currency.convert(sbjkzb.getSjje().setScale(2,
                    BigDecimal.ROUND_HALF_UP)));
            aForm.setJkpzh(sbjkzb.getJkpzh());
            aForm.setLsgx(sbjkzb.getLsgxmc());
            aForm.setZsjg(sbjkzb.getZsswjgzzjgmc());
            aForm.setJhh(swjgzzjg.getGkjhh());
            //    System.out.println("Jkjehj_nu==="+aForm.getJkjehj_nu());

            aForm.setBz(sbjkzb.getBz());

            aForm.setDzjktfrq(DataConvert.TS2JksDate(sbjkzb.getCjrq()));
            aForm.setSbbh(sbjkzb.getSbbh());

            aForm.setMxList(mxList);

            String xmmc = "";
            String kssl = "";
            String jsje = "￥";
            String sjse = "￥";
            String fcbl = "";

            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Sbjkmx sbjkmx = (Sbjkmx) iter.next();
                xmmc = xmmc + sbjkmx.getSzsmmc() + ";;";
                kssl = kssl + DataConvert.BigDecimal2String(sbjkmx.getKssl(), 0, false) +
                       ";;";
                jsje = jsje + DataConvert.BigDecimal2String(sbjkmx.getJsje(), 2, false) +
                       ";;￥";
                sjse = sjse + DataConvert.BigDecimal2String(sbjkmx.getSjse(), 2, false) +
                       ";;￥";
                fcbl = fcbl + sbjkmx.getYskmfcbl() + ";;";
            }

            //去掉最后的;;
            aForm.setJkmxxmmc(xmmc.substring(0, (xmmc.length() - 2)));
            aForm.setJkmxkssl(kssl.substring(0, (kssl.length() - 2)));
            aForm.setJkmxjsje(jsje.substring(0, (jsje.length() - 3)));
            aForm.setJkmxsjse(sjse.substring(0, (sjse.length() - 3)));
            aForm.setJkmxfcbl(fcbl.substring(0, (fcbl.length() - 2)));
            
            aForm.setMxList(mxList);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        // 保存Token;
        saveToken(request);
        //   System.out.println("lwzt===="+lw);
        if (lw != null && lw.equals("1")) {
            return mapping.findForward("showLw");
        } else {
            return mapping.findForward("show");
        }
    }


    /**
     * 显示汇总完税证的页面
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShowHz(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        aForm.setJsfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 汇总完税证
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doHzWsz(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        UserData ud = this.getUserData();
        WszBo bo = (WszBo) aForm.getData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(ud);
        vo.setAction(ActionType.HZ_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            ArrayList hzList = (ArrayList) QsglProxy.getInstance().process(vo);
            if (hzList != null && hzList.size() > 0) {
                QueryCache cache = new QueryCache(hzList, ud.myxszds);
                aForm.setQueryCache(cache);
                //将页面显示状态设定为显示查询结果
                aForm.setStatus("Result");
                request.setAttribute(Constants.MESSAGE_KEY, "已经按照要求汇总了完税证！");
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
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        JksBo jksBo = (JksBo) aForm.getJksBo();
        jksBo = aForm.getJksBo();

        VOPackage vo = new VOPackage();
        vo.setData(jksBo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.GET);
        vo.setProcessor(prop.getProperty(jksBo.getClass().getName()));
        try {
            Sbjkzb SbjkZb = (Sbjkzb) QsglProxy.getInstance().process(vo);

            if (SbjkZb != null && SbjkZb.getJkpzh() != null) {
                ArrayList aList = new ArrayList();
                aList.add(SbjkZb);
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
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        WszBo wszBo = new WszBo();
        JksBo jksBo = new JksBo();
        VOPackage vo = new VOPackage();

        String jkpzh = "";

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //如果是汇总完税证方式生成的缴款书，调用wszProcessor的撤销方法
        if (Constants.JKS_SJLY_HZ.equals(aForm.getScfs())) {
            //构造后台需要的条件数据对象
            wszBo = (WszBo) aForm.getData();
            jkpzh = wszBo.getJkpzh();

            vo.setData(wszBo);
            vo.setUserData(this.getUserData());
            vo.setAction(ActionType.CX_HZWSZ);
            vo.setProcessor(prop.getProperty(wszBo.getClass().getName()));
        }
        //如果是直接生成的缴款书，调用jksProcessor的撤销方法
        else if (Constants.JKS_SJLY_FHZ.equals(aForm.getScfs())) {
            //构造后台需要的条件数据对象
            jksBo = aForm.getJksBo();
            jkpzh = jksBo.getJkpzh();

            vo.setData(jksBo);
            vo.setUserData(this.getUserData());
            vo.setAction(ActionType.CX_JKS);
            vo.setProcessor(prop.getProperty(jksBo.getClass().getName()));
        }
        try {
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY,
                                 "成功撤销缴款书号为" + jkpzh + "的缴款书！");
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
        return mapping.findForward("show");
    }

    /**
     * 更新缴款书打印状态
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintStatus(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        WszBo bo = (WszBo) aForm.getData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CHANGE_JKS_STATUS);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
        } catch (Exception ex) {
            Debug.printException(ex);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * 通过页面传来的缴款书的信息，查询该缴款书所包含的完税证的信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQueryWsz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        QueryWszForm queryWszForm = (QueryWszForm) form;
        int index = queryWszForm.getViewIndex();

        String type = request.getParameter("type");

        HttpSession session = request.getSession(false);

        JksForm jksForm = new JksForm();
        hzWsz2JksForm hzWsz2JksForm = new hzWsz2JksForm();

        QueryCache cache = new QueryCache();
        JksBo bo = new JksBo();

        //由于查询缴款书用的是JksForm，汇总和撤销缴款书用的是hzWsz2JksForm，所以要判断一下
        if (type.equals("1")) {
            jksForm = (JksForm) session.getAttribute("jksForm");
            cache = jksForm.getQueryCache();
            bo = (JksBo) cache.getDataDetail(index);
        } else if (type.equals("2")) {
            hzWsz2JksForm = (hzWsz2JksForm) session.getAttribute(
                    "hzWsz2JksForm");
            cache = hzWsz2JksForm.getQueryCache();
            bo.setSbjkzb((Sbjkzb) cache.getDataDetail(index));
        }
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.JKS_QUERY_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            ArrayList resultList = (ArrayList) QsglProxy.getInstance().process(
                    vo);
            QueryCache wszCache = new QueryCache(resultList,
                                                 this.getUserData().myxszds);
            queryWszForm.setQueryCache(wszCache);
            //将页面显示状态设定为显示查询结果
            queryWszForm.setStatus("Result");
        } catch (Exception ex) {
            Debug.printException(ex);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());

            return doReturnPrePage(mapping, form, request, response);
        }
        // 保存Token;
        saveToken(request);

        return mapping.findForward("show");
    }

    /**
     * 汇总完税证生成缴款书进入缴款书打印页面后退出的方法
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doReturnPrepage(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        // 保存Token;
        saveToken(request);

        return mapping.findForward("return");
    }

    /**
     * 查询缴款书、汇总生成缴款书、撤销缴款书页面，进入完税证列表页面后返回原来页面的方法
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doReturnPrePage(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        String returnPage = ((QueryWszForm) form).getYuan();

        // 删除form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);

        if (returnPage != null && !returnPage.equals("")) {
            return mapping.findForward(returnPage);
        } else {
            return mapping.findForward("error");
        }
    }

    public ActionForward doChangePage(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
//将参数中的Form构型为QueryBaseForm。
        QueryBaseForm queryForm = (QueryBaseForm) form;
// 调用Form的changPage接口实现数据的变换。
        queryForm.changePage();
// 保存Token;
        saveToken(request);
        return mapping.findForward("query");
    }

}
