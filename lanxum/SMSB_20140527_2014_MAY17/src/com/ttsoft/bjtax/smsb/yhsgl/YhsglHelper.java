/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 录入印花税购买情况辅助类</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsglHelper
{

    /**
     * 根据所属单位代码 取得销售人员列表
     * @param userSsdwdm 所属单位代码
     * @return 销售人员列表
     * @throws BaseException
     */
    public static List getPeopleSelect (String userSsdwdm)
        throws BaseException
    {
        try
        {
            //调票证接口
            SfHashList yhspList = new SfHashList(com.ttsoft.bjtax.pzgl.proxy.
                                                 ServiceProxy.
                                                 getYhsZhBySwjg(userSsdwdm));
            ArrayList dataList = new ArrayList();
            for (int i = 0; i < yhspList.size(); i++)
            {
                LabelValueBean bean = new LabelValueBean(yhspList.get(i, "ZHMC"),
                    yhspList.get(i, "ZHDM"));
                dataList.add(bean);
            }
            Debug.out(yhspList);
            return dataList;
        }
        catch (Exception e1)
        {
            Debug.out("--------Action-Show----------" + e1.getMessage());
            throw ExceptionUtil.getBaseException(e1);
        }
    }
}
