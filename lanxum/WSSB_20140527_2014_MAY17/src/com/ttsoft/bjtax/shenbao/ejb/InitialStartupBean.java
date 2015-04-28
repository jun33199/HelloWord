package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.*;
import com.ekernel.db.connection.Manager;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 初始化</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class InitialStartupBean implements SessionBean
{
    SessionContext sessionContext;

    public void ejbCreate() throws CreateException
    {
        try
        {
            init_aux();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
        }
    }

    public void ejbRemove()
    {
    }

    public void ejbActivate()
    {
    }

    public void ejbPassivate()
    {
    }

    public void setSessionContext(SessionContext sessionContext)
    {
        this.sessionContext = sessionContext;
    }

    public static void init_aux()
    {
        try
        {
            Manager.getInstance().initialize();
            System.out.println(
                "\n============ ORMappingStartup Running OK ! ==============\n");
        }
        catch(Exception e)
        {
            System.out.println(
                "=======================   ! ! !   ========================");
            System.out.println(
                "Some errors occurred in the OR servlet. \n\n");
            e.printStackTrace(System.out);
        }
    }

}