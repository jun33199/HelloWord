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
     * ��ҵ�걨����
     *
     * �������ռ�����ҵ���� ֵ����
     */
    private Czzsjbqysj qysbsj;

    /**
     * Ͷ�����걨����
     *
     * List�����ݣ�����Ķ����Ǻ�Ͷ������Ŀ��ȵ� �������ռ���Ͷ�������� ֵ����
     */
    private List tzzsbsjList;

    // ��ҵ���������
    private String jsjdm = "0602580";

    public TestCzzsjdProcessor(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        System.out.println("����������ռ��Ȳ���������");
        czzsjdProcessor = new CzzsjdProcessor();
    }

    protected void tearDown() throws Exception {
        czzsjdProcessor = null;
        System.out.println("�˳��������ռ��Ȳ���������");
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
            System.out.println("�������ռ��Ȳ�������doQuery()����ͨ����");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("�������ռ��Ȳ�������doQuery()�쳣��ֹ��");
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
            System.out.println("�������ռ��Ȳ�������doSave()����ͨ����");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("�������ռ��Ȳ�������doSave()�쳣��ֹ��");
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
            System.out.println("�������ռ��Ȳ�������doDelete()����ͨ����");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("�������ռ��Ȳ�������doDelete()�쳣��ֹ��");
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
