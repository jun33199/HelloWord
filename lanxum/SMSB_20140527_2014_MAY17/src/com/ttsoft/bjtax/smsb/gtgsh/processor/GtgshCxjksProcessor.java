/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshCxjksForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻������ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshCxjksProcessor
    implements Processor
{
    public GtgshCxjksProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
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
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ�������ò�ѯ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        GtgshCxjksForm pf = new GtgshCxjksForm();
        try
        {
            pf = (GtgshCxjksForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
        }
        return pf;
    }

    /**
     * ��ѯ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
        private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        String names[] =
                         {
                         "jkpzh", "jsjdm", "szdm", "nsrmc", "swjgzzjgmc",
                         "sbrq",
                         "sjje"};

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        GtgshCxjksForm pf = new GtgshCxjksForm();
        pf = (GtgshCxjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //���ò�ѯ����
            Vector tempVector = new Vector();

            tempVector.addElement("lrr='" + pf.getLrrdm() + "'");
            tempVector.addElement("sjly='" + CodeConstant.SMSB_SJLY_GTGSHLR
                                  + "'");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                                  + CodeConstant.SMSB_ZWBS + "'");
            tempVector.addElement("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh like '" + pf.getHeadJsjdm() + "%'");
            tempVector.addElement("1=1 order by sbrq desc,jkpzh desc ");

            //��ѯ
            List tempList = da.query(Sbjkzb.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("û�з��������ļ�¼��");
            }
            for (int i = 0; i < tempList.size(); i++)
            {
                //��ʽ��ÿ����¼
                Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
                sbjkzb.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM",
                    sbjkzb.getSzdm())); //˰������
                //��ֵ�����ֵ����Map
                HashMap map = new HashMap();

                try
                {
                    BeanUtil.copyBeanToMap(names, sbjkzb, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("����ת������");
                }
                dataList.add(map);
            }
            //��ֵ�Ż�form����
            pf.setDataList(dataList);

        }
        catch (Exception ex)
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
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ɾ����������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from����
        GtgshCxjksForm pf = new GtgshCxjksForm();
        pf = (GtgshCxjksForm) vo.getData();
        dataList = pf.getDataList();

        //ormapping����
        Sbjkzb orObjz = new Sbjkzb();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();

            String strSql = "delete from sbdb.sb_jl_sbjkmx " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and jkpzh='" + pf.getHeadJkpzh() + "'";
            //1��ɾ����ϸ������
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ����ϸ�����ݳ���");
            }

            //2�������������
            orObjz.setQxdm(InterfaceDj.getQxdm(ud)); //���ش���
            orObjz.setJkpzh(pf.getHeadJkpzh());
            orObjz.setJsjdm(pf.getHeadJsjdm());
            try
            {
                da.delete(orObjz);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ���������ݳ���");
            }
            pf.setHeadJkpzh("");
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
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

}
//:-)
