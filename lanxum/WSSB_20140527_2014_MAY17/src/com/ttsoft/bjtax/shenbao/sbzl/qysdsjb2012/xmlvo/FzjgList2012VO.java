package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo;


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


public class FzjgList2012VO implements XMLVOInterface
{
    /*
     * ��֧������ϸ��Ϣ
     */
    private List mxxx = new ArrayList();
    Map m = new HashMap();

    public FzjgList2012VO()
    {
        super();
        m.put("mxxx", "com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Fzjgxx2012VO");
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
            xmlstr += ( (Fzjgxx2012VO) mxxx.get(i)).toXML();
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
