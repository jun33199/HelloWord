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
	  private static final String YPYS = "ZhsbjksypysAction.do"; //一票一税的连接名
	  private static final String YPDS = "ZhsbjksypdsAction.do"; //一票多税的连接名
	  /**
	   * 实现Processor接口
	   * @param vo 业务参数
	   * @return Object VOPackage型数据
	   * @throws BaseException 业务异常
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
	   * 得到传送过来的交款书号 从中选出所有的满足条件的明细
	   * @param vo 业务参数
	   * @return 此form 主要明细信息存放在DataList
	   * @throws BaseException
	   */
	  private Object doQuery(VOPackage vo) throws BaseException {
	    Connection conn = null;
	    SfDBAccess sbDB = null;
	    SfDBUtils sbDBU = null;
	    //设置格式化数字
	    DecimalFormat deFormat = new DecimalFormat("#0.00");

	    ZnjwhMxActionForm form = (ZnjwhMxActionForm) vo.getData();
	    try {
	      conn = SfDBResource.getConnection();
	      sbDB = new SfDBAccess(conn);
	      sbDBU = new SfDBUtils(conn);
	      //UserData对象
	      UserData ud = (UserData) vo.getUserData();
	      //得到区县代码
	      String qxdm = InterfaceDj.getQxdm(ud);
	      BigDecimal hjje = new BigDecimal("0.00");
	      //Modified by lufeng 2003-11-18
	      String mxPmmc = ""; //明细品目名称
	      String mxKssl = ""; //明细课税数量
	      String mxJsje = ""; //明细缴税金额
	      String mxSl = ""; //明细，税率
	      String mxSjse = ""; //明细实缴税额

	      String sql = "";
	      Sbjkzb orObj = new Sbjkzb();
	      Vector condition = new Vector();
	      //添加区县代码
	      condition.add("qxdm='" + qxdm + "'");
	      condition.add("jsjdm='" + form.getJsjdm() + "'");
	      condition.add("jkpzh='" + form.getJkpzh() + "'");
	      //取出数据库中的该条记录 只有一条 目前按照多条的处理的
	      List list = sbDB.query(orObj.getClass(), condition);
	      if (list.size() != 1) {

//	 抛出异常
	      }
	      orObj = (Sbjkzb) list.get(0);
	      String[] names = {
	          "jsjdm", "zh", "skssksrq", "skssjsrq", "jkpzh",
	          "lrrq", "jydzlxdm", "yskmdm",
	          "xjrq", "sbbh", "ysjcdm", "sjje", "sjly"};

	      //将ormapping值对象中的数据拷贝到form中去
	      BeanUtil.copyBeanToBean(names, orObj, form);
	      //如果没有联系电话则为空
	      if (form.getJydzlxdm() == null || form.getJydzlxdm().equals("null")) {
	        form.setJydzlxdm("");
	      }
	      form.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
	          orObj.getSwjgzzjgdm()));
	      form.setYsjcmc(CodeUtils.getCodeBeanLabel("DM_YSJC", orObj.getYsjcdm()));

	      //Modified by lufneg 2003-12-30
	      //收款国库
	      try {
	        Vector dmVector = new Vector();
	        dmVector.addElement("swjgzzjgdm='" + orObj.getSwjgzzjgdm() + "'");
	        ArrayList dmList = (ArrayList) sbDB.query(Swjgzzjg.class, dmVector);
	        if (dmList.size() <= 0) {
	          throw new ApplicationException("获取收款国库信息出错！");
	        }
	        Swjgzzjg swjgzzjg = (Swjgzzjg) dmList.get(0);
	        form.setGkzzjgmc(swjgzzjg.getSkgk()); //收款国库
	        form.setSkgkh(swjgzzjg.getGkjhh()); //收款国库号

	      }
	      catch (Exception ex1) {
	        ex1.printStackTrace();
	        throw ExceptionUtil.getBaseException(ex1);
	      }

	      form.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orObj.getSzdm()));
	      form.setYskmmc(CodeUtils.getCodeBeanLabel("DM_YSKM", orObj.getYskmdm()));
	      //银行名称和帐号直接取申报缴款主表的数据 Modified by lufeng 20003-12-11
	      form.setYhmc(orObj.getYhmc());
	      form.setZh(orObj.getZh());
	      //Modified by lufeng 2003-12-13
	      form.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", orObj.getSklxdm())); //税款类型

	      /**  通过登记接口得到纳税人名称   Shi Yanfeng 20031028 **/
	      if (form.getSjly()!=null&&form.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
	        //纳税人为自然人
	        ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
	        form.setNsrmc(jbsj.getNsrmc());
	      }
	      else {
	        /* start added by huxiaofeng 2005.8.16*/
	        //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
	        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
	        /* end added by huxiaofeng 2005.8.16*/

	        form.setNsrmc(jbsj.getNsrmc());
	        //隶属关系和注册类型为空，Modified by lufeng 2004-03-16
	        form.setLsgx(""); //隶属关系
	        form.setZclxmc(""); //注册类型名称
	      }
	      //设置地方税务机关，Modified by lufeng 2004-03-16
	      form.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
	                                                orObj.
	                                                getSwjgzzjgdm().substring(0, 2) +
	                                                "00"));
	      form.setBz(orObj.getBz()); //备注

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
	          mxKssl += " " + ";;"; //明细课税数量
	        }
	        else {
	          mxKssl += map.get("kssl") + ";;"; //明细课税数量

	        }
	        if (map.get("sl") == null || map.get("sl").equals("")) {
	          mxSl += " " + ";;"; //明细课税数量
	        }
	        else {
	          mxSl += map.get("sl") + ";;"; //明细课税数量

	        }
	        mxPmmc += map.get("szsmmc") + ";;"; //明细品目名称
	        mxJsje += map.get("jsje") + ";;"; //明细缴税金额
	        mxSjse += map.get("sjse") + ";;"; //明细实缴税额
	        //计算合计//Modified by lufeng 20031113
	        //hjje = hjje + StringUtil.getDouble( (String) map.get("sjse"), 0.00);
	        BigDecimal tmpBig = new BigDecimal(map.get("sjse").toString());
	        tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	        hjje = hjje.add(tmpBig);
	      }
	      //明细信息,Modified by lufeng 2003-10-18
	      form.setMxPmmc(mxPmmc); //明细品目名称
	      form.setMxKssl(mxKssl); //明细课税数量
	      form.setMxJsje(mxJsje); //明细缴税金额
	      form.setMxSl(mxSl); //明细，税率
	      form.setMxSjse(mxSjse); //明细实缴税额

	      //Modified by lufeng 20031113
	      form.setHjje(deFormat.format(hjje)); //合计金额
	      form.setHjjedx(Currency.convert(hjje)); //把合计金额转换为大写

	      form.setDataList( (ArrayList) orMxList);
	      EArray jsArray = new EArray();
	      String tempJsStr = "";
	      tempJsStr += jsArray.getArrayByCode("szsmlist", "JKSWH_SZSMDMLIST");
	      String jsSql = "";
	      String szsmdm = (String) map.get("szsmdm");
	      if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM023) == 0) { //当税种税目代码前三位是023时 是金融 特殊处理
	        jsSql = "JKSWH_SZSMDM02023";
	      }
	      else {
	        if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM02) == 0) { //当税种税目代码前两位是02且前三位不是023时 是金融 特殊处理
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
	   * 得到传送过来的交款书号 从中选出所有的满足条件的明细
	   * @param vo 业务参数
	   * @return 此form 主要明细信息存放在DataList
	   * @throws BaseException
	   */
	  private Object doUpdate(VOPackage vo) throws BaseException {
	    Connection conn = null;
	    SfDBAccess sbDB = null;
	    SfDBUtils sbDBU = null;
	    ZnjwhMxActionForm form = (ZnjwhMxActionForm) vo.getData();
	    //UserData对象
	    UserData ud = (UserData) vo.getUserData();
	    //得到区县代码
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
	      //添加区县代码
	      condition.add("qxdm='" + qxdm + "'");
	      condition.add("jsjdm='" + form.getJsjdm() + "'");
	      condition.add("jkpzh='" + form.getJkpzh() + "'");
	      List orObjList = sbDB.query(orObj.getClass(), condition);
	      if (orObjList.size() != 1) {

//	 抛出异常
	      }
	      // 取得要记录到历史表的明细数据
	      Sbjkmx oldMx = new Sbjkmx();
	      Vector conditionMx = new Vector();
	      conditionMx.add("jsjdm='" + form.getJsjdm() + "'");
	      conditionMx.add("jkpzh='" + form.getJkpzh() + "'");
	      conditionMx.add("szsmdm like '%0091'");
	      List oldMxList = sbDB.query(oldMx.getClass(), conditionMx);
              // 取得要记录到历史表的明细数据
              Sbjkmx checkMx = new Sbjkmx();
              Vector condCheckMx = new Vector();
              condCheckMx.add("jsjdm='" + form.getJsjdm() + "'");
              condCheckMx.add("jkpzh='" + form.getJkpzh() + "'");
              condCheckMx.add("szsmdm not like '%0091'");
              List checkMxList = sbDB.query(checkMx.getClass(), condCheckMx);
              if(oldMxList.size()<1)
              {
//	    		 抛出异常

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

	      //税务机关组织机构代码
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
	      //年度
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
	        //设置税务机关组织机构代码
	        orMx.setSwjgzzjgdm(swjgzzjgdm);
	        //设置年度
	        orMx.setNd(nd);
	        //设置创建日期，修改日期，区县代码
	        orMx.setCjrq(orObj.getCjrq());
	        orMx.setLrrq(now);
	        orMx.setQxdm(orObj.getQxdm());
	        //added by wurong ,20031223 23:33
	        orMx.setRkje(orMx.getSjse());
	        //--//Modified By Ha Zhengze 20040314 Start/////////////////////
	        //--处理税率

	        rs = sbDB.querySQL("select sl from dmdb.sb_dm_szsm where szsmdm='" +
	                           orMx.getSzsmdm() + "'");
	        if (rs.next()) {
	          orMx.setSl(rs.getBigDecimal(1));
	        }
	        rs.close();
	        //--处理市级分成及区级分成
	        Yskm yskmObj = JKAdapter.getInstance().getYskm(orMx.getSzsmdm(),
	            djzclxdm,
	            gjbzhydm,
	            ysjc);
	        if (CodeConstant.YSJC_DIFANGJI.equals(ysjc)) {
	          orMx.setSjfc(ju.getFc(orMx.getSjse(), yskmObj.getSjfcbl())); //设置市局级分成金额
	          orMx.setQjfc(ju.getFc(orMx.getSjse(), yskmObj.getQxfcbl())); //设置区县级分成金额
	        }
	        StringBuffer sb=new StringBuffer();
	        sb.append(SfTimeUtil.getNowTimestamp()+"缴款书维护参数-->");
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
	      //添加入库金额
	      orObj.setRkje(orObj.getSjje());
	      //保存限缴日期
	      orObj.setXjrq(SfTimeUtil.getTimestamp(form.getXjrq()));
	      //修改录入日期
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
