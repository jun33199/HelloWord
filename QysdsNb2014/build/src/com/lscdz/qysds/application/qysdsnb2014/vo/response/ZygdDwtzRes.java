package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;

public class ZygdDwtzRes {
	//企业主要股东
	public List<sb_jl_qysds_qyzygd> qyzygdList=new ArrayList<sb_jl_qysds_qyzygd>();
	//对外投资
	public List<sb_jl_qysds_dwtz> dwtzList=new ArrayList<sb_jl_qysds_dwtz>();
	public List<sb_jl_qysds_qyzygd> getQyzygdList() {
		return qyzygdList;
	}
	public void setQyzygdList(List<sb_jl_qysds_qyzygd> qyzygdList) {
		this.qyzygdList = qyzygdList;
	}
	public List<sb_jl_qysds_dwtz> getDwtzList() {
		return dwtzList;
	}
	public void setDwtzList(List<sb_jl_qysds_dwtz> dwtzList) {
		this.dwtzList = dwtzList;
	}
	
}
