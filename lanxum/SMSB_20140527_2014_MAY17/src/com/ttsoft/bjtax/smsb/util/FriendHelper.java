package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;



public class FriendHelper
{
	/**
	 * 获取税源标识
	 *  “地” CODE_QYSDS_SYBS_D = "0"
	 *  “总” CODE_QYSDS_SYBS_Z = "1";
	 *  “分” CODE_QYSDS_SYBS_F = "2";
	 *  “国”或“不”标识填报为空其他的纳税人" CODE_QYSDS_SYBS_OTHER = "3";
	 * @param swdjjbsj
	 * @return
	 */
	public static String getNsrSybs(SWDJJBSJ swdjjbsj){
		String sybs="";
		String qyzy=swdjjbsj.getQyzy();
		if(qyzy==null || qyzy.length()<1){
			sybs=CodeConstant.CODE_QYSDS_SYBS_OTHER;
		}else{
			String sybs_temp=qyzy.substring(0,1);
			if(sybs_temp.equals("地")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_D;
			}else if(sybs_temp.equals("总")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_Z;
			}else if(sybs_temp.equals("分")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_F;
			}else{
				sybs=CodeConstant.CODE_QYSDS_SYBS_OTHER;
			}
		}
		return sybs;
	}
}
