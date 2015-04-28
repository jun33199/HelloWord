package com.ttsoft.bjtax.shenbao.zrrsb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��Ȼ���걨Form</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */
public class ZrrsbForm extends BaseForm
{
    // ��������
    private Zrrgrsdsz zrrgrsdsz;

    // ��ϸ����List
    private List zrrgrsdsmxList;

    // ��������˰ĿList
    private List gsszsmList = new ArrayList();

    // ����List
    private List bzList = new ArrayList();

    // ����List
    private List yhList = new ArrayList();

    private String jsjdm; //���������
    private String nsrmc; //��˰������
    private Timestamp sbrq; //�걨����
    private String zjlxdm; //֤�����ʹ���
    private String zjlxmc; //֤����������
    private String zjhm; //֤������
    private String gjdm; //��������
    private String gjmc = ""; //��������
    private Timestamp cjsj; //����ʱ��
    private String cjr; //������
    private Timestamp skssksrq; //˰��������ʼ����
    private Timestamp skssjsrq; //˰��������������
    private Timestamp dhrq; //�ֻ�����
    private String jnzz; //����סַ
    private String txdz; //ͨѶ��ַ
    private String yzbm; //��������
    private String zydm; //ְҵ����
    private String zymc; //ְҵ����
    private String dh; //�绰
    private String fwdw; //����λ
    private String fwdd; //����ص�
    private String dwfdbl; //��λ��������
    private String sfczbs; //�Ƿ�פ��ʶ
    private String zffkce; //�ⷿ�ѿ۳���
    private String fdfsdm; //������ʽ����
    private String fdfsmc; //������ʽ����
    private String qxdm; //���ش���
    private String yhmc; //��������
    private String yhdm; //���д���
    private String yhzh; //�����ʺ�

    // ��ϸ����
    private String[] szsmdm; //˰��˰Ŀ����
    private String[] sdksrq; //���ÿ�ʼ����
    private String[] sdjsrq; //���ý�������
    private String[] srermb; //����������
    private String[] bzdm; //���ִ���
    private String[] je; //���
    private String[] whpj; //����Ƽ�
    private String[] zhrmb; //�ۺ������
    private String[] rmbhj; //����Һϼ�
    private String[] jfye; //�����ö�
    private String[] ynssde; //Ӧ��˰���ö�
    private String[] sl; //˰��
    private String[] sskcs; //����۳���
    private String[] ynse; //Ӧ��˰��
    private String[] ykjse; //�ѿ۽�˰��
    private String[] ybtsk; //Ӧ����˰��
    private String[] zh;  //�ۺ�
    private String[] yjzgyjnsk; //ԭ��ס��˰��
    private String[] fdkce; //�����۳���

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


    /**
     * ȡ����Ȼ����ϸ����List
     * @return List
     * @throws BaseException
     */
    public List getZrrgrsdsmx(ZRRJBSJ zrrjbsj) throws BaseException
    {
        ArrayList mxList = new ArrayList();
        try
        {
            if(this.ybtsk != null)
            {
                int length = ybtsk.length;
                for(int i = 0; i < length; i++)
                {
                    Zrrgrsdsmx tempMx = new Zrrgrsdsmx();
//                    tempMx.setBzdm(bzdm[i]);
//                    tempMx.setJe(ToBigDecimal(je[i]));
                    tempMx.setJfye(ToBigDecimal(jfye[i]));
                    tempMx.setJsjdm(zrrjbsj.getJsjdm());
                    tempMx.setRmbhj(ToBigDecimal(rmbhj[i]));
                    tempMx.setCjrq(new Timestamp(System.currentTimeMillis()));
                    tempMx.setLrrq(new Timestamp(System.currentTimeMillis()));
                    tempMx.setSbrq(TinyTools.second2Day(new Timestamp(System.currentTimeMillis())));
                    tempMx.setSdjsrq(new Timestamp(sdf.parse(this.sdjsrq[i]).getTime()));
                    tempMx.setSdksrq(new Timestamp(sdf.parse(this.sdksrq[i]).getTime()));
                    tempMx.setSdksrqStr(this.sdksrq[i]);
                    tempMx.setSdjsrqStr(this.sdjsrq[i]);
//                    tempMx.setSl(ToBigDecimal(sl[i]));
                    tempMx.setSrermb(ToBigDecimal(srermb[i]));
//                    tempMx.setSskcs(ToBigDecimal(sskcs[i]));
                    tempMx.setSzsmdm(this.szsmdm[i]);
//                    tempMx.setWhpj(ToBigDecimal(whpj[i]));
                    tempMx.setYbtsk(ToBigDecimal(this.ybtsk[i]));
                    tempMx.setYkjse(ToBigDecimal(ykjse[i]));
                    tempMx.setYnse(ToBigDecimal(ynse[i]));
                    tempMx.setYnssde(ToBigDecimal(ynssde[i]));
                    tempMx.setZhrmb(ToBigDecimal(zhrmb[i]));
                    tempMx.setFdkce(ToBigDecimal(fdkce[i]));
                    tempMx.setYjzgsk(ToBigDecimal(yjzgyjnsk[i]));
                    tempMx.setQxdm(zrrjbsj.getSwjgzzjgdm().substring(0, 2));
                    tempMx.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm());
                    mxList.add(tempMx);
                }
            }
        }
        catch(Exception ex)
        {
//            throw new ApplicationException("���ύ�˴������ϸ���ݣ�����������걨��");
              throw new ApplicationException(ex.toString(),ex);
        }
        return mxList;
    }

    public void setBzdm(String[] bzdm)
    {
        this.bzdm = bzdm;
    }
    public String getCjr()
    {
        return cjr;
    }
    public void setCjr(String cjr)
    {
        this.cjr = cjr;
    }
    public Timestamp getCjsj()
    {
        return cjsj;
    }
    public void setCjsj(Timestamp cjsj)
    {
        this.cjsj = cjsj;
    }
    public String getDh()
    {
        return dh;
    }
    public void setDh(String dh)
    {
        this.dh = dh;
    }
    public Timestamp getDhrq()
    {
        return dhrq;
    }
    public void setDhrq(Timestamp dhrq)
    {
        this.dhrq = dhrq;
    }
    public String getDwfdbl()
    {
        return dwfdbl;
    }
    public void setDwfdbl(String dwfdbl)
    {
        this.dwfdbl = dwfdbl;
    }
    public String getFwdd()
    {
        return fwdd;
    }
    public void setFwdd(String fwdd)
    {
        this.fwdd = fwdd;
    }
    public String getFwdw()
    {
        return fwdw;
    }
    public void setFwdw(String fwdw)
    {
        this.fwdw = fwdw;
    }
    public String getGjdm()
    {
        return gjdm;
    }
    public void setGjdm(String gjdm)
    {
        this.gjdm = gjdm;
    }
    public void setJe(String[] je)
    {
        this.je = je;
    }
    public void setJfye(String[] jfye)
    {
        this.jfye = jfye;
    }
    public String getJnzz()
    {
        return jnzz;
    }
    public void setJnzz(String jnzz)
    {
        this.jnzz = jnzz;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setRmbhj(String[] rmbhj)
    {
        this.rmbhj = rmbhj;
    }
    public Timestamp getSbrq()
    {
        return sbrq;
    }
    public void setSbrq(Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }
    public void setSdjsrq(String[] sdjsrq)
    {
        this.sdjsrq = sdjsrq;
    }
    public void setSdksrq(String[] sdksrq)
    {
        this.sdksrq = sdksrq;
    }
    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }
    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }
//    public void setSl(String[] sl)
//    {
//        this.sl = sl;
//    }
    public void setSrermb(String[] srermb)
    {
        this.srermb = srermb;
    }
//    public void setSskcs(String[] sskcs)
//    {
//        this.sskcs = sskcs;
//    }
    public void setSzsmdm(String[] szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public String getTxdz()
    {
        return txdz;
    }
    public void setTxdz(String txdz)
    {
        this.txdz = txdz;
    }
    public void setWhpj(String[] whpj)
    {
        this.whpj = whpj;
    }
    public void setYbtsk(String[] ybtsk)
    {
        this.ybtsk = ybtsk;
    }
    public void setYkjse(String[] ykjse)
    {
        this.ykjse = ykjse;
    }
    public void setYnse(String[] ynse)
    {
        this.ynse = ynse;
    }
    public void setYnssde(String[] ynssde)
    {
        this.ynssde = ynssde;
    }
    public String getYzbm()
    {
        return yzbm;
    }
    public void setYzbm(String yzbm)
    {
        this.yzbm = yzbm;
    }
    public void setZhrmb(String[] zhrmb)
    {
        this.zhrmb = zhrmb;
    }
    public void setZjhm(String zjhm)
    {
        this.zjhm = zjhm;
    }
    public String getZjhm()
    {
        return zjhm;
    }
    public String getZjlxdm()
    {
        return zjlxdm;
    }
    public void setZjlxdm(String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }
    public String getZydm()
    {
        return zydm;
    }
    public void setZydm(String zydm)
    {
        this.zydm = zydm;
    }
    public List getGsszsmList()
    {
        return gsszsmList;
    }
    public void setGsszsmList(List gsszsmList)
    {
        this.gsszsmList = gsszsmList;
    }
    public List getBzList()
    {
        return bzList;
    }
    public void setBzList(List bzList)
    {
        this.bzList = bzList;
    }
    public String getSfczbs()
    {
        return sfczbs;
    }
    public void setSfczbs(String sfczbs)
    {
        this.sfczbs = sfczbs;
    }
    public String getZffkce()
    {
        return zffkce;
    }
    public void setZffkce(String zffkce)
    {
        this.zffkce = zffkce;
    }
    public String getGjmc()
    {
        return gjmc;
    }
    public void setGjmc(String gjmc)
    {
        this.gjmc = gjmc;
    }
    public String getNsrmc()
    {
        return nsrmc;
    }
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
    public String getZjlxmc()
    {
        return zjlxmc;
    }
    public void setZjlxmc(String zjlxmc)
    {
        this.zjlxmc = zjlxmc;
    }
    public String getZymc()
    {
        return zymc;
    }
    public void setZymc(String zymc)
    {
        this.zymc = zymc;
    }
    public Zrrgrsdsz getZrrgrsdsz()
    {
        return zrrgrsdsz;
    }
    public void setZrrgrsdsz(Zrrgrsdsz zrrgrsdsz)
    {
        this.zrrgrsdsz = zrrgrsdsz;
    }
    public String[] getFdkce()
    {
        return fdkce;
    }
    public void setFdkce(String[] fdkce)
    {
        this.fdkce = fdkce;
    }
    public String getQxdm()
    {
        return qxdm;
    }
    public void setQxdm(String qxdm)
    {
        this.qxdm = qxdm;
    }

    //��String��BigDecimal
    private BigDecimal ToBigDecimal(String je)
    {
        if(je != null && !je.trim().equals(""))
        {
            return new BigDecimal(je);
        }
        else
        {
            return null;
        }
    }
    public String[] getSl()
    {
        return sl;
    }
    public void setSl(String[] sl)
    {
        this.sl = sl;
    }
    public String[] getSskcs()
    {
        return sskcs;
    }
    public void setSskcs(String[] sskcs)
    {
        this.sskcs = sskcs;
    }
    public String getFdfsmc()
    {
        return fdfsmc;
    }
    public void setFdfsmc(String fdfsmc)
    {
        this.fdfsmc = fdfsmc;
    }
    public String getFdfsdm()
    {
        return fdfsdm;
    }
    public void setFdfsdm(String fdfsdm)
    {
        this.fdfsdm = fdfsdm;
    }
    public String[] getZh()
    {
        return zh;
    }
    public void setZh(String[] zh)
    {
        this.zh = zh;
    }
    public String[] getSrermb()
    {
        return srermb;
    }
    public String[] getSdjsrq()
    {
        return sdjsrq;
    }
    public String[] getSdksrq()
    {
        return sdksrq;
    }
    public String[] getSzsmdm()
    {
        return szsmdm;
    }
    public String[] getYbtsk()
    {
        return ybtsk;
    }
    public String[] getYkjse()
    {
        return ykjse;
    }
    public String[] getYnse()
    {
        return ynse;
    }
    public String[] getYnssde()
    {
        return ynssde;
    }
    public String[] getJe()
    {
        return je;
    }
    public String[] getYjzgyjnsk()
    {
        return yjzgyjnsk;
    }
    public void setYjzgyjnsk(String[] yjzgyjnsk)
    {
        this.yjzgyjnsk = yjzgyjnsk;
    }
    public String[] getBzdm()
    {
        return bzdm;
    }
    public String[] getJfye()
    {
        return jfye;
    }
    public String[] getRmbhj()
    {
        return rmbhj;
    }
    public String[] getWhpj()
    {
        return whpj;
    }
    public String[] getZhrmb()
    {
        return zhrmb;
    }
    public List getZrrgrsdsmxList()
    {
        return zrrgrsdsmxList;
    }
    public void setZrrgrsdsmxList(List zrrgrsdsmxList)
    {
        this.zrrgrsdsmxList = zrrgrsdsmxList;
    }
    public String getYhdm()
    {
        return yhdm;
    }
    public void setYhdm(String yhdm)
    {
        this.yhdm = yhdm;
    }
    public List getYhList()
    {
        return yhList;
    }
    public void setYhList(List yhList)
    {
        this.yhList = yhList;
    }
    public String getYhmc()
    {
        return yhmc;
    }
    public void setYhmc(String yhmc)
    {
        this.yhmc = yhmc;
    }
    public String getYhzh()
    {
        return yhzh;
    }
    public void setYhzh(String yhzh)
    {
        this.yhzh = yhzh;
    }

}
