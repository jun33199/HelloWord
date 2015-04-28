package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;

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

public class ZfjgqysdsNbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * 申报信息
     */
    private SbxxNb2014VO sbxx = new SbxxNb2014VO();

    /**
     * 总机构信息
     */
    private ZjgxxNb2014VO zjgxx = new ZjgxxNb2014VO();

    /**
     * 分支机构信息
     */
    private FzjgNbList2014VO fzjgxx = new FzjgNbList2014VO();
    private FzjgNbhj2014VO fzjghj = new FzjgNbhj2014VO();

    public ZfjgqysdsNbVO()
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

    public FzjgNbList2014VO getFzjgxx()
    {
        return fzjgxx;
    }

    public SbxxNb2014VO getSbxx()
    {
        return sbxx;
    }

    public ZjgxxNb2014VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgNbList2014VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(ZjgxxNb2014VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(SbxxNb2014VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public FzjgNbhj2014VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(FzjgNbhj2014VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
