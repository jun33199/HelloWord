/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web;

import java.util.*;

import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel;
import com.ttsoft.framework.form.*;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Form。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class GdzysSydjxxlrForm extends BaseForm {
    public GdzysSydjxxlrForm() {
    }
    
    //打印时间
    private int nian;
    private int yue;
    private int tian;
    
    private List dylist;
    /*---------------------------------------添加备注----------------------------------*/
//    private String bzms;
//    private String bzlxdm;
    /*---------------------------------------确认数据----------------------------------*/
    private String[] sbbxlh_ary;
    /*---------------------------------------初始数据----------------------------------*/
  //税源类型list
	private List sylx_list;
	//占地用途list
	private List zdyt_list;
	//适用税额list
	private List syse_list;
	//减免类别list
	private List jmlb_list;
    /*---------------------------------------申报信息-----------------------------------*/
	//所属行业
	private List sshy_list;
	//登记注册类型
	private List djzclx_list;
	//适用税额年度
	private List nd_list;
	//适用税额年度
	private String sysend;
  //申报序列号
    private String sbbxlh;
    //税源类型代码
    private String sylxdm;
    //纳税人类型
    private String nsrlx;
    //纳税人名称
    private String nsrmc;
    //计算机代码
    private String jsjdm;
   //纳税人识别号
    private String nsrsbh;
    //纳税人所属行业代码
    private String nsrsshy;
    private String nsrsshymc;
    //身份证照类型
    private String sfzzlxdm;
    private String sfzzlxmc;
    //身份证照号码
    private String sfzzhm;
  
	//纳税人详细地址
    private String nsrxxdz;
    //企业登记注册类型--代码
    private String qydjzclx;
    private String qydjzclxmc;
    //开户银行代码
    private String khyh;
    //银行帐号
    private String yhzh;
    //联系人姓名
    private String lxrxm;
    //联系电话
    private String lxdh;
    //是否市局审批
    private String sfsjsp;
    /*-----------------------------------土地信息------------------------------------*/
    //土地坐落地址
    private String tdzldz;
    //批准占地文号
    private String pzjdwh;
    //批准占地面积
    private String pzjdmj;
    //建设项目名称
    private String jsxmmc;
    //实际占地面积
    private String sjzdmj;
    //占地时间
    private String zdsj;
    //减免申报理由
    private String jmsyj;
    /*-------------------------------------减免信息------------------------------------*/
    //减免税依据代码
    private String jmsyjdm;
    
    //计税面积
    private String hj_jsmj;
    //计征税额
    private String hj_jzse;
    //减免面积
    private String hj_jmmj;
    //减免税额
    private String hj_jmse;
    //应税面积
    private String hj_ysmj;
    //应纳税额
    private String hj_ynse;
    //创建人
    private String cjr;
    //创建时间
    private String cjsj;
    //确认人
    private String qrr;
    //确认时间
    private String qrrq;
    //确认状态
    private String qrzt;
    //确认人
    private String sjqrr;
    //确认时间
    private String sjqrrq;
    //确认状态
    private String sjqrzt;
    //录入人
    private String lrr;
    //录入时间
    private Date lrsj;
    //备注类型代码
    private String bzlxdm;
    //备注描述
    private String bzms;
    
    /*------------------------------------------申报明细---------------------------------------*/
    
    private String[] zdyt;
    //适用税额
    private String[] syse;
    //计税面积
    private String[] jsmj;
    //计征税额
    private String[] jzse;
    //减免面积
    private String[] jmmj;
    //减免税额
    private String[] jmse;
    //减免税额
    private String[] ysmj;
    //应纳税额
    private String[] ynse;
    
    //占地用途代码
    private List zdytbean;
    private String[] zdyt_sbmx;
    //适用税额
    private String[] syse_sbmx;
    //计税面积
    private String[] jsmj_sbmx;
    //计征税额
    private String[] jzse_sbmx;
    //减免面积
    private String[] jmmj_sbmx;
    //减免税额
    private String[] jmse_sbmx;
    //应纳税额
    private String[] ynse_sbmx;
    
    
    /*-------------------------------------------减免明细--------------------------------------*/
    
    private JMXXModel jmxxModel;
    //减免类别代码
    private String[] jmlbdm_jmmx;
    //减免面积
    private String[] jmmj_jmmx;
    //减免税额
    private String[] jmse_jmmx; 
    //分类减免面积
    private String[] fljmmj;
    //分类减免税额
    private String[] fljmse;
    
    private List zdytmc;
    private List zdytdm;
    
    private List jmlbdm;
    private List jmlbmc;
    private Map sbmx_map;
	private Map jmmx_map;
    private String shzt;
    private String sjqrsj;
    private String bzdm;
    private String bznr;
    private String yhdm;
    private String errors;
    private String flag;
    private String whnd;
    private String wh;
    private List sbmxlist;
    private String bg_flag;
    private String jks_flag;
    private String jms_flag;
    private String zwbs_flag;
    private String sh_flag;

    
    
    
    
	public String getSh_flag() {
		return sh_flag;
	}
	public void setSh_flag(String sh_flag) {
		this.sh_flag = sh_flag;
	}
	public String getJks_flag() {
		return jks_flag;
	}
	public void setJks_flag(String jks_flag) {
		this.jks_flag = jks_flag;
	}
	public String getJms_flag() {
		return jms_flag;
	}
	public void setJms_flag(String jms_flag) {
		this.jms_flag = jms_flag;
	}
	public String getZwbs_flag() {
		return zwbs_flag;
	}
	public void setZwbs_flag(String zwbs_flag) {
		this.zwbs_flag = zwbs_flag;
	}
	public String getBg_flag() {
		return bg_flag;
	}
	public void setBg_flag(String bg_flag) {
		this.bg_flag = bg_flag;
	}
	public List getSbmxlist() {
		return sbmxlist;
	}
	public void setSbmxlist(List sbmxlist) {
		this.sbmxlist = sbmxlist;
	}
	public String getWhnd() {
		return whnd;
	}
	public void setWhnd(String whnd) {
		this.whnd = whnd;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getBzdm() {
		return bzdm;
	}
	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}
	public String getBznr() {
		return bznr;
	}
	public void setBznr(String bznr) {
		this.bznr = bznr;
	}
	public String getSjqrsj() {
		return sjqrsj;
	}
	public void setSjqrsj(String sjqrsj) {
		this.sjqrsj = sjqrsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public Map getJmmx_map() {
		return jmmx_map;
	}
	public void setJmmx_map(Map jmmx_map) {
		this.jmmx_map = jmmx_map;
	}
	public int getNian() {
		return nian;
	}
	public void setNian(int nian) {
		this.nian = nian;
	}
	public int getYue() {
		return yue;
	}
	public void setYue(int yue) {
		this.yue = yue;
	}
	public int getTian() {
		return tian;
	}
	public void setTian(int tian) {
		this.tian = tian;
	}
	public Map getSbmx_map() {
		return sbmx_map;
	}
	public void setSbmx_map(Map sbmx_map) {
		this.sbmx_map = sbmx_map;
	}
	public List getJmlbdm() {
		return jmlbdm;
	}
	public void setJmlbdm(List jmlbdm) {
		this.jmlbdm = jmlbdm;
	}
	public List getJmlbmc() {
		return jmlbmc;
	}
	public void setJmlbmc(List jmlbmc) {
		this.jmlbmc = jmlbmc;
	}
	public List getZdytmc() {
		return zdytmc;
	}
	public void setZdytmc(List zdytmc) {
		this.zdytmc = zdytmc;
	}
	public List getZdytdm() {
		return zdytdm;
	}
	public void setZdytdm(List zdytdm) {
		this.zdytdm = zdytdm;
	}
	public String getSfzzlxmc() {
		return sfzzlxmc;
	}
	public void setSfzzlxmc(String sfzzlxhm) {
		this.sfzzlxmc = sfzzlxhm;
	}
    public List getDylist() {
		return dylist;
	}
	public String getQydjzclxmc() {
		return qydjzclxmc;
	}
	public void setQydjzclxmc(String qydjzclxmc) {
		this.qydjzclxmc = qydjzclxmc;
	}
	public String getNsrsshymc() {
		return nsrsshymc;
	}
	public void setNsrsshymc(String nsrsshymc) {
		this.nsrsshymc = nsrsshymc;
	}
	public void setDylist(List dylist) {
		this.dylist = dylist;
	}
	public String[] getYsmj() {
		return ysmj;
	}
	public void setYsmj(String[] ysmj) {
		this.ysmj = ysmj;
	}
	public String[] getSbbxlh_ary() {
		return sbbxlh_ary;
	}
	public void setSbbxlh_ary(String[] sbbxlh_ary) {
		this.sbbxlh_ary = sbbxlh_ary;
	}
	public List getSylx_list() {
		return sylx_list;
	}
	public void setSylx_list(List sylx_list) {
		this.sylx_list = sylx_list;
	}
	public List getZdyt_list() {
		return zdyt_list;
	}
	public void setZdyt_list(List zdyt_list) {
		this.zdyt_list = zdyt_list;
	}
	public List getSyse_list() {
		return syse_list;
	}
	public void setSyse_list(List syse_list) {
		this.syse_list = syse_list;
	}
	public List getJmlb_list() {
		return jmlb_list;
	}
	public void setJmlb_list(List jmlb_list) {
		this.jmlb_list = jmlb_list;
	}
	public List getSshy_list() {
		return sshy_list;
	}
	public void setSshy_list(List sshy_list) {
		this.sshy_list = sshy_list;
	}
	public List getDjzclx_list() {
		return djzclx_list;
	}
	public void setDjzclx_list(List djzclx_list) {
		this.djzclx_list = djzclx_list;
	}
	public List getNd_list() {
		return nd_list;
	}
	public void setNd_list(List nd_list) {
		this.nd_list = nd_list;
	}
	public String getSysend() {
		return sysend;
	}
	public void setSysend(String sysend) {
		this.sysend = sysend;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public String getSylxdm() {
		return sylxdm;
	}
	public void setSylxdm(String sylxdm) {
		this.sylxdm = sylxdm;
	}
	public String getNsrlx() {
		return nsrlx;
	}
	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrsshy() {
		return nsrsshy;
	}
	public void setNsrsshy(String nsrsshy) {
		this.nsrsshy = nsrsshy;
	}
	public String getSfzzlxdm() {
		return sfzzlxdm;
	}
	public void setSfzzlxdm(String sfzzlxdm) {
		this.sfzzlxdm = sfzzlxdm;
	}
	public String getSfzzhm() {
		return sfzzhm;
	}
	public void setSfzzhm(String sfzzhm) {
		this.sfzzhm = sfzzhm;
	}
	public String getNsrxxdz() {
		return nsrxxdz;
	}
	public void setNsrxxdz(String nsrxxdz) {
		this.nsrxxdz = nsrxxdz;
	}
	public String getQydjzclx() {
		return qydjzclx;
	}
	public void setQydjzclx(String qydjzclx) {
		this.qydjzclx = qydjzclx;
	}
	public String getKhyh() {
		return khyh;
	}
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getSfsjsp() {
		return sfsjsp;
	}
	public void setSfsjsp(String sfsjsp) {
		this.sfsjsp = sfsjsp;
	}
	public String getTdzldz() {
		return tdzldz;
	}
	public void setTdzldz(String tdzldz) {
		this.tdzldz = tdzldz;
	}
	public String getPzjdwh() {
		return pzjdwh;
	}
	public void setPzjdwh(String pzjdwh) {
		this.pzjdwh = pzjdwh;
	}
	public String getPzjdmj() {
		return pzjdmj;
	}
	public void setPzjdmj(String pzjdmj) {
		this.pzjdmj = pzjdmj;
	}
	public String getJsxmmc() {
		return jsxmmc;
	}
	public void setJsxmmc(String jsxmmc) {
		this.jsxmmc = jsxmmc;
	}
	public String getSjzdmj() {
		return sjzdmj;
	}
	public void setSjzdmj(String sjzdmj) {
		this.sjzdmj = sjzdmj;
	}
	public String getZdsj() {
		return zdsj;
	}
	public void setZdsj(String zdsj) {
		this.zdsj = zdsj;
	}
	public String getJmsyj() {
		return jmsyj;
	}
	public void setJmsyj(String jmsyj) {
		this.jmsyj = jmsyj;
	}
	public String getJmsyjdm() {
		return jmsyjdm;
	}
	public void setJmsyjdm(String jmsyjdm) {
		this.jmsyjdm = jmsyjdm;
	}
	
	public String getHj_jsmj() {
		return hj_jsmj;
	}
	public void setHj_jsmj(String hj_jsmj) {
		this.hj_jsmj = hj_jsmj;
	}
	public String getHj_jzse() {
		return hj_jzse;
	}
	public void setHj_jzse(String hj_jzse) {
		this.hj_jzse = hj_jzse;
	}
	public String getHj_jmmj() {
		return hj_jmmj;
	}
	public void setHj_jmmj(String hj_jmmj) {
		this.hj_jmmj = hj_jmmj;
	}
	public String getHj_jmse() {
		return hj_jmse;
	}
	public void setHj_jmse(String hj_jmse) {
		this.hj_jmse = hj_jmse;
	}
	public String getHj_ysmj() {
		return hj_ysmj;
	}
	public void setHj_ysmj(String hj_ysmj) {
		this.hj_ysmj = hj_ysmj;
	}
	public String getHj_ynse() {
		return hj_ynse;
	}
	public void setHj_ynse(String hj_ynse) {
		this.hj_ynse = hj_ynse;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getQrr() {
		return qrr;
	}
	public void setQrr(String qrr) {
		this.qrr = qrr;
	}
	
	public String getQrrq() {
		return qrrq;
	}
	public void setQrrq(String qrrq) {
		this.qrrq = qrrq;
	}
	public String getSjqrr() {
		return sjqrr;
	}
	public void setSjqrr(String sjqrr) {
		this.sjqrr = sjqrr;
	}
	public String getSjqrrq() {
		return sjqrrq;
	}
	public void setSjqrrq(String sjqrrq) {
		this.sjqrrq = sjqrrq;
	}
	public String getSjqrzt() {
		return sjqrzt;
	}
	public void setSjqrzt(String sjqrzt) {
		this.sjqrzt = sjqrzt;
	}
	public String getQrzt() {
		return qrzt;
	}
	public void setQrzt(String qrzt) {
		this.qrzt = qrzt;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Date getLrsj() {
		return lrsj;
	}
	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	public String getBzlxdm() {
		return bzlxdm;
	}
	public void setBzlxdm(String bzlxdm) {
		this.bzlxdm = bzlxdm;
	}
	public String getBzms() {
		return bzms;
	}
	public void setBzms(String bzms) {
		this.bzms = bzms;
	}
	public String[] getZdyt() {
		return zdyt;
	}
	public void setZdyt(String[] zdyt) {
		this.zdyt = zdyt;
	}
	public String[] getSyse() {
		return syse;
	}
	public void setSyse(String[] syse) {
		this.syse = syse;
	}
	public String[] getJsmj() {
		return jsmj;
	}
	public void setJsmj(String[] jsmj) {
		this.jsmj = jsmj;
	}
	public String[] getJzse() {
		return jzse;
	}
	public void setJzse(String[] jzse) {
		this.jzse = jzse;
	}
	public String[] getJmmj() {
		return jmmj;
	}
	public void setJmmj(String[] jmmj) {
		this.jmmj = jmmj;
	}
	public String[] getJmse() {
		return jmse;
	}
	public void setJmse(String[] jmse) {
		this.jmse = jmse;
	}
	public String[] getYnse() {
		return ynse;
	}
	public void setYnse(String[] ynse) {
		this.ynse = ynse;
	}
	public List getZdytbean() {
		return zdytbean;
	}
	public void setZdytbean(List zdytbean) {
		this.zdytbean = zdytbean;
	}
	public String[] getZdyt_sbmx() {
		return zdyt_sbmx;
	}
	public void setZdyt_sbmx(String[] zdyt_sbmx) {
		this.zdyt_sbmx = zdyt_sbmx;
	}
	public String[] getSyse_sbmx() {
		return syse_sbmx;
	}
	public void setSyse_sbmx(String[] syse_sbmx) {
		this.syse_sbmx = syse_sbmx;
	}
	public String[] getJsmj_sbmx() {
		return jsmj_sbmx;
	}
	public void setJsmj_sbmx(String[] jsmj_sbmx) {
		this.jsmj_sbmx = jsmj_sbmx;
	}
	public String[] getJzse_sbmx() {
		return jzse_sbmx;
	}
	public void setJzse_sbmx(String[] jzse_sbmx) {
		this.jzse_sbmx = jzse_sbmx;
	}
	public String[] getJmmj_sbmx() {
		return jmmj_sbmx;
	}
	public void setJmmj_sbmx(String[] jmmj_sbmx) {
		this.jmmj_sbmx = jmmj_sbmx;
	}
	public String[] getJmse_sbmx() {
		return jmse_sbmx;
	}
	public void setJmse_sbmx(String[] jmse_sbmx) {
		this.jmse_sbmx = jmse_sbmx;
	}
	public String[] getYnse_sbmx() {
		return ynse_sbmx;
	}
	public void setYnse_sbmx(String[] ynse_sbmx) {
		this.ynse_sbmx = ynse_sbmx;
	}
	public JMXXModel getJmxxModel() {
		return jmxxModel;
	}
	public void setJmxxModel(JMXXModel jmxxModel) {
		this.jmxxModel = jmxxModel;
	}
	public String[] getJmlbdm_jmmx() {
		return jmlbdm_jmmx;
	}
	public void setJmlbdm_jmmx(String[] jmlbdm_jmmx) {
		this.jmlbdm_jmmx = jmlbdm_jmmx;
	}
	public String[] getJmmj_jmmx() {
		return jmmj_jmmx;
	}
	public void setJmmj_jmmx(String[] jmmj_jmmx) {
		this.jmmj_jmmx = jmmj_jmmx;
	}
	public String[] getJmse_jmmx() {
		return jmse_jmmx;
	}
	public void setJmse_jmmx(String[] jmse_jmmx) {
		this.jmse_jmmx = jmse_jmmx;
	}
	public String[] getFljmmj() {
		return fljmmj;
	}
	public void setFljmmj(String[] fljmmj) {
		this.fljmmj = fljmmj;
	}
	public String[] getFljmse() {
		return fljmse;
	}
	public void setFljmse(String[] fljmse) {
		this.fljmse = fljmse;
	}
    
    
  
    
    
    
   
   
   
}
