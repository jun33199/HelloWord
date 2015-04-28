package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class Sbxx2014VO implements XMLVOInterface
{
    /**
     * ��ʽ����
     */
    private String fsdm = "";

    /**
     * ����
     */
    private String jd = "";

    /**
     * ���
     */
    private String nd = "";

    /**
     * �걨����
     */
    private String sbrq = "";

    /**
     * չ���걨����
     */
    private String sbrqShow = "";

    /**
     * ˰��������ʼ����
     */
    private String skssksrq = "";

    /**
     * ˰��������������
     */
    private String skssjsrq = "";

    /**
     * ���������Ч����
     */
    private String fpblyxqq = "";

    /**
     * ���������Ч��ֹ
     */
    private String fpblyxqz = "";


    public Sbxx2014VO()
    {
        super();
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String toXML()
    {
        String xmlstr = "<sbxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</sbxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //��ʽ����
        xmlstr += XMLBuildUtil.appendStringElement("fsdm", this.fsdm);
        //����
        xmlstr += XMLBuildUtil.appendStringElement("jd", this.jd);
        //���
        xmlstr += XMLBuildUtil.appendStringElement("nd", this.nd);
        //�걨����
        xmlstr += XMLBuildUtil.appendStringElement("sbrq", this.sbrq);
        //չ���걨����
        xmlstr += XMLBuildUtil.appendStringElement("sbrqshow", this.sbrqShow);
        //˰��������ʼ����
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq", this.skssksrq);
        //˰��������������
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq", this.skssjsrq);
        //���������Ч����
        xmlstr += XMLBuildUtil.appendStringElement("fpblyxqq", this.fpblyxqq);
        //���������Ч��ֹ
        xmlstr += XMLBuildUtil.appendStringElement("fpblyxqz", this.fpblyxqz);
        return xmlstr;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public String getJd()
    {
        return jd;
    }

    public String getNd()
    {
        return nd;
    }

    public String getSbrq()
    {
        return sbrq;
    }

    public String getSbrqShow()
    {
        return sbrqShow;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSbrqShow(String sbrqShow)
    {
        this.sbrqShow = sbrqShow;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }
}
