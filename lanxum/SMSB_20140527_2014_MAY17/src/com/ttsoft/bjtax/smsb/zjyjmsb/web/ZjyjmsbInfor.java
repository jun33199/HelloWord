package com.ttsoft.bjtax.smsb.zjyjmsb.web;

import java.io.Serializable;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx3;
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �پ�ҵ�����걨ǰ��̨�������ݶ���</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0 2006-5-15
 */

public class ZjyjmsbInfor implements Serializable
{
    public ZjyjmsbInfor()
    {
    }

    //�پ�ҵ�����걨������Ϣ
    private Zjyjmsbz zjyjmsbz;
    //�پ�ҵ�����걨��ϸ��12��List
    private List zjyjmsbmxList;
    //�پ�ҵ�����걨��ϸ��3
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