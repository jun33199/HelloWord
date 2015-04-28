package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.kjqysds.*;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 框架VO与前台传输xml文件对应
 *
 */
public class BadjbVO extends YWRootVO implements XMLVOInterface
{
	/**
     * 备案登记序号（主键）
     */
    private String badjbxh;
    
    /**
     * 填报日期
     */
    private String tbrq;

    /**
     * 展示填报日期
     *    格式为yyyy年mm月dd日
     */
    private String tbrqShow;
    
    /**
     * 当前页
     */
    private String currentPage;
    
    /**
     * 修改标记
     *    0-未修改操作|1-修改操作
     */
    private String modifyFlag = "0";
    
    /**
     * 扣缴义务人信息
     */
    private KJYWRXX kjywrxx = new KJYWRXX();

    /**
     * 非居民企业信息
     */
    private FJMQYXX fjmqyxx = new FJMQYXX();

    /**
     * 合同信息
     */
    private BAHTXX htxx = new BAHTXX();
    
    /**
     * 明细信息
     */
    private BadjbListVO mxxx = new BadjbListVO();

    /**
     * 构造器
     */
    public BadjbVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 明细数据
     * @return Map
     */
    public Map getListTypeMap()
    {
        return null;
    }

    /**
     * 生成XML信息
     * @return String
     */
    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>");
        xmlstr.append(toXMLHead());
        xmlstr.append(toXMLChilds());
        xmlstr.append("</taxdoc>");

        return xmlstr.toString();
    }

    /**
     * 将子对象信息转换成XML字符串
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        xmlstr.append(XMLBuildUtil.appendObjectElement("badjbxh", badjbxh));
        xmlstr.append(XMLBuildUtil.appendObjectElement("tbrq", tbrq));
        xmlstr.append(XMLBuildUtil.appendObjectElement("tbrqShow", tbrqShow));
        xmlstr.append(XMLBuildUtil.appendObjectElement("currentPage", currentPage));
        xmlstr.append(XMLBuildUtil.appendObjectElement("modifyFlag", modifyFlag));
        xmlstr.append(kjywrxx.toXML());
        xmlstr.append(fjmqyxx.toXML());
        xmlstr.append(htxx.toXML());
        xmlstr.append(mxxx.toXML());

        return xmlstr.toString();
    }

    public FJMQYXX getFjmqyxx()
    {
        return fjmqyxx;
    }

    public BAHTXX getHtxx()
    {
        return htxx;
    }

    public KJYWRXX getKjywrxx()
    {
        return kjywrxx;
    }

    public String getTbrq()
    {
        return tbrq;
    }

    public String getTbrqShow()
    {
        return tbrqShow;
    }

    public void setKjywrxx(KJYWRXX kjywrxx)
    {
        this.kjywrxx = kjywrxx;
    }

    public void setHtxx(BAHTXX htxx)
    {
        this.htxx = htxx;
    }

    public void setFjmqyxx(FJMQYXX fjmqyxx)
    {
        this.fjmqyxx = fjmqyxx;
    }

    public void setTbrq(String tbrq)
    {
        this.tbrq = tbrq;
    }

    public void setTbrqShow(String tbrqShow)
    {
        this.tbrqShow = tbrqShow;
    }

	public BadjbListVO getMxxx() {
		return mxxx;
	}

	public void setMxxx(BadjbListVO mxxx) {
		this.mxxx = mxxx;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getBadjbxh() {
		return badjbxh;
	}

	public void setBadjbxh(String badjbxh) {
		this.badjbxh = badjbxh;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}


}
