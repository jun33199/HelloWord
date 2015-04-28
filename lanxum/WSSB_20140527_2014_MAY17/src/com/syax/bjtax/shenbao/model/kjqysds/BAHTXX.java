package com.syax.bjtax.shenbao.model.kjqysds;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
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
     * 合同编号
     */
    private String htbh;

    /**
     * 合同名称
     */
    private String htmc;

    /**
     * 签约日期
     */
    private String qyrq;

    /**
     * 合同有效期
     */
    private String htyxq;

    /**
     * 合同金额
     */
    private String htje;

    /**
     * 币种
     */
    private String bz;
    
    /**
     * 币种名称
     */
    private String bzmc;

    /**
     * 支付项目
     */
    private String zfxm;

    /**
     * 付款次数
     */
    private String fkcs;

    /**
     * 其它资料名称
     */
    private String qtzlmc;

    public BAHTXX()
    {
        super();
    }

    /**
     * 将对象转换成XML字符串
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
     * 将节点信息转换成xml字符串
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // 合同编号
        xmlstr.append(XMLBuildUtil.appendObjectElement("htbh", htbh));
        // 合同名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("htmc", htmc));
        // 签约日期
        xmlstr.append(XMLBuildUtil.appendObjectElement("qyrq", qyrq));
        // 合同有效期
        xmlstr.append(XMLBuildUtil.appendObjectElement("htyxq", htyxq));
        // 合同金额
        xmlstr.append(XMLBuildUtil.appendObjectElement("htje", htje));
        // 币种
        xmlstr.append(XMLBuildUtil.appendObjectElement("bz", bz));
        // 币种名称
        xmlstr.append(XMLBuildUtil.appendObjectElement("bzmc", bzmc));
        // 支付项目
        xmlstr.append(XMLBuildUtil.appendObjectElement("zfxm", zfxm));
        // 付款次数
        xmlstr.append(XMLBuildUtil.appendObjectElement("fkcs", fkcs));
        // 其它资料名称
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
            qtzlmc = "无";
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
