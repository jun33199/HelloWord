package com.syax.bjtax.shenbao.model.kjqysds;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class BAHTXX implements XMLVOInterface
{
    /**
     * ��ͬ���
     */
    private String htbh;

    /**
     * ��ͬ����
     */
    private String htmc;

    /**
     * ǩԼ����
     */
    private String qyrq;

    /**
     * ��ͬ��Ч��
     */
    private String htyxq;

    /**
     * ��ͬ���
     */
    private String htje;

    /**
     * ����
     */
    private String bz;
    
    /**
     * ��������
     */
    private String bzmc;

    /**
     * ֧����Ŀ
     */
    private String zfxm;

    /**
     * �������
     */
    private String fkcs;

    /**
     * ������������
     */
    private String qtzlmc;

    public BAHTXX()
    {
        super();
    }

    /**
     * ������ת����XML�ַ���
     * @return String
     */
    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<htxx>");
        xmlstr.append(toXMLChilds());
        xmlstr.append("</htxx>");

        return xmlstr.toString();
    }

    /**
     * ���ڵ���Ϣת����xml�ַ���
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // ��ͬ���
        xmlstr.append(XMLBuildUtil.appendObjectElement("htbh", htbh));
        // ��ͬ����
        xmlstr.append(XMLBuildUtil.appendObjectElement("htmc", htmc));
        // ǩԼ����
        xmlstr.append(XMLBuildUtil.appendObjectElement("qyrq", qyrq));
        // ��ͬ��Ч��
        xmlstr.append(XMLBuildUtil.appendObjectElement("htyxq", htyxq));
        // ��ͬ���
        xmlstr.append(XMLBuildUtil.appendObjectElement("htje", htje));
        // ����
        xmlstr.append(XMLBuildUtil.appendObjectElement("bz", bz));
        // ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("bzmc", bzmc));
        // ֧����Ŀ
        xmlstr.append(XMLBuildUtil.appendObjectElement("zfxm", zfxm));
        // �������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fkcs", fkcs));
        // ������������
        xmlstr.append(XMLBuildUtil.appendObjectElement("qtzlmc", this.getQtzlmc()));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }


    public String getBz()
    {
        return bz;
    }

    public String getFkcs()
    {
        return fkcs;
    }

    public String getHtbh()
    {
        return htbh;
    }

    public String getHtje()
    {
        return htje;
    }

    public String getHtyxq()
    {
        return htyxq;
    }

    public String getQtzlmc()
    {
        if(null == qtzlmc || "".equals(qtzlmc))
        {
            qtzlmc = "��";
        }
        return qtzlmc;
    }

    public String getQyrq()
    {
        return qyrq;
    }

    public String getZfxm()
    {
        return zfxm;
    }

    public String getHtmc()
    {
        return htmc;
    }

    public void setZfxm(String zfxm)
    {
        this.zfxm = zfxm;
    }

    public void setQtzlmc(String qtzlmc)
    {
        this.qtzlmc = qtzlmc;
    }

    public void setQyrq(String qyrq)
    {
        this.qyrq = qyrq;
    }

    public void setHtyxq(String htyxq)
    {
        this.htyxq = htyxq;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public void setHtmc(String htmc)
    {
        this.htmc = htmc;
    }

    public void setFkcs(String fkcs)
    {
        this.fkcs = fkcs;
    }

    public void setHtje(String htje)
    {
        this.htje = htje;
    }

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

}
