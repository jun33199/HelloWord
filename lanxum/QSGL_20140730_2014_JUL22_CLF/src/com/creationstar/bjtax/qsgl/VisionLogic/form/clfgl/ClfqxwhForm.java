package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhBo;


public class ClfqxwhForm extends BaseForm{
	
	//新增人员
	private String allNewAddInfo;
	
	private String dyz;
	

	/**
     * 录入人
     */
    private String lrr;

    /**
     * 录入日期
     */
    private String lrrq;
    
    /**
	 * 存量房交易参数表
	 */
    private ArrayList jycsList= new ArrayList();
    
    /**
     *在此次操作之前已经注销的列表
     */
    private ArrayList yqyjqhList = new ArrayList();
    
    /**
     * 有待进行"帐户反清"的数据, 为一个格式为
     * "zhdm0_ztbs0 zhdm1_ztbs1"的字符串,
     * 将其放在map中，由于map本身key不能重复
     * 的特性，对每个key-value对,按顺序放入的
     * 最后一个一个为最终状态
     */
    private String datastr;
    
    /**
     * 存放以上key-value的map
     */
    private HashMap dataMap = new HashMap();
    
  //有号分页变量

    /**
     * 页码
     */
    private int ym;

    /**
     * 每页行数
     */
    private String myhs;

    /**
     * 总行数
     */
    private int zhs;

    /**
     * 起始行
     */
    private String qsh;

    /**
     * 总页数
     */
    private int zys;
    
    
    public Object getData(){
    	ClfqxwhBo data = new ClfqxwhBo();
    	data.setAllNewAddInfo(this.allNewAddInfo);
    	
    	data.setDyz(this.dyz);
    	
    	return data;
    }
    
    
    

    /**
     * 默认构造器
     */
    public ClfqxwhForm()
    {
        this.setYm(1);
        this.setQsh("0");
        this.setMyhs("10");
    }
    
    /**
     * 翻页
     */
    public void resetPage()
    {
        this.setYm(1);
        this.setZhs(0);
        this.setQsh("0");
        this.setZys(0);
    }
    
    /**
     * 清空form
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
     * 获的数据表
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
