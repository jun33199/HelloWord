/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.gzwh.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsxpl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
import com.ttsoft.bjtax.smsb.model.client.nsrtogzsx;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰��������ϵͳ���������걨
 * </p>
 * <p>
 * Description: ������֪����
 * </p>
 * 
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */

public class XzgzsxProcessor implements Processor {
	/**
	 * ҳ��Ԫ���б�����
	 */
	private String names[] = { "jsjdm", "gzsxfcrq", "nsrmc", "gzsxksrq",
			"gzsxjsrq", "gzsxnr", "gzsxfcdw", "gzsxsdbz", "fzcbs", "bz", "lrr",
			"lrrdm", "swjgzzjgdm", "swjgzzjgdm2", "djzclxdm", "gjbzhydm",
			"cjrq", "lrrq", "qxdm", "ph", "gzsxnrbt", "gzsx_id" };

	/**
	 * ͨ�ô������ģ��
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;
		case CodeConstant.SMSB_UPDATEACTION:
			result = doUpdate(vo);
			break;
		default:
			throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}
		return result;
	}

	/**
	 * ҳ���ʼ��
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * ��ѯ
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * �����̨����
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {
			Gzsx g = new Gzsx(); // ��֪�����ORMappingֵ����
			UserData userData = vo.getUserData();
			g.setGzsxfcrq(new Timestamp(System.currentTimeMillis()));
			g.setNsrmc(gf.getMxNsrmc());
			g.setGzsxksrq(SfTimeUtil.getTimestamp((gf.getMxGzqsrq())));
			g.setGzsxjsrq(SfTimeUtil.getTimestamp((gf.getMxGzjzrq())));
			g.setGzsxnr(gf.getMxGzsxxxxx());

			g.setSwjgzzjgdm(userData.getSsdwdm()); // ������λ����
			g.setGzsxfcdw(userData.getSsdwmc());
			// g.setGzsxsdbz("");δ�ʹ�
			g.setFzcbs(gf.getMxGzlx());
			g.setBz(""); // ��ע
			g.setLrr(userData.getYhmc());
			g.setLrrdm(userData.getYhid());
			g.setCjrq(SfTimeUtil.getNowTimestamp());
			g.setLrrq(SfTimeUtil.getNowTimestamp());
			g.setQxdm(InterfaceDj.getQxdm(userData));
			// Ⱥ�������Ĭ��ֵ
			g.setDjzclxdm("0");
			g.setGjbzhydm("0");
			g.setSwjgzzjgdm2("0");
			// 2009.4.8��Ը�֪����������wcl�޸�
			String gzsxid = (String) getsequenceid();
                       if(gf.getMxChooseTypeRadioHidden().equals("1")||gf.getMxChooseTypeRadioHidden().equals("2")){
			if (gf.getMxGzsxxxxx().length() < 20) {
				g.setGzsxnrbt(gf.getMxGzsxxxxx());

			} else {
                                
				g.setGzsxnrbt(gf.getMxGzsxxxxx().substring(0, 20));

			}
  } else {
  if (gf.getMxGzsxxxxx().length() < 20) {
				g.setGzsxnrbt(new String(gf.getMxGzsxxxxx().getBytes("ISO8859_1"),"GBK"));

			} else {
                                g.setGzsxnrbt(new String(gf.getMxGzsxxxxx().getBytes("ISO8859_1"),"GBK").substring(0, 20));
				
}
}
			g.setGzsx_id(gzsxid);
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			// �������ݿ�
			insertGzsx(g, da, gf, conn, userData);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return gf;

	}

	/**
	 * ��ȡsequence��
	 */
	private Object getsequenceid() throws BaseException {
		Connection conn = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		// ������ô������ɶ�ã�
		try {

			sql = "select SBDB.SEQ_SB_GZBH.nextval from dual";

			con = SfDBResource.getConnection();// ���걨ģ������ӻ�ȡ���ݿ����ӵ���
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			nsrtogzsx model = null;
			List list = new ArrayList();
			if(rs.next()) {
				return rs.getString(1);
			}else{
				throw new ApplicationException("��֪������Ϊ��ȡ����������֪����ʧ�ܣ������ԣ�");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("��֪������Ϊ��ȡ����������֪����ʧ�ܣ������ԣ�");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}

	/**
	 * ɾ��
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	private Object doDelete(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * �޸ĺ�̨����
	 * 
	 * @param vo
	 *            ���ݼ����󣨰���Form��UserData����
	 * @return ��ǰҳ���Ӧ��Form����
	 * @throws BaseException
	 */
	private Object doUpdate(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {

			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			Gzsx g = new Gzsx(); // �����
			String primaryKey = gf.getModifyIndex();
			String strJsjdm = primaryKey.substring(0, primaryKey.indexOf("|"));
			String strGzsxfcrq = primaryKey.substring(
					primaryKey.indexOf("|") + 1, primaryKey.length());
			// ȥ������
			strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

			Vector gzV = new Vector();
			gzV.add(" JSJDM = '" + strJsjdm + "' ");
			gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
					+ "','YYYY-mm-dd hh24:mi:ss') ");
			List sourceGzsxList = da.query(Gzsx.class, gzV);
			Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
			BeanUtil.copyBeanToBean(names, sourceGzsx, g);

			g.setGzsxksrq(stringToTimestamp(gf.getMxGzqsrq()));
			g.setGzsxjsrq(stringToTimestamp(gf.getMxGzjzrq()));
			g.setFzcbs(gf.getMxGzlx());
			g.setGzsxnr(gf.getMxGzsxxxxx());
			// ��¼��������Ϣ
			UserData userData = vo.getUserData();
			g.setSwjgzzjgdm(userData.getSsdwdm()); // ������λ����
			g.setGzsxfcdw(userData.getSsdwmc());
			g.setLrr(userData.getYhmc());
			g.setLrrdm(userData.getYhid());
			g.setLrrq(SfTimeUtil.getNowTimestamp());
			g.setQxdm(InterfaceDj.getQxdm(userData));

			// ��������
			da.delete(sourceGzsxList);
			insertGzsx(g, da, gf, conn, userData);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gf;
	}

	/**
	 * �������ִ�(format:yyyyMMdd eg. 20030611)ת��ΪTimestamp������
	 * 
	 * @param strDate
	 *            �����ַ���
	 * @return Timestamp������
	 */
	private Timestamp stringToTimestamp(String strDate) {
		// int year = Integer.parseInt(strDate.substring(0, 4)) - 1900;
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
		int date = Integer.parseInt(strDate.substring(6, 8));

		Calendar gCalendar = GregorianCalendar.getInstance();
		gCalendar.set(gCalendar.YEAR, year);
		gCalendar.set(gCalendar.MONTH, month);
		gCalendar.set(gCalendar.DATE, date);
		gCalendar.set(gCalendar.HOUR_OF_DAY, 0);
		gCalendar.set(gCalendar.MINUTE, 0);
		gCalendar.set(gCalendar.SECOND, 0);
		gCalendar.set(gCalendar.MILLISECOND, 0);
		Timestamp t = new Timestamp(gCalendar.getTime().getTime());
		return t;
	}

	/**
	 * �����֪�����
	 * 
	 * @param g
	 *            ��֪ʱ������
	 * @param da
	 *            ���ݿ����
	 * @param gf
	 *            ��ǰҳ���Form����
	 * @param conn
	 *            ���ݿ�����
	 * @param userData
	 *            �û���Ϣ����
	 * @throws BaseException
	 */
//	private void insertGzsx(Gzsx g, SfDBAccess da, GzsxwhForm gf,
//			Connection conn, UserData userData) throws BaseException {
//
//		// ------------------------------
//
//		// try {
//		// Statement sttest=conn.createStatement();
//		// String sql="insert into sbdb.sb_jl_gzsx()
//		// values('*',to_date('20090101','yyyymmdd'),'yy',to_date('20090101','yyyymmdd'),"
//		// +
//		// "to_date('20090101','yyyymmdd'),'������������������֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ�ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣����������֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ�ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣��֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ
//		// �ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣������һ��������������ҵ���ȫ������������ʵ�֡����ʱ�ʾ���ֻ�ƾ��ʵʱ����ʱ����ء���������ԣ����Ϊ����Ҫ�ĸ����նˣ���ͬ�����Ƽ����ƽ̨����ҵ�����Խ��Խ���׺;�׼�����ֻ�ָ���Ʒ��ʱ���ֻ����Ը���������ȫ����Ϣ������Լ�ʱ��������ֻ���������������������̵������Ʒ���бȽϣ������������ֻ������������ҵ���̡�','','','1','','','','1','1','1','1',to_date('20090101','yyyymmdd'),to_date('20090101','yyyymmdd'),'1','','','')";
//		// sttest.execute(sql);
//		// sttest.ex
//		// } catch (SQLException e1) {
//		// // TODO Auto-generated catch block
//		// e1.printStackTrace();
//		// }
//		// -------------------------------
//
//		// �������ʿ���
//		parallelControl(g, da, conn);
//
//		if (gf.getMxChooseTypeRadioHidden().equals("1")) {
//			// ��֪������˰��
//			// 2009.4.1����306�У����ݿ������޸���
//			if ((gf.getMxJsjdm().trim()).equals("*")) {
//				g.setJsjdm("*");
//				g.setNsrmc("ȫ����˰��");
//				// ��������
//				da.insert(g);
//				// ��֪һ����˰�ˡ�
//			} else if (gf.getMxJsjdm().indexOf(",") > 0) {
//				StringTokenizer st = new StringTokenizer(
//						gf.getMxJsjdm().trim(), ",");
//				String tmpJsjdm = null;
//				List insertArrayJsjdmList = new ArrayList();
//				try {
//					while (st.hasMoreTokens()) {
//						tmpJsjdm = st.nextToken();
//
//						com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
//						HashMap ghdwMap = djProxy.getDjInfo(tmpJsjdm, userData);
//						SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
//						Gzsx tempG = new Gzsx();
//						BeanUtil.copyBeanToBean(names, g, tempG);
//						tempG.setJsjdm(tmpJsjdm);
//						tempG.setNsrmc(swdjjbsj.getNsrmc());
//						insertArrayJsjdmList.add(tempG);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					// ϵͳ��׽�쳣�������쳣�����׳�
//					throw new ApplicationException("���������" + tmpJsjdm
//							+ "�����ڻ���û���㹻��Ȩ�޴���˼��������");
//				}
//				da.insert(insertArrayJsjdmList);
//				// ��֪�ض���˰��
//			} else {
//				g.setJsjdm(gf.getMxJsjdm());
//				// ��������
//				da.insert(g);
//			}
//		} else if (gf.getMxChooseTypeRadioHidden().equals("2")) {
//			// -------------------------------
//			g.setJsjdm("*");
//			// Ⱥ����������
//			if (gf.getMxJhfs().equals("0")) { // ��Ϸ�ʽ:"��"
//				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
//						&& gf.getMxDqjs().equals("0")) {
//					// ��������
//					g.setNsrmc("ȫ����˰��");
//					da.insert(g);
//				} else {
//					// ��Խ�����Ⱥ
//					// ����˰������
//					g.setNsrmc(getNsrmcCollection(gf, "��"));
//					g.setDjzclxdm(gf.getMxQylx());
//					g.setGjbzhydm(gf.getMxHylb());
//					g.setSwjgzzjgdm2(gf.getMxDqjs());
//					// ��������
//					da.insert(g);
//
//				}
//			} else if (gf.getMxJhfs().equals("1")) { // ��Ϸ�ʽ:"��"
//				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
//						&& gf.getMxDqjs().equals("0")) {
//					// ��������
//					g.setNsrmc("ȫ����˰��");
//					da.insert(g);
//				} else {
//					Gzsx tempG = new Gzsx();
//					String tempSql = new String();
//					// ����˰������
//					g.setNsrmc(getNsrmcCollection(gf, "��"));
//					// ��ҵ��������
//					if (!gf.getMxQylx().equals("0")) {
//						g.setDjzclxdm(gf.getMxQylx());
//						g.setGjbzhydm("0");
//						g.setSwjgzzjgdm2("0");
//						da.insert(g);
//					}
//					// ��ҵ�������
//					if (!gf.getMxHylb().equals("0")) {
//						g.setDjzclxdm("0");
//						g.setGjbzhydm(gf.getMxHylb());
//						g.setSwjgzzjgdm2("0");
//						da.insert(g);
//					}
//					// ������������
//					if (!gf.getMxDqjs().equals("0")) {
//						g.setDjzclxdm("0");
//						g.setGjbzhydm("0");
//						g.setSwjgzzjgdm2(gf.getMxDqjs());
//						da.insert(g);
//					}
//				}
//			}
//
//		} else if (gf.getMxChooseTypeRadioHidden().equals("3")) {
//			// ��������뵼�뷽ʽ
//
//			// ����������ʱ��
//			String ph = this.insertGzsxpl(gf.getJsjdmList(), da, g
//					.getSwjgzzjgdm());
//			// �õ�gzsx���б�
//			// List list = this.getGzsxList(da, ph, g);
//			// 2009.4.2wcl�޸Ľ�ȡ���б�ķ����޸ġ�����
//			List list = this.getGzsxList(da, ph, g);
//			// �����֪����
//			da.insert(list);
//		}
//
//	}
	private void insertGzsx(Gzsx g, SfDBAccess da, GzsxwhForm gf,
			Connection conn, UserData userData) throws BaseException {

		// ------------------------------

		// try {
		// Statement sttest=conn.createStatement();
		// String sql="insert into sbdb.sb_jl_gzsx()
		// values('*',to_date('20090101','yyyymmdd'),'yy',to_date('20090101','yyyymmdd'),"
		// +
		// "to_date('20090101','yyyymmdd'),'������������������֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ�ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣����������֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ�ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣��֪����ķ��������690�ֺ��֡���ҳ���Ͽ��Ƶ���2000���������ڡ����Ǵ���690���ֵĸ�֪�����Ƿ��Ͳ��˵ġ���֪����ʱ�ᵯ����֪����������ͳ�ƣ�����������ʾ������4��29�գ��ȸ�ȫ���ܲ����ʶ�δ���Ļ�����ʱ��������Ԥ�⡣����Ϊ���������ȣ�2018��Ļ�������Ϣʱ�������ǵ����ʽ�ᷢ�����츲�صĸı䡣�Ƽ���Ϳ��Ż�������Ϣ���粻�������Ǹ����ܵ���ϵ��һ��Ҳ�Ὺ����ǰ��δ�е���ҵ��ֵ����ҵ���ᡣ
		// �ݽ��У������ܽ���ʮ��������������������������ĸı䡣��Щ�ı��������Ϣ��Դ����ͨ���������ַ�ʽ������ҵ��ȸ������档����ָ��������ȫ��ÿ�췢������20�ڴε��������н���1200�ڷ�ĵ����ʼ��ͼ�ʱͨѶ��ȫ�򴫵ݡ�ͬʱ��ȫ�򳬹�85%�Ļ������û������Ͻ��й�����ڲ���ǰ����3300�����˹�ͬ�����տ���2009�괺��������ᡣ��һϵ�е����ֶ���������ȥ��ʮ�꣬���ǵ������Ѿ������������׵߸�����̸��2018�Ļ�������Ϣʱ��ʱ�����������һ�����Ż��������磺ʮ��֮����Ϣ�����������꣬����Ծ�������ʲô�ط��¡��¶೤ʱ�䡢�¶����������ܵ����ݹ���ϵͳ���԰��������Ч��Ϣ�ĺ�ը��ͬʱ��ȫ���н���7000�����ԣ�ʮ������ʵ���޷�ʽ��ֱ�룬�����������������֣�������Դͷ�����ֵ����ԣ����շ��������Լ���Ϥ������ȥ���պ���֪������δ�����Ĺ�ͨ��ʽ�ĸı䡣������һ��������������ҵ���ȫ������������ʵ�֡����ʱ�ʾ���ֻ�ƾ��ʵʱ����ʱ����ء���������ԣ����Ϊ����Ҫ�ĸ����նˣ���ͬ�����Ƽ����ƽ̨����ҵ�����Խ��Խ���׺;�׼�����ֻ�ָ���Ʒ��ʱ���ֻ����Ը���������ȫ����Ϣ������Լ�ʱ��������ֻ���������������������̵������Ʒ���бȽϣ������������ֻ������������ҵ���̡�','','','1','','','','1','1','1','1',to_date('20090101','yyyymmdd'),to_date('20090101','yyyymmdd'),'1','','','')";
		// sttest.execute(sql);
		// sttest.ex
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// -------------------------------

		// �������ʿ���
		
		parallelControl(g, da, conn);
         //��һ�ַ��ͷ�ʽ
		if (gf.getMxChooseTypeRadioHidden().equals("1")) {
			// ��֪������˰��
			// 2009.4.1����306�У����ݿ������޸���
			if ((gf.getMxJsjdm().trim()).equals("*")) {
				List list=new ArrayList();
				g.setJsjdm("*");
				g.setNsrmc("ȫ����˰��");
				// ��������
				list.add(g);
				insert(conn,list);//ֱ�ӾͲ�����
				// ��֪һ����˰�ˡ�
			} else if (gf.getMxJsjdm().indexOf(",") > 0) {
				StringTokenizer st = new StringTokenizer(
						gf.getMxJsjdm().trim(), ",");
				String tmpJsjdm = null;
				List insertArrayJsjdmList = new ArrayList();
				try {
					while (st.hasMoreTokens()) {
						tmpJsjdm = st.nextToken();

						com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
						HashMap ghdwMap = djProxy.getDjInfo(tmpJsjdm, userData);
						SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
						Gzsx tempG = new Gzsx();
						BeanUtil.copyBeanToBean(names, g, tempG);
						tempG.setJsjdm(tmpJsjdm);
						tempG.setNsrmc(swdjjbsj.getNsrmc());
						insertArrayJsjdmList.add(tempG);
					}
				} catch (Exception e) {
					e.printStackTrace();
					// ϵͳ��׽�쳣�������쳣�����׳�
					throw new ApplicationException("���������" + tmpJsjdm
							+ "�����ڻ���û���㹻��Ȩ�޴���˼��������");
				}
				insert(conn,insertArrayJsjdmList);
				// ��֪�ض���˰��
			} else {
				List list=new ArrayList();
				g.setJsjdm(gf.getMxJsjdm());
				// ��������
				list.add(g);
				insert(conn,list);
			}
		} 
		//�ڶ��ַ��ͷ�ʽ
		
		else if (gf.getMxChooseTypeRadioHidden().equals("2")) {
			// -------------------------------
			g.setJsjdm("*");
			// Ⱥ����������
			if (gf.getMxJhfs().equals("0")) { // ��Ϸ�ʽ:"��"
				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
						&& gf.getMxDqjs().equals("0")) {
					// ��������
					g.setNsrmc("ȫ����˰��");
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);
				} else {
					// ��Խ�����Ⱥ
					// ����˰������
					g.setNsrmc(getNsrmcCollection(gf, "��"));
					g.setDjzclxdm(gf.getMxQylx());
					g.setGjbzhydm(gf.getMxHylb());
					g.setSwjgzzjgdm2(gf.getMxDqjs());
					// ��������
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);

				}
			} else if (gf.getMxJhfs().equals("1")) { // ��Ϸ�ʽ:"��"
				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
						&& gf.getMxDqjs().equals("0")) {
					// ��������
					g.setNsrmc("ȫ����˰��");
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);
					//da.insert(g);
				} else {
					Gzsx tempG = new Gzsx();
					String tempSql = new String();
					// ����˰������
					g.setNsrmc(getNsrmcCollection(gf, "��"));
					// ��ҵ��������
					if (!gf.getMxQylx().equals("0")) {
						g.setDjzclxdm(gf.getMxQylx());
						g.setGjbzhydm("0");
						g.setSwjgzzjgdm2("0");
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
					// ��ҵ�������
					if (!gf.getMxHylb().equals("0")) {
						g.setDjzclxdm("0");
						g.setGjbzhydm(gf.getMxHylb());
						g.setSwjgzzjgdm2("0");
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
					// ������������
					if (!gf.getMxDqjs().equals("0")) {
						g.setDjzclxdm("0");
						g.setGjbzhydm("0");
						g.setSwjgzzjgdm2(gf.getMxDqjs());
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
				}
			}

		} 
		
		//�����ַ��ͷ�ʽ
		else if (gf.getMxChooseTypeRadioHidden().equals("3")) {
			// ��������뵼�뷽ʽ

			// ����������ʱ��
			String ph = this.insertGzsxpl(gf.getJsjdmList(), da, g
					.getSwjgzzjgdm());
			// �õ�gzsx���б�
			// List list = this.getGzsxList(da, ph, g);
			// 2009.4.2wcl�޸Ľ�ȡ���б�ķ����޸ġ�����
			List list = this.getGzsxList(da, ph, g);
			// �����֪����
			//List list1=new ArrayList();
			//list.add(g);
			insert(conn,list);
			//da.insert(list);
		}

	}

	private void insert(Connection conn, List list) throws BaseException{
		Statement sttest=null;
		try {
			for(int i=0;i<list.size();i++){
				Gzsx g=(Gzsx)list.get(i);
				sttest = conn.createStatement();
				
				/*��������*/
				String fcrq=TinyTools.Date2String(new Date(g.getGzsxfcrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*��������*/
				String cjrq=TinyTools.Date2String(new Date(g.getCjrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*¼������*/
				String lrrq=TinyTools.Date2String(new Date(g.getLrrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*��ʼ����*/
				String ksrq=TinyTools.Date2String(new Date(g.getGzsxksrq().getTime()));
				/*��������*/
				String jsrq=TinyTools.Date2String(new Date(g.getGzsxjsrq().getTime()));
				
				String sql = "insert into sbdb.sb_jl_gzsx(JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,DJZCLXDM,GJBZHYDM,SWJGZZJGDM2,CJRQ,LRRQ,QXDM,PH,GZSX_ID,GZSXNRBT) " +
				"values('"+g.getJsjdm()+"',to_date('"+fcrq+"','yyyy-MM-dd HH24:mi:ss'),'"+g.getNsrmc()+"',"+
				"to_date('"+ksrq+"','yyyy-MM-dd')"+","+
				"to_date('"+jsrq+"','yyyy-MM-dd')"+",'"+
				g.getGzsxnr()+"','"+
				g.getGzsxfcdw()+"','"+
				(g.getGzsxsdbz()==null?"":g.getGzsxsdbz())+"','"+
				g.getFzcbs()+"','"+
				g.getBz()+"','"+
				g.getLrr()+"','"+
				g.getLrrdm()+"','"+
				g.getSwjgzzjgdm()+"','"+
				g.getDjzclxdm()+"','"+
				g.getGjbzhydm()+"','"+
				g.getSwjgzzjgdm2()+"',to_date('"+cjrq+"','yyyy-MM-dd HH24:mi:ss'),to_date('"+lrrq+"','yyyy-MM-dd HH24:mi:ss')"+
				",'"+
				g.getQxdm()+"','"+
				(g.getPh()==null?"":g.getPh())+"','"+
				g.getGzsx_id()+"','"+
				g.getGzsxnrbt()+"')";
				//System.out.println("---sql"+sql);
				sttest.execute(sql);
                                if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			}
			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new ApplicationException("������֪����ʧ�ܣ����ݿ����ʧ��\n��ϸ��Ϣ��"+e1.getMessage());
		}finally{
			if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��Ⱥ���Ķ�����Ϣ
	 * 
	 * @param gf
	 *            ��ǰFrom����
	 * @param split
	 *            �ָ���
	 * @return String
	 */
	private String getNsrmcCollection(GzsxwhForm gf, String split) {
		StringBuffer sb = new StringBuffer();

		if (!gf.getMxQylx().equals("0")) {
			List list = CodeManager.getCodeList("ZQWH_DJZCLX",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxQylx().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		// ��ҵ�������
		if (!gf.getMxHylb().equals("0")) {
			List list = CodeManager.getCodeList("ZBZL_GJBZHY",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxHylb().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		// ������������
		if (!gf.getMxDqjs().equals("0")) {
			List list = CodeManager.getCodeList("GZWH_SWJGZZJG",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxDqjs().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		String returnString = sb.toString();
		returnString = returnString.substring(0, returnString.length() - 3);
		return returnString;
	}

	/**
	 * �������ʿ���
	 * 
	 * @param g
	 *            ��֪��������
	 * @param da
	 *            ���ݷ��ʶ���
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @throws BaseException
	 */
	private void parallelControl(Gzsx g, SfDBAccess da, Connection conn)
			throws BaseException {
		SfDBUtils sfDB = new SfDBUtils(conn);
		try {
			sfDB.executeSql("LOCK TABLE SBDB.SB_JL_GZSX IN EXCLUSIVE MODE ");
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			throw new ApplicationException("ϵͳ��æ�����Ժ����ԣ�");
		}
		try {
			for (int i = 0; i < 20; i++) {
				Vector gzV = new Vector();
				String strGzsxfcrq = g.getGzsxfcrq().toString();
				strGzsxfcrq = strGzsxfcrq
						.substring(0, strGzsxfcrq.indexOf("."));
				gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
						+ "','YYYY-mm-dd hh24:mi:ss') ");
				List list = da.query(Gzsx.class, gzV);
				if (list.size() == 0) {
					return;
				} else {
					Timestamp t = g.getGzsxfcrq();
					g.setGzsxfcrq(new Timestamp(t.getTime() + 1000));
				}
			}
			throw new ApplicationException("ϵͳ��æ�����Ժ����ԣ�");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ���б��еļ����������뵽�������� Ȼ��ʹ�ø�������͵Ǽǻ�����Ϣ��Ƚ� �ж��Ƿ��в��Ϸ��ļ��������
	 * 
	 * @param list
	 *            �����������list
	 * @param da
	 *            ���ݿ���ʶ���
	 * @param swjgzzjgdm
	 *            ˰�������֯��������
	 * @return ����
	 * @throws BaseException
	 */
	private String insertGzsxpl(List list, SfDBAccess da, String swjgzzjgdm)
			throws BaseException {
		List gzsxpl = new ArrayList();
		String ph = this.getPh(swjgzzjgdm);
		for (int i = 0; i < list.size(); i++) {
			// ���ɰ���Gzsxpl��list
			Gzsxpl temp = new Gzsxpl();
			temp.setPh(ph);
			temp.setJsjdm((String) list.get(i));
			gzsxpl.add(temp);
		}
		// ��������
		try {
			da.insert(gzsxpl);
		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return ph;

	}

	/**
	 * �õ���ʱ���е�����
	 * 
	 * @param swjgzzjgdm
	 *            ˰�������֯��������
	 * @return ��ʱ���е�����
	 */
	private String getPh(String swjgzzjgdm) {
		return swjgzzjgdm + TinyTools.Date2String(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * ������ʱ���������ɸ�֪�����б�
	 * 
	 * @param da
	 *            ���ݿ���ʶ���
	 * @param ph
	 *            ��ʱ���е�����
	 * @param g
	 *            ��֪��������
	 * @return ������ĸ�֪����list
	 */
	// private List getGzsxList (SfDBAccess da, String ph, Gzsx g)
	// {
	// List list = new ArrayList();
	// try
	// {
	// //���ϵǼǻ�����Ϣ��õ���˰�˻�����Ϣ
	// ResultSet ret = da.querySQL("select a.jsjdm,b.nsrmc,b.DJZCLXDM," +
	// " b.GJBZHYDM,b.SWJGZZJGDM " +
	// " from sbdb.SB_JL_GZSXPL a, djdb.dj_jl_jbsj b " +
	// " where a.jsjdm=b.jsjdm");
	// while (ret.next())
	// {
	// Gzsx temp = new Gzsx();
	// BeanUtil.copyBeanToBean(names, g, temp);
	//
	// temp.setGzsxnr(new String(g.getGzsxnr().getBytes("ISO8859_1"),
	// "GBK"));
	// temp.setJsjdm(ret.getString("jsjdm"));
	// temp.setNsrmc(ret.getString("nsrmc"));
	// temp.setDjzclxdm(ret.getString("djzclxdm"));
	// temp.setGjbzhydm(ret.getString("gjbzhydm"));
	// temp.setSwjgzzjgdm(ret.getString("swjgzzjgdm"));
	// temp.setQxdm(temp.getSwjgzzjgdm().substring(0, 2));
	// temp.setPh(ph);
	// list.add(temp);
	// }
	// //ɾ����ʱ���¼
	// da.querySQL("delete from sbdb.sb_jl_gzsxpl where ph='" + ph + "'");
	// return list;
	// }
	// catch (Exception ex)
	// {
	// return null;
	// }
	// }
	/**
	 * ������ʱ���������ɸ�֪�����б�
	 * 
	 * @param da
	 *            ���ݿ���ʶ���
	 * @param ph
	 *            ��ʱ���е�����
	 * @param g
	 *            ��֪��������
	 * @return ������ĸ�֪����list wcl2009.4.2�޸Ľ�ԭ���ķ�������
	 */
	private List getGzsxList(SfDBAccess da, String ph, Gzsx g) {
		List list = new ArrayList();
		try {
			// ���ϵǼǻ�����Ϣ��õ���˰�˻�����Ϣ
			ResultSet ret = da.querySQL("select a.jsjdm,b.nsrmc,b.DJZCLXDM,"
					+ " b.GJBZHYDM,b.SWJGZZJGDM "
					+ " from sbdb.SB_JL_GZSXPL a, djdb.dj_jl_jbsj b "
					+ " where a.jsjdm=b.jsjdm");
			while (ret.next()) {
				Gzsx temp = new Gzsx();
				BeanUtil.copyBeanToBean(names, g, temp);

				temp.setGzsxnr(new String(g.getGzsxnr().getBytes("ISO8859_1"),
						"GBK"));
				temp.setJsjdm(ret.getString("jsjdm"));
				temp.setNsrmc(ret.getString("nsrmc"));
				temp.setDjzclxdm(ret.getString("djzclxdm"));
				temp.setGjbzhydm(ret.getString("gjbzhydm"));
				temp.setSwjgzzjgdm(ret.getString("swjgzzjgdm"));
				temp.setQxdm(temp.getSwjgzzjgdm().substring(0, 2));
				temp.setPh(ph);
				list.add(temp);
			}
			// ɾ����ʱ���¼
			da.querySQL("delete from sbdb.sb_jl_gzsxpl where ph='" + ph + "'");
			return list;
		} catch (Exception ex) {
			return null;
		}
	}
}
