package com.ttsoft.bjtax.smsb.jmssb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jmssb.web.CjrjyjmsInfo;
import com.ttsoft.bjtax.smsb.jmssb.web.CjrjyjmscxtjForm;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * Title: ������˰�ۺϹ���ϵͳ
 * Description: �����걨-�����걨����¼��-���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�
 * Copyright: Copyright (c) 2005
 * Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjProcessor implements Processor {

	/**
	 * Processor Dispacher
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
			case CodeConstant.SMSB_QUERYACTION:
				return doQuery(vo);
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
		Debug.out("Call CjrjyjmscxtjProcessor_doShow......");
		// �������
		CjrjyjmscxtjForm cForm = null;
		UserData userData = null;
		SfDBAccess da = null;
		Connection conn = null;
		// ��ʼ��VO���ݶ���
		try {
			userData = vo.getUserData();
			cForm = (CjrjyjmscxtjForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		
		// ��ʼҵ��
		try {
			// /��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /����userData���û���������־���֯���������ֵ�б�
			cForm.setZgswjgList(this.getZgswjgList(userData, da));
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return cForm;
	}
	
	/**
	 * ��ѯ����
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		Debug.out("Call CjrjyjmscxtjProcessor_doQuery......");
		// �������
		CjrjyjmscxtjForm cForm = null;
		UserData userData = null;
		SfDBAccess da = null;
		Connection conn = null;
		// ���������������ݼ�
		List allDataList = new ArrayList();
		// ��ȡ�ϼ����ݼ�
		List totalList = new ArrayList();
		// ��ҳ��ǰ��ʾ�����ݼ�
		List showDataList = new ArrayList();
		
		// ��ʼ��VO���ݶ���
		try {
			userData = vo.getUserData();
			cForm = (CjrjyjmscxtjForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		
		// ��ʼҵ��
		try {
			// ��ʼ������
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /����userData���û���������־���֯���������ֵ�б�
			cForm.setZgswjgList(this.getZgswjgList(userData, da));
			// ִ�в�ѯ
			allDataList = this.queryCjrjms(userData, cForm, da, "list");
			if(allDataList.size() > 0) {
				totalList = this.queryCjrjms(userData, cForm, da, "total");
				// ��ȡ��ҳ������
				cForm.setTotalpage(this.getPageTotalCount(allDataList.size()));
				// �����ѯ���
				showDataList = this.getPageDataList(allDataList, cForm);
				
				// ����ҳ�����
				cForm.setNjzyysxeTotal(totalList.get(0).toString());
				cForm.setSjjzyyseTotal(totalList.get(1).toString());
				cForm.setAzcjzgrsTotal(totalList.get(2).toString());
				cForm.setDataList(showDataList);
				cForm.setMessage("");
			} else {
				cForm.setNjzyysxeTotal("0.00");
				cForm.setSjjzyyseTotal("0.00");
				cForm.setAzcjzgrsTotal("0");
				cForm.setMessage("û�������ѯ���������ݣ�");
			}
			// �������
			allDataList = null;
			totalList = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return cForm;
	}
	
	/**
	 * ��ȡ���з���������˰����
	 * 
	 * @param userData
	 *            �û�����
	 * @param da
	 *            ���ݿ��������
	 * @return ����������˰����List
	 * @throws BaseException
	 *             �쳣
	 */
	private List getZgswjgList(UserData ud, SfDBAccess da) throws BaseException {
		// �������
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		String fjdm = ud.getSsdwdm().substring(0, 2) + "00";
		// �����û��������ʹ��ĳһ��sql
		String yhjb = ud.getYhjb();
		if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { // ˰������
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and swjgzzjgdm="
					+ SBStringUtils.getSQLStr(swjgzzjgdm);
		} else if (CodeConstants.JBDM_FJ.equals(yhjb)) { // �־ּ�
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and qxfjdm="
					+ SBStringUtils.getSQLStr(fjdm);
			tmpList.add(fjdm + "|ȫ��");
		} else if (CodeConstants.JBDM_SJ.equals(yhjb)) { // �оּ�
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' order by swjgzzjgdm asc";
			tmpList.add("0000|ȫ��");
		}
		// ִ�в�ѯ����������
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
	 * ��ȡҪ��ѯ��SQL���
	 * 
	 * @param cForm
	 * @param queryType ��ѯ���("list"-->��ѯ�������ݣ�"total"-->��ѯ�ܼ�����)
	 * @return
	 */
	private String getQuerySql(CjrjyjmscxtjForm cForm, String queryType) {
		Debug.out("Call CjrjyjmscxtjProcessor_getQuerySql......");
		// �������
		StringBuffer sqlString = new StringBuffer();
		String queryJsjdm = cForm.getQueryJsjdm();
		String queryQymc = cForm.getQueryQymc();
		String queryZgswjgdm = cForm.getQueryZgswjgdm().substring(0,
				cForm.getQueryZgswjgdm().indexOf("|"));
		String queryCxqsrq = cForm.getQueryCxqsrq();
		String queryCxjzrq = cForm.getQueryCxjzrq();
		// �����ֹ����Ϊ�գ���Ϊ��ǰʱ��
		if("".equals(SBStringUtils.killNull(queryCxjzrq))) {
			queryCxjzrq = (new SimpleDateFormat("yyyyMMdd")).format(new Date()).toString();
		}
		
		// ������ѯ���
		if(queryType == "list") {
			sqlString.append("SELECT (SELECT A.JSJDM FROM DJDB.DJ_JL_JBSJ A WHERE A.JSJDM = B.JSJDM) JSJDM, (SELECT A.NSRMC FROM DJDB.DJ_JL_JBSJ A WHERE A.JSJDM = B.JSJDM) NSRMC, C.SQSPBH, B.NJYYSXE, TO_CHAR(B.JMKSRQ, 'YYYY-MM-DD'), TO_CHAR(B.JMJZRQ, 'YYYY-MM-DD'), C.BYSJJZYYSYE, C.CJRZGRS");
		} else if(queryType == "total") {
			sqlString.append("SELECT SUM(NVL(B.NJYYSXE,0)), SUM(NVL(C.BYSJJZYYSYE,0)), SUM(C.CJRZGRS)");
		}
		sqlString.append(" FROM SPDB.SP_JL_FLQYMYYS B, SBDB.SB_JL_JM_AZCJRJMSB C ");
		sqlString.append(" WHERE B.JSJDM = C.JSJDM AND B.SWJGZZJGDM = C.SWJGZZJGDM AND B.SQSPBH = C.SQSPBH ");
		sqlString.append(" AND C.CJRQ >= TO_DATE('" + queryCxqsrq + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') AND C.CJRQ <= TO_DATE('" + queryCxjzrq + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		
		// ����¼��Ĳ�ѯ����
		if (!"".equals(SBStringUtils.killNull(queryJsjdm))) {
			sqlString.append(" AND B.JSJDM = ");
			sqlString.append(SBStringUtils.getSQLStr(queryJsjdm));
		}
		if (!"".equals(SBStringUtils.killNull(queryQymc))) {
			sqlString.append(" AND B.JSJDM IN (SELECT D.JSJDM FROM DJDB.DJ_JL_JBSJ D WHERE D.NSRMC = ");
			sqlString.append(SBStringUtils.getSQLStr(queryQymc));
			sqlString.append(")");
		}
		if (!"0000".equals(SBStringUtils.killNull(queryZgswjgdm))) {
			sqlString.append(" AND B.SWJGZZJGDM = ");
			sqlString.append(SBStringUtils.getSQLStr(queryZgswjgdm));
		}
		sqlString.append(" ORDER BY C.CJRQ DESC, C.JSJDM");
		return sqlString.toString();
	}
	
	/**
	 * ��ѯ���òм��˾�ҵ��ҵӪҵ˰����˰���
	 * 
	 * @param userData
	 * @param cForm
	 * @param da
	 * @param queryType ��ѯ���("list"-->��ѯ�������ݣ�"total"-->��ѯ�ܼ�����)
	 * @return
	 * @throws BaseException
	 */
	private List queryCjrjms(UserData userData, CjrjyjmscxtjForm cForm, SfDBAccess da, String queryType) 
		throws BaseException {
		Debug.out("Call CjrjyjmscxtjProcessor_queryCjrjms......");
		// �������
		String sql = null;
		List tmpList = new ArrayList();
		ResultSet rs = null;
		CjrjyjmsInfo cjrjyInfo = null;
		// ����������ѯ��sql
		sql = this.getQuerySql(cForm, queryType);
		try {
			// ִ��SQL
			rs = da.querySQL(sql);
			if(queryType == "list") {
				int count = 1;
				while(rs.next()) {
					cjrjyInfo = new CjrjyjmsInfo();
					cjrjyInfo.setIndexId(count++);
					cjrjyInfo.setJsjdm(rs.getString(1));
					cjrjyInfo.setQymc(rs.getString(2));
					cjrjyInfo.setSpbbh(rs.getString(3));
					cjrjyInfo.setNjzyysxe(rs.getString(4));
					cjrjyInfo.setJzksrq(rs.getString(5));
					cjrjyInfo.setJzjzrq(rs.getString(6));
					cjrjyInfo.setSjjzyyse(rs.getString(7));
					cjrjyInfo.setAzcjzgrs(rs.getString(8));
					tmpList.add(cjrjyInfo);
				}
			} else if(queryType == "total") {
				while(rs.next()) {
					tmpList.add(rs.getString(1));
					tmpList.add(rs.getString(2));
					tmpList.add(rs.getString(3));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		return tmpList;
	}

	/**
	 * ��ȡҳ��
	 * 
	 * @param rsCount
	 *            ��ѯ�����build
	 * @return ҳ��
	 */
	private String getPageTotalCount(int rsCount) {
		// �������
		String countTotal = "0";
		// ��ʼҵ��
		int pageCount = 0;
		if ((rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// ����ֵ
		return countTotal;
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
	private List getPageDataList(List tmpList, CjrjyjmscxtjForm pf) {
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
		// 99.����ֵ
		return end;
	}
	
}
