package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.ekernel.db.or.ORObject;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 再就业减免申报明细表三</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: TTSOFT</p>
 * @author qinwei
 * @version 1.0
 */
public class Zjyjmsbmx3
    implements ORObject {
//	税务计算机代码
    
    private String jsjdm;

    //报表类型代码
    
    private String bblxdm;
    
    //报表期
    
    private String bbq;
    
    //报表年度
    
    private String bbnd;
    

    //全年营业收入
    
    private String qnyysr;

    //全年减免税额
    
    private String qnjmse;
    
    //录入人
    
    private String lrr;
    
    //录入日期
    
    private Timestamp lrrq;
    
    //创建人
    
    private String cjr;
    
    //创建日期
    
    private Timestamp cjrq; 
    
    //区县代码
    private String qxdm;
    
    //年度
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
