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
     * ���ݵ�ǰ��ѯ�������Excel�ļ�
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSaveExcel(VOPackage vo) throws BaseException
    {
        System.out.println("-------------------------doSaveExcel------------");
        return doQuery(vo);
    }

    /**
     * ��ѯ�ۿ�״̬��Ϣ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        System.out.println("doQuery---------proce----------begin-");
        KkztcxForm kf = (KkztcxForm) vo.getData();
        String swjgzzjgdm = vo.getUserData().getSsdwdm();
        System.out.println("--------------swjgzzjgdm----------" + swjgzzjgdm);
        Connection conn = null;
        List qlist = null; //List of �ۿ���Ϣ��HashMap�������
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
                System.out.println("doQuery---------proce--------���� ����0---");
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
     * ���ɲ�ѯ��SQL���
     * @param kf
         * @return   key  =sumSql   ,value String ;key=sumTitle ,value String[] ,key = sumKey ,value String[]
     *           key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private Map getSql(KkztcxForm kf, String swjgzzjgdm)
    {
        //��ѯ����
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select ");
        //���ط־�
        sqlBuffer.append("(select a.jc from dmdb.gy_dm_swjgzzjg a where substr(a.qxfjdm,1,2) = t.qxdm and a.ccbs='1' ) as qxfj,");
        //���ɿۿ���Ϣʱ��
        sqlBuffer.append("to_char(t.cjrq,'yyyy-mm-dd') as sckkxxsj,");

        //���ɿۿ���Ϣ����
        sqlBuffer.append(
            "(select count(distinct t1.jsjdm) from sfdb.sf_jl_gtgsh_yhkkxx t1 where t1.qxdm=t.qxdm and t1.nd=t.nd and t1.yd=t.yd ) as kkzhs,");
        //���ɿۿ���Ϣ����
        sqlBuffer.append("t.zjls as kkxxzbs,");
        //���ɿۿ���Ϣ���
        sqlBuffer.append("t.zje as sckkxxje,");
        //���е�һ�οۿ�ʱ��
        sqlBuffer.append(
            "(select to_char(t2.kkrq,'yyyy-mm-dd') from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where rownum<2 and t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgrq,");
        //���е�һ�οۿ�ɹ�����
        sqlBuffer.append(
            "(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgbs,");
        //���е�һ�οۿ�ɹ����
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='10') as yckkcgje,");
        //���еڶ��οۿ�ʱ��
        sqlBuffer.append(
            "(select to_char(t2.kkrq,'yyyy-mm-dd') from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where rownum<2 and t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgrq,");
        //���еڶ��οۿ�ɹ�����
        sqlBuffer.append("(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgbs,");
        //���еڶ��οۿ�ɹ����
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and t2.KKBZ='20') as eckkcgje,");
        //���пۿ�ɹ������ϼ�
        sqlBuffer.append(
            "(select count(t2.jkpzh) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and (t2.KKBZ='10' or t2.KKBZ='20')) as kkcgzbs,");
        //���пۿ�ɹ����ϼ�
        sqlBuffer.append(
            "(select sum(t2.sjje) from sfdb.sf_jl_gtgsh_yhkkhzxx t2 where t2.qxdm=t.qxdm and t2.nd=t.nd and t2.yd=t.yd and (t2.KKBZ='10' or t2.KKBZ='20')) as kkcgzje,");
        //���пۿ�ɹ�����
        sqlBuffer.append("t.cwzjls as kkbcgzbs,");
        //���пۿ�ɹ����
        sqlBuffer.append("t.cwzje as kkbcgzje");
        sqlBuffer.append(" from sfdb.sf_jl_gtgsh_zt t");

        StringBuffer whereBuffer = new StringBuffer();
        //���
        whereBuffer.append(" where t.nd = '" + kf.getNd() + "'");
        //����
        //����
        whereBuffer.append(" and t.yd = '" + kf.getZq() + "'");
        //����
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
     * ��ȡ�������Ʋ�ת�����ݸ�ʽ
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