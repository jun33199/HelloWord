package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 *
 * @author wangcy 
 * @version 1.0
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.ttsoft.framework.exception.ExceptionUtil;
import com.syax.creports.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web.CzzsfzjgNbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web.ZfjgfzjgNbForm;


public class ZfjgfzjgNbProcessor implements Processor
{
    // 企业所得税税率
	private static final String QYSDS_SL = "0.23";
    private QysdsCzzsNb2014Util util = new QysdsCzzsNb2014Util();

    public ZfjgfzjgNbProcessor()
    {
    }

    /**
     * 实现Processor接口
     *
     * @param vo
     *            业务参数
     * @return Object VOPackage型数据
     * @throws BaseException
     *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
     *             其他异常抛出由EJB的process方法处理。
     */

    public Object process(VOPackage vo) throws BaseException
    {

        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case CodeConstant.SMSB_SHOWACTION:
            result = doShow(vo);
            break;
        case CodeConstant.SMSB_QUERYACTION:
            result = doQuery(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;

        default:
            throw new ApplicationException("用户执行了系统不支持的方法或功能.");
        }

        return result;
    }

    
    /**
     * doShow 第一次录入数据从主表的数据拼装子表所需数据
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     *
     */
    private Object doShow(VOPackage vo) throws BaseException
    {
        // 得到Action传递过来ZfjgfzjgNbForm对象
    	Map dataMap=(Map) vo.getData();
    	
    	CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) dataMap.get("CzzsfzjgNbForm");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) dataMap.get("ZfjgfzjgNbForm");
        Connection conn = null;
        try {
            // 创建数据库连接
            conn = SfDBResource.getConnection();

            // 年报期号为1
            String qh = "1";
            
            System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.getSbrq()：" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "的qh：" + qh);
           

            // 从页面中取得税款所属期和年度
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // 设置季度
            zfjgForm.setQh(qh);
            // 设置年度
            zfjgForm.setSknd(nd);
            // 设置form中其它所需属性
            zfjgForm = (ZfjgfzjgNbForm) QysdsCzzsNb2014Util.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            //机构类型为分支机构
            zfjgForm.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG);
            // 税费核定信息
			this.getHdxx(zfjgForm);
            /* 征收方式 */
            String zsfs = zfjgForm.getZsfs();


            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException("该企业已认定为核定征收户，不能在此录入，请录入核定征收季度申报表！");
            }
            //查询查帐征收表关联分配表数据
            this.getFtseFormCzzs(conn, zfjgForm, czzsfzjgNbForm);
            zfjgForm.setJsjdm(zfjgForm.getJsjdm());
            zfjgForm.setSbrq(czzsfzjgNbForm.getSbrq());
            zfjgForm.setSkssksrq(czzsfzjgNbForm.getSkssksrq());
            zfjgForm.setSkssjsrq(czzsfzjgNbForm.getSkssjsrq());
           // zfjgForm.setSkssjsrq(czzsfzjgNbForm.getZjgmc());

            zfjgForm.setQysdsNbList(this.CzzsForm2Page(zfjgForm));
            zfjgForm.setMaxIndex(1);
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // 释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        // 查询成功返回czqysdsjbForm
        return zfjgForm;
    }
    
    
    /**
     * doQuery 用于返回页面索要查询的详尽信息
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     *
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        // 得到Action传递过来ZfjgfzjgNbForm对象
    	Map dataMap=(Map) vo.getData();
    	CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) dataMap.get("CzzsfzjgNbForm");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) dataMap.get("ZfjgfzjgNbForm");
        Connection conn = null;
        try {
            // 创建数据库连接
            conn = SfDBResource.getConnection();

            // 年报期号为1
            String qh = "1";
            
            System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.getSbrq()：" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "的qh：" + qh);
           

            // 从页面中取得税款所属期和年度
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // 设置季度
            zfjgForm.setQh(qh);
            // 设置年度
            zfjgForm.setSknd(nd);
            System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.setSknd：" + zfjgForm.getSknd());

            // 设置form中其它所需属性
            zfjgForm = (ZfjgfzjgNbForm) QysdsCzzsNb2014Util.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            
            
            System.out.println(zfjgForm.getJsjdm() + "的纳税人名称：" + zfjgForm.getNsrmc());
            System.out.println(zfjgForm.getJsjdm() + "的纳税人识别号：" + zfjgForm.getNsrsbh());
            
            // 税费核定信息
			this.getHdxx(zfjgForm);
            /* 征收方式 */
            String zsfs = zfjgForm.getZsfs();

            System.out.println(zfjgForm.getJsjdm() + "的征收方式代码：" + zsfs);

            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException("该企业已认定为核定征收户，不能在此录入，请录入核定征收季度申报表！");
            }

            //获取查帐征收汇总纳税方法，以判断是否为可填写本表用户（总机构及分支机构填写）
            int result = this.checkCzzsNsff(conn, zfjgForm);
            switch(result)
            {
                case CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException("该企业尚未填报企业所得税分支机构年度纳税申报表，不能在此录入，请先录入企业所得税分支机构年度纳税申报表！");
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
            }
            
            System.out.println("-------zfjgForm.getJglx()-------" + zfjgForm.getJglx());

            //查询查帐征收表关联分配表数据
            this.getCzzsFtse(conn, zfjgForm);

            //重新取主表的数据进行覆盖
            this.getFtseFormCzzs(conn,zfjgForm,czzsfzjgNbForm);
            
            
            // 创建QysdsReportsDeclare对象
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            // 将form中的基本信息转入QysdsReportsDeclare report 中
            QysdsNewUtil.setQysdsReport(report, zfjgForm);

            System.out.println("--------------" + report.getSbrq());
            System.out.println("--------------" + report.getSkssksrq());
            System.out.println("--------------" + report.getSkssjsrq());

            // 设置QysdsReportsTableDeclare的基本信息
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            table.setTableName(CodeConstant.TABLE_NAME_ZFJGSDSNB_2012);
            table.setTbblx(zfjgForm.getBbqlx()); //季报（code = "1"）
            // 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
            report.getTableContentList().put(table.getTableId(), table);

            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // 调用查询方法进行查询
            iApp.querySingleTable(report);
            // 获取查询到的具体数据
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);

            //根据查询到的关联数据构建对应的QysdsReportsItemDeclare
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            
            if (table.getCellContentList().size() > 0) {
            	
            	System.out.println("---------------有数据--------------------");
            	zfjgForm.setJsjdm(zfjgForm.getJsjdm());
                zfjgForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
                zfjgForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
                zfjgForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
            }else{
              if(zfjgForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_ZJG)){
            	zfjgForm.setZjgmc(zfjgForm.getNsrmc());
              }else{
              	zfjgForm.setNsrsbh("");      
              }
          		zfjgForm.setSrehj("0.00");
          		zfjgForm.setGzehj("0.00");
          		zfjgForm.setZcehj("0.00");
          		zfjgForm.setFpblhj("0");
          		zfjgForm.setFpsehj("0.00");
            	System.out.println("---------------无数据--------------------");
            }

            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report, zfjgForm));
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // 释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        // 查询成功返回czqysdsjbForm
        return zfjgForm;
    }
    
    
    /**
     * 取从一率和企业征税类型,用于页面校验
     *
     * @param form
     * @throws BaseException
     */
    private void getHdxx(ZfjgfzjgNbForm form) throws BaseException
    {
        String qyzssllx = "3"; // 缺省为正常申报

        // 企业征税的税率 相对于企业征税的税率类型
        String qyzssl = QYSDS_SL;

        // 定额征收税额
        String dezsse = "0.00";

        // 当前时间
        // 从申报页面取得申报日期和税款所属日期
        Timestamp sbrq = QysdsCzzsNb2014Util.getNxetTimestamp(form.getSkssjsrq());

        // Map getsbjd = this.quarterSkssrq(sbrq);
        Timestamp skssksrq = QysdsCzzsNb2014Util.getTimestamp(form.getSkssksrq());
        Timestamp skssjsrq = QysdsCzzsNb2014Util.getTimestamp(form.getSkssjsrq());

        System.out.println(form.getJsjdm() + "sbrq：" + sbrq);
        System.out.println(form.getJsjdm() + "skssksrq：" + skssksrq);
        System.out.println(form.getJsjdm() + "skssjsrq：" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh：" + form.getQh());

        ServiceProxy proxy = new ServiceProxy();

        String bblx = form.getBbqlx();
        String jsjdm = form.getJsjdm();

        // 查询税费接口
        QysdsSet qysdsSet = null;

        // 减免资格标识
        boolean jm_type = false;
        form.setJmzg("0"); // 有减免资格

        try {
            if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR))
            {
                //年报
                qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
            }
            else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR))
            {
                //季报
                if (form.getQh() == null || (form.getQh() != null && form.getQh().trim().equals("")))
                {
                    /* 期号不能为空，如果为空抛出异常 */
                    throw new ApplicationException("系统发生异常，期号为空，请与系统管理员联系！");
                }
                System.out.println("form.getQh()::" + form.getQh());

                if ("4".equals(form.getQh()))
                {
                    /* 如果为第四季度，获取企业所得税认定信息时按年报来获取 */
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
                }
                else
                {
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
                    //重载Zsfs
                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
                    qysdsSet.setZsfs(zsfs);
                }
            }
        }
        catch (com.ttsoft.framework.exception.BaseException e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        // 1、查询企业征收方式
        Zsfs zsfs = qysdsSet.getZsfs();
        if (zsfs != null) {
            form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS : zsfs.getZsfsdm());
        }
        else
        {
            // 20070208征收方式如果取出为空则认为是查账征收企业的。
            form.setZsfs(CodeConstant.ZSFSDM_CZZS);
        }

        /* 高新技术企业认定日期 */
        Date gxqyrdrq = qysdsSet.getGxjsqy();

        // 初值
        form.setCyl("0");
        form.setXzqy("0");
        form.setDezsse("0.00");
        form.setYbjmsl("0.00");

        if (zsfs != null)
        {
            String zsfsdm = zsfs.getZsfsdm();
            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS))
            {
                if (gxqyrdrq == null)
                {
                    // 纯益率征收
                    qyzssllx = "2";
                }
                else
                {
                    // 高新技术和纯益率企业
                    qyzssllx = "5";
                    qyzssl = "0.15";
                    form.setJmzg("1"); // 有减免资格
                }
                form.setCyl(zsfs.getCyl());
            }
            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS))
            {
                // 定额征收
                qyzssllx = "4";
                // 此时本字段代表企业核定税额
                // ynsdse = zsfs.getDe();
                dezsse = zsfs.getDe();
                form.setDezsse(dezsse);
            }
        }

        // 2、查询是否是高新技术企业
        if (gxqyrdrq != null) {
            if (zsfs != null
                && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
                // 高新技术和纯益率企业
                qyzssllx = "5";
            }
            else {
                // 类型为高新技术企业
                qyzssllx = "1";
            }
            qyzssl = "0.15";
            form.setJmzg("1"); // 有减免资格

        }
        else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
            // 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
            if (qysdsSet.isXzqy()) {
                form.setXzqy("1");
                form.setJmzg("1"); // 有减免资格
            }
        }

        if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
            && qysdsSet.getYbjmsl() != null) {
            // 非乡镇企业的减免情况
            form.setJmzg("1"); // 有减免资格
            DecimalFormat ft = new DecimalFormat("0.00");
            form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
        }
        form.setQyzslx(qyzssllx);
        form.setSysl(qyzssl);

        /* 核定信息输出 */
        System.out.println("-------------核定信息--------------");
        System.out.println("企业征收税率类型-" + qyzssllx);
        System.out.println("减免资格-" + form.getJmzg());
        System.out.println("一般减免税率-" + form.getYbjmsl());
        System.out.println("征收方式-" + form.getZsfs());
        System.out.println("纯益率-" + form.getCyl());
        System.out.println("定额-" + form.getDezsse());
        System.out.println("适用税率-" + form.getSysl());
        System.out.println("-------------核定信息--------------");
    }

    /**
     * 判断当前纳税人查帐征收申报方式
     * @param conn Connection
     * @param form ZfjgfzjgNbForm
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgfzjgNbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        //汇总纳税-总机构
        int resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_ZJG;

        HashMap result = new HashMap();
        try
        {
            sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZZSSDSNB_2012).append("' ");
            sql.append("and to_number(hc) < 3 order by to_number(hc) ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            while(rs.next())
            {
                result.put(rs.getString("hc"), rs.getString("yz"));
            }
            if(result.size() == 0)
            {
                //没有数据
                resultType = CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA;
            }
            else
            {
                String hzff = (String) result.get("1");
                if(hzff.equals(CodeConstant.HZNSFF_QYSDSNB2012_CZZSSDS_HZNS))
                {
                    String hzfs = (String) result.get("2") == null ? "" : (String) result.get("2");
                    if(hzfs.equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG))
                    {
                        //汇总纳税-分支机构
                        resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                        form.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG);
                    }else
                    {
                    	form.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_ZJG);
                    }
                }
                else if(hzff.equals(CodeConstant.HZNSFF_QYSDSNB2012_CZZSSDS_DLNS))
                {
                    //独立纳税
                    resultType = CodeConstant.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
            
            System.out.println("******************************"+resultType);
            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("查询查帐征收表纳税方法错误！");
        }
        return resultType;
    }
   
    /**
     * 查询查帐征收表关联分配表数据
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgfzjgNbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try
        {
            sql.append("select hc,yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZZSSDSNB_2012).append("' ");
            sql.append("and hc in('12','24','25','26','29','30') ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ynsdse="0.00";
            String zjgftse="0.00";
            String zjgfpse="0.00";
            String fzjgftse = "0.00";
            String fzjgfpbl = "0.00";
            String fzjgfpse = "0.00";
            while(rs.next())
            {
            	if(rs.getString("hc").equals("12")){
            		ynsdse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("24")){
            		zjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("25")){
            		zjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("26")){
            		fzjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("29")){
            		fzjgfpbl=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("30")){
            		fzjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            }
            System.out.println("query ynsdse = " + ynsdse);
            System.out.println("query zjgftse = " + zjgftse);
            System.out.println("query zjgfpse = " + zjgfpse);
            System.out.println("query fzjgftse = " + fzjgftse);
            System.out.println("query fzjgfpbl = " + fzjgfpbl);
            System.out.println("query fzjgfpse = " + fzjgfpse);
            
           if(form.getJglx().equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG))
           {
        	   form.setFzjgftse(fzjgftse);
        	   form.setFzjgfpbl(fzjgfpbl);
        	   form.setFzjgfpse(fzjgfpse);
        	   form.setFzjgnsrsbh(form.getNsrsbh());
        	   form.setFzjgnsrmc(form.getNsrmc());
           }else{
        	   form.setYnsdse(ynsdse);
        	   form.setZjgftse(zjgftse);
        	   form.setZjgfpse(zjgfpse);
        	   form.setFzjgftse(fzjgftse);        	   
           }

            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("查询查帐征收表分摊税额错误！");
        }
    }
    
    /**
     * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
     *
     * @param QysdsReportsTableDeclare
     * @return 页面表单数据的List对象
     */
    private List translate2Page(QysdsReportsTableDeclare table,ZfjgfzjgNbForm zfjgForm)
    {
        // 创建List对象，用来存放页面表单数据
        ArrayList pagelist = new ArrayList();
        // 对不带*号的行的数据进行翻译
        Iterator it = table.getCellContentList().keySet().iterator();
        Iterator it_temp = table.getCellContentList().keySet().iterator();
        //数据库已经保存的分支机构分配比例
		String fzjgFpbl_old="0.00";
		//主表新填写的分支机构分配比例
		String fzjgFpbl_new=((String) zfjgForm.getFzjgfpbl()) == null ? "0.00" : (String) zfjgForm.getFzjgfpbl();
		
		//数据库已经保存的分支机构分配比例
		String fzjgFpsdse_old="0.00";
		//主表新填写的分支机构分配比例
		String fzjgFpsdse_new=((String) zfjgForm.getFzjgfpse()) == null ? "0.00" : (String) zfjgForm.getFzjgfpse();
        //重新取的数据中的数据 放在下面的的while循环跟次序有关可能出错
        while (it_temp.hasNext()) {
        	String key = (String) it_temp.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //设置分配比例为百分数形式
            if(key.indexOf(".") > 0)
            {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17)
                {
                    String value = item.getItemValue();
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
        	if(item.getItemID().equals("17.1")){
    			fzjgFpbl_old=item.getItemValue();
    		}
    		if(item.getItemID().equals("18.1")){
    			fzjgFpsdse_old=item.getItemValue();
    		}
        }
        
        while (it.hasNext()) {
            String key = (String) it.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //设置分配比例为百分数形式
//            if(key.indexOf(".") > 0)
//            {
//                String head = key.substring(0, key.indexOf("."));
//                if(Integer.parseInt(head) == 17)
//                {
//                    String value = item.getItemValue();
//                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
//                }
//            }
            HashMap map = new HashMap();
           
            if(item.getItemID().equals("1")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }
            if(item.getItemID().equals("2")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }
    		//重新赋值  应客户要求不在进行重新赋值
//    		if(item.getItemID().equals("6")){
//    			item.setItemValue(zfjgForm.getFzjgftse());
//    		}
//    		if(item.getItemID().equals("17.1")){
//    			item.setItemValue(fzjgFpbl_new+"%");
//    		}
//    		if(item.getItemID().equals("18.1")){
//    			item.setItemValue(fzjgFpsdse_new);
//    		}
//    		
//    		//重新计算分支机构分配比例和分支机构分配税额
//
//    		DecimalFormat deFormat = new DecimalFormat("#0.00");
//    		
//    		if(item.getItemID().equals("10")){
//	    		BigDecimal fpblhj_old=new BigDecimal(item.getItemValue().replaceAll("%", ""));
//	    		BigDecimal fpblhj_new=fpblhj_old.add(new BigDecimal(fzjgFpbl_new)).subtract(new BigDecimal(fzjgFpbl_old.replaceAll("%",""))) ;
//	    		item.setItemValue(deFormat.format(fpblhj_new)+"%");
//    		}
//    		if(item.getItemID().equals("11")){
//	    		BigDecimal fpsehj_old=new BigDecimal(item.getItemValue());       
//	            BigDecimal fpsehj_new=fpsehj_old.add(new BigDecimal(fzjgFpsdse_new)).subtract(new BigDecimal(fzjgFpsdse_old)) ;
//	            item.setItemValue(deFormat.format(fpsehj_new));
//            }
            
            System.out.println("item.getItemID() :: "+item.getItemID());
            System.out.println("item.getItemValue() :: "+item.getItemValue());
            System.out.println("");
            
            map.put("hc", item.getItemID());
            map.put("value", item.getItemValue());
            pagelist.add(map);
        }
        // 返回List对象
        return pagelist;
    }
    
    /**
     * 查询明细数据最大index
     *    根据填报人JSJDM，填报表ID获取对应明细数据的最大index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, ZfjgfzjgNbForm form) throws BaseException
    {
        int maxIndex = 0;
        //获取QysdsReportsTableDeclare对象
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql语句
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("' ");
        sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
        sql.append("and qh = '").append(form.getQh()).append("' ");
        sql.append("and sknd = '").append(form.getSknd()).append("' ");

        System.out.println("sql:\n" + sql.toString());
        
        try
        {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            throw new ApplicationException("查询明细数据最大index失败！");
        }

        return maxIndex;
    }
    
    
    /**
     * doSave 用于存储页面提交的详尽处理信息
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     */
    private Object doSave(VOPackage vo) throws BaseException {
        // 得到Action传递过来ZfjgqysdsjbForm对象
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) vo.getData();

        Connection conn = null;

        // 年度期号为1
        String qh = "1";

        // 从页面中取得税款所属期和年度
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // 设置季度
        zfjgForm.setQh(qh);
        // 设置年度
        zfjgForm.setSknd(nd);
        try
        {
            // 创建数据库连接
            conn = SfDBResource.getConnection();

            // 将zfjgForm中的数据结构转换，置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            report.setVersion(CodeConstant.SBZL_QYSDSNB_VERSION_2012);
            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // 调用saveSingleTable方法进行数据保存
            iApp.saveSingleTable(report);

            // 获取一个具有空值的QysdsReportsTableDeclare对象
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            table.getCellContentList().clear();

            // 将数据库中的数据翻译成页面所需数据格式
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            //vo.setData(zfjgForm);

            //zfjgForm = (ZfjgfzjgNbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // 抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // 释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        // 保存成功返回czqysdsjbForm
        return zfjgForm;
    }
    
    /**
     * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
     *
     * @param form
     * @return 企业所得税报表申明对象
     */
    private QysdsReportsDeclare translate2Interface(ZfjgfzjgNbForm form) {

        // 创建QysdsReportsDeclare对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        // 将form中的基本信息转入QysdsReportsDeclare对象中
        QysdsNewUtil.setQysdsReport(report, form);

        // 创建企业所得税报表内单表申明对象，并置入基本信息
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        //总分机构表
        table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
        table.setTableName(CodeConstant.TABLE_NAME_ZFJGSDSNB_2012);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

        // 保存页面总机构数据
        this.translateZjgDate2Interface(form, table);
        // 保存页面分支机构明细数据
        this.translateFzjgmxDate2Interface(form, table);
        // 单元格空值域处理
        report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));

        return report;
    }
    
    
    /**
     * 保存页面总机构数据
     *    将数据从ZfjgqysdsjbForm中取出填充到QysdsReportsTableDeclare对象
     * @param form ZfjgqysdsjbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateZjgDate2Interface(ZfjgfzjgNbForm form, QysdsReportsTableDeclare table)
    {
        QysdsReportsItemDeclare item;
        /**
         * 总机构名称
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("1");
        item.setItemValue(form.getZjgmc());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构纳税人识别号
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("2");
        item.setItemValue(form.getNsrsbh());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 应纳所得税额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("3");
        item.setItemValue(form.getYnsdse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构分摊所得税额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("4");
        item.setItemValue(form.getZjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构财政集分配所得税额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("5");
        item.setItemValue(form.getZjgfpse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 分支机构分摊所得税额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("6");
        item.setItemValue(form.getFzjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 收入额合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("7");
        item.setItemValue(form.getSrehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 工资额合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("8");
        item.setItemValue(form.getGzehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 资产额合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("9");
        item.setItemValue(form.getZcehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
        
        /**
         * 工资额合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("10");
        item.setItemValue(form.getFpblhj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 资产额合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("11");
        item.setItemValue(form.getFpsehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);        
    }

    /**
     * 保存页面分支机构明细数据
     *     将数据从ZfjgqysdsjbForm中取出填充到QysdsReportsTableDeclare对象
     * @param form ZfjgfzjgNbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateFzjgmxDate2Interface(ZfjgfzjgNbForm form, QysdsReportsTableDeclare table)
    {
        // 把页面分支机构明细数据翻译成数据库接口的数据格式
        List list = form.getQysdsNbList();

        for (int i = 0; i < list.size(); i++)
        {
            HashMap map = (HashMap) list.get(i);

            boolean flag =  true;
            for(int j = 12; j < 19; j++)
            {
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                String id = String.valueOf(j) + "." + String.valueOf(i + 1);
                item.setItemID(id);
                switch(j)
                {
                    case 12:
                        //分支机构纳税人识别号
                        if(map.get("fzjgnsrsbh") == null || map.get("fzjgnsrsbh").equals(""))
                        {
                            flag = false;
                            item.setItemValue("");
                        }
                        else
                        {
                            item.setItemValue((String) map.get("fzjgnsrsbh"));
                            item.setItemType("11");
                        }
                        break;
                    case 13:
                        if(flag)
                        {
                            //分支机构名称
                            item.setItemValue((String) map.get("fzjgmc"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 14:
                        if(flag)
                        {
                            //分支机构收入总额
                            item.setItemValue((String) map.get("fzjgsrze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 15:
                        if(flag)
                        {
                            //分支机构工资总额
                            item.setItemValue((String) map.get("fzjggzze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 16:
                        if(flag)
                        {
                            //分支机构资产总额
                            item.setItemValue((String) map.get("fzjgzcze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 17:
                        if(flag)
                        {
                            //分支机构分配比例
                            String temp = (String) map.get("fzjgfpbl");
                            if(!temp.equals("0")){
                            	temp = temp.substring(0, (temp.length() - 1));
                            }
                            item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(temp)/100, 4)));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 18:
                        if(flag)
                        {
                            //分支机构分配税额
                            item.setItemValue((String) map.get("fzjgfpse"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                }
                table.getCellContentList().put(item.getItemID(), item);
            }
        }
    }
    
    /**
     * doDelete 用于删除页面提交的详尽处理信息
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        System.out.println("---------doDelete");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) vo.getData();

        Connection conn = null;
        // 年报期号为1
        String qh ="1"; 

        // 从页面中取得税款所属期和年度
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // 设置季度
        zfjgForm.setQh(qh);
        // 设置年度
        zfjgForm.setSknd(nd);

        try {
            // 创建数据库连接
            conn = SfDBResource.getConnection();
            // 将czqysdsjbForm中的数据结构转换,置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);

            // 获取数据库应用接口,调用deleteSingleTable方法删除数据
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);

            //企业所得税报表内单表申明对象
			QysdsReportsTableDeclare table32 = new QysdsReportsTableDeclare();
			table32.setTableId(CodeConstant.TABLE_ID_CZZSSDSNB_2012);
			table32.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// set table
			report.getTableContentList().put(table32.getTableId(), table32);
			// 调用delete方法进行数据删除
			iApp.deleteSingleTable(report);
            
            // 获取一个具有空值的QysdsReportsTableDeclare对象
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            System.out.println("11111table.getCellContentList() = " + table.getCellContentList().size());
            table.getCellContentList().clear();
            System.out.println("table.getCellContentList() = " + table.getCellContentList().size());
            // 将数据库中的数据翻译成页面所需数据格式
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            //vo.setData(zfjgForm);            
            //zfjgForm = (ZfjgqysdsjbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // 抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // 释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        // 删除成功返回hdzssdsnbForm
        return zfjgForm;
    }
        
    
    /**
     * 查询查帐征收表关联分配表数据 
     * 第一次录入通过CzzsfzjgNbForm拼装页面所需要数据
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getFtseFormCzzs(Connection conn, ZfjgfzjgNbForm form,CzzsfzjgNbForm czzsfzjgNbForm) throws BaseException
    {

        String fzjgftse = "0.00";
        String fzjgfpbl = "0.00";
        String fzjgfpse = "0.00";  
        
        String ynsdse="0.00"; 
        String zjgftse="0.00"; 
        String zjgfpse="0.00"; 
        List list = czzsfzjgNbForm.getQysdsnbList();
    	for (int i = 0; i < list.size(); i++) {
    		HashMap map = (HashMap) list.get(i);
    		String hc = (String) map.get("hc");
    		if(hc.equals("32")){
    			double fzjgftseCzzs=Double.parseDouble((String)map.get("lje"));
    			ynsdse=fzjgftseCzzs*2+"";
    			zjgftse=fzjgftseCzzs/2+"";
    			zjgfpse=fzjgftseCzzs/2+"";
    			fzjgftse=fzjgftseCzzs+"";
    			
    		}
    		if(hc.equals("34")){
    			fzjgfpbl=(String)map.get("lje");
    		}
    		if(hc.equals("35")){
    			fzjgfpse=(String)map.get("lje");
    		}
    	} 
    	form.setYnsdse(ynsdse);
    	form.setZjgftse(zjgftse);
    	form.setZjgfpse(zjgfpse);
    	
        form.setFzjgftse(fzjgftse);
        form.setFzjgfpbl(fzjgfpbl);
        form.setFzjgfpse(fzjgfpse);
        form.setFzjgnsrsbh(form.getNsrsbh());
        form.setFzjgnsrmc(form.getNsrmc());
          
    }
    
    /**
     * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
     * 第一次录入通过CzzsfzjgNbForm拼装页面所需要数据
     * @param QysdsReportsTableDeclare
     * @return 页面表单数据的List对象
     */
    private List CzzsForm2Page(ZfjgfzjgNbForm form)
    {
        // 创建List对象，用来存放页面表单数据
        ArrayList pagelist = new ArrayList();
     
       //纳税人识别号
       Map map1=new HashMap();     
       map1.put("hc", "1");
       map1.put("value", "");
       pagelist.add(map1);
        
       //纳税人名称
       Map map2=new HashMap();     
       map2.put("hc", "2");
       map2.put("value", "");
       pagelist.add(map2);
       
       //应纳所得税额
       Map map3=new HashMap();     
       map3.put("hc", "3");
       map3.put("value", "0.00");
       pagelist.add(map3);
       
       //总机构分摊所得税额
       Map map4=new HashMap();     
       map4.put("hc", "4");
       map4.put("value", "0.00");
       pagelist.add(map4);
       
       //总机构财政集中分配所得税额
       Map map5=new HashMap();     
       map5.put("hc", "5");
       map5.put("value", "0.00");
       pagelist.add(map5);
       
       //分支机构分摊所得税额
       Map map6=new HashMap();     
       map6.put("hc", "6");
       //map6.put("value", form.getFzjgftse());
       map6.put("value", "0.00");
       pagelist.add(map6);
       
       Map map10=new HashMap();     
       map10.put("hc", "10");
       //map10.put("value",form.getFzjgfpbl()+"%");
       map10.put("value","0.00");
       pagelist.add(map10);
       
       Map map11=new HashMap();     
       map11.put("hc", "11");
       //map11.put("value", form.getFzjgfpse());
       map11.put("value", "0.00");
       pagelist.add(map11);
       
       Map map12=new HashMap();     
       map12.put("hc", "12.1");
       map12.put("value", form.getNsrsbh());
       pagelist.add(map12);
       
       Map map13=new HashMap();     
       map13.put("hc", "13.1");
       map13.put("value", form.getNsrmc());
       pagelist.add(map13);
       
       Map map14=new HashMap();     
       map14.put("hc", "14.1");
       map14.put("value", "0.00");
       pagelist.add(map14);
       
       Map map15=new HashMap();     
       map15.put("hc", "15.1");
       map15.put("value", "0.00");
       pagelist.add(map15);
       
       
       Map map16=new HashMap();     
       map16.put("hc", "16.1");
       map16.put("value", "0.00");
       pagelist.add(map16);
       
       Map map17=new HashMap();     
       map17.put("hc", "17.1");
       //map17.put("value", form.getFzjgfpbl()+"%");
       map17.put("value", "0.00");
       pagelist.add(map17);
       
       Map map18=new HashMap();     
       map18.put("hc", "18.1");
       //map18.put("value", form.getFzjgfpse());
       map18.put("value", "0.00");
       pagelist.add(map18);
       
        // 返回List对象
        return pagelist;
    }
}
