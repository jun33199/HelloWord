package com.ttsoft.bjtax.shenbao.jiekou.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.shenbao.jiekou.access.Sb_jl_sbjkmxAccess;
import com.ttsoft.bjtax.shenbao.jiekou.access.Sb_jl_sbjkzbAccess;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Ysjc;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

/**
 * Ϊ��˰�����ṩ�����ɽɿ������
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class ShenbaoProcessor {
  private static final int SESSION_ID = 0;

  //�Ƿ񷵻ؽɿ�����Ĭ��Ϊfalse
  private boolean isReturnPaymentInfo = false;
  //��ӡ��ʶ
  private int printTag;
  /**
   * �걨���
   */
  private String sbbh;
  /**
   * Ʊ�ݸ�ʽ��һƱһ˰
   */
  private final static int PRINT_YPYS = 1;
  /**
   * ���걨
   */
  private final static String CLBJDM_YSB = "11";

  //���췽��
  public ShenbaoProcessor() {
  }

  /**
   * ���ɽɿ����ݲ��������ݿ�������
   * @param declareInfor �걨����
   * @param _sbbh �걨���
   * @param conn ���ݿ�����
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws Exception �����쳣
   */
  public Object createJkInfor(DeclareInfor declareInfor,
                              String _sbbh, Connection conn) throws Exception {
    this.sbbh = _sbbh;
//        isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
//        printTag = declareInfor.getPrintTag();
    Sbjkzb insbjkzb = declareInfor.getSbjkzb();
    List insbjkmxInfo = declareInfor.getSbjkmxInfo();
    try {
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      //2.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList =
          createSkjkzbInfor_onetax(insbjkzb, insbjkmxInfo, conn);

      //���ؽɿ�����
      return jkdataList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--�������ݴ���!");
    }
  }

  /**
   * ����걨��ϸList�е�Ԥ�㼶�κͿ�Ŀ��Ϣ
   * @param insbjkzb �ɿ�������Ϣ
   * @param insbjkmxInfo �ɿ���ϸ��Ϣ
   * @throws Exception �����쳣
   */
  private void fillSbjkmxInfo(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      Exception {
    Sbjkmx sbjkmxTmp = null;
    for (int i = 0; i < insbjkmxInfo.size(); i++) {
      sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
      try {
        fillSbjkmx(sbjkmxTmp, insbjkzb);
      }
      catch (Exception ex) {
        throw ex;
      }
    }
  }

  /**
   * �ڽɿ���ϸ���������Ԥ�㼶�κ�Ԥ���Ŀ��Ϣ
   * @param sbjkmx �����Ľɿ���ϸ
   * @param insbjkzb �걨�ɿ�����
   * @throws Exception �����쳣
   */
  private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception {
    //3.��д˰������ʱ��
    if (sbjkmx.getSkssjsrq() == null) {
      throw new Exception("˰������ʱ�ڵĽ�ֹ���ڲ���Ϊ�գ�");
    }
    if (sbjkmx.getYsjcdm() == null) { //���Ԥ�㼶����δ��ֵ����ִ�в���Ԥ�㼶��
      //1.����Ԥ�㼶�β����
      Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(),
                          sbjkmx.getSzsmdm(), sbjkmx.getSkssjsrq()); //����������Ϊ˰��������ֹ����
      sbjkmx.setYsjcdm(ysjc.getYsjcdm()); //��ֵ
    }
    //����Ԥ���Ŀ���벢���
    //���üƻ�ӿڣ���ȡԤ���Ŀ
    Yskm yskm = null;
    try {
      yskm = JKAdapter.getInstance().getYskm(sbjkmx.getSzsmdm(),
                                             insbjkzb.getDjzclxdm(),
                                             insbjkzb.getGjbzhydm(),
                                             sbjkmx.getYsjcdm());
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--��ѯԤ���Ŀʱ��������");
    }
    if (yskm == null) {
      throw new Exception("��ȡԤ���Ŀʧ��!");
    }

    if (sbjkmx.getYskmdm() == null) { //���Ԥ���Ŀ������δ��ֵ���򽫲�ѯ�����ֵ����ϸ����Ķ�Ӧ����
      sbjkmx.setYskmdm(yskm.getYskmdm()); //��ֵ
    }

    //�����
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //�����걨���
    if (sbjkmx.getYsjcdm().equals("21")) { //����ǵط���
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //�����оּ��ֳɽ��
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //�������ؼ��ֳɽ��
    }
  }

  /**
   * ���걨����,�걨��ϸ���ݲ������ݿ�
   * @param sbjkzbList �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
   * @param sbjkmxInfoList �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
   * ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
   * @param conn ���ݿ�����
   * @throws Exception �쳣��Ϣ
   */
  private void insertSbjkData(List sbjkzbList,
                              List sbjkmxInfoList, Connection conn) throws
      Exception {
    try {
      //������������
      for (int i = 0; i < sbjkzbList.size(); i++) {
        Sbjkzb zbData = (Sbjkzb) sbjkzbList.get(i);
        Sb_jl_sbjkzbAccess.insertRecord(conn, zbData);
      }
      //������ϸ����
      for (int j = 0; j < sbjkmxInfoList.size(); j++) {
        Sbjkmx mxData = (Sbjkmx) sbjkmxInfoList.get(j);
        Sb_jl_sbjkmxAccess.insertRecord(conn, mxData);
      }
    }
    catch (Exception ex) {
      throw ex;
    }
  }

  //�԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱһ˰���
  //�������ɵĽɿ�����Ϣ
  private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb,
                                        List sbjkmxInfo, Connection conn) throws
      Exception {
    String sequence = null; //�ɿ�ƾ֤���е����к�
    //�õ���ǰ��������걨�ɿ������е������ˮ��.
    try {
      //�õ�¼�����ڵ����£�6λ��
      SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyyyMM");
      String yyyyMM = simpleDataFormats.format(insbjkzb.getLrrq());
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      //NumberFormat nmbFormat = new DecimalFormat("000");
      //ѭ���е���ʱ����
      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
      List jkInforList = new ArrayList(); //���صĽɿ�������List
      DeclareInfor jkInfor = null; //һ���ɿ�����
      //List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list

      //Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      //List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
      //BigDecimal sjjehe; //ʵ�ɽ��

      //ȡ�õ�ǰ���������ʹ�õ�������
//            sequence = nmbFormat.format(
//                Integer.parseInt(getXh(insbjkzb.getJsjdm(), yyyyMM, conn)) + 1);
      sequence = getXh(insbjkzb.getJsjdm(), yyyyMM, conn);
      //�������ASCII��
      String sequenceAscii = TinyTools.stringToASCII(TinyTools.
          asciiToString(Integer.parseInt(sequence) + 1));
      //��ʽ��Ϊ"000"��ʽ
      sequence = TinyTools.xhFormat(
          TinyTools.asciiToString(Integer.parseInt(sequenceAscii)));

      jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
      sbjkzbtmp = createSbjkzb(sbjkmxInfo, insbjkzb, jkpzh, CLBJDM_YSB, conn); //���걨
      sbjkzbList.add(sbjkzbtmp);
      jkInfor = new DeclareInfor(sbjkzbtmp, sbjkmxInfo);
      jkInforList.add(jkInfor);

      //�����ɵ����ݲ������ݿ���
      insertSbjkData(sbjkzbList, sbjkmxInfo, conn);
      return jkInforList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "���ɽɿ�����ʧ�ܣ�");
    }

  }

  //�õ���ϸ���ݴ���һ��sbjkzb����
  private Sbjkzb createSbjkzb(List sbjkmxList, Sbjkzb sbjkzb, String jkpzh,
                              String clbj, Connection conn) throws Exception {
    Sbjkzb cloneSbjkzb = sbjkzb.getCopy();
    cloneSbjkzb.setJkpzh(jkpzh);
    Sbjkmx sbjkmxtmp = null;
    BigDecimal sjjehe = new BigDecimal(0.00);
    for (int j = 0; j < sbjkmxList.size(); j++) {
      sbjkmxtmp = (Sbjkmx) sbjkmxList.get(j);
      sbjkmxtmp.setJkpzh(jkpzh);
      if (sbjkmxtmp.getSjse() != null) {
        sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); //ͳ��ʵ�ɽ��ĺϼ�
      }
    }
    //����ʵ�ɽ��������ֶ�����
    cloneSbjkzb.setSjje(sjjehe);
    cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
    cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
    cloneSbjkzb.setSzdm(sbjkmxtmp.getSzsmdm().substring(0, 2)); //��ϸ������˰���ֶΣ�ȡ˰Ŀǰ��λ
    cloneSbjkzb.setRkje(sjjehe);
    cloneSbjkzb.setSbbh(sbbh); //�����걨���
    cloneSbjkzb.setZwbs("00000000000000000000");
    cloneSbjkzb.setClbjdm(clbj);
    //����걨�ɿ�����˰������ʱ��
    cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
    cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
    //�޽�����
    String smdm = sbjkmxtmp.getSzsmdm(); //˰��˰Ŀ����
    String djzclx = sbjkzb.getDjzclxdm(); //�Ǽ�ע�����ʹ���
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
    String rq = simpleDataFormat.format(new Date()); //��ǰ������:YYYYMMdd
    if (cloneSbjkzb.getXjrq() == null) //����޽�����Ϊ��
      cloneSbjkzb.setXjrq(getZqzzrq(smdm, djzclx, rq, conn)); //�޽�����
      //�õ����������ĵǼ���Ϣ����д����
    String jsjdm = sbjkmxtmp.getJsjdm(); //���������
    //��ȡ���⽻����
    String gkjhh = getSkgk(jsjdm, cloneSbjkzb.getSwjgzzjgdm(), conn);
    cloneSbjkzb.setGkzzjgdm(gkjhh); //������֯��������

    return cloneSbjkzb;
  }

  /**
   * ����˰��˰Ŀ����͵�ǰ���£���ȡ�����������ж�ѽ��������ֹ����
   * ������ڶ������������ļ�¼��ȡ��һ����
   * ���û�����������ļ�¼���򷵻ص�ǰ�µ�10��Ϊ�޽�����
   * @param smdm ˰��˰Ŀ����
   * @param djzclx �Ǽ�ע�����ʹ���
   * @param rq  ����
   * @return  Timestamp ������ֹ����
   * @param conn ���ݿ�����
   * @throws Exception
   */
  private Timestamp getZqzzrq(String smdm, String djzclx, String rq,
                              Connection conn) throws Exception {
    try {
      //����Ĭ�����ڽ�ֹ����
      long currentTime = System.currentTimeMillis();
      Timestamp time = new Timestamp(currentTime);
      time.setDate(15);

      String sqlWhere = "SELECT ZQZZRQ FROM SBDB.SB_JL_ZQRL WHERE SZSMDM = '"
          + smdm + "' ZQQSRQ <= to_date('"
          + rq + "','yyyymmdd') and ZQZZRQ >= to_date('" + rq
          + "','yyyymmdd') and djzclxdm = '" + djzclx
          + "' ORDER BY ZQQSRQ";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sqlWhere);
      if (rs.next())
        return rs.getTimestamp("zqzzrq");
      else
        return time; //����Ĭ��ֵ����ǰ��10��
    }
    catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * ��ѯ����������ѽ���տ����
   * @param jsjdm ���������
   * @param swjgdm ˰�������֯��������
   * @param conn ���ݿ�����
   * @return String ���⽻����
   * @throws Exception
   */
  private String getSkgk(String jsjdm, String swjgdm, Connection conn) throws
      Exception {
    try {

      String sqlWhere
          = "SELECT GKJHH FROM DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '" +
          swjgdm + "'";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sqlWhere);
      if (rs.next())
        return rs.getString("gkjhh");
      else
        return null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "--��ȡ�տ�������!");
    }
  }

//    /**
//     * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
//     * @param jsjdm ���������
//     * @return sbbh
//     * @throws DeclareException
//     */
//    public String getSbbh(String jsjdm)
//    {
//        //�õ���ǰʱ��
//        long currentTime = System.currentTimeMillis();
//        String sbbh = jsjdm + Long.toHexString(currentTime);  //��׼ʱ�䣬1970��1��1��0�������ĺ�����
//        return sbbh;
//    }

  /**
   * ����ֳɽ��
   * @param je ʵ�ɽ��
   * @param bl �ֳɱ�����Ĭ����0��00��
   * @return �ֳɽ��(����4λС��)
   */
  private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
    BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
    return fc;
  }

  /**
   * ͨ��˰��˰Ŀ������ȷ��Ԥ�㼶��
   * @param jsjdm ���������
   * @param szsmdm ˰��˰Ŀ����
   * @param rq ����
   * @return Ysjc Ԥ�㼶����Ϣ
   */
  private Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) {
    Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
    //����˰�ѹ���ӿڵõ�Ԥ�㼶��
    try {
      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
          com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
      com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
          getYsjcInfo(jsjdm, szsmdm, rq);
      if (sfysjc != null) {
        ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return ysjc;
  }

  /**
   * �����������ȡ����ʱ���������ж�Ӧ���У�ֱ�����½���������
   * @param jsjdm ���������8λ
   * @param ny ����yyyyMM 6λ
   * @param conn ���ݿ�����
   * @return String xh
   * @throws Exception
   */
  private String getXh(String jsjdm, String ny, Connection conn) throws
      Exception {
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("��������벻��Ϊ�գ�");
    if (ny == null || ny.equals(""))
      throw new Exception("¼�����ڲ���Ϊ�գ�");

    String sequence = "0"; //Ĭ��
    int xh = 0; //Ĭ��Ϊ0
    try {
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //����sql��䣬������ȡ��ǰ���
      sqlStringBuffer.append(
          "SELECT XH FROM SBDB.SB_JL_JKPZHKZ WHERE JSJDM = '")
          .append(jsjdm)
          .append("' AND NY='").append(ny).append("'");
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
        xh = 48; //����0(ASCII:48)
      }
      else {
        xhStr = TinyTools.stringToASCII(TinyTools.asciiToString(xh + 1));
      }

      sqlStringBuffer.append("UPDATE SBDB.SB_JL_JKPZHKZ SET XH='")
          .append(xhStr).append("', NY='").append(ny)
          .append("' WHERE JSJDM = '").append(jsjdm).append("'");
      int i = st1.executeUpdate(sqlStringBuffer.toString());
      if (i == 0) {
        sqlStringBuffer.setLength(0);
        Statement stInsert = conn.createStatement();
        sqlStringBuffer.append(
            "INSERT INTO SBDB.SB_JL_JKPZHKZ (JSJDM, XH, NY) VALUES('")
            .append(jsjdm).append("','49','").append(ny).append("')");
        stInsert.addBatch(sqlStringBuffer.toString());
        stInsert.executeBatch();
      }
      sequence = String.valueOf(xh); //��ǰ���ú�����xh+1���˴�ֻ�������õ�������
      return sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "��ѯ�ü���������Ӧ�����ʧ��!");
    }
  }

  /**
   * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ����������������λ�����8λ���к�
   * @param jsjdm ���������
   * @return sbbh �걨���
   * @throws DeclareException �쳣
   */
  public String getSbbh(String jsjdm) throws Exception {
    return InterFaceProcessor.getSbbh(jsjdm);
  }

  /**
   * ˰Ʊ���������,˰Ʊ��������ɹ���Ϊ����������������λ�����8λ���к�
   * @param jsjdm ���������
   * @return sbbh �걨���
   * @throws DeclareException �쳣
   */
  public String getSphm(String jsjdm) throws Exception {
    return InterFaceProcessor.getSphm(jsjdm);
  }

  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯��
   */
  public List getYpdsJks(String sbbh) throws Exception {
    return InterFaceProcessor.getYpdsJks(sbbh);
  }

  /**
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param JksList һƱһ˰�ɿ��鼯��,�ü��ϱ��뱣֤����ͬһ���걨
   * @return һƱ��˰�ɿ��鼯��
   */
  public List getYpdsJks(List JksList) throws Exception {
    return InterFaceProcessor.getYpdsJks(JksList);
  }

}