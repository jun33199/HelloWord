package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.form.Pageable;

public class FangtuVO extends YWRootVO implements XMLVOInterface, Pageable {

	private Map m = new HashMap();

	// 计算机编号
	private String jsjdm;

	// 纳税人名称
	private String taxpayerName;

	// 纳税人识别号
	private String taxpayerId;

	// 填表日期
	private String inputDate;

	// 处理的是哪种类型的房土信息
	private String cat;

	// 要取得的哪种类型的房土信息
	private String destCat;

	// 复核标记
	private String auditFlag;

    //分页属性
	private String totalPageNum;
    private String totalItemsNum;
    private String currentPageNum;
    private String pageSize;
    
	// 自用房屋列表
	private List fwZiyongList = new ArrayList();

	private List fwChuzuList = new ArrayList();

	private List fwChengzuList = new ArrayList();

	private List tdZiyongList = new ArrayList();

	private List tdChuzuList = new ArrayList();

	private List tdChengzuList = new ArrayList();

	public FangtuVO() {
		super();
		String pack = "com.ttsoft.bjtax.shenbao.fangtu.xmlvo.";
		m.put("fwZiyongList", pack + "FWZiyongVO");
		m.put("fwChuzuList", pack + "FWChuzuVO");
		m.put("fwChengzuList", pack + "FWChengzuVO");
		m.put("tdZiyongList", pack + "TDZiyongVO");
		m.put("tdChuzuList", pack + "TDChuzuVO");
		m.put("tdChengzuList", pack + "TDChengzuVO");
	}



	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getDestCat() {
		return destCat;
	}

	public void setDestCat(String destCat) {
		this.destCat = destCat;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public String getTaxpayerName() {
		return taxpayerName;
	}

	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}

	public Map getListTypeMap() {
		return m;
	}

	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
		xmlstr += XMLBuildUtil.appendStringElement("taxpayerName", taxpayerName);
		xmlstr += XMLBuildUtil.appendStringElement("taxpayerId", taxpayerId);
		xmlstr += XMLBuildUtil.appendStringElement("inputDate", inputDate);
		xmlstr += XMLBuildUtil.appendStringElement("cat", cat);
		xmlstr += XMLBuildUtil.appendStringElement("destCat", destCat);
		xmlstr += XMLBuildUtil.appendStringElement("auditFlag", auditFlag);

		xmlstr += XMLBuildUtil.appendStringElement("totalPageNum", String.valueOf(totalPageNum));
		xmlstr += XMLBuildUtil.appendStringElement("totalItemsNum", String.valueOf(totalItemsNum));
		xmlstr += XMLBuildUtil.appendStringElement("currentPageNum", String.valueOf(currentPageNum));
		xmlstr += XMLBuildUtil.appendStringElement("pageSize", String.valueOf(pageSize));

		//xml parser 有问题，只能注释掉
//		xmlstr += "<fwZiyongList>";
		if (this.fwZiyongList != null && this.fwZiyongList.size() > 0) {
			for (int i = 0; i < this.fwZiyongList.size(); i++) {
				xmlstr += ((FWZiyongVO) this.fwZiyongList.get(i)).toXML();
			}
		}
//		xmlstr += "</fwZiyongList>";
		
		if (this.fwChuzuList != null && this.fwChuzuList.size() > 0) {
			for (int i = 0; i < this.fwChuzuList.size(); i++) {
				xmlstr += ((FWChuzuVO) this.fwChuzuList.get(i)).toXML();
			}
		}
		if (this.fwChengzuList != null && this.fwChengzuList.size() > 0) {
			for (int i = 0; i < this.fwChengzuList.size(); i++) {
				xmlstr += ((FWChengzuVO) this.fwChengzuList.get(i)).toXML();
			}
		}
		if (this.tdZiyongList != null && this.tdZiyongList.size() > 0) {
			for (int i = 0; i < this.tdZiyongList.size(); i++) {
				xmlstr += ((TDZiyongVO) this.tdZiyongList.get(i)).toXML();
			}
		}
		if (this.tdChuzuList != null && this.tdChuzuList.size() > 0) {
			for (int i = 0; i < this.tdChuzuList.size(); i++) {
				xmlstr += ((TDChuzuVO) this.tdChuzuList.get(i)).toXML();
			}
		}
		if (this.tdChengzuList != null && this.tdChengzuList.size() > 0) {
			for (int i = 0; i < this.tdChengzuList.size(); i++) {
				xmlstr += ((TDChengzuVO) this.tdChengzuList.get(i)).toXML();
			}
		}
		return xmlstr;
	}

	public String toXML() {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
		xmlstr += toXMLHead();
		xmlstr += toXMLChilds();
		xmlstr += "</taxdoc>";
		return xmlstr;
	}

	public static void main(String[] args) {
		FangtuVO vo = new FangtuVO();
		//System.out.println(vo.toXML());

	}

	public List getFwZiyongList() {
		return fwZiyongList;
	}

	public void setFwZiyongList(List fwZiyongList) {
		this.fwZiyongList = fwZiyongList;
	}

	public List getFwChengzuList() {
		return fwChengzuList;
	}

	public void setFwChengzuList(List fwChengzuList) {
		this.fwChengzuList = fwChengzuList;
	}

	public List getFwChuzuList() {
		return fwChuzuList;
	}

	public void setFwChuzuList(List fwChuzuList) {
		this.fwChuzuList = fwChuzuList;
	}

	public List getTdChengzuList() {
		return tdChengzuList;
	}

	public void setTdChengzuList(List tdChengzuList) {
		this.tdChengzuList = tdChengzuList;
	}

	public List getTdChuzuList() {
		return tdChuzuList;
	}

	public void setTdChuzuList(List tdChuzuList) {
		this.tdChuzuList = tdChuzuList;
	}

	public List getTdZiyongList() {
		return tdZiyongList;
	}

	public void setTdZiyongList(List tdZiyongList) {
		this.tdZiyongList = tdZiyongList;
	}



	public String getCurrentPageNum() {
		return currentPageNum;
	}



	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}



	public String getPageSize() {
		return pageSize;
	}



	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}



	public String getTotalItemsNum() {
		return totalItemsNum;
	}



	public void setTotalItemsNum(String totalItemsNum) {
		this.totalItemsNum = totalItemsNum;
	}



	public String getTotalPageNum() {
		return totalPageNum;
	}



	public void setTotalPageNum(String totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


}
