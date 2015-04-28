package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb_His;

public class Sbjkzb_HisTbl extends ORTable{
	static final String tabName = "SBDB.SB_JL_SBJKZB_HIS";
	public Sbjkzb_HisTbl(ORManager manager) {
		super(manager, tabName, Sbjkzb_His.class);
	}
}
