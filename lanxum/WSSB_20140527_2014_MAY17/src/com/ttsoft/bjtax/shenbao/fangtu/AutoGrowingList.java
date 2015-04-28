package com.ttsoft.bjtax.shenbao.fangtu;

import java.util.ArrayList;

public class AutoGrowingList extends ArrayList {
    private Class clazz;

    public AutoGrowingList(Class clazz)
    {
        super();
        this.clazz = clazz;
    }

    public Object get(int index)
    {
        try {
            while (this.size() < (index + 1)) {
                this.add(clazz.newInstance());
            }
        } catch (Exception e) {
        }

        return super.get(index);
    }
}
