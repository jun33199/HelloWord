package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2008查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class CzzssdsjbForm  extends QysdsNewForm{
	
	/**
	 * 所得税年报列表标题项目代码 行次、本期数、累计数
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje"};
    /**
     * 存放入从表的数据
     */
    private String[] cbsb_columns = {"cbJmxmYzhc", "cbJmxmYz","cbJmxmDmhc", "cbJmxmDm"};
    /**
     * 存放入从表的数据
     */
    private String[] cbsbMssrxmCol = {"cbMssrxmYzhc", "cbMssrxmYz","cbMssrxmDmhc", "cbMssrxmDm"};
    /**
     * 存放入从表的数据
     */
    private String[] cbsbJzmzxmCol = {"cbJzmzxmYzhc", "cbJzmzxmYz","cbJzmzxmDmhc", "cbJzmzxmDm"};
	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsjbList = new ArrayList();
	private List cbsb_qysdsjbList = new ArrayList();
	private List cbsbMssrxmList = new ArrayList();
	private List cbsbJzmzxmList = new ArrayList();
	private String SAVE_FLAG="0";//0-初始值,1-保存成功	
	
	private String xzqy;
	//税源标识
	private String sybs;
	//一般减免税率
	private String ybjmsl;
	//纳税方式
	private String nsfs;
	//总分机构
	private String zfjg;
	//减免资格
	private String jmzg;
	
	//税款所属年度等于开业登记日期Y 否N
	private String sfxkh;
	//获取税款所属期所在年度上一年度征收方式
	private String syndZsfsdm;
	//获取上一年度核定征收年报行6数据
	private String syndZbh6;
	//获取上一年度汇算清缴主表行25数据
	private String syndZbh25;
	//获取上一年度汇算清缴附表5行45、46、47的校验结果
	private String syndFb5jyjg;
		
	//是否执行了查询   否则查询不出最新的税源标识 1 代表查询了
	private String isQuery;
	
	//是否新开业，新开业的不参加税额分摊
	private String isXky;
	//免税收入项目代码表
	private List mssrxmList = new ArrayList();
	private String mssrxmdm;
	//减征、免征项目代码表
	private List jzmzxmList = new ArrayList();
	private String jzmzxmdm;
	//减免项目代码表
	private List jmxmList = new ArrayList();
	private String jmxmdm;
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}

	//获取数据库中的纳税方法和总分机构的域值
	private HashMap nsfs_fsjg = new HashMap();
	
	public String getJmzg() {
		return jmzg;
	}
	
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}
	
	public String getXzqy() {
		return xzqy;
	}
	
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}
	
	public void setYbjmsl(String ybjmsl) {
		this.xzqy = ybjmsl;
	}
	
	public String[] getColumns() {
		return hdjb_columns;
	}

	public void setColumns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public List getQysdsjbList() {
		return qysdsjbList;
	}

	public void setQysdsjbList(List qysdsjbList) {
		this.qysdsjbList = qysdsjbList;
	}

	/**
	 * @return Returns the sAVE_FLAG.
	 */
	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	/**
	 * @param save_flag The sAVE_FLAG to set.
	 */
	public void setSAVE_FLAG(String save_flag) {
		SAVE_FLAG = save_flag;
	}

	public String[] getHdjb_columns() {
		return hdjb_columns;
	}

	public void setHdjb_columns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public String getSfxkh() {
		return sfxkh;
	}

	public void setSfxkh(String sfxkh) {
		this.sfxkh = sfxkh;
	}

	public String getSyndZsfsdm() {
		return syndZsfsdm;
	}

	public void setSyndZsfsdm(String syndZsfsdm) {
		this.syndZsfsdm = syndZsfsdm;
	}

	public String getSyndZbh6() {
		return syndZbh6;
	}

	public void setSyndZbh6(String syndZbh6) {
		this.syndZbh6 = syndZbh6;
	}

	public String getSyndZbh25() {
		return syndZbh25;
	}

	public void setSyndZbh25(String syndZbh25) {
		this.syndZbh25 = syndZbh25;
	}

	public String getSyndFb5jyjg() {
		return syndFb5jyjg;
	}

	public void setSyndFb5jyjg(String syndFb5jyjg) {
		this.syndFb5jyjg = syndFb5jyjg;
	}

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getIsXky() {
		return isXky;
	}

	public void setIsXky(String isXky) {
		this.isXky = isXky;
	}

	public String[] getCbsb_columns() {
		return cbsb_columns;
	}

	public void setCbsb_columns(String[] cbsbColumns) {
		cbsb_columns = cbsbColumns;
	}

	public List getCbsb_qysdsjbList() {
		return cbsb_qysdsjbList;
	}

	public void setCbsb_qysdsjbList(List cbsbQysdsjbList) {
		cbsb_qysdsjbList = cbsbQysdsjbList;
	}

	public List getJmxmList() {
		return jmxmList;
	}

	public void setJmxmList(List jmxmList) {
		this.jmxmList = jmxmList;
	}

	public String getJmxmdm() {
		return jmxmdm;
	}

	public void setJmxmdm(String jmxmdm) {
		this.jmxmdm = jmxmdm;
	}

	public List getMssrxmList() {
		return mssrxmList;
	}

	public void setMssrxmList(List mssrxmList) {
		this.mssrxmList = mssrxmList;
	}

	public String getMssrxmdm() {
		return mssrxmdm;
	}

	public void setMssrxmdm(String mssrxmdm) {
		this.mssrxmdm = mssrxmdm;
	}

	public List getJzmzxmList() {
		return jzmzxmList;
	}

	public void setJzmzxmList(List jzmzxmList) {
		this.jzmzxmList = jzmzxmList;
	}

	public String getJzmzxmdm() {
		return jzmzxmdm;
	}

	public void setJzmzxmdm(String jzmzxmdm) {
		this.jzmzxmdm = jzmzxmdm;
	}

	public String[] getCbsbMssrxmCol() {
		return cbsbMssrxmCol;
	}

	public void setCbsbMssrxmCol(String[] cbsbMssrxmCol) {
		this.cbsbMssrxmCol = cbsbMssrxmCol;
	}

	public String[] getCbsbJzmzxmCol() {
		return cbsbJzmzxmCol;
	}

	public void setCbsbJzmzxmCol(String[] cbsbJzmzxmCol) {
		this.cbsbJzmzxmCol = cbsbJzmzxmCol;
	}

	public List getCbsbMssrxmList() {
		return cbsbMssrxmList;
	}

	public void setCbsbMssrxmList(List cbsbMssrxmList) {
		this.cbsbMssrxmList = cbsbMssrxmList;
	}

	public List getCbsbJzmzxmList() {
		return cbsbJzmzxmList;
	}

	public void setCbsbJzmzxmList(List cbsbJzmzxmList) {
		this.cbsbJzmzxmList = cbsbJzmzxmList;
	}

}
