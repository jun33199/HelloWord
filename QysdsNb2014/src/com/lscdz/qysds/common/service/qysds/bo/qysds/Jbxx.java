package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

public class Jbxx extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7487743428169578159L;

	/**
	 * 计算机代码
	 */
	public String jsjdm;

	/**
	 * 纳税人名称
	 */
	public String nsrmc;

	/**
	 * 所属经济类型
	 */
	public String ssjjlx;

	/**
	 * 联系电话
	 */
	public String lxdh;

	/**
	 * 经营地址
	 */
	public String jydz;

	/**
	 * 所属行业
	 */
	public String sshy;

	/*
	 * 征收方式
	 */
	public String zsfs;

	/**
	 * 财会制度
	 */
	public String ckzd;

	/**
	 * 工资管理形式
	 */
	public String gzglxs;

	/**
	 * 减免类型
	 */
	public String jmlx;

	/**
	 * 系统级别
	 */
	public String xtjb;

	/**
	 * 报表描述符
	 */
	public String bbmsf;

	public String sybs;
	/**
	 * 校验参数
	 * 
	 * @param rangeFlag
	 *            校验范围参数，0-保存方法参数校验，1-查询方法参数校验，2-删除方法参数校验
	 *            ，3-保存单表方法参数校验，1-查询单表方法参数校验，2-删除单表方法参数校验
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 */
	public boolean checkValid(int rangeFlag) {
		boolean flag = true;
		// ///////////////////以下为校验必添项 START////////////////////////////////

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
			 * @todo 保存单表动作校验必添项
			 */
		}
		// ///////////////////以上为保存单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为查询单表动作校验必添项 START////////////////////////
		if (4 == rangeFlag) {
			/**
			 * @todo 查询单表动作校验必添项
			 */
		}
		// ///////////////////以上为查询单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为删除单表动作校验必添项 START////////////////////////
		if (5 == rangeFlag) {
			/**
			 * @todo 删除单表动作校验必添项
			 */
		}
		// ///////////////////以上为删除单表动作校验必添项 END//////////////////////////

		return flag;
	}

	/**
	 * 获取一个报表基本信息的内容描述
	 * 
	 * @return 报表基本信息的内容描述
	 */
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		sb.append("[jsjdm:");
		sb.append(jsjdm);
		sb.append("|");
		sb.append("nsrmc:");
		sb.append(nsrmc);
		sb.append("|");
		sb.append("ssjjlx:");
		sb.append(ssjjlx);
		sb.append("|");
		sb.append("lxdh:");
		sb.append(lxdh);
		sb.append("|");
		sb.append("sshy:");
		sb.append(sshy);
		sb.append("|");
		sb.append("zsfs:");
		sb.append(zsfs);
		sb.append("|");
		sb.append("ckzd:");
		sb.append(ckzd);
		sb.append("|");
		sb.append("gzglxs:");
		sb.append(gzglxs);
		sb.append("|");
		sb.append("jmlx:");
		sb.append(jmlx);
		sb.append("]");
		return sb.toString();

	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	public String getCkzd() {
		return ckzd;
	}

	public String getGzglxs() {
		return gzglxs;
	}

	public String getJmlx() {
		return jmlx;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getSshy() {
		return sshy;
	}

	public String getSsjjlx() {
		return ssjjlx;
	}

	public String getZsfs() {
		return zsfs;
	}

	public String getBbmsf() {
		return bbmsf;
	}

	public String getJydz() {
		return jydz;
	}

	public String getXtjb() {
		return xtjb;
	}

	public void setCkzd(String ckzd) {
		this.ckzd = ckzd;
	}

	public void setGzglxs(String gzglxs) {
		this.gzglxs = gzglxs;
	}

	public void setJmlx(String jmlx) {
		this.jmlx = jmlx;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}

	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}

	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}
}
