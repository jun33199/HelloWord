package com.ttsoft.bjtax.test.shenbao.gzsx.processor;

import junit.framework.*;
import com.ttsoft.framework.util.VOPackage;
import java.util.List;
import com.ttsoft.bjtax.shenbao.gzsx.processor.GzsxProcessor;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.bjtax.test.shenbao.util.SetEnv;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;

public class TestGzsxProcessor extends TestCase {
    private GzsxProcessor gzsxProcessor = null;

    protected void setUp() throws Exception {
        super.setUp();
        SetEnv.setProperties();
        InitORMapping.init_aux();
        /**@todo verify the constructors*/
        gzsxProcessor = new GzsxProcessor();
    }

    protected void tearDown() throws Exception {
        gzsxProcessor = null;
        super.tearDown();
    }

    public void testDoQueryGzsx()
    {
        try
        {
            VOPackage vo = new VOPackage();
            vo.setData(new String("001"));
            List gzsxList = gzsxProcessor.doQueryGzsxData(vo);
            assertEquals(4,gzsxList.size());
            Gzsx gzsx = (Gzsx)gzsxList.get(0);
            System.out.println(gzsx.getJsjdm());
            assertEquals(gzsx.getJsjdm(),"*");
            assertEquals(gzsx.getFzcbs(),"1");
            assertEquals(gzsx.getGzsxnr(),"test");
            vo.setData(new String("0602580"));
            gzsxList = gzsxProcessor.doQueryGzsxData(vo);
            assertEquals(2,gzsxList.size());
            gzsx = (Gzsx)gzsxList.get(1);
            assertEquals(gzsx.getFzcbs(),"0");
            assertEquals(gzsx.getGzsxnr(),"正常告知");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}
