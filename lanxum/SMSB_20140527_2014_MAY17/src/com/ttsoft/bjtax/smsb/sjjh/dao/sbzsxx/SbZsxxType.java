/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class SbZsxxType.
 * 
 * @version $Revision$ $Date$
 */
public class SbZsxxType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 表头
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Head _head;

    /**
     * 表体
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Body _body;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbZsxxType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'body'. The field 'body' has the
     * following description: 表体
     * 
     * @return the value of field 'Body'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Body getBody(
    ) {
        return this._body;
    }

    /**
     * Returns the value of field 'head'. The field 'head' has the
     * following description: 表头
     * 
     * @return the value of field 'Head'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Head getHead(
    ) {
        return this._head;
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
     * Sets the value of field 'body'. The field 'body' has the
     * following description: 表体
     * 
     * @param body the value of field 'body'.
     */
    public void setBody(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Body body) {
        this._body = body;
    }

    /**
     * Sets the value of field 'head'. The field 'head' has the
     * following description: 表头
     * 
     * @param head the value of field 'head'.
     */
    public void setHead(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Head head) {
        this._head = head;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxType
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxType) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxType.class, reader);
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
