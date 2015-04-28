package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;

/**
 * 发票种类代码DAO
 * @author tutu
 * 2013-05-07(1)
 */
public class FpzlDAO extends BaseDAO{
	
	/**
     * 插入一条发票操作数据记录
     * @param fpcz 发票操作数据值对象
     * @param conn 数据库连接对象
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

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
