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
        // ��ʼ��ORMapping
        InitORMapping.init_aux();
        szsmProcessor = new SzsmProcessor();
    }

    protected void tearDown() throws Exception {
        szsmProcessor = null;
        super.tearDown();
    }

    /**
     * ����SzsmProcessor��queryPreviousSzsm����
     * Ԥ�ڽ������ȷ�������ݣ����쳣�׳�
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
            // �õ��ķ���ֵ��Ϊnull��Pass��
            assertTrue(actualReturn!=null);
        }
        catch(Exception ex)
        {
            // ���쳣�׳�, NOPass!
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ����SzsmProcessor��getJjlxglszsmList��getSbSzsmZqrlList����
     * Ԥ�ڽ�����õ�������ȷ�ķ���ֵ
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
             // ���з������ݲ�Ϊnull, Pass
             assertTrue(data!=null);
             assertTrue((List)data.get(SzsmMapConstant.SZSM_LIST)!=null);
             assertTrue((List)data.get(SzsmMapConstant.JJLXGLSZSM_LIST)!=null);
        }
        catch(Exception ex)
        {
            // ���쳣�׳���NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ����SzsmProcessor��refreshFavorite����
     * Ԥ�ڽ�����õ�����ֵnull�����쳣�׳�
     * @throws BaseException
     */
    public void testProcess_M_refreshFavorite() throws BaseException
    {
        // ����VOPackage
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
        sqsb.setSzmc("ӡ��˰");
        sqsb.setSzsmdm("161220");
        sqsb.setSzsmmc("�����ʲ�");
        dataList.add(sqsb);
        vo.setAction(SzsmActionConstant.REFRESH_FAVORITE);
        vo.setData(dataList);
        vo.setUserData(userData);
        try
        {
            Object obj = szsmProcessor.process(vo);
            // ��������ΪNULL�� ���쳣�׳��� Pass
            assertTrue(obj==null);
        }
        catch(Exception ex)
        {
            // �쳣�׳�. No Pass
            assertTrue(ex.toString(), false);
        }
    }
}
