package com.creationstar.bjtax.qsgl.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NameValue implements Serializable {

    private String father = ""; // ���ڵ�

    private String name = ""; // ����

    private String value = ""; // ���ƶ�Ӧ��ֵ

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
     * ����������ԡ�
     *
     * @return ��������
     */
    public String getName() {
        return name;
    }

    /**
     * �����������ԡ�
     *
     * @param name
     *            ��Ҫ���õ���������ֵ��
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���ֵ���ԡ�
     *
     * @return ֵ���ԡ�
     */
    public String getValue() {
        return value;
    }

    /**
     * ����ֵ���ԡ�
     *
     * @param value
     *            ֵ���ԡ�
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * ���������ö�����ַ�����
     *
     * @return String �����ö�����ַ�����
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
