package com.ttsoft.bjtax.test.shenbao.sbzl.wqyys.processor;

import junit.framework.*;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.processor.*;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

public class TestWqyysProcessor extends TestCase {

    private WqyysProcessor wqyysProcessor = null;

    // 企业计算机代码
    private String jsjdm = "0602580";
    private String szsmdm = "02";
    private String tester = "test";
    private String jm = "007";
    private String fsdm = "05";

    protected void setUp() throws Exception
    {
        super.setUp();
        InitORMapping.init_aux();
        System.out.println("进入外企营业税测试用例。");
        wqyysProcessor = new WqyysProcessor();
    }

    protected void tearDown() throws Exception
    {
        wqyysProcessor = null;
        System.out.println("退出外企营业税测试用例。");
        super.tearDown();
    }

    private UserData getUserData(){
        UserData uData = new UserData();
        uData.yhid = this.jsjdm;
            return uData;
    }

    public void testWqyysProcessorMdoSave()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(WqyysActionConstant.INT_ACTION_SAVE);
            voPackage.setData(buildParameter(WqyysActionConstant.
                                             INT_ACTION_SAVE));
            voPackage = (VOPackage)this.wqyysProcessor.process(voPackage);
            Map map = (Map)voPackage.getData();
            Assert.assertEquals(map.get(WqyysMapConstant.RESULT), Boolean.TRUE);
            System.out.println("外企营业税测试用例doSave()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("外企营业税测试用例doSave()异常中止。");
        }
    }

    public void testWqyysProcessorMdoQuery()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(WqyysActionConstant.INT_ACTION_QUERY);
            voPackage.setData(buildParameter(WqyysActionConstant.
                                             INT_ACTION_QUERY));
            voPackage = (VOPackage)this.wqyysProcessor.process(voPackage);
            Map map = (Map)voPackage.getData();
            List wqyysList = (List)map.get(WqyysMapConstant.LIST_WQYYS);
            Assert.assertEquals(wqyysList.size(), 1);
            System.out.println("外企营业税测试用例doQuery()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("外企营业税测试用例doQuery()异常中止。");
        }
    }

    public void testWqyysProcessorMdoDelete()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(WqyysActionConstant.INT_ACTION_DELETE);
            voPackage.setData(buildParameter(WqyysActionConstant.
                                             INT_ACTION_DELETE));
            voPackage = (VOPackage)this.wqyysProcessor.process(voPackage);
            Map map = (Map)voPackage.getData();
            Assert.assertEquals(map.get(WqyysMapConstant.RESULT), Boolean.TRUE);
            System.out.println("外企营业税测试用例doDelete()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("外企营业税测试用例doDelete()异常中止。");
        }
    }

    private Map buildParameter(int action)
    {
        Map data = new HashMap();
        List list = new ArrayList();
        Map sksjrqMap = Skssrq.monthSkssrq(new Date());
        Timestamp ksrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ);
        Timestamp jsrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ);
        switch(action) {
            case WqyysActionConstant.INT_ACTION_SAVE:
		list.add(newInstanceWqyys(jsjdm, ksrq, jsrq));
                data.put(WqyysMapConstant.LIST_WQYYS, list);
                break;
            case WqyysActionConstant.INT_ACTION_QUERY:
                data.put(WqyysMapConstant.JSJDM, jsjdm);
                data.put(WqyysMapConstant.SKSSKSRQ, TinyTools.Date2String(new Date((ksrq).getTime())));
                data.put(WqyysMapConstant.SKSSJSRQ, TinyTools.Date2String(new Date((jsrq).getTime())));
                break;
            case WqyysActionConstant.INT_ACTION_DELETE:
                list.add(newInstanceWqyys(jsjdm, ksrq, jsrq));
                data.put(WqyysMapConstant.LIST_WQYYS, list);
                break;
        }
        return data;
    }

    private Wqyys newInstanceWqyys(String jsjdm, Timestamp skssksrq, Timestamp skssjsrq)
    {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Wqyys wqyys = new Wqyys();
	wqyys.setJsjdm(jsjdm);
        wqyys.setCjsj(timestamp);
        wqyys.setSzsmdm(this.szsmdm);
        wqyys.setSkssksrq(skssksrq);
        wqyys.setSkssjsrq(skssjsrq);
        wqyys.setSbrq(timestamp);
        wqyys.setSwjgzzjgdm(this.jm);
        wqyys.setLrr(this.tester);
        wqyys.setLrrq(timestamp);
        wqyys.setFsdm(this.fsdm);
        return wqyys;
    }
}