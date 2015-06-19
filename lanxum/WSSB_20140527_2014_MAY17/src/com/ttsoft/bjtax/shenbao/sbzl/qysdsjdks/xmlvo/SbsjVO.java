package com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjVO implements XMLVOInterface
{
    /**
     * �����ܶ�
     */
    private String srze = "";
    /**
     * �����ܶ�(Ӧ��˰���ö
     */
    private String lrze = "";
    /**
     * �ֲ���ǰ��ȿ���
     */
    private String mbyqndks = "";
    /**
     * �����������ܶ�
     */
    private String bkhlrze = "";

    /**
     * Ӧ������˰��
     */
    private String ynsdse = "";
    /**
     * �ڳ�δ������˰��
     */
    private String qcwjsdse = "";

    /**
     * ��������˰��
     */
    private String jmsdse = "";

    /**
     * �鲹��ǰ���˰��
     */
    private String cbyqndse = "";

    /**
     * ʵ���ѽ���˰����˰��
     */
    private String sjyjnssdse = "";

    /**
     * �����걨�ӽ�����˰��
     */
    private String bqyjsdse = "";

    /**
     * ʵ��Ӧ��(��)����˰��
     */
    private String sjybsdse = "";


    /**
     * ����˰��
     */
    private String sl = "";

    public SbsjVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<sbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("srze",srze);
        xmlstr += XMLBuildUtil.appendStringElement("lrze",lrze);
        xmlstr += XMLBuildUtil.appendStringElement("mbyqndks",mbyqndks);
        xmlstr += XMLBuildUtil.appendStringElement("bkhlrze",bkhlrze);
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse",ynsdse);
        xmlstr += XMLBuildUtil.appendStringElement("qcwjsdse",qcwjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("jmsdse",jmsdse);
        xmlstr += XMLBuildUtil.appendStringElement("cbyqndse",cbyqndse);
        xmlstr += XMLBuildUtil.appendStringElement("sjyjnssdse",sjyjnssdse);
        xmlstr += XMLBuildUtil.appendStringElement("bqyjsdse",bqyjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("sjybsdse",sjybsdse);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        return xmlstr;
    }
    
    public String getBkhlrze()
    {
        return bkhlrze;
    }
    public void setBkhlrze(String bkhlrze)
    {
        this.bkhlrze = bkhlrze;
    }
    public String getBqyjsdse()
    {
        return bqyjsdse;
    }
    public void setBqyjsdse(String bqyjsdse)
    {
        this.bqyjsdse = bqyjsdse;
    }
    public String getCbyqndse()
    {
        return cbyqndse;
    }
    public void setCbyqndse(String cbyqndse)
    {
        this.cbyqndse = cbyqndse;
    }
    public String getJmsdse()
    {
        return jmsdse;
    }
    public void setJmsdse(String jmsdse)
    {
        this.jmsdse = jmsdse;
    }
    public String getLrze()
    {
        return lrze;
    }
    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }
    public String getMbyqndks()
    {
        return mbyqndks;
    }
    public void setMbyqndks(String mbyqndks)
    {
        this.mbyqndks = mbyqndks;
    }
    public String getQcwjsdse()
    {
        return qcwjsdse;
    }
    public void setQcwjsdse(String qcwjsdse)
    {
        this.qcwjsdse = qcwjsdse;
    }
    public String getSjybsdse()
    {
        return sjybsdse;
    }
    public void setSjybsdse(String sjybsdse)
    {
        this.sjybsdse = sjybsdse;
    }
    public String getSjyjnssdse()
    {
        return sjyjnssdse;
    }
    public void setSjyjnssdse(String sjyjnssdse)
    {
        this.sjyjnssdse = sjyjnssdse;
    }
    public String getSl()
    {
        return sl;
    }
    public void setSl(String sl)
    {
        this.sl = sl;
    }
    public String getSrze()
    {
        return srze;
    }
    public void setSrze(String srze)
    {
        this.srze = srze;
    }
    public String getYnsdse()
    {
        return ynsdse;
    }
    public void setYnsdse(String ynsdse)
    {
        this.ynsdse = ynsdse;
    }

}
