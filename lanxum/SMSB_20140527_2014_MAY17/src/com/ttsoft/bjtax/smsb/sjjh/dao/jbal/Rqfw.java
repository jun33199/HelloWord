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
 * 一个日期范围（包括起、止日期，各8位字符，可用于所属时期、认定日期等）
 * 
 * @version $Revision$ $Date$
 */
public class Rqfw implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 日期起
     */
    private java.lang.String _rqQ;

    /**
     * 日期止
     */
    private java.lang.String _rqZ;


      //----------------/
     //- Constructors -/
    //----------------/

    public Rqfw() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'rqQ'. The field 'rqQ' has the
     * following description: 日期起
     * 
     * @return the value of field 'RqQ'.
     */
    public java.lang.String getRqQ(
    ) {
        return this._rqQ;
    }

    /**
     * Returns the value of field 'rqZ'. The field 'rqZ' has the
     * following description: 日期止
     * 
     * @return the value of field 'RqZ'.
     */
    public java.lang.String getRqZ(
    ) {
        return this._rqZ;
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
     * Sets the value of field 'rqQ'. The field 'rqQ' has the
     * following description: 日期起
     * 
     * @param rqQ the value of field 'rqQ'.
     */
    public void setRqQ(
            final java.lang.String rqQ) {
        this._rqQ = rqQ;
    }

    /**
     * Sets the value of field 'rqZ'. The field 'rqZ' has the
     * following description: 日期止
     * 
     * @param rqZ the value of field 'rqZ'.
     */
    public void setRqZ(
            final java.lang.String rqZ) {
        this._rqZ = rqZ;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Rqfw
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Rqfw unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Rqfw) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Rqfw.class, reader);
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
