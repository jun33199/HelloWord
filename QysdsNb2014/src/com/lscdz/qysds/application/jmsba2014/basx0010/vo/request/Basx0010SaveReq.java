package com.lscdz.qysds.application.jmsba2014.basx0010.vo.request;

import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.inner.Basx0010Vo;

public class Basx0010SaveReq {
	
	private JmsbaBaseVo jmsbaBaseData=new JmsbaBaseVo();

	public JmsbaBaseVo getJmsbaBaseData() {
		return jmsbaBaseData;
	}

	public void setJmsbaBaseData(JmsbaBaseVo jmsbaBaseData) {
		this.jmsbaBaseData = jmsbaBaseData;
	}
	
	//资源综合利用企业（项目）申请减免企业所得税备案事项数据
	private Basx0010Vo basx0010Data=new Basx0010Vo();

	public Basx0010Vo getBasx0010Data() {
		return basx0010Data;
	}

	public void setBasx0010Data(Basx0010Vo basx0010Data) {
		this.basx0010Data = basx0010Data;
	}
}
