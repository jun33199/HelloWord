/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.jbal;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 实行按上年度平均额预缴的纳税人填写内容
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdASndPjeYj implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 本期金额—12 本月(季)应纳税所得额（11行/4或11行/12）
     */
    private java.lang.String _bqjeBjYnssde;

    /**
     * 本期金额—14 本月（季）应纳所得税额（12行×13行）
     */
    private java.lang.String _bqjeBjYnsdse;

    /**
     * 累计金额—11 上一年度应纳税所得额
     */
    private java.lang.String _ljjeSndYnssde;

    /**
     * 累计金额—12 本月(季)应纳税所得额（11行/4或11行/12）
     */
    private java.lang.String _ljjeBjYnssde;

    /**
     * 累计金额—14 本月（季）应纳所得税额（12行×13行）
     */
    private java.lang.String _ljjeBjYnsdse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdASndPjeYj() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bqjeBjYnsdse'. The field
     * 'bqjeBjYnsdse' has the following description: 本期金额—14
     * 本月（季）应纳所得税额（12行×13行）
     * 
     * @return the value of field 'BqjeBjYnsdse'.
     */
    public java.lang.String getBqjeBjYnsdse(
    ) {
        return this._bqjeBjYnsdse;
    }

    /**
     * Returns the value of field 'bqjeBjYnssde'. The field
     * 'bqjeBjYnssde' has the following description: 本期金额—12
     * 本月(季)应纳税所得额（11行/4或11行/12）
     * 
     * @return the value of field 'BqjeBjYnssde'.
     */
    public java.lang.String getBqjeBjYnssde(
    ) {
        return this._bqjeBjYnssde;
    }

    /**
     * Returns the value of field 'ljjeBjYnsdse'. The field
     * 'ljjeBjYnsdse' has the following description: 累计金额—14
     * 本月（季）应纳所得税额（12行×13行）
     * 
     * @return the value of field 'LjjeBjYnsdse'.
     */
    public java.lang.String getLjjeBjYnsdse(
    ) {
        return this._ljjeBjYnsdse;
    }

    /**
     * Returns the value of field 'ljjeBjYnssde'. The field
     * 'ljjeBjYnssde' has the following description: 累计金额—12
     * 本月(季)应纳税所得额（11行/4或11行/12）
     * 
     * @return the value of field 'LjjeBjYnssde'.
     */
    public java.lang.String getLjjeBjYnssde(
    ) {
        return this._ljjeBjYnssde;
    }

    /**
     * Returns the value of field 'ljjeSndYnssde'. The field
     * 'ljjeSndYnssde' has the following description: 累计金额—11
     * 上一年度应纳税所得额
     * 
     * @return the value of field 'LjjeSndYnssde'.
     */
    public java.lang.String getLjjeSndYnssde(
    ) {
        return this._ljjeSndYnssde;
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
     * Sets the value of field 'bqjeBjYnsdse'. The field
     * 'bqjeBjYnsdse' has the following description: 本期金额—14
     * 本月（季）应纳所得税额（12行×13行）
     * 
     * @param bqjeBjYnsdse the value of field 'bqjeBjYnsdse'.
     */
    public void setBqjeBjYnsdse(
            final java.lang.String bqjeBjYnsdse) {
        this._bqjeBjYnsdse = bqjeBjYnsdse;
    }

    /**
     * Sets the value of field 'bqjeBjYnssde'. The field
     * 'bqjeBjYnssde' has the following description: 本期金额—12
     * 本月(季)应纳税所得额（11行/4或11行/12）
     * 
     * @param bqjeBjYnssde the value of field 'bqjeBjYnssde'.
     */
    public void setBqjeBjYnssde(
            final java.lang.String bqjeBjYnssde) {
        this._bqjeBjYnssde = bqjeBjYnssde;
    }

    /**
     * Sets the value of field 'ljjeBjYnsdse'. The field
     * 'ljjeBjYnsdse' has the following description: 累计金额—14
     * 本月（季）应纳所得税额（12行×13行）
     * 
     * @param ljjeBjYnsdse the value of field 'ljjeBjYnsdse'.
     */
    public void setLjjeBjYnsdse(
            final java.lang.String ljjeBjYnsdse) {
        this._ljjeBjYnsdse = ljjeBjYnsdse;
    }

    /**
     * Sets the value of field 'ljjeBjYnssde'. The field
     * 'ljjeBjYnssde' has the following description: 累计金额—12
     * 本月(季)应纳税所得额（11行/4或11行/12）
     * 
     * @param ljjeBjYnssde the value of field 'ljjeBjYnssde'.
     */
    public void setLjjeBjYnssde(
            final java.lang.String ljjeBjYnssde) {
        this._ljjeBjYnssde = ljjeBjYnssde;
    }

    /**
     * Sets the value of field 'ljjeSndYnssde'. The field
     * 'ljjeSndYnssde' has the following description: 累计金额—11
     * 上一年度应纳税所得额
     * 
     * @param ljjeSndYnssde the value of field 'ljjeSndYnssde'.
     */
    public void setLjjeSndYnssde(
            final java.lang.String ljjeSndYnssde) {
        this._ljjeSndYnssde = ljjeSndYnssde;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASndPjeYj
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASndPjeYj unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASndPjeYj) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASndPjeYj.class, reader);
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
