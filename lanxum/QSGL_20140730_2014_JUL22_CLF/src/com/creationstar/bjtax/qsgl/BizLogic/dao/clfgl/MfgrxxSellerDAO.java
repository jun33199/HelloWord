package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.MfskzsBo;
import com.ttsoft.common.util.Debug;

/**
 * ����������ϢDAO
 * @author tutu
 * 2013-05-10(1)
 */
public class MfgrxxSellerDAO extends BaseDAO {
	
	/**
	 * д��������Ϣ
	 * @methodName:InsertMfgrxxSellerList
	 * @function:
	 * 
	 * @param conn
	 * @param sellerList
	 * @throws SQLException
	 * @author:�Ʋ���
	 * @create date��2013-5-14 ����09:59:09
	 * @version 1.1
	 * 
	 *
	 */
    public  void InsertMfgrxxSellerList(Connection conn,List sellerList) throws SQLException {
    	
    	System.out.println("+++++++++����������Ϣ������+++++++++"+sellerList.size());
    	String sql = "insert into qsdb.qs_jl_mfgrxx_seller (sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,swjgzzjgdm,lb)  values(?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?)";
    	Debug.out("InsertfgrxxBuyerList:"+sql);
    	PreparedStatement ps = null;
    	try {
    	ps = conn.prepareStatement(sql);
    	for(int index =0;index <sellerList.size();index ++){
    		MfgrxxSeller mfgrxxseller = (MfgrxxSeller)sellerList.get(index);
    		int j=1;
    		ps.setString(j++, mfgrxxseller.getSbbh());
    		ps.setString(j++, mfgrxxseller.getHtbh());
    		ps.setBigDecimal(j++, new BigDecimal(mfgrxxseller.getSxh()));
    		ps.setString(j++, mfgrxxseller.getNsrmc());
    		ps.setString(j++, mfgrxxseller.getZjlxdm());
    		ps.setString(j++, mfgrxxseller.getZjlxmc());
    		ps.setString(j++, mfgrxxseller.getZjhm());
    		ps.setString(j++, mfgrxxseller.getQlrfe());
    		ps.setString(j++, mfgrxxseller.getLxdh());
    		ps.setString(j++, mfgrxxseller.getCjr());
    		ps.setString(j++, mfgrxxseller.getLrr());
    		ps.setString(j++, mfgrxxseller.getSwjgzzjgdm());
    		ps.setString(j++, mfgrxxseller.getLb());
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
     * ����conditions��ȡ����������Ϣ����
     * @param conditions ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ����������Ϣ����ֵ����ļ���
     * @throws SQLException
     */
    public  ArrayList queryMfgrxxSellerList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfgrxxSellerList = new ArrayList();
        String sql = "select  sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,lb from qsdb.qs_jl_mfgrxx_seller " + conditions+" order by sxh asc";
        
        //Debug.out("queryMfgrxxSellerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
            	
            	mfgrxxSellerItem.setSbbh(rs.getString("sbbh"));
            	mfgrxxSellerItem.setHtbh(rs.getString("htbh"));
            	mfgrxxSellerItem.setSxh(rs.getString("sxh"));
            	mfgrxxSellerItem.setNsrmc(rs.getString("nsrmc"));
            	mfgrxxSellerItem.setZjlxdm(rs.getString("zjlxdm"));
            	mfgrxxSellerItem.setZjlxmc(rs.getString("zjlxmc"));
            	mfgrxxSellerItem.setZjhm(rs.getString("zjhm"));
            	mfgrxxSellerItem.setQlrfe(rs.getString("qlrfe"));
            	mfgrxxSellerItem.setLxdh(rs.getString("lxdh"));
            	mfgrxxSellerItem.setCjr(rs.getString("cjr"));
            	mfgrxxSellerItem.setCjrq(rs.getTimestamp("cjrq"));
            	mfgrxxSellerItem.setLrr(rs.getString("lrr"));
            	mfgrxxSellerItem.setLrrq(rs.getTimestamp("lrrq"));
            	mfgrxxSellerItem.setLb(rs.getString("lb"));
            	
            	mfgrxxSellerList.add(mfgrxxSellerItem);
            }
            //System.out.println(" mfgrxxSellerList size:"+mfgrxxSellerList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfgrxxSellerList;
    }
    
    /**
     * ����conditions��ȡ��ͬ������֤�������Ӧ����˰����˰����ʷ��Ϣ
     * @param conditions ����where �������־�
     * @param ztbs <>'91'  ��˰��״̬ ��91(����)
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰����˰����ʷ��Ϣ����ֵ����ļ���
     * @throws SQLException
     */
    public  ArrayList queryMfgrxxSbxxHisList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfgrxxSbxxHisList = new ArrayList();
        String sql = "select  a.sbbh,a.nsrmc,a.sfzjlxmc,a.sfzjhm,' ' fwsyqzh ,c.tdfwzldz,to_char(c.htqdsj,'YYYY-MM-DD') htqdsj,to_char(b.sbrq,'YYYY-MM-DD') sbrq,c.fwjzmj,b.jsje,b.sl,b.ynse,c.tdfwqszylxmc from qsdb.qs_jl_grxx a,qsdb.qs_jl_sbzb b,qsdb.qs_jl_tufwxx c,qsdb.qs_jl_sbtdfwgl d where a.sbbh=b.sbbh and b.sbbh=d.sbbh and d.tdfwid=c.tdfwid " + conditions + " and b.ztbs<>'91' order by sbbh";
        
//        Debug.out("queryMfgrxxSbxxHisList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfskzsBo bo = new MfskzsBo();
            	
            	bo.setSbbh_his(rs.getString("sbbh"));// �걨��ż�¼
            	bo.setNsrmc_his(rs.getString("nsrmc"));
            	bo.setSfzjlxmc_his(rs.getString("sfzjlxmc"));// ֤�����ͼ�¼
            	bo.setSfzjhm_his(rs.getString("sfzjhm"));// ֤�������¼
            	bo.setFwsyqzh_his(rs.getString("fwsyqzh")+"&nbsp;");// ��������Ȩ֤�ż�¼
            	bo.setTdfwzldz_his(rs.getString("tdfwzldz"));// ���ز�λ�ü�¼
            	bo.setHtqdsj_his(rs.getString("htqdsj"));// ��ͬǩ�����ڼ�¼
            	bo.setSbrq_his(rs.getString("sbrq"));// �걨���ڼ�¼
            	bo.setFwjzmj_his(rs.getString("fwjzmj"));// ���ݽ��������¼
            	bo.setJsje_his(rs.getString("jsje"));// ��˰����¼
            	bo.setSl_his(String.valueOf(rs.getDouble("sl")));// ˰�ʼ�¼
            	bo.setYnse_his(rs.getString("ynse"));// Ӧ�ɽ���¼	
            	bo.setFwqszylx_his(rs.getString("tdfwqszylxmc"));// ����Ȩ��ת������
            	
            	mfgrxxSbxxHisList.add(bo);
            }
//            System.out.println(" mfgrxxSbxxHisList size:"+mfgrxxSbxxHisList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfgrxxSbxxHisList;
    }  
	 /**
	  * ����������Ϣ����ʷ��
	  * @return
	  */
	public boolean saveSellerInfo2his (String sbbh,String scrydm, Connection conn)throws SQLException{
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
                          +" select QSDB.SEQ_QS_CLFJYXH.nextval xh, '1' MMFBS,SBBH, HTBH, SXH, NSRMC, ZJLXDM, ZJLXMC, ZJHM, QLRFE, LXDH, SWJGZZJGDM, CJR, CJRQ, LRR, LRRQ, LB");
		 MMF_HisSQLBuff.append(scrySQL);
		 MMF_HisSQLBuff.append("  from qsdb.qs_jl_mfgrxx_seller where sbbh = ? ");
		 
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
	  * ɾ��������Ϣ
	  * @param htbh
	  * @param sbbh
	  * @param conn
	  * @return
	  */
	 public boolean delSellerInfo(String sbbh, Connection conn)throws SQLException{
		 boolean isSuccess = false;
		 PreparedStatement ps = null;
		 try {
			 String delSQL = "delete from qsdb.qs_jl_mfgrxx_seller where sbbh=?";
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
    
    
    
}
