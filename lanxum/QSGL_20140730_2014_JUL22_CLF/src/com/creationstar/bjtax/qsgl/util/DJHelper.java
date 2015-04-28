package com.creationstar.bjtax.qsgl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

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
public class DJHelper {
    /**
     * 获取自然人登记信息
     * @param sfzjlx String　身份证件类型
     * @param sfzjhm String　身份证件号码
     * @param gjdm String　国籍代码
     * @return Map　自然人登记信息
     */
    public static Map getZrrDjInfo(String sfzjlx, String sfzjhm, String gjdm) throws
            BaseException {
        Map rtnMap = null;
        //
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = QSDBUtil.getConnection();
            String sql =
                    "select jsjdm,nsrmc,swjgzzjgdm from djdb.dj_jl_zrrjbsj t where t.zjlxdm='" +
                    sfzjlx +
                    "' and t.zjhm='" + sfzjhm +
                    "' and t.gjdm='" + gjdm +
                    "'";
            System.out.println("sql=" + sql);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                rtnMap = new HashMap();
                ZRRJBSJ zrrjbsj = new ZRRJBSJ();
                zrrjbsj.setJsjdm(rs.getString("jsjdm"));
                zrrjbsj.setNsrmc(rs.getString("nsrmc"));
                zrrjbsj.setZjlxdm(sfzjlx);
                zrrjbsj.setZjhm(sfzjhm);
                zrrjbsj.setGjdm(gjdm);
                zrrjbsj.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));

                rtnMap.put(DjOuterConstant.ZRRJBSJ, zrrjbsj);
            }
        } catch (SQLException ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //
        return rtnMap;
    }
}
