package com.ttsoft.bjtax.shenbao.service.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.jiekou.InterFaceCoding;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.service.constant.SkhConstant;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbResult;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzMx;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzsmmx;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.TypeChecker;
import java.sql.*;

/**
 * <p>
 * Title: ˰���нӿ�ҵ����
 * </p>
 * <p>
 * Description:
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

public class SkhInterFaceProcessor extends InterFaceProcessor {

	/**
	 * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ��� ������˰Ʊ�Ժ���Ҫ�������־����Ϊ���һλΪ9���Է�ֹ������ɾ�������
	 *
	 * @param yhsbInfo
	 *            �걨����
	 * @param hjzse
	 *            �걨�ϼ���Ӧ��˰��
	 * @return ����ֵ����YhsbResult����
	 * @throws java.lang.Exception
	 *             Ӧ���쳣
	 */
	private static String NULL_ZERO = "";
        protected static String getYpysJksQueryOrderByPostfixFoSKH() {
          StringBuffer sb = new StringBuffer();
          sb.append(" ORDER BY a.sklxdm,a.zsswjgzzjgdm,a.ysjcdm,a.szdm,a.yskmdm,a.skssksrq,a.skssjsrq,a.xjrq"); //���н�������
          return sb.toString();
   }
	public static YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo,
			double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.�������
		String jsjdm = null;
		YhsbSzMx yhsbSzmx = null;
		YhsbSzsmmx yhsbSzsmmx = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		// 1.��ڲ���У��
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null) {
			throw new Exception("���걨���нɿ��걨�����ݷǷ�,����!");
		}
		// 2.��ʼ��
		jsjdm = yhsbInfo.getJsjdm();
		yhsbResult.setJsjdm(jsjdm);
		// 3.ҵ�����
		try {
			// /3.-1.��ȡ��Դ
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.0.���õǼǽӿڻ�ȡ��˰�˻�������
			ServiceProxy serviceProxy = new ServiceProxy();
			String swjgzzjgdm = "";
			String qxdm = "";
			String djzclxdm = "";
			String gjbzhydm = "";
			String lsgxdm = "";
			String nsrmc = "";
			String jydzlxdm = "";
			//
			TypeChecker tc = new TypeChecker();
			String flag = TypeChecker.isQyyh(jsjdm);
			//
			if (ApplicationConstant.JSJDM_LX_QY.equals(flag)) { // ��ҵ�û�
				Map map = serviceProxy.getDjInfo(jsjdm);
				SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
				if (swdjjbsj == null) {
					throw ExceptionUtil
							.getBaseException(new ApplicationException(
									"�޷��õ�����˰�˵ĵǼ����ݣ�" + jsjdm));
				}
				swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); // ˰�����
				qxdm = swjgzzjgdm.substring(0, 2);
				djzclxdm = swdjjbsj.getDjzclxdm();
				gjbzhydm = swdjjbsj.getGjbzhydm();
				lsgxdm = swdjjbsj.getLsgxdm();
				nsrmc = swdjjbsj.getNsrmc();
				jydzlxdm = swdjjbsj.getJydzlxdm();
				// /3.1.�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
				if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
						swdjjbsj)
						|| !FriendHelper.checkNsrztForJks(
								CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
					// ��������
					throw ExceptionUtil
							.getBaseException(new ApplicationException(
									"����˰��״̬��Ϊ�������������ɽɿ��飡" + jsjdm));
				}
			} else if (ApplicationConstant.JSJDM_LX_ZRR.equals(flag)) { // ��Ȼ����
				Map map = serviceProxy.getZrrDjInfo(jsjdm);
				ZRRJBSJ zrrJbsj = (ZRRJBSJ) map.get("zrrjbsj");
				if (zrrJbsj == null) {
					throw new Exception("�����ڸ���Ȼ����Ϣ");
				}

				swjgzzjgdm = zrrJbsj.getSwjgzzjgdm(); // ˰�����
				qxdm = swjgzzjgdm.substring(0, 2);
				if (zrrJbsj.getGjdm().equals("CHN")) {
					djzclxdm = CodeConstant.DEFAULT_CHINA_ZRR_DJZCLXDM; // �Ǽ�ע�����ʹ���
				} else {
					djzclxdm = CodeConstant.DEFAULT_FOREIGN_ZRR_DJZCLXDM; // �Ǽ�ע�����ʹ���
				}
				gjbzhydm = CodeConstant.DEFAULT_ZRR_GJBZHYDM;
				lsgxdm = "";
				nsrmc = zrrJbsj.getNsrmc();
				jydzlxdm = zrrJbsj.getZzdh();

			} else {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"����˰���޷��ж��Ǽ����ͣ�" + jsjdm));
			}
			// /3.1.5 �ж��Ƿ����� add by hazhengze 2006-01-22 18:00
			if (!InterFaceCoding.getInstance().isLw(swjgzzjgdm,
					yhsbInfo.getYhdm(), conn)) {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"����˰��ϵ������������˰�˻������ϵ���������У�"));
			}

			// /3.2.�ж���˰��˰������Ƿ����
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			yhsbResult.setQxdm(qxdm);
			log("˰�������ջ������ش���: ======= " + yhsbInfo.getQxdm());
			log("�Ǽ����ջ��ش���: ======= " + swjgzzjgdm);
			// /3.3.����׼������
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String nd = new SimpleDateFormat("yyyy").format(now);
			String sklxdm = yhsbInfo.getSklxdm();
			if (sklxdm == null || sklxdm.length() <= 0) {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"˰�����ʹ��벻��Ϊ�գ�"));
			}
			String sjly = ApplicationConstant.SJLY_YHSB_YHLR;
			String sbfsdm = ApplicationConstant.FSDM_YHSB_YHLR;
			String bz = yhsbInfo.getBz();
			String yhdm = yhsbInfo.getYhdm();
			String yhmc = yhsbInfo.getYhmc();
			;
			String zh = yhsbInfo.getZh();
			String lrr = yhsbInfo.getLrr();
			BigDecimal sjjehj = new BigDecimal(hjzse);
			// /3.4.���ɽɿ�����
			List sbjkmxList = new ArrayList();
			// /3.4.0.���ɽɿ�������д�걨��ϸ��Ϣ
			for (int i = 0; i < yhsbInfo.getSzList().size(); i++) {
				yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(i);
				for (int j = 0; j < yhsbSzmx.getSzsmList().size(); j++) {
					yhsbSzsmmx = (YhsbSzsmmx) yhsbSzmx.getSzsmList().get(j);
					String szsmdm = yhsbSzsmmx.getSzsmdm();
					if (szsmdm == null) {
						throw ExceptionUtil
								.getBaseException(new ApplicationException(
										"����Ϊ�գ�"));
					}
					BigDecimal sjje = yhsbSzsmmx.getSjje();
					if (sjje == null) {
						throw ExceptionUtil
								.getBaseException(new ApplicationException(
										"����Ϊ�գ�"));
					}
					BigDecimal jsje = yhsbSzsmmx.getJsje();
					BigDecimal kssl = yhsbSzsmmx.getKssl();
					BigDecimal sl = yhsbSzsmmx.getSl();
					//
					Szsm szsm = getSzsm(szsmdm);
					// �����걨�ɿ���ϸ
					Sbjkmx sbjkmx = new Sbjkmx();
					sbjkmx.setSzsmdm(szsmdm);
					sbjkmx.setZqlxdm(szsm.getZqlxdm()); // �������ʹ���
					sbjkmx.setJsjdm(jsjdm);
					sbjkmx.setSjse(sjje);
					sbjkmx.setRkje(sjje);
					sbjkmx.setKssl(kssl);
					sbjkmx.setSl(sl);
					sbjkmx.setJsje(jsje);
					sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
					sbjkmx.setNd(nd);
					sbjkmx.setCjrq(now); // ��������
					sbjkmx.setLrrq(now); // ¼������
					sbjkmx.setQxdm(qxdm); // ���ش���
					// ˰��������ʼ����
					if (yhsbSzmx.getSkssksrq() != null) {
						sbjkmx.setSkssksrq(yhsbSzmx.getSkssksrq());
					}
					// ˰��������������
					if (yhsbSzmx.getSkssjsrq() != null) {
						sbjkmx.setSkssjsrq(yhsbSzmx.getSkssjsrq());
					}
					sbjkmxList.add(sbjkmx);
				}
			}
			// /3.4.0.���ɽɿ�������д�걨������
			Sbjkzb sbjkzb = new Sbjkzb();
			sbjkzb.setJsjdm(jsjdm);
			// ��˰��Ǽǵõ�������
			sbjkzb.setDjzclxdm(djzclxdm);
			sbjkzb.setGjbzhydm(gjbzhydm);
			sbjkzb.setLsgxdm(lsgxdm);
			sbjkzb.setNsrmc(nsrmc);
			sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
			sbjkzb.setLrrq(now);
			sbjkzb.setCjrq(now);
			sbjkzb.setFsdm(sbfsdm); // ���շ�ʽΪ�����걨�ɿ�,����ֵΪ7
			if (lrr != null && (!"".equals(lrr))) {
				sbjkzb.setLrr(lrr);
			} else {
				sbjkzb.setLrr(jsjdm);
			}
			sbjkzb.setRkje(sjjehj);
			sbjkzb.setSbrq(TinyTools.second2Day(now)); // �걨����
			sbjkzb.setZyrq(TinyTools.second2Day(now)); // ��ҳ���ڣ���ʼΪ�걨����
			sbjkzb.setSjje(sjjehj);
			sbjkzb.setSklxdm(sklxdm);
			sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
			sbjkzb.setNd(nd);
			sbjkzb.setBz(bz);
			sbjkzb.setSjly(sjly); // ������Դ
			sbjkzb.setQxdm(qxdm); // ���ش���
			sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); // Ĭ��Ϊ�����걨��
			sbjkzb.setJydzlxdm(jydzlxdm); // ��ϵ�绰

			// �޽�����
			if (yhsbInfo.getXjrq() != null) {
				sbjkzb.setXjrq(yhsbInfo.getXjrq());
			}

			if (yhsbInfo.getYhdm() != null && yhsbInfo.getYhmc() != null) {
				sbjkzb.setYhdm(yhsbInfo.getYhdm()); // ���д���
				sbjkzb.setYhmc(yhsbInfo.getYhmc()); // ��������
				sbjkzb.setZh(yhsbInfo.getZh()); // �����˺�
			} else {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"������Ϣ����Ϊ�գ�"));
			}
			// �������ݶ���
			DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
			declareInfor.setIsReturnPaymentInfo(true); // ��������
			declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM); // Ĭ��ΪһƱһ˰

			// �����ɵ����ݲ������ݱ��в�����
			ZhsbProcessor zhsbPro = new ZhsbProcessor();
			String sbbh;
			try {
				sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			// �����걨�ɿ�����
			List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);
			// /3.5.�����걨�������һƱ��˰�ɿ���,���һ�д˰Ʊ���뵽���ݿ�
			// //3.5.0.��ȡһƱ��˰�ɿ���
			rtnList = getYpdsJks(sbbh);
			// //3.5.1.����˰Ʊ���벢��д�����ݿ�
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.printContent();
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(",yhmc=(select yhmc from dmdb.gy_dm_yh where yhdm = "
						+ StringUtils.getSQLStr(yhsbInfo.getYhdm()) + ")");
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND swjgzzjgdm=");
				sb.append(StringUtils.getSQLStr(swjgzzjgdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(jks.getSbbh()));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		yhsbResult.setRnObjectClassName("java.util.List");
		yhsbResult.setRnObject(rtnList);
		// 99.
		return yhsbResult;
	}

	/**
	 * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���
	 *
	 * @param yhsbInfo
	 *            �걨����
	 * @param hjzse
	 *            �걨�ϼ���Ӧ��˰��
	 * @return ����ֵ����YhsbResult����
	 * @throws java.lang.Exception
	 *             Ӧ���쳣
	 */
	public static YhsbResult generateYpdsJksWithSbInfo(YhsbInfo yhsbInfo,
			double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.�������
		String jsjdm = null;
		String swjgzzjgdm = null;
		String qxdm = null;
		String sbbh = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		// 1.��ڲ���У��
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null
				|| yhsbInfo.getSbbh() == null || yhsbInfo.getQxdm() == null) {
			throw new Exception("���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���ӿڵ�����ڲ����Ƿ�.");
		}
		// 2.��ʼ��
		jsjdm = yhsbInfo.getJsjdm();
		yhsbResult.setJsjdm(jsjdm);
		sbbh = yhsbInfo.getSbbh();
		// 3.ҵ�����
		try {
			// /3.0.��ȡ��Դ
			conn = db.getConnection();
			st = conn.createStatement();

			// /3.1.�����걨�������һƱ��˰�ɿ���
			rtnList = getYpdsJks(sbbh);
			// 3.1.1.��ȡ����
			if (rtnList.size() == 0) {
				throw new Exception("���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���ӿ��޷����ɽɿ�������.");
			}
			// 3.1.2.��ȡ����
			swjgzzjgdm = ((YPDSJKS) rtnList.get(0)).getSwjgzzjgdm();
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			qxdm = swjgzzjgdm.substring(0, 2);
			yhsbResult.setQxdm(qxdm);
			//
			yhsbResult.setRnObjectClassName("java.util.List");
			yhsbResult.setRnObject(rtnList);
			// /3.2.���¿����걨����
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(sbbh));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

	/**
	 * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���,��ѡ˰��
	 *
	 * @param yhsbInfo
	 *            �걨����,Ӧ������ѡ�������˰��˰Ŀ��Ϣ
	 * @param hjzse
	 *            �걨�ϼ���Ӧ��˰��
	 * @return ����ֵ����YhsbResult����
	 * @throws java.lang.Exception
	 *             Ӧ���쳣
	 */
	public static YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(
			YhsbInfo yhsbInfo, double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.�������
		String jsjdm = null;
		String sbbh = null;
		String swjgzzjgdm = null;
		String qxdm = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		DeclareInfor declareInfo = null;
		// 1.��ڲ���У��
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null
				|| yhsbInfo.getSbbh() == null || yhsbInfo.getQxdm() == null
				|| (!yhsbInfo.checkValidation())) {
			throw new Exception("���걨���нɿ��걨���ݷǷ�,����");
		}
		// 2.��ʼ��
		jsjdm = yhsbInfo.getJsjdm();
		sbbh = yhsbInfo.getSbbh();
		// 3.ҵ�����
		try {
			// /3.0.��ȡ��Դ
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.1.�����걨��źͼ������������һƱ��˰�ɿ���
			// //3.1.0.�����걨��Ų�ѯ����
			List declareInfos = queryDataBySBBH(jsjdm, sbbh, conn);
			// //3.1.1.
			swjgzzjgdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getSwjgzzjgdm();
			qxdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb().getQxdm();
			yhsbResult.setJsjdm(jsjdm);
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			yhsbResult.setQxdm(qxdm);

			/** *************modify by hezy ����һ���걨��˰�����һ�ν��ɵ�����*************** */
			if (getSzNumbyDeclareInfo(declareInfos) > yhsbInfo.getSzList()
					.size()) {
				throw new Exception("һ���걨��˰�����һ���Խ���,���ִܷν�");
			}

			// //3.1.1.������ڵ�ѡ��˰�ֹ�������
			YhsbSzMx yhsbSzmx = null;
			for (int i = 0; i < declareInfos.size(); i++) {
				declareInfo = (DeclareInfor) declareInfos.get(i);
				boolean delFlag = true;
				for (int j = 0; j < yhsbInfo.getSzList().size(); j++) {
					yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(j);
					if (declareInfo.getSbjkzb().getSzdm().equals(
							yhsbSzmx.getSzdm())) {
						delFlag = false;
						break;
					}
				}
				if (delFlag) {
					declareInfos.remove(i--);
				}
			}
			// //3.1.2.������ڵ�ѡ��˰�ֽ���У��
                        double szje = 0.00D;
			for (int j = 0; j < yhsbInfo.getSzList().size(); j++) {
				yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(j);
//				double sjzse = getSzje(declareInfos, yhsbSzmx.getSzdm());
				szje += yhsbSzmx.getSjje().doubleValue();

				log("szje=" + szje);
			}
                        double sjzse = getHjSzje(declareInfos);
                        double res = szje - sjzse;
                        if ((new BigDecimal(res)).abs().doubleValue() > ApplicationConstant.JE_ERROR_OFFSET) {
                                yhsbSzmx.printContent();
                                throw new Exception("�ɿ���Ϳ��н���!");
                        }
			/** *************modify by hezy �ж�˰Ʊ״̬*************** */
			String zwbs = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getZwbs();
			String zh = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getZh();
			String yhdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getYhdm();
			if (zh == null) {
				zh = "";
			}
			if (zwbs != null && !zwbs.trim().equals("")) {
				if (zwbs.charAt(0) != '0') {
					throw new Exception("���걨�ѽɿ�!");

				} else if (zwbs.substring(17, 20).equals(
						ApplicationConstant.ZWBS_YHKK_SUCCESS)) {
					throw new Exception("���걨�ѽɿ�!");
				} else if (zwbs.substring(19, 20).equals(
						ApplicationConstant.ZWBS_YHKK_LOCK)) {

					if (!yhsbInfo.getYhdm().equals(yhdm)) {
						throw new Exception("���걨�����������н��нɿ���!");
					}
					if (!zh.trim().equals(yhsbInfo.getZh().trim())) {
						if (yhsbInfo.getZh() != null
								&& !yhsbInfo.getZh().trim().equals("")
								&& !zh.trim().equals("")) {
							throw new Exception("���������걨������˺���˰Ʊ�е��˺Ų���!");
						}
					}

					// ��������˰Ʊ����
					sb = new StringBuffer();
					sb.append(getYpysJksQueryPrefix());
					sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
					sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
					sb.append(" and a.jsjdm=");
					sb.append(StringUtils.getSQLStr(jsjdm));
					sb.append(" and a.sbbh=");
					sb.append(StringUtils.getSQLStr(sbbh));
					sb.append(" AND a.zwbs LIKE '0%9'");
					sb.append(" AND a.jkpzh in (");
					for (int i = 0; i < declareInfos.size(); i++) {
						declareInfo = (DeclareInfor) declareInfos.get(i);
						if (i != 0) {
							sb.append(",");
						}
						sb.append(StringUtils.getSQLStr(declareInfo.getSbjkzb()
								.getJkpzh()));
					}
					sb.append(")");
					sb.append(getYpysJksQueryOrderByPostfixFoSKH());
					sql = sb.toString();
					// /3.2.���в�ѯ��ȡ�����
					InterFaceProcessor.log(sql);
					rs = st.executeQuery(sql);

					List tmpList = new ArrayList();
					Map jksmx = null;
					while (rs.next()) {
						jksmx = new HashMap();
						for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
							jksmx
									.put(
											ApplicationConstant.JKS_YPYS_MX_KEYS[i],
											rs
													.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
						}
						InterFaceProcessor.log("��һ�ֲ�ѯ��һƱһ˰��ϸ==" + jksmx);
						tmpList.add(jksmx);
					}
					rs.close();
					// /3.4.�����ӷ������з�Ʊ
					rtnList = InterFaceProcessor.getYpdsJksForSkh(tmpList);

					yhsbResult.setRnObjectClassName("java.util.List");
					yhsbResult.setRnObject(rtnList);

					/**/
					String sphm = declareInfo.getSbjkzb().getSphm();
					for (int i = 0; i < rtnList.size(); i++) {
						jks = (YPDSJKS) rtnList.get(i);
						jks.setSphm(sphm);
					}

					// 98.
					yhsbResult
							.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
									+ SkhConstant.RESULT_CODE_MID_DEFAULT
									+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
					yhsbResult
							.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
					// 99.
					return yhsbResult;

				}
			} else {
				throw new Exception("�걨���ݴ��ڴ���!");
			}

			// //3.1.3.���ݹ��˺����������һƱ��˰�ɿ���
			sb = new StringBuffer();
			sb.append(getYpysJksQueryPrefix());
			sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
			sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
			sb.append(" and a.jsjdm=");
			sb.append(StringUtils.getSQLStr(jsjdm));
			sb.append(" and a.sbbh=");
			sb.append(StringUtils.getSQLStr(sbbh));
			sb.append(" AND a.zwbs LIKE '0%0'");
			sb.append(" AND a.jkpzh in (");
			for (int i = 0; i < declareInfos.size(); i++) {
				declareInfo = (DeclareInfor) declareInfos.get(i);
				if (i != 0) {
					sb.append(",");
				}
				sb.append(StringUtils.getSQLStr(declareInfo.getSbjkzb()
						.getJkpzh()));
			}
			sb.append(")");
			sb.append(getYpysJksQueryOrderByPostfix());
			sql = sb.toString();
			// /3.2.���в�ѯ��ȡ�����
			InterFaceProcessor.log(sql);
			rs = st.executeQuery(sql);
			// /3.3.�������������ȡ��������
			List tmpList = new ArrayList();
			Map jksmx = null;
			while (rs.next()) {
				jksmx = new HashMap();
				for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
					jksmx
							.put(
									ApplicationConstant.JKS_YPYS_MX_KEYS[i],
									rs
											.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
				}
				InterFaceProcessor.log("��һ�ֲ�ѯ��һƱһ˰��ϸ==" + jksmx);
				tmpList.add(jksmx);
			}
			rs.close();
			// /3.4.�����ӷ������з�Ʊ
			rtnList = InterFaceProcessor.getYpdsJks(tmpList);
			//
			yhsbResult.setRnObjectClassName("java.util.List");
			yhsbResult.setRnObject(rtnList);
			// /3.2.���¿����걨����
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(",yhdm=");
				sb.append(StringUtils.getSQLStr(yhsbInfo.getYhdm()));
				/** *modified by liutg 20051230** */
				sb.append(",yhmc=(select yhmc from dmdb.gy_dm_yh where yhdm = "
						+ StringUtils.getSQLStr(yhsbInfo.getYhdm()) + ")");
				// sb.append(StringUtils.getSQLStr(yhsbInfo.getYhmc()));
				sb.append(",zh=");
				sb.append(StringUtils.getSQLStr(yhsbInfo.getZh()));
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(sbbh));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

	private static int getSzNumbyDeclareInfo(List declareInfos) {
		DeclareInfor declareInfo = null;
		java.util.Set szSet = new java.util.HashSet();
		for (int i = 0; i < declareInfos.size(); i++) {
			declareInfo = (DeclareInfor) declareInfos.get(i);
			String szdm = declareInfo.getSbjkzb().getSzdm();
			szSet.add(szdm);
		}

		return szSet.size();
	}

	private static double getSzje(List declareInfos, String szdm)
			throws Exception {
		// 0.�������
		double hjzje = 0.00;
		DeclareInfor declareInfo = null;
		// 1.ҵ�����
		for (int i = 0; i < declareInfos.size(); i++) {
			declareInfo = (DeclareInfor) declareInfos.get(i);
			if (szdm.equals(declareInfo.getSbjkzb().getSzdm())) {
				hjzje += declareInfo.getSbjkzb().getSjje().doubleValue();
			}
		}
		// 2.�Ϸ��Լ��
		if (hjzje == 0.00) {
			throw new Exception("����˰�ֻ�ȡͬһ���걨�ڵ�˰���ܽ�����!" + szdm);
		}
		// 99.����ֵ
		return hjzje;

	}

        private static double getHjSzje(List declareInfos)
                        throws Exception {
                // 0.�������
                double hjzje = 0.00;
                DeclareInfor declareInfo = null;
                // 1.ҵ�����
                for (int i = 0; i < declareInfos.size(); i++) {
                        declareInfo = (DeclareInfor) declareInfos.get(i);
                                hjzje += declareInfo.getSbjkzb().getSjje().doubleValue();
                }
                // 2.�Ϸ��Լ��
                if (hjzje == 0.00) {
                        throw new Exception("��ȡͬһ���걨�ڵ�˰���ܽ�����!");
                }
                // 99.����ֵ
                return hjzje;

        }

	/**
	 * ���걨�����걨���нɿ�ۿ��� �ж��Ƿ������걨ͨ��������걨���ݵ�������Դ�����ж���������ԴΪ91�������걨�ɿ
	 * �������Ϊ���걨�ɿ���ڳɹ�����ʧ�ܣ�����Ҫ�޶��걨�ɿ����ݵ������־Ϊ�ɿ�״̬����
	 * �ſ��Խ������һ����״̬�޸ġ������ʧ�ܵ�������������걨��������Ҫ�ع�SPHM��ZWBS�ֶ�
	 *
	 * @param kkFlag
	 *            �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
	 * @param ypdsJkss
	 *            һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
	 * @return ����ֵ����YhsbResult����
	 * @throws java.lang.Exception
	 *             Ӧ���쳣
	 */
	public static YhsbResult WsbYhJkKkResult(boolean kkFlag, List ypdsJkss)
			throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.�������
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		String jsjdm = null;
		// String _swjgzzjgdm = null;
		String sbbh = null;
		String sphm = null;
		String jksj = null;
		Map tmpMap = null;
		List tmpList = null;
		// 1.��ڲ������
		if (ypdsJkss == null || ypdsJkss.size() == 0) {
			throw new Exception("���걨���нɿ�ۿ����ӿڵ�����ڲ����Ƿ�.");
		}
		// 2.��ʼ��
		// 3.ҵ�񷽷�
		try {
			// /3.-2.��ȡ��Դ
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.-1.�ж��ñ����������걨�������걨
			// //3.-1.0.ִ�в�ѯ��ȡ���
			sb = new StringBuffer();
			jsjdm = ((YPDSJKS) ypdsJkss.get(0)).getJsjdm();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sphm IN (");
			for (int i = 0; i < ypdsJkss.size(); i++) {
				jks = (YPDSJKS) ypdsJkss.get(i);
				if (i != 0) {
					sb.append(",");
				}
				sb.append(StringUtils.getSQLStr(jks.getSphm()));
			}
			sb.append(")");
			sb.append(" AND jsjdm=");
			sb.append(StringUtils.getSQLStr(jsjdm));
			sql = sb.toString();
			log(sql);
			rs = st.executeQuery(sql);
			// //3.-1.1.ֵ����ת��
			Sbjkzb sbjkzb = null;
			List zbList = new ArrayList();
			while (rs.next()) {
				sbjkzb = new Sbjkzb();
				sbjkzb.setJkpzh(rs.getString("jkpzh"));
				sbjkzb.setSklxdm(rs.getString("sklxdm"));
				sbjkzb.setJsjdm(rs.getString("jsjdm"));
				sbjkzb.setFsdm(rs.getString("fsdm"));
				sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
				sbjkzb.setYhdm(rs.getString("yhdm"));
				sbjkzb.setYhmc(rs.getString("yhmc"));
				sbjkzb.setZh(rs.getString("zh"));
				sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
				sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
				sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
				sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
				sbjkzb.setYskmdm(rs.getString("yskmdm"));
				sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
				sbjkzb.setSzdm(rs.getString("szdm"));
				sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
				sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
				sbjkzb.setJksj(rs.getTimestamp("jksj"));
				sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
				sbjkzb.setClbjdm(rs.getString("clbjdm"));
				sbjkzb.setSjje(rs.getBigDecimal("sjje"));
				sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
				sbjkzb.setRkje(rs.getBigDecimal("rkje"));
				sbjkzb.setZwbs(rs.getString("zwbs"));
				sbjkzb.setHxrdm(rs.getString("hxrdm"));
				sbjkzb.setHxrmc(rs.getString("hxrmc"));
				sbjkzb.setLrr(rs.getString("lrr"));
				sbjkzb.setBz(rs.getString("bz"));
				sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
				sbjkzb.setSbbh(rs.getString("sbbh"));
				sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
				sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkzb.setSjly(rs.getString("sjly"));
				sbjkzb.setNd(rs.getString("nd"));
				sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
				sbjkzb.setQxdm(rs.getString("qxdm"));
				sbjkzb.setSphm(rs.getString("sphm"));
				zbList.add(sbjkzb);
			}
			rs.close();
			// //3.-1.2.���ݼ��
			if (zbList.size() == 0) {
				throw new Exception("�޷����ҵ�˰Ʊ��Ӧ�����ݣ�sphm sql=" + sql);
			}
			//
			sbjkzb = (Sbjkzb) zbList.get(0);
			yhsbResult.setJsjdm(sbjkzb.getJsjdm());
			yhsbResult.setQxdm(sbjkzb.getQxdm());
			yhsbResult.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
			//
			String zwbsPostFix = "0";
			String sjly = null;
			List sjlyList = new ArrayList();
			for (int i = 0; i < zbList.size(); i++) {
				sbjkzb = (Sbjkzb) zbList.get(i);
				zwbsPostFix = sbjkzb.getZwbs().substring(19, 20);
				// �ж������־�Ƿ�Ϊ�ۿ�����
				if (!ApplicationConstant.ZWBS_YHKK_LOCK.equals(zwbsPostFix)) {
					throw new Exception("˰Ʊ��Ӧ�����ݿۿ�״̬����sphm="
							+ sbjkzb.getSphm());
				}
				// �ռ�������Դ
				if (!sjlyList.contains(sbjkzb.getSjly())) {
					sjlyList.add(sbjkzb.getSjly());
				}
			}
			// //3.-1.2.������Դ�ж�
			int ly = -1; // 0--���걨��1--���걨
			if (sjlyList.size() != 1) {
				throw new Exception("˰Ʊ��Ӧ��������Դ��ͳһ��ϵ�Ƿ����ݣ�sphm="
						+ sbjkzb.getSphm());
			} else {
				if (ApplicationConstant.SJLY_YHSB_YHLR
						.equals(((String) sjlyList.get(0)))) {
					ly = 0;
					System.out
							.println("==========================˰�����ӿ�ʵʱҵ��,˰Ʊ����Ϊ���걨���нɿ�����==========================");
				} else {
					ly = 1;
					System.out
							.println("==========================˰�����ӿ�ʵʱҵ��,˰Ʊ����Ϊ���걨���нɿ�����==========================");
				}
			}
			// /3.1.����ͬ����Ŀۿ�ؽ��
			if (ly == 0) { // ���걨���
				for (int i = 0; i < ypdsJkss.size(); i++) {
					// //3.1.0.��ȡֵ����
					jks = (YPDSJKS) ypdsJkss.get(i);
					jsjdm = jks.getJsjdm();
					sphm = jks.getSphm();
					sbbh = jks.getSbbh();
					jksj = jks.getSbrq();
					// //3.1.1.����ۿ�ɹ�
					if (kkFlag) { // �ۿ�ɹ������ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Ÿ��¼�¼�������־�����ʱ��,������˰�ָ��������
						sb = new StringBuffer();
						sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
						sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
						sb
								.append(StringUtils
										.getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
						sb.append(",lrrq=sysdate");
						sb.append(",jksj=");
						sb.append(StringUtils.getSQLDate(jksj));
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sql = sb.toString();
						log(sql);
						st.executeUpdate(sql);
						System.out
								.println("==========================˰�����ӿ�ʵʱҵ��ɹ�������˰Ʊ��������==========================");
					}
					// //3.1.2.����ۿ�ʧ��
					else { // �ۿ�ʧ��,���ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ��ɾ�����������־����ɾ������
						// ///3.1.2.0.ɾ����ϸ����
						sb = new StringBuffer();
						sb.append("DELETE");
						sb.append(" FROM SBDB.SB_JL_SBJKMX b");
						sb.append(" WHERE b.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND b.jkpzh IN (");
						sb
								.append("SELECT JKPZH FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sb.append(")");
						sql = sb.toString();
						log(sql);
						int rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("˰Ʊ��Ӧ���걨��ϸ�����޷��ҵ���ϵ�Ƿ����ݣ�sphm="
									+ sphm);
						}
						// ///3.1.2.0.ɾ��������
						sb = new StringBuffer();
						sb
								.append("DELETE FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sql = sb.toString();
						log(sql);
						rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("˰Ʊ��Ӧ���걨�����������޷��ҵ���ϵ�Ƿ����ݣ�sphm="
									+ sphm);
						}
						System.out
								.println("==========================˰�����ӿ�ʵʱҵ��ʧ�ܣ�ɾ��˰Ʊ����==========================");
					}
				}
			} else if (ly == 1) { // ���걨���
				for (int i = 0; i < ypdsJkss.size(); i++) {
					// //3.1.0.��ȡֵ����
					jks = (YPDSJKS) ypdsJkss.get(i);
					jsjdm = jks.getJsjdm();
					sphm = jks.getSphm();
					sbbh = jks.getSbbh();
					jksj = jks.getSbrq();
					// //3.1.1.����ۿ�ɹ�
					if (kkFlag) { // �ۿ�ɹ������ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Ÿ��¼�¼�������־�����ʱ��,������˰�ָ��������
						sb = new StringBuffer();
						sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
						sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
						sb
								.append(StringUtils
										.getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
						sb.append(",lrrq=sysdate");
						sb.append(",jksj=");
						sb.append(StringUtils.getSQLDate(jksj));
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sql = sb.toString();
						log(sql);
						st.executeUpdate(sql);
						System.out
								.println("==========================˰�����ӿ�ʵʱҵ��ɹ�������˰Ʊ��������==========================");
					}
					// //3.1.2.����ۿ�ʧ��
					else { // �ۿ�ʧ��,���ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Żع����������־���ָ�˰Ʊ�����ֵ
						// ///3.1.2.0.�ع�������
						sb = new StringBuffer();
						sb
								.append("UPDATE SBDB.SB_JL_SBJKZB a SET zwbs=SUBSTR(zwbs,1,19)||'0',sphm=jkpzh");
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sql = sb.toString();
						log(sql);
						int rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("˰Ʊ��Ӧ���걨�����������޷��ҵ���ϵ�Ƿ����ݣ�sphm="
									+ sphm);
						}
						System.out
								.println("==========================˰�����ӿ�ʵʱҵ��ʧ�ܣ�ɾ��˰Ʊ����==========================");
					}
				}
			} else {
				throw new Exception("˰Ʊ��Ӧ�����ݷǷ���");
			}
			// /3.99.�ͷ���Դ
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

        /**
         * ���걨�����걨���нɿ�ۿ��� �ж��Ƿ������걨ͨ��������걨���ݵ�������Դ�����ж���������ԴΪ91�������걨�ɿ
         * �������Ϊ���걨�ɿ���ڳɹ�����ʧ�ܣ�����Ҫ�޶��걨�ɿ����ݵ������־Ϊ�ɿ�״̬����
         * �ſ��Խ������һ����״̬�޸ġ������ʧ�ܵ�������������걨��������Ҫ�ع�SPHM��ZWBS�ֶ�
         *
         * @param kkFlag
         *            �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
         * @param ypdsJkss
         *            һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
         * @return ����ֵ����YhsbResult����
         * @throws java.lang.Exception
         *             Ӧ���쳣
         */
        public static YhsbResult yhJkKkResult(boolean kkFlag, List ypdsJkss)
                        throws Exception {
                YhsbResult yhsbResult = new YhsbResult();
                // 0.�������
                DBResource db = null;
                Statement st = null;
                Connection conn = null;
                ResultSet rs = null;
                YPDSJKS jks = null;
                StringBuffer sb = null;
                String sql = null;
                String jsjdm = null;
                // String _swjgzzjgdm = null;
                String sbbh = null;
                String sphm = null;
                String jksj = null;
                Map tmpMap = null;
                List tmpList = null;
                // 1.��ڲ������
                if (ypdsJkss == null || ypdsJkss.size() == 0) {
                        throw new Exception("���걨���нɿ�ۿ����ӿڵ�����ڲ����Ƿ�.");
                }
                // 2.��ʼ��
                // 3.ҵ�񷽷�
                try {
                        // /3.-2.��ȡ��Դ
                        conn = db.getConnection();
                        st = conn.createStatement();
                        // /3.-1.�ж��ñ����������걨�������걨
                        // //3.-1.0.ִ�в�ѯ��ȡ���
                        sb = new StringBuffer();
                        jsjdm = ((YPDSJKS) ypdsJkss.get(0)).getJsjdm();
                        sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sphm IN (");
                        for (int i = 0; i < ypdsJkss.size(); i++) {
                                jks = (YPDSJKS) ypdsJkss.get(i);
                                if (i != 0) {
                                        sb.append(",");
                                }
                                sb.append(StringUtils.getSQLStr(jks.getSphm()));
                        }
                        sb.append(")");
                        sb.append(" AND jsjdm=");
                        sb.append(StringUtils.getSQLStr(jsjdm));
                        sql = sb.toString();
                        log(sql);
                        rs = st.executeQuery(sql);
                        // //3.-1.1.ֵ����ת��
                        Sbjkzb sbjkzb = null;
                        List zbList = new ArrayList();
                        while (rs.next()) {
                                sbjkzb = new Sbjkzb();
                                sbjkzb.setJkpzh(rs.getString("jkpzh"));
                                sbjkzb.setSklxdm(rs.getString("sklxdm"));
                                sbjkzb.setJsjdm(rs.getString("jsjdm"));
                                sbjkzb.setFsdm(rs.getString("fsdm"));
                                sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
                                sbjkzb.setYhdm(rs.getString("yhdm"));
                                sbjkzb.setYhmc(rs.getString("yhmc"));
                                sbjkzb.setZh(rs.getString("zh"));
                                sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
                                sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                                sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
                                sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
                                sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
                                sbjkzb.setYskmdm(rs.getString("yskmdm"));
                                sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
                                sbjkzb.setSzdm(rs.getString("szdm"));
                                sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
                                sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
                                sbjkzb.setJksj(rs.getTimestamp("jksj"));
                                sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
                                sbjkzb.setClbjdm(rs.getString("clbjdm"));
                                sbjkzb.setSjje(rs.getBigDecimal("sjje"));
                                sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
                                sbjkzb.setRkje(rs.getBigDecimal("rkje"));
                                sbjkzb.setZwbs(rs.getString("zwbs"));
                                sbjkzb.setHxrdm(rs.getString("hxrdm"));
                                sbjkzb.setHxrmc(rs.getString("hxrmc"));
                                sbjkzb.setLrr(rs.getString("lrr"));
                                sbjkzb.setBz(rs.getString("bz"));
                                sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
                                sbjkzb.setSbbh(rs.getString("sbbh"));
                                sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
                                sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
                                sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
                                sbjkzb.setSjly(rs.getString("sjly"));
                                sbjkzb.setNd(rs.getString("nd"));
                                sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
                                sbjkzb.setQxdm(rs.getString("qxdm"));
                                sbjkzb.setSphm(rs.getString("sphm"));
                                zbList.add(sbjkzb);
                        }
                        rs.close();
                        // //3.-1.2.���ݼ��
                        if (zbList.size() == 0) {

                            yhsbResult.setResultCode("99999");
                            yhsbResult
                                            .setResultDescription("����ʧ��");
                                    return yhsbResult;
                        }
                        //
                        sbjkzb = (Sbjkzb) zbList.get(0);
                        yhsbResult.setJsjdm(sbjkzb.getJsjdm());
                        yhsbResult.setQxdm(sbjkzb.getQxdm());
                        yhsbResult.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
                        //
                        String zwbsPostFix = "0";
                        String sjly = null;
                        List sjlyList = new ArrayList();
                        for (int i = 0; i < zbList.size(); i++) {
                                sbjkzb = (Sbjkzb) zbList.get(i);
                                zwbsPostFix = sbjkzb.getZwbs().substring(19, 20);
                                // �ж������־�Ƿ�Ϊ�ۿ�����
                                if (!ApplicationConstant.ZWBS_YHKK_LOCK.equals(zwbsPostFix)) {
                                        throw new Exception("˰Ʊ��Ӧ�����ݿۿ�״̬����sphm="
                                                        + sbjkzb.getSphm());
                                }
                                // �ռ�������Դ
                                if (!sjlyList.contains(sbjkzb.getSjly())) {
                                        sjlyList.add(sbjkzb.getSjly());
                                }
                        }
                        // //3.-1.2.������Դ�ж�
                        int ly = -1; // 0--���걨��1--���걨
                        if (sjlyList.size() != 1) {
                                throw new Exception("˰Ʊ��Ӧ��������Դ��ͳһ��ϵ�Ƿ����ݣ�sphm="
                                                + sbjkzb.getSphm());
                        } else {
                                if (ApplicationConstant.SJLY_YHSB_YHLR
                                                .equals(((String) sjlyList.get(0)))) {
                                        ly = 0;
                                        System.out
                                                        .println("==========================˰�����ӿ�ʵʱҵ��,˰Ʊ����Ϊ���걨���нɿ�����==========================");
                                } else {
                                        ly = 1;
                                        System.out
                                                        .println("==========================˰�����ӿ�ʵʱҵ��,˰Ʊ����Ϊ���걨���нɿ�����==========================");
                                }
                        }
                        // /3.1.����ͬ����Ŀۿ�ؽ��
                        if (ly == 0) { // ���걨���
                                for (int i = 0; i < ypdsJkss.size(); i++) {
                                        // //3.1.0.��ȡֵ����
                                        jks = (YPDSJKS) ypdsJkss.get(i);
                                        jsjdm = jks.getJsjdm();
                                        sphm = jks.getSphm();
                                        sbbh = jks.getSbbh();
                                        jksj = jks.getSbrq();
                                        // //3.1.1.����ۿ�ɹ�
                                        if (kkFlag) { // �ۿ�ɹ������ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Ÿ��¼�¼�������־�����ʱ��,������˰�ָ��������
                                                sb = new StringBuffer();
                                                sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
                                                sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
                                                sb
                                                                .append(StringUtils
                                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
                                                sb.append(",lrrq=sysdate");
                                                sb.append(",jksj=");
                                                sb.append(StringUtils.getSQLDate(jksj));
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sql = sb.toString();
                                                log(sql);
                                                st.executeUpdate(sql);
                                                System.out
                                                                .println("==========================˰�����ӿ�ʵʱҵ��ɹ�������˰Ʊ��������==========================");
                                        }
                                        // //3.1.2.����ۿ�ʧ��
                                        else { // �ۿ�ʧ��,���ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ��ɾ�����������־����ɾ������
                                                // ///3.1.2.0.ɾ����ϸ����
                                                sb = new StringBuffer();
                                                sb.append("DELETE");
                                                sb.append(" FROM SBDB.SB_JL_SBJKMX b");
                                                sb.append(" WHERE b.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND b.jkpzh IN (");
                                                sb
                                                                .append("SELECT JKPZH FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sb.append(")");
                                                sql = sb.toString();
                                                log(sql);
                                                int rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("˰Ʊ��Ӧ���걨��ϸ�����޷��ҵ���ϵ�Ƿ����ݣ�sphm="
                                                                        + sphm);
                                                }
                                                // ///3.1.2.0.ɾ��������
                                                sb = new StringBuffer();
                                                sb
                                                                .append("DELETE FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sql = sb.toString();
                                                log(sql);
                                                rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("˰Ʊ��Ӧ���걨�����������޷��ҵ���ϵ�Ƿ����ݣ�sphm="
                                                                        + sphm);
                                                }
                                                System.out
                                                                .println("==========================˰�����ӿ�ʵʱҵ��ʧ�ܣ�ɾ��˰Ʊ����==========================");
                                        }
                                }
                        } else if (ly == 1) { // ���걨���
                                for (int i = 0; i < ypdsJkss.size(); i++) {
                                        // //3.1.0.��ȡֵ����
                                        jks = (YPDSJKS) ypdsJkss.get(i);
                                        jsjdm = jks.getJsjdm();
                                        sphm = jks.getSphm();
                                        sbbh = jks.getSbbh();
                                        jksj = jks.getSbrq();
                                        // //3.1.1.����ۿ�ɹ�
                                        if (kkFlag) { // �ۿ�ɹ������ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Ÿ��¼�¼�������־�����ʱ��,������˰�ָ��������
                                                sb = new StringBuffer();
                                                sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
                                                sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
                                                sb
                                                                .append(StringUtils
                                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
                                                sb.append(",lrrq=sysdate");
                                                sb.append(",jksj=");
                                                sb.append(StringUtils.getSQLDate(jksj));
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sql = sb.toString();
                                                log(sql);
                                                st.executeUpdate(sql);
                                                System.out
                                                                .println("==========================˰�����ӿ�ʵʱҵ��ɹ�������˰Ʊ��������==========================");
                                        }
                                        // //3.1.2.����ۿ�ʧ��
                                        else { // �ۿ�ʧ��,���ռ�������롢˰�������֯�������롢�걨��š�˰Ʊ�Żع����������־���ָ�˰Ʊ�����ֵ
                                                // ///3.1.2.0.�ع�������
                                                sb = new StringBuffer();
                                                sb
                                                                .append("UPDATE SBDB.SB_JL_SBJKZB a SET zwbs=SUBSTR(zwbs,1,19)||'0',sphm=jkpzh");
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sql = sb.toString();
                                                log(sql);
                                                int rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("˰Ʊ��Ӧ���걨�����������޷��ҵ���ϵ�Ƿ����ݣ�sphm="
                                                                        + sphm);
                                                }
                                                System.out
                                                                .println("==========================˰�����ӿ�ʵʱҵ��ʧ�ܣ�ɾ��˰Ʊ����==========================");
                                        }
                                }
                        } else {
                                throw new Exception("˰Ʊ��Ӧ�����ݷǷ���");
                        }
                        // /3.99.�ͷ���Դ
                        st.close();
                } catch (Exception e) {
                        e.printStackTrace();
                        throw ExceptionUtil.getBaseException(e);
                } finally {
                        db.destroyConnection(conn);
                }
                // 98.
                yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
                                + SkhConstant.RESULT_CODE_MID_DEFAULT
                                + SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
                yhsbResult
                                .setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
                // 99.
                return yhsbResult;
        }


	/**
	 * �����걨��Ų�ѯδ�������
	 *
	 * @param sbbh
	 *            �ɿ�ƾ֤��
	 * @param conn
	 *            ���ݿ�����
	 * @return ArrayList ���������б� ��Ա����ΪDeclareInfor
	 * @throws java.lang.Exception
	 */
	protected static List queryDataBySBBH(String jsjdm, String sbbh,
			Connection conn) throws Exception {
		// 0.�������
		List rnList = null;
		StringBuffer sb = null;
		String sql = null;
		Statement stat = null;
		ResultSet rs = null;
		boolean exFlag = false;
		DeclareInfor declareInfor = null;
		Sbjkzb sbjkzb = null;
		Sbjkmx sbjkmx = null;
		ArrayList jksmxList = null;
		// 1.��ڲ������
		if (sbbh == null || NULL_ZERO.equals(sbbh)) {
			throw new Exception("��ڲ����Ƿ�(sbbh=" + sbbh + ")");
		} else if (conn == null) {
			throw new Exception("������ݿ�����Ϊ�գ�");
		}
		// 2.��ʼ��
		rnList = new ArrayList();
		// 3.ҵ�����
		// /3.0.������ض���
		// /3.1.��ȡ����
		try {
			// //3.1.0.��ʼ�����ݿ�
			stat = conn.createStatement();
			// //3.1.1.������ѯ�걨�ɿ������SQL
			sb = new StringBuffer();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
			sb.append(sbbh);
			sb.append("'");
			sb.append(" and jsjdm = '");
			sb.append(jsjdm);
			sb.append("'");
			/** **************modfiy by hezy ͬһ���п����ٴδ���***************** */
			// sb.append("' AND zwbs like '0%0'");
			sql = sb.toString();
			// //3.1.1.ִ�в�ѯ
			log(sql);
			rs = stat.executeQuery(sql);
			// //3.1.2.�����걨�ɿ����ݲ��������ݼ�
			while (rs.next()) {
				declareInfor = new DeclareInfor();
				sbjkzb = new Sbjkzb();
				jksmxList = new ArrayList();
				declareInfor.setSbjkzb(sbjkzb);
				declareInfor.setSbjkmxInfo(jksmxList);
				// ֵ����ת��
				sbjkzb.setJkpzh(rs.getString("jkpzh"));
				sbjkzb.setSklxdm(rs.getString("sklxdm"));
				sbjkzb.setJsjdm(rs.getString("jsjdm"));
				sbjkzb.setFsdm(rs.getString("fsdm"));
				sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
				sbjkzb.setYhdm(rs.getString("yhdm"));
				sbjkzb.setYhmc(rs.getString("yhmc"));
				sbjkzb.setZh(rs.getString("zh"));
				sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
				sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
				sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
				sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
				sbjkzb.setYskmdm(rs.getString("yskmdm"));
				sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
				sbjkzb.setSzdm(rs.getString("szdm"));
				sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
				sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
				sbjkzb.setJksj(rs.getTimestamp("jksj"));
				sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
				sbjkzb.setClbjdm(rs.getString("clbjdm"));
				sbjkzb.setSjje(rs.getBigDecimal("sjje"));
				sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
				sbjkzb.setRkje(rs.getBigDecimal("rkje"));
				sbjkzb.setZwbs(rs.getString("zwbs"));
				sbjkzb.setHxrdm(rs.getString("hxrdm"));
				sbjkzb.setHxrmc(rs.getString("hxrmc"));
				sbjkzb.setLrr(rs.getString("lrr"));
				sbjkzb.setBz(rs.getString("bz"));
				sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
				sbjkzb.setSbbh(rs.getString("sbbh"));
				sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
				sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkzb.setSjly(rs.getString("sjly"));
				sbjkzb.setNd(rs.getString("nd"));
				sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
				sbjkzb.setQxdm(rs.getString("qxdm"));
				sbjkzb.setSphm(rs.getString("sphm"));
				rnList.add(declareInfor);
			}
			rs.close();
			// //3.1.2.��������ݼ��ϵĺϷ���
			if (rnList.size() == 0) {
				throw new Exception("(sbbh=" + sbbh + ")�޴��걨����");
			}
			// //3.1.2.������ѯ�걨�ɿ������SQL
			sb = new StringBuffer();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKMX b WHERE b.jkpzh in (");
			sb.append("SELECT a.jkpzh FROM SBDB.SB_JL_SBJKZB a WHERE a.sbbh=");
			sb.append(StringUtils.getSQLStr(sbbh));
			sb.append(" and jsjdm = '");
			sb.append(jsjdm);
			sb.append("'");
			/** **************modfiy by hezy ͬһ���п����ٴδ���***************** */
			// sb.append(" AND a.zwbs like '0%0'");
			sb.append(")");
			sql = sb.toString();
			log(sql);
			// //3.1.1.ִ�в�ѯ
			rs = stat.executeQuery(sql);
			boolean mxFlag = false;
			while (rs.next()) {
				// ///3.1.1.0.�����µ�ֵ����
				sbjkmx = new Sbjkmx();
				mxFlag = false;
				// ///3.1.1.1.����ת��
				sbjkmx.setSzsmdm(rs.getString("szsmdm"));
				sbjkmx.setJkpzh(rs.getString("jkpzh"));
				sbjkmx.setJsjdm(rs.getString("jsjdm"));
				sbjkmx.setYskmdm(rs.getString("yskmdm"));
				sbjkmx.setYsjcdm(rs.getString("ysjcdm"));
				sbjkmx.setKssl(rs.getBigDecimal("kssl"));
				sbjkmx.setJsje(rs.getBigDecimal("jsje"));
				sbjkmx.setSjse(rs.getBigDecimal("sjse"));
				sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkmx.setRkje(rs.getBigDecimal("rkje"));
				sbjkmx.setSbbh(rs.getString("sbbh"));
				sbjkmx.setSjfc(rs.getBigDecimal("sjfc"));
				sbjkmx.setQjfc(rs.getBigDecimal("qjfc"));
				sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkmx.setNd(rs.getString("nd"));
				sbjkmx.setSl(rs.getBigDecimal("sl"));
				sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
				sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
				sbjkmx.setQxdm(rs.getString("qxdm"));
				// ///3.1.1.2.�ж�������һ�����ݼ�����������ݼ�,����Ҳ������׳��쳣
				for (int i = 0; i < rnList.size(); i++) {
					declareInfor = (DeclareInfor) rnList.get(i);
					if (sbjkmx.getJkpzh().equals(
							declareInfor.getSbjkzb().getJkpzh())
							&& sbjkmx.getJsjdm().equals(
									declareInfor.getSbjkzb().getJsjdm())) {
						mxFlag = true;
						declareInfor.getSbjkmxInfo().add(sbjkmx);
						break;
					}
				}
				if (!mxFlag) {
					throw new Exception("�޷��ҵ��걨�ɿ���ϸ[" + sbjkmx.getJkpzh() + "]");
				}
			}
			rs.close();
			// //3.1.2.�����ݼ��Ͻ��кϷ��Լ��
			if (rnList.size() == 0) {
				throw new Exception("�޷��ҵ��걨�ɿ�����!sbbh=" + sbbh);
			}
			for (int i = 0; i < rnList.size(); i++) {
				declareInfor = (DeclareInfor) rnList.get(i);
				if (declareInfor.getSbjkmxInfo().size() == 0) {
					throw new Exception("�޷��ҵ���Ӧ���걨�ɿ���ϸ["
							+ declareInfor.getSbjkzb().getJkpzh() + "]");
				}
			}
			// //3.1.99.�ر����ݿ�����
			stat.close();
		} catch (Exception ex) {
			log(ex.getMessage());
			throw ex;
		}
		// 99.����ֵ
		return rnList;
	}

	public static boolean deleteSbjkDataBySphm(String sphm) throws Exception {
		// 0. �������
		boolean rnFlag = true;
		PreparedStatement pStat = null;
		// 1.�������
		if (sphm == null) {
			throw new Exception("��ڲ�������sphm=" + sphm);
		}
		// 2.��ʼ��
		// 3.ҵ�����
		try {
			/**
			 * @todo ɾ��˰Ʊ���붨������ݣ�������ɾ��֮ǰȷ��״̬Ϊ�ѷ��Ϳۿ�
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 99.����ֵ
		return rnFlag;
	}
        public static YhsbResult yhkkBySbbh(String sbbh, String sphm, String jsjdm, String jksj) throws Exception {
            DBResource db = null;
            Statement st = null;
            Connection conn = null;
            conn = db.getConnection();
            st = conn.createStatement();

            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
            sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
            sb
                            .append(StringUtils

                                            .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));

            sb.append(",lrrq=sysdate");
            sb.append(",sphm=");
            sb.append(StringUtils.getSQLStr(sphm));
            sb.append(",jksj=");
            sb.append(StringUtils.getSQLDate(jksj));
            sb.append(" WHERE a.jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND a.sbbh=");
            sb.append(StringUtils.getSQLStr(sbbh));
            sb.append(" AND a.zwbs like '0%0'");
            String sql = sb.toString();
            System.out.println("YHSBSQL ==== : " + sb.toString());
            try {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                throw new Exception("���ж˽ɿ����˰Ʊ��Ϣʧ��.");
            }
            System.out
                            .println("==========================˰�����ӿ�ʵʱҵ��ɹ�������˰Ʊ��������==========================");

                    YhsbResult yhsbResult = new YhsbResult();
                    // 98.
                    yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
                                    + SkhConstant.RESULT_CODE_MID_DEFAULT
                                    + SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
                    yhsbResult
                                    .setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
                    // 99.
                    return yhsbResult;

        }

	public static void gtgshYhkkhz(Connection conn) throws Exception {
		// 0. �������
		boolean rnFlag = true;
		PreparedStatement pStat = null;
		// 1.�������
		if (conn == null) {
			throw new Exception("���ݿ����Ӵ���");
		}
		// 2.��ʼ��
		// 3.ҵ�����
		try {
			/**
			 * @todo ����������幤�̻��ۿ��ִ
			 */
			/**
			 * BEGIN LOOP 1.����˰Ʊ�����ѯSFDB.SF_JL_GTGSH_YHKKHZXX_LW�Ŀۿ����ݣ�����Ϊ�ѷ���
			 * 2.���ݿۿ��ִ��Ϣ�Ŀۿ����������ݣ��ɹ��������걨�ɿ�����
			 * 3.���ݿۿ��ִ��Ϣ�Ŀۿ����������ݣ��ɹ�����Ϊ�ѿۿʧ������Ϊ�ۿ�ʧ�� END LOOP
			 * 4.������˰Ʊ��ִ��������Ժ󣬼�������¶ȿۿ������Ƿ�ȫ��������ϣ����ȫ�������������״̬�������¶�Ϊ����ɣ�������Ϊ������
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 99.����ֵ
	}

	public static String SQL_GTGSH_YHKKHZXX_SELECT = "";

}
