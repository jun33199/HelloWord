/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.proxy;

import java.rmi.RemoteException;

import com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.ejb.ZhsbEJB;
import com.ttsoft.bjtax.smsb.ejb.ZhsbEJBHome;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description: �ۺ��걨
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbProxy {
	private static ZhsbProxy instance = null;

	private static Object initLock = new Object();

	private static ZhsbEJBHome ejbHome = null;

	private ZhsbProxy() {
		try {
			ejbHome = (ZhsbEJBHome) SfResourceLocator.getEjbHome(
					JNDINames.ZHSB_HOME_NAME, JNDINames.ZHSB_CLASS_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * �õ�proxyʵ��
	 * 
	 * @return SbzlProxy
	 */

	public static ZhsbProxy getInstance() {
		if (instance == null) {
			synchronized (initLock) {
				if (instance == null) {
					instance = new ZhsbProxy();
				}
			}
		}
		return instance;
	}

	/**
	 * process����
	 * 
	 * @param vo
	 *            ���ݼ�
	 * @return Object
	 * @throws Exception
	 */

	public Object process(VOPackage vo) throws Exception {
		try {
			ZhsbEJB ejb = ejbHome.create();
			Debug.out("-------RUN IN PROXY-------");
			return ejb.process(vo);
		} catch (BaseException ex) {
			Debug.out("-----Proxy--BaseException-------" + ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		} catch (RemoteException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(new Exception("����ʧ���������Ա��ϵ!"));
		} catch (Exception ex) {
			Debug.out("---------Proxy----------" + ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}

	}
}
