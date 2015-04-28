package com.lscdz.qysds.application.qysdsjb2014.hdzsjb.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjbzhy;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2008查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class HdzsjbVo  extends QysdsjbBaseVo{
		
	private String xzqy;
	//一般减免税率
	private String ybjmsl;
	//纳税方式
	private String nsfs;
	//总分机构
	private String zfjg;
	//减免资格
	private String jmzg;
	
	//税款所属年度等于开业登记日期Y 否N
	private String sfxkh;
	//获取税款所属期所在年度上一年度征收方式
	private String syndZsfsdm;
	//获取上一年度核定征收年报行6数据
	private String syndZbh6;
	//获取上一年度汇算清缴主表行25数据
	private String syndZbh25;
	//获取上一年度汇算清缴附表5行45、46、47的校验结果
	private String syndFb5jyjg;
			
	//是否新开业，新开业的不参加税额分摊
	private String isXky;
	private String cyl;
	private String dezsse;
	private String qyzslx;
	private String sysl;
	//获取数据库中的纳税方法和总分机构的域值
	private HashMap nsfs_fsjg = new HashMap();
	private List<gy_dm_gjbzhy> gjbzhyList;
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}


	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	public String getSfxkh() {
		return sfxkh;
	}

	public void setSfxkh(String sfxkh) {
		this.sfxkh = sfxkh;
	}

	public String getSyndZsfsdm() {
		return syndZsfsdm;
	}

	public void setSyndZsfsdm(String syndZsfsdm) {
		this.syndZsfsdm = syndZsfsdm;
	}

	public String getSyndZbh6() {
		return syndZbh6;
	}

	public void setSyndZbh6(String syndZbh6) {
		this.syndZbh6 = syndZbh6;
	}

	public String getSyndZbh25() {
		return syndZbh25;
	}

	public void setSyndZbh25(String syndZbh25) {
		this.syndZbh25 = syndZbh25;
	}

	public String getSyndFb5jyjg() {
		return syndFb5jyjg;
	}

	public void setSyndFb5jyjg(String syndFb5jyjg) {
		this.syndFb5jyjg = syndFb5jyjg;
	}

	public String getIsXky() {
		return isXky;
	}

	public void setIsXky(String isXky) {
		this.isXky = isXky;
	}

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getDezsse() {
		return dezsse;
	}

	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}

	public String getQyzslx() {
		return qyzslx;
	}

	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	public String getSysl() {
		return sysl;
	}

	public void setSysl(String sysl) {
		this.sysl = sysl;
	}

	public List<gy_dm_gjbzhy> getGjbzhyList() {
		return gjbzhyList;
	}

	public void setGjbzhyList(List<gy_dm_gjbzhy> gjbzhyList) {
		this.gjbzhyList = gjbzhyList;
	}




}
