/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 2008版企业所得税汇总纳税分支机构分配表
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _head.
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Head _head;

    /**
     * 基本信息表头
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.JbxxHead _jbxxHead;

    /**
     * Field _body.
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Body _body;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'body'.
     * 
     * @return the value of field 'Body'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Body getBody(
    ) {
        return this._body;
    }

    /**
     * Returns the value of field 'head'.
     * 
     * @return the value of field 'Head'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Head getHead(
    ) {
        return this._head;
    }

    /**
     * Returns the value of field 'jbxxHead'. The field 'jbxxHead'
     * has the following description: 基本信息表头
     * 
     * @return the value of field 'JbxxHead'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.JbxxHead getJbxxHead(
    ) {
        return this._jbxxHead;
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
     * Sets the value of field 'body'.
     * 
     * @param body the value of field 'body'.
     */
    public void setBody(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Body body) {
        this._body = body;
    }

    /**
     * Sets the value of field 'head'.
     * 
     * @param head the value of field 'head'.
     */
    public void setHead(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Head head) {
        this._head = head;
    }

    /**
     * Sets the value of field 'jbxxHead'. The field 'jbxxHead' has
     * the following description: 基本信息表头
     * 
     * @param jbxxHead the value of field 'jbxxHead'.
     */
    public void setJbxxHead(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.JbxxHead jbxxHead) {
        this._jbxxHead = jbxxHead;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbType
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbType) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbType.class, reader);
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
