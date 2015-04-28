package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm;
/**
 * <p>Title:</p>
 * <p>Description:</p>
 * @author 唐昌富
 * @version 1.1
 */
public class ClfxxcjBo implements Serializable{
	public boolean hasMAuthorise =true;//是否有修改存量房采集信息的权利  false --无  true--有
	public String hasHdxx="N";
	public String SaveIsSuccess = "0";//保存操作是否成功0--未成功  1--成功
	public String keyStr;
	public String UNEpiccode;//非加密二维码
	//房屋信息
	public String bbbs;//版本标示
	public String htbh;//合同编号
	public String htwsqyrq;//合同网上签约日期
	public String fwzlqx;//房屋坐落区县
	public String fwzldz;//房屋坐落地址
	public String fwqszylx;//房屋权属转移类型_代码
	public String fwqszylxmc;//房屋权属转移类型_名称
	public String sfwscsssggf;//是否为首次上市已购公房_代码
	public String sfwscsssggfmc;//是否为首次上市已购公房_名称
	public String fwcqzh;//房屋产权证号
	public String fwsyqztfrq;//房屋所有权证填发日期
	public String fwjzmj;// 房屋建筑面积
	public String jzjgdm;// 建筑结构代码
	public String jzjgmc;// 建筑结构名称
	public String ghyt;//规划用途
	public String htzj;//合同总价
	public String bzdm;//币种代码
	public String bzmc;//币种名称
	public String hl;//汇率
	public String wbje;//外币金额
	public String szlc;//所在楼层
	public String fdczjjgmc;//房地产中介机构名称
	//卖方信息
	public String all_sellerInfo;
	
	public List sellerList = new ArrayList();//存量房交易信息查询用 --所有卖方信息
	public String allSellerNames4jyxxcx;//存量房交易信息查询用   --卖方姓名，多个用“/”隔开
	//买方信息
	public String all_buyerInfo;
	public List buyerList = new ArrayList();//存量房交易信息查询用 --买方信息
	public String allBuyerNames4jyxxcx;//存量房交易信息查询用   --买方姓名，多个用“/”隔开
	
	public String sbbh;//查询时用
	
	//新加字段:房屋性质代码
	public String fwxzdm;
	
	//信息来源  01：二维码，02：手工采集
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
	 * @author:唐昌富
	 * @create date：2013-5-16 下午03:55:00
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
