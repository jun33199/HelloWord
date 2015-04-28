package com.ttsoft.bjtax.shenbao.sbzl.wqyys.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.math.BigDecimal;
import javax.servlet.http.HttpSession;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * 外企营业税申报form，同时支持按实申报、核定征收、经费换算三种方式
 * 金额用字符串形式保存
 * @author Haifeng Su
 * @version 1.15
 */
public class WqyysForm extends SbzlBaseForm
{

    /**
     * 处理器的名称
     */
//    private String processor;

    /**
     * session的句柄
     */

    /**
     * 征税方式代码
     */
    private String zsffdm;

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     * 税种税目
     */
    private List szsmList;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 税种税目代码
     */
    private String[] szsmdm;

    /**
     * 税种税目名称
     */
    private String[] szsmmc;

    /**
     * 计税金额
     */
    private String[] jsje;

    /**
     * 税率
     */
    private String[] sl;

    /**
     * 应纳税额
     */
    private String[] ynse;

    /**
     * 已缴纳税额
     */
    private String[] yjnse;

    /**
     * 本期应补税额
     */
    private String[] bqybse;

    /**
     * 收入额
     */
    private String[] sre;

    /**
     * 合同成交额
     */
    private String[] htcje;

    /**
     * 佣金率
     */
    private String[] yjl;

    /**
     * 核定收入额
     */
    private String[] hdsre;

    /**
     * 经费支出额
     */
    private String[] jfzce;

    /**
     * 换算收入额
     */
    private String[] hssre;

    /**
     * 外企营业税
     */
    private List wqyysList;

    /**
     * 外企营业税模板
     */
    private Wqyys wqyysTemplate;

    /**
     * 登记常量
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * 税种代码
     */
    private static final String SZDM = "02";

    /**
     * 税种代码处罚部分
     */
    private static final String SZDMEND1 = "91";

    /**
     * 税种代码处罚部分
     */
    private static final String SZDMEND2 = "92";

    /**
     * 申报数据常量
     */
    public static final String SHENBAO = "SHENBAO";

    /**
     * 基本信息常量
     */
    public static final String JBXX = "JBXX";

    /**
     * 原始的税种税目
     */
    private List szsmOriginalList;

    /**
     * 是否数据能正常调到数据区
     */
    private boolean done = true;

    public WqyysForm()
    {
//        this.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
    }

    /**
     * 设置页面下拉框的税种税目
     * @param szsmList 税种税目
     */
    public void setSzsmList(List szsmList)
    {
        // 使用复制机制保护原始数据
        this.szsmOriginalList = szsmList;
        this.szsmList = new ArrayList();
        // 缩进的空格定义，全角
        String oneSpace = "　";
        String twoSpace = "　　";
        String threeSpace = "　　　";
        String fourSpace = "　　　　";
        for(int i = 0, size = szsmList.size(); i < size; i++)
        {
            Szsm szsmSource = (Szsm)szsmList.get(i);
            Szsm szsm = new Szsm();
            szsm.setSl(szsmSource.getSl());
            szsm.setSzsmdm(szsmSource.getSzsmdm());
            szsm.setSzsmmc(szsmSource.getSzsmmc());
            String szsmdm = szsm.getSzsmdm();
            // 代码以本税种代码开头
            if(szsmdm.startsWith(this.SZDM))
            {
                // 过滤掉"营业税滞纳金"和"营业税罚款"
                if(!szsmdm.endsWith(this.SZDMEND1) &&
                   !szsmdm.endsWith(this.SZDMEND2))
                {
                    switch (szsmdm.length() - 2) {
                        case 0:
                            // 2位长度的税种不做改变
                            break;
                        case 1:
                            // 3位长度的税种税目前面加1个空格
                            szsm.setSzsmmc(oneSpace + szsm.getSzsmmc());
                            break;
                        case 2:
                            // 4位长度的税种税目前面加2个空格
                            szsm.setSzsmmc(twoSpace + szsm.getSzsmmc());
                            break;
                        case 3:
                            // 5位长度的税种税目前面加3个空格
                            szsm.setSzsmmc(threeSpace + szsm.getSzsmmc());
                            break;
                        case 4:
                            // 6位长度的税种税目前面加4个空格
                            szsm.setSzsmmc(fourSpace + szsm.getSzsmmc());
                            break;
                    }
                    this.szsmList.add(szsm);
                }
            }
        }
    }

    /**
     * @param data 返回的数据
     * @return Map
     */
    public Map afterQuery(Map data, SWDJJBSJ jbsj)
    {
        // 产生数据模板
        this.wqyysTemplate = makeTemplate(jbsj.getJsjdm(), jbsj.getNsrmc(),
                                          jbsj.getSwjgzzjgdm());
        // 产生业务数据
        this.wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);
        for(int i = 0, size = this.wqyysList.size(); i < size; i++)
        {
            Wqyys wqyys = (Wqyys)this.wqyysList.get(i);
            // 设置纳税人名称
            wqyys.setNsrmc(jbsj.getNsrmc());
        }
        Map saveData = new HashMap(2);
        saveData.put(this.SHENBAO, this.wqyysList);
        saveData.put(this.JBXX, this.wqyysTemplate);
        return saveData;
    }

    /**
     * @param jsjdm 计算机代码
     * @return Map
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(1);
        map.put(WqyysMapConstant.LIST_WQYYS, this.wqyysList);
        return map;
    }

    /**
     * 生成Map，使用key为HdzsgrsdsMapConstant.JSJDM放入计算机代码，返回此Map。
     * @param jsjdm 计算机代码
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(SWDJJBSJ jbsj) throws BaseException
    {
        // 设置操作数据到VOPackage
        Map map = new HashMap(3);

        Map sksjrqMap = Skssrq.monthSkssrq(new Date());
        Timestamp ksrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ);
        String skssksrq = TinyTools.Date2String(ksrq);

        map.put(WqyysMapConstant.JSJDM, jbsj.getJsjdm());
        map.put(WqyysMapConstant.SKSSKSRQ, skssksrq);
        map.put(WqyysMapConstant.DJJBSJ, jbsj);

        return map;
    }

    /**
     * @param jsjdm 计算机代码
     * @return Map 返回需要删除的值对象
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(1);
        List list = new ArrayList(1);
        list.add(this.wqyysTemplate);
        map.put(WqyysMapConstant.LIST_WQYYS, list);
        return map;
    }

    /**
     * 产生一个外企营业税的申报数据模板
     * @param jsjdm 计算机代码
     * @param nsrmc 纳税人名称
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return Wqyys 外企营业税的申报数据模板
     */
    private Wqyys makeTemplate(String jsjdm, String nsrmc, String swjgzzjgdm)
    {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        // 税款所属日期
        Map sksjrqMap = Skssrq.monthSkssrq(today);

        Wqyys wqyys = new Wqyys();
        //计算机代码	JSJDM
        wqyys.setJsjdm(jsjdm);
        //税款所属开始日期	SKSSKSRQ
        wqyys.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //税种税目代码	SZSMDM
        //创建时间	CJSJ
        wqyys.setCjrq(now);
        //纳税人名称	NSRMC
        wqyys.setNsrmc(nsrmc);
        //税种税目名称	SZSMMC
        //申报日期	SBRQ
        wqyys.setSbrq(TinyTools.second2Day(now));
        //税款所属结束日期	SKSSJSRQ
        wqyys.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //征税方法代码	ZSFFDM
        //征税方法名称	ZSFFMC
        //收入额	SRE
        //合同成交额	HTCJE
        //佣金率	YJL
        //核定收入额	HDSRE
        //经费支出额	JFZCE
        //换算收入额	HSSRE
        //计税金额	JSJE
        //税率	SL
        //应纳税额	YNSE
        //已纳税额	YINSE
        //本期应补税额	BQYBSE
        //税务机关组织机构代码	SWJGZZJGDM
        wqyys.setSwjgzzjgdm(swjgzzjgdm);
        //录入人代码	LRR
        wqyys.setLrr(jsjdm);
        //录入日期	LRRQ
        wqyys.setLrrq(now);
        //登记申报方式代码	FSDM
        wqyys.setFsdm(CodeConstant.FSDM_WSSB);
        //年度 ND
        wqyys.setNd((String)sksjrqMap.get(Skssrq.SKSSRQ_ND));

        wqyys.setQxdm(swjgzzjgdm.substring(0, 2));

        return wqyys;
    }

    /**
     * 从税种税目代码查找税种税目名称
     * @param szsmdm 税种税目代码
     * @return String 税种税目名称
     */
    private Szsm find(String szsmdm)
    {
        Szsm szsm = null;
        // 需要在原始的代码表中查找因为我们使用的已经经过加工（前面有空格）
        for(int i = 0, size = this.szsmOriginalList.size(); i < size; i++)
        {
            szsm = (Szsm)this.szsmOriginalList.get(i);
            if(szsm.getSzsmdm().equalsIgnoreCase(szsmdm))
            {
                // 找到税种税目代码对应的税种税目名称
                break;
            }
        }
        return szsm;
    }

    /**
     * 从页面的资料填充值对象返回
     * @param jsjdm 计算机代码
     * @throws ApplicationException
     */
    private void collect(String jsjdm) throws ApplicationException
    {
        int size = 0;
        if(this.bqybse != null)
        {
            size = this.bqybse.length;
        }

        this.wqyysList = new ArrayList(size);

        // 0作为基准值
        BigDecimal zero = new BigDecimal("0");

        for(int i = 0; i < size; i++)
        {
            // 取得一条数据模板
            Wqyys wqyys = makeTemplate(jsjdm, this.nsrmc, this.swjgzzjgdm);
            // 查找对应的税种税目
            Szsm szsm = this.find(this.szsmdm[i]);
            //税种税目代码	SZSMDM
            wqyys.setSzsmdm(this.szsmdm[i]);
            //征税方法代码	ZSFFDM
            wqyys.setZsffdm(this.zsffdm);
            if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_JFHS))
            {
                // 进入“经费换算”的判断逻辑

                //经费支出额	JFZCE
                wqyys.setJfzce(new BigDecimal(this.jfzce[i]));
                //换算收入额	HSSRE
                wqyys.setHssre(new BigDecimal(this.hssre[i]));
                //其他置0
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
                wqyys.setSre(zero);
            }
            else if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_HDZS))
            {
                // 进入“核定征收”的判断逻辑

                //合同成交额	HTCJE
                wqyys.setHtcje(new BigDecimal(this.htcje[i]));
                //佣金率	YJL
                wqyys.setYjl(new BigDecimal(this.yjl[i]));
                //核定收入额	HDSRE
                wqyys.setHdsre(new BigDecimal(this.hdsre[i]));
                //其他置0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setSre(zero);
            }
            else if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_ASSB))
            {
                // 进入“按实申报”的判断逻辑

                //收入额	SRE
                wqyys.setSre(new BigDecimal(this.sre[i]));
                //其他置0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
            }
            else
            {
                // 没有这个征收方式
                throw new ApplicationException("操作失败！");
            }

            BigDecimal jsjeWrapper = new BigDecimal(this.jsje[i]);

            BigDecimal slWrapper = szsm.getSl();
            // 应纳税额 = 计税金额 * 税率
            BigDecimal ynseWrapper = jsjeWrapper.multiply(slWrapper).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
            // 已缴纳税额
            BigDecimal yjnseWrapper = new BigDecimal(this.yjnse[i]);
            // 本期应补税额 = 应纳税额 - 已缴纳税额
            BigDecimal bqybseWrapper =
                ynseWrapper.subtract(yjnseWrapper).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            wqyys.setJsje(jsjeWrapper);  //计税金额
            wqyys.setSl(slWrapper);  //税率
            wqyys.setYnse(ynseWrapper);  //应纳税额
            wqyys.setYinse(yjnseWrapper);  //已纳税额
            wqyys.setBqybse(bqybseWrapper);  //本期应补税额

            this.wqyysList.add(wqyys);
        }
    }

//    /**
//     * 取得处理器的名称
//     * @return String 处理器的名称
//     */
//    public String getProcessor()
//    {
//        return processor;
//    }
//
//    /**
//     * 设置处理器的名称
//     * @param processor 处理器的名称
//     */
//    public void setProcessor(String processor)
//    {
//        this.processor = processor;
//    }

    // getter & setter
    public void setZsffdm(String zsffdm)
    {
        this.zsffdm = zsffdm;
    }

    public List getSzsmList()
    {
        return szsmList;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public void setSl(String[] sl)
    {
        this.sl = sl;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public void setYjnse(String[] yjnse)
    {
        this.yjnse = yjnse;
    }

    public void setBqybse(String[] bqybse)
    {
        this.bqybse = bqybse;
    }

    public void setSre(String[] sre)
    {
        this.sre = sre;
    }

    public void setHtcje(String[] htcje)
    {
        this.htcje = htcje;
    }

    public void setYjl(String[] yjl)
    {
        this.yjl = yjl;
    }

    public void setHdsre(String[] hdsre)
    {
        this.hdsre = hdsre;
    }

    public void setJfzce(String[] jfzce)
    {
        this.jfzce = jfzce;
    }

    public void setHssre(String[] hssre)
    {
        this.hssre = hssre;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public List getWqyysList()
    {
        return wqyysList;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setSzsmmc(String[] szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public Wqyys getWqyysTemplate()
    {
        return wqyysTemplate;
    }

  public void setWqyysTemplate(Wqyys wqyysTemplate) {
    this.wqyysTemplate = wqyysTemplate;
  }
    public boolean isDone()
    {
        return done;
    }
    public void setDone(boolean done)
    {
        this.done = done;
    }

}