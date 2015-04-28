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
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: Ϊǰ̨���ú�̨ʹ�õ�processorģ���ṩͳһ�ӿڵ�Proxy��</p>
 * @author ��˰�����飭���Բ�
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
     * ��ȡ��proxy��ʵ��.
     *
     * @return PzglProxy the PzglProxy.
     */
    public static QsglServiceProxy getInstance() {
        try {
            ejbHome.create();
            Debug.out("��� QsglServiceProxy.getInstance() �Ƿ���ã�");
        } catch (Exception ex) {
            Debug.out("QsglServiceProxy.getInstance() �����ã������µ� QsglProxy ʵ����");
            instance = new QsglServiceProxy();
            Debug.out("�Ѿ�����ʵ���� QsglServiceProxy ��");
        }

        return instance;
    }

    /**
     * ��˰����˰ҵ�����Ľӿ�
     * @param wszh String  ��˰֤��
     * @param ndzb String  ����ֱ�
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
     * ��Ʊ֤�ṩ�Ľӿ�
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
                         ud.yhmc = "����";
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
