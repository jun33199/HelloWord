package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryWszForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import java.sql.Connection;


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
public class WszAction extends QueryBaseAction {
    /**
     * 进入完税证的打印页面
     * 先生成、再保存、最后打印
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
        ArrayList wszList = new ArrayList();
        ArrayList mxList = new ArrayList();

        QueryWszForm wszForm = (QueryWszForm) form;

        HttpSession session = request.getSession(false);
        ShskForm shskForm = (ShskForm) session.getAttribute("shskForm");

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        QueryWszBo bo = new QueryWszBo();
        bo.setSbbh(shskForm.getSbbh());
        String jsjdm = shskForm.getJsjdm();
        bo.setJsjdm(jsjdm);
        bo.setNsrmc(ActionUtil.getNsrmc(shskForm.getNsrList(),
                                        shskForm.getXsjsjdm(), jsjdm));

        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CREATE_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        Qswszz wszZb = new Qswszz();
        Qswszmx wszMx = new Qswszmx();

        try {
            //生成完税证并返回结果
            wszList = (ArrayList) QsglProxy.getInstance().process(vo);
            //将返回的完税证结果集付给QueryWszForm以便完税证分票打印的时候使用
            wszForm.setWszList(wszList);
            //将生成完税证的数量付给QueryWszForm以便完税证分票打印的时候使用
            wszForm.setWszTotals(wszList.size());
            //代表当前打印的是第一张完税证
            wszForm.setPrintPages(1);

            //先打印第一张完税证
            wszZb = (Qswszz) wszList.get(0);
            wszMx = (Qswszmx) wszZb.getMxList().get(0);
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);

            vo.setAction(ActionType.PRINT_WSZ);
            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
            wszMx = bo.getMxVo();

            /*Debug.out("取得的完税证的金额：" + wszZb.getHjsjje());
             Debug.out("转换中的完税证的金额：" + DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
                             Debug.out("转换后的完税证的金额：" + DataConvert.round(wszZb.getHjsjje(), 2).doubleValue());*/

            ////////////////////////////////
            //在契税完税证和契税缴款书的右上角空白处打印“合同编号”，
            //数据取自申报界面中的“合同编号”。
            //zzb 20090731  begin
            Connection conn  = QSDBUtil.getConnection();//建立数据库连接，为了查找合同编号

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
                htbh=" ";
            }else{
                System.out.println("================htbh============="+ htbh);
            }
            QSDBUtil.freeConnection(conn);//关闭数据库连接
            //向打印缴款书页面传送合同编号
            wszForm.setHtbh(htbh);
            //zzb 20090731  end
            /////////////////////////////////


            wszForm.setWszh(wszZb.getWszh());
            wszForm.setZb(wszZb.getNdzb());
            wszForm.setPzzldm(wszZb.getPzzldm());

            wszForm.setTfrq(DataConvert.TS2String(wszZb.getCjrq()));
            wszForm.setNsrmc(wszZb.getNsrmc());
            wszForm.setHtqdrq(DataConvert.TimeStamp2String(wszZb.getHtclrq()));
            wszForm.setFdcwz(wszZb.getFdcwz());
            wszForm.setDz(" ");
            wszForm.setJbr(wszZb.getCjr());
            wszForm.setNsrdm(wszZb.getNsrdm());

            mxList = wszZb.getMxList();

            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Qswszmx mx = (Qswszmx) iter.next();
                /** @todo 如果以后改为多税种税目的情况，需要放入到数组中 */
                wszForm.setSzsmmc(mx.getSzsmmc());
                wszForm.setJsje(DataConvert.BigDecimal2String(mx.getJsje(), 2));
                wszForm.setSl(DataConvert.BigDecimal2String(mx.getSl()));
                wszForm.setSjse(DataConvert.BigDecimal2String(mx.getSjse(), 2));
                wszForm.setQszymj(DataConvert.BigDecimal2String(mx.getQszymj()));
                wszForm.setSkssksrq(DataConvert.TimeStamp2String(mx.getSkssksrq()));
                wszForm.setSkssjzrq(DataConvert.TimeStamp2String(mx.getSkssjsrq()));

            }
            StringBuffer sb = new StringBuffer("减免税金额:￥");
            sb.append(DataConvert.BigDecimal2String(wszZb.getJsje(), 2))
                    .append("  缴税方式:").append(wszZb.getJsfsmc());
            //如果完税证有分票的情况，要在备注中把分票的完税证号都打印出来
            /*if(wszList.size() > 1)
                             {
                sb.append(" 完税证号：");
                for(int i = 0; i < wszList.size(); i++ )
                {
                    sb.append(((Qswszz)wszList.get(i)).getWszh()).append(", ");
                }
                wszForm.setBz(sb.substring(0, (sb.length() - 2)) );
                             }
                             else    //如果没有分票的情况，在备注中不需要打印完税证号
                             {
                wszForm.setBz(sb.toString() );
                             }*/
            wszForm.setBz(sb.toString());
            wszForm.setYqts(DataConvert.BigDecimal2String(wszZb.getYqts(), 0));
            wszForm.setZnj("￥" +
                           DataConvert.BigDecimal2String(wszZb.getZnjzje(), 2));
            Debug.out("转换前的合计实缴金额： " + wszZb.getHjsjje());
            wszForm.setJehj(DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
            Debug.out("转换后的 " +
                      wszZb.getHjsjje().setScale(2, BigDecimal.ROUND_HALF_UP));
            wszForm.setJehj_dx(Currency.convert(wszZb.getHjsjje().setScale(2,
                    BigDecimal.ROUND_HALF_UP)));
            wszForm.setZsjg(wszZb.getSwjgzzjgmc());
            request.setAttribute(Constants.MESSAGE_KEY, "已经生成收现缴款书！");

            //清掉相应的页面数据
            QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            aForm.removeData(aForm.getViewIndex());
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        }

        //将生成的数据放回前台
        //request.setAttribute(mapping.getAttribute(),wszForm);
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    /**
     * 换号打印
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doChange(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            Exception {
        QueryWszForm wszForm = (QueryWszForm) form;

        ArrayList wszList = wszForm.getWszList();
        Qswszz wszZb = (Qswszz) wszList.get(wszForm.getPrintPages() - 1);
        wszZb.setWszh(wszForm.getWszh());
        wszZb.setNdzb(wszForm.getZb());

        HttpSession session = request.getSession(false);
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        QueryWszBo bo = new QueryWszBo();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CHANGE_WSZH_PRINT);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            bo.setQswszzVo(wszZb);
            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        wszForm.setZb(wszZb.getNdzb()); //年度字别
        wszForm.setWszh(wszZb.getWszh()); //完税证号
        wszForm.setPzzldm(wszZb.getPzzldm()); //pzzldm
        request.setAttribute(Constants.MESSAGE_KEY, "收现缴款书已经换号成功！");

        return mapping.findForward("show");
    }

    /**
     * 从页面通过完税证号获得完税证的信息
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
        QueryWszForm queryWszForm = (QueryWszForm) form;
        QueryWszBo bo = (QueryWszBo) queryWszForm.getData();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.GET);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            /** @todo 将后台返回的数据显示到前台页面 */
            Qswszz wszZb = (Qswszz) QsglProxy.getInstance().process(vo);

            if (wszZb != null && wszZb.getMxList() != null &&
                wszZb.getMxList().size() > 0) {
                ArrayList aList = new ArrayList();
                bo.setQswszzVo(wszZb);
                bo.setResultList(wszZb.getMxList());
                aList.add(bo);
                QueryCache cache = new QueryCache(aList,
                                                  this.getUserData().myxszds);
                queryWszForm.setQueryCache(cache);
                //将页面显示状态设定为显示查询结果
                queryWszForm.setStatus("Result");
                request.setAttribute(Constants.MESSAGE_KEY, "已经从库中获得了所要的收现缴款书！");
            } else {
                QueryCache cache = queryWszForm.getQueryCache();
                cache.removeAll();
                queryWszForm.setStatus("Query");
                request.setAttribute(Constants.MESSAGE_KEY, "没有符合查询条件的收现缴款书！");
            }
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 撤销完税证
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCxWsz(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryWszForm queryWszForm = (QueryWszForm) form;
        QueryWszBo bo = (QueryWszBo) queryWszForm.getData();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CX_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            QueryCache cache = queryWszForm.getQueryCache();
            cache.removeAll();
            queryWszForm.setStatus("Query");
            request.setAttribute(Constants.MESSAGE_KEY, "已经撤销了该收现缴款书！");
        } catch (Exception ex) {
            Debug.printException(ex);
            QueryCache cache = queryWszForm.getQueryCache();
            cache.removeAll();
            queryWszForm.setStatus("Query");
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 打印完税证后更改完税证主表的处理标记状态
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doUpdateState(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Qswszz wszVo = new Qswszz();
        QueryWszForm wszForm = (QueryWszForm) form;

        wszVo.setWszh(wszForm.getWszh());
        wszVo.setNdzb(wszForm.getZb());
        wszVo.setPzzldm(wszForm.getPzzldm());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        VOPackage vo = new VOPackage();
        vo.setData(wszVo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CHANGE_WSZ_STATUS);
        vo.setProcessor(prop.getProperty(wszVo.getClass().getName()));
        wszForm.setZb("");
        session.setAttribute(mapping.getAttribute(), wszForm);

        try {
            QsglProxy.getInstance().process(vo);
        } catch (Exception ex) {
            Debug.printException(ex);
        }
        //如果完税证就一张没有分票的情况或者分票的完税证已全部打印，则退出打印
        if (wszForm.wszTotals == 1 || wszForm.printPages == wszForm.wszTotals) {
            return mapping.findForward("return");
        } else { //如果有分票的情况，要继续打印后面的完税证
            return doContinuePrint(mapping, form, request, response);
        }
    }

    /**
     * 完税证分票的打印
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doContinuePrint(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        QueryWszForm wszForm = (QueryWszForm) form;
        //设置当前打印的是第几张完税证
        wszForm.setPrintPages(wszForm.getPrintPages() + 1);

        ArrayList wszList = wszForm.getWszList();
        Qswszz wszZb = (Qswszz) wszList.get(wszForm.getPrintPages() - 1);
        Qswszmx wszMx = (Qswszmx) wszZb.getMxList().get(0);

        HttpSession session = request.getSession(false);
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        QueryWszBo bo = new QueryWszBo();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.PRINT_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);

            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
            wszMx = bo.getMxVo();
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        }

        wszForm.setWszh(wszZb.getWszh());
        wszForm.setZb(wszZb.getNdzb());
        wszForm.setPzzldm(wszZb.getPzzldm());

        ArrayList mxList = wszZb.getMxList();

        for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
            Qswszmx mx = (Qswszmx) iter.next();
            /** @todo 如果以后改为多税种税目的情况，需要放入到数组中 */
            //分票的完税证计税金额、税率、权属转移面积都不显示
            wszForm.setSzsmmc(mx.getSzsmmc());
            wszForm.setJsje(" ");
            wszForm.setSl(" ");
            wszForm.setSjse("￥" + DataConvert.BigDecimal2String(mx.getSjse(), 2));
            wszForm.setQszymj(" ");
            wszForm.setSkssksrq(DataConvert.TimeStamp2String(mx.getSkssksrq()));
            wszForm.setSkssjzrq(DataConvert.TimeStamp2String(mx.getSkssjsrq()));

        }
        StringBuffer sb = new StringBuffer(" 缴税方式：");
        sb.append(wszZb.getJsfsmc());
        //如果完税证有分票的情况，要在备注中把分票的完税证号都打印出来
        /*sb.append(" 完税证号：");
                     for (int i = 0; i < wszList.size(); i++)
                     {
            sb.append(((Qswszz) wszList.get(i)).getWszh()).append(", ");
                     }*/
        wszForm.setBz(sb.toString());
        wszForm.setJbr(wszZb.getCjr());

        wszForm.setYqts(" ");
        wszForm.setZnj(" ");
        Debug.out("转换前的合计实缴金额： " + wszZb.getHjsjje());
        wszForm.setJehj(DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
        Debug.out("转换后的 " +
                  wszZb.getHjsjje().setScale(2, BigDecimal.ROUND_HALF_UP));
        wszForm.setJehj_dx(Currency.convert(wszZb.getHjsjje().setScale(2,
                BigDecimal.ROUND_HALF_UP)));

        request.setAttribute(Constants.MESSAGE_KEY, "已经生成收现缴款书！");

        return mapping.findForward("show");
    }


    /**
     * 查询完税证－－如果查询结果多于200条，则只显示前200条，因此要覆盖父类的doQuery方法
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
        try {
            // 将参数中的Form构型为QueryWszForm。
            QueryWszForm queryForm = (QueryWszForm) form;
            // 从QueryWszForm中获取查询条件。
            QueryWszBo obj = (QueryWszBo) queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession(false);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY);
            vo.setUserData(this.getUserData());
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);

            //如果查询结果有201条，则只取前200条（查询结果是按时间排序的，最近的日期在前面）
            if (list.size() == 201) {
                list.remove(200);
                String msg =
                        "查询结果太多，为了保证速度，只显示前200条数据，请您输入更多的查询条件以便得到精确的查询结果!!!";
                request.setAttribute(Constants.MESSAGE_KEY, msg);
            }
            // 将结果构造成通用的QueryCache对象，以便翻页使用。

            QueryCache cache = new QueryCache(list, this.getUserData().myxszds);
            queryForm.setQueryCache(cache);
            //将页面显示状态设定为显示查询结果
            queryForm.setStatus("Result");

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward("query");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("query");
        }

    }

}
