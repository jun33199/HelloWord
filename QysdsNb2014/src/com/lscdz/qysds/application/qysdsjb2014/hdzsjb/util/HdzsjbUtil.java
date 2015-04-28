package com.lscdz.qysds.application.qysdsjb2014.hdzsjb.util;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.qysdsjb2014.hdzsjb.vo.HdzsjbVo;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjbzhy;
import com.lscdz.util.codetable.CodeTableManager;

public class HdzsjbUtil {
	/**
	 * 核定征收季报初始化代码表
	 * @param czzssdsjbForm
	 */
	public static void initCodeTable(HdzsjbVo hdzsjbVo){
        //国家标准行业
		List<gy_dm_gjbzhy> gjbzhy=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_GJBZHY);
        ArrayList<gy_dm_gjbzhy> gjbzhyList=new ArrayList<gy_dm_gjbzhy>();
        for(int i=0;i<gjbzhy.size();i++){
        	gy_dm_gjbzhy gjbzhyObject=(gy_dm_gjbzhy)gjbzhy.get(i);
        	String gjbzhydm=gjbzhyObject.getGjbzhydm();
        	if(gjbzhydm!=null && !gjbzhydm.equals("")&&gjbzhydm.length()==4){
        		gjbzhyList.add(gjbzhyObject);
//        		System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
        	}
        	
        }
        hdzsjbVo.setGjbzhyList(gjbzhyList);
	}
	
}
