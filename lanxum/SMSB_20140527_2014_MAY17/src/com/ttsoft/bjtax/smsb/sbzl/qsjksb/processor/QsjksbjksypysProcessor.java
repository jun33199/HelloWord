/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbjksypysActionForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ��һƱһ˰�ɿ���Ĳ�ѯ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QsjksbjksypysProcessor
    implements Processor {
  public QsjksbjksypysProcessor() {
  }

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException {
    switch (vo.getAction()) {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);

      default:
        return null;
    }

  }

  /**
   * �õ����͹����Ľɿ���� ����ѡ�����е�������������ϸ
   * @param vo ҵ�����
   * @return ��form ��Ҫ��ϸ��Ϣ�����DataList
   * @throws BaseException
   */
  private Object doQuery(VOPackage vo) throws BaseException {
    Connection conn = null;
    SfDBAccess sbDB = null;
    SfDBUtils sbDBU = null;
    //���ø�ʽ������
    DecimalFormat deFormat = new DecimalFormat("#0.00");

    QsjksbjksypysActionForm form = (QsjksbjksypysActionForm) vo.getData();
    try {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      //UserData����
      UserData ud = (UserData) vo.getUserData();
      //�õ����ش���
      String qxdm = InterfaceDj.getQxdm(ud);
      BigDecimal hjje = new BigDecimal("0.00");
      String mxPmmc = ""; //��ϸƷĿ����
      String mxKssl = ""; //��ϸ��˰����
      String mxJsje = ""; //��ϸ��˰���
      String mxSl = ""; //��ϸ��˰��
      String mxSjse = ""; //��ϸʵ��˰��
      String mxFcbl = ""; //��ϸ�ֳɱ���
      
      String sql = "";
      Sbjkzb orObj = new Sbjkzb();
      Vector condition = new Vector();
      //������ش���(����ǻ���һ�����û�,�����ش����ѯ����)
      if(!(qxdm.endsWith("25")||qxdm.endsWith("41"))){
          condition.add("qxdm='" + qxdm + "'");
      }
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      condition.add("jkpzh='" + form.getJkpzh() + "'");
      
      //ȡ�����ݿ��еĸ�����¼ ֻ��һ�� Ŀǰ���ն����Ĵ����
      List list = sbDB.query(orObj.getClass(), condition);
      if (list.size() != 1) {
    	  // �׳��쳣
      }
      orObj = (Sbjkzb) list.get(0);
      String[] names = {
          "jsjdm", "zh", "skssksrq", "skssjsrq", "jkpzh",
          "lrrq", "jydzlxdm", "yskmdm",
          "xjrq", "sbbh", "ysjcdm", "sjje", "sjly"};

      //��ormappingֵ�����е����ݿ�����form��ȥ
      BeanUtil.copyBeanToBean(names, orObj, form);
      //���û����ϵ�绰��Ϊ��
      if (form.getJydzlxdm() == null || form.getJydzlxdm().equals("null")) {
        form.setJydzlxdm("");
      }
      form.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
          orObj.getSwjgzzjgdm()));
      form.setYsjcmc(CodeUtils.getCodeBeanLabel("DM_YSJC", orObj.getYsjcdm()));

      //�տ����
      try {
        Vector dmVector = new Vector();
        dmVector.addElement("swjgzzjgdm='" + orObj.getSwjgzzjgdm() + "'");
        ArrayList dmList = (ArrayList) sbDB.query(Swjgzzjg.class, dmVector);
        if (dmList.size() <= 0) {
          throw new ApplicationException("��ȡ�տ������Ϣ����");
        }
        Swjgzzjg swjgzzjg = (Swjgzzjg) dmList.get(0);
        form.setGkzzjgmc(swjgzzjg.getSkgk()); //�տ����
        form.setSkgkh(swjgzzjg.getGkjhh()); //�տ�����

      }
      catch (Exception ex1) {
        ex1.printStackTrace();
        throw ExceptionUtil.getBaseException(ex1);
      }

      form.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orObj.getSzdm()));
      form.setYskmmc(CodeUtils.getCodeBeanLabel("DM_YSKM", orObj.getYskmdm()));
      form.setYhmc(orObj.getYhmc());
      form.setZh(orObj.getZh());
      form.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", orObj.getSklxdm())); //˰������

      /**  ͨ���Ǽǽӿڵõ���˰������**/
      
      if (((!TinyTools.isCompany(form.getJsjdm())) && form.getSjly()
				.equals(CodeConstant.SMSB_SJLY_BJQS))
				|| form.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
    	  
    	 System.out.println("===========���Ӳ���Ƿ��˰��ģ�鴦��===============");
        //��˰��Ϊ��Ȼ��
        ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
        form.setNsrmc(jbsj.getNsrmc());
      }
      else {
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);

        form.setNsrmc(jbsj.getNsrmc());
        //������ϵ��ע������Ϊ��
        form.setLsgx(""); //������ϵ
        form.setZclxmc(""); //ע����������
      }
      //���õط�˰�����
      form.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                                                orObj.
                                                getSwjgzzjgdm().substring(0, 2) +
                                                "00"));
      form.setBz(orObj.getBz()); //��ע

      sql = "select  szsmdm, jkpzh, jsjdm, yskmdm, ysjcdm, kssl, jsje, sjse, skssksrq, skssjsrq, rkje, sbbh, sjfc, qjfc, swjgzzjgdm, nd, sl, cjrq, lrrq, qxdm  from "
          + " SBDB.SB_JL_SBJKMX "
          + " where jkpzh='" + form.getJkpzh() + "'";

      List orMxList = sbDBU.getDataList(sql);
      HashMap map = new HashMap();
      for (int i = 0; i < orMxList.size(); i++) {
        map = new HashMap();
        //Sbjkmx orMx = (Sbjkmx)orMxList.get(i);
        map = ( (HashMap) orMxList.get(i));
        if (map.get("jsje") != null) {
          map.put("jsje",
                  deFormat.format(StringUtil.getDouble( (String) map.get("jsje"),
              0.00)));
        }
        if (map.get("sjse") != null) {
          map.put("sjse",
                  deFormat.format(StringUtil.getDouble( (String) map.get("sjse"),
              0.00)));
        }
        map.put("szsmmc",
                CodeUtils.getCodeBeanLabel("DM_SZSM", (String) map.get("szsmdm")));
        map.put("szsmdm_old", map.get("szsmdm"));

        if (map.get("kssl") == null || map.get("kssl").equals("")) {
          mxKssl += " " + ";;"; //��ϸ��˰����
        }
        else {
          mxKssl += map.get("kssl") + ";;"; //��ϸ��˰����

        }
        if (map.get("sl") == null || map.get("sl").equals("")) {
          mxSl += " " + ";;"; //��ϸ��˰����
        }
        else {
          mxSl += map.get("sl") + ";;"; //��ϸ��˰����

        }
        
        // ���������걨�ӿڵõ�Ԥ���Ŀ�ֳɱ�������
    	String fcbl = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(orObj.getYskmdm(), map.get("szsmdm").toString(), map.get("swjgzzjgdm").toString());	

      	map.put("fcbl", fcbl);
        
        mxPmmc += map.get("szsmmc") + ";;"; //��ϸƷĿ����
        mxJsje += map.get("jsje") + ";;"; //��ϸ��˰���
        mxSjse += map.get("sjse") + ";;"; //��ϸʵ��˰��
        mxFcbl += map.get("fcbl") + ";;"; //��ϸ�ֳɱ��� 
        BigDecimal tmpBig = new BigDecimal(map.get("sjse").toString());
        tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
        hjje = hjje.add(tmpBig);
      }

      form.setMxPmmc(mxPmmc); //��ϸƷĿ����
      form.setMxKssl(mxKssl); //��ϸ��˰����
      form.setMxJsje(mxJsje); //��ϸ��˰���
      form.setMxSl(mxSl); //��ϸ��˰��
      form.setMxSjse(mxSjse); //��ϸʵ��˰��
      form.setMxFcbl(mxFcbl); //��ϸ�ֳɱ���      

      form.setHjje(deFormat.format(hjje)); //�ϼƽ��
      form.setHjjedx(Currency.convert(hjje)); //�Ѻϼƽ��ת��Ϊ��д

      form.setDataList( (ArrayList) orMxList);
      
      form.setTfrqn(form.getLrrq().substring(0, 4));//�������
      form.setTfrqy(form.getLrrq().substring(4, 6));//�������
      form.setTfrqr(form.getLrrq().substring(6, 8));//�������
      return form;
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }
  
}
