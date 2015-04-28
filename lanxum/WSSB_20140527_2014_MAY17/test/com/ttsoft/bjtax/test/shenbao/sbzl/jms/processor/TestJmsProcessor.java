package com.ttsoft.bjtax.test.shenbao.sbzl.jms.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.jms.processor.*;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.common.model.UserData;
import java.util.HashMap;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import java.sql.Timestamp;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import java.math.BigDecimal;
import java.util.ArrayList;

public class TestJmsProcessor extends TestCase {
    private JmsProcessor jmsProcessor = null;

    public TestJmsProcessor(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        // 初始化ORMapping
        InitORMapping.init_aux();
        jmsProcessor = new JmsProcessor();
    }

    protected void tearDown() throws Exception {
        jmsProcessor = null;
        super.tearDown();
    }

    public void testQuery_01()
    {
        Debug.testCase("减免税查询测试例_01");
        Map data = new HashMap();
        data.put(JmsMapConstant.SBRQ, new Timestamp(System.currentTimeMillis()));
        //生成VOPackage
        VOPackage voPackage = new VOPackage();
        UserData userData = new UserData();
        userData.yhid = "0602580";
        //设定参数
        voPackage.setUserData(userData);
        voPackage.setAction(JmsActionConstant.INT_ACTION_QUERY);
        voPackage.setData(data);
        try
        {
            voPackage = (VOPackage)jmsProcessor.process(voPackage);
            data = (Map)voPackage.getData();
            assertTrue(data!=null);
            assertTrue(((List)data.get(JmsMapConstant.LIST_JMYSB))!=null);
        }
        catch(Exception ex)
        {
           assertTrue(ex.toString(),false);
        }
    }

    public void testProcess_M_doSave()
    {
        VOPackage vo = new VOPackage();
        Map data = new HashMap();
        UserData userData = new UserData();
        userData.yhid = "0602580";
        List jmList = new ArrayList();
        Jm jm = new Jm();
        jm.setCjsj(new Timestamp(System.currentTimeMillis()));
        jm.setDjzclxdm("120");
        jm.setFsdm("5");
        jm.setGjbzhydm("001");
        jm.setJmlx("dfs");
        jm.setJmse(new BigDecimal("2000"));
        jm.setJmxmdm("52");
        jm.setJsjdm("0602580");
        jm.setJsje(new BigDecimal("100000"));
        jm.setJzbz("0");
        jm.setKssl(new BigDecimal("2"));
        jm.setLrr("0602580");
        jm.setLrrq(new Timestamp(System.currentTimeMillis()));
        jm.setNsrmc("dsfsdf");
        jm.setSbrq(new Timestamp(System.currentTimeMillis()));
        jm.setSkssjsrq(new Timestamp(System.currentTimeMillis()));
        jm.setSkssksrq(new Timestamp(System.currentTimeMillis()));
        jm.setSwjgzzjgdm("050");
        jm.setSzsmdm("125000");
        jm.setYsjcdm("025");
        jm.setYskmdm("020");
        jmList.add(jm);
        data.put(JmsMapConstant.LIST_JMSB, jmList);
        vo.setData(data);
        vo.setUserData(userData);
        vo.setAction(JmsActionConstant.INT_ACTION_SAVE);
        try
        {
            vo = (VOPackage)jmsProcessor.process(vo);
            data = (Map)vo.getData();
            assertTrue(data!=null);
            assertTrue(((Boolean)(data.get(JmsMapConstant.RESULT))).booleanValue());
        }
        catch(Exception ex)
        {
           assertTrue(ex.toString(),false);
        }
    }
}
