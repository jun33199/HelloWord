package com.ttsoft.bjtax.shenbao.jiekou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.jiekou.processor.ShenbaoProcessor;
import com.ttsoft.bjtax.shenbao.jiekou.vo.Nspg;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;

//import com.ttsoft.bjtax.shenbao.util.FriendHelper;
//import com.ttsoft.bjtax.shenbao.util.*;

/**
 * �ṩ����˰����ϵͳ�Ľӿڴ���
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class InterFaceCoding {
  //��������ʵ��
  private static InterFaceCoding _instance = null;
  //���캯��
  private InterFaceCoding() {

  }

  /**
   * ��ȡ���൱ǰʵ��
   * @return instance
   */
  public static InterFaceCoding getInstance() {
    if (_instance == null)
      _instance = new InterFaceCoding();
    return _instance;
  }

  /**
   * ɾ���ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ�����,
   * @param jkpzhList �ɿ�ƾ֤��ArrayList
   * @param conn  ���ݿ�����
   * @throws Exception �����쳣 ע�⵱Ҫɾ���Ľɿ�ƾ֤�Ų�����ʱҲ�����쳣
   */
  public void deleteJKS(ArrayList jkpzhList, Connection conn) throws Exception {
    log("=======================deleteJKS start========================");
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw new Exception("�������Ϸ������ύ��ȷ�Ľɿ�ƾ֤�ţ�");
    }
    try {
      //ɾ����ϸ����������
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("JKPZH IN ('" + jkpzhList.get(0) + "'");
      for (int i = 1; i < jkpzhList.size(); i++) {
        sqlBuffer.append(",'" + jkpzhList.get(i) + "'");
      }
      sqlBuffer.append(") AND SJLY = '").append(SBJK_SJLY_NSPGLR)
          .append(
          "' AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //ɾ������

      String sqlZb = wheresql; //��������
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";

      StringBuffer delBuffer = new StringBuffer();
      Statement st = conn.createStatement();
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").append(sqlMx);
      st.addBatch(delBuffer.toString());
      delBuffer.setLength(0);
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE ").append(sqlZb);
      st.addBatch(delBuffer.toString());
      st.executeBatch(); //delete
      delBuffer.setLength(0); //clear
      sqlBuffer.setLength(0); //clear
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--delete data failed!");
    }
    log("=======================deleteJKS end========================");
  }

  /**
   * ���ݽɿ�ƾ֤�Ų�ѯ�ü�¼�Ƿ�����⼰�������
   * @param jkpzhList �ɿ�ƾ֤��
   * @param conn  ���ݿ�����
   * @return ArrayList ���������б�
   * @throws java.lang.Exception
   */
  public ArrayList queryDataByJkpzh(ArrayList jkpzhList, Connection conn) throws
      Exception {
    log("=======================queryDataByJkpzh start========================");
    if (jkpzhList == null || jkpzhList.size() == 0 || conn == null)
      throw new Exception("error:��������Ϊ�գ�");

    try {
      ArrayList resultList = new ArrayList(); //define result
      ArrayList sqlList = new ArrayList(); //����in��max_countһ��

      int c = jkpzhList.size();
      int n = 0;

      while (n < c) {
        StringBuffer s = new StringBuffer();
        s.append(" JKPZH IN ('").append(jkpzhList.get(n)).append("'");
        for (int i = 1; i < MAX_COUNT; i++) {
          n = n + 1;
          if (n >= c)
            break;
          s.append(",'").append(jkpzhList.get(n)).append("'");
        }
        n = n + 1;
        s.append(") ");
        sqlList.add(s);
      }

      String sqlHead =
          "SELECT JKPZH, RKJE, ZYRQ, ZWBS FROM SBDB.SB_JL_SBJKZB WHERE ";

      String sqlEnd = " ORDER BY JKPZH";
      Statement st = conn.createStatement();
      ResultSet rs = null;
      for (int i = 0; i < sqlList.size(); i++) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlWhere = (StringBuffer) sqlList.get(i);
        sql.append(sqlHead).append(sqlWhere.toString()).append(sqlEnd);
        rs = st.executeQuery(sql.toString());
        while (rs.next()) {
          Nspg nspg = new Nspg();
          nspg.setJkpzh(rs.getString("jkpzh"));
          nspg.setRkje(rs.getBigDecimal("rkje"));
          nspg.setZyrq(rs.getTimestamp("zyrq"));
          //�����ʶ��һλΪ0����ʾδ���
          if (rs.getString("zwbs").substring(0, 1).equals("0"))
            nspg.setIsYrk(Boolean.FALSE); //δ���
          else
            nspg.setIsYrk(Boolean.TRUE);

          resultList.add(nspg); //add to result list
        }
      }
      log("=======================queryDataByJkpzh end========================");

      return resultList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--query data failed!");
    }
  }

  /**
   * ������˰�����ṩ���걨���ݺ����ݿ����ӣ����ɽɿ�����
   * @param sbsj �걨����
   * @param conn ���ݿ�����
   * @return �ɿ�ֵ����
   * @throws java.lang.Exception
   */
  public DeclareInfor shenbaoJiaokuan(Sbsj sbsj, Connection conn) throws
      Exception {
    log("=======================shenbaoJiaokuan start========================");
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    //�����ɵ����ݲ������ݱ��в�����
    ShenbaoProcessor sbPro = new ShenbaoProcessor();
    //String p = sbPro.getProperty("WSSB_JIEKOU_NSPG_EXCLUDES_NSRZT",conn);
    String n = swdjjbsj.getNsrzt();
    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����

//�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    //   if (n.equals("20") || n.equals("40") || n.equals("50"))

    if (swjgzzjgdm.substring(swjgzzjgdm.length() - 2)
        .equalsIgnoreCase("00")) {
      // ��������
      throw new Exception("����˰��״̬��Ϊ�������������ɽɿ��飡");
    }

    List sbjkmxList = new ArrayList();
    //��д�걨��ϸ��Ϣ
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //��������
    sbjkmx.setLrrq(now); //¼������
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkmxList.add(sbjkmx);

    //�걨��������
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //��˰��Ǽǵõ�������
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //���շ�ʽΪ����
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(second2Day(now));
    sbjkzb.setZyrq(second2Day(now)); //��ҳ����
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    //��ȡ�����ʻ���Ϣ
    List tempList = (List) map.get("YHZH");
    if (tempList != null && tempList.size() != 0) {
      for (int i = 0; i < tempList.size(); i++) {
        YHZH yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
          sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
          sbjkzb.setZh(yhzh.getZh()); //�����˺�
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CLBJDM_YSB); //Ĭ��Ϊ�����걨��

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������

    String sbbh = sbPro.getSbbh(sbsj.getJsjdm());
    List dataList = (List) sbPro.createJkInfor(declareInfor, sbbh, conn);

    //ֻ��һ��Ʊ����Ϊ��ֻ��һ���ɿ���ϸ��¼
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    log("=======================shenbaoJiaokuan end========================");
    return data; //��װ������ݷ���
  }

  //�����ʽ��Timestampת��Ϊ�����ʽ��Timestamp
  public java.sql.Timestamp second2Day(java.sql.Timestamp date) {
    if (date == null)
      return null;
    java.sql.Timestamp tempStamp = null;

    try {
      String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
      tempStamp = java.sql.Timestamp.valueOf(tempStr + " 00:00:00.000");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return tempStamp;
  }

  /**
   * �ж����������Ƿ�����
   * @param swjgzzjgdm ˰�������֯��������
   * @param yhdm ���д���
   * @param conn ���ݿ�����
   * @return true-��������false-δ����
   * @throws java.lang.Exception �쳣
   */
  public boolean isLw(String swjgzzjgdm, String yhdm, Connection conn) throws
      Exception {
    log("=======================isLw end========================");
    //0.�������
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.��ڲ������
    if (swjgzzjgdm == null || NULL_ZERO.equals(swjgzzjgdm) || yhdm == null ||
        NULL_ZERO.equals(yhdm)) {
      throw new Exception("��ڲ����Ƿ�(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm +
                          ")");
    }
    else if (conn == null) {
      throw new Exception("������ݿ�����Ϊ�գ�");
    }
    //2.��ʼ��
    sb = new StringBuffer();
    //3.ҵ�����
    ///3.0.������ѯSQL����ض���
    String swjgzzjgLwzt = null;
    String yhLwzt = null;
    sb.append("SELECT a.SWJGZZJGDM as dm1,a.LWZT zt1,b.YHDM as dm2,b.LWZT as zt2 FROM DMDB.GY_DM_SWJGZZJG a,DMDB.GY_DM_YH b WHERE a.SWJGZZJGDM='");
    sb.append(swjgzzjgdm);
    sb.append("' AND b.YHDM='");
    sb.append(yhdm);
    sb.append("'");
    sql = sb.toString();
    ///3.1.ִ�в�ѯ
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgLwzt = rs.getString("zt1");
        yhLwzt = rs.getString("zt2");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm +
                            ")�����ݿ���û�л�ö�Ӧ�Ĵ����¼��");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.�ж��Ƿ�����
    if (LWZT_LW.equals(swjgzzjgLwzt) && LWZT_LW.equals(yhLwzt)) {
      rnFlag = true;
      log("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm + ")��Ӧ����������������!");
    }
    else {
      rnFlag = false;
      log("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm + ")��Ӧ����������δ����!");
    }
    //99.����ֵ
    log("=======================isLw end========================");
    return rnFlag;
  }

  /**
   * �ж����������Ƿ�����
   * @param swjgzzjgdm ˰�������֯��������
   * @param conn ���ݿ�����
   * @return true-��������false-δ����
   * @throws java.lang.Exception �쳣
   */
  public boolean isLwByQxdm(String swjgzzjgdm,Connection conn) throws
      Exception {
    log("=======================isLw end========================");
    //0.�������
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.��ڲ������
    if (swjgzzjgdm == null || NULL_ZERO.equals(swjgzzjgdm)) {
      throw new Exception("��ڲ����Ƿ�(swjgzzjgdm=" + swjgzzjgdm + ")");
    }
    else if (conn == null) {
      throw new Exception("������ݿ�����Ϊ�գ�");
    }
    //2.��ʼ��
    sb = new StringBuffer();
    //3.ҵ�����
    ///3.0.������ѯSQL����ض���
    String swjgzzjgLwzt = null;
    sb.append("SELECT a.SWJGZZJGDM as dm1,a.LWZT zt1 FROM DMDB.GY_DM_SWJGZZJG a WHERE a.SWJGZZJGDM='");
    sb.append(swjgzzjgdm);
    sb.append("'");
    sql = sb.toString();
    ///3.1.ִ�в�ѯ
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgLwzt = rs.getString("zt1");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(swjgzzjgdm=" + swjgzzjgdm +
                            ")�����ݿ���û�л�ö�Ӧ�Ĵ����¼��");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.�ж��Ƿ�����
    if (LWZT_LW.equals(swjgzzjgLwzt)) {
      rnFlag = true;
      log("(swjgzzjgdm=" + swjgzzjgdm  + ")��Ӧ������������!");
    }
    else {
      rnFlag = false;
      log("(swjgzzjgdm=" + swjgzzjgdm  + ")��Ӧ������δ����!");
    }
    //99.����ֵ
    log("=======================isLw end========================");
    return rnFlag;
  }


  /**
   * ���ݽɿ�ƾ֤�ŵ������ж��漰���������Ƿ�����
   * @param jkpzh �ɿ�ƾ֤��
   * @param conn ���ݿ�����
   * @return true-��������false-δ����
   * @throws java.lang.Exception �쳣
   */
  public boolean isLwQueryByJkpzh(String jkpzh, Connection conn) throws
      Exception {
    log("=======================isLwQueryByJkpzh start========================");
    //0.�������
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    String swjgzzjgdm = null;
    String yhdm = null;
    //1.��ڲ������
    if (jkpzh == null || NULL_ZERO.equals(jkpzh)) {
      throw new Exception("��ڲ����Ƿ�(jkpzh=" + jkpzh + ")");
    }
    else if (conn == null) {
      throw new Exception("������ݿ�����Ϊ�գ�");
    }
    try {
      stat = conn.createStatement();
      //2.��ȡ�ɿ����ݵ������ʻ���˰�������֯��������
      ///2.0.������ѯSQL����ض���
      sb = new StringBuffer();
      sb.append("SELECT swjgzzjgdm,yhdm FROM SBDB.SB_JL_SBJKZB WHERE JKPZH='");
      sb.append(jkpzh);
      sb.append("'");
      sql = sb.toString();
      ///2.1.ִ�в�ѯ
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgdm = rs.getString("swjgzzjgdm");
        yhdm = rs.getString("yhdm");
      }
      else {
        throw new Exception("(jkpzh=" + jkpzh + ")�����ݿ���û�л�ö�Ӧ�Ĵ����¼��");
      }
      //3.�ж��Ƿ�����ҵ�����
      rnFlag = this.isLw(swjgzzjgdm, yhdm, conn);
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    //99.����ֵ
    log("=======================isLwQueryByJkpzh end========================");
    return rnFlag;
  }

  /**
   * ���ݽɿ�ƾ֤�Ż�ȡ��Ӧ���걨���
   * @param jkpzh �ɿ�ƾ֤��
   * @param conn ���ݿ�����
   * @return �걨���
   * @throws java.lang.Exception �쳣
   */
  public String getSbbhByJkpzh(String jkpzh, Connection conn) throws Exception {
    log("=======================getSbbhByJkpzh start========================");
    //0.�������
    String rnStr = null;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.��ڲ������
    if (jkpzh == null || NULL_ZERO.equals(jkpzh)) {
      throw new Exception("��ڲ����Ƿ�(jkpzh=" + jkpzh + ")");
    }
    else if (conn == null) {
      throw new Exception("������ݿ�����Ϊ�գ�");
    }
    //2.��ʼ��
    sb = new StringBuffer();
    //3.ҵ�����
    ///3.0.������ѯSQL����ض���
    sb.append("SELECT sbbh FROM SBDB.SB_JL_SBJKZB WHERE JKPZH='");
    sb.append(jkpzh);
    sb.append("'");
    sql = sb.toString();
    ///3.1.ִ�в�ѯ
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        rnStr = rs.getString("sbbh");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(jkpzh=" + jkpzh + ")�����ݿ���û�л�ö�Ӧ�Ĵ����¼��");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.����걨��źϷ���
    if (rnStr == null || NULL_ZERO.equals(rnStr) || NULL_STR.equals(rnStr)) {
      throw new Exception("(jkpzh=" + jkpzh + ")�����ݿ��ж�Ӧ���걨���Ϊ�գ�");
    }
    //99.����ֵ
    log("=======================getSbbhByJkpzh end========================");
    return rnStr;
  }

  /**
   * �����걨��Ų�ѯδ�������
   * @param sbbh �ɿ�ƾ֤��
   * @param conn  ���ݿ�����
   * @return ArrayList ���������б� ��Ա����ΪDeclareInfor
   * @throws java.lang.Exception
   */
  public List queryDataBySBBH(String sbbh, Connection conn) throws Exception {
    log("=======================queryDataBySBBH start========================");
    //0.�������
    List rnList = null;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    DeclareInfor declareInfor = null;
    Sbjkzb sbjkzb = null;
    Sbjkmx sbjkmx = null;
    ArrayList jksmxList = null;
    //1.��ڲ������
    if (sbbh == null || NULL_ZERO.equals(sbbh)) {
      throw new Exception("��ڲ����Ƿ�(sbbh=" + sbbh + ")");
    }
    else if (conn == null) {
      throw new Exception("������ݿ�����Ϊ�գ�");
    }
    //2.��ʼ��
    rnList = new ArrayList();
    String jsjdm=sbbh.substring(0,8);
    //3.ҵ�����
    ///3.0.������ض���
    ///3.1.��ȡ����
    try {
      ////3.1.0.��ʼ�����ݿ�
      stat = conn.createStatement();
      ////3.1.1.������ѯ�걨�ɿ������SQL
      sb = new StringBuffer();
      sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
      sb.append(sbbh);
      sb.append("' AND jsjdm='");
      sb.append(jsjdm);
      sb.append("' AND zwbs like '0%0'");
      sql = sb.toString();
      ////3.1.1.ִ�в�ѯ
      rs = stat.executeQuery(sql);
      ////3.1.2.�����걨�ɿ����ݲ��������ݼ�
      while (rs.next()) {
        declareInfor = new DeclareInfor();
        sbjkzb = new Sbjkzb();
        jksmxList = new ArrayList();
        declareInfor.setSbjkzb(sbjkzb);
        declareInfor.setSbjkmxInfo(jksmxList);
        //ֵ����ת��
        sbjkzb.setJkpzh(rs.getString("jkpzh"));
        sbjkzb.setSklxdm(rs.getString("sklxdm"));
        sbjkzb.setJsjdm(rs.getString("jsjdm"));
        sbjkzb.setFsdm(rs.getString("fsdm"));
        sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
        sbjkzb.setYhdm(rs.getString("yhdm"));
        sbjkzb.setYhmc(rs.getString("yhmc"));
        sbjkzb.setZh(rs.getString("zh"));
        sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
        sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
        sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
        sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
        sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
        sbjkzb.setYskmdm(rs.getString("yskmdm"));
        sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
        sbjkzb.setSzdm(rs.getString("szdm"));
        sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
        sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
        sbjkzb.setJksj(rs.getTimestamp("jksj"));
        sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
        sbjkzb.setClbjdm(rs.getString("clbjdm"));
        sbjkzb.setSjje(rs.getBigDecimal("sjje"));
        sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
        sbjkzb.setRkje(rs.getBigDecimal("rkje"));
        sbjkzb.setZwbs(rs.getString("zwbs"));
        sbjkzb.setHxrdm(rs.getString("hxrdm"));
        sbjkzb.setHxrmc(rs.getString("hxrmc"));
        sbjkzb.setLrr(rs.getString("lrr"));
        sbjkzb.setBz(rs.getString("bz"));
        sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
        sbjkzb.setSbbh(rs.getString("sbbh"));
        sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
        sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
        sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
        sbjkzb.setSjly(rs.getString("sjly"));
        sbjkzb.setNd(rs.getString("nd"));
        sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
        sbjkzb.setQxdm(rs.getString("qxdm"));
        sbjkzb.setSphm(rs.getString("sphm"));
        rnList.add(declareInfor);
      }
      rs.close();
      ////3.1.2.��������ݼ��ϵĺϷ���
      if (rnList.size() == 0) {
        throw new Exception("(sbbh=" + sbbh + ")�����ݿ���û�л�ö�Ӧ���걨�ɿ�����δ�������¼��");
      }
      ////3.1.2.������ѯ�걨�ɿ������SQL
      sb = new StringBuffer();
      sb.append("SELECT * FROM SBDB.SB_JL_SBJKMX WHERE sbbh='");
      sb.append(sbbh);
      sb.append("' AND jsjdm='");
      sb.append(jsjdm);
      sb.append("'");
      sql = sb.toString();
      ////3.1.1.ִ�в�ѯ
      rs = stat.executeQuery(sql);
      boolean mxFlag = false;
      while (rs.next()) {
        /////3.1.1.0.�����µ�ֵ����
        sbjkmx = new Sbjkmx();
        mxFlag = false;
        /////3.1.1.1.����ת��
        sbjkmx.setSzsmdm(rs.getString("szsmdm"));
        sbjkmx.setJkpzh(rs.getString("jkpzh"));
        sbjkmx.setJsjdm(rs.getString("jsjdm"));
        sbjkmx.setYskmdm(rs.getString("yskmdm"));
        sbjkmx.setYsjcdm(rs.getString("ysjcdm"));
        sbjkmx.setKssl(rs.getBigDecimal("kssl"));
        sbjkmx.setJsje(rs.getBigDecimal("jsje"));
        sbjkmx.setSjse(rs.getBigDecimal("sjse"));
        sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
        sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
        sbjkmx.setRkje(rs.getBigDecimal("rkje"));
        sbjkmx.setSbbh(rs.getString("sbbh"));
        sbjkmx.setSjfc(rs.getBigDecimal("sjfc"));
        sbjkmx.setQjfc(rs.getBigDecimal("qjfc"));
        sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
        sbjkmx.setNd(rs.getString("nd"));
        sbjkmx.setSl(rs.getBigDecimal("sl"));
        sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
        sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
        sbjkmx.setQxdm(rs.getString("qxdm"));
        /////3.1.1.2.�ж�������һ�����ݼ�����������ݼ�,����Ҳ������׳��쳣
        for (int i = 0; i < rnList.size(); i++) {
          declareInfor = (DeclareInfor) rnList.get(i);
          if (sbjkmx.getJkpzh().equals(declareInfor.getSbjkzb().getJkpzh()) &&
              sbjkmx.getJsjdm().equals(declareInfor.getSbjkzb().getJsjdm())) {
            mxFlag = true;
            declareInfor.getSbjkmxInfo().add(sbjkmx);
            break;
          }
        }
        if (!mxFlag) {
          throw new Exception("�޷��ҵ��걨�ɿ���ϸ[" + sbjkmx.getJkpzh() + "]");
        }
      }
      rs.close();
      ////3.1.2.�����ݼ��Ͻ��кϷ��Լ��
      for (int i = 0; i < rnList.size(); i++) {
        declareInfor = (DeclareInfor) rnList.get(i);
        if (declareInfor.getSbjkmxInfo().size() == 0) {
          throw new Exception("�޷��ҵ���Ӧ���걨�ɿ���ϸ[" +
                              declareInfor.getSbjkzb().getJkpzh() + "]");
        }
      }
      ////3.1.99.�ر����ݿ�����
      stat.close();
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    //99.����ֵ
    log("=======================queryDataBySBBH end========================");
    return rnList;
  }

  public String getSbbh(String jsjdm, Connection conn) throws Exception {
    log("=======================getSbbh start========================");
    //0.�������
    String sequence = "00000000"; //Ĭ��
    String sql = null;
    Statement st = null;
    ResultSet rs = null;
    String sbbh = null;
    //1.��ڲ���У��
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("��������벻��Ϊ�գ�");
    //2.��ʼ��
    //3.ҵ�����
    try {
      //1.������Դ
      st = conn.createStatement();
      //2.����sql��䣬������ȡ��ǰ���
      sql = "SELECT LPAD(SBDB.SEQ_SB_SBBH.NEXTVAL,8,'0') FROM DUAL";
      rs = st.executeQuery(sql);
      if (rs.next()) {
        sequence = rs.getString(1);
      }
      else {
        throw new Exception("�޷���ȡ�걨��Ŵ�������");
      }
      //3.��֯���
      sbbh = jsjdm + getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "��ȡ�걨���ʧ��!");
    }
    finally {
      try {
        rs.close();
        st.close();
      }
      catch (Exception sqle) {
        sqle.printStackTrace();
      }
    }
    //99.����ֵ
    log("=======================getSbbh end========================");
    return sbbh;
  }

  /**
   * ������˰�����ṩ���걨���ݺ����ݿ����ӣ����ɽɿ�����
   * @param sbsj �걨����
   * @param sbbh �걨���
   * @param conn ���ݿ�����
   * @return �ɿ�ֵ����
   * @throws java.lang.Exception
   */
  public DeclareInfor shenbaoJiaokuanWithSbbh(Sbsj sbsj, String sbbh,
                                              Connection conn) throws
      Exception {
    log(
        "=======================shenbaoJiaokuanWithSbbh start========================");
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    //�����ɵ����ݲ������ݱ��в�����
    ShenbaoProcessor sbPro = new ShenbaoProcessor();
    //String p = sbPro.getProperty("WSSB_JIEKOU_NSPG_EXCLUDES_NSRZT",conn);
    String n = swdjjbsj.getNsrzt();
    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����

//�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    //   if (n.equals("20") || n.equals("40") || n.equals("50"))

    if (swjgzzjgdm.substring(swjgzzjgdm.length() - 2)
        .equalsIgnoreCase("00")) {
      // ��������
      throw new Exception("����˰��״̬��Ϊ�������������ɽɿ��飡");
    }

    List sbjkmxList = new ArrayList();
    //��д�걨��ϸ��Ϣ
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //��������
    sbjkmx.setLrrq(now); //¼������
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkmxList.add(sbjkmx);

    //�걨��������
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //��˰��Ǽǵõ�������
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //���շ�ʽΪ����
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(second2Day(now));
    sbjkzb.setZyrq(second2Day(now)); //��ҳ����
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    //��ȡ�����ʻ���Ϣ
    List tempList = (List) map.get("YHZH");
    if (tempList != null && tempList.size() != 0) {
      for (int i = 0; i < tempList.size(); i++) {
        YHZH yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
          sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
          sbjkzb.setZh(yhzh.getZh()); //�����˺�
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CLBJDM_YSB); //Ĭ��Ϊ�����걨��

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    List dataList = (List) sbPro.createJkInfor(declareInfor, sbbh, conn);

    //ֻ��һ��Ʊ����Ϊ��ֻ��һ���ɿ���ϸ��¼
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    log(
        "=======================shenbaoJiaokuanWithSbbh start========================");
    return data; //��װ������ݷ���
  }

  /**
   * ɾ���걨��Ŷ�Ӧ��δ�ɿ��걨����,������걨��Ŷ�Ӧ�����ݼ�����������ѽɿ��������׳��쳣
   * @param sbbh �걨���
   * @param conn  ���ݿ�����
   * @throws Exception �����쳣 ע�⵱Ҫɾ���Ľɿ�ƾ֤�Ų�����ʱҲ�����쳣
   */
  public void deleteSbInfoWithSbbh(String sbbh, Connection conn) throws
      Exception {
    log(
        "=======================deleteSbInfoWithSbbh start========================");
    if (sbbh == null || NULL_ZERO.equals(sbbh)) {
      throw new Exception("��ڲ����Ƿ�(sbbh=" + sbbh + ")");
    }
    String jsjdm=sbbh.substring(0,8);
    try {
      Statement st = conn.createStatement();
      //���ñ��������������Ƿ����
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
      sqlBuffer.append(sbbh);
      sqlBuffer.append("' AND jsjdm='");
      sqlBuffer.append(jsjdm);
      sqlBuffer.append("' AND ((zwbs NOT LIKE '0%') OR (zwbs NOT LIKE '%0'))");
      ResultSet rs = st.executeQuery(sqlBuffer.toString());
      if (rs.next()) {
        log(sbbh + "���漰���걨�ɿ��������в��Ϸ���δ�ɿ�����!");
        rs.close();
        st.close();
        throw new Exception(sbbh + "���漰���걨�ɿ��������в��Ϸ���δ�ɿ�����!");
      }
      rs.close();
      //ɾ����ϸ����������
      sqlBuffer = new StringBuffer();
      String sql = null;
      sqlBuffer.append("sbbh='" + sbbh + "'");
      sqlBuffer.append(" AND SJLY = '").append(SBJK_SJLY_NSPGLR)
          .append(
          "' AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //ɾ������
      String sqlZb = wheresql; //��������
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";
      StringBuffer delBuffer = new StringBuffer();
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").append(sqlMx);
      sql = delBuffer.toString();
      log(sql);
      st.addBatch(sql);
      delBuffer.setLength(0);
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE ").append(sqlZb);
      sql = delBuffer.toString();
      log(sql);
      st.addBatch(sql);
      st.executeBatch(); //delete
      delBuffer.setLength(0); //clear
      sqlBuffer.setLength(0); //clear
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception(ex.getMessage() + "--delete data failed!");
    }
    log(
        "=======================deleteSbInfoWithSbbh end========================");
  }

  private void log(String str) {
    System.out.println("[WSSB FOR NSPG INTERFACE (" + (new Date()) + ")]" + str);
  }

  private String getCurYearStr() {
    Date date = new Date();
    String year = String.valueOf(date.getYear() + 1900);
    year = year.substring(2, 4);
    return year;
  }

  //��˰��������շ�ʽ����
  private final static String FSDM_SM = "1";
  //������Դ����˰����¼��  81
  public final static String SBJK_SJLY_NSPGLR = "81";
  //�����Ǵ��롮���걨���� ��11��
  private final static String CLBJDM_YSB = "11";
  //�ɿ�ƾ֤��ѯ����ÿ������������ܳ���1000
  private final static int MAX_COUNT = 900;
  //����״̬����
  private final static String LWZT_LW = "1";
  //""ֵ����
  private final static String NULL_ZERO = "";
  //"null"ֵ����
  private final static String NULL_STR = "null";

}
