package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;
/**
 *��ѯͳ��������˰��� form
 * 201307
 * wangxq
 */
public class GdzysJmszmCxForm extends BaseForm{
	
	
	//�־ִ��루��ѯ�ۿ���ϸ��Ϣ�ã�
	private String fjdmxx="";
	//˰�������루��ѯ�ۿ���ϸ��Ϣ�ã�
	private String swsdmxx="";
	//���д��루��ѯ�ۿ���ϸ��Ϣ�ã�
	private String yhdmxx="";
	//�ۿ�ʱ�䣨��ѯ�ۿ���ϸ��Ϣ�ã�
	private String kksjxx="";
	/**
     * ��ҳ��
     */
    private int maxPage;
	
    /**
     * ÿҳ��ʾ�ļ�¼����Ĭ��Ϊ10
     */
    private int pageSize =10;
    /**
     * ��ǰҳ��
     */
    private int currentPage = 1;
    /**
     * �˶Բ�һ�±���
     */
    private int totalRowCount = 0;
    
    
    
	/**
     * ��ҳ�����ۿ���ϸ��Ϣ��
     */
    private int maxPageXx;
	
    /**
     * ÿҳ��ʾ�ļ�¼����Ĭ��Ϊ10���ۿ���ϸ��Ϣ��
     */
    private int pageSizeXx =10;
    /**
     * ��ǰҳ�루�ۿ���ϸ��Ϣ��
     */
    private int currentPageXx = 1;
    /**
     * �˶Բ�һ�±������ۿ���ϸ��Ϣ��
     */
    private int totalRowCountXx = 0;
    
    
    
    
    /**
	   * ҳ����ʾ��Ϣ
	*/
	private String message;
	
	/**
	   * ҳ����ʾ��Ϣ
	 */
	  private String yhjb="";
	
	 //���д���
	 String yhdm="";
	 //˰����
	 String cxdqjs="";
	 //���
	 String nd;
	 //�ۿ���ʼ�·�
	 String ystart;
	 //�ۿ���ֹ�·�
	 String yend;
	 //�־�List
	 private List swdwlist;
	 //˰����List
	 private List cxswdwlist;
	 //��������
	 private String dqjs="";;
	 
	 //�Ƿ�����˲�ѯ����
	 private boolean queryFlag = false;
	 
	 //�ܽ��
	 String zje="";
	 //�ɹ��ܽ��
	 String cgZje="";
	 //ʧ���ܽ��
	 String sbZje="";
	 
	 //�ܱ���
	 String zbs="";
	 //�ܳɹ�����
	 String zcgbs="";
	 //��ʧ�ܱ���
	 String zsbbs="";
	 
	 //����List
	 private List dataList=new ArrayList();
	 
	 
	 //�ɹ�ʧ�ܱ�ʶ
	 String cgsbFlag="";
	 //����˰֤��״̬
	 private String status="";
	 //����˰֤�����
	 private String jmszmbh="";
	 
	 
	 
	 public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getJmszmbh() {
		return jmszmbh;
	}




	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
	}




	public int getMaxPage (){
	        try{
	            maxPage = this.getTotalRowCount() / this.getPageSize();
	            int number = totalRowCount % pageSize;
	            if(number != 0){
	                maxPage += 1;
	            }
	        } catch(Exception ex){
	            maxPage = 0;
	        }
	        return maxPage;

	}

	 


	public String getCgsbFlag() {
		return cgsbFlag;
	}




	public void setCgsbFlag(String cgsbFlag) {
		this.cgsbFlag = cgsbFlag;
	}




	public String getYhjb() {
		return yhjb;
	}




	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}




	public boolean isQueryFlag() {
		return queryFlag;
	}



	public void setQueryFlag(boolean queryFlag) {
		this.queryFlag = queryFlag;
	}



	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getYstart() {
		return ystart;
	}

	public void setYstart(String ystart) {
		this.ystart = ystart;
	}

	public String getYend() {
		return yend;
	}

	public void setYend(String yend) {
		this.yend = yend;
	}

	public String getDqjs() {
		return dqjs;
	}

	public void setDqjs(String dqjs) {
		this.dqjs = dqjs;
	}

	

	public List getSwdwlist() {
		return swdwlist;
	}

	public void setSwdwlist(List swdwlist) {
		this.swdwlist = swdwlist;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public List getCxswdwlist() {
		return cxswdwlist;
	}

	public void setCxswdwlist(List cxswdwlist) {
		this.cxswdwlist = cxswdwlist;
	}





	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}
	public String getZbs() {
		return zbs;
	}
	public void setZbs(String zbs) {
		this.zbs = zbs;
	}




	public String getZcgbs() {
		return zcgbs;
	}




	public void setZcgbs(String zcgbs) {
		this.zcgbs = zcgbs;
	}




	public String getZsbbs() {
		return zsbbs;
	}




	public void setZsbbs(String zsbbs) {
		this.zsbbs = zsbbs;
	}




	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}




	public String getFjdmxx() {
		return fjdmxx;
	}




	public void setFjdmxx(String fjdmxx) {
		this.fjdmxx = fjdmxx;
	}




	public String getSwsdmxx() {
		return swsdmxx;
	}




	public void setSwsdmxx(String swsdmxx) {
		this.swsdmxx = swsdmxx;
	}




	public String getYhdmxx() {
		return yhdmxx;
	}




	public void setYhdmxx(String yhdmxx) {
		this.yhdmxx = yhdmxx;
	}




	public String getKksjxx() {
		return kksjxx;
	}




	public void setKksjxx(String kksjxx) {
		this.kksjxx = kksjxx;
	}




	public int getMaxPageXx() {
		  try{
	            maxPageXx = this.getTotalRowCountXx() / this.getPageSizeXx();
	            int number = totalRowCountXx % pageSizeXx;
	            if(number != 0){
	                maxPageXx += 1;
	            }
	        } catch(Exception ex){
	            maxPageXx = 0;
	        }
	        return maxPageXx;
	}




	public void setMaxPageXx(int maxPageXx) {
		this.maxPageXx = maxPageXx;
	}




	public int getPageSizeXx() {
		return pageSizeXx;
	}




	public void setPageSizeXx(int pageSizeXx) {
		this.pageSizeXx = pageSizeXx;
	}




	public int getCurrentPageXx() {
		return currentPageXx;
	}




	public void setCurrentPageXx(int currentPageXx) {
		this.currentPageXx = currentPageXx;
	}




	public int getTotalRowCountXx() {
		return totalRowCountXx;
	}




	public void setTotalRowCountXx(int totalRowCountXx) {
		this.totalRowCountXx = totalRowCountXx;
	}




	public String getCgZje() {
		return cgZje;
	}




	public void setCgZje(String cgZje) {
		this.cgZje = cgZje;
	}




	public String getSbZje() {
		return sbZje;
	}




	public void setSbZje(String sbZje) {
		this.sbZje = sbZje;
	}
	
	 
}
