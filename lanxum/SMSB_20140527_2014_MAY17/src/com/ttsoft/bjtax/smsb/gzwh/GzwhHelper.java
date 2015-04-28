/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gzwh;

import java.util.*;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.exception.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 告知事项的公共类</p>
 * @author 开发六组－－石岩峰
 * @version 1.1
 */
public class GzwhHelper
{

    /**
     * 根据所属单位代码 取得地区局所信息
     * @param userSsdwdm 用户所属单位代码
     * @return 地区局所列表
     * @throws BaseException
     */
    public static List getDqjsSelect (String userSsdwdm)
        throws BaseException
    {
        try
        {
            List dqjsList = new ArrayList();
            List codeList = CodeManager.getCodeList("GZWH_SWJGZZJG",
                CodeConstants.CODE_MAP_BEANLIST).
                            getRecords();
            if (userSsdwdm.startsWith("90"))
            {
                //取得市局能处理的地区局所
                dqjsList = getCityBureau(userSsdwdm, codeList, dqjsList);
            }
            else if (userSsdwdm.endsWith("00"))
            {
                //取得分局能处理的地区局所
                dqjsList = getBureau(userSsdwdm, codeList, dqjsList);
            }
            else
            {
                //取得指定所能处理的地区局所
                getDepartment(userSsdwdm, codeList, dqjsList);
            }
            return dqjsList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取得市局能处理的地区局所
     * @param userSsdwdm 用户所属单位代码
     * @param codeList 代码list
     * @param dqjsList 地区局所信息list
     * @return dqjsList
     */
    private static List getCityBureau (String userSsdwdm, List codeList,
                                       List dqjsList)
    {
        LabelValueBean firstBean = new LabelValueBean("* 所有地区局所", "0");
        dqjsList.add(firstBean);
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            LabelValueBean tempDqjs = new LabelValueBean("", "");
            tempDqjs.setLabel(dqjs.getLabel());
            tempDqjs.setValue(dqjs.getValue());
            if (dqjs.getValue().endsWith("00"))
            {
                dqjsList.add(tempDqjs);
            }
            else
            {
                tempDqjs.setLabel("　　" + dqjs.getLabel());
                dqjsList.add(tempDqjs);
            }
        }
        return dqjsList;
    }

    /**
     * 取得分局能处理的地区局所
     * @param userSsdwdm 用户所属单位代码
     * @param codeList 代码list
     * @param dqjsList 地区局所信息list
     * @return dqjsList
     */
    private static List getBureau (String userSsdwdm, List codeList,
                                   List dqjsList)
    {
        String subSsdwdm = userSsdwdm.substring(0, 2);
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            if (dqjs.getValue().startsWith(subSsdwdm))
            {
                LabelValueBean tempDqjs = new LabelValueBean("", "");
                tempDqjs.setLabel(dqjs.getLabel());
                tempDqjs.setValue(dqjs.getValue());
                if (dqjs.getValue().endsWith("00"))
                {
                    dqjsList.add(tempDqjs);
                }
                else
                {
                    tempDqjs.setLabel("　　" + dqjs.getLabel());
                    dqjsList.add(tempDqjs);
                }
            }
        }
        return dqjsList;
    }

    /**
     * 取得指定所能处理的地区局所
     * @param userSsdwdm 所属单位代码
     * @param codeList 代码list
     * @param dqjsList 地区局所信息list
     * @return dqjsList 地区局所信息list
     */
    private static List getDepartment (String userSsdwdm, List codeList,
                                       List dqjsList)
    {
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            if (dqjs.getValue().equals(userSsdwdm))
            {
                LabelValueBean tempDqjs = new LabelValueBean("", "");
                tempDqjs.setLabel(dqjs.getLabel());
                tempDqjs.setValue(dqjs.getValue());
                dqjsList.add(tempDqjs);
            }
        }
        return dqjsList;
    }

}