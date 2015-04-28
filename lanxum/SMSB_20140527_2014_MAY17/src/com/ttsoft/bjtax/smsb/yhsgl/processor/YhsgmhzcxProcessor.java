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

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmhzcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title:  ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰����������� ��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzcxProcessor
    implements Processor
{

    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsgmhzcxProcessor ()
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
        YhsgmhzcxForm form = (YhsgmhzcxForm) vo.getData();
        String cxhzdx = form.getCxhzdx();
        String jsjdm = "";
        String yhsxsry = form.getYhsxsry();
        Connection conn = null;

        try
        {
            ArrayList jksList = new ArrayList();

            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm();
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);
            if (strDsJsjdm == null || strDsJsjdm == "")
            {
                throw new ApplicationException("δ�õ���Ӧ�Ĵ��ۼ�������룡");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
                jsjdm = strDsJsjdm;
            }

            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(
                "select distinct t1.JKPZH, t1.SJJE, t1.SBBH")
                .append(" from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2")
                .append(
                " where t1.JKPZH=t2.JKPZH and t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) +
                        "' ")
                .append(" and t1.zwbs like  '" + CodeConstant.SMSB_ZWBS + "%" +
                        CodeConstant.SMSB_ZWBS + "' ")
                .append(" and t1.JSJDM='").append(jsjdm).append("' ")
                .append(" and t2.qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) +
                        "' ")
                .append(" and t2.SJLY='0' and t2.jzbs='0'");

            if (cxhzdx.equals("0"))
            { //����λ����
                sqlBuffer.append(" and t2.HZFS='0'");
            }
            else
            { //�������˻���
                sqlBuffer.append(" and t2.HZFS='1' and t2.ZHDM='")
                    .append(yhsxsry).append("'");
            } //end if

            String sqlString = sqlBuffer.toString();
            ResultSet rs = da.querySQL(sqlString);
            while (rs.next())
            {
                HashMap map = new HashMap();
                map.put("jkpzh", rs.getString("jkpzh"));
                map.put("sjje", rs.getString("sjje"));
                map.put("sbbh",rs.getString("sbbh"));
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
        UserData us = vo.getUserData();
        YhsgmhzcxForm form = (YhsgmhzcxForm) vo.getData();
        String cxsbbh = form.getCxsbbh();
        String strSsdwdm = us.getSsdwdm(); //userData������˰�������֯��������
        String jsjdm = getDsjsjdmByUserData(strSsdwdm);
        Connection conn = null;

         //����ɾ����־ 20040429 Shi Yanfeng
        TinyTools.makeLog4YhsZfjks(vo.getUserData(), cxsbbh);
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //��֤�˽ɿ��黹δ���
            StringBuffer checkSql = new StringBuffer();
            checkSql.append(
                "select sum(substr(t1.zwbs,1,1) || substr(t1.zwbs,20,1)) Count from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2 where t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.JSJDM='" + jsjdm
                        + "' ")
                .append(" and t1.SWJGZZJGDM='"
                        + us.getSsdwdm() +
                        "' ")
                .append(" and t1.SBBH='").append(cxsbbh).append(
                "' and t2.JZBS='0' and t1.JKPZH=t2.JKPZH");
            ResultSet rs = da.querySQL(checkSql.toString());

            if (rs.next())
            {
                if (rs.getInt("Count") <= 0) { //���˽ɿ�����Գ���
                    String sbjkzSql =
                            "SELECT JKPZH FROM SBDB.SB_JL_SBJKZB t WHERE t.SBBH='" +
                            cxsbbh + "' and t.JSJDM='"+jsjdm+"'";
                    System.out.println("sbjkzSql = " + sbjkzSql);
                    ResultSet sbzbrs = da.querySQL(sbjkzSql);
                    while (sbzbrs.next()) {
                        //�ָ�ӡ��˰�����¼��δ����״̬
                        StringBuffer yhsSql = new StringBuffer();
                        String timeNow = SfTimeUtil.getNowTimestamp().toString();
                        timeNow = timeNow.substring(0, timeNow.length() - 4);
                        yhsSql.append("UPDATE SBDB.SB_JL_YHSGMZ")
                                .append(
                                        " SET JKPZH='', HZRQ='', HZR='', YHZBS='0', HZFS=''")
                                .append(", LRRQ=TO_DATE('")
                                .append(timeNow)
                                .append("','yyyy-mm-dd hh24:mi:ss')")
                                .append(" WHERE JKPZH='").append(sbzbrs.
                                getString("jkpzh")).append(
                                        "'");
                        try {
                            PreparedStatement sqlStatement = conn.
                                    prepareStatement(
                                            yhsSql.
                                            toString());
                            sqlStatement.execute();
                        } catch (Exception ex10) {
                            throw new ApplicationException("�ָ�ӡ��˰�����¼��δ����״̬����");
                        } //end try
                    }
                    sbzbrs.close();
                    //ɾ���걨�ɿ���ϸ������
                    String jkmxSql =
                        "DELETE FROM SBDB.SB_JL_SBJKMX WHERE SBBH='"+
                        cxsbbh+"' AND JSJDM='"+jsjdm+"'";
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkmxSql);
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("ɾ���걨�ɿ���ϸ�����ݳ���");
                    } //end try

                    //ɾ���걨�ɿ���������
                    String jkzbSql =
                            "DELETE FROM SBDB.SB_JL_SBJKZB WHERE SBBH='" +
                            cxsbbh + "' AND JSJDM='"+jsjdm+"'";
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkzbSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        ex8.printStackTrace();
                        throw new ApplicationException("ɾ���걨�ɿ��������ݳ���");
                    } //end try
                }
                else
                {
                    throw new ApplicationException("�걨��� " + cxsbbh + " ���ɳ�����");
                }

            } //end if
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

    /**
     * ����UserData�õ����۵�λ���������
     * @param strSsdwdm ����˰��λ����
     * @return ���۵�λ���������
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

}
