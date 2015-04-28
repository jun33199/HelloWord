package com.syax.bjtax.shenbao.model.kjqysds;

/**
 * �Ǿ�����ҵ������Ϣ
 * @author tum
 *
 */
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import java.util.Map;


public class FJMQYXX implements XMLVOInterface
{
	/**
	 * �Ǿ�����ҵ����
	 */
	private String fjmgb;
	/**
	 * �Ǿ�����ҵ��������
	 */
	private String fjmgbmc;
	/**
	 * �Ǿ�����ҵ���һ��������
	 */
	private String fjmgjdq;
    /**
     * �Ǿ�����ҵ���һ��������
     */
    private String fjmgjdqmc;
	/**
	 * �Ǿ�����ҵ��������
	 */
	private String fjmmc_cn;
	/**
	 * �Ǿ�����ҵӢ������
	 */
	private String fjmmc_en;
	/**
	 * �Ǿ�����ҵ��������ĵ�ַ
	 */
	private String fjmdz_cn;
	/**
	 * �Ǿ�����ҵ�����Ӣ�ĵ�ַ
	 */
	private String fjmdz_en;
	/**
	 * �Ǿ�����ҵ��������
	 */
	private String fjmcwfzr;
	/**
	 * �Ǿ�����ҵ��ϵ��
	 */
	private String fjmlxr;
	/**
	 * �Ǿ�����ҵ��ϵ�绰
	 */
	private String fjmlxdh;
	/**
	 * �Ǿ�����ҵ����
	 */
	private String fjmczhm;

    public FJMQYXX()
    {
        super();
	}

    /**
     * ������ת����XML�ַ���
     * @return String
     */
    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<fjmqyxx>");
        xmlstr.append(toXMLChilds());
        xmlstr.append("</fjmqyxx>");

        return xmlstr.toString();
    }

    /**
     * ���ڵ���Ϣת����xml�ַ���
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // �Ǿ�����ҵ����
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgb", fjmgb == null ? "" : fjmgb));
        // �Ǿ�����ҵ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgbmc", fjmgbmc == null ? "" : fjmgbmc));
        // �Ǿ�����ҵ���һ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgjdq", fjmgjdq == null ? "" : fjmgjdq));
        // �Ǿ�����ҵ���һ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgjdqmc", fjmgjdqmc == null ? "" : fjmgjdqmc));
        // �Ǿ�����ҵ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmmc_cn", fjmmc_cn == null ? "" : fjmmc_cn));
        // �Ǿ�����ҵӢ������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmmc_en", fjmmc_en == null ? "" : fjmmc_en));
        // �Ǿ�����ҵ��������ĵ�ַ
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmdz_cn", fjmdz_cn == null ? "" : fjmdz_cn));
        // �Ǿ�����ҵ�����Ӣ�ĵ�ַ
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmdz_en", fjmdz_en == null ? "" : fjmdz_en));
        // �Ǿ�����ҵ��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmcwfzr", fjmcwfzr == null ? "" : fjmcwfzr));
        // �Ǿ�����ҵ��ϵ��
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmlxr", fjmlxr == null ? "" : fjmlxr));
        // �Ǿ�����ҵ��ϵ�绰
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmlxdh", fjmlxdh == null ? "" : fjmlxdh));
        // �Ǿ�����ҵ����
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmczhm", fjmczhm == null ? "" : fjmczhm));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }

	public String getFjmgb() {
		return fjmgb;
	}
	public void setFjmgb(String fjmgb) {
		this.fjmgb = fjmgb;
	}
	public String getFjmgjdq() {
		return fjmgjdq;
	}
	public void setFjmgjdq(String fjmgjdq) {
		this.fjmgjdq = fjmgjdq;
	}
	public String getFjmmc_cn() {
		return fjmmc_cn;
	}
	public void setFjmmc_cn(String fjmmc_cn) {
		this.fjmmc_cn = fjmmc_cn;
	}
	public String getFjmmc_en() {
		return fjmmc_en;
	}
	public void setFjmmc_en(String fjmmc_en) {
		this.fjmmc_en = fjmmc_en;
	}
	public String getFjmdz_cn() {
		return fjmdz_cn;
	}
	public void setFjmdz_cn(String fjmdz_cn) {
		this.fjmdz_cn = fjmdz_cn;
	}
	public String getFjmdz_en() {
		return fjmdz_en;
	}
	public void setFjmdz_en(String fjmdz_en) {
		this.fjmdz_en = fjmdz_en;
	}
	public String getFjmcwfzr() {
		return fjmcwfzr;
	}
	public void setFjmcwfzr(String fjmcwfzr) {
		this.fjmcwfzr = fjmcwfzr;
	}
	public String getFjmlxr() {
		return fjmlxr;
	}
	public void setFjmlxr(String fjmlxr) {
		this.fjmlxr = fjmlxr;
	}
	public String getFjmlxdh() {
		return fjmlxdh;
	}
	public void setFjmlxdh(String fjmlxdh) {
		this.fjmlxdh = fjmlxdh;
	}
	public String getFjmczhm() {
		return fjmczhm;
	}

    public String getFjmgjdqmc()
    {
        return fjmgjdqmc;
    }

    public void setFjmczhm(String fjmczhm) {
		this.fjmczhm = fjmczhm;
	}

    public void setFjmgjdqmc(String fjmgjdqmc)
    {
        this.fjmgjdqmc = fjmgjdqmc;
    }

	public String getFjmgbmc() {
		return fjmgbmc;
	}

	public void setFjmgbmc(String fjmgbmc) {
		this.fjmgbmc = fjmgbmc;
	}

}
