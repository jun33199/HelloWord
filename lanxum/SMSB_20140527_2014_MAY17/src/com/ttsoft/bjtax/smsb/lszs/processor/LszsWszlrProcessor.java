/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszz;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsWszlrForm;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ������˰֤¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsWszlrProcessor
    implements Processor {
  public LszsWszlrProcessor() {
  }

  /**
   * ͨ�ô������ģ��
   * @param vo  ���ݴ���ֵ����
   * @return  ���ݽ������[ActionForm]
   * @throws com.ttsoft.framework.exception.BaseException
   */
  public Object process(VOPackage vo) throws BaseException {
    Object result = null;

    /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
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
      case CodeConstant.SMSB_ZHSB_INITLIST:
        return getInitList(vo);
      default:
        throw new ApplicationException(
            "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
    }
    return result;
  }

  /**
   * ҳ���ʼ��
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doShow(VOPackage vo) throws BaseException {
    List dataList = new ArrayList();
//    Connection conn = null;
//    SfDBUtils sfDB = null;
    Timestamp nowTime = new Timestamp(System.currentTimeMillis());

    LszsWszlrForm pf = new LszsWszlrForm();
    pf = (LszsWszlrForm) vo.getData();

    try {
      //UserData����
      UserData ud = (UserData) vo.getUserData();

      pf.setLrrq(SfDateUtil.getDate());
      //�ӵǼǽӿ��л����Ϣ
      SWDJJBSJ dj = new SWDJJBSJ();
      try {
        dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
        pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
        pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
        pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
        //pf.setGjbzhydm(dj.getGjbzhydm()); //���ұ�׼��ҵ����
        //Modified by lufent 20003-11-26
        pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //���ұ�׼��ҵ���� 8190
        pf.setDjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
        //pf.setDjzclxmc(dj.getDjzclxmc()); //remove modified by lufeng 20031031
        pf.setDz(dj.getJydz()); //��ַ����Ӫ��ַ
      }
      catch (Exception ex5) {
        ex5.printStackTrace();
        throw ExceptionUtil.getBaseException(ex5);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return pf;
  }

  /**
   * ��ѯ
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doQuery(VOPackage vo) throws BaseException {
    List dataList = new ArrayList();
    //Connection conn = null;
    //SfDBUtils sfDB = null;
    LszsWszlrForm pf = new LszsWszlrForm();
    pf = (LszsWszlrForm) vo.getData();

    try {
      //conn = SfDBResource.getConnection();
      //sfDB = new SfDBUtils(conn);
      //�ӵǼǽӿ��л����Ϣ
      //SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm());
      //pf.setNsrmc(dj.getNsrmc());
      //pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
      //pf.setGjbzhydm(dj.getGjbzhydm()); //���ұ�׼��ҵ����
      //pf.setDjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
      //pf.setDjzclxmc(dj.getDjzclxmc()); //
      //pf.setDz(dj.getJydz()); //��ַ����Ӫ��ַ
    }
    catch (Exception ex) {
      throw new ApplicationException("��ѯ��˰����Ϣ����");
    }
    finally {
      //SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * ����
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doSave(VOPackage vo) throws BaseException {
    List dataList = new ArrayList();
    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
    String pzzldm = ""; //Ʊ֤�������
    String zhdm = ""; //�ʻ�����
    String wszh = ""; //��˰֤��
    String ndzb = ""; //����ֱ�
    String wszxh = ""; //���
    String nsrjsjdm = "";
    String nsrmc = "";
    String swjgzzjgdm = "";
    String qxdm = ""; //���ش���
    String sklxdm = ""; //˰������ ��ɢ˰Դ˰��������Ŀ tujb 2014.05
    //String

    String names[] = {
        "swjgzzjgdm", "zjhm", "zjlxdm",
        "wszh", "nsrmc", "pzzldm", "hjsjje",
        "lrr", "gjbzhydm", "djzclxdm", "swjgzzjgdm2"};
    //remove ˰�������֯�������� "swjgzzjgmc" Modified by lufeng 20031031

    Connection conn = null;
    SfDBUtils sfDB = null;
    //from����
    LszsWszlrForm pf = new LszsWszlrForm();
    pf = (LszsWszlrForm) vo.getData();
    zhdm = pf.getZhdm();
    pzzldm = pf.getPzzldm();
    nsrmc = pf.getNsrmc();
    sklxdm = pf.getSklxdm(); //˰������ ��ɢ˰Դ˰��������Ŀ tujb 2014.05

    swjgzzjgdm = pf.getSwjgzzjgdm();
    UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    //ormapping����
    Lsswszz orObjz = new Lsswszz();
    dataList = (List) pf.getDataList();

    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //�õ����ش���
      qxdm = InterfaceDj.getQxdm(ud);
      //���ұ�׼��ҵ���� ����Ϊ�գ�
      if (pf.getGjbzhydm().equals("")) {
        //Modified by lufent 20003-11-26
        pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //���ұ�׼��ҵ���� 8190
        //throw new ApplicationException("�޷���ù��ұ�׼��ҵ���룡");
      }
      //�Ǽ�ע�����ʹ���
      if (pf.getDjzclxdm().equals("")) {
        throw new ApplicationException("�Ǽ�ע�����ʹ��벻��Ϊ�գ�");
      }

      //������
      JksUtil ju = new JksUtil();
      try {
        wszxh = ju.getSequenceOfWSZXH(conn);
      }
      catch (Exception ex) {
        throw new ApplicationException("��ȡ���кų���");
      }

      //��������
      double total = 0;
      //�õ��ܽ�� by lufeng 20031031
//      for (int i = 0; i < dataList.size(); i++) {
//        HashMap map = (HashMap) dataList.get(i);
//        total = total +
//            StringUtil.getDouble(String.valueOf(map.get("sjse")), 0.00);
//      }

      //�����˰֤��
      try {
        String retResult = ServiceProxy.getNumber(ud, pzzldm,
                                                  StringUtil.getDouble(pf.
            getHjsjje(),
            total), "1", "1");
        //Ʊ֤�ӿ��޸ģ�//Modified by lufeng 2003-12-01
        ndzb = retResult.substring(0, 4); //����ֱ�
        wszh = retResult.substring(4); //��˰֤��
      }
      catch (Exception ex) {
        ex.printStackTrace();
        throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�");
      }
      pf.setWszh(wszh);
      pf.setNdzb(ndzb);
      //pf.setHjsjje(String.valueOf(total));
      try {
        BeanUtil.copyBeanToBean(names, pf, orObjz);

        if (orObjz.getZjlxdm().equals(CodeConstant.ZJLXDM_NULL)) {
          //֤������Ϊ��ʱ������֤������Ϊ˰�������֯��������+yyyyMMddHHmmssSSS
          /**20040413 Shi Yanfeng**/
          orObjz.setZjhm(TinyTools.getXh(orObjz.getSwjgzzjgdm()));
        }
      }
      catch (Exception ex1) {
        throw new ApplicationException("��ʽ��������Ϣ����");
      }
      //����ĳЩֵ
      orObjz.setWszxh(wszxh); //���
      orObjz.setWszh(wszh);
      orObjz.setCjrq(nowTime); //��������
      orObjz.setLrrq(nowTime); //¼������
      orObjz.setQxdm(qxdm); //���ش���
      orObjz.setJsjdm(pf.getJsjdm()); //���������
      //orObjz.setClbjdm(CodeConstant.SMSB_CLBJDM_WCL); //�����Ǵ��� 0
      orObjz.setClbjdm(CodeConstant.SMSB_WSZ_UNPRINT); //�����Ǵ��룬δ��ӡ 0
      orObjz.setFsdm(CodeConstant.FSDM_SMSB); //�Ǽ��걨��ʽ����Ϊ��1
      //�������
      orObjz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
      orObjz.setNdzb(ndzb); //����ֱ�
      orObjz.setSklxdm(sklxdm); //˰������ ��ɢ˰Դ˰��������Ŀ tujb 2014.05
      //��������
      try {
        da.insert(orObjz);
      }
      catch (BaseException ex1) {
        ex1.printStackTrace();
        throw new ApplicationException("����������Ϣ����");
      }

      //������ϸ��
      for (int i = 0; i < dataList.size(); i++) {
        //��ʼ����ϸ
        Lsswszmx orObjmx = new Lsswszmx();
        HashMap map = (HashMap) dataList.get(i);
        try {
          BeanUtil.populate(orObjmx, map);
        }
        catch (Exception ex2) {
          throw new ApplicationException("��ʽ����ϸ����Ϣ����");
        }
        //��������ֵ����ṹ�����⣬���޸ĺ����޸Ĵ˴�����Ϣ
        orObjmx.setWszxh(wszxh); //���
        orObjmx.setPzzldm(pzzldm);
        orObjmx.setWszh(wszh);
        orObjmx.setCjrq(nowTime); //��������
        orObjmx.setLrrq(nowTime); //¼������
        orObjmx.setQxdm(qxdm); //���ش���
        //orObjmx.setNsrmc(nsrmc);
        orObjmx.setJsjdm(pf.getJsjdm()); //���������
        orObjmx.setSwjgzzjgdm(swjgzzjgdm);
        orObjmx.setCjrq(nowTime);
        orObjmx.setJzbz(CodeConstant.SMSB_JZBZ); //���˱�־��Ĭ������0
        //�������
        orObjmx.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
        orObjmx.setNdzb(ndzb); //����ֱ�
        //���Ԥ�㼶��
        //���Ԥ�㼶�� //Modified by lufeng 2003-11-26
        orObjmx.setYsjcdm(CodeConstant.YSJC_GTGSH); //������Ԥ�㼶�δ��룬21 �ط���
//        try {
//          Ysjc ysjc = JksUtil.getYsjc(orObjmx.getJsjdm(), orObjmx.getSzsmdm(),
//                                      java.sql.Date.valueOf( (String.valueOf(
//              nowTime)).substring(0, 10)));
//          orObjmx.setYsjcdm(ysjc.getYsjcdm());
//        }
//        catch (Exception ex3) {
//          throw new ApplicationException("��ȡԤ�㼶�δ������");
//        }
        //���Ԥ���Ŀ
        try {
          Yskm yskm = (Yskm) JKAdapter.getInstance().getYskm(orObjmx.getSzsmdm(),
              orObjz.getDjzclxdm(),
              orObjz.getGjbzhydm(),
              orObjmx.getYsjcdm());
          orObjmx.setYskmdm(yskm.getYskmdm());
        }
        catch (Exception ex4) {
          throw new ApplicationException("û��Ԥ���Ŀ���룡");
        }

        if (orObjmx.getYskmdm().equals("")) {
          throw new ApplicationException("Ԥ���Ŀ���벻��Ϊ�գ�");
        }

        //��������
        try {
          da.insert(orObjmx);
        }
        catch (BaseException ex6) {
          ex6.printStackTrace();
          throw new ApplicationException("������ϸ����Ϣ����");
        }
      } //end of loop
    }
    catch (BaseException ex) {
      //���治�ɹ��������ϸո�ȡ������˰֤�ţ�
      try {
        wszh = ServiceProxy.setCancellation(ud,
                                            pf.getPzzldm(), ndzb + wszh,
                                            StringUtil.getDouble(pf.getHjsjje(),
            0.00),
                                            "1", "0", "1");
      }
      catch (Exception ex5) {
        ex5.printStackTrace();
      }
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * ɾ��
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doDelete(VOPackage vo) throws BaseException {
    return null;
  }

  /**
   * ����
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object doUpdate(VOPackage vo) throws BaseException {
    return null;
  }

  /**
   * �õ���ʼ��list����˰��˰Ŀlist,����˰list
   * @param vo ���ݼ����󣨰���Form��UserData����
   * @return ��ǰҳ���Ӧ��Form����
   * @throws BaseException
   */
  private Object getInitList(VOPackage vo) throws BaseException {
    //��ȡform����
    ZhsbActionForm form = (ZhsbActionForm) vo.getData();
    List ret = new ArrayList();
    Connection con = null;
    try {
      con = SfDBResource.getConnection();

      //�õ�˰��˰Ŀ�����б��б�͸���˰�б�

//      EArray jsArray = new EArray(con);
//      String tempJsStr = jsArray.getArray("szsmlist_add",
//          "SELECT SZSMDM, FJSSZSMDM FROM sbdb.SB_JL_SZSMYFJS");
//      //���������֮����ӿո� shiyanfeng 20031030
//      tempJsStr +=
//          jsArray.getMsgs("szsmdm", "select SZSMDM , SZSMDM||' '||SZSMMC descr from dmdb.SB_DM_SZSM where length(szsmdm)=6 and szsmdm not like'%91' and szsmdm not like'%92' and (sffjs!='2' or sffjs is null) order by SZSMDM",
//                          new ArrayList());
      /**
       * ʹ�ô��������˰��˰Ŀ�����б�
       * Shi Yanfeng
       * 20031031
       */
      EArray jsArray = new EArray();
      String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                "ZHSB_SZSMADD");
      tempJsStr +=
          jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",
                                new ArrayList());

      //����˰�ѽӿڴ����ڶ�����ʺ͸���˰
      List mxList = this.dealWithSfgl(form.getJsjdm(), this.getSzsmList(con));
      //�����Ѿ��õ�����������mapΪ��ϸ�������˰����������
      Date date = new Date(); //SfDateUtil.getDate(form.getSbrq());
      if (date == null) {
        date = new Date();
      }
      this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
      form.setInitMxList(mxList);
      //������ϸ���ݵ�js����
      tempJsStr += this.getMxJsArray(mxList);
      tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\"];";
      form.setScriptStr(tempJsStr);
      //���ø�֪�����б�
      //form.setGzsxList(this.getGzsxList(form.getJsjdm(),new Date()));
      return form;

    }
    catch (Exception ex) {
      ex.printStackTrace();
      //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
      throw new ApplicationException("�õ�˰��˰Ŀ�����б��б�͸���˰�б���Ϣ����");
    }
    finally {
      SfDBResource.freeConnection(con);
    }

  }

  /**
   * �õ�˰��˰Ŀ
   * @param con �������Ӷ���
   * @return ˰��˰Ŀ������list
   * @throws BaseException
   */
  private List getSzsmList(Connection con) throws BaseException {

    try {
      List ret = new ArrayList();
      ret = CodeManager.getCodeList("ORSZSM", CodeConstants.CODE_MAP_ORLIST).
          getRecords();
      return ret;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new ApplicationException("��ȡ˰��˰Ŀ����");
    }
    /*
         //Connection con = null;
         try {
      //con = SfDBResource.getConnection();
      SfDBAccess db = new SfDBAccess(con);
      Vector v = new Vector();
      v.add("1=1 order by szsmdm");
      return db.query(new Szsm().getClass(), v);
         }
         catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
         }
         finally {
      //SfDBResource.freeConnection(con);
         }
     */

  }

  /**
   * ������ϸ�б��е�˰��˰Ŀ���걨���ڡ����˰����������
   * @param jsjdm ���������
   * @param mxList ��ϸ�б�����list
   * @param rq �걨����
   * @param conn ���ݿ�����
   * @return ��ϸ�б��list
   * @throws BaseException
   */
  private List addSkssrqByMap(String jsjdm, List mxList, Date rq,
                              Connection conn) throws BaseException {
    List ret = new ArrayList();
    try {
      //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
      SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
      //�õ��������ڵ�����˰��˰Ŀ˰����������
      Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), new Date(), conn);
      //ͨ��˰�ѽӿڻ��Ӧ��˰�ƶ�
      Map ynsjeMap = InterfaceSf4Sb.getYnsje(jsjdm, rq);
      for (int i = 0; i < mxList.size(); i++) {

        //Ϊÿ����ϸ���˰����������
        SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
        //����˰����������mapΪ��ϸ�������˰����������
        Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());
        if (zqrl != null) {
          mxData.setSkssjsrq(zqrl.getZqssrqz());
          mxData.setSkssksrq(zqrl.getZqssrqq());
        }
        else {
          Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                                           CodeConstant.SKLXDM_ZCJK,
                                           mxData.getZqlxdm(),
                                           rq, mxData.getSjse(),
                                           mxData.getKssl(),
                                           mxData.getJsje(),
                                           ynsjeMap);
//          Map map = (Map)Skssrq.getSksssq(mxData.getSzsmdm(),
//                                CodeConstant.SKLXDM_ZCJK,
//                                mxData.getZqlxdm(),
//                                new Date());
          mxData.setSkssjsrq( (Timestamp) map.get("SKSSJSRQ")); //��ʼ����
          mxData.setSkssksrq( (Timestamp) map.get("SKSSKSRQ")); //��������
        }
        ret.add(mxData);
      }
      return ret;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
      throw new ApplicationException("��ȡ˰��˰Ŀ���걨���ڡ����˰���������ڳ���");
    }
  }

  /**
   * ������ϸ�����б�����js2ά����<br>
   * @param mxList ��ϸ����list
   * @return ��ϸ���ݵ�javascript��ά����
   */
  private String getMxJsArray(List mxList) {
    StringBuffer ret = new StringBuffer();
    //ret.append("[");
    for (int i = 0; i < mxList.size(); i++) {
      SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
      ret.append("[");
      ret.append("\"" + mxData.getSzsmdm() + "\",");
      ret.append("\"" + mxData.getSzmc() + "\",");
      ret.append("\"" + mxData.getSzsmmc() + "\",");
      if (mxData.getSkssksrq() != null) {
        ret.append("\"" + SfDateUtil.getDate(mxData.getSkssksrq()) + "\",");
      }
      else {
        ret.append("\"" + mxData.getSkssksrq() + "\",");
      }
      if (mxData.getSkssjsrq() != null) {
        ret.append("\"" + SfDateUtil.getDate(mxData.getSkssjsrq()) + "\",");
      }
      else {
        ret.append("\"" + mxData.getSkssjsrq() + "\",");
      }
      ret.append("\"" + mxData.getKssl() + "\",");
      ret.append("\"" + mxData.getJsje() + "\",");
      ret.append("\"" + mxData.getSjse() + "\",");
      ret.append("\"" + mxData.getSzdm() + "\",");
      ret.append("\"" + mxData.getSffjs() + "\",");
      ret.append("\"" + mxData.getSzsmdm() + "\",");
      ret.append("\"" + mxData.getAsljbs() + "\",");
      ret.append("\"" + mxData.getSl() + "\"");
      ret.append("],");
    }
    if (ret.length() > 0) {
      //��������ݣ���ɾ�������ӵĶ���
      ret.delete(ret.length() - 1, ret.length());
    }
    else {
      return "var szsmlist = new Array();";
    }
    ret.append("];");
    ret = SfStringUtils.replaceAll(ret, "null", "");
//    String clean = SfStringUtil.replaceString(ret.toString(),"null","");
//    return "var szsmlist = [" + clean;
    return "var szsmlist = [" + ret.toString();

  }

  /**
   * �������������õ�����˰��˰Ŀ��˰������ʱ��<br>
   * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
   * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
   * @param djzclxdm �Ǽ�ע�����ʹ���
   * @param rq �걨����
   * @param conn ���ݿ�����
   * @return ˰��������ʼ ����ʱ�䣬�޽����ڵ�Map
   * @throws java.lang.Exception
   */
  private Map getSksssqMap(String djzclxdm, Date rq, Connection conn) throws
      Exception {
    List ret = new ArrayList();
    //Connection conn = null;
    try {
      //�õ�����
      //conn = SfDBResource.getConnection();

      String dateStr = TinyTools.Date2String(rq,"yyyyMM");
      Vector criteria = new Vector(); //��ѯ����
      criteria.add("djzclxdm='" + djzclxdm + "'");
      criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
      criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

      SfDBAccess db = new SfDBAccess(conn);
      ret = db.query(new Zqrl().getClass(), criteria);
      Map zqrlMap = new HashMap();
      for (int i = 0; i < ret.size(); i++) {
        Zqrl zqrl = (Zqrl) ret.get(i);
        zqrlMap.put(zqrl.getSzsmdm(), zqrl);
      }
      return zqrlMap;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "��ѯ��������ʧ��!");
      throw new ApplicationException("��ѯ��������ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      //SfDBResource.freeConnection(conn);
    }

  }

  /**
   * ����˰�ѹ����϶����������������Ϊ�µ�˰��˰Ŀ����<br>
   * �������ʹ��룱�����ꡢ��������ꡢ�������ȡ�12�����£�
   * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist��
   * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
   * @param jsjdm ���������
   * @param szsmList ˰��˰Ŀlist
   * @return �����ĸ���˰��˰Ŀ��Ӧ����ϸlist
   * @throws java.lang.Exception
   */
  private List dealWithSfgl(String jsjdm, List szsmList) throws Exception {
    //��Ӵ�������ݵ���ϸlist
    List mxList = new ArrayList();
    //˰�ѹ���ӿ�
    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
        new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

    try {
      //ͨ�������������ڶ���͸���˰��һ�Ľӿڵõ��������
      Map cdfMap = this.getCDFSet(jsjdm, new Date(), new Date(), new Date());
      //���ڶ�����Ϣ
      Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.SFGL_SB_DQDE));
      //Ӫҵ˰����˰��Ϣ
      Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.SFGL_SB_FJS));
      //����˰�ѽӿڵõ���������Ϣ
      Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.SFGL_SB_CFT));
      mxList = this.creatMxList(szsmList);
      //���ݸ�˰����ӿڵõ����и��˵Ķ���ϼ�
      List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);

      //������˰�����ڶ�����������
      for (int i = 0; i < mxList.size(); i++) {
        SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
        // ����˰�ѹ����еĸ���˰˰��
        String szsmdm = mxData.getSzsmdm();
        if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
            fjsInfo != null) {
          //˰Ŀ��Ӫҵ˰����˰��������˰��ȡ�ú˶���Ϣ
          Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0, 2));
          if (tszslmx != null) {
            mxData.setSl(tszslmx.getSl());
          }
        }
        // ����˰�ѹ����еĶ��ڶ���
        if (dqdeInfo != null) {
          Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
          if (dqde != null) {
            if (dqde.getZsfsdm().equals("01")) {
              //���շ�ʽΪ����
              mxData.setSjse(dqde.getYnsrd());
              mxData.setJsje(dqde.getYnsrd());
              mxData.setFromDqde(true);
            }
//            else {
//              //���շ�ʽΪ����
//              mxData.setSl(dqde.getZsl());
//            }
          }
        }
        // ��������
        if (cftInfo != null) {
          Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
          //BigDecimal money = (BigDecimal) cftInfo.get(szsmdm);
          //ʵ�ɽ��
          if (cft != null) {
            BigDecimal money = cft.getSjje();
            mxData.setSjse(money);
            mxData.setJsje(money);
            mxData.setKssl(cft.getKssl());
          }
        }
        //����н����050110
        if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM)) {
          //��˰����ϼ�
          BigDecimal hj = new BigDecimal(0);
          //�ϼ����еĸ�˰����
          for (int ig = 0; ig < gsList.size(); ig++) {
            Grtszygsde temp = (Grtszygsde) gsList.get(ig);
            hj = hj.add(temp.getHdske());
          }
          if (hj.longValue() != 0) {
            mxData.setSjse(hj);
          }
        }

      }

      return mxList;
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�ʧ��");
    }

  }

  /**
   * ����˰�ѽӿڵõ���������Ϣ<br>
   * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
   * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
   * @param jsjdm ���������
   * @param date �걨����
   * @param qsrq ��ʼ����
   * @param jzrq ��ֹ����
   * @return ����������map
   */
  private Map getCDFSet(String jsjdm, Date date, Date qsrq, Date jzrq) {

    Map map = new HashMap();
    try {

      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
          new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
      map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
    }
    catch (Exception ex) {
      //ex.printStackTrace();
      return null;
    }
    return map;
  }

  /**
   * ����˰�ѽӿ�ȡ�ö��ڶ���˶���ͨ�����ڶ���list�õ����ڶ���map
   * @param dqdeInfo ���ڶ��������list
   * @return ���ڶ����map
   * @throws BaseException
   */
  private Map getDqdeMap(List dqdeInfo) throws BaseException {
//     com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
//         new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//
//     List dqdeInfo = sfglProxy.getYnsje(jsjdm, new Date());

    Map dqdeMap = new HashMap();
    if (dqdeInfo != null) {
      for (int i = 0; i < dqdeInfo.size(); i++) {
        Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
        dqdeMap.put(dqde.getSzsmdm(), dqde);
      }
    }

    return dqdeMap;
  }

  /**
   * ����˰�ѽӿڵõ�����˰�˶�
   * @param fjsInfo ����˰���ݵ�list
   * @return ����˰������map
   * @throws BaseException
   */
  private Map getFjsMap(List fjsInfo) throws BaseException {
//    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
//        new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//
//    List fjsInfo = sfglProxy.getYyfjsslInfo(jsjdm);

    Map fjsMap = new HashMap();
    for (int i = 0; i < fjsInfo.size(); i++) {
      Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
      fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
    }

    return fjsMap;
  }

  /**
   * ����˰�ѽӿڵõ�������List�õ�map<br>
   * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
   * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
   * <br>�÷���ͨ�����������õ����еĳ���������
   * @param cftList ���������ݵ�list
   * @return ���س�������map
   */
  private Map getCftMap(List cftList) {

    Map cftMap = new HashMap();
    try {

//      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
//          new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//      List cftList = proxy.getCftsyhdInfo(jsjdm, date);
      for (int i = 0; i < cftList.size(); i++) {
        Cftsyhd temp = (Cftsyhd) cftList.get(i);
        cftMap.put(temp.getSzsmdm(), temp);
      }
    }
    catch (Exception ex) {
      //ex.printStackTrace();
      return null;
    }
    return cftMap;
  }

  /**
   * ����˰�ѹ����϶��������˰��˰Ŀ����<br>
   * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
   * @param szsmList ˰��˰Ŀ����list
   * @return �����ĸ���˰��˰Ŀ��Ӧ����ϸlist
   */
  private List creatMxList(List szsmList) {
    //��Ӵ�������ݵ���ϸlist
    List mxList = new ArrayList();
    String szdm = "";
    String szmc = "";
    for (int i = 0; i < szsmList.size(); i++) {
      //ǰ̨��ʾ���걨�ɿ���ϸ
      SbjkmxDis temp = new SbjkmxDis();
      Szsm szsm = (Szsm) szsmList.get(i);
      //��Ϊ����˰��˰Ŀ�����������Կ�����ȡ��˰��

      if (szsm.getSzsmdm().length() == 2) {
        //����Ϊ2����˰�ִ���
        szdm = szsm.getSzsmdm();
        szmc = szsm.getSzsmmc();
      }
      //������ϸ��˰��˰Ŀ
      if (szsm.getSzsmdm().length() == 6) {
        //����Ϊ6Ϊ˰Ŀ
        temp.setSzsmdm(szsm.getSzsmdm());
        temp.setSzsmmc(szsm.getSzsmmc());
        //�����Ƿ񸽼�˰��ʾ
        temp.setSffjs(szsm.getSffjs());
        //����˰��
        temp.setSzdm(szdm);
        temp.setSzmc(szmc);
        temp.setAsljbs(szsm.getAsljbs());
        temp.setSl(szsm.getSl());
        temp.setZqlxdm(szsm.getZqlxdm());
        mxList.add(temp);
      }

    }
    return mxList;
  }

}
//:-)
