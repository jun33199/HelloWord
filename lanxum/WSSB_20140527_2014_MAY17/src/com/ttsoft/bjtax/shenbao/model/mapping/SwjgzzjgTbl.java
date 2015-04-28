// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2003-9-5 19:55:52
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   SWJGZZJGTBL.java

package com.ttsoft.bjtax.shenbao.model.mapping;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORTable;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;

public class SwjgzzjgTbl extends ORTable
{

    public SwjgzzjgTbl(ORManager ormanager)
    {
        super(ormanager, "DMDB.GY_DM_SWJGZZJG", Swjgzzjg.class);
    }

    static final String tabName = "DMDB.GY_DM_SWJGZZJG";
}