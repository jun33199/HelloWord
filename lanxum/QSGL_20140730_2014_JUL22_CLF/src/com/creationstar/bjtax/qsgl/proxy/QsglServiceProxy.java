package com.creationstar.bjtax.qsgl.proxy;

import java.util.ArrayList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.creationstar.bjtax.qsgl.BizLogic.ejb.QsglServiceSession;
import com.creationstar.bjtax.qsgl.BizLogic.ejb.QsglServiceSessionHome;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 为前台调用后台使用的processor模块提供统一接口的Proxy类</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class QsglServiceProxy {
    private static QsglServiceSessionHome ejbHome;
    protected static QsglServiceProxy instance = new QsglServiceProxy();
    private static InitialContext initContex;

    protected QsglServiceProxy() {

        try {
            Properties prop = new Properties();

            prop.load(this.getClass().getClassLoader().getResourceAsStream(
                    "InitialConfig.properties"));
            String url = prop.getProperty("PROVIDER_URL");
            String contextFactory = prop.getProperty("INITIAL_CONTEXT_FACTORY");

            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
            properties.put(Context.PROVIDER_URL, url);

            initContex = new InitialContext(properties);

            String JNDI_NAME =
                    "com.creationstar.bjtax.qsgl.BizLogic.ejb.QsglServiceSessionHome";
            Object test = initContex.lookup(JNDI_NAME);
            ejbHome = (QsglServiceSessionHome) PortableRemoteObject.narrow(test,
                    QsglServiceSessionHome.class);

//            ResourceLocator.getEjbHome(JNDI_NAME,QsglSessionHome.class.getName());
        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }

    /**
     * 获取此proxy的实例.
     *
     * @return PzglProxy the PzglProxy.
     */
    public static QsglServiceProxy getInstance() {
        try {
            ejbHome.create();
            Debug.out("检查 QsglServiceProxy.getInstance() 是否可用！");
        } catch (Exception ex) {
            Debug.out("QsglServiceProxy.getInstance() 不可用，生成新的 QsglProxy 实例！");
            instance = new QsglServiceProxy();
            Debug.out("已经重新实例化 QsglServiceProxy ！");
        }

        return instance;
    }

    /**
     * 契税给退税业务做的接口
     * @param wszh String  完税证号
     * @param ndzb String  年度字别
     * @return Object
     * @throws BaseException
     */
    public Object getWszDataQS(String wszh, String ndzb) throws BaseException {
        try {
            QsglServiceSession ejb = ejbHome.create();

            return ejb.getWszDataQS(wszh, ndzb);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }


    /**
     * 给票证提供的接口
     * @param ndzb_wszh ArrayList
     * @param jbdh String
     * @param ud UserData
     * @return boolean
     * @throws BaseException
     */
    public boolean jiebao(ArrayList ndzb_wszh, String jbdh, UserData ud) throws
            BaseException {
        try {
            QsglServiceSession ejb = ejbHome.create();

            return ejb.jiebao(ndzb_wszh, jbdh, ud);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public static void main(String[] args) {
        try {
            /*QsglServiceProxy instnace  = QsglServiceProxy.getInstance();
                         String ndzb = "2004";
                         String wszh = "1234567";
                         String haoma = ndzb + wszh;
                         ArrayList list = new ArrayList();
                         list.add(haoma);

                         UserData ud = new UserData();
                         ud.yhid = "hanl";
                         ud.ssdwdm = "0601";
                         ud.yhmc = "韩雷";
                         ud.xtsbm1 = "0106010017";

                         boolean flag = instance.jiebao(list, "888999", ud);
                         System.out.println("flag: " + flag);
             ArrayList wszList = (ArrayList)instance.getWszDataQS("0041186", "2003");
                         System.out.println(wszList.size() );
                         if(wszList.size() > 0)
                         {
                Qswszz wsz = ((QueryWszBo) wszList.get(0)).getQswszzVo();
                System.out.println(wsz.getHjsjje());
                         }*/
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
