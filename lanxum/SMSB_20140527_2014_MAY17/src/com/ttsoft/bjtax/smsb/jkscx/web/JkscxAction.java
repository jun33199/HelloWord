package com.ttsoft.bjtax.smsb.jkscx.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.util.*;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.jkscx.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.common.util.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JkscxAction extends SmsbAction {

    static final String[] TITLE = {"序号", "计算机代码", "缴款凭证号", "税款类型", "申报方式", "银行",
                                  "登记注册类型", "主管税务机关", "征收机关", "国家标准行业", "预算科目",
                                  "预算级次",
                                  "税种", "实缴金额", "税目", "课税数量", "计税金额", "实缴税额",
                                  "申报日期", "税款所属开始日期", "税款所属结束日期", "纳税人名称",
                                  "入库标识"
    };

    static final String[] COLUMS = {"index", "jsjdm", "jkpzh", "sklx", "sbfs",
                                   "yh",
                                   "djzclx", "zgswjg", "zsswjg", "gjbzhy",
                                   "yskmmc", "ysjcmc",
                                   "szmc", "zbsjje", "szsmmc", "kssl", "jsje",
                                   "sjje",
                                   "sbrq", "skssksrq", "skssjsrq", "nsrmc",
                                   "rkbs"
    };

    /**
     * doShow
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        UserData userData = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            userData = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.reset(mapping, request);
            request.getSession().removeAttribute("initlist");
// 主管税务机关
            Map zgswjg = CodeTableUtil.getZgswjg(userData);
            // 主管税务所
            //用户级别
            pf.setYhjb(userData.getYhjb());
            //主管税务所
            Map zgsws = CodeTableUtil.getZgsws(userData);

            pf.setZgswjg(zgswjg);
            pf.setZgsws(zgsws);
            pf.setSwjgdm(userData.getSsdwdm().substring(0, 2).concat("00"));
            if (userData.getYhjb().equals(CodeConstants.JBDM_FJ) ||
                userData.getYhjb().equals(CodeConstants.JBDM_SJ)) {
                pf.setSwsdm("");
            } else {
                pf.setSwsdm(userData.getSsdwdm());
            }
            //征收税务机关
            String filterDm = null;
            Collection zsswjg = CodeTableUtil.getZgsws(filterDm).values();
            List l = new ArrayList();
            l.addAll(zsswjg);

            request.getSession().setAttribute("zsswjg", l);
            pf.setSzdm("02");
//SZDM_ALL:select SZSMDM , SZSMDM||SZSMMC from DMDB.SB_DM_SZSM where length(szsmdm)=2 and zxbs='0' order by SZSMDM
            //税种税目代码
            SfHashList szList = CodeManager.getCodeList("SZDM_ALL",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_MAPLIST);

//SMDM_ALL:select SZSMDM , SZSMDM||SZSMMC  from dmdb.SB_DM_SZSM where length(szsmdm)=6  and zxbs='0'  order by SZSMDM
            SfHashList smList = CodeManager.getCodeList("SMDM_ALL",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_MAPLIST);
            pf.setSzList(szList);
            pf.setSmList(smList);
            //登记注册类型
            List djzclx = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
            request.getSession().setAttribute("djzclx", djzclx);
            //国家标准行业
            List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
            request.getSession().setAttribute("gjbzhy", gjbzhy);

            List yh = CodeTableUtil.getCodeTableList(CodeTableKey.YH);
            request.getSession().setAttribute("yh", yh);

            List sklxList = CodeManager.getCodeList("ZHSB_SKLX",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("sklx", sklxList);
            List yskmList = CodeManager.getCodeList("DM_YSKM",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("yskm", yskmList);
            List ysjcList = CodeManager.getCodeList("DM_YSJC",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("ysjc", ysjcList);
            List sbfsList = CodeManager.getCodeList("DM_SBFS",
                    com.ttsoft.bjtax.sfgl.common.constant.CodeConstants.
                    CODE_MAP_BEANLIST).getRecords();
            request.getSession().setAttribute("sbfs", sbfsList);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Show");

    }

    /**
     * doQuery
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {

        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            ud = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.setCurPage("0");
            pf.setNextPage("1");
            pf.setTotalpage("0");
            pf.setPageSize(CodeConstant.JKS_PG_LENGTH + "");
            //PageRoll pr = new PageRoll();
            // pr.setPageSize(CodeConstant.JKS_PG_LENGTH);
            // pr.setNewQuery(true);

            pf.setMessage("");
            pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "查询输入申报日期起止时间段不合法或计算机代码为空或缴款凭证号为空，请修改后重新查询.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            Map result = (Map) ZhsbProxy.getInstance().process(vo);
            List tmpList = (List) result.get("DATALIST");
            Map mxMap = (HashMap)result.get("MXDATAMAP");
            Integer totalCount = (Integer) result.get("TOTALCOUNT");
            //request.getSession().setAttribute("initlist", tmpList);

            //pf.setInitList(tmpList);
            ///3.5.获取总页数数据
            pf.setTotalpage(getPageTotalCount(totalCount.intValue()));
            ///3.6.整理查询结果
            HttpSession session = request.getSession();
            session.setAttribute("DATALIST",tmpList);
            session.setAttribute("MXDATAMAP",mxMap);
            pf.setDataList(tmpList);
            pf.setMxMap(mxMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");

    }

    /**
     * doChangePageA
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doChangePage(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
            BaseException {
        //获得当前的userData对象
        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            ud = this.getUserData(request);
            pf = (JkscxForm) form;
            pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "查询输入申报日期起止时间段不合法或计算机代码为空或缴款凭证号为空，请修改后重新查询.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            Map result = (Map) ZhsbProxy.getInstance().process(vo);
            List tmpList = (List) result.get("DATALIST");
            Map mxMap = (HashMap)result.get("MXDATAMAP");
            Integer totalCount = (Integer) result.get("TOTALCOUNT");

            ///3.5.获取总页数数据
//          pf.setTotalpage(getPageTotalCount(totalCount.intValue()));
            int nextPage = Integer.parseInt(pf.getNextPage());
            int totalPage = Integer.parseInt(pf.getTotalpage());
            if (nextPage > totalPage) nextPage = totalPage;
            pf.setNextPage(String.valueOf(nextPage));
            System.out.println(pf.getCurPage() + "  " + nextPage + "  " +
                               totalPage);
            ///3.6.整理查询结果
            HttpSession session = request.getSession();
            session.setAttribute("DATALIST",tmpList);
            session.setAttribute("MXDATAMAP",mxMap);
            pf.setDataList(tmpList);
            pf.setMxMap(mxMap);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("ChangePage");

    }

    /**
     * doExport
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doExport(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {

        UserData ud = null;
        JkscxForm pf = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            ud = this.getUserData(request);
            pf = (JkscxForm) form;

            //List tmpList = (List)request.getSession().getAttribute("initlist");

            pf.setMessage("");
            // pf.setDataList(new ArrayList());
            //request.getSession().removeAttribute("initlist");
            if (!checkInputValid(pf.getSbrqq(), pf.getSbrqz())) {
                if (pf.getJsjdm() == null || pf.getJsjdm().trim().equals("")) {
                    if (pf.getJkpzh() == null || pf.getJkpzh().trim().equals("")) {
                        pf.setMessage(
                                "查询输入申报日期起止时间段不合法或计算机代码为空或缴款凭证号为空，请修改后重新查询.");
                        return mapping.findForward("Query");
                    }
                }
            }

            vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.JKSCX_PROCESSOR);
            List tmpList = (List) ZhsbProxy.getInstance().process(vo);

            //输出文件
            System.out.println("输出Excel文件记录数：" + tmpList.size());
            if (tmpList == null || tmpList.size() <= 0) {
                pf.setMessage("没有查询到数据，无法导出Excel文件");
                return mapping.findForward("Export");
            }
            String currDate = TinyTools.Date2String(new Date(System.
                    currentTimeMillis()));
            String fileName = "申报查询结果".concat(currDate).concat(".xls");
            String encodeFileName = com.ttsoft.framework.util.StringUtil.
                                    GBK2ISO(
                                            fileName);
            response.resetBuffer();
            response.setHeader("Content-disposition",
                               "attachment; filename=" + encodeFileName);
            response.setContentType("application/vnd.ms-excel");

            ExcelUtil.generateExcel(response.getOutputStream(), TITLE, COLUMS,
                                    tmpList);

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        //return mapping.findForward("Export");

    }

    public ActionForward doViewMx(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
        JkscxForm jkscxForm = (JkscxForm) form;
        jkscxForm.setDataList((List)request.getSession().getAttribute("DATALIST"));
        jkscxForm.setMxMap((Map)request.getSession().getAttribute("MXDATAMAP"));
        System.out.println("data list size:"+jkscxForm.getDataList());
        String sphm = request.getParameter("mxsphm");
        jkscxForm.makeMxDate(sphm);
        return mapping.findForward("Show");
    }

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>申报缴款书查询 ");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "");
    }

    /**
     * 检查输入查询参数合法性
     * @param sbrqq 申报日期起
     * @param sbrqz 申报日期止
     * @return 合法标志
     */
    private boolean checkInputValid(String sbrqq, String sbrqz) throws
            BaseException {
        //1.句柄申明
        boolean returnFlag = true;
        int offset = -1;
        //2.初始化数值
        Calendar calendar = new GregorianCalendar();
        //System.out.println("查询参数申报日期起：" + sbrqq + "|");
        //System.out.println("查询参数申报日期止：" + sbrqz + "|");
        sbrqq = SBStringUtils.trim(sbrqq);
        sbrqz = SBStringUtils.trim(sbrqz);
        Date dsbrqq = SBStringUtils.getDateValue(sbrqq, "yyyyMMdd");
        Date dsbrqz = SBStringUtils.getDateValue(sbrqz, "yyyyMMdd");
        calendar.setTime(dsbrqq);
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);
        int maxday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        calendar.setTime(dsbrqz);
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH);
        int endDay = calendar.get(Calendar.DAY_OF_MONTH);

//3.开始业务
        offset = (endYear * 12 + endMonth) - (startYear * 12 + startMonth);
        if (offset > 1 || offset < 0) {
            returnFlag = false;
        } else {
            int offsetday = maxday * offset + endDay - startDay;
            //    System.out.println("查询参数申报日期offsetday：" + offsetday+":"+startDay+":"+maxday);
            if (offsetday > 10 || offsetday < 0) {
                returnFlag = false;
            }
        }
        //99.返回值


        return returnFlag;
    }

    /**
     * 获取页数
     * @param rsCount 查询结果集build
     * @return 页数
     */
    private String getPageTotalCount(int rsCount) {
        //1.句柄申明
        String countTotal = "0";
        //2.开始业务
        int pageCount = 0;
        if ((rsCount % CodeConstant.JKS_PG_LENGTH) == 0) {
            pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH);
        } else {
            pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH) + 1;
        }
        countTotal = String.valueOf(pageCount);
        //99.返回值
        return countTotal;
    }
}
