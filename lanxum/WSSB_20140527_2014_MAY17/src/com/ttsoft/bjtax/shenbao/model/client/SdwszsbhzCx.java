package com.ttsoft.bjtax.shenbao.model.client;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description:������������ </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-09-13
 */

public class SdwszsbhzCx implements Serializable
{
    public SdwszsbhzCx()
    {
    }
    //�걨���ܵ���
    private String     sbhzdh ;
    //�ɿ�ƾ֤������
    private int     jkpzhNum;
    //ʵ�ɽ�����
    private BigDecimal sjjehz;
    //��������
    private Timestamp  hzrq;
    //��Ӧ������������������
    private List sdwszsbhzList;

    public Timestamp getHzrq()
    {
        return hzrq;
    }
    public int getJkpzhNum()
    {
        return jkpzhNum;
    }
    public String getSbhzdh()
    {
        return sbhzdh;
    }
    public List getSdwszsbhzList()
    {
        return sdwszsbhzList;
    }
    public BigDecimal getSjjehz()
    {
        return sjjehz;
    }
    public void setSjjehz(BigDecimal sjjehz)
    {
        this.sjjehz = sjjehz;
    }
    public void setSdwszsbhzList(List sdwszsbhzList)
    {
        this.sdwszsbhzList = sdwszsbhzList;
    }
    public void setSbhzdh(String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }
    public void setJkpzhNum(int jkpzhNum)
    {
        this.jkpzhNum = jkpzhNum;
    }
    public void setHzrq(Timestamp hzrq)
    {
        this.hzrq = hzrq;
    }

    //���ؽɿ�ƾ֤��List
    public List getjkpzhList()
    {
        List jkpzhList = new ArrayList();
        if(sdwszsbhzList != null)
        {
            for(int i = 0; i < sdwszsbhzList.size(); i++)
            {
                jkpzhList.add(((Sdwszsbhz)sdwszsbhzList.get(i)).getJkpzh());
            }
        }
        return jkpzhList;
    }

}