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
 * 注册资本投资总额
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrZczbtzze implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 序号
     */
    private java.lang.String _xh;

    /**
     * 注册币种代码
     */
    private java.lang.String _zcbzDm;

    /**
     * 注册资本金额
     */
    private java.lang.String _zczbJe = "0.00";


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrZczbtzze() {
        super();
        setZczbJe("0.00");
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'zcbzDm'. The field 'zcbzDm' has
     * the following description: 注册币种代码
     * 
     * @return the value of field 'ZcbzDm'.
     */
    public java.lang.String getZcbzDm(
    ) {
        return this._zcbzDm;
    }

    /**
     * Returns the value of field 'zczbJe'. The field 'zczbJe' has
     * the following description: 注册资本金额
     * 
     * @return the value of field 'ZczbJe'.
     */
    public java.lang.String getZczbJe(
    ) {
        return this._zczbJe;
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
     * Sets the value of field 'zcbzDm'. The field 'zcbzDm' has the
     * following description: 注册币种代码
     * 
     * @param zcbzDm the value of field 'zcbzDm'.
     */
    public void setZcbzDm(
            final java.lang.String zcbzDm) {
        this._zcbzDm = zcbzDm;
    }

    /**
     * Sets the value of field 'zczbJe'. The field 'zczbJe' has the
     * following description: 注册资本金额
     * 
     * @param zczbJe the value of field 'zczbJe'.
     */
    public void setZczbJe(
            final java.lang.String zczbJe) {
        this._zczbJe = zczbJe;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZczbtzze
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZczbtzze unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZczbtzze) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrZczbtzze.class, reader);
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
