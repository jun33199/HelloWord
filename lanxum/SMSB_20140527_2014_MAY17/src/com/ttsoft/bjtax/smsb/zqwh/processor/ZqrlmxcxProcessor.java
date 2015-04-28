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
import java.util.HashMap;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlmxcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历明细查询</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlmxcxProcessor
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
        List dataList = new ArrayList();
        //主表OrMapping值对象的每个参数
        String columns[] =
                           {
                           "szsmdm", "szsmmc", "zqlxdm", "zqlxmc", "zqssrqq",
                           "zqssrqz",
                           "zqqsrq", "zqzzrq", "cjrq", "lrrq", "swjgzzjgdm",
                           "nd", "djzclxdm", "djzclxmc", "zqksrq", "zqts"};

        //得到Action传递过来ActionForm对象
        ZqrlmxcxForm form = (ZqrlmxcxForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();

            SfDBUtils sfDB = new SfDBUtils(conn);
            //得到sql语句
            String sql = getQuerySql(form, "query");
            List tempList = sfDB.getDataList(sql
                + " ORDER BY A.SZSMDM,A.ZQLXDM,A.DJZCLXDM");
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的记录！");
            }

            //将主表数据赋给ActionForm
            for (int i = 0; i < tempList.size(); i++)
            {
                //格式化每条纪录
                //将值对象的值赋给Map
                HashMap map = new HashMap();
                map = (HashMap) tempList.get(i);
                map.put("djzclxmc",
                        CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",
                    map.get("djzclxdm").toString()));
                map.put("szsmmc",
                        CodeUtils.getCodeBeanLabel("DM_SZSM",
                    map.get("szsmdm").toString())); //税种名称
                map.put("zqssrqq",
                        (map.get("zqssrqq").toString()).substring(0, 10));
                map.put("zqssrqz",
                        (map.get("zqssrqz").toString()).substring(0, 10));
                map.put("zqqsrq",
                        (map.get("zqqsrq").toString()).substring(0, 10));
                map.put("zqzzrq",
                        (map.get("zqzzrq").toString()).substring(0, 10));

                dataList.add(map);
            }
            //把值放回form对象
            form.setTempMonth(form.getHeadMonth()); //月
            form.setTempNd(form.getHeadNd()); //年
            form.setTempDjzclx(form.getHeadDjzclx()); //登记注册类型
            form.setTempSz(form.getHeadSz()); //税种
            form.setTempZqlx(form.getHeadZqlx()); //征期类型
            form.setDataList(dataList);
        }
        catch (Exception ex)
        {
            //抛出异常
            form.setDataList(dataList);
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
        ZqrlmxcxForm form = (ZqrlmxcxForm) vo.getData();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            Zqrl zqrl = new Zqrl();
            String tempStr = "";
            Vector zqrlVector = new Vector();
            zqrlVector.add(" SZSMDM = '" + form.getTempSz() + "' ");
            zqrlVector.add(" ZQLXDM = '" + form.getTempZqlx() + "' ");
            zqrlVector.add(" DJZCLXDM = '" + form.getTempDjzclx() + "' ");
            zqrlVector.add(" ZQQSRQ = TO_DATE('" + form.getTempZqqsrq()
                           + "','YYYY-MM-DD')");
            zqrlVector.add(" ND = '" + form.getTempNd() + "' ");
            zqrl.setSzsmdm(form.getTempSz());
            zqrl.setZqlxdm(form.getTempZqlx());
            zqrl.setNd(form.getTempNd());
            zqrl.setDjzclxdm(form.getTempDjzclx());
            zqrl.setZqqsrq(Timestamp.valueOf(form.getTempZqqsrq() + " 00:00:00"));
            //删除
            da.delete(zqrl);

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
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 得到查询所用SQL语句
     * @param sf 当前页面对应的Form对象
     * @param flag 查询（query）或保存（save）标记
     * @return sql语句条件
     */
    private String getQuerySql (ZqrlmxcxForm sf, String flag)
    {
        String sql = null;
        String whereCon = " ";
        //得到查询条件
        //查询，则使用用户输入值，保存则使用临时值
        if (flag.equals("query"))
        {
            if (sf.getHeadDjzclx() != null && !sf.getHeadDjzclx().equals("*")
                && !sf.getHeadDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getHeadDjzclx() + "'";
            }
            if (sf.getHeadSz() != null && !sf.getHeadSz().equals("*")
                && !sf.getHeadSz().equals(""))
            {
                whereCon += " AND A.SZSMDM LIKE '" + sf.getHeadSz() + "%'";
            }
            if (sf.getHeadZqlx() != null && ! (sf.getHeadZqlx()).equals("*")
                && ! (sf.getHeadZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getHeadZqlx() + "'";
            }
            if (sf.getHeadMonth() != null && ! (sf.getHeadMonth()).equals("*")
                && ! (sf.getHeadMonth()).equals(""))
            {
                if (sf.getHeadMonth().equals("12")){  //十二月的处理
                    whereCon += " AND A.ZQQSRQ>=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-01','YYYY-MM-DD')" +
                    " AND A.ZQQSRQ<=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-31','YYYY-MM-DD')";
                }
                else {
                    whereCon += " AND A.ZQQSRQ>=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-01','YYYY-MM-DD')" +
                    " AND A.ZQQSRQ<TO_DATE('" + sf.getHeadNd() + "-" +
                    (StringUtil.getInt(sf.getHeadMonth(),1)+1) +
                    "-01','YYYY-MM-DD')";
                }
            }
        }

        sql = "SELECT * FROM SBDB.SB_JL_ZQRL A "
              + " WHERE 1=1 " + whereCon + " AND A.ND = '" + sf.getHeadNd()
              + "' ";
        System.out.println("<><>"+sql);
        return sql;
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
}
