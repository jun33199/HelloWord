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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �걨�ṩ��һЩ�����ӿ�(processor��)</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0 2003-8-22
 */
public class InterFaceProcessor {
  protected static final int SESSION_ID = 0;

  private final static int JKPZH = 0;
  private final static int JKSINFOR = 1;

  //��˰��������շ�ʽ����
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
   * �õ��ɿ�ƾ֤��  15λ
   * (�ɿ�ƾ֤������Ϊ16λ���˴���õ�ֻ��ǰ15λ�����һλ��ˮ����Ҫ�������Լ���ӣ�
   * �����һƱһ˰����ӡ�0���������һƱ��˰���������1��4�����Ϊ4��)
   * @param jsjdm ���������
   * @return �ɿ�ƾ֤��
   * @throws BaseException �����쳣
   * ��������:
   * ----���������(8λ)���꣨2λ�����£�2λ����3λ˳���<br>
   * ----˳���Ϊ��ǰ��������뱾�µļ�¼����1<br>
   * ----��ˮ��ΪһƱ��˰�����һ��ƾ֤�ж����Ŀ��˳���<br>
   */
  public static String getJkpzh(String jsjdm) throws BaseException {
    String jkpzh = null;
    String sequence = null; //3λ˳���
    try {
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(new Date()); //��ǰ����:yyMM��ϵͳʱ��
      //�õ�¼�����ڵ����£�6λ��
      SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyyyMM");
      String yyyyMM = simpleDataFormats.format(new Date());
      //�õ�3λ˳���
      sequence = getXh(jsjdm, yyyyMM);
      //ת��˳��ŵĸ�ʽ��Ϊ3λ����������������߲�0
      //NumberFormat nmbFormat = new DecimalFormat("000");

      //�����(ASCII)��ת��Ϊ�ַ���
      //sequence = nmbFormat.format(TinyTools.asciiToString(Integer.parseInt(sequence) + 1));

      //�������ASCII��
      String sequenceAscii = TinyTools.stringToASCII(TinyTools.asciiToString(
          Integer.parseInt(sequence) + 1));
      //��ʽ��Ϊ"000"��ʽ
      sequence = TinyTools.xhFormat(
          TinyTools.asciiToString(Integer.parseInt(sequenceAscii)));

      jkpzh = jsjdm + yyMM + sequence;
      return jkpzh;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "ȡ�ɿ�ƾ֤��ʧ�ܣ��������Ա��ϵ��");
    }
  }

  /**
   * �õ���ǰ����������ڱ��µĽɿ�ƾ֤��������3λ˳���
   * @param jsjdm ���������
   * @return sequence �˼���������Ӧ�ı��½ɿ������нɿ�ƾ֤��������3λ˳������
   * ���´˼�����û���û�нɿ�ƾ֤���򷵻ء�0��
   * @throws BaseException �����쳣
   */
  public static String getSequenceOfJkpzh(String jsjdm) throws BaseException {
    String sequence = null;
    //���صĽɿ���ƾ֤��
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
    String yyMM = simpleDataFormat.format(new Date());
    try {
      //�������ASCII��ת��Ϊ�ַ���
      sequence = TinyTools.asciiToString(Integer.parseInt(getXh(jsjdm, yyMM)));

      return sequence;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�ƾ֤�ŵ����˳���ʧ��!");
    }
  }

  /**
   * �����������ȡ����ʱ���������ж�Ӧ���У�ֱ�����½���������
   * @param jsjdm ���������8λ
   * @param ny ����yyyyMM��λ
   * @return String xh
   * @throws BaseException
   */
  public static String getXh(String jsjdm, String ny) throws BaseException {
    if (jsjdm == null || jsjdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("��������벻��Ϊ�գ�"));
    if (ny == null || ny.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("¼�����ڲ���Ϊ�գ�"));
    System.out.println("*****************************************************");
    System.out.println("InterfaceProcessor.getXh:" + "jsjdm=" + jsjdm +
                       "  and  " + "ny=" + ny);
    System.out.println("*****************************************************");

    Connection conn = null;
    String sequence = "0"; //Ĭ��
    int xh = 0; //Ĭ��Ϊ0
    try {
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
      sqlStringBuffer.append(
          "SELECT XH FROM SBDB.SB_JL_JKPZHKZ WHERE JSJDM = '")
          .append(jsjdm)
          .append("' AND NY='").append(ny)
          .append("' FOR UPDATE");
      //   System.out.println("InterfaceProcessor.getXh:"+"sqlselect="+sqlStringBuffer.toString());
      //������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        xh = rs.getInt("xh");
      }
      sqlStringBuffer.setLength(0);
      Statement st1 = conn.createStatement();

      //�������
      //�������
      String xhStr = "";
      if (xh == 0) {
        xhStr = "49"; //��1(ASCII:49)��ʼ
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
        //���û����ţ���ʼ��һ�����'1'�����Ǵ洢��ΪASCII��'49'
        sqlStringBuffer.setLength(0);
        Statement stInsert = conn.createStatement();
        sqlStringBuffer.append(
            "INSERT INTO SBDB.SB_JL_JKPZHKZ (JSJDM, XH, NY) VALUES('")
            .append(jsjdm).append("','49','").append(ny).append("')");
        //            System.out.println("InterfaceProcessor.getXh:"+"sqlinsert="+sqlStringBuffer.toString());
        stInsert.addBatch(sqlStringBuffer.toString());
        stInsert.executeBatch();
      }
      sequence = String.valueOf(xh); //��ǰ���ú�����xh+1���˴�ֻ�������õ�������
      System.out.println("----------------------------------------------------");
      System.out.println("XH sequence == " + sequence + "  jsjdm== " + jsjdm);
      System.out.println("----------------------------------------------------" +
                         sequence);
      return sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "��ѯ�ü���������Ӧ�����ʧ��!");
    }
    finally {
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ͨ���ɿ�ƾ֤�ŵõ����Ľɿ�����(DeclareInfo:sbjkzb��sbjkmxList)  ��Ŀǰ������һƱһ˰�Ľɿ��飩
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @return �ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ������б�
   * @throws BaseException �����쳣
   */
  public static List getJkDataByJkpzh(String[] jkpzh) throws BaseException {
    //�������
    if (jkpzh.length == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����Ľɿ�ƾ֤��Ϊ�գ�"));
    }
    ArrayList dataList = new ArrayList(); //���������б�
    ArrayList zbResult = new ArrayList(); //���������ѯ�����
    ArrayList mxResult = new ArrayList(); //������ϸ��ѯ�����
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    ORManager orManager = null;
    try {
      //�����걨�ɿ��������declareInfo
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(jkpzh[0]);
      for (int i = 1; i < jkpzh.length; i++) {
        sqlStrBuf.append("','").append(jkpzh[i]);
      }
      sqlStrBuf.append("') AND SUBSTR(ZWBS,2,1)='1') ORDER BY JKPZH");
      String sqlString = sqlStrBuf.toString(); //���ɿ�ƾ֤���б��е����м�¼����ѯ����

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //�����ѯ���
      if (zbResult.size() == 0) {
        return dataList; //û�����������ļ�¼������һ���յ�ArrayList
      }

      //ȡ��ϸ����
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //ȡ��һ���ɿ�����
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //ȡ��һ���ɿ�����
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //��ѯ��ϸ���ݲ����declareInfo��������ͬ�Ľɿ�ƾ֤��
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //�����ϸ���������������ݣ������ݲ���ƥ��
        throw ExceptionUtil.getBaseException(
            new Exception("�ɿ����ݲ�ƥ�䣬�������ݹ���Ա��ϵ��"), "");
      }
      //�����ӱ����ݰ���DeclareInfo������з�װ
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //���ؽɿ�ƾ֤�����
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�δ�õ��ɿ����ݣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ͨ��˰Ʊ����õ����Ľɿ�����(DeclareInfo:sbjkzb��sbjkmxList)
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @return �ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ������б�
   * @throws BaseException �����쳣
   */
  public static List getJkDataBySphm(String[] sphm) throws BaseException {
    //�������
    if (sphm.length == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����Ľɿ�ƾ֤��Ϊ�գ�"));
    }
    ArrayList dataList = new ArrayList(); //���������б�
    ArrayList zbResult = new ArrayList(); //���������ѯ�����
    ArrayList mxResult = new ArrayList(); //������ϸ��ѯ�����
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    ORManager orManager = null;
    try {
      //�����걨�ɿ��������declareInfo
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(SPHM IN ('");
      sqlStrBuf.append(sphm[0]);
      for (int i = 1; i < sphm.length; i++) {
        sqlStrBuf.append("','").append(sphm[i]);
      }
      sqlStrBuf.append("') AND SUBSTR(ZWBS,2,1)='1') ORDER BY JKPZH");
      String sqlString = sqlStrBuf.toString(); //���ɿ�ƾ֤���б��е����м�¼����ѯ����

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //�����ѯ���
      if (zbResult.size() == 0) {
        return dataList; //û�����������ļ�¼������һ���յ�ArrayList
      }

      //ȡ��ϸ����
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //ȡ��һ���ɿ�����
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //ȡ��һ���ɿ�����
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //��ѯ��ϸ���ݲ����declareInfo��������ͬ�Ľɿ�ƾ֤��
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //�����ϸ���������������ݣ������ݲ���ƥ��
        throw ExceptionUtil.getBaseException(
            new Exception("�ɿ����ݲ�ƥ�䣬�������ݹ���Ա��ϵ��"), "");
      }
      //�����ӱ����ݰ���DeclareInfo������з�װ
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //���ؽɿ�ƾ֤�����
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�δ�õ��ɿ����ݣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ��������������ѯ�ɿ�����(DeclareInfo:sbjkzb��sbjkmxList)
   *  ��Ŀǰ������һƱһ˰�Ľɿ��飩
   * @param jsjdm ��˰�˼��������
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @param sjly ������Դ
   * @param fsdm ��ʽ����
   * @param zwbs �����ʶ�ж�
   * @return �ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ������б�
   * @throws BaseException �����쳣
   */
  public static List searchJkData(String jsjdm, String[] jkpzh, String sjly,
                                  String fsdm, int zwbs) throws BaseException {
    //�������
    if ( (jkpzh == null || jkpzh.length == 0) &&
        (jsjdm == null || jsjdm.length() <= 0)) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����Ľɿ�ƾ֤�źͼ�������벻��ͬʱΪ�գ�"));
    }
    ArrayList dataList = new ArrayList(); //���������б�
    ArrayList zbResult = new ArrayList(); //���������ѯ�����
    ArrayList mxResult = new ArrayList(); //������ϸ��ѯ�����
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    ORManager orManager = null;

    String theZwbs = null;
    if (zwbs == 0) {
      theZwbs = "00000000000000000000";
    }
    else if (zwbs == 1) {
      theZwbs = "01000000000000000000";
    }

    try {
      //�����걨�ɿ��������declareInfo
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

      String sqlString = sqlStrBuf.toString(); //���ɿ�ƾ֤���б��е����м�¼����ѯ����

      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //�����ѯ���
      if (zbResult.size() == 0) {
        return dataList; //û�����������ļ�¼������һ���յ�ArrayList
      }

      //ȡ��ϸ����
      Sbjkzb tmpZb = new Sbjkzb();
      tmpZb = (Sbjkzb) zbResult.get(0); //ȡ��һ���ɿ�����
      sqlStrBuf.setLength(0); //clear
      sqlStrBuf.append("(JKPZH IN ('");
      sqlStrBuf.append(tmpZb.getJkpzh());
      for (int i = 1; i < zbResult.size(); i++) {
        tmpZb = (Sbjkzb) zbResult.get(i); //ȡ��һ���ɿ�����
        sqlStrBuf.append("','").append(tmpZb.getJkpzh());
      }
      sqlStrBuf.append("')) ORDER BY JKPZH");
      sqlString = sqlStrBuf.toString();
      Vector cri = new Vector();
      cri.add(sqlString);
      //��ѯ��ϸ���ݲ����declareInfo��������ͬ�Ľɿ�ƾ֤��
      orCtx = new ORContext(Sbjkmx.class.getName(), cri);
      mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx);
      if (mxResult.size() < zbResult.size()) { //�����ϸ���������������ݣ������ݲ���ƥ��
        throw ExceptionUtil.getBaseException(
            new Exception("�ɿ����ݲ�ƥ�䣬�������ݹ���Ա��ϵ��"), "");
      }
      //�����ӱ����ݰ���DeclareInfo������з�װ
      dataList = packList(zbResult, mxResult, conn);

      return dataList; //���ؽɿ�ƾ֤�����
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�δ�õ��ɿ����ݣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ���ɿ��������ϸ���ݣ����սɿ�ƾ֤����װΪDeclareInfor���󣬲���װ��list�з���
   * @param zb ��������
   * @param mx ��ϸ����
   * @param conn ���ݿ�����
   * @throws BaseException �쳣����
   * @return List DeclareInfor��������
   */
  private static ArrayList packList(ArrayList zb, ArrayList mx, Connection conn) throws
      BaseException {
    ArrayList dataAr = new ArrayList(); //���巵������
    ArrayList groupAr = new ArrayList(); //��������ϸ�����б�Ƕ���б�
    int zbSize = zb.size();
    int mxSize = mx.size();
    String oldJkpzh = ( (Sbjkmx) mx.get(0)).getJkpzh();
    ArrayList tempAr = new ArrayList();
    //��ϸ���ݰ��սɿ�ƾ֤�����򣬿�˳������ͬһ�ɿ�ƾ֤�ŵ����ݷ�װ��һ��list��
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
    //��װ���һ���¼
    ArrayList tmpList = (ArrayList) tempAr.clone();
    groupAr.add(tmpList);
    tempAr.clear();
    //���սɿ�ƾ֤���󣬶����ӱ����ݽ��з�װ
    //˵�����������ݶ��ǰ��սɿ�ƾ֤���������ԣ��������ݺͷ�������ϸ���ݣ���ͬ�ɿ�ƾ֤�ŵ����ݣ�λ����ͬ
    if (zbSize != groupAr.size())
      throw ExceptionUtil.getBaseException(
          new Exception("�ɿ����ݲ�ƥ�䣬�������ݿ����Ա��ϵ��"), "");
    for (int j = 0; j < zbSize; j++) {
      //��������(����)
      Sbjkzb tempZb = (Sbjkzb) zb.get(j);
      String jsjdm = tempZb.getJsjdm();
      SWDJJBSJ tempJbsj = null;
      ZRRJBSJ zrrjbsj = null;
      String key = (String) TypeChecker.isQyyh(jsjdm);
      ServiceProxy serviceProxy = new ServiceProxy();
      //key=1 ��ʾ��ҵ�û���key=2 ��ʾ��Ȼ���û�
      if (key.equals("1")) {
        tempJbsj = (SWDJJBSJ) serviceProxy.getDjInfo(jsjdm).get("JBSJ");
        if (tempJbsj != null) {
          tempZb.setNsrmc(tempJbsj.getNsrmc()); //��˰������
          tempZb.setSwjgzzjgmc(tempJbsj.getSwjgzzjgmc()); //˰�����
          tempZb.setLsgxmc(tempJbsj.getLsgxmc()); //������ϵ
          tempZb.setDjzclxmc(tempJbsj.getDjzclxmc()); //�Ǽ�ע������
          tempZb.setGjbzhymc(tempJbsj.getGjbzhymc()); //��ҵ
          tempZb.setZsswjgzzjgmc(tempJbsj.getSwjgzzjgmc()); //���ջ���
        }
      }
      else if (key.equals("2")) {
        Map tempMap = serviceProxy.getZrrDjInfo(jsjdm);
        zrrjbsj = (ZRRJBSJ) tempMap.get(DjOuterConstant.ZRRJBSJ);
        if (zrrjbsj != null) {
          tempZb.setNsrmc(zrrjbsj.getNsrmc()); //��˰������
          String swjgzzjgdm = zrrjbsj.getSwjgzzjgdm();
          String swjgmc = (String) getSwjgmc(swjgzzjgdm, conn);
          tempZb.setSwjgzzjgmc(swjgmc); //˰�����
          tempZb.setZsswjgzzjgmc(swjgmc); //���ջ���
        }
      }
      else {
        throw ExceptionUtil.getBaseException(new Exception(key),
                                             "δ֪���͵ļ�������룡");
      }

      String szmc = addSzsmMc(tempZb.getSzdm(), conn); //˰������
      tempZb.setSzmc(szmc);
      String yskmmc = addYskmMc(tempZb.getYskmdm(), tempZb.getLrrq(), conn); //Ԥ���Ŀ����
      tempZb.setYskmmc(yskmmc);
      String sklxmc = addSklxMc(tempZb.getSklxdm(), conn); //˰����������
      tempZb.setSklxmc(sklxmc);
      String ysjcmc = addYsjcMc(tempZb.getYsjcdm(), conn); //Ԥ�㼶��
      tempZb.setYsjcmc(ysjcmc);
      String gkmc = addGkMc(tempZb.getSwjgzzjgdm(), conn); //����
      tempZb.setGkzzjgmc(gkmc);

      //��ϸ����
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
    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("û�еõ��ü��������ĵǼ����ݣ�"));
    }

    //�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJWITHYH,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJWITHYH, swdjjbsj)) {
      // ��������
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����˰��״̬��Ϊ�������������ɽɿ��飡"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����
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
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //�걨����
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //��ҳ���ڣ���ʼΪ�걨����
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //���д���
      sbjkzb.setYhmc(yhmc); //��������
      sbjkzb.setZh(zh); //�����˺�
    }
    else {
      List tempList = (List) map.get("YHZH");

      YHZH yhzh = (YHZH) tempList.get(0);
      sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
      sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
      sbjkzb.setZh(yhzh.getZh()); //�����˺�
      for (int i = 0; i < tempList.size(); i++) {
        yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
          sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
          sbjkzb.setZh(yhzh.getZh()); //�����˺�
          break;
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //Ĭ��Ϊ�����걨��

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //Ĭ��ΪһƱһ˰

    //�����ɵ����ݲ������ݱ��в�����
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbsj.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //ֻ��һ��Ʊ����Ϊ��ֻ��һ���ɿ���ϸ��¼
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //��װ������ݷ���
  }

  /**ת��������ϵͳ�������걨����Ϊ�걨�ɿ�������걨�ɿ���ϸ����
   * @param sbsj ����ģ����걨����
   * @return DeclareInfor ���ɺõ��걨�ɿ�����
   * @throws BaseException �쳣����
   */
  public static DeclareInfor getJkInforBySbsj(Sbsj sbsj) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("û�еõ��ü��������ĵǼ����ݣ�"));
    }

    //�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // ��������
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����˰��״̬��Ϊ�������������ɽɿ��飡"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����
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
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //�걨����
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //��ҳ���ڣ���ʼΪ�걨����
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());

    List tempList = (List) map.get("YHZH");

    if (tempList != null && tempList.size() != 0) {
      YHZH yhzh = (YHZH) tempList.get(0);
      sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
      sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
      sbjkzb.setZh(yhzh.getZh()); //�����˺�
      for (int i = 0; i < tempList.size(); i++) {
        yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
          sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
          sbjkzb.setZh(yhzh.getZh()); //�����˺�
          break;
        }
      }
    }

    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //Ĭ��Ϊ�����걨��

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //Ĭ��ΪһƱһ˰

    //�����ɵ����ݲ������ݱ��в�����
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbsj.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //ֻ��һ��Ʊ����Ϊ��ֻ��һ���ɿ���ϸ��¼
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //��װ������ݷ���
  }

  /**ת��������ϵͳ������һ���걨����Ϊ�걨�ɿ�������걨�ɿ���ϸ����
   * sbsjList��ȡ��һ�����ɽɿ��������ݣ�������֤Ҳֻ�ǵ�һ��
   * @param sbsj ����ģ����걨����
   * @return DeclareInfor ���ɺõ��걨�ɿ�����
   * @throws BaseException �쳣����
   */
  public static List getJkInforListBySbsj(Map sbsjMap) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    if (sbsjMap == null || sbsjMap.size() < 6) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�ṩ�����ݲ��������ɽɿ��飡"));
    }

    String jsjdm = (String) sbsjMap.get(SBSJZB_JSJDM);
    if (jsjdm == null || jsjdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("��������벻��Ϊ�գ�"));
    }
    String sklxdm = (String) sbsjMap.get(SBSJZB_SKLXDM);
    if (sklxdm == null || sklxdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("˰�����ʹ��벻��Ϊ�գ�"));
    }
    String sjly = (String) sbsjMap.get(SBSJZB_SJLY);
    if (sjly == null || sjly.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("������Դ����Ϊ�գ�"));
    }
    String sbfsdm = (String) sbsjMap.get(SBSJZB_SBFSDM);
    if (sbfsdm == null || sbfsdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�걨��ʽ���벻��Ϊ�գ�"));
    }
    String bz = (String) sbsjMap.get(SBSJZB_BEIZHU);
    String yhdm = (String) sbsjMap.get(SBSJZB_YHDM);
    String yhmc = (String) sbsjMap.get(SBSJZB_YHMC);
    String zh = (String) sbsjMap.get(SBSJZB_ZH);

    BigDecimal sjjehj = (BigDecimal) sbsjMap.get(SBSJZB_SJJE);
    if (sjjehj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("ʵ�ɽ��ϼƲ���Ϊ�գ�"));
    }
    List mxList = (List) sbsjMap.get(SBSJZB_SBJKMX);
    if (mxList == null || mxList.size() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�걨�ɿ���ϸ������һ����"));
    }

    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(jsjdm);
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("û�еõ��ü��������ĵǼ����ݣ�"));
    }

    //�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // ��������
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����˰��״̬��Ϊ�������������ɽɿ��飡"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����
    List sbjkmxList = new ArrayList();
    //��д�걨��ϸ��Ϣ
    for (int i = 0; i < mxList.size(); i++) {
      Map mxMap = (Map) mxList.get(i);
      String szsmdm = (String) mxMap.get(SBSJMX_SZSMDM);
      if (szsmdm == null || szsmdm.length() <= 0) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("˰��˰Ŀ���벻��Ϊ�գ�"));
      }
      BigDecimal sjje = (BigDecimal) mxMap.get(SBSJMX_SJJE);
      BigDecimal jsje = (BigDecimal) mxMap.get(SBSJMX_JSJE);
      BigDecimal kssl = (BigDecimal) mxMap.get(SBSJMX_KSSL);
      BigDecimal sl = (BigDecimal) mxMap.get(SBSJMX_SL);
      if (sjje == null || jsje == null || kssl == null || sl == null) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("����Ϊ�գ�"));
      }
	   //˰��������ʼ���� 
      java.sql.Timestamp skssksrq=mxMap.get(SBSJMX_SKSSKSRQ)==null?null:(java.sql.Timestamp) mxMap.get(SBSJMX_SKSSKSRQ);
      //˰��������������
      java.sql.Timestamp skssjsrq=mxMap.get(SBSJMX_SKSSJSRQ)==null?null:(java.sql.Timestamp) mxMap.get(SBSJMX_SKSSJSRQ);

      Szsm szsm = getSzsm(szsmdm);
      Sbjkmx sbjkmx = new Sbjkmx();
      sbjkmx.setSzsmdm(szsmdm);
      sbjkmx.setZqlxdm(szsm.getZqlxdm()); // �������ʹ���
      sbjkmx.setJsjdm(jsjdm);
      sbjkmx.setSjse(sjje);
      sbjkmx.setRkje(sjje);
      sbjkmx.setKssl(kssl);
      sbjkmx.setSl(sl);
      sbjkmx.setJsje(jsje);
      sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
      sbjkmx.setNd(nd);
      sbjkmx.setCjrq(now); //��������
      sbjkmx.setLrrq(now); //¼������
      sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
	  
	  sbjkmx.setSkssksrq(skssksrq);
      sbjkmx.setSkssjsrq(skssjsrq);

      sbjkmxList.add(sbjkmx);
    }

    //�걨��������
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(jsjdm);
    //��˰��Ǽǵõ�������
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(sbfsdm); //���շ�ʽΪ����
    sbjkzb.setLrr(jsjdm);
    sbjkzb.setRkje(sjjehj);
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //�걨����
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //��ҳ���ڣ���ʼΪ�걨����
    sbjkzb.setSjje(sjjehj);
    sbjkzb.setSklxdm(sklxdm);
    sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
    sbjkzb.setNd(nd);
    sbjkzb.setBz(bz);
    sbjkzb.setSjly(sjly); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //Ĭ��Ϊ�����걨��
    sbjkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // ��ϵ�绰

    List tempList = (List) map.get("YHZH");

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //���д���
      sbjkzb.setYhmc(yhmc); //��������
      sbjkzb.setZh(zh); //�����˺�
    }
    else {
      if (tempList != null && tempList.size() != 0) {
        YHZH yhzh = (YHZH) tempList.get(0);
        sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
        sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
        sbjkzb.setZh(yhzh.getZh()); //�����˺�
        for (int i = 0; i < tempList.size(); i++) {
          yhzh = (YHZH) tempList.get(i);
          if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
            sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
            sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
            sbjkzb.setZh(yhzh.getZh()); //�����˺�
            break;
          }
        }
      }
    }

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //Ĭ��ΪһƱһ˰

    //�����ɵ����ݲ������ݱ��в�����
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    return dataList; //��װ������ݷ���
  }

  /**
   * ��ѯ�����Ľɿ�ƾ֤��list�е����нɿ�ƾ֤���Ƿ��Ѿ����
   * @param jkpzhList �ɿ�ƾ֤�ŵ�list
   * @param conn ���ݿ�����
   * @param bs ���ͱ�ʶ
   * @return ֻҪ��һ���ɿ�ƾ֤��û�����ͷ���FALSE,����ⷵ��TRUE
   * @throws BaseException �����쳣
   */
  protected static boolean isJkpzhInStore(List jkpzhList, Connection conn,
                                          int bs) throws
      BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("Ҫ��ѯ�Ľɿ�ƾ֤�Ų���Ϊ�գ�"));
    }
    //ORʵ��
    ORManager orManager = null;
    try {
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      //��ѯ�ɿ�ƾ֤�ŵĴ�����Ϣ
      Vector criteria = null;
      String jkpzh = null;
      ORContext orCtx = null;
      Sbjkzb sbjkzb = null;
      for (int i = 0; i < jkpzhList.size(); i++) {
        criteria = new Vector();
        jkpzh = (String) jkpzhList.get(i);
        criteria.add("SPHM = '" + jkpzh + "'"); //��������:�ɿ�ƾ֤����ͬ
        orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
        List queryresult = (ArrayList) orManager.query(1, conn, orCtx);
        if (queryresult.size() == 0) {
          if (bs == 0) //�����쳣
            throw ExceptionUtil.getBaseException(
                new ApplicationException("�ɿ�ƾ֤��(" + jkpzh + ")�����ڣ�"));
          else if (bs == 1) //����false
            return false;
        }
        sbjkzb = (Sbjkzb) queryresult.get(0);
        if (sbjkzb.getZwbs().substring(1, 2).equals("0")) {
          //��������ʶ�ڶ�λ��ȻΪ��0������ýɿ�����δ���
          return false;
        }
      }
      return true;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�ƾ֤��ʧ��!");
    }
  }

  /**
   * ��ָ���Ļ��ܵ��Ų��Ҷ����ɿ�ƾ֤��(����ɢ˰����˰֤���ܱ���)
   * @param hzdhList ���ܵ���List
   * @param resultType ���ص�����(0=ֻ�Ƿ��ؽɿ�ƾ֤�ŵ�list;1=���ؽɿ�ƾ֤�ź�ʵ�ɽ��)
   * @param conn ���ݿ�����
   * @return �ɿ�ƾ֤��List
   * @throws BaseException �����쳣
   */
//    private static ArrayList findJkDataInLswszsbhz(List hzdhList,
//                                                   int resultType,
//                                                   Connection conn)
//        throws BaseException
//    {
//        if(hzdhList == null || hzdhList.size() == 0)
//        {
//            throw ExceptionUtil.getBaseException(
//                new ApplicationException("��ָ��Ҫ���ҵĻ��ܵ��ţ�"));
//        }
//        ArrayList findResultList = new ArrayList();
//        //ORʵ��
//        ORManager orManager = null;
//        try
//        {
//            // ��� ORManager
//            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
//
//            //��ѯ��˰֤���ܱ�õ���Ӧ�Ľɿ�ƾ֤����Ϣ
//            //ѭ����һЩ����
//            String hzdh = null; //���ܵ���
//            Vector criteria = new Vector(); //��ѯ����
//            ORContext orCtx = null; //��ѯ��ORContext����
//            List queryresult = null; //��ѯ����
//            String jkpzh = null; //�ɿ�ƾ֤��
//            BigDecimal je = null; //ʵ�ɽ��
//            JksInfor jksInfor = null; //�ɿ�����Ϣ(����ʵ�ɽ��)
//            for(int i = 0; i < hzdhList.size(); i++)
//            {
//                hzdh = (String)hzdhList.get(i);
//                criteria.clear();
//                criteria.add("SBHZDH = '" + hzdh + "'");
//                orCtx = new ORContext(Lswszsbhz.class.getName(), criteria);
//                queryresult = (ArrayList)orManager.query(SESSION_ID, conn,
//                    orCtx);
//                //����ҵ��Ľɿ�ƾ֤��
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
//            throw ExceptionUtil.getBaseException(ex, "����ɢ˰���ܱ��в�ѯ��Ӧ�Ľɿ�ƾ֤��ʧ��!");
//        }
//    }

  /**
   * ɾ���ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ�����,
   * @param jkpzhList �ɿ�ƾ֤��List
   * @throws BaseException �����쳣
   */
  public static void deleteJKS(List jkpzhList) throws BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�������Ϸ������ύ��ȷ�Ľɿ�ƾ֤�ţ�"));
    }

    Connection conn = null;
    ORManager orManager = null;
    try {
      //�õ����Ӻ�orManager����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      //����ɾ���ɿ����ݵķ���
      delJKS(jkpzhList, conn);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "ɾ���ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ�����ʧ��!");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ɾ���ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ�����,
   * @param jkpzhList �ɿ�ƾ֤��List
   * @param conn ���ݿ�����
   * @throws BaseException �����쳣
   */
  public static void delJKS(List jkpzhList, Connection conn) throws
      BaseException {
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�������Ϸ������ύ��ȷ�Ľɿ�ƾ֤�ţ�"));
    }
    if (conn == null) {
      throw ExceptionUtil.getBaseException(new Exception(), "���ݿ����Ӵ���");
    }
    ORManager orManager = null;
    try {
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = null; //��ѯ����
      ORContext orCtx = null; //��ѯ��ORContext����
      //ɾ����ϸ����������
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("JKPZH IN ('" + jkpzhList.get(0) + "'");
      for (int i = 1; i < jkpzhList.size(); i++) {
        sqlBuffer.append(",'" + jkpzhList.get(i) + "'");
      }
      //���˱�ʶ�Ĺ�ϵ
      sqlBuffer.append(
          ") AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //ɾ������

      String sqlZb = wheresql; //��������
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";
      //ɾ����ϸ
      orManager.deleteObject(SESSION_ID, conn, sqlMx, new Sbjkmx());
      //ɾ������
      orManager.deleteObject(SESSION_ID, conn, sqlZb, new Sbjkzb());
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "ɾ���ɿ�ƾ֤�Ŷ�Ӧ�Ľɿ�����ʧ��!");
    }
  }

  /**
   * ��ȡ�걨�ɿ�����δ�������
   * @param jsjdm ���������
   * @param time ʱ��
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
      //key=1 ��ʾ��ҵ�û���key=2 ��ʾ��Ȼ���û�
      if (key.equals("1")) {
        swdjjbsj = (SWDJJBSJ) serviceProxy.getDjInfo(jsjdm).get("JBSJ");
        if (swdjjbsj != null) {
          swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�������֯��������
        }
      }
      else if (key.equals("2")) {
        Map tempMap = serviceProxy.getZrrDjInfo(jsjdm);
        zrrjbsj = (ZRRJBSJ) tempMap.get(DjOuterConstant.ZRRJBSJ);
        if (zrrjbsj != null)
          swjgzzjgdm = zrrjbsj.getSwjgzzjgdm(); //˰�������֯��������
      }
      else {
        throw ExceptionUtil.getBaseException(new Exception(key), "");
      }

      List result = new ArrayList();
      if (swjgzzjgdm == null || swjgzzjgdm.equals("")) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("�����ڸü��������(" + jsjdm + ")�ĵǼ���Ϣ��"));
      }
      else {
        String qxdm = swjgzzjgdm.substring(0, 2); //���ش���
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

        //�õ����Ӻ�orManager����
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ�걨�ɿ������δ�������ʧ��!");
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
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

//    /**
//     * ȡ���걨��ţ��������������أ�������18λ
//     * @param jsjdm ���������
//     * @return String �걨���
//     */
//    public static String getSbbh(String jsjdm)
//    {
//        ZhsbProcessor zhsbPro = new ZhsbProcessor();
//        return zhsbPro.getSbbh(jsjdm);
//    }

  /**
   * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ����������������λ�����8λ���к�
   * @param jsjdm ���������
   * @return sbbh �걨���
   * @throws DeclareException �쳣
   */
  public static String getSbbh(String jsjdm) throws Exception {
    //0.�������
    Connection conn = null;
    DBResource db = new DBResource();
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
      conn = db.getConnection();
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
      sbbh = jsjdm + DateUtilPro.getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "��ȡ�걨���ʧ��!");
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
   * ˰Ʊ���������,˰Ʊ��������ɹ���Ϊ����������������λ�����8λ���к�
   * @param jsjdm ���������
   * @return sbbh �걨���
   * @throws DeclareException �쳣
   */
  public static String getSphm(String jsjdm) throws Exception {
    //0.�������
    Connection conn = null;
    DBResource db = new DBResource();
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
      conn = db.getConnection();
      st = conn.createStatement();
      //2.����sql��䣬������ȡ��ǰ���
      sql = "SELECT LPAD(SBDB.SEQ_SB_SPHM.NEXTVAL,8,'0') FROM DUAL";
      rs = st.executeQuery(sql);
      if (rs.next()) {
        sequence = rs.getString(1);
      }
      else {
        throw new Exception("�޷���ȡ˰Ʊ�����������");
      }
      //3.��֯���
      sbbh = jsjdm + DateUtilPro.getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "��ȡ˰Ʊ����ʧ�ܣ�");
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
   * ��ȡ˰��˰Ŀ����
   * @param szsmdm ˰��˰Ŀ����
   * @param conn ���ݿ�����
   * @return String ˰��˰Ŀ����
   * @throws BaseException
   */
  private static String addSzsmMc(String szsmdm, Connection conn) throws
      BaseException {
    String szsmmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ˰��˰Ŀ����ʧ��!");
    }
  }

  //ȡ˰���������
  private static String getSwjgmc(String swjgzzjgdm, Connection conn) throws
      BaseException {
    String swjgmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ˰���������ʧ�ܣ�");
    }
  }

  /**
   * ����Ԥ���Ŀ�����ʱ�䣬��ѯ��Ӧ��Ԥ���Ŀ����
   * @param yskmdm ����
   * @param time ʱ��
   * @param conn ���ݿ�����
   * @return String ����
   * @throws BaseException
   */
  private static String addYskmMc(String yskmdm, Date time, Connection conn) throws
      BaseException {
    String yskmmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //������ǰʱ��
      int curYear = time.getYear() + 1900;
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯԤ���Ŀ����ʧ��!");
    }
  }

  /**
   * ���ݴ����ѯ˰����������
   * @param sklxdm ����
   * @param conn ���ݿ�����
   * @return String ����
   * @throws BaseException
   */
  private static String addSklxMc(String sklxdm, Connection conn) throws
      BaseException {
    String sklxmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ˰����������ʧ��!");
    }
  }

  /**
   * ���ݴ����ѯԤ�㼶������
   * @param ysjcdm ����
   * @param conn ���ݿ�����
   * @return String ����
   * @throws BaseException
   */
  private static String addYsjcMc(String ysjcdm, Connection conn) throws
      BaseException {
    String ysjcmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯԤ�㼶������ʧ��!");
    }
  }

  /**
   * ��ù�������
   * @param swjgzzjgdm ˰�������֯��������
   * @param conn ���ݿ�����
   * @return  String
   * @throws BaseException
   */
  private static String addGkMc(String swjgzzjgdm, Connection conn) throws
      BaseException {
    String gkmc = "";
    try {
      if (conn == null)
        throw new Exception("û�л�����ݿ����ӣ�");
      //�õ����Ӻ�orManager����
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ�տ��������ʧ�ܣ�");
    }
  }

  /**
   * ����˰��˰Ŀ���룬ȡ��Ӧ�ļ�˰����
   * @param djzclxdm �Ǽ�ע�����ʹ���
   * @return Djzclx Object
   * @throws BaseException
   */
  public static Djzclx getDjzclx(String djzclxdm) throws BaseException {
    Szsm data = new Szsm();
    Connection conn = null;
    ORManager orManager = null;
    try {
      //�õ����Ӻ�orManager����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = new Vector(); //��ѯ����
      criteria.add("DJZCLXDM = '" + djzclxdm + "'");
      //��ѯ��ORContext����
      ORContext orCtx = new ORContext(Djzclx.class.getName(), criteria);
      List queryresult = orManager.query(SESSION_ID, conn, orCtx);
      if (queryresult.size() != 0) {
        return (Djzclx) queryresult.get(0);
      }
      return null;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽ�ע���������ݴ���!");
    }
    finally {
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ����˰��˰Ŀ���룬ȡ��Ӧ�ļ�˰����
   * @param szsmdm ˰��˰Ŀ
   * @return Szsm Object
   * @throws BaseException
   */
  public static Szsm getSzsm(String szsmdm) throws BaseException {
    Szsm data = new Szsm();
    Connection conn = null;
    ORManager orManager = null;
    try {
      //�õ����Ӻ�orManager����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      Vector criteria = new Vector(); //��ѯ����
      criteria.add("SZSMDM = '" + szsmdm + "'");
      //��ѯ��ORContext����
      ORContext orCtx = new ORContext(Szsm.class.getName(), criteria);
      List queryresult = orManager.query(SESSION_ID, conn, orCtx);
      if (queryresult.size() == 1) {
        return (Szsm) queryresult.get(0);
      }
      else
        throw new Exception("û��˰��˰Ŀ����(" + szsmdm + ")��Ӧ�����ݣ�");
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ˰��˰Ŀ���ݴ���!");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**ת������ϵͳ�������걨����Ϊ�걨�ɿ�������걨�ɿ���ϸ����
   * @param sbsj ����ģ����걨����
   * @return DeclareInfor ���ɺõ��걨�ɿ�����
   * @throws BaseException �쳣����
   */
  public static DeclareInfor getJksBySbsjForCF(Sbsj sbsj) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    String swjgzzjgdm = sbsj.getZsjgdm(); //˰�����
    List sbjkmxList = new ArrayList();
    //��д�걨��ϸ��Ϣ
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm); //˰�����
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
    sbjkzb.setDjzclxdm(sbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(sbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(sbsj.getLsgxdm());
    sbjkzb.setNsrmc(sbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //���շ�ʽΪ����
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //�걨����
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //��ҳ����
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //Ĭ��Ϊ�����걨��

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //Ĭ��ΪһƱһ˰

    //�����ɵ����ݲ������ݱ��в�����
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    //ֻ��һ��Ʊ����Ϊ��ֻ��һ���ɿ���ϸ��¼
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    return data; //��װ������ݷ���
  }

  /**
   * ��ȡ˰����������
   * @param smdm ˰��˰Ŀ����
   * @param djzclx �Ǽ�ע������
   * @param rq  ��ǰ����
   * @return  Map
   * @throws BaseException
   */
  public static Map getZqzzrq(String smdm, String djzclx, String rq) throws
      BaseException {
    Connection conn = null;
    try {
      //��� ORManager
      ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
      //��ѯ����������
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
      zqrlRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //��ѯ����������
      if (zqrlRs.size() != 0) {
        Map retMap = new HashMap(3);
        retMap.put("SKSSKSRQ", ( (Zqrl) zqrlRs.get(0)).getZqssrqq()); // ��ʼ����
        retMap.put("SKSSJSRQ", ( (Zqrl) zqrlRs.get(0)).getZqssrqz()); // ��������
        retMap.put("ND", ( (Zqrl) zqrlRs.get(0)).getNd()); //���
        return retMap;
      }
      else
        return null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "��ȡ˰���������ڳ���");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ��������ֱ����˰֤�ż����ʹ��룬ȡ��Ӧ����˰����ϸ����
   * @param wszh ��˰֤��
   * @param nd ���
   * @param lxdm ���ʹ��룺1������ɢ˰��˰֤��2�������幤�̻���˰֤��
   * @return List
   * @throws BaseException
   */
  public static List getWszData(String wszh, String nd, int lxdm) throws
      BaseException {
    if (wszh == null || wszh.trim().equals("") || nd == null ||
        nd.trim().equals(""))
      throw ExceptionUtil.getBaseException(new ApplicationException("��������Ϊ�գ�"));
    Connection conn = null;
    try {
      ArrayList result = new ArrayList();
      StringBuffer sqlBuffer = new StringBuffer();
      if (lxdm == 1) {
        //��ɢ˰
        sqlBuffer.append(
            "SELECT DISTINCT A.JKPZH,A.HZRQ FROM SBDB.SB_JL_LSWSZSBHZ A,")
            .append("SBDB.SB_JL_LSSWSZZ B WHERE A.JSJDM>='0' AND B.JSJDM>='0'")
            .append(" AND B.WSZH = '").append(wszh)
            .append("' AND B.ND = '")
            .append(nd).append("' AND A.SBHZDH = B.SBHZDH ");
      }
      else if (lxdm == 2) {
        //���幤�̻�
        sqlBuffer.append(
            "SELECT DISTINCT C.JKPZH,C.HZRQ FROM SBDB.SB_JL_GTGSHWSZSBHZ C,")
            .append(
            "SBDB.SB_JL_GTGSHWSZZ D WHERE C.JSJDM>='0' AND D.JSJDM>='0'")
            .append(" AND D.WSZH = '").append(wszh)
            .append("' AND D.ND = '")
            .append(nd).append("' AND C.SBHZDH = D.SBHZDH");
      }
      else {
        throw ExceptionUtil.getBaseException(new ApplicationException("δ֪�������ͣ�"));
      }
      String sql = sqlBuffer.toString();
      sqlBuffer.setLength(0);

      ArrayList jkpzhList = new ArrayList();
      Timestamp hzrq = null;
      // ������ݿ�����
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
      //�ж��Ƿ����
      boolean isRk = isJkpzhInStore(jkpzhList, conn, 1);
      if (isRk) {
        //��� ORManager
        ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
        Vector criteria = new Vector();
        String sqlWhere = "WSZH = '" + wszh + "' AND ND = '" + nd + "'";
        criteria.add(sqlWhere);
        ORContext orCtx = null;
        if (lxdm == 1) //��ɢ˰
          orCtx = new ORContext(Lsswszmx.class.getName(), criteria);
        else if (lxdm == 2) //���幤�̻�
          orCtx = new ORContext(Gtgshwszmx.class.getName(), criteria);
        else
          throw ExceptionUtil.getBaseException(new ApplicationException(
              "δ֪�������ͣ�"));
        ArrayList tempAr = new ArrayList();
        tempAr = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //��ѯ��ϸ����

        if (lxdm == 1) { //��ɢ˰
          for (int i = 0; i < tempAr.size(); i++) {
            Lsswszmx lx = new Lsswszmx();
            lx = (Lsswszmx) tempAr.get(i);
            lx.setSzmc(addSzsmMc(lx.getSzdm(), conn)); //˰������
            lx.setSzsmmc(addSzsmMc(lx.getSzsmdm(), conn)); //˰Ŀ����
            lx.setYskmmc(addYskmMc(lx.getYskmdm(), lx.getLrrq(), conn)); //Ԥ���Ŀ����
            LsswszInfor lsswszInfo = new LsswszInfor();
            lsswszInfo.setData(lx); //��װΪǰ��Ӧ��ֵ����
            lsswszInfo.setHzrq(hzrq); //��������
            result.add(lsswszInfo);
          }
        }
        else if (lxdm == 2) { //���幤�̻�
          for (int j = 0; j < tempAr.size(); j++) {
            Gtgshwszmx gx = new Gtgshwszmx();
            gx = (Gtgshwszmx) tempAr.get(j);
            gx.setSzmc(addSzsmMc(gx.getSzdm(), conn));
            gx.setSzsmmc(addSzsmMc(gx.getSzsmdm(), conn));
            gx.setYskmmc(addYskmMc(gx.getYskmdm(), gx.getLrrq(), conn));
            GtgshwszInfo gtInfo = new GtgshwszInfo();
            gtInfo.setData(gx);
            gtInfo.setHzrq(hzrq); //��װΪǰ��Ӧ��ֵ����
            result.add(gtInfo);
          }
        }
        else
          throw ExceptionUtil.getBaseException(new ApplicationException(
              "δ֪�������ͣ�"));
      }
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, e.getMessage());
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**add by Daniel
   * ת������ϵͳ������һ���걨����Ϊ�걨�ɿ�������걨�ɿ���ϸ����
   * sbsjList��ȡ��һ�����ɽɿ��������ݣ�������֤Ҳֻ�ǵ�һ��
   * @param sbsj ����ģ����걨����
   * @return DeclareInfor ���ɺõ��걨�ɿ�����
   * @throws BaseException �쳣����
   */
  public static List generateBankSBJKS(Map sbsjMap) throws BaseException {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);

    if (sbsjMap == null || sbsjMap.size() < 6) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�ṩ�����ݲ��������ɽɿ��飡"));
    }

    String jsjdm = (String) sbsjMap.get(SBSJZB_JSJDM);
    if (jsjdm == null || jsjdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("��������벻��Ϊ�գ�"));
    }
    String sklxdm = (String) sbsjMap.get(SBSJZB_SKLXDM);
    if (sklxdm == null || sklxdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("˰�����ʹ��벻��Ϊ�գ�"));
    }
    String sjly = (String) sbsjMap.get(SBSJZB_SJLY);
    if (sjly == null || sjly.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("������Դ����Ϊ�գ�"));
    }
    String sbfsdm = (String) sbsjMap.get(SBSJZB_SBFSDM);
    if (sbfsdm == null || sbfsdm.length() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�걨��ʽ���벻��Ϊ�գ�"));
    }
    String bz = (String) sbsjMap.get(SBSJZB_BEIZHU);
    String yhdm = (String) sbsjMap.get(SBSJZB_YHDM);
    String yhmc = (String) sbsjMap.get(SBSJZB_YHMC);
    String zh = (String) sbsjMap.get(SBSJZB_ZH);
    String lrr = (String) sbsjMap.get(SBSJZB_LRR);

    BigDecimal sjjehj = (BigDecimal) sbsjMap.get(SBSJZB_SJJE);
    if (sjjehj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("ʵ�ɽ��ϼƲ���Ϊ�գ�"));
    }
    List mxList = (List) sbsjMap.get(SBSJZB_SBJKMX);
    if (mxList == null || mxList.size() <= 0) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�걨�ɿ���ϸ������һ����"));
    }

    //��д�걨������Ϣ
    Map map = null;
    //���õǼǽӿ�
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(jsjdm);
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    if (swdjjbsj == null) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("û�еõ��ü��������ĵǼ����ݣ�"));
    }

    //�ж���˰���Ƿ���걨,˰�������֯�����������λΪ00�Ĳ��ܿ��ɿ���
    if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
                                      swdjjbsj) || !FriendHelper.
        checkNsrztForJks(CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
      // ��������
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����˰��״̬��Ϊ�������������ɽɿ��飡"));
    }

    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //˰�����
    List sbjkmxList = new ArrayList();
    //��д�걨��ϸ��Ϣ
    for (int i = 0; i < mxList.size(); i++) {
      Map mxMap = (Map) mxList.get(i);
      String szsmdm = (String) mxMap.get(SBSJMX_SZSMDM);
      if (szsmdm == null || szsmdm.length() <= 0) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("˰��˰Ŀ���벻��Ϊ�գ�"));
      }
      BigDecimal sjje = (BigDecimal) mxMap.get(SBSJMX_SJJE);
      BigDecimal jsje = (BigDecimal) mxMap.get(SBSJMX_JSJE);
      BigDecimal kssl = (BigDecimal) mxMap.get(SBSJMX_KSSL);
      BigDecimal sl = (BigDecimal) mxMap.get(SBSJMX_SL);
      if (sjje == null) {
        throw ExceptionUtil.getBaseException(
            new ApplicationException("����Ϊ�գ�"));
      }
      Szsm szsm = getSzsm(szsmdm);
      Sbjkmx sbjkmx = new Sbjkmx();
      sbjkmx.setSzsmdm(szsmdm);
      sbjkmx.setZqlxdm(szsm.getZqlxdm()); // �������ʹ���
      sbjkmx.setJsjdm(jsjdm);
      sbjkmx.setSjse(sjje);
      sbjkmx.setRkje(sjje);
      sbjkmx.setKssl(kssl);
      sbjkmx.setSl(sl);
      sbjkmx.setJsje(jsje);
      sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
      sbjkmx.setNd(nd);
      sbjkmx.setCjrq(now); //��������
      sbjkmx.setLrrq(now); //¼������
      sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���

      //˰��������ʼ����
      if (mxMap.get(SBSJMX_SKSSKSRQ) != null) {
        sbjkmx.setSkssksrq( (Timestamp) mxMap.get(SBSJMX_SKSSKSRQ));
      }

      //˰��������������
      if (mxMap.get(SBSJMX_SKSSJSRQ) != null) {
        sbjkmx.setSkssjsrq( (Timestamp) mxMap.get(SBSJMX_SKSSJSRQ));
      }

      sbjkmxList.add(sbjkmx);
    }

    //�걨��������
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(jsjdm);
    //��˰��Ǽǵõ�������
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(sbfsdm); //���շ�ʽΪ����
    if (lrr != null) {
      sbjkzb.setLrr(lrr);
    }
    else {
      sbjkzb.setLrr(jsjdm);
    }
    sbjkzb.setRkje(sjjehj);
    sbjkzb.setSbrq(TinyTools.second2Day(now)); //�걨����
    sbjkzb.setZyrq(TinyTools.second2Day(now)); //��ҳ���ڣ���ʼΪ�걨����
    sbjkzb.setSjje(sjjehj);
    sbjkzb.setSklxdm(sklxdm);
    sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
    sbjkzb.setNd(nd);
    sbjkzb.setBz(bz);
    sbjkzb.setSjly(sjly); //������Դ
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //���ش���
    sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); //Ĭ��Ϊ�����걨��
    sbjkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // ��ϵ�绰

    //�޽�����
    if (sbsjMap.get(SBSJZB_XJRQ) != null) {
      sbjkzb.setXjrq( (Timestamp) sbsjMap.get(SBSJZB_XJRQ));
    }

    List tempList = (List) map.get("YHZH");

    if (yhdm != null && yhmc != null && zh != null) {
      sbjkzb.setYhdm(yhdm); //���д���
      sbjkzb.setYhmc(yhmc); //��������
      sbjkzb.setZh(zh); //�����˺�
    }
    else {
      if (tempList != null && tempList.size() != 0) {
        YHZH yhzh = (YHZH) tempList.get(0);
        sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
        sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
        sbjkzb.setZh(yhzh.getZh()); //�����˺�
        for (int i = 0; i < tempList.size(); i++) {
          yhzh = (YHZH) tempList.get(i);
          if (yhzh.getJbzhbs().equals("1")) { //����������Ϣ
            sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
            sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
            sbjkzb.setZh(yhzh.getZh()); //�����˺�
            break;
          }
        }
      }
    }

    //�������ݶ���
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //��������
    declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //Ĭ��ΪһƱһ˰

    //�����ɵ����ݲ������ݱ��в�����
    ZhsbProcessor zhsbPro = new ZhsbProcessor();
    String sbbh;
    try {
      sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);

    return dataList; //��װ������ݷ���
  }

  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯�� �ڲ�����Ϊcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJks(String sbbh) throws Exception {
    //0.�������
    List tmpList = null;
    DBResource db = null;
    Statement st = null;
    Connection conn = null;
    ResultSet rs = null;
    Map jksmx = null;
    String jsjdm = null;
    StringBuffer sb;
    String sql = null;
    //1.�������У��
    if (sbbh == null || sbbh.length() != 18) {
      throw new Exception("�걨��ŷǷ�:" + sbbh);
    }
    //2.��ʼ��
    tmpList = new ArrayList();
    try {
      jsjdm = sbbh.substring(0, 8);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.ҵ�����
    try {
      ///3.0.��ȡ��Դ
      conn = db.getConnection();
      st = conn.createStatement();
      ///3.1.���ɸ���sbbh��jsjdm��Ϊ��ѯ������SQL���
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
      ///3.2.���в�ѯ��ȡ�����
      InterFaceProcessor.log(sql);
      rs = st.executeQuery(sql);
      ///3.3.�������������ȡ��������
      tmpList = new ArrayList();
      while (rs.next()) {
        jksmx = new HashMap();
        for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
          //InterFaceProcessor.log(ApplicationConstant.JKS_YPYS_MX_KEYS[i]);
          jksmx.put(ApplicationConstant.JKS_YPYS_MX_KEYS[i],
                    rs.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
        }
        InterFaceProcessor.log("��һ�ֲ�ѯ��һƱһ˰��ϸ==" + jksmx);
        tmpList.add(jksmx);
      }
      rs.close();
      ///3.4.�����ӷ������з�Ʊ
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
    //99.����ֵ
    return tmpList;
  }

  /**
   * ����˰Ʊ�����ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯�� �ڲ�����Ϊcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJksBySphm(String sphm) throws Exception {
    //0.�������
    List tmpList = null;
    DBResource db = null;
    Statement st = null;
    Connection conn = null;
    ResultSet rs = null;
    Map jksmx = null;
    String jsjdm = null;
    StringBuffer sb;
    String sql = null;
    //1.�������У��
    if (sphm == null || sphm.length() != 18) {
      throw new Exception("˰Ʊ����Ƿ�:" + sphm);
    }
    //2.��ʼ��
    tmpList = new ArrayList();
    try {
      jsjdm = sphm.substring(0, 8);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    //3.ҵ�����
    try {
      ///3.0.��ȡ��Դ
      conn = db.getConnection();
      st = conn.createStatement();
      ///3.1.���ɸ���sbbh��jsjdm��Ϊ��ѯ������SQL���
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
      ///3.2.���в�ѯ��ȡ�����
      InterFaceProcessor.log(sql);
      rs = st.executeQuery(sql);
      ///3.3.�������������ȡ��������
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
      ///3.4.�����ӷ������з�Ʊ
      tmpList = InterFaceProcessor.getYpdsJks(tmpList);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      db.destroyConnection(conn);
    }
    //99.����ֵ
    return tmpList;
  }

  //* �˷�������Ϊ������[public static List getYpdsJks(String obj) throws Exception]�ṩʹ��
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

  //* �˷�������Ϊ������[public static List getYpdsJks(String obj) throws Exception]�ṩʹ��
   protected static String getYpysJksQueryOrderByPostfix() {
     StringBuffer sb = new StringBuffer();
     sb.append(" ORDER BY a.sklxdm,a.zsswjgzzjgdm,a.ysjcdm,a.yskmdm,a.skssksrq,a.skssjsrq,a.xjrq"); //���н�������
     return sb.toString();
   }

  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * �ɿ��Ʊԭ��
   * һ��һ��Ʊ��ֻ��һ��˰������
   * ����һ��˰Ʊ��ֻ��һ�����ջ���
   * ����ͬһ�Žɿ���������õ�������ӡ
   * �ġ����ղ�ͬԤ�㼶�η��д�
   * �塢��ͬ��Ԥ���Ŀ���д�
   * ������ͬ��˰�������ڷ��д�
   * �ߡ���ͬ�޽����ڷ��д�
   * �ˡ�����˳��Ԥ�㼶�δ��� Ԥ���Ŀ���� ˰�������� �޽����� ��С����
   * @param JksList һƱһ˰�ɿ��鼯��,�ü��ϱ��뱣֤����ͬһ���걨�ұ��뱣֤���ݼ��Ѿ����к�
   * @return һƱ��˰�ɿ��鼯�� �ڲ�����Ϊcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   */
  public static List getYpdsJks(List JksList) throws Exception {
    //0.�������
    List tmpList = null;
    List returnList = null;
    Map tmpMap = null;
    String sbbh = null;
    String tmpStr = null;
    //1.�������У��
    if (JksList == null || JksList.size() == 0) {
      throw new Exception("һƱһ˰�ɿ��鼯�ϷǷ���Ϊ��");
    }
    for (int i = 0; i < JksList.size(); i++) {
      tmpStr = (String) ( ( (Map) JksList.get(i)).get("sbbh"));
      if (i == 0) {
        sbbh = tmpStr;
      }
      if (!sbbh.equals(tmpStr)) {
        throw new Exception("���ݼ��Ƿ����ñ�һƱһ˰�ɿ��鼯�ϴ��ڲ�����ͬһ���걨������");
      }
    }
    //2.��ʼ��
    tmpList = new ArrayList();
    returnList = new ArrayList();
    //3.ҵ�����
    String sklxdm = null;
    Map map_sklxdm = new HashMap(); //���Map���������з�������ݵ�����
//    String [] keys={"sklxdm","zsswjgzzjgdm","ysjcdm","yskmdm","skssksrq","skssjsrq","xjrq"};
    String[] keys = {
        "sklxdm", "zsswjgzzjgdm"};
    List[] sorts = {
        new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(),
        new ArrayList(), new ArrayList(), new ArrayList()};
    ///3.0.���ݷ�Ʊԭ������һƱһ˰�ɿ��鼯���ڵĶ��󵽲�ͬ����������
    int count = JksList.size();
    Map map = null;
    for (int i = 0; i < count; i++) {
      ////3.0.0.��ȡһƱһ˰�ɿ�����ϸ����Map
      map = (HashMap) JksList.get(i);
      ////3.0.1.��ȡһƱһ˰�ɿ����Ʊ����
      sklxdm = (String) map.get("sklxdm");
      ////3.0.2.��һƱһ˰�ɿ����������벻ͬ�����ݼ�
      InterFaceProcessor ifp = new InterFaceProcessor();
      ifp.ypdsNBranchTreeIterator(map_sklxdm, sklxdm, keys, 0, map, sorts); //�ݹ�������������ݷ��࣬���ĳһ��������������������д���
      ////3.0.99.�ָ�Ĭ��ֵ
      sklxdm = null;
      map = null;
    }
    ///3.1.���ݽɿ����Ʊԭ�����ɽɿ�����󲢼������ݼ�
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
    for (int i = 0; i < sorts[0].size(); i++) { //����˰�����ͽ��з�Ʊ
      InterFaceProcessor.log("����˰�����ͽ��з�Ʊ[" + i + "]");
      obj0 = map_sklxdm.get(sorts[0].get(i)); //˰������Ϊ��һ��϶�����Ϊ��
      for (int j = 0; j < sorts[1].size(); j++) { //�������ջ��ؽ��з�Ʊ
        InterFaceProcessor.log("�������ջ��ؽ��з�Ʊ[" + j + "]");
        jks = null;
        ////3.1.0.��ȡ��ǰ���ݷ�������
        obj1 = ( (Map) obj0).get(sorts[1].get(j));
        if (obj1 == null) { //�����Ӧ˰�����ͼ����ջ��ز������������һ��ѭ��
          continue;
        }
        ////3.1.1.���ڿ�ʼ����ϵͳԤ�����������ȡ������һƱһ˰����
        tmpList310 = (List) obj1; //���������������Ҫ����ͬһ��һƱ��˰�ɿ��������
        InterFaceProcessor ifp = new InterFaceProcessor();
        List copiedYpysJksmx = ifp.copyYpysJksMxMap(tmpList310); //����һ��һƱһ˰�ɿ�����ϸ
        Map ypysJksmxSplitByLine = ifp.splitYpysJksMxMapByLine(copiedYpysJksmx); //�������з����һƱһ˰�ɿ�����ϸ
        Map tmpMap31010 = new HashMap();
        List tmpList31010 = null;
        for (int k = 0; k < tmpList310.size(); k++) {
          /////3.1.0.0.ȡ����һ�����ݲ������һ���ɿ�����󲢸��貿��ֵ
          tmpMap310 = (Map) tmpList310.get(k);
          InterFaceProcessor.log("����ϵͳԤ�����������ȡ������һƱһ˰����[" +
                                 tmpMap310.get("sjse") + "]");
          /////3.1.0.1.�ϲ�������Ҫ�ϲ�������
          if (k < (tmpList310.size() - 1) && tmpList310.size() > 1) { //����ǰԪ�ز������һ��Ԫ������������������1
            //���бȽϺͺϲ�,�����Ϸ���ԭ��������ں͵�ǰ���ݺϲ����Ƴ��μ����ݲ��ƶ�ָ����ǰ
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
              //�ϲ�˰��
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
          else if (k == (tmpList310.size() - 1) && tmpList310.size() > 1) { //����ǰԪ�������һ��Ԫ������������������1
            //do nothing
          }
          else if (tmpList310.size() == 1) { //����������������1
            //do nothing
          }
        }
        ////3.1.2.�Ӽ���Ʊ
        tmp_ypds_dataList = new ArrayList();
        tmp_ypys_dataList = new ArrayList();
        String hjsjje = "0.00";
        log("�Ӽ���Ʊʱ����" + tmpList310.size() + "��Ԫ��");
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
            //�ӷ��к��һƱһ˰�ɿ�����ϸ��ȡ��������һƱ��˰�ɿ������
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
            //�ӷ��к��һƱһ˰�ɿ�����ϸ��ȡ��������һƱ��˰�ɿ������
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
    ///3.99.������

    //99.����ֵ
    return returnList;
  }

  
  public static List getYpdsJksForSkh(List JksList) throws Exception {
    //0.�������
    List tmpList = null;
    List returnList = null;
    Map tmpMap = null;
    String sbbh = null;
    String tmpStr = null;
    //1.�������У��
    if (JksList == null || JksList.size() == 0) {
      throw new Exception("һƱһ˰�ɿ��鼯�ϷǷ���Ϊ��");
    }
    for (int i = 0; i < JksList.size(); i++) {
      tmpStr = (String) ( ( (Map) JksList.get(i)).get("sbbh"));
      if (i == 0) {
        sbbh = tmpStr;
      }
      if (!sbbh.equals(tmpStr)) {
        throw new Exception("���ݼ��Ƿ����ñ�һƱһ˰�ɿ��鼯�ϴ��ڲ�����ͬһ���걨������");
      }
    }
    //2.��ʼ��
    tmpList = new ArrayList();
    returnList = new ArrayList();
    //3.ҵ�����
    String sklxdm = null;
    Map map_sklxdm = new HashMap(); //���Map���������з�������ݵ�����
//    String [] keys={"sklxdm","zsswjgzzjgdm","ysjcdm","yskmdm","skssksrq","skssjsrq","xjrq"};
    String[] keys = {
        "sklxdm", "zsswjgzzjgdm"};
    List[] sorts = {
        new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(),
        new ArrayList(), new ArrayList(), new ArrayList()};
    ///3.0.���ݷ�Ʊԭ������һƱһ˰�ɿ��鼯���ڵĶ��󵽲�ͬ����������
    int count = JksList.size();
    Map map = null;
    for (int i = 0; i < count; i++) {
      ////3.0.0.��ȡһƱһ˰�ɿ�����ϸ����Map
      map = (HashMap) JksList.get(i);
      ////3.0.1.��ȡһƱһ˰�ɿ����Ʊ����
      sklxdm = (String) map.get("sklxdm");
      ////3.0.2.��һƱһ˰�ɿ����������벻ͬ�����ݼ�
      InterFaceProcessor ifp = new InterFaceProcessor();
      ifp.ypdsNBranchTreeIterator(map_sklxdm, sklxdm, keys, 0, map, sorts); //�ݹ�������������ݷ��࣬���ĳһ��������������������д���
      ////3.0.99.�ָ�Ĭ��ֵ
      sklxdm = null;
      map = null;
    }
    ///3.1.���ݽɿ����Ʊԭ�����ɽɿ�����󲢼������ݼ�
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
    for (int i = 0; i < sorts[0].size(); i++) { //����˰�����ͽ��з�Ʊ
      InterFaceProcessor.log("����˰�����ͽ��з�Ʊ[" + i + "]");
      obj0 = map_sklxdm.get(sorts[0].get(i)); //˰������Ϊ��һ��϶�����Ϊ��
      for (int j = 0; j < sorts[1].size(); j++) { //�������ջ��ؽ��з�Ʊ
        InterFaceProcessor.log("�������ջ��ؽ��з�Ʊ[" + j + "]");
        jks = null;
        ////3.1.0.��ȡ��ǰ���ݷ�������
        obj1 = ( (Map) obj0).get(sorts[1].get(j));
        if (obj1 == null) { //�����Ӧ˰�����ͼ����ջ��ز������������һ��ѭ��
          continue;
        }
        ////3.1.1.���ڿ�ʼ����ϵͳԤ�����������ȡ������һƱһ˰����
        tmpList310 = (List) obj1; //���������������Ҫ����ͬһ��һƱ��˰�ɿ��������
        InterFaceProcessor ifp = new InterFaceProcessor();
        List copiedYpysJksmx = ifp.copyYpysJksMxMap(tmpList310); //����һ��һƱһ˰�ɿ�����ϸ
        Map ypysJksmxSplitByLine = ifp.splitYpysJksMxMapByLineForSkh(copiedYpysJksmx); //�������з����һƱһ˰�ɿ�����ϸ
        Map tmpMap31010 = new HashMap();
        List tmpList31010 = null;
        
        for (int k = 0; k < tmpList310.size(); k++) {
          /////3.1.0.0.ȡ����һ�����ݲ������һ���ɿ�����󲢸��貿��ֵ
          
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

          InterFaceProcessor.log("����ϵͳԤ�����������ȡ������һƱһ˰����[" +
                                 tmpMap310.get("sjse") + "]");
          /////3.1.0.1.�ϲ�������Ҫ�ϲ�������
          if (k < (tmpList310.size() - 1) && tmpList310.size() > 1) { //����ǰԪ�ز������һ��Ԫ������������������1
            //���бȽϺͺϲ�,�����Ϸ���ԭ��������ں͵�ǰ���ݺϲ����Ƴ��μ����ݲ��ƶ�ָ����ǰ
            
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
                
                //�ϲ�˰��
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
          else if (k == (tmpList310.size() - 1) && tmpList310.size() > 1) { //����ǰԪ�������һ��Ԫ������������������1
            //do nothing
          }
          else if (tmpList310.size() == 1) { //����������������1
            //do nothing
          }
        }
        ////3.1.2.�Ӽ���Ʊ
        tmp_ypds_dataList = new ArrayList();
        tmp_ypys_dataList = new ArrayList();
        String hjsjje = "0.00";
        log("�Ӽ���Ʊʱ����" + tmpList310.size() + "��Ԫ��");
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
            //�ӷ��к��һƱһ˰�ɿ�����ϸ��ȡ��������һƱ��˰�ɿ������
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
            //�ӷ��к��һƱһ˰�ɿ�����ϸ��ȡ��������һƱ��˰�ɿ������
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
    ///3.99.������

    //99.����ֵ
    return returnList;
  }
  
  
  /**
       * �˷�������Ϊ������[public static List getYpdsJks(List JksList) throws Exception]�ṩʹ��
   * @param list һƱһ˰�ɿ�����ϸList
   * @param jkpzh �ɿ�ƾ֤��
   * @param szsmdm ˰��˰Ŀ����
   * @return һƱһ˰�ɿ�����ϸ
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
       * �˷�������Ϊ������[public static List getYpdsJks(List JksList) throws Exception]�ṩʹ��
   * @param list һƱһ˰�ɿ�����ϸList
   * @return ���շ��й���ķ���Map����
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
       * �˷�������Ϊ������[public static List getYpdsJks(List JksList) throws Exception]�ṩʹ��
   * @param list һƱһ˰�ɿ�����ϸList
   * @return һƱһ˰�ɿ�����ϸList
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
       * �˷�������Ϊ������[public static List getYpdsJks(List JksList) throws Exception]�ṩʹ��
   * @param map ���ݷ�������
   * @param str ��ǰ���������е�keyֵ
   * @param keys key������ֵ
   * @param i �ݹ����
   * @param dataMap ��ǰһƱһ˰�ɿ�������ϸ����
   * @param sorts ������������
   */
  private void ypdsNBranchTreeIterator(Map map, String str, String[] keys,
                                       int i, Map dataMap, List[] sorts) {
    //this.log("�����������"+i);
    Object obj = map.get(str); //�Ӵ����֦�ڵ��ȡ�ӽڵ�
    List tmpList = null;
    Map tmpMap = null;
    if (i == keys.length - 1) { //�������һ��֦�ڵ㣬��ʼ����Ҷ�ڵ����ݲ�����
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
    else { //�ڷ����֦�ڵ㴦��֦�ڵ�����
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
	 * @desc ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
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
			// ������ݿ�����
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);

			yskmdm = tmpYskmdm;
			szsmdm = tmpSzsmdm;
			swjgzzjgdm = tmpSwjgzzjgdm;

			// ���ô洢�����洢���̵�һ������Ϊ��ȣ�����Ϊ��
			proc = conn.prepareCall("{ call jkdb.getYskmFpblMc(?,?,?,?,?,?) }");
			// �������
			proc.setString(1, "");
			proc.setString(2, yskmdm);
			proc.setString(3, szsmdm);
			proc.setString(4, swjgzzjgdm);

			// ���ڲ���
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			// ���ô洢����
			proc.execute();

			// ��ȡ���
			yskmFcblmc = proc.getString(5);
			yskmfpblmc_err = proc.getString(6);

			// �ж��Ƿ��Ѿ���ѯ��Ԥ�㼶������
			// if(yskmfpblmc_err!=null || !"".equals(yskmfpblmc_err) || !"null".equals(yskmfpblmc_err)){
			if (yskmfpblmc_err != null) {
				System.out.println("���»�ȡԤ�㼶�η���������ƴ���" + yskmfpblmc_err);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e, "���ô洢����jkdb.getYskmFpblMc()����ʧ��!");
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