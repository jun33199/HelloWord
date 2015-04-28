//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\hdzsgrsds\\web\\HdzsgrsdsForm.java

package com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.form.BaseForm;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzsqysb;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * �˶����ո��˶��ʸ��˺ϻ��������˰�걨��
 * _Ӧ˰������
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class HdzsgrsdsZslForm extends SbzlBaseForm
{

    /**
     * ����������	Date	N	ϵͳ����ó�
     * String
     */
    private String skssksrq;

    /**
     * ��������ֹ	Date	N	ϵͳ����ó�
     * String
     */
    private String skssjsrq;

    /**
     * �걨����	Date	N	������������ǰ����
     * String
     */
    private String sbrq;

    /**
     * ˰����������	VARCHAR2(8)	N	�ӵ�¼������ȡ��
     * String
     */
    private String jsjdm;

    /**
     * ��λ����	VARCHAR2(400)	N	���ݼ��������ӵǼ�������ȡ��
     * String
     */
    private String nsrmc;

    /**
     * �����ܶ�	Number(15��2)	Y	¼�룬����������
     * String
     * 	����û�����'�����ܶ�'��ϵͳ���ݼ��㹫ʽ����������ܶӦ��˰���ö
     * 	Ӧ��˰�� ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String qysrze;

    /**
     * Ӧ˰������	Number(10��5)	N
     * 	���ݼ���������˰�ֹ���ĺ˶��в�ѯ�������ɱ༭
     * String
     */
    private String yssdl;

    /**
     * �����ܶ�	Number(15��2)	Y	¼�룬����������
     * String
     *  	�����ܶ�����ܶ��Ӧ˰�����ʡ�
     */
    private String lrze;

    /**
     * Ͷ��������	VARCHAR2(30)	N
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     * String[]
     */
    private String[] tzzxm;

    /**
     * Ͷ�������֤������	VARCHAR2(18)	N
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     * String[]
     */
    private String[] tzzsfzjhm;

    /**
     * ����������	Number(10��5)	N
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     * String[]
     */
    private String[] lrfpbl;

    /**
     * Ӧ��˰���ö�	Number(15��2)	N
     * ���������ܶ���������������㣬���Ҳ��ɱ༭
     * String[]
     *  	Ӧ��˰���ö�����ܶ� �� ������������
     */
    private String[] ynssde;

    /**
     * ����˰��	Number(10��5)	N
     * ���ݼ���������˰�ֹ���ĺ˶��в�ѯ�������ɱ༭
     * String[]
     */
    private String[] sysl;

    /**
     * ����۳���	Number(15��2)	N
     * ����Ӧ��˰���ö����"˰�ʱ�"�õ�
     * String[]
     */
    private String[] sskcs;

    /**
     * Ӧ��˰��	Number(15��2)	N	����Ӧ��˰���ö���㣬���Ҳ��ɱ༭
     * String[]
     *  	Ӧ��˰�Ӧ��˰���ö������˰�ʣ�����۳�����
     */
    private String[] ynse;

    /**
     * ����˰��	Number(15��2)	N	¼�룬�����Ǵ��ڵ���0������
     * String[]
     * 	����û�����ĳһͶ���ߵ�'����˰��'��'�ڳ�δ��˰��'��'�ѽ���˰��'��
     * 	ϵͳ���ݼ��㹫ʽ�������Ͷ���ߵ�ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String[] jmse;

    /**
     * �ڳ�δ��˰��	Number(15��2)	N	¼�룬�����Ǵ��ڵ���0������
     * String[]
     * 	����û�����ĳһͶ���ߵ�'����˰��'��'�ڳ�δ��˰��'��'�ѽ���˰��'��
     * 	ϵͳ���ݼ��㹫ʽ�������Ͷ���ߵ�ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String[] qcwjsk;

    /**
     * �ѽ���˰��	Number(15��2)	N	¼�룬�����Ǵ��ڵ���0������
     * String[]
     * 	����û�����ĳһͶ���ߵ�'����˰��'��'�ڳ�δ��˰��'��'�ѽ���˰��'��
     * 	ϵͳ���ݼ��㹫ʽ�������Ͷ���ߵ�ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String[] yjnse;

    /**
     * ʵ��Ӧ��˰��	Number(15��2)	Y	��Ӧ��˰�����˰�
     * �ڳ�δ��˰����ѽ���˰����㹫ʽ����� �����޸ģ�Ӧ���ڵ���0��
     * String[]
     *  	ʵ��Ӧ��˰��=Ӧ��˰�����˰��ڳ�δ��˰��ѽ���˰�
     */
    private String[] sjyjse;

    /**
     * �ϼ�	Number(15��2)	N	ʵ�ʽ�˰��ĺϼ����������޸�
     * String
     *  	�ϼƣ���ʵ��Ӧ��˰�
     */
    private String hj;

    /**
     * ��ҵ�걨����
     * �˶�������ҵ�걨���� ֵ����
     */
    private Hdzsqysb qysbsj;

    /**
     * Ͷ�����걨����
     * List�����ݣ�����Ķ����Ǻ�Ͷ������Ŀ��ȵ� �˶�����Ͷ�����걨���� ֵ����
     */
    private List tzzsbsjList;

    /**
     * ��������
     * List�����ݣ�����Ķ����� �������� ֵ����
     */
    private List jmsjList;

    /**
     * ˰�ʱ�����
     * List�����ݣ�����Ķ����� ˰�ʱ����� ֵ����
     */
    private List slbsjList;

    /**
     * ����ʱ��
     * String
     */
    private String cjsj;

    /**
     * ¼������
     * String
     */
    private String lrrq;

    /**
     * ˰��Ǽ�֤��
     * String
     */
    private String swdjzh;

    /**
     * ˰�������֯��������
     * String
     */
    private String swjgzzjgdm;

    /**
     * ֤�����ʹ���
     */
    private String[] zjlxdm;

    /**
     * ֤������
     */
    private String[] zjhm;

    /**
     * ��ҵ���շ�ʽ���ݽṹ
     */
    private Grzsfs grzsfs;

    /**
     * �Ƿ���������������������
     */
    private boolean done = true;

    /**
     * �Ƿ���Ȩ�������걨
     */

    /**
     * ��data��ʹ��keyΪHdzsgrsdsMapConstant.QYSBSJȡ��ҵ�걨���ݷŵ�form��qysbsj�У�
     * ʹ��keyΪHdzsgrsdsMapConstant.LIST_TZZSBSJȡͶ�����걨���ݷŵ�form��tzzsbsjList�У�
     * ����˰�ѵĽӿ�ȡ�������ݷŵ�form��jmsjList�С�
     * @param jsjdm ���������
     * @param data ���ص�����
     * @throws BaseException
     */
    public void afterQuery(String jsjdm, Map data)
    {
        this.jmsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_JMSJ);
        this.qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
        this.tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);
    }

    /**
     * ����Map��
     * ʹ��keyΪHdzsgrsdsMapConstant.QYSBSJ����ҵ�걨����qysbsj��
     * ʹ��keyΪHdzsgrsdsMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList
     * ���ش�Map��
     * @param jsjdm ���������
     * @return Map
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     * ����Map��ʹ��keyΪHdzsgrsdsMapConstant.JSJDM�����������룬���ش�Map��
     * @param jsjdm ���������
     * @param tzfList Ͷ�ʷ�����
     * @param zsl ������
     * @return Map
     */
    public Map beforeQuery(String jsjdm, List tzfList, String zsl)
    {
        Map map = new HashMap(3);
        map.put(HdzsgrsdsMapConstant.JSJDM, jsjdm);
        map.put(HdzsgrsdsMapConstant.LIST_TZF, tzfList);
        map.put(HdzsgrsdsMapConstant.ZSL, zsl);
        return map;
    }

    /**
     * ����Map��
     * ʹ��keyΪHdzsgrsdsMapConstant.QYSBSJ����ҵ�걨����qysbsj��
     * ʹ��keyΪHdzsgrsdsMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList��
     * ���ش�Map��
     * @param jsjdm ���������
     * @return Map ������Ҫɾ����ֵ����
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(HdzsgrsdsMapConstant.QYSBSJ, this.qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     *
     * ������������˰�ʱ�����slbsjList�ͼ������ݣ�
     * ˰�ʱ�����ֻ��д˰�ʺ�˰��˰Ŀ������
     * �ռ�ҳ��������д��form�е�qysbsj��tzzsbsjList��
     * ���к˶�����Ͷ�����걨�����Ǹ���֤�����ʹ���ZJLXDM��֤������ZJHM��
     * ��ȷ���ض���ֵ����
     *
     * ���ɹ������£�
     *
     * ����˶�������ҵ�걨����ֵ����������
     * ��ҵ�����ܶ�	QYSRZE��form.srze��
     * �����ܶ�	LRZE��form.lrze��
     *
     * ����˶�����Ͷ�����걨����ֵ����������
     * Ӧ��˰���ö�	YNSSDE��form.ysssl[i]��
     * ����˰��	SYSL��form.sysl[i]��
     * ����۳���	SSKCS��form.sskcs[i]��
     * Ӧ��˰��	YNSE��form.ynse[i]��
     * ����˰��	JMSE��form.jmse[i]��
     * �ڳ�δ��˰��	QCWJSK��form.qcwjse[i]��
     * �ѽ���˰��	YJNSE��form.yjnse[i]��
     * ʵ��Ӧ��˰��	SJYJSE��form.sjyjse[i]��
     * @param jsjdm ���������
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    private void collect(String jsjdm) throws ApplicationException
    {
        String defaultString = "0.00";
        Date today = new Date();

        // ˰����������
        Map sksjrqMap = Skssrq.otherSkssrq(today);
        String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
        String quarter = Skssrq.preQuarter(today);

        //��ҵ�����ܶ�	QYSRZE
        BigDecimal qysrzeCount = new BigDecimal(this.qysrze);
        //Ӧ˰������	YSSDL
        BigDecimal yssdlCount = new BigDecimal(this.yssdl);
        //�����ܶ�	LRZE
        BigDecimal lrzeCount = qysrzeCount.multiply(yssdlCount).setScale(2,
            BigDecimal.ROUND_HALF_EVEN);

        if(lrzeCount.compareTo(new BigDecimal(this.lrze)) != 0)
        {
            throw new ApplicationException("ҳ��������");
        }

	// ʹ�ÿ��ŵļ�������룬���ֵ������ֵ
        this.qysbsj = newInstanceHdzsqysb(
            //���������	JSJDM
            jsjdm,
            //���	ND
            year,
            //����	JD
            quarter,
            //����ʱ��	CJSJ
            this.cjsj,
            //¼������	LRRQ
            this.lrrq,
            //˰��������ʼ����	SKSSKSRQ
            this.skssksrq,
            //˰��������������	SKSSJSRQ
            this.skssjsrq,
            //��˰������	NSRMC
            this.nsrmc,
            //˰��Ǽ�֤��	SWDJZH
            this.swdjzh,
            //��ҵ�����ܶ�	QYSRZE
            this.qysrze,
            //Ӧ˰������	YSSDL
            this.yssdl,
            //�����ܶ�	LRZE
            this.lrze,
            //˰�������֯��������	SWJGZZJGDM
            this.swjgzzjgdm,
            //¼���˴���	LRR
            jsjdm,
            //�걨����	SBRQ
            this.sbrq,
            //�Ǽ��걨��ʽ����	FSDM
            CodeConstant.FSDM_WSSB
            );
        // ʹ�ÿ��ŵ�����
        this.qysbsj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        this.qysbsj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        this.qysbsj.setLrrq(new Timestamp(today.getTime()));

	// �����ж��Ƿ�С��0�Ļ�׼ֵ
        BigDecimal zero = new BigDecimal(0);

        BigDecimal total = new BigDecimal(0);
        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        for(int i = 0, size = this.tzzxm.length; i < size; i++)
        {
            //�ڳ�δ��˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.qcwjsk[i])) > 0)
            {
                throw new ApplicationException("�ڳ�δ��˰��Ӧ�ô��ڵ���0��");
            }
            //����˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.jmse[i])) > 0)
            {
                throw new ApplicationException("����˰��Ӧ�ô��ڵ���0��");
            }
            //�ѽ���˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.yjnse[i])) > 0)
            {
                throw new ApplicationException("�ѽ���˰��Ӧ�ô��ڵ���0��");
            }
            //ʵ��Ӧ��˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.sjyjse[i])) > 0)
            {
                throw new ApplicationException("ʵ��Ӧ��˰��Ӧ�ô��ڵ���0��");
            }

            //�����߼��Ƿ���ȷ
//            BigDecimal fpblCount = new BigDecimal(this.lrfpbl[i]);
            //Ӧ��˰���ö�����ܶ� �� ����������
//            BigDecimal ynsdseCount = lrzeCount.multiply(fpblCount).divide(new
//                BigDecimal(100), BigDecimal.ROUND_HALF_EVEN).setScale(2,
//                BigDecimal.ROUND_HALF_EVEN);
//            total = total.add(fpblCount);
            //�������ܶ�ΪС��0��Ӧ��˰��Ϊ0
//            if(zero.compareTo(ynsdseCount) > 0)
//            {
//                ynsdseCount = new BigDecimal(0.00);
//            }
//            this.ynse[i] = ynsdseCount.toString();

            //ʵ��Ӧ��˰��=Ͷ����Ӧ��˰�����˰��ڳ�δ��˰��ѽ���˰��
//            ynsdseCount.subtract(new BigDecimal(this.jmse[i])).add(new
//            BigDecimal(this.qcwjsk[i])).subtract(new BigDecimal(this.yjnse[i]));
//            BigDecimal sjyjseCount = new BigDecimal(this.sjyjse[i]);
//            if(zero.compareTo(sjyjseCount) > 0)
//            {
//                this.sjyjse[i] = "0.00";
//            }

            // ʹ�ÿ��ŵļ�������룬���ֵ������ֵ
            Hdzstzzsb hdzstzzsb = newInstanceHdzstzzsb(
                //���������	JSJDM
                jsjdm,
                //���	ND
                year,
                //����	JD
                quarter,
                //֤�����ʹ���	ZJLXDM
                this.zjlxdm[i],
                //֤������	ZJHM
                this.zjhm[i],
                //Ͷ��������	TZZXM
                this.tzzxm[i],
                //¼������	LRRQ
                this.lrrq,
                //����ʱ��	CJSJ
                this.cjsj,
                //�걨����	SBRQ
                this.sbrq,
                //����������	LRFPBL
                this.lrfpbl[i],
                //Ӧ��˰���ö�	YNSSDE
                null, //this.ynssde[i],
                //����˰��	SYSL
                null, //this.sysl[i],
                //����۳���	SSKCS
                null, //this.sskcs[i],
                //Ӧ��˰��	YNSE
                this.ynse[i],
                //����˰��	JMSE
                this.jmse[i],
                //�ڳ�δ��˰��	QCWJSK
                this.qcwjsk[i],
                //�ѽ���˰��	YJNSE
                this.yjnse[i],
                //ʵ��Ӧ��˰��	SJYJSE
                this.sjyjse[i],
                //˰�������֯��������	SWJGZZJGDM
		this.swjgzzjgdm
                );
            tzzsbsjList.add(hdzstzzsb);
        }
//        if(total.compareTo(new BigDecimal(100)) != 0)
//        {
//            throw new ApplicationException("ȫ��Ͷ���ߵķ�������Ͳ�Ϊ100��");
//        }
    }

    /**
     *   �˶�������ҵ�걨����
     * @param jsjdm ���������
     * @param nd ���
     * @param jd ����
     * @param cjsj  ����ʱ��
     * @param lrrq ¼������
     * @param skssksrq ˰��������ʼ����
     * @param skssjsrq ˰��������������
     * @param nsrmc ��˰������
     * @param swdjzh ˰��Ǽ�֤��
     * @param qysrze ��ҵ�����ܶ�
     * @param yssdl Ӧ˰������
     * @param lrze �����ܶ�
     * @param swjgzzjgdm ˰�������֯��������
     * @param lrr ¼���˴���
     * @param sbrq �걨����
     * @param fsdm �Ǽ��걨��ʽ����
     * @return  Hdzsqysb �˶�������ҵ�걨����
     */
    public Hdzsqysb newInstanceHdzsqysb(
        String jsjdm,
        String nd,
        String jd,
        String cjsj,
        String lrrq,
        String skssksrq,
        String skssjsrq,
        String nsrmc,
        String swdjzh,
        String qysrze,
        String yssdl,
        String lrze,
        String swjgzzjgdm,
        String lrr,
        String sbrq,
        String fsdm
        )
    {
        Hdzsqysb hdzsqysb = new Hdzsqysb();
        hdzsqysb.setJsjdm(jsjdm);
        hdzsqysb.setNd(nd);
        hdzsqysb.setJd(jd);
        hdzsqysb.setCjsj(getTimestamp(cjsj));
        hdzsqysb.setLrrq(getTimestamp(lrrq));
        hdzsqysb.setSkssksrq(getTimestamp(skssksrq));
        hdzsqysb.setSkssjsrq(getTimestamp(skssjsrq));
        hdzsqysb.setNsrmc(nsrmc);
        hdzsqysb.setSwdjzh(swdjzh);
        hdzsqysb.setQysrze(new BigDecimal(qysrze));
        hdzsqysb.setYssdl(new BigDecimal(yssdl));
        hdzsqysb.setLrze(new BigDecimal(lrze));
        hdzsqysb.setSwjgzzjgdm(swjgzzjgdm);
        hdzsqysb.setLrr(lrr);
        hdzsqysb.setSbrq(TinyTools.second2Day(getTimestamp(sbrq)));
        hdzsqysb.setFsdm(fsdm);
        return hdzsqysb;
    }

    /**
     * ���ַ��͵��걨����ת����ʱ�����
     * @param sbrq �걨����
     * @return Timestamp �걨����
     */
    private Timestamp getTimestamp(String sbrq)
    {
        return new Timestamp(TinyTools.stringToDate(sbrq).getTime());
    }

    /**
     *  �˶�����Ͷ�����걨����
     * @param jsjdm ���������
     * @param nd ���
     * @param jd ����
     * @param zjlxdm ֤�����ʹ���
     * @param zjhm ֤������
     * @param tzzxm Ͷ��������
     * @param lrrq  ¼������
     * @param cjsj ����ʱ��
     * @param sbrq �걨����
     * @param lrfpbl ����������
     * @param ynssde Ӧ��˰���ö�
     * @param sysl ����˰��
     * @param sskcs ����۳���
     * @param ynse Ӧ��˰��
     * @param jmse ����˰��
     * @param qcwjsk �ڳ�δ��˰��
     * @param yjnse �ѽ���˰��
     * @param sjyjse ʵ��Ӧ��˰��
     * @return  �˶�����Ͷ�����걨����
     * @param swjgzzjgdm ˰�������֯��������
     */
    public Hdzstzzsb newInstanceHdzstzzsb(
        String jsjdm,
        String nd,
        String jd,
        String zjlxdm,
        String zjhm,
        String tzzxm,
        String lrrq,
        String cjsj,
        String sbrq,
        String lrfpbl,
        String ynssde,
        String sysl,
        String sskcs,
        String ynse,
        String jmse,
        String qcwjsk,
        String yjnse,
        String sjyjse,
        String swjgzzjgdm
        )
    {
        Hdzstzzsb hdzstzzsb = new Hdzstzzsb();
        hdzstzzsb.setJsjdm(jsjdm);
        hdzstzzsb.setNd(nd);
        hdzstzzsb.setJd(jd);
        hdzstzzsb.setZjlxdm(zjlxdm);
        hdzstzzsb.setZjhm(zjhm);
        hdzstzzsb.setTzzxm(tzzxm);
        hdzstzzsb.setLrrq(getTimestamp(lrrq));
        hdzstzzsb.setCjsj(getTimestamp(cjsj));
        hdzstzzsb.setSbrq(TinyTools.second2Day(getTimestamp(sbrq)));
        hdzstzzsb.setLrfpbl(new BigDecimal(lrfpbl));
        hdzstzzsb.setYnssde(new BigDecimal(0));
        hdzstzzsb.setSysl(new BigDecimal(0));
        hdzstzzsb.setSskcs(new BigDecimal(0));
        hdzstzzsb.setYnse(new BigDecimal(ynse));
        hdzstzzsb.setJmse(new BigDecimal(jmse));
        hdzstzzsb.setQcwjsk(new BigDecimal(qcwjsk));
        hdzstzzsb.setYjnse(new BigDecimal(yjnse));
        hdzstzzsb.setSjyjse(new BigDecimal(sjyjse));
        hdzstzzsb.setSwjgzzjgdm(swjgzzjgdm);
        return hdzstzzsb;
    }

    // getter & setter
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public List getJmsjList()
    {
        return jmsjList;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setLrfpbl(String[] lrfpbl)
    {
        this.lrfpbl = lrfpbl;
    }

    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }

    public void setQcwjsk(String[] qcwjsk)
    {
        this.qcwjsk = qcwjsk;
    }

    public Hdzsqysb getQysbsj()
    {
        return qysbsj;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSjyjse(String[] sjyjse)
    {
        this.sjyjse = sjyjse;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public void setQysrze(String qysrze)
    {
        this.qysrze = qysrze;
    }

    public void setSskcs(String[] sskcs)
    {
        this.sskcs = sskcs;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSysl(String[] sysl)
    {
        this.sysl = sysl;
    }

    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public void setTzzsfzjhm(String[] tzzsfzjhm)
    {
        this.tzzsfzjhm = tzzsfzjhm;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setYjnse(String[] yjnse)
    {
        this.yjnse = yjnse;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public void setYnssde(String[] ynssde)
    {
        this.ynssde = ynssde;
    }

    public void setYssdl(String yssdl)
    {
        this.yssdl = yssdl;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getSwdjzh()
    {
        return swdjzh;
    }

    public void setSwdjzh(String swdjzh)
    {
        this.swdjzh = swdjzh;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm(String[] zjhm)
    {
        this.zjhm = zjhm;
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