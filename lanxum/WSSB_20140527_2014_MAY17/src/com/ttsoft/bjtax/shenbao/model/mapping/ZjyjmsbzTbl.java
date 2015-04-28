package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
public class ZjyjmsbzTbl extends ORTable {
	static final String tabName = "SBDB.SB_JL_ZJYJMSBZ";
	public ZjyjmsbzTbl(ORManager manager) {
		super(manager, tabName, Zjyjmsbz.class);

	}

}