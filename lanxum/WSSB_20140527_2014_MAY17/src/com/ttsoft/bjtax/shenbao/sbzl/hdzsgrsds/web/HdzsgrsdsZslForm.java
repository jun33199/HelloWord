//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\hdzsgrsds\\web\\HdzsgrsdsForm.java

package com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.form.BaseForm;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzsqysb;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * 核定征收个人独资个人合伙个人所得税申报表
 * _应税所得率
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class HdzsgrsdsZslForm extends SbzlBaseForm
{

    /**
     * 所属日期起	Date	N	系统计算得出
     * String
     */
    private String skssksrq;

    /**
     * 所属日期止	Date	N	系统计算得出
     * String
     */
    private String skssjsrq;

    /**
     * 申报日期	Date	N	带出服务器当前日期
     * String
     */
    private String sbrq;

    /**
     * 税务计算机代码	VARCHAR2(8)	N	从登录数据中取得
     * String
     */
    private String jsjdm;

    /**
     * 单位名称	VARCHAR2(400)	N	根据计算机代码从登记数据中取得
     * String
     */
    private String nsrmc;

    /**
     * 收入总额	Number(15，2)	Y	录入，必须是数字
     * String
     * 	如果用户输入'收入总额'，系统根据计算公式计算出利润总额、应纳税所得额、
     * 	应纳税额 实际应缴税额，并计算出实际应缴税额合计。
     */
    private String qysrze;

    /**
     * 应税所得率	Number(10，5)	N
     * 	根据计算机代码从税种管理的核定中查询带出不可编辑
     * String
     */
    private String yssdl;

    /**
     * 利润总额	Number(15，2)	Y	录入，必须是数字
     * String
     *  	利润总额＝收入总额×应税所得率。
     */
    private String lrze;

    /**
     * 投资者姓名	VARCHAR2(30)	N
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     * String[]
     */
    private String[] tzzxm;

    /**
     * 投资者身份证件号码	VARCHAR2(18)	N
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     * String[]
     */
    private String[] tzzsfzjhm;

    /**
     * 利润分配比例	Number(10，5)	N
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     * String[]
     */
    private String[] lrfpbl;

    /**
     * 应纳税所得额	Number(15，2)	N
     * 根据利润总额和利润分配比例计算，并且不可编辑
     * String[]
     *  	应纳税所得额＝利润总额 × 利润分配比例。
     */
    private String[] ynssde;

    /**
     * 适用税率	Number(10，5)	N
     * 根据计算机代码从税种管理的核定中查询带出不可编辑
     * String[]
     */
    private String[] sysl;

    /**
     * 速算扣除数	Number(15，2)	N
     * 根据应纳税所得额检索"税率表"得到
     * String[]
     */
    private String[] sskcs;

    /**
     * 应纳税额	Number(15，2)	N	根据应纳税所得额计算，并且不可编辑
     * String[]
     *  	应纳税额＝应纳税所得额×适用税率－速算扣除数。
     */
    private String[] ynse;

    /**
     * 减免税额	Number(15，2)	N	录入，必须是大于等于0的数字
     * String[]
     * 	如果用户输入某一投资者的'减免税额'、'期初未缴税额'或'已缴纳税额'，
     * 	系统根据计算公式计算出该投资者的实际应缴税额，并计算出实际应缴税额合计。
     */
    private String[] jmse;

    /**
     * 期初未缴税额	Number(15，2)	N	录入，必须是大于等于0的数字
     * String[]
     * 	如果用户输入某一投资者的'减免税额'、'期初未缴税额'或'已缴纳税额'，
     * 	系统根据计算公式计算出该投资者的实际应缴税额，并计算出实际应缴税额合计。
     */
    private String[] qcwjsk;

    /**
     * 已缴纳税额	Number(15，2)	N	录入，必须是大于等于0的数字
     * String[]
     * 	如果用户输入某一投资者的'减免税额'、'期初未缴税额'或'已缴纳税额'，
     * 	系统根据计算公式计算出该投资者的实际应缴税额，并计算出实际应缴税额合计。
     */
    private String[] yjnse;

    /**
     * 实际应缴税额	Number(15，2)	Y	由应纳税额、减免税额、
     * 期初未缴税额和已缴纳税额计算公式计算出 可以修改（应大于等于0）
     * String[]
     *  	实际应缴税额=应纳税额－减免税额＋期初未缴税额－已缴纳税额。
     */
    private String[] sjyjse;

    /**
     * 合计	Number(15，2)	N	实际缴税额的合计数，不可修改
     * String
     *  	合计＝∑实际应缴税额。
     */
    private String hj;

    /**
     * 企业申报数据
     * 核定征收企业申报数据 值对象
     */
    private Hdzsqysb qysbsj;

    /**
     * 投资者申报数据
     * List型数据，里面的对象是和投资者数目相等的 核定征收投资者申报数据 值对象
     */
    private List tzzsbsjList;

    /**
     * 减免数据
     * List型数据，里面的对象是 减免数据 值对象
     */
    private List jmsjList;

    /**
     * 税率表数据
     * List型数据，里面的对象是 税率表数据 值对象
     */
    private List slbsjList;

    /**
     * 创建时间
     * String
     */
    private String cjsj;

    /**
     * 录入日期
     * String
     */
    private String lrrq;

    /**
     * 税务登记证号
     * String
     */
    private String swdjzh;

    /**
     * 税务机关组织机构代码
     * String
     */
    private String swjgzzjgdm;

    /**
     * 证件类型代码
     */
    private String[] zjlxdm;

    /**
     * 证件号码
     */
    private String[] zjhm;

    /**
     * 企业征收方式数据结构
     */
    private Grzsfs grzsfs;

    /**
     * 是否数据能正常调到数据区
     */
    private boolean done = true;

    /**
     * 是否有权限做本申报
     */

    /**
     * 从data中使用key为HdzsgrsdsMapConstant.QYSBSJ取企业申报数据放到form的qysbsj中，
     * 使用key为HdzsgrsdsMapConstant.LIST_TZZSBSJ取投资者申报数据放到form的tzzsbsjList中，
     * 调用税费的接口取减免数据放到form的jmsjList中。
     * @param jsjdm 计算机代码
     * @param data 返回的数据
     * @throws BaseException
     */
    public void afterQuery(String jsjdm, Map data)
    {
        this.jmsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_JMSJ);
        this.qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
        this.tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);
    }

    /**
     * 生成Map，
     * 使用key为HdzsgrsdsMapConstant.QYSBSJ放企业申报数据qysbsj，
     * 使用key为HdzsgrsdsMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList
     * 返回此Map。
     * @param jsjdm 计算机代码
     * @return Map
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     * 生成Map，使用key为HdzsgrsdsMapConstant.JSJDM放入计算机代码，返回此Map。
     * @param jsjdm 计算机代码
     * @param tzfList 投资方数据
     * @param zsl 征收率
     * @return Map
     */
    public Map beforeQuery(String jsjdm, List tzfList, String zsl)
    {
        Map map = new HashMap(3);
        map.put(HdzsgrsdsMapConstant.JSJDM, jsjdm);
        map.put(HdzsgrsdsMapConstant.LIST_TZF, tzfList);
        map.put(HdzsgrsdsMapConstant.ZSL, zsl);
        return map;
    }

    /**
     * 生成Map，
     * 使用key为HdzsgrsdsMapConstant.QYSBSJ放企业申报数据qysbsj，
     * 使用key为HdzsgrsdsMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList，
     * 返回此Map。
     * @param jsjdm 计算机代码
     * @return Map 返回需要删除的值对象
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     *
     * 从隐含域生成税率表数据slbsjList和减免数据，
     * 税率表数据只填写税率和税种税目代码域。
     * 收集页面数据填写到form中的qysbsj和tzzsbsjList，
     * 其中核定征收投资者申报数据是根据证件类型代码ZJLXDM和证件号码ZJHM域
     * 来确定特定的值对象。
     *
     * 生成规则如下：
     *
     * 填入核定征收企业申报数据值对象以下域
     * 企业收入总额	QYSRZE（form.srze）
     * 利润总额	LRZE（form.lrze）
     *
     * 填入核定征收投资者申报数据值对象以下域
     * 应纳税所得额	YNSSDE（form.ysssl[i]）
     * 适用税率	SYSL（form.sysl[i]）
     * 速算扣除数	SSKCS（form.sskcs[i]）
     * 应纳税额	YNSE（form.ynse[i]）
     * 减免税额	JMSE（form.jmse[i]）
     * 期初未缴税额	QCWJSK（form.qcwjse[i]）
     * 已缴纳税额	YJNSE（form.yjnse[i]）
     * 实际应缴税额	SJYJSE（form.sjyjse[i]）
     * @param jsjdm 计算机代码
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    private void collect(String jsjdm) throws ApplicationException
    {
        String defaultString = "0.00";
        Date today = new Date();

        // 税款所属日期
        Map sksjrqMap = Skssrq.otherSkssrq(today);
        String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
        String quarter = Skssrq.preQuarter(today);

        //企业收入总额	QYSRZE
        BigDecimal qysrzeCount = new BigDecimal(this.qysrze);
        //应税所得率	YSSDL
        BigDecimal yssdlCount = new BigDecimal(this.yssdl);
        //利润总额	LRZE
        BigDecimal lrzeCount = qysrzeCount.multiply(yssdlCount).setScale(2,
            BigDecimal.ROUND_HALF_EVEN);

        if(lrzeCount.compareTo(new BigDecimal(this.lrze)) != 0)
        {
            throw new ApplicationException("页面计算错误。");
        }

	// 使用可信的计算机代码，年度值，季度值
        this.qysbsj = newInstanceHdzsqysb(
            //计算机代码	JSJDM
            jsjdm,
            //年度	ND
            year,
            //季度	JD
            quarter,
            //创建时间	CJSJ
            this.cjsj,
            //录入日期	LRRQ
            this.lrrq,
            //税款所属开始日期	SKSSKSRQ
            this.skssksrq,
            //税款所属结束日期	SKSSJSRQ
            this.skssjsrq,
            //纳税人名称	NSRMC
            this.nsrmc,
            //税务登记证号	SWDJZH
            this.swdjzh,
            //企业收入总额	QYSRZE
            this.qysrze,
            //应税所得率	YSSDL
            this.yssdl,
            //利润总额	LRZE
            this.lrze,
            //税务机关组织机构代码	SWJGZZJGDM
            this.swjgzzjgdm,
            //录入人代码	LRR
            jsjdm,
            //申报日期	SBRQ
            this.sbrq,
            //登记申报方式代码	FSDM
            CodeConstant.FSDM_WSSB
            );
        // 使用可信的日期
        this.qysbsj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        this.qysbsj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        this.qysbsj.setLrrq(new Timestamp(today.getTime()));

	// 用来判断是否小于0的基准值
        BigDecimal zero = new BigDecimal(0);

        BigDecimal total = new BigDecimal(0);
        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        for(int i = 0, size = this.tzzxm.length; i < size; i++)
        {
            //期初未缴税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.qcwjsk[i])) > 0)
            {
                throw new ApplicationException("期初未缴税额应该大于等于0。");
            }
            //减免税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.jmse[i])) > 0)
            {
                throw new ApplicationException("减免税额应该大于等于0。");
            }
            //已缴纳税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.yjnse[i])) > 0)
            {
                throw new ApplicationException("已缴纳税额应该大于等于0。");
            }
            //实际应缴税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.sjyjse[i])) > 0)
            {
                throw new ApplicationException("实际应缴税额应该大于等于0。");
            }

            //验算逻辑是否正确
//            BigDecimal fpblCount = new BigDecimal(this.lrfpbl[i]);
            //应纳税所得额＝利润总额 × 利润分配比例
//            BigDecimal ynsdseCount = lrzeCount.multiply(fpblCount).divide(new
//                BigDecimal(100), BigDecimal.ROUND_HALF_EVEN).setScale(2,
//                BigDecimal.ROUND_HALF_EVEN);
//            total = total.add(fpblCount);
            //如利润总额为小于0：应纳税额为0
//            if(zero.compareTo(ynsdseCount) > 0)
//            {
//                ynsdseCount = new BigDecimal(0.00);
//            }
//            this.ynse[i] = ynsdseCount.toString();

            //实际应缴税额=投资者应纳税额－减免税额＋期初未缴税额－已缴纳税额
//            ynsdseCount.subtract(new BigDecimal(this.jmse[i])).add(new
//            BigDecimal(this.qcwjsk[i])).subtract(new BigDecimal(this.yjnse[i]));
//            BigDecimal sjyjseCount = new BigDecimal(this.sjyjse[i]);
//            if(zero.compareTo(sjyjseCount) > 0)
//            {
//                this.sjyjse[i] = "0.00";
//            }

            // 使用可信的计算机代码，年度值，季度值
            Hdzstzzsb hdzstzzsb = newInstanceHdzstzzsb(
                //计算机代码	JSJDM
                jsjdm,
                //年度	ND
                year,
                //季度	JD
                quarter,
                //证件类型代码	ZJLXDM
                this.zjlxdm[i],
                //证件号码	ZJHM
                this.zjhm[i],
                //投资者姓名	TZZXM
                this.tzzxm[i],
                //录入日期	LRRQ
                this.lrrq,
                //创建时间	CJSJ
                this.cjsj,
                //申报日期	SBRQ
                this.sbrq,
                //利润分配比例	LRFPBL
                this.lrfpbl[i],
                //应纳税所得额	YNSSDE
                null, //this.ynssde[i],
                //适用税率	SYSL
                null, //this.sysl[i],
                //速算扣除数	SSKCS
                null, //this.sskcs[i],
                //应纳税额	YNSE
                this.ynse[i],
                //减免税额	JMSE
                this.jmse[i],
                //期初未缴税额	QCWJSK
                this.qcwjsk[i],
                //已缴纳税额	YJNSE
                this.yjnse[i],
                //实际应缴税额	SJYJSE
                this.sjyjse[i],
                //税务机关组织机构代码	SWJGZZJGDM
		this.swjgzzjgdm
                );
            tzzsbsjList.add(hdzstzzsb);
        }
//        if(total.compareTo(new BigDecimal(100)) != 0)
//        {
//            throw new ApplicationException("全部投资者的分配比例和不为100。");
//        }
    }

    /**
     *   核定征收企业申报数据
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param jd 季度
     * @param cjsj  创建时间
     * @param lrrq 录入日期
     * @param skssksrq 税款所属开始日期
     * @param skssjsrq 税款所属结束日期
     * @param nsrmc 纳税人名称
     * @param swdjzh 税务登记证号
     * @param qysrze 企业收入总额
     * @param yssdl 应税所得率
     * @param lrze 利润总额
     * @param swjgzzjgdm 税务机关组织机构代码
     * @param lrr 录入人代码
     * @param sbrq 申报日期
     * @param fsdm 登记申报方式代码
     * @return  Hdzsqysb 核定征收企业申报数据
     */
    public Hdzsqysb newInstanceHdzsqysb(
        String jsjdm,
        String nd,
        String jd,
        String cjsj,
        String lrrq,
        String skssksrq,
        String skssjsrq,
        String nsrmc,
        String swdjzh,
        String qysrze,
        String yssdl,
        String lrze,
        String swjgzzjgdm,
        String lrr,
        String sbrq,
        String fsdm
        )
    {
        Hdzsqysb hdzsqysb = new Hdzsqysb();
        hdzsqysb.setJsjdm(jsjdm);
        hdzsqysb.setNd(nd);
        hdzsqysb.setJd(jd);
        hdzsqysb.setCjsj(getTimestamp(cjsj));
        hdzsqysb.setLrrq(getTimestamp(lrrq));
        hdzsqysb.setSkssksrq(getTimestamp(skssksrq));
        hdzsqysb.setSkssjsrq(getTimestamp(skssjsrq));
        hdzsqysb.setNsrmc(nsrmc);
        hdzsqysb.setSwdjzh(swdjzh);
        hdzsqysb.setQysrze(new BigDecimal(qysrze));
        hdzsqysb.setYssdl(new BigDecimal(yssdl));
        hdzsqysb.setLrze(new BigDecimal(lrze));
        hdzsqysb.setSwjgzzjgdm(swjgzzjgdm);
        hdzsqysb.setLrr(lrr);
        hdzsqysb.setSbrq(TinyTools.second2Day(getTimestamp(sbrq)));
        hdzsqysb.setFsdm(fsdm);
        return hdzsqysb;
    }

    /**
     * 把字符型的申报日期转换成时间戳型
     * @param sbrq 申报日期
     * @return Timestamp 申报日期
     */
    private Timestamp getTimestamp(String sbrq)
    {
        return new Timestamp(TinyTools.stringToDate(sbrq).getTime());
    }

    /**
     *  核定征收投资者申报数据
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param jd 季度
     * @param zjlxdm 证件类型代码
     * @param zjhm 证件号码
     * @param tzzxm 投资者姓名
     * @param lrrq  录入日期
     * @param cjsj 创建时间
     * @param sbrq 申报日期
     * @param lrfpbl 利润分配比例
     * @param ynssde 应纳税所得额
     * @param sysl 适用税率
     * @param sskcs 速算扣除数
     * @param ynse 应纳税额
     * @param jmse 减免税额
     * @param qcwjsk 期初未缴税额
     * @param yjnse 已缴纳税额
     * @param sjyjse 实际应缴税额
     * @return  核定征收投资者申报数据
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public Hdzstzzsb newInstanceHdzstzzsb(
        String jsjdm,
        String nd,
        String jd,
        String zjlxdm,
        String zjhm,
        String tzzxm,
        String lrrq,
        String cjsj,
        String sbrq,
        String lrfpbl,
        String ynssde,
        String sysl,
        String sskcs,
        String ynse,
        String jmse,
        String qcwjsk,
        String yjnse,
        String sjyjse,
        String swjgzzjgdm
        )
    {
        Hdzstzzsb hdzstzzsb = new Hdzstzzsb();
        hdzstzzsb.setJsjdm(jsjdm);
        hdzstzzsb.setNd(nd);
        hdzstzzsb.setJd(jd);
        hdzstzzsb.setZjlxdm(zjlxdm);
        hdzstzzsb.setZjhm(zjhm);
        hdzstzzsb.setTzzxm(tzzxm);
        hdzstzzsb.setLrrq(getTimestamp(lrrq));
        hdzstzzsb.setCjsj(getTimestamp(cjsj));
        hdzstzzsb.setSbrq(TinyTools.second2Day(getTimestamp(sbrq)));
        hdzstzzsb.setLrfpbl(new BigDecimal(lrfpbl));
        hdzstzzsb.setYnssde(new BigDecimal(0));
        hdzstzzsb.setSysl(new BigDecimal(0));
        hdzstzzsb.setSskcs(new BigDecimal(0));
        hdzstzzsb.setYnse(new BigDecimal(ynse));
        hdzstzzsb.setJmse(new BigDecimal(jmse));
        hdzstzzsb.setQcwjsk(new BigDecimal(qcwjsk));
        hdzstzzsb.setYjnse(new BigDecimal(yjnse));
        hdzstzzsb.setSjyjse(new BigDecimal(sjyjse));
        hdzstzzsb.setSwjgzzjgdm(swjgzzjgdm);
        return hdzstzzsb;
    }

    // getter & setter
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public List getJmsjList()
    {
        return jmsjList;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setLrfpbl(String[] lrfpbl)
    {
        this.lrfpbl = lrfpbl;
    }

    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }

    public void setQcwjsk(String[] qcwjsk)
    {
        this.qcwjsk = qcwjsk;
    }

    public Hdzsqysb getQysbsj()
    {
        return qysbsj;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSjyjse(String[] sjyjse)
    {
        this.sjyjse = sjyjse;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public void setQysrze(String qysrze)
    {
        this.qysrze = qysrze;
    }

    public void setSskcs(String[] sskcs)
    {
        this.sskcs = sskcs;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSysl(String[] sysl)
    {
        this.sysl = sysl;
    }

    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public void setTzzsfzjhm(String[] tzzsfzjhm)
    {
        this.tzzsfzjhm = tzzsfzjhm;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setYjnse(String[] yjnse)
    {
        this.yjnse = yjnse;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public void setYnssde(String[] ynssde)
    {
        this.ynssde = ynssde;
    }

    public void setYssdl(String yssdl)
    {
        this.yssdl = yssdl;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getSwdjzh()
    {
        return swdjzh;
    }

    public void setSwdjzh(String swdjzh)
    {
        this.swdjzh = swdjzh;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm(String[] zjhm)
    {
        this.zjhm = zjhm;
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