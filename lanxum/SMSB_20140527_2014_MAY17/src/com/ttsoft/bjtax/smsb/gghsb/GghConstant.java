package com.ttsoft.bjtax.smsb.gghsb;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GghConstant {
  public GghConstant() {
  }

  //税款入库方式代码
  public static final String SKRKFSDM_SANFANG = "03"; //三方协议银行缴款
  //征收方式为定额
  public static final String ZSFS_DQDEDL_DE = "01"; //定额
  //纳税人状态
  public static final String NSRZT_ZC = "10"; //正常
  //扣款标志
  public static final String KKBZ_DKK = "00"; //待扣款
  public static final String KKBZ_1_SUCCESS = "10"; //待扣款
  public static final String KKBZ_1_LOSE = "11"; //待扣款
  public static final String KKBZ_2_SUCCESS = "20"; //待扣款
  public static final String KKBZ_2_LOSE = "21"; //待扣款
  public static final String KKBZ_OTHER = "99"; //待扣款
  //传输文件后缀
  public static final String FILE_POSTFIX_DATA = ".txt"; //数据文件
  public static final String FILE_POSTFIX_LOG = ".log"; //日志
  //传输文件前缀
  public static final String FILE_PREFIX_MX = "TM"; //明细数据
  public static final String FILE_PREFIX_HZ = "TZ"; //汇总数据
  public static final String FILE_PREFIX_LS = "LS"; //银行开始取日志
  public static final String FILE_PREFIX_LE = "LE"; //银行取结束日志
  //区县代码(2位)
  public static final String QXDM_SJ = "90"; //市局
  //银行代码
  public static final String YHDM_BJ = "05"; //北京银行
  public static final String YHDM_NX = "16"; //农信社
  //帐务标识(插申报表时用到)
  public static final String ZWBS = "00000000000000000001";

}