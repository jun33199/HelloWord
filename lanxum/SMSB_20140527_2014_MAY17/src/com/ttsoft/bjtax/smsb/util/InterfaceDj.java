/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 通过登记借口取得相关信息</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceDj {
  public InterfaceDj() {
  }

  /**
   * 取得全部登记基本信息
   * @param jsjdm 计算机代码
   * @return HashMap 登记基本信息
   * @throws Exception 查询异常
   */
  public static HashMap getDjInfo(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getDjInfo(jsjdm);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }

  }

  /** The new interface.
   * 取得全部登记基本信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return HashMap 登记基本信息
   * @throws Exception 查询异常
   */
  public static HashMap getDjInfo(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getDjInfo(jsjdm, ud);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }
  }

  /* start added by huxiaofeng 2005.8.16*/
  /** The new interface.
   * 取得全部登记基本信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return HashMap 登记基本信息
   * @throws Exception 查询异常
   */
  /**
   *
   *提出：区县600问 392大兴
   *修改人：胡小锋
   *新加方法日期：2005-8-16
   */
  public static HashMap getDjInfo_New(String jsjdm, UserData ud) throws
     Exception {
   ServiceProxy djS = new ServiceProxy();
   try {
     return djS.getDjInfo(jsjdm, ud);
   }
   catch (BaseException ex) {
     throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
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
//        throw new ApplicationException("该纳税人不存在！");
//      }
//      ret = (SWDJJBSJ) djMap.get("JBSJ");
//    }
//    catch (BaseException ex) {
//      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//    }
//    if (ud.getYhjb().equals("40")) {
//      if (!ret.getSwjgzzjgdm().substring(0,
//                                         2).equals(ud.getSsdwdm().substring(0,
//          2))) {
//        throw new ApplicationException("您没有权限察看该纳税人信息！");
//      }
//
//    }
//    if (ud.getYhjb().equals("30")) {
//
//      if (!ret.getSwjgzzjgdm().equals(ud.getSsdwdm())) {
//        if (ret.getNsrzt().equals("50") &&
//            ret.getSwjgzzjgdm().substring(2, 4).equals("00")
//            &&ret.getSwjgzzjgdm().substring(0,2).equals(ud.getSsdwdm().substring(0,2))) {
//          throw new ApplicationException("该纳税人状态为：在途！请先办理转入税务登记！");
//        }
//
//        throw new ApplicationException("您没有权限察看该纳税人信息！");
//      }
//
//      if (ret.getNsrzt().equals("50") &&
//          ret.getSwjgzzjgdm().substring(2, 4).equals("00")) {
//        throw new ApplicationException("该纳税人状态为：在途！请先办理转入税务登记！");
//      }
//
//    }
//
//    return djMap;
//
//  }

  /* end added by huxiaofeng 2005.8.16*/
  /**
   * 取得全部自然人登记基本信息
   * @param  zjlxdm  证件类型代码 长度2位 不能为空
   * @param  zjhm  证件号码 长度30位 不能为空
   * @param  gjdm  籍代码 长度3位 不能为空
   * @return HashMap 登记自然人基本信息
   * @throws Exception 查询异常
   */
  public static HashMap getZrrInfo(String zjlxdm, String zjhm, String gjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getZrrDjInfo(zjlxdm, zjhm, gjdm);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }
  }

  /**The new interface
   * 取得全部自然人登记基本信息
   * @param  zjlxdm  证件类型代码 长度2位 不能为空
   * @param  zjhm  证件号码 长度30位 不能为空
   * @param  gjdm  籍代码 长度3位 不能为空
   * @param ud 操作员信息
   * @return HashMap 登记自然人基本信息
   * @throws Exception 查询异常
   */
  public static HashMap getZrrInfo(String zjlxdm, String zjhm, String gjdm,
                                   UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    try {
      return djS.getZrrDjInfo(zjlxdm, zjhm, gjdm, ud);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }
  }

  /**
   * 取得登记基本信息
   * @param jsjdm 计算机代码
   * @return SWDJJBSJ 登记基本信息值对象
   * @throws Exception 查询异常
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
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }
    return ret;
  }
  /* start added by huxiaofeng 2005.8.16*/
  /**
  * 取得登记基本信息
  * @param jsjdm 计算机代码
  * @return SWDJJBSJ 登记基本信息值对象
  * @throws Exception 查询异常
  */
// public static SWDJJBSJ getJBSJ_New2(String jsjdm) throws
//     Exception {
//   SWDJJBSJ ret = null;
//   ServiceProxy djS = new ServiceProxy();
//   try {
//     HashMap djMap = djS.getDjInfo(jsjdm);
//     if (djMap == null) {
//      throw new ApplicationException("该纳税人不存在！");
//    }
//
//     ret = (SWDJJBSJ) djMap.get("JBSJ");
//   }
//   catch (BaseException ex) {
//     throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
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
     throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
   }
   return ret;
 }

  /* end added by huxiaofeng 2005.8.16*/

  /**The new interface
   * 取得登记基本信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return SWDJJBSJ 登记基本信息值对象
   * @throws Exception 查询异常
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
      throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
    }
    return ret;
  }
/* start added by huxiaofeng 2005.8.16*/
  /**The new interface
  * 取得登记基本信息
  * @param jsjdm 计算机代码
  * @param ud 操作员信息
  * @return SWDJJBSJ 登记基本信息值对象
  * @throws Exception 查询异常
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
    throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
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
//      throw new ApplicationException("该纳税人不存在！");
//    }
//
//     ret = (SWDJJBSJ) djMap.get("JBSJ");
//   }
//   catch (BaseException ex) {
//     throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
//   }
//   if (ud.getYhjb().equals("40")) {
//     if (!ret.getSwjgzzjgdm().substring(0,
//                                        2).equals(ud.getSsdwdm().substring(0,
//         2))) {
//       throw new ApplicationException("您没有权限察看该纳税人信息！");
//     }
//
//   }
//   if (ud.getYhjb().equals("30")) {
//
//     if (!ret.getSwjgzzjgdm().equals(ud.getSsdwdm())) {
//       if (ret.getNsrzt().equals("50") &&
//           ret.getSwjgzzjgdm().substring(2, 4).equals("00")
//           &&ret.getSwjgzzjgdm().substring(0,2).equals(ud.getSsdwdm().substring(0,2))) {
//         throw new ApplicationException("该纳税人状态为：在途！请先办理转入税务登记！");
//       }
//
//       throw new ApplicationException("您没有权限察看该纳税人信息！");
//     }
//
//     if (ret.getNsrzt().equals("50") &&
//         ret.getSwjgzzjgdm().substring(2, 4).equals("00")) {
//       throw new ApplicationException("该纳税人状态为：在途！请先办理转入税务登记！");
//     }
//
//   }
//   return ret;
// }

/* end added by huxiaofeng 2005.8.16*/


  /**
   * 取得纳税人银行信息
   * @param jsjdm 计算机代码
   * @return List YHZH值对象列表
   * @throws Exception 查询异常
   */
  public static List getYHZH(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm).get("YHZH");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询银行信息失败!");
    }
    return ret;

  }

  /**The new interface.
   * 取得纳税人银行信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return List YHZH值对象列表
   * @throws Exception 查询异常
   */
  public static List getYHZH(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm, ud).get("YHZH");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询银行信息失败!");
    }
    return ret;

  }

  /**
   * 取得纳税人投资方信息
   * @param jsjdm 计算机代码
   * @return List TZF值对象列表
   * @throws Exception 查询异常
   */
  public static List getTZF(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm).get("TZF");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询投资方信息失败!");
    }
    return ret;
  }

  /**The new interface
   * 取得纳税人投资方信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return List TZF值对象列表
   * @throws Exception 查询异常
   */
  public static List getTZF(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    List ret = null;
    try {
      ret = (List) djS.getDjInfo(jsjdm, ud).get("TZF");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询投资方信息失败!");
    }
    return ret;
  }

  /**
   * 取得纳税人企业人员信息
   * @param jsjdm 计算机代码
   * @return  QYRY值对象
   * @throws Exception 查询异常
   */
  public static QYRY getQYRY(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    QYRY ret = new QYRY();
    try {
      ret = (QYRY) djS.getDjInfo(jsjdm).get("QYRY");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询企业人员信息失败!");
    }
    return ret;
  }

  /**The new interface
   * 取得纳税人企业人员信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return  QYRY值对象
   * @throws Exception 查询异常
   */
  public static QYRY getQYRY(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    QYRY ret = new QYRY();
    try {
      ret = (QYRY) djS.getDjInfo(jsjdm, ud).get("QYRY");
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询企业人员信息失败!");
    }
    return ret;
  }

  /**
   * 取自然人登记信息
   * @param  zjlxdm  证件类型代码 长度2位 不能为空
   * @param  zjhm  证件号码 长度30位 不能为空
   * @param  gjdm  籍代码 长度3位 不能为空
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
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
        throw new Exception("不存在该自然人信息");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
    }
    return ret;

  }

  /**The new interface.
   * 取自然人登记信息
   * @param  zjlxdm  证件类型代码 长度2位 不能为空
   * @param  zjhm  证件号码 长度30位 不能为空
   * @param  gjdm  籍代码 长度3位 不能为空
   * @param ud 操作员信息
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
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
        throw new Exception("不存在该自然人信息");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
    }
    return ret;

  }

  /**
   * 取自然人登记信息
   * @param jsjdm 计算机代码
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
   */
  public static ZRRJBSJ getZRRJBSJ(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("不存在该自然人信息");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
    }
    return ret;

  }

  /**
   * 取自然人登记信息
   * @param jsjdm 计算机代码
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
   */
  public static Map getZRRInfo(String jsjdm) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("不存在该自然人信息");
      }
      return djMap;
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
    }
    //return ret;

  }

  /**The new interface
   * 取自然人登记信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
   */
  public static ZRRJBSJ getZRRJBSJ(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new Exception("不存在该自然人信息");
      }
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
    }
    return ret;

  }

  /**The new interface
   * 取自然人登记信息
   * @param jsjdm 计算机代码
   * @param ud 操作员信息
   * @return  ZRRJBSJ值对象
   * @throws Exception 查询异常
   */
  public static Map getZRRInfo(String jsjdm, UserData ud) throws
      Exception {
    ServiceProxy djS = new ServiceProxy();
    ZRRJBSJ ret = new ZRRJBSJ();
    try {
      Map djMap = djS.getZrrDjInfo(jsjdm, ud);
      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
      if (ret == null) {
        throw new ApplicationException("不存在该自然人信息或没有操作该纳税人的权限！");
      }
//      djMap = djS.getZrrDjInfo(ret.getZjlxdm(),ret.getZjhm(),ret.getGjdm(),ud);
//      ret = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
//      if (ret == null) {
//        throw new ApplicationException("不存在该自然人信息或没有操作该纳税人的权限！");
//      }

      return djMap;
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "不存在该自然人信息或没有操作该纳税人的权限！");
    }
    //return ret;

  }

  /**
   * 根据税务机关组织机构代码取得相应的计算机代码
   * @param  userData 总控得到的用户信息
   * @return String 计算机代码
   *
   */
  public static String getZrrJsjdm(UserData userData) {
    String jsjdm = null;
    //根据总控得到税务机关组织机构代码
    String ssdw = userData.getSsdwdm();
    //由税务机关组织机构代码表得到计算机代码
    jsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG", "swjgzzjgdm", ssdw,
                                      "jsjdm");
    return jsjdm;
  }

  /**
   * 根据税务机关组织机构代码取得相应的区县代码
   * @param  userData 总控得到的用户信息
   * @return String 区县代码
   */
  public static String getQxdm(UserData userData) {
    String ret = "";
    if (userData == null) {
      return ret;
    }
    //得到税务机关组织机构代码
    String swjgzzjgdm = userData.getSsdwdm();
    //区县代码=税务机关组织机构代码的前两位
    ret = swjgzzjgdm.substring(0, 2);
    return ret;
  }
}
