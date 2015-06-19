/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.proxy;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.creationstar.bjtax.qsgl.BizLogic.ejb.QsglSession;
import com.creationstar.bjtax.qsgl.BizLogic.ejb.QsglSessionHome;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QsglPropertiesUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: Ϊǰ̨���ú�̨ʹ�õ�processorģ���ṩͳһ�ӿڵ�Proxy��</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class QsglProxy {
    private static QsglSessionHome ejbHome;
    protected static QsglProxy instance = new QsglProxy();
    private static InitialContext initContex;

    protected QsglProxy() {

        try {
            Properties prop = new Properties();

            prop.load(this.getClass().getClassLoader().getResourceAsStream(
                    "InitialConfig.properties"));
//            String url = prop.getProperty("PROVIDER_URL");
//            String contextFactory = prop.getProperty("INITIAL_CONTEXT_FACTORY");
//
//            Properties properties = new Properties();
//            properties.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
//            properties.put(Context.PROVIDER_URL, url);
//
//            initContex = new InitialContext(properties);
            initContex = new InitialContext(prop);

            Properties prop1 = QsglPropertiesUtil.getProperties(Constants.
                    QSGL_PROPERTIES_FILE_NAME);
            String JNDI_NAME = prop1.getProperty(Constants.
                                                 JNDI_QsglEJBSessionBean_HOME);
            Object test = initContex.lookup(JNDI_NAME);
            ejbHome = (QsglSessionHome) PortableRemoteObject.narrow(test,
                    QsglSessionHome.class);

            //ResourceLocator.getEjbHome(JNDI_NAME,QsglSessionHome.class.getName());
        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }

    /**
     * ��ȡ��proxy��ʵ��.
     *
     * @return PzglProxy the PzglProxy.
     */
    public static QsglProxy getInstance() {
        try {
            ejbHome.create();
//            Debug.out("��� QsglProxy.getInstance() �Ƿ���ã�");
        } catch (Exception ex) {
//            Debug.out("QsglProxy.getInstance() �����ã������µ� QsglProxy ʵ����");
            instance = new QsglProxy();
            Debug.out("����ʵ���� QsglProxy ��");
        }

        return instance;
    }

    /**
     * vo��ȷ��actionType��ָ����Ҫʹ�õĽӿڡ�
     *
     * @param vo        VOPackage
     * @return Object   ���ص�ҵ�����
     * @throws BaseException �׳����쳣
     */
    public Object process(VOPackage vo) throws BaseException {
        try {
            QsglSession ejb = ejbHome.create();

            return ejb.process(vo);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}
