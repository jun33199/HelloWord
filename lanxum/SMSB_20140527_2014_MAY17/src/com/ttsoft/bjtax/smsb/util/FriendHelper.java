package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;



public class FriendHelper
{
	/**
	 * ��ȡ˰Դ��ʶ
	 *  ���ء� CODE_QYSDS_SYBS_D = "0"
	 *  ���ܡ� CODE_QYSDS_SYBS_Z = "1";
	 *  ���֡� CODE_QYSDS_SYBS_F = "2";
	 *  �������򡰲�����ʶ�Ϊ����������˰��" CODE_QYSDS_SYBS_OTHER = "3";
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
			if(sybs_temp.equals("��")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_D;
			}else if(sybs_temp.equals("��")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_Z;
			}else if(sybs_temp.equals("��")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_F;
			}else{
				sybs=CodeConstant.CODE_QYSDS_SYBS_OTHER;
			}
		}
		return sybs;
	}
}
