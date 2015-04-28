package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web;

import java.sql.*;
import java.util.*;

import org.apache.struts.upload.*;
import com.ttsoft.framework.form.*;
import java.math.BigDecimal;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:代征代扣代缴Form </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-7
 */

public class DsdzdkForm extends SbzlBaseForm
{
    public DsdzdkForm()
    {
    }

    //操作jsp标识
    public final static String OPTJSP_DR = "DR";
    public final static String OPTJSP_HZ = "HZ";
    public final static String OPTJSP_CX = "CX";

    public final static int NOERR     = 0;
    public final static int HASERR    = 1;

    //每页显示的明细条目
    public final static int MAXNUMBEREACHPAGE = 10;
    //分页后的各页list的list
    private List splitPageList = new ArrayList();
    //当前显示的页面明细List
    private List dsdzdkmxItemList = new ArrayList();
    //导入页面当前页索引 默认为0
    private int pageindex = 0;
    //导入页面最大的页索引 默认为0
    private int maxpageindex = 0;
    //汇总页面当前页索引 默认为0 ,这里就将就这样写了
    private int hzpageindex = 0;
    //汇总页面最大的页索引 默认为0 ,这里就将就这样写了,应该不会有很多的页面吧
    private int hzmaxpageindex = 0;
    //上传的文件
    private FormFile theFile;
    //被代征人名称
    private String bdzrmc;
    //备注
    private String bz;
    //汇总单号
    private String hzdh;
    //缴款凭证号
    private String[] jkpzh = null;
    //税款所属开始日期
    private Timestamp skssksrq;
    //税款所属开始日期
    private Timestamp skssjsrq;
    //计算机代码
    private String jsjdm;
    //联系电话 从登记得到 保存时需要填写到明细表中
    private String lxdh;
    //录入人名称
    private String lrr;
    //税务机构组织机构代码 从userdata得到保存时需要填写到明细表中
    private String swjgzzjgdm;
    //单位名称 从登记得到 保存时需要填写到明细表中
    private String dwmc;
    //分页操作的类型
    private String viewPageType;
    //当前页的汇总信息List
    private List hzInforList = new ArrayList();
    //分页后的所有汇总信息List
    private List splitHzInforList = new ArrayList();
    //当前对应的业务页面标识
    private String optJspTag = OPTJSP_DR;

    //要撤消的的汇总单list
    private List erasehzdList =  new ArrayList();
    //汇总单中的缴款书list
    private List erasejkpzhList = new ArrayList();
    //当前在撤消页面选中的汇总单号的索引
    private int curEraseIndex = 0;
    //上传的数据是否有错
    private int errTag = NOERR;
    //操作结果页面的信息
    private String resultMessage = "撤消代售代征代扣汇总数据成功";
    //合计实缴金额
    private BigDecimal sjjehj ;
    //合计计税金额
    private BigDecimal jsjehj;
    //上一次申报编号
    private String preSbbh;

    public List convertdsdzdkmx()
    {
        List dsdzdkmxList = new ArrayList();

        return dsdzdkmxList;
    }

    public String getHzdh()
    {
        return hzdh;
    }
    public String[] getJkpzh()
    {
        return jkpzh;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getLxdh()
    {
        return lxdh;
    }
    public FormFile getTheFile()
    {
        return theFile;
    }
    public void setTheFile(FormFile theFile)
    {
        this.theFile = theFile;
    }
    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setJkpzh(String[] jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setHzdh(String hzdh)
    {
        this.hzdh = hzdh;
    }
    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }
    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public void setDwmc(String dwmc)
    {
        this.dwmc = dwmc;
    }
    public String getDwmc()
    {
        return dwmc;
    }
    public String getLrr()
    {
        return lrr;
    }
    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }
    public List getDsdzdkmxItemList()
    {
        return dsdzdkmxItemList;
    }
    public void setDsdzdkmxItemList(List dsdzdkmxItemList)
    {
        this.dsdzdkmxItemList = dsdzdkmxItemList;
    }
    public int getMaxpageindex()
    {
        return maxpageindex;
    }
    public void setMaxpageindex(int maxpageindex)
    {
        this.maxpageindex = maxpageindex;
    }
    public void setPageindex(int pageindex)
    {
        this.pageindex = pageindex;
    }
    public int getPageindex()
    {
        return pageindex;
    }
    public List getSplitPageList()
    {
        return splitPageList;
    }
    public void setSplitPageList(List splitPageList)
    {
        this.splitPageList = splitPageList;
    }
    public void setViewPageType(String viewPageType)
    {
        this.viewPageType = viewPageType;
    }
    public String getViewPageType()
    {
        return viewPageType;
    }
    public List getHzInforList()
    {
        return hzInforList;
    }
    public void setHzInforList(List hzInforList)
    {
        this.hzInforList = hzInforList;
    }
    public String getOptJspTag()
    {
        return optJspTag;
    }
    public void setOptJspTag(String optJspTag)
    {
        this.optJspTag = optJspTag;
    }
    public int getHzmaxpageindex()
    {
        return hzmaxpageindex;
    }
    public int getHzpageindex()
    {
        return hzpageindex;
    }
    public void setHzmaxpageindex(int hzmaxpageindex)
    {
        this.hzmaxpageindex = hzmaxpageindex;
    }
    public void setHzpageindex(int hzpageindex)
    {
        this.hzpageindex = hzpageindex;
    }
    public List getSplitHzInforList()
    {
        return splitHzInforList;
    }
    public void setSplitHzInforList(List splitHzInforList)
    {
        this.splitHzInforList = splitHzInforList;
    }
  public List getErasehzdList() {
    return erasehzdList;
  }
  public List getErasejkpzhList() {
    return erasejkpzhList;
  }
  public void setErasehzdList(List erasehzdList) {
    this.erasehzdList = erasehzdList;
  }
  public void setErasejkpzhList(List erasejkpzhList) {
    this.erasejkpzhList = erasejkpzhList;
  }
    public int getCurEraseIndex()
    {
        return curEraseIndex;
    }
    public void setCurEraseIndex(int curEraseIndex)
    {
        this.curEraseIndex = curEraseIndex;
    }
    public int getErrTag()
    {
        return errTag;
    }
    public void setErrTag(int errTag)
    {
        this.errTag = errTag;
    }
    public String getResultMessage()
    {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }
    public BigDecimal getJsjehj()
    {
        return jsjehj;
    }
    public void setJsjehj(BigDecimal jsjehj)
    {
        this.jsjehj = jsjehj;
    }
    public void setSjjehj(BigDecimal sjjehj)
    {
        this.sjjehj = sjjehj;
    }
    public BigDecimal getSjjehj()
    {
        return sjjehj;
    }
    public String getBdzrmc()
    {
        return bdzrmc;
    }
    public String getBz()
    {
        return bz;
    }
    public void setBdzrmc(String bdzrmc)
    {
        this.bdzrmc = bdzrmc;
    }
    public void setBz(String bz)
    {
        this.bz = bz;
    }
    public String getPreSbbh()
    {
        return preSbbh;
    }
    public void setPreSbbh(String preSbbh)
    {
        this.preSbbh = preSbbh;
    }
}