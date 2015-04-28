/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.processor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkxx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx;
import com.ttsoft.bjtax.shenbao.model.domain.GtgshZt;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjscscForm;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.exception.ZwclException;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import java.util.GregorianCalendar;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;
import java.sql.*;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件生成上传Processor</p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjscscProcessor
    implements Processor {
  public YhdkwjscscProcessor() {
  }

  /**
   * 通用处理调度模块
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws com.ttsoft.framework.exception.BaseException
   */
  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException {
    Object result = null;

    if (vo == null) {
      throw new NullPointerException();
    }

    switch (vo.getAction()) {
      case CodeConstant.SMSB_SAVEACTION:
        result = doSave(vo);
        break;
      case CodeConstant.SMSB_SHOWACTION:
        result = doShow(vo);
        break;
      case CodeConstant.SMSB_UPLOADACTION:
        result = doUpdateZt(vo);
        break;
      case CodeConstant.SMSB_UPDATEACTION:
        result = doUpdateYhqzt(vo);
        break;

      default:
        throw new UnsupportedOperationException(
            "Method process() not yet implemented.");
    }
    return result;
  }

  /**
   * 页面显示准备
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doShow(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjscscForm form = (YhdkwjscscForm) vo.getData();
    ResourceBundle rb = ResourceBundle.getBundle(
        "com.ttsoft.bjtax.smsb.ApplicationResources");
    String qxdmOfBccb = rb.getString("gghsb.qxdm.bccb"); //划属北京商行的区县
    String qxdmOfNxs = rb.getString("gghsb.qxdm.nxs"); //划属农信社的区县
    String qxdms = qxdmOfBccb + "," + qxdmOfNxs; //所有区县
    form.setQxdmOfBccb(qxdmOfBccb);
    form.setQxdmOfNxs(qxdmOfNxs);
    UserData userData = vo.getUserData();
    String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
    List qxList = new ArrayList(); //区县列表
    List nyList = new ArrayList(); //年月列表
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);

      // 1. 查询获得可选的区县列表
      StringBuffer sqlBuffer = new StringBuffer();
      if (iQxdm.equals(GghConstant.QXDM_SJ)) { //市局可以生成全部区县局的数据
        sqlBuffer.append("SELECT SWJGZZJGMC DESCR, SWJGZZJGDM CODE FROM")
            .append(" DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM IN (" + qxdms + ")")
            .append(" ORDER BY SWJGZZJGDM");
      }
      else {
        sqlBuffer.append("SELECT SWJGZZJGMC DESCR, SWJGZZJGDM CODE FROM")
            .append(" DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '" + iQxdm + "00'")
            .append(" ORDER BY SWJGZZJGDM");
      }
      try {
        ResultSet rs = da.querySQL(sqlBuffer.toString());
        while (rs.next()) {
          LabelValueBean bean = new LabelValueBean(rs.getString("DESCR"),
              rs.getString("CODE"));
          qxList.add(bean);
        }
        if (rs != null) {
          rs.close();
        }
      }
      catch (Exception ex) {
        throw new ApplicationException("获取区县局列表失败");
      }

      // 2. 生成可选的年月列表
      String year = SfDateUtil.getDate().substring(0, 4); //当前年
      String month = SfDateUtil.getDate().substring(4, 6); //当前月
      // 2.1 判断本年本月
      //非市局用户 判断是否已生成过，有则提示需要确认重新生成
      if (!iQxdm.equals(GghConstant.QXDM_SJ)) {
        StringBuffer queryYhBuf1 = new StringBuffer(
            "SELECT * FROM SFDB.SF_JL_GTGSH_ZT WHERE QXDM='")
            .append(iQxdm).append("' AND ND='").append(year)
            .append("' AND YD='").append(month).append("'");
        try {
          ResultSet rs1 = da.querySQL(queryYhBuf1.toString());
          if (rs1.next()) {
            form.setYscNY(year + month);
          }
          if (rs1 != null) {
            rs1.close();
          }
        }
        catch (Exception ex1) {
          throw new ApplicationException("获取征期列表失败");
        }
      }

      if (! (year + month).equals("200501")) {
        nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));
      }

      // 2.2 判断下月
      //首先得到下月 对应的 年月值

      if (month.equals("12")) {
        String iYear = String.valueOf(Integer.parseInt(year) + 10001);
        year = iYear.substring(iYear.length() - 4, iYear.length());
        month = "01";
      }
      else {
        String tMonth = String.valueOf(Integer.parseInt(month) + 101);
        month = tMonth.substring(tMonth.length() - 2, tMonth.length());
      }
      //非市局用户 判断是否银行已取前月数据 有则 前月不可再生成文件
      if (!iQxdm.equals(GghConstant.QXDM_SJ)) {
        StringBuffer queryYhBuf2 = new StringBuffer(
            "SELECT * FROM SFDB.SF_JL_GTGSH_ZT WHERE QXDM='")
            .append(iQxdm).append("' AND ND='").append(year)
            .append("' AND YD='").append(month).append("'");
        try {
          ResultSet rs2 = da.querySQL(queryYhBuf2.toString());
          if (rs2.next()) {
            if (rs2.getString("yhqzt").equals("N")) {
              //添加已生成的年月
              if (!form.getYscNY().equals("")) {
                form.setYscNY(form.getYscNY() + "|" + year + month);
              }
              else {
                form.setYscNY(year + month);
              }
            }
          }
          if (rs2 != null) {
            rs2.close();
          }
        }
        catch (Exception ex2) {
          throw new ApplicationException("获取征期列表失败");
        }
      }

      nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));

      form.setNyList(nyList); //可生成文件的年月列表
      form.setQxList(qxList); //可操作的区县列表
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 保存并封装扣款信息文件
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doSave(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjscscForm form = (YhdkwjscscForm) vo.getData();
    UserData userData = vo.getUserData();
//    List xxList = new ArrayList(); //明细信息list
    List hzxxList = new ArrayList(); //汇总信息list
    String totalAmount = "0"; //总金额
    String ny = form.getNy(); //年月
    String nd = ny.substring(0, 4); //年度
    String yd = ny.substring(4, 6); //月度
    String ssny = ""; // 所属年月
    String ssnd = ""; // 所属年度
    String ssyd = ""; // 所属月度
    if (yd.equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(nd) + 9999).substring(1, 5);
      ssyd = "12";
    }
    else {
      ssnd = nd;
      ssyd = String.valueOf(Integer.parseInt(yd) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    String cjr = userData.getYhid();
    Timestamp cjrq = SfTimeUtil.getNowTimestamp();
    Timestamp skssksrq = SfTimeUtil.getTimestamp(new StringBuffer(ssny).append(
        "01").toString());
    Timestamp skssjsrq = SfTimeUtil.getTimestamp(new StringBuffer(ssny).append(
        getmaxDay(
        Integer.parseInt(ssnd), Integer.parseInt(ssyd) - 1)).toString());
    String qxdm = form.getQxdm().substring(0, 2); //2位 区县代码
    String yhdm = form.getYhdm(); //银行代码

    try {
      long time1 = System.currentTimeMillis();
      System.out.print(" 查询开始... ");

      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("SELECT T2.JSJDM jsjdm, T3.NSRMC nsrmc, T1.YHDM yhdm,")
          .append(" T1.ZH zh, T2.SZSMDM szsmdm, ")
          .append(" T2.SJRD sjrd, T2.YNSRD sjje, T3.SWJGZZJGDM swjgzzjgdm,")
          .append(" T3.JYDZLXDM jydzlxdm, T3.DJZCLXDM djzclxdm,T3.LSGXDM")
          .append(" lsgxdm, T3.GJBZHYDM gjbzhydm, T3.SCJXDM scjxdm")
          .append(" FROM SFDB.SF_JL_SKRKFS T1, SFDB.SF_JL_DQDEDLMX1 T2,")
          .append(" djdb.dj_jl_jbsj T3")
          .append(" WHERE T3.JSJDM = T2.JSJDM")
          .append(" AND T1.ZH IS NOT NULL")
          .append(" AND T1.SKRKFSDM = '").append(GghConstant.SKRKFSDM_SANFANG) //入库方式为三方协议
//          .append("' AND T2.RDQRQ < TO_DATE('").append("200502")
//          .append("01','YYYYMMDD') + 1") //核定有效 自 当月2日以前的
          .append("' AND T2.ZSFSDM='").append(GghConstant.ZSFS_DQDEDL_DE) // 征收方式为定额
          .append("' AND T2.YNSRD > 0") // 核定金额
          .append(" AND T2.SJRD > 0")
          .append(" AND T1.JSJDM = T3.JSJDM")
          .append(" AND T3.NSRZT = '").append(GghConstant.NSRZT_ZC) //纳税人状态为正常的
          .append("' AND T3.SWJGZZJGDM LIKE '" + qxdm + "%' ORDER BY T1.JSJDM");
      ResultSet rs = null;
      try {
        rs = da.querySQL(sqlBuffer.toString());
      }
      catch (BaseException ex7) {
        throw new ApplicationException("查询扣款数据失败");
      }

      PreparedStatement statement = null;
      Map iMap = new HashMap(); //缴款凭证号缓存
      iMap.put("JSJDM", "");
      iMap.put("HS", "0");
      iMap.put("XH", "0");
      List ysjcList = getYsjcObj(qxdm, conn); //预算级次缓存
      List codeList = new ArrayList(); //预算科目缓存

      long time2 = System.currentTimeMillis();
      System.out.println(" done");
      System.out.print(" 数据准备开始... ");
      long ysjcTime =0;
      long yskmTime = 0;
      long jkpzhTime = 0;
//      int iii = 0;
      while (rs.next()) {
//        iii++;
//        if (iii > 300) { //取得条数
//          break;
//        }
        for (int ii = 0; ii < 1; ii++) { //循环for压力测试 每条循环次数
          totalAmount = getAddResult(totalAmount, rs.getString("sjje")); //累加总金额
          String jsjdm = rs.getString("jsjdm"); //计算机代码
          String djzclx = rs.getString("djzclxdm"); //登记注册类型代码
          String szsmdm = rs.getString("szsmdm"); //税种税目代码
          String gjbzhydm = rs.getString("gjbzhydm"); //国家标准行业代码

          // 1. 扣款信息表对象
          Yhkkhzxx xxOr = new Yhkkhzxx();
          xxOr.setCjr(cjr);
          xxOr.setLrr(cjr);
          xxOr.setLrrq(cjrq);
          xxOr.setCjrq(cjrq);
          xxOr.setNd(nd);
          xxOr.setYd(yd);
          xxOr.setQxdm(qxdm);
          xxOr.setJsjdm(jsjdm);
          xxOr.setNsrmc(rs.getString("nsrmc"));
          xxOr.setYhdm(rs.getString("yhdm"));
          xxOr.setZh(rs.getString("zh"));
          xxOr.setSzsmdm(szsmdm);
          xxOr.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", szsmdm));
          if (rs.getBigDecimal("sjrd") != null) {
            xxOr.setSjrd(rs.getBigDecimal("sjrd"));
          }
          if (rs.getBigDecimal("sjje") != null) {
            xxOr.setSjje(rs.getBigDecimal("sjje"));
          }
          xxOr.setSkssksrq(skssksrq);
          xxOr.setSkssjsrq(skssjsrq);
          xxOr.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
          xxOr.setSwjgzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
              "swjgzzjgdm", xxOr.getSwjgzzjgdm(), "swjgzzjgmc"));
          xxOr.setGkzzjgdm(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
              "swjgzzjgdm", xxOr.getSwjgzzjgdm(), "gkjhh"));
          xxOr.setGkzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
              "swjgzzjgdm", xxOr.getSwjgzzjgdm(), "skgk"));
          xxOr.setScjxdm(rs.getString("scjxdm")); //所处街乡
          xxOr.setDjzclxdm(rs.getString("djzclxdm"));
          xxOr.setGjbzhydm(rs.getString("gjbzhydm"));
          xxOr.setLsgxdm(rs.getString("lsgxdm"));
          xxOr.setJydzlxdm(rs.getString("jydzlxdm"));

long start = System.currentTimeMillis();
          // 缓存取预算级次
          try {
            Ysjc ysjc = getYsjcdm(xxOr.getJsjdm(), xxOr.getSzsmdm(), ysjcList);
            xxOr.setYsjcdm(ysjc.getYsjcdm());
            xxOr.setYsjcmc(ysjc.getYsjcmc());
          }
          catch (BaseException ex6) {
            throw new ApplicationException("获取预算级次失败:" + ex6.getMessage());
          }
long ysjct = System.currentTimeMillis();
          ysjcTime += ysjct-start;
          // 缓存获取预算科目
          Yskm yskm = new Yskm();
          try {
            yskm = getYskmdm(szsmdm, djzclx, gjbzhydm, xxOr.getYsjcdm(),
                             codeList);
//            yskm.setQxfcbl(new BigDecimal(0.5));
//            yskm.setSjfcbl(new BigDecimal(0.5));
//            yskm.setYskmdm("123456");
            xxOr.setYskmdm(yskm.getYskmdm());
          }
          catch (BaseException ex1) {
            throw new ApplicationException("获取预算科目失败:" + ex1.getMessage());
          }
long yskmt = System.currentTimeMillis();
            yskmTime += yskmt-ysjct;
          /* 调用申报接口得缴款凭证号 */
          try {
            xxOr.setJkpzh(getJkpzh(jsjdm, ssny.substring(2, 6), iMap));
          }
          catch (BaseException ex2) {
            throw new ApplicationException("获取缴款凭证号失败:" + ex2.getMessage());
          }
long jkpzht = System.currentTimeMillis();
          jkpzhTime += jkpzht-yskmt;
          // 2. 扣款回执表对象
//          Yhkkhzxx hzxxOr = new Yhkkhzxx();
//          hzxxOr.setCjr(cjr);
//          hzxxOr.setLrr(cjr);
//          hzxxOr.setLrrq(cjrq);
//          hzxxOr.setCjrq(cjrq);
//          hzxxOr.setNd(xxOr.getNd());
//          hzxxOr.setYd(xxOr.getYd());
//          hzxxOr.setQxdm(xxOr.getQxdm());
//          hzxxOr.setJsjdm(xxOr.getJsjdm());
//          hzxxOr.setNsrmc(xxOr.getNsrmc());
//          hzxxOr.setYhdm(xxOr.getYhdm());
//          hzxxOr.setZh(xxOr.getZh());
//          hzxxOr.setSzsmdm(xxOr.getSzsmdm());
//          hzxxOr.setSzsmmc(xxOr.getSzsmmc());
//          hzxxOr.setSjrd(xxOr.getSjrd());
//          hzxxOr.setSjje(xxOr.getSjje());
//          hzxxOr.setSkssksrq(xxOr.getSkssksrq());
//          hzxxOr.setSkssjsrq(xxOr.getSkssjsrq());
//          hzxxOr.setSwjgzzjgdm(xxOr.getSwjgzzjgdm());
//          hzxxOr.setSwjgzzjgmc(xxOr.getSwjgzzjgmc());
//          hzxxOr.setGkzzjgdm(xxOr.getGkzzjgdm());
//          hzxxOr.setGkzzjgmc(xxOr.getGkzzjgmc());
//          hzxxOr.setYsjcdm(xxOr.getYsjcdm());
//          hzxxOr.setYsjcmc(xxOr.getYsjcmc());
//          hzxxOr.setYskmdm(xxOr.getYskmdm());
          xxOr.setSjfc(yskm.getSjfcbl() == null ? new BigDecimal(0) :
                         yskm.getSjfcbl());
          xxOr.setQjfc(yskm.getQxfcbl() == null ? new BigDecimal(0) :
                         yskm.getQxfcbl());
//          hzxxOr.setJkpzh(xxOr.getJkpzh());
//          hzxxOr.setScjxdm(xxOr.getScjxdm());
//          hzxxOr.setDjzclxdm(xxOr.getDjzclxdm());
//          hzxxOr.setGjbzhydm(xxOr.getGjbzhydm());
//          hzxxOr.setLsgxdm(xxOr.getLsgxdm());
//          hzxxOr.setJydzlxdm(xxOr.getJydzlxdm());
          xxOr.setKkbz(GghConstant.KKBZ_DKK); //待扣款

          //加入该条扣款信息(汇总，明细)
          hzxxList.add(xxOr);
//          xxList.add(xxOr);
        }
      } //循环for压力测试

      if (rs != null) {
        rs.close();
      }
      System.gc();
      System.out.println(" done");
      System.out.print(" 状态表数据准备及清理旧的相关数据开始... ");

      // 3. 个体工商户状态表对象
      GtgshZt gtgshZtOr = new GtgshZt();
      gtgshZtOr.setNd(nd);
      gtgshZtOr.setYd(yd);
      gtgshZtOr.setQxdm(qxdm);
      gtgshZtOr.setZjls(hzxxList.size());
      gtgshZtOr.setZje(new BigDecimal(totalAmount).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      gtgshZtOr.setDsczt("N"); //地税上传状态
      gtgshZtOr.setYhqzt("N"); //银行取状态
      gtgshZtOr.setYckkzt("N"); //一次扣款获得状态
      gtgshZtOr.setYckknrzt("N"); //一次扣款内容状态
      gtgshZtOr.setEckkzt("N"); //二次扣款获得状态
      gtgshZtOr.setEckknrzt("N"); //二次扣款内容状态
      gtgshZtOr.setCjr(cjr);
      gtgshZtOr.setLrr(cjr);
      gtgshZtOr.setLrrq(cjrq);
      gtgshZtOr.setCjrq(cjrq);

      // 4. 清除该区县该月扣款信息相关表
      StringBuffer deleteConditionBuf = new StringBuffer(" qxdm='")
          .append(qxdm)
          .append("' and nd='").append(nd)
          .append("' and yd='").append(yd).append("'");
      String deleteCondition = deleteConditionBuf.toString();
      try {
        da.delete(deleteCondition, new Yhkkxx());
        da.delete(deleteCondition, new Yhkkhzxx());
        da.delete(deleteCondition, new GtgshZt());
      }
      catch (BaseException ex5) {
        throw new ApplicationException("清除旧的扣款数据失败");
      }

      long time3 = System.currentTimeMillis();
      System.out.println(" done");
      System.out.print(" 保存扣款信息开始... ");

      // 5. 保存该区县该月扣款信息相关表
      try {
//        da.insert(xxList);
        insertKkxx(conn, hzxxList);
      }
      catch (BaseException ex3) {
        throw new ApplicationException("保存扣款信息失败");
      }
//      hzxxList=null;
//      System.gc();
      System.out.println(" done");
      System.out.print(" 保存扣款回执信息开始... ");

      long time4 = System.currentTimeMillis();
      try {
//        da.insert(hzxxList);
        insertKkhzxx(conn, hzxxList);
      }
      catch (BaseException ex3) {
        throw new ApplicationException("保存扣款回执信息失败");
      }
//      hzxxList=null;
//      System.gc();
      System.out.println(" done");
      System.out.print(" 封装上传文件开始... ");

      try {
        da.insert(gtgshZtOr);
      }
      catch (Exception ex4) {
        throw new ApplicationException("保存扣款状态信息失败");
      }

      // 6. 封装上传文件内容
      String fileNamePos = new StringBuffer(ssny).append(qxdm)
          .append(yhdm).append(GghConstant.FILE_POSTFIX_DATA).toString();
      // 6.1 封装明细文件Map
      HashMap kkmxMap = new HashMap();
      //明细文件名  命名规则: TM + 年 + 月 + 区县代码 + 银行代码 .txt
      kkmxMap.put("FILENAME",
                  new StringBuffer(GghConstant.FILE_PREFIX_MX).append(
          fileNamePos).toString());
      //明细内容list
      kkmxMap.put("CONTENT", hzxxList);

      // 6.2 封装汇总文件Map
      HashMap kkhzMap = new HashMap();
      //汇总文件名  命名规则: TZ + 年 + 月 + 区县代码 + 银行代码 .txt
      kkhzMap.put("FILENAME",
                  new StringBuffer(GghConstant.FILE_PREFIX_HZ).append(
          fileNamePos).toString());
      //总记录数
      kkhzMap.put("COUNT", String.valueOf(hzxxList.size()));
      //总金额
      kkhzMap.put("TOTALAMOUNT", totalAmount);

      // 6.3 得到最终上传HashMap
      HashMap ftpMap = new HashMap();
      ftpMap.put("MXWJ", kkmxMap);
      ftpMap.put("HZWJ", kkhzMap);
      form.setFtpMap(ftpMap);
      long time5 = System.currentTimeMillis();
      form.setHs( (String) iMap.get("HS"));

      System.out.println(" done");
      System.out.println(qxdm + " [银行代扣文件上传] 查询数据耗时:  " + (time2 - time1) + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 生成扣款耗时:  " + (time3 - time2) + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 预算级次耗时:  " + ysjcTime + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 预算科目耗时:  " + yskmTime + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 缴款书号耗时:  " + jkpzhTime + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 保存扣款耗时:  " + (time4 - time3) + " 毫秒");
      System.out.println(qxdm + " [银行代扣文件上传] 保存回执耗时:  " + (time5 - time4) + " 毫秒");
      System.gc();
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 更新区县局扣款状态表
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doUpdateZt(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjscscForm form = (YhdkwjscscForm) vo.getData();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sqlBuf = new StringBuffer(
          "UPDATE SFDB.SF_JL_GTGSH_ZT SET DSCZT='Y'")
          .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
          .append("' AND ND='").append(form.getNy().substring(0, 4))
          .append("' AND YD ='").append(form.getNy().substring(4, 6))
          .append("'");
      da.updateSQL(sqlBuf.toString());

      UserData userData = vo.getUserData();
      String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码

      //如果不是市局用户则,添加已生成的年月
      if (!iQxdm.equals(GghConstant.QXDM_SJ)) {
        if (!form.getYscNY().equals("")) {
          form.setYscNY(form.getYscNY() + "|" + form.getNy());
        }
        else {
          form.setYscNY(form.getNy());
        }
      }
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 更新区县局扣款状态表 银行取状态
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doUpdateYhqzt(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjscscForm form = (YhdkwjscscForm) vo.getData();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //选择银行对应的区县
      String qxdms = null;
      if (form.getYhdm().equals(GghConstant.YHDM_BJ)) {
        qxdms = form.getQxdmOfBccb();
      }
      else {
        qxdms = form.getQxdmOfNxs();
      }
      StringBuffer sqlBuf = new StringBuffer(
          "UPDATE SFDB.SF_JL_GTGSH_ZT SET YHQZT='Y'")
          .append(" WHERE QXDM||'00' IN (").append(qxdms)
          .append(") AND ND='").append(form.getNy().substring(0, 4))
          .append("' AND YD ='").append(form.getNy().substring(4, 6))
          .append("'");
      da.updateSQL(sqlBuf.toString());

      UserData userData = vo.getUserData();
      String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码

      //如果不是市局用户则,添加已生成的年月
      if (!iQxdm.equals(GghConstant.QXDM_SJ)) {
        if (!form.getYscNY().equals("")) {
          form.setYscNY(form.getYscNY() + "|" + form.getNy());
        }
        else {
          form.setYscNY(form.getNy());
        }
      }
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 得到指定年和月对应的最大的天数
   * @param year 指定年
   * @param month 指定月
   * @return 指定年和月对应的最大的天数
   */
  private static int getmaxDay(final int year, final int month) {
    Calendar calendar = new GregorianCalendar(year, month, 1);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  /**
   * 功能：金额相加计算(小数点后保留两位)
   * @param addend 加数
   * @param augend 被加数
   * @return 结果
   */
  private String getAddResult(String addend, String augend) {
    String result = "";
    //输入校验
    if (addend == null || addend.equals("")) {
      addend = "0";
    }
    if (augend == null || augend.equals("")) {
      augend = "0";
    }

    //计算
    BigDecimal iAddend = new BigDecimal(addend);
    BigDecimal iAugend = new BigDecimal(augend);
    result = iAddend.add(iAugend).setScale(2, BigDecimal.ROUND_HALF_UP).
        toString();
    return result;
  }

  /**
   * 插入扣款信息
   * @param conn
   * @param xxList 扣款信息列表
   * @throws BaseException
   */
  private void insertKkxx(Connection conn, List xxList) throws BaseException {
    if (xxList.size() == 0) {
      return;
    }
    Yhkkhzxx yhkkxxOr = (Yhkkhzxx) xxList.get(0);
    PreparedStatement statement = null;
    StringBuffer insertSql = new StringBuffer(
        "insert into SFDB.SF_JL_GTGSH_YHKKXX (JKPZH, JSJDM, NSRMC, YHDM, ZH, SZSMDM, SZSMMC, SJJE, SKSSKSRQ, SKSSJSRQ, YSKMDM, YSJCDM, YSJCMC, GKZZJGDM, GKZZJGMC, SWJGZZJGDM, SWJGZZJGMC, QXDM, ND, YD, CJR, CJRQ, LRR, LRRQ, SCJXDM, SJRD, JYDZLXDM, GJBZHYDM, DJZCLXDM, LSGXDM) values (")
        // 1.JKPZH, 2.JSJDM, 3.NSRMC, 4.YHDM, 5.ZH,
        .append("?,?,?,?,?,")
        // 6.SZSMDM, 7.SZSMMC, 8.SJJE, SKSSKSRQ, SKSSJSRQ,
        .append("?,?,?,to_date('")
        .append(yhkkxxOr.getSkssksrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),to_date('")
        .append(yhkkxxOr.getSkssjsrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),")
        // 9.YSKMDM, 10.YSJCDM, 11.YSJCMC, 12.GKZZJGDM, 13.GKZZJGMC,
        .append("?,?,?,?,?,")
        // 14.SWJGZZJGDM, 15.SWJGZZJGMC, QXDM, ND, YD,
        .append("?,?,'")
        .append(yhkkxxOr.getQxdm()).append("','")
        .append(yhkkxxOr.getNd()).append("','")
        .append(yhkkxxOr.getYd()).append("',")
        //CJR, CJRQ, LRR, LRRQ, 16.SCJXDM,
        .append("'")
        .append(yhkkxxOr.getCjr()).append("',to_date('")
        .append(yhkkxxOr.getCjrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),'")
        .append(yhkkxxOr.getLrr()).append("',to_date('")
        .append(yhkkxxOr.getLrrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),?,")
        // 17.SJRD, 18.JYDZLXDM, 19.GJBZHYDM, 20.DJZCLXDM, 21.LSGXDM
        .append("?,?,?,?,?")
        .append(")");
    try {
      long count = 0;
      statement = conn.prepareStatement(insertSql.toString());
      for (int i = 0; i < xxList.size(); i++) {
        count++;
        yhkkxxOr = (Yhkkhzxx) xxList.get(i);
        statement.setString(1, yhkkxxOr.getJkpzh());
        statement.setString(2, yhkkxxOr.getJsjdm());
        statement.setString(3, yhkkxxOr.getNsrmc());
        statement.setString(4, yhkkxxOr.getYhdm());
        statement.setString(5, yhkkxxOr.getZh());
        statement.setString(6, yhkkxxOr.getSzsmdm());
        statement.setString(7, yhkkxxOr.getSzsmmc());
        statement.setBigDecimal(8, new BigDecimal(yhkkxxOr.getSjje()));
        statement.setString(9, yhkkxxOr.getYskmdm());
        statement.setString(10, yhkkxxOr.getYsjcdm());
        statement.setString(11, yhkkxxOr.getYsjcmc());
        statement.setString(12, yhkkxxOr.getGkzzjgdm());
        statement.setString(13, yhkkxxOr.getGkzzjgmc());
        statement.setString(14, yhkkxxOr.getSwjgzzjgdm());
        statement.setString(15, yhkkxxOr.getSwjgzzjgmc());
        statement.setString(16, yhkkxxOr.getScjxdm());
        statement.setBigDecimal(17, new BigDecimal(yhkkxxOr.getSjrd()));
        statement.setString(18, yhkkxxOr.getJydzlxdm());
        statement.setString(19, yhkkxxOr.getGjbzhydm());
        statement.setString(20, yhkkxxOr.getDjzclxdm());
        statement.setString(21, yhkkxxOr.getLsgxdm());
        statement.addBatch();
        yhkkxxOr = null;
        //每1万条执行一次
        if (count >= 100) {
          statement.executeBatch();
          statement.clearBatch();
          count = 0;
        }
      }
      statement.executeBatch();
      statement.clearBatch();
      statement.close();
      statement = null;
      System.gc();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      throw new ApplicationException("保存扣款信息失败");
    }
  }

  /**
   * 插入扣款回执信息
   * @param conn
   * @param xxList 扣款信息列表
   * @throws BaseException
   */
  private void insertKkhzxx(Connection conn, List hzxxList) throws
      BaseException {
    if (hzxxList.size() == 0) {
      return;
    }
    Yhkkhzxx yhkkhzxxOr = (Yhkkhzxx) hzxxList.get(0);
    PreparedStatement statement = null;
    StringBuffer insertSql = new StringBuffer(
        "insert into SFDB.SF_JL_GTGSH_YHKKHZXX (JKPZH, JSJDM, NSRMC, YHDM, ZH, SZSMDM, SZSMMC, SJJE, SKSSKSRQ, SKSSJSRQ, YSKMDM, YSJCDM, YSJCMC, GKZZJGDM, GKZZJGMC, SWJGZZJGDM, SWJGZZJGMC, KKBZ, KKRQ, KKDWDM, BCGYYDM, QXDM, ND, YD, CJR, CJRQ, LRR, LRRQ, SCJXDM, RKJE, SJRD, JYDZLXDM, GJBZHYDM, DJZCLXDM, LSGXDM, SJFC, QJFC) values (")
        // 1.JKPZH, 2.JSJDM, 3.NSRMC, 4.YHDM, 5.ZH,
        .append("?,?,?,?,?,")
        // 6.SZSMDM, 7.SZSMMC, 8.SJJE, SKSSKSRQ,SKSSJSRQ,
        .append("?,?,?,to_date('")
        .append(yhkkhzxxOr.getSkssksrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),to_date('")
        .append(yhkkhzxxOr.getSkssjsrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),")
        // 9.YSKMDM, 10.YSJCDM, 11.YSJCMC, 12.GKZZJGDM, 13.GKZZJGMC,
        .append("?,?,?,?,?,")
        // 14.SWJGZZJGDM, 15.SWJGZZJGMC, KKBZ, KKRQ, KKDWDM,
        .append("?,?,'00',null,null,")
        // BCGYYDM,QXDM, ND, YD, CJR,
        .append("null,'")
        .append(yhkkhzxxOr.getQxdm()).append("','")
        .append(yhkkhzxxOr.getNd()).append("','")
        .append(yhkkhzxxOr.getYd()).append("','")
        .append(yhkkhzxxOr.getCjr()).append("',to_date('")
        //CJRQ, LRR, LRRQ, 16.SCJXDM, RKJE,
        .append(yhkkhzxxOr.getCjrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),'")
        .append(yhkkhzxxOr.getLrr()).append("',to_date('")
        .append(yhkkhzxxOr.getLrrq().toString().substring(0, 19))
        .append("', 'yyyy-mm-dd hh24:mi:ss'),?,0,")
        // 17.SJRD, 18.JYDZLXDM, 19.GJBZHYDM, 20.DJZCLXDM, 21.LSGXDM,
        .append("?,?,?,?,?,")
        // 22.SJFC, 23.QJFC
        .append("?,?)");
    try {
      long count = 0;
      statement = conn.prepareStatement(insertSql.toString());
      for (int i = 0; i < hzxxList.size(); i++) {
        count++;
        yhkkhzxxOr = (Yhkkhzxx) hzxxList.get(i);
        statement.setString(1, yhkkhzxxOr.getJkpzh());
        statement.setString(2, yhkkhzxxOr.getJsjdm());
        statement.setString(3, yhkkhzxxOr.getNsrmc());
        statement.setString(4, yhkkhzxxOr.getYhdm());
        statement.setString(5, yhkkhzxxOr.getZh());
        statement.setString(6, yhkkhzxxOr.getSzsmdm());
        statement.setString(7, yhkkhzxxOr.getSzsmmc());
        statement.setBigDecimal(8, new BigDecimal(yhkkhzxxOr.getSjje()));
        statement.setString(9, yhkkhzxxOr.getYskmdm());
        statement.setString(10, yhkkhzxxOr.getYsjcdm());
        statement.setString(11, yhkkhzxxOr.getYsjcmc());
        statement.setString(12, yhkkhzxxOr.getGkzzjgdm());
        statement.setString(13, yhkkhzxxOr.getGkzzjgmc());
        statement.setString(14, yhkkhzxxOr.getSwjgzzjgdm());
        statement.setString(15, yhkkhzxxOr.getSwjgzzjgmc());
        statement.setString(16, yhkkhzxxOr.getScjxdm());
        statement.setBigDecimal(17, new BigDecimal(yhkkhzxxOr.getSjrd()));
        statement.setString(18, yhkkhzxxOr.getJydzlxdm());
        statement.setString(19, yhkkhzxxOr.getGjbzhydm());
        statement.setString(20, yhkkhzxxOr.getDjzclxdm());
        statement.setString(21, yhkkhzxxOr.getLsgxdm());
        statement.setBigDecimal(22, new BigDecimal(yhkkhzxxOr.getSjfc()));
        statement.setBigDecimal(23, new BigDecimal(yhkkhzxxOr.getQjfc()));
        statement.addBatch();
        yhkkhzxxOr = null;
        //每1万条执行一次
        if (count >= 100) {
          statement.executeBatch();
          statement.clearBatch();
          count = 0;
        }
      }
      statement.executeBatch();
      statement.clearBatch();
      statement.close();
      statement = null;
      System.gc();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      throw new ApplicationException("保存扣款回执信息失败");
    }
  }

  /**
   * 查询预算级次认定结果集
   * @param qxdm 区县代码
   * @param conn
   * @return
   * @throws BaseException
   */
  private List getYsjcObj(String qxdm, Connection conn) throws
      BaseException {
    List list = new ArrayList();
    try {



      StringBuffer sqlStringBuffer = new StringBuffer("SELECT T.YSJCDM, T.JSJDM, SUBSTR(T.SZSMDM, 0, 2) SZ,T.RDRQ FROM  SFDB.SF_JL_TSYSJCMX T , ( SELECT DISTINCT(SUBSTR(T2.SZSMDM, 0, 2)) SZSMDM FROM SFDB.SF_JL_SKRKFS T1, SFDB.SF_JL_DQDEDLMX1 T2 WHERE T1.JSJDM=T2.JSJDM AND T1.SKRKFSDM='")
          .append(GghConstant.SKRKFSDM_SANFANG).append("' AND T2.ZSFSDM='")
          .append(GghConstant.ZSFS_DQDEDL_DE).append("' AND T1.SWJGZZJGDM LIKE '")
          .append(qxdm).append("%')  D ").append(" WHERE T.RDRQ <= TO_DATE('").append(SfDateUtil.getDate())
          .append(" 23:59:59', 'yyyy-MM-dd hh24:mi:ss') and T.SWJGZZJGDM LIKE '")
          .append(qxdm).append("%' ORDER BY JSJDM,SZ");

//          .append(CodeConstant.YSJC_GTGSH) // 只要非地方级的认定
//          .append("' AND SUBSTR(T.SZSMDM, 0, 2) IN ( SELECT DISTINCT(SUBSTR(T2.SZSMDM, 0, 2)) FROM SFDB.SF_JL_SKRKFS T1, SFDB.SF_JL_DQDEDLMX1 T2 WHERE T1.JSJDM=T2.JSJDM AND T1.SKRKFSDM='")
//          .append(GghConstant.SKRKFSDM_SANFANG) //三方协议扣款
//          .append("' AND T2.ZSFSDM='")
//          .append(GghConstant.ZSFS_DQDEDL_DE) //定额征收
//          .append("' AND T1.SWJGZZJGDM LIKE '").append(qxdm)
//          .append("%') AND T.RDRQ <= TO_DATE('").append(SfDateUtil.getDate())
//          .append(" 23:59:59', 'yyyy-MM-dd hh24:mi:ss') AND T.SWJGZZJGDM LIKE '")
//          .append(qxdm).append("%' ORDER BY JSJDM,SZ");

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());

      while (rs.next()) {
        if (list.size() == 0) {
          Map iMap = new HashMap();
          iMap.put("JSJDM", rs.getString("JSJDM"));
          iMap.put("SZ", rs.getString("SZ"));
          iMap.put("YSJCDM", rs.getString("YSJCDM"));
          iMap.put("RDRQ", rs.getTimestamp("RDRQ"));
          list.add(iMap);
        }
        else {
          for (int i = 0; i < list.size(); i++) {
            Map iMap = (HashMap) list.get(i);
            //如果有重复认定，取认定日期晚的
            if ( ( (String) iMap.get("JSJDM")).equals(rs.getString("JSJDM"))
                && ( (String) iMap.get("SZ")).equals(rs.getString("SZ"))) {
              if ( ( (Timestamp) iMap.get("RDRQ")).before(rs.getTimestamp(
                  "RDRQ"))) {
                iMap.put("YSJCDM", rs.getString("YSJCDM"));
                iMap.put("RDRQ", rs.getTimestamp("RDRQ"));
                break; // 跳出内循环
              }
            }
          } //end for
        }
      } //end while
      rs.close();
      st.close();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return list;
  }

  private Ysjc getYsjcdm(String jsjdm, String szsmdm, List ysjcList) throws
      BaseException {
    try {
      Ysjc ysjc = new Ysjc();
      ysjc.setYsjcdm(CodeConstant.YSJC_GTGSH);
      ysjc.setYsjcmc("地方级");

      String sz = szsmdm.substring(0, 2);
      if (ysjcList.size() != 0) {
        for (int i = 0; i < ysjcList.size(); i++) {
          Map iMap = (HashMap) ysjcList.get(i);
          //如果有特殊认定,则取特殊级次
          if ( ( (String) iMap.get("JSJDM")).equals(jsjdm)
              && ( (String) iMap.get("SZ")).equals(sz)) {
            ysjc.setYsjcdm( (String) iMap.get("YSJCDM"));
            ysjc.setYsjcmc(CodeUtils.getCodeBeanLabel("DM_YSJC", ysjc.getYsjcdm()));
            break; //跳出循环
          }
        }
      }
      return ysjc;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取缴款凭证号
   * @param jsjdm 当前计算机代码
   * @param yyMM 征期
   * @param iMap  存贮 上次流水序号 and 上次操作的计算机代码
   * @return 缴款凭证号
   * @throws BaseException
   */
  private String getJkpzh(String jsjdm, String yyMM, Map iMap) throws
      BaseException {
    //如果是同一计算机代码,则序号加1
    try {
      if (jsjdm.equals(iMap.get("JSJDM"))) {
        iMap.put("XH", new Integer( ( (Integer) iMap.get("XH")).intValue() + 1));
      }
      else {
        iMap.put("JSJDM", jsjdm);
        iMap.put("XH", new Integer(1));
        iMap.put("HS",
                 String.valueOf(Integer.parseInt( (String) iMap.get("HS")) + 1));
      }

      return new StringBuffer(jsjdm).append(yyMM)
          .append(String.valueOf( ( (Integer) iMap.get("XH")).intValue() + 1000).
                  substring(1, 4))
          .append("Z").toString();
    }
    catch (NumberFormatException ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 获取预算科目代码
   * @param szsmdm 税种税目代码
   * @param djzclxdm 登记注册类型代码
   * @param gjbzhydm 国家标准行业代码
   * @param ysjcdm 预算级次代码
   * @return 预算科目代码
   */
  private Yskm getYskmdm(String szsmdm, String djzclxdm, String gjbzhydm,
                         String ysjcdm, List codeList) throws
      BaseException {
    Yskm yskm = new Yskm();
    StringBuffer key = new StringBuffer(szsmdm).append(djzclxdm)
        .append(gjbzhydm).append(ysjcdm);
    for (int i = 0; i < codeList.size(); i++) {
      Map map = (HashMap) codeList.get(i);
      if ( ( (String) map.get("KEY")).equals(key.toString())) {
        //找到匹配的
        yskm.setQxfcbl( (BigDecimal) map.get("QXFCBL"));
        yskm.setSjfcbl( (BigDecimal) map.get("SJFCBL"));
        yskm.setYskmdm( (String) map.get("YSKMDM"));
        return yskm;
      }
    }
    //没有命中,取借口获得预算科目
    try {
      yskm = JKAdapter.getInstance().getYskm(szsmdm, djzclxdm, gjbzhydm,
                                             ysjcdm);
      Map map = new HashMap();
      map.put("KEY", key.toString());
      map.put("QXFCBL", yskm.getQxfcbl());
      map.put("SJFCBL", yskm.getSjfcbl());
      map.put("YSKMDM", yskm.getYskmdm());
      codeList.add(map);
      return yskm;
    }
    catch (ZwclException ex1) {
      ex1.printStackTrace();
      throw ExceptionUtil.getBaseException(ex1);
    }
  }


}