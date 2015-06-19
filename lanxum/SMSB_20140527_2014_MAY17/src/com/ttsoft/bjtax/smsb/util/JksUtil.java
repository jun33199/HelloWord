/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ṩ�ɿ�����صĹ��÷���</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JksUtil
{
  //�����ʶ����(Ĭ��ֵΪ0000000000)
  public final static String ZWBS = "00000000000000000000";
  //һƱһ˰��ÿƱ��˰Ŀ
  private static final int SPLITNUM_SM = 4;
  //�Ļ���ҵ����Ѻ�����Ͷ����ҵ����ʹ�÷�
  private final static String SZSM_WHSYJSF = "53";
  private final static String SZSM_WSTZQYTDSYF = "76";
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
  public String getJkpzh(String jsjdm, Date time) throws Exception
  {
    String jkpzh = null;
    jkpzh = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getJkpzh(jsjdm);

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
      Exception
  {
    Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
//    if (szsmdm.substring(0, 2).equals(SZSM_WHSYJSF) ||
//        szsmdm.equals(SZSM_WSTZQYTDSYF)) {
    //����˰�ѹ���ӿڵõ�Ԥ�㼶��
    try
    {
      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new com.
          ttsoft.bjtax.sfgl.proxy.ServiceProxy();
      com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
          getYsjcInfo(jsjdm, szsmdm, rq);
      if (sfysjc == null)
      {
        //���û�еõ��϶��������϶�Ϊ�ط���
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
      throw ExceptionUtil.getBaseException(ex, "��ѯԤ�㼶��ʧ��!");
    }

//    }
//    else {
//      //���������Ϊ�ط���
//      ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
//    }
    return ysjc;
  }

  /**
   * �������д���ȡ������������־
   * @param  yhdm ���д���
   * @return String ����������־ "T"Ϊ����
   * @throws java.lang.Exception �����쳣
   */
  public String getLwzt(String yhdm) throws Exception
  {
    Connection conn = null;
    String ret = "";
    try
    {
      //�õ�����
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ����������־ʧ��!");
    }
    finally
    {
      //�ͷ�����
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * ���ɽɿ����ݲ��������ݿ�������
   *
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public List getJkData(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      BaseException
  {
    try
    {
      //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  }

  /**
   * ���ɽɿ����ݲ��������ݿ�������
   *
   * modified by qianchao 2005.11.2
   *
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public Map getJkDataZhsb(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype) throws
      BaseException
  {
    try
    {
      /*
             //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      HashMap retMap = new HashMap();
      //�������ɵĽɿ�������

      //start modifying by qianchao 2005.11.2
      //retMap.put(CodeConstant.ZHSB_JKS_TYPE, lwzt);
      //end modifying by qianchao 2005.11.2

      //�������ɵĽɿ����б�
      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
      return retMap;
      //return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  }

  /**��������ʹ��
   *
   * ���ɽɿ����ݲ��������ݿ�������
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @param  flag һƱһ˰CodeConstant.PRINT_YPYS��һƱ��˰CodeConstant.PRINT_YPDS_KM
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public List getJkDataSD(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      fillSbjkmxInfo(insbjkzb, mxList);
      //fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  } //End of getJkDataSD

  /**���幤�̻�/��ɢ��ʹ��
   *
   * ���ɽɿ����ݲ��������ݿ�������
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @param  flag һƱһ˰CodeConstant.PRINT_YPYS��һƱ��˰CodeConstant.PRINT_YPDS_KM
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public List getJkDataLS(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  } //End of getJkDataLS

  /**����ռ��˰ʹ��
  *
  * ���ɽɿ����ݲ��������ݿ�������
  * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
  * @param insbjkmxInfo �걨�ɿ���ϸList
  * @param  flag һƱһ˰CodeConstant.PRINT_YPYS��һƱ��˰CodeConstant.PRINT_YPDS_KM
  * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
  * @throws BaseException �쳣��Ϣ
  */
 public List getJkDataGDZYS(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
     BaseException
 {
   try
   {
     //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
     //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
     List mxList = this.convertMap2Vo(insbjkmxInfo);
     //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
     setSbbh(getSbbh(insbjkzb.getJsjdm()));
     //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
     //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
     fillSbjkmxInfo(insbjkzb, mxList);
     //2.����Ԥ���Ŀ�����ϸ����
     List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

     //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
     //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
     List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

     //���ش���
     return jkdataList;
   }
   catch (Exception ex)
   {
     throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
   }
 } //End of getJkDataGDZYS
  



  /**���幤�̻�/��ɢ��ʹ��
   *
   * ���ɽɿ����ݲ��������ݿ�������
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @param  flag һƱһ˰CodeConstant.PRINT_YPYS��һƱ��˰CodeConstant.PRINT_YPDS_KM
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public List getJkDataYhs(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  } //End of getJkDataLS

  /**��Ȼ�˸�������˰¼��ʹ��
   *
   * ���ɽɿ����ݲ��������ݿ�������
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @param  flag һƱһ˰CodeConstant.PRINT_YPYS��һƱ��˰CodeConstant.PRINT_YPDS_KM
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public List getJkDataZRR(Sbjkzb insbjkzb, List insbjkmxInfo, int flag) throws
      BaseException
  {
    try
    {
      //��������������־���ô�ӡ״̬����������־Ϊ"T"ʱΪ����
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
      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      //List mxList = this.convertMap2Vo(insbjkmxInfo);
      List mxList = insbjkmxInfo;
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfo(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  } //End of getJkDataLS

  /**
   * ����걨��ϸList�е�Ԥ�㼶�κͿ�Ŀ��Ϣ
   * @param insbjkzb �ɿ�������Ϣ
   * @param insbjkmxInfo �ɿ���ϸ��Ϣ
   * @throws Exception �����쳣
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
        throw ExceptionUtil.getBaseException(ex, "��ȡԤ���Ŀʧ��!");
      }

    }
  }

  /**
   * �ڽɿ���ϸ���������Ԥ�㼶�κ�Ԥ���Ŀ��Ϣ
   * @param sbjkmx �����Ľɿ���ϸ
   * @param insbjkzb �걨�ɿ�����
   * @throws Exception �����쳣
   */
  private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception
  {
  	
	  Timestamp tempXjrq=insbjkzb.getXjrq();
  	
    //1.����Ԥ�㼶�β����
    //Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(), new Date());
    Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(),
                        sbjkmx.getSkssjsrq());
    sbjkmx.setYsjcdm(ysjc.getYsjcdm());
    sbjkmx.setYsjcmc(ysjc.getYsjcmc());
//System.out.println("+++++++++++++++++++++++"+insbjkzb.getJsjdm()+" : "+sbjkmx.getSzsmdm()+" : "+sbjkmx.getSkssjsrq());
//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ysjc.getYsjcdm()+" : "+ysjc.getYsjcmc());
    //2.����Ԥ���Ŀ���벢���
//    Yskm yskm = getYskm(
//        sbjkmx.getSzsmdm(), insbjkzb.getDjzclxdm(), insbjkzb.getGjbzhydm(),
//        ysjc.getYsjcdm());
    /**Modified by lufeng
         //���üƻ�ӿڣ���ȡԤ���Ŀ*/
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
      //sbjkmx.setYskmmc("���к˹�ҵ����˰");
      throw new ApplicationException("��ȡԤ���Ŀʧ�ܣ�");
      //throw ExceptionUtil.getBaseException(new Exception(), "��ȡԤ���Ŀʧ��!");
    }

    //3.��д˰������ʱ��
    //ToDo.print(this.getClass(), "fillSbjkmx",
    //           "˰������ʱ�ڵ���д��ʽ�д�ȷ��,����Ϊ��̨��д������Skssrq����д");
//    Map map = Skssrq.getSksssq(sbjkmx.getSzsmdm(), insbjkzb.getSklxdm(),
//                               sbjkmx.getZqlxdm(), insbjkzb.getLrrq());
//    sbjkmx.setSkssksrq( (Timestamp) map.get(Skssrq.SKSSKSRQ));
//    sbjkmx.setSkssjsrq( (Timestamp) map.get(Skssrq.SKSSJSRQ));
    //ͨ�����������õ�˰���������ڵ�
    //Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getLrrq());
    /**
     * �����걨���ڵõ��޽�����
     */
    Zqrl zqrl = Skssrq.getSksssqByZqrl(sbjkmx.getSzsmdm(), insbjkzb.getSbrq(),
                                       insbjkzb.getDjzclxdm());
    //�����޽�����=���ڽ�ֹ����+1��
    if (zqrl != null)
    {
      Timestamp ts = zqrl.getZqzzrq();
      /**
       * �޽�����=���ڽ�ֹ����
       * Shi Yanfeng
       * 20031031
       */
      if (zqrl.getZqlxdm() != null && zqrl.getZqlxdm().equals("08"))
      {
        Debug.out("xjrq and szsmdm and zqlxdm equals '08' " + sbjkmx.getSzsmdm());
        //����õǼ�ע�����ʹ��롢��˰Ŀ��Ӧ���������ʹ���Ϊ08�������걨�����޽�����Ӧ��Ϊ��ǰ���ڵĺ�һ�죬
        ts = Timestamp.valueOf(DateUtil.addDatetimeByDay(ts.toString(), 1));
      }
      //ts.setDate(ts.getDate() + 1);
      if (zqrl.getZqzzrq().getTime() < insbjkzb.getSbrq().getTime())
      {
        //1.�����ǰ�걨���ڹ������ڵĽ������ڣ����޽�����Ϊ���죻
        ts = new Timestamp(System.currentTimeMillis());
      }
      insbjkzb.setXjrq(ts);
    }

    //������ɢ¼�룬�������ɢ��˰�����ܣ��޽�����Ϊ��ǰ���ڵĺ�һ�죡
    //Modified by lufeng 2004-03-24
    if (insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR) ||
        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_GTGSHHZ) ||
        insbjkzb.getSjly().equals(CodeConstant.SMSB_SJLY_LSHZ))
    {
      Timestamp nowTime = new Timestamp(System.currentTimeMillis());
      insbjkzb.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(nowTime.
          toString(), 1)));
    }


    //����Ǹ�����˰
    if (insbjkzb.getSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
    	insbjkzb.setXjrq(tempXjrq);
    }
    


    //�����
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //�����걨���
    /**Modified by lufeng*/
    if (sbjkmx.getYsjcdm().equals("21"))
    { //����ǵط���
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //�����оּ��ֳɽ��
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //�������ؼ��ֳɽ��
    }
    //by lufeng
    //sbjkmx.setSjfc(new BigDecimal("0.25"));
    //sbjkmx.setQjfc(new BigDecimal("0.35"));
    //������ϸ���ݵļ��������
    sbjkmx.setJsjdm(insbjkzb.getJsjdm());
    //������ϸ��˰�������֯��������
    sbjkmx.setSwjgzzjgdm(insbjkzb.getSwjgzzjgdm());
    //������ϸ�����
    sbjkmx.setNd(String.valueOf(TinyTools.getYear(insbjkzb.getSbrq())));
    //����������¼������
    sbjkmx.setLrrq(insbjkzb.getLrrq());
    //������ϸ�Ĵ�������
    sbjkmx.setCjrq(insbjkzb.getCjrq());
    //������ϸ�����ش���
    sbjkmx.setQxdm(insbjkzb.getQxdm());
  }

  /**
   * ����ֳɽ��
   * @param je ʵ�ɽ��
   * @param bl �ֳɱ�����Ĭ����0��00��
   * @return �ֳɽ��(����4λС��)�����ʵ�ɽ���ֳɱ���Ϊnull����Ż�0
   */
  public BigDecimal getFc(BigDecimal je, BigDecimal bl)
  {
    //
    //�ƻ�ӿ�û�з��طֳɱ���
    if (je == null || bl == null)
    {
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
  private List slipSbjkmxInfo(List sbjkmxInfo)
  {
    List splitsbjkmx = new ArrayList();
    List paramList = new ArrayList();

    //����Ԥ���Ŀ�����˰��������ʼ�ͽ�������ֽɿ���ϸ����
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
      //Debug.out("����걨����ʧ��!");
      splitsbjkmx = null;
    }
    return splitsbjkmx;
  }

  /**
   * ����˰�������֯���������ѯ������Ϣ
   * @param swjgzzjgdm ˰�������֯��������
   * @return swjgzzjg ˰�������֯������Ϣ
   * @throws java.lang.Exception �����쳣
   */
  public Swjgzzjg queryGkInfor(String swjgzzjgdm) throws Exception
  {
    Connection conn = null;
    try
    {
      //�õ�����
      conn = SfDBResource.getConnection();

      Vector criteria = new Vector(); //��ѯ����
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
    }
    finally
    {
      //�ͷ�����
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * ���ղ�ֺ���걨�ɿ���ϸ����(���List���ɵ�List,List�е�����ͬ��)��������걨��������
   * @param insbjkzb �걨�����һЩ������Ϣ
   * @param sbjkmxInfo ��ֺ���걨��ϸ����
   * @return �걨�ɿ��������ݵ�List
   * @throws java.lang.Exception �����е��쳣��Ϣ
   */
  private List creatjkData(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      Exception
  {
    //�õ���������������д����Ӧ��������
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
      //ȡ�������ʱ�����쳣������������
      ex.printStackTrace();
    }
    try
    {

      switch (printTag)
      {
        case CodeConstant.PRINT_YPYS: //һƱһ˰
          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
        case CodeConstant.PRINT_YPDS_KM: //һƱ��˰(��Ŀ)
          return createSkjkzbInfor_mortax(insbjkzb, sbjkmxInfo);
        default: //Ĭ��ΪһƱһ˰
          return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);

      }
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }

  }

  //�԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱһ˰���
  //�������ɵĽɿ�����Ϣ
  private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      BaseException
  {
    String sequence = null; //�ɿ�ƾ֤���е����к�
    //�õ���ǰ��������걨�ɿ������е������ˮ��.
    try
    {
//
      //�õ��ɿ�ƾ֤��
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;
      //�õ�¼�����ڵ����£�4λ��
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //ѭ���е���ʱ����
      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
      List jkInforList = new ArrayList(); //���صĽɿ�������List
      DeclareInfor jkInfor = null; //һ���ɿ�����
      List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list

      Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
      BigDecimal sjjehe; //ʵ�ɽ��
      for (int i = 0; i < sbjkmxInfo.size(); i++)
      {
        //����4����ϸ���
        splitsbjkmxList = TinyTools.splitList(
            (List) sbjkmxInfo.get(i), SPLITNUM_SM);
        //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
          sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
          //ͨ��˰�ѽӿڵõ��ɿ�ƾ֤��
          jkpzh = sbJkpzh + "0";
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
//                    ToDo.print(this.getClass(), "createSkjkzbInfor_onetax"
//                               , "һƱһ˰�����,��������ʱ��д�Ĵ�����Ϊ???");

          sbjkzbtmp =
              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                           CodeConstant.CLBJDM_YSB);
          sbjkzbList.add(sbjkzbtmp);
          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
          jkInfor.setPrintTag(printTag);
          jkInforList.add(jkInfor);
        }
      }
      //�����ɵ����ݲ������ݿ���
      insertSbjkData(sbjkzbList, sbjkmxInfo);

      return jkInforList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
    }

  }

  //�õ���ϸ���ݴ���һ��sbjkzb����
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
        sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); //ͳ��ʵ�ɽ��ĺϼ�
      }
    }
    //����ʵ�ɽ��������ֶ�����
    //Debug.out("ʵ�ɽ��ϼ�:" + sjjehe);
    cloneSbjkzb.setSjje(sjjehe);
    cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
    cloneSbjkzb.setYsjcmc(sbjkmxtmp.getYsjcmc());
    cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
    cloneSbjkzb.setYskmmc(sbjkmxtmp.getYskmmc());
    cloneSbjkzb.setSzdm(sbjkmxtmp.getSzdm());
    cloneSbjkzb.setSzmc(sbjkmxtmp.getSzmc());
    cloneSbjkzb.setRkje(sjjehe);
    cloneSbjkzb.setSbbh(sbbh); //�����걨���
    if(cloneSbjkzb.getZwbs()==null ||cloneSbjkzb.getZwbs().equals("") ||cloneSbjkzb.getZwbs().length()!=20){
     cloneSbjkzb.setZwbs(ZWBS); //�����ʶ��Ĭ��20��0
   }

    cloneSbjkzb.setClbjdm(clbjdm); //�����Ǵ��룬Ĭ��10
    //����걨�ɿ�����˰������ʱ��  add by wanghw
    cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
    cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
    //����걨�ɿ��������
    cloneSbjkzb.setNd(String.valueOf(TinyTools.getYear(cloneSbjkzb.getSbrq())));
    //��ҳ���ڵ����걨����
    if (cloneSbjkzb.getSbrq() == null || cloneSbjkzb.getSbrq().equals(""))
    {
      Debug.out("****************" + cloneSbjkzb.getJsjdm() +
                " : �걨����Ϊ��***************");
    }
    cloneSbjkzb.setZyrq(cloneSbjkzb.getSbrq());
    return cloneSbjkzb;
  }

  //�԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱ��˰���
  //�������ɵĽɿ�����Ϣ
  private List createSkjkzbInfor_mortax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
      BaseException
  {
    String sequence = null; //�ɿ�ƾ֤���е����к�
    //�õ���ǰ��������걨�ɿ������е������ˮ��.
    try
    {
      //sequence = getSequenceOfJkpzh(insbjkzb.getJsjdm(),
      //                              insbjkzb.getLrrq());
      //�õ��ɿ�ƾ֤��
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;

      //�õ�¼�����ڵ����£�4λ��
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //ѭ���е���ʱ����
      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
      List jkInforList = new ArrayList(); //���صĽɿ�������List(����Ʊ������)
      List onePageJkInfor = null; //һ��Ʊ������
      DeclareInfor jkInfor = null; //һ���ɿ�����
      List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
      BigDecimal sjjehe; //ʵ�ɽ��

      //��ÿ��4��(������4��)��ͬ�Ŀ�Ŀ˰�������з���
      List eachfourList = TinyTools.splitList(sbjkmxInfo, SPLITNUM_SM);

      for (int i = 0; i < eachfourList.size(); i++)
      {
        //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
        sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
        splitsbjkmxList = (List) eachfourList.get(i);
        onePageJkInfor = new ArrayList();
        //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          int mark = k + 1;
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; //һƱ��˰�����һλ��1��ʼ

          jkpzh = sbJkpzh + mark; //һƱ��˰�����һλ��1��ʼ
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
          //����һ���µ�sbjkzb����д�ɿ�ƾ֤��

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
      throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
    }
  }

  //add by 2003-09-28 wanghw
  /**
   * modified by qianchao 2005.11.1
   * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
   * ���Ϲ������ϣ�
   * ʹ���걨�Ľӿ������걨��š�
   *
   * @param jsjdm ���������
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
      throw ExceptionUtil.getBaseException(ex,"�����걨�����걨���ʧ�ܣ�");
    }

    /*
         //�õ���ǰʱ��
         long currentTime = System.currentTimeMillis();
         //�ο�ʱ��
         long targetTime = Long.parseLong("1064700000000");
         //�����걨���
         String sbbh = jsjdm + Long.toHexString(currentTime - targetTime);
     */
    return sbbh;
  }

  /**
   * ��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
   * @param insbjkmxInfo ��ת����HashMap��ʽ��List
   * @return List ת��ΪSbjkmx��ʽ��List
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
      //�������ϸ��ϲ���ʹ��һ��������Ҫ����ϸ���ݼ���ֵ����
      try
      {
        BeanUtil.populate(voTemp, map);
        ret.add(i, voTemp);
      }
      catch (Exception ex)
      {
        throw ExceptionUtil.getBaseException(ex, "ת��List��ʽʧ��");
      }
    }
    return ret;
  }

  /**
   * ���걨����,�걨��ϸ���ݲ������ݿ�
   * @param sbjkzbList �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
   * @param sbjkmxInfoList �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
   * ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
   * @throws BaseException �쳣��Ϣ
   */
  public void insertSbjkData(List sbjkzbList, List sbjkmxInfoList) throws
      BaseException
  {
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    //ORManager orManager = null;
    //ѭ�������������ϸ����
    try
    {
      // ������ݿ�����
      //conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);
      // ��� ORManager
      //orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //������������
      for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); )
      {
        //orManager.makePersistent(SESSIONID, conn, sbjkzbs.next());
        Sbjkzb temp = (Sbjkzb) sbjkzbs.next();
        //
        db.insert(temp);

      }

      //������ϸ����
      List sbjkmxList = null;
      for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
           sbjkmxLists.hasNext(); )
      {
        //ͬһ��Ŀ����ϸlist
        for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
             sbjkmxs.hasNext(); )
        {
          //ͬһ��Ŀ�ж�����ϸ
          //orManager.makePersistent(SESSIONID, conn, sbjkmxs.next());
          db.insert((Sbjkmx) sbjkmxs.next());
        }
      }
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * ͨ��sequence ����걨���ܵ���
   * ���ܵ��ŵĳ���Ϊ8λ
   * @param con ���ݿ�����
   * @return ���ػ��ܵ���
   * @throws Exception �����쳣
   */
  public String getSequenceOfSbhzd(Connection con) throws
      Exception
  {
    String sequence = null;
    //���ݿ�����
    //Connection conn = null;
    try
    {
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
    }
    catch (Exception ex)
    {
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
      Exception
  {
    String sequence = null;
    //���ݿ�����
    //Connection conn = null;
    try
    {
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
    }
    catch (Exception ex)
    {
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
  public List CxJks(String jkpzh, String qxdm) throws
      Exception
  {
    //��ؽɿ�����ʾ��Ϣ

    Connection conn = null;
    try
    {

      //�õ����ݿ�����
      conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);
      //�õ���ؽɿ����б�
      List ret = this.getCoJks(jkpzh, qxdm, conn);

      Vector v = new Vector();
      String sql = "";

      if (jkpzh.length() == 15)
      {
        //�ɿ�ƾ֤�ų���Ϊ15ʱΪһƱ��˰
        sql = "jkpzh like '" + jkpzh + "%'";
      }
      else
      {
        //һƱһ˰
        sql = "jkpzh = '" + jkpzh + "'";
      }
      //ɾ����ϸ������
      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where qxdm='" + qxdm +
                   "' and " + sql);
      //ɾ����������
      int nret = db.updateSQL("delete from sbdb.sb_jl_sbjkzb where qxdm='" +
                              qxdm +
                              "' and " + sql + "  AND zwbs like '" +
                              CodeConstant.SMSB_ZWBS + "%" +
                              CodeConstant.SMSB_ZWBS + "'  ");
      if (nret == 0)
      {
        //���û�п�ɾ��������ع�
        throw new ApplicationException("�����ɿ���ʧ�ܣ�");
      }
      return ret;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * �����ɿ��飬ɾ���ýɿ�����������ϸ�����������
   * ��˰�Ļ����Ǹ���˰�Ľɿ����
   * @param  sbbh �걨���
   * @param  qxdm ���ش���
   * @param  jsjdm ���������
   * @param conn ���ݿ�����
   * @throws Exception
   */
  public void CxAllJks(String sbbh, String qxdm, String jsjdm, Connection conn) throws
      Exception
  {
    //��ؽɿ�����ʾ��Ϣ

    //Connection conn = null;
    try
    {

      //�õ����ݿ�����
      //conn = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(conn);

      //ɾ����ϸ������
//      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where  qxdm='" + qxdm + "' and jsjdm='"+jsjdm+"' and sbbh='" + sbbh +
//                   "'");
      db.updateSQL("delete from sbdb.sb_jl_sbjkmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where  qxdm='" +
                   qxdm + "' and jsjdm='" + jsjdm + "' and sbbh='" + sbbh +
                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
                   CodeConstant.SMSB_ZWBS + "'  )");
      //ɾ����������
      db.updateSQL("delete from sbdb.sb_jl_sbjkzb where  qxdm='" + qxdm +
                   "' and jsjdm='" + jsjdm + "'  and sbbh='" + sbbh +
                   "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
                   CodeConstant.SMSB_ZWBS + "'  ");

    }
    catch (SystemException ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
    }
    finally
    {
      //SfDBResource.freeConnection(conn);
    }

  }

  /**
   * �õ��������ɿ����������ؽɿ����б�
   * @param  jkpzh �ɿ�ƾ֤��
   * @param  qxdm ���ش���
   * @param conn ���ݿ�����
   * @return List ��ؽɿ�����ʾ��Ϣ
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
        //�ɿ�ƾ֤�ų���Ϊ15ʱΪһƱ��˰
        v.add("jkpzh like '" + jkpzh + "%'");
      }
      else
      {
        //һƱһ˰
        v.add("jkpzh = '" + jkpzh + "'");
      }
      //�õ��������ɿ������ϸ�б�
      List mxList = db.query(new Sbjkmx().getClass(), v);
      Map mxMap = new HashMap();
      for (int i = 0; i < mxList.size(); i++)
      {
        Sbjkmx mxVo = (Sbjkmx) mxList.get(i);
        mxMap.put(mxVo.getSzsmdm(), mxVo);
      }
      //�õ���ϸֵ����
      Sbjkmx mxVo = (Sbjkmx) mxList.get(0);
      //�����걨��ŵõ�ͬһ�����ɵ����нɿ�����ϸ����
      Vector v1 = new Vector();
      v1.add("qxdm='" + qxdm + "'");
      v1.add("jsjdm='" + mxVo.getJsjdm() + "'");
      v1.add(" sbbh = '" + mxVo.getSbbh() + "' order by jkpzh");
      List coMx = db.query(new Sbjkmx().getClass(), v1);
      //�õ�˰��˰Ŀ�͸���˰�Ĵ����
      Vector v2 = new Vector();
      List sfList = db.query(new Szsmyfjs().getClass(), v2);

      for (int i = 0; i < coMx.size(); i++)
      {
        //�쿴�Ƿ��������ϸ
        Sbjkmx temp = (Sbjkmx) coMx.get(i);
        if (!temp.getJkpzh().substring(0, 15).equals(jkpzh.substring(0, 15)))
        {
          //���Ǳ�ɾ���ɿ���
          //1.����Ƿ��������˰
          for (int ii = 0; ii < sfList.size(); ii++)
          {
            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
            if (temp.getSzsmdm().equals(szsm) && mxMap.get(fjs) != null)
            {
              //���������˰����ӵ�����б���
              ret.add(temp);
            }
          }

          //2.����Ƿ������ظ���˰
          for (int ii = 0; ii < sfList.size(); ii++)
          {
            String fjs = ( (Szsmyfjs) sfList.get(ii)).getFjsszsmdm();
            String szsm = ( (Szsmyfjs) sfList.get(ii)).getSzsmdm();
            if (temp.getSzsmdm().equals(fjs) && mxMap.get(szsm) != null)
            {
              //������ظ���˰����ӵ�����б���
              ret.add(temp);
            }
          }
        }

      }
      return ret;
    }
    catch (BaseException ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�õ���ؽɿ���ʧ��!");
    }
  }

  /**
   * һƱ��˰��ʾǰ��������
   * @param detList �����б�
   * @return ArrayList
   * */
  public static ArrayList getYpdsList(ArrayList detList)
  {
    //���ø�ʽ������
    DecimalFormat deFormat = new DecimalFormat("#0.00");
    ArrayList detailList = new ArrayList();
    ArrayList tempList = new ArrayList();
    detailList = (ArrayList) detList.clone();
    //���򷵻�
    if (detList.size() <= 0)
    {
      return detList;
    }

    int intCount = 1;
    while (intCount < detailList.size())
    {
      //ʵ����
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
   * ���ɽɿ����ݲ��������ݿ�������
   *
   *
   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
   * @param insbjkmxInfo �걨�ɿ���ϸList
   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
   * @throws BaseException �쳣��Ϣ
   */
  public Map getJkDataKjqysds(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype) throws
      BaseException
  {
    try
    {
      printTag = jkstype;

      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
      List mxList = this.convertMap2Vo(insbjkmxInfo);
      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
      setSbbh(getSbbh(insbjkzb.getJsjdm()));
      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
      //fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      fillSbjkmxInfoKj(insbjkzb, mxList);
      //2.����Ԥ���Ŀ�����ϸ����
      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
      List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

      //���ش���
      HashMap retMap = new HashMap();
      //�������ɵĽɿ�������

      //start modifying by qianchao 2005.11.2
      //retMap.put(CodeConstant.ZHSB_JKS_TYPE, lwzt);
      //end modifying by qianchao 2005.11.2

      //�������ɵĽɿ����б�
      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
      return retMap;
      //return jkdataList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
    }
  }
  /**
   * ����걨��ϸList�е�Ԥ�㼶�κͿ�Ŀ��Ϣ
   * @param insbjkzb �ɿ�������Ϣ
   * @param insbjkmxInfo �ɿ���ϸ��Ϣ
   * @throws Exception �����쳣
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
        throw ExceptionUtil.getBaseException(ex, "��ȡԤ���Ŀʧ��!");
      }
    }
  }

  /**
   * �ڽɿ���ϸ���������Ԥ�㼶�κ�Ԥ���Ŀ��Ϣ
   * @param sbjkmx �����Ľɿ���ϸ
   * @param insbjkzb �걨�ɿ�����
   * @throws Exception �����쳣
   */
  private void fillSbjkmxKj(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception
  {
    //1.����Ԥ�㼶�β����
    //Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(), new Date());
    Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(), sbjkmx.getSzsmdm(),
                        sbjkmx.getSkssjsrq());
    sbjkmx.setYsjcdm(ysjc.getYsjcdm());
    sbjkmx.setYsjcmc(ysjc.getYsjcmc());

    sbjkmx.setYskmdm("101043509");
    sbjkmx.setYskmmc("�۰�̨������Ͷ����ҵ����˰");
    
  //���üƻ�ӿڣ���ȡԤ���Ŀ*/
    Yskm yskm = KjssjksHelper.getYskm(sbjkmx.getYskmdm(),insbjkzb.getSbrq());


    //�޽�����=��ǰ����+6
      Timestamp nowTime = new Timestamp(System.currentTimeMillis());
      insbjkzb.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(nowTime.
          toString(), 6)));

    //�����
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //�����걨���
    
    /**Modified by lufeng*/
    if (sbjkmx.getYsjcdm().equals("21"))
    { //����ǵط���
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //�����оּ��ֳɽ��
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //�������ؼ��ֳɽ��
    }

    //������ϸ���ݵļ��������
    sbjkmx.setJsjdm(insbjkzb.getJsjdm());
    //������ϸ��˰�������֯��������
    sbjkmx.setSwjgzzjgdm(insbjkzb.getSwjgzzjgdm());
    //������ϸ�����
    sbjkmx.setNd(String.valueOf(TinyTools.getYear(insbjkzb.getSbrq())));
    //����������¼������
    sbjkmx.setLrrq(insbjkzb.getLrrq());
    //������ϸ�Ĵ�������
    sbjkmx.setCjrq(insbjkzb.getCjrq());
    //������ϸ�����ش���
    sbjkmx.setQxdm(insbjkzb.getQxdm());
  }
  
	/**
	 * �ƶ��걨�ɿ��鲻��һԪ�����ݵ�his���У�
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

		// �������ݿ�����
		Connection conn = null;

		try {
			// ������ݿ�����
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
			throw ExceptionUtil.getBaseException(ex, "�걨�ɿ�ת�����ݴ���!");
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}
  
	
/*--add by lijn 20141014  copy from above-----*/
/*--the functions below is used for saving date into history table when we havn't save it in ordinary table-----*/
/*-- fisrt used in С΢��ҵӪҵ˰���⣬С΢��ҵ�Ļ�����Ѽ��� --*/	
	
	/**
	   * ���ɽɿ����ݲ��������ݿ�������;������ʷ��
	   *
	   * modified by qianchao 2005.11.2
	   *
	   * @param insbjkzb �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
	   * @param insbjkmxInfo �걨�ɿ���ϸList
	   * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
	   * @throws BaseException �쳣��Ϣ
	   */
	  public Map getJkDataZhsb_his(Sbjkzb insbjkzb, List insbjkmxInfo,int jkstype,String zrlxdm ,String zrr,String sbbh) throws
	      BaseException
	  {
	    try
	    {
	      
	      printTag = jkstype;
	  
	      //��HashMap��ʽ��Listת����Sbjkmx��ʽ��List
	      List mxList = this.convertMap2Vo(insbjkmxInfo);
	      
	      //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
	      setSbbh(sbbh==null||"".equals(sbbh)?getSbbh(insbjkzb.getJsjdm()):sbbh);
	      
	      //1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
	      fillSbjkmxInfo(insbjkzb, mxList);
	      
	      //2.����Ԥ���Ŀ�����ϸ����
	      List sbjkmxInfoByyskmdm = slipSbjkmxInfo(mxList);

	      //3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
	      //���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
	      List jkdataList = creatjkData_his(insbjkzb, sbjkmxInfoByyskmdm ,zrlxdm ,zrr);

	      //���ش���
	      HashMap retMap = new HashMap();
	      //�������ɵĽɿ�������

	      //�������ɵĽɿ����б�
	      retMap.put(CodeConstant.ZHSB_JKS_LIST, jkdataList);
	      return retMap;
	      //return jkdataList;
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
	    }
	  }
	
	  /**
	   * ���ղ�ֺ���걨�ɿ���ϸ����(���List���ɵ�List,List�е�����ͬ��)��������걨��������
	   * @param insbjkzb �걨�����һЩ������Ϣ
	   * @param sbjkmxInfo ��ֺ���걨��ϸ����
	   * @return �걨�ɿ��������ݵ�List
	   * @throws java.lang.Exception �����е��쳣��Ϣ
	   */
	  private List creatjkData_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
	      Exception
	  {
	    //�õ���������������д����Ӧ��������
	    try
	    {

	      insbjkzb.setGkzzjgdm(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
	          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "gkjhh"));
	      insbjkzb.setGkzzjgmc(CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
	          "swjgzzjgdm", insbjkzb.getZsswjgzzjgdm(), "skgk"));
	    }
	    catch (Exception ex)
	    {
	      //ȡ�������ʱ�����쳣������������
	      ex.printStackTrace();
	    }
	    try
	    {

	      switch (printTag)
	      {
	        case CodeConstant.PRINT_YPYS: //һƱһ˰
	          return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);
	        case CodeConstant.PRINT_YPDS_KM: //һƱ��˰(��Ŀ)
	          return createSkjkzbInfor_mortax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);
	        default: //Ĭ��ΪһƱһ˰
	          return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo,zrlxdm,zrr);

	      }
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "�������ݴ���,�������Ա��ϵ!");
	    }

	  }
	
	  /**
	 * @Description: TODO һ˰һƱ�ɿ�
	 * @param insbjkzb
	 * @param sbjkmxInfo
	 * @return
	 * @throws BaseException
	 */
	private List createSkjkzbInfor_onetax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws BaseException
  {
    String sequence = null; //�ɿ�ƾ֤���е����к�
    //�õ���ǰ��������걨�ɿ������е������ˮ��.
    try
    {
//
      //�õ��ɿ�ƾ֤��
      //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
      String sbJkpzh = null;
      //�õ�¼�����ڵ����£�4λ��
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      NumberFormat nmbFormat = new DecimalFormat("000");

      //ѭ���е���ʱ����
      List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
      List jkInforList = new ArrayList(); //���صĽɿ�������List
      DeclareInfor jkInfor = null; //һ���ɿ�����
      List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list

      Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
      BigDecimal sjjehe; //ʵ�ɽ��
      for (int i = 0; i < sbjkmxInfo.size(); i++)
      {
        //����4����ϸ���
        splitsbjkmxList = TinyTools.splitList(
            (List) sbjkmxInfo.get(i), SPLITNUM_SM);
        //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
        for (int k = 0; k < splitsbjkmxList.size(); k++)
        {
          //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
          //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
          sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
          //ͨ��˰�ѽӿڵõ��ɿ�ƾ֤��
          jkpzh = sbJkpzh + "0";
          tmpSbjkmxList = (List) splitsbjkmxList.get(k);
//                    ToDo.print(this.getClass(), "createSkjkzbInfor_onetax"
//                               , "һƱһ˰�����,��������ʱ��д�Ĵ�����Ϊ???");

          sbjkzbtmp =
              createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                           CodeConstant.CLBJDM_YSB);
          sbjkzbList.add(sbjkzbtmp);
          jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
          jkInfor.setPrintTag(printTag);
          jkInforList.add(jkInfor);
        }
      }
      //�����ɵ����ݲ������ݿ���
      insertSbjkData_his(sbjkzbList, sbjkmxInfo,zrlxdm,zrr);

      return jkInforList;
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
    }

  }
	  
	/**
	 * @Description: TODO һ˰��Ʊ�ɿ�
	 * @param insbjkzb
	 * @param sbjkmxInfo
	 * @return
	 * @throws BaseException
	 */
	private List createSkjkzbInfor_mortax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
    BaseException
{
  String sequence = null; //�ɿ�ƾ֤���е����к�
  //�õ���ǰ��������걨�ɿ������е������ˮ��.
  try
  {
    //sequence = getSequenceOfJkpzh(insbjkzb.getJsjdm(),
    //                              insbjkzb.getLrrq());
    //�õ��ɿ�ƾ֤��
    //String sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(),insbjkzb.getLrrq());
    String sbJkpzh = null;

    //�õ�¼�����ڵ����£�4λ��
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
    String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
    NumberFormat nmbFormat = new DecimalFormat("000");

    //ѭ���е���ʱ����
    List sbjkzbList = new ArrayList(); //�����Ľɿ�����List
    List jkInforList = new ArrayList(); //���صĽɿ�������List(����Ʊ������)
    List onePageJkInfor = null; //һ��Ʊ������
    DeclareInfor jkInfor = null; //һ���ɿ�����
    List splitsbjkmxList = null; //��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
    Sbjkzb sbjkzbtmp = null;
    String jkpzh = null;
    List tmpSbjkmxList; //��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
    BigDecimal sjjehe; //ʵ�ɽ��

    //��ÿ��4��(������4��)��ͬ�Ŀ�Ŀ˰�������з���
    List eachfourList = TinyTools.splitList(sbjkmxInfo, SPLITNUM_SM);

    for (int i = 0; i < eachfourList.size(); i++)
    {
      //sequence = nmbFormat.format(Integer.parseInt(sequence) + 1);
      sbJkpzh = this.getJkpzh(insbjkzb.getJsjdm(), insbjkzb.getLrrq());
      splitsbjkmxList = (List) eachfourList.get(i);
      onePageJkInfor = new ArrayList();
      //�Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
      for (int k = 0; k < splitsbjkmxList.size(); k++)
      {
        int mark = k + 1;
        //jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; //һƱ��˰�����һλ��1��ʼ

        jkpzh = sbJkpzh + mark; //һƱ��˰�����һλ��1��ʼ
        tmpSbjkmxList = (List) splitsbjkmxList.get(k);
        //����һ���µ�sbjkzb����д�ɿ�ƾ֤��

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
    throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
  }
} 
	
	/**
	   * �걨����ֱ�Ӳ�����ʷ���Żݼ���ʱʹ��
	   * @param sbjkzbList �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
	   * @param sbjkmxInfoList �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
	   * ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
	   * @throws BaseException �쳣��Ϣ
	   */
	  public void insertSbjkData_his(List sbjkzbList, List sbjkmxInfoList,String zrlxdm ,String zrr) throws
	      BaseException
	  {
	    //�������ݿ�����
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
	    
	    //ORʵ��
	    //ORManager orManager = null;
	    //ѭ�������������ϸ����
	    try
	    {
	      // ������ݿ�����
	      //conn = DBResource.getConnection(DBResource.DB_SHENBAO);
	      conn = SfDBResource.getConnection();
	      SfDBAccess db = new SfDBAccess(conn);
	      // ��� ORManager
	      //orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

	      //������������_his
	      for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); )
	      {
	        //orManager.makePersistent(SESSIONID, conn, sbjkzbs.next());
	        Sbjkzb temp = (Sbjkzb) sbjkzbs.next();
	        
	        //��sbjkzbתhis
	        Sbjkzb_His sbjkzbHis_temp = new Sbjkzb_His();
			BeanUtil.copyBeanToBean(sbjkZbNames, temp, sbjkzbHis_temp);

			sbjkzbHis_temp.setZrlxdm(zrlxdm);
			sbjkzbHis_temp.setZrrq(now);
			sbjkzbHis_temp.setZrr(zrr);
	        db.insert(sbjkzbHis_temp);

	      }

	      //������ϸ����_his
	      for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
	           sbjkmxLists.hasNext(); )
	      {
	        //ͬһ��Ŀ����ϸlist
	        for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
	             sbjkmxs.hasNext(); )
	        {	
	         //����ϸ����תΪ��ʷ����
	        	
	        	Sbjkmx sbjkmx_temp = (Sbjkmx) sbjkmxs.next();
				Sbjkmx_His sbjkmxHis_temp = new Sbjkmx_His();
				BeanUtil.copyBeanToBean(sbjkmxNames, sbjkmx_temp, sbjkmxHis_temp);

				sbjkmxHis_temp.setZrlxdm(zrlxdm);
				sbjkmxHis_temp.setZrrq(now);
				sbjkmxHis_temp.setZrr(zrr);

	        	
	          //ͬһ��Ŀ�ж�����ϸ
	          //orManager.makePersistent(SESSIONID, conn, sbjkmxs.next());
	          db.insert(sbjkmxHis_temp);
	        }
	      }
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
	    }
	    finally
	    {
	      SfDBResource.freeConnection(conn);
	    }
	  }
	
	  
	
}
