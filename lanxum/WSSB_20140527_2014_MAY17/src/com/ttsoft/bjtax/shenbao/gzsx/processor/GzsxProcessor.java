package com.ttsoft.bjtax.shenbao.gzsx.processor;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.ekernel.db.or.*;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��֪�����processor</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Stone
 * @version 1.0 2003-9-1
 */

public class GzsxProcessor implements Processor
{
    private static final int SESSION_ID = 0;

    private static final String DJ_JBSJ = "JBSJ";
    public GzsxProcessor()
    {
    }

    /**
     * ʵ�ֽӿ�Processor��process����
     * @param vo VOPackage������
     * @return ���ش�����Ϊ��֪�����List
     * @throws BaseException �����쳣
     */
    public Object process(VOPackage vo) throws BaseException
    {
        try
        {
            if(vo.getAction() == ActionConstant.INT_ACTION_QUERY)
            {
                //��ѯ��֪��Ϣ
                return doQueryGzsxData(vo);
            }
            else
            {
                throw ExceptionUtil.getBaseException(new Exception(),"�������Ϸ�!");
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex,"��ѯʧ��!");
        }
    }

    /**
     * ��ѯ��֪����
     * @param vo VOPackage��
     * @return List ��ѯ���ĸ�֪��Ϣ
     * @throws java.lang.Exception �����쳣
     */
    public List doQueryGzsxData(VOPackage vo) throws Exception
    {
        Connection conn = null;
        try
        {
            String jsjdm = (String)vo.getData();
            //�õ���ǰʱ��
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = format.format(new java.util.Date());
            //��ѯ�ķ��ؽ��
            List queryresult = new ArrayList();

            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);

            String swjgzzjgdm2 = jbsj.getSwjgzzjgdm();
            String qxdm = swjgzzjgdm2.substring(0, 2);
            String superSwjgzzjgdm = swjgzzjgdm2.substring(0, 2) + "00";
            String gjbzhydm = jbsj.getGjbzhydm();
            String djzclxdm = jbsj.getDjzclxdm();

            // ��ѯ�󷵻����ݵ�������
            //	1	ʱ����ڵ����ݿ��¼�ļ����������ڴ���ļ��������
            //	2	ʱ����ڵ����ݿ��¼�ļ�����������*
            //		�������ݿ��¼��GJBZHYDM����0���ߴ����GJBZHYDM
            //		�������ݿ��¼��DJZCLXDM����0���ߴ����DJZCLXDM
            //		�������ݿ��¼��SWJGZZJGDM2����0���ߴ����SWJGZZJGDM2���ߵ������־���
            //add by wanghw
            StringBuffer sqlW = new StringBuffer();
            sqlW.append("GZSXKSRQ <= to_date('").append(strDate)
                .append("','yyyy-mm-dd') AND GZSXJSRQ >= to_date('")
                .append(strDate).append("','yyyy-mm-dd')")
                .append("  AND ((JSJDM = '").append(jsjdm)
                .append("' AND QXDM = '").append(qxdm)
                .append("') OR (QXDM = '").append(qxdm)
                .append("' AND JSJDM = '*' AND ")
                .append("DJZCLXDM IN ('0','").append(djzclxdm).append("') AND ")
                .append("GJBZHYDM IN ('0','").append(gjbzhydm).append("') AND ")
                .append("SWJGZZJGDM2 IN ('0','").append(swjgzzjgdm2).append("','")
                .append(superSwjgzzjgdm)
                .append("')) OR (JSJDM = '*' AND ")
                .append("DJZCLXDM = '0' AND ")
                .append("GJBZHYDM = '0' AND ")
                .append("SWJGZZJGDM2 = '0'")
                .append(")) order by cjrq desc");

            Vector criteria = new Vector();
            criteria.add(sqlW.toString());
            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            ORContext orCtx = new ORContext(Gzsx.class.getName(), criteria);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            queryresult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx);

            return queryresult;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
}