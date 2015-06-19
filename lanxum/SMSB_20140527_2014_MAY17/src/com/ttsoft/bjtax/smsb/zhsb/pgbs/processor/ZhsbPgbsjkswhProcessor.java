/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.pgbs.web.ZhsbPgbsjkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import java.util.Vector;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ���ۺ��걨_������˰���ܣ������ɿ���¼�룬ά����</p>
 * @author zzb  20090330
 * @version 1.1
 */

public class ZhsbPgbsjkswhProcessor
    implements Processor
{
  public ZhsbPgbsjkswhProcessor()
  {
  }

  //private static final String YPYS = "Ypys"; //һƱһ˰��������
  //private static final String YPDS = "Ypds"; //һƱ��˰��������
  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException
  {
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      case CodeConstant.SMSB_UPDATEACTION:
        return doCx(vo);

      default:
        return null;
    }

  }

  /**
   * ��ʾ�ü���������Ӧ������δ���ɿ���
   * modified by qianchao 2005.11.2
   *
   * @param     vo ҵ�����
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm������
   * @throws BaseException ҵ���쳣
   */
  //������˰
  private ZhsbPgbsjkswhActionForm doQuery(VOPackage vo) throws BaseException
  {

    //�������ݿ�����
    Connection conn = null;
    SfDBUtils sbDB = null;

    try
    {	
      //������˰
      ZhsbPgbsjkswhActionForm form = (ZhsbPgbsjkswhActionForm) vo.getData(); //��ǰ�˻��map����
      //�õ�userdata
      UserData userData = vo.getUserData();
      //�õ����ش���
      String qxdm = InterfaceDj.getQxdm(userData);
      String jsjdm = form.getJsjdm(); //��ȡ�û��ļ��������
      //������Դ
      String sjly = form.getSjly();
      if (sjly == null || sjly.equals(""))
      {
        //δָ�������������Դ������ȱʡ��Ϊ�걨¼��
        sjly = CodeConstant.SMSB_SJLY_SBLR;
      }
      //������ݿ�����
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //��� ORManager


      //���ò�ѯ����
      Vector tempSql = new Vector();

      //������˰,�����ڵ�"ά���ɿ���"��ťʱ��ֻ��ѯ��"˰�����ʹ���"Ϊ"������˰"�ļ�¼
      tempSql.add("SKLXDM=600");

      tempSql.add("LRR='" + userData.getYhid() + "'");
      tempSql.add("QXDM='" + qxdm + "'");
      tempSql.add("JSJDM='" + jsjdm + "'");
      tempSql.add("SJLY='" + sjly + "'");
      tempSql.add("FSDM='" + CodeConstant.FSDM_SMSB + "'");
      tempSql.add("ZWBS LIKE '0%0'");
       //����걨��Ϊ�գ���ֻ��ʾ���걨��ŵĽɿ����б�
      if (form.getSbbh() != null && !form.getSbbh().equals("") &&
          form.getPresbbh() != null && form.getPresbbh().equals("1"))
      {
        tempSql.add("SBBH='" + form.getSbbh() + "'");
      }
      //started modified by qianchao 2005-12-19
//      tempSql.add("SBBH NOT IN "
//              + " (SELECT SBBH FROM SBDB.SB_JL_SBJKZB "
//              + "  WHERE ZYRQ >= to_date('20050101','yyyymmdd') AND "
//              + "  QXDM = '" + qxdm + "' AND "
//              + "  JSJDM = '" + jsjdm + "' AND"
//              + "  SJLY='" + sjly + "' AND "
//              + "  FSDM='" + CodeConstant.FSDM_SMSB + "' AND "
//              + "  ((SUBSTR(ZWBS, 1, 1) <> '0') OR (SUBSTR(ZWBS, 20, 1) <> '0'))) ");
      tempSql.add("ZYRQ >= to_date('20050101','yyyymmdd')");
      tempSql.add("ND=to_char(sysdate,'yyyy')");
      //ended   modified by qianchao 2005-12-19
      tempSql.addElement("1=1 ORDER BY SBRQ DESC,SBBH,JKPZH ASC");

      //started added by qianchao 2005-12-19
      long t = System.currentTimeMillis();
      //ended   added by qianchao 2005-12-19
      
      List tempList = da.query(Sbjkzb.class, tempSql);

      //started added by qianchao 2005-12-19
      //������˰
      Debug.out("com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbPgbsjkswhProcessor.doQuery time cost:" + (System.currentTimeMillis() - t));
      //ended   added by qianchao 2005-12-19
      
      //���˰�����ƺ������б�
      Debug.out("tempList.size()=" + tempList.size());

      HashMap sbmaps = new HashMap();
      SBData sb = null;
      for (int i = 0; i < tempList.size(); i++)
      {
        //��ʽ��ÿ����¼
        Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
        sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                                                  sbjkzb.getSzdm())); //˰������
        sb = (SBData)sbmaps.get(sbjkzb.getSbbh());
        if (sb == null)
        {
          sb = new SBData();
          sbmaps.put(sbjkzb.getSbbh(),sb);
        }
        sb.addSbjkzb(sbjkzb);
      }

      //��mapתΪlist
      Iterator c = sbmaps.values().iterator();
      ArrayList datalist = new ArrayList();

      while (c.hasNext())
      {
        datalist.add(c.next());
      }
      //��ֵ�Ż�form����

      form.setDataList(datalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * �����б��е�˰�ִ���õ�˰������
   * @param  list �걨�ɿ������б� List ���� HashMap�͵���Ϣ
   * @return ArrayList ���˰�����Ƶ��걨�ɿ������б�
   * @author Shi Yanfeng 20040112
   */
  /* deleted by qianchao 2005.11.3
  private ArrayList addSzmc(ArrayList list)
  {
    for (int i = 0; i < list.size(); i++)
    {
      HashMap temp = (HashMap) list.get(i);
      //ͨ��˰��˰Ŀ�����õ�˰�ִ���
      temp.put("szmc",
               CodeUtils.getCodeBeanLabel("DM_SZSM", (String) temp.get("szdm")));
    }
    return list;
  }
*/

  /**
   * ����ָ���ɿ��� ���걨
   * modified by qianchao 2005.10.27
   *
   * @param     vo ҵ�����
   * @return ArrayList ���˰�����Ƶ��걨�ɿ������б�
   * @throws BaseException ҵ���쳣
   */
  //������˰
  private ZhsbPgbsjkswhActionForm doCx(VOPackage vo) throws BaseException
  {
	//������˰
	ZhsbPgbsjkswhActionForm form = (ZhsbPgbsjkswhActionForm) vo.getData(); //��ǰ�˻��map����
    //�õ�userdata
    UserData userData = vo.getUserData();
    //�õ����ش���
    String qxdm = InterfaceDj.getQxdm(userData);
    /**
     * �����־
     * 20040509 ShiYanfeng
     */
    TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
    JksUtil jks = new JksUtil();
    Connection conn = null;

    try
    {

      //start modifying by qianchao 2005.10.27
      //ArrayList ret = (ArrayList) jks.CxJks(form.getJkpzhStr(), qxdm);
      ArrayList ret = null;
      if (form.getJksType() == CodeConstant.PRINT_YPYS)
      {
        jks.CxJks(form.getJkpzhStr(), qxdm);
      }
      else
      {
        conn = SfDBResource.getConnection();
        jks.CxAllJks(form.getSbbh(),qxdm,form.getJsjdm(),conn);
      }
      //form.setCoList(ret);
      //end modifying by qianchao 2005.10.27
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
    return form;
  }

}
