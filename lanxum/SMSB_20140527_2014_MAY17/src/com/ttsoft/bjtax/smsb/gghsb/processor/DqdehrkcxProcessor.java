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
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: ���ڶ����������ѯProcessor</p>
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
     * ҳ��Ԫ���б�����
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
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;

    }

    /**
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
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
     * ��ȡ��˰��״̬�б�
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
     * ��ȡ˰����б�
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
            //˰���
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
            //LabelValueBean label = new LabelValueBean("*ѡ��˰����", "0");
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
     * ��ȡ�����б�
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
            //˰���
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
     * ��ȡ˰����б�
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
            //˰���
            StringBuffer sb = new StringBuffer();
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
            sb.append(
                " select scjxdm as value, scjxmc as descr from dmdb.dj_dm_scjx ");
            if (!"90".equals(iQxdm))
            {
                sb.append(" where scjxdm like '" + iQxdm + "%'");
            }
            sb.append(" order by scjxdm ");
            LabelValueBean label = new LabelValueBean("*���н���", "0");
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
     * �������˰����ⷽʽ�б�
     * @param sfDBUtils
     * @return
     * @throws BaseException
     */
    private ArrayList getSkrkfsList(SfDBUtils sfDBUtils) throws BaseException
    {
        LabelValueBean label = new LabelValueBean("*���з�ʽ", "0");
        String sql =
            "select skrkfsdm as value, skrkfsmc as descr from DMDB.SF_DM_SKRKFS order by value";
        ArrayList list = this.getOptionList(sfDBUtils, sql);
        list.add(0, label);
        list.add(new LabelValueBean("��", "1"));
        return list;

    }

    /**
     * ����������б�
     * @return
     * @throws BaseException
     */
    private ArrayList getRkqkList() throws BaseException
    {
        ArrayList list = new ArrayList();
        list.add(new LabelValueBean("�����", "0"));
        list.add(new LabelValueBean("�ѽɿ�δ���", "1"));
        list.add(new LabelValueBean("δ�ɿ�", "2"));
        return list;
    }

    //add by hsm
    /**
     * �����˰�ڼ��б�
     * @return
     * @throws BaseException
     */
    private ArrayList getNsqjList() throws BaseException
    {
        ArrayList list = new ArrayList();
        list.add(new LabelValueBean("*ȫ���ڼ�", "0"));
        list.add(new LabelValueBean("��", "1"));
        list.add(new LabelValueBean("��", "3"));
        list.add(new LabelValueBean("����", "6"));
        list.add(new LabelValueBean("��", "12"));
        return list;
    }

    //hsm code finsh

    /**
     * ��ѯ��̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        DqdehrkcxForm df = (DqdehrkcxForm) vo.getData();
        Connection conn = null;
        ArrayList qlist = null; //List of �ۿ���Ϣ��HashMap�������
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
            //����
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
                    //��ɾ�����һҳ���м�¼�����˵���һҳ������ǰ���һҳ
                    if (df.getPgNum() > 1 && qlist.size() < 1)
                    {
                        df.setPgNum(df.getPgNum() - 1);
                        sfHashList = sfDB.getData(querySql, df.getLength(),
                                                  df.getPgNum());
                        qlist = sfHashList.getRecords();
                    }
                    //����һЩ��Ҫ�ĸ�ʽת��
                    if (qlist.size() > 0)
                    {
                        Debug.out("excuce query value\n");
                        df.setDataList(qlist);
                        //ȷ�����ҳ��
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
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_result(DqdehrkcxForm df)
    {
        StringBuffer resultStr = new StringBuffer(); //��ѯ�����
        //��������� ��˰������
        resultStr.append("SELECT G.JSJDM,G.NSRMC,");
        //˰��
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = SUBSTR(G.SZSMDM,1,2)) AS HDSZMC,");
        //˰Ŀ
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = G.SZSMDM) AS SMMC,");
        //��Ӧ��˰�� ��˰�ڼ� ��Ӧ��˰��
        resultStr.append(
            "G.YYNSE AS HDJE,DECODE(G.NSQJ,1,'��',3,'��',6,'����',12,'ȫ��') AS NSQJ,G.YNSRD AS QYNSE,");
        //�ɿ����� �ɿ��� �������
        resultStr.append(
            "TO_CHAR(E.JKSJ,'YYYYMMDD') AS JKRQ,NVL(F.RKJE,0) AS JKJE,TO_CHAR(E.ZYRQ,'YYYYMMDD') AS RKRQ,");
        //��Ӫ��ַ ��Ӫ�绰
        resultStr.append("G.JYDZ,G.JYDZLXDM AS JYDH,");
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
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
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_resultWjk(DqdehrkcxForm df)
    {
        StringBuffer resultStr = new StringBuffer(); //��ѯ�����
        //��������� ��˰������
        resultStr.append("SELECT B.JSJDM,B.NSRMC,");
        //˰��
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = SUBSTR(A.SZSMDM,1,2)) AS HDSZMC,");
        //˰Ŀ
        resultStr.append(
            "(SELECT SZSMMC FROM DMDB.SB_DM_SZSM  WHERE SZSMDM = A.SZSMDM) AS SMMC,");
        //��Ӧ��˰�� ��˰�ڼ� ��Ӧ��˰��
        resultStr.append(
            "A.YYNSE AS HDJE,DECODE(A.NSQJ,1,'��',3,'��',6,'����',12,'ȫ��') AS NSQJ,A.YNSRD AS QYNSE,");
        //�ɿ����� �ɿ��� �������
        resultStr.append(
            "'' AS JKRQ,'' JKJE,'' AS RKRQ,");
        //��Ӫ��ַ ��Ӫ�绰
        resultStr.append("B.JYDZ,B.JYDZLXDM AS JYDH,");
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
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
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_relation(DqdehrkcxForm df)
    {
        StringBuffer relation = new StringBuffer(); //������
        //��ʱ���ݣ���ѯ¼�붨�ڶ�����϶������ϸ�����˰��Ǽ�-�������ݡ�˰����ⷽʽ�϶�
        //��������� ��˰������ ��Ӫ��ַ ��Ӫ�绰 ˰��˰Ŀ ��Ӧ��˰�� ��˰�ڼ� ��Ӧ��˰��
        relation.append(
            "(SELECT B.JSJDM,B.NSRMC,B.JYDZ,B.JYDZLXDM,A.SZSMDM,A.YYNSE,A.NSQJ,A.YNSRD ");
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
        //���շ�ʽ����Ϊ��01�������������գ�
        if (df.getRkfs().equals("1"))
        {
            relation.append("FROM SFDB.SF_JL_DQDEDLMX1 A, ");
        }
        else
        {
            relation.append(",C.SKRKFSDM FROM SFDB.SF_JL_DQDEDLMX1 A, ");
        }
        relation.append("(SELECT JSJDM,NSRMC,JYDZ,JYDZLXDM FROM DJDB.DJ_JL_JBSJ C WHERE 1=1 ");
        //���ݲ�ѯ���������ȹ��˻������ݱ�
        //--�Ǽ�ע������
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
        //--��������
        //AND B.SCJXDM='10'
        if (!df.getJx().equals("0"))
        {
            relation.append("AND C.SCJXDM='").append(df.getJx().trim()).append("' ");
        }
        //--��˰��״̬
        //AND B.NSRZT='10'
        relation.append("AND C.NSRZT='").append(df.getNsrzt().trim()).append("' ");
        //--����˰����
        //AND B.SWJGZZJGDM='0205'
        relation.append("AND C.SWJGZZJGDM='").append(df.getSwjs().trim()).append("' ");
        //--���������
        //AND B.JSJDM='0205'
        //���������
        if (df.getJsjdm() != null && df.getJsjdm().length() > 0)
        {
            relation.append("AND C.JSJDM='").append(df.getJsjdm().trim()).append("' ");
        }
        relation.append(") B ");
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
        //���շ�ʽ����Ϊ��01�������������գ�
        if (df.getRkfs().equals("1"))
        {
            relation.append("WHERE B.JSJDM=A.JSJDM AND A.ZSFSDM='01' ");
        }
        else
        {
            relation.append(
                ",SFDB.SF_JL_SKRKFS C WHERE C.JSJDM=A.JSJDM AND B.JSJDM=C.JSJDM AND A.ZSFSDM='01' ");
        }

        //--��˰�ڼ�
        //AND A.NSQJ='10'
        if (!df.getNsqj().equals("0"))
        {
            relation.append("AND A.NSQJ='").append(df.getNsqj().trim()).append("' ");
        }
        //--˰��
        //AND A.SZSMDM='10'
        if (!df.getSz().equals("0"))
        {
            relation.append("AND SUBSTR(A.SZSMDM,1,2)='").append(df.getSz().trim()).append("' ");
        }
        //--��ⷽʽ
        //AND C.SKRKFSDM='10' �ǡ����з�ʽ�������ޡ�
        if (! (df.getRkfs().equals("0") || df.getRkfs().equals("1")))
        {
            relation.append("AND C.SKRKFSDM='").append(df.getRkfs().trim()).append("' ");
        }
        relation.append(") G ");
        return relation;
    }

    /**
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getVectForWjk(DqdehrkcxForm df)
    {
        StringBuffer relation = new StringBuffer(); //������
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
//        //���շ�ʽ����Ϊ��01�������������գ�
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
        //���ݲ�ѯ���������ȹ��˻������ݱ�
        //--�Ǽ�ע������
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
        //--��������
        //AND B.SCJXDM='10'
        if (!df.getJx().equals("0"))
        {
            relation.append("AND C.SCJXDM='").append(df.getJx().trim()).append("' ");
        }
        //--��˰��״̬
        //AND B.NSRZT='10'
        relation.append("AND C.NSRZT='").append(df.getNsrzt().trim()).append("' ");
        //--����˰����
        //AND B.SWJGZZJGDM='0205'
        relation.append("AND C.SWJGZZJGDM='").append(df.getSwjs().trim()).append("' ");
        //--���������
        //AND B.JSJDM='0205'
        //���������
        if (df.getJsjdm() != null && df.getJsjdm().length() > 0)
        {
            relation.append("AND C.JSJDM='").append(df.getJsjdm().trim()).append("' ");
        }
        relation.append(") B ");
        //˰����ⷽʽ
        //��ѯ������ⷽʽ:Ϊ���ޡ�ʱ������ѯ˰����ⷽʽ�϶���
        //���շ�ʽ����Ϊ��01�������������գ�
        if (df.getRkfs().equals("1"))
        {
            relation.append("WHERE B.JSJDM=A.JSJDM AND A.ZSFSDM='01' ");
        }
        else
        {
            relation.append(
                ",SFDB.SF_JL_SKRKFS C WHERE C.JSJDM=A.JSJDM AND B.JSJDM=C.JSJDM AND A.ZSFSDM='01' ");
        }

        //--��˰�ڼ�
        //AND A.NSQJ='10'
        if (!df.getNsqj().equals("0"))
        {
            relation.append("AND A.NSQJ='").append(df.getNsqj().trim()).append("' ");
        }
        //--˰��
        //AND A.SZSMDM='10'
        if (!df.getSz().equals("0"))
        {
            relation.append("AND SUBSTR(A.SZSMDM,1,2)='").append(df.getSz().trim()).append("' ");
        }
        //--��ⷽʽ
        //AND C.SKRKFSDM='10' �ǡ����з�ʽ�������ޡ�
        if (! (df.getRkfs().equals("0") || df.getRkfs().equals("1")))
        {
            relation.append("AND C.SKRKFSDM='").append(df.getRkfs().trim()).append("' ");
        }
        return relation;
    }

    /**
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private StringBuffer getSql_condition(DqdehrkcxForm df)
    {
        StringBuffer condition = new StringBuffer(); //��ѯ����
        condition.append("WHERE G.JSJDM = E.JSJDM AND E.JKPZH = F.JKPZH AND F.SZSMDM=G.SZSMDM ");
        if (df.getRkqk().equals("0"))
        { //��ѯ�����
            //���ڶ�����ع��ܻ����пۿ�� �����ʶΪ���
            condition.append("AND E.SJLY = '23' AND SUBSTR(E.ZWBS,2,1)='1' ");
        }
        else if (df.getRkqk().equals("1"))
        {
            //��ѯ�ѽɿ�δ���
            //���ڶ�����ع��ܻ����пۿ�� �����ʶΪ���
            condition.append("AND SUBSTR(E.ZWBS,2,1)<>'1' ");
        }
        //������ʼ����
        condition.append(
            "AND E.ZYRQ>=TO_DATE('").append(df.getFromzq().trim()).append("','YYYYMMDD') ");
        //���ڽ�ֹ����
        condition.append(
            "AND E.ZYRQ<=TO_DATE('").append(df.getEndzq().trim()).append("','YYYYMMDD') ");
        //���
        condition.append("AND E.ND='").append(df.getEndzq().trim().substring(0, 4)).append("' ");
        return condition;
    }

    /**
     * ���ɲ�ѯ��SQL���
     * @param df
     * @return  key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private Map getSql(DqdehrkcxForm df)
    {
        //��ѯsql
        String sql = null;
        String sumSql = null;
        if (!df.getRkqk().equals("2"))
        {
            StringBuffer result = getSql_result(df); //��ѯ�����
            StringBuffer table = new StringBuffer(); //��ѯ��
            StringBuffer condition = getSql_condition(df); //��ѯ����
            StringBuffer relation = getSql_relation(df); //������
            //�걨�ɿ���ϸ����
            table.append("FROM SBDB.SB_JL_SBJKMX F,");
            //�걨�ɿ���������
            table.append("SBDB.SB_JL_SBJKZB E,");
            sumSql =
                "SELECT COUNT(G.JSJDM) AS JLS, SUM(G.YNSRD) AS HDJEHJ, SUM(F.RKJE) AS JKJEHJ "
                + table.append(relation).append(condition).toString();
            //����
            sql = result.append(table).append("ORDER BY JSJDM,HDSZMC,SMMC ").toString();
        }
        else
        {
            StringBuffer result = getSql_resultWjk(df); //��ѯ�����
            StringBuffer condition = new StringBuffer(); //��ѯ����
            StringBuffer relation = getVectForWjk(df); //������
            condition.append(relation);
            condition.append("AND B.JSJDM NOT IN");
            condition.append("(SELECT DISTINCT G.JSJDM ");
            condition.append("FROM SBDB.SB_JL_SBJKZB E, ");
            condition.append("(SELECT B.JSJDM ");
            condition.append(relation);
            condition.append(") G ");
            condition.append("WHERE G.JSJDM = E.JSJDM ");
            //������ʼ����
            condition.append(
                "AND E.ZYRQ>=TO_DATE('").append(df.getFromzq().trim()).append("','YYYYMMDD') ");
            //���ڽ�ֹ����
            condition.append(
                "AND E.ZYRQ<=TO_DATE('").append(df.getEndzq().trim()).append("','YYYYMMDD') ");
            //���
            condition.append("AND E.ND='").append(df.getEndzq().trim().substring(0, 4)).append("' ");
            condition.append(")");
            sumSql = "SELECT COUNT(B.JSJDM) AS JLS, SUM(A.YNSRD) AS HDJEHJ, '' AS JKJEHJ " +
                condition.toString();
            //����
            sql = result.append(condition).toString();
        }

        df.setLength(CodeConstant.GZ_PG_LENGTH); //����ҳ��
        String queryTitle[] =
            {
            "���������", "��˰������", "˰��", "˰Ŀ",
            "��Ӧ��˰��", "��˰�ڼ�", "��Ӧ��˰��", "�ɿ�����",
            "�ɿ���", "�������", "˰����ⷽʽ", "��Ӫ��ַ",
            "��Ӫ�绰"};
        String queryKey[] =
            {
            "jsjdm", "nsrmc", "hdszmc", "smmc",
            "hdje", "nsqj", "qynse", "jkrq",
            "jkje", "rkrq", "skrkfsmc", "jydz",
            "jydh"};

        String sumTitle[] =
            {
            "��¼��", "��Ӧ��˰��ϼ�", "�ɿ���ϼ�"};
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
           //��ѯsql
           String sql = null;
           String sumSql = null;
           String resultStr = null; //��ѯ�����
           String tableStr = null; //��ѯ��
           String conditionStr = null; //��ѯ����
           if (df.getRkqk().equals("0")) { //��ѯ�����
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
           else if (df.getRkqk().equals("1")) { //��ѯ�ѽɿ�δ���
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
           else { //��δ�ɿ�
             resultStr = "SELECT C.JSJDM AS JSJDM, C.RDND AS RDND, C.SZSMDM AS SZSMDM, C.YNSRD AS HDJE, '-' AS JKRQ, '-' AS RKRQ, '-' AS JKJE, D.NSRMC AS NSRMC, D.JYDZ AS JYDZ, D.JYDZLXDM AS JYDH";
             tableStr = " FROM SFDB.SF_JL_DQDEDLMX1 C, DJDB.DJ_JL_JBSJ D";
             conditionStr = " WHERE C.ZSFSDM='01' AND D.SWJGZZJGDM='" + df.getSwjs() +
                 "' AND D.NSRZT='" + df.getNsrzt() + "' AND D.JSJDM=C.JSJDM AND NOT EXISTS (SELECT * FROM SBDB.SB_JL_SBJKZB A, SBDB.SB_JL_SBJKMX B WHERE D.JSJDM=A.JSJDM AND A.JKPZH=B.JKPZH AND B.SZSMDM=C.SZSMDM  AND A.JKSJ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD') AND A.JKSJ<TO_DATE('" +
                 df.getEndzq() + "', 'YYYYMMDD') AND A.ZYRQ>TO_DATE('" +
                 df.getFromzq() + "', 'YYYYMMDD')-30)";
           }
           //�϶����
           //if (df.getNd() != null && df.getNd().trim().length() > 0) {
           //  conditionStr += " AND C.RDND='" + df.getNd().trim() + "'";
           //}
           //���������
           if (df.getJsjdm() != null && df.getJsjdm().length() > 0) {
             conditionStr += " AND D.JSJDM='" + df.getJsjdm().trim() + "'";
           }
           //�Ǽ�ע������
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
           //˰��
           if (df.getSz() != null && !df.getSz().equals("0")) {
             conditionStr += " AND SUBSTR(C.SZSMDM,1,2)='" + df.getSz() + "'";
           }
           //��������
           if (df.getJx() != null && !df.getJx().equals("0")) {
             conditionStr += " AND D.SCJXDM='" + df.getJx() + "'";
           }
           //��ⷽʽ
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
           df.setLength(CodeConstant.GZ_PG_LENGTH); //����ҳ��
           String queryTitle[] = {
               "���������", "��˰������", "�϶����", "�˶�˰������",
               "˰Ŀ����", "�˶����", "�ɿ�����",
               "�ɿ���", "�������", "˰����ⷽʽ",
               "��������", "��Ӫ��ַ", "��Ӫ�绰"
           };
           String queryKey[] = {
               "jsjdm", "nsrmc", "rdnd", "hdszmc",
               "smmc", "hdje", "jkrq",
               "jkje", "rkrq", "skrkfsmc",
               "jxmc", "jydz", "jydh"
           };
           String sumTitle[] = {
               "��¼��", "�˶����ϼ�", "�ɿ���ϼ�"};
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
     * ���ݵ�ǰ��ѯ�������Excel�ļ�
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
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
            //��ȡ����ֵ
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
                //�ۿ���Ϣ��HashMap�������
                ArrayList dataList = sfHashList.getRecords();
                //����һЩ��Ҫ�ĸ�ʽת��
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
//      //�����˰��״̬����,��Ӫ��ַ,��Ӫ�绰
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
            //���˰������
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
            //���˰Ŀ����
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
            //�����ⷽʽ����
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
            //���������������
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