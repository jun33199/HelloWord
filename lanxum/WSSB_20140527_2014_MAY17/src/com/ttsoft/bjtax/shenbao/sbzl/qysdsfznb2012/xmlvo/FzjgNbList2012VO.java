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
 * @author tum
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.*;


public class FzjgNbList2012VO implements XMLVOInterface
{
    /*
     * 分支机构明细信息
     */
    private List mxxx = new ArrayList();
    Map m = new HashMap();

    public FzjgNbList2012VO()
    {
        super();
        m.put("mxxx", "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo.FzjgxxNb2012VO");
    }

    public String toXML()
    {
        String xmlstr = "<fzjgxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</fzjgxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        for(int i = 0; i < mxxx.size(); i++) {
            xmlstr += ( (FzjgxxNb2012VO) mxxx.get(i)).toXML();
        }
        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return m;
    }

    public List getMxxx()
    {
        return mxxx;
    }

    public void setMxxx(List fzjgList)
    {
        this.mxxx = fzjgList;
    }

}
