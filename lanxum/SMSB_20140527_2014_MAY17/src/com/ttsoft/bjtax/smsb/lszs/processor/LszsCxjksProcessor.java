/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsCxjksForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰��������ϵͳ���������걨
 * </p>
 * <p>
 * Description: ��ɢ���ճ����ɿ���
 * </p>
 * 
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsCxjksProcessor implements Processor
{
    public LszsCxjksProcessor()
    {
    }

    /**
     * ͨ�ô������ģ��
     * 
     * @param vo
     *            ���ݴ���ֵ����
     * @return ���ݽ������[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        Object result = null;

        /** @todo Implement this com.ttsoft.framework.processor.Processor method */
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
                throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ��
     * 
     * @param vo
     *            ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow(VOPackage vo) throws BaseException
    {
        LszsCxjksForm pf = new LszsCxjksForm();
        try
        {
            pf = (LszsCxjksForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return pf;
    }

    /**
     * ��ѯ modifying by qianchao 2005.10.26
     * 
     * ����ʹ��map���� ҳ�����ݣ���ʹ��SBData�洢ҳ�����ݡ�
     * 
     * @param vo
     *            ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        LszsCxjksForm pf = new LszsCxjksForm();

        pf = (LszsCxjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            // �õ�UserData
            ud = (UserData) vo.getUserData();
            // ���ò�ѯ����
            Vector tempVector = new Vector();
            String qxdm = InterfaceDj.getQxdm(ud);
            String jsjdm = pf.getHeadJsjdm();

            tempVector.addElement("lrr='" + pf.getLrrdm() + "'");
            tempVector.addElement("sjly='" + CodeConstant.SMSB_SJLY_LSZSLR + "'");
            tempVector.addElement("FSDM='" + CodeConstant.FSDM_SMSB + "'");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%" + CodeConstant.SMSB_ZWBS + "'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + jsjdm + "'");

            // �Ӳ�ѯ

            // started modified by qianchao 2005-12-19
//            tempVector.addElement("SBBH NOT IN " + "(SELECT SBBH FROM SBDB.SB_JL_SBJKZB "
//                    + " WHERE ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
//                    + "'" + " AND SJLY = '" + CodeConstant.SMSB_SJLY_LSZSLR + "' AND FSDM = '" + CodeConstant.FSDM_SMSB
//                    + "' AND ((substr(zwbs, 1, 1) <> '0') OR (substr(zwbs, 20, 1) <> '0')))");

            tempVector.addElement("ZYRQ >= to_date('20050101','yyyymmdd')");
            tempVector.addElement("ND=to_char(sysdate,'yyyy')");
            // ended modified by qianchao 2005-12-19

            // tempVector.addElement("jkpzh like '" + pf.getHeadJsjdm() + "%'");
            tempVector.addElement("1=1 order by sbrq desc,sbbh,jkpzh asc ");

            // ��ѯ
            //started added by qianchao 2005-12-19
            long t = System.currentTimeMillis();
            //ended   added by qianchao 2005-12-19
            List tempList = da.query(Sbjkzb.class, tempVector);
            //started added by qianchao 2005-12-19
            Debug.out("com.ttsoft.bjtax.smsb.lszs.processor.LszsCxjksProcessor.doQuery time cost:" + (System.currentTimeMillis() - t));
            //ended   added by qianchao 2005-12-19

            Debug.out("tempList.size()=" + tempList.size());

            HashMap sbmaps = new HashMap();
            SBData sb = null;
            for (int i = 0; i < tempList.size(); i++)
            {
                // ��ʽ��ÿ����¼
                Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
                sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", sbjkzb.getSzdm())); // ˰������
                sb = (SBData) sbmaps.get(sbjkzb.getSbbh());
                if (sb == null)
                {
                    sb = new SBData();
                    sbmaps.put(sbjkzb.getSbbh(), sb);
                }
                sb.addSbjkzb(sbjkzb);
            }

            // ��mapתΪlist
            Iterator c = sbmaps.values().iterator();
            ArrayList datalist = new ArrayList();

            while (c.hasNext())
            {
                datalist.add(c.next());
            }
            // ��ֵ�Ż�form����
            pf.setDataList(datalist);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return pf;
    }

    /**
     * ����
     * 
     * @param vo
     *            ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException
    {
        return null;
    }

    /**
     * ɾ�� modified by qianchao 2005.10.27 �����һƱ��˰�Ĵ���
     * 
     * @param vo
     *            ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {

        Connection conn = null;

        int nret;
        UserData ud = new UserData();
        // from����
        LszsCxjksForm pf = new LszsCxjksForm();
        pf = (LszsCxjksForm) vo.getData();
        ResultSet rs = null;
        int zbcount = 0;

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            // �õ�UserData
            ud = (UserData) vo.getUserData();
            String strSql;
            if (pf.getJksType() == CodeConstant.PRINT_YPYS)
            {
                strSql = "delete from sbdb.sb_jl_sbjkmx " + " where qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                        + " and jsjdm='" + pf.getHeadJsjdm() + "'" + " and jkpzh='" + pf.getHeadJkpzh() + "'";
            }
            else
            {
                strSql = "delete from sbdb.sb_jl_sbjkmx " + " where qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                        + " and jsjdm='" + pf.getHeadJsjdm() + "'" + " and sbbh='" + pf.getHeadSbbh() + "'";
            }
            Debug.out("strSql=" + strSql);

            // " and zwbs like
            // '"+CodeConstant.SMSB_ZWBS+"%"+CodeConstant.SMSB_ZWBS+"'";
            // 1��ɾ����ϸ������
            try
            {
                nret = da.updateSQL(strSql);
                if (nret == 0)
                {
                    // ���û�п�ɾ��������ع�
                    throw new ApplicationException("ɾ����ϸ�����ݳ�������ϸ���ݡ�");
                }
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ����ϸ�����ݳ���");
            }

            // 2�������������
            try
            {
                if (pf.getJksType() == CodeConstant.PRINT_YPYS)
                {
                    strSql = " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" + " and jsjdm='" + pf.getHeadJsjdm() + "'"
                            + " and jkpzh='" + pf.getHeadJkpzh() + "'";
                }
                else
                {
                    strSql = " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" + " and jsjdm='" + pf.getHeadJsjdm() + "'"
                            + " and sbbh='" + pf.getHeadSbbh() + "'";
                }
                rs = da.querySQL("select count(*) from sbdb.sb_jl_sbjkzb " + strSql);
                rs.next();
                zbcount = rs.getInt(1);

                Debug.out("zbcount=" + zbcount);

                strSql = "delete from sbdb.sb_jl_sbjkzb " + strSql + " AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                        + CodeConstant.SMSB_ZWBS + "'  ";

                Debug.out("strSql=" + strSql);

                nret = da.updateSQL(strSql);

                Debug.out("nret=" + nret);

                if (nret != zbcount)
                {
                    // ��ɾ�����ݲ����ϣ���ع�
                    throw new ApplicationException("�����ɿ���ʧ�ܣ����������Ѿ������");
                }

            }
            catch (ApplicationException aex)
            {
                throw aex;
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ���������ݳ���");
            }
            pf.setHeadJkpzh("");
            pf.setHeadSbbh("");
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * ����
     * 
     * @param vo
     *            ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate(VOPackage vo) throws BaseException
    {
        return null;
    }

}
// :-)
