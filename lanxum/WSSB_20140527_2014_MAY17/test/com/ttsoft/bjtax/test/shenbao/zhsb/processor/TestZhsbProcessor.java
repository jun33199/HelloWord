package com.ttsoft.bjtax.test.shenbao.zhsb.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.zhsb.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import java.util.HashMap;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import java.sql.Timestamp;
import java.math.BigDecimal;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.util.Debug;
import java.util.Date;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;

public class TestZhsbProcessor extends TestCase {
    private ZhsbProcessor zhsbProcessor = null;

    protected void setUp() throws Exception {
        super.setUp();
        InitORMapping.init_aux();
        zhsbProcessor = new ZhsbProcessor();
    }

    protected void tearDown() throws Exception {
        zhsbProcessor = null;
        super.tearDown();
    }


    /**
     * 测试Zhsb得doSave方法
     * 预期结果：得到Boolean.True,无异常抛出
     * @throws BaseException
     */
    public void testProcess_M_doSave_01() throws BaseException
    {
        VOPackage vo = new VOPackage();
        Map data = new HashMap();
        DeclareInfor declareInfor = new DeclareInfor();
        List sbjkmxInfo = new ArrayList();
        Sbjkzb sbjkzb = new Sbjkzb();
        Sbjkmx sbjkmx = new Sbjkmx();
        sbjkzb.setCjsj(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setClbjdm("000");
        sbjkzb.setDjzclxdm("120");
        sbjkzb.setDjzclxmc("dsds");
        sbjkzb.setFsdm("5");
        sbjkzb.setGjbzhydm("000");
        sbjkzb.setGjbzhymc("DDD");
        sbjkzb.setGkzzjgdm("000");
        sbjkzb.setGkzzjgmc("DDD");
        sbjkzb.setHxrdm("000");
        sbjkzb.setHxrmc("DDD");
        sbjkzb.setHxrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setJkpzh("000");
        sbjkzb.setJksj(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setJsjdm("0602410");
        sbjkzb.setLrr("0602410");
        sbjkzb.setLrrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setLsgxdm("000");
        sbjkzb.setLsgxmc("ddd");
        sbjkzb.setNsrmc("sdfs");
        sbjkzb.setRkje(new BigDecimal("100"));
        sbjkzb.setSklxdm("00");
        sbjkzb.setSklxmc("sfsd");
        sbjkzb.setSwjgzzjgdm("0156");
        sbjkzb.setSwjgzzjgmc("dsfs");
        sbjkzb.setSzmc("sds");
        sbjkzb.setSzdm("16");
        sbjkzb.setXjrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setYhdm("000");
        sbjkzb.setYhmc("sss");
        sbjkzb.setYsjcdm("000");
        sbjkzb.setYsjcmc("sss");
        sbjkzb.setYskmdm("000");
        sbjkzb.setYskmmc("sss");
        sbjkzb.setZh("sds");
        sbjkzb.setZsswjgzzjgdm("000");
        sbjkzb.setZsswjgzzjgmc("ss");
        sbjkzb.setZwbs("0");
        sbjkzb.setZyrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setJkpzh("00");
        sbjkmx.setJnqx("00");
        sbjkmx.setJsjdm("0602410");
        sbjkmx.setJsje(new BigDecimal("5000"));
        sbjkmx.setKssl(new BigDecimal("2"));
        sbjkmx.setRkje(new BigDecimal("100"));
        sbjkmx.setSjse(new BigDecimal("8000"));
        sbjkmx.setSkssjsrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setSkssksrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setSzdm("16");
        sbjkmx.setSzmc("ss");
        sbjkmx.setSzsmdm("160214");
        sbjkmx.setSzsmmc("sfsds");
        sbjkmx.setYskmdm("000");
        sbjkmx.setYskmmc("ddd");
        sbjkmx.setZqlxdm("000");
        sbjkmxInfo.add(sbjkmx);
        declareInfor.setSbjkmxInfo(sbjkmxInfo);
        declareInfor.setSbjkzb(sbjkzb);
        declareInfor.setIsReturnPaymentInfo(false);
        declareInfor.setPrintTag(1);
        data.put("sds",declareInfor);
        vo.setAction(ActionConstant.INT_ACTION_SAVE);
        vo.setData(data);
        try
        {
            Object actualReturn = zhsbProcessor.process(vo);
            assertTrue(actualReturn!=null);
            assertTrue(((Boolean)actualReturn).booleanValue());
        }
        catch(Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试Zhsb得doSave方法
     * 预期结果：得到返回数据不为null,无异常抛出
     * @throws BaseException
     */
    public void testProcess_M_doSave_02() throws BaseException
    {
        VOPackage vo = new VOPackage();
        Map data = new HashMap();
        DeclareInfor declareInfor = new DeclareInfor();
        List sbjkmxInfo = new ArrayList();
        Sbjkzb sbjkzb = new Sbjkzb();
        Sbjkmx sbjkmx = new Sbjkmx();
        sbjkzb.setCjsj(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setClbjdm("000");
        sbjkzb.setDjzclxdm("120");
        sbjkzb.setDjzclxmc("dsds");
        sbjkzb.setFsdm("5");
        sbjkzb.setGjbzhydm("000");
        sbjkzb.setGjbzhymc("DDD");
        sbjkzb.setGkzzjgdm("000");
        sbjkzb.setGkzzjgmc("DDD");
        sbjkzb.setHxrdm("000");
        sbjkzb.setHxrmc("DDD");
        sbjkzb.setHxrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setJkpzh("000");
        sbjkzb.setJksj(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setJsjdm("0602410");
        sbjkzb.setLrr("0602410");
        sbjkzb.setLrrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setLsgxdm("000");
        sbjkzb.setLsgxmc("ddd");
        sbjkzb.setNsrmc("sdfs");
        sbjkzb.setRkje(new BigDecimal("100"));
        sbjkzb.setSklxdm("00");
        sbjkzb.setSklxmc("sfsd");
        sbjkzb.setSwjgzzjgdm("0156");
        sbjkzb.setSwjgzzjgmc("dsfs");
        sbjkzb.setSzmc("sds");
        sbjkzb.setSzdm("16");
        sbjkzb.setXjrq(new Timestamp(System.currentTimeMillis()));
        sbjkzb.setYhdm("000");
        sbjkzb.setYhmc("sss");
        sbjkzb.setYsjcdm("000");
        sbjkzb.setYsjcmc("sss");
        sbjkzb.setYskmdm("000");
        sbjkzb.setYskmmc("sss");
        sbjkzb.setZh("sds");
        sbjkzb.setZsswjgzzjgdm("000");
        sbjkzb.setZsswjgzzjgmc("ss");
        sbjkzb.setZwbs("0");
        sbjkzb.setZyrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setJkpzh("00");
        sbjkmx.setJnqx("00");
        sbjkmx.setJsjdm("0602410");
        sbjkmx.setJsje(new BigDecimal("5000"));
        sbjkmx.setKssl(new BigDecimal("2"));
        sbjkmx.setRkje(new BigDecimal("100"));
        sbjkmx.setSjse(new BigDecimal("8000"));
        sbjkmx.setSkssjsrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setSkssksrq(new Timestamp(System.currentTimeMillis()));
        sbjkmx.setSzdm("16");
        sbjkmx.setSzmc("ss");
        sbjkmx.setSzsmdm("160214");
        sbjkmx.setSzsmmc("sfsds");
        sbjkmx.setYskmdm("000");
        sbjkmx.setYskmmc("ddd");
        sbjkmx.setZqlxdm("000");
        sbjkmxInfo.add(sbjkmx);
        declareInfor.setSbjkmxInfo(sbjkmxInfo);
        declareInfor.setSbjkzb(sbjkzb);
        declareInfor.setIsReturnPaymentInfo(true);
        declareInfor.setPrintTag(1);
        data.put("sds",declareInfor);
        vo.setAction(ActionConstant.INT_ACTION_SAVE);
        vo.setData(data);
        try
        {
            Object actualReturn = zhsbProcessor.process(vo);
            assertTrue(actualReturn!=null);
        }
        catch(Exception ex)
        {
            assertTrue(ex.toString(), false);
        }
    }


    public void testCreatePaymentInfoAndDoInsert_01()
  {
      Debug.testCase("生成缴款数据并进行数据库插入操作测试_01:一条明细数据");
      try
      {
          //主表数据
          Sbjkzb sbjkzb = new Sbjkzb();
          sbjkzb.setJsjdm("0602410");
          sbjkzb.setCjsj(new Timestamp((new Date()).getTime()));
          sbjkzb.setDjzclxdm("120");
          sbjkzb.setClbjdm("1");
          sbjkzb.setFsdm("5");
          sbjkzb.setGjbzhydm("4200");
          sbjkzb.setGkzzjgmc("test");
          sbjkzb.setJksj(new Timestamp((new Date()).getTime()));
          sbjkzb.setLrr("01");
          sbjkzb.setLrrq(new Timestamp((new Date()).getTime()));
          sbjkzb.setSbrq(new Timestamp((new Date()).getTime()));
          sbjkzb.setSjje(new BigDecimal(0.00));
          sbjkzb.setSklxdm("110");
          sbjkzb.setSwjgzzjgdm("00001");
          sbjkzb.setSzdm("02");
          sbjkzb.setXjrq(new Timestamp((new Date()).getTime()));
          sbjkzb.setYhdm("02");
          sbjkzb.setZh("00000000001");
          sbjkzb.setZsswjgzzjgdm("00001");
          sbjkzb.setZwbs("00000000");
          sbjkzb.setNsrmc("stone");
          sbjkzb.setZyrq(new Timestamp((new Date()).getTime()));

         //明细数据
          Sbjkmx sbjkmx = new Sbjkmx();
          sbjkmx.setJsjdm("0602410");
          sbjkmx.setJsje(new BigDecimal(10000.10));
          sbjkmx.setSjse(new BigDecimal(10.10));
          sbjkmx.setSkssjsrq(new Timestamp((new Date()).getTime()));
          sbjkmx.setSkssksrq(new Timestamp((new Date()).getTime()));
          sbjkmx.setSzdm("02");
          sbjkmx.setSzsmdm("100010");
          sbjkmx.setZqlxdm("01");
          List sbjkmxInfor = new ArrayList();
          sbjkmxInfor.add(sbjkmx);
          ZhsbProcessor zhsbProcessor = new ZhsbProcessor();
          String sbbh = zhsbProcessor.getSbbh(sbjkzb.getJsjdm());
          zhsbProcessor.createJkInfor(sbjkzb, sbjkmxInfor, sbbh);
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
  }

  public void testCreatePaymentInfoAndDoInsert_02()
 {
     Debug.testCase("生成缴款数据并进行数据库插入操作测试_02:多条明细数据一票一税");
     try
     {
         //主表数据
         Sbjkzb sbjkzb = new Sbjkzb();
         sbjkzb.setJsjdm("0602410");
         sbjkzb.setCjsj(new Timestamp((new Date()).getTime()));
         sbjkzb.setDjzclxdm("8");
         sbjkzb.setClbjdm("1");
         sbjkzb.setFsdm("5");
         sbjkzb.setGjbzhydm("0100");
         sbjkzb.setGkzzjgdm("0001");
         sbjkzb.setJksj(new Timestamp((new Date()).getTime()));
         sbjkzb.setLrr("01");
         sbjkzb.setLrrq(new Timestamp((new Date()).getTime()));
         sbjkzb.setSbrq(new Timestamp((new Date()).getTime()));
         sbjkzb.setSjje(new BigDecimal(0.00));
         sbjkzb.setSklxdm("110");
         sbjkzb.setSwjgzzjgdm("00001");
         sbjkzb.setSzdm("02");
         sbjkzb.setXjrq(new Timestamp((new Date()).getTime()));
         sbjkzb.setYhdm("02");
         sbjkzb.setZh("00000000001");
         sbjkzb.setZsswjgzzjgdm("00001");
         sbjkzb.setZwbs("00000000");
         sbjkzb.setNsrmc("stone");
         sbjkzb.setZyrq(new Timestamp((new Date()).getTime()));


         List sbjkmxInfor = new ArrayList();

        //明细数据
         Sbjkmx sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("01");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp((new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp((new Date()).getTime()));
         sbjkmx.setSzdm("02");
         sbjkmx.setSzsmdm("021103");
         sbjkmxInfor.add(sbjkmx);

         sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("02");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp((new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp((new Date()).getTime()));
         sbjkmx.setSzdm("02");
         sbjkmx.setSzsmdm("027700");
         sbjkmxInfor.add(sbjkmx);


         sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("01");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSzdm("08");
         sbjkmx.setSzsmdm("080050");
         sbjkmxInfor.add(sbjkmx);

         ZhsbProcessor zhsbProcessor = new ZhsbProcessor();
//         zhsbProcessor.createJkInfor(sbjkzb, sbjkmxInfor);
         DeclareInfor declareInfor = new DeclareInfor(sbjkzb,sbjkmxInfor);
         declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
         declareInfor.setIsReturnPaymentInfo(true);
         String sbbh = zhsbProcessor.getSbbh(sbjkzb.getJsjdm());
         List list = (List)zhsbProcessor.createJkInfor(declareInfor, sbbh);
         Assert.assertEquals(list.size(),3);
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     }
 }

 public void testCreatePaymentInfoAndDoInsert_03()
 {
     Debug.testCase("生成缴款数据并进行数据库插入操作测试_03:多条明细数据,一票多税");
     try
     {
         //主表数据
         Sbjkzb sbjkzb = new Sbjkzb();
         sbjkzb.setJsjdm("0602410");
         sbjkzb.setCjsj(new Timestamp( (new Date()).getTime()));
         sbjkzb.setDjzclxdm("8");
         sbjkzb.setClbjdm("1");
         sbjkzb.setFsdm("5");
         sbjkzb.setGjbzhydm("0100");
         sbjkzb.setGkzzjgdm("0001");
         sbjkzb.setJksj(new Timestamp( (new Date()).getTime()));
         sbjkzb.setLrr("01");
         sbjkzb.setLrrq(new Timestamp( (new Date()).getTime()));
         sbjkzb.setSbrq(new Timestamp( (new Date()).getTime()));
         sbjkzb.setSjje(new BigDecimal(0.00));
         sbjkzb.setSklxdm("110");
         sbjkzb.setSwjgzzjgdm("00001");
         sbjkzb.setSzdm("02");
         sbjkzb.setXjrq(new Timestamp( (new Date()).getTime()));
         sbjkzb.setYhdm("02");
         sbjkzb.setZh("00000000001");
         sbjkzb.setZsswjgzzjgdm("00001");
         sbjkzb.setZwbs("00000000");
         sbjkzb.setNsrmc("stone");
         sbjkzb.setZyrq(new Timestamp( (new Date()).getTime()));

         List sbjkmxInfor = new ArrayList();

         //明细数据
         Sbjkmx sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("01");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSzdm("02");
         sbjkmx.setSzsmdm("021103");
         sbjkmxInfor.add(sbjkmx);

         sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("02");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSzdm("02");
         sbjkmx.setSzsmdm("027700");
         sbjkmxInfor.add(sbjkmx);

         sbjkmx = new Sbjkmx();
         sbjkmx.setZqlxdm("01");
         sbjkmx.setJsjdm("0602410");
         sbjkmx.setJsje(new BigDecimal(10000.10));
         sbjkmx.setSjse(new BigDecimal(10.10));
         sbjkmx.setSkssjsrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSkssksrq(new Timestamp( (new Date()).getTime()));
         sbjkmx.setSzdm("08");
         sbjkmx.setSzsmdm("080050");
         sbjkmxInfor.add(sbjkmx);

         ZhsbProcessor zhsbProcessor = new ZhsbProcessor();
//         zhsbProcessor.createJkInfor(sbjkzb, sbjkmxInfor);
         DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxInfor);
         declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
         declareInfor.setIsReturnPaymentInfo(true);
         String sbbh = zhsbProcessor.getSbbh(sbjkzb.getJsjdm());
         List list = (List)zhsbProcessor.createJkInfor(declareInfor, sbbh);
         Assert.assertEquals(list.size(), 1);
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     }
 }


}
