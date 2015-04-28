package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;

/**
 * @author zhangyj
 * 获取初始化税种税目DAO
 * 
 */
public class SzsmInitDAO extends BaseDAO {
    
    
    /**
     * 查询可申报税目对应附加税
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证主数据值对象的集合
     * @throws SQLException
     */
    public String queryFjs(Connection conn) throws
            SQLException {
    	StringBuffer sb = new StringBuffer();
    	sb.append("\nvar szsmlist_add = [");
        String sql = "select b.szsmdm,b.fjsszsmdm from dmdb.v_qs_dm_clfjyszsmdm a,sbdb.sb_jl_szsmyfjs b where a.szsmdm=b.szsmdm";
        PreparedStatement ps = null;
//        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	sb.append("[\""+rs.getString("szsmdm")+"\",\""+rs.getString("fjsszsmdm")+"\"],");
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return sb.substring(0, sb.length()-1)+"];";
    }
    
    /**
     * 查询可申报税目name及value
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证主数据值对象的集合
     * @throws SQLException
     */
    public String querySzsm(Connection conn,String conditions) throws
            SQLException {
    	
    	StringBuffer name = new StringBuffer();
    	name.append("\nvar szsmdm_name = [");
    	StringBuffer value = new StringBuffer();
    	value.append("\nvar szsmdm_value = ["); 
    	
    	StringBuffer name_all= new StringBuffer();
    	name_all.append("\nvar szsmdm_name_all = [");
    	StringBuffer vale_all = new StringBuffer();
    	vale_all.append("\nvar szsmdm_value_all = [");
    	
    	
        String st = "";
        String sql = "select a.szsmdm||' '||a.szsmmc name,a.szsmdm value from dmdb.v_qs_dm_clfjyszsmdm a where length(a.szsmdm)=6 "+conditions;
        PreparedStatement ps = null;
//        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	name.append("\""+rs.getString("name")+"\",");
            	name_all.append("\""+rs.getString("name")+"\",");
            	
            	value.append("\""+rs.getString("value")+"\","); 
            	vale_all.append("\""+rs.getString("value")+"\","); 
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return value.substring(0, value.toString().length()-1)+"];" + name.substring(0, name.toString().length()-1)+"];" +vale_all.substring(0, vale_all.toString().length()-1)+"];"+name_all.substring(0, name_all.toString().length()-1)+"];";
    }
    
    
    /**
     * 查询可申报税目页面显示数据
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证主数据值对象的集合
     * @throws SQLException
     */
    public String querySzsmlist(Connection conn,String skssksrq,String skssjsrq,String conditions) throws
            SQLException {
    	
    	String sffjs="";
    	String sl="";
    	StringBuffer szsmlist = new StringBuffer();
    	szsmlist.append("\nvar szsmlist = [");	
    	
        String sql = "select a.szsmdm,b.szsmmc szmc,a.szsmmc,'' skssksrq,'' skssjsrq,'' kssl,'' jsje,'' sjse,substr(a.szsmdm,0,2) szdm,a.sffjs,a.szsmdm szsmdm_old,a.asljbs,a.sl from dmdb.v_qs_dm_clfjyszsmdm a,dmdb.sb_dm_szsm b where length(a.szsmdm)=6 and substr(a.szsmdm,0,2)=b.szsmdm"+conditions;
        PreparedStatement ps = null;
//        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {     
            	if(!(rs.getString("sffjs")==null||rs.getString("sffjs").equals("")))
            	{
            		sffjs=rs.getString("sffjs");
            	}
            	if(!(rs.getString("sl")==null||rs.getString("sl").equals("")))
            	{
            		sl=String.valueOf(rs.getDouble("sl"));
            	}            	
            	szsmlist.append("[\""+rs.getString("szsmdm")+"\",\""+rs.getString("szmc")+"\",\""+rs.getString("szsmmc")+"\",\"");
            	szsmlist.append(skssksrq+"\",\""+skssjsrq+"\",\"\",\"\",\"\",\"");
            	szsmlist.append(rs.getString("szdm")+"\",\""+sffjs+"\",\""+rs.getString("szsmdm_old")+"\",\"");
            	szsmlist.append(rs.getString("asljbs")+"\",\""+sl+"\",\"\",\"\"],");
            	
            	sffjs="";
            	sl="";            	
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return szsmlist.substring(0, szsmlist.length()-1)+"];";
    }    
}
