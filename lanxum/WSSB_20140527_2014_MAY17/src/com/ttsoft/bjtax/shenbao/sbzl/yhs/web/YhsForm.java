package com.ttsoft.bjtax.shenbao.sbzl.yhs.web;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.Skssrq;

import java.util.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * @author zhiyong He
 * @version 1.0
 * 印花税Form
 */
public class YhsForm extends SbzlBaseForm
{

    /**
     * 税款所属开始所属日期
     */
    private String skssksrq;

    /**
     * 税款所属结束日期
     */
    private String skssjsrq;

    /**
     * 税务计算机代码
     */
    private String jsjdm;

    /**
     * 税种税目代码
     */
    private String[] ssrq;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 合计份数
     */
    private String hjfs;

    /**
     * 合计计税金额
     */
    private String hjjsje;

    /**
     * 合计已纳税额
     */
    private String hjynse;

    /**
     * 印花税报告明细数据
     * 存放印花税明细值对象
     */
    private List yhsbgmxList;

    /**
     * 印花税税种税目信息List
     */
    private List yhsSzsmInfoList;

    /**
     * 税目
     */
    private String[] sm;

    /**
     * 份数
     */
    private String[] fs;

    /**
     * 计税金额
     */
    private String[] jsje;

    /**
     * 税种税目数据
     * 存放税种税目值对象
     */
    private List szsmList;

    /**
     * 税率
     */
    private String[] sl;

    /**
     * 已纳税额
     */
    private String[] ynse;

    /**
     * 印花税报告主数据
     * 存放印花税主数据值对象
     */
    private Yhsbgz yhsbgz;

    /**
     * 纳税人资料值对象
     */
    private SWDJJBSJ nsrzl;

    /**
     * 年度
     */
    private String nd;
    /**
     *  纳税人名称
     */
    private String nsrmc;
    /**
     *  税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     * 税种税目代码数组
     */
    private String[] szsmdm;

    public YhsForm()
    {

    }

    /**
     * 使用key为YhsMapConstant.JSJDM放入计算机代码，生成Map。
     * 构造VOPackage，设置action= YhsActionConstant.INT_ACTION_QUERY传递给processor
     * @param userData UserData
     * @param djMap Map
     * @return VOPackage
     */
    public VOPackage getQueryData(UserData userData, Map djMap)
    {
        Map data = new HashMap();
        data.put(YhsMapConstant.JSJDM, userData.yhid);
        data.put(YhsMapConstant.MAP_DJSJ, djMap);
        VOPackage voPackage = new VOPackage();
        voPackage.setData(data);
        voPackage.setAction(YhsActionConstant.INT_ACTION_QUERY);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);
        return voPackage;
    }

    /**
     * 取印花税报告明细数据用YhsMapConstant.LIST_YHSBGMX放入yhsbgmxList
     * 取印花税报告主数据用YhsMapConstant.LIST_YHSBG放入yhsbgList
     * 取税种税目数据用YhsMapConstant.LIST_SZSM放入szsmList
     * 放入纳税人资料用YhsMapConstant.NSRZL放入nsrzl
     * 设置form中的数据，显示页面
     *
     * jsjdm = nsrzl.jsjdm;
     * nsrmc = nsrzl.nsrmc;
     * lxdh = nsrzl.lxdh;
     * hjfs = yhszsj.hjfs;
     * hjjsje = yhszsj.hjjsje;
     * hjynse = yhszsj.hjynse;
     * szsmdm[] = yhsmxsj.szsmdm;
     * fs = yhsmxsj.fs;
     * jsje = yhsmxsj.jsje;
     * sl = yhsmxsj.sl;
     * sjse = yhsmxsj.sjse;
     *
     * 印花税报告主数据
     * 计算机代码	JSJDM	VARCHAR2(8)
     * 年度	ND	VARCHAR2(4)
     * 税款所属开始日期	SKSSKSRQ	DATE
     * 税款所属结束日期	SKSSJSRQ	DATE
     * 纳税人名称	NSRMC	VARCHAR2(200)
     * 企业联系电话	QYLXDH	VARCHAR2(20)
     * 合计份数	HJFS	NUMBER(15,2)
     * 合计计税金额	HJJSJE	NUMBER(15,2)
     * 合计已纳税额	HJYNSE	NUMBER(15,2)
     * 税务机关组织机构代码	SWJGZZJGDM	VARCHAR2(8)
     * 税务机关组织机构名称	SWJGZZJGMC	VARCHAR2(60)
     * 录入人代码	LRR	VARCHAR2(30)
     * 备注	BZ	VARCHAR2(100)
     * 税务机关组织机构代码2	SWJGZZJGDM2	VARCHAR2(8)
     *
     * 印花税报告明细数据
     * 计算机代码	JSJDM	VARCHAR2(8)
     * 年度	ND	VARCHAR2(4)
     * 税种税目代码	SZSMDM	 VARCHAR2(9)
     * 税种代码	SZDM	VARCHAR2(9)
     * 税种名称	SZMC	VARCHAR2(60)
     * 税种税目名称	SZSMMC	VARCHAR2(60)
     * 份数	FS	NUMBER(15,2)
     * 计税金额	JSJE	NUMBER(15,2)
     * 税率	SL	NUMBER(10,5)
     * 实缴税额	SJSE	NUMBER(15,2)
     * 备注	BZ	VARCHAR2(100)
     * @param voPackage VOPackage
     */
    public void setFormData(VOPackage voPackage)
    {
        Map data = (Map)voPackage.getData();
        this.nsrzl = (SWDJJBSJ)data.get(YhsMapConstant.JBSJ);
        this.yhsbgz = (Yhsbgz)data.get(YhsMapConstant.YHSBGZ);
        this.yhsbgmxList = (List)data.get(YhsMapConstant.LIST_YHSBGMX);
        this.yhsSzsmInfoList = (List)data.get(YhsMapConstant.LIST_SZSM);
        // 因为页面显示需要设置印花税报告明细的SZSMMC
        for(int i=0; i<this.yhsbgmxList.size(); i++)
        {
           ((Yhsbgmx)this.yhsbgmxList.get(i)).setSzsmmc(((Szsm)(this.yhsSzsmInfoList.get(i))).getSzsmmc());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.skssksrq = sdf.format(this.yhsbgz.getSkssksrq());
        this.skssjsrq = sdf.format(this.yhsbgz.getSkssjsrq());
        this.nsrmc = this.nsrzl.getNsrmc();
        this.hjfs = this.yhsbgz.getHjfs()==null?null:this.yhsbgz.getHjfs().toString();
        this.hjjsje = this.yhsbgz.getHjjsje()==null?null:this.yhsbgz.getHjjsje().toString();
        this.hjynse = this.yhsbgz.getHjynse()==null?null:this.yhsbgz.getHjynse().toString();
        this.jsjdm = this.nsrzl.getJsjdm();
        this.lxdh = this.nsrzl.getJydzlxdm();
        this.nd = this.yhsbgz.getNd();
        this.swjgzzjgdm = this.yhsbgz.getSwjgzzjgdm();
    }

    /**
     * 使用key为YhsMapConstant.LIST_YHSBGMX放印花税报告明细数据，
     * 使用key为YhsMapConstant.LIST_YHSBG放印花税报告主数据yhsbgList
     * 使用key为YhsMapConstant.JSJDM取计算机代码，生成Map。
     * 构造VOPackage，action= YhsActionConstant.INT_ACTION_SAVE传递给processor
     *
     * 生成值对象List字段对应参考afterQuery方法注释
     * @param userData UserData
     * @return VOPackage
     */
    public VOPackage getSbData(UserData userData, SWDJJBSJ jbsj)
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        VOPackage voPackage = new VOPackage();
        Map data = new HashMap();

        // 印花税年报明细的设置
        this.yhsbgmxList = new ArrayList();
        // 去掉空格
        this.strTrim();
        for(int i = 0; i < this.szsmdm.length; i++)
        {
            Yhsbgmx mx = new Yhsbgmx();
            // 如果该条印花税税目是按份数×单位税额计算已纳税额
            if(this.szsmdm[i].equals(SzsmdmConstant.YHS_QTZB) ||
               this.szsmdm[i].equals(SzsmdmConstant.YHS_QLXKZZ))
            {
                mx.setFs(strToBigDecimal(this.fs[i]));
                mx.setSl(strToBigDecimal(this.sl[i]));

                mx.setSjse(strToBigDecimal(this.ynse[i]));
                mx.setJsje(strToBigDecimal(this.jsje[i]));
            }
            //如果该条印花税税目是按计税金额×税率计算已纳税额
            else
            {
                mx.setFs(strToBigDecimal(this.fs[i]));
                mx.setSl(strToBigDecimal(this.sl[i]));
                mx.setJsje(strToBigDecimal(this.jsje[i]));
                mx.setSjse(strToBigDecimal(this.ynse[i]));
            }
            mx.setJsjdm(this.jsjdm);
            mx.setNd(this.nd);
            mx.setSzdm(SzsmdmConstant.YHS);
            mx.setSzsmdm(this.szsmdm[i]);
            mx.setSwjgzzjgdm(this.swjgzzjgdm);
            mx.setLrrq(now);
            mx.setCjrq(now);
            mx.setQxdm(this.swjgzzjgdm.substring(0, 2));

            this.yhsbgmxList.add(mx);
        }
        // 印花税年度报告设置
        this.yhsbgz = new Yhsbgz();
        this.yhsbgz.setFsdm(CodeConstant.FSDM_WSSB);
        this.yhsbgz.setHjfs(strToBigDecimal(this.hjfs));
        this.yhsbgz.setHjjsje(strToBigDecimal(this.hjjsje));
        this.yhsbgz.setHjynse(strToBigDecimal(this.hjynse));
        this.yhsbgz.setJsjdm(this.jsjdm);
        this.yhsbgz.setLrr(userData.yhid);
        this.yhsbgz.setNd(this.nd);
        Map skssrqMap = this.getSkssrq();
        this.yhsbgz.setSkssjsrq( (Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        this.yhsbgz.setSkssksrq( (Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        this.yhsbgz.setSwjgzzjgdm(this.swjgzzjgdm);

        this.yhsbgz.setLrrq(now);
        this.yhsbgz.setCjrq(now);
        this.yhsbgz.setQxdm(this.swjgzzjgdm.substring(0, 2));

        data.put(YhsMapConstant.YHSBGZ, this.yhsbgz);
        data.put(YhsMapConstant.LIST_YHSBGMX, this.yhsbgmxList);
        data.put(YhsMapConstant.JBSJ, jbsj);

        voPackage.setData(data);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);
        return voPackage;
    }

    // 将字符串数值转换为BigDecimal
    private BigDecimal strToBigDecimal(String value)
    {
        if(value != null && !value.trim().equals(""))
        {
            return new BigDecimal(value);
        }
        else
        {
            return null;
        }
    }

    // 去掉所有用户提交的数据的两边的空格，以防万一
    private void strTrim()
    {
        for(int i=0; i<this.fs.length; i++)
        {
             this.fs[i] = this.fs[i].trim();
             if(this.fs[i].equals(""))
             {
                 this.fs[i] = "0";
             }
             this.jsje[i] = this.jsje[i].trim();
             this.sl[i] = this.sl[i].trim();
             // 税率有可能为空字符
             if(this.sl[i].equals(""))
             {
                this.sl[i]="0.00";
             }
             this.ynse[i] = this.ynse[i].trim();
        }
    }
    // 计算税款所属日期
    private Map getSkssrq()
    {
        return Skssrq.yearSkssrq(new Date());
    }

    public String[] getFs()
    {
        return fs;
    }

    public void setFs(String[] fs)
    {
        this.fs = fs;
    }

    public String getHjfs()
    {
        return hjfs;
    }

    public void setHjfs(String hjfs)
    {
        this.hjfs = hjfs;
    }

    public String getHjjsje()
    {
        return hjjsje;
    }

    public void setHjjsje(String hjjsje)
    {
        this.hjjsje = hjjsje;
    }

    public String getHjynse()
    {
        return hjynse;
    }

    public void setHjynse(String hjynse)
    {
        this.hjynse = hjynse;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String[] getJsje()
    {
        return jsje;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public String getLxdh()
    {
        return lxdh;
    }

    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }

    public SWDJJBSJ getNsrzl()
    {
        return nsrzl;
    }

    public void setNsrzl(SWDJJBSJ nsrzl)
    {
        this.nsrzl = nsrzl;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String[] getSl()
    {
        return sl;
    }

    public void setSl(String[] sl)
    {
        this.sl = sl;
    }

    public String[] getSm()
    {
        return sm;
    }

    public void setSm(String[] sm)
    {
        this.sm = sm;
    }

    public String[] getSsrq()
    {
        return ssrq;
    }

    public void setSsrq(String[] ssrq)
    {
        this.ssrq = ssrq;
    }

    public List getSzsmList()
    {
        return szsmList;
    }

    public void setSzsmList(List szsmList)
    {
        this.szsmList = szsmList;
    }

    public List getYhsbgmxList()
    {
        return yhsbgmxList;
    }

    public void setYhsbgmxList(List yhsbgmxList)
    {
        this.yhsbgmxList = yhsbgmxList;
    }

    public Yhsbgz getYhsbgz()
    {
        return yhsbgz;
    }

    public void setYhsbgz(Yhsbgz yhsbgz)
    {
        this.yhsbgz = yhsbgz;
    }

    public String[] getYnse()
    {
        return ynse;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String[] getSzsmdm()
    {
        return szsmdm;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public List getYhsSzsmInfoList()
    {
        return yhsSzsmInfoList;
    }
    public void setYhsSzsmInfoList(List yhsSzsmInfoList)
    {
        this.yhsSzsmInfoList = yhsSzsmInfoList;
    }
}