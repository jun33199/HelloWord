/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;


/**
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 * <p>Description: PzglEJB(Ʊ֤ҵ��SessionBean) ��home�ӿ� </p>
 * @author ��˰�����飭���Բ�
 * @version 1.1
 */
public interface QsglSessionHome extends javax.ejb.EJBHome {
    public QsglSession create() throws CreateException, RemoteException;
}
