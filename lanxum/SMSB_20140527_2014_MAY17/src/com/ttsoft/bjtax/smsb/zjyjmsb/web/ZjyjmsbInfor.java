package com.ttsoft.bjtax.smsb.zjyjmsb.web;

import java.io.Serializable;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx3;
/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 再就业减免申报前后台交互数据对象</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0 2006-5-15
 */

public class ZjyjmsbInfor implements Serializable
{
    public ZjyjmsbInfor()
    {
    }

    //再就业减免申报主表信息
    private Zjyjmsbz zjyjmsbz;
    //再就业减免申报明细表12的List
    private List zjyjmsbmxList;
    //再就业减免申报明细表3
    private Zjyjmsbmx3 zjyjmsbmx3 ;
	public List getZjyjmsbmxList() {
		return zjyjmsbmxList;
	}
	public void setZjyjmsbmxList(List zjyjmsbmxList) {
		this.zjyjmsbmxList = zjyjmsbmxList;
	}
	public Zjyjmsbz getZjyjmsbz() {
		return zjyjmsbz;
	}
	public void setZjyjmsbz(Zjyjmsbz zjyjmsbz) {
		this.zjyjmsbz = zjyjmsbz;
	}
	public Zjyjmsbmx3 getZjyjmsbmx3() {
		return zjyjmsbmx3;
	}
	public void setZjyjmsbmx3(Zjyjmsbmx3 zjyjmsbmx3) {
		this.zjyjmsbmx3 = zjyjmsbmx3;
	}
    
}