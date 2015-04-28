package com.ttsoft.bjtax.smsb.jccswh.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jccswh.vo.ZjjmsdmVo;
import com.ttsoft.framework.form.BaseForm;

/**
 * �ּܾ���˰����ά�� �ù����Ƕ��ּܾ���˰��Ŀ����ά��
 * 
 * @author tangchangfu 2014-04-22
 * 
 */
public class ZjjmsdmwhForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	private String saveIsSucc;
	private String message;

	private String allNewAddInfo;
	private List smsmList = new ArrayList();
	private List jmxzdlList = new ArrayList();
	private List jmxzxlList = new ArrayList();
	private String lrrmc;
	private String lrrq;

	// ��ѯ����
	private String query_jmslxdm;// ����˰���ʹ���
	private String query_wh;// �ĺ�
	private String query_szdm;// ˰�ִ���
	private String query_jmszcqsnd;// ����˰������ʼ���
	private String query_jmslxdldm;// ����˰���ʹ������
	private String query_jmslxxldm;// ����˰����С�����
	private String query_lrrqKS;// ¼�뿪ʼ����
	private String query_lrrqJS;// ¼���������
	private String query_yxbs;// ��Ч��ʶ��0-��Ч��9-��Ч��

	private ArrayList queryList_onePage = new ArrayList();// ��ѯ���(һҳ)

	// �޸�
	private ZjjmsdmVo vo = new ZjjmsdmVo();
	private String modifyKey_jmslxdm;// �޸�����--����˰���ʹ���
	private String updateType = "";// ����ֵ����UPDATE_YXBS��--������Ч��ʶ
									// ��UPDATE_ALL��--�޸�������Ϣ

	/**
	 * ��ѯ���
	 */
	private List queryResList = new ArrayList();

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
	 * ��ǰҳ��
	 */
	private String curPage;

	public List getQueryResList() {
		return queryResList;
	}

	public void setQueryResList(List queryResList) {
		this.queryResList = queryResList;
	}

	public String getSaveIsSucc() {
		return saveIsSucc;
	}

	public void setSaveIsSucc(String saveIsSucc) {
		this.saveIsSucc = saveIsSucc;
	}

	public List getSmsmList() {
		return smsmList;
	}

	public void setSmsmList(List smsmList) {
		this.smsmList = smsmList;
	}

	public String getLrrmc() {
		return lrrmc;
	}

	public void setLrrmc(String lrrmc) {
		this.lrrmc = lrrmc;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getJmxzdlList() {
		return jmxzdlList;
	}

	public void setJmxzdlList(List jmxzdlList) {
		this.jmxzdlList = jmxzdlList;
	}

	public List getJmxzxlList() {
		return jmxzxlList;
	}

	public void setJmxzxlList(List jmxzxlList) {
		this.jmxzxlList = jmxzxlList;
	}

	public String getAllNewAddInfo() {
		return allNewAddInfo;
	}

	public void setAllNewAddInfo(String allNewAddInfo) {
		this.allNewAddInfo = allNewAddInfo;
	}

	public String getQuery_jmslxdm() {
		return query_jmslxdm;
	}

	public void setQuery_jmslxdm(String query_jmslxdm) {
		this.query_jmslxdm = query_jmslxdm;
	}

	public String getQuery_wh() {
		return query_wh;
	}

	public void setQuery_wh(String query_wh) {
		this.query_wh = query_wh;
	}

	public String getQuery_szdm() {
		return query_szdm;
	}

	public void setQuery_szdm(String query_szdm) {
		this.query_szdm = query_szdm;
	}

	public String getQuery_jmszcqsnd() {
		return query_jmszcqsnd;
	}

	public void setQuery_jmszcqsnd(String query_jmszcqsnd) {
		this.query_jmszcqsnd = query_jmszcqsnd;
	}

	public String getQuery_jmslxdldm() {
		return query_jmslxdldm;
	}

	public void setQuery_jmslxdldm(String query_jmslxdldm) {
		this.query_jmslxdldm = query_jmslxdldm;
	}

	public String getQuery_jmslxxldm() {
		return query_jmslxxldm;
	}

	public void setQuery_jmslxxldm(String query_jmslxxldm) {
		this.query_jmslxxldm = query_jmslxxldm;
	}

	public String getQuery_yxbs() {
		return query_yxbs;
	}

	public void setQuery_yxbs(String query_yxbs) {
		this.query_yxbs = query_yxbs;
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

	public ZjjmsdmVo getVo() {
		return vo;
	}

	public void setVo(ZjjmsdmVo vo) {
		this.vo = vo;
	}

	public String getModifyKey_jmslxdm() {
		return modifyKey_jmslxdm;
	}

	public void setModifyKey_jmslxdm(String modifyKey_jmslxdm) {
		this.modifyKey_jmslxdm = modifyKey_jmslxdm;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getQuery_lrrqKS() {
		return query_lrrqKS;
	}

	public void setQuery_lrrqKS(String query_lrrqKS) {
		this.query_lrrqKS = query_lrrqKS;
	}

	public String getQuery_lrrqJS() {
		return query_lrrqJS;
	}

	public void setQuery_lrrqJS(String query_lrrqJS) {
		this.query_lrrqJS = query_lrrqJS;
	}

}
