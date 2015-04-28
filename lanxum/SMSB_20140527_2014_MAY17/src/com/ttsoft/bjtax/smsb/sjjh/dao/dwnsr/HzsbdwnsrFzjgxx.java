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
 * 分支机构信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrFzjgxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 序号
     */
    private java.lang.String _xh;

    /**
     * 分支机构纳税人识别号
     */
    private java.lang.String _fzjgNsrsbh;

    /**
     * 分支机构名称
     */
    private java.lang.String _fzjgmc;

    /**
     * 分支机构(或分店)电话号码(单位纳税人或临时纳税人不必填写)
     */
    private java.lang.String _dhhm;

    /**
     * 地址
     */
    private java.lang.String _dz;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrFzjgxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dhhm'. The field 'dhhm' has the
     * following description: 分支机构(或分店)电话号码(单位纳税人或临时纳税人不必填写)
     * 
     * @return the value of field 'Dhhm'.
     */
    public java.lang.String getDhhm(
    ) {
        return this._dhhm;
    }

    /**
     * Returns the value of field 'dz'. The field 'dz' has the
     * following description: 地址
     * 
     * @return the value of field 'Dz'.
     */
    public java.lang.String getDz(
    ) {
        return this._dz;
    }

    /**
     * Returns the value of field 'fzjgNsrsbh'. The field
     * 'fzjgNsrsbh' has the following description: 分支机构纳税人识别号
     * 
     * @return the value of field 'FzjgNsrsbh'.
     */
    public java.lang.String getFzjgNsrsbh(
    ) {
        return this._fzjgNsrsbh;
    }

    /**
     * Returns the value of field 'fzjgmc'. The field 'fzjgmc' has
     * the following description: 分支机构名称
     * 
     * @return the value of field 'Fzjgmc'.
     */
    public java.lang.String getFzjgmc(
    ) {
        return this._fzjgmc;
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
     * following description: 分支机构(或分店)电话号码(单位纳税人或临时纳税人不必填写)
     * 
     * @param dhhm the value of field 'dhhm'.
     */
    public void setDhhm(
            final java.lang.String dhhm) {
        this._dhhm = dhhm;
    }

    /**
     * Sets the value of field 'dz'. The field 'dz' has the
     * following description: 地址
     * 
     * @param dz the value of field 'dz'.
     */
    public void setDz(
            final java.lang.String dz) {
        this._dz = dz;
    }

    /**
     * Sets the value of field 'fzjgNsrsbh'. The field 'fzjgNsrsbh'
     * has the following description: 分支机构纳税人识别号
     * 
     * @param fzjgNsrsbh the value of field 'fzjgNsrsbh'.
     */
    public void setFzjgNsrsbh(
            final java.lang.String fzjgNsrsbh) {
        this._fzjgNsrsbh = fzjgNsrsbh;
    }

    /**
     * Sets the value of field 'fzjgmc'. The field 'fzjgmc' has the
     * following description: 分支机构名称
     * 
     * @param fzjgmc the value of field 'fzjgmc'.
     */
    public void setFzjgmc(
            final java.lang.String fzjgmc) {
        this._fzjgmc = fzjgmc;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrFzjgxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrFzjgxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrFzjgxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrFzjgxx.class, reader);
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
