package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Kplx;

/**
 * ��Ʊ�������DAO
 * @author tutu
 * 2013-05-07(1)
 */
public class KplxDAO extends BaseDAO {
	
	/**
     * ����һ����Ʊ�������ݼ�¼
     * @param fpcz ��Ʊ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Kplx kplx, Connection conn) throws
            SQLException {
        String sql = "insert into dmdb.fp_dm_kplx (kplxdm,kplxmc,zxbs,cjr,cjrq,lrr,lrrq) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, kplx.kplxdm);
            ps.setString(2, kplx.kplxmc);
            ps.setString(3, kplx.zxbs);
            ps.setString(4, kplx.cjr);
            ps.setTimestamp(5, kplx.cjrq);
            ps.setString(6, kplx.lrr);
            ps.setTimestamp(7, kplx.lrrq);

            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
