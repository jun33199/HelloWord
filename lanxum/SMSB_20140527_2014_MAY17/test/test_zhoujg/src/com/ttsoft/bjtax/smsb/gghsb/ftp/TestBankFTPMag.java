package com.ttsoft.bjtax.smsb.gghsb.ftp;

import junit.framework.*;
import java.util.*;
import java.sql.Timestamp;

import com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkxx;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;

public class TestBankFTPMag extends TestCase {
    private BankFTPMag bankFTPMag = null;

    protected void setUp() throws Exception {
        super.setUp();
        /**@todo verify the constructors*/
        bankFTPMag = new BankFTPMag();
    }

    protected void tearDown() throws Exception {
        bankFTPMag = null;
        super.tearDown();
    }

    public void testCheckLsLogFile() throws Exception {
        String fileName = "LS20041105.log";
        boolean expectedReturn = true;
        boolean actualReturn = bankFTPMag.checkLsLogFile(fileName);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testDelete() throws Exception {
        String fileName = "LS20041205.log";
        bankFTPMag.delete(fileName,2);
    }

    public void testGetFtpYhfk() throws Exception {
        String flag = "2004100105";
        String[] files = null;
        HashMap expectedReturn = new HashMap();
        /*
         123,ABC,,王五,,123,海淀地税,,05,,,,,,,,,,1234567890123456,100.00,,123,20041001,20041030,123,456,01,级次,010,0,20041020,,05
         123,ABC,,李四,,123,海淀地税,,05,,,,,,,,,,1234567890123456,100.00,,123,20041001,20041030,123,456,01,级次,010,0,20041020,,05
         */
        HashMap sum = new HashMap();
        sum.put("COUNT", "2");
        sum.put("TOTALAMOUNT", "100");
        sum.put("ERRORCOUNT", "0");
        sum.put("ERRORTOTALAMOUNT", "0");
        expectedReturn.put("TZ2004100105.20041010.txt", sum);
        Yhkkhzxx m_objYhkkhzxx = new Yhkkhzxx();

        HashMap actualReturn = bankFTPMag.getFtpYhfk(flag, files);
        HashMap actualSum = (HashMap) actualReturn.get(
            "TZ2004100105.20041010.txt");
        assertEquals("return value", (String) actualSum.get("ERRORTOTALAMOUNT"),
                     (String) sum.get("ERRORTOTALAMOUNT"));
        List actualList = (List)actualReturn.get("TM2004100105.20041010.txt");
        m_objYhkkhzxx.setNsrmc("王五");
        Yhkkhzxx actualYhkkhzxx = (Yhkkhzxx)actualList.get(0);
        assertEquals("return value", actualYhkkhzxx.getNsrmc(),m_objYhkkhzxx.getNsrmc());
        m_objYhkkhzxx.setNsrmc("李四");
        actualYhkkhzxx = (Yhkkhzxx)actualList.get(1);
        assertEquals("return value", actualYhkkhzxx.getNsrmc(),m_objYhkkhzxx.getNsrmc());
    }

    public void testGetLeLogFile() throws Exception {
        String fileName = "LS20041105.log";
        String[][] expectedReturn = {
            {"01", "true"},
            {"02", "true"},
            {"03", "true"},
            {"04", "true"},
            {"05", "true"}
        };
        String[][] actualReturn = bankFTPMag.getLeLogFile(fileName);
        assertEquals("return value", expectedReturn.length, actualReturn.length);
    }

    public void testPutKkData() throws Exception {
        HashMap kkDate = new HashMap();
        HashMap sum = new HashMap();
        sum.put("FILENAME", "TZ2004100105.txt");
        sum.put("COUNT", "2");
        sum.put("TOTALAMOUNT", "200");
        kkDate.put("HZWJ", sum);

        HashMap item = new HashMap();
        item.put("FILENAME", "TM2004100105.txt");
        ArrayList al = new ArrayList();
        Yhkkxx m_objYhkkxx = new Yhkkxx();
        m_objYhkkxx.setJkpzh("12345");
        m_objYhkkxx.setJsjdm("abc");
        m_objYhkkxx.setNsrmc("张三");
        m_objYhkkxx.setYhdm("05");
        m_objYhkkxx.setZh("123456789012345");
        m_objYhkkxx.setSzsmdm("ABC");
        m_objYhkkxx.setSzsmmc("jsoft company");
        m_objYhkkxx.setSjje(100);
        m_objYhkkxx.setSkssksrq(SfTimeUtil.getTimestamp("20041001"));
        m_objYhkkxx.setSkssjsrq(SfTimeUtil.getTimestamp("20041031"));
        m_objYhkkxx.setYskmdm("123");
        m_objYhkkxx.setYsjcdm("123");
        m_objYhkkxx.setYsjcmc("junior");
        m_objYhkkxx.setGkzzjgdm("GK01");
        m_objYhkkxx.setGkzzjgmc("nation money");
        m_objYhkkxx.setSwjgzzjgdm("01");
        m_objYhkkxx.setSwjgzzjgmc("hai dian");
        al.add(m_objYhkkxx);
        m_objYhkkxx = new Yhkkxx();
        m_objYhkkxx.setJkpzh("12345");
        m_objYhkkxx.setJsjdm("abc");
        m_objYhkkxx.setNsrmc("李四");
        m_objYhkkxx.setYhdm("05");
        m_objYhkkxx.setZh("123456789012345");
        m_objYhkkxx.setSzsmdm("ABC");
        m_objYhkkxx.setSzsmmc("oracle company");
        m_objYhkkxx.setSjje(100);
        m_objYhkkxx.setSkssksrq(SfTimeUtil.getTimestamp("20041010"));
        m_objYhkkxx.setSkssjsrq(SfTimeUtil.getTimestamp("20041031"));
        m_objYhkkxx.setYskmdm("123");
        m_objYhkkxx.setYsjcdm("123");
        m_objYhkkxx.setYsjcmc("junior");
        m_objYhkkxx.setGkzzjgdm("GK01");
        m_objYhkkxx.setGkzzjgmc("nation money");
        m_objYhkkxx.setSwjgzzjgdm("01");
        m_objYhkkxx.setSwjgzzjgmc("hai dian");
        al.add(m_objYhkkxx);
        item.put("CONTENT", al);
        kkDate.put("MXWJ", item);
        bankFTPMag.putKkData(kkDate);
    }

}
