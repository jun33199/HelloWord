package com.lscdz.qysds.application.jmsba2014.basx0010.vo.response;

import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.inner.Basx0010Vo;

public class Basx0010ViewRes {
	
	//����������������
	private JmsbaBaseVo jmsbaBaseData=new JmsbaBaseVo();
	//��Դ�ۺ�������ҵ����Ŀ�����������ҵ����˰������������
	private Basx0010Vo basx0010Data=new Basx0010Vo();
	
	public JmsbaBaseVo getJmsbaBaseData() {
		return jmsbaBaseData;
	}

	public void setJmsbaBaseData(JmsbaBaseVo jmsbaBaseData) {
		this.jmsbaBaseData = jmsbaBaseData;
	}
	
	

	public Basx0010Vo getBasx0010Data() {
		return basx0010Data;
	}

	public void setBasx0010Data(Basx0010Vo basx0010Data) {
		this.basx0010Data = basx0010Data;
	}
}
