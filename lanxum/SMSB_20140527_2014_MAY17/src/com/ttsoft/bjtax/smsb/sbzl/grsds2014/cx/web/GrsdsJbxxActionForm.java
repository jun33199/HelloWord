/**
 * @Title:       GrsdsJbxxActionForm.java
 * @Description: TODO
 * @date:        2014-12-10����02:12:47
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-10
 */
public class GrsdsJbxxActionForm extends BaseForm{

	/**
	 * Description��
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description������C����ʹ��a��
	 */
	private List sfzjlxList = new ArrayList();
	
	/**
	 * Description������
	 */
	private List gjList = new ArrayList();

	/**
	 * Description��˰�������֯����
	 */
	private List swjgzzjgList = new ArrayList();
	
	/**
	 * Description����ҵ
	 */
	private List hyList = new ArrayList();
	
	/**
	 * Description���Ǽ�ע������
	 */
	private List djzclxList = new ArrayList();
	
	
	/**
	 * Description�� ���
	 */
	private String nd =String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
	
	/**
	 * Description����ʾ��Ϣ
	 */
	private String msg = "";
	
	 /*--- Description����ҵ��Ϣ  --*/
	 
	/**
	 * Description�����������
	 */
	private String qyxx_jsjdm ="";
	
	/**
	 * Description����Ͷ��������
	 */
	private String qyxx_btzzxm ="";
	
	/**
	 * Description����˰��ʶ���
	 */
	private String qyxx_nsrsbh="";
	
	/**
	 * Description���۽������˱��
	 */
	private String qyxx_kjywrbh="";
	
	/**
	 * Description����ַ
	 */
	private String qyxx_dz="";
	
	/**
	 * Description����������
	 */
	private String qyxx_yzbm="";
	
	/**
	 * Description���Ǽ�ע������
	 */
	private String qyxx_djzclx="";
	
	/**
	 * Description����ҵ
	 */
	private String qyxx_hy="";
	
	/**
	 * Description������˰���շ�ʽ
	 */
	private String qyxx_sdszsfs="";
	
	/**
	 * Description������˰�����
	 */
	private String qyxx_zgswjg="";
	

	/*----------������Ϣ------------*/
	
	/**
	 * Description�����֤������
	 */
	private String grxx_sfzjlx="";
	/**
	 * Description�����֤������
	 */
	private String grxx_sfzjhm="";
	
	
	
	/**
	 * @description: getter-- msg
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @description: setter-- msg
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * Description����ְ�ܹ͵�λ����
	 */
	private String grxx_rzsgdwmc="";
	/**
	 * Description����ְ�ܹ͵�λ��˰��ʶ���
	 */
	private String grxx_rzsgdwnsrsbh="";
	/**
	 * Description������
	 */
	private String grxx_xm="";
	/**
	 * Description����˰������
	 */
	private String grxx_nsrlx="";
	/**
	 * Description������һ��������
	 */
	private String grxx_sfyjjnqk="";
	/**
	 * Description����������
	 */
	private String grxx_email="";
	/**
	 * Description��������ϵ��ַ
	 */
	private String grxx_jnlxdz="";
	/**
	 * Description����������
	 */
	private String grxx_yzbm="";
	/**
	 * Description����ϵ�绰
	 */
	private String grxx_lxdh="";
	/**
	 * Description��ְҵ
	 */
	private String grxx_zy="";
	/**
	 * Description��ְ��
	 */
	private String grxx_zw="";
	/**
	 * Description��ѧ��
	 */
	private String grxx_xl="";
	/**
	 * Description���Ƿ�м���/����/����
	 */
	private String grxx_sfcjrlsgl="";
	/**
	 * Description���м��ȼ����
	 */
	private String grxx_cjdjqk="";
	/**
	 * Description��Ͷ��������
	 */
	private String grxx_tzzlx="";
	/**
	 * Description���о�������_��ַ����
	 */
	private String grxx_yjwsd_dzlx="";
	/**
	 * Description���о�������_��ַ
	 */
	private String grxx_yjwsd_dz="";
	/**
	 * Description���о�������_��������
	 */
	private String grxx_yjwsd_yzbm="";
	/**
	 * Description����ס����˰��_����
	 */
	private String grxx_wzsnsr_gj="";
	/**
	 * Description����ס����˰��_������
	 */
	private String grxx_wzsnsr_csd="";
	/**
	 * Description����ס����˰��_�Ա�
	 */
	//VARCHAR2(8)
	private String grxx_wzsnsr_xb="";
	/**
	 * Description����ס����˰��_��������
	 */
	private String grxx_wzsnsr_csrq="";
	/**
	 * Description����ס����˰��_�Ͷ���ҵ֤����
	 */
	private String grxx_wzsnsr_ldjyzhm="";
	/**
	 * Description����ס����˰��_�Ƿ�˰��Э����Լ���Է�����
	 */
	private String grxx_wzsnsr_sfssxddygdfjm="";
	/**
	 * Description����ס����˰��_����ְ��
	 */
	private String grxx_wzsnsr_jnzw="";
	/**
	 * Description����ס����˰��_����ְ��
	 */
	private String grxx_wzsnsr_jwzw="";
	/**
	 * Description����ס����˰��_����ʱ��
	 */
	private String grxx_wzsnsr_lhsj="";
	/**
	 * Description����ס����˰��_��ְ����
	 */
	private String grxx_wzsnsr_rzqx="";
	/**
	 * Description����ס����˰��_Ԥ���뾳ʱ��
	 */
	private String grxx_wzsnsr_yjljsj="";
	/**
	 * Description����ס����˰��_Ԥ���뾳�ص�
	 */
	private String grxx_wzsnsr_yjljdd="";
	/**
	 * Description����ס����˰��_������ְ�ܹ͵�λ_����
	 */
	private String grxx_wzsnsr_jnrzsgdw_mc="";
	/**
	 * Description����ס����˰��_������ְ�ܹ͵�λ_�۽������˱���
	 */
	private String grxx_wzsnsr_jnrzsgdw_kjywrbm="";
	/**
	 * Description����ס����˰��_������ְ�ܹ͵�λ_��ַ
	 */
	private String grxx_wzsnsr_jnrzsgdw_dz="";
	/**
	 * Description����ס����˰��_������ְ�ܹ͵�λ_��������
	 */
	private String grxx_wzsnsr_jnrzsgdw_yzbm="";
	/**
	 * Description����ס����˰��_������ƸǩԼ��λ_����
	 */
	private String grxx_wzsnsr_jnspqydw_mc="";
	/**
	 * Description����ס����˰��_������ƸǩԼ��λ_�۽������˱���
	 */
	private String grxx_wzsnsr_jnspqydw_kjywrbm="";
	/**
	 * Description����ס����˰��_������ƸǩԼ��λ_��ַ
	 */
	private String grxx_wzsnsr_jnspqydw_dz="";
	/**
	 * Description����ס����˰��_������ƸǩԼ��λ_��������
	 */
	private String grxx_wzsnsr_jnspqydw_yzbm="";
	/**
	 * Description����ס����˰��_������ǲ��λ_����
	 */
	private String grxx_wzsnsr_jwpqdw_mc="";
	/**
	 * Description����ס����˰��_������ǲ��λ_��ַ
	 */
	private String grxx_wzsnsr_jwpqdw_dz="";
	
	/**
	 * Description����ס����˰��_��˰��ʶ��� 
	 */
	private String grxx_wzsnsr_nsrsbh="";
	
	/**
	 * Description����ס����˰��_֧����
	 */
	private String grxx_wzsnsr_zfd="";
	/**
	 * Description����ס����˰��_����֧��������
	 */
	private String grxx_wzsnsr_jwzfggb="";
	/**
	 * @description: getter-- sfzjlxList
	 * @return the sfzjlxList
	 */
	public List getSfzjlxList() {
		return sfzjlxList;
	}
	/**
	 * @description: setter-- sfzjlxList
	 * @param sfzjlxList the sfzjlxList to set
	 */
	public void setSfzjlxList(List sfzjlxList) {
		this.sfzjlxList = sfzjlxList;
	}
	/**
	 * @description: getter-- gjList
	 * @return the gjList
	 */
	public List getGjList() {
		return gjList;
	}
	/**
	 * @description: setter-- gjList
	 * @param gjList the gjList to set
	 */
	public void setGjList(List gjList) {
		this.gjList = gjList;
	}
	/**
	 * @description: getter-- nd
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @description: setter-- nd
	 * @param nd the nd to set
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @description: getter-- qyxx_jsjdm
	 * @return the qyxx_jsjdm
	 */
	public String getQyxx_jsjdm() {
		return qyxx_jsjdm;
	}
	/**
	 * @description: setter-- qyxx_jsjdm
	 * @param qyxx_jsjdm the qyxx_jsjdm to set
	 */
	public void setQyxx_jsjdm(String qyxx_jsjdm) {
		this.qyxx_jsjdm = qyxx_jsjdm;
	}
	/**
	 * @description: getter-- qyxx_btzzxm
	 * @return the qyxx_btzzxm
	 */
	public String getQyxx_btzzxm() {
		return qyxx_btzzxm;
	}
	/**
	 * @description: setter-- qyxx_btzzxm
	 * @param qyxx_btzzxm the qyxx_btzzxm to set
	 */
	public void setQyxx_btzzxm(String qyxx_btzzxm) {
		this.qyxx_btzzxm = qyxx_btzzxm;
	}
	/**
	 * @description: getter-- qyxx_nsrsbh
	 * @return the qyxx_nsrsbh
	 */
	public String getQyxx_nsrsbh() {
		return qyxx_nsrsbh;
	}
	/**
	 * @description: setter-- qyxx_nsrsbh
	 * @param qyxx_nsrsbh the qyxx_nsrsbh to set
	 */
	public void setQyxx_nsrsbh(String qyxx_nsrsbh) {
		this.qyxx_nsrsbh = qyxx_nsrsbh;
	}
	/**
	 * @description: getter-- qyxx_kjywrbh
	 * @return the qyxx_kjywrbh
	 */
	public String getQyxx_kjywrbh() {
		return qyxx_kjywrbh;
	}
	/**
	 * @description: setter-- qyxx_kjywrbh
	 * @param qyxx_kjywrbh the qyxx_kjywrbh to set
	 */
	public void setQyxx_kjywrbh(String qyxx_kjywrbh) {
		this.qyxx_kjywrbh = qyxx_kjywrbh;
	}
	/**
	 * @description: getter-- qyxx_dz
	 * @return the qyxx_dz
	 */
	public String getQyxx_dz() {
		return qyxx_dz;
	}
	/**
	 * @description: setter-- qyxx_dz
	 * @param qyxx_dz the qyxx_dz to set
	 */
	public void setQyxx_dz(String qyxx_dz) {
		this.qyxx_dz = qyxx_dz;
	}
	/**
	 * @description: getter-- qyxx_yzbm
	 * @return the qyxx_yzbm
	 */
	public String getQyxx_yzbm() {
		return qyxx_yzbm;
	}
	/**
	 * @description: setter-- qyxx_yzbm
	 * @param qyxx_yzbm the qyxx_yzbm to set
	 */
	public void setQyxx_yzbm(String qyxx_yzbm) {
		this.qyxx_yzbm = qyxx_yzbm;
	}
	/**
	 * @description: getter-- qyxx_djzclx
	 * @return the qyxx_djzclx
	 */
	public String getQyxx_djzclx() {
		return qyxx_djzclx;
	}
	/**
	 * @description: setter-- qyxx_djzclx
	 * @param qyxx_djzclx the qyxx_djzclx to set
	 */
	public void setQyxx_djzclx(String qyxx_djzclx) {
		this.qyxx_djzclx = qyxx_djzclx;
	}
	/**
	 * @description: getter-- qyxx_hy
	 * @return the qyxx_hy
	 */
	public String getQyxx_hy() {
		return qyxx_hy;
	}
	/**
	 * @description: setter-- qyxx_hy
	 * @param qyxx_hy the qyxx_hy to set
	 */
	public void setQyxx_hy(String qyxx_hy) {
		this.qyxx_hy = qyxx_hy;
	}
	/**
	 * @description: getter-- qyxx_sdszsfs
	 * @return the qyxx_sdszsfs
	 */
	public String getQyxx_sdszsfs() {
		return qyxx_sdszsfs;
	}
	/**
	 * @description: setter-- qyxx_sdszsfs
	 * @param qyxx_sdszsfs the qyxx_sdszsfs to set
	 */
	public void setQyxx_sdszsfs(String qyxx_sdszsfs) {
		this.qyxx_sdszsfs = qyxx_sdszsfs;
	}
	/**
	 * @description: getter-- qyxx_zgswjg
	 * @return the qyxx_zgswjg
	 */
	public String getQyxx_zgswjg() {
		return qyxx_zgswjg;
	}
	/**
	 * @description: setter-- qyxx_zgswjg
	 * @param qyxx_zgswjg the qyxx_zgswjg to set
	 */
	public void setQyxx_zgswjg(String qyxx_zgswjg) {
		this.qyxx_zgswjg = qyxx_zgswjg;
	}
	/**
	 * @description: getter-- grxx_sfzjlx
	 * @return the grxx_sfzjlx
	 */
	public String getGrxx_sfzjlx() {
		return grxx_sfzjlx;
	}
	/**
	 * @description: setter-- grxx_sfzjlx
	 * @param grxx_sfzjlx the grxx_sfzjlx to set
	 */
	public void setGrxx_sfzjlx(String grxx_sfzjlx) {
		this.grxx_sfzjlx = grxx_sfzjlx;
	}
	/**
	 * @description: getter-- grxx_sfzjhm
	 * @return the grxx_sfzjhm
	 */
	public String getGrxx_sfzjhm() {
		return grxx_sfzjhm;
	}
	/**
	 * @description: setter-- grxx_sfzjhm
	 * @param grxx_sfzjhm the grxx_sfzjhm to set
	 */
	public void setGrxx_sfzjhm(String grxx_sfzjhm) {
		this.grxx_sfzjhm = grxx_sfzjhm;
	}
	/**
	 * @description: getter-- grxx_rzsgdwmc
	 * @return the grxx_rzsgdwmc
	 */
	public String getGrxx_rzsgdwmc() {
		return grxx_rzsgdwmc;
	}
	/**
	 * @description: setter-- grxx_rzsgdwmc
	 * @param grxx_rzsgdwmc the grxx_rzsgdwmc to set
	 */
	public void setGrxx_rzsgdwmc(String grxx_rzsgdwmc) {
		this.grxx_rzsgdwmc = grxx_rzsgdwmc;
	}
	/**
	 * @description: getter-- grxx_rzsgdwnsrsbh
	 * @return the grxx_rzsgdwnsrsbh
	 */
	public String getGrxx_rzsgdwnsrsbh() {
		return grxx_rzsgdwnsrsbh;
	}
	/**
	 * @description: setter-- grxx_rzsgdwnsrsbh
	 * @param grxx_rzsgdwnsrsbh the grxx_rzsgdwnsrsbh to set
	 */
	public void setGrxx_rzsgdwnsrsbh(String grxx_rzsgdwnsrsbh) {
		this.grxx_rzsgdwnsrsbh = grxx_rzsgdwnsrsbh;
	}
	
	/**
	 * @description: getter-- djzclxList
	 * @return the djzclxList
	 */
	public List getDjzclxList() {
		return djzclxList;
	}
	/**
	 * @description: setter-- djzclxList
	 * @param djzclxList the djzclxList to set
	 */
	public void setDjzclxList(List djzclxList) {
		this.djzclxList = djzclxList;
	}
	/**
	 * @description: getter-- grxx_xm
	 * @return the grxx_xm
	 */
	public String getGrxx_xm() {
		return grxx_xm;
	}
	/**
	 * @description: setter-- grxx_xm
	 * @param grxx_xm the grxx_xm to set
	 */
	public void setGrxx_xm(String grxx_xm) {
		this.grxx_xm = grxx_xm;
	}
	/**
	 * @description: getter-- grxx_nsrlx
	 * @return the grxx_nsrlx
	 */
	public String getGrxx_nsrlx() {
		return grxx_nsrlx;
	}
	/**
	 * @description: setter-- grxx_nsrlx
	 * @param grxx_nsrlx the grxx_nsrlx to set
	 */
	public void setGrxx_nsrlx(String grxx_nsrlx) {
		this.grxx_nsrlx = grxx_nsrlx;
	}
	/**
	 * @description: getter-- grxx_sfyjjnqk
	 * @return the grxx_sfyjjnqk
	 */
	public String getGrxx_sfyjjnqk() {
		return grxx_sfyjjnqk;
	}
	/**
	 * @description: setter-- grxx_sfyjjnqk
	 * @param grxx_sfyjjnqk the grxx_sfyjjnqk to set
	 */
	public void setGrxx_sfyjjnqk(String grxx_sfyjjnqk) {
		this.grxx_sfyjjnqk = grxx_sfyjjnqk;
	}
	/**
	 * @description: getter-- grxx_email
	 * @return the grxx_email
	 */
	public String getGrxx_email() {
		return grxx_email;
	}
	/**
	 * @description: setter-- grxx_email
	 * @param grxx_email the grxx_email to set
	 */
	public void setGrxx_email(String grxx_email) {
		this.grxx_email = grxx_email;
	}
	/**
	 * @description: getter-- grxx_jnlxdz
	 * @return the grxx_jnlxdz
	 */
	public String getGrxx_jnlxdz() {
		return grxx_jnlxdz;
	}
	/**
	 * @description: setter-- grxx_jnlxdz
	 * @param grxx_jnlxdz the grxx_jnlxdz to set
	 */
	public void setGrxx_jnlxdz(String grxx_jnlxdz) {
		this.grxx_jnlxdz = grxx_jnlxdz;
	}
	/**
	 * @description: getter-- grxx_yzbm
	 * @return the grxx_yzbm
	 */
	public String getGrxx_yzbm() {
		return grxx_yzbm;
	}
	/**
	 * @description: setter-- grxx_yzbm
	 * @param grxx_yzbm the grxx_yzbm to set
	 */
	public void setGrxx_yzbm(String grxx_yzbm) {
		this.grxx_yzbm = grxx_yzbm;
	}
	/**
	 * @description: getter-- grxx_lxdh
	 * @return the grxx_lxdh
	 */
	public String getGrxx_lxdh() {
		return grxx_lxdh;
	}
	/**
	 * @description: setter-- grxx_lxdh
	 * @param grxx_lxdh the grxx_lxdh to set
	 */
	public void setGrxx_lxdh(String grxx_lxdh) {
		this.grxx_lxdh = grxx_lxdh;
	}
	/**
	 * @description: getter-- grxx_zy
	 * @return the grxx_zy
	 */
	public String getGrxx_zy() {
		return grxx_zy;
	}
	/**
	 * @description: setter-- grxx_zy
	 * @param grxx_zy the grxx_zy to set
	 */
	public void setGrxx_zy(String grxx_zy) {
		this.grxx_zy = grxx_zy;
	}
	/**
	 * @description: getter-- grxx_zw
	 * @return the grxx_zw
	 */
	public String getGrxx_zw() {
		return grxx_zw;
	}
	/**
	 * @description: setter-- grxx_zw
	 * @param grxx_zw the grxx_zw to set
	 */
	public void setGrxx_zw(String grxx_zw) {
		this.grxx_zw = grxx_zw;
	}
	/**
	 * @description: getter-- grxx_xl
	 * @return the grxx_xl
	 */
	public String getGrxx_xl() {
		return grxx_xl;
	}
	/**
	 * @description: setter-- grxx_xl
	 * @param grxx_xl the grxx_xl to set
	 */
	public void setGrxx_xl(String grxx_xl) {
		this.grxx_xl = grxx_xl;
	}
	/**
	 * @description: getter-- grxx_sfcjrlsgl
	 * @return the grxx_sfcjrlsgl
	 */
	public String getGrxx_sfcjrlsgl() {
		return grxx_sfcjrlsgl;
	}
	/**
	 * @description: setter-- grxx_sfcjrlsgl
	 * @param grxx_sfcjrlsgl the grxx_sfcjrlsgl to set
	 */
	public void setGrxx_sfcjrlsgl(String grxx_sfcjrlsgl) {
		this.grxx_sfcjrlsgl = grxx_sfcjrlsgl;
	}
	/**
	 * @description: getter-- grxx_cjdjqk
	 * @return the grxx_cjdjqk
	 */
	public String getGrxx_cjdjqk() {
		return grxx_cjdjqk;
	}
	/**
	 * @description: setter-- grxx_cjdjqk
	 * @param grxx_cjdjqk the grxx_cjdjqk to set
	 */
	public void setGrxx_cjdjqk(String grxx_cjdjqk) {
		this.grxx_cjdjqk = grxx_cjdjqk;
	}
	/**
	 * @description: getter-- grxx_tzzlx
	 * @return the grxx_tzzlx
	 */
	public String getGrxx_tzzlx() {
		return grxx_tzzlx;
	}
	/**
	 * @description: setter-- grxx_tzzlx
	 * @param grxx_tzzlx the grxx_tzzlx to set
	 */
	public void setGrxx_tzzlx(String grxx_tzzlx) {
		this.grxx_tzzlx = grxx_tzzlx;
	}
	/**
	 * @description: getter-- grxx_yjwsd_dzlx
	 * @return the grxx_yjwsd_dzlx
	 */
	public String getGrxx_yjwsd_dzlx() {
		return grxx_yjwsd_dzlx;
	}
	/**
	 * @description: setter-- grxx_yjwsd_dzlx
	 * @param grxx_yjwsd_dzlx the grxx_yjwsd_dzlx to set
	 */
	public void setGrxx_yjwsd_dzlx(String grxx_yjwsd_dzlx) {
		this.grxx_yjwsd_dzlx = grxx_yjwsd_dzlx;
	}
	/**
	 * @description: getter-- grxx_yjwsd_dz
	 * @return the grxx_yjwsd_dz
	 */
	public String getGrxx_yjwsd_dz() {
		return grxx_yjwsd_dz;
	}
	/**
	 * @description: setter-- grxx_yjwsd_dz
	 * @param grxx_yjwsd_dz the grxx_yjwsd_dz to set
	 */
	public void setGrxx_yjwsd_dz(String grxx_yjwsd_dz) {
		this.grxx_yjwsd_dz = grxx_yjwsd_dz;
	}
	/**
	 * @description: getter-- grxx_yjwsd_yzbm
	 * @return the grxx_yjwsd_yzbm
	 */
	public String getGrxx_yjwsd_yzbm() {
		return grxx_yjwsd_yzbm;
	}
	/**
	 * @description: setter-- grxx_yjwsd_yzbm
	 * @param grxx_yjwsd_yzbm the grxx_yjwsd_yzbm to set
	 */
	public void setGrxx_yjwsd_yzbm(String grxx_yjwsd_yzbm) {
		this.grxx_yjwsd_yzbm = grxx_yjwsd_yzbm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_gj
	 * @return the grxx_wzsnsr_gj
	 */
	public String getGrxx_wzsnsr_gj() {
		return grxx_wzsnsr_gj;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_gj
	 * @param grxx_wzsnsr_gj the grxx_wzsnsr_gj to set
	 */
	public void setGrxx_wzsnsr_gj(String grxx_wzsnsr_gj) {
		this.grxx_wzsnsr_gj = grxx_wzsnsr_gj;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_csd
	 * @return the grxx_wzsnsr_csd
	 */
	public String getGrxx_wzsnsr_csd() {
		return grxx_wzsnsr_csd;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_csd
	 * @param grxx_wzsnsr_csd the grxx_wzsnsr_csd to set
	 */
	public void setGrxx_wzsnsr_csd(String grxx_wzsnsr_csd) {
		this.grxx_wzsnsr_csd = grxx_wzsnsr_csd;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_xb
	 * @return the grxx_wzsnsr_xb
	 */
	public String getGrxx_wzsnsr_xb() {
		return grxx_wzsnsr_xb;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_xb
	 * @param grxx_wzsnsr_xb the grxx_wzsnsr_xb to set
	 */
	public void setGrxx_wzsnsr_xb(String grxx_wzsnsr_xb) {
		this.grxx_wzsnsr_xb = grxx_wzsnsr_xb;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_csrq
	 * @return the grxx_wzsnsr_csrq
	 */
	public String getGrxx_wzsnsr_csrq() {
		return grxx_wzsnsr_csrq;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_csrq
	 * @param grxx_wzsnsr_csrq the grxx_wzsnsr_csrq to set
	 */
	public void setGrxx_wzsnsr_csrq(String grxx_wzsnsr_csrq) {
		this.grxx_wzsnsr_csrq = grxx_wzsnsr_csrq;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_ldjyzhm
	 * @return the grxx_wzsnsr_ldjyzhm
	 */
	public String getGrxx_wzsnsr_ldjyzhm() {
		return grxx_wzsnsr_ldjyzhm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_ldjyzhm
	 * @param grxx_wzsnsr_ldjyzhm the grxx_wzsnsr_ldjyzhm to set
	 */
	public void setGrxx_wzsnsr_ldjyzhm(String grxx_wzsnsr_ldjyzhm) {
		this.grxx_wzsnsr_ldjyzhm = grxx_wzsnsr_ldjyzhm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_sfssxddygdfjm
	 * @return the grxx_wzsnsr_sfssxddygdfjm
	 */
	public String getGrxx_wzsnsr_sfssxddygdfjm() {
		return grxx_wzsnsr_sfssxddygdfjm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_sfssxddygdfjm
	 * @param grxx_wzsnsr_sfssxddygdfjm the grxx_wzsnsr_sfssxddygdfjm to set
	 */
	public void setGrxx_wzsnsr_sfssxddygdfjm(String grxx_wzsnsr_sfssxddygdfjm) {
		this.grxx_wzsnsr_sfssxddygdfjm = grxx_wzsnsr_sfssxddygdfjm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnzw
	 * @return the grxx_wzsnsr_jnzw
	 */
	public String getGrxx_wzsnsr_jnzw() {
		return grxx_wzsnsr_jnzw;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnzw
	 * @param grxx_wzsnsr_jnzw the grxx_wzsnsr_jnzw to set
	 */
	public void setGrxx_wzsnsr_jnzw(String grxx_wzsnsr_jnzw) {
		this.grxx_wzsnsr_jnzw = grxx_wzsnsr_jnzw;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jwzw
	 * @return the grxx_wzsnsr_jwzw
	 */
	public String getGrxx_wzsnsr_jwzw() {
		return grxx_wzsnsr_jwzw;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jwzw
	 * @param grxx_wzsnsr_jwzw the grxx_wzsnsr_jwzw to set
	 */
	public void setGrxx_wzsnsr_jwzw(String grxx_wzsnsr_jwzw) {
		this.grxx_wzsnsr_jwzw = grxx_wzsnsr_jwzw;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_lhsj
	 * @return the grxx_wzsnsr_lhsj
	 */
	public String getGrxx_wzsnsr_lhsj() {
		return grxx_wzsnsr_lhsj;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_lhsj
	 * @param grxx_wzsnsr_lhsj the grxx_wzsnsr_lhsj to set
	 */
	public void setGrxx_wzsnsr_lhsj(String grxx_wzsnsr_lhsj) {
		this.grxx_wzsnsr_lhsj = grxx_wzsnsr_lhsj;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_rzqx
	 * @return the grxx_wzsnsr_rzqx
	 */
	public String getGrxx_wzsnsr_rzqx() {
		return grxx_wzsnsr_rzqx;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_rzqx
	 * @param grxx_wzsnsr_rzqx the grxx_wzsnsr_rzqx to set
	 */
	public void setGrxx_wzsnsr_rzqx(String grxx_wzsnsr_rzqx) {
		this.grxx_wzsnsr_rzqx = grxx_wzsnsr_rzqx;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_yjljsj
	 * @return the grxx_wzsnsr_yjljsj
	 */
	public String getGrxx_wzsnsr_yjljsj() {
		return grxx_wzsnsr_yjljsj;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_yjljsj
	 * @param grxx_wzsnsr_yjljsj the grxx_wzsnsr_yjljsj to set
	 */
	public void setGrxx_wzsnsr_yjljsj(String grxx_wzsnsr_yjljsj) {
		this.grxx_wzsnsr_yjljsj = grxx_wzsnsr_yjljsj;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_yjljdd
	 * @return the grxx_wzsnsr_yjljdd
	 */
	public String getGrxx_wzsnsr_yjljdd() {
		return grxx_wzsnsr_yjljdd;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_yjljdd
	 * @param grxx_wzsnsr_yjljdd the grxx_wzsnsr_yjljdd to set
	 */
	public void setGrxx_wzsnsr_yjljdd(String grxx_wzsnsr_yjljdd) {
		this.grxx_wzsnsr_yjljdd = grxx_wzsnsr_yjljdd;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnrzsgdw_mc
	 * @return the grxx_wzsnsr_jnrzsgdw_mc
	 */
	public String getGrxx_wzsnsr_jnrzsgdw_mc() {
		return grxx_wzsnsr_jnrzsgdw_mc;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnrzsgdw_mc
	 * @param grxx_wzsnsr_jnrzsgdw_mc the grxx_wzsnsr_jnrzsgdw_mc to set
	 */
	public void setGrxx_wzsnsr_jnrzsgdw_mc(String grxx_wzsnsr_jnrzsgdw_mc) {
		this.grxx_wzsnsr_jnrzsgdw_mc = grxx_wzsnsr_jnrzsgdw_mc;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnrzsgdw_kjywrbm
	 * @return the grxx_wzsnsr_jnrzsgdw_kjywrbm
	 */
	public String getGrxx_wzsnsr_jnrzsgdw_kjywrbm() {
		return grxx_wzsnsr_jnrzsgdw_kjywrbm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnrzsgdw_kjywrbm
	 * @param grxx_wzsnsr_jnrzsgdw_kjywrbm the grxx_wzsnsr_jnrzsgdw_kjywrbm to set
	 */
	public void setGrxx_wzsnsr_jnrzsgdw_kjywrbm(String grxx_wzsnsr_jnrzsgdw_kjywrbm) {
		this.grxx_wzsnsr_jnrzsgdw_kjywrbm = grxx_wzsnsr_jnrzsgdw_kjywrbm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnrzsgdw_dz
	 * @return the grxx_wzsnsr_jnrzsgdw_dz
	 */
	public String getGrxx_wzsnsr_jnrzsgdw_dz() {
		return grxx_wzsnsr_jnrzsgdw_dz;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnrzsgdw_dz
	 * @param grxx_wzsnsr_jnrzsgdw_dz the grxx_wzsnsr_jnrzsgdw_dz to set
	 */
	public void setGrxx_wzsnsr_jnrzsgdw_dz(String grxx_wzsnsr_jnrzsgdw_dz) {
		this.grxx_wzsnsr_jnrzsgdw_dz = grxx_wzsnsr_jnrzsgdw_dz;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnrzsgdw_yzbm
	 * @return the grxx_wzsnsr_jnrzsgdw_yzbm
	 */
	public String getGrxx_wzsnsr_jnrzsgdw_yzbm() {
		return grxx_wzsnsr_jnrzsgdw_yzbm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnrzsgdw_yzbm
	 * @param grxx_wzsnsr_jnrzsgdw_yzbm the grxx_wzsnsr_jnrzsgdw_yzbm to set
	 */
	public void setGrxx_wzsnsr_jnrzsgdw_yzbm(String grxx_wzsnsr_jnrzsgdw_yzbm) {
		this.grxx_wzsnsr_jnrzsgdw_yzbm = grxx_wzsnsr_jnrzsgdw_yzbm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnspqydw_mc
	 * @return the grxx_wzsnsr_jnspqydw_mc
	 */
	public String getGrxx_wzsnsr_jnspqydw_mc() {
		return grxx_wzsnsr_jnspqydw_mc;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnspqydw_mc
	 * @param grxx_wzsnsr_jnspqydw_mc the grxx_wzsnsr_jnspqydw_mc to set
	 */
	public void setGrxx_wzsnsr_jnspqydw_mc(String grxx_wzsnsr_jnspqydw_mc) {
		this.grxx_wzsnsr_jnspqydw_mc = grxx_wzsnsr_jnspqydw_mc;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnspqydw_kjywrbm
	 * @return the grxx_wzsnsr_jnspqydw_kjywrbm
	 */
	public String getGrxx_wzsnsr_jnspqydw_kjywrbm() {
		return grxx_wzsnsr_jnspqydw_kjywrbm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnspqydw_kjywrbm
	 * @param grxx_wzsnsr_jnspqydw_kjywrbm the grxx_wzsnsr_jnspqydw_kjywrbm to set
	 */
	public void setGrxx_wzsnsr_jnspqydw_kjywrbm(String grxx_wzsnsr_jnspqydw_kjywrbm) {
		this.grxx_wzsnsr_jnspqydw_kjywrbm = grxx_wzsnsr_jnspqydw_kjywrbm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnspqydw_dz
	 * @return the grxx_wzsnsr_jnspqydw_dz
	 */
	public String getGrxx_wzsnsr_jnspqydw_dz() {
		return grxx_wzsnsr_jnspqydw_dz;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnspqydw_dz
	 * @param grxx_wzsnsr_jnspqydw_dz the grxx_wzsnsr_jnspqydw_dz to set
	 */
	public void setGrxx_wzsnsr_jnspqydw_dz(String grxx_wzsnsr_jnspqydw_dz) {
		this.grxx_wzsnsr_jnspqydw_dz = grxx_wzsnsr_jnspqydw_dz;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jnspqydw_yzbm
	 * @return the grxx_wzsnsr_jnspqydw_yzbm
	 */
	public String getGrxx_wzsnsr_jnspqydw_yzbm() {
		return grxx_wzsnsr_jnspqydw_yzbm;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jnspqydw_yzbm
	 * @param grxx_wzsnsr_jnspqydw_yzbm the grxx_wzsnsr_jnspqydw_yzbm to set
	 */
	public void setGrxx_wzsnsr_jnspqydw_yzbm(String grxx_wzsnsr_jnspqydw_yzbm) {
		this.grxx_wzsnsr_jnspqydw_yzbm = grxx_wzsnsr_jnspqydw_yzbm;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jwpqdw_mc
	 * @return the grxx_wzsnsr_jwpqdw_mc
	 */
	public String getGrxx_wzsnsr_jwpqdw_mc() {
		return grxx_wzsnsr_jwpqdw_mc;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jwpqdw_mc
	 * @param grxx_wzsnsr_jwpqdw_mc the grxx_wzsnsr_jwpqdw_mc to set
	 */
	public void setGrxx_wzsnsr_jwpqdw_mc(String grxx_wzsnsr_jwpqdw_mc) {
		this.grxx_wzsnsr_jwpqdw_mc = grxx_wzsnsr_jwpqdw_mc;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jwpqdw_dz
	 * @return the grxx_wzsnsr_jwpqdw_dz
	 */
	public String getGrxx_wzsnsr_jwpqdw_dz() {
		return grxx_wzsnsr_jwpqdw_dz;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jwpqdw_dz
	 * @param grxx_wzsnsr_jwpqdw_dz the grxx_wzsnsr_jwpqdw_dz to set
	 */
	public void setGrxx_wzsnsr_jwpqdw_dz(String grxx_wzsnsr_jwpqdw_dz) {
		this.grxx_wzsnsr_jwpqdw_dz = grxx_wzsnsr_jwpqdw_dz;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_nsrsbh
	 * @return the grxx_wzsnsr_nsrsbh
	 */
	public String getGrxx_wzsnsr_nsrsbh() {
		return grxx_wzsnsr_nsrsbh;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_nsrsbh
	 * @param grxx_wzsnsr_nsrsbh the grxx_wzsnsr_nsrsbh to set
	 */
	public void setGrxx_wzsnsr_nsrsbh(String grxx_wzsnsr_nsrsbh) {
		this.grxx_wzsnsr_nsrsbh = grxx_wzsnsr_nsrsbh;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_zfd
	 * @return the grxx_wzsnsr_zfd
	 */
	public String getGrxx_wzsnsr_zfd() {
		return grxx_wzsnsr_zfd;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_zfd
	 * @param grxx_wzsnsr_zfd the grxx_wzsnsr_zfd to set
	 */
	public void setGrxx_wzsnsr_zfd(String grxx_wzsnsr_zfd) {
		this.grxx_wzsnsr_zfd = grxx_wzsnsr_zfd;
	}
	/**
	 * @description: getter-- grxx_wzsnsr_jwzfggb
	 * @return the grxx_wzsnsr_jwzfggb
	 */
	public String getGrxx_wzsnsr_jwzfggb() {
		return grxx_wzsnsr_jwzfggb;
	}
	/**
	 * @description: setter-- grxx_wzsnsr_jwzfggb
	 * @param grxx_wzsnsr_jwzfggb the grxx_wzsnsr_jwzfggb to set
	 */
	public void setGrxx_wzsnsr_jwzfggb(String grxx_wzsnsr_jwzfggb) {
		this.grxx_wzsnsr_jwzfggb = grxx_wzsnsr_jwzfggb;
	}
	/**
	 * @description: getter-- swjgzzjgList
	 * @return the swjgzzjgList
	 */
	public List getSwjgzzjgList() {
		return swjgzzjgList;
	}
	/**
	 * @description: setter-- swjgzzjgList
	 * @param swjgzzjgList the swjgzzjgList to set
	 */
	public void setSwjgzzjgList(List swjgzzjgList) {
		this.swjgzzjgList = swjgzzjgList;
	}
	/**
	 * @description: getter-- hyList
	 * @return the hyList
	 */
	public List getHyList() {
		return hyList;
	}
	/**
	 * @description: setter-- hyList
	 * @param hyList the hyList to set
	 */
	public void setHyList(List hyList) {
		this.hyList = hyList;
	}
	
	
	
}
