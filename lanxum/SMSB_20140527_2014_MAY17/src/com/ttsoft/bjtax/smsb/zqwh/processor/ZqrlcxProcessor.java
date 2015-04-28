/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlcxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历查询</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlcxProcessor
    implements Processor
{

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
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 获得前台默认显示控制的ActionForm
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        return null;
    }

    /**
     * 获得查询结果的ActionForm
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        ZqrlcxForm zf = (ZqrlcxForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);

            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            if (zf.getHeadZqlx() != null && !zf.getHeadZqlx().equals("*"))
            {
                vMx.add(" ZQLXDM = '" + zf.getHeadZqlx() + "' ");
            }
            String whnf = zf.getWhnf();
            if (zf.getSelectTypeRadio().equals("1"))
            {
                vMx.add(" ND = '" + whnf + "' ");
            }
            vMx.add(" 1=1 ORDER BY ZQLXDM ");
            //明细查询数据结果
            List mxDataList = da.query(Czqrl.class, vMx);
            if (mxDataList == null || mxDataList.size() == 0)
            {
                throw new ApplicationException("没有找到指定的记录！");
            }
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                {
                "zqlxdm", "zqlxmc", "zqssrqq", "zqssrqz",
                "zqqsrq", "zqzzrq"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            List mxMapDataList = new ArrayList();
            for (int i = 0; i < mxDataList.size(); i++)
            {
                //获得此条明细值
                Czqrl orMx = (Czqrl) mxDataList.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                mxMapDataList.add(map);
            }
            zf.setDataList(mxMapDataList);
            return zf;
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

}