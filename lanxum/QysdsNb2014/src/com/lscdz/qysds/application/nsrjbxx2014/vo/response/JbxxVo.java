package com.lscdz.qysds.application.nsrjbxx2014.vo.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;

public class JbxxVo {
	//�жϻ�����Ϣ��2014�Ƿ������ݵı�־
	public String flag;
	//��Ҫ�ɶ�
	public String zygd_del="";
	//����Ͷ��
	public String dwtz_del="";
	//��ҵ������Ϣ
	public List<QyjbxxVo> qyjcxxList=new ArrayList<QyjbxxVo>();
	//2014����ҵ����˰��˰�˻�����Ϣ��
	public List<sb_jl_qysds_nsrjbxxb_2014> jbxxList2014=new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();
	//��ҵ��Ҫ�ɶ�
	public List<sb_jl_qysds_qyzygd> qyzygdList=new ArrayList<sb_jl_qysds_qyzygd>();
	//����Ͷ��
	public List<sb_jl_qysds_dwtz> dwtzList=new ArrayList<sb_jl_qysds_dwtz>();
	public String getZygd_del() {
		return zygd_del;
	}
	public void setZygd_del(String zygd_del) {
		this.zygd_del = zygd_del;
	}
	public String getDwtz_del() {
		return dwtz_del;
	}
	public void setDwtz_del(String dwtz_del) {
		this.dwtz_del = dwtz_del;
	}	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<sb_jl_qysds_dwtz> getDwtzList() {
		return dwtzList;
	}
	public void setDwtzList(List<sb_jl_qysds_dwtz> dwtzList) {
		this.dwtzList = dwtzList;
	}
	public List<sb_jl_qysds_qyzygd> getQyzygdList() {
		return qyzygdList;
	}
	public void setQyzygdList(List<sb_jl_qysds_qyzygd> qyzygdList) {
		this.qyzygdList = qyzygdList;
	}
	public List<sb_jl_qysds_nsrjbxxb_2014> getJbxxList2014() {
		return jbxxList2014;
	}
	public void setJbxxList2014(List<sb_jl_qysds_nsrjbxxb_2014> jbxxList2014) {
		this.jbxxList2014 = jbxxList2014;
	}
	public List<QyjbxxVo> getQyjcxxList() {
		return qyjcxxList;
	}
	public void setQyjcxxList(List<QyjbxxVo> qyjcxxList) {
		this.qyjcxxList = qyjcxxList;
	}
	
}
