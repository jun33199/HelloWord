/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//登记接口结果类
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//smsbbaselib提供的工具类
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrmx;
//OrMapping值对象包
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.GtgshHelper;//申报内部工具类
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.web.GtgshyysrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;//smsbbaselib提供的工具类
//申报内部工具类
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户营业所得</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrProcessor
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
            case CodeConstant.SMSB_CXWSZACTION: //需要更新的查询
                result = doUpdateQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow    用于返回页面初始化的详尽信息
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        //form.setLrrdm("1111");
        //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
        //form.setFsdm(CodeConstant.FSDM_SMSB);
        //设置申报日期
        form.setSbrq(SfDateUtil.getDate());
        //当前日期，根据它去获得相应的申报征期
        Map skssrq = getSkssrq(form.getSbrq());
        //取得税款所属的开始日期和结束日期
        form.setSkssksrq( (String) skssrq.get("skssksrq"));
        form.setSkssjsrq( (String) skssrq.get("skssjsrq"));
        //  Map getsbjd = Skssrq.quarterSkssrq(curTime);
//    String nd = (String) getsbjd.get(Skssrq.SKSSRQ_ND);
//    form.setNd(nd);
        //初始化列表显示控制，因为标题不在数据库提取[这是特殊情况]
        //form.setCjrq(TinyTools.Date2String(curTime, CodeConstant.DATETYPE));
        List dataList = GtgshHelper.getShowList();
        form.setDataList(dataList);
        form.setTempSbrq("");
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
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //建立数据库连接
        Connection conn = null;
        try
        {

            //主表OrMapping值对象的每个参数
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swdjzh", "qxdm", "swjgzzjgdm",
                               "skssksrq",
                               "skssjsrq", "fsdm", "nd", "nsrmc"};
            //如果主表数据不存在
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
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE))
                && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("此计算机代码不属于个体工商户！");
            }
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            form.setQxdm(InterfaceDj.getQxdm(ud));
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            form.setCjrq(TinyTools.Date2String(curTime, CodeConstant.DATETYPE));
            //form.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
            //初始化列表显示控制，因为此用户还没有相应的纳税信息
            List dataList = GtgshHelper.getShowList();
            form.setDataList(dataList);
            form.setTempSbrq("");
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
        return form;
    }

    /**
     * doUpdateQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     *
     */

    public Object doUpdateQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //建立数据库连接
        Connection conn = null;
        try
        {

            //主表OrMapping值对象的每个参数
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swdjzh", "qxdm", "swjgzzjgdm",
                               "skssksrq",
                               "skssjsrq", "fsdm", "nd", "nsrmc"};
            //如果主表数据不存在
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
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE)))
            {
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("此计算机代码不属于个体工商户！");
            }
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //打开数据库
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            //Map dateMap = getSbrl(form.getSbrq());
            //查询主表数据
            Vector vZb = new Vector();
            //form.setQxdm(InterfaceDj.getQxdm(ud));
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq=to_date('" + form.getTempSbrq()
                    + "','yyyy-MM-dd hh24:mi:ss')");

            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //主表数据结果
            List zbData = da.query(Gtgshyysrz.class, vZb);
            if (zbData.size() <= 0)
            {
                //根据登记接口得到当前计算机代码的纳税人信息，然后赋值给ActionForm

                if (djxx == null)
                {
                    Debug.out("此计算机代码不存在！");
                    List dataList = GtgshHelper.getShowList();
                    form.setDataList(dataList);
                    throw new ApplicationException("此计算机代码不存在！");
                }
                form.setQxdm(InterfaceDj.getQxdm(ud));
                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                //form.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                //初始化列表显示控制，因为此用户还没有相应的纳税信息
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                return form;
            } //end if
            //将主表数据赋给ActionForm
            Gtgshyysrz orZb = (Gtgshyysrz) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //form.setNsrmc(djxx.getNsrmc());
            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq=to_date('" + form.getTempSbrq()
                    + "','yyyy-MM-dd hh24:mi:ss')");

            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //明细查询数据结果
            List mxData = da.query(Gtgshyysrmx.class, vMx);
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                               {
                               "szsmdm", "dqkjfpje", "dqwkjfpje", "dqyysrjehj",
                               "jzyysr"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            SfHashList showList = (SfHashList) (new SfHashList(GtgshHelper.
                getShowList())).clone();
            //List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //获得此条明细值
                Gtgshyysrmx orMx = (Gtgshyysrmx) mxData.get(i);
                HashMap map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                Debug.out(orMx.getSzsmdm());
                int location = showList.findFirst(0, "szsmdm", orMx.getSzsmdm());

                //Debug.out("showList.get() = " + showList.get(i, "szsmmc"));
                map.put("szsmmc", showList.get(location, "szsmmc"));
                showList.put(location, map);
                //mxMapData.add(map);
                //Debug.out("mxMapData = " + mxMapData);
            }
            form.setSbrq(this.getFormatDate( (form.getTempSbrq().toString()).
                                            substring(0, 10)));
            form.setDataList(showList.getRecords());
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
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        //建立数据库连接
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        String tempSbrq = "";

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
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());

            Map getsbnd = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swjgzzjgdm", "skssksrq",
                               "skssjsrq"};
            //OrMapping主表值对象
            Gtgshyysrz orZb = new Gtgshyysrz();
            //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
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
            orZb.setLrrdm(ud.getYhid());
            orZb.setSwdjzh(djxx.getSwdjzh());

            try
            {
                //插入主表数据，如果临时的申报日期不为空，则更新，否则插入
                if (form.getTempSbrq() != null && !form.getTempSbrq().equals(""))
                {
                    //设置日期时间型的申报日期
                    orZb.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                    da.update(orZb);
                }
                else
                {
                    //设置日期时间型的申报日期
                    tempSbrq = setFormatDate(form.getSbrq())
                               + (curTime.toString()).substring(10, 19);
                    orZb.setSbrq(Timestamp.valueOf(tempSbrq));
                    Debug.out("---1" + orZb.getSbrq());
                    da.insert(orZb);

                }
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

                map.put("sbrq", form.getSbrq());
                map.put("nd", nd);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                map.put("cjrq", cjrqmx);
                map.put("lrrq", curTime);
                Gtgshyysrmx orMx = new Gtgshyysrmx();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
                mxData.add(orMx);
                try
                {
                    //插入主表数据，如果临时的申报日期不为空，则更新，否则插入
                    if (form.getTempSbrq() != null
                        && !form.getTempSbrq().equals(""))
                    {
                        //设置日期时间型的申报日期
                        orMx.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                        da.update(orMx);
                    }
                    else
                    {
                        //设置日期时间型的申报日期
                        tempSbrq = setFormatDate(form.getSbrq())
                                   + (curTime.toString()).substring(10, 19);
                        orMx.setSbrq(Timestamp.valueOf(tempSbrq));
                        Debug.out("---2" + orZb.getSbrq());
                        da.insert(orMx);
                    }
                }
                catch (BaseException ex4)
                {
                    throw new ApplicationException("数据库操作失败，请您找管理员联系！");
                }

            }
            form.setTempSbrq("");
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
        return null;
    }

    /**
     * 根据申报日期取得当前月第一天和下月第一天
     * @param curSbrq 申报日期
     * @return dateMap
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
     * 取得税款所属的开始日期和结束日期
     * @param curSbrq 申报日期
     * @return skssMap
     */
    private Map getSkssrq (String curSbrq)
    {
        //当前日期，根据它去获得相应的申报征期
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        Map skssrq = Skssrq.monthSkssrq(curTime);
        //取得税款所属的开始日期和结束日期
        String skssksrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.
            get(
            Skssrq.SKSSKSRQ));
        String skssjsrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.
            get(
            Skssrq.SKSSJSRQ));
        Map skssMap = new HashMap();
        skssMap.put("skssksrq", skssksrq);
        skssMap.put("skssjsrq", skssjsrq);
        return skssMap;
    }

    /**
     * 时间转换函数。
     * 如：2008-08-08 00:00:00 转化为20080808 00:00:00
     * 或者：2008-08-08 转化为20080808
     *
     * @param inTime 日期或日期时间字符串
     * @return String
     * @throws BaseException
     **/
    public static String getFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        String tempStr = inTime.substring(0, 10);
        String defStr = "";

        try
        {
            if (inTime.length() > 15)
            {
                defStr = inTime.substring(10);
            }
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyyMMdd");
            result = df.format(java.sql.Date.valueOf(tempStr)) + defStr;
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of getFormatDate

    /**
     * 时间转换函数。getFormatDate函数的逆运算
     * 如：20080808 00:00:00 转化为2008-08-08 00:00:00
     * 或者：20080808 转化为2008-08-08
     *
     * @param inTime 日期或日期时间字符串
     * @return String
     * @throws BaseException
     **/
    public static String setFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        try
        {
            String tempStr = inTime.substring(0, 4);
            String defStr = inTime.substring(4, 6);
            result = tempStr + "-" + defStr + "-" + inTime.substring(6);
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of setFormatDate

}
