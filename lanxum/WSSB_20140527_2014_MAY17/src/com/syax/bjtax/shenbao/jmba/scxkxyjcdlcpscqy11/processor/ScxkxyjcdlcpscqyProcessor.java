package com.syax.bjtax.shenbao.jmba.scxkxyjcdlcpscqy11.processor;

import java.sql.CallableStatement;
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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba09Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba11Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
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
 * Title: ������˰��������ϵͳ���������걨
 * </p>
 *
 * <p>
 * Description: (ʮһ)�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ
 * </p>
 *
 * <p>
 * Copyright: ��һ����
 * </p>
 *
 * <p>
 * Company: ��һ����
 * </p>
 *
 * @author wangcl
 * @version 1.1
 */
public class ScxkxyjcdlcpscqyProcessor implements Processor {
	/**
	 * �ܿ�����
	 */
	private UserData userData;

	/**
	 * ��������
	 */
	public Object process(VOPackage vo) throws BaseException {
		this.userData = vo.getUserData();
		Object result = null;
		// ����ҵ���������ֵ����ҵ�����
		try {
			switch (vo.getAction()) {
			// ��ѯ
			case JmbaActionConstant.INTI_ACTION_QUERY:
				// vo.setData(this.doQuery((Map)vo.getData()));
				// ��ʼ��ҳ��
			case JmbaActionConstant.INTI_ACTION_SHOW:
				Debug.out("processor111111111111");
				result = doShow(vo);

				return result;
				// �������
			case JmbaActionConstant.INTI_ACTION_SAVE:
				Debug.out("JMBA09 SAVE");
				result = doSave(vo);
				return result;
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * ���湦��
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		Jmba11Vo mxvo=(Jmba11Vo)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
		try {
			conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//����
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_11 SET "+

				"HLND='"+mxvo.getHlnd()+
				"',MZQSND='"+mxvo.getMzqsnd()+
				"',MZZZND='"+mxvo.getMzzznd()+
				"',JZQSND='"+mxvo.getJzqsnd()+
				"',JZZZND='"+mxvo.getJzzznd()+
				"',YJJMSE='"+mxvo.getYjjmse()+
				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_11 (XH,BASQWSH,JSJDM,BAND,swjgzzjgdm,HLND,"
							+ " JZQSND,MZZZND,MZQSND,JZZZND,YJJMSE,CJR,CJRQ,LRR,LRRQ) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getHlnd()+"','"+
				mxvo.getJzqsnd()+"','"+
				mxvo.getMzzznd()+"','"+
				mxvo.getMzqsnd()+"','"+
				mxvo.getJzzznd()+"','"+
				mxvo.getYjjmse()+"','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
		} finally {
			DBResource.destroyConnection(conn);
		}

		return list;
	}

	/**
	 * ��ѯ����
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}

	/**
	 * ��ʼ��ҳ��
	 */
	private Jmba11Vo getBamxvo(ResultSet rs) throws Exception {
		Jmba11Vo vo = new Jmba11Vo();
		try {
			vo.setXh(rs.getString("XH"));
			vo.setBand(rs.getString("BAND"));
			vo.setBasqwsh(rs.getString("BASQWSH"));
			vo.setCjr(rs.getString("CJR"));
			vo.setCjrq(rs.getString("CJRQ"));
			vo.setLrr(rs.getString("LRR"));
			vo.setLrrq(rs.getString("LRRQ").substring(0, 10));
			vo.setJsjdm(rs.getString("JSJDM"));
			vo.setHlnd(rs.getString("HLND"));
			vo.setJzqsnd(rs.getString("JZQSND"));
			vo.setJzzznd(rs.getString("JZZZND"));
			vo.setMzqsnd(rs.getString("MZQSND"));
			vo.setMzzznd(rs.getString("MZZZND"));
			vo.setYjjmse(MoneyUtils.format(rs.getString("YJJMSE")));
			// mxvo.setZfgz( MoneyUtils.format(rs.getString("zfgz")));
			// mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
			return vo;
		} catch (Exception e) {
			throw e;
		}
	}

	private Object doShow(VOPackage vo) throws BaseException {
		Debug.out("����ҵ��ʮһprocessor");
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
//				sb.append("select  tt.*,rownum zs from (select t.* ");
//				sb
//						.append(" from sfdb.sf_jl_qysdsjmsba_11 t,sfdb.sf_jl_qysdsjmsbajl c where  ");
//				sb
//						.append(" t.basqwsh=c.basqwsh and (c.sqzt = '4' or c.sqzt = '5') and  t.jsjdm='"
//								+ ud.getYhid() + "'  order by t.band )tt order by rownum desc ");
//				System.out.println("===query wn sql==" + sb.toString());
//				ps = con.prepareStatement(sb.toString());
//				rs = ps.executeQuery();
//
//				if (rs.next()) {
//					Jmba11Vo mxvo = getBamxvo(rs);
//					mxvo.setXh("");
//					if(rs.getString("zs").equals("5")){
//					mxvo.setZs("1");
//					}else if(rs.getString("zs")!=null&&rs.getString("zs")!=""){
//						mxvo.setZs("2");
//					}else{
//						mxvo.setZs("0");
//					}
//
//					list.add(mxvo);
//				}
//
//				// }
//				vo1.setQysdsjmba(list);
				return vo1;
			}
			sb=new StringBuffer();
			sb.append("select * " );//t.SFYCJRMC,t.SFYSHBX,t.SFYLDHT,t.SFYCJRZM,t.SFYGZZM,
//					sb.append(" decode(t.SFYCJRMC,'0','��','1','��','') SFYCJRMCmc ,t.QTZL, ");
//            sb.append(" decode(t.SFYSHBX,'0','��','1','��','') SFYSHBXmc , ");
//            sb.append(" decode(t.SFYLDHT,'0','��','1','��','') SFYLDHTmc , ");
//            sb.append(" decode(t.SFYCJRZM,'0','��','1','��','') SFYCJRZMmc , ");
//            sb.append(" decode(t.SFYGZZM,'0','��','1','��','') SFYGZZMmc  ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_11 t where  basqwsh='"+bo.getBasqwsh()+"'");


			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}

			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				 Jmba11Vo mxvo=getBamxvo(rs);
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
