/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.web;

import java.util.*;

import com.ttsoft.bjtax.smsb.dzwsz.processor.*;
import com.ttsoft.framework.form.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Form��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class DzwszForm extends BaseForm {
    public DzwszForm() {
    }

    /**
     * ������ˮ��
     */
    private String jylsh;
    /**
     * ���������
     */
    private String jsjdm;
    /**
     * ˰Ʊ����
     */
    private String sphm;
    /**
     * ��ǰҳ��
     */
    private String currentPage;

    /**
     * ǰ̨չʾbo
     */
    private DzwszBO bo;
    /**
     * ��ӡbo
     */
    private List boList;
    /**
     *  �Ƿ��ӡ ��ֵΪ�Ѵ�ӡ
     */
    private String dy;
    
   /* 201306 kanght*/
    
    /**
     *��˰֤��ѯ 
     */
    private String fkpzjsjdm;
    /**
     *��˰֤��ѯ 
     */
    private String fkpzsphm;
    /**
     *��˰֤��ѯ 
     */
    private String czjsjdm;
    /**
     *�����۲�ѯ����--��ѯ��ֹ����
     */
    private String czcxqrq;
    /**
     *��˰֤��ѯ 
     */
    private String czcxzrq;
    
    /**
     *��˰֤�б� 
     */
    private List wszList;
    /**
     *��ӡ��˰֤--���������
     */
    private String dyjsjdm;
    /**
     *��ӡ��˰֤--������ˮ
     */
    private String dyjylsh;
    /**
     *��ӡ��˰֤--˰Ʊ����
     */
    private String dysphm;
    /**
     *��ѯ��˰֤--������ʶ
     */
    private String flag;
    
    
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFkpzjsjdm() {
		return fkpzjsjdm;
	}

	public void setFkpzjsjdm(String fkpzjsjdm) {
		this.fkpzjsjdm = fkpzjsjdm;
	}

	public String getFkpzsphm() {
		return fkpzsphm;
	}

	public void setFkpzsphm(String fkpzsphm) {
		this.fkpzsphm = fkpzsphm;
	}

	public String getCzjsjdm() {
		return czjsjdm;
	}

	public void setCzjsjdm(String czjsjdm) {
		this.czjsjdm = czjsjdm;
	}

	public String getCzcxqrq() {
		return czcxqrq;
	}

	public void setCzcxqrq(String czcxqrq) {
		this.czcxqrq = czcxqrq;
	}

	public String getCzcxzrq() {
		return czcxzrq;
	}

	public void setCzcxzrq(String czcxzrq) {
		this.czcxzrq = czcxzrq;
	}

	public String getDyjsjdm() {
		return dyjsjdm;
	}

	public void setDyjsjdm(String dyjsjdm) {
		this.dyjsjdm = dyjsjdm;
	}

	public String getDyjylsh() {
		return dyjylsh;
	}

	public void setDyjylsh(String dyjylsh) {
		this.dyjylsh = dyjylsh;
	}

	public String getDysphm() {
		return dysphm;
	}

	public void setDysphm(String dysphm) {
		this.dysphm = dysphm;
	}

	public List getWszList() {
		return wszList;
	}

	public void setWszList(List wszList) {
		this.wszList = wszList;
	}

	public String getJylsh() {
        return jylsh;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getSphm() {
        return sphm;
    }

    public List getBoList() {
        return boList;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public DzwszBO getBo() {
        return bo;
    }

    public String getDy() {
        return dy;
    }

    public void setJylsh(String jylsh) {
        this.jylsh = jylsh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setSphm(String sphm) {
        this.sphm = sphm;
    }

    public void setBoList(List boList) {
        this.boList = boList;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setBo(DzwszBO bo) {
        this.bo = bo;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }
}
