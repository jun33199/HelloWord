package com.ttsoft.bjtax.shenbao.service.constant;

/**
 * <p>Title: 税库行常量定义</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SkhConstant {

  public static String RESULT_CODE_PREFIX_SUCCESS="00";//接口操作完全成功

  public static String RESULT_CODE_PREFIX_ERROR="99";//接口操作完全失败

  public static String RESULT_CODE_MID_DEFAULT="00";//暂定不区分

  public static String RESULT_CODE_POSTFIX_SUCCESS="0000";//接口操作完全成功

  public static String RESULT_CODE_POSTFIX_ERROR_BIZ="9997";//业务接口操作完全失败

  public static String RESULT_CODE_POSTFIX_ERROR_EJB="9998";//EJB接口操作完全失败

  public static String RESULT_CODE_POSTFIX_ERROR="9999";//接口操作完全失败



  public static String RESULT_CODE_DESCRIPTION_SUCCESS="业务成功";//接口操作完全成功

  public static String RESULT_CODE_DESCRIPTION_ERROR="业务失败";//接口操作完全失败

  public static String RESULT_CODE_DESCRIPTION_ERROR_EJB="系统错误！";//接口操作完全失败

  public static String RESULT_CODE_DESCRIPTION_ERROR_BIZ="业务错误！";//接口操作完全失败



  public static String JSJDM_LX_QY = "1";//企业用户
  public static String JSJDM_LX_ZRR = "2";//自然人用户




}