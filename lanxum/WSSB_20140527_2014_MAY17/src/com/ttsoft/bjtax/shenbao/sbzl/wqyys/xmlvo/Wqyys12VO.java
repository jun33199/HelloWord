/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo;

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
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Wqyys12VO  extends YWRootVO implements XMLVOInterface{

    /**
     * 纳税人信息 = new Nsrxx02VO()
     */
    private Nsrxx12VO nsrxx;
    private Sbxx12VO sbxx;
    private Map m = new HashMap();
   
    /**
     * 申报数据
     */
    List sbsj=new ArrayList();
    
    /*
     * 核定信息= new Hdxx02VO()
     */
    private Hdxx12VO hdxx ;
    
	/**
	 * 
	 */
	public Wqyys12VO() {
		super();
        m.put("sbsj", "com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Sbsj12VO");
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
        if (hdxx!=null)
            xmlstr += hdxx.toXML();
        if (sbxx!=null)
            xmlstr += sbxx.toXML();
        if (sbsj != null )
        {
            for (int i = 0; i < sbsj.size(); i++)
            {
                xmlstr += ((Sbsj12VO) sbsj.get(i)).toXML();
            }
        }
        return xmlstr;
	}

	/**
	 * @return Returns the nsrxx.
	 */
	public Nsrxx12VO getNsrxx() {
		return nsrxx;
	}
	/**
	 * @param nsrxx The nsrxx to set.
	 */
	public void setNsrxx(Nsrxx12VO nsrxx) {
		this.nsrxx = nsrxx;
	}

    /**
	 * @return Returns the hdxx.
	 */
	public Hdxx12VO getHdxx() {
		return hdxx;
	}
	/**
	 * @param hdxx The hdxx to set.
	 */
	public void setHdxx(Hdxx12VO hdxx) {
		this.hdxx = hdxx;
	}

	/**
	 * @return Returns the sbsj.
	 */
	public List getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj The sbsj to set.
	 */
	public void setSbsj(List sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return Returns the sbxx.
	 */
	public Sbxx12VO getSbxx() {
		return sbxx;
	}
	/**
	 * @param sbxx The sbxx to set.
	 */
	public void setSbxx(Sbxx12VO sbxx) {
		this.sbxx = sbxx;
	}
	
    /**
	 * @param request
	 * @param wqyysList
	 * @param jbsj
	 * @param boolean1
	 * @return
	 */
	private static Wqyys12VO convert2XMLVO() {
		// TODO Auto-generated method stub
		Wqyys12VO vo=new Wqyys12VO();
        Sbsj12VO sbsj = new Sbsj12VO();
        Sbxx12VO sbxx = new Sbxx12VO();
        Hdxx12VO hdxx = new Hdxx12VO();
        Nsrxx12VO nsrxx = new Nsrxx12VO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        List sbsjlist=new ArrayList();
        
        nsrxx.setJsjdm("06003600");
        nsrxx.setSwjgzzjgdm("0601");
        nsrxx.setNsrmc("nsrmc");
        hdxx.setZsffdm("03");
        
        sbxx.setDone("true");
        sbxx.setSbrq(sdf.format(curDate));
        sbxx.setSkssksrq(sdf.format(curDate));
        sbxx.setSkssjsrq(sdf.format(curDate));
        
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        vo.setHdxx(hdxx);
        
            sbsj = new Sbsj12VO();
            // 设置纳税人名称
            sbsj.setSzsmdm("020911");

            sbsj.setBqybse("0.00");
            sbsj.setHdsre("0.00");
            sbsj.setHssre("0.00");
            sbsj.setHtcje("0.00");
            sbsj.setJfzce("0.00");
            sbsj.setJsje("0.00");
            //sbsj.setKssl(NumberUtils.bigDeciaml2String(wqyys.getKssl()));
            sbsj.setSl("0.00");
            sbsj.setSre("0.00");
            sbsj.setYjl("0.00");
            sbsj.setYjnse("0.00");
            sbsj.setYnse("0.00");
            sbsjlist.add(sbsj);
        vo.setSbsj(sbsjlist);
		vo.setSchemaVersion("20060401");
        vo.setXsltVersion("20060401");
        vo.setYwlx("100012");

		return vo;
	}

    public static void main(String[] args){
        String xmlvo="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[100012]]></ywlx><ywczlx><![CDATA[]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[nsrmc]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbsj><szsmdm><![CDATA[020911]]></szsmdm><kssl><![CDATA[]]></kssl><jsje><![CDATA[0.00]]></jsje><sl><![CDATA[0.00]]></sl><ynse><![CDATA[0.00]]></ynse><yjnse><![CDATA[0.00]]></yjnse><bqybse><![CDATA[0.00]]></bqybse><sre><![CDATA[0.00]]></sre><htcje><![CDATA[0.00]]></htcje><yjl><![CDATA[0.00]]></yjl><hdsre><![CDATA[0.00]]></hdsre><jfzce><![CDATA[0.00]]></jfzce><hssre><![CDATA[0.00]]></hssre></sbsj></taxdoc>";
        Wqyys12VO vo = new Wqyys12VO();
        //vo =  convert2XMLVO();
        try
        {
        	 XMLParseHelper.parseXMLString(vo,xmlvo,XMLParseHelper.XERCES_PARSER,false,true);
        	 vo = new Wqyys12VO();
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

	
}

