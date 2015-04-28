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
     * �����Ƚӿڣ�һ�β�ѯ����ҵ����˰�걨�������������
     * @param jsjdm String ���������*
     * @param rq  Date ����
     * @param qsrq Date ˰��������ʼ����
     * @param jzrq Date ˰��������ֹ����
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
            // ��ҵ����˰���շ�ʽ
			if(bbfs.equals("00")){
				//�������ü�������������ȡ��ǰ�˶���qysdszsfs
			    ret.setZsfs(this.getZsfsInfo(jsjdm, jzrq, con));
			}else{
				//�걨�����걨����������ȡ��ʷ�˶���qysdszsfs_his
				ret.setZsfs(this.getNbZsfsInfo(jsjdm,jzrq,con));
			}
            //���µֿ�
            ret.setSxdk(this.getSxdkSp(jsjdm, "", rq, con));
            //�Ʋ���ʧ
            ret.setCcss(this.getCcssSp(jsjdm, "", rq, con));
            //�ֲ���ǰ��ȿ���
            ret.setNbyqndks(this.getNbyqndksSp(jsjdm, "", rq, con));
            //������������豸Ͷ�ʵ���
            ret.setJsgzgcsb(this.getJsgzgcSp(jsjdm, "", rq, con));
            //��˰�ļ���ת������
            ret.setMsdjxzrsy(this.getMsdjxzrsy(jsjdm, "", rq, con));
            //���¼�����ҵ
            ret.setGxjsqy(this.getGxjsqyHd(jsjdm, "", qsrq, jzrq, con));
            //һ�����
            ret.setYbjme(this.getYbjme(jsjdm, qsrq, jzrq, con));
            //������ҵ
            ret.setXzqy(this.getXzqyHd(jsjdm, qsrq, jzrq, con));
            //jiqiang insert 2005-02-18 �����ˡ�һ�����˰�ʡ�
            //һ�����˰��
            ret.setYbjmsl(this.getYbjmsl(jsjdm, qsrq, jzrq, con));

            return ret;
        }
        catch (FrameException ex)
        {
        	ex.printStackTrace();
        	throw new FrameException("��ҵ����˰�걨������������ݳ���"+ex.getMessage());
        }
        finally
        {

        }

    }
	
    /**
     * ��ȡ��ҵ����˰��
     * @param jsjdm String ���������
     * @param rq Date ��ʼ���ڣ����ã�
     * @param jzrq Date ��������
     * @param db SfDBAccess �������Ӷ���
     * @return BigDecimal ����˰��
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
        	throw new FrameException("��ȡ��ȡ��ҵ����˰������ʧ�ܣ�"+ex.getMessage());
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
     * ������ҵ�϶�
     * @param jsjdm String ���������
     * @param rq  Date ����
     * @param jzrq Date ����
     * @param db ���ݿ����ʵ��
     * @return boolean �Ƿ�������ҵ
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
        	throw new FrameException("��ȡ������ҵ�϶�����ʧ�ܣ�"+ex.getMessage());
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
     * һ�����˰��
     * @param jsjdm String ���������
     * @param  rq Date ����
     * @param jzrq Date ��������
     * @param db ���ݿ���ʻ���ʵ��
     * @return BigDecimal �ֿ۶�
     * @throws FrameException
     */

    public java.math.BigDecimal getYbjme(String jsjdm, Date rq, Date jzrq,Connection con)throws FrameException{
        BigDecimal ret = null;
		Statement st = null;
		ResultSet rs = null;
		String sql=null;
        try
        {
    		// ����������ݵĽ�ֹ���ڴ��ڻ���ڵ�ǰ�걨�������ڵ���������һ���Ҽ���˰��Ϊ30��
    		//���걨����һ��������������еġ�����˰�������걨���81�д��ֶ��� jzrq Ϊ�걨Ҫ�����������
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
        	throw new FrameException("��ȡһ�����˰������ʧ�ܣ�"+ex.getMessage());
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
	 * ���¼�����ҵ�϶�
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
    		throw new FrameException("��ȡ���¼�����ҵ�϶�����ʧ�ܣ�"+ex.getMessage());
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
     * ��˰�ļ���ת������
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param rq ����
     * @param db ���ݿ���ʻ���ʵ��
     * @return �������ݵ�¼�����ڵ��ڵ�ǰ�걨���������꣬���걨�������������еġ���׼�۳���ϼ�
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
        	throw new FrameException("��ȡ��˰�ļ���ת����������ʧ�ܣ�"+ex.getMessage());
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
     * ��������������豸Ͷ�ʵ��⡱����
     * @param jsjdm String ���������
     * @param szsmdm String ˰��˰Ŀ���� δʹ��
     * @param rq  Date ����
     * @return �ֿ۶�
     * @param db ���ݿ���ʻ���ʵ��
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
            throw new FrameException("��ȡ��������������豸Ͷ�ʵ��⡱��������ʧ�ܣ�"+ex.getMessage());
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
     * ���ֲ���ǰ��ȿ�������
     * @param jsjdm String ���������
     * @param szsmdm String ˰��˰Ŀ���� δʹ��
     * @param rq  Date ����
     * @return �ֿ۶�
     * @param db ���ݿ���ʻ���ʵ��
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
            //�϶����Ϊ�걨���-1
            //��ҵ�ֲ�����������
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
            //���˶��ʺͺϻ���ҵ�ֲ�������˽����
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
        	 throw new FrameException("��ȡ���ֲ���ǰ��ȿ�����������ʧ�ܣ�"+ex.getMessage());
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
    /**���Ʋ���ʧ������
     * @param jsjdm String ���������
     * @param szsmdm String ˰��˰Ŀ���� δʹ��
     * @param rq Date ����
     * @param db ���ݿ���ʻ���ʵ��
     * @return �ֿ۶�
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
            //���˶��ʺͺϻ���ҵ�Ʋ���ʧ��˽����
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
            //���塢˽Ӫ��ҵ�Ʋ���ʧ˰ǰ�۳�����������
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
       	 	throw new FrameException("��ȡ���Ʋ���ʧ����������ʧ�ܣ�"+ex.getMessage());
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
     * �����µֿۡ�����
     * @param jsjdm String ���������
     * @param szsmdm String ˰��˰Ŀ���� δʹ��
     * @param rq  Date ����
     * @param db ���ݷ��ʻ���ʵ��
     * @return �ֿ۶�
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
        	 throw new FrameException("��ȡ�����µֿۡ���������ʧ�ܣ�"+ex.getMessage());
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
	 * ��ҵ����˰�걨ȡ���շ�ʽ
	 * @param jsjdm ���������
	 * @param rq �϶�����
	 * @param con ��������
	 * @return ���շ�ʽ����
	 * @throws FrameException �쳣����
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
            throw new FrameException("��ȡ�걨���շ�ʽʧ�ܣ�"+ex.getMessage());
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
     * ���շ�ʽ
     * @param jsjdm String ���������*
     *@param rq ��ȥ
     * @param con ���ݿ�ʵ��
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
            throw new FrameException("��ȡ�������շ�ʽʧ�ܣ�"+ex.getMessage());
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
     * �õ��������ڵ���� Ϊint��
     * @param date ����������
     * @return int ���ֵ
     */
    public static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR));
    }
    /**
     * ��ȡ���շ�ʽ����
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
