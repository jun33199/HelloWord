package com.ttsoft.bjtax.smsb.gdzys.sbzs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;
/**
 * <p>
 * Title: ������˰��������ϵͳ--����ռ��˰���չ���
 * </p>
 * <p>
 * Description: �����ɿ���Form
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class GdzysCxjksForm extends BaseForm{
	//��˰������
	private String nsrmc="";
	//���������
	private String jsjdm="";
	//��׼ռ���ĺ�
	private String pzjdwh="";
	//�걨�����к�
	private String sbbxlh="";
	
	//�걨�����к�
	private String cxSbbxlh="";
	//��ѯ�����ʾ��ʶ(1:��ʾ��ʾ��Ϣ��0������ʾ��ʾ��Ϣ)
	private String cxJgTsFlag="0";
	//�ɿ�ƾ֤��
	private String cxJkpzh="";
	
	//�걨���
	private String cxSbbh="";
	//���������
	private String cxJsjdm="";
	
	//��ѯ�������
	private List dataList = new ArrayList();
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getPzjdwh() {
		return pzjdwh;
	}

	public void setPzjdwh(String pzjdwh) {
		this.pzjdwh = pzjdwh;
	}

	public String getSbbxlh() {
		return sbbxlh;
	}

	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getCxSbbxlh() {
		return cxSbbxlh;
	}

	public void setCxSbbxlh(String cxSbbxlh) {
		this.cxSbbxlh = cxSbbxlh;
	}

	public String getCxJkpzh() {
		return cxJkpzh;
	}

	public void setCxJkpzh(String cxJkpzh) {
		this.cxJkpzh = cxJkpzh;
	}

	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}

	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}

	public String getCxSbbh() {
		return cxSbbh;
	}

	public void setCxSbbh(String cxSbbh) {
		this.cxSbbh = cxSbbh;
	}

	public String getCxJsjdm() {
		return cxJsjdm;
	}

	public void setCxJsjdm(String cxJsjdm) {
		this.cxJsjdm = cxJsjdm;
	}


}
