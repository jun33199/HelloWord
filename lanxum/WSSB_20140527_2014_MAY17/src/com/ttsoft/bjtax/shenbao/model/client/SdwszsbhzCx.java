package com.ttsoft.bjtax.shenbao.model.client;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:三代撤消数据 </p>
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
    //申报汇总单号
    private String     sbhzdh ;
    //缴款凭证号张数
    private int     jkpzhNum;
    //实缴金额汇总
    private BigDecimal sjjehz;
    //汇总日期
    private Timestamp  hzrq;
    //对应的三代多条汇总数据
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

    //返回缴款凭证号List
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