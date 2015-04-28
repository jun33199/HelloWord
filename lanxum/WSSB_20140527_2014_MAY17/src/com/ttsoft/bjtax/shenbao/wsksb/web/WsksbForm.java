package com.ttsoft.bjtax.shenbao.wsksb.web;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.common.model.UserData;
import java.util.Map;
import java.util.HashMap;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.wsksb.SessionKey;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import java.util.List;

public class WsksbForm extends BaseForm
{
    public WsksbForm()
    {
    }

    /**
     * 税务计算机代码
     */
    private String jsjdm;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 申报编号
     */
    private String sbbh;

    /**
     * 税款所属开始所属日期
     */
    private String skssksrq;

    /**
     * 税款所属结束日期
     */
    private String skssjsrq;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 年度
     */
    private String nd;

    /**
     * 年度
     */
    private String bz;

    /**
     *  纳税人名称
     */
    private String nsrmc;

    /**
     *  税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     *  提示信息
     */
    private List msg;
//    private String msg;


    /**
     *  是否可以保存
     */
    private int canSave;

    /**
     *  是否可以删除
     */
    private int canDel;

    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getLxdh()
    {
        return lxdh;
    }
    public String getNd()
    {
        return nd;
    }
    public String getNsrmc()
    {
        return nsrmc;
    }
    public String getSbrq()
    {
        return sbrq;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    /**
     * 使用key为SessionKey.JSJDM放入计算机代码，生成Map。
     * 构造VOPackage，设置action= YhsActionConstant.INT_ACTION_QUERY传递给processor
     * @param userData UserData
     * @param djMap Map
     * @return VOPackage
     */
    public VOPackage getQueryData(UserData userData, Map data, int actionId)
    {
        VOPackage voPackage = new VOPackage();
        voPackage.setData(data);
        voPackage.setAction(actionId);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);
        return voPackage;
    }

    public int getCanSave()
    {
        return canSave;
    }
    public int getCanDel()
    {
        return canDel;
    }
    public void setCanDel(int canDel)
    {
        this.canDel = canDel;
    }
    public void setCanSave(int canSave)
    {
        this.canSave = canSave;
    }
    public String getBz()
    {
        return bz;
    }
    public void setBz(String bz)
    {
        this.bz = bz;
    }
    public String getSbbh()
    {
        return sbbh;
    }
    public void setSbbh(String sbbh)
    {
        this.sbbh = sbbh;
    }
    public List getMsg()
    {
        return msg;
    }
    public void setMsg(List msg)
    {
        this.msg = msg;
    }


}