package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO;


/**
 * 
 * <p>
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 减免备案VO
 * 本类为减免备案主数据结构
 * 数据结构如下
 * 
 * jmbavo
 *     --版本号              
 *     --schema文件的版本号  
 *     --业务类型            
 *     --业务操作类型
 *     --纳税人信息
 *     		--计算机代码
 *     		--纳税人名称
 *     		--税务机关组织代码
 *     --减免备案主数据
 *     		--编号
 *     		--年度
 *     		--... ...
 *     		--备案明细VO
 *     			--（说明 该处只留接口，由各备案VO实现）
 *     		--备案资料清单VO
 *     			--序号
 *     			--资料清单
 *     
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 *  * @author Chenmt
 * @version 1.0
 */

public class JmbaVO extends YWRootVO implements XMLVOInterface{

	/**
	 * 纳税人信息
	 */
	private NsrxxVO nsrxx;
	/**
	 * 抛出异常标识，是否能继续，是：Y，否：N
	 */
	private String sfxz="Y";
	
	
	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}
	/**
	 * 减免备案主表
	 */
	private List jmsbajl=new ArrayList();
	
	private Map m = new HashMap();
	
	public JmbaVO(){
		super();
        m.put("jmsbajl", "com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO");
	}
	
	public Map getListTypeMap() {
		return m;
	}

	public String toXML() {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
	}

	public String toXMLChilds() {
		String xmlstr = "";
		xmlstr += XMLBuildUtil.appendStringElement("sfxz", this.sfxz);
		
	    xmlstr += nsrxx.toXML();
	
        if (jmsbajl != null) {
            for (int i = 0; i < jmsbajl.size(); i++) {
                xmlstr += ( (JmbaZbVO) jmsbajl.get(i)).toXML();
            }
        }
	    return xmlstr;
	}

	public NsrxxVO getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(NsrxxVO nsrxx) {
		this.nsrxx = nsrxx;
	}


	/**
	 * @return Returns the jmsbajl.
	 */
	public List getJmsbajl() {
		return jmsbajl;
	}
	/**
	 * @param jmsbajl The jmsbajl to set.
	 */
	public void setJmsbajl(List jmsbajl) {
		this.jmsbajl = jmsbajl;
	}
}
