package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: ��ҵ����˰����2014��-������˰��֧���������Form</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author zhangyj
 * @version 1.0
 */
import java.util.*;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.*;


public class GdzcjszjyjqkjbForm extends QysdsNewForm
{
    /**
     * ��Ź̶���Ϣ���ַ�������
     */
    private String[] sb_columns = {"fwjzw_yz", "fwjzw_bqzje", "fwjzw_ljzje", "jqsbhqtgdzc_yz","jqsbhqtgdzc_bqzje", 
    		"jqsbhqtgdzc_ljzje", "hj_yz", "hj_bqzje_zczje", "hj_bqzje_jszje", "hj_ljzje_zczje", "hj_ljzje_jszje"};
    /**
     * ��Ź̶������ݵ�LIST
     */
    private ArrayList gjbzhy;
    private String gjbzhydm;
    private String jumpFlag="";
    
    private List gdzcjszjyjqkjbList = new ArrayList();

	public String[] getSb_columns() {
		return sb_columns;
	}
	public void setSb_columns(String[] sbColumns) {
		sb_columns = sbColumns;
	}
	public List getGdzcjszjyjqkjbList() {
		return gdzcjszjyjqkjbList;
	}
	public void setGdzcjszjyjqkjbList(List gdzcjszjyjqkjbList) {
		this.gdzcjszjyjqkjbList = gdzcjszjyjqkjbList;
	}
	public ArrayList getGjbzhy() {
		return gjbzhy;
	}
	public void setGjbzhy(ArrayList gjbzhy) {
		this.gjbzhy = gjbzhy;
	}
	public String getGjbzhydm() {
		return gjbzhydm;
	}
	public void setGjbzhydm(String gjbzhydm) {
		this.gjbzhydm = gjbzhydm;
	}
	public String getJumpFlag() {
		return jumpFlag;
	}
	public void setJumpFlag(String jumpFlag) {
		this.jumpFlag = jumpFlag;
	}



    
}
