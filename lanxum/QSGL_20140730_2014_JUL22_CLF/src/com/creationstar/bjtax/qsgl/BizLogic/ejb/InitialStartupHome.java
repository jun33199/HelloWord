/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: InitialStartupEJB(��˰ģ���ʼ����SessionBean) ��home�ӿ� </p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public interface InitialStartupHome extends javax.ejb.EJBHome {
    public InitialStartup create() throws CreateException, RemoteException;
}
