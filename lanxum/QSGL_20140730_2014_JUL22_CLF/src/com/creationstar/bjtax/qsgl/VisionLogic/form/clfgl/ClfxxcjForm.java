package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;

/**
 * 
 * <p>
 * Title:存量房信息采集form
 * </p>
 * <p>
 * Description:form
 * </p>
 * 
 * @author 唐昌富
 * @version 1.1
 */
public class ClfxxcjForm extends QueryBaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean hasMAuthorise =true;//是否有修改存量房采集信息的权利  false --无  true--有
	
	private String xxly="01";//采集类型  01：二维码，02：手工采集;
	private String cjfsdm="01";//采集方式  01：二维码扫描，02：手工采集;
	
	//
	private String saveIsSuccess = "0";//保存操作是否成功0--未成功  1--成功
	private String keyStr;//主键格式 SBBH+"::"+HTBH
	private String piccode;// 加密二维码
	private String UNEpiccode;// 非加密二维码
	// 房屋信息
	private String hasHdxx="N";//包含税务人员核定信息  Y--包含  N--不包含
	private String bbbs;// 版本标示
	private String htbh;// 合同编号
	private String htwsqyrq;// 合同网上签约日期
	private String fwzlqx;// 房屋坐落区县
	private String fwzldz;// 房屋坐落地址
	private String fwqszylx;// 房屋权属转移类型_代码
	private String fwqszylxmc;// 房屋权属转移类型_名称
	private String sfwscsssggf;// 是否为首次上市已购公房_代码
	private String sfwscsssggfmc;// 是否为首次上市已购公房_名称
	private String fwcqzh;// 房屋产权证号
	private String fwsyqztfrq;// 房屋所有权证填发日期
	private String fwjzmj;// 房屋建筑面积
	private String jzjgdm;// 建筑结构代码
	private String jzjgmc;// 建筑结构名称
	private String ghyt;// 规划用途
	private String htzj;// 合同总价
	private String bzdm;// 币种代码
	private String bzmc;// 币种名称
	private String hl;// 汇率
	private String wbje;// 外币金额
	private String szlc;// 所在楼层
	private String fdczjjgmc;// 房地产中介机构名称

	// 卖方信息
	private String all_sellerInfo;
	private String sell_mc;
	private String sell_lb;
	private String sell_lb_mc;
	private String sell_zjlx;
	private String sell_zjlx_mc;
	private String sell_zjhm;
	private String sell_qlrfe;
	private String sell_lxdh;

	// 买方信息
	private String all_buyerInfo;
	private String buy_mc;
	private String buy_lb;
	private String buy_lb_mc;
	private String buy_zjlx;
	private String buy_zjlx_mc;
	private String buy_zjhm;
	private String buy_qlrfe;
	private String buy_lxdh;
	
	//打印信息
	private String slrq;//受理日期
	
	//获得当前证件类型名称
	private String tmp_zllxmc;
	
	//新加字段:房屋性质代码
	private String fwxzdm;
	
	
	/**
	 * 代码表信息
	 */
	//证件代码表
	private List zjList= new ArrayList();
	//币种代码表
	private List codeList_bz = new ArrayList();
	
	//房屋性质代码表
	private List codeList_fwxz = new ArrayList();
	
	
	
	
	

	public Object getData() {
		ClfxxcjBo bo = new ClfxxcjBo();
		bo.keyStr = this.keyStr;
		bo.sbbh = this.sbbh;
		bo.bbbs = this.bbbs;// 版本标示
		bo.htbh = this.htbh;// 合同编号
		bo.htwsqyrq = this.htwsqyrq;// 合同网上签约日期
		bo.fwzlqx = this.fwzlqx;// 房屋坐落区县
		bo.fwzldz = this.fwzldz;// 房屋坐落地址
		bo.fwqszylx = this.fwqszylx;// 房屋权属转移类型_代码
		bo.fwqszylxmc = this.fwqszylxmc;// 房屋权属转移类型_名称
		bo.sfwscsssggf = this.sfwscsssggf;// 是否为首次上市已购公房_代码
		bo.sfwscsssggfmc = this.sfwscsssggfmc;// 是否为首次上市已购公房_名称
		bo.fwcqzh = this.fwcqzh;// 房屋产权证号
		bo.fwsyqztfrq = this.fwsyqztfrq;// 房屋所有权证填发日期
		bo.fwjzmj = this.fwjzmj;// 房屋建筑面积
		bo.jzjgdm = this.jzjgdm;// 建筑结构代码
		bo.jzjgmc = this.jzjgmc;// 建筑结构名称
		bo.ghyt = this.ghyt;// 规划用途
		bo.htzj = this.htzj;// 合同总价
		bo.bzdm = this.bzdm;// 币种代码
		bo.bzmc = this.bzmc;// 币种名称
		bo.hl = this.hl;// 汇率
		bo.wbje = this.wbje;// 外币金额
		bo.szlc = this.szlc;// 所在楼层
		bo.fdczjjgmc = this.fdczjjgmc;// 房地产中介机构名称
		bo.all_sellerInfo = this.all_sellerInfo;
		bo.all_buyerInfo = this.all_buyerInfo;
		bo.UNEpiccode = this.UNEpiccode;

		bo.fwxzdm = this.fwxzdm;
		
		bo.xxly = this.xxly;
		return bo;
	}
	
	
	public void clear(){
		this.hasMAuthorise =true;//是否有修改存量房采集信息的权利  false --无  true--有
		this.xxly="01";//采集类型  01：二维码，02：手工采集="";
		this.saveIsSuccess = "0";//保存操作是否成功0--未成功  1--成功
		this.keyStr="";//主键格式 SBBH+"::"+HTBH
		this.piccode="";// 加密二维码
		this.UNEpiccode="";// 非加密二维码
		this.sbbh ="";
		// 房屋信息
		this.hasHdxx="N";
		this.bbbs="";// 版本标示
		this.htbh="";// 合同编号
		this.htwsqyrq="";// 合同网上签约日期
		this.fwzlqx="";// 房屋坐落区县
		this.fwzldz="";// 房屋坐落地址
		this.fwqszylx="";// 房屋权属转移类型_代码
		this.fwqszylxmc="";// 房屋权属转移类型_名称
		this.sfwscsssggf="";// 是否为首次上市已购公房_代码
		this.sfwscsssggfmc="";// 是否为首次上市已购公房_名称
		this.fwcqzh="";// 房屋产权证号
		this.fwsyqztfrq="";// 房屋所有权证填发日期
		this.fwjzmj="";// 房屋建筑面积
		this.jzjgdm="";// 建筑结构代码
		this.jzjgmc="";// 建筑结构名称
		this.ghyt="";// 规划用途
		this.htzj="";// 合同总价
		this.bzdm="";// 币种代码
		this.bzmc="";// 币种名称
		this.hl="";// 汇率
		this.wbje="";// 外币金额
		this.szlc="";// 所在楼层
		this.fdczjjgmc="";// 房地产中介机构名称
		this.all_sellerInfo="";// 卖方信息
		this.all_buyerInfo="";// 买方信息
		this.slrq="";//受理日期
		this.tmp_zllxmc="";//获得当前证件类型名称
		this.fwxzdm="";//新加字段:房屋性质代码
		this.zjList= new ArrayList();//证件代码表
		this.codeList_bz = new ArrayList();//币种代码表	
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
