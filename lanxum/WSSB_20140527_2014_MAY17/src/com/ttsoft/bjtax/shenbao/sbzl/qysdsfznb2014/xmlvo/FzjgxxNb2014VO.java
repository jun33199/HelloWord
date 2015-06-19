package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;


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

public class FzjgxxNb2014VO implements XMLVOInterface
{
    /**
     * ����
     */
    private String index = "";

    /**
     * ��֧������˰��ʶ���
     */
    private String nsrsbh = "";

    /**
     * ��֧��������
     */
    private String nsrmc = "";

    /**
     * ��֧���������ܶ�
     */
    private String srze = "";

    /**
     * ��֧���������ܶ�
     */
    private String gzze = "";

    /**
     * ��֧�����ʲ��ܶ�
     */
    private String zcze = "";

    /**
     * ��֧�����ϼƽ��
     */
    private String hj = "";

    /**
     * ��֧�����������
     */
    private String fpbl = "";

    /**
     * ��֧��������˰��
     */
    private String fpse = "";


    public FzjgxxNb2014VO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<mxxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</mxxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //����
        xmlstr += XMLBuildUtil.appendStringElement("index", this.index);
        //��֧������˰��ʶ���
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh", this.nsrsbh);
        //��֧��������
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc);
        //��֧���������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("srze", this.srze);
        //��֧���������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("gzze", this.gzze);
        //��֧�����ʲ��ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("zcze", this.zcze);
        //��֧�����������
        xmlstr += XMLBuildUtil.appendStringElement("fpbl", this.fpbl);
        //��֧��������˰��
        xmlstr += XMLBuildUtil.appendStringElement("fpse", this.fpse);

        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String getFpbl()
    {
        return fpbl;
    }

    public String getFpse()
    {
        return fpse;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
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

    public String getIndex()
    {
        return index;
    }

    public void setFpbl(String fpbl)
    {
        this.fpbl = fpbl;
    }

    public void setFpse(String fpse)
    {
        this.fpse = fpse;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setIndex(String index)
    {
        this.index = index;
    }

}
