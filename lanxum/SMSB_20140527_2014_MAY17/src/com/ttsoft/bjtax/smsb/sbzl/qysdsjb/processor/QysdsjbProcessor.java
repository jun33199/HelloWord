/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.QysdsjbHelper;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web.QysdsjbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税季报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbProcessor
    implements Processor
{

  private java.util.Date time;

  /**
   * 实现Processor接口
   * @param vo 业务参数
   * @return Object VOPackage型数据
   * @throws BaseException 业务异常
   * 		1 当传过来的操作类型不对时抛出
   * 		2 当调用的业务方法抛出业务异常时向上传递抛出
   * 	其他异常抛出由EJB的process方法处理。
   */

  public Object process(VOPackage vo) throws BaseException
  {
    //回传对象
    Object result = null;
    //判断VO是否为空
    if (vo == null)
    {
      throw new NullPointerException();
    }
    //根据Action的值调用不同的process方法
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_SHOWACTION: //前台默认显示
        result = doShow(vo);
        break;
      case CodeConstant.SMSB_QUERYACTION: //查询
        result = doQuery(vo);
        break;
      case CodeConstant.SMSB_SAVEACTION: //存储
        doSave(vo);
        break;
      case CodeConstant.SMSB_DELETEACTION: //删除
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

  public Object doShow(VOPackage vo) throws BaseException
  {
    //得到Action传递过来CzzsjdForm对象
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //得到当前时间的所属月
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(new Date());
    int month = calendar.get(calendar.MONTH);
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
//    Map getsbjd = Skssrq.otherSkssrq(curTime);
    Map getsbjd = this.quarterSkssrq(curTime);
    String nd = (String) getsbjd.get(Skssrq.SKSSRQ_ND);
    Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
    Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
    //税款所属开始日期
    form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
    //税款所属结束日期
    form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
    // }
    form.setSbrq(SfDateUtil.getDate());
    //Helper放入dataList中
    List dataList = QysdsjbHelper.getShowList();
    form.setDataList(dataList);
    form.setNsrzt("99");
    System.out.println(dataList);
    return form;
  }

  /**
   * doQuery    用于返回页面索要查询的详尽信息
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   *
   */

  public Object doQuery(VOPackage vo) throws BaseException
  {
    //取得form中的对象
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //UserData数据提取
    UserData ud = (UserData) vo.getUserData();
    //登记基本信息
    SWDJJBSJ djxx = null;
    try
    {
      //登记基本信息
      /* start added by huxiaofeng 2005.8.16*/
      //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
      djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
      form.setNsrzt(djxx.getNsrzt());
      /* end added by huxiaofeng 2005.8.16*/

      //取得区县代码
      form.setQxdm(InterfaceDj.getQxdm(ud));
      System.out.println(InterfaceDj.getQxdm(ud));
    }
    catch (Exception ex1)
    {
      //在异常下重新设置页面显示
      List dataList = QysdsjbHelper.getShowList();
      form.setDataList(dataList);
      throw ExceptionUtil.getBaseException(ex1);
    }
    Connection conn = null;
    //系统当前时间，为了在原有数据库中无纪录做时间准备
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
    try
    {
      //数据库连接
      conn = SfDBResource.getConnection();
      //使用OrMap操作数据库的封装类
      SfDBAccess da = new SfDBAccess(conn);
      //查询主表数据
      Vector vZb = new Vector();

      //主表OrMapping值对象的每个参数
      String zbNames[] =
          {
          "jkpzh", "qxdm", "lrrq",
          "fsdm", "jd", "jsjdm", "lrr", "nd", "sbrq",
          "skssjsrq", "skssksrq", "swjgzzjgdm"};
      //申报时间
//      Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(form.getSbrq()));
      //季度
//      String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"的jd："+jd);
//      String nd = this.getNd(jd, getsbnd, form.getSbrq());
//		从页面中取得税款所属期和年度
		String nd = form.getSkssksrq().substring(0, 4);

      //从登记信息中提取部分数据到form中
      BeanUtil.copyBeanToBean(zbNames, djxx, form);
      form.setNsrmc(djxx.getNsrmc());
      
//      java.util.Date time = SfDateUtil.getDate(form.getSbrq());
//
//      Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(form.
//          getSkssksrq(), "yyyyMMdd").getTime());
//      Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(form.
//          getSkssjsrq(), "yyyyMMdd").getTime());
//      System.out.println("skssksrq = " + skssksrq);
//      System.out.println("skssjsrq = " + skssjsrq);
      
		//从申报页面取得申报日期和税款所属日期
		Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());
      Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());
		
		
		System.out.println(form.getJsjdm()+"sbrq："+sbrq);
		System.out.println(form.getJsjdm()+"skssksrq："+skssksrq);
		System.out.println(form.getJsjdm()+"skssjsrq："+skssjsrq);
      
      
      //税费接口
      ServiceProxy proxy = new ServiceProxy();
      //企业信息接口
//      QysdsSet sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq,
//                                            skssksrq,
//                                            skssjsrq,CodeConstant.SFGL_QYSDS_BBFS_JB);
      QysdsSet sdsInfo = null;
      if("4".equals(jd)){
			/*如果为第四季度，或取企业所得税认定信息时按年报来获取*/
    	  sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_NB);
		}else{
			sdsInfo = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_JB);
		}
      
      
      //高新企业优先----此规则已修改：高新企业不再优先 要与征收方式平等处理（既没有相互制约关系）zhu guanglin
      System.out.println("高新企业" + sdsInfo.getGxjsqy());
      if (sdsInfo.getGxjsqy() != null)
      {
        //传递高新企业的税率0.15
        form.setZssl(CodeConstant.GAOXINQIYESL);
        //传递高新企业的方式，方便页面js区分计算
        StringBuffer proStr = new StringBuffer(form.getPromptStr());
        proStr.append("<li>该企业具有高新技术企业认定；");
        form.setPromptStr(proStr.toString());

      }
      else
      {
        form.setZssl("");
      }

      //企业征收方式查找
      //有企业征收方式
      if (sdsInfo.getZsfs() != null
          && sdsInfo.getZsfs().getZsfsdm() != "")
      {
        System.out.println("企业征收方式代码" + sdsInfo.getZsfs().getZsfsdm());
        System.out.println("纯益率检查");
        //纯益率方式
        if (sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
                                                 ZSFSDM_CYLZS))
        {
          form.setZsfs(sdsInfo.getZsfs().getZsfsdm());
          System.out.println("纯益率数值为" + sdsInfo.getZsfs().getCyl());
          form.setCyl(sdsInfo.getZsfs().getCyl());
          StringBuffer proStr = new StringBuffer(form.getPromptStr());
          proStr.append("<li>纯益率征收方式：纯益率为" + form.getCyl() + "；");
          form.setPromptStr(proStr.toString());

        }
        else
        {
          form.setCyl("");
        }
        //定额方式
        System.out.println("定额检查");
        if (sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
                                                 ZSFSDM_DEZS))
        {
          form.setZsfs(sdsInfo.getZsfs().getZsfsdm());
          System.out.println("定额数值为" + sdsInfo.getZsfs().getDe());
          form.setDe(sdsInfo.getZsfs().getDe());
          StringBuffer proStr = new StringBuffer(form.getPromptStr());
          proStr.append("<li>定额征收方式：单位税额为" + form.getDe() +
                        "，则本期应纳所得税额（行次6）=单位税额×当期生产数量或人数；");
          form.setPromptStr(proStr.toString());
        }
        else
        {
          form.setDe("");
        }
        //非纯益率方式、非定额方式
        if ( (!sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
            ZSFSDM_CYLZS) &&
              !sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.
            ZSFSDM_DEZS)))
        {
          form.setZsfs(CodeConstant.QITAQIYE);
        }
      }
      //无企业征收方式
      if (sdsInfo.getZsfs() == null
          || sdsInfo.getZsfs().getZsfsdm() == "")
      {
        //非纯益率方式、非定额方式
        form.setZsfs(CodeConstant.QITAQIYE);
      }
      //集体企业下的乡镇企业要*1
      //2003 12.25 不做*0.9了
      if ( (sdsInfo.isXzqy()) )
      {
        form.setYhbl(CodeConstant.YHBL_QITA);
        form.setXzqyjm(CodeConstant.XZQYBS);
      }
      else
      {
        //非集体企业下的乡镇企业要*1
        form.setYhbl(CodeConstant.YHBL_QITA);
        form.setXzqyjm("");
      }

      //调用企业信息应用大接口调出一般减免额
//      java.util.Date times = TinyTools.addMonth(0,
//                                                SfDateUtil.getDate(form.
//          getSbrq()));
//      QysdsSet sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), times,
//                                             skssksrq,
//                                             skssjsrq,CodeConstant.SFGL_QYSDS_BBFS_JB);
      
      
      QysdsSet sdsInfos = null;
      if("4".equals(jd)){
			/*如果为第四季度，或取企业所得税认定信息时按年报来获取*/
    	  sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_NB);
		}else{
			sdsInfos = proxy.getQysdsInfo(form.getJsjdm(), sbrq, skssksrq, skssjsrq,
					CodeConstant.SFGL_QYSDS_BBFS_JB);
		}

      //查询条件设置
      vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      vZb.add(" jd='" + jd + "'");
      vZb.add("nd='" + nd + "'");
      vZb.add(" jsjdm='" + form.getJsjdm() + "'");
      List zbData = da.query(Qysdsjd.class, vZb);
      if (zbData.size() <= 0)
      {
        //无数据
        if (djxx == null)
        {
          System.out.println("此计算机代码不存在！");
          List dataList = QysdsjbHelper.getShowList();
          form.setDataList(dataList);
          throw new ApplicationException("此计算机代码不存在！");
        }
        //从登记接口提取部分信息提取到页面上
        BeanUtil.copyBeanToBean(zbNames, djxx, form);
        form.setNsrmc(djxx.getNsrmc());
        form.setCjrq(TinyTools.Date2String(curTime,
                                           CodeConstant.DATETYPE));

        //是否得到减免资格

        System.out.println("查询的时候一般减免比率是" + sdsInfos.getYbjmsl());
        if (sdsInfos.getYbjmsl() != null)
        {
          //得到减免金额
          if (! (sdsInfo.isXzqy()))
          {
            //具有一般纳税单位减免减免税认定，并且该纳税人不属于乡镇企业
            String jmsl = sdsInfos.getYbjmsl().toString();
            form.setJmsl(jmsl);
          }
        }

        //页面初始化设置
        List dataList = QysdsjbHelper.getShowList();
        form.setDataList(dataList);
        System.out.println(dataList);
        return form;
      } //end if

      //已有保存的申报数据

      //将登记部分信息显示到前台页面上
      BeanUtil.copyBeanToBean(zbNames, djxx, form);
      //得到数据库里数据
      Qysdsjd orZb = (Qysdsjd) zbData.get(0);
      //显示到前台页面上
      BeanUtil.copyBeanToBean(zbNames, orZb, form);
      form.setCjrq(String.valueOf(orZb.getCjrq()));
      //明细和主表数据在一起
      //明细OrMapping值对象的每个参数
      String mxNames[] =
          {
          "srze", "lrze", "sl", "ynsdse", "qcwjsdse",
          "jmsdse", "cbyqndse", "sjyjsdsse", "bqyjsdse",
          "sjybsdse", "mbyqndks", "bkhlrze"};
      //将OrMapping值对象转成Map，以便ActionForm能够使用
      List mxMapData = new ArrayList();
      //获得此条明细值
      Qysdsjd orMx = (Qysdsjd) zbData.get(0);
      Map map = new HashMap();
      //将值对象的值赋给Map
      BeanUtil.copyBeanToMap(mxNames, orMx, map);
      Object jmsdse = map.get("jmsdse");
      List dataList = QysdsjbHelper.getShowList();
      //生成完整的页面显示数据
      //begin modify by shiyanfeng 20050203
      mxMapData = this.addData(map, dataList);
      //end modify by shiyanfeng 20050203
      form.setDataList(mxMapData);
      form.setCjrq(form.getCjrq());
      form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
      form.setNsrmc(djxx.getNsrmc());

      System.out.println("查询的时候一般减免比率是" + sdsInfos.getYbjmsl());
      if (sdsInfos.getYbjmsl() != null)
      {
        //得到减免金额
        if (! (sdsInfo.isXzqy() ))
        {
          //具有一般纳税单位减免减免税认定，并且该纳税人不属于乡镇企业
          String jmsl = sdsInfos.getYbjmsl().toString();
          form.setJmsl(jmsl);
        }
      }

      return form;
    }
    catch (Exception ex)
    {
      //抛出异常
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //释放数据库连接
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * doSave   用于存储页面提交的详尽处理信息
   * @param   vo 业务参数
   * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
   */

  public Object doSave(VOPackage vo) throws BaseException
  {
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    //登记基本信息
    SWDJJBSJ djxx = null;
    UserData ud = (UserData) vo.getUserData();
    try
    {
      /* start added by huxiaofeng 2005.8.16*/
      //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
      djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
      /* end added by huxiaofeng 2005.8.16*/

    }
    catch (Exception ex1)
    {
      throw ExceptionUtil.getBaseException(ex1);
    }
    Connection conn = null;
    Timestamp curTime = new Timestamp(System.currentTimeMillis());
//    Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(form.
//        getSkssksrq(), "yyyyMMdd").getTime());
//    Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(form.
//        getSkssjsrq(), "yyyyMMdd").getTime());
    
//  从申报页面取得申报日期和税款所属日期
	Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());
  Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
	Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());
	
	
	System.out.println(form.getJsjdm()+"sbrq："+sbrq);
	System.out.println(form.getJsjdm()+"skssksrq："+skssksrq);
	System.out.println(form.getJsjdm()+"skssjsrq："+skssjsrq);
	
    try
    {
      //数据库连接
      conn = SfDBResource.getConnection();
      //使用OrMap操作数据库的封装类
      SfDBAccess da = new SfDBAccess(conn);
      //查询主表数据
//      Map getsbjd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbjd = this.quarterSkssrq(SfDateUtil.getDate(form.getSbrq()));
      //季度
//      String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
//      String jd = Skssrq.preQuarter(TinyTools.addDay(1,SfDateUtil.getDate(form
//				.getSkssjsrq())));
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"的jd："+jd);
      //年度
//      String nd = this.getNd(jd, getsbjd, form.getSbrq());
//	从页面中取得税款所属期和年度
	String nd = form.getSkssksrq().substring(0, 4);
  System.out.println("nd =" + nd);
      String SQL = " jsjdm='" + form.getJsjdm() + "' and nd='" + nd +
          "' and jd='" + jd + "'";
      //删除原先的记录
      try
      {
        //删除数据
        da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) + "' and nd='"
                  + nd +
                  "' and jd='" + jd + "'" + "and jsjdm='" +
                  form.getJsjdm() + "'",
                  new Qysdsjd());
      }
      catch (BaseException ex1)
      {
        throw new ApplicationException(" 数据库操作失败，请您找管理员联系！");
      }
      String zbNames[] =
          {
          "jkpzh",
          "jsjdm", "sbrq",
          "skssjsrq", "skssksrq"};
      List mxMapData = form.getDataList();
      //存贮明细数据值对象
      List mxData = new ArrayList();
      Map map = (Map) mxMapData.get(0);
      //将主表的数据添加进来
      //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
      BeanUtil.copyBeanToMap(zbNames, form, map);
      Qysdsjd orMx = new Qysdsjd();
      //将数据传递给明细值对象
      BeanUtil.populate(orMx, map);
      //其它数据插入值对象中
      Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                                         CodeConstant.DATETYPE);
      Timestamp cjrqmx = new Timestamp(cjrq.getTime());
      orMx.setCjrq(cjrqmx);
      orMx.setLrrq(curTime);
      orMx.setJd(jd);
      orMx.setNd(nd);
      orMx.setFsdm(CodeConstant.FSDM_SMSB);
      orMx.setQxdm(InterfaceDj.getQxdm(ud));
      orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
      orMx.setLrr(ud.getYhid());
      String jmje = orMx.getJmsdse().toString();
      mxData.add(orMx);
      try
      {
        //插入明细表数据
        da.insert(mxData);
      }
      catch (BaseException ex4)
      {
        throw new ApplicationException("数据库操作失败，请您找管理员联系！");
      }
      //设置插入一般减免方法的入口参数
      Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(form.
          getCjrq(),
          CodeConstant.DATETYPE).getTime());
      Timestamp ts_lrrq = new Timestamp(curTime.getTime());
      Timestamp ts_sbrq = new Timestamp(TinyTools.stringToDate(form.
          getSbrq(),
          "yyyyMMdd").getTime());
      Timestamp ts_Skssksrq = skssksrq;
      Timestamp ts_Skssjsrq = skssjsrq;

      //税费接口再次检查是否具备一般减免资格
      //接口参数：日期的上月是否在减免终止日期内

      //将一般减免插入减免申报方法
      this.putJm(jmje, form,
                 da, ud,
                 djxx, ts_cjrq,
                 ts_lrrq, ts_sbrq,
                 ts_Skssjsrq,
                 ts_Skssksrq, nd);

      return null;
    }
    catch (Exception ex)
    {
      //抛出异常
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //释放数据库连接
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * doDelete  用于删除页面提交的详尽处理信息
   * @param    vo 业务参数
   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
   * @throws BaseException 当其他情况都会抛出异常信息
   */
  public Object doDelete(VOPackage vo) throws BaseException
  {
    //得到Action传递过来ActionForm对象
    QysdsjbForm form = (QysdsjbForm) vo.getData();
    UserData ud = (UserData) vo.getUserData();
    //建立数据库连接
    Connection conn = null;
    try
    {
      conn = SfDBResource.getConnection();
      //使用OrMap操作数据库的封装类
      SfDBAccess da = new SfDBAccess(conn);
//      Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
//      Map getsbnd = this.quarterSkssrq(TinyTools.addDay(1,SfDateUtil.getDate(form
//				.getSkssjsrq())));
      //季度
      String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(form
				.getSkssjsrq()));
      System.out.println(form.getJsjdm()+"的jd："+jd);
      //年度
//      String nd = this.getNd(jd, getsbnd, form.getSbrq());
//		从页面中取得税款所属期和年度
		String nd = form.getSkssksrq().substring(0, 4);
      System.out.println("nd =" + nd);

      //删除原先的记录
      try
      {
        //删除明细数据
        da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) + "' and nd='"
                  + nd +
                  "' and jd='" + jd + "'" + "and jsjdm='" +
                  form.getJsjdm() + "'",
                  new Qysdsjd());
        //记录删除日志
        TinyTools.makeLog4Qysds(ud, form.getJsjdm(), "企业所得税季报");
      }
      catch (BaseException ex1)
      {
        throw new ApplicationException("明细数据库出现错误，请找管理员！");
      }
      return null;
    }
    catch (Exception ex)
    {
      //抛出异常
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally
    {
      //释放数据库连接
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 根据季度求得所属年度的方法
   * @param jd 季度
   * @param getsbnd 申报年度
   * @param sbrq 申报日期
   * @return 年度
   */
  private String getNd(String jd, Map getsbnd, String sbrq)
  {
    String nd;
    int year;
    if (jd.equals("4"))
    {
      nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
      System.out.println("nds =" + nd);
    }
    else
    {
      nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
    }
    System.out.println("nd =" + nd);
    return nd;
  }

  /**
   * 根据申报日期取得当前前年0101-1231
   * @param curSbrq 申报日期
   * @return dateMap
   */
  private Map getSbrl(String curSbrq)
  {
    Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(sbrq);
    calendar.add(calendar.MONTH, -1);
    int month = calendar.get(calendar.MONTH);
    int year = calendar.get(calendar.YEAR);
    String nd = new Integer(year).toString();
    int maxDate = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    Timestamp ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).
                                   getTime().getTime());
    Timestamp jsrq = new Timestamp(new GregorianCalendar(year,
        month, maxDate).getTime().getTime());
    Map dateMap = new HashMap();
    dateMap.put("ksrq", ksrq);
    dateMap.put("jsrq", jsrq);
    return dateMap;
  }

  /**
   * 减免操作
   * @param jmje 减免金额
   * @param form 申报信息
   * @param dbAgent 数据库连接
   * @param ud 操作员信息
   * @param djsj 登记信息
   * @param ts_cjrq 创建日起
   * @param ts_lrrq 录入日起
   * @param ts_sbrq 申报日期
   * @param ts_Skssjsrq 税款所属结束日期
   * @param ts_Skssksrq 税款所属开始日期
   * @param nd 年度
   */
  private void putJm(String jmje, QysdsjbForm form,
                     SfDBAccess dbAgent, UserData ud,
                     SWDJJBSJ djsj, Timestamp ts_cjrq,
                     Timestamp ts_lrrq, Timestamp ts_sbrq,
                     Timestamp ts_Skssjsrq,
                     Timestamp ts_Skssksrq, String nd)
  {
    try
    {
//当企业所得税季报和年报的减免字段有值且纳税人有减免资格的时候，要往减免申报表插入数据：
//控制修改：
//如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
//如果本征期已经插入过数据了，且数据记账标识为已记账，则不用再插入数据；
//如果本征期没有插入过数据，则插入一条数据；
//
//且网上减免税申报的时候控制不可录入企业所得税的减免数据，即在申报的税种税目下过滤掉企业所得税的税种税目；
      //当页面数据的减免数额不为空或0并且具备一般减免资格的进入
      if (!"0".equals(jmje) && !"".equals(jmje) && form.getJmsl() != null &&
          !form.getJmsl().equals(""))
      {

        Jm jm = new Jm();
        //设置时间参数
        Map dateMap = getSbrl(form.getSbrq());
        Vector vZb = new Vector();
        //查询条件
        vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
        vZb.add("SKSSKSRQ = to_date('" +
                String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        vZb.add("SKSSJSRQ = to_date('" +
                String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                "','yyyy-MM-dd')");
        //不对jzbz进行判断，只有没有减免数据的时候才插入新的数据，如果有减免数据在根据jzbz进行处理
//                vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
//                        + "'");
        vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB +
                "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') ");
        vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
        vZb.add("jsjdm='" + form.getJsjdm() + "' ");
        List zbData = dbAgent.query(Jm.class, vZb);
        if (zbData.size() <= 0)
        {
          try
          {
            //删除减免数据

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
                           form.getJsjdm() + "'"
                           ,
                           new Jm());

            System.out.println("减免数据库删除原有数据");
          }
          catch (BaseException ex1)
          {
            throw new ApplicationException("数据库操作失败，请您找管理员联系！");
          }
          //取得预算级次代码
          Ysjc ysjc = null;
          try
          {
            ysjc = JksUtil.getYsjc(form.getJsjdm(),
                                   CodeConstant.SZSM_QYSDS,
                                   SfDateUtil.getDate(form.getSbrq()));
          }
          catch (Exception e)
          {
            throw new ApplicationException("该计算机代码得预算级次代码没有纪录！");
          }
          if (ysjc != null)
          {
            System.out.println("级次 =" + ysjc.getYsjcdm());
          }
          else
          {
            throw new ApplicationException("该计算机代码的预算级次代码没有纪录！");
          }
          //取得预算科目代码
          com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
          try
          {
            yskm = JKAdapter.getInstance().getYskm(CodeConstant.
                SZSM_QYSDS,
                djsj.getDjzclxdm(),
                djsj.getGjbzhydm(),
                ysjc.getYsjcdm());
          }
          catch (Exception e)
          {
            throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
          }
          if (yskm != null)
          {
            System.out.println("预算科目 =" + yskm.getYskmdm());
          }
          else
          {
            throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
          }
          Date date = TinyTools.stringToDate(form.getSbrq(),
                                             "yyyyMMdd");
          //减免类别代码
          ServiceProxy proxy = new ServiceProxy();
          String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
                                         CodeConstant.SZSM_QYSDS,
                                         ts_Skssksrq, ts_Skssjsrq);
          System.out.println("减免类别代码 =" + jmxmdm);
          //减免值对象插入
          jm.setCjrq(ts_cjrq);
          jm.setJsjdm(form.getJsjdm());
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
          jm.setNd(nd);
          jm.setYsjcdm(ysjc.getYsjcdm());
          jm.setYskmdm(yskm.getYskmdm());
          try
          {
            //插入减免表数据
            dbAgent.insert(jm);
          }
          catch (BaseException ex4)
          {
            throw new ApplicationException("数据库操作失败，请您找管理员联系！");
          }
        }
        else
        {
          //如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
          Jm jmTemp = (Jm) zbData.get(0);
          if (jmTemp.getJzbz().substring(0,
                                         1).equals(CodeConstant.SMSB_JZBZ_EDIT))
          {
            //未记账，则更新jmse
            jmTemp.setJmse(new BigDecimal(jmje));
            dbAgent.update(jmTemp);
          }

        }

      }

    }
    catch (Exception ex)
    {
      //抛出异常
      ex.printStackTrace();
    }
  }

  /**
   * 如果该用户已经有该季度申报数据，则需要生成相应的数据列表
   * 完整的页面显示数据包括行次信息、项目名称、本期累计数
   * @param map 已经保存数据
   * @param dataList 页面显示项目列表
   * @return 包含已申报数据列表
   */
  private List addData(Map map, List dataList)
  {
    List mxMapData = new ArrayList();
    /* begin of 1 */
    Object srze = map.get("srze");
    if (srze == null)
    {
      srze = "0";
    }
    Map srzes = (Map) dataList.get(0);
    Map map1 = new HashMap();
    map1.putAll(srzes);
    map1.put("srze", srze);
    mxMapData.add(map1);
    /* end of 1  */
    /* begin of 2 */
    Object lrze = map.get("lrze");
    if (lrze == null)
    {
      lrze = "0";
    }
    Map lrzes = (Map) dataList.get(1);
    Map map2 = new HashMap();
    map2.putAll(lrzes);
    map2.put("lrze", lrze);
    mxMapData.add(map2);
    /* end of 2  */

    //begin of modified by shiyanfeng
    /* begin of 3 */
    Object mbyqndks = map.get("mbyqndks");
    if (mbyqndks == null)
    {
      mbyqndks = "0";
    }
    Map mbyqndkss = (Map) dataList.get(2);
    Map map11 = new HashMap();
    map11.putAll(mbyqndkss);
    map11.put("mbyqndks", mbyqndks);
    mxMapData.add(map11);
    /* end of 3  */
    /* begin of 4 */
    Object bkhlrze = map.get("bkhlrze");
    if (bkhlrze == null)
    {
      bkhlrze = "0";
    }
    Map bkhlrzes = (Map) dataList.get(3);
    Map map12 = new HashMap();
    map12.putAll(bkhlrzes);
    map12.put("bkhlrze", bkhlrze);
    mxMapData.add(map12);
    /* end of 4  */

    //end of modified by shiyanfeng

    /* begin of 5 */
    Object sl = map.get("sl");
    if (sl == null)
    {
      sl = "";
    }
    Map sls = (Map) dataList.get(4);
    Map map3 = new HashMap();
    map3.putAll(sls);
    map3.put("sl", sl);
    mxMapData.add(map3);
    /* end of 5  */

    /* begin of 6 */
    Object ynsdse = map.get("ynsdse");
    if (ynsdse == null)
    {
      ynsdse = "0";
    }
    Map ynsdses = (Map) dataList.get(5);
    Map map4 = new HashMap();
    map4.putAll(ynsdses);
    map4.put("ynsdse", ynsdse);
    mxMapData.add(map4);
    /* end of 6  */
    /* begin of 7 */
    Object qcwjsdse = map.get("qcwjsdse");
    if (qcwjsdse == null)
    {
      qcwjsdse = "0";
    }
    Map qcwjsdses = (Map) dataList.get(6);
    Map map5 = new HashMap();
    map5.putAll(qcwjsdses);
    map5.put("qcwjsdse", qcwjsdse);
    mxMapData.add(map5);
    /* end of 7  */
    /* begin of 8 */
    Object jmsdse = map.get("jmsdse");
    if (jmsdse == null)
    {
      jmsdse = "0";
    }
    Map jmsdses = (Map) dataList.get(7);
    Map map6 = new HashMap();
    map6.putAll(jmsdses);
    map6.put("jmsdse", jmsdse);
    mxMapData.add(map6);
    /* end of 8  */
    /* begin of 9 */
    Object cbyqndse = map.get("cbyqndse");
    if (cbyqndse == null)
    {
      cbyqndse = "0";
    }
    Map cbyqndses = (Map) dataList.get(8);
    Map map7 = new HashMap();
    map7.putAll(cbyqndses);
    map7.put("cbyqndse", cbyqndse);
    mxMapData.add(map7);
    /* end of 9  */
    /* begin of 10 */
    Object sjyjsdsse = map.get("sjyjsdsse");
    if (sjyjsdsse == null)
    {
      sjyjsdsse = "0";
    }
    Map sjyjsdsses = (Map) dataList.get(9);
    Map map8 = new HashMap();
    map8.putAll(sjyjsdsses);
    map8.put("sjyjsdsse", sjyjsdsse);
    mxMapData.add(map8);
    /* end of 10  */
    /* begin of 11 */
    Object bqyjsdse = map.get("bqyjsdse");
    if (bqyjsdse == null)
    {
      bqyjsdse = "0";
    }
    Map bqyjsdses = (Map) dataList.get(10);
    Map map9 = new HashMap();
    map9.putAll(bqyjsdses);
    map9.put("bqyjsdse", bqyjsdse);
    mxMapData.add(map9);
    /* end of 11  */
    /* begin of 12 */
    Object sjybsdse = map.get("sjybsdse");
    if (sjybsdse == null)
    {
      sjybsdse = "0";
    }
    Map sjybsdses = (Map) dataList.get(11);
    Map map10 = new HashMap();
    map10.putAll(sjybsdses);
    map10.put("sjybsdse", sjybsdse);
    mxMapData.add(map10);

    return mxMapData;
  }

  /**
    * 计算季报类型的税款所属日期
    * @param curDate 日期
    * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
    *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
    *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
    */
   public  Map quarterSkssrq (Date curDate)
   {
       GregorianCalendar calendar = new GregorianCalendar();
       calendar.setTime(curDate);
       int month = calendar.get(calendar.MONTH);
       int year = calendar.get(calendar.YEAR);

       int jd = month / 3 ;
       if (jd==0) {
         year--;
         jd=4;
       }
       String nd = new Integer(year).toString();
       Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
           0, 1).getTime().getTime());
       Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
           (jd - 1) * 3 + 2,
           new GregorianCalendar(year, (jd - 1) * 3 + 2,
                                 1).getActualMaximum(calendar.
           DAY_OF_MONTH)
           ).getTime().getTime());
       Map retMap = new HashMap();
       retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
       retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
       retMap.put(Skssrq.SKSSRQ_ND, nd);
       return retMap;
   }
//   public static void main(String[] args) {
//       QysdsjbProcessor t = new QysdsjbProcessor();
//       System.out.println(t.quarterSkssrq(SfDateUtil.getDate("20051001")));
//     }

}
