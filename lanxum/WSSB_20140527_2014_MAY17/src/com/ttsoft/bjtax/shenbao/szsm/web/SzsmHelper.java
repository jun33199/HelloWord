package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.List;
import java.util.Map;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.shenbao.szsm.SzsmActionConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.dj.model.*;

public class SzsmHelper
{
    public SzsmHelper()
    {
    }

    public SzsmHelper(UserData userData)
    {
        this.userData = userData;
    }

    protected UserData userData;

    public void refreshFavorite(Map data) throws BaseException
    {
        VOPackage vo = new VOPackage();

        vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
        vo.setAction(SzsmActionConstant.REFRESH_FAVORITE);

        vo.setData(data);
        vo.setUserData(userData);

        ShenbaoProxy.getInstance().process(vo);
    }

    public Map getSzsmTreeInfo(SWDJJBSJ djsj) throws BaseException
    {
        VOPackage vo = new VOPackage();
        vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
        vo.setAction(SzsmActionConstant.QUERY_SZSMTREE_INFO);

        vo.setData(djsj);
        vo.setUserData(this.userData);
        return (Map)ShenbaoProxy.getInstance().process(vo);
    }


    public List getJkInfor(SWDJJBSJ djsj) throws BaseException
    {
        VOPackage vo = new VOPackage();
        vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
        vo.setAction(3);
        vo.setUserData(userData);
        vo.setData(djsj);

        return (List)ShenbaoProxy.getInstance().process(vo);
    }
    
    /**
     *  获得纳税人是否 有 委托代征、代扣、代售、监督代售单位认定情况
     * @param djsj
     * @return
     * @throws BaseException
     * @author lsc-tujb
     */
    public List getWtdwInfor(SWDJJBSJ djsj) throws BaseException
    {
        VOPackage vo = new VOPackage();
        vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
        vo.setAction(SzsmActionConstant.QUERY_WTDW_INFO);
        vo.setUserData(userData);
        vo.setData(djsj);

        return (List)ShenbaoProxy.getInstance().process(vo);
    }

    public void setUserData(UserData userData)
    {
        this.userData = userData;
    }
    public UserData getUserData()
    {
        return userData;
    }
}