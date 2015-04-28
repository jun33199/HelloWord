package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import java.util.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsnb;
import com.ttsoft.bjtax.shenbao.model.domain.Lygf;
import com.ttsoft.bjtax.shenbao.model.domain.Qyjbcwzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttwh;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.QycwzbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.SbsjlistVO;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.bjtax.shenbao.constant.*;

/**
 * ��ҵ����˰helper
 */
public class QysdsHelper {

	// ˰���������ڵ����ڸ�ʽ
	private final String SKSSRQ_DATE_SYMBOL = "yyyy-MM-dd";

	// ��ҵ����˰˰��
	private final String QYSDS_SL = "0.33";

	/**
	 * ������ҵ����˰�걨form�е�����ת���ɺ�̨process������걨��list��
	 * 
	 * 1���걨����Ϊϵͳ��ǰ���� 2������˰������ʱ�� 3����UserData�л�ü�������롢���ơ�����˰����صȵǼ�����
	 * 4���ӵǼ���ȡ�������н������������ 5��¼���걨���ں�������� 6������list��������
	 * 
	 * @param userData
	 *            �û���¼����
	 * @param form
	 *            ��ҵ����˰�걨ActionForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ�Ǽ���Ϣ
	 * @return �걨list
	 * @throws BaseException
	 */
	public QysdsnbData getQysdsnbData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) throws BaseException {

		// ���������
		String jsjdm = userData.yhid;

		String[] hcs = form.getNbhc();
		String hc = null;
		String bqljs = null;

		List nbList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = String.valueOf(curTime.getYear() - 1 + 1900);

		Timestamp sbrq = TinyTools.second2Day(curTime);

		String jmse = null;

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {
				Qysdsnb nb = new Qysdsnb();
				nbList.add(nb);

				nb.setJsjdm(jsjdm); // ���������
				nb.setNd(strYear); // ���
				hc = form.getNbhc()[i];
				nb.setHc(hc); // �д�

				// �����ۼ���
				bqljs = form.getNbbqljs()[i];
				if (bqljs != null && bqljs.trim().length() > 0) {
					nb.setBqljs(new BigDecimal(bqljs));
				}

				if (hc.equals("81")) // 81�дΣ�����˰��
				{
					jmse = bqljs;
				}

				nb.setSbrq(sbrq); // �걨����
				nb.setSkssksrq(getSkssrq(form.getSkssksrq())); // ˰������ʱ��
				nb.setSkssjsrq(getSkssrq(form.getSkssjsrq())); // ˰������ʱ��
				nb.setCjrq(curTime); // ����ʱ��
				nb.setLrrq(curTime); // �������
				nb.setLrr(userData.yhid); // ¼���˴���
				nb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰��������
				nb.setZgswjgzzjgdm(djInfo.getSwjgzzjgdm()); // ����˰����ش���
				nb.setFsdm(CodeConstant.FSDM_WSSB); // �Ǽ��걨��ʽ����
				nb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2)); // ���ش���
			}
		}

		nbData.getNbData().setNbData(nbList);

		// ׼����������
		ServiceProxy proxy = new ServiceProxy();
		Map ssrq = Skssrq.yearSkssrq(curTime);
		Date ksrq = (Date) ssrq.get(Skssrq.SKSSKSRQ);
		Date jsrq = (Date) ssrq.get(Skssrq.SKSSJSRQ);
		BigDecimal ybjme = proxy.getQysdsInfo(jsjdm, curTime, ksrq, jsrq, "01")
				.getYbjme();

		Jm jm = null;
		if (ybjme != null && jmse != null && !jmse.equals("")) {
			jm = new Jm();
			jm.setJsjdm(jsjdm);
			jm.setJmlx(CodeConstant.JMLX_SP); // ��������
			jm.setSzsmdm(SzsmdmConstant.QYSDS_SM);
			jm.setSbrq(sbrq);
			jm.setLrrq(curTime);
			jm.setJsje(new BigDecimal(jmse));
			jm.setKssl(null);
			jm.setJmse(jm.getJsje());
			jm.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
			jm.setLrr(jsjdm);
			jm.setFsdm(CodeConstant.FSDM_WSSB);

			jm.setDjzclxdm(djInfo.getDjzclxdm());
			jm.setGjbzhydm(djInfo.getGjbzhydm());
			jm.setYskmdm(null); // ��processor��ֵ
			jm.setYsjcdm(null); // ��processor��ֵ
			jm.setSkssksrq(getSkssrq(form.getSkssksrq())); // ˰������ʱ��
			jm.setSkssjsrq(getSkssrq(form.getSkssjsrq())); // ˰������ʱ��
			jm.setNd(strYear);
			jm.setCjrq(curTime);
			jm.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));

			String jmxmdm = proxy.getJmsbs(jsjdm, SzsmdmConstant.QYSDS, ksrq,
					jsrq);
			jm.setJmxmdm(jmxmdm); // ������Ŀ����
		}

		nbData.setJm(jm);

		return nbData;
	}

	/**
	 * ������ҵ����˰�걨form�е�����ת���ɺ�̨process�������ҵ��λlist�� list 0; 1���걨����Ϊϵͳ��ǰ����
	 * 2������˰������ʱ�� 3����UserData�л�ü�������롢���ơ�����˰����صȵǼ����� 4���ӵǼ���ȡ�������н������������
	 * 5��¼���걨���ں�������� 6��������ҵ��λ����list, 7��������׼�ĺ�list 8������ҵ��λ�걨���ݺ���׼�ĺ����ݹ����һ�����󲢷���
	 * 
	 * @param userData
	 *            �û���¼����
	 * @param form
	 *            ��ҵ��λQysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ�Ǽ���Ϣ
	 * @return ��ҵ��λlist
	 */
	public QysdsnbData getSydwData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// ���������
		String jsjdm = userData.yhid;

		String[] hcs = form.getSydwhc();
		String hc = null;
		String bqljs = null;
		String xmmc = null;

		Sydwshttsrzb zbdata = null;
		List mxList = null;
		List whList = null;

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		String qxdm = djInfo.getSwjgzzjgdm().substring(0, 2);

		// ��������
		zbdata = new Sydwshttsrzb();
		zbdata.setJsjdm(jsjdm); // ���������
		zbdata.setNd(strYear); // ���
		zbdata.setSfyyssr(form.getSfyyssr());

		zbdata.setSbrq(TinyTools.second2Day(curTime)); // �걨����

		// ˰������ʱ��
		zbdata.setSkssksrq(getSkssrq(form.getSkssksrq()));
		zbdata.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

		zbdata.setCjrq(curTime); // ����ʱ��
		zbdata.setLrrq(curTime); // �������
		zbdata.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰�������֯��������
		zbdata.setLrr(userData.yhid); // ¼���˴���
		zbdata.setFsdm(CodeConstant.FSDM_WSSB); // �Ǽ��걨��ʽ����
		zbdata.setQxdm(qxdm);

		mxList = new ArrayList();
		whList = new ArrayList();

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {
				Sydwshttsrmx mx = new Sydwshttsrmx();
				mxList.add(mx);

				// ���������
				mx.setJsjdm(jsjdm);

				// ���
				mx.setNd(strYear);

				// �д�
				mx.setHc(form.getSydwhc()[i]);

				// �����ۼ���
				bqljs = form.getSydwbqljs()[i];
				if (bqljs != null && bqljs.trim().length() > 0) {
					mx.setBqljs(new BigDecimal(bqljs));
				}

				mx.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());

				mx.setQxdm(qxdm);

				mx.setLrrq(curTime);
				mx.setCjrq(curTime);
			}
		}

		String wh = null;

		// ��3����׼�ĺ�
		wh = form.getSydwpzwh3();
		if (wh != null && wh.trim().length() > 0) {
			Sydwshttwh wh3 = new Sydwshttwh();
			wh3.setJsjdm(jsjdm);
			wh3.setHc("3");
			wh3.setNd(strYear);
			wh3.setWh(wh);
			wh3.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
			wh3.setCjrq(curTime);
			wh3.setLrrq(curTime);
			wh3.setQxdm(qxdm);

			whList.add(wh3);
		}

		// ��4����׼�ĺ�
		wh = form.getSydwpzwh4();
		if (wh != null && wh.trim().length() > 0) {
			Sydwshttwh wh4 = new Sydwshttwh();
			wh4.setJsjdm(jsjdm);
			wh4.setHc("4");
			wh4.setNd(strYear);
			wh4.setWh(wh);
			wh4.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
			wh4.setCjrq(curTime);
			wh4.setLrrq(curTime);
			wh4.setQxdm(qxdm);

			whList.add(wh4);
		}

		// ��5����׼�ĺ�
		wh = form.getSydwpzwh5();
		if (wh != null && wh.trim().length() > 0) {
			Sydwshttwh wh5 = new Sydwshttwh();
			wh5.setJsjdm(jsjdm);
			wh5.setHc("5");
			wh5.setNd(strYear);
			wh5.setWh(wh);
			wh5.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
			wh5.setCjrq(curTime);
			wh5.setLrrq(curTime);
			wh5.setQxdm(qxdm);

			whList.add(wh5);
		}

		// ����ҵ��λ��ֵ
		nbData.getSydwData().setSydwshttsrzb(zbdata);
		nbData.getSydwData().setMxList(mxList);
		nbData.getSydwData().setWhList(whList);

		return nbData;
	}

	/**
	 * ������ҵ����˰�걨form�е�����ת���ɺ�̨process����� ��Ӫ�ɷ�list��
	 * 
	 * 1���걨����Ϊϵͳ��ǰ���� 2������˰������ʱ�� 3����UserData�л�ü�������롢���ơ�����˰����صȵǼ�����
	 * 4���ӵǼ���ȡ�������н������������ 5��¼���걨���ں�������� 6������Vopackage�������� 7������list��������
	 * 
	 * @param userData
	 *            �û���¼����
	 * @param form
	 *            ��Ӫ�ɷ�QysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ�Ǽ���Ϣ
	 * @return ��Ӫ�ɷ�list
	 */
	public QysdsnbData getLygfData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// ���������
		String jsjdm = userData.yhid;

		String[] hcs = form.getQycwzbncs();
		String hc = null;
		String nms = null;
		String ncs = null;
		String xmmc = null;

		List lygfList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		// �������
		int[] xh = new int[5];
		// �������
		String qylxdm = null;
		int lxdm;

		if (form.getLygfdjzclxdm() != null) {
			int size = form.getLygfdjzclxdm().length;
			for (int i = 0; i < size; i++) {
				Lygf lygf = new Lygf();
				lygfList.add(lygf);

				// ���������
				lygf.setJsjdm(jsjdm);

				// ���
				lygf.setNd(strYear);

				// ��ҵ���ʹ���
				qylxdm = form.getLygfdjzclxdm()[i];
				lygf.setFl(qylxdm);

				// ���
				lxdm = Integer.parseInt(qylxdm);
				lygf.setXh(new BigDecimal(xh[lxdm]));
				xh[lxdm]++;

				// ��λ����
				lygf.setDwmc(form.getLygfqymc()[i]);

				// ˰��
				lygf.setSl(new BigDecimal(form.getLygfsl()[i]));

				// ����
				lygf.setLr(new BigDecimal(form.getLygflrgx()[i]));

				// Ӧ��˰���ö�
				lygf.setYnssde(new BigDecimal(form.getLygfynssde()[i]));

				// Ӧ������˰��
				lygf.setYnsdse(new BigDecimal(form.getLygfynsdse()[i]));

				// ˰�տ۳���
				lygf.setSskce(new BigDecimal(form.getLygfsskce()[i]));

				// Ӧ������˰��
				lygf.setYbsdse(new BigDecimal(form.getLygfybsdse()[i]));

				// �걨����
				lygf.setSbrq(TinyTools.second2Day(curTime));

				// ˰������ʱ��
				lygf.setSkssksrq(getSkssrq(form.getSkssksrq()));
				lygf.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				lygf.setCjrq(curTime); // ����ʱ��
				lygf.setLrrq(curTime); // �������
				lygf.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰�������֯��������
				lygf.setLrr(userData.yhid); // ¼���˴���
				lygf.setNsrmc(form.getQymc()); // ��˰������
				lygf.setQylxdh(form.getQylxdh()); // ��ҵ��ϵ�绰
				lygf.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
				lygf.setFsdm(CodeConstant.FSDM_WSSB);
			}
		}

		nbData.getLygfData().setLygfData(lygfList);

		return nbData;
	}

	/**
	 * ������ҵ����˰�걨form�е�����ת���ɺ�̨process����Ĳ���ָ��list��
	 * 
	 * 1���걨����Ϊϵͳ��ǰ���� 2������˰������ʱ�� 3����UserData�л�ü�������롢���ơ�����˰����صȵǼ�����
	 * 4���ӵǼ���ȡ�������н������������ 5��¼���걨���ں�������� 6������list��������
	 * 
	 * @param userData
	 *            �û���¼����
	 * @param form
	 *            ��ҵ����ָ��QysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ�Ǽ���Ϣ
	 * @return ����ָ��list
	 */
	public QysdsnbData getCwzbData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// ���������
		String jsjdm = userData.yhid;

		String[] hcs = form.getQycwzbncs();
		String hc = null;
		String nms = null;
		String ncs = null;
		String xmmc = null;

		List cwzbList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {
				Qyjbcwzb cwzb = new Qyjbcwzb();
				cwzbList.add(cwzb);

				// ���������
				cwzb.setJsjdm(jsjdm);

				// ���
				cwzb.setNd(strYear);

				// �д�
				cwzb.setHc(form.getQycwzbhc()[i]);

				// �����
				ncs = form.getQycwzbncs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}

				// ��ĩ��
				nms = form.getQycwzbnms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// �걨����
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// ˰������ʱ��
				cwzb.setSkssksrq(getSkssrq(form.getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				cwzb.setCjrq(curTime); // ����ʱ��
				cwzb.setLrrq(curTime); // �������
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰�������֯��������
				cwzb.setLrr(userData.yhid); // ¼���˴���
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // �Ǽ��걨��ʽ����
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	/**
	 * ������ҵ����ָ���걨form�е�����ת���ɺ�̨process����Ĳ���ָ��list��
	 * 
	 * 1���걨����Ϊϵͳ��ǰ���� 2������˰������ʱ�� 3����UserData�л�ü�������롢���ơ�����˰����صȵǼ�����
	 * 4���ӵǼ���ȡ�������н������������ 5��¼���걨���ں�������� 6������list��������
	 * 
	 * @param userData
	 *            �û���¼����
	 * @param form
	 *            ��ҵ����ָ��QycwzbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ�Ǽ���Ϣ
	 * @return ����ָ��list
	 */
	public QysdsnbData getCwzbData(UserData userData, QycwzbVO qycw,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// ���������
		String jsjdm = userData.yhid;
		SbsjlistVO sbsjlist=qycw.getSbsjlist();
		String[] hcs = sbsjlist.getSbsj().getNcs();	
		String hc = null;
		String nms = null;
		String ncs = null;
		String xmmc = null;

		List cwzbList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {		
				Qyjbcwzb cwzb = new Qyjbcwzb();
				cwzbList.add(cwzb);

				// ���������
				cwzb.setJsjdm(jsjdm);

				// ���
				cwzb.setNd(strYear);

				// �д�
				cwzb.setHc(sbsjlist.getSbsj().getHc()[i]);

				// �����
				ncs = sbsjlist.getSbsj().getNcs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}
				// ��ĩ��
				nms =sbsjlist.getSbsj().getNms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// �걨����
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// ˰������ʱ��
				cwzb.setSkssksrq(getSkssrq(qycw.getSbxx().getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(qycw.getSbxx().getSkssjsrq()));

				cwzb.setCjrq(curTime); // ����ʱ��
				cwzb.setLrrq(curTime); // �������
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰�������֯��������
				cwzb.setLrr(userData.yhid); // ¼���˴���
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // �Ǽ��걨��ʽ����
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	public QysdsnbData getCwzbData(UserData userData, QycwzbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// ���������
		String jsjdm = userData.yhid;

		String[] hcs = form.getQycwzbncs();
		String hc = null;
		String nms = null;
		String ncs = null;
		String xmmc = null;

		List cwzbList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {
				Qyjbcwzb cwzb = new Qyjbcwzb();
				cwzbList.add(cwzb);

				// ���������
				cwzb.setJsjdm(jsjdm);

				// ���
				cwzb.setNd(strYear);

				// �д�
				cwzb.setHc(form.getQycwzbhc()[i]);

				// �����
				ncs = form.getQycwzbncs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}
				// ��ĩ��
				nms = form.getQycwzbnms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// �걨����
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// ˰������ʱ��
				cwzb.setSkssksrq(getSkssrq(form.getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				cwzb.setCjrq(curTime); // ����ʱ��
				cwzb.setLrrq(curTime); // �������
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // ˰�������֯��������
				cwzb.setLrr(userData.yhid); // ¼���˴���
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // �Ǽ��걨��ʽ����
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	/**
	 * ������ҵ����˰�걨������ת��Ϊform�е�����
	 * 
	 * 1�����걨����defineList��ֵ 2����Ŀ���ơ��дΡ���š������ۼ�����ֵ
	 * 
	 * @param nbdata
	 *            QysdsnbData.NBData
	 * @param form
	 *            ��ҵ����˰�걨QysdsnbForm
	 */
	public void setQysdsnbData(QysdsnbData.NBData nbdata, QysdsnbForm form) {
		// ���������
		form.setNbDefineList(nbdata.getDefineList());

		// ��������
		List data = nbdata.getNbData();
		if (data != null && data.size() > 0) {
			int size = data.size();
			String[] hc = new String[size];
			String[] bqljs = new String[size];

			for (int i = 0; i < data.size(); i++) {
				Qysdsnb item = (Qysdsnb) data.get(i);
				hc[i] = item.getHc();
				bqljs[i] = (item.getBqljs() == null) ? "" : String.valueOf(item
						.getBqljs());
			}

			// �д�
			form.setNbhc(hc);
			// �����ۼ���
			form.setNbbqljs(bqljs);
		}
	}

	/**
	 * ��������ҵ��λ������ת��Ϊform�е�����
	 * 
	 * 1�����걨����defineList��ֵ 2����Ŀ���ơ��дΡ���š������ۼ�����ֵ 3������׼�ĺ�3��4��5��ֵ
	 * 
	 * @param sydwData
	 *            ��ҵ��λQysdsnbData.SydwData
	 * @param form
	 *            ��ҵ����˰�걨ActionForm
	 */
	public void setSydwData(QysdsnbData.SydwData sydwData, QysdsnbForm form) {
		// ��ҵ��λ�걨����Ĵ��������
		form.setSydwsbbDefineList(sydwData.getDefineList());

		if (sydwData.getSydwshttsrzb() != null) {
			// �Ƿ���Ӧ˰����
			String sfyyssr = sydwData.getSydwshttsrzb().getSfyyssr();
			if (sfyyssr != null)
				form.setSfyyssr(sfyyssr);

			// �걨����
			List sbdata = sydwData.getMxList();
			if (sbdata != null && sbdata.size() > 0) {
				int size = sbdata.size();
				String[] hc = new String[size];
				String[] bqljs = new String[size];

				for (int i = 0; i < sbdata.size(); i++) {
					Sydwshttsrmx item = (Sydwshttsrmx) sbdata.get(i);
					hc[i] = item.getHc();
					bqljs[i] = (item.getBqljs() == null) ? "" : String
							.valueOf(item.getBqljs());
				}

				// �д�
				form.setSydwhc(hc);
				// �����ۼ���
				form.setSydwbqljs(bqljs);
			}

			// �ĺ�����
			List whdata = sydwData.getWhList();
			if (whdata != null && whdata.size() > 0) {
				for (int i = 0; i < whdata.size(); i++) {
					Sydwshttwh item = (Sydwshttwh) whdata.get(i);
					String hc = item.getHc();
					String wh = item.getWh();
					if (hc.equals("3")) {
						form.setSydwpzwh3(wh);
					} else if (hc.equals("4")) {
						form.setSydwpzwh4(wh);
					} else if (hc.equals("5")) {
						form.setSydwpzwh5(wh);
					}
				}
			}
		}
	}

	/**
	 * ������Ӫ�ɷݵ�����ת��Ϊform�е�����
	 * 
	 * ����ҵ���ʹ��롢˰�ʡ������Ϣ�����롢��ҵ���ơ�Ӧ��˰���öӦ������˰����� ����˰�����ҵ˰�ʷ���form��
	 * 
	 * @param lygfData
	 *            ��Ӫ�ɷ�����QysdsnbData.LygfData
	 * @param form
	 *            ��ҵ����˰�걨QysdsnbForm
	 */
	public void setLygfData(QysdsnbData.LygfData lygfData, QysdsnbForm form) {
		List data = lygfData.getLygfData();
		if (data != null && data.size() > 0) {
			int size = data.size();

			String[] lygfdjzclxdm = new String[size];

			String[] lygfqymc = new String[size];

			String[] lygfsl = new String[size];

			String[] lygfybsdse = new String[size];

			String[] lygfynsdse = new String[size];

			String[] lygfynssde = new String[size];

			String[] lygflrgx = new String[size];
			String[] lygfsskce = new String[size];

			for (int i = 0; i < size; i++) {
				Lygf item = (Lygf) data.get(i);

				lygfdjzclxdm[i] = item.getFl();
				lygfqymc[i] = item.getDwmc();
				lygfsl[i] = (item.getSl() == null) ? "" : String.valueOf(item
						.getSl());
				lygfybsdse[i] = (item.getYbsdse() == null) ? "" : String
						.valueOf(item.getYbsdse());
				lygfynsdse[i] = (item.getYnsdse() == null) ? "" : String
						.valueOf(item.getYnsdse());
				lygfynssde[i] = (item.getYnssde() == null) ? "" : String
						.valueOf(item.getYnssde());
				lygflrgx[i] = (item.getLr() == null) ? "" : String.valueOf(item
						.getLr());
				lygfsskce[i] = (item.getSskce() == null) ? "" : String
						.valueOf(item.getSskce());
			}

			form.setLygfdjzclxdm(lygfdjzclxdm);
			form.setLygfqymc(lygfqymc);
			form.setLygfsl(lygfsl);
			form.setLygfybsdse(lygfybsdse);
			form.setLygfynsdse(lygfynsdse);
			form.setLygfynssde(lygfynssde);
			form.setLygflrgx(lygflrgx);
			form.setLygfsskce(lygfsskce);
		}
	}

	/**
	 * ������ҵ����ָ�������ת��Ϊform�е�����
	 * 
	 * 1�����걨����defineList��ֵ 2����Ŀ���ơ��дΡ���š����������ĩ����ֵ
	 * 
	 * @param cwzbData
	 *            ����ָ������
	 * @param form
	 *            ��ҵ����˰�걨ActionForm
	 */
	public void setCwzbData(QysdsnbData.CwzbData cwzbData, QysdsnbForm form) {
		// ���������
		form.setQycwzbDefineList(cwzbData.getDefineList());

		// ��������
		List data = cwzbData.getCwzbData();
		if (data != null && data.size() > 0) {
			int size = data.size();
			// �д�
			String[] hc = new String[size];
			// �����
			String[] ncs = new String[size];
			// ����
			String[] nms = new String[size];

			for (int i = 0; i < data.size(); i++) {
				Qyjbcwzb item = (Qyjbcwzb) data.get(i);
				hc[i] = item.getHc();
				ncs[i] = (item.getNcs() == null) ? "" : String.valueOf(item
						.getNcs());
				nms[i] = (item.getNms() == null) ? "" : String.valueOf(item
						.getNms());
			}

			// �д�
			form.setQycwzbhc(hc);
			// �����
			form.setQycwzbncs(ncs);
			// ����
			form.setQycwzbnms(nms);
		}
	}

	/**
	 * ������ҵ����ָ�������ת��Ϊform�е�����
	 * 
	 * 1�����걨����defineList��ֵ 2����Ŀ���ơ��дΡ���š����������ĩ����ֵ
	 * 
	 * @param cwzbData
	 *            ����ָ������
	 * @param form
	 *            ��ҵ����˰�걨ActionForm
	 */
	public void setCwzbData(QysdsnbData.CwzbData cwzbData, QycwzbForm form) {
		// ���������
		form.setQycwzbDefineList(cwzbData.getDefineList());

		// ��������
		List data = cwzbData.getCwzbData();
		if (data != null && data.size() > 0) {
			int size = data.size();
			// �д�
			String[] hc = new String[size];
			// �����
			String[] ncs = new String[size];
			// ����
			String[] nms = new String[size];

			for (int i = 0; i < data.size(); i++) {
				Qyjbcwzb item = (Qyjbcwzb) data.get(i);
				hc[i] = item.getHc();
				ncs[i] = (item.getNcs() == null) ? "" : String.valueOf(item
						.getNcs());
				nms[i] = (item.getNms() == null) ? "" : String.valueOf(item
						.getNms());
			}

			// �д�
			form.setQycwzbhc(hc);
			// �����
			form.setQycwzbncs(ncs);
			// ��ĩ��
			form.setQycwzbnms(nms);
		}
	}

	/**
	 * ����˰�ѹ�������ݣ�����˰�ʺ��������ʸ�
	 * 
	 * @param userData
	 *            UserData
	 * @param form
	 *            QysdsnbForm
	 * @param swdjjbsj
	 *            �Ǽ���Ϣ
	 * @throws BaseException
	 */
	public void setSfglData(UserData userData, QysdsnbForm form,
			SWDJJBSJ swdjjbsj) throws BaseException {
		// ��ҵ��˰��˰������
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		/*
		 * ��Ӫ�ɷ���ҵ˰�� �����ҵ��˰��˰������Ϊ��1�������¼�����ҵ˰�ʣ���Ϊqyzssl ����Ϊqysdssl
		 */
		String lygfqysl = QYSDS_SL;

		// ���������
		String jsjdm = userData.yhid;
		// ��ǰʱ��
		Date sbrq = new Date();

		Map ssrq = Skssrq.yearSkssrq(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = proxy.getQysdsInfo(jsjdm, // ���������
				sbrq, // �걨����
				(Date) ssrq.get(Skssrq.SKSSKSRQ), // ˰��������ʼ����
				(Date) ssrq.get(Skssrq.SKSSJSRQ), "01"); // ˰��������������

		// 1����ѯ�Ƿ��Ǹ��¼�����ҵ
		Date gxqyrdrq = qysdsSet.getGxjsqy();
		if (gxqyrdrq != null) {
			qyzssllx = "1";
			qyzssl = "0.15";
			// ��Ӫ�ɷ���ҵ˰��Ϊ���¼�����ҵ��˰��
			lygfqysl = qyzssl;

			form.setGxqy("1");
		}
		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				form.setIsXzqy("1");
			}
		}

		// 2����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ����������
				qyzssllx = "2";
				qyzssl = zsfs.getCyl();
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				qyzssl = zsfs.getDe();
			}
		}

		form.setLygfqysl(lygfqysl);
		form.setQyzssllx(qyzssllx);
		form.setQyzssl(qyzssl);

		// �����ʸ�
		form.setSpzg24(qysdsSet.getCcss()); // �Ʋ���ʧ�۳���
		// form.setSpzg63(qysdsSet.getNbyqndks()); //�ֲ���ǰ��ȿ���
		// form.setSpzg77(qysdsSet.getSxdk()); //���µֿ�
		form.setSpzg81(qysdsSet.getYbjme()); // ����������˰��
		form.setSpzg82(qysdsSet.getJsgzgcsb()); // ������������豸Ͷ�ʵ���˰��
		form.setSpzg69(qysdsSet.getJsgzgcsb()); // ��˰�ļ���ת������
	}

	/**
	 * ����˰����������
	 * 
	 * @param sbrq
	 *            Timestamp
	 * @param form
	 *            QysdsnbForm
	 */
	public void setSkssrq(Timestamp sbrq, QysdsnbForm form) {
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(sbrq);
		DateFormat df = new SimpleDateFormat(SKSSRQ_DATE_SYMBOL);
		String skssksrq = df.format((Timestamp) map
				.get(com.ttsoft.bjtax.shenbao.util.Skssrq.SKSSKSRQ));
		form.setSkssksrq(skssksrq);
		String skssjsrq = df.format((Timestamp) map
				.get(com.ttsoft.bjtax.shenbao.util.Skssrq.SKSSJSRQ));
		form.setSkssjsrq(skssjsrq);
	}

	/**
	 * ����˰����������
	 * 
	 * @param sbrq
	 *            Timestamp
	 * @param form
	 *            QycwzbForm
	 */
	public void setSkssrq(Timestamp sbrq, QycwzbForm form) {
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(sbrq);
		DateFormat df = new SimpleDateFormat(SKSSRQ_DATE_SYMBOL);
		String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
		form.setSkssksrq(skssksrq);
		String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
		form.setSkssjsrq(skssjsrq);
	}

	/**
	 * ȡ��˰����������
	 * 
	 * @param skssrq
	 *            ˰����������
	 * @return Timestamp
	 */
	public Timestamp getSkssrq(String skssrq) {
		DateFormat df = new SimpleDateFormat(SKSSRQ_DATE_SYMBOL);
		Date d = null;
		try {
			d = df.parse(skssrq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return new Timestamp(d.getTime());
	}

	/**
	 * ���õǼ�����
	 * 
	 * @param djsj
	 *            SWDJJBSJ
	 * @param form
	 *            QysdsnbForm
	 */
	public void setDjInfo(SWDJJBSJ djsj, QysdsnbForm form) {
		// ���������
		form.setJsjdm(djsj.getJsjdm());
		// ��˰������
		form.setQymc(djsj.getNsrmc());
		// ��ҵ��Ӫ��ַ��ϵ�绰
		form.setQylxdh(djsj.getJydzlxdm());
		// ��ҵ�Ǽ�ע�����ʹ���
		form.setDjzclxdm(djsj.getDjzclxdm());
	}

	/**
	 * ���õǼ�����
	 * 
	 * @param djsj
	 *            SWDJJBSJ
	 * @param form
	 *            QycwzbForm
	 */
	public void setDjInfo(SWDJJBSJ djsj, QycwzbForm form) {
		// ���������
		form.setJsjdm(djsj.getJsjdm());
		// ��˰������
		form.setQymc(djsj.getNsrmc());
		// ��ҵ��Ӫ��ַ��ϵ�绰
		form.setQylxdh(djsj.getJydzlxdm());
	}
}
