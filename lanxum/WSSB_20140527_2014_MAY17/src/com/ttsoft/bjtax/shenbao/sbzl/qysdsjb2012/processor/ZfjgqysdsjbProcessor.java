package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.processor;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 汇总纳税分支机构分配表Processor</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 *
 * <p>Company: 清华同方软件股份有限公司</p>
 *
 * @author not attributable
 * @version 1.1
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import java.util.Map;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.ZfjgqysdsjbBO;
import java.sql.Timestamp;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import java.util.HashMap;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.Constant;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.framework.exception.ApplicationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.QysdsUtil2012;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.ttsoft.common.model.UserData;
import java.sql.CallableStatement;
import com.ekernel.db.or.ORManager;
import com.syax.common.util.CAcodeConstants;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.ZfjgqysdsjbVO;
import com.syax.creports.bo.qysds.Jbxx;
import com.ttsoft.framework.util.DateTimeUtil;
import java.sql.Date;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import java.sql.*;
import java.math.BigDecimal;
import com.syax.creports.util.Arith;

public class ZfjgqysdsjbProcessor implements Processor
{
    //初始化工具类
    private QysdsUtil2012 sdsUtil = new QysdsUtil2012();

    public ZfjgqysdsjbProcessor()
    {
    }

    /**
     * 根据业务操作类型值来做业务操作
     *
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // 根据业务操作类型值来做业务操作
        try {
            switch(vo.getAction()) {
                // 查询
                case QysdsksActionConstant.INT_ACTION_QUERY: {
                    return doQuery( (Map)vo.getData());
                }
                // 保存
                case QysdsksActionConstant.INT_ACTION_SAVE: {
                    return doSave(vo);
                }
                // 删除
                case QysdsksActionConstant.INT_ACTION_DELETE: {
                    doDelete(vo);
                    return null;
                }
                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 保存企业所得税数据
     *
     * @param data
     *            Map
     * @throws BaseException
     */
    private Map doSave(VOPackage vop) throws BaseException
    {
//        System.out.println("zfjg  pro   doSave");
        DzyjHelper dh = new DzyjHelper();
//        System.out.println("111");
        Map retData = new HashMap();
        Map data = (Map)vop.getData();
        UserData ud = vop.getUserData();
        Connection conn = null;

        //ZfjgqysdsjbBO
        ZfjgqysdsjbVO qysdsjbvo = (ZfjgqysdsjbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        //ZfjgqysdsjbBO
        ZfjgqysdsjbBO qysdsjbbo = (ZfjgqysdsjbBO)data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
        // 报表类型
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // 季度类型
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // 税务登记基本数据值对象
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsjbbo, tableID, jdlx, djjbsj);
//            System.out.println("==============================");
//            System.out.println("bbqlx = " + report.getBbqlx());
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get("28");
//            System.out.println("table id = " + table.getTableId() + "\n table name = " + table.getTableName()
//                 + "\ntbblx = " + table.getTbblx());
            // 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
			DBResource.destroyConnection(conn);
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }

        if(ud.getCaflag()) {
            System.out.println("===========签名开始==========");
            try {
                String ywid = qysdsjbvo.getZjgxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========签名结束==========");
            }
            catch(Exception ex) {
                System.out.println("===========签名失败==========");
                throw ExceptionUtil.getBaseException(ex);

            }

            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }

        return retData;
    }

    /**
     * 查询企业所得税汇总纳税分支机构分配表信息
     *
     * @param pData
     *            Map
     * @return Map
     * @throws BaseException
     */
    private Object doQuery(Map pData) throws BaseException
    {
        // 数据库连接对象
        Connection conn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            ZfjgqysdsjbBO zfjgbo = new ZfjgqysdsjbBO();
            // 计算机代码
            String jsjdm = null;
            // 当前日期
            Timestamp curDate = null;
            // 税务登记基本数据值对象
            SWDJJBSJ djjbsj = (SWDJJBSJ)pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
            zfjgbo.setJsjdm(djjbsj.getJsjdm());
            zfjgbo.setNsrmc(djjbsj.getNsrmc());
            zfjgbo.setNsrsbh(djjbsj.getSwdjzh());

            // 取得计算机代码
            jsjdm = (String)pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
            // 取得日期参数
            curDate = (Timestamp)pData.get(QysdsksMapConstant.STRING_KEY_DATE);
            zfjgbo.setSbrq(curDate);
            zfjgbo.setSbrqshow(sdf.format(curDate));
            // 取得所在的季度
            String jd = Skssrq.preQuarter(curDate);
            zfjgbo.setJd(jd);
            // 报表类型 - 表ID
            String bblx = (String)pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
            // 季度类型 - 期号
            String jdlx = (String)pData.get(QysdsksMapConstant.STRING_KEY_JDLX);
            zfjgbo.setJd(jdlx); //期号-季报期号即为季度数
            System.out.println("jsjdm = " + jsjdm + "\nbblx = " + bblx + "\nqh = " + jdlx);

            // 取得税款所属日期Map
            Map skssrq = new HashMap();
            if(bblx.equals(Constant.TABLE_ID_ZFJGSDS_2008)) {
                skssrq = Skssrq.otherSkssrq(curDate);
            }
            else {
                throw new ApplicationException("传递的报表ID错误！");
            }
            // 取得税款所属开始和结束日期
            Timestamp skssksrq = (Timestamp)skssrq.get(Skssrq.SKSSKSRQ);
            Timestamp skssjsrq = (Timestamp)skssrq.get(Skssrq.SKSSJSRQ);
            zfjgbo.setSkssksrq(skssksrq);
            zfjgbo.setSkssjsrq(skssjsrq);

            // 取得年度
            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            zfjgbo.setNd(nd);
            System.out.println("nd = " + nd);

            //报表期类型
            zfjgbo.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

            //获取查帐征收汇总纳税方法，以判断是否为可填写本表用户
            int result = this.checkCzzsNsff(conn, zfjgbo);
            switch(result) {
                case Constant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException(Constant.CHECK_HZNSFF_MESSAGE_NO_DATA);
                    //break;
                case Constant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(Constant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
                //case Constant.CHECK_HZNSFF_TYPE_HZNS_FZJG:
                    //throw new ApplicationException(Constant.CHECK_HZNSFF_MESSAGE_FZJG);
                    //break;
            }

            //查询查帐征收表分摊税额错误
            this.getCzzsFtse(conn, zfjgbo);

            // 创建QysdsReportsDeclare对象
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            // 设置QysdsReportsTableDeclare的基本信息
            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();

            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //version
            qrd.setVersion(Constant.QYSDSJB_VERSION_2012);
            qrd.setNsrjsjdm(jsjdm); //计算机代码
            qrd.setSknd(nd); //税款年度
            // 如果不是汇总纳税分支机构表，则抛异常
            if(bblx.equals(Constant.TABLE_ID_ZFJGSDS_2008)) {
                //报表期类型
                qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
                //期号
                qrd.setQh(jdlx);

                //报表ID
                qrtd.setTableId(bblx);
                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            }
            else {
                throw new ApplicationException("传递的报表ID错误！");
            }
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            // 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);

            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

            // 调用查询方法进行查询
            qrd = (QysdsReportsDeclare)iApp.querySingleTable(qrd);

            // 获取查询到的具体数据
            qrtd = (QysdsReportsTableDeclare)qrd.getTableContentList().get(bblx);

            //根据查询到的分摊税额构建对应的QysdsReportsItemDeclare
            /** 看不明白有什么用，所有数据都将在translate2Page中进行处理。暂时屏蔽，guoxh,2012-3-6
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID("6");
            item.setItemType("11");
            item.setItemValue(zfjgbo.getFtse());
            qrtd.getCellContentList().put(item.getItemID(), item);
            **/

            // 将数据库中的数据翻译成页面所需数据格式
            if(qrtd == null) {
                System.out.println("======no value======");
            }
            else {
                //获取分支机构明细信息的行数
                int rows = this.getMxDateMaxIndex(conn, qrd, pData);

                //获取保存的数据Map,以id, value 形式保存
                HashMap map = (HashMap)this.translate2Page(qrtd, rows);

                zfjgbo.setSbsj(map);
            }
            return zfjgbo;
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询企业所得税汇总纳税分支机构季报数据失败");
        }
        finally {
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 删除申报数据
     *
     * @param data
     *            Map
     * @throws BaseException
     */
    private void doDelete(VOPackage vop) throws BaseException
    {
        DzyjHelper dh = new DzyjHelper();
        Map retData = new HashMap();
        Map data = (Map)vop.getData();
        UserData ud = vop.getUserData();
        Connection conn = null;

        PreparedStatement pstmt = null;

        // 构造删除条件
        StringBuffer sqlBuffer = new StringBuffer();

        String sql = "";
        //ZfjgqysdsjbVO
        ZfjgqysdsjbVO qysdsjbvo = (ZfjgqysdsjbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        //ZfjgqysdsjbBO
        ZfjgqysdsjbBO qysdsjbbo = (ZfjgqysdsjbBO)data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        // 报表类型
        String bblx = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // 季度类型
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
        // 税务登记基本数据值对象
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.STRING_KEY_JBSJ);

        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //版本
            qrd.setVersion(Constant.QYSDSJB_VERSION_2012);
            qrd.setNsrjsjdm(qysdsjbvo.getZjgxx().getJsjdm()); //计算机代码
            qrd.setSknd(qysdsjbvo.getSbxx().getNd()); //税款年度
            qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR); //季报
            qrd.setQh(jdlx); //期号

            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
            qrtd.setTableId(bblx); //报表ID
            qrtd.setTableName(Constant.TABLE_NAME_ZFJGSDS_2008); //报表名称
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            //填报表类型，和报表期类型一样
//            if(bblx.equals(Constant.CZNB)) {
//                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
//            }
//            else {
            qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
//            }

            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);

            // 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(qrd);
			DBResource.destroyConnection(conn);
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }

        if(ud.getCaflag()) {

            System.out.println("===========签名开始==========");
            try {
                String ywid = qysdsjbvo.getZjgxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========签名结束==========");
            }
            catch(Exception ex) {
                System.out.println("===========签名失败==========");
                throw ExceptionUtil.getBaseException(ex);

            }
            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
    }

    /**
     * 将数据转换成页面展示的形式
     * @param table QysdsReportsTableDeclare
     * @param maxIndex int
     * @return Map
     */
    private Map translate2Page(QysdsReportsTableDeclare table, int maxIndex)
    {
        HashMap dataMap = new HashMap();
        // 创建List对象，用来存放页面表单数据
//        ArrayList keyList = sdsUtil.arrangeMapKey( (HashMap)table.getCellContentList());
//        System.out.println("list size = " + keyList.size());
        Map data = table.getCellContentList();
        System.out.println("Map size = " + data.size());
        java.util.Iterator it = data.keySet().iterator();
//        for(int i = 0; i < keyList.size(); i++) {
        while(it.hasNext())
        {
//            String key = (String)keyList.get(i);
            String key = (String) it.next();
            System.out.println("key = " + key);
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare)table.getCellContentList().get(key);
            if(key.indexOf(".") > 0) {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17) {
                    String value = item.getItemValue();
                    System.out.println("value = " + value);
                    System.out.println("change = " + Double.parseDouble(value) * 100);
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
            System.out.println("---------");
            dataMap.put(item.getItemID(), item.getItemValue());
        }
		System.out.println("==========processor========");
		System.out.println(dataMap.toString());
//        }

        //如果保存的数据不足默认行数,补齐差余行数
        if(maxIndex < Constant.ZFJGSDSJB_2008_DEFAULT_MX_ROW_NUMBER) {
            int statrIndex = maxIndex + 1;
            int endIndex = Constant.ZFJGSDSJB_2008_DEFAULT_MX_ROW_NUMBER + 1;

            for(int k = statrIndex; k < endIndex; k++) {
                for(int j = 12; j < 19; j++) {
                    //
                    String id = String.valueOf(j) + "." + String.valueOf(k);
//                    System.out.println("add id = " + id);
                    switch(j) {
                        case 12:
                            //分支机构纳税人识别号
                            dataMap.put(id, "");
                            break;
                        case 13:
                            //分支机构名称
                            dataMap.put(id, "");
                            break;
                        case 14:
                            //分支机构收入总额
                            dataMap.put(id, "0.00");
                            break;
                        case 15:
                            //分支机构工资总额
                            dataMap.put(id, "0.00");
                            break;
                        case 16:
                            //分支机构资产总额
                            dataMap.put(id, "0.00");
                            break;
                        case 17:
                            //分支机构分配比例
                            dataMap.put(id, "0");
                            break;
                        case 18:
                            //分支机构分配税额
                            dataMap.put(id, "0.00");
                            break;
                    }
                }
            }
            //设置最大行数
            dataMap.put(Constant.ZFJGSDSJB_2008_MAX_ROW, String.valueOf(Constant.ZFJGSDSJB_2008_DEFAULT_MX_ROW_NUMBER));
        }
        else {
            //设置最大行数
            dataMap.put(Constant.ZFJGSDSJB_2008_MAX_ROW, String.valueOf(maxIndex));
        }

        // 返回Map对象
        return dataMap;
    }

    /**
     * 数据翻译接口
     *
     * @param qysdsbo
     *            企业所得税BO对象
     * @param tableID
     *            报表ID
     * @param jdlx
     *            季度类型
     * @param djjbsj
     *            登记数据对象
     * @return
     */
    private QysdsReportsDeclare ConvertBoToReportsDeclare(ZfjgqysdsjbBO qysdsbo,
        String tableID, String jdlx, SWDJJBSJ djjbsj)
    {
        QysdsReportsDeclare report = new QysdsReportsDeclare();

        // 基本信息
        Jbxx jbxx = new Jbxx();
        
        
        // 基本信息(JBXX)-计算机代码
        //jbxx.setJsjdm(qysdsbo.getJsjdm());
        // 基本信息(JBXX)-纳税人名称
        //jbxx.setNsrmc(qysdsbo.getNsrmc());
        
        // 基本信息(JBXX)-计算机代码
        jbxx.setJsjdm(djjbsj.getJsjdm());
        // 基本信息(JBXX)-纳税人名称
        jbxx.setNsrmc(djjbsj.getNsrmc());
        
        // 基本信息(JBXX)-联系电话
        jbxx.setLxdh(djjbsj.getJydzlxdm());
        // 基本信息(JBXX)-所属行业
        jbxx.setSshy(djjbsj.getGjbzhydm());
        // 基本信息(JBXX)-征收方式
        jbxx.setZsfs("");

        // 向报表中添加纳税人基本信息
        report.setJbxx(jbxx);

        /**
         * 应用编号
         */
        report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
        /**
         * 版本号
         */
        //report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
        report.setVersion(Constant.QYSDSJB_VERSION_2012);
        /**
         * 纳税人计算机代码
         */
        //report.setNsrjsjdm(qysdsbo.getJsjdm());
        report.setNsrjsjdm(djjbsj.getJsjdm());
        /**
         * 纳税人名称
         */
        //report.setNsrmc(qysdsbo.getNsrmc());
        report.setNsrmc(djjbsj.getNsrmc());
        /**
         * 报表期类型
         */
        // 如果是年报数据则固定设置期号为1；如果是季报数据则根据情况设置期号为1、2、3、4
//        if(tableID.equals(Constant.TABLE_ID_ZFJGSDS_2008)) {
        //季报
        report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        /**期号*/
        report.setQh(jdlx);
//        }
        // 取得税款所属日期Map
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        Map skssrq = new HashMap();
//        if(bblx.equals(Constant.CZNB)) {
//            skssrq = Skssrq.yearSkssrq(curDate);
//        }
//        else {
        skssrq = Skssrq.otherSkssrq(curDate);
//        }
        String sbrq = "";
        try {
            /**税款所属期*/
            //skssksrq
            String skssksrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
            //skssjsrq
            String skssjsrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
            // 申报日期
            sbrq = DateTimeUtil.timestampToString(curDate);
            report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        }
        catch(Exception e) {
            System.out.println("转换申报期时出错！");
        }
        // 税款年度
        report.setSknd( (String)skssrq.get(Skssrq.SKSSRQ_ND));
        /**
         * 税务机关组织机构代码
         */
        report.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
        /**
         * 区县代码
         */
        report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));
        /**
         * 录入人
         */
        report.setLrr(qysdsbo.getJsjdm());
        /**
         * 录入日期
         */
        report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        /**
         * 创建人
         */
        report.setCjr(qysdsbo.getJsjdm());
        /**
         * 创建日期
         */
        report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

        // 企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(tableID);
        table.setTableName(Constant.TABLE_NAME_ZFJGSDS_2008);
        /**
         * 填报表类型，和报表期类型一样
         *
         */
//        if(bblx.equals(Constant.CZNB)) {
//            table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
//        }
//        else {
        table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
//        }

//        Map sbsjMap = ConvertSbsjBoToMap(qysdsbo);

        Map sbsj = qysdsbo.getSbsj();

        Iterator it = sbsj.keySet().iterator();
        while(it.hasNext()) {
            String id = (String)it.next();
            String value = (String) sbsj.get(id);
//            System.out.println("id = " + id);
//            System.out.println("value = " + value);
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID(id);
            //设置分配比例为百分数形式
            if(id.indexOf(".") > 0)
            {
                String head = id.substring(0, id.indexOf("."));
                //分支机机构分配比例
                if(Integer.parseInt(head) == 17)
                {
//                    System.out.println("id = " + id + "\nvalue = " + value);
                	if(!value.equals("0")){
                		value = value.substring(0, (value.length() - 1));
                	}
//                    System.out.println("str value = " + value);
//                    System.out.println("change value = " + Arith.round(Double.parseDouble(value)/100, 4));
                    value = String.valueOf(Arith.round(Double.parseDouble(value)/100, 4));
                }
            }
            item.setItemValue(value);
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);
        }
//        System.out.println("tid = " + table.getTableId() + "\ntable.getCellContentList().size = " + table.getCellContentList().size());
        report.getTableContentList().put(table.getTableId(), table);
//        System.out.println("report.getTableContentList() size = " + report.getTableContentList().size());

        return report;
    }

    /**
     * 查询明细数据最大index
     *    根据填报人JSJDM，填报表ID获取对应明细数据的最大index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     * @author gaoyh
     * @modify_date 2012-03-15
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, Map pData) throws BaseException
    {
        int maxIndex = 0;
        HashMap hpData = (HashMap)pData;
        //获取QysdsReportsTableDeclare对象
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get(Constant.
            TABLE_ID_ZFJGSDS_2008);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 当前日期
        Timestamp curDate = null;
        // 取得日期参数
        curDate = (Timestamp)hpData.get(QysdsksMapConstant.STRING_KEY_DATE);
        // 取得税款所属日期Map
		Map skssrq = new HashMap();	
		skssrq = Skssrq.otherSkssrq(curDate);
		// 取得年度
		String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);

        //sql语句
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("'");
        sql.append("and bbqlx = '").append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
        sql.append("and qh = '").append((String)hpData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
        sql.append("and sknd = '").append(nd).append("' ");

        System.out.println("sql:\n" + sql.toString());

        try {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            throw new ApplicationException("查询明细数据最大index失败！");
        }

        return maxIndex;
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
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get(Constant.
            TABLE_ID_ZFJGSDS_2008);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //sql语句
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("'");

        try {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            throw new ApplicationException("查询明细数据最大index失败！");
        }

        return maxIndex;
    }

    /**
     * 判断当前纳税人查帐征收申报方式
     * @param conn Connection
     * @param bo ZfjgqysdsjbBO
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgqysdsjbBO bo) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        //汇总纳税-总机构
        int resultType = Constant.CHECK_HZNSFF_TYPE_HZNS_ZJG;

        HashMap result = new HashMap();
        try {
            sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(bo.getBbqlx()).append("' ");
            sql.append("and qh = '").append(bo.getJd()).append("' ");
            sql.append("and sknd = '").append(bo.getNd()).append("' ");
            sql.append("and sbdm = '").append(Constant.TABLE_ID_CZZSSDS_2008).append("' ");
            sql.append("and to_number(hc) < 3 order by to_number(hc) ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            while(rs.next()) {
                result.put(rs.getString("hc"), rs.getString("yz"));
            }
            System.out.println("result.size() = " + result.size());
            if(result.size() == 0) {
                //没有数据
                resultType = Constant.CHECK_HZNSFF_TYPE_NO_DATA;
            }
            else {
                String hzff = (String)result.get("1");
                System.out.println("hzff = " + hzff);
                if(hzff.equals(Constant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS)) {
                    String hzfs = (String)result.get("2") == null ? "" : (String)result.get("2");
                    System.out.println("hzfs = " + hzfs);
                    if(hzfs.equals(Constant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG)) {
                        //汇总纳税-分支机构
                        resultType = Constant.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                    }
                }
                else if(hzff.equals(Constant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS)) {
                    //独立纳税
                    resultType = Constant.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("查询查帐征收表纳税方法错误！");
        }
        return resultType;
    }
    
    /**
     * 查询查帐征收表分摊税额错误
     * @param conn Connection
     * @param bo ZfjgqysdsjbBO
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgqysdsjbBO bo) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(bo.getBbqlx()).append("' ");
            sql.append("and qh = '").append(bo.getJd()).append("' ");
            sql.append("and sknd = '").append(bo.getNd()).append("' ");
            sql.append("and sbdm = '").append(Constant.TABLE_ID_CZZSSDS_2008).append("' ");
            sql.append("and hc = '26' ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ftse = "0.00";
            while(rs.next())
            {
                ftse = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            }
            bo.setFtse(ftse);

            //关闭数据库对象
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("查询查帐征收表分摊税额错误！");
        }
    }
}
