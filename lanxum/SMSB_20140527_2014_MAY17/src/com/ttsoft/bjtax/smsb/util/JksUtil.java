/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx_His;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb_His;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsmyfjs;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.KjssjksHelper;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.StringUtil;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 提供缴款书相关的公用方法</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JksUtil
{
  //帐务标识代码(默认值为0000000000)
  public final static String ZWBS = "00000000000000000000";
  //一票一税中每票的税目
  private static final int SPLITNUM_SM = 4;
  //文化事业建设费和外商投资企业土地使用费
  private final static String SZSM_WHSYJSF = "53";
  private final static String SZSM_WSTZQYTDSYF = "76";
  /**
   * 申报编号
   */
  private String sbbh;
  //打印标识
  private int printTag;
  /**
   * 得到缴款凭证号  16位<br>
   * 规则如下:<br>
   * ----计算机代码(8位)＋年（2位）＋月（2位）＋3位顺序号＋1位流水号<br>
   * ----顺序号为当前计算机代码本月的记录数＋1<br>
   * ----流水号为一票多税情况下一张凭证中多个科目的顺序号<br>
   * @param jsjdm 计算机代码
   * @param time 要求查找的时间
   * @return String 不含最后一位流水号的缴款凭证号
   * @throws Exception 操作异常
   */
  public String getJkpzh(String jsjdm, Date time) throws Exception
  {
    String jkpzh = null;
    jkpzh = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getJkpzh(jsjdm);

    return jkpzh;
  }

  /**
   * 通过税种税目代码来确定预算级次<br>
   * 当税种为文化事业建设费或外商投资企业土地使用费时
   * 通过税费接口取得相应的预算级次其他情况为地方级
   * @param jsjdm 计算机代码
   * @param szsmdm 税种税目代码
   * @param rq 日期
   * @return Ysjc 预算级次信息
   * @throws Exception
   */
  public static Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) throws
      Exception
  {
    Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
//    if (szsmdm.substring(0, 2).equals(SZSM_WHSYJSF) ||
//        szsmdm.equals(SZSM_WSTZQYTDSYF)) {
    //调用税费管理接口得到预算级次
    try
    {
      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new com.
          ttsoft.bjtax.sfgl.proxy.ServiceProxy();
      com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
          getYsjcInfo(jsjdm, szsmdm, rq);
      if (sfysjc == null)
      {
        //如果没有得到认定级次则认定为地方级
        ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
      }
      else
      {
        ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
      }

    }
    catch (Exception ex)
    {
//        ex.printStackTrace();
//        ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
      throw ExceptionUtil.getBaseException(ex, "查询预算级次失败!");
    }

//    }
//    else {
//      //其他情况都为地方级
//      ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
//    }
    return ysjc;
  }

  /**
   * 根据银行代码取得银行联网标志
   * @param  yhdm 银行代码
   * @return String 银行联网标志 "T"为联网
   * @throws java.lang.Exception 操作异常
   */
  public String getLwzt(String yhdm) throws Exception
  {
    Connection conn = null;
    String ret = "";
    try
    {
      //得到连接
      conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);
      ResultSet rs = db.querySQL("select lwzt from dmdb.GY_DM_YH where yhdm='" +
                                 yhdm + "'");
      if (rs.next())
      {
        return rs.getString("lwzt");
      }
      return ret;

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "查询银行联网标志失败!");
    }
    finally
    {
      //释放连接
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 生成缴款数据并进行数据库插入操作
   *
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public List getJkData(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      BaseException
  {
    try
    {
      //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
      String lwzt = this.getLwzt(insbjkzb.getYhdm());
      if (lwzt != null && lwzt.equals(CodeConstant.SMSB_LWZT))
      {
        printTag = CodeConstant.PRINT_YPDS_KM;
      }
      else
      {
        this.printTag = CodeConstant.PRINT_YPYS;
      }
      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  }

  /**
   * 生成缴款数据并进行数据库插入操作
   *
   * modified by qianchao 2005.11.2
   *
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public Map getJkDataZhsb(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype) throws
      BaseException
  {
    try
    {
      /*
             //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
             String lwzt = this.getLwzt(insbjkzb.getYhdm());
             if (lwzt != null && lwzt.equals(CodeConstant.SMSB_LWZT))
             {
        printTag = CodeConstant.PRINT_YPDS_KM;
             }
             else
             {
        this.printTag = CodeConstant.PRINT_YPYS;
             }
       */
  //start modifying by qianchao 2005.11.1
      printTag = jkstype;
  //end modifying by qianchao 2005.11.1



      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      HashMap retMap = new HashMap();
      //设置生成的缴款书类型

      //start modifying by qianchao 2005.11.2
      //retMap.put(CodeConstant.ZHSB_JKS_TYPE, lwzt);
      //end modifying by qianchao 2005.11.2

      //设置生成的缴款书列表
      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
      return retMap;
      //return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  }

  /**三代汇总使用
   *
   * 生成缴款数据并进行数据库插入操作
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @param  flag 一票一税CodeConstant.PRINT_YPYS或一票多税CodeConstant.PRINT_YPDS_KM
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public List getJkDataSD(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
      //String lwzt = this.getLwzt(insbjkzb.getYhdm());
      if (flag == CodeConstant.PRINT_YPYS)
      {
        this.printTag = CodeConstant.PRINT_YPYS;
      }
      else
      {
        this.printTag = CodeConstant.PRINT_YPDS_KM;
      }
      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      fillSbjkmxInfo(insbjkzb, mxList);
      //fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  } //End of getJkDataSD

  /**个体工商户/零散户使用
   *
   * 生成缴款数据并进行数据库插入操作
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @param  flag 一票一税CodeConstant.PRINT_YPYS或一票多税CodeConstant.PRINT_YPDS_KM
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public List getJkDataLS(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
      //String lwzt = this.getLwzt(insbjkzb.getYhdm());
      if (flag == CodeConstant.PRINT_YPYS)
      {
        this.printTag = CodeConstant.PRINT_YPYS;
      }
      else
      {
        this.printTag = CodeConstant.PRINT_YPDS_KM;
      }
      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  } //End of getJkDataLS

  /**耕地占用税使用
  *
  * 生成缴款数据并进行数据库插入操作
  * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
  * @param insbjkmxInfo 申报缴款明细List
  * @param  flag 一票一税CodeConstant.PRINT_YPYS或一票多税CodeConstant.PRINT_YPDS_KM
  * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
  * @throws BaseException 异常信息
  */
 public List getJkDataGDZYS(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
     BaseException
 {
   try
   {
     //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
     //String lwzt = this.getLwzt(insbjkzb.getYhdm());
     if (flag == CodeConstant.PRINT_YPYS)
     {
       this.printTag = CodeConstant.PRINT_YPYS;
     }
     else
     {
       this.printTag = CodeConstant.PRINT_YPDS_KM;
     }
     //
     //将HashMap格式的List转换成Sbjkmx格式的List
     List mxList = this.convertMap2Vo(insbjkmxInfo);
     //为该次申报的该税款类型取一个申报编号
     setSbbh(getSbbh(insbjkzb.getJsjdm()));
     //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
     //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
     fillSbjkmxInfo(insbjkzb, mxList);
     //2.按照预算科目拆分明细数据
     List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

     //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
     //并插入数据库按照不同的打印标识返回缴款数据
     List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

     //返回处理
     return jkdataList;
   }
   catch (Exception ex)
   {
     throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
   }
 } //End of getJkDataGDZYS
  



  /**个体工商户/零散户使用
   *
   * 生成缴款数据并进行数据库插入操作
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @param  flag 一票一税CodeConstant.PRINT_YPYS或一票多税CodeConstant.PRINT_YPDS_KM
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public List getJkDataYhs(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
      //String lwzt = this.getLwzt(insbjkzb.getYhdm());
      if (flag == CodeConstant.PRINT_YPYS)
      {
        this.printTag = CodeConstant.PRINT_YPYS;
      }
      else
      {
        this.printTag = CodeConstant.PRINT_YPDS_KM;
      }
      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  } //End of getJkDataLS

  /**自然人个人所得税录入使用
   *
   * 生成缴款数据并进行数据库插入操作
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @param  flag 一票一税CodeConstant.PRINT_YPYS或一票多税CodeConstant.PRINT_YPDS_KM
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public List getJkDataZRR(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //根据银行联网标志设置打印状态，当联网标志为"T"时为联网
      //String lwzt = this.getLwzt(insbjkzb.getYhdm());
      if (flag == CodeConstant.PRINT_YPYS)
      {
        this.printTag = CodeConstant.PRINT_YPYS;
      }
      else
      {
        this.printTag = CodeConstant.PRINT_YPDS_KM;
      }
      //
      //将HashMap格式的List转换成Sbjkmx格式的List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  } //End of getJkDataLS

  /**
   * 填充申报明细List中的预算级次和科目信息
   * @param insbjkzb 缴款主表信息
   * @param insbjkmxInfo 缴款明细信息
   * @throws Exception 操作异常
   */
  private void fillSbjkmxInfo(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      Exception
  {
    Sbjkmx sbjkmxTmp = null;
    String qylxdm = insbjkzb.getDjzclxdm();
    String gjbzhydm = insbjkzb.getGjbzhydm();
    String sklxdm = insbjkzb.getSklxdm();
    for (int i = 0; i < insbjkmxInfo.size(); i++)
    {
      sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
      try
      {
        fillSbjkmx(sbjkmxTmp, insbjkzb);
      }
      catch (Exception ex)
      {
        throw ExceptionUtil.getBaseException(ex, "获取预算科目失败!");
      }

    }
  }

  /**
   * 在缴款明细数据中添加预算级次和预算科目信息
   * @param sbjkmx 操作的缴款明细
   * @param insbjkzb 申报缴款主表
   * @throws Exception 操作异常
   */
  private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception
  {
  	
	  Timestamp tempXjrq=insbjkzb.getXjrq();
  	
    //1.查找预算级次并填充
    //Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(), new Date());
    Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(),
                        sbjkmx.getSkssjsrq());
    sbjkmx.setYsjcdm(ysjc.getYsjcdm());
    sbjkmx.setYsjcmc(ysjc.getYsjcmc());
//System.out.println("+++++++++++++++++++++++"+insbjkzb.getJsjdm()+" : "+sbjkmx.getSzsmdm()+" : "+sbjkmx.getSkssjsrq());
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ysjc.getYsjcdm()+" : "+ysjc.getYsjcmc());
    //2.查找预算科目代码并填充
//    Yskm yskm = getYskm(
//        sbjkmx.getSzsmdm(), insbjkzb.getDjzclxdm(), insbjkzb.getGjbzhydm(),
//        ysjc.getYsjcdm());
    /**Modified by lufeng
         //调用计会接口，获取预算科目*/
    Yskm yskm = JKAdapter.getInstance().getYskm(sbjkmx.getSzsmdm(),
                                                insbjkzb.getDjzclxdm(),
                                                insbjkzb.getGjbzhydm(),
                                                ysjc.getYsjcdm());
    if (yskm != null)
    {
      sbjkmx.setYskmdm(yskm.getYskmdm());
      sbjkmx.setYskmmc(yskm.getYskmmc());

    }
    else
    {
      //sbjkmx.setYskmdm("041400");
      //sbjkmx.setYskmmc("国有核工业所得税");
      throw new ApplicationException("获取预算科目失败！");
      //throw ExceptionUtil.getBaseException(new Exception(), "获取预算科目失败!");
    }

    //3.填写税款所属时期
    //ToDo.print(this.getClass(), "fillSbjkmx",
    //           "税款所属时期的填写方式有待确定,这里为后台填写调用类Skssrq来填写");
//    Map map = Skssrq.getSksssq(sbjkmx.getSzsmdm(), insbjkzb.getSklxdm(),
//                               sbjkmx.getZqlxdm(), insbjkzb.getLrrq());
//    sbjkmx.setSkssksrq( (Timestamp) map.get(Skssrq.SKSSKSRQ));
//    sbjkmx.setSkssjsrq( (Timestamp) map.get(Skssrq.SKSSJSRQ));
    //通过征期日历得到税款所属日期等
    //Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getLrrq());
    /**
     * 根据申报日期得到限缴日期
     */
    Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getSbrq(),
                                       insbjkzb.getDjzclxdm());
    //设置限缴日期=征期截止日期+1天
    if (zqrl != null)
    {
      Timestamp ts = zqrl.getZqzzrq();
      /**
       * 限缴日期=征期截止日期
       * Shi Yanfeng
       * 20031031
       */
      if (zqrl.getZqlxdm() != null && zqrl.getZqlxdm().equals("08"))
      {
        Debug.out("xjrq and szsmdm and zqlxdm equals '08' " + sbjkmx.getSzsmdm());
        //如果该登记注册类型代码、该税目对应的征期类型代码为08即按次申报，则限缴日期应该为当前日期的后一天，
        ts = Timestamp.valueOf(DateUtil.addDatetimeByDay(ts.toString(), 1));
      }
      //ts.setDate(ts.getDate() + 1);
      if (zqrl.getZqzzrq().getTime() < insbjkzb.getSbrq().getTime())
      {
        //1.如果当前申报日期过了征期的结束日期，则限缴日期为当天；
        ts = new Timestamp(System.currentTimeMillis());
      }
      insbjkzb.setXjrq(ts);
    }

    //对于零散录入，个体和零散完税征汇总，限缴日期为当前日期的后一天！
    //Modified by lufeng 2004-03-24
    if (insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR) ||
        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_GTGSHHZ) ||
        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSHZ))
    {
      Timestamp nowTime = new Timestamp(System.currentTimeMillis());
      insbjkzb.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(nowTime.
          toString(), 1)));
    }


    //如果是耕地有税
    if (insbjkzb.getSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
    	insbjkzb.setXjrq(tempXjrq);
    }
    


    //入库金额
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //设置申报编号
    /**Modified by lufeng*/
    if (sbjkmx.getYsjcdm().equals("21"))
    { //如果是地方级
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //设置市局级分成金额
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //设置区县级分成金额
    }
    //by lufeng
    //sbjkmx.setSjfc(new BigDecimal("0.25"));
    //sbjkmx.setQjfc(new BigDecimal("0.35"));
    //设置明细数据的计算机代码
    sbjkmx.setJsjdm(insbjkzb.getJsjdm());
    //设置明细的税务机关组织机构代码
    sbjkmx.setSwjgzzjgdm(insbjkzb.getSwjgzzjgdm());
    //设置明细的年度
    sbjkmx.setNd(String.valueOf(TinyTools.getYear(insbjkzb.getSbrq())));
    //设置明晰的录入日期
    sbjkmx.setLrrq(insbjkzb.getLrrq());
    //设置明细的创建日期
    sbjkmx.setCjrq(insbjkzb.getCjrq());
    //设置明细的区县代码
    sbjkmx.setQxdm(insbjkzb.getQxdm());
  }

  /**
   * 计算分成金额
   * @param je 实缴金额
   * @param bl 分成比例（默认是0。00）
   * @return 分成金额(保留4位小数)，如果实缴金额或分成比例为null，则放回0
   */
  public BigDecimal getFc(BigDecimal je, BigDecimal bl)
  {
    //
    //计会接口没有返回分成比率
    if (je == null || bl == null)
    {
      return new BigDecimal(0);
    }
    BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
    //BigDecimal fc = je.multiply(je).setScale(4, BigDecimal.ROUND_HALF_UP);
    return fc;
  }

  /**
   * 按照预算科目信息拆分sbjkmxInfo为多个List(每个List中的sbjkmx实例有相同预算科目信)
   * 组成的嵌套List
   * @param sbjkmxInfo 申报缴款明细信息(多条sbjkmx实例的List)
   * @return List 成员还是为List
   */
  private List slipSbjkmxInfo(List sbjkmxInfo)
  {
    List splitsbjkmx = new ArrayList();
    List paramList = new ArrayList();

    //按照预算科目代码加税款所属开始和结束来拆分缴款明细数据
    paramList.add("getYskmdm");
    paramList.add("getSkssksrq");
    paramList.add("getSkssjsrq");
    try
    {
      splitsbjkmx = FindObjInList.splitListByParam(sbjkmxInfo, Sbjkmx.class,
          paramList);
    }
    catch (Exception ex)
    {
      //Debug.out("拆分申报数据失败!");
      splitsbjkmx = null;
    }
    return splitsbjkmx;
  }

  /**
   * 依照税务机关组织机构代码查询国库信息
   * @param swjgzzjgdm 税务机关组织机构代码
   * @return swjgzzjg 税务机关组织机构信息
   * @throws java.lang.Exception 操作异常
   */
  public Swjgzzjg queryGkInfor(String swjgzzjgdm) throws Exception
  {
    Connection conn = null;
    try
    {
      //得到连接
      conn = SfDBResource.getConnection();

      Vector criteria = new Vector(); //查询条件
      criteria.add("SWJGZZJGDM = '" + swjgzzjgdm + "'");
      SfDBAccess db = new SfDBAccess(conn);
      List queryresult = db.query(new Swjgzzjg().getClass(), criteria);
      if (queryresult.size() == 0)
      {
        return null;
      }
      else
      {
        return (Swjgzzjg) queryresult.get(0);
      }
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
    }
    finally
    {
      //释放连接
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 按照拆分后的申报缴款明细数据(多个List构成的List,List中的描述同上)创建多个申报主表数据
   * @param insbjkzb 申报主表的一些公共信息
   * @param sbjkmxInfo 拆分后的申报明细数据
   * @return 申报缴款主表数据的List
   * @throws java.lang.Exception 操作中的异常信息
   */
  private List creatjkData(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      Exception
  {
    //得到国库代码和名称填写到对应的主表上
    try
    {
//      Swjgzzjg swjgzzjg = queryGkInfor(insbjkzb.
//                                       getSwjgzzjgdm());
//      if (swjgzzjg != null) {
//        insbjkzb.setGkzzjgdm(swjgzzjg.getGkjhh());
//        insbjkzb.setGkzzjgmc(swjgzzjg.getSkgk());
//
//      }
      insbjkzb.setGkzzjgdm(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "gkjhh"));
      insbjkzb.setGkzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "skgk"));
    }
    catch (Exception ex)
    {
      //取国库出错时不抛异常继续插入数据
      ex.printStackTrace();
    }
    try
    {

      switch (printTag)
      {
        case CodeConstant.PRINT_YPYS: //一票一税
          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
        case CodeConstant.PRINT_YPDS_KM: //一票多税(科目)
          return createSkjkzbInfor_mortax(insbjkzb, sbjkmxInfo);
        default: //默认为一票一税
          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);

      }
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }

  }

  //对按照预算科目拆分后的每组明细生成缴款主表数据并填写缴款凭证号一票一税情况
  //返回生成的缴款书信息
  private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      BaseException
  {
    String sequence = null; //缴款凭证号中的序列号
    //得到当前计算机在申报缴款主表中的最大流水号.
    try
    {
//
      //得到缴款凭证号
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;
      //得到录入日期的年月（4位）
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //循环中的临时变量
      List sbjkzbList = new ArrayList(); //创建的缴款主表List
      List jkInforList = new ArrayList(); //返回的缴款书数据List
      DeclareInfor jkInfor = null; //一条缴款数据
      List splitsbjkmxList = null; //按4个科目税款时期拆分后的多组明细数据list

      Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //按4个科目税款时期拆分后的一组明细数据list
      BigDecimal sjjehe; //实缴金额
      for (int i = 0; i < sbjkmxInfo.size(); i++)
      {
        //超过4条明细拆分
        splitsbjkmxList = TinyTools.splitList(
            (List) sbjkmxInfo.get(i), SPLITNUM_SM);
        //对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
          sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
          //通过税费接口得到缴款凭证号
          jkpzh = sbJkpzh + "0";
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
//                    ToDo.print(this.getClass(), "createSkjkzbInfor_onetax"
//                               , "一票一税情况下,三方联网时填写的处理标记为???");

          sbjkzbtmp =
              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                           CodeConstant.CLBJDM_YSB);
          sbjkzbList.add(sbjkzbtmp);
          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
          jkInfor.setPrintTag(printTag);
          jkInforList.add(jkInfor);
        }
      }
      //把生成的数据插入数据库中
      insertSbjkData(sbjkzbList, sbjkmxInfo);

      return jkInforList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
    }

  }

  //得到明细数据创建一条sbjkzb数据
  private Sbjkzb createSbjkzb(
      List sbjkmxList, Sbjkzb sbjkzb, String jkpzh, String clbjdm)
  {
    Sbjkzb cloneSbjkzb = sbjkzb.getCopy();
    cloneSbjkzb.setJkpzh(jkpzh);
    cloneSbjkzb.setSphm(jkpzh);
    Sbjkmx sbjkmxtmp = null;
    BigDecimal sjjehe = new BigDecimal(0.00);
    for (int j = 0; j < sbjkmxList.size(); j++)
    {
      sbjkmxtmp = (Sbjkmx) sbjkmxList.get(j);
      sbjkmxtmp.setJkpzh(jkpzh);
      if (sbjkmxtmp.getSjse() != null)
      {
        sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); //统计实缴金额的合计
      }
    }
    //设置实缴金额等其他字段数据
    //Debug.out("实缴金额合计:" + sjjehe);
    cloneSbjkzb.setSjje(sjjehe);
    cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
    cloneSbjkzb.setYsjcmc(sbjkmxtmp.getYsjcmc());
    cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
    cloneSbjkzb.setYskmmc(sbjkmxtmp.getYskmmc());
    cloneSbjkzb.setSzdm(sbjkmxtmp.getSzdm());
    cloneSbjkzb.setSzmc(sbjkmxtmp.getSzmc());
    cloneSbjkzb.setRkje(sjjehe);
    cloneSbjkzb.setSbbh(sbbh); //设置申报编号
    if(cloneSbjkzb.getZwbs()==null ||cloneSbjkzb.getZwbs().equals("") ||cloneSbjkzb.getZwbs().length()!=20){
     cloneSbjkzb.setZwbs(ZWBS); //帐务标识，默认20个0
   }

    cloneSbjkzb.setClbjdm(clbjdm); //处理标记代码，默认10
    //添加申报缴款主表税款所属时期  add by wanghw
    cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
    cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
    //添加申报缴款主表年度
    cloneSbjkzb.setNd(String.valueOf(TinyTools.getYear(cloneSbjkzb.getSbrq())));
    //帐页日期等于申报日期
    if (cloneSbjkzb.getSbrq() == null || cloneSbjkzb.getSbrq().equals(""))
    {
      Debug.out("****************" + cloneSbjkzb.getJsjdm() +
                " : 申报日期为空***************");
    }
    cloneSbjkzb.setZyrq(cloneSbjkzb.getSbrq());
    return cloneSbjkzb;
  }

  //对按照预算科目拆分后的每组明细生成缴款主表数据并填写缴款凭证号一票多税情况
  //返回生成的缴款书信息
  private List createSkjkzbInfor_mortax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      BaseException
  {
    String sequence = null; //缴款凭证号中的序列号
    //得到当前计算机在申报缴款主表中的最大流水号.
    try
    {
      //sequence = getSequenceOfJkpzh(insbjkzb.getJsjdm(),
      //                              insbjkzb.getLrrq());
      //得到缴款凭证号
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;

      //得到录入日期的年月（4位）
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //循环中的临时变量
      List sbjkzbList = new ArrayList(); //创建的缴款主表List
      List jkInforList = new ArrayList(); //返回的缴款书数据List(多张票据数据)
      List onePageJkInfor = null; //一张票据数据
      DeclareInfor jkInfor = null; //一条缴款数据
      List splitsbjkmxList = null; //按4个科目税款时期拆分后的多组明细数据list
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //按4个科目税款时期拆分后的一组明细数据list
      BigDecimal sjjehe; //实缴金额

      //按每组4条(或少于4条)不同的科目税款来进行分组
      List eachfourList = TinyTools.splitList(sbjkmxInfo, SPLITNUM_SM);

      for (int i = 0; i < eachfourList.size(); i++)
      {
        //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
        sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
        splitsbjkmxList = (List) eachfourList.get(i);
        onePageJkInfor = new ArrayList();
        //对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          int mark = k + 1;
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; //一票多税的最后一位从1开始

          jkpzh = sbJkpzh + mark; //一票多税的最后一位从1开始
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
          //创建一个新的sbjkzb并填写缴款凭证号

          sbjkzbtmp =
              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                           CodeConstant.CLBJDM_WCL);
          sbjkzbList.add(sbjkzbtmp);
          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);     
          jkInfor.setPrintTag(printTag);
          onePageJkInfor.add(jkInfor);
        }
        jkInforList.add(onePageJkInfor);
      }
      insertSbjkData(sbjkzbList, sbjkmxInfo);

      return jkInforList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
    }
  }

  //add by 2003-09-28 wanghw
  /**
   * modified by qianchao 2005.11.1
   * 申报编号的生成,申报编号的生成规则为：计算机代码加上服务器的当前时间的十八位字符串
   * 以上规则作废，
   * 使用申报的接口生成申报编号。
   *
   * @param jsjdm 计算机代码
   * @return sbbh
   */
  private String getSbbh(String jsjdm) throws BaseException
  {

    String sbbh;
    try
    {
      sbbh = ServiceProxy.getSbbh(jsjdm);
    }
    catch (BaseException ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex,"调用申报生成申报表号失败！");
    }

    /*
         //得到当前时间
         long currentTime = System.currentTimeMillis();
         //参考时间
         long targetTime = Long.parseLong("1064700000000");
         //生成申报编号
         String sbbh = jsjdm + Long.toHexString(currentTime - targetTime);
     */
    return sbbh;
  }

  /**
   * 将HashMap格式的List转换成Sbjkmx格式的List
   * @param insbjkmxInfo 待转换的HashMap格式的List
   * @return List 转换为Sbjkmx格式的List
   * @throws BaseException
   */
  private List convertMap2Vo(List insbjkmxInfo) throws
      BaseException
  {

    List ret = new ArrayList();
    for (int i = 0; i < insbjkmxInfo.size(); i++)
    {
      HashMap map = (HashMap) insbjkmxInfo.get(i);
      Sbjkmx voTemp = new Sbjkmx();
      //主表和明细表合并，使用一个表，所以要将明细数据加入值对象
      try
      {
        BeanUtil.populate(voTemp, map);
        ret.add(i, voTemp);
      }
      catch (Exception ex)
      {
        throw ExceptionUtil.getBaseException(ex, "转换List格式失败");
      }
    }
    return ret;
  }

  /**
   * 把申报主表,申报明细数据插入数据库
   * @param sbjkzbList 申报缴款主表信息List中的值对象为:Sbjkzb
   * @param sbjkmxInfoList 申报明细信息:List中的对象还是List的实例(有相同科目信息的
   * 明细的List):每个subList中的对象才是Sbjkmx
   * @throws BaseException 异常信息
   */
  public void insertSbjkData(List sbjkzbList, List sbjkmxInfoList) throws
      BaseException
  {
    //定义数据库连接
    Connection conn = null;
    //OR实例
    //ORManager orManager = null;
    //循环插入主表和明细数据
    try
    {
      // 获得数据库连接
      //conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);
      // 获得 ORManager
      //orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //插入主表数据
      for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); )
      {
        //orManager.makePersistent(SESSIONID, conn, sbjkzbs.next());
        Sbjkzb temp = (Sbjkzb) sbjkzbs.next();
        //
        db.insert(temp);

      }

      //插入明细数据
      List sbjkmxList = null;
      for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
           sbjkmxLists.hasNext(); )
      {
        //同一科目的明细list
        for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
             sbjkmxs.hasNext(); )
        {
          //同一科目中多条明细
          //orManager.makePersistent(SESSIONID, conn, sbjkmxs.next());
          db.insert((Sbjkmx) sbjkmxs.next());
        }
      }
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 通过sequence 获得申报汇总单号
   * 汇总单号的长的为8位
   * @param con 数据库连接
   * @return 返回汇总单号
   * @throws Exception 操作异常
   */
  public String getSequenceOfSbhzd(Connection con) throws
      Exception
  {
    String sequence = null;
    //数据库连接
    //Connection conn = null;
    try
    {
      // 获得数据库连接
      //conn = SfDBResource.getConnection();
      String sql = "select sbdb.seq_sb_hzdh.nextval from dual";
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      rs.next();
      //汇总单号
      long hzdhindex = rs.getLong("nextval");
      NumberFormat nmbFormat = new DecimalFormat("00000000");
      sequence = nmbFormat.format(hzdhindex);

      return sequence;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "得到汇总单号的最大顺序号失败!");
    }
  } //End of getSequenceOfSbhzd

  /**
   * 通过sequence 获得个体工商户/零散的序号
   * 汇总单号的长的为8位
   * @param con 数据库连接
   * @return 返回汇总单号
   * @throws Exception 操作异常
   */
  public String getSequenceOfWSZXH(Connection con) throws
      Exception
  {
    String sequence = null;
    //数据库连接
    //Connection conn = null;
    try
    {
      // 获得数据库连接
      //conn = SfDBResource.getConnection();
      String sql = "select sbdb.seq_sb_wszxh.nextval from dual";
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      rs.next();
      //汇总单号
      String wszxh = rs.getString("nextval");
      //NumberFormat nmbFormat = new DecimalFormat("00000000");
      //sequence = nmbFormat.format(hzdhindex);
      return wszxh;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "得到序列号失败!");
    }
  } //End of getSequenceOfWSZXH

  /**
   * 撤销缴款书，删除该缴款书的主表和明细表的所有数据，返回相关缴款书的提示信息
   * 主税的或者是附加税的缴款书号
   * @param  jkpzh 缴款凭证号
   * @param  qxdm 区县代码
   * @return List 相关缴款书信息
   * @throws Exception
   */
  public List CxJks(String jkpzh, String qxdm) throws
      Exception
  {
    //相关缴款书提示信息

    Connection conn = null;
    try
    {

      //得到数据库连接
      conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);
      //得到相关缴款书列表
      List ret = this.getCoJks(jkpzh, qxdm, conn);

      Vector v = new Vector();
      String sql = "";

      if (jkpzh.length() == 15)
      {
        //缴款凭证号长度为15时为一票多税
        sql = "jkpzh like '" + jkpzh + "%'";
      }
      else
      {
        //一票一税
        sql = "jkpzh = '" + jkpzh + "'";
      }
      //删除明细表数据
      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where qxdm='" + qxdm +
                   "' and " + sql);
      //删除主表数据
      int nret = db.updateSQL("delete from sbdb.sb_jl_sbjkzb where qxdm='" +
                              qxdm +
                              "' and " + sql + "  AND zwbs like '" +
                              CodeConstant.SMSB_ZWBS + "%" +
                              CodeConstant.SMSB_ZWBS + "'  ");
      if (nret == 0)
      {
        //如果没有可删除数据则回滚
        throw new ApplicationException("撤销缴款书失败！");
      }
      return ret;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * 撤销缴款书，删除该缴款书的主表和明细表的所有数据
   * 主税的或者是附加税的缴款书号
   * @param  sbbh 申报编号
   * @param  qxdm 区县代码
   * @param  jsjdm 计算机代码
   * @param conn 数据库连接
   * @throws Exception
   */
  public void CxAllJks(String sbbh, String qxdm, String jsjdm, Connection conn) throws
      Exception
  {
    //相关缴款书提示信息

    //Connection conn = null;
    try
    {

      //得到数据库连接
      //conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);

      //删除明细表数据
//      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where  qxdm='" + qxdm + "' and jsjdm='"+jsjdm+"' and sbbh='" + sbbh +
//                   "'");
      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where  qxdm='" +
                   qxdm + "' and jsjdm='" + jsjdm + "' and sbbh='" + sbbh +
                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
                   CodeConstant.SMSB_ZWBS + "'  )");
      //删除主表数据
      db.updateSQL("delete from sbdb.sb_jl_sbjkzb where  qxdm='" + qxdm +
                   "' and jsjdm='" + jsjdm + "'  and sbbh='" + sbbh +
                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
                   CodeConstant.SMSB_ZWBS + "'  ");

    }
    catch (SystemException ex)
    {
      throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
    }
    finally
    {
      //SfDBResource.freeConnection(conn);
    }

  }

  /**
   * 得到被撤销缴款书的所有相关缴款书列表
   * @param  jkpzh 缴款凭证号
   * @param  qxdm 区县代码
   * @param conn 数据库连接
   * @return List 相关缴款书提示信息
   * @throws Exception
   */
  public List getCoJks(String jkpzh, String qxdm, Connection conn) throws
      Exception
  {
    List ret = new ArrayList();
    try
    {
      SfDBAccess db = new SfDBAccess(conn);
      Vector v = new Vector();
      v.add("qxdm='" + qxdm + "'");
      if (jkpzh.length() == 15)
      {
        //缴款凭证号长度为15时为一票多税
        v.add("jkpzh like '" + jkpzh + "%'");
      }
      else
      {
        //一票一税
        v.add("jkpzh = '" + jkpzh + "'");
      }
      //得到被撤销缴款书的明细列表
      List mxList = db.query(new Sbjkmx().getClass(), v);
      Map mxMap = new HashMap();
      for (int i = 0; i < mxList.size(); i++)
      {
        Sbjkmx mxVo = (Sbjkmx) mxList.get(i);
        mxMap.put(mxVo.getSzsmdm(), mxVo);
      }
      //得到明细值对象
      Sbjkmx mxVo = (Sbjkmx) mxList.get(0);
      //根据申报编号得到同一次生成的所有缴款书明细数据
      Vector v1 = new Vector();
      v1.add("qxdm='" + qxdm + "'");
      v1.add("jsjdm='" + mxVo.getJsjdm() + "'");
      v1.add(" sbbh = '" + mxVo.getSbbh() + "' order by jkpzh");
      List coMx = db.query(new Sbjkmx().getClass(), v1);
      //得到税种税目和附加税的代码表
      Vector v2 = new Vector();
      List sfList = db.query(new Szsmyfjs().getClass(), v2);

      for (int i = 0; i < coMx.size(); i++)
      {
        //察看是否有相关明细
        Sbjkmx temp = (Sbjkmx) coMx.get(i);
        if (!temp.getJkpzh().substring(0, 15).equals(jkpzh.substring(0, 15)))
        {
          //不是被删除缴款书
          //1.检查是否有相关主税
          for (int ii = 0; ii < sfList.size(); ii++)
          {
            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
            if (temp.getSzsmdm().equals(szsm) && mxMap.get(fjs) != null)
            {
              //存在相关主税，添加到相关列表中
              ret.add(temp);
            }
          }

          //2.检查是否存在相关附加税
          for (int ii = 0; ii < sfList.size(); ii++)
          {
            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
            if (temp.getSzsmdm().equals(fjs) && mxMap.get(szsm) != null)
            {
              //存在相关附加税，添加到相关列表中
              ret.add(temp);
            }
          }
        }

      }
      return ret;
    }
    catch (BaseException ex)
    {
      throw ExceptionUtil.getBaseException(ex, "得到相关缴款书失败!");
    }
  }

  /**
   * 一票多税显示前的整理函数
   * @param detList 数据列表
   * @return ArrayList
   * */
  public static ArrayList getYpdsList(ArrayList detList)
  {
    //设置格式化数字
    DecimalFormat deFormat = new DecimalFormat("#0.00");
    ArrayList detailList = new ArrayList();
    ArrayList tempList = new ArrayList();
    detailList = (ArrayList) detList.clone();
    //空则返回
    if (detList.size() <= 0)
    {
      return detList;
    }

    int intCount = 1;
    while (intCount < detailList.size())
    {
      //实例化
      HashMap tempMap = (HashMap) detailList.get(intCount);
      tempList = (ArrayList) detailList.clone();
      int flag = 0;
      String tempJkpzh = (String) tempMap.get("jkpzh_ypds");
      for (int i = 0; i < intCount; i++)
      {
        HashMap ypdsMap = (HashMap) tempList.get(i);
        if ( (ypdsMap.get("jkpzh_ypds")).equals(tempJkpzh))
        {
          //
          HashMap tMap = (HashMap) detailList.get(intCount - 1);
          tMap.put("sjse",
                   String.valueOf(deFormat.format(StringUtil.getDouble( (String)
              tempMap.get("sjse"), 0.00) +
                                                  StringUtil.getDouble( (String)
              ypdsMap.get("sjse"), 0.00))));
          detailList.remove(intCount);
          flag = 1;
          break;
        }
      } //loop;
      if (flag == 0)
      {
        intCount++;
      }
    } //End of while

    return detailList;
  } //End of getYpdsList

  public String getSbbh()
  {
    return sbbh;
  }

  public void setSbbh(String sbbh)
  {
    this.sbbh = sbbh;
  }
  
  /**
   * 生成缴款数据并进行数据库插入操作
   *
   *
   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
   * @param insbjkmxInfo 申报缴款明细List
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws BaseException 异常信息
   */
  public Map getJkDataKjqysds(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype) throws
      BaseException
  {
    try
    {
      printTag = jkstype;

      //将HashMap格式的List转换成Sbjkmx格式的List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //为该次申报的该税款类型取一个申报编号
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfoKj(insbjkzb, mxList);
      //2.按照预算科目拆分明细数据
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //返回处理
      HashMap retMap = new HashMap();
      //设置生成的缴款书类型

      //start modifying by qianchao 2005.11.2
      //retMap.put(CodeConstant.ZHSB_JKS_TYPE, lwzt);
      //end modifying by qianchao 2005.11.2

      //设置生成的缴款书列表
      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
      return retMap;
      //return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
    }
  }
  /**
   * 填充申报明细List中的预算级次和科目信息
   * @param insbjkzb 缴款主表信息
   * @param insbjkmxInfo 缴款明细信息
   * @throws Exception 操作异常
   */
  private void fillSbjkmxInfoKj(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      Exception
  {
    Sbjkmx sbjkmxTmp = null;
    String qylxdm = insbjkzb.getDjzclxdm();
    String gjbzhydm = insbjkzb.getGjbzhydm();
    String sklxdm = insbjkzb.getSklxdm();
    for (int i = 0; i < insbjkmxInfo.size(); i++)
    {
      sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
      try
      {
        fillSbjkmxKj(sbjkmxTmp, insbjkzb);
      }
      catch (Exception ex)
      {
        throw ExceptionUtil.getBaseException(ex, "获取预算科目失败!");
      }
    }
  }

  /**
   * 在缴款明细数据中添加预算级次和预算科目信息
   * @param sbjkmx 操作的缴款明细
   * @param insbjkzb 申报缴款主表
   * @throws Exception 操作异常
   */
  private void fillSbjkmxKj(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception
  {
    //1.查找预算级次并填充
    //Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(), new Date());
    Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(),
                        sbjkmx.getSkssjsrq());
    sbjkmx.setYsjcdm(ysjc.getYsjcdm());
    sbjkmx.setYsjcmc(ysjc.getYsjcmc());

    sbjkmx.setYskmdm("101043509");
    sbjkmx.setYskmmc("港澳台和外商投资企业所得税");
    
  //调用计会接口，获取预算科目*/
    Yskm yskm = KjssjksHelper.getYskm(sbjkmx.getYskmdm(),insbjkzb.getSbrq());


    //限缴日期=当前日期+6
      Timestamp nowTime = new Timestamp(System.currentTimeMillis());
      insbjkzb.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(nowTime.
          toString(), 6)));

    //入库金额
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //设置申报编号
    
    /**Modified by lufeng*/
    if (sbjkmx.getYsjcdm().equals("21"))
    { //如果是地方级
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //设置市局级分成金额
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //设置区县级分成金额
    }

    //设置明细数据的计算机代码
    sbjkmx.setJsjdm(insbjkzb.getJsjdm());
    //设置明细的税务机关组织机构代码
    sbjkmx.setSwjgzzjgdm(insbjkzb.getSwjgzzjgdm());
    //设置明细的年度
    sbjkmx.setNd(String.valueOf(TinyTools.getYear(insbjkzb.getSbrq())));
    //设置明晰的录入日期
    sbjkmx.setLrrq(insbjkzb.getLrrq());
    //设置明细的创建日期
    sbjkmx.setCjrq(insbjkzb.getCjrq());
    //设置明细的区县代码
    sbjkmx.setQxdm(insbjkzb.getQxdm());
  }
  
	/**
	 * 移动申报缴款书不足一元的数据到his表中，
	 * 
	 * @param insbjkzb
	 * @param insbjkmxInfo
	 * @throws BaseException
	 */
	public void moveSbjkToHis(String jsjdm, List jkpzhList, String zrr) throws BaseException {

		if (jkpzhList == null || jkpzhList.size() == 0) {
			return;
		}

		String zrlxdm = "09";
		if (zrr == null) {
			zrr = "admin";
		}

		String[] sbjkZbNames = { "jkpzh", "sklxdm", "jsjdm", "fsdm", "lsgxdm",
				"yhdm", "yhmc", "zh", "djzclxdm", "swjgzzjgdm", "zsswjgzzjgdm",
				"gjbzhydm", "gkzzjgdm", "yskmdm", "ysjcdm", "szdm", "lrrq",
				"sbrq", "jksj", "xjrq", "clbjdm", "sjje", "zyrq", "rkje",
				"zwbs", "hxrdm", "hxrmc", "lrr", "bz", "hxrq", "sbbh",
				"jydzlxdm", "skssksrq", "skssjsrq", "sjly", "nd", "cjrq",
				"qxdm", "sphm" };

		String[] sbjkmxNames = { "szsmdm", "jkpzh", "jsjdm", "yskmdm",
				"ysjcdm", "kssl", "jsje", "sjse", "skssksrq", "skssjsrq",
				"rkje", "sbbh", "sjfc", "qjfc", "swjgzzjgdm", "nd", "sl",
				"cjrq", "lrrq", "qxdm" };

		// 定义数据库连接
		Connection conn = null;

		try {
			// 获得数据库连接
			conn = SfDBResource.getConnection();
			SfDBAccess db = new SfDBAccess(conn);



			Timestamp now = new Timestamp((new java.util.Date()).getTime());

			for (int i = 0; i < jkpzhList.size(); i++) {
				String jkpzh = (String) jkpzhList.get(i);
				
				Vector v = new Vector();
				v.add("jsjdm='" + jsjdm + "'");
				v.add("jkpzh='" + jkpzh + "'");

				List sbjkmxList = db.query(Sbjkmx.class, v);
				
				for (int j = 0; j < sbjkmxList.size(); j++) {
					Sbjkmx sbjkmx = (Sbjkmx) sbjkmxList.get(j);
					Sbjkmx_His sbjkmxHis = new Sbjkmx_His();
					BeanUtil.copyBeanToBean(sbjkmxNames, sbjkmx, sbjkmxHis);

					sbjkmxHis.setZrlxdm("09");
					sbjkmxHis.setZrrq(now);
					sbjkmxHis.setZrr(zrr);

					db.insert(sbjkmxHis);
					db.delete(sbjkmx);
				}

				List sbjkzbList = db.query(Sbjkzb.class, v);
				if(sbjkzbList.size()>0){
					Sbjkzb sbjkzb = (Sbjkzb)sbjkzbList.get(0); 
					Sbjkzb_His sbjkzbHis = new Sbjkzb_His();
					BeanUtil.copyBeanToBean(sbjkZbNames, sbjkzb, sbjkzbHis);
	
					sbjkzbHis.setZrlxdm(zrlxdm);
					sbjkzbHis.setZrrq(now);
					sbjkzbHis.setZrr(zrr);
	
					db.insert(sbjkzbHis);
					db.delete(sbjkzb);
				}
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "申报缴款转入数据错误!");
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}
  
	
/*--add by lijn 20141014  copy from above-----*/
/*--the functions below is used for saving date into history table when we havn't save it in ordinary table-----*/
/*-- fisrt used in 小微企业营业税减免，小微企业文化建设费减免 --*/	
	
	/**
	   * 生成缴款数据并进行数据库插入操作;插入历史表
	   *
	   * modified by qianchao 2005.11.2
	   *
	   * @param insbjkzb 申报缴款主表(已经基本填写了数据,jkpzh没有)
	   * @param insbjkmxInfo 申报缴款明细List
	   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
	   * @throws BaseException 异常信息
	   */
	  public Map getJkDataZhsb_his(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype,String zrlxdm ,String zrr,String sbbh) throws
	      BaseException
	  {
	    try
	    {
	      
	      printTag = jkstype;
	  
	      //将HashMap格式的List转换成Sbjkmx格式的List
	      List mxList = this.convertMap2Vo(insbjkmxInfo);
	      
	      //为该次申报的该税款类型取一个申报编号
	      setSbbh(sbbh==null||"".equals(sbbh)?getSbbh(insbjkzb.getJsjdm()):sbbh);
	      
	      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
	      fillSbjkmxInfo(insbjkzb, mxList);
	      
	      //2.按照预算科目拆分明细数据
	      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

	      //3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
	      //并插入数据库按照不同的打印标识返回缴款数据
	      List jkdataList = creatjkData_his(insbjkzb, sbjkmxInfoByyskmdm ,zrlxdm ,zrr);

	      //返回处理
	      HashMap retMap = new HashMap();
	      //设置生成的缴款书类型

	      //设置生成的缴款书列表
	      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
	      return retMap;
	      //return jkdataList;
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
	    }
	  }
	
	  /**
	   * 按照拆分后的申报缴款明细数据(多个List构成的List,List中的描述同上)创建多个申报主表数据
	   * @param insbjkzb 申报主表的一些公共信息
	   * @param sbjkmxInfo 拆分后的申报明细数据
	   * @return 申报缴款主表数据的List
	   * @throws java.lang.Exception 操作中的异常信息
	   */
	  private List creatjkData_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
	      Exception
	  {
	    //得到国库代码和名称填写到对应的主表上
	    try
	    {

	      insbjkzb.setGkzzjgdm(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
	          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "gkjhh"));
	      insbjkzb.setGkzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
	          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "skgk"));
	    }
	    catch (Exception ex)
	    {
	      //取国库出错时不抛异常继续插入数据
	      ex.printStackTrace();
	    }
	    try
	    {

	      switch (printTag)
	      {
	        case CodeConstant.PRINT_YPYS: //一票一税
	          return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);
	        case CodeConstant.PRINT_YPDS_KM: //一票多税(科目)
	          return createSkjkzbInfor_mortax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);
	        default: //默认为一票一税
	          return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);

	      }
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与管理员联系!");
	    }

	  }
	
	  /**
	 * @Description: TODO 一税一票缴款
	 * @param insbjkzb
	 * @param sbjkmxInfo
	 * @return
	 * @throws BaseException
	 */
	private List createSkjkzbInfor_onetax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws BaseException
  {
    String sequence = null; //缴款凭证号中的序列号
    //得到当前计算机在申报缴款主表中的最大流水号.
    try
    {
//
      //得到缴款凭证号
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;
      //得到录入日期的年月（4位）
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //循环中的临时变量
      List sbjkzbList = new ArrayList(); //创建的缴款主表List
      List jkInforList = new ArrayList(); //返回的缴款书数据List
      DeclareInfor jkInfor = null; //一条缴款数据
      List splitsbjkmxList = null; //按4个科目税款时期拆分后的多组明细数据list

      Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //按4个科目税款时期拆分后的一组明细数据list
      BigDecimal sjjehe; //实缴金额
      for (int i = 0; i < sbjkmxInfo.size(); i++)
      {
        //超过4条明细拆分
        splitsbjkmxList = TinyTools.splitList(
            (List) sbjkmxInfo.get(i), SPLITNUM_SM);
        //对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
          sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
          //通过税费接口得到缴款凭证号
          jkpzh = sbJkpzh + "0";
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
//                    ToDo.print(this.getClass(), "createSkjkzbInfor_onetax"
//                               , "一票一税情况下,三方联网时填写的处理标记为???");

          sbjkzbtmp =
              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                           CodeConstant.CLBJDM_YSB);
          sbjkzbList.add(sbjkzbtmp);
          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
          jkInfor.setPrintTag(printTag);
          jkInforList.add(jkInfor);
        }
      }
      //把生成的数据插入数据库中
      insertSbjkData_his(sbjkzbList, sbjkmxInfo,zrlxdm,zrr);

      return jkInforList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
    }

  }
	  
	/**
	 * @Description: TODO 一税多票缴款
	 * @param insbjkzb
	 * @param sbjkmxInfo
	 * @return
	 * @throws BaseException
	 */
	private List createSkjkzbInfor_mortax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
    BaseException
{
  String sequence = null; //缴款凭证号中的序列号
  //得到当前计算机在申报缴款主表中的最大流水号.
  try
  {
    //sequence = getSequenceOfJkpzh(insbjkzb.getJsjdm(),
    //                              insbjkzb.getLrrq());
    //得到缴款凭证号
    //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
    String sbJkpzh = null;

    //得到录入日期的年月（4位）
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
    String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
    NumberFormat nmbFormat = new DecimalFormat("000");

    //循环中的临时变量
    List sbjkzbList = new ArrayList(); //创建的缴款主表List
    List jkInforList = new ArrayList(); //返回的缴款书数据List(多张票据数据)
    List onePageJkInfor = null; //一张票据数据
    DeclareInfor jkInfor = null; //一条缴款数据
    List splitsbjkmxList = null; //按4个科目税款时期拆分后的多组明细数据list
    Sbjkzb sbjkzbtmp = null;
    String jkpzh = null;
    List tmpSbjkmxList; //按4个科目税款时期拆分后的一组明细数据list
    BigDecimal sjjehe; //实缴金额

    //按每组4条(或少于4条)不同的科目税款来进行分组
    List eachfourList = TinyTools.splitList(sbjkmxInfo, SPLITNUM_SM);

    for (int i = 0; i < eachfourList.size(); i++)
    {
      //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
      sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
      splitsbjkmxList = (List) eachfourList.get(i);
      onePageJkInfor = new ArrayList();
      //对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
      for (int k = 0; k < splitsbjkmxList.size(); k++)
      {
        int mark = k + 1;
        //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; //一票多税的最后一位从1开始

        jkpzh = sbJkpzh + mark; //一票多税的最后一位从1开始
        tmpSbjkmxList = (List) splitsbjkmxList.get(k);
        //创建一个新的sbjkzb并填写缴款凭证号

        sbjkzbtmp =
            createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                         CodeConstant.CLBJDM_WCL);
        sbjkzbList.add(sbjkzbtmp);
        jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);     
        jkInfor.setPrintTag(printTag);
        onePageJkInfor.add(jkInfor);
      }
      jkInforList.add(onePageJkInfor);
    }
    insertSbjkData_his(sbjkzbList, sbjkmxInfo,zrlxdm,zrr);

    return jkInforList;
  }
  catch (Exception ex)
  {
    throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
  }
} 
	
	/**
	   * 申报数据直接插入历史表，优惠减免时使用
	   * @param sbjkzbList 申报缴款主表信息List中的值对象为:Sbjkzb
	   * @param sbjkmxInfoList 申报明细信息:List中的对象还是List的实例(有相同科目信息的
	   * 明细的List):每个subList中的对象才是Sbjkmx
	   * @throws BaseException 异常信息
	   */
	  public void insertSbjkData_his(List sbjkzbList, List sbjkmxInfoList,String zrlxdm ,String zrr) throws
	      BaseException
	  {
	    //定义数据库连接
	    Connection conn = null;
	    
	    if (zrr == null) {
			zrr = "admin";
		}
	    Timestamp now = new Timestamp((new java.util.Date()).getTime());
	    String[] sbjkZbNames = { "jkpzh", "sklxdm", "jsjdm", "fsdm", "lsgxdm",
				"yhdm", "yhmc", "zh", "djzclxdm", "swjgzzjgdm", "zsswjgzzjgdm",
				"gjbzhydm", "gkzzjgdm", "yskmdm", "ysjcdm", "szdm", "lrrq",
				"sbrq", "jksj", "xjrq", "clbjdm", "sjje", "zyrq", "rkje",
				"zwbs", "hxrdm", "hxrmc", "lrr", "bz", "hxrq", "sbbh",
				"jydzlxdm", "skssksrq", "skssjsrq", "sjly", "nd", "cjrq",
				"qxdm", "sphm" };

		String[] sbjkmxNames = { "szsmdm", "jkpzh", "jsjdm", "yskmdm",
				"ysjcdm", "kssl", "jsje", "sjse", "skssksrq", "skssjsrq",
				"rkje", "sbbh", "sjfc", "qjfc", "swjgzzjgdm", "nd", "sl",
				"cjrq", "lrrq", "qxdm" };
	    
	    //OR实例
	    //ORManager orManager = null;
	    //循环插入主表和明细数据
	    try
	    {
	      // 获得数据库连接
	      //conn = DBResource.getConnection(DBResource.DB_SHENBAO);
	      conn = SfDBResource.getConnection();
	      SfDBAccess db = new SfDBAccess(conn);
	      // 获得 ORManager
	      //orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

	      //插入主表数据_his
	      for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); )
	      {
	        //orManager.makePersistent(SESSIONID, conn, sbjkzbs.next());
	        Sbjkzb temp = (Sbjkzb) sbjkzbs.next();
	        
	        //由sbjkzb转his
	        Sbjkzb_His sbjkzbHis_temp = new Sbjkzb_His();
			BeanUtil.copyBeanToBean(sbjkZbNames, temp, sbjkzbHis_temp);

			sbjkzbHis_temp.setZrlxdm(zrlxdm);
			sbjkzbHis_temp.setZrrq(now);
			sbjkzbHis_temp.setZrr(zrr);
	        db.insert(sbjkzbHis_temp);

	      }

	      //插入明细数据_his
	      for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
	           sbjkmxLists.hasNext(); )
	      {
	        //同一科目的明细list
	        for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
	             sbjkmxs.hasNext(); )
	        {	
	         //将明细数据转为历史数据
	        	
	        	Sbjkmx sbjkmx_temp = (Sbjkmx) sbjkmxs.next();
				Sbjkmx_His sbjkmxHis_temp = new Sbjkmx_His();
				BeanUtil.copyBeanToBean(sbjkmxNames, sbjkmx_temp, sbjkmxHis_temp);

				sbjkmxHis_temp.setZrlxdm(zrlxdm);
				sbjkmxHis_temp.setZrrq(now);
				sbjkmxHis_temp.setZrr(zrr);

	        	
	          //同一科目中多条明细
	          //orManager.makePersistent(SESSIONID, conn, sbjkmxs.next());
	          db.insert(sbjkmxHis_temp);
	        }
	      }
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
	    }
	    finally
	    {
	      SfDBResource.freeConnection(conn);
	    }
	  }
	
	  
	
}
