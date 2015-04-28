//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\web\\CzzsndForm.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbfpbl;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbqy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzmxsj;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import java.util.*;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * @author Haifeng Su
 * @version 1.0
 *
 * 查账征收个人独资和合伙企业年度申报
 */
public class CzzsndForm extends SbzlBaseForm
{

    /**
     * 页面的 所属日期起
     *
     * 查帐征收年报主数据 值对象的 税款所属开始日期SKSSKSRQ 属性
     *
     * 不可编辑
     */
    private String ssrqq;

    /**
     * 页面的投资者证件类型代码 （隐含域）
     */
    private String[] zjlxdm;

    /**
     * 页面的 创建时间 （隐含域）
     *
     * 查帐征收年报主数据 值对象的 创建时间 属性
     */
    private String cjsj;

    /**
     * 页面的 年度 (隐含域)
     */
    private String nd;

    /**
     * 页面的 税务机关组织机构代码 (隐含域)
     */
    private String swjgzzjgdm;

    /**
     * 录入人 (隐含域)
     */
    private String lrr;

    /**
     * 录入日期 (隐含域)
     */
    private String lrrq;

    /**
     * 税务计算机代码 String
     *
     * 从登录数据中取得
     *
     * 不可编辑
     */
    private String jsjdm;

    /**
     * 页面的 投资者姓名
     *
     * 查帐征收年报投资者数据 值对象的 投资者姓名TZZXM 属性
     *
     * 不可编辑
     */
    private String tzzxm[];

    /**
     * 页面的 投资者身份证件号码
     *
     * 查帐征收年报投资者数据 值对象的 证件号码ZJHM 属性
     *
     * 不可编辑
     */
    private String tzzsfzjhm[];

    /**
     * 表列1 String[]
     *
     * 行次从1到18的框
     *
     * 一、企业收入总额	录入，必须为数字	行次1
     * 减：成本	录入，必须为数字	行次2
     * 费用、税金	录入，必须为数字	行次3
     * 营业外支出	录入，必须为数字	行次4
     * 二、企业利润总额	计算得到，不能修改	行次5=1-2-3-4
     * 三、纳税调整增加额	计算得到，不能修改	行次6= 7+19+30
     * 1.超过规定比例扣除的项目	录入，必须为数字	行次7
     * (1)从业人员工资支出	录入，必须为数字	行次8
     * (2)职工福利费	录入，必须为数字	行次9
     * (3)职工教育经费	录入，必须为数字	行次10
     * (4)工会经费	录入，必须为数字	行次11
     * (5)利息支出	录入，必须为数字	行次12
     * (6)广告费	录入，必须为数字	行次13
     * (7)业务招待费	录入，必须为数字	行次14
     * (8)教育和公益事业捐赠	录入，必须为数字	行次15
     * (9)提取折旧费	录入，必须为数字	行次16
     * (10)无形资产摊销	录入，必须为数字	行次17
     * (11)其他	录入，必须为数字	行次18
     */
    private String[] bl1;

    /**
     * 表列2 String[]
     *
     * 行次从19到36的框
     *
     * 2.不允许扣除的项目	计算得到，不能修改	行次19=20+21+22+23+24+25+26+27+28+29
     * (1)资本性支出	录入，必须为数字	行次20
     * (2)无形资产受让、开发支出	录入，必须为数字	行次21
     * (3)违法经营罚款和被没收财物损失	录入，必须为数字	行次22
     * (4)税收滞纳金、罚金、罚款	录入，必须为数字	行次23
     * (5)灾害事故损失赔偿	录入，必须为数字	行次24
     * (6)非教育和公益事业捐赠 	录入，必须为数字	行次25
     * (7)各种赞助支出	录入，必须为数字	行次26
     * (8)计提的各种准备金	录入，必须为数字	行次27
     * (9)投资者的工资	录入，必须为数字	行次28
     * (10)与收入无关的支出	录入，必须为数字	行次29
     * 3.应税收益项目	录入，必须为数字	行次30
     * (1)少计应税收益	录入，必须为数字	行次31
     * (2)未计应税收益	录入，必须为数字	行次32
     * 四、纳税调整减少额	计算得到，不能修改	行次33= 34+35+36+37
     * 1.弥补亏损	录入，必须为数字	行次34
     * 2.国库券利息收入	录入，必须为数字	行次35
     * 3.投资者标准费用扣除额	录入，必须为数字	行次36
     */
    private String[] bl2;

    /**
     * 表列3 String[]
     *
     * 行次从37到54的框
     *
     * 4.其他	录入，必须为数字	行次37
     * 五、调整后的应纳税所得额	计算得到，不能修改（如果为负数，置为0，且以后各计算项均
     * 不计算，可编辑）	行次38= 5+6-33
     * 六、分配比例	根据计算机代码从登记数据的投资者信息中带出，不能修改	行次39
     * 七、投资者应纳税所得额	计算得到，不能修改（如果为负数，置为0，且以后各计算项均不
     * 计算，可编辑）	行次40＝38*39
     * 八、适用税率	根据应纳税所得额检索“税率表”得到，不可修改（如果“投资者应纳税所\uFFFD
     * 额”小于等于0，则不检索）	行次41
     * 九、速算扣除数	根据应纳税所得额检索“税率表”
     * 得到，不可修改（如果“投资者应纳税所得额”小于等于0，则不检索）	行次42
     * 十、应纳所得税额	计算得到，不能修改（如果为负数，置为0，且以后各计算项均不计算，
     * 可编辑）	行次43= 40*41-42
     * 减：减、免所得税额	录入，必须为数字（如果有当期减免审批记录，则可编辑，否则不可\uFFFD
     * 辑。）	行次44
     * 十一、应缴入库所得税额	计算得到，不能修改（如果为负数，置为0，且以后各计算项均不
     * 计算，可编辑）	行次45 = 43-44
     * 加：期初未缴所得税额	录入，必须为数字	行次46
     * 减：实际已缴纳所得税额	录入，必须为数字	行次47
     * 十二、期末应补(退)所得税额	计算得到，不能修改（如果为负数，置为0）	行次48＝45+46
     * -47
     * 补充资料：1.年平均职工人数（人）	录入，必须为数字	行次49
     * 2.工资总额（实发数）（元）	录入，必须为数字	行次50
     * (1) 分配比例所占金额	录入，必须为非负数字	行次51
     * (2) 分配比例所占金额	录入，必须为非负数字	行次52
     * (3) 分配比例所占金额	录入，必须为非负数字	行次53
     * (4) 分配比例所占金额	录入，必须为非负数字	行次54
     */
    private String[] bl3;

    /**
     * 表列4 String[]
     *
     * 行次从51到54的小表框
     *
     *
     * 3.从其他企业取得的生产经营所得(1) 分配比例
     * 	录入，必须为数字，应大于等于0小于等于100	行次51
     * (2) 分配比例	录入，必须为数字，应大于等于0小于等于100	行次52
     * (3) 分配比例 	录入，必须为数字，应大于等于0小于等于100	行次53
     * (4) 分配比例	录入，必须为数字，应大于等于0小于等于100	行次54
     */
    private String[] bl4;

    /**
     * 企业申报数据
     *
     * List型数据，里面的对象是 查帐征收年报企业数据 值对象
     */
    private List qysbsjList;

    /**
     * 投资者申报数据
     *
     * List型数据，里面的对象是和投资者数目相等的  查帐征收年报投资者数据 值对象
     */
    private List tzzsbsjList;

    /**
     * 投资者申报数据
     *
     * List型数据，里面的对象是和投资者数目相等的List，内层List是
     * 查帐征收年度分配比例数据 值对象
     */
    private List fpblsjList;

    /**
     * 税率表数据
     *
     * List型数据，里面的对象是 税率表数据 值对象
     */
    private List slbsjList;

    /**
     * 减免数据
     *
     * List型数据，里面的对象是 减免数据 值对象
     */
    private List jmsjList;

    /**
     * 查帐征收年报主数据
     */
    private Czzsnbzb czzsnbzb;

    /**
     * 页面的 申报日期
     *
     * 查帐征收年报主数据 值对象的 录入日期LRRQ 属性
     */
    private String sbrq;

    /**
     * 页面的 所属日期止
     *
     * 查帐征收年报主数据 值对象的 税款所属结束日期SKSSJSRQ 属性
     */
    private String ssrqz;

    /**
     * 减免数据
     *
     */
    private String jmsj;

    /**
     * 页面的 表列5
     *
     */
    private String[] bl5;

    /**
     * 页面的 表列6
     */
    private String[] bl6;

    /**
     * 财务负责人
     *
     */
    private String[] cwfzr;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 投资者明细数据
     */
    private List tzzsbsjmxList;

    /**
     * 是否数据能正常调到数据区
     */
    private boolean done = false;

    /**
     * 从data中
     * 使用key为CzzsndMapConstant.CZZSNBZB取查帐征收年报主数据放到czzsnbzb中，
     * 使用key为CzzsndMapConstant.LIST_QYSBSJ取企业申报数据放到qysbsjList中，
     * 使用key为CzzsndMapConstant.LIST_TZZSBSJ取投资者申报数据放到form的tzzsbsjList中，
     * 使用key为CzzsndMapConstant.LIST_FPBLSJ取投资者数据放到form的tzzsjList中，
     * 使用key为CzzsndMapConstant.LIST_JMSJ取减免数据放到form的jmsjList中，
     * 使用key为CzzsndMapConstant.LIST_SLBSJ取税率表数据放到slbsjList中。
     * @param jsjdm 计算机代码
     * @param data 返回的数据
     * @throws BaseException
     */
    public void afterQuery(String jsjdm, Map data) throws BaseException
    {
        this.czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
        this.qysbsjList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
        this.tzzsbsjList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
        this.fpblsjList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);
        this.tzzsbsjmxList = (List)data.get(CzzsndMapConstant.LIST_TZZMX);
        SWDJJBSJ swdjjbsj = (SWDJJBSJ)data.get(CzzsndMapConstant.JBSJ);
        this.nsrmc = swdjjbsj.getNsrmc();

        //调用税费管理接口取得减免数据
        this.jmsjList = new ArrayList(this.tzzsbsjList.size());
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        for(int i = 0, size = this.tzzsbsjList.size(); i < size; i++)
        {
            Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj)this.tzzsbsjList.get(i);

            // 下面的调用因为使用缺省的国籍代码，未来某个时候会更改过来，
            // 所以就没写到CodeTable去。未来某个时候国籍会从投资者数据中取。
            // 有一种可能，是Czzsnbtzzsj对应的数据库表结构改动，
            // 增加国籍代码字段，所以，漫漫长路。
            boolean result = false;
            try
            {
                Calendar cc = new GregorianCalendar();
                int year = cc.get(Calendar.YEAR) - 1;
                cc.set(year, Calendar.DECEMBER, 31);
                result = sfProxy.getZRRJm(czzsnbtzzsj.getZjlxdm(),
                                          czzsnbtzzsj.getZjhm(), "CHN",
                                          SzsmdmConstant.GRSDS, cc.getTime());
            }
            catch(Exception e)
            {
                throw ExceptionUtil.getBaseException(e);
            }
            this.jmsjList.add(result ? Boolean.TRUE : Boolean.FALSE);
        }
    }

    /**
     * 生成Map，
     * 使用key为CzzsndMapConstant.CZZSNBZB放查帐征收年报主数据czzsnbzb，
     * 使用key为CzzsndMapConstant.LIST_QYSBSJ放企业申报数据qysbsjList，
     * 使用key为CzzsndMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList，
     * 使用key为CzzsndMapConstant.LIST_FPBLSJ放分配比例数据fpblsjList，返回此Map。
     * @param jsjdm 计算机代码
     * @return Map
     * @throws BaseException
     */
    public Map beforeSave(String jsjdm) throws BaseException
    {
        this.collect(jsjdm);
        Map retMap = new HashMap(5);
        retMap.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
        retMap.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        retMap.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZMX, this.tzzsbsjmxList);
        return retMap;
    }

    /**
     * 生成Map，使用key为CzzsndMapConstant.JSJDM放入计算机代码，返回此Map。
     * @param jsjdm 计算机代码
     * @param tzfList 投资方数据
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(String jsjdm, List tzfList) throws BaseException
    {
        Map map = new HashMap(2);
        map.put(CzzsndMapConstant.JSJDM, jsjdm);
        map.put(CzzsndMapConstant.LIST_TZF, tzfList);
        return map;
    }

    /**
     * 生成Map，
     * 使用key为CzzsndMapConstant.CZZSNBZB放查帐征收年报主数据czzsnbzb，
     * 使用key为CzzsndMapConstant.LIST_QYSBSJ放企业申报数据qysbsjList，
     * 使用key为CzzsndMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList，
     * 使用key为CzzsndMapConstant.LIST_FPBLSJ放分配比例数据fpblsjList，
     * 返回此Map。
     * @param jsjdm 计算机代码
     * @return Map
     * @throws BaseException
     */
    public Map beforeDelete(String jsjdm) throws BaseException
    {
        this.collect(jsjdm);
        Map retMap = new HashMap(5);
        retMap.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
        retMap.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        retMap.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZMX, this.tzzsbsjmxList);
        return retMap;
    }

    /**
     * 收集页面数据转换成值对象放到 czzsnbzb、qysbsjList、jmsjList、
     *	tzzsbsjList、fpblsjList和tzzmxsjList中。
     * @param jsjdm 计算机代码
     */
    private void collect(String jsjdm)
    {
        Date today = new Date();
        Map sksjrqMap = Skssrq.yearSkssrq(today);
        String nd = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);

        // 减免数据List
        this.jmsjList = new ArrayList(1);
        this.jmsjList.add(new Boolean(this.jmsj));

        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 更新年度主表数据
        this.czzsnbzb = this.newInstanceCzzsnbzb(jsjdm, nd,
                                                 CodeConstant.FSDM_WSSB,
                                                 this.swjgzzjgdm,
                                                 this.lrr,
                                                 this.lrrq,
                                                 this.ssrqz,
                                                 this.ssrqq,
                                                 this.cjsj,
                                                 now);

        // 更新投资者数据
        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        for(int i = 0; i < this.tzzxm.length; i++)
        {
            Czzsnbtzzsj czzsnbtzzsj = this.newInstanceTzzsbsj(jsjdm, nd,
                this.zjlxdm[i], this.tzzsfzjhm[i], this.cwfzr[i],
                this.swjgzzjgdm, now);
            this.tzzsbsjList.add(czzsnbtzzsj);
        }

        // 更新企业数据List
            //1-38，49-50
        this.qysbsjList = new ArrayList(40);
        for(int i = 0; i < this.bl1.length; i++)
        {
            Czzsnbqy czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                String.valueOf(i + 1), bl1[i], this.swjgzzjgdm, now);
            this.qysbsjList.add(czzsnbqy);
        }
        for(int i = 0; i < this.bl2.length; i++)
        {
            Czzsnbqy czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                String.valueOf(i + 1 + bl1.length), bl2[i], this.swjgzzjgdm, now);

            this.qysbsjList.add(czzsnbqy);
        }
        for(int i = 0; i < this.bl3.length; i++)
        {
            Czzsnbqy czzsnbqy = null;
            if(i < 2)
            {
                czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                    String.valueOf(i + 1 + bl1.length + bl2.length),
                                                  bl3[i], this.swjgzzjgdm, now);
            }
            else
            {
                czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                    String.valueOf(i + 1 + bl1.length + bl2.length + 10),
                                                  bl3[i], this.swjgzzjgdm, now);
            }
            this.qysbsjList.add(czzsnbqy);
        }

    // 更新投资者明细数据
            //39-48
        int tzzSize = this.tzzxm.length;
        this.tzzsbsjmxList = new ArrayList(tzzSize);
        for(int i = 0; i < tzzSize; i++)
        {
            List tzzList = new ArrayList(this.bl4.length / tzzSize);
            for(int j = 0; j < this.bl4.length / tzzSize; j++)
            {
                Czzsnbtzzmxsj czzsnbtzzmxsj = this.newCzzsnbtzzmxsj(jsjdm, nd,
                    this.zjlxdm[i], this.tzzsfzjhm[i],
                    String.valueOf(j + 39), bl4[tzzSize * j + i], this.swjgzzjgdm, now);
                tzzList.add(czzsnbtzzmxsj);
            }
            this.tzzsbsjmxList.add(tzzList);
        }

        // 更新分配比率数据
            //51-54
        this.fpblsjList = new ArrayList(4);
        for(int i = 0; i < 4; i++)
        {
            if(bl5[i] == null)
            {
                bl5[i] = "0.00";
            }
            if(bl6[i] == null)
            {
                bl6[i] = "0.00";
            }
            Czzsnbfpbl czzsnbfpbl = this.newInstanceFpbl(jsjdm, nd,
                String.valueOf(i + 51), bl5[i], bl6[i], this.swjgzzjgdm, now);
            this.fpblsjList.add(czzsnbfpbl);
        }
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报投资者数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param zjlxdm 证件类型代码
     * @param zjhm 证件号码
     * @param hc 行次
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return List 投资者数据
     */
    public Czzsnbtzzmxsj newCzzsnbtzzmxsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String hc, String bqljs,
                                           String swjgzzjgdm, Timestamp now)
    {
        Czzsnbtzzmxsj czzsnbtzzmxsj = new Czzsnbtzzmxsj();
        czzsnbtzzmxsj.setBqljs(new BigDecimal(bqljs));
        czzsnbtzzmxsj.setHc(hc);
        czzsnbtzzmxsj.setJsjdm(jsjdm);
        czzsnbtzzmxsj.setNd(nd);
        czzsnbtzzmxsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzmxsj.setZjhm(zjhm);
        czzsnbtzzmxsj.setZjlxdm(zjlxdm);
        czzsnbtzzmxsj.setCjrq(now);
        czzsnbtzzmxsj.setLrrq(now);
        czzsnbtzzmxsj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbtzzmxsj;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报主数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param fsdm 方式代码
     * @param swjgzzjgdm 机关代码
     * @param lrr 录入人
     * @param lrrq 录入日期
     * @param ssrqz 所属日期止
     * @param ssrqq 所属日期起
     * @param strCjsj 创建时间
     * @return Czzsnbzb 年报主表
     */
    public Czzsnbzb newInstanceCzzsnbzb(String jsjdm, String nd, String fsdm,
                                         String swjgzzjgdm, String lrr,
                                         String lrrq,
                                         String ssrqz, String ssrqq,
                                         String strCjsj,
                                         Timestamp now)
    {
        Czzsnbzb czzsnbzb = new Czzsnbzb();
        czzsnbzb.setJsjdm(jsjdm);
        czzsnbzb.setNd(nd);
        czzsnbzb.setFsdm(fsdm);
        czzsnbzb.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbzb.setLrr(lrr);
        czzsnbzb.setLrrq(now);
        czzsnbzb.setSbrq(TinyTools.second2Day(now));
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        czzsnbzb.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        czzsnbzb.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        Date cjsj = TinyTools.stringToDate(strCjsj);
        czzsnbzb.setCjrq(now);
        czzsnbzb.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbzb;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报企业数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param hc 行次
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return Czzsnbqy 年报企业
     */
    public Czzsnbqy newInstanceQysbsj(String jsjdm, String nd, String hc,
                                       String bqljs, String swjgzzjgdm, Timestamp now)
    {
        Czzsnbqy czzsnbqy = new Czzsnbqy();
        czzsnbqy.setJsjdm(jsjdm);
        czzsnbqy.setNd(nd);
        czzsnbqy.setHc(hc);
        czzsnbqy.setBqljs(new BigDecimal(bqljs));
        czzsnbqy.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbqy.setLrrq(now);
        czzsnbqy.setCjrq(now);
        czzsnbqy.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbqy;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报投资者数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param zjlxdm 证件类型代码
     * @param zjhm 证件号码
     * @param cwfzr 财务负责人
     * @param swjgzzjgdm 机关代码
     * @return List 投资者数据
     */
    public Czzsnbtzzsj newInstanceTzzsbsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String cwfzr, String swjgzzjgdm,
                                           Timestamp now)
    {
        Czzsnbtzzsj czzsnbtzzsj = new Czzsnbtzzsj();
        czzsnbtzzsj.setJsjdm(jsjdm);
        czzsnbtzzsj.setNd(nd);
        czzsnbtzzsj.setZjlxdm(zjlxdm);
        czzsnbtzzsj.setZjhm(zjhm);
        czzsnbtzzsj.setCwfzr(cwfzr);
        czzsnbtzzsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzsj.setQxdm(swjgzzjgdm.substring(0, 2));
        czzsnbtzzsj.setCjrq(now);
        czzsnbtzzsj.setLrrq(now);
        return czzsnbtzzsj;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年度分配比例数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param hc 行次
     * @param bl 比例
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return Czzsnbfpbl 分配比例
     */
    public Czzsnbfpbl newInstanceFpbl(String jsjdm, String nd, String hc,
                                       String bl, String bqljs, String swjgzzjgdm,
                                       Timestamp now)
    {
        Czzsnbfpbl czzsnbfpbl = new Czzsnbfpbl();
        czzsnbfpbl.setJsjdm(jsjdm);
        czzsnbfpbl.setNd(nd);
        czzsnbfpbl.setHc(hc);
        czzsnbfpbl.setBl(new BigDecimal(bl));
        czzsnbfpbl.setBqljs(new BigDecimal(bqljs));
        czzsnbfpbl.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbfpbl.setLrrq(now);
        czzsnbfpbl.setCjrq(now);
        czzsnbfpbl.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbfpbl;
    }

    // getter & setter
    public void setBl1(String[] bl1)
    {
        this.bl1 = bl1;
    }

    public void setBl2(String[] bl2)
    {
        this.bl2 = bl2;
    }

    public void setBl3(String[] bl3)
    {
        this.bl3 = bl3;
    }

    public void setBl4(String[] bl4)
    {
        this.bl4 = bl4;
    }

    public Czzsnbzb getCzzsnbzb()
    {
        return czzsnbzb;
    }

    public List getFpblsjList()
    {
        return fpblsjList;
    }

    public List getJmsjList()
    {
        return jmsjList;
    }

    public void setJmsjList(List jmsjList)
    {
        this.jmsjList = jmsjList;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public List getQysbsjList()
    {
        return qysbsjList;
    }

    public void setQysbsjList(List qysbsjList)
    {
        this.qysbsjList = qysbsjList;
    }

    public String getSbrq()
    {
        return sbrq;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public String getSsrqq()
    {
        return ssrqq;
    }

    public void setSsrqq(String ssrqq)
    {
        this.ssrqq = ssrqq;
    }

    public String getSsrqz()
    {
        return ssrqz;
    }

    public void setSsrqz(String ssrqz)
    {
        this.ssrqz = ssrqz;
    }

    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public void setTzzsbsjList(List tzzsbsjList)
    {
        this.tzzsbsjList = tzzsbsjList;
    }

    public String[] getTzzsfzjhm()
    {
        return tzzsfzjhm;
    }

    public void setTzzsfzjhm(String[] tzzsfzjhm)
    {
        this.tzzsfzjhm = tzzsfzjhm;
    }

    public String[] getTzzxm()
    {
        return tzzxm;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setJmsj(String jmsj)
    {
        this.jmsj = jmsj;
    }

    public String getLrr()
    {
        return lrr;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public String getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String[] getZjlxdm()
    {
        return zjlxdm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }


    public void setBl5(String[] bl5)
    {
        this.bl5 = bl5;
    }

    public String[] getCwfzr()
    {
        return cwfzr;
    }

    public void setCwfzr(String[] cwfzr)
    {
        this.cwfzr = cwfzr;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setBl6(String[] bl6)
    {
        this.bl6 = bl6;
    }

    public List getTzzsbsjmxList()
    {
        return tzzsbsjmxList;
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