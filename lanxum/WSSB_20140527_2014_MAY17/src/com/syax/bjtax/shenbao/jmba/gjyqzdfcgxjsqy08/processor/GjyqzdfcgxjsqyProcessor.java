package com.syax.bjtax.shenbao.jmba.gjyqzdfcgxjsqy08.processor;

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
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba03VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba08Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;
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
 * Description: （八）国家要求重点扶持的高新技术企业
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
public class GjyqzdfcgxjsqyProcessor implements Processor {
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
				// vo.setData(this.doQuery((Map)vo.getData()));
				// 初始化页面
			case JmbaActionConstant.INTI_ACTION_SHOW:
				Debug.out("processor111111111111");
				result = doShow(vo);

				return result;

				// 保存操作
			case JmbaActionConstant.INTI_ACTION_SAVE:
				result = doSave(vo);
				return result;
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	private Jmba08Vo getBamxvo(ResultSet rs) throws Exception {
		Jmba08Vo vo = new Jmba08Vo();
		try {
			vo.setXh(rs.getString("XH"));
			vo.setBand(rs.getString("BAND"));
			vo.setBasqwsh(rs.getString("BASQWSH"));
			vo.setCjr(rs.getString("CJR"));
			vo.setCjrq(rs.getString("CJRQ"));
			vo.setLrr(rs.getString("LRR"));
			vo.setLrrq(rs.getString("LRRQ"));
			vo.setJsjdm(rs.getString("JSJDM"));
			vo.setGxcpsrbl(rs.getString("GXCPSRBL"));
			vo.setGxjslydm(rs.getString("GXJSLYDM"));
			vo.setSfyfyjgmxb(rs.getString("F_FYJGMXB"));
			vo.setSfysygdfw(rs.getString("SFYSYGDFW"));
			vo.setSfyzjjzbg(rs.getString("SFYZJJZBG"));
			vo.setYffybl(MoneyUtils.format(rs.getString("YFFYBL")));
			vo.setYfrybl(rs.getString("YFRYBL"));
			vo.setZkysbl(rs.getString("ZKYSBL"));
			vo.setZsbh(rs.getString("ZSBH"));
			vo.setZsyxqsrq(rs.getString("ZSYXQSRQ")
					.substring(0, 10));
			vo.setZsyxzzrq(rs.getString("ZSYXZZRQ")
					.substring(0, 10));
			// mxvo.setZfgz( MoneyUtils.format(rs.getString("zfgz")));
			// mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
			return vo;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 初始化页面
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		Debug.out("进入业务八processor");
		// 获取备案申请文书号
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
//						.append(" from sfdb.sf_jl_qysdsjmsba_08 t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
//								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
//				sb
//						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
//								+ ud.getYhid() + "' and rownum=1 ");
//				System.out.println("===query wn sql==" + sb.toString());
//				ps = con.prepareStatement(sb.toString());
//				rs = ps.executeQuery();
//
//				while (rs.next()) {
//					Jmba08Vo mxvo = getBamxvo(rs);
//					mxvo.setXh("");
//					list.add(mxvo);
//				}
//
//				// }
//				vo1.setQysdsjmba(list);
				return vo1;
			}
			sb=new StringBuffer();
			sb.append("select * " );//t.SFYCJRMC,t.SFYSHBX,t.SFYLDHT,t.SFYCJRZM,t.SFYGZZM,
//					sb.append(" decode(t.SFYCJRMC,'0','是','1','否','') SFYCJRMCmc ,t.QTZL, ");
//            sb.append(" decode(t.SFYSHBX,'0','是','1','否','') SFYSHBXmc , ");
//            sb.append(" decode(t.SFYLDHT,'0','是','1','否','') SFYLDHTmc , ");
//            sb.append(" decode(t.SFYCJRZM,'0','是','1','否','') SFYCJRZMmc , ");
//            sb.append(" decode(t.SFYGZZM,'0','是','1','否','') SFYGZZMmc  ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_08 t where  basqwsh='"+bo.getBasqwsh()+"'");
			
			
			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}
			
			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {	
				 Jmba08Vo mxvo=getBamxvo(rs);	            
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
	 * 保存功能
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba08Vo mxvo=(Jmba08Vo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
		
		try {
			conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//新增
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_08 SET "+
				" GXJSLYDM='"+mxvo.getGxjslydm()+"',ZSBH='"+mxvo.getZsbh()+
				"',ZSYXQSRQ=to_date('"+mxvo.getZsyxqsrq()+"','yyyy-mm-dd'),ZSYXZZRQ=to_date('"+mxvo.getZsyxzzrq()+"','yyyy-mm-dd')"+
				",ZKYSBL='"+mxvo.getZkysbl()+
				"',YFRYBL='"+mxvo.getYfrybl()+
				"',YFFYBL='"+mxvo.getYffybl()+
				"',GXCPSRBL='"+mxvo.getGxcpsrbl()+
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";	
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_08 (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,GXJSLYDM,ZSBH,"
							+ " ZSYXQSRQ,ZSYXZZRQ,ZKYSBL,YFRYBL,YFFYBL,GXCPSRBL,CJR,CJRQ,LRR,LRRQ) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"','"+
				mxvo.getZsbh()+"',"+
				"to_date('"+mxvo.getZsyxqsrq()+"','yyyy-mm-dd'),to_date('"+mxvo.getZsyxzzrq()+"','yyyy-mm-dd'),"+
				mxvo.getZkysbl()+","+
				mxvo.getYfrybl()+",'"+
				mxvo.getYffybl()+"',"+
				mxvo.getGxcpsrbl()+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return list;
	}

	/**
	 * 查询功能
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}

	// /**
	// * 初始化页面
	// */
	// private Map doShow(Map data) throws BaseException {
	// Debug.out("进入业务八processor");
	// // 获取备案申请文书号
	// Map map = new HashMap();
	// String BASQWSH = (String) data.get("BASQWSH");
	// String jsjdm = (String) data.get("jsjdm");
	// System.out.println("BASQWSH = " + BASQWSH);
	// // 创建明细vo
	// Jmba08Vo vo = new Jmba08Vo();
	// Connection con = null;
	// ResultSet rs = null;
	// Statement st = null;
	// String tempgxjsls="";
	// try {
	// String sql = "select * from sfdb.sf_jl_qysdsjmsba_08 t where t.basqwsh =
	// '"
	// + BASQWSH + "' order by t.xh";
	// con = DBResource.getConnection();
	// st = con.createStatement();
	// rs = st.executeQuery(sql);
	// if (rs.next()) {
	// //如果有数据就是修改
	// vo.setXh(rs.getString("XH"));
	// vo.setBand(rs.getString("BAND"));
	// vo.setBasqwsh(rs.getString("BASQWSH"));
	// vo.setCjr(rs.getString("CJR"));
	// vo.setCjrq(rs.getString("CJRQ"));
	// vo.setLrr(rs.getString("LRR"));
	// vo.setLrrq(rs.getString("LRRQ").substring(0, 10));
	// vo.setJsjdm(rs.getString("JSJDM"));
	// vo.setGxcpsrbl(rs.getString("GXCPSRBL"));
	// vo.setGxjslydm(rs.getString("GXJSLYDM"));
	// vo.setSfyfyjgmxb(rs.getString("F_FYJGMXB"));
	// vo.setSfysygdfw(rs.getString("SFYSYGDFW"));
	// vo.setSfyzjjzbg(rs.getString("SFYZJJZBG"));
	// vo.setYffybl(rs.getString("YFFYBL"));
	// vo.setYfrybl(rs.getString("YFRYBL"));
	// vo.setZkysbl(rs.getString("ZKYSBL"));
	// vo.setZsbh(rs.getString("ZSBH"));
	// vo.setZsyxqsrq(rs.getString("ZSYXQSRQ").substring(0, 10));
	// vo.setZsyxzzrq(rs.getString("ZSYXZZRQ").substring(0, 10));
	// tempgxjsls=rs.getString("GXJSLYDM");
	// } else {
	// // 如果没有数据，代表是新增，则查询主表和子表是否有数据。
	// sql = "select * from SFDB.SF_JL_QYSDSJMSBAJL t,sfdb.sf_jl_qysdsjmsba_08
	// t1 where t.BASQWSH=t1.BASQWSH and t.band = '"
	// + (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1 + "")
	// + "' and t.JMBASXDM='0080' and t.jsjdm='" + jsjdm + "'";
	// if (rs != null) {
	// rs.close();
	// }
	// if (st != null) {
	// st.close();
	// }
	// if (con != null) {
	// con.close();
	// }
	// con = DBResource.getConnection();
	// st = con.createStatement();
	// rs = st.executeQuery(sql);
	// if (rs.next()) {
	// // 有数据新增
	// vo.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4())
	// - 1 + "");
	// vo.setLrr(jsjdm);
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// vo.setLrrq(format.format(new Date(System
	// .currentTimeMillis())));
	// } else {
	// // 没有数据查询上年数据
	// sql = "select t1.* from SFDB.SF_JL_QYSDSJMSBAJL
	// t,sfdb.sf_jl_qysdsjmsba_08 t1 where t.BASQWSH=t1.BASQWSH and t.band = '"
	// + (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 2 + "")
	// + "' and t.JMBASXDM='0080' and t.jsjdm='"
	// + jsjdm
	// + "' and t.SQZT='4'";
	// if (rs.next()) {
	// // 如果有，带出上年数据
	//
	// vo.setXh(rs.getString("XH"));
	// vo.setBand(rs.getString("BAND"));
	// vo.setBasqwsh(rs.getString("BASQWSH"));
	// vo.setCjr(rs.getString("CJR"));
	// vo.setCjrq(rs.getString("CJRQ"));
	// vo.setLrr(rs.getString("LRR"));
	// vo.setLrrq(rs.getString("LRRQ"));
	// vo.setJsjdm(rs.getString("JSJDM"));
	// vo.setGxcpsrbl(rs.getString("GXCPSRBL"));
	// vo.setGxjslydm(rs.getString("GXJSLYDM"));
	// vo.setSfyfyjgmxb(rs.getString("F_FYJGMXB"));
	// vo.setSfysygdfw(rs.getString("SFYSYGDFW"));
	// vo.setSfyzjjzbg(rs.getString("SFYZJJZBG"));
	// vo.setYffybl(rs.getString("YFFYBL"));
	// vo.setYfrybl(rs.getString("YFRYBL"));
	// vo.setZkysbl(rs.getString("ZKYSBL"));
	// vo.setZsbh(rs.getString("ZSBH"));
	// vo.setZsyxqsrq(rs.getString("ZSYXQSRQ").substring(0, 10));
	// vo.setZsyxzzrq(rs.getString("ZSYXZZRQ").substring(0, 10));
	// tempgxjsls=rs.getString("GXJSLYDM");
	//
	// } else {
	// // 如果没有新增
	// vo.setBand(Integer.parseInt(DateUtilPro
	// .getCurYearStr4())
	// - 1 + "");
	// vo.setLrr(jsjdm);
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// vo.setLrrq(format.format(new Date(System
	// .currentTimeMillis())));
	// }
	// }
	// }
	// // 将明细VO设置到map
	// map.put("Jmba08VO", vo);
	// // 将将主表VO设置到map中
	// map.put("JmbaZbVO", PublicAccess.getJmbaZbVO(con, BASQWSH));
	// map.put("tempgxjsls", tempgxjsls);
	// // map.put("JmbaZlqdVO", PublicAccess.getJmbaZlqdVO(con, BASQWSH));
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// } finally {
	// try {
	// if (rs != null) {
	// rs.close();
	// }
	// } catch (Exception exx) {
	// }
	// try {
	// if (st != null) {
	// st.close();
	// }
	// } catch (Exception exx) {
	// }
	// try {
	// if (con != null) {
	// con.close();
	// }
	// } catch (Exception exx) {
	// }
	// }
	//
	// return map;
	// }

}
