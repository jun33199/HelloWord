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
 * 总机构信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrZjgxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 总机构纳税人识别号
     */
    private java.lang.String _zjgNsrsbh;

    /**
     * 总机构名称
     */
    private java.lang.String _zjgmc;

    /**
     * 总机构注册地址
     */
    private java.lang.String _zjgZcdz;

    /**
     * 经营范围
     */
    private java.lang.String _jyfw;

    /**
     * 总机构法定代表人名称
     */
    private java.lang.String _zjgFddbrmc;

    /**
     * 主管税务机关代码
     */
    private java.lang.String _zgswjgDm;

    /**
     * 主管税务机关名称
     */
    private java.lang.String _zgswjgMc;

    /**
     * 邮编
     */
    private java.lang.String _yb;

    /**
     * 电话号码
     */
    private java.lang.String _dhhm;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrZjgxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dhhm'. The field 'dhhm' has the
     * following description: 电话号码
     * 
     * @return the value of field 'Dhhm'.
     */
    public java.lang.String getDhhm(
    ) {
        return this._dhhm;
    }

    /**
     * Returns the value of field 'jyfw'. The field 'jyfw' has the
     * following description: 经营范围
     * 
     * @return the value of field 'Jyfw'.
     */
    public java.lang.String getJyfw(
    ) {
        return this._jyfw;
    }

    /**
     * Returns the value of field 'yb'. The field 'yb' has the
     * following description: 邮编
     * 
     * @return the value of field 'Yb'.
     */
    public java.lang.String getYb(
    ) {
        return this._yb;
    }

    /**
     * Returns the value of field 'zgswjgDm'. The field 'zgswjgDm'
     * has the following description: 主管税务机关代码
     * 
     * @return the value of field 'ZgswjgDm'.
     */
    public java.lang.String getZgswjgDm(
    ) {
        return this._zgswjgDm;
    }

    /**
     * Returns the value of field 'zgswjgMc'. The field 'zgswjgMc'
     * has the following description: 主管税务机关名称
     * 
     * @return the value of field 'ZgswjgMc'.
     */
    public java.lang.String getZgswjgMc(
    ) {
        return this._zgswjgMc;
    }

    /**
     * Returns the value of field 'zjgFddbrmc'. The field
     * 'zjgFddbrmc' has the following description: 总机构法定代表人名称
     * 
     * @return the value of field 'ZjgFddbrmc'.
     */
    public java.lang.String getZjgFddbrmc(
    ) {
        return this._zjgFddbrmc;
    }

    /**
     * Returns the value of field 'zjgNsrsbh'. The field
     * 'zjgNsrsbh' has the following description: 总机构纳税人识别号
     * 
     * @return the value of field 'ZjgNsrsbh'.
     */
    public java.lang.String getZjgNsrsbh(
    ) {
        return this._zjgNsrsbh;
    }

    /**
     * Returns the value of field 'zjgZcdz'. The field 'zjgZcdz'
     * has the following description: 总机构注册地址
     * 
     * @return the value of field 'ZjgZcdz'.
     */
    public java.lang.String getZjgZcdz(
    ) {
        return this._zjgZcdz;
    }

    /**
     * Returns the value of field 'zjgmc'. The field 'zjgmc' has
     * the following description: 总机构名称
     * 
     * @return the value of field 'Zjgmc'.
     */
    public java.lang.String getZjgmc(
    ) {
        return this._zjgmc;
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
     * Sets the value of field 'dhhm'. The field 'dhhm' has the
     * following description: 电话号码
     * 
     * @param dhhm the value of field 'dhhm'.
     */
    public void setDhhm(
            final java.lang.String dhhm) {
        this._dhhm = dhhm;
    }

    /**
     * Sets the value of field 'jyfw'. The field 'jyfw' has the
     * following description: 经营范围
     * 
     * @param jyfw the value of field 'jyfw'.
     */
    public void setJyfw(
            final java.lang.String jyfw) {
        this._jyfw = jyfw;
    }

    /**
     * Sets the value of field 'yb'. The field 'yb' has the
     * following description: 邮编
     * 
     * @param yb the value of field 'yb'.
     */
    public void setYb(
            final java.lang.String yb) {
        this._yb = yb;
    }

    /**
     * Sets the value of field 'zgswjgDm'. The field 'zgswjgDm' has
     * the following description: 主管税务机关代码
     * 
     * @param zgswjgDm the value of field 'zgswjgDm'.
     */
    public void setZgswjgDm(
            final java.lang.String zgswjgDm) {
        this._zgswjgDm = zgswjgDm;
    }

    /**
     * Sets the value of field 'zgswjgMc'. The field 'zgswjgMc' has
     * the following description: 主管税务机关名称
     * 
     * @param zgswjgMc the value of field 'zgswjgMc'.
     */
    public void setZgswjgMc(
            final java.lang.String zgswjgMc) {
        this._zgswjgMc = zgswjgMc;
    }

    /**
     * Sets the value of field 'zjgFddbrmc'. The field 'zjgFddbrmc'
     * has the following description: 总机构法定代表人名称
     * 
     * @param zjgFddbrmc the value of field 'zjgFddbrmc'.
     */
    public void setZjgFddbrmc(
            final java.lang.String zjgFddbrmc) {
        this._zjgFddbrmc = zjgFddbrmc;
    }

    /**
     * Sets the value of field 'zjgNsrsbh'. The field 'zjgNsrsbh'
     * has the following description: 总机构纳税人识别号
     * 
     * @param zjgNsrsbh the value of field 'zjgNsrsbh'.
     */
    public void setZjgNsrsbh(
            final java.lang.String zjgNsrsbh) {
        this._zjgNsrsbh = zjgNsrsbh;
    }

    /**
     * Sets the value of field 'zjgZcdz'. The field 'zjgZcdz' has
     * the following description: 总机构注册地址
     * 
     * @param zjgZcdz the value of field 'zjgZcdz'.
     */
    public void setZjgZcdz(
            final java.lang.String zjgZcdz) {
        this._zjgZcdz = zjgZcdz;
    }

    /**
     * Sets the value of field 'zjgmc'. The field 'zjgmc' has the
     * following description: 总机构名称
     * 
     * @param zjgmc the value of field 'zjgmc'.
     */
    public void setZjgmc(
            final java.lang.String zjgmc) {
        this._zjgmc = zjgmc;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZjgxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZjgxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZjgxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZjgxx.class, reader);
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
