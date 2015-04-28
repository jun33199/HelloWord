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
        // 初始化ORMapping
        InitORMapping.init_aux();
        qysdsksProcessor = new QysdsksProcessor();
    }

    protected void tearDown() throws Exception {
        qysdsksProcessor = null;
        super.tearDown();
    }

    /**
     * 测试企业所得税亏损Processor的doQuery方法
     * 预期结果： 得到正确的返回数据
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
            // 返回的数据不为null
            assertTrue(retdata!=null);
            // 亏损申报值对象不为null
            assertTrue(retdata.get(QysdsksMapConstant.VO_KEY_KSSBSJ)!=null);
            // 登记基本数据不为null
            assertTrue(retdata.get(QysdsksMapConstant.OBJECT_KEY_DJSJ)!=null);
        }
        catch(Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试企业所得税亏损Processor的doSave方法
     * 预期结果：得到返回值null，无异常抛出
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
             // 保存成功
             assertTrue(returnData==null);
         }
         catch(Exception ex)
         {
             // 异常捕获，NoPass
             assertTrue(ex.toString(), false);
         }
    }

    /**
     * 测试企业所得税亏损Processor的doDelete方法
     * 预期结果：得到返回值null，无异常抛出
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
             // 删除成功
             assertTrue(returnData==null);
         }
         catch(Exception ex)
         {
             // 异常捕获，NoPass
             assertTrue(ex.toString(), false);
         }
     }
}
