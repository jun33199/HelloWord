/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor;

import java.math.BigDecimal; //import com.ekernel.db.or.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ttsoft.bjtax.shenbao.model.domain.Lygf;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsnb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
//import com.ttsoft.bjtax.smsb.model.client.Yskm;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
//import com.ekernel.db.or.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsnbProcessor
    implements Processor {
  /**
   * ��ҵ����˰˰��
   */
  private final String QYSDS_SL = "0.33";

  /**
   * ��ҵ����˰˰��
   */
  private final String QYSDS_SZ = "30";

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   * 		1 ���������Ĳ������Ͳ���ʱ�׳�
   * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
   * 	�����쳣�׳���EJB��process��������
   */

  public Object process(VOPackage vo) throws BaseException {

    Object result = null;
    if (vo == null) {
      throw new NullPointerException();
    }

    switch (vo.getAction()) {
      case CodeConstant.SMSB_SHOWACTION:
        result = doShow(vo);
        break;
      case CodeConstant.SMSB_QUERYACTION:
        result = doQuery(vo);
        break;
      case CodeConstant.SMSB_SAVEACTION:
        result = doSave(vo);
        break;
      case CodeConstant.SMSB_DELETEACTION:
        result = doDelete(vo);
        break;
      case CodeConstant.SMSB_UPDATEACTION:
        result = doUpdate(vo);
        break;
      default:
        throw new ApplicationException(
            "�û�ִ����ϵͳ��֧�ֵķ�������.");
    }

    return result;
  }

  /**
   * doShow��ʼ������ҳ����ϢҪ��
   * @param vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  private Object doShow(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    // ��ʼ��FORM�������걨���ڡ��걨�ڼ�
    initForm(nbForm);

    return nbForm;
  }

  /**
   * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   *
   */

  private Object doQuery(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();
    nbForm.setNsrmc(""); // ��˰������
    nbForm.setZcdzlxdh(""); // ע���ַ��ϵ�绰

    Connection conn = null;
    SfDBAccess dbAgent = null;

    try {
      conn = SfDBResource.getConnection();
      dbAgent = new SfDBAccess(conn);

      UserData ud = (UserData) vo.getUserData();
      SWDJJBSJ djsj = null;
      try {
        // �����ҵ�Ǽǻ�����Ϣ
        /* start added by huxiaofeng 2005.8.16*/
        //djsj = InterfaceDj.getJBSJ(nbForm.getJsjdm(), ud);
        djsj = InterfaceDj.getJBSJ_New(nbForm.getJsjdm(), ud);
        nbForm.setNsrzt(djsj.getNsrzt());
        /* end added by huxiaofeng 2005.8.16*/

      }
      catch (Exception ex1) {
        throw ExceptionUtil.getBaseException(ex1);
      }

      nbForm.setQxdm(InterfaceDj.getQxdm(ud));
      nbForm.setNsrmc(djsj.getNsrmc()); // ��˰������
      nbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
      nbForm.setDjzclxdm(djsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
      nbForm.setSwjgzzjgdm2(djsj.getSwjgzzjgdm()); // ˰�������֯��������
      nbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // ˰��������
      nbForm.setFsdm(CodeConstant.FSDM_SMSB); // �����걨��ʽ
      nbForm.setNd(getSbnd(nbForm.getSbrq()));

//Update  Start  Zhou kejing 20031212
//      java.util.Date time = TinyTools.addYear( -1, SfDateUtil.getDate(nbForm.getSbrq()));
      java.util.Date time = SfDateUtil.getDate(nbForm.getSbrq());
//Update  End    Zhou kejing 20031212
      // ȡ�ø���ҵ����˰��ص���Ϣ
      nbForm.setZsfsdm("");
      nbForm.setGxjsqy(false);
      nbForm.setZssl(QYSDS_SL);

      ServiceProxy proxy = new ServiceProxy();
//Update  Start  Zhou kejing 20031212
//      QysdsSet sdsInfo = proxy.getQysdsInfo(nbForm.getJsjdm(), time);
      Debug.out("˰����ڲ��������������=" + nbForm.getJsjdm());
      Debug.out("˰����ڲ������걨����=" + time);
      Debug.out("˰����ڲ�����˰��������ʼ����="
                + SfDateUtil.getDate(nbForm.getSkssksrq()));
      Debug.out("˰����ڲ�����˰��������������="
                + SfDateUtil.getDate(nbForm.getSkssjsrq()));
      //modified by hazhengze 20051221 start
      QysdsSet sdsInfo = proxy.getQysdsInfo(nbForm.getJsjdm(), time,
                                            SfDateUtil.getDate(nbForm.
          getSkssksrq()),
                                            SfDateUtil.getDate(nbForm.
          getSkssjsrq()), CodeConstant.SFGL_QYSDS_BBFS_NB);
      //modified by hazhengze 20051221 start

//Update  End    Zhou kejing 20031212
      if (sdsInfo.getZsfs() != null) {
        nbForm.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // ���շ�ʽ
//Insert  Start  Zhou kejing 20040113
        nbForm.setZsfsdm2(sdsInfo.getZsfs().getZsfsdm()); // ���շ�ʽ
//Insert  End    Zhou kejing 20040113
        //modified by hazhengze 20051227 Start
        nbForm.setZssl(sdsInfo.getZsfs().getSl());
          System.out.println("================��ȡ��ҵ��˰˰��1==================" +
                             sdsInfo.getZsfs().getSl());
        //modified by hazhengze 20051227 End
        if (nbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
          System.out.println("================��ȡ��ҵ�����ʿ�ʼ==================" +
                             nbForm.getJsjdm());
          if (sdsInfo.getZsfs() != null) {
            System.out.println(nbForm.getJsjdm() + "�ĺ˶�������Ϊ" +
                               sdsInfo.getZsfs().getCyl());
            nbForm.setCyl(sdsInfo.getZsfs().getCyl());
          }
          System.out.println("================��ȡ��ҵ�����ʽ���==================" +
                             nbForm.getJsjdm());
          // ����������
          nbForm.setZsfsdm(CodeConstant.ZSFSDM_CYLZS);

          //modified by hazhengze 20051227 Start
          nbForm.setZssl(QYSDS_SL);
          System.out.println("================��ȡ��ҵ��˰˰��2==================" +
                             sdsInfo.getZsfs().getSl());
          // nbForm.setZssl(sdsInfo.getZsfs().getCyl());
          //modified by hazhengze 20051227 End
//Insert  Start  Zhou kejing 20040113
          nbForm.setZssl2(sdsInfo.getZsfs().getCyl());
//Insert  End    Zhou kejing 20040113
          Debug.out("CYL= " + nbForm.getZssl());
        }
        else if (nbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)) { // ��������
          nbForm.setDe(sdsInfo.getZsfs().getDe());
        }
        Debug.out("ZSSL= " + nbForm.getZssl());
        if (nbForm.getZssl() == null) {
          nbForm.setZssl("0.00");
        }
      }

      Debug.out("�Ʋ���ʧ�۳���" + sdsInfo.getCcss()); //�Ʋ���ʧ�۳���
      Debug.out("�ֲ���ǰ��ȿ���" + sdsInfo.getNbyqndks());
      Debug.out("���µֿ�" + sdsInfo.getSxdk());
      Debug.out("������������豸Ͷ�ʵ���˰��" + sdsInfo.getJsgzgcsb());
      Debug.out("����������˰��" + sdsInfo.getYbjme());
      Debug.out("��˰�ļ���ת������" + sdsInfo.getMsdjxzrsy());
      if ( (sdsInfo.isXzqy()) &&
//Update  End    Zhou kejing 20031212
          (djsj.getDjzclxdm().equals(CodeConstant.JITIQIYE_CODE))) {
        Debug.out("������ҵ�µ�������ҵ");
        nbForm.setYhbl(CodeConstant.YHBL_QITA);
        nbForm.setXzqyjm(CodeConstant.JM_XIANZHEN);
        nbForm.SetXzqy(true);
      }
      else {
        Debug.out("������ҵ");
        nbForm.setYhbl(CodeConstant.YHBL_QITA);
      }

      // ��ѯ����ҵ�Ƿ��Ǹ��¼�����ҵ
      if (sdsInfo.getGxjsqy() != null) {

        // ���ø���ҵΪ���¼�����ҵ
        nbForm.setZsfsdm("");
        nbForm.setGxjsqy(true);
        nbForm.setZssl(CodeConstant.GAOXINQIYESL);

      }

      // �����Ƿ����е�������˰�걨����
      String zb_columns[] = {
          "fsdm", "sbrq", "skssksrq", "skssjsrq"};
      String mx_columns[] = {
          "hc", "bqljs"};
      List mxResult = new ArrayList();

      Vector vZb = new Vector();
      vZb.add(" ND='" + nbForm.getNd() + "'" +
              " and QXDM='" + nbForm.getQxdm() + "'" +
              " and JSJDM='" + nbForm.getJsjdm() + "'" +
              " order by  to_number(HC) ASC ");
      List mxData = dbAgent.query(
          com.ttsoft.bjtax.shenbao.model.domain.Qysdsnb.class, vZb);
      if (mxData.size() > 0) {
        Qysdsnb orData = (Qysdsnb) mxData.get(0);
        HashMap zbMap = new HashMap();
        BeanUtil.copyBeanToMap(zb_columns, orData, zbMap);
        nbForm.setFsdm( (String) zbMap.get("fsdm"));
        nbForm.setSbrq( (String) zbMap.get("sbrq"));
        nbForm.setSkssksrq( (String) zbMap.get("skssksrq"));
        nbForm.setSkssjsrq( (String) zbMap.get("skssjsrq"));
        nbForm.setLrrq(TinyTools.Date2String(orData.getLrrq(),
                                             CodeConstant.DATETYPE));
        nbForm.setCjrq(TinyTools.Date2String(orData.getCjrq(),
                                             CodeConstant.DATETYPE));

        Iterator it = mxData.iterator();
        while (it.hasNext()) {
          Qysdsnb item = (Qysdsnb) it.next();
          HashMap record = new HashMap();
          BeanUtil.copyBeanToMap(mx_columns, item, record);
          mxResult.add(record);
        }
        nbForm.setDataList(mxResult);
        Debug.out("��ϸ����" + nbForm.getDataList());
        // �鿴�Ƿ����е�����Ӫ���ɷ�����
        String lg_columns[] = {
            "fl", "dwmc", "sl", "lr", "ynssde",
            "ynsdse", "sskce", "ybsdse"};
        List lgResult = new ArrayList();
        Vector vLg = new Vector();
        vLg.add(" JSJDM='" + nbForm.getJsjdm() + "' ");
        vLg.add(" QXDM='" + nbForm.getQxdm() + "' ");
        vLg.add(" ND='" + nbForm.getNd() + "' order by fl ");
        List lgData = dbAgent.query(
            com.ttsoft.bjtax.shenbao.model.domain.Lygf.class, vLg);
        Iterator lgItems = lgData.iterator();
        while (lgItems.hasNext()) {
          Lygf item = (Lygf) lgItems.next();
          HashMap record = new HashMap();
          BeanUtil.copyBeanToMap(lg_columns, item, record);
          lgResult.add(record);
        }

        nbForm.setLygfDataList(lgResult);
        Debug.out("��ϸ����" + nbForm.getLygfDataList());
      }
      else {

        nbForm.setSpzg24(sdsInfo.getCcss()); //�Ʋ���ʧ�۳���
//                nbForm.setSpzg63(sdsInfo.getNbyqndks()); //�ֲ���ǰ��ȿ���
//                nbForm.setSpzg77(sdsInfo.getSxdk()); //���µֿ�
        nbForm.setSpzg82(sdsInfo.getJsgzgcsb()); //������������豸Ͷ�ʵ���˰��
        nbForm.setSpzg81(sdsInfo.getYbjme()); //����������˰��
        nbForm.setSpzg69(sdsInfo.getMsdjxzrsy()); //��˰�ļ���ת������

      }
    }
    catch (Exception ex) {
      //�׳��쳣
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    Debug.out("���ݳ���" + nbForm.getDataList().size());
    System.out.println(nbForm.getJsjdm() + "��ҵ���շ�ʽΪ" + nbForm.getZsfsdm());
    System.out.println(nbForm.getJsjdm() + "��ҵ���շ�ʽ2Ϊ" + nbForm.getZsfsdm2());
    return nbForm;
  }

  /**
   * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
   * @param   vo ҵ�����
   * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  private Object doSave(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    Connection conn = null;
    SfDBAccess dbAgent = null;
    List nbResult = new ArrayList();

    try {
      conn = SfDBResource.getConnection();
      dbAgent = new SfDBAccess(conn);
      UserData ud = (UserData) vo.getUserData();
      ServiceProxy proxy = new ServiceProxy();
      SWDJJBSJ djsj = null;
      try {
        // �����ҵ�Ǽǻ�����Ϣ
        djsj = InterfaceDj.getJBSJ(nbForm.getJsjdm(), ud);
      }
      catch (Exception ex1) {
        ex1.printStackTrace();
      }
      if (djsj == null) {
        Debug.out("�˼�������벻���ڣ�");
        throw new ApplicationException("�˼�������벻���ڣ�");
      }
      nbForm.setNsrmc(djsj.getNsrmc());
      nbForm.setZcdzlxdh(djsj.getZcdzlxdh());
      nbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // ˰��������
      nbForm.setZgswjgzzjgdm(djsj.getSwjgzzjgdm());
      nbForm.setFsdm(CodeConstant.FSDM_SMSB); // �����걨��ʽ
      nbForm.setNd(getSbnd(nbForm.getSbrq()));
      nbForm.setLrrq(TinyTools.Date2String(new Date(),
                                           CodeConstant.DATETYPE));
      Debug.out("CJRQ=" + nbForm.getCjrq());
      if (nbForm.getCjrq() == null || "".equals(nbForm.getCjrq())) {
        nbForm.setCjrq(TinyTools.Date2String(new Date(),
                                             CodeConstant.DATETYPE));
      }

      Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(nbForm.
          getCjrq(),
          CodeConstant.DATETYPE).getTime());
      Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(nbForm.
          getLrrq(),
          CodeConstant.DATETYPE).getTime());
      Timestamp ts_sbrq = new Timestamp(TinyTools.stringToDate(nbForm.
          getSbrq(),
          "yyyyMMdd").getTime());
      Timestamp ts_Skssjsrq = new Timestamp(TinyTools.stringToDate(nbForm.
          getSkssjsrq(),
          "yyyyMMdd").getTime());
      Timestamp ts_Skssksrq = new Timestamp(TinyTools.stringToDate(nbForm.
          getSkssksrq(),
          "yyyyMMdd").getTime());
      if (nbForm.getSpzg81() != null) {

      }
      /**
       * modified by hazhengze 20060418 start ���Ӷ�sfdb.sf_jl_qysdszsfs_his��nsrzt���޸�
       */
      try {
          String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"+nbForm.getJsjdm()+"'";
          dbAgent.updateSQL(sql);
        }
        catch (BaseException ex1) {
          //�׳��쳣
          ex1.printStackTrace();
          new ApplicationException("���ݿ���¼�¼ʧ�ܣ�"+nbForm.getJsjdm()+":"+ex1.getMessage());
        }
      /**
       * modified by hazhengze 20060418 end
       */
      // ɾ��������ʷ����˰����
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        dbAgent.delete(strWhere, new Qysdsnb());
      }
      catch (BaseException ex1) {
        //�׳��쳣
        ex1.printStackTrace();
        new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
      }

      // 81�д�һ�������
      String jmje = "";

      // �����µ�����˰�걨����
      String mx_columns[] = {
          "qxdm", "nd", "jsjdm", "sbrq", "lrr",
          "skssksrq", "skssjsrq", "fsdm", "swjgzzjgdm",
          "zgswjgzzjgdm"};

      nbResult = nbForm.getDataList();
      for (int i = 0; i < nbResult.size(); i++) {
        HashMap map = (HashMap) nbResult.get(i);
        BeanUtil.copyBeanToMap(mx_columns, nbForm, map);

        String hc = (String) map.get("hc");
        if ("81".equals(hc)) {
          jmje = (String) map.get("bqljs");
        }

        Qysdsnb orNb = new Qysdsnb();
        BeanUtil.populate(orNb, map);
        orNb.setCjrq(ts_cjrq);
        orNb.setLrrq(ts_lrrq);
        dbAgent.insert(orNb);
      }

      // ɾ��������Ӫ���ɷ���ʷ����
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        dbAgent.delete(strWhere, new Lygf());
      }
      catch (BaseException ex1) {
        //�׳��쳣
        ex1.printStackTrace();
        new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
      }

      // ������Ӫ���ɷ�������
      String lgZb_columns[] = {
          "nd", "jsjdm", "sbrq", "lrr", "nsrmc",
          "qxdm", "skssksrq", "skssjsrq",
          "swjgzzjgdm", "fsdm"};

      List lgResult = nbForm.getLygfDataList();
      for (int i = 0; i < lgResult.size(); i++) {
        HashMap map = (HashMap) lgResult.get(i);
        BeanUtil.copyBeanToMap(lgZb_columns, nbForm, map);
        map.put("xh", String.valueOf(i)); // �������ҵ������ţ���
        map.put("qylxdh", nbForm.getZcdzlxdh());
        Lygf orLygf = new Lygf();
        BeanUtil.populate(orLygf, map);
        orLygf.setCjrq(ts_cjrq);
        orLygf.setLrrq(ts_lrrq);
        dbAgent.insert(orLygf);
      }
      //����
//Update  Start  Zhou kejing 20031212
      java.util.Date time = SfDateUtil.getDate(nbForm.getSbrq());
      QysdsSet sdsInfo = proxy.getQysdsInfo(nbForm.getJsjdm(), time,
                                            SfDateUtil.getDate(nbForm.
          getSkssksrq()),
                                            SfDateUtil.getDate(nbForm.
          getSkssjsrq()), CodeConstant.SFGL_QYSDS_BBFS_NB);
//Update  End    Zhou kejing 20031212
      Debug.out("�������������Ƿ�߱�һ������ʸ�" + sdsInfo.getYbjme());
      Debug.out("������" + jmje);
      //BigDecimal jmsp81 = proxy.getyb(nbForm.getJsjdm(), time, dbAgent);
      this.putJm(jmje, sdsInfo, nbForm,
                 dbAgent, ud, proxy,
                 djsj, ts_cjrq,
                 ts_lrrq, ts_sbrq,
                 ts_Skssjsrq,
                 ts_Skssksrq, time);

    }
    catch (Exception ex) {
      //�׳��쳣
      ex.printStackTrace();
      throw new ApplicationException(ex.getMessage());
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

    return nbForm;
  }

  /**
   * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
   * @param    vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  private Object doDelete(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    Connection conn = null;
    SfDBAccess dbAgent = null;
    UserData ud = (UserData) vo.getUserData();

    try {
      conn = SfDBResource.getConnection();
      dbAgent = new SfDBAccess(conn);

      nbForm.setNd(getSbnd(nbForm.getSbrq()));

      // ɾ��������ʷ����˰����
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        // ɾ����Ӫ���ɷ�����
        dbAgent.delete(strWhere, new Lygf());

        // ɾ����ҵ����˰�걨����
        dbAgent.delete(strWhere, new Qysdsnb());
        //��¼ɾ����־
        TinyTools.makeLog4Qysds(ud, nbForm.getJsjdm(), "��ҵ����˰�걨");
      }
      catch (BaseException ex1) {
        //�׳��쳣
        ex1.printStackTrace();
        new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
      }

      // �������ó�ʼ������
      initForm(nbForm);
      // ����Ѽ�������
      nbForm.getDataList().clear();
    }
    catch (Exception ex) {
      //�׳��쳣
      ex.printStackTrace();
      throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return nbForm;
  }

  /**
   * doUpdate  ���ڴ洢ҳ���ύ���꾡������Ϣ
   * @param    vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  private Object doUpdate(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    return nbForm;
  }

  /**
   * ��ʼ��
   * @param nbForm ��������
   * @throws BaseException ��������������׳��쳣��Ϣ
   */

  private void initForm(QysdsnbForm nbForm) throws BaseException {

    nbForm.setSbrq(SfDateUtil.getDate());
    nbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
    nbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
    //nbForm.setZsfsdm(CodeConstant.ZSFSDM_CZZS);
    nbForm.setZsfsdm("");
    nbForm.setZssl(QYSDS_SL);
    // �걨�ڼ�
    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(nbForm.getSbrq()));
    try {
      String ksrq = DateTimeUtil.timestampToString(
          (Timestamp) qj.get(Skssrq.SKSSKSRQ),
          DateTimeUtil.JAVA_DATEFORMAT);

      String jsrq = DateTimeUtil.timestampToString(
          (Timestamp) qj.get(Skssrq.SKSSJSRQ),
          DateTimeUtil.JAVA_DATEFORMAT);
      nbForm.setNd( (String) qj.get(Skssrq.SKSSRQ_ND));
      nbForm.setSkssksrq(ksrq);
      nbForm.setSkssjsrq(jsrq);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * �����걨���
   * @param   sbrq �걨����
   * @return String ���
   */

  private String getSbnd(String sbrq) {

    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
    return (String) qj.get(Skssrq.SKSSRQ_ND);
  }

  /**
   * �����걨����ȡ�õ�ǰǰ��0101-1231
   * @param curSbrq �걨����
   * @return dateMap
   */
  private Map getSbrl(String curSbrq) {
    Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(sbrq);
    calendar.add(calendar.YEAR, -1);
    int year = calendar.get(calendar.YEAR);
    String nd = new Integer(year).toString();
    Timestamp ksrq;
    Timestamp jsrq;
    Map retMap = new HashMap();
    ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime().
                         getTime());
    jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).
                         getTime().getTime());

    Map dateMap = new HashMap();
    dateMap.put("ksrq", ksrq);
    dateMap.put("jsrq", jsrq);
    return dateMap;
  }

  /**
   * �����������
   * @param jmje ������
   * @param sdsInfo ˰���϶���Ϣ
   * @param nbForm ¼������
   * @param dbAgent ���ݿ�����
   * @param ud ����Ա��Ϣ
   * @param proxy �ӿ�
   * @param djsj �Ǽǻ�����Ϣ
   * @param ts_cjrq ��������
   * @param ts_lrrq ¼������
   * @param ts_sbrq �걨����
   * @param ts_Skssjsrq ˰��������������
   * @param ts_Skssksrq ˰��������ʼ����
   * @param time ��ǰʱ��
   */
  private void putJm(String jmje, QysdsSet sdsInfo, QysdsnbForm nbForm,
                     SfDBAccess dbAgent, UserData ud, ServiceProxy proxy,
                     SWDJJBSJ djsj, Timestamp ts_cjrq,
                     Timestamp ts_lrrq, Timestamp ts_sbrq,
                     Timestamp ts_Skssjsrq,
                     Timestamp ts_Skssksrq, java.util.Date time) {
    try {
      if (!"".equals(jmje) && sdsInfo.getYbjme() != null) {
//����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
//�����޸ģ�
//����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
//����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
//���������û�в�������ݣ������һ�����ݣ�
//
//�����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��

        Debug.out("�������");
        Jm jm = new Jm();
        Map dateMap = getSbrl(nbForm.getSbrq());
        Vector vZb = new Vector();

        vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
        vZb.add("SKSSKSRQ = to_date('" +
                String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        vZb.add("SKSSJSRQ = to_date('" +
                String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB +
                "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') ");

        //����jzbz�����жϣ�ֻ��û�м������ݵ�ʱ��Ų����µ����ݣ�����м��������ڸ���jzbz���д���
//                vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
//                        + "'");

        vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
        vZb.add("jsjdm='" + nbForm.getJsjdm() + "' ");
        List zbData = dbAgent.query(Jm.class, vZb);
        Debug.out("�鵽������Ϊ" + zbData.size());
        if (zbData.size() <= 0) {
          //������û�в�������ݣ�����һ�����ݣ�
          try {
            //ɾ����ϸ����
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
                           nbForm.getJsjdm() + "'",
                           new Jm());

            Debug.out("�������ݿ�ɾ��ԭ������");
          }
          catch (BaseException ex1) {
            throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
          }
          Ysjc ysjc = null;
          try {
            ysjc = JksUtil.getYsjc(nbForm.getJsjdm(),
                                   CodeConstant.SZSM_QYSDS,
                                   SfDateUtil.getDate(nbForm.
                getSbrq()));
          }
          catch (Exception e) {
            throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
          }
          if (ysjc != null) {
            Debug.out("���� =" + ysjc.getYsjcdm());
          }
          else {
            throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
          }

          com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
          try {
            yskm = JKAdapter.getInstance().getYskm(CodeConstant.
                SZSM_QYSDS,
                djsj.getDjzclxdm(),
                djsj.getGjbzhydm(),
                ysjc.getYsjcdm());
          }
          catch (Exception e) {
            throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
          }
          if (yskm != null) {
            Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
          }
          else {
            throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
          }
          Date date = TinyTools.stringToDate(nbForm.getSbrq(),
                                             "yyyyMMdd");
          //����˰����ӿ�
//Update  Start  Zhou kejing 20031212
          String jmxmdm = proxy.getJmsbs(nbForm.getJsjdm(),
                                         CodeConstant.SZSM_QYSDS,
//                                         time);
                                         SfDateUtil.getDate(nbForm.getSkssksrq()),
                                         SfDateUtil.getDate(nbForm.getSkssjsrq()));
//Update  End    Zhou kejing 20031212
          Debug.out("���������� =" + jmxmdm);
          //����ֵ�������
          jm.setCjrq(ts_cjrq);
          jm.setJsjdm(nbForm.getJsjdm());
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
          jm.setNd(nbForm.getNd());
          jm.setYsjcdm(ysjc.getYsjcdm());
          jm.setYskmdm(yskm.getYskmdm());
          try {
            //������������
            dbAgent.insert(jm);
          }
          catch (BaseException ex4) {
            throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
          }
        }
        else {
          //����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
          Jm jmTemp = (Jm) zbData.get(0);
          if (jmTemp.getJzbz().substring(0,
              1).equals(CodeConstant.SMSB_JZBZ_EDIT)) {
            //δ���ˣ������jmse
            jmTemp.setJmse(new BigDecimal(jmje));
            dbAgent.update(jmTemp);
          }

        }
      }

    }
    catch (Exception ex) {
      //�׳��쳣
      ex.printStackTrace();
    }
  }
}
