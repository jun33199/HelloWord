package com.ttsoft.bjtax.shenbao.service.adaptor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.skh.sjjh.model.PaymentModel;
import com.syax.skh.sjjh.model.SkhSingleReturn;
import com.syax.skh.sjjh.model.TaxSubjectModel;
import com.syax.skh.sjjh.model.TaxTypeMxModel;
import com.syax.skh.sjjh.model.TurnAccountModel;
import com.syax.skh.sjjh.model.realtime.kk.RealHeadModel;
import com.syax.skh.sjjh.model.realtime.kk.RealTimeKKRequestModel;
import com.syax.skh.sjjh.service.SKHSjjhProxy;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: 税库行外部接口业务适配器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.1
 */

public class SKHAdaptor {

  public static String YHKK_RESULT_SUCCESS="1";//扣款成gon

  public static String YHKK_RESULT_LOST="2";//扣款失败

  public static String YHKK_RESULT_NOREASON="3";//扣款失败未知原因
  
  public static String YHKK_KEY_RESULT="RESULT";//扣款回执MapKey 
  
  public static String YHKK_KEY_ADDWORD="ADDWORD";//扣款回执MapKey

  public SKHAdaptor() {
  }

  /**
   * 扣款接口
   * @param yhdm 银行代码
   * @param zh 银行账户
   * @param qxdm 区县代码
   * @param je 总金额
   * @param ypdsJkss 一票多税缴款书List
   * @param ud 用户数据
   * @return 成功标记
   * @throws java.lang.Exception 异常
   */
  public Object transferMoneyFromNsrZhToGk(String qxdm,
                                         double je, List ypdsJkss,String swjgzzjgdm,
                                         UserData ud,Sfxy sfxy) throws Exception {
    // 杨健添加处理金额部分，将小数点后的多余 000 等去掉
    DecimalFormat df = new DecimalFormat("0.00");
    String msg = null;
    Map reObj=null;
    /**
        RealTimeKKRequestModel（实时扣款请求对象）不能为空，其子属性或子对象属性要求如下：
     1. swijgzzjgdm（税务机关组织机构代码）String 必填项
     2.RealTimeKKRequestModel \uF0E0RealHeadModel(实时业务头)子对象
     1)	TaxOrgCode（征收机关代码） String 必填项 描述：区县代码
     2)	entrustDate(委托日期) Date不填
     3)	traNo（交易流水号）String不填
     3.TurnAccountModel(转帐信息)子对象
     1)	经收类别
     2)	PayeeBankNo（收款行行号）String区县代码
     3)	PayeeOrgCode（收款单位代码）String区县代码
     4)	PayeeAcct（收款人账号）String区县代码
     5)	PayeeName（收款人名称）String区县代码
     6)	PayBkCode（付款行行号）String 填写税费入库方式认定表中的银行代码
     7)	PayOpBkCode（付款开户行行号）String填写税费入库方式认定表中的银行代码
     4.PaymentModel(基本信息，包含付款信息和税票信息)
     1)	PayAcct（付款账户）String 必填项 描述：“三方委托缴款协议书”中纳税人的缴款户账号（税费入库方式认定表中银行帐户）
     2)	HandOrgName（缴款单位名称）String 必填项 描述：纳税人名称
     3)	TraAmt（交易金额）BigDecimal 必填项 描述：本次实时扣款的总金额
     4)	TaxVouNo（税票号码）String 必填项 描述：电子缴款书流水号，征收机关唯一标识一个缴款书的标志。
     5)	BillDate（开票日期）Date 必填项 描述：缴款书开具日期 填申报日期
     6)	LimitDate（限缴日期）必填项 描述：缴款书限缴期限 填申报日期
     7)	TaxPayCode（纳税人编码）String 必填项 描述： 计算机代码
     8)	TaxPayName（纳税人名称）String 必填项描述： 纳税人名称
     9)	BudgetType（预算种类）String 必填项 填写“1”
     10)TrimSign（整理期标志）String 必填项 填写“0”
     11)CorpType（企业注册类型）String 必填项 填“x”
     12)PrintVouSign（打印付款凭证标志）String 必填项 填写“1”
     13)TaxTypeNum（税种条数）int 必填项 描述：本缴款书中所包含的税种条数，征收机关填写
     14)taxTypeList（税种明细，其中是TaxTypeMxModel对象）List 必填项 描述：包含1…….N个税种信息
     15)TaxTypeMxModel属性如下：
     16)ProjectId（税种序号）int 必填项 描述：税种序号，大小小于100。
     17)BudgetSubjectCode（预算科目代码）String 必填项 描述：见公共数据更新通知报文中的预算科目代码 就是申报中的预算科目代码
     18)TaxTypeName（税种名称）String 必填项 描述：需要委托银行打印付款凭证时必填。 税种名称
     19)BudgetLevelCode（预算级次代码）String 必填项 暂时填写为“1”
     20)BudgetLevelName（预算级次名称）String 必填项 暂时填写为“中央”
     21)TaxStartDate（税款所属日期）Date 必填项 描述：缴款书税款所属日期起。
     22)TaxEndDate（税款所属日期止）Date 必填项 描述：缴款书税款所属日期止。
     23)TaxType（税款类型）String 必填项 描述：见附录代码表
     24)TaxTypeAmt（税种金额）BigDecimal 必填项 描述：当前税种所属税款金额
     25)DetailNum（明细条数）int 必填项 描述：所包含的税目条数
     26)taxSubjectList（税目明细，其中的Object是TaxSubjectModel）必填项 描述：包含1……N个税目信息
     27)TaxSubjectModel属性如下：
     28)DetailNo（明细序号）int 必填项 描述：税目序号
     29)TaxSubjectCode（税目代码）String 必填项 描述：税目代码
     30)TaxSubjectName（税目名称）String 必填项 描述：税目名称
     31)TaxNumber（课税数量）int 必填项 描述：
     32)TaxAmt（计税金额）BigDecimal 必填项 描述：
     33)TaxRate（税率）BigDecimal 必填项 描述：小于1。
     34)ExpTaxAmt（应缴税额）BigDecimal 必填项
     35)DiscountTaxAmt（扣除额）BigDecimal 必填项 填“0”
     36)FactTaxAmt（实缴税额）BigDecimal 必填项
     */
    for (int i = 0; i < ypdsJkss.size(); i++) {
      YPDSJKS jks = (YPDSJKS) ypdsJkss.get(i);
      jks.printContent();
      //1.构造实时扣款请求根对象
      RealTimeKKRequestModel rkkrm = new RealTimeKKRequestModel();
      ///1.0.设置区县代码
      rkkrm.setSwijgzzjgdm(swjgzzjgdm);
      //log("rkkrm.getSwijgzzjgdm()=" + rkkrm.getSwijgzzjgdm());
      ///1.1.生成报文头对象
      RealHeadModel rhm = new RealHeadModel();
      rkkrm.setRealHeadModel(rhm);
      rhm.setTaxOrgCode(qxdm);
      //log("rhm.getTaxOrgCode()=" + rhm.getTaxOrgCode());
      ///1.2.生成(转帐信息)子对象
      TurnAccountModel tam = new TurnAccountModel();
      //log("rhm.getTaxOrgCode()=" + rhm.getTaxOrgCode());
      rkkrm.setTurnAccountModel(tam);
      tam.setHandleType(qxdm);
      //log("tam.getHandleType()=" + tam.getHandleType());
      tam.setPayeeBankNo(qxdm);
      //log("tam.getPayeeBankNo()=" + tam.getPayeeBankNo());
      tam.setPayeeOrgCode(qxdm);
      //log("tam.getPayeeOrgCode()=" + tam.getPayeeOrgCode());
      tam.setPayeeAcct(qxdm);
      //log("tam.getPayeeOrgCode()=" + tam.getPayeeOrgCode());
      tam.setPayeeName(qxdm);
      //log("tam.getPayeeName()=" + tam.getPayeeName());
      tam.setPayBkCode(sfxy.getYhdm());
      //log("tam.getPayBkCode()=" + tam.getPayBkCode());
      tam.setPayOpBkCode(sfxy.getYhdm());
      //log("tam.getPayOpBkCode()=" + tam.getPayOpBkCode());
      ///1.3.生成基本信息，包含付款信息和税票信息
      PaymentModel pm = new PaymentModel();
      rkkrm.setPaymentModel(pm);
      ////1.3.0.填充基本信息
      pm.setPayAcct(sfxy.getZh()); //（付款账户）String 必填项 描述：“三方委托缴款协议书”中纳税人的缴款户账号（税费入库方式认定表中银行帐户）
      //log("pm.getPayAcct()=" + pm.getPayAcct());
      pm.setHandOrgName(sfxy.getHandOrgName()); //（缴款单位名称）String 必填项 描述：纳税人名称
      //log("pm.getHandOrgName()=" + pm.getHandOrgName());
      pm.setTraAmt(new BigDecimal(df.format(Double.parseDouble(jks.getSjjexx())))); //（交易金额）BigDecimal 必填项 描述：本次实时扣款的总金额
      //log("pm.getTraAmt()=" + pm.getTraAmt());
      pm.setTaxVouNo(jks.getSphm()); //（税票号码）String 必填项 描述：电子缴款书流水号，征收机关唯一标识一个缴款书的标志。
      //log("pm.getTaxVouNo()=" + pm.getTaxVouNo());
      pm.setBillDate(DateUtilPro.getDate(jks.getSbrq(), "yyyyMMdd")); //（开票日期）Date 必填项 描述：缴款书开具日期 填申报日期
      //log("pm.getBillDate()=" + pm.getBillDate());
      pm.setLimitDate(DateUtilPro.getDate(jks.getSbrq(), "yyyyMMdd")); //（限缴日期）必填项 描述：缴款书限缴期限 填申报日期
      //log("pm.getLimitDate()=" + pm.getLimitDate());
      pm.setTaxPayCode(jks.getJsjdm()); //（纳税人编码）String 必填项 描述： 计算机代码
      //log("pm.getTaxPayCode()=" + pm.getTaxPayCode());
      pm.setTaxPayName(jks.getNsrmc()); //（纳税人名称）String 必填项描述： 纳税人名称
      //log("pm.getTaxPayName()=" + pm.getTaxPayName());
      pm.setBudgetType("1"); //（预算种类）String 必填项 填写“1”
      //log("pm.getBudgetType()=" + pm.getBudgetType());
      pm.setTrimSign("0"); //（整理期标志）String 必填项 填写“0”
      //log("pm.getTrimSign()=" + pm.getTrimSign());
      pm.setCorpType("x"); //（企业注册类型）String 必填项 填“x”
      //log("pm.getCorpType()=" + pm.getCorpType());
      pm.setPrintVouSign("1"); //（打印付款凭证标志）String 必填项 填写“1”
      //log("pm.getPrintVouSign()=" + pm.getPrintVouSign());
      pm.setTaxTypeNum(jks.getCurJksSzAndYskmCount()); //（税种和预算科目条数）int 必填项 描述：本缴款书中所包含的税种条数，征收机关填写
      //log("pm.getTaxTypeNum()=" + pm.getTaxTypeNum());
      pm.setProtocolNo(sfxy.getXyshm()); //协议书号(征收机关、商业银行、纳税人三方签订的委托协议书号)
      ////1.3.1.填写税种明细
      List szmxs = new ArrayList();
      pm.setTaxTypeList(szmxs);
      Map ypysJksmxSplitBySzAndYskm = jks.splitCurJksBySzAndYskm();
      Iterator iterator = ypysJksmxSplitBySzAndYskm.keySet().iterator();
      String key = null;
      List tmpypysJksmxs = null;
      List taxSubjectList = null;
      int count = 0;
      while (iterator.hasNext()) {
        key = (String) iterator.next();
        //log("=================分税种(" + szdm + ")生成明细==================");
        tmpypysJksmxs = (List) ypysJksmxSplitBySzAndYskm.get(key); //根据税种区分的一票一税缴款书明细到税目的List
        TaxTypeMxModel ttmm = new TaxTypeMxModel();
        taxSubjectList = new ArrayList();
        ttmm.setTaxSubjectList(taxSubjectList);
        //
        String yskmdm = null;
        String szmc = null;
        String ysjcdm = null;
        String ysjcmc = null;
        String skssksrq = null;
        String skssjsrq = null;
        String sklxdm = null;
        double szje = 0.00;
        int szsmcount = 0;
        //生成到税目的明细
        Map szsm = null;
        for (int j = 0; j < tmpypysJksmxs.size(); j++) {
          szsmcount = j;
          szsm = (Map) tmpypysJksmxs.get(j);
          if (j == 0) {
            yskmdm = (String) szsm.get("yskmdm");
            szmc = (String) szsm.get("szmc");
            ysjcdm = (String) szsm.get("ysjcdm");
            ysjcmc = (String) szsm.get("ysjcmc");
            skssksrq = (String) szsm.get("skssksrq");
            skssjsrq = (String) szsm.get("skssjsrq");
            sklxdm = (String) szsm.get("sklxdm");
          }
          //
          szje += Double.parseDouble( (String) szsm.get("sjse"));
          //
          TaxSubjectModel tsm = new TaxSubjectModel();
          tsm.setDetailNo(j + 1); //（明细序号）int 必填项 描述：税目序号
          //log("tsm.getDetailNo()=" + tsm.getDetailNo());
          tsm.setTaxSubjectCode( (String) szsm.get("szsmdm")); //（税目代码）String 必填项 描述：税目代码
          //log("tsm.getTaxSubjectCode()=" + tsm.getTaxSubjectCode());
          tsm.setTaxSubjectName( (String) szsm.get("szsmmc")); //（税目名称）String 必填项 描述：税目名称
          //log("tsm.getTaxSubjectName()=" + tsm.getTaxSubjectName());
          String kssl = StringUtils.getPartBeforeSeparatorOfStr( (String) szsm.
              get("kssl"), ".");
          //String kssl=StringUtils
          if (szsm.get("kssl") != null) {
            tsm.setTaxNumber(Integer.parseInt(kssl)); //（课税数量）int 必填项 描述：
          }
          else {
            tsm.setTaxNumber(0);
          }
          //log("tsm.getTaxNumber()=" + tsm.getTaxNumber());
          if (! ("".equals(StringUtils.killNull( (String) szsm.get("jsje"))))) {
            tsm.setTaxAmt(new BigDecimal( (String) szsm.get("jsje"))); //（计税金额）BigDecimal 必填项 描述：
          }
          else {
            tsm.setTaxAmt(new BigDecimal(0));
          }
          //log("tsm.getTaxAmt()=" + tsm.getTaxAmt());
          if (! ("".equals(StringUtils.killNull( (String) szsm.get("sl"))))) {
            tsm.setTaxRate(new BigDecimal( (String) szsm.get("sl"))); //（税率）BigDecimal 必填项 描述：小于1。
          }
          else {
            tsm.setTaxRate(new BigDecimal(0));
          }
          //log("tsm.getTaxRate()=" + tsm.getTaxRate());
          tsm.setExpTaxAmt(new BigDecimal( (String) szsm.get("sjse"))); //（应缴税额）BigDecimal 必填项
          //log("tsm.getExpTaxAmt()=" + tsm.getExpTaxAmt());
          tsm.setDiscountTaxAmt(new BigDecimal(0)); //（扣除额）BigDecimal 必填项 填“0”
          //log("tsm.getDiscountTaxAmt()=" + tsm.getDiscountTaxAmt());
          tsm.setFactTaxAmt(new BigDecimal( (String) szsm.get("sjse"))); //（实缴税额）BigDecimal 必填项
          //log("tsm.getFactTaxAmt()=" + tsm.getFactTaxAmt());
          taxSubjectList.add(tsm);
        }
        ttmm.setProjectId(count + 1); //（税种序号）int 必填项 描述：税种序号，大小小于100。
        //log("ttmm.getProjectId()=" + ttmm.getProjectId());
        ttmm.setBudgetSubjectCode(yskmdm); //（预算科目代码）String 必填项 描述：见公共数据更新通知报文中的预算科目代码 就是申报中的预算科目代码
        //log("ttmm.getBudgetSubjectCode()=" + ttmm.getBudgetSubjectCode());
        ttmm.setTaxTypeName(szmc); //（税种名称）String 必填项 描述：需要委托银行打印付款凭证时必填。 税种名称
        //log("ttmm.getTaxTypeName()=" + ttmm.getTaxTypeName());
        ttmm.setBudgetLevelCode(ysjcdm); //（预算级次代码）String 必填项 暂时填写为“1”,已修改为系统生成的代码
        //log("ttmm.getBudgetLevelCode=" + ttmm.getBudgetLevelCode());
        ttmm.setBudgetLevelName(ysjcmc); //（预算级次名称）String 必填项 暂时填写为“中央,已修改为系统生成的名称”
        //log("ttmm.getBudgetLevelName()=" + ttmm.getBudgetLevelName());
        ttmm.setTaxStartDate(DateUtilPro.getDate(skssksrq, "yyyyMMdd")); //（税款所属日期）Date 必填项 描述：缴款书税款所属日期起。
        //log("ttmm.getTaxStartDate()=" + ttmm.getTaxStartDate());
        ttmm.setTaxEndDate(DateUtilPro.getDate(skssjsrq, "yyyyMMdd")); //（税款所属日期止）Date 必填项 描述：缴款书税款所属日期止。
        //log("ttmm.getTaxEndDate()=" + ttmm.getTaxEndDate());
        ttmm.setTaxType(sklxdm); //（税款类型）String 必填项 描述：见附录代码表
        //log("ttmm.getTaxType()=" + ttmm.getTaxType());
        ttmm.setTaxTypeAmt(new BigDecimal(df.format(szje))); //（税种金额）BigDecimal 必填项 描述：当前税种所属税款金额
        //log("ttmm.getTaxTypeAmt()=" + ttmm.getTaxTypeAmt());
        ttmm.setDetailNum(szsmcount + 1); //（明细条数）int 必填项 描述：所包含的税目条数
        //log("ttmm.getDetailNum(()=" + ttmm.getDetailNum());
        //
        szmxs.add(ttmm);
        count++;
      }
      //
      long start=System.currentTimeMillis();
      SkhSingleReturn ssr=SKHSjjhProxy.sendRealTimeKKRequest(rkkrm);
      reObj = new HashMap();
      reObj.put(YHKK_KEY_RESULT, ssr.getResult());
      reObj.put(YHKK_KEY_ADDWORD, ssr.getAddWord());
      //reObj.setJksj(new Timestamp(ssr.getTaxDate().getTime()));
      System.out.println("三方协议银行实时扣款共耗费时间 " + (System.currentTimeMillis()-start)+"毫秒！");
    }
    return reObj;
  }

  public Object gghsbSendYhkk(String qxdm, String nd, String yd, String bizph) throws
      Exception {
    //log("入口参数{qxdm:"+qxdm+"|nd:"+nd+"|yd:"+yd+"|bizPh:"+bizph+"}");
    long start = System.currentTimeMillis();
    //log("银行批量扣款开始！");
    SkhSingleReturn ssr=null;
    //2013税库行项目删除该接口方法，由于网上申报该段代码未使用，而且打包报错，故注释 zhangyj 20131205
    //SKHSjjhProxy.sendBatchKKRequestModel(nd,yd,qxdm,bizph);
    //log("银行批量扣款结束！");
    System.out.println("银行批量扣款共耗费时间 " + (System.currentTimeMillis() - start) + "毫秒！");
    return null;
  }

  private void log(Object str) {
    if (true) {
      System.out.println("[WSSB SKHAdaptor DEBUG " + (new Date()) + "]" + str);
    }
  }

}
