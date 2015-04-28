package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * 
 * 项目名称：smsb 类名称：QyqssdsBaseForm 类描述： 企业所得税清算　基类　 创建人：wangcy 创建时间：2014-2-14
 * 下午4:17:53 修改人：wangcy 修改时间：2014-2-14 下午4:17:53 修改备注：
 * 
 * @version 1.0
 */
public class QyqssdsBaseForm extends BaseForm {

	public QyqssdsBaseForm() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 申报日期 String
	 */
	private String tbrq;

	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm;

	/**
	 * 纳税人名称 String
	 */
	private String nsrmc;

	/**
	 * 创建人代码 String
	 * 
	 * 从登录数据中取得
	 */
	private String cjr;
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
	 * 
	 * 清算备案开始日期
	 */
	private String qsbaksrq;

	/**
	 * 税款年度 String
	 * 为清算备案审核通过所在的年度
	 * 主要为了报表能够保存（与企业所得税汇算清缴保持一致）
	 */
	private String sknd;
	/**
	 * 
	 * 清算备案结束日期
	 */
	private String qsbajsrq;

	/**
	 * 
	 * 清算申报开始日期
	 */
	private String qssbksrq;

	/**
	 * 
	 * 清算申报结束日期
	 */
	private String qssbjsrq;

	/**
	 * 隐藏域-清算申报开始日期
	 */
	private String hid_qssbksrq;

	/**
	 * 隐藏域-清算申报结束日期
	 */
	private String hid_qssbjsrq;
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
	 * 企业所得税年报数据
	 */
	private List qyqssds = new ArrayList();

	/**
	 * 综合申报跳转用
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
	 * 管理人或清算组联络人员
	 */
	private String qsllry = "";
	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 所属行业
	 */
	private String sshy;

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
	private List tableList = new ArrayList();

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
	 * 表链接MAP 存放规则： 1、关键字以N（代表next）开头的表示对应的value为下一张表链接，关键字以P（代表previous）
	 * 开头的表示对应的value为上一张表链接 2、N或P之后的数字表示当前表的ID
	 * 
	 * 举例 key：N_2,表示当前表的id为“2”，对应的为当前表的下一张表链接
	 */
	private Map linkMap = new HashMap();

	/**
	 * 下一张表链接
	 */
	private String nextTableURL;

	/**
	 * 上一张表链接
	 */
	private String previousTableURL;

	/**
	 * 是否为最后一张表 是：yes 否：no
	 */
	private String isLastTable;

	// 企业章程规定的经营期限届满
	private String jyqxjm = "";
	// 企业股东会、股东大会或类似机构决议解散
	private String gdjyjs = "";
	// 企业依法被吊销营业执照、责令关闭或者被撤销
	private String yfdxgb = "";
	// 企业被人民法院依法予以解散或宣告破产
	private String yfxgpc = "";
	// 有关法律、行政法规规定清算
	private String yfgdqs = "";
	// 企业因其他原因解散或进行清算
	private String qtyy = "";

	// 备案审核状态标识
	private String baShztbs = "";
	// 备案审核状态描述
	private String baShztMessage = "";
	// 备案审核通过日期
	private String baShtgrq = "";

	// 申报审核状态标识  填写清算申报数据
	private String sbShztbs = "";
	// 备案审核状态描述
	private String sbShztMessage = "";
	// 备案审核通过日期
	private String sbShtgrq = "";
	
	// 是否保存申报日期  0 没有保存 1 已保存
	private String isBcsbrq = "";

	// 申请类型
	private String sqlx="";
	
	//税款所属期
	private String skssq;
	
	public String getSkssq() {
		return skssq;
	}
	

	public void setSkssq(String skssq) {
		this.skssq = skssq;
	}

	/**
	 * 返回Form的属性
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("------QysdsNewForm Properties----"
				+ this.getClass().getName() + "\n");
		buffer.append("计算机代码--" + this.jsjdm + "\n");
		buffer.append("纳税人名称--" + this.nsrmc + "\n");
		buffer.append("纳税人识别号--" + this.nsrsbh + "\n");
		buffer.append("填报日期--" + this.tbrq + "\n");
		buffer.append("清算开始日期--" + this.qssbksrq + "\n");
		buffer.append("清算结束日期--" + this.qssbjsrq + "\n");
		buffer.append("税务机关组织机构代码--" + this.swjgzzjgdm + "\n");
		buffer.append("税务机关名称--" + this.swjgzzjgmc + "\n");
		buffer.append("税务计算机代码--" + this.swjsjdm + "\n");
		buffer.append("所属行业--" + this.sshy + "\n");
		buffer.append("所属经济类型--" + this.ssjjlx + "\n");
		buffer.append("经营地址--" + this.jydz + "\n");
		buffer.append("期号--" + this.qh + "\n");
		buffer.append("报表期类型--" + this.bbqlx + "\n");
		buffer.append("-------QysdsNewForm Properties----" + "\n");
		return buffer.toString();
	}

	
	public String getSqlx() {
		return sqlx;
	}


	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
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

	

	public List getDataList() {
		return this.qyqssds;
	}

	public void setDataList(List qyqssds) {
		this.qyqssds = qyqssds;
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

	
	
	

	/**
	 * 页面要素清除
	 * 
	 * @param actionMapping
	 *            struts.action.ActionMapping
	 * @param httpServletRequest
	 *            HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		jsjdm = null;
		nsrsbh = null;
		nsrmc = null;
		lrr = null;
		lrrq = null;
		cjsj = null;
		swjgzzjgdm = null;
		zgswjgzzjgdm = null;
		zgswjgzzjgmc = null;
		swjsjdm = null;
		swjgzzjgmc = null;
		qxdm = null;
		ssjjlx = null;
		lxdh = null;
		sshy = null;
		bbqlx = null;
		qh = null;
		jydz = null;
		linkUrlHTML = null;
		this.getTableList().clear();
		// this.getDataList().clear();
	}

	private void jbInit() throws Exception {
	}

	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
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

	public List getQyqssds() {
		return qyqssds;
	}

	public void setQyqssds(List qyqssds) {
		this.qyqssds = qyqssds;
	}

	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public String getQsbaksrq() {
		return qsbaksrq;
	}

	public void setQsbaksrq(String qsbaksrq) {
		this.qsbaksrq = qsbaksrq;
	}

	public String getQsbajsrq() {
		return qsbajsrq;
	}

	public void setQsbajsrq(String qsbajsrq) {
		this.qsbajsrq = qsbajsrq;
	}

	public String getQssbksrq() {
		return qssbksrq;
	}

	public void setQssbksrq(String qssbksrq) {
		this.qssbksrq = qssbksrq;
	}

	public String getQssbjsrq() {
		return qssbjsrq;
	}

	public void setQssbjsrq(String qssbjsrq) {
		this.qssbjsrq = qssbjsrq;
	}

	public String getHid_qssbksrq() {
		return hid_qssbksrq;
	}

	public void setHid_qssbksrq(String hid_qssbksrq) {
		this.hid_qssbksrq = hid_qssbksrq;
	}

	public String getHid_qssbjsrq() {
		return hid_qssbjsrq;
	}

	public void setHid_qssbjsrq(String hid_qssbjsrq) {
		this.hid_qssbjsrq = hid_qssbjsrq;
	}

	public String getQsllry() {
		return qsllry;
	}

	public void setQsllry(String qsllry) {
		this.qsllry = qsllry;
	}

	public String getJyqxjm() {
		return jyqxjm;
	}

	public void setJyqxjm(String jyqxjm) {
		this.jyqxjm = jyqxjm;
	}

	public String getGdjyjs() {
		return gdjyjs;
	}

	public void setGdjyjs(String gdjyjs) {
		this.gdjyjs = gdjyjs;
	}

	public String getYfdxgb() {
		return yfdxgb;
	}

	public void setYfdxgb(String yfdxgb) {
		this.yfdxgb = yfdxgb;
	}

	public String getYfxgpc() {
		return yfxgpc;
	}

	public void setYfxgpc(String yfxgpc) {
		this.yfxgpc = yfxgpc;
	}

	public String getYfgdqs() {
		return yfgdqs;
	}

	public void setYfgdqs(String yfgdqs) {
		this.yfgdqs = yfgdqs;
	}

	public String getQtyy() {
		return qtyy;
	}

	public void setQtyy(String qtyy) {
		this.qtyy = qtyy;
	}

	public String getBaShztbs() {
		return baShztbs;
	}

	public void setBaShztbs(String baShztbs) {
		this.baShztbs = baShztbs;
	}

	public String getBaShztMessage() {
		return baShztMessage;
	}

	public void setBaShztMessage(String baShztMessage) {
		this.baShztMessage = baShztMessage;
	}

	public String getBaShtgrq() {
		return baShtgrq;
	}

	public void setBaShtgrq(String baShtgrq) {
		this.baShtgrq = baShtgrq;
	}

	public String getSbShztbs() {
		return sbShztbs;
	}

	public void setSbShztbs(String sbShztbs) {
		this.sbShztbs = sbShztbs;
	}

	public String getSbShztMessage() {
		return sbShztMessage;
	}

	public void setSbShztMessage(String sbShztMessage) {
		this.sbShztMessage = sbShztMessage;
	}

	public String getSbShtgrq() {
		return sbShtgrq;
	}

	public void setSbShtgrq(String sbShtgrq) {
		this.sbShtgrq = sbShtgrq;
	}

	public String getSknd() {
		return sknd;
	}

	public void setSknd(String sknd) {
		this.sknd = sknd;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getIsBcsbrq() {
		return isBcsbrq;
	}

	public void setIsBcsbrq(String isBcsbrq) {
		this.isBcsbrq = isBcsbrq;
	}
	
}
