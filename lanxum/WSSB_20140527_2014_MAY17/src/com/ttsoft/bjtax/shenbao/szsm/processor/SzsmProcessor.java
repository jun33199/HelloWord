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
     * ����˰��˰Ŀ��
     */
    private Map getSzsmMap(SWDJJBSJ djsj,Connection conn) throws BaseException{
    	 
       
             
             ORManager orMgr = DBResource.getORManager();
             DBAccess db = new DBAccess(conn, orMgr);

             int size = 0;
             

             // ��ѯ˰��˰Ŀ
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
     * ��ѯ�����걨˰Ŀ�����˰������Ϊ��ί�д�����������˰��˰ĿΪ��,ȷ��ί�д���˰��˰ĿΨһ�� tujb 201406��
     * @param djsj �Ǽ�����
     * @param con ���ݿ�����
     * @return List Sqsbtmp�б�
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

            
            //added by zhangyj 20100517 ����˰��˰Ŀ��������
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
 * ��ѯҪ���˵�˰Ŀ
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
            // �����������������������͵����ݵ�������;
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
            +	             "AND ZQLXDM IN (SELECT ZQLXDM FROM DMDB.SB_DM_ZQLX WHERE ZQLXDM <> '10') "  //add 2008-06-17 �ų���������
            +                "AND DJZCLXDM = ? " 
            +	             "AND ND = ? " 
            //add 2008-06-17 begin  �����������ڵ���������
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
       // System.out.println("��ѯ��"+result.size()+"��Ҫ���˵�Ҷ��¼");
        return result;
    }

   

    /**
     * ȡ��˰��˰Ŀ����Ϣ������˰�����͡�ί�д���������˰��˰Ŀ tujb 201406��
     * @param djsj �Ǽ�����
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
            //���е�˰��˰Ŀ�����
            result.put("szsmMap", this.getSzsmMap(djsj, conn));
           //���˰��˰ĿLIST
            result.put("szsm", this.getSzsmRawMaterial(djsj, sklxdm, conn));
            //���Ҫ���˵�˰��˰Ŀ
            result.put("toBeFilterszsm", this.queryToBeFilterszsm(djsj, conn));
            //��ó���˰��˰Ŀ
            result.put("favorite", this.queryFavoriteSzsm(djsj, sklxdm, conn));           
            //�����ʾ˰��˰Ŀ 
            //start added by zhangyj 20120905
            result.put("toBeAlertszsm", this.queryToBeAlertszsm(djsj, conn));
            //end added by zhangyj 20120905
            
            //�����ʾ˰��˰Ŀ 
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
     *  ��ȡ˰��˰Ŀ�����˰������Ϊ��ί�д�����,��ѯί�д����϶����˰��˰Ŀ�������ѯ˰��˰Ŀ�� tujb 201406��
     * @param djsj ���������
     * @param sklxdm ˰������
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
        	
        	/*��ȡί�е�λ������˰��˰Ŀ���жϲ�ѯ������Ƿ����˰������Ϊ������˰ĿҪ��ʾ modified by Junbing Tu 2014.07*/
        	if(wtdzsmList != null && wtdzsmList.size() > 0)
        	{
        		for(int i=0; i<wtdzsmList.size(); i++)
            	{
        			WtdwInfor temp = null;
        			WtdwInfor tempSm = (WtdwInfor) wtdzsmList.get(i);

        			//1.��ȡ˰������Ϊ��������ʱ����ѯ��˰��˰Ŀ����ѯSQL
        			if(tempSm.getSklxbs() != null && tempSm.getSklxbs().equals(CodeConstant.WSSB_YSSB_SKLXBS))
        			{
        				temp = new WtdwInfor();
        				temp.setSzsmdm(tempSm.getSzsmdm());
        				sklxsmList.add(temp);
        			}
        			
        			//2.˰������Ϊ��ί�д�����ʱ����ѯ��˰��˰ĿSQL��ѡ��ί�д���˰������ʱʹ�ø�˰Ŀ
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
        		
        		/*��ȡ˰������Ϊ����������˰��˰Ŀ��ѡ������˰������ʱʹ�ø�˰Ŀ*/
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
            	
            	//System.out.println("wtdz sql��"+sql);
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
            	
            	//System.out.println("wtdz sql2��"+sql);
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
            	
            	//System.out.println("sklx sql��"+sql);
            	pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, djzclxdm);
                pstmt.setString(2, djzclxdm);
                pstmt.setString(3, djzclxdm);
            }

            for(rs = pstmt.executeQuery(); rs.next(); )
            {
                Szsm temp = new Szsm();

                // ˰��˰Ŀ����
                temp.setSzsmdm(rs.getString("szsmdm"));
                // ˰��˰Ŀ����
                temp.setSzsmmc(rs.getString("szsmmc"));
                // ���ڵ����
                temp.setFjddm(rs.getString("fjddm"));
                //��δ���
                temp.setCcbs(rs.getString("ccbs"));

                szsm.add(temp);
            }
           // System.out.println("��ѯ��"+szsm.size()+"��˰Ŀ��¼******************");
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
            // �ر����ݿ�����
            DBResource.destroyConnection(conn);
        }
    }
    
    
    
    /**
     * ��ѯҪί�д���˰Ŀ tujb 201406
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
     * ��ѯҪ��ʾ��˰Ŀ
     */
        private List queryToBeAlertszsm(SWDJJBSJ djsj, Connection conn) throws BaseException
        {
            ResultSet rs = null;
            PreparedStatement pst = null;

            List result = new ArrayList();

            try
            {
                //��˰��˰Ŀ��ʾ�������ñ��в�ѯ��Ҫ��ʾ��˰��˰Ŀ���롢��ʾ���ʹ��롢��ʾ���ݡ���ʾ��ʼ���ڡ���ʾ��������
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
            //System.out.println("��ѯ��"+result.size()+"��Ҫ��ʾ����Ŀ��¼");
            return result;
        }
        
        /**
         * �����˰���Ƿ� �� ί�д��������ۡ����ۡ��ල���۵�λ�϶����
         * @param djsj
         * @return 
         * ����˰��Ŀ  tujb 200404
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
                // �ر����ݿ�����
                DBResource.destroyConnection(conn);
            }
        }
        
        
        /**
         * ���˰��˰Ŀ������ʾ��ί���϶�˰��˰Ŀ
         * @param djsj
         * @return 
         * ����˰��Ŀ�������걨ӡ��˰��Ŀ  Junbing Tu 201404,201407
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
                // �ر����ݿ�����
                DBResource.destroyConnection(conn);
            }
        }
}
