package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;

/**
 * 
 * <p>
 * Title:��������Ϣ�ɼ�form
 * </p>
 * <p>
 * Description:form
 * </p>
 * 
 * @author �Ʋ���
 * @version 1.1
 */
public class ClfxxcjForm extends QueryBaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean hasMAuthorise =true;//�Ƿ����޸Ĵ������ɼ���Ϣ��Ȩ��  false --��  true--��
	
	private String xxly="01";//�ɼ�����  01����ά�룬02���ֹ��ɼ�;
	private String cjfsdm="01";//�ɼ���ʽ  01����ά��ɨ�裬02���ֹ��ɼ�;
	
	//
	private String saveIsSuccess = "0";//��������Ƿ�ɹ�0--δ�ɹ�  1--�ɹ�
	private String keyStr;//������ʽ SBBH+"::"+HTBH
	private String piccode;// ���ܶ�ά��
	private String UNEpiccode;// �Ǽ��ܶ�ά��
	// ������Ϣ
	private String hasHdxx="N";//����˰����Ա�˶���Ϣ  Y--����  N--������
	private String bbbs;// �汾��ʾ
	private String htbh;// ��ͬ���
	private String htwsqyrq;// ��ͬ����ǩԼ����
	private String fwzlqx;// ������������
	private String fwzldz;// ���������ַ
	private String fwqszylx;// ����Ȩ��ת������_����
	private String fwqszylxmc;// ����Ȩ��ת������_����
	private String sfwscsssggf;// �Ƿ�Ϊ�״������ѹ�����_����
	private String sfwscsssggfmc;// �Ƿ�Ϊ�״������ѹ�����_����
	private String fwcqzh;// ���ݲ�Ȩ֤��
	private String fwsyqztfrq;// ��������Ȩ֤�����
	private String fwjzmj;// ���ݽ������
	private String jzjgdm;// �����ṹ����
	private String jzjgmc;// �����ṹ����
	private String ghyt;// �滮��;
	private String htzj;// ��ͬ�ܼ�
	private String bzdm;// ���ִ���
	private String bzmc;// ��������
	private String hl;// ����
	private String wbje;// ��ҽ��
	private String szlc;// ����¥��
	private String fdczjjgmc;// ���ز��н��������

	// ������Ϣ
	private String all_sellerInfo;
	private String sell_mc;
	private String sell_lb;
	private String sell_lb_mc;
	private String sell_zjlx;
	private String sell_zjlx_mc;
	private String sell_zjhm;
	private String sell_qlrfe;
	private String sell_lxdh;

	// ����Ϣ
	private String all_buyerInfo;
	private String buy_mc;
	private String buy_lb;
	private String buy_lb_mc;
	private String buy_zjlx;
	private String buy_zjlx_mc;
	private String buy_zjhm;
	private String buy_qlrfe;
	private String buy_lxdh;
	
	//��ӡ��Ϣ
	private String slrq;//��������
	
	//��õ�ǰ֤����������
	private String tmp_zllxmc;
	
	//�¼��ֶ�:�������ʴ���
	private String fwxzdm;
	
	
	/**
	 * �������Ϣ
	 */
	//֤�������
	private List zjList= new ArrayList();
	//���ִ����
	private List codeList_bz = new ArrayList();
	
	//�������ʴ����
	private List codeList_fwxz = new ArrayList();
	
	
	
	
	

	public Object getData() {
		ClfxxcjBo bo = new ClfxxcjBo();
		bo.keyStr = this.keyStr;
		bo.sbbh = this.sbbh;
		bo.bbbs = this.bbbs;// �汾��ʾ
		bo.htbh = this.htbh;// ��ͬ���
		bo.htwsqyrq = this.htwsqyrq;// ��ͬ����ǩԼ����
		bo.fwzlqx = this.fwzlqx;// ������������
		bo.fwzldz = this.fwzldz;// ���������ַ
		bo.fwqszylx = this.fwqszylx;// ����Ȩ��ת������_����
		bo.fwqszylxmc = this.fwqszylxmc;// ����Ȩ��ת������_����
		bo.sfwscsssggf = this.sfwscsssggf;// �Ƿ�Ϊ�״������ѹ�����_����
		bo.sfwscsssggfmc = this.sfwscsssggfmc;// �Ƿ�Ϊ�״������ѹ�����_����
		bo.fwcqzh = this.fwcqzh;// ���ݲ�Ȩ֤��
		bo.fwsyqztfrq = this.fwsyqztfrq;// ��������Ȩ֤�����
		bo.fwjzmj = this.fwjzmj;// ���ݽ������
		bo.jzjgdm = this.jzjgdm;// �����ṹ����
		bo.jzjgmc = this.jzjgmc;// �����ṹ����
		bo.ghyt = this.ghyt;// �滮��;
		bo.htzj = this.htzj;// ��ͬ�ܼ�
		bo.bzdm = this.bzdm;// ���ִ���
		bo.bzmc = this.bzmc;// ��������
		bo.hl = this.hl;// ����
		bo.wbje = this.wbje;// ��ҽ��
		bo.szlc = this.szlc;// ����¥��
		bo.fdczjjgmc = this.fdczjjgmc;// ���ز��н��������
		bo.all_sellerInfo = this.all_sellerInfo;
		bo.all_buyerInfo = this.all_buyerInfo;
		bo.UNEpiccode = this.UNEpiccode;

		bo.fwxzdm = this.fwxzdm;
		
		bo.xxly = this.xxly;
		return bo;
	}
	
	
	public void clear(){
		this.hasMAuthorise =true;//�Ƿ����޸Ĵ������ɼ���Ϣ��Ȩ��  false --��  true--��
		this.xxly="01";//�ɼ�����  01����ά�룬02���ֹ��ɼ�="";
		this.saveIsSuccess = "0";//��������Ƿ�ɹ�0--δ�ɹ�  1--�ɹ�
		this.keyStr="";//������ʽ SBBH+"::"+HTBH
		this.piccode="";// ���ܶ�ά��
		this.UNEpiccode="";// �Ǽ��ܶ�ά��
		this.sbbh ="";
		// ������Ϣ
		this.hasHdxx="N";
		this.bbbs="";// �汾��ʾ
		this.htbh="";// ��ͬ���
		this.htwsqyrq="";// ��ͬ����ǩԼ����
		this.fwzlqx="";// ������������
		this.fwzldz="";// ���������ַ
		this.fwqszylx="";// ����Ȩ��ת������_����
		this.fwqszylxmc="";// ����Ȩ��ת������_����
		this.sfwscsssggf="";// �Ƿ�Ϊ�״������ѹ�����_����
		this.sfwscsssggfmc="";// �Ƿ�Ϊ�״������ѹ�����_����
		this.fwcqzh="";// ���ݲ�Ȩ֤��
		this.fwsyqztfrq="";// ��������Ȩ֤�����
		this.fwjzmj="";// ���ݽ������
		this.jzjgdm="";// �����ṹ����
		this.jzjgmc="";// �����ṹ����
		this.ghyt="";// �滮��;
		this.htzj="";// ��ͬ�ܼ�
		this.bzdm="";// ���ִ���
		this.bzmc="";// ��������
		this.hl="";// ����
		this.wbje="";// ��ҽ��
		this.szlc="";// ����¥��
		this.fdczjjgmc="";// ���ز��н��������
		this.all_sellerInfo="";// ������Ϣ
		this.all_buyerInfo="";// ����Ϣ
		this.slrq="";//��������
		this.tmp_zllxmc="";//��õ�ǰ֤����������
		this.fwxzdm="";//�¼��ֶ�:�������ʴ���
		this.zjList= new ArrayList();//֤�������
		this.codeList_bz = new ArrayList();//���ִ����	
		this.codeList_fwxz = new ArrayList();
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getPiccode() {
		return piccode;
	}

	public void setPiccode(String piccode) {
		this.piccode = piccode;
	}

	public String getHtwsqyrq() {
		return htwsqyrq;
	}

	public void setHtwsqyrq(String htwsqyrq) {
		this.htwsqyrq = htwsqyrq;
	}

	public String getFwzlqx() {
		return fwzlqx;
	}

	public void setFwzlqx(String fwzlqx) {
		this.fwzlqx = fwzlqx;
	}

	public String getFwzldz() {
		return fwzldz;
	}

	public void setFwzldz(String fwzldz) {
		this.fwzldz = fwzldz;
	}

	public String getUNEpiccode() {
		return UNEpiccode;
	}

	public void setUNEpiccode(String uNEpiccode) {
		UNEpiccode = uNEpiccode;
	}

	public String getBbbs() {
		return bbbs;
	}

	public void setBbbs(String bbbs) {
		this.bbbs = bbbs;
	}

	public String getFwqszylx() {
		return fwqszylx;
	}

	public void setFwqszylx(String fwqszylx) {
		this.fwqszylx = fwqszylx;
	}

	public String getFwqszylxmc() {
		return fwqszylxmc;
	}

	public void setFwqszylxmc(String fwqszylxmc) {
		this.fwqszylxmc = fwqszylxmc;
	}

	public String getSfwscsssggf() {
		return sfwscsssggf;
	}

	public void setSfwscsssggf(String sfwscsssggf) {
		this.sfwscsssggf = sfwscsssggf;
	}

	public String getSfwscsssggfmc() {
		return sfwscsssggfmc;
	}

	public void setSfwscsssggfmc(String sfwscsssggfmc) {
		this.sfwscsssggfmc = sfwscsssggfmc;
	}

	public String getFwcqzh() {
		return fwcqzh;
	}

	public void setFwcqzh(String fwcqzh) {
		this.fwcqzh = fwcqzh;
	}

	public String getFwsyqztfrq() {
		return fwsyqztfrq;
	}

	public void setFwsyqztfrq(String fwsyqztfrq) {
		this.fwsyqztfrq = fwsyqztfrq;
	}

	public String getFwjzmj() {
		return fwjzmj;
	}

	public void setFwjzmj(String fwjzmj) {
		this.fwjzmj = fwjzmj;
	}

	public String getJzjgdm() {
		return jzjgdm;
	}

	public void setJzjgdm(String jzjgdm) {
		this.jzjgdm = jzjgdm;
	}

	public String getJzjgmc() {
		return jzjgmc;
	}

	public void setJzjgmc(String jzjgmc) {
		this.jzjgmc = jzjgmc;
	}

	public String getGhyt() {
		return ghyt;
	}

	public void setGhyt(String ghyt) {
		this.ghyt = ghyt;
	}

	public String getHtzj() {
		return htzj;
	}

	public void setHtzj(String htzj) {
		this.htzj = htzj;
	}

	public String getBzdm() {
		return bzdm;
	}

	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getHl() {
		return hl;
	}

	public void setHl(String hl) {
		this.hl = hl;
	}

	public String getWbje() {
		return wbje;
	}

	public void setWbje(String wbje) {
		this.wbje = wbje;
	}

	public String getSzlc() {
		return szlc;
	}

	public void setSzlc(String szlc) {
		this.szlc = szlc;
	}

	public String getFdczjjgmc() {
		return fdczjjgmc;
	}

	public void setFdczjjgmc(String fdczjjgmc) {
		this.fdczjjgmc = fdczjjgmc;
	}

	public String getSell_mc() {
		return sell_mc;
	}

	public void setSell_mc(String sell_mc) {
		this.sell_mc = sell_mc;
	}

	public String getSell_lb() {
		return sell_lb;
	}

	public void setSell_lb(String sell_lb) {
		this.sell_lb = sell_lb;
	}

	public String getSell_zjlx() {
		return sell_zjlx;
	}

	public void setSell_zjlx(String sell_zjlx) {
		this.sell_zjlx = sell_zjlx;
	}

	public String getSell_zjhm() {
		return sell_zjhm;
	}

	public void setSell_zjhm(String sell_zjhm) {
		this.sell_zjhm = sell_zjhm;
	}

	public String getSell_qlrfe() {
		return sell_qlrfe;
	}

	public void setSell_qlrfe(String sell_qlrfe) {
		this.sell_qlrfe = sell_qlrfe;
	}

	public String getSell_lxdh() {
		return sell_lxdh;
	}

	public void setSell_lxdh(String sell_lxdh) {
		this.sell_lxdh = sell_lxdh;
	}

	public String getBuy_mc() {
		return buy_mc;
	}

	public void setBuy_mc(String buy_mc) {
		this.buy_mc = buy_mc;
	}

	public String getBuy_lb() {
		return buy_lb;
	}

	public void setBuy_lb(String buy_lb) {
		this.buy_lb = buy_lb;
	}

	public String getBuy_zjlx() {
		return buy_zjlx;
	}

	public void setBuy_zjlx(String buy_zjlx) {
		this.buy_zjlx = buy_zjlx;
	}

	public String getBuy_zjhm() {
		return buy_zjhm;
	}

	public void setBuy_zjhm(String buy_zjhm) {
		this.buy_zjhm = buy_zjhm;
	}

	public String getBuy_qlrfe() {
		return buy_qlrfe;
	}

	public void setBuy_qlrfe(String buy_qlrfe) {
		this.buy_qlrfe = buy_qlrfe;
	}

	public String getBuy_lxdh() {
		return buy_lxdh;
	}

	public void setBuy_lxdh(String buy_lxdh) {
		this.buy_lxdh = buy_lxdh;
	}

	public String getSell_lb_mc() {
		return sell_lb_mc;
	}

	public void setSell_lb_mc(String sell_lb_mc) {
		this.sell_lb_mc = sell_lb_mc;
	}

	public String getSell_zjlx_mc() {
		return sell_zjlx_mc;
	}

	public void setSell_zjlx_mc(String sell_zjlx_mc) {
		this.sell_zjlx_mc = sell_zjlx_mc;
	}

	public String getBuy_lb_mc() {
		return buy_lb_mc;
	}

	public void setBuy_lb_mc(String buy_lb_mc) {
		this.buy_lb_mc = buy_lb_mc;
	}

	public String getBuy_zjlx_mc() {
		return buy_zjlx_mc;
	}

	public void setBuy_zjlx_mc(String buy_zjlx_mc) {
		this.buy_zjlx_mc = buy_zjlx_mc;
	}

	public String getAll_sellerInfo() {
		return all_sellerInfo;
	}

	public void setAll_sellerInfo(String all_sellerInfo) {
		this.all_sellerInfo = all_sellerInfo;
	}

	public String getAll_buyerInfo() {
		return all_buyerInfo;
	}

	public void setAll_buyerInfo(String all_buyerInfo) {
		this.all_buyerInfo = all_buyerInfo;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public String getSlrq() {
		return slrq;
	}

	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}

	public String getSaveIsSuccess() {
		return saveIsSuccess;
	}

	public void setSaveIsSuccess(String saveIsSuccess) {
		this.saveIsSuccess = saveIsSuccess;
	}

	public String getTmp_zllxmc() {
		return tmp_zllxmc;
	}

	public void setTmp_zllxmc(String tmp_zllxmc) {
		this.tmp_zllxmc = tmp_zllxmc;
	}

	public List getZjList() {
		return zjList;
	}

	public void setZjList(List zjList) {
		this.zjList = zjList;
	}

	public String getFwxzdm() {
		return fwxzdm;
	}

	public void setFwxzdm(String fwxzdm) {
		this.fwxzdm = fwxzdm;
	}

	public List getCodeList_bz() {
		return codeList_bz;
	}

	public void setCodeList_bz(List codeList_bz) {
		this.codeList_bz = codeList_bz;
	}

	public String getXxly() {
		return xxly;
	}

	public void setXxly(String xxly) {
		this.xxly = xxly;
	}


	public String getHasHdxx() {
		return hasHdxx;
	}


	public void setHasHdxx(String hasHdxx) {
		this.hasHdxx = hasHdxx;
	}


	public List getCodeList_fwxz() {
		return codeList_fwxz;
	}


	public void setCodeList_fwxz(List codeList_fwxz) {
		this.codeList_fwxz = codeList_fwxz;
	}


	public String getCjfsdm() {
		return cjfsdm;
	}


	public void setCjfsdm(String cjfsdm) {
		this.cjfsdm = cjfsdm;
	}


	public boolean isHasMAuthorise() {
		return hasMAuthorise;
	}


	public void setHasMAuthorise(boolean hasMAuthorise) {
		this.hasMAuthorise = hasMAuthorise;
	}


}
