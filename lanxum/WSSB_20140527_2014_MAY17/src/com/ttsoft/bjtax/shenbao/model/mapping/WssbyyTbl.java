package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Wssbyy;

/**
 * <p>Title: ������˰��������ϵͳ���������걨--��˰�걨ԭ��</p>
 * <p>Description: </p>
 * @author  lsc-tujb
 * @version 1.1
 */
public class WssbyyTbl extends ORTable
{
	static final String tabName = "DMDB.SB_DM_WSSBYY";
    public WssbyyTbl(ORManager manager)
    {
        super(manager, tabName, Wssbyy.class);
    }

}
