//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\hdzsgrsds\\processor\\HdzsgrsdsProcessor.java

package com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzsqysb;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

/**
 *
 * 模块设计思想--doQuery方法：
 * 	前台传入必需的资料（详细的参照查询方法的传入参数），
 * 	后台进行查询，如果存在本期申报数据，返回取得的数据。
 * 	如果不存在本期申报数据，生成本期申报数据，缺省填充后返回。
 * 	在本设计思想中前台面对后台的接口在无论是否存在本期申报数据时都是一致的。
 * 模块设计思想--doSave方法：
 * 	前台传入本期申报数据，
 * 	后台先进行数据库本期申报数据的删除操作，再做保存操作。
 * 	（所有出现在页面上的数据都是保存的数据，所见即所得。）
 * 模块设计思想--doDelete方法：
 * 	前台传入本期申报数据，
 * 	后台进行数据库本期申报数据的删除操作。
 *
 * 本处理器可以处理2种类型的核定申报，区别只是值对象中填充不同的字段。
 *
 * @author Haifeng Su
 * @version 1.0
 *
 * 核定征收个人独资个人合伙个人所得税申报
 */
public class HdzsgrsdsProcessor implements Processor
{

    /**
     * 总控用户数据
     */
    private UserData userData;
    /**
     * 登记常量
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * 操作参数出错时的异常信息
     */
    public static final String ILLEGALACTION = "操作出错!";
    /**
     *orManage的常量
     */
    private static final long SESSIONID = 0;

    /**
     * 实现Processor接口
     * @param voPackage 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     * 		1 当传过来的操作类型不对时抛出
     * 		2 当调用的业务方法抛出业务异常时向上传递抛出
     * 	其他异常抛出由EJB的process方法处理。
     */
    public Object process(VOPackage voPackage) throws BaseException
    {

        this.userData = voPackage.getUserData();
        // 根据业务操作类型值来做业务操作
        try
        {
            switch(voPackage.getAction())
            {
                case HdzsgrsdsActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    break;
                case HdzsgrsdsActionConstant.INT_ACTION_SAVE:
                    voPackage.setData(doSave((Map)voPackage.getData()));
                    break;
                case HdzsgrsdsActionConstant.INT_ACTION_DELETE:
                    voPackage.setData(doDelete((Map)voPackage.getData()));
                    break;
                default:
                    throw new ApplicationException(ILLEGALACTION);
            }
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return voPackage;
    }

    /**
     * @param data Map类型参数
     * @return Map
     * @throws Exception
     */
    private Map doQuery(Map data) throws Exception
    {

        //保存结果数据结构
        Map map = new HashMap(2);
        //定义数据库连接
        Connection connection = null;

        // 企业申报数据
        Hdzsqysb qysbsj = null;
        // 投资者申报数据
        List tzzsbsjList = null;

        try
        {
            // 取企业计算机代码使用key为HdzsgrsdsMapConstant.JSJDM
            String jsjdm = (String)data.get(HdzsgrsdsMapConstant.JSJDM);
            // 取企业征收率或者应税所得率
            BigDecimal yssdl = new BigDecimal((String)data.get(
                HdzsgrsdsMapConstant.ZSL));
            // 取投资方数据
            List tzfList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZF);

            //当前日期调用公共接口产生当期所属 年度和季度
            Date today = new Date();
            Map sksjrqMap = Skssrq.otherSkssrq(today);
            String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
            String quarter = Skssrq.preQuarter(today);

            // 通过登记的接口取登记数据
            // 参数：计算机代码
            // 根据接口文档，没有该纳税人的时候会抛出BaseException。
            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);

            // 登记基本数据
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
            // 取国家标准行业代码
            String hydm = jbsj.getGjbzhydm();

            // 获得 ORManager实例
            ORManager orManager = DBResource.getORManager();
            // 获得数据库连接
            connection = DBResource.getConnection();
            // ORManager的条件语句
            Vector criteria = new Vector();
            // ORManager的条件上下文
            ORContext orContext = null;

            // 取当期申报数据
            //	使用ORManager对表“核定征收企业申报数据”取企业申报数据
            //	参数：计算机代码、年度、季度
            criteria.clear();
            criteria.add("jsjdm = '" + jsjdm + "'");
            criteria.add("nd ='" + year + "'");
            criteria.add("jd ='" + quarter + "'");
            orContext = new ORContext(Hdzsqysb.class.getName(), criteria);
            List qysbsjList = orManager.query(this.SESSIONID, connection,
                                              orContext);
            if(qysbsjList.size() == 0)
            {
                // 如果没有当期的数据，生成这些数据返回。
                qysbsj = newInstanceHdzsqysb(djMap, jsjdm, year, quarter, yssdl);
                tzzsbsjList = newInstanceHdzstzzsbList(djMap, tzfList, jsjdm,
                    year, quarter);
                // 注：如果希望早些放弃对数据库连接的占用，
                // 可以考虑把这段产生代码放到try的外层去。
            } else
            {
                qysbsj = (Hdzsqysb)qysbsjList.get(0);
                qysbsj.setNsrmc(jbsj.getNsrmc());
                //使用ORManager对表“核定征收投资者申报数据”取投资者申报数据
                //参数：计算机代码、年度、季度(使用和企业数据相同的条件)
                orContext = new ORContext(Hdzstzzsb.class.getName(), criteria);
                tzzsbsjList = orManager.query(this.SESSIONID, connection,
                                              orContext);
                if(tzzsbsjList.size() == 0)
                {
                    throw new ApplicationException("取不到核定征收投资者申报数据!");
                }
            }
        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询操作失败!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }

        // 放置返回值
        map.put(HdzsgrsdsMapConstant.QYSBSJ, qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, tzzsbsjList);

        return map;
    }

    /**
     * @param data Map类型参数
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doSave(java.util.Map data) throws BaseException
    {

        //保存结果数据结构
        Map map = new HashMap(1);
        //定义数据库连接
        Connection connection = null;
        try
        {
            // 取企业申报数据使用key为HdzsgrsdsMapConstant.QYSBSJ
            Hdzsqysb qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
            // 取投资者申报数据使用key为HdzsgrsdsMapConstant.LIST_TZZSBSJ
            List tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);

            // 对存在的本期申报先删后插
            this.doDelete(data);

            // 获得 ORManager实例
            ORManager orManager = DBResource.getORManager();
            // 获得数据库连接
            connection = DBResource.getConnection();

            orManager.makePersistent(SESSIONID, connection, qysbsj);
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection,
                                         tzzsbsjList.get(i));
            }

        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存操作失败!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }
        map.put(HdzsgrsdsMapConstant.RESULT, Boolean.TRUE);
        return map;
    }

    /**
     * 返回已填充数值的企业数据
     * @param djMap 登记数据
     * @param jsjdm 计算机代码
     * @param year 年份
     * @param quarter 季度
     * @param yssdl 应税所得率
     * @return 企业申报数据
     */
    private Hdzsqysb newInstanceHdzsqysb(Map djMap, String jsjdm, String year,
                                         String quarter, BigDecimal yssdl)
    {

        Date today = new Date();
        // To be changed.
        Timestamp now = new Timestamp(today.getTime());
        // 登记基本数据
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        // 税款所属日期
        Map sksjrqMap = Skssrq.otherSkssrq(today);

        //核定征收企业申报数据
        Hdzsqysb hdzsqysb = new Hdzsqysb();
        //计算机代码	JSJDM（登记数据）
        hdzsqysb.setJsjdm(jsjdm);
        //年度	ND（系统公共接口）
        hdzsqysb.setNd(year);
        //季度	JD（系统公共接口）
        hdzsqysb.setJd(quarter);
        //创建时间	CJRQ（系统公共接口）
        hdzsqysb.setCjsj(now);
        //录入日期	LRRQ（系统公共接口）
        hdzsqysb.setLrrq(now);
        //税款所属开始日期	SKSSKSRQ（系统公共接口）
        hdzsqysb.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //税款所属结束日期	SKSSJSRQ（系统公共接口）
        hdzsqysb.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //纳税人名称	NSRMC（登记数据）
        hdzsqysb.setNsrmc(jbsj.getNsrmc());
        //税务登记证号	SWDJZH（登记数据）
        hdzsqysb.setSwdjzh(jbsj.getSwdjzh());
        //企业收入总额	QYSRZE（留空）
        hdzsqysb.setQysrze(new BigDecimal(0.00));
        //应税所得率	YSSDL（税费数据）
        hdzsqysb.setYssdl(yssdl);
        //利润总额	LRZE（留空）
        hdzsqysb.setLrze(new BigDecimal(0.00));
        //税务机关组织机构代码	SWJGZZJGDM（登记数据）
        hdzsqysb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //录入人代码	LRR（UserData）
        hdzsqysb.setLrr(this.userData.yhid);
        //申报日期	SBRQ
        hdzsqysb.setSbrq(TinyTools.second2Day(now));

        return hdzsqysb;
    }

    /**
     * 返回已填充数值的投资者数据
     * @param djMap 登记数据
     * @param tzfList 投资方数据
     * @param jsjdm 计算机代码
     * @param year 年份
     * @param quarter 季度
     * @return 投资者申报数据
     */
    private List newInstanceHdzstzzsbList(Map djMap, List tzfList, String jsjdm,
                                          String year, String quarter)
    {
        List list = new ArrayList();
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // 登记基本数据
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        for(int i = 0, size = tzfList.size(); i < size; i++)
        {
            Tzf tzf = (Tzf)tzfList.get(i);
            //核定征收投资者申报数据
            Hdzstzzsb hdzstzzsb = new Hdzstzzsb();
            //计算机代码	JSJDM（登记数据）
            hdzstzzsb.setJsjdm(jsjdm);
            //年度	ND（系统公共接口）
            hdzstzzsb.setNd(year);
            //季度	JD（系统公共接口）
            hdzstzzsb.setJd(quarter);
            //证件类型代码	ZJLXDM（登记数据）
            hdzstzzsb.setZjlxdm(tzf.getZjlxdm());
            //证件号码	ZJHM（登记数据）
            hdzstzzsb.setZjhm(tzf.getZjhm());
            //录入日期	LRRQ（系统公共接口）
            hdzstzzsb.setLrrq(now);
            //创建时间	CJRQ（系统公共接口）
            hdzstzzsb.setCjsj(now);
            //申报日期	SBRQ（系统公共接口）
            hdzstzzsb.setSbrq(TinyTools.second2Day(now));
            //利润分配比例	LRFPBL（登记数据）
            hdzstzzsb.setLrfpbl(tzf.getFpbl());
            //应纳税所得额	YNSSDE（留空）
            hdzstzzsb.setYnssde(new BigDecimal(0.00));
            //适用税率	SYSL（留空）
            hdzstzzsb.setSysl(new BigDecimal(0.00));
            //速算扣除数	SSKCS（留空）
            hdzstzzsb.setSskcs(new BigDecimal(0.00));
            //应纳税额	YNSE（留空）
            hdzstzzsb.setYnse(new BigDecimal(0.00));
            //减免税额	JMSE（留空）
            hdzstzzsb.setJmse(new BigDecimal(0.00));
            //期初未缴税额	QCWJSK（留空）
            hdzstzzsb.setQcwjsk(new BigDecimal(0.00));
            //已缴纳税额	YJNSE（留空）
            hdzstzzsb.setYjnse(new BigDecimal(0.00));
            //实际应缴税额	SJYJSE（留空）
            hdzstzzsb.setSjyjse(new BigDecimal(0.00));
            //税务机关组织机构代码	SWJGZZJGDM（登记数据）
            hdzstzzsb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

            list.add(hdzstzzsb);
        }
        return list;
    }

    /**
     * @param data Map类型参数
     * @return Map
     * @throws Exception
     */
    private Map doDelete(Map data) throws Exception
    {
        //保存结果数据结构
        Map map = new HashMap(1);
        //定义数据库连接
        Connection connection = null;
        try
        {
            // 取企业申报数据使用key为CzzsjdMapConstant.QYSBSJ
            Hdzsqysb qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
            // 取投资者申报数据使用key为CzzsjdMapConstant.LIST_TZZSBSJ
            List tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);

            // 获得 ORManager实例
            ORManager orManager = DBResource.getORManager();
            // 获得数据库连接
            connection = DBResource.getConnection();

            // 删除申报数据
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.deleteObject(SESSIONID, connection, tzzsbsjList.get(i));
            }
            orManager.deleteObject(SESSIONID, connection, qysbsj);

        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除操作失败!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }
        map.put(HdzsgrsdsMapConstant.RESULT, Boolean.TRUE);
        return map;
    }

}