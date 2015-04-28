package com.syax.bjtax.shenbao.model.kjqysds;


/**
 * 扣缴义务人基本信息
 * @author tum
 *
 */

import com.syax.common.xml.XMLVOInterface;
import java.util.Map;
import com.syax.common.xml.util.XMLBuildUtil;


public class KJYWRXX implements XMLVOInterface{

	/**
	 * 计算机代码
	 */
	private String kjrjsjdm;
	/**
	 * 扣缴义务人纳税识别号
	 */
	private String kjrnssbh;
	/**
	 * 扣缴人中文名称
	 */
	private String kjrmc_cn;
	/**
	 * 扣缴人英文名称
	 */
	private String kjrmc_en;
	/**
	 * 扣缴人中文地址
	 */
	private String kjrdz_cn;
	/**
	 * 扣缴人财务负责人
	 */
	private String kjrcwfzr;
	/**
	 * 联系人
	 */
	private String kjrlxr;
	/**
	 * 联系电话
	 */
	private String kjrlxdh;
	/**
	 * 扣缴人邮政编码
	 */
	private String kjryzbm;
	/**
	 * 扣缴人传真号码
	 */
	private String kjrczhm;


    public KJYWRXX()
    {
        super();
    }

    /**
     * 将对象转换成XML字符串
     * @return String
     */
    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<kjywrxx>");
        xmlstr.append(toXMLChilds());
        xmlstr.append("</kjywrxx>");

        return xmlstr.toString();
    }

    /**
     * 将节点信息转换成xml字符串
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // 扣缴义务人计算机代码
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrjsjdm", kjrjsjdm));
        //  扣缴义务人纳税识别号
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrnssbh", kjrnssbh));
        // 扣缴人中文名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrmc_cn", kjrmc_cn));
        // 扣缴人英文名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrmc_en", kjrmc_en));
        // 扣缴人中文地址
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrdz_cn", kjrdz_cn));
        // 扣缴人财务负责人
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrcwfzr", kjrcwfzr));
        // 联系人
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrlxr", kjrlxr));
        // 联系电话
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrlxdh", kjrlxdh));
        // 扣缴人邮政编码
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjryzbm", kjryzbm));
        // 扣缴人传真号码
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrczhm", kjrczhm));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }


	public String getKjrjsjdm() {
		return kjrjsjdm;
	}
	public void setKjrjsjdm(String kjrjsjdm) {
		this.kjrjsjdm = kjrjsjdm;
	}
	public String getKjrnssbh() {
		return kjrnssbh;
	}
	public void setKjrnssbh(String kjrnssbh) {
		this.kjrnssbh = kjrnssbh;
	}

	public String getKjrcwfzr() {
		return kjrcwfzr;
	}
	public void setKjrcwfzr(String kjrcwfzr) {
		this.kjrcwfzr = kjrcwfzr;
	}
	public String getKjrlxr() {
		return kjrlxr;
	}
	public void setKjrlxr(String kjrlxr) {
		this.kjrlxr = kjrlxr;
	}
	public String getKjrlxdh() {
		return kjrlxdh;
	}
	public void setKjrlxdh(String kjrlxdh) {
		this.kjrlxdh = kjrlxdh;
	}
	public String getKjryzbm() {
		return kjryzbm;
	}
	public void setKjryzbm(String kjryzbm) {
		this.kjryzbm = kjryzbm;
	}
	public String getKjrczhm() {
		return kjrczhm;
	}
	public void setKjrczhm(String kjrczhm) {
		this.kjrczhm = kjrczhm;
	}

    public String getKjrdz_cn()
    {
        return kjrdz_cn;
    }

    public String getKjrmc_cn()
    {
        return kjrmc_cn;
    }

    public String getKjrmc_en()
    {
        return kjrmc_en;
    }

    public void setKjrmc_en(String kjrmc_en)
    {
        this.kjrmc_en = kjrmc_en;
    }

    public void setKjrmc_cn(String kjrmc_cn)
    {
        this.kjrmc_cn = kjrmc_cn;
    }

    public void setKjrdz_cn(String kjrdz_cn)
    {
        this.kjrdz_cn = kjrdz_cn;
    }
}
