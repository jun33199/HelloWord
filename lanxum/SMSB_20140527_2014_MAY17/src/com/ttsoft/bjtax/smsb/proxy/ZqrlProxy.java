/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.proxy;

import java.rmi.RemoteException;

import com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.ejb.ZqrlEJB;
import com.ttsoft.bjtax.smsb.ejb.ZqrlEJBHome;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 综合申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZqrlProxy
{
    private static ZqrlProxy instance = null;

    private static Object initLock = new Object();

    private static ZqrlEJBHome ejbHome = null;

    private ZqrlProxy ()
    {
        try
        {
            ejbHome = (ZqrlEJBHome) SfResourceLocator.getEjbHome(
                JNDINames.ZQRL_HOME_NAME,
                JNDINames.ZQRL_CLASS_NAME);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * 得到proxy实例
     * @return SbzlProxy
     */

    public static ZqrlProxy getInstance ()
    {
        if (instance == null)
        {
            synchronized (initLock)
            {
                if (instance == null)
                {
                    instance = new ZqrlProxy();
                }
            }
        }
        return instance;
    }

    /**
     * process方法
     * @param vo 数据集
     * @return Object
     * @throws Exception
     */

    public Object process (VOPackage vo)
        throws Exception
    {
        try
        {
            ZqrlEJB ejb = ejbHome.create();
            Debug.out("-------RUN IN PROXY-------");
            return ejb.process(vo);
        }
        catch (BaseException ex)
        {
            Debug.out("-----Proxy--BaseException-------" + ex.getMessage());
            throw ExceptionUtil.getBaseException(ex);
        }
        catch (RemoteException ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(new Exception("操作失败请与管理员联系!"));
        }
        catch (Exception ex)
        {
            Debug.out("---------Proxy----------" + ex.getMessage());
            throw ExceptionUtil.getBaseException(ex);
        }

    }
}
