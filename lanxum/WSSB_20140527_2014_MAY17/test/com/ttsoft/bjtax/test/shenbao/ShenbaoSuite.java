package com.ttsoft.bjtax.test.shenbao;

import junit.framework.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;

public class ShenbaoSuite extends TestCase
{

    public ShenbaoSuite(String s)
    {
        super(s);
    }

    public static Test suite()
    {
        InitORMapping.init_aux();
        System.getProperties().setProperty("isMapped","true");
        System.getProperties().setProperty("isTest","true");
        TestSuite suite = new TestSuite();
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.gzsx.processor.
                           TestGzsxProcessor.class);
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.qysdsnb.processor.
                           TestQysdsnbProcessor.class);
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.jms.processor.
        TestJmsProcessor.class);
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.
        yhs.processor.TestYhsProcessor.class);


	// 外企营业税测试类
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.wqyys.processor.
                           TestWqyysProcessor.class);
        // 查账征收年度申报测试类
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.czzsnd.processor.
                           TestCzzsndProcessor.class);
        // 查账征收月度申报测试类
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.czzsjd.processor.
                           TestCzzsjdProcessor.class);
        // 核定征收申报测试类
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.hdzsgrsds.processor.
                           TestHdzsgrsdsProcessor.class);
        return suite;
    }
}
