package com.ttsoft.bjtax.shenbao.model.mapping;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 ��һ���Źɷ����޹�˾����Ȩ����.</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
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