package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkclrz;

public class SbjkclrzTbl extends ORTable{
	
	static final String tabName = "SBDB.SB_JL_SBJKCLRZ";
	public SbjkclrzTbl(ORManager manager) {
		super(manager, tabName, Sbjkclrz.class);
	}
}
