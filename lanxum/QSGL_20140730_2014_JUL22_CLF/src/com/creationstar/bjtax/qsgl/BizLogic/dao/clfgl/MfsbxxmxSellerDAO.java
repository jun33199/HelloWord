package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxmxSeller;

/**
 * 卖方申报信息表子表DAO
 * @author zhangyj
 * 2013-06-09
 */
public class MfsbxxmxSellerDAO extends BaseDAO {

	
    public  void InsertList(Connection conn,List mfsbxxmxList) throws SQLException {
    	
    	String sql = "insert into qsdb.qs_jl_mfsbxxmx_seller (sbbh,szsmdm,jsje,kssl,sl,ynse,swjgzzjgdm,cjr,cjrq,lrr,lrrq,skssksrq,skssjsrq,xh,jmse,sjje)  values(?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?)";
    	PreparedStatement ps = null;
    	try {
    	ps = conn.prepareStatement(sql);
    	for(int index =0;index <mfsbxxmxList.size();index ++){
    		MfsbxxmxSeller ms = (MfsbxxmxSeller)mfsbxxmxList.get(index);
    		int j=1;
    		ps.setString(j++, ms.getSbbh());
    		ps.setString(j++, ms.getSzsmdm());
    		ps.setBigDecimal(j++, ms.getJsje());
    		ps.setBigDecimal(j++, ms.getKssl());
    		ps.setBigDecimal(j++, ms.getSl());
    		ps.setBigDecimal(j++, ms.getYnse());
    		ps.setString(j++, ms.getSwjgzzjgdm());
    		ps.setString(j++, ms.getCjr());
    		ps.setString(j++, ms.getLrr());
    		ps.setTimestamp(j++, ms.getSkssksrq());
    		ps.setTimestamp(j++, ms.getSkssjsrq());
    		ps.setBigDecimal(j++, new BigDecimal(ms.getXh()));
    		ps.setBigDecimal(j++, ms.getJmse());
    		ps.setBigDecimal(j++, ms.getSjje());
    		ps.addBatch();
    		
    	}
    	//执行批量操作
    	ps.executeBatch();
    	}catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
    	
    }
}
