package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.form.Pageable;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.LabelValueXMLVO;

public class AlterFangtuVO extends YWRootVO implements XMLVOInterface, Pageable {

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

    //分页属性
	private String totalPageNum;
    private String totalItemsNum;
    private String currentPageNum;
    private String pageSize;

	//证件类型
	private List zhengjianList = new ArrayList();
	//政策列表
	private List zhengceList = new ArrayList();
	//减免原因列表
	private List jianMianList = new ArrayList();
	//应税原因列表
	private List yingShuiList = new ArrayList();
	//减免原因列表
	private List jianMianMJList = new ArrayList();
	//应税原因列表
	private List yingShuiMJList = new ArrayList();
	//是否代缴
	private List daiJiaoList = new ArrayList();
	//坐落区县列表
	private List regionList = new ArrayList();
	//城镇土地使用税税率
	private List slList = new ArrayList();

	// 各种行数据的列表,每种DataVO 包含着登记数据,变更数据,操作标识
	private List fwZiyongList = new ArrayList();
	private List fwChuzuList = new ArrayList();
	private List fwChengzuList = new ArrayList();
	private List tdZiyongList = new ArrayList();
	private List tdChuzuList = new ArrayList();
	private List tdChengzuList = new ArrayList();
	
	
	
	public AlterFangtuVO() {
		super();
		String pack = "com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.";
		     
		m.put("zhengceList", pack + "LabelValueXMLVO");
		m.put("zhengjianList", pack + "LabelValueXMLVO");
		m.put("jianMianList", pack + "LabelValueXMLVO");
		m.put("yingShuiList", pack + "LabelValueXMLVO");
		m.put("jianMianMJList", pack + "LabelValueXMLVO");
		m.put("yingShuiMJList", pack + "LabelValueXMLVO");
		m.put("daiJiaoList", pack + "LabelValueXMLVO");
		m.put("regionList", pack + "LabelValueXMLVO");
		m.put("slList", pack + "LabelValueXMLVO");
		
		m.put("fwZiyongList", pack + "FWZiyongDataVO");
		m.put("fwChuzuList", pack + "FWChuzuDataVO");
		m.put("fwChengzuList", pack + "FWChengzuDataVO");
		m.put("tdZiyongList", pack + "TDZiyongDataVO");
		m.put("tdChuzuList", pack + "TDChuzuDataVO");
		m.put("tdChengzuList", pack + "TDChengzuDataVO");
	}

	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
		xmlstr += XMLBuildUtil
				.appendStringElement("taxpayerName", taxpayerName);
		xmlstr += XMLBuildUtil.appendStringElement("taxpayerId", taxpayerId);
		xmlstr += XMLBuildUtil.appendStringElement("inputDate", inputDate);
		xmlstr += XMLBuildUtil.appendStringElement("cat", cat);
		xmlstr += XMLBuildUtil.appendStringElement("destCat", destCat);

		xmlstr += XMLBuildUtil.appendStringElement("totalPageNum", String.valueOf(totalPageNum));
		xmlstr += XMLBuildUtil.appendStringElement("totalItemsNum", String.valueOf(totalItemsNum));
		xmlstr += XMLBuildUtil.appendStringElement("currentPageNum", String.valueOf(currentPageNum));
		xmlstr += XMLBuildUtil.appendStringElement("pageSize", String.valueOf(pageSize));

		
		if (this.zhengjianList != null && this.zhengjianList.size() > 0) {
			for (int i = 0; i < this.zhengjianList.size(); i++) {
				xmlstr += "<zhengjianList>";
				xmlstr += ((LabelValueXMLVO) this.zhengjianList.get(i)).toXML();
				xmlstr += "</zhengjianList>";
			}
		}
		if (this.zhengceList != null && this.zhengceList.size() > 0) {
			for (int i = 0; i < this.zhengceList.size(); i++) {
				xmlstr += "<zhengceList>";
				xmlstr += ((LabelValueXMLVO) this.zhengceList.get(i)).toXML();
				xmlstr += "</zhengceList>";
			}
		}
		if (this.jianMianList != null && this.jianMianList.size() > 0) {
			for (int i = 0; i < this.jianMianList.size(); i++) {
				xmlstr += "<jianMianList>";
				xmlstr += ((LabelValueXMLVO) this.jianMianList.get(i)).toXML();
				xmlstr += "</jianMianList>";
			}
		}
		if (this.yingShuiList != null && this.yingShuiList.size() > 0) {
			for (int i = 0; i < this.yingShuiList.size(); i++) {
				xmlstr += "<yingShuiList>";
				xmlstr += ((LabelValueXMLVO) this.yingShuiList.get(i)).toXML();
				xmlstr += "</yingShuiList>";
			}
		}
		if (this.jianMianMJList != null && this.jianMianMJList.size() > 0) {
			for (int i = 0; i < this.jianMianMJList.size(); i++) {
				xmlstr += "<jianMianMJList>";
				xmlstr += ((LabelValueXMLVO) this.jianMianMJList.get(i)).toXML();
				xmlstr += "</jianMianMJList>";
			}
		}
		if (this.yingShuiMJList != null && this.yingShuiMJList.size() > 0) {
			for (int i = 0; i < this.yingShuiMJList.size(); i++) {
				xmlstr += "<yingShuiMJList>";
				xmlstr += ((LabelValueXMLVO) this.yingShuiMJList.get(i)).toXML();
				xmlstr += "</yingShuiMJList>";
			}
		}
		if (this.daiJiaoList != null && this.daiJiaoList.size() > 0) {
			for (int i = 0; i < this.daiJiaoList.size(); i++) {
				xmlstr += "<daiJiaoList>";
				xmlstr += ((LabelValueXMLVO) this.daiJiaoList.get(i)).toXML();
				xmlstr += "</daiJiaoList>";
			}
		}
		if (this.regionList != null && this.regionList.size() > 0) {
			for (int i = 0; i < this.regionList.size(); i++) {
				xmlstr += "<regionList>";
				xmlstr += ((LabelValueXMLVO) this.regionList.get(i)).toXML();
				xmlstr += "</regionList>";
			}
		}
		if (this.slList != null && this.slList.size() > 0) {
			for (int i = 0; i < this.slList.size(); i++) {
				xmlstr += "<slList>";
				xmlstr += ((LabelValueXMLVO) this.slList.get(i)).toXML();
				xmlstr += "</slList>";
			}
		}
		if (this.fwZiyongList != null && this.fwZiyongList.size() > 0) {
			for (int i = 0; i < this.fwZiyongList.size(); i++) {
				xmlstr += "<fwZiyongList>";
				xmlstr += ((FWZiyongDataVO) this.fwZiyongList.get(i)).toXML();
				xmlstr += "</fwZiyongList>";
			}
		}
		
		if (this.fwChuzuList != null && this.fwChuzuList.size() > 0) {
			for (int i = 0; i < this.fwChuzuList.size(); i++) {
				xmlstr += "<fwChuzuList>";
				xmlstr += ((FWChuzuDataVO) this.fwChuzuList.get(i)).toXML();
				xmlstr += "</fwChuzuList>";
			}
		}
		if (this.fwChengzuList != null && this.fwChengzuList.size() > 0) {
			for (int i = 0; i < this.fwChengzuList.size(); i++) {
				xmlstr += "<fwChengzuList>";
				xmlstr += ((FWChengzuDataVO) this.fwChengzuList.get(i)).toXML();
				xmlstr += "</fwChengzuList>";
			}
		}
		if (this.tdZiyongList != null && this.tdZiyongList.size() > 0) {
			for (int i = 0; i < this.tdZiyongList.size(); i++) {
				xmlstr += "<tdZiyongList>";
				xmlstr += ((TDZiyongDataVO) this.tdZiyongList.get(i)).toXML();
				xmlstr += "</tdZiyongList>";
			}
		}
		if (this.tdChuzuList != null && this.tdChuzuList.size() > 0) {
			for (int i = 0; i < this.tdChuzuList.size(); i++) {
				xmlstr += "<tdChuzuList>";
				xmlstr += ((TDChuzuDataVO) this.tdChuzuList.get(i)).toXML();
				xmlstr += "</tdChuzuList>";
			}
		}
		if (this.tdChengzuList != null && this.tdChengzuList.size() > 0) {
			for (int i = 0; i < this.tdChengzuList.size(); i++) {
				xmlstr += "<tdChengzuList>";
				xmlstr += ((TDChengzuDataVO) this.tdChengzuList.get(i)).toXML();
				xmlstr += "</tdChengzuList>";
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
		AlterFangtuVO vo = new AlterFangtuVO();
		vo.getFwZiyongList().add( new FWZiyongDataVO());
		vo.getFwChuzuList().add( new FWChuzuDataVO());
		vo.getFwChengzuList().add( new FWChengzuDataVO());
		vo.getTdZiyongList().add( new TDZiyongDataVO());
		vo.getTdChuzuList().add( new TDChuzuDataVO());
		vo.getTdChengzuList().add( new TDChengzuDataVO());
		
		vo.getZhengceList().add( new LabelValueXMLVO());
		vo.getJianMianList().add( new LabelValueXMLVO());
		vo.getYingShuiList().add( new LabelValueXMLVO());
		vo.getJianMianMJList().add( new LabelValueXMLVO());
		vo.getYingShuiMJList().add( new LabelValueXMLVO());
		vo.getDaiJiaoList().add( new LabelValueXMLVO("label_1","value_1"));
		vo.getDaiJiaoList().add( new LabelValueXMLVO("label_2","value_2"));
		
		vo.getFwZiyongList().add( new FWZiyongDataVO());
		
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

	public List getDaiJiaoList() {
		return daiJiaoList;
	}

	public void setDaiJiaoList(List daiJiaoList) {
		this.daiJiaoList = daiJiaoList;
	}

	public List getJianMianList() {
		return jianMianList;
	}

	public void setJianMianList(List jianMianList) {
		this.jianMianList = jianMianList;
	}

	public List getJianMianMJList() {
		return jianMianMJList;
	}

	public void setJianMianMJList(List jianMianMJList) {
		this.jianMianMJList = jianMianMJList;
	}

	public List getYingShuiList() {
		return yingShuiList;
	}

	public void setYingShuiList(List yingShuiList) {
		this.yingShuiList = yingShuiList;
	}

	public List getYingShuiMJList() {
		return yingShuiMJList;
	}

	public void setYingShuiMJList(List yingShuiMJList) {
		this.yingShuiMJList = yingShuiMJList;
	}

	public List getZhengceList() {
		return zhengceList;
	}

	public void setZhengceList(List zhengceList) {
		this.zhengceList = zhengceList;
	}

	public List getZhengjianList() {
		return zhengjianList;
	}

	public void setZhengjianList(List zhengjianList) {
		this.zhengjianList = zhengjianList;
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

	public List getRegionList() {
		return regionList;
	}

	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}

	public List getSlList() {
		return slList;
	}

	public void setSlList(List slList) {
		this.slList = slList;
	}
	
}
