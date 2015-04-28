/**
 * @Title:       GrsdsQyxxVO.java
 * @Description: TODO
 * @date:        2014-11-7下午02:04:31
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-7
 */
public class GrsdsQyxxVO  implements XMLVOInterface
{

	public GrsdsQyxxVO(){
		super();
	}
	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description：计算机代码
	 */
	private String qyxx_jsjdm ="";
	
	/**
	 * Description：被投资者姓名
	 */
	private String qyxx_btzzxm ="";
	
	/**
	 * Description：纳税人识别号
	 */
	private String qyxx_nsrsbh="";
	
	/**
	 * Description：扣缴义务人编号
	 */
	private String qyxx_kjywrbh="";
	
	/**
	 * Description：地址
	 */
	private String qyxx_dz="";
	
	/**
	 * Description：邮政编码
	 */
	private String qyxx_yzbm="";
	
	/**
	 * Description：登记注册类型
	 */
	private String qyxx_djzclx="";
	
	/**
	 * Description：行业
	 */
	private String qyxx_hy="";
	
	/**
	 * Description：所得税征收方式
	 */
	private String qyxx_sdszsfs="";
	
	/**
	 * Description：主管税务机关
	 */
	private String qyxx_zgswjg="";
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<qyxxvo>");
		sb.append("<qyxx_btzzxm>").append(this.qyxx_btzzxm).append("</qyxx_btzzxm>");
		sb.append("<qyxx_djzclx>").append(this.qyxx_djzclx).append("</qyxx_djzclx>");
		sb.append( "<qyxx_dz>").append(this.qyxx_dz).append("</qyxx_dz>");
		sb.append("<qyxx_hy>").append(this.qyxx_hy).append("</qyxx_hy>");
		sb.append("<qyxx_jsjdm>").append(this.qyxx_jsjdm).append("</qyxx_jsjdm>");
		sb.append("<qyxx_kjywrbh>").append(this.qyxx_kjywrbh).append("</qyxx_kjywrbh>");
		sb.append("<qyxx_nsrsbh>").append(this.qyxx_nsrsbh).append("</qyxx_nsrsbh>");
		sb.append("<qyxx_sdszsfs>").append(this.qyxx_sdszsfs).append("</qyxx_sdszsfs>");
		sb.append("<qyxx_yzbm>").append(this.qyxx_yzbm).append("</qyxx_yzbm>");
		sb.append("<qyxx_zgswjg>").append(this.qyxx_zgswjg).append("</qyxx_zgswjg>");
		sb.append("</qyxxvo>");
		return sb.toString();
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @description: getter-- qyxx_jsjdm
	 * @return the qyxx_jsjdm
	 */
	public String getQyxx_jsjdm() {
		return qyxx_jsjdm;
	}

	/**
	 * @description: setter-- qyxx_jsjdm
	 * @param qyxx_jsjdm the qyxx_jsjdm to set
	 */
	public void setQyxx_jsjdm(String qyxx_jsjdm) {
		this.qyxx_jsjdm = qyxx_jsjdm;
	}

	/**
	 * @description: getter-- qyxx_btzzxm
	 * @return the qyxx_btzzxm
	 */
	public String getQyxx_btzzxm() {
		return qyxx_btzzxm;
	}

	/**
	 * @description: setter-- qyxx_btzzxm
	 * @param qyxx_btzzxm the qyxx_btzzxm to set
	 */
	public void setQyxx_btzzxm(String qyxx_btzzxm) {
		this.qyxx_btzzxm = qyxx_btzzxm;
	}

	/**
	 * @description: getter-- qyxx_nsrsbh
	 * @return the qyxx_nsrsbh
	 */
	public String getQyxx_nsrsbh() {
		return qyxx_nsrsbh;
	}

	/**
	 * @description: setter-- qyxx_nsrsbh
	 * @param qyxx_nsrsbh the qyxx_nsrsbh to set
	 */
	public void setQyxx_nsrsbh(String qyxx_nsrsbh) {
		this.qyxx_nsrsbh = qyxx_nsrsbh;
	}

	
	/**
	 * @description: getter-- qyxx_kjywrbh
	 * @return the qyxx_kjywrbh
	 */
	public String getQyxx_kjywrbh() {
		return qyxx_kjywrbh;
	}

	/**
	 * @description: setter-- qyxx_kjywrbh
	 * @param qyxx_kjywrbh the qyxx_kjywrbh to set
	 */
	public void setQyxx_kjywrbh(String qyxx_kjywrbh) {
		this.qyxx_kjywrbh = qyxx_kjywrbh;
	}

	/**
	 * @description: getter-- qyxx_dz
	 * @return the qyxx_dz
	 */
	public String getQyxx_dz() {
		return qyxx_dz;
	}

	/**
	 * @description: setter-- qyxx_dz
	 * @param qyxx_dz the qyxx_dz to set
	 */
	public void setQyxx_dz(String qyxx_dz) {
		this.qyxx_dz = qyxx_dz;
	}

	/**
	 * @description: getter-- qyxx_yzbm
	 * @return the qyxx_yzbm
	 */
	public String getQyxx_yzbm() {
		return qyxx_yzbm;
	}

	/**
	 * @description: setter-- qyxx_yzbm
	 * @param qyxx_yzbm the qyxx_yzbm to set
	 */
	public void setQyxx_yzbm(String qyxx_yzbm) {
		this.qyxx_yzbm = qyxx_yzbm;
	}

	/**
	 * @description: getter-- qyxx_djzclx
	 * @return the qyxx_djzclx
	 */
	public String getQyxx_djzclx() {
		return qyxx_djzclx;
	}

	/**
	 * @description: setter-- qyxx_djzclx
	 * @param qyxx_djzclx the qyxx_djzclx to set
	 */
	public void setQyxx_djzclx(String qyxx_djzclx) {
		this.qyxx_djzclx = qyxx_djzclx;
	}

	/**
	 * @description: getter-- qyxx_hy
	 * @return the qyxx_hy
	 */
	public String getQyxx_hy() {
		return qyxx_hy;
	}

	/**
	 * @description: setter-- qyxx_hy
	 * @param qyxx_hy the qyxx_hy to set
	 */
	public void setQyxx_hy(String qyxx_hy) {
		this.qyxx_hy = qyxx_hy;
	}

	/**
	 * @description: getter-- qyxx_sdszsfs
	 * @return the qyxx_sdszsfs
	 */
	public String getQyxx_sdszsfs() {
		return qyxx_sdszsfs;
	}

	/**
	 * @description: setter-- qyxx_sdszsfs
	 * @param qyxx_sdszsfs the qyxx_sdszsfs to set
	 */
	public void setQyxx_sdszsfs(String qyxx_sdszsfs) {
		this.qyxx_sdszsfs = qyxx_sdszsfs;
	}

	/**
	 * @description: getter-- qyxx_zgswjg
	 * @return the qyxx_zgswjg
	 */
	public String getQyxx_zgswjg() {
		return qyxx_zgswjg;
	}

	/**
	 * @description: setter-- qyxx_zgswjg
	 * @param qyxx_zgswjg the qyxx_zgswjg to set
	 */
	public void setQyxx_zgswjg(String qyxx_zgswjg) {
		this.qyxx_zgswjg = qyxx_zgswjg;
	}

	
}
