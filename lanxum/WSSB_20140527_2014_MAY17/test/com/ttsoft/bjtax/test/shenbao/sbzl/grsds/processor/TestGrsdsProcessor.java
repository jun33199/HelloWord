package com.ttsoft.bjtax.test.shenbao.sbzl.grsds.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsz;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsActionConstant;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsMapConstant;
import java.util.List;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import java.util.Date;

public class TestGrsdsProcessor extends TestCase {
    private GrsdsProcessor grsdsProcessor = null;
    private static Map data = null;
    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        grsdsProcessor = new GrsdsProcessor();
    }

    protected void tearDown() throws Exception {
        grsdsProcessor = null;
        super.tearDown();
    }

    /**
     * ���Ը�������˰doQueryHistoryData����
     * Ԥ�ڽ���� �õ���ȷ����ֵ
     * @throws BaseException
     */
    public void testProcess_M_doQueryHistoryData() throws BaseException
    {
        VOPackage vo = null;
        Grsdsz grsdsz = new Grsdsz();
        grsdsz.setJsjdm("0698086");
        Map skssrq = Skssrq.monthSkssrq(new Date());
        grsdsz.setSkssksrq((Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
        grsdsz.setSkssjsrq((Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
        vo = new VOPackage();
        vo.setData(grsdsz);
        vo.setAction(GrsdsActionConstant.ACTION_QUERY);
        try
        {
            data = (Map)grsdsProcessor.process(vo);
            assertTrue(data!=null);
            assertTrue((List)data.get(GrsdsMapConstant.OLDMX)!=null);
            assertTrue((Grsdsz)data.get(GrsdsMapConstant.OLDZ)!=null);
        }
        catch (Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ���Ը�������˰doRefresh����
     * Ԥ�ڽ���� �õ���ȷ����ֵ
     * @throws BaseException
     */
    public void testProcess_M_doRefresh() throws BaseException
    {
        VOPackage vo = null;
        vo = new VOPackage();
        this.data.put(GrsdsMapConstant.NEWMX, this.data.get(GrsdsMapConstant.OLDMX));
        this.data.put(GrsdsMapConstant.NEWZ, this.data.get(GrsdsMapConstant.OLDZ));
        vo.setData(this.data);
        vo.setAction(GrsdsActionConstant.ACTION_REFRESH);
        try
        {
            Object obj= grsdsProcessor.process(vo);
            assertTrue(obj==null);
        }
        catch (Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }

}
