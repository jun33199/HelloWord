package com.ttsoft.bjtax.smsb.sgsswszmlr.web;

import java.util.ArrayList;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmVo;
import com.ttsoft.framework.form.BaseForm;

public class SgsswszmlrForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	private String saveIsSucc="";//保存是否成功
	private String hasSaved="";//该信息是否已经被保存过（该属性用于修改时按钮显示控制）
	private String pzlxdm=Constant.CONS_SGLR_PZLXDM_0; //录入凭证类型  0 -- 缴税凭证    1 -- 退税凭证
	private String kjlydm=Constant.CONS_SGLR_KJLYDM_0;//开具来源代码(0税收完税证明管理 1申报换开 2保单换开)
	private String pzlydm=Constant.CONS_SGLR_PZLYDM_08;//凭证来源代码 默认为08--手工完税证明
	private String yxbz=Constant.CONS_SGLR_YXBZ_0;//有效标志 0--有效  1-- 作废
	private String dybz=Constant.CONS_SGLR_DYBZ_0;//打印标志  0--未打印  1--已经打印
	private int    dycs=Constant.CONS_SGLR_DYCS_0;//打印次数默认为0次
	private String cjwszmBYothers=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//已经被其他程序（如出具税收完税证明）开具了完税证明，即该完税证明被开具了完税证明（0--未开具  1--已开具）；用于修改和作废时判断用，如果开具不能修改和作废
	
	private String nsrsbh;//纳税人识别号
	private String swjgmc;//税务机关
	private String nsrmc;//纳税人名称
	private String tfrq;//填发日期
	
	private String wsxxAll;//所有完税信息
	private String sjjeHJ;//金额合计
	private String lrrmc;//录入人姓名
	private String lrrdm;//录入人代码
	private String lrrq;//录入日期
	private String bz;//备注信息
	
	//for 查询
	private String query_nsrsbh;//纳税人识别号
	private String query_nsrmc;//纳税人名称
	private String query_wspzh;//完税证明号码
	private ArrayList queryList_onePage = new ArrayList();//查询结果(一页)
	private String query_type = Constant.CONS_SGLR_QUERY_TYPE_1;//查询类型
	
	
	//完税证明信息主键，格式为“票证种类代码-完税证明号-年度子别”（用途包括打印、打印预览、修改）
	private String wszmKey;
	private String rePrint = Constant.CONS_SGLR_REPRINT_NO;//是否重打
	
	private ArrayList smsmList = new ArrayList();
	private SgsswszmVo printVo = new SgsswszmVo();
	
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
