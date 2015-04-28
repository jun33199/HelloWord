package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.HtypzdzgxbLs;

/**
 * @author tutu
 * ��ͬ��ƾ֤���չ�ϵ��ʱ��DAO
 * 2013-05-15(1)
 */
public class HtypzdzgxbLsDAO extends BaseDAO {

	/**
     * ����һ����ͬ��ƾ֤���չ�ϵ�����ݼ�¼
     * @param htypzdzgxb ��ͬ��ƾ֤���չ�ϵ���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(HtypzdzgxbLs htypzdzgxbLs, Connection conn) throws
            SQLException {
        String sql = "insert into qsdb.qs_jl_htypzdzgxb_ls (xh,ybxh,htbh,sbbh,mmfbz,pzfldm,pizzldm,pzhm,pzndzb,cjr,cjrq,lrr,lrrq,czr,czrq,swjgzzjgdm,bz) values (QSDB.SEQ_QS_CLFJYXH.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, htypzdzgxbLs.ybxh);
            ps.setString(2, htypzdzgxbLs.htbh);
            ps.setString(3, htypzdzgxbLs.sbbh);
            ps.setString(4, htypzdzgxbLs.mmfbz);
            ps.setString(5, htypzdzgxbLs.pzfldm);
            ps.setString(6, htypzdzgxbLs.pizzldm);
            ps.setString(7, htypzdzgxbLs.pzhm);
            ps.setString(8, htypzdzgxbLs.pzndzb);
            ps.setString(9, htypzdzgxbLs.cjr);
            ps.setTimestamp(10, htypzdzgxbLs.cjrq);
            ps.setString(11, htypzdzgxbLs.lrr);
            ps.setTimestamp(12, htypzdzgxbLs.lrrq);
            ps.setString(13, htypzdzgxbLs.czr);
            ps.setTimestamp(14, htypzdzgxbLs.czrq);
            ps.setString(15, htypzdzgxbLs.swjgzzjgdm);
            ps.setString(16, htypzdzgxbLs.bz);
            
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
