/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor;

//申报内部工具类
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
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
//OrMapping值对象包
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.web.GtgshyysrCxForm;
//申报内部工具类
import com.ttsoft.bjtax.smsb.util.InterfaceDj;//smsbbaselib提供的工具类
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

public class GtgshyysrCxProcessor
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
     * doShow    用于返回页面初始化的详尽信息
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        //设置申报日期
        try
        {
            form.setHeadSbrq( (SfDateUtil.getDate()).substring(0, 6));
            Debug.out(form.getHeadSbrq());
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
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
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        List dataList = new ArrayList();

        //建立数据库连接
        Connection conn = null;
        try
        {

            //主表OrMapping值对象的每个参数
            String names[] =
                             {
                             "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm", "lrrdm",
                             "lrrq", "fsdm", "skssksrq", "skssjsrq", "nd",
                             "cjrq", "qxdm", "nsrmc"};
            //如果主表数据不存在
            UserData ud = (UserData) vo.getUserData();
            SWDJJBSJ djxx = null;
            try
            {
                djxx = InterfaceDj.getJBSJ(form.getHeadJsjdm(), ud);
            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            //判断个体工商户
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE)
                   && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH))))
            {
                throw new ApplicationException("此计算机代码不属于个体工商户！");
            }

            //打开数据库
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getHeadSbrq() + "15");
            //查询主表数据
            Vector vZb = new Vector();
            //form.setQxdm(InterfaceDj.getQxdm(ud));
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" jsjdm='" + form.getHeadJsjdm() + "' order by sbrq desc ");

            //主表数据结果
            List tempList = da.query(Gtgshyysrz.class, vZb);
            //Debug.out("-----templist.size="+tempList.size());
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的记录！");
            }
            //将主表数据赋给ActionForm
            for (int i = 0; i < tempList.size(); i++)
            {
                //格式化每条纪录
                Gtgshyysrz zb = (Gtgshyysrz) tempList.get(i);
                //sbjkzb.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM",sbjkzb.getSzdm()));//税种名称
                //将值对象的值赋给Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, zb, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("数据转换出错！");
                }
                //申报日期使用日期时间型，增加纳税人名称
                map.put("sbrq", (zb.getSbrq().toString()).substring(0, 19));
                map.put("nsrmc", djxx.getNsrmc());
                dataList.add(map);
            }
            //把值放回form对象
            form.setDataList(dataList);
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

    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
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
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        Gtgshyysrz orObjz = new Gtgshyysrz();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);

            //1、删除明细表数据
            String strSql = "delete from sbdb.sb_jl_gtgshyysrmx " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + form.getTempJsjdm() + "'" +
                            " and sbrq=to_date('" + form.getTempSbrq()
                            + "','yyyy-mm-dd hh24:mi:ss')";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除明细表数据出错！");
            }
            Debug.out(form.getHeadSbrq() + "<>" + form.getTempJsjdm());
            //2、查出主表数据
            /**orObjz.setQxdm(InterfaceDj.getQxdm(ud));//区县代码
                   orObjz.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                   orObjz.setJsjdm(form.getTempJsjdm());
                   try {
              da.delete(orObjz);
                   }
                   catch (BaseException ex1) {
              ex1.printStackTrace();
              throw new ApplicationException("删除主表数据出错！");
                   }*/
            strSql = "delete from sbdb.sb_jl_gtgshyysrz " +
                     " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                     " and jsjdm='" + form.getTempJsjdm() + "'" +
                     " and sbrq=to_date('" + form.getTempSbrq()
                     + "','yyyy-mm-dd hh24:mi:ss')";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除主表数据出错！");
            }

            //清空
            form.setTempJsjdm("");
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

}
