package com.lscdz.qysds.application.qysdsnb2014.util;

import com.lscdz.qysds.application.qysdsnb2014.QysdsNb2014Contant;
/**
 * ��ҵ����˰������
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-1-29 ����09:27:48
 */
public class QysdsNb2014Util {
	/**
	 * �������õĻ��׼������ƶȣ��μ������DMDB.SB_DM_QYSDS_KJZZ�����������õĻ��׼������ƶȣ�01:һ����ҵ;08:��ҵ��λ����ƶ�;14:����Ӫ����֯����ƶȣ���ȡ�ƻ��ƶ�
	 * 1�����õĻ��׼������ƶ�Ϊ01��06��07ʱ���ƻ��ƶ�Ϊ12
	 * 2�����õĻ��׼������ƶ�Ϊ02��03��04��05ʱ���ƻ��ƶ�Ϊ14
	 * 3�����õĻ��׼������ƶ�Ϊ08��09��10��11��12��13ʱ���ƻ��ƶ�Ϊ15
	 * 4�����õĻ��׼������ƶ�Ϊ14ʱ���ƻ��ƶ�Ϊ16
	 * 5�����õĻ��׼������ƶ�Ϊ������ֵʱ�����ݡ��������õĻ��׼������ƶȡ���ֵȷ���ƻ��ƶȣ�
	 *    ���������õĻ��׼������ƶȡ�������ƻ��ƶȵĶ�Ӧ����������1~4�Ĺ�����ͬ��
	 * @param sykjzzhkjzz
	 * @param qtsykjzzhkjzz
	 * @return
	 */
	public static String getCkzd(String sykjzzhkjzz,String qtsykjzzhkjzz){
		if(QysdsNb2014Contant.QYSDS_NB_KJZZDM_CJTJJZZKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_NMZYHZSCWKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_QT.equals(sykjzzhkjzz)){
			return getCkzd(qtsykjzzhkjzz);
		}else{
			return getCkzd(sykjzzhkjzz);
		}
		
	}
	/**
	 * �������õĻ��׼������ƶȣ��μ������DMDB.SB_DM_QYSDS_KJZZ�����������õĻ��׼������ƶȣ�01:һ����ҵ;08:��ҵ��λ����ƶ�;14:����Ӫ����֯����ƶȣ���ȡ�ƻ��ƶ�
	 * 1�����õĻ��׼������ƶ�Ϊ01��06��07ʱ���ƻ��ƶ�Ϊ12
	 * 2�����õĻ��׼������ƶ�Ϊ02��03��04��05ʱ���ƻ��ƶ�Ϊ14
	 * 3�����õĻ��׼������ƶ�Ϊ08��09��10��11��12��13ʱ���ƻ��ƶ�Ϊ15
	 * 4�����õĻ��׼������ƶ�Ϊ14ʱ���ƻ��ƶ�Ϊ16
	 * @param sykjzzhkjzz
	 * @return
	 */
	private static String getCkzd(String sykjzzhkjzz){
		if(null==sykjzzhkjzz||"".equals(sykjzzhkjzz)){
			return "";
		}
		if(QysdsNb2014Contant.QYSDS_NB_KJZZDM_YBQY.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_XQYKJZZ.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_QYKJZD.equals(sykjzzhkjzz)){
			return QysdsNb2014Contant.QYSDS_NB_CKZD_YBQY;
		}
		if(QysdsNb2014Contant.QYSDS_NB_KJZZDM_YH.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_ZQ.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_BX.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_DB.equals(sykjzzhkjzz)){
			return QysdsNb2014Contant.QYSDS_NB_CKZD_JRQY;
		}
		if(QysdsNb2014Contant.QYSDS_NB_KJZZDM_QYDWKJZJ.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_KXSYDWKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_YYKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_GDXXKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_ZXXXKJZD.equals(sykjzzhkjzz)||
				QysdsNb2014Contant.QYSDS_NB_KJZZDM_CPJGKJZD.equals(sykjzzhkjzz)){
			return QysdsNb2014Contant.QYSDS_NB_CKZD_QSYDW;
		}
		if(QysdsNb2014Contant.QYSDS_NB_KJZZDM_MJFYLZZKJZD.equals(sykjzzhkjzz)){
			return QysdsNb2014Contant.QYSDS_NB_CKZD_MJFYLZZ;
		}
		return "";
	}
}
