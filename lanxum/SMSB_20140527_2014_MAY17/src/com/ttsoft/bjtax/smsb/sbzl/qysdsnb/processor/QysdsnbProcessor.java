/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsnbProcessor
    implements Processor {
  /**
   * 企业所得税税率
   */
  private final String QYSDS_SL = "0.33";

  /**
   * 企业所得税税种
   */
  private final String QYSDS_SZ = "30";

  /**
   * 实现Processor接口
   * @param vo 业务参数
   * @return Object VOPackage型数据
   * @throws BaseException 业务异常
   * 		1 当传过来的操作类型不对时抛出
   * 		2 当调用的业务方法抛出业务异常时向上传递抛出
   * 	其他异常抛出由EJB的process方法处理。
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
            "用户执行了系统不支持的方法或功能.");
    }

    return result;
  }

  /**
   * doShow初始化对象页面信息要素
   * @param vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException 当其他情况都会抛出异常信息
   */

  private Object doShow(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    // 初始化FORM：设置申报日期、申报期间
    initForm(nbForm);

    return nbForm;
  }

  /**
   * doQuery    用于返回页面索要查询的详尽信息
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   *
   */

  private Object doQuery(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();
    nbForm.setNsrmc(""); // 纳税人名称
    nbForm.setZcdzlxdh(""); // 注册地址联系电话

    Connection conn = null;
    SfDBAccess dbAgent = null;

    try {
      conn = SfDBResource.getConnection();
      dbAgent = new SfDBAccess(conn);

      UserData ud = (UserData) vo.getUserData();
      SWDJJBSJ djsj = null;
      try {
        // 获得企业登记基本信息
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
      nbForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
      nbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // 注册地址联系电话
      nbForm.setDjzclxdm(djsj.getDjzclxdm()); // 登记注册类型代码
      nbForm.setSwjgzzjgdm2(djsj.getSwjgzzjgdm()); // 税务机关组织机构代码
      nbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // 税务所代码
      nbForm.setFsdm(CodeConstant.FSDM_SMSB); // 上门申报方式
      nbForm.setNd(getSbnd(nbForm.getSbrq()));

//Update  Start  Zhou kejing 20031212
//      java.util.Date time = TinyTools.addYear( -1, SfDateUtil.getDate(nbForm.getSbrq()));
      java.util.Date time = SfDateUtil.getDate(nbForm.getSbrq());
//Update  End    Zhou kejing 20031212
      // 取得该企业所得税相关的信息
      nbForm.setZsfsdm("");
      nbForm.setGxjsqy(false);
      nbForm.setZssl(QYSDS_SL);

      ServiceProxy proxy = new ServiceProxy();
//Update  Start  Zhou kejing 20031212
//      QysdsSet sdsInfo = proxy.getQysdsInfo(nbForm.getJsjdm(), time);
      Debug.out("税费入口参数，计算机代码=" + nbForm.getJsjdm());
      Debug.out("税费入口参数，申报日期=" + time);
      Debug.out("税费入口参数，税款所属开始日期="
                + SfDateUtil.getDate(nbForm.getSkssksrq()));
      Debug.out("税费入口参数，税款所属结束日期="
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
        nbForm.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // 征收方式
//Insert  Start  Zhou kejing 20040113
        nbForm.setZsfsdm2(sdsInfo.getZsfs().getZsfsdm()); // 征收方式
//Insert  End    Zhou kejing 20040113
        //modified by hazhengze 20051227 Start
        nbForm.setZssl(sdsInfo.getZsfs().getSl());
          System.out.println("================获取企业主税税率1==================" +
                             sdsInfo.getZsfs().getSl());
        //modified by hazhengze 20051227 End
        if (nbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
          System.out.println("================获取企业纯益率开始==================" +
                             nbForm.getJsjdm());
          if (sdsInfo.getZsfs() != null) {
            System.out.println(nbForm.getJsjdm() + "的核定纯益率为" +
                               sdsInfo.getZsfs().getCyl());
            nbForm.setCyl(sdsInfo.getZsfs().getCyl());
          }
          System.out.println("================获取企业纯益率结束==================" +
                             nbForm.getJsjdm());
          // 纯益率征收
          nbForm.setZsfsdm(CodeConstant.ZSFSDM_CYLZS);

          //modified by hazhengze 20051227 Start
          nbForm.setZssl(QYSDS_SL);
          System.out.println("================获取企业主税税率2==================" +
                             sdsInfo.getZsfs().getSl());
          // nbForm.setZssl(sdsInfo.getZsfs().getCyl());
          //modified by hazhengze 20051227 End
//Insert  Start  Zhou kejing 20040113
          nbForm.setZssl2(sdsInfo.getZsfs().getCyl());
//Insert  End    Zhou kejing 20040113
          Debug.out("CYL= " + nbForm.getZssl());
        }
        else if (nbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)) { // 定额征收
          nbForm.setDe(sdsInfo.getZsfs().getDe());
        }
        Debug.out("ZSSL= " + nbForm.getZssl());
        if (nbForm.getZssl() == null) {
          nbForm.setZssl("0.00");
        }
      }

      Debug.out("财产损失扣除额" + sdsInfo.getCcss()); //财产损失扣除额
      Debug.out("弥补以前年度亏损" + sdsInfo.getNbyqndks());
      Debug.out("三新抵扣" + sdsInfo.getSxdk());
      Debug.out("技术改造国产设备投资抵免税额" + sdsInfo.getJsgzgcsb());
      Debug.out("减、免所得税额" + sdsInfo.getYbjme());
      Debug.out("免税的技术转让收益" + sdsInfo.getMsdjxzrsy());
      if ( (sdsInfo.isXzqy()) &&
//Update  End    Zhou kejing 20031212
          (djsj.getDjzclxdm().equals(CodeConstant.JITIQIYE_CODE))) {
        Debug.out("集体企业下的乡镇企业");
        nbForm.setYhbl(CodeConstant.YHBL_QITA);
        nbForm.setXzqyjm(CodeConstant.JM_XIANZHEN);
        nbForm.SetXzqy(true);
      }
      else {
        Debug.out("其它企业");
        nbForm.setYhbl(CodeConstant.YHBL_QITA);
      }

      // 查询该企业是否是高新技术企业
      if (sdsInfo.getGxjsqy() != null) {

        // 设置该企业为高新技术企业
        nbForm.setZsfsdm("");
        nbForm.setGxjsqy(true);
        nbForm.setZssl(CodeConstant.GAOXINQIYESL);

      }

      // 查找是否已有当期所得税申报数据
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
        Debug.out("明细数据" + nbForm.getDataList());
        // 查看是否已有当期联营、股份数据
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
        Debug.out("明细数据" + nbForm.getLygfDataList());
      }
      else {

        nbForm.setSpzg24(sdsInfo.getCcss()); //财产损失扣除额
//                nbForm.setSpzg63(sdsInfo.getNbyqndks()); //弥补以前年度亏损
//                nbForm.setSpzg77(sdsInfo.getSxdk()); //三新抵扣
        nbForm.setSpzg82(sdsInfo.getJsgzgcsb()); //技术改造国产设备投资抵免税额
        nbForm.setSpzg81(sdsInfo.getYbjme()); //减、免所得税额
        nbForm.setSpzg69(sdsInfo.getMsdjxzrsy()); //免税的技术转让收益

      }
    }
    catch (Exception ex) {
      //抛出异常
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    Debug.out("数据长度" + nbForm.getDataList().size());
    System.out.println(nbForm.getJsjdm() + "企业征收方式为" + nbForm.getZsfsdm());
    System.out.println(nbForm.getJsjdm() + "企业征收方式2为" + nbForm.getZsfsdm2());
    return nbForm;
  }

  /**
   * doSave   用于存储页面提交的详尽处理信息
   * @param   vo 业务参数
   * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
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
        // 获得企业登记基本信息
        djsj = InterfaceDj.getJBSJ(nbForm.getJsjdm(), ud);
      }
      catch (Exception ex1) {
        ex1.printStackTrace();
      }
      if (djsj == null) {
        Debug.out("此计算机代码不存在！");
        throw new ApplicationException("此计算机代码不存在！");
      }
      nbForm.setNsrmc(djsj.getNsrmc());
      nbForm.setZcdzlxdh(djsj.getZcdzlxdh());
      nbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // 税务所代码
      nbForm.setZgswjgzzjgdm(djsj.getSwjgzzjgdm());
      nbForm.setFsdm(CodeConstant.FSDM_SMSB); // 上门申报方式
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
       * modified by hazhengze 20060418 start 增加对sfdb.sf_jl_qysdszsfs_his的nsrzt的修改
       */
      try {
          String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"+nbForm.getJsjdm()+"'";
          dbAgent.updateSQL(sql);
        }
        catch (BaseException ex1) {
          //抛出异常
          ex1.printStackTrace();
          new ApplicationException("数据库更新记录失败！"+nbForm.getJsjdm()+":"+ex1.getMessage());
        }
      /**
       * modified by hazhengze 20060418 end
       */
      // 删除已有历史所得税数据
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        dbAgent.delete(strWhere, new Qysdsnb());
      }
      catch (BaseException ex1) {
        //抛出异常
        ex1.printStackTrace();
        new ApplicationException("数据库删除操作失败！");
      }

      // 81行次一般减免金额
      String jmje = "";

      // 保存新的所得税申报数据
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

      // 删除已有联营、股份历史数据
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        dbAgent.delete(strWhere, new Lygf());
      }
      catch (BaseException ex1) {
        //抛出异常
        ex1.printStackTrace();
        new ApplicationException("数据库删除操作失败！");
      }

      // 保存联营、股份新数据
      String lgZb_columns[] = {
          "nd", "jsjdm", "sbrq", "lrr", "nsrmc",
          "qxdm", "skssksrq", "skssjsrq",
          "swjgzzjgdm", "fsdm"};

      List lgResult = nbForm.getLygfDataList();
      for (int i = 0; i < lgResult.size(); i++) {
        HashMap map = (HashMap) lgResult.get(i);
        BeanUtil.copyBeanToMap(lgZb_columns, nbForm, map);
        map.put("xh", String.valueOf(i)); // 本年度企业数据需号？？
        map.put("qylxdh", nbForm.getZcdzlxdh());
        Lygf orLygf = new Lygf();
        BeanUtil.populate(orLygf, map);
        orLygf.setCjrq(ts_cjrq);
        orLygf.setLrrq(ts_lrrq);
        dbAgent.insert(orLygf);
      }
      //减免
//Update  Start  Zhou kejing 20031212
      java.util.Date time = SfDateUtil.getDate(nbForm.getSbrq());
      QysdsSet sdsInfo = proxy.getQysdsInfo(nbForm.getJsjdm(), time,
                                            SfDateUtil.getDate(nbForm.
          getSkssksrq()),
                                            SfDateUtil.getDate(nbForm.
          getSkssjsrq()), CodeConstant.SFGL_QYSDS_BBFS_NB);
//Update  End    Zhou kejing 20031212
      Debug.out("这个计算机代码是否具备一般减免资格" + sdsInfo.getYbjme());
      Debug.out("减免金额" + jmje);
      //BigDecimal jmsp81 = proxy.getyb(nbForm.getJsjdm(), time, dbAgent);
      this.putJm(jmje, sdsInfo, nbForm,
                 dbAgent, ud, proxy,
                 djsj, ts_cjrq,
                 ts_lrrq, ts_sbrq,
                 ts_Skssjsrq,
                 ts_Skssksrq, time);

    }
    catch (Exception ex) {
      //抛出异常
      ex.printStackTrace();
      throw new ApplicationException(ex.getMessage());
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

    return nbForm;
  }

  /**
   * doDelete  用于删除页面提交的详尽处理信息
   * @param    vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
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

      // 删除已有历史所得税数据
      try {
        String strWhere = " jsjdm='" + nbForm.getJsjdm() + "'" +
            " and qxdm='" + nbForm.getQxdm() + "'" +
            " and nd='" + nbForm.getNd() + "'";
        // 删除联营、股份数据
        dbAgent.delete(strWhere, new Lygf());

        // 删除企业所得税年报数据
        dbAgent.delete(strWhere, new Qysdsnb());
        //记录删除日志
        TinyTools.makeLog4Qysds(ud, nbForm.getJsjdm(), "企业所得税年报");
      }
      catch (BaseException ex1) {
        //抛出异常
        ex1.printStackTrace();
        new ApplicationException("数据库删除操作失败！");
      }

      // 重新设置初始化数据
      initForm(nbForm);
      // 清楚已加载数据
      nbForm.getDataList().clear();
    }
    catch (Exception ex) {
      //抛出异常
      ex.printStackTrace();
      throw new ApplicationException("数据库删除操作失败！");
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
    return nbForm;
  }

  /**
   * doUpdate  用于存储页面提交的详尽处理信息
   * @param    vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
   */

  private Object doUpdate(VOPackage vo) throws BaseException {

    QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

    return nbForm;
  }

  /**
   * 初始化
   * @param nbForm 主表数据
   * @throws BaseException 当其他情况都会抛出异常信息
   */

  private void initForm(QysdsnbForm nbForm) throws BaseException {

    nbForm.setSbrq(SfDateUtil.getDate());
    nbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
    nbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
    //nbForm.setZsfsdm(CodeConstant.ZSFSDM_CZZS);
    nbForm.setZsfsdm("");
    nbForm.setZssl(QYSDS_SL);
    // 申报期间
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
   * 计算申报年度
   * @param   sbrq 申报日期
   * @return String 年度
   */

  private String getSbnd(String sbrq) {

    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
    return (String) qj.get(Skssrq.SKSSRQ_ND);
  }

  /**
   * 根据申报日期取得当前前年0101-1231
   * @param curSbrq 申报日期
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
   * 保存减免数据
   * @param jmje 减免金额
   * @param sdsInfo 税费认定信息
   * @param nbForm 录入数据
   * @param dbAgent 数据库链接
   * @param ud 操作员信息
   * @param proxy 接口
   * @param djsj 登记基本信息
   * @param ts_cjrq 创建日期
   * @param ts_lrrq 录入日期
   * @param ts_sbrq 申报日期
   * @param ts_Skssjsrq 税款所属结束日期
   * @param ts_Skssksrq 税款所属开始日期
   * @param time 当前时间
   */
  private void putJm(String jmje, QysdsSet sdsInfo, QysdsnbForm nbForm,
                     SfDBAccess dbAgent, UserData ud, ServiceProxy proxy,
                     SWDJJBSJ djsj, Timestamp ts_cjrq,
                     Timestamp ts_lrrq, Timestamp ts_sbrq,
                     Timestamp ts_Skssjsrq,
                     Timestamp ts_Skssksrq, java.util.Date time) {
    try {
      if (!"".equals(jmje) && sdsInfo.getYbjme() != null) {
//当企业所得税季报和年报的减免字段有值且纳税人有减免资格的时候，要往减免申报表插入数据：
//控制修改：
//如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
//如果本征期已经插入过数据了，且数据记账标识为已记账，则不用再插入数据；
//如果本征期没有插入过数据，则插入一条数据；
//
//且网上减免税申报的时候控制不可录入企业所得税的减免数据，即在申报的税种税目下过滤掉企业所得税的税种税目；

        Debug.out("进入减免");
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

        //不对jzbz进行判断，只有没有减免数据的时候才插入新的数据，如果有减免数据在根据jzbz进行处理
//                vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
//                        + "'");

        vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
        vZb.add("jsjdm='" + nbForm.getJsjdm() + "' ");
        List zbData = dbAgent.query(Jm.class, vZb);
        Debug.out("查到的数据为" + zbData.size());
        if (zbData.size() <= 0) {
          //本征期没有插入过数据，插入一条数据；
          try {
            //删除明细数据
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

            Debug.out("减免数据库删除原有数据");
          }
          catch (BaseException ex1) {
            throw new ApplicationException("数据库操作失败，请您找管理员联系！");
          }
          Ysjc ysjc = null;
          try {
            ysjc = JksUtil.getYsjc(nbForm.getJsjdm(),
                                   CodeConstant.SZSM_QYSDS,
                                   SfDateUtil.getDate(nbForm.
                getSbrq()));
          }
          catch (Exception e) {
            throw new ApplicationException("该计算机代码得预算级次代码没有纪录！");
          }
          if (ysjc != null) {
            Debug.out("级次 =" + ysjc.getYsjcdm());
          }
          else {
            throw new ApplicationException("该计算机代码的预算级次代码没有纪录！");
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
            throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
          }
          if (yskm != null) {
            Debug.out("预算科目 =" + yskm.getYskmdm());
          }
          else {
            throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
          }
          Date date = TinyTools.stringToDate(nbForm.getSbrq(),
                                             "yyyyMMdd");
          //减免税情况接口
//Update  Start  Zhou kejing 20031212
          String jmxmdm = proxy.getJmsbs(nbForm.getJsjdm(),
                                         CodeConstant.SZSM_QYSDS,
//                                         time);
                                         SfDateUtil.getDate(nbForm.getSkssksrq()),
                                         SfDateUtil.getDate(nbForm.getSkssjsrq()));
//Update  End    Zhou kejing 20031212
          Debug.out("减免类别代码 =" + jmxmdm);
          //减免值对象插入
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
            //插入减免表数据
            dbAgent.insert(jm);
          }
          catch (BaseException ex4) {
            throw new ApplicationException("数据库操作失败，请您找管理员联系！");
          }
        }
        else {
          //如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
          Jm jmTemp = (Jm) zbData.get(0);
          if (jmTemp.getJzbz().substring(0,
              1).equals(CodeConstant.SMSB_JZBZ_EDIT)) {
            //未记账，则更新jmse
            jmTemp.setJmse(new BigDecimal(jmje));
            dbAgent.update(jmTemp);
          }

        }
      }

    }
    catch (Exception ex) {
      //抛出异常
      ex.printStackTrace();
    }
  }
}
