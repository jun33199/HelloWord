package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZnjwhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class ZnjwhProcessor implements Processor {
	public ZnjwhProcessor() {
	}

	// private static final String YPYS = "Ypys"; //һƱһ˰��������
	// private static final String YPDS = "Ypds"; //һƱ��˰��������
	/**
	 * ʵ��Processor�ӿ�
	 *
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣
	 */

	public Object process(VOPackage vo)
			throws com.ttsoft.framework.exception.BaseException {
		switch (vo.getAction()) {
		case CodeConstant.SMSB_QUERYACTION:
			return doQuery(vo);
		case CodeConstant.SMSB_UPDATEACTION:
			return doCx(vo);

		default:
			return null;
		}

	}

	/**
	 * ��ѯ������������������δ���ɿ��� modified by qianchao 2005.11.2
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ZnjwhActionForm ZnjwhActionForm������
	 * @throws BaseException
	 *             ҵ���쳣
	 */
	private ZnjwhActionForm doQuery(VOPackage vo) throws BaseException {

		// �������ݿ�����
		Connection conn = null;
		SfDBUtils sbDB = null;

		try {
			ZnjwhActionForm form = (ZnjwhActionForm) vo.getData(); // ��ǰ�˻��map����
			// �õ�userdata
			UserData userData = vo.getUserData();
			// �õ����ش���
			String qxdm = InterfaceDj.getQxdm(userData);
			String jsjdm = form.getJsjdm(); // ��ȡ�û��ļ��������
			// ��ȡ��ʼ����
			String qsrq = form.getQsrq();
			// ��ȡ��ֹ����
			String jzrq = form.getJzrq();
			// ��ȡ�걨���
			String sbbh = form.getSbbh();
			// ������ݿ�����
			conn = SfDBResource.getConnection();

	        // ��ѯSQL
	        StringBuffer buffer = new StringBuffer();

	        buffer.append("SELECT A.*, C.SZSMMC FROM SBDB.SB_JL_SBJKZB A, SBDB.SB_JL_SBJKMX B, DMDB.SB_DM_SZSM C ");
	        buffer.append("WHERE A.JSJDM = '" + jsjdm +"' ");
	        buffer.append("AND A.SBRQ >= TO_DATE('" + qsrq +"','YYYYMMDD') ");
	        buffer.append("AND A.SBRQ <= TO_DATE('" + jzrq+" 23:59:59','YYYYMMDD hh24:mi:ss') ");
	        buffer.append("AND A.SKLXDM NOT LIKE '5%' ");
	        buffer.append("AND A.SKLXDM NOT LIKE '6%' ");
	        buffer.append("AND A.SKLXDM NOT LIKE '8%' ");
	        buffer.append("AND A.SKLXDM <> '120' ");
	        buffer.append("AND A.SJLY = '35' ");
	        buffer.append("AND A.JKPZH = B.JKPZH ");
	        buffer.append("AND B.SZSMDM LIKE '%0091' ");
	        buffer.append("AND A.SZDM = C.SZSMDM ");
	        buffer.append("AND A.QXDM = '" + qxdm + "' ");
	        buffer.append("AND A.ZWBS like '0%0'");

	        if(sbbh!=null && !sbbh.trim().equals(""))
	        {
		        buffer.append(" AND A.SBBH = '" + sbbh + "'");

	        }

	        System.out.println("SQL == " + buffer.toString());
	        //��ȡPreparedStatement
	        PreparedStatement pst = conn.prepareStatement(buffer.toString());
	         //ִ��sql
	        ResultSet rs = pst.executeQuery();

	        List datalist = getJksList(rs);

			form.setDataList(datalist);
			return form;
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	}

    /**
     * ��ȡ�����걨��Ϣ
     * @param rs ResultSet
     * @return List
     * @throws Exception
     */
    private List getJksList(ResultSet rs) throws BaseException {
        List retList = new ArrayList();
        try {
            while (rs.next()) {
                HashMap vo = new HashMap();
                // ʵ�ɽ��
                vo.put("sjje",rs.getBigDecimal("sjje"));
                // ˰�ִ���
                vo.put("szdm",rs.getString("szdm"));
                // ˰������
                vo.put("szmc",rs.getString("szsmmc"));
                // �ɿ�ƾ֤��
                vo.put("jkpzh", rs.getString("jkpzh"));
                // �걨���
                vo.put("sbbh",rs.getString("sbbh"));
                // �걨����
                vo.put("sbrq", rs.getDate("sbrq"));
                retList.add(vo);
            }
            return retList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

	/**
	 * �����б��е�˰�ִ���õ�˰������
	 *
	 * @param list
	 *            �걨�ɿ������б� List ���� HashMap�͵���Ϣ
	 * @return ArrayList ���˰�����Ƶ��걨�ɿ������б�
	 * @author Shi Yanfeng 20040112
	 */
	/*
	 * deleted by qianchao 2005.11.3 private ArrayList addSzmc(ArrayList list) {
	 * for (int i = 0; i < list.size(); i++) { HashMap temp = (HashMap)
	 * list.get(i); //ͨ��˰��˰Ŀ�����õ�˰�ִ��� temp.put("szmc",
	 * CodeUtils.getCodeBeanLabel("DM_SZSM", (String) temp.get("szdm"))); }
	 * return list; }
	 */

	/**
	 * ����ָ���ɿ��� ���걨 modified by qianchao 2005.10.27
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ArrayList ���˰�����Ƶ��걨�ɿ������б�
	 * @throws BaseException
	 *             ҵ���쳣
	 */
	private ZhsbjkswhActionForm doCx(VOPackage vo) throws BaseException {
		ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) vo.getData(); // ��ǰ�˻��map����
		// �õ�userdata
		UserData userData = vo.getUserData();
		// �õ����ش���
		String qxdm = InterfaceDj.getQxdm(userData);
		/**
		 * �����־ 20040509 ShiYanfeng
		 */
		TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
		JksUtil jks = new JksUtil();
		Connection conn = null;

		try {

			// start modifying by qianchao 2005.10.27
			// ArrayList ret = (ArrayList) jks.CxJks(form.getJkpzhStr(), qxdm);
			ArrayList ret = null;
			if (form.getJksType() == CodeConstant.PRINT_YPYS) {
				jks.CxJks(form.getJkpzhStr(), qxdm);
			} else {
				conn = SfDBResource.getConnection();
				jks.CxAllJks(form.getSbbh(), qxdm, form.getJsjdm(), conn);
			}
			// form.setCoList(ret);
			// end modifying by qianchao 2005.10.27
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

}
