package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class Jmfl implements ORObject
{
    private String jmfldm;
    private String jmflmc;
    private java.sql.Timestamp cjrq;
    private java.sql.Timestamp lrrq;
    private String cjr;
    public Jmfl()
    {
    }
    public String getJmfldm()
    {
        return jmfldm;
    }
    public void setJmfldm(String jmfldm)
    {
        this.jmfldm = jmfldm;
    }
    public String getJmflmc()
    {
        return jmflmc;
    }
    public void setJmflmc(String jmflmc)
    {
        this.jmflmc = jmflmc;
    }
    public java.sql.Timestamp getCjrq()
    {
        return cjrq;
    }
    public void setCjrq(java.sql.Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }
    public String getCjr()
    {
        return cjr;
    }
    public void setCjr(String cjr)
    {
        this.cjr = cjr;
    }
    public java.sql.Timestamp getLrrq()
    {
        return lrrq;
    }
    public void setLrrq(java.sql.Timestamp lrrq)
    {
        this.lrrq = lrrq;
    }

}