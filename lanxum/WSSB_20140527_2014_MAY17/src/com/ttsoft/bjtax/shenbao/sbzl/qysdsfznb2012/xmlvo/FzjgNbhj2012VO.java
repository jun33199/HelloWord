package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo;


/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
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
     * 分支机构收入总额
     */
    private String srehj = "";

    /**
     * 分支机构工资总额
     */
    private String gzehj = "";

    /**
     * 分支机构资产总额
     */
    private String zcehj = "";

    /**
     * 分支机构分配比例
     */
    private String fpblhj = "";

    /**
     * 分支机构分配税额
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
        //分支机构收入总额
        xmlstr += XMLBuildUtil.appendStringElement("srehj", this.srehj);
        //分支机构工资总额
        xmlstr += XMLBuildUtil.appendStringElement("gzehj", this.gzehj);
        //分支机构资产总额
        xmlstr += XMLBuildUtil.appendStringElement("zcehj", this.zcehj);
        //分支机构分配比例
        xmlstr += XMLBuildUtil.appendStringElement("fpblhj", this.fpblhj);
        //分支机构分配税额
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
