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
 * <p>Title: ˰�����ⲿ�ӿ�ҵ��������</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.1
 */

public class SKHAdaptor {

  public static String YHKK_RESULT_SUCCESS="1";//�ۿ��gon

  public static String YHKK_RESULT_LOST="2";//�ۿ�ʧ��

  public static String YHKK_RESULT_NOREASON="3";//�ۿ�ʧ��δ֪ԭ��
  
  public static String YHKK_KEY_RESULT="RESULT";//�ۿ��ִMapKey 
  
  public static String YHKK_KEY_ADDWORD="ADDWORD";//�ۿ��ִMapKey

  public SKHAdaptor() {
  }

  /**
   * �ۿ�ӿ�
   * @param yhdm ���д���
   * @param zh �����˻�
   * @param qxdm ���ش���
   * @param je �ܽ��
   * @param ypdsJkss һƱ��˰�ɿ���List
   * @param ud �û�����
   * @return �ɹ����
   * @throws java.lang.Exception �쳣
   */
  public Object transferMoneyFromNsrZhToGk(String qxdm,
                                         double je, List ypdsJkss,String swjgzzjgdm,
                                         UserData ud,Sfxy sfxy) throws Exception {
    // ���Ӵ�����֣���С�����Ķ��� 000 ��ȥ��
    DecimalFormat df = new DecimalFormat("0.00");
    String msg = null;
    Map reObj=null;
    /**
        RealTimeKKRequestModel��ʵʱ�ۿ�������󣩲���Ϊ�գ��������Ի��Ӷ�������Ҫ�����£�
     1. swijgzzjgdm��˰�������֯�������룩String ������
     2.RealTimeKKRequestModel \uF0E0RealHeadModel(ʵʱҵ��ͷ)�Ӷ���
     1)	TaxOrgCode�����ջ��ش��룩 String ������ ���������ش���
     2)	entrustDate(ί������) Date����
     3)	traNo��������ˮ�ţ�String����
     3.TurnAccountModel(ת����Ϣ)�Ӷ���
     1)	�������
     2)	PayeeBankNo���տ����кţ�String���ش���
     3)	PayeeOrgCode���տλ���룩String���ش���
     4)	PayeeAcct���տ����˺ţ�String���ش���
     5)	PayeeName���տ������ƣ�String���ش���
     6)	PayBkCode���������кţ�String ��д˰����ⷽʽ�϶����е����д���
     7)	PayOpBkCode����������кţ�String��д˰����ⷽʽ�϶����е����д���
     4.PaymentModel(������Ϣ������������Ϣ��˰Ʊ��Ϣ)
     1)	PayAcct�������˻���String ������ ������������ί�нɿ�Э���顱����˰�˵Ľɿ�˺ţ�˰����ⷽʽ�϶����������ʻ���
     2)	HandOrgName���ɿλ���ƣ�String ������ ��������˰������
     3)	TraAmt�����׽�BigDecimal ������ ����������ʵʱ�ۿ���ܽ��
     4)	TaxVouNo��˰Ʊ���룩String ������ ���������ӽɿ�����ˮ�ţ����ջ���Ψһ��ʶһ���ɿ���ı�־��
     5)	BillDate����Ʊ���ڣ�Date ������ �������ɿ��鿪������ ���걨����
     6)	LimitDate���޽����ڣ������� �������ɿ����޽����� ���걨����
     7)	TaxPayCode����˰�˱��룩String ������ ������ ���������
     8)	TaxPayName����˰�����ƣ�String ������������ ��˰������
     9)	BudgetType��Ԥ�����ࣩString ������ ��д��1��
     10)TrimSign�������ڱ�־��String ������ ��д��0��
     11)CorpType����ҵע�����ͣ�String ������ �x��
     12)PrintVouSign����ӡ����ƾ֤��־��String ������ ��д��1��
     13)TaxTypeNum��˰��������int ������ ���������ɿ�������������˰�����������ջ�����д
     14)taxTypeList��˰����ϸ��������TaxTypeMxModel����List ������ ����������1����.N��˰����Ϣ
     15)TaxTypeMxModel�������£�
     16)ProjectId��˰����ţ�int ������ ������˰����ţ���СС��100��
     17)BudgetSubjectCode��Ԥ���Ŀ���룩String ������ ���������������ݸ���֪ͨ�����е�Ԥ���Ŀ���� �����걨�е�Ԥ���Ŀ����
     18)TaxTypeName��˰�����ƣ�String ������ ��������Ҫί�����д�ӡ����ƾ֤ʱ��� ˰������
     19)BudgetLevelCode��Ԥ�㼶�δ��룩String ������ ��ʱ��дΪ��1��
     20)BudgetLevelName��Ԥ�㼶�����ƣ�String ������ ��ʱ��дΪ�����롱
     21)TaxStartDate��˰���������ڣ�Date ������ �������ɿ���˰������������
     22)TaxEndDate��˰����������ֹ��Date ������ �������ɿ���˰����������ֹ��
     23)TaxType��˰�����ͣ�String ������ ����������¼�����
     24)TaxTypeAmt��˰�ֽ�BigDecimal ������ ��������ǰ˰������˰����
     25)DetailNum����ϸ������int ������ ��������������˰Ŀ����
     26)taxSubjectList��˰Ŀ��ϸ�����е�Object��TaxSubjectModel�������� ����������1����N��˰Ŀ��Ϣ
     27)TaxSubjectModel�������£�
     28)DetailNo����ϸ��ţ�int ������ ������˰Ŀ���
     29)TaxSubjectCode��˰Ŀ���룩String ������ ������˰Ŀ����
     30)TaxSubjectName��˰Ŀ���ƣ�String ������ ������˰Ŀ����
     31)TaxNumber����˰������int ������ ������
     32)TaxAmt����˰��BigDecimal ������ ������
     33)TaxRate��˰�ʣ�BigDecimal ������ ������С��1��
     34)ExpTaxAmt��Ӧ��˰�BigDecimal ������
     35)DiscountTaxAmt���۳��BigDecimal ������ �0��
     36)FactTaxAmt��ʵ��˰�BigDecimal ������
     */
    for (int i = 0; i < ypdsJkss.size(); i++) {
      YPDSJKS jks = (YPDSJKS) ypdsJkss.get(i);
      jks.printContent();
      //1.����ʵʱ�ۿ����������
      RealTimeKKRequestModel rkkrm = new RealTimeKKRequestModel();
      ///1.0.�������ش���
      rkkrm.setSwijgzzjgdm(swjgzzjgdm);
      //log("rkkrm.getSwijgzzjgdm()=" + rkkrm.getSwijgzzjgdm());
      ///1.1.���ɱ���ͷ����
      RealHeadModel rhm = new RealHeadModel();
      rkkrm.setRealHeadModel(rhm);
      rhm.setTaxOrgCode(qxdm);
      //log("rhm.getTaxOrgCode()=" + rhm.getTaxOrgCode());
      ///1.2.����(ת����Ϣ)�Ӷ���
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
      ///1.3.���ɻ�����Ϣ������������Ϣ��˰Ʊ��Ϣ
      PaymentModel pm = new PaymentModel();
      rkkrm.setPaymentModel(pm);
      ////1.3.0.��������Ϣ
      pm.setPayAcct(sfxy.getZh()); //�������˻���String ������ ������������ί�нɿ�Э���顱����˰�˵Ľɿ�˺ţ�˰����ⷽʽ�϶����������ʻ���
      //log("pm.getPayAcct()=" + pm.getPayAcct());
      pm.setHandOrgName(sfxy.getHandOrgName()); //���ɿλ���ƣ�String ������ ��������˰������
      //log("pm.getHandOrgName()=" + pm.getHandOrgName());
      pm.setTraAmt(new BigDecimal(df.format(Double.parseDouble(jks.getSjjexx())))); //�����׽�BigDecimal ������ ����������ʵʱ�ۿ���ܽ��
      //log("pm.getTraAmt()=" + pm.getTraAmt());
      pm.setTaxVouNo(jks.getSphm()); //��˰Ʊ���룩String ������ ���������ӽɿ�����ˮ�ţ����ջ���Ψһ��ʶһ���ɿ���ı�־��
      //log("pm.getTaxVouNo()=" + pm.getTaxVouNo());
      pm.setBillDate(DateUtilPro.getDate(jks.getSbrq(), "yyyyMMdd")); //����Ʊ���ڣ�Date ������ �������ɿ��鿪������ ���걨����
      //log("pm.getBillDate()=" + pm.getBillDate());
      pm.setLimitDate(DateUtilPro.getDate(jks.getSbrq(), "yyyyMMdd")); //���޽����ڣ������� �������ɿ����޽����� ���걨����
      //log("pm.getLimitDate()=" + pm.getLimitDate());
      pm.setTaxPayCode(jks.getJsjdm()); //����˰�˱��룩String ������ ������ ���������
      //log("pm.getTaxPayCode()=" + pm.getTaxPayCode());
      pm.setTaxPayName(jks.getNsrmc()); //����˰�����ƣ�String ������������ ��˰������
      //log("pm.getTaxPayName()=" + pm.getTaxPayName());
      pm.setBudgetType("1"); //��Ԥ�����ࣩString ������ ��д��1��
      //log("pm.getBudgetType()=" + pm.getBudgetType());
      pm.setTrimSign("0"); //�������ڱ�־��String ������ ��д��0��
      //log("pm.getTrimSign()=" + pm.getTrimSign());
      pm.setCorpType("x"); //����ҵע�����ͣ�String ������ �x��
      //log("pm.getCorpType()=" + pm.getCorpType());
      pm.setPrintVouSign("1"); //����ӡ����ƾ֤��־��String ������ ��д��1��
      //log("pm.getPrintVouSign()=" + pm.getPrintVouSign());
      pm.setTaxTypeNum(jks.getCurJksSzAndYskmCount()); //��˰�ֺ�Ԥ���Ŀ������int ������ ���������ɿ�������������˰�����������ջ�����д
      //log("pm.getTaxTypeNum()=" + pm.getTaxTypeNum());
      pm.setProtocolNo(sfxy.getXyshm()); //Э�����(���ջ��ء���ҵ���С���˰������ǩ����ί��Э�����)
      ////1.3.1.��д˰����ϸ
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
        //log("=================��˰��(" + szdm + ")������ϸ==================");
        tmpypysJksmxs = (List) ypysJksmxSplitBySzAndYskm.get(key); //����˰�����ֵ�һƱһ˰�ɿ�����ϸ��˰Ŀ��List
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
        //���ɵ�˰Ŀ����ϸ
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
          tsm.setDetailNo(j + 1); //����ϸ��ţ�int ������ ������˰Ŀ���
          //log("tsm.getDetailNo()=" + tsm.getDetailNo());
          tsm.setTaxSubjectCode( (String) szsm.get("szsmdm")); //��˰Ŀ���룩String ������ ������˰Ŀ����
          //log("tsm.getTaxSubjectCode()=" + tsm.getTaxSubjectCode());
          tsm.setTaxSubjectName( (String) szsm.get("szsmmc")); //��˰Ŀ���ƣ�String ������ ������˰Ŀ����
          //log("tsm.getTaxSubjectName()=" + tsm.getTaxSubjectName());
          String kssl = StringUtils.getPartBeforeSeparatorOfStr( (String) szsm.
              get("kssl"), ".");
          //String kssl=StringUtils
          if (szsm.get("kssl") != null) {
            tsm.setTaxNumber(Integer.parseInt(kssl)); //����˰������int ������ ������
          }
          else {
            tsm.setTaxNumber(0);
          }
          //log("tsm.getTaxNumber()=" + tsm.getTaxNumber());
          if (! ("".equals(StringUtils.killNull( (String) szsm.get("jsje"))))) {
            tsm.setTaxAmt(new BigDecimal( (String) szsm.get("jsje"))); //����˰��BigDecimal ������ ������
          }
          else {
            tsm.setTaxAmt(new BigDecimal(0));
          }
          //log("tsm.getTaxAmt()=" + tsm.getTaxAmt());
          if (! ("".equals(StringUtils.killNull( (String) szsm.get("sl"))))) {
            tsm.setTaxRate(new BigDecimal( (String) szsm.get("sl"))); //��˰�ʣ�BigDecimal ������ ������С��1��
          }
          else {
            tsm.setTaxRate(new BigDecimal(0));
          }
          //log("tsm.getTaxRate()=" + tsm.getTaxRate());
          tsm.setExpTaxAmt(new BigDecimal( (String) szsm.get("sjse"))); //��Ӧ��˰�BigDecimal ������
          //log("tsm.getExpTaxAmt()=" + tsm.getExpTaxAmt());
          tsm.setDiscountTaxAmt(new BigDecimal(0)); //���۳��BigDecimal ������ �0��
          //log("tsm.getDiscountTaxAmt()=" + tsm.getDiscountTaxAmt());
          tsm.setFactTaxAmt(new BigDecimal( (String) szsm.get("sjse"))); //��ʵ��˰�BigDecimal ������
          //log("tsm.getFactTaxAmt()=" + tsm.getFactTaxAmt());
          taxSubjectList.add(tsm);
        }
        ttmm.setProjectId(count + 1); //��˰����ţ�int ������ ������˰����ţ���СС��100��
        //log("ttmm.getProjectId()=" + ttmm.getProjectId());
        ttmm.setBudgetSubjectCode(yskmdm); //��Ԥ���Ŀ���룩String ������ ���������������ݸ���֪ͨ�����е�Ԥ���Ŀ���� �����걨�е�Ԥ���Ŀ����
        //log("ttmm.getBudgetSubjectCode()=" + ttmm.getBudgetSubjectCode());
        ttmm.setTaxTypeName(szmc); //��˰�����ƣ�String ������ ��������Ҫί�����д�ӡ����ƾ֤ʱ��� ˰������
        //log("ttmm.getTaxTypeName()=" + ttmm.getTaxTypeName());
        ttmm.setBudgetLevelCode(ysjcdm); //��Ԥ�㼶�δ��룩String ������ ��ʱ��дΪ��1��,���޸�Ϊϵͳ���ɵĴ���
        //log("ttmm.getBudgetLevelCode=" + ttmm.getBudgetLevelCode());
        ttmm.setBudgetLevelName(ysjcmc); //��Ԥ�㼶�����ƣ�String ������ ��ʱ��дΪ������,���޸�Ϊϵͳ���ɵ����ơ�
        //log("ttmm.getBudgetLevelName()=" + ttmm.getBudgetLevelName());
        ttmm.setTaxStartDate(DateUtilPro.getDate(skssksrq, "yyyyMMdd")); //��˰���������ڣ�Date ������ �������ɿ���˰������������
        //log("ttmm.getTaxStartDate()=" + ttmm.getTaxStartDate());
        ttmm.setTaxEndDate(DateUtilPro.getDate(skssjsrq, "yyyyMMdd")); //��˰����������ֹ��Date ������ �������ɿ���˰����������ֹ��
        //log("ttmm.getTaxEndDate()=" + ttmm.getTaxEndDate());
        ttmm.setTaxType(sklxdm); //��˰�����ͣ�String ������ ����������¼�����
        //log("ttmm.getTaxType()=" + ttmm.getTaxType());
        ttmm.setTaxTypeAmt(new BigDecimal(df.format(szje))); //��˰�ֽ�BigDecimal ������ ��������ǰ˰������˰����
        //log("ttmm.getTaxTypeAmt()=" + ttmm.getTaxTypeAmt());
        ttmm.setDetailNum(szsmcount + 1); //����ϸ������int ������ ��������������˰Ŀ����
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
      System.out.println("����Э������ʵʱ�ۿ�ķ�ʱ�� " + (System.currentTimeMillis()-start)+"���룡");
    }
    return reObj;
  }

  public Object gghsbSendYhkk(String qxdm, String nd, String yd, String bizph) throws
      Exception {
    //log("��ڲ���{qxdm:"+qxdm+"|nd:"+nd+"|yd:"+yd+"|bizPh:"+bizph+"}");
    long start = System.currentTimeMillis();
    //log("���������ۿʼ��");
    SkhSingleReturn ssr=null;
    //2013˰������Ŀɾ���ýӿڷ��������������걨�öδ���δʹ�ã����Ҵ��������ע�� zhangyj 20131205
    //SKHSjjhProxy.sendBatchKKRequestModel(nd,yd,qxdm,bizph);
    //log("���������ۿ������");
    System.out.println("���������ۿ�ķ�ʱ�� " + (System.currentTimeMillis() - start) + "���룡");
    return null;
  }

  private void log(Object str) {
    if (true) {
      System.out.println("[WSSB SKHAdaptor DEBUG " + (new Date()) + "]" + str);
    }
  }

}
