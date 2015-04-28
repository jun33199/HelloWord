package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.MfskzsBo;

/**
 * 
 * <p>
 * Title:卖方税款征收form
 * </p>
 * <p>
 * Description:form
 * </p>
 * 
 * @author 张一军
 * @version 1.1
 */
public class MfskzsForm extends QueryBaseForm {

	private String[] columns =
	{"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
    "skssksrq", "skssjsrq", "szdm", "szmc", "sl","jmje","yjje"};
	
	private String hasHdxx="N";
	private String tfrq;// 填发日期
	private String zsjg;// 征收机关
	
	private String yhid;// 用户ID
	private String yhxm;// 用户名称
	
	private String htbh;// 合同编号
	private String nsrmc;// 卖方纳税人名称
	private String zjhm;// 卖方证件号码
	private String cxzjhm;// 查询卖方证件号码
	public String htwsqyrq;// 买卖双方合同签订日期
	
	private String sbbh_his;// 申报编号记录
	private String nsrmc_his;// 纳税人姓名记录
	private String sfzjlxmc_his;// 证件类型记录
	private String sfzjhm_his;// 证件号码记录
	private String fwsyqzh_his;// 房屋所有权证号记录
	private String tdfwzldz_his;// 房地产位置记录
	private String htqdsj_his;// 合同签订日期记录
	private String sbrq_his;// 申报日期记录
	private String fwjzmj_his;// 房屋建筑面积记录
	private String jsje_his;// 计税金额记录
	private String sl_his;// 税率记录
	private String ynse_his;// 应纳金额记录
	private String fwqszylx_his;// 房屋权属转移类型	
	
	private String zsqx;// 征收区县
	private String fwyz;// 房屋原值
	
	private ArrayList sbxxHisList = new ArrayList();//契税缴纳税款历史信息  

	private String sbbh;// 申报编号
    private String scriptStr;// js数组字符串
    private String jsjdm;// 计算机代码
    
    private ArrayList dataList = new ArrayList();//申报页面数据集
    public String hjsjje; 
    private String hjjsje;//合计计税金额
    private String hjsjse;//合计应缴金额
    private String hjjmje;//合计减免金额
    private String hjyjje;//合计实缴金额
    
    private String zjlxdm; //证件类型代码
    private String zjlxmc; //证件类型名称

	private String htbh1;//卖方申报数据维护结果数据合同编号
    private String bz; //返回信息一
    private String message; //返回信息二
    private String pzzhdm; //票证账户代码

    private ArrayList sbxxList = new ArrayList();//卖方申报数据信息
    private String tdzzssl; //土地增值税税率

    private String dybs; //打印标识
	//完税证打印  (zsjg tfrq jsjdm nsrmc 字段与上面共用)
    private String wszxh; //完税证序号
    private String headWszh; //完税证号
    private String zclx; //注册类型
    private String dz; //地址
    private String skssksrq; //税款所属开始日期
    private String skssjsrq; //税款所属结束日期
    private String hjje; //合计金额
    private String hjjedx; //合计金额大写
    private String wszbz; //备注
    private String mxSz; //名细信息，税种名称
    private String mxPmmc; //名细信息，品目名称，其实是税种税目名称
    private String mxKssl; //名细信息，课税数量
    private String mxJsje; //名细信息，缴税金额
    private String mxSl; //名细信息，税率
    private String mxSkssrq; //税款所属开始日期
    private String mxYjhkc; //名细信息，已缴或扣除
    private String mxSjse; //名细信息，应纳税额
    private String mxJmje; //名细信息，减免税额
    private String mxYjje; //名细信息，实缴税额
    
    private String sbrq; //申报日期
    private String yhmc; //银行名称
    private String zh; //帐号
    private String swjgzzjgmc; //征收机关名称（税务所的名称）
    private String gkzzjgdm; //国库代码
    private String gkzzjgmc; //国库名称
    private String sbrqn; //申报日期年
    private String sbrqy; //申报日期月
    private String sbrqr; //申报日期日
    
    public String rqcs; //日期参数
    //added by zhangj
    private String fwhdlxdm;// 房屋核定类型代码
    private String htzj;// 合同总价
    private String  isPrintSuccess;
	public Object getData() {
		MfskzsBo bo = new MfskzsBo();
		bo.tdzzssl = this.tdzzssl;
		bo.hasHdxx = this.hasHdxx;
		bo.yhid = this.yhid;
		bo.yhxm = this.yhxm;
		bo.tfrq = this.tfrq;// 填发日期
		bo.zsjg = this.zsjg;// 征收机关
		bo.htbh = this.htbh;// 合同编号
		bo.nsrmc = this.nsrmc;// 卖方纳税人名称
		bo.zjhm = this.zjhm;// 卖方证件号码
		bo.cxzjhm = this.cxzjhm;// 查询卖方证件号码
		bo.htwsqyrq = this.htwsqyrq;
		bo.rqcs = this.rqcs;
		
		bo.sbbh_his = this.sbbh_his;// 申报编号记录
		bo.nsrmc_his = this.nsrmc_his;// 证件类型记录
		bo.sfzjlxmc_his = this.sfzjlxmc_his;// 证件类型记录
		bo.sfzjhm_his = this.sfzjhm_his;// 证件号码记录
		bo.fwsyqzh_his = this.fwsyqzh_his;// 房屋所有权证号记录
		bo.tdfwzldz_his = this.tdfwzldz_his;// 房地产位置记录
		bo.htqdsj_his = this.htqdsj_his;// 合同签订日期记录
		bo.sbrq_his = this.sbrq_his;// 申报日期记录
		bo.fwjzmj_his = this.fwjzmj_his;// 房屋建筑面积记录
		bo.jsje_his = this.jsje_his;// 计税金额记录
		bo.sl_his = this.sl_his;// 税率记录
		bo.ynse_his = this.ynse_his;// 应纳金额记录
		bo.fwqszylx_his = this.fwqszylx_his;//房屋权属转移类型
		bo.sbxxHisList=this.sbxxHisList;//契税缴纳税款历史信息
		bo.zsqx = this.zsqx;// 征收区县
		bo.sbbh = this.sbbh;// 申报编号
		bo.fwyz = this.fwyz;//建委查询房屋原值
		bo.scriptStr = this.scriptStr;// js数组字符串
		bo.jsjdm = this.jsjdm;// 计算机代码		
		bo.dataList=this.dataList;//页面数据集
		bo.hjjsje=this.hjjsje;//合计计税金额
		bo.hjsjse=this.hjsjse;//合计应缴金额
		bo.hjjmje=this.hjjmje;//合计减免金额
		bo.hjyjje=this.hjyjje;//合计实缴金额
		bo.zjlxdm=this.zjlxdm;//证件类型代码
		bo.zjlxmc=this.zjlxmc;//证件类型名称
		
		bo.bz=this.bz;
		bo.message=this.message;
		bo.htbh1=this.htbh1;
		bo.pzzhdm=this.pzzhdm;//票证账户代码
		bo.sbxxList=this.sbxxList;//卖方申报数据信息
		
		bo.dybs=this.dybs; //打印标识
		//完税证打印  (zsjg tfrq jsjdm nsrmc 字段与上面共用)
		bo.wszxh=this.wszxh; //完税证序号
		bo.headWszh=this.headWszh; //完税证号
		bo.zclx=this.zclx; //注册类型
		bo.dz=this.dz; //地址
		bo.skssksrq=this.skssksrq; //税款所属开始日期
		bo.skssjsrq=this.skssjsrq; //税款所属结束日期
		bo.hjje=this.hjje; //合计金额
		bo.hjjedx=this.hjjedx; //合计金额大写
		bo.wszbz=this.wszbz; //备注
		bo.mxSz=this.mxSz; //名细信息，税种名称
		bo.mxPmmc=this.mxPmmc; //名细信息，品目名称，其实是税种税目名称
		bo.mxKssl=this.mxKssl; //名细信息，课税数量
		bo.mxJsje=this.mxJsje; //名细信息，缴税金额
		bo.mxSl=this.mxSl; //名细信息，税率
		bo.mxSkssrq = this.mxSkssrq; //明细信息，税款所属日期
		bo.mxYjhkc=this.mxYjhkc; //名细信息，已缴或扣除
		bo.mxSjse=this.mxSjse; //名细信息，应纳税额
		bo.mxJmje=this.mxJmje; //名细信息，减免税额
		bo.mxYjje=this.mxYjje; //名细信息，实缴税额
		
		bo.sbrq=this.sbrq;//申报日期
		bo.yhmc=this.yhmc;//银行名称
		bo.zh=this.zh;//帐号
		bo.swjgzzjgmc=this.swjgzzjgmc;//征收机关名称（税务所的名称）
		bo.gkzzjgdm=this.gkzzjgdm;//国库代码
		bo.gkzzjgmc=this.gkzzjgmc;//国库名称
		bo.sbrqn=this.sbrqn;//申报日期年
		bo.sbrqy=this.sbrqy;//申报日期月
		bo.sbrqr=this.sbrqr;//申报日期日
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
