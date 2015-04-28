/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.grsds.web.CzzsjdForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 查帐征收个人独资企业和合伙企业投资者个人所得税季度申报表</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class CzzsjdProcessor
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
            case CodeConstant.SMSB_SAVEACTION: //存储
                doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION: //删除
                doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow初始化对象页面信息要素
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到CzzsjdAction传递过来CzzsjdForm对象
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        //当前日期，根据它去获得相应的申报征期
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //当前日期，根据它去获得季度的申报征期
        Map getsbjd = Skssrq.otherSkssrq(curTime);
        Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
        //税款税款所属结束日期
        Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
        //把得到的税款所属开始日期由Timestamp类型转换String类型并放入到CzzsjdForm里
        form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
        //把得到的税款所属结束日期由Timestamp类型转换String类型并放入到CzzsjdForm里
        form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
        //其他信息存入CzzsjdForm里
        form.setSbrq(SfDateUtil.getDate());
        String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSkssksrq()));
        form.setJd(jd);
        return form;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到CzzsjdAction传递过来CzzsjdForm对象
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        Map lastYear = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
        Timestamp lastskssjsrq = (Timestamp) lastYear.get(Skssrq.SKSSJSRQ);
        java.util.Date time = SfDateUtil.getDate(lastskssjsrq.toString());
        //投资方数组
        List tzfinfo = new ArrayList();
        //当前日期
        Date date = SfDateUtil.getDate(form.getSbrq());
        //数据库连接
        Connection conn = null;
        try
        {
            //数据库连接

            ServiceProxy proxy = new ServiceProxy();
            //主表OrMapping值对象的每个参数
            String zbNames[] = {
                               "fsdm", "jd", "jsjdm", "lrr", "lrze", "nd",
                               "nsrmc", "sbrq",
                               "qxdm",
                               "skssjsrq", "skssksrq", "swdjzh", "swjgzzjgdm"};
            //登记基本数据
            SWDJJBSJ djxx = null;
            UserData ud = (UserData) vo.getUserData();
            try {
                //登记接口
                djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            //纳税人名称
            form.setNsrmc(djxx.getNsrmc());
            //复制到form
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //得到季度
            int query = TinyTools.getQuarter(SfDateUtil.getDate(form.getSbrq()));
            //按照季度划分
            //1季度
            if (query == 1)
            {

                //查帐征收征收方式接口
                Grzsfs grzsfs = (Grzsfs) proxy.getGrzsfsInfo(form.getJsjdm(),
                    time);
                if (grzsfs == null)
                {
                    throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");
                }
                else
                {
                    form.setZsfs(grzsfs.getZsfsdm());
                }
                if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
                {
                    form.setZsfs("no");
                    throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");
                }
            }
            //2、3、4季度
            else
            {
                //查帐征收征收方式接口
                Grzsfs grzsfs = (Grzsfs) proxy.getGrzsfsInfo(form.getJsjdm(),
                    SfDateUtil.getDate(form.getSkssjsrq()));
                if (grzsfs == null)
                {
                    throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");
                }
                else
                {
                    form.setZsfs(grzsfs.getZsfsdm());
                }
                if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
                {
                    form.setZsfs("no");
                    throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");

                }
            }
            if (query == 1)
            {
                //投资人接口
                tzfinfo = proxy.getTzfInfo(form.getJsjdm(), time);
            }
            else
            {
                //投资人接口
                tzfinfo = proxy.getTzfInfo(form.getJsjdm(),
                                           SfDateUtil.getDate(form.getSkssjsrq()));
            }
            if (tzfinfo == null || tzfinfo.size() == 0)
            {
                throw new ApplicationException("没有该企业投资人信息！");
            }
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            //设置查询条件
            Map getsbnd = Skssrq.otherSkssrq(date);
            //季度
            String jd = Skssrq.preQuarter(date);
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            //查询主表数据
            Vector vZb = new Vector();
            
            //按照区县代码、计算机代码、年度、季度设置查询条件
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add("jd='" + jd + "'");
            vZb.add("nd='" + nd + "'");
            vZb.add("jsjdm='" + form.getJsjdm() + "'");
            
            //查找纪录
            List zbData = da.query(Czzsjbqysj.class, vZb);
            
            //如果主表数据不存在
            if (zbData.size() <= 0)
            {
                //根据登记接口得到当前计算机代码的纳税人信息，然后赋值给CzzsjdForm
                //根据计算机代码在税费管理数据库中查找投资人基本信息
                if (djxx == null)
                {
                    form.setJsjdm(null);
                    form.setNsrmc(null);
                    form.setLrze(null);
                    throw new ApplicationException("此计算机代码不存在！");
                }
                else
                {
                    form.setNsrmc(djxx.getNsrmc());
                }
                form.setNsrmc(djxx.getNsrmc());
                //主表一些其他信息补充
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setSbrq(form.getSbrq());
                //投资人信息
                if (tzfinfo == null || tzfinfo.size() == 0)
                {
                    throw new ApplicationException("没有该企业投资人信息！");
                }
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                String tzfNames[] =
                                    {
                                    "tzfmc", "zjlxdm", "zjhm", "fpbl"};
                List mxMapDatas = new ArrayList();
                for (int i = 0; i < tzfinfo.size(); i++)
                {
                    //通过税目对照预算科目的接口中的查找投资人信息明细值
                    Tzf orMxs = (Tzf) tzfinfo.get(i);
                    Map maps = new HashMap();
                    //将值对象的值赋给Map
                    BeanUtil.copyBeanToMap(tzfNames, orMxs, maps);
                    Object tzfRen = maps.get("tzfmc");
                    maps.remove("tzfmc");
                    maps.put("tzzxm", tzfRen);
                    //"ynssde","sysl","sskcs","ynsdse","jmse","qcwjsdse","yjnsdse","sjyse"初始化
                    maps.put("ynssde", new Integer(0));
                    maps.put("sysl", new Float(0));
                    maps.put("sskcs", new Integer(0));
                    maps.put("ynsdse", new Integer(0));
                    maps.put("jmse", new Integer(0));
                    maps.put("qcwjsdse", new Integer(0));
                    maps.put("yjnsdse", new Integer(0));
                    maps.put("sjyjse", new Integer(0));
                    mxMapDatas.add(maps);
                }
                //将查到的结果压入到DataList中
                form.setDataList(mxMapDatas);
                //数值返回
                return form;
            }
            //如果主表数据存在将主表数据赋给CzzsjdForm
            Czzsjbqysj orZb = (Czzsjbqysj) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add("jd='" + jd + "'");
            vMx.add("nd='" + nd + "'");
            vMx.add("jsjdm='" + form.getJsjdm() + "'");

            //明细查询数据结果
            List mxData = da.query(Czzsjbtzzsj.class, vMx);
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                               {
                               "zjlxdm", "zjhm", "tzzxm", "fpbl", "ynssde",
                               "sysl", "sskcs",
                               "ynsdse", "jmse", "qcwjsdse", "yjnsdse",
                               "sjyjse"};
            List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //获得此条明细值
                Czzsjbtzzsj orMx = (Czzsjbtzzsj) mxData.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                mxMapData.add(map);
            }
            //将查到的结果压入到DataList中
            form.setDataList(mxMapData);
            //数值返回
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
     * @param   vo 业务参数
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到CzzsjdAction传递过来CzzsjdForm对象
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        //登记接口
        SWDJJBSJ djxx = null;
        try
        {
            //登记接口信息
            djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        //数据库连接
        Connection conn = null;
        try
        {
            //数据库连接
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            //设置删除条件
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            //季度
            String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
            String SQL = "  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                         + "and jsjdm='" +
                         form.getJsjdm() + "' and jd='" + jd + "' and nd='"
                         + nd + "'" +
                         "order by  nd ASC";
            //删除原先的记录
            try
            {
                //删除明细数据
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbtzzsj());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(" 数据库操作失败，请您找管理员联系！");
            }
            try
            {
                //删除主表数据
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbqysj());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException(" 数据库操作失败，请您找管理员联系！");
            }
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                               {
                               "jsjdm", "lrze",
                               "sbrq", "skssjsrq", "skssksrq"};
            //OrMapping主表值对象
            Czzsjbqysj orZb = new Czzsjbqysj();
            //对主表值对象赋值
            HashMap new_data = new HashMap();
            BeanUtil.copyBeanToMap(zbNames, form, new_data);
            BeanUtil.populate(orZb, new_data);
            Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                                               CodeConstant.DATETYPE);
            Timestamp cjrqmx = new Timestamp(cjrq.getTime());
            orZb.setCjrq(cjrqmx);
            orZb.setLrrq(curTime);
            orZb.setJd(jd);
            orZb.setNd(nd);
            orZb.setFsdm(CodeConstant.FSDM_SMSB);
            orZb.setSwdjzh(djxx.getSwdjzh());
            orZb.setQxdm(InterfaceDj.getQxdm(ud));
            orZb.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
            orZb.setLrr(ud.getYhid());
            try
            {
                //插入主表数据
                da.insert(orZb);
            }
            catch (BaseException ex3)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            //得到明细数据集
            List mxMapData = form.getDataList();
            //存贮明细数据值对象
            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                //添加一些外键信息
                map.put("jsjdm", form.getJsjdm());
                map.put("nd", nd);
                map.put("jd", jd);
                map.put("cjrq", cjrqmx);
                map.put("lrrq", curTime);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Czzsjbtzzsj orMx = new Czzsjbtzzsj();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
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
     * @param    vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //得到CzzsjdAction传递过来CzzsjdForm对象
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        //UserData数据
        UserData ud = (UserData) vo.getUserData();
        //数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            //设置删除条件
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            //季度
            String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
            //年度
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            //当前时间
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            form.setSbrq(SfDateUtil.getDate());
            form.setNsrmc(null);
            form.setLrze(null);
            //删除原先的记录
            try
            {
                //删除明细数据
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbtzzsj());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            try
            {
                //删除主表数据
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbqysj());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            form.setJsjdm(null);
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
     * 季度求得所属年度
     * @param jd 季度
     * @param getsbnd 其它类型的税款所属日期
     * @param sbrq 申报日期
     * @return 所属年度
     */
    private String getNd (String jd, Map getsbnd, String sbrq)
    {
        String nd;
        int year;
        if (jd.equals("4"))
        {
            nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
        }
        else
        {
            nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
        }
        return nd;
    }
}
