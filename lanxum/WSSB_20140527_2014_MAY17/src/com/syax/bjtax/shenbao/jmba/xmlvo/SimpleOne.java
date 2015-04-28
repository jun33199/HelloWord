package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SimpleOne extends YWRootVO  implements XMLVOInterface, JmbamxVoInterface{

	private String[] name;
	
	private String[] age;
	
	private String[] num;
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		String xmlstr ="";
		for (int i = 0; i < name.length; i++){
			xmlstr +="<Simple>";
			xmlstr += XMLBuildUtil.appendStringElement("name",name[i]);
			xmlstr += XMLBuildUtil.appendStringElement("age",age[i]);
			xmlstr += XMLBuildUtil.appendStringElement("num",num[i]);
			xmlstr+="</Simple>";
		}
		
		
		
		return xmlstr;
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toMxXML() {
		// TODO Auto-generated method stub
		return toXML();
	}

	public String[] getAge() {
		return age;
	}

	public void setAge(String[] age) {
		this.age = age;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getNum() {
		return num;
	}

	public void setNum(String[] num) {
		this.num = num;
	}

}
