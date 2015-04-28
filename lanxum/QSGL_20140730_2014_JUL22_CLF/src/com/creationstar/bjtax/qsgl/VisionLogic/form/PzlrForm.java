package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * 批量软件配置录入form
 * @author not attributable
 * @version 1.0
 */

public class PzlrForm extends BaseForm {
    //主键id
    private String id;
    //删除按钮标志，true为显示，false为隐藏
    private String del_flag;
    //纳税人组织机构代码
    private String zzjgdm;
    //纳税人组织机构名称
    private String zzjgmc;
    //房地产项目名称
    private String fdcxmmc;
    //土地级别
    private String tdjb;
    //容积率
    private String rjl;
    //建筑面积
    private String jzmj;
    //平均交易价格
    private String pjjyjg;
    /*added by gaoyh to 20141016*/
    //房屋每套价格
    private String fwmtjg;
	//使用起始日期
    private String qsrq;
    //使用结束日期
    private String jzrq;
    //是否可操作拆迁信息
    private String czcq;
    //是否可操作已购公有住房出售信息
    private String czcsxx;
    //是否可操作房屋交易信息
    private String czfwjyxx;
    //操作人员
    private String czry;
    //税务机关组织机构代码
    private String swjgzzjgdm;
    //税务机关组织机构名称
    private String swjgzzjgmc;
    /**
     * 土地级次列表
     */
    private ArrayList tdjcList = new ArrayList();


    public String getCzcq() {
        return czcq;
    }

    public String getCzcsxx() {
        return czcsxx;
    }

    public String getCzfwjyxx() {
        return czfwjyxx;
    }

    public String getCzry() {
        return czry;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getJzmj() {
        return jzmj;
    }

    public String getJzrq() {
        return jzrq;
    }

    public String getPjjyjg() {
        return pjjyjg;
    }

    public String getQsrq() {
        return qsrq;
    }

    public String getRjl() {
        return rjl;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getTdjb() {
        return tdjb;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public String getZzjgmc() {
        return zzjgmc;
    }

    public String getId() {
        return id;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public ArrayList getTdjcList() {
        return tdjcList;
    }

    public void setCzcq(String czcq) {
        this.czcq = czcq;
    }

    public void setCzcsxx(String czcsxx) {
        this.czcsxx = czcsxx;
    }

    public void setCzfwjyxx(String czfwjyxx) {
        this.czfwjyxx = czfwjyxx;
    }

    public void setCzry(String czry) {
        this.czry = czry;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public void setPjjyjg(String pjjyjg) {
        this.pjjyjg = pjjyjg;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setTdjb(String tdjb) {
        this.tdjb = tdjb;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public void setZzjgmc(String zzjgmc) {
        this.zzjgmc = zzjgmc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public void setTdjcList(ArrayList tdjcList) {
        this.tdjcList = tdjcList;
    }
    
    public String getFwmtjg() {
		return fwmtjg;
	}

	public void setFwmtjg(String fwmtjg) {
		this.fwmtjg = fwmtjg;
	}
}
