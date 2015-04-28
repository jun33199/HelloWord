package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web;


/**
 * <p>Title: 企业所得税季报2008版-汇总纳税分支机构分配表Form</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.*;


public class ZfjgqysdsjbForm extends QysdsNewForm
{
    /**
     * 所得税年报列表分支机构标题项目代码 纳税人识别号、分支机构名称、收入总额、工资总额、资产总额、合计、分配比例、分配税额 String[]
     */
    private String[] fzjg_columns = { "fzjgnsrsbh", "fzjgmc", "fzjgsrze", "fzjggzze", "fzjgzcze", "fzjghj", "fzjgfpbl", "fzjgfpse" };

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
     * 分支机构分摊的所得税额
     */
    private String ftse;

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

    public String getFtse()
    {
        return ftse;
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

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
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
}
