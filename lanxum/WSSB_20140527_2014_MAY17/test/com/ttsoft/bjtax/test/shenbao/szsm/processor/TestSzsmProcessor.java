package com.ttsoft.bjtax.test.shenbao.szsm.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.szsm.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.bjtax.shenbao.szsm.SzsmActionConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.ttsoft.bjtax.shenbao.szsm.SzsmMapConstant;
import java.sql.Timestamp;
import com.ttsoft.common.model.UserData;
import java.util.ArrayList;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import java.math.BigDecimal;

public class TestSzsmProcessor extends TestCase {
    private SzsmProcessor szsmProcessor = null;

    protected void setUp() throws Exception {
        super.setUp();
        // 初始化ORMapping
        InitORMapping.init_aux();
        szsmProcessor = new SzsmProcessor();
    }

    protected void tearDown() throws Exception {
        szsmProcessor = null;
        super.tearDown();
    }

    /**
     * 测试SzsmProcessor的queryPreviousSzsm方法
     * 预期结果：正确返回数据，无异常抛出
     * @throws BaseException
     */
    public void testProcess_M_queryPreviousSzsm() throws BaseException
    {
        VOPackage vo = new VOPackage();
        UserData userData = new UserData();
        userData.yhid = "0602580";

        vo.setAction(SzsmActionConstant.QUERY_PREVIOUS_SZSM);
        vo.setData("0602580");
        vo.setUserData(userData);
        try
        {
            List actualReturn = (List)szsmProcessor.process(vo);
            // 得到的返回值不为null，Pass！
            assertTrue(actualReturn!=null);
        }
        catch(Exception ex)
        {
            // 有异常抛出, NOPass!
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试SzsmProcessor的getJjlxglszsmList和getSbSzsmZqrlList方法
     * 预期结果：得到所有正确的返回值
     * @throws BaseException
     */
    public void testProcess_M_getJjlxglszsmList_getSbSzsmZqrlList() throws BaseException
    {
        VOPackage vo = new VOPackage();
        UserData userData = new UserData();
        userData.yhid = "0602580";
        Map data = new HashMap();
        vo.setData("120");
        vo.setAction(SzsmActionConstant.QUERY_SZSMTREE_INFO);
        vo.setUserData(userData);
        try
        {
             data = (Map)szsmProcessor.process(vo);
             // 所有返回数据不为null, Pass
             assertTrue(data!=null);
             assertTrue((List)data.get(SzsmMapConstant.SZSM_LIST)!=null);
             assertTrue((List)data.get(SzsmMapConstant.JJLXGLSZSM_LIST)!=null);
        }
        catch(Exception ex)
        {
            // 有异常抛出，NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试SzsmProcessor的refreshFavorite方法
     * 预期结果：得到返回值null，无异常抛出
     * @throws BaseException
     */
    public void testProcess_M_refreshFavorite() throws BaseException
    {
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        UserData userData = new UserData();
        userData.yhid = "0602580";
        List dataList = new ArrayList(1);
        Sqsbtmp sqsb = new Sqsbtmp();
        sqsb.setJsjdm("0602580");
        sqsb.setJsje(new BigDecimal("5000.00"));
        sqsb.setKssl(new BigDecimal("2.00"));
        sqsb.setSjse(new BigDecimal("10000.00"));
        sqsb.setSzdm("16");
        sqsb.setSzmc("印花税");
        sqsb.setSzsmdm("161220");
        sqsb.setSzsmmc("其它帐簿");
        dataList.add(sqsb);
        vo.setAction(SzsmActionConstant.REFRESH_FAVORITE);
        vo.setData(dataList);
        vo.setUserData(userData);
        try
        {
            Object obj = szsmProcessor.process(vo);
            // 返回数据为NULL， 无异常抛出。 Pass
            assertTrue(obj==null);
        }
        catch(Exception ex)
        {
            // 异常抛出. No Pass
            assertTrue(ex.toString(), false);
        }
    }
}
