package com.ttsoft.bjtax.test.shenbao.zhsb.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.zhsb.processor.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class TestZhsbProcessor_M_insertSbjkData extends TestCase {
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

    public void testInsertSbjkData()
    {
        List sbjkzbList = new ArrayList();
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
        sbjkzb.setJsjdm("0602580");
        sbjkzb.setLrr("0602580");
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
        sbjkzbList.add(sbjkzb);
        sbjkmx.setJkpzh("00");
        sbjkmx.setJnqx("00");
        sbjkmx.setJsjdm("0602580");
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
        try
        {
           zhsbProcessor.insertSbjkData(sbjkzbList, sbjkmxInfo);
           assertTrue(true);
        }
        catch(Exception ex)
        {
           assertTrue(ex.toString(), false);
        }
    }

}
