/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

//import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
//import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
//import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
//import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
//import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//import com.ttsoft.bjtax.sfgl.common.util.Debug;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
//import com.ttsoft.bjtax.smsb.constant.CodeConstant;
//import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.sfgl.common.model.Ysjc;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ExceptionUtil;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ṩ�ɿ�����صĹ��÷���</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JksUtil {
    //�����ʶ����(Ĭ��ֵΪ0000000000)
    public final static String ZWBS = "00000000000000000000";
    //һƱһ˰��ÿƱ��˰Ŀ
    private static final int SPLITNUM_SM = 4;
//  //�Ļ���ҵ����Ѻ�����Ͷ����ҵ����ʹ�÷�
//  private final static String SZSM_WHSYJSF = "53";
//  private final static String SZSM_WSTZQYTDSYF = "76";
    /**
     * �걨���
     */
    private String sbbh;
    //��ӡ��ʶ
    private int printTag;
    /**
     * �õ��ɿ�ƾ֤��  16λ<br>
     * ��������:<br>
     * ----���������(8λ)���꣨2λ�����£�2λ����3λ˳��ţ�1λ��ˮ��<br>
     * ----˳���Ϊ��ǰ��������뱾�µļ�¼����1<br>
     * ----��ˮ��ΪһƱ��˰�����һ��ƾ֤�ж����Ŀ��˳���<br>
     * @param jsjdm ���������
     * @param time Ҫ����ҵ�ʱ��
     * @return String �������һλ��ˮ�ŵĽɿ�ƾ֤��
     * @throws Exception �����쳣
     */
    public static String getJkpzh(String jsjdm) throws Exception {
        String jkpzh = null;
        jkpzh = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getJkpzh(jsjdm);
        //����һλ��ˮ��
        jkpzh = jkpzh + "0";
        Debug.out("ȡ�õĽɿ����Ϊ��" + jkpzh);
        Debug.out("ȡ�õĽɿ���ŵĳ���Ϊ��" + jkpzh.length());

        return jkpzh;
    }

    /**
     * ͨ��˰��˰Ŀ������ȷ��Ԥ�㼶��<br>
     * ��˰��Ϊ�Ļ���ҵ����ѻ�����Ͷ����ҵ����ʹ�÷�ʱ
     * ͨ��˰�ѽӿ�ȡ����Ӧ��Ԥ�㼶���������Ϊ�ط���
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param rq ����
     * @return Ysjc Ԥ�㼶����Ϣ
     * @throws Exception
     */
    public static Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) throws
            Exception {
        Ysjc ysjc = new Ysjc();
        ysjc.setYsjcdm(Constants.YSJCDM_DF); //����Ĭ��Ϊ���ط�����
//    //����˰�ѹ���ӿڵõ�Ԥ�㼶��
//    try
//    {
//      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy =
//                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//      Ysjc sfysjc =
//                sfServiceProxy.getYsjcInfo(jsjdm, szsmdm, rq);
//      if (sfysjc != null)
//      {
//          ysjc.setYsjcdm(sfysjc.getYsjcdm());
//          ysjc.setYsjcmc(sfysjc.getYsjcmc());
//      }
//
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "��ѯԤ�㼶��ʧ��!");
//    }
        return ysjc;
    }

    /**
     * ͨ��˰��˰Ŀ����ѯԤ���Ŀ����
     * @param szsmdm String
     * @return String
     * @throws Exception
     */
//  public static String getYskm(String szsmdm) throws Exception
//  {
//      String yskm = "";
//
//      String sql =
//          "select t.yskmdm from dmdb.kj_dm_szsm_yskm t where t.szsmdm = '" +
//          szsmdm + "' ";
//
//      Debug.out("ȡ��Ԥ���Ŀ�����sql = " + sql);
//      Connection conn = null;
//      PreparedStatement pst = null;
//      ResultSet rs = null;
//
//      try
//      {
//          conn = QSDBUtil.getConnection();
//
//          pst = conn.prepareStatement(sql);
//
//          rs = pst.executeQuery();
//
//          if (rs.next())
//          {
//              yskm = rs.getString(1);
//          }
//      }
//      catch (Exception ex)
//      {
//          throw ExceptionUtil.getBaseException(ex, "��ѯԤ���Ŀ����ʧ��!!!");
//      }
//      finally
//      {
//          try
//          {
//              rs.close();
//          }
//          catch (Exception ex)
//          {
//              ex.printStackTrace();
//          }
//          pst.close();
//      }
//
//      return yskm;
//  }


    /**
     * ͨ��˰��˰Ŀ������ȷ��Ԥ�㼶��<br>
     * ��˰��Ϊ�Ļ���ҵ����ѻ�����Ͷ����ҵ����ʹ�÷�ʱ
     * ͨ��˰�ѽӿ�ȡ����Ӧ��Ԥ�㼶���������Ϊ�ط���
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param rq ����
     * @return Ysjc Ԥ�㼶����Ϣ
     * @throws Exception
     */
    /** @todo ���Ե�ʱ�����ȡ��������KJ_DM_SZSM_YSKM���ձ� */
    public static Yskm getYskm(com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb
                               sbjkzb, String szsmdm) throws
            Exception {
        Yskm yskm = new Yskm();
        //����˰�ѹ���ӿڵõ�Ԥ�㼶��
        try {
//           //���üƻ�ӿڣ���ȡԤ���Ŀ*/
//          yskm = JKAdapter.getInstance().getYskm(szsmdm,
//                                                sbjkzb.getDjzclxdm(),
//                                                sbjkzb.getGjbzhydm(),
//                                                sbjkzb.getYsjcdm());
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯԤ���Ŀʧ��!");
        }
        return yskm;
    }

//  /**
//   * ���ɽɿ����ݲ��������ݿ�������
//   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
//   * @param insbjkmxInfo �걨�ɿ���ϸList
//   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
//   * @throws BaseException �쳣��Ϣ
//   */
//  public List getJkData(Sbjkzb insbjkzb, List insbjkmxInfo) throws
//      BaseException
//  {
//    try
//    {
//      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
//      List mxList = this.convertMap2Vo(insbjkmxInfo);
//      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
//      sbbh = getSbbh(insbjkzb.getJsjdm());
//      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
//      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
//      fillSbjkmxInfo(insbjkzb, mxList);
//      //2.����Ԥ���Ŀ�����ϸ����
//      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);
//
//      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
//      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
//      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);
//
//      //���ش���
//      return jkdataList;
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
//    }
//  }
//
//  /**
//   * ���ɽɿ����ݲ��������ݿ�������
//   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
//   * @param insbjkmxInfo �걨�ɿ���ϸList
//   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
//   * @throws BaseException �쳣��Ϣ
//   */
//  public Map getJkDataZhsb(Sbjkzb insbjkzb, List insbjkmxInfo) throws
//      BaseException
//  {
//    try
//    {
//      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
//      List mxList = this.convertMap2Vo(insbjkmxInfo);
//      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
//      sbbh = getSbbh(insbjkzb.getJsjdm());
//      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
//      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
//      fillSbjkmxInfo(insbjkzb, mxList);
//      //2.����Ԥ���Ŀ�����ϸ����
//      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);
//
//      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
//      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
//      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);
//
//      //���ش���
//      HashMap retMap = new HashMap();
//      //�������ɵĽɿ�������
////      retMap.put(CodeConstant.ZHSB_JKS_TYPE, lwzt);
////      //�������ɵĽɿ����б�
////      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
//      return retMap;
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
//    }
//  }

    /**
     * ����걨��ϸList�е�Ԥ�㼶�κͿ�Ŀ��Ϣ
     * @param insbjkzb �ɿ�������Ϣ
     * @param insbjkmxInfo �ɿ���ϸ��Ϣ
     * @throws Exception �����쳣
     */
//  private void fillSbjkmxInfo(Sbjkzb insbjkzb, List insbjkmxInfo) throws
//      Exception
//  {
//    Sbjkmx sbjkmxTmp = null;
//    String qylxdm = insbjkzb.getDjzclxdm();
//    String gjbzhydm = insbjkzb.getGjbzhydm();
//    String sklxdm = insbjkzb.getSklxdm();
//    for (int i = 0; i < insbjkmxInfo.size(); i++)
//    {
//      sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
//      try
//      {
//        fillSbjkmx(sbjkmxTmp, insbjkzb);
//      }
//      catch (Exception ex)
//      {
//        throw ExceptionUtil.getBaseException(ex, "��ȡԤ���Ŀʧ��!");
//      }
//
//    }
//  }

    /**
     * �ڽɿ���ϸ���������Ԥ�㼶�κ�Ԥ���Ŀ��Ϣ
     * @param sbjkmx �����Ľɿ���ϸ
     * @param insbjkzb �걨�ɿ�����
     * @throws Exception �����쳣
     */
//  private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception
//  {
//    //1.����Ԥ�㼶�β����
//    //Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(), new Date());
//    Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(),
//                        sbjkmx.getSkssjsrq());
//    sbjkmx.setYsjcdm(ysjc.getYsjcdm());
//    sbjkmx.setYsjcmc(ysjc.getYsjcmc());
////System.out.println("+++++++++++++++++++++++"+insbjkzb.getJsjdm()+" : "+sbjkmx.getSzsmdm()+" : "+sbjkmx.getSkssjsrq());
////    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ysjc.getYsjcdm()+" : "+ysjc.getYsjcmc());
//    //2.����Ԥ���Ŀ���벢���
////    Yskm yskm = getYskm(
////        sbjkmx.getSzsmdm(), insbjkzb.getDjzclxdm(), insbjkzb.getGjbzhydm(),
////        ysjc.getYsjcdm());
//    /**Modified by lufeng
//         //���üƻ�ӿڣ���ȡԤ���Ŀ*/
//    Yskm yskm = JKAdapter.getInstance().getYskm(sbjkmx.getSzsmdm(),
//                                                insbjkzb.getDjzclxdm(),
//                                                insbjkzb.getGjbzhydm(),
//                                                ysjc.getYsjcdm());
//    if (yskm != null)
//    {
//      sbjkmx.setYskmdm(yskm.getYskmdm());
//      sbjkmx.setYskmmc(yskm.getYskmmc());
//
//    }
//    else
//    {
//      //sbjkmx.setYskmdm("041400");
//      //sbjkmx.setYskmmc("���к˹�ҵ����˰");
//      throw new ApplicationException("��ȡԤ���Ŀʧ�ܣ�");
//      //throw ExceptionUtil.getBaseException(new Exception(), "��ȡԤ���Ŀʧ��!");
//    }
//
//    //3.��д˰������ʱ��
//    //ToDo.print(this.getClass(), "fillSbjkmx",
//    //           "˰������ʱ�ڵ���д��ʽ�д�ȷ��,����Ϊ��̨��д������Skssrq����д");
////    Map map = Skssrq.getSksssq(sbjkmx.getSzsmdm(), insbjkzb.getSklxdm(),
////                               sbjkmx.getZqlxdm(), insbjkzb.getLrrq());
////    sbjkmx.setSkssksrq( (Timestamp) map.get(Skssrq.SKSSKSRQ));
////    sbjkmx.setSkssjsrq( (Timestamp) map.get(Skssrq.SKSSJSRQ));
//    //ͨ�����������õ�˰���������ڵ�
//    //Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getLrrq());
//    /**
//     * �����걨���ڵõ��޽�����
//     */
//    Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getSbrq(),
//                                       insbjkzb.getDjzclxdm());
//    //�����޽�����=���ڽ�ֹ����+1��
//    if (zqrl != null)
//    {
//      Timestamp ts = zqrl.getZqzzrq();
//      /**
//       * �޽�����=���ڽ�ֹ����
//       * Shi Yanfeng
//       * 20031031
//       */
//      if (zqrl.getZqlxdm() != null && zqrl.getZqlxdm().equals("08"))
//      {
//        Debug.out("xjrq and szsmdm and zqlxdm equals '08' " + sbjkmx.getSzsmdm());
//        //����õǼ�ע�����ʹ��롢��˰Ŀ��Ӧ���������ʹ���Ϊ08�������걨�����޽�����Ӧ��Ϊ��ǰ���ڵĺ�һ�죬
//        ts = Timestamp.valueOf(DateUtil.addDatetimeByDay(ts.toString(), 1));
//      }
//      //ts.setDate(ts.getDate() + 1);
//      if (zqrl.getZqzzrq().getTime() < insbjkzb.getSbrq().getTime())
//      {
//        //1.�����ǰ�걨���ڹ������ڵĽ������ڣ����޽�����Ϊ���죻
//        ts = new Timestamp(System.currentTimeMillis());
//      }
//      insbjkzb.setXjrq(ts);
//    }
//
//    //������ɢ¼�룬�������ɢ��˰�����ܣ��޽�����Ϊ��ǰ���ڵĺ�һ�죡
//    //Modified by lufeng 2004-03-24
//    if (insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR) ||
//        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_GTGSHHZ) ||
//        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSHZ))
//    {
//      Timestamp nowTime = new Timestamp(System.currentTimeMillis());
//      insbjkzb.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(nowTime.
//          toString(), 1)));
//    }
//
//    //�����
//    sbjkmx.setRkje(sbjkmx.getSjse());
//    sbjkmx.setSbbh(sbbh); //�����걨���
//    /**Modified by lufeng*/
//    if (sbjkmx.getYsjcdm().equals("21"))
//    { //����ǵط���
//      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //�����оּ��ֳɽ��
//      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //�������ؼ��ֳɽ��
//    }
//    //by lufeng
//    //sbjkmx.setSjfc(new BigDecimal("0.25"));
//    //sbjkmx.setQjfc(new BigDecimal("0.35"));
//    //������ϸ���ݵļ��������
//    sbjkmx.setJsjdm(insbjkzb.getJsjdm());
//    //������ϸ��˰�������֯��������
//    sbjkmx.setSwjgzzjgdm(insbjkzb.getSwjgzzjgdm());
//    //������ϸ�����
//    DateUtil dt = new DateUtil();
//    sbjkmx.setNd(String.valueOf(dt.getYear()));
//    //����������¼������
//    sbjkmx.setLrrq(insbjkzb.getLrrq());
//    //������ϸ�Ĵ�������
//    sbjkmx.setCjrq(insbjkzb.getCjrq());
//    //������ϸ�����ش���
//    sbjkmx.setQxdm(insbjkzb.getQxdm());
//  }

    /**
     * ����ֳɽ��
     * @param je ʵ�ɽ��
     * @param bl �ֳɱ�����Ĭ����0��00��
     * @return �ֳɽ��(����4λС��)�����ʵ�ɽ���ֳɱ���Ϊnull����Ż�0
     */
    private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
        //
        //�ƻ�ӿ�û�з��طֳɱ���
        if (je == null || bl == null) {
            return new BigDecimal(0);
        }
        BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
        //BigDecimal fc = je.multiply(je).setScale(4, BigDecimal.ROUND_HALF_UP);
        return fc;
    }

    /**
     * ����Ԥ���Ŀ��Ϣ���sbjkmxInfoΪ���List(ÿ��List�е�sbjkmxʵ������ͬԤ���Ŀ��)
     * ��ɵ�Ƕ��List
     * @param sbjkmxInfo �걨�ɿ���ϸ��Ϣ(����sbjkmxʵ����List)
     * @return List ��Ա����ΪList
     */
//  private List slipSbjkmxInfo(List sbjkmxInfo)
//  {
//    List splitsbjkmx = new ArrayList();
//    List paramList = new ArrayList();
//
//    //����Ԥ���Ŀ�����˰��������ʼ�ͽ�������ֽɿ���ϸ����
//    paramList.add("getYskmdm");
//    paramList.add("getSkssksrq");
//    paramList.add("getSkssjsrq");
//    try
//    {
//      splitsbjkmx = FindObjInList.splitListByParam(sbjkmxInfo, Sbjkmx.class,
//          paramList);
//    }
//    catch (Exception ex)
//    {
//      //Debug.out("����걨����ʧ��!");
//      splitsbjkmx = null;
//    }
//    return splitsbjkmx;
//  }

    /**
     * ����˰�������֯���������ѯ������Ϣ
     * @param swjgzzjgdm ˰�������֯��������
     * @return swjgzzjg ˰�������֯������Ϣ
     * @throws java.lang.Exception �����쳣
     */
//  public Swjgzzjg queryGkInfor(String swjgzzjgdm) throws Exception
//  {
//    Connection conn = null;
//    try
//    {
//      //�õ�����
//      conn = QSDBUtil.getConnection();
//
//      Vector criteria = new Vector(); //��ѯ����
//      criteria.add("SWJGZZJGDM = '" + swjgzzjgdm + "'");
////      SfDBAccess db = new SfDBAccess(conn);
////      List queryresult = db.query(new Swjgzzjg().getClass(), criteria);
//      if (queryresult.size() == 0)
//      {
//        return null;
//      }
//      else
//      {
//        return (Swjgzzjg) queryresult.get(0);
//      }
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
//    }
//    finally
//    {
//      //�ͷ�����
//      QSDBUtil.freeConnection(conn);
//    }
//  }

    /**
     * ���ղ�ֺ���걨�ɿ���ϸ����(���List���ɵ�List,List�е�����ͬ��)��������걨��������
     * @param insbjkzb �걨�����һЩ������Ϣ
     * @param sbjkmxInfo ��ֺ���걨��ϸ����
     * @return �걨�ɿ��������ݵ�List
     * @throws java.lang.Exception �����е��쳣��Ϣ
     */
//  private List creatjkData(Sbjkzb insbjkzb, List sbjkmxInfo) throws
//      Exception
//  {
//    //�õ���������������д����Ӧ��������
//    try
//    {
////      Swjgzzjg swjgzzjg = queryGkInfor(insbjkzb.
////                                       getSwjgzzjgdm());
////      if (swjgzzjg != null) {
////        insbjkzb.setGkzzjgdm(swjgzzjg.getGkjhh());
////        insbjkzb.setGkzzjgmc(swjgzzjg.getSkgk());
////
////      }
//      insbjkzb.setGkzzjgdm(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "gkjhh"));
//      insbjkzb.setGkzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "skgk"));
//    }
//    catch (Exception ex)
//    {
//      //ȡ�������ʱ�����쳣������������
//      ex.printStackTrace();
//    }
//    try
//    {
//
//      switch (printTag)
//      {
//        case CodeConstant.PRINT_YPYS: //һƱһ˰
//          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
//        case CodeConstant.PRINT_YPDS_KM: //һƱ��˰(��Ŀ)
//          return createSkjkzbInfor_mortax(insbjkzb, sbjkmxInfo);
//        default: //Ĭ��ΪһƱһ˰
//          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
//
//      }
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
//    }
//
//  }
//
//  //�԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱһ˰���
//  //�������ɵĽɿ�����Ϣ
//  private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
//      BaseException
//  {
//    String sequence = null; //�ɿ�ƾ֤���е����к�
//    //�õ���ǰ��������걨�ɿ������е������ˮ��.
//    try
//    {
////
//      //�õ��ɿ�ƾ֤��
//      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
//      String sbJkpzh = null;
//      //�õ�¼�����ڵ����£�4λ��
//      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
//      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
//      NumberFormat nmbFormat = new DecimalFormat("000");
//
//      //ѭ���е���ʱ����
//      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
//      List jkInforList = new ArrayList(); //���صĽɿ�������List
//      DeclareInfor jkInfor = null; //һ���ɿ�����
//      List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
//
//      Sbjkmx sbjkmxtmp = null;
//      Sbjkzb sbjkzbtmp = null;
//      String jkpzh = null;
//      List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
//      BigDecimal sjjehe; //ʵ�ɽ��
//      for (int i = 0; i < sbjkmxInfo.size(); i++)
//      {
//        //����4����ϸ���
//        splitsbjkmxList = TinyTools.splitList(
//            (List) sbjkmxInfo.get(i), SPLITNUM_SM);
//        //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
//        for (int k = 0; k < splitsbjkmxList.size(); k++)
//        {
//          //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
//          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
//          sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
//          //ͨ��˰�ѽӿڵõ��ɿ�ƾ֤��
//          jkpzh = sbJkpzh + "0";
//          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
////                    ToDo.print(this.getClass(), "createSkjkzbInfor_onetax"
////                               , "һƱһ˰�����,��������ʱ��д�Ĵ�����Ϊ???");
//          sbjkzbtmp =
//              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
//                           CodeConstant.CLBJDM_YSB);
//          sbjkzbList.add(sbjkzbtmp);
//          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
//          jkInforList.add(jkInfor);
//        }
//      }
//      //�����ɵ����ݲ������ݿ���
//      insertSbjkData(sbjkzbList, sbjkmxInfo);
//
//      return jkInforList;
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
//    }
//
//  }
//
//  //�õ���ϸ���ݴ���һ��sbjkzb����
//  private Sbjkzb createSbjkzb(
//      List sbjkmxList, Sbjkzb sbjkzb, String jkpzh, String clbjdm)
//  {
//    Sbjkzb cloneSbjkzb = sbjkzb.getCopy();
//    cloneSbjkzb.setJkpzh(jkpzh);
//    Sbjkmx sbjkmxtmp = null;
//    BigDecimal sjjehe = new BigDecimal(0.00);
//    for (int j = 0; j < sbjkmxList.size(); j++)
//    {
//      sbjkmxtmp = (Sbjkmx) sbjkmxList.get(j);
//      sbjkmxtmp.setJkpzh(jkpzh);
//      if (sbjkmxtmp.getSjse() != null)
//      {
//        sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); //ͳ��ʵ�ɽ��ĺϼ�
//      }
//    }
//    //����ʵ�ɽ��������ֶ�����
//    //Debug.out("ʵ�ɽ��ϼ�:" + sjjehe);
//    cloneSbjkzb.setSjje(sjjehe);
//    cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
//    cloneSbjkzb.setYsjcmc(sbjkmxtmp.getYsjcmc());
//    cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
//    cloneSbjkzb.setYskmmc(sbjkmxtmp.getYskmmc());
//    cloneSbjkzb.setSzdm(sbjkmxtmp.getSzdm());
//    cloneSbjkzb.setSzmc(sbjkmxtmp.getSzmc());
//    cloneSbjkzb.setRkje(sjjehe);
//    cloneSbjkzb.setSbbh(sbbh); //�����걨���
//    cloneSbjkzb.setZwbs(ZWBS); //�����ʶ��Ĭ��20��0
//    cloneSbjkzb.setClbjdm(clbjdm); //�����Ǵ��룬Ĭ��10
//    //����걨�ɿ�����˰������ʱ��  add by wanghw
//    cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
//    cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
//    //����걨�ɿ��������
//    cloneSbjkzb.setNd(String.valueOf(DateUtil.getYear(cloneSbjkzb.getSbrq())));
//    //��ҳ���ڵ����걨����
//    if (cloneSbjkzb.getSbrq() == null || cloneSbjkzb.getSbrq().equals(""))
//    {
//      Debug.out("****************" + cloneSbjkzb.getJsjdm() +
//                " : �걨����Ϊ��***************");
//    }
//    cloneSbjkzb.setZyrq(cloneSbjkzb.getSbrq());
//    return cloneSbjkzb;
//  }
//
//  //�԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱ��˰���
//  //�������ɵĽɿ�����Ϣ
//  private List createSkjkzbInfor_mortax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
//      BaseException
//  {
//    String sequence = null; //�ɿ�ƾ֤���е����к�
//    //�õ���ǰ��������걨�ɿ������е������ˮ��.
//    try
//    {
//      //sequence = getSequenceOfJkpzh(insbjkzb.getJsjdm(),
//      //                              insbjkzb.getLrrq());
//      //�õ��ɿ�ƾ֤��
//      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
//      String sbJkpzh = null;
//
//      //�õ�¼�����ڵ����£�4λ��
//      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
//      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
//      NumberFormat nmbFormat = new DecimalFormat("000");
//
//      //ѭ���е���ʱ����
//      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
//      List jkInforList = new ArrayList(); //���صĽɿ�������List(����Ʊ������)
//      List onePageJkInfor = null; //һ��Ʊ������
//      DeclareInfor jkInfor = null; //һ���ɿ�����
//      List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
//      Sbjkzb sbjkzbtmp = null;
//      String jkpzh = null;
//      List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
//      BigDecimal sjjehe; //ʵ�ɽ��
//
//      //��ÿ��4��(������4��)��ͬ�Ŀ�Ŀ˰�������з���
//      List eachfourList = TinyTools.splitList(sbjkmxInfo, SPLITNUM_SM);
//
//      for (int i = 0; i < eachfourList.size(); i++)
//      {
//        //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
//        sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
//        splitsbjkmxList = (List) eachfourList.get(i);
//        onePageJkInfor = new ArrayList();
//        //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
//        for (int k = 0; k < splitsbjkmxList.size(); k++)
//        {
//          int mark = k + 1;
//          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; //һƱ��˰�����һλ��1��ʼ
//
//          jkpzh = sbJkpzh + mark; //һƱ��˰�����һλ��1��ʼ
//          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
//          //����һ���µ�sbjkzb����д�ɿ�ƾ֤��
//          sbjkzbtmp =
//              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
//                           CodeConstant.CLBJDM_WCL);
//          sbjkzbList.add(sbjkzbtmp);
//          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
//          onePageJkInfor.add(jkInfor);
//        }
//        jkInforList.add(onePageJkInfor);
//      }
//      insertSbjkData(sbjkzbList, sbjkmxInfo);
//
//      return jkInforList;
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
//    }
//  }

    //add by 2003-09-28 wanghw
    /**
     * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
     * @param jsjdm ���������
     * @return sbbh
     */
    public static String getSbbh(String jsjdm) {
        //�õ���ǰʱ��
        long currentTime = System.currentTimeMillis();
        //�ο�ʱ��
        long targetTime = Long.parseLong("1064700000000");
        //�����걨���
        String sbbh = jsjdm + Long.toHexString(currentTime - targetTime);
        return sbbh;
    }

    /**
     * ��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
     * @param insbjkmxInfo ��ת����HashMap��ʽ��List
     * @return List ת��ΪSbjkmx��ʽ��List
     * @throws BaseException
     */
//  private List convertMap2Vo(List insbjkmxInfo) throws
//      BaseException
//  {
//
//    List ret = new ArrayList();
//    for (int i = 0; i < insbjkmxInfo.size(); i++)
//    {
//      HashMap map = (HashMap) insbjkmxInfo.get(i);
//      Sbjkmx voTemp = new Sbjkmx();
//      //�������ϸ��ϲ���ʹ��һ��������Ҫ����ϸ���ݼ���ֵ����
//      try
//      {
//        BeanUtil.populate(voTemp, map);
//        ret.add(i, voTemp);
//      }
//      catch (Exception ex)
//      {
//        throw ExceptionUtil.getBaseException(ex, "ת��List��ʽʧ��");
//      }
//    }
//    return ret;
//  }

    /**
     * ���걨����,�걨��ϸ���ݲ������ݿ�
     * @param sbjkzbList �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
     * @param sbjkmxInfoList �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
     * ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
     * @throws BaseException �쳣��Ϣ
     */
//  public void insertSbjkData(List sbjkzbList, List sbjkmxInfoList) throws
//      BaseException
//  {
//    //�������ݿ�����
//    Connection conn = null;
//    //ORʵ��
//    //ORManager orManager = null;
//    //ѭ�������������ϸ����
//    try
//    {
//      // ������ݿ�����
//      //conn = DBResource.getConnection(DBResource.DB_SHENBAO);
//      conn = SfDBResource.getConnection();
//      SfDBAccess db = new SfDBAccess(conn);
//      // ��� ORManager
//      //orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
//
//      //������������
//      for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); )
//      {
//        //orManager.makePersistent(SESSIONID, conn, sbjkzbs.next());
//        Sbjkzb temp = (Sbjkzb) sbjkzbs.next();
//        //
//        db.insert(temp);
//
//      }
//
//      //������ϸ����
//      List sbjkmxList = null;
//      for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
//           sbjkmxLists.hasNext(); )
//      {
//        //ͬһ��Ŀ����ϸlist
//        for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
//             sbjkmxs.hasNext(); )
//        {
//          //ͬһ��Ŀ�ж�����ϸ
//          //orManager.makePersistent(SESSIONID, conn, sbjkmxs.next());
//          db.insert( (Sbjkmx) sbjkmxs.next());
//        }
//      }
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
//    }
//    finally
//    {
//      SfDBResource.freeConnection(conn);
//    }
//  }

    /**
     * ͨ��sequence ����걨���ܵ���
     * ���ܵ��ŵĳ���Ϊ8λ
     * @param con ���ݿ�����
     * @return ���ػ��ܵ���
     * @throws Exception �����쳣
     */
    public String getSequenceOfSbhzd(Connection con) throws
            Exception {
        String sequence = null;
        //���ݿ�����
        //Connection conn = null;
        try {
            // ������ݿ�����
            //conn = SfDBResource.getConnection();
            String sql = "select sbdb.seq_sb_hzdh.nextval from dual";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //���ܵ���
            long hzdhindex = rs.getLong("nextval");
            NumberFormat nmbFormat = new DecimalFormat("00000000");
            sequence = nmbFormat.format(hzdhindex);

            return sequence;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�õ����ܵ��ŵ����˳���ʧ��!");
        }
    } //End of getSequenceOfSbhzd

    /**
     * ͨ��sequence ��ø��幤�̻�/��ɢ�����
     * ���ܵ��ŵĳ���Ϊ8λ
     * @param con ���ݿ�����
     * @return ���ػ��ܵ���
     * @throws Exception �����쳣
     */
    public String getSequenceOfWSZXH(Connection con) throws
            Exception {
        String sequence = null;
        //���ݿ�����
        //Connection conn = null;
        try {
            // ������ݿ�����
            //conn = SfDBResource.getConnection();
            String sql = "select sbdb.seq_sb_wszxh.nextval from dual";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //���ܵ���
            String wszxh = rs.getString("nextval");
            //NumberFormat nmbFormat = new DecimalFormat("00000000");
            //sequence = nmbFormat.format(hzdhindex);
            return wszxh;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�õ����к�ʧ��!");
        }
    } //End of getSequenceOfWSZXH

    /**
     * �����ɿ��飬ɾ���ýɿ�����������ϸ����������ݣ�������ؽɿ������ʾ��Ϣ
     * ��˰�Ļ����Ǹ���˰�Ľɿ����
     * @param  jkpzh �ɿ�ƾ֤��
     * @param  qxdm ���ش���
     * @return List ��ؽɿ�����Ϣ
     * @throws Exception
     */
//  public List CxJks(String jkpzh, String qxdm) throws
//      Exception
//  {
//    //��ؽɿ�����ʾ��Ϣ
//
//    Connection conn = null;
//    try
//    {
//
//      //�õ����ݿ�����
//      conn = SfDBResource.getConnection();
//      SfDBAccess db = new SfDBAccess(conn);
//      //�õ���ؽɿ����б�
//      List ret = this.getCoJks(jkpzh, qxdm, conn);
//
//      Vector v = new Vector();
//      String sql = "";
//
//      if (jkpzh.length() == 15)
//      {
//        //�ɿ�ƾ֤�ų���Ϊ15ʱΪһƱ��˰
//        sql = "jkpzh like '" + jkpzh + "%'";
//      }
//      else
//      {
//        //һƱһ˰
//        sql = "jkpzh = '" + jkpzh + "'";
//      }
//      //ɾ����ϸ������
//      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where qxdm='" + qxdm +
//                   "' and " + sql);
//      //ɾ����������
//      int nret = db.updateSQL("delete from sbdb.sb_jl_sbjkzb where qxdm='" +
//                              qxdm +
//                              "' and " + sql + "  AND zwbs like '" +
//                              CodeConstant.SMSB_ZWBS + "%" +
//                              CodeConstant.SMSB_ZWBS + "'  ");
//      if (nret == 0)
//      {
//        //���û�п�ɾ��������ع�
//        throw new ApplicationException("�����ɿ���ʧ�ܣ�");
//      }
//      return ret;
//    }
//    catch (Exception ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
//    }
//    finally
//    {
//      SfDBResource.freeConnection(conn);
//    }
//
//  }

    /**
     * �����ɿ��飬ɾ���ýɿ�����������ϸ�����������
     * ��˰�Ļ����Ǹ���˰�Ľɿ����
     * @param  sbbh �걨���
     * @param  qxdm ���ش���
     * @param  jsjdm ���������
     * @param conn ���ݿ�����
     * @throws Exception
     */
//  public void CxAllJks(String sbbh, String qxdm, String jsjdm, Connection conn) throws
//      Exception
//  {
//    //��ؽɿ�����ʾ��Ϣ
//
//    //Connection conn = null;
//    try
//    {
//
//      //�õ����ݿ�����
//      //conn = SfDBResource.getConnection();
//      SfDBAccess db = new SfDBAccess(conn);
//
//      //ɾ����ϸ������
////      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where  qxdm='" + qxdm + "' and jsjdm='"+jsjdm+"' and sbbh='" + sbbh +
////                   "'");
//      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where  qxdm='" +
//                   qxdm + "' and jsjdm='" + jsjdm + "' and sbbh='" + sbbh +
//                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
//                   CodeConstant.SMSB_ZWBS + "'  )");
//      //ɾ����������
//      db.updateSQL("delete from sbdb.sb_jl_sbjkzb where  qxdm='" + qxdm +
//                   "' and jsjdm='" + jsjdm + "'  and sbbh='" + sbbh +
//                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
//                   CodeConstant.SMSB_ZWBS + "'  ");
//
//    }
//    catch (SystemException ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
//    }
//    finally
//    {
//      //SfDBResource.freeConnection(conn);
//    }
//
//  }

//  /**
//   * �õ��������ɿ����������ؽɿ����б�
//   * @param  jkpzh �ɿ�ƾ֤��
//   * @param  qxdm ���ش���
//   * @param conn ���ݿ�����
//   * @return List ��ؽɿ�����ʾ��Ϣ
//   * @throws Exception
//   */
//  public List getCoJks(String jkpzh, String qxdm, Connection conn) throws
//      Exception
//  {
//    List ret = new ArrayList();
//    try
//    {
//      SfDBAccess db = new SfDBAccess(conn);
//      Vector v = new Vector();
//      v.add("qxdm='" + qxdm + "'");
//      if (jkpzh.length() == 15)
//      {
//        //�ɿ�ƾ֤�ų���Ϊ15ʱΪһƱ��˰
//        v.add("jkpzh like '" + jkpzh + "%'");
//      }
//      else
//      {
//        //һƱһ˰
//        v.add("jkpzh = '" + jkpzh + "'");
//      }
//      //�õ��������ɿ������ϸ�б�
//      List mxList = db.query(new Sbjkmx().getClass(), v);
//      Map mxMap = new HashMap();
//      for (int i = 0; i < mxList.size(); i++)
//      {
//        Sbjkmx mxVo = (Sbjkmx) mxList.get(i);
//        mxMap.put(mxVo.getSzsmdm(), mxVo);
//      }
//      //�õ���ϸֵ����
//      Sbjkmx mxVo = (Sbjkmx) mxList.get(0);
//      //�����걨��ŵõ�ͬһ�����ɵ����нɿ�����ϸ����
//      Vector v1 = new Vector();
//      v1.add("qxdm='" + qxdm + "'");
//      v1.add("jsjdm='" + mxVo.getJsjdm() + "'");
//      v1.add(" sbbh = '" + mxVo.getSbbh() + "' order by jkpzh");
//      List coMx = db.query(new Sbjkmx().getClass(), v1);
//      //�õ�˰��˰Ŀ�͸���˰�Ĵ����
//      Vector v2 = new Vector();
//      List sfList = db.query(new Szsmyfjs().getClass(), v2);
//
//      for (int i = 0; i < coMx.size(); i++)
//      {
//        //�쿴�Ƿ��������ϸ
//        Sbjkmx temp = (Sbjkmx) coMx.get(i);
//        if (!temp.getJkpzh().substring(0, 15).equals(jkpzh.substring(0, 15)))
//        {
//          //���Ǳ�ɾ���ɿ���
//          //1.����Ƿ��������˰
//          for (int ii = 0; ii < sfList.size(); ii++)
//          {
//            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
//            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
//            if (temp.getSzsmdm().equals(szsm) && mxMap.get(fjs) != null)
//            {
//              //���������˰����ӵ�����б���
//              ret.add(temp);
//            }
//          }
//
//          //2.����Ƿ������ظ���˰
//          for (int ii = 0; ii < sfList.size(); ii++)
//          {
//            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
//            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
//            if (temp.getSzsmdm().equals(fjs) && mxMap.get(szsm) != null)
//            {
//              //������ظ���˰����ӵ�����б���
//              ret.add(temp);
//            }
//          }
//        }
//
//      }
//      return ret;
//    }
//    catch (BaseException ex)
//    {
//      throw ExceptionUtil.getBaseException(ex, "�õ���ؽɿ���ʧ��!");
//    }
//  }
//
//  /**
//   * һƱ��˰��ʾǰ��������
//   * @param detList �����б�
//   * @return ArrayList
//   * */
//  public static ArrayList getYpdsList(ArrayList detList)
//  {
//    //���ø�ʽ������
//    DecimalFormat deFormat = new DecimalFormat("#0.00");
//    ArrayList detailList = new ArrayList();
//    ArrayList tempList = new ArrayList();
//    detailList = (ArrayList) detList.clone();
//    //���򷵻�
//    if (detList.size() <= 0)
//    {
//      return detList;
//    }
//
//    int intCount = 1;
//    while (intCount < detailList.size())
//    {
//      //ʵ����
//      HashMap tempMap = (HashMap) detailList.get(intCount);
//      tempList = (ArrayList) detailList.clone();
//      int flag = 0;
//      String tempJkpzh = (String) tempMap.get("jkpzh_ypds");
//      for (int i = 0; i < intCount; i++)
//      {
//        HashMap ypdsMap = (HashMap) tempList.get(i);
//        if ( (ypdsMap.get("jkpzh_ypds")).equals(tempJkpzh))
//        {
//          //
//          HashMap tMap = (HashMap) detailList.get(intCount - 1);
//          tMap.put("sjse",
//                   String.valueOf(deFormat.format(StringUtil.getDouble( (String)
//              tempMap.get("sjse"), 0.00) +
//                                                  StringUtil.getDouble( (String)
//              ypdsMap.get("sjse"), 0.00))));
//          detailList.remove(intCount);
//          flag = 1;
//          break;
//        }
//      } //loop;
//      if (flag == 0)
//      {
//        intCount++;
//      }
//    } //End of while
//
//    return detailList;
//  } //End of getYpdsList

}
