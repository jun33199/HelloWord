package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.gqtzsdmxb.processor;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import java.util.Map;
import com.syax.creports.check.Checker;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.HashMap;
import com.ttsoft.framework.exception.ApplicationException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web.NstzmxzjbForm;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.sql.Connection;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import java.util.Iterator;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.gqtzsdmxb.web.GqtzsdmxbForm;
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
public class GqtzsdmxbProcessor implements Processor {
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

        GqtzsdmxbForm nbForm = (GqtzsdmxbForm) vo.getData();
        Connection conn = null;
        try {
//			获取数据库连接
            conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsUtil2008.setQysdsReport(report, nbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2008_11);
            table.setTableName(CodeConstant.TABLE_NAME_2008_11);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_2008_11);
            int[] arrs = {17, 58};
            List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("GD");
            List listhj = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("HJ");
            List listpczl = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("PC");
            nbForm.setHjList(listhj);
            nbForm.setDataList(listgd);
            nbForm.setSbbczlList(listpczl);

            nbForm.setBz32(((QysdsReportsItemDeclare) table.
                            getCellContentList().get("32")).getItemValue());
            nbForm.setItem58(((QysdsReportsItemDeclare) table.
                              getCellContentList().get("58")).getItemValue());

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

        GqtzsdmxbForm nstzmxzjbForm = (GqtzsdmxbForm) vo.getData();
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
        GqtzsdmxbForm form = (GqtzsdmxbForm) vo.getData();
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
                table.setTableId(CodeConstant.TABLE_ID_2008_11);
                table.setTableName(CodeConstant.TABLE_NAME_2008_11);
                table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
                table = (QysdsReportsTableDeclare) report.getTableContentList().
                        get(CodeConstant.TABLE_ID_2008_11);
                int[] arrs = {17, 58};
                List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("GD");
                List listhj = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("HJ");
                List listpczl = (List)this.translate2Page(QysdsUtil2008.
                        putSpace(
                                table, arrs)).get("PC");
                form.setHjList(listhj);
                form.setDataList(listgd);
                form.setSbbczlList(listpczl);

                form.setBz32(((QysdsReportsItemDeclare) table.
                              getCellContentList().get("32")).getItemValue());
                form.setItem58(((QysdsReportsItemDeclare) table.
                                getCellContentList().get("58")).getItemValue());

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
        GqtzsdmxbForm nstzmxzjbForm = (GqtzsdmxbForm) vo.getData();
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
    private QysdsReportsDeclare translate2Interface(GqtzsdmxbForm nbForm) {
//		企业所得税报表申明对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsUtil2008.setQysdsReport(report, nbForm); //对report 进行一系列的设置
//		企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_2008_11);
        table.setTableName(CodeConstant.TABLE_NAME_2008_11);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        List list = nbForm.getDataList(); //存放固定行的LIST
        List pcList = nbForm.getSbbczlList();

        QysdsReportsItemDeclare itembz = new QysdsReportsItemDeclare();
        itembz.setItemID("32");
        itembz.setItemValue(nbForm.getBz32());
        itembz.setItemType("11");
        table.getCellContentList().put(itembz.getItemID(), itembz);

        QysdsReportsItemDeclare item58 = new QysdsReportsItemDeclare();
        item58.setItemID("58");
        item58.setItemValue(nbForm.getItem58());
        item58.setItemType("11");
        table.getCellContentList().put(item58.getItemID(), item58);

        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);

            /*当前行次*/
//			String hc=(String)map.get("zjhc");
            String hc = Integer.toString(i + 1);
            ((Map) nbForm.getDataList().get(i)).put("hc", hc);
            Iterator it = map.keySet().iterator();

            while (it.hasNext()) {
                /*关键字*/
                String key = (String) it.next();

                /*ITEM ID*/
                String itemId = "";

                if (key.startsWith("C")) {
                    QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                    String col = key.substring(1);
                    /*表右上角抵扣方法ITEM ID 规定为1，直接抵免数据ID从2开始，故列号+1=ITEM ID*/
                    itemId = col;

                    item.setItemID(itemId + "." + hc);
                    item.setItemValue((String) map.get(key));
                    item.setItemType("11");
                    table.getCellContentList().put(item.getItemID(), item);

                }
            }

        }

        //翻译固定补充资料行数据
        for (int i = 0; i < pcList.size(); i++) {
            HashMap map = new HashMap();
            map = (HashMap) pcList.get(i);
            String hc = (String) map.get("hc1");
            int hcnum = Integer.parseInt(hc);
            //设置固定行第一列数据
            QysdsReportsItemDeclare item0 = new QysdsReportsItemDeclare();
            item0.setItemID(String.valueOf(5 * hcnum - 4 + 32));
            item0.setItemValue((String) map.get("P_1"));
            item0.setItemType("11");
            table.getCellContentList().put(item0.getItemID(), item0);

            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID(String.valueOf(5 * hcnum - 3 + 32));
            item.setItemValue((String) map.get("P_2"));
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);

            QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
            item1.setItemID(String.valueOf(5 * hcnum - 2 + 32));
            item1.setItemValue((String) map.get("P_3"));
            item1.setItemType("11");
            table.getCellContentList().put(item1.getItemID(), item1);

            QysdsReportsItemDeclare item2 = new QysdsReportsItemDeclare();
            item2.setItemID(String.valueOf(5 * hcnum - 1 + 32));
            item2.setItemValue((String) map.get("P_4"));
            item2.setItemType("11");
            table.getCellContentList().put(item2.getItemID(), item2);

            QysdsReportsItemDeclare item3 = new QysdsReportsItemDeclare();
            item3.setItemID(String.valueOf(5 * hcnum + 32));
            item3.setItemValue((String) map.get("P_5"));
            item3.setItemType("11");
            table.getCellContentList().put(item3.getItemID(), item3);

        }

        /**
         * 翻译合计行数据
         */
        List hjList  = nbForm.getHjList();
        for (int i = 0; i < hjList.size(); i++) {
            Map map = (Map) hjList.get(i);

            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID((String) map.get("hjhc"));
            item.setItemValue((String) map.get("hjje"));
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);
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
        Map zjMap = new HashMap();
        List pagelist = new ArrayList();
        List hjList = new ArrayList();
        List pczlPageList = new ArrayList();
        Iterator it = table.getCellContentList().keySet().iterator();
        /*求最大行时使用*/
        int curRow = 0;
        int MaxRow = 0;

        while (it.hasNext()) {

            String key = (String) it.next();
            String mainItemID = "";

            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.
                                           getCellContentList().get(key);

            if (key.indexOf(".") > 0) {
                mainItemID = key.substring(0, key.indexOf("."));
                curRow = Integer.parseInt(key.substring(key.indexOf(".") + 1));
            } else {
                mainItemID = key;
            }
            /**
             * ITEM ID 1--->16
             */
            if (Integer.parseInt(mainItemID) >= 1 &&
                Integer.parseInt(mainItemID) <= 16) {
                zjMap.put(key, item.getItemValue());
                MaxRow = curRow > MaxRow ? curRow : MaxRow;
            }
            /**
             * 合计行 ITEM ID 17--->31
             */
            if (Integer.parseInt(mainItemID) >= 17 &&
                Integer.parseInt(mainItemID) <= 31) {
                Map hjMap = new HashMap();
                hjMap.put(key, item.getItemValue());
                hjMap.put("hjhc", item.getItemID());
                hjMap.put("hjje", item.getItemValue());
                hjList.add(hjMap);
            }

        }

        for (int i = 1; i < 6; i++) {

            HashMap map = new HashMap();
            map.put("hc1", String.valueOf(i));
            map.put("P_1",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 4 + 32))).
                    getItemValue());
            map.put("P_2",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 3 + 32))).
                    getItemValue());
            map.put("P_3",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 2 + 32))).
                    getItemValue());
            map.put("P_4",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 1 + 32))).
                    getItemValue());
            map.put("P_5",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i + 32))).
                    getItemValue());
            pczlPageList.add(map);
        }

        for (int i = 1; i <= MaxRow; i++) {
            Map map = new HashMap();
            map.put("hc", Integer.toString(i));
            Iterator iterator = zjMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                int row = Integer.parseInt(key.substring(key.indexOf(".") + 1));
                int id = Integer.parseInt(key.substring(0, key.indexOf(".")));
                if (i == row) {
                    map.put("C" + (id), zjMap.get(key));
                }
            }
            pagelist.add(map);
        }

        backMap.put("GD", pagelist);
        backMap.put("HJ", hjList);
        backMap.put("PC", pczlPageList);
        return backMap;
    }
}
