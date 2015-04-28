package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报页面表单基类</p>
 * @author 王志民
 * @version 1.1
 */

public class QysdsNewForm
extends BaseForm {
	public QysdsNewForm() {
		try {
			jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Description 征管范围鉴定类型 addby Lijn
	private String jdlx = "";

	/**
	 * 申报日期 String
	 */
	private String sbrq;
	
	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm;
	
	/**
	 * 纳税人名称 String
	 */
	private String nsrmc;
	
	/**
	 * 税款年度 String
	 */
	private String sknd;
	
	/**
	 * 录入人代码 String
	 *
	 * 从登录数据中取得
	 */
	private String lrr;
	
	/**
	 * 录入日期 String
	 */
	
	private String lrrq;
	
	/**
	 * 创建时间 String
	 */
	
	private String cjsj;
	
	/**
	 * 税款所属时期起 String
	 *
	 * 系统根据申报日期自动计算
	 */
	
	private String skssksrq;
	
	/**
	 * 税款所属时期止 String
	 *
	 * 系统根据申报日期自动计算
	 */
	private String skssjsrq;
	
	/**
	 * 隐藏域-税款所属开始日期
	 */
	private String hid_skssksrq;
	
	/**
	 * 隐藏域-税款所属结束日期
	 */
	
	private String hid_skssjsrq;
	/**
	 * 税务机关组织机构代码 String
	 *
	 * 从登录数据中取得
	 */
	
	private String swjgzzjgdm;
	
	/**
	 * 主管税务机关代码 String
	 */
	private String zgswjgzzjgdm;
	
	/**
	 * 主管税务机关名称 String
	 */
	private String zgswjgzzjgmc;
	
	/**
	 * 税务所代码 String
	 */
	private String swjsjdm;
	
	/**
	 * 税务所名称 String
	 */
	private String swjgzzjgmc;
	
	/**
	 * 登记注册类型代码 String
	 */
	private String djzclxdm;
	
	/**
	 * 征收方式代码 String
	 */
	private String zsfsdm;
	
	/**
	 * 企业所得税年报数据
	 */
	private List qysdsnb = new ArrayList();
	
	/**
	 *  综合申报跳转用
	 */
	private String iszhsb;
	
	/**
	 * 区县代码
	 */
	private String qxdm;
	
	/**
	 * 校验结果List
	 */
	private List checkList;
	
	/**
     * 所属经济类型
     */
    private String ssjjlx;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 所属行业
     */
    private String sshy;

    /**
     * 征收方式
     */
    private String zsfs;

    /**
     * 财会制度
     */
    private String ckzd;

    /**
     * 工资管理形式（分“01”、“02”、“03”，数据库编码）
     */
    private String gzglxs;
    
    /**
     * 工资管理类型（分“工效”和“非工效”，程序内编码）
     */
    private String gzlx;
    
    /**
     * 减免类型
     */
    private String jmlx;
    
    /**
     * 报表期类型
     */
    private String bbqlx;

    /**
     * 期号
     */
    private String qh;
    
    /**
	 * 纳税人所需填写的相关纳税申报表 
	 */
	private List tableList=new ArrayList();
	
	/**
	 * 纳税人识别号
	 */
	private String nsrsbh;
	
	/**
	 * 经营地址
	 */
	private String jydz;
	
	/**
	 * 表链接URL HTML
	 */
	private String linkUrlHTML;
	
	/**
	 * 表链接MAP
	 * 存放规则：
	 * 1、关键字以N（代表next）开头的表示对应的value为下一张表链接，关键字以P（代表previous）开头的表示对应的value为上一张表链接
	 * 2、N或P之后的数字表示当前表的ID
	 * 
	 * 举例
	 * key：N_2,表示当前表的id为“2”，对应的为当前表的下一张表链接
	 */
	private Map linkMap=new HashMap();
	
	/**
	 * 下一张表链接
	 */
	private String nextTableURL;
	
	/**
	 * 上一张表链接
	 */
	private String previousTableURL;
	
	/**
	 * 是否为最后一张表
	 * 是：yes
	 * 否：no
	 */
	private String isLastTable;
	
	/**
	 * 纯益率
	 */
	private String cyl;
	/**
	 * 定额征收税额
	 */
	private String dezsse;
	/**
	 * 企业征税类型
	 */
	private String qyzslx;
	/**
     * 高新技术企业适用税率
     */
    private String sysl = "";
	
	/**
	 * 返回Form的属性
	 */
    
    
	/**
	 *
	 */
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("------QysdsNewForm Properties----"+this.getClass().getName()+"\n");
		buffer.append("计算机代码--"+this.jsjdm+"\n");
		buffer.append("纳税人名称--"+this.nsrmc+"\n");
		buffer.append("纳税人识别号--"+this.nsrsbh+"\n");
		buffer.append("申报日期--"+this.sbrq+"\n");
		buffer.append("税款所属开始日期--"+this.skssksrq+"\n");
		buffer.append("税款所属结束日期--"+this.skssjsrq+"\n");
		buffer.append("税务机关组织机构代码--"+this.swjgzzjgdm+"\n");
		buffer.append("税务机关名称--"+this.swjgzzjgmc+"\n");
		buffer.append("税务计算机代码--"+this.swjsjdm+"\n");
		buffer.append("财会制度--"+this.ckzd+"\n");
		buffer.append("减免类型--"+this.jmlx+"\n");
		buffer.append("工资管理形式--"+this.gzglxs+"\n");
		buffer.append("纯益率--"+this.cyl+"\n");
		buffer.append("定额征收税额--"+this.dezsse+"\n");
		buffer.append("所属行业--"+this.sshy+"\n");
		buffer.append("所属经济类型--"+this.ssjjlx+"\n");
		buffer.append("经营地址--"+this.jydz+"\n");
		buffer.append("期号--"+this.qh+"\n");
		buffer.append("报表期类型--"+this.bbqlx+"\n");
		buffer.append("适应税率--"+this.sysl+"\n");
		buffer.append("-------QysdsNewForm Properties----"+"\n");
		return buffer.toString();
	}
	

	/**
	 * @description: getter
	 * @return the jdlx
	 */
	public String getJdlx() {
		return jdlx;
	}


	/**
	 * @description: setter
	 * @param jdlx the jdlx to set
	 */
	public void setJdlx(String jdlx) {
		this.jdlx = jdlx;
	}


	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public List getCheckList() {
		return checkList;
	}
	
	public void setCheckList(List checkList) {
		this.checkList = checkList;
	}
	
	public String getQxdm() {
		return this.qxdm;
	}
	
	public void setQxdm(String _qxdm) {
		this.qxdm = _qxdm;
	}
	
	public String getIszhsb() {
		return this.iszhsb;
	}
	
	public void setIszhsb(String _iszhsb) {
		this.iszhsb = _iszhsb;
	}
	
	public String getDjzclxdm() {
		return this.djzclxdm;
	}
	
	public void setDjzclxdm(String _djzclxdm) {
		this.djzclxdm = _djzclxdm;
	}
	
	public String getZgswjgzzjgdm() {
		return this.zgswjgzzjgdm;
	}
	
	public void setZgswjgzzjgdm(String _zgswjgzzjgdm) {
		this.zgswjgzzjgdm = _zgswjgzzjgdm;
	}
	
	public String getZgswjgzzjgmc() {
		return this.zgswjgzzjgmc;
	}
	
	public void setZgswjgzzjgmc(String _zgswjgzzjgmc) {
		this.zgswjgzzjgmc = _zgswjgzzjgmc;
	}
	
	public String getSwjsjdm() {
		return swjsjdm;
	}

	public void setSwjsjdm(String swjsjdm) {
		this.swjsjdm = swjsjdm;
	}

	public void setSwjgzzjgdm(String _swjgzzjgdm) {
		this.swjgzzjgdm = _swjgzzjgdm;
	}
	
	public String getSwjgzzjgmc() {
		return this.swjgzzjgmc;
	}
	
	public void setSwjgzzjgmc(String _swjgzzjgmc) {
		this.swjgzzjgmc = _swjgzzjgmc;
	}

	public String getJsjdm() {
		return this.jsjdm;
	}
	
	public void setJsjdm(String _jsjdm) {
		this.jsjdm = _jsjdm;
	}
	
	public String getNsrmc() {
		return this.nsrmc;
	}
	
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	
	public String getSbrq() {
		return this.sbrq;
	}
	
	public void setSbrq(String rq) {
		this.sbrq = rq;
	}
	
	public List getDataList() {
		return this.qysdsnb;
	}
	
	public void setDataList(List qysdsnb) {
		this.qysdsnb = qysdsnb;
	}
	
	public String getSknd() {
		return sknd;
	}
	
	public void setSknd(String _sknd) {
		sknd = _sknd;
	}
	
	public String getLrr() {
		return lrr;
	}
	
	public void setLrr(String _lrr) {
		lrr = _lrr;
	}
	
	public String getCjrq() {
		return cjsj;
	}
	
	public void setCjrq(String _cjsj) {
		cjsj = _cjsj;
	}
	
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	
	public void setSwjgzzjgdm2(String _swjgzzjgdm2) {
		swjgzzjgdm = _swjgzzjgdm2;
	}
	
	public String getLrrq() {
		return lrrq;
	}
	
	public void setLrrq(String _lrrq) {
		lrrq = _lrrq;
	}
	
	public String getSkssksrq() {
		return skssksrq;
	}
	
	public void setSkssksrq(String _skssksrq) {
		skssksrq = _skssksrq;
	}
	
	public String getSkssjsrq() {
		return skssjsrq;
	}
	
	public void setSkssjsrq(String _skssjsrq) {
		skssjsrq = _skssjsrq;
	}
	
	public String getZsfsdm() {
		//return com.ttsoft.bjtax.smsb.constant.CodeConstant.ZSFSDM_CYLZS;
		return this.zsfsdm;
	}
	
	public void setZsfsdm(String _zsfsdm) {
		this.zsfsdm = _zsfsdm;
	}
	
	/**
	 * 页面要素清除
	 * @param actionMapping struts.action.ActionMapping
	 * @param httpServletRequest HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		
		jsjdm=null;
		nsrsbh=null;
		nsrmc=null;
		sknd=null;
		lrr=null;
		lrrq=null;
		cjsj=null;
		swjgzzjgdm=null;
		zgswjgzzjgdm=null;
		zgswjgzzjgmc=null;
		swjsjdm=null;
		swjgzzjgmc=null;
		djzclxdm=null;
		zsfsdm=null;
		qxdm=null;
		ssjjlx=null;
		lxdh=null;
		sshy=null;
		zsfs=null;
		ckzd=null;
		gzglxs=null;
		jmlx=null;
		bbqlx=null;
		qh=null;
		jydz=null;
		linkUrlHTML=null;
		cyl=null;
		dezsse=null;
		qyzslx=null;
		sysl=null;
		jdlx="";
		this.getTableList().clear();
		//this.getDataList().clear();
	}
	
	private void jbInit() throws Exception {
	}

	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	public String getCkzd() {
		return ckzd;
	}

	public void setCkzd(String ckzd) {
		this.ckzd = ckzd;
	}

	public String getGzglxs() {
		return gzglxs;
	}

	public void setGzglxs(String gzglxs) {
		this.gzglxs = gzglxs;
	}

	public String getJmlx() {
		return jmlx;
	}

	public void setJmlx(String jmlx) {
		this.jmlx = jmlx;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getQh() {
		return qh;
	}

	public void setQh(String qh) {
		this.qh = qh;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getSsjjlx() {
		return ssjjlx;
	}

	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}

	public List getTableList() {
		return tableList;
	}

	public void setTableList(List tableList) {
		this.tableList = tableList;
	}

	public String getZsfs() {
		return zsfs;
	}

	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}

	public String getJydz() {
		return jydz;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public String getLinkUrlHTML() {
		return linkUrlHTML;
	}

	public void setLinkUrlHTML(String linkUrlHTML) {
		this.linkUrlHTML = linkUrlHTML;
	}
	
	public String getCyl() {
		return cyl;
	}
	
	public void setCyl(String cyl) {
		this.cyl = cyl;
	}
	public String getDezsse() {
		return dezsse;
	}
	
	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}
	public String getQyzslx() {
		return qyzslx;
	}
	
	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	public String getGzlx() {
		return gzlx;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	public Map getLinkMap() {
		return linkMap;
	}

	public void setLinkMap(Map linkMap) {
		this.linkMap = linkMap;
	}

	public String getNextTableURL() {
		return nextTableURL;
	}

	public void setNextTableURL(String nextTableURL) {
		this.nextTableURL = nextTableURL;
	}

	public String getPreviousTableURL() {
		return previousTableURL;
	}

	public void setPreviousTableURL(String previousTableURL) {
		this.previousTableURL = previousTableURL;
	}

	public String getIsLastTable() {
		return isLastTable;
	}

	public void setIsLastTable(String isLastTable) {
		this.isLastTable = isLastTable;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public List getQysdsnb() {
		return qysdsnb;
	}

	public void setQysdsnb(List qysdsnb) {
		this.qysdsnb = qysdsnb;
	}

	public String getSysl() {
		return sysl;
	}

	public void setSysl(String sysl) {
		this.sysl = sysl;
	}

	public String getHid_skssjsrq() {
		return hid_skssjsrq;
	}

	public void setHid_skssjsrq(String hid_skssjsrq) {
		this.hid_skssjsrq = hid_skssjsrq;
	}

	public String getHid_skssksrq() {
		return hid_skssksrq;
	}

	public void setHid_skssksrq(String hid_skssksrq) {
		this.hid_skssksrq = hid_skssksrq;
	}



}

