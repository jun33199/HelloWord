/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.proxy;


import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator;
import com.ttsoft.bjtax.smsb.ejb.SbzlEJBHome;
import com.ttsoft.bjtax.smsb.ejb.SbzlEJB;
import java.rmi.RemoteException;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ۺ��걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SbzlProxy {
  private static SbzlProxy instance = null;
  private static Object initLock = new Object();
  private static SbzlEJBHome ejbHome = null;

  private SbzlProxy() {
    try {
      ejbHome = (SbzlEJBHome) SfResourceLocator.getEjbHome(
          JNDINames.SBZL_HOME_NAME,
          JNDINames.SBZL_CLASS_NAME);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
/**
 * �õ�proxyʵ��
 * @return SbzlProxy
 */
  public static SbzlProxy getInstance() {
    if (instance == null) {
      synchronized (initLock) {
        if (instance == null) {
          instance = new SbzlProxy();
        }
      }
    }
    return instance;
  }
  /**
   * process����
   * @param vo ���ݼ�
   * @return Object
   * @throws Exception
   */
  public Object process(VOPackage vo) throws Exception {
    try {
      SbzlEJB ejb = ejbHome.create();
      Debug.out("-------RUN IN PROXY-------");
      return ejb.process(vo);
    }
    catch (BaseException ex){
      Debug.out("-----Proxy--BaseException-------"+ex.getMessage());
      throw ExceptionUtil.getBaseException(ex);
    }
    catch (RemoteException ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(new Exception("RemoteException:����ʧ���������Ա��ϵ!"));
    }
    catch (Exception ex) {
      Debug.out("---------Proxy----------"+ex.getMessage());
      throw ExceptionUtil.getBaseException(ex);
    }

  }
}
