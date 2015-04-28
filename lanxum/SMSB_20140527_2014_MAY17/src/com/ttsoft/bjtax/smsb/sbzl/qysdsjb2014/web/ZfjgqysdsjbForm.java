package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: 企业所得税季报2014版-汇总纳税分支机构分配表Form</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author zhangyj
 * @version 1.0
 */
import java.util.*;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.*;


public class ZfjgqysdsjbForm extends QysdsNewForm
{
    /**
     * 所得税年报列表分支机构标题项目代码 纳税人识别号、分支机构名称、收入总额、工资总额、资产总额、分配比例、分配税额 String[]
     */
    private String[] fzjg_columns = { "fzjgnsrsbh", "fzjgmc", "fzjgsrze", "fzjggzze", "fzjgzcze", "fzjgfpbl", "fzjgfpse" };

    /**
     * 所得税季报数据
     */
    private List qysdsjbList = new ArrayList();

    /**
     * 分配比例有效期起
     */
    private String fpblyxqq;

    /**
     * 分配比例有效期止
     */
    private String fpblyxqz;

    /**
     * 总机构收入总额
     */
    private String srze;

    /**
     * 总机构工资总额
     */
    private String gzze;

    /**
     * 总机构资产总额
     */
    private String zcze;

    /**
     * 总机构合计
     */
    private String hj;


    /**
     * 明细数据据最大index
     */
    private int maxIndex;

    /**
     * 减免资格
     */
    private String jmzg;

    /**
     * 一般减免税率
     */
    private String ybjmsl;

    /**
     * 乡镇企业
     */
    private String xzqy;

    /**
     * 机构类型
     */
    private String jglx;

	/**
     * 应纳所得税额
     */
    private String ynsdse;    
    
    /**
     * 总机构分摊所得税额
     */
    private String zjgftse;       
    
    /**
     * 总机构财政集中分配所得税额
     */
    private String zjgfpse;   
    
    /**
     * 分支机构分摊所得税额
     */
    private String fzjgftse;   
    
    /**
     * 分支机构分配比例
     */
    private String fzjgfpbl;  
 
    /**
     * 分支机构分配税额
     */
    private String fzjgfpse; 
    
    /**
     * 总机构名称
     */
    private String zjgmc;  

	/**
     * 收入额合计
     */
    private String srehj;
    
    /**
     * 工资额合计
     */
    private String gzehj;
    
    /**
     * 资产额合计
     */
    private String zcehj;
    
    /**
     * 分配比例合计
     */
    private String fpblhj;
    
    /**
     * 分配税额合计
     */
    private String fpsehj; 

	/**
     * 分支机构纳税人识别号
     */
    private String fzjgnsrsbh;
    
    /**
     * 分支机构纳税人名称
     */
    private String fzjgnsrmc;
    //保存标识，0为保存失败，1为保存成功，added by zhangj 2014.11.28
    private String SAVE_FLAG="0";
    public String getZjgmc() {
		return zjgmc;
	}

	public void setZjgmc(String zjgmc) {
		this.zjgmc = zjgmc;
	}

	public ZfjgqysdsjbForm()
    {
    }

    public void setFzjgColumns(String[] column)
    {
        this.fzjg_columns = column;
    }

    public String[] getFzjgColumns()
    {
        return this.fzjg_columns;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public List getQysdsjbList()
    {
        return qysdsjbList;
    }

    public String getSrze()
    {
        return srze;
    }

    public String getZcze()
    {
        return zcze;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
    }

    public int getMaxIndex()
    {
        return maxIndex;
    }

    public String getXzqy()
    {
        return xzqy;
    }

    public String getYbjmsl()
    {
        return ybjmsl;
    }

    public String getJmzg()
    {
        return jmzg;
    }
    
    public String getJglx()
    {
        return jglx;
    }    

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setQysdsjbList(List qysdsjbList)
    {
        this.qysdsjbList = qysdsjbList;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setMaxIndex(int maxIndex)
    {
        this.maxIndex = maxIndex;
    }

    public void setYbjmsl(String ybjmsl)
    {
        this.ybjmsl = ybjmsl;
    }

    public void setXzqy(String xzqy)
    {
        this.xzqy = xzqy;
    }

    public void setJmzg(String jmzg)
    {
        this.jmzg = jmzg;
    }

    public void setJglx(String jglx)
    {
        this.jglx = jglx;
    }

    public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getZjgftse() {
		return zjgftse;
	}

	public void setZjgftse(String zjgftse) {
		this.zjgftse = zjgftse;
	}

	public String getZjgfpse() {
		return zjgfpse;
	}

	public void setZjgfpse(String zjgfpse) {
		this.zjgfpse = zjgfpse;
	}

	public String getFzjgftse() {
		return fzjgftse;
	}

	public void setFzjgftse(String fzjgftse) {
		this.fzjgftse = fzjgftse;
	}

	public String getFzjgfpbl() {
		return fzjgfpbl;
	}

	public void setFzjgfpbl(String fzjgfpbl) {
		this.fzjgfpbl = fzjgfpbl;
	}

	public String getFzjgfpse() {
		return fzjgfpse;
	}

	public void setFzjgfpse(String fzjgfpse) {
		this.fzjgfpse = fzjgfpse;
	}
	
	
    public String getSrehj() {
		return srehj;
	}

	public void setSrehj(String srehj) {
		this.srehj = srehj;
	}

	public String getGzehj() {
		return gzehj;
	}

	public void setGzehj(String gzehj) {
		this.gzehj = gzehj;
	}

	public String getZcehj() {
		return zcehj;
	}

	public void setZcehj(String zcehj) {
		this.zcehj = zcehj;
	}

	public String getFpblhj() {
		return fpblhj;
	}

	public void setFpblhj(String fpblhj) {
		this.fpblhj = fpblhj;
	}

	public String getFpsehj() {
		return fpsehj;
	}

	public void setFpsehj(String fpsehj) {
		this.fpsehj = fpsehj;
	}	
    
    public String getFzjgnsrsbh() {
		return fzjgnsrsbh;
	}

	public void setFzjgnsrsbh(String fzjgnsrsbh) {
		this.fzjgnsrsbh = fzjgnsrsbh;
	}

	public String getFzjgnsrmc() {
		return fzjgnsrmc;
	}

	public void setFzjgnsrmc(String fzjgnsrmc) {
		this.fzjgnsrmc = fzjgnsrmc;
	}

	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	public void setSAVE_FLAG(String sAVEFLAG) {
		SAVE_FLAG = sAVEFLAG;
	}
	
}
