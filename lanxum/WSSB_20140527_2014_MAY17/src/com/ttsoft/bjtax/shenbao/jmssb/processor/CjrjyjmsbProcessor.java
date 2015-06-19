package com.ttsoft.bjtax.shenbao.jmssb.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.jmssb.bo.CjrjyjmsbBo;
import com.ttsoft.bjtax.shenbao.jmssb.xmlvo.CjrjyjmsbVo;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class CjrjyjmsbProcessor implements Processor {
	private UserData userData;

	public CjrjyjmsbProcessor() {
	}

	public Object process(VOPackage vo) throws BaseException {
		this.userData = vo.getUserData();
		// TODO Auto-generated method stub
		// �ش�����
		// Object result = null;
		// �ж�VO�Ƿ�Ϊ��
		if (vo == null) {
			throw new NullPointerException();
		}
		// ����Action��ֵ���ò�ͬ��process����
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION: // ǰ̨Ĭ����ʾ
			return doShow(vo);

		case CodeConstant.SMSB_QUERYACTION: // ��ѯ
			return doQuery(vo);

		case CodeConstant.SMSB_SAVEACTION:
			return doSave(vo);
			// case CodeConstant.SMSB_DELETEACTION:
			// return doDelete(vo);
		default:
			throw new SystemException("no such mothod!");
		}
	}

	private List doShow(VOPackage vo) throws BaseException {
		// System.out.println("========CjrjyjmsbProcessor======doShow=========");
		// ����BO
		//CjrjyjmsbBo cjrjyjmsbBo = new CjrjyjmsbBo();
		// ȡ��ҵ���������ʹ��keyΪCzzsjdMapConstant.JSJDM
		//String jsjdm = this.userData.yhid;
		try {
			List dwxzList =  dwxzListSort("01");
			return dwxzList;
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} 

	}

	private Object doQuery(VOPackage vo) throws BaseException {
		 System.out.println("========CjrjyjmsbProcessor======doQuery=========");
		// TODO Auto-generated method stub
		String jsjdm = this.userData.yhid;
		Connection conn = null;
		// ����BO
		CjrjyjmsbBo cjrjyjmsbBo = new CjrjyjmsbBo();
		
		// ����ִ�ж���
		PreparedStatement ps = null;
		// ���ݷ��ض���
		ResultSet rs = null;
		try {
			conn = DBResource.getConnection();
			// ���ڲ��Դ�����������
			boolean flag = false;
			String sql = "";
				sql = "select f.sqspbh sqspbh from spdb.sp_jl_flqymyys f where f.jsjdm= '"
						+ jsjdm
						+ "' and rownum<2";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					cjrjyjmsbBo.setSqspbh(rs.getString("sqspbh"));
					flag = true;
				}
				if (flag == true) {
					// ��sql��ѯsbdb.sb_jl_jm_azcjrjmsb����jsjdm��Ӧ�����걨����
					sql = "select max(to_char(a.skssjsrq, 'yyyymm')) skssrq from sbdb.sb_jl_jm_azcjrjmsb a where a.jsjdm = '"
							+ jsjdm + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					String maxskssrq = "";
					while (rs.next()) {
						maxskssrq = rs.getString(1);
					}
					String sql1 = "SELECT A.SWDJZH,A.JSJDM,A.NSRMC, A.ZCDZ, A.JYFW,B.DWXZ,B.ZGZRS,B.CJRZGRS,B.CJRYBL,"
							+ "B.YNYYSSR,B.YJYYSSE,B.XSYHZZSE,B.BYYJZYYSXE,B.SYYJZYYSXE,B.BYKJZYYSXE,B.BYSJJZYYSYE,"
							+ "B.BYSJYESSE,B.BYMJZYYSXE,B.SBRQ,B.LRRQ,B.FSDM,B.SWJGZZJGDM,B.LRR,B.SKSSJSRQ,(SELECT to_char(add_months(SYSDATE,-1),'yyyymm') FROM dual) SKSSRQ,"
							+ "B.ND,B.CJRQ,B.BZ,B.QXDM FROM DJDB.DJ_JL_JBSJ A, SBDB.SB_JL_JM_AZCJRJMSB B WHERE A.JSJDM = B.JSJDM(+) AND A.JSJDM =?";
					if ("".equals(maxskssrq) || maxskssrq == null) {
						sql = sql1;
					} else {
						sql = sql1 + "and to_char(b.skssjsrq, 'yyyymm')=" + maxskssrq;
					}
					// ����sql
					ps = conn.prepareStatement(sql);
					// ���ڲ��Դ�����������
					ps.setString(1, jsjdm);
					rs = ps.executeQuery();
					if (rs.next()) {
						
						// ��BO��ֵ
						cjrjyjmsbBo.setJsjdm(rs.getString("jsjdm"));
						cjrjyjmsbBo.setSkssrq(rs.getString("skssrq"));
						cjrjyjmsbBo.setNsrmc(rs.getString("nsrmc"));
						cjrjyjmsbBo.setSwdjzh(rs.getString("swdjzh"));
						cjrjyjmsbBo.setZcdz(rs.getString("zcdz"));
						cjrjyjmsbBo.setDwxz(rs.getString("dwxz"));
						cjrjyjmsbBo.setDwxzList(dwxzListSort(rs.getString("dwxz")));
						cjrjyjmsbBo.setJyfw(rs.getString("jyfw"));
						cjrjyjmsbBo.setZgzrs(rs.getString("zgzrs"));
						cjrjyjmsbBo.setCjrzgrs(rs.getString("cjrzgrs"));
						cjrjyjmsbBo.setCjrybl(String.valueOf(rs.getFloat("cjrybl")));
						cjrjyjmsbBo.setYnyyssr(rs.getString("ynyyssr"));
						cjrjyjmsbBo.setYjyysse(rs.getString("yjyysse"));
						cjrjyjmsbBo.setXsyhzzse(rs.getString("xsyhzzse"));
						cjrjyjmsbBo.setByyjzyysxe(rs.getString("byyjzyysxe"));
						cjrjyjmsbBo.setSyyjzyysxe(rs.getString("syyjzyysxe"));
						cjrjyjmsbBo.setBykjzyysxe(rs.getString("bykjzyysxe"));
						cjrjyjmsbBo.setBysjjzyysye(rs.getString("bysjjzyysye"));
						cjrjyjmsbBo.setBysjyesse(rs.getString("bysjyesse"));
						cjrjyjmsbBo.setBymjzyysxe(rs.getString("bymjzyysxe"));
						cjrjyjmsbBo.setQxdm(rs.getString("qxdm"));
						cjrjyjmsbBo.setLrr(rs.getString("lrr"));
						cjrjyjmsbBo.setSignFlagVal("01");

					} else {
						cjrjyjmsbBo.setDwxz("01");
						cjrjyjmsbBo.setDwxzList(dwxzListSort("01"));
						cjrjyjmsbBo.setIsError("û�и���˰�˵ĵǼ���Ϣ����û��Ȩ�޲鿴����˰����Ϣ");
					}
				} else {
					cjrjyjmsbBo.setDwxz("01");
					cjrjyjmsbBo.setDwxzList(dwxzListSort("01"));
					cjrjyjmsbBo.setIsError("�����������������������˰�����ϵ");
				}
				return cjrjyjmsbBo;
			
			
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			DBResource.destroyConnection(conn);
		}
	}

	private Map doSave(VOPackage vo) throws BaseException {
		// TODO Auto-generated method stub
		Map dataMap = (Map) vo.getData();
		UserData ud = vo.getUserData();
		DzyjHelper dh = new DzyjHelper();
		
		CjrjyjmsbVo cjrjyjmsbVo = (CjrjyjmsbVo) dataMap.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) dataMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		
		
		//String jsjdm = ud.yhid;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ���ڲ��Դ�����������
			//System.out.println("=============nd============"+(((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq()).substring(0, 4));
			String nd = (((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq()).substring(0, 4);
			String dwxz = (((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getDwxz());
			String sql = "";
			boolean flag = false;
			conn = DBResource.getConnection();
			String sql1 = "select * from sbdb.sb_jl_jm_azcjrjmsb a where a.jsjdm = ? and to_char(A.SKSSJSRQ, 'yyyymm') = '"
					+ ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq()+"'";
			ps = conn.prepareStatement(sql1);
			ps.setString(1, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			if (flag == true) {
				sql = "update sbdb.sb_jl_jm_azcjrjmsb a set a.dwxz = ?,a.zgzrs = ?,a.cjrzgrs = ?,a.cjrybl = ?,a.ynyyssr = ?,"
						+ "a.yjyysse = ?,a.xsyhzzse = ?,a.byyjzyysxe = ?,a.syyjzyysxe = ?,a.bykjzyysxe = ?,a.bysjjzyysye = ?,"
						+ "a.bysjyesse = ?,a.bymjzyysxe = ?,a.sbrq = trunc(SYSDATE,'dd'),a.fsdm = ?,a.lrrq = SYSDATE,a.lrr = ? where a.jsjdm = ? and to_char(A.SKSSJSRQ, 'yyyymm') = '"
						+ ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq() + "'";
				ps = conn.prepareStatement(sql);
				ps.setString(1, dwxz);
				ps.setString(2, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getZgzrs());
				ps.setString(3,  ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getCjrzgrs());
				ps.setString(4, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getCjrybl());
				ps.setString(5, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getYnyyssr());
				ps.setString(6, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getYjyysse());
				ps.setString(7, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getXsyhzzse());
				ps.setString(8, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getByyjzyysxe());
				ps.setString(9, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSyyjzyysxe());
				ps.setString(10, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBykjzyysxe());
				ps.setString(11, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBysjjzyysye());
				ps.setString(12, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBysjyesse());
				ps.setString(13, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBymjzyysxe());
				ps.setString(14, CodeConstant.FSDM_WSSB);
				ps.setString(15, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
				ps.setString(16, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
				System.out.println("sql=================="+sql);
			} else {
				sql = "insert into sbdb.sb_jl_jm_azcjrjmsb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(SYSDATE,'dd'),SYSDATE,?,?,?, TRUNC(LAST_DAY(to_date('"
						+ ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq()
						+ "02"
						+ "','yyyymmdd')), 'dd'), TRUNC(to_date('"
						+ ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSkssrq()
						+ "02"
						+ "','yyyymmdd'), 'mm'),?,SYSDATE,?,?,?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1,  ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
				ps.setString(2, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSwdjzh());
				ps.setString(3, dwxz);
				ps.setString(4, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getZgzrs());
				ps.setString(5, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getCjrzgrs());
				ps.setString(6, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getCjrybl());
				ps.setString(7, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getYnyyssr());
				ps.setString(8, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getYjyysse());
				ps.setString(9, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getXsyhzzse());
				ps.setString(10, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getByyjzyysxe());
				ps.setString(11, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSyyjzyysxe());
				ps.setString(12, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBykjzyysxe());
				ps.setString(13, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBysjjzyysye());
				ps.setString(14, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBysjyesse());
				ps.setString(15, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBymjzyysxe());
				ps.setString(16, CodeConstant.FSDM_WSSB);
				ps.setString(17, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSwjgzzjgdm());
				ps.setString(18, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
				ps.setString(19, nd);
				ps.setString(20, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getBz());
				ps.setString(21, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getQxdm());
				ps.setString(22, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getSqspbh());
				ps.setString(23, ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm());
			}
			
			// System.out.println(sql);
			// ���ڲ��Դ�����������
			ps.executeUpdate();
			// ���ڲ��Դ�����������
			// System.out.println("�ɹ�");
			dataMap.put("dwxzList", dwxzListSort("01"));
			dataMap.put("signFlagVal", "01");
			
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			DBResource.destroyConnection(conn);
		}
		
		if (ud.getCaflag()) {
			try {
				String ywid = ((CjrjyjmsbBo) dataMap.get("cjrjyjmsbbo")).getJsjdm()
						+ "+"
						+ DjStringUtil
								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
//				String ywid = cjrjyjmsbVo.getNsrxx().getJsjdm()
//						+ "+"
//						+ DjStringUtil
//								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				//System.out.println("======ywid:" + ywid);*/
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
			} catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			}
			dataMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}	
		return dataMap;
	}

	/*
	 * private Object doDelete(VOPackage vo) { // TODO Auto-generated method
	 * stub
	 *  }
	 */
	private List dwxzListSort(String dwxz) throws BaseException {
		Connection conn = null;
		// ����ִ�ж���
		PreparedStatement ps = null;
		// ���ݷ��ض���
		ResultSet rs = null;
		List list = new ArrayList();
		List listsort = new ArrayList();
		try {
			conn = DBResource.getConnection();
			ps = conn.prepareStatement(ProcessorNames.JMSSB_CJRJYJMSB_DWXZ_SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			if (dwxz == null || dwxz.equals("") || dwxz.equals("01")) {
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
			// System.out.println("========CjrjyjmsbProcessor======dwxzListSort=========");
			return listsort;
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			DBResource.destroyConnection(conn);
		}
	}

}
