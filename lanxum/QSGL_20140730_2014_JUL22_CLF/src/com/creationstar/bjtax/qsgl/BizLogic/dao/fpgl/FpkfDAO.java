package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkf;

/**
 * 发票库房代码DAO
 * @author tutu
 * 2013-05-02(1)
 */
public class FpkfDAO extends BaseDAO {
	
	/**
     * 插入一条发票操作数据记录
     * @param fpcz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fpkf fpkf, Connection conn) throws
            SQLException {
        String sql = "insert int dmdb.fp_dm_fpkf (fpkfdm,fpkfmc,jqbh,jsjdm,zxbs,cjr,cjrq,lrr,lrrq) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpkf.fpkfdm);
            ps.setString(2, fpkf.fpkfmc);
            ps.setString(3, fpkf.jqbh);
            ps.setString(4, fpkf.jsjdm);
            ps.setString(5, fpkf.zxbs);
            ps.setString(6, fpkf.cjr);
            ps.setTimestamp(7, fpkf.cjrq);
            ps.setString(8, fpkf.lrr);
            ps.setTimestamp(9, fpkf.lrrq);
            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
