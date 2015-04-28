package com.ttsoft.bjtax.shenbao.service.processor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.GtgshwszInfo;
import com.ttsoft.bjtax.shenbao.model.client.LsswszInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.service.Sbjkyqwrk;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.TypeChecker;

//Insert  End    Zhou kejing 20040131

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 申报提供的一些公共接口(processor层)</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0 2003-8-22
 */
public class InterFaceProcessor {
  protected static final int SESSION_ID = 0;

  private final static int JKPZH = 0;
  private final static int JKSINFOR = 1;

  //到税务机关征收方式代码
  private final static String FSDM_SM = "1";

  private final static String SBSJZB_JSJDM = "JSJDM";
  private final static String SBSJZB_SJJE = "SJJEHJ";
  private final static String SBSJZB_SKLXDM = "SKLXDM";
  private final static String SBSJZB_BEIZHU = "BEIZHU";
  private final static String SBSJZB_SJLY = "SJLY";
  private final static String SBSJZB_SBFSDM = "SBFSDM";
  private final static String SBSJZB_SBJKMX = "SBJKMX";
  private final static String SBSJZB_YHDM = "YHDM";
  private final static String SBSJZB_YHMC = "YHMC";
  private final static String SBSJZB_ZH = "ZH";
  private final static String SBSJZB_LRR = "LRR";

  private final static String SBSJMX_SJJE = "SJJE";
  private final static String SBSJMX_JSJE = "JSJE";
  private final static String SBSJMX_SL = "SL";
  private final static String SBSJMX_KSSL = "KSSL";
  private final static String SBSJMX_SZSMDM = "SZSMDM";

  /**
   * ADD BY DANIEL
   */
  private final static String SBSJMX_SKSSKSRQ = "SKSSKSRQ";
  private final static String SBSJMX_SKSSJSRQ = "SKSSJSRQ";
  private final static String SBSJZB_XJRQ = "XJRQ";

  /**
   * 得到缴款凭证号  15位
   * (缴款凭证号最终为16位，此处获得的只是前15位，最后一位流水号需要调用者自己添加，
   * 如果是一票一税，则加‘0’；如果是一票多税，则可以是1－4，最大为4。)
   * @param jsjdm 计算机代码
   * @return 缴款凭证号
   * @throws BaseException 操作异常
   * 规则如下:
   * ----计算机代码(8位)＋年（2位）＋月（2位）＋3位顺序号<br>
   * ----顺序号为当前计算机代码本月的记录数＋1<br>
   * ----流水号为一票多税情况下一张凭证中多个科目的顺序号<br>
   */
  public static String getJkpzh(String jsjdm) throws BaseException {
    String jkpzh = null;
    String sequence = null; //3位顺序号
    try {
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(new Date()); //当前年月:yyMM，系统时间
      //得到录入日期的年月（6位）
      SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyyyMM");
      String yyyyMM = simpleDataFormats.format(new Date());
      //得到3位顺序号
      sequence = getXh(jsjdm, yyyyMM);
      //转换顺序号的格式，为3位，若不够，则在左边补0
      //NumberFormat nmbFormat = new DecimalFormat("000");

      //将序号(ASCII)后转换为字符串
      //sequence = nmbFormat.format(TinyTools.asciiToString(Integer.parseInt(sequence) + 1));

      //自增后的ASCII码
      String sequenceAscii = TinyTools.stringToASCII(TinyTools.asciiToString(
          Integer.parseInt(sequence) + 1));
      //格式化为"000"格式
      sequence = TinyTools.xhFormat(
          TinyTools.asciiToString(Integer.parseInt(sequenceAscii)));

      jkpzh = jsjdm + yyMM + sequence;
      return jkpzh;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "取缴款凭证号失败，请与管理员联系！");
    }
  }

  /**
   * 得到当前计算机代码在本月的缴款凭证号中最大的3位顺序号
   * @param jsjdm 计算机代码
   * @return sequence 此计算机代码对应的本月缴款数据中缴款凭证号则最大的3位顺序号如果
   * 本月此计算机用户还没有缴款凭证号则返回‘0’
   * @throws BaseException 操作异常
   */
  public static String getSequenceOfJkpzh(String jsjdm) throws BaseException {
    String sequence = null;
    //返回的缴款书凭证号
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
    String yyMM = simpleDataFormat.format(new Date());
    try {
      //将序号由ASCII码转换为字符串
      sequence = TinyTools.asciiToString(Integer.parseInt(getXh(jsjdm, yyMM)));

      return sequence;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询缴款凭证号的最大顺序号失败!");
    }
  }

  /**
   * 处理并发情况，取数据时，锁定表中对应的行，直到更新结束，解锁
   * @param jsjdm 计算机代码8位
   * @param ny 年月yyyyMM六位
   * @return String xh
   * @throws BaseException
   */
  public static String getXh(String jsjdm, String ny) throws BaseException {
    if (jsjdm == null || jsjdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("计算机代码不能为空！"));
    if (ny == null || ny.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("录入日期不能为空！"));
    System.out.println("*****************************************************");
    System.out.println("InterfaceProcessor.getXh:" + "jsjdm=" + jsjdm +
                       "  and  " + "ny=" + ny);
    System.out.println("*****************************************************");

    Connection conn = null;
    String sequence = "0"; //默认
    int xh = 0; //默认为0
    try {
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT XH FROM SBDB.SB_JL_JKPZHKZ WHERE JSJDM = '")
          .append(jsjdm)
          .append("' AND NY='").append(ny)
          .append("' FOR UPDATE");
      //   System.out.println("InterfaceProcessor.getXh:"+"sqlselect="+sqlStringBuffer.toString());
      //获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        xh = rs.getInt("xh");
      }
      sqlStringBuffer.setLength(0);
      Statement st1 = conn.createStatement();

      //序号自增
      //解析序号
      String xhStr = "";
      if (xh == 0) {
        xhStr = "49"; //从1(ASCII:49)开始
        xh = 48;
      }
      else {
        xhStr = TinyTools.stringToASCII(TinyTools.asciiToString(xh + 1));
      }

      sqlStringBuffer.append("UPDATE SBDB.SB_JL_JKPZHKZ SET XH='")
          .append(xhStr).append("', NY='").append(ny)
          .append("' WHERE JSJDM = '").append(jsjdm).append("'");
      //     System.out.println("InterfaceProcessor.getXh:"+"sqlupdate="+sqlStringBuffer.toString());
      int i = st1.executeUpdate(sqlStringBuffer.toString());
      if (i == 0) {
        //如果没有序号，初始化一个序号'1'，但是存储的为ASCII码'49'
        sqlStringBuffer.setLength(0);
        Statement stInsert = conn.createStatement();
        sqlStringBuffer.append(
            "INSERT INTO SBDB.SB_JL_JKPZHKZ (JSJDM, XH, NY) VALUES('")
            .append(jsjdm).append("','49','").append(ny).append("')");
        //            System.out.println("InterfaceProcessor.getXh:"+"sqlinsert="+sqlStringBuffer.toString());
        stInsert.addBatch(sqlStringBuffer.toString());
        stInsert.executeBatch();
      }
      sequence = String.valueOf(xh); //当前可用号码是xh+1，此处只返回已用的最大序号
      System.out.println("----------------------------------------------------");
      System.out.println("XH sequence == " + sequence + "  jsjdm== " + jsjdm);
      System.out.println("----------------------------------------------------" +
                         sequence);
      return sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "查询该计算机代码对应的序号失败!");
    }
    finally {
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 通过缴款凭证号得到入库的缴款数据(DeclareInfo:sbjkzb和sbjkmxList)  （目前仅限于一票一税的缴款书）
   * @param jkpzh 缴款凭证号字符数组
   * @return 缴款凭证号对应的缴款数据列表
   * @throws BaseException 操作异常
   */
  public static List getJkDataByJkpzh(String[] jkpzh) throws BaseException {
    //参数检查
    if (jkpzh.length == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("输入的缴款凭证号为空！"));
    }
    ArrayList dataList = new ArrayList(); //定义数据列表
    ArrayList zbResult = new ArrayList(); //定义主表查询结果集
    ArrayList mxResult = new ArrayList(); //定义明细查询结果集
    //定义数据库连接
    Connection conn = null;
    //OR实例
    ORManager orManager = null;
    try {
      //查找申报缴款主表并填充declareInfo
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(jkpzh[0]);
      for (int i = 1; i < jkpzh.length; i++) {
        sqlStrBuf.append("','").append(jkpzh[i]);
      }
      sqlStrBuf.append("') AND SUBSTR(ZWBS,2,1)='1') ORDER BY JKPZH");
      String sqlString = sqlStrBuf.toString(); //将缴款凭证号列表中的所有记录都查询出来

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //主表查询结果
      if (zbResult.size() == 0) {
        return dataList; //没有满足条件的记录，返回一个空的ArrayList
      }

      //取明细数据
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //取第一条缴款数据
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //取第一条缴款数据
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //查询明细数据并填充declareInfo和主表相同的缴款凭证号
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //如果明细数据少于主表数据，则数据不会匹配
        throw ExceptionUtil.getBaseException(
            new Exception("缴款数据不匹配，请与数据管理员联系！"), "");
      }
      //将主子表数据按照DeclareInfo对象进行封装
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //返回缴款凭证结果集
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败，未得到缴款数据！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 通过税票号码得到入库的缴款数据(DeclareInfo:sbjkzb和sbjkmxList)
   * @param jkpzh 缴款凭证号字符数组
   * @return 缴款凭证号对应的缴款数据列表
   * @throws BaseException 操作异常
   */
  public static List getJkDataBySphm(String[] sphm) throws BaseException {
    //参数检查
    if (sphm.length == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("输入的缴款凭证号为空！"));
    }
    ArrayList dataList = new ArrayList(); //定义数据列表
    ArrayList zbResult = new ArrayList(); //定义主表查询结果集
    ArrayList mxResult = new ArrayList(); //定义明细查询结果集
    //定义数据库连接
    Connection conn = null;
    //OR实例
    ORManager orManager = null;
    try {
      //查找申报缴款主表并填充declareInfo
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(SPHM IN ('");
      sqlStrBuf.append(sphm[0]);
      for (int i = 1; i < sphm.length; i++) {
        sqlStrBuf.append("','").append(sphm[i]);
      }
      sqlStrBuf.append("') AND SUBSTR(ZWBS,2,1)='1') ORDER BY JKPZH");
      String sqlString = sqlStrBuf.toString(); //将缴款凭证号列表中的所有记录都查询出来

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //主表查询结果
      if (zbResult.size() == 0) {
        return dataList; //没有满足条件的记录，返回一个空的ArrayList
      }

      //取明细数据
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //取第一条缴款数据
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //取第一条缴款数据
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //查询明细数据并填充declareInfo和主表相同的缴款凭证号
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //如果明细数据少于主表数据，则数据不会匹配
        throw ExceptionUtil.getBaseException(
            new Exception("缴款数据不匹配，请与数据管理员联系！"), "");
      }
      //将主子表数据按照DeclareInfo对象进行封装
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //返回缴款凭证结果集
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败，未得到缴款数据！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 根据输入条件查询缴款数据(DeclareInfo:sbjkzb和sbjkmxList)
   *  （目前仅限于一票一税的缴款书）
   * @param jsjdm 纳税人计算机代码
   * @param jkpzh 缴款凭证号字符数组
   * @param sjly 数据来源
   * @param fsdm 方式代码
   * @param zwbs 帐务标识判断
   * @return 缴款凭证号对应的缴款数据列表
   * @throws BaseException 操作异常
   */
  public static List searchJkData(String jsjdm, String[] jkpzh, String sjly,
                                  String fsdm, int zwbs) throws BaseException {
    //参数检查
    if ( (jkpzh == null || jkpzh.length == 0) &&
        (jsjdm == null || jsjdm.length() <= 0)) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("输入的缴款凭证号和计算机代码不能同时为空！"));
    }
    ArrayList dataList = new ArrayList(); //定义数据列表
    ArrayList zbResult = new ArrayList(); //定义主表查询结果集
    ArrayList mxResult = new ArrayList(); //定义明细查询结果集
    //定义数据库连接
    Connection conn = null;
    //OR实例
    ORManager orManager = null;

    String theZwbs = null;
    if (zwbs == 0) {
      theZwbs = "00000000000000000000";
    }
    else if (zwbs == 1) {
      theZwbs = "01000000000000000000";
    }

    try {
      //查找申报缴款主表并填充declareInfo
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(1=1 ");

      if (jkpzh != null && jkpzh.length > 0) {
        sqlStrBuf.append(" AND JKPZH IN ('");
        sqlStrBuf.append(jkpzh[0]);
        for (int i = 1; i < jkpzh.length; i++) {
          sqlStrBuf.append("','").append(jkpzh[i]);
        }
        sqlStrBuf.append("')");
      }

      if (jsjdm != null && jsjdm.length() > 0)
        sqlStrBuf.append(" AND JSJDM='" + jsjdm + "' ");

      if (sjly != null && sjly.length() > 0)
        sqlStrBuf.append(" AND SJLY='" + sjly + "' ");

      if (fsdm != null && fsdm.length() > 0)
        sqlStrBuf.append(" AND FSDM='" + fsdm + "' ");

      if (theZwbs != null)
        sqlStrBuf.append(" AND ZWBS='" + theZwbs + "' ");

      sqlStrBuf.append(" ) ORDER BY JKPZH");

      String sqlString = sqlStrBuf.toString(); //将缴款凭证号列表中的所有记录都查询出来

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //主表查询结果
      if (zbResult.size() == 0) {
        return dataList; //没有满足条件的记录，返回一个空的ArrayList
      }

      //取明细数据
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //取第一条缴款数据
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //取第一条缴款数据
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //查询明细数据并填充declareInfo和主表相同的缴款凭证号
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //如果明细数据少于主表数据，则数据不会匹配
        throw ExceptionUtil.getBaseException(
            new Exception("缴款数据不匹配，请与数据管理员联系！"), "");
      }
      //将主子表数据按照DeclareInfo对象进行封装
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //返回缴款凭证结果集
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败，未得到缴款数据！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 将缴款主表和明细数据，按照缴款凭证，封装为DeclareInfor对象，并封装到list中返回
   * @param zb 主表数据
   * @param mx 明细数据
   * @param conn 数据库连接
   * @throws BaseException 异常操作
   * @return List DeclareInfor对象数组
   */
  private static ArrayList packList(ArrayList zb, ArrayList mx, Connection conn) throws
      BaseException {
    ArrayList dataAr = new ArrayList(); //定义返回数组
    ArrayList groupAr = new ArrayList(); //分组后的明细数据列表（嵌套列表）
    int zbSize = zb.size();
    int mxSize = mx.size();
    String oldJkpzh = ( (Sbjkmx) mx.get(0)).getJkpzh();
    ArrayList tempAr = new ArrayList();
    //明细数据按照缴款凭证号排序，可顺序处理，将同一缴款凭证号的数据封装到一个list中
    for (int i = 0; i < mxSize; i++) {
      Sbjkmx mxObj = (Sbjkmx) mx.get(i);
      String jkpzh = mxObj.getJkpzh();
      if (!jkpzh.equals(oldJkpzh)) {
        ArrayList tmpList = (ArrayList) tempAr.clone();
        groupAr.add(tmpList);
        tempAr.clear();
        tempAr.add(mxObj);
      }
      else
        tempAr.add(mxObj);
    }
    //封装最后一组记录
    ArrayList tmpList = (ArrayList) tempAr.clone();
    groupAr.add(tmpList);
    tempAr.clear();
    //按照缴款凭证对象，对主子表数据进行封装
    //说明：所有数据都是按照缴款凭证号排序，所以，主表数据和分组后的明细数据，相同缴款凭证号的数据，位置相同
    if (zbSize != groupAr.size())
      throw ExceptionUtil.getBaseException(
          new Exception("缴款数据不匹配，请与数据库管理员联系！"), "");
    for (int j = 0; j < zbSize; j++) {
      //主表数据(名称)
      Sbjkzb tempZb = (Sbjkzb) zb.get(j);
      String jsjdm = tempZb.getJsjdm();
      SWDJJBSJ tempJbsj = null;
      ZRRJBSJ zrrjbsj = null;
      String key = (String) TypeChecker.isQyyh(jsjdm);
      ServiceProxy serviceProxy = new ServiceProxy();
      //key=1 表示企业用户；key=2 表示自然人用户
      if (key.equals("1")) {
        tempJbsj = (SWDJJBSJ) serviceProxy.getDjInfo(jsjdm).get("JBSJ");
        if (tempJbsj != null) {
          tempZb.setNsrmc(tempJbsj.getNsrmc()); //纳税人名称
          tempZb.setSwjgzzjgmc(tempJbsj.getSwjgzzjgmc()); //税务机关
          tempZb.setLsgxmc(tempJbsj.getLsgxmc()); //隶属关系
          tempZb.setDjzclxmc(tempJbsj.getDjzclxmc()); //登记注册类型
          tempZb.setGjbzhymc(tempJbsj.getGjbzhymc()); //行业
          tempZb.setZsswjgzzjgmc(tempJbsj.getSwjgzzjgmc()); //征收机关
        }
      }
      else if (key.equals("2")) {
        Map tempMap = serviceProxy.getZrrDjInfo(jsjdm);
        zrrjbsj = (ZRRJBSJ) tempMap.get(DjOuterConstant.ZRRJBSJ);
        if (zrrjbsj != null) {
          tempZb.setNsrmc(zrrjbsj.getNsrmc()); //纳税人名称
          String swjgzzjgdm = zrrjbsj.getSwjgzzjgdm();
          String swjgmc = (String) getSwjgmc(swjgzzjgdm, conn);
          tempZb.setSwjgzzjgmc(swjgmc); //税务机关
          tempZb.setZsswjgzzjgmc(swjgmc); //征收机关
        }
      }
      else {
        throw ExceptionUtil.getBaseException(new Exception(key),
                                             "未知类型的计算机代码！");
      }

      String szmc = addSzsmMc(tempZb.getSzdm(), conn); //税种名称
      tempZb.setSzmc(szmc);
      String yskmmc = addYskmMc(tempZb.getYskmdm(), tempZb.getLrrq(), conn); //预算科目名称
      tempZb.setYskmmc(yskmmc);
      String sklxmc = addSklxMc(tempZb.getSklxdm(), conn); //税款类型名称
      tempZb.setSklxmc(sklxmc);
      String ysjcmc = addYsjcMc(tempZb.getYsjcdm(), conn); //预算级次
      tempZb.setYsjcmc(ysjcmc);
      String gkmc = addGkMc(tempZb.getSwjgzzjgdm(), conn); //国库
      tempZb.setGkzzjgmc(gkmc);

      //明细数据
      ArrayList tempList = new ArrayList();
      List mxList = (List) groupAr.get(j);
      Sbjkmx tempMx = null;
      int tmpSize = mxList.size();
      for (int i = 0; i < tmpSize; i++) {
        tempMx = (Sbjkmx) mxList.get(i);
        tempMx.setYsjcmc(ysjcmc);
        tempMx.setYskmmc(yskmmc);
        tempMx.setSzsmmc(addSzsmMc(tempMx.getSzsmdm(), conn));
        tempList.add(tempMx);
      }
      ArrayList tmpAr = (ArrayList) tempList.clone(); //copy
      DeclareInfor declare = new DeclareInfor(tempZb, tmpAr);
      dataAr.add(declare);
    }
    return dataAr;
  }

  public static DeclareInfor getJkInforBySbsj(Sbsj sbsj, String yhdm,
                                              String yhmc, String zh) throws
      BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("没有得到该计算机代码的登记数据！"));
    }

    //判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJWITHYH,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJWITHYH, swdjjbsj)) {
      // 非正常户
      throw ExceptionUtil.getBaseException(
          new ApplicationException("该纳税人状态不为正常，不能生成缴款书！"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关
    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //创建日期
    sbjkmx.setLrrq(now); //录入日期
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkmxList.add(sbjkmx);

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //征收方式为上门
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //申报日期
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //帐页日期，初始为申报日期
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //银行代码
      sbjkzb.setYhmc(yhmc); //银行名称
      sbjkzb.setZh(zh); //银行账号
    }
    else {
      List tempList = (List) map.get("YHZH");

      YHZH yhzh = (YHZH) tempList.get(0);
      sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
      sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
      sbjkzb.setZh(yhzh.getZh()); //银行账号
      for (int i = 0; i < tempList.size(); i++) {
        yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
          sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
          sbjkzb.setZh(yhzh.getZh()); //银行账号
          break;
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //默认为‘已申报’

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //默认为一票一税

    //把生成的数据插入数据表中并返回
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbsj.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //只有一张票，因为，只有一条缴款明细记录
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //封装后的数据返回
  }

  /**转换其他子系统过来的申报数据为申报缴款主表和申报缴款明细数据
   * @param sbsj 其他模块的申报数据
   * @return DeclareInfor 生成好的申报缴款数据
   * @throws BaseException 异常操作
   */
  public static DeclareInfor getJkInforBySbsj(Sbsj sbsj) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("没有得到该计算机代码的登记数据！"));
    }

    //判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // 非正常户
      throw ExceptionUtil.getBaseException(
          new ApplicationException("该纳税人状态不为正常，不能生成缴款书！"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关
    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //创建日期
    sbjkmx.setLrrq(now); //录入日期
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkmxList.add(sbjkmx);

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //征收方式为上门
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //申报日期
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //帐页日期，初始为申报日期
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());

    List tempList = (List) map.get("YHZH");

    if (tempList != null && tempList.size() != 0) {
      YHZH yhzh = (YHZH) tempList.get(0);
      sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
      sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
      sbjkzb.setZh(yhzh.getZh()); //银行账号
      for (int i = 0; i < tempList.size(); i++) {
        yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
          sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
          sbjkzb.setZh(yhzh.getZh()); //银行账号
          break;
        }
      }
    }

    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //默认为‘已申报’

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //默认为一票一税

    //把生成的数据插入数据表中并返回
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbsj.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //只有一张票，因为，只有一条缴款明细记录
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //封装后的数据返回
  }

  /**转换其他子系统过来的一批申报数据为申报缴款主表和申报缴款明细数据
   * sbsjList中取第一项生成缴款主表数据，数据验证也只是第一项
   * @param sbsj 其他模块的申报数据
   * @return DeclareInfor 生成好的申报缴款数据
   * @throws BaseException 异常操作
   */
  public static List getJkInforListBySbsj(Map sbsjMap) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    if (sbsjMap == null || sbsjMap.size() < 6) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("提供的数据不足以生成缴款书！"));
    }

    String jsjdm = (String) sbsjMap.get(SBSJZB_JSJDM);
    if (jsjdm == null || jsjdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("计算机代码不能为空！"));
    }
    String sklxdm = (String) sbsjMap.get(SBSJZB_SKLXDM);
    if (sklxdm == null || sklxdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("税款类型代码不能为空！"));
    }
    String sjly = (String) sbsjMap.get(SBSJZB_SJLY);
    if (sjly == null || sjly.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("数据来源不能为空！"));
    }
    String sbfsdm = (String) sbsjMap.get(SBSJZB_SBFSDM);
    if (sbfsdm == null || sbfsdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("申报方式代码不能为空！"));
    }
    String bz = (String) sbsjMap.get(SBSJZB_BEIZHU);
    String yhdm = (String) sbsjMap.get(SBSJZB_YHDM);
    String yhmc = (String) sbsjMap.get(SBSJZB_YHMC);
    String zh = (String) sbsjMap.get(SBSJZB_ZH);

    BigDecimal sjjehj = (BigDecimal) sbsjMap.get(SBSJZB_SJJE);
    if (sjjehj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("实缴金额合计不能为空！"));
    }
    List mxList = (List) sbsjMap.get(SBSJZB_SBJKMX);
    if (mxList == null || mxList.size() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("申报缴款明细至少有一条！"));
    }

    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(jsjdm);
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("没有得到该计算机代码的登记数据！"));
    }

    //判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // 非正常户
      throw ExceptionUtil.getBaseException(
          new ApplicationException("该纳税人状态不为正常，不能生成缴款书！"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关
    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    for (int i = 0; i < mxList.size(); i++) {
      Map mxMap = (Map) mxList.get(i);
      String szsmdm = (String) mxMap.get(SBSJMX_SZSMDM);
      if (szsmdm == null || szsmdm.length() <= 0) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("税种税目代码不能为空！"));
      }
      BigDecimal sjje = (BigDecimal) mxMap.get(SBSJMX_SJJE);
      BigDecimal jsje = (BigDecimal) mxMap.get(SBSJMX_JSJE);
      BigDecimal kssl = (BigDecimal) mxMap.get(SBSJMX_KSSL);
      BigDecimal sl = (BigDecimal) mxMap.get(SBSJMX_SL);
      if (sjje == null || jsje == null || kssl == null || sl == null) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("金额不能为空！"));
      }
	   //税款所属开始日期 
      java.sql.Timestamp skssksrq=mxMap.get(SBSJMX_SKSSKSRQ)==null?null:(java.sql.Timestamp) mxMap.get(SBSJMX_SKSSKSRQ);
      //税款所属结束日期
      java.sql.Timestamp skssjsrq=mxMap.get(SBSJMX_SKSSJSRQ)==null?null:(java.sql.Timestamp) mxMap.get(SBSJMX_SKSSJSRQ);

      Szsm szsm = getSzsm(szsmdm);
      Sbjkmx sbjkmx = new Sbjkmx();
      sbjkmx.setSzsmdm(szsmdm);
      sbjkmx.setZqlxdm(szsm.getZqlxdm()); // 征期类型代码
      sbjkmx.setJsjdm(jsjdm);
      sbjkmx.setSjse(sjje);
      sbjkmx.setRkje(sjje);
      sbjkmx.setKssl(kssl);
      sbjkmx.setSl(sl);
      sbjkmx.setJsje(jsje);
      sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
      sbjkmx.setNd(nd);
      sbjkmx.setCjrq(now); //创建日期
      sbjkmx.setLrrq(now); //录入日期
      sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
	  
	  sbjkmx.setSkssksrq(skssksrq);
      sbjkmx.setSkssjsrq(skssjsrq);

      sbjkmxList.add(sbjkmx);
    }

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(jsjdm);
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(sbfsdm); //征收方式为上门
    sbjkzb.setLrr(jsjdm);
    sbjkzb.setRkje(sjjehj);
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //申报日期
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //帐页日期，初始为申报日期
    sbjkzb.setSjje(sjjehj);
    sbjkzb.setSklxdm(sklxdm);
    sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
    sbjkzb.setNd(nd);
    sbjkzb.setBz(bz);
    sbjkzb.setSjly(sjly); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //默认为‘已申报’
    sbjkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 联系电话

    List tempList = (List) map.get("YHZH");

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //银行代码
      sbjkzb.setYhmc(yhmc); //银行名称
      sbjkzb.setZh(zh); //银行账号
    }
    else {
      if (tempList != null && tempList.size() != 0) {
        YHZH yhzh = (YHZH) tempList.get(0);
        sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
        sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
        sbjkzb.setZh(yhzh.getZh()); //银行账号
        for (int i = 0; i < tempList.size(); i++) {
          yhzh = (YHZH) tempList.get(i);
          if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
            sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
            sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
            sbjkzb.setZh(yhzh.getZh()); //银行账号
            break;
          }
        }
      }
    }

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //默认为一票一税

    //把生成的数据插入数据表中并返回
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    return dataList; //封装后的数据返回
  }

  /**
   * 查询给定的缴款凭证号list中的所有缴款凭证号是否已经入库
   * @param jkpzhList 缴款凭证号的list
   * @param conn 数据库连接
   * @param bs 类型标识
   * @return 只要有一个缴款凭证号没有入库就返回FALSE,都入库返回TRUE
   * @throws BaseException 操作异常
   */
  protected static boolean isJkpzhInStore(List jkpzhList, Connection conn,
                                          int bs) throws
      BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("要查询的缴款凭证号不能为空！"));
    }
    //OR实例
    ORManager orManager = null;
    try {
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      //查询缴款凭证号的处理信息
      Vector criteria = null;
      String jkpzh = null;
      ORContext orCtx = null;
      Sbjkzb sbjkzb = null;
      for (int i = 0; i < jkpzhList.size(); i++) {
        criteria = new Vector();
        jkpzh = (String) jkpzhList.get(i);
        criteria.add("SPHM = '" + jkpzh + "'"); //查找条件:缴款凭证号相同
        orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
        List queryresult = (ArrayList) orManager.query(1, conn, orCtx);
        if (queryresult.size() == 0) {
          if (bs == 0) //返回异常
            throw ExceptionUtil.getBaseException(
                new ApplicationException("缴款凭证号(" + jkpzh + ")不存在！"));
          else if (bs == 1) //返回false
            return false;
        }
        sbjkzb = (Sbjkzb) queryresult.get(0);
        if (sbjkzb.getZwbs().substring(1, 2).equals("0")) {
          //如果帐务标识第二位仍然为“0”，则该缴款数据未入库
          return false;
        }
      }
      return true;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询缴款凭证号失败!");
    }
  }

  /**
   * 按指定的汇总单号查找多条缴款凭证号(在零散税的完税证汇总表中)
   * @param hzdhList 汇总单号List
   * @param resultType 返回的类型(0=只是返回缴款凭证号的list;1=返回缴款凭证号和实缴金额)
   * @param conn 数据库连接
   * @return 缴款凭证号List
   * @throws BaseException 操作异常
   */
//    private static ArrayList findJkDataInLswszsbhz(List hzdhList,
//                                                   int resultType,
//                                                   Connection conn)
//        throws BaseException
//    {
//        if(hzdhList == null || hzdhList.size() == 0)
//        {
//            throw ExceptionUtil.getBaseException(
//                new ApplicationException("请指定要查找的汇总单号！"));
//        }
//        ArrayList findResultList = new ArrayList();
//        //OR实例
//        ORManager orManager = null;
//        try
//        {
//            // 获得 ORManager
//            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
//
//            //查询完税证汇总表得到对应的缴款凭证号信息
//            //循环的一些变量
//            String hzdh = null; //汇总单号
//            Vector criteria = new Vector(); //查询条件
//            ORContext orCtx = null; //查询的ORContext对象
//            List queryresult = null; //查询额结果
//            String jkpzh = null; //缴款凭证号
//            BigDecimal je = null; //实缴金额
//            JksInfor jksInfor = null; //缴款书信息(包含实缴金额)
//            for(int i = 0; i < hzdhList.size(); i++)
//            {
//                hzdh = (String)hzdhList.get(i);
//                criteria.clear();
//                criteria.add("SBHZDH = '" + hzdh + "'");
//                orCtx = new ORContext(Lswszsbhz.class.getName(), criteria);
//                queryresult = (ArrayList)orManager.query(SESSION_ID, conn,
//                    orCtx);
//                //填充找到的缴款凭证号
//                for(int j = 0; j < queryresult.size(); j++)
//                {
//                    if(resultType == JKPZH)
//                    {
//                        jkpzh = ( (Lswszsbhz)queryresult.get(j)).getJkpzh();
//                        findResultList.add(jkpzh);
//                    }
//                    else
//                    {
//                        jkpzh = ( (Lswszsbhz)queryresult.get(j)).getJkpzh();
//                        je = ( (Lswszsbhz)queryresult.get(j)).getSjse();
//                        jksInfor = new JksInfor(jkpzh, je);
//                        findResultList.add(jksInfor);
//                    }
//                }
//            }
//            return findResultList;
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            throw ExceptionUtil.getBaseException(ex, "在零散税汇总表中查询对应的缴款凭证号失败!");
//        }
//    }

  /**
   * 删除缴款凭证号对应的缴款数据,
   * @param jkpzhList 缴款凭证号List
   * @throws BaseException 操作异常
   */
  public static void deleteJKS(List jkpzhList) throws BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("参数不合法，请提交正确的缴款凭证号！"));
    }

    Connection conn = null;
    ORManager orManager = null;
    try {
      //得到连接和orManager对象
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      //调用删除缴款数据的方法
      delJKS(jkpzhList, conn);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "删除缴款凭证号对应的缴款数据失败!");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 删除缴款凭证号对应的缴款数据,
   * @param jkpzhList 缴款凭证号List
   * @param conn 数据库连接
   * @throws BaseException 操作异常
   */
  public static void delJKS(List jkpzhList, Connection conn) throws
      BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("参数不合法，请提交正确的缴款凭证号！"));
    }
    if (conn == null) {
      throw ExceptionUtil.getBaseException(new Exception(), "数据库连接错误！");
    }
    ORManager orManager = null;
    try {
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = null; //查询条件
      ORContext orCtx = null; //查询的ORContext对象
      //删除明细和主表数据
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("JKPZH IN ('" + jkpzhList.get(0) + "'");
      for (int i = 1; i < jkpzhList.size(); i++) {
        sqlBuffer.append(",'" + jkpzhList.get(i) + "'");
      }
      //记账标识的关系
      sqlBuffer.append(
          ") AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //删除条件

      String sqlZb = wheresql; //主表条件
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";
      //删除明细
      orManager.deleteObject(SESSION_ID, conn, sqlMx, new Sbjkmx());
      //删除主表
      orManager.deleteObject(SESSION_ID, conn, sqlZb, new Sbjkzb());
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "删除缴款凭证号对应的缴款数据失败!");
    }
  }

  /**
   * 获取申报缴款逾期未入库数据
   * @param jsjdm 计算机代码
   * @param time 时间
   * @return  List
   * @throws BaseException
   */
  public static List getSbjkyqwrkData(String jsjdm, Date time) throws
      BaseException {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    SWDJJBSJ swdjjbsj = null;
    ZRRJBSJ zrrjbsj = null;
    String swjgzzjgdm = null;
    try {
      String key = (String) TypeChecker.isQyyh(jsjdm);
      ServiceProxy serviceProxy = new ServiceProxy();
      //key=1 表示企业用户；key=2 表示自然人用户
      if (key.equals("1")) {
        swdjjbsj = (SWDJJBSJ) serviceProxy.getDjInfo(jsjdm).get("JBSJ");
        if (swdjjbsj != null) {
          swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关组织机构代码
        }
      }
      else if (key.equals("2")) {
        Map tempMap = serviceProxy.getZrrDjInfo(jsjdm);
        zrrjbsj = (ZRRJBSJ) tempMap.get(DjOuterConstant.ZRRJBSJ);
        if (zrrjbsj != null)
          swjgzzjgdm = zrrjbsj.getSwjgzzjgdm(); //税务机关组织机构代码
      }
      else {
        throw ExceptionUtil.getBaseException(new Exception(key), "");
      }

      List result = new ArrayList();
      if (swjgzzjgdm == null || swjgzzjgdm.equals("")) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("不存在该计算机代码(" + jsjdm + ")的登记信息！"));
      }
      else {
        String qxdm = swjgzzjgdm.substring(0, 2); //区县代码
        String sql =
            "SELECT Z.XJRQ,Z.SZDM,Z.SZSMMC,M.SZSMDM,MD.SZSMMC,M.SKSSKSRQ,"
            +
            "M.SKSSJSRQ,M.SJSE FROM SBDB.SB_JL_SBJKMX M, DMDB.SB_DM_SZSM MD, ("
            +
            "SELECT ZB.XJRQ,ZB.JKPZH,ZB.SZDM,SZ.SZSMMC FROM SBDB.SB_JL_SBJKZB ZB, "
            + "DMDB.SB_DM_SZSM SZ WHERE ZB.QXDM = ? AND ZB.JSJDM = ? "
            +
            "AND ZB.SBRQ < ? AND SUBSTR(ZB.ZWBS,2,1) = '0' AND ZB.SZDM = SZ.SZSMDM) Z "
            + "WHERE M.JKPZH = Z.JKPZH AND M.JSJDM = ? "
            + "AND M.SZSMDM=MD.SZSMDM AND M.QXDM=? ";

        //得到连接和orManager对象
        conn = DBResource.getConnection(DBResource.DB_SHENBAO);
        pst = conn.prepareStatement(sql);
        pst.setString(1, qxdm);
        pst.setString(2, jsjdm);
        pst.setTimestamp(3, new Timestamp(time.getTime()));
        pst.setString(4, jsjdm);
        pst.setString(5, qxdm);
        rs = pst.executeQuery();
      }

      while (rs.next()) {
        Sbjkyqwrk sbjkyqwrk = new Sbjkyqwrk();
        sbjkyqwrk.setXjrq(rs.getTimestamp(1));
        sbjkyqwrk.setSzdm(rs.getString(2));
        sbjkyqwrk.setSzmc(rs.getString(3));
        sbjkyqwrk.setSzsmdm(rs.getString(4));
        sbjkyqwrk.setSzsmmc(rs.getString(5));
        sbjkyqwrk.setSkssksrq(rs.getTimestamp(6));
        sbjkyqwrk.setSkssjsrq(rs.getTimestamp(7));
        sbjkyqwrk.setSjse(rs.getBigDecimal(8));

        result.add(sbjkyqwrk);
      }
      return result;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询申报缴款款逾期未入库数据失败!");
    }
    finally {
      try {
        rs.close();
      }
      catch (Exception ex) {}
      try {
        pst.close();
      }
      catch (Exception ex) {}
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

//    /**
//     * 取得申报编号，跟计算机代码相关，不超过18位
//     * @param jsjdm 计算机代码
//     * @return String 申报编号
//     */
//    public static String getSbbh(String jsjdm)
//    {
//        ZhsbProcessor zhsbPro = new ZhsbProcessor();
//        return zhsbPro.getSbbh(jsjdm);
//    }

  /**
   * 申报编号的生成,申报编号的生成规则为：计算机代码加上两位年加上8位序列号
   * @param jsjdm 计算机代码
   * @return sbbh 申报编号
   * @throws DeclareException 异常
   */
  public static String getSbbh(String jsjdm) throws Exception {
    //0.句柄申明
    Connection conn = null;
    DBResource db = new DBResource();
    String sequence = "00000000"; //默认
    String sql = null;
    Statement st = null;
    ResultSet rs = null;
    String sbbh = null;
    //1.入口参数校验
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("计算机代码不能为空！");
    //2.初始化
    //3.业务过程
    try {
      //1.创建资源
      conn = db.getConnection();
      st = conn.createStatement();
      //2.定义sql语句，批处理，取当前序号
      sql = "SELECT LPAD(SBDB.SEQ_SB_SBBH.NEXTVAL,8,'0') FROM DUAL";
      rs = st.executeQuery(sql);
      if (rs.next()) {
        sequence = rs.getString(1);
      }
      else {
        throw new Exception("无法获取申报编号从序列中");
      }
      //3.组织序号
      sbbh = jsjdm + DateUtilPro.getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "获取申报编号失败!");
    }
    finally {
      try {
        rs.close();
        st.close();
        db.destroyConnection(conn);
      }
      catch (Exception sqle) {
        sqle.printStackTrace();
      }
    }
    return sbbh;
  }

  /**
   * 税票号码的生成,税票号码的生成规则为：计算机代码加上两位年加上8位序列号
   * @param jsjdm 计算机代码
   * @return sbbh 申报编号
   * @throws DeclareException 异常
   */
  public static String getSphm(String jsjdm) throws Exception {
    //0.句柄申明
    Connection conn = null;
    DBResource db = new DBResource();
    String sequence = "00000000"; //默认
    String sql = null;
    Statement st = null;
    ResultSet rs = null;
    String sbbh = null;
    //1.入口参数校验
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("计算机代码不能为空！");
    //2.初始化
    //3.业务过程
    try {
      //1.创建资源
      conn = db.getConnection();
      st = conn.createStatement();
      //2.定义sql语句，批处理，取当前序号
      sql = "SELECT LPAD(SBDB.SEQ_SB_SPHM.NEXTVAL,8,'0') FROM DUAL";
      rs = st.executeQuery(sql);
      if (rs.next()) {
        sequence = rs.getString(1);
      }
      else {
        throw new Exception("无法获取税票号码从序列中");
      }
      //3.组织序号
      sbbh = jsjdm + DateUtilPro.getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "获取税票号码失败！");
    }
    finally {
      try {
        rs.close();
        st.close();
        db.destroyConnection(conn);
      }
      catch (Exception sqle) {
        sqle.printStackTrace();
      }
    }
    return sbbh;
  }

  /**
   * 获取税种税目名称
   * @param szsmdm 税种税目代码
   * @param conn 数据库连接
   * @return String 税种税目名称
   * @throws BaseException
   */
  private static String addSzsmMc(String szsmdm, Connection conn) throws
      BaseException {
    String szsmmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT SZSMMC FROM DMDB.SB_DM_SZSM WHERE SZSMDM = '")
          .append(szsmdm).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        szsmmc = rs.getString("szsmmc");
      }
      return szsmmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询税种税目数据失败!");
    }
  }

  //取税务机关名称
  private static String getSwjgmc(String swjgzzjgdm, Connection conn) throws
      BaseException {
    String swjgmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '")
          .append(swjgzzjgdm).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        swjgmc = rs.getString("swjgzzjgmc");
      }
      return swjgmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询税务机关名称失败！");
    }
  }

  /**
   * 根据预算科目代码和时间，查询对应的预算科目名称
   * @param yskmdm 代码
   * @param time 时间
   * @param conn 数据库连接
   * @return String 名称
   * @throws BaseException
   */
  private static String addYskmMc(String yskmdm, Date time, Connection conn) throws
      BaseException {
    String yskmmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //分析当前时间
      int curYear = time.getYear() + 1900;
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT YSKMMC FROM DMDB.KJ_DM_YSKM WHERE YSKMDM = '")
          .append(yskmdm)
          .append("' AND ND='").append(curYear).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        yskmmc = rs.getString("yskmmc");
      }
      return yskmmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询预算科目数据失败!");
    }
  }

  /**
   * 根据代码查询税款类型名称
   * @param sklxdm 代码
   * @param conn 数据库连接
   * @return String 名称
   * @throws BaseException
   */
  private static String addSklxMc(String sklxdm, Connection conn) throws
      BaseException {
    String sklxmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT SKLXMC FROM DMDB.KJ_DM_SKLX WHERE SKLXDM = '")
          .append(sklxdm).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        sklxmc = rs.getString("sklxmc");
      }
      return sklxmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询税款类型数据失败!");
    }
  }

  /**
   * 根据代码查询预算级次名称
   * @param ysjcdm 代码
   * @param conn 数据库连接
   * @return String 名称
   * @throws BaseException
   */
  private static String addYsjcMc(String ysjcdm, Connection conn) throws
      BaseException {
    String ysjcmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT YSJCMC FROM DMDB.SF_DM_YSJC WHERE YSJCDM = '")
          .append(ysjcdm).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        ysjcmc = rs.getString("ysjcmc");
      }
      return ysjcmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询预算级次数据失败!");
    }
  }

  /**
   * 获得国库名称
   * @param swjgzzjgdm 税务机关组织机构代码
   * @param conn 数据库连接
   * @return  String
   * @throws BaseException
   */
  private static String addGkMc(String swjgzzjgdm, Connection conn) throws
      BaseException {
    String gkmc = "";
    try {
      if (conn == null)
        throw new Exception("没有获得数据库连接！");
      //得到连接和orManager对象
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT SKGK FROM DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '")
          .append(swjgzzjgdm).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        gkmc = rs.getString("skgk");
      }
      return gkmc;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询收款国库数据失败！");
    }
  }

  /**
   * 根据税种税目代码，取对应的计税基数
   * @param djzclxdm 登记注册类型代码
   * @return Djzclx Object
   * @throws BaseException
   */
  public static Djzclx getDjzclx(String djzclxdm) throws BaseException {
    Szsm data = new Szsm();
    Connection conn = null;
    ORManager orManager = null;
    try {
      //得到连接和orManager对象
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = new Vector(); //查询条件
      criteria.add("DJZCLXDM = '" + djzclxdm + "'");
      //查询的ORContext对象
      ORContext orCtx = new ORContext(Djzclx.class.getName(), criteria);
      List queryresult = orManager.query(SESSION_ID, conn, orCtx);
      if (queryresult.size() != 0) {
        return (Djzclx) queryresult.get(0);
      }
      return null;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询登记注册类型数据错误!");
    }
    finally {
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 根据税种税目代码，取对应的计税基数
   * @param szsmdm 税种税目
   * @return Szsm Object
   * @throws BaseException
   */
  public static Szsm getSzsm(String szsmdm) throws BaseException {
    Szsm data = new Szsm();
    Connection conn = null;
    ORManager orManager = null;
    try {
      //得到连接和orManager对象
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = new Vector(); //查询条件
      criteria.add("SZSMDM = '" + szsmdm + "'");
      //查询的ORContext对象
      ORContext orCtx = new ORContext(Szsm.class.getName(), criteria);
      List queryresult = orManager.query(SESSION_ID, conn, orCtx);
      if (queryresult.size() == 1) {
        return (Szsm) queryresult.get(0);
      }
      else
        throw new Exception("没有税种税目代码(" + szsmdm + ")对应的数据！");
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "查询税种税目数据错误!");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**转换处罚系统过来的申报数据为申报缴款主表和申报缴款明细数据
   * @param sbsj 处罚模块的申报数据
   * @return DeclareInfor 生成好的申报缴款数据
   * @throws BaseException 异常操作
   */
  public static DeclareInfor getJksBySbsjForCF(Sbsj sbsj) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    String swjgzzjgdm = sbsj.getZsjgdm(); //税务机关
    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm); //税务机关
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //创建日期
    sbjkmx.setLrrq(now); //录入日期
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkmxList.add(sbjkmx);

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(sbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(sbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(sbsj.getLsgxdm());
    sbjkzb.setNsrmc(sbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //征收方式为上门
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //申报日期
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //帐页日期
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //默认为‘已申报’

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //默认为一票一税

    //把生成的数据插入数据表中并返回
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //只有一张票，因为，只有一条缴款明细记录
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //封装后的数据返回
  }

  /**
   * 获取税款所属日期
   * @param smdm 税种税目代码
   * @param djzclx 登记注册类型
   * @param rq  当前日期
   * @return  Map
   * @throws BaseException
   */
  public static Map getZqzzrq(String smdm, String djzclx, String rq) throws
      BaseException {
    Connection conn = null;
    try {
      //获得 ORManager
      ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      //查询征期日历表
      ArrayList zqrlRs = new ArrayList();

      String sqlWhere =
          "(ZQLXDM >= '0' AND ZQQSRQ <= to_date('"
          + rq + "','yyyymmdd') AND SZSMDM = '" + smdm
          + "' AND DJZCLXDM = '" + djzclx
          + "' AND ZQZZRQ >= to_date('" + rq
          + "','yyyymmdd')) ORDER BY ZQQSRQ";
      Vector criteria = new Vector();
      criteria.add(sqlWhere);
      ORContext orCtx = new ORContext(Zqrl.class.getName(), criteria);
      conn = DBResource.getConnection(DBResource.DB_SHENBAO); //
      zqrlRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //查询征期日历表
      if (zqrlRs.size() != 0) {
        Map retMap = new HashMap(3);
        retMap.put("SKSSKSRQ", ( (Zqrl) zqrlRs.get(0)).getZqssrqq()); // 开始日期
        retMap.put("SKSSJSRQ", ( (Zqrl) zqrlRs.get(0)).getZqssrqz()); // 结束日期
        retMap.put("ND", ( (Zqrl) zqrlRs.get(0)).getNd()); //年度
        return retMap;
      }
      else
        return null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "获取税款所属日期出错！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
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
    if (wszh == null || wszh.trim().equals("") || nd == null ||
        nd.trim().equals(""))
      throw ExceptionUtil.getBaseException(new ApplicationException("参数不能为空！"));
    Connection conn = null;
    try {
      ArrayList result = new ArrayList();
      StringBuffer sqlBuffer = new StringBuffer();
      if (lxdm == 1) {
        //零散税
        sqlBuffer.append(
            "SELECT DISTINCT A.JKPZH,A.HZRQ FROM SBDB.SB_JL_LSWSZSBHZ A,")
            .append("SBDB.SB_JL_LSSWSZZ B WHERE A.JSJDM>='0' AND B.JSJDM>='0'")
            .append(" AND B.WSZH = '").append(wszh)
            .append("' AND B.ND = '")
            .append(nd).append("' AND A.SBHZDH = B.SBHZDH ");
      }
      else if (lxdm == 2) {
        //个体工商户
        sqlBuffer.append(
            "SELECT DISTINCT C.JKPZH,C.HZRQ FROM SBDB.SB_JL_GTGSHWSZSBHZ C,")
            .append(
            "SBDB.SB_JL_GTGSHWSZZ D WHERE C.JSJDM>='0' AND D.JSJDM>='0'")
            .append(" AND D.WSZH = '").append(wszh)
            .append("' AND D.ND = '")
            .append(nd).append("' AND C.SBHZDH = D.SBHZDH");
      }
      else {
        throw ExceptionUtil.getBaseException(new ApplicationException("未知请求类型！"));
      }
      String sql = sqlBuffer.toString();
      sqlBuffer.setLength(0);

      ArrayList jkpzhList = new ArrayList();
      Timestamp hzrq = null;
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement sqlStatement = conn.prepareStatement(sql);
      ResultSet rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzhList.add(rs.getString("jkpzh"));
        hzrq = (Timestamp) rs.getTimestamp("hzrq");
      }
      if (jkpzhList.size() == 0) {
        return result;
      }
      //判断是否入库
      boolean isRk = isJkpzhInStore(jkpzhList, conn, 1);
      if (isRk) {
        //获得 ORManager
        ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
        Vector criteria = new Vector();
        String sqlWhere = "WSZH = '" + wszh + "' AND ND = '" + nd + "'";
        criteria.add(sqlWhere);
        ORContext orCtx = null;
        if (lxdm == 1) //零散税
          orCtx = new ORContext(Lsswszmx.class.getName(), criteria);
        else if (lxdm == 2) //个体工商户
          orCtx = new ORContext(Gtgshwszmx.class.getName(), criteria);
        else
          throw ExceptionUtil.getBaseException(new ApplicationException(
              "未知请求类型！"));
        ArrayList tempAr = new ArrayList();
        tempAr = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //查询明细数据

        if (lxdm == 1) { //零散税
          for (int i = 0; i < tempAr.size(); i++) {
            Lsswszmx lx = new Lsswszmx();
            lx = (Lsswszmx) tempAr.get(i);
            lx.setSzmc(addSzsmMc(lx.getSzdm(), conn)); //税种名称
            lx.setSzsmmc(addSzsmMc(lx.getSzsmdm(), conn)); //税目名称
            lx.setYskmmc(addYskmMc(lx.getYskmdm(), lx.getLrrq(), conn)); //预算科目名称
            LsswszInfor lsswszInfo = new LsswszInfor();
            lsswszInfo.setData(lx); //封装为前端应用值对象
            lsswszInfo.setHzrq(hzrq); //汇总日期
            result.add(lsswszInfo);
          }
        }
        else if (lxdm == 2) { //个体工商户
          for (int j = 0; j < tempAr.size(); j++) {
            Gtgshwszmx gx = new Gtgshwszmx();
            gx = (Gtgshwszmx) tempAr.get(j);
            gx.setSzmc(addSzsmMc(gx.getSzdm(), conn));
            gx.setSzsmmc(addSzsmMc(gx.getSzsmdm(), conn));
            gx.setYskmmc(addYskmMc(gx.getYskmdm(), gx.getLrrq(), conn));
            GtgshwszInfo gtInfo = new GtgshwszInfo();
            gtInfo.setData(gx);
            gtInfo.setHzrq(hzrq); //封装为前端应用值对象
            result.add(gtInfo);
          }
        }
        else
          throw ExceptionUtil.getBaseException(new ApplicationException(
              "未知请求类型！"));
      }
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, e.getMessage());
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**add by Daniel
   * 转换银行系统过来的一批申报数据为申报缴款主表和申报缴款明细数据
   * sbsjList中取第一项生成缴款主表数据，数据验证也只是第一项
   * @param sbsj 其他模块的申报数据
   * @return DeclareInfor 生成好的申报缴款数据
   * @throws BaseException 异常操作
   */
  public static List generateBankSBJKS(Map sbsjMap) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    if (sbsjMap == null || sbsjMap.size() < 6) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("提供的数据不足以生成缴款书！"));
    }

    String jsjdm = (String) sbsjMap.get(SBSJZB_JSJDM);
    if (jsjdm == null || jsjdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("计算机代码不能为空！"));
    }
    String sklxdm = (String) sbsjMap.get(SBSJZB_SKLXDM);
    if (sklxdm == null || sklxdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("税款类型代码不能为空！"));
    }
    String sjly = (String) sbsjMap.get(SBSJZB_SJLY);
    if (sjly == null || sjly.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("数据来源不能为空！"));
    }
    String sbfsdm = (String) sbsjMap.get(SBSJZB_SBFSDM);
    if (sbfsdm == null || sbfsdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("申报方式代码不能为空！"));
    }
    String bz = (String) sbsjMap.get(SBSJZB_BEIZHU);
    String yhdm = (String) sbsjMap.get(SBSJZB_YHDM);
    String yhmc = (String) sbsjMap.get(SBSJZB_YHMC);
    String zh = (String) sbsjMap.get(SBSJZB_ZH);
    String lrr = (String) sbsjMap.get(SBSJZB_LRR);

    BigDecimal sjjehj = (BigDecimal) sbsjMap.get(SBSJZB_SJJE);
    if (sjjehj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("实缴金额合计不能为空！"));
    }
    List mxList = (List) sbsjMap.get(SBSJZB_SBJKMX);
    if (mxList == null || mxList.size() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("申报缴款明细至少有一条！"));
    }

    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(jsjdm);
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("没有得到该计算机代码的登记数据！"));
    }

    //判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // 非正常户
      throw ExceptionUtil.getBaseException(
          new ApplicationException("该纳税人状态不为正常，不能生成缴款书！"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关
    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    for (int i = 0; i < mxList.size(); i++) {
      Map mxMap = (Map) mxList.get(i);
      String szsmdm = (String) mxMap.get(SBSJMX_SZSMDM);
      if (szsmdm == null || szsmdm.length() <= 0) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("税种税目代码不能为空！"));
      }
      BigDecimal sjje = (BigDecimal) mxMap.get(SBSJMX_SJJE);
      BigDecimal jsje = (BigDecimal) mxMap.get(SBSJMX_JSJE);
      BigDecimal kssl = (BigDecimal) mxMap.get(SBSJMX_KSSL);
      BigDecimal sl = (BigDecimal) mxMap.get(SBSJMX_SL);
      if (sjje == null) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("金额不能为空！"));
      }
      Szsm szsm = getSzsm(szsmdm);
      Sbjkmx sbjkmx = new Sbjkmx();
      sbjkmx.setSzsmdm(szsmdm);
      sbjkmx.setZqlxdm(szsm.getZqlxdm()); // 征期类型代码
      sbjkmx.setJsjdm(jsjdm);
      sbjkmx.setSjse(sjje);
      sbjkmx.setRkje(sjje);
      sbjkmx.setKssl(kssl);
      sbjkmx.setSl(sl);
      sbjkmx.setJsje(jsje);
      sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
      sbjkmx.setNd(nd);
      sbjkmx.setCjrq(now); //创建日期
      sbjkmx.setLrrq(now); //录入日期
      sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码

      //税款所属开始日期
      if (mxMap.get(SBSJMX_SKSSKSRQ) != null) {
        sbjkmx.setSkssksrq( (Timestamp) mxMap.get(SBSJMX_SKSSKSRQ));
      }

      //税款所属结束日期
      if (mxMap.get(SBSJMX_SKSSJSRQ) != null) {
        sbjkmx.setSkssjsrq( (Timestamp) mxMap.get(SBSJMX_SKSSJSRQ));
      }

      sbjkmxList.add(sbjkmx);
    }

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(jsjdm);
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(sbfsdm); //征收方式为上门
    if (lrr != null) {
      sbjkzb.setLrr(lrr);
    }
    else {
      sbjkzb.setLrr(jsjdm);
    }
    sbjkzb.setRkje(sjjehj);
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //申报日期
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //帐页日期，初始为申报日期
    sbjkzb.setSjje(sjjehj);
    sbjkzb.setSklxdm(sklxdm);
    sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
    sbjkzb.setNd(nd);
    sbjkzb.setBz(bz);
    sbjkzb.setSjly(sjly); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //默认为‘已申报’
    sbjkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 联系电话

    //限缴日期
    if (sbsjMap.get(SBSJZB_XJRQ) != null) {
      sbjkzb.setXjrq( (Timestamp) sbsjMap.get(SBSJZB_XJRQ));
    }

    List tempList = (List) map.get("YHZH");

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //银行代码
      sbjkzb.setYhmc(yhmc); //银行名称
      sbjkzb.setZh(zh); //银行账号
    }
    else {
      if (tempList != null && tempList.size() != 0) {
        YHZH yhzh = (YHZH) tempList.get(0);
        sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
        sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
        sbjkzb.setZh(yhzh.getZh()); //银行账号
        for (int i = 0; i < tempList.size(); i++) {
          yhzh = (YHZH) tempList.get(i);
          if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
            sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
            sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
            sbjkzb.setZh(yhzh.getZh()); //银行账号
            break;
          }
        }
      }
    }

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //默认为一票一税

    //把生成的数据插入数据表中并返回
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    return dataList; //封装后的数据返回
  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合 内部对象为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJks(String sbbh) throws Exception {
    //0.句柄申明
    List tmpList = null;
    DBResource db = null;
    Statement st = null;
    Connection conn = null;
    ResultSet rs = null;
    Map jksmx = null;
    String jsjdm = null;
    StringBuffer sb;
    String sql = null;
    //1.输入参数校验
    if (sbbh == null || sbbh.length() != 18) {
      throw new Exception("申报编号非法:" + sbbh);
    }
    //2.初始化
    tmpList = new ArrayList();
    try {
      jsjdm = sbbh.substring(0, 8);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.业务过程
    try {
      ///3.0.获取资源
      conn = db.getConnection();
      st = conn.createStatement();
      ///3.1.生成根据sbbh和jsjdm作为查询条件的SQL语句
      sb = new StringBuffer();
      sb.append(InterFaceProcessor.getYpysJksQueryPrefix());
      sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
      sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
      sb.append(" and a.jsjdm=");
      sb.append(StringUtils.getSQLStr(jsjdm));
      sb.append(" and a.sbbh=");
      sb.append(StringUtils.getSQLStr(sbbh));
      //sb.append(" AND a.zwbs LIKE '0%0'");
      sb.append(InterFaceProcessor.getYpysJksQueryOrderByPostfix());
      sql = sb.toString();
      ///3.2.进行查询获取结果集
      InterFaceProcessor.log(sql);
      rs = st.executeQuery(sql);
      ///3.3.进行数据整理获取数据容器
      tmpList = new ArrayList();
      while (rs.next()) {
        jksmx = new HashMap();
        for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
          //InterFaceProcessor.log(ApplicationConstant.JKS_YPYS_MX_KEYS[i]);
          jksmx.put(ApplicationConstant.JKS_YPYS_MX_KEYS[i],
                    rs.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
        }
        InterFaceProcessor.log("第一轮查询的一票一税明细==" + jksmx);
        tmpList.add(jksmx);
      }
      rs.close();
      ///3.4.调用子方法进行分票
      tmpList = InterFaceProcessor.getYpdsJks(tmpList);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      st.close();
      db.destroyConnection(conn);
    }
    //99.返回值
    return tmpList;
  }

  /**
   * 根据税票号码获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合 内部对象为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJksBySphm(String sphm) throws Exception {
    //0.句柄申明
    List tmpList = null;
    DBResource db = null;
    Statement st = null;
    Connection conn = null;
    ResultSet rs = null;
    Map jksmx = null;
    String jsjdm = null;
    StringBuffer sb;
    String sql = null;
    //1.输入参数校验
    if (sphm == null || sphm.length() != 18) {
      throw new Exception("税票号码非法:" + sphm);
    }
    //2.初始化
    tmpList = new ArrayList();
    try {
      jsjdm = sphm.substring(0, 8);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.业务过程
    try {
      ///3.0.获取资源
      conn = db.getConnection();
      st = conn.createStatement();
      ///3.1.生成根据sbbh和jsjdm作为查询条件的SQL语句
      sb = new StringBuffer();
      sb.append(InterFaceProcessor.getYpysJksQueryPrefix());
      sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
      sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
      sb.append(" AND a.jsjdm=");
      sb.append(StringUtils.getSQLStr(jsjdm));
      sb.append(" AND a.sphm=");
      sb.append(StringUtils.getSQLStr(sphm));
      //sb.append(" AND a.zwbs LIKE '0%0'");
      sb.append(InterFaceProcessor.getYpysJksQueryOrderByPostfix());
      sql = sb.toString();
      ///3.2.进行查询获取结果集
      InterFaceProcessor.log(sql);
      rs = st.executeQuery(sql);
      ///3.3.进行数据整理获取数据容器
      tmpList = new ArrayList();
      while (rs.next()) {
        jksmx = new HashMap();
        for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
          jksmx.put(ApplicationConstant.JKS_YPYS_MX_KEYS[i],
                    rs.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
        }
        tmpList.add(jksmx);
      }
      rs.close();
      st.close();
      ///3.4.调用子方法进行分票
      tmpList = InterFaceProcessor.getYpdsJks(tmpList);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      db.destroyConnection(conn);
    }
    //99.返回值
    return tmpList;
  }

  //* 此方法仅仅为本类中[public static List getYpdsJks(String obj) throws Exception]提供使用
   protected static String getYpysJksQueryPrefix() {
     StringBuffer sb = new StringBuffer();
     sb.append("SELECT a.sbbh AS sbbh");
     sb.append(",a.sklxdm AS sklxdm");
     sb.append(
         ",(SELECT t.sklxmc FROM DMDB.KJ_DM_SKLX t WHERE t.sklxdm=a.sklxdm) AS sklxmc");
     sb.append(",a.sphm AS sphm");
     sb.append(",a.jsjdm AS jsjdm");
     sb.append(
         ",(select nsrmc from (SELECT nsrmc,jsjdm FROM djdb.dj_jl_jbsj UNION ALL SELECT nsrmc,jsjdm FROM djdb.dj_jl_zrrjbsj) where rownum =1 and jsjdm = a.jsjdm) AS nsrmc");
     sb.append(",a.jkpzh AS jkpzh");
     sb.append(",a.yhdm AS yhdm");
     sb.append(",a.yhmc AS yhmc");
     sb.append(",a.zh AS zh");
     sb.append(",a.gkzzjgdm as gkzzjgdm");
     sb.append(",a.swjgzzjgdm as swjgzzjgdm");
     sb.append(",a.ysjcdm AS ysjcdm");
     sb.append(",a.yskmdm as yskmdm");
     sb.append(",a.szdm as szdm");
     sb.append(
         ",(SELECT t.szsmmc FROM DMDB.SB_DM_SZSM t WHERE t.szsmdm=a.szdm) as szmc");
     sb.append(",to_char(a.xjrq,'yyyymmdd') as xjrq");
     sb.append(",a.zsswjgzzjgdm AS zsswjgzzjgdm");
     sb.append(",a.lrr AS lrr");
     sb.append(",to_char(a.sbrq,'yyyymmdd') AS sbrq");
     sb.append(",to_char(a.lrrq,'yyyymmdd') AS lrrq");
     sb.append(",(SELECT t.swjgzzjgmc FROM DMDB.GY_DM_SWJGZZJG t WHERE a.swjgzzjgdm=t.swjgzzjgdm) AS swjgzzjgmc");
     sb.append(",(SELECT t.swjgzzjgmc FROM DMDB.GY_DM_SWJGZZJG t WHERE a.zsswjgzzjgdm=t.swjgzzjgdm) AS zsswjgzzjgmc");
     sb.append(",b.szsmdm AS szsmdm");
     sb.append(
         ",(SELECT t.szsmmc FROM DMDB.SB_DM_SZSM t WHERE t.szsmdm=b.szsmdm) as szsmmc");
     sb.append(",to_char(b.skssksrq,'yyyymmdd') AS skssksrq");
     sb.append(",to_char(b.skssjsrq,'yyyymmdd') AS skssjsrq");
     sb.append(",b.sjse AS sjse");
     sb.append(",b.kssl AS kssl");
     sb.append(",b.jsje AS jsje");
     sb.append(",b.sl AS sl");
     return sb.toString();
   }

  //* 此方法仅仅为本类中[public static List getYpdsJks(String obj) throws Exception]提供使用
   protected static String getYpysJksQueryOrderByPostfix() {
     StringBuffer sb = new StringBuffer();
     sb.append(" ORDER BY a.sklxdm,a.zsswjgzzjgdm,a.ysjcdm,a.yskmdm,a.skssksrq,a.skssjsrq,a.xjrq"); //此行进行排序
     return sb.toString();
   }

  /**
   * 根据申报编号获取一票多税的缴款书
   * 缴款分票原则：
   * 一、一张票上只能一种税款类型
   * 二、一张税票上只能一个征收机关
   * 三、同一张缴款书根据配置的行数打印
   * 四、按照不同预算级次分行打
   * 五、不同的预算科目分行打
   * 六、不同的税款所属期分行打
   * 七、不同限缴日期分行打
   * 八、排序顺序：预算级次代码 预算科目代码 税款所属期 限缴日期 从小到大
   * @param JksList 一票一税缴款书集合,该集合必须保证属于同一笔申报且必须保证数据集已经排列好
   * @return 一票多税缴款书集合 内部对象为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJks(List JksList) throws Exception {
    //0.句柄申明
    List tmpList = null;
    List returnList = null;
    Map tmpMap = null;
    String sbbh = null;
    String tmpStr = null;
    //1.输入参数校验
    if (JksList == null || JksList.size() == 0) {
      throw new Exception("一票一税缴款书集合非法或为空");
    }
    for (int i = 0; i < JksList.size(); i++) {
      tmpStr = (String) ( ( (Map) JksList.get(i)).get("sbbh"));
      if (i == 0) {
        sbbh = tmpStr;
      }
      if (!sbbh.equals(tmpStr)) {
        throw new Exception("数据集非法，该笔一票一税缴款书集合存在不属于同一笔申报的数据");
      }
    }
    //2.初始化
    tmpList = new ArrayList();
    returnList = new ArrayList();
    //3.业务过程
    String sklxdm = null;
    Map map_sklxdm = new HashMap(); //这个Map对象将是所有分类后数据的容器
//    String [] keys={"sklxdm","zsswjgzzjgdm","ysjcdm","yskmdm","skssksrq","skssjsrq","xjrq"};
    String[] keys = {
        "sklxdm", "zsswjgzzjgdm"};
    List[] sorts = {
        new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(),
        new ArrayList(), new ArrayList(), new ArrayList()};
    ///3.0.根据分票原则整理一票一税缴款书集合内的对象到不同的数据容器
    int count = JksList.size();
    Map map = null;
    for (int i = 0; i < count; i++) {
      ////3.0.0.获取一票一税缴款书明细对象Map
      map = (HashMap) JksList.get(i);
      ////3.0.1.获取一票一税缴款书分票数据
      sklxdm = (String) map.get("sklxdm");
      ////3.0.2.将一票一税缴款书数据置入不同的数据集
      InterFaceProcessor ifp = new InterFaceProcessor();
      ifp.ypdsNBranchTreeIterator(map_sklxdm, sklxdm, keys, 0, map, sorts); //递归遍历树进行数据分类，如果某一个分类容器不存在则进行创建
      ////3.0.99.恢复默认值
      sklxdm = null;
      map = null;
    }
    ///3.1.根据缴款书分票原则生成缴款书对象并加入数据集
    YPDSJKS jks = null;
    ArrayList tmp_ypys_dataList = null;
    ArrayList tmp_ypds_dataList = null;
    tmpList = new ArrayList();
    List tmpList310 = null;
    Map tmpMap310 = null;
    Map tmpMap3101 = null;
    int lineCount = 0;
    Object obj0 = null;
    Object obj1 = null;
    for (int i = 0; i < sorts[0].size(); i++) { //根据税款类型进行分票
      InterFaceProcessor.log("根据税款类型进行分票[" + i + "]");
      obj0 = map_sklxdm.get(sorts[0].get(i)); //税款类型为第一层肯定不会为空
      for (int j = 0; j < sorts[1].size(); j++) { //根据征收机关进行分票
        InterFaceProcessor.log("根据征收机关进行分票[" + j + "]");
        jks = null;
        ////3.1.0.获取当前数据分类容器
        obj1 = ( (Map) obj0).get(sorts[1].get(j));
        if (obj1 == null) { //如果对应税款类型及征收机关不存在则进行下一轮循环
          continue;
        }
        ////3.1.1.现在开始根据系统预定义的行数获取并整理一票一税数据
        tmpList310 = (List) obj1; //这个对象是所有需要分在同一类一票多税缴款书的数据
        InterFaceProcessor ifp = new InterFaceProcessor();
        List copiedYpysJksmx = ifp.copyYpysJksMxMap(tmpList310); //复制一份一票一税缴款书明细
        Map ypysJksmxSplitByLine = ifp.splitYpysJksMxMapByLine(copiedYpysJksmx); //经过分行分类的一票一税缴款书明细
        Map tmpMap31010 = new HashMap();
        List tmpList31010 = null;
        for (int k = 0; k < tmpList310.size(); k++) {
          /////3.1.0.0.取出第一个数据并制造第一个缴款书对象并赋予部分值
          tmpMap310 = (Map) tmpList310.get(k);
          InterFaceProcessor.log("根据系统预定义的行数获取并整理一票一税数据[" +
                                 tmpMap310.get("sjse") + "]");
          /////3.1.0.1.合并所有需要合并的数据
          if (k < (tmpList310.size() - 1) && tmpList310.size() > 1) { //当当前元素不是最后一个元素且容器内数量大于1
            //进行比较和合并,当符合分行原则的数据在和当前数据合并后移出次级数据并移动指针向前
            tmpMap3101 = (Map) tmpList310.get(k + 1);
            if ( ( ( (String) tmpMap310.get("ysjcdm")).equals( (String)
                tmpMap3101.get("ysjcdm")))
                &&
                ( ( (String) tmpMap310.get("yskmdm")).equals( (String)
                tmpMap3101.
                get("yskmdm")))
                &&
                ( ( (String) tmpMap310.get("skssksrq")).equals( (String)
                tmpMap3101.
                get("skssksrq")))
                &&
                ( ( (String) tmpMap310.get("skssjsrq")).equals( (String)
                tmpMap3101.
                get("skssjsrq")))
                &&
                ( ( (String) tmpMap310.get("xjrq")).equals( (String) tmpMap3101.
                get("xjrq")))) {
              //合并税额
              tmpMap310.put("sjse",
                            String.valueOf( (Double.parseDouble( (String)
                  tmpMap310.get("sjse"))) +
                                           (Double.parseDouble( (String)
                  tmpMap3101.
                  get("sjse")))));
              tmpList310.remove(k + 1);
              k--;
            }
          }
          else if (k == (tmpList310.size() - 1) && tmpList310.size() > 1) { //当当前元素是最后一个元素且容器内数量大于1
            //do nothing
          }
          else if (tmpList310.size() == 1) { //当容器内数量等于1
            //do nothing
          }
        }
        ////3.1.2.子集分票
        tmp_ypds_dataList = new ArrayList();
        tmp_ypys_dataList = new ArrayList();
        String hjsjje = "0.00";
        log("子集分票时存在" + tmpList310.size() + "个元素");
        for (int k = 0; k < tmpList310.size(); k++) {
          tmpMap310 = (Map) tmpList310.get(k);
          if (k == 0) {
            jks = new YPDSJKS();
            jks.setSklxdm( (String) tmpMap310.get("sklxdm"));
            jks.setSklxmc( (String) tmpMap310.get("sklxmc"));
            jks.setSphm( (String) tmpMap310.get("sphm"));
            jks.setNsrmc( (String) tmpMap310.get("nsrmc"));
            jks.setSbbh( (String) tmpMap310.get("sbbh"));
            jks.setJsjdm( (String) tmpMap310.get("jsjdm"));
            jks.setYhdm( (String) tmpMap310.get("yhdm"));
            jks.setYhmc( (String) tmpMap310.get("yhmc"));
            jks.setZh( (String) tmpMap310.get("zh"));
            jks.setSwjgzzjgdm( (String) tmpMap310.get("swjgzzjgdm"));
            jks.setSwjgzzjgmc( (String) tmpMap310.get("swjgzzjgmc"));
            jks.setZsdwdm( (String) tmpMap310.get("zsswjgzzjgdm"));
            jks.setZsdwmc( (String) tmpMap310.get("zsswjgzzjgmc"));
            jks.setLrr( (String) tmpMap310.get("lrr"));
            jks.setLrrq( (String) tmpMap310.get("lrrq"));
            jks.setSbrq( (String) tmpMap310.get("sbrq"));
            jks.setXjrq( (String) tmpMap310.get("xjrq"));
          }
          if (lineCount < ApplicationConstant.JKS_YPDS_LINENUM - 1) {
            lineCount++;
            tmp_ypds_dataList.add(tmpMap310);
            hjsjje = String.valueOf(Double.parseDouble(hjsjje) +
                                    Double.
                                    parseDouble( (String) (tmpMap310.get("sjse"))));
            //从分行后的一票一税缴款书明细获取数据置入一票多税缴款书对象
            String lineKey = (String) tmpMap310.get("ysjcdm") +
                (String) tmpMap310.get("yskmdm") +
                (String) tmpMap310.get("skssksrq") +
                (String) tmpMap310.get("skssjsrq") +
                (String) tmpMap310.get("xjrq");
            List tmpList312 = (List) ypysJksmxSplitByLine.get(lineKey);
            for (int m = 0; m < tmpList312.size(); m++) {
              tmp_ypys_dataList.add(tmpList312.get(m));
            }
            //
            if (k == tmpList310.size() - 1) {
              jks.setYpdsJksMx(tmp_ypds_dataList);
              jks.setYpysJksMx(tmp_ypys_dataList);
              jks.setSjjexx(hjsjje);
              //jks.setSjjedx(MoneyUtils.convert0(Double.parseDouble(hjsjje)));
              returnList.add(jks);
            }
          }
          else if (lineCount == ApplicationConstant.JKS_YPDS_LINENUM - 1) {
            tmp_ypds_dataList.add(tmpMap310);
            hjsjje = String.valueOf(Double.parseDouble(hjsjje) +
                                    Double.
                                    parseDouble( (String) (tmpMap310.get("sjse"))));
            //从分行后的一票一税缴款书明细获取数据置入一票多税缴款书对象
            String lineKey = (String) tmpMap310.get("ysjcdm") +
                (String) tmpMap310.get("yskmdm") +
                (String) tmpMap310.get("skssksrq") +
                (String) tmpMap310.get("skssjsrq") +
                (String) tmpMap310.get("xjrq");
            List tmpList312 = (List) ypysJksmxSplitByLine.get(lineKey);
            for (int m = 0; m < tmpList312.size(); m++) {
              tmp_ypys_dataList.add(tmpList312.get(m));
            }
            //
            jks.setYpdsJksMx(tmp_ypds_dataList);
            jks.setYpysJksMx(tmp_ypys_dataList);
            jks.setSjjexx(hjsjje);
            //jks.setSjjedx(MoneyUtils.convert0(Double.parseDouble(hjsjje)));
            returnList.add(jks);
            //
            jks = jks.cloneJKSMainContent();
            jks.setSjjexx(null);
            jks.setSjjedx(null);
            lineCount = 0;
            hjsjje = "0.00";
            tmp_ypds_dataList = new ArrayList();
            tmp_ypys_dataList = new ArrayList();
          }
        }
      }
    }
    ///3.99.清理句柄

    //99.返回值
    return returnList;
  }

  
  public static List getYpdsJksForSkh(List JksList) throws Exception {
    //0.句柄申明
    List tmpList = null;
    List returnList = null;
    Map tmpMap = null;
    String sbbh = null;
    String tmpStr = null;
    //1.输入参数校验
    if (JksList == null || JksList.size() == 0) {
      throw new Exception("一票一税缴款书集合非法或为空");
    }
    for (int i = 0; i < JksList.size(); i++) {
      tmpStr = (String) ( ( (Map) JksList.get(i)).get("sbbh"));
      if (i == 0) {
        sbbh = tmpStr;
      }
      if (!sbbh.equals(tmpStr)) {
        throw new Exception("数据集非法，该笔一票一税缴款书集合存在不属于同一笔申报的数据");
      }
    }
    //2.初始化
    tmpList = new ArrayList();
    returnList = new ArrayList();
    //3.业务过程
    String sklxdm = null;
    Map map_sklxdm = new HashMap(); //这个Map对象将是所有分类后数据的容器
//    String [] keys={"sklxdm","zsswjgzzjgdm","ysjcdm","yskmdm","skssksrq","skssjsrq","xjrq"};
    String[] keys = {
        "sklxdm", "zsswjgzzjgdm"};
    List[] sorts = {
        new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(),
        new ArrayList(), new ArrayList(), new ArrayList()};
    ///3.0.根据分票原则整理一票一税缴款书集合内的对象到不同的数据容器
    int count = JksList.size();
    Map map = null;
    for (int i = 0; i < count; i++) {
      ////3.0.0.获取一票一税缴款书明细对象Map
      map = (HashMap) JksList.get(i);
      ////3.0.1.获取一票一税缴款书分票数据
      sklxdm = (String) map.get("sklxdm");
      ////3.0.2.将一票一税缴款书数据置入不同的数据集
      InterFaceProcessor ifp = new InterFaceProcessor();
      ifp.ypdsNBranchTreeIterator(map_sklxdm, sklxdm, keys, 0, map, sorts); //递归遍历树进行数据分类，如果某一个分类容器不存在则进行创建
      ////3.0.99.恢复默认值
      sklxdm = null;
      map = null;
    }
    ///3.1.根据缴款书分票原则生成缴款书对象并加入数据集
    YPDSJKS jks = null;
    ArrayList tmp_ypys_dataList = null;
    ArrayList tmp_ypds_dataList = null;
    tmpList = new ArrayList();
    List tmpList310 = null;
    Map tmpMap310 = null;
    Map tmpMap3101 = null;
    int lineCount = 0;
    Object obj0 = null;
    Object obj1 = null;
    for (int i = 0; i < sorts[0].size(); i++) { //根据税款类型进行分票
      InterFaceProcessor.log("根据税款类型进行分票[" + i + "]");
      obj0 = map_sklxdm.get(sorts[0].get(i)); //税款类型为第一层肯定不会为空
      for (int j = 0; j < sorts[1].size(); j++) { //根据征收机关进行分票
        InterFaceProcessor.log("根据征收机关进行分票[" + j + "]");
        jks = null;
        ////3.1.0.获取当前数据分类容器
        obj1 = ( (Map) obj0).get(sorts[1].get(j));
        if (obj1 == null) { //如果对应税款类型及征收机关不存在则进行下一轮循环
          continue;
        }
        ////3.1.1.现在开始根据系统预定义的行数获取并整理一票一税数据
        tmpList310 = (List) obj1; //这个对象是所有需要分在同一类一票多税缴款书的数据
        InterFaceProcessor ifp = new InterFaceProcessor();
        List copiedYpysJksmx = ifp.copyYpysJksMxMap(tmpList310); //复制一份一票一税缴款书明细
        Map ypysJksmxSplitByLine = ifp.splitYpysJksMxMapByLineForSkh(copiedYpysJksmx); //经过分行分类的一票一税缴款书明细
        Map tmpMap31010 = new HashMap();
        List tmpList31010 = null;
        
        for (int k = 0; k < tmpList310.size(); k++) {
          /////3.1.0.0.取出第一个数据并制造第一个缴款书对象并赋予部分值
          
          tmpMap310 = (Map) tmpList310.get(k);
          //add by guzx
          List smList = (List)tmpMap310.get("mxlist");
          if (smList==null){
             smList=new ArrayList();
             Map tMap = new HashMap();
             for (int j1 = 0; j1 < ApplicationConstant.JKS_YPYS_MX_KEYS.length; j1++) {
               tMap.put(ApplicationConstant.JKS_YPYS_MX_KEYS[j1],
                    tmpMap310.get(ApplicationConstant.JKS_YPYS_MX_KEYS[j1]));
             }
             smList.add(tMap); 
             tmpMap310.put("mxlist" , smList);
          }

          InterFaceProcessor.log("根据系统预定义的行数获取并整理一票一税数据[" +
                                 tmpMap310.get("sjse") + "]");
          /////3.1.0.1.合并所有需要合并的数据
          if (k < (tmpList310.size() - 1) && tmpList310.size() > 1) { //当当前元素不是最后一个元素且容器内数量大于1
            //进行比较和合并,当符合分行原则的数据在和当前数据合并后移出次级数据并移动指针向前
            
            tmpMap3101 = (Map) tmpList310.get(k + 1);
            if ( ( ( (String) tmpMap310.get("ysjcdm")).equals( (String)
                tmpMap3101.get("ysjcdm")))
                &&
                ( ( (String) tmpMap310.get("szdm")).equals( (String)
                tmpMap3101.
                get("szdm")))
                &&
                ( ( (String) tmpMap310.get("yskmdm")).equals( (String)
                tmpMap3101.
                get("yskmdm")))
                &&
                ( ( (String) tmpMap310.get("skssksrq")).equals( (String)
                tmpMap3101.
                get("skssksrq")))
                &&
                ( ( (String) tmpMap310.get("skssjsrq")).equals( (String)
                tmpMap3101.
                get("skssjsrq")))
                &&
                ( ( (String) tmpMap310.get("xjrq")).equals( (String) tmpMap3101.
                get("xjrq")))) {
              
                smList.add(tmpMap3101); 
                
                //合并税额
              tmpMap310.put("sjse",
                            String.valueOf( (Double.parseDouble( (String)
                  tmpMap310.get("sjse"))) +
                                           (Double.parseDouble( (String)
                  tmpMap3101.
                  get("sjse")))));
              tmpList310.remove(k + 1);
             
              k--;              
            }
          }
          else if (k == (tmpList310.size() - 1) && tmpList310.size() > 1) { //当当前元素是最后一个元素且容器内数量大于1
            //do nothing
          }
          else if (tmpList310.size() == 1) { //当容器内数量等于1
            //do nothing
          }
        }
        ////3.1.2.子集分票
        tmp_ypds_dataList = new ArrayList();
        tmp_ypys_dataList = new ArrayList();
        String hjsjje = "0.00";
        log("子集分票时存在" + tmpList310.size() + "个元素");
        for (int k = 0; k < tmpList310.size(); k++) {
          tmpMap310 = (Map) tmpList310.get(k);
          if (k == 0) {
            jks = new YPDSJKS();
            jks.setSklxdm( (String) tmpMap310.get("sklxdm"));
            jks.setSklxmc( (String) tmpMap310.get("sklxmc"));
            jks.setSphm( (String) tmpMap310.get("sphm"));
            jks.setNsrmc( (String) tmpMap310.get("nsrmc"));
            jks.setSbbh( (String) tmpMap310.get("sbbh"));
            jks.setJsjdm( (String) tmpMap310.get("jsjdm"));
            jks.setYhdm( (String) tmpMap310.get("yhdm"));
            jks.setYhmc( (String) tmpMap310.get("yhmc"));
            jks.setZh( (String) tmpMap310.get("zh"));
            jks.setSwjgzzjgdm( (String) tmpMap310.get("swjgzzjgdm"));
            jks.setSwjgzzjgmc( (String) tmpMap310.get("swjgzzjgmc"));
            jks.setZsdwdm( (String) tmpMap310.get("zsswjgzzjgdm"));
            jks.setZsdwmc( (String) tmpMap310.get("zsswjgzzjgmc"));
            jks.setLrr( (String) tmpMap310.get("lrr"));
            jks.setLrrq( (String) tmpMap310.get("lrrq"));
            jks.setSbrq( (String) tmpMap310.get("sbrq"));
            jks.setXjrq( (String) tmpMap310.get("xjrq"));
          }
          if (lineCount < ApplicationConstant.JKS_YPDS_LINENUM - 1) {
            lineCount++;
            tmp_ypds_dataList.add(tmpMap310);
            hjsjje = String.valueOf(Double.parseDouble(hjsjje) +
                                    Double.
                                    parseDouble( (String) (tmpMap310.get("sjse"))));
            //从分行后的一票一税缴款书明细获取数据置入一票多税缴款书对象
            String lineKey = (String) tmpMap310.get("ysjcdm") +
                (String) tmpMap310.get("szdm") +
                (String) tmpMap310.get("yskmdm") +
                (String) tmpMap310.get("skssksrq") +
                (String) tmpMap310.get("skssjsrq") +
                (String) tmpMap310.get("xjrq");
            List tmpList312 = (List) ypysJksmxSplitByLine.get(lineKey);
            for (int m = 0; m < tmpList312.size(); m++) {
              tmp_ypys_dataList.add(tmpList312.get(m));
            }
            //
            if (k == tmpList310.size() - 1) {
              jks.setYpdsJksMx(tmp_ypds_dataList);
              jks.setYpysJksMx(tmp_ypys_dataList);
              jks.setSjjexx(hjsjje);
              //jks.setSjjedx(MoneyUtils.convert0(Double.parseDouble(hjsjje)));
              returnList.add(jks);
            }
          }
          else if (lineCount == ApplicationConstant.JKS_YPDS_LINENUM - 1) {
            tmp_ypds_dataList.add(tmpMap310);
            hjsjje = String.valueOf(Double.parseDouble(hjsjje) +
                                    Double.
                                    parseDouble( (String) (tmpMap310.get("sjse"))));
            //从分行后的一票一税缴款书明细获取数据置入一票多税缴款书对象
            String lineKey = (String) tmpMap310.get("ysjcdm") +
                (String) tmpMap310.get("szdm") +
                (String) tmpMap310.get("yskmdm") +
                (String) tmpMap310.get("skssksrq") +
                (String) tmpMap310.get("skssjsrq") +
                (String) tmpMap310.get("xjrq");
            List tmpList312 = (List) ypysJksmxSplitByLine.get(lineKey);
            for (int m = 0; m < tmpList312.size(); m++) {
              tmp_ypys_dataList.add(tmpList312.get(m));
            }
            //
            jks.setYpdsJksMx(tmp_ypds_dataList);
            jks.setYpysJksMx(tmp_ypys_dataList);
            jks.setSjjexx(hjsjje);
            //jks.setSjjedx(MoneyUtils.convert0(Double.parseDouble(hjsjje)));
            returnList.add(jks);
            //
            jks = jks.cloneJKSMainContent();
            jks.setSjjexx(null);
            jks.setSjjedx(null);
            lineCount = 0;
            hjsjje = "0.00";
            tmp_ypds_dataList = new ArrayList();
            tmp_ypys_dataList = new ArrayList();
          }
        }
      }
    }
    ///3.99.清理句柄

    //99.返回值
    return returnList;
  }
  
  
  /**
       * 此方法仅仅为本类中[public static List getYpdsJks(List JksList) throws Exception]提供使用
   * @param list 一票一税缴款书明细List
   * @param jkpzh 缴款凭证号
   * @param szsmdm 税种税目代码
   * @return 一票一税缴款书明细
   */
  private Map getYpysJksMxMap(List list, String jkpzh, String szsmdm) {
    Map map = null;
    for (int i = 0; i < list.size(); i++) {
      map = (Map) list.get(i);
      if (jkpzh.equals( (String) map.get("jkpzh")) &&
          szsmdm.equals( (String) map.get("szsmdm"))) {
        return map;
      }
    }
    return null;
  }

  /**
       * 此方法仅仅为本类中[public static List getYpdsJks(List JksList) throws Exception]提供使用
   * @param list 一票一税缴款书明细List
   * @return 按照分行规则的分类Map对象
   */
  private Map splitYpysJksMxMapByLine(List list) {
    Map map = null;
    Map returnMap = new HashMap();
    List tmpList = null;
    for (int i = 0; i < list.size(); i++) {
      map = (Map) list.get(i);
      String lineKey = (String) map.get("ysjcdm") + (String) map.get("yskmdm") +
          (String) map.get("skssksrq") + (String) map.get("skssjsrq") +
          (String) map.get("xjrq");
      if (returnMap.get(lineKey) == null) {
        tmpList = new ArrayList();
        tmpList.add(map);
        returnMap.put(lineKey, tmpList);
      }
      else {
        tmpList = (List) returnMap.get(lineKey);
        tmpList.add(map);
      }
    }
    return returnMap;
  }

  private Map splitYpysJksMxMapByLineForSkh(List list) {
    Map map = null;
    Map returnMap = new HashMap();
    List tmpList = null;
    for (int i = 0; i < list.size(); i++) {
      map = (Map) list.get(i);
      String lineKey = (String) map.get("ysjcdm") + (String) map.get("szdm") + (String) map.get("yskmdm") +
          (String) map.get("skssksrq") + (String) map.get("skssjsrq") +
          (String) map.get("xjrq");
      if (returnMap.get(lineKey) == null) {
        tmpList = new ArrayList();
        tmpList.add(map);
        returnMap.put(lineKey, tmpList);
      }
      else {
        tmpList = (List) returnMap.get(lineKey);
        tmpList.add(map);
      }
    }
    return returnMap;
  }

  /**
       * 此方法仅仅为本类中[public static List getYpdsJks(List JksList) throws Exception]提供使用
   * @param list 一票一税缴款书明细List
   * @return 一票一税缴款书明细List
   */
  private List copyYpysJksMxMap(List list) {
    List tmpList = new ArrayList();
    Map tmpMap = null;
    Map map = null;
    for (int i = 0; i < list.size(); i++) {
      tmpMap = new HashMap();
      map = (Map) list.get(i);
      for (int j = 0; j < ApplicationConstant.JKS_YPYS_MX_KEYS.length; j++) {
        tmpMap.put(ApplicationConstant.JKS_YPYS_MX_KEYS[j],
                   map.get(ApplicationConstant.JKS_YPYS_MX_KEYS[j]));
      }
      tmpList.add(tmpMap);
    }
    return tmpList;
  }

  /**
       * 此方法仅仅为本类中[public static List getYpdsJks(List JksList) throws Exception]提供使用
   * @param map 数据分类容器
   * @param str 当前分类容器中的key值
   * @param keys key的名字值
   * @param i 递归计数
   * @param dataMap 当前一票一税缴款数据明细容器
   * @param sorts 数据排序容器
   */
  private void ypdsNBranchTreeIterator(Map map, String str, String[] keys,
                                       int i, Map dataMap, List[] sorts) {
    //this.log("分类遍历计数"+i);
    Object obj = map.get(str); //从传入的枝节点获取子节点
    List tmpList = null;
    Map tmpMap = null;
    if (i == keys.length - 1) { //到达最后一层枝节点，开始处理叶节点数据并返回
      if (obj != null) {
        ( (List) obj).add(dataMap);
        //this.log(dataMap.get("sjse"));
      }
      else {
        tmpList = new ArrayList();
        sorts[i].add(str);
        map.put(str, tmpList);
        tmpList.add(dataMap);
        //this.log(dataMap.get("sjse"));
      }
      return;
    }
    else { //在非最后枝节点处理枝节点数据
      String key = (String) dataMap.get(keys[i + 1]);
      if (obj != null) {
        i += 1;
        ypdsNBranchTreeIterator( (Map) obj, key, keys, i, dataMap, sorts);
      }
      else {
        tmpMap = new HashMap();
        sorts[i].add(str);
        map.put(str, tmpMap);
        i += 1;
        ypdsNBranchTreeIterator(tmpMap, key, keys, i, dataMap, sorts);
      }
    }
  }

  protected static void log(Object str) {
    if (ApplicationConstant.DEBUG_FLAG) {
      System.out.println("[WSSB InterFace DEBUG " + (new Date()) +
                         "] " + str);
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
	public static String getYskmFcblmc(String tmpYskmdm, String tmpSzsmdm, String tmpSwjgzzjgdm) throws BaseException {

		Connection conn = null;
		CallableStatement proc = null;
		String yskmFcblmc = "";
		String yskmfpblmc_err = "";
		
		String yskmdm = "";
		String szsmdm = "";
		String swjgzzjgdm = "";
		
		try {
			// 获得数据库连接
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);

			yskmdm = tmpYskmdm;
			szsmdm = tmpSzsmdm;
			swjgzzjgdm = tmpSwjgzzjgdm;

			// 调用存储过，存储过程第一个参数为年度，允许为空
			proc = conn.prepareCall("{ call jkdb.getYskmFpblMc(?,?,?,?,?,?) }");
			// 输入参数
			proc.setString(1, "");
			proc.setString(2, yskmdm);
			proc.setString(3, szsmdm);
			proc.setString(4, swjgzzjgdm);

			// 出口参数
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			// 调用存储过程
			proc.execute();

			// 获取结果
			yskmFcblmc = proc.getString(5);
			yskmfpblmc_err = proc.getString(6);

			// 判断是否已经查询到预算级次名称
			// if(yskmfpblmc_err!=null || !"".equals(yskmfpblmc_err) || !"null".equals(yskmfpblmc_err)){
			if (yskmfpblmc_err != null) {
				System.out.println("重新获取预算级次分配比例名称错误：" + yskmfpblmc_err);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e, "调用存储过程jkdb.getYskmFpblMc()操作失败!");
		} finally {
			if (proc != null) {
				try {
					proc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBResource.destroyConnection(conn);
		}
		return yskmFcblmc;
	}

}