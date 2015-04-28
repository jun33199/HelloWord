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
        throw new ApplicationException("δ�ҵ����������Ĳ���");
    }
  }

  /**
   * ��ʼ������
   * @param vo Value Object
   * @return PageForm
   * @exception BaseException BaseException
   */
  private Object doShow(VOPackage vo) throws BaseException {
    //1.�������
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    ArrayList datalist = new ArrayList();
    //2.��ʼ��VO���ݶ���
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.��ʼҵ��
    try {
      ///3.1.��ʼ������
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setJxList(this.getJxList(da, ud));

      ///3.5.���õǼ�ע������
      pf.setDjzclx(this.getDjzclxList());
      ///3.5.����˰������
      pf.setSklxlist(this.getSklxList(da));
//      List djzclx2 = new ArrayList();
//      djzclx2.add("* ��ѯ�в������ĵǼ�ע������ *");
//      pf.setDjzclx2(djzclx2);
      //���ý������
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
   * ��ȡ���з���������˰����
   * @param ud �û�����
   * @param da ���ݿ��������
   * @return ����������˰����List
   * @throws BaseException �쳣
   */
  /**
   * ��ȡ˰����б�
   * @param db
   * @param userData
   * @return
   * @throws BaseException
   */
  private ArrayList getSwsList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //˰���
      String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
        LabelValueBean label = new LabelValueBean("*����˰����", "0");
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
   * �õ��Ǽ�ע�����ͻ�������
   * @return
   */
  private List getDjzclxList() {
    //1.�������
    String sql = null;
    List dataList = new ArrayList();
    List tmpList = new ArrayList();
    CodeTableInterface ci = null;
    String value = null;
    //2.������ݲ�ѯ������List
    ///2.1.ͨ���ǼǵĴ��빤�߻�ȡ������ҵ״̬�б�
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
    //99.����ֵ
    return dataList;
  }

  /**
   * ��ȡ�����б�
   * @param db
   * @param userData
   * @return
   * @throws BaseException
   */
  private ArrayList getQxList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //˰���
      String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
   * ��ȡ˰����б�
   * @param db
   * @return
   * @throws BaseException
   */
  private ArrayList getJxList(SfDBAccess db, UserData userData) throws
      BaseException {
    ArrayList list = new ArrayList();
    try {
      //˰���
      StringBuffer sb = new StringBuffer();
      String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
      sb.append(
          " select scjxdm as value, scjxmc as descr from dmdb.dj_dm_scjx ");
      if (!"90".equals(iQxdm)) {
        sb.append(" where scjxdm like '" + iQxdm + "%'");
      }
      sb.append(" order by scjxdm ");
      LabelValueBean label = new LabelValueBean("*���н���", "0");
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
   * ���ɲ�ѯ��SQL���
   * @param kf
   * @return   key  =sumSql   ,value String ;key=sumTitle ,value String[] ,key = sumKey ,value String[]
   *           key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
   */
  private Map getSql(WrkcxActionForm kf) {
    //��ѯ����
    StringBuffer sqlBuffer = new StringBuffer();
    String sumSql = null;
    StringBuffer whereBuffer = new StringBuffer();
    //String djzclxWhere = this.getDjzclxWhere(kf.getAlldjzclx());
    String djzclxWhere = null;
    whereBuffer.append(" where c.jsjdm=b.jsjdm");
    whereBuffer.append(" and d.sklxdm=c.sklxdm");
    //�걨������
    whereBuffer.append(" and c.sbrq >= " + SBStringUtils.getSQLDate(kf.getSbrqq()));
    //ǰ��30��
    whereBuffer.append(" and c.zyrq >= " + SBStringUtils.getSQLDate(kf.getSbrqq())+"-30");
    //����90��
    //whereBuffer.append(" and c.zyrq <= " + SBStringUtils.getSQLDate(kf.getSbrqq())+"+90");
    //�걨ֹ����
    whereBuffer.append(" and c.sbrq <= " + SBStringUtils.getSQLDate(kf.getSbrqz()));
    System.out.println("sklxdm:"+kf.getSklxdm());

    //˰������
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


    //�Ǽ�ע�����ʹ���
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

    //����
    if (!"9000".equals(kf.getQueryfj())) {
      whereBuffer.append(" and c.SWJGZZJGDM like '" +
                         kf.getQueryfj().substring(0, 2) + "%'");
    }
    //˰����
    if (!"".equals(kf.getQuerysws())) {
      whereBuffer.append(" and c.SWJGZZJGDM ='" + kf.getQuerysws() + "'");
    }
    //����
    if (!"".equals(kf.getQueryjx())) {
      whereBuffer.append(" and b.SCJXDM ='" + kf.getQueryjx() + "'");
    }
    //ҵ������
    whereBuffer.append(" and (substr(c.zwbs,1,1) ='0' or c.sjje<>c.rkje)");
    if (null != djzclxWhere || "".equals(djzclxWhere)) {
      whereBuffer.append(" and " + djzclxWhere);
    }
    //�޽�����
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
    //�����տ�ʱ��
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
   * �Ǽ� ע������where����
   * @param allDjzclx
   * @return
   */
  private String getDjzclxWhere(String allDjzclx) {
    String djzclxWhere = "";
    ArrayList tempList = new ArrayList();
    System.out.println("allDjzclx:" + allDjzclx);
    //��allDjzclx�г�ȡ�����еĵǼ����ʹ���ţ����ұ�����list�У�
    for (int i = 0; i < allDjzclx.length(); i++) {
      if (allDjzclx.substring(i, i + 1).equals("|")) {
        String tempString = allDjzclx.substring(i - 3, i);
        tempList.add(tempString);
      }
    }

    //����list�еĵǼ����ʹ���ţ�����djzclx��where���֡�
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
    //1.�������
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.��ʼ��VO���ݶ���
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.��ʼҵ��
    try {
      ///3.1.��ʼ������
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setJxList(this.getJxList(da, ud));

      ///3.3.3.���õǼ�ע�������б�
      pf.setDjzclx(this.getDjzclxList());
      ///3.5.����˰������
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
      ///3.5.��ȡ��ҳ������
      pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
      ///3.6.�����ѯ���
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
   * �õ�û�б�ѡ�ĵǼ�ע������
   * @param allDjzclxList
   * @param yxDjzclxList
   * @return
   */
  private List getNoYxDjzclxList(List allDjzclxList, List yxDjzclxList) {
    List returnList = new ArrayList();
    returnList.add("* ��ѯ�����в������ĵǼ�ע������ *");

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
   * �õ����б�ѡ�ĵǼ�ע������
   * @param allDjzclxList
   * @param allDjzclx
   * @return
   */
  private List getYxDjzclxList(List allDjzclxList, String allDjzclx) {
    List returnList = new ArrayList();
    returnList.add("* ��ѯ�����а����ĵǼ�ע������ *");

    List tempList = new ArrayList();
    //��allDjzclx�г�ȡ�����еĵǼ����ʹ���ţ����ұ�����list�У�
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
   * ��ȡҳ��
   * @param rsCount ��ѯ�����build
   * @return ҳ��
   */
  private String getPageTotalCount(int rsCount) {
    //1.�������
    String countTotal = "0";
    //2.��ʼҵ��
    int pageCount = 0;
    if ( (rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
      pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
    }
    else {
      pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
    }
    countTotal = String.valueOf(pageCount);
    //99.����ֵ
    return countTotal;
  }

  /**
   * ��ȡ��ǰ��ҳ���ݼ�
   * @param tmpList �������ݼ�
   * @param pf ҳ�����
   * @return ��ǰ��ҳ���ݼ�
   */
  private List getPageDataList(List tmpList, WrkcxActionForm pf) {
    //1.�������
    List dataList = new ArrayList();
    //2.��ʼ������ֵ
    int startIndex = this.getPageStartIndex(pf.getNextPage(), pf.getTotalpage());
    int endIndex = this.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
    //3.��ʼҵ��
    for (int i = startIndex; i < endIndex; i++) {
      if (i < tmpList.size()) {
        dataList.add(tmpList.get(i));
      }
    }
    tmpList = null;
    //99.����ֵ
    return dataList;
  }

  /**
   * ��ȡ��ǰҳ����index
   * @param nextPage ��һҳ
   * @param countTotal ��ҳ��
   * @return ����index
   */
  private int getPageEndIndex(String nextPage, String countTotal) {
    //1.�������
    int iNextPage = Integer.parseInt(nextPage);
    int iCountTotal = Integer.parseInt(countTotal);
    int end = -1;
    //2.��ʼҵ��
    end = iNextPage * CodeConstant.SD_PG_LENGTH;
    //99.����ֵ
    return end;
  }

  /**
   * ��ȡ��ǰҳ��ʼindex
   * @param nextPage ��һҳ
   * @param countTotal ��ҳ��
   * @return ��ʼindex
   */
  private int getPageStartIndex(String nextPage, String countTotal) {
    //1.�������
    int iNextPage = Integer.parseInt(nextPage);
    int iCountTotal = Integer.parseInt(countTotal);
    int start = -1;
    //2.��ʼҵ��
    start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
    //99.����ֵ
    return start;
  }

  /**
   * ��ѯ���걨��
   * @param ud �û�����
   * @param pf ҳ�����
   * @param da ���ݿ��������
   * @return ��ѯ���
   * @exception BaseException BaseException
   */
  private List queryWSB(SfDBAccess da, WrkcxActionForm pf) throws
      BaseException {
    //1.�������
    String sql = null;
    List tmpList = new ArrayList();
    ResultSet rs = null;
    WrkVo wv = null;
    //2.����������ѯ��sql
    sql = (String)this.getSql(pf).get("sql");
    //3.ִ�в�ѯ
    try {
      rs = da.querySQL(sql);
      //4.��������
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

        //zyrq �������
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
          wv.setClbj("δ����");
        }
        if ("11".equals(rs.getString(14))) {
          wv.setClbj("���걨");
        }
        if ("12".equals(rs.getString(14))) {
          wv.setClbj("�ѽɿ�");
        }
        if ("14".equals(rs.getString(14))) {
          wv.setClbj("����˰");

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
            wv.setSjly("����");
          }
          if (CodeConstant.SMSB_SJLY_SBLR.equals(rs.getString(20))) {
            wv.setSjly("�걨¼��");
          }
          if (CodeConstant.SMSB_SJLY_LSHZ.equals(rs.getString(20))) {
            wv.setSjly("��ɢ����");
          }
          if (CodeConstant.SMSB_SJLY_GTGSHHZ.equals(rs.getString(20))) {
            wv.setSjly("���幤�̻�����");
          }
          if (CodeConstant.SMSB_SJLY_YHSHZ.equals(rs.getString(20))) {
            wv.setSjly("ӡ��˰����");
          }
          if (CodeConstant.SMSB_SJLY_SDHZ.equals(rs.getString(20))) {
            wv.setSjly("��������");
          }
          if (CodeConstant.SMSB_SJLY_GTGSHLR.equals(rs.getString(20))) {
            wv.setSjly("���Ṥ�̻�¼��Ľɿ���");
          }
          if (CodeConstant.SMSB_SJLY_LSZSLR.equals(rs.getString(20))) {
            wv.setSjly("��ɢ˰����¼��Ľɿ���");
          }
          if (CodeConstant.SMSB_SJLY_ZRRLR.equals(rs.getString(20))) {
            wv.setSjly("��Ȼ���걨¼��");
          }
          if (CodeConstant.SJLY_DQDEDR.equals(rs.getString(20))) {
            wv.setSjly("���ڶ�����ع��ܻ����пۿ��");
          }
        }

        //zsswjg
        wv.setZsswjg(rs.getString(21));
        String sjje = wv.getSjje();
        String rkje = wv.getRkje();
        String ce = String.valueOf(Double.parseDouble(sjje) -
                                   Double.parseDouble(rkje));
        //���
        wv.setCe(ce);
        //��Ӫ�绰
        wv.setJydh(rs.getString(22));
        //�����
        wv.setSjc(rs.getString(23));

        //more........
        tmpList.add(wv);
      }

      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new ApplicationException("��ѯ�걨��ⲻһ����Ϣ����ʧ��");
    }catch (Exception e) {
      e.printStackTrace();
      throw new ApplicationException("��ѯ�걨��ⲻһ����Ϣ����ʧ��");
    }
    //99.����ֵ
    return tmpList;
  }

  /**
   * ���ݵ�ǰ��ѯ�������Excel�ļ�
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doSaveExcel(VOPackage vo) throws BaseException {
    System.out.println("-------------------------doSaveExcel------------");
    //1.�������
    WrkcxActionForm pf = null;
    UserData ud = null;
    SfDBAccess da = null;
    Connection conn = null;
    List tmpList = null;
    List dataList = new ArrayList();
    //2.��ʼ��VO���ݶ���
    try {
      ud = vo.getUserData();
      pf = (WrkcxActionForm) vo.getData();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.��ʼҵ��
    try {
      ///3.1.��ʼ������
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setFjList(this.getQxList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setSwsList(this.getSwsList(da, ud));
      ///3.2.����ud���û��������˰�������֯���������ֵ�б�
      pf.setJxList(this.getJxList(da, ud));

      ///3.3.3.���õǼ�ע�������б�
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
 * ��ȡ˰�������б�
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