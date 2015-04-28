package com.syax.bjtax.shenbao.model.kjqysds;

/**
 * 非居民企业基本信息
 * @author tum
 *
 */
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import java.util.Map;


public class FJMQYXX implements XMLVOInterface
{
	/**
	 * 非居民企业国别
	 */
	private String fjmgb;
	/**
	 * 非居民企业国别名称
	 */
	private String fjmgbmc;
	/**
	 * 非居民企业国家或地区代码
	 */
	private String fjmgjdq;
    /**
     * 非居民企业国家或地区名称
     */
    private String fjmgjdqmc;
	/**
	 * 非居民企业中文名称
	 */
	private String fjmmc_cn;
	/**
	 * 非居民企业英文名称
	 */
	private String fjmmc_en;
	/**
	 * 非居民企业居民国中文地址
	 */
	private String fjmdz_cn;
	/**
	 * 非居民企业居民国英文地址
	 */
	private String fjmdz_en;
	/**
	 * 非居民企业财务负责人
	 */
	private String fjmcwfzr;
	/**
	 * 非居民企业联系人
	 */
	private String fjmlxr;
	/**
	 * 非居民企业联系电话
	 */
	private String fjmlxdh;
	/**
	 * 非居民企业传真
	 */
	private String fjmczhm;

    public FJMQYXX()
    {
        super();
	}

    /**
     * 将对象转换成XML字符串
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
     * 将节点信息转换成xml字符串
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // 非居民企业国别
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgb", fjmgb == null ? "" : fjmgb));
        // 非居民企业国别名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgbmc", fjmgbmc == null ? "" : fjmgbmc));
        // 非居民企业国家或地区代码
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgjdq", fjmgjdq == null ? "" : fjmgjdq));
        // 非居民企业国家或地区名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmgjdqmc", fjmgjdqmc == null ? "" : fjmgjdqmc));
        // 非居民企业中文名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmmc_cn", fjmmc_cn == null ? "" : fjmmc_cn));
        // 非居民企业英文名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmmc_en", fjmmc_en == null ? "" : fjmmc_en));
        // 非居民企业居民国中文地址
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmdz_cn", fjmdz_cn == null ? "" : fjmdz_cn));
        // 非居民企业居民国英文地址
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmdz_en", fjmdz_en == null ? "" : fjmdz_en));
        // 非居民企业财务负责人
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmcwfzr", fjmcwfzr == null ? "" : fjmcwfzr));
        // 非居民企业联系人
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmlxr", fjmlxr == null ? "" : fjmlxr));
        // 非居民企业联系电话
        xmlstr.append(XMLBuildUtil.appendObjectElement("fjmlxdh", fjmlxdh == null ? "" : fjmlxdh));
        // 非居民企业传真
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
