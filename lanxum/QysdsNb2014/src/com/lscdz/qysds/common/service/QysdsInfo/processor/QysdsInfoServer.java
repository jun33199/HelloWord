package com.lscdz.qysds.common.service.QysdsInfo.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.QysdsInfo.IQysdsInfoServer;
import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.QysdsInfo.util.DateUtil;


public class QysdsInfoServer implements IQysdsInfoServer {
    /**
     * 粗粒度接口，一次查询出企业所得税年报所需的所有数据
     * @param jsjdm String 计算机代码*
     * @param rq  Date 日期
     * @param qsrq Date 税款所属起始日期
     * @param jzrq Date 税款所属截止日期
     * @return com.ttsoft.bjtax.sfgl.common.model.QysdsSet
     * @throws BaseException
     */
	@Override
    public QysdsSet getQysdsInfo(Connection con,String jsjdm, Date rq, Date qsrq, Date jzrq,String bbfs) throws FrameException{
        QysdsSet ret = new QysdsSet();

        try
        {
//            con =ResourceManager.getConnection();
//            SfDBAccess db = new SfDBAccess(con);
            // 企业所得税征收方式
			if(bbfs.equals("00")){
				//季报调用季报方法－－－取当前核定表qysdszsfs
			    ret.setZsfs(this.getZsfsInfo(jsjdm, jzrq, con));
			}else{
				//年报调用年报方法－－－取历史核定表qysdszsfs_his
				ret.setZsfs(this.getNbZsfsInfo(jsjdm,jzrq,con));
			}
            //三新抵扣
            ret.setSxdk(this.getSxdkSp(jsjdm, "", rq, con));
            //财产损失
            ret.setCcss(this.getCcssSp(jsjdm, "", rq, con));
            //弥补以前年度亏损
            ret.setNbyqndks(this.getNbyqndksSp(jsjdm, "", rq, con));
            //技术改造国产设备投资抵免
            ret.setJsgzgcsb(this.getJsgzgcSp(jsjdm, "", rq, con));
            //免税的技术转让收益
            ret.setMsdjxzrsy(this.getMsdjxzrsy(jsjdm, "", rq, con));
            //高新技术企业
            ret.setGxjsqy(this.getGxjsqyHd(jsjdm, "", qsrq, jzrq, con));
            //一般减免
            ret.setYbjme(this.getYbjme(jsjdm, qsrq, jzrq, con));
            //乡镇企业
            ret.setXzqy(this.getXzqyHd(jsjdm, qsrq, jzrq, con));
            //jiqiang insert 2005-02-18 增加了“一般减免税率”
            //一般减免税率
            ret.setYbjmsl(this.getYbjmsl(jsjdm, qsrq, jzrq, con));

            return ret;
        }
        catch (FrameException ex)
        {
        	ex.printStackTrace();
        	throw new FrameException("企业所得税年报所需的所有数据出错！"+ex.getMessage());
        }
        finally
        {

        }

    }
	
    /**
     * 获取企业减免税率
     * @param jsjdm String 计算机代码
     * @param rq Date 起始日期（备用）
     * @param jzrq Date 截至日期
     * @param db SfDBAccess 数据链接对象
     * @return BigDecimal 减免税率
     * @throws FrameException
     */
    private java.math.BigDecimal getYbjmsl(String jsjdm, Date rq, Date jzrq,Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
        	sql="select JMSL from SPDB.SP_JL_YBNSDWJMSP a, SPDB.SP_JL_YBNSDWJMSPMX b where jsjdm='" +
            jsjdm + "' and JMSQSRQ<=to_date('" + DateUtil.getDate(jzrq) +
            "','yyyy-MM-dd') and JMSZZRQ>=to_date('" + DateUtil.getDate(jzrq) +
            "','yyyy-MM-dd') and (szsmdm='30' or szsmdm='300000') and a.sqspbh=b.sqspbh  order by a.lrrq desc";
	        st=con.createStatement();
	        rs=st.executeQuery(sql);

            if (rs.next())
            {
                if (rs.getBigDecimal("JMSL") == null)
                {
                    ret = new BigDecimal(0);
                }
                else
                {
                    ret = rs.getBigDecimal("JMSL").divide(new BigDecimal(100), 4,
                                                         BigDecimal.ROUND_HALF_UP);
                }
            }
            return ret;
        }
        catch (Exception ex)
        {
        	throw new FrameException("获取获取企业减免税率数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }	
    /**
     * 乡镇企业认定
     * @param jsjdm String 计算机代码
     * @param rq  Date 日期
     * @param jzrq Date 日期
     * @param db 数据库访问实例
     * @return boolean 是否乡镇企业
     * @throws FrameException
     */

    private boolean getXzqyHd(String jsjdm, Date rq, Date jzrq,Connection con)throws FrameException{
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
        	sql="select JMSPZJE  from SPDB.SP_JL_YBNSDWJMSP a, SPDB.SP_JL_YBNSDWJMSPMX b where jsjdm='" +
	            jsjdm + "' and ((JMSQSRQ<=to_date('" + DateUtil.getDate(rq) +
	            "','yyyy-MM-dd') and JMSZZRQ>=to_date('" + DateUtil.getDate(rq) +
	            "','yyyy-MM-dd')) or ( JMSQSRQ<=to_date('" +
	            DateUtil.getDate(jzrq) +
	            "','yyyy-MM-dd') and JMSZZRQ>=to_date('"
	            + DateUtil.getDate(jzrq) +
	            "','yyyy-MM-dd')) or ( JMSQSRQ>=to_date('"
	            + DateUtil.getDate(rq) +
	            "','yyyy-MM-dd') and JMSZZRQ<=to_date('"
	            + DateUtil.getDate(jzrq) +
	            "','yyyy-MM-dd'))) and JMSLBDM='9100' and a.sqspbh=b.sqspbh order by a.lrrq desc";
	        st=con.createStatement();
	        rs=st.executeQuery(sql);

            return rs.next();
        }
        catch (Exception ex)
        {
        	throw new FrameException("获取乡镇企业认定数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }	
    /**
     * 一般减免税额
     * @param jsjdm String 计算机代码
     * @param  rq Date 日期
     * @param jzrq Date 截至日期
     * @param db 数据库访问基类实例
     * @return BigDecimal 抵扣额
     * @throws FrameException
     */

    public java.math.BigDecimal getYbjme(String jsjdm, Date rq, Date jzrq,Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
    		// 如果审批数据的截止日期大于或等于当前申报日期所在的上年的最后一天且减免税种为30，
    		//则申报带出一般减免审批数据中的“减免税审批额”填到申报表的81行次字段上 jzrq 为申报要求的限制日期
    		sql="select JMSPZJE  from SPDB.SP_JL_YBNSDWJMSP a, SPDB.SP_JL_YBNSDWJMSPMX b where jsjdm='" +
            	jsjdm + "' and JMSZZRQ>=to_date('" + DateUtil.getDate(jzrq) +
            	"','yyyy-MM-dd') and (szsmdm='30' or szsmdm='300000') and a.sqspbh=b.sqspbh  order by a.lrrq desc";
	        st=con.createStatement();
	        rs=st.executeQuery(sql);
            if (rs.next())
            {
                if (rs.getBigDecimal("JMSPZJE") == null)
                {
                    return new BigDecimal(0);
                }
                return rs.getBigDecimal("JMSPZJE");
            }

            return ret;
        }
        catch (Exception ex)
        {
        	throw new FrameException("获取一般减免税额数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }
	
	
	/**
	 * 高新技术企业认定
	 * @param jsjdm
	 * @param szsmdm
	 * @param rq
	 * @param jzrq
	 * @param con
	 * @return
	 * @throws FrameException
	 */
    public java.sql.Date getGxjsqyHd(String jsjdm, String szsmdm, Date rq,Date jzrq,Connection con)throws FrameException{
    	java.sql.Date ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
    	try
    	{
    		int rdnd = getYear(jzrq);
    		sql="select pzrq from SFDB.SF_JL_TSQYRD where jsjdm='" +
				jsjdm + "' and pzrq<to_date('" +
				DateUtil.getDate(jzrq) +
				" 23:59:59','yyyy-MM-dd hh24:mi:ss') and TSQYLXDM='01'  order by pzrq desc";
            st=con.createStatement();
            rs=st.executeQuery(sql);
    		if (rs.next())
    		{
    			ret = rs.getDate("pzrq");
    		}
    		return ret;
    	}
    	catch (Exception ex)
    	{
    		throw new FrameException("获取高新技术企业认定数据失败！"+ex.getMessage());
    	}finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
	}
	
    /**
     * 免税的技术转让收益
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param rq 日期
     * @param db 数据库访问基类实例
     * @return 审批数据的录入日期等于当前申报日期所在年，则申报带出审批数据中的“批准扣除额合计
     * @throws FrameException
     */
    private java.math.BigDecimal getMsdjxzrsy(String jsjdm, String szsmdm,Date rq, Connection con)throws FrameException{
        java.math.BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
            int nd = getYear(rq);
            sql="select MSSDEHJ from SPDB.SP_JL_JSXMSSP where jsjdm='" +
            jsjdm + "' and to_char(lrrq,'yyyy')='" +
            nd + "'  order by lrrq desc";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if (rs.next())
            {
                BigDecimal temp = rs.getBigDecimal("MSSDEHJ");
                if (temp != null)
                {
                    return temp;
                }
            }
            return ret;
        }
        catch (Exception ex)
        {
        	throw new FrameException("获取免税的技术转让收益数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }	
    /**
     * “技术改造国产设备投资抵免”审批
     * @param jsjdm String 计算机代码
     * @param szsmdm String 税总税目代码 未使用
     * @param rq  Date 日期
     * @return 抵扣额
     * @param db 数据库访问基类实例
     * @throws FrameException
     */

    public java.math.BigDecimal getJsgzgcSp(String jsjdm, String szsmdm, Date rq,Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
            int rdnd = getYear(rq);
            sql="select sum(b.QRDMYE) QRDMYE from spdb.SP_JL_SBDMQYSDS a ,spdb.SP_JL_SBDMQYSDSMX b where a.SQSPBH=b.SQSPBH and a.jsjdm='" +
            jsjdm + "' and a.DMND='" + rdnd + "'";
            st=con.createStatement();
            rs=st.executeQuery(sql);

            if (rs.next())
            {
                return rs.getBigDecimal("QRDMYE");
            }

            return ret;
        }
        catch (Exception ex)
        {
            throw new FrameException("获取“技术改造国产设备投资抵免”审批数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }
	
	
    /**
     * “弥补以前年度亏损”审批
     * @param jsjdm String 计算机代码
     * @param szsmdm String 税总税目代码 未使用
     * @param rq  Date 日期
     * @return 抵扣额
     * @param db 数据库访问基类实例
     * @throws FrameException
     */

    public java.math.BigDecimal getNbyqndksSp(String jsjdm, String szsmdm, Date rq, Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st_QYSQBKE = null;
		ResultSet rs_QYSQBKE = null;
		Statement st_PZMBE = null;
		ResultSet rs_PZMBE = null;
		String sql=null;
        try
        {
            //认定年度为申报年度-1
            //企业弥补亏损审批表
            int rdnd = getYear(rq);
            sql="select sum(QYSQBKE) QYSQBKE from SPDB.SP_JL_QYMMKSSPMX a,SPDB.SP_JL_QYMMKSSP b where jsjdm='" +
            jsjdm + "' and bknd='" + rdnd +
            "' and a.sqspbh=b.sqspbh";
            st_QYSQBKE=con.createStatement();
            rs_QYSQBKE=st_QYSQBKE.executeQuery(sql);
            if (rs_QYSQBKE.next())
            {
                BigDecimal temp = rs_QYSQBKE.getBigDecimal("QYSQBKE");
                if (temp != null)
                {
                    return temp;
                }
            }
            //个人独资和合伙企业弥补亏损审核结果表
            sql="select sum(PZMBE) PZMBE from SFDB.SF_JL_GRDZMBKSSP where jsjdm='" +
            jsjdm + "'  and to_char(pzrq,'yyyy')='" +
            rdnd + "' order by pzrq desc";
            st_PZMBE=con.createStatement();
            rs_PZMBE=st_PZMBE.executeQuery(sql);

            if (rs_PZMBE.next())
            {
                return rs_PZMBE.getBigDecimal("PZMBE");
            }

            return ret;
        }
        catch (Exception ex)
        {
        	 throw new FrameException("获取“弥补以前年度亏损”审批数据失败！"+ex.getMessage());
        }finally
        {
			if (rs_QYSQBKE != null) {
				try {
					rs_QYSQBKE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st_QYSQBKE != null) {
				try {
					st_QYSQBKE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			} 
			if (rs_PZMBE != null) {
				try {
					rs_PZMBE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st_PZMBE != null) {
				try {
					st_PZMBE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}			
        }	
    }
    /**“财产损失”审批
     * @param jsjdm String 计算机代码
     * @param szsmdm String 税总税目代码 未使用
     * @param rq Date 日期
     * @param db 数据库访问基类实例
     * @return 抵扣额
     * @throws FrameException
     */

    public java.math.BigDecimal getCcssSp(String jsjdm, String szsmdm,Date rq,Connection con)throws FrameException{
        java.math.BigDecimal ret = null;
		Statement st_PZKCEHJ = null;
		ResultSet rs_PZKCEHJ = null;
		Statement st_PZJE = null;
		ResultSet rs_PZJE = null;
		String sql=null;
        try
        {
            //个人独资和合伙企业财产损失审核结果表
            int rdnd = getYear(rq);
            sql="select sum(PZKCEHJ) PZKCEHJ from SFDB.SF_JL_DZHHCCSSSP where jsjdm='" +
            jsjdm + "' and to_char(pzrq,'yyyy')='" +
            rdnd + "' order by pzrq desc";
            st_PZKCEHJ=con.createStatement();
            rs_PZKCEHJ=st_PZKCEHJ.executeQuery(sql);
            if (rs_PZKCEHJ.next())
            {
                BigDecimal temp = rs_PZKCEHJ.getBigDecimal("PZKCEHJ");
                if (temp != null)
                {
                    return temp;
                }
            }
            //集体、私营企业财产损失税前扣除申请审批表
            sql="select sum(PZJE) PZJE from SFDB.SF_JL_JTSYCCSSKCSP where jsjdm='" +
            jsjdm + "' and to_char(lrrq,'yyyy')='" +
            rdnd + "' order by lrrq desc";
            st_PZJE=con.createStatement();
            rs_PZJE=st_PZJE.executeQuery(sql);
            if (rs_PZJE.next())
            {
                return rs_PZJE.getBigDecimal("PZJE");
            }
            return ret;
        }
        catch (Exception ex)
        {
       	 	throw new FrameException("获取“财产损失”审批数据失败！"+ex.getMessage());
        }finally
        {
			if (rs_PZKCEHJ != null) {
				try {
					rs_PZKCEHJ.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st_PZKCEHJ != null) {
				try {
					st_PZKCEHJ.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (rs_PZJE != null) {
				try {
					rs_PZJE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st_PZJE != null) {
				try {
					st_PZJE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
	    } 
    }
    /**
     * “三新抵扣”审批
     * @param jsjdm String 计算机代码
     * @param szsmdm String 税总税目代码 未使用
     * @param rq  Date 日期
     * @param db 数据访问基类实例
     * @return 抵扣额
     * @throws FrameException
     */

    public java.math.BigDecimal getSxdkSp(String jsjdm, String szsmdm, Date rq,Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
        try
        {
            int rdnd = getYear(rq);
            String sql= "select sum(SWJGSHS) SWJGSHS from SPDB.SP_JL_QYSDSDKSP a,SPDB.SP_JL_QYSDSDKSPMX b where a.jsjdm='" +
	            jsjdm +
	            "'  and b.SPXMDM ='06' and a.sqspbh=b.sqspbh and to_char(a.lrrq,'yyyy')='" +
	            rdnd + "' order by a.lrrq desc";
            st=con.createStatement();
            rs =st.executeQuery(sql);
            if (rs.next())
            {
                ret = rs.getBigDecimal("SWJGSHS");
            }
            return ret;
        }
        catch (Exception ex)
        {
        	 throw new FrameException("获取“三新抵扣”审批数据失败！"+ex.getMessage());
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
    }

	/**
	 * 企业所得税年报取征收方式
	 * @param jsjdm 计算机代码
	 * @param rq 认定日期
	 * @param con 数据连接
	 * @return 征收方式对象
	 * @throws FrameException 异常错误
	 */
	private Zsfs getNbZsfsInfo(String jsjdm,Date rq, Connection con)throws FrameException{	
		
        Zsfs ret = new Zsfs();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
        	int rdnd = getYear(rq);
        	String sql="SELECT * FROM sfdb.sf_jl_qysdszsfs_his  WHERE JSJDM=? and to_number(rdnd)<=? order by rdnd desc,cjrq desc";
        	ps=con.prepareStatement(sql);
        	ps.setString(1, jsjdm);
        	ps.setString(2, String.valueOf(rdnd));
        	
        	rs=ps.executeQuery();
        	if(rs.next()){
        		ret.setZsfsdm(rs.getString("ZSFSDM"));
        		ret.setSl(String.valueOf(rs.getBigDecimal("DL")));
        		ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
        		if(rs.getBigDecimal("CYL")!=null){
                    ret.setCyl(String.valueOf(rs.getBigDecimal("CYL").divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP)));        			
        		}
        		ret.setDe(String.valueOf(rs.getBigDecimal("DE")));
        	}else
            {
                return null;
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw new FrameException("获取年报征收方式失败！"+ex.getMessage());
        }finally
        {
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
	}

    /**
     * 征收方式
     * @param jsjdm String 计算机代码*
     *@param rq 如去
     * @param con 数据库实例
     * @return com.ttsoft.bjtax.sfgl.common.model.Zsfs
     * @throws FrameException
     */

    public Zsfs getZsfsInfo(String jsjdm,Date rq, Connection con) throws FrameException
    {
        Zsfs ret = new Zsfs();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
        	int rdnd = getYear(rq);
        	String sql="SELECT * FROM SFDB.SF_JL_QYSDSZSFS  WHERE JSJDM=? and to_number(rdnd)<=? order by rdnd desc,cjrq desc";
        	ps=con.prepareStatement(sql);
        	ps.setString(1, jsjdm);
        	ps.setInt(2, rdnd);
        	rs=ps.executeQuery();
        	if(rs.next()){
        		ret.setZsfsdm(rs.getString("ZSFSDM"));
        		ret.setSl(String.valueOf(rs.getBigDecimal("DL")));
        		ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
        		if(rs.getBigDecimal("CYL")!=null){
                    ret.setCyl(String.valueOf(rs.getBigDecimal("CYL").divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP)));        			
        		}
        		ret.setDe(String.valueOf(rs.getBigDecimal("DE")));
        	}else
            {
                return null;
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw new FrameException("获取季报征收方式失败！"+ex.getMessage());
        }finally
        {
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
    }
    
    /**
     * 得到给定日期的年份 为int型
     * @param date 给定的日期
     * @return int 年份值
     */
    public static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR));
    }
    /**
     * 获取征收方式名称
     * @param zsfsdm
     * @param con
     * @return
     */
    public java.lang.String getZsfsmc(String zsfsdm, Connection con)
    {
        String ret = "";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
                + zsfsdm +
                "'";
            ps=con.prepareStatement(sql);
            rs =ps.executeQuery(sql);
            if (rs.next())
            {
                ret = rs.getString("zsfsmc");
            }

            return ret;
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
            return "";
        }finally
        {
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
    }
    
}
