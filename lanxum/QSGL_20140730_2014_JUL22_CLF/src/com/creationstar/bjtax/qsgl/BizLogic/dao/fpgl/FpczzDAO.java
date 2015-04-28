package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Kplx;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdkBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * @author tutu
 * 发票开票主表DAO
 * 2013-05-10(1)
 */
public class FpczzDAO extends BaseDAO {

	/**
     * 插入一条发票操作主表数据记录
     * @param fpczz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fpczz fpczz, Connection conn) throws
            SQLException {
        String sql = "insert into fpdb.fp_jl_fpkpzb (fpzldm,fphm,fpkfdm,kprq,fkdw,skdw,dkdwmc,kplxdm,sphm,tpfpzldm,tpfphm,kpr,sfyjbltp,cjr,cjrq,lrr,lrrq,swjgzzjgdm,bz,dcbz,hyfl,fwcqzh,fwzldz) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpczz.fpzldm);
            ps.setString(2, fpczz.fphm);
            ps.setString(3, fpczz.fpkfdm);
            ps.setTimestamp(4, fpczz.kprq);
            ps.setString(5, fpczz.fkdw);
            ps.setString(6, fpczz.skdw);
            ps.setString(7, fpczz.dkdwmc);
            ps.setString(8, fpczz.kplxdm);
            ps.setString(9, fpczz.sphm);
            ps.setString(10, fpczz.tpfpzldm);
            ps.setString(11, fpczz.tpfphm);
            ps.setString(12, fpczz.kpr);
            ps.setString(13, fpczz.sfyjbltp);
            ps.setString(14, fpczz.cjr);
            ps.setTimestamp(15, fpczz.cjrq);
            ps.setString(16, fpczz.lrr);
            ps.setTimestamp(17, fpczz.lrrq);
            ps.setString(18, fpczz.swjgzzjgdm);
            ps.setString(19, fpczz.bz);
            ps.setString(20, fpczz.dcbz);
            ps.setString(21, fpczz.hyfl);
            ps.setString(22, fpczz.fwcqzh);
            ps.setString(23, fpczz.fwzldz);
            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 更新一条发票操作主表数据记录
     * @param fpczz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void update(Fpczz fpczz, Connection conn) throws
            SQLException {
        String sql = "update  fpdb.fp_jl_fpkpzb set fpzldm=?,fphm=?,fpkfdm=?,kprq=?,fkdw=?,skdw=?,dkdwmc=?,kplxdm=?,sphm=?,tpfpzldm=?,tpfphm=?,kpr=?,sfyjbltp=?,cjr=?,cjrq=?,lrr=?,lrrq=?,swjgzzjgdm=?,bz=?,dcbz=?,hyfl=?,fwcqzh =?,fwzldz =?  where fpzldm = ? and fphm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpczz.fpzldm);
            ps.setString(2, fpczz.fphm);
            ps.setString(3, fpczz.fpkfdm);
            ps.setTimestamp(4, fpczz.kprq);
            ps.setString(5, fpczz.fkdw);
            ps.setString(6, fpczz.skdw);
            ps.setString(7, fpczz.dkdwmc);
            ps.setString(8, fpczz.kplxdm);
            ps.setString(9, fpczz.sphm);
            ps.setString(10, fpczz.tpfpzldm);
            ps.setString(11, fpczz.tpfphm);
            ps.setString(12, fpczz.kpr);
            ps.setString(13, fpczz.sfyjbltp);
            ps.setString(14, fpczz.cjr);
            ps.setTimestamp(15, fpczz.cjrq);
            ps.setString(16, fpczz.lrr);
            ps.setTimestamp(17, fpczz.lrrq);
            ps.setString(18, fpczz.swjgzzjgdm);
            ps.setString(19, fpczz.bz);
            ps.setString(20, fpczz.dcbz);
            ps.setString(21, fpczz.hyfl);
            ps.setString(22, fpczz.fwcqzh);
            ps.setString(23, fpczz.fwzldz);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 更新一条发票操作主表退票数据记录
     * @param fpczz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void updateTp(Fpczz fpczz, Connection conn) throws
            SQLException {
        String sql = "update  fpdb.fp_jl_fpkpzb set tpfpzldm=?,tpfphm=?,sfyjbltp=?,lrr=?,lrrq=? where fpzldm = ? and fphm = ? and fpkfdm = ? ";
        
        //System.out.println("updateTp SQL:" +sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpczz.tpfpzldm);
            ps.setString(2, fpczz.tpfphm);
            ps.setString(3, fpczz.sfyjbltp);
            ps.setString(4, fpczz.lrr);
            ps.setTimestamp(5, fpczz.lrrq);
            ps.setString(6, fpczz.fpzldm);
            ps.setString(7, fpczz.fphm);
            ps.setString(8, fpczz.fpkfdm);
            
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    
    /**
     * 更新一条发票操作主表导出数据记录
     * @param fpczz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void updateDc(Fpczz fpczz, Connection conn) throws
            SQLException {
        String sql = "update  fpdb.fp_jl_fpkpzb set dcbz=?,lrr=?,lrrq=? where fpzldm = ? and fphm = ? and fpkfdm = ? and dcbz='0' ";
        
        //System.out.println("updateDc SQL:" +sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpczz.dcbz);
            ps.setString(2, fpczz.lrr);
            ps.setTimestamp(3, fpczz.lrrq);
            ps.setString(4, fpczz.fpzldm);
            ps.setString(5, fpczz.fphm);
            ps.setString(6, fpczz.fpkfdm);
            
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 删除多条发票操作主表数据记录
     * @param qswszzList 数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList fpczzList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  fpdb.fp_jl_fpkpzb where fpzldm = ? and fphm = ?  ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fpczzList.size(); i++) 
            {
            	Fpczz fpczz = (Fpczz) fpczzList.get(i);
            	
                ps.setString(1, fpczz.fpzldm);
                ps.setString(2, fpczz.fphm);
                
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 撤销发票使用
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  fpdb.fp_jl_fpkpzb  where ");
        sql.append(condition);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 根据主键获取发票操作主数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FpczzList = new ArrayList();
        String sql = "select fpzldm,fphm,fpkfdm,kprq,fkdw,skdw,dkdwmc,kplxdm,sphm,tpfpzldm,tpfphm,kpr,sfyjbltp,cjr,cjrq,lrr,lrrq,swjgzzjgdm,bz,dcbz,hyfl,fwcqzh,fwzldz from fpdb.fp_jl_fpkpzb " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	Fpczz fpczz = new Fpczz();
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setKprq(rs.getTimestamp("kprq"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	
            	FpczzList.add(fpczz);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczzList;
    }
    
    /**
     * 根据主键获取发票操作主数据值对象
     * @param qswszz 契税完税证主数据值对象
     * @param conn 数据库连接对象
     * @return 数据值对象
     * @throws SQLException
     */
    public static Object get(Fpczz fpczz, Connection conn) throws
            SQLException {
    	Fpczz fpczz1 = null;
        String sql = "select fpzldm,fphm,fpkfdm,kprq,fkdw,skdw,dkdwmc,kplxdm,sphm,tpfpzldm,tpfphm,kpr,sfyjbltp,cjr,cjrq,lrr,lrrq,swjgzzjgdm,bz,dcbz,hyfl,fwcqzh,fwzldz from fpdb.fp_jl_fpkpzb  where fpzldm = ? and fphm = ?  ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fpczz.fpzldm);
            ps.setString(2, fpczz.fphm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	fpczz1 = new Fpczz();
            	fpczz1.setFpzldm(rs.getString("fpzldm"));
            	fpczz1.setFphm(rs.getString("fphm"));
            	fpczz1.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz1.setKprq(rs.getTimestamp("kprq"));
            	fpczz1.setFkdw(rs.getString("fkdw"));
            	fpczz1.setSkdw(rs.getString("skdw"));
            	fpczz1.setDkdwmc(rs.getString("dkdwmc"));
            	fpczz1.setKplxdm(rs.getString("kplxdm"));
            	fpczz1.setSphm(rs.getString("sphm"));
            	fpczz1.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz1.setTpfphm(rs.getString("tpfphm"));
            	fpczz1.setKpr(rs.getString("kpr"));
            	fpczz1.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz1.setCjr(rs.getString("cjr"));
            	fpczz1.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz1.setLrr(rs.getString("lrr"));
            	fpczz1.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz1.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz1.setBz(rs.getString("bz"));
            	fpczz1.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));

            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return fpczz1;
    }
    
    /**
     * 根据查询条件获取发票打印数据值对象（代开）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFpdkPrint(String condition, Connection conn) throws
            Exception {
        ArrayList FpczAllList = new ArrayList();
        String sql = "select a.*,b.fpzldm mx_fpzldm,b.fphm mx_fphm,b.fpkfdm mx_fpkfdm,b.kprq mx_kprq, " +
        		"b.pm mx_pm,b.dj mx_dj,b.sl mx_sl,b.je mx_je,b.swjgzzjgdm mx_swjgzzjgdm from fpdb.fp_jl_fpkpzb a,  " +
        		"fpdb.fp_jl_fpkpmx b where a.fpkfdm = b.fpkfdm  and a.fphm = b.fphm and a.fpzldm = b.fpzldm " +
        		"and a.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"'" + condition;
        
        //Debug.out("queryFpdkPrint()'s SQL : " + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpdkBO fpdkBO = new FpdkBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setKprq(rs.getTimestamp("kprq"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	
            	fpczmx.setFpzldm(rs.getString("mx_fpzldm"));
            	fpczmx.setFphm(rs.getString("mx_fphm"));
            	fpczmx.setFpkfdm(rs.getString("mx_fpkfdm"));
            	fpczmx.setKprq(rs.getTimestamp("mx_kprq"));
            	fpczmx.setPm(rs.getString("mx_pm"));
            	fpczmx.setDj(rs.getBigDecimal("mx_dj"));
            	fpczmx.setSl(rs.getBigDecimal("mx_sl"));
            	fpczmx.setJe(rs.getBigDecimal("mx_je"));
            	fpczmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
            	
            	fpdkBO.setFpczz(fpczz);
            	fpdkBO.setFpczmx(fpczmx);
            	
            	FpczAllList.add(fpdkBO);
           }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczAllList;
    }
    
    /**
     * 根据查询条件获取发票打印数据值对象（重打）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFpcdPrint(String condition, Connection conn) throws
            Exception {
        ArrayList FpczAllList = new ArrayList();
        String sql = "select a.*,b.fpzldm mx_fpzldm,b.fphm mx_fphm,b.fpkfdm mx_fpkfdm,b.kprq mx_kprq, " +
        		"b.pm mx_pm,b.dj mx_dj,b.sl mx_sl,b.je mx_je,b.swjgzzjgdm mx_swjgzzjgdm from fpdb.fp_jl_fpkpzb a,  " +
        		"fpdb.fp_jl_fpkpmx b where a.fpkfdm = b.fpkfdm  and a.fphm = b.fphm and a.fpzldm = b.fpzldm " +
        		"and a.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"'" + condition;
        
        //Debug.out("queryFpcdPrint()'s SQL : " + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpcdBO fpcdBO = new FpcdBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setKprq(rs.getTimestamp("kprq"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	
            	fpczmx.setFpzldm(rs.getString("mx_fpzldm"));
            	fpczmx.setFphm(rs.getString("mx_fphm"));
            	fpczmx.setFpkfdm(rs.getString("mx_fpkfdm"));
            	fpczmx.setKprq(rs.getTimestamp("mx_kprq"));
            	fpczmx.setPm(rs.getString("mx_pm"));
            	fpczmx.setDj(rs.getBigDecimal("mx_dj"));
            	fpczmx.setSl(rs.getBigDecimal("mx_sl"));
            	fpczmx.setJe(rs.getBigDecimal("mx_je"));
            	fpczmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
            	
            	fpcdBO.setFpczz(fpczz);
            	fpcdBO.setFpczmx(fpczmx);
            	
            	FpczAllList.add(fpcdBO);
           }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczAllList;
    }
    
    /**
     * 根据查询条件获取发票打印数据值对象（退票）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFptpPrint(String condition, Connection conn) throws
            Exception {
        ArrayList FpczAllList = new ArrayList();
        String sql = "select a.*,b.fpzldm mx_fpzldm,b.fphm mx_fphm,b.fpkfdm mx_fpkfdm,b.kprq mx_kprq, " +
        		"b.pm mx_pm,b.dj mx_dj,b.sl mx_sl,b.je mx_je,b.swjgzzjgdm mx_swjgzzjgdm from fpdb.fp_jl_fpkpzb a,  " +
        		"fpdb.fp_jl_fpkpmx b where a.fpkfdm = b.fpkfdm  and a.fphm = b.fphm and a.fpzldm = b.fpzldm " +
        		"and a.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"'" + condition;
        
        //Debug.out("queryFpcdPrint()'s SQL : " + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FptpBO fptpBO = new FptpBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setKprq(rs.getTimestamp("kprq"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	
            	fpczmx.setFpzldm(rs.getString("mx_fpzldm"));
            	fpczmx.setFphm(rs.getString("mx_fphm"));
            	fpczmx.setFpkfdm(rs.getString("mx_fpkfdm"));
            	fpczmx.setKprq(rs.getTimestamp("mx_kprq"));
            	fpczmx.setPm(rs.getString("mx_pm"));
            	fpczmx.setDj(rs.getBigDecimal("mx_dj"));
            	fpczmx.setSl(rs.getBigDecimal("mx_sl"));
            	fpczmx.setJe(rs.getBigDecimal("mx_je"));
            	fpczmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
            	
            	fptpBO.setFpczz(fpczz);
            	fptpBO.setFpczmx(fpczmx);
            	
            	FpczAllList.add(fptpBO);
           }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczAllList;
    }
    
    /**
     * 根据查询条件获取发票退票数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFptp(String condition, Connection conn) throws
            Exception {
    	ArrayList FpAllList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c  " +
        		"where b.fpkfdm = c.fpkfdm  and b.fpzldm = c.fpzldm and b.fphm = c.fphm  " +
        		"and a.pizzldm =b.fpzldm  and a.pzhm = b.fphm and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' " + 
        		condition + "order by b.fpzldm,b.fphm" ;
        
        //Debug.out("queryFptp()'s SQL : " + sql.toString());
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FptpBO fptpBO = new FptpBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	
            	fpczmx.setPm(rs.getString("pm"));
            	fpczmx.setDj(rs.getBigDecimal("dj"));
            	fpczmx.setSl(rs.getBigDecimal("sl"));
            	fpczmx.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb.setXh(rs.getString("xh"));
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb.setCjr(rs.getString("cjr"));
            	htypzdzgxb.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb.setLrr(rs.getString("lrr"));
            	htypzdzgxb.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb.setSbbh(rs.getString("sbbh"));
            	
            	fptpBO.setFpczz(fpczz);
            	fptpBO.setFpczmx(fpczmx);
            	fptpBO.setHtypzdzgxb(htypzdzgxb);
            	
            	FpAllList.add(fptpBO);
           }
            
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpAllList;
    }
    
    /**
     * 根据查询条件获取发票重打数据值对象（重打）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFpcd(String condition, Connection conn) throws
            Exception {
    	ArrayList FpAllList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c  " +
        		"where b.fpkfdm = c.fpkfdm  and b.fpzldm = c.fpzldm and b.fphm = c.fphm  " +
        		"and a.pizzldm(+) =b.fpzldm  and a.pzhm(+) = b.fphm " + 
        		condition + "order by b.fpzldm,b.fphm" ;
        
        //Debug.out("queryFpcd()'s SQL : " + sql.toString());
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpcdBO fpcdBO = new FpcdBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	
            	fpczmx.setPm(rs.getString("pm"));
            	fpczmx.setDj(rs.getBigDecimal("dj"));
            	fpczmx.setSl(rs.getBigDecimal("sl"));
            	fpczmx.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb.setXh(rs.getString("xh"));
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb.setCjr(rs.getString("cjr"));
            	htypzdzgxb.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb.setLrr(rs.getString("lrr"));
            	htypzdzgxb.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb.setSbbh(rs.getString("sbbh"));
            	
            	fpcdBO.setFpczz(fpczz);
            	fpcdBO.setFpczmx(fpczmx);
            	fpcdBO.setHtypzdzgxb(htypzdzgxb);
            	
            	FpAllList.add(fpcdBO);
           }
            
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpAllList;
    }
    
    
    /**
     * 根据查询条件获取发票导出数据值对象（导出查询）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFpdc(String condition, Connection conn) throws
            Exception {
    	ArrayList FpAllList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c  " +
        		"where b.fpkfdm = c.fpkfdm(+)  and b.fpzldm = c.fpzldm(+) and b.fphm = c.fphm(+)  " +
        		"and a.pizzldm(+) =b.fpzldm  and a.pzhm(+) = b.fphm " + 
        		condition + " order by b.dcbz,b.fphm";
        
        //Debug.out("queryFpdc()'s SQL : " + sql.toString());
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpdcBO fpdcBO = new FpdcBO();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	
            	fpczmx.setPm(rs.getString("pm"));
            	fpczmx.setDj(rs.getBigDecimal("dj"));
            	fpczmx.setSl(rs.getBigDecimal("sl"));
            	fpczmx.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb.setXh(rs.getString("xh"));
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb.setCjr(rs.getString("cjr"));
            	htypzdzgxb.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb.setLrr(rs.getString("lrr"));
            	htypzdzgxb.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb.setSbbh(rs.getString("sbbh"));
            	
            	fpdcBO.setFpczz(fpczz);
            	fpdcBO.setFpczmx(fpczmx);
            	fpdcBO.setHtypzdzgxb(htypzdzgxb);
            	
            	FpAllList.add(fpdcBO);
           }
            
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpAllList;
    }
    
    /**
     * 根据查询条件获取发票导出数据值对象（导出数据）
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws Exception 
     */
    public static ArrayList queryFpdcData(String condition, Connection conn) throws
            Exception {
    	ArrayList FpAllList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je,dm1.jsjdm,dm2.kplxmc  " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c, dmdb.gy_dm_swjgzzjg dm1, dmdb.fp_dm_kplx dm2  " +
        		"where b.fpkfdm = c.fpkfdm(+)  and b.fpzldm = c.fpzldm(+) and b.fphm = c.fphm(+)  " +
        		"and a.pizzldm(+) =b.fpzldm  and a.pzhm(+) = b.fphm and b.swjgzzjgdm = dm1.swjgzzjgdm and b.kplxdm = dm2.kplxdm " + 
        		condition + " order by b.dcbz,b.fphm";
        
        //Debug.out("queryFpdc()'s SQL : " + sql.toString());
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpdcBO fpdcBO = new FpdcBO();
            	
            	Kplx kplx = new Kplx();
            	Swjgzzjg swjgzzjg = new Swjgzzjg();
            	Fpczz fpczz = new Fpczz();
            	Fpczmx fpczmx = new Fpczmx();
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	kplx.setKplxmc(rs.getString("kplxmc"));
            	swjgzzjg.setJsjdm(rs.getString("jsjdm"));
            	
            	fpczz.setFpkfdm(rs.getString("fpkfdm"));
            	fpczz.setFpzldm(rs.getString("fpzldm"));
            	fpczz.setFphm(rs.getString("fphm"));
            	fpczz.setFkdw(rs.getString("fkdw"));
            	fpczz.setSkdw(rs.getString("skdw"));
            	fpczz.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz.setFwzldz(rs.getString("fwzldz"));
            	fpczz.setKplxdm(rs.getString("kplxdm"));
            	fpczz.setSphm(rs.getString("sphm"));
            	fpczz.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz.setTpfphm(rs.getString("tpfphm"));
            	fpczz.setKprq(rs.getTimestamp("kprq"));
            	fpczz.setKpr(rs.getString("kpr"));
            	fpczz.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz.setCjr(rs.getString("cjr"));
            	fpczz.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz.setLrr(rs.getString("lrr"));
            	fpczz.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz.setBz(rs.getString("bz"));
            	fpczz.setDcbz(rs.getString("dcbz"));
            	fpczz.setHyfl(rs.getString("hyfl"));
            	fpczz.setDkdwmc(rs.getString("dkdwmc"));
            	
            	fpczmx.setPm(rs.getString("pm"));
            	fpczmx.setDj(rs.getBigDecimal("dj"));
            	fpczmx.setSl(rs.getBigDecimal("sl"));
            	fpczmx.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb.setXh(rs.getString("xh"));
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb.setCjr(rs.getString("cjr"));
            	htypzdzgxb.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb.setLrr(rs.getString("lrr"));
            	htypzdzgxb.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb.setSbbh(rs.getString("sbbh"));
            	
            	fpdcBO.setFpczz(fpczz);
            	fpdcBO.setFpczmx(fpczmx);
            	fpdcBO.setHtypzdzgxb(htypzdzgxb);
            	fpdcBO.setSwjgzzjg(swjgzzjg);
            	fpdcBO.setKplx(kplx);
            	
            	FpAllList.add(fpdcBO);
           }
            
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpAllList;
    }
    
}
