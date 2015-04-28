package com.ttsoft.bjtax.smsb.jkcx.processor;

import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;

public class JkcxProcessor implements Processor {
    public JkcxProcessor() {
    }

    /**
     *
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException {
        //回传对象
        Object result = null;
        //判断VO是否为空
        if (vo == null) {
            throw new NullPointerException();
        }
        //根据Action的值调用不同的process方法

        switch (vo.getAction()) {
        case CodeConstant.SMSB_QUERYACTION: //查询
            result = this.doQuery(vo);
            break;
        case CodeConstant.SMSB_PRINTACTION: //明细
            result = doDetail(vo);
            break;
        default:
            throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    private Object doQuery(VOPackage vo) throws BaseException {
        Connection conn = null;
        //得到UserData
        UserData ud = vo.getUserData();
        ArrayList resList = new ArrayList();
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Map dataMap = (Map) vo.getData();
            String sklx = (String) dataMap.get("sklx");
            String jsjdm = (String) dataMap.get("jsjdm");
            String swjgzzjgdm = "";
            try {
                SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(jsjdm);
                swjgzzjgdm = dj.getSwjgzzjgdm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                ZRRJBSJ zrrJbsj = InterfaceDj.getZRRJBSJ(jsjdm);
                swjgzzjgdm=zrrJbsj.getSwjgzzjgdm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String yhjb = ud.getYhjb();
            //税务所级
            if (yhjb.equals("30")) {
                if (!swjgzzjgdm.equals(ud.getSsdwdm())) {
                    throw new ApplicationException("该用户不属于当前所在税务所！");
                }
            }
            //分局级
            if (yhjb.equals("40")) {
                if (!swjgzzjgdm.substring(0, 2).equals(ud.getSsdwdm().substring(0, 2))) {
                    throw new ApplicationException("该用户不属于当前所在分局！");
                }
            }
            //设置查询条件
            StringBuffer querySql = new StringBuffer(
                    "SELECT SBBH, SBRQ, SPHM, SKLXDM,JKPZH,SJLY,");
            querySql.append("TO_DATE(DECODE(SUBSTR(ZWBS, 1, 1)||SUBSTR(ZWBS, 20, 1), '00', NULL, '09', NULL, '01', TO_CHAR(JKSJ, 'YYYYMMDD'), '02', TO_CHAR(JKSJ, 'YYYYMMDD'), TO_CHAR(ZYRQ, 'YYYYMMDD')), 'YYYYMMDD') AS JKRQ,");
            querySql.append("TO_DATE(DECODE(SUBSTR(ZWBS, 1, 1), '0', NULL, TO_CHAR(ZYRQ, 'YYYYMMDD')), 'YYYYMMDD') AS ZYRQ,");
            querySql.append("SUM(SJJE) AS SJJEHJ, SUM(RKJE) ");
            querySql.append("FROM SBDB.SB_JL_SBJKZB ");
            querySql.append("WHERE JSJDM = '" + jsjdm + "'");
            if (!sklx.equals("0")) {
                querySql.append(" AND SKLXDM LIKE '" + sklx + "%'");
            }
            querySql.append(" AND TO_CHAR(SBRQ,'YYYYMM') = '" +
                            dataMap.get("sbrq") + "' ");
            querySql.append("GROUP BY SBBH, SBRQ, SPHM, SKLXDM,JKPZH,SJLY,");
            querySql.append("TO_DATE(DECODE(SUBSTR(ZWBS, 1, 1)||SUBSTR(ZWBS, 20, 1), '00', NULL, '09', NULL, '01', TO_CHAR(JKSJ, 'YYYYMMDD'), '02', TO_CHAR(JKSJ, 'YYYYMMDD'), TO_CHAR(ZYRQ, 'YYYYMMDD')), 'YYYYMMDD'),");
            querySql.append("TO_DATE(DECODE(SUBSTR(ZWBS, 1, 1), '0', NULL, TO_CHAR(ZYRQ, 'YYYYMMDD')), 'YYYYMMDD')");
            System.out.println("jkcx QuerySql=====" + querySql);
            //查询
            ResultSet rs = da.querySQL(querySql.toString());
            while (rs.next()) {
                Sbjkzb zb = new Sbjkzb();
                //申报序号、申报日期、税票号、税款类型、实缴金额、缴款日期、入库日期
                zb.setSbbh(rs.getString("SBBH"));
                zb.setSbrq(rs.getTimestamp("SBRQ"));
                zb.setSphm(rs.getString("SPHM"));
                zb.setSklxdm(rs.getString("SKLXDM"));
                zb.setSjje(rs.getBigDecimal("SJJEHJ"));
                zb.setJksj(rs.getTimestamp("JKRQ"));
                zb.setZyrq(rs.getTimestamp("ZYRQ"));
                zb.setJkpzh(rs.getString("JKPZH"));
                zb.setSjly(rs.getString("SJLY"));
                resList.add(zb);
            }
            return resList;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
    }

    private Object doDetail(VOPackage vo) throws BaseException {
        Connection conn = null;
        HashMap resMap = new HashMap();
        List mxList = new ArrayList();
        List zbList = new ArrayList();
        Sbjkzb zb = new Sbjkzb();
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Map dataMap = (Map) vo.getData();
            String sbbh = (String)dataMap.get("sbbh");
            Vector ve = new Vector();
            ve.addElement("SBBH='"+sbbh+"'");
            zbList = da.query(Sbjkzb.class,ve);
            zb = (Sbjkzb)zbList.get(0);
            for(int i=0;i<zbList.size();i++)
            {
                Sbjkzb tmpZb = (Sbjkzb)zbList.get(i);
                Vector veMx = new Vector();
                veMx.addElement("JKPZH='"+tmpZb.getJkpzh()+"'");
                mxList.addAll(da.query(Sbjkmx.class,veMx));
            }
            resMap.put("sbjkzb",zb);
            resMap.put("mxList",mxList);
        }catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return resMap;
    }
}
