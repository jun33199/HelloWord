package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;


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
public interface QsglServiceSession extends javax.ejb.EJBObject {
    public Object getWszDataQS(String wszh, String ndzb) throws RemoteException,
            BaseException;

    public boolean jiebao(ArrayList ndzb_wszh, String jbdh, UserData ud) throws
            RemoteException, BaseException;
}
