package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

import com.ttsoft.common.model.UserData;

/**
 * ҵ���������
 */
public class ProcessorParameter implements Serializable {

    /**
     * �������͡�
     */
    public int operationType;

    /**
     * ����Ҫ��������ݡ�
     */
    public Object data;

    /**
     * ǰ̨���˵��û���Ϣ��
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
