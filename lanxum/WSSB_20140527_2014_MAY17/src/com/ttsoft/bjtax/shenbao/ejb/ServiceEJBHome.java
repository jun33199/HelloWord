package com.ttsoft.bjtax.shenbao.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 *
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ServiceEjb Home�ӿ�</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */
public interface ServiceEJBHome extends javax.ejb.EJBHome
{
    public Service create() throws CreateException, RemoteException;
}