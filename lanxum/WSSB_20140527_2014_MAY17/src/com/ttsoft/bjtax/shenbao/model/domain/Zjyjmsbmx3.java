package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.ekernel.db.or.ORObject;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �پ�ҵ�����걨��ϸ����</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: TTSOFT</p>
 * @author qinwei
 * @version 1.0
 */
public class Zjyjmsbmx3
    implements ORObject {
//	˰����������
    
    private String jsjdm;

    //�������ʹ���
    
    private String bblxdm;
    
    //������
    
    private String bbq;
    
    //�������
    
    private String bbnd;
    

    //ȫ��Ӫҵ����
    
    private String qnyysr;

    //ȫ�����˰��
    
    private String qnjmse;
    
    //¼����
    
    private String lrr;
    
    //¼������
    
    private Timestamp lrrq;
    
    //������
    
    private String cjr;
    
    //��������
    
    private Timestamp cjrq; 
    
    //���ش���
    private String qxdm;
    
    //���
    private String nd;
    
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getBblxdm()
    {
        return bblxdm;
    }
    public String getBbq()
    {
        return bbq;
    }
    public String getBbnd()
    {
        return bbnd;
    }
    public String getQnyysr()
    {
        return qnyysr;
    }
    public String getQnjmse()
    {
        return qnjmse;
    }
  
    public void setBblxdm(String bblxdm)
    {
        this.bblxdm = bblxdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setBbq(String bbq)
    {
        this.bbq = bbq;
    }
    public void setBbnd(String bbnd)
    {
        this.bbnd = bbnd;
    }
    public void setQnyysr(String qnyysr)
    {
        this.qnyysr = qnyysr;
    }
 
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public void setQnjmse(String qnjmse) {
		this.qnjmse = qnjmse;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
  

 }
