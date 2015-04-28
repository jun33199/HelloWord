//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsjd\\web\\CzzsjdForm.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsjd.web;

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
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * 查账征收季度
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class CzzsjdForm extends SbzlBaseForm
{

    /**
     * 申报日期 String
     *
     * 不可编辑，值为当前日期
     */
    private String sbrq;

    /**
     * 税务计算机代码 String
     *
     * 从登录数据中取得
     */
    private String jsjdm;

    /**
     * 单位名称 String
     *
     * 根据计算机代码从登记数据中取得
     */
    private String nsrmc;

    /**
     * 利润总额 String
     *
     * 录入，必须是数字
     *
     * 如果用户输入'利润总额'，系统根据计算公式计算出应纳税所得额、应纳税额及实际应缴税
     * 额，并计算出实际应缴税额合计。
     *
     * 如利润总额为小于0：
     * 应纳税所得额为利润总额×利润分配比例
     * 应纳税额为0
     * 期初未缴税额和已缴纳税额录入（大于等于0）
     * 实际应缴税额缺省设为0，并可修改（大于等于0）
     */
    private String lrze;

    /**
     * 税款所属开始日期
     *
     * 系统计算得出
     */
    private String skssksrq;

    /**
     * 税款所属结束日期
     *
     * 系统计算得出
     */
    private String skssjsrq;

    /**
     * 投资者姓名 String[]
     *
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     */
    private String[] tzzxm;

    /**
     * 投资者身份证件号码 String[]
     *
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     */
    private String[] zjhm;

    /**
     * 利润分配比例 String[]
     *
     * 不可编辑，根据计算机代码从投资者数据中查询带出
     */
    private String[] fpbl;

    /**
     * 应纳税所得额 String[]
     *
     * 根据利润总额和利润分配比例计算，并且不可编辑
     *
     * 应纳税所得额=利润总额×利润分配比例。
     *
     * 根据应纳税所得额检索"税率表--个体工商户生产经营和承包、承租经营所得适用"得到税率
     * 和速算扣除数，计算应纳税额。
     */
    private String[] ynssde;

    /**
     * 应纳税额 String[]
     *
     * 根据应纳税所得额计算，并且不可编辑
     *
     * 应纳税额＝应纳税所得额×适用税率－速算扣除数。
     */
    private String[] ynsdse;

    /**
     * 期初未缴税额 String[]
     *
     * 录入，必须是大于等于0的数字
     *
     * 如果用户输入某一投资者的'期初未缴税额'或'已缴纳税额'，系统根据计算公式计算出该投
     * 资者的实际应缴税额，并计算出实际应缴税额合计。
     */
    private String[] qcwjsdse;

    /**
     * 已缴纳税额 String[]
     *
     * 录入，必须是大于等于0的数字
     *
     * 如果用户输入某一投资者的'期初未缴税额'或'已缴纳税额'，系统根据计算公式计算出该投
     * 资者的实际应缴税额，并计算出实际应缴税额合计。
     */
    private String[] yjnsdse;

    /**
     * 实际应缴税额 String[]
     *
     * 由应纳税额、期初未缴税额和已缴纳税额计算，可以修改（应大于等于0）
     *
     * 实际应缴税额=应纳税额＋期初未缴税额－已缴纳税额。
     */
    private String[] sjyjse;

    /**
     * 合计 String
     *
     * 实际缴税额的合计数，不可修改
     *
     * 合计＝∑实际缴税额。
     */
    private String hj;

    /**
     * 税率表数据
     *
     * List型数据，里面的对象是 税率表数据 值对象
     */
    private List slbsjList;

    /**
     * 企业申报数据
     *
     * 查帐征收季报企业数据 值对象
     */
    private Czzsjbqysj qysbsj;

    /**
     * 投资者申报数据
     *
     * List型数据，里面的对象是和投资者数目相等的 查帐征收季报投资者数据 值对象
     */
    private List tzzsbsjList;

    // 以下是隐含域
    /**
     * 年度
     */
    private String nd;

    /**
     * 季度
     */
    private String jd;

    /**
     * 创建时间
     */
    private String cjsj;

    /**
     * 税务登记证号
     */
    private String swdjzh;

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     * 录入人代码
     */
    private String lrr;

    /**
     * 证件类型代码
     */
    private String[] zjlxdm;

    /**
     * 适用税率
     *
     * String[]
     */
    private String[] sysl;

    /**
     * 速算扣除数
     *
     * String[]
     */
    private String[] sskcs;
    /**
     * 减免税额
     *
     * String[]
     */
    private String[] jmse;
    /**
     * 录入日期 String
     *
     * 不可编辑，值为当前日期
     */
    private String lrrq;

    /**
     * 企业征收方式数据结构
     */
    private Grzsfs grzsfs;

    /**
     * 是否数据能正常调到数据区
     */
    private boolean done = true;

    /**
     * 收集页面数据转换成值对象放到qysbsj和tzzsbsjList中。
     *
     * 以下的表字段需要从页面上取值，值对象的其他字段不变，其中查帐征收季报投资者数据是
     * 根据证件类型代码ZJLXDM和证件号码ZJHM域来确定特定的值对象。
     *
     * 查帐征收季报企业数据 值对象
     * 利润总额	LRZE（form.lrze）
     *
     * 查帐征收季报投资者数据 值对象
     * 应纳税所得额	YNSSDE（form.ynssde）
     * 适用税率	SYSL（form.sysl）
     * 速算扣除数	SSKCS（form.sskcs）
     * 应纳所得税额	YNSDSE（form.ynssde）
     * 减免税额	JMSE（留空）
     * 期初未缴所得税额	QCWJSDSE（form.qcwjse）
     * 已缴纳所得税额	YJNSDSE（form.yjnse）
     * 实际应缴税额	SJYJSE（form.sjyjse）
     * @param jsjdm 计算机代码
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    private void collect(String jsjdm) throws ApplicationException
    {

        Date today = new Date();

        // 税款所属日期
        Map sksjrqMap = Skssrq.otherSkssrq(today);
        String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
        String quarter = Skssrq.preQuarter(today);
        int quarterInt = Integer.parseInt(quarter);

        // 用来判断是否小于0的基准值
        BigDecimal zero = new BigDecimal(0);
        // 使用可信的计算机代码，年度值，季度值
        this.qysbsj = newInstanceCzzsjdQysbsj(
            jsjdm,
            year,
            quarter,
            this.cjsj,
            this.lrrq,
            this.skssksrq,
            this.skssjsrq,
            this.nsrmc,
            this.lrze,
            this.swdjzh,
            this.swjgzzjgdm,
            this.lrr,
            this.lrrq,
            CodeConstant.FSDM_WSSB
            );
        // 使用可信的日期
        this.qysbsj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        this.qysbsj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        this.qysbsj.setLrrq(new Timestamp(today.getTime()));
        this.qysbsj.setSbrq(TinyTools.second2Day(new Timestamp(today.getTime())));
        this.qysbsj.setCjrq(new Timestamp(today.getTime()));

        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        BigDecimal total = new BigDecimal(0);
        for(int i = 0, size = this.tzzxm.length; i < size; i++)
        {
            //算逻辑是否正确
            //验证内容
            //应纳税所得额＝利润总额 × 利润分配比例
            //如利润总额为小于0：应纳税额为0

            //期初未缴税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.qcwjsdse[i])) > 0)
            {
                throw new ApplicationException("期初未缴税额应该大于等于0。");
            }
            //已缴纳税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.ynsdse[i])) > 0)
            {
                throw new ApplicationException("已缴纳税额应该大于等于0。");
            }
            //实际应缴税额（大于等于0）
            if(zero.compareTo(new BigDecimal(this.yjnsdse[i])) > 0)
            {
                throw new ApplicationException("实际应缴税额应该大于等于0。");
            }

            //应纳税额＝应纳税所得额×适用税率－速算扣除数
//            BigDecimal lrzeCount = new BigDecimal(this.lrze);
//            BigDecimal fpblCount = new BigDecimal(this.fpbl[i]);
            // 因为适用税率从数据库里出来的是0到100之间的数据，需要除于100。
//            BigDecimal ynssdeCount = lrzeCount.multiply(fpblCount).divide(new
//                BigDecimal(100), BigDecimal.ROUND_HALF_EVEN).setScale(2,
//                BigDecimal.ROUND_HALF_EVEN);
//            BigDecimal ynsdseCount = new BigDecimal(0);
//            total = total.add(fpblCount);
//            if(ynssdeCount.compareTo(zero) > 0)
//            {
                //查账季度申报的计算要用应纳税所得额/（当前季度－1）×4，
                //推算出全年的税额，用这个全年的税额找税率表中的税率，再计算。
                // 注意，按照这个算法，这个业务模块是不能从一季度进来的。
//                BigDecimal extend = ynssdeCount.divide(
//                    new BigDecimal(quarterInt), BigDecimal.ROUND_HALF_EVEN).
//                    multiply(new BigDecimal(4));
                // 查找所得额那一档的税率和速算扣除数。
//                BigDecimal[] temp = getLevel(extend, this.slbsjList);
//                ynsdseCount = ynssdeCount.multiply(temp[0]).subtract(temp[1]);
//                this.sysl[i] = temp[0].toString();
//                this.sskcs[i] = temp[1].toString();
//            } else
//            {
//                this.sysl[i] = "0.00";
//                this.sskcs[i] = "0.00";
//            }
//            this.ynsdse[i] = ynsdseCount.toString();
            Czzsjbtzzsj czzsjbtzzsj = newInstanceCzzsjdTzzsbsj(
                jsjdm,
                year,
                quarter,
                this.zjlxdm[i],
                this.zjhm[i],
                this.tzzxm[i],
                this.lrrq,
                this.fpbl[i],
                this.ynssde[i],
                this.sysl[i],
                this.sskcs[i],
                this.ynsdse[i],
                this.jmse[i],
                this.qcwjsdse[i],
                this.yjnsdse[i],
                this.sjyjse[i],
                this.swjgzzjgdm
                );
            this.tzzsbsjList.add(czzsjbtzzsj);
        }
//        if(total.compareTo(new BigDecimal(100)) != 0)
//        {
//            throw new ApplicationException("全部投资者的分配比例和不为100。");
//        }
    }

    /**
     * 在税率表数据中找出合适的一挡返回适用税率和速算扣除数
     * @param find 需要查挡的数据
     * @param slbList 税率表数据
     * @return BigDecimal[] 适用税率和速算扣除数
     */
    private BigDecimal[] getLevel(BigDecimal find, List slbList)
    {
        BigDecimal zero = new BigDecimal(0);
        BigDecimal[] temp = new BigDecimal[2];
        for(int i = 0, size = slbList.size(); i < size; i++)
        {
            Szsm szsm = (Szsm)slbList.get(i);
            //大于 起始 并且 （小于 终止 或者 终止 == 0）
            if(find.compareTo(szsm.getYnsqss()) > 0
               && (find.compareTo(szsm.getYnszzs()) <= 0
                   || zero.compareTo(szsm.getYnszzs()) == 0))
            {
                temp[0] = szsm.getSl();
                temp[1] = szsm.getSskcs();
                break;
            }
        }
        return temp;
    }

    /**
     * 从data中使用key为CzzsjdMapConstant.QYSBSJ取企业申报数据放到form的qysbsj中，
     * 使用key为CzzsjdMapConstant.LIST_TZZSBSJ取投资者申报数据放到form的tzzsbsjList中，
     * @param data 返回的数据
     */
    public void afterQuery(Map data)
    {
        this.qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
        this.tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);
    }

    /**
     * 收集信息转换成值对象，
     * 生成Map，
     * 使用key为CzzsjdMapConstant.QYSBSJ放企业申报数据qysbsj，
     * 使用key为CzzsjdMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList，
     * 返回此Map。
     * @param jsjdm 计算机代码
     * @return Map
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     * 生成Map，使用key为CzzsjdMapConstant.JSJDM放入计算机代码，返回此Map。
     * @param jsjdm 计算机代码
     * @param tzfList 投资方数据
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(String jsjdm, List tzfList) throws BaseException
    {
        Map map = new HashMap(2);

        map.put(CzzsjdMapConstant.JSJDM, jsjdm);
        map.put(CzzsjdMapConstant.LIST_TZF, tzfList);
        return map;
    }

    /**
     * 收集信息转换成值对象
     * 生成Map，
     * 使用key为CzzsjdMapConstant.QYSBSJ放企业申报数据qysbsj，
     * 使用key为CzzsjdMapConstant.LIST_TZZSBSJ放投资者申报数据tzzsbsjList，
     * 返回此Map。
     * @param jsjdm 计算机代码
     * @return Map 返回需要删除的值对象
     * @throws ApplicationException 校验没有通过的时候抛出异常
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     *
     * @param lrrq     录入日期
     * @param qcwjsdse 期初未缴所得税额
     * @param sjyjse   实际应缴税额
     * @param jsjdm    计算机代码
     * @param zjhm     证件号码
     * @param tzzxm    投资者姓名
     * @param ynssde   应纳所得税额
     * @param nd       年度
     * @param zjlxdm   证件类型代码
     * @param sysl     适用税率
     * @param jmse     减免税额
     * @param jd       季度
     * @param sskcs    速算扣除数
     * @param ynsdse   应纳所得税额
     * @param fpbl     分配比例
     * @param yjnsdse  已缴纳所得税额
     * @param swjgzzjgdm  税务机关组织机构代码
     * @return     Czzsjbtzzsj 查帐征收季报投资者数据 值对象
     */
    public Czzsjbtzzsj newInstanceCzzsjdTzzsbsj(
        String jsjdm,
        String nd,
        String jd,
        String zjlxdm,
        String zjhm,
        String tzzxm,
        String lrrq,
        String fpbl,
        String ynssde,
        String sysl,
        String sskcs,
        String ynsdse,
        String jmse,
        String qcwjsdse,
        String yjnsdse,
        String sjyjse,
        String swjgzzjgdm
        )
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Czzsjbtzzsj czzsjbtzzsj = new Czzsjbtzzsj();
        //计算机代码
        czzsjbtzzsj.setJsjdm(jsjdm);
        //年度
        czzsjbtzzsj.setNd(nd);
        //季度
        czzsjbtzzsj.setJd(jd);
        //证件类型代码
        czzsjbtzzsj.setZjlxdm(zjlxdm);
        //证件号码
        czzsjbtzzsj.setZjhm(zjhm);
        //投资者姓名
        czzsjbtzzsj.setTzzxm(tzzxm);
        //录入日期
        czzsjbtzzsj.setLrrq(now);
        //分配比例
        czzsjbtzzsj.setFpbl(new BigDecimal(fpbl));
        //应纳所得税额
        czzsjbtzzsj.setYnssde(new BigDecimal(ynssde));
        //应纳所得税额
        czzsjbtzzsj.setSysl(new BigDecimal(sysl));
        //速算扣除数
        czzsjbtzzsj.setSskcs(new BigDecimal(sskcs));
        //应纳所得税额
        czzsjbtzzsj.setYnsdse(new BigDecimal(ynsdse));
        //减免税额
        czzsjbtzzsj.setJmse(new BigDecimal(jmse));
        //期初未缴所得税额
        czzsjbtzzsj.setQcwjsdse(new BigDecimal(qcwjsdse));
        //已缴纳所得税额
        czzsjbtzzsj.setYjnsdse(new BigDecimal(yjnsdse));
        //实际应缴税额
        czzsjbtzzsj.setSjyjse(new BigDecimal(sjyjse));
        //税务机关组织机构代码
        czzsjbtzzsj.setSwjgzzjgdm(swjgzzjgdm);

        czzsjbtzzsj.setCjrq(now);
        czzsjbtzzsj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsjbtzzsj;
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
     *
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param jd 季度
     * @param cjsj 创建时间
     * @param lrrq 录入日期
     * @param skssksrq 税款所属开始日期
     * @param skssjsrq 税款所属结束日期
     * @param nsrmc 纳税人名称
     * @param lrze 利润总额
     * @param swdjzh 税务登记证号
     * @param swjgzzjgdm  税务机关组织机构代码
     * @param lrr 录入人代码
     * @param sbrq 申报日期
     * @param fsdm 方式代码
     * @return  Czzsjbqysj 查帐征收季报企业数据 值对象
     */
    public Czzsjbqysj newInstanceCzzsjdQysbsj(
        String jsjdm,
        String nd,
        String jd,
        String cjsj,
        String lrrq,
        String skssksrq,
        String skssjsrq,
        String nsrmc,
        String lrze,
        String swdjzh,
        String swjgzzjgdm,
        String lrr,
        String sbrq,
        String fsdm
        )
    {

        Czzsjbqysj czzsjbqysj = new Czzsjbqysj();
//	* @param jsjdm 计算机代码
        czzsjbqysj.setJsjdm(jsjdm);
//      * @param nd 年度
        czzsjbqysj.setNd(nd);
//    	* @param jd 季度
        czzsjbqysj.setJd(jd);
//    	* @param cjsj 创建时间
//        czzsjbqysj.setCjrq(getTimestamp(cjsj));
//    	* @param lrrq 录入日期
//        czzsjbqysj.setLrr(lrr);
//    	* @param skssksrq 税款所属开始日期
        czzsjbqysj.setSkssksrq(getTimestamp(skssksrq));
//    	* @param skssjsrq 税款所属结束日期
        czzsjbqysj.setSkssjsrq(getTimestamp(skssjsrq));
//    	* @param nsrmc 纳税人名称
        czzsjbqysj.setNsrmc(nsrmc);
//    	* @param lrze 利润总额
        czzsjbqysj.setLrze(new BigDecimal(lrze));
//    	* @param swdjzh 税务登记证号
        czzsjbqysj.setSwdjzh(swdjzh);
//    	* @param swjgzzjgdm  税务机关组织机构代码
        czzsjbqysj.setSwjgzzjgdm(swjgzzjgdm);
//    	* @param lrr 录入人代码
        czzsjbqysj.setLrr(lrr);
//    	* @param sbrq 申报日期
//        czzsjbqysj.setSbrq(getTimestamp(sbrq));
//    	* @param fsdm 方式代码
        czzsjbqysj.setFsdm(fsdm);
        czzsjbqysj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsjbqysj;
    }

    // getter && setter
    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public Czzsjbqysj getQysbsj()
    {
        return qysbsj;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public void setFpbl(String[] fpbl)
    {
        this.fpbl = fpbl;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setQcwjsdse(String[] qcwjsdse)
    {
        this.qcwjsdse = qcwjsdse;
    }

    public void setSjyjse(String[] sjyjse)
    {
        this.sjyjse = sjyjse;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSskcs(String[] sskcs)
    {
        this.sskcs = sskcs;
    }

    public void setSwdjzh(String swdjzh)
    {
        this.swdjzh = swdjzh;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSysl(String[] sysl)
    {
        this.sysl = sysl;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setYjnsdse(String[] yjnsdse)
    {
        this.yjnsdse = yjnsdse;
    }

    public void setYnsdse(String[] ynsdse)
    {
        this.ynsdse = ynsdse;
    }

    public void setYnssde(String[] ynssde)
    {
        this.ynssde = ynssde;
    }

    public void setZjhm(String[] zjhm)
    {
        this.zjhm = zjhm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    public boolean isDone()
    {
        return done;
    }

}