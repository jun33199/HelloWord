package com.syax.bjtax.shenbao.model.kjqysds;


/**
 * <p>Title: 北京地税核心征管系统－－扣缴企业所得税-明细信息</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class MXXXVO implements XMLVOInterface
{
	Map recordMap = new HashMap();
    /**
     * 备案登记序号（记录主键）
     */
	private String badjxh;
	
    /**
     * 合同编号
     */
	private String htbh;
    
    /**
     * 合同名称
     */
    private String htmc;
    
    /**
     * 合同签约日期
     */
    private String htqyrq;
    
    /**
     * 合同有效期
     */
    private String htyxq;
    
    /**
     * 合同金额
     */
    private String htje;
    
    /**
     * 填报日期
     */
    private String tbrq;
    
    /**
     * 审核状态
     */
    private String shzt;


    public MXXXVO()
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
        StringBuffer xmlstr = new StringBuffer();
        // 备案登记序号（记录主键）
        xmlstr.append(XMLBuildUtil.appendStringElement("badjxh", this.badjxh));
        // 合同编号
        xmlstr.append(XMLBuildUtil.appendStringElement("htbh", this.htbh));
        // 合同名称
        xmlstr.append(XMLBuildUtil.appendStringElement("htmc", this.htmc));
        // 合同签约日期
        xmlstr.append(XMLBuildUtil.appendStringElement("htqyrq", this.htqyrq));
        // 合同有效期
        xmlstr.append(XMLBuildUtil.appendStringElement("htyxq", this.htyxq));
        // 合同金额
        xmlstr.append(XMLBuildUtil.appendStringElement("htje", this.htje));
        // 填报日期
        xmlstr.append(XMLBuildUtil.appendStringElement("tbrq", this.tbrq));
        // 审核状态
        xmlstr.append(XMLBuildUtil.appendStringElement("shzt", this.shzt));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }

	public String getBadjxh() {
		return badjxh;
	}

	public String getHtbh() {
		return htbh;
	}

	public String getHtmc() {
		return htmc;
	}

	public String getHtqyrq() {
		return htqyrq;
	}

	public String getHtyxq() {
		return htyxq;
	}

	public String getHtje() {
		return htje;
	}

	public String getTbrq() {
		return tbrq;
	}

	public String getShzt() {
		return shzt;
	}

	public void setBadjxh(String badjxh) {
		this.badjxh = badjxh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public void setHtqyrq(String htqyrq) {
		this.htqyrq = htqyrq;
	}

	public void setHtyxq(String htyxq) {
		this.htyxq = htyxq;
	}

	public void setHtje(String htje) {
		this.htje = htje;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

    

}
