package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo;


/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- ������˰��֧���������BO</p>
 *
 * <p>Description: ����ejb���������ݶ���</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class ZfjgqysdsjbBO implements Serializable
{
    /**
     * �ܻ������������
     */
    private String jsjdm = "";

    /**
     * �ܻ�����˰��ʶ���
     */
    private String nsrsbh = "";

    /**
     * �ܻ�����˰������
     */
    private String nsrmc = "";

    /**
     * �걨����
     */
    private Timestamp sbrq;

    /**
     * ҳ��չʾ���걨����
     */
    private String sbrqshow = "";

    /**
     * ˰��������ʼ����
     */
    private Timestamp skssksrq;

    /**
     * ˰��������������
     */
    private Timestamp skssjsrq;

    /**
     * ����
     */
    private String jd = "";

    /**
     * �������
     */
    private String nd = "";

    /**
     * �걨��Ϣ
     */
    private HashMap sbsj = new HashMap();

    /**
     * ��ʽ����
     */
    private String fsdm = "";

    /**
     * ���������Ч����
     */
    private String fpblyxqq = "";

    /**
     * ���������Ч��ֹ
     */
    private String fpblyxqz = "";

    /**
     * ����������
     */
    private String bbqlx = "";

    /**
     * ��̯˰��
     */
    private String ftse = "";

	public ZfjgqysdsjbBO()
    {
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getNsrsbh()
    {
        return nsrsbh;
    }

    public Timestamp getSbrq()
    {
        return sbrq;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public String getSbrqshow()
    {
        return sbrqshow;
    }

    public String getJd()
    {
        return jd;
    }

    public String getNd()
    {
        return nd;
    }

    public HashMap getSbsj()
    {
        return sbsj;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getBbqlx()
    {
        return bbqlx;
    }

    public String getFtse()
    {
        return ftse;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setSbrq(Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSbrqshow(String sbrqshow)
    {
        this.sbrqshow = sbrqshow;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public void setSbsj(HashMap sbsj)
    {
        this.sbsj = sbsj;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setBbqlx(String bbqlx)
    {
        this.bbqlx = bbqlx;
    }

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
    }

}
