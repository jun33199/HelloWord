package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo;


/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author guoxh,2012-3-2
 * @version 1.0
 */
import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class FzjgNbhj2012VO implements XMLVOInterface
{
    /**
     * ��֧���������ܶ�
     */
    private String srehj = "";

    /**
     * ��֧���������ܶ�
     */
    private String gzehj = "";

    /**
     * ��֧�����ʲ��ܶ�
     */
    private String zcehj = "";

    /**
     * ��֧�����������
     */
    private String fpblhj = "";

    /**
     * ��֧��������˰��
     */
    private String fpsehj = "";


    public FzjgNbhj2012VO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<fzjghj>";
        xmlstr += toXMLChilds();
        xmlstr += "</fzjghj>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //��֧���������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("srehj", this.srehj);
        //��֧���������ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("gzehj", this.gzehj);
        //��֧�����ʲ��ܶ�
        xmlstr += XMLBuildUtil.appendStringElement("zcehj", this.zcehj);
        //��֧�����������
        xmlstr += XMLBuildUtil.appendStringElement("fpblhj", this.fpblhj);
        //��֧��������˰��
        xmlstr += XMLBuildUtil.appendStringElement("fpsehj", this.fpsehj);

        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

	public String getSrehj() {
		return srehj;
	}

	public void setSrehj(String srehj) {
		this.srehj = srehj;
	}

	public String getGzehj() {
		return gzehj;
	}

	public void setGzehj(String gzehj) {
		this.gzehj = gzehj;
	}

	public String getZcehj() {
		return zcehj;
	}

	public void setZcehj(String zcehj) {
		this.zcehj = zcehj;
	}

	public String getFpblhj() {
		return fpblhj;
	}

	public void setFpblhj(String fpblhj) {
		this.fpblhj = fpblhj;
	}

	public String getFpsehj() {
		return fpsehj;
	}

	public void setFpsehj(String fpsehj) {
		this.fpsehj = fpsehj;
	}

}
