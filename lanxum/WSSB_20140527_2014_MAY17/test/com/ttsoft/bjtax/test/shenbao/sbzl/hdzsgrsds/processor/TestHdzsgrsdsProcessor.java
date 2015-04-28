package com.ttsoft.bjtax.test.shenbao.sbzl.hdzsgrsds.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.math.BigDecimal;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzsqysb;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.processor.HdzsgrsdsProcessor;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

public class TestHdzsgrsdsProcessor extends TestCase {
    private HdzsgrsdsProcessor hdzsgrsdsProcessor = null;

    private Hdzsqysb qysbsj;
    private List tzzsbsjList;

    // 企业计算机代码
    private String jsjdm = "0602580";
    private String zsl = "0.2";

    public TestHdzsgrsdsProcessor(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        InitORMapping.init_aux();
        System.out.println("进入核定征收测试用例。");
        hdzsgrsdsProcessor = new HdzsgrsdsProcessor();
    }

    protected void tearDown() throws Exception
    {
        hdzsgrsdsProcessor = null;
        System.out.println("退出核定征收测试用例。");
        super.tearDown();
    }

    private UserData getUserData(){
        UserData uData = new UserData();
        uData.yhid = this.jsjdm;
            return uData;
    }

    public void testHdzsgrsdsProcessMdoSave()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_SAVE);
            voPackage.setData(buildParameter(HdzsgrsdsActionConstant.
                                             INT_ACTION_SAVE));
            voPackage = (VOPackage)this.hdzsgrsdsProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(HdzsgrsdsMapConstant.RESULT), Boolean.TRUE);
            System.out.println("核定征收测试用例doSave()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("核定征收测试用例doSave()异常中止。");
        }
    }

    public void testHdzsgrsdsProcessMdoQuery()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_QUERY);
            voPackage.setData(buildParameter(HdzsgrsdsActionConstant.
                                             INT_ACTION_QUERY));
            voPackage = (VOPackage)this.hdzsgrsdsProcessor.process(voPackage);
            Map data = (Map)voPackage.getData();
            this.qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
            this.qysbsj.setFsdm(CodeConstant.FSDM_WSSB);
            this.tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);
            Assert.assertNotNull(this.qysbsj);
            Assert.assertNotNull(this.tzzsbsjList);
            Assert.assertTrue(this.tzzsbsjList.size() != 0);
            System.out.println("核定征收测试用例doQuery()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("核定征收测试用例doQuery()异常中止。");
        }
    }

    public void testHdzsgrsdsProcessMdoDelete()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_DELETE);
            voPackage.setData(buildParameter(HdzsgrsdsActionConstant.
                                             INT_ACTION_DELETE));
            voPackage = (VOPackage)this.hdzsgrsdsProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(HdzsgrsdsMapConstant.RESULT), Boolean.TRUE);
            System.out.println("核定征收测试用例doDelete()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("核定征收测试用例doDelete()异常中止。");
        }
    }

    private Map buildParameter(int action) throws BaseException
    {
        Map data = new HashMap();
        List list = new ArrayList();
        switch(action) {
            case HdzsgrsdsActionConstant.INT_ACTION_SAVE:
                if(this.qysbsj == null) {
                    this.testHdzsgrsdsProcessMdoQuery();
                }
                data.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
                data.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                break;
            case HdzsgrsdsActionConstant.INT_ACTION_DELETE:
                if(this.qysbsj == null) {
                    this.testHdzsgrsdsProcessMdoQuery();
                }
                data.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
                data.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                break;
            case HdzsgrsdsActionConstant.INT_ACTION_QUERY:
                data.put(HdzsgrsdsMapConstant.JSJDM, this.jsjdm);
                data.put(HdzsgrsdsMapConstant.ZSL, this.zsl);
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                    = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
                List tzfList = sfProxy.getTzfInfo(jsjdm);
		data.put(HdzsgrsdsMapConstant.LIST_TZF, tzfList);
                break;
        }
        return data;
    }

}