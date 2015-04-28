package com.ttsoft.bjtax.shenbao.voucher.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.proxy.SKHProxy;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzMx;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzsmmx;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.List;


public class SbcghzPrint001Action
    extends ShenbaoAction {

  public ActionForward doShow(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    try {
      SbcghzPrint001ActionForm myForm = (SbcghzPrint001ActionForm) form;
      //myForm=this.getTestPrintData(myForm);
      request.setAttribute(mapping.getAttribute(), myForm);
      return mapping.findForward("Show");
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

//  private SbcghzPrint001ActionForm getTestPrintData(SbcghzPrint001ActionForm f){
//    f.setSbbh("1234567890123456");
//    f.setSbrq("2005年10月1日");
//    f.setJsjdm("06000000");
//    f.setNsrmc("测试缴款人");
//    f.setActionType("Show");
//    f.setHjjexx("192.00");
//    f.setZh("12345678");
//    f.setYhmc("测试银行");
//    f.setBz("测试备注");
//    ArrayList tmpList=new ArrayList();
//    HashMap map=null;
//    for(int i=0;i<10;i++){
//      map=new HashMap();
//      map.put("szsmmc","税种税目"+i);
//      map.put("kssl","kssl"+i);
//      map.put("jsje","jsje"+i);
//      map.put("sjje","sjje"+i);
//      tmpList.add(map);
//    }
//    f.setMxs(tmpList);
//    return f;
//  }
//
//  private void testSKHProxyGenerateYpdsJksWithNoSbInfo(){
//    //0.
//    YhsbInfo yhsbInfo=null;
//    YhsbSzMx yhsbSzmx=null;
//    YhsbSzsmmx yhsbSzsmmx=null;
//    ArrayList szList=null;
//    ArrayList szsmList=null;
//    //1.
//    Timestamp now=new Timestamp(System.currentTimeMillis());
//    //2.生成主数据
//    yhsbInfo = new YhsbInfo();
//    yhsbInfo.setJsjdm("21005900");
//    yhsbInfo.setBz("备注");
//    yhsbInfo.setJksj(now);
//    yhsbInfo.setLrr("hazz");
//    yhsbInfo.setQxdm("21");
//    //yhsbInfo.setSbbh(String sbbh);
//    yhsbInfo.setSbrq(now);
//    yhsbInfo.setSklxdm("100");
//    yhsbInfo.setSzCount(2);
//    yhsbInfo.setYhdm("测试银行代码");
//    yhsbInfo.setYhmc("测试银行名称");
//    yhsbInfo.setZh("测试银行账户");
//    yhsbInfo.setZsswjgzzjgdm("2101");
//    yhsbInfo.setZyrq(now);
//    ///2.0.生成税种列表税据
//    szList=new ArrayList();
//    ////2.0.1.生成第一个税种数据
//    yhsbSzmx=new YhsbSzMx();
//    /////2.0.1.0.生成第一个税种数据的基本数据
//    yhsbSzmx.setSjje(new BigDecimal(199.99));
//    yhsbSzmx.setSkssjsrq(new Timestamp(105,9,0,0,0,0,0));
//    yhsbSzmx.setSkssksrq(new Timestamp(105,9,30,0,0,0,0));
//    yhsbSzmx.setSzdm("02");
//    yhsbSzmx.setSzsmCount(2);
//    yhsbSzmx.setXjrq(now);
//    yhsbSzmx.setYnse(new BigDecimal(199.99));
//    /////2.0.1.1.生成第一个税种数据的税种税目明细数据
//    szsmList=new ArrayList();
//    //////2.0.1.1.0.生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setKssl(new BigDecimal(11));
//    yhsbSzsmmx.setSjje(new BigDecimal(100));
//    yhsbSzsmmx.setSl(null);
//    yhsbSzsmmx.setSzsmdm("021101");
//    yhsbSzsmmx.setYnse(new BigDecimal(100));
//    szsmList.add(yhsbSzsmmx);
//    //////2.0.1.1.1.生成第一个税种数据的第二个税种税目明细数据
//    yhsbSzsmmx=new YhsbSzsmmx();
//    yhsbSzsmmx.setKssl(new BigDecimal(12));
//    yhsbSzsmmx.setSjje(new BigDecimal(99.99));
//    yhsbSzsmmx.setSl(null);
//    yhsbSzsmmx.setSzsmdm("021102");
//    yhsbSzsmmx.setYnse(new BigDecimal(99.99));
//    szsmList.add(yhsbSzsmmx);
//    //////2.0.1.1.2.加入第一个税种数据
//    yhsbSzmx.setSzsmList(szsmList);
//    /////2.0.1.2.加入szlist
//    szList.add(yhsbSzmx);
//    ////2.0.2.生成第二个税种数据
//    yhsbSzmx=new YhsbSzMx();
//    /////2.0.2.0.生成第一个税种数据的基本数据
//    yhsbSzmx.setSjje(new BigDecimal(299.99));
//    yhsbSzmx.setSkssjsrq(new Timestamp(105,9,0,0,0,0,0));
//    yhsbSzmx.setSkssksrq(new Timestamp(105,9,30,0,0,0,0));
//    yhsbSzmx.setSzdm("05");
//    yhsbSzmx.setSzsmCount(2);
//    yhsbSzmx.setXjrq(now);
//    yhsbSzmx.setYnse(new BigDecimal(299.99));
//    /////2.0.2.1.生成第二个税种数据的税种税目明细数据
//    szsmList=new ArrayList();
//    //////2.0.1.1.0.生成第二个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setKssl(new BigDecimal(21));
//    yhsbSzsmmx.setSjje(new BigDecimal(100));
//    yhsbSzsmmx.setSl(null);
//    yhsbSzsmmx.setSzsmdm("050111");
//    yhsbSzsmmx.setYnse(new BigDecimal(100));
//    szsmList.add(yhsbSzsmmx);
//    //////2.0.1.1.1.生成第二个税种数据的第二个税种税目明细数据
//    yhsbSzsmmx=new YhsbSzsmmx();
//    yhsbSzsmmx.setKssl(new BigDecimal(22));
//    yhsbSzsmmx.setSjje(new BigDecimal(199.99));
//    yhsbSzsmmx.setSl(null);
//    yhsbSzsmmx.setSzsmdm("050112");
//    yhsbSzsmmx.setYnse(new BigDecimal(199.99));
//    szsmList.add(yhsbSzsmmx);
//    //////2.0.1.1.2.加入第二个税种数据
//    yhsbSzmx.setSzsmList(szsmList);
//    /////2.0.2.2.加入szlist
//    szList.add(yhsbSzmx);
//    ////2.0.3.设置基本数据
//    yhsbInfo.setSzList(szList);
//
//    ////////
//    try{
//      SKHProxy.generateYpdsJksWithNoSbInfo(yhsbInfo, 499.98);
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//  }
//
//  private void testSKHProxygenerateYpdsJksWithSbInfoAndSzAlterable(){
//    //0.
//    YhsbInfo yhsbInfo=null;
//    YhsbSzMx yhsbSzmx=null;
//    YhsbSzsmmx yhsbSzsmmx=null;
//    ArrayList szList=null;
//    ArrayList szsmList=null;
//    //1.
//    Timestamp now=new Timestamp(System.currentTimeMillis());
//    //2.生成主数据
//    yhsbInfo = new YhsbInfo();
//    yhsbInfo.setJsjdm("21005900");
//    yhsbInfo.setSbbh("210059000500008375");
//    yhsbInfo.setQxdm("21");
//    //yhsbInfo.setSbbh(String sbbh);
//    yhsbInfo.setSbrq(now);
//    yhsbInfo.setSklxdm("100");
//    yhsbInfo.setSzCount(2);
//    yhsbInfo.setZsswjgzzjgdm("2101");
//    yhsbInfo.setZyrq(now);
//    ///2.0.生成税种列表税据
//    szList=new ArrayList();
//    ////2.0.1.生成第一个税种数据
//    yhsbSzmx=new YhsbSzMx();
//    /////2.0.1.0.生成第一个税种数据的基本数据
//    yhsbSzmx.setSjje(new BigDecimal(2900));
//    yhsbSzmx.setSkssjsrq(new Timestamp(105,9,0,0,0,0,0));
//    yhsbSzmx.setSkssksrq(new Timestamp(105,9,30,0,0,0,0));
//    yhsbSzmx.setSzdm("02");
//    yhsbSzmx.setSzsmCount(7);
//    yhsbSzmx.setXjrq(now);
//    yhsbSzmx.setYnse(new BigDecimal(2900));
//    /////2.0.1.1.生成第一个税种数据的税种税目明细数据
//    szsmList=new ArrayList();
//    //////1生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(300));
//    yhsbSzsmmx.setSzsmdm("021101");
//    yhsbSzsmmx.setYnse(new BigDecimal(300));
//    szsmList.add(yhsbSzsmmx);
//    //////2生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(300));
//    yhsbSzsmmx.setSzsmdm("021102");
//    yhsbSzsmmx.setYnse(new BigDecimal(300));
//    szsmList.add(yhsbSzsmmx);
//    //////3生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(300));
//    yhsbSzsmmx.setSzsmdm("021103");
//    yhsbSzsmmx.setYnse(new BigDecimal(300));
//    szsmList.add(yhsbSzsmmx);
//    //////4生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(500));
//    yhsbSzsmmx.setSzsmdm("023201");
//    yhsbSzsmmx.setYnse(new BigDecimal(500));
//    szsmList.add(yhsbSzsmmx);
//    //////5生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(500));
//    yhsbSzsmmx.setSzsmdm("027102");
//    yhsbSzsmmx.setYnse(new BigDecimal(500));
//    szsmList.add(yhsbSzsmmx);
//    //////6生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(500));
//    yhsbSzsmmx.setSzsmdm("027103");
//    yhsbSzsmmx.setYnse(new BigDecimal(500));
//    szsmList.add(yhsbSzsmmx);
//    //////7生成第一个税种数据的第一个税种税目明细数据
//    yhsbSzsmmx = new YhsbSzsmmx();
//    yhsbSzsmmx.setSjje(new BigDecimal(500));
//    yhsbSzsmmx.setSzsmdm("027104");
//    yhsbSzsmmx.setYnse(new BigDecimal(500));
//    szsmList.add(yhsbSzsmmx);
//    //////2.0.1.1.2.加入第一个税种数据
//    yhsbSzmx.setSzsmList(szsmList);
//    /////2.0.1.2.加入szlist
//    szList.add(yhsbSzmx);
//    ////2.0.3.设置基本数据
//    yhsbInfo.setSzList(szList);
//    ////////
//    try{
//      List jksList=SKHProxy.generateYpdsJksWithSbInfoAndSzAlterable(yhsbInfo, 2900);
//      SKHProxy.WsbYhJkKkResult(true,jksList);
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//  }


}
