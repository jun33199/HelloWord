package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.util.List;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��Ȼ���걨ǰ��̨�������ݶ���</p>
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

    //��Ȼ���걨������Ϣ
    private Zrrgrsdsz zrrgrsdsz ;
    //��Ȼ���걨��ϸList(Wjgrsdsmx)
    private List zrrsbmxList;
    //����ۺ������List
    private List wbzhrmbList;
    //�걨�ɿ���Ϣ
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