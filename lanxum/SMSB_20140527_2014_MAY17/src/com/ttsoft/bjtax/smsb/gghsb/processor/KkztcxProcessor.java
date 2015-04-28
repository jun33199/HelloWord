package com.ttsoft.bjtax.smsb.gghsb.processor;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.gghsb.web.KkxxcxForm;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;

import java.util.*;

import com.ttsoft.bjtax.smsb.gghsb.web.KkztcxForm;
import java.sql.PreparedStatement;
import com.ttsoft.bjtax.smsb.gghsb.KkztcxVo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class KkztcxProcessor
    implements Processor
{
    public KkztcxProcessor()
    {
    }

    private static String KEY_QUERY_SQL = "querySql";
    private static String KEY_QUERY_TITLE = "queryTitle";
    private static String KEY_QUERY_KEY = "queryKey";
    private static String KEY_SUM_SQL = "sumSql";
    private static String KEY_SUM_TITLE = "sumTitle";
    private static String KEY_SUM_KEY = "sumKey";

    /**
     * 页面元素列表数组
     */

    public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;
        if (vo == null)
        {
            throw new NullPointerException(" VOpackage is null ");
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;

            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_TOEXCELACTION:
                result = doSaveExcel(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;

    }

    /**
     * 依据当前查询结果生成Excel文件
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSaveExcel(VOPackage vo) throws BaseException
    {
        System.out.println("-------------------------doSaveExcel------------");
        return doQuery(vo);
    }

    /**
     * 查询扣款状态信息
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        System.out.println("doQuery---------proce----------begin-");
        KkztcxForm kf = (KkztcxForm) vo.getData();
        String swjgzzjgdm = vo.getUserData().getSsdwdm();
        System.out.println("--------------swjgzzjgdm----------" + swjgzzjgdm);
        Connection conn = null;
        List qlist = null; //List of 扣款信息表HashMap检出对象
        Map sqlMap = getSql(kf, swjgzzjgdm);
        String querySql = (String) sqlMap.get(KEY_QUERY_SQL);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try
        {
            KkztcxVo kvo = null;
            conn = SfDBResource.getConnection();
            ps = conn.prepareStatement(querySql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                kvo = new KkztcxVo();
                kvo.setQxfj(rs.getString(1));
                kvo.setSckkxxsj(rs.getString(2));
                kvo.setKkzhs(rs.getString(3));
                kvo.setKkxxzbs(rs.getString(4));
                kvo.setSckkxxje(rs.getString(5));
                kvo.setYckkcgrq(rs.getString(6));
                kvo.setYckkcgbs(rs.getString(7));
                kvo.setYckkcgje(rs.getString(8));
                kvo.setEckkcgrq(rs.getString(9));
                kvo.setEckkcgbs(rs.getString(10));
                kvo.setEckkcgje(rs.getString(11));
                kvo.setKkcgzbs(rs.getString(12));
                kvo.setKkcgzje(rs.getString(13));
                kvo.setKkbcgzbs(rs.getString(14));
                kvo.setKkbcgzje(rs.getString(15));
                list.add(kvo);
                System.out.println("doQuery---------proce--------长度 大于0---");
            }
            kf.setDataList(list);
            System.out.println("doQuery---------proce----------end-");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return kf;
    }

    private Object doShow(VOPackage vo) throws BaseException
    {

        return null;
    }

    /**
     * 生成查询的SQL语句
     * @param kf
         * @return   key  =sumSql   ,value String ;key=sumTitle ,value String[] ,key = sumKey ,value String[]
     *           key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private Map getSql(KkztcxForm kf, String swjgzzjgdm)
    {
        //查询条件
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select ");
        //区县分局
        sqlBuffer.append("(select a.jc from dmdb.gy_dm_swjgzzjg a where substr(a.qxfjdm,1,2) = t.qxdm and a.ccbs='1' ) as qxfj,");
        //生成扣款信息时间
        sqlBuffer.append("to_char(t.cjrq,'yyyy-mm-dd') as sckkxxsj,");

        //生成扣款信息户数
        sqlBuffer.append(
            "(select count(distinct t1.jsjdm) from sfdb.sf_jl_gtgsh_yhkkxx t1 where t1.qxdm=t.qxdm and t1.nd=t.nd and t1.yd=t.yd ) as kkzhs,");
        //生成扣款信息笔数
        sqlBuffer.append("t.zjls as kkxxzbs,");
        //生成扣款信息金额
        sqlBuffer.append("t.zje as sckkxxje,");
        //银行第一次扣款时间
        sqlBuffer.append(
            "(select to_char(t2.kkrq,'yyyy-mm-dd') from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where rownum<2 and t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgrq,");
        //银行第一次扣款成功笔数
        sqlBuffer.append(
            "(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgbs,");
        //银行第一次扣款成功金额
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgje,");
        //银行第二次扣款时间
        sqlBuffer.append(
            "(select to_char(t2.kkrq,'yyyy-mm-dd') from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where rownum<2 and t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgrq,");
        //银行第二次扣款成功笔数
        sqlBuffer.append("(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgbs,");
        //银行第二次扣款成功金额
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgje,");
        //银行扣款成功笔数合计
        sqlBuffer.append(
            "(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and (t2.KKBZ='10' or t2.KKBZ='20')) as kkcgzbs,");
        //银行扣款成功金额合计
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and (t2.KKBZ='10' or t2.KKBZ='20')) as kkcgzje,");
        //银行扣款不成功笔数
        sqlBuffer.append("t.cwzjls as kkbcgzbs,");
        //银行扣款不成功金额
        sqlBuffer.append("t.cwzje as kkbcgzje");
        sqlBuffer.append(" from sfdb.sf_jl_gtgsh_zt t");

        StringBuffer whereBuffer = new StringBuffer();
        //年度
        whereBuffer.append(" where t.nd = '" + kf.getNd() + "'");
        //征期
        //征期
        whereBuffer.append(" and t.yd = '" + kf.getZq() + "'");
        //区县
        if (!swjgzzjgdm.startsWith("90"))
        {
            whereBuffer.append(" and t.qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");
        }

        //whereBuffer.append(" and t.yd = '" + kf.getNd().substring(4, 6) + "'");
        whereBuffer.append(" order by t.qxdm asc");
        String sql = sqlBuffer.append(whereBuffer.toString()).toString();
        System.out.println(
            "++++++++++++++++++++sql-------%%%%%%%%%%%%%%%%%%555---------:");
        System.out.println(sql);
        //UserData userdata = vo.getUserData();
        Map sqlMap = new HashMap();
        sqlMap.put(KEY_QUERY_SQL, sql);
        return sqlMap;
    }

    /**
     * 获取中文名称并转换数据格式
     * @param qlist
     * @return
     */
    private ArrayList DataListFormat(List qlist)
    {
        ArrayList resultList = new ArrayList();
        for (int i = 0; i < qlist.size(); i++)
        {
            Map map = (Map) qlist.get(i);
            resultList.add(map);
        }
        return resultList;
    }

}