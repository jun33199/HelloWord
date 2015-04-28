package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;


import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;

/**
 * 存量房税务机关核定信息录入 Form
 * @author 
 *
 */
public class ClfswjghdxxlrForm extends ClfxxcjForm {
	private static final long serialVersionUID = 1L;
	
	private String isSaved ="false";//核定信息已经被保存
	private String hasMfSkzsxx = "false";//该核定有卖方税款征收信息
	private String hasMfFpdkxx = "false";//该核定有卖方发票代开信息
	
	private String szlc_show;
	private String zlc_show;
	private String sqrxzdz; //申请人现住址 
	private String jtwyshyhbz; //是否为家庭唯一生活用房
	private String fwlxdm; //房屋类型
	private String jcnd; //建成年代
	private String zlc;//总楼层
	private String ygffpje ="0.00";//原购房发票金额
	private String gfzmrq; //购房证明日期
	private String tdzzssbfs; //土地增值税申报方式
	private String qdfcqsje; //取得房地产时所缴纳的契税金额
	private String qdfcyhsje; //取得房地产时所缴纳的印花税金额
	private String qdtdsyqzfje; //取得土地使用权所支付的金额
	private String jfpgjg; //旧房及建筑物的评估价格
	private String jgpgfy; //价格评估费用
	private String fdczjjsjdm; //地税计算机代码
	private String fdczjswdjzh; //地税税务登记号码
	private String fdczjlxdh; //房地产中介联系电话
	private String fdczjjjr; //房地产经纪人姓名
	private String fdczjjjrlxdh; //房地产经纪人联系电话
	private String fdczjjjrzjhm; //房地产经纪人身份证号码
	private String fdczjjjrzgzsh; //经纪人资格证书号码
	private String cqzbzjzmjfl; //产权证标注建筑面积
	private String mpmjydj ="0.00"; //每平米交易单价
	private String ptzfzgxj; //普通住房最高限价
	private String fwrjl; //房屋容积率
	private String hfbz; //划分标准
	private String zfsjsjfl; //住房使用时间
	private String yyszsfs; //营业税征收方式
	private String grsdszsfs; //个人所得税征收方式
	private String tdzsszsfs; //土地增值税征收方式
	private String jssrqrfs; //计税收入确认方式
	private String jsje; //计税收入金额
	//private String ygffpje; //原购房发票金额
	private String zfpgjg; //住房评估价格
	//private String jfzjwpgje; //其中:旧房及建筑物评估价格
	private String zfzxfy; //住房装修费用
	private String zfdklx; //住房贷款利息
	private String sxf; //手续费
	private String gzf; //公证费
	private String tdcrj;//new add 土地出让金
	private String hlfy ="0.00"; //合理费用
	private String tdjcdm; //土地级次代码
	private String tdjcmc; //土地级次名称
	private String fwcqzbzzflxdm; //房屋产权证标注住房类型代码
	private String fwcqzbzzflxmc; //房屋产权证标注住房类型名称
	
	//new add
	//private String mpmhdjg ="0.00";//每平米核定价格
	private String mpmhdjg;//每平米核定价格
	private String cjssl ;//城建税税率
	private String fpszrq;//发票所载日期
	private String anjjse;//按年加计数额
	private String fwszqydm;//房屋所在区域代码
	// new  modify 
	private String fwszqymc;//房屋所在区域名称
	public String getFwszqymc() {
		return fwszqymc;
	}


	public void setFwszqymc(String fwszqymc) {
		this.fwszqymc = fwszqymc;
	}
	//end 
	

	//打印时用
	private ArrayList mfsbxxList = new ArrayList();//卖方申报信息
	private String sjhjje="";//实缴合计金额

	
	//added by zhangj
	private String fwhdlxdm;//房屋核定类型代码0：住房，1：非住房
	private String qszyxsmxdm; //房屋权属转移明细代码
	private String qszyxsmxmc; //房屋权属转移明细名称
	private String yqspfwjsjg; //原契税票房屋计税价格 
	private String yhszsfs; //印花税征收方式
	private boolean isQuery=true;//判断操作是查询还是切换房屋核定类型
	private String tdzsszsfsSubmit;
	private String yhszsfsSubmit;
	private String fpcxLink;
    /**
     * 房屋权属转移明细代码表
     */
    private ArrayList qszyxsmxList = new ArrayList();
    private String fwszqyjeSubmit;
    private String fwszqyje;
	/**
	 * 按钮提交域
	 */
	private String cqzbzjzmjflSubmit;//产权证标注建筑面积Submit
	private String hfbzSubmit;//划分标准Submit
	private String grsdszsfsSubmit;//个人所得税征收方式Submit
	private String yyszsfsSubmit;//营业税征收方式Submit
	private String jsjeSubmit="0.00";//计税收入金额Submit
	private String htbhSubmit;//合同编号Submit
	private String sbbhSubmit;//申报编号Submit
	private String ptzfzgxjSubmit;//普通住房最高限价Submit
	private String jssrqrfsSubmit;//计税方式Submit
	
	
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
		bvo.setTdcrj(this.getTdcrj());//new add土地出让金
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
		
		bvo.setFwjzmj(this.getFwjzmj());//房屋建筑面积
		
		//
		bvo.setMpmhdjg(this.mpmhdjg);//每平米核定价格
		bvo.setCjssl(this.cjssl);
		bvo.setFpszrq(this.fpszrq);//发票所载日期
		bvo.setAnjjse(this.anjjse);//按年加计数额
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
		 this.setBbbs(cf.getBbbs());//版本标示
		 this.setHtbh(cf.getHtbh());//合同编号
		 this.setHtwsqyrq(cf.getHtwsqyrq());//合同网上签约日期
		 this.setFwzlqx(cf.getFwzlqx());//房屋坐落区县
		 this.setFwzldz(cf.getFwzldz());//房屋坐落地址
		 this.setFwqszylx(cf.getFwqszylx());//房屋权属转移类型_代码
		 this.setFwqszylxmc(cf.getFwqszylxmc());//房屋权属转移类型_名称
		 this.setSfwscsssggf(cf.getSfwscsssggf());//是否为首次上市已购公房_代码
		 this.setSfwscsssggfmc(cf.getSfwscsssggfmc());//是否为首次上市已购公房_名称
		 this.setFwcqzh(cf.getFwcqzh());//房屋产权证号
		 this.setFwsyqztfrq(cf.getFwsyqztfrq());//房屋所有权证填发日期
		 this.setFwjzmj(cf.getFwjzmj());//房屋建筑面积
		 this.setJzjgdm(cf.getJzjgdm());//建筑结构代码
		 this.setJzjgmc(cf.getJzjgmc());//建筑结构名称
		 this.setGhyt(cf.getGhyt());//规划用途
		 this.setHtzj(cf.getHtzj());//合同总价
		 this.setBzdm(cf.getBzdm());//币种代码
		 this.setBzmc(cf.getBzmc());//币种名称
		 this.setHl(cf.getHl());//汇率
		 this.setWbje(cf.getWbje());//外币金额
		 this.setSzlc(cf.getSzlc());//所在楼层
		 this.setFdczjjgmc(cf.getFdczjjgmc());//房地产中介机构名称
		 this.setAll_sellerInfo(cf.getAll_sellerInfo());
		 this.setAll_buyerInfo(cf.getAll_buyerInfo());
		 this.setUNEpiccode(cf.getUNEpiccode());
	 }
	 
	 public void reSet(){
		 this.isSaved ="false";//核定信息已经被保存
		 this.hasMfSkzsxx = "false";//该核定有卖方税款征收信息
		 this.hasMfFpdkxx = "false";//该核定有卖方发票代开信息
		 
		 this.szlc_show="";
		 this.zlc_show="";	
		 this.sqrxzdz=""; //申请人现住址 
		 this.jtwyshyhbz=""; //是否为家庭唯一生活用房
		 this.fwlxdm=""; //房屋类型
		 this.jcnd=""; //建成年代
		 this.zlc="";//总楼层
		 this.ygffpje ="0.00";//原购房发票金额
		 this.gfzmrq=""; //购房证明日期
		 this.tdzzssbfs=""; //土地增值税申报方式
		 this.qdfcqsje=""; //取得房地产时所缴纳的契税金额
		 this.qdfcyhsje=""; //取得房地产时所缴纳的印花税金额
		 this.qdtdsyqzfje=""; //取得土地使用权所支付的金额
		 this.jfpgjg=""; //旧房及建筑物的评估价格
		 this.jgpgfy=""; //价格评估费用
		 this.fdczjjsjdm=""; //地税计算机代码
		 this.fdczjswdjzh=""; //地税税务登记号码
		 this.fdczjlxdh=""; //房地产中介联系电话
		 this.fdczjjjr=""; //房地产经纪人姓名
		 this.fdczjjjrlxdh=""; //房地产经纪人联系电话
		 this.fdczjjjrzjhm=""; //房地产经纪人身份证号码
		 this.fdczjjjrzgzsh=""; //经纪人资格证书号码
		 this.cqzbzjzmjfl=""; //产权证标注建筑面积
		 this.mpmjydj ="0.00"; //每平米交易单价
		 this.ptzfzgxj=""; //普通住房最高限价
		 this.fwrjl=""; //房屋容积率
		 this.hfbz=""; //划分标准
		 this.zfsjsjfl=""; //住房使用时间
		 this.yyszsfs=""; //营业税征收方式
		 this.grsdszsfs=""; //个人所得税征收方式
		 this.tdzsszsfs=""; //土地增值税征收方式
		 this.jssrqrfs=""; //计税收入确认方式
		 this.jsje=""; //计税收入金额
		 this.zfpgjg=""; //住房评估价格
		 this.zfzxfy=""; //住房装修费用
		 this.zfdklx=""; //住房贷款利息
		 this.sxf=""; //手续费
		 this.gzf=""; //公证费
		 this.tdcrj="";//土地出让金
		 this.hlfy ="0.00"; //合理费用
		 this.tdjcdm=""; //土地级次代码
		 this.tdjcmc=""; //土地级次名称
		 this.fwcqzbzzflxdm=""; //房屋产权证标注住房类型代码
		 this.fwcqzbzzflxmc=""; //房屋产权证标注住房类型名称
		 
		 this.mpmhdjg="0.00";//每平米核定价格
		 this.cjssl="";
		 this.fpszrq="";//发票所载日期
	     this.anjjse="";//按年加计数额		
	     this.fwszqydm="";
	     //added by zhangj
	     this.fwhdlxdm=""; //房屋核定类型
		 this.qszyxsmxdm=""; //房屋权属转移明细代码
		 this.qszyxsmxmc=""; //房屋权属转移明细名称
		 this.yqspfwjsjg=""; //
		 this.yhszsfs=""; //
		 this.isQuery=true;
		 this.tdzsszsfsSubmit="";
		 this.yhszsfsSubmit="";
		 this.fpcxLink="";
		 	/**
		 	 * 按钮提交域
		 	 */
		 this.cqzbzjzmjflSubmit="";//产权证标注建筑面积Submit
		 this.hfbzSubmit="";//划分标准Submit
		 this.grsdszsfsSubmit="";//个人所得税征收方式Submit
		 this.yyszsfsSubmit="";//营业税征收方式Submit
		 this.jsjeSubmit="0.00";//计税收入金额Submit
		 this.htbhSubmit="";//合同编号Submit
		 this.sbbhSubmit="";//申报编号Submit		
		 this.ptzfzgxjSubmit="";
		 this.jssrqrfsSubmit="";
		 
		 
		 this.sjhjje = "";
		 this.mfsbxxList = new ArrayList();
		 //modify
		 this.fwszqymc ="";
		 
		 //重值采集信息
		 this.clear();
	 }
	 
	 
	
    /**
     * 录入人
     */
    private String lrr;

    /**
     * 录入日期
     */
    private String lrrq;
	
	
	/**
     * 土地级次列表
     */
    private ArrayList tdjcList = new ArrayList();
    
    /**
     * 房屋产权证标注住房类型代码表
     */
    private ArrayList fwcqzbzzflxList = new ArrayList();
    
    
    /**
     * 
     * 房屋所载区域
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
