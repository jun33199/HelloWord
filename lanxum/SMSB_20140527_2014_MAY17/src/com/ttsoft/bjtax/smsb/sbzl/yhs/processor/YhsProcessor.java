/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.yhs.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.yhs.web.YhsForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ӡ��˰�����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class YhsProcessor
    implements Processor {

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   * 		1 ���������Ĳ������Ͳ���ʱ�׳�
   * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
   * 	�����쳣�׳���EJB��process��������
   */
  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException {

    Object result = null;
    Debug.out("--Debug-- Info : Here is YhsProcessor.process(vo)");

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
        doSave(vo);
        break;
      case CodeConstant.SMSB_DELETEACTION:
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
  private Object doShow(VOPackage vo) throws BaseException {
    Connection conn = null;
    SfDBAccess da = null;
    YhsForm yhsForm = (YhsForm) vo.getData();

    initForm(yhsForm);

    try {
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);

      //ȡ˰��˰Ŀ����
      Vector condition = new Vector();
      condition.add(" szsmdm like '16%' ");
      condition.add(" szsmdm > '160092' ");
      List list = da.query(com.ttsoft.bjtax.shenbao.model.domain.Szsm.class,
                           condition);
      String[] columns = {
          "szsmdm", "szsmmc", "sl"};
      List mxMapData = new ArrayList();
      for (int i = 0; i < list.size(); i++) {
        //�����ϸֵ
        Szsm orMx = (Szsm) list.get(i);
        Map map = new HashMap();
        BeanUtil.copyBeanToMap(columns, orMx, map);
        mxMapData.add(map);
      }
      yhsForm.setDataList(mxMapData); //
      return yhsForm;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   *
   */
  private Object doQuery(VOPackage vo) throws BaseException {

    YhsForm yhsForm = (YhsForm) vo.getData();
    SWDJJBSJ djsj = null;
    try {
      // �����ҵ�Ǽǻ�����Ϣ
      /* start added by huxiaofeng 2005.8.16*/
      //djsj = InterfaceDj.getJBSJ(yhsForm.getJsjdm(), vo.getUserData());
      djsj = InterfaceDj.getJBSJ_New(yhsForm.getJsjdm(), vo.getUserData());
      yhsForm.setNsrzt(djsj.getNsrzt());
      /* end added by huxiaofeng 2005.8.16*/

      //Update  End    Zhou kejing 20031118
    }
    catch (Exception ex1) {
      ex1.printStackTrace();
      //Insert  Start  Zhou kejing 20031118
      throw ExceptionUtil.getBaseException(ex1);
      //Insert  End    Zhou kejing 20031118
    }
    if (djsj == null) {
      Debug.out("�˼�������벻���ڣ�");
      throw new ApplicationException("�˼�������벻���ڣ�");
    }
    yhsForm.setNsrmc(djsj.getNsrmc()); //�õ���˰�� ��λ����
    yhsForm.setQylxdh(djsj.getZcdzlxdh()); //�õ���˰�� ��ϵ�绰 (ע���ַ��ϵ�绰)
    yhsForm.setNd(getSbnd(yhsForm.getSbrq()));

    Connection conn = null;
    SfDBAccess da = null;
    try {

      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);

      setSzsm(da, yhsForm);

      // �����ֶ�
      String[] zb_columns = {
          "nd", "skssksrq", "skssjsrq",
          "qylxdh", "hjfs", "hjjsje", "hjynse", "sbrq"};

//Insert  Start  Zhou kejing 20031121
      Vector vZ = new Vector();
      vZ.add(" qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'");
      vZ.add(" nd = '" + yhsForm.getNd() + "'");
      vZ.add(" jsjdm= '" + yhsForm.getJsjdm() + "'");
      List zlist = da.query(Yhsbgz.class, vZ);
      if (zlist.size() > 0) {
        yhsForm.setCjrq( ( (Yhsbgz) zlist.get(0)).getCjrq().toString());
        //���ڸ�ֵ
        Map tempMap = new HashMap();
        BeanUtil.copyBeanToMap(zb_columns, (Yhsbgz) zlist.get(0),
                               tempMap);
        yhsForm.setSbrq( (String) tempMap.get("sbrq"));
        yhsForm.setSkssksrq( (String) tempMap.get("skssksrq"));
        yhsForm.setSkssjsrq( (String) tempMap.get("skssjsrq"));
      }
      Debug.out("yhs cjrq" + yhsForm.getCjrq());
//Insert  End    Zhou kejing 20031121

      HashMap mxMapData = new HashMap();

      //����걨��ϸֵ
      String[] mx_columns = {
          "fs", "jsje", "sl", "sjse"};
      Vector vmx = new Vector();
      vmx.add(" qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'");
      vmx.add(" nd = '" + yhsForm.getNd() + "'");
      vmx.add(" jsjdm= '" + yhsForm.getJsjdm() + "'");
      List mxlist = da.query(Yhsbgmx.class, vmx);
      for (int i = 0; i < mxlist.size(); i++) {

        Yhsbgmx orMx = (Yhsbgmx) mxlist.get(i);
        HashMap map = new HashMap();
        BeanUtil.copyBeanToMap(mx_columns, orMx, map);
        mxMapData.put(orMx.getSzsmdm(), map);

      }

      Iterator items = yhsForm.getDataList().iterator();
      while (items.hasNext()) {
        Map item = (Map) items.next();
        if (mxMapData.containsKey(item.get("szsmdm"))) {
          Map data = (Map) mxMapData.get(item.get("szsmdm"));
          item.putAll(data);
        }
      }
    }
    catch (Exception ex1) {
      throw ExceptionUtil.getBaseException(ex1);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return yhsForm;
  }

  /**
   * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ������ӡ��˰�����˰�걨��
   * @param   vo ҵ�����
   * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */
  private Object doSave(VOPackage vo) throws BaseException {
    Connection conn = null;
    SfDBAccess da = null;
    YhsForm yhsForm = (YhsForm) vo.getData();

    conn = SfDBResource.getConnection();
    da = new SfDBAccess(conn);
    try {

  /* start added by huxiaofeng 2005.8.16*/
      //Update  Start  Zhou kejing 20031118
      //SWDJJBSJ jbsj = (SWDJJBSJ) InterfaceDj.getJBSJ(yhsForm.getJsjdm(),
      // vo.getUserData());
      //Update  End    Zhou kejing 20031118
      SWDJJBSJ jbsj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(yhsForm.getJsjdm(),
          vo.getUserData());
  /* end added by huxiaofeng 2005.8.16*/
      yhsForm.setNsrmc(jbsj.getNsrmc());
      yhsForm.setQylxdh(jbsj.getZcdzlxdh());
      yhsForm.setNd(getSbnd(yhsForm.getSbrq()));

      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'" +
                " and jsjdm= '" + yhsForm.getJsjdm() +
                "' and nd= '" + yhsForm.getNd() + "'",
                new Yhsbgmx()); //ɾ����ϸ������
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'" +
                " and jsjdm= '" + yhsForm.getJsjdm() +
                "' and nd= '" + yhsForm.getNd() +
                "'", new Yhsbgz()); ; //ɾ����������

      yhsForm.setSwjgzzjgdm2(jbsj.getSwjgzzjgdm());
      yhsForm.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
      yhsForm.setFsdm(CodeConstant.FSDM_SMSB);
      yhsForm.setQylxdh(jbsj.getDjzclxdm()); //��ҵ��ϵ�绰Ϊע���ַ��ϵ�绰��

      String zbNames[] = {
          "jsjdm", "nd", "skssksrq", "skssjsrq", "hjfs",
          "hjjsje", "hjynse", "swjgzzjgdm", "lrr", "fsdm",
          "sbrq"};
      Map map = new HashMap();
      BeanUtil.copyBeanToMap(zbNames, yhsForm, map);
      Yhsbgz orZb = new Yhsbgz(); //ormapping ����ֵ����
      BeanUtil.populate(orZb, map); //������ֵ����ֵ
      Timestamp timeNow = SfTimeUtil.getNowTimestamp();

      if (yhsForm.getCjrq().length() > 0) {
        orZb.setCjrq(Timestamp.valueOf(yhsForm.getCjrq()));
      }
      else {
        orZb.setCjrq(timeNow);
      }
      orZb.setLrrq(timeNow);
      orZb.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
      da.insert(orZb);

      //���ݲ�����ϸ��
      List mxdatalist = yhsForm.getDataList();
      List mxlist = new ArrayList();
      Vector condition = new Vector();
      for (int i = 0; i < mxdatalist.size(); i++) {
        Map mxMap = (Map) mxdatalist.get(i);
        //���һЩ�����Ϣ
        mxMap.put("szdm", "16");
        mxMap.put("jsjdm", yhsForm.getJsjdm());
        mxMap.put("nd", yhsForm.getNd());
        mxMap.put("swjgzzjgdm", yhsForm.getSwjgzzjgdm());

        Yhsbgmx orMx = new Yhsbgmx();
        BeanUtil.populate(orMx, mxMap); //�����ݴ��ݸ���ϸֵ����
        if (yhsForm.getCjrq().length() > 0) {
          orMx.setCjrq(Timestamp.valueOf(yhsForm.getCjrq()));
        }
        else {
          orMx.setCjrq(timeNow);
        }
        orMx.setLrrq(timeNow);
        orMx.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
        mxlist.add(orMx);
      }
      da.insert(mxlist); //������ϸ������

      setSzsm(da, yhsForm);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return null;
  }

  /**
   * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
   * @param    vo ҵ�����
   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
   * @throws BaseException ��������������׳��쳣��Ϣ
   */
  private Object doDelete(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhsForm yhsForm = (YhsForm) vo.getData();
    yhsForm.setNd(getSbnd(yhsForm.getSbrq()));

    String jsjdmd = yhsForm.getJsjdm();

    SfDBAccess da = null;
    try {
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);

      //ɾ����ϸ������
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'"
                + " and jsjdm= '" + jsjdmd + "' and nd= '"
                + yhsForm.getNd() + "'",
                new Yhsbgmx());

      //ɾ����������
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'"
                + " and jsjdm= '" + jsjdmd + "' and nd= '"
                + yhsForm.getNd() + "'",
                new Yhsbgz());

      setSzsm(da, yhsForm);
    }
    catch (BaseException ex2) {
      throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

    return null;
  }

  /**
   * ����ӡ��˰˰��˰Ŀ
   * @param  da ˰�ݿ�����
   * @param  yhsForm ��������
   * @throws BaseException ��������������׳��쳣��Ϣ
   */
  private void setSzsm(SfDBAccess da, YhsForm yhsForm) throws BaseException {
    yhsForm.getDataList().clear();

    Vector condition = new Vector();
    condition.add(" szsmdm like '16%' ");
    condition.add(" szsmdm > '160092' ");
    List list = da.query(com.ttsoft.bjtax.shenbao.model.domain.Szsm.class,
                         condition);

    String[] szsm_columns = {
        "szsmdm", "szsmmc", "sl", };
    try {
      for (int i = 0; i < list.size(); i++) {
        //�����ϸֵ
        Szsm orSm = (Szsm) list.get(i);
        Map map = new HashMap();
        BeanUtil.copyBeanToMap(szsm_columns, orSm, map);
        yhsForm.getDataList().add(map);
        //mxMapData.add(map);
      }
    }
    catch (Exception ex2) {
      throw new ApplicationException("����ӡ��˰˰��˰Ŀʧ�ܣ�");
    }
  }

  /**
   * ���������걨�ڼ䡢�걨����
   * @param zbForm zbForm
   * @throws BaseException ��������������׳��쳣��Ϣ
   */
  private void initForm(YhsForm zbForm) throws BaseException {

    zbForm.setSbrq(SfDateUtil.getDate());
    // �걨�ڼ�
    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(zbForm.getSbrq()));
    try {
      String ksrq = DateTimeUtil.timestampToString(
          (Timestamp) qj.get(Skssrq.SKSSKSRQ),
          DateTimeUtil.JAVA_DATEFORMAT);

      String jsrq = DateTimeUtil.timestampToString(
          (Timestamp) qj.get(Skssrq.SKSSJSRQ),
          DateTimeUtil.JAVA_DATEFORMAT);

      zbForm.setSkssksrq(ksrq);
      zbForm.setSkssjsrq(jsrq);
      zbForm.setNd( (String) qj.get(Skssrq.SKSSRQ_ND));
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * �����걨���
   * @param sbrq �걨����
   * @return String ND
   */
  private String getSbnd(String sbrq) {

    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
    return (String) qj.get(Skssrq.SKSSRQ_ND);
  }

}
