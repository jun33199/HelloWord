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
 * <p>Description:�ۺ��걨ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public interface ZqrlEJBHome
    extends javax.ejb.EJBHome
{
    public ZqrlEJB create ()
        throws CreateException, RemoteException;
}