package com.ttsoft.bjtax.smsb.gghsb.processor;

import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gghsb.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description: 定期定额户入库情况查询Processor</p>
 * @author zhoujinguang
 * @version 1.0
 */
public class DqdehrkcxProcessor
    implements Processor
{

    private final String KEY_QUERY_SQL = "querySql";
    private final String KEY_QUERY_TITLE = "queryTitle";
    private final String KEY_QUERY_KEY = "queryKey";
    private final String KEY_SUM_SQL = "sumSql";
    private final String KEY_SUM_TITLE = "sumTitle";
    private final String KEY_SUM_KEY = "sumKey";

    public DqdehrkcxProcessor()
    {
    }

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
     * 页面初始化
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow(VOPackage vo) throws BaseException
    {
        DqdehrkcxForm df = (DqdehrkcxForm) vo.getData();
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDBUtils = new SfDBUtils(conn);
            UserData userData = vo.getUserData();
            df.setSwjsList(getSwjsList(sfDBUtils, userData));
            df.setJxList(getJxList(sfDBUtils, userData));
            df.setQxList(this.getQxList(sfDBUtils, userData));
            df.setNsrztList(this.getNsrztList(sfDBUtils));
            df.setRkfsList(this.getSkrkfsList(sfDBUtils));
            df.setRkqkList(this.getRkqkList());
            //add by hsm
            df.setNsqjList(this.getNsqjList());
            //hsm code finish
            return df;
        }
        catch (SystemException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 获取纳税人状态列表
     * @param db
     * @return
     * @throws BaseException
     */
    private ArrayList getNsrztList(SfDBUtils sfDBUtils) throws BaseException
    {
        StringBuffer sb = new StringBuffer();
        sb.append(
            " select nsrztdm  value,nsrztmc  descr from DMDB.DJ_DM_NSRZT order by nsrztdm  ");
        return getOptionList(sfDBUtils, sb.toString());
    }

    private ArrayList getOptionList(SfDBUtils sfDBUtils, String sql) throws
        BaseException
    {
        ArrayList list = new ArrayList();
        try
        {
            ResultSet rs = sfDBUtils.fetchResult(sql);
            while (rs.next())
            {
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue( (String) rs.getString("value"));
                bean.setLabel( (String) rs.getString("descr"));
                list.add(bean);
            }
            rs.close();
        }
        catch (SQLException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }

//============================
    /**
     * 获取税务局列表
     * @param db
     * @param userData
     * @return
     * @throws BaseException
     */
    private ArrayList getSwjsList(SfDBUtils sfDBUtils, UserData userData) throws
        BaseException
    {
        ArrayList list = new ArrayList();
        try
        {
            //税务局
            String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
            StringBuffer sb = new StringBuffer();
            sb.append(
                " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
            sb.append(
                " where substr(SWJGZZJGDM,3,2) <> '00' AND SWJGZZJGDM not like '90%' ");
            if (!"90".equals(iQxdm))
            {
                sb.append(" and SWJGZZJGDM like '" + iQxdm + "%' ");
            }
            sb.append(" order by SWJGZZJGDM ");
            //LabelValueBean label = new LabelValueBean("*选择税务所", "0");
            //list.add(label);
            ResultSet rs = sfDBUtils.fetchResult(sb.toString());
            while (rs.next())
            {
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue( (String) rs.getString("value"));
                bean.setLabel( (String) rs.getString("descr"));
                list.add(bean);
            }
            rs.close();
        }
        catch (SQLException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }

    /**
     * 获取区县列表
     * @param db
     * @param userData
     * @return
     * @throws BaseException
     */
    private ArrayList getQxList(SfDBUtils sfDBUtils, UserData userData) throws
        BaseException
    {
        ArrayList list = new ArrayList();
        try
        {
            //税务局
            String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
            StringBuffer sb = new StringBuffer();
            sb.append(
                " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
            if (!"90".equals(iQxdm))
            {
                sb.append(" where SWJGZZJGDM  = '" + iQxdm + "00' ");
            }
            else
            {
                sb.append(
                    " where substr(SWJGZZJGDM,3,2) = '00' AND SWJGZZJGDM not like '90%'");
            }
            sb.append(" order by SWJGZZJGDM ");
            ResultSet rs = sfDBUtils.fetchResult(sb.toString());
            while (rs.next())
            {
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue( (String) rs.getString("value"));
                bean.setLabel( (String) rs.getString("descr"));
                if ("9000".equals( (String) rs.getString("value")))
                {
                    list.add(0, bean);
                }
                else
                {
                    list.add(bean);
                }
            }
            rs.close();
        }
        catch (SQLException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }

    /**
     * 获取税务局列表
     * @param db
     * @return
     * @throws BaseException
     */
    private ArrayList getJxList(SfDBUtils sfDBUtils, UserData userData) throws
        BaseException
    {
        ArrayList list = new ArrayList();
        try
        {
            //税务局
            StringBuffer sb = new StringBuffer();
            String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
            sb.append(
                " select scjxdm as value, scjxmc as descr from dmdb.dj_dm_scjx ");
            if (!"90".equals(iQxdm))
            {
                sb.append(" where scjxdm like '" + iQxdm + "%'");
            }
            sb.append(" order by scjxdm ");
            LabelValueBean label = new LabelValueBean("*所有街乡", "0");
            list.add(label);
            ResultSet rs = sfDBUtils.fetchResult(sb.toString());
            while (rs.next())
            {
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue( (String) rs.getString("value"));
                bean.setLabel( (String) rs.getString("descr"));
                list.add(bean);
            }
            rs.close();
        }
        catch (SQLException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }

    /**
     * 获得所有税款入库方式列表
     * @param sfDBUtils
     * @return
     * @throws BaseException
     */
    private ArrayList getSkrkfsList(SfDBUtils sfDBUtils) throws BaseException
    {
        LabelValueBean label = new LabelValueBean("*所有方式", "0");
        String sql =
            "select skrkfsdm as value, skrkfsmc as descr from DMDB.SF_DM_SKRKFS order by value";
        ArrayList list = this.getOptionList(sfDBUtils, sql);
        list.add(0, label);
        list.add(new LabelValueBean("无", "1"));
        return list;

    }

    /**
     * 获得入库情况列表
     * @return
     * @throws BaseException
     */
    private ArrayList getRkqkList() throws BaseException
    {
        ArrayList list = new ArrayList();
        list.add(new LabelValueBean("已入库", "0"));
        list.add(new LabelValueBean("已缴款未入库", "1"));
        list.add(new LabelValueBean("未缴款", "2"));
        return list;
    }

    //add by hsm
    /**
     * 获得纳税期间列表
     * @return
     * @throws BaseException
     */
    private ArrayList getNsqjList() throws BaseException
    {
        ArrayList list = new ArrayList();
        list.add(new LabelValueBean("*全部期间", "0"));
        list.add(new LabelValueBean("月", "1"));
        list.add(new LabelValueBean("季", "3"));
        list.add(new LabelValueBean("半年", "6"));
        list.add(new LabelValueBean("年", "12"));
        return list;
    }

    //hsm code finsh

    /**
     * 查询后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        DqdehrkcxForm df = (DqdehrkcxForm) vo.getData();
        Connection conn = null;
        ArrayList qlist = null; //List of 扣款信息表HashMap检出对象
        Map sqlMap = getSql(df);
        String querySql = (String) sqlMap.get(KEY_QUERY_SQL);
        String sumSql = (String) sqlMap.get(KEY_SUM_SQL);
        Debug.out("\n querySql is \n" + querySql + "\n");
        Debug.out("\n sumSql is \n" + sumSql + "\n");
        UserData userData = vo.getUserData();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            df.setSwjsList(getSwjsList(sfDB, userData));
            df.setJxList(getJxList(sfDB, userData));
            df.setQxList(getQxList(sfDB, userData));
            df.setNsrztList(this.getNsrztList(sfDB));
            df.setRkfsList(this.getSkrkfsList(sfDB));
            df.setRkqkList(this.getRkqkList());
            //add by hsm
            df.setNsqjList(this.getNsqjList());
            //汇总
            SfHashList hashlist = sfDB.getData(sumSql);
            List list = hashlist.getRecords();
            if (list != null && list.size() > 0)
            {
                Map map = (Map) list.get(0);
                df.setHdjehj( (String) map.get("hdjehj"));
                df.setJkjehj( (String) map.get("jkjehj"));
                String jls = (String) map.get("jls");
                df.setJls(jls);
                if (jls != null && !jls.equals("0"))
                {
                    Debug.out("excuce query \n");
                    SfHashList sfHashList = sfDB.getData(querySql, df.getLength(),
                                                         df.getPgNum());
                    qlist = sfHashList.getRecords();
                    //若删除最后一页所有记录，则退到上一页，即当前最后一页
                    if (df.getPgNum() > 1 && qlist.size() < 1)
                    {
                        df.setPgNum(df.getPgNum() - 1);
                        sfHashList = sfDB.getData(querySql, df.getLength(),
                                                  df.getPgNum());
                        qlist = sfHashList.getRecords();
                    }
                    //进行一些必要的格式转换
                    if (qlist.size() > 0)
                    {
                        Debug.out("excuce query value\n");
                        df.setDataList(qlist);
                        //确定最大页数
                        df.setPgSum(sfDB.getMaxResultNum() % df.getLength() == 0 ?
                                    sfDB.getMaxResultNum() / df.getLength() :
                                    sfDB.getMaxResultNum() / df.getLength() + 1);
                    }
                }
                else
                {
                    Debug.out("excuce query none \n");
                    df.setDataList(new ArrayList());
                    df.setPgNum(0);
                    df.setPgSum(0);
                    return df;
                }
            }
            //hsm code finish
            else
            {
                Debug.out("excuce query none \n");
                df.setDataList(new ArrayList());
                df.setPgNum(0);
                df.setPgSum(0);
                return df;
            }

        }
        catch (Exception sqlex)
        {
            throw ExceptionUtil.getBaseException(sqlex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return df;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_result(DqdehrkcxForm df)
    {
        StringBuffer resultStr = new StringBuffer(); //查询结果列
        //计算机代码 纳税人名称
        resultStr.append("SELECT G.JSJDM,G.NSRMC,");
        //税种
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = SUBSTR(G.SZSMDM,1,2)) AS HDSZMC,");
        //税目
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = G.SZSMDM) AS SMMC,");
        //月应纳税额 纳税期间 期应纳税额
        resultStr.append(
            "G.YYNSE AS HDJE,DECODE(G.NSQJ,1,'月',3,'季',6,'半年',12,'全年') AS NSQJ,G.YNSRD AS QYNSE,");
        //缴款日期 缴款金额 入库日期
        resultStr.append(
            "TO_CHAR(E.JKSJ,'YYYYMMDD') AS JKRQ,NVL(F.RKJE,0) AS JKJE,TO_CHAR(E.ZYRQ,'YYYYMMDD') AS RKRQ,");
        //经营地址 经营电话
        resultStr.append("G.JYDZ,G.JYDZLXDM AS JYDH,");
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
        if (df.getRkfs().equals("1"))
        {
            resultStr.append("'' AS SKRKFSMC ");
        }
        else
        {
            resultStr.append(
                "(SELECT SKRKFSMC FROM DMDB.SF_DM_SKRKFS WHERE SKRKFSDM = G.SKRKFSDM) AS SKRKFSMC ");
        }
        return resultStr;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_resultWjk(DqdehrkcxForm df)
    {
        StringBuffer resultStr = new StringBuffer(); //查询结果列
        //计算机代码 纳税人名称
        resultStr.append("SELECT B.JSJDM,B.NSRMC,");
        //税种
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = SUBSTR(A.SZSMDM,1,2)) AS HDSZMC,");
        //税目
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = A.SZSMDM) AS SMMC,");
        //月应纳税额 纳税期间 期应纳税额
        resultStr.append(
            "A.YYNSE AS HDJE,DECODE(A.NSQJ,1,'月',3,'季',6,'半年',12,'全年') AS NSQJ,A.YNSRD AS QYNSE,");
        //缴款日期 缴款金额 入库日期
        resultStr.append(
            "'' AS JKRQ,'' JKJE,'' AS RKRQ,");
        //经营地址 经营电话
        resultStr.append("B.JYDZ,B.JYDZLXDM AS JYDH,");
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
        if (df.getRkfs().equals("1"))
        {
            resultStr.append("'' AS SKRKFSMC ");
        }
        else
        {
            resultStr.append(
                "(SELECT SKRKFSMC FROM DMDB.SF_DM_SKRKFS WHERE SKRKFSDM = C.SKRKFSDM) AS SKRKFSMC ");
        }
        return resultStr;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_relation(DqdehrkcxForm df)
    {
        StringBuffer relation = new StringBuffer(); //关联表
        //临时数据，查询录入定期定额定率认定结果明细情况、税务登记-基本数据、税款入库方式认定
        //计算机代码 纳税人名称 经营地址 经营电话 税种税目 月应纳税额 纳税期间 期应纳税额
        relation.append(
            "(SELECT B.JSJDM,B.NSRMC,B.JYDZ,B.JYDZLXDM,A.SZSMDM,A.YYNSE,A.NSQJ,A.YNSRD ");
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
        //征收方式代码为‘01’（纯益率征收）
        if (df.getRkfs().equals("1"))
        {
            relation.append("FROM SFDB.SF_JL_DQDEDLMX1 A, ");
        }
        else
        {
            relation.append(",C.SKRKFSDM FROM SFDB.SF_JL_DQDEDLMX1 A, ");
        }
        relation.append("(SELECT JSJDM,NSRMC,JYDZ,JYDZLXDM FROM DJDB.DJ_JL_JBSJ C WHERE 1=1 ");
        //根据查询条件，首先过滤基本数据表
        //--登记注册类型
        //AND B.DJZCLXDM='10'
        if (df.getDkzclx() != null)
        {
            String s = "";
            for (int i = 0; i < df.getDkzclx().length; i++)
            {
                if(df.getDkzclx()[i]!=null&&df.getDkzclx()[i].length()>0)
                {
                    s += df.getDkzclx()[i] + ",";
                }
            }
            if (s.length() > 0)
            {
                s = s.substring(0, s.length() - 1);
                relation.append("AND INSTR('" + s + "',C.DJZCLXDM)>0 ");
            }
        }
        //--所处街乡
        //AND B.SCJXDM='10'
        if (!df.getJx().equals("0"))
        {
            relation.append("AND C.SCJXDM='").append(df.getJx().trim()).append("' ");
        }
        //--纳税人状态
        //AND B.NSRZT='10'
        relation.append("AND C.NSRZT='").append(df.getNsrzt().trim()).append("' ");
        //--主管税务所
        //AND B.SWJGZZJGDM='0205'
        relation.append("AND C.SWJGZZJGDM='").append(df.getSwjs().trim()).append("' ");
        //--计算机代码
        //AND B.JSJDM='0205'
        //计算机代码
        if (df.getJsjdm() != null && df.getJsjdm().length() > 0)
        {
            relation.append("AND C.JSJDM='").append(df.getJsjdm().trim()).append("' ");
        }
        relation.append(") B ");
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
        //征收方式代码为‘01’（纯益率征收）
        if (df.getRkfs().equals("1"))
        {
            relation.append("WHERE B.JSJDM=A.JSJDM AND A.ZSFSDM='01' ");
        }
        else
        {
            relation.append(
                ",SFDB.SF_JL_SKRKFS C WHERE C.JSJDM=A.JSJDM AND B.JSJDM=C.JSJDM AND A.ZSFSDM='01' ");
        }

        //--纳税期间
        //AND A.NSQJ='10'
        if (!df.getNsqj().equals("0"))
        {
            relation.append("AND A.NSQJ='").append(df.getNsqj().trim()).append("' ");
        }
        //--税种
        //AND A.SZSMDM='10'
        if (!df.getSz().equals("0"))
        {
            relation.append("AND SUBSTR(A.SZSMDM,1,2)='").append(df.getSz().trim()).append("' ");
        }
        //--入库方式
        //AND C.SKRKFSDM='10' 非“所有方式”、“无”
        if (! (df.getRkfs().equals("0") || df.getRkfs().equals("1")))
        {
            relation.append("AND C.SKRKFSDM='").append(df.getRkfs().trim()).append("' ");
        }
        relation.append(") G ");
        return relation;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getVectForWjk(DqdehrkcxForm df)
    {
        StringBuffer relation = new StringBuffer(); //关联表
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
//        //征收方式代码为‘01’（纯益率征收）
//        if (df.getRkfs().equals("1"))
//        {
//            relation.append("FROM SFDB.SF_JL_DQDEDLMX1 A, ");
//        }
//        else
//        {
//
//        }
        relation.append("FROM SFDB.SF_JL_DQDEDLMX1 A, ");
        relation.append("(SELECT JSJDM,NSRMC,JYDZ,JYDZLXDM FROM DJDB.DJ_JL_JBSJ C WHERE 1=1 ");
        //根据查询条件，首先过滤基本数据表
        //--登记注册类型
        //AND B.DJZCLXDM='10'
        if (df.getDkzclx() != null)
        {
            String s = "";
            for (int i = 0; i < df.getDkzclx().length; i++)
            {
                s += df.getDkzclx()[i] + ",";
            }
            if (s.length() > 0)
            {
                s = s.substring(0, s.length() - 1);
                relation.append("AND INSTR('" + s + "',C.DJZCLXDM)>0 ");
            }
        }
        //--所处街乡
        //AND B.SCJXDM='10'
        if (!df.getJx().equals("0"))
        {
            relation.append("AND C.SCJXDM='").append(df.getJx().trim()).append("' ");
        }
        //--纳税人状态
        //AND B.NSRZT='10'
        relation.append("AND C.NSRZT='").append(df.getNsrzt().trim()).append("' ");
        //--主管税务所
        //AND B.SWJGZZJGDM='0205'
        relation.append("AND C.SWJGZZJGDM='").append(df.getSwjs().trim()).append("' ");
        //--计算机代码
        //AND B.JSJDM='0205'
        //计算机代码
        if (df.getJsjdm() != null && df.getJsjdm().length() > 0)
        {
            relation.append("AND C.JSJDM='").append(df.getJsjdm().trim()).append("' ");
        }
        relation.append(") B ");
        //税款入库方式
        //查询条件入库方式:为“无”时，不查询税款入库方式认定表
        //征收方式代码为‘01’（纯益率征收）
        if (df.getRkfs().equals("1"))
        {
            relation.append("WHERE B.JSJDM=A.JSJDM AND A.ZSFSDM='01' ");
        }
        else
        {
            relation.append(
                ",SFDB.SF_JL_SKRKFS C WHERE C.JSJDM=A.JSJDM AND B.JSJDM=C.JSJDM AND A.ZSFSDM='01' ");
        }

        //--纳税期间
        //AND A.NSQJ='10'
        if (!df.getNsqj().equals("0"))
        {
            relation.append("AND A.NSQJ='").append(df.getNsqj().trim()).append("' ");
        }
        //--税种
        //AND A.SZSMDM='10'
        if (!df.getSz().equals("0"))
        {
            relation.append("AND SUBSTR(A.SZSMDM,1,2)='").append(df.getSz().trim()).append("' ");
        }
        //--入库方式
        //AND C.SKRKFSDM='10' 非“所有方式”、“无”
        if (! (df.getRkfs().equals("0") || df.getRkfs().equals("1")))
        {
            relation.append("AND C.SKRKFSDM='").append(df.getRkfs().trim()).append("' ");
        }
        return relation;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_condition(DqdehrkcxForm df)
    {
        StringBuffer condition = new StringBuffer(); //查询条件
        condition.append("WHERE G.JSJDM = E.JSJDM AND E.JKPZH = F.JKPZH AND F.SZSMDM=G.SZSMDM ");
        if (df.getRkqk().equals("0"))
        { //查询已入库
            //定期定额国地共管户银行扣款导入 帐务标识为入库
            condition.append("AND E.SJLY = '23' AND SUBSTR(E.ZWBS,2,1)='1' ");
        }
        else if (df.getRkqk().equals("1"))
        {
            //查询已缴款未入库
            //定期定额国地共管户银行扣款导入 帐务标识为入库
            condition.append("AND SUBSTR(E.ZWBS,2,1)<>'1' ");
        }
        //征期起始日期
        condition.append(
            "AND E.ZYRQ>=TO_DATE('").append(df.getFromzq().trim()).append("','YYYYMMDD') ");
        //征期截止日期
        condition.append(
            "AND E.ZYRQ<=TO_DATE('").append(df.getEndzq().trim()).append("','YYYYMMDD') ");
        //年度
        condition.append("AND E.ND='").append(df.getEndzq().trim().substring(0, 4)).append("' ");
        return condition;
    }

    /**
     * 生成查询的SQL语句
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private Map getSql(DqdehrkcxForm df)
    {
        //查询sql
        String sql = null;
        String sumSql = null;
        if (!df.getRkqk().equals("2"))
        {
            StringBuffer result = getSql_result(df); //查询结果列
            StringBuffer table = new StringBuffer(); //查询表
            StringBuffer condition = getSql_condition(df); //查询条件
            StringBuffer relation = getSql_relation(df); //关联表
            //申报缴款明细数据
            table.append("FROM SBDB.SB_JL_SBJKMX F,");
            //申报缴款主表数据
            table.append("SBDB.SB_JL_SBJKZB E,");
            sumSql =
                "SELECT COUNT(G.JSJDM) AS JLS, SUM(G.YNSRD) AS HDJEHJ, SUM(F.RKJE) AS JKJEHJ "
                + table.append(relation).append(condition).toString();
            //排序
            sql = result.append(table).append("ORDER BY JSJDM,HDSZMC,SMMC ").toString();
        }
        else
        {
            StringBuffer result = getSql_resultWjk(df); //查询结果列
            StringBuffer condition = new StringBuffer(); //查询条件
            StringBuffer relation = getVectForWjk(df); //关联表
            condition.append(relation);
            condition.append("AND B.JSJDM NOT IN");
            condition.append("(SELECT DISTINCT G.JSJDM ");
            condition.append("FROM SBDB.SB_JL_SBJKZB E, ");
            condition.append("(SELECT B.JSJDM ");
            condition.append(relation);
            condition.append(") G ");
            condition.append("WHERE G.JSJDM = E.JSJDM ");
            //征期起始日期
            condition.append(
                "AND E.ZYRQ>=TO_DATE('").append(df.getFromzq().trim()).append("','YYYYMMDD') ");
            //征期截止日期
            condition.append(
                "AND E.ZYRQ<=TO_DATE('").append(df.getEndzq().trim()).append("','YYYYMMDD') ");
            //年度
            condition.append("AND E.ND='").append(df.getEndzq().trim().substring(0, 4)).append("' ");
            condition.append(")");
            sumSql = "SELECT COUNT(B.JSJDM) AS JLS, SUM(A.YNSRD) AS HDJEHJ, '' AS JKJEHJ " +
                condition.toString();
            //排序
            sql = result.append(condition).toString();
        }

        df.setLength(CodeConstant.GZ_PG_LENGTH); //设置页长
        String queryTitle[] =
            {
            "计算机代码", "纳税人名称", "税种", "税目",
            "月应纳税额", "纳税期间", "期应纳税额", "缴款日期",
            "缴款金额", "入库日期", "税款入库方式", "经营地址",
            "经营电话"};
        String queryKey[] =
            {
            "jsjdm", "nsrmc", "hdszmc", "smmc",
            "hdje", "nsqj", "qynse", "jkrq",
            "jkje", "rkrq", "skrkfsmc", "jydz",
            "jydh"};

        String sumTitle[] =
            {
            "记录数", "期应纳税额合计", "缴款金额合计"};
        String sumKey[] =
            {
            "jls", "hdjehj", "jkjehj"};
        Map sqlMap = new HashMap();
        sqlMap.put(KEY_QUERY_SQL, sql);
        sqlMap.put(KEY_QUERY_TITLE, queryTitle);
        sqlMap.put(KEY_QUERY_KEY, queryKey);
        sqlMap.put(KEY_SUM_SQL, sumSql);
        sqlMap.put(KEY_SUM_TITLE, sumTitle);
        sqlMap.put(KEY_SUM_KEY, sumKey);

        return sqlMap;
    }

    /*
       private Map getSql(DqdehrkcxForm df) {
           //查询sql
           String sql = null;
           String sumSql = null;
           String resultStr = null; //查询结果列
           String tableStr = null; //查询表
           String conditionStr = null; //查询条件
           if (df.getRkqk().equals("0")) { //查询已入库
             resultStr = "SELECT C.JSJDM AS JSJDM, C.RDND AS RDND, C.SZSMDM AS SZSMDM, C.YNSRD AS HDJE, TO_CHAR(A.JKSJ,'YYYYMMDD') AS JKRQ, TO_CHAR(A.ZYRQ,'YYYYMMDD') AS RKRQ, NVL(B.RKJE,0) AS JKJE, D.NSRMC AS NSRMC, D.JYDZ AS JYDZ, D.JYDZLXDM AS JYDH";
             tableStr = " FROM SFDB.SF_JL_DQDEDLMX1 C, DJDB.DJ_JL_JBSJ D, SBDB.SB_JL_SBJKMX B, SBDB.SB_JL_SBJKZB A";
             conditionStr =
                 " WHERE C.ZSFSDM='01' AND D.SWJGZZJGDM='" + df.getSwjs() +
                 "' AND D.JSJDM=A.JSJDM AND D.NSRZT='" + df.getNsrzt() +
                 "' AND D.JSJDM=C.JSJDM AND B.SZSMDM=C.SZSMDM AND A.JKPZH=B.JKPZH AND ((INSTR('11,12,16,18,23,51,61,71,81',A.SJLY)>0 AND SUBSTR(A.ZWBS,2,1)='1' ) OR A.SJLY='14') AND A.HXRQ>=TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD') AND A.HXRQ<=TO_DATE('" +
                 df.getEndzq() + "', 'YYYYMMDD') AND A.ZYRQ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD')-30";
           }
           else if (df.getRkqk().equals("1")) { //查询已缴款未入库
             resultStr = "SELECT C.JSJDM AS JSJDM, C.RDND AS RDND, C.SZSMDM AS SZSMDM, C.YNSRD AS HDJE, TO_CHAR(A.JKSJ,'YYYYMMDD') AS JKRQ, '-' AS RKRQ, NVL(B.RKJE,0) AS JKJE, D.NSRMC AS NSRMC, D.JYDZ AS JYDZ, D.JYDZLXDM AS JYDH";
             tableStr = " FROM SFDB.SF_JL_DQDEDLMX1 C, DJDB.DJ_JL_JBSJ D, SBDB.SB_JL_SBJKMX B, SBDB.SB_JL_SBJKZB A";
             conditionStr =
                 " WHERE C.ZSFSDM='01' AND D.SWJGZZJGDM='" + df.getSwjs() +
                 "' AND D.JSJDM=A.JSJDM AND D.NSRZT='" + df.getNsrzt() +
                 "' AND D.JSJDM=C.JSJDM AND B.SZSMDM=C.SZSMDM AND A.JKPZH=B.JKPZH AND INSTR('11,12,16,18,23,51,61,71,81',A.SJLY)>0 AND SUBSTR(A.ZWBS,2,1)<>'1' AND A.JKSJ>=TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD') AND A.JKSJ<=TO_DATE('" +
                 df.getEndzq() + "', 'YYYYMMDD') AND A.ZYRQ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD')-30";
           }
           else { //查未缴款
             resultStr = "SELECT C.JSJDM AS JSJDM, C.RDND AS RDND, C.SZSMDM AS SZSMDM, C.YNSRD AS HDJE, '-' AS JKRQ, '-' AS RKRQ, '-' AS JKJE, D.NSRMC AS NSRMC, D.JYDZ AS JYDZ, D.JYDZLXDM AS JYDH";
             tableStr = " FROM SFDB.SF_JL_DQDEDLMX1 C, DJDB.DJ_JL_JBSJ D";
             conditionStr = " WHERE C.ZSFSDM='01' AND D.SWJGZZJGDM='" + df.getSwjs() +
                 "' AND D.NSRZT='" + df.getNsrzt() + "' AND D.JSJDM=C.JSJDM AND NOT EXISTS (SELECT * FROM SBDB.SB_JL_SBJKZB A, SBDB.SB_JL_SBJKMX B WHERE D.JSJDM=A.JSJDM AND A.JKPZH=B.JKPZH AND B.SZSMDM=C.SZSMDM  AND A.JKSJ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD') AND A.JKSJ<TO_DATE('" +
                 df.getEndzq() + "', 'YYYYMMDD') AND A.ZYRQ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD')-30)";
           }
           //认定年度
           //if (df.getNd() != null && df.getNd().trim().length() > 0) {
           //  conditionStr += " AND C.RDND='" + df.getNd().trim() + "'";
           //}
           //计算机代码
           if (df.getJsjdm() != null && df.getJsjdm().length() > 0) {
             conditionStr += " AND D.JSJDM='" + df.getJsjdm().trim() + "'";
           }
           //登记注册类型
           if (df.getDkzclx() != null) {
             String s = "";
             for (int i = 0; i < df.getDkzclx().length; i++) {
               s += df.getDkzclx()[i] + ",";
             }
             if (s.length() > 0) {
               s = s.substring(0, s.length() - 1);
               conditionStr += " AND INSTR('" + s + "',D.DJZCLXDM)>0";
             }
           }
           //税种
           if (df.getSz() != null && !df.getSz().equals("0")) {
             conditionStr += " AND SUBSTR(C.SZSMDM,1,2)='" + df.getSz() + "'";
           }
           //所处街乡
           if (df.getJx() != null && !df.getJx().equals("0")) {
             conditionStr += " AND D.SCJXDM='" + df.getJx() + "'";
           }
           //入库方式
           if (df.getRkfs() != null && !df.getRkfs().equals("0")) {
             if (df.getRkfs().equals("1")) {
               conditionStr +=
                   " AND NOT EXISTS (SELECT * FROM SFDB.SF_JL_SKRKFS T WHERE D.JSJDM=T.JSJDM)";
             }
             else {
               tableStr += ", SFDB.SF_JL_SKRKFS E";
               conditionStr += " AND D.JSJDM=E.JSJDM AND E.SKRKFSDM='" + df.getRkfs() +
                   "'";
             }
           }
           if (!df.getRkqk().equals("2")) {
             sql = resultStr + tableStr + conditionStr +
                 " ORDER BY JSJDM,RDND,SZSMDM,JKRQ,RKRQ";
             sumSql =
                 "SELECT COUNT(*) AS JLS, SUM(C.YNSRD) AS HDJEHJ, SUM(B.RKJE) AS JKJEHJ "
                 + tableStr + conditionStr;
           }
           else {
             sql = resultStr + tableStr + conditionStr + " ORDER BY JSJDM,RDND,SZSMDM";
             sumSql =
                 "SELECT COUNT(*) AS JLS, SUM(C.YNSRD) AS HDJEHJ, '0.00' AS JKJEHJ "
                 + tableStr + conditionStr;
           }
           df.setLength(CodeConstant.GZ_PG_LENGTH); //设置页长
           String queryTitle[] = {
               "计算机代码", "纳税人名称", "认定年度", "核定税种名称",
               "税目名称", "核定金额", "缴款日期",
               "缴款金额", "入库日期", "税款入库方式",
               "所处街乡", "经营地址", "经营电话"
           };
           String queryKey[] = {
               "jsjdm", "nsrmc", "rdnd", "hdszmc",
               "smmc", "hdje", "jkrq",
               "jkje", "rkrq", "skrkfsmc",
               "jxmc", "jydz", "jydh"
           };
           String sumTitle[] = {
               "记录数", "核定金额合计", "缴款金额合计"};
           String sumKey[] = {
               "jls", "hdjehj", "jkjehj"};
           Map sqlMap = new HashMap();
           sqlMap.put(KEY_QUERY_SQL, sql);
           sqlMap.put(KEY_QUERY_TITLE, queryTitle);
           sqlMap.put(KEY_QUERY_KEY, queryKey);
           sqlMap.put(KEY_SUM_SQL, sumSql);
           sqlMap.put(KEY_SUM_TITLE, sumTitle);
           sqlMap.put(KEY_SUM_KEY, sumKey);
           return sqlMap;
         }
     */
    /**
     * 依据当前查询结果生成Excel文件
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSaveExcel(VOPackage vo) throws BaseException
    {
        DqdehrkcxForm df = (DqdehrkcxForm) vo.getData();
        Connection conn = null;
        try
        {
            Map sqlMap = getSql(df);
            String querySql = (String) sqlMap.get(KEY_QUERY_SQL);
            String sumSql = (String) sqlMap.get(KEY_SUM_SQL);
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            //获取汇总值
            SfHashList sumHashList = sfDB.getData(sumSql);
            ArrayList sumList = sumHashList.getRecords();
            if (sumList.size() > 0)
            {
                Debug.out("excuce excel\n");
                df.setSumList(sumList);
                df.setSumListKey( (String[]) sqlMap.get(KEY_SUM_KEY));
                df.setSumListTitle( (String[]) sqlMap.get(KEY_SUM_TITLE));
                Map map = (Map) sumList.get(0);
                df.setHdjehj( (String) map.get("hdjehj"));
                df.setJkjehj( (String) map.get("jkjehj"));
                df.setJls( (String) map.get("jls"));
                SfHashList sfHashList = sfDB.getData(querySql);
                //扣款信息表HashMap检出对象
                ArrayList dataList = sfHashList.getRecords();
                //进行一些必要的格式转换
                if (dataList.size() > 0)
                {
                    Debug.out("excuce query value\n");
                    df.setDataListTitle( (String[]) sqlMap.get(KEY_QUERY_TITLE));
                    df.setDataListKey( (String[]) sqlMap.get(KEY_QUERY_KEY));
                    df.setDataList(dataList);
                }
                else
                {
                    Debug.out("excuce query nune\n");
                    df.setDataList(new ArrayList());
                }
            }
            else
            {
                Debug.out("excuce query nune\n");
                df.setDataList(new ArrayList());
            }
        }
        catch (SystemException e)
        {
            throw ExceptionUtil.getBaseException(e, e.getMessage());
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return df;
    }

    private void formatData(Map data, Connection conn, DqdehrkcxForm df)
    {
        try
        {
//      //获得纳税人状态名称,经营地址,经营电话
//      String sql =
//          "select t.NSRMC,t.JYDZ,t.JYDZLXDM from DJDB.V_DJSB_JBSJ t where t.JSJDM = '" +
//          data.get("jsjdm") + "'";
//      PreparedStatement psmt = conn.prepareStatement(sql);
//      ResultSet result = psmt.executeQuery();
//      if (result.next()) {
//        data.put("nsrmc", result.getString(1));
//        data.put("jydz", result.getString(2));
//        data.put("jydh", result.getString(3));
//      }
//      result.close();
//      psmt.close();
            //获得税种名称
            String sql =
                "select t.szsmmc from DMDB.SB_DM_SZSM t where t.szsmdm = substr('" +
                data.get("szsmdm") + "',1,2)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet result = psmt.executeQuery();
            if (result.next())
            {
                data.put("hdszmc", result.getString(1));
            }
            result.close();
            psmt.close();
            //获得税目名称
            sql = "select t.szsmmc from DMDB.SB_DM_SZSM t where t.szsmdm ='" +
                data.get("szsmdm") + "'";
            psmt = conn.prepareStatement(sql);
            result = psmt.executeQuery();
            if (result.next())
            {
                data.put("smmc", result.getString(1));
            }
            result.close();
            psmt.close();
            //获得入库方式名称
            sql = "select t.skrkfsmc from DMDB.SF_DM_SKRKFS t,SFDB.SF_JL_SKRKFS m "
                + " where "
                + " m.jsjdm='" + data.get("jsjdm") + "'"
                + " and t.skrkfsdm = m.skrkfsdm";
            psmt = conn.prepareStatement(sql);
            result = psmt.executeQuery();
            if (result.next())
            {
                data.put("skrkfsmc", result.getString(1));
            }
            else
            {
                data.put("skrkfsmc", "");
            }
            result.close();
            psmt.close();
            //获得所处街乡名称
            sql = "select t.scjxmc from DMDB.DJ_DM_SCJX t, DJDB.V_DJSB_JBSJ m "
                + " where "
                + " m.JSJDM = '" + data.get("jsjdm") + "'"
                + " and t.scjxdm = m.SCJXDM ";
            psmt = conn.prepareStatement(sql);
            result = psmt.executeQuery();
            if (result.next())
            {
                data.put("jxmc", result.getString(1));
            }
            result.close();
            psmt.close();
        }
        catch (Exception ex)
        {
            Debug.printException(ex);
        }
    }

}