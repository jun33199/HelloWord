package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- �۽���ҵ����˰-�����ǼǱ���ϢxmlVO</p>
 *
 * <p>Description: ����xml����</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.bjtax.ca.vo.*;
import com.syax.bjtax.shenbao.model.kjqysds.MXXXVO;
import com.syax.common.xml.*;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.Fzjgxx2008VO;

public class BadjbListVO implements XMLVOInterface
{
    /*
     * �����ǼǱ���ϸ��Ϣ
     */
    private List mxxx;
    
    Map m = new HashMap();

    public BadjbListVO()
    {
        super();
        m.put("mxxx", "com.syax.bjtax.shenbao.model.kjqysds.MXXXVO");
    }

    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<badjbxx>");
        xmlstr.append(toXMLChilds());
        xmlstr.append("</badjbxx>");
        
        return xmlstr.toString();
    }

    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        if(mxxx != null && mxxx.size() > 0)
        {
	        for(int i = 0; i < mxxx.size(); i++) {
	            xmlstr.append(((MXXXVO) mxxx.get(i)).toXML());
	        }
	    }
        return xmlstr.toString();
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
