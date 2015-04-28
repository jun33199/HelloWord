//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\CzzsndMapConstant.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd;

import java.util.HashMap;
import java.util.Map;


/**
 * 查账征收个人独资和合伙企业年度申报
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class CzzsndMapConstant
{

   /**
    * public static final String LIST_TZZSBSJ = "LIST_TZZSBSJ";
    *
    * 投资者申报数据
    */
   public static final String LIST_TZZSBSJ = "LIST_TZZSBSJ";

   /**
    * public static final String LIST_QYSBSJ = "LIST_QYSBSJ";
    *
    * 企业申报数据
    */
   public static final String LIST_QYSBSJ = "LIST_QYSBSJ";

   /**
    * public static final String RESULT = "RESULT";
    *
    * 操作结果
    */
   public static final String RESULT = "RESULT";

   /**
    * public static final String JSJDM = "JSJDM";
    *
    * 计算机代码
    */
   public static final String JSJDM = "JSJDM";

   /**
    * public static final String LIST_FPBLSJ = "LIST_FPBLSJ";
    *
    * 分配比例数据
    *
    * List里面是和投资者相同数据个List，内层List是多个 查帐征收年度分配比例数据
    * 值对象。
    */
   public static final String LIST_FPBLSJ = "LIST_FPBLSJ";

   /**
    * 投资者明细数据
    */
   public static final String LIST_TZZMX = "LIST_TZZMX";

   /**
    * public static final String LIST_JMSJ = "LIST_JMSJ";
    *
    * 减免数据
    */
   public static final String LIST_JMSJ = "LIST_JMSJ";

   /**
    * public static final String LIST_SLBSJ = "LIST_SLBSJ";
    *
    * 税率表数据
    */
   public static final String LIST_SLBSJ = "LIST_SLBSJ";

   /**
    * public static final String CZZSNBZB = "CZZSNBZB";
    *
    * 查帐征收年报主数据
    */
   public static final String CZZSNBZB = "CZZSNBZB";

   public static final String CZZSNBEXISTED = "CZZSNBEXISTED";

   /**
    * 登记投资方数据List
    */
   public static final String LIST_TZF = "TZF";

   /**
    * 登记基本数据
    */
   public static final String JBSJ = "JBSJ";

   /**
    * public static final int INDEX_HC_TZZSJ = 39;
    *
    * 投资者数据在表中的行次数据
    */
   public static final int INDEX_HC_TZZSJ = 39;

   /**
    * public static final int INDEX_HC_FPBL = 39;
    *
    * 分配比例数据在表中的行次数据
    */
   public static final int INDEX_HC_FPBL = 51;

   public Map czMap=new HashMap();
   
   public static CzzsndMapConstant inst=new CzzsndMapConstant();
   
   
   public CzzsndMapConstant(){
   	  czMap.put("1","一、企业收入总额");
   	  czMap.put("2","　　减：成本");
   	  czMap.put("3","　　费用、税金");
   	  czMap.put("4","　　营业外支出");
   	  czMap.put("5","二、企业利润总额(1-2-3-4)");
   	  czMap.put("6","三、纳税调整增加额(7+19+30)");
   	  czMap.put("7","　　1.超过规定比例扣除的项目");
   	  czMap.put("8","　　　(1)从业人员工资支出");
   	  czMap.put("9","　　　(2)职工福利费");
   	  czMap.put("10","　　　(3)职工教育经费");
   	  czMap.put("11","　　　(4)工会经费");
   	  czMap.put("12","　　　(5)利息支出");
   	  czMap.put("13","　　　(6)广告费");
   	  czMap.put("14","　　　(7)业务招待费");
   	  czMap.put("15","　　　(8)教育和公益事业捐赠");
   	  czMap.put("16","　　　(9)提取折旧费");
   	  czMap.put("17","　　　(10)无形资产摊销");
   	  czMap.put("18","　　　(11)其他");
   	  
   	  czMap.put("19","　　2.不允许扣除的项目(20+..+29)");
   	  czMap.put("20","　　　(1)资本性支出");
   	  czMap.put("21","　　　(2)无形资产受让、开发支出");
   	  czMap.put("22","　　　(3)违法经营罚款和被没收财物损失");
   	  czMap.put("23","　　　(4)税收滞纳金、罚金、罚款 ");
   	  czMap.put("24","　　　(5)灾害事故损失赔偿 ");
   	  czMap.put("25","　　　(6)非教育和公益事业捐赠");
   	  czMap.put("26","　　　(7)各种赞助支出");
   	  czMap.put("27","　　　(8)计提的各种准备金");
   	  czMap.put("28","　　　(9)投资者的工资");
   	  czMap.put("29","　　　(10)与收入无关的支出");
   	  czMap.put("30","　　3.应税收益项目(31+32)");
   	  czMap.put("31","　　　(1)少计应税收益");
   	  czMap.put("32","　　　(2)未计应税收益");
   	  czMap.put("33","四、纳税调整减少额 (34+..+37)");
   	  czMap.put("34","　　1.弥补亏损");
   	  czMap.put("35","　　2.国库券利息收入");
   	  czMap.put("36","　　3.投资者标准费用扣除额");
   	  
   	  czMap.put("37","　　4.其他");
   	  czMap.put("38","五、调整后的应纳税所得额(5+6-33)");
   	  czMap.put("39","六、分配比例");
   	  czMap.put("40","七、应纳税所得额(38*39)");
   	  czMap.put("41","八、适用税率");
   	  czMap.put("42","九、速算扣除数");
   	  czMap.put("43","十、应纳所得税额(40*41-42)");
   	  czMap.put("44","　　减：减、免所得税额");
   	  czMap.put("45","十一、应缴入库所得税额(43-44)");
   	  czMap.put("46","　　加：期初末缴所得税额");
   	  czMap.put("47","　　减：实际已缴纳所得税额");
   	  czMap.put("48","十二、期末应补(退)所得税额");
   	  
   	  czMap.put("49","补充资料：1.年平均职工人数（人）");
   	  czMap.put("50","　　2.工资总额（实发数）（元）");
   	  
   	  czMap.put("51","　　(1) 分配比例");
   	  czMap.put("52","　　(2) 分配比例");
   	  czMap.put("53","　　(3) 分配比例");
   	  czMap.put("54","　　(4) 分配比例");

   }
   
   public static CzzsndMapConstant getInst(){   	
   	if (inst==null){
   		inst=new CzzsndMapConstant();
   	}
   	return inst;
   }
   public String getCzmc(String hc){
   	return (String)czMap.get(hc);
   }
   
}
