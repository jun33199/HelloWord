package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;

/**
 * ��Ʊ�������DAO
 * @author tutu
 * 2013-05-07(1)
 */
public class FpzlDAO extends BaseDAO{
	
	/**
     * ����һ����Ʊ�������ݼ�¼
     * @param fpcz ��Ʊ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Fpzl fpzl, Connection conn) throws
            SQLException {
        String sql = "insert into dmdb.fp_dm_fpzl (fpzldm,fpzlmc,zxbs,cjr,cjrq,lrr,lrrq,fphmcd) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpzl.fpzldm);
            ps.setString(2, fpzl.fpzlmc);
            ps.setString(3, fpzl.zxbs);
            ps.setString(4, fpzl.cjr);
            ps.setTimestamp(5, fpzl.cjrq);
            ps.setString(6, fpzl.lrr);
            ps.setTimestamp(7, fpzl.lrrq);
            ps.setString(8, fpzl.fphmcd);

            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
