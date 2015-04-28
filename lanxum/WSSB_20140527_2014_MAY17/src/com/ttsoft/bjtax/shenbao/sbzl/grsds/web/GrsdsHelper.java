//Source file: F:\\Genereated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\grsds\\web\\GrsdsHelper.java

package com.ttsoft.bjtax.shenbao.sbzl.grsds.web;

import java.sql.Timestamp;
import com.ttsoft.framework.exception.BaseException;
import java.util.Map;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsz;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import java.util.HashMap;
import java.util.List;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsMapConstant;

/**
 * @author Ding Chenggang
 * @version 1.0
 */
public class GrsdsHelper
{

    public GrsdsHelper()
    {

    }

    /**
     * VOPackage vo = new VOPackage();
     * vo.setAction();
     * vo.setProcessor();
     * vo.setData();  // 构造一个map
     *
     * return ShenbaoProxy.process(vo);
     * @param jsjdm String
     * @param now Timestamp
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Map queryHistoryData(String jsjdm, Timestamp now)
        throws BaseException
    {
        try
        {
            // 构造查询条件，将查询条件的值放到值对象Grsdsz中去
            Map skssrq = Skssrq.monthSkssrq(now);
            Grsdsz z = new Grsdsz();
            z.setJsjdm(jsjdm);
            z.setSkssksrq((Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            z.setSkssjsrq((Timestamp)skssrq.get(Skssrq.SKSSJSRQ));

            VOPackage vo = new VOPackage();
            vo.setAction(GrsdsActionConstant.ACTION_QUERY);
            vo.setProcessor(ProcessorNames.GRSDS_PROCESSOR);
            vo.setData(z);  // 构造一个map

            return (Map)ShenbaoProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * VOPackage vo = new VOPackage();
     * vo.setAction();
     * vo.setProcessor();
     * vo.setData(info);
     *
     * ShenbaoProxy.process(vo);
     * @param info Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public void save(Grsdsz oldZ, List oldMx, Grsdsz newZ, List newMx) throws BaseException
    {
        try
        {
            Map info = new HashMap(4);

            info.put(GrsdsMapConstant.OLDZ, oldZ);
            info.put(GrsdsMapConstant.OLDMX, oldMx);

            info.put(GrsdsMapConstant.NEWZ, newZ);
            info.put(GrsdsMapConstant.NEWMX, newMx);

            VOPackage vo = new VOPackage();
            vo.setAction(GrsdsActionConstant.ACTION_REFRESH);
            vo.setProcessor(ProcessorNames.GRSDS_PROCESSOR);
            vo.setData(info);

            ShenbaoProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public void delete(Grsdsz z, List mx) throws BaseException
    {
        try
        {
            VOPackage vo = new VOPackage();

            Map data = new HashMap(2);
            data.put(GrsdsMapConstant.OLDZ, z);
            data.put(GrsdsMapConstant.OLDMX, mx);
            vo.setAction(GrsdsActionConstant.ACTION_DELETE);
            vo.setProcessor(ProcessorNames.GRSDS_PROCESSOR);
            vo.setData(data);

            ShenbaoProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}