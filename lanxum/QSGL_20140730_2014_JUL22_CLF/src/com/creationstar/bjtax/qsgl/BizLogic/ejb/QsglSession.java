/*
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 * <p>Description: PzglEJB(Ʊ֤ҵ��SessionBean) ��remote�ӿ� </p>
 * @author �������飭�������
 * @version 1.1
 */
public interface QsglSession extends javax.ejb.EJBObject {
    public Object process(VOPackage vo) throws RemoteException, BaseException;
}
