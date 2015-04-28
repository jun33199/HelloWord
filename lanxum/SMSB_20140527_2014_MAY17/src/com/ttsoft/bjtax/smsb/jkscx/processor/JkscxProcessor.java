package com.ttsoft.bjtax.smsb.jkscx.processor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.jkscx.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.bjtax.smsb.util.JspUtil;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;

public class JkscxProcessor implements Processor {
    protected int sessionID = 0;

    static final String DATE_FORMAT = "yyyyMMdd";
    static final String SBJKMX_VIEW = "SELECT"
    +
    " A.SKSSKSRQ,A.SBRQ,A.SKSSJSRQ,A.SJJE,A.ZWBS,A.JSJDM,A.JKPZH,A.SPHM," //,A.SZDMB.SZSMDM,B.RKJE AS SMRKJE, B.SL,
    +
    " C.SKLXMC,D.SBMC,E.LSGXMC,F.YHMC AS ZHMC,G.DJZCLXMC ,H.SWJGZZJGMC,"
    +
    "I.SWJGZZJGMC AS ZSSWJGZZJGMC, J.GJBZHYMC,K.SKGK,L.YSKMMC,M.YSJCMC,N.SZSMMC SZMC,O.NSRMC,"
    +
    "  B.KSSL, B.JSJE, B.SJSE,  P.SZSMMC,row_number() over ( order by A.JKPZH desc ) rn "
    +
    " FROM SBDB.SB_JL_SBJKZB A,SBDB.SB_JL_SBJKMX B , DMDB.KJ_DM_SKLX C,"
    +
    " DMDB.GY_DM_FS D, DMDB.DJ_DM_LSGX E, DMDB.GY_DM_YH F, DMDB.DJ_DM_DJZCLX G,"
    +
    " DMDB.GY_DM_SWJGZZJG H, DMDB.GY_DM_SWJGZZJG I, DMDB.GY_DM_GJBZHY J,"
    +
    " (select gkjhh,max(skgk) SKGK from DMDB.GY_DM_SWJGZZJG where trim(gkjhh) is not null group by gkjhh) K,"
    +
    " DMDB.KJ_DM_YSKM L, DMDB.SF_DM_YSJC M, DMDB.SB_DM_SZSM N, DJDB.DJ_JL_JBSJ O, DMDB.SB_DM_SZSM P"
    +
    " WHERE B.JKPZH = A.JKPZH AND B.JSJDM = A.JSJDM AND B.SZSMDM = P.SZSMDM(+)"
    +
    " AND A.SKLXDM = C.SKLXDM(+) AND A.FSDM = D.SBDM(+) AND A.LSGXDM = E.LSGXDM(+)"
    +
    " AND A.YHDM = F.YHDM(+) AND A.DJZCLXDM = G.DJZCLXDM(+) AND A.SWJGZZJGDM = H.SWJGZZJGDM(+)"
    +
    " AND A.ZSSWJGZZJGDM = I.SWJGZZJGDM(+) AND A.GJBZHYDM = J.GJBZHYDM(+)"
    +
    " AND A.GKZZJGDM = K.GKJHH(+) AND A.YSKMDM = L.YSKMDM(+) AND A.ND =  L.ND(+)"
    +
    " AND A.YSJCDM = M.YSJCDM(+) AND A.SZDM = N.SZSMDM(+) AND A.JSJDM = O.JSJDM(+)";


    /**
     * Processor Dispacher
     * @param vo Value Object
     * @return PageForm
     * @throws BaseException Excetion throwable
     */
    public Object process(VOPackage vo) throws BaseException {
        switch (vo.getAction()) {
        case CodeConstant.SMSB_QUERYACTION: //
            return doQuery(vo);
        case CodeConstant.SMSB_TOEXCELACTION:
            return doExport(vo);
        default:
            throw new ApplicationException("未找到符合条件的操作");
        }
    }

    /**
     * doQuery
     * @param vo Value Object
     * @return PageForm
     * @exception BaseException BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        SfDBAccess da = null;
        Connection conn = null;
        Statement st = null; //Prepared
        List list = new ArrayList();
        Map mxMap = new HashMap();
        Map result = new HashMap();
        //3.开始业务
        try {
            ///3.1.初始化工具
            JkscxForm pf = (JkscxForm) vo.getData();
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ResultSet rs = null;
            JksInfo o = null;
            String sqlwhereZB = getQueryWhereZB(pf);
            String sqlwhereMX = getQueryWhereMX(pf);
            StringBuffer sql = new StringBuffer();
            int startIndex = getStartIndex(pf.getNextPage(), pf.getTotalpage());
            int endIndex = getEndIndex(pf.getNextPage(), pf.getTotalpage());
            int pageSize = Integer.parseInt(pf.getPageSize());
            if (pf.getTotalpage() == null || pf.getTotalpage().equals("0")) {
                sql.append(
                        "SELECT count(*) totalcount from  SBDB.SB_JL_SBJKMX B where B.jkpzh in");
                sql.append(
                        " (select A.jkpzh from SBDB.SB_JL_SBJKZB A where 1=1 ");
                sql.append(sqlwhereZB + " ) ");
                sql.append(sqlwhereMX);
                System.out.println("COUNT ＳＱＬ === " + sql.toString());
                rs = da.querySQL(sql.toString());
                rs.next();
                int totalCount = rs.getInt("totalcount");
                if (startIndex > totalCount) startIndex = totalCount;
                result.put("TOTALCOUNT", new Integer(totalCount));
                System.out.println("TOTALCOUNT === " + totalCount);
                rs.close();
            } else {
            }

            //3.执行查询
            sql = new StringBuffer();
            sql.append("select * from (");
            sql.append(SBJKMX_VIEW);
            sql.append(sqlwhereZB);
            sql.append(sqlwhereMX);
            sql.append(") where rn>" + startIndex);
            sql.append(" AND rn<=" + endIndex);
            //sql.append(" order by A.JKPZH desc ");
            System.out.println("JKSCX ＳＱＬ === " + sql.toString());
            st = conn.createStatement();
            //确定你的jdbc驱动程序支持jdbc2.0
//          st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );

            rs = st.executeQuery(sql.toString());
            //4.整理数据

            int count = 1;

            //for ( int m = 0; m <startIndex ; m++ ) rs.next();
            //     if (startIndex >0) rs.absolute(startIndex);

            for (count = 1; count <= pageSize && rs.next(); count++) {
                o = new JksInfo();
                o.setIndex(startIndex + count);
                o.setJkpzh(rs.getString("jkpzh"));
                o.setJsjdm(rs.getString("jsjdm"));
                o.setSphm(rs.getString("sphm"));
                o.setJsje(JspUtil.format(rs.getBigDecimal("jsje")));
                o.setSjje(JspUtil.format(rs.getBigDecimal("sjse")));
                o.setKssl(JspUtil.format(rs.getBigDecimal("kssl")));
                o.setZbsjje(JspUtil.format(rs.getBigDecimal("sjje")));
                o.setSkssksrq(JspUtil.format(rs.getTimestamp("SKSSKSRQ")));
                o.setSkssjsrq(JspUtil.format(rs.getTimestamp("SKSSJSRQ")));
                o.setSbrq(JspUtil.format(rs.getTimestamp("SBRQ")));
                o.setDjzclx(rs.getString("DJZCLXMC"));
                o.setGjbzhy(rs.getString("GJBZHYMC"));
                o.setNsrmc(rs.getString("NSRMC"));
                String zwbs = rs.getString("ZWBS");
                if (zwbs.charAt(0) == '0') {
                    o.setRkbs("未入库");
                } else {
                    o.setRkbs("入库");
                }
                o.setSbfs(rs.getString("SBMC"));
                o.setSklx(rs.getString("SKLXMC"));
                o.setSzmc(rs.getString("SZMC"));
                o.setSzsmmc(rs.getString("SZSMMC"));
                o.setYh(rs.getString("ZHMC"));
                o.setYsjcmc(rs.getString("YSJCMC"));
                o.setYskmmc(rs.getString("YSKMMC"));
                o.setZgswjg(rs.getString("SWJGZZJGMC"));
                o.setZsswjg(rs.getString("ZSSWJGZZJGMC"));

                list.add(o);
                // 调用网上申报接口查询一票多税数据
                mxMap.put(o.getSphm(),ServiceProxy.getYpdsJksBySphm(o.getSphm()));
            }

            rs.close();
            st.close();
            result.put("DATALIST", list);
            result.put("MXDATAMAP",mxMap);

        } catch (Exception e) {
            e.printStackTrace();

            throw ExceptionUtil.getBaseException(e);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return result;
    }

    /**
     * 获取当前页开始index
     * @param nextPage 下一页
     * @param countTotal 总页数
     * @return 开始index
     */
    private int getStartIndex(String nextPage, String countTotal) {
        //1.句柄申明
        int iNextPage = Integer.parseInt(nextPage);
        int iCountTotal = Integer.parseInt(countTotal);
        int start = -1;
        //2.开始业务
        start = (iNextPage - 1) * CodeConstant.JKS_PG_LENGTH;
        //99.返回值
        return start;
    }

    /**
     * 获取当前页结束index
     * @param nextPage 下一页
     * @param countTotal 总页数
     * @return 结束index
     */
    private int getEndIndex(String nextPage, String countTotal) {
        //1.句柄申明
        int iNextPage = Integer.parseInt(nextPage);
        int iCountTotal = Integer.parseInt(countTotal);
        int end = -1;
        //2.开始业务
        end = iNextPage * CodeConstant.JKS_PG_LENGTH;
        //99.返回值
        return end;
    }


    /**
     * doExport
     * @param vo Value Object
     * @return PageForm
     * @exception BaseException BaseException
     */
    private Object doExport(VOPackage vo) throws BaseException {
        SfDBAccess da = null;
        Connection conn = null;
        List list = new ArrayList();
        //3.开始业务
        try {
            ///3.1.初始化工具

            JkscxForm pf = (JkscxForm) vo.getData();
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);

            ResultSet rs = null;
            JksInfo o = null;
            StringBuffer sql = new StringBuffer();
            sql.append(SBJKMX_VIEW);
            sql.append(getQueryWhereZB(pf));
            sql.append(getQueryWhereMX(pf));
            sql.append(" and rownum<= 20000");
            sql.append(" order by A.JKPZH desc ");

            //3.执行查询
            rs = da.querySQL(sql.toString());

            for (int count = 1; count <= 10000 && rs.next(); count++) {

                o = new JksInfo();
                o.setIndex(count);
                o.setJkpzh(rs.getString("jkpzh"));
                o.setJsjdm(rs.getString("jsjdm"));
                o.setSphm(rs.getString("sphm"));
                o.setJsje(JspUtil.format(rs.getBigDecimal("jsje")));
                o.setSjje(JspUtil.format(rs.getBigDecimal("sjse")));
                o.setKssl(JspUtil.format(rs.getBigDecimal("kssl")));
                o.setZbsjje(JspUtil.format(rs.getBigDecimal("sjje")));
                o.setSkssksrq(JspUtil.format(rs.getTimestamp("SKSSKSRQ")));
                o.setSkssjsrq(JspUtil.format(rs.getTimestamp("SKSSJSRQ")));
                o.setSbrq(JspUtil.format(rs.getTimestamp("SBRQ")));
                o.setDjzclx(rs.getString("DJZCLXMC"));
                o.setGjbzhy(rs.getString("GJBZHYMC"));
                o.setNsrmc(rs.getString("NSRMC"));
                String zwbs = rs.getString("ZWBS");
                if (zwbs.charAt(0) == '0') {
                    o.setRkbs("未入库");
                } else {
                    o.setRkbs("入库");
                }
                o.setSbfs(rs.getString("SBMC"));
                o.setSklx(rs.getString("SKLXMC"));
                o.setSzmc(rs.getString("SZMC"));
                o.setSzsmmc(rs.getString("SZSMMC"));
                o.setYh(rs.getString("ZHMC"));
                o.setYsjcmc(rs.getString("YSJCMC"));
                o.setYskmmc(rs.getString("YSKMMC"));
                o.setZgswjg(rs.getString("SWJGZZJGMC"));
                o.setZsswjg(rs.getString("ZSSWJGZZJGMC"));

                list.add(o);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return list;
    }

    private String getQueryWhereZB(JkscxForm pf) {
        StringBuffer sql_where = new StringBuffer();
        String sbrqq = pf.getSbrqq();
        String sbrqz = pf.getSbrqz();
        sql_where.append(" and A.SBRQ >= to_date('" + sbrqq + "','" +
                         DATE_FORMAT + "')");
        sql_where.append(" and A.SBRQ < to_date('" + sbrqz + "','" +
                         DATE_FORMAT + "')");

        String zyrqq = TinyTools.Date2String(TinyTools.addMonth( -1,
                TinyTools.stringToDate(sbrqq, "yyyyMMdd")), DATE_FORMAT);
        String zyrqz = TinyTools.Date2String(TinyTools.addMonth(1,
                TinyTools.stringToDate(sbrqz, "yyyyMMdd")), DATE_FORMAT);
        sql_where.append(" and A.ZYRQ > to_date('" + zyrqq + "','" +
                         DATE_FORMAT + "')");
        sql_where.append(" and A.ZYRQ < to_date('" + zyrqz + "','" +
                         DATE_FORMAT + "')");

        String swjgdm = pf.getSwjgdm();
        String swsdm = pf.getSwsdm();

        if (swjgdm != null && !swjgdm.equals("")) {
            sql_where.append(" and A.QXDM = '" + swjgdm.substring(0, 2) + "'");
        }

        if (swsdm != null && !swsdm.equals("")) {
            sql_where.append(" and A.SWJGZZJGDM = '" + swsdm + "'");
        }

        String jsjdm = pf.getJsjdm();
        if (jsjdm != null && !jsjdm.equals("")) {
            sql_where.append(" and A.JSJDM = '" + jsjdm + "'");
        }

        String jkpzh = pf.getJkpzh();
        if (jkpzh != null && !jkpzh.equals("")) {
            sql_where.append(" and A.JKPZH = '" + jkpzh + "'");
        }

        String sphm = pf.getSphm();
        if(sphm != null && !sphm.equals(""))
        {
            sql_where.append(" and A.SPHM = '" + sphm + "'");
        }

        String fsdm = pf.getSbfsdm();
        if (fsdm != null && !fsdm.equals("")) {
            sql_where.append(" and A.FSDM = '" + fsdm + "'");
        }

        String szdm = pf.getSzdm();

        if (szdm != null && !szdm.equals("")) {
            sql_where.append(" and A.SZDM = '" + szdm + "'");
        }

        String ysjcdm = pf.getYsjcdm();
        if (ysjcdm != null && !ysjcdm.equals("")) {
            sql_where.append(" and A.YSJCDM = '" + ysjcdm + "'");
        }

        String yskmdm = pf.getYskmdm();
        if (yskmdm != null && !yskmdm.equals("")) {
            sql_where.append(" and A.YSKMDM = '" + yskmdm + "'");
        }

        String gjbzhydm = pf.getGjbzhydm();
        if (gjbzhydm != null && !gjbzhydm.equals("")) {
            sql_where.append(" and A.GJBZHYDM = '" + gjbzhydm + "'");
        }

        String zsswjgdm = pf.getZsswjgdm();
        if (zsswjgdm != null && !zsswjgdm.equals("")) {
            sql_where.append(" and A.ZSSWJGZZJGDM = '" + zsswjgdm + "'");
        }

        String sklxdm = pf.getSklxdm();
        if (sklxdm != null && !sklxdm.equals("")) {
            sql_where.append(" and A.SKLXDM = '" + sklxdm + "'");
        }

        String djzclxdm = pf.getDjzclxdm();
        if (djzclxdm != null && !djzclxdm.equals("")) {
            sql_where.append(" and A.DJZCLXDM = '" + djzclxdm + "'");
        }

        String yhdm = pf.getYhdm();
        if (yhdm != null && !yhdm.equals("")) {
            sql_where.append(" and A.YHDM = '" + yhdm + "'");
        }

        String zbsjje = pf.getZbsjje();
        if (zbsjje != null && !zbsjje.equals("")) {
            sql_where.append(" and A.SJJE " + pf.getOp_zbsjje() + zbsjje);
        }

        return sql_where.toString();
    }


    private String getQueryWhereMX(JkscxForm pf) {
        StringBuffer sql_where = new StringBuffer();
        String smdm = pf.getSzsmdm();
        if (smdm != null && !smdm.equals("")) {
            sql_where.append(" and B.SZSMDM = '" + smdm + "'");
        }

        String sjje = pf.getSjje();
        if (sjje != null && !sjje.equals("")) {
            sql_where.append(" and B.SJSE " + pf.getOp_sjje() + sjje);
        }

        String jsje = pf.getJsje();
        if (jsje != null && !jsje.equals("")) {
            sql_where.append(" and B.JSJE " + pf.getOp_jsje() + jsje);
        }

        String kssl = pf.getKssl();
        if (kssl != null && !kssl.equals("")) {
            sql_where.append(" and B.KSSL " + pf.getOp_kssl() + kssl);
        }
        return sql_where.toString();
    }
}
