package com.ttsoft.bjtax.shenbao.proxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.ejb.Service;
import com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.ResourceLocator;

/**
 *
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>�걨�����ṩ����Ľӿ� </p>
 * <p>Copyright:  (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class ServiceProxy {
  private static ServiceEJBHome ejbHome = null;
  private static final String SHENBAO_HOME_NAME = "ServiceEJB";
  private static final String SHENBAO_CLASS_NAME =
      "com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome";
  private ServiceProxy() {
  }

  private static Service getEJB() {
    try {
      if (ejbHome == null) {
        //�õ�ShenbaoEjb home�ӿ�
        ejbHome = (ServiceEJBHome) ResourceLocator.getEjbHome(
            SHENBAO_HOME_NAME,
            SHENBAO_CLASS_NAME);
      }
      return ejbHome.create();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * �ɿ�����ϸ��Ϣ
   * @param sbsj �걨����
   * @return �걨�ɿ�����
   * @throws BaseException
   */
  public static DeclareInfor getJkInforBySbsj(Sbsj sbsj) throws BaseException {
    try {
      return getEJB().getJkInforBySbsj(sbsj);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * �ɿ�����ϸ��Ϣ
   * @param sbsj �걨����
   * @return �걨�ɿ�����
   * @throws BaseException
   */
  public static List getJkInforListBySbsj(Map sbsjMap) throws BaseException {
    try {
      return getEJB().getJkInforListBySbsj(sbsjMap);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * �ɿ�����ϸ��Ϣ
   * @param sbsj �걨����
   * @return �걨�ɿ�����
   * @throws BaseException
   */
  public static DeclareInfor getJkInforBySbsj(Sbsj sbsj, String yhdm,
                                              String yhmc, String zh) throws
      BaseException {
    try {
      return getEJB().getJkInforBySbsj(sbsj, yhdm, yhmc, zh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ȡ�������
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @param ywlx ҵ������
   * @return List �ɿ������б�
   * @throws BaseException
   */
  public static List getJkDataByJkpzh(String[] jkpzh, int ywlx) throws
      BaseException {
    try {
      return getEJB().getJkDataByJkpzh(jkpzh, ywlx);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
  * ȡ�������
  * @param sphm ˰Ʊ�����ַ�����
  * @param ywlx ҵ������
  * @return List �ɿ������б�
  * @throws BaseException
  */
 public static List getJkDataBySphm(String[] sphm, int ywlx) throws
     BaseException {
   try {
     return getEJB().getJkDataBySphm(sphm, ywlx);
   }
   catch (Exception e) {
     throw ExceptionUtil.getBaseException(e);
   }
 }

  /**
   * ��ѯ�걨�ɿ�����
   * @param jsjdm ��˰�˼��������
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @param sjly ������Դ
   * @param fsdm ��ʽ����
   * @param zwbs �����ʶ�ж�
   * @return List �ɿ������б�
   * @throws BaseException
   */
  public static List searchJkData(String jsjdm, String[] jkpzh, String sjly,
                                  String fsdm, int zwbs) throws BaseException {
    try {
      return getEJB().searchJkData(jsjdm, jkpzh, sjly, fsdm, zwbs);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ����ᱨ����
   * @param wszhList ��˰֤�б�
   * @param xspzhList ����ƾ֤�б�
   * @param jbdh �ᱨ����
   * @param userData �û���Ϣ
   * @throws BaseException
   */
  public static void jiebao(List wszhList, List xspzhList, String jbdh,
                            UserData userData) throws BaseException {
    try {
      getEJB().jiebao(wszhList, xspzhList, jbdh, userData);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ��ѯ�ᱨ����Ӧ����˰֤�Ƿ������
   * @param jbdh �ᱨ����
   * @return true ����⣬false δ���
   * @throws BaseException
   */
  public static boolean cxsfrk(String jbdh) throws BaseException {
    try {
      return getEJB().cxsfrk(jbdh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ��ѯ��˰����Ӧ�Ľɿ�������
   * @param wszhArray ��˰֤���б�
   * @return �ɿ�����Ϣ�б�
   * @throws BaseException
   */
  public static List getJkpzhJeByWszh(String[] wszhArray) throws BaseException {
    try {
      return getEJB().getJkpzhJeByWszh(wszhArray);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ��ѯ��˰����Ӧ�Ľɿ�������
   * @param wszhArray ��˰֤���б�
   * @return �ɿ�����Ϣ�б�
   * @throws BaseException
   */
  public static List getJkpzhJeByWszhForLw(String[] wszhArray) throws BaseException {
    try {
      return getEJB().getJkpzhJeByWszhForLw(wszhArray);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ɾ���ɿ�ƾ֤����
   * @param jkpzhList �ɿ�ƾ֤���б�
   * @throws BaseException
   */
  public static void deleteJKS(List jkpzhList) throws BaseException {
    try {
      getEJB().deleteJKS(jkpzhList);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * �õ��ɿ�ƾ֤��
   * @param jsjdm ���������
   * @return  �ɿ�ƾ֤��
   * @throws BaseException
   */
  public static String getJkpzh(String jsjdm) throws BaseException {
    try {
      return getEJB().getJkpzh(jsjdm);

    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ȡ�걨�ɿ�����δ�������
   * @param jsjdm ���������
   * @param time ʱ��
   * @return List
   * @throws BaseException
   */
  public static List getSbjkyqwrkData(String jsjdm, Date time) throws
      BaseException {
    try {
      return getEJB().getSbjkyqwrkData(jsjdm, time);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ���ݼ���������ȡ�걨���
   * @param jsjdm ���������
   * @return String sbbh
   * @throws BaseException
   */
  public static String getSbbh(String jsjdm) throws BaseException {
    try {
      return getEJB().getSbbh(jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ���ݼ���������ȡ˰Ʊ����
   * @param jsjdm ���������
   * @return String sphm
   * @throws BaseException
   */
  public static String getSphm(String jsjdm) throws BaseException {
    try {
      return getEJB().getSbbh(jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  //Ʊ֤��ӡ��˰�������ݽ��д�����ýӿ�
  /**
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �����ʻ������ѯӡ��˰������ϸ����
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ArrayList ��ϸ�����б�
   * @throws BaseException
   */
  public static ArrayList getYhssj(String zhdm, String jsjdm) throws
      BaseException {
    try {
      return getEJB().getYhssj(zhdm, jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }



  //Ʊ֤��ӡ��˰�������ݽ��д�����ýӿ�
  /**
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �����ʻ������ѯӡ��˰������ϸ����
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ArrayList ��ϸ�����б�
   * @throws BaseException
   */
  public static ArrayList getYhssjForLw(String zhdm, String jsjdm) throws
      BaseException {
    try {
      return getEJB().getYhssjForLw(zhdm, jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }


  /**
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �޸ļ��ʱ�ʶ
   * ���Ѿ���¼��Ʊ֤���е�ӡ��˰���ۼ�¼�ļ��ʱ�ʶ��0��Ϊ1
   * @param xspzhList ӡ��˰����ƾ֤���б�
   * @param jsjdm ���������
   * @param lx ���ʱ�ʶ�������� 0������Ϊδ���ʣ� 1������Ϊ�Ѽ���
   * @throws BaseException
   */
  public static void updateJzbs(ArrayList xspzhList, String jsjdm, int lx) throws
      BaseException {
    try {
      getEJB().updateJzbs(xspzhList, jsjdm, lx);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * �������ڴ���ģ����ã�
   * ���ݴ���ģ����걨���ݣ����ɽɿ��飬�����ؽɿ�����Ϣ
   * @param sbsj �걨����
   * @return DeclareInfor �ɿ�����
   * @throws BaseException
   */
  public static DeclareInfor getJksBySbsjForCF(Sbsj sbsj) throws BaseException {
    try {
      return getEJB().getJksBySbsjForCF(sbsj);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ΪƱ֤ģ���ṩ�ĸ��ݽɿ�ƾ֤�Ż�ȡ��ƾ֤�����Ľӿڷ���
   * @param jkpzh �ɿ�ƾ֤
   * @return rkje �����
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZ(String jkpzh) throws BaseException {
    try {
      return getEJB().getRkjeByJkpzhPZ(jkpzh);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ΪƱ֤ģ���ṩ�ĸ��ݽɿ�ƾ֤�Ż�ȡ��ƾ֤�����Ľӿڷ���
   * @param jkpzh �ɿ�ƾ֤
   * @return rkje �����
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZForLw(String jkpzh) throws BaseException {
    try {
      return getEJB().getRkjeByJkpzhPZForLw(jkpzh);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * ��������ֱ����˰֤�ż����ʹ��룬ȡ��Ӧ����˰����ϸ����
   * @param wszh ��˰֤��
   * @param nd ���
   * @param lxdm ���ʹ��룺1������ɢ˰��˰֤��2�������幤�̻���˰֤��
   * @return List
   * @throws BaseException
   */
  public static List getWszData(String wszh, String nd, int lxdm) throws
      BaseException {
    try {
      return getEJB().getWszData(wszh, nd, lxdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

//    4.����Դ��λ����һλ����ϵͳ����Ϊ�걨ϵͳ2Ϊ�ƻ�ϵͳ��Ϊ����ϵͳ��Ϊ����ϵͳ���ڶ�λ����ʽ��Ϊ¼�룲Ϊ���룺
//    �� 1��  �����걨¼��
//    ������  �����걨����
//    �� 13   ������ɢ����
//    ������  ������幤�̻�����
//    ������  ����ӡ��˰����
//    ������  ������������
//       17   ������ɢ¼��
//    �� 18   ������幤�̻�¼��
//      1 9   ������Ȼ�˲���
//    ��2 0   �����⼮���˲���
//      5 ��  ����ƻ�ϵͳ¼��
//    ��6 ��  �������ϵͳ¼��
//    ��7 ��  �����ڴ���¼��
//    ��8 1   ��˰����¼��
  public final static String SBJK_SJLY_SBLR = "11";
  public final static String SBJK_SJLY_SBDR = "12";
  public final static String SBJK_SJLY_LSHZ = "13";
  public final static String SBJK_SJLY_GTGSHHZ = "14";
  public final static String SBJK_SJLY_YHSHZ = "15";
  public final static String SBJK_SJLY_SDHZ = "16";
  public final static String SBJK_SJLY_LSLR = "17";
  public final static String SBJK_SJLY_GTGSHLR = "18";
  public final static String SBJK_SJLY_ZRRLR = "19";
  public final static String SBJK_SJLY_WJGRLR = "20";
  public final static String SBJK_SJLY_JKLR = "51";
  public final static String SBJK_SJLY_JCLR = "61";
  public final static String SBJK_SJLY_CKCFLR = "71";
  public final static String SBJK_SJLY_NSPGLR = "81";

  //��˰�����ݳ���
  public final static int LSSMX = 1;
  public final static int GTGSHMX = 2;

  /**
   * add by Daniel �����걨�ɿ�ӿ�
   * �������д�����һ���걨���ݣ����ɽɿ��飬�����ؽɿ�������
   * @throws BaseException
   */
  public static List generateBankSBJKS(Map sbsjMap) throws BaseException {
    try {
      return getEJB().generateBankSBJKS(sbsjMap);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯��
   */
  public static List getYpdsJks(String sbbh) throws BaseException {
    try {
      return getEJB().getYpdsJks(sbbh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }



  /**
   * ����˰Ʊ�����ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯��
   */
  public static List getYpdsJksBySphm(String sphm) throws BaseException {
    try {
      return getEJB().getYpdsJksBySphm(sphm);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }


  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param JksList һƱһ˰�ɿ��鼯��,�ü��ϱ��뱣֤����ͬһ���걨
   * @return һƱ��˰�ɿ��鼯��
   */
  public static List getYpdsJks(List JksList) throws BaseException {
    try {
      return getEJB().getYpdsJks(JksList);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }
  
  /**
	 * @desc ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
	 * @author gaoyh
	 * @date 20131219
	 * @param yskmdm
	 * @param szsmdm
	 * @param swjgzzjgdm
	 * @return String
	 * @throws BaseException
	 */
	public static String getYskmFcblmc(String tmpYskmdm, String tmpSzsmdm,
			String tmpSwjgzzjgdm) throws BaseException {
		try {
			return getEJB().getYskmFcblmc(tmpYskmdm, tmpSzsmdm, tmpSwjgzzjgdm);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}

	}


}