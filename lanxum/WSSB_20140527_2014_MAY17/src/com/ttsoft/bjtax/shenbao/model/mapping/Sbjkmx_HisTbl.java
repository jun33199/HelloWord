package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx_His;

public class Sbjkmx_HisTbl extends ORTable{
	static final String tabName = "SBDB.SB_JL_SBJKMX_HIS";
	public Sbjkmx_HisTbl(ORManager manager) {
		super(manager, tabName, Sbjkmx_His.class);
	}
}
