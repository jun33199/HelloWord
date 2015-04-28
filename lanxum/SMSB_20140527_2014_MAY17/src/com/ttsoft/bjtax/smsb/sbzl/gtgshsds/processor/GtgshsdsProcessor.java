/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.QYRY;
//登记接口结果类
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//smsbbaselib提供的工具类
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshsdsmx;
//OrMapping值对象包
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshsdsz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.GtgshsdsHelper;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web.GtgshsdsForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj; //smsbbaselib提供的工具类
//申报内部工具类
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil; //申报内部工具类
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户所得税</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsProcessor
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
        //得到Action传递过来ActionForm对象
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        //创建时间

        //设置申报日期
        form.setSbrq(SfDateUtil.getDate());
        //当前日期，根据它去获得相应的申报征期
        Map skssrq = getSkssrq(form.getSbrq());
        //取得税款所属的开始日期和结束日期
        form.setSkssksrq( (String) skssrq.get("skssksrq"));
        form.setSkssjsrq( (String) skssrq.get("skssjsrq"));

        List dataList = GtgshsdsHelper.getShowList();
        form.setDataList(dataList);
        return form;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     *
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        //建立数据库连接
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        Connection conn = null;
        try
        {

            //查询主表数据
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "xm", "scjyrq", "yhdm", "zh", "nd", "swjgzzjgdm",
                "lrr", "fsdm", "zjhm", "zjlxdm", "nsrmc",
                "gjbzhydm", "qxdm"};
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
                Debug.out(InterfaceDj.getQxdm(ud));
            }
            catch (Exception ex1)
            {
                List dataList = GtgshsdsHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE))
                && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                List dataList = GtgshsdsHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("此计算机代码不属于个体工商户！");
            }
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            Vector vZb = new Vector();
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //主表数据结果
            List zbData = da.query(Gtgshsdsz.class, vZb);
            //如果主表数据不存在
            if (zbData.size() <= 0)
            {
                Debug.out("----------From DJ Interface----------------");
                //根据登记接口得到当前计算机代码的纳税人信息，然后赋值给ActionForm
                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setDz(djxx.getJydz());
                form.setQyyb(djxx.getZcdzyb());
                form.setLxdh(djxx.getZcdzlxdh());
                form.setNsrmc(djxx.getNsrmc());
                form.setQxdm(InterfaceDj.getQxdm(ud));
                QYRY qyry = InterfaceDj.getQYRY(form.getJsjdm());

                BeanUtil.copyBeanToBean(zbNames, qyry, form);
                //初始化列表显示控制，因为此用户还没有相应的纳税信息
                List zhList = InterfaceDj.getYHZH(form.getJsjdm());
                if (zhList.size() > 0)
                {
                    YHZH yhzh = (YHZH) zhList.get(0);
                    form.setYhdm(yhzh.getYhdm());
                    form.setZh(yhzh.getZh());
                }
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
                form.setDataList(GtgshsdsHelper.getShowList());
                return form;
            } //end if
            //将主表数据赋给ActionForm
            Gtgshsdsz orZb = (Gtgshsdsz) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //补充主表不存在的信息
            //业主信息
            QYRY qyry = InterfaceDj.getQYRY(form.getJsjdm());
            BeanUtil.copyBeanToBean(zbNames, qyry, form);
            form.setDz(djxx.getJydz());
            form.setQyyb(djxx.getZcdzyb());
            form.setLxdh(djxx.getZcdzlxdh());
            form.setNsrmc(djxx.getNsrmc());
            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //明细查询数据结果
            List mxData = da.query(Gtgshsdsmx.class, vMx);
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                {
                "hc", "xmmc", "je"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            SfHashList showList = new SfHashList(GtgshsdsHelper.getShowList());
            List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //获得此条明细值
                Gtgshsdsmx orMx = (Gtgshsdsmx) mxData.get(i);
                Debug.out(orMx.getJsjdm());
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                int location = showList.findFirst(0, "hc", orMx.getHc());
                map.put("xmmc", showList.get(i, "xmmc"));
                mxMapData.add(map);
            }
            //对明细数据进行排序
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
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
        //得到Action传递过来ActionForm对象
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
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
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsmx());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            try
            {
                //删除主表数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsz());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "xm", "scjyrq", "yhdm", "zh",
            };
            //OrMapping主表值对象
            Gtgshsdsz orZb = new Gtgshsdsz();
            //对主表值对象赋值
            BeanUtil.copyBeanToBean(zbNames, form, orZb);
            java.util.Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                CodeConstant.DATETYPE);
            Timestamp cjrqmx = new Timestamp(cjrq.getTime());
            orZb.setCjrq(cjrqmx);
            orZb.setLrrq(curTime);
            orZb.setNd(nd);
            orZb.setFsdm(CodeConstant.FSDM_SMSB);
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
                map.put("cjrq", cjrqmx);
                map.put("sbrq", form.getSbrq());
                map.put("lrrq", curTime);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("nd", nd);
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                Gtgshsdsmx orMx = new Gtgshsdsmx();
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
        //得到Action传递过来ActionForm对象
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
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
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsmx());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            try
            {
                //删除主表数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsz());
            }
            catch (BaseException ex2)
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
     * 根据申报日期取得当前月第一天和下月第一天
     * @param curSbrq 申报日期
     * @return dateMap 开始日期和结束日期
     */
    private Map getSbrl (String curSbrq)
    {
        Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sbrq);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Timestamp ksrq = new Timestamp(new GregorianCalendar(year, month, 1).
                                       getTime().getTime());
        Timestamp jsrq = new Timestamp(new GregorianCalendar(year, month + 1, 1).
                                       getTime().getTime());
        Map dateMap = new HashMap();
        dateMap.put("ksrq", ksrq);
        dateMap.put("jsrq", jsrq);
        return dateMap;
    }

    /**
     * 根据它去获得相应的申报征期
     * @param curSbrq 申报日期
     * @return  skssMap
     */
    private Map getSkssrq (String curSbrq)
    {
        //当前日期，根据它去获得相应的申报征期
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        String skssksrq = "";
        String skssjsrq = "";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curTime);
        int month = calendar.get(Calendar.MONTH);
        Map skssrq = null;
        if (month == 1)
        {
            skssrq = Skssrq.yearSkssrq(curTime);
        }
        else
        {
            skssrq = Skssrq.monthSkssrq(curTime);
        }
        //取得税款所属的开始日期和结束日期
        skssksrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.get(
            Skssrq.SKSSKSRQ));
        skssjsrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.get(
            Skssrq.SKSSJSRQ));
        Map skssMap = new HashMap();
        skssMap.put("skssksrq", skssksrq);
        skssMap.put("skssjsrq", skssjsrq);
        return skssMap;
    }

}
