package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>
 * Title: ������˰��������ϵͳ--����ռ��˰��ѯ����
 * </p>
 * <p>
 * Description: �ɿ����ѯForm
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class GdzysJksCxForm extends BaseForm{
	//��ʼ����
	private String ksrq="";
	//��������
	private String jsrq="";
	//���������
	private String jsjdm="";
	//��׼ռ���ĺ�
	private String pzzdwh="";
	//�ɿ���״̬
	private String jkszt="";
	 //˰����
	private String cxdqjs="";
	 //��������
	 private String dqjs="";
	 //�־�List
	 private List swdwlist;
	 //˰����List
	 private List cxswdwlist;

	 //�Ƿ�����˲�ѯ����
	 private boolean queryFlag = false;
	 //����List
	 private List dataList;
	 //�û�����
	 private String yhjb="";
 
	/**
     * ��ҳ��
     */
    private int maxPage;
	
    /**
     * ÿҳ��ʾ�ļ�¼����Ĭ��Ϊ10
     */
    private int pageSize =10;
    /**
     * ��ǰҳ��
     */
    private int currentPage = 1;
    /**
     * �˶Բ�һ�±���
     */
    private int totalRowCount = 0;
 
	 
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJkszt() {
		return jkszt;
	}

	public void setJkszt(String jkszt) {
		this.jkszt = jkszt;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getDqjs() {
		return dqjs;
	}

	public void setDqjs(String dqjs) {
		this.dqjs = dqjs;
	}

	public List getSwdwlist() {
		return swdwlist;
	}

	public void setSwdwlist(List swdwlist) {
		this.swdwlist = swdwlist;
	}

	public List getCxswdwlist() {
		return cxswdwlist;
	}

	public void setCxswdwlist(List cxswdwlist) {
		this.cxswdwlist = cxswdwlist;
	}

	public boolean isQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(boolean queryFlag) {
		this.queryFlag = queryFlag;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getYhjb() {
		return yhjb;
	}

	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}
	
	
	 public int getMaxPage (){
	        try{
	            maxPage = this.getTotalRowCount() / this.getPageSize();
	            int number = totalRowCount % pageSize;
	            if(number != 0){
	                maxPage += 1;
	            }
	        } catch(Exception ex){
	            maxPage = 0;
	        }
	        return maxPage;

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getPzzdwh() {
		return pzzdwh;
	}

	public void setPzzdwh(String pzzdwh) {
		this.pzzdwh = pzzdwh;
	}
	
}
