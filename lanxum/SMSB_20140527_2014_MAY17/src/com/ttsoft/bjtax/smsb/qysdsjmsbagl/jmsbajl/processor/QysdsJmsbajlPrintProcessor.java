package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlPrintForm;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QysdsJmsbajlPrintProcessor implements Processor {
	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_PRINTACTION:
			result = doPrint(vo);
			break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}
	
	/**
	 * doPrint��ӡ��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doPrint(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlPrintForm form = (QysdsJmsbajlPrintForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = " select t.sqzt,t.basqbh,t.shsj,t.jmbasxdm,d.nsrmc,j.jmbasxmc," +
					" (SELECT G.BBBZDWMC FROM DMDB.GY_DM_SWJGZZJG G WHERE " +
					" G.CCBS = '1' AND SUBSTR(G.SWJGZZJGDM, 0, 2) = SUBSTR(T.SWJGZZJGDM, 0, 2)) ZZJGMC " +
					" from sfdb.sf_jl_qysdsjmsbajl t,djdb.dj_jl_jbsj d,dmdb.sf_dm_jmbasxdm j " +
					" WHERE t.jsjdm = d.jsjdm AND t.jmbasxdm = j.jmbasxdm " +
					" AND t.basqwsh = '"+form.getBasqwsh()+"'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	
				form.setSqzt(rs.getString("SQZT"));
				form.setBasqbh(rs.getString("BASQBH"));
				form.setNsrmc(rs.getString("NSRMC"));
				form.setJmbasxdm(rs.getString("JMBASXDM"));
				form.setJmbasxmc(rs.getString("JMBASXMC"));
				form.setSwjgzzjgmc(rs.getString("ZZJGMC"));
				String shsj = rs.getString("SHSJ");
				if(!"".equals(shsj)){
					form.setShsj_y(shsj.substring(0, 4));
					form.setShsj_m(shsj.substring(5, 7));
					form.setShsj_d(shsj.substring(8, 10));
				}
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
}
