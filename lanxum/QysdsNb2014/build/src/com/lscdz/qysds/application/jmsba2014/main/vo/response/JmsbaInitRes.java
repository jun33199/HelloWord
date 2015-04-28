package com.lscdz.qysds.application.jmsba2014.main.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSqlx;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSxdm;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaZgswjg;

/**
 * 减免税备案初始化
 * 项目名称：QysdsNb2014   
 * 类名称：JmsbaInitRes   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2015-1-6 下午3:37:35   
 * 修改人：wangcy   
 * 修改时间：2015-1-6 下午3:37:35   
 * 修改备注：   
 * @version  1.0
 */
public class JmsbaInitRes implements java.io.Serializable{

	private static final long serialVersionUID = -6370588162599672740L;
	
	private String add_band="";

	// 减免税备案事项代码
	List<JmsbaSxdm> jmsbaSxdmList = new ArrayList<JmsbaSxdm>();

	//减免税备案申请类型
	List<JmsbaSqlx> jmsbaSqlxList = new ArrayList<JmsbaSqlx>();
	//主管税务机关
	List<JmsbaZgswjg> jmsbaZgswjgList = new ArrayList<JmsbaZgswjg>();
	public List<JmsbaSxdm> getJmsbaSxdmList() {
		return jmsbaSxdmList;
	}

	public void setJmsbaSxdmList(List<JmsbaSxdm> jmsbaSxdmList) {
		this.jmsbaSxdmList = jmsbaSxdmList;
	}

	public String getAdd_band() {
		return add_band;
	}

	public void setAdd_band(String add_band) {
		this.add_band = add_band;
	}

	public List<JmsbaSqlx> getJmsbaSqlxList() {
		return jmsbaSqlxList;
	}

	public void setJmsbaSqlxList(List<JmsbaSqlx> jmsbaSqlxList) {
		this.jmsbaSqlxList = jmsbaSqlxList;
	}

	public List<JmsbaZgswjg> getJmsbaZgswjgList() {
		return jmsbaZgswjgList;
	}

	public void setJmsbaZgswjgList(List<JmsbaZgswjg> jmsbaZgswjgList) {
		this.jmsbaZgswjgList = jmsbaZgswjgList;
	}
	
	
}
