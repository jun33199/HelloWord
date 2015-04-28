/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 印花税代售单位销售汇总 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsxshzProcessor
    implements Processor
{

    /**
     * 默认构造函数
     */
    public YhsxshzProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param parm1 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage parm1)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        throw new java.lang.UnsupportedOperationException(
            "Method process() not yet implemented.");
    }

}