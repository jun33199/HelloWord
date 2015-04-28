package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.*;
import com.ekernel.db.connection.Manager;

/**
 *
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ʼ��</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
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