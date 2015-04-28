package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 汇总纳税分支机构分配表xmlVO</p>
 *
 * <p>Description: 生成xml数据</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.bjtax.ca.vo.*;
import com.syax.common.xml.*;

public class ZfjgqysdsjbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * 申报信息
     */
    private Sbxx2012VO sbxx = new Sbxx2012VO();

    /**
     * 总机构信息
     */
    private Zjgxx2012VO zjgxx = new Zjgxx2012VO();

    /**
     * 分支机构信息
     */
    private FzjgList2012VO fzjgxx = new FzjgList2012VO();
    private Fzjghj2012VO fzjghj = new Fzjghj2012VO();

    public ZfjgqysdsjbVO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += sbxx.toXML();
        xmlstr += zjgxx.toXML();
        xmlstr += fzjgxx.toXML();
        //demo info
//        fzjghj.setSrehj("123.01");
//        fzjghj.setGzehj("123.02");
//        fzjghj.setZcehj("123.03");
//        fzjghj.setFpblhj("30");
//        fzjghj.setFpsehj("123.05");
        xmlstr += fzjghj.toXML();
        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public FzjgList2012VO getFzjgxx()
    {
        return fzjgxx;
    }

    public Sbxx2012VO getSbxx()
    {
        return sbxx;
    }

    public Zjgxx2012VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgList2012VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(Zjgxx2012VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(Sbxx2012VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public Fzjghj2012VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(Fzjghj2012VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
