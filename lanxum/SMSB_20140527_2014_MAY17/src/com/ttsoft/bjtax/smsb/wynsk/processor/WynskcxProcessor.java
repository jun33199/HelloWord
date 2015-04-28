package com.ttsoft.bjtax.smsb.wynsk.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.dj.DjCodeConstant;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wynsk.web.SbqyInfo;
import com.ttsoft.bjtax.smsb.wynsk.web.WynskcxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.codetable.CodeTableInterface;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统</p>
 * <p>Description: 上门申报</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class WynskcxProcessor
    implements Processor {

  public static final String QYZT_ALL_DM = "00";

  public static final String QYZT_ALL_MS = "全部状态";

  /**
   * Processor Dispacher
   * @param vo Value Object
   * @return PageForm
   * @throws BaseException Excetion throwable
   */
  public Object process(VOPackage vo) throws BaseException {
    switch (vo.getAction()) {
      case CodeConstant.SMSB_SHOWACTION:
        return doShow(vo);
      case CodeConstant.SMSB_QUERYACTION: //
        return doQueryA(vo);
      case CodeConstant.SMSB_QUERYACTION1:
        return doQueryB(vo);
      case CodeConstant.SMSB_TOEXCELACTION:
        return doExportA(vo);
      case CodeConstant.SMSB_TOEXCELACTION1:
        return doExportB(vo);
      default:
        throw new ApplicationException("未找到符合条件的操作");
    }
  }

  /**
   * 初始化方法
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doShow(VOPackage vo) throws BaseException {
    //1.申明句柄
    WynskcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WynskcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.开始业务
    try {
      ///3.1.初始化工具
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(ud, da));
      ///3.3.设置企业状态列表
      pf.setQyztList(this.getQyztList(ud, da));
      //*********add by zhangshubing **********
       ///3.4.设置申报类型
      pf.setSblxList(this.getSblxList(da));
      //***************************************
       //*********add by zhangshubing **********
        ///3.5.设置登记注册类型
      pf.setDjzclx(this.getDjzclxList());
      List djzclx2 = new ArrayList();
      djzclx2.add("* 查询中不包括的登记注册类型 *");
      pf.setDjzclx2(djzclx2);
      //***************************************
       ///3.6.设置值列表到页面对象

      pf.setHeadLrr(ud.getYhid());
      pf.setHeadLrrjb(ud.getYhjb());
      pf.setHeadSwjgzzjgdm(ud.getSsdwdm());
      pf.setHeadSwjgzzjgmc(ud.ssdwmc);
      pf.setHeadDqrq(SfDateUtil.getDate());
      pf.setQuerySbrqq(pf.getHeadDqrq());
      pf.setQuerySbrqz(pf.getHeadDqrq());
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * doQueryA
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doQueryA(VOPackage vo) throws BaseException {
    //1.申明句柄
    WynskcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WynskcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.开始业务
    try {
      ///3.1.初始化工具
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(ud, da));
      ///3.3.设置企业状态列表
      pf.setQyztList(this.getQyztList(ud, da));
      //**********add by zhangshubing ************
       ///3.3.2.设置申报类型列表
      pf.setSblxList(this.getSblxList(da));
      ///3.3.3.设置登记注册类型列表
      List tempDjzclxList = this.getDjzclxList();
      pf.setDjzclx(this.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
      pf.setDjzclx2(this.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
      //**********add end by zhangshubing*********

       ///3.4.开始查询
      tmpList = this.queryZeroSB(ud, pf, da);
      ///3.5.获取总页数数据
      pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
      ///3.6.整理查询结果
      dataList = this.getPageDataList(tmpList, pf);
      tmpList = null;
      ///3.7.加入页面对象
      //************add by zhangshubing ***************
       //System.out.println("*********************");
       //System.out.println("querySblx : " + pf.getQuerySblx());
       //System.out.println("*********************");
      pf.setQuerySblx(pf.getQuerySblx());
      //************add by zhangshubing end ***********

      pf.setDataList(dataList);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * doQueryB
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doQueryB(VOPackage vo) throws BaseException {
    //1.申明句柄
    WynskcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WynskcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      ExceptionUtil.getBaseException(e);
    }
    //3.开始业务
    try {
      ///3.1.初始化工具
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(ud, da));
      ///3.3.设置企业状态列表
      pf.setQyztList(this.getQyztList(ud, da));
      //**********add by zhangshubing ************
       ///3.3.2.设置申报类型列表
      pf.setSblxList(this.getSblxList(da));
      ///3.3.3.设置登记注册类型列表
      List tempDjzclxList = this.getDjzclxList();
      pf.setDjzclx(this.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
      pf.setDjzclx2(this.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
      //**********add end by zhangshubing*********

       ///3.4.开始查询
      tmpList = this.queryNoSB(ud, pf, da);
      ///3.5.获取总页数数据
      pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
      ///3.6.整理查询结果
      dataList = this.getPageDataList(tmpList, pf);
      tmpList = null;
      ///3.7.加入页面对象
      debug("本次查询数据集size=" + dataList.size());
      //************add by zhangshubing ***************
       //System.out.println("*********************");
       //System.out.println("querySblx : " + pf.getQuerySblx());
       //System.out.println("*********************");
      pf.setQuerySblx(pf.getQuerySblx());
      //************add by zhangshubing end ***********

      pf.setDataList(dataList);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * doExportA
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doExportA(VOPackage vo) throws BaseException {
    //1.申明句柄
    WynskcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WynskcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.开始业务
    try {
      ///3.1.初始化工具
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(ud, da));
      ///3.3.设置企业状态列表
      pf.setQyztList(this.getQyztList(ud, da));
      //**********add by zhangshubing ************
       ///3.3.2.设置申报类型列表
      pf.setSblxList(this.getSblxList(da));
      ///3.3.3.设置登记注册类型列表
      List tempDjzclxList = this.getDjzclxList();
      pf.setDjzclx(this.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
      pf.setDjzclx2(this.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
      //**********add end by zhangshubing*********

       ///3.4.开始查询
      tmpList = this.queryZeroSB(ud, pf, da);
      ///3.5.整理查询结果
      //************add by zhangshubing ***************
       //System.out.println("*********************");
       //System.out.println("querySblx : " + pf.getQuerySblx());
       //System.out.println("*********************");
      pf.setQuerySblx(pf.getQuerySblx());
      //************add by zhangshubing end ***********

      pf.setDataList(tmpList);
      tmpList = null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * doExportB
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doExportB(VOPackage vo) throws BaseException {
    //1.申明句柄
    WynskcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WynskcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      ExceptionUtil.getBaseException(e);
    }
    //3.开始业务
    try {
      ///3.1.初始化工具
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(ud, da));
      ///3.3.设置企业状态列表
      pf.setQyztList(this.getQyztList(ud, da));
      //**********add by zhangshubing ************
       ///3.3.2.设置申报类型列表
      pf.setSblxList(this.getSblxList(da));
      ///3.3.3.设置登记注册类型列表
      List tempDjzclxList = this.getDjzclxList();
      pf.setDjzclx(this.getYxDjzclxList(tempDjzclxList, pf.getAlldjzclx()));
      pf.setDjzclx2(this.getNoYxDjzclxList(tempDjzclxList, pf.getDjzclx()));
      //**********add end by zhangshubing*********

       ///3.4.开始查询
      tmpList = this.queryNoSB(ud, pf, da);
      ///3.5.整理查询结果
      //************add by zhangshubing ***************
       //System.out.println("*********************");
       //System.out.println("querySblx : " + pf.getQuerySblx());
       //System.out.println("*********************");
      pf.setQuerySblx(pf.getQuerySblx());
      //************add by zhangshubing end ***********

      pf.setDataList(tmpList);
      tmpList = null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * 获取当前分页数据集
   * @param tmpList 完整数据集
   * @param pf 页面对象
   * @return 当前分页数据集
   */
  private List getPageDataList(List tmpList, WynskcxActionForm pf) {
    //1.申明句柄
    List dataList = new ArrayList();
    //2.初始化参数值
    int startIndex = this.getPageStartIndex(pf.getNextPage(), pf.getTotalpage());
    int endIndex = this.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
    //3.开始业务
    for (int i = startIndex; i < endIndex; i++) {
      if (i < tmpList.size()) {
        dataList.add(tmpList.get(i));
      }
    }
    tmpList = null;
    //99.返回值
    return dataList;
  }

  /**
   * 获取所有符合条件的税务所
   * @param ud 用户对象
   * @param da 数据库操作对象
   * @return 符合条件的税务所List
   * @throws BaseException 异常
   */
  private List getSwsList(UserData ud, SfDBAccess da) throws BaseException {
    //1.句柄申明
    String sql = null;
    List tmpList = new ArrayList();
    String swjgzzjgdm = ud.getSsdwdm();
    ResultSet rs = null;
    String fjdm = ud.getSsdwdm().substring(0, 2) + "00";
    //2.根据用户级别决定使用某一个sql
    String yhjb = ud.getYhjb();
    String swsjb = CodeConstants.JBDM_SWSJ; //税务所级
    String fjjb = CodeConstants.JBDM_FJ; //税务所级
    String grjb = CodeConstants.JBDM_GRJ;
    if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { //税务所级
      sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and swjgzzjgdm=" +
          SBStringUtils.getSQLStr(swjgzzjgdm);
    }
    else if (CodeConstants.JBDM_FJ.equals(yhjb)) { //分局级
      sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and qxfjdm=" +
          SBStringUtils.getSQLStr(fjdm);
    }

    else if (CodeConstants.JBDM_SJ.equals(yhjb)) { //市局级

      sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' order by swjgzzjgdm asc";

}


    //3.执行查询并整理数据
    try {
      rs = da.querySQL(sql);
      while (rs.next()) {
        tmpList.add(rs.getString(1));
      }
      rs.close();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //4.返回值
    return tmpList;
  }

  private List getQyztList(UserData ud, SfDBAccess da) {
    //1.句柄申明
    String sql = null;
    List dataList = new ArrayList();
    List tmpList = new ArrayList();
    CodeTableInterface ci = null;
    String value = null;
    //2.完成数据查询并生成List
    ///2.0.加入通用标记
    dataList.add(QYZT_ALL_DM + "|" + QYZT_ALL_MS);
    ///2.1.通过登记的代码工具获取所有企业状态列表
    tmpList = CodeTableUtil.getCodeTableList(CodeTableKey.NSRZT);
    ///2.2.整理数据成为符合格式的状态列表
    for (int i = 0; i < tmpList.size(); i++) {
      ci = (CodeTableInterface) tmpList.get(i);
      if (!DjCodeConstant.NSRZT_SHUIWUSUO.equals(ci.getOptionValue())) {
        dataList.add(ci.getOptionValue() + "|" + ci.getOptionText());
      }
    }
    //99.返回值
    return dataList;
  }

  /**
   * 查询零申报户
   * 查询参数：税务机关组织机构代码、企业状态、计算机代码、申报日期起、申报日期止，
   * 如果企业状态不是全部的话需要作联查过滤企业状态，否则避免联查
   * @param ud 用户对象
   * @param pf 页面对象
   * @param da 数据库操作对象
   * @return 查询结果
   * @exception BaseException BaseException
   */
  private List queryZeroSB(UserData ud, WynskcxActionForm pf, SfDBAccess da) throws
      BaseException {
    //1.句柄申明
    String sql = null;
    List tmpList = new ArrayList();
    String swjgzzjgdm = ud.getSsdwdm();
    ResultSet rs = null;
    SbqyInfo si = null;
    //2.整理并创建查询用sql
    sql = this.getZeroSBQuerySqlStr(pf);
    this.debug(sql);
    try {
      //3.执行查询
      rs = da.querySQL(sql);
      //4.整理数据
      int count = 1;
      while (rs.next()) {
        si = new SbqyInfo();
        si.setIndex(count++);
        si.setJsjdm(rs.getString(1));
        si.setNsrmc(rs.getString(2));
        si.setDjzclxdm(rs.getString(3));
        si.setQyztdm(rs.getString(4));
        si.setJydz(rs.getString(5));
        si.setLxdh(rs.getString(6));
        si.setDjzclxmc(rs.getString(7));
        si.setQyztmc(rs.getString(8));
        tmpList.add(si);
      }
      rs.close();
    }
    catch (SQLException e) {
      debugCore(sql);
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //99.返回值
    return tmpList;
  }

  /**
   * 查询无申报户
   * @param ud 用户对象
   * @param pf 页面对象
   * @param da 数据库操作对象
   * @return 查询结果
   * @exception BaseException BaseException
   */
  private List queryNoSB(UserData ud, WynskcxActionForm pf, SfDBAccess da) throws
      BaseException {
    //1.句柄申明
    String sql = null;
    List tmpList = new ArrayList();
    String swjgzzjgdm = ud.getSsdwdm();
    ResultSet rs = null;
    SbqyInfo si = null;
    //2.整理并创建查询用sql
    sql = this.getNoSBQuerySqlStr(pf);
    this.debug(sql);
    //3.执行查询
    try {
      rs = da.querySQL(sql);
      //4.整理数据
      int count = 1;
      while (rs.next()) {
        si = new SbqyInfo();
        si.setIndex(count++);
        si.setJsjdm(rs.getString(1));
        si.setNsrmc(rs.getString(2));
        si.setDjzclxdm(rs.getString(3));
        si.setQyztdm(rs.getString(4));
        si.setJydz(rs.getString(5));
        si.setLxdh(rs.getString(6));
        si.setDjzclxmc(rs.getString(7));
        si.setQyztmc(rs.getString(8));
        tmpList.add(si);
      }
      rs.close();
    }
    catch (SQLException e) {
      debugCore(sql);
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //99.返回值
    return tmpList;
  }

  /**
   * 获取零申报查询SQL
   * @param pf 页面对象
   * @return QuerySqlFROMAndWhereStr
   */
  private String getZeroSBQuerySqlStr(WynskcxActionForm pf) {
    //1.句柄申明
    StringBuffer sb = new StringBuffer();
    String qxdm = pf.getQuerySwjgzzjg().substring(0, 2);
    String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
        pf.getQuerySwjgzzjg().indexOf("|"));
    String nsrzt = pf.getQueryQyzt().substring(0, pf.getQueryQyzt().indexOf("|"));
    //String sblxWhere = this.getSblxWhere(pf.getQuerySblx());
    String wynsksbSblxWhere = this.getWynsksbSblxWhere(pf.getQuerySblx());
    String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx());
    //String sbjkzbDjzclxWhere = this.getSbjkzbDjzclxWhere(pf.getAlldjzclx());
    String sbjkzbSblxWhere = this.getSbjkzbSblxWhere(pf.getQuerySblx());

    //2.开始业务
    sb.append("select c.jsjdm,c.nsrmc,c.djzclxdm,c.nsrzt,c.jydz,c.jydzlxdm");
    sb.append(",(select t.djzclxmc from dmdb.dj_dm_djzclx t where t.djzclxdm=c.djzclxdm) djzclxmc");
    sb.append(
        ",(select t.nsrztmc from dmdb.dj_dm_nsrzt t where t.nsrztdm=c.nsrzt) nsrztmc");
    sb.append(" from sbdb.sb_jl_wynsksb a,djdb.dj_jl_jbsj c");
    sb.append(" where ");
    sb.append(djzclxWhere);
    //if (!sblxWhere.equals("")) {
    //  sb.append(" and ");
    // sb.append(sblxWhere);
    //}
    //**************add by zhangshubing ************
    if (!wynsksbSblxWhere.equals("")) {
      sb.append(" and ");
      sb.append(wynsksbSblxWhere + " ");
    }
    //**************add by zhangshubing end**********
     sb.append(" and a.qxdm=");

    sb.append(SBStringUtils.getSQLStr(qxdm));
    sb.append(" and a.swjgzzjgdm=");
    sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
    sb.append(" and a.jsjdm=c.jsjdm");
    if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
      sb.append(" and a.jsjdm=");
      sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
    }
    if (!QYZT_ALL_DM.equals(nsrzt)) {
      sb.append(" and c.nsrzt=");
      sb.append(SBStringUtils.getSQLStr(nsrzt));
    }
    sb.append(" and (a.sbrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append(")");
    sb.append(" and not exists (");
    sb.append("select distinct b.jsjdm,b.skssksrq,b.skssjsrq from sbdb.sb_jl_sbjkzb b where b.qxdm=");
    sb.append(SBStringUtils.getSQLStr(qxdm));
    //******************add by zhangshubing*************
     //sb.append(" and " + sbjkzbDjzclxWhere + " ");
    if (!sbjkzbSblxWhere.equals("")) {
      sb.append(" and ");
      sb.append(sbjkzbSblxWhere + " ");
    }
    //******************add by zhangshubing end*********

     sb.append(" and b.swjgzzjgdm=");
    sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
    if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
      sb.append(" and b.jsjdm=");
      sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
    }
    sb.append(" and ((b.zyrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append("-30");
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append("+30");
    sb.append(")");
    sb.append(" or b.zyrq is null");
    sb.append(")");
    sb.append(" and (b.sbrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append(")and a.jsjdm=b.jsjdm)");
    //99.返回值
//    System.out.println(sb.toString());
    //System.out.println("*****************************");
    //System.out.println("ZeroSBQuery : " + sb.toString());
    //System.out.println("*****************************");

    return sb.toString();
  }

  /**
   * 获取无申报查询SQL
   * @param pf 页面对象
   * @return QuerySqlFROMAndWhereStr
   */
  private String getNoSBQuerySqlStr(WynskcxActionForm pf) {
    //1.句柄申明
    StringBuffer sb = new StringBuffer();
    String qxdm = pf.getQuerySwjgzzjg().substring(0, 2);
    String swjgzzjgdm = pf.getQuerySwjgzzjg().substring(0,
        pf.getQuerySwjgzzjg().indexOf("|"));
    String nsrzt = pf.getQueryQyzt().substring(0, pf.getQueryQyzt().indexOf("|"));
    //String sblxWhere = this.getSblxWhere(pf.getQuerySblx());
    String djzclxWhere = this.getDjzclxWhere(pf.getAlldjzclx());
    String wynsksbSblxWhere = this.getWynsksbSblxWhere(pf.getQuerySblx());
    //String sbjkzbDjzclxWhere = this.getSbjkzbDjzclxWhere(pf.getAlldjzclx());
    String sbjkzbSblxWhere = this.getSbjkzbSblxWhere(pf.getQuerySblx());
    String ndWhere = "";
    //System.out.println("----------nd==="+pf.getQuerySbrqq().substring(0,4));
    if (pf.getQuerySbrqq().substring(0,4).equalsIgnoreCase(pf.getQuerySbrqz().substring(0,4))){
        ndWhere = pf.getQuerySbrqq().substring(0,4);
    }

    //2.开始业务
    sb.append("select c.jsjdm,c.nsrmc,c.djzclxdm,c.nsrzt,c.jydz,c.jydzlxdm");
    sb.append(",(select t.djzclxmc from dmdb.dj_dm_djzclx t where t.djzclxdm=c.djzclxdm) djzclxmc");
    sb.append(
        ",(select t.nsrztmc from dmdb.dj_dm_nsrzt t where t.nsrztdm=c.nsrzt) nsrztmc");
    sb.append(" from djdb.dj_jl_jbsj c");
    sb.append(" where ");
    sb.append(djzclxWhere);
    //if (!sblxWhere.equals("")) {
    //  sb.append(" and ");
    //  sb.append(sblxWhere);
    //}
    sb.append(" and c.swjgzzjgdm=");
    sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
    if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
      sb.append(" and c.jsjdm=");
      sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
    }
    if (!QYZT_ALL_DM.equals(nsrzt)) {
      sb.append(" and c.nsrzt=");
      sb.append(SBStringUtils.getSQLStr(nsrzt));
    }
    //*********************ADD BY HUXF 20050725**********************
    sb.append(" and c.cjrq<to_date(to_char(sysdate, 'yyyymm')||'01', 'YYYYMMDD')");
    sb.append(" and c.cjrq<to_date(to_char(");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqz()));
    sb.append(",'yyyymm')||'01','YYYYMMDD')");
    //*********************ADD by huxf END 20050725**************************

    sb.append(" and not exists (");
    sb.append("select distinct b.jsjdm from sbdb.sb_jl_sbjkzb b where b.qxdm=");
    sb.append(SBStringUtils.getSQLStr(qxdm));
    //******************add by zhangshubing*************
     //sb.append(" and " + sbjkzbDjzclxWhere + " ");
    if (!sbjkzbSblxWhere.equals("")) {
      sb.append(" and ");
      sb.append(sbjkzbSblxWhere + " ");
    }
    //******************add by zhangshubing end*********

     if (!ndWhere.equals("")) {
       sb.append(" and b.nd=");
       sb.append(ndWhere + " ");
     }

     sb.append("  and b.jsjdm=c.jsjdm");
    if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
      sb.append(" and b.jsjdm=");
      sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
    }
    //**************delete by guzx ************
//    sb.append(" and b.swjgzzjgdm=");
//    sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));
    sb.append(" and ((b.zyrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append("-30");
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append("+30");
    sb.append(")");
    sb.append(" or b.zyrq is null");
    sb.append(")");
    sb.append(" and (b.sbrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append("))");
    sb.append(" and not exists (");
    sb.append("select distinct a.jsjdm from sbdb.sb_jl_wynsksb a where a.qxdm=");
    sb.append(SBStringUtils.getSQLStr(qxdm));
    //**************add by zhangshubing ************
    if (!wynsksbSblxWhere.equals("")) {
      sb.append(" and ");
      sb.append(wynsksbSblxWhere + " ");
    }
    //**************add by zhangshubing end**********

     if (!ndWhere.equals("")) {
       sb.append(" and a.nd=");
       sb.append(ndWhere + " ");
     }

     sb.append("  and a.jsjdm=c.jsjdm");
    if (!"".equals(SBStringUtils.killNull(pf.getQueryJsjdm()))) {
      sb.append(" and a.jsjdm=");
      sb.append(SBStringUtils.getSQLStr(pf.getQueryJsjdm()));
    }

    //**************delete by guzx ************
//    sb.append(" and a.swjgzzjgdm=");
//    sb.append(SBStringUtils.getSQLStr(swjgzzjgdm));

    sb.append(" and (a.sbrq between ");
    sb.append(SBStringUtils.getSQLDate(pf.getQuerySbrqq()));
    sb.append(" and ");
    sb.append(SBStringUtils.getSQLDate1(pf.getQuerySbrqz()));
    sb.append(")");
    sb.append(")");
    //99.返回值
    //System.out.println("*****************************");
    //System.out.println("NoSB : " + sb.toString());
    //System.out.println("*****************************");
    return sb.toString();
  }

  /**
   * 获取页数
   * @param rsCount 查询结果集build
   * @return 页数
   */
  private String getPageTotalCount(int rsCount) {
    //1.句柄申明
    String countTotal = "0";
    //2.开始业务
    debug("本次查询临时结果集size=" + rsCount);
    int pageCount = 0;
    if ( (rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
      pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
    }
    else {
      pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
    }
    countTotal = String.valueOf(pageCount);
    //99.返回值
    return countTotal;
  }

  /**
   * 获取当前页开始index
   * @param nextPage 下一页
   * @param countTotal 总页数
   * @return 开始index
   */
  private int getPageStartIndex(String nextPage, String countTotal) {
    //1.句柄申明
    int iNextPage = Integer.parseInt(nextPage);
    int iCountTotal = Integer.parseInt(countTotal);
    int start = -1;
    //2.开始业务
    start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
    debug("本次查询开始索引=" + start);
    //99.返回值
    return start;
  }

  /**
   * 获取当前页结束index
   * @param nextPage 下一页
   * @param countTotal 总页数
   * @return 结束index
   */
  private int getPageEndIndex(String nextPage, String countTotal) {
    //1.句柄申明
    int iNextPage = Integer.parseInt(nextPage);
    int iCountTotal = Integer.parseInt(countTotal);
    int end = -1;
    //2.开始业务
    end = iNextPage * CodeConstant.SD_PG_LENGTH;
    debug("本次查询结束索引=" + end);
    //99.返回值
    return end;
  }

  /**
   * 获取当前数据是否在当前分页内
   * @param start 开始index
   * @param end 结束index
   * @param index 当前index
   * @return  -1表示在当前分页前，0表示在当前分页内，1表示在当前分页之后
   */
  private int getInPageData(int start, int end, int index) {
    //1.句柄申明
    int flag = -1;
    //2.开始业务
    if (index >= start || index < end) {
      flag = 0;
    }
    else if (index >= end) {
      flag = 1;
    }
    //99.返回值
    return flag;
  }

  /**
   * Debug方法
   * @param str 信息
   */
  private void debug(String str) {
    System.out.println("SMSB DEBUG:" + str);
  }

  /**
   * 系统输出方法
   * @param str 信息
   */
  private void debugCore(String str) {
    System.out.println("SMSB CORE:" + str);
  }

  /**
   * 得到申报类型基础数据
   * @return
   */
  private List getSblxList(SfDBAccess da) {
    //1.句柄申明
    String sql = null;
    List returnList = new ArrayList();
    ResultSet rs = null;

    sql = "select sbdm,sbmc from dmdb.gy_dm_fs order by sbdm ";

    returnList.add(new String("0|全选"));
    //3.执行查询并整理数据
    try {
      rs = da.querySQL(sql);
      while (rs.next()) {
        String insertString = rs.getString(1) + "|" + rs.getString(2);
        //System.out.println(insertString);
        returnList.add(insertString);
      }
      rs.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    //4.返回值
    //System.out.println("**********************");
    //for(int i = 0 ; i < returnList.size() ; i++){
    //  System.out.println(returnList.get(i));
    //}
    //System.out.println("**********************");

    return returnList;
  }

  /**
   * 得到登记注册类型基础数据
   * @return
   */
  private List getDjzclxList() {
    //1.句柄申明
    String sql = null;
    List dataList = new ArrayList();
    List tmpList = new ArrayList();
    CodeTableInterface ci = null;
    String value = null;
    //2.完成数据查询并生成List
    ///2.1.通过登记的代码工具获取所有企业状态列表
    tmpList = CodeTableUtil.getCodeTableList(CodeTableKey.DJZCLX);
    ///2.2.整理数据成为符合格式的状态列表
    dataList.add("* 查询条件中包括的登记注册类型 *");
    //System.out.println("**************************************");
    for (int i = 0; i < tmpList.size(); i++) {
      ci = (CodeTableInterface) tmpList.get(i);
      //System.out.println("WynskcxProcessor : " + ci.getOptionValue() + "|" +
      //                   ci.getOptionText());
      dataList.add(ci.getOptionValue() + "|" + ci.getOptionText());
    }
    //System.out.println("**************************************");
    //99.返回值
    return dataList;
  }

  /**
   * 得到sblx的where语句部分；如果为0，则返回""，表示不加此条件；否则，返回条件；
   * @param sblx
   * @return
   */
  /*
   private String getSblxWhere(String sblx) {
    String subString = sblx.substring(0, 1);
    if (subString.equals("0")) {
      return "";
    }
    else {
      //System.out.println("***************************");
      //System.out.println("sblxWhere : " + "(d.sbdm=" + "'" + subString.trim() + "' and "+ "d.sbdm=c.sbdm" + ")");
      //System.out.println("***************************");
      return "(d.sbdm=" + "'" + subString.trim() + "' and " + "d.sbdm=c.sbdm" +
          ")";
    }
   }
   */

  private String getDjzclxWhere(String allDjzclx) {
    String djzclxWhere = "";
    ArrayList tempList = new ArrayList();

    //从allDjzclx中抽取出所有的登记类型代码号，并且保存在list中；
    for (int i = 0; i < allDjzclx.length(); i++) {
      if (allDjzclx.substring(i, i + 1).equals("|")) {
        String tempString = allDjzclx.substring(i - 3, i);
        tempList.add(tempString);
      }
    }

    //根据list中的登记类型代码号，构造djzclx的where部分。
    for (int i = 0; i < tempList.size(); i++) {
      if (i == 0) {
        djzclxWhere = "(" + "c.djzclxdm=" + "'" +
            ( (String) tempList.get(i)).trim() + "'";
      }
      else {
        djzclxWhere = djzclxWhere + " or " + "c.djzclxdm=" + "'" +
            ( (String) tempList.get(i)).trim() + "'";
      }
    }
    djzclxWhere = djzclxWhere + ")";
    //System.out.println("*******************************");
    //System.out.println("djzclxWhere : " + djzclxWhere);
    //System.out.println("*******************************");
    return djzclxWhere;
  }

  /**
   * 得到所有被选的登记注册类型
   * @param allDjzclxList
   * @param allDjzclx
   * @return
   */
  private List getYxDjzclxList(List allDjzclxList, String allDjzclx) {
    List returnList = new ArrayList();
    returnList.add("* 查询条件中包括的登记注册类型 *");

    List tempList = new ArrayList();
    //从allDjzclx中抽取出所有的登记类型代码号，并且保存在list中；
    for (int i = 0; i < allDjzclx.length(); i++) {
      if (allDjzclx.substring(i, i + 1).equals("|")) {
        String tempString = allDjzclx.substring(i - 3, i);
        tempList.add(tempString);
      }
    }

    for (int i = 1; i < allDjzclxList.size(); i++) {
      String tempString2 = (String) allDjzclxList.get(i);
      boolean bool = false;
      for (int j = 0; j < tempList.size(); j++) {
        if (tempString2.trim().substring(0,
                                         3).equals( ( (String) tempList.get(j)).
            trim())) {
          bool = true;
          break;
        }
      }
      if (bool) {
        returnList.add(tempString2);
      }
    }

    //System.out.println("WynskcxProcessor 952:********************************");
    //for(int i = 0 ; i < returnList.size() ; i++){
    // System.out.println("bxDjzclxList " + returnList.get(i));
    //}
    //System.out.println("WynskcxProcessor 952:********************************");

    return returnList;
  }

  /**
   * 得到没有被选的登记注册类型
   * @param allDjzclxList
   * @param yxDjzclxList
   * @return
   */
  private List getNoYxDjzclxList(List allDjzclxList, List yxDjzclxList) {
    List returnList = new ArrayList();
    returnList.add("* 查询条件中不包括的登记注册类型 *");

    for (int i = 1; i < allDjzclxList.size(); i++) {
      boolean bool = false;
      String temp = (String) allDjzclxList.get(i);
      for (int j = 1; j < yxDjzclxList.size(); j++) {
        if ( ( (String) allDjzclxList.get(i)).trim().substring(0,
            3).equals( ( (String) yxDjzclxList.get(j)).trim().substring(0, 3))) {
          bool = true;
          break;
        }
      }
      if (!bool) {
        returnList.add(temp);
      }
    }

    //System.out.println("WynskcxProccessor 985*************************");
    //for(int i = 0 ; i < returnList.size() ; i++){
    //  System.out.println("returnList : " + returnList.get(i));
    //}
    //System.out.println("WynskcxProccessor 985*************************");

    return returnList;
  }

  /**
   * 得到sbjkzb中djzclxdm项的where部分
   * @param allDjzclx
   * @return
   */
  /*
   public String getSbjkzbDjzclxWhere(String allDjzclx) {
    String djzclxWhere = "";
    ArrayList tempList = new ArrayList();
    //从allDjzclx中抽取出所有的登记类型代码号，并且保存在list中；
    for (int i = 0; i < allDjzclx.length(); i++) {
      if (allDjzclx.substring(i, i + 1).equals("|")) {
        String tempString = allDjzclx.substring(i - 3, i);
        tempList.add(tempString);
      }
    }
    //根据list中的登记类型代码号，构造djzclx的where部分。
    for (int i = 0; i < tempList.size(); i++) {
      if (i == 0) {
        djzclxWhere = "(" + "b.djzclxdm=" + "'" +
            ( (String) tempList.get(i)).trim() + "'";
      }
      else {
        djzclxWhere = djzclxWhere + " or " + "b.djzclxdm=" + "'" +
            ( (String) tempList.get(i)).trim() + "'";
      }
    }
    djzclxWhere = djzclxWhere + ")";
    //System.out.println("*******************************");
    //System.out.println("djzclxWhere : " + djzclxWhere);
    //System.out.println("*******************************");
    return djzclxWhere;
   }
   */

  /**
   * 得到wynsksb中的fsdm项的where语句部分
   * @param sblx
   * @return
   */
  private String getWynsksbSblxWhere(String sblx) {
    String subString = sblx.substring(0, 1);
    if (subString.equals("0")) {
      return "";
    }
    else {
      //System.out.println("***************************");
      //System.out.println("sblxWhere : " + "(d.sbdm=" + "'" + subString.trim() + "' and "+ "d.sbdm=c.sbdm" + ")");
      //System.out.println("***************************");
      return " ( a.fsdm=" + "'" + subString.trim()+"' ) ";
    }
  }

  /**
   * 得到wynsksb中的fsdm项的where语句部分
   * @param sblx
   * @return
   */
  private String getSbjkzbSblxWhere(String sblx) {
    String subString = sblx.substring(0, 1);
    if (subString.equals("0")) {
      return "";
    }
    else {
      //System.out.println("***************************");
      //System.out.println("sblxWhere : " + "(d.sbdm=" + "'" + subString.trim() + "' and "+ "d.sbdm=c.sbdm" + ")");
      //System.out.println("***************************");
      return " ( b.fsdm=" + "'" + subString.trim()+"' ) ";
    }
  }

}
