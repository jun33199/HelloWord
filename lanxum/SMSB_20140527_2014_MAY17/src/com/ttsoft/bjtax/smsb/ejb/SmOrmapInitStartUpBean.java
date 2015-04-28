/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.ekernel.db.connection.Manager;

import com.ttsoft.bjtax.sfgl.common.jms.SfMessageReceiver;
import com.ttsoft.bjtax.sfgl.common.util.Debug;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SmOrmapInitStartUpBean
    implements SessionBean
{
    SessionContext sessionContext;

    public void ejbCreate ()
        throws CreateException
    {
        try
        {
            initORMap();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
        }
        try
        {
            SfMessageReceiver receiver = new SfMessageReceiver();
            receiver.init();
        }
        catch (Exception ex)
        {
            Debug.out("上门申报JMS监听建立失败！");
        }

    }

    public void ejbRemove ()
    {
        /**@todo Complete this method*/
    }

    public void ejbActivate ()
    {
        /**@todo Complete this method*/
    }

    public void ejbPassivate ()
    {
        /**@todo Complete this method*/
    }

    public void setSessionContext (SessionContext sessionContext)
    {
        this.sessionContext = sessionContext;
    }

    /**
     * 初始化ormap
     */
    public static void initORMap ()
    {
        try
        {
            Manager.getInstance().initialize();
            Debug.out(
                "\n============SMSB ORMappingStartup Running OK ! ==============\n");
        }
        catch (Exception ex)
        {
            Debug.out(
                "\n============SMSB ORMappingStartup Start Failed! ==============\n");
            ex.printStackTrace();
        }
    }

}
