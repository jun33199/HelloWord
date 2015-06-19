package com.ttsoft.bjtax.shenbao.sbzl.jms.web;

import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 *
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ����˰�걨Form</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Wu youzhi
 * @version 1.0
 */
public class JmsForm extends SbzlBaseForm
{

    /**
     * ˰����������
     */
    private String jsjdm;

    /**
     * ��λ����
     */
    private String nsrmc;

    /**
     * ��˰����
     */
    private String[] kssl;

    /**
     * ��˰���
     */
    private String[] jsje;

    /**
     * ����˰��
     */
    private String[] jmse;

    /**
     * �ϼ�
     */
    private String hj;

    /**
     * �걨����
     * ϵͳ�Զ�������������ǰ����
     */
    private Timestamp sbrq;

    /**
     * ˰��˰Ŀ����
     */
    private String[] szsmdm;

    /**
     * ˰��˰Ŀ����
     */
    private String[] szdm;

    /**
     * ��������
     */
    private String[] jmlx;

    /**
     * �����걨����
     * ��ż����걨ֵ����
     */
    private List jmsbList = new ArrayList();

    /**
     * ���걨����
     * ��ż����걨ֵ����
     */
    private List jmysbList = new ArrayList();

    /**
     * ��˰������ֵ����
     */
    private SWDJJBSJ nsrzl;

    // ����ʱ��
    private String cjsj[];

    // �������ʹ���
    private String zqlxdm[];

    // �������ʹ���
    private String jmxmdm[];

    // ˰����Ϣ
    private List szList = new ArrayList();

    // ˰Ŀ��Ϣ
    private List smList = new ArrayList();

    // ˰��˰ĿMap
    private Map szsmMap;

    // �������List
    private List jmflList;

    // �������Map
    private Map jmflMap;

    // ����˰List
    private List fjsList;

    /**
     * ����һ��map
     * JmsMapConstant.SBRQ-----�걨����
     * @return Map
     */
    public Map getQueryData()
    {
        Map queryMap = new HashMap();
        queryMap.put(JmsMapConstant.SBRQ,this.sbrq);
        queryMap.put(JmsMapConstant.QXDM,this.nsrzl.getSwjgzzjgdm().substring(0,2));
        queryMap.put(JmsMapConstant.DJZCLXDM,this.nsrzl.getDjzclxdm());
        return queryMap;
    }

    /**
     * ��form�е�����ת��Ϊֵ����ʹ��keyΪJmsMapConstant.LIST_JMSB��������걨����
     * @return Map
     * @throws BaseException
     */
    public Map getSbData() throws BaseException
    {
        try
        {
            Map data = new HashMap();
            List sbList = new ArrayList();
            for(int i = 0; i < this.szsmdm.length; i++)
            {
                Jm vo = new Jm();
                // ���������
                vo.setJsjdm(this.jsjdm);
                // ��ʽ����
                vo.setFsdm(CodeConstant.FSDM_WSSB);
                // ��������
                vo.setJmlx(this.jmlx[i]);
                // ����˰��
                vo.setJmse(new BigDecimal(this.jmse[i]));
                // ��˰���
                vo.setJsje(new BigDecimal(this.jsje[i]));
                // ��˰����
                Szsm szsmTmp = (Szsm)szsmMap.get(this.szsmdm[i]); // ȡ��Szsmֵ����
                if(szsmTmp.getAsljbs() != null ? szsmTmp.getAsljbs().equals("1") : false) // �Ƿ�������
                {
                    vo.setKssl(new BigDecimal(this.kssl[i]));
                }
                // �������ʹ���
                vo.setJmxmdm(this.jmxmdm[i]);
                // �Ǽ�ע�����ʹ���
                vo.setDjzclxdm(nsrzl.getDjzclxdm());
                // ���ұ�׼��ҵ����
                vo.setGjbzhydm(nsrzl.getGjbzhydm());
                // ¼����
                vo.setLrr(this.jsjdm);
                // ¼������
                vo.setLrrq(this.sbrq);
                // ��˰������
                //vo.setNsrmc(nsrzl.getNsrmc());
                // �걨����
                vo.setSbrq(TinyTools.second2Day(this.sbrq));
                // ˰�������֯�ṹ����
                vo.setSwjgzzjgdm(nsrzl.getSwjgzzjgdm());
                // ˰��˰Ŀ����
                vo.setSzsmdm(this.szsmdm[i]);
                // ����ʱ��
                if(this.cjsj[i] == null || this.cjsj[i].equals(""))
                {
                    vo.setCjrq(this.sbrq);
                }
                else
                {
                    vo.setCjrq(new Timestamp(Long.parseLong(this.cjsj[i])));
                }
                // ���
                vo.setNd(TinyTools.Date2String(this.sbrq).substring(0,4));
                // ���ش���
                vo.setQxdm(nsrzl.getSwjgzzjgdm().substring(0, 2));

                // ȡ��˰����������
                Map skssrqMap = Skssrq.getSksssq(this.jsjdm,
                                                 this.szsmdm[i],
                                                 nsrzl.getDjzclxdm(),
                                                 CodeConstant.SKLXDM_ZCJK,
                                                 zqlxdm[i],
                                                 this.sbrq,
                                                 null,
                                                 null,
                                                 null);
                vo.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
                vo.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));

                sbList.add(vo);
            }
            // �ѱ����걨���ݺͱ������걨���ݷ���
            data.put(JmsMapConstant.LIST_JMSB, sbList);
            data.put(JmsMapConstant.LIST_JMYSB, jmysbList);
            data.put(JmsMapConstant.ZQLXDM,zqlxdm);

            return data;
        }
        catch(Exception e)
        {
            throw new ApplicationException("���ύ�˴�������ݣ�����������걨��");
        }
    }

    /**
     * ��keyΪJmsMapConstant.LIST_JMYSBȡ�������걨����
     * @param data �������걨����
     */
    public void setFormData(Map data)
    {
        this.jmysbList = (List)data.get(JmsMapConstant.LIST_JMYSB);
    }

    public String[] getJmlx()
    {
        return jmlx;
    }

    public void setJmlx(String[] jmlx)
    {
        this.jmlx = jmlx;
    }

    public List getJmsbList()
    {
        return jmsbList;
    }

    public void setJmsbList(List jmsbList)
    {
        this.jmsbList = jmsbList;
    }

    public void setJmse(String[] jmse)
    {
        this.jmse = jmse;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public void setKssl(String[] kssl)
    {
        this.kssl = kssl;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public String getHj()
    {
        return hj;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String[] getCjsj()
    {
        return cjsj;
    }

    public void setCjsj(String cjsj[])
    {
        this.cjsj = cjsj;
    }

    public void setSbrq(Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }
    public List getJmysbList()
    {
        return jmysbList;
    }
    public String[] getSzsmdm()
    {
        return szsmdm;
    }
    public void setNsrzl(SWDJJBSJ nsrzl)
    {
        this.nsrzl = nsrzl;
    }
    public String getNsrmc()
    {
        return this.nsrzl.getNsrmc();
    }
    public Timestamp getSbrq()
    {
        return sbrq;
    }
    public String[] getJmse()
    {
        return jmse;
    }
    public String[] getJsje()
    {
        return jsje;
    }
    public String[] getKssl()
    {
        return kssl;
    }
    public String[] getZqlxdm()
    {
        return zqlxdm;
    }
    public void setZqlxdm(String[] zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }
    public List getSzList()
    {
        return szList;
    }
    public void setSzList(List szList)
    {
        this.szList = szList;
    }
    public List getSmList()
    {
        return smList;
    }
    public void setSmList(List smList)
    {
        this.smList = smList;
    }
    public String[] getSzdm()
    {
        return szdm;
    }
    public void setSzdm(String[] szdm)
    {
        this.szdm = szdm;
    }
    public void setJmysbList(List jmysbList)
    {
        this.jmysbList = jmysbList;
    }
    public void setJmxmdm(String[] jmxmdm)
    {
        this.jmxmdm = jmxmdm;
    }
    public Map getSzsmMap()
    {
        return szsmMap;
    }
    public void setSzsmMap(Map szsmMap)
    {
        this.szsmMap = szsmMap;
    }
    public List getJmflList()
    {
        return jmflList;
    }
    public void setJmflList(List jmflList)
    {
        this.jmflList = jmflList;
    }
    public Map getJmflMap()
    {
        return jmflMap;
    }
    public void setJmflMap(Map jmflMap)
    {
        this.jmflMap = jmflMap;
    }
    public SWDJJBSJ getNsrzl()
    {
        return nsrzl;
    }
    public List getFjsList()
    {
        return fjsList;
    }
    public void setFjsList(List fjsList)
    {
        this.fjsList = fjsList;
    }

}