package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.MfskzsForm;
/**
 * <p>Title:</p>
 * <p>Description:</p>
 * @author ��һ��
 * @version 1.1
 */
public class MfskzsBo implements Serializable{

	public String hasHdxx="N";
	public String yhid;// �û�ID
	public String yhxm;// �û�����
	
	public String tfrq;// �����
	public String zsjg;// ���ջ���
	public String htbh;// ��ͬ���
	public String nsrmc;// ������˰������
	public String zjhm;// ����֤������
	public String cxzjhm;// ��ѯ����֤������
	public String htwsqyrq;// ����˫����ͬǩ������
	public String fwyz; //��ί��ѯ����ԭֵ
	
	public String sbbh_his;// �걨��ż�¼
	public String nsrmc_his;// ��˰��������¼
	public String sfzjlxmc_his;// ֤�����ͼ�¼
	public String sfzjhm_his;// ֤�������¼
	public String fwsyqzh_his;// ��������Ȩ֤�ż�¼
	public String tdfwzldz_his;// ���ز�λ�ü�¼
	public String htqdsj_his;// ��ͬǩ�����ڼ�¼
	public String sbrq_his;// �걨���ڼ�¼
	public String fwjzmj_his;// ���ݽ��������¼
	public String jsje_his;// ��˰����¼
	public String sl_his;// ˰�ʼ�¼
	public String ynse_his;// Ӧ�ɽ���¼
	public String fwqszylx_his;// ����Ȩ��ת������
	 
    public ArrayList sbxxHisList = new ArrayList();//��˰����˰����ʷ��Ϣ
    
	public String zsqx;// ��������
	public String sbbh;// �걨���	
	public String scriptStr;// js�����ַ���
	public String jsjdm;// ���������
	
	public ArrayList dataList = new ArrayList();//ҳ�����ݼ�
	public String hjsjje; 
	public String hjjsje;//�ϼƼ�˰���
	public String hjsjse;//�ϼ�Ӧ�ɽ��
	public String hjjmje;//�ϼƼ�����
	public String hjyjje;//�ϼ�ʵ�ɽ��
    
	public String zjlxdm; //֤�����ʹ���
	public String zjlxmc; //֤����������

	public String htbh1;// �����걨����ά��������ݺ�ͬ���
	public String bz; //������Ϣһ
	public String message; //������Ϣ��
	
	public String pzzhdm; //Ʊ֤�˻�����
	
	public ArrayList sbxxList = new ArrayList();//�����걨������Ϣ
	public String tdzzssl; //������ֵ˰˰��
	
	public String dybs; //��ӡ��ʶ
	//��˰֤��ӡ  (zsjg tfrq jsjdm nsrmc �ֶ������湲��)
    public String wszxh; //��˰֤���
    public String headWszh; //��˰֤��
    public String zclx; //ע������
    public String dz; //��ַ
    public String skssksrq; //˰��������ʼ����
    public String skssjsrq; //˰��������������
    public String hjje; //�ϼƽ��
    public String hjjedx; //�ϼƽ���д
    public String wszbz; //��ע
    public String mxSz; //��ϸ��Ϣ��˰������
    public String mxPmmc; //��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
    public String mxKssl; //��ϸ��Ϣ����˰����
    public String mxJsje; //��ϸ��Ϣ����˰���
    public String mxSl; //��ϸ��Ϣ��˰��
    public String mxSkssrq; //��ϸ��Ϣ��˰����������
    public String mxYjhkc; //��ϸ��Ϣ���ѽɻ�۳�
    public String mxSjse; //��ϸ��Ϣ��ʵ��˰��
    public String mxJmje; //��ϸ��Ϣ������˰��
    public String mxYjje; //��ϸ��Ϣ��ʵ��˰��
    
    
    public String sbrq; //�걨����
    public String yhmc; //��������
    public String zh; //�ʺ�   
    public String swjgzzjgmc; //���ջ������ƣ�˰���������ƣ�
    public String gkzzjgdm; //�������
    public String gkzzjgmc; //��������
    
    public String sbrqn; //�걨������
    public String sbrqy; //�걨������
    public String sbrqr; //�걨������
    
    public String rqcs; //���ڲ���
    //added by zhangj
    public String fwhdlxdm;// �˶����ʹ���
    public String htzj;//��ͬ�ܼ�
    public String  isPrintSuccess;
 
	/**
	 * @methodName:getFromData
	 * @function:
	 * 
	 * @return
	 * @author:��һ��
	 * @create
	 * @version 1.1
	 * 
	 * 
	 */
	public Object getFromData() {
		MfskzsForm mf= new MfskzsForm();
		mf.setYhid(this.yhid);
		mf.setYhxm(this.yhxm);
		mf.setTdzzssl(this.tdzzssl);
		mf.setHasHdxx(this.hasHdxx);
		mf.setTfrq(this.tfrq);// �����
		mf.setZsjg(this.zsjg);// ���ջ���
		mf.setHtbh(this.htbh);// ��ͬ���
		mf.setNsrmc(this.nsrmc);// ������˰������
		mf.setZjhm(this.zjhm);// ����֤������
		mf.setCxzjhm(this.cxzjhm);// ����֤������
		mf.setHtwsqyrq(this.htwsqyrq); 
		mf.setRqcs(this.rqcs);

		mf.setSbbh_his(this.sbbh_his);// �걨��ż�¼
		mf.setNsrmc_his(this.nsrmc_his);// ֤�����ͼ�¼
		mf.setSfzjlxmc_his(this.sfzjlxmc_his);// ֤�����ͼ�¼
		mf.setSfzjhm_his(this.sfzjhm_his);// ֤�������¼
		mf.setFwsyqzh_his(this.fwsyqzh_his);// ��������Ȩ֤�ż�¼
		mf.setTdfwzldz_his(this.tdfwzldz_his);// ���ز�λ�ü�¼
		mf.setHtqdsj_his(this.htqdsj_his);// ��ͬǩ�����ڼ�¼
		mf.setSbrq_his(this.sbrq_his);// �걨���ڼ�¼
		mf.setFwjzmj_his(this.fwjzmj_his);// ���ݽ��������¼
		mf.setJsje_his(this.jsje_his);// ��˰����¼
		mf.setSl_his(this.sl_his);// ˰�ʼ�¼
		mf.setYnse_his(this.ynse_his);// Ӧ�ɽ���¼
		mf.setFwqszylx_his(this.fwqszylx_his);//����Ȩ��ת������
		mf.setSbxxHisList(this.sbxxHisList);//��˰����˰����ʷ��Ϣ
		mf.setZsqx(this.zsqx);// ��������
		
		mf.setSbbh(this.sbbh);// �걨���
		mf.setScriptStr(this.scriptStr);// js�����ַ���
		mf.setJsjdm(this.jsjdm);// ���������
		mf.setFwyz(this.fwyz);// ��ί��ѯ����ԭֵ
		
		mf.setDataList(this.dataList);// ҳ�����ݼ�
		mf.setHjjsje(this.hjjsje);//�ϼƼ�˰���
		mf.setHjsjse(this.hjsjse);//�ϼ�Ӧ�ɽ��
		mf.setHjjmje(this.hjjmje);//�ϼƼ�����
		mf.setHjyjje(this.hjyjje);// �ϼ�ʵ�ɽ��
		mf.setZjlxdm(this.zjlxdm);// ֤�����ʹ���
		mf.setZjlxmc(this.zjlxmc);// ֤����������
		mf.setBz(this.bz);
		mf.setMessage(this.message);
		mf.setHtbh1(this.htbh1);
		mf.setPzzhdm(this.pzzhdm);//Ʊ֤�˻�����
		mf.setSbxxList(this.sbxxList);//�����걨������Ϣ
		
		mf.setDybs(this.dybs);//��ӡ��ʶ
		mf.setWszxh(this.wszxh);//��˰֤���
		mf.setHeadWszh(this.headWszh);//��˰֤��
		mf.setZclx(this.zclx);//ע������
		mf.setDz(this.dz);//��ַ
		mf.setSkssksrq(this.skssksrq);//˰��������ʼ����
		mf.setSkssjsrq(this.skssjsrq);//˰��������������
		mf.setHjje(this.hjje);//�ϼƽ��
		mf.setHjjedx(this.hjjedx);//�ϼƽ���д
		mf.setWszbz(this.wszbz);//��ע
		mf.setMxSz(this.mxSz);//��ϸ��Ϣ��˰������
		mf.setMxPmmc(this.mxPmmc);//��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
		mf.setMxKssl(this.mxKssl);//��ϸ��Ϣ����˰����
		mf.setMxJsje(this.mxJsje);//��ϸ��Ϣ����˰���
		mf.setMxSl(this.mxSl);//��ϸ��Ϣ��˰��
		mf.setMxSkssrq(this.mxSkssrq);
		mf.setMxYjhkc(this.mxYjhkc);//��ϸ��Ϣ���ѽɻ�۳�		
		mf.setMxSjse(this.mxSjse);//��ϸ��Ϣ��Ӧ��˰��	
		mf.setMxJmje(this.mxJmje); //��ϸ��Ϣ������˰��
		mf.setMxYjje(this.mxYjje); //��ϸ��Ϣ��ʵ��˰��
		
		mf.setSbrq(this.sbrq);//�걨����
		mf.setYhmc(this.yhmc);//��������
		mf.setZh(this.zh);//�ʺ�
		mf.setSwjgzzjgmc(this.swjgzzjgmc);//���ջ������ƣ�˰���������ƣ�
		mf.setGkzzjgdm(this.gkzzjgdm);//�������
		mf.setGkzzjgmc(this.gkzzjgmc);//��������
		mf.setSbrqn(this.sbrqn);//�걨������
		mf.setSbrqy(this.sbrqy);//�걨������
		mf.setSbrqr(this.sbrqr);//�걨������
		mf.setFwhdlxdm(this.fwhdlxdm);// ���ݺ˶����ʹ���
		mf.setHtzj(this.htzj);// ���ݺ˶����ʹ���
		mf.setIsPrintSuccess(this.isPrintSuccess);
		return mf;
	}
	
	public String getTfrq() {
		return tfrq;
	}

	public void setTfrq(String tfrq) {
		this.tfrq = tfrq;
	}

	public String getZsjg() {
		return zsjg;
	}

	public void setZsjg(String zsjg) {
		this.zsjg = zsjg;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}	

	public String getCxzjhm() {
		return cxzjhm;
	}

	public void setCxzjhm(String cxzjhm) {
		this.cxzjhm = cxzjhm;
	}

	public String getSbbh_his() {
		return sbbh_his;
	}

	public void setSbbh_his(String sbbh_his) {
		this.sbbh_his = sbbh_his;
	}

	public String getNsrmc_his() {
		return nsrmc_his;
	}

	public void setNsrmc_his(String nsrmc_his) {
		this.nsrmc_his = nsrmc_his;
	}

	public String getSfzjlxmc_his() {
		return sfzjlxmc_his;
	}

	public void setSfzjlxmc_his(String sfzjlxmc_his) {
		this.sfzjlxmc_his = sfzjlxmc_his;
	}

	public String getSfzjhm_his() {
		return sfzjhm_his;
	}

	public void setSfzjhm_his(String sfzjhm_his) {
		this.sfzjhm_his = sfzjhm_his;
	}

	public String getFwsyqzh_his() {
		return fwsyqzh_his;
	}

	public void setFwsyqzh_his(String fwsyqzh_his) {
		this.fwsyqzh_his = fwsyqzh_his;
	}

	public String getTdfwzldz_his() {
		return tdfwzldz_his;
	}

	public void setTdfwzldz_his(String tdfwzldz_his) {
		this.tdfwzldz_his = tdfwzldz_his;
	}

	public String getHtqdsj_his() {
		return htqdsj_his;
	}

	public void setHtqdsj_his(String htqdsj_his) {
		this.htqdsj_his = htqdsj_his;
	}

	public String getSbrq_his() {
		return sbrq_his;
	}

	public void setSbrq_his(String sbrq_his) {
		this.sbrq_his = sbrq_his;
	}

	public String getFwjzmj_his() {
		return fwjzmj_his;
	}

	public void setFwjzmj_his(String fwjzmj_his) {
		this.fwjzmj_his = fwjzmj_his;
	}

	public String getJsje_his() {
		return jsje_his;
	}

	public void setJsje_his(String jsje_his) {
		this.jsje_his = jsje_his;
	}

	public String getSl_his() {
		return sl_his;
	}

	public void setSl_his(String sl_his) {
		this.sl_his = sl_his;
	}

	public String getYnse_his() {
		return ynse_his;
	}

	public void setYnse_his(String ynse_his) {
		this.ynse_his = ynse_his;
	}
	
	public String getFwqszylx_his() {
		return fwqszylx_his;
	}

	public void setFwqszylx_his(String fwqszylx_his) {
		this.fwqszylx_his = fwqszylx_his;
	}

	public ArrayList getSbxxHisList() {
		return sbxxHisList;
	}

	public void setSbxxHisList(ArrayList sbxxHisList) {
		this.sbxxHisList = sbxxHisList;
	}
	
	public String getZsqx() {
		return zsqx;
	}

	public void setZsqx(String zsqx) {
		this.zsqx = zsqx;
	}

    public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public String getScriptStr() {
		return scriptStr;
	}

	public void setScriptStr(String scriptStr) {
		this.scriptStr = scriptStr;
	}
	
	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	
	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}

	public String getHjsjje() {
		return hjsjje;
	}

	public void setHjsjje(String hjsjje) {
		this.hjsjje = hjsjje;
	}
	
	public String getZjlxdm() {
		return zjlxdm;
	}

	public void setZjlxdm(String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}

	public String getZjlxmc() {
		return zjlxmc;
	}

	public void setZjlxmc(String zjlxmc) {
		this.zjlxmc = zjlxmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHtbh1() {
		return htbh1;
	}

	public void setHtbh1(String htbh1) {
		this.htbh1 = htbh1;
	}

	public String getPzzhdm() {
		return pzzhdm;
	}

	public void setPzzhdm(String pzzhdm) {
		this.pzzhdm = pzzhdm;
	}

	public ArrayList getSbxxList() {
		return sbxxList;
	}

	public void setSbxxList(ArrayList sbxxList) {
		this.sbxxList = sbxxList;
	}

	public String getWszxh() {
		return wszxh;
	}

	public void setWszxh(String wszxh) {
		this.wszxh = wszxh;
	}

	public String getDybs() {
		return dybs;
	}

	public void setDybs(String dybs) {
		this.dybs = dybs;
	}

	public String getHeadWszh() {
		return headWszh;
	}

	public void setHeadWszh(String headWszh) {
		this.headWszh = headWszh;
	}

	public String getZclx() {
		return zclx;
	}

	public void setZclx(String zclx) {
		this.zclx = zclx;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getHjjedx() {
		return hjjedx;
	}

	public void setHjjedx(String hjjedx) {
		this.hjjedx = hjjedx;
	}

	public String getWszbz() {
		return wszbz;
	}

	public void setWszbz(String wszbz) {
		this.wszbz = wszbz;
	}

	public String getMxSz() {
		return mxSz;
	}

	public void setMxSz(String mxSz) {
		this.mxSz = mxSz;
	}

	public String getMxPmmc() {
		return mxPmmc;
	}

	public String getMxSkssrq() {
		return mxSkssrq;
	}

	public void setMxSkssrq(String mxSkssrq) {
		this.mxSkssrq = mxSkssrq;
	}

	public void setMxPmmc(String mxPmmc) {
		this.mxPmmc = mxPmmc;
	}

	public String getMxKssl() {
		return mxKssl;
	}

	public void setMxKssl(String mxKssl) {
		this.mxKssl = mxKssl;
	}

	public String getMxJsje() {
		return mxJsje;
	}

	public void setMxJsje(String mxJsje) {
		this.mxJsje = mxJsje;
	}

	public String getMxSl() {
		return mxSl;
	}

	public void setMxSl(String mxSl) {
		this.mxSl = mxSl;
	}

	public String getMxYjhkc() {
		return mxYjhkc;
	}

	public void setMxYjhkc(String mxYjhkc) {
		this.mxYjhkc = mxYjhkc;
	}

	public String getMxSjse() {
		return mxSjse;
	}

	public void setMxSjse(String mxSjse) {
		this.mxSjse = mxSjse;
	}

	public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

	public String getGkzzjgdm() {
		return gkzzjgdm;
	}

	public void setGkzzjgdm(String gkzzjgdm) {
		this.gkzzjgdm = gkzzjgdm;
	}

	public String getGkzzjgmc() {
		return gkzzjgmc;
	}

	public void setGkzzjgmc(String gkzzjgmc) {
		this.gkzzjgmc = gkzzjgmc;
	}

	public String getSbrqn() {
		return sbrqn;
	}

	public void setSbrqn(String sbrqn) {
		this.sbrqn = sbrqn;
	}

	public String getSbrqy() {
		return sbrqy;
	}

	public void setSbrqy(String sbrqy) {
		this.sbrqy = sbrqy;
	}

	public String getSbrqr() {
		return sbrqr;
	}

	public void setSbrqr(String sbrqr) {
		this.sbrqr = sbrqr;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getYhxm() {
		return yhxm;
	}

	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}

	public String getHjyjje() {
		return hjyjje;
	}

	public void setHjyjje(String hjyjje) {
		this.hjyjje = hjyjje;
	}

	public String getHjjsje() {
		return hjjsje;
	}

	public void setHjjsje(String hjjsje) {
		this.hjjsje = hjjsje;
	}

	public String getHjsjse() {
		return hjsjse;
	}

	public void setHjsjse(String hjsjse) {
		this.hjsjse = hjsjse;
	}

	public String getHjjmje() {
		return hjjmje;
	}

	public void setHjjmje(String hjjmje) {
		this.hjjmje = hjjmje;
	}

	public String getMxJmje() {
		return mxJmje;
	}

	public void setMxJmje(String mxJmje) {
		this.mxJmje = mxJmje;
	}

	public String getMxYjje() {
		return mxYjje;
	}

	public void setMxYjje(String mxYjje) {
		this.mxYjje = mxYjje;
	}

	public String getHtwsqyrq() {
		return htwsqyrq;
	}

	public void setHtwsqyrq(String htwsqyrq) {
		this.htwsqyrq = htwsqyrq;
	}
	
	public String getRqcs() {
		return rqcs;
	}

	public void setRqcs(String rqcs) {
		this.rqcs = rqcs;
	}

	public String getFwyz() {
		return fwyz;
	}

	public void setFwyz(String fwyz) {
		this.fwyz = fwyz;
	}

	public String getHasHdxx() {
		return hasHdxx;
	}

	public void setHasHdxx(String hasHdxx) {
		this.hasHdxx = hasHdxx;
	}

	public String getTdzzssl() {
		return tdzzssl;
	}

	public void setTdzzssl(String tdzzssl) {
		this.tdzzssl = tdzzssl;
	}
	public String getFwhdlxdm() {
		return fwhdlxdm;
	}


	public void setFwhdlxdm(String fwhdlxdm) {
		this.fwhdlxdm = fwhdlxdm;
	}
	public String getHtzj() {
		return htzj;
	}

	public void setHtzj(String htzj) {
		this.htzj = htzj;
	}

	public String getIsPrintSuccess() {
		return isPrintSuccess;
	}

	public void setIsPrintSuccess(String isPrintSuccess) {
		this.isPrintSuccess = isPrintSuccess;
	}
	
}
