package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 自然人申报前后台交互数据对象</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-17
 */

public class ZrrsbInfor implements Serializable
{
    public ZrrsbInfor()
    {
    }

    //自然人申报主表信息
    private Zrrgrsdsz zrrgrsdsz ;
    //自然人申报明细List(Wjgrsdsmx)
    private List zrrsbmxList;
    //外币折合人民币List
    private List wbzhrmbList;
    //申报缴款信息
    private DeclareInfor declareInfor;

    public List getZrrsbmxList()
    {
        return zrrsbmxList;
    }
    public void setZrrsbmxList(List zrrsbmxList)
    {
        this.zrrsbmxList = zrrsbmxList;
    }
    public DeclareInfor getDeclareInfor()
    {
        return declareInfor;
    }
    public void setDeclareInfor(DeclareInfor declareInfor)
    {
        this.declareInfor = declareInfor;
    }
    public Zrrgrsdsz getZrrgrsdsz()
    {
        return zrrgrsdsz;
    }
    public void setZrrgrsdsz(Zrrgrsdsz zrrgrsdsz)
    {
        this.zrrgrsdsz = zrrgrsdsz;
    }
    public List getWbzhrmbList()
    {
        return wbzhrmbList;
    }
    public void setWbzhrmbList(List wbzhrmbList)
    {
        this.wbzhrmbList = wbzhrmbList;
    }

}