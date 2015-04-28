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
 * 2008版企业所得税汇总纳税分支机构分配表表头
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 公用表头信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PublicHead _publicHead;

    /**
     * 分配比例有效期
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.FpblYxq _fpblYxq;

    /**
     * 私有表头信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PrivateHead _privateHead;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fpblYxq'. The field 'fpblYxq'
     * has the following description: 分配比例有效期
     * 
     * @return the value of field 'FpblYxq'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.FpblYxq getFpblYxq(
    ) {
        return this._fpblYxq;
    }

    /**
     * Returns the value of field 'privateHead'. The field
     * 'privateHead' has the following description: 私有表头信息
     * 
     * @return the value of field 'PrivateHead'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PrivateHead getPrivateHead(
    ) {
        return this._privateHead;
    }

    /**
     * Returns the value of field 'publicHead'. The field
     * 'publicHead' has the following description: 公用表头信息
     * 
     * @return the value of field 'PublicHead'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PublicHead getPublicHead(
    ) {
        return this._publicHead;
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
     * Sets the value of field 'fpblYxq'. The field 'fpblYxq' has
     * the following description: 分配比例有效期
     * 
     * @param fpblYxq the value of field 'fpblYxq'.
     */
    public void setFpblYxq(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.FpblYxq fpblYxq) {
        this._fpblYxq = fpblYxq;
    }

    /**
     * Sets the value of field 'privateHead'. The field
     * 'privateHead' has the following description: 私有表头信息
     * 
     * @param privateHead the value of field 'privateHead'.
     */
    public void setPrivateHead(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PrivateHead privateHead) {
        this._privateHead = privateHead;
    }

    /**
     * Sets the value of field 'publicHead'. The field 'publicHead'
     * has the following description: 公用表头信息
     * 
     * @param publicHead the value of field 'publicHead'.
     */
    public void setPublicHead(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PublicHead publicHead) {
        this._publicHead = publicHead;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbHead
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbHead.class, reader);
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
