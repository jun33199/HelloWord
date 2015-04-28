package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;

/**
 * 存量房税务机关核定信息录入 BO
 * @author 
 *
 */
public class ClfswjghdxxlrBo extends ClfxxcjBo {
	private String isSaved ="false";//核定信息已经被保存
	private String hasMfSkzsxx = "false";//该核定有卖方税款征收信息
	private String hasMfFpdkxx = "false";//该核定有卖方发票代开信息
	
	
	public String szlc_show;
	public String zlc_show;
	
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
	private String tdcrj;// new add 土地出让金
	private String hlfy ="0.00"; //合理费用
	private String tdjcdm; //土地级次代码
	private String tdjcmc; //土地级次名称
	private String fwcqzbzzflxdm; //房屋产权证标注住房类型代码
	private String fwcqzbzzflxmc; //房屋产权证标注住房类型名称
	
	//new add
	private String mpmhdjg="0.00";//每平米核定价格
	private String cjssl;//城建税税率
	private String fpszrq;//发票所载日期
	private String anjjse;//按年加计数额
	private String fwszqydm;//房屋所在区域代码
	
	//added by zhangj
	private String fwhdlxdm;//房屋核定类型代码0：住房，1：非住房
	private String qszyxsmxdm; //房屋权属转移明细代码
	private String qszyxsmxmc; //房屋权属转移明细名称
	private String yqspfwjsjg; //原契税票房屋计税价格 
	private String yhszsfs; //印花税征收方式
	private boolean isQuery;//判断操作是查询还是切换房屋核定类型
	private String tdzsszsfsSubmit;//
	private String yhszsfsSubmit;//
	private String fpcxLink;//
	private String lrrq;
	private String fwszqyjeSubmit;
	private String fwszqyje;
	// new  modify  by yugw
		private String fwszqymc;//房屋所在区域名称
		public String getFwszqymc() {
			return fwszqymc;
		}


		public void setFwszqymc(String fwszqymc) {
			this.fwszqymc = fwszqymc;
		}
		//end 
		
	/**
	 * 按钮提交域
	 */
	private String cqzbzjzmjflSubmit;//产权证标注建筑面积Submit
	private String hfbzSubmit;//划分标准Submit
	private String grsdszsfsSubmit;//个人所得税征收方式Submit
	private String yyszsfsSubmit;//营业税征收方式Submit
	private String jsjeSubmit;//计税收入金额Submit
/*	private String htbhSubmit;//合同编号Submit
	private String sbbhSubmit;//申报编号Submit
*/	
	
	// 打印用
	private ArrayList mfsbxxList = new ArrayList();
	private String sjhjje="";//实缴合计金额
	
	
	/**
	 * @methodName:getFromData1
	 * @function:
	 * 
	 * @return
	 * @author:唐昌富
	 * @create date：2013-5-16 下午03:55:00
	 * @version 1.1
	 * 
	 * 
	 */
	public Object getFromData() {
		ClfswjghdxxlrForm cf= new ClfswjghdxxlrForm();
		//==============存量房采集信息=================
		cf.setHasMAuthorise(this.hasMAuthorise);
		cf.setSaveIsSuccess(this.SaveIsSuccess);
		cf.setHasMfSkzsxx(this.hasMfSkzsxx);//该核定有卖方税款征收信息
		cf.setHasMfFpdkxx(this.hasMfFpdkxx);//该核定有卖方发票代开信息
		 
		cf.setKeyStr(this.keyStr);
		cf.setSbbh(this.sbbh);
		cf.setBbbs(this.bbbs);// 版本标示
		cf.setHtbh(this.htbh);// 合同编号
		cf.setHtwsqyrq(this.htwsqyrq);// 合同网上签约日期
		cf.setFwzlqx(this.fwzlqx);// 房屋坐落区县
		cf.setFwzldz(this.fwzldz);// 房屋坐落地址
		cf.setFwqszylx(this.fwqszylx);// 房屋权属转移类型_代码
		cf.setFwqszylxmc(this.fwqszylxmc);// 房屋权属转移类型_名称
		cf.setSfwscsssggf(this.sfwscsssggf);// 是否为首次上市已购公房_代码
		cf.setSfwscsssggfmc(this.sfwscsssggfmc);// 是否为首次上市已购公房_名称
		cf.setFwcqzh(this.fwcqzh);// 房屋产权证号
		cf.setFwsyqztfrq(this.fwsyqztfrq);// 房屋所有权证填发日期
		cf.setFwjzmj(this.fwjzmj);// 房屋建筑面积
		cf.setJzjgdm(this.jzjgdm);// 建筑结构代码
		cf.setJzjgmc(this.jzjgmc);// 建筑结构名称
		cf.setGhyt(this.ghyt);// 规划用途
		cf.setHtzj(this.htzj);// 合同总价
		cf.setBzdm(this.bzdm);// 币种代码
		cf.setBzmc(this.bzmc);// 币种名称
		cf.setHl(this.hl);// 汇率
		cf.setWbje(this.wbje);// 外币金额
		cf.setSzlc(this.szlc);// 所在楼层
		cf.setFdczjjgmc(this.fdczjjgmc);// 房地产中介机构名称
		cf.setZlc_show(this.zlc_show);//
		cf.setSzlc_show(this.szlc_show);
		cf.setAll_sellerInfo(this.all_sellerInfo);
		cf.setAll_buyerInfo(this.all_buyerInfo);
		cf.setUNEpiccode(this.UNEpiccode);
		
		//===============核定信息======================
		cf.setSzlc_show(this.szlc_show);
		cf.setZlc_show(this.zlc_show);
		cf.setSqrxzdz(this.sqrxzdz);//申请人现住址
		cf.setJtwyshyhbz(this.jtwyshyhbz);//是否为家庭唯一生活用房
		cf.setFwlxdm(this.fwlxdm);//房屋类型
		cf.setJcnd(this.jcnd);//建成年代
		cf.setZlc(this.zlc);//总楼层
		cf.setYgffpje(this.ygffpje);//原购房发票金额
		cf.setGfzmrq(this.gfzmrq);//购房证明日期
		cf.setTdzzssbfs(this.tdzzssbfs);//土地增值税申报方式
		cf.setQdfcqsje(this.qdfcqsje);//取得房地产时所缴纳的契税金额
		cf.setQdfcyhsje(this.qdfcyhsje);//取得房地产时所缴纳的印花税金额
		cf.setQdtdsyqzfje(this.qdtdsyqzfje);//取得土地使用权所支付的金额
		cf.setJfpgjg(this.jfpgjg);//旧房及建筑物的评估价格
		cf.setJgpgfy(this.jgpgfy);//价格评估费用
		cf.setFdczjjsjdm(this.fdczjjsjdm);//地税计算机代码
		cf.setFdczjswdjzh(this.fdczjswdjzh);//地税税务登记号码
		cf.setFdczjlxdh(this.fdczjlxdh);//房地产中介联系电话
		cf.setFdczjjjr(this.fdczjjjr);//房地产经纪人姓名
		cf.setFdczjjjrlxdh(this.fdczjjjrlxdh);//房地产经纪人联系电话
		cf.setFdczjjjrzjhm(this.fdczjjjrzjhm);//房地产经纪人身份证号码
		cf.setFdczjjjrzgzsh(this.fdczjjjrzgzsh);//经纪人资格证书号码
		cf.setCqzbzjzmjfl(this.cqzbzjzmjfl);//产权证标注建筑面积
		cf.setMpmjydj(this.mpmjydj);//每平米交易单价
		cf.setPtzfzgxj(this.ptzfzgxj);//普通住房最高限价
		cf.setFwrjl(this.fwrjl);//房屋容积率
		cf.setHfbz(this.hfbz);//划分标准
		cf.setZfsjsjfl(this.zfsjsjfl);//住房使用时间
		cf.setYyszsfs(this.yyszsfs);//营业税征收方式
		cf.setGrsdszsfs(this.grsdszsfs);//个人所得税征收方式
		cf.setTdzsszsfs(this.tdzsszsfs);//土地增值税征收方式
		cf.setJssrqrfs(this.jssrqrfs);//计税收入确认方式
		cf.setJsje(this.jsje);//计税收入金额
		cf.setZfpgjg(this.zfpgjg);//住房评估价格
		cf.setZfzxfy(this.zfzxfy);//住房装修费用
		cf.setZfdklx(this.zfdklx);//住房贷款利息
		cf.setSxf(this.sxf);//手续费
		cf.setGzf(this.gzf);//公证费
		cf.setTdcrj(this.tdcrj);
		cf.setHlfy(this.hlfy);//合理费用
		cf.setTdjcdm(this.tdjcdm);//土地级次代码
		cf.setTdjcmc(this.tdjcmc);//土地级次名称
		cf.setFwcqzbzzflxdm(this.fwcqzbzzflxdm);//房屋产权证标注住房类型代码
		cf.setFwcqzbzzflxmc(this.fwcqzbzzflxmc);//房屋产权证标注住房类型名称
		cf.setCqzbzjzmjflSubmit(this.cqzbzjzmjflSubmit);//产权证标注建筑面积Submit
		cf.setHfbzSubmit(this.hfbzSubmit);//划分标准Submit
		cf.setGrsdszsfsSubmit(this.grsdszsfsSubmit);//个人所得税征收方式Submit
		cf.setYyszsfsSubmit(this.yyszsfsSubmit);//营业税征收方式Submit
		cf.setJsjeSubmit(this.jsjeSubmit);//计税收入金额Submit
		cf.setJssrqrfsSubmit(this.jssrqrfs);
		
		cf.setIsSaved(this.isSaved);
		
		cf.setMpmhdjg(this.mpmhdjg);
		cf.setCjssl(this.cjssl);
		cf.setFpszrq(this.fpszrq);//发票所载日期
		cf.setAnjjse(this.anjjse);//按年加计数额
		cf.setFwszqydm(this.fwszqydm);
		
		cf.setMfsbxxList(this.mfsbxxList);
		cf.setSjhjje(this.sjhjje);
		//added by zhangj
		cf.setFwhdlxdm(this.fwhdlxdm);//房屋核定类型
		cf.setQszyxsmxdm(this.qszyxsmxdm);//房屋权属转移明细代码
		cf.setQszyxsmxmc(this.qszyxsmxmc);//房屋权属转移明细名称
		cf.setYqspfwjsjg(this.yqspfwjsjg);
		cf.setYhszsfs(this.yhszsfs);
		cf.setIsQuery(this.isQuery);
		cf.setTdzsszsfsSubmit(this.tdzsszsfsSubmit);
		cf.setYhszsfsSubmit(this.yhszsfsSubmit);
		cf.setFpcxLink(this.fpcxLink);
		//modify
		cf.setFwszqymc(this.fwszqymc);//房屋所在区域名称
		cf.setFwszqyje(this.fwszqyje);//普通住房最高套总价，added by zhangj,2014.10.15
		
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
