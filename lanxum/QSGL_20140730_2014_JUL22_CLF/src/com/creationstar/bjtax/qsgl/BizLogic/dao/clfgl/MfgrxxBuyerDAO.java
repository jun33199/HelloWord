package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.ttsoft.common.util.Debug;

/**
 * �򷽸�����ϢDAO
 * @author tutu
 * 2013-05-10(1)
 */
public class MfgrxxBuyerDAO extends BaseDAO {
/**
 * ������Ϣ
 * @methodName:InsertMfgrxxBuyerList
 * @function:
 * 
 * @param conn
 * @param buyerList
 * @throws SQLException
 * @author:�Ʋ���
 * @create date��2013-5-14 ����10:25:12
 * @version 1.1
 * 
 *
 */
    public  void InsertMfgrxxBuyerList(Connection conn,List buyerList) throws SQLException {
    	System.out.println("+++++++++�����򷽸�����Ϣ������+++++++++"+buyerList.size());
    	String sql = "insert into qsdb.qs_jl_mfgrxx_buyer (sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,swjgzzjgdm,lb)  values(?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?)";
    	Debug.out("InsertfgrxxBuyerList:"+sql);
    	PreparedStatement ps = null;
    	try {
    	ps = conn.prepareStatement(sql);
    	for(int index =0;index <buyerList.size();index ++){
    		MfgrxxBuyer mfgrxxBuyer = (MfgrxxBuyer)buyerList.get(index);
    		int j=1;
    		ps.setString(j++, mfgrxxBuyer.getSbbh());
    		ps.setString(j++, mfgrxxBuyer.getHtbh());
    		ps.setBigDecimal(j++, new BigDecimal(mfgrxxBuyer.getSxh()));
    		ps.setString(j++, mfgrxxBuyer.getNsrmc());
    		ps.setString(j++, mfgrxxBuyer.getZjlxdm());
    		ps.setString(j++, mfgrxxBuyer.getZjlxmc());
    		ps.setString(j++, mfgrxxBuyer.getZjhm());
    		ps.setString(j++, mfgrxxBuyer.getQlrfe());
    		ps.setString(j++, mfgrxxBuyer.getLxdh());
    		ps.setString(j++, mfgrxxBuyer.getCjr());
    		ps.setString(j++, mfgrxxBuyer.getLrr());
    		ps.setString(j++, mfgrxxBuyer.getSwjgzzjgdm());
    		ps.setString(j++, mfgrxxBuyer.getLb());
    		ps.addBatch();
    		
    	}
    	//ִ����������
    	ps.executeBatch();
    	}catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
    	
    }
	
	/**
     * ����conditions��ȡ�򷽸�����Ϣ����
     * @param conditions ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �򷽸�����Ϣ����ֵ����ļ���
     * @throws SQLException
     */
    public  ArrayList queryMfgrxxBuyerList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfgrxxBuyerList = new ArrayList();
        String sql = "select  sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,lb  from qsdb.qs_jl_mfgrxx_buyer " + conditions+" order by sxh asc";
        
        //Debug.out("queryMfgrxxBuyerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
            	
            	mfgrxxBuyerItem.setSbbh(rs.getString("sbbh"));
            	mfgrxxBuyerItem.setHtbh(rs.getString("htbh"));
            	mfgrxxBuyerItem.setSxh(rs.getString("sxh"));
            	mfgrxxBuyerItem.setNsrmc(rs.getString("nsrmc"));
            	mfgrxxBuyerItem.setZjlxdm(rs.getString("zjlxdm"));
            	mfgrxxBuyerItem.setZjlxmc(rs.getString("zjlxmc"));
            	mfgrxxBuyerItem.setZjhm(rs.getString("zjhm"));
            	mfgrxxBuyerItem.setQlrfe(rs.getString("qlrfe"));
            	mfgrxxBuyerItem.setLxdh(rs.getString("lxdh"));
            	mfgrxxBuyerItem.setCjr(rs.getString("cjr"));
            	mfgrxxBuyerItem.setCjrq(rs.getTimestamp("cjrq"));
            	mfgrxxBuyerItem.setLrr(rs.getString("lrr"));
            	mfgrxxBuyerItem.setLrrq(rs.getTimestamp("lrrq"));
            	mfgrxxBuyerItem.setLb(rs.getString("lb"));
               
            	mfgrxxBuyerList.add(mfgrxxBuyerItem);
            }
           // System.out.println(" mfgrxxBuyerList size:"+mfgrxxBuyerList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfgrxxBuyerList;
    }
    
	 /**
	  * ��������Ϣ����ʷ��
	  * @return
	  */
	public boolean saveBuyyerInfo2his (String sbbh,String scrydm, Connection conn)throws SQLException{
		 boolean isSuccess = false;
		 PreparedStatement ps = null;
		 
		 //ɾ��ʱƴ��ɾ����Ա��ɾ��ʱ��SQL
		 String scrySQL=",'' SCCZRY,null SCRQ";
		 if(scrydm != null && !"".equals(scrydm)){
			 scrySQL=",'"+scrydm+"' SCCZRY,sysdate SCRQ";
		 }
		 
		 try {
		 //1.ƴ�Ӳ�����������ʷ��SQL
		 StringBuffer MMF_HisSQLBuff = new StringBuffer();
		 MMF_HisSQLBuff.append(" insert into QSDB.QS_JL_MMFGRXX_LS (XH,MMFBS,SBBH, HTBH, SXH, NSRMC, ZJLXDM, ZJLXMC, ZJHM, QLRFE, LXDH, SWJGZZJGDM, CJR, CJRQ, LRR, LRRQ, LB,SCCZRY,SCRQ)"
				 			  +" select QSDB.SEQ_QS_CLFJYXH.nextval xh,'0' MMFBS,SBBH, HTBH, SXH, NSRMC, ZJLXDM, ZJLXMC, ZJHM, QLRFE, LXDH, SWJGZZJGDM, CJR, CJRQ, LRR, LRRQ, LB");
		 MMF_HisSQLBuff.append(scrySQL);
		 MMF_HisSQLBuff.append(" from qsdb.qs_jl_mfgrxx_buyer where sbbh = ? ");
		 
		 System.out.println("MMF_HisSQLBuff##########"+MMF_HisSQLBuff.toString());
		 
		 ps = conn.prepareStatement(MMF_HisSQLBuff.toString()); 
		 
		 ps.setString(1, sbbh);
		 
		 isSuccess = ps.execute();
		 
		 } catch (SQLException e) {
	            throw e;
	        } finally {
	            ps.close();
	        }
		 return isSuccess;		
	}
	
	 /**
	  * ɾ������Ϣ
	  * @param htbh
	  * @param sbbh
	  * @param conn
	  * @return
	  */
	 public boolean delBuyyerInfo(String sbbh, Connection conn)throws SQLException{
		 boolean isSuccess = false;
		 PreparedStatement ps = null;
		 try {
			 String delSQL = "delete from qsdb.qs_jl_mfgrxx_buyer where sbbh=?";
			 ps = conn.prepareStatement(delSQL.toString()); 

			 ps.setString(1, sbbh);
			 
			 isSuccess = ps.execute();			 
		 } catch (SQLException e) {
	            throw e;
	        } finally {
	            ps.close();
	        }
		 
		 return isSuccess;
	 }
    
	 
		/**
	     * ��˰����conditions��ȡ�򷽸�����Ϣ����
	     * @param conditions ����where �������־�
	     * @param conn ���ݿ����Ӷ���
	     * @return ArrayList ��˰��ѯ�򷽸�����Ϣ����ֵ����ļ���
	     * @throws SQLException
	     */
	    public  ArrayList queryMfgrxxBuyerListForQs(Connection conn,String conditions) throws
	            SQLException {
	        ArrayList mfgrxxBuyerList = new ArrayList();
	        String sql = "select  sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,lb  from qsdb.qs_jl_mfgrxx_buyer " + conditions+" order by sxh asc";
	        
	        Debug.out("queryMfgrxxBuyerList:"+sql);
	        
	        PreparedStatement ps = null;
	        try {
	            ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) 
	            {
	            	MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
	            	
	            	mfgrxxBuyerItem.setSbbh(rs.getString("sbbh"));
	            	mfgrxxBuyerItem.setHtbh(rs.getString("htbh"));
	            	mfgrxxBuyerItem.setSxh(rs.getString("sxh"));
	            	mfgrxxBuyerItem.setNsrmc(rs.getString("nsrmc"));
	            	mfgrxxBuyerItem.setZjlxdm(rs.getString("zjlxdm"));
	            	mfgrxxBuyerItem.setZjlxmc(rs.getString("zjlxmc"));
	            	mfgrxxBuyerItem.setZjhm(rs.getString("zjhm"));
	            	mfgrxxBuyerItem.setQlrfe(rs.getString("qlrfe"));
	            	mfgrxxBuyerItem.setLxdh(rs.getString("lxdh"));
	            	mfgrxxBuyerItem.setCjr(rs.getString("cjr"));
	            	mfgrxxBuyerItem.setCjrq(rs.getTimestamp("cjrq"));
	            	mfgrxxBuyerItem.setLrr(rs.getString("lrr"));
	            	mfgrxxBuyerItem.setLrrq(rs.getTimestamp("lrrq"));
	            	mfgrxxBuyerItem.setLb(rs.getString("lb"));
	               
	            	mfgrxxBuyerList.add(mfgrxxBuyerItem);
	            }
	            System.out.println(" mfgrxxBuyerList size:"+mfgrxxBuyerList.size());
	            
	            rs.close();
	        } catch (SQLException e) {
	        	throw e;
	        } finally {
	            ps.close();
	        }
	        return mfgrxxBuyerList;
	    }
    
}
