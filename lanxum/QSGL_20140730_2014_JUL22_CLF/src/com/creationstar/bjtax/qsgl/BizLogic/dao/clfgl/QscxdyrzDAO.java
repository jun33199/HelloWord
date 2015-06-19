package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qscxdyrz;
/**
 * ������������˰��ѯ��ӡ������¼��־DAO
 * @author tutu
 * 2013-08-19
 */
public class QscxdyrzDAO extends BaseDAO {

	/**
     * ����һ����ͬ��ƾ֤���չ�ϵ�����ݼ�¼
     * @param htypzdzgxb ��ͬ��ƾ֤���չ�ϵ���������ֵ����
     * @param conn ���ݿ����Ӷ���
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
            
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
