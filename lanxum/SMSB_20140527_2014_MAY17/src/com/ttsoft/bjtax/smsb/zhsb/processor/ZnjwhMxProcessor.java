package com.ttsoft.bjtax.smsb.zhsb.processor;

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
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkclrz;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx_His;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb_His;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.zhsb.web.ZnjwhMxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

public class ZnjwhMxProcessor implements Processor {
	  public ZnjwhMxProcessor() {
	  }
	  private static final String YPYS = "ZhsbjksypysAction.do"; //һƱһ˰��������
	  private static final String YPDS = "ZhsbjksypdsAction.do"; //һƱ��˰��������
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
	      case CodeConstant.SMSB_UPDATEACTION:
	        return doUpdate(vo);

	      default:
	        return null;
	    }

	  }

	  /**
	   * �õ����͹����Ľ������ ����ѡ�����е�������������ϸ
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

	    ZnjwhMxActionForm form = (ZnjwhMxActionForm) vo.getData();
	    try {
	      conn = SfDBResource.getConnection();
	      sbDB = new SfDBAccess(conn);
	      sbDBU = new SfDBUtils(conn);
	      //UserData����
	      UserData ud = (UserData) vo.getUserData();
	      //�õ����ش���
	      String qxdm = InterfaceDj.getQxdm(ud);
	      BigDecimal hjje = new BigDecimal("0.00");
	      //Modified by lufeng 2003-11-18
	      String mxPmmc = ""; //��ϸƷĿ����
	      String mxKssl = ""; //��ϸ��˰����
	      String mxJsje = ""; //��ϸ��˰���
	      String mxSl = ""; //��ϸ��˰��
	      String mxSjse = ""; //��ϸʵ��˰��

	      String sql = "";
	      Sbjkzb orObj = new Sbjkzb();
	      Vector condition = new Vector();
	      //������ش���
	      condition.add("qxdm='" + qxdm + "'");
	      condition.add("jsjdm='" + form.getJsjdm() + "'");
	      condition.add("jkpzh='" + form.getJkpzh() + "'");
	      //ȡ�����ݿ��еĸ�����¼ ֻ��һ�� Ŀǰ���ն����Ĵ����
	      List list = sbDB.query(orObj.getClass(), condition);
	      if (list.size() != 1) {

//	 �׳��쳣
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

	      //Modified by lufneg 2003-12-30
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
	      //�������ƺ��ʺ�ֱ��ȡ�걨�ɿ���������� Modified by lufeng 20003-12-11
	      form.setYhmc(orObj.getYhmc());
	      form.setZh(orObj.getZh());
	      //Modified by lufeng 2003-12-13
	      form.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", orObj.getSklxdm())); //˰������

	      /**  ͨ���Ǽǽӿڵõ���˰������   Shi Yanfeng 20031028 **/
	      if (form.getSjly()!=null&&form.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
	        //��˰��Ϊ��Ȼ��
	        ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
	        form.setNsrmc(jbsj.getNsrmc());
	      }
	      else {
	        /* start added by huxiaofeng 2005.8.16*/
	        //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
	        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
	        /* end added by huxiaofeng 2005.8.16*/

	        form.setNsrmc(jbsj.getNsrmc());
	        //������ϵ��ע������Ϊ�գ�Modified by lufeng 2004-03-16
	        form.setLsgx(""); //������ϵ
	        form.setZclxmc(""); //ע����������
	      }
	      //���õط�˰����أ�Modified by lufeng 2004-03-16
	      form.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
	                                                orObj.
	                                                getSwjgzzjgdm().substring(0, 2) +
	                                                "00"));
	      form.setBz(orObj.getBz()); //��ע

	      sql = "select  szsmdm, jkpzh, jsjdm, yskmdm, ysjcdm, kssl, jsje, sjse, skssksrq, skssjsrq, rkje, sbbh, sjfc, qjfc, swjgzzjgdm, nd, sl, cjrq, lrrq, qxdm  from "
	          + " SBDB.SB_JL_SBJKMX "
	          + " where jkpzh='" + form.getJkpzh() + "' and szsmdm like '%0091'";

	      List orMxList = sbDBU.getDataList(sql);
	      HashMap map = new HashMap();
	      for (int i = 0; i < orMxList.size(); i++) {
	        map = new HashMap();
	        //Modified by lufeng 20031113
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

	        //Modified by lufeng 20031118
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
	        mxPmmc += map.get("szsmmc") + ";;"; //��ϸƷĿ����
	        mxJsje += map.get("jsje") + ";;"; //��ϸ��˰���
	        mxSjse += map.get("sjse") + ";;"; //��ϸʵ��˰��
	        //����ϼ�//Modified by lufeng 20031113
	        //hjje = hjje + StringUtil.getDouble( (String) map.get("sjse"), 0.00);
	        BigDecimal tmpBig = new BigDecimal(map.get("sjse").toString());
	        tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	        hjje = hjje.add(tmpBig);
	      }
	      //��ϸ��Ϣ,Modified by lufeng 2003-10-18
	      form.setMxPmmc(mxPmmc); //��ϸƷĿ����
	      form.setMxKssl(mxKssl); //��ϸ��˰����
	      form.setMxJsje(mxJsje); //��ϸ��˰���
	      form.setMxSl(mxSl); //��ϸ��˰��
	      form.setMxSjse(mxSjse); //��ϸʵ��˰��

	      //Modified by lufeng 20031113
	      form.setHjje(deFormat.format(hjje)); //�ϼƽ��
	      form.setHjjedx(Currency.convert(hjje)); //�Ѻϼƽ��ת��Ϊ��д

	      form.setDataList( (ArrayList) orMxList);
	      EArray jsArray = new EArray();
	      String tempJsStr = "";
	      tempJsStr += jsArray.getArrayByCode("szsmlist", "JKSWH_SZSMDMLIST");
	      String jsSql = "";
	      String szsmdm = (String) map.get("szsmdm");
	      if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM023) == 0) { //��˰��˰Ŀ����ǰ��λ��023ʱ �ǽ��� ���⴦��
	        jsSql = "JKSWH_SZSMDM02023";
	      }
	      else {
	        if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM02) == 0) { //��˰��˰Ŀ����ǰ��λ��02��ǰ��λ����023ʱ �ǽ��� ���⴦��
	          jsSql = "JKSWH_SZSMDM02";
	        }
	        else {
	          jsSql = "JKSWH_SZSMDM" + orObj.getSzdm();
	        }
	      }

	      tempJsStr +=
	          jsArray.getMsgsByCode("szsmdm", jsSql,
	                                form.getDataList());
	      form.setScriptStr(tempJsStr);
	      return form;
	    }
	    catch (Exception e) {
	      throw ExceptionUtil.getBaseException(e);
	    }
	    finally {
	      SfDBResource.freeConnection(conn);
	    }

	  }

	  /**
	   * �õ����͹����Ľ������ ����ѡ�����е�������������ϸ
	   * @param vo ҵ�����
	   * @return ��form ��Ҫ��ϸ��Ϣ�����DataList
	   * @throws BaseException
	   */
	  private Object doUpdate(VOPackage vo) throws BaseException {
	    Connection conn = null;
	    SfDBAccess sbDB = null;
	    SfDBUtils sbDBU = null;
	    ZnjwhMxActionForm form = (ZnjwhMxActionForm) vo.getData();
	    //UserData����
	    UserData ud = (UserData) vo.getUserData();
	    //�õ����ش���
	    String qxdm = InterfaceDj.getQxdm(ud);

	    String zrlxdm = "01";
	    if(form.getDataList().size()<1)
	    {
	    	zrlxdm = "02";
	    }
	    Timestamp now = new Timestamp( (new java.util.Date()).getTime());

	    try {
	      conn = SfDBResource.getConnection();
	      sbDB = new SfDBAccess(conn);
	      sbDBU = new SfDBUtils(conn);
	      Sbjkzb orObj = new Sbjkzb();
	      Vector condition = new Vector();
	      //������ش���
	      condition.add("qxdm='" + qxdm + "'");
	      condition.add("jsjdm='" + form.getJsjdm() + "'");
	      condition.add("jkpzh='" + form.getJkpzh() + "'");
	      List orObjList = sbDB.query(orObj.getClass(), condition);
	      if (orObjList.size() != 1) {

//	 �׳��쳣
	      }
	      // ȡ��Ҫ��¼����ʷ�����ϸ����
	      Sbjkmx oldMx = new Sbjkmx();
	      Vector conditionMx = new Vector();
	      conditionMx.add("jsjdm='" + form.getJsjdm() + "'");
	      conditionMx.add("jkpzh='" + form.getJkpzh() + "'");
	      conditionMx.add("szsmdm like '%0091'");
	      List oldMxList = sbDB.query(oldMx.getClass(), conditionMx);
              // ȡ��Ҫ��¼����ʷ�����ϸ����
              Sbjkmx checkMx = new Sbjkmx();
              Vector condCheckMx = new Vector();
              condCheckMx.add("jsjdm='" + form.getJsjdm() + "'");
              condCheckMx.add("jkpzh='" + form.getJkpzh() + "'");
              condCheckMx.add("szsmdm not like '%0091'");
              List checkMxList = sbDB.query(checkMx.getClass(), condCheckMx);
              if(oldMxList.size()<1)
              {
//	    		 �׳��쳣

              }


	      orObj = (Sbjkzb) orObjList.get(0);
	      Sbjkzb_His sbjkzbHis = new Sbjkzb_His();
	      String []oldZbNames = {"jkpzh","sklxdm","jsjdm","fsdm","lsgxdm","yhdm","yhmc",
	    		  "zh","djzclxdm","swjgzzjgdm","zsswjgzzjgdm","gjbzhydm","gkzzjgdm","yskmdm","ysjcdm",
	    		  "szdm","lrrq","sbrq","jksj","xjrq","clbjdm","sjje","zyrq","rkje",
	    		  "zwbs","hxrdm","hxrmc","lrr","bz","hxrq","sbbh","jydzlxdm","skssksrq",
	    		  "skssjsrq","sjly","nd","cjrq","qxdm","sphm"};

	      BeanUtil.copyBeanToBean(oldZbNames, orObj, sbjkzbHis);
	      sbjkzbHis.setZrlxdm(zrlxdm);
	      sbjkzbHis.setZrrq(now);
	      sbjkzbHis.setZrr(ud.getYhid());

	      List sbMxHisList = new ArrayList();
	      String []oldMxNames={"szsmdm","jkpzh","jsjdm","yskmdm","ysjcdm","kssl",
	    		  "jsje","sjse","skssksrq","skssjsrq","rkje","sbbh",
	    		  "sjfc","qjfc","swjgzzjgdm","nd","sl","cjrq","lrrq","qxdm"};
	      for(int i=0; i<oldMxList.size(); i++)
	      {

	    	  Sbjkmx_His sbjkmxHis = new Sbjkmx_His();
	    	  Sbjkmx sbmx = (Sbjkmx)oldMxList.get(i);
		      BeanUtil.copyBeanToBean(oldMxNames, sbmx, sbjkmxHis);
		      sbjkmxHis.setZrlxdm(zrlxdm);
		      sbjkmxHis.setZrrq(now);
		      sbjkmxHis.setZrr(ud.getYhid());
	    	  sbMxHisList.add(sbjkmxHis);
	      }

	      Sbjkclrz rz = getSbjkclrz(zrlxdm, ud.getYhid(), orObj,now);

	      //˰�������֯��������
	      String szdm = "";
	      String ysjc = "";
	      String yskm = "";
	      String gjbzhydm = "";
	      String djzclxdm = "";

	      String swjgzzjgdm = orObj.getSwjgzzjgdm();
	      szdm = orObj.getSzdm();
	      ysjc = orObj.getYsjcdm();
	      yskm = orObj.getYskmdm();
	      gjbzhydm = orObj.getGjbzhydm();
	      djzclxdm = orObj.getDjzclxdm();
	      //���
	      String nd = orObj.getNd();
	      String[] names = {
	          "jsjdm", "skssksrq", "skssjsrq", "jkpzh",
	          "sbbh", "yskmdm", "ysjcdm"
	      };
	      /*
	            BeanUtil.copyBeanToBean(names, orObj, form);
	       */
	      Sbjkmx orMx = new Sbjkmx();
	      ArrayList orMxList = form.getDataList();
	      ArrayList orList = new ArrayList();
	      BigDecimal tempSjje = new BigDecimal(0);
	      JksUtil ju = new JksUtil();
	      ResultSet rs = null;
	      for (int i = 0; i < orMxList.size(); i++) {
	        HashMap map = (HashMap) orMxList.get(i);
	        orMx = new Sbjkmx();
	        BeanUtil.populate(orMx, map);
	        BeanUtil.copyBeanToBean(names, form, orMx);
	        //
	        tempSjje = tempSjje.add(orMx.getSjse());
	        //����˰�������֯��������
	        orMx.setSwjgzzjgdm(swjgzzjgdm);
	        //�������
	        orMx.setNd(nd);
	        //���ô������ڣ��޸����ڣ����ش���
	        orMx.setCjrq(orObj.getCjrq());
	        orMx.setLrrq(now);
	        orMx.setQxdm(orObj.getQxdm());
	        //added by wurong ,20031223 23:33
	        orMx.setRkje(orMx.getSjse());
	        //--//Modified By Ha Zhengze 20040314 Start/////////////////////
	        //--����˰��

	        rs = sbDB.querySQL("select sl from dmdb.sb_dm_szsm where szsmdm='" +
	                           orMx.getSzsmdm() + "'");
	        if (rs.next()) {
	          orMx.setSl(rs.getBigDecimal(1));
	        }
	        rs.close();
	        //--�����м��ֳɼ������ֳ�
	        Yskm yskmObj = JKAdapter.getInstance().getYskm(orMx.getSzsmdm(),
	            djzclxdm,
	            gjbzhydm,
	            ysjc);
	        if (CodeConstant.YSJC_DIFANGJI.equals(ysjc)) {
	          orMx.setSjfc(ju.getFc(orMx.getSjse(), yskmObj.getSjfcbl())); //�����оּ��ֳɽ��
	          orMx.setQjfc(ju.getFc(orMx.getSjse(), yskmObj.getQxfcbl())); //�������ؼ��ֳɽ��
	        }
	        StringBuffer sb=new StringBuffer();
	        sb.append(SfTimeUtil.getNowTimestamp()+"�ɿ���ά������-->");
	        sb.append("sl:");
	        sb.append(orMx.getSl());
	        sb.append("|");
	        sb.append("Sjfc:");
	        sb.append(orMx.getSjfc());
	        sb.append("|");
	        sb.append("qjfc:");
	        sb.append(orMx.getQjfc());
	        sb.append("|");
	        System.out.println(sb.toString());
	        //--//Modified By Ha Zhengze 20040314 end///////////////////////

	        orList.add(orMx);
	      }
              if(checkMxList!=null&&checkMxList.size()>0)
              {
                  for(int i=0; i<checkMxList.size(); i++)
                  {
                      Sbjkmx mx =(Sbjkmx)checkMxList.get(i);
                      tempSjje = tempSjje.add(mx.getSjse());
                      orList.add(mx);
                  }
              }
	      orObj.setSjje(tempSjje);
	      //��������
	      orObj.setRkje(orObj.getSjje());
	      //�����޽�����
	      orObj.setXjrq(SfTimeUtil.getTimestamp(form.getXjrq()));
	      //�޸�¼������
	      orObj.setLrrq(now);
	      try {
               System.out.println("ALL OK!!!");
               sbDB.insert(sbjkzbHis);
	    	sbDB.insert(sbMxHisList);
                sbDB.insert(rz);
	        sbDB.delete("jkpzh='" + form.getJkpzh()+"'", orMx);
	        if (orList.size() == 0) {
	          sbDB.delete(orObj);
	        }
	        else {
	          Debug.outVO(orObj);
	          sbDB.insert(orList);
	          sbDB.update(orObj);
	        }
	      }
	      catch (BaseException ex) {
	        throw ExceptionUtil.getBaseException(ex);
	      }

	      return form;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw ExceptionUtil.getBaseException(e);
	    }
	    finally {
	      SfDBResource.freeConnection(conn);
	    }

	  }

	 private Sbjkclrz getSbjkclrz(String zrlxdm, String zrr, Sbjkzb zb, Timestamp now)
	 {
		 Sbjkclrz sbjkclrz = new Sbjkclrz();
		 sbjkclrz.setBz("");
		 sbjkclrz.setCjrq(now);
		 sbjkclrz.setJkpzh(zb.getJkpzh());
		 sbjkclrz.setJsjdm(zb.getJsjdm());
		 sbjkclrz.setLrrq(now);
		 sbjkclrz.setNd(zb.getNd());
		 sbjkclrz.setQxdm(zb.getQxdm());
		 sbjkclrz.setZrlxdm(zrlxdm);
		 sbjkclrz.setZrr(zrr);
		 sbjkclrz.setZrrq(now);
		 return sbjkclrz;
	 }



}
