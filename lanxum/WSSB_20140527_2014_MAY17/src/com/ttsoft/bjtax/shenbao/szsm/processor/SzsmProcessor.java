package com.ttsoft.bjtax.shenbao.szsm.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.JkInfor;
import com.ttsoft.bjtax.shenbao.model.client.WtdwInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.szsm.SzsmActionConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author Ding Chenggang
 * @version 1.0
 */
public class SzsmProcessor implements Processor
{
    public SzsmProcessor()
    {
    }

    /**
     * @param vo VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        String jsjdm = vo.getUserData().yhid;
        
        switch(vo.getAction())
        {
            case SzsmActionConstant.REFRESH_FAVORITE:
                refreshFavorite(jsjdm, (Map)vo.getData());
                return null;

            case SzsmActionConstant.QUERY_SZSMTREE_INFO:
                return this.getSzsmTreeInfo(vo);

            case 3:
                return this.getJkInfor((SWDJJBSJ)vo.getData());
                
            case SzsmActionConstant.QUERY_WTDW_INFO:
                return this.getWtdwInfor((SWDJJBSJ)vo.getData());    
           
            default:
            	
                throw new SystemException("NoSuchMethod");
        }
    }
    /**
     * 生成税种税目表
     */
    private Map getSzsmMap(SWDJJBSJ djsj,Connection conn) throws BaseException{
    	 
       
             
             ORManager orMgr = DBResource.getORManager();
             DBAccess db = new DBAccess(conn, orMgr);

             int size = 0;
             

             // 查询税种税目
             Vector cri = new Vector(1);
             cri.add("1=1 order by szsmdm");
             List szsmList = db.query(Szsm.class, cri);
             
             size = szsmList.size();
             Map szsmMap = new HashMap(size);
             Map szsmMap_available = new HashMap(size);
             for (int i=0; i<size; i++)
             {
                 Szsm s = (Szsm)szsmList.get(i);
                 szsmMap.put(s.getSzsmdm(), s);

                 if (s.getZxbs() != null && s.getZxbs().equals("0"))
                 {
                     szsmMap_available.put(s.getSzsmdm(), s);
                    
                 }
             }
             return szsmMap_available;
          
    }
   
    private void refreshFavorite(String jsjdm, Map data) throws BaseException
    {
        Connection con = null;
        PreparedStatement pst = null;
        try
        {
            String swjgzzjgdm = (String)data.get("swjgzzjgdm");
            String qxdm = swjgzzjgdm.substring(0, 2);

            con = DBResource.getConnection();
//            pst = con.prepareStatement("delete from sbdb.sb_jl_sqsb_tmp where jsjdm = ? and qxdm = ?");
            pst = con.prepareStatement("delete from sbdb.sb_jl_sqsb_tmp where jsjdm = ? ");

            pst.setString(1, jsjdm);
         //   pst.setString(2, qxdm);

            pst.executeUpdate();

            List sqsbtmpList = (List)data.get("favorite");

            if(sqsbtmpList == null || sqsbtmpList.size() == 0)
            {
                return;
            }

            ORManager orMgr = DBResource.getORManager();
            for(int i = 0; i < sqsbtmpList.size(); i++)
            {
                orMgr.makePersistent(0, con, sqsbtmpList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            try { pst.close();  }   catch (Exception ex) {  }
            DBResource.destroyConnection(con);
        }
    }

    /**
     * 查询常用申报税目（如果税款类型为“委托代征”，常用税种税目为空,确保委托代征税种税目唯一性 tujb 201406）
     * @param djsj 登记数据
     * @param con 数据库连接
     * @return List Sqsbtmp列表
     * @throws BaseException
     */
    private List queryFavoriteSzsm(SWDJJBSJ djsj,String sklxdm, Connection con)
        throws BaseException
    {
        ResultSet rs = null;
        PreparedStatement pst = null;

        try
        {
            List ret = new ArrayList();

            String jsjdm = djsj.getJsjdm();
            String qxdm = djsj.getSwjgzzjgdm().substring(0, 2);
            String sql = "";
            if(sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
            {
            	sql ="select a.szsmdm, b.szsmmc from SBDB.SB_JL_SQSB_TMP a, DMDB.SB_DM_SZSM b "
                    + "where a.jsjdm='" + jsjdm + "' and a.szsmdm=b.szsmdm and b.zxbs='0' and b.szsmdm is null";
            }
            else
            {
            	sql ="select a.szsmdm, b.szsmmc from SBDB.SB_JL_SQSB_TMP a, DMDB.SB_DM_SZSM b "
                    + "where a.jsjdm='" + jsjdm + "' and a.szsmdm=b.szsmdm and b.zxbs='0'";
            }

            
            //added by zhangyj 20100517 常用税种税目排序问题
            sql = sql+" order by a.szsmdm";
            // added by zhangyj 20100517 end 

            pst = con.prepareStatement(sql);
            for (rs = pst.executeQuery(); rs.next(); )
            {
                Sqsbtmp tmp = new Sqsbtmp();
                tmp.setSzsmdm(rs.getString(1));
                tmp.setSzsmmc(rs.getString(2));

                ret.add(tmp);
            }

            return ret;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            try   {   rs.close();   }  catch(Exception e)    {  }
            try   {   pst.close();   }  catch(Exception e)    {  }
        }
    }
/**
 * 
 * 查询要过滤的税目
 */
    private List queryToBeFilterszsm(SWDJJBSJ djsj, Connection conn) throws BaseException
    {
        ResultSet rs = null;
        PreparedStatement pst = null;

        List result = new ArrayList();

        try
        {
            String djzclxdm = djsj.getDjzclxdm();
            String jsjdm=djsj.getJsjdm();
            // 在征期日历中特殊征期类型的数据单独处理。;
            String sql =
               "SELECT SZSMDM FROM "
            +  "( SELECT A.SZSMDM, B.SZSM "
            +      "FROM (SELECT SZSMDM, CCBS "
            +              "FROM DMDB.SB_DM_SZSM "
            +              "WHERE CCBS = '2') A, "
            +           "(SELECT /*+ index(t IDX_SB_JL_ZQRL_ND)*/DISTINCT(SZSMDM) AS SZSM "
            +              "FROM SBDB.SB_JL_ZQRL t "
            +              "WHERE ZQQSRQ <= ? "
            +                "AND ZQZZRQ >= ? "
            +	             "AND ZQLXDM IN (SELECT ZQLXDM FROM DMDB.SB_DM_ZQLX WHERE ZQLXDM <> '10') "  //add 2008-06-17 排除特殊征期
            +                "AND DJZCLXDM = ? " 
            +	             "AND ND = ? " 
            //add 2008-06-17 begin  增加特殊征期的征期数据
            +                "UNION SELECT /*+ index(t IDX_SB_JL_ZQRL_ND)*/DISTINCT(T2.SZSMDM) AS SZSM "
            +                 "FROM SFDB.SF_JL_SZJNCSMX T1, SBDB.SB_JL_ZQRL T2 " 
            +                "WHERE T1.JSJDM = ? "
            +                  "AND T1.RDND = ? "							       
            +                  "AND T1.ZQLXDM IN (SELECT ZQLXDM FROM DMDB.SB_DM_ZQLX WHERE ZQLXDM = '10') "    
            +                  "AND T2.SZSMDM LIKE T1.SZSMDM||'%' "
            +                  "AND T2.ZQLXDM = T1.ZQLXDM "
            +                  "AND T2.ZQQSRQ <= ? "
            +                  "AND T2.ZQZZRQ >= ? "
            +                  "AND T2.ND = ? "
            //add 2008-06-17 end
            +                ") B "                       
            +      "WHERE A.SZSMDM = B.SZSM(+)) "
            +  "WHERE SZSM IS NULL";

            pst = conn.prepareStatement(sql);
            
            Timestamp now = TinyTools.second2Day(new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(1, now);
            pst.setTimestamp(2, now);
            pst.setString(3, djzclxdm);
            pst.setString(4, String.valueOf(now.getYear()+1900));
            //add 2008-06-17 begin
            pst.setString(5, jsjdm);
            pst.setString(6, String.valueOf(now.getYear()+1900));
            pst.setTimestamp(7, now);
            pst.setTimestamp(8, now);
            pst.setString(9, String.valueOf(now.getYear()+1900));
            //add 2008-06-17 end

            for(rs = pst.executeQuery(); rs.next(); )
            {
                Szsm tmp = new Szsm();
                tmp.setSzsmdm(rs.getString(1));
                result.add(tmp);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            try   {   rs.close();   }  catch(Exception e)    {  }
            try   {   pst.close();   }  catch(Exception e)    {  }
        }
       // System.out.println("查询出"+result.size()+"个要过滤的叶记录");
        return result;
    }

   

    /**
     * 取得税种税目树信息（增加税款类型“委托代征”调整税种税目 tujb 201406）
     * @param djsj 登记数据
     * @return Map
     * @throws BaseException
     */
    private Map getSzsmTreeInfo(VOPackage vo) throws BaseException
    {
        Connection conn = null;
        HashMap dataMap = (HashMap)vo.getData();
        
        SWDJJBSJ djsj = (SWDJJBSJ)dataMap.get("djsj");
        String sklxdm = (String)dataMap.get("sklxdm");
        try
        {
            Map result = new HashMap(2);

            conn = DBResource.getConnection();
            //所有的税种税目代码表
            result.put("szsmMap", this.getSzsmMap(djsj, conn));
           //获得税种税目LIST
            result.put("szsm", this.getSzsmRawMaterial(djsj, sklxdm, conn));
            //获得要过滤的税种税目
            result.put("toBeFilterszsm", this.queryToBeFilterszsm(djsj, conn));
            //获得常用税种税目
            result.put("favorite", this.queryFavoriteSzsm(djsj, sklxdm, conn));           
            //获得提示税种税目 
            //start added by zhangyj 20120905
            result.put("toBeAlertszsm", this.queryToBeAlertszsm(djsj, conn));
            //end added by zhangyj 20120905
            
            //获得提示税种税目 
            //start added by tujb 201406
            result.put("wtdzszsm", this.queryWtdzszsm(djsj, conn));
            //end added by zhangyj 201406
            return result;
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

   
    /**
     *  获取税种税目（如果税款类型为“委托代征”,查询委托代征认定表的税种税目，否则查询税种税目表 tujb 201406）
     * @param djsj 计算机代码
     * @param sklxdm 税款类型
     * @param conn
     * @return
     * @throws BaseException
     */
    private List getSzsmRawMaterial(SWDJJBSJ djsj, String sklxdm, Connection conn) throws BaseException
    {
    	ResultSet rs = null;
        PreparedStatement pstmt = null;

        try
        {
            String djzclxdm = djsj.getDjzclxdm();
            String jsjdm = djsj.getJsjdm();

            List szsm = new ArrayList();
            List sklxsmList = new ArrayList();
            
            List wtdzsmList = this.getWtdzSmInfor(djsj);
        	StringBuffer selectOneBuff = new StringBuffer();
        	StringBuffer selectTwoBuff = new StringBuffer();
        	
        	/*获取委托单位代征的税种税目，判断查询结果中是否存在税款类型为正常的税目要显示 modified by Junbing Tu 2014.07*/
        	if(wtdzsmList != null && wtdzsmList.size() > 0)
        	{
        		for(int i=0; i<wtdzsmList.size(); i++)
            	{
        			WtdwInfor temp = null;
        			WtdwInfor tempSm = (WtdwInfor) wtdzsmList.get(i);

        			//1.获取税款类型为“正常”时，查询的税种税目及查询SQL
        			if(tempSm.getSklxbs() != null && tempSm.getSklxbs().equals(CodeConstant.WSSB_YSSB_SKLXBS))
        			{
        				temp = new WtdwInfor();
        				temp.setSzsmdm(tempSm.getSzsmdm());
        				sklxsmList.add(temp);
        			}
        			
        			//2.税款类型为“委托代征”时，查询的税种税目SQL，选择委托代征税款类型时使用该税目
        			if(sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
        			{
        				if(i == 0){
        					selectOneBuff.append("( b.szsmdm like '"+tempSm.getSzsmdm()+"%'");
        					selectTwoBuff.append("( d.szsmdm like '"+tempSm.getSzsmdm()+"%'");
        				}else{
        					selectOneBuff.append("or b.szsmdm like '"+tempSm.getSzsmdm()+"%' ");
        					selectTwoBuff.append("or d.szsmdm like '"+tempSm.getSzsmdm()+"%' ");
        				}
        				
        				if(i == wtdzsmList.size() -1){
        					selectOneBuff.append(") and");
        					selectTwoBuff.append(") and");
        				}
        			}
            	}
        		
        		/*获取税款类型为”正常“的税种税目，选择正常税款类型时使用该税目*/
        		if((sklxsmList != null && sklxsmList.size() > 0) && (sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_ZCJK)))
        		{
        			for(int i=0; i<sklxsmList.size(); i++)
                	{
            			WtdwInfor tempSkSm = (WtdwInfor) sklxsmList.get(i);
            			
            			if(i == 0){
        					selectOneBuff.append("( b.szsmdm like '"+tempSkSm.getSzsmdm()+"%'");
        					selectTwoBuff.append("( d.szsmdm like '"+tempSkSm.getSzsmdm()+"%'");
        				}else{
        					selectOneBuff.append("or b.szsmdm like '"+tempSkSm.getSzsmdm()+"%' ");
        					selectTwoBuff.append("or d.szsmdm like '"+tempSkSm.getSzsmdm()+"%' ");
        				}
        				
        				if(i == sklxsmList.size() -1){
        					selectOneBuff.append(") and");
        					selectTwoBuff.append(") and");
        				}
                	}
        		}
        	}

            StringBuffer sql = new StringBuffer();
            if(sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
            {
            	sql.append("select a.szsmdm, a.szsmmc, a.fjddm, a.zqlxdm, a.ccbs ").
        		append(" from DMDB.SB_DM_SZSM a ").
        		append(" where a.zxbs = '0' and (a.sffjs <>'3' or a.sffjs is null) and a.ccbs = '2'").
        		append(" and a.szsmdm in (select b.szsmdm from DMDB.SB_DM_SZSM b ").
        		append(" where ").append(selectOneBuff).
        		append(" b.szsmdm in (select distinct(d.szsmdm) from sfdb.sf_jl_wtdwmx c, dmdb.sb_dm_szsm d  where c.jsjdm = ? and ").
        		append(" d.szsmdm like c.szsmdm||'%' and (").append(selectTwoBuff).append(" length(c.szsmdm) = 6))) " ).
        		append(" order by szsmdm ");
            	
            	//System.out.println("wtdz sql："+sql);
            	pstmt = conn.prepareStatement(sql.toString());
            	pstmt.setString(1, jsjdm);
            }
            else if((sklxsmList != null && sklxsmList.size() > 0) && (sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_ZCJK)))
            {
            	sql.append("select a.szsmdm, a.szsmmc, a.fjddm, a.zqlxdm, a.ccbs ").
        		append(" from DMDB.SB_DM_SZSM a ").
        		append(" where a.zxbs = '0' and (a.sffjs <>'3' or a.sffjs is null) and a.ccbs = '2'").
        		append(" and substr(a.szsmdm,0,2) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" and substr(a.szsmdm,0,4) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" and a.szsmdm not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" union ").
                append("select a.szsmdm, a.szsmmc, a.fjddm, a.zqlxdm, a.ccbs ").
        		append(" from DMDB.SB_DM_SZSM a ").
        		append(" where a.zxbs = '0' and (a.sffjs <>'3' or a.sffjs is null) and a.ccbs = '2'").
        		append(" and a.szsmdm in (select b.szsmdm from DMDB.SB_DM_SZSM b ").
        		append(" where ").append(selectOneBuff).
        		append(" b.szsmdm in (select distinct(d.szsmdm) from sfdb.sf_jl_wtdwmx c, dmdb.sb_dm_szsm d  where c.jsjdm = ? and ").
        		append(" d.szsmdm like c.szsmdm||'%' and (").append(selectOneBuff).append(" length(c.szsmdm) = 6))) " ).
        		append(" and a.szsmdm in (select distinct b.szsmdm from DMDB.SB_DM_SZSM b, sbdb.sb_jl_zqrl c, dmdb.sb_dm_sklxszsmdzdm d,sfdb.sf_jl_wtdwmx e ").
        		append(" where ").append(selectOneBuff).
        		append(" b.szsmdm = c.szsmdm and d.szsmdm = b.szsmdm and d.szsmdm = e.szsmdm ").
        		append(" and c.djzclxdm = ? and c.zqqsrq <= ? and c.zqqsrq + (zqts-1) >= ? and e.jsjdm = ?  and c.nd = ? ").
        		append(" and d.zxbs ='0' and d.sklxbs = '").append(CodeConstant.WSSB_YSSB_SKLXBS).append("') ").
        		append(" order by szsmdm ");
            	
            	//System.out.println("wtdz sql2："+sql);
            	Timestamp now = TinyTools.second2Day(new Timestamp(System.currentTimeMillis()));
            	pstmt = conn.prepareStatement(sql.toString());
            	pstmt.setString(1, djzclxdm);
                pstmt.setString(2, djzclxdm);
                pstmt.setString(3, djzclxdm);
            	pstmt.setString(4, jsjdm);
            	pstmt.setString(5, djzclxdm);
            	pstmt.setTimestamp(6, now);
            	pstmt.setTimestamp(7, now);
            	pstmt.setString(8, jsjdm);
            	pstmt.setString(9, String.valueOf(now.getYear()+1900));
            }
            else
            {
            	sql.append("select a.szsmdm, a.szsmmc, a.fjddm, a.zqlxdm, a.ccbs ").
                append(" from DMDB.SB_DM_SZSM a ").
                append(" where a.zxbs = '0' and (a.sffjs <>'3' or a.sffjs is null) and a.ccbs = '2'").
                append(" and substr(a.szsmdm,0,2) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" and substr(a.szsmdm,0,4) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" and a.szsmdm not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = ? or djzclxdm='WSSB' )").
                append(" order by a.szsmdm");
            	
            	//System.out.println("sklx sql："+sql);
            	pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, djzclxdm);
                pstmt.setString(2, djzclxdm);
                pstmt.setString(3, djzclxdm);
            }

            for(rs = pstmt.executeQuery(); rs.next(); )
            {
                Szsm temp = new Szsm();

                // 税种税目代码
                temp.setSzsmdm(rs.getString("szsmdm"));
                // 税种税目名称
                temp.setSzsmmc(rs.getString("szsmmc"));
                // 父节点代码
                temp.setFjddm(rs.getString("fjddm"));
                //层次代码
                temp.setCcbs(rs.getString("ccbs"));

                szsm.add(temp);
            }
           // System.out.println("查询出"+szsm.size()+"个税目记录******************");
            return szsm;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            try  {   rs.close();  }  catch(Exception ex)    {  }
            try  {   pstmt.close();  }   catch(Exception ex)   {  }
        }
    }
    private List getJkInfor(SWDJJBSJ djsj)
    {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try
        {
            Calendar now = Calendar.getInstance();

            Calendar first = new GregorianCalendar();
            first.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1);

            Calendar last = new GregorianCalendar();
            last.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH)+1, 1);

            String sql =
                "SELECT z.sklxdm, z.szdm, z.sjje " +
                "  FROM sbdb.sb_jl_sbjkzb z " +
                " WHERE z.qxdm = ? " +
                " AND z.zyrq > to_date('2004-01-01','yyyy-mm-dd') " +
                "   AND z.jsjdm = ? " +
                "   AND z.sbrq >= ? " +
                "   AND z.sbrq < ? ";

            conn = DBResource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, djsj.getSwjgzzjgdm().substring(0, 2));
            pstmt.setString(2, djsj.getJsjdm());
            pstmt.setTimestamp(3, new Timestamp(first.getTime().getTime()));
            pstmt.setTimestamp(4, new Timestamp(last.getTime().getTime()));

            List ret = new ArrayList();

            JkInfor jk = null;
            for(rs = pstmt.executeQuery(); rs.next(); ret.add(jk))
            {
                jk = new JkInfor();
                jk.setSklxdm(rs.getString(1));
                jk.setSzsmdm(rs.getString(2));
                jk.setSjse(rs.getBigDecimal(3));
            }

            return ret;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ArrayList();
        }
        finally
        {
            try  {   rs.close();  }  catch(Exception ex)    {  }
            try  {   pstmt.close();  }   catch(Exception ex)   {  }
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }
    
    
    
    /**
     * 查询要委托代征税目 tujb 201406
     * @param djsj
     * @param conn
     * @return
     * @throws BaseException
     */
    private List queryWtdzszsm(SWDJJBSJ djsj, Connection conn) throws BaseException
        {
            ResultSet rs = null;
            PreparedStatement pst = null;

            List result = new ArrayList();

            try
            {
            	String jsjdm = djsj.getJsjdm();

                StringBuffer sql = new StringBuffer();
                sql.append("select a.szsmdm, a.szsmmc, a.fjddm, a.zqlxdm, a.ccbs ").
            		append(" from DMDB.SB_DM_SZSM a ").
            		append(" where a.zxbs = '0' and (a.sffjs <>'3' or a.sffjs is null) and a.ccbs = '2' ").
            		append(" and a.szsmdm in ( select b.szsmdm from sfdb.sf_jl_wtdwmx c,dmdb.sb_dm_sklxszsmdzdm b ").
            		append(" where c.jsjdm = ? and c.szsmdm = b.szsmdm and b.zxbs ='0') order by a.szsmdm ");
                
                pst = conn.prepareStatement(sql.toString());
                pst.setString(1, jsjdm);

                for(rs = pst.executeQuery(); rs.next(); )
                {
                    String[] str = new String[2];
                    str[0]=rs.getString(1);
                    str[1]=rs.getString(2);
                    
                    result.add(str);
                }
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex);
            }
            finally
            {
                try   {   rs.close();   }  catch(Exception e)    {  }
                try   {   pst.close();   }  catch(Exception e)    {  }
            }
            return result;
        }
        
        
        /**
         * 
     * 查询要提示的税目
     */
        private List queryToBeAlertszsm(SWDJJBSJ djsj, Connection conn) throws BaseException
        {
            ResultSet rs = null;
            PreparedStatement pst = null;

            List result = new ArrayList();

            try
            {
                //在税种税目提示控制设置表中查询需要提示的税种税目代码、提示类型代码、提示内容、提示开始日期、提示结束日期
                String sql ="select a.szsmdm,a.tslxdm,a.tsny,to_char(a.tsksrq,'yyyymmdd'),to_char(a.tsjsrq,'yyyymmdd'),to_char(sysdate,'yyyymmdd'),b.szsmmc "+
                	"from dmdb.sb_dm_szsmtskzszb a,dmdb.sb_dm_szsm b where a.szsmdm=b.szsmdm and a.zxbs=?";

                pst = conn.prepareStatement(sql);
                pst.setString(1, "0");

                for(rs = pst.executeQuery(); rs.next(); )
                {
                    String[] str = new String[7];
                    str[0]=rs.getString(1);
                    str[1]=rs.getString(2);
                    if(rs.getString(3) == null){
                    	str[2]="";
                    }else{
                    	str[2]=rs.getString(3);
                    }
                    if(rs.getString(4) == null){
                    	str[3]="";
                    }else{
                    	str[3]=rs.getString(4);
                    }
                    if(rs.getString(5) == null){
                    	str[4]="";
                    }else{
                    	str[4]=rs.getString(5);
                    }
                    str[5]=rs.getString(6);
                    str[6]=rs.getString(7);
                    result.add(str);
                }
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex);
            }
            finally
            {
                try   {   rs.close();   }  catch(Exception e)    {  }
                try   {   pst.close();   }  catch(Exception e)    {  }
            }
            //System.out.println("查询出"+result.size()+"个要提示的数目记录");
            return result;
        }
        
        /**
         * 获得纳税人是否 有 委托代征、代扣、代售、监督代售单位认定情况
         * @param djsj
         * @return 
         * 减免税项目  tujb 200404
         */
        private List getWtdwInfor(SWDJJBSJ djsj)
        {
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            try
            {
                Calendar now = Calendar.getInstance();

                Calendar first = new GregorianCalendar();
                first.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1);

                Calendar last = new GregorianCalendar();
                last.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH)+1, 1);

                String sql =
                    "SELECT z.jsjdm " +
                    "  FROM sfdb.sf_jl_wtdw z " +
                    " WHERE z.jsjdm = ? ";
                conn = DBResource.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, djsj.getJsjdm());

                List ret = new ArrayList();

                WtdwInfor wtdw = null;
                for(rs = pstmt.executeQuery(); rs.next(); ret.add(wtdw))
                {
                	wtdw = new WtdwInfor();
                	wtdw.setJsjdm(rs.getString(1));
                }

                return ret;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList();
            }
            finally
            {
                try  {   rs.close();  }  catch(Exception ex)    {  }
                try  {   pstmt.close();  }   catch(Exception ex)   {  }
                // 关闭数据库连接
                DBResource.destroyConnection(conn);
            }
        }
        
        
        /**
         * 获得税种税目可以显示的委托认定税种税目
         * @param djsj
         * @return 
         * 减免税项目及网上申报印花税项目  Junbing Tu 201404,201407
         */
        private List getWtdzSmInfor(SWDJJBSJ djsj)
        {
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            try
            {
            	String sql =
                    "SELECT a.jsjdm,a.szsmdm,b.sklxbs" +
                    "  FROM sfdb.sf_jl_wtdwmx a,dmdb.sb_dm_sklxszsmdzdm b " +
                    " WHERE a.jsjdm = ? and a.szsmdm = b.szsmdm and b.zxbs ='0'";
                conn = DBResource.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, djsj.getJsjdm());

                List ret = new ArrayList();

                WtdwInfor temp = null;
                for(rs = pstmt.executeQuery(); rs.next(); ret.add(temp))
                {
                	temp = new WtdwInfor();
                	temp.setJsjdm(rs.getString("jsjdm"));
                	temp.setSzsmdm(rs.getString("szsmdm"));
                	temp.setSklxbs(rs.getString("sklxbs"));
                }

                return ret;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList();
            }
            finally
            {
                try  {   rs.close();  }  catch(Exception ex)    {  }
                try  {   pstmt.close();  }   catch(Exception ex)   {  }
                // 关闭数据库连接
                DBResource.destroyConnection(conn);
            }
        }
}
