package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;

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

public class ZfjgqysdsNbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * �걨��Ϣ
     */
    private SbxxNb2014VO sbxx = new SbxxNb2014VO();

    /**
     * �ܻ�����Ϣ
     */
    private ZjgxxNb2014VO zjgxx = new ZjgxxNb2014VO();

    /**
     * ��֧������Ϣ
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
