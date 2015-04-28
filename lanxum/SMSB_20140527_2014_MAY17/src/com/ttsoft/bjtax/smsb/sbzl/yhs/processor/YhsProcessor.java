/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 印花税年度纳税申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class YhsProcessor
    implements Processor {

  /**
   * 实现Processor接口
   * @param vo 业务参数
   * @return Object VOPackage型数据
   * @throws BaseException 业务异常
   * 		1 当传过来的操作类型不对时抛出
   * 		2 当调用的业务方法抛出业务异常时向上传递抛出
   * 	其他异常抛出由EJB的process方法处理。
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
   * doShow初始化对象页面信息要素
   * @param vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException 当其他情况都会抛出异常信息
   */
  private Object doShow(VOPackage vo) throws BaseException {
    Connection conn = null;
    SfDBAccess da = null;
    YhsForm yhsForm = (YhsForm) vo.getData();

    initForm(yhsForm);

    try {
      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);

      //取税种税目名称
      Vector condition = new Vector();
      condition.add(" szsmdm like '16%' ");
      condition.add(" szsmdm > '160092' ");
      List list = da.query(com.ttsoft.bjtax.shenbao.model.domain.Szsm.class,
                           condition);
      String[] columns = {
          "szsmdm", "szsmmc", "sl"};
      List mxMapData = new ArrayList();
      for (int i = 0; i < list.size(); i++) {
        //获得明细值
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
   * doQuery    用于返回页面索要查询的详尽信息
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   *
   */
  private Object doQuery(VOPackage vo) throws BaseException {

    YhsForm yhsForm = (YhsForm) vo.getData();
    SWDJJBSJ djsj = null;
    try {
      // 获得企业登记基本信息
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
      Debug.out("此计算机代码不存在！");
      throw new ApplicationException("此计算机代码不存在！");
    }
    yhsForm.setNsrmc(djsj.getNsrmc()); //得到纳税人 单位名称
    yhsForm.setQylxdh(djsj.getZcdzlxdh()); //得到纳税人 联系电话 (注册地址联系电话)
    yhsForm.setNd(getSbnd(yhsForm.getSbrq()));

    Connection conn = null;
    SfDBAccess da = null;
    try {

      conn = SfDBResource.getConnection();
      da = new SfDBAccess(conn);

      setSzsm(da, yhsForm);

      // 主表字段
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
        //日期赋值
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

      //获得申报明细值
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
   * doSave   用于存储页面提交的详尽处理信息，保存印花税年度纳税申报表
   * @param   vo 业务参数
   * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
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
                new Yhsbgmx()); //删除明细表数据
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'" +
                " and jsjdm= '" + yhsForm.getJsjdm() +
                "' and nd= '" + yhsForm.getNd() +
                "'", new Yhsbgz()); ; //删除主表数据

      yhsForm.setSwjgzzjgdm2(jbsj.getSwjgzzjgdm());
      yhsForm.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
      yhsForm.setFsdm(CodeConstant.FSDM_SMSB);
      yhsForm.setQylxdh(jbsj.getDjzclxdm()); //企业联系电话为注册地址联系电话？

      String zbNames[] = {
          "jsjdm", "nd", "skssksrq", "skssjsrq", "hjfs",
          "hjjsje", "hjynse", "swjgzzjgdm", "lrr", "fsdm",
          "sbrq"};
      Map map = new HashMap();
      BeanUtil.copyBeanToMap(zbNames, yhsForm, map);
      Yhsbgz orZb = new Yhsbgz(); //ormapping 主表值对象
      BeanUtil.populate(orZb, map); //对主表值对象赋值
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

      //数据插入明细表
      List mxdatalist = yhsForm.getDataList();
      List mxlist = new ArrayList();
      Vector condition = new Vector();
      for (int i = 0; i < mxdatalist.size(); i++) {
        Map mxMap = (Map) mxdatalist.get(i);
        //添加一些外键信息
        mxMap.put("szdm", "16");
        mxMap.put("jsjdm", yhsForm.getJsjdm());
        mxMap.put("nd", yhsForm.getNd());
        mxMap.put("swjgzzjgdm", yhsForm.getSwjgzzjgdm());

        Yhsbgmx orMx = new Yhsbgmx();
        BeanUtil.populate(orMx, mxMap); //将数据传递给明细值对象
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
      da.insert(mxlist); //插入明细表数据

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
   * doDelete  用于删除页面提交的详尽处理信息
   * @param    vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
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

      //删除明细表数据
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'"
                + " and jsjdm= '" + jsjdmd + "' and nd= '"
                + yhsForm.getNd() + "'",
                new Yhsbgmx());

      //删除主表数据
      da.delete("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'"
                + " and jsjdm= '" + jsjdmd + "' and nd= '"
                + yhsForm.getNd() + "'",
                new Yhsbgz());

      setSzsm(da, yhsForm);
    }
    catch (BaseException ex2) {
      throw new ApplicationException("数据库操作失败，请您找管理员联系！");
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

    return null;
  }

  /**
   * 设置印花税税种税目
   * @param  da 税据库连接
   * @param  yhsForm 主表数据
   * @throws BaseException 当其他情况都会抛出异常信息
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
        //获得明细值
        Szsm orSm = (Szsm) list.get(i);
        Map map = new HashMap();
        BeanUtil.copyBeanToMap(szsm_columns, orSm, map);
        yhsForm.getDataList().add(map);
        //mxMapData.add(map);
      }
    }
    catch (Exception ex2) {
      throw new ApplicationException("设置印花税税种税目失败！");
    }
  }

  /**
   * 设置收入申报期间、申报日期
   * @param zbForm zbForm
   * @throws BaseException 当其他情况都会抛出异常信息
   */
  private void initForm(YhsForm zbForm) throws BaseException {

    zbForm.setSbrq(SfDateUtil.getDate());
    // 申报期间
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
   * 计算申报年度
   * @param sbrq 申报日期
   * @return String ND
   */
  private String getSbnd(String sbrq) {

    Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
    return (String) qj.get(Skssrq.SKSSRQ_ND);
  }

}
