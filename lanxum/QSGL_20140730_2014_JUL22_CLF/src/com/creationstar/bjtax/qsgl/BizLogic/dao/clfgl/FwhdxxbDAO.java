package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxmxSeller;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * 房屋核定信息表DAO
 * @author tutu
 * 2013-08-14
 */
public class FwhdxxbDAO extends BaseDAO {

	/**
     * 插入一条房屋核定信息数据记录
     * @param fpcz 发票库存数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public  void insert(Fwhdxx fwhdxx, Connection conn) throws SQLException {
    	
    	try{
	    	if(hasExists(fwhdxx.getSbbh(),conn)){
	    		//执行更新
	    		doUpdate(fwhdxx, conn);
	    	}else{
	    		//执行插入
	    		doInsert(fwhdxx, conn);
	    	}
    	} catch (SQLException e) {
            throw e;
        }
    }

    /**
     * 执行插入
     * @param fwhdxx
     * @param conn
     * @throws SQLException
     */
	private void doInsert(Fwhdxx fwhdxx, Connection conn) throws SQLException {
		System.out.println("+++++++++++执行插入 DAO++++++++++++");
        String sql = "insert into QSDB.QS_JL_FWHDXXB (SBBH,HTBH,SQRXZDZ,JTWYSHYHBZ,FWCQZBZZFLXDM,FWLXDM,FWJZMJ,JCND," +
        		"YGFFPJE,GFZMRQ,TDZZSSBFS,QDFCQSJE,QDFCYHSJE,QDTDSYQZFJE,JFPGJG,JGPGFY,FDCZJJSJDM,FDCZJSWDJZH,FDCZJLXDH," +
        		"FDCZJJJR,FDCZJJJRLXDH,FDCZJJJRZJHM,FDCZJJJRZGZSH,CQZBZJZMJFL,MPMJYDJ,TDJCDM,PTZFZGXJ,FWRJL,HFBZ,ZFSJSJFL," +
        		"YYSZSFS,GRSDSZSFS,TDZZSZSFS,JSSRQRFS,JSJE,ZFPGJG,ZFZXFY,ZFDKLX,SXF,GZF,HLFY,cjr,cjrq,lrr,lrrq,"+
        		"mpmhdjg,tdcrj,jwcxfwyz,cjssl,fpszrq,anjjse,fwszqydm,hdjg,fwhdlx,QSZYXSMXDM,YQSPFWJSJG,yhszsfs,FWSZQYJE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++,fwhdxx.sbbh);
            
            ps.setString(index++,fwhdxx.htbh);
            
            ps.setString(index++,fwhdxx.sqrxzdz);
            
            ps.setString(index++,fwhdxx.jtwyshyhbz);
            
            ps.setString(index++,fwhdxx.fwcqzbzzflxdm);
            
            ps.setString(index++,fwhdxx.fwlxdm);
            
            ps.setBigDecimal(index++, fwhdxx.fwjzmj);
            
            ps.setString(index++,fwhdxx.jcnd);
            
            ps.setBigDecimal(index++, fwhdxx.ygffpje);
            
            ps.setTimestamp(index++, fwhdxx.gfzmrq);
            
            ps.setString(index++,fwhdxx.tdzzssbfs);
            
            ps.setBigDecimal(index++, fwhdxx.qdfcqsje);
            
            ps.setBigDecimal(index++, fwhdxx.qdfcyhsje);
            
            ps.setBigDecimal(index++, fwhdxx.qdtdsyqzfje);
            
            ps.setBigDecimal(index++, fwhdxx.jfpgjg);
            
            ps.setBigDecimal(index++, fwhdxx.jgpgfy);
            
            ps.setString(index++,fwhdxx.fdczjjsjdm);
            
            ps.setString(index++,fwhdxx.fdczjswdjzh);
            
            ps.setString(index++,fwhdxx.fdczjlxdh);
            
            ps.setString(index++,fwhdxx.fdczjjjr);
            
            ps.setString(index++,fwhdxx.fdczjjjrlxdh);
            
            ps.setString(index++,fwhdxx.fdczjjjrzjhm);
            
            ps.setString(index++,fwhdxx.fdczjjjrzgzsh);
            
            ps.setString(index++,fwhdxx.cqzbzjzmjfl);
            
            ps.setBigDecimal(index++, fwhdxx.mpmjydj);
            
            ps.setString(index++, fwhdxx.tdjcdm);
            
            ps.setBigDecimal(index++, fwhdxx.ptzfzgxj);
            
            ps.setString(index++,fwhdxx.fwrjl);
            
            ps.setString(index++,fwhdxx.hfbz);
            
            ps.setString(index++,fwhdxx.zfsjsjfl);
            
            ps.setString(index++,fwhdxx.yyszsfs);
            
            ps.setString(index++,fwhdxx.grsdszsfs);
            
            ps.setString(index++,fwhdxx.tdzsszsfs);
            
            ps.setString(index++,fwhdxx.jssrqrfs);
            
            ps.setBigDecimal(index++, fwhdxx.jsje);
            
            ps.setBigDecimal(index++, fwhdxx.zfpgjg);
            
            ps.setBigDecimal(index++, fwhdxx.zfzxfy);
            
            ps.setBigDecimal(index++, fwhdxx.zfdklx);
            
            ps.setBigDecimal(index++, fwhdxx.sxf);
            
            ps.setBigDecimal(index++, fwhdxx.gzf);
            
            ps.setBigDecimal(index++, fwhdxx.hlfy);
            
            ps.setString(index++, fwhdxx.cjr);
            //ps.setTimestamp(23, fwhdxx.cjrq);
            ps.setString(index++, fwhdxx.lrr);
            //ps.setTimestamp(45, fwhdxx.lrrq);
            
            ps.setBigDecimal(index++, fwhdxx.mpmhdjg);
            ps.setBigDecimal(index++, fwhdxx.tdcrj);
            ps.setBigDecimal(index++, fwhdxx.jwcxfwyz);
            ps.setBigDecimal(index++, fwhdxx.cjssl);
            ps.setTimestamp(index++, fwhdxx.fpszrq);
            ps.setBigDecimal(index++, fwhdxx.anjjse);
            ps.setString(index++, fwhdxx.fwszqydm);
            
            BigDecimal hdjg = new BigDecimal("0.00");
            if(fwhdxx.jssrqrfs != null && !"".equals(fwhdxx.jssrqrfs)&& Constants.JSSRQRFS_HDJSJG.equals(fwhdxx.jssrqrfs)){
            	hdjg= fwhdxx.jsje;
            }
            ps.setBigDecimal(index++, hdjg);
            ps.setString(index++, fwhdxx.fwhdlxdm);
            ps.setString(index++, fwhdxx.qszyxsmxdm);
            ps.setBigDecimal(index++, fwhdxx.yqspfwjsjg);
            ps.setString(index++, fwhdxx.yhszsfs);
            ps.setBigDecimal(index++, fwhdxx.fwszqyje);
            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
	}
    
	/**
	 * 是否存在核定信息
	 * @param sbbh
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
    public boolean  hasExists(String sbbh,Connection conn)throws SQLException{
        PreparedStatement ps = null;
        ResultSet  rs = null;
        
        if(sbbh == null || "".equals(sbbh)){
        	return false;
        }
        
        try {
        	String sql ="select * from QSDB.QS_JL_FWHDXXB where sbbh =?";
            ps = conn.prepareStatement(sql);  	
            ps.setString(1, sbbh);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
            	return true;
            }
    	} catch (SQLException e) {
            throw e;
        } finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
        }
    	
    	return false;
    }
    
    /**
     * 执行更新操作
     * @param fwhdxx
     * @param conn
     * @return
     * @throws SQLException
     */
    private boolean doUpdate(Fwhdxx fwhdxx, Connection conn)throws SQLException{
    	System.out.println("+++++++++++执行更新 DAO++++++++++++");
        PreparedStatement ps = null;
    	String updateSQL="update QSDB.QS_JL_FWHDXXB set HTBH=?,SQRXZDZ=?,JTWYSHYHBZ=?,FWCQZBZZFLXDM=?,FWLXDM=?,FWJZMJ=?,JCND=?," +
    			"YGFFPJE=?,GFZMRQ=?,TDZZSSBFS=?,QDFCQSJE=?,QDFCYHSJE=?,QDTDSYQZFJE=?,JFPGJG=?,JGPGFY=?,FDCZJJSJDM=?,FDCZJSWDJZH=?," +
    			"FDCZJLXDH=?,FDCZJJJR=?,FDCZJJJRLXDH=?,FDCZJJJRZJHM=?,FDCZJJJRZGZSH=?,CQZBZJZMJFL=?,MPMJYDJ=?,TDJCDM=?,PTZFZGXJ=?," +
    			"FWRJL=?,HFBZ=?,ZFSJSJFL=?,YYSZSFS=?,GRSDSZSFS=?,TDZZSZSFS=?,JSSRQRFS=?,JSJE=?,ZFPGJG=?,ZFZXFY=?,ZFDKLX=?,SXF=?," +
    			"GZF=?,HLFY=?,lrr=?,lrrq=sysdate,mpmhdjg=?,tdcrj=?,jwcxfwyz=?,cjssl=?,fpszrq=?,anjjse=?,fwszqydm=?,hdjg=?,fwhdlx=?,QSZYXSMXDM=?,YQSPFWJSJG=?,yhszsfs=?,fwszqyje=? where sbbh =?";
    	
    	//如果存入历史表出错，则返回false
    	if(!saveHis(fwhdxx, conn,null)){
    		return false;
    	}
    	
    	System.out.println("+++++++++++执行更新start++++++++++++");
    	 try {
    		 ps = conn.prepareStatement(updateSQL);  
    		 
             int index = 1;
             //ps.setString(index++,fwhdxx.sbbh);
             
             ps.setString(index++,fwhdxx.htbh);
             
             ps.setString(index++,fwhdxx.sqrxzdz);
             
             ps.setString(index++,fwhdxx.jtwyshyhbz);
             
             ps.setString(index++,fwhdxx.fwcqzbzzflxdm);
             
             ps.setString(index++,fwhdxx.fwlxdm);
             
             ps.setBigDecimal(index++, fwhdxx.fwjzmj);
             
             ps.setString(index++,fwhdxx.jcnd);
             
             ps.setBigDecimal(index++, fwhdxx.ygffpje);
             
             ps.setTimestamp(index++, fwhdxx.gfzmrq);
             
             ps.setString(index++,fwhdxx.tdzzssbfs);
             
             ps.setBigDecimal(index++, fwhdxx.qdfcqsje);
             
             ps.setBigDecimal(index++, fwhdxx.qdfcyhsje);
             
             ps.setBigDecimal(index++, fwhdxx.qdtdsyqzfje);
             
             ps.setBigDecimal(index++, fwhdxx.jfpgjg);
             
             ps.setBigDecimal(index++, fwhdxx.jgpgfy);
             
             ps.setString(index++,fwhdxx.fdczjjsjdm);
             
             ps.setString(index++,fwhdxx.fdczjswdjzh);
             
             ps.setString(index++,fwhdxx.fdczjlxdh);
             
             ps.setString(index++,fwhdxx.fdczjjjr);
             
             ps.setString(index++,fwhdxx.fdczjjjrlxdh);
             
             ps.setString(index++,fwhdxx.fdczjjjrzjhm);
             
             ps.setString(index++,fwhdxx.fdczjjjrzgzsh);
             
             ps.setString(index++,fwhdxx.cqzbzjzmjfl);
             
             ps.setBigDecimal(index++, fwhdxx.mpmjydj);
             
             ps.setString(index++, fwhdxx.tdjcdm);
             
             ps.setBigDecimal(index++, fwhdxx.ptzfzgxj);
             
             ps.setString(index++,fwhdxx.fwrjl);
             
             ps.setString(index++,fwhdxx.hfbz);
             
             ps.setString(index++,fwhdxx.zfsjsjfl);
             
             ps.setString(index++,fwhdxx.yyszsfs);
             
             ps.setString(index++,fwhdxx.grsdszsfs);
             
             ps.setString(index++,fwhdxx.tdzsszsfs);
             
             ps.setString(index++,fwhdxx.jssrqrfs);
             
             ps.setBigDecimal(index++, fwhdxx.jsje);
             
             ps.setBigDecimal(index++, fwhdxx.zfpgjg);
             
             ps.setBigDecimal(index++, fwhdxx.zfzxfy);
             
             ps.setBigDecimal(index++, fwhdxx.zfdklx);
             
             ps.setBigDecimal(index++, fwhdxx.sxf);
             
             ps.setBigDecimal(index++, fwhdxx.gzf);
             
             ps.setBigDecimal(index++, fwhdxx.hlfy);
             
             ps.setString(index++, fwhdxx.lrr);
             
             ps.setBigDecimal(index++, fwhdxx.mpmhdjg);
             ps.setBigDecimal(index++, fwhdxx.tdcrj);
             ps.setBigDecimal(index++, fwhdxx.jwcxfwyz);
             ps.setBigDecimal(index++, fwhdxx.cjssl);
             ps.setTimestamp(index++, fwhdxx.fpszrq);
             ps.setBigDecimal(index++, fwhdxx.anjjse);
             ps.setString(index++, fwhdxx.fwszqydm);
             
             BigDecimal hdjg = new BigDecimal("0.00");
             if(fwhdxx.jssrqrfs != null && !"".equals(fwhdxx.jssrqrfs)&& Constants.JSSRQRFS_HDJSJG.equals(fwhdxx.jssrqrfs)){
             	hdjg= fwhdxx.jsje;
             }
             ps.setBigDecimal(index++, hdjg);
             ps.setString(index++, fwhdxx.fwhdlxdm);
             ps.setString(index++, fwhdxx.qszyxsmxdm);
             ps.setBigDecimal(index++, fwhdxx.yqspfwjsjg);            
             ps.setString(index++, fwhdxx.yhszsfs);  
             ps.setBigDecimal(index++, fwhdxx.fwszqyje);//added by zhangj,2014.10.15
             //更新条件
             ps.setString(index++, fwhdxx.sbbh);
             
             //执行
            ps.execute();  
    	 } catch (SQLException e) {
             throw e;
         } finally {
             ps.close();
             System.out.println("+++++++++++执行更新end++++++++++++");
         }
       	 return true;
    }
    
    /**
     * 删除核定信息
     * @param fwhdxx
     * @param conn
     * @return
     */
    public boolean delete(Fwhdxx fwhdxx, Connection conn)throws SQLException{
    	boolean isSucc = false;
    	System.out.println("删除核定信息-------------------");
    	PreparedStatement ps = null;
    	try{
    		//保存历史信息
    		boolean saveHisSucc = saveHis(fwhdxx, conn, "delete");
    		
    		if(saveHisSucc == true){
    			String delSql = "delete from qsdb.qs_jl_fwhdxxb where sbbh=?";
    			 ps = conn.prepareStatement(delSql); 
    			 ps.setString(1, fwhdxx.getSbbh());
    			 isSucc = ps.execute();
    		}
    	}catch(SQLException e){
    		throw e;
    	}
    	return isSucc;
    }
    
    
    
    
    /**
     * 保存历史
     * @param fwhdxx
     * @param conn
     * @return
     * @throws SQLException
     */
    private boolean saveHis(Fwhdxx fwhdxx, Connection conn,String type)throws SQLException{
    	System.out.println("保存历史-------------------");
    	 PreparedStatement ps = null;
    	 StringBuffer hisSQL = new StringBuffer();
    			 
    	 hisSQL.append("insert into QSDB.QS_JL_FWHDXXB_ls (xh,SBBH,HTBH,SQRXZDZ,JTWYSHYHBZ,FWCQZBZZFLXDM,FWLXDM,FWJZMJ,JCND," +
     	 		"YGFFPJE,GFZMRQ,TDZZSSBFS,QDFCQSJE,QDFCYHSJE,QDTDSYQZFJE,JFPGJG,JGPGFY,FDCZJJSJDM,FDCZJSWDJZH,FDCZJLXDH," +
     	 		"FDCZJJJR,FDCZJJJRLXDH,FDCZJJJRZJHM,FDCZJJJRZGZSH,CQZBZJZMJFL,MPMJYDJ,TDJCDM,PTZFZGXJ,FWRJL,HFBZ,ZFSJSJFL," +
     	 		"YYSZSFS,GRSDSZSFS,TDZZSZSFS,JSSRQRFS,JSJE,ZFPGJG,ZFZXFY,ZFDKLX,SXF,GZF,HLFY,cjr,cjrq,lrr,lrrq," +
     	 		"mpmhdjg,tdcrj,jwcxfwyz,cjssl,fpszrq,anjjse,fwszqydm,hdjg,fwhdlx,QSZYXSMXDM,YQSPFWJSJG,yhszsfs,fwszqyje");
    	 
    	 if(type != null && "delete".equals(type)){
    		 hisSQL.append(",scczry,scrq ");
    		 
    	 }
    	 hisSQL.append(") ");
    	 hisSQL.append(" select QSDB.SEQ_QS_CLFJYXH.NEXTVAL,SBBH,HTBH,SQRXZDZ,JTWYSHYHBZ,FWCQZBZZFLXDM,FWLXDM,FWJZMJ,JCND," +
    	 		"YGFFPJE,GFZMRQ,TDZZSSBFS,QDFCQSJE,QDFCYHSJE,QDTDSYQZFJE,JFPGJG,JGPGFY,FDCZJJSJDM,FDCZJSWDJZH,FDCZJLXDH," +
    	 		"FDCZJJJR,FDCZJJJRLXDH,FDCZJJJRZJHM,FDCZJJJRZGZSH,CQZBZJZMJFL,MPMJYDJ,TDJCDM,PTZFZGXJ,FWRJL,HFBZ,ZFSJSJFL," +
    	 		"YYSZSFS,GRSDSZSFS,TDZZSZSFS,JSSRQRFS,JSJE,ZFPGJG,ZFZXFY,ZFDKLX,SXF,GZF,HLFY,cjr,cjrq,lrr,lrrq," +
    	 		"mpmhdjg,tdcrj,jwcxfwyz,cjssl,fpszrq,anjjse,fwszqydm,hdjg,fwhdlx,QSZYXSMXDM,YQSPFWJSJG,yhszsfs,fwszqyje ");
    	 
    	 if(type != null && "delete".equals(type)){
    		 hisSQL.append(",'"+fwhdxx.getLrr()+"',sysdate ");
    	 }
    	 
    	 hisSQL.append(" from QSDB.QS_JL_FWHDXXB where sbbh=?");
    	 
    	 System.out.println("存入核定信息历史表#####"+hisSQL.toString());
    	 
   	 try {
   		 ps = conn.prepareStatement(hisSQL.toString()); 
   		 
   		 ps.setString(1, fwhdxx.getSbbh());
   		 
   		  ps.execute();
   		 
   		 
   	 } catch (SQLException e) {
         throw e;
     } finally {
         ps.close();
     }
   	 return true;
    }
    
	/**
	 * 通过sbbh查询房屋核定信息表
	 * @param conn
	 * @param conditions
	 * @return
	 * @throws SQLException
	 */
    public  ArrayList query(Connection conn,String conditions) throws
    SQLException {
    	//System.out.println("获得税务人员核定信息-------------------");
    	ArrayList list = new ArrayList();
    	 PreparedStatement ps = null;
    	 ResultSet rs=null;
    	String querySQL = "select sbbh, htbh, sqrxzdz, jtwyshyhbz, fwcqzbzzflxdm, fwlxdm, fwjzmj, jcnd, ygffpje, gfzmrq, " +
    			"tdzzssbfs, qdfcqsje, qdfcyhsje, qdtdsyqzfje, jfpgjg, jgpgfy, fdczjjsjdm, fdczjswdjzh, fdczjlxdh, fdczjjjr, " +
    			"fdczjjjrlxdh, fdczjjjrzjhm, fdczjjjrzgzsh, cqzbzjzmjfl, mpmjydj, tdjcdm, ptzfzgxj, fwrjl, zfsjsjfl, yyszsfs, " +
    			"grsdszsfs, tdzzszsfs, jssrqrfs, jsje, zfpgjg, zfzxfy, zfdklx, sxf, gzf, hlfy, cjr, cjrq, lrr, lrrq, hfbz, " +
    			"mpmhdjg, tdcrj, jwcxfwyz, cjssl, fpszrq, anjjse,fwszqydm,hdjg,fwhdlx,QSZYXSMXDM,YQSPFWJSJG,yhszsfs,fwszqyje from QSDB.QS_JL_FWHDXXB "+conditions;
    	
    	//System.out.println("querySQL ::"+querySQL);
    	
    	
        try {
            ps = conn.prepareStatement(querySQL);
            
            rs= ps.executeQuery();
            
            while (rs.next()) 
            {
            	Fwhdxx fwhdxx = new Fwhdxx();
            	
            	fwhdxx.setSbbh(rs.getString("sbbh"));
            	fwhdxx.setHtbh(rs.getString("htbh"));
            	fwhdxx.setSqrxzdz(rs.getString("sqrxzdz"));
            	fwhdxx.setJtwyshyhbz(rs.getString("jtwyshyhbz"));
            	fwhdxx.setFwcqzbzzflxdm(rs.getString("fwcqzbzzflxdm"));
            	fwhdxx.setFwlxdm(rs.getString("fwlxdm"));
            	fwhdxx.setFwjzmj(rs.getBigDecimal("fwjzmj"));
            	fwhdxx.setJcnd(rs.getString("jcnd"));
            	
            	fwhdxx.setYgffpje(rs.getBigDecimal("ygffpje"));
            	fwhdxx.setGfzmrq(rs.getTimestamp("gfzmrq"));
            	fwhdxx.setTdzzssbfs(rs.getString("tdzzssbfs"));
            	fwhdxx.setQdfcqsje(rs.getBigDecimal("qdfcqsje"));
            	fwhdxx.setQdfcyhsje(rs.getBigDecimal("qdfcyhsje"));
            	fwhdxx.setQdtdsyqzfje(rs.getBigDecimal("qdtdsyqzfje"));
            	fwhdxx.setJfpgjg(rs.getBigDecimal("jfpgjg"));
            	fwhdxx.setJgpgfy(rs.getBigDecimal("jgpgfy"));
            	
            	fwhdxx.setFdczjjsjdm(rs.getString("fdczjjsjdm"));
            	fwhdxx.setFdczjswdjzh(rs.getString("fdczjswdjzh"));
            	fwhdxx.setFdczjlxdh(rs.getString("fdczjlxdh"));
            	fwhdxx.setFdczjjjr(rs.getString("fdczjjjr"));
            	fwhdxx.setFdczjjjrlxdh(rs.getString("fdczjjjrlxdh"));
            	fwhdxx.setFdczjjjrzjhm(rs.getString("fdczjjjrzjhm"));
            	fwhdxx.setFdczjjjrzgzsh(rs.getString("fdczjjjrzgzsh"));
            	
            	fwhdxx.setCqzbzjzmjfl(rs.getString("cqzbzjzmjfl"));
            	fwhdxx.setMpmjydj(rs.getBigDecimal("mpmjydj"));
            	fwhdxx.setTdjcdm(rs.getString("tdjcdm"));
            	fwhdxx.setPtzfzgxj(rs.getBigDecimal("ptzfzgxj"));
            	fwhdxx.setFwrjl(rs.getString("fwrjl"));
            	fwhdxx.setZfsjsjfl(rs.getString("zfsjsjfl"));
            	fwhdxx.setYyszsfs(rs.getString("yyszsfs"));
            	fwhdxx.setGrsdszsfs(rs.getString("grsdszsfs"));
            	fwhdxx.setTdzsszsfs(rs.getString("tdzzszsfs"));
            	
            	fwhdxx.setJssrqrfs(rs.getString("jssrqrfs"));
            	fwhdxx.setJsje(rs.getBigDecimal("jsje"));
            	fwhdxx.setZfpgjg(rs.getBigDecimal("zfpgjg"));
            	fwhdxx.setZfzxfy(rs.getBigDecimal("zfzxfy"));
            	fwhdxx.setZfdklx(rs.getBigDecimal("zfdklx"));
            	fwhdxx.setSxf(rs.getBigDecimal("sxf"));
            	fwhdxx.setGzf(rs.getBigDecimal("gzf"));
            	fwhdxx.setHlfy(rs.getBigDecimal("hlfy"));
            	fwhdxx.setCjr(rs.getString("cjr"));
            	fwhdxx.setCjrq(rs.getTimestamp("cjrq"));
            	fwhdxx.setLrr(rs.getString("lrr"));
            	fwhdxx.setLrrq(rs.getTimestamp("lrrq"));
            	fwhdxx.setHfbz(rs.getString("hfbz"));
            	
            	fwhdxx.setMpmhdjg(rs.getBigDecimal("mpmhdjg"));
            	fwhdxx.setTdcrj(rs.getBigDecimal("tdcrj"));
            	fwhdxx.setJwcxfwyz(rs.getBigDecimal("jwcxfwyz"));
            	fwhdxx.setCjssl(rs.getBigDecimal("cjssl"));
            	fwhdxx.setFpszrq(rs.getTimestamp("fpszrq"));
            	fwhdxx.setAnjjse(rs.getBigDecimal("anjjse"));
            	fwhdxx.setFwszqydm(rs.getString("fwszqydm"));
            	fwhdxx.setHdjg(rs.getBigDecimal("hdjg"));
            	fwhdxx.setFwhdlxdm(rs.getString("fwhdlx"));
            	fwhdxx.setQszyxsmxdm(rs.getString("qszyxsmxdm"));
            	fwhdxx.setYqspfwjsjg(rs.getBigDecimal("yqspfwjsjg")==null?new BigDecimal("0"):rs.getBigDecimal("yqspfwjsjg"));            	
            	fwhdxx.setYhszsfs(rs.getString("yhszsfs")); 
            	fwhdxx.setFwszqyje(rs.getBigDecimal("fwszqyje"));//added by zhangj,2014.10.15
            	
            	list.add(fwhdxx);
            }
            
        } catch (SQLException e) {
            throw e;
        } finally {
        	if(rs!=null) rs.close();
        	if(ps!=null) ps.close();
        }
    	return list;
    }
    
    /**
     * 更新一条核定信息记录(更新建委房屋查询原值)
     * @param fpczz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void updateFwyz(Fwhdxx fwhdxx, Connection conn) throws
            SQLException {
        String sql = "update QSDB.QS_JL_FWHDXXB set jwcxfwyz=? where sbbh = ? ";
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setBigDecimal(1, fwhdxx.jwcxfwyz);
            ps.setString(2,fwhdxx.sbbh);
            
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 查询卖方税款申报信息
     * @param conn
     * @param conditions
     * @return
     * @throws SQLException
     */
    public ArrayList queryMfsbxx(Connection conn,String sbbh) throws
    SQLException {
    	
        String sql = "select substr(SZSMDM, 1, 2) SZSMDM,nvl(sl,0)*100 sl,sum(jsje) JSJE,sum(sjje) SJJE from qsdb.qs_jl_mfsbxxmx_seller mx where mx.sbbh = ? group by mx.sbbh,mx.sl,substr(SZSMDM, 1, 2)";
        PreparedStatement ps = null;
        ArrayList mfsbxxList = new ArrayList();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            
            while(rs.next()){
            	MfsbxxmxSeller  mx = new MfsbxxmxSeller();
            	mx.setSzsmdm(rs.getString("SZSMDM"));
            	mx.setSl(rs.getBigDecimal("sl"));
            	mx.setJsje(rs.getBigDecimal("JSJE"));
            	mx.setSjje(rs.getBigDecimal("SJJE"));
            	
            	mfsbxxList.add(mx);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }  	
        return mfsbxxList;
    }
    
    /**
     * 查询卖方税款申报合计金额
     * @param conn
     * @param conditions
     * @return
     * @throws SQLException
     */
    public String queryMfsbSJHJJE(Connection conn,String sbbh) throws
    SQLException {
    	
        String sql = "select  sjhjje  from qsdb.qs_jl_mfsbxxzb_seller where sbbh=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sjhjje = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            
            while(rs.next()){
            	sjhjje = rs.getString("sjhjje");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }  	
        return sjhjje;
    }    
    
    
}
