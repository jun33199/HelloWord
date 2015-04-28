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
* <p>Title:完税证明的form </p>
*
* <p>Description:完税证明的form </p>
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
     * 填发日期
     */
    private String tfrq;
    
	/**
     * 税收票证来源代码
     */
    private String sspzlxdm;
    
    /**
     * 税收票证类型代码
     */
    private String pzlxdm = CodeConstant.WSZM_SGWSPZ_JS;
    
    /**
     * 原凭证号入库日期
     */
    private String ypzrkrq;
    
    /**
     * 原凭证号
     */
    private String ysphm;
    
    /**
     * 原凭证号
     */
    private String ypzh;
    
    /**
     * 原票证种类代码
     */
    private String ypzzldm;
    
    /**
     * 原完税证明完税证号
     */
    private String ywszh;
    
    /**
     * 原年度字别
     */
    private String yndzb;
    
    /**
     * 地方税务机关
     */
    private String dfswjg; //地方税务机关
    
    /**
     * 名细信息，原凭证号
     */
    private String mxYpzh; //名细信息，原凭证号
    
    /**
     * 名细信息，税种名称
     */
    private String mxYwszh; //名细信息，原完税证号
    
    /**
     * 名细信息，税种名称
     */
    private String mxSz; //名细信息，税种名称

    /**
     * 明细信息，品目名称，其实是税种税目名称
     */
    private String mxPmmc; //名细信息，品目名称，其实是税种税目名称
    
    /**
     * 明细信息，税款所属日期
     */
    private String mxSkssrq; //税款所属开始日期

    /**
     * 明细信息，实缴税额
     */
    private String mxSjse; //名细信息，实缴税额
    
    /**
     * 明细信息，实缴税额
     */
    private String mxRkrq; //名细信息，入库日期

    /**
     * 填发日期，年
     */
    private String tfrqYear; //填发日期，年

    /**
     * 填发日期，月
     */
    private String tfrqMonth; //填发日期，月

    /**
     * 填发日期，日
     */
    private String tfrqDate; //填发日期，日

    /**
     * 完税证号
     */
    private String headWszh; //完税证号

    /**
     * 年度字别
     */
    private String ndzb; //年度字别
    
    /**
     * 备注
     */
    private String bz;
    
    /**
     * 随机码
     */
    private String wszmsjm; //随机码
    
    /**
     * 银行退票标志
     */
    private String yhtpbz;
    
    /**
     * 金额合计
     */
    private String hjje;
    
    /**
     * 金额合计（大写）
     */
    private String hjjedx;
    
    /**
     * 特殊处理的金额合计
     */
    private String hjje2;
    
    /**
     * 特殊处理的金额合计（大写）
     */
    private String hjjedx2;
    
    /**
     * 计算机代码（税务所）
     */
    private String jsjdm;
    
    /**
     * 打印次数
     */
    private String dycs;
    
    /**
     * 纳税人名称
     */
    private String nsrmc;
    
    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    
    /**
     * 所属税务机关组织机构代码
     */
    private String ssswjgzzjgdm;
    
    /**
     * 开具税务机关组织结构代码
     */
    private String kjswjgzzjgdm;
    
    /**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm;
    
    /**
     * 税务机关组织结构名称
     */
    private String swjgzzjgmc;
    
    /**
     * 录入人
     */
    private String lrr;
    
    /**
     * 录入日期
     */
    private String lrrq;
    
    /**
     * 帐户代码
     */
    private String zhdm;
    
    /**
     * 票证种类代码
     */
    private String pzzldm;
    
    /**
     * 计算机代码（输入条件）
     */
    private String query_jsjdm;
    
    /**
     * 税票号码（输入条件）
     */
    private String query_sphm;
    
    /**
     * 入库起始日期（输入条件）
     */
    private String query_qsrq;
    
    /**
     * 入库截止日期（输入条件）
     */
    private String query_jzrq;
    
    /**
     * 完税证号码（输入条件）
     */
    private String query_wszh;
    
    /**
     * 完税证年度字别（输入条件）
     */
    private String query_ndzb;
    
    /**
     * 完税证号码保单（输入条件）
     */
    private String query_bdwsz;
    
    /**
     * 号牌号码（输入条件）
     */
    private String query_hphm;
    
    /**
     * 车架号（输入条件）
     */
    private String query_cjbh;
    
    /**
     * 收入退还书号（输入条件）
     */
    private String query_srthsh;
    
    /**
     * 税收完税证明号（输入条件）
     */
    private String query_wszm;
    
    /**
     * 税收完税证明原收现缴款书号（输入条件）
     */
    private String query_wszmh;
    
    String wszmwszhs = ""; //多个完税证号
    String wszmndzbs = ""; //多个年度字别
    
    
    /**
     * 页面数据集
     */
    private ArrayList dataList = new ArrayList();
    
    /**
     * 打印页面数据集
     */
    private ArrayList dyDataList = new ArrayList();
    
    
    /**
     * 分票后的List
     */
    private ArrayList fpList = new ArrayList();
    
    
    /**
	 * 页面显示尺寸
	 */
	private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

	 /**
	  * 页码
	  */
	 private String nextPage = "1";

	 /**
	  * 页数
	  */
	 private String totalpage = "0";

	 /**
	  * 页面提示信息
	  */
	 private String message;

	 /**
	  * 当前页码
	  */
	 private String curPage;
	 
	 /**
	  * 保存成功标志
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
