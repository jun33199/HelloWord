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
 * 企业所得税helper
 */
public class QysdsHelper {

	// 税款所属日期的日期格式
	private final String SKSSRQ_DATE_SYMBOL = "yyyy-MM-dd";

	// 企业所得税税率
	private final String QYSDS_SL = "0.33";

	/**
	 * 负责将企业所得税年报form中的数据转换成后台process所需的年报的list。
	 * 
	 * 1、申报日期为系统当前日期 2、计算税款所属时期 3、从UserData中获得计算机代码、名称、主管税务机关等登记数据
	 * 4、从登记中取出代理中介机构财务负责人 5、录入申报日期和填表日期 6、构造list，并返回
	 * 
	 * @param userData
	 *            用户登录数据
	 * @param form
	 *            企业所得税年报ActionForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ登记信息
	 * @return 年报list
	 * @throws BaseException
	 */
	public QysdsnbData getQysdsnbData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) throws BaseException {

		// 计算机代码
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

				nb.setJsjdm(jsjdm); // 计算机代码
				nb.setNd(strYear); // 年度
				hc = form.getNbhc()[i];
				nb.setHc(hc); // 行次

				// 本期累计数
				bqljs = form.getNbbqljs()[i];
				if (bqljs != null && bqljs.trim().length() > 0) {
					nb.setBqljs(new BigDecimal(bqljs));
				}

				if (hc.equals("81")) // 81行次，减免税额
				{
					jmse = bqljs;
				}

				nb.setSbrq(sbrq); // 申报日期
				nb.setSkssksrq(getSkssrq(form.getSkssksrq())); // 税款所属时间
				nb.setSkssjsrq(getSkssrq(form.getSkssjsrq())); // 税款所属时间
				nb.setCjrq(curTime); // 创建时间
				nb.setLrrq(curTime); // 填表日期
				nb.setLrr(userData.yhid); // 录入人代码
				nb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务所代码
				nb.setZgswjgzzjgdm(djInfo.getSwjgzzjgdm()); // 主管税务机关代码
				nb.setFsdm(CodeConstant.FSDM_WSSB); // 登记申报方式代码
				nb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2)); // 区县代码
			}
		}

		nbData.getNbData().setNbData(nbList);

		// 准备减免数据
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
			jm.setJmlx(CodeConstant.JMLX_SP); // 审批减免
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
			jm.setYskmdm(null); // 在processor赋值
			jm.setYsjcdm(null); // 在processor赋值
			jm.setSkssksrq(getSkssrq(form.getSkssksrq())); // 税款所属时间
			jm.setSkssjsrq(getSkssrq(form.getSkssjsrq())); // 税款所属时间
			jm.setNd(strYear);
			jm.setCjrq(curTime);
			jm.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));

			String jmxmdm = proxy.getJmsbs(jsjdm, SzsmdmConstant.QYSDS, ksrq,
					jsrq);
			jm.setJmxmdm(jmxmdm); // 减免项目代码
		}

		nbData.setJm(jm);

		return nbData;
	}

	/**
	 * 负责将企业所得税年报form中的数据转换成后台process所需的事业单位list。 list 0; 1、申报日期为系统当前日期
	 * 2、计算税款所属时期 3、从UserData中获得计算机代码、名称、主管税务机关等登记数据 4、从登记中取出代理中介机构财务负责人
	 * 5、录入申报日期和填表日期 6、构建事业单位数据list, 7、构建批准文号list 8、将事业单位申报数据和批准文号数据构造成一个对象并返回
	 * 
	 * @param userData
	 *            用户登录数据
	 * @param form
	 *            事业单位QysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ登记信息
	 * @return 事业单位list
	 */
	public QysdsnbData getSydwData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// 计算机代码
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

		// 主表数据
		zbdata = new Sydwshttsrzb();
		zbdata.setJsjdm(jsjdm); // 计算机代码
		zbdata.setNd(strYear); // 年度
		zbdata.setSfyyssr(form.getSfyyssr());

		zbdata.setSbrq(TinyTools.second2Day(curTime)); // 申报日期

		// 税款所属时间
		zbdata.setSkssksrq(getSkssrq(form.getSkssksrq()));
		zbdata.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

		zbdata.setCjrq(curTime); // 创建时间
		zbdata.setLrrq(curTime); // 填表日期
		zbdata.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务机关组织机构代码
		zbdata.setLrr(userData.yhid); // 录入人代码
		zbdata.setFsdm(CodeConstant.FSDM_WSSB); // 登记申报方式代码
		zbdata.setQxdm(qxdm);

		mxList = new ArrayList();
		whList = new ArrayList();

		if (hcs != null) {
			for (int i = 0; i < hcs.length; i++) {
				Sydwshttsrmx mx = new Sydwshttsrmx();
				mxList.add(mx);

				// 计算机代码
				mx.setJsjdm(jsjdm);

				// 年度
				mx.setNd(strYear);

				// 行次
				mx.setHc(form.getSydwhc()[i]);

				// 本期累计数
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

		// 第3行批准文号
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

		// 第4行批准文号
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

		// 第5行批准文号
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

		// 对事业单位赋值
		nbData.getSydwData().setSydwshttsrzb(zbdata);
		nbData.getSydwData().setMxList(mxList);
		nbData.getSydwData().setWhList(whList);

		return nbData;
	}

	/**
	 * 负责将企业所得税年报form中的数据转换成后台process所需的 联营股份list。
	 * 
	 * 1、申报日期为系统当前日期 2、计算税款所属时期 3、从UserData中获得计算机代码、名称、主管税务机关等登记数据
	 * 4、从登记中取出代理中介机构财务负责人 5、录入申报日期和填表日期 6、构造Vopackage，并返回 7、构造list，并返回
	 * 
	 * @param userData
	 *            用户登录数据
	 * @param form
	 *            联营股份QysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ登记信息
	 * @return 联营股份list
	 */
	public QysdsnbData getLygfData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// 计算机代码
		String jsjdm = userData.yhid;

		String[] hcs = form.getQycwzbncs();
		String hc = null;
		String nms = null;
		String ncs = null;
		String xmmc = null;

		List lygfList = new ArrayList();

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		String strYear = (curTime.getYear() - 1 + 1900) + "";

		// 序号数组
		int[] xh = new int[5];
		// 分类代码
		String qylxdm = null;
		int lxdm;

		if (form.getLygfdjzclxdm() != null) {
			int size = form.getLygfdjzclxdm().length;
			for (int i = 0; i < size; i++) {
				Lygf lygf = new Lygf();
				lygfList.add(lygf);

				// 计算机代码
				lygf.setJsjdm(jsjdm);

				// 年度
				lygf.setNd(strYear);

				// 企业类型代码
				qylxdm = form.getLygfdjzclxdm()[i];
				lygf.setFl(qylxdm);

				// 序号
				lxdm = Integer.parseInt(qylxdm);
				lygf.setXh(new BigDecimal(xh[lxdm]));
				xh[lxdm]++;

				// 单位名称
				lygf.setDwmc(form.getLygfqymc()[i]);

				// 税率
				lygf.setSl(new BigDecimal(form.getLygfsl()[i]));

				// 利润
				lygf.setLr(new BigDecimal(form.getLygflrgx()[i]));

				// 应纳税所得额
				lygf.setYnssde(new BigDecimal(form.getLygfynssde()[i]));

				// 应纳所得税额
				lygf.setYnsdse(new BigDecimal(form.getLygfynsdse()[i]));

				// 税收扣除额
				lygf.setSskce(new BigDecimal(form.getLygfsskce()[i]));

				// 应补所得税额
				lygf.setYbsdse(new BigDecimal(form.getLygfybsdse()[i]));

				// 申报日期
				lygf.setSbrq(TinyTools.second2Day(curTime));

				// 税款所属时间
				lygf.setSkssksrq(getSkssrq(form.getSkssksrq()));
				lygf.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				lygf.setCjrq(curTime); // 创建时间
				lygf.setLrrq(curTime); // 填表日期
				lygf.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务机关组织机构代码
				lygf.setLrr(userData.yhid); // 录入人代码
				lygf.setNsrmc(form.getQymc()); // 纳税人名称
				lygf.setQylxdh(form.getQylxdh()); // 企业联系电话
				lygf.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
				lygf.setFsdm(CodeConstant.FSDM_WSSB);
			}
		}

		nbData.getLygfData().setLygfData(lygfList);

		return nbData;
	}

	/**
	 * 负责将企业所得税年报form中的数据转换成后台process所需的财务指标list。
	 * 
	 * 1、申报日期为系统当前日期 2、计算税款所属时期 3、从UserData中获得计算机代码、名称、主管税务机关等登记数据
	 * 4、从登记中取出代理中介机构财务负责人 5、录入申报日期和填表日期 6、构造list，并返回
	 * 
	 * @param userData
	 *            用户登录数据
	 * @param form
	 *            企业财务指标QysdsnbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ登记信息
	 * @return 财务指标list
	 */
	public QysdsnbData getCwzbData(UserData userData, QysdsnbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// 计算机代码
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

				// 计算机代码
				cwzb.setJsjdm(jsjdm);

				// 年度
				cwzb.setNd(strYear);

				// 行次
				cwzb.setHc(form.getQycwzbhc()[i]);

				// 年初数
				ncs = form.getQycwzbncs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}

				// 年末数
				nms = form.getQycwzbnms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// 申报日期
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// 税款所属时间
				cwzb.setSkssksrq(getSkssrq(form.getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				cwzb.setCjrq(curTime); // 创建时间
				cwzb.setLrrq(curTime); // 填表日期
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务机关组织机构代码
				cwzb.setLrr(userData.yhid); // 录入人代码
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // 登记申报方式代码
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	/**
	 * 负责将企业财务指标年报form中的数据转换成后台process所需的财务指标list。
	 * 
	 * 1、申报日期为系统当前日期 2、计算税款所属时期 3、从UserData中获得计算机代码、名称、主管税务机关等登记数据
	 * 4、从登记中取出代理中介机构财务负责人 5、录入申报日期和填表日期 6、构造list，并返回
	 * 
	 * @param userData
	 *            用户登录数据
	 * @param form
	 *            企业财务指标QycwzbForm
	 * @param nbData
	 *            QysdsnbData
	 * @param djInfo
	 *            SWDJJBSJ登记信息
	 * @return 财务指标list
	 */
	public QysdsnbData getCwzbData(UserData userData, QycwzbVO qycw,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// 计算机代码
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

				// 计算机代码
				cwzb.setJsjdm(jsjdm);

				// 年度
				cwzb.setNd(strYear);

				// 行次
				cwzb.setHc(sbsjlist.getSbsj().getHc()[i]);

				// 年初数
				ncs = sbsjlist.getSbsj().getNcs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}
				// 年末数
				nms =sbsjlist.getSbsj().getNms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// 申报日期
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// 税款所属时间
				cwzb.setSkssksrq(getSkssrq(qycw.getSbxx().getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(qycw.getSbxx().getSkssjsrq()));

				cwzb.setCjrq(curTime); // 创建时间
				cwzb.setLrrq(curTime); // 填表日期
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务机关组织机构代码
				cwzb.setLrr(userData.yhid); // 录入人代码
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // 登记申报方式代码
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	public QysdsnbData getCwzbData(UserData userData, QycwzbForm form,
			QysdsnbData nbData, SWDJJBSJ djInfo) {
		// 计算机代码
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

				// 计算机代码
				cwzb.setJsjdm(jsjdm);

				// 年度
				cwzb.setNd(strYear);

				// 行次
				cwzb.setHc(form.getQycwzbhc()[i]);

				// 年初数
				ncs = form.getQycwzbncs()[i];
				if (ncs != null && ncs.trim().length() > 0) {
					cwzb.setNcs(new BigDecimal(ncs));
				}
				// 年末数
				nms = form.getQycwzbnms()[i];
				if (nms != null && nms.trim().length() > 0) {
					cwzb.setNms(new BigDecimal(nms));
				}

				// 申报日期
				cwzb.setSbrq(TinyTools.second2Day(curTime));

				// 税款所属时间
				cwzb.setSkssksrq(getSkssrq(form.getSkssksrq()));
				cwzb.setSkssjsrq(getSkssrq(form.getSkssjsrq()));

				cwzb.setCjrq(curTime); // 创建时间
				cwzb.setLrrq(curTime); // 填表日期
				cwzb.setSwjgzzjgdm(djInfo.getSwjgzzjgdm()); // 税务机关组织机构代码
				cwzb.setLrr(userData.yhid); // 录入人代码
				cwzb.setFsdm(CodeConstant.FSDM_WSSB); // 登记申报方式代码
				cwzb.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
			}
		}

		nbData.getCwzbData().setCwzbData(cwzbList);

		return nbData;
	}

	/**
	 * 负责将企业所得税年报的数据转换为form中的数据
	 * 
	 * 1、对申报表定义defineList赋值 2、项目名称、行次、序号、本期累计数赋值
	 * 
	 * @param nbdata
	 *            QysdsnbData.NBData
	 * @param form
	 *            企业所得税年报QysdsnbForm
	 */
	public void setQysdsnbData(QysdsnbData.NBData nbdata, QysdsnbForm form) {
		// 代码表数据
		form.setNbDefineList(nbdata.getDefineList());

		// 设置数据
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

			// 行次
			form.setNbhc(hc);
			// 本期累计数
			form.setNbbqljs(bqljs);
		}
	}

	/**
	 * 负责将企事业单位的数据转换为form中的数据
	 * 
	 * 1、对申报表定义defineList赋值 2、项目名称、行次、序号、本期累计数赋值 3、对批准文号3、4、5赋值
	 * 
	 * @param sydwData
	 *            事业单位QysdsnbData.SydwData
	 * @param form
	 *            企业所得税年报ActionForm
	 */
	public void setSydwData(QysdsnbData.SydwData sydwData, QysdsnbForm form) {
		// 事业单位申报表定义的代码表数据
		form.setSydwsbbDefineList(sydwData.getDefineList());

		if (sydwData.getSydwshttsrzb() != null) {
			// 是否有应税收入
			String sfyyssr = sydwData.getSydwshttsrzb().getSfyyssr();
			if (sfyyssr != null)
				form.setSfyyssr(sfyyssr);

			// 申报数据
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

				// 行次
				form.setSydwhc(hc);
				// 本期累计数
				form.setSydwbqljs(bqljs);
			}

			// 文号数据
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
	 * 负责将联营股份的数据转换为form中的数据
	 * 
	 * 将企业类型代码、税率、利润股息等收入、企业名称、应纳税所得额、应纳所得税额、已纳 所得税额、本企业税率放入form中
	 * 
	 * @param lygfData
	 *            联营股份数据QysdsnbData.LygfData
	 * @param form
	 *            企业所得税年报QysdsnbForm
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
	 * 负责将企业财务指标的数据转换为form中的数据
	 * 
	 * 1、对申报表定义defineList赋值 2、项目名称、行次、序号、年初数、年末数赋值
	 * 
	 * @param cwzbData
	 *            财务指标数据
	 * @param form
	 *            企业所得税年报ActionForm
	 */
	public void setCwzbData(QysdsnbData.CwzbData cwzbData, QysdsnbForm form) {
		// 代码表数据
		form.setQycwzbDefineList(cwzbData.getDefineList());

		// 设置数据
		List data = cwzbData.getCwzbData();
		if (data != null && data.size() > 0) {
			int size = data.size();
			// 行次
			String[] hc = new String[size];
			// 年初数
			String[] ncs = new String[size];
			// 年数
			String[] nms = new String[size];

			for (int i = 0; i < data.size(); i++) {
				Qyjbcwzb item = (Qyjbcwzb) data.get(i);
				hc[i] = item.getHc();
				ncs[i] = (item.getNcs() == null) ? "" : String.valueOf(item
						.getNcs());
				nms[i] = (item.getNms() == null) ? "" : String.valueOf(item
						.getNms());
			}

			// 行次
			form.setQycwzbhc(hc);
			// 年初数
			form.setQycwzbncs(ncs);
			// 年数
			form.setQycwzbnms(nms);
		}
	}

	/**
	 * 负责将企业财务指标的数据转换为form中的数据
	 * 
	 * 1、对申报表定义defineList赋值 2、项目名称、行次、序号、年初数、年末数赋值
	 * 
	 * @param cwzbData
	 *            财务指标数据
	 * @param form
	 *            企业所得税年报ActionForm
	 */
	public void setCwzbData(QysdsnbData.CwzbData cwzbData, QycwzbForm form) {
		// 代码表数据
		form.setQycwzbDefineList(cwzbData.getDefineList());

		// 设置数据
		List data = cwzbData.getCwzbData();
		if (data != null && data.size() > 0) {
			int size = data.size();
			// 行次
			String[] hc = new String[size];
			// 年初数
			String[] ncs = new String[size];
			// 年数
			String[] nms = new String[size];

			for (int i = 0; i < data.size(); i++) {
				Qyjbcwzb item = (Qyjbcwzb) data.get(i);
				hc[i] = item.getHc();
				ncs[i] = (item.getNcs() == null) ? "" : String.valueOf(item
						.getNcs());
				nms[i] = (item.getNms() == null) ? "" : String.valueOf(item
						.getNms());
			}

			// 行次
			form.setQycwzbhc(hc);
			// 年初数
			form.setQycwzbncs(ncs);
			// 年末数
			form.setQycwzbnms(nms);
		}
	}

	/**
	 * 设置税费管理的数据，包括税率和审批的资格
	 * 
	 * @param userData
	 *            UserData
	 * @param form
	 *            QysdsnbForm
	 * @param swdjjbsj
	 *            登记信息
	 * @throws BaseException
	 */
	public void setSfglData(UserData userData, QysdsnbForm form,
			SWDJJBSJ swdjjbsj) throws BaseException {
		// 企业征税的税率类型
		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		/*
		 * 联营股份企业税率 如果企业征税的税率类型为“1”：高新技术企业税率，则为qyzssl 否则，为qysdssl
		 */
		String lygfqysl = QYSDS_SL;

		// 计算机代码
		String jsjdm = userData.yhid;
		// 当前时间
		Date sbrq = new Date();

		Map ssrq = Skssrq.yearSkssrq(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// 查询税费接口
		QysdsSet qysdsSet = proxy.getQysdsInfo(jsjdm, // 计算机代码
				sbrq, // 申报日期
				(Date) ssrq.get(Skssrq.SKSSKSRQ), // 税款所属开始日期
				(Date) ssrq.get(Skssrq.SKSSJSRQ), "01"); // 税款所属结束日期

		// 1、查询是否是高新技术企业
		Date gxqyrdrq = qysdsSet.getGxjsqy();
		if (gxqyrdrq != null) {
			qyzssllx = "1";
			qyzssl = "0.15";
			// 联营股份企业税率为高新技术企业的税率
			lygfqysl = qyzssl;

			form.setGxqy("1");
		}
		// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				form.setIsXzqy("1");
			}
		}

		// 2、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				// 纯益率征收
				qyzssllx = "2";
				qyzssl = zsfs.getCyl();
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				qyzssl = zsfs.getDe();
			}
		}

		form.setLygfqysl(lygfqysl);
		form.setQyzssllx(qyzssllx);
		form.setQyzssl(qyzssl);

		// 审批资格
		form.setSpzg24(qysdsSet.getCcss()); // 财产损失扣除额
		// form.setSpzg63(qysdsSet.getNbyqndks()); //弥补以前年度亏损
		// form.setSpzg77(qysdsSet.getSxdk()); //三新抵扣
		form.setSpzg81(qysdsSet.getYbjme()); // 减、免所得税额
		form.setSpzg82(qysdsSet.getJsgzgcsb()); // 技术改造国产设备投资抵免税额
		form.setSpzg69(qysdsSet.getJsgzgcsb()); // 免税的技术转让收益
	}

	/**
	 * 设置税款所属日期
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
	 * 设置税款所属日期
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
	 * 取得税款所属日期
	 * 
	 * @param skssrq
	 *            税款所属日期
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
	 * 设置登记数据
	 * 
	 * @param djsj
	 *            SWDJJBSJ
	 * @param form
	 *            QysdsnbForm
	 */
	public void setDjInfo(SWDJJBSJ djsj, QysdsnbForm form) {
		// 计算机代码
		form.setJsjdm(djsj.getJsjdm());
		// 纳税人名称
		form.setQymc(djsj.getNsrmc());
		// 企业经营地址联系电话
		form.setQylxdh(djsj.getJydzlxdm());
		// 企业登记注册类型代码
		form.setDjzclxdm(djsj.getDjzclxdm());
	}

	/**
	 * 设置登记数据
	 * 
	 * @param djsj
	 *            SWDJJBSJ
	 * @param form
	 *            QycwzbForm
	 */
	public void setDjInfo(SWDJJBSJ djsj, QycwzbForm form) {
		// 计算机代码
		form.setJsjdm(djsj.getJsjdm());
		// 纳税人名称
		form.setQymc(djsj.getNsrmc());
		// 企业经营地址联系电话
		form.setQylxdh(djsj.getJydzlxdm());
	}
}
