package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qscxdyrz;
/**
 * 存量房交易契税查询打印操作记录日志DAO
 * @author tutu
 * 2013-08-19
 */
public class QscxdyrzDAO extends BaseDAO {

	/**
     * 插入一条合同与凭证对照关系表数据记录
     * @param htypzdzgxb 合同与凭证对照关系表操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qscxdyrz qscxdyrz, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_CLFJY_QSCXDYRZ (xh,htbh,sbbh_clf,sbbh_qs,cjr,cjrq) values (QSDB.SEQ_QS_CLFJYXH.nextval,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, qscxdyrz.htbh);
            ps.setString(2, qscxdyrz.sbbh_clf);
            ps.setString(3, qscxdyrz.sbbh_qs);
            ps.setString(4, qscxdyrz.cjr);
            ps.setTimestamp(5, qscxdyrz.cjrq);
            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
