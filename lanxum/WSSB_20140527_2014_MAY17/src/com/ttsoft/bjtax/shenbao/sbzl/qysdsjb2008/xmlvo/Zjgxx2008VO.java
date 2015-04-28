package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;


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


public class Zjgxx2008VO implements XMLVOInterface
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
     * �ܻ�������
     */
    private String nsrmc = "";

    /**
     * �ܻ��������ܶ�
     */
    private String srze = "";

    /**
     * �ܻ��������ܶ�
     */
    private String gzze = "";

    /**
     * �ܻ����ʲ��ܶ�
     */
    private String zcze = "";

    /**
     * �ܻ����ϼƽ��
     */
    private String hj = "";

    /**
     * �ܻ�����֧������̯˰��
     */
    private String ftse = "";

    public Zjgxx2008VO()
    {
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String toXML()
    {
        String xmlstr = "<zjgxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</zjgxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //�ܻ������������
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm);
        //�ܻ�����˰��ʶ���
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh", this.nsrsbh);
        //�ܻ�������
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc);
        //�ܻ��������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("srze", this.srze);
        //�ܻ��������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("gzze", this.gzze);
        //�ܻ����ʲ��ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("zcze", this.zcze);
        //�ܻ����ϼƽ��
        xmlstr += XMLBuildUtil.appendStringElement("hj", this.hj);
        //�ܻ�����֧������̯˰��
        xmlstr += XMLBuildUtil.appendStringElement("ftse", this.ftse);
        return xmlstr;
    }

    public String getFtse()
    {
        return ftse;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
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

    public String getSrze()
    {
        return srze;
    }

    public String getZcze()
    {
        return zcze;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

}
