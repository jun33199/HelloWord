package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- �̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�xmlVO</p>
 *
 * <p>Description: ����xml����</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ��˼������</p>
 *
 * @author zhangj
 * @version 1.0
 */
import java.util.*;

import com.syax.bjtax.ca.vo.*;
import com.syax.common.xml.*;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class GdzcjszjyjqkjbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * �걨��Ϣ
     */
    private Sbxx2014VO sbxx = new Sbxx2014VO();
    /**
     * ��˰����Ϣ
     */
    private GdzcjszjyjqkjbNsrxxVO nsrxx = new GdzcjszjyjqkjbNsrxxVO();
    /**
     * �걨�����б�
     */
    private GdzcjszjyjqkjblistVO sbsjlist=new GdzcjszjyjqkjblistVO();
    
    private String jumpFlag="";
    
    public GdzcjszjyjqkjbVO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        
        
		System.out.println("..................zhangj\n"+xmlstr);
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += sbxx.toXML();
        xmlstr += nsrxx.toXML();
        xmlstr += sbsjlist.toXML();
        xmlstr +=XMLBuildUtil.appendStringElement("jumpFlag", jumpFlag);
        return xmlstr;
    }

	public Sbxx2014VO getSbxx() {
		return sbxx;
	}

	public void setSbxx(Sbxx2014VO sbxx) {
		this.sbxx = sbxx;
	}
	public Map getListTypeMap() {
		Map map = new HashMap();
    	map.put("sbsjlist","com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjblistVO2");
        return map;
	}

	public GdzcjszjyjqkjblistVO getSbsjlist() {
		return sbsjlist;
	}

	public void setSbsjlist(GdzcjszjyjqkjblistVO sbsjlist) {
		this.sbsjlist = sbsjlist;
	}

	public GdzcjszjyjqkjbNsrxxVO getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(GdzcjszjyjqkjbNsrxxVO nsrxx) {
		this.nsrxx = nsrxx;
	}

	public String getJumpFlag() {
		return jumpFlag;
	}

	public void setJumpFlag(String jumpFlag) {
		this.jumpFlag = jumpFlag;
	}
    
    
}
