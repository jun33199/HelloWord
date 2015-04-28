/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.QysdsjbHelper;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web.QysdsjbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbProcessor
    implements Processor
{

  private java.util.Date time;

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   * 		1 ���������Ĳ������Ͳ���ʱ�׳�
   * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
   * 	�����쳣�׳���EJB��process��������
   */

  public Object process(VOPackage vo) throws BaseException
  {
    //�ش�����
    Object result = null;
    //�ж�VO�Ƿ�Ϊ��
    if (vo == null)
    {
      throw new NullPointerException();
    }
    //����Action��ֵ���ò�ͬ��process����
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_SHOWACTION: //ǰ̨Ĭ����ʾ
        result = doShow(vo);
        break;
      case CodeConstant.SMSB_QUERYACTION: //��ѯ
        result = doQuery(vo);
        break;
      case CodeConstant.SMSB_SAVEACTION: //�洢
        doSave(vo);
        break;
      case CodeConstant.SMSB_DELETEACTION: //ɾ��
        doDelete(vo);
        break;
      default:
        throw new UnsupportedOperationException(
            "Method process() not yet implemented.");
    }
    return result;
  }

  /**
   * doShow��ʼ������ҳ����ϢҪ��
   * @param vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  public Object doShow(VOPackage vo) throws BaseException
  {
    //�õ�Action���ݹ���CzzsjdForm����
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //�õ���ǰʱ���������
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(new Date());
    int month = calendar.get(calendar.MONTH);
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
//    Map getsbjd = Skssrq.otherSkssrq(curTime);
    Map getsbjd = this.quarterSkssrq(curTime);
    String nd = (String) getsbjd.get(Skssrq.SKSSRQ_ND);
    Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
    Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
    //˰��������ʼ����
    form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
    //˰��������������
    form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
    // }
    form.setSbrq(SfDateUtil.getDate());
    //Helper����dataList��
    List dataList = QysdsjbHelper.getShowList();
    form.setDataList(dataList);
    form.setNsrzt("99");
    System.out.println(dataList);
    return form;
  }

  /**
   * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   *
   */

  public Object doQuery(VOPackage vo) throws BaseException
  {
    //ȡ��form�еĶ���
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //UserData������ȡ
    UserData ud = (UserData) vo.getUserData();
    //�Ǽǻ�����Ϣ
    SWDJJBSJ djxx = null;
    try
    {
      //�Ǽǻ�����Ϣ
      /* start added by huxiaofeng 2005.8.16*/
      //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
      djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
      form.setNsrzt(djxx.getNsrzt());
      /* end added by huxiaofeng 2005.8.16*/

      //ȡ�����ش���
      form.setQxdm(InterfaceDj.getQxdm(ud));
      System.out.println(InterfaceDj.getQxdm(ud));
    }
    catch (Exception ex1)
    {
      //���쳣����������ҳ����ʾ
      List dataList = QysdsjbHelper.getShowList();
      form.setDataList(dataList);
      throw ExceptionUtil.getBaseException(ex1);
    }
    Connection conn = null;
    //ϵͳ��ǰʱ�䣬Ϊ����ԭ�����ݿ����޼�¼��ʱ��׼��
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
    try
    {
      //���ݿ�����
      conn = SfDBResource.getConnection();
      //ʹ��OrMap�������ݿ�ķ�װ��
      SfDBAccess da = new SfDBAccess(conn);
      //��ѯ��������
      Vector vZb = new Vector();

      //����OrMappingֵ�����ÿ������
      String zbNames[] =
          {
          "jkpzh", "qxdm", "lrrq",
          "fsdm", "jd", "jsjdm", "lrr", "nd", "sbrq",
          "skssjsrq", "skssksrq", "swjgzzjgdm"};
      //�걨ʱ��
//      Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(form.getSbrq()));
      //����
//      String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"��jd��"+jd);
//      String nd = this.getNd(jd, getsbnd, form.getSbrq());
//		��ҳ����ȡ��˰�������ں����
		String nd = form.getSkssksrq().substring(0, 4);

      //�ӵǼ���Ϣ����ȡ�������ݵ�form��
      BeanUtil.copyBeanToBean(zbNames, djxx, form);
      form.setNsrmc(djxx.getNsrmc());
      
//      java.util.Date time = SfDateUtil.getDate(form.getSbrq());
//
//      Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(form.
//          getSkssksrq(), "yyyyMMdd").getTime());
//      Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(form.
//          getSkssjsrq(), "yyyyMMdd").getTime());
//      System.out.println("skssksrq = " + skssksrq);
//      System.out.println("skssjsrq = " + skssjsrq);
      
		//���걨ҳ��ȡ���걨���ں�˰����������
		Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());
      Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());
		
		
		System.out.println(form.getJsjdm()+"sbrq��"+sbrq);
		System.out.println(form.getJsjdm()+"skssksrq��"+skssksrq);
		System.out.println(form.getJsjdm()+"skssjsrq��"+skssjsrq);
      
      
      //˰�ѽӿ�
      ServiceProxy proxy = new ServiceProxy();
      //��ҵ��Ϣ�ӿ�
//      QysdsSet sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq,
//                                            skssksrq,
//                                            skssjsrq,CodeConstant.SFGL_QYSDS_BBFS_JB);
      QysdsSet sdsInfo = null;
      if("4".equals(jd)){
			/*���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ*/
    	  sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_NB);
		}else{
			sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_JB);
		}
      
      
      //������ҵ����----�˹������޸ģ�������ҵ�������� Ҫ�����շ�ʽƽ�ȴ�����û���໥��Լ��ϵ��zhu guanglin
      System.out.println("������ҵ" + sdsInfo.getGxjsqy());
      if (sdsInfo.getGxjsqy() != null)
      {
        //���ݸ�����ҵ��˰��0.15
        form.setZssl(CodeConstant.GAOXINQIYESL);
        //���ݸ�����ҵ�ķ�ʽ������ҳ��js���ּ���
        StringBuffer proStr = new StringBuffer(form.getPromptStr());
        proStr.append("<li>����ҵ���и��¼�����ҵ�϶���");
        form.setPromptStr(proStr.toString());

      }
      else
      {
        form.setZssl("");
      }

      //��ҵ���շ�ʽ����
      //����ҵ���շ�ʽ
      if (sdsInfo.getZsfs() != null
          && sdsInfo.getZsfs().getZsfsdm() != "")
      {
        System.out.println("��ҵ���շ�ʽ����" + sdsInfo.getZsfs().getZsfsdm());
        System.out.println("�����ʼ��");
        //�����ʷ�ʽ
        if (sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
                                                 ZSFSDM_CYLZS))
        {
          form.setZsfs(sdsInfo.getZsfs().getZsfsdm());
          System.out.println("��������ֵΪ" + sdsInfo.getZsfs().getCyl());
          form.setCyl(sdsInfo.getZsfs().getCyl());
          StringBuffer proStr = new StringBuffer(form.getPromptStr());
          proStr.append("<li>���������շ�ʽ��������Ϊ" + form.getCyl() + "��");
          form.setPromptStr(proStr.toString());

        }
        else
        {
          form.setCyl("");
        }
        //���ʽ
        System.out.println("������");
        if (sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
                                                 ZSFSDM_DEZS))
        {
          form.setZsfs(sdsInfo.getZsfs().getZsfsdm());
          System.out.println("������ֵΪ" + sdsInfo.getZsfs().getDe());
          form.setDe(sdsInfo.getZsfs().getDe());
          StringBuffer proStr = new StringBuffer(form.getPromptStr());
          proStr.append("<li>�������շ�ʽ����λ˰��Ϊ" + form.getDe() +
                        "������Ӧ������˰��д�6��=��λ˰�����������������������");
          form.setPromptStr(proStr.toString());
        }
        else
        {
          form.setDe("");
        }
        //�Ǵ����ʷ�ʽ���Ƕ��ʽ
        if ( (!sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
            ZSFSDM_CYLZS) &&
              !sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
            ZSFSDM_DEZS)))
        {
          form.setZsfs(CodeConstant.QITAQIYE);
        }
      }
      //����ҵ���շ�ʽ
      if (sdsInfo.getZsfs() == null
          || sdsInfo.getZsfs().getZsfsdm() == "")
      {
        //�Ǵ����ʷ�ʽ���Ƕ��ʽ
        form.setZsfs(CodeConstant.QITAQIYE);
      }
      //������ҵ�µ�������ҵҪ*1
      //2003 12.25 ����*0.9��
      if ( (sdsInfo.isXzqy()) )
      {
        form.setYhbl(CodeConstant.YHBL_QITA);
        form.setXzqyjm(CodeConstant.XZQYBS);
      }
      else
      {
        //�Ǽ�����ҵ�µ�������ҵҪ*1
        form.setYhbl(CodeConstant.YHBL_QITA);
        form.setXzqyjm("");
      }

      //������ҵ��ϢӦ�ô�ӿڵ���һ������
//      java.util.Date times = TinyTools.addMonth(0,
//                                                SfDateUtil.getDate(form.
//          getSbrq()));
//      QysdsSet sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), times,
//                                             skssksrq,
//                                             skssjsrq,CodeConstant.SFGL_QYSDS_BBFS_JB);
      
      
      QysdsSet sdsInfos = null;
      if("4".equals(jd)){
			/*���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ*/
    	  sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_NB);
		}else{
			sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_JB);
		}

      //��ѯ��������
      vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      vZb.add(" jd='" + jd + "'");
      vZb.add("nd='" + nd + "'");
      vZb.add(" jsjdm='" + form.getJsjdm() + "'");
      List zbData = da.query(Qysdsjd.class, vZb);
      if (zbData.size() <= 0)
      {
        //������
        if (djxx == null)
        {
          System.out.println("�˼�������벻���ڣ�");
          List dataList = QysdsjbHelper.getShowList();
          form.setDataList(dataList);
          throw new ApplicationException("�˼�������벻���ڣ�");
        }
        //�ӵǼǽӿ���ȡ������Ϣ��ȡ��ҳ����
        BeanUtil.copyBeanToBean(zbNames, djxx, form);
        form.setNsrmc(djxx.getNsrmc());
        form.setCjrq(TinyTools.Date2String(curTime,
                                           CodeConstant.DATETYPE));

        //�Ƿ�õ������ʸ�

        System.out.println("��ѯ��ʱ��һ����������" + sdsInfos.getYbjmsl());
        if (sdsInfos.getYbjmsl() != null)
        {
          //�õ�������
          if (! (sdsInfo.isXzqy()))
          {
            //����һ����˰��λ�������˰�϶������Ҹ���˰�˲�����������ҵ
            String jmsl = sdsInfos.getYbjmsl().toString();
            form.setJmsl(jmsl);
          }
        }

        //ҳ���ʼ������
        List dataList = QysdsjbHelper.getShowList();
        form.setDataList(dataList);
        System.out.println(dataList);
        return form;
      } //end if

      //���б�����걨����

      //���Ǽǲ�����Ϣ��ʾ��ǰ̨ҳ����
      BeanUtil.copyBeanToBean(zbNames, djxx, form);
      //�õ����ݿ�������
      Qysdsjd orZb = (Qysdsjd) zbData.get(0);
      //��ʾ��ǰ̨ҳ����
      BeanUtil.copyBeanToBean(zbNames, orZb, form);
      form.setCjrq(String.valueOf(orZb.getCjrq()));
      //��ϸ������������һ��
      //��ϸOrMappingֵ�����ÿ������
      String mxNames[] =
          {
          "srze", "lrze", "sl", "ynsdse", "qcwjsdse",
          "jmsdse", "cbyqndse", "sjyjsdsse", "bqyjsdse",
          "sjybsdse", "mbyqndks", "bkhlrze"};
      //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
      List mxMapData = new ArrayList();
      //��ô�����ϸֵ
      Qysdsjd orMx = (Qysdsjd) zbData.get(0);
      Map map = new HashMap();
      //��ֵ�����ֵ����Map
      BeanUtil.copyBeanToMap(mxNames, orMx, map);
      Object jmsdse = map.get("jmsdse");
      List dataList = QysdsjbHelper.getShowList();
      //����������ҳ����ʾ����
      //begin modify by shiyanfeng 20050203
      mxMapData = this.addData(map, dataList);
      //end modify by shiyanfeng 20050203
      form.setDataList(mxMapData);
      form.setCjrq(form.getCjrq());
      form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
      form.setNsrmc(djxx.getNsrmc());

      System.out.println("��ѯ��ʱ��һ����������" + sdsInfos.getYbjmsl());
      if (sdsInfos.getYbjmsl() != null)
      {
        //�õ�������
        if (! (sdsInfo.isXzqy() ))
        {
          //����һ����˰��λ�������˰�϶������Ҹ���˰�˲�����������ҵ
          String jmsl = sdsInfos.getYbjmsl().toString();
          form.setJmsl(jmsl);
        }
      }

      return form;
    }
    catch (Exception ex)
    {
      //�׳��쳣
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //�ͷ����ݿ�����
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
   * @param   vo ҵ�����
   * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  public Object doSave(VOPackage vo) throws BaseException
  {
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //�Ǽǻ�����Ϣ
    SWDJJBSJ djxx = null;
    UserData ud = (UserData) vo.getUserData();
    try
    {
      /* start added by huxiaofeng 2005.8.16*/
      //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
      djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
      /* end added by huxiaofeng 2005.8.16*/

    }
    catch (Exception ex1)
    {
      throw ExceptionUtil.getBaseException(ex1);
    }
    Connection conn = null;
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
//    Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(form.
//        getSkssksrq(), "yyyyMMdd").getTime());
//    Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(form.
//        getSkssjsrq(), "yyyyMMdd").getTime());
    
//  ���걨ҳ��ȡ���걨���ں�˰����������
	Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());
  Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
	Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());
	
	
	System.out.println(form.getJsjdm()+"sbrq��"+sbrq);
	System.out.println(form.getJsjdm()+"skssksrq��"+skssksrq);
	System.out.println(form.getJsjdm()+"skssjsrq��"+skssjsrq);
	
    try
    {
      //���ݿ�����
      conn = SfDBResource.getConnection();
      //ʹ��OrMap�������ݿ�ķ�װ��
      SfDBAccess da = new SfDBAccess(conn);
      //��ѯ��������
//      Map getsbjd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbjd = this.quarterSkssrq(SfDateUtil.getDate(form.getSbrq()));
      //����
//      String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
//      String jd = Skssrq.preQuarter(TinyTools.addDay(1,SfDateUtil.getDate(form
//				.getSkssjsrq())));
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"��jd��"+jd);
      //���
//      String nd = this.getNd(jd, getsbjd, form.getSbrq());
//	��ҳ����ȡ��˰�������ں����
	String nd = form.getSkssksrq().substring(0, 4);
  System.out.println("nd =" + nd);
      String SQL = " jsjdm='" + form.getJsjdm() + "' and nd='" + nd +
          "' and jd='" + jd + "'";
      //ɾ��ԭ�ȵļ�¼
      try
      {
        //ɾ������
        da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) + "' and nd='"
                  + nd +
                  "' and jd='" + jd + "'" + "and jsjdm='" +
                  form.getJsjdm() + "'",
                  new Qysdsjd());
      }
      catch (BaseException ex1)
      {
        throw new ApplicationException(" ���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
      }
      String zbNames[] =
          {
          "jkpzh",
          "jsjdm", "sbrq",
          "skssjsrq", "skssksrq"};
      List mxMapData = form.getDataList();
      //������ϸ����ֵ����
      List mxData = new ArrayList();
      Map map = (Map) mxMapData.get(0);
      //�������������ӽ���
      //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
      BeanUtil.copyBeanToMap(zbNames, form, map);
      Qysdsjd orMx = new Qysdsjd();
      //�����ݴ��ݸ���ϸֵ����
      BeanUtil.populate(orMx, map);
      //�������ݲ���ֵ������
      Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                                         CodeConstant.DATETYPE);
      Timestamp cjrqmx = new Timestamp(cjrq.getTime());
      orMx.setCjrq(cjrqmx);
      orMx.setLrrq(curTime);
      orMx.setJd(jd);
      orMx.setNd(nd);
      orMx.setFsdm(CodeConstant.FSDM_SMSB);
      orMx.setQxdm(InterfaceDj.getQxdm(ud));
      orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
      orMx.setLrr(ud.getYhid());
      String jmje = orMx.getJmsdse().toString();
      mxData.add(orMx);
      try
      {
        //������ϸ������
        da.insert(mxData);
      }
      catch (BaseException ex4)
      {
        throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
      }
      //���ò���һ����ⷽ������ڲ���
      Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(form.
          getCjrq(),
          CodeConstant.DATETYPE).getTime());
      Timestamp ts_lrrq = new Timestamp(curTime.getTime());
      Timestamp ts_sbrq = new Timestamp(TinyTools.stringToDate(form.
          getSbrq(),
          "yyyyMMdd").getTime());
      Timestamp ts_Skssksrq = skssksrq;
      Timestamp ts_Skssjsrq = skssjsrq;

      //˰�ѽӿ��ٴμ���Ƿ�߱�һ������ʸ�
      //�ӿڲ��������ڵ������Ƿ��ڼ�����ֹ������

      //��һ������������걨����
      this.putJm(jmje, form,
                 da, ud,
                 djxx, ts_cjrq,
                 ts_lrrq, ts_sbrq,
                 ts_Skssjsrq,
                 ts_Skssksrq, nd);

      return null;
    }
    catch (Exception ex)
    {
      //�׳��쳣
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //�ͷ����ݿ�����
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
   * @param    vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */
  public Object doDelete(VOPackage vo) throws BaseException
  {
    //�õ�Action���ݹ���ActionForm����
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    UserData ud = (UserData) vo.getUserData();
    //�������ݿ�����
    Connection conn = null;
    try
    {
      conn = SfDBResource.getConnection();
      //ʹ��OrMap�������ݿ�ķ�װ��
      SfDBAccess da = new SfDBAccess(conn);
//      Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbnd = this.quarterSkssrq(TinyTools.addDay(1,SfDateUtil.getDate(form
//				.getSkssjsrq())));
      //����
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"��jd��"+jd);
      //���
//      String nd = this.getNd(jd, getsbnd, form.getSbrq());
//		��ҳ����ȡ��˰�������ں����
		String nd = form.getSkssksrq().substring(0, 4);
      System.out.println("nd =" + nd);

      //ɾ��ԭ�ȵļ�¼
      try
      {
        //ɾ����ϸ����
        da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) + "' and nd='"
                  + nd +
                  "' and jd='" + jd + "'" + "and jsjdm='" +
                  form.getJsjdm() + "'",
                  new Qysdsjd());
        //��¼ɾ����־
        TinyTools.makeLog4Qysds(ud, form.getJsjdm(), "��ҵ����˰����");
      }
      catch (BaseException ex1)
      {
        throw new ApplicationException("��ϸ���ݿ���ִ������ҹ���Ա��");
      }
      return null;
    }
    catch (Exception ex)
    {
      //�׳��쳣
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //�ͷ����ݿ�����
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * ���ݼ������������ȵķ���
   * @param jd ����
   * @param getsbnd �걨���
   * @param sbrq �걨����
   * @return ���
   */
  private String getNd(String jd, Map getsbnd, String sbrq)
  {
    String nd;
    int year;
    if (jd.equals("4"))
    {
      nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
      System.out.println("nds =" + nd);
    }
    else
    {
      nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
    }
    System.out.println("nd =" + nd);
    return nd;
  }

  /**
   * �����걨����ȡ�õ�ǰǰ��0101-1231
   * @param curSbrq �걨����
   * @return dateMap
   */
  private Map getSbrl(String curSbrq)
  {
    Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(sbrq);
    calendar.add(calendar.MONTH, -1);
    int month = calendar.get(calendar.MONTH);
    int year = calendar.get(calendar.YEAR);
    String nd = new Integer(year).toString();
    int maxDate = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    Timestamp ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).
                                   getTime().getTime());
    Timestamp jsrq = new Timestamp(new GregorianCalendar(year,
        month, maxDate).getTime().getTime());
    Map dateMap = new HashMap();
    dateMap.put("ksrq", ksrq);
    dateMap.put("jsrq", jsrq);
    return dateMap;
  }

  /**
   * �������
   * @param jmje ������
   * @param form �걨��Ϣ
   * @param dbAgent ���ݿ�����
   * @param ud ����Ա��Ϣ
   * @param djsj �Ǽ���Ϣ
   * @param ts_cjrq ��������
   * @param ts_lrrq ¼������
   * @param ts_sbrq �걨����
   * @param ts_Skssjsrq ˰��������������
   * @param ts_Skssksrq ˰��������ʼ����
   * @param nd ���
   */
  private void putJm(String jmje, QysdsjbForm form,
                     SfDBAccess dbAgent, UserData ud,
                     SWDJJBSJ djsj, Timestamp ts_cjrq,
                     Timestamp ts_lrrq, Timestamp ts_sbrq,
                     Timestamp ts_Skssjsrq,
                     Timestamp ts_Skssksrq, String nd)
  {
    try
    {
//����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
//�����޸ģ�
//����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
//����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
//���������û�в�������ݣ������һ�����ݣ�
//
//�����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��
      //��ҳ�����ݵļ������Ϊ�ջ�0���Ҿ߱�һ������ʸ�Ľ���
      if (!"0".equals(jmje) && !"".equals(jmje) && form.getJmsl() != null &&
          !form.getJmsl().equals(""))
      {

        Jm jm = new Jm();
        //����ʱ�����
        Map dateMap = getSbrl(form.getSbrq());
        Vector vZb = new Vector();
        //��ѯ����
        vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
        vZb.add("SKSSKSRQ = to_date('" +
                String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        vZb.add("SKSSJSRQ = to_date('" +
                String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        //����jzbz�����жϣ�ֻ��û�м������ݵ�ʱ��Ų����µ����ݣ�����м��������ڸ���jzbz���д���
//                vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
//                        + "'");
        vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB +
                "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') ");
        vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
        vZb.add("jsjdm='" + form.getJsjdm() + "' ");
        List zbData = dbAgent.query(Jm.class, vZb);
        if (zbData.size() <= 0)
        {
          try
          {
            //ɾ����������

            dbAgent.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                           "' and SKSSKSRQ = to_date('" +
                           String.valueOf(dateMap.get("ksrq")).
                           substring(0, 10) +
                           "','yyyy-MM-dd') and SKSSJSRQ = to_date('" +
                           String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                           "','yyyy-MM-dd') " + "and jzbz like '" +
                           CodeConstant.SMSB_JZBZ_EDIT + "%" + "'" +
                           "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                           "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                           "and jsjdm='" +
                           form.getJsjdm() + "'"
                           ,
                           new Jm());

            System.out.println("�������ݿ�ɾ��ԭ������");
          }
          catch (BaseException ex1)
          {
            throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
          }
          //ȡ��Ԥ�㼶�δ���
          Ysjc ysjc = null;
          try
          {
            ysjc = JksUtil.getYsjc(form.getJsjdm(),
                                   CodeConstant.SZSM_QYSDS,
                                   SfDateUtil.getDate(form.getSbrq()));
          }
          catch (Exception e)
          {
            throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
          }
          if (ysjc != null)
          {
            System.out.println("���� =" + ysjc.getYsjcdm());
          }
          else
          {
            throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
          }
          //ȡ��Ԥ���Ŀ����
          com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
          try
          {
            yskm = JKAdapter.getInstance().getYskm(CodeConstant.
                SZSM_QYSDS,
                djsj.getDjzclxdm(),
                djsj.getGjbzhydm(),
                ysjc.getYsjcdm());
          }
          catch (Exception e)
          {
            throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
          }
          if (yskm != null)
          {
            System.out.println("Ԥ���Ŀ =" + yskm.getYskmdm());
          }
          else
          {
            throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
          }
          Date date = TinyTools.stringToDate(form.getSbrq(),
                                             "yyyyMMdd");
          //����������
          ServiceProxy proxy = new ServiceProxy();
          String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
                                         CodeConstant.SZSM_QYSDS,
                                         ts_Skssksrq, ts_Skssjsrq);
          System.out.println("���������� =" + jmxmdm);
          //����ֵ�������
          jm.setCjrq(ts_cjrq);
          jm.setJsjdm(form.getJsjdm());
          jm.setJmlx(CodeConstant.JMLX_SP);
          jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
          jm.setSbrq(ts_sbrq);
          jm.setLrrq(ts_lrrq);
          jm.setFsdm(CodeConstant.FSDM_SMSB);
          jm.setJzbz(CodeConstant.SMSB_JZBZ);
          jm.setJmse(new BigDecimal(jmje));
          jm.setJsje(new BigDecimal(jmje));
          jm.setJmxmdm(jmxmdm);
          jm.setLrr(ud.getYhid());
          jm.setSkssjsrq(ts_Skssjsrq);
          jm.setSkssksrq(ts_Skssksrq);
          jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
          jm.setQxdm(InterfaceDj.getQxdm(ud));
          jm.setDjzclxdm(djsj.getDjzclxdm());
          jm.setGjbzhydm(djsj.getGjbzhydm());
          jm.setNd(nd);
          jm.setYsjcdm(ysjc.getYsjcdm());
          jm.setYskmdm(yskm.getYskmdm());
          try
          {
            //������������
            dbAgent.insert(jm);
          }
          catch (BaseException ex4)
          {
            throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
          }
        }
        else
        {
          //����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
          Jm jmTemp = (Jm) zbData.get(0);
          if (jmTemp.getJzbz().substring(0,
                                         1).equals(CodeConstant.SMSB_JZBZ_EDIT))
          {
            //δ���ˣ������jmse
            jmTemp.setJmse(new BigDecimal(jmje));
            dbAgent.update(jmTemp);
          }

        }

      }

    }
    catch (Exception ex)
    {
      //�׳��쳣
      ex.printStackTrace();
    }
  }

  /**
   * ������û��Ѿ��иü����걨���ݣ�����Ҫ������Ӧ�������б�
   * ������ҳ����ʾ���ݰ����д���Ϣ����Ŀ���ơ������ۼ���
   * @param map �Ѿ���������
   * @param dataList ҳ����ʾ��Ŀ�б�
   * @return �������걨�����б�
   */
  private List addData(Map map, List dataList)
  {
    List mxMapData = new ArrayList();
    /* begin of 1 */
    Object srze = map.get("srze");
    if (srze == null)
    {
      srze = "0";
    }
    Map srzes = (Map) dataList.get(0);
    Map map1 = new HashMap();
    map1.putAll(srzes);
    map1.put("srze", srze);
    mxMapData.add(map1);
    /* end of 1  */
    /* begin of 2 */
    Object lrze = map.get("lrze");
    if (lrze == null)
    {
      lrze = "0";
    }
    Map lrzes = (Map) dataList.get(1);
    Map map2 = new HashMap();
    map2.putAll(lrzes);
    map2.put("lrze", lrze);
    mxMapData.add(map2);
    /* end of 2  */

    //begin of modified by shiyanfeng
    /* begin of 3 */
    Object mbyqndks = map.get("mbyqndks");
    if (mbyqndks == null)
    {
      mbyqndks = "0";
    }
    Map mbyqndkss = (Map) dataList.get(2);
    Map map11 = new HashMap();
    map11.putAll(mbyqndkss);
    map11.put("mbyqndks", mbyqndks);
    mxMapData.add(map11);
    /* end of 3  */
    /* begin of 4 */
    Object bkhlrze = map.get("bkhlrze");
    if (bkhlrze == null)
    {
      bkhlrze = "0";
    }
    Map bkhlrzes = (Map) dataList.get(3);
    Map map12 = new HashMap();
    map12.putAll(bkhlrzes);
    map12.put("bkhlrze", bkhlrze);
    mxMapData.add(map12);
    /* end of 4  */

    //end of modified by shiyanfeng

    /* begin of 5 */
    Object sl = map.get("sl");
    if (sl == null)
    {
      sl = "";
    }
    Map sls = (Map) dataList.get(4);
    Map map3 = new HashMap();
    map3.putAll(sls);
    map3.put("sl", sl);
    mxMapData.add(map3);
    /* end of 5  */

    /* begin of 6 */
    Object ynsdse = map.get("ynsdse");
    if (ynsdse == null)
    {
      ynsdse = "0";
    }
    Map ynsdses = (Map) dataList.get(5);
    Map map4 = new HashMap();
    map4.putAll(ynsdses);
    map4.put("ynsdse", ynsdse);
    mxMapData.add(map4);
    /* end of 6  */
    /* begin of 7 */
    Object qcwjsdse = map.get("qcwjsdse");
    if (qcwjsdse == null)
    {
      qcwjsdse = "0";
    }
    Map qcwjsdses = (Map) dataList.get(6);
    Map map5 = new HashMap();
    map5.putAll(qcwjsdses);
    map5.put("qcwjsdse", qcwjsdse);
    mxMapData.add(map5);
    /* end of 7  */
    /* begin of 8 */
    Object jmsdse = map.get("jmsdse");
    if (jmsdse == null)
    {
      jmsdse = "0";
    }
    Map jmsdses = (Map) dataList.get(7);
    Map map6 = new HashMap();
    map6.putAll(jmsdses);
    map6.put("jmsdse", jmsdse);
    mxMapData.add(map6);
    /* end of 8  */
    /* begin of 9 */
    Object cbyqndse = map.get("cbyqndse");
    if (cbyqndse == null)
    {
      cbyqndse = "0";
    }
    Map cbyqndses = (Map) dataList.get(8);
    Map map7 = new HashMap();
    map7.putAll(cbyqndses);
    map7.put("cbyqndse", cbyqndse);
    mxMapData.add(map7);
    /* end of 9  */
    /* begin of 10 */
    Object sjyjsdsse = map.get("sjyjsdsse");
    if (sjyjsdsse == null)
    {
      sjyjsdsse = "0";
    }
    Map sjyjsdsses = (Map) dataList.get(9);
    Map map8 = new HashMap();
    map8.putAll(sjyjsdsses);
    map8.put("sjyjsdsse", sjyjsdsse);
    mxMapData.add(map8);
    /* end of 10  */
    /* begin of 11 */
    Object bqyjsdse = map.get("bqyjsdse");
    if (bqyjsdse == null)
    {
      bqyjsdse = "0";
    }
    Map bqyjsdses = (Map) dataList.get(10);
    Map map9 = new HashMap();
    map9.putAll(bqyjsdses);
    map9.put("bqyjsdse", bqyjsdse);
    mxMapData.add(map9);
    /* end of 11  */
    /* begin of 12 */
    Object sjybsdse = map.get("sjybsdse");
    if (sjybsdse == null)
    {
      sjybsdse = "0";
    }
    Map sjybsdses = (Map) dataList.get(11);
    Map map10 = new HashMap();
    map10.putAll(sjybsdses);
    map10.put("sjybsdse", sjybsdse);
    mxMapData.add(map10);

    return mxMapData;
  }

  /**
    * ���㼾�����͵�˰����������
    * @param curDate ����
    * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
    *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
    *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
    */
   public  Map quarterSkssrq (Date curDate)
   {
       GregorianCalendar calendar = new GregorianCalendar();
       calendar.setTime(curDate);
       int month = calendar.get(calendar.MONTH);
       int year = calendar.get(calendar.YEAR);

       int jd = month / 3 ;
       if (jd==0) {
         year--;
         jd=4;
       }
       String nd = new Integer(year).toString();
       Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
           0, 1).getTime().getTime());
       Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
           (jd - 1) * 3 + 2,
           new GregorianCalendar(year, (jd - 1) * 3 + 2,
                                 1).getActualMaximum(calendar.
           DAY_OF_MONTH)
           ).getTime().getTime());
       Map retMap = new HashMap();
       retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
       retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
       retMap.put(Skssrq.SKSSRQ_ND, nd);
       return retMap;
   }
//   public static void main(String[] args) {
//       QysdsjbProcessor t = new QysdsjbProcessor();
//       System.out.println(t.quarterSkssrq(SfDateUtil.getDate("20051001")));
//     }

}
