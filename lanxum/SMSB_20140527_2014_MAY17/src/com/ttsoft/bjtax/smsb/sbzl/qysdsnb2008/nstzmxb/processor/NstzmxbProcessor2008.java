package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.nstzmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.nstzmxb.web.NstzmxbForm2008;

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
public class NstzmxbProcessor2008 implements Processor {
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

        NstzmxbForm2008 nbForm = (NstzmxbForm2008) vo.getData();
        Connection conn = null;
        try {
//			获取数据库连接
            conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsUtil2008.setQysdsReport(report, nbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2008_3);
            table.setTableName(CodeConstant.TABLE_NAME_2008_3);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            int[] arrs = {1, 134};

            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_2008_3);
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

        NstzmxbForm2008 nstzmxzjbForm = (NstzmxbForm2008) vo.getData();
        Connection con = null;
        try {
            //获取数据库连接
            con = SfDBResource.getConnection();
            //将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(nstzmxzjbForm);
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
        return nstzmxzjbForm;
    }

    /**
     * doDelete    用于删除页面数据
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        NstzmxbForm2008 form = (NstzmxbForm2008) vo.getData();
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
                table.setTableId(CodeConstant.TABLE_ID_2008_3);
                table.setTableName(CodeConstant.TABLE_NAME_2008_3);
                table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
                table = (QysdsReportsTableDeclare) report.getTableContentList().
                        get(CodeConstant.TABLE_ID_2008_3);
                int[] arrs = {1, 134};
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
        NstzmxbForm2008 nstzmxzjbForm = (NstzmxbForm2008) vo.getData();
        Connection con = null;
        try {
//			创建数据库连接
            con = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(nstzmxzjbForm);
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
            nstzmxzjbForm.setCheckList(listSingle);
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
        return nstzmxzjbForm;
    }


    /**
     * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
     * 页面数据结构-->接口数据结构
     * @param ZcmxbForm
     * @return 企业所得税报表申明对象
     */
    private QysdsReportsDeclare translate2Interface(NstzmxbForm2008 nbForm) throws
            ApplicationException {
//		企业所得税报表申明对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsUtil2008.setQysdsReport(report, nbForm); //对report 进行一系列的设置
//		企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_2008_3);
        table.setTableName(CodeConstant.TABLE_NAME_2008_3);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        List list = nbForm.getDataList(); //存放固定行的LIST
        //翻译固定行数据
        for (int i = 0; i < list.size(); i++) {
            HashMap map = new HashMap();
            map = (HashMap) list.get(i);
            String hc = (String) map.get("hc");
            int hcnum = Integer.parseInt(hc);

            switch (hcnum) {

            case 1:
                this.putTable(table, map, "tzje", "1");
                this.putTable(table, map, "tjje", "2");
                break;
            case 2:
                this.putTable(table, map, "tzje", "3");
                break;
            case 3:
                this.putTable(table, map, "ssje", "4");
                this.putTable(table, map, "tzje", "5");
                break;
            case 4:
                this.putTable(table, map, "zzje", "6");
                this.putTable(table, map, "ssje", "7");
                this.putTable(table, map, "tzje", "8");
                break;
            case 5:
                this.putTable(table, map, "zzje", "9");
                this.putTable(table, map, "ssje", "10");
                this.putTable(table, map, "tzje", "11");
                this.putTable(table, map, "tjje", "12");
                break;
            case 6:
                this.putTable(table, map, "tjje", "13");
                break;
            case 7:
                this.putTable(table, map, "tzje", "14");
                this.putTable(table, map, "tjje", "15");
                break;
            case 8:
                this.putTable(table, map, "zzje", "16");
                this.putTable(table, map, "ssje", "17");
                this.putTable(table, map, "tzje", "18");
                this.putTable(table, map, "tjje", "19");
                break;
            case 9:
                this.putTable(table, map, "zzje", "20");
                this.putTable(table, map, "ssje", "21");
                this.putTable(table, map, "tzje", "22");
                this.putTable(table, map, "tjje", "23");
                break;
            case 10:
                this.putTable(table, map, "tzje", "24");
                this.putTable(table, map, "tjje", "25");
                break;
            case 11:
                this.putTable(table, map, "zzje", "26");
                this.putTable(table, map, "ssje", "27");
                this.putTable(table, map, "tzje", "28");
                this.putTable(table, map, "tjje", "29");
                break;
            case 12:
                this.putTable(table, map, "tjje", "30");
                break;
            case 13:

                this.putTable(table, map, "tzje", "31");
                break;
            case 14:
                this.putTable(table, map, "tjje", "32");
                break;
            case 15:
                this.putTable(table, map, "tjje", "33");
                break;
            case 16:
                this.putTable(table, map, "tjje", "34");
                break;
            case 17:
                this.putTable(table, map, "tjje", "35");
                break;
            case 18:
                this.putTable(table, map, "tjje", "36");
                break;
            case 19:
                this.putTable(table, map, "zzje", "37");
                this.putTable(table, map, "ssje", "38");
                this.putTable(table, map, "tzje", "39");
                this.putTable(table, map, "tjje", "40");
                break;
            case 20:
                this.putTable(table, map, "tzje", "41");
                this.putTable(table, map, "tjje", "42");
                break;
            case 21:
                this.putTable(table, map, "tjje", "43");
                break;
            case 22:
                this.putTable(table, map, "zzje", "44");
                this.putTable(table, map, "ssje", "45");
                this.putTable(table, map, "tzje", "46");
                this.putTable(table, map, "tjje", "47");
                break;
            case 23:
                this.putTable(table, map, "zzje", "48");
                this.putTable(table, map, "ssje", "49");
                this.putTable(table, map, "tzje", "50");
                this.putTable(table, map, "tjje", "51");
                break;
            case 24:
                this.putTable(table, map, "zzje", "52");
                this.putTable(table, map, "ssje", "53");
                this.putTable(table, map, "tzje", "54");
                this.putTable(table, map, "tjje", "55");
                break;
            case 25:
                this.putTable(table, map, "zzje", "56");
                this.putTable(table, map, "ssje", "57");
                this.putTable(table, map, "tzje", "58");
                this.putTable(table, map, "tjje", "59");
                break;
            case 26:
                this.putTable(table, map, "zzje", "60");
                this.putTable(table, map, "ssje", "61");
                this.putTable(table, map, "tzje", "62");
                break;
            case 27:
                this.putTable(table, map, "tzje", "63");
                this.putTable(table, map, "tjje", "64");
                break;
            case 28:
                this.putTable(table, map, "zzje", "65");
                this.putTable(table, map, "ssje", "66");
                this.putTable(table, map, "tzje", "67");
                break;
            case 29:
                this.putTable(table, map, "zzje", "68");
                this.putTable(table, map, "ssje", "69");
                this.putTable(table, map, "tzje", "70");
                this.putTable(table, map, "tjje", "71");
                break;
            case 30:
                this.putTable(table, map, "zzje", "72");
                this.putTable(table, map, "ssje", "73");
                this.putTable(table, map, "tzje", "74");
                break;
            case 31:
                this.putTable(table, map, "zzje", "75");
                this.putTable(table, map, "tzje", "76");
                break;
            case 32:
                this.putTable(table, map, "zzje", "77");
                this.putTable(table, map, "tzje", "78");
                break;
            case 33:
                this.putTable(table, map, "zzje", "79");
                this.putTable(table, map, "tzje", "80");
                break;
            case 34:
                this.putTable(table, map, "zzje", "81");
                this.putTable(table, map, "ssje", "82");
                this.putTable(table, map, "tzje", "83");
                this.putTable(table, map, "tjje", "84");
                break;
            case 35:
                this.putTable(table, map, "zzje", "85");
                this.putTable(table, map, "ssje", "86");
                this.putTable(table, map, "tzje", "87");
                this.putTable(table, map, "tjje", "88");
                break;
            case 36:
                this.putTable(table, map, "zzje", "89");
                this.putTable(table, map, "ssje", "90");
                this.putTable(table, map, "tzje", "91");
                this.putTable(table, map, "tjje", "92");
                break;
            case 37:
                this.putTable(table, map, "zzje", "93");
                this.putTable(table, map, "tzje", "94");
                break;
            case 38:
                this.putTable(table, map, "zzje", "95");
                this.putTable(table, map, "tzje", "96");
                break;
            case 39:
                this.putTable(table, map, "tjje", "97");
                break;
            case 40:
                this.putTable(table, map, "zzje", "98");
                this.putTable(table, map, "ssje", "99");
                this.putTable(table, map, "tzje", "100");
                this.putTable(table, map, "tjje", "101");
                break;
            case 41:
                this.putTable(table, map, "tzje", "102");
                this.putTable(table, map, "tjje", "103");
                break;
            case 42:
                this.putTable(table, map, "zzje", "104");
                this.putTable(table, map, "ssje", "105");
                this.putTable(table, map, "tzje", "106");
                this.putTable(table, map, "tjje", "107");
                break;
            case 43:
                this.putTable(table, map, "tzje", "108");
                this.putTable(table, map, "tjje", "109");
                break;
            case 44:
                this.putTable(table, map, "tzje", "110");
                this.putTable(table, map, "tjje", "111");
                break;
            case 45:
                this.putTable(table, map, "tzje", "112");
                this.putTable(table, map, "tjje", "113");
                break;
            case 46:
                this.putTable(table, map, "tzje", "114");
                this.putTable(table, map, "tjje", "115");
                break;
            case 47:
                this.putTable(table, map, "tzje", "116");
                this.putTable(table, map, "tjje", "117");
                break;
            case 48:
                this.putTable(table, map, "tzje", "118");
                this.putTable(table, map, "tjje", "119");
                break;
            case 49:
                this.putTable(table, map, "tzje", "120");
                this.putTable(table, map, "tjje", "121");
                break;
            case 50:
                this.putTable(table, map, "zzje", "122");
                this.putTable(table, map, "ssje", "123");
                this.putTable(table, map, "tzje", "124");
                this.putTable(table, map, "tjje", "125");
                break;
            case 51:
                this.putTable(table, map, "tzje", "126");
                this.putTable(table, map, "tjje", "127");
                break;
            case 52:
                this.putTable(table, map, "tzje", "128");
                this.putTable(table, map, "tjje", "129");
                break;
            case 53:
                this.putTable(table, map, "tzje", "130");
                break;
            case 54:
                this.putTable(table, map, "tzje", "131");
                this.putTable(table, map, "tjje", "132");
                break;
            case 55:
                this.putTable(table, map, "tzje", "133");
                this.putTable(table, map, "tjje", "134");
                break;
            default:
                throw new ApplicationException(
                        "错误的行数.");
            }

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
    private HashMap translate2Page(QysdsReportsTableDeclare table) throws
            ApplicationException {
        HashMap backMap = new HashMap();
        List pagelist = new ArrayList();

        for (int i = 1; i <= 55; i++) {

            HashMap map = new HashMap();
            switch (i) {

            case 1:
                this.putMap(table, pagelist, map, i, "*", "*", "1", "2");
                break;
            case 2:
                this.putMap(table, pagelist, map, i, "*", "*", "3", "*");
                break;
            case 3:
                this.putMap(table, pagelist, map, i, "*", "4", "5", "*");
                break;
            case 4:
                this.putMap(table, pagelist, map, i, "6", "7", "8", "*");
                break;
            case 5:
                this.putMap(table, pagelist, map, i, "9", "10", "11", "12");
                break;
            case 6:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "13");
                break;
            case 7:
                this.putMap(table, pagelist, map, i, "*", "*", "14", "15");
                break;
            case 8:
                this.putMap(table, pagelist, map, i, "16", "17", "18", "19");
                break;
            case 9:
                this.putMap(table, pagelist, map, i, "20", "21", "22", "23");
                break;
            case 10:
                this.putMap(table, pagelist, map, i, "*", "*", "24", "25");
                break;
            case 11:
                this.putMap(table, pagelist, map, i, "26", "27", "28", "29");
                break;
            case 12:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "30");
                break;
            case 13:
                this.putMap(table, pagelist, map, i, "*", "*", "31", "*");
                break;
            case 14:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "32");
                break;
            case 15:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "33");
                break;
            case 16:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "34");
                break;
            case 17:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "35");
                break;
            case 18:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "36");
                break;
            case 19:
                this.putMap(table, pagelist, map, i, "37", "38", "39", "40");
                break;
            case 20:
                this.putMap(table, pagelist, map, i, "*", "*", "41", "42");
                break;
            case 21:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "43");
                break;
            case 22:
                this.putMap(table, pagelist, map, i, "44", "45", "46", "47");
                break;
            case 23:
                this.putMap(table, pagelist, map, i, "48", "49", "50", "51");
                break;
            case 24:
                this.putMap(table, pagelist, map, i, "52", "53", "54", "55");
                break;
            case 25:
                this.putMap(table, pagelist, map, i, "56", "57", "58", "59");
                break;
            case 26:
                this.putMap(table, pagelist, map, i, "60", "61", "62", "*");
                break;
            case 27:
                this.putMap(table, pagelist, map, i, "*", "*", "63", "64");
                break;
            case 28:
                this.putMap(table, pagelist, map, i, "65", "66", "67", "*");
                break;
            case 29:
                this.putMap(table, pagelist, map, i, "68", "69", "70", "71");
                break;
            case 30:
                this.putMap(table, pagelist, map, i, "72", "73", "74", "*");
                break;
            case 31:
                this.putMap(table, pagelist, map, i, "75", "*", "76", "*");
                break;
            case 32:
                this.putMap(table, pagelist, map, i, "77", "*", "78", "*");
                break;
            case 33:
                this.putMap(table, pagelist, map, i, "79", "*", "80", "*");
                break;
            case 34:
                this.putMap(table, pagelist, map, i, "81", "82", "83", "84");
                break;
            case 35:
                this.putMap(table, pagelist, map, i, "85", "86", "87", "88");
                break;
            case 36:
                this.putMap(table, pagelist, map, i, "89", "90", "91", "92");
                break;
            case 37:
                this.putMap(table, pagelist, map, i, "93", "*", "94", "*");
                break;
            case 38:
                this.putMap(table, pagelist, map, i, "95", "*", "96", "*");
                break;
            case 39:
                this.putMap(table, pagelist, map, i, "*", "*", "*", "97");
                break;
            case 40:
                this.putMap(table, pagelist, map, i, "98", "99", "100", "101");
                break;
            case 41:
                this.putMap(table, pagelist, map, i, "*", "*", "102", "103");
                break;
            case 42:
                this.putMap(table, pagelist, map, i, "104", "105", "106", "107");
                break;
            case 43:
                this.putMap(table, pagelist, map, i, "*", "*", "108", "109");
                break;
            case 44:
                this.putMap(table, pagelist, map, i, "*", "*", "110", "111");
                break;
            case 45:
                this.putMap(table, pagelist, map, i, "*", "*", "112", "113");
                break;
            case 46:
                this.putMap(table, pagelist, map, i, "*", "*", "114", "115");
                break;
            case 47:
                this.putMap(table, pagelist, map, i, "*", "*", "116", "117");
                break;
            case 48:
                this.putMap(table, pagelist, map, i, "*", "*", "118", "119");
                break;
            case 49:
                this.putMap(table, pagelist, map, i, "*", "*", "120", "121");
                break;
            case 50:
                this.putMap(table, pagelist, map, i, "122", "123", "124", "125");
                break;
            case 51:
                this.putMap(table, pagelist, map, i, "*", "*", "126", "127");
                break;
            case 52:
                this.putMap(table, pagelist, map, i, "*", "*", "128", "129");
                break;
            case 53:
                this.putMap(table, pagelist, map, i, "*", "*", "130", "*");
                break;
            case 54:
                this.putMap(table, pagelist, map, i, "*", "*", "131", "132");
                break;
            case 55:
                this.putMap(table, pagelist, map, i, "*", "*", "133", "134");
                break;
            default:
                throw new ApplicationException(
                        "错误的行数.");
            }

        }

        backMap.put("GD", pagelist);

        return backMap;
    }


    private void putMap(QysdsReportsTableDeclare table, List pagelist,
                        HashMap map,
                        int i,
                        String zzje,
                        String ssje,
                        String tzje,
                        String tjje) {
        map.put("hc", String.valueOf(i));

        if (i == 48 || i == 49) {
            map.put("zzje", "");
            map.put("ssje", "");
            map.put("tzje", "*".equals(tzje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(tzje)).
                    getItemValue());
            map.put("tjje", "*".equals(tjje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(tjje)).
                    getItemValue());

        } else {
            map.put("zzje", "*".equals(zzje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(zzje)).
                    getItemValue());
            map.put("ssje", "*".equals(ssje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(ssje)).
                    getItemValue());
            map.put("tzje", "*".equals(tzje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(tzje)).
                    getItemValue());
            map.put("tjje", "*".equals(tjje) ? "*" :
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(tjje)).
                    getItemValue());
        }
        pagelist.add(map);

    }


    private void putTable(QysdsReportsTableDeclare table, HashMap map,
                          String colKey,
                          String itemID) {

        QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
        item.setItemID(itemID);
        item.setItemValue((String) map.get(colKey));
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

    }

}
