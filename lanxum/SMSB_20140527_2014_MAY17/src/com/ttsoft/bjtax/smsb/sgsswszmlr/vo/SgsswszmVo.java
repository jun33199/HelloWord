package com.ttsoft.bjtax.smsb.sgsswszmlr.vo;

import java.io.Serializable;
import java.util.ArrayList;

import com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant;
public class SgsswszmVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indexId;
	private String PZLYDM;//凭证来源代码
	private String PZZLDM;//票证种类代码
	private String SJM;//随机码
	private String WSZH;//完税证明完税证号
	private String NDZB;//年度字别
	private String NSRSBH;//纳税人识别号
	private String NSRMC;//纳税人名称
	private String SWJGZZJGDM;//税务机关组织机构代码
	private String SWJGMC;//税务机关名称
	private String KJSWJGZZJGDM;//开具税务机关组织机构代码
	private String TFRQ;//填发日期
	private String HJJE;//合计金额
	private String HJJEDX;//合计金额大写
	private String CJR;//创建人
	private String CJRQ;//创建日期
	private String LRR;//录入人
	private String LRRQ;//录入日期
	private String QXDM;//区县代码
	private String YPZH;//原凭证号
	private String YPZZLDM;//原票证种类代码
	private String YWSZH;//原完税证明完税证号
	private String YNDZB;//原年度字别
	private String YWHM;//业务号码(投保确认码||车辆编号 交易流水号)
	private String DYBZ;//打印标志
	private String DYCS;//已打印次数
	private String CURRENT_DYCS;//当前是第几次打印  = 已打印次数 +1
	private String YXBZ;//有效标志
	private String KJLYDM;//开具来源代码(0税收完税证明管理 1申报换开 2保单换开)
	private String BZ;//备注
	private String PZLXDM;//凭证类型代码(0缴税凭证  1退税凭证)
	
	private ArrayList mxList = new ArrayList();
	
	//打印时用
	private SgsswszmMXVo printMx = new SgsswszmMXVo();
	private String reShowMx;//重新显示明细信息（用于修改和保存失败回显）
	
	private String cjwszmBYothers=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//已经被其他程序（如出具税收完税证明）开具了完税证明，即该完税证明被开具了完税证明（0--未开具  1--已开具）；用于修改和作废时判断用，如果开具不能修改和作废
	
	
	public String getPZLYDM() {
		return PZLYDM;
	}
	public void setPZLYDM(String pZLYDM) {
		PZLYDM = pZLYDM;
	}
	public String getPZZLDM() {
		return PZZLDM;
	}
	public void setPZZLDM(String pZZLDM) {
		PZZLDM = pZZLDM;
	}
	public String getWSZH() {
		return WSZH;
	}
	public void setWSZH(String wSZH) {
		WSZH = wSZH;
	}
	public String getNDZB() {
		return NDZB;
	}
	public void setNDZB(String nDZB) {
		NDZB = nDZB;
	}
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}
	public String getNSRMC() {
		return NSRMC;
	}
	public void setNSRMC(String nSRMC) {
		NSRMC = nSRMC;
	}
	public String getSWJGZZJGDM() {
		return SWJGZZJGDM;
	}
	public void setSWJGZZJGDM(String sWJGZZJGDM) {
		SWJGZZJGDM = sWJGZZJGDM;
	}
	public String getKJSWJGZZJGDM() {
		return KJSWJGZZJGDM;
	}
	public void setKJSWJGZZJGDM(String kJSWJGZZJGDM) {
		KJSWJGZZJGDM = kJSWJGZZJGDM;
	}
	public String getTFRQ() {
		return TFRQ;
	}
	public void setTFRQ(String tFRQ) {
		TFRQ = tFRQ;
	}
	public String getHJJE() {
		return HJJE;
	}
	public void setHJJE(String hJJE) {
		HJJE = hJJE;
	}
	public String getCJR() {
		return CJR;
	}
	public void setCJR(String cJR) {
		CJR = cJR;
	}
	public String getCJRQ() {
		return CJRQ;
	}
	public void setCJRQ(String cJRQ) {
		CJRQ = cJRQ;
	}
	public String getLRR() {
		return LRR;
	}
	public void setLRR(String lRR) {
		LRR = lRR;
	}
	public String getLRRQ() {
		return LRRQ;
	}
	public void setLRRQ(String lRRQ) {
		LRRQ = lRRQ;
	}
	public String getQXDM() {
		return QXDM;
	}
	public void setQXDM(String qXDM) {
		QXDM = qXDM;
	}
	public String getYPZH() {
		return YPZH;
	}
	public void setYPZH(String yPZH) {
		YPZH = yPZH;
	}
	public String getYPZZLDM() {
		return YPZZLDM;
	}
	public void setYPZZLDM(String yPZZLDM) {
		YPZZLDM = yPZZLDM;
	}
	public String getYWSZH() {
		return YWSZH;
	}
	public void setYWSZH(String yWSZH) {
		YWSZH = yWSZH;
	}
	public String getYNDZB() {
		return YNDZB;
	}
	public void setYNDZB(String yNDZB) {
		YNDZB = yNDZB;
	}
	public String getYWHM() {
		return YWHM;
	}
	public void setYWHM(String yWHM) {
		YWHM = yWHM;
	}
	public String getDYBZ() {
		return DYBZ;
	}
	public void setDYBZ(String dYBZ) {
		DYBZ = dYBZ;
	}
	public String getDYCS() {
		return DYCS;
	}
	public void setDYCS(String dYCS) {
		DYCS = dYCS;
	}
	public String getYXBZ() {
		return YXBZ;
	}
	public void setYXBZ(String yXBZ) {
		YXBZ = yXBZ;
	}
	public String getKJLYDM() {
		return KJLYDM;
	}
	public void setKJLYDM(String kJLYDM) {
		KJLYDM = kJLYDM;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public String getPZLXDM() {
		return PZLXDM;
	}
	public void setPZLXDM(String pZLXDM) {
		PZLXDM = pZLXDM;
	}
	public ArrayList getMxList() {
		return mxList;
	}
	public void setMxList(ArrayList mxList) {
		this.mxList = mxList;
	}
	public String getReShowMx() {
		return reShowMx;
	}
	public void setReShowMx(String reShowMx) {
		this.reShowMx = reShowMx;
	}
	public SgsswszmMXVo getPrintMx() {
		return printMx;
	}
	public void setPrintMx(SgsswszmMXVo printMx) {
		this.printMx = printMx;
	}
	public String getSWJGMC() {
		return SWJGMC;
	}
	public void setSWJGMC(String sWJGMC) {
		SWJGMC = sWJGMC;
	}
	public String getHJJEDX() {
		return HJJEDX;
	}
	public void setHJJEDX(String hJJEDX) {
		HJJEDX = hJJEDX;
	}
	public int getIndexId() {
		return indexId;
	}
	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}
	public String getSJM() {
		return SJM;
	}
	public void setSJM(String sJM) {
		SJM = sJM;
	}
	public String getCURRENT_DYCS() {
		return CURRENT_DYCS;
	}
	public void setCURRENT_DYCS(String cURRENT_DYCS) {
		CURRENT_DYCS = cURRENT_DYCS;
	}
	public String getCjwszmBYothers() {
		return cjwszmBYothers;
	}
	public void setCjwszmBYothers(String cjwszmBYothers) {
		this.cjwszmBYothers = cjwszmBYothers;
	}
}
