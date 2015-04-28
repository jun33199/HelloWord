/**
 * @Title:       SbinfGrVO.java
 * @Description: TODO
 * @date:        2014-11-13上午09:55:17
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-13
 */
public class SbinfGrVO extends YWRootVO implements XMLVOInterface{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description：税款所属期起
	 */
	private String skssqq = "";
	
	/**
	 * Description：税款所属期止
	 */
	private String skssqz = "";
	
	/**
	 * Description：投资者信息_姓名
	 */
	private String tzzxx_name = "";
	
	/**
	 * Description：投资者信息_身份证件类型
	 */
	private String tzzxx_sfzjlx = "";
	
	/**
	 * Description：投资者信息_身份证件号码
	 */
	private String tzzxx_sfzjhm = "";
	
	/**
	 * Description：投资者信息_国籍
	 */
	private String tzzxx_gj = "";
	
	/**
	 * Description：投资者信息_纳税人识别号
	 */
	private String tzzxx_nsrsbh = "";
	
	
	
	/**
	 * Description：分配比例
	 */
	private String	col_40="0.0000";
	
	/**
	 * Description：允许扣除的其他费用
	 */
	private String	col_41="0.00";
	
	/**
	 * Description：投资者减除费用
	 */
	private String	col_42="0.00";
	
	/**
	 * Description：应纳税所得额
	 */
	private String	col_43="0.00";
	
	/**
	 * Description：税率
	 */
	private String	col_44="0.00";
	
	/**
	 * Description：速算扣除数
	 */
	private String	col_45="0.00";
	
	/**
	 * Description：应纳税额
	 */
	private String	col_46="0.00";
	
	/**
	 * Description：减免税额
	 */
	private String	col_47="0.00";
	
	/**
	 * Description：全年应缴税额
	 */
	private String	col_48="0.00";
	
	/**
	 * Description：期初未缴税额
	 */
	private String	col_49="0.00";
	
	/**
	 * Description：全年已预缴税额
	 */
	private String	col_50="0.00";
	
	/**
	 * Description：应补退税额
	 */
	private String	col_51="0.00";
	
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<inf_gr>");
		sb.append("<skssqq>").append(skssqq).append("</skssqq>");
		sb.append("<skssqz>").append(skssqz).append("</skssqz>");
		sb.append("<tzzxx_gj>").append(tzzxx_gj).append("</tzzxx_gj>");
		sb.append("<tzzxx_name>").append(tzzxx_name).append("</tzzxx_name>");
		sb.append("<tzzxx_nsrsbh>").append(tzzxx_nsrsbh).append("</tzzxx_nsrsbh>");
		sb.append("<tzzxx_sfzjhm>").append(tzzxx_sfzjhm).append("</tzzxx_sfzjhm>");
		sb.append("<tzzxx_sfzjlx>").append(tzzxx_sfzjlx).append("</tzzxx_sfzjlx>");
		sb.append("<col_40>").append(col_40).append("</col_40>");
		sb.append("<col_41>").append(col_41).append("</col_41>");
		sb.append("<col_42>").append(col_42).append("</col_42>");
		sb.append("<col_43>").append(col_43).append("</col_43>");
		sb.append("<col_44>").append(col_44).append("</col_44>");
		sb.append("<col_45>").append(col_45).append("</col_45>");
		sb.append("<col_46>").append(col_46).append("</col_46>");
		sb.append("<col_47>").append(col_47).append("</col_47>");
		sb.append("<col_48>").append(col_48).append("</col_48>");
		sb.append("<col_49>").append(col_49).append("</col_49>");
		sb.append("<col_50>").append(col_50).append("</col_50>");
		sb.append("<col_51>").append(col_51).append("</col_51>");
		sb.append("</inf_gr>");
		return sb.toString();
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description: getter-- skssqq
	 * @return the skssqq
	 */
	public String getSkssqq() {
		return skssqq;
	}

	/**
	 * @description: setter-- skssqq
	 * @param skssqq the skssqq to set
	 */
	public void setSkssqq(String skssqq) {
		this.skssqq = skssqq;
	}

	/**
	 * @description: getter-- skssqz
	 * @return the skssqz
	 */
	public String getSkssqz() {
		return skssqz;
	}

	/**
	 * @description: setter-- skssqz
	 * @param skssqz the skssqz to set
	 */
	public void setSkssqz(String skssqz) {
		this.skssqz = skssqz;
	}

	/**
	 * @description: getter-- tzzxx_name
	 * @return the tzzxx_name
	 */
	public String getTzzxx_name() {
		return tzzxx_name;
	}

	/**
	 * @description: setter-- tzzxx_name
	 * @param tzzxx_name the tzzxx_name to set
	 */
	public void setTzzxx_name(String tzzxx_name) {
		this.tzzxx_name = tzzxx_name;
	}

	/**
	 * @description: getter-- tzzxx_sfzjlx
	 * @return the tzzxx_sfzjlx
	 */
	public String getTzzxx_sfzjlx() {
		return tzzxx_sfzjlx;
	}

	/**
	 * @description: setter-- tzzxx_sfzjlx
	 * @param tzzxx_sfzjlx the tzzxx_sfzjlx to set
	 */
	public void setTzzxx_sfzjlx(String tzzxx_sfzjlx) {
		this.tzzxx_sfzjlx = tzzxx_sfzjlx;
	}

	/**
	 * @description: getter-- tzzxx_sfzjhm
	 * @return the tzzxx_sfzjhm
	 */
	public String getTzzxx_sfzjhm() {
		return tzzxx_sfzjhm;
	}

	/**
	 * @description: setter-- tzzxx_sfzjhm
	 * @param tzzxx_sfzjhm the tzzxx_sfzjhm to set
	 */
	public void setTzzxx_sfzjhm(String tzzxx_sfzjhm) {
		this.tzzxx_sfzjhm = tzzxx_sfzjhm;
	}

	/**
	 * @description: getter-- tzzxx_gj
	 * @return the tzzxx_gj
	 */
	public String getTzzxx_gj() {
		return tzzxx_gj;
	}

	/**
	 * @description: setter-- tzzxx_gj
	 * @param tzzxx_gj the tzzxx_gj to set
	 */
	public void setTzzxx_gj(String tzzxx_gj) {
		this.tzzxx_gj = tzzxx_gj;
	}

	/**
	 * @description: getter-- tzzxx_nsrsbh
	 * @return the tzzxx_nsrsbh
	 */
	public String getTzzxx_nsrsbh() {
		return tzzxx_nsrsbh;
	}

	/**
	 * @description: setter-- tzzxx_nsrsbh
	 * @param tzzxx_nsrsbh the tzzxx_nsrsbh to set
	 */
	public void setTzzxx_nsrsbh(String tzzxx_nsrsbh) {
		this.tzzxx_nsrsbh = tzzxx_nsrsbh;
	}

	/**
	 * @description: getter-- col_40
	 * @return the col_40
	 */
	public String getCol_40() {
		return col_40;
	}

	/**
	 * @description: setter-- col_40
	 * @param col_40 the col_40 to set
	 */
	public void setCol_40(String col_40) {
		this.col_40 = col_40;
	}

	/**
	 * @description: getter-- col_41
	 * @return the col_41
	 */
	public String getCol_41() {
		return col_41;
	}

	/**
	 * @description: setter-- col_41
	 * @param col_41 the col_41 to set
	 */
	public void setCol_41(String col_41) {
		this.col_41 = col_41;
	}

	/**
	 * @description: getter-- col_42
	 * @return the col_42
	 */
	public String getCol_42() {
		return col_42;
	}

	/**
	 * @description: setter-- col_42
	 * @param col_42 the col_42 to set
	 */
	public void setCol_42(String col_42) {
		this.col_42 = col_42;
	}

	/**
	 * @description: getter-- col_43
	 * @return the col_43
	 */
	public String getCol_43() {
		return col_43;
	}

	/**
	 * @description: setter-- col_43
	 * @param col_43 the col_43 to set
	 */
	public void setCol_43(String col_43) {
		this.col_43 = col_43;
	}

	/**
	 * @description: getter-- col_44
	 * @return the col_44
	 */
	public String getCol_44() {
		return col_44;
	}

	/**
	 * @description: setter-- col_44
	 * @param col_44 the col_44 to set
	 */
	public void setCol_44(String col_44) {
		this.col_44 = col_44;
	}

	/**
	 * @description: getter-- col_45
	 * @return the col_45
	 */
	public String getCol_45() {
		return col_45;
	}

	/**
	 * @description: setter-- col_45
	 * @param col_45 the col_45 to set
	 */
	public void setCol_45(String col_45) {
		this.col_45 = col_45;
	}

	/**
	 * @description: getter-- col_46
	 * @return the col_46
	 */
	public String getCol_46() {
		return col_46;
	}

	/**
	 * @description: setter-- col_46
	 * @param col_46 the col_46 to set
	 */
	public void setCol_46(String col_46) {
		this.col_46 = col_46;
	}

	/**
	 * @description: getter-- col_47
	 * @return the col_47
	 */
	public String getCol_47() {
		return col_47;
	}

	/**
	 * @description: setter-- col_47
	 * @param col_47 the col_47 to set
	 */
	public void setCol_47(String col_47) {
		this.col_47 = col_47;
	}

	/**
	 * @description: getter-- col_48
	 * @return the col_48
	 */
	public String getCol_48() {
		return col_48;
	}

	/**
	 * @description: setter-- col_48
	 * @param col_48 the col_48 to set
	 */
	public void setCol_48(String col_48) {
		this.col_48 = col_48;
	}

	/**
	 * @description: getter-- col_49
	 * @return the col_49
	 */
	public String getCol_49() {
		return col_49;
	}

	/**
	 * @description: setter-- col_49
	 * @param col_49 the col_49 to set
	 */
	public void setCol_49(String col_49) {
		this.col_49 = col_49;
	}

	/**
	 * @description: getter-- col_50
	 * @return the col_50
	 */
	public String getCol_50() {
		return col_50;
	}

	/**
	 * @description: setter-- col_50
	 * @param col_50 the col_50 to set
	 */
	public void setCol_50(String col_50) {
		this.col_50 = col_50;
	}

	/**
	 * @description: getter-- col_51
	 * @return the col_51
	 */
	public String getCol_51() {
		return col_51;
	}

	/**
	 * @description: setter-- col_51
	 * @param col_51 the col_51 to set
	 */
	public void setCol_51(String col_51) {
		this.col_51 = col_51;
	}

	
}
