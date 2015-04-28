/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.test.shenbao.service.processor;

import junit.framework.*;
import com.ttsoft.bjtax.shenbao.service.processor.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.test.shenbao.util.InitORMapping;
import com.ttsoft.bjtax.shenbao.szsm.SzsmActionConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.sql.Timestamp;
import com.ttsoft.common.model.UserData;
import java.util.ArrayList;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import java.math.BigDecimal;
import com.ttsoft.bjtax.test.shenbao.util.SetEnv;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 测试InterFaceProcessor的getXh方法</p>
 * @author 开发一组－－GuZhiXian
 * @version 1.1
 */
public class TestInterfaceProcessor  extends TestCase
{

    protected void setUp() throws Exception {
        super.setUp();
        SetEnv.setProperties();
        InitORMapping.init_aux();
    }

    /**
     * 测试InterFaceProcessor的getXh方法
     * 计算机代码的数据已存在，年月不变时序号递增
     * 测试时由于查询序号的Sql语句中用了For Update所以程序无法通过
     * 预期结果：得到所有正确的返回值
     * @throws BaseException
     */
    public void testProcess_M_getXh1() throws BaseException
    {
        try
        {
            String xhData = InterFaceProcessor.getXh("06534190","200404");
            assertTrue("xh=505050",xhData.equalsIgnoreCase("505050"));
            xhData = InterFaceProcessor.getXh("06534190","200404");
            assertTrue("xh=505051",xhData.equalsIgnoreCase("505051"));
            xhData = InterFaceProcessor.getXh("06534190","200404");
            assertTrue("xh=505052",xhData.equalsIgnoreCase("505052"));
        }
        catch(Exception ex)
        {
            // 有异常抛出，NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试InterFaceProcessor的getXh方法
     * 计算机代码的数据已存在，年月变化时序号归1(49)
     * 测试时由于查询序号的Sql语句中用了For Update所以程序无法通过
     * 预期结果：得到所有正确的返回值
     * @throws BaseException
     */
    public void testProcess_M_getXh2() throws BaseException
    {
        try
        {
            String xhData = InterFaceProcessor.getXh("06534190","200405");
            assertTrue("xh=48",xhData.equalsIgnoreCase("48"));
            xhData = InterFaceProcessor.getXh("06534190","200405");
            assertTrue("xh=49",xhData.equalsIgnoreCase("49"));
            xhData = InterFaceProcessor.getXh("06534190","200405");
            assertTrue("xh=50",xhData.equalsIgnoreCase("50"));
        }
        catch(Exception ex)
        {
            // 有异常抛出，NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * 测试InterFaceProcessor的getXh方法
     * 计算机代码的数据不存在
     * 测试时由于查询序号的Sql语句中用了For Update所以程序无法通过
     * 预期结果：得到所有正确的返回值
     * @throws BaseException
     */
    public void testProcess_M_getXh3() throws BaseException
    {
        try
        {
            String xhData = InterFaceProcessor.getXh("06025810","200404");
            assertTrue("xh=48",xhData.equalsIgnoreCase("48"));
            xhData = InterFaceProcessor.getXh("06025810","200404");
            assertTrue("xh=49",xhData.equalsIgnoreCase("49"));
            xhData = InterFaceProcessor.getXh("06025810","200404");
            assertTrue("xh=50",xhData.equalsIgnoreCase("50"));
        }
        catch(Exception ex)
        {
            // 有异常抛出，NoPass
            assertTrue(ex.toString(), false);
        }
    }

}