//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\web\\CzzsndForm.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbfpbl;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbqy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzmxsj;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import java.util.*;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * @author Haifeng Su
 * @version 1.0
 *
 * �������ո��˶��ʺͺϻ���ҵ����걨
 */
public class CzzsndForm extends SbzlBaseForm
{

    /**
     * ҳ��� ����������
     *
     * ���������걨������ ֵ����� ˰��������ʼ����SKSSKSRQ ����
     *
     * ���ɱ༭
     */
    private String ssrqq;

    /**
     * ҳ���Ͷ����֤�����ʹ��� ��������
     */
    private String[] zjlxdm;

    /**
     * ҳ��� ����ʱ�� ��������
     *
     * ���������걨������ ֵ����� ����ʱ�� ����
     */
    private String cjsj;

    /**
     * ҳ��� ��� (������)
     */
    private String nd;

    /**
     * ҳ��� ˰�������֯�������� (������)
     */
    private String swjgzzjgdm;

    /**
     * ¼���� (������)
     */
    private String lrr;

    /**
     * ¼������ (������)
     */
    private String lrrq;

    /**
     * ˰���������� String
     *
     * �ӵ�¼������ȡ��
     *
     * ���ɱ༭
     */
    private String jsjdm;

    /**
     * ҳ��� Ͷ��������
     *
     * ���������걨Ͷ�������� ֵ����� Ͷ��������TZZXM ����
     *
     * ���ɱ༭
     */
    private String tzzxm[];

    /**
     * ҳ��� Ͷ�������֤������
     *
     * ���������걨Ͷ�������� ֵ����� ֤������ZJHM ����
     *
     * ���ɱ༭
     */
    private String tzzsfzjhm[];

    /**
     * ����1 String[]
     *
     * �дδ�1��18�Ŀ�
     *
     * һ����ҵ�����ܶ�	¼�룬����Ϊ����	�д�1
     * �����ɱ�	¼�룬����Ϊ����	�д�2
     * ���á�˰��	¼�룬����Ϊ����	�д�3
     * Ӫҵ��֧��	¼�룬����Ϊ����	�д�4
     * ������ҵ�����ܶ�	����õ��������޸�	�д�5=1-2-3-4
     * ������˰�������Ӷ�	����õ��������޸�	�д�6= 7+19+30
     * 1.�����涨�����۳�����Ŀ	¼�룬����Ϊ����	�д�7
     * (1)��ҵ��Ա����֧��	¼�룬����Ϊ����	�д�8
     * (2)ְ��������	¼�룬����Ϊ����	�д�9
     * (3)ְ����������	¼�룬����Ϊ����	�д�10
     * (4)���ᾭ��	¼�룬����Ϊ����	�д�11
     * (5)��Ϣ֧��	¼�룬����Ϊ����	�д�12
     * (6)����	¼�룬����Ϊ����	�д�13
     * (7)ҵ���д���	¼�룬����Ϊ����	�д�14
     * (8)�����͹�����ҵ����	¼�룬����Ϊ����	�д�15
     * (9)��ȡ�۾ɷ�	¼�룬����Ϊ����	�д�16
     * (10)�����ʲ�̯��	¼�룬����Ϊ����	�д�17
     * (11)����	¼�룬����Ϊ����	�д�18
     */
    private String[] bl1;

    /**
     * ����2 String[]
     *
     * �дδ�19��36�Ŀ�
     *
     * 2.������۳�����Ŀ	����õ��������޸�	�д�19=20+21+22+23+24+25+26+27+28+29
     * (1)�ʱ���֧��	¼�룬����Ϊ����	�д�20
     * (2)�����ʲ����á�����֧��	¼�룬����Ϊ����	�д�21
     * (3)Υ����Ӫ����ͱ�û�ղ�����ʧ	¼�룬����Ϊ����	�д�22
     * (4)˰�����ɽ𡢷��𡢷���	¼�룬����Ϊ����	�д�23
     * (5)�ֺ��¹���ʧ�⳥	¼�룬����Ϊ����	�д�24
     * (6)�ǽ����͹�����ҵ���� 	¼�룬����Ϊ����	�д�25
     * (7)��������֧��	¼�룬����Ϊ����	�д�26
     * (8)����ĸ���׼����	¼�룬����Ϊ����	�д�27
     * (9)Ͷ���ߵĹ���	¼�룬����Ϊ����	�д�28
     * (10)�������޹ص�֧��	¼�룬����Ϊ����	�д�29
     * 3.Ӧ˰������Ŀ	¼�룬����Ϊ����	�д�30
     * (1)�ټ�Ӧ˰����	¼�룬����Ϊ����	�д�31
     * (2)δ��Ӧ˰����	¼�룬����Ϊ����	�д�32
     * �ġ���˰�������ٶ�	����õ��������޸�	�д�33= 34+35+36+37
     * 1.�ֲ�����	¼�룬����Ϊ����	�д�34
     * 2.����ȯ��Ϣ����	¼�룬����Ϊ����	�д�35
     * 3.Ͷ���߱�׼���ÿ۳���	¼�룬����Ϊ����	�д�36
     */
    private String[] bl2;

    /**
     * ����3 String[]
     *
     * �дδ�37��54�Ŀ�
     *
     * 4.����	¼�룬����Ϊ����	�д�37
     * �塢�������Ӧ��˰���ö�	����õ��������޸ģ����Ϊ��������Ϊ0�����Ժ���������
     * �����㣬�ɱ༭��	�д�38= 5+6-33
     * �����������	���ݼ��������ӵǼ����ݵ�Ͷ������Ϣ�д����������޸�	�д�39
     * �ߡ�Ͷ����Ӧ��˰���ö�	����õ��������޸ģ����Ϊ��������Ϊ0�����Ժ�����������
     * ���㣬�ɱ༭��	�д�40��38*39
     * �ˡ�����˰��	����Ӧ��˰���ö������˰�ʱ��õ��������޸ģ������Ͷ����Ӧ��˰��\uFFFD
     * �С�ڵ���0���򲻼�����	�д�41
     * �š�����۳���	����Ӧ��˰���ö������˰�ʱ�
     * �õ��������޸ģ������Ͷ����Ӧ��˰���öС�ڵ���0���򲻼�����	�д�42
     * ʮ��Ӧ������˰��	����õ��������޸ģ����Ϊ��������Ϊ0�����Ժ��������������㣬
     * �ɱ༭��	�д�43= 40*41-42
     * ��������������˰��	¼�룬����Ϊ���֣�����е��ڼ���������¼����ɱ༭�����򲻿�\uFFFD
     * ������	�д�44
     * ʮһ��Ӧ���������˰��	����õ��������޸ģ����Ϊ��������Ϊ0�����Ժ�����������
     * ���㣬�ɱ༭��	�д�45 = 43-44
     * �ӣ��ڳ�δ������˰��	¼�룬����Ϊ����	�д�46
     * ����ʵ���ѽ�������˰��	¼�룬����Ϊ����	�д�47
     * ʮ������ĩӦ��(��)����˰��	����õ��������޸ģ����Ϊ��������Ϊ0��	�д�48��45+46
     * -47
     * �������ϣ�1.��ƽ��ְ���������ˣ�	¼�룬����Ϊ����	�д�49
     * 2.�����ܶʵ��������Ԫ��	¼�룬����Ϊ����	�д�50
     * (1) ���������ռ���	¼�룬����Ϊ�Ǹ�����	�д�51
     * (2) ���������ռ���	¼�룬����Ϊ�Ǹ�����	�д�52
     * (3) ���������ռ���	¼�룬����Ϊ�Ǹ�����	�д�53
     * (4) ���������ռ���	¼�룬����Ϊ�Ǹ�����	�д�54
     */
    private String[] bl3;

    /**
     * ����4 String[]
     *
     * �дδ�51��54��С���
     *
     *
     * 3.��������ҵȡ�õ�������Ӫ����(1) �������
     * 	¼�룬����Ϊ���֣�Ӧ���ڵ���0С�ڵ���100	�д�51
     * (2) �������	¼�룬����Ϊ���֣�Ӧ���ڵ���0С�ڵ���100	�д�52
     * (3) ������� 	¼�룬����Ϊ���֣�Ӧ���ڵ���0С�ڵ���100	�д�53
     * (4) �������	¼�룬����Ϊ���֣�Ӧ���ڵ���0С�ڵ���100	�д�54
     */
    private String[] bl4;

    /**
     * ��ҵ�걨����
     *
     * List�����ݣ�����Ķ����� ���������걨��ҵ���� ֵ����
     */
    private List qysbsjList;

    /**
     * Ͷ�����걨����
     *
     * List�����ݣ�����Ķ����Ǻ�Ͷ������Ŀ��ȵ�  ���������걨Ͷ�������� ֵ����
     */
    private List tzzsbsjList;

    /**
     * Ͷ�����걨����
     *
     * List�����ݣ�����Ķ����Ǻ�Ͷ������Ŀ��ȵ�List���ڲ�List��
     * ����������ȷ���������� ֵ����
     */
    private List fpblsjList;

    /**
     * ˰�ʱ�����
     *
     * List�����ݣ�����Ķ����� ˰�ʱ����� ֵ����
     */
    private List slbsjList;

    /**
     * ��������
     *
     * List�����ݣ�����Ķ����� �������� ֵ����
     */
    private List jmsjList;

    /**
     * ���������걨������
     */
    private Czzsnbzb czzsnbzb;

    /**
     * ҳ��� �걨����
     *
     * ���������걨������ ֵ����� ¼������LRRQ ����
     */
    private String sbrq;

    /**
     * ҳ��� ��������ֹ
     *
     * ���������걨������ ֵ����� ˰��������������SKSSJSRQ ����
     */
    private String ssrqz;

    /**
     * ��������
     *
     */
    private String jmsj;

    /**
     * ҳ��� ����5
     *
     */
    private String[] bl5;

    /**
     * ҳ��� ����6
     */
    private String[] bl6;

    /**
     * ��������
     *
     */
    private String[] cwfzr;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * Ͷ������ϸ����
     */
    private List tzzsbsjmxList;

    /**
     * �Ƿ���������������������
     */
    private boolean done = false;

    /**
     * ��data��
     * ʹ��keyΪCzzsndMapConstant.CZZSNBZBȡ���������걨�����ݷŵ�czzsnbzb�У�
     * ʹ��keyΪCzzsndMapConstant.LIST_QYSBSJȡ��ҵ�걨���ݷŵ�qysbsjList�У�
     * ʹ��keyΪCzzsndMapConstant.LIST_TZZSBSJȡͶ�����걨���ݷŵ�form��tzzsbsjList�У�
     * ʹ��keyΪCzzsndMapConstant.LIST_FPBLSJȡͶ�������ݷŵ�form��tzzsjList�У�
     * ʹ��keyΪCzzsndMapConstant.LIST_JMSJȡ�������ݷŵ�form��jmsjList�У�
     * ʹ��keyΪCzzsndMapConstant.LIST_SLBSJȡ˰�ʱ����ݷŵ�slbsjList�С�
     * @param jsjdm ���������
     * @param data ���ص�����
     * @throws BaseException
     */
    public void afterQuery(String jsjdm, Map data) throws BaseException
    {
        this.czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
        this.qysbsjList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
        this.tzzsbsjList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
        this.fpblsjList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);
        this.tzzsbsjmxList = (List)data.get(CzzsndMapConstant.LIST_TZZMX);
        SWDJJBSJ swdjjbsj = (SWDJJBSJ)data.get(CzzsndMapConstant.JBSJ);
        this.nsrmc = swdjjbsj.getNsrmc();

        //����˰�ѹ���ӿ�ȡ�ü�������
        this.jmsjList = new ArrayList(this.tzzsbsjList.size());
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        for(int i = 0, size = this.tzzsbsjList.size(); i < size; i++)
        {
            Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj)this.tzzsbsjList.get(i);

            // ����ĵ�����Ϊʹ��ȱʡ�Ĺ������룬δ��ĳ��ʱ�����Ĺ�����
            // ���Ծ�ûд��CodeTableȥ��δ��ĳ��ʱ��������Ͷ����������ȡ��
            // ��һ�ֿ��ܣ���Czzsnbtzzsj��Ӧ�����ݿ��ṹ�Ķ���
            // ���ӹ��������ֶΣ����ԣ�������·��
            boolean result = false;
            try
            {
                Calendar cc = new GregorianCalendar();
                int year = cc.get(Calendar.YEAR) - 1;
                cc.set(year, Calendar.DECEMBER, 31);
                result = sfProxy.getZRRJm(czzsnbtzzsj.getZjlxdm(),
                                          czzsnbtzzsj.getZjhm(), "CHN",
                                          SzsmdmConstant.GRSDS, cc.getTime());
            }
            catch(Exception e)
            {
                throw ExceptionUtil.getBaseException(e);
            }
            this.jmsjList.add(result ? Boolean.TRUE : Boolean.FALSE);
        }
    }

    /**
     * ����Map��
     * ʹ��keyΪCzzsndMapConstant.CZZSNBZB�Ų��������걨������czzsnbzb��
     * ʹ��keyΪCzzsndMapConstant.LIST_QYSBSJ����ҵ�걨����qysbsjList��
     * ʹ��keyΪCzzsndMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList��
     * ʹ��keyΪCzzsndMapConstant.LIST_FPBLSJ�ŷ����������fpblsjList�����ش�Map��
     * @param jsjdm ���������
     * @return Map
     * @throws BaseException
     */
    public Map beforeSave(String jsjdm) throws BaseException
    {
        this.collect(jsjdm);
        Map retMap = new HashMap(5);
        retMap.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
        retMap.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        retMap.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZMX, this.tzzsbsjmxList);
        return retMap;
    }

    /**
     * ����Map��ʹ��keyΪCzzsndMapConstant.JSJDM�����������룬���ش�Map��
     * @param jsjdm ���������
     * @param tzfList Ͷ�ʷ�����
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(String jsjdm, List tzfList) throws BaseException
    {
        Map map = new HashMap(2);
        map.put(CzzsndMapConstant.JSJDM, jsjdm);
        map.put(CzzsndMapConstant.LIST_TZF, tzfList);
        return map;
    }

    /**
     * ����Map��
     * ʹ��keyΪCzzsndMapConstant.CZZSNBZB�Ų��������걨������czzsnbzb��
     * ʹ��keyΪCzzsndMapConstant.LIST_QYSBSJ����ҵ�걨����qysbsjList��
     * ʹ��keyΪCzzsndMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList��
     * ʹ��keyΪCzzsndMapConstant.LIST_FPBLSJ�ŷ����������fpblsjList��
     * ���ش�Map��
     * @param jsjdm ���������
     * @return Map
     * @throws BaseException
     */
    public Map beforeDelete(String jsjdm) throws BaseException
    {
        this.collect(jsjdm);
        Map retMap = new HashMap(5);
        retMap.put(CzzsndMapConstant.CZZSNBZB, this.czzsnbzb);
        retMap.put(CzzsndMapConstant.LIST_QYSBSJ, this.qysbsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        retMap.put(CzzsndMapConstant.LIST_FPBLSJ, this.fpblsjList);
        retMap.put(CzzsndMapConstant.LIST_TZZMX, this.tzzsbsjmxList);
        return retMap;
    }

    /**
     * �ռ�ҳ������ת����ֵ����ŵ� czzsnbzb��qysbsjList��jmsjList��
     *	tzzsbsjList��fpblsjList��tzzmxsjList�С�
     * @param jsjdm ���������
     */
    private void collect(String jsjdm)
    {
        Date today = new Date();
        Map sksjrqMap = Skssrq.yearSkssrq(today);
        String nd = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);

        // ��������List
        this.jmsjList = new ArrayList(1);
        this.jmsjList.add(new Boolean(this.jmsj));

        Timestamp now = new Timestamp(System.currentTimeMillis());

        // ���������������
        this.czzsnbzb = this.newInstanceCzzsnbzb(jsjdm, nd,
                                                 CodeConstant.FSDM_WSSB,
                                                 this.swjgzzjgdm,
                                                 this.lrr,
                                                 this.lrrq,
                                                 this.ssrqz,
                                                 this.ssrqq,
                                                 this.cjsj,
                                                 now);

        // ����Ͷ��������
        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        for(int i = 0; i < this.tzzxm.length; i++)
        {
            Czzsnbtzzsj czzsnbtzzsj = this.newInstanceTzzsbsj(jsjdm, nd,
                this.zjlxdm[i], this.tzzsfzjhm[i], this.cwfzr[i],
                this.swjgzzjgdm, now);
            this.tzzsbsjList.add(czzsnbtzzsj);
        }

        // ������ҵ����List
            //1-38��49-50
        this.qysbsjList = new ArrayList(40);
        for(int i = 0; i < this.bl1.length; i++)
        {
            Czzsnbqy czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                String.valueOf(i + 1), bl1[i], this.swjgzzjgdm, now);
            this.qysbsjList.add(czzsnbqy);
        }
        for(int i = 0; i < this.bl2.length; i++)
        {
            Czzsnbqy czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                String.valueOf(i + 1 + bl1.length), bl2[i], this.swjgzzjgdm, now);

            this.qysbsjList.add(czzsnbqy);
        }
        for(int i = 0; i < this.bl3.length; i++)
        {
            Czzsnbqy czzsnbqy = null;
            if(i < 2)
            {
                czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                    String.valueOf(i + 1 + bl1.length + bl2.length),
                                                  bl3[i], this.swjgzzjgdm, now);
            }
            else
            {
                czzsnbqy = this.newInstanceQysbsj(jsjdm, nd,
                    String.valueOf(i + 1 + bl1.length + bl2.length + 10),
                                                  bl3[i], this.swjgzzjgdm, now);
            }
            this.qysbsjList.add(czzsnbqy);
        }

    // ����Ͷ������ϸ����
            //39-48
        int tzzSize = this.tzzxm.length;
        this.tzzsbsjmxList = new ArrayList(tzzSize);
        for(int i = 0; i < tzzSize; i++)
        {
            List tzzList = new ArrayList(this.bl4.length / tzzSize);
            for(int j = 0; j < this.bl4.length / tzzSize; j++)
            {
                Czzsnbtzzmxsj czzsnbtzzmxsj = this.newCzzsnbtzzmxsj(jsjdm, nd,
                    this.zjlxdm[i], this.tzzsfzjhm[i],
                    String.valueOf(j + 39), bl4[tzzSize * j + i], this.swjgzzjgdm, now);
                tzzList.add(czzsnbtzzmxsj);
            }
            this.tzzsbsjmxList.add(tzzList);
        }

        // ���·����������
            //51-54
        this.fpblsjList = new ArrayList(4);
        for(int i = 0; i < 4; i++)
        {
            if(bl5[i] == null)
            {
                bl5[i] = "0.00";
            }
            if(bl6[i] == null)
            {
                bl6[i] = "0.00";
            }
            Czzsnbfpbl czzsnbfpbl = this.newInstanceFpbl(jsjdm, nd,
                String.valueOf(i + 51), bl5[i], bl6[i], this.swjgzzjgdm, now);
            this.fpblsjList.add(czzsnbfpbl);
        }
    }

    /**
     * �Ӵ������Ĳ�������һ�� ���������걨Ͷ�������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param zjlxdm ֤�����ʹ���
     * @param zjhm ֤������
     * @param hc �д�
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return List Ͷ��������
     */
    public Czzsnbtzzmxsj newCzzsnbtzzmxsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String hc, String bqljs,
                                           String swjgzzjgdm, Timestamp now)
    {
        Czzsnbtzzmxsj czzsnbtzzmxsj = new Czzsnbtzzmxsj();
        czzsnbtzzmxsj.setBqljs(new BigDecimal(bqljs));
        czzsnbtzzmxsj.setHc(hc);
        czzsnbtzzmxsj.setJsjdm(jsjdm);
        czzsnbtzzmxsj.setNd(nd);
        czzsnbtzzmxsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzmxsj.setZjhm(zjhm);
        czzsnbtzzmxsj.setZjlxdm(zjlxdm);
        czzsnbtzzmxsj.setCjrq(now);
        czzsnbtzzmxsj.setLrrq(now);
        czzsnbtzzmxsj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbtzzmxsj;
    }

    /**
     * �Ӵ������Ĳ�������һ�� ���������걨������ ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param fsdm ��ʽ����
     * @param swjgzzjgdm ���ش���
     * @param lrr ¼����
     * @param lrrq ¼������
     * @param ssrqz ��������ֹ
     * @param ssrqq ����������
     * @param strCjsj ����ʱ��
     * @return Czzsnbzb �걨����
     */
    public Czzsnbzb newInstanceCzzsnbzb(String jsjdm, String nd, String fsdm,
                                         String swjgzzjgdm, String lrr,
                                         String lrrq,
                                         String ssrqz, String ssrqq,
                                         String strCjsj,
                                         Timestamp now)
    {
        Czzsnbzb czzsnbzb = new Czzsnbzb();
        czzsnbzb.setJsjdm(jsjdm);
        czzsnbzb.setNd(nd);
        czzsnbzb.setFsdm(fsdm);
        czzsnbzb.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbzb.setLrr(lrr);
        czzsnbzb.setLrrq(now);
        czzsnbzb.setSbrq(TinyTools.second2Day(now));
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        czzsnbzb.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        czzsnbzb.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        Date cjsj = TinyTools.stringToDate(strCjsj);
        czzsnbzb.setCjrq(now);
        czzsnbzb.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbzb;
    }

    /**
     * �Ӵ������Ĳ�������һ�� ���������걨��ҵ���� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param hc �д�
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return Czzsnbqy �걨��ҵ
     */
    public Czzsnbqy newInstanceQysbsj(String jsjdm, String nd, String hc,
                                       String bqljs, String swjgzzjgdm, Timestamp now)
    {
        Czzsnbqy czzsnbqy = new Czzsnbqy();
        czzsnbqy.setJsjdm(jsjdm);
        czzsnbqy.setNd(nd);
        czzsnbqy.setHc(hc);
        czzsnbqy.setBqljs(new BigDecimal(bqljs));
        czzsnbqy.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbqy.setLrrq(now);
        czzsnbqy.setCjrq(now);
        czzsnbqy.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbqy;
    }

    /**
     * �Ӵ������Ĳ�������һ�� ���������걨Ͷ�������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param zjlxdm ֤�����ʹ���
     * @param zjhm ֤������
     * @param cwfzr ��������
     * @param swjgzzjgdm ���ش���
     * @return List Ͷ��������
     */
    public Czzsnbtzzsj newInstanceTzzsbsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String cwfzr, String swjgzzjgdm,
                                           Timestamp now)
    {
        Czzsnbtzzsj czzsnbtzzsj = new Czzsnbtzzsj();
        czzsnbtzzsj.setJsjdm(jsjdm);
        czzsnbtzzsj.setNd(nd);
        czzsnbtzzsj.setZjlxdm(zjlxdm);
        czzsnbtzzsj.setZjhm(zjhm);
        czzsnbtzzsj.setCwfzr(cwfzr);
        czzsnbtzzsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzsj.setQxdm(swjgzzjgdm.substring(0, 2));
        czzsnbtzzsj.setCjrq(now);
        czzsnbtzzsj.setLrrq(now);
        return czzsnbtzzsj;
    }

    /**
     * �Ӵ������Ĳ�������һ�� ����������ȷ���������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param hc �д�
     * @param bl ����
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return Czzsnbfpbl �������
     */
    public Czzsnbfpbl newInstanceFpbl(String jsjdm, String nd, String hc,
                                       String bl, String bqljs, String swjgzzjgdm,
                                       Timestamp now)
    {
        Czzsnbfpbl czzsnbfpbl = new Czzsnbfpbl();
        czzsnbfpbl.setJsjdm(jsjdm);
        czzsnbfpbl.setNd(nd);
        czzsnbfpbl.setHc(hc);
        czzsnbfpbl.setBl(new BigDecimal(bl));
        czzsnbfpbl.setBqljs(new BigDecimal(bqljs));
        czzsnbfpbl.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbfpbl.setLrrq(now);
        czzsnbfpbl.setCjrq(now);
        czzsnbfpbl.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbfpbl;
    }

    // getter & setter
    public void setBl1(String[] bl1)
    {
        this.bl1 = bl1;
    }

    public void setBl2(String[] bl2)
    {
        this.bl2 = bl2;
    }

    public void setBl3(String[] bl3)
    {
        this.bl3 = bl3;
    }

    public void setBl4(String[] bl4)
    {
        this.bl4 = bl4;
    }

    public Czzsnbzb getCzzsnbzb()
    {
        return czzsnbzb;
    }

    public List getFpblsjList()
    {
        return fpblsjList;
    }

    public List getJmsjList()
    {
        return jmsjList;
    }

    public void setJmsjList(List jmsjList)
    {
        this.jmsjList = jmsjList;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public List getQysbsjList()
    {
        return qysbsjList;
    }

    public void setQysbsjList(List qysbsjList)
    {
        this.qysbsjList = qysbsjList;
    }

    public String getSbrq()
    {
        return sbrq;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public String getSsrqq()
    {
        return ssrqq;
    }

    public void setSsrqq(String ssrqq)
    {
        this.ssrqq = ssrqq;
    }

    public String getSsrqz()
    {
        return ssrqz;
    }

    public void setSsrqz(String ssrqz)
    {
        this.ssrqz = ssrqz;
    }

    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public void setTzzsbsjList(List tzzsbsjList)
    {
        this.tzzsbsjList = tzzsbsjList;
    }

    public String[] getTzzsfzjhm()
    {
        return tzzsfzjhm;
    }

    public void setTzzsfzjhm(String[] tzzsfzjhm)
    {
        this.tzzsfzjhm = tzzsfzjhm;
    }

    public String[] getTzzxm()
    {
        return tzzxm;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setJmsj(String jmsj)
    {
        this.jmsj = jmsj;
    }

    public String getLrr()
    {
        return lrr;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public String getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String[] getZjlxdm()
    {
        return zjlxdm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }


    public void setBl5(String[] bl5)
    {
        this.bl5 = bl5;
    }

    public String[] getCwfzr()
    {
        return cwfzr;
    }

    public void setCwfzr(String[] cwfzr)
    {
        this.cwfzr = cwfzr;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setBl6(String[] bl6)
    {
        this.bl6 = bl6;
    }

    public List getTzzsbsjmxList()
    {
        return tzzsbsjmxList;
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