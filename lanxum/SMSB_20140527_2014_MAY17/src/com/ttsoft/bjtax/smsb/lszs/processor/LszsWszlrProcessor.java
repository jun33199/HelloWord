/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收完税证录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsWszlrProcessor
    implements Processor {
  public LszsWszlrProcessor() {
  }

  /**
   * 通用处理调度模块
   * @param vo  数据传递值对象
   * @return  数据结果对象[ActionForm]
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
            "ActionType有错误，processor中找不到相应的方法.");
    }
    return result;
  }

  /**
   * 页面初始化
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
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
      //UserData对象
      UserData ud = (UserData) vo.getUserData();

      pf.setLrrq(SfDateUtil.getDate());
      //从登记接口中获得信息
      SWDJJBSJ dj = new SWDJJBSJ();
      try {
        dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
        pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
        pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
        pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
        //pf.setGjbzhydm(dj.getGjbzhydm()); //国家标准行业代码
        //Modified by lufent 20003-11-26
        pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //国家标准行业代码 8190
        pf.setDjzclxdm(dj.getDjzclxdm()); //登记注册类型代码
        //pf.setDjzclxmc(dj.getDjzclxmc()); //remove modified by lufeng 20031031
        pf.setDz(dj.getJydz()); //地址，经营地址
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
   * 查询
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
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
      //从登记接口中获得信息
      //SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm());
      //pf.setNsrmc(dj.getNsrmc());
      //pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
      //pf.setGjbzhydm(dj.getGjbzhydm()); //国家标准行业代码
      //pf.setDjzclxdm(dj.getDjzclxdm()); //登记注册类型代码
      //pf.setDjzclxmc(dj.getDjzclxmc()); //
      //pf.setDz(dj.getJydz()); //地址，经营地址
    }
    catch (Exception ex) {
      throw new ApplicationException("查询纳税人信息出错！");
    }
    finally {
      //SfDBResource.freeConnection(conn);
    }
    return pf;
  }

  /**
   * 保存
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
   * @throws BaseException
   */
  private Object doSave(VOPackage vo) throws BaseException {
    List dataList = new ArrayList();
    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
    String pzzldm = ""; //票证种类代码
    String zhdm = ""; //帐户代码
    String wszh = ""; //完税证号
    String ndzb = ""; //年度字别
    String wszxh = ""; //序号
    String nsrjsjdm = "";
    String nsrmc = "";
    String swjgzzjgdm = "";
    String qxdm = ""; //区县代码
    String sklxdm = ""; //税款类型 零散税源税款类型项目 tujb 2014.05
    //String

    String names[] = {
        "swjgzzjgdm", "zjhm", "zjlxdm",
        "wszh", "nsrmc", "pzzldm", "hjsjje",
        "lrr", "gjbzhydm", "djzclxdm", "swjgzzjgdm2"};
    //remove 税务机关组织机构名称 "swjgzzjgmc" Modified by lufeng 20031031

    Connection conn = null;
    SfDBUtils sfDB = null;
    //from对象
    LszsWszlrForm pf = new LszsWszlrForm();
    pf = (LszsWszlrForm) vo.getData();
    zhdm = pf.getZhdm();
    pzzldm = pf.getPzzldm();
    nsrmc = pf.getNsrmc();
    sklxdm = pf.getSklxdm(); //税款类型 零散税源税款类型项目 tujb 2014.05

    swjgzzjgdm = pf.getSwjgzzjgdm();
    UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    //ormapping对象
    Lsswszz orObjz = new Lsswszz();
    dataList = (List) pf.getDataList();

    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //得到区县代码
      qxdm = InterfaceDj.getQxdm(ud);
      //国家标准行业代码 不能为空！
      if (pf.getGjbzhydm().equals("")) {
        //Modified by lufent 20003-11-26
        pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //国家标准行业代码 8190
        //throw new ApplicationException("无法获得国家标准行业代码！");
      }
      //登记注册类型代码
      if (pf.getDjzclxdm().equals("")) {
        throw new ApplicationException("登记注册类型代码不能为空！");
      }

      //获得序号
      JksUtil ju = new JksUtil();
      try {
        wszxh = ju.getSequenceOfWSZXH(conn);
      }
      catch (Exception ex) {
        throw new ApplicationException("获取序列号出错！");
      }

      //插入主表
      double total = 0;
      //得到总金额 by lufeng 20031031
//      for (int i = 0; i < dataList.size(); i++) {
//        HashMap map = (HashMap) dataList.get(i);
//        total = total +
//            StringUtil.getDouble(String.valueOf(map.get("sjse")), 0.00);
//      }

      //获得完税证号
      try {
        String retResult = ServiceProxy.getNumber(ud, pzzldm,
                                                  StringUtil.getDouble(pf.
            getHjsjje(),
            total), "1", "1");
        //票证接口修改，//Modified by lufeng 2003-12-01
        ndzb = retResult.substring(0, 4); //年度字别
        wszh = retResult.substring(4); //完税证号
      }
      catch (Exception ex) {
        ex.printStackTrace();
        throw new ApplicationException("获取完税证失败！");
      }
      pf.setWszh(wszh);
      pf.setNdzb(ndzb);
      //pf.setHjsjje(String.valueOf(total));
      try {
        BeanUtil.copyBeanToBean(names, pf, orObjz);

        if (orObjz.getZjlxdm().equals(CodeConstant.ZJLXDM_NULL)) {
          //证件类型为无时，设置证件号码为税务机关组织机构代码+yyyyMMddHHmmssSSS
          /**20040413 Shi Yanfeng**/
          orObjz.setZjhm(TinyTools.getXh(orObjz.getSwjgzzjgdm()));
        }
      }
      catch (Exception ex1) {
        throw new ApplicationException("格式化主表信息出错！");
      }
      //补充某些值
      orObjz.setWszxh(wszxh); //序号
      orObjz.setWszh(wszh);
      orObjz.setCjrq(nowTime); //创建日期
      orObjz.setLrrq(nowTime); //录入日期
      orObjz.setQxdm(qxdm); //区县代码
      orObjz.setJsjdm(pf.getJsjdm()); //计算机代码
      //orObjz.setClbjdm(CodeConstant.SMSB_CLBJDM_WCL); //处理标记代码 0
      orObjz.setClbjdm(CodeConstant.SMSB_WSZ_UNPRINT); //处理标记代码，未打印 0
      orObjz.setFsdm(CodeConstant.FSDM_SMSB); //登记申报方式代码为：1
      //设置年度
      orObjz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
      orObjz.setNdzb(ndzb); //年度字别
      orObjz.setSklxdm(sklxdm); //税款类型 零散税源税款类型项目 tujb 2014.05
      //更新数据
      try {
        da.insert(orObjz);
      }
      catch (BaseException ex1) {
        ex1.printStackTrace();
        throw new ApplicationException("新增主表信息出错！");
      }

      //插入明细表
      for (int i = 0; i < dataList.size(); i++) {
        //初始化明细
        Lsswszmx orObjmx = new Lsswszmx();
        HashMap map = (HashMap) dataList.get(i);
        try {
          BeanUtil.populate(orObjmx, map);
        }
        catch (Exception ex2) {
          throw new ApplicationException("格式化明细表信息出错！");
        }
        //设置其它值，表结构有问题，等修改后需修改此处的信息
        orObjmx.setWszxh(wszxh); //序号
        orObjmx.setPzzldm(pzzldm);
        orObjmx.setWszh(wszh);
        orObjmx.setCjrq(nowTime); //创建日期
        orObjmx.setLrrq(nowTime); //录入日期
        orObjmx.setQxdm(qxdm); //区县代码
        //orObjmx.setNsrmc(nsrmc);
        orObjmx.setJsjdm(pf.getJsjdm()); //计算机代码
        orObjmx.setSwjgzzjgdm(swjgzzjgdm);
        orObjmx.setCjrq(nowTime);
        orObjmx.setJzbz(CodeConstant.SMSB_JZBZ); //记账标志，默认六个0
        //设置年度
        orObjmx.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
        orObjmx.setNdzb(ndzb); //年度字别
        //获得预算级次
        //获得预算级次 //Modified by lufeng 2003-11-26
        orObjmx.setYsjcdm(CodeConstant.YSJC_GTGSH); //给定的预算级次代码，21 地方级
//        try {
//          Ysjc ysjc = JksUtil.getYsjc(orObjmx.getJsjdm(), orObjmx.getSzsmdm(),
//                                      java.sql.Date.valueOf( (String.valueOf(
//              nowTime)).substring(0, 10)));
//          orObjmx.setYsjcdm(ysjc.getYsjcdm());
//        }
//        catch (Exception ex3) {
//          throw new ApplicationException("获取预算级次代码出错！");
//        }
        //获得预算科目
        try {
          Yskm yskm = (Yskm) JKAdapter.getInstance().getYskm(orObjmx.getSzsmdm(),
              orObjz.getDjzclxdm(),
              orObjz.getGjbzhydm(),
              orObjmx.getYsjcdm());
          orObjmx.setYskmdm(yskm.getYskmdm());
        }
        catch (Exception ex4) {
          throw new ApplicationException("没有预算科目代码！");
        }

        if (orObjmx.getYskmdm().equals("")) {
          throw new ApplicationException("预算科目代码不能为空！");
        }

        //更新数据
        try {
          da.insert(orObjmx);
        }
        catch (BaseException ex6) {
          ex6.printStackTrace();
          throw new ApplicationException("新增明细表信息出错！");
        }
      } //end of loop
    }
    catch (BaseException ex) {
      //保存不成功，则作废刚刚取出的完税证号！
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
   * 删除
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
   * @throws BaseException
   */
  private Object doDelete(VOPackage vo) throws BaseException {
    return null;
  }

  /**
   * 更新
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
   * @throws BaseException
   */
  private Object doUpdate(VOPackage vo) throws BaseException {
    return null;
  }

  /**
   * 得到初始化list包括税种税目list,附加税list
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return 当前页面对应的Form对象
   * @throws BaseException
   */
  private Object getInitList(VOPackage vo) throws BaseException {
    //获取form对象
    ZhsbActionForm form = (ZhsbActionForm) vo.getData();
    List ret = new ArrayList();
    Connection con = null;
    try {
      con = SfDBResource.getConnection();

      //得到税种税目下拉列表列表和附加税列表

//      EArray jsArray = new EArray(con);
//      String tempJsStr = jsArray.getArray("szsmlist_add",
//          "SELECT SZSMDM, FJSSZSMDM FROM sbdb.SB_JL_SZSMYFJS");
//      //代码和名称之间添加空格 shiyanfeng 20031030
//      tempJsStr +=
//          jsArray.getMsgs("szsmdm", "select SZSMDM , SZSMDM||' '||SZSMMC descr from dmdb.SB_DM_SZSM where length(szsmdm)=6 and szsmdm not like'%91' and szsmdm not like'%92' and (sffjs!='2' or sffjs is null) order by SZSMDM",
//                          new ArrayList());
      /**
       * 使用代码表生成税种税目下拉列表
       * Shi Yanfeng
       * 20031031
       */
      EArray jsArray = new EArray();
      String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                "ZHSB_SZSMADD");
      tempJsStr +=
          jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",
                                new ArrayList());

      //根据税费接口处理定期定额、定率和附加税
      List mxList = this.dealWithSfgl(form.getJsjdm(), this.getSzsmList(con));
      //根据已经得到的征期日历map为明细数据添加税款所属日期
      Date date = new Date(); //SfDateUtil.getDate(form.getSbrq());
      if (date == null) {
        date = new Date();
      }
      this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
      form.setInitMxList(mxList);
      //生成明细数据的js数组
      tempJsStr += this.getMxJsArray(mxList);
      tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\"];";
      form.setScriptStr(tempJsStr);
      //设置告知事项列表
      //form.setGzsxList(this.getGzsxList(form.getJsjdm(),new Date()));
      return form;

    }
    catch (Exception ex) {
      ex.printStackTrace();
      //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
      throw new ApplicationException("得到税种税目下拉列表列表和附加税列表信息出错！");
    }
    finally {
      SfDBResource.freeConnection(con);
    }

  }

  /**
   * 得到税种税目
   * @param con 数据连接对象
   * @return 税种税目的数据list
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
      throw new ApplicationException("获取税种税目出错！");
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
      throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
         }
         finally {
      //SfDBResource.freeConnection(con);
         }
     */

  }

  /**
   * 根据明细列表中的税种税目、申报日期、添加税款所属日期
   * @param jsjdm 计算机代码
   * @param mxList 明细列表数据list
   * @param rq 申报日期
   * @param conn 数据库连接
   * @return 明细列表的list
   * @throws BaseException
   */
  private List addSkssrqByMap(String jsjdm, List mxList, Date rq,
                              Connection conn) throws BaseException {
    List ret = new ArrayList();
    try {
      //通过登记接口得到纳税人基本信息
      SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
      //得到该征期内的所有税种税目税款所属日期
      Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), new Date(), conn);
      //通过税费接口获得应纳税计额
      Map ynsjeMap = InterfaceSf4Sb.getYnsje(jsjdm, rq);
      for (int i = 0; i < mxList.size(); i++) {

        //为每个明细添加税款所属日期
        SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
        //根据税款所属日期map为明细数据添加税款所属日期
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
          mxData.setSkssjsrq( (Timestamp) map.get("SKSSJSRQ")); //开始日期
          mxData.setSkssksrq( (Timestamp) map.get("SKSSKSRQ")); //结束日期
        }
        ret.add(mxData);
      }
      return ret;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
      throw new ApplicationException("获取税种税目、申报日期、添加税款所属日期出错！");
    }
  }

  /**
   * 根据明细数据列表生成js2维数组<br>
   * @param mxList 明细数据list
   * @return 明细数据的javascript二维数组
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
      //如果有数据，则删除最后添加的逗号
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
   * 根据征期日历得到所有税种税目的税款所属时期<br>
   * 当前日期的月等于征期起始或截止日期的月
   * 征期截止日期＋1天=限缴日期<br>
   * @param djzclxdm 登记注册类型代码
   * @param rq 申报日期
   * @param conn 数据库连接
   * @return 税款所属开始 结束时间，限缴日期的Map
   * @throws java.lang.Exception
   */
  private Map getSksssqMap(String djzclxdm, Date rq, Connection conn) throws
      Exception {
    List ret = new ArrayList();
    //Connection conn = null;
    try {
      //得到连接
      //conn = SfDBResource.getConnection();

      String dateStr = TinyTools.Date2String(rq,"yyyyMM");
      Vector criteria = new Vector(); //查询条件
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
      //throw ExceptionUtil.getBaseException(ex, "查询征期日历失败!");
      throw new ApplicationException("查询征期日历失败！");
    }
    finally {
      //释放连接
      //SfDBResource.freeConnection(conn);
    }

  }

  /**
   * 根据税费管理认定情况处理征期类行为月的税种税目数据<br>
   * 征期类型代码１代表年、２代表半年、４代表季度、12代表月；
   * 根据定期定额数据和营业税附加税数据处理税种税目list，
   * 返回处理后的根据税种税目对应的明细list
   * @param jsjdm 计算机代码
   * @param szsmList 税种税目list
   * @return 处理后的根据税种税目对应的明细list
   * @throws java.lang.Exception
   */
  private List dealWithSfgl(String jsjdm, List szsmList) throws Exception {
    //添加处理后数据的明细list
    List mxList = new ArrayList();
    //税费管理接口
    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
        new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

    try {
      //通过车房土、定期定额和附加税合一的接口得到相关数据
      Map cdfMap = this.getCDFSet(jsjdm, new Date(), new Date(), new Date());
      //定期定额信息
      Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.SFGL_SB_DQDE));
      //营业税附加税信息
      Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.SFGL_SB_FJS));
      //根据税费接口得到车房土信息
      Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.SFGL_SB_CFT));
      mxList = this.creatMxList(szsmList);
      //根据个税定额接口得到所有个人的定额合计
      List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);

      //处理附加税，定期定额，车房土情况
      for (int i = 0; i < mxList.size(); i++) {
        SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
        // 处理税费管理中的附加税税率
        String szsmdm = mxData.getSzsmdm();
        if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
            fjsInfo != null) {
          //税目是营业税附加税，并且由税费取得核定信息
          Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0, 2));
          if (tszslmx != null) {
            mxData.setSl(tszslmx.getSl());
          }
        }
        // 处理税费管理中的定期定额
        if (dqdeInfo != null) {
          Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
          if (dqde != null) {
            if (dqde.getZsfsdm().equals("01")) {
              //征收方式为定额
              mxData.setSjse(dqde.getYnsrd());
              mxData.setJsje(dqde.getYnsrd());
              mxData.setFromDqde(true);
            }
//            else {
//              //征收方式为定律
//              mxData.setSl(dqde.getZsl());
//            }
          }
        }
        // 处理车房土
        if (cftInfo != null) {
          Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
          //BigDecimal money = (BigDecimal) cftInfo.get(szsmdm);
          //实缴金额
          if (cft != null) {
            BigDecimal money = cft.getSjje();
            mxData.setSjse(money);
            mxData.setJsje(money);
            mxData.setKssl(cft.getKssl());
          }
        }
        //处理工薪所得050110
        if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM)) {
          //个税定额合计
          BigDecimal hj = new BigDecimal(0);
          //合计所有的个税定额
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
      throw ExceptionUtil.getBaseException(ex, "处理税种税目列表失败");
    }

  }

  /**
   * 根据税费接口得到车房土信息<br>
   * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
   * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
   * @param jsjdm 计算机代码
   * @param date 申报日期
   * @param qsrq 起始日期
   * @param jzrq 截止日期
   * @return 车房土数据map
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
   * 根据税费接口取得定期定额核定，通过定期定额list得到定期定额map
   * @param dqdeInfo 定期定额的数据list
   * @return 定期定额的map
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
   * 根据税费接口得到附加税核定
   * @param fjsInfo 附加税数据的list
   * @return 附加税的数据map
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
   * 根据税费接口得到车房土List得到map<br>
   * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
   * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
   * <br>该方法通过计算机代码得到所有的车房土数据
   * @param cftList 车房土数据的list
   * @return 返回车房土的map
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
   * 根据税费管理认定情况处理税种税目数据<br>
   * 根据定期定额数据和营业税附加税数据处理税种税目list
   * @param szsmList 税种税目数据list
   * @return 处理后的根据税种税目对应的明细list
   */
  private List creatMxList(List szsmList) {
    //添加处理后数据的明细list
    List mxList = new ArrayList();
    String szdm = "";
    String szmc = "";
    for (int i = 0; i < szsmList.size(); i++) {
      //前台显示用申报缴款明细
      SbjkmxDis temp = new SbjkmxDis();
      Szsm szsm = (Szsm) szsmList.get(i);
      //因为按照税种税目代码排序，所以可以先取的税种

      if (szsm.getSzsmdm().length() == 2) {
        //长度为2按照税种处理
        szdm = szsm.getSzsmdm();
        szmc = szsm.getSzsmmc();
      }
      //设置明细的税种税目
      if (szsm.getSzsmdm().length() == 6) {
        //长度为6为税目
        temp.setSzsmdm(szsm.getSzsmdm());
        temp.setSzsmmc(szsm.getSzsmmc());
        //设置是否附加税标示
        temp.setSffjs(szsm.getSffjs());
        //设置税种
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
