package com.ttsoft.bjtax.shenbao.sbzl.yhs.web;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.Skssrq;

import java.util.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * @author zhiyong He
 * @version 1.0
 * ӡ��˰Form
 */
public class YhsForm extends SbzlBaseForm
{

    /**
     * ˰��������ʼ��������
     */
    private String skssksrq;

    /**
     * ˰��������������
     */
    private String skssjsrq;

    /**
     * ˰����������
     */
    private String jsjdm;

    /**
     * ˰��˰Ŀ����
     */
    private String[] ssrq;

    /**
     * ��ϵ�绰
     */
    private String lxdh;

    /**
     * �ϼƷ���
     */
    private String hjfs;

    /**
     * �ϼƼ�˰���
     */
    private String hjjsje;

    /**
     * �ϼ�����˰��
     */
    private String hjynse;

    /**
     * ӡ��˰������ϸ����
     * ���ӡ��˰��ϸֵ����
     */
    private List yhsbgmxList;

    /**
     * ӡ��˰˰��˰Ŀ��ϢList
     */
    private List yhsSzsmInfoList;

    /**
     * ˰Ŀ
     */
    private String[] sm;

    /**
     * ����
     */
    private String[] fs;

    /**
     * ��˰���
     */
    private String[] jsje;

    /**
     * ˰��˰Ŀ����
     * ���˰��˰Ŀֵ����
     */
    private List szsmList;

    /**
     * ˰��
     */
    private String[] sl;

    /**
     * ����˰��
     */
    private String[] ynse;

    /**
     * ӡ��˰����������
     * ���ӡ��˰������ֵ����
     */
    private Yhsbgz yhsbgz;

    /**
     * ��˰������ֵ����
     */
    private SWDJJBSJ nsrzl;

    /**
     * ���
     */
    private String nd;
    /**
     *  ��˰������
     */
    private String nsrmc;
    /**
     *  ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     * ˰��˰Ŀ��������
     */
    private String[] szsmdm;

    public YhsForm()
    {

    }

    /**
     * ʹ��keyΪYhsMapConstant.JSJDM�����������룬����Map��
     * ����VOPackage������action= YhsActionConstant.INT_ACTION_QUERY���ݸ�processor
     * @param userData UserData
     * @param djMap Map
     * @return VOPackage
     */
    public VOPackage getQueryData(UserData userData, Map djMap)
    {
        Map data = new HashMap();
        data.put(YhsMapConstant.JSJDM, userData.yhid);
        data.put(YhsMapConstant.MAP_DJSJ, djMap);
        VOPackage voPackage = new VOPackage();
        voPackage.setData(data);
        voPackage.setAction(YhsActionConstant.INT_ACTION_QUERY);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);
        return voPackage;
    }

    /**
     * ȡӡ��˰������ϸ������YhsMapConstant.LIST_YHSBGMX����yhsbgmxList
     * ȡӡ��˰������������YhsMapConstant.LIST_YHSBG����yhsbgList
     * ȡ˰��˰Ŀ������YhsMapConstant.LIST_SZSM����szsmList
     * ������˰��������YhsMapConstant.NSRZL����nsrzl
     * ����form�е����ݣ���ʾҳ��
     *
     * jsjdm = nsrzl.jsjdm;
     * nsrmc = nsrzl.nsrmc;
     * lxdh = nsrzl.lxdh;
     * hjfs = yhszsj.hjfs;
     * hjjsje = yhszsj.hjjsje;
     * hjynse = yhszsj.hjynse;
     * szsmdm[] = yhsmxsj.szsmdm;
     * fs = yhsmxsj.fs;
     * jsje = yhsmxsj.jsje;
     * sl = yhsmxsj.sl;
     * sjse = yhsmxsj.sjse;
     *
     * ӡ��˰����������
     * ���������	JSJDM	VARCHAR2(8)
     * ���	ND	VARCHAR2(4)
     * ˰��������ʼ����	SKSSKSRQ	DATE
     * ˰��������������	SKSSJSRQ	DATE
     * ��˰������	NSRMC	VARCHAR2(200)
     * ��ҵ��ϵ�绰	QYLXDH	VARCHAR2(20)
     * �ϼƷ���	HJFS	NUMBER(15,2)
     * �ϼƼ�˰���	HJJSJE	NUMBER(15,2)
     * �ϼ�����˰��	HJYNSE	NUMBER(15,2)
     * ˰�������֯��������	SWJGZZJGDM	VARCHAR2(8)
     * ˰�������֯��������	SWJGZZJGMC	VARCHAR2(60)
     * ¼���˴���	LRR	VARCHAR2(30)
     * ��ע	BZ	VARCHAR2(100)
     * ˰�������֯��������2	SWJGZZJGDM2	VARCHAR2(8)
     *
     * ӡ��˰������ϸ����
     * ���������	JSJDM	VARCHAR2(8)
     * ���	ND	VARCHAR2(4)
     * ˰��˰Ŀ����	SZSMDM	 VARCHAR2(9)
     * ˰�ִ���	SZDM	VARCHAR2(9)
     * ˰������	SZMC	VARCHAR2(60)
     * ˰��˰Ŀ����	SZSMMC	VARCHAR2(60)
     * ����	FS	NUMBER(15,2)
     * ��˰���	JSJE	NUMBER(15,2)
     * ˰��	SL	NUMBER(10,5)
     * ʵ��˰��	SJSE	NUMBER(15,2)
     * ��ע	BZ	VARCHAR2(100)
     * @param voPackage VOPackage
     */
    public void setFormData(VOPackage voPackage)
    {
        Map data = (Map)voPackage.getData();
        this.nsrzl = (SWDJJBSJ)data.get(YhsMapConstant.JBSJ);
        this.yhsbgz = (Yhsbgz)data.get(YhsMapConstant.YHSBGZ);
        this.yhsbgmxList = (List)data.get(YhsMapConstant.LIST_YHSBGMX);
        this.yhsSzsmInfoList = (List)data.get(YhsMapConstant.LIST_SZSM);
        // ��Ϊҳ����ʾ��Ҫ����ӡ��˰������ϸ��SZSMMC
        for(int i=0; i<this.yhsbgmxList.size(); i++)
        {
           ((Yhsbgmx)this.yhsbgmxList.get(i)).setSzsmmc(((Szsm)(this.yhsSzsmInfoList.get(i))).getSzsmmc());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.skssksrq = sdf.format(this.yhsbgz.getSkssksrq());
        this.skssjsrq = sdf.format(this.yhsbgz.getSkssjsrq());
        this.nsrmc = this.nsrzl.getNsrmc();
        this.hjfs = this.yhsbgz.getHjfs()==null?null:this.yhsbgz.getHjfs().toString();
        this.hjjsje = this.yhsbgz.getHjjsje()==null?null:this.yhsbgz.getHjjsje().toString();
        this.hjynse = this.yhsbgz.getHjynse()==null?null:this.yhsbgz.getHjynse().toString();
        this.jsjdm = this.nsrzl.getJsjdm();
        this.lxdh = this.nsrzl.getJydzlxdm();
        this.nd = this.yhsbgz.getNd();
        this.swjgzzjgdm = this.yhsbgz.getSwjgzzjgdm();
    }

    /**
     * ʹ��keyΪYhsMapConstant.LIST_YHSBGMX��ӡ��˰������ϸ���ݣ�
     * ʹ��keyΪYhsMapConstant.LIST_YHSBG��ӡ��˰����������yhsbgList
     * ʹ��keyΪYhsMapConstant.JSJDMȡ��������룬����Map��
     * ����VOPackage��action= YhsActionConstant.INT_ACTION_SAVE���ݸ�processor
     *
     * ����ֵ����List�ֶζ�Ӧ�ο�afterQuery����ע��
     * @param userData UserData
     * @return VOPackage
     */
    public VOPackage getSbData(UserData userData, SWDJJBSJ jbsj)
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        VOPackage voPackage = new VOPackage();
        Map data = new HashMap();

        // ӡ��˰�걨��ϸ������
        this.yhsbgmxList = new ArrayList();
        // ȥ���ո�
        this.strTrim();
        for(int i = 0; i < this.szsmdm.length; i++)
        {
            Yhsbgmx mx = new Yhsbgmx();
            // �������ӡ��˰˰Ŀ�ǰ���������λ˰���������˰��
            if(this.szsmdm[i].equals(SzsmdmConstant.YHS_QTZB) ||
               this.szsmdm[i].equals(SzsmdmConstant.YHS_QLXKZZ))
            {
                mx.setFs(strToBigDecimal(this.fs[i]));
                mx.setSl(strToBigDecimal(this.sl[i]));

                mx.setSjse(strToBigDecimal(this.ynse[i]));
                mx.setJsje(strToBigDecimal(this.jsje[i]));
            }
            //�������ӡ��˰˰Ŀ�ǰ���˰����˰�ʼ�������˰��
            else
            {
                mx.setFs(strToBigDecimal(this.fs[i]));
                mx.setSl(strToBigDecimal(this.sl[i]));
                mx.setJsje(strToBigDecimal(this.jsje[i]));
                mx.setSjse(strToBigDecimal(this.ynse[i]));
            }
            mx.setJsjdm(this.jsjdm);
            mx.setNd(this.nd);
            mx.setSzdm(SzsmdmConstant.YHS);
            mx.setSzsmdm(this.szsmdm[i]);
            mx.setSwjgzzjgdm(this.swjgzzjgdm);
            mx.setLrrq(now);
            mx.setCjrq(now);
            mx.setQxdm(this.swjgzzjgdm.substring(0, 2));

            this.yhsbgmxList.add(mx);
        }
        // ӡ��˰��ȱ�������
        this.yhsbgz = new Yhsbgz();
        this.yhsbgz.setFsdm(CodeConstant.FSDM_WSSB);
        this.yhsbgz.setHjfs(strToBigDecimal(this.hjfs));
        this.yhsbgz.setHjjsje(strToBigDecimal(this.hjjsje));
        this.yhsbgz.setHjynse(strToBigDecimal(this.hjynse));
        this.yhsbgz.setJsjdm(this.jsjdm);
        this.yhsbgz.setLrr(userData.yhid);
        this.yhsbgz.setNd(this.nd);
        Map skssrqMap = this.getSkssrq();
        this.yhsbgz.setSkssjsrq( (Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        this.yhsbgz.setSkssksrq( (Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        this.yhsbgz.setSwjgzzjgdm(this.swjgzzjgdm);

        this.yhsbgz.setLrrq(now);
        this.yhsbgz.setCjrq(now);
        this.yhsbgz.setQxdm(this.swjgzzjgdm.substring(0, 2));

        data.put(YhsMapConstant.YHSBGZ, this.yhsbgz);
        data.put(YhsMapConstant.LIST_YHSBGMX, this.yhsbgmxList);
        data.put(YhsMapConstant.JBSJ, jbsj);

        voPackage.setData(data);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);
        return voPackage;
    }

    // ���ַ�����ֵת��ΪBigDecimal
    private BigDecimal strToBigDecimal(String value)
    {
        if(value != null && !value.trim().equals(""))
        {
            return new BigDecimal(value);
        }
        else
        {
            return null;
        }
    }

    // ȥ�������û��ύ�����ݵ����ߵĿո��Է���һ
    private void strTrim()
    {
        for(int i=0; i<this.fs.length; i++)
        {
             this.fs[i] = this.fs[i].trim();
             if(this.fs[i].equals(""))
             {
                 this.fs[i] = "0";
             }
             this.jsje[i] = this.jsje[i].trim();
             this.sl[i] = this.sl[i].trim();
             // ˰���п���Ϊ���ַ�
             if(this.sl[i].equals(""))
             {
                this.sl[i]="0.00";
             }
             this.ynse[i] = this.ynse[i].trim();
        }
    }
    // ����˰����������
    private Map getSkssrq()
    {
        return Skssrq.yearSkssrq(new Date());
    }

    public String[] getFs()
    {
        return fs;
    }

    public void setFs(String[] fs)
    {
        this.fs = fs;
    }

    public String getHjfs()
    {
        return hjfs;
    }

    public void setHjfs(String hjfs)
    {
        this.hjfs = hjfs;
    }

    public String getHjjsje()
    {
        return hjjsje;
    }

    public void setHjjsje(String hjjsje)
    {
        this.hjjsje = hjjsje;
    }

    public String getHjynse()
    {
        return hjynse;
    }

    public void setHjynse(String hjynse)
    {
        this.hjynse = hjynse;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String[] getJsje()
    {
        return jsje;
    }

    public void setJsje(String[] jsje)
    {
        this.jsje = jsje;
    }

    public String getLxdh()
    {
        return lxdh;
    }

    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }

    public SWDJJBSJ getNsrzl()
    {
        return nsrzl;
    }

    public void setNsrzl(SWDJJBSJ nsrzl)
    {
        this.nsrzl = nsrzl;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String[] getSl()
    {
        return sl;
    }

    public void setSl(String[] sl)
    {
        this.sl = sl;
    }

    public String[] getSm()
    {
        return sm;
    }

    public void setSm(String[] sm)
    {
        this.sm = sm;
    }

    public String[] getSsrq()
    {
        return ssrq;
    }

    public void setSsrq(String[] ssrq)
    {
        this.ssrq = ssrq;
    }

    public List getSzsmList()
    {
        return szsmList;
    }

    public void setSzsmList(List szsmList)
    {
        this.szsmList = szsmList;
    }

    public List getYhsbgmxList()
    {
        return yhsbgmxList;
    }

    public void setYhsbgmxList(List yhsbgmxList)
    {
        this.yhsbgmxList = yhsbgmxList;
    }

    public Yhsbgz getYhsbgz()
    {
        return yhsbgz;
    }

    public void setYhsbgz(Yhsbgz yhsbgz)
    {
        this.yhsbgz = yhsbgz;
    }

    public String[] getYnse()
    {
        return ynse;
    }

    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String[] getSzsmdm()
    {
        return szsmdm;
    }

    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public List getYhsSzsmInfoList()
    {
        return yhsSzsmInfoList;
    }
    public void setYhsSzsmInfoList(List yhsSzsmInfoList)
    {
        this.yhsSzsmInfoList = yhsSzsmInfoList;
    }
}