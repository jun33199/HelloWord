package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;


/**
 *
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 *
 * <p>Description: ��˰��˰ҵ���remote�ӿ� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public interface QsglServiceSessionHome extends javax.ejb.EJBHome {
    public QsglServiceSession create() throws CreateException, RemoteException;
}
