package com.syax.bjtax.shenbao.jmba.basx013b.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba013bVo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 *
 * <p>
 * Description: （七）符合条件的技术转让所得
 * </p>
 *
 * <p>
 * Copyright: 四一安信
 * </p>
 *
 * <p>
 * Company: 四一安信
 * </p>
 *
 * @author wangcl
 * @version 1.1
 */
public class Basx13bProcessor implements Processor {

	/**
	 * 总控数据
	 */
	private UserData userData;

	/**
	 * 处理请求
	 */
	public Object process(VOPackage vo) throws BaseException {
		this.userData = vo.getUserData();
		Object result = null;
		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case JmbaActionConstant.INTI_ACTION_QUERY:
				Debug.out("processor111111111111");
				result = doQuery(vo);

				return result;
			case JmbaActionConstant.INTI_ACTION_QUERY1:
				Debug.out("processor111111111111");
				result = doQuery1(vo);

				return result;
			case JmbaActionConstant.INTI_ACTION_SHOW:
				Debug.out("processor111111111111");
				result = doShow(vo);

				return result;
			case JmbaActionConstant.INTI_ACTION_SHOW1:
				Debug.out("processor111111111111");
				result = doShowview(vo);

				return result;
			case JmbaActionConstant.INTI_ACTION_SAVE:
				Debug.out("JMBA07 SAVE");
				result = doSave(vo);
				return result;
			case JmbaActionConstant.INTI_ACTION_DELETE:
				vo.setData(this.doDelete((Map) vo.getData()));
				return vo;
			case JmbaActionConstant.INTI_ACTION_COMMIT:
				vo.setData(this.doCommit((Map) vo.getData()));
				return vo;
				// 没有可调用的方法
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	// 查询页面，展示的是多条数据。
	private Object doShowview(VOPackage vo) throws BaseException {
		Debug.out("processor2222222222222222");
		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			con = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(con, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			  // int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
//			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
//				vo1 = new JmbaZbVO();
//				vo1.setBand(bo.getBand());
//				vo1.setBasqbh(bo.getBasqbh());
//				vo1.setBasqwsh(bo.getBasqwsh());
//				vo1.setJmbasxdm(bo.getJmbasxdm());
//				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
//						.format(new Date()));
//				sb = new StringBuffer();
//
////				sb.append("select * ");
////				sb
////						.append(" from sfdb.sf_jl_qysdsjmsba_13b t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
////								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
////				sb
////						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
////								+ ud.getYhid() + "' and rownum=1 ");
//				 String wnsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs ,a.xh,a.basqwsh,a.lrr,a.lrrq,a.btzqyjsjdm,a.gxjslydm,a.btzqymc,a.btzqyswdjzh,a.btzqyssd " +
//	                " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";
//
//	            for(int i = 2006; i <= band; i++) {
//	                /*需根据备案年度动态构造*/
//	            	if(i==band){
//	                wnsql += "select '" + i + "' tznd from dual  ";
//	            	}else{
//	            		 wnsql += "select '" + i + "' tznd from dual  union ";
//	            	}
//	            }
//	            wnsql += " ) b  ";
//	            wnsql += " where a.tznd(+)=b.tznd ";
//	            wnsql += " and a.btzqyjsjdm(+)=null";
//	            wnsql += " order by b.tznd ";
//				System.out.println("===query wn sql==" +wnsql);
//				ps = con.prepareStatement(wnsql);
//				rs = ps.executeQuery();
//
//				while (rs.next()) {
//					Jmba013bVo mxvo = getBamxvo(rs);
//					mxvo.setXh("");
//					list.add(mxvo);
//				}
//
//				// }
//				vo1.setQysdsjmba(list);
//				return vo1;
//			}

			 String wnsql = "select a.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs ,a.xh,a.basqwsh,a.lrr,a.lrrq,a.btzqyjsjdm,a.gxjslydm,a.btzqymc,a.btzqyswdjzh,a.btzqyssd " +
             " from sfdb.sf_jl_qysdsjmsba_13b_his a  ";




         wnsql += " where a.basqwsh='" + vo1.getBasqwsh() +"' ";

         wnsql += " order by a.tznd ";
			System.out.println("===query wn sql==" +wnsql);
			ps = con.prepareStatement(wnsql);

			rs = ps.executeQuery();
			while (rs.next()) {
				Jmba013bVo mxvo=getBamxvo(rs);
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(con);
		}

		return vo1;
	}

	/**
	 */

	private Map doCommit(Map data) throws BaseException {
		Connection con = null;
		PreparedStatement st = null;
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		con = DBResource.getConnection();
		String sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
		try {
		st = con.prepareStatement(sql);
		st.setString(1, BASQWSH);
		st.executeUpdate();
        st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}
		return null;
	}

	private Map doDelete(Map data) throws BaseException {
		System.out.println("into Processor Delete");

		Connection con = null;
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		String type = (String) data.get("type");
		String selIndex = (String) data.get("selIndex");
		try {
			con = DBResource.getConnection();
			java.sql.Statement delSt = con.createStatement();

			String del = "delete from sfdb.sf_jl_qysdsjmsba_07 t where t.basqwsh = '"
					+ BASQWSH + "' and t.xh = '" + selIndex + "'";
			delSt.execute(del);

			delSt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}
		return null;
	}
	private Object doQuery1(VOPackage vo) throws BaseException {
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		Jmba013bVo mxvo=(Jmba013bVo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor query");
		String sql = "";
		String sjjsjdm = "";
		String sjswdjzh = "";
		String sjnsrmc = "";
		ResultSet rs = null;
		int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
		try {

			conn = DBResource.getConnection();
				//查询基本数据表信息
				sql="select t.jsjdm,t.swdjzh,t.nsrmc from djdb.dj_jl_jbsj t ";
				if(!"".equals(mxvo.getSwdjzh())&&mxvo.getSwdjzh()!=null){
					sql+="where t.swdjzh='"+mxvo.getSwdjzh()+"'";
				}
				System.out.println("into Processor query----基本信息--"+sql);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					sjjsjdm=(rs.getString("JSJDM"));
					sjswdjzh=(rs.getString("SWDJZH"));
					sjnsrmc=(rs.getString("NSRMC"));

				}
                if (rs != null) {
                    rs.close();
                }

				if (ps != null) {
					ps.close();
				}
				sql="select  b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs,a.GXJSLYDM " +
	             " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";
				 for(int i = 2006; i <= band; i++) {
		             /*需根据备案年度动态构造*/
		         	if(i==band){
		         		sql += "select '" + i + "' tznd from dual  ";
		         	}else{
		         		sql += "select '" + i + "' tznd from dual  union ";
		         	}
		         }
				 sql += " ) b  ";
				 sql += " where a.tznd(+)=b.tznd ";
				 sql += "and a.ywcbabs(+)='0'";
				 if(!"".equals(mxvo.getSwdjzh())&&mxvo.getSwdjzh()!=null){
					 sql += " and a.BTZQYSWDJZH(+)='" +mxvo.getSwdjzh() +"' ";
				 }

				 sql += " and a.jsjdm(+)='" + mxvo.getJsjdm() +"' ";
				 sql += " order by b.tznd ";
				ps = conn.prepareStatement(sql);
				System.out.println("into Processor query----历史数据--"+sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Jmba013bVo mxvo1=new Jmba013bVo();
					System.out.println("into Processor query----历史数据--"+rs.getString("TZND"));
					mxvo1.setBtzqyjsjdm(sjjsjdm);
					mxvo1.setSwdjzh(sjswdjzh);
					mxvo1.setBtzqyssd("0");
					mxvo1.setBtzqymc(sjnsrmc);
					mxvo1.setGxjslydm(rs.getString("GXJSLYDM"));
					mxvo1.setTznd(rs.getString("TZND"));
					mxvo1.setTze(rs.getDouble("TZE")+"");
					mxvo1.setDke(rs.getDouble("DKE")+"");
					mxvo1.setDnkdke(rs.getDouble("DNKDKE")+"");
					mxvo1.setJze(rs.getDouble("JZE")+"");
					mxvo1.setYwcbabs(rs.getString("YWCBABS"));
		            list.add(mxvo1);
				}
				vo1.setQysdsjmba(list);
                vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
            if(rs != null) {
                rs.close();
            }

				if (ps != null) {
					ps.close();
				}


		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return vo1;
	}
	private Object doQuery(VOPackage vo) throws BaseException {
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		Jmba013bVo mxvo=(Jmba013bVo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor query");
		String sql = "";
		String sjjsjdm = "";
		String sjswdjzh = "";
		String sjnsrmc = "";
		ResultSet rs = null;
		int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
		try {

			conn = DBResource.getConnection();
				//查询基本数据表信息
				sql="select t.jsjdm,t.swdjzh,t.nsrmc from djdb.dj_jl_jbsj t ";
				if(!"".equals(mxvo.getBtzqyjsjdm())&&mxvo.getBtzqyjsjdm()!=null){
					sql+="where t.jsjdm='"+mxvo.getBtzqyjsjdm()+"'";
				}
				System.out.println("into Processor query----基本信息--"+sql);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					sjjsjdm=(rs.getString("JSJDM"));
					sjswdjzh=(rs.getString("SWDJZH"));
					sjnsrmc=(rs.getString("NSRMC"));

				}
                if (rs != null) {
                    rs.close();
                }

				if (ps != null) {
					ps.close();
				}
				sql="select  b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs,a.GXJSLYDM " +
	             " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";
				 for(int i = 2006; i <= band; i++) {
		             /*需根据备案年度动态构造*/
		         	if(i==band){
		         		sql += "select '" + i + "' tznd from dual  ";
		         	}else{
		         		sql += "select '" + i + "' tznd from dual  union ";
		         	}
		         }
				 sql += " ) b  ";
				 sql += " where a.tznd(+)=b.tznd ";
				 sql += "and a.ywcbabs(+)='0'";
				 if(!"".equals(mxvo.getBtzqyjsjdm())&&mxvo.getBtzqyjsjdm()!=null){
					 sql += " and a.btzqyjsjdm(+)='" +mxvo.getBtzqyjsjdm() +"' ";
				 }
				 sql += " and a.jsjdm(+)='" + mxvo.getJsjdm() +"' ";
				 sql += " order by b.tznd ";
				ps = conn.prepareStatement(sql);
				System.out.println("into Processor query----历史数据--"+sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Jmba013bVo mxvo1=new Jmba013bVo();
					System.out.println("into Processor query----历史数据--"+rs.getString("TZND"));
					mxvo1.setBtzqyjsjdm(sjjsjdm);
					mxvo1.setSwdjzh(sjswdjzh);
					mxvo1.setBtzqymc(sjnsrmc);
                    mxvo1.setBtzqyssd("0");
					mxvo1.setGxjslydm(rs.getString("GXJSLYDM"));
					mxvo1.setTznd(rs.getString("TZND"));
					mxvo1.setTze(rs.getDouble("TZE")+"");
					mxvo1.setDke(rs.getDouble("DKE")+"");
					mxvo1.setDnkdke(rs.getDouble("DNKDKE")+"");
					mxvo1.setJze(rs.getDouble("JZE")+"");
					mxvo1.setYwcbabs(rs.getString("YWCBABS"));
		            list.add(mxvo1);
				}
				vo1.setQysdsjmba(list);
                vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
            if (rs != null) {
                    rs.close();
                }

				if (ps != null) {
					ps.close();
				}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return vo1;
	}

	/**
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		Jmba013bVo mxvo=(Jmba013bVo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
		int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
		String sql = "";
		try {
			conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//新增
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
				//新增时先将数据搬出去
//				sql="insert into sfdb.sf_jl_qysdsjmsba_13b_his a select * from sfdb.sf_jl_qysdsjmsba_13b b where b.BTZQYSWDJZH='"+mxvo.getSwdjzh()+"'";
//				ps = conn.prepareStatement(sql);
//				ps.execute();
//				if (ps != null) {
//					ps.close();
//				}
				//将原来数据删掉
				sql="delete sfdb.sf_jl_qysdsjmsba_13b t where t.BTZQYSWDJZH='"+mxvo.getSwdjzh()+"' and t.jsjdm='"+mxvo.getJsjdm()+"'";
				ps = conn.prepareStatement(sql);
				ps.execute();
				if (ps != null) {
					ps.close();
				}
				sql="delete sfdb.sf_jl_qysdsjmsba_13b_his t where t.BASQWSH='"+vo1.getBasqwsh()+"'";
				ps = conn.prepareStatement(sql);
				ps.execute();
				if (ps != null) {
					ps.close();
				}
				//插入新的数据
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,GXJSLYDM,"
					+ " BTZQYJSJDM,BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,DNKDKE,JZE,CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
				 +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"',";
				if("1".equals(mxvo.getBtzqyssd())){
				sql=sql+"''"+",'";
				}else{
				sql=sql+"'"+mxvo.getBtzqyjsjdm()+"','";
				}
				sql=sql+
				mxvo.getSwdjzh()+"','"+
				mxvo.getBtzqymc()+
				"','"+mxvo.getBtzqyssd()+

				"',?,?,?,?,?,'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
				System.out.println("02Processor===doSave===sql="+sql);
				ps = conn.prepareStatement(sql);
				for(int i=0;i<=(band-2006);i++){
					System.out.println("02Processor===doSave===sql="+ mxvo.getTznd().split("\\,")[i]+"--"+mxvo.getTze().split("\\,")[i]+"--"+mxvo.getDke().split("\\,")[i]+"--"+mxvo.getDnkdke().split("\\,")[i]+"--"+mxvo.getJze().split("\\,")[i]+"--");
					ps.setString(1, qysdsUtil.getSequence());
					ps.setString(2, mxvo.getTznd().split("\\,")[i]);
					ps.setString(3, mxvo.getTze().split("\\,")[i]);
					ps.setString(4, mxvo.getDke().split("\\,")[i]);
					ps.setString(5, mxvo.getDnkdke().split("\\,")[i]);
					ps.setString(6, mxvo.getJze().split("\\,")[i]);
					ps.execute();
				}
				if (ps != null) {
					ps.close();
				}
				//历史表中插入新的数据
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b_his (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,GXJSLYDM,"
					+ " BTZQYJSJDM,BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,DNKDKE,JZE,CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
				 +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"',";
				if("1".equals(mxvo.getBtzqyssd())){
					sql=sql+"''"+",'";
					}else{
					sql=sql+"'"+mxvo.getBtzqyjsjdm()+"','";
					}
					sql=sql+
				mxvo.getSwdjzh()+"','"+
				mxvo.getBtzqymc()+
				"',"+mxvo.getBtzqyssd()+

				",?,?,?,?,?,'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
				System.out.println("02Processor===doSave===sql="+sql);
				ps = conn.prepareStatement(sql);
				for(int i=0;i<=(band-2006);i++){
					System.out.println("02Processor===doSave===sql="+ mxvo.getTznd().split("\\,")[i]+"--"+mxvo.getTze().split("\\,")[i]+"--"+mxvo.getDke().split("\\,")[i]+"--"+mxvo.getDnkdke().split("\\,")[i]+"--"+mxvo.getJze().split("\\,")[i]+"--");
					ps.setString(1, qysdsUtil.getSequence());
					ps.setString(2, mxvo.getTznd().split("\\,")[i]);
					ps.setString(3, mxvo.getTze().split("\\,")[i]);
					ps.setString(4, mxvo.getDke().split("\\,")[i]);
					ps.setString(5, mxvo.getDnkdke().split("\\,")[i]);
					ps.setString(6, mxvo.getJze().split("\\,")[i]);
					ps.execute();
				}
				if (ps != null) {
					ps.close();
				}
			}else{
					//修改操作是这样的，先根据备案申请文书号删除记录，在新增数据
				sql="delete sfdb.sf_jl_qysdsjmsba_13b t where t.BASQWSH='"+vo1.getBasqwsh()+"'";
				ps = conn.prepareStatement(sql);
				ps.execute();
				if (ps != null) {
					ps.close();
				}
				sql="delete sfdb.sf_jl_qysdsjmsba_13b_his t where t.BASQWSH='"+vo1.getBasqwsh()+"'";
				ps = conn.prepareStatement(sql);
				ps.execute();
				if (ps != null) {
					ps.close();
				}
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,GXJSLYDM,"
					+ " BTZQYJSJDM,BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,DNKDKE,JZE,CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
				 +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"',";
				if("1".equals(mxvo.getBtzqyssd())){
					sql=sql+"''"+",'";
					}else{
					sql=sql+"'"+mxvo.getBtzqyjsjdm()+"','";
					}
					sql=sql+
				mxvo.getSwdjzh()+"','"+
				mxvo.getBtzqymc()+
				"',"+mxvo.getBtzqyssd()+

				",?,?,?,?,?,'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
				System.out.println("02Processor===doSave===sql="+sql);
				ps = conn.prepareStatement(sql);
				for(int i=0;i<=(band-2006);i++){
					System.out.println("02Processor===doSave===sql="+ mxvo.getTznd().split("\\,")[i]+"--"+mxvo.getTze().split("\\,")[i]+"--"+mxvo.getDke().split("\\,")[i]+"--"+mxvo.getDnkdke().split("\\,")[i]+"--"+mxvo.getJze().split("\\,")[i]+"--");
					ps.setString(1, qysdsUtil.getSequence());
					ps.setString(2, mxvo.getTznd().split("\\,")[i]);
					ps.setString(3, mxvo.getTze().split("\\,")[i]);
					ps.setString(4, mxvo.getDke().split("\\,")[i]);
					ps.setString(5, mxvo.getDnkdke().split("\\,")[i]);
					ps.setString(6, mxvo.getJze().split("\\,")[i]);
					ps.addBatch();
				}
				ps.executeBatch();
				if (ps != null) {
					ps.close();
				}
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b_his (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,GXJSLYDM,"
					+ " BTZQYJSJDM,BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,DNKDKE,JZE,CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
				 +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"',";
				if("1".equals(mxvo.getBtzqyssd())){
					sql=sql+"''"+",'";
					}else{
					sql=sql+"'"+mxvo.getBtzqyjsjdm()+"','";
					}
					sql=sql+
				mxvo.getSwdjzh()+"','"+
				mxvo.getBtzqymc()+
				"',"+mxvo.getBtzqyssd()+

				",?,?,?,?,?,'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
				System.out.println("02Processor===doSave===sql="+sql);
				ps = conn.prepareStatement(sql);
				for(int i=0;i<=(band-2006);i++){
					System.out.println("02Processor===doSave===sql="+ mxvo.getTznd().split("\\,")[i]+"--"+mxvo.getTze().split("\\,")[i]+"--"+mxvo.getDke().split("\\,")[i]+"--"+mxvo.getDnkdke().split("\\,")[i]+"--"+mxvo.getJze().split("\\,")[i]+"--");
					ps.setString(1, qysdsUtil.getSequence());
					ps.setString(2, mxvo.getTznd().split("\\,")[i]);
					ps.setString(3, mxvo.getTze().split("\\,")[i]);
					ps.setString(4, mxvo.getDke().split("\\,")[i]);
					ps.setString(5, mxvo.getDnkdke().split("\\,")[i]);
					ps.setString(6, mxvo.getJze().split("\\,")[i]);
					ps.addBatch();
				}
				ps.executeBatch();
				if (ps != null) {
					ps.close();
				}
			}



		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return list;
	}
	private Jmba013bVo getBamxvo(ResultSet rs) throws Exception {
		Jmba013bVo vo = new Jmba013bVo();
		try {
			Debug.out("查看投资额--------------------"+rs.getString("DKE"));
			Debug.out("查看投资额--------------------"+rs.getDouble("DKE"));
			vo.setXh(rs.getString("XH"));
			//vo.setBand(rs.getString("BAND"));
			vo.setBasqbh(rs.getString("BASQWSH"));
			vo.setLrr(rs.getString("LRR"));
			//vo.setLrrq(rs.getString("LRRQ").substring(0, 10));
			vo.setBtzqyjsjdm(rs.getString("BTZQYJSJDM"));
			vo.setGxjslydm(rs.getString("GXJSLYDM"));
            vo.setBtzqymc(rs.getString("BTZQYMC"));
            vo.setSwdjzh(rs.getString("BTZQYSWDJZH"));
            vo.setTznd(rs.getString("TZND"));
            vo.setTze(rs.getDouble("TZE")+"");
            vo.setDke(rs.getDouble("DKE")+"");
            vo.setDnkdke(rs.getDouble("DNKDKE")+"");
            vo.setJze(rs.getDouble("JZE")+"");
            vo.setYwcbabs(rs.getString("YWCBABS"));
            vo.setBtzqyssd(rs.getString("BTZQYSSD"));
			// mxvo.setZfgz( MoneyUtils.format(rs.getString("zfgz")));
			// mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
			return vo;
		} catch (Exception e) {
			throw e;
		}
	}
	// 查询页面，展示的是多条数据。
	private Object doShow(VOPackage vo) throws BaseException {
		Debug.out("processor2222222222222222");
		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			con = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(con, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			   int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				sb = new StringBuffer();

//				sb.append("select * ");
//				sb
//						.append(" from sfdb.sf_jl_qysdsjmsba_13b t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
//								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
//				sb
//						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
//								+ ud.getYhid() + "' and rownum=1 ");
				 String wnsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs ,a.xh,a.basqwsh,a.lrr,a.lrrq,a.btzqyjsjdm,a.gxjslydm,a.btzqymc,a.btzqyswdjzh,nvl(a.btzqyssd,0) btzqyssd " +
	                " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";

	            for(int i = 2006; i <= band; i++) {
	                /*需根据备案年度动态构造*/
	            	if(i==band){
	                wnsql += "select '" + i + "' tznd from dual  ";
	            	}else{
	            		 wnsql += "select '" + i + "' tznd from dual  union ";
	            	}
	            }
	            wnsql += " ) b  ";
	            wnsql += " where a.tznd(+)=b.tznd ";
	            wnsql += " and a.btzqyjsjdm(+)=null";
	            wnsql += " order by b.tznd ";
				System.out.println("===query wn sql==" +wnsql);
				ps = con.prepareStatement(wnsql);
				rs = ps.executeQuery();

				while (rs.next()) {
					Jmba013bVo mxvo = getBamxvo(rs);
					mxvo.setXh("");
					list.add(mxvo);
				}

				// }
				vo1.setQysdsjmba(list);
				return vo1;
			}

			 String wnsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs ,a.xh,a.basqwsh,a.lrr,a.lrrq,a.btzqyjsjdm,a.gxjslydm,a.btzqymc,a.btzqyswdjzh,a.btzqyssd " +
             " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";

         for(int i = 2006; i <= band; i++) {
             /*需根据备案年度动态构造*/
         	if(i==band){
             wnsql += "select '" + i + "' tznd from dual  ";
         	}else{
         		 wnsql += "select '" + i + "' tznd from dual  union ";
         	}
         }
         wnsql += " ) b  ";
         wnsql += " where a.tznd(+)=b.tznd ";

         wnsql += " and a.basqwsh='" + vo1.getBasqwsh() +"' ";

         wnsql += " order by b.tznd ";
			System.out.println("===query wn sql==" +wnsql);
			ps = con.prepareStatement(wnsql);

			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				Jmba013bVo mxvo=getBamxvo(rs);
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(con);
		}

		return vo1;
	}

}
