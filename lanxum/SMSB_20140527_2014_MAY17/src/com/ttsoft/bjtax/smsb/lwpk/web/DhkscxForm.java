package com.ttsoft.bjtax.smsb.lwpk.web;

import java.math.BigDecimal;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.framework.form.BaseForm;
/**
 *�����ۿ��ѯ form
 * 201307
 * kanght
 */
public class DhkscxForm extends BaseForm{

	//���������
	private String jsjdm;
	//���
	private String nd;
	//�ۿ���ʼ�·�
	private String kkqsyf;
	//�ۿ���ֹ�·�
	private String kkzzyf;
	//��������model
	private PKJBSJModel pkjbsjModel;
	//������ѯmodel
	private List plkkdhcxlist;
	//������
	private int zts;
	/**
	   * ҳ����ʾ�ߴ�
	   */
	  private String pageSize = String.valueOf(CodeConstant.SMSB_PK_PG_LENGTH);

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
	  
	  
	public int getZts() {
		return zts;
	}
	public void setZts(int zts) {
		this.zts = zts;
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
	public List getPlkkdhcxlist() {
		return plkkdhcxlist;
	}
	public void setPlkkdhcxlist(List plkkdhcxlist) {
		this.plkkdhcxlist = plkkdhcxlist;
	}
	
	public PKJBSJModel getPkjbsjModel() {
		return pkjbsjModel;
	}
	public void setPkjbsjModel(PKJBSJModel pkjbsjModel) {
		this.pkjbsjModel = pkjbsjModel;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getKkqsyf() {
		return kkqsyf;
	}
	public void setKkqsyf(String kkqsyf) {
		this.kkqsyf = kkqsyf;
	}
	public String getKkzzyf() {
		return kkzzyf;
	}
	public void setKkzzyf(String kkzzyf) {
		this.kkzzyf = kkzzyf;
	}
	
}
