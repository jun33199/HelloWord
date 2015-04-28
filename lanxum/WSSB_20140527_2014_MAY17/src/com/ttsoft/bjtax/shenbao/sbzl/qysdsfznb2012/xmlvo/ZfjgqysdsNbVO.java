package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo;

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
    private SbxxNb2012VO sbxx = new SbxxNb2012VO();

    /**
     * 总机构信息
     */
    private ZjgxxNb2012VO zjgxx = new ZjgxxNb2012VO();

    /**
     * 分支机构信息
     */
    private FzjgNbList2012VO fzjgxx = new FzjgNbList2012VO();
    private FzjgNbhj2012VO fzjghj = new FzjgNbhj2012VO();

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

    public FzjgNbList2012VO getFzjgxx()
    {
        return fzjgxx;
    }

    public SbxxNb2012VO getSbxx()
    {
        return sbxx;
    }

    public ZjgxxNb2012VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgNbList2012VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(ZjgxxNb2012VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(SbxxNb2012VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public FzjgNbhj2012VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(FzjgNbhj2012VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
