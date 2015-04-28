package com.ttsoft.bjtax.test.shenbao.sbzl.yhs.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import java.util.Map;
import java.util.HashMap;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import java.util.List;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;


public class TestYhsProcessor extends TestCase {
    private YhsProcessor yhsProcessor = null;
    private static VOPackage voPackage;
    private static Map retdata;

    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        yhsProcessor = new YhsProcessor();
    }

    protected void tearDown() throws Exception {
        yhsProcessor = null;
        super.tearDown();
    }

    /**
     * 测试印花税Processor的doQuery方法
     * 预期结果： 得到正确的返回数据
     * @throws BaseException
     */
    public void testProcess_M_doQuery() throws BaseException
    {
        voPackage = new VOPackage();
        Map data = new HashMap();
        UserData userData = new UserData();
        userData.yhid = "0602580";
        userData.gxswjgzzjgdm = "001002";

        data.put(YhsMapConstant.JSJDM, "0602580");
        voPackage.setData(data);
        voPackage.setAction(YhsActionConstant.INT_ACTION_QUERY);
        voPackage.setUserData(userData);
        try
        {
          voPackage = (VOPackage)yhsProcessor.process(voPackage);
          retdata = (Map)voPackage.getData();
//          assertEquals("return value", expectedReturn, actualReturn);

            // 正确取得所有数据
            assertTrue(retdata != null);
            // 印花税报告主表数据
            assertTrue(((Yhsbgz)retdata.get(YhsMapConstant.YHSBGZ))!=null);
            // 印花税报告明细数据
            assertTrue(((List)retdata.get(YhsMapConstant.LIST_YHSBGMX))!=null);
            assertTrue(((List)retdata.get(YhsMapConstant.LIST_YHSBGMX)).size()>0);
            // 登记基本数据
            assertTrue(((SWDJJBSJ)retdata.get(YhsMapConstant.JBSJ))!=null);

        }
        catch (Exception ex)
        {
          assertTrue(ex.toString(),false);
        }
    }

    /**
     * 测试YhsProcessor的doSave方法
     * 预期结果： 得到返回结果null
     * @throws BaseException
     */
    public void testProcessor_M_doSave() throws BaseException
    {
        voPackage.setAction(YhsActionConstant.INT_ACTION_SAVE);
        try
        {
            VOPackage vo = (VOPackage)yhsProcessor.process(voPackage);
            Map retdata = (Map)vo.getData();
            assertTrue(retdata == null);
        }
        catch(Exception ex)
        {
           assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试YhsProcessor的doDelete方法
     * 预期结果： 得到返回值null
     * @throws BaseException
     */
    public void testProcessor_M_doDelete() throws BaseException
    {
        voPackage.setAction(YhsActionConstant.INT_ACTION_DELETE);
        voPackage.setData(retdata);
        try
        {
            VOPackage vo = (VOPackage)yhsProcessor.process(voPackage);
            Map retdata = (Map)vo.getData();
            assertTrue(retdata == null);
        }
        catch(Exception ex)
        {
           assertTrue(ex.toString(), false);
        }
    }

}
