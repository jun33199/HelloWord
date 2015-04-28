package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

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
    private Sbxx2014VO sbxx = new Sbxx2014VO();

    /**
     * 总机构信息
     */
    private Zjgxx2014VO zjgxx = new Zjgxx2014VO();

    /**
     * 分支机构信息
     */
    private FzjgList2014VO fzjgxx = new FzjgList2014VO();
    private Fzjghj2014VO fzjghj = new Fzjghj2014VO();

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

    public FzjgList2014VO getFzjgxx()
    {
        return fzjgxx;
    }

    public Sbxx2014VO getSbxx()
    {
        return sbxx;
    }

    public Zjgxx2014VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgList2014VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(Zjgxx2014VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(Sbxx2014VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public Fzjghj2014VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(Fzjghj2014VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
