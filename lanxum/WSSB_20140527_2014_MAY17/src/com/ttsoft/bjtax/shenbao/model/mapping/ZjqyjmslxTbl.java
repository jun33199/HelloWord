package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Zjqyjmslx;

/**
 * <p>Title: ������˰��������ϵͳ���������걨--�ܾ���ҵ����˰����</p>
 * <p>Description: </p>
 * @author  lsc-tujb
 * @version 1.1
 */
public class ZjqyjmslxTbl extends ORTable
{
	static final String tabName = "DMDB.SB_DM_ZJQYJMSLX";
    public ZjqyjmslxTbl(ORManager manager)
    {
        super(manager, tabName, Zjqyjmslx.class);
    }
}
