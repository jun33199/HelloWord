package com.ttsoft.bjtax.shenbao.sbzl.jms.web;

import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 *
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 减免税申报Form</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Wu youzhi
 * @version 1.0
 */
public class JmsForm extends SbzlBaseForm
{

    /**
     * 税务计算机代码
     */
    private String jsjdm;

    /**
     * 单位名称
     */
    private String nsrmc;

    /**
     * 课税数量
     */
    private String[] kssl;

    /**
     * 计税金额
     */
    private String[] jsje;

    /**
     * 减免税额
     */
    private String[] jmse;

    /**
     * 合计
     */
    private String hj;

    /**
     * 申报日期
     * 系统自动带出服务器当前日期
     */
    private Timestamp sbrq;

    /**
     * 税种税目代码
     */
    private String[] szsmdm;

    /**
     * 税种税目代码
     */
    private String[] szdm;

    /**
     * 减免类型
     */
    private String[] jmlx;

    /**
     * 减免申报数据
     * 存放减免申报值对象
     */
    private List jmsbList = new ArrayList();

    /**
     * 已申报数据
     * 存放减免申报值对象
     */
    private List jmysbList = new ArrayList();

    /**
     * 纳税人资料值对象
     */
    private SWDJJBSJ nsrzl;

    // 创建时间
    private String cjsj[];

    // 征期类型代码
    private String zqlxdm[];

    // 减免类型代码
    private String jmxmdm[];

    // 税种信息
    private List szList = new ArrayList();

    // 税目信息
    private List smList = new ArrayList();

    // 税种税目Map
    private Map szsmMap;

    // 减免分类List
    private List jmflList;

    // 减免分类Map
    private Map jmflMap;

    // 附加税List
    private List fjsList;

    /**
     * 构造一个map
     * JmsMapConstant.SBRQ-----申报日期
     * @return Map
     */
    public Map getQueryData()
    {
        Map queryMap = new HashMap();
        queryMap.put(JmsMapConstant.SBRQ,this.sbrq);
        queryMap.put(JmsMapConstant.QXDM,this.nsrzl.getSwjgzzjgdm().substring(0,2));
        queryMap.put(JmsMapConstant.DJZCLXDM,this.nsrzl.getDjzclxdm());
        return queryMap;
    }

    /**
     * 把form中的数据转换为值对象，使用key为JmsMapConstant.LIST_JMSB放入减免申报数据
     * @return Map
     * @throws BaseException
     */
    public Map getSbData() throws BaseException
    {
        try
        {
            Map data = new HashMap();
            List sbList = new ArrayList();
            for(int i = 0; i < this.szsmdm.length; i++)
            {
                Jm vo = new Jm();
                // 计算机代码
                vo.setJsjdm(this.jsjdm);
                // 方式代码
                vo.setFsdm(CodeConstant.FSDM_WSSB);
                // 减免类型
                vo.setJmlx(this.jmlx[i]);
                // 减免税额
                vo.setJmse(new BigDecimal(this.jmse[i]));
                // 计税金额
                vo.setJsje(new BigDecimal(this.jsje[i]));
                // 课税数量
                Szsm szsmTmp = (Szsm)szsmMap.get(this.szsmdm[i]); // 取得Szsm值对象
                if(szsmTmp.getAsljbs() != null ? szsmTmp.getAsljbs().equals("1") : false) // 是否按数量记
                {
                    vo.setKssl(new BigDecimal(this.kssl[i]));
                }
                // 减免类型代码
                vo.setJmxmdm(this.jmxmdm[i]);
                // 登记注册类型代码
                vo.setDjzclxdm(nsrzl.getDjzclxdm());
                // 国家标准行业代码
                vo.setGjbzhydm(nsrzl.getGjbzhydm());
                // 录入人
                vo.setLrr(this.jsjdm);
                // 录入日期
                vo.setLrrq(this.sbrq);
                // 纳税人名称
                //vo.setNsrmc(nsrzl.getNsrmc());
                // 申报日期
                vo.setSbrq(TinyTools.second2Day(this.sbrq));
                // 税务机关组织结构代码
                vo.setSwjgzzjgdm(nsrzl.getSwjgzzjgdm());
                // 税种税目代码
                vo.setSzsmdm(this.szsmdm[i]);
                // 创建时间
                if(this.cjsj[i] == null || this.cjsj[i].equals(""))
                {
                    vo.setCjrq(this.sbrq);
                }
                else
                {
                    vo.setCjrq(new Timestamp(Long.parseLong(this.cjsj[i])));
                }
                // 年度
                vo.setNd(TinyTools.Date2String(this.sbrq).substring(0,4));
                // 区县代码
                vo.setQxdm(nsrzl.getSwjgzzjgdm().substring(0, 2));

                // 取得税款所属日期
                Map skssrqMap = Skssrq.getSksssq(this.jsjdm,
                                                 this.szsmdm[i],
                                                 nsrzl.getDjzclxdm(),
                                                 CodeConstant.SKLXDM_ZCJK,
                                                 zqlxdm[i],
                                                 this.sbrq,
                                                 null,
                                                 null,
                                                 null);
                vo.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
                vo.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));

                sbList.add(vo);
            }
            // 把本次申报数据和本期已申报数据放入
            data.put(JmsMapConstant.LIST_JMSB, sbList);
            data.put(JmsMapConstant.LIST_JMYSB, jmysbList);
            data.put(JmsMapConstant.ZQLXDM,zqlxdm);

            return data;
        }
        catch(Exception e)
        {
            throw new ApplicationException("您提交了错误的数据！请检查后重新申报！");
        }
    }

    /**
     * 用key为JmsMapConstant.LIST_JMYSB取本期已申报数据
     * @param data 本期已申报数据
     */
    public void setFormData(Map data)
    {
        this.jmysbList = (List)data.get(JmsMapConstant.LIST_JMYSB);
    }

    public String[] getJmlx()
    {
        return jmlx;
    }

    public void setJmlx(String[] jmlx)
    {
        this.jmlx = jmlx;
    }

    public List getJmsbList()
    {
        return jmsbList;
    }

    public void setJmsbList(List jmsbList)
    {
        this.jmsbList = jmsbList;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public void setKssl(String[] kssl)
    {
        this.kssl = kssl;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public String getHj()
    {
        return hj;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String[] getCjsj()
    {
        return cjsj;
    }

    public void setCjsj(String cjsj[])
    {
        this.cjsj = cjsj;
    }

    public void setSbrq(Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }
    public List getJmysbList()
    {
        return jmysbList;
    }
    public String[] getSzsmdm()
    {
        return szsmdm;
    }
    public void setNsrzl(SWDJJBSJ nsrzl)
    {
        this.nsrzl = nsrzl;
    }
    public String getNsrmc()
    {
        return this.nsrzl.getNsrmc();
    }
    public Timestamp getSbrq()
    {
        return sbrq;
    }
    public String[] getJmse()
    {
        return jmse;
    }
    public String[] getJsje()
    {
        return jsje;
    }
    public String[] getKssl()
    {
        return kssl;
    }
    public String[] getZqlxdm()
    {
        return zqlxdm;
    }
    public void setZqlxdm(String[] zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }
    public List getSzList()
    {
        return szList;
    }
    public void setSzList(List szList)
    {
        this.szList = szList;
    }
    public List getSmList()
    {
        return smList;
    }
    public void setSmList(List smList)
    {
        this.smList = smList;
    }
    public String[] getSzdm()
    {
        return szdm;
    }
    public void setSzdm(String[] szdm)
    {
        this.szdm = szdm;
    }
    public void setJmysbList(List jmysbList)
    {
        this.jmysbList = jmysbList;
    }
    public void setJmxmdm(String[] jmxmdm)
    {
        this.jmxmdm = jmxmdm;
    }
    public Map getSzsmMap()
    {
        return szsmMap;
    }
    public void setSzsmMap(Map szsmMap)
    {
        this.szsmMap = szsmMap;
    }
    public List getJmflList()
    {
        return jmflList;
    }
    public void setJmflList(List jmflList)
    {
        this.jmflList = jmflList;
    }
    public Map getJmflMap()
    {
        return jmflMap;
    }
    public void setJmflMap(Map jmflMap)
    {
        this.jmflMap = jmflMap;
    }
    public SWDJJBSJ getNsrzl()
    {
        return nsrzl;
    }
    public List getFjsList()
    {
        return fjsList;
    }
    public void setFjsList(List fjsList)
    {
        this.fjsList = fjsList;
    }

}