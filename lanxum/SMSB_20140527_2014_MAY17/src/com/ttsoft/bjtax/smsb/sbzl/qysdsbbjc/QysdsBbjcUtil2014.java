package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web.QysdsBbjcForm;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;
public class QysdsBbjcUtil2014 {
	/**
     * 获取征收方式
     * @param jsjdm String
     * @param rq Date
     * @return Zsfs
     * @throws BaseException
     */
    public Zsfs getZsfsInfo(String jsjdm, Date rq) throws BaseException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Zsfs ret = new Zsfs();
        QysdsZsfs q = new QysdsZsfs();
        StringBuffer sql = new StringBuffer();
        int rdnd = getYear(rq);
        try {
            con = SfDBResource.getConnection();
            sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) <= ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            System.out.println("zsfs query sql:\n" + sql.toString());

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            //取最后核定的一条记录
            if(rs.next())
            {
                q.setZsfsdm(rs.getString("ZSFSDM"));
                q.setDl(new BigDecimal(rs.getDouble("DL")));
                q.setCyl(new BigDecimal(rs.getDouble("CYL")));
                q.setDe(new BigDecimal(rs.getDouble("DE")));
            }
            else
            {
                return null;
            }

            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));
            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP)));
            }
            else {
                ret.setCyl(String.valueOf(q.getCyl()));
            }
            ret.setDe(String.valueOf(q.getDe()));
            //关闭数据库对象
            rs.close();
            pstmt.close();
            return ret;
        }
        catch (Exception ex) {
        	ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            SfDBResource.freeConnection(con);
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
     * 获取支付方式名称
     * @param zsfsdm
     * @param con
     * @return
     */
    public java.lang.String getZsfsmc(String zsfsdm, Connection con)
    {
        PreparedStatement pstmt = null;
        String ret = "";
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='" +
            		"" + zsfsdm +"'";
            SfDBAccess db = new SfDBAccess(con);
            ResultSet rs = db.querySQL(sql);
            if (rs.next())
            {
                ret = rs.getString("zsfsmc");
            }
            rs.close();
            return ret;
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    /**
     * 获取跳转的路径
     * @param sbzllxdm
     * @param skssksrq
     * @param skssjsrq
     * @return path
     * @throws ApplicationException
     */
	public String getPath(String sbzllxdm,String skssksrq,String skssjsrq)throws  ApplicationException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String path=null;
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		int nowYear=ca.get(Calendar.YEAR);
		String nowyear=String.valueOf(nowYear);
		int dqnd=Integer.parseInt(nowyear);
	    int lastYear=dqnd-1;
		String year=String.valueOf(lastYear);
		//String year=String.valueOf(dqnd);
		//数据库为空时得到的截止时间
		String rq=getRq(sbzllxdm,nowyear,year);
		try{
			con = SfDBResource.getConnection();
			sql.append("SELECT t.YYDZ from DMDB.QYSDS_DM_SBZLLXMX t where ");
			sql.append("  to_DATE('"+skssjsrq+"','yyyyMMdd') between " +
					"t.qsrq and nvl(t.jzrq, to_DATE('"+rq+"', 'yyyyMMdd'))");
			sql.append("  and t.SBZLLXDM = '").append(sbzllxdm).append("' ");
			System.out.println("+++++++++path query sql :"+sql.toString());
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());
			if (rs.next()) {
				path = rs.getString("YYDZ");
					if(path.length()<=0){
						throw new ApplicationException("不存在该跳转路径，无法进行跳转！！！");
					}
			}else{
				   throw new ApplicationException("不存在该跳转路径！！！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("获取跳转路径错误！！！");
		} finally {
			 try {
    			 if(pstmt!=null){
    				 pstmt.close();
    			 }
    			 if(rs!=null){
    				 rs.close();
    			 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			SfDBResource.freeConnection(con);
		}
		 return path;
	}
	/**
	 * 获得税款开始日期
	 * @param dqnd
	 * @param jddm
	 * @param bbqlx
	 * @return skksrq
	 * 
	 */
	public String  getKsrq(String dqnd,String bbqlx,String jddm){
		String skksrq="";
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		int nowYear=ca.get(Calendar.YEAR);
		String nowyear=String.valueOf(nowYear);
		int nd=Integer.parseInt(dqnd);
		int lastYear=nd-1;
		String year=String.valueOf(lastYear);
		  if("2".equals(bbqlx)){
			  if(dqnd.equals(nowyear)){
				skksrq=year+"0101";
			}else{
				skksrq=dqnd+"0101";
			}
		  }else if("1".equals(bbqlx)){
		
			 skksrq=dqnd+"0101";
		  }
		  return skksrq;
	}
	/**
	 * 获得税款结束日期
	 * @param dqnd
	 * @param jddm
	 * @param bbqlx
	 * @return skjsrq
	 * 
	 */
	public String  getJsrq(String dqnd,String jddm,String bbqlx){
		String skjsrq="";
		if("2".equals(bbqlx)){
			Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
			int nowYear=ca.get(Calendar.YEAR);
			String nowyear=String.valueOf(nowYear);
			if(dqnd.equals(nowyear)){
				int nd=Integer.parseInt(dqnd);
				int lastYear=nd-1;
				String year=String.valueOf(lastYear);
				skjsrq=year+"1231";
			}else{
				skjsrq=dqnd+"1231";
			}
		}
		else if("1".equals(bbqlx)){
			if("1".equals(jddm)){
				skjsrq=dqnd+"0331";
		}else if("2".equals(jddm)){
			    skjsrq=dqnd+"0630";
		}else if("3".equals(jddm)){
			    skjsrq=dqnd+"0930";
		}else if("4".equals(jddm)){
			    skjsrq=dqnd+"1231";
		}
	  }
		return skjsrq;
	}
	/**
	 * 获得企业所得税申报资料类型的具体信息
	 * @param sbzllxdm
	 * @param form
	 * @throws ApplicationException
	 */
	public void getQysdssbzllx(String sbzllxdm,QysdsBbjcForm form)throws  ApplicationException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = SfDBResource.getConnection();		
			ps = conn.prepareStatement(" select SBZLLXDM,SBZLLXMC,BBQLX,ZSFS from DMDB.QYSDS_DM_SBZLLX where SBZLLXDM='"+sbzllxdm+"'");
			rs = ps.executeQuery();
			while (rs.next()) {	
				form.setBbqlx(rs.getString("BBQLX"));
				form.setZsfs(rs.getString("ZSFS"));
				form.setQysdssbfsdm(rs.getString("SBZLLXDM"));
				form.setQysdssbfsmc(rs.getString("SBZLLXMC"));
			}
			
	     }catch (Exception ex) {
	    	ex.printStackTrace();
			throw new ApplicationException("sbzl");
			
	     }finally{
    		 try {
    			 if(ps!=null){
    				 ps.close();
    			 }
    			 if(rs!=null){
    				 rs.close();
    			 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	 SfDBResource.freeConnection(conn);
	     }
		
	}		
	/**
	 * 获得税款年度
	 * @return sknd
	 */
	public String getSknd(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map nbqj = Skssrq.yearSkssrq(curTime);
		String sknd=(String) nbqj.get(Skssrq.SKSSRQ_ND);
		return sknd;
	}
	/**
	 * 获得企业所得税征收方式接口
	 * @param bbqlx
	 * @param jsjdm
	 * @param skssksrq
	 * @param sbrq
	 * @param skssjsrq
	 * @param jddm
	 * @param qysdsSet
	 * @param proxy
	 * @return
	 * @throws ApplicationException
	 */
	public QysdsSet getQysdsSet(String bbqlx,String jsjdm,Date skssksrq,Date sbrq,
			Date skssjsrq,String jddm,QysdsSet qysdsSet,ServiceProxy proxy) throws ApplicationException {
		          
				try {
					
					if (bbqlx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
						System.out.println("***********年度测试********");
						qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,CodeConstant.SFGL_QYSDS_BBFS_NB);
					} else if (bbqlx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
						System.out.println("**********季度测试**********");
						/* 如果为第四季度，或取企业所得税认定信息时按年报来获取 */
						if (jddm == null|| (jddm != null && jddm.trim().equals(""))) 
						{ 
							throw new ApplicationException("系统发生异常，没有选定季度！！！");
						}
						if ("4".equals(jddm)) {
							qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
						} else {
							qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
		                    //重载Zsfs
		                    Zsfs zsfs = this.getZsfsInfo(jsjdm, skssjsrq);
		                    qysdsSet.setZsfs(zsfs);
						}
					}

				} catch (com.ttsoft.framework.exception.BaseException e) {
					e.printStackTrace();
					throw new ApplicationException("在税款所属区间内，查询不到该计算机代码的增收方式代码！！！");
				}
		          return qysdsSet;
	}
	/**
	 * 校验是否清算，是否为企业所得税的应征户
	 * @param nd
	 * @param sbzllxdm
	 * @param qysdsBbjcForm
	 * @param checkBean
	 * @throws BaseException
	 */
	public void jumpCheck(int nd,String sbzllxdm,QysdsBbjcForm qysdsBbjcForm,CheckBean checkBean)
			throws BaseException{
	
		if(sbzllxdm.equals("01")||sbzllxdm.equals("02")||sbzllxdm.equals("03")||
				                                   sbzllxdm.equals("04")||sbzllxdm.equals("05")){
			System.out.println("申报日期++++++++++++++++++wwwwwwwwwwwwwwwwwwwwwwwwwwwww");
			if((nd==2006)||(nd==2007)){
			checkBean=this.setCheckInf2006(qysdsBbjcForm);
			System.out.println("+++++++++++++++++++++++校验1"+nd);
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
			}else if((nd>=2008)&&(nd<=2014)){
			checkBean=this.setCheckInf(qysdsBbjcForm);
			System.out.println("+++++++++++++++++++++++校验2"+nd);
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			}
		}else if(sbzllxdm.equals("06")||sbzllxdm.equals("07")){
			if(nd<2008){
				throw new ApplicationException("企业所得税汇总纳税分支机构不支持2008年之前的申报！！！");
			}else if(nd>=2008&&(nd<=2014)){
				checkBean=this.setCheckInf(qysdsBbjcForm);
				QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			}
		}
	 
	}
	/**
	 * 校验清算期的材料(2006版)
	 * @param qysdsNewForm
	 * @return checkBean
	 */
	public CheckBean setCheckInf2006(QysdsBbjcForm qysdsNewForm)
	{
		CheckBean checkBean = new CheckBean();
		//校验清算期的材料
		checkBean.setJsjdm(qysdsNewForm.getJsjdm());
		return checkBean;
	}
	/**
	 *  校验清算期的材料(2008版,2009版，2013版)
	 * @param qysdsNewForm
	 * @return
	 */
	public CheckBean setCheckInf(QysdsBbjcForm qysdsNewForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setJsjdm(qysdsNewForm.getJsjdm());
		System.out.println("++++++++++++++++计算机"+qysdsNewForm.getJsjdm());
		//验证是否是企业所得税应征户
		checkBean.setSkssrqq(qysdsNewForm.getSkssksrq());
		System.out.println("++++++++++++++++开始日期"+qysdsNewForm.getSkssksrq());
		checkBean.setSkssrqz(qysdsNewForm.getSkssjsrq());
		System.out.println("++++++++++++++++结束日期"+qysdsNewForm.getSkssjsrq());
		return checkBean;
	}
	/**
	 * 替换字符串
	 * @param original 源字符串
	 * @param find 查找字符串
	 * @param replacement 替换字符串
	 * @return 替换后的字符串
	 */
	public final static String replaceAll(String original, String find,
			String replacement) {
		StringBuffer buffer = new StringBuffer(original);
		
		int idx = original.length();
		int offset = find.length();
		
		while ( (idx = original.lastIndexOf(find, idx - 1)) > -1) {
			buffer.replace(idx, idx + offset, replacement);
		}
		
		return buffer.toString();
	}
	/**
	 * //年报季报当表中截止时间为空的处理方式
	 * @param sbzllxdm
	 * @param nowYear
	 * @param year
	 * @return rq
	 */
	public final String getRq(String sbzllxdm,String nowYear,String year){
		String rq=null;
		if(sbzllxdm.equals("04")||sbzllxdm.equals("05")||sbzllxdm.equals("06")){
			rq=nowYear+"1231";
		}else{
			rq=year+"1231";
		}
		return rq;
	}
	
	/*public QysdsBbjcForm getAccount(QysdsBbjcForm form){
		QysdsBbjcForm xsQysdsBbjcForm=new QysdsBbjcForm();
		xsQysdsBbjcForm.setNsrmc(form.getNsrmcSubmit());
		xsQysdsBbjcForm.setNsrsbh(form.getNsrsbhSubmit());
		xsQysdsBbjcForm.setSshymc(form.getSshymcSubmit());
		xsQysdsBbjcForm.setSsjjlxmc(form.getSsjjlxmcSubmit());
		return  xsQysdsBbjcForm;
	}*/
}
