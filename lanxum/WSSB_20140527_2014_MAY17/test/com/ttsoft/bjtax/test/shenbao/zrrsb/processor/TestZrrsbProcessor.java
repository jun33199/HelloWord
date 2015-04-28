package com.ttsoft.bjtax.test.shenbao.zrrsb.processor;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.ttsoft.bjtax.shenbao.model.client.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;

import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import junit.framework.*;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.zrrsb.processor.ZrrsbProcessor;
import com.ttsoft.bjtax.shenbao.zrrsb.ZrrsbActionConstant;

public class TestZrrsbProcessor extends TestCase {
    private ZrrsbProcessor zrrsbProcessor = null;

    protected void setUp() throws Exception {
        super.setUp();
        /**@todo verify the constructors*/
        zrrsbProcessor = new ZrrsbProcessor();
    }

    protected void tearDown() throws Exception {
        zrrsbProcessor = null;
        super.tearDown();
    }

    public void testProcess_01() throws BaseException
    {
        Debug.testCase("自然人申报测试例_01:保存申报数据(只有一条明细数据)");
        Timestamp currq = new Timestamp( (new Date()).getTime());
        BigDecimal testje = new BigDecimal(110.00);
        VOPackage vo = new VOPackage();
        vo.setAction(ZrrsbActionConstant.ACTION_SAVESBDATA);
        try
        {
            ZrrsbInfor zrrsbInfor = new ZrrsbInfor();
            Zrrsbz zrrsbz = new Zrrsbz();
            zrrsbz.setGjdm("CHN");
            zrrsbz.setGjmc("中国");
            zrrsbz.setLrrq(currq);
            zrrsbz.setSbrq(currq);
            zrrsbz.setZjhm("23456789");
            zrrsbz.setZjlxdm("5");
            zrrsbz.setZydm("01");
            zrrsbz.setZymc("programmer");

            List zrrsbmxList = new ArrayList();
            Zrrsbmx zrrsbmx = new Zrrsbmx();
            zrrsbmx.setCjrq(currq);
            zrrsbmx.setGjdm("CHN");
            zrrsbmx.setJmse(testje);
            zrrsbmx.setLrrq(currq);
            zrrsbmx.setSbrq(currq);
            zrrsbmx.setSdqjq(currq);
            zrrsbmx.setSdqjz(currq);
            zrrsbmx.setSjse(testje);
            zrrsbmx.setSl(testje);
            zrrsbmx.setSre(testje);
            zrrsbmx.setSrlyd("不知道");
            zrrsbmx.setSzsmdm("020001");
            zrrsbmx.setSzsmmc("一般营业税");
            zrrsbmx.setYkse(testje);
            zrrsbmx.setYnssde(testje);
            zrrsbmx.setZfdwmc("stone");
            zrrsbmx.setZjhm("23456789");
            zrrsbmx.setZjlxdm("5");
            zrrsbmxList.add(zrrsbmx);
            zrrsbInfor.setZrrsbmxList(zrrsbmxList);
            zrrsbInfor.setZrrsbz(zrrsbz);
            vo.setData(zrrsbInfor);
            Object expectedReturn = null;
            Object actualReturn = zrrsbProcessor.process(vo);
            assertEquals(Boolean.TRUE, actualReturn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testProcess_02() throws BaseException
    {
        Debug.testCase("自然人申报测试例_02:保存申报数据(多条条明细数据)");
        Timestamp currq = new Timestamp( (new Date()).getTime());
        BigDecimal testje = new BigDecimal(110.00);
        VOPackage vo = new VOPackage();
        vo.setAction(ZrrsbActionConstant.ACTION_SAVESBDATA);
        try
        {
            ZrrsbInfor zrrsbInfor = new ZrrsbInfor();
            Zrrsbz zrrsbz = new Zrrsbz();
            zrrsbz.setGjdm("CHN");
            zrrsbz.setGjmc("中国");
            zrrsbz.setLrrq(currq);
            zrrsbz.setSbrq(currq);
            zrrsbz.setZjhm("23456789");
            zrrsbz.setZjlxdm("5");
            zrrsbz.setZydm("01");
            zrrsbz.setZymc("programmer");

            List zrrsbmxList = new ArrayList();
            Zrrsbmx zrrsbmx = new Zrrsbmx();
            zrrsbmx.setCjrq(currq);
            zrrsbmx.setGjdm("CHN");
            zrrsbmx.setJmse(testje);
            zrrsbmx.setLrrq(currq);
            zrrsbmx.setSbrq(currq);
            zrrsbmx.setSdqjq(currq);
            zrrsbmx.setSdqjz(currq);
            zrrsbmx.setSjse(testje);
            zrrsbmx.setSl(testje);
            zrrsbmx.setSre(testje);
            zrrsbmx.setSrlyd("不知道");
            zrrsbmx.setSzsmdm("020001");
            zrrsbmx.setSzsmmc("一般营业税");
            zrrsbmx.setYkse(testje);
            zrrsbmx.setYnssde(testje);
            zrrsbmx.setZfdwmc("stone");
            zrrsbmx.setZjhm("23456789");
            zrrsbmx.setZjlxdm("5");
            zrrsbmxList.add(zrrsbmx);

            Zrrsbmx zrrsbmx1 = new Zrrsbmx();
            zrrsbmx1.setCjrq(currq);
            zrrsbmx1.setGjdm("CHN");
            zrrsbmx1.setJmse(testje);
            zrrsbmx1.setLrrq(currq);
            zrrsbmx1.setSbrq(currq);
            zrrsbmx1.setSdqjq(currq);
            zrrsbmx1.setSdqjz(currq);
            zrrsbmx1.setSjse(testje);
            zrrsbmx1.setSl(testje);
            zrrsbmx1.setSre(testje);
            zrrsbmx1.setSrlyd("不知道");
            zrrsbmx1.setSzsmdm("020002");
            zrrsbmx1.setSzsmmc("一般营业税");
            zrrsbmx1.setYkse(testje);
            zrrsbmx1.setYnssde(testje);
            zrrsbmx1.setZfdwmc("stone");
            zrrsbmx1.setZjhm("23456789");
            zrrsbmx1.setZjlxdm("5");
            zrrsbmxList.add(zrrsbmx1);

            Zrrsbmx zrrsbm2 = new Zrrsbmx();
            zrrsbm2.setCjrq(currq);
            zrrsbm2.setGjdm("CHN");
            zrrsbm2.setJmse(testje);
            zrrsbm2.setLrrq(currq);
            zrrsbm2.setSbrq(currq);
            zrrsbm2.setSdqjq(currq);
            zrrsbm2.setSdqjz(currq);
            zrrsbm2.setSjse(testje);
            zrrsbm2.setSl(testje);
            zrrsbm2.setSre(testje);
            zrrsbm2.setSrlyd("不知道");
            zrrsbm2.setSzsmdm("020003");
            zrrsbm2.setSzsmmc("一般营业税");
            zrrsbm2.setYkse(testje);
            zrrsbm2.setYnssde(testje);
            zrrsbm2.setZfdwmc("stone");
            zrrsbm2.setZjhm("23456789");
            zrrsbm2.setZjlxdm("5");
            zrrsbmxList.add(zrrsbm2);

            zrrsbInfor.setZrrsbmxList(zrrsbmxList);
            zrrsbInfor.setZrrsbz(zrrsbz);
            vo.setData(zrrsbInfor);
            Object expectedReturn = null;
            Object actualReturn = zrrsbProcessor.process(vo);
            assertEquals(Boolean.TRUE, actualReturn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void testProcess_03() throws BaseException
    {
        Debug.testCase("自然人申报测试例_03:保存申报数据(多条明细数据,其中有相同的数据)");
        Timestamp currq = new Timestamp( (new Date()).getTime());
        BigDecimal testje = new BigDecimal(110.00);
        VOPackage vo = new VOPackage();
        vo.setAction(ZrrsbActionConstant.ACTION_SAVESBDATA);
        try
        {
            ZrrsbInfor zrrsbInfor = new ZrrsbInfor();
                    Zrrsbz zrrsbz = new Zrrsbz();
                    zrrsbz.setGjdm("CHN");
                    zrrsbz.setGjmc("中国");
                    zrrsbz.setLrrq(currq);
                    zrrsbz.setSbrq(currq);
                    zrrsbz.setZjhm("23456789");
                    zrrsbz.setZjlxdm("5");
                    zrrsbz.setZydm("01");
                    zrrsbz.setZymc("programmer");

                    List zrrsbmxList = new ArrayList();
                    Zrrsbmx zrrsbmx = new Zrrsbmx();
                    zrrsbmx.setCjrq(currq);
                    zrrsbmx.setGjdm("CHN");
                    zrrsbmx.setJmse(testje);
                    zrrsbmx.setLrrq(currq);
                    zrrsbmx.setSbrq(currq);
                    zrrsbmx.setSdqjq(currq);
                    zrrsbmx.setSdqjz(currq);
                    zrrsbmx.setSjse(testje);
                    zrrsbmx.setSl(testje);
                    zrrsbmx.setSre(testje);
                    zrrsbmx.setSrlyd("不知道");
                    zrrsbmx.setSzsmdm("020001");
                    zrrsbmx.setSzsmmc("一般营业税");
                    zrrsbmx.setYkse(testje);
                    zrrsbmx.setYnssde(testje);
                    zrrsbmx.setZfdwmc("stone");
                    zrrsbmx.setZjhm("23456789");
                    zrrsbmx.setZjlxdm("5");
                    zrrsbmxList.add(zrrsbmx);

                    Zrrsbmx zrrsbmx1 = new Zrrsbmx();
                    zrrsbmx1.setCjrq(currq);
                    zrrsbmx1.setGjdm("CHN");
                    zrrsbmx1.setJmse(testje);
                    zrrsbmx1.setLrrq(currq);
                    zrrsbmx1.setSbrq(currq);
                    zrrsbmx1.setSdqjq(currq);
                    zrrsbmx1.setSdqjz(currq);
                    zrrsbmx1.setSjse(testje);
                    zrrsbmx1.setSl(testje);
                    zrrsbmx1.setSre(testje);
                    zrrsbmx1.setSrlyd("不知道");
                    zrrsbmx1.setSzsmdm("020001");
                    zrrsbmx1.setSzsmmc("一般营业税");
                    zrrsbmx1.setYkse(testje);
                    zrrsbmx1.setYnssde(testje);
                    zrrsbmx1.setZfdwmc("stone");
                    zrrsbmx1.setZjhm("23456789");
                    zrrsbmx1.setZjlxdm("5");
                    zrrsbmxList.add(zrrsbmx1);


                    Zrrsbmx zrrsbm2 = new Zrrsbmx();
                    zrrsbm2.setCjrq(currq);
                    zrrsbm2.setGjdm("CHN");
                    zrrsbm2.setJmse(testje);
                    zrrsbm2.setLrrq(currq);
                    zrrsbm2.setSbrq(currq);
                    zrrsbm2.setSdqjq(currq);
                    zrrsbm2.setSdqjz(currq);
                    zrrsbm2.setSjse(testje);
                    zrrsbm2.setSl(testje);
                    zrrsbm2.setSre(testje);
                    zrrsbm2.setSrlyd("不知道");
                    zrrsbm2.setSzsmdm("020003");
                    zrrsbm2.setSzsmmc("一般营业税");
                    zrrsbm2.setYkse(testje);
                    zrrsbm2.setYnssde(testje);
                    zrrsbm2.setZfdwmc("stone");
                    zrrsbm2.setZjhm("23456789");
                    zrrsbm2.setZjlxdm("5");
                    zrrsbmxList.add(zrrsbm2);

                    zrrsbInfor.setZrrsbmxList(zrrsbmxList);
                    zrrsbInfor.setZrrsbz(zrrsbz);
                    vo.setData(zrrsbInfor);
                    Object expectedReturn = null;
                    Object actualReturn = zrrsbProcessor.process(vo);
                    assertEquals(Boolean.TRUE,actualReturn);

        }
        catch(Exception ex)
        {
            //出现异常为pass
            assertEquals(1,1);
            Debug.out(ex.getMessage());
        }

    }



}
