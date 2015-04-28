package com.ttsoft.bjtax.shenbao.sbzl.wqyys.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.math.BigDecimal;
import javax.servlet.http.HttpSession;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * ����Ӫҵ˰�걨form��ͬʱ֧�ְ�ʵ�걨���˶����ա����ѻ������ַ�ʽ
 * ������ַ�����ʽ����
 * @author Haifeng Su
 * @version 1.15
 */
public class WqyysForm extends SbzlBaseForm
{

    /**
     * ������������
     */
//    private String processor;

    /**
     * session�ľ��
     */

    /**
     * ��˰��ʽ����
     */
    private String zsffdm;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     * ˰��˰Ŀ
     */
    private List szsmList;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ˰��˰Ŀ����
     */
    private String[] szsmdm;

    /**
     * ˰��˰Ŀ����
     */
    private String[] szsmmc;

    /**
     * ��˰���
     */
    private String[] jsje;

    /**
     * ˰��
     */
    private String[] sl;

    /**
     * Ӧ��˰��
     */
    private String[] ynse;

    /**
     * �ѽ���˰��
     */
    private String[] yjnse;

    /**
     * ����Ӧ��˰��
     */
    private String[] bqybse;

    /**
     * �����
     */
    private String[] sre;

    /**
     * ��ͬ�ɽ���
     */
    private String[] htcje;

    /**
     * Ӷ����
     */
    private String[] yjl;

    /**
     * �˶������
     */
    private String[] hdsre;

    /**
     * ����֧����
     */
    private String[] jfzce;

    /**
     * ���������
     */
    private String[] hssre;

    /**
     * ����Ӫҵ˰
     */
    private List wqyysList;

    /**
     * ����Ӫҵ˰ģ��
     */
    private Wqyys wqyysTemplate;

    /**
     * �Ǽǳ���
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * ˰�ִ���
     */
    private static final String SZDM = "02";

    /**
     * ˰�ִ��봦������
     */
    private static final String SZDMEND1 = "91";

    /**
     * ˰�ִ��봦������
     */
    private static final String SZDMEND2 = "92";

    /**
     * �걨���ݳ���
     */
    public static final String SHENBAO = "SHENBAO";

    /**
     * ������Ϣ����
     */
    public static final String JBXX = "JBXX";

    /**
     * ԭʼ��˰��˰Ŀ
     */
    private List szsmOriginalList;

    /**
     * �Ƿ���������������������
     */
    private boolean done = true;

    public WqyysForm()
    {
//        this.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
    }

    /**
     * ����ҳ���������˰��˰Ŀ
     * @param szsmList ˰��˰Ŀ
     */
    public void setSzsmList(List szsmList)
    {
        // ʹ�ø��ƻ��Ʊ���ԭʼ����
        this.szsmOriginalList = szsmList;
        this.szsmList = new ArrayList();
        // �����Ŀո��壬ȫ��
        String oneSpace = "��";
        String twoSpace = "����";
        String threeSpace = "������";
        String fourSpace = "��������";
        for(int i = 0, size = szsmList.size(); i < size; i++)
        {
            Szsm szsmSource = (Szsm)szsmList.get(i);
            Szsm szsm = new Szsm();
            szsm.setSl(szsmSource.getSl());
            szsm.setSzsmdm(szsmSource.getSzsmdm());
            szsm.setSzsmmc(szsmSource.getSzsmmc());
            String szsmdm = szsm.getSzsmdm();
            // �����Ա�˰�ִ��뿪ͷ
            if(szsmdm.startsWith(this.SZDM))
            {
                // ���˵�"Ӫҵ˰���ɽ�"��"Ӫҵ˰����"
                if(!szsmdm.endsWith(this.SZDMEND1) &&
                   !szsmdm.endsWith(this.SZDMEND2))
                {
                    switch (szsmdm.length() - 2) {
                        case 0:
                            // 2λ���ȵ�˰�ֲ����ı�
                            break;
                        case 1:
                            // 3λ���ȵ�˰��˰Ŀǰ���1���ո�
                            szsm.setSzsmmc(oneSpace + szsm.getSzsmmc());
                            break;
                        case 2:
                            // 4λ���ȵ�˰��˰Ŀǰ���2���ո�
                            szsm.setSzsmmc(twoSpace + szsm.getSzsmmc());
                            break;
                        case 3:
                            // 5λ���ȵ�˰��˰Ŀǰ���3���ո�
                            szsm.setSzsmmc(threeSpace + szsm.getSzsmmc());
                            break;
                        case 4:
                            // 6λ���ȵ�˰��˰Ŀǰ���4���ո�
                            szsm.setSzsmmc(fourSpace + szsm.getSzsmmc());
                            break;
                    }
                    this.szsmList.add(szsm);
                }
            }
        }
    }

    /**
     * @param data ���ص�����
     * @return Map
     */
    public Map afterQuery(Map data, SWDJJBSJ jbsj)
    {
        // ��������ģ��
        this.wqyysTemplate = makeTemplate(jbsj.getJsjdm(), jbsj.getNsrmc(),
                                          jbsj.getSwjgzzjgdm());
        // ����ҵ������
        this.wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);
        for(int i = 0, size = this.wqyysList.size(); i < size; i++)
        {
            Wqyys wqyys = (Wqyys)this.wqyysList.get(i);
            // ������˰������
            wqyys.setNsrmc(jbsj.getNsrmc());
        }
        Map saveData = new HashMap(2);
        saveData.put(this.SHENBAO, this.wqyysList);
        saveData.put(this.JBXX, this.wqyysTemplate);
        return saveData;
    }

    /**
     * @param jsjdm ���������
     * @return Map
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(1);
        map.put(WqyysMapConstant.LIST_WQYYS, this.wqyysList);
        return map;
    }

    /**
     * ����Map��ʹ��keyΪHdzsgrsdsMapConstant.JSJDM�����������룬���ش�Map��
     * @param jsjdm ���������
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(SWDJJBSJ jbsj) throws BaseException
    {
        // ���ò������ݵ�VOPackage
        Map map = new HashMap(3);

        Map sksjrqMap = Skssrq.monthSkssrq(new Date());
        Timestamp ksrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ);
        String skssksrq = TinyTools.Date2String(ksrq);

        map.put(WqyysMapConstant.JSJDM, jbsj.getJsjdm());
        map.put(WqyysMapConstant.SKSSKSRQ, skssksrq);
        map.put(WqyysMapConstant.DJJBSJ, jbsj);

        return map;
    }

    /**
     * @param jsjdm ���������
     * @return Map ������Ҫɾ����ֵ����
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(1);
        List list = new ArrayList(1);
        list.add(this.wqyysTemplate);
        map.put(WqyysMapConstant.LIST_WQYYS, list);
        return map;
    }

    /**
     * ����һ������Ӫҵ˰���걨����ģ��
     * @param jsjdm ���������
     * @param nsrmc ��˰������
     * @param swjgzzjgdm ˰�������֯��������
     * @return Wqyys ����Ӫҵ˰���걨����ģ��
     */
    private Wqyys makeTemplate(String jsjdm, String nsrmc, String swjgzzjgdm)
    {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        // ˰����������
        Map sksjrqMap = Skssrq.monthSkssrq(today);

        Wqyys wqyys = new Wqyys();
        //���������	JSJDM
        wqyys.setJsjdm(jsjdm);
        //˰��������ʼ����	SKSSKSRQ
        wqyys.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //˰��˰Ŀ����	SZSMDM
        //����ʱ��	CJSJ
        wqyys.setCjrq(now);
        //��˰������	NSRMC
        wqyys.setNsrmc(nsrmc);
        //˰��˰Ŀ����	SZSMMC
        //�걨����	SBRQ
        wqyys.setSbrq(TinyTools.second2Day(now));
        //˰��������������	SKSSJSRQ
        wqyys.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //��˰��������	ZSFFDM
        //��˰��������	ZSFFMC
        //�����	SRE
        //��ͬ�ɽ���	HTCJE
        //Ӷ����	YJL
        //�˶������	HDSRE
        //����֧����	JFZCE
        //���������	HSSRE
        //��˰���	JSJE
        //˰��	SL
        //Ӧ��˰��	YNSE
        //����˰��	YINSE
        //����Ӧ��˰��	BQYBSE
        //˰�������֯��������	SWJGZZJGDM
        wqyys.setSwjgzzjgdm(swjgzzjgdm);
        //¼���˴���	LRR
        wqyys.setLrr(jsjdm);
        //¼������	LRRQ
        wqyys.setLrrq(now);
        //�Ǽ��걨��ʽ����	FSDM
        wqyys.setFsdm(CodeConstant.FSDM_WSSB);
        //��� ND
        wqyys.setNd((String)sksjrqMap.get(Skssrq.SKSSRQ_ND));

        wqyys.setQxdm(swjgzzjgdm.substring(0, 2));

        return wqyys;
    }

    /**
     * ��˰��˰Ŀ�������˰��˰Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     * @return String ˰��˰Ŀ����
     */
    private Szsm find(String szsmdm)
    {
        Szsm szsm = null;
        // ��Ҫ��ԭʼ�Ĵ�����в�����Ϊ����ʹ�õ��Ѿ������ӹ���ǰ���пո�
        for(int i = 0, size = this.szsmOriginalList.size(); i < size; i++)
        {
            szsm = (Szsm)this.szsmOriginalList.get(i);
            if(szsm.getSzsmdm().equalsIgnoreCase(szsmdm))
            {
                // �ҵ�˰��˰Ŀ�����Ӧ��˰��˰Ŀ����
                break;
            }
        }
        return szsm;
    }

    /**
     * ��ҳ����������ֵ���󷵻�
     * @param jsjdm ���������
     * @throws ApplicationException
     */
    private void collect(String jsjdm) throws ApplicationException
    {
        int size = 0;
        if(this.bqybse != null)
        {
            size = this.bqybse.length;
        }

        this.wqyysList = new ArrayList(size);

        // 0��Ϊ��׼ֵ
        BigDecimal zero = new BigDecimal("0");

        for(int i = 0; i < size; i++)
        {
            // ȡ��һ������ģ��
            Wqyys wqyys = makeTemplate(jsjdm, this.nsrmc, this.swjgzzjgdm);
            // ���Ҷ�Ӧ��˰��˰Ŀ
            Szsm szsm = this.find(this.szsmdm[i]);
            //˰��˰Ŀ����	SZSMDM
            wqyys.setSzsmdm(this.szsmdm[i]);
            //��˰��������	ZSFFDM
            wqyys.setZsffdm(this.zsffdm);
            if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_JFHS))
            {
                // ���롰���ѻ��㡱���ж��߼�

                //����֧����	JFZCE
                wqyys.setJfzce(new BigDecimal(this.jfzce[i]));
                //���������	HSSRE
                wqyys.setHssre(new BigDecimal(this.hssre[i]));
                //������0
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
                wqyys.setSre(zero);
            }
            else if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_HDZS))
            {
                // ���롰�˶����ա����ж��߼�

                //��ͬ�ɽ���	HTCJE
                wqyys.setHtcje(new BigDecimal(this.htcje[i]));
                //Ӷ����	YJL
                wqyys.setYjl(new BigDecimal(this.yjl[i]));
                //�˶������	HDSRE
                wqyys.setHdsre(new BigDecimal(this.hdsre[i]));
                //������0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setSre(zero);
            }
            else if(this.zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_ASSB))
            {
                // ���롰��ʵ�걨�����ж��߼�

                //�����	SRE
                wqyys.setSre(new BigDecimal(this.sre[i]));
                //������0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
            }
            else
            {
                // û��������շ�ʽ
                throw new ApplicationException("����ʧ�ܣ�");
            }

            BigDecimal jsjeWrapper = new BigDecimal(this.jsje[i]);

            BigDecimal slWrapper = szsm.getSl();
            // Ӧ��˰�� = ��˰��� * ˰��
            BigDecimal ynseWrapper = jsjeWrapper.multiply(slWrapper).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
            // �ѽ���˰��
            BigDecimal yjnseWrapper = new BigDecimal(this.yjnse[i]);
            // ����Ӧ��˰�� = Ӧ��˰�� - �ѽ���˰��
            BigDecimal bqybseWrapper =
                ynseWrapper.subtract(yjnseWrapper).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            wqyys.setJsje(jsjeWrapper);  //��˰���
            wqyys.setSl(slWrapper);  //˰��
            wqyys.setYnse(ynseWrapper);  //Ӧ��˰��
            wqyys.setYinse(yjnseWrapper);  //����˰��
            wqyys.setBqybse(bqybseWrapper);  //����Ӧ��˰��

            this.wqyysList.add(wqyys);
        }
    }

//    /**
//     * ȡ�ô�����������
//     * @return String ������������
//     */
//    public String getProcessor()
//    {
//        return processor;
//    }
//
//    /**
//     * ���ô�����������
//     * @param processor ������������
//     */
//    public void setProcessor(String processor)
//    {
//        this.processor = processor;
//    }

    // getter & setter
    public void setZsffdm(String zsffdm)
    {
        this.zsffdm = zsffdm;
    }

    public List getSzsmList()
    {
        return szsmList;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public void setSl(String[] sl)
    {
        this.sl = sl;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public void setYjnse(String[] yjnse)
    {
        this.yjnse = yjnse;
    }

    public void setBqybse(String[] bqybse)
    {
        this.bqybse = bqybse;
    }

    public void setSre(String[] sre)
    {
        this.sre = sre;
    }

    public void setHtcje(String[] htcje)
    {
        this.htcje = htcje;
    }

    public void setYjl(String[] yjl)
    {
        this.yjl = yjl;
    }

    public void setHdsre(String[] hdsre)
    {
        this.hdsre = hdsre;
    }

    public void setJfzce(String[] jfzce)
    {
        this.jfzce = jfzce;
    }

    public void setHssre(String[] hssre)
    {
        this.hssre = hssre;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public List getWqyysList()
    {
        return wqyysList;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setSzsmmc(String[] szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public Wqyys getWqyysTemplate()
    {
        return wqyysTemplate;
    }

  public void setWqyysTemplate(Wqyys wqyysTemplate) {
    this.wqyysTemplate = wqyysTemplate;
  }
    public boolean isDone()
    {
        return done;
    }
    public void setDone(boolean done)
    {
        this.done = done;
    }

}