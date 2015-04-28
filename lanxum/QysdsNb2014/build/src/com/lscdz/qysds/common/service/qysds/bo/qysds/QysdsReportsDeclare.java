package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;
import com.lscdz.qysds.common.util.StringUtil;

/**
 * 企业所得税报表申明对象
 * 项目名称：QysdsNb2014   
 * 类名称：QysdsReportsDeclare   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:11:11   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:11:11   
 * 修改备注：   
 * @version  1.0
 */
public class QysdsReportsDeclare extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7247138197942969139L;

	/**
	 * 纳税人基本信息
	 */
	private Jbxx jbxx;

	/**
	 * 应用编号
	 */
	private String appid;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 纳税人计算机代码
	 */
	private String nsrjsjdm;

	/**
	 * 纳税人名称
	 */
	private String nsrmc;

	/**
	 * 报表期类型
	 */
	private String bbqlx;

	/**
	 * 期号
	 */
	private String qh;

	/**
	 * 审核通过标识
	 */
	private boolean CheckFlag = false;

	/**
	 * 税款所属开始日期
	 */
	private Timestamp skssksrq;

	/**
	 * 税款所属结束日期
	 */
	private Timestamp skssjsrq;

	/**
	 * 申报日期
	 */
	private Timestamp sbrq;

	/**
	 * 税款年度
	 */
	private String sknd;

	/**
	 * 税务计算机代码
	 */
	private String swjsjdm;

	/**
	 * 税务机关组织机构代码
	 */
	private String swjgzzjgdm;

	/**
	 * 区县代码
	 */
	private String qxdm;

	/**
	 * 录入人
	 */
	private String lrr;

	/**
	 * 录入日期
	 */
	private Timestamp lrrq;

	/**
	 * 创建人
	 */
	private String cjr;

	/**
	 * 创建日期
	 */
	private Timestamp cjrq;

	/**
	 * 填报表内容,key为报表编号,成员为com.lscdz.qysds.application.qysdsnb2014.bo.qysds.
	 * QysdsReportsTableDeclare
	 */
	@SuppressWarnings("rawtypes")
	private Map tableContentList = new HashMap();

	/**
	 * 校验参数
	 * 
	 * @param rangeFlag
	 *            校验范围参数，0-保存方法参数校验，1-查询方法参数校验，2-删除方法参数校验
	 *            ，3-保存单表方法参数校验，4-查询单表方法参数校验，5-删除单表方法参数校验
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkValid(int rangeFlag) {
		boolean flag = true;
		// ///////////////////以下为校验必添项 START////////////////////////////////
		if (StringUtil.killNull2(this.appid) == null) {
			return false;
		} else if (StringUtil.killNull2(this.nsrjsjdm) == null) {
			return false;
		} else if (StringUtil.killNull2(this.sknd) == null) {
			return false;
		} else if (StringUtil.killNull2(this.bbqlx) == null) {
			return false;
		} else if (StringUtil.killNull2(this.qh) == null) {
			return false;
		}
		// ///////////////////以上为校验必添项 END////////////////////////////////
		// ///////////////////以下为保存动作校验必添项 START////////////////////////
		if (0 == rangeFlag) {
			/**
			 * @todo 保存动作校验必添项
			 */
		}
		// ///////////////////以上为保存动作校验必添项 END//////////////////////////
		// ///////////////////以下为查询动作校验必添项 START////////////////////////
		if (1 == rangeFlag) {
			/**
			 * @todo 查询动作校验必添项
			 */
		}
		// ///////////////////以上为查询动作校验必添项 END//////////////////////////
		// ///////////////////以下为删除动作校验必添项 START////////////////////////
		if (2 == rangeFlag) {
			/**
			 * @todo 删除动作校验必添项
			 */
		}
		// ///////////////////以上为删除动作校验必添项 END//////////////////////////
		// ///////////////////以下为保存单表动作校验必添项 START////////////////////////
		if (3 == rangeFlag) {
			/**
			 * @todo保存单表动作校验必添项
			 */
		}
		// ///////////////////以上为保存单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为查询单表动作校验必添项 START////////////////////////
		if (4 == rangeFlag) {
			/**
			 * @todo查询单表动作校验必添项
			 */
		}
		// ///////////////////以上为查询单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为删除单表动作校验必添项 START////////////////////////
		if (5 == rangeFlag) {
			/**
			 * @todo删除单表动作校验必添项
			 */
		}
		// ///////////////////以上为删除单表动作校验必添项 END//////////////////////////
		// 校验字节点信息
		Iterator iterator = tableContentList.keySet().iterator();
		QysdsReportsTableDeclare qrtd;
		while (iterator.hasNext()) {
			qrtd = (QysdsReportsTableDeclare) (tableContentList.get(iterator
					.next()));
			flag = flag && qrtd.checkValid(rangeFlag);
		}
		// 校验基本信息
		if (jbxx != null) {
			flag = flag && this.jbxx.checkValid(rangeFlag);
		} else {
			throw new ArithmeticException("基本信息为空！！请检查输入参数！！");
		}
		//
		return flag;
	}

	/**
	 * 获取一个报表的内容描述
	 * 
	 * @return 报表的内容描述
	 */
	@SuppressWarnings("rawtypes")
	public String getContents() {

		StringBuffer sb = new StringBuffer();
		sb.append(jbxx.getContents());
		sb.append("|");
		sb.append("appid:");
		sb.append(appid);
		sb.append("|");
		sb.append("version:");
		sb.append(version);
		sb.append("|");
		sb.append("nsrjsjdm:");
		sb.append(nsrjsjdm);
		sb.append("|");
		sb.append("nsrmc:");
		sb.append(nsrmc);
		sb.append("|");
		sb.append("bbqlx:");
		sb.append(bbqlx);
		sb.append("|");
		sb.append("qh:");
		sb.append(qh);
		sb.append("|");
		sb.append("skssksrq:");
		sb.append(skssksrq);
		sb.append("|");
		sb.append("skssjsrq:");
		sb.append(skssjsrq);
		sb.append("|");
		sb.append("sbrq:");
		sb.append(sbrq);
		sb.append("|");
		sb.append("sknd:");
		sb.append(sknd);
		sb.append("|");
		sb.append("swjgzzjgdm:");
		sb.append(swjgzzjgdm);
		sb.append("|");
		sb.append("qxdm:");
		sb.append(qxdm);
		sb.append("|");
		sb.append("lrr:");
		sb.append(lrr);
		sb.append("|");
		sb.append("lrrq:");
		sb.append(lrrq);
		sb.append("|");
		sb.append("cjr:");
		sb.append(cjr);
		sb.append("|");
		sb.append("cjrq:");
		sb.append(cjrq);
		sb.append("|{");
		Iterator iterator = tableContentList.keySet().iterator();
		QysdsReportsTableDeclare qrtd;
		while (iterator.hasNext()) {
			qrtd = (QysdsReportsTableDeclare) (tableContentList.get(iterator
					.next()));
			sb.append(qrtd.getContents());
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid
	 *            the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the bbqlx
	 */
	public String getBbqlx() {
		return bbqlx;
	}

	/**
	 * @param bbqlx
	 *            the bbqlx to set
	 */
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	/**
	 * @return the cjr
	 */
	public String getCjr() {
		return cjr;
	}

	/**
	 * @param cjr
	 *            the cjr to set
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	/**
	 * @return the cjrq
	 */
	public Timestamp getCjrq() {
		return cjrq;
	}

	/**
	 * @param cjrq
	 *            the cjrq to set
	 */
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}

	/**
	 * @param lrr
	 *            the lrr to set
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	/**
	 * @return the lrrq
	 */
	public Timestamp getLrrq() {
		return lrrq;
	}

	/**
	 * @param lrrq
	 *            the lrrq to set
	 */
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	/**
	 * @return the nsrjsjdm
	 */
	public String getNsrjsjdm() {
		return nsrjsjdm;
	}

	/**
	 * @param nsrjsjdm
	 *            the nsrjsjdm to set
	 */
	public void setNsrjsjdm(String nsrjsjdm) {
		this.nsrjsjdm = nsrjsjdm;
	}

	/**
	 * @return the nsrmc
	 */
	public String getNsrmc() {
		return nsrmc;
	}

	/**
	 * @param nsrmc
	 *            the nsrmc to set
	 */
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	/**
	 * @return the qh
	 */
	public String getQh() {
		return qh;
	}

	/**
	 * @param qh
	 *            the qh to set
	 */
	public void setQh(String qh) {
		this.qh = qh;
	}

	/**
	 * @return the qxdm
	 */
	public String getQxdm() {
		return qxdm;
	}

	/**
	 * @param qxdm
	 *            the qxdm to set
	 */
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}

	/**
	 * @return the sbrq
	 */
	public Timestamp getSbrq() {
		return sbrq;
	}

	/**
	 * @param sbrq
	 *            the sbrq to set
	 */
	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	/**
	 * @return the sknd
	 */
	public String getSknd() {
		return sknd;
	}

	/**
	 * @param sknd
	 *            the sknd to set
	 */
	public void setSknd(String sknd) {
		this.sknd = sknd;
	}

	/**
	 * @return the skssjsrq
	 */
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	/**
	 * @param skssjsrq
	 *            the skssjsrq to set
	 */
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	/**
	 * @return the skssksrq
	 */
	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	/**
	 * @param skssksrq
	 *            the skssksrq to set
	 */
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	/**
	 * @return the swjgzzjgdm
	 */
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	/**
	 * @param swjgzzjgdm
	 *            the swjgzzjgdm to set
	 */
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	/**
	 * @return the tableContentList
	 */
	@SuppressWarnings("rawtypes")
	public Map getTableContentList() {
		return tableContentList;
	}

	/**
	 * @param tableContentList
	 *            the tableContentList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setTableContentList(Map tableContentList) {
		this.tableContentList = tableContentList;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the jbxx
	 */
	public Jbxx getJbxx() {
		return jbxx;
	}

	public String getSwjsjdm() {
		return swjsjdm;
	}

	public boolean isCheckFlag() {
		return CheckFlag;
	}

	/**
	 * @param jbxx
	 *            the jbxx to set
	 */
	public void setJbxx(Jbxx jbxx) {
		this.jbxx = jbxx;
	}

	public void setSwjsjdm(String swjsjdm) {
		this.swjsjdm = swjsjdm;
	}

	public void setCheckFlag(boolean CheckFlag) {
		this.CheckFlag = CheckFlag;
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

}
