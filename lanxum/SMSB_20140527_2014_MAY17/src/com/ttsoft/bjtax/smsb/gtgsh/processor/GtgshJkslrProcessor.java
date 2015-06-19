/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshJkslrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻��ɿ���¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJkslrProcessor
    implements Processor
{
    public GtgshJkslrProcessor ()
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
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        GtgshJkslrForm pf = new GtgshJkslrForm();
        pf = (GtgshJkslrForm) vo.getData();
        //�����
        pf.setSbrq(SfDateUtil.getDate());

        try
        {
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());

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
     * ��ѯ����������ļ���������ѯ���û�����Ϣ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        GtgshJkslrForm pf = new GtgshJkslrForm();
        pf = (GtgshJkslrForm) vo.getData();

        try
        {
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getNsrjsjdm(), ud);
                if (!dj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GTGSH))
                {
                    throw new ApplicationException("����˰�˲��Ǹ��幤�̻���");
                }

                pf.setNsrmc(dj.getNsrmc());
                pf.setGjbzhydm(dj.getGjbzhydm()); //���ұ�׼��ҵ����
                pf.setDjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
                pf.setDjzclxmc(dj.getDjzclxmc()); //
                pf.setDz(dj.getJydz()); //��ַ����Ӫ��ַ
            }
            catch (Exception ex5)
            {
                pf.setNsrmc("");
                pf.setGjbzhydm(""); //���ұ�׼��ҵ����
                pf.setDjzclxdm(""); //�Ǽ�ע�����ʹ���
                pf.setDjzclxmc(""); //
                pf.setDz(""); //��ַ����Ӫ��ַ
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

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
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        String qxdm = ""; //���ش���
        //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        //��ȡform����
        GtgshJkslrForm form = (GtgshJkslrForm) vo.getData();
        //���UserData
        UserData ud = vo.getUserData();
        //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            //��form�ж�Ӧ������Ϣ���浽ֵ����
            qxdm = InterfaceDj.getQxdm(ud); //�õ����ش���
            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
            //�ӵǼǽӿ��л����Ϣ
            //����������Ϣ
            HashMap mapDJ = new HashMap();
            try
            {
                mapDJ = (HashMap) InterfaceDj.getDjInfo(form.getNsrjsjdm(), ud);
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw ExceptionUtil.getBaseException(ex1);
            }
            //Modified by lufeng 2003-12-09
            SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
            if (jbsj == null)
            {
                throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
            }

            //����������Ϣ
            //���������//Modified by lufeng 2003-12-11
            orObj.setJsjdm(form.getNsrjsjdm());
            //����˰�˼���ƴ������˰������һ��ŵ���ע�ֶ�,�Ѷ��Ÿ���
            //Modified by lufeng 2003-12-11

            //�걨����
            orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
            //�Ǽ�ע������
            orObj.setDjzclxdm(jbsj.getDjzclxdm());
            orObj.setDjzclxmc(jbsj.getDjzclxmc());
            //���ұ�׼��ҵ����
            orObj.setGjbzhydm(jbsj.getGjbzhydm());
            orObj.setGjbzhymc(jbsj.getGjbzhymc());
            //˰�������֯����
            orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
            //���ջ���
            orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
            //������ϵ
            orObj.setLsgxdm(jbsj.getLsgxdm());
            orObj.setLsgxmc(jbsj.getLsgxmc());
            //��Ӫ��ַ��ϵ�绰
            orObj.setJydzlxdm(jbsj.getJydzlxdm());
            //¼����
            orObj.setLrr(form.getLrr());
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
            }
            //�걨����
            orObj.setSbrq(nowTime);
            orObj.setCjrq(nowTime); //��������
            orObj.setLrrq(nowTime); //¼������
            orObj.setQxdm(qxdm); //���ش���

            //�õ�������Ϣ//Modified by lufeng 2003-12-09
            ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
            for (int i = 0; i < dmList.size(); i++)
            {
                YHZH yhzh = (YHZH) dmList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    orObj.setYhdm(yhzh.getYhdm()); //���д���
                    orObj.setYhmc(yhzh.getKhyhmc()); //��������
                    orObj.setZh(yhzh.getZh()); //�ʻ�
                    break;
                }
            }

            //�걨��ʽ����
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //������Դ
            orObj.setSjly(CodeConstant.SMSB_SJLY_GTGSHLR);
            //˰������
            orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
            orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
            //�����ݽ��з�Ʊ����
            JksUtil ju = new JksUtil();
            try
            {
                List tempList = (ArrayList) ju.getJkDataLS(orObj,
                    form.getDataList(),
                    CodeConstant.PRINT_YPYS);
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("��������ʧ�ܣ�");
            }

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
    }

    /**
     * ɾ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
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
