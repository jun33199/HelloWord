package com.ttsoft.bjtax.shenbao.constant;

public class ApplicationConstant {

  public static int JKS_YPDS_LINENUM = 14;
  public static boolean DEBUG_FLAG = true;

  public static String[] JKS_YPYS_MX_KEYS = {
      "sklxdm","sklxmc","sbbh","nsrmc", "sphm", "jsjdm", "jkpzh", "yhdm", "yhmc", "zh", "gkzzjgdm",
      "swjgzzjgdm"
      , "zsswjgzzjgdm", "swjgzzjgmc", "zsswjgzzjgmc", "lrr", "lrrq", "sbrq",
      "sklxdm"
      , "ysjcdm", "yskmdm", "szdm","szmc", "xjrq", "szsmdm","szsmmc", "skssksrq", "skssjsrq",
      "sjse","kssl","jsje","sl"};

  public static String[] JKS_YPDS_MX_KEYS = {
      "ysjc", "yskm", "skssksrq", "skssjsrq", "xjrq"};

  public static String[] SBCGHZ_MX_KEYS = {
      "szdm", "szmc", "sjje", "xjrq"};

  public static String ZWBS_YHKK_SFXY_SUCCESS="092";//有申报网上实时扣款成功最后三位

  public static String ZWBS_YHKK_SFXY_STATUS_LOCK="099";//有申报网上实时扣款状态锁定最后三位

  public static String ZWBS_YHKK_SUCCESS="112";//无申报银行扣款成功后三位

  public static String ZWBS_YHKK_LOCK="9";//银行扣款状态锁定，标志已形成税票后三位


  public static String SJLY_YHSB_YHLR="91";//数据来源-银行录入

  public static String FSDM_YHSB_YHLR="7";//方式代码-银行录入

  public static double JE_ERROR_OFFSET=0.5;

  public static String JSJDM_LX_QY = "1";//企业用户
  public static String JSJDM_LX_ZRR = "2";//自然人用户

}