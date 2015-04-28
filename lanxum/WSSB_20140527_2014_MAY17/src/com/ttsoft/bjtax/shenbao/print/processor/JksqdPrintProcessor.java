/*
 * <p>Title:北京地税市长决策支持</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光科技股份有限公司，版权所有. </p>
 * <p>Company: 四一安信科技股份有限公司</p>
 */
package com.ttsoft.bjtax.shenbao.print.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 缴款申请单打印</p>
 * 
 * 钱超修改 2006.2.11 
 *     1。修改显示格式为“电子缴库专用缴款书”
 * 钱超修改  2006.2.23
 *     1。显示格式，按照一个预算科目一行显示，滞纳金罚款单独一行
 * 
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
            case ActionConstant.INT_ACTION_QUERY:
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

        HashMap datamap = (HashMap) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        ArrayList results = new ArrayList();
        
        try
        {
            UserData ud = (UserData) vo.getUserData();
            ZRRJBSJ zrrJbsj = new ZRRJBSJ();
            String qxdm = ud.getSsdwdm().substring(0,2);
            SWDJJBSJ dj = null;
            String sjly = (String)datamap.get("sjly");
            String jsjdm = (String)datamap.get("jsjdm");
            String sbbh = (String)datamap.get("sbbh");


            //如果是自然人，则调用自然人接口
            if (sjly.equals(CodeConstant.SJLY_SB_ZRR_SBLR))
            {
                //基本信息
                zrrJbsj = (ZRRJBSJ)FriendHelper.getZrrjbsj(jsjdm);
                if (zrrJbsj == null)
                {
                    throw new ApplicationException("获取自然人登记信息出错！");
                }
                datamap.put("nsrmc",zrrJbsj.getNsrmc()); //纳税人全称
            } 
            else if (sjly.equals(CodeConstant.SJLY_SB_SBLR))
            {
                dj = FriendHelper.getDjJbsj(jsjdm);
                if (dj == null)
                {
                    throw new ApplicationException("获取登记信息出错！");
                }
                datamap.put("nsrmc",dj.getNsrmc()); //纳税人全称
            }
            else
            {
                throw new ApplicationException("获取自然人登记信息出错！未知的数量来源：" + sjly);
            }


            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            String sql = 
                "SELECT zb.szdm AS szdm,mx.szsmdm AS szsmdm,mx.sjse AS sjse, " +
                "zb.swjgzzjgdm AS swjgzzjgdm,zb.bz AS bz,zb.sbrq AS sbrq, " +
                "zb.yhmc AS yhmc,zb.zh AS yhzh,mx.yskmdm AS yskmdm, " +
                "zb.gkzzjgdm AS gkzzjgdm " +
                "FROM sbdb.sb_jl_sbjkmx mx,sbdb.sb_jl_sbjkzb zb " +
                "WHERE mx.sbbh=?    AND zb.sbbh=?          AND mx.jkpzh=zb.jkpzh " +
                " AND zb.sjly=?     AND zb.zwbs like '0%0' AND zb.qxdm=? " +
                " AND zb.jsjdm=? " +
                " ORDER BY mx.szsmdm,mx.yskmdm";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1,sbbh);
            ps.setString(2,sbbh);
            ps.setString(3,sjly);
            ps.setString(4,qxdm);
            ps.setString(5,jsjdm);
            
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
            datamap.put("sbrq",new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            datamap.put("yhmc",tq.yhmc);
            datamap.put("zh",tq.yhzh);

            // 征收机关名称（税务所的名称），需要根据代码查名称
            datamap.put("swjgzzjgdm",tq.swjgzzjgdm);

            datamap.put("gkzzjgdm",tq.gkzzjgdm);

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
                    curZB.put("szsmdm",tq.szdm);
                    curZB.put("sjje",tq.sjse);
                    curZB.put("yskmdm",tq.yskmdm);
                    
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
            datamap.put("hjjexx",deFormat.format(hjje)); //合计金额
            datamap.put("datalist",dataList); //合计金额
            datamap.put("hjjedx",Currency.convert(hjje)); //合计金额
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return datamap;
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
