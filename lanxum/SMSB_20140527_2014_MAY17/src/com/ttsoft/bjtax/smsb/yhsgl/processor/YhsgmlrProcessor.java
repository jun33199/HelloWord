/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import java.sql.Timestamp;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ¼��ӡ��˰������� ��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */

public class YhsgmlrProcessor
    implements Processor
{
    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsgmlrProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                doShow(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_READERACTION:
                doReader(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @throws BaseException
     */
    private void doShow (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * ����¼���ӡ��˰�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsgmlrForm form = (YhsgmlrForm) vo.getData();
        //ȡ�õ�λ����
        if (form.getGhrxm().equals(""))
        {
            form.setDwmc(form.getGhdwmc());
        }
        else
        {
            form.setDwmc(form.getGhrxm());
        }

        Yhsgmz orObj = new Yhsgmz();
        String columns[] =
            {
            "dsjsjdm", "cjsj", "lrrq", "dsdwmc", "sjly",
            "swjgzzjgdm", "fsdm", "lrr", "hjje"};

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm(); //userData������˰�������֯��������
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);//���۵�λ���������
            if (strDsJsjdm == null || strDsJsjdm.equals(""))
            {
                throw new ApplicationException("δ�õ���Ӧ�Ĵ��ۼ�������룡");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
            }
            //�������ݴ���
            if (form.getDwmc().equals(""))
            {
                throw new ApplicationException("������λ(��)����Ϊ�գ�");
            }
            if (Float.parseFloat(form.getHjje()) <= 0)
            {
                throw new ApplicationException("û�пɱ�������ݣ�");
            }

            BeanUtil.copyBeanToBean(columns, form, orObj);
            Vector condition = new Vector();
            String xspzh = getNextXspzh(orObj.getDsjsjdm());
            orObj.setXspzh(xspzh);
            Timestamp cjrq = TinyTools.getDBTimestamp(conn);
            orObj.setCjrq(cjrq);
            orObj.setLrrq(cjrq);
            orObj.setQxdm(InterfaceDj.getQxdm(userData));
            if (orObj.getCjrq() != null)
            {
                orObj.setNd("" + TinyTools.getYear(orObj.getCjrq()));
            }
            orObj.setZhdm(userData.getXtsbm1()); //�˻�����
            orObj.setJzbs("0"); //δ����
            condition.add("qxdm='" + InterfaceDj.getQxdm(userData) + "'");
            condition.add("xspzh='" + xspzh + "'");
            condition.add("dsjsjdm='" + form.getDsjsjdm() + "'");
            if (da.query(orObj.getClass(), condition).size() > 0)
            {
                throw new ApplicationException("¼������������Ѵ��ڣ�");
            }
            else
            {
                try
                {
                    da.insert(orObj);
                }
                catch (BaseException ex3)
                {
                    ex3.printStackTrace();
                    throw new ApplicationException("����¼����������ݳ���");
                } //end try
            } //end if

            //��ϸ���ݴ���
            ArrayList list = form.getDataList();
            for (int i = 0; i < list.size(); i++)
            {
                HashMap map = (HashMap) list.get(i);
                //���û���������򲻱���
                if (map.get("gpsl").equals(""))
                {
                    continue;
                }
                Yhsgmmx orMx = new Yhsgmmx();
                map.remove("pzzldm");
                map.remove("mz");
                map.remove("pzzlmc");
                map.put("xspzh", xspzh);
                map.put("dsjsjdm", form.getDsjsjdm());
                map.put("jsjdm", form.getJsjdm());
                map.put("dwmc", form.getDwmc());
                map.put("gjdqdm", form.getGjdqdm());
                map.put("zjlxdm", form.getZjlxdm());
                map.put("zjhm", form.getZjhm());
                map.put("swjgzzjgdm", form.getSwjgzzjgdm());
                BeanUtil.populate(orMx, map);
                orMx.setNd("" + orObj.getNd());

//                orMx.setCjrq(SfTimeUtil.getNowTimestamp());
                orMx.setCjrq(cjrq);
                orMx.setLrrq(cjrq);
                orMx.setQxdm(InterfaceDj.getQxdm(userData));
                Vector conditionmx = new Vector();
                conditionmx.add("qxdm='" + InterfaceDj.getQxdm(userData) + "'");
                conditionmx.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                conditionmx.add("xspzh='" + form.getXspzh() + "'");
                conditionmx.add("spmzdm='" + map.get("spmzdm") + "'");
                if (da.query(orMx.getClass(), conditionmx).size() > 0)
                {
                    throw new ApplicationException("¼���������ϸ�����Ѵ��ڣ�");
                }
                else
                {
                    try
                    {
                        da.insert(orMx);
                    }
                    catch (BaseException ex3)
                    {
                        ex3.printStackTrace();
                        throw new ApplicationException("����¼���������ϸ���ݳ���");
                    } //end try
                } //end if
            } //end for

            List tempDataList = (List) form.getDataList().clone();
            List inputDataList = new ArrayList();
            for (int i = 0; i < tempDataList.size(); i++)
            {
                Map map = (Map) tempDataList.get(i);
                if (map.get("gpsl") == null
                    || ( (String) map.get("gpsl")).equals("")
                    || ( (String) map.get("gpsl")).equals("0"))
                {
                    //donothing
                }
                else
                {
                    map.put("sequence", "" + (i + 1));
                    inputDataList.add(map);
                }
            }
            for (int i = 0; i < inputDataList.size(); i++)
            {
                Map map = (Map) inputDataList.get(i);
            }
            //����Ʊ֤�ӿ�ӡ��˰Ʊ���飬�����¿��
            String zhdm = vo.getUserData().getXtsbm1();
            List yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                            checkSpsl(vo.getUserData(), zhdm,
                                      (List) form.getDataList().clone());
            checkYhspsl(yhspList);
            return form;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        } //end try
    }

    /**
     * doReader
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @throws BaseException
     */
    private void doReader (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * �������ƾ֤��
     * @param dsjsjdm ���۵�λ���������
     * @return nextXspzh
     * @throws java.lang.Exception
     */
    private static String getNextXspzh (String dsjsjdm)
        throws
        Exception
    {
        StringBuffer sequence = new StringBuffer();
        long nextXspzh = 0;
        //���ݿ�����
        Connection conn = null;
        try
        {
            // ������ݿ�����
            conn = SfDBResource.getConnection();
            StringBuffer sqlbuffer = new StringBuffer();
            sqlbuffer.append("SELECT MAX(TO_NUMBER(XSPZH)) sequ ")
                .append("from SBDB.SB_JL_YHSGMZ ")
                .append("WHERE DSJSJDM = '")
                .append(dsjsjdm)
                .append("'");
            SfDBUtils sfDB = new SfDBUtils(conn);
            SfHashList sequList = sfDB.getData(sqlbuffer.toString());
            if (sequList.size() > 0)
            {
                nextXspzh = sequList.getLong(0, "sequ", 0);
            }
            nextXspzh++;
            String nextXspzhStr = (nextXspzh > 0) ? String.valueOf(nextXspzh)
                                  : "1";
            for (int i = 0; i < 8 - nextXspzhStr.length(); i++)
            {
                sequence.append("0");
            }
            sequence.append(nextXspzhStr);
            return sequence.toString();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�������ƾ֤��ʧ��!");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * ����UerData�õ����۵�λ���������
     * @param strSsdwdm ������λ����
     * @return ���۵�λ���������
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

    /**
     * �������Ʊ֤�ӿڷ��ص�List���Ƿ���Error Message���������ڿͻ�����ʾ
     * @param yhspList ӡ��˰Ʊ�б�
     * @throws ApplicationException
     */
    private void checkYhspsl (List yhspList)
        throws ApplicationException
    {
        String errorMessage = "";
        for (int i = 0; i < yhspList.size(); i++)
        {
            Map map = (Map) yhspList.get(i);
            if (map.get("error") != null)
            {
                int intError = Integer.parseInt( (String) map.get("error"));
                if (intError == -1)//��ǰ����Ա��Ȩ���۴���ӡ��˰Ʊ
                {
                    errorMessage = errorMessage + "�������Ϊ" +
                                   (String) map.get("sequence") + "��ӡ��˰Ʊ��" +
                                   "��û��Ȩ�����۴���ӡ��˰Ʊ��<BR>";
                }
                else//��ǰ����Ա�����۵Ĵ���ӡ��˰Ʊ���������
                {
                    errorMessage = errorMessage + "�������Ϊ" +
                                   (String) map.get("sequence") + "��ӡ��˰Ʊ��" +
                                   "����ӡ��˰Ʊ�Ŀ������Ϊ" + intError + "��<BR>";
                }
            }
        }
        if (!errorMessage.equals(""))
        {
            throw new ApplicationException(errorMessage);
        }
    }
}