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

public class Jkpzhkz implements ORObject
{
    private String jsjdm;
    private java.math.BigDecimal xh;
    private String ny;

    public Jkpzhkz()
    {
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public java.math.BigDecimal getXh()
    {
        return xh;
    }
    public String getNy()
    {
        return ny;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setXh(java.math.BigDecimal xh)
    {
        this.xh = xh;
    }
    public void setNy(String ny)
    {
        this.ny = ny;
    }

}