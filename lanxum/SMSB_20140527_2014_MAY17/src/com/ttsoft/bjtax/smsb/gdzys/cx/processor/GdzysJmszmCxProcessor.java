package com.ttsoft.bjtax.smsb.gdzys.cx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.cx.web.GdzysJmszmCxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>
 * Title:������˰��������ϵͳ--����ռ��˰ҵ���ѯ
 * </p>
 * <p>
 * Description:����˰֤����ѯProcessor
 * </p>
 * 
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmszmCxProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		// ��ȡ�־�����
		case CodeConstant.SMSB_DQJSLIST:
			result = dogetdqjslist(vo);
			break;
		// ��ȡ˰��������
		case CodeConstant.SMSB_CXDQJSLIST:
			result = dogetcxdqjslist(vo);
			break;
		// ��ѯ
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		default:
			throw new UnsupportedOperationException(
					"Method process() not yet implemented.");
		}
		return result;
	}

	private Object doQuery(VOPackage vo) {
		// ��ȡform
		GdzysJmszmCxForm form = (GdzysJmszmCxForm) vo.getData();

		// �������ݿ�
		PreparedStatement ps = null;
		Connection conn = null;
		// ��ȡ��������sql

		try {
			int count = 0;// ��ѯ�ܼ�¼����
			int currentPage = form.getCurrentPage();//��ǰҳ��
			int startNum = (currentPage - 1) * (form.getPageSize()) + 1;//��ʼ��¼�������ڷ�ҳrownum��
			int endNum = currentPage * (form.getPageSize());//������¼�������ڷ�ҳrownum��

			// ��ȡ����
			conn = SfDBResource.getConnection();

			StringBuffer buffer = new StringBuffer();
			// ��ѯ���м�¼������Ŀsql
			buffer.append("SELECT count(*) totalcount from(SELECT b.sbbxlh,a.jmszmbh,a.kjrq,a.qxdm,");
			buffer.append("(SELECT COUNT(*) FROM SBDB.Sb_JL_GDZYS_JMSZMDYRZ c WHERE c.sbbxlh=a.sbbxlh AND c.jmszmbh=a.jmszmbh) dycs,");
			buffer.append("(SELECT swjgzzjgmc FROM  dmdb.gy_dm_swjgzzjg d WHERE d.swjgzzjgdm=a.qxdm) kjjg,");
			buffer.append("a.dyry,a.dyrq,a.zfbz,a.jzbz FROM SBDB.SB_JL_GDZYS_JMSZM a,SBDB.SB_JL_GDZYS_SBB b ");
			buffer.append(" WHERE a.sbbxlh=b.sbbxlh");
			//��ʼ����
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				buffer.append(" and a.kjrq >=to_date(?,'yyyyMMdd') ");
			}
			//��ֹ����
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				buffer.append(" and a.kjrq<to_date(?,'yyyyMMdd') ");
			}
			//�־ִ���
			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				buffer.append(" and b.qxdm=? ");
			}
			//˰��������
			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				buffer.append(" and a.qxdm=? ");
			}
			//����˰֤��״̬�������ͳ�����
			if (!form.getStatus().equals("ȫ��")
					&& !form.getStatus().trim().equals("")) {
				buffer.append(" and a.zfbz=? ");
			}
			//����˰֤�����
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				buffer.append(" and a.jmszmbh=? ");
			}

			buffer.append(")");

			ps = conn.prepareStatement(buffer.toString());
			int i = 1;
			//���������ֵ�Ļ����͸�������ֵ
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				ps.setString(i, form.getYstart().trim());
				i++;
			}
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				ps.setString(i, form.getYend().trim());
				i++;
			}

			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				ps.setString(i, form.getDqjs().trim().substring(0, 2));
				i++;
			}

			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				ps.setString(i, form.getCxdqjs().trim());
				i++;
			}

			if (!form.getStatus().equals("ȫ��")
					&& !form.getStatus().trim().equals("")) {
				ps.setString(i, form.getStatus().trim());
				i++;
			}
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				ps.setString(i, form.getJmszmbh().trim());
				i++;
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // ȡ�����������ļ���
				count = rs.getInt(1);
			}
			System.out.println("###########count=" + count);
			//�����м�¼����ֵ�ŵ�form��
			form.setTotalRowCount(count);
			//�ر�����
			rs.close();

			buffer = new StringBuffer();
			//��ѯ�����м�¼��sql
			buffer.append("SELECT *FROM (SELECT ROWNUM r,b.sbbxlh,a.jmszmbh,b.jsjdm,b.nsrmc,a.kjrq,a.qxdm,");
			buffer.append("(SELECT COUNT(*) FROM SBDB.Sb_JL_GDZYS_JMSZMDYRZ c WHERE c.sbbxlh=a.sbbxlh AND c.jmszmbh=a.jmszmbh) dycs,");
			buffer.append("(SELECT swjgzzjgmc FROM  dmdb.gy_dm_swjgzzjg d WHERE d.swjgzzjgdm=a.qxdm) kjjg,");
			buffer.append("a.dyry,a.dyrq,a.zfbz,a.jzbz FROM SBDB.SB_JL_GDZYS_JMSZM a,SBDB.SB_JL_GDZYS_SBB b ");
			buffer.append("WHERE a.sbbxlh=b.sbbxlh ");
			//��ʼ����
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				buffer.append(" and a.kjrq >=to_date(?,'yyyyMMdd') ");
			}
			//��ֹ����
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				buffer.append(" and a.kjrq<to_date(?,'yyyyMMdd') ");
			}
			//�־ִ���
			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				buffer.append(" and b.qxdm=? ");
			}
			//˰��������
			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				buffer.append(" and a.qxdm=? ");
			}
			//����˰֤����״̬�������ͳ�����
			if (!form.getStatus().equals("ȫ��")
					&& !form.getStatus().trim().equals("")) {
				buffer.append(" and a.zfbz=? ");
			}
			//����˰֤�����
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				buffer.append(" and a.jmszmbh=? ");
			}

			buffer.append("AND ROWNUM<=?) WHERE r>=?");
			System.out.println(buffer.toString() + "��ѯ��sql#########");

			ps = conn.prepareStatement(buffer.toString());
			i = 1;

			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				ps.setString(i, form.getYstart().trim());
				i++;
			}
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				ps.setString(i, form.getYend().trim());
				i++;
			}

			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				ps.setString(i, form.getDqjs().trim().substring(0, 2));
				i++;
			}

			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				ps.setString(i, form.getCxdqjs().trim());
				i++;
			}

			if (!form.getStatus().equals("ȫ��")
					&& !form.getStatus().trim().equals("")) {
				ps.setString(i, form.getStatus().trim());
				i++;
			}
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				ps.setString(i, form.getJmszmbh().trim());
				i++;
			}
			//��ֹ��¼��ֵ����ҳrownum��
			ps.setInt(i++, endNum);
			//��ʼ��¼��ֵ����ҳrownum��
			ps.setInt(i++, startNum);
			//�����
			ResultSet rst = ps.executeQuery();
			// ��Ž��������
			List list = new ArrayList();
			// ���ÿ����¼�ļ��ϣ��൱��model��
			Map map = null;
			while (rst.next()) {
				map = new HashMap();
				// �걨���к�
				map.put("sbbxlh",
						rst.getString("sbbxlh") == null ? "" : rst
								.getString("sbbxlh"));
				// ����˰֤�����
				map.put("jmszmbh",
						rst.getString("jmszmbh") == null ? "" : rst
								.getString("jmszmbh"));
				//���������
				map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
				//��˰������
				map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
				//ȡ�ÿ������ڵ�timestamp
				Timestamp ts=rst.getTimestamp("kjrq");
				//ȡ�ô�ӡ���ڵ�timestamp
				Timestamp tsdyrq=rst.getTimestamp("dyrq");
				String kjrq="";
				String dyrq="";
				if(ts!=null){
					//ȡ�ÿ������ڵ�ʱ��longֵ
					long mil =ts.getTime();
					Date date =new Date(mil);
					DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					kjrq=df.format(date);
				}
				if(tsdyrq!=null){
					//ȡ�ô�ӡ���ڵ�ʱ��longֵ
					long mdyrq=tsdyrq.getTime();
					Date date1=new Date(mdyrq);
					DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dyrq=df.format(date1);
				}
				// ��������
				map.put("kjrq",
						kjrq);
				// ���߻���
				map.put("kjjg",
						rst.getString("kjjg") == null ? "" : rst
								.getString("kjjg"));
				// ��ӡ����
				map.put("dycs",
						rst.getString("dycs") == null ? "" : rst
								.getString("dycs"));
				// ��ӡ����
				map.put("dyrq",
						dyrq);
				// ��ӡ��Ա
				map.put("dyry",
						rst.getString("dyry") == null ? "" : rst
								.getString("dyry"));
				// ���ͣ�'0'����������'1'�����ѳ�����
				map.put("lx", rst.getString("zfbz") == null ? "" : (rst
						.getString("zfbz").equals("0") ? "����" : "�ѳ���"));
				// �Ƿ��Ѽ��ˣ�'000000'����δ���ˣ����������Ѽ��ˣ�
				map.put("jzbz", rst.getString("jzbz") == null ? "" : (rst
						.getString("jzbz").equals("000000") ? "δ����" : "�Ѽ���"));
				// ����¼��ӵ�list������
				list.add(map);
			}

			form.setDataList(list);
			rst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * ��ȡ�־�����list
	 */
	private Object dogetdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJmszmCxForm theForm = (GdzysJmszmCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			swdwmodel model1 = null;
			List list = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = userdata.getSsdwdm();
			// ���ݵ�¼�û�����Ϣ��ȡ��½�ľ�������Ϣ
			// 90���о֣����Բ�ѯ���з־�
			if ("90".equals(gxswjgzzjgdm.substring(0, 2))) {
				// ��������з־�
				//sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '%00' "
					//	+ " and b.swjgzzjgdm!='9000' order by b.swjgzzjgdm ";
				sql="SELECT b.qxdm||'00',b.qxfjmc FROM  DMDB.GD_DM_QXFJDM b where b.qxdm!='90' and (b.zxbz='0' or b.zxbz is null)  order by qxdm";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			} else {
				//sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm='"
						//+ gxswjgzzjgdm.substring(0, 2)
						//+ "00' order by b.swjgzzjgdm ";
				sql="select b.qxdm||'00',b.qxfjmc from dmdb.gd_dm_qxfjdm b where b.qxdm='"
						+gxswjgzzjgdm.substring(0,2)
						+"' and (b.zxbz='0' or zxbz is null) order by b.qxdm";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				// System.out.println(sql + "-------------���½˰����ص�sql");
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	 * ��ȡ˰������Ϣlist
	 */
	private Object dogetcxdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJmszmCxForm theForm = (GdzysJmszmCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {

			swdwmodel model1 = null;

			List list1 = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = theForm.getDqjs();
			// ���ݵ�¼�û�����Ϣ��ȡ��½�ľ�������Ϣ
			if ("30".equals(userdata.yhjb)) {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where "
						+ "  b.swjgzzjgdm='"
						+ userdata.getSsdwdm()
						+ "' and (zxbs='0' or zxbs is null) order by b.swjgzzjgdm ";
			} else {
				// ���ݷ־ֲ�˰����
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '"
						+ gxswjgzzjgdm.substring(0, 2)
						+ "%' "
						+ " and b.swjgzzjgdm!='"
						+ gxswjgzzjgdm
						+ "' and (zxbs='0' or zxbs is null) order by b.swjgzzjgdm ";
			}

			System.out.println("###########sql=" + sql);

			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new swdwmodel();
				model1.setSwdwid(rs.getString(1));
				model1.setSwdwmc(rs.getString(2));
				list1.add(model1);
			}
			theForm.setCxswdwlist(list1);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			return theForm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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

}
