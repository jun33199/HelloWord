/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsxshzcxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰���۵�λ���ۻ��� ��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */

public class YhsxshzcxProcessor
    implements Processor
{

    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsxshzcxProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws
        BaseException
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
            case CodeConstant.SMSB_CXJKSACTION:
                result = doCxjks(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ��ѯ�ʻ����Գ����Ľɿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        YhsxshzcxForm form = (YhsxshzcxForm) vo.getData();
        String jsjdm = form.getDsjsjdm();
        Connection conn = null;
        try
        {
            ArrayList jksList = new ArrayList();
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //��ѯsql
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(
                "select distinct t1.JKPZH, t1.SJJE")
                .append(" from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2")
                .append(
                " where t1.JKPZH=t2.JKPZH and t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                        + "' ")
                .append(" and t1.zwbs like  '" + CodeConstant.SMSB_ZWBS + "%"
                        + CodeConstant.SMSB_ZWBS + "' ")
                .append(" and t1.JSJDM='").append(jsjdm).append("' ")
                .append(" and t2.qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                        + "' ")
                .append(" and t2.SJLY='1' and t2.HZFS='0'");

            String sqlString = sqlBuffer.toString();
            ResultSet rs = da.querySQL(sqlString);
            while (rs.next())
            {
                HashMap map = new HashMap();
                map.put("jkpzh", rs.getString("jkpzh"));
                map.put("sjje", rs.getString("sjje"));
                jksList.add(map);
            }
            rs.close();
            if (jksList.size() == 0 && !form.isIsFromCx())
            {
                throw new ApplicationException("û�п��Գ����Ľɿ��飡");
            }
            else
            {
                form.setDataList(jksList); //�ɿ�������
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * �����ɿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doCxjks (VOPackage vo)
        throws BaseException
    {
        YhsxshzcxForm form = (YhsxshzcxForm) vo.getData();
        String cxjkpzh = form.getCxjkpzh();
        String dsjsjdm = form.getDsjsjdm();
        Connection conn = null;
        //����ɾ����־ 20040429 Shi Yanfeng
        TinyTools.makeLog4DsYhsZfjks(vo.getUserData(), cxjkpzh);

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //��֤�˽ɿ��黹δ���
            StringBuffer checkSql = new StringBuffer();
            checkSql.append(
                "select count(*) Count from sbdb.sb_jl_sbjkzb where FSDM='1' and SJLY='15'")
                .append(" and substr(zwbs,1,1)='" + CodeConstant.SMSB_ZWBS
                        + "' ")
                .append(" and substr(zwbs,20,1)='" + CodeConstant.SMSB_ZWBS20
                        + "' ")
                .append(" and jkpzh='").append(cxjkpzh).append("'");
            ResultSet rs = da.querySQL(checkSql.toString());
            //���˽ɿ�����Գ���
            if (rs.next())
            {
                if (rs.getInt("Count") > 0)
                {
                    //ɾ���걨�ɿ���ϸ������
                    StringBuffer jkmxSql = new StringBuffer();
                    jkmxSql.append(
                        "delete from sbdb.sb_jl_sbjkmx where jkpzh='")
                        .append(cxjkpzh).append("'");
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkmxSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("ɾ���걨�ɿ���ϸ�����ݳ���");
                    } //end try

                    //ɾ���걨�ɿ���������
                    StringBuffer jkzbSql = new StringBuffer();
                    jkzbSql.append(
                        "delete from sbdb.sb_jl_sbjkzb where jkpzh='")
                        .append(cxjkpzh).append("'");
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkzbSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("ɾ���걨�ɿ��������ݳ���");
                    } //end try

                    //�õ���Ӧ������ƾ֤��
                    ArrayList jksList = new ArrayList();
                    StringBuffer yhszbSql = new StringBuffer();
                    yhszbSql.append("select xspzh from sbdb.sb_jl_yhsgmz")
                        .append(" where jkpzh='").append(cxjkpzh).append("'");
                    try
                    {
                        ResultSet rs2 = da.querySQL(yhszbSql.toString());
                        while (rs2.next())
                        {
                            HashMap map = new HashMap();
                            map.put("xspzh", rs2.getString("xspzh"));
                            jksList.add(map);
                        }
                        if (jksList.size() == 0)
                        {
                            throw new ApplicationException("û����Ӧ��ӡ��˰�����¼��");
                        }
                        rs2.close();
                    }
                    catch (Exception ex1)
                    {
                        throw new ApplicationException("ȡ��Ӧ����ƾ֤��ʧ�ܣ�"
                            + ex1.getMessage());
                    }

                    //ɾ����Ӧ����������
                    for (int ii = 0; ii < jksList.size(); ii++)
                    {
                        String xspzh = ( (HashMap) jksList.get(ii)).get("xspzh").
                                       toString();
                        if (xspzh.equals(""))
                        {
                            continue;
                        }
                        //����ӡ��˰Ʊ֤���
                        StringBuffer pzSelectSql = new StringBuffer();
                        pzSelectSql.append(
                            "select distinct a.XSPZH,a.SPMZDM,a.GPSL,a.JE,b.zhdm ")
                            .append(
                            " from sbdb.sb_jl_yhsgmmx a, sbdb.sb_jl_yhsgmz b")
                            .append(
                            " where a.xspzh=b.xspzh and a.dsjsjdm=b.dsjsjdm and a.xspzh='")
                            .append(xspzh).append("'").append(
                            " and a.dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        SfDBUtils sfDB = new SfDBUtils(conn);
                        List pzSelectlist = sfDB.getDataList(pzSelectSql.
                            toString());
                        try
                        {
                            if (pzSelectlist.size() > 0)
                            {
                                com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                    yhsUpdatePzkc(
                                    vo.getUserData(),
                                    (String) ( (Map) pzSelectlist.get(0)).get(
                                    "zhdm"), pzSelectlist);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            throw new ApplicationException("����ӡ��˰Ʊ���ʧ�ܣ�");
                        }

                        //ɾ��������ϸ����
                        StringBuffer xsmxSql = new StringBuffer();
                        xsmxSql.append(
                            "delete from sbdb.sb_jl_yhsgmmx where xspzh='")
                            .append(xspzh).append("'").append(" and dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        try
                        {
                            PreparedStatement sqlStatement = conn.
                                prepareStatement(xsmxSql.
                                                 toString());
                            sqlStatement.execute();
                        }
                        catch (Exception ex1)
                        {
                            throw new ApplicationException("ɾ��ӡ��˰������ϸ��¼ʧ�ܣ�");
                        }

                        //ɾ��������������
                        StringBuffer xszbSql = new StringBuffer();
                        xszbSql.append(
                            "delete from sbdb.sb_jl_yhsgmz where xspzh='")
                            .append(xspzh).append("'").append(" and dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        try
                        {
                            PreparedStatement sqlStatement = conn.
                                prepareStatement(xszbSql.
                                                 toString());
                            sqlStatement.execute();
                        }
                        catch (Exception ex1)
                        {
                            throw new ApplicationException("ɾ��ӡ��˰���������¼ʧ�ܣ�");
                        } //end try
                    } //end for
                }
            } //end if
            else
            {
                throw new ApplicationException("�ɿ��� " + cxjkpzh + " ���ɳ�������");
            }
            rs.close();
            return form;
        }
        catch (Exception ex)
        {
            throw new ApplicationException("�����ɿ���ʧ�ܣ�" + ex.getMessage());
        } //end try
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

}