/**
 * @Title:       GrsdsNdsbbActionForm.java
 * @Description: TODO
 * @date:        2014-12-10下午02:14:15
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-10
 */
public class GrsdsNdsbbActionForm extends BaseForm{


	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description：国籍
	 */
	private List gjList = new ArrayList();

	/**
	 * Description：身份證件類型代碼表
	 */
	private List sfzjlxList = new ArrayList();
	
	/**
	 * Description： 年度
	 */
	private String nd =String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
	
	/**
	 * Description：计算机代码
	 */
	private String btzzxx_jsjdm = "";
	
	/**
	 * Description：投资者信息_姓名
	 */
	private String btzzxx_name = "";
	
	/**
	 * Description：投资者信息_纳税人识别号
	 */
	private String btzzxx_nsrsbh = "";
	
	/**
	 * Description：投资者信息_类型
	 */
	private String btzzxx_djzclx = "";
	
	/**
	 * Description：投资者信息_年平均职工人数
	 */
	private String btzzxx_npjzgrs = "";
	
	/**
	 * Description：投资者信息_工资总额
	 */
	private String btzzxx_gzze = "";
	
	/**
	 * Description：投资者信息_投资者人数
	 */
	private String btzzxx_tzzrs = "";
	
	/**
	 * Description：收入总额
	 */
	private String col_1="0.00";
	
	/**
	 * Description：减成本
	 */
	private String col_2="0.00";
	
	/**
	 * Description：营业费用
	 */
	private String col_3="0.00";
	
	/**
	 * Description：管理费用
	 */
	private String col_4="0.00";
	
	/**
	 * Description：财务费用
	 */
	private String col_5="0.00";
	
	/**
	 * Description：营业税金及附加
	 */
	private String col_6="0.00";
	
	/**
	 * Description：营业外支出
	 */
	private String col_7="0.00";
	
	/**
	 * Description：利润总额
	 */
	private String col_8="0.00";
	
	
	
	/**
	 * @description: getter-- nd
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @description: setter-- nd
	 * @param nd the nd to set
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}

	/**
	 * Description：纳税调整增加额
	 */
	private String col_9="0.00";
	
	/**
	 * Description：超过规定标准扣除的项目
	 */
	private String col_10="0.00";
	
	/**
	 * Description：职工福利费
	 */
	private String col_11="0.00";
	
	/**
	 * Description：职工教育经费
	 */
	private String col_12="0.00";
	
	/**
	 * Description：工会经费
	 */
	private String col_13="0.00";
	
	/**
	 * Description：利息支出
	 */
	private String col_14="0.00";
	
	/**
	 * Description：业务招待费
	 */
	private String col_15="0.00";
	
	/**
	 * Description：广告费和业务宣传费
	 */
	private String col_16="0.00";
	
	/**
	 * Description：教育和公益事业捐赠
	 */
	private String col_17="0.00";
	
	/**
	 * Description：住房公积金
	 */
	private String col_18="0.00";
	
	/**
	 * Description：社会
	 */
	private String col_19="0.00";
	
	/**
	 * Description：折旧费用
	 */
	private String col_20="0.00";
	
	/**
	 * Description：无形资产摊销
	 */
	private String col_21="0.00";
	
	/**
	 * Description：资产损失
	 */
	private String col_22="0.00";
	
	/**
	 * Description：其他
	 */
	private String col_23="0.00";
	
	/**
	 * Description：不允许扣除的项目
	 */
	private String col_24="0.00";
	
	/**
	 * Description：资本性支出
	 */
	private String col_25="0.00";
	
	/**
	 * Description：无形资产受让开发支出
	 */
	private String col_26="0.00";
	
	/**
	 * Description：税收滞纳金罚金罚款
	 */
	private String col_27="0.00";
	
	/**
	 * Description：赞助支出非教育和公益事业捐赠
	 */
	private String col_28="0.00";
	
	/**
	 * Description：灾害事故损失赔偿
	 */
	private String col_29="0.00";
	
	/**
	 * Description：计提的各种准备金
	 */
	private String col_30="0.00";
	
	/**
	 * Description：投资者工资薪金
	 */
	private String col_31="0.00";
	
	/**
	 * Description：与收入无关的支出
	 */
	private String col_32="0.00";
	
	/**
	 * Description：投资者家庭费用
	 */
	private String col_33="0.00"; 
	
	/**
	 * Description：纳税调整减少额
	 */
	private String col_34="0.00";
	
	/**
	 * Description：国债利息收入
	 */
	private String col_35="0.00";
	
	/**
	 * Description：其他
	 */
	private String col_36="0.00";
	
	/**
	 * Description：以前年度损益调整
	 */
	private String col_37="0.00";
	
	/**
	 * Description：经纳税调整后的生产经营所得
	 */
	private String col_38="0.00";
	
	/**
	 * Description：弥补以前年度亏损
	 */
	private String col_39="0.00";
	

	/**
	 * Description：税款所属期起
	 */
	private String skssqq = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1)+"0101";
	
	/**
	 * Description：税款所属期止
	 */
	private String skssqz = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1)+"1231";
	
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
	 * Description：提示信息
	 */
	private String msg = "";
	
	/**
	 * Description：分配比例
	 */
	private String	col_40="0.00";
	
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

	/**
	 * @description: getter-- gjList
	 * @return the gjList
	 */
	public List getGjList() {
		return gjList;
	}

	/**
	 * @description: setter-- gjList
	 * @param gjList the gjList to set
	 */
	public void setGjList(List gjList) {
		this.gjList = gjList;
	}

	/**
	 * @description: getter-- sfzjlxList
	 * @return the sfzjlxList
	 */
	public List getSfzjlxList() {
		return sfzjlxList;
	}

	/**
	 * @description: setter-- sfzjlxList
	 * @param sfzjlxList the sfzjlxList to set
	 */
	public void setSfzjlxList(List sfzjlxList) {
		this.sfzjlxList = sfzjlxList;
	}

	/**
	 * @description: getter-- btzzxx_jsjdm
	 * @return the btzzxx_jsjdm
	 */
	public String getBtzzxx_jsjdm() {
		return btzzxx_jsjdm;
	}

	/**
	 * @description: setter-- btzzxx_jsjdm
	 * @param btzzxx_jsjdm the btzzxx_jsjdm to set
	 */
	public void setBtzzxx_jsjdm(String btzzxx_jsjdm) {
		this.btzzxx_jsjdm = btzzxx_jsjdm;
	}

	/**
	 * @description: getter-- btzzxx_name
	 * @return the btzzxx_name
	 */
	public String getBtzzxx_name() {
		return btzzxx_name;
	}

	/**
	 * @description: setter-- btzzxx_name
	 * @param btzzxx_name the btzzxx_name to set
	 */
	public void setBtzzxx_name(String btzzxx_name) {
		this.btzzxx_name = btzzxx_name;
	}

	/**
	 * @description: getter-- btzzxx_nsrsbh
	 * @return the btzzxx_nsrsbh
	 */
	public String getBtzzxx_nsrsbh() {
		return btzzxx_nsrsbh;
	}

	/**
	 * @description: setter-- btzzxx_nsrsbh
	 * @param btzzxx_nsrsbh the btzzxx_nsrsbh to set
	 */
	public void setBtzzxx_nsrsbh(String btzzxx_nsrsbh) {
		this.btzzxx_nsrsbh = btzzxx_nsrsbh;
	}

	/**
	 * @description: getter-- msg
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @description: setter-- msg
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @description: getter-- btzzxx_djzclx
	 * @return the btzzxx_djzclx
	 */
	public String getBtzzxx_djzclx() {
		return btzzxx_djzclx;
	}

	/**
	 * @description: setter-- btzzxx_djzclx
	 * @param btzzxx_djzclx the btzzxx_djzclx to set
	 */
	public void setBtzzxx_djzclx(String btzzxx_djzclx) {
		this.btzzxx_djzclx = btzzxx_djzclx;
	}

	/**
	 * @description: getter-- btzzxx_npjzgrs
	 * @return the btzzxx_npjzgrs
	 */
	public String getBtzzxx_npjzgrs() {
		return btzzxx_npjzgrs;
	}

	/**
	 * @description: setter-- btzzxx_npjzgrs
	 * @param btzzxx_npjzgrs the btzzxx_npjzgrs to set
	 */
	public void setBtzzxx_npjzgrs(String btzzxx_npjzgrs) {
		this.btzzxx_npjzgrs = btzzxx_npjzgrs;
	}

	/**
	 * @description: getter-- btzzxx_gzze
	 * @return the btzzxx_gzze
	 */
	public String getBtzzxx_gzze() {
		return btzzxx_gzze;
	}

	/**
	 * @description: setter-- btzzxx_gzze
	 * @param btzzxx_gzze the btzzxx_gzze to set
	 */
	public void setBtzzxx_gzze(String btzzxx_gzze) {
		this.btzzxx_gzze = btzzxx_gzze;
	}

	/**
	 * @description: getter-- btzzxx_tzzrs
	 * @return the btzzxx_tzzrs
	 */
	public String getBtzzxx_tzzrs() {
		return btzzxx_tzzrs;
	}

	/**
	 * @description: setter-- btzzxx_tzzrs
	 * @param btzzxx_tzzrs the btzzxx_tzzrs to set
	 */
	public void setBtzzxx_tzzrs(String btzzxx_tzzrs) {
		this.btzzxx_tzzrs = btzzxx_tzzrs;
	}

	/**
	 * @description: getter-- col_1
	 * @return the col_1
	 */
	public String getCol_1() {
		return col_1;
	}

	/**
	 * @description: setter-- col_1
	 * @param col_1 the col_1 to set
	 */
	public void setCol_1(String col_1) {
		this.col_1 = col_1;
	}

	/**
	 * @description: getter-- col_2
	 * @return the col_2
	 */
	public String getCol_2() {
		return col_2;
	}

	/**
	 * @description: setter-- col_2
	 * @param col_2 the col_2 to set
	 */
	public void setCol_2(String col_2) {
		this.col_2 = col_2;
	}

	/**
	 * @description: getter-- col_3
	 * @return the col_3
	 */
	public String getCol_3() {
		return col_3;
	}

	/**
	 * @description: setter-- col_3
	 * @param col_3 the col_3 to set
	 */
	public void setCol_3(String col_3) {
		this.col_3 = col_3;
	}

	/**
	 * @description: getter-- col_4
	 * @return the col_4
	 */
	public String getCol_4() {
		return col_4;
	}

	/**
	 * @description: setter-- col_4
	 * @param col_4 the col_4 to set
	 */
	public void setCol_4(String col_4) {
		this.col_4 = col_4;
	}

	/**
	 * @description: getter-- col_5
	 * @return the col_5
	 */
	public String getCol_5() {
		return col_5;
	}

	/**
	 * @description: setter-- col_5
	 * @param col_5 the col_5 to set
	 */
	public void setCol_5(String col_5) {
		this.col_5 = col_5;
	}

	/**
	 * @description: getter-- col_6
	 * @return the col_6
	 */
	public String getCol_6() {
		return col_6;
	}

	/**
	 * @description: setter-- col_6
	 * @param col_6 the col_6 to set
	 */
	public void setCol_6(String col_6) {
		this.col_6 = col_6;
	}

	/**
	 * @description: getter-- col_7
	 * @return the col_7
	 */
	public String getCol_7() {
		return col_7;
	}

	/**
	 * @description: setter-- col_7
	 * @param col_7 the col_7 to set
	 */
	public void setCol_7(String col_7) {
		this.col_7 = col_7;
	}

	/**
	 * @description: getter-- col_8
	 * @return the col_8
	 */
	public String getCol_8() {
		return col_8;
	}

	/**
	 * @description: setter-- col_8
	 * @param col_8 the col_8 to set
	 */
	public void setCol_8(String col_8) {
		this.col_8 = col_8;
	}

	/**
	 * @description: getter-- col_9
	 * @return the col_9
	 */
	public String getCol_9() {
		return col_9;
	}

	/**
	 * @description: setter-- col_9
	 * @param col_9 the col_9 to set
	 */
	public void setCol_9(String col_9) {
		this.col_9 = col_9;
	}

	/**
	 * @description: getter-- col_10
	 * @return the col_10
	 */
	public String getCol_10() {
		return col_10;
	}

	/**
	 * @description: setter-- col_10
	 * @param col_10 the col_10 to set
	 */
	public void setCol_10(String col_10) {
		this.col_10 = col_10;
	}

	/**
	 * @description: getter-- col_11
	 * @return the col_11
	 */
	public String getCol_11() {
		return col_11;
	}

	/**
	 * @description: setter-- col_11
	 * @param col_11 the col_11 to set
	 */
	public void setCol_11(String col_11) {
		this.col_11 = col_11;
	}

	/**
	 * @description: getter-- col_12
	 * @return the col_12
	 */
	public String getCol_12() {
		return col_12;
	}

	/**
	 * @description: setter-- col_12
	 * @param col_12 the col_12 to set
	 */
	public void setCol_12(String col_12) {
		this.col_12 = col_12;
	}

	/**
	 * @description: getter-- col_13
	 * @return the col_13
	 */
	public String getCol_13() {
		return col_13;
	}

	/**
	 * @description: setter-- col_13
	 * @param col_13 the col_13 to set
	 */
	public void setCol_13(String col_13) {
		this.col_13 = col_13;
	}

	/**
	 * @description: getter-- col_14
	 * @return the col_14
	 */
	public String getCol_14() {
		return col_14;
	}

	/**
	 * @description: setter-- col_14
	 * @param col_14 the col_14 to set
	 */
	public void setCol_14(String col_14) {
		this.col_14 = col_14;
	}

	/**
	 * @description: getter-- col_15
	 * @return the col_15
	 */
	public String getCol_15() {
		return col_15;
	}

	/**
	 * @description: setter-- col_15
	 * @param col_15 the col_15 to set
	 */
	public void setCol_15(String col_15) {
		this.col_15 = col_15;
	}

	/**
	 * @description: getter-- col_16
	 * @return the col_16
	 */
	public String getCol_16() {
		return col_16;
	}

	/**
	 * @description: setter-- col_16
	 * @param col_16 the col_16 to set
	 */
	public void setCol_16(String col_16) {
		this.col_16 = col_16;
	}

	/**
	 * @description: getter-- col_17
	 * @return the col_17
	 */
	public String getCol_17() {
		return col_17;
	}

	/**
	 * @description: setter-- col_17
	 * @param col_17 the col_17 to set
	 */
	public void setCol_17(String col_17) {
		this.col_17 = col_17;
	}

	/**
	 * @description: getter-- col_18
	 * @return the col_18
	 */
	public String getCol_18() {
		return col_18;
	}

	/**
	 * @description: setter-- col_18
	 * @param col_18 the col_18 to set
	 */
	public void setCol_18(String col_18) {
		this.col_18 = col_18;
	}

	/**
	 * @description: getter-- col_19
	 * @return the col_19
	 */
	public String getCol_19() {
		return col_19;
	}

	/**
	 * @description: setter-- col_19
	 * @param col_19 the col_19 to set
	 */
	public void setCol_19(String col_19) {
		this.col_19 = col_19;
	}

	/**
	 * @description: getter-- col_20
	 * @return the col_20
	 */
	public String getCol_20() {
		return col_20;
	}

	/**
	 * @description: setter-- col_20
	 * @param col_20 the col_20 to set
	 */
	public void setCol_20(String col_20) {
		this.col_20 = col_20;
	}

	/**
	 * @description: getter-- col_21
	 * @return the col_21
	 */
	public String getCol_21() {
		return col_21;
	}

	/**
	 * @description: setter-- col_21
	 * @param col_21 the col_21 to set
	 */
	public void setCol_21(String col_21) {
		this.col_21 = col_21;
	}

	/**
	 * @description: getter-- col_22
	 * @return the col_22
	 */
	public String getCol_22() {
		return col_22;
	}

	/**
	 * @description: setter-- col_22
	 * @param col_22 the col_22 to set
	 */
	public void setCol_22(String col_22) {
		this.col_22 = col_22;
	}

	/**
	 * @description: getter-- col_23
	 * @return the col_23
	 */
	public String getCol_23() {
		return col_23;
	}

	/**
	 * @description: setter-- col_23
	 * @param col_23 the col_23 to set
	 */
	public void setCol_23(String col_23) {
		this.col_23 = col_23;
	}

	/**
	 * @description: getter-- col_24
	 * @return the col_24
	 */
	public String getCol_24() {
		return col_24;
	}

	/**
	 * @description: setter-- col_24
	 * @param col_24 the col_24 to set
	 */
	public void setCol_24(String col_24) {
		this.col_24 = col_24;
	}

	/**
	 * @description: getter-- col_25
	 * @return the col_25
	 */
	public String getCol_25() {
		return col_25;
	}

	/**
	 * @description: setter-- col_25
	 * @param col_25 the col_25 to set
	 */
	public void setCol_25(String col_25) {
		this.col_25 = col_25;
	}

	/**
	 * @description: getter-- col_26
	 * @return the col_26
	 */
	public String getCol_26() {
		return col_26;
	}

	/**
	 * @description: setter-- col_26
	 * @param col_26 the col_26 to set
	 */
	public void setCol_26(String col_26) {
		this.col_26 = col_26;
	}

	/**
	 * @description: getter-- col_27
	 * @return the col_27
	 */
	public String getCol_27() {
		return col_27;
	}

	/**
	 * @description: setter-- col_27
	 * @param col_27 the col_27 to set
	 */
	public void setCol_27(String col_27) {
		this.col_27 = col_27;
	}

	/**
	 * @description: getter-- col_28
	 * @return the col_28
	 */
	public String getCol_28() {
		return col_28;
	}

	/**
	 * @description: setter-- col_28
	 * @param col_28 the col_28 to set
	 */
	public void setCol_28(String col_28) {
		this.col_28 = col_28;
	}

	/**
	 * @description: getter-- col_29
	 * @return the col_29
	 */
	public String getCol_29() {
		return col_29;
	}

	/**
	 * @description: setter-- col_29
	 * @param col_29 the col_29 to set
	 */
	public void setCol_29(String col_29) {
		this.col_29 = col_29;
	}

	/**
	 * @description: getter-- col_30
	 * @return the col_30
	 */
	public String getCol_30() {
		return col_30;
	}

	/**
	 * @description: setter-- col_30
	 * @param col_30 the col_30 to set
	 */
	public void setCol_30(String col_30) {
		this.col_30 = col_30;
	}

	/**
	 * @description: getter-- col_31
	 * @return the col_31
	 */
	public String getCol_31() {
		return col_31;
	}

	/**
	 * @description: setter-- col_31
	 * @param col_31 the col_31 to set
	 */
	public void setCol_31(String col_31) {
		this.col_31 = col_31;
	}

	/**
	 * @description: getter-- col_32
	 * @return the col_32
	 */
	public String getCol_32() {
		return col_32;
	}

	/**
	 * @description: setter-- col_32
	 * @param col_32 the col_32 to set
	 */
	public void setCol_32(String col_32) {
		this.col_32 = col_32;
	}

	/**
	 * @description: getter-- col_33
	 * @return the col_33
	 */
	public String getCol_33() {
		return col_33;
	}

	/**
	 * @description: setter-- col_33
	 * @param col_33 the col_33 to set
	 */
	public void setCol_33(String col_33) {
		this.col_33 = col_33;
	}

	/**
	 * @description: getter-- col_34
	 * @return the col_34
	 */
	public String getCol_34() {
		return col_34;
	}

	/**
	 * @description: setter-- col_34
	 * @param col_34 the col_34 to set
	 */
	public void setCol_34(String col_34) {
		this.col_34 = col_34;
	}

	/**
	 * @description: getter-- col_35
	 * @return the col_35
	 */
	public String getCol_35() {
		return col_35;
	}

	/**
	 * @description: setter-- col_35
	 * @param col_35 the col_35 to set
	 */
	public void setCol_35(String col_35) {
		this.col_35 = col_35;
	}

	/**
	 * @description: getter-- col_36
	 * @return the col_36
	 */
	public String getCol_36() {
		return col_36;
	}

	/**
	 * @description: setter-- col_36
	 * @param col_36 the col_36 to set
	 */
	public void setCol_36(String col_36) {
		this.col_36 = col_36;
	}

	/**
	 * @description: getter-- col_37
	 * @return the col_37
	 */
	public String getCol_37() {
		return col_37;
	}

	/**
	 * @description: setter-- col_37
	 * @param col_37 the col_37 to set
	 */
	public void setCol_37(String col_37) {
		this.col_37 = col_37;
	}

	/**
	 * @description: getter-- col_38
	 * @return the col_38
	 */
	public String getCol_38() {
		return col_38;
	}

	/**
	 * @description: setter-- col_38
	 * @param col_38 the col_38 to set
	 */
	public void setCol_38(String col_38) {
		this.col_38 = col_38;
	}

	/**
	 * @description: getter-- col_39
	 * @return the col_39
	 */
	public String getCol_39() {
		return col_39;
	}

	/**
	 * @description: setter-- col_39
	 * @param col_39 the col_39 to set
	 */
	public void setCol_39(String col_39) {
		this.col_39 = col_39;
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
