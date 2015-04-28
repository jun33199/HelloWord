package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;


/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票号段录入功能form类:FplrForm </p>
 * @author tutu
 * @version 1.1
 */
public class FplrForm extends BaseForm {
	
	
	
	/**
     * 发票库房代码
     */
    public String fpkfdm;
    
    
    /**
     * 用户ID
     */
    public String yhid;
    
    /**
     * 票证账户代码
     */
    public String zhdm;
    
    /**
     * 税务机关组织机构代码
     */
    public String swjgzzjgdm;
    
	/**
     * 发票种类代码数组
     */
    public String[] fpzldm = { "" };
    
    /**
     * 发票种类名称数组
     */
    public String[] fpzlmc = { "" };
    
    /**
     * 发票起始号码
     */
    public String[] fpqzhm = { "" };
    
    /**
     * 发票截止号码
     */
    public String[] fpjzhm = { "" };
    
    /**
     * 发票起始号码
     */
    public String[] fpkcqzhm = { "" };
    
    /**
     * 发票截止号码
     */
    public String[] fpkcjzhm = { "" };
    
    /**
     * 发票数量
     */
    public long[] sl;
    
    /**
     * 录入人
     */
    public String lrr;
    
    /**
     * 录入日期
     */
    public String lrrq;
    
    /**
     * 发票种类长度
     */
    public String fphmcd;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * fplrList
     */
    public ArrayList fplrList = new ArrayList();
    
    
	public ArrayList getFpzlList() {
		return fpzlList;
	}

	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
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

	public String[] getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String[] fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String[] getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(String[] fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public String[] getFpqzhm() {
		return fpqzhm;
	}

	public void setFpqzhm(String[] fpqzhm) {
		this.fpqzhm = fpqzhm;
	}

	public String[] getFpjzhm() {
		return fpjzhm;
	}

	public void setFpjzhm(String[] fpjzhm) {
		this.fpjzhm = fpjzhm;
	}

	public String[] getFpkcqzhm() {
		return fpkcqzhm;
	}

	public void setFpkcqzhm(String[] fpkcqzhm) {
		this.fpkcqzhm = fpkcqzhm;
	}

	public String[] getFpkcjzhm() {
		return fpkcjzhm;
	}

	public void setFpkcjzhm(String[] fpkcjzhm) {
		this.fpkcjzhm = fpkcjzhm;
	}


	public long[] getSl() {
		return sl;
	}

	public void setSl(long[] sl) {
		this.sl = sl;
	}

	public void setFplrList(ArrayList fplrList) {
		this.fplrList = fplrList;
	}
	
	/**
     * 将页面的数组转换为ArrayList返回
     *
     * @return ArrayList 缴款书列表
     */
    public ArrayList getFplrList()
    {
    	fplrList = new ArrayList();

        int len = fpqzhm.length;

        for (int i = 0; i < len; i++)
        {
        	Fpkc fpkcItem = new Fpkc();
        	fpkcItem.setFpkfdm(this.fpkfdm);
        	fpkcItem.setFpzldm(fpzldm[i]);
        	fpkcItem.setQshm(fpqzhm[i]);
        	fpkcItem.setJzhm(fpjzhm[i]);
        	fpkcItem.setSl(BigDecimal.valueOf(sl[i]));

            fplrList.add(fpkcItem);
        }

        return fplrList;
    }

	public String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getZhdm() {
		return zhdm;
	}

	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getFphmcd() {
		return fphmcd;
	}

	public void setFphmcd(String fphmcd) {
		this.fphmcd = fphmcd;
	}
}
