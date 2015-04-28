/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 为前台调用后台使用的processor模块提供统一接口的Proxy类</p>
 * @author 契税开发组－－赵博
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
     * 获取此proxy的实例.
     *
     * @return PzglProxy the PzglProxy.
     */
    public static QsglProxy getInstance() {
        try {
            ejbHome.create();
//            Debug.out("检查 QsglProxy.getInstance() 是否可用！");
        } catch (Exception ex) {
//            Debug.out("QsglProxy.getInstance() 不可用，生成新的 QsglProxy 实例！");
            instance = new QsglProxy();
            Debug.out("重新实例化 QsglProxy ！");
        }

        return instance;
    }

    /**
     * vo中确定actionType来指定所要使用的接口。
     *
     * @param vo        VOPackage
     * @return Object   返回的业务对象
     * @throws BaseException 抛出的异常
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
