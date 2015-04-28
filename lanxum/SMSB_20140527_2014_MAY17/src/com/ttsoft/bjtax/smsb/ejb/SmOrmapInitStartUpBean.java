/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.ekernel.db.connection.Manager;

import com.ttsoft.bjtax.sfgl.common.jms.SfMessageReceiver;
import com.ttsoft.bjtax.sfgl.common.util.Debug;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
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
            Debug.out("�����걨JMS��������ʧ�ܣ�");
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
     * ��ʼ��ormap
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
