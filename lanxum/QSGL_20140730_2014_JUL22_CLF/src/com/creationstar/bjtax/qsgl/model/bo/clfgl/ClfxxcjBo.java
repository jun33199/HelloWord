package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm;
/**
 * <p>Title:</p>
 * <p>Description:</p>
 * @author �Ʋ���
 * @version 1.1
 */
public class ClfxxcjBo implements Serializable{
	public boolean hasMAuthorise =true;//�Ƿ����޸Ĵ������ɼ���Ϣ��Ȩ��  false --��  true--��
	public String hasHdxx="N";
	public String SaveIsSuccess = "0";//��������Ƿ�ɹ�0--δ�ɹ�  1--�ɹ�
	public String keyStr;
	public String UNEpiccode;//�Ǽ��ܶ�ά��
	//������Ϣ
	public String bbbs;//�汾��ʾ
	public String htbh;//��ͬ���
	public String htwsqyrq;//��ͬ����ǩԼ����
	public String fwzlqx;//������������
	public String fwzldz;//���������ַ
	public String fwqszylx;//����Ȩ��ת������_����
	public String fwqszylxmc;//����Ȩ��ת������_����
	public String sfwscsssggf;//�Ƿ�Ϊ�״������ѹ�����_����
	public String sfwscsssggfmc;//�Ƿ�Ϊ�״������ѹ�����_����
	public String fwcqzh;//���ݲ�Ȩ֤��
	public String fwsyqztfrq;//��������Ȩ֤�����
	public String fwjzmj;// ���ݽ������
	public String jzjgdm;// �����ṹ����
	public String jzjgmc;// �����ṹ����
	public String ghyt;//�滮��;
	public String htzj;//��ͬ�ܼ�
	public String bzdm;//���ִ���
	public String bzmc;//��������
	public String hl;//����
	public String wbje;//��ҽ��
	public String szlc;//����¥��
	public String fdczjjgmc;//���ز��н��������
	//������Ϣ
	public String all_sellerInfo;
	
	public List sellerList = new ArrayList();//������������Ϣ��ѯ�� --����������Ϣ
	public String allSellerNames4jyxxcx;//������������Ϣ��ѯ��   --��������������á�/������
	//����Ϣ
	public String all_buyerInfo;
	public List buyerList = new ArrayList();//������������Ϣ��ѯ�� --����Ϣ
	public String allBuyerNames4jyxxcx;//������������Ϣ��ѯ��   --������������á�/������
	
	public String sbbh;//��ѯʱ��
	
	//�¼��ֶ�:�������ʴ���
	public String fwxzdm;
	
	//��Ϣ��Դ  01����ά�룬02���ֹ��ɼ�
	public String xxly;
	
	public HashMap getZjMap() {
		return zjMap;
	}



	public void setZjMap(HashMap zjMap) {
		this.zjMap = zjMap;
	}



	public HashMap  zjMap = new HashMap();
	
	 
	/**
	 * @methodName:getFromData
	 * @function:
	 * 
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-16 ����03:55:00
	 * @version 1.1
	 * 
	 * 
	 */
	public Object getFromData() {
		ClfxxcjForm cf= new ClfxxcjForm();
		cf.setHasMAuthorise(this.hasMAuthorise);
		cf.setSaveIsSuccess(this.SaveIsSuccess);
		cf.setKeyStr(this.keyStr);
		cf.setSbbh(this.sbbh);
		cf.setBbbs(this.bbbs);// �汾��ʾ
		cf.setHtbh(this.htbh);// ��ͬ���
		cf.setHtwsqyrq(this.htwsqyrq);// ��ͬ����ǩԼ����
		cf.setFwzlqx(this.fwzlqx);// ������������
		cf.setFwzldz(this.fwzldz);// ���������ַ
		cf.setFwqszylx(this.fwqszylx);// ����Ȩ��ת������_����
		cf.setFwqszylxmc(this.fwqszylxmc);// ����Ȩ��ת������_����
		cf.setSfwscsssggf(this.sfwscsssggf);// �Ƿ�Ϊ�״������ѹ�����_����
		cf.setSfwscsssggfmc(this.sfwscsssggfmc);// �Ƿ�Ϊ�״������ѹ�����_����
		cf.setFwcqzh(this.fwcqzh);// ���ݲ�Ȩ֤��
		cf.setFwsyqztfrq(this.fwsyqztfrq);// ��������Ȩ֤�����
		cf.setFwjzmj(this.fwjzmj);// ���ݽ������
		cf.setJzjgdm(this.jzjgdm);// �����ṹ����
		cf.setJzjgmc(this.jzjgmc);// �����ṹ����
		cf.setGhyt(this.ghyt);// �滮��;
		cf.setHtzj(this.htzj);// ��ͬ�ܼ�
		cf.setBzdm(this.bzdm);// ���ִ���
		cf.setBzmc(this.bzmc);// ��������
		cf.setHl(this.hl);// ����
		cf.setWbje(this.wbje);// ��ҽ��
		cf.setSzlc(this.szlc);// ����¥��
		cf.setFdczjjgmc(this.fdczjjgmc);// ���ز��н��������
		cf.setAll_sellerInfo(this.all_sellerInfo);
		cf.setAll_buyerInfo(this.all_buyerInfo);
		cf.setUNEpiccode(this.UNEpiccode);
		
		cf.setFwxzdm(this.fwxzdm);
		
		cf.setXxly(this.xxly);
		
		cf.setHasHdxx(this.hasHdxx);

		return cf;
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
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
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
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}



	public String getKeyStr() {
		return keyStr;
	}



	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}



	public String getSaveIsSuccess() {
		return SaveIsSuccess;
	}



	public void setSaveIsSuccess(String saveIsSuccess) {
		SaveIsSuccess = saveIsSuccess;
	}



	public List getSellerList() {
		return sellerList;
	}



	public void setSellerList(List sellerList) {
		this.sellerList = sellerList;
	}



	public List getBuyerList() {
		return buyerList;
	}



	public void setBuyerList(List buyerList) {
		this.buyerList = buyerList;
	}



	public String getAllSellerNames4jyxxcx() {
		return allSellerNames4jyxxcx;
	}



	public void setAllSellerNames4jyxxcx(String allSellerNames4jyxxcx) {
		this.allSellerNames4jyxxcx = allSellerNames4jyxxcx;
	}



	public String getAllBuyerNames4jyxxcx() {
		return allBuyerNames4jyxxcx;
	}



	public void setAllBuyerNames4jyxxcx(String allBuyerNames4jyxxcx) {
		this.allBuyerNames4jyxxcx = allBuyerNames4jyxxcx;
	}



	public String getFwxzdm() {
		return fwxzdm;
	}



	public void setFwxzdm(String fwxzdm) {
		this.fwxzdm = fwxzdm;
	}



	public boolean isHasMAuthorise() {
		return hasMAuthorise;
	}



	public void setHasMAuthorise(boolean hasMAuthorise) {
		this.hasMAuthorise = hasMAuthorise;
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
	
	

}
