package com.lscdz.qysds.common.service.qysds.check.formula;

import java.util.Map;

/**
 * �Զ��屨��-��������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�Result   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����1:41:21   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����1:41:21   
 * �޸ı�ע��   
 * @version  1.0
 */
public class Result {

    private Object result; // ������.
    @SuppressWarnings("rawtypes")
	private Map varMap; // ����HashMap��.

    public Result() {
    }

    /**
     * ��ȡ������.
     *
     * @return ������.
     */
    public Object getResult() {
        return result;
    }

    /**
     * ���ü�����.
     *
     * @param result ������.
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * ��ȡ����HashMap��.
     *
     * @return ����HashMap��.
     */
    @SuppressWarnings("rawtypes")
	public Map getVarMap() {
        return varMap;
    }

    /**
     * ���ñ���HashMap��.
     *
     * @param varMap ����HashMap��.
     */
    @SuppressWarnings("rawtypes")
	public void setVarMap(Map varMap) {
        this.varMap = varMap;
    }

    /**
     * ��÷��ظö�����ַ�������
     *
     * @return �ö�����ַ�������
     */
    public String toString() {
        return "������=" + result + "�� ��������ı�������ֵ��" + varMap;
    }

}
