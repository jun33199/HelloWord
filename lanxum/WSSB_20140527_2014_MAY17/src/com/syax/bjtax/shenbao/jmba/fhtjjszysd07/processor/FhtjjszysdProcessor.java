package com.syax.bjtax.shenbao.jmba.fhtjjszysd07.processor;

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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba07Vo;
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
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;

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
public class FhtjjszysdProcessor implements Processor {

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

			case JmbaActionConstant.INTI_ACTION_SHOW:
				Debug.out("processor111111111111");
				result = doShow(vo);

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

	/**
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}
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

	/**
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba07Vo mxvo=(Jmba07Vo)vo1.getQysdsjmba().get(0);
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
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_07 SET "+
				" JSZRLXDM='"+mxvo.getJszrlxdm()+"',JNJWBS='"+mxvo.getJnjwbs()+
				
				"',JSZRSD='"+mxvo.getJszrsd()+
				"',JSZRHTMC='"+mxvo.getJszrhtmc()+
				
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";	
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_07 (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,JSZRLXDM,"
					+ " JNJWBS,JSZRHTMC,JSZRSD,CJR,CJRQ,LRR,LRRQ) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getJszrlxdm()+"',"+
				mxvo.getJnjwbs()+",'"+
				mxvo.getJszrhtmc()+"','"+
				mxvo.getJszrsd()+
				"','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
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
	private Jmba07Vo getBamxvo(ResultSet rs) throws Exception {
		Jmba07Vo vo = new Jmba07Vo();
		try {
			vo.setXh(rs.getString("XH"));
			vo.setBand(rs.getString("BAND"));
			vo.setBasqbh(rs.getString("BASQWSH"));
			vo.setCjr(rs.getString("CJR"));
			vo.setCjrq(rs.getString("CJRQ"));
			vo.setLrr(rs.getString("LRR"));
			vo.setLrrq(rs.getString("LRRQ").substring(0, 10));
			vo.setJsjdm(rs.getString("JSJDM"));
			vo.setJszrlxdm(rs.getString("JSZRLXDM"));
			//vo.setJszrlxmc(rs.getString("JSZRLXMC"));
            vo.setJnjwbs(rs.getString("JNJWBS"));
            vo.setJszrhtmc(rs.getString("JSZRHTMC"));
			vo.setJszrsd(MoneyUtils.format(rs.getString("JSZRSD")));
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
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
//				sb = new StringBuffer();
//
//				sb.append("select * ");
//				sb
//						.append(" from sfdb.sf_jl_qysdsjmsba_07 t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
//								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
//				sb
//						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
//								+ ud.getYhid() + "' and rownum=1 ");
//				System.out.println("===query wn sql==" + sb.toString());
//				ps = con.prepareStatement(sb.toString());
//				rs = ps.executeQuery();
//
//				while (rs.next()) {
//					Jmba07Vo mxvo = getBamxvo(rs);
//					mxvo.setXh("");
//					list.add(mxvo);
//				}

				// }
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

            sb.append(" from sfdb.sf_jl_qysdsjmsba_07 t where  basqwsh='"+bo.getBasqwsh()+"'");
			
			
			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}
			
			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {	
				 Jmba07Vo mxvo=getBamxvo(rs);	            
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
