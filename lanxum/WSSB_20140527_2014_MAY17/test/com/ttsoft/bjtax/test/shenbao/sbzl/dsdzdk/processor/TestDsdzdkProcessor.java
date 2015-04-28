package com.ttsoft.bjtax.test.shenbao.sbzl.dsdzdk.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.processor.*;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.DsdzdkActionConstant;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Dsdzdkmx;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class TestDsdzdkProcessor extends TestCase
{
    private DsdzdkProcessor dsdzdkProcessor = null;

    protected void setUp() throws Exception
    {
        super.setUp();
        dsdzdkProcessor = new DsdzdkProcessor();
    }

    protected void tearDown() throws Exception
    {
        dsdzdkProcessor = null;
        super.tearDown();
    }

    public void testProcessor_01()
    {
        Debug.testCase("代售代征代扣的processor中的保存测试例_01");
        DsdzdkProcessor dsdzdkProcessor = new DsdzdkProcessor();
        VOPackage vo = new VOPackage();
        vo.setAction(DsdzdkActionConstant.ACTION_SAVESBMX);
        List dsdzdkmxList = new ArrayList();
//        dsdzdkmxList
        Dsdzdkmx dsdzdkmx = new Dsdzdkmx();
        dsdzdkmx.setBdzrmc(dsdzdkmx.getBdzrmc());
//                 dsdzdkmx.setCjsj(new Timestatmp());//创建时间
        dsdzdkmx.setFsdm(CodeConstant.FSDM_WSSB);
        dsdzdkmx.setJsjdm("0603136");
        dsdzdkmx.setJsje(new BigDecimal(1.00));
        dsdzdkmx.setLrr("stone");
        dsdzdkmx.setLrrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx.setQylxdh("08995578");
        dsdzdkmx.setSjse(new BigDecimal(1.11));
        dsdzdkmx.setSkssjsrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx.setSkssksrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx.setSl(new BigDecimal(1.55));
        dsdzdkmx.setSwjgzzjgdm("002");
        dsdzdkmx.setSzdm("02");
        dsdzdkmx.setSzmc("营业税");
        dsdzdkmx.setSzsmdm("021104");
        dsdzdkmx.setSzsmmc("一般营业税");
        dsdzdkmx.setWszh("00011112");
        dsdzdkmxList.add(dsdzdkmx);

        Dsdzdkmx dsdzdkmx1 = new Dsdzdkmx();
        dsdzdkmx1.setBdzrmc(dsdzdkmx.getBdzrmc());
//                 dsdzdkmx.setCjsj(new Timestatmp());//创建时间
        dsdzdkmx1.setFsdm(CodeConstant.FSDM_WSSB);
        dsdzdkmx1.setJsjdm("0603136");
        dsdzdkmx1.setJsje(new BigDecimal(1000.00));
        dsdzdkmx1.setLrr("stone");
        dsdzdkmx1.setLrrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx1.setQylxdh("08995578");
        dsdzdkmx1.setSjse(new BigDecimal(110.11));
        dsdzdkmx1.setSkssjsrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx1.setSkssksrq(new Timestamp( (new Date()).getTime()));
        dsdzdkmx1.setSl(new BigDecimal(111.55));
        dsdzdkmx1.setSwjgzzjgdm("002");
        dsdzdkmx1.setSzdm("14");
        dsdzdkmx1.setSzmc("资源税");
        dsdzdkmx1.setSzsmdm("144020");
        dsdzdkmx1.setSzsmmc("大理石");
        dsdzdkmx1.setWszh("00011111111");
        dsdzdkmxList.add(dsdzdkmx1);
        vo.setData(dsdzdkmxList);
        try
        {
           List querylist = (List)dsdzdkProcessor.process(vo);
            Assert.assertTrue((querylist != null)&&(querylist.size()>0));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

      public void testProcessor_02()
      {
          Debug.testCase("代售代征代扣的processor中的查询汇总测试例_02");
          VOPackage vo = new VOPackage();
          vo.setAction(DsdzdkActionConstant.ACTION_QUERYHZ);
          vo.setData("0603136");
          DsdzdkProcessor dsdzdkProcessor = new DsdzdkProcessor();
          try
          {
              List querylist = (List)dsdzdkProcessor.process(vo);
              Assert.assertTrue((querylist != null)&&(querylist.size()>0));
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
      }

}