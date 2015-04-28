package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web.ZfjgqysdsjbForm;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import java.util.HashMap;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
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
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import java.sql.Statement;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.QysdsUtil2008;


public class ZfjgqysdsjbProcessor implements Processor
{
    // 企业所得税税率
	private static final String QYSDS_SL = "0.33";
    private QysdsUtil2008 util = new QysdsUtil2008();

    public ZfjgqysdsjbProcessor()
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
            // case CodeConstant.SMSB_CHECKACTION:
            // result = doCheck(vo);
            // break;

        default:
            throw new ApplicationException("用户执行了系统不支持的方法或功能.");
        }

        return result;
    }


    /**
     * doShow初始化对象页面信息要素
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     */
    private Object doShow(VOPackage vo) throws BaseException
    {

        // 得到Action传递过来ZfjgqysdsjbForm对象
        ZfjgqysdsjbForm form = (ZfjgqysdsjbForm) vo.getData();
        // 得到当前时间的所属月
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        Map getsbjd = this.quarterSkssrq(curTime);
        Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
        // 税款所属开始日期
        form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
        // 税款所属结束日期
        form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
        // 税款申报日期
        form.setSbrq(SfDateUtil.getDate());
        return form;
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
        // 得到Action传递过来ZfjgqysdsjbForm对象
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();
        Connection conn = null;

        try {
            // 创建数据库连接
            conn = SfDBResource.getConnection();

            // 获取税款所属季度
            String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

            System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.getSbrq()：" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "的jd：" + jd);

            // 从页面中取得税款所属期和年度
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // 设置季度
            zfjgForm.setQh(jd);
            // 设置年度
            zfjgForm.setSknd(nd);
            System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.setSknd：" + zfjgForm.getSknd());

            // 设置form中其它所需属性
            zfjgForm = (ZfjgqysdsjbForm) QysdsNewUtil.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            // 税费核定信息
			this.getHdxx(zfjgForm);
            /* 征收方式 */
            String zsfs = zfjgForm.getZsfs();

            System.out.println(zfjgForm.getJsjdm() + "的征收方式代码：" + zsfs);

            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException(
                        "该企业已认定为核定征收户，不能在此录入，请录入核定征收季度申报表！");
            }

            //获取查帐征收汇总纳税方法，以判断是否为可填写本表用户
            int result = this.checkCzzsNsff(conn, zfjgForm);
            switch(result)
            {
                case CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_NO_DATA);
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_HZNS_FZJG:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_FZJG);
                    //break;
            }

            //查询查帐征收表分摊税额错误
            this.getCzzsFtse(conn, zfjgForm);

            // 创建QysdsReportsDeclare对象
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            // 将form中的基本信息转入QysdsReportsDeclare report 中
            QysdsNewUtil.setQysdsReport(report, zfjgForm);

            System.out.println("--------------" + report.getSbrq());
            System.out.println("--------------" + report.getSkssksrq());
            System.out.println("--------------" + report.getSkssjsrq());

            // 设置QysdsReportsTableDeclare的基本信息
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            table.setTableName(CodeConstant.TABLE_NAME_HZJNFZJG_2008);
            table.setTbblx(zfjgForm.getBbqlx()); //季报（code = "1"）
            // 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
            report.getTableContentList().put(table.getTableId(), table);

            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // 调用查询方法进行查询
            iApp.querySingleTable(report);
            // 获取查询到的具体数据
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);

            if (table.getCellContentList().size() > 0) {
                zfjgForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
                zfjgForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
                zfjgForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
            }
            //根据查询到的分摊税额构建对应的QysdsReportsItemDeclare
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID("9");
            item.setItemType("11");
            item.setItemValue(zfjgForm.getFtse());
            table.getCellContentList().put(item.getItemID(), item);

            // 将数据库中的数据翻译成页面所需数据格式
//            int[] arrs = {1, 11};
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report));

            // 测试用，完成后要删除
            System.out.println(zfjgForm.getJsjdm());
            System.out.println(zfjgForm.getSbrq());
            System.out.println(zfjgForm.getNsrmc());
            System.out.println(zfjgForm.getSknd());
            System.out.println(zfjgForm.getQh());
            System.out.println(zfjgForm.getBbqlx());
            System.out.println(zfjgForm.getSkssksrq());
            System.out.println(zfjgForm.getSkssjsrq());
            System.out.println(zfjgForm.getSwjgzzjgdm());
            System.out.println(zfjgForm.getQxdm());
            System.out.println(zfjgForm.getLrr());
            System.out.println(zfjgForm.getSwjsjdm());

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
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();

        Connection conn = null;

        // 获取税款所属季度
        String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

        // 从页面中取得税款所属期和年度
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // 设置季度
        zfjgForm.setQh(jd);
        // 设置年度
        zfjgForm.setSknd(nd);
        System.out.println(zfjgForm.getJsjdm() + "的zfjgForm.setSknd：" + zfjgForm.getSknd());
        try
        {
            // 创建数据库连接
            conn = SfDBResource.getConnection();

            // 将zfjgForm中的数据结构转换，置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // 调用saveSingleTable方法进行数据保存
            iApp.saveSingleTable(report);

            // 获取一个具有空值的QysdsReportsTableDeclare对象
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            table.getCellContentList().clear();

            // 将数据库中的数据翻译成页面所需数据格式
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report));

            zfjgForm = (ZfjgqysdsjbForm) this.doShow(vo);

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
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();

        Connection conn = null;
        // 获取税款所属季度
        String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

        // 从页面中取得税款所属期和年度
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // 设置季度
        zfjgForm.setQh(jd);
        // 设置年度
        zfjgForm.setSknd(nd);


        try {
            // 创建数据库连接
            conn = SfDBResource.getConnection();
            // 将czqysdsjbForm中的数据结构转换,置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            System.out.println("nd = " + report.getSknd() + "\nqh = " + report.getQh()
                               + "\nskssksrq = " + report.getSkssksrq() + "\nskssjsrq = " + report.getSkssjsrq()
                + "\njsjdm = " + report.getNsrjsjdm());

            // 获取数据库应用接口,调用deleteSingleTable方法删除数据
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);

            // 获取一个具有空值的QysdsReportsTableDeclare对象
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            System.out.println("11111table.getCellContentList() = " + table.getCellContentList().size());
            table.getCellContentList().clear();
            System.out.println("table.getCellContentList() = " + table.getCellContentList().size());
            // 将数据库中的数据翻译成页面所需数据格式
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report));

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
     * 计算季报类型的税款所属日期
     *
     * @param curDate
     *            日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp 使用Key ＝
     *         Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp 使用Key ＝ Skssrq.SKSSRQ_ND 得到
     *         税款所属日期所在的年度 String
     */
    public Map quarterSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        int jd = month / 3;
        if (jd == 0) {
            year--;
            jd = 4;
        }
        String nd = new Integer(year).toString();
        Timestamp skssksrqDate = new Timestamp(
            new GregorianCalendar(year, 0, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            (jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
            1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
                                               .getTime());
        Map retMap = new HashMap();
        retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
        retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
        retMap.put(Skssrq.SKSSRQ_ND, nd);
        return retMap;
    }

    /**
     * 把存放数据时过滤掉的空格复原
     *
     * @param table
     * @param a
     * @return
     */
    public static QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table, int arrs[])
    {
        String flag = null;

        if (table.getCellContentList().size() == 0) {
            flag = "0.00";
        } else {
            flag = "";
        }

        System.out.println("**显示qysdsNewUtil中的putSpace()**");

        for (int j = 1; j <= arrs.length; j = j + 2) {
            System.out.println("j___  " + j + "***" + arrs.length);
            for (int i = arrs[j - 1]; i <= arrs[j]; i++) {
                QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
                        .getCellContentList().get(String.valueOf(i));
                if (item == null) {
                    System.out.println("aaaaaaaa");
                    QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
                    item1.setItemID(String.valueOf(i));
                    item1.setItemValue(flag);
                    item1.setItemType("11");
                    table.getCellContentList().put(String.valueOf(i), item1);
                } else if (item != null && item.getItemValue() != null
                        && "".equals(item.getItemValue().trim())) {
                 System.out.println("bbbbbbbbb");
                    QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
                    item1.setItemID(String.valueOf(i));
                    item1.setItemValue(flag);
                    item1.setItemType("11");
                    table.getCellContentList().put(String.valueOf(i), item1);
                }
            }
        }
        return table;
    }

    /**
     * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
     *
     * @param QysdsReportsTableDeclare
     * @return 页面表单数据的List对象
     */
    private List translate2Page(QysdsReportsTableDeclare table)
    {
        // 创建List对象，用来存放页面表单数据
        ArrayList pagelist = new ArrayList();
        // 对不带*号的行的数据进行翻译
        Iterator it = table.getCellContentList().keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //设置分配比例为百分数形式
            if(key.indexOf(".") > 0)
            {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 16)
                {
                    String value = item.getItemValue();
                    System.out.println("id = " + key + "\nvalue = " + Arith.round(Double.parseDouble(value) * 100, 2));
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
            HashMap map = new HashMap();
//            System.out.println("2Page item.getItemID() = " + item.getItemID());
            map.put("hc", item.getItemID());
            map.put("value", item.getItemValue());
            pagelist.add(map);
        }
        // 返回List对象
        return pagelist;
    }

    /**
     * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
     *
     * @param form
     * @return 企业所得税报表申明对象
     */
    private QysdsReportsDeclare translate2Interface(ZfjgqysdsjbForm form) {

        // 创建QysdsReportsDeclare对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        // 将form中的基本信息转入QysdsReportsDeclare对象中
        QysdsNewUtil.setQysdsReport(report, form);

        // 创建企业所得税报表内单表申明对象，并置入基本信息
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        //总分机构表
        table.setTableId(CodeConstant.TABLE_ID_HZJNFZJG_2008);
        table.setTableName(CodeConstant.TABLE_NAME_HZJNFZJG_2008);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);

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
    private void translateZjgDate2Interface(ZfjgqysdsjbForm form, QysdsReportsTableDeclare table)
    {
        //QysdsReportsItemDeclare对象
        QysdsReportsItemDeclare item;
        /**
         * 分配比例有效期
         */
        //分配比例有效期初
        item = new QysdsReportsItemDeclare();
        item.setItemID("1");
        item.setItemValue(form.getFpblyxqq());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        //分配比例有效期末
        item = new QysdsReportsItemDeclare();
        item.setItemID("2");
        item.setItemValue(form.getFpblyxqz());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构纳税人识别号
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("3");
        item.setItemValue(form.getNsrsbh());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构纳税人名称
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("4");
        item.setItemValue(form.getNsrmc());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构收入总额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("5");
        item.setItemValue(form.getSrze());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构工资总额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("6");
        item.setItemValue(form.getGzze());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构资产总额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("7");
        item.setItemValue(form.getZcze());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 总机构合计
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("8");
        item.setItemValue(form.getHj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * 分支机构分摊的所得税额
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("9");
        item.setItemValue(form.getFtse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
    }

    /**
     * 保存页面分支机构明细数据
     *     将数据从ZfjgqysdsjbForm中取出填充到QysdsReportsTableDeclare对象
     * @param form ZfjgqysdsjbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateFzjgmxDate2Interface(ZfjgqysdsjbForm form, QysdsReportsTableDeclare table)
    {
        // 把页面分支机构明细数据翻译成数据库接口的数据格式
        List list = form.getQysdsjbList();

        for (int i = 0; i < list.size(); i++)
        {
            HashMap map = (HashMap) list.get(i);

            boolean flag =  true;
            for(int j = 10; j < 18; j++)
            {
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                String id = String.valueOf(j) + "." + String.valueOf(i + 1);
//                System.out.println("iid = " + id);
                //iid
                item.setItemID(id);
                switch(j)
                {
                    case 10:
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
                    case 11:
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
                    case 12:
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
                    case 13:
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
                    case 14:
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
                    case 15:
                        if(flag)
                        {
                            //分支机构合计
                            item.setItemValue((String) map.get("fzjghj"));
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
                            //分支机构分配比例
                            String temp = (String) map.get("fzjgfpbl");
                            if(!temp.equals("0")){
                            	temp = temp.substring(0, (temp.length() - 1));
                            }
                            //System.out.println("id = " + id + "\n value = " + Arith.round(Double.parseDouble(temp)/100, 4));
                            item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(temp)/100, 4)));
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
//                System.out.println("flag_" + j + " = " + flag);
//                System.out.println("item value = " + item.getItemValue());
                table.getCellContentList().put(item.getItemID(), item);
            }
        }
    }

    /**
     * 查询明细数据最大index
     *    根据填报人JSJDM，填报表ID获取对应明细数据的最大index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report) throws BaseException
    {
        int maxIndex = 0;
        //获取QysdsReportsTableDeclare对象
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql语句
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("'");

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
     * 取从一率和企业征税类型,用于页面校验
     *
     * @param form
     * @throws BaseException
     */
    private void getHdxx(ZfjgqysdsjbForm form) throws BaseException
    {
        String qyzssllx = "3"; // 缺省为正常申报

        // 企业征税的税率 相对于企业征税的税率类型
        String qyzssl = QYSDS_SL;

        // 定额征收税额
        String dezsse = "0.00";

        // 当前时间
        // 从申报页面取得申报日期和税款所属日期
        Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());

        // Map getsbjd = this.quarterSkssrq(sbrq);
        Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
        Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

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
                qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
                                              CodeConstant.SFGL_QYSDS_BBFS_NB);
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
     * @param form ZfjgqysdsjbForm
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgqysdsjbForm form) throws BaseException
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
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZQYSDSJB_2008).append("' ");
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
                if(hzff.equals(CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS))
                {
                    String hzfs = (String) result.get("2") == null ? "" : (String) result.get("2");
                    if(hzfs.equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG))
                    {
                        //汇总纳税-分支机构
                        resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                    }
                }
                else if(hzff.equals(CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS))
                {
                    //独立纳税
                    resultType = CodeConstant.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
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
     * 查询查帐征收表分摊税额错误
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgqysdsjbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try
        {
            sql.append("select yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZQYSDSJB_2008).append("' ");
            sql.append("and hc = '18' ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ftse = "0.00";
            while(rs.next())
            {
                ftse = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            }
            System.out.println("query ftse = " + ftse);

            form.setFtse(ftse);

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

}
