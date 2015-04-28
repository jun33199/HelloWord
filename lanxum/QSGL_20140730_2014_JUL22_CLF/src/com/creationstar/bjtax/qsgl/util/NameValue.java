package com.creationstar.bjtax.qsgl.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NameValue implements Serializable {

    private String father = ""; // 父节点

    private String name = ""; // 名称

    private String value = ""; // 名称对应的值

    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public NameValue(String name, String value, String father) {
        this.name = name;
        this.value = value;
        this.father = father;
    }

    private void readObject(ObjectInputStream ois) throws
            ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    /**
     * 获得名称属性。
     *
     * @return 名称属性
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称属性。
     *
     * @param name
     *            将要设置的名称属性值。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得值属性。
     *
     * @return 值属性。
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值属性。
     *
     * @param value
     *            值属性。
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * 返回描述该对象的字符串。
     *
     * @return String 描述该对象的字符串。
     */
    public String toString() {
        return "<name=" + name + ", value=" + value + ",father=" + father + ">";
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
