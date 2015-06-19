package com.ttsoft.bjtax.test.shenbao.sbzl.qysdsjdks.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.common.model.UserData;
import java.util.HashMap;

public class TestQysdsksProcessor extends TestCase {
    private QysdsksProcessor qysdsksProcessor = null;
    private VOPackage voPackage;
    private static Map retdata;
    protected void setUp() throws Exception {
        super.setUp();
        // ��ʼ��ORMapping
        InitORMapping.init_aux();
        qysdsksProcessor = new QysdsksProcessor();
    }

    protected void tearDown() throws Exception {
        qysdsksProcessor = null;
        super.tearDown();
    }

    /**
     * ������ҵ����˰����Processor��doQuery����
     * Ԥ�ڽ���� �õ���ȷ�ķ�������
     * @throws BaseException
     */
    public void testProcess_M_doQuery() throws BaseException
    {
        voPackage = new VOPackage();
        retdata = new HashMap();
        retdata.put(QysdsksMapConstant.STRING_KEY_JSJDM,"0602580");
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        retdata.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
        voPackage.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
        voPackage.setData(retdata);
        voPackage.setUserData(new UserData());
        try
        {
            voPackage = (VOPackage)qysdsksProcessor.process(voPackage);
            retdata = (Map)voPackage.getData();
            // ���ص����ݲ�Ϊnull
            assertTrue(retdata!=null);
            // �����걨ֵ����Ϊnull
            assertTrue(retdata.get(QysdsksMapConstant.VO_KEY_KSSBSJ)!=null);
            // �Ǽǻ������ݲ�Ϊnull
            assertTrue(retdata.get(QysdsksMapConstant.OBJECT_KEY_DJSJ)!=null);
        }
        catch(Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ������ҵ����˰����Processor��doSave����
     * Ԥ�ڽ�����õ�����ֵnull�����쳣�׳�
     * @throws BaseException
     */
    public void testProcess_M_doSave() throws BaseException
    {
         voPackage = new VOPackage();
         voPackage.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
         voPackage.setData(retdata);
         voPackage.setUserData(new UserData());
         try
         {
             voPackage = (VOPackage)qysdsksProcessor.process(voPackage);
             Object returnData = voPackage.getData();
             // ����ɹ�
             assertTrue(returnData==null);
         }
         catch(Exception ex)
         {
             // �쳣����NoPass
             assertTrue(ex.toString(), false);
         }
    }

    /**
     * ������ҵ����˰����Processor��doDelete����
     * Ԥ�ڽ�����õ�����ֵnull�����쳣�׳�
     * @throws BaseException
     */
    public void testProcess_M_doDelete()
    {
         voPackage = new VOPackage();
         voPackage.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
         voPackage.setData(retdata);
         voPackage.setUserData(new UserData());
         try
         {
             voPackage = (VOPackage)qysdsksProcessor.process(voPackage);
             Object returnData = voPackage.getData();
             // ɾ���ɹ�
             assertTrue(returnData==null);
         }
         catch(Exception ex)
         {
             // �쳣����NoPass
             assertTrue(ex.toString(), false);
         }
     }
}
