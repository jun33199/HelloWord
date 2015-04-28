package com.ttsoft.bjtax.test.shenbao.sbzl.qysdsnb.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.processor.*;
import com.ttsoft.framework.exception.*;
import java.util.*;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbMapConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbActionConstant;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;

public class TestQysdsnbProcessor extends TestCase {
    private QysdsnbProcessor qysdsnbProcessor = null;

    public TestQysdsnbProcessor(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
//       Properties p = System.getProperties();
//       p.setProperty("isTest","true");
       super.setUp();
        /**@todo verify the constructors*/
        qysdsnbProcessor = new QysdsnbProcessor();
    }

    protected void tearDown() throws Exception {
        qysdsnbProcessor = null;
        super.tearDown();
    }

    /**
     * ≤‚ ‘
     * @throws BaseException
     */
    public void testDoDelete() {

        try
        {
            Timestamp curTime = new Timestamp(System.currentTimeMillis());

            Map map = new HashMap();
            map.put(QysdsnbMapConstant.STRING_KEY_JSJDM,"0602580");
            map.put(QysdsnbMapConstant.STRING_KEY_DATE,curTime);

            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
            vo.setAction(QysdsnbActionConstant.INT_ACTION_DELETE_ALL);
            vo.setData(map);


            InitORMapping.init_aux();
            Object retObj = qysdsnbProcessor.process(vo);
            assertEquals(null,retObj);


            vo.setAction(QysdsnbActionConstant.INT_ACTION_QUERY_All);
            QysdsnbData nbdata = (QysdsnbData)qysdsnbProcessor.process(vo);

            assertEquals(0,nbdata.getNbData().getNbData().size());
            assertEquals(0,nbdata.getCwzbData().getCwzbData().size());
            assertEquals(0,nbdata.getLygfData().getLygfData().size());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



        /**@todo fill in the test code*/
    }

}
