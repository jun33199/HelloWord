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


	// ����Ӫҵ˰������
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.wqyys.processor.
                           TestWqyysProcessor.class);
        // ������������걨������
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.czzsnd.processor.
                           TestCzzsndProcessor.class);
        // ���������¶��걨������
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.czzsjd.processor.
                           TestCzzsjdProcessor.class);
        // �˶������걨������
        suite.addTestSuite(com.ttsoft.bjtax.test.shenbao.sbzl.hdzsgrsds.processor.
                           TestHdzsgrsdsProcessor.class);
        return suite;
    }
}
