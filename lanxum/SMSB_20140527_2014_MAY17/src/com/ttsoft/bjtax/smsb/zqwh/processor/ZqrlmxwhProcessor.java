/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlmxwhForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历明细维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlmxwhProcessor
    implements Processor
{

    /**
     * 用户信息对象
     */
    private UserData userData = null;

    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
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
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 获得前台默认显示控制的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 获得查询结果的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        ZqrlmxwhForm form = (ZqrlmxwhForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Vector zqrlVector = new Vector();
            zqrlVector.add(" SZSMDM = '" + form.getSzsmdm() + "' ");
            zqrlVector.add(" ZQLXDM = '" + form.getZqlxdm() + "' ");
            zqrlVector.add(" DJZCLXDM = '" + form.getDjzclxdm() + "' ");
            zqrlVector.add(" ZQQSRQ = TO_DATE('" + form.getZqqsrq()
                           + "','YYYY-MM-DD')");
            zqrlVector.add(" ND = '" + form.getNd() + "' ");

            //查询出当前需要修改的数据
            List zqrlList = da.query(Zqrl.class, zqrlVector);
            if (zqrlList == null || zqrlList.size() == 0)
            {
                throw new ApplicationException("没有找到指定的记录！");
            }
            if (zqrlList != null && zqrlList.size() > 0)
            {
                Zqrl zqrl = (Zqrl) zqrlList.get(0);
                //格式化数据以供显示
                form.setDjzclxmc(CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",
                    zqrl.getDjzclxdm()));
                form.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                    zqrl.getSzsmdm()));
                form.setZqlxmc(zqrl.getZqlxmc());
                //格式化显示日期
                form.setZqssrqq(getFormatDate(zqrl.getZqssrqq().toString().
                                              substring(0, 10)));
                form.setZqssrqz(getFormatDate(zqrl.getZqssrqz().toString().
                                              substring(0, 10)));
                form.setZqqsrq(getFormatDate(zqrl.getZqqsrq().toString().
                                             substring(0, 10)));
                form.setZqzzrq(getFormatDate(zqrl.getZqzzrq().toString().
                                             substring(0, 10)));
            }

        }
        catch (Exception ex)
        {
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
     * 删除
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return null;
    }

    /**
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        ZqrlmxwhForm form = (ZqrlmxwhForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //先查询出当前纪录
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();
            zqrlVector.add("szsmdm='" + form.getSzsmdm() + "'");
            zqrlVector.add("djzclxdm='" + form.getDjzclxdm() + "'");
            zqrlVector.add("nd='" + form.getNd() + "'");
            zqrlVector.add("zqlxdm='" + form.getZqlxdm() + "'");
            zqrlVector.add("zqqsrq=to_date('" + form.getZqqsrq()
                           + "','yyyymmdd')");
            List tempList = da.query(Zqrl.class, zqrlVector);

            if (tempList == null || tempList.size() == 0)
            {
                throw new ApplicationException("没有找到指定的记录！");
            }
            zqrl = (Zqrl) tempList.get(0);
            //设置更新值，并更新
            zqrl.setZqzzrq(Timestamp.valueOf(setFormatDate(form.getZqzzrq())
                                             + " 00:00:00"));
            //更新数据
            da.update(zqrl);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return form;
    }

    /**
     * 将Timestamp型转换为一个String型日期 （eg.20030611）
     * @param gCalendar 日历
     * @return String型日期 （eg.20030611）
     */
    private String calendarToString (Calendar gCalendar)
    {
        int month = gCalendar.get(Calendar.MONTH) + 1;
        int date = gCalendar.get(Calendar.DATE);
        String strMonth = "" + month;
        String strDate = "" + date;
        if (month < 10)
        {
            strMonth = "0" + strMonth;
        }
        if (date < 10)
        {
            strDate = "0" + strDate;
        }
        String strDay = "" + gCalendar.get(Calendar.YEAR) + strMonth + strDate;
        return strDay;
    }

    /**
     * 时间转换函数。
     * 如：2008-08-08 00:00:00 转化为20080808 00:00:00
     * 或者：2008-08-08 转化为20080808
     * @param inTime 日期或日期时间字符串
     * @return 时间
     * @exception BaseException
     */
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
     * @param inTime 日期或日期时间字符串
     * @return 时间
     * @exception BaseException
     */
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
