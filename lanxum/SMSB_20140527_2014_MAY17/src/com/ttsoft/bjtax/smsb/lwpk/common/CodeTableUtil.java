package com.ttsoft.bjtax.smsb.lwpk.common;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.dj.model.dm.SWJGZZJG;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;

/**
 * ��ȡ˰�������־ִ����
 * 201307
 * kanght
 * @version 1.1
 */
public class CodeTableUtil
{

    /**
     * ���췽��
     */
    public CodeTableUtil()
    {
    }

    /**
     * ˰�������֯���������
     */
    public final static String SWJGZZJG = "SWJGZZJG";

    //�����е�˰��˰Ŀ�������
    private static Map swjgzzjgMap;
  
  
    /**
     * ����˰�������֯��������
     * @return Map
     * @throws BaseException
     */
    public static Map getSwjgMap() throws BaseException
    {
        Map rtnMap = new TreeMap();
        //�����е�˰��˰Ŀ�������Ϊ�գ��������ݿ����»�ȡ
        if (swjgzzjgMap == null)
        {
            setSwjgzzjgMap();
        }
        rtnMap = swjgzzjgMap;
        return rtnMap;
    }

    public static Map getZgswjg (UserData userData)
        throws BaseException
    {
        Map swjgzzjgMap = getSwjgMap();
        Map swjgMap = new TreeMap();
        //�û�����
        if (userData == null || userData.yhjb.equals(CodeConstants.JBDM_SJ)){
        	//���о�Ȩ��
            Iterator iterator = swjgzzjgMap.keySet().iterator();
            while (iterator.hasNext())
            {
                String dm = (String) iterator.next();
                SWJGZZJG swjgzzjg = (SWJGZZJG) swjgzzjgMap.get(dm);
                if (dm.endsWith("00") == false)
                {
                    continue;
                }
                if (dm.startsWith("90") == true)
                { //���˵��о֣���Ϊ�оֲ���ֱ����Ϊ����˰�����
                    continue;
                }
                swjgMap.put(dm, swjgzzjg);
            }
        }
        else{
        	//���о�Ȩ�����ؾ�Ȩ��
        	//������λ����
            String dm = userData.ssdwdm.substring(0, 2) + "00";
            swjgMap.put(dm, swjgzzjgMap.get(dm));
        }
        return swjgMap;
    }

    /**
     * �����û�Ȩ�޹���˰�����--˰���������
     * �о�Ȩ�ޣ�ȫ������˰���������
     * ���ؾ�Ȩ�ޣ������ؾ�����ȫ��˰�������롣
     * ˰������ȫ�أ��������롣
     * @param userData �û���Ϣ
     * @return ���˺�Ĵ����
     * @throws BaseException �׳�Ӧ���쳣
     */
    public static Map getZgsws(UserData userData) throws BaseException
    {
        Map rtnMap = new TreeMap();
        if (swjgzzjgMap == null)
        {
            setSwjgzzjgMap();
        }
        String filterdm = null;
        if (userData == null ||
            userData.yhjb.equals(CodeConstants.JBDM_SJ))
        { //���оּ�Ȩ��
            filterdm = null;
        }
        else if (userData.yhjb.equals(CodeConstants.JBDM_FJ))
        { //�����ط־ּ�Ȩ��
            filterdm = userData.ssdwdm.substring(0, 2);
        }
        else if (userData.yhjb.equals(CodeConstants.JBDM_SWSJ))
        { //˰������Ȩ��
            filterdm = userData.ssdwdm.substring(0, 4);
            rtnMap.put(userData.ssdwdm, swjgzzjgMap.get(userData.ssdwdm));
            return rtnMap;
        }
        Iterator iterator = swjgzzjgMap.keySet().iterator();
        while (iterator.hasNext())
        {
            String dm = (String) iterator.next();
            if (dm.endsWith("00") == true)
            {
                continue;
            }
            if (filterdm != null && dm.startsWith(filterdm) == false)
            {
                continue;
            }
            rtnMap.put(dm, swjgzzjgMap.get(dm));
        }
        return rtnMap;
    }
    /**
     * ��ʼ��˰�������֯���������
     * @throws BaseException
     */
    private static void setSwjgzzjgMap() throws BaseException
    {
        //�������ݿ�����
        Connection conn = null;
        ResultSet rs = null;
        Statement st;
        conn = SfDBResource.getConnection();
        swjgzzjgMap = new TreeMap();
        try
        {
            st = conn.createStatement();
            //��ѯsql
            String queryString = "SELECT SWJGZZJGDM,SWJGZZJGMC "
                + "FROM DMDB.GY_DM_SWJGZZJG "
                + "WHERE JGZNLX!='3'"
                + " ORDER BY SWJGZZJGDM";

            rs = st.executeQuery(queryString);
            while (rs.next())
            {
                SWJGZZJG swjg = new SWJGZZJG();
                //˰����ش���
                String swjgdm = rs.getString("SWJGZZJGDM");
                swjg.setSwjgzzjgdm(swjgdm);
                //˰���������
                String swjgmc = rs.getString("SWJGZZJGMC");
                swjg.setJc(swjgmc);

                swjgzzjgMap.put(rs.getString("SWJGZZJGDM"), swjg);
            }
            if (rs != null)
            {
                rs.close();
            }
        }
        catch (Exception e)
        {
            throw new ApplicationException("��ѯ˰����ش������");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }


  
}
