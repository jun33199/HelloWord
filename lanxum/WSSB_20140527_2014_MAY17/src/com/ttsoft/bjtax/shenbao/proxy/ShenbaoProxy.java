package com.ttsoft.bjtax.shenbao.proxy;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.ejb.ShenbaoHome;
import com.ttsoft.framework.util.ResourceLocator;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.ejb.Shenbao;
import java.rmi.RemoteException;
import com.ttsoft.framework.exception.BaseException;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 申报子系统与后台EJB通信的代理类，所有与后台的通信必须通过该类</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class ShenbaoProxy
{
    /**
     * 保存ShenbaoHome缓存
     */
    private static ShenbaoHome ejbHome;

    private static final String SHENBAO_HOME_NAME = "shenbao";
    private static final String SHENBAO_CLASS_NAME =
        "com.ttsoft.bjtax.shenbao.ejb.ShenbaoHome";

    /**
     *单例模式下的proxy实例
     */
    protected static ShenbaoProxy instance = new ShenbaoProxy();

    protected ShenbaoProxy()
    {
        try
        {
            //得到ShenbaoEjb home接口
            ejbHome = (ShenbaoHome)ResourceLocator.getEjbHome(
                SHENBAO_HOME_NAME,
                SHENBAO_CLASS_NAME);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static ShenbaoProxy getInstance()
    {
        if (instance == null && ejbHome == null)
        {
            instance = new ShenbaoProxy();
        }
        return instance;
    }

    public Object process(VOPackage vo) throws BaseException
    {
        try
        {
            //得到Ejb
            Shenbao ejb = ejbHome.create();
            //返回处理的结果
            return ejb.process(vo);
        }
        catch(RemoteException ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(new Exception("操作失败请与技术支持联系!"));
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}
