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
 * 总分支机构填写内容
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdAZfzjg implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 本期金额—总分支机构明细信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeZfzjgxx _bqjeZfzjgxx;

    /**
     * 累计金额—总分支机构明细信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeZfzjgxx _ljjeZfzjgxx;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdAZfzjg() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bqjeZfzjgxx'. The field
     * 'bqjeZfzjgxx' has the following description: 本期金额—总分支机构明细信息
     * 
     * @return the value of field 'BqjeZfzjgxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeZfzjgxx getBqjeZfzjgxx(
    ) {
        return this._bqjeZfzjgxx;
    }

    /**
     * Returns the value of field 'ljjeZfzjgxx'. The field
     * 'ljjeZfzjgxx' has the following description: 累计金额—总分支机构明细信息
     * 
     * @return the value of field 'LjjeZfzjgxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeZfzjgxx getLjjeZfzjgxx(
    ) {
        return this._ljjeZfzjgxx;
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
     * Sets the value of field 'bqjeZfzjgxx'. The field
     * 'bqjeZfzjgxx' has the following description: 本期金额—总分支机构明细信息
     * 
     * @param bqjeZfzjgxx the value of field 'bqjeZfzjgxx'.
     */
    public void setBqjeZfzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeZfzjgxx bqjeZfzjgxx) {
        this._bqjeZfzjgxx = bqjeZfzjgxx;
    }

    /**
     * Sets the value of field 'ljjeZfzjgxx'. The field
     * 'ljjeZfzjgxx' has the following description: 累计金额—总分支机构明细信息
     * 
     * @param ljjeZfzjgxx the value of field 'ljjeZfzjgxx'.
     */
    public void setLjjeZfzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeZfzjgxx ljjeZfzjgxx) {
        this._ljjeZfzjgxx = ljjeZfzjgxx;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjg
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjg unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjg) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjg.class, reader);
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
