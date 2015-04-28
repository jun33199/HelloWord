package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.MfskzsForm;
/**
 * <p>Title:</p>
 * <p>Description:</p>
 * @author 张一军
 * @version 1.1
 */
public class MfskzsBo implements Serializable{

	public String hasHdxx="N";
	public String yhid;// 用户ID
	public String yhxm;// 用户名称
	
	public String tfrq;// 填发日期
	public String zsjg;// 征收机关
	public String htbh;// 合同编号
	public String nsrmc;// 卖方纳税人名称
	public String zjhm;// 卖方证件号码
	public String cxzjhm;// 查询卖方证件号码
	public String htwsqyrq;// 买卖双方合同签订日期
	public String fwyz; //建委查询房屋原值
	
	public String sbbh_his;// 申报编号记录
	public String nsrmc_his;// 纳税人姓名记录
	public String sfzjlxmc_his;// 证件类型记录
	public String sfzjhm_his;// 证件号码记录
	public String fwsyqzh_his;// 房屋所有权证号记录
	public String tdfwzldz_his;// 房地产位置记录
	public String htqdsj_his;// 合同签订日期记录
	public String sbrq_his;// 申报日期记录
	public String fwjzmj_his;// 房屋建筑面积记录
	public String jsje_his;// 计税金额记录
	public String sl_his;// 税率记录
	public String ynse_his;// 应纳金额记录
	public String fwqszylx_his;// 房屋权属转移类型
	 
    public ArrayList sbxxHisList = new ArrayList();//契税缴纳税款历史信息
    
	public String zsqx;// 征收区县
	public String sbbh;// 申报编号	
	public String scriptStr;// js数组字符串
	public String jsjdm;// 计算机代码
	
	public ArrayList dataList = new ArrayList();//页面数据集
	public String hjsjje; 
	public String hjjsje;//合计计税金额
	public String hjsjse;//合计应缴金额
	public String hjjmje;//合计减免金额
	public String hjyjje;//合计实缴金额
    
	public String zjlxdm; //证件类型代码
	public String zjlxmc; //证件类型名称

	public String htbh1;// 卖方申报数据维护结果数据合同编号
	public String bz; //返回信息一
	public String message; //返回信息二
	
	public String pzzhdm; //票证账户代码
	
	public ArrayList sbxxList = new ArrayList();//卖方申报数据信息
	public String tdzzssl; //土地增值税税率
	
	public String dybs; //打印标识
	//完税证打印  (zsjg tfrq jsjdm nsrmc 字段与上面共用)
    public String wszxh; //完税证序号
    public String headWszh; //完税证号
    public String zclx; //注册类型
    public String dz; //地址
    public String skssksrq; //税款所属开始日期
    public String skssjsrq; //税款所属结束日期
    public String hjje; //合计金额
    public String hjjedx; //合计金额大写
    public String wszbz; //备注
    public String mxSz; //名细信息，税种名称
    public String mxPmmc; //名细信息，品目名称，其实是税种税目名称
    public String mxKssl; //名细信息，课税数量
    public String mxJsje; //名细信息，缴税金额
    public String mxSl; //名细信息，税率
    public String mxSkssrq; //明细信息，税款所属日期
    public String mxYjhkc; //名细信息，已缴或扣除
    public String mxSjse; //名细信息，实缴税额
    public String mxJmje; //名细信息，减免税额
    public String mxYjje; //名细信息，实缴税额
    
    
    public String sbrq; //申报日期
    public String yhmc; //银行名称
    public String zh; //帐号   
    public String swjgzzjgmc; //征收机关名称（税务所的名称）
    public String gkzzjgdm; //国库代码
    public String gkzzjgmc; //国库名称
    
    public String sbrqn; //申报日期年
    public String sbrqy; //申报日期月
    public String sbrqr; //申报日期日
    
    public String rqcs; //日期参数
    //added by zhangj
    public String fwhdlxdm;// 核定类型代码
    public String htzj;//合同总价
    public String  isPrintSuccess;
 
	/**
	 * @methodName:getFromData
	 * @function:
	 * 
	 * @return
	 * @author:张一军
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
		mf.setTfrq(this.tfrq);// 填发日期
		mf.setZsjg(this.zsjg);// 征收机关
		mf.setHtbh(this.htbh);// 合同编号
		mf.setNsrmc(this.nsrmc);// 卖方纳税人名称
		mf.setZjhm(this.zjhm);// 卖方证件号码
		mf.setCxzjhm(this.cxzjhm);// 卖方证件号码
		mf.setHtwsqyrq(this.htwsqyrq); 
		mf.setRqcs(this.rqcs);

		mf.setSbbh_his(this.sbbh_his);// 申报编号记录
		mf.setNsrmc_his(this.nsrmc_his);// 证件类型记录
		mf.setSfzjlxmc_his(this.sfzjlxmc_his);// 证件类型记录
		mf.setSfzjhm_his(this.sfzjhm_his);// 证件号码记录
		mf.setFwsyqzh_his(this.fwsyqzh_his);// 房屋所有权证号记录
		mf.setTdfwzldz_his(this.tdfwzldz_his);// 房地产位置记录
		mf.setHtqdsj_his(this.htqdsj_his);// 合同签订日期记录
		mf.setSbrq_his(this.sbrq_his);// 申报日期记录
		mf.setFwjzmj_his(this.fwjzmj_his);// 房屋建筑面积记录
		mf.setJsje_his(this.jsje_his);// 计税金额记录
		mf.setSl_his(this.sl_his);// 税率记录
		mf.setYnse_his(this.ynse_his);// 应纳金额记录
		mf.setFwqszylx_his(this.fwqszylx_his);//房屋权属转移类型
		mf.setSbxxHisList(this.sbxxHisList);//契税缴纳税款历史信息
		mf.setZsqx(this.zsqx);// 征收区县
		
		mf.setSbbh(this.sbbh);// 申报编号
		mf.setScriptStr(this.scriptStr);// js数组字符串
		mf.setJsjdm(this.jsjdm);// 计算机代码
		mf.setFwyz(this.fwyz);// 建委查询房屋原值
		
		mf.setDataList(this.dataList);// 页面数据集
		mf.setHjjsje(this.hjjsje);//合计计税金额
		mf.setHjsjse(this.hjsjse);//合计应缴金额
		mf.setHjjmje(this.hjjmje);//合计减免金额
		mf.setHjyjje(this.hjyjje);// 合计实缴金额
		mf.setZjlxdm(this.zjlxdm);// 证件类型代码
		mf.setZjlxmc(this.zjlxmc);// 证件类型名称
		mf.setBz(this.bz);
		mf.setMessage(this.message);
		mf.setHtbh1(this.htbh1);
		mf.setPzzhdm(this.pzzhdm);//票证账户代码
		mf.setSbxxList(this.sbxxList);//卖方申报数据信息
		
		mf.setDybs(this.dybs);//打印标识
		mf.setWszxh(this.wszxh);//完税证序号
		mf.setHeadWszh(this.headWszh);//完税证号
		mf.setZclx(this.zclx);//注册类型
		mf.setDz(this.dz);//地址
		mf.setSkssksrq(this.skssksrq);//税款所属开始日期
		mf.setSkssjsrq(this.skssjsrq);//税款所属结束日期
		mf.setHjje(this.hjje);//合计金额
		mf.setHjjedx(this.hjjedx);//合计金额大写
		mf.setWszbz(this.wszbz);//备注
		mf.setMxSz(this.mxSz);//名细信息，税种名称
		mf.setMxPmmc(this.mxPmmc);//名细信息，品目名称，其实是税种税目名称
		mf.setMxKssl(this.mxKssl);//名细信息，课税数量
		mf.setMxJsje(this.mxJsje);//名细信息，缴税金额
		mf.setMxSl(this.mxSl);//名细信息，税率
		mf.setMxSkssrq(this.mxSkssrq);
		mf.setMxYjhkc(this.mxYjhkc);//名细信息，已缴或扣除		
		mf.setMxSjse(this.mxSjse);//名细信息，应纳税额	
		mf.setMxJmje(this.mxJmje); //名细信息，减免税额
		mf.setMxYjje(this.mxYjje); //名细信息，实缴税额
		
		mf.setSbrq(this.sbrq);//申报日期
		mf.setYhmc(this.yhmc);//银行名称
		mf.setZh(this.zh);//帐号
		mf.setSwjgzzjgmc(this.swjgzzjgmc);//征收机关名称（税务所的名称）
		mf.setGkzzjgdm(this.gkzzjgdm);//国库代码
		mf.setGkzzjgmc(this.gkzzjgmc);//国库名称
		mf.setSbrqn(this.sbrqn);//申报日期年
		mf.setSbrqy(this.sbrqy);//申报日期月
		mf.setSbrqr(this.sbrqr);//申报日期日
		mf.setFwhdlxdm(this.fwhdlxdm);// 房屋核定类型代码
		mf.setHtzj(this.htzj);// 房屋核定类型代码
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
