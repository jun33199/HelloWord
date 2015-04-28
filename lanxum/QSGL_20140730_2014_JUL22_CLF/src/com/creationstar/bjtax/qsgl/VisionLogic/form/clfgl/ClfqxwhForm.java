package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhBo;


public class ClfqxwhForm extends BaseForm{
	
	//������Ա
	private String allNewAddInfo;
	
	private String dyz;
	

	/**
     * ¼����
     */
    private String lrr;

    /**
     * ¼������
     */
    private String lrrq;
    
    /**
	 * ���������ײ�����
	 */
    private ArrayList jycsList= new ArrayList();
    
    /**
     *�ڴ˴β���֮ǰ�Ѿ�ע�����б�
     */
    private ArrayList yqyjqhList = new ArrayList();
    
    /**
     * �д�����"�ʻ�����"������, Ϊһ����ʽΪ
     * "zhdm0_ztbs0 zhdm1_ztbs1"���ַ���,
     * �������map�У�����map����key�����ظ�
     * �����ԣ���ÿ��key-value��,��˳������
     * ���һ��һ��Ϊ����״̬
     */
    private String datastr;
    
    /**
     * �������key-value��map
     */
    private HashMap dataMap = new HashMap();
    
  //�кŷ�ҳ����

    /**
     * ҳ��
     */
    private int ym;

    /**
     * ÿҳ����
     */
    private String myhs;

    /**
     * ������
     */
    private int zhs;

    /**
     * ��ʼ��
     */
    private String qsh;

    /**
     * ��ҳ��
     */
    private int zys;
    
    
    public Object getData(){
    	ClfqxwhBo data = new ClfqxwhBo();
    	data.setAllNewAddInfo(this.allNewAddInfo);
    	
    	data.setDyz(this.dyz);
    	
    	return data;
    }
    
    
    

    /**
     * Ĭ�Ϲ�����
     */
    public ClfqxwhForm()
    {
        this.setYm(1);
        this.setQsh("0");
        this.setMyhs("10");
    }
    
    /**
     * ��ҳ
     */
    public void resetPage()
    {
        this.setYm(1);
        this.setZhs(0);
        this.setQsh("0");
        this.setZys(0);
    }
    
    /**
     * ���form
     */
    public void clearForm()
    {
        this.datastr = "";
        this.dataMap = new HashMap();
        this.yqyjqhList = new ArrayList();
        this.yqyjqhList = new ArrayList();
        this.allNewAddInfo ="";
    }

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public ArrayList getJycsList() {
		return jycsList;
	}

	public void setJycsList(ArrayList jycsList) {
		this.jycsList = jycsList;
	}

	public ArrayList getYqyjqhList() {
		return yqyjqhList;
	}

	public void setYqyjqhList(ArrayList yqyjqhList) {
		this.yqyjqhList = yqyjqhList;
	}

	public String getDatastr() {
		return datastr;
	}

	public void setDatastr(String datastr) {
		this.datastr = datastr;
	}

	public void setDataMap(HashMap dataMap) {
		this.dataMap = dataMap;
	}

	public int getYm() {
		return ym;
	}

	public void setYm(int ym) {
		this.ym = ym;
	}

	public String getMyhs() {
		return myhs;
	}

	public void setMyhs(String myhs) {
		this.myhs = myhs;
	}

	public int getZhs() {
		return zhs;
	}

	public void setZhs(int zhs) {
		this.zhs = zhs;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public int getZys() {
		return zys;
	}

	public void setZys(int zys) {
		this.zys = zys;
	}
	
	/**
     * ������ݱ�
     *
     * @return HashMap the HashMap
     */
    public HashMap getDataMap()
    {
        String tmpStr;
        String tmpDM;
        String tmpZT;
        StringTokenizer token = new StringTokenizer(this.datastr, " ");

        while (token.hasMoreTokens())
        {
            tmpStr = token.nextToken();
            tmpDM = tmpStr.substring(0, tmpStr.length()-2);
            tmpZT = tmpStr.substring(tmpStr.length()-1, tmpStr.length());
            System.out.println("tmpDM:"+tmpDM+" tmpZT:"+tmpZT);
            dataMap.put(tmpDM, tmpZT);
        }

        return dataMap;
    }

	public String getAllNewAddInfo() {
		return allNewAddInfo;
	}

	public void setAllNewAddInfo(String allNewAddInfo) {
		this.allNewAddInfo = allNewAddInfo;
	}




	public String getDyz() {
		return dyz;
	}




	public void setDyz(String dyz) {
		this.dyz = dyz;
	}
}
