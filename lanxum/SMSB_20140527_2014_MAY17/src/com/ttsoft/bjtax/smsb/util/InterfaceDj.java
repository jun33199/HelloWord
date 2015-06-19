/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.QYRY;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ͨ���Ǽǽ��ȡ�������Ϣ</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceDj {
  public InterfaceDj() {
  }

  /**
   * ȡ��ȫ���Ǽǻ�����Ϣ
   * @param jsjdm ���������
   * @return HashMap �Ǽǻ�����Ϣ
   * @throws Exception ��ѯ�쳣
   */
  public static HashMap getDjInfo(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getDjInfo(jsjdm);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }

  }

  /** The new interface.
   * ȡ��ȫ���Ǽǻ�����Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return HashMap �Ǽǻ�����Ϣ
   * @throws Exception ��ѯ�쳣
   */
  public static HashMap getDjInfo(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getDjInfo(jsjdm, ud);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
  }

  /* start added by huxiaofeng 2005.8.16*/
  /** The new interface.
   * ȡ��ȫ���Ǽǻ�����Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return HashMap �Ǽǻ�����Ϣ
   * @throws Exception ��ѯ�쳣
   */
  /**
   *
   *���������600�� 392����
   *�޸��ˣ���С��
   *�¼ӷ������ڣ�2005-8-16
   */
  public static HashMap getDjInfo_New(String jsjdm, UserData ud) throws
     Exception {
   ServiceProxy djS = new ServiceProxy();
   try {
     return djS.getDjInfo(jsjdm, ud);
   }
   catch (BaseException ex) {
     throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
   }
 }

//  public static HashMap getDjInfo_New(String jsjdm, UserData ud) throws
//      Exception {
//    SWDJJBSJ ret = null;
//    ServiceProxy djS = new ServiceProxy();
//    HashMap djMap = null;
//    try {
//      djMap = djS.getDjInfo(jsjdm, ud);
//      if (djMap == null) {
//        throw new ApplicationException("����˰�˲����ڣ�");
//      }
//      ret = (SWDJJBSJ) djMap.get("JBSJ");
//    }
//    catch (BaseException ex) {
//      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//    }
//    if (ud.getYhjb().equals("40")) {
//      if (!ret.getSwjgzzjgdm().substring(0,
//                                         2).equals(ud.getSsdwdm().substring(0,
//          2))) {
//        throw new ApplicationException("��û��Ȩ�޲쿴����˰����Ϣ��");
//      }
//
//    }
//    if (ud.getYhjb().equals("30")) {
//
//      if (!ret.getSwjgzzjgdm().equals(ud.getSsdwdm())) {
//        if (ret.getNsrzt().equals("50") &&
//            ret.getSwjgzzjgdm().substring(2, 4).equals("00")
//            &&ret.getSwjgzzjgdm().substring(0,2).equals(ud.getSsdwdm().substring(0,2))) {
//          throw new ApplicationException("����˰��״̬Ϊ����;�����Ȱ���ת��˰��Ǽǣ�");
//        }
//
//        throw new ApplicationException("��û��Ȩ�޲쿴����˰����Ϣ��");
//      }
//
//      if (ret.getNsrzt().equals("50") &&
//          ret.getSwjgzzjgdm().substring(2, 4).equals("00")) {
//        throw new ApplicationException("����˰��״̬Ϊ����;�����Ȱ���ת��˰��Ǽǣ�");
//      }
//
//    }
//
//    return djMap;
//
//  }

  /* end added by huxiaofeng 2005.8.16*/
  /**
   * ȡ��ȫ����Ȼ�˵Ǽǻ�����Ϣ
   * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
   * @param  zjhm  ֤������ ����30λ ����Ϊ��
   * @param  gjdm  ������ ����3λ ����Ϊ��
   * @return HashMap �Ǽ���Ȼ�˻�����Ϣ
   * @throws Exception ��ѯ�쳣
   */
  public static HashMap getZrrInfo(String zjlxdm, String zjhm, String gjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getZrrDjInfo(zjlxdm, zjhm, gjdm);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
  }

  /**The new interface
   * ȡ��ȫ����Ȼ�˵Ǽǻ�����Ϣ
   * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
   * @param  zjhm  ֤������ ����30λ ����Ϊ��
   * @param  gjdm  ������ ����3λ ����Ϊ��
   * @param ud ����Ա��Ϣ
   * @return HashMap �Ǽ���Ȼ�˻�����Ϣ
   * @throws Exception ��ѯ�쳣
   */
  public static HashMap getZrrInfo(String zjlxdm, String zjhm, String gjdm,
                                   UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getZrrDjInfo(zjlxdm, zjhm, gjdm, ud);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
  }

  /**
   * ȡ�õǼǻ�����Ϣ
   * @param jsjdm ���������
   * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static SWDJJBSJ getJBSJ(String jsjdm) throws
      Exception {
    SWDJJBSJ ret = null;
    ServiceProxy djS = new ServiceProxy();
    try {
      HashMap djMap = djS.getDjInfo(jsjdm);
      ret = (SWDJJBSJ) djMap.get("JBSJ");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
    return ret;
  }
  /* start added by huxiaofeng 2005.8.16*/
  /**
  * ȡ�õǼǻ�����Ϣ
  * @param jsjdm ���������
  * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
  * @throws Exception ��ѯ�쳣
  */
// public static SWDJJBSJ getJBSJ_New2(String jsjdm) throws
//     Exception {
//   SWDJJBSJ ret = null;
//   ServiceProxy djS = new ServiceProxy();
//   try {
//     HashMap djMap = djS.getDjInfo(jsjdm);
//     if (djMap == null) {
//      throw new ApplicationException("����˰�˲����ڣ�");
//    }
//
//     ret = (SWDJJBSJ) djMap.get("JBSJ");
//   }
//   catch (BaseException ex) {
//     throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
//   }
//   return ret;
// }
 public static SWDJJBSJ getJBSJ_New2(String jsjdm) throws
     Exception {
   SWDJJBSJ ret = null;
   ServiceProxy djS = new ServiceProxy();
   try {
     HashMap djMap = djS.getDjInfo(jsjdm);
     ret = (SWDJJBSJ) djMap.get("JBSJ");
   }
   catch (BaseException ex) {
     throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
   }
   return ret;
 }

  /* end added by huxiaofeng 2005.8.16*/

  /**The new interface
   * ȡ�õǼǻ�����Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static SWDJJBSJ getJBSJ(String jsjdm, UserData ud) throws
      Exception {
    SWDJJBSJ ret = null;
    ServiceProxy djS = new ServiceProxy();
    try {
      HashMap djMap = djS.getDjInfo(jsjdm, ud);
      ret = (SWDJJBSJ) djMap.get("JBSJ");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
    return ret;
  }
/* start added by huxiaofeng 2005.8.16*/
  /**The new interface
  * ȡ�õǼǻ�����Ϣ
  * @param jsjdm ���������
  * @param ud ����Ա��Ϣ
  * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
  * @throws Exception ��ѯ�쳣
  */
 public static SWDJJBSJ getJBSJ_New(String jsjdm, UserData ud) throws
    Exception {
  SWDJJBSJ ret = null;
  ServiceProxy djS = new ServiceProxy();
  try {
    HashMap djMap = djS.getDjInfo(jsjdm, ud);
    ret = (SWDJJBSJ) djMap.get("JBSJ");
  }
  catch (BaseException ex) {
    throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
  }
  return ret;
}

// public static SWDJJBSJ getJBSJ_New(String jsjdm, UserData ud) throws
//     Exception {
//   SWDJJBSJ ret = null;
//   ServiceProxy djS = new ServiceProxy();
//   try {
//     HashMap djMap = djS.getDjInfo(jsjdm, ud);
//     if (djMap == null) {
//      throw new ApplicationException("����˰�˲����ڣ�");
//    }
//
//     ret = (SWDJJBSJ) djMap.get("JBSJ");
//   }
//   catch (BaseException ex) {
//     throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
//   }
//   if (ud.getYhjb().equals("40")) {
//     if (!ret.getSwjgzzjgdm().substring(0,
//                                        2).equals(ud.getSsdwdm().substring(0,
//         2))) {
//       throw new ApplicationException("��û��Ȩ�޲쿴����˰����Ϣ��");
//     }
//
//   }
//   if (ud.getYhjb().equals("30")) {
//
//     if (!ret.getSwjgzzjgdm().equals(ud.getSsdwdm())) {
//       if (ret.getNsrzt().equals("50") &&
//           ret.getSwjgzzjgdm().substring(2, 4).equals("00")
//           &&ret.getSwjgzzjgdm().substring(0,2).equals(ud.getSsdwdm().substring(0,2))) {
//         throw new ApplicationException("����˰��״̬Ϊ����;�����Ȱ���ת��˰��Ǽǣ�");
//       }
//
//       throw new ApplicationException("��û��Ȩ�޲쿴����˰����Ϣ��");
//     }
//
//     if (ret.getNsrzt().equals("50") &&
//         ret.getSwjgzzjgdm().substring(2, 4).equals("00")) {
//       throw new ApplicationException("����˰��״̬Ϊ����;�����Ȱ���ת��˰��Ǽǣ�");
//     }
//
//   }
//   return ret;
// }

/* end added by huxiaofeng 2005.8.16*/


  /**
   * ȡ����˰��������Ϣ
   * @param jsjdm ���������
   * @return List YHZHֵ�����б�
   * @throws Exception ��ѯ�쳣
   */
  public static List getYHZH(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm).get("YHZH");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ������Ϣʧ��!");
    }
    return ret;

  }

  /**The new interface.
   * ȡ����˰��������Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return List YHZHֵ�����б�
   * @throws Exception ��ѯ�쳣
   */
  public static List getYHZH(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm, ud).get("YHZH");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ������Ϣʧ��!");
    }
    return ret;

  }

  /**
   * ȡ����˰��Ͷ�ʷ���Ϣ
   * @param jsjdm ���������
   * @return List TZFֵ�����б�
   * @throws Exception ��ѯ�쳣
   */
  public static List getTZF(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm).get("TZF");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯͶ�ʷ���Ϣʧ��!");
    }
    return ret;
  }

  /**The new interface
   * ȡ����˰��Ͷ�ʷ���Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return List TZFֵ�����б�
   * @throws Exception ��ѯ�쳣
   */
  public static List getTZF(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm, ud).get("TZF");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯͶ�ʷ���Ϣʧ��!");
    }
    return ret;
  }

  /**
   * ȡ����˰����ҵ��Ա��Ϣ
   * @param jsjdm ���������
   * @return  QYRYֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static QYRY getQYRY(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    QYRY ret = new QYRY();
    try {
      ret = (QYRY) djS.getDjInfo(jsjdm).get("QYRY");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ��Ա��Ϣʧ��!");
    }
    return ret;
  }

  /**The new interface
   * ȡ����˰����ҵ��Ա��Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return  QYRYֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static QYRY getQYRY(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    QYRY ret = new QYRY();
    try {
      ret = (QYRY) djS.getDjInfo(jsjdm, ud).get("QYRY");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ��Ա��Ϣʧ��!");
    }
    return ret;
  }

  /**
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
   * @param  zjhm  ֤������ ����30λ ����Ϊ��
   * @param  gjdm  ������ ����3λ ����Ϊ��
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static ZRRJBSJ getZRRJBSJ(String zjlxdm, String zjhm, String gjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(zjlxdm, zjhm, gjdm);
      //ret = (ZRRJBSJ) djS.getZrrDjInfo(zjlxdm, zjhm, gjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("�����ڸ���Ȼ����Ϣ");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
    }
    return ret;

  }

  /**The new interface.
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
   * @param  zjhm  ֤������ ����30λ ����Ϊ��
   * @param  gjdm  ������ ����3λ ����Ϊ��
   * @param ud ����Ա��Ϣ
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static ZRRJBSJ getZRRJBSJ(String zjlxdm, String zjhm, String gjdm,
                                   UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(zjlxdm, zjhm, gjdm, ud);
      //ret = (ZRRJBSJ) djS.getZrrDjInfo(zjlxdm, zjhm, gjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("�����ڸ���Ȼ����Ϣ");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
    }
    return ret;

  }

  /**
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param jsjdm ���������
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static ZRRJBSJ getZRRJBSJ(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("�����ڸ���Ȼ����Ϣ");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
    }
    return ret;

  }

  /**
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param jsjdm ���������
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static Map getZRRInfo(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("�����ڸ���Ȼ����Ϣ");
      }
      return djMap;
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
    }
    //return ret;

  }

  /**The new interface
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static ZRRJBSJ getZRRJBSJ(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("�����ڸ���Ȼ����Ϣ");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
    }
    return ret;

  }

  /**The new interface
   * ȡ��Ȼ�˵Ǽ���Ϣ
   * @param jsjdm ���������
   * @param ud ����Ա��Ϣ
   * @return  ZRRJBSJֵ����
   * @throws Exception ��ѯ�쳣
   */
  public static Map getZRRInfo(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm, ud);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new ApplicationException("�����ڸ���Ȼ����Ϣ��û�в�������˰�˵�Ȩ�ޣ�");
      }
//      djMap = djS.getZrrDjInfo(ret.getZjlxdm(),ret.getZjhm(),ret.getGjdm(),ud);
//      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
//      if (ret == null) {
//        throw new ApplicationException("�����ڸ���Ȼ����Ϣ��û�в�������˰�˵�Ȩ�ޣ�");
//      }

      return djMap;
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "�����ڸ���Ȼ����Ϣ��û�в�������˰�˵�Ȩ�ޣ�");
    }
    //return ret;

  }

  /**
   * ����˰�������֯��������ȡ����Ӧ�ļ��������
   * @param  userData �ܿصõ����û���Ϣ
   * @return String ���������
   *
   */
  public static String getZrrJsjdm(UserData userData) {
    String jsjdm = null;
    //�����ܿصõ�˰�������֯��������
    String ssdw = userData.getSsdwdm();
    //��˰�������֯���������õ����������
    jsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG", "swjgzzjgdm", ssdw,
                                      "jsjdm");
    return jsjdm;
  }

  /**
   * ����˰�������֯��������ȡ����Ӧ�����ش���
   * @param  userData �ܿصõ����û���Ϣ
   * @return String ���ش���
   */
  public static String getQxdm(UserData userData) {
    String ret = "";
    if (userData == null) {
      return ret;
    }
    //�õ�˰�������֯��������
    String swjgzzjgdm = userData.getSsdwdm();
    //���ش���=˰�������֯���������ǰ��λ
    ret = swjgzzjgdm.substring(0, 2);
    return ret;
  }
}
