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
 * 实行税务机关确定方式预缴的纳税人填写内容
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdASwjgQdfsYj implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 本期金额—16 本月(季)确定预缴的所得税额
     */
    private java.lang.String _bqjeBjqdYjse;

    /**
     * 累计金额—16 本月(季)确定预缴的所得税额
     */
    private java.lang.String _ljjeBjqdYjse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdASwjgQdfsYj() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bqjeBjqdYjse'. The field
     * 'bqjeBjqdYjse' has the following description: 本期金额—16
     * 本月(季)确定预缴的所得税额
     * 
     * @return the value of field 'BqjeBjqdYjse'.
     */
    public java.lang.String getBqjeBjqdYjse(
    ) {
        return this._bqjeBjqdYjse;
    }

    /**
     * Returns the value of field 'ljjeBjqdYjse'. The field
     * 'ljjeBjqdYjse' has the following description: 累计金额—16
     * 本月(季)确定预缴的所得税额
     * 
     * @return the value of field 'LjjeBjqdYjse'.
     */
    public java.lang.String getLjjeBjqdYjse(
    ) {
        return this._ljjeBjqdYjse;
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
     * Sets the value of field 'bqjeBjqdYjse'. The field
     * 'bqjeBjqdYjse' has the following description: 本期金额—16
     * 本月(季)确定预缴的所得税额
     * 
     * @param bqjeBjqdYjse the value of field 'bqjeBjqdYjse'.
     */
    public void setBqjeBjqdYjse(
            final java.lang.String bqjeBjqdYjse) {
        this._bqjeBjqdYjse = bqjeBjqdYjse;
    }

    /**
     * Sets the value of field 'ljjeBjqdYjse'. The field
     * 'ljjeBjqdYjse' has the following description: 累计金额—16
     * 本月(季)确定预缴的所得税额
     * 
     * @param ljjeBjqdYjse the value of field 'ljjeBjqdYjse'.
     */
    public void setLjjeBjqdYjse(
            final java.lang.String ljjeBjqdYjse) {
        this._ljjeBjqdYjse = ljjeBjqdYjse;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASwjgQdfsYj
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASwjgQdfsYj unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASwjgQdfsYj) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdASwjgQdfsYj.class, reader);
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
