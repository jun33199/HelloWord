package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;


/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.*;


public class FzjgNbList2014VO implements XMLVOInterface
{
    /*
     * ��֧������ϸ��Ϣ
     */
    private List mxxx = new ArrayList();
    Map m = new HashMap();

    public FzjgNbList2014VO()
    {
        super();
        m.put("mxxx", "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.FzjgxxNb2014VO");
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
            xmlstr += ( (FzjgxxNb2014VO) mxxx.get(i)).toXML();
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
