/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import java.rmi.RemoteException;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:�ۺ��걨ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public interface ZhsbEJB
    extends javax.ejb.EJBObject
{
    public Object process (VOPackage vo)
        throws BaseException, RemoteException;

    public void refreshCache ()
        throws RemoteException;
}