/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:附表八捐赠支出明细表
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class JzzcmxbForm extends QysdsNewForm {
	public JzzcmxbForm() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、捐赠项目,金额, 非营利社会团体或国家机关名称 ,捐赠扣除比例,扣除限额,
	 * 允许税前扣除的公益救济性捐赠额String[]
	 */
	private String[] jzzc_columns = { "hc", "L1", "L2", "L3", "L4", "L5" };

	private String[] qekc_Columns = { "qekc_L1", "qekc_L2", "qekc_L3",
			"qekc_L4", "qekc_L5", "qekc_L6" };

	private String[] bfzskc_Columns = { "bfzskc_L1", "bfzskc_L2", "bfzskc_L3",
			"bfzskc_L4", "bfzskc_L5", "bfzskc_L6" };

	private String[] bfzwkc_Columns = { "bfzwkc_L1", "bfzwkc_L2", "bfzwkc_L3",
			"bfzwkc_L4", "bfzwkc_L5", "bfzwkc_L6" };

	private String[] bfzydwkc_Columns = { "bfzydwkc_L1", "bfzydwkc_L2",
			"bfzydwkc_L3", "bfzydwkc_L4", "bfzydwkc_L5", "bfzydwkc_L6" };

	private String[] fgyjjx_Columns = { "fgyjjx_L1", "fgyjjx_L2", "fgyjjx_L3",
			"fgyjjx_L4", "fgyjjx_L5", "fgyjjx_L6" };

	/**
	 * 全额扣除 List
	 */
	private List qekclist = new ArrayList();

	/**
	 * 按10%扣除 List
	 */
	private List bfzskclist = new ArrayList();

	/**
	 * 按3%扣除 List
	 */
	private List bfzwkclist = new ArrayList();

	/**
	 * 按1.5%扣除 List
	 */
	private List bfzydwkclist = new ArrayList();

	/**
	 * 非公益性救济捐赠 List
	 */
	private List fgyjjxlist = new ArrayList();

	public String[] getJzzc_columns() {
		return jzzc_columns;
	}

	public void setJzzc_columns(String[] jzzc_columns) {
		this.jzzc_columns = jzzc_columns;
	}

	public String[] getBfzskc_Columns() {
		return bfzskc_Columns;
	}

	public void setBfzskc_Columns(String[] bfzscc_Columns) {
		this.bfzskc_Columns = bfzscc_Columns;
	}

	public String[] getBfzwkc_Columns() {
		return bfzwkc_Columns;
	}

	public void setBfzwkc_Columns(String[] bfzwcc_Columns) {
		this.bfzwkc_Columns = bfzwcc_Columns;
	}

	public String[] getBfzydwkc_Columns() {
		return bfzydwkc_Columns;
	}

	public void setBfzydwkc_Columns(String[] bfzydwcc_Columns) {
		this.bfzydwkc_Columns = bfzydwcc_Columns;
	}

	public String[] getFgyjjx_Columns() {
		return fgyjjx_Columns;
	}

	public void setFgyjjx_Columns(String[] fgyjjx_Columns) {
		this.fgyjjx_Columns = fgyjjx_Columns;
	}

	public String[] getQekc_Columns() {
		return qekc_Columns;
	}

	public void setQekc_Columns(String[] qecc_Columns) {
		this.qekc_Columns = qecc_Columns;
	}

	public List getBfzskclist() {
		return bfzskclist;
	}

	public void setBfzskclist(List bfzskclist) {
		this.bfzskclist = bfzskclist;
	}

	public List getBfzwkclist() {
		return bfzwkclist;
	}

	public void setBfzwkclist(List bfzwkclist) {
		this.bfzwkclist = bfzwkclist;
	}

	public List getBfzydwkclist() {
		return bfzydwkclist;
	}

	public void setBfzydwkclist(List bfzydwkclist) {
		this.bfzydwkclist = bfzydwkclist;
	}

	public List getFgyjjxlist() {
		return fgyjjxlist;
	}

	public void setFgyjjxlist(List fgyjjxlist) {
		this.fgyjjxlist = fgyjjxlist;
	}

	public List getQekclist() {
		return qekclist;
	}

	public void setQekclist(List qekclist) {
		this.qekclist = qekclist;
	}

}
