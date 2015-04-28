package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.dm.GJBZHY;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.web.HdzssdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;

public class QysdsHdzsNbUtil2014 {
	
	public static void main(String[] args){		
		
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
    
	 public java.lang.String getZsfsmc(String zsfsdm, Connection con)
	    {
	        PreparedStatement pstmt = null;
//	        ResultSet rs = null;
	        String ret = "";
	        try
	        {
	            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
	                + zsfsdm +
	                "'";
	            SfDBAccess db = new SfDBAccess(con);
	            ResultSet rs = db.querySQL(sql);
	            if (rs.next())
	            {
	                //rs.first();
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
//            SfDBAccess db = new SfDBAccess(con);
//            Vector v = new Vector();
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
                //ret.setZsfsdm(rs.getString("ZSFSDM"));
                //ret.setSl(String.valueOf(rs.getDouble("DL")));
                //ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
            }
            else
            {
                return null;
            }

            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),
                    4,
                    BigDecimal.ROUND_HALF_UP)));
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
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            SfDBResource.freeConnection(con);
        }

    }
	
	/**
	 * 设置报表对象基本信息
	 * @param report
	 * @param form
	 */
	public static void setQysdsReport(QysdsReportsDeclare report,QysdsNewForm form){
		/**
		 * 基本信息
		 */
		Jbxx jbxx=new Jbxx();
		
		/**
		 * 基本信息(JBXX)-计算机代码
		 */
		jbxx.setJsjdm(form.getJsjdm());
		/**
		 * 基本信息(JBXX)-纳税人名称
		 */
		jbxx.setNsrmc(form.getNsrmc());
		/**
		 * 基本信息(JBXX)-所属经济类型
		 */
		jbxx.setSsjjlx(form.getSsjjlx());
		/**
		 * 基本信息(JBXX)-联系电话
		 */
		jbxx.setLxdh(form.getLxdh());
		/**
		 * 基本信息(JBXX)-所属行业
		 */
		jbxx.setSshy(form.getSshy());
		/**
		 * 基本信息(JBXX)-征收方式
		 */
		jbxx.setZsfs(form.getZsfs());
		/**
		 * 基本信息(JBXX)-财会制度
		 */
		jbxx.setCkzd(form.getCkzd());
//		/**
//		 * 基本信息(JBXX)-工资管理形式
//		 */
//		jbxx.setGzglxs("");
//		/**
//		 * 基本信息(JBXX)-减免类型
//		 */
//		jbxx.setJmlx("");
		
		/**
		 * 基本信息(JBXX)-工资管理形式
		 */
		jbxx.setGzglxs(form.getGzglxs());
		/**
		 * 基本信息(JBXX)-减免类型
		 */
		jbxx.setJmlx(form.getJmlx());
		
		report.setJbxx(jbxx);
		
		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		report.setVersion(QysdsConstant2014.QYSDS_VERSION_HDZSQYSDSNB_2014);
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(form.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(form.getNsrmc());
		/**
		 * 报表期类型
		 */
		report.setBbqlx(form.getBbqlx());
		/**
		 * 期号
		 */
		report.setQh(form.getQh());
		/**
		 * 税款所属开始日期
		 */
		if(form.getSkssksrq()!=null && !form.getSkssksrq().equals("")){
			report.setSkssksrq(new Date(TinyTools.stringToDate(form.getSkssksrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 税款所属结束日期
		 */
		if(form.getSkssjsrq()!=null && !form.getSkssjsrq().equals("")){
			report.setSkssjsrq(new Date(TinyTools.stringToDate(form.getSkssjsrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 申报日期
		 */
		if(form.getSbrq()!=null && !form.getSbrq().equals("")){
			report.setSbrq(new Date(TinyTools.stringToDate(form.getSbrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 税款年度
		 */
		report.setSknd(form.getSknd());
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * 税务计算机代码
		 */
		report.setSwjsjdm(form.getSwjsjdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(form.getQxdm());
		/**
		 * 录入人
		 */
		report.setLrr(form.getLrr());
		/**
		 * 录入日期
		 */
		if(form.getLrrq()!=null && !form.getLrrq().equals("")){
//			report.setLrrq(new Date(TinyTools.stringToDate(form.getLrrq(),"yyyyMMdd").getTime()) );
		}
		report.setLrrq(new java.sql.Date(new java.util.Date().getTime()) );
		/**
		 * 创建人
		 */
		report.setCjr(form.getLrr());
		/**
		 * 创建日期
		 */
		if(form.getCjrq()!=null && !form.getCjrq().equals("")){
//			report.setCjrq(new Date(TinyTools.stringToDate(form.getCjrq(),"yyyyMMdd").getTime()));
		}
		report.setCjrq(new java.sql.Date(new java.util.Date().getTime()));
	}
		
	/**
	 * 过滤放入table数据中的空格
	 * @param table
	 * @return
	 */
	public static QysdsReportsTableDeclare cleanSpace(QysdsReportsTableDeclare table){
		Iterator it = table.getCellContentList().keySet().iterator();
		Map map=new HashMap();
		while(it.hasNext()){
			String key = (String)it.next();
			
			QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(key);
			if(!"".equals(item.getItemValue().trim())){
				map.put(key, item);
				Debug.out("key--"+key);
			}				
		}
		table.setCellContentList(map);
		return table;
		
	}
	
	/**
	 * 把存放数据时过滤掉的空格复原
	 * @param table
	 * @param a
	 * @return
	 */
	public static QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table,int arrs[]) {
		System.out.println("**显示qysdsNewUtil中的putSpace()**");
		for(int j=1;j<=arrs.length;j=j+2){			
			System.out.println("j___  "+j+"***"+arrs.length);
			for(int i=arrs[j-1];i<=arrs[j];i++){
				QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i));
				if(item==null ){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}else if(item!=null && item.getItemValue()!=null && "".equals(item.getItemValue().trim())){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}				
			}
		}	
		return table;
	}
	
	/**
	 * 判断是否是新开户
	 * 
	 * @param skssksrq
	 * @param djJbsj
	 * @return
	 */
	public String getSfxkh(String skssnd, SWDJJBSJ djJbsj) {
		String sfxkh = "N";
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(djJbsj.getKydjrq());
		// 开业登记年度
		int kydjnd = calendar.get(calendar.YEAR);
		if (kydjnd == Integer.parseInt(skssnd)) {
			sfxkh = "Y";
			;
		} else {
			sfxkh = "N";
		}
		return sfxkh;
	}

	/**
	 * 获取税款所属期所在年度的上一年度的征收方式代码
	 * 
	 * @param jsjdm
	 *            String
	 * @param skssrqq
	 *            Date
	 * @return String
	 * @throws BaseException
	 */
	public String getSyndZsfsDm(String jsjdm, String skssnd)
			throws BaseException, ApplicationException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String zsfsdm = CodeConstant.ZSFSDM_CZZS;
		int rdnd = Integer.parseInt(skssnd);
		try {
			con = SfDBResource.getConnection();
			sql.append("select * from sfdb.sf_jl_qysdszsfs_his where ");
			sql.append("jsjdm = '").append(jsjdm).append("' ");
			sql.append("and to_number(rdnd) < ").append(rdnd);
			sql.append(" order by rdnd desc, cjrq desc");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());

			if (rs.next()) {
				zsfsdm = rs.getString("ZSFSDM") == null ? CodeConstant.ZSFSDM_CZZS
						: rs.getString("ZSFSDM");
			}
			// 关闭数据库对象
			rs.close();
			pstmt.close();
			return zsfsdm;
		} catch (Exception ex) {
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(con);
		}
	}

	/**
	 * 查询查帐征收表关联分配表数据
	 * 
	 * @param conn
	 *            Connection
	 * @param form
	 *            ZfjgqysdsjbForm
	 * @throws BaseException
	 */
	public Map getSyndHsqjSbxx(String zsfsdm, String jsjdm, String skssnd)
			throws BaseException, ApplicationException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		int sknd = Integer.parseInt(skssnd);

		// 获取上一年度核定征收行6数据
		String syndZbh6 = "0.00";
		// 获取上一年度汇算清缴主表行9数据
		String syndZbh25 = "0.00";
		// 获取上一年度汇算清缴附表5行45、46、47的校验结果
		String syndFb5jyjg = "N";
		try {
			conn = SfDBResource.getConnection();
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfsdm)) {
				sql.append("select * from sbdb.sb_jl_qysdssbb_zb_nd where ");
				sql.append("nsrjsjdm = '").append(jsjdm).append("' ");
				sql.append("and bbqlx = '2' ");
				sql.append("and qh = '1' ");
				sql.append("and sknd = '").append(sknd - 1).append("' ");
				sql.append("and ((sbdm='1' and hc in ('9','25')) or (sbdm='10' and hc in ('45','46','47'))) ");
				System.out.println("sql:\n" + sql.toString());

				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery(sql.toString());

				String zbh25 = "0.00";
				String fb5h45 = "0.00";
				String fb5h46 = "0.00";
				String fb5h47 = "";

				while (rs.next()) {
					if (rs.getString("hc").equals("25")) {
						zbh25 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("45")) {
						fb5h45 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("46")) {
						fb5h46 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("47")) {
						fb5h47 = rs.getString("yz") == null ? "" : rs.getString("yz");
					}
				}

				syndZbh25 = zbh25;
				if (Double.parseDouble(zbh25) <= 30 * 10000) {
					if (fb5h47.equals("01")) {// 工业企业
						if (Double.parseDouble(fb5h45) <= 100 && Double.parseDouble(fb5h46) <= 3000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else if (fb5h47.equals("02")) {// 其他企业
						if (Double.parseDouble(fb5h45) <= 80 && Double.parseDouble(fb5h46) <= 1000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else {
						syndFb5jyjg = "N";
					}
				} else {
					syndFb5jyjg = "N";
				}
				// 关闭数据库对象
				rs.close();
				pstmt.close();
			} else {
				sql1.append(
						"select * from sbdb.sb_jl_qysdssbb_zb_jd t where nsrjsjdm='")
						.append(jsjdm).append("' and  sbdm='24' and hc='6'")
						.append("and sknd = '").append(sknd - 1).append("' ");
				pstmt1 = conn.prepareStatement(sql1.toString());
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					if (rs1.getString("hc").equals("6")) {
						syndZbh6 = rs1.getString("yz") == null ? "0.00" : rs1.getString("yz");
					}
				}
				// 关闭数据库对象
				rs1.close();
				pstmt1.close();
			}
			Map map = new HashMap();
			map.put("syndZbh6", syndZbh6);
			map.put("syndZbh25", syndZbh25);
			map.put("syndFb5jyjg", syndFb5jyjg);
			
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}
	/**
	 * 核定征收年报初始化代码表
	 * @param hdzssdsnbForm
	 */
	public static void initCodeTable(HdzssdsnbForm hdzssdsnbForm){
        //国家标准行业
        List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
        ArrayList gjbzhyList=new ArrayList();
        for(int i=0;i<gjbzhy.size();i++){
        	GJBZHY gjbzhyObject=(GJBZHY)gjbzhy.get(i);
        	String gjbzhydm=gjbzhyObject.getGjbzhydm();
        	if(gjbzhydm!=null && !gjbzhydm.equals("")&&gjbzhydm.length()==4){
        		gjbzhyList.add(gjbzhyObject);
//        		System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
        	}
        	
        }
        hdzssdsnbForm.setGjbzhy(gjbzhyList);
	}	
}
