package com.ttsoft.bjtax.smsb.wszm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;


/**
*
* <p>Title:��˰֤����form </p>
*
* <p>Description:��˰֤����form </p>
*
* <p>Copyright: Copyright (c) 2013</p>
*
* <p>Company: </p>
*
* @author tujb
* @version 1.0
*/
public class WszmForm extends BaseForm
{
	/**
     * �����
     */
    private String tfrq;
    
	/**
     * ˰��Ʊ֤��Դ����
     */
    private String sspzlxdm;
    
    /**
     * ˰��Ʊ֤���ʹ���
     */
    private String pzlxdm = CodeConstant.WSZM_SGWSPZ_JS;
    
    /**
     * ԭƾ֤���������
     */
    private String ypzrkrq;
    
    /**
     * ԭƾ֤��
     */
    private String ysphm;
    
    /**
     * ԭƾ֤��
     */
    private String ypzh;
    
    /**
     * ԭƱ֤�������
     */
    private String ypzzldm;
    
    /**
     * ԭ��˰֤����˰֤��
     */
    private String ywszh;
    
    /**
     * ԭ����ֱ�
     */
    private String yndzb;
    
    /**
     * �ط�˰�����
     */
    private String dfswjg; //�ط�˰�����
    
    /**
     * ��ϸ��Ϣ��ԭƾ֤��
     */
    private String mxYpzh; //��ϸ��Ϣ��ԭƾ֤��
    
    /**
     * ��ϸ��Ϣ��˰������
     */
    private String mxYwszh; //��ϸ��Ϣ��ԭ��˰֤��
    
    /**
     * ��ϸ��Ϣ��˰������
     */
    private String mxSz; //��ϸ��Ϣ��˰������

    /**
     * ��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
     */
    private String mxPmmc; //��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
    
    /**
     * ��ϸ��Ϣ��˰����������
     */
    private String mxSkssrq; //˰��������ʼ����

    /**
     * ��ϸ��Ϣ��ʵ��˰��
     */
    private String mxSjse; //��ϸ��Ϣ��ʵ��˰��
    
    /**
     * ��ϸ��Ϣ��ʵ��˰��
     */
    private String mxRkrq; //��ϸ��Ϣ���������

    /**
     * ����ڣ���
     */
    private String tfrqYear; //����ڣ���

    /**
     * ����ڣ���
     */
    private String tfrqMonth; //����ڣ���

    /**
     * ����ڣ���
     */
    private String tfrqDate; //����ڣ���

    /**
     * ��˰֤��
     */
    private String headWszh; //��˰֤��

    /**
     * ����ֱ�
     */
    private String ndzb; //����ֱ�
    
    /**
     * ��ע
     */
    private String bz;
    
    /**
     * �����
     */
    private String wszmsjm; //�����
    
    /**
     * ������Ʊ��־
     */
    private String yhtpbz;
    
    /**
     * ���ϼ�
     */
    private String hjje;
    
    /**
     * ���ϼƣ���д��
     */
    private String hjjedx;
    
    /**
     * ���⴦��Ľ��ϼ�
     */
    private String hjje2;
    
    /**
     * ���⴦��Ľ��ϼƣ���д��
     */
    private String hjjedx2;
    
    /**
     * ��������루˰������
     */
    private String jsjdm;
    
    /**
     * ��ӡ����
     */
    private String dycs;
    
    /**
     * ��˰������
     */
    private String nsrmc;
    
    /**
     * ��˰��ʶ���
     */
    private String nsrsbh;
    
    /**
     * ����˰�������֯��������
     */
    private String ssswjgzzjgdm;
    
    /**
     * ����˰�������֯�ṹ����
     */
    private String kjswjgzzjgdm;
    
    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm;
    
    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgmc;
    
    /**
     * ¼����
     */
    private String lrr;
    
    /**
     * ¼������
     */
    private String lrrq;
    
    /**
     * �ʻ�����
     */
    private String zhdm;
    
    /**
     * Ʊ֤�������
     */
    private String pzzldm;
    
    /**
     * ��������루����������
     */
    private String query_jsjdm;
    
    /**
     * ˰Ʊ���루����������
     */
    private String query_sphm;
    
    /**
     * �����ʼ���ڣ�����������
     */
    private String query_qsrq;
    
    /**
     * ����ֹ���ڣ�����������
     */
    private String query_jzrq;
    
    /**
     * ��˰֤���루����������
     */
    private String query_wszh;
    
    /**
     * ��˰֤����ֱ�����������
     */
    private String query_ndzb;
    
    /**
     * ��˰֤���뱣��������������
     */
    private String query_bdwsz;
    
    /**
     * ���ƺ��루����������
     */
    private String query_hphm;
    
    /**
     * ���ܺţ�����������
     */
    private String query_cjbh;
    
    /**
     * �����˻���ţ�����������
     */
    private String query_srthsh;
    
    /**
     * ˰����˰֤���ţ�����������
     */
    private String query_wszm;
    
    /**
     * ˰����˰֤��ԭ���ֽɿ���ţ�����������
     */
    private String query_wszmh;
    
    String wszmwszhs = ""; //�����˰֤��
    String wszmndzbs = ""; //�������ֱ�
    
    
    /**
     * ҳ�����ݼ�
     */
    private ArrayList dataList = new ArrayList();
    
    /**
     * ��ӡҳ�����ݼ�
     */
    private ArrayList dyDataList = new ArrayList();
    
    
    /**
     * ��Ʊ���List
     */
    private ArrayList fpList = new ArrayList();
    
    
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
	 
	 /**
	  * ����ɹ���־
	  */
	 private String bccgbz = "0";
	 

	public void reset (ActionMapping actionMapping,
	            HttpServletRequest httpServletRequest)
	 {
		 this.actionType = "Show";
		 this.sspzlxdm = "01";
		 this.query_jsjdm = "";
		 this.query_sphm = "";
		 this.query_qsrq = "";
		 this.query_jzrq = "";
		 this.query_ndzb = "";
		 this.query_wszh = "";
		 this.query_bdwsz = "";
		 this.query_hphm = "";
		 this.query_cjbh = "";
		 this.query_srthsh = "";
		 this.query_wszm = "";
		 this.query_wszmh = "";
		 this.nextPage = "1";
		 this.totalpage = "0";
		 this.bccgbz = "0";
		 dataList = new ArrayList();
		 dyDataList = new ArrayList();
	 }
	 
	 public Object getData()
	 {
		 Map dataMap = new HashMap();
		 dataMap.put("sspzlxdm",this.sspzlxdm);
		 dataMap.put("query_jsjdm",this.query_jsjdm);
		 dataMap.put("query_sphm",this.query_sphm);
		 dataMap.put("query_qsrq",this.query_qsrq);
		 dataMap.put("query_jzrq",this.query_jzrq);
		 dataMap.put("query_wszh",this.query_wszh);
		 dataMap.put("query_ndzb",this.query_ndzb);
		 dataMap.put("query_bdwsz",this.query_bdwsz);
		 dataMap.put("query_hphm",this.query_hphm);
		 dataMap.put("query_cjbh",this.query_cjbh);
		 dataMap.put("query_srthsh",this.query_srthsh);
		 dataMap.put("query_wszm",this.query_wszm);
		 dataMap.put("query_wszmh",this.query_wszmh);
		 dataMap.put("pzlxdm",this.pzlxdm);
		 
		 return dataMap;
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

	public String getPzzldm() {
		return pzzldm;
	}

	public void setPzzldm(String pzzldm) {
		this.pzzldm = pzzldm;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getZhdm() {
		return zhdm;
	}

	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
	}

	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}

	public String getSspzlxdm() {
		return sspzlxdm;
	}

	public void setSspzlxdm(String sspzlxdm) {
		this.sspzlxdm = sspzlxdm;
	}

	public WszmForm() 
	{
    }

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

	public String getQuery_jsjdm() {
		return query_jsjdm;
	}

	public void setQuery_jsjdm(String query_jsjdm) {
		this.query_jsjdm = query_jsjdm;
	}

	public String getQuery_sphm() {
		return query_sphm;
	}

	public void setQuery_sphm(String query_sphm) {
		this.query_sphm = query_sphm;
	}

	public String getQuery_qsrq() {
		return query_qsrq;
	}

	public void setQuery_qsrq(String query_qsrq) {
		this.query_qsrq = query_qsrq;
	}

	public String getQuery_jzrq() {
		return query_jzrq;
	}

	public void setQuery_jzrq(String query_jzrq) {
		this.query_jzrq = query_jzrq;
	}

	public String getQuery_wszh() {
		return query_wszh;
	}

	public void setQuery_wszh(String query_wszh) {
		this.query_wszh = query_wszh;
	}

	public String getQuery_ndzb() {
		return query_ndzb;
	}

	public void setQuery_ndzb(String query_ndzb) {
		this.query_ndzb = query_ndzb;
	}

	public String getQuery_bdwsz() {
		return query_bdwsz;
	}

	public void setQuery_bdwsz(String query_bdwsz) {
		this.query_bdwsz = query_bdwsz;
	}

	public String getQuery_hphm() {
		return query_hphm;
	}

	public void setQuery_hphm(String query_hphm) {
		this.query_hphm = query_hphm;
	}

	public String getQuery_cjbh() {
		return query_cjbh;
	}

	public void setQuery_cjbh(String query_cjbh) {
		this.query_cjbh = query_cjbh;
	}

	public String getQuery_srthsh() {
		return query_srthsh;
	}

	public void setQuery_srthsh(String query_srthsh) {
		this.query_srthsh = query_srthsh;
	}

	public String getQuery_wszm() {
		return query_wszm;
	}

	public void setQuery_wszm(String query_wszm) {
		this.query_wszm = query_wszm;
	}

	public String getTfrq() {
		return tfrq;
	}

	public void setTfrq(String tfrq) {
		this.tfrq = tfrq;
	}

	public String getYpzh() {
		return ypzh;
	}

	public void setYpzh(String ypzh) {
		this.ypzh = ypzh;
	}

	public String getYpzzldm() {
		return ypzzldm;
	}

	public void setYpzzldm(String ypzzldm) {
		this.ypzzldm = ypzzldm;
	}

	public String getYwszh() {
		return ywszh;
	}

	public void setYwszh(String ywszh) {
		this.ywszh = ywszh;
	}

	public String getYndzb() {
		return yndzb;
	}

	public void setYndzb(String yndzb) {
		this.yndzb = yndzb;
	}

	public String getDfswjg() {
		return dfswjg;
	}

	public void setDfswjg(String dfswjg) {
		this.dfswjg = dfswjg;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getHjjedx() {
		return hjjedx;
	}

	public void setHjjedx(String hjjedx) {
		this.hjjedx = hjjedx;
	}

	public ArrayList getDyDataList() {
		return dyDataList;
	}

	public void setDyDataList(ArrayList dyDataList) {
		this.dyDataList = dyDataList;
	}

	public String getMxSz() {
		return mxSz;
	}

	public void setMxSz(String mxSz) {
		this.mxSz = mxSz;
	}

	public String getMxPmmc() {
		return mxPmmc;
	}

	public void setMxPmmc(String mxPmmc) {
		this.mxPmmc = mxPmmc;
	}

	public String getMxSkssrq() {
		return mxSkssrq;
	}

	public void setMxSkssrq(String mxSkssrq) {
		this.mxSkssrq = mxSkssrq;
	}

	public String getMxSjse() {
		return mxSjse;
	}

	public void setMxSjse(String mxSjse) {
		this.mxSjse = mxSjse;
	}

	public String getTfrqYear() {
		return tfrqYear;
	}

	public void setTfrqYear(String tfrqYear) {
		this.tfrqYear = tfrqYear;
	}

	public String getTfrqMonth() {
		return tfrqMonth;
	}

	public void setTfrqMonth(String tfrqMonth) {
		this.tfrqMonth = tfrqMonth;
	}

	public String getTfrqDate() {
		return tfrqDate;
	}

	public void setTfrqDate(String tfrqDate) {
		this.tfrqDate = tfrqDate;
	}

	public String getHeadWszh() {
		return headWszh;
	}

	public void setHeadWszh(String headWszh) {
		this.headWszh = headWszh;
	}

	public String getNdzb() {
		return ndzb;
	}

	public void setNdzb(String ndzb) {
		this.ndzb = ndzb;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getYpzrkrq() {
		return ypzrkrq;
	}

	public void setYpzrkrq(String ypzrkrq) {
		this.ypzrkrq = ypzrkrq;
	}

	public String getDycs() {
		return dycs;
	}

	public void setDycs(String dycs) {
		this.dycs = dycs;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getMxRkrq() {
		return mxRkrq;
	}

	public void setMxRkrq(String mxRkrq) {
		this.mxRkrq = mxRkrq;
	}

	public String getBccgbz() {
		return bccgbz;
	}

	public void setBccgbz(String bccgbz) {
		this.bccgbz = bccgbz;
	}

	public String getKjswjgzzjgdm() {
		return kjswjgzzjgdm;
	}

	public void setKjswjgzzjgdm(String kjswjgzzjgdm) {
		this.kjswjgzzjgdm = kjswjgzzjgdm;
	}

	public String getSsswjgzzjgdm() {
		return ssswjgzzjgdm;
	}

	public void setSsswjgzzjgdm(String ssswjgzzjgdm) {
		this.ssswjgzzjgdm = ssswjgzzjgdm;
	}

	public String getMxYpzh() {
		return mxYpzh;
	}

	public void setMxYpzh(String mxYpzh) {
		this.mxYpzh = mxYpzh;
	}

	public String getMxYwszh() {
		return mxYwszh;
	}

	public void setMxYwszh(String mxYwszh) {
		this.mxYwszh = mxYwszh;
	}

	public String getWszmsjm() {
		return wszmsjm;
	}

	public void setWszmsjm(String wszmsjm) {
		this.wszmsjm = wszmsjm;
	}

	public String getYhtpbz() {
		return yhtpbz;
	}

	public void setYhtpbz(String yhtpbz) {
		this.yhtpbz = yhtpbz;
	}

	public String getHjje2() {
		return hjje2;
	}

	public void setHjje2(String hjje2) {
		this.hjje2 = hjje2;
	}

	public String getHjjedx2() {
		return hjjedx2;
	}

	public void setHjjedx2(String hjjedx2) {
		this.hjjedx2 = hjjedx2;
	}

	public String getPzlxdm() {
		return pzlxdm;
	}

	public void setPzlxdm(String pzlxdm) {
		this.pzlxdm = pzlxdm;
	}

	public String getQuery_wszmh() {
		return query_wszmh;
	}

	public void setQuery_wszmh(String query_wszmh) {
		this.query_wszmh = query_wszmh;
	}

	public String getYsphm() {
		return ysphm;
	}

	public void setYsphm(String ysphm) {
		this.ysphm = ysphm;
	}

	public ArrayList getFpList() {
		return fpList;
	}

	public void setFpList(ArrayList fpList) {
		this.fpList = fpList;
	}

	public String getWszmwszhs() {
		return wszmwszhs;
	}

	public void setWszmwszhs(String wszmwszhs) {
		this.wszmwszhs = wszmwszhs;
	}

	public String getWszmndzbs() {
		return wszmndzbs;
	}

	public void setWszmndzbs(String wszmndzbs) {
		this.wszmndzbs = wszmndzbs;
	}

}
