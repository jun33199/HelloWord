package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.ttsoft.bjtax.dj.DjCodeConstant;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web.SbqyInfo;
import com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web.SwjgzzjgInfo;
import com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web.QyjbcwzbCxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.codetable.CodeTableInterface;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ
 * </p>
 * <p>
 * Description: �����걨-�걨����-��ҵ��������ָ����걨���Ȳ�ѯ
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: SYAX
 * </p>
 * 
 * @author Ha Zhengze
 * @version 1.0
 */

public class QyjbcwzbCxProcessor implements Processor {

	public static final String QYZT_ALL_DM = "00";

	public static final String QYZT_ALL_MS = "ȫ��״̬";

	/**
	 * Processor Dispacher
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @throws BaseException
	 *             Excetion throwable
	 */
	public Object process(VOPackage vo) throws BaseException {
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			return doShow(vo);
		case CodeConstant.SMSB_QUERYACTION: //
			return doQueryA(vo);
		case CodeConstant.SMSB_QUERYACTION1:
			return doQueryB(vo);
		case CodeConstant.SMSB_QUERYACTION2: //
			return doQueryC(vo);
		case CodeConstant.SMSB_QUERYACTION3:
			return doQueryD(vo);
		case CodeConstant.SMSB_QUERYACTION4:
			return doQueryE(vo);
		case CodeConstant.SMSB_TOEXCELACTION:
			return doExportA(vo);
		case CodeConstant.SMSB_TOEXCELACTION1:
			return doExportB(vo);
		default:
			throw new ApplicationException("δ�ҵ����������Ĳ���");
		}
	}

	/**
	 * ��ʼ������
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			///3.2.3.�����û�����������ʾ����
			String yhjb=ud.yhjb;
			if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { // ˰������				
			} else if (CodeConstants.JBDM_FJ.equals(yhjb)) { // �־ּ�		
				pf.setShowFjQueryButton("0");
			}
			else if (CodeConstants.JBDM_SJ.equals(yhjb)) { // �оּ�		
				pf.setShowFjQueryButton("0");		
				pf.setShowSjQueryButton("0");
			}
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// *********add by zhangshubing **********
			// /3.4.�����걨����
			pf.setSblxList(this.getSblxList(da));
			// ***************************************
			// *********add by zhangshubing **********
			// /3.5.���õǼ�ע������
			pf.setDjzclx(this.getDjzclxList());
			List djzclx2 = new ArrayList();
			djzclx2.add("* ��ѯ�в������ĵǼ�ע������ *");
			pf.setDjzclx2(djzclx2);
			// ***************************************
			// /3.6.����ֵ�б�ҳ�����

			pf.setHeadLrr(ud.getYhid());
			pf.setHeadLrrjb(ud.getYhjb());
			pf.setHeadSwjgzzjgdm(ud.getSsdwdm());
			pf.setHeadSwjgzzjgmc(ud.ssdwmc);
			pf.setHeadDqrq(SfDateUtil.getDate());
			pf.setNd(String.valueOf(TinyTools.getYear(new Date())));
			pf.setQuerySbrqq(pf.getHeadDqrq());
			pf.setQuerySbrqz(pf.getHeadDqrq());
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doQueryA
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQueryA(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List tmpList = null;
		List dataList = new ArrayList();
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			tmpList = this.queryZeroSB(ud, pf, da);
			// /3.5.��ȡ��ҳ������
			pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
			// /3.6.�����ѯ���
			dataList = this.getPageDataList(tmpList, pf);
			tmpList = null;
			// /3.7.����ҳ�����
			// ************add by zhangshubing ***************
			// System.out.println("*********************");
			// System.out.println("querySblx : " + pf.getQuerySblx());
			// System.out.println("*********************");
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doQueryB
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQueryB(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List tmpList = null;
		List dataList = new ArrayList();
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			tmpList = this.queryNoSB(ud, pf, da);
			// /3.5.��ȡ��ҳ������
			pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
			// /3.6.�����ѯ���
			dataList = this.getPageDataList(tmpList, pf);
			tmpList = null;
			// /3.7.����ҳ�����
			debug("���β�ѯ���ݼ�size=" + dataList.size());
			// ************add by zhangshubing ***************
			// System.out.println("*********************");
			// System.out.println("querySblx : " + pf.getQuerySblx());
			// System.out.println("*********************");
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doQueryC
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQueryC(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List dataList = new ArrayList();
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			debug("=========���н�������ѯ=========");
			// //3.4.0.��ȡ��ʼ������
			String qxdm = pf.getQueryFj().substring(0, 2);
			String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
					pf.getQuerySwjgzzjg().indexOf("|"));
			String nsrzt = pf.getQueryQyzt().substring(0,
					pf.getQueryQyzt().indexOf("|"));
			// **************************************
			String fjdm = pf.getQueryFj().substring(0,
					pf.getQueryFj().indexOf("|"));
			String nd = pf.getNd();
			String swjgzzjgdm_prefix = swjgzzjgdm.substring(0, 2);
			String swjgzzjgdm_postfix = swjgzzjgdm.substring(2);
			String fjdm_prefix = fjdm.substring(0, 2);
			String fjdm_postfix = fjdm.substring(2);
			// **************************************
			String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx(), "t.");
			// //3.4.1.���ò�ѯӦ����sql
			StringBuffer sb = new StringBuffer();
			sb.append("select z.swjgzzjgdm swjgzzjgdm");
			sb.append(",z.swjgzzjgmc ");
			sb
					.append(",(select count(jsjdm) data1 from djdb.dj_jl_jbsj t where ");
			sb.append(djzclxWhere);
			sb.append(" and t.swjgzzjgdm like SUBSTR(z.swjgzzjgdm,0,2)||'%'");
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and t.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(") data1");
			sb.append(" from dmdb.gy_dm_swjgzzjg z ");
			sb.append(" where 2=2");
			sb.append(" and z.ccbs='1'");
			sb.append(" and z.zxbs='0'");
			sb.append(" order by z.swjgzzjgdm");
			/**
			 * @todo ���н�������ѯ
			 */
			debug(sb.toString());
			// //3.4.1.ִ�в�ѯӦ����sql
			ResultSet rs = da.querySQL(sb.toString());
			// //3.4.2.����Ӧ��������
			SwjgzzjgInfo si = null;
			int index = 1;
			while (rs.next()) {
				si = new SwjgzzjgInfo();
				si.setIndex(index++);
				si.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				si.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));
				si.setData1(rs.getInt("data1"));
				dataList.add(si);
			}
			rs.close();
			// // 3.4.3.���ò�ѯ������sql
			sb = new StringBuffer();
			sb.append("select z2.qxfjdm swjgzzjgdm");
			sb.append(",count(distinct z4.jsjdm) data2");
			sb
					.append(" from dmdb.gy_dm_swjgzzjg z2,sbdb.sb_jl_qyjbcwzb z3 ,djdb.dj_jl_jbsj z4");
			sb.append(" where ");
			sb.append(this.getDjzclxWhere(pf.getAlldjzclx(), "z4."));
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and z4.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(" and z2.swjgzzjgdm=z4.swjgzzjgdm");
			sb.append(" and z4.jsjdm=z3.jsjdm");
			sb.append(" and z3.nd=");
			sb.append(SBStringUtils.getSQLStr(pf.getNd()));
			sb.append(" and z2.ccbs='2'");
			sb.append(" and z2.zxbs='0'");
			sb.append(" group by z2.qxfjdm");
			debug(sb.toString());
			// //3.4.4.ִ�в�ѯ������sql
			rs = da.querySQL(sb.toString());
			// //3.4.5.��������������
			String tmp_swjgzzjgdm = null;
			int tmp = 0;
			while (rs.next()) {
				tmp_swjgzzjgdm = rs.getString("swjgzzjgdm");
				tmp = rs.getInt("data2");
				for (int i = 0; i < dataList.size(); i++) {
					si = (SwjgzzjgInfo) dataList.get(i);
					if (si.getSwjgzzjgdm().equals(tmp_swjgzzjgdm)) {
						si.setData2(tmp);
						si.setData3(si.getData1() - tmp);
					}
				}
			}
			rs.close();
			// //3.4.5.������������
			for (int i = 0; i < dataList.size(); i++) {
				si = (SwjgzzjgInfo) dataList.get(i);
				if (si.getData2()==si.getData3()&&si.getData3()==0) {
					si.setData3(si.getData1());
				}
			}
			// /3.5.���ò�ѯ����
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList1(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doQueryD
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQueryD(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List dataList = new ArrayList();
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			debug("=========���н�������ѯ=========");
			// //3.4.0.��ȡ��ʼ������
			String qxdm = pf.getQueryFj().substring(0, 2);
			String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
					pf.getQuerySwjgzzjg().indexOf("|"));
			String nsrzt = pf.getQueryQyzt().substring(0,
					pf.getQueryQyzt().indexOf("|"));
			// **************************************
			String fjdm = pf.getQueryFj().substring(0,
					pf.getQueryFj().indexOf("|"));
			String nd = pf.getNd();
			String swjgzzjgdm_prefix = swjgzzjgdm.substring(0, 2);
			String swjgzzjgdm_postfix = swjgzzjgdm.substring(2);
			String fjdm_prefix = fjdm.substring(0, 2);
			String fjdm_postfix = fjdm.substring(2);
			// **************************************
			String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx(), "t.");
			// //3.4.1.���ò�ѯӦ����sql
			StringBuffer sb = new StringBuffer();
			sb.append("select z.swjgzzjgdm swjgzzjgdm");
			sb.append(",z.swjgzzjgmc ");
			sb
					.append(",(select count(jsjdm) data1 from djdb.dj_jl_jbsj t where ");
			sb.append(djzclxWhere);
			sb.append(" and t.swjgzzjgdm like SUBSTR(z.swjgzzjgdm,0,2)||'%'");
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and t.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(") data1");
			sb.append(" from dmdb.gy_dm_swjgzzjg z ");
			sb.append(" where 2=2");
			sb.append(" and z.ccbs='1'");
			sb.append(" and z.zxbs='0'");
			if (!"00".equals(fjdm_prefix)) {
				sb.append(" and z.swjgzzjgdm=");
				sb.append(SBStringUtils.getSQLStr(fjdm));
			}
			sb.append(" order by z.swjgzzjgdm");
			/**
			 * @todo ���н�������ѯ
			 */
			debug(sb.toString());
			// //3.4.1.ִ�в�ѯӦ����sql
			ResultSet rs = da.querySQL(sb.toString());
			// //3.4.2.����Ӧ��������
			SwjgzzjgInfo si = null;
			int index = 1;
			while (rs.next()) {
				si = new SwjgzzjgInfo();
				si.setIndex(index++);
				si.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				si.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));
				si.setData1(rs.getInt("data1"));
				dataList.add(si);
			}
			rs.close();
			// // 3.4.3.���ò�ѯ������sql
			sb = new StringBuffer();
			sb.append("select z2.qxfjdm swjgzzjgdm");
			sb.append(",count(distinct z4.jsjdm) data2");
			sb
					.append(" from dmdb.gy_dm_swjgzzjg z2,sbdb.sb_jl_qyjbcwzb z3 ,djdb.dj_jl_jbsj z4");
			sb.append(" where ");
			sb.append(this.getDjzclxWhere(pf.getAlldjzclx(), "z4."));
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and z4.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(" and z2.swjgzzjgdm=z4.swjgzzjgdm");
			sb.append(" and z4.jsjdm=z3.jsjdm");
			sb.append(" and z3.nd=");
			sb.append(SBStringUtils.getSQLStr(pf.getNd()));
			sb.append(" and z2.ccbs='2'");
			sb.append(" and z2.zxbs='0'");
			if (!"00".equals(fjdm_prefix)) {
				sb.append(" and z2.qxfjdm=");
				sb.append(SBStringUtils.getSQLStr(fjdm));
			}
			sb.append(" group by z2.qxfjdm");
			debug(sb.toString());
			// //3.4.4.ִ�в�ѯ������sql
			rs = da.querySQL(sb.toString());
			// //3.4.5.��������������
			String tmp_swjgzzjgdm = null;
			int tmp = 0;
			while (rs.next()) {
				tmp_swjgzzjgdm = rs.getString("swjgzzjgdm");
				tmp = rs.getInt("data2");
				for (int i = 0; i < dataList.size(); i++) {
					si = (SwjgzzjgInfo) dataList.get(i);
					if (si.getSwjgzzjgdm().equals(tmp_swjgzzjgdm)) {
						si.setData2(tmp);
						si.setData3(si.getData1() - tmp);
					}
				}
			}
			rs.close();
			// //3.4.5.������������
			for (int i = 0; i < dataList.size(); i++) {
				si = (SwjgzzjgInfo) dataList.get(i);
				if (si.getData2()==si.getData3()&&si.getData3()==0) {
					si.setData3(si.getData1());
				}
			}
			// /3.5.���ò�ѯ����
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList1(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doQueryC
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQueryE(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List dataList = new ArrayList();
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			debug("=========���н�������ѯ=========");
			// //3.4.0.��ȡ��ʼ������
			String qxdm = pf.getQueryFj().substring(0, 2);
			String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
					pf.getQuerySwjgzzjg().indexOf("|"));
			String nsrzt = pf.getQueryQyzt().substring(0,
					pf.getQueryQyzt().indexOf("|"));
			// **************************************
			String fjdm = pf.getQueryFj().substring(0,
					pf.getQueryFj().indexOf("|"));
			String nd = pf.getNd();
			String swjgzzjgdm_prefix = swjgzzjgdm.substring(0, 2);
			String swjgzzjgdm_postfix = swjgzzjgdm.substring(2);
			String fjdm_prefix = fjdm.substring(0, 2);
			String fjdm_postfix = fjdm.substring(2);
			// **************************************
			// String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx(),"t.");
			// //3.4.1.���ò�ѯӦ����sql
			StringBuffer sb = new StringBuffer();
			sb.append("select z2.swjgzzjgdm swjgzzjgdm");
			sb
					.append(",(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg t where t.swjgzzjgdm=z2.swjgzzjgdm) swjgzzjgmc");
			sb.append(",count(distinct z4.jsjdm) data1");
			sb.append(" from dmdb.gy_dm_swjgzzjg z2,djdb.dj_jl_jbsj z4");
			sb.append(" where ");
			sb.append(this.getDjzclxWhere(pf.getAlldjzclx(), "z4."));
			sb.append(" and z2.swjgzzjgdm=z4.swjgzzjgdm");
			// sb.append(" and z4.jsjdm=z3.jsjdm");
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and z4.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(" and z2.ccbs='2'");
			sb.append(" and z2.zxbs='0'");
			if (!"00".equals(swjgzzjgdm_postfix)) {
				sb.append(" and z2.swjgzzjgdm=");
				sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
			}
			if (!"00".equals(fjdm_prefix)) {
				sb.append(" and z2.qxfjdm=");
				sb.append(SBStringUtils.getSQLStr(fjdm));
			}
			sb.append("	group by z2.swjgzzjgdm");
			debug(sb.toString());
			// //3.4.1.ִ�в�ѯӦ����sql
			ResultSet rs = da.querySQL(sb.toString());
			// //3.4.2.����Ӧ��������
			SwjgzzjgInfo si = null;
			int index = 1;
			while (rs.next()) {
				si = new SwjgzzjgInfo();
				si.setIndex(index++);
				si.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				si.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));
				si.setData1(rs.getInt("data1"));
				dataList.add(si);
			}
			rs.close();
			// //3.4.3.���ò�ѯ������sql
			sb = new StringBuffer();
			sb.append("select z1.swjgzzjgdm swjgzzjgdm");
			sb.append(",count(distinct z3.jsjdm) data2");
			sb.append(" from dmdb.gy_dm_swjgzzjg z1");
			sb.append(",sbdb.sb_jl_qyjbcwzb z3 ");
			sb.append(",djdb.dj_jl_jbsj z4");
			sb.append(" where ");
			sb.append(this.getDjzclxWhere(pf.getAlldjzclx(), "z4."));
			sb.append(" and z1.swjgzzjgdm=z4.swjgzzjgdm");
			sb.append(" and z1.ccbs='2'");
			sb.append(" and z1.zxbs='0'");
			sb.append(" and z3.jsjdm=z4.jsjdm");
			sb.append(" and z3.nd=");
			sb.append(SBStringUtils.getSQLStr(pf.getNd()));
			if (!"00".equals(swjgzzjgdm_postfix)) {
				sb.append(" and z1.swjgzzjgdm=");
				sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
			}
			if (!"00".equals(fjdm_prefix)) {
				sb.append(" and z1.qxfjdm=");
				sb.append(SBStringUtils.getSQLStr(fjdm));
			}
			if (!QYZT_ALL_DM.equals(nsrzt)) {
				sb.append(" and z4.nsrzt=");
				sb.append(SBStringUtils.getSQLStr(nsrzt));
			}
			sb.append(" group by z1.swjgzzjgdm");
			debug(sb.toString());
			// //3.4.4.ִ�в�ѯ������sql
			rs = da.querySQL(sb.toString());
			// //3.4.5.��������������
			String tmp_swjgzzjgdm = null;
			int tmp = 0;
			while (rs.next()) {
				tmp_swjgzzjgdm = rs.getString("swjgzzjgdm");
				tmp = rs.getInt("data2");
				for (int i = 0; i < dataList.size(); i++) {
					si = (SwjgzzjgInfo) dataList.get(i);
					if (si.getSwjgzzjgdm().equals(tmp_swjgzzjgdm)) {
						si.setData2(tmp);
						si.setData3(si.getData1() - tmp);
					}
				}
			}
			rs.close();
			// //3.4.6.������������
			for (int i = 0; i < dataList.size(); i++) {
				si = (SwjgzzjgInfo) dataList.get(i);
				if (si.getData2()==si.getData3()&&si.getData3()==0) {
					si.setData3(si.getData1());
				}
			}
			// /3.5.���ò�ѯ����
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList1(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doExportA
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doExportA(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List tmpList = null;
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			tmpList = this.queryZeroSB(ud, pf, da);
			// /3.5.�����ѯ���
			// ************add by zhangshubing ***************
			// System.out.println("*********************");
			// System.out.println("querySblx : " + pf.getQuerySblx());
			// System.out.println("*********************");
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList(tmpList);
			tmpList = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * doExportB
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doExportB(VOPackage vo) throws BaseException {
		// 1.�������
		QyjbcwzbCxActionForm pf = null;
		UserData ud = null;
		SfDBAccess da = null;
		Connection conn = null;
		List tmpList = null;
		// 2.��ʼ��VO���ݶ���
		try {
			ud = vo.getUserData();
			pf = (QyjbcwzbCxActionForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getBaseException(e);
		}
		// 3.��ʼҵ��
		try {
			// /3.1.��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /3.2.0.����ud���û���������־���֯���������ֵ�б�
			pf.setFjList(this.getFjList(ud, da));
			// /3.2.1.����ud���û��������˰�������֯���������ֵ�б�
			pf.setSwsList(this.getSwsList(ud, da));
			// /3.3.������ҵ״̬�б�
			pf.setQyztList(this.getQyztList(ud, da));
			// **********add by zhangshubing ************
			// /3.3.2.�����걨�����б�
			pf.setSblxList(this.getSblxList(da));
			// /3.3.3.���õǼ�ע�������б�
			List tempDjzclxList = this.getDjzclxList();
			pf.setDjzclx(this
					.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
			pf.setDjzclx2(this
					.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
			// **********add end by zhangshubing*********

			// /3.4.��ʼ��ѯ
			tmpList = this.queryNoSB(ud, pf, da);
			// /3.5.�����ѯ���
			// ************add by zhangshubing ***************
			// System.out.println("*********************");
			// System.out.println("querySblx : " + pf.getQuerySblx());
			// System.out.println("*********************");
			pf.setQuerySblx(pf.getQuerySblx());
			// ************add by zhangshubing end ***********

			pf.setDataList(tmpList);
			tmpList = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return pf;
	}

	/**
	 * ��ȡ��ǰ��ҳ���ݼ�
	 * 
	 * @param tmpList
	 *            �������ݼ�
	 * @param pf
	 *            ҳ�����
	 * @return ��ǰ��ҳ���ݼ�
	 */
	private List getPageDataList(List tmpList, QyjbcwzbCxActionForm pf) {
		// 1.�������
		List dataList = new ArrayList();
		// 2.��ʼ������ֵ
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.��ʼҵ��
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.����ֵ
		return dataList;
	}

	/**
	 * ��ȡ���з���������˰����
	 * 
	 * @param ud
	 *            �û�����
	 * @param da
	 *            ���ݿ��������
	 * @return ����������˰����List
	 * @throws BaseException
	 *             �쳣
	 */
	private List getSwsList(UserData ud, SfDBAccess da) throws BaseException {
		// 1.�������
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		String fjdm = ud.getSsdwdm().substring(0, 2) + "00";
		// 2.�����û��������ʹ��ĳһ��sql
		String yhjb = ud.getYhjb();
		String swsjb = CodeConstants.JBDM_SWSJ; // ˰������
		String fjjb = CodeConstants.JBDM_FJ; // ˰������
		String grjb = CodeConstants.JBDM_GRJ;
		if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { // ˰������
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and swjgzzjgdm="
					+ SBStringUtils.getSQLStr(swjgzzjgdm);
		} else if (CodeConstants.JBDM_FJ.equals(yhjb)) { // �־ּ�
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and qxfjdm="
					+ SBStringUtils.getSQLStr(fjdm);
			tmpList.add(fjdm + "|ȫ��");
		}

		else if (CodeConstants.JBDM_SJ.equals(yhjb)) { // �оּ�

			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' order by swjgzzjgdm asc";
			tmpList.add("0000|ȫ��");

		}

		// 3.ִ�в�ѯ����������
		try {
			rs = da.querySQL(sql);
			while (rs.next()) {
				tmpList.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 4.����ֵ
		return tmpList;
	}

	/**
	 * ��ȡ���з��������ķ־��б�
	 * 
	 * @param ud
	 *            �û�����
	 * @param da
	 *            ���ݿ��������
	 * @return ����������˰����List
	 * @throws BaseException
	 *             �쳣
	 */
	private List getFjList(UserData ud, SfDBAccess da) throws BaseException {
		// 1.�������
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		String fjdm = ud.getSsdwdm().substring(0, 2) + "00";
		// 2.�����û��������ʹ��ĳһ��sql
		String yhjb = ud.getYhjb();
		String swsjb = CodeConstants.JBDM_SWSJ; // ˰������
		String fjjb = CodeConstants.JBDM_FJ; // ˰������
		String grjb = CodeConstants.JBDM_GRJ;
		if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { // ˰������
			sql = "select a.qxfjdm||'|'||(select t.swjgzzjgmc from dmdb.gy_dm_swjgzzjg t where t.swjgzzjgdm=a.qxfjdm) from dmdb.gy_dm_swjgzzjg a where a.ccbs='2' and a.zxbs='0' and a.swjgzzjgdm="
					+ SBStringUtils.getSQLStr(swjgzzjgdm);
		} else if (CodeConstants.JBDM_FJ.equals(yhjb)) { // �־ּ�
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='1' and zxbs='0' and qxfjdm="
					+ SBStringUtils.getSQLStr(fjdm);
		}

		else if (CodeConstants.JBDM_SJ.equals(yhjb)) { // �оּ�

			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='1' and zxbs='0' order by swjgzzjgdm asc";
			tmpList.add("0000|ȫ��");

		}

		// 3.ִ�в�ѯ����������
		try {
			rs = da.querySQL(sql);
			while (rs.next()) {
				tmpList.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 4.����ֵ
		return tmpList;
	}

	private List getQyztList(UserData ud, SfDBAccess da) {
		// 1.�������
		String sql = null;
		List dataList = new ArrayList();
		List tmpList = new ArrayList();
		CodeTableInterface ci = null;
		String value = null;
		// 2.������ݲ�ѯ������List
		// /2.0.����ͨ�ñ��
		dataList.add(QYZT_ALL_DM + "|" + QYZT_ALL_MS);
		// /2.1.ͨ���ǼǵĴ��빤�߻�ȡ������ҵ״̬�б�
		tmpList = CodeTableUtil.getCodeTableList(CodeTableKey.NSRZT);
		// /2.2.�������ݳ�Ϊ���ϸ�ʽ��״̬�б�
		for (int i = 0; i < tmpList.size(); i++) {
			ci = (CodeTableInterface) tmpList.get(i);
			if (!DjCodeConstant.NSRZT_SHUIWUSUO.equals(ci.getOptionValue())) {
				dataList.add(ci.getOptionValue() + "|" + ci.getOptionText());
			}
		}
		// 99.����ֵ
		return dataList;
	}

	/**
	 * ��ѯ���걨�� ��ѯ������˰�������֯�������롢��ҵ״̬����������롢�걨�������걨����ֹ��
	 * �����ҵ״̬����ȫ���Ļ���Ҫ�����������ҵ״̬�������������
	 * 
	 * @param ud
	 *            �û�����
	 * @param pf
	 *            ҳ�����
	 * @param da
	 *            ���ݿ��������
	 * @return ��ѯ���
	 * @exception BaseException
	 *                BaseException
	 */
	private List queryZeroSB(UserData ud, QyjbcwzbCxActionForm pf, SfDBAccess da)
			throws BaseException {
		// 1.�������
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		SbqyInfo si = null;
		// 2.����������ѯ��sql
		sql = this.getZeroSBQuerySqlStr(pf);
		this.debug(sql);
		try {
			// 3.ִ�в�ѯ
			rs = da.querySQL(sql);
			// 4.��������
			int count = 1;
			while (rs.next()) {
				si = new SbqyInfo();
				si.setIndex(count++);
				si.setJsjdm(rs.getString(1));
				si.setNsrmc(rs.getString(2));
				si.setDjzclxdm(rs.getString(3));
				si.setQyztdm(rs.getString(4));
				si.setJydz(rs.getString(5));
				si.setLxdh(rs.getString(6));
				si.setDjzclxmc(rs.getString(7));
				si.setQyztmc(rs.getString(8));
				tmpList.add(si);
			}
			rs.close();
		} catch (SQLException e) {
			debugCore(sql);
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 99.����ֵ
		return tmpList;
	}

	/**
	 * ��ѯ���걨��
	 * 
	 * @param ud
	 *            �û�����
	 * @param pf
	 *            ҳ�����
	 * @param da
	 *            ���ݿ��������
	 * @return ��ѯ���
	 * @exception BaseException
	 *                BaseException
	 */
	private List queryNoSB(UserData ud, QyjbcwzbCxActionForm pf, SfDBAccess da)
			throws BaseException {
		// 1.�������
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		SbqyInfo si = null;
		// 2.����������ѯ��sql
		sql = this.getNoSBQuerySqlStr(pf);
		this.debug(sql);
		// 3.ִ�в�ѯ
		try {
			rs = da.querySQL(sql);
			// 4.��������
			int count = 1;
			while (rs.next()) {
				si = new SbqyInfo();
				si.setIndex(count++);
				si.setJsjdm(rs.getString(1));
				si.setNsrmc(rs.getString(2));
				si.setDjzclxdm(rs.getString(3));
				si.setQyztdm(rs.getString(4));
				si.setJydz(rs.getString(5));
				si.setLxdh(rs.getString(6));
				si.setDjzclxmc(rs.getString(7));
				si.setQyztmc(rs.getString(8));
				tmpList.add(si);
			}
			rs.close();
		} catch (SQLException e) {
			debugCore(sql);
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 99.����ֵ
		return tmpList;
	}

	/**
	 * ��ȡ���걨��ѯSQL
	 * 
	 * @param pf
	 *            ҳ�����
	 * @return QuerySqlFROMAndWhereStr
	 */
	private String getZeroSBQuerySqlStr(QyjbcwzbCxActionForm pf) {
		// 1.�������
		StringBuffer sb = new StringBuffer();
		String qxdm = pf.getQueryFj().substring(0, 2);
		String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
				pf.getQuerySwjgzzjg().indexOf("|"));
		String nsrzt = pf.getQueryQyzt().substring(0,
				pf.getQueryQyzt().indexOf("|"));
		// **************************************
		String fjdm = pf.getQueryFj()
				.substring(0, pf.getQueryFj().indexOf("|"));
		String nd = pf.getNd();
		String swjgzzjgdm_prefix = swjgzzjgdm.substring(0, 2);
		String swjgzzjgdm_postfix = swjgzzjgdm.substring(2);
		String fjdm_prefix = fjdm.substring(0, 2);
		String fjdm_postfix = fjdm.substring(2);
		// **************************************
		String wynsksbSblxWhere = this.getWynsksbSblxWhere(pf.getQuerySblx());
		String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx(), "c.");
		// String sbjkzbDjzclxWhere =
		// this.getSbjkzbDjzclxWhere(pf.getAlldjzclx());
		String sbjkzbSblxWhere = this.getSbjkzbSblxWhere(pf.getQuerySblx());

		// 2.��ʼҵ��
		sb
				.append("select distinct c.jsjdm,c.nsrmc,c.djzclxdm,c.nsrzt,c.jydz,c.jydzlxdm");
		sb
				.append(",(select t.djzclxmc from dmdb.dj_dm_djzclx t where t.djzclxdm=c.djzclxdm) djzclxmc");
		sb
				.append(",(select t.nsrztmc from dmdb.dj_dm_nsrzt t where t.nsrztdm=c.nsrzt) nsrztmc");
		sb.append(" from sbdb.sb_jl_qyjbcwzb a,djdb.dj_jl_jbsj c");
		sb.append(" where a.nd=");
		sb.append(SBStringUtils.getSQLStr(pf.getNd()));
		sb.append(" and ");
		sb.append(djzclxWhere);
		if (!wynsksbSblxWhere.equals("")) {
			sb.append(" and ");
			sb.append(wynsksbSblxWhere + " ");
		}
		if (!"00".equals(qxdm)) {
			sb.append(" and a.qxdm=");
			sb.append(SBStringUtils.getSQLStr(qxdm));
		}
		if (!"00".equals(swjgzzjgdm_postfix)) {
			sb.append(" and a.qxdm=");
			sb.append(SBStringUtils.getSQLStr(swjgzzjgdm_prefix));
			sb.append(" and a.swjgzzjgdm=");
			sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
		}
		sb.append(" and a.jsjdm=c.jsjdm");
		if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
			sb.append(" and a.jsjdm=");
			sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
		}
		if (!QYZT_ALL_DM.equals(nsrzt)) {
			sb.append(" and c.nsrzt=");
			sb.append(SBStringUtils.getSQLStr(nsrzt));
		}
		// 99.����ֵ
		// System.out.println("*****************************");
		// System.out.println("ZeroSBQuery : " + sb.toString());
		// System.out.println("*****************************");
		return sb.toString();
	}

	/**
	 * ��ȡ���걨��ѯSQL
	 * 
	 * @param pf
	 *            ҳ�����
	 * @return QuerySqlFROMAndWhereStr
	 */
	private String getNoSBQuerySqlStr(QyjbcwzbCxActionForm pf) {
		// 1.�������
		StringBuffer sb = new StringBuffer();
		String qxdm = pf.getQueryFj().substring(0, 2);
		String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
				pf.getQuerySwjgzzjg().indexOf("|"));
		String nsrzt = pf.getQueryQyzt().substring(0,
				pf.getQueryQyzt().indexOf("|"));
		// **************************************
		String fjdm = pf.getQueryFj()
				.substring(0, pf.getQueryFj().indexOf("|"));
		String nd = pf.getNd();
		String swjgzzjgdm_prefix = swjgzzjgdm.substring(0, 2);
		String swjgzzjgdm_postfix = swjgzzjgdm.substring(2);
		String fjdm_prefix = fjdm.substring(0, 2);
		String fjdm_postfix = fjdm.substring(2);
		// **************************************
		String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx(), "c.");
		String wynsksbSblxWhere = this.getWynsksbSblxWhere(pf.getQuerySblx());
		String sbjkzbSblxWhere = this.getSbjkzbSblxWhere(pf.getQuerySblx());

		// 2.��ʼҵ��
		sb
				.append("select distinct c.jsjdm,c.nsrmc,c.djzclxdm,c.nsrzt,c.jydz,c.jydzlxdm");
		sb
				.append(",(select t.djzclxmc from dmdb.dj_dm_djzclx t where t.djzclxdm=c.djzclxdm) djzclxmc");
		sb
				.append(",(select t.nsrztmc from dmdb.dj_dm_nsrzt t where t.nsrztdm=c.nsrzt) nsrztmc");
		sb.append(" from djdb.dj_jl_jbsj c");
		sb.append(" where ");
		sb.append(djzclxWhere);
		if (!"00".equals(qxdm)) {
			sb.append(" and c.swjgzzjgdm like '");
			sb.append(qxdm);
			sb.append("%'");
		}
		if (!"00".equals(swjgzzjgdm_postfix)) {
			sb.append(" and c.swjgzzjgdm=");
			sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
		}
		if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
			sb.append(" and c.jsjdm=");
			sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
		}
		if (!QYZT_ALL_DM.equals(nsrzt)) {
			sb.append(" and c.nsrzt=");
			sb.append(SBStringUtils.getSQLStr(nsrzt));
		}
		sb
				.append(" and not exists (select * from sbdb.sb_jl_qyjbcwzb a where a.jsjdm=c.jsjdm and ");
		sb.append("a.nd=");
		sb.append(SBStringUtils.getSQLStr(pf.getNd()));
		if (!wynsksbSblxWhere.equals("")) {
			sb.append(" and ");
			sb.append(wynsksbSblxWhere + " ");
		}
		if (!"00".equals(qxdm)) {
			sb.append(" and a.qxdm=");
			sb.append(SBStringUtils.getSQLStr(qxdm));
		}
		if (!"00".equals(swjgzzjgdm_postfix)) {
			sb.append(" and a.qxdm=");
			sb.append(SBStringUtils.getSQLStr(swjgzzjgdm_prefix));
			sb.append(" and a.swjgzzjgdm=");
			sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
		}
		sb.append(")");
		// 99.����ֵ
		// System.out.println("*****************************");
		// System.out.println("NoSB : " + sb.toString());
		// System.out.println("*****************************");

		return sb.toString();
	}

	/**
	 * ��ȡҳ��
	 * 
	 * @param rsCount
	 *            ��ѯ�����build
	 * @return ҳ��
	 */
	private String getPageTotalCount(int rsCount) {
		// 1.�������
		String countTotal = "0";
		// 2.��ʼҵ��
		debug("���β�ѯ��ʱ�����size=" + rsCount);
		int pageCount = 0;
		if ((rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// 99.����ֵ
		return countTotal;
	}

	/**
	 * ��ȡ��ǰҳ��ʼindex
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ��ʼindex
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.��ʼҵ��
		start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
		debug("���β�ѯ��ʼ����=" + start);
		// 99.����ֵ
		return start;
	}

	/**
	 * ��ȡ��ǰҳ����index
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ����index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.��ʼҵ��
		end = iNextPage * CodeConstant.SD_PG_LENGTH;
		debug("���β�ѯ��������=" + end);
		// 99.����ֵ
		return end;
	}

	/**
	 * ��ȡ��ǰ�����Ƿ��ڵ�ǰ��ҳ��
	 * 
	 * @param start
	 *            ��ʼindex
	 * @param end
	 *            ����index
	 * @param index
	 *            ��ǰindex
	 * @return -1��ʾ�ڵ�ǰ��ҳǰ��0��ʾ�ڵ�ǰ��ҳ�ڣ�1��ʾ�ڵ�ǰ��ҳ֮��
	 */
	private int getInPageData(int start, int end, int index) {
		// 1.�������
		int flag = -1;
		// 2.��ʼҵ��
		if (index >= start || index < end) {
			flag = 0;
		} else if (index >= end) {
			flag = 1;
		}
		// 99.����ֵ
		return flag;
	}

	/**
	 * Debug����
	 * 
	 * @param str
	 *            ��Ϣ
	 */
	private void debug(String str) {
		System.out.println("SMSB DEBUG:" + str);
	}

	/**
	 * ϵͳ�������
	 * 
	 * @param str
	 *            ��Ϣ
	 */
	private void debugCore(String str) {
		System.out.println("SMSB CORE:" + str);
	}

	/**
	 * �õ��걨���ͻ�������
	 * 
	 * @return
	 */
	private List getSblxList(SfDBAccess da) {
		// 1.�������
		String sql = null;
		List returnList = new ArrayList();
		ResultSet rs = null;

		sql = "select sbdm,sbmc from dmdb.gy_dm_fs order by sbdm ";

		returnList.add(new String("0|ȫѡ"));
		// 3.ִ�в�ѯ����������
		try {
			rs = da.querySQL(sql);
			while (rs.next()) {
				String insertString = rs.getString(1) + "|" + rs.getString(2);
				// System.out.println(insertString);
				returnList.add(insertString);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4.����ֵ
		// System.out.println("**********************");
		// for(int i = 0 ; i < returnList.size() ; i++){
		// System.out.println(returnList.get(i));
		// }
		// System.out.println("**********************");

		return returnList;
	}

	/**
	 * �õ��Ǽ�ע�����ͻ�������
	 * 
	 * @return
	 */
	private List getDjzclxList() {
		// 1.�������
		String sql = null;
		List dataList = new ArrayList();
		List tmpList = new ArrayList();
		CodeTableInterface ci = null;
		String value = null;
		// 2.������ݲ�ѯ������List
		// /2.1.ͨ���ǼǵĴ��빤�߻�ȡ������ҵ״̬�б�
		tmpList = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
		// /2.2.�������ݳ�Ϊ���ϸ�ʽ��״̬�б�
		dataList.add("* ��ѯ�����а����ĵǼ�ע������ *");
		// System.out.println("**************************************");
		for (int i = 0; i < tmpList.size(); i++) {
			ci = (CodeTableInterface) tmpList.get(i);
			// System.out.println("WynskcxProcessor : " + ci.getOptionValue() +
			// "|" +
			// ci.getOptionText());
			dataList.add(ci.getOptionValue() + "|" + ci.getOptionText());
		}
		// System.out.println("**************************************");
		// 99.����ֵ
		return dataList;
	}

	/**
	 * �õ�sblx��where��䲿�֣����Ϊ0���򷵻�""����ʾ���Ӵ����������򣬷���������
	 * 
	 * @param sblx
	 * @return
	 */
	/*
	 * private String getSblxWhere(String sblx) { String subString =
	 * sblx.substring(0, 1); if (subString.equals("0")) { return ""; } else {
	 * //System.out.println("***************************");
	 * //System.out.println("sblxWhere : " + "(d.sbdm=" + "'" + subString.trim() + "'
	 * and "+ "d.sbdm=c.sbdm" + ")");
	 * //System.out.println("***************************"); return "(d.sbdm=" +
	 * "'" + subString.trim() + "' and " + "d.sbdm=c.sbdm" + ")"; } }
	 */

	private String getDjzclxWhere(String allDjzclx, String scheme) {
		String djzclxWhere = "";
		ArrayList tempList = new ArrayList();

		// ��allDjzclx�г�ȡ�����еĵǼ����ʹ���ţ����ұ�����list�У�
		for (int i = 0; i < allDjzclx.length(); i++) {
			if (allDjzclx.substring(i, i + 1).equals("|")) {
				String tempString = allDjzclx.substring(i - 3, i);
				tempList.add(tempString);
			}
		}

		// ����list�еĵǼ����ʹ���ţ�����djzclx��where���֡�
		for (int i = 0; i < tempList.size(); i++) {
			if (i == 0) {
				djzclxWhere = "(" + scheme + "djzclxdm=" + "'"
						+ ((String) tempList.get(i)).trim() + "'";
			} else {
				djzclxWhere = djzclxWhere + " or " + scheme + "djzclxdm=" + "'"
						+ ((String) tempList.get(i)).trim() + "'";
			}
		}
		djzclxWhere = djzclxWhere + ")";
		// System.out.println("*******************************");
		// System.out.println("djzclxWhere : " + djzclxWhere);
		// System.out.println("*******************************");
		return djzclxWhere;
	}

	/**
	 * �õ����б�ѡ�ĵǼ�ע������
	 * 
	 * @param allDjzclxList
	 * @param allDjzclx
	 * @return
	 */
	private List getYxDjzclxList(List allDjzclxList, String allDjzclx) {
		List returnList = new ArrayList();
		returnList.add("* ��ѯ�����а����ĵǼ�ע������ *");

		List tempList = new ArrayList();
		// ��allDjzclx�г�ȡ�����еĵǼ����ʹ���ţ����ұ�����list�У�
		for (int i = 0; i < allDjzclx.length(); i++) {
			if (allDjzclx.substring(i, i + 1).equals("|")) {
				String tempString = allDjzclx.substring(i - 3, i);
				tempList.add(tempString);
			}
		}

		for (int i = 1; i < allDjzclxList.size(); i++) {
			String tempString2 = (String) allDjzclxList.get(i);
			boolean bool = false;
			for (int j = 0; j < tempList.size(); j++) {
				if (tempString2.trim().substring(0, 3).equals(
						((String) tempList.get(j)).trim())) {
					bool = true;
					break;
				}
			}
			if (bool) {
				returnList.add(tempString2);
			}
		}

		// System.out.println("WynskcxProcessor
		// 952:********************************");
		// for(int i = 0 ; i < returnList.size() ; i++){
		// System.out.println("bxDjzclxList " + returnList.get(i));
		// }
		// System.out.println("WynskcxProcessor
		// 952:********************************");

		return returnList;
	}

	/**
	 * �õ�û�б�ѡ�ĵǼ�ע������
	 * 
	 * @param allDjzclxList
	 * @param yxDjzclxList
	 * @return
	 */
	private List getNoYxDjzclxList(List allDjzclxList, List yxDjzclxList) {
		List returnList = new ArrayList();
		returnList.add("* ��ѯ�����в������ĵǼ�ע������ *");

		for (int i = 1; i < allDjzclxList.size(); i++) {
			boolean bool = false;
			String temp = (String) allDjzclxList.get(i);
			for (int j = 1; j < yxDjzclxList.size(); j++) {
				if (((String) allDjzclxList.get(i)).trim().substring(0, 3)
						.equals(
								((String) yxDjzclxList.get(j)).trim()
										.substring(0, 3))) {
					bool = true;
					break;
				}
			}
			if (!bool) {
				returnList.add(temp);
			}
		}

		// System.out.println("WynskcxProccessor 985*************************");
		// for(int i = 0 ; i < returnList.size() ; i++){
		// System.out.println("returnList : " + returnList.get(i));
		// }
		// System.out.println("WynskcxProccessor 985*************************");

		return returnList;
	}

	/**
	 * �õ�sbjkzb��djzclxdm���where����
	 * 
	 * @param allDjzclx
	 * @return
	 */
	/*
	 * public String getSbjkzbDjzclxWhere(String allDjzclx) { String djzclxWhere =
	 * ""; ArrayList tempList = new ArrayList();
	 * //��allDjzclx�г�ȡ�����еĵǼ����ʹ���ţ����ұ�����list�У� for (int i = 0; i <
	 * allDjzclx.length(); i++) { if (allDjzclx.substring(i, i + 1).equals("|")) {
	 * String tempString = allDjzclx.substring(i - 3, i);
	 * tempList.add(tempString); } } //����list�еĵǼ����ʹ���ţ�����djzclx��where���֡� for
	 * (int i = 0; i < tempList.size(); i++) { if (i == 0) { djzclxWhere = "(" +
	 * "b.djzclxdm=" + "'" + ( (String) tempList.get(i)).trim() + "'"; } else {
	 * djzclxWhere = djzclxWhere + " or " + "b.djzclxdm=" + "'" + ( (String)
	 * tempList.get(i)).trim() + "'"; } } djzclxWhere = djzclxWhere + ")";
	 * //System.out.println("*******************************");
	 * //System.out.println("djzclxWhere : " + djzclxWhere);
	 * //System.out.println("*******************************"); return
	 * djzclxWhere; }
	 */

	/**
	 * �õ�wynsksb�е�fsdm���where��䲿��
	 * 
	 * @param sblx
	 * @return
	 */
	private String getWynsksbSblxWhere(String sblx) {
		String subString = sblx.substring(0, 1);
		if (subString.equals("0")) {
			return "";
		} else {
			// System.out.println("***************************");
			// System.out.println("sblxWhere : " + "(d.sbdm=" + "'" +
			// subString.trim() + "' and "+ "d.sbdm=c.sbdm" + ")");
			// System.out.println("***************************");
			return " ( a.fsdm=" + "'" + subString.trim() + "' ) ";
		}
	}

	/**
	 * �õ�wynsksb�е�fsdm���where��䲿��
	 * 
	 * @param sblx
	 * @return
	 */
	private String getSbjkzbSblxWhere(String sblx) {
		String subString = sblx.substring(0, 1);
		if (subString.equals("0")) {
			return "";
		} else {
			// System.out.println("***************************");
			// System.out.println("sblxWhere : " + "(d.sbdm=" + "'" +
			// subString.trim() + "' and "+ "d.sbdm=c.sbdm" + ")");
			// System.out.println("***************************");
			return " ( b.fsdm=" + "'" + subString.trim() + "' ) ";
		}
	}

}
