package com.ttsoft.bjtax.smsb.wrkcx.processor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.Serializable;
import java.util.List;
import com.ttsoft.bjtax.dj.model.SWDL;
import com.ttsoft.bjtax.dj.model.ZJG;
import com.ttsoft.bjtax.dj.model.JBSJ;

/**
 * <p>Title: 北京地税核心征管系统－－税务登记</p>
 * <p>Description: 税务登记详细信息数据对象</p>
 * @author 开发四组－－张瑞洲
 * @version 1.1
 */
public class DjxxxxBO
    implements Serializable
{
    /**
     * 默认构造函数
     */
    public DjxxxxBO()
    {
    }

    //税务登记
    /**
     * 税务登记—税务代理信息
     */
    SWDL swdl;

    /**
     * 税务登记—总机构
     */

    ZJG zjg;

    /**
     * 税务登记—基本数据
     */

    JBSJ jbsj;

    /**
     * 税务登记—分支机构
     */

    List fzjgList;

    /**
     * 税务登记—银行信息
     */

    List yhzhList;

    /**
     * 税务登记—变更信息
     */

    List bgxxList;

    /**税务登记—停业登记*/

    List tydjList;

    /**
     * 税务登记—吊销登记
     */

    List dxdjList;

    /**
     * 税务登记—注销登记
     */

    List zxdjList;

    /**
     * 税务登记—停业登记——历史
     */

    List tydjList_ls;

    /**
     * 税务登记—吊销登记——历史
     */

    List dxdjList_ls;

    /**
     * 税务登记—注销登记——历史
     */

    List zxdjList_ls;

    /**
     * 税务登记—税务登记——联系人
     */

    List lxrList;

    /**
     * 税务登记—投资方登记信息
     */

    List tzfList;

    public List getBgxxList()
    {
        return bgxxList;
    }

    public List getDxdjList()
    {
        return dxdjList;
    }

    public List getDxdjList_ls()
    {
        return dxdjList_ls;
    }

    public List getFzjgList()
    {
        return fzjgList;
    }

    public JBSJ getJbsj()
    {
        return jbsj;
    }

    public List getTydjList()
    {
        return tydjList;
    }

    public List getTydjList_ls()
    {
        return tydjList_ls;
    }

    public List getYhzhList()
    {
        return yhzhList;
    }

    public ZJG getZjg()
    {
        return zjg;
    }

    public List getZxdjList()
    {
        return zxdjList;
    }

    public List getZxdjList_ls()
    {
        return zxdjList_ls;
    }

    public void setBgxxList(List bgxxList)
    {
        this.bgxxList = bgxxList;
    }

    public void setDxdjList(List dxdjList)
    {
        this.dxdjList = dxdjList;
    }

    public void setDxdjList_ls(List dxdjList_ls)
    {
        this.dxdjList_ls = dxdjList_ls;
    }

    public void setFzjgList(List fzjgList)
    {
        this.fzjgList = fzjgList;
    }

    public void setJbsj(JBSJ jbsj)
    {
        this.jbsj = jbsj;
    }

    public void setTydjList(List tydjList)
    {
        this.tydjList = tydjList;
    }

    public void setTydjList_ls(List tydjList_ls)
    {
        this.tydjList_ls = tydjList_ls;
    }

    public void setYhzhList(List yhzhList)
    {
        this.yhzhList = yhzhList;
    }

    public void setZjg(ZJG zjg)
    {
        this.zjg = zjg;
    }

    public void setZxdjList(List zxdjList)
    {
        this.zxdjList = zxdjList;
    }

    public void setZxdjList_ls(List zxdjList_ls)
    {
        this.zxdjList_ls = zxdjList_ls;
    }

    public List getLxrList()
    {
        return lxrList;
    }

    public SWDL getSwdl()
    {
        return swdl;
    }

    public List getTzfList()
    {
        return tzfList;
    }

    public void setTzfList(List tzfList)
    {
        this.tzfList = tzfList;
    }

    public void setSwdl(SWDL swdl)
    {
        this.swdl = swdl;
    }

    public void setLxrList(List lxrList)
    {
        this.lxrList = lxrList;
    }

}


