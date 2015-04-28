package com.ttsoft.bjtax.shenbao.sbzl.czzsjd.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdActionConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.framework.exception.*;

/**
 *
 * 模块设计思想--doQuery方法：
 * 	前台传入必需的资料（详细的参照查询方法的传入参数），
 * 	后台进行查询，如果存在本期申报数据，返回取得的数据。
 * 	如果不存在本期申报数据，生成本期申报数据，缺省填充后返回。
 * 模块设计思想--doSave方法：
 * 	前台传入本期申报数据，
 * 	后台先进行数据库本期申报数据的删除操作，再做保存操作。
 * 	（所有出现在页面上的数据都是保存的数据，所见即所得。）
 * 模块设计思想--doDelete方法：
 * 	前台传入本期申报数据，
 * 	后台进行数据库本期申报数据的删除操作。
 *
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 查账征收季度申报的后台processor</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Haifeng Su
 * @version 1.0 2003-8-30
 */

public class CzzsjdProcessor implements Processor
{

    /**
     * 总控数据
     */
    private UserData userData;

    /**
     * 登记常量
     */
    private static final String DJ_JBSJ = "JBSJ";

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
                case CzzsjdActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    return voPackage;

                case CzzsjdActionConstant.INT_ACTION_SAVE:
                    doSave((Map)voPackage.getData());
                    return null;

                case CzzsjdActionConstant.INT_ACTION_DELETE:
                    doDelete((Map)voPackage.getData());
                    return null;

                default:
                    throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取得税率表数据、企业申报数据和投资者申报数据
     * @param data Map型数据
     * @return Map
     * @throws Exception 当其他情况都会抛出异常信息
     */
    private Map doQuery(Map data) throws Exception
    {
        //保存结果数据结构
        Map map = new HashMap(2);
        //定义数据库连接
        Connection connection = null;

        // 企业申报数据
        Czzsjbqysj czzsjbqysj = null;
        // 投资者申报数据
        List tzzsbsjList = null;

        try
        {
            // 取企业计算机代码使用key为CzzsjdMapConstant.JSJDM
            String jsjdm = (String)data.get(CzzsjdMapConstant.JSJDM);
            // 取投资方数据
            List tzfList = (List)data.get(CzzsjdMapConstant.LIST_TZF);

            //当前日期调用公共接口产生当期所属 年度和季度
            Date today = new Date();
            Map sksjrqMap = Skssrq.otherSkssrq(today);
            String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
            String quarter = Skssrq.preQuarter(today);

            // 通过登记的接口取登记数据
            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);

            Vector criteria = new Vector();
            ORContext orContext = null;

            // 取当期申报数据
            criteria.clear();
            criteria.add("jsjdm = '" + jsjdm + "'");  // 计算机代码
            criteria.add("nd ='" + year + "'");  // 年度
            criteria.add("jd ='" + quarter + "'");  // 季度
            criteria.add("fsdm ='" + CodeConstant.FSDM_WSSB + "'");  // 方式代码
            criteria.add("qxdm ='" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");  // 区县代码

            orContext = new ORContext(Czzsjbqysj.class.getName(), criteria);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            List qysbsjList = orManager.query(this.SESSIONID, connection, orContext);
            if(qysbsjList.size() == 0)
            {
                // 如果没有当期的数据，生成这些数据返回。
                czzsjbqysj = newInstanceCzzsjbqysj(djMap, jsjdm, year, quarter);
                tzzsbsjList = newInstanceCzzsjbtzzsjList(djMap, tzfList, jsjdm,
                                                         year, quarter);
            }
            else
            {
                criteria.clear();
                criteria.add("jsjdm = '" + jsjdm + "'");  // 计算机代码
                criteria.add("nd ='" + year + "'");  // 年度
                criteria.add("jd ='" + quarter + "'");  // 季度

                czzsjbqysj = (Czzsjbqysj)qysbsjList.get(0);
                czzsjbqysj.setNsrmc(jbsj.getNsrmc());
                //使用ORManager对表“查帐征收季报投资者数据”取投资者申报数据
                //参数：计算机代码、年度、季度(使用和企业数据相同的条件)
                orContext = new ORContext(Czzsjbtzzsj.class.getName(), criteria);
                tzzsbsjList = orManager.query(this.SESSIONID, connection, orContext);
                if(tzzsbsjList.size() == 0)
                {
                    throw new ApplicationException("取不到查帐征收季报投资者数据!");
                }
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        // 放置返回值
        map.put(CzzsjdMapConstant.QYSBSJ, czzsjbqysj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, tzzsbsjList);

        return map;
    }

    /**
     * 返回已填充数值的企业数据
     * @param djMap 登记数据
     * @param jsjdm 计算机代码
     * @param year 年份
     * @param quarter 季度
     * @return 企业申报数据
     */
    private Czzsjbqysj newInstanceCzzsjbqysj(Map djMap, String jsjdm,
                                             String year, String quarter)
    {

        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // 登记基本数据
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        // 税款所属日期
        Map sksjrqMap = Skssrq.otherSkssrq(today);

        Czzsjbqysj czzsjbqysj = new Czzsjbqysj();
        //计算机代码	JSJDM
        czzsjbqysj.setJsjdm(jsjdm);
        //年度	ND
        czzsjbqysj.setNd(year);
        //季度	JD
        czzsjbqysj.setJd(quarter);
        //创建时间	CJSJ
        czzsjbqysj.setCjrq(now);
        //录入日期	LRRQ
        czzsjbqysj.setLrrq(now);
        //税款所属开始日期	SKSSKSRQ
        czzsjbqysj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //税款所属结束日期	SKSSJSRQ
        czzsjbqysj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //纳税人名称	NSRMC
        czzsjbqysj.setNsrmc(jbsj.getNsrmc());
        //利润总额	LRZE
        czzsjbqysj.setLrze(new BigDecimal(0.00));
        //税务登记证号	SWDJZH
        czzsjbqysj.setSwdjzh(jbsj.getSwdjzh());
        //税务机关组织机构代码	SWJGZZJGDM
        czzsjbqysj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //录入人代码	LRR
        czzsjbqysj.setLrr(jsjdm);
        //申报日期	SBRQ
        czzsjbqysj.setSbrq(TinyTools.second2Day(now));

        return czzsjbqysj;
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
    private List newInstanceCzzsjbtzzsjList(Map djMap,
                                            List tzfList,
                                            String jsjdm,
                                            String year,
                                            String quarter)
    {
        List list = new ArrayList();
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // 登记基本数据
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        for(int i = 0, size = tzfList.size(); i < size; i++)
        {
            Tzf tzf = (Tzf)tzfList.get(i);
            Czzsjbtzzsj czzsjbtzzsj = new Czzsjbtzzsj();
            //计算机代码	JSJDM
            czzsjbtzzsj.setJsjdm(jsjdm);
            //年度	ND
            czzsjbtzzsj.setNd(year);
            //季度	JD
            czzsjbtzzsj.setJd(quarter);
            //证件类型代码	ZJLXDM
            czzsjbtzzsj.setZjlxdm(tzf.getZjlxdm());
            //证件号码	ZJHM
            czzsjbtzzsj.setZjhm(tzf.getZjhm());
            //录入日期	LRRQ
            czzsjbtzzsj.setLrrq(now);
            //分配比例	FPBL
            czzsjbtzzsj.setFpbl(tzf.getFpbl());
            //应纳税所得额	YNSSDE
            czzsjbtzzsj.setYnssde(new BigDecimal(0.00));
            //适用税率	SYSL
            czzsjbtzzsj.setSysl(new BigDecimal(0.00));
            //速算扣除数	SSKCS
            czzsjbtzzsj.setSskcs(new BigDecimal(0.00));
            //应纳所得税额	YNSDSE
            czzsjbtzzsj.setYnsdse(new BigDecimal(0.00));
            //减免税额	JMSE
            czzsjbtzzsj.setJmse(new BigDecimal(0.00));
            //期初未缴所得税额	QCWJSDSE
            czzsjbtzzsj.setQcwjsdse(new BigDecimal(0.00));
            //已缴纳所得税额	YJNSDSE
            czzsjbtzzsj.setYjnsdse(new BigDecimal(0.00));
            //实际应缴税额	SJYJSE
            czzsjbtzzsj.setSjyjse(new BigDecimal(0.00));
            //税务机关组织机构代码	SWJGZZJGDM
            czzsjbtzzsj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

            list.add(czzsjbtzzsj);
        }
        return list;
    }

    /**
     * 保存企业申报数据和投资者申报数据
     * @param data Map型数据
     * @throws Exception 当其他情况都会抛出异常信息
     */
    private void doSave(Map data) throws Exception
    {
        Connection connection = null;
        try
        {
            // 取企业申报数据
            Czzsjbqysj qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
            // 取投资者申报数据
            List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);

            // 对存在的本期申报先删
            this.doDelete(data);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            //后插
            orManager.makePersistent(SESSIONID, connection, qysbsj);
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection, tzzsbsjList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
    }

    /**
     * 删除企业申报数据和投资者申报数据
     * @param data Map型数据
     * @throws Exception 当其他情况都会抛出异常信息
     */
    private void doDelete(Map data) throws Exception
    {
        Connection connection = null;
        try
        {
            // 取企业申报数据使用
            Czzsjbqysj qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
            // 取投资者申报数据
            List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // 删除申报数据
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.deleteObject(SESSIONID, connection, tzzsbsjList.get(i));
            }
            orManager.deleteObject(SESSIONID, connection, qysbsj);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
    }
}