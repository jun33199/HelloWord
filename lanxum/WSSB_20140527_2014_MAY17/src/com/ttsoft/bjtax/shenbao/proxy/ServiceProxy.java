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
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>申报对外提供服务的接口 </p>
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
        //得到ShenbaoEjb home接口
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
   * 缴款书详细信息
   * @param sbsj 申报数据
   * @return 申报缴款数据
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
   * 缴款书详细信息
   * @param sbsj 申报数据
   * @return 申报缴款数据
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
   * 缴款书详细信息
   * @param sbsj 申报数据
   * @return 申报缴款数据
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
   * 取入库数据
   * @param jkpzh 缴款凭证号字符数组
   * @param ywlx 业务类型
   * @return List 缴款数据列表
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
  * 取入库数据
  * @param sphm 税票号码字符数组
  * @param ywlx 业务类型
  * @return List 缴款数据列表
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
   * 查询申报缴款数据
   * @param jsjdm 纳税人计算机代码
   * @param jkpzh 缴款凭证号字符数组
   * @param sjly 数据来源
   * @param fsdm 方式代码
   * @param zwbs 帐务标识判断
   * @return List 缴款数据列表
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
   * 回填结报单号
   * @param wszhList 完税证列表
   * @param xspzhList 销售凭证列表
   * @param jbdh 结报单号
   * @param userData 用户信息
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
   * 查询结报单对应的完税证是否已入库
   * @param jbdh 结报单号
   * @return true 已入库，false 未入库
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
   * 查询完税征对应的缴款书数据
   * @param wszhArray 完税证号列表
   * @return 缴款书信息列表
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
   * 查询完税征对应的缴款书数据
   * @param wszhArray 完税证号列表
   * @return 缴款书信息列表
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
   * 删除缴款凭证数据
   * @param jkpzhList 缴款凭证号列表
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
   * 得到缴款凭证号
   * @param jsjdm 计算机代码
   * @return  缴款凭证号
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
   * 取申报缴款逾期未入库数据
   * @param jsjdm 计算机代码
   * @param time 时间
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
   * 根据计算机代码获取申报编号
   * @param jsjdm 计算机代码
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
   * 根据计算机代码获取税票号码
   * @param jsjdm 计算机代码
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

  //票证对印花税销售数据进行处理调用接口
  /**
   * 票证模块处理印花税用
   * 根据帐户代码查询印花税购买明细数据
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return ArrayList 明细数据列表
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



  //票证对印花税销售数据进行处理调用接口
  /**
   * 票证模块处理印花税用
   * 根据帐户代码查询印花税购买明细数据
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return ArrayList 明细数据列表
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
   * 票证模块处理印花税用
   * 修改记帐标识
   * 将已经记录在票证帐中的印花税销售记录的记帐标识由0改为1
   * @param xspzhList 印花税销售凭证号列表
   * @param jsjdm 计算机代码
   * @param lx 记帐标识更新类型 0：更新为未记帐， 1：更新为已记帐
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
   * （仅用于处罚模块调用）
   * 根据处罚模块的申报数据，生成缴款书，并返回缴款书信息
   * @param sbsj 申报数据
   * @return DeclareInfor 缴款数据
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
   * 为票证模块提供的根据缴款凭证号获取该凭证入库金额的接口方法
   * @param jkpzh 缴款凭证
   * @return rkje 入库金额
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
   * 为票证模块提供的根据缴款凭证号获取该凭证入库金额的接口方法
   * @param jkpzh 缴款凭证
   * @return rkje 入库金额
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
   * 根据年度字别和完税证号及类型代码，取对应的完税征明细数据
   * @param wszh 完税证号
   * @param nd 年度
   * @param lxdm 类型代码：1－－零散税完税证；2－－个体工商户完税证。
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

//    4.数据源２位，第一位代表系统：１为申报系统2为计会系统３为稽查系统４为处罚系统；第二位代表方式１为录入２为导入：
//    　 1１  代表申报录入
//    　１２  代表申报导入
//    　 13   代表零散汇总
//    　１４  代表个体工商户汇总
//    　１５  代表印花税汇总
//    　１６  代表三代汇总
//       17   代表零散录入
//    　 18   代表个体工商户录入
//      1 9   代表自然人插入
//    　2 0   代表外籍个人插入
//      5 １  代表计会系统录入
//    　6 １  代表稽查系统录入
//    　7 １  代表窗口处罚录入
//    　8 1   纳税评估录入
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

  //完税征数据常量
  public final static int LSSMX = 1;
  public final static int GTGSHMX = 2;

  /**
   * add by Daniel 银行申报缴款接口
   * 根据银行传来的一批申报数据，生成缴款书，并返回缴款书内容
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
   * 根据申报编号获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合
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
   * 根据税票号码获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合
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
   * 根据申报编号获取一票多税的缴款书
   * @param JksList 一票一税缴款书集合,该集合必须保证属于同一笔申报
   * @return 一票多税缴款书集合
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
	 * @desc 根据收入规划核算处要求，重新查询预算级次名称，预算级次显示为中央级、市级、区级
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