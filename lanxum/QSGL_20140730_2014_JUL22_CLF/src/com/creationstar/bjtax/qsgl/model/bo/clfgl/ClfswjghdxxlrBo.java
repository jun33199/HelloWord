package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;

/**
 * ������˰����غ˶���Ϣ¼�� BO
 * @author 
 *
 */
public class ClfswjghdxxlrBo extends ClfxxcjBo {
	private String isSaved ="false";//�˶���Ϣ�Ѿ�������
	private String hasMfSkzsxx = "false";//�ú˶�������˰��������Ϣ
	private String hasMfFpdkxx = "false";//�ú˶���������Ʊ������Ϣ
	
	
	public String szlc_show;
	public String zlc_show;
	
	private String sqrxzdz; //��������סַ 
	private String jtwyshyhbz; //�Ƿ�Ϊ��ͥΨһ�����÷�
	private String fwlxdm; //��������
	private String jcnd; //�������
	private String zlc;//��¥��
	private String ygffpje ="0.00";//ԭ������Ʊ���
	private String gfzmrq; //����֤������
	private String tdzzssbfs; //������ֵ˰�걨��ʽ
	private String qdfcqsje; //ȡ�÷��ز�ʱ�����ɵ���˰���
	private String qdfcyhsje; //ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���
	private String qdtdsyqzfje; //ȡ������ʹ��Ȩ��֧���Ľ��
	private String jfpgjg; //�ɷ���������������۸�
	private String jgpgfy; //�۸���������
	private String fdczjjsjdm; //��˰���������
	private String fdczjswdjzh; //��˰˰��ǼǺ���
	private String fdczjlxdh; //���ز��н���ϵ�绰
	private String fdczjjjr; //���ز�����������
	private String fdczjjjrlxdh; //���ز���������ϵ�绰
	private String fdczjjjrzjhm; //���ز����������֤����
	private String fdczjjjrzgzsh; //�������ʸ�֤�����
	private String cqzbzjzmjfl; //��Ȩ֤��ע�������
	private String mpmjydj ="0.00"; //ÿƽ�׽��׵���
	private String ptzfzgxj; //��ͨס������޼�
	private String fwrjl; //�����ݻ���
	private String hfbz; //���ֱ�׼
	private String zfsjsjfl; //ס��ʹ��ʱ��
	private String yyszsfs; //Ӫҵ˰���շ�ʽ
	private String grsdszsfs; //��������˰���շ�ʽ
	private String tdzsszsfs; //������ֵ˰���շ�ʽ
	private String jssrqrfs; //��˰����ȷ�Ϸ�ʽ
	private String jsje; //��˰������
	//private String ygffpje; //ԭ������Ʊ���
	private String zfpgjg; //ס�������۸�
	//private String jfzjwpgje; //����:�ɷ��������������۸�
	private String zfzxfy; //ס��װ�޷���
	private String zfdklx; //ס��������Ϣ
	private String sxf; //������
	private String gzf; //��֤��
	private String tdcrj;// new add ���س��ý�
	private String hlfy ="0.00"; //�������
	private String tdjcdm; //���ؼ��δ���
	private String tdjcmc; //���ؼ�������
	private String fwcqzbzzflxdm; //���ݲ�Ȩ֤��עס�����ʹ���
	private String fwcqzbzzflxmc; //���ݲ�Ȩ֤��עס����������
	
	//new add
	private String mpmhdjg="0.00";//ÿƽ�׺˶��۸�
	private String cjssl;//�ǽ�˰˰��
	private String fpszrq;//��Ʊ��������
	private String anjjse;//����Ӽ�����
	private String fwszqydm;//���������������
	
	//added by zhangj
	private String fwhdlxdm;//���ݺ˶����ʹ���0��ס����1����ס��
	private String qszyxsmxdm; //����Ȩ��ת����ϸ����
	private String qszyxsmxmc; //����Ȩ��ת����ϸ����
	private String yqspfwjsjg; //ԭ��˰Ʊ���ݼ�˰�۸� 
	private String yhszsfs; //ӡ��˰���շ�ʽ
	private boolean isQuery;//�жϲ����ǲ�ѯ�����л����ݺ˶�����
	private String tdzsszsfsSubmit;//
	private String yhszsfsSubmit;//
	private String fpcxLink;//
	private String lrrq;
	private String fwszqyjeSubmit;
	private String fwszqyje;
	// new  modify  by yugw
		private String fwszqymc;//����������������
		public String getFwszqymc() {
			return fwszqymc;
		}


		public void setFwszqymc(String fwszqymc) {
			this.fwszqymc = fwszqymc;
		}
		//end 
		
	/**
	 * ��ť�ύ��
	 */
	private String cqzbzjzmjflSubmit;//��Ȩ֤��ע�������Submit
	private String hfbzSubmit;//���ֱ�׼Submit
	private String grsdszsfsSubmit;//��������˰���շ�ʽSubmit
	private String yyszsfsSubmit;//Ӫҵ˰���շ�ʽSubmit
	private String jsjeSubmit;//��˰������Submit
/*	private String htbhSubmit;//��ͬ���Submit
	private String sbbhSubmit;//�걨���Submit
*/	
	
	// ��ӡ��
	private ArrayList mfsbxxList = new ArrayList();
	private String sjhjje="";//ʵ�ɺϼƽ��
	
	
	/**
	 * @methodName:getFromData1
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
		ClfswjghdxxlrForm cf= new ClfswjghdxxlrForm();
		//==============�������ɼ���Ϣ=================
		cf.setHasMAuthorise(this.hasMAuthorise);
		cf.setSaveIsSuccess(this.SaveIsSuccess);
		cf.setHasMfSkzsxx(this.hasMfSkzsxx);//�ú˶�������˰��������Ϣ
		cf.setHasMfFpdkxx(this.hasMfFpdkxx);//�ú˶���������Ʊ������Ϣ
		 
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
		cf.setZlc_show(this.zlc_show);//
		cf.setSzlc_show(this.szlc_show);
		cf.setAll_sellerInfo(this.all_sellerInfo);
		cf.setAll_buyerInfo(this.all_buyerInfo);
		cf.setUNEpiccode(this.UNEpiccode);
		
		//===============�˶���Ϣ======================
		cf.setSzlc_show(this.szlc_show);
		cf.setZlc_show(this.zlc_show);
		cf.setSqrxzdz(this.sqrxzdz);//��������סַ
		cf.setJtwyshyhbz(this.jtwyshyhbz);//�Ƿ�Ϊ��ͥΨһ�����÷�
		cf.setFwlxdm(this.fwlxdm);//��������
		cf.setJcnd(this.jcnd);//�������
		cf.setZlc(this.zlc);//��¥��
		cf.setYgffpje(this.ygffpje);//ԭ������Ʊ���
		cf.setGfzmrq(this.gfzmrq);//����֤������
		cf.setTdzzssbfs(this.tdzzssbfs);//������ֵ˰�걨��ʽ
		cf.setQdfcqsje(this.qdfcqsje);//ȡ�÷��ز�ʱ�����ɵ���˰���
		cf.setQdfcyhsje(this.qdfcyhsje);//ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���
		cf.setQdtdsyqzfje(this.qdtdsyqzfje);//ȡ������ʹ��Ȩ��֧���Ľ��
		cf.setJfpgjg(this.jfpgjg);//�ɷ���������������۸�
		cf.setJgpgfy(this.jgpgfy);//�۸���������
		cf.setFdczjjsjdm(this.fdczjjsjdm);//��˰���������
		cf.setFdczjswdjzh(this.fdczjswdjzh);//��˰˰��ǼǺ���
		cf.setFdczjlxdh(this.fdczjlxdh);//���ز��н���ϵ�绰
		cf.setFdczjjjr(this.fdczjjjr);//���ز�����������
		cf.setFdczjjjrlxdh(this.fdczjjjrlxdh);//���ز���������ϵ�绰
		cf.setFdczjjjrzjhm(this.fdczjjjrzjhm);//���ز����������֤����
		cf.setFdczjjjrzgzsh(this.fdczjjjrzgzsh);//�������ʸ�֤�����
		cf.setCqzbzjzmjfl(this.cqzbzjzmjfl);//��Ȩ֤��ע�������
		cf.setMpmjydj(this.mpmjydj);//ÿƽ�׽��׵���
		cf.setPtzfzgxj(this.ptzfzgxj);//��ͨס������޼�
		cf.setFwrjl(this.fwrjl);//�����ݻ���
		cf.setHfbz(this.hfbz);//���ֱ�׼
		cf.setZfsjsjfl(this.zfsjsjfl);//ס��ʹ��ʱ��
		cf.setYyszsfs(this.yyszsfs);//Ӫҵ˰���շ�ʽ
		cf.setGrsdszsfs(this.grsdszsfs);//��������˰���շ�ʽ
		cf.setTdzsszsfs(this.tdzsszsfs);//������ֵ˰���շ�ʽ
		cf.setJssrqrfs(this.jssrqrfs);//��˰����ȷ�Ϸ�ʽ
		cf.setJsje(this.jsje);//��˰������
		cf.setZfpgjg(this.zfpgjg);//ס�������۸�
		cf.setZfzxfy(this.zfzxfy);//ס��װ�޷���
		cf.setZfdklx(this.zfdklx);//ס��������Ϣ
		cf.setSxf(this.sxf);//������
		cf.setGzf(this.gzf);//��֤��
		cf.setTdcrj(this.tdcrj);
		cf.setHlfy(this.hlfy);//�������
		cf.setTdjcdm(this.tdjcdm);//���ؼ��δ���
		cf.setTdjcmc(this.tdjcmc);//���ؼ�������
		cf.setFwcqzbzzflxdm(this.fwcqzbzzflxdm);//���ݲ�Ȩ֤��עס�����ʹ���
		cf.setFwcqzbzzflxmc(this.fwcqzbzzflxmc);//���ݲ�Ȩ֤��עס����������
		cf.setCqzbzjzmjflSubmit(this.cqzbzjzmjflSubmit);//��Ȩ֤��ע�������Submit
		cf.setHfbzSubmit(this.hfbzSubmit);//���ֱ�׼Submit
		cf.setGrsdszsfsSubmit(this.grsdszsfsSubmit);//��������˰���շ�ʽSubmit
		cf.setYyszsfsSubmit(this.yyszsfsSubmit);//Ӫҵ˰���շ�ʽSubmit
		cf.setJsjeSubmit(this.jsjeSubmit);//��˰������Submit
		cf.setJssrqrfsSubmit(this.jssrqrfs);
		
		cf.setIsSaved(this.isSaved);
		
		cf.setMpmhdjg(this.mpmhdjg);
		cf.setCjssl(this.cjssl);
		cf.setFpszrq(this.fpszrq);//��Ʊ��������
		cf.setAnjjse(this.anjjse);//����Ӽ�����
		cf.setFwszqydm(this.fwszqydm);
		
		cf.setMfsbxxList(this.mfsbxxList);
		cf.setSjhjje(this.sjhjje);
		//added by zhangj
		cf.setFwhdlxdm(this.fwhdlxdm);//���ݺ˶�����
		cf.setQszyxsmxdm(this.qszyxsmxdm);//����Ȩ��ת����ϸ����
		cf.setQszyxsmxmc(this.qszyxsmxmc);//����Ȩ��ת����ϸ����
		cf.setYqspfwjsjg(this.yqspfwjsjg);
		cf.setYhszsfs(this.yhszsfs);
		cf.setIsQuery(this.isQuery);
		cf.setTdzsszsfsSubmit(this.tdzsszsfsSubmit);
		cf.setYhszsfsSubmit(this.yhszsfsSubmit);
		cf.setFpcxLink(this.fpcxLink);
		//modify
		cf.setFwszqymc(this.fwszqymc);//����������������
		cf.setFwszqyje(this.fwszqyje);//��ͨס��������ܼۣ�added by zhangj,2014.10.15
		
		return cf;
	}
	public String getSzlc_show() {
		return szlc_show;
	}
	public void setSzlc_show(String szlc_show) {
		this.szlc_show = szlc_show;
	}
	public String getZlc_show() {
		return zlc_show;
	}
	public void setZlc_show(String zlc_show) {
		this.zlc_show = zlc_show;
	}
	public String getSqrxzdz() {
		return sqrxzdz;
	}
	public void setSqrxzdz(String sqrxzdz) {
		this.sqrxzdz = sqrxzdz;
	}
	public String getJtwyshyhbz() {
		return jtwyshyhbz;
	}
	public void setJtwyshyhbz(String jtwyshyhbz) {
		this.jtwyshyhbz = jtwyshyhbz;
	}
	public String getFwlxdm() {
		return fwlxdm;
	}
	public void setFwlxdm(String fwlxdm) {
		this.fwlxdm = fwlxdm;
	}
	public String getJcnd() {
		return jcnd;
	}
	public void setJcnd(String jcnd) {
		this.jcnd = jcnd;
	}
	public String getZlc() {
		return zlc;
	}
	public void setZlc(String zlc) {
		this.zlc = zlc;
	}
	public String getYgffpje() {
		return ygffpje;
	}
	public void setYgffpje(String ygffpje) {
		this.ygffpje = ygffpje;
	}
	public String getGfzmrq() {
		return gfzmrq;
	}
	public void setGfzmrq(String gfzmrq) {
		this.gfzmrq = gfzmrq;
	}
	public String getTdzzssbfs() {
		return tdzzssbfs;
	}
	public void setTdzzssbfs(String tdzzssbfs) {
		this.tdzzssbfs = tdzzssbfs;
	}
	public String getQdfcqsje() {
		return qdfcqsje;
	}
	public void setQdfcqsje(String qdfcqsje) {
		this.qdfcqsje = qdfcqsje;
	}
	public String getQdfcyhsje() {
		return qdfcyhsje;
	}
	public void setQdfcyhsje(String qdfcyhsje) {
		this.qdfcyhsje = qdfcyhsje;
	}
	public String getQdtdsyqzfje() {
		return qdtdsyqzfje;
	}
	public void setQdtdsyqzfje(String qdtdsyqzfje) {
		this.qdtdsyqzfje = qdtdsyqzfje;
	}
	public String getJfpgjg() {
		return jfpgjg;
	}
	public void setJfpgjg(String jfpgjg) {
		this.jfpgjg = jfpgjg;
	}
	public String getJgpgfy() {
		return jgpgfy;
	}
	public void setJgpgfy(String jgpgfy) {
		this.jgpgfy = jgpgfy;
	}
	public String getFdczjjsjdm() {
		return fdczjjsjdm;
	}
	public void setFdczjjsjdm(String fdczjjsjdm) {
		this.fdczjjsjdm = fdczjjsjdm;
	}
	public String getFdczjswdjzh() {
		return fdczjswdjzh;
	}
	public void setFdczjswdjzh(String fdczjswdjzh) {
		this.fdczjswdjzh = fdczjswdjzh;
	}
	public String getFdczjlxdh() {
		return fdczjlxdh;
	}
	public void setFdczjlxdh(String fdczjlxdh) {
		this.fdczjlxdh = fdczjlxdh;
	}
	public String getFdczjjjr() {
		return fdczjjjr;
	}
	public void setFdczjjjr(String fdczjjjr) {
		this.fdczjjjr = fdczjjjr;
	}
	public String getFdczjjjrlxdh() {
		return fdczjjjrlxdh;
	}
	public void setFdczjjjrlxdh(String fdczjjjrlxdh) {
		this.fdczjjjrlxdh = fdczjjjrlxdh;
	}
	public String getFdczjjjrzjhm() {
		return fdczjjjrzjhm;
	}
	public void setFdczjjjrzjhm(String fdczjjjrzjhm) {
		this.fdczjjjrzjhm = fdczjjjrzjhm;
	}
	public String getFdczjjjrzgzsh() {
		return fdczjjjrzgzsh;
	}
	public void setFdczjjjrzgzsh(String fdczjjjrzgzsh) {
		this.fdczjjjrzgzsh = fdczjjjrzgzsh;
	}
	public String getCqzbzjzmjfl() {
		return cqzbzjzmjfl;
	}
	public void setCqzbzjzmjfl(String cqzbzjzmjfl) {
		this.cqzbzjzmjfl = cqzbzjzmjfl;
	}
	public String getMpmjydj() {
		return mpmjydj;
	}
	public void setMpmjydj(String mpmjydj) {
		this.mpmjydj = mpmjydj;
	}
	public String getPtzfzgxj() {
		return ptzfzgxj;
	}
	public void setPtzfzgxj(String ptzfzgxj) {
		this.ptzfzgxj = ptzfzgxj;
	}
	public String getFwrjl() {
		return fwrjl;
	}
	public void setFwrjl(String fwrjl) {
		this.fwrjl = fwrjl;
	}
	public String getHfbz() {
		return hfbz;
	}
	public void setHfbz(String hfbz) {
		this.hfbz = hfbz;
	}
	public String getZfsjsjfl() {
		return zfsjsjfl;
	}
	public void setZfsjsjfl(String zfsjsjfl) {
		this.zfsjsjfl = zfsjsjfl;
	}
	public String getYyszsfs() {
		return yyszsfs;
	}
	public void setYyszsfs(String yyszsfs) {
		this.yyszsfs = yyszsfs;
	}
	public String getGrsdszsfs() {
		return grsdszsfs;
	}
	public void setGrsdszsfs(String grsdszsfs) {
		this.grsdszsfs = grsdszsfs;
	}
	public String getTdzsszsfs() {
		return tdzsszsfs;
	}
	public void setTdzsszsfs(String tdzsszsfs) {
		this.tdzsszsfs = tdzsszsfs;
	}
	public String getJssrqrfs() {
		return jssrqrfs;
	}
	public void setJssrqrfs(String jssrqrfs) {
		this.jssrqrfs = jssrqrfs;
	}
	public String getJsje() {
		return jsje;
	}
	public void setJsje(String jsje) {
		this.jsje = jsje;
	}
	public String getZfpgjg() {
		return zfpgjg;
	}
	public void setZfpgjg(String zfpgjg) {
		this.zfpgjg = zfpgjg;
	}
	public String getZfzxfy() {
		return zfzxfy;
	}
	public void setZfzxfy(String zfzxfy) {
		this.zfzxfy = zfzxfy;
	}
	public String getZfdklx() {
		return zfdklx;
	}
	public void setZfdklx(String zfdklx) {
		this.zfdklx = zfdklx;
	}
	public String getSxf() {
		return sxf;
	}
	public void setSxf(String sxf) {
		this.sxf = sxf;
	}
	public String getGzf() {
		return gzf;
	}
	public void setGzf(String gzf) {
		this.gzf = gzf;
	}
	public String getHlfy() {
		return hlfy;
	}
	public void setHlfy(String hlfy) {
		this.hlfy = hlfy;
	}
	public String getTdjcdm() {
		return tdjcdm;
	}
	public void setTdjcdm(String tdjcdm) {
		this.tdjcdm = tdjcdm;
	}
	public String getTdjcmc() {
		return tdjcmc;
	}
	public void setTdjcmc(String tdjcmc) {
		this.tdjcmc = tdjcmc;
	}
	public String getFwcqzbzzflxdm() {
		return fwcqzbzzflxdm;
	}
	public void setFwcqzbzzflxdm(String fwcqzbzzflxdm) {
		this.fwcqzbzzflxdm = fwcqzbzzflxdm;
	}
	public String getFwcqzbzzflxmc() {
		return fwcqzbzzflxmc;
	}
	public void setFwcqzbzzflxmc(String fwcqzbzzflxmc) {
		this.fwcqzbzzflxmc = fwcqzbzzflxmc;
	}
	public String getCqzbzjzmjflSubmit() {
		return cqzbzjzmjflSubmit;
	}
	public void setCqzbzjzmjflSubmit(String cqzbzjzmjflSubmit) {
		this.cqzbzjzmjflSubmit = cqzbzjzmjflSubmit;
	}
	public String getHfbzSubmit() {
		return hfbzSubmit;
	}
	public void setHfbzSubmit(String hfbzSubmit) {
		this.hfbzSubmit = hfbzSubmit;
	}
	public String getGrsdszsfsSubmit() {
		return grsdszsfsSubmit;
	}
	public void setGrsdszsfsSubmit(String grsdszsfsSubmit) {
		this.grsdszsfsSubmit = grsdszsfsSubmit;
	}
	public String getYyszsfsSubmit() {
		return yyszsfsSubmit;
	}
	public void setYyszsfsSubmit(String yyszsfsSubmit) {
		this.yyszsfsSubmit = yyszsfsSubmit;
	}
	public String getJsjeSubmit() {
		return jsjeSubmit;
	}
	public void setJsjeSubmit(String jsjeSubmit) {
		this.jsjeSubmit = jsjeSubmit;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public String getTdcrj() {
		return tdcrj;
	}
	public void setTdcrj(String tdcrj) {
		this.tdcrj = tdcrj;
	}
	public String getMpmhdjg() {
		return mpmhdjg;
	}
	public void setMpmhdjg(String mpmhdjg) {
		this.mpmhdjg = mpmhdjg;
	}
	public String getCjssl() {
		return cjssl;
	}
	public void setCjssl(String cjssl) {
		this.cjssl = cjssl;
	}
	public String getFpszrq() {
		return fpszrq;
	}
	public void setFpszrq(String fpszrq) {
		this.fpszrq = fpszrq;
	}
	public String getAnjjse() {
		return anjjse;
	}
	public void setAnjjse(String anjjse) {
		this.anjjse = anjjse;
	}
	public String getFwszqydm() {
		return fwszqydm;
	}
	public void setFwszqydm(String fwszqydm) {
		this.fwszqydm = fwszqydm;
	}
	public String getHasMfSkzsxx() {
		return hasMfSkzsxx;
	}
	public void setHasMfSkzsxx(String hasMfSkzsxx) {
		this.hasMfSkzsxx = hasMfSkzsxx;
	}
	public String getHasMfFpdkxx() {
		return hasMfFpdkxx;
	}
	public void setHasMfFpdkxx(String hasMfFpdkxx) {
		this.hasMfFpdkxx = hasMfFpdkxx;
	}
	public ArrayList getMfsbxxList() {
		return mfsbxxList;
	}
	public void setMfsbxxList(ArrayList mfsbxxList) {
		this.mfsbxxList = mfsbxxList;
	}
	public String getSjhjje() {
		return sjhjje;
	}
	public void setSjhjje(String sjhjje) {
		this.sjhjje = sjhjje;
	}
	//added by zhangj start
	public String getFwhdlxdm() {
		return fwhdlxdm;
	}

	public void setFwhdlxdm(String fwhdlxdm) {
		this.fwhdlxdm = fwhdlxdm;
	}
	public String getQszyxsmxdm() {
		return qszyxsmxdm;
	}

	public void setQszyxsmxdm(String qszyxsmxdm) {
		this.qszyxsmxdm = qszyxsmxdm;
	}
	public String getQszyxsmxmc() {
		return qszyxsmxmc;
	}

	public void setQszyxsmxmc(String qszyxsmxmc) {
		this.qszyxsmxmc = qszyxsmxmc;
	}

	public String getYqspfwjsjg() {
		return yqspfwjsjg;
	}
	public void setYqspfwjsjg(String yqspfwjsjg) {
		this.yqspfwjsjg = yqspfwjsjg;
	}
	public String getYhszsfs() {
		return yhszsfs;
	}
	public void setYhszsfs(String yhszsfs) {
		this.yhszsfs = yhszsfs;
	}
	public boolean getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}
	public String getTdzsszsfsSubmit() {
		return tdzsszsfsSubmit;
	}
	public void setTdzsszsfsSubmit(String string) {
		this.tdzsszsfsSubmit = string;
	}
	public String getYhszsfsSubmit() {
		return yhszsfsSubmit;
	}
	public void setYhszsfsSubmit(String yhszsfsSubmit) {
		this.yhszsfsSubmit = yhszsfsSubmit;
	}
	public String getFpcxLink() {
		return fpcxLink;
	}
	public void setFpcxLink(String fpcxLink) {
		this.fpcxLink = fpcxLink;
	}


	public String getLrrq() {
		return lrrq;
	}


	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}


	public String getFwszqyjeSubmit() {
		return fwszqyjeSubmit;
	}


	public void setFwszqyjeSubmit(String fwszqyjeSubmit) {
		this.fwszqyjeSubmit = fwszqyjeSubmit;
	}


	public String getFwszqyje() {
		return fwszqyje;
	}


	public void setFwszqyje(String fwszqyje) {
		this.fwszqyje = fwszqyje;
	}
	
	//added by zhangj end	
}
