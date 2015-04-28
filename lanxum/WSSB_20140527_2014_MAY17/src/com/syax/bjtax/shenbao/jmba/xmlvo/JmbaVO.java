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
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ���ⱸ��VO
 * ����Ϊ���ⱸ�������ݽṹ
 * ���ݽṹ����
 * 
 * jmbavo
 *     --�汾��              
 *     --schema�ļ��İ汾��  
 *     --ҵ������            
 *     --ҵ���������
 *     --��˰����Ϣ
 *     		--���������
 *     		--��˰������
 *     		--˰�������֯����
 *     --���ⱸ��������
 *     		--���
 *     		--���
 *     		--... ...
 *     		--������ϸVO
 *     			--��˵�� �ô�ֻ���ӿڣ��ɸ�����VOʵ�֣�
 *     		--���������嵥VO
 *     			--���
 *     			--�����嵥
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
	 * ��˰����Ϣ
	 */
	private NsrxxVO nsrxx;
	/**
	 * �׳��쳣��ʶ���Ƿ��ܼ������ǣ�Y����N
	 */
	private String sfxz="Y";
	
	
	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}
	/**
	 * ���ⱸ������
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
