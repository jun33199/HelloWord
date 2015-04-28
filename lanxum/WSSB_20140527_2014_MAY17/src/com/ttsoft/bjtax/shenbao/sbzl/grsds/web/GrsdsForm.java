package com.ttsoft.bjtax.shenbao.sbzl.grsds.web;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsz;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * @author Ding Chenggang
 * @version 1.0
 */
public class GrsdsForm extends SbzlBaseForm
{
    public GrsdsForm()
    {
    }

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 申报所属时期
     */
    private String sbsssq;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 本期单位总人数
     */
    private String bqdwzrs;

    /**
     * 本期非单位总人数
     */
    private String bqfdwzrs;

    /**
     * 国籍代码
     */
    private String gjdm[];

    /**
     * 证件类型代码
     */
    private String zjlxdm[];

    /**
     * 证件号码
     */
    private String zjhm[];

    /**
     * 姓名
     */
    private String xm[];

    /**
     * 职业代码
     */
    private String zydm[];

    /**
     * 税种税目代码
     */
    private String szsmdm[];

    /**
     * 收入额
     */
    private String sre[];

    /**
     * 应纳税所得额
     */
    private String ynssde[];

    /**
     * 税率
     */
    private String sl[];

    /**
     * 减免税额
     */
    private String jmje[];

    /**
     * 本期实扣税额
     */
    private String bqskse[];

    /**
     * 完税证号
     */
    private String wszh[];

    /**
     * 个人所得税列表
     */
    private List mxList;

    /**
     * 定额列表
     */
    private List deMxList;

    /**
     * 国籍列表
     */
    private List gjList;

    /**
     * 税种税目列表
     */
    private List szsmList;

    /**
     * 职业列表
     */
    private List zyList;

    /**
     * 证件类型列表
     */
    private List zjlxList;

    /**
     * Sets the value of the gjdm property.
     *
     * @param aGjdm the new value of the gjdm property
     */
    public void setGjdm(String[] aGjdm)
    {
        gjdm = aGjdm;
    }

    /**
     * Sets the value of the zjlxdm property.
     *
     * @param aZjlxdm the new value of the zjlxdm property
     */
    public void setZjlxdm(String[] aZjlxdm)
    {
        zjlxdm = aZjlxdm;
    }

    /**
     * Sets the value of the zjhm property.
     *
     * @param aZjhm the new value of the zjhm property
     */
    public void setZjhm(String[] aZjhm)
    {
        zjhm = aZjhm;
    }

    /**
     * Sets the value of the xm property.
     *
     * @param aXm the new value of the xm property
     */
    public void setXm(String[] aXm)
    {
        xm = aXm;
    }

    /**
     * Sets the value of the zydm property.
     *
     * @param aZydm the new value of the zydm property
     */
    public void setZydm(String[] aZydm)
    {
        zydm = aZydm;
    }

    /**
     * Sets the value of the szsmdm property.
     *
     * @param aSzsmdm the new value of the szsmdm property
     */
    public void setSzsmdm(String[] aSzsmdm)
    {
        szsmdm = aSzsmdm;
    }

    /**
     * Sets the value of the sre property.
     *
     * @param aSre the new value of the sre property
     */
    public void setSre(String[] aSre)
    {
        sre = aSre;
    }

    /**
     * Sets the value of the ynssde property.
     *
     * @param aYnssde the new value of the ynssde property
     */
    public void setYnssde(String[] aYnssde)
    {
        ynssde = aYnssde;
    }

    /**
     * Sets the value of the sl property.
     *
     * @param aSl the new value of the sl property
     */
    public void setSl(String[] aSl)
    {
        sl = aSl;
    }

    /**
     * Sets the value of the jmje property.
     *
     * @param aJmje the new value of the jmje property
     */
    public void setJmje(String[] aJmje)
    {
        jmje = aJmje;
    }

    /**
     * Sets the value of the bqskse property.
     *
     * @param aBqskse the new value of the bqskse property
     */
    public void setBqskse(String[] aBqskse)
    {
        bqskse = aBqskse;
    }

    /**
     * Sets the value of the wszh property.
     *
     * @param aWszh the new value of the wszh property
     */
    public void setWszh(String[] aWszh)
    {
        wszh = aWszh;
    }

    /**
     * Access method for the mxList property.
     *
     * @return   the current value of the mxList property
     */
    public List getMxList()
    {
        return mxList;
    }

    /**
     * 取得个数明细数据
     *
     * @param request HttpServletRequest
     * @param cjsj 创建时间
     * @return java.util.List
     */
    public List getGsmx(HttpServletRequest request, Timestamp cjsj)
        throws BaseException
    {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);

        String _jsjdm = FriendHelper.getUserData(request).yhid;

        String nd = new SimpleDateFormat("yyyy").format(cjsj);

        String swjgzzjgdm = FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm();

        int size = this.szsmdm.length;
        List mxList = new ArrayList(size);

        for(int i = 0; i < size; i++)
        {
            Grsdsmx mx = new Grsdsmx();

            Szsm szsm = (Szsm)szsmMap.get(this.szsmdm[i]);

            mx.setZjlxdm(this.zjlxdm[i]);
            mx.setGjdm(this.gjdm[i]);
            mx.setZjhm(this.zjhm[i]);
            mx.setSzsmdm(this.szsmdm[i]);
            mx.setJsjdm(_jsjdm);
            mx.setSbrq(TinyTools.second2Day(cjsj));
            mx.setCjsj(cjsj);
            mx.setLrrq(cjsj);
            mx.setXm(xm[i]);
            mx.setZydm(zydm[i]);
            mx.setSre(new BigDecimal(this.sre[i]));
            mx.setYnssde(new BigDecimal(this.ynssde[i]));
            mx.setSl(szsm.getSl());
            mx.setJmje(new BigDecimal(this.jmje[i]));
            mx.setBqskse(new BigDecimal(this.bqskse[i]));
            mx.setWszh(this.wszh[i]);
            mx.setNd(nd);
            mx.setSwjgzzjgdm(swjgzzjgdm);

            mxList.add(mx);
        }

        return mxList;
    }

    public void setMxList(List mxList)
    {
        this.mxList = mxList;
    }

    public String getSbrq()
    {
        return sbrq;
    }
    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getBqdwzrs()
    {
        return bqdwzrs;
    }

    public void setBqdwzrs(String bqdwzrs)
    {
        this.bqdwzrs = bqdwzrs;
    }

    public String getBqfdwzrs()
    {
        return bqfdwzrs;
    }

    public void setBqfdwzrs(String bqfdwzrs)
    {
        this.bqfdwzrs = bqfdwzrs;
    }

    public List getZyList()
    {
        return zyList;
    }

    public void setZyList(List zyList)
    {
        this.zyList = zyList;
    }

    public List getZjlxList()
    {
        return zjlxList;
    }

    public void setZjlxList(List zjlxList)
    {
        this.zjlxList = zjlxList;
    }

    public List getSzsmList()
    {
        return szsmList;
    }

    public void setSzsmList(List szsmList)
    {
        this.szsmList = szsmList;
    }

    public List getGjList()
    {
        return gjList;
    }

    public void setGjList(List gjList)
    {
        this.gjList = gjList;
    }

    public String[] getGjdm()
    {
        return gjdm;
    }

    public String[] getJmje()
    {
        return jmje;
    }

    public String[] getSl()
    {
        return sl;
    }

    public String[] getZydm()
    {
        return zydm;
    }

    public String[] getZjlxdm()
    {
        return zjlxdm;
    }

    public String[] getZjhm()
    {
        return zjhm;
    }

    public String[] getYnssde()
    {
        return ynssde;
    }

    public String[] getXm()
    {
        return xm;
    }

    public String[] getWszh()
    {
        return wszh;
    }

    public String[] getSzsmdm()
    {
        return szsmdm;
    }

    public String[] getSre()
    {
        return sre;
    }

    public String[] getBqskse()
    {
        return bqskse;
    }
    public String getSbsssq()
    {
        return sbsssq;
    }
    public void setSbsssq(String sbsssq)
    {
        this.sbsssq = sbsssq;
    }
    public List getDeMxList()
    {
        return deMxList;
    }
    public void setDeMxList(List deMxList)
    {
        this.deMxList = deMxList;
    }
}