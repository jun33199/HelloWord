package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

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
    private Sbxx2014VO sbxx = new Sbxx2014VO();

    /**
     * �ܻ�����Ϣ
     */
    private Zjgxx2014VO zjgxx = new Zjgxx2014VO();

    /**
     * ��֧������Ϣ
     */
    private FzjgList2014VO fzjgxx = new FzjgList2014VO();
    private Fzjghj2014VO fzjghj = new Fzjghj2014VO();

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

    public FzjgList2014VO getFzjgxx()
    {
        return fzjgxx;
    }

    public Sbxx2014VO getSbxx()
    {
        return sbxx;
    }

    public Zjgxx2014VO getZjgxx()
    {
        return zjgxx;
    }

    public void setFzjgxx(FzjgList2014VO fzjgxx)
    {
        this.fzjgxx = fzjgxx;
    }

    public void setZjgxx(Zjgxx2014VO zjgxx)
    {
        this.zjgxx = zjgxx;
    }

    public void setSbxx(Sbxx2014VO sbxx)
    {
        this.sbxx = sbxx;
    }

	public Fzjghj2014VO getFzjghj() {
		return fzjghj;
	}

	public void setFzjghj(Fzjghj2014VO fzjghj) {
		this.fzjghj = fzjghj;
	}
}
