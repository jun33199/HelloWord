package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;

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
    private Sbxx2008VO sbxx = new Sbxx2008VO();

    /**
     * 总机构信息
     */
    private Zjgxx2008VO zjgxx = new Zjgxx2008VO();

    /**
     * 分支机构信息
     */
    private FzjgList2008VO fzjgxx = new FzjgList2008VO();

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
        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public FzjgList2008VO getFzjgxx()
    {
        return fzjgxx;
    }

    public Sbxx2008VO getSbxx()
    {
        return sbxx;
    }

    public Zjgxx2008VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgList2008VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(Zjgxx2008VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(Sbxx2008VO sbxx)
    {
        this.sbxx = sbxx;
    }
}
