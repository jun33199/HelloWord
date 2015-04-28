package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.helper;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;


import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.helper.QysdsjbBaseHelper;
import com.lscdz.qysds.application.qysdsjb2014.base.util.QysdsBaseUtil;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.ReportVo;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo.CzzssdsjbSbbVo;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
/**
 * ���������걨��dao��
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-22 ����11:31:42
 */
public class CzzsjbSbbHelper extends QysdsjbBaseHelper{

	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";
	
	@Override
	public void delete(QysdsjbBaseVo qysdsjbBaseVo)throws FrameException{
		super.delete(qysdsjbBaseVo);
	}
	@Override
	public void save(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		qysdsjbBaseVo.setZsfsdm("03");//��������
		super.save(qysdsjbBaseVo);	
	}
	@Override
	public void query(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		CzzssdsjbSbbVo czzssdsjbSbbVo=(CzzssdsjbSbbVo)qysdsjbBaseVo;
		Connection conn = null;
		try {		
		// ��ȡ˰����������
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(czzssdsjbSbbVo.getSkssjsrq()));
		String nd = czzssdsjbSbbVo.getSkssksrq().substring(0, 4);
		// ���ü���
		czzssdsjbSbbVo.setQh(jd);
		// �������
		czzssdsjbSbbVo.setSknd(nd);
		// ����form��������������
		conn = ResourceManager.getConnection();
//		conn = ConnFactoryTest.getConnect();
		czzssdsjbSbbVo = (CzzssdsjbSbbVo) QysdsBaseUtil.queryDjxxByInterfaceDJ(conn, czzssdsjbSbbVo);
		// �����ҵ�Ǽǻ�����Ϣ
		Djjbsj djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, czzssdsjbSbbVo.getJsjdm());
		
		Timestamp skssksrq = DateUtils.getDateTime(czzssdsjbSbbVo.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			czzssdsjbSbbVo.setIsXky("Y");
        }else{
        	czzssdsjbSbbVo.setIsXky("N");
        }
		QysdsBaseUtil qysdsUtil2014=new QysdsBaseUtil();
		//��ȡ�Ƿ����¿���  �ǣ�Y ��N 
		czzssdsjbSbbVo.setSfxkh(qysdsUtil2014.getSfxkh(czzssdsjbSbbVo.getSknd(), djsj, czzssdsjbSbbVo.getQh()));
		//��ȡ˰�����������������һ������շ�ʽ
		czzssdsjbSbbVo.setSyndZsfsdm(qysdsUtil2014.getSyndZsfsDm(czzssdsjbSbbVo.getJsjdm(), czzssdsjbSbbVo.getSknd(),czzssdsjbSbbVo.getQh()));
		//��ȡ˰�����������������һ��ȵĻ�������걨��Ϣ ������9��25 ������45��46��47
		Map map=qysdsUtil2014.getSyndHsqjSbxx(czzssdsjbSbbVo.getSyndZsfsdm(),czzssdsjbSbbVo.getJsjdm(), czzssdsjbSbbVo.getSknd(),czzssdsjbSbbVo.getQh());
		czzssdsjbSbbVo.setSyndZbh6(map.get("syndZbh6").toString());
		czzssdsjbSbbVo.setSyndZbh25(map.get("syndZbh25").toString());
		czzssdsjbSbbVo.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
		this.getHdxx(czzssdsjbSbbVo);
		/* ���շ�ʽ */
		String zsfs = czzssdsjbSbbVo.getZsfsdm();

		System.out.println(czzssdsjbSbbVo.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

		if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
			throw new FrameException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
		}
		if (!(QysdsJb2014Contant.ZSFSDM_CZZS.equals(zsfs))) {
			throw new FrameException("����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼����Ӧ�����걨��");
		}
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("��ѯ���ݳ���");
		}
		czzssdsjbSbbVo.setReportData(super.queryReportData(this.getReportVo(czzssdsjbSbbVo)));
	}
	@Override
	protected ReportVo getReportVo(QysdsjbBaseVo qysdsjbBaseVo) {
		ReportVo reportVo=new ReportVo();
		reportVo.setBbqlx(qysdsjbBaseVo.getBbqlx());
		reportVo.setJsjdm(qysdsjbBaseVo.getJsjdm());
		reportVo.setNd(qysdsjbBaseVo.getSknd());
		reportVo.setQh(qysdsjbBaseVo.getQh());
		reportVo.setSkssjsrq(qysdsjbBaseVo.getSkssjsrq());
		reportVo.setSkssksrq(qysdsjbBaseVo.getSkssksrq());
		reportVo.setTid(QysdsJb2014Contant.TABLE_NAME_CZQYSDSJB_2008);
		reportVo.setTname(QysdsJb2014Contant.TABLE_NAME_CZQYSDSJB_2008);
		reportVo.setReportData(qysdsjbBaseVo.getReportData());
		reportVo.setReportType(ReportsInterface.REPROTTYPE_REPORTDATA);
		return reportVo;
	}


	/**
	 * ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
	 *
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(CzzssdsjbSbbVo form) throws FrameException {
		Connection conn=null;
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		// Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// ���걨ҳ��ȡ���걨���ں�˰����������
		Timestamp sbrq = DateUtils.getDateTime(form.getSkssjsrq());

		// Map getsbjd = this.quarterSkssrq(sbrq);
		Timestamp skssksrq = DateUtils.getDateTime(form.getSkssksrq());
		Timestamp skssjsrq = DateUtils.getDateTime(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq��" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq��" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq��" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh��" + form.getQh());

//		ServiceProxy proxy = new ServiceProxy();

		String bblx = form.getBbqlx();
		String jsjdm = form.getJsjdm();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
	//	Connection conn = null;
	//	Statement st = null;
	//	ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {
			conn=ResourceManager.getConnection();
			if (bblx.equals(QysdsJb2014Contant.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq, skssjsrq,
						QysdsJb2014Contant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(QysdsJb2014Contant.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
				/* ���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ */
				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* �ںŲ���Ϊ�գ����Ϊ���׳��쳣 */
					throw new FrameException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
				}
				if ("4".equals(form.getQh())) {
					qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq,
							skssjsrq, QysdsJb2014Contant.SFGL_QYSDS_BBFS_NB);
				} else {
					qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq,
							skssjsrq, QysdsJb2014Contant.SFGL_QYSDS_BBFS_JB);
                    //����Zsfs
//                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
//                    qysdsSet.setZsfs(zsfs);
				}
			}

		} catch (FrameException e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			form.setZsfsdm(zsfs.getZsfsdm() == null ? QysdsJb2014Contant.ZSFSDM_CZZS
					: zsfs.getZsfsdm());
		} else {
			form.setZsfsdm(QysdsJb2014Contant.ZSFSDM_CZZS);
		}

		/* ���¼�����ҵ�϶����� */
		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// ��ֵ
		form.setCyl("0");
		form.setXzqy("0");
		form.setDezsse("0.00");
		form.setYbjmsl("0.00");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(QysdsJb2014Contant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					form.setJmzg("1"); // �м����ʸ�
				}
				form.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(QysdsJb2014Contant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				form.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(QysdsJb2014Contant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			form.setJmzg("1"); // �м����ʸ�
		} else if (form.getSsjjlx().equals(QysdsJb2014Contant.JITIQIYE_CODE)) {
			// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
			if (qysdsSet.isXzqy()) {
				form.setXzqy("1");
				form.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			form.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		form.setQyzslx(qyzssllx);
		form.setSysl(qyzssl);
	}

}
