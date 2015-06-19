package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.MfskzsBo;

/**
 * 
 * <p>
 * Title:����˰������form
 * </p>
 * <p>
 * Description:form
 * </p>
 * 
 * @author ��һ��
 * @version 1.1
 */
public class MfskzsForm extends QueryBaseForm {

	private String[] columns =
	{"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
    "skssksrq", "skssjsrq", "szdm", "szmc", "sl","jmje","yjje"};
	
	private String hasHdxx="N";
	private String tfrq;// �����
	private String zsjg;// ���ջ���
	
	private String yhid;// �û�ID
	private String yhxm;// �û�����
	
	private String htbh;// ��ͬ���
	private String nsrmc;// ������˰������
	private String zjhm;// ����֤������
	private String cxzjhm;// ��ѯ����֤������
	public String htwsqyrq;// ����˫����ͬǩ������
	
	private String sbbh_his;// �걨��ż�¼
	private String nsrmc_his;// ��˰��������¼
	private String sfzjlxmc_his;// ֤�����ͼ�¼
	private String sfzjhm_his;// ֤�������¼
	private String fwsyqzh_his;// ��������Ȩ֤�ż�¼
	private String tdfwzldz_his;// ���ز�λ�ü�¼
	private String htqdsj_his;// ��ͬǩ�����ڼ�¼
	private String sbrq_his;// �걨���ڼ�¼
	private String fwjzmj_his;// ���ݽ��������¼
	private String jsje_his;// ��˰����¼
	private String sl_his;// ˰�ʼ�¼
	private String ynse_his;// Ӧ�ɽ���¼
	private String fwqszylx_his;// ����Ȩ��ת������	
	
	private String zsqx;// ��������
	private String fwyz;// ����ԭֵ
	
	private ArrayList sbxxHisList = new ArrayList();//��˰����˰����ʷ��Ϣ  

	private String sbbh;// �걨���
    private String scriptStr;// js�����ַ���
    private String jsjdm;// ���������
    
    private ArrayList dataList = new ArrayList();//�걨ҳ�����ݼ�
    public String hjsjje; 
    private String hjjsje;//�ϼƼ�˰���
    private String hjsjse;//�ϼ�Ӧ�ɽ��
    private String hjjmje;//�ϼƼ�����
    private String hjyjje;//�ϼ�ʵ�ɽ��
    
    private String zjlxdm; //֤�����ʹ���
    private String zjlxmc; //֤����������

	private String htbh1;//�����걨����ά��������ݺ�ͬ���
    private String bz; //������Ϣһ
    private String message; //������Ϣ��
    private String pzzhdm; //Ʊ֤�˻�����

    private ArrayList sbxxList = new ArrayList();//�����걨������Ϣ
    private String tdzzssl; //������ֵ˰˰��

    private String dybs; //��ӡ��ʶ
	//��˰֤��ӡ  (zsjg tfrq jsjdm nsrmc �ֶ������湲��)
    private String wszxh; //��˰֤���
    private String headWszh; //��˰֤��
    private String zclx; //ע������
    private String dz; //��ַ
    private String skssksrq; //˰��������ʼ����
    private String skssjsrq; //˰��������������
    private String hjje; //�ϼƽ��
    private String hjjedx; //�ϼƽ���д
    private String wszbz; //��ע
    private String mxSz; //��ϸ��Ϣ��˰������
    private String mxPmmc; //��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
    private String mxKssl; //��ϸ��Ϣ����˰����
    private String mxJsje; //��ϸ��Ϣ����˰���
    private String mxSl; //��ϸ��Ϣ��˰��
    private String mxSkssrq; //˰��������ʼ����
    private String mxYjhkc; //��ϸ��Ϣ���ѽɻ�۳�
    private String mxSjse; //��ϸ��Ϣ��Ӧ��˰��
    private String mxJmje; //��ϸ��Ϣ������˰��
    private String mxYjje; //��ϸ��Ϣ��ʵ��˰��
    
    private String sbrq; //�걨����
    private String yhmc; //��������
    private String zh; //�ʺ�
    private String swjgzzjgmc; //���ջ������ƣ�˰���������ƣ�
    private String gkzzjgdm; //�������
    private String gkzzjgmc; //��������
    private String sbrqn; //�걨������
    private String sbrqy; //�걨������
    private String sbrqr; //�걨������
    
    public String rqcs; //���ڲ���
    //added by zhangj
    private String fwhdlxdm;// ���ݺ˶����ʹ���
    private String htzj;// ��ͬ�ܼ�
    private String  isPrintSuccess;
	public Object getData() {
		MfskzsBo bo = new MfskzsBo();
		bo.tdzzssl = this.tdzzssl;
		bo.hasHdxx = this.hasHdxx;
		bo.yhid = this.yhid;
		bo.yhxm = this.yhxm;
		bo.tfrq = this.tfrq;// �����
		bo.zsjg = this.zsjg;// ���ջ���
		bo.htbh = this.htbh;// ��ͬ���
		bo.nsrmc = this.nsrmc;// ������˰������
		bo.zjhm = this.zjhm;// ����֤������
		bo.cxzjhm = this.cxzjhm;// ��ѯ����֤������
		bo.htwsqyrq = this.htwsqyrq;
		bo.rqcs = this.rqcs;
		
		bo.sbbh_his = this.sbbh_his;// �걨��ż�¼
		bo.nsrmc_his = this.nsrmc_his;// ֤�����ͼ�¼
		bo.sfzjlxmc_his = this.sfzjlxmc_his;// ֤�����ͼ�¼
		bo.sfzjhm_his = this.sfzjhm_his;// ֤�������¼
		bo.fwsyqzh_his = this.fwsyqzh_his;// ��������Ȩ֤�ż�¼
		bo.tdfwzldz_his = this.tdfwzldz_his;// ���ز�λ�ü�¼
		bo.htqdsj_his = this.htqdsj_his;// ��ͬǩ�����ڼ�¼
		bo.sbrq_his = this.sbrq_his;// �걨���ڼ�¼
		bo.fwjzmj_his = this.fwjzmj_his;// ���ݽ��������¼
		bo.jsje_his = this.jsje_his;// ��˰����¼
		bo.sl_his = this.sl_his;// ˰�ʼ�¼
		bo.ynse_his = this.ynse_his;// Ӧ�ɽ���¼
		bo.fwqszylx_his = this.fwqszylx_his;//����Ȩ��ת������
		bo.sbxxHisList=this.sbxxHisList;//��˰����˰����ʷ��Ϣ
		bo.zsqx = this.zsqx;// ��������
		bo.sbbh = this.sbbh;// �걨���
		bo.fwyz = this.fwyz;//��ί��ѯ����ԭֵ
		bo.scriptStr = this.scriptStr;// js�����ַ���
		bo.jsjdm = this.jsjdm;// ���������		
		bo.dataList=this.dataList;//ҳ�����ݼ�
		bo.hjjsje=this.hjjsje;//�ϼƼ�˰���
		bo.hjsjse=this.hjsjse;//�ϼ�Ӧ�ɽ��
		bo.hjjmje=this.hjjmje;//�ϼƼ�����
		bo.hjyjje=this.hjyjje;//�ϼ�ʵ�ɽ��
		bo.zjlxdm=this.zjlxdm;//֤�����ʹ���
		bo.zjlxmc=this.zjlxmc;//֤����������
		
		bo.bz=this.bz;
		bo.message=this.message;
		bo.htbh1=this.htbh1;
		bo.pzzhdm=this.pzzhdm;//Ʊ֤�˻�����
		bo.sbxxList=this.sbxxList;//�����걨������Ϣ
		
		bo.dybs=this.dybs; //��ӡ��ʶ
		//��˰֤��ӡ  (zsjg tfrq jsjdm nsrmc �ֶ������湲��)
		bo.wszxh=this.wszxh; //��˰֤���
		bo.headWszh=this.headWszh; //��˰֤��
		bo.zclx=this.zclx; //ע������
		bo.dz=this.dz; //��ַ
		bo.skssksrq=this.skssksrq; //˰��������ʼ����
		bo.skssjsrq=this.skssjsrq; //˰��������������
		bo.hjje=this.hjje; //�ϼƽ��
		bo.hjjedx=this.hjjedx; //�ϼƽ���д
		bo.wszbz=this.wszbz; //��ע
		bo.mxSz=this.mxSz; //��ϸ��Ϣ��˰������
		bo.mxPmmc=this.mxPmmc; //��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
		bo.mxKssl=this.mxKssl; //��ϸ��Ϣ����˰����
		bo.mxJsje=this.mxJsje; //��ϸ��Ϣ����˰���
		bo.mxSl=this.mxSl; //��ϸ��Ϣ��˰��
		bo.mxSkssrq = this.mxSkssrq; //��ϸ��Ϣ��˰����������
		bo.mxYjhkc=this.mxYjhkc; //��ϸ��Ϣ���ѽɻ�۳�
		bo.mxSjse=this.mxSjse; //��ϸ��Ϣ��Ӧ��˰��
		bo.mxJmje=this.mxJmje; //��ϸ��Ϣ������˰��
		bo.mxYjje=this.mxYjje; //��ϸ��Ϣ��ʵ��˰��
		
		bo.sbrq=this.sbrq;//�걨����
		bo.yhmc=this.yhmc;//��������
		bo.zh=this.zh;//�ʺ�
		bo.swjgzzjgmc=this.swjgzzjgmc;//���ջ������ƣ�˰���������ƣ�
		bo.gkzzjgdm=this.gkzzjgdm;//�������
		bo.gkzzjgmc=this.gkzzjgmc;//��������
		bo.sbrqn=this.sbrqn;//�걨������
		bo.sbrqy=this.sbrqy;//�걨������
		bo.sbrqr=this.sbrqr;//�걨������
		bo.fwhdlxdm = this.fwhdlxdm;//
		bo.htzj = this.htzj;// 
		bo.isPrintSuccess=this.isPrintSuccess;
		return bo;
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

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
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

	public void setMxPmmc(String mxPmmc) {
		this.mxPmmc = mxPmmc;
	}

	public String getMxSkssrq() {
		return mxSkssrq;
	}

	public void setMxSkssrq(String mxSkssrq) {
		this.mxSkssrq = mxSkssrq;
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

	public String getFwyz() {
		return fwyz;
	}

	public void setFwyz(String fwyz) {
		this.fwyz = fwyz;
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
