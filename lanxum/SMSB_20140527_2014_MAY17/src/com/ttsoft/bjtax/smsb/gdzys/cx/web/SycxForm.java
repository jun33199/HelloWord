package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class SycxForm extends BaseForm {

	//���������
	private String jsjdm;
	
	//˰Դ����
	private String sylx;
	
	//��˰������
	private String nsrlx;
	
	//˰Դ���ʹ���
	private String sylxdm;
	
	/*--��ѯʱ��--*/
	private String starttime;
	private String endtime;
	
	/*--���ֻ���--*/	
	private String fjmc=null;
	private String fjdm=null;			//�־ִ���
	private String swsmc=null;
	private String swsdm=null;			//˰��������
	
	/*--��ҳ--*/
    private int maxPage=-1;        	//��ҳ��  --(-1��ʾ��δ�����ҳ��)
    private int pageSizeXx =10; 	//ÿҳ��ʾ������Ĭ��Ϊ10
    private int currentPageXx = 1;  //��ǰҳ��
    
    /*--��ѯ����2������--*/
    private List fjlist ;			//�־��б�
    private List swslist = new ArrayList();			//˰�����б�
    
    //�����б�
    private List infList;
    
    //��ϸ��ѯ--�걨�����к�
    private String sbbxlh;
    
    //��Ϣ����
    private int totalnum = -1;
    
/*-----------------------------------------------------------------------------------------------------------*/
    
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getSylx() {
		return sylx;
	}
	
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getSwsmc() {
		return swsmc;
	}
	public void setSwsmc(String swsmc) {
		this.swsmc = swsmc;
	}
	public void setSylx(String sylx) {
		this.sylx = sylx;
	}
	public String getNsrlx() {
		return nsrlx;
	}
	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}
	public String getSylxdm() {
		return sylxdm;
	}
	public void setSylxdm(String sylxdm) {
		this.sylxdm = sylxdm;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getFjdm() {
		return fjdm;
	}
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	public String getSwsdm() {
		return swsdm;
	}
	public void setSwsdm(String swsdm) {
		this.swsdm = swsdm;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getPageSizeXx() {
		return pageSizeXx;
	}
	public void setPageSizeXx(int pageSizeXx) {
		this.pageSizeXx = pageSizeXx;
	}
	public int getCurrentPageXx() {
		return currentPageXx;
	}
	public void setCurrentPageXx(int currentPageXx) {
		this.currentPageXx = currentPageXx;
	}
	public List getFjlist() {
		return fjlist;
	}
	public void setFjlist(List fjlist) {
		this.fjlist = fjlist;
	}
	public List getSwslist() {
		return swslist;
	}
	public void setSwslist(List swslist) {
		this.swslist = swslist;
	}
	public List getInfList() {
		return infList;
	}
	public void setInfList(List infList) {
		this.infList = infList;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	
    
}
