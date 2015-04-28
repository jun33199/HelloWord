package com.ttsoft.bjtax.test.shenbao.sbzl.czzsjd.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.sql.Timestamp;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.processor.CzzsjdProcessor;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;

public class TestCzzsjdProcessor extends TestCase {

    private CzzsjdProcessor czzsjdProcessor = null;

    /**
     * 企业申报数据
     *
     * 查帐征收季报企业数据 值对象
     */
    private Czzsjbqysj qysbsj;

    /**
     * 投资者申报数据
     *
     * List型数据，里面的对象是和投资者数目相等的 查帐征收季报投资者数据 值对象
     */
    private List tzzsbsjList;

    // 企业计算机代码
    private String jsjdm = "0602580";

    public TestCzzsjdProcessor(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        System.out.println("进入查账征收季度测试用例。");
        czzsjdProcessor = new CzzsjdProcessor();
    }

    protected void tearDown() throws Exception {
        czzsjdProcessor = null;
        System.out.println("退出查账征收季度测试用例。");
        super.tearDown();
    }
    private UserData getUserData(){
	UserData uData = new UserData();
        uData.yhid = this.jsjdm;
    	return uData;
    }
    public void testCzzsjdProcessMdoQuery()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsjdActionConstant.INT_ACTION_QUERY);
            voPackage.setData(buildParameter(CzzsjdActionConstant.
                                             INT_ACTION_QUERY));
            voPackage = (VOPackage)this.czzsjdProcessor.process(voPackage);
            Map data = (Map)voPackage.getData();
            this.qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
            this.qysbsj.setFsdm(CodeConstant.FSDM_WSSB);
            this.tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);
            Assert.assertNotNull(this.qysbsj);
            Assert.assertNotNull(this.tzzsbsjList);
            Assert.assertTrue(this.tzzsbsjList.size() != 0);
            System.out.println("查账征收季度测试用例doQuery()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收季度测试用例doQuery()异常中止。");
        }
    }

    public void testCzzsjdProcessMdoSave()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsjdActionConstant.INT_ACTION_SAVE);
            voPackage.setData(buildParameter(CzzsjdActionConstant.
                                             INT_ACTION_SAVE));
            voPackage = (VOPackage)this.czzsjdProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(CzzsndMapConstant.RESULT), Boolean.TRUE);
            System.out.println("查账征收季度测试用例doSave()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收季度测试用例doSave()异常中止。");
        }
    }

    public void testCzzsjdProcessMdoDelete()
    {
        VOPackage voPackage = new VOPackage();
        try {
            voPackage.setUserData(getUserData());
            voPackage.setAction(CzzsjdActionConstant.INT_ACTION_DELETE);
            voPackage.setData(buildParameter(CzzsjdActionConstant.
                                             INT_ACTION_DELETE));
            voPackage = (VOPackage)this.czzsjdProcessor.process(voPackage);
            Map result = (Map)voPackage.getData();
            Assert.assertEquals(result.get(CzzsndMapConstant.RESULT), Boolean.TRUE);
            System.out.println("查账征收季度测试用例doDelete()正常通过。");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("查账征收季度测试用例doDelete()异常中止。");
        }
    }

    private Map buildParameter(int action) throws BaseException
    {
        Map data = new HashMap();
        List list = new ArrayList();
        switch(action) {
            case CzzsjdActionConstant.INT_ACTION_SAVE:
                if(this.qysbsj == null){
                    this.testCzzsjdProcessMdoQuery();
                }
                data.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
                data.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                break;
            case CzzsjdActionConstant.INT_ACTION_DELETE:
                if(this.qysbsj == null){
                    this.testCzzsjdProcessMdoQuery();
                }
                data.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
                data.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
                break;
            case CzzsjdActionConstant.INT_ACTION_QUERY:
               data.put(CzzsjdMapConstant.JSJDM, this.jsjdm);
               com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                   = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
               List tzfList = sfProxy.getTzfInfo(jsjdm);
               data.put(CzzsjdMapConstant.LIST_TZF, tzfList);
               break;
        }
        return data;
    }

}
