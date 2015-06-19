package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.kjqysds.*;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * ���VO��ǰ̨����xml�ļ���Ӧ
 *
 */
public class BadjbVO extends YWRootVO implements XMLVOInterface
{
	/**
     * �����Ǽ���ţ�������
     */
    private String badjbxh;
    
    /**
     * �����
     */
    private String tbrq;

    /**
     * չʾ�����
     *    ��ʽΪyyyy��mm��dd��
     */
    private String tbrqShow;
    
    /**
     * ��ǰҳ
     */
    private String currentPage;
    
    /**
     * �޸ı��
     *    0-δ�޸Ĳ���|1-�޸Ĳ���
     */
    private String modifyFlag = "0";
    
    /**
     * �۽���������Ϣ
     */
    private KJYWRXX kjywrxx = new KJYWRXX();

    /**
     * �Ǿ�����ҵ��Ϣ
     */
    private FJMQYXX fjmqyxx = new FJMQYXX();

    /**
     * ��ͬ��Ϣ
     */
    private BAHTXX htxx = new BAHTXX();
    
    /**
     * ��ϸ��Ϣ
     */
    private BadjbListVO mxxx = new BadjbListVO();

    /**
     * ������
     */
    public BadjbVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * ��ϸ����
     * @return Map
     */
    public Map getListTypeMap()
    {
        return null;
    }

    /**
     * ����XML��Ϣ
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
     * ���Ӷ�����Ϣת����XML�ַ���
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
