/*
 * <p>Title:北京地税市长决策支持</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光科技股份有限公司，版权所有. </p>
 * <p>Company: 四一安信科技股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 缴款申请单打印</p>
 * 
 * 钱超修改 2006.2.11 
 *     1。修改显示格式为“电子缴库专用缴款书”
 * 
 * 钱超修改  2006.2.23
 *     1。显示格式，按照一个预算科目一行显示，滞纳金罚款单独一行
 * 
 * @author 开发部－－吴有智
 * @version 1.1
 */
public class JksqdPrintProcessor implements Processor{

    class tmpQuery{
        //备注
        String bz;
        //国库组织结构代码
        String gkzzjgdm;
        //申报日期
        Timestamp sbrq;
        //实缴数额
        BigDecimal sjse;
        //税种代码
        String szdm;
        //税目代码
        String szsmdm;
        //银行帐号
        String yhzh;
        //银行名称        
        String yhmc;
        //预算科目代码
        String yskmdm;
        //税务机关组织机构代码
        String swjgzzjgdm;
    };
    
    
    public JksqdPrintProcessor() {
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

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 查询
     * 
     * 钱超  2006.2.12  修改
     * 钱超  2006.2.23  修改
     *      本函数重写了，从用ormap查主表改为直接查明细。
     * 
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        BigDecimal hjje = new BigDecimal("0.00");
        BigDecimal tmpdd;

        JksqdPrintForm pf = new JksqdPrintForm();
        pf = (JksqdPrintForm) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        ArrayList results = new ArrayList();
        
        String tempstr;

        try
        {
            UserData ud = (UserData) vo.getUserData();
            HashMap djmap = new HashMap();
            ZRRJBSJ zrrJbsj = new ZRRJBSJ();
            String qxdm = InterfaceDj.getQxdm(ud);
            SWDJJBSJ dj = null;

            try
            {
                //如果是自然人，则调用自然人接口
            	//增加补缴欠缴税款模块处理
                if (((!TinyTools.isCompany(pf.getJsjdm())) && pf.getHeadSjly()
						.equals(CodeConstant.SMSB_SJLY_BJQS))
						|| pf.getHeadSjly()
								.equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
                	
                	System.out.println("===========增加补缴欠缴税款模块处理===============");
					djmap = (HashMap) InterfaceDj.getZRRInfo(pf.getH_jsjdm());
				} else {
					djmap = (HashMap) InterfaceDj.getDjInfo(pf.getH_jsjdm());
				}
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("从登记接口获取信息出错！");
            }
            //如果是自然人，则调用自然人接口
           //增加补缴欠缴税款模块处理
            if (((!TinyTools.isCompany(pf.getJsjdm())) && pf.getHeadSjly()
					.equals(CodeConstant.SMSB_SJLY_BJQS))
					|| pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
            	System.out.println("===========增加补缴欠缴税款模块处理===============");
            	//基本信息
                zrrJbsj = (ZRRJBSJ)djmap.get(DjOuterConstant.ZRRJBSJ);
                if (zrrJbsj == null)
                {
                    throw new ApplicationException("获取自然人登记信息出错！");
                }
                pf.setNsrmc(zrrJbsj.getNsrmc()); //纳税人全称
            } //零散录入的时候，全称是纳税人的名称
            else if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
            }
            else
            {
                dj = (SWDJJBSJ) djmap.get("JBSJ");
                if (dj == null)
                {
                    throw new ApplicationException("获取登记信息出错！");
                }
                pf.setNsrmc(dj.getNsrmc()); //纳税人名称
            }
            
            conn = SfDBResource.getConnection();
            String sql = 
                "SELECT zb.szdm AS szdm,mx.szsmdm AS szsmdm,mx.sjse AS sjse, " +
                "zb.swjgzzjgdm AS swjgzzjgdm,zb.bz AS bz,zb.sbrq AS sbrq, " +
                "zb.yhmc AS yhmc,zb.zh AS yhzh,mx.yskmdm AS yskmdm, " +
                "zb.gkzzjgdm AS gkzzjgdm " +
                "FROM sbdb.sb_jl_sbjkmx mx,sbdb.sb_jl_sbjkzb zb " +
                "WHERE mx.sbbh=?    AND zb.sbbh=?          AND mx.jkpzh=zb.jkpzh " +
                " AND zb.sjly=?     AND zb.zwbs like '0%0' " +
                //"AND zb.qxdm=? " +
                " AND zb.jsjdm=? " +
                " ORDER BY mx.szsmdm,mx.yskmdm";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1,pf.getSbbh());
            ps.setString(2,pf.getSbbh());
            ps.setString(3,pf.getHeadSjly());
            //ps.setString(4,qxdm);
            //ps.setString(5,pf.getJsjdm());
            ps.setString(4,pf.getJsjdm());
            
            Debug.out("pf.getHeadSjly()=" + pf.getHeadSjly());
            //Debug.out("qxdm=" + qxdm);
            Debug.out("pf.getSbbh()=" + pf.getSbbh());
            Debug.out("pf.getJsjdm()=" + pf.getJsjdm());

            rs = ps.executeQuery();
            tmpQuery tq = null;
            //将查询结果放到数组中，便于处理
            while (rs.next())
            {
                tq = new tmpQuery();
                tq.bz = rs.getString("bz");
                tq.gkzzjgdm = rs.getString("gkzzjgdm");
                tq.sbrq = rs.getTimestamp("sbrq");
                tq.sjse = rs.getBigDecimal("sjse");
                tq.swjgzzjgdm = rs.getString("swjgzzjgdm");
                tq.szdm = rs.getString("szdm");
                tq.szsmdm = rs.getString("szsmdm");
                tq.yhmc = rs.getString("yhmc");
                tq.yhzh = rs.getString("yhzh");
                tq.yskmdm = rs.getString("yskmdm");
                results.add(tq);
            }
            rs.close();
            rs = null;
            ps.close();
            ps = null;
            
            if (results.size() == 0)
            {
                throw new ApplicationException("错误的申报编号！找不到申报数据。");
            }
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                pf.setNsrmc(tq.bz.substring(0, tq.bz.indexOf(" #$# "))); // 纳税人全称
            }
            
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_BJQS))
            {
                pf.setBz(tq.bz); // 备注信息
            }
            
            pf.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            pf.setYhmc(tq.yhmc);
            pf.setZh(tq.yhzh);


            // 征收机关名称（税务所的名称），需要根据代码查名称
            pf.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",tq.swjgzzjgdm));

            // 国库名称
            pf.setGkzzjgdm(tq.gkzzjgdm);
            pf.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG",tq.gkzzjgdm));

            
            Map preZB = null;
            Map curZB = null;
            int isz;
            for (int i = 0; i < results.size(); i++)
            {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for(isz = 0;isz < dataList.size();isz++)
                {
                    preZB = (Map)dataList.get(isz);
                    if (((String)preZB.get("szdm")).equals(tq.szdm) &&
                        ((String)preZB.get("yskmdm")).equals(tq.yskmdm))
                    {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size()))
                {
                    curZB = new HashMap();
                    curZB.put("szdm",tq.szdm);
                    curZB.put("sjje",tq.sjse);
                    curZB.put("yskmdm",tq.yskmdm);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM",tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92"))
                    {
                        tempstr += "(滞纳金、罚款)";    
                    }
                    curZB.put("szmc",tempstr);
                    
                    dataList.add(curZB);
                }
                else
                {
                    tmpdd = (BigDecimal)preZB.get("sjje"); 
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje",tmpdd);
                }
                hjje = hjje.add(tq.sjse);
                
            }
            for (int i = 0;i < dataList.size();i ++)
            {
                curZB = (HashMap)dataList.get(i);
                tmpdd = (BigDecimal)curZB.get("sjje"); 
                curZB.put("sjje",deFormat.format(tmpdd));
            }
            pf.setHjjexx(deFormat.format(hjje)); //合计金额
            Debug.out("合计金额小写：" + hjje);
            pf.setSzitem(dataList);
            pf.setHjjedx(Currency.convert(hjje)); //把合计金额转换为大写
            Debug.out("合计金额大写：" + hjje);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                rs = null;
            }
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return pf;
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
