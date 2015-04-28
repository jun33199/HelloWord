/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypysActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现一票多税缴款书的增加、修改、删除功能</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbjksypdsProcessor
    implements Processor
{
  public ZhsbjksypdsProcessor()
  {
  }

  /**
   * 一票一税的连接名
   */
  //private static final String YPYS = "ZhsbjksypysAction.do";

  /**
   * 一票多税的连接名
   */
  //private static final String YPDS = "ZhsbjksypdsAction.do";

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

  private Object doQuery(VOPackage vo) throws BaseException
  {
    if (1==1)
    {
      throw new ApplicationException("本功能不可用！代码是错误的，未修改完成");
    }

    //HashMap dataMap = new HashMap(); //返回的map对象
    ZhsbjksypdsActionForm form = (ZhsbjksypdsActionForm) vo.getData();
    //定义数据库连接

    Connection conn = null;
    SfDBAccess sbDB = null;
    SfDBUtils sbDBU = null;

    try
    {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      //UserData对象
      UserData ud = (UserData) vo.getUserData();
      //得到区县代码
      String qxdm = InterfaceDj.getQxdm(ud);
      String sql = "";
      sql = "";
      Sbjkzb orObj = new Sbjkzb();
      Sbjkmx orMx = new Sbjkmx();
      Vector condition = new Vector();

      //添加区县代码
      condition.add("qxdm='" + qxdm + "'");
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      //condition.add("substr(jkpzh,1,length(jkpzh)-1)='" + form.getJkpzh() + "'");
      condition.add("sbbh='" + form.getSbbh() + "'");
      List list = sbDB.query(Sbjkzb.class, condition);
      ArrayList ysFormList = new ArrayList();

      String[] names =
          {
          "jsjdm", "lrrq", "yhmc", "sjly"};
      EArray jsArray = new EArray();
      String tempJsStr = "";
      String varName = "";
      String jsSql = "";
      if (list.size() > 0)
      {
        orObj = (Sbjkzb) list.get(0);
        BeanUtil.copyBeanToBean(names, orObj, form);
        form.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
            orObj.getSwjgzzjgdm()));
        /**  通过登记接口得到纳税人名称   Shi Yanfeng 20031028 **/
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.16*/
        form.setNsrmc(jbsj.getNsrmc());
      }

      for (int i = 0; i < list.size(); i++)
      {
        orObj = (Sbjkzb) list.get(i);
        orObj.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG", orObj.getSwjgzzjgdm()));
        orObj.setNsrmc(form.getNsrmc());
        orObj.setYsjcmc(CodeUtils.getCodeBeanLabel("DM_YSJC", orObj.getYsjcdm()));
        orObj.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", orObj.getGkzzjgdm()));
        orObj.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orObj.getSzdm()));

        condition.clear();
        condition.add("qxdm='" + qxdm + "'");
        condition.add("sbbh='" + form.getSbbh() + "'");

        List orMxList = sbDB.query(Sbjkmx.class, condition);

        for (int j = 0; j < orMxList.size(); j++)
        {
          orMx = ( (Sbjkmx) orMxList.get(j));
          orMx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orMx.getSzsmdm()));
        }

        varName = "d" + orObj.getJkpzh();
        String szsmdm = orMx.getSzsmdm();
        if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM023) == 0)
        { //当税种税目代码前三位是023时 是金融 特殊处理
          jsSql = "JKSWH_SZSMDM02023";
        }
        else
        {
          if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM02)
              == 0)
          { //当税种税目代码前两位是02且前三位不是023时 是金融 特殊处理
            jsSql = "JKSWH_SZSMDM02";
          }
          else
          {
            jsSql = "JKSWH_SZSMDM" + orObj.getSzdm();
          }
        }
      }
      //将存有多个一票一税类的List作为一票多税的List
      form.setDataList(ysFormList);

      tempJsStr += jsArray.getArrayByCode("szsmlist", "JKSWH_SZSMDMLIST");
      form.setScriptStr(tempJsStr);

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
   * 更新缴款书信息
   * @param vo 业务参数
   * @return 此form 主要明细信息存放在DataList
   * @throws BaseException
   */

  private Object doUpdate(VOPackage vo) throws BaseException
  {
    if (1==1)
    {
      throw new ApplicationException("本功能不可用！代码是错误的，未修改完成");
    }
    //HashMap dataMap = new HashMap(); //返回的map对象
    ZhsbjksypdsActionForm form = (ZhsbjksypdsActionForm) vo.getData();
    Swjgzzjg swjgzzjg = null;
    //定义数据库连接
    Connection conn = null;
    SfDBAccess sbDB = null;
    SfDBUtils sbDBU = null;
    //UserData对象
    UserData ud = (UserData) vo.getUserData();
    //得到区县代码
    String qxdm1 = InterfaceDj.getQxdm(ud);

    try
    {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      Sbjkzb orObj = new Sbjkzb();
      Sbjkmx orMx = new Sbjkmx();
      Sbjkmx orMxTmp = new Sbjkmx();
      Vector condition = new Vector();
      //添加区县代码
      condition.add("qxdm='" + qxdm1 + "'");
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      condition.add("jkpzh like '" + form.getJkpzh().substring(0, 15)
                    + "%'");
      List orObjList = sbDB.query(Sbjkzb.class, condition);
      //税务机关组织机构代码
      String swjgzzjgdm = "";
      //年度
      String nd = "";
      //创建日期
      Timestamp cjrq = new Timestamp( (new java.util.Date()).getTime());
      Timestamp now = new Timestamp( (new java.util.Date()).getTime());
      //区县代码
      String qxdm = "";
      String szdm = "";
      String ysjc = "";
      String yskm = "";
      String gjbzhydm = "";
      String djzclxdm = "";
      if (orObjList.size() > 0)
      {
        //通过主表数据得到税务机关组织机构代码和年度
        Sbjkzb temp = (Sbjkzb) orObjList.get(0);
        swjgzzjgdm = temp.getSwjgzzjgdm();
        szdm = temp.getSzdm();
        ysjc = temp.getYsjcdm();
        yskm = temp.getYskmdm();
        gjbzhydm = temp.getGjbzhydm();
        djzclxdm = temp.getDjzclxdm();
        nd = temp.getNd();
        //创建日期
        cjrq = temp.getCjrq();
        //区县代码
        qxdm = temp.getQxdm();
      }
      ArrayList orMxList = form.getDataList();
      ArrayList orList = new ArrayList();
      //将页面上取到的数据封装到ormapping值对象中
      JksUtil ju = new JksUtil();
      ResultSet rs = null;
      for (int i = 0; i < orMxList.size(); i++)
      {
        HashMap map = (HashMap) orMxList.get(i);
        orMx = new Sbjkmx();
        BeanUtil.populate(orMx, map);
        orMx.setSwjgzzjgdm(swjgzzjgdm);
        orMx.setNd(nd);
        //设置创建日期
        orMx.setCjrq(cjrq);
        //录入日起
        orMx.setLrrq(now);
        //区县代码
        orMx.setQxdm(qxdm);
        //
        orMx.setRkje(orMx.getSjse());
        //--//Modified By Ha Zhengze 20040314 Start/////////////////////
        //--处理税率
        rs = sbDB.querySQL("select sl from dmdb.sb_dm_szsm where szsmdm='" +
                           orMx.getSzsmdm() + "'");
        if (rs.next())
        {
          orMx.setSl(rs.getBigDecimal(1));
        }
        rs.close();
        //--处理市级分成及区级分成
        Yskm yskmObj = JKAdapter.getInstance().getYskm(orMx.getSzsmdm(),
            djzclxdm,
            gjbzhydm,
            ysjc);
        if (CodeConstant.YSJC_DIFANGJI.equals(ysjc))
        {
          orMx.setSjfc(ju.getFc(orMx.getSjse(), yskmObj.getSjfcbl())); //设置市局级分成金额
          orMx.setQjfc(ju.getFc(orMx.getSjse(), yskmObj.getQxfcbl())); //设置区县级分成金额
        }
        StringBuffer sb = new StringBuffer();
        sb.append(SfTimeUtil.getNowTimestamp() + "缴款书维护参数-->");
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
      //删除明细表中 所有数据 并插入修改后的所有数据
      try
      {
//        sbDB.delete("substr(jkpzh,0,15)='" + form.getJkpzh().substring(0, 15) +
//                    "'", orMx);
        sbDB.delete("jkpzh like '" + form.getJkpzh().substring(0, 15) +
                    "%'", orMx);
        sbDB.insert(orList);
      }
      catch (BaseException ex)
      {
        throw ExceptionUtil.getBaseException(ex);
      }
      //计算修改后的合计值 并插入主表
      String tempJkpzh = "";
      BigDecimal tempSjje = new BigDecimal(0);
      //haveMX判断一个缴款凭证号 是否还有明细数据的存在
      boolean noneMx = true;
      for (int i = 0; i < orObjList.size(); i++)
      {
        noneMx = true;
        tempSjje = new BigDecimal(0);
        orObj = new Sbjkzb();
        orObj = (Sbjkzb) orObjList.get(i);
        tempJkpzh = orObj.getJkpzh();
        for (int j = 0; j < orList.size(); j++)
        {
          orMx = new Sbjkmx();
          orMx = (Sbjkmx) orList.get(j);

          if (tempJkpzh.equals(orMx.getJkpzh()))
          {
            noneMx = false;
            tempSjje = tempSjje.add(orMx.getSjse());
          } //end if
        } //end for 明细循环
        orObj.setSjje(tempSjje);
        //添加入库金额
        orObj.setRkje(orObj.getSjje());
        try
        {
          //更新SJJE
          sbDB.update(orObj);
          //如果其下面有明细数据删除是删除不了的 shit 事实证明可以删除 烂数据库设计
          if (noneMx)
          {

            sbDB.delete(orObj);
          }
        } //end try
        catch (BaseException ex1)
        {

          throw ExceptionUtil.getBaseException(ex1);
        } // end catch
      } //end for

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

}
