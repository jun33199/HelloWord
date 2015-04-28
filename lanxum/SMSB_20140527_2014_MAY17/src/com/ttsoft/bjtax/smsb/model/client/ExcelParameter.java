package com.ttsoft.bjtax.smsb.model.client;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ttsoft.bjtax.smsb.util.gzsxexcel.DateTimeUtil;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.StringUtil;

//import com.ttsoft.bjtax.smsb.util.DateTimeUtil;
//import com.ttsoft.bjtax.smsb.util.StringUtil;

import java.sql.Date;
///import com.syax.frame.util.DateTimeUtil;
//import com.syax.frame.exception.*;
//import com.syax.frame.util.StringUtil;

/**
 * <p>Title: 打印Excel文件参数封装</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ExcelParameter implements java.io.Serializable {

    private HttpServletResponse response = null;
    private String currDate = null;
    private String encodeFileName = null;
    private String[] TITLE = null;
    private String[] COLUMS = null;
    private List dataList = null;

    public ExcelParameter() {
    }

    public String[] getCOLUMS() {
        return COLUMS;
    }

    private String getCurrDate() {
        try {
            currDate = DateTimeUtil.timestampToString(new Date(System.
                    currentTimeMillis()),DateTimeUtil.JAVA_DATEFORMAT);
        }
        catch (Exception ex) {
            currDate ="";
            ex.printStackTrace();
        }
        return currDate;
    }

    public List getDataList() {
        return dataList;
    }

    public String getEncodeFileName() {
        encodeFileName = encodeFileName.concat(getCurrDate()).concat(".xls");
        encodeFileName = StringUtil.GBK2ISO( encodeFileName);
        return "attachment; filename=".concat(encodeFileName);
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String[] getTITLE() {
        return TITLE;
    }

    public void setCOLUMS(String[] COLUMS) {
        this.COLUMS = COLUMS;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void setEncodeFileName(String encodeFileName) {
        this.encodeFileName = encodeFileName;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setTITLE(String[] TITLE) {
        this.TITLE = TITLE;
    }

}
