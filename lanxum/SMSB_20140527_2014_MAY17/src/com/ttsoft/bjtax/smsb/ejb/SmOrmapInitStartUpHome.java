/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public interface SmOrmapInitStartUpHome
    extends javax.ejb.EJBHome
{
    public SmOrmapInitStartUp create ()
        throws CreateException, RemoteException;
}