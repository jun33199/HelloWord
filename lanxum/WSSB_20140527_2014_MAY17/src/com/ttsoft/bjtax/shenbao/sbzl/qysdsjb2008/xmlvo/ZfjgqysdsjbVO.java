package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- ������˰��֧���������xmlVO</p>
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
import com.syax.common.xml.*;

public class ZfjgqysdsjbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * �걨��Ϣ
     */
    private Sbxx2008VO sbxx = new Sbxx2008VO();

    /**
     * �ܻ�����Ϣ
     */
    private Zjgxx2008VO zjgxx = new Zjgxx2008VO();

    /**
     * ��֧������Ϣ
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
