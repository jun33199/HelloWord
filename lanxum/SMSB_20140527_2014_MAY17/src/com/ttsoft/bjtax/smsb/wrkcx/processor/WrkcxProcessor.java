package com.ttsoft.bjtax.smsb.wrkcx.processor;

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
import com.ttsoft.bjtax.smsb.wynsk.web.SbqyInfo;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.codetable.CodeTableInterface;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.wrkcx.web.WrkcxActionForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import java.util.Map;
import java.util.HashMap;
import com.ttsoft.bjtax.smsb.wrkcx.WrkVo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WrkcxProcessor
    implements Processor {
  public WrkcxProcessor() {
  }

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
        return doQuery(vo);
      case CodeConstant.SMSB_TOEXCELACTION: //
        return doSaveExcel(vo);
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
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    ArrayList datalist = new ArrayList();
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
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
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setJxList(this.getJxList(da, ud));

      ///3.5.设置登记注册类型
      pf.setDjzclx(this.getDjzclxList());
      ///3.5.设置税款类型
      pf.setSklxlist(this.getSklxList(da));
//      List djzclx2 = new ArrayList();
//      djzclx2.add("* 查询中不包括的登记注册类型 *");
//      pf.setDjzclx2(djzclx2);
      //设置结果集合
      pf.setDataList(datalist);
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
   * 获取所有符合条件的税务所
   * @param ud 用户对象
   * @param da 数据库操作对象
   * @return 符合条件的税务所List
   * @throws BaseException 异常
   */
  /**
   * 获取税务局列表
   * @param db
   * @param userData
   * @return
   * @throws BaseException
   */
  private ArrayList getSwsList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //税务局
      String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
      String yhjb = userData.getYhjb();
      String ssdwdm = userData.getSsdwdm();
      StringBuffer sb = new StringBuffer();
      sb.append(
          " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
      sb.append(
          " where substr(SWJGZZJGDM,3,2) <> '00' AND SWJGZZJGDM not like '90%' ");
      if (!"90".equals(iQxdm)) {
        sb.append(" and SWJGZZJGDM like '" + iQxdm + "%' ");
        if ("30".equals(yhjb)) {
          sb.append(" and SWJGZZJGDM ='" + ssdwdm + "'");
        }
      }
      sb.append(" order by SWJGZZJGDM ");
      if (!"30".equals(yhjb)) {
        LabelValueBean label = new LabelValueBean("*所有税务所", "0");
        list.add(label);
      }
      ResultSet rs = db.querySQL(sb.toString());
      while (rs.next()) {
        LabelValueBean bean = new LabelValueBean("", "");
        bean.setValue( (String) rs.getString("value"));
        bean.setLabel( (String) rs.getString("descr"));
        list.add(bean);
      }
    }
    catch (SQLException e) {
      throw ExceptionUtil.getBaseException(e);
    }
    return list;
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
    for (int i = 0; i < tmpList.size(); i++) {
      ci = (CodeTableInterface) tmpList.get(i);
      //System.out.println("WynskcxProcessor : " + ci.getOptionValue() + "|" +
      //                   ci.getOptionText());
      LabelValueBean bean = new LabelValueBean("", "");
      bean.setValue(  ci.getOptionValue());
      bean.setLabel(  ci.getOptionText());

      dataList.add(bean);
    }
    LabelValueBean bean = new LabelValueBean("", "");
    dataList.add(0,bean);
    //System.out.println("**************************************");
    //99.返回值
    return dataList;
  }

  /**
   * 获取区县列表
   * @param db
   * @param userData
   * @return
   * @throws BaseException
   */
  private ArrayList getQxList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //税务局
      String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
      StringBuffer sb = new StringBuffer();
      sb.append(
          " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
      if (!"90".equals(iQxdm)) {
        sb.append(" where SWJGZZJGDM  = '" + iQxdm + "00' ");
      }
      else {
        sb.append(" where substr(SWJGZZJGDM,3,2) = '00' ");
      }
      sb.append(" order by SWJGZZJGDM ");
      ResultSet rs = db.querySQL(sb.toString());
      while (rs.next()) {
        LabelValueBean bean = new LabelValueBean("", "");
        bean.setValue( (String) rs.getString("value"));
        bean.setLabel( (String) rs.getString("descr"));
        if ("9000".equals( (String) rs.getString("value"))) {
          //list.add(0, bean);
        }
        else {
          list.add(bean);
        }
      }
    }
    catch (SQLException e) {
      throw ExceptionUtil.getBaseException(e);
    }
    return list;
  }

  /**
   * 获取税务局列表
   * @param db
   * @return
   * @throws BaseException
   */
  private ArrayList getJxList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //税务局
      StringBuffer sb = new StringBuffer();
      String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
      sb.append(
          " select scjxdm as value, scjxmc as descr from dmdb.dj_dm_scjx ");
      if (!"90".equals(iQxdm)) {
        sb.append(" where scjxdm like '" + iQxdm + "%'");
      }
      sb.append(" order by scjxdm ");
      LabelValueBean label = new LabelValueBean("*所有街乡", "0");
      list.add(label);
      ResultSet rs = db.querySQL(sb.toString());
      while (rs.next()) {
        LabelValueBean bean = new LabelValueBean("", "");
        bean.setValue( (String) rs.getString("value"));
        bean.setLabel( (String) rs.getString("descr"));
        list.add(bean);
      }
    }
    catch (SQLException e) {
      throw ExceptionUtil.getBaseException(e);
    }
    return list;
  }

  /**
   * 生成查询的SQL语句
   * @param kf
   * @return   key  =sumSql   ,value String ;key=sumTitle ,value String[] ,key = sumKey ,value String[]
   *           key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
   */
  private Map getSql(WrkcxActionForm kf) {
    //查询条件
    StringBuffer sqlBuffer = new StringBuffer();
    String sumSql = null;
    StringBuffer whereBuffer = new StringBuffer();
    //String djzclxWhere = this.getDjzclxWhere(kf.getAlldjzclx());
    String djzclxWhere = null;
    whereBuffer.append(" where c.jsjdm=b.jsjdm");
    whereBuffer.append(" and d.sklxdm=c.sklxdm");
    //申报起日期
    whereBuffer.append(" and c.sbrq >= " + SBStringUtils.getSQLDate(kf.getSbrqq()));
    //前推30天
    whereBuffer.append(" and c.zyrq >= " + SBStringUtils.getSQLDate(kf.getSbrqq())+"-30");
    //后推90天
    //whereBuffer.append(" and c.zyrq <= " + SBStringUtils.getSQLDate(kf.getSbrqq())+"+90");
    //申报止日期
    whereBuffer.append(" and c.sbrq <= " + SBStringUtils.getSQLDate(kf.getSbrqz()));
    System.out.println("sklxdm:"+kf.getSklxdm());

    //税款类型
    String[] sklx=kf.getSklxdm();
    if(null!=sklx&&sklx.length>=1){
      if(!"".equals(sklx[0])){
        whereBuffer.append(" and (c.sklxdm='" + sklx[0] + "'");
        for (int i = 1; i < sklx.length; i++) {
          whereBuffer.append(" or c.sklxdm='" + sklx[i] + "'");
        }
        whereBuffer.append(")");
      }
    }


    //登记注册类型代码
    String[] djzclx=kf.getQueryDjzclx();
    System.out.println("djzclxdm:"+djzclx);
    if(null!=djzclx&&djzclx.length>=1){
      if(!"".equals(djzclx[0])){
        whereBuffer.append(" and (c.djzclxdm='" + djzclx[0] + "'");
        for (int i = 1; i < djzclx.length; i++) {
          whereBuffer.append(" or c.djzclxdm='" + djzclx[i] + "'");
        }
        whereBuffer.append(")");
      }
    }

    //区县
    if (!"9000".equals(kf.getQueryfj())) {
      whereBuffer.append(" and c.SWJGZZJGDM like '" +
                         kf.getQueryfj().substring(0, 2) + "%'");
    }
    //税务所
    if (!"".equals(kf.getQuerysws())) {
      whereBuffer.append(" and c.SWJGZZJGDM ='" + kf.getQuerysws() + "'");
    }
    //街乡
    if (!"".equals(kf.getQueryjx())) {
      whereBuffer.append(" and b.SCJXDM ='" + kf.getQueryjx() + "'");
    }
    //业务条件
    whereBuffer.append(" and (substr(c.zwbs,1,1) ='0' or c.sjje<>c.rkje)");
    if (null != djzclxWhere || "".equals(djzclxWhere)) {
      whereBuffer.append(" and " + djzclxWhere);
    }
    //限缴日期
    if (null != kf.getXjrq() && !"".equals(kf.getXjrq())) {
      whereBuffer.append(" and xjrq<=" + SBStringUtils.getSQLDate(kf.getXjrq()));
    }
    whereBuffer.append(" order by c.jsjdm,c.sklxdm,c.szdm");   
    //UserData userdata = vo.getUserData();
    sqlBuffer.append(" select c.jsjdm jsjdm,");
    sqlBuffer.append(" b.nsrmc nsrmc,");
    sqlBuffer.append(" d.sklxmc sklx,");
    sqlBuffer.append(
        " (select yskmmc from dmdb.kj_dm_yskm where yskmdm=c.yskmdm and nd='" +
        kf.getSbrqq().substring(0, 4) + "') yskmmc,");
    sqlBuffer.append(
        " (select szsmmc from dmdb.sb_dm_szsm where szsmdm=c.szdm) szmc,");
    sqlBuffer.append(" to_char(c.sbrq,'yyyymmdd') sbrq,");
    //银行收款时间
    sqlBuffer.append("c.jksj yhsksj,");
    sqlBuffer.append(" to_char(c.xjrq,'yyyymmdd') xjrq,");
    sqlBuffer.append(" c.sjje sjje,");
    sqlBuffer.append(" to_char(c.zyrq,'yyyymmdd') zyrq,");
    sqlBuffer.append(" c.rkje rkje,");
    sqlBuffer.append(" to_char(c.skssksrq,'yyyymmdd') skssksrq,");
    sqlBuffer.append(" to_char(c.skssjsrq,'yyyymmdd') skssjsrq,");
    sqlBuffer.append(" c.clbjdm clbj,");
    sqlBuffer.append(" c.zwbs zwbs,");
    sqlBuffer.append(" c.hxrmc hxrmc,");
    sqlBuffer.append(" c.lrr lrr,");
    sqlBuffer.append(" to_char(c.lrrq,'yyyymmdd') lrrq,");
    sqlBuffer.append(" to_char(c.hxrq,'yyyymmdd') hxrq,");
    sqlBuffer.append(" c.sjly sjly,");
    sqlBuffer.append(" (select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=c.zsswjgzzjgdm ) zsswjg,");

    sqlBuffer.append(" b.jydzlxdm jydh,");
    sqlBuffer.append(
        " (select jc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=c.swjgzzjgdm ) sjc");

    sqlBuffer.append(
        " from sbdb.sb_jl_sbjkzb c,djdb.dj_jl_jbsj b,DMDB.KJ_DM_SKLX d");

    String sql = sqlBuffer.append(whereBuffer.toString()).toString();
    System.out.println(sql);
    StringBuffer sb= new StringBuffer();
    String sumsql=null;
    sb.append("select count(*) bs,sum(c.sjje) sbjehj , sum(decode(substr(c.zwbs, 1, 1), '0', 0, c.rkje)) rkjehj,(sum(c.sjje)-sum(decode(substr(zwbs, 1, 1), '0', 0, c.rkje))) cehj");
    sb.append(",count(distinct c.jsjdm) hs");
    sb.append(" from sbdb.sb_jl_sbjkzb c,djdb.dj_jl_jbsj b,DMDB.KJ_DM_SKLX d");
    sb.append(whereBuffer.toString());
    sumsql=sb.toString();
    System.out.println("sumsql:"+sumsql);
    Map sqlMap = new HashMap();
    sqlMap.put("sql", sql);
    sqlMap.put("sumsql",sumsql);

    return sqlMap;
  }

  /**
   * 登记 注册类型where条件
   * @param allDjzclx
   * @return
   */
  private String getDjzclxWhere(String allDjzclx) {
    String djzclxWhere = "";
    ArrayList tempList = new ArrayList();
    System.out.println("allDjzclx:" + allDjzclx);
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
    System.out.println("*******************************");
    System.out.println("djzclxWhere : " + djzclxWhere);
    System.out.println("*******************************");
    return djzclxWhere;
  }

  /**
   * doQueryA
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doQuery(VOPackage vo) throws BaseException {
    //1.申明句柄
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
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
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setJxList(this.getJxList(da, ud));

      ///3.3.3.设置登记注册类型列表
      pf.setDjzclx(this.getDjzclxList());
      ///3.5.设置税款类型
      pf.setSklxlist(this.getSklxList(da));

      //to do-------------------------
      try{
        tmpList = queryWSB(da, pf);
      }catch(Exception e){
        e.printStackTrace(
            );

      }
      if(tmpList.size()>=1)
     {
       ResultSet rs=null;
       String sumsql = (String)this.getSql(pf).get("sumsql");
       rs=da.querySQL(sumsql);

       while (rs.next()){
         pf.setBs(rs.getString(1));
         pf.setSbjehj(rs.getString(2));
         pf.setRkjehj(rs.getString(3));
         pf.setCehj(rs.getString(4));
         pf.setHs(rs.getString(5));

       }

     }

      System.out.println("----tmpList.size--" + tmpList.size());
      if (null == tmpList) {
        tmpList = new ArrayList();
      }
      ///3.5.获取总页数数据
      pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
      ///3.6.整理查询结果
      dataList = this.getPageDataList(tmpList, pf);
      System.out.println("-------dataList.size:" + dataList.size());
      //tmpList = null;
      pf.setDataList( (ArrayList) dataList);
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

    return returnList;
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
   * 获取页数
   * @param rsCount 查询结果集build
   * @return 页数
   */
  private String getPageTotalCount(int rsCount) {
    //1.句柄申明
    String countTotal = "0";
    //2.开始业务
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
   * 获取当前分页数据集
   * @param tmpList 完整数据集
   * @param pf 页面对象
   * @return 当前分页数据集
   */
  private List getPageDataList(List tmpList, WrkcxActionForm pf) {
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
    //99.返回值
    return end;
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
    //99.返回值
    return start;
  }

  /**
   * 查询无申报户
   * @param ud 用户对象
   * @param pf 页面对象
   * @param da 数据库操作对象
   * @return 查询结果
   * @exception BaseException BaseException
   */
  private List queryWSB(SfDBAccess da, WrkcxActionForm pf) throws
      BaseException {
    //1.句柄申明
    String sql = null;
    List tmpList = new ArrayList();
    ResultSet rs = null;
    WrkVo wv = null;
    //2.整理并创建查询用sql
    sql = (String)this.getSql(pf).get("sql");
    //3.执行查询
    try {
      rs = da.querySQL(sql);
      //4.整理数据
      int count = 1;
      while (rs.next()) {
        wv = new WrkVo();
        wv.setJsjdm(rs.getString(1));
        wv.setNsrmc(rs.getString(2));
        //sklx
        wv.setSklx(rs.getString(3));
        //yskmmc
        wv.setYskmmc(rs.getString(4));
        //szmc
        wv.setSzmc(rs.getString(5));
        //sbrq
        wv.setSbrq(rs.getString(6));
        //yhsksj
        if (null == rs.getString(7) || "".equals(rs.getString(7))) {
          wv.setYhsksj("");
        }
        else {
          wv.setYhsksj(rs.getString(7));
        }

        //xjrq

        if (null == rs.getString(8) || "".equals(rs.getString(8))) {
          wv.setXjrq("");
        }
        else {
          wv.setXjrq(rs.getString(8));
        }
        //sjje
        wv.setSjje(rs.getString(9));

        //zyrq 入库日期
        if (null == rs.getString(10) || "".equals(rs.getString(10))) {
          wv.setZyrq("&nbsp;");
        }
        else {
          wv.setZyrq(rs.getString(10));
        }
        //rkje

        if (null == rs.getString(11) || "".equals(rs.getString(11))) {
          wv.setRkje("0");
        }
        else {
          wv.setRkje(rs.getString(11));
        }

        //skssksrq
        if (null == rs.getString(12) || "".equals(rs.getString(12))) {
          wv.setSkssksrq("");
        }
        else {
          wv.setSkssksrq(rs.getString(12));
        }

        //skssjsrq
        if (null == rs.getString(13) || "".equals(rs.getString(13))) {
          wv.setSkssjsrq("");
        }
        else {
          wv.setSkssjsrq(rs.getString(13));
        }

        //clbj
        if ("10".equals(rs.getString(14))) {
          wv.setClbj("未处理");
        }
        if ("11".equals(rs.getString(14))) {
          wv.setClbj("已申报");
        }
        if ("12".equals(rs.getString(14))) {
          wv.setClbj("已缴款");
        }
        if ("14".equals(rs.getString(14))) {
          wv.setClbj("已完税");

          //zwbs

        }
        wv.setZwbs(rs.getString(15));
        if ("0".equals(rs.getString(15).substring(0, 1))) {
          wv.setZyrq("");
          wv.setRkje("0");
        }

        //hxrmc
        if (null == rs.getString(16) || "".equals(rs.getString(16))) {
          wv.setHxrmc("");
        }
        else {
          wv.setHxrmc(rs.getString(16));
        }

        //lrr
        if (null == rs.getString(17) || "".equals(rs.getString(17))) {
          wv.setLrr("");
        }
        else {
          wv.setLrr(rs.getString(17));
        }

        //lrrq
        wv.setLrrq(rs.getString(18));

        //hxrq
        if (null == rs.getString(19) || "".equals(rs.getString(19))) {
          wv.setHxrq("");
        }
        else {
          wv.setHxrq(rs.getString(19));
        }

        //sjly
        if (null == rs.getString(20) || "".equals(rs.getString(20))) {
          wv.setSjly("");
        }
        else {
          if (CodeConstant.SMSB_SJLY_DR.equals(rs.getString(20))) {
            wv.setSjly("导入");
          }
          if (CodeConstant.SMSB_SJLY_SBLR.equals(rs.getString(20))) {
            wv.setSjly("申报录入");
          }
          if (CodeConstant.SMSB_SJLY_LSHZ.equals(rs.getString(20))) {
            wv.setSjly("零散汇总");
          }
          if (CodeConstant.SMSB_SJLY_GTGSHHZ.equals(rs.getString(20))) {
            wv.setSjly("个体工商户汇总");
          }
          if (CodeConstant.SMSB_SJLY_YHSHZ.equals(rs.getString(20))) {
            wv.setSjly("印花税汇总");
          }
          if (CodeConstant.SMSB_SJLY_SDHZ.equals(rs.getString(20))) {
            wv.setSjly("三代汇总");
          }
          if (CodeConstant.SMSB_SJLY_GTGSHLR.equals(rs.getString(20))) {
            wv.setSjly("个提工商户录入的缴款书");
          }
          if (CodeConstant.SMSB_SJLY_LSZSLR.equals(rs.getString(20))) {
            wv.setSjly("零散税征收录入的缴款书");
          }
          if (CodeConstant.SMSB_SJLY_ZRRLR.equals(rs.getString(20))) {
            wv.setSjly("自然人申报录入");
          }
          if (CodeConstant.SJLY_DQDEDR.equals(rs.getString(20))) {
            wv.setSjly("定期定额国地共管户银行扣款导入");
          }
        }

        //zsswjg
        wv.setZsswjg(rs.getString(21));
        String sjje = wv.getSjje();
        String rkje = wv.getRkje();
        String ce = String.valueOf(Double.parseDouble(sjje) -
                                   Double.parseDouble(rkje));
        //差额
        wv.setCe(ce);
        //经营电话
        wv.setJydh(rs.getString(22));
        //所简称
        wv.setSjc(rs.getString(23));

        //more........
        tmpList.add(wv);
      }

      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new ApplicationException("查询申报入库不一致信息数据失败");
    }catch (Exception e) {
      e.printStackTrace();
      throw new ApplicationException("查询申报入库不一致信息数据失败");
    }
    //99.返回值
    return tmpList;
  }

  /**
   * 依据当前查询结果生成Excel文件
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
   * @throws BaseException
   */
  private Object doSaveExcel(VOPackage vo) throws BaseException {
    System.out.println("-------------------------doSaveExcel------------");
    //1.申明句柄
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.初始化VO数据对象
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
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
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.根据ud的用户级别决定税务机关组织机构代码的值列表
      pf.setJxList(this.getJxList(da, ud));

      ///3.3.3.设置登记注册类型列表
      //to do -------------------------------------------------------

      tmpList = queryWSB(da, pf);
      if(tmpList.size()>=1)
     {
       ResultSet rs=null;
       String sumsql = (String)this.getSql(pf).get("sumsql");
       rs=da.querySQL(sumsql);

       while (rs.next()){
         pf.setBs(rs.getString(1));
         pf.setSbjehj(rs.getString(2));
         pf.setRkjehj(rs.getString(3));
         pf.setCehj(rs.getString(4));
         pf.setHs(rs.getString(5));

       }

     }

      System.out.println("----tmpList.size--" + tmpList.size());

      if (null == tmpList) {
        tmpList = new ArrayList();
      }

      pf.setDataList( (ArrayList) tmpList);
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
  private String getSumSql(){
    StringBuffer sb= new StringBuffer();
    sb.append("");
    return null;
  }

  /**
 * 获取税款类型列表
 * @param db
 * @param userData
 * @return
 * @throws BaseException
 */
private ArrayList getSklxList(SfDBAccess db) throws
    BaseException {
  ArrayList list = new ArrayList();
  try {

    StringBuffer sb = new StringBuffer();
    sb.append(
        " SELECT sklxdm value,sklxmc descr FROM DMDB.KJ_DM_SKLX ");
   sb.append(" where zxbs='0'");
    sb.append(" order by sklxdm ");
    ResultSet rs = db.querySQL(sb.toString());
    while (rs.next()) {
      LabelValueBean bean = new LabelValueBean("", "");
      bean.setValue( (String) rs.getString("value"));
      bean.setLabel( (String) rs.getString("descr"));

        list.add(bean);

    }
    LabelValueBean bean = new LabelValueBean("", "");
    list.add(0,bean);
  }
  catch (SQLException e) {
    throw ExceptionUtil.getBaseException(e);
  }
  return list;
}




}