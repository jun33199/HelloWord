/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.util.NumberUtils;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SbjkmxData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.util.JspUtil;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zhsb.web.ZhsbForm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * @author guzx
 * 查账征收年报
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Czzsnb04VO  extends YWRootVO implements XMLVOInterface{

    /**
     * 纳税人信息 = new Nsrxx02VO()
     */
    private Nsrxx04VO nsrxx;
    private Sbxx04VO sbxx;
    private Map m = new HashMap();
    private Sbsjlist04VO sbsjlist;
   
    /**
     * 申报数据
     */
    List tzfsj=new ArrayList();
    
    private Czzbsj04VO czzbsj ;
    
	/**
	 * 
	 */
	public Czzsnb04VO() {
		super();
        m.put("tzfsj", "com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Tzfsj04VO");
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
        return m;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
        String xmlstr = "";
        if (nsrxx!=null )
            xmlstr += nsrxx.toXML();
        if (sbxx!=null)
            xmlstr += sbxx.toXML();
        if (czzbsj!=null)
            xmlstr += czzbsj.toXML();
        if (tzfsj != null )
        {
            for (int i = 0; i < tzfsj.size(); i++)
            {
                xmlstr += ((Tzfsj04VO) tzfsj.get(i)).toXML();
            }
        }
        if (sbsjlist!=null)
            xmlstr += sbsjlist.toXML();
        return xmlstr;
	}

	
    /**
	 * @param request
	 * @param wqyysList
	 * @param jbsj
	 * @param boolean1
	 * @return
	 */
	private static Czzsnb04VO convert2XMLVO() {
		// TODO Auto-generated method stub
		Czzsnb04VO vo=new Czzsnb04VO();

		return vo;
	}

    public static void main(String[] args){
        String xmlvo="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[100012]]></ywlx><ywczlx><![CDATA[]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[nsrmc]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbsj><szsmdm><![CDATA[020911]]></szsmdm><kssl><![CDATA[]]></kssl><jsje><![CDATA[0.00]]></jsje><sl><![CDATA[0.00]]></sl><ynse><![CDATA[0.00]]></ynse><yjnse><![CDATA[0.00]]></yjnse><bqybse><![CDATA[0.00]]></bqybse><sre><![CDATA[0.00]]></sre><htcje><![CDATA[0.00]]></htcje><yjl><![CDATA[0.00]]></yjl><hdsre><![CDATA[0.00]]></hdsre><jfzce><![CDATA[0.00]]></jfzce><hssre><![CDATA[0.00]]></hssre></sbsj></taxdoc>";
        Czzsnb04VO vo = new Czzsnb04VO();
        //vo =  convert2XMLVO();
        try
        {
        	 XMLParseHelper.parseXMLString(vo,xmlvo,XMLParseHelper.XERCES_PARSER,false,true);
        	 vo = new Czzsnb04VO();
        	 XMLParseHelper.parseXMLString(vo,xmlvo,XMLParseHelper.VTDXML_PARSER,false,true);
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        
        String tmpxml = vo.toXML();
        System.out.println("====================");
        System.out.println(tmpxml);
        System.out.println("====================");


        
       }

	
	/**
	 * @return Returns the nsrxx.
	 */
	public Nsrxx04VO getNsrxx() {
		return nsrxx;
	}
	/**
	 * @param nsrxx The nsrxx to set.
	 */
	public void setNsrxx(Nsrxx04VO nsrxx) {
		this.nsrxx = nsrxx;
	}
	/**
	 * @return Returns the sbsjlist.
	 */
	public Sbsjlist04VO getSbsjlist() {
		return sbsjlist;
	}
	/**
	 * @param sbsjlist The sbsjlist to set.
	 */
	public void setSbsjlist(Sbsjlist04VO sbsjlist) {
		this.sbsjlist = sbsjlist;
	}
	/**
	 * @return Returns the sbxx.
	 */
	public Sbxx04VO getSbxx() {
		return sbxx;
	}
	/**
	 * @param sbxx The sbxx to set.
	 */
	public void setSbxx(Sbxx04VO sbxx) {
		this.sbxx = sbxx;
	}
	/**
	 * @return Returns the tzfsj.
	 */
	public List getTzfsj() {
		return tzfsj;
	}
	/**
	 * @param tzfsj The tzfsj to set.
	 */
	public void setTzfsj(List tzfsj) {
		this.tzfsj = tzfsj;
	}
	/**
	 * @return Returns the czzbsj.
	 */
	public Czzbsj04VO getCzzbsj() {
		return czzbsj;
	}
	/**
	 * @param czzbsj The czzbsj to set.
	 */
	public void setCzzbsj(Czzbsj04VO czzbsj) {
		this.czzbsj = czzbsj;
	}
}

