package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.processor;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.check.Checker;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.HashMap;
import com.ttsoft.framework.exception.ApplicationException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.sql.Connection;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.web.YgyjzjlnstzbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class YgyjzjlnstzbProcessor implements Processor {
    /**
     * 实现Processor接口
     * @param vo 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     * 		1 当传过来的操作类型不对时抛出
     * 		2 当调用的业务方法抛出业务异常时向上传递抛出
     * 	其他异常抛出由EJB的process方法处理。
     */
    public Object process(VOPackage vo) throws BaseException {

        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case CodeConstant.SMSB_SHOWACTION:
            result = doShow(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
            result = doDelete(vo);
            break;
        case CodeConstant.SMSB_CHECKACTION:
            result = doCheck(vo);
            break;
        default:
            throw new ApplicationException(
                    "用户执行了系统不支持的方法或功能.");
        }

        return result;
    }

    /**
     * doShow初始化对象页面信息要素
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doShow(VOPackage vo) throws BaseException {

        YgyjzjlnstzbForm nbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection conn = null;
        try {
//			获取数据库连接
            conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsUtil2008.setQysdsReport(report, nbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2008_7);
            table.setTableName(CodeConstant.TABLE_NAME_2008_7);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            int[] arrs = {1, 50};

            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_2008_7);
            List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("GD");
            nbForm.setDataList(listgd);
        } catch (Exception ex) {
//			抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return nbForm;
    }

    /**
     * doSave    用于保存页面信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    private Object doSave(VOPackage vo) throws BaseException {

        YgyjzjlnstzbForm ygyjzjlnstzbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection con = null;
        try {
            //获取数据库连接
            con = SfDBResource.getConnection();
            //将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(
                    ygyjzjlnstzbForm);
//			获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(con,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
//			更新审核状态为“保存成功”
            iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(con);
        }
        return ygyjzjlnstzbForm;
    }

    /**
     * doDelete    用于删除页面数据
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        YgyjzjlnstzbForm form = (YgyjzjlnstzbForm) vo.getData();
        Connection conn = null;
        try {
//			获取数据库连接

            conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(form);
//			获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            try {
                iApp.deleteSingleTable(report);
                iApp.updateCheckStatus(report, "");
                QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
                table.setTableId(CodeConstant.TABLE_ID_2008_7);
                table.setTableName(CodeConstant.TABLE_NAME_2008_7);
                table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
                table = (QysdsReportsTableDeclare) report.getTableContentList().
                        get(CodeConstant.TABLE_ID_2008_7);
                int[] arrs = {1, 50};
                List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("GD");
                form.setDataList(listgd);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * doCheck    用于校验页面数据
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    private Object doCheck(VOPackage vo) throws BaseException {
        YgyjzjlnstzbForm ygyjzjlnstzbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection con = null;
        try {
//			创建数据库连接
            con = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(
                    ygyjzjlnstzbForm);
            /**
             * 单表校验
             * 通过后继续执行；
             * 未通过退出执行，页面提示校验结果信息
             */
            Checker checker = CheckerFactory.getAInstance(con,
                    CheckerFactory.ACCESS_MODEL_APP_QYSDS);
            IAppAccess iApp = AppAccessFactory.getAInstance(con,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            //进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
            List listSingle = checker.checkSingeTable(report,
                    Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
            ygyjzjlnstzbForm.setCheckList(listSingle);
            /*如果校验通过，调用接口保存数据*/
            if (listSingle == null ||
                (listSingle != null && listSingle.size() == 0)) {
                iApp.saveSingleTable(report);
                //更新审核状态为“单表审核通过”
                iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SINGLE_PASS);
            } else if (listSingle.size() > 0) {
                //单表审核未通过
                iApp.updateCheckStatus(report, "");
            }
        } catch (Exception ex) {
//			抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
//			释放数据库连接
            SfDBResource.freeConnection(con);
        }
        return ygyjzjlnstzbForm;
    }


    /**
     * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
     * 页面数据结构-->接口数据结构
     * @param ZcmxbForm
     * @return 企业所得税报表申明对象
     */
    private QysdsReportsDeclare translate2Interface(YgyjzjlnstzbForm nbForm) {
//		企业所得税报表申明对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsUtil2008.setQysdsReport(report, nbForm); //对report 进行一系列的设置
//		企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_2008_7);
        table.setTableName(CodeConstant.TABLE_NAME_2008_7);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        List list = nbForm.getDataList(); //存放固定行的LIST

        //翻译固定行数据
        for (int i = 0; i < list.size(); i++) {
            HashMap map = new HashMap();
            map = (HashMap) list.get(i);
            String hc = (String) map.get("hc");
            int hcnum = Integer.parseInt(hc);

            //设置固定行第一列数据
            QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
            item1.setItemID((5 * hcnum - 4) + "");
            item1.setItemValue((String) map.get("qczzje"));
            item1.setItemType("11");
            table.getCellContentList().put(item1.getItemID(), item1);

            //设置固定行第二列数据
            QysdsReportsItemDeclare item2 = new QysdsReportsItemDeclare();
            item2.setItemID((5 * hcnum - 3) + "");
            item2.setItemValue((String) map.get("qcjsjc"));
            item2.setItemType("11");
            table.getCellContentList().put(item2.getItemID(), item2);

            //设置固定行第三列数据
            QysdsReportsItemDeclare item3 = new QysdsReportsItemDeclare();
            item3.setItemID((5 * hcnum - 2) + "");
            item3.setItemValue((String) map.get("qmzzje"));
            item3.setItemType("11");
            table.getCellContentList().put(item3.getItemID(), item3);

            //设置固定行第四列数据
            QysdsReportsItemDeclare item4 = new QysdsReportsItemDeclare();
            item4.setItemID((5 * hcnum - 1) + "");
            item4.setItemValue((String) map.get("qmjsjc"));
            item4.setItemType("11");
            table.getCellContentList().put(item4.getItemID(), item4);

            //设置固定行第五列数据
            QysdsReportsItemDeclare item5 = new QysdsReportsItemDeclare();
            item5.setItemID((5 * hcnum) + "");
            item5.setItemValue((String) map.get("nstze"));
            item5.setItemType("11");
            table.getCellContentList().put(item5.getItemID(), item5);

        }

        report.getTableContentList().put(table.getTableId(),
                                         QysdsUtil2008.cleanSpace(table));
        return report;
    }

    /**
     * 将接口数据结构中的数据转换置入页面要求的数据结构
     * 接口数据结构-->页面数据结构
     * @param QysdsReportsTableDeclare
     * @return 企业所得税报表申明对象
     */
    private HashMap translate2Page(QysdsReportsTableDeclare table) {
        HashMap backMap = new HashMap();
        List pagelist = new ArrayList();

        for (int i = 1; i < 11; i++) {

            HashMap map = new HashMap();
            map.put("hc", String.valueOf(i));
            map.put("qczzje",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 4))).
                    getItemValue());
            map.put("qcjsjc",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 3))).
                    getItemValue());
            map.put("qmzzje",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i -2))).
                    getItemValue());
            map.put("qmjsjc",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 1))).
                    getItemValue());
            map.put("nstze",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i))).
                    getItemValue());
            pagelist.add(map);
        }

        backMap.put("GD", pagelist);

        return backMap;
    }

}
