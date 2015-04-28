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
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 自然人申报Form</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class ZrrsbForm extends BaseForm
{
    // 主表数据
    private Zrrgrsdsz zrrgrsdsz;

    // 明细数据List
    private List zrrgrsdsmxList;

    // 个人所得税目List
    private List gsszsmList = new ArrayList();

    // 币种List
    private List bzList = new ArrayList();

    // 银行List
    private List yhList = new ArrayList();

    private String jsjdm; //计算机代码
    private String nsrmc; //纳税人名称
    private Timestamp sbrq; //申报日期
    private String zjlxdm; //证件类型代码
    private String zjlxmc; //证件类型名称
    private String zjhm; //证件号码
    private String gjdm; //国籍代码
    private String gjmc = ""; //国籍名称
    private Timestamp cjsj; //创建时间
    private String cjr; //创建人
    private Timestamp skssksrq; //税款所属开始日期
    private Timestamp skssjsrq; //税款所属结束日期
    private Timestamp dhrq; //抵华日期
    private String jnzz; //境内住址
    private String txdz; //通讯地址
    private String yzbm; //邮政编码
    private String zydm; //职业代码
    private String zymc; //职业名称
    private String dh; //电话
    private String fwdw; //服务单位
    private String fwdd; //服务地点
    private String dwfdbl; //单位负担比例
    private String sfczbs; //是否常驻标识
    private String zffkce; //租房费扣除额
    private String fdfsdm; //负担方式代码
    private String fdfsmc; //负担方式名称
    private String qxdm; //区县代码
    private String yhmc; //银行名称
    private String yhdm; //银行代码
    private String yhzh; //银行帐号

    // 明细数据
    private String[] szsmdm; //税种税目代码
    private String[] sdksrq; //所得开始日期
    private String[] sdjsrq; //所得结束日期
    private String[] srermb; //收入额人民币
    private String[] bzdm; //币种代码
    private String[] je; //金额
    private String[] whpj; //外汇牌价
    private String[] zhrmb; //折合人民币
    private String[] rmbhj; //人民币合计
    private String[] jfye; //减费用额
    private String[] ynssde; //应纳税所得额
    private String[] sl; //税率
    private String[] sskcs; //速算扣除数
    private String[] ynse; //应纳税额
    private String[] ykjse; //已扣缴税额
    private String[] ybtsk; //应补退税款
    private String[] zh;  //折和
    private String[] yjzgyjnsk; //原居住国税款
    private String[] fdkce; //法定扣除额

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


    /**
     * 取得自然人明细数据List
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
//            throw new ApplicationException("您提交了错误的明细数据！请检查后重新申报！");
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

    //从String到BigDecimal
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
