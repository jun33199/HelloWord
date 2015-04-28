package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.CzzsJbSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsFbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsnbFbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsFbSbsjVO;

public class QysdsUtil {
	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.33";

	public QysdsUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void getHdxx(String jsjdm, HdxxVO hdvo, HdzsSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx) throws BaseException {
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

		// ����
		Map skssrq = new HashMap();

		// ˰��������ʼ�ͽ�������
		Timestamp skssksrq;
		Timestamp skssjsrq;

		// ȡ�����ڵļ���
		String jd = Skssrq.preQuarter(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {
			if (bblx.equals(Constant.NB)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// �걨
			} else {

				if ("4".equals(jd)) {

					skssrq = Skssrq.yearSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "01");// �걨
				} else {

					skssrq = Skssrq.otherSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "00");// ����
				}

				// qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
				// skssjsrq, "00");//����
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// ��ֵ
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("��ǰ�걨�ļ���jd:" + jd);

		System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

		System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

		System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);
                System.out.print("��ȡ���շ�ʽ���롷��������������������������������ʼ��������������������");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
                        System.out.print("���շ�ʽ���룺 ========= " + zsfsdm);

			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					hdvo.setJmzg("1"); // �м����ʸ�
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			hdvo.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				hdvo.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			hdvo.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		// sbsj.setSl(qyzssl);

		// /**
		// * �ж��ڼ���������������Ƿ��м����ʸ�
		// */
		//
		// try {
		//
		// conn = DBResource.getConnection();
		// StringBuffer bf = new StringBuffer();
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
		// t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.jmsqsrq<= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.jmszzrq>= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.szsmdm='30' ").append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // �м����ʸ�
		// }
		//
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// throw ExceptionUtil.getBaseException(ex, "��ѯ�������ͣ��Ƿ��м����ʸ�ʧ��");
		// } finally {
		// // �ر����ݿ�����
		// DBResource.destroyConnection(conn);
		// }

	}

        public void getFbHdxx(String jsjdm, HdxxVO hdvo, HdzsFbSbsjVO sbsj,
                        SWDJJBSJ swdjjbsj, String bblx) throws BaseException {
                String qyzssllx = "3"; // ȱʡΪ�����걨

                // ��ҵ��˰��˰�� �������ҵ��˰��˰������
                String qyzssl = QYSDS_SL;

                // Ӧ������˰��
                String ynsdse = "0.00";
                // ��������˰��
                String dezsse = "0.00";

                // ��ǰʱ��
                Timestamp sbrq = new Timestamp(System.currentTimeMillis());
                // Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
                // .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
                // CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

                // ����
                Map skssrq = new HashMap();

                // ˰��������ʼ�ͽ�������
                Timestamp skssksrq;
                Timestamp skssjsrq;

                // ȡ�����ڵļ���
                String jd = Skssrq.preQuarter(sbrq);

                ServiceProxy proxy = new ServiceProxy();

                // ��ѯ˰�ѽӿ�
                QysdsSet qysdsSet = null;

                // ���ݿ����Ӷ���
                Connection conn = null;
                Statement st = null;
                ResultSet rs = null;
                // �����ʸ��ʶ
                boolean jm_type = false;

                try {
                        if (bblx.equals(Constant.NB)) {

                                skssrq = Skssrq.yearSkssrq(sbrq);

                                // ȡ��˰��������ʼ�ͽ�������
                                skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
                                skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

                                qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
                                                "01");// �걨
                        } else {

                                if ("4".equals(jd)) {

                                        skssrq = Skssrq.yearSkssrq(sbrq);

                                        // ȡ��˰��������ʼ�ͽ�������
                                        skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
                                        skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

                                        // ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
                                        qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
                                                        skssjsrq, "01");// �걨
                                } else {

                                        skssrq = Skssrq.otherSkssrq(sbrq);

                                        // ȡ��˰��������ʼ�ͽ�������
                                        skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
                                        skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

                                        // �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
                                        qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
                                                        skssjsrq, "00");// ����
                                }

                                // qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
                                // skssjsrq, "00");//����
                        }

                } catch (com.ttsoft.framework.exception.BaseException e) {
                        e.printStackTrace();
                        throw ExceptionUtil.getBaseException(e);
                }

                // 1����ѯ��ҵ���շ�ʽ
                Zsfs zsfs = qysdsSet.getZsfs();

                Date gxqyrdrq = qysdsSet.getGxjsqy();

                // ��ֵ
                hdvo.setCyl("0");
                hdvo.setXzqy("0");
                hdvo.setDezsse("0.00");
                hdvo.setYbjmsl("0.00");

                System.out.println("��ǰ�걨�ļ���jd:" + jd);

                System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

                System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

                System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);

                if (zsfs != null) {
                        String zsfsdm = zsfs.getZsfsdm();
                        if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
                                if (gxqyrdrq == null) {
                                        // ����������
                                        qyzssllx = "2";
                                } else {
                                        // ���¼����ʹ�������ҵ
                                        qyzssllx = "5";
                                        qyzssl = "0.15";
                                        hdvo.setJmzg("1"); // �м����ʸ�
                                }
                                hdvo.setCyl(zsfs.getCyl());
                        } else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
                                // ��������
                                qyzssllx = "4";
                                // ��ʱ���ֶδ�����ҵ�˶�˰��
                                // ynsdse = zsfs.getDe();
                                dezsse = zsfs.getDe();
                                hdvo.setDezsse(dezsse);
                        }
                }

                // 2����ѯ�Ƿ��Ǹ��¼�����ҵ
                if (gxqyrdrq != null) {
                        if (zsfs != null
                                        && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
                                // ���¼����ʹ�������ҵ
                                qyzssllx = "5";
                        } else {
                                // ����Ϊ���¼�����ҵ
                                qyzssllx = "1";
                        }
                        qyzssl = "0.15";
                        hdvo.setJmzg("1"); // �м����ʸ�
                }

                // �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
                else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
                        if (qysdsSet.isXzqy()) {
                                hdvo.setXzqy("1");
                                hdvo.setJmzg("1"); // �м����ʸ�
                        }
                }

                if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
                                && qysdsSet.getYbjmsl() != null) {
                        // ��������ҵ�ļ������
                        hdvo.setJmzg("1"); // �м����ʸ�
                        DecimalFormat ft = new DecimalFormat("0.00");
                        hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
                }
                hdvo.setQyzslx(qyzssllx);
                // sbsj.setSl(qyzssl);

                // /**
                // * �ж��ڼ���������������Ƿ��м����ʸ�
                // */
                //
                // try {
                //
                // conn = DBResource.getConnection();
                // StringBuffer bf = new StringBuffer();
                // bf.delete(0, bf.length());
                //
                // bf
                // .append(
                // " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
                // t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
                // .append(" where t1.jsjdm= ").append(
                // StringUtils.getSQLStr(jsjdm)).append(
                // " and t2.sqspbh=t1.sqspbh ").append(
                // " and t2.jmsqsrq<= ").append(
                // StringUtils.getSQLFromTimestamp(skssjsrq)).append(
                // " and t2.jmszzrq>= ").append(
                // StringUtils.getSQLFromTimestamp(skssjsrq)).append(
                // " and t2.szsmdm='30' ").append(
                // " order by t1.cjrq desc ");
                //
                // System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL");
                // System.out.println(bf.toString());
                // st = conn.createStatement();
                // rs = st.executeQuery(bf.toString());
                //
                // if (rs.next()) {
                // jm_type = true;
                // }
                //
                // if (!jm_type) {
                // jm_type = false;
                // }
                // if (jm_type) {
                // hdvo.setJmzg("1"); // �м����ʸ�
                // }
                //
                // } catch (Exception ex) {
                // ex.printStackTrace();
                // throw ExceptionUtil.getBaseException(ex, "��ѯ�������ͣ��Ƿ��м����ʸ�ʧ��");
                // } finally {
                // // �ر����ݿ�����
                // DBResource.destroyConnection(conn);
                // }

        }


	/**
	 * �˶�������ҵ ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsnbVO Hdzsconvert2XMLVO(HdzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws BaseException {
		SbxxVO sbxx = new SbxxVO();
		HdzsSbsjVO sbsj = new HdzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO nsrxx = new NsrxxVO();
		HdzssdsnbVO qysdsnb = new HdzssdsnbVO();
		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ��˰����Ϣ��
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// �˶���Ϣ������һ�����걨��Ϣ
		getHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// ��Ϣ
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// �걨����
		sbsj.setSrze_bqs(qysdsndbo.getSrze_bqs());
		sbsj.setSrze_ljs(qysdsndbo.getSrze_ljs());
		sbsj.setYssdl_bqs(qysdsndbo.getYssdl_bqs());
		sbsj.setYssdl_ljs(qysdsndbo.getYssdl_ljs());
		sbsj.setYnssde_bqs(qysdsndbo.getYnssde_bqs());
		sbsj.setYnssde_ljs(qysdsndbo.getYnssde_ljs());
		sbsj.setYbdsdse_bqs(qysdsndbo.getYbdsdse_bqs());
		sbsj.setYbdsdse_ljs(qysdsndbo.getYbdsdse_ljs());
		sbsj.setSysl_bqs(qysdsndbo.getSysl_bqs());
		sbsj.setSysl_ljs(qysdsndbo.getSysl_ljs());
		sbsj.setYjsdse_bqs(qysdsndbo.getYjsdse_bqs());
		sbsj.setYjsdse_ljs(qysdsndbo.getYjsdse_ljs());
		sbsj.setSjyyjdsdse_bqs(qysdsndbo.getSjyyjdsdse_bqs());
		sbsj.setSjyyjdsdse_ljs(qysdsndbo.getSjyyjdsdse_ljs());
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		
		sbsj.setZczbje(qysdsndbo.getZczbje());
		sbsj.setZcze_ljs(qysdsndbo.getZcze_ljs());
		
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSNB);
		qysdsnb.setSchemaVersion(Constant.CA_XSLTDM_HDZSSDSNB);
		if (bblx.equals(Constant.NB)) {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSNB);
		} else {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB);
		}

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}
        /**
         * �˶�������ҵ ���ɵ�VO����ת��ΪXML-VO����
         *
         * @param qysdsjd
         * @param djJbsj
         * @return
         * @throws BaseException
         */
        public HdzsnbFbVO HdzsFbconvert2XMLVO(HdzssdsFbBO qysdsndbo, SWDJJBSJ djJbsj,
                        String bblx) throws BaseException {
                SbxxVO sbxx = new SbxxVO();
                HdzsFbSbsjVO sbsj = new HdzsFbSbsjVO();
                HdxxVO hdxx = new HdxxVO();
                NsrxxVO nsrxx = new NsrxxVO();
                HdzsnbFbVO qysdsnb = new HdzsnbFbVO();
                // ���ϵͳ��ǰ����
                Timestamp curDate = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                // ////////////////////////////////////
                // ���VO��
                // ��˰����Ϣ��
                nsrxx.setJsjdm(djJbsj.getJsjdm());
                nsrxx.setNsrmc(djJbsj.getNsrmc());
                nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

                qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
                // �˶���Ϣ������һ�����걨��Ϣ
                getFbHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

                // ��Ϣ
                sbxx.setFsdm(CodeConstant.FSDM_WSSB);
                sbxx.setJd(qysdsndbo.getJd());
                sbxx.setNd(qysdsndbo.getNd());
                sbxx.setSbrq(sdf.format(curDate));
                sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
                sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

                // �걨����
                sbsj.setJmshj_je(qysdsndbo.getJmshj_je());
                sbsj.setGdqyh_je(qysdsndbo.getGdqyh_je());
                sbsj.setGxqy_je(qysdsndbo.getGxqy_je());
                sbsj.setMzzz_je(qysdsndbo.getMzzz_je());
                sbsj.setQt_je(qysdsndbo.getQt_je());
                sbsj.setQyrs(qysdsndbo.getQyrs());
                sbsj.setSshy(qysdsndbo.getSshy());
                sbsj.setXxwl_je(qysdsndbo.getXxwl_je());
                sbsj.setZcze(qysdsndbo.getZcze());
                sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
                // ��ҵ����˰�걨
                qysdsnb.setNsrxx(nsrxx);
                qysdsnb.setSbsj(sbsj);
                qysdsnb.setSbxx(sbxx);
                qysdsnb.setHdxx(hdxx);

                // XML�ĵ���Ϣ
                qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSFBNB);
                qysdsnb.setSchemaVersion(Constant.CA_XSLTDM_HDZSSDSFBNB);
                if (bblx.equals(Constant.NB)) {
                        qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSFBNB);
                } else {
                        qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB);
                }

                qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
                return qysdsnb;
        }
        /**
         * �˶�������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
         *
         * @param qysdsjb
         * @return
         * @throws BaseException
         */
        public HdzssdsFbBO HdzsFbconvert2VO(HdzsnbFbVO qysdsnbvo) throws BaseException {
                HdzssdsFbBO qysdsnbbo = new HdzssdsFbBO();
                // ��˰����Ϣ
                qysdsnbbo.setJsjdm(qysdsnbvo.getNsrxx().getJsjdm());
                qysdsnbbo.setNsrmc(qysdsnbvo.getNsrxx().getNsrmc());
                qysdsnbbo.setSwjgzzjgdm(qysdsnbvo.getNsrxx().getSwjgzzjgdm());
                // �˶���Ϣ
                qysdsnbbo.setCyl(qysdsnbvo.getHdxx().getCyl());
                qysdsnbbo.setDezsse(qysdsnbvo.getHdxx().getDezsse());
                qysdsnbbo.setJmzg(qysdsnbvo.getHdxx().getJmzg());
                qysdsnbbo.setQyzslx(qysdsnbvo.getHdxx().getQyzslx());
                qysdsnbbo.setXzqy(qysdsnbvo.getHdxx().getXzqy());
                qysdsnbbo.setYbjmsl(qysdsnbvo.getHdxx().getYbjmsl());
                // �걨��Ϣ
                qysdsnbbo.setFsdm(qysdsnbvo.getSbxx().getFsdm());
                qysdsnbbo.setJd(qysdsnbvo.getSbxx().getJd());
                qysdsnbbo.setNd(qysdsnbvo.getSbxx().getNd());
                qysdsnbbo.setSbrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSbrq())));
                qysdsnbbo.setSkssjsrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSkssjsrq())));
                qysdsnbbo.setSkssksrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSkssksrq())));

                // �걨���ݡ�
                qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
                qysdsnbbo.setJmshj_je(qysdsnbvo.getSbsj().getJmshj_je());
                qysdsnbbo.setGdqyh_je(qysdsnbvo.getSbsj().getGdqyh_je());
                qysdsnbbo.setGxqy_je(qysdsnbvo.getSbsj().getGxqy_je());
                qysdsnbbo.setMzzz_je(qysdsnbvo.getSbsj().getMzzz_je());
                qysdsnbbo.setQt_je(qysdsnbvo.getSbsj().getQt_je());
                qysdsnbbo.setQyrs(qysdsnbvo.getSbsj().getQyrs());
                qysdsnbbo.setSshy(qysdsnbvo.getSbsj().getSshy());
                qysdsnbbo.setXxwl_je(qysdsnbvo.getSbsj().getXxwl_je());
                qysdsnbbo.setZcze(qysdsnbvo.getSbsj().getZcze());
                return qysdsnbbo;
        }


	/**
	 * �˶�������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsBO Hdzsconvert2VO(HdzssdsnbVO qysdsnbvo) throws BaseException {
		HdzssdsBO qysdsnbbo = new HdzssdsBO();
		// ��˰����Ϣ
		qysdsnbbo.setJsjdm(qysdsnbvo.getNsrxx().getJsjdm());
		qysdsnbbo.setNsrmc(qysdsnbvo.getNsrxx().getNsrmc());
		qysdsnbbo.setSwjgzzjgdm(qysdsnbvo.getNsrxx().getSwjgzzjgdm());
		// �˶���Ϣ
		qysdsnbbo.setCyl(qysdsnbvo.getHdxx().getCyl());
		qysdsnbbo.setDezsse(qysdsnbvo.getHdxx().getDezsse());
		qysdsnbbo.setJmzg(qysdsnbvo.getHdxx().getJmzg());
		qysdsnbbo.setQyzslx(qysdsnbvo.getHdxx().getQyzslx());
		qysdsnbbo.setXzqy(qysdsnbvo.getHdxx().getXzqy());
		qysdsnbbo.setYbjmsl(qysdsnbvo.getHdxx().getYbjmsl());
		// �걨��Ϣ
		qysdsnbbo.setFsdm(qysdsnbvo.getSbxx().getFsdm());
		qysdsnbbo.setJd(qysdsnbvo.getSbxx().getJd());
		qysdsnbbo.setNd(qysdsnbvo.getSbxx().getNd());
		qysdsnbbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSbrq())));
		qysdsnbbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSkssjsrq())));
		qysdsnbbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSkssksrq())));

		// �걨���ݡ�
		qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
		qysdsnbbo.setSjyyjdsdse_bqs(qysdsnbvo.getSbsj().getSjyyjdsdse_bqs());
		qysdsnbbo.setSjyyjdsdse_ljs(qysdsnbvo.getSbsj().getSjyyjdsdse_ljs());
		qysdsnbbo.setSrze_bqs(qysdsnbvo.getSbsj().getSrze_bqs());
		qysdsnbbo.setSrze_ljs(qysdsnbvo.getSbsj().getSrze_ljs());
		qysdsnbbo.setSysl_bqs(qysdsnbvo.getSbsj().getSysl_bqs());
		qysdsnbbo.setSysl_ljs(qysdsnbvo.getSbsj().getSysl_ljs());
		qysdsnbbo.setYbdsdse_bqs(qysdsnbvo.getSbsj().getYbdsdse_bqs());
		qysdsnbbo.setYbdsdse_ljs(qysdsnbvo.getSbsj().getYbdsdse_ljs());
		qysdsnbbo.setYjsdse_bqs(qysdsnbvo.getSbsj().getYjsdse_bqs());
		qysdsnbbo.setYjsdse_ljs(qysdsnbvo.getSbsj().getYjsdse_ljs());
		qysdsnbbo.setYnssde_bqs(qysdsnbvo.getSbsj().getYnssde_bqs());
		qysdsnbbo.setYnssde_ljs(qysdsnbvo.getSbsj().getYnssde_ljs());
		qysdsnbbo.setYssdl_bqs(qysdsnbvo.getSbsj().getYssdl_bqs());
		qysdsnbbo.setYssdl_ljs(qysdsnbvo.getSbsj().getYssdl_ljs());
		
		qysdsnbbo.setZczbje(qysdsnbvo.getSbsj().getZczbje());
		qysdsnbbo.setZcze_ljs(qysdsnbvo.getSbsj().getZcze_ljs());

		return qysdsnbbo;
	}

	private String getFormatDatetime(String datetime) {
		try {
			String retStr = new String();
			StringTokenizer st = new StringTokenizer(datetime.trim());
			retStr = st.nextToken();
			// �Ƚ����ڲ��ָ�ʽ��
			if (retStr.substring(6, 7).equals("-")) {
				retStr = retStr.substring(0, 5) + "0" + retStr.substring(5);
			}
			if (retStr.length() < 10) {
				retStr = retStr.substring(0, 8) + "0" + retStr.substring(8);
				// ����ʱ�䲿��
			}
			if (st.countTokens() < 1) {
				return retStr + " 00:00:00.000"; // ��������
			}
			String s = new String();
			s = st.nextToken();
			st = new StringTokenizer(s, ":");
			int count = st.countTokens();
			if (count == 1) {
				retStr += " " + s + ":00:00.000"; // ʱ����ֻ��Сʱ
			}
			if (count == 2) {
				retStr += " " + s + ":00.000"; // ʱ����ֻ��Сʱ������
			}
			if (count == 3) {
				retStr += " " + s + ".000"; // ʱ�����޺���
			}
			return retStr;
		} catch (Exception e) {
			return null;
		}
	}

	public String getJbDM() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		String jd = Skssrq.preQuarter(curDate);
		return jd;
	}

	public void getCzxx(String jsjdm, HdxxVO hdvo, CzzsJbSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx) throws BaseException {
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

		// ����
		Map skssrq = new HashMap();

		// ˰��������ʼ�ͽ�������
		Timestamp skssksrq;
		Timestamp skssjsrq;

		// ȡ�����ڵļ���
		String jd = Skssrq.preQuarter(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {
			if (bblx.equals(Constant.CZNB)) {
				skssrq = Skssrq.yearSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// �걨
			} else {

				if ("4".equals(jd)) {

					skssrq = Skssrq.yearSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "01");// �걨
				} else {

					skssrq = Skssrq.otherSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "00");// ����
				}

				// qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
				// skssjsrq, "00");//����
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// ��ֵ
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("��ǰ�걨�ļ���jd:" + jd);

		System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

		System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

		System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);

		if (zsfs != null) {

			System.out.println("���շ�ʽ����:" + zsfs.getZsfsdm());

			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					hdvo.setJmzg("1"); // �м����ʸ�
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			hdvo.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				hdvo.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			hdvo.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		// sbsj.setSl(qyzssl);

		// /**
		// * �ж��ڼ���������������Ƿ��м����ʸ�
		// */
		//
		// try {
		//
		// conn = DBResource.getConnection();
		// StringBuffer bf = new StringBuffer();
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
		// t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.jmsqsrq<= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.jmszzrq>= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.szsmdm='30' ").append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // �м����ʸ�
		// }
		//
		//
		// // 2007��4��4�����жϸ��¼�����ҵ9010ֻ�ж������������ж��Ƿ����
		//
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from
		// spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.szsmdm='30' ")
		// .append(" and t1.jmslbdm in ('9010')")
		// .append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL�����¼�����ҵ9010");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // �м����ʸ�
		// }
		//
		//
		// // 2007��4��4�����жϸ��¼�����ҵ9010ֻ�ж������������ж��Ƿ���� end
		//
		//
		// } catch (Exception ex) {
		//
		// ex.printStackTrace();
		//
		// throw ExceptionUtil.getBaseException(ex, "��ѯ�������ͣ��Ƿ��м����ʸ�ʧ��");
		// } finally {
		// // �ر����ݿ�����
		// DBResource.destroyConnection(conn);
		// }

	}

	/**
	 * ����������ҵ ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsjbVO Czzsconvert2XMLVO(CzzssdsBO qysdsbo, SWDJJBSJ djJbsj,
			String bblx) throws BaseException {
		SbxxVO sbxx = new SbxxVO();
		CzzsJbSbsjVO sbsj = new CzzsJbSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO nsrxx = new NsrxxVO();
		CzzssdsjbVO qysdsvo = new CzzssdsjbVO();
		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ��˰����Ϣ��
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// �˶���Ϣ������һ�����걨��Ϣ
		getCzxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// ��Ϣ
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));

		// �걨����
		sbsj.setHznscyqyjdyjbl(qysdsbo.getHznscyqyjdyjbl());
		sbsj.setJmsdse(qysdsbo.getJmsdse());
		sbsj.setLrze(qysdsbo.getLrze());
		sbsj.setMbyqndks(qysdsbo.getMbyqndks());
		sbsj.setNstzjse(qysdsbo.getNstzjse());
		sbsj.setNstzzje(qysdsbo.getNstzzje());
		sbsj.setSbrqshow(qysdsbo.getSbrqshow());
		sbsj.setSjyyjdsdse(qysdsbo.getSjyyjdsdse());
		sbsj.setSysl(qysdsbo.getSysl());
		sbsj.setYbdsdse(qysdsbo.getYbdsdse());
		sbsj.setYnsdse(qysdsbo.getYnsdse());
		sbsj.setYnssde(qysdsbo.getYnssde());

		// ��ҵ����˰�걨
		qysdsvo.setNsrxx(nsrxx);
		qysdsvo.setSbsj(sbsj);
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_CZSSDSJB);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_CZZSSDSJB);

		qysdsvo.setYwlx(Constant.CA_YWLXDM_CZZSSDSJB);

		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsvo;
	}

	/**
	 * ����������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsBO Czzsconvert2VO(CzzssdsjbVO qysdsvo) throws BaseException {
		CzzssdsBO qysdsbo = new CzzssdsBO();
		// ��˰����Ϣ
		qysdsbo.setJsjdm(qysdsvo.getNsrxx().getJsjdm());
		qysdsbo.setNsrmc(qysdsvo.getNsrxx().getNsrmc());
		qysdsbo.setSwjgzzjgdm(qysdsvo.getNsrxx().getSwjgzzjgdm());
		// �˶���Ϣ
		qysdsbo.setCyl(qysdsvo.getHdxx().getCyl());
		qysdsbo.setDezsse(qysdsvo.getHdxx().getDezsse());
		qysdsbo.setJmzg(qysdsvo.getHdxx().getJmzg());
		qysdsbo.setQyzslx(qysdsvo.getHdxx().getQyzslx());
		qysdsbo.setXzqy(qysdsvo.getHdxx().getXzqy());
		qysdsbo.setYbjmsl(qysdsvo.getHdxx().getYbjmsl());
		// �걨��Ϣ
		qysdsbo.setFsdm(qysdsvo.getSbxx().getFsdm());
		qysdsbo.setJd(qysdsvo.getSbxx().getJd());
		qysdsbo.setNd(qysdsvo.getSbxx().getNd());
		qysdsbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSbrq())));
		qysdsbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssjsrq())));
		qysdsbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssksrq())));

		// �걨���ݡ�
		qysdsbo.setHznscyqyjdyjbl(qysdsvo.getSbsj().getHznscyqyjdyjbl());
		qysdsbo.setJmsdse(qysdsvo.getSbsj().getJmsdse());
		qysdsbo.setLrze(qysdsvo.getSbsj().getLrze());
		qysdsbo.setMbyqndks(qysdsvo.getSbsj().getMbyqndks());
		qysdsbo.setNstzjse(qysdsvo.getSbsj().getNstzjse());
		qysdsbo.setNstzzje(qysdsvo.getSbsj().getNstzzje());
		qysdsbo.setSbrqshow(qysdsvo.getSbsj().getSbrqshow());
		qysdsbo.setSjyyjdsdse(qysdsvo.getSbsj().getSjyyjdsdse());
		qysdsbo.setSysl(qysdsvo.getSbsj().getSysl());
		qysdsbo.setYbdsdse(qysdsvo.getSbsj().getYbdsdse());
		qysdsbo.setYnsdse(qysdsvo.getSbsj().getYnsdse());
		qysdsbo.setYnssde(qysdsvo.getSbsj().getYnssde());

		return qysdsbo;
	}

	/**
	 * ������Ϣ�� ��BO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public NsrJbxxdjbVO NsrJbxxconvert2XMLVO(NsrJbxxBO nsrJbxxBO,
			SWDJJBSJ djJbsj, String bblx) throws BaseException {

		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		NsrJbxxVO nsrjbxx = new NsrJbxxVO();

		// //////////////////////////////////
		// ���VO��

		nsrjbxx.setBbmsf(nsrJbxxBO.getBbmsf());
		nsrjbxx.setCjr(nsrJbxxBO.getCjr());
		nsrjbxx.setCjrq(nsrJbxxBO.getCjrq());
		nsrjbxx.setCwkjzddm(nsrJbxxBO.getCwkjzddm());
		nsrjbxx.setCwkjzddm_old(nsrJbxxBO.getCwkjzddm_old());
		nsrjbxx.setCwkjzdmc(nsrJbxxBO.getCwkjzddm_old());
		nsrjbxx.setGzglxsdm(nsrJbxxBO.getGzglxsdm());
		nsrjbxx.setGzglxsdm_old(nsrJbxxBO.getGzglxsdm_old());
		nsrjbxx.setGzglxsmc(nsrJbxxBO.getGzglxsmc());
		nsrjbxx.setJmlxdm(nsrJbxxBO.getJmlxdm());
		nsrjbxx.setJmlxdm_old(nsrJbxxBO.getJmlxdm_old());
		nsrjbxx.setJmlxmc(nsrJbxxBO.getJmlxmc());
		nsrjbxx.setJsjdm(nsrJbxxBO.getJsjdm());
		nsrjbxx.setJydz(nsrJbxxBO.getJydz());
		nsrjbxx.setLrr(nsrJbxxBO.getLrr());
		nsrjbxx.setLrrq(nsrJbxxBO.getLrrq());
		nsrjbxx.setLxdh(nsrJbxxBO.getLxdh());
		nsrjbxx.setNsrmc(nsrJbxxBO.getNsrmc());
		nsrjbxx.setNsrsbh(nsrJbxxBO.getNsrsbh());
		nsrjbxx.setSbnd(nsrJbxxBO.getSbnd());
		nsrjbxx.setSknd(nsrJbxxBO.getSknd());
		nsrjbxx.setSbrq(nsrJbxxBO.getSbrq());
		nsrjbxx.setSkssjsrq(nsrJbxxBO.getSkssjsrq());
		nsrjbxx.setSkssksrq(nsrJbxxBO.getSkssksrq());
		nsrjbxx.setSqspbh(nsrJbxxBO.getSqspbh());
		nsrjbxx.setSshydm(nsrJbxxBO.getSshydm());
		nsrjbxx.setSshymc(nsrJbxxBO.getSshymc());
		nsrjbxx.setSsjjlxdm(nsrJbxxBO.getSsjjlxdm());
		nsrjbxx.setSsjjlxmc(nsrJbxxBO.getSsjjlxmc());
		nsrjbxx.setSwjgzzjgdm(nsrJbxxBO.getSwjgzzjgdm());
		nsrjbxx.setSwjgzzjgmc(nsrJbxxBO.getSwjgzzjgmc());
		nsrjbxx.setVersion(nsrJbxxBO.getVersion());
		nsrjbxx.setXtjb(nsrJbxxBO.getXtjb());
		nsrjbxx.setZsfsdm(nsrJbxxBO.getZsfsdm());
		nsrjbxx.setZsfsmc(nsrJbxxBO.getZsfsmc());
		nsrjbxx.setZczbje(nsrJbxxBO.getZczbje());
		nsrjbxx.setZcze(nsrJbxxBO.getZcze());
		// ��ȡ˰Դ��ʶ
		nsrjbxx.setSybs(FriendHelper.getNsrSybs(djJbsj));

		//��ȡ�Ƿ���д�������������
		nsrjbxx.setQueryFlag(DoQueryData(nsrJbxxBO));
		
		// ��˰�˻�����Ϣ
		nsrJbxxdjbVO.setNsrjbxx(nsrjbxx);

		// XML�ĵ���Ϣ
		nsrJbxxdjbVO.setXsltVersion(Constant.CA_XSLTDM_NSRJBXXB);
		nsrJbxxdjbVO.setSchemaVersion(Constant.CA_SCHEMADM_NSRJBXXB);

		nsrJbxxdjbVO.setYwlx(Constant.CA_YWLXDM_NSRJBXXB);

		nsrJbxxdjbVO.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return nsrJbxxdjbVO;
	}

	/**
	 * ������Ϣ�� ��BO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public NsrJbxxBO NsrJbxxconvert2VO(NsrJbxxdjbVO nsrJbxxdjbVO)
			throws BaseException {

		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();
		NsrJbxxVO nsrjbxx = new NsrJbxxVO();
		nsrjbxx = nsrJbxxdjbVO.getNsrjbxx();

		// //////////////////////////////////
		// ���VO��

		nsrJbxxBO.setBbmsf(nsrjbxx.getBbmsf());
		nsrJbxxBO.setCjr(nsrjbxx.getCjr());
		nsrJbxxBO.setCjrq(nsrjbxx.getCjrq());
		nsrJbxxBO.setCwkjzddm(nsrjbxx.getCwkjzddm());
		nsrJbxxBO.setCwkjzddm_old(nsrjbxx.getCwkjzddm_old());
		nsrJbxxBO.setCwkjzdmc(nsrjbxx.getCwkjzddm_old());
		nsrJbxxBO.setGzglxsdm(nsrjbxx.getGzglxsdm());
		nsrJbxxBO.setGzglxsdm_old(nsrjbxx.getGzglxsdm_old());
		nsrJbxxBO.setGzglxsmc(nsrjbxx.getGzglxsmc());
		nsrJbxxBO.setJmlxdm(nsrjbxx.getJmlxdm());
		nsrJbxxBO.setJmlxdm_old(nsrjbxx.getJmlxdm_old());
		nsrJbxxBO.setJmlxmc(nsrjbxx.getJmlxmc());
		nsrJbxxBO.setJsjdm(nsrjbxx.getJsjdm());
		nsrJbxxBO.setJydz(nsrjbxx.getJydz());
		nsrJbxxBO.setLrr(nsrjbxx.getLrr());
		nsrJbxxBO.setLrrq(nsrjbxx.getLrrq());
		nsrJbxxBO.setLxdh(nsrjbxx.getLxdh());
		nsrJbxxBO.setNsrmc(nsrjbxx.getNsrmc());
		nsrJbxxBO.setNsrsbh(nsrjbxx.getNsrsbh());
		nsrJbxxBO.setSbnd(nsrjbxx.getSbnd());
		nsrJbxxBO.setSknd(nsrjbxx.getSknd());
		nsrJbxxBO.setSbrq(nsrjbxx.getSbrq());
		nsrJbxxBO.setSkssjsrq(nsrjbxx.getSkssjsrq());
		nsrJbxxBO.setSkssksrq(nsrjbxx.getSkssksrq());
		nsrJbxxBO.setSqspbh(nsrjbxx.getSqspbh());
		nsrJbxxBO.setSshydm(nsrjbxx.getSshydm());
		nsrJbxxBO.setSshymc(nsrjbxx.getSshymc());
		nsrJbxxBO.setSsjjlxdm(nsrjbxx.getSsjjlxdm());
		nsrJbxxBO.setSsjjlxmc(nsrjbxx.getSsjjlxmc());
		nsrJbxxBO.setSwjgzzjgdm(nsrjbxx.getSwjgzzjgdm());
		nsrJbxxBO.setSwjgzzjgmc(nsrjbxx.getSwjgzzjgmc());
		nsrJbxxBO.setVersion(nsrjbxx.getVersion());
		nsrJbxxBO.setXtjb(nsrjbxx.getXtjb());
		nsrJbxxBO.setZsfsdm(nsrjbxx.getZsfsdm());
		nsrJbxxBO.setZsfsmc(nsrjbxx.getZsfsmc());
		nsrJbxxBO.setZczbje(nsrjbxx.getZczbje());
		nsrJbxxBO.setZcze(nsrjbxx.getZcze());

		return nsrJbxxBO;
	}
	
    /**
	 * @decription ������ѯ�Ƿ���д�������������
	 * @author wangcy
	 * @modify_date 2013-12-10
	 * @param pData
	 * @throws BaseException
     * @throws com.ttsoft.framework.exception.BaseException 
	 */
	public String DoQueryData(NsrJbxxBO nsrJbxxBO)  
	{
		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		String  queryFlag = "";

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();
		try {
			// �������ݿ�����
			conn = DBResource.getConnection();
			// ��ȡ˰���������ȡ����   ��Ϊ���걨  �����ں�  Ϊ1
			String qh = "1";
			String nd = nsrJbxxBO.getSkssksrq().substring(0, 4);
			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM= '");

			querySqlZb.append(nsrJbxxBO.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(Constants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append(qh).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append(nd).append("' ");


			System.out.println(querySqlZb.toString());
			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM= '");
			querySqlCb.append(nsrJbxxBO.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append(qh).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append(nd).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			System.out.println(querySqlCb.toString());
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			if(queryRsZb.next()){ queryFlagZb = "1"; }
			if(queryRsCb.next()){ queryFlagCb = "1"; }

			if(queryFlagZb.equals("1") || queryFlagCb.equals("1")){
				queryFlag="1";
			}else{
				queryFlag="0";
			}
			
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			this.release(queryRsZb, queryPstmtZb);
			this.release(queryRsCb, queryPstmtCb);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return queryFlag;
	}
	
    /**
	 * @description �ͷ����ݿ���Դ
	 * @author wangcy
	 * @modify_date 2013-12-08
	 * @param rs
	 * @param stmt
	 * @param con
	 */
    public void release(ResultSet rs, Statement stmt){
		if(rs!=null){
			  try{
			      rs.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(stmt!=null){
			  try{
			      stmt.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
	}
}
