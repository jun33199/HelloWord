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
 * ʵ�о�ʵԤ�ɵ���˰����д����
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdANsrJsYj implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ���ڽ���˰�˾�ʵԤ��
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeNsrJsYj _bqjeNsrJsYj;

    /**
     * �ۼƽ���˰�˾�ʵԤ��
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeNsrJsYj _ljjeNsrJsYj;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdANsrJsYj() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bqjeNsrJsYj'. The field
     * 'bqjeNsrJsYj' has the following description: ���ڽ���˰�˾�ʵԤ��
     * 
     * @return the value of field 'BqjeNsrJsYj'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeNsrJsYj getBqjeNsrJsYj(
    ) {
        return this._bqjeNsrJsYj;
    }

    /**
     * Returns the value of field 'ljjeNsrJsYj'. The field
     * 'ljjeNsrJsYj' has the following description: �ۼƽ���˰�˾�ʵԤ��
     * 
     * @return the value of field 'LjjeNsrJsYj'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeNsrJsYj getLjjeNsrJsYj(
    ) {
        return this._ljjeNsrJsYj;
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
     * Sets the value of field 'bqjeNsrJsYj'. The field
     * 'bqjeNsrJsYj' has the following description: ���ڽ���˰�˾�ʵԤ��
     * 
     * @param bqjeNsrJsYj the value of field 'bqjeNsrJsYj'.
     */
    public void setBqjeNsrJsYj(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeNsrJsYj bqjeNsrJsYj) {
        this._bqjeNsrJsYj = bqjeNsrJsYj;
    }

    /**
     * Sets the value of field 'ljjeNsrJsYj'. The field
     * 'ljjeNsrJsYj' has the following description: �ۼƽ���˰�˾�ʵԤ��
     * 
     * @param ljjeNsrJsYj the value of field 'ljjeNsrJsYj'.
     */
    public void setLjjeNsrJsYj(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeNsrJsYj ljjeNsrJsYj) {
        this._ljjeNsrJsYj = ljjeNsrJsYj;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYj
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYj unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYj) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYj.class, reader);
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
