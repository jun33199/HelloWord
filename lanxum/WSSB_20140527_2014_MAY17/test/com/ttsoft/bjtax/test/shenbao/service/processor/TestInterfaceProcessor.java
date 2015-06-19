/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����InterFaceProcessor��getXh����</p>
 * @author ����һ�飭��GuZhiXian
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
     * ����InterFaceProcessor��getXh����
     * ���������������Ѵ��ڣ����²���ʱ��ŵ���
     * ����ʱ���ڲ�ѯ��ŵ�Sql���������For Update���Գ����޷�ͨ��
     * Ԥ�ڽ�����õ�������ȷ�ķ���ֵ
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
            // ���쳣�׳���NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ����InterFaceProcessor��getXh����
     * ���������������Ѵ��ڣ����±仯ʱ��Ź�1(49)
     * ����ʱ���ڲ�ѯ��ŵ�Sql���������For Update���Գ����޷�ͨ��
     * Ԥ�ڽ�����õ�������ȷ�ķ���ֵ
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
            // ���쳣�׳���NoPass
            assertTrue(ex.toString(), false);
        }
    }

    /**
     * ����InterFaceProcessor��getXh����
     * �������������ݲ�����
     * ����ʱ���ڲ�ѯ��ŵ�Sql���������For Update���Գ����޷�ͨ��
     * Ԥ�ڽ�����õ�������ȷ�ķ���ֵ
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
            // ���쳣�׳���NoPass
            assertTrue(ex.toString(), false);
        }
    }

}