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
     * ��ȡ��Ȼ�˵Ǽ���Ϣ
     * @param sfzjlx String�����֤������
     * @param sfzjhm String�����֤������
     * @param gjdm String����������
     * @return Map����Ȼ�˵Ǽ���Ϣ
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
