/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现一票一税缴款书的查询功能</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QsjksbjksypysProcessor
    implements Processor {
  public QsjksbjksypysProcessor() {
  }

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

      default:
        return null;
    }

  }

  /**
   * 得到传送过来的缴款书号 从中选出所有的满足条件的明细
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

    QsjksbjksypysActionForm form = (QsjksbjksypysActionForm) vo.getData();
    try {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      //UserData对象
      UserData ud = (UserData) vo.getUserData();
      //得到区县代码
      String qxdm = InterfaceDj.getQxdm(ud);
      BigDecimal hjje = new BigDecimal("0.00");
      String mxPmmc = ""; //明细品目名称
      String mxKssl = ""; //明细课税数量
      String mxJsje = ""; //明细缴税金额
      String mxSl = ""; //明细，税率
      String mxSjse = ""; //明细实缴税额
      String mxFcbl = ""; //明细分成比例
      
      String sql = "";
      Sbjkzb orObj = new Sbjkzb();
      Vector condition = new Vector();
      //添加区县代码(如果非稽查一二局用户,则含区县代码查询条件)
      if(!(qxdm.endsWith("25")||qxdm.endsWith("41"))){
          condition.add("qxdm='" + qxdm + "'");
      }
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      condition.add("jkpzh='" + form.getJkpzh() + "'");
      
      //取出数据库中的该条记录 只有一条 目前按照多条的处理的
      List list = sbDB.query(orObj.getClass(), condition);
      if (list.size() != 1) {
    	  // 抛出异常
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
      form.setYhmc(orObj.getYhmc());
      form.setZh(orObj.getZh());
      form.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", orObj.getSklxdm())); //税款类型

      /**  通过登记接口得到纳税人名称**/
      
      if (((!TinyTools.isCompany(form.getJsjdm())) && form.getSjly()
				.equals(CodeConstant.SMSB_SJLY_BJQS))
				|| form.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
    	  
    	 System.out.println("===========增加补缴欠缴税款模块处理===============");
        //纳税人为自然人
        ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
        form.setNsrmc(jbsj.getNsrmc());
      }
      else {
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);

        form.setNsrmc(jbsj.getNsrmc());
        //隶属关系和注册类型为空
        form.setLsgx(""); //隶属关系
        form.setZclxmc(""); //注册类型名称
      }
      //设置地方税务机关
      form.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                                                orObj.
                                                getSwjgzzjgdm().substring(0, 2) +
                                                "00"));
      form.setBz(orObj.getBz()); //备注

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
        
        // 调用网上申报接口得到预算科目分成比例名称
    	String fcbl = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(orObj.getYskmdm(), map.get("szsmdm").toString(), map.get("swjgzzjgdm").toString());	

      	map.put("fcbl", fcbl);
        
        mxPmmc += map.get("szsmmc") + ";;"; //明细品目名称
        mxJsje += map.get("jsje") + ";;"; //明细缴税金额
        mxSjse += map.get("sjse") + ";;"; //明细实缴税额
        mxFcbl += map.get("fcbl") + ";;"; //明细分成比例 
        BigDecimal tmpBig = new BigDecimal(map.get("sjse").toString());
        tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
        hjje = hjje.add(tmpBig);
      }

      form.setMxPmmc(mxPmmc); //明细品目名称
      form.setMxKssl(mxKssl); //明细课税数量
      form.setMxJsje(mxJsje); //明细缴税金额
      form.setMxSl(mxSl); //明细，税率
      form.setMxSjse(mxSjse); //明细实缴税额
      form.setMxFcbl(mxFcbl); //明细分成比例      

      form.setHjje(deFormat.format(hjje)); //合计金额
      form.setHjjedx(Currency.convert(hjje)); //把合计金额转换为大写

      form.setDataList( (ArrayList) orMxList);
      
      form.setTfrqn(form.getLrrq().substring(0, 4));//填发日期年
      form.setTfrqy(form.getLrrq().substring(4, 6));//填发日期月
      form.setTfrqr(form.getLrrq().substring(6, 8));//填发日期日
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
