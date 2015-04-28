/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 打印缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class JksPrintProcessor
    implements Processor
{
    public JksPrintProcessor ()
    {
    }
    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        Object result = null;

            /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
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
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        JksPrintForm pf = new JksPrintForm();
        try
        {
            pf = (JksPrintForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            //SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 查询
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        //String swjgzzjgdm = ""; //税务机关组织机构代码
        BigDecimal hjje = new BigDecimal("0.00");
        String mxPmmc = ""; //明细品目名称
        String mxKssl = ""; //明细课税数量
        String mxJsje = ""; //明细缴税金额
        String mxSl = ""; //明细，税率
        String mxSjse = ""; //明细实缴税额
        String mxFcbl = ""; //明细分成比例

        JksPrintForm pf = new JksPrintForm();
        pf = (JksPrintForm) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //设置查询条件
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();

            //tempVector.addElement("lrr='" + pf.getLrr() + "'");
            tempVector.addElement("sjly='" + pf.getHeadSjly() + "'"); //数据来源
            //tempVector.addElement("(zyrq is null or zyrq='')");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                                  + CodeConstant.SMSB_ZWBS + "'");
            //tempVector.addElement("substr(zwbs,1,1)='"+CodeConstant.SMSB_ZWBS+"'");
            //tempVector.addElement("substr(zwbs,20,1)='"+CodeConstant.SMSB_ZWBS20+"'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh='" + pf.getHeadJkpzh() + "'");
            //tempVector.addElement("1=1 order by jkpzh asc ");

            //查询
            ArrayList zbList = (ArrayList) da.query(Sbjkzb.class, tempVector);
            //把值放回form对象
            if (zbList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的信息！");
            }
            Sbjkzb zb = (Sbjkzb) zbList.get(0);
            //把值放回form对象
            //pf.setHeadJkpzh(zb.getJkpzh());
            //pf.setHeadJsjdm(zb.getJsjdm());//
            /**20040413 Shi Yanfeng  **/
            /***在零散的缴款书打印的时候把备注的信息都去掉不打印了，因为纳税人名称已经打印在缴款书的纳税人名称一栏中了**/
//          pf.setBz(zb.getBz());//备注
            //耕地占有税需要备注
            if(pf.isBzVisible()){
            	pf.setBz(zb.getBz());//备注
            }
            

            pf.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM", zb.getSzdm())); //税种名称
            //Modified by lufeng 2003-12-13
            pf.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", zb.getSklxdm())); //税款类型
            pf.setHeadTfrq( (String.valueOf(zb.getSbrq())).substring(0, 10)); //填发日期
            //modified by zhangyj 20131114 start
            pf.setHeadTfrqn( (String.valueOf(zb.getSbrq())).substring(0, 4)); //填发日期年
            pf.setHeadTfrqy( (String.valueOf(zb.getSbrq())).substring(5, 7)); //填发日期月
            pf.setHeadTfrqr( (String.valueOf(zb.getSbrq())).substring(8, 10)); //填发日期日
          //modified by zhangyj 20131114 end
//Update  Start  Zhou kejing 20031113
            //pf.setDm(zb.getLrr()); //登陆人代码
            pf.setDm(zb.getJsjdm()); //销售机关计算机代码
//Update  End    Zhou kejing 20031113

            //根据计算机代码获得的登记信息
            /**从登记接口中获取必要的信息*/
            HashMap mapDJ = new HashMap();
            
            try
            {
                //如果是自然人，则调用自然人接口
                if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
                {
                    /* start added by huxiaofeng 2005.8.16*/
                    //mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                    mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                    /* end added by huxiaofeng 2005.8.16*/
                //如果是耕地占用税
                }else if(pf.getHeadSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
                	 //调用登记
                	try{
                	  mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getHeadJsjdm(),
                             ud);
                	}catch (Exception ex10){
                		 //ex10.printStackTrace();
                    }
  //System.out.println("JksPrintProcessor##########test1");
                	 SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                	 if (dj == null)
                     {
                		 //System.out.println("JksPrintProcessor##########test11");
                		//调用自然人
                		 mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                     }
                }
                else
                {
                    /* start added by huxiaofeng 2005.8.16*/
                    //mapDJ = (HashMap) InterfaceDj.getDjInfo(pf.getHeadJsjdm(),ud);
                    mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getHeadJsjdm(),
                        ud);
                    /* end added by huxiaofeng 2005.8.16*/

                }
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("从登记接口获取信息出错！");
            }

            //如果是自然人，则调用自然人接口
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
                //基本信息
                ZRRJBSJ zrrJbsj = new ZRRJBSJ();
                try
                {
                    zrrJbsj = InterfaceDj.getZRRJBSJ(pf.getHeadJsjdm());
                    pf.setQc(zrrJbsj.getNsrmc()); //纳税人全称
                    pf.setDh(zrrJbsj.getZzdh()); //经营地址联系电话
                    //pf.setHeadLsgx(""); //得到隶属关系名称
                }
                catch (Exception ex2)
                {
                    ex2.printStackTrace();
                    throw new ApplicationException("获取自然人登记信息出错！");
                }
            } //零散录入的时候，全称是纳税人的名称
            else if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                String tempStr = zb.getBz();
                pf.setQc(tempStr.substring(0, tempStr.indexOf(" #$# "))); //纳税人全称
            //如果是耕地占用税
            }else if(pf.getHeadSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
            	 SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                 if (dj == null)
                 {
                	 //System.out.println("JksPrintProcessor##########test2");
                	 //基本信息
                     ZRRJBSJ zrrJbsj = new ZRRJBSJ();
                     try
                     {
                         zrrJbsj = InterfaceDj.getZRRJBSJ(pf.getHeadJsjdm());
                         pf.setQc(zrrJbsj.getNsrmc()); //纳税人全称
                         pf.setDh(zrrJbsj.getZzdh()); //经营地址联系电话
                         //pf.setHeadLsgx(""); //得到隶属关系名称
                     }
                     catch (Exception ex2)
                     {
                         ex2.printStackTrace();
                         throw new ApplicationException("获取自然人登记信息出错！");
                     }
                 }else{
                	 //System.out.println("JksPrintProcessor##########test22");
                     pf.setQc(dj.getNsrmc()); //纳税人名称
                     pf.setDh(dj.getJydzlxdm()); //经营地址联系电话
                 }
            }else
            {
                SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                if (dj == null)
                {
                    throw new ApplicationException("获取登记信息出错！");
                }
                //pf.setHeadLsgx(dj.getLsgxmc()); //得到隶属关系名称
                pf.setQc(dj.getNsrmc()); //纳税人名称
                pf.setDh(dj.getJydzlxdm()); //经营地址联系电话
            }
            //银行信息
            pf.setKhyh(zb.getYhmc()); //开户银行
            pf.setZh(zb.getZh()); //帐户

            //征收机关名称（税务所的名称），需要根据代码查名称
            pf.setHeadZsjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                zb.getSwjgzzjgdm()));
            //Modified by lufeng 2004-01-03
            //注册类型，如果是自然人，则注册类型不显示
//      if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)){
//        pf.setHeadZclxmc("");
//      }
//      else {
//        pf.setHeadZclxmc(CodeUtils.getCodeBeanLabel("QSJMSP_DJZCLXDM",
//            zb.getDjzclxdm()));
//      }

            //隶属关系和注册类型都不打印了！Modified by lufeng 2004-03-12
            pf.setHeadLsgx(""); //得到隶属关系名称
            pf.setHeadZclxmc("");

            //预算科目
            pf.setYskmdm(zb.getYskmdm());
            //需要根据代码获取预算科目名称
            pf.setYskmmc(CodeUtils.getCodeBeanLabel("DM_YSKM", zb.getYskmdm()));
            //预算级次
            pf.setYskmjc(CodeUtils.getCodeBeanLabel("DM_YSJC", zb.getYsjcdm()));

            //收款国库
            dmVector.clear();
            dmVector.addElement("swjgzzjgdm='" + zb.getSwjgzzjgdm() + "'");
            dmList = (ArrayList) da.query(Swjgzzjg.class, dmVector);
            if (dmList.size() <= 0)
            {
                throw new ApplicationException("获取收款国库信息出错！");
            }
            Swjgzzjg swjgzzjg = (Swjgzzjg) dmList.get(0);
            pf.setSkgk("(" + swjgzzjg.getGkjhh() + ")" + swjgzzjg.getSkgk()); //收款国库

            pf.setSkssksrq( (String.valueOf(zb.getSkssksrq())).substring(0, 4)+(String.valueOf(zb.getSkssksrq())).substring(5, 7)+(String.valueOf(zb.getSkssksrq())).substring(8, 10));
            pf.setSkssjsrq( (String.valueOf(zb.getSkssjsrq())).substring(0, 4)+(String.valueOf(zb.getSkssjsrq())).substring(5, 7)+(String.valueOf(zb.getSkssjsrq())).substring(8, 10));
            pf.setSkxjrq( (String.valueOf(zb.getXjrq())).substring(0, 10)); //限缴日期

            //可编辑的三个字段
            pf.setEditSkssksrq(getFormatDate( (String.valueOf(zb.getSkssksrq())).
                                             substring(0, 10)));
            pf.setEditSkssjsrq(getFormatDate( (String.valueOf(zb.getSkssjsrq())).
                                             substring(0, 10)));
            pf.setEditSkxjrq(getFormatDate( (String.valueOf(zb.getXjrq())).
                                           substring(0, 10))); //限缴日期

            //查询明细表信息
            tempVector.clear();
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh='" + pf.getHeadJkpzh() + "'");
            ArrayList mxList = (ArrayList) da.query(Sbjkmx.class, tempVector);
            for (int i = 0; i < mxList.size(); i++)
            {
                Sbjkmx mx = (Sbjkmx) mxList.get(i);
                HashMap map = new HashMap();
                map.put("szsmdm", mx.getSzsmdm());
                //根据税种税目代码获得税种名称
                map.put("szsmmc",
                        CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                if (mx.getKssl() == null)
                {
                    map.put("kssl", " ");
                }
                else
                {
                    map.put("kssl", String.valueOf(mx.getKssl()));
                    //税率
                }
                if (mx.getSl() == null)
                {
                    map.put("sl", " ");
                }
                else
                {
                    map.put("sl", String.valueOf(mx.getSl()));

                }
                map.put("jsje",
                        deFormat.format(StringUtil.getDouble(String.
                    valueOf(mx.getJsje()), 0.00)));
                map.put("sjse",
                        deFormat.format(StringUtil.getDouble(String.
                    valueOf(mx.getSjse()), 0.00)));
            	
                // 调用网上申报接口得到预算科目分成比例名称
            	String fcbl =com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(zb.getYskmdm(), mx.getSzsmdm(), mx.getSwjgzzjgdm());
    			
                map.put("fcbl", fcbl);

                //明细信息
                mxPmmc += map.get("szsmmc") + ";;"; //明细品目名称
                mxKssl += map.get("kssl") + ";;"; //明细课税数量
                mxJsje += map.get("jsje") + ";;"; //明细缴税金额
                mxSl += map.get("sl") + ";;"; //明细，税率
                mxSjse += map.get("sjse") + ";;"; //明细实缴税额
                mxFcbl += map.get("fcbl") + ";;"; //分配比成名称    

                //计算合计
//        hjje = hjje + StringUtil.getDouble(String.valueOf(mx.getSjse()),0.00);
                BigDecimal tmpBig = new BigDecimal(mx.getSjse().toString());
                tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
                hjje = hjje.add(tmpBig);

                dataList.add(map);
            }

            //明细信息
            pf.setMxPmmc(mxPmmc); //明细品目名称
            pf.setMxKssl(mxKssl); //明细课税数量
            pf.setMxJsje(mxJsje); //明细缴税金额
            pf.setMxSl(mxSl); //明细，税率
            pf.setMxSjse(mxSjse); //明细实缴税额
            pf.setMxFcbl(mxFcbl); //明细分成比例 
            pf.setHjje(deFormat.format(hjje)); //合计金额
            pf.setDataList(dataList);
            pf.setHjjedx(Currency.convert(hjje)); //把合计金额转换为大写
            //零散录入，底下的缴款单位盖章不打印
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                pf.setJkdw(""); //缴款单位
            }
            else
            {
                pf.setJkdw(pf.getQc()); //缴款单位
            }

            pf.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                zb.getSwjgzzjgdm().substring(0, 2) + "00")); //地方税务机关

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        JksPrintForm pf = new JksPrintForm();
        pf = (JksPrintForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);

            //更新主表
            //da.update(zb);
//        	2014新票样调整项目仅保留税款限缴日期修改功能 zhangyj 20131120               
            String strSql = "update sbdb.sb_jl_sbjkzb " +        
//                            " set skssksrq=to_date('" + pf.getEditSkssksrq()
//                            + "','yyyymmdd')," +
//                            " skssjsrq=to_date('" + pf.getEditSkssjsrq()
//                            + "','yyyymmdd')," +
//                            " xjrq=to_date('" + pf.getEditSkxjrq()
            				" set xjrq=to_date('" + pf.getEditSkxjrq()            
                            + "','yyyymmdd'), " +
                            " lrrq=to_date('"
                            + String.valueOf(nowTime).substring(0, 19)
                            + "','yyyy-mm-dd hh24:mi:ss') " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and jkpzh='" + pf.getHeadJkpzh() + "'";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("更新主表数据出错！");
            }

            //更新明细表
            strSql = "update sbdb.sb_jl_sbjkmx " +
//                     " set skssksrq=to_date('" + pf.getEditSkssksrq()
//                     + "','yyyymmdd'), " +
//                     " skssjsrq=to_date('" + pf.getEditSkssjsrq()
//                     + "','yyyymmdd'), " +
                     " set lrrq=to_date('"
                     + String.valueOf(nowTime).substring(0, 19)
                     + "','yyyy-mm-dd hh24:mi:ss') " +
                     " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                     " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                     " and jkpzh='" + pf.getHeadJkpzh() + "'";

            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("更新明细表数据出错！");
            }
            

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
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
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 更新
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 时间转换函数。
     * 如：2008-08-08 00:00:00 转化为20080808 00:00:00
     * 或者：2008-08-08 转化为20080808
     * @param inTime 日期或日期时间字符串
     * @return 字符串时间格式
     * @throws BaseException
     */
    public static String getFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        String tempStr = inTime.substring(0, 10);
        String defStr = "";

        try
        {
            if (inTime.length() > 15)
            {
                defStr = inTime.substring(10);
            }
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyyMMdd");
            result = df.format(java.sql.Date.valueOf(tempStr)) + defStr;
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of getFormatDate

}
//:-)
