/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.wqyys.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.wqyys.web.WqyyshdzsForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税局税务管理模块－－上门申报</p>
 * <p>Description: 北京市外国企业营业税纳税申报表－－核定征收</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class WqyyshdzsProcessor
    implements Processor
{
    /**
     * 实现Processor接口
     * @param vo 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     * 		1 当传过来的操作类型不对时抛出
     * 		2 当调用的业务方法抛出业务异常时向上传递抛出
     * 	其他异常抛出由EJB的process方法处理。
     */

    public Object process (VOPackage vo)
        throws BaseException
    {
        //回传对象
        Object result = null;
        //判断VO是否为空
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //根据Action的值调用不同的process方法
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //前台默认显示
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //查询
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "-----------Method process() not yet implemented.---------");
        }
        return result;
    }

    /**
     * doShow初始化对象页面信息要素
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        EArray jsArray = new EArray();
        String tempJsStr = "";
        //设置申报日期
        form.setSbrq(SfDateUtil.getDate());
        Date sbrq = SfDateUtil.getDate(form.getSbrq());
        Date nextSbrq = TinyTools.addMonth( -3, sbrq);
        //当前日期，根据它去获得相应的申报征期
        Map skssrq = Skssrq.quarterSkssrq(nextSbrq);
        //取得税款所属的开始日期和结束日期
        form.setSkssksrq(DateTimeUtil.timestampToString(
            (Timestamp) skssrq.get(Skssrq.SKSSKSRQ),
            DateTimeUtil.JAVA_DATEFORMAT));
        form.setSkssjsrq(DateTimeUtil.timestampToString(
            (Timestamp) skssrq.get(Skssrq.SKSSJSRQ),
            DateTimeUtil.JAVA_DATEFORMAT));
        tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL", new ArrayList());
        form.setScriptStr(tempJsStr);
        return form;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djxx = null;
        try
        {

            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            form.setNsrzt(djxx.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/
            form.setQxdm(InterfaceDj.getQxdm(ud));
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        //建立数据库连接
        Connection conn = null;
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            EArray jsArray = new EArray();
            String tempJsStr = "";
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            //查询主表数据
            Vector vZb = new Vector();
            //主表数据结果
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                {
                "jsjdm", "sbrq", "nsrmc", "skssksrq", "skssjsrq",
                "szdm", "szmc", "swjgzzjgdm", "lrr", "fsdm",
                "zsffdm", "zsffmc",
                "qxdm"};
            ServiceProxy proxy = new ServiceProxy();
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //外企营业税征收方式
            Wqzsf wqzsf = proxy.getWqzsfsInfo(form.getJsjdm(),
                                              SfDateUtil.getDate(form.
                getSkssjsrq()));
            if (wqzsf == null)
            {
                throw new ApplicationException("操作人员注意：该计算机代码不能进行核定征收！");
            }
            else
            {
                form.setZsfs(wqzsf.getWqzsfsdm());
            }
            //判断是否属于核定征收
            if (! (wqzsf.getWqzsfsdm().equals(CodeConstant.WQYYSZSFS_HDZS)))
            {
                form.setZsfs("no");
                throw new ApplicationException("操作人员注意：该计算机代码不能进行核定征收！");
            }
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add("jsjdm='" + form.getJsjdm() + "' ");
            List zbData = da.query(Wqyys.class, vZb);
            //如果主表数据不存在
            if (zbData.size() <= 0)
            {
                //根据登记接口得到当前计算机代码的纳税人信息，然后赋值给ActionForm

                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setSzdm("02");
                form.setSzmc("营业税");
                form.setZsffdm("03");
                form.setZsffmc("核定征收");
                tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
                tempJsStr +=
                    jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL",
                                          new ArrayList());
                form.setScriptStr(tempJsStr);
                return form;
            } //end if
            //将主表数据赋给ActionForm
            Wqyys orZb = (Wqyys) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //补充主表不存在的信息
            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //明细查询数据结果
            List mxData = da.query(Wqyys.class, vMx);
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                {
                "szsmdm", "szsmmc", "sre", "htcje", "yjl",
                "hdsre", "jfzce", "hssre",
                "jsje", "sl", "ynse", "yinse", "bqybse"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            ArrayList mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //获得此条明细值
                Wqyys orMx = (Wqyys) mxData.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                String szsmdm = (String) map.get("szsmdm");
                String szmc = szsmdm.substring(0, 2);
                Vector vMxszsmmc = new Vector();
                Vector vMxszmc = new Vector();
                vMxszsmmc.add("szsmdm ='" + szsmdm + "'");
                vMxszmc.add("szsmdm ='" + szmc + "'");
                List szsmmcData = da.query(Szsm.class, vMxszsmmc);
                Szsm szsm = (Szsm) szsmmcData.get(0);
                List szmcData = da.query(Szsm.class, vMxszmc);
                Szsm szmcs = (Szsm) szmcData.get(0);
                map.put("szsmmc", szsm.getSzsmmc());
                map.put("szmc", szmcs.getSzsmmc());
                map.put("szsmdm_old", map.get("szsmdm"));
                mxMapData.add(map);
            }
            //对明细数据进行排序
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
            form.setSzdm("02");
            form.setSzmc("营业税");
            form.setZsffdm("03");
            form.setZsffmc("核定征收");
            tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL",
                                      form.getDataList());
            form.setScriptStr(tempJsStr);
            return form;
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * doSave   用于存储页面提交的详尽处理信息
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */

    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        SWDJJBSJ djxx = null;
        UserData ud = (UserData) vo.getUserData();
        try
        {

            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            Date lastSbrq = TinyTools.addMonth( -3,
                                               SfDateUtil.getDate(form.getSbrq()));
            Map getsbnd = Skssrq.quarterSkssrq(lastSbrq);
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //删除原先的记录
            try
            {
                //删除主表数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq < to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Wqyys());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "szsmdm"
            };
            //得到明细数据集
            List mxMapData = form.getDataList();
            //存贮明细数据值对象
            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                //将主表的数据添加进来
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Wqyys orMx = new Wqyys();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
                java.util.Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                    CodeConstant.DATETYPE);
                Timestamp cjrqmx = new Timestamp(cjrq.getTime());
                orMx.setCjrq(cjrqmx);
                orMx.setLrrq(curTime);
                orMx.setNd(nd);
                orMx.setFsdm(CodeConstant.FSDM_SMSB);
                orMx.setQxdm(InterfaceDj.getQxdm(ud));
                orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                orMx.setLrr(ud.getYhid());
                orMx.setZsffdm(CodeConstant.WQYYSZSFS_HDZS);
                mxData.add(orMx);
            }
            try
            {
                //插入明细表数据
                da.insert(mxData);
            }
            catch (BaseException ex4)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            return null;
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * doDelete  用于删除页面提交的详尽处理信息
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            //删除原先的记录
            try
            {
                //删除明细数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq < to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Wqyys());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            return null;
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * 根据申报日期取得当本季第一天和本季最后一天
     * @param curSbrq 申报日期
     * @return Map  申报日历（内含开始日期和结束日期）
     */
    private Map getSbrl (String curSbrq)
    {
        Date sbrq = SfDateUtil.getDate(curSbrq);
        Map skssrq = new HashMap();
        skssrq = Skssrq.quarterSkssrq(sbrq);
        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
        Map dateMap = new HashMap();
        dateMap.put("ksrq", skssksrq);
        dateMap.put("jsrq", skssjsrq);
        return dateMap;
    }

    /**
     * 得到税款所属日期
     * @param curSbrq 申报日期
     * @return Map 税款所属日期（内含开始日期和结束日期）
     */
    private Map getSkssrq (String curSbrq)
    {
        //当前日期，根据它去获得相应的申报征期
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curTime);
        int month = calendar.get(Calendar.MONTH);
        Map skssrq = null;
        skssrq = Skssrq.quarterSkssrq(curTime);

        //取得税款所属的开始日期和结束日期
        Timestamp skssksrq = (Timestamp) skssrq.get(
            Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) skssrq.get(
            Skssrq.SKSSJSRQ);
        Map skssMap = new HashMap();
        skssMap.put("ksrq", skssksrq);
        skssMap.put("jsrq", skssjsrq);
        return skssMap;
    }

}
