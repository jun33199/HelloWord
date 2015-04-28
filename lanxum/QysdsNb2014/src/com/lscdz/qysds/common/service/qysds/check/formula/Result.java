package com.lscdz.qysds.common.service.qysds.check.formula;

import java.util.Map;

/**
 * 自定义报表-计算结果类
 * 项目名称：QysdsNb2014   
 * 类名称：Result   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:41:21   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:41:21   
 * 修改备注：   
 * @version  1.0
 */
public class Result {

    private Object result; // 计算结果.
    @SuppressWarnings("rawtypes")
	private Map varMap; // 变量HashMap。.

    public Result() {
    }

    /**
     * 获取计算结果.
     *
     * @return 计算结果.
     */
    public Object getResult() {
        return result;
    }

    /**
     * 设置计算结果.
     *
     * @param result 计算结果.
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 获取变量HashMap。.
     *
     * @return 变量HashMap。.
     */
    @SuppressWarnings("rawtypes")
	public Map getVarMap() {
        return varMap;
    }

    /**
     * 设置变量HashMap。.
     *
     * @param varMap 变量HashMap。.
     */
    @SuppressWarnings("rawtypes")
	public void setVarMap(Map varMap) {
        this.varMap = varMap;
    }

    /**
     * 获得返回该对象的字符描述。
     *
     * @return 该对象的字符描述。
     */
    public String toString() {
        return "计算结果=" + result + "； 参与运算的变量及其值：" + varMap;
    }

}
