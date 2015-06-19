package com.ttsoft.bjtax.shenbao.fangtu.form;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.shenbao.fangtu.total.FwChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdZiyongTotal;

public class FangtuForm implements java.io.Serializable {
	//��������
	private String jsjdm;
	//��˰������
	private String taxpayerName;
	//��˰��ʶ���
	private String taxpayerId;
	
	//�������
	private String inputDate;

    //��������������͵ķ�����Ϣ
	private int cat;
	//Ҫȡ�õ��������͵ķ�����Ϣ
	private int destCat;
	
	//������Ϣ
	private List list = new ArrayList();
	
	//��ҳ��Ϣ
	private String totalPageNum;
	private String totalItemsNum;
	private String currentPageNum;
	private String pageSize;

	
	//�����б�
	private List zhengceList = new ArrayList();
	//����ԭ���б�
	private List jianMianList = new ArrayList();
	//Ӧ˰ԭ���б�
	private List yingShuiList = new ArrayList();
	//����ԭ���б�
	private List jianMianMJList = new ArrayList();
	//Ӧ˰ԭ���б�
	private List yingShuiMJList = new ArrayList();
	//�Ƿ����
	private List daiJiaoList = new ArrayList();
	
	private FwZiyongTotal fwZiyongTotal = new FwZiyongTotal();
	private TdZiyongTotal tdZiyongTotal = new TdZiyongTotal();
	private FwChuzuTotal fwChuzuTotal = new FwChuzuTotal();
	private TdChuzuTotal tdChuzuTotal = new TdChuzuTotal();
	
	
	public int getCat() {
		return cat;
	}
	public void setCat(int cat) {
		this.cat = cat;
	}
	public int getDestCat() {
		return destCat;
	}
	public void setDestCat(int destCat) {
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
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
	public FwChuzuTotal getFwChuzuTotal() {
		return fwChuzuTotal;
	}
	public void setFwChuzuTotal(FwChuzuTotal fwChuzuTotal) {
		this.fwChuzuTotal = fwChuzuTotal;
	}
	public FwZiyongTotal getFwZiyongTotal() {
		return fwZiyongTotal;
	}
	public void setFwZiyongTotal(FwZiyongTotal fwZiyongTotal) {
		this.fwZiyongTotal = fwZiyongTotal;
	}
	public TdChuzuTotal getTdChuzuTotal() {
		return tdChuzuTotal;
	}
	public void setTdChuzuTotal(TdChuzuTotal tdChuzuTotal) {
		this.tdChuzuTotal = tdChuzuTotal;
	}
	public TdZiyongTotal getTdZiyongTotal() {
		return tdZiyongTotal;
	}
	public void setTdZiyongTotal(TdZiyongTotal tdZiyongTotal) {
		this.tdZiyongTotal = tdZiyongTotal;
	}
	
	
}
