/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 投资方信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrTzfxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 序号
     */
    private java.lang.String _xh;

    /**
     * 投资方纳税人识别号
     */
    private java.lang.String _tzfNsrsbh;

    /**
     * 投资方名称
     */
    private java.lang.String _tzfmc;

    /**
     * 国家代码
     */
    private java.lang.String _gjDm;

    /**
     * 投资金额
     */
    private java.lang.String _tzje = "0.00";

    /**
     * 币种代码
     */
    private java.lang.String _bzDm;

    /**
     * 投资比例
     */
    private java.lang.String _tzbl = "0.00";

    /**
     * 分配比例
     */
    private java.lang.String _fpbl = "0.00";

    /**
     * 协议出资日期
     */
    private java.lang.String _xyczrq;

    /**
     * 汇率比价
     */
    private java.lang.String _hlbj = "0.00";

    /**
     * 投资方性质
     */
    private java.lang.String _tzfxz;

    /**
     * 证件种类
     */
    private java.lang.String _tzfZjzlDm;

    /**
     * 证件号码
     */
    private java.lang.String _tzfZjhm;

    /**
     * 投资方地址
     */
    private java.lang.String _tzfDz;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrTzfxx() {
        super();
        setTzje("0.00");
        setTzbl("0.00");
        setFpbl("0.00");
        setHlbj("0.00");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bzDm'. The field 'bzDm' has the
     * following description: 币种代码
     * 
     * @return the value of field 'BzDm'.
     */
    public java.lang.String getBzDm(
    ) {
        return this._bzDm;
    }

    /**
     * Returns the value of field 'fpbl'. The field 'fpbl' has the
     * following description: 分配比例
     * 
     * @return the value of field 'Fpbl'.
     */
    public java.lang.String getFpbl(
    ) {
        return this._fpbl;
    }

    /**
     * Returns the value of field 'gjDm'. The field 'gjDm' has the
     * following description: 国家代码
     * 
     * @return the value of field 'GjDm'.
     */
    public java.lang.String getGjDm(
    ) {
        return this._gjDm;
    }

    /**
     * Returns the value of field 'hlbj'. The field 'hlbj' has the
     * following description: 汇率比价
     * 
     * @return the value of field 'Hlbj'.
     */
    public java.lang.String getHlbj(
    ) {
        return this._hlbj;
    }

    /**
     * Returns the value of field 'tzbl'. The field 'tzbl' has the
     * following description: 投资比例
     * 
     * @return the value of field 'Tzbl'.
     */
    public java.lang.String getTzbl(
    ) {
        return this._tzbl;
    }

    /**
     * Returns the value of field 'tzfDz'. The field 'tzfDz' has
     * the following description: 投资方地址
     * 
     * @return the value of field 'TzfDz'.
     */
    public java.lang.String getTzfDz(
    ) {
        return this._tzfDz;
    }

    /**
     * Returns the value of field 'tzfNsrsbh'. The field
     * 'tzfNsrsbh' has the following description: 投资方纳税人识别号
     * 
     * @return the value of field 'TzfNsrsbh'.
     */
    public java.lang.String getTzfNsrsbh(
    ) {
        return this._tzfNsrsbh;
    }

    /**
     * Returns the value of field 'tzfZjhm'. The field 'tzfZjhm'
     * has the following description: 证件号码
     * 
     * @return the value of field 'TzfZjhm'.
     */
    public java.lang.String getTzfZjhm(
    ) {
        return this._tzfZjhm;
    }

    /**
     * Returns the value of field 'tzfZjzlDm'. The field
     * 'tzfZjzlDm' has the following description: 证件种类
     * 
     * @return the value of field 'TzfZjzlDm'.
     */
    public java.lang.String getTzfZjzlDm(
    ) {
        return this._tzfZjzlDm;
    }

    /**
     * Returns the value of field 'tzfmc'. The field 'tzfmc' has
     * the following description: 投资方名称
     * 
     * @return the value of field 'Tzfmc'.
     */
    public java.lang.String getTzfmc(
    ) {
        return this._tzfmc;
    }

    /**
     * Returns the value of field 'tzfxz'. The field 'tzfxz' has
     * the following description: 投资方性质
     * 
     * @return the value of field 'Tzfxz'.
     */
    public java.lang.String getTzfxz(
    ) {
        return this._tzfxz;
    }

    /**
     * Returns the value of field 'tzje'. The field 'tzje' has the
     * following description: 投资金额
     * 
     * @return the value of field 'Tzje'.
     */
    public java.lang.String getTzje(
    ) {
        return this._tzje;
    }

    /**
     * Returns the value of field 'xh'. The field 'xh' has the
     * following description: 序号
     * 
     * @return the value of field 'Xh'.
     */
    public java.lang.String getXh(
    ) {
        return this._xh;
    }

    /**
     * Returns the value of field 'xyczrq'. The field 'xyczrq' has
     * the following description: 协议出资日期
     * 
     * @return the value of field 'Xyczrq'.
     */
    public java.lang.String getXyczrq(
    ) {
        return this._xyczrq;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'bzDm'. The field 'bzDm' has the
     * following description: 币种代码
     * 
     * @param bzDm the value of field 'bzDm'.
     */
    public void setBzDm(
            final java.lang.String bzDm) {
        this._bzDm = bzDm;
    }

    /**
     * Sets the value of field 'fpbl'. The field 'fpbl' has the
     * following description: 分配比例
     * 
     * @param fpbl the value of field 'fpbl'.
     */
    public void setFpbl(
            final java.lang.String fpbl) {
        this._fpbl = fpbl;
    }

    /**
     * Sets the value of field 'gjDm'. The field 'gjDm' has the
     * following description: 国家代码
     * 
     * @param gjDm the value of field 'gjDm'.
     */
    public void setGjDm(
            final java.lang.String gjDm) {
        this._gjDm = gjDm;
    }

    /**
     * Sets the value of field 'hlbj'. The field 'hlbj' has the
     * following description: 汇率比价
     * 
     * @param hlbj the value of field 'hlbj'.
     */
    public void setHlbj(
            final java.lang.String hlbj) {
        this._hlbj = hlbj;
    }

    /**
     * Sets the value of field 'tzbl'. The field 'tzbl' has the
     * following description: 投资比例
     * 
     * @param tzbl the value of field 'tzbl'.
     */
    public void setTzbl(
            final java.lang.String tzbl) {
        this._tzbl = tzbl;
    }

    /**
     * Sets the value of field 'tzfDz'. The field 'tzfDz' has the
     * following description: 投资方地址
     * 
     * @param tzfDz the value of field 'tzfDz'.
     */
    public void setTzfDz(
            final java.lang.String tzfDz) {
        this._tzfDz = tzfDz;
    }

    /**
     * Sets the value of field 'tzfNsrsbh'. The field 'tzfNsrsbh'
     * has the following description: 投资方纳税人识别号
     * 
     * @param tzfNsrsbh the value of field 'tzfNsrsbh'.
     */
    public void setTzfNsrsbh(
            final java.lang.String tzfNsrsbh) {
        this._tzfNsrsbh = tzfNsrsbh;
    }

    /**
     * Sets the value of field 'tzfZjhm'. The field 'tzfZjhm' has
     * the following description: 证件号码
     * 
     * @param tzfZjhm the value of field 'tzfZjhm'.
     */
    public void setTzfZjhm(
            final java.lang.String tzfZjhm) {
        this._tzfZjhm = tzfZjhm;
    }

    /**
     * Sets the value of field 'tzfZjzlDm'. The field 'tzfZjzlDm'
     * has the following description: 证件种类
     * 
     * @param tzfZjzlDm the value of field 'tzfZjzlDm'.
     */
    public void setTzfZjzlDm(
            final java.lang.String tzfZjzlDm) {
        this._tzfZjzlDm = tzfZjzlDm;
    }

    /**
     * Sets the value of field 'tzfmc'. The field 'tzfmc' has the
     * following description: 投资方名称
     * 
     * @param tzfmc the value of field 'tzfmc'.
     */
    public void setTzfmc(
            final java.lang.String tzfmc) {
        this._tzfmc = tzfmc;
    }

    /**
     * Sets the value of field 'tzfxz'. The field 'tzfxz' has the
     * following description: 投资方性质
     * 
     * @param tzfxz the value of field 'tzfxz'.
     */
    public void setTzfxz(
            final java.lang.String tzfxz) {
        this._tzfxz = tzfxz;
    }

    /**
     * Sets the value of field 'tzje'. The field 'tzje' has the
     * following description: 投资金额
     * 
     * @param tzje the value of field 'tzje'.
     */
    public void setTzje(
            final java.lang.String tzje) {
        this._tzje = tzje;
    }

    /**
     * Sets the value of field 'xh'. The field 'xh' has the
     * following description: 序号
     * 
     * @param xh the value of field 'xh'.
     */
    public void setXh(
            final java.lang.String xh) {
        this._xh = xh;
    }

    /**
     * Sets the value of field 'xyczrq'. The field 'xyczrq' has the
     * following description: 协议出资日期
     * 
     * @param xyczrq the value of field 'xyczrq'.
     */
    public void setXyczrq(
            final java.lang.String xyczrq) {
        this._xyczrq = xyczrq;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrTzfxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrTzfxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrTzfxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrTzfxx.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
