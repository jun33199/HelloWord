/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.pgbs.web.ZhsbPgbsjkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import java.util.Vector;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现综合申报_评估补税功能：包括缴款书录入，维护。</p>
 * @author zzb  20090330
 * @version 1.1
 */

public class ZhsbPgbsjkswhProcessor
    implements Processor
{
  public ZhsbPgbsjkswhProcessor()
  {
  }

  //private static final String YPYS = "Ypys"; //一票一税的连接名
  //private static final String YPDS = "Ypds"; //一票多税的连接名
  /**
   * 实现Processor接口
   * @param vo 业务参数
   * @return Object VOPackage型数据
   * @throws BaseException 业务异常
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException
  {
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      case CodeConstant.SMSB_UPDATEACTION:
        return doCx(vo);

      default:
        return null;
    }

  }

  /**
   * 显示该计算机代码对应的所有未入库缴款书
   * modified by qianchao 2005.11.2
   *
   * @param     vo 业务参数
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm型数据
   * @throws BaseException 业务异常
   */
  //评估补税
  private ZhsbPgbsjkswhActionForm doQuery(VOPackage vo) throws BaseException
  {

    //定义数据库连接
    Connection conn = null;
    SfDBUtils sbDB = null;

    try
    {	
      //评估补税
      ZhsbPgbsjkswhActionForm form = (ZhsbPgbsjkswhActionForm) vo.getData(); //从前端获得map对象
      //得到userdata
      UserData userData = vo.getUserData();
      //得到区县代码
      String qxdm = InterfaceDj.getQxdm(userData);
      String jsjdm = form.getJsjdm(); //获取用户的计算机代码
      //数据来源
      String sjly = form.getSjly();
      if (sjly == null || sjly.equals(""))
      {
        //未指定特殊的数据来源，则则缺省设为申报录入
        sjly = CodeConstant.SMSB_SJLY_SBLR;
      }
      //获得数据库连接
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //获得 ORManager


      //设置查询条件
      Vector tempSql = new Vector();

      //评估补税,设置在点"维护缴款书"按钮时，只查询出"税款类型代码"为"评估补税"的记录
      tempSql.add("SKLXDM=600");

      tempSql.add("LRR='" + userData.getYhid() + "'");
      tempSql.add("QXDM='" + qxdm + "'");
      tempSql.add("JSJDM='" + jsjdm + "'");
      tempSql.add("SJLY='" + sjly + "'");
      tempSql.add("FSDM='" + CodeConstant.FSDM_SMSB + "'");
      tempSql.add("ZWBS LIKE '0%0'");
       //如果申报表不为空，则只显示该申报编号的缴款书列表
      if (form.getSbbh() != null && !form.getSbbh().equals("") &&
          form.getPresbbh() != null && form.getPresbbh().equals("1"))
      {
        tempSql.add("SBBH='" + form.getSbbh() + "'");
      }
      //started modified by qianchao 2005-12-19
//      tempSql.add("SBBH NOT IN "
//              + " (SELECT SBBH FROM SBDB.SB_JL_SBJKZB "
//              + "  WHERE ZYRQ >= to_date('20050101','yyyymmdd') AND "
//              + "  QXDM = '" + qxdm + "' AND "
//              + "  JSJDM = '" + jsjdm + "' AND"
//              + "  SJLY='" + sjly + "' AND "
//              + "  FSDM='" + CodeConstant.FSDM_SMSB + "' AND "
//              + "  ((SUBSTR(ZWBS, 1, 1) <> '0') OR (SUBSTR(ZWBS, 20, 1) <> '0'))) ");
      tempSql.add("ZYRQ >= to_date('20050101','yyyymmdd')");
      tempSql.add("ND=to_char(sysdate,'yyyy')");
      //ended   modified by qianchao 2005-12-19
      tempSql.addElement("1=1 ORDER BY SBRQ DESC,SBBH,JKPZH ASC");

      //started added by qianchao 2005-12-19
      long t = System.currentTimeMillis();
      //ended   added by qianchao 2005-12-19
      
      List tempList = da.query(Sbjkzb.class, tempSql);

      //started added by qianchao 2005-12-19
      //评估补税
      Debug.out("com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbPgbsjkswhProcessor.doQuery time cost:" + (System.currentTimeMillis() - t));
      //ended   added by qianchao 2005-12-19
      
      //添加税种名称后设置列表
      Debug.out("tempList.size()=" + tempList.size());

      HashMap sbmaps = new HashMap();
      SBData sb = null;
      for (int i = 0; i < tempList.size(); i++)
      {
        //格式化每条纪录
        Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
        sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                                                  sbjkzb.getSzdm())); //税种名称
        sb = (SBData)sbmaps.get(sbjkzb.getSbbh());
        if (sb == null)
        {
          sb = new SBData();
          sbmaps.put(sbjkzb.getSbbh(),sb);
        }
        sb.addSbjkzb(sbjkzb);
      }

      //将map转为list
      Iterator c = sbmaps.values().iterator();
      ArrayList datalist = new ArrayList();

      while (c.hasNext())
      {
        datalist.add(c.next());
      }
      //把值放回form对象

      form.setDataList(datalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * 根据列表中的税种代码得到税种名称
   * @param  list 申报缴款主表列表 List 包含 HashMap型的信息
   * @return ArrayList 添加税种名称的申报缴款主表列表
   * @author Shi Yanfeng 20040112
   */
  /* deleted by qianchao 2005.11.3
  private ArrayList addSzmc(ArrayList list)
  {
    for (int i = 0; i < list.size(); i++)
    {
      HashMap temp = (HashMap) list.get(i);
      //通过税种税目代码表得到税种代码
      temp.put("szmc",
               CodeUtils.getCodeBeanLabel("DM_SZSM", (String) temp.get("szdm")));
    }
    return list;
  }
*/

  /**
   * 撤销指定缴款书 或申报
   * modified by qianchao 2005.10.27
   *
   * @param     vo 业务参数
   * @return ArrayList 添加税种名称的申报缴款主表列表
   * @throws BaseException 业务异常
   */
  //评估补税
  private ZhsbPgbsjkswhActionForm doCx(VOPackage vo) throws BaseException
  {
	//评估补税
	ZhsbPgbsjkswhActionForm form = (ZhsbPgbsjkswhActionForm) vo.getData(); //从前端获得map对象
    //得到userdata
    UserData userData = vo.getUserData();
    //得到区县代码
    String qxdm = InterfaceDj.getQxdm(userData);
    /**
     * 添加日志
     * 20040509 ShiYanfeng
     */
    TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
    JksUtil jks = new JksUtil();
    Connection conn = null;

    try
    {

      //start modifying by qianchao 2005.10.27
      //ArrayList ret = (ArrayList) jks.CxJks(form.getJkpzhStr(), qxdm);
      ArrayList ret = null;
      if (form.getJksType() == CodeConstant.PRINT_YPYS)
      {
        jks.CxJks(form.getJkpzhStr(), qxdm);
      }
      else
      {
        conn = SfDBResource.getConnection();
        jks.CxAllJks(form.getSbbh(),qxdm,form.getJsjdm(),conn);
      }
      //form.setCoList(ret);
      //end modifying by qianchao 2005.10.27
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
    return form;
  }

}
