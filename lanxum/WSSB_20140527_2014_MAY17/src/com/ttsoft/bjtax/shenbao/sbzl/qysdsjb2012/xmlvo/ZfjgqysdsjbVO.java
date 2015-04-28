package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo;

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
    private Sbxx2012VO sbxx = new Sbxx2012VO();

    /**
     * �ܻ�����Ϣ
     */
    private Zjgxx2012VO zjgxx = new Zjgxx2012VO();

    /**
     * ��֧������Ϣ
     */
    private FzjgList2012VO fzjgxx = new FzjgList2012VO();
    private Fzjghj2012VO fzjghj = new Fzjghj2012VO();

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

    public FzjgList2012VO getFzjgxx()
    {
        return fzjgxx;
    }

    public Sbxx2012VO getSbxx()
    {
        return sbxx;
    }

    public Zjgxx2012VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgList2012VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(Zjgxx2012VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(Sbxx2012VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public Fzjghj2012VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(Fzjghj2012VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
