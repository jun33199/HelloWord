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
	private String PZLYDM;//ƾ֤��Դ����
	private String PZZLDM;//Ʊ֤�������
	private String SJM;//�����
	private String WSZH;//��˰֤����˰֤��
	private String NDZB;//����ֱ�
	private String NSRSBH;//��˰��ʶ���
	private String NSRMC;//��˰������
	private String SWJGZZJGDM;//˰�������֯��������
	private String SWJGMC;//˰���������
	private String KJSWJGZZJGDM;//����˰�������֯��������
	private String TFRQ;//�����
	private String HJJE;//�ϼƽ��
	private String HJJEDX;//�ϼƽ���д
	private String CJR;//������
	private String CJRQ;//��������
	private String LRR;//¼����
	private String LRRQ;//¼������
	private String QXDM;//���ش���
	private String YPZH;//ԭƾ֤��
	private String YPZZLDM;//ԭƱ֤�������
	private String YWSZH;//ԭ��˰֤����˰֤��
	private String YNDZB;//ԭ����ֱ�
	private String YWHM;//ҵ�����(Ͷ��ȷ����||������� ������ˮ��)
	private String DYBZ;//��ӡ��־
	private String DYCS;//�Ѵ�ӡ����
	private String CURRENT_DYCS;//��ǰ�ǵڼ��δ�ӡ  = �Ѵ�ӡ���� +1
	private String YXBZ;//��Ч��־
	private String KJLYDM;//������Դ����(0˰����˰֤������ 1�걨���� 2��������)
	private String BZ;//��ע
	private String PZLXDM;//ƾ֤���ʹ���(0��˰ƾ֤  1��˰ƾ֤)
	
	private ArrayList mxList = new ArrayList();
	
	//��ӡʱ��
	private SgsswszmMXVo printMx = new SgsswszmMXVo();
	private String reShowMx;//������ʾ��ϸ��Ϣ�������޸ĺͱ���ʧ�ܻ��ԣ�
	
	private String cjwszmBYothers=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//�Ѿ����������������˰����˰֤������������˰֤����������˰֤������������˰֤����0--δ����  1--�ѿ��ߣ��������޸ĺ�����ʱ�ж��ã�������߲����޸ĺ�����
	
	
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
