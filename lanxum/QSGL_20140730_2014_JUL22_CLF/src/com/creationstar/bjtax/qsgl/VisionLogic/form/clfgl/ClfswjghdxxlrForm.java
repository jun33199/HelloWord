package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;


import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;

/**
 * ������˰����غ˶���Ϣ¼�� Form
 * @author 
 *
 */
public class ClfswjghdxxlrForm extends ClfxxcjForm {
	private static final long serialVersionUID = 1L;
	
	private String isSaved ="false";//�˶���Ϣ�Ѿ�������
	private String hasMfSkzsxx = "false";//�ú˶�������˰��������Ϣ
	private String hasMfFpdkxx = "false";//�ú˶���������Ʊ������Ϣ
	
	private String szlc_show;
	private String zlc_show;
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
	private String tdcrj;//new add ���س��ý�
	private String hlfy ="0.00"; //�������
	private String tdjcdm; //���ؼ��δ���
	private String tdjcmc; //���ؼ�������
	private String fwcqzbzzflxdm; //���ݲ�Ȩ֤��עס�����ʹ���
	private String fwcqzbzzflxmc; //���ݲ�Ȩ֤��עס����������
	
	//new add
	//private String mpmhdjg ="0.00";//ÿƽ�׺˶��۸�
	private String mpmhdjg;//ÿƽ�׺˶��۸�
	private String cjssl ;//�ǽ�˰˰��
	private String fpszrq;//��Ʊ��������
	private String anjjse;//����Ӽ�����
	private String fwszqydm;//���������������
	// new  modify 
	private String fwszqymc;//����������������
	public String getFwszqymc() {
		return fwszqymc;
	}


	public void setFwszqymc(String fwszqymc) {
		this.fwszqymc = fwszqymc;
	}
	//end 
	

	//��ӡʱ��
	private ArrayList mfsbxxList = new ArrayList();//�����걨��Ϣ
	private String sjhjje="";//ʵ�ɺϼƽ��

	
	//added by zhangj
	private String fwhdlxdm;//���ݺ˶����ʹ���0��ס����1����ס��
	private String qszyxsmxdm; //����Ȩ��ת����ϸ����
	private String qszyxsmxmc; //����Ȩ��ת����ϸ����
	private String yqspfwjsjg; //ԭ��˰Ʊ���ݼ�˰�۸� 
	private String yhszsfs; //ӡ��˰���շ�ʽ
	private boolean isQuery=true;//�жϲ����ǲ�ѯ�����л����ݺ˶�����
	private String tdzsszsfsSubmit;
	private String yhszsfsSubmit;
	private String fpcxLink;
    /**
     * ����Ȩ��ת����ϸ�����
     */
    private ArrayList qszyxsmxList = new ArrayList();
    private String fwszqyjeSubmit;
    private String fwszqyje;
	/**
	 * ��ť�ύ��
	 */
	private String cqzbzjzmjflSubmit;//��Ȩ֤��ע�������Submit
	private String hfbzSubmit;//���ֱ�׼Submit
	private String grsdszsfsSubmit;//��������˰���շ�ʽSubmit
	private String yyszsfsSubmit;//Ӫҵ˰���շ�ʽSubmit
	private String jsjeSubmit="0.00";//��˰������Submit
	private String htbhSubmit;//��ͬ���Submit
	private String sbbhSubmit;//�걨���Submit
	private String ptzfzgxjSubmit;//��ͨס������޼�Submit
	private String jssrqrfsSubmit;//��˰��ʽSubmit
	
	
	public Object getData() {
		ClfswjghdxxlrBo bvo = new ClfswjghdxxlrBo();	
		bvo.setHtbh(this.getHtbh());
		bvo.setSbbh(this.getSbbh());
		bvo.setSqrxzdz(this.getSqrxzdz());
		bvo.setJtwyshyhbz(this.getJtwyshyhbz());
		bvo.setFwlxdm(this.getFwlxdm());
		bvo.setJcnd(this.getJcnd());
		bvo.setZlc(this.getZlc());
		bvo.setYgffpje(this.getYgffpje());
		bvo.setGfzmrq(this.getGfzmrq());
		bvo.setTdzzssbfs(this.getTdzzssbfs());
		bvo.setQdfcqsje(this.getQdfcqsje());
		bvo.setQdfcyhsje(this.getQdfcyhsje());
		bvo.setQdtdsyqzfje(this.getQdtdsyqzfje());
		bvo.setJfpgjg(this.getJfpgjg());
		bvo.setJgpgfy(this.getJgpgfy());
		bvo.setFdczjjsjdm(this.getFdczjjsjdm());
		bvo.setFdczjswdjzh(this.getFdczjswdjzh());
		bvo.setFdczjlxdh(this.getFdczjlxdh());
		bvo.setFdczjjjr(this.getFdczjjjr());
		bvo.setFdczjjjrlxdh(this.getFdczjjjrlxdh());
		bvo.setFdczjjjrzjhm(this.getFdczjjjrzjhm());
		bvo.setFdczjjjrzgzsh(this.getFdczjjjrzgzsh());
		bvo.setCqzbzjzmjfl(this.getCqzbzjzmjfl());
		bvo.setMpmjydj(this.getMpmjydj());
		bvo.setPtzfzgxj(this.getPtzfzgxjSubmit());
		bvo.setFwrjl(this.getFwrjl());
		bvo.setHfbz(this.getHfbz());
		bvo.setZfsjsjfl(this.getZfsjsjfl());
		bvo.setYyszsfs(this.getYyszsfs());
		bvo.setGrsdszsfs(this.getGrsdszsfs());
		bvo.setTdzsszsfs(this.getTdzsszsfs());
		bvo.setJssrqrfs(this.getJssrqrfsSubmit());
		bvo.setJsje(this.getJsjeSubmit());
		bvo.setZfpgjg(this.getZfpgjg());
		bvo.setZfzxfy(this.getZfzxfy());
		bvo.setZfdklx(this.getZfdklx());
		bvo.setSxf(this.getSxf());
		bvo.setGzf(this.getGzf());
		bvo.setTdcrj(this.getTdcrj());//new add���س��ý�
		bvo.setHlfy(this.getHlfy());
		bvo.setTdjcdm(this.getTdjcdm());
		bvo.setTdjcmc(this.getTdjcmc());
		bvo.setFwcqzbzzflxdm(this.getFwcqzbzzflxdm());
		bvo.setFwcqzbzzflxmc(this.getFwcqzbzzflxmc());
		bvo.setCqzbzjzmjflSubmit(this.getCqzbzjzmjflSubmit());
		bvo.setHfbzSubmit(this.getHfbzSubmit());
		bvo.setGrsdszsfsSubmit(this.getGrsdszsfsSubmit());
		bvo.setYyszsfsSubmit(this.getYyszsfsSubmit());
		bvo.setJsjeSubmit(this.getJsjeSubmit());
/*		bvo.setSbbhSubmit(this.getSbbhSubmit());
		bvo.setHtbhSubmit(this.getHtbhSubmit());*/
		
		bvo.setFwjzmj(this.getFwjzmj());//���ݽ������
		
		//
		bvo.setMpmhdjg(this.mpmhdjg);//ÿƽ�׺˶��۸�
		bvo.setCjssl(this.cjssl);
		bvo.setFpszrq(this.fpszrq);//��Ʊ��������
		bvo.setAnjjse(this.anjjse);//����Ӽ�����
		bvo.setFwszqydm(this.fwszqydm);
		//added by zhangj start
		bvo.setFwhdlxdm(this.fwhdlxdm);
		bvo.setQszyxsmxdm(this.getQszyxsmxdm());
		bvo.setQszyxsmxmc(this.getQszyxsmxmc());
		bvo.setYqspfwjsjg(this.yqspfwjsjg);
		bvo.setYhszsfs(this.yhszsfs);
		bvo.setIsQuery(this.isQuery);
		bvo.setTdzsszsfsSubmit(this.getTdzsszsfsSubmit());
		bvo.setYhszsfsSubmit(this.getYhszsfsSubmit());
		bvo.setFpcxLink(this.getFpcxLink());
		//modify
		bvo.setFwszqymc(this.fwszqymc);
		bvo.setFwszqyje(this.getFwszqyjeSubmit());
		//added by zhangj end
		return bvo;
	}
	
	
	 public void setFormData(ClfxxcjForm cf){
		 this.setKeyStr(cf.getKeyStr());
		 this.setSbbh(cf.getSbbh());
		 this.setBbbs(cf.getBbbs());//�汾��ʾ
		 this.setHtbh(cf.getHtbh());//��ͬ���
		 this.setHtwsqyrq(cf.getHtwsqyrq());//��ͬ����ǩԼ����
		 this.setFwzlqx(cf.getFwzlqx());//������������
		 this.setFwzldz(cf.getFwzldz());//���������ַ
		 this.setFwqszylx(cf.getFwqszylx());//����Ȩ��ת������_����
		 this.setFwqszylxmc(cf.getFwqszylxmc());//����Ȩ��ת������_����
		 this.setSfwscsssggf(cf.getSfwscsssggf());//�Ƿ�Ϊ�״������ѹ�����_����
		 this.setSfwscsssggfmc(cf.getSfwscsssggfmc());//�Ƿ�Ϊ�״������ѹ�����_����
		 this.setFwcqzh(cf.getFwcqzh());//���ݲ�Ȩ֤��
		 this.setFwsyqztfrq(cf.getFwsyqztfrq());//��������Ȩ֤�����
		 this.setFwjzmj(cf.getFwjzmj());//���ݽ������
		 this.setJzjgdm(cf.getJzjgdm());//�����ṹ����
		 this.setJzjgmc(cf.getJzjgmc());//�����ṹ����
		 this.setGhyt(cf.getGhyt());//�滮��;
		 this.setHtzj(cf.getHtzj());//��ͬ�ܼ�
		 this.setBzdm(cf.getBzdm());//���ִ���
		 this.setBzmc(cf.getBzmc());//��������
		 this.setHl(cf.getHl());//����
		 this.setWbje(cf.getWbje());//��ҽ��
		 this.setSzlc(cf.getSzlc());//����¥��
		 this.setFdczjjgmc(cf.getFdczjjgmc());//���ز��н��������
		 this.setAll_sellerInfo(cf.getAll_sellerInfo());
		 this.setAll_buyerInfo(cf.getAll_buyerInfo());
		 this.setUNEpiccode(cf.getUNEpiccode());
	 }
	 
	 public void reSet(){
		 this.isSaved ="false";//�˶���Ϣ�Ѿ�������
		 this.hasMfSkzsxx = "false";//�ú˶�������˰��������Ϣ
		 this.hasMfFpdkxx = "false";//�ú˶���������Ʊ������Ϣ
		 
		 this.szlc_show="";
		 this.zlc_show="";	
		 this.sqrxzdz=""; //��������סַ 
		 this.jtwyshyhbz=""; //�Ƿ�Ϊ��ͥΨһ�����÷�
		 this.fwlxdm=""; //��������
		 this.jcnd=""; //�������
		 this.zlc="";//��¥��
		 this.ygffpje ="0.00";//ԭ������Ʊ���
		 this.gfzmrq=""; //����֤������
		 this.tdzzssbfs=""; //������ֵ˰�걨��ʽ
		 this.qdfcqsje=""; //ȡ�÷��ز�ʱ�����ɵ���˰���
		 this.qdfcyhsje=""; //ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���
		 this.qdtdsyqzfje=""; //ȡ������ʹ��Ȩ��֧���Ľ��
		 this.jfpgjg=""; //�ɷ���������������۸�
		 this.jgpgfy=""; //�۸���������
		 this.fdczjjsjdm=""; //��˰���������
		 this.fdczjswdjzh=""; //��˰˰��ǼǺ���
		 this.fdczjlxdh=""; //���ز��н���ϵ�绰
		 this.fdczjjjr=""; //���ز�����������
		 this.fdczjjjrlxdh=""; //���ز���������ϵ�绰
		 this.fdczjjjrzjhm=""; //���ز����������֤����
		 this.fdczjjjrzgzsh=""; //�������ʸ�֤�����
		 this.cqzbzjzmjfl=""; //��Ȩ֤��ע�������
		 this.mpmjydj ="0.00"; //ÿƽ�׽��׵���
		 this.ptzfzgxj=""; //��ͨס������޼�
		 this.fwrjl=""; //�����ݻ���
		 this.hfbz=""; //���ֱ�׼
		 this.zfsjsjfl=""; //ס��ʹ��ʱ��
		 this.yyszsfs=""; //Ӫҵ˰���շ�ʽ
		 this.grsdszsfs=""; //��������˰���շ�ʽ
		 this.tdzsszsfs=""; //������ֵ˰���շ�ʽ
		 this.jssrqrfs=""; //��˰����ȷ�Ϸ�ʽ
		 this.jsje=""; //��˰������
		 this.zfpgjg=""; //ס�������۸�
		 this.zfzxfy=""; //ס��װ�޷���
		 this.zfdklx=""; //ס��������Ϣ
		 this.sxf=""; //������
		 this.gzf=""; //��֤��
		 this.tdcrj="";//���س��ý�
		 this.hlfy ="0.00"; //�������
		 this.tdjcdm=""; //���ؼ��δ���
		 this.tdjcmc=""; //���ؼ�������
		 this.fwcqzbzzflxdm=""; //���ݲ�Ȩ֤��עס�����ʹ���
		 this.fwcqzbzzflxmc=""; //���ݲ�Ȩ֤��עס����������
		 
		 this.mpmhdjg="0.00";//ÿƽ�׺˶��۸�
		 this.cjssl="";
		 this.fpszrq="";//��Ʊ��������
	     this.anjjse="";//����Ӽ�����		
	     this.fwszqydm="";
	     //added by zhangj
	     this.fwhdlxdm=""; //���ݺ˶�����
		 this.qszyxsmxdm=""; //����Ȩ��ת����ϸ����
		 this.qszyxsmxmc=""; //����Ȩ��ת����ϸ����
		 this.yqspfwjsjg=""; //
		 this.yhszsfs=""; //
		 this.isQuery=true;
		 this.tdzsszsfsSubmit="";
		 this.yhszsfsSubmit="";
		 this.fpcxLink="";
		 	/**
		 	 * ��ť�ύ��
		 	 */
		 this.cqzbzjzmjflSubmit="";//��Ȩ֤��ע�������Submit
		 this.hfbzSubmit="";//���ֱ�׼Submit
		 this.grsdszsfsSubmit="";//��������˰���շ�ʽSubmit
		 this.yyszsfsSubmit="";//Ӫҵ˰���շ�ʽSubmit
		 this.jsjeSubmit="0.00";//��˰������Submit
		 this.htbhSubmit="";//��ͬ���Submit
		 this.sbbhSubmit="";//�걨���Submit		
		 this.ptzfzgxjSubmit="";
		 this.jssrqrfsSubmit="";
		 
		 
		 this.sjhjje = "";
		 this.mfsbxxList = new ArrayList();
		 //modify
		 this.fwszqymc ="";
		 
		 //��ֵ�ɼ���Ϣ
		 this.clear();
	 }
	 
	 
	
    /**
     * ¼����
     */
    private String lrr;

    /**
     * ¼������
     */
    private String lrrq;
	
	
	/**
     * ���ؼ����б�
     */
    private ArrayList tdjcList = new ArrayList();
    
    /**
     * ���ݲ�Ȩ֤��עס�����ʹ����
     */
    private ArrayList fwcqzbzzflxList = new ArrayList();
    
    
    /**
     * 
     * ������������
     */
    private ArrayList fwszqyList = new ArrayList();
	
	

	public ArrayList getFwcqzbzzflxList() {
		return fwcqzbzzflxList;
	}

	public void setFwcqzbzzflxList(ArrayList fwcqzbzzflxList) {
		this.fwcqzbzzflxList = fwcqzbzzflxList;
	}

	public ArrayList getTdjcList() {
		return tdjcList;
	}

	public void setTdjcList(ArrayList tdjcList) {
		this.tdjcList = tdjcList;
	}

	public String getSqrxzdz() {
		return sqrxzdz;
	}

	public void setSqrxzdz(String sqrxzdz) {
		this.sqrxzdz = sqrxzdz;
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

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
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

	public String getJsjeSubmit() {
		return jsjeSubmit;
	}

	public void setJsjeSubmit(String jsjeSubmit) {
		this.jsjeSubmit = jsjeSubmit;
	}

	public String getHtbhSubmit() {
		return htbhSubmit;
	}

	public void setHtbhSubmit(String htbhSubmit) {
		this.htbhSubmit = htbhSubmit;
	}

	public String getSbbhSubmit() {
		return sbbhSubmit;
	}

	public void setSbbhSubmit(String sbbhSubmit) {
		this.sbbhSubmit = sbbhSubmit;
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


	public ArrayList getFwszqyList() {
		return fwszqyList;
	}


	public void setFwszqyList(ArrayList fwszqyList) {
		this.fwszqyList = fwszqyList;
	}


	public String getPtzfzgxjSubmit() {
		return ptzfzgxjSubmit;
	}


	public void setPtzfzgxjSubmit(String ptzfzgxjSubmit) {
		this.ptzfzgxjSubmit = ptzfzgxjSubmit;
	}


	public String getJssrqrfsSubmit() {
		return jssrqrfsSubmit;
	}


	public void setJssrqrfsSubmit(String jssrqrfsSubmit) {
		this.jssrqrfsSubmit = jssrqrfsSubmit;
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
	public ArrayList getQszyxsmxList() {
		return qszyxsmxList;
	}

	public void setQszyxsmxList(ArrayList qszyxsmxList) {
		this.qszyxsmxList = qszyxsmxList;
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


	public void setTdzsszsfsSubmit(String tdzsszsfsSubmit) {
		this.tdzsszsfsSubmit = tdzsszsfsSubmit;
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
