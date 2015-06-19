//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsjd\\web\\CzzsjdForm.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsjd.web;

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
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * �������ռ���
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class CzzsjdForm extends SbzlBaseForm
{

    /**
     * �걨���� String
     *
     * ���ɱ༭��ֵΪ��ǰ����
     */
    private String sbrq;

    /**
     * ˰���������� String
     *
     * �ӵ�¼������ȡ��
     */
    private String jsjdm;

    /**
     * ��λ���� String
     *
     * ���ݼ��������ӵǼ�������ȡ��
     */
    private String nsrmc;

    /**
     * �����ܶ� String
     *
     * ¼�룬����������
     *
     * ����û�����'�����ܶ�'��ϵͳ���ݼ��㹫ʽ�����Ӧ��˰���öӦ��˰�ʵ��Ӧ��˰
     * ��������ʵ��Ӧ��˰��ϼơ�
     *
     * �������ܶ�ΪС��0��
     * Ӧ��˰���ö�Ϊ�����ܶ������������
     * Ӧ��˰��Ϊ0
     * �ڳ�δ��˰����ѽ���˰��¼�루���ڵ���0��
     * ʵ��Ӧ��˰��ȱʡ��Ϊ0�������޸ģ����ڵ���0��
     */
    private String lrze;

    /**
     * ˰��������ʼ����
     *
     * ϵͳ����ó�
     */
    private String skssksrq;

    /**
     * ˰��������������
     *
     * ϵͳ����ó�
     */
    private String skssjsrq;

    /**
     * Ͷ�������� String[]
     *
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     */
    private String[] tzzxm;

    /**
     * Ͷ�������֤������ String[]
     *
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     */
    private String[] zjhm;

    /**
     * ���������� String[]
     *
     * ���ɱ༭�����ݼ���������Ͷ���������в�ѯ����
     */
    private String[] fpbl;

    /**
     * Ӧ��˰���ö� String[]
     *
     * ���������ܶ���������������㣬���Ҳ��ɱ༭
     *
     * Ӧ��˰���ö�=�����ܶ��������������
     *
     * ����Ӧ��˰���ö����"˰�ʱ�--���幤�̻�������Ӫ�ͳа������⾭Ӫ��������"�õ�˰��
     * ������۳���������Ӧ��˰�
     */
    private String[] ynssde;

    /**
     * Ӧ��˰�� String[]
     *
     * ����Ӧ��˰���ö���㣬���Ҳ��ɱ༭
     *
     * Ӧ��˰�Ӧ��˰���ö������˰�ʣ�����۳�����
     */
    private String[] ynsdse;

    /**
     * �ڳ�δ��˰�� String[]
     *
     * ¼�룬�����Ǵ��ڵ���0������
     *
     * ����û�����ĳһͶ���ߵ�'�ڳ�δ��˰��'��'�ѽ���˰��'��ϵͳ���ݼ��㹫ʽ�������Ͷ
     * ���ߵ�ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String[] qcwjsdse;

    /**
     * �ѽ���˰�� String[]
     *
     * ¼�룬�����Ǵ��ڵ���0������
     *
     * ����û�����ĳһͶ���ߵ�'�ڳ�δ��˰��'��'�ѽ���˰��'��ϵͳ���ݼ��㹫ʽ�������Ͷ
     * ���ߵ�ʵ��Ӧ��˰��������ʵ��Ӧ��˰��ϼơ�
     */
    private String[] yjnsdse;

    /**
     * ʵ��Ӧ��˰�� String[]
     *
     * ��Ӧ��˰��ڳ�δ��˰����ѽ���˰����㣬�����޸ģ�Ӧ���ڵ���0��
     *
     * ʵ��Ӧ��˰��=Ӧ��˰��ڳ�δ��˰��ѽ���˰�
     */
    private String[] sjyjse;

    /**
     * �ϼ� String
     *
     * ʵ�ʽ�˰��ĺϼ����������޸�
     *
     * �ϼƣ���ʵ�ʽ�˰�
     */
    private String hj;

    /**
     * ˰�ʱ�����
     *
     * List�����ݣ�����Ķ����� ˰�ʱ����� ֵ����
     */
    private List slbsjList;

    /**
     * ��ҵ�걨����
     *
     * �������ռ�����ҵ���� ֵ����
     */
    private Czzsjbqysj qysbsj;

    /**
     * Ͷ�����걨����
     *
     * List�����ݣ�����Ķ����Ǻ�Ͷ������Ŀ��ȵ� �������ռ���Ͷ�������� ֵ����
     */
    private List tzzsbsjList;

    // ������������
    /**
     * ���
     */
    private String nd;

    /**
     * ����
     */
    private String jd;

    /**
     * ����ʱ��
     */
    private String cjsj;

    /**
     * ˰��Ǽ�֤��
     */
    private String swdjzh;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     * ¼���˴���
     */
    private String lrr;

    /**
     * ֤�����ʹ���
     */
    private String[] zjlxdm;

    /**
     * ����˰��
     *
     * String[]
     */
    private String[] sysl;

    /**
     * ����۳���
     *
     * String[]
     */
    private String[] sskcs;
    /**
     * ����˰��
     *
     * String[]
     */
    private String[] jmse;
    /**
     * ¼������ String
     *
     * ���ɱ༭��ֵΪ��ǰ����
     */
    private String lrrq;

    /**
     * ��ҵ���շ�ʽ���ݽṹ
     */
    private Grzsfs grzsfs;

    /**
     * �Ƿ���������������������
     */
    private boolean done = true;

    /**
     * �ռ�ҳ������ת����ֵ����ŵ�qysbsj��tzzsbsjList�С�
     *
     * ���µı��ֶ���Ҫ��ҳ����ȡֵ��ֵ����������ֶβ��䣬���в������ռ���Ͷ����������
     * ����֤�����ʹ���ZJLXDM��֤������ZJHM����ȷ���ض���ֵ����
     *
     * �������ռ�����ҵ���� ֵ����
     * �����ܶ�	LRZE��form.lrze��
     *
     * �������ռ���Ͷ�������� ֵ����
     * Ӧ��˰���ö�	YNSSDE��form.ynssde��
     * ����˰��	SYSL��form.sysl��
     * ����۳���	SSKCS��form.sskcs��
     * Ӧ������˰��	YNSDSE��form.ynssde��
     * ����˰��	JMSE�����գ�
     * �ڳ�δ������˰��	QCWJSDSE��form.qcwjse��
     * �ѽ�������˰��	YJNSDSE��form.yjnse��
     * ʵ��Ӧ��˰��	SJYJSE��form.sjyjse��
     * @param jsjdm ���������
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    private void collect(String jsjdm) throws ApplicationException
    {

        Date today = new Date();

        // ˰����������
        Map sksjrqMap = Skssrq.otherSkssrq(today);
        String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
        String quarter = Skssrq.preQuarter(today);
        int quarterInt = Integer.parseInt(quarter);

        // �����ж��Ƿ�С��0�Ļ�׼ֵ
        BigDecimal zero = new BigDecimal(0);
        // ʹ�ÿ��ŵļ�������룬���ֵ������ֵ
        this.qysbsj = newInstanceCzzsjdQysbsj(
            jsjdm,
            year,
            quarter,
            this.cjsj,
            this.lrrq,
            this.skssksrq,
            this.skssjsrq,
            this.nsrmc,
            this.lrze,
            this.swdjzh,
            this.swjgzzjgdm,
            this.lrr,
            this.lrrq,
            CodeConstant.FSDM_WSSB
            );
        // ʹ�ÿ��ŵ�����
        this.qysbsj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        this.qysbsj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        this.qysbsj.setLrrq(new Timestamp(today.getTime()));
        this.qysbsj.setSbrq(TinyTools.second2Day(new Timestamp(today.getTime())));
        this.qysbsj.setCjrq(new Timestamp(today.getTime()));

        this.tzzsbsjList = new ArrayList(this.tzzxm.length);
        BigDecimal total = new BigDecimal(0);
        for(int i = 0, size = this.tzzxm.length; i < size; i++)
        {
            //���߼��Ƿ���ȷ
            //��֤����
            //Ӧ��˰���ö�����ܶ� �� ����������
            //�������ܶ�ΪС��0��Ӧ��˰��Ϊ0

            //�ڳ�δ��˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.qcwjsdse[i])) > 0)
            {
                throw new ApplicationException("�ڳ�δ��˰��Ӧ�ô��ڵ���0��");
            }
            //�ѽ���˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.ynsdse[i])) > 0)
            {
                throw new ApplicationException("�ѽ���˰��Ӧ�ô��ڵ���0��");
            }
            //ʵ��Ӧ��˰����ڵ���0��
            if(zero.compareTo(new BigDecimal(this.yjnsdse[i])) > 0)
            {
                throw new ApplicationException("ʵ��Ӧ��˰��Ӧ�ô��ڵ���0��");
            }

            //Ӧ��˰�Ӧ��˰���ö������˰�ʣ�����۳���
//            BigDecimal lrzeCount = new BigDecimal(this.lrze);
//            BigDecimal fpblCount = new BigDecimal(this.fpbl[i]);
            // ��Ϊ����˰�ʴ����ݿ����������0��100֮������ݣ���Ҫ����100��
//            BigDecimal ynssdeCount = lrzeCount.multiply(fpblCount).divide(new
//                BigDecimal(100), BigDecimal.ROUND_HALF_EVEN).setScale(2,
//                BigDecimal.ROUND_HALF_EVEN);
//            BigDecimal ynsdseCount = new BigDecimal(0);
//            total = total.add(fpblCount);
//            if(ynssdeCount.compareTo(zero) > 0)
//            {
                //���˼����걨�ļ���Ҫ��Ӧ��˰���ö�/����ǰ���ȣ�1����4��
                //�����ȫ���˰������ȫ���˰����˰�ʱ��е�˰�ʣ��ټ��㡣
                // ע�⣬��������㷨�����ҵ��ģ���ǲ��ܴ�һ���Ƚ����ġ�
//                BigDecimal extend = ynssdeCount.divide(
//                    new BigDecimal(quarterInt), BigDecimal.ROUND_HALF_EVEN).
//                    multiply(new BigDecimal(4));
                // �������ö���һ����˰�ʺ�����۳�����
//                BigDecimal[] temp = getLevel(extend, this.slbsjList);
//                ynsdseCount = ynssdeCount.multiply(temp[0]).subtract(temp[1]);
//                this.sysl[i] = temp[0].toString();
//                this.sskcs[i] = temp[1].toString();
//            } else
//            {
//                this.sysl[i] = "0.00";
//                this.sskcs[i] = "0.00";
//            }
//            this.ynsdse[i] = ynsdseCount.toString();
            Czzsjbtzzsj czzsjbtzzsj = newInstanceCzzsjdTzzsbsj(
                jsjdm,
                year,
                quarter,
                this.zjlxdm[i],
                this.zjhm[i],
                this.tzzxm[i],
                this.lrrq,
                this.fpbl[i],
                this.ynssde[i],
                this.sysl[i],
                this.sskcs[i],
                this.ynsdse[i],
                this.jmse[i],
                this.qcwjsdse[i],
                this.yjnsdse[i],
                this.sjyjse[i],
                this.swjgzzjgdm
                );
            this.tzzsbsjList.add(czzsjbtzzsj);
        }
//        if(total.compareTo(new BigDecimal(100)) != 0)
//        {
//            throw new ApplicationException("ȫ��Ͷ���ߵķ�������Ͳ�Ϊ100��");
//        }
    }

    /**
     * ��˰�ʱ��������ҳ����ʵ�һ����������˰�ʺ�����۳���
     * @param find ��Ҫ�鵲������
     * @param slbList ˰�ʱ�����
     * @return BigDecimal[] ����˰�ʺ�����۳���
     */
    private BigDecimal[] getLevel(BigDecimal find, List slbList)
    {
        BigDecimal zero = new BigDecimal(0);
        BigDecimal[] temp = new BigDecimal[2];
        for(int i = 0, size = slbList.size(); i < size; i++)
        {
            Szsm szsm = (Szsm)slbList.get(i);
            //���� ��ʼ ���� ��С�� ��ֹ ���� ��ֹ == 0��
            if(find.compareTo(szsm.getYnsqss()) > 0
               && (find.compareTo(szsm.getYnszzs()) <= 0
                   || zero.compareTo(szsm.getYnszzs()) == 0))
            {
                temp[0] = szsm.getSl();
                temp[1] = szsm.getSskcs();
                break;
            }
        }
        return temp;
    }

    /**
     * ��data��ʹ��keyΪCzzsjdMapConstant.QYSBSJȡ��ҵ�걨���ݷŵ�form��qysbsj�У�
     * ʹ��keyΪCzzsjdMapConstant.LIST_TZZSBSJȡͶ�����걨���ݷŵ�form��tzzsbsjList�У�
     * @param data ���ص�����
     */
    public void afterQuery(Map data)
    {
        this.qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
        this.tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);
    }

    /**
     * �ռ���Ϣת����ֵ����
     * ����Map��
     * ʹ��keyΪCzzsjdMapConstant.QYSBSJ����ҵ�걨����qysbsj��
     * ʹ��keyΪCzzsjdMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList��
     * ���ش�Map��
     * @param jsjdm ���������
     * @return Map
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeSave(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     * ����Map��ʹ��keyΪCzzsjdMapConstant.JSJDM�����������룬���ش�Map��
     * @param jsjdm ���������
     * @param tzfList Ͷ�ʷ�����
     * @return Map
     * @throws BaseException
     */
    public Map beforeQuery(String jsjdm, List tzfList) throws BaseException
    {
        Map map = new HashMap(2);

        map.put(CzzsjdMapConstant.JSJDM, jsjdm);
        map.put(CzzsjdMapConstant.LIST_TZF, tzfList);
        return map;
    }

    /**
     * �ռ���Ϣת����ֵ����
     * ����Map��
     * ʹ��keyΪCzzsjdMapConstant.QYSBSJ����ҵ�걨����qysbsj��
     * ʹ��keyΪCzzsjdMapConstant.LIST_TZZSBSJ��Ͷ�����걨����tzzsbsjList��
     * ���ش�Map��
     * @param jsjdm ���������
     * @return Map ������Ҫɾ����ֵ����
     * @throws ApplicationException У��û��ͨ����ʱ���׳��쳣
     */
    public Map beforeDelete(String jsjdm) throws ApplicationException
    {
        this.collect(jsjdm);
        Map map = new HashMap(2);
        map.put(CzzsjdMapConstant.QYSBSJ, this.qysbsj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, this.tzzsbsjList);
        return map;
    }

    /**
     *
     * @param lrrq     ¼������
     * @param qcwjsdse �ڳ�δ������˰��
     * @param sjyjse   ʵ��Ӧ��˰��
     * @param jsjdm    ���������
     * @param zjhm     ֤������
     * @param tzzxm    Ͷ��������
     * @param ynssde   Ӧ������˰��
     * @param nd       ���
     * @param zjlxdm   ֤�����ʹ���
     * @param sysl     ����˰��
     * @param jmse     ����˰��
     * @param jd       ����
     * @param sskcs    ����۳���
     * @param ynsdse   Ӧ������˰��
     * @param fpbl     �������
     * @param yjnsdse  �ѽ�������˰��
     * @param swjgzzjgdm  ˰�������֯��������
     * @return     Czzsjbtzzsj �������ռ���Ͷ�������� ֵ����
     */
    public Czzsjbtzzsj newInstanceCzzsjdTzzsbsj(
        String jsjdm,
        String nd,
        String jd,
        String zjlxdm,
        String zjhm,
        String tzzxm,
        String lrrq,
        String fpbl,
        String ynssde,
        String sysl,
        String sskcs,
        String ynsdse,
        String jmse,
        String qcwjsdse,
        String yjnsdse,
        String sjyjse,
        String swjgzzjgdm
        )
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Czzsjbtzzsj czzsjbtzzsj = new Czzsjbtzzsj();
        //���������
        czzsjbtzzsj.setJsjdm(jsjdm);
        //���
        czzsjbtzzsj.setNd(nd);
        //����
        czzsjbtzzsj.setJd(jd);
        //֤�����ʹ���
        czzsjbtzzsj.setZjlxdm(zjlxdm);
        //֤������
        czzsjbtzzsj.setZjhm(zjhm);
        //Ͷ��������
        czzsjbtzzsj.setTzzxm(tzzxm);
        //¼������
        czzsjbtzzsj.setLrrq(now);
        //�������
        czzsjbtzzsj.setFpbl(new BigDecimal(fpbl));
        //Ӧ������˰��
        czzsjbtzzsj.setYnssde(new BigDecimal(ynssde));
        //Ӧ������˰��
        czzsjbtzzsj.setSysl(new BigDecimal(sysl));
        //����۳���
        czzsjbtzzsj.setSskcs(new BigDecimal(sskcs));
        //Ӧ������˰��
        czzsjbtzzsj.setYnsdse(new BigDecimal(ynsdse));
        //����˰��
        czzsjbtzzsj.setJmse(new BigDecimal(jmse));
        //�ڳ�δ������˰��
        czzsjbtzzsj.setQcwjsdse(new BigDecimal(qcwjsdse));
        //�ѽ�������˰��
        czzsjbtzzsj.setYjnsdse(new BigDecimal(yjnsdse));
        //ʵ��Ӧ��˰��
        czzsjbtzzsj.setSjyjse(new BigDecimal(sjyjse));
        //˰�������֯��������
        czzsjbtzzsj.setSwjgzzjgdm(swjgzzjgdm);

        czzsjbtzzsj.setCjrq(now);
        czzsjbtzzsj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsjbtzzsj;
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
     *
     * @param jsjdm ���������
     * @param nd ���
     * @param jd ����
     * @param cjsj ����ʱ��
     * @param lrrq ¼������
     * @param skssksrq ˰��������ʼ����
     * @param skssjsrq ˰��������������
     * @param nsrmc ��˰������
     * @param lrze �����ܶ�
     * @param swdjzh ˰��Ǽ�֤��
     * @param swjgzzjgdm  ˰�������֯��������
     * @param lrr ¼���˴���
     * @param sbrq �걨����
     * @param fsdm ��ʽ����
     * @return  Czzsjbqysj �������ռ�����ҵ���� ֵ����
     */
    public Czzsjbqysj newInstanceCzzsjdQysbsj(
        String jsjdm,
        String nd,
        String jd,
        String cjsj,
        String lrrq,
        String skssksrq,
        String skssjsrq,
        String nsrmc,
        String lrze,
        String swdjzh,
        String swjgzzjgdm,
        String lrr,
        String sbrq,
        String fsdm
        )
    {

        Czzsjbqysj czzsjbqysj = new Czzsjbqysj();
//	* @param jsjdm ���������
        czzsjbqysj.setJsjdm(jsjdm);
//      * @param nd ���
        czzsjbqysj.setNd(nd);
//    	* @param jd ����
        czzsjbqysj.setJd(jd);
//    	* @param cjsj ����ʱ��
//        czzsjbqysj.setCjrq(getTimestamp(cjsj));
//    	* @param lrrq ¼������
//        czzsjbqysj.setLrr(lrr);
//    	* @param skssksrq ˰��������ʼ����
        czzsjbqysj.setSkssksrq(getTimestamp(skssksrq));
//    	* @param skssjsrq ˰��������������
        czzsjbqysj.setSkssjsrq(getTimestamp(skssjsrq));
//    	* @param nsrmc ��˰������
        czzsjbqysj.setNsrmc(nsrmc);
//    	* @param lrze �����ܶ�
        czzsjbqysj.setLrze(new BigDecimal(lrze));
//    	* @param swdjzh ˰��Ǽ�֤��
        czzsjbqysj.setSwdjzh(swdjzh);
//    	* @param swjgzzjgdm  ˰�������֯��������
        czzsjbqysj.setSwjgzzjgdm(swjgzzjgdm);
//    	* @param lrr ¼���˴���
        czzsjbqysj.setLrr(lrr);
//    	* @param sbrq �걨����
//        czzsjbqysj.setSbrq(getTimestamp(sbrq));
//    	* @param fsdm ��ʽ����
        czzsjbqysj.setFsdm(fsdm);
        czzsjbqysj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsjbqysj;
    }

    // getter && setter
    public List getTzzsbsjList()
    {
        return tzzsbsjList;
    }

    public List getSlbsjList()
    {
        return slbsjList;
    }

    public Czzsjbqysj getQysbsj()
    {
        return qysbsj;
    }

    public void setCjsj(String cjsj)
    {
        this.cjsj = cjsj;
    }

    public void setFpbl(String[] fpbl)
    {
        this.fpbl = fpbl;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public void setLrrq(String lrrq)
    {
        this.lrrq = lrrq;
    }

    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setQcwjsdse(String[] qcwjsdse)
    {
        this.qcwjsdse = qcwjsdse;
    }

    public void setSjyjse(String[] sjyjse)
    {
        this.sjyjse = sjyjse;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSskcs(String[] sskcs)
    {
        this.sskcs = sskcs;
    }

    public void setSwdjzh(String swdjzh)
    {
        this.swdjzh = swdjzh;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSysl(String[] sysl)
    {
        this.sysl = sysl;
    }

    public void setTzzxm(String[] tzzxm)
    {
        this.tzzxm = tzzxm;
    }

    public void setYjnsdse(String[] yjnsdse)
    {
        this.yjnsdse = yjnsdse;
    }

    public void setYnsdse(String[] ynsdse)
    {
        this.ynsdse = ynsdse;
    }

    public void setYnssde(String[] ynssde)
    {
        this.ynssde = ynssde;
    }

    public void setZjhm(String[] zjhm)
    {
        this.zjhm = zjhm;
    }

    public void setZjlxdm(String[] zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSlbsjList(List slbsjList)
    {
        this.slbsjList = slbsjList;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    public boolean isDone()
    {
        return done;
    }

}