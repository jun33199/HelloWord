package com.ttsoft.bjtax.shenbao.util;

import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.naming.NamingException;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.model.domain.Lsgx;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.*;

public class JspUtil
{

    public static String getSklxmc(HttpServletRequest request, String sklxdm)
    {
        Sklx sklx = (Sklx)CodeTableUtil.getCodeTableMap(request, CodeTable.SKLX_MAP).get(sklxdm);
        return sklx.getSklxmc();
    }

    public static void completeZbInfo(HttpServletRequest request, Sbjkzb zb)
    {
        if (zb.getSzmc() != null && zb.getLsgxmc() != null && zb.getDjzclxmc() != null &&
            zb.getSwjgzzjgmc() != null && zb.getZsswjgzzjgmc() != null && zb.getYskmmc() != null &&
            zb.getYsjcmc() != null && zb.getGkzzjgmc() != null && zb.getNsrmc() != null)
        {
            return;
        }

        Map szsmMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
        Map lsgxMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.LSGX_MAP);
        Map djzclxMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.DJZCLX_MAP);

        Map swjgzzjgMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.SWJJZZJG_MAP);
        Map yskmMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.YSKM_MAP);
        Map ysjcMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.YSJC_MAP);

        if (szsmMap.get(zb.getSzdm()) != null)
        {
        zb.setSzmc(((Szsm)szsmMap.get(zb.getSzdm())).getSzsmmc());  // ˰��
        }
        if (lsgxMap.get(zb.getLsgxdm()) != null)
        {
            zb.setLsgxmc(((Lsgx)lsgxMap.get(zb.getLsgxdm())).getLsgxmc()); // ������ϵ
        }
        if (djzclxMap.get(zb.getDjzclxdm()) != null)
        {
            zb.setDjzclxmc(((Djzclx)djzclxMap.get(zb.getDjzclxdm())).getDjzclxmc()); // �Ǽ�ע������
        }
        if (swjgzzjgMap.get(zb.getSwjgzzjgdm()) != null)
        {
            zb.setSwjgzzjgmc(((Swjgzzjg)swjgzzjgMap.get(zb.getSwjgzzjgdm().substring(0,2)+"00")).getSwjgzzjgmc()); // ˰�������֯����
        }
        if (swjgzzjgMap.get(zb.getZsswjgzzjgdm()) != null)
        {
            zb.setZsswjgzzjgmc(((Swjgzzjg)swjgzzjgMap.get(zb.getZsswjgzzjgdm())).getSwjgzzjgmc()); // ����˰�������֯����
        }
        if (yskmMap.get(zb.getYskmdm()) != null)
        {
            zb.setYskmmc( ( (Yskm)yskmMap.get(zb.getYskmdm())).getYskmmc()); // Ԥ���Ŀ
        }
        if (ysjcMap.get(zb.getYsjcdm()) != null)
        {
            zb.setYsjcmc( ( (Ysjc)ysjcMap.get(zb.getYsjcdm())).getYsjcmc()); // Ԥ�㼶��
        }
        if (swjgzzjgMap.get(zb.getSwjgzzjgdm()) != null)
        {
            zb.setGkzzjgmc( ( (Swjgzzjg)swjgzzjgMap.get(zb.getSwjgzzjgdm())).getSkgk()); // �տ����
        }
        String nsrmc = "";
        try
        {
            if (((UserData)request.getSession().getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA)).getYhlb().
                equals(com.ttsoft.common.util.CodeConstants.YHLB_ZRR))
            {
                nsrmc = FriendHelper.getZrrjbsj(request).getNsrmc();
            }
            else
            {
                nsrmc = FriendHelper.getSWDJJBSJ(request).getNsrmc();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        zb.setNsrmc(nsrmc);
    }

    public static void completeMxInfo(HttpServletRequest request, Sbjkmx mx)
    {
        if (mx.getSzsmmc() != null)
        {
            return;
        }
        Map szsmMap = (Map)CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
        mx.setSzsmmc(((Szsm)szsmMap.get(mx.getSzsmdm())).getSzsmmc());
    }
    
    /**
	 * @desc ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
	 * @author gaoyh
	 * @date 20131219
	 */
    public static void completeMxFcblmc(HttpServletRequest request, Sbjkmx mx) {
        
        String yskmFcblmc = "";
		try {
			yskmFcblmc = ServiceProxy.getYskmFcblmc(mx.getYskmdm(), mx.getSzsmdm(), mx.getSwjgzzjgdm());
		} catch (BaseException e) {
			e.printStackTrace();
		}

        mx.setYsjcmc(yskmFcblmc);
    }

    public static String format(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        else if (obj instanceof java.sql.Timestamp)
        {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(obj);
        }
        else if (obj instanceof String)
        {
            return (String)obj;
        }
        else if (obj instanceof java.math.BigDecimal)
        {
            java.math.BigDecimal decimal = (java.math.BigDecimal)obj;
            if (decimal.scale() != 2)
            {
                decimal = decimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
            }
            return decimal.toString();
        }
        else
        {
            return obj.toString();
        }
    }

    private static String static_contextpath = null;

    public static void init(boolean reload) throws IOException, FileNotFoundException, NamingException
    {
        if (reload || static_contextpath == null)
        {
            Properties prop = new Properties();
            prop.load(JspUtil.class.getClassLoader().getResourceAsStream("InitialConfig.properties"));
            static_contextpath = prop.getProperty("static_contextpath");
        }
    }
}