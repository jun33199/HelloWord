package com.ttsoft.bjtax.test.shenbao.sbzl.czzsnd.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.sql.Timestamp;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.processor.CzzsndProcessor;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbzb;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;

public class TestCzzsndProcessor extends TestCase {
    private CzzsndProcessor czzsndProcessor = null;

    private Czzsnbzb czzsnbzb;
    private List qysbsjList;
    private List tzzsbsjList;
    private List fpblsjList;
    private List jmsjList;

    // 企业计算机代码
    private String jsjdm = "0602580";

    public TestCzzsndProcessor(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        System.out.println("进入查账征收年度测试用例。");
        czzsndProcessor = new CzzsndProcessor();
    }

    protected void tearDown() throws Exception {
        czzsndProcessor = null;
        System.out.println("退出查账征收年度测试用例。");
        super.tearDown();
    }

    private UserData getUserData(){
        UserData uData = new UserData();
        uData.yhid = this.jsjdm;
            return uData;
    }

    public void testCzzsndProcessMdoSave()
    {
        System.out.println("查账征收年度测试用例doSave()。");
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsndActionConstant.INT_ACTION_SAVE);
            voPackage.setData(buildParameter(CzzsndActionConstant.
                                             INT_ACTION_SAVE));
            voPackage = (VOPackage)this.czzsndProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(CzzsndMapConstant.RESULT), Boolean.TRUE);
            System.out.println("查账征收年度测试用例doSave()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收年度测试用例doSave()异常中止。");
        }
    }

    public void testCzzsndProcessMdoQuery()
    {
        System.out.println("查账征收年度测试用例doQuery()。");
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsndActionConstant.INT_ACTION_QUERY);
            voPackage.setData(buildParameter(CzzsndActionConstant.
                                             INT_ACTION_QUERY));
            voPackage = (VOPackage)this.czzsndProcessor.process(voPackage);
            Map data = (Map)voPackage.getData();

            this.czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
            this.czzsnbzb.setFsdm(CodeConstant.FSDM_WSSB);
            this.qysbsjList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
            this.tzzsbsjList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
            this.fpblsjList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);

            Assert.assertNotNull(this.czzsnbzb);
            Assert.assertNotNull(this.qysbsjList);
            Assert.assertNotNull(this.tzzsbsjList);
            Assert.assertNotNull(this.fpblsjList);
            Assert.assertTrue(this.qysbsjList.size() != 0);
            Assert.assertTrue(this.tzzsbsjList.size() != 0);
            Assert.assertTrue(this.fpblsjList.size() != 0);
            System.out.println("查账征收年度测试用例doQuery()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收年度测试用例doQuery()异常中止。");
        }
    }

    public void testCzzsndProcessMdoDelete()
    {
        System.out.println("查账征收年度测试用例doDelete()。");
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsndActionConstant.INT_ACTION_DELETE);
            voPackage.setData(buildParameter(CzzsndActionConstant.
                                             INT_ACTION_DELETE));
            voPackage = (VOPackage)this.czzsndProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(CzzsndMapConstant.RESULT), Boolean.TRUE);
            System.out.println("查账征收年度测试用例doDelete()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收年度测试用例doDelete()异常中止。");
        }
    }

    private Map buildParameter(int action) throws BaseException
    {
        Map data = new HashMap();
        List list = new ArrayList();
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
            = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
        List tzfList = sfProxy.getTzfInfo(jsjdm);
        switch(action) {
            case CzzsndActionConstant.INT_ACTION_SAVE:
                if(this.czzsnbzb == null){
                    this.testCzzsndProcessMdoQuery();
                }
                data.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
                data.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
                data.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                data.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
               	data.put(CzzsndMapConstant.LIST_TZF, tzfList);
                break;
            case CzzsndActionConstant.INT_ACTION_DELETE:
                if(this.czzsnbzb == null){
                    this.testCzzsndProcessMdoQuery();
                }
                data.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
                data.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
                data.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                data.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
                break;
            case CzzsndActionConstant.INT_ACTION_QUERY:
               data.put(CzzsndMapConstant.JSJDM, jsjdm);
               data.put(CzzsndMapConstant.LIST_TZF, tzfList);
               break;
        }
        return data;
    }

}
