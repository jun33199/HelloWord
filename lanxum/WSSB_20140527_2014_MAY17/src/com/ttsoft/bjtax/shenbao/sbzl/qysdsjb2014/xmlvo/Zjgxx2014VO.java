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


public class Zjgxx2014VO implements XMLVOInterface
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
    private String ynsdse = "";

    /**
     * �ܻ��������ܶ�
     */
    private String gzze = "";
    private String ftsdse = "";

    /**
     * �ܻ����ʲ��ܶ�
     */
    private String zcze = "";
    private String fpsdse = "";

    /**
     * �ܻ����ϼƽ��
     */
    private String hj = "";

    /**
     * �ܻ�����֧������̯˰��
     */
    private String ftse = "";
    private String fzjgftse = "";

    public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getFtsdse() {
		return ftsdse;
	}

	public void setFtsdse(String ftsdse) {
		this.ftsdse = ftsdse;
	}

	public String getFpsdse() {
		return fpsdse;
	}

	public void setFpsdse(String fpsdse) {
		this.fpsdse = fpsdse;
	}

	public String getFzjgftse() {
		return fzjgftse;
	}

	public void setFzjgftse(String fzjgftse) {
		this.fzjgftse = fzjgftse;
	}

	public Zjgxx2014VO()
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
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse", this.ynsdse);
        //�ܻ��������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("ftsdse", this.ftsdse);
        //�ܻ����ʲ��ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("fpsdse", this.fpsdse);
        //�ܻ�����֧������̯˰��
        xmlstr += XMLBuildUtil.appendStringElement("fzjgftse", this.fzjgftse);
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
