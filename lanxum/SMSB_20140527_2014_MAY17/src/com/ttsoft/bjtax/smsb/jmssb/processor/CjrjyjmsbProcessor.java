package com.ttsoft.bjtax.smsb.jmssb.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jmssb.web.CjrjyjmsForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class CjrjyjmsbProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		// TODO Auto-generated method stub
		// �ش�����
		Object result = null;
		// �ж�VO�Ƿ�Ϊ��
		if (vo == null) {
			throw new NullPointerException();
		}
		// ����Action��ֵ���ò�ͬ��process����
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION: // ǰ̨Ĭ����ʾ
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION: // ��ѯ
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			doDelete(vo);
			break;
		default:
			throw new UnsupportedOperationException(
					"Method process() not yet implemented.");
		}
		return result;
	}

	private Object doShow(VOPackage vo) throws BaseException {
		// TODO Auto-generated method stub
		CjrjyjmsForm form = (CjrjyjmsForm) vo.getData();
		Connection conn = null;
		// ����ִ�ж���
		PreparedStatement ps = null;
		// ���ݷ��ض���
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(CodeConstant.JMSSB_CJRJYJMSB_DWXZ_SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			form.setDwxzList(list);
			form.setLrrq(SfDateUtil.getDate());
			return form;
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

	}

	private Object doQuery(VOPackage vo) throws BaseException {

		// TODO Auto-generated method stub
		CjrjyjmsForm form = (CjrjyjmsForm) vo.getData();
		Connection conn = null;
		// ����ִ�ж���
		PreparedStatement ps = null;
		// ���ݷ��ض���
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			// ���ڲ��Դ�����������
			//type �������ȡ��ʶ������1ʱֻ����
			String  type = "";
			boolean flag = false;
			String sql = "";
			sql = "select f.sqspbh sqspbh from spdb.sp_jl_flqymyys f where f.jsjdm= '"
					+ form.getJsjdm()
					+ "' and rownum<2";
					//+ "' and add_months(sysdate, -1) between f.jmksrq and f.jmjzrq and rownum<2";
			//System.out.println("###################sql = " + sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				form.setSqspbh(rs.getString("sqspbh"));
				flag = true;
			}
			if (flag == true) {
				String sql1 = "SELECT A.SWDJZH,A.JSJDM,A.NSRMC, A.ZCDZ, A.JYFW,B.DWXZ,B.ZGZRS,B.CJRZGRS,B.CJRYBL,"
						+ "B.YNYYSSR,B.YJYYSSE,B.XSYHZZSE,B.BYYJZYYSXE,B.SYYJZYYSXE,B.BYKJZYYSXE,B.BYSJJZYYSYE,"
						+ "B.BYSJYESSE,B.BYMJZYYSXE,B.SBRQ,TO_CHAR(B.SKSSJSRQ,'YYYYMM') SKSSRQ,B.LRRQ,B.FSDM,B.SWJGZZJGDM,B.LRR,B.SKSSJSRQ,B.SKSSKSRQ,"
						+ "B.ND,B.CJRQ,B.BZ,B.QXDM FROM DJDB.DJ_JL_JBSJ A, SBDB.SB_JL_JM_AZCJRJMSB B WHERE A.JSJDM = B.JSJDM(+) AND A.JSJDM =?";
				if ("".equals(form.getSkssrq()) || form.getSkssrq() == null) {
					// ��sql��ѯsbdb.sb_jl_jm_azcjrjmsb����jsjdm��Ӧ����걨����
					sql = "select max(to_char(a.skssjsrq, 'yyyymm')) skssrq from sbdb.sb_jl_jm_azcjrjmsb a where a.jsjdm = '"
							+ form.getJsjdm() + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					String maxskssrq = "";
					while (rs.next()) {
						maxskssrq = rs.getString(1);
					}

					if ("".equals(maxskssrq) || maxskssrq == null) {
						sql = sql1;
					} else {
						sql = sql1 + " and to_char(b.skssjsrq, 'yyyymm')='"
								+ maxskssrq+"'";
					}
				} else {
					String sql2 = "SELECT * FROM SBDB.SB_JL_JM_AZCJRJMSB B WHERE B.JSJDM = '"
							+ form.getJsjdm()
							+ "' AND TO_CHAR(B.SKSSJSRQ,'yyyymm')="
							+ form.getSkssrq();
					ps = conn.prepareStatement(sql2);
					rs = ps.executeQuery();
					boolean falg1= false;
					while (rs.next()) {
						falg1 = true;		
					} 
					if(falg1==true){
						sql = sql1 + " and to_char(B.SKSSJSRQ, 'yyyymm')="
						+ form.getSkssrq();
					}else {
						sql = "SELECT A.SWDJZH,A.JSJDM,A.NSRMC, A.ZCDZ, A.JYFW FROM DJDB.DJ_JL_JBSJ A WHERE A.JSJDM=?";
						type="1";
					}
				}
				// ����sql
				//System.out.println("end###################sql = " + sql);
				ps = conn.prepareStatement(sql);
				// ���ڲ��Դ�����������
				ps.setString(1, form.getJsjdm());
				rs = ps.executeQuery();
				if (rs.next()) {
					if(type.equals("1")){
						form.setJsjdm(rs.getString("jsjdm"));
						form.setNsrmc(rs.getString("nsrmc"));
						form.setSwdjzh(rs.getString("swdjzh"));
						form.setZcdz(rs.getString("zcdz"));	
						form.setJyfw(rs.getString("jyfw"));
						form.setDwxzList(dwxzListSort("01"));
						form.setSign_re("01");
						form.setSign("01");
						form.setIsError("�����������˰������ʱ��δ��ѯ�����µ��걨���ݣ�");
					}else{
						form.setJsjdm(rs.getString("jsjdm"));
						form.setSkssrq(rs.getString("skssrq"));
						form.setNsrmc(rs.getString("nsrmc"));
						form.setSwdjzh(rs.getString("swdjzh"));
						form.setZcdz(rs.getString("zcdz"));
						form.setDwxz(rs.getString("dwxz"));
						form.setDwxzList(dwxzListSort(rs.getString("dwxz")));
						form.setJyfw(rs.getString("jyfw"));
						form.setZgzrs(rs.getString("zgzrs"));
						form.setCjrzgrs(rs.getString("cjrzgrs"));
						form.setCjrybl(String.valueOf(rs.getFloat("cjrybl")));
						form.setYnyyssr(rs.getString("ynyyssr"));
						form.setYjyysse(rs.getString("yjyysse"));
						form.setXsyhzzse(rs.getString("xsyhzzse"));
						form.setByyjzyysxe(rs.getString("byyjzyysxe"));
						form.setSyyjzyysxe(rs.getString("syyjzyysxe"));
						form.setBykjzyysxe(rs.getString("bykjzyysxe"));
						form.setBysjjzyysye(rs.getString("bysjjzyysye"));
						form.setBysjyesse(rs.getString("bysjyesse"));
						form.setBymjzyysxe(rs.getString("bymjzyysxe"));
						form.setQxdm(rs.getString("qxdm"));
						form.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
						form.setSign("01");
					}
				} else {
					form.setIsError("�����������˰������ʱ��δ��ѯ�����µ��걨���ݣ�");
				}
			} else {
				form.setIsError("�����������������������˰�����ϵ");
			}
			return form;
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
	}

	private Object doSave(VOPackage vo) throws BaseException {
		// TODO Auto-generated method stub

		CjrjyjmsForm form = (CjrjyjmsForm) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		SWDJJBSJ djxx = null;
		try {

			djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);

		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ���ڲ��Դ�����������
			// System.out.println("---" + form.getLrrq());
			String nd = ((String) form.getSkssrq()).substring(0, 4);
			String dwxz = form.getDwxz().substring(0, 2);
			String sql = "";
			boolean flag = false;
			boolean flag1 = false;
			conn = SfDBResource.getConnection();
			sql = "select f.sqspbh sqspbh from spdb.sp_jl_flqymyys f where f.jsjdm= '"
					+ form.getJsjdm()
					+ "' and to_date('"+form.getSkssrq()+"','yyyymm') between f.jmksrq and f.jmjzrq  and rownum<2";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				form.setSqspbh(rs.getString("sqspbh"));
				flag1 = true;
			}
			if (flag1 == true) {
				String sql1 = "select * from sbdb.sb_jl_jm_azcjrjmsb a where a.jsjdm = ? and to_char(A.SKSSJSRQ, 'yyyymm') = '"
						+ form.getSkssrq() + "'";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, form.getJsjdm());
				// ps.setString(2, ((String)form.getSkssksrq()).substring(0,
				// 6));
				rs = ps.executeQuery();
				while (rs.next()) {
					flag = true;
				}
				if (flag == true) {
					//System.out.println("------------sbrq" + form.getSkssrq());
					sql = "update sbdb.sb_jl_jm_azcjrjmsb a set a.dwxz = ?,a.zgzrs = ?,a.cjrzgrs = ?,a.cjrybl = ?,a.ynyyssr = ?,"
							+ "a.yjyysse = ?,a.xsyhzzse = ?,a.byyjzyysxe = ?,a.syyjzyysxe = ?,a.bykjzyysxe = ?,a.bysjjzyysye = ?,"
							+ "a.bysjyesse = ?,a.bymjzyysxe = ?,a.sbrq = trunc(SYSDATE,'dd' ),a.fsdm = ?,a.lrrq = SYSDATE,a.lrr = ?,a.qxdm = ?  where a.jsjdm = ? and to_char(a.skssjsrq, 'yyyymm') = '"
							+ form.getSkssrq() + "'";
					ps = conn.prepareStatement(sql);
					ps.setString(1, dwxz);
					//System.out.println(dwxz);
					ps.setString(2, form.getZgzrs());
					ps.setString(3, form.getCjrzgrs());
					ps.setString(4, form.getCjrybl());
					ps.setString(5, form.getYnyyssr());
					ps.setString(6, form.getYjyysse());
					ps.setString(7, form.getXsyhzzse());
					ps.setString(8, form.getByyjzyysxe());
					ps.setString(9, form.getSyyjzyysxe());
					ps.setString(10, form.getBykjzyysxe());
					ps.setString(11, form.getBysjjzyysye());
					ps.setString(12, form.getBysjyesse());
					ps.setString(13, form.getBymjzyysxe());
					ps.setString(14, CodeConstant.FSDM_SMSB);
					ps.setString(15, ud.getYhid());
					ps.setString(16, InterfaceDj.getQxdm(ud));
					ps.setString(17, form.getJsjdm());
					System.out.println(sql);
				} else {
					sql = "insert into sbdb.sb_jl_jm_azcjrjmsb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(SYSDATE,'dd' ),SYSDATE,?,?,?, TRUNC(LAST_DAY(to_date('"
							+ form.getSkssrq()
							+ "02"
							+ "','yyyymmdd')), 'dd'), TRUNC(to_date('"
							+ form.getSkssrq()
							+ "02"
							+ "','yyyymmdd'), 'mm'),?,SYSDATE,?,?,?,?)";

					ps = conn.prepareStatement(sql);
					ps.setString(1, form.getJsjdm());
					ps.setString(2, form.getSwdjzh());
					ps.setString(3, dwxz);
					ps.setString(4, form.getZgzrs());
					ps.setString(5, form.getCjrzgrs());
					ps.setString(6, form.getCjrybl());
					ps.setString(7, form.getYnyyssr());
					ps.setString(8, form.getYjyysse());
					ps.setString(9, form.getXsyhzzse());
					ps.setString(10, form.getByyjzyysxe());
					ps.setString(11, form.getSyyjzyysxe());
					ps.setString(12, form.getBykjzyysxe());
					ps.setString(13, form.getBysjjzyysye());
					ps.setString(14, form.getBysjyesse());
					ps.setString(15, form.getBymjzyysxe());
					ps.setString(16, CodeConstant.FSDM_SMSB);
					ps.setString(17, djxx.getSwjgzzjgdm());
					ps.setString(18, ud.getYhid());
					ps.setString(19, nd);
					ps.setString(20, form.getBz());
					ps.setString(21, InterfaceDj.getQxdm(ud));
					ps.setString(22, form.getSqspbh());
					ps.setString(23, ud.getYhid());
				}
				// ���ڲ��Դ�����������
				ps.executeUpdate();
				// ���ڲ��Դ�����������
				//System.out.println("�ɹ�");
				form.setJsjdm(djxx.getJsjdm());
				form.setNsrmc(djxx.getNsrmc());
				form.setSwdjzh(djxx.getSwdjzh());
				form.setZcdz(djxx.getZcdz());	
				form.setJyfw(djxx.getJyfw());
				form.setDwxzList(dwxzListSort("01"));
				form.setIsError("����ɹ�������");
				return form;
			} else {
				form.setJsjdm(djxx.getJsjdm());
				form.setNsrmc(djxx.getNsrmc());
				form.setSwdjzh(djxx.getSwdjzh());
				form.setZcdz(djxx.getZcdz());	
				form.setJyfw(djxx.getJyfw());
				form.setDwxzList(dwxzListSort("01"));
				form.setIsError("�����걨�ļ���˰��˰������ʱ�䲻����������ʱ�䷶Χ������");
				return form;
			}
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

	}

	private void doDelete(VOPackage vo) {
		// TODO Auto-generated method stub

	}

	private List dwxzListSort(String dwxz) throws BaseException {
		Connection conn = null;
		// ����ִ�ж���
		PreparedStatement ps = null;
		// ���ݷ��ض���
		ResultSet rs = null;
		List list = new ArrayList();
		List listsort = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(CodeConstant.JMSSB_CJRJYJMSB_DWXZ_SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			if (dwxz == null || "".equals(dwxz) || dwxz.equals("01")) {
				return list;
			} else if (dwxz.equals("02")) {
				listsort.add(list.get(1));
				listsort.add(list.get(0));
				listsort.add(list.get(2));
				listsort.add(list.get(3));
			} else if (dwxz.equals("03")) {
				listsort.add(list.get(2));
				listsort.add(list.get(0));
				listsort.add(list.get(1));
				listsort.add(list.get(3));
			} else if (dwxz.equals("99")) {
				listsort.add(list.get(3));
				listsort.add(list.get(0));
				listsort.add(list.get(1));
				listsort.add(list.get(2));
			}
			return listsort;
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
	}

}
