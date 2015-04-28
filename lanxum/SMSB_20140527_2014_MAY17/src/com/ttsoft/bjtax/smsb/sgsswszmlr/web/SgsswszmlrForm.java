package com.ttsoft.bjtax.smsb.sgsswszmlr.web;

import java.util.ArrayList;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmVo;
import com.ttsoft.framework.form.BaseForm;

public class SgsswszmlrForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	private String saveIsSucc="";//�����Ƿ�ɹ�
	private String hasSaved="";//����Ϣ�Ƿ��Ѿ���������������������޸�ʱ��ť��ʾ���ƣ�
	private String pzlxdm=Constant.CONS_SGLR_PZLXDM_0; //¼��ƾ֤����  0 -- ��˰ƾ֤    1 -- ��˰ƾ֤
	private String kjlydm=Constant.CONS_SGLR_KJLYDM_0;//������Դ����(0˰����˰֤������ 1�걨���� 2��������)
	private String pzlydm=Constant.CONS_SGLR_PZLYDM_08;//ƾ֤��Դ���� Ĭ��Ϊ08--�ֹ���˰֤��
	private String yxbz=Constant.CONS_SGLR_YXBZ_0;//��Ч��־ 0--��Ч  1-- ����
	private String dybz=Constant.CONS_SGLR_DYBZ_0;//��ӡ��־  0--δ��ӡ  1--�Ѿ���ӡ
	private int    dycs=Constant.CONS_SGLR_DYCS_0;//��ӡ����Ĭ��Ϊ0��
	private String cjwszmBYothers=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//�Ѿ����������������˰����˰֤������������˰֤����������˰֤������������˰֤����0--δ����  1--�ѿ��ߣ��������޸ĺ�����ʱ�ж��ã�������߲����޸ĺ�����
	
	private String nsrsbh;//��˰��ʶ���
	private String swjgmc;//˰�����
	private String nsrmc;//��˰������
	private String tfrq;//�����
	
	private String wsxxAll;//������˰��Ϣ
	private String sjjeHJ;//���ϼ�
	private String lrrmc;//¼��������
	private String lrrdm;//¼���˴���
	private String lrrq;//¼������
	private String bz;//��ע��Ϣ
	
	//for ��ѯ
	private String query_nsrsbh;//��˰��ʶ���
	private String query_nsrmc;//��˰������
	private String query_wspzh;//��˰֤������
	private ArrayList queryList_onePage = new ArrayList();//��ѯ���(һҳ)
	private String query_type = Constant.CONS_SGLR_QUERY_TYPE_1;//��ѯ����
	
	
	//��˰֤����Ϣ��������ʽΪ��Ʊ֤�������-��˰֤����-����ӱ𡱣���;������ӡ����ӡԤ�����޸ģ�
	private String wszmKey;
	private String rePrint = Constant.CONS_SGLR_REPRINT_NO;//�Ƿ��ش�
	
	private ArrayList smsmList = new ArrayList();
	private SgsswszmVo printVo = new SgsswszmVo();
	
	/**
	  * ҳ����ʾ�ߴ�
	  */
	private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

	/**
	  * ҳ��
	  */
	private String nextPage = "1";

	/**
	  * ҳ��
	  */
	private String totalpage = "0";

	/**
	  * ҳ����ʾ��Ϣ
	  */
	private String message;

	/**
	  * ��ǰҳ��
	  */
	private String curPage;
	
	
	
	
	public String getSwjgmc() {
		return swjgmc;
	}

	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}

	public String getTfrq() {
		return tfrq;
	}

	public void setTfrq(String tfrq) {
		this.tfrq = tfrq;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getSjjeHJ() {
		return sjjeHJ;
	}

	public void setSjjeHJ(String sjjeHJ) {
		this.sjjeHJ = sjjeHJ;
	}

	public String getLrrmc() {
		return lrrmc;
	}

	public void setLrrmc(String lrrmc) {
		this.lrrmc = lrrmc;
	}

	public String getLrrdm() {
		return lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public ArrayList getSmsmList() {
		return smsmList;
	}

	public void setSmsmList(ArrayList smsmList) {
		this.smsmList = smsmList;
	}

	public String getWsxxAll() {
		return wsxxAll;
	}

	public void setWsxxAll(String wsxxAll) {
		this.wsxxAll = wsxxAll;
	}

	public String getPzlxdm() {
		return pzlxdm;
	}

	public void setPzlxdm(String pzlxdm) {
		this.pzlxdm = pzlxdm;
	}

	public String getPzlydm() {
		return pzlydm;
	}

	public void setPzlydm(String pzlydm) {
		this.pzlydm = pzlydm;
	}

	public String getDybz() {
		return dybz;
	}

	public void setDybz(String dybz) {
		this.dybz = dybz;
	}

	public int getDycs() {
		return dycs;
	}

	public void setDycs(int dycs) {
		this.dycs = dycs;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getKjlydm() {
		return kjlydm;
	}

	public void setKjlydm(String kjlydm) {
		this.kjlydm = kjlydm;
	}

	public String getQuery_nsrmc() {
		return query_nsrmc;
	}

	public void setQuery_nsrmc(String query_nsrmc) {
		this.query_nsrmc = query_nsrmc;
	}

	public String getQuery_nsrsbh() {
		return query_nsrsbh;
	}

	public void setQuery_nsrsbh(String query_nsrsbh) {
		this.query_nsrsbh = query_nsrsbh;
	}

	public String getQuery_wspzh() {
		return query_wspzh;
	}

	public void setQuery_wspzh(String query_wspzh) {
		this.query_wspzh = query_wspzh;
	}

	public String getQuery_type() {
		return query_type;
	}

	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}

	public String getWszmKey() {
		return wszmKey;
	}

	public void setWszmKey(String wszmKey) {
		this.wszmKey = wszmKey;
	}

	public String getRePrint() {
		return rePrint;
	}

	public void setRePrint(String rePrint) {
		this.rePrint = rePrint;
	}

	public String getSaveIsSucc() {
		return saveIsSucc;
	}

	public void setSaveIsSucc(String saveIsSucc) {
		this.saveIsSucc = saveIsSucc;
	}

	public SgsswszmVo getPrintVo() {
		return printVo;
	}

	public void setPrintVo(SgsswszmVo printVo) {
		this.printVo = printVo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public ArrayList getQueryList_onePage() {
		return queryList_onePage;
	}

	public void setQueryList_onePage(ArrayList queryList_onePage) {
		this.queryList_onePage = queryList_onePage;
	}

	public String getHasSaved() {
		return hasSaved;
	}

	public void setHasSaved(String hasSaved) {
		this.hasSaved = hasSaved;
	}

	public String getCjwszmBYothers() {
		return cjwszmBYothers;
	}

	public void setCjwszmBYothers(String cjwszmBYothers) {
		this.cjwszmBYothers = cjwszmBYothers;
	}
}
