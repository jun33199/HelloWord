package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

import com.ttsoft.common.model.UserData;

/**
 * 业务处理参数。
 */
public class ProcessorParameter implements Serializable {

    /**
     * 动作类型。
     */
    public int operationType;

    /**
     * 传人要处理的数据。
     */
    public Object data;

    /**
     * 前台传人的用户信息。
     */
    public UserData userData;

    /**
     * Access method for the userData property.
     *
     * @return   the current value of the userData property
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     * Sets the value of the userData property.
     *
     * @param aUserData the new value of the userData property
     */
    public void setUserData(UserData aUserData) {
        userData = aUserData;
    }

    public Object getData() {
        return data;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
}
