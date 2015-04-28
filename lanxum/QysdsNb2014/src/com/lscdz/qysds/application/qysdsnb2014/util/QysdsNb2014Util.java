package com.lscdz.qysds.application.qysdsnb2014.util;

import com.lscdz.qysds.application.qysdsnb2014.QysdsNb2014Contant;
/**
 * 企业所得税工具类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-1-29 上午09:27:48
 */
public class QysdsNb2014Util {
	/**
	 * 根据适用的会计准则或会计制度（参见代码表：DMDB.SB_DM_QYSDS_KJZZ）和其他适用的会计准则或会计制度（01:一般企业;08:事业单位会计制度;14:民间非营利组织会计制度）获取财会制度
	 * 1、适用的会计准则或会计制度为01、06、07时，财会制度为12
	 * 2、适用的会计准则或会计制度为02、03、04、05时，财会制度为14
	 * 3、适用的会计准则或会计制度为08、09、10、11、12、13时，财会制度为15
	 * 4、适用的会计准则或会计制度为14时，财会制度为16
	 * 5、适用的会计准则或会计制度为其他的值时，根据“其他适用的会计准则或会计制度”的值确定财会制度，
	 *    “其他适用的会计准则或会计制度”代码与财会制度的对应规则与以上1~4的规则相同。
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
	 * 根据适用的会计准则或会计制度（参见代码表：DMDB.SB_DM_QYSDS_KJZZ）和其他适用的会计准则或会计制度（01:一般企业;08:事业单位会计制度;14:民间非营利组织会计制度）获取财会制度
	 * 1、适用的会计准则或会计制度为01、06、07时，财会制度为12
	 * 2、适用的会计准则或会计制度为02、03、04、05时，财会制度为14
	 * 3、适用的会计准则或会计制度为08、09、10、11、12、13时，财会制度为15
	 * 4、适用的会计准则或会计制度为14时，财会制度为16
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
