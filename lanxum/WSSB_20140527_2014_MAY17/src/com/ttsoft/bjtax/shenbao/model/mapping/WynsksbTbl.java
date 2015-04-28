package com.ttsoft.bjtax.shenbao.model.mapping;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ekernel.db.or.*;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class WynsksbTbl extends ORTable
{
    static final String tabName = "SBDB.SB_JL_WYNSKSB";
    public WynsksbTbl(ORManager manager)
    {
        super(manager, tabName, Wynsksb.class);
    }

}