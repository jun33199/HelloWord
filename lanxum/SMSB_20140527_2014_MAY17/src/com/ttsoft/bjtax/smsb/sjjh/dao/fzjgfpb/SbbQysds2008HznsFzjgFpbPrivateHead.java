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
 * 2008版企业所得税汇总纳税分支机构分配表私有表头
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbPrivateHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 会计主管
     */
    private java.lang.String _kjzg;

    /**
     * 受理税务机关代码
     */
    private java.lang.String _slSwjgDm;

    /**
     * 受理税务机关名称
     */
    private java.lang.String _slSwjgMc;

    /**
     * 受理税务机关受理人代码
     */
    private java.lang.String _slSwjgSlrDm;

    /**
     * 受理税务机关受理人名称
     */
    private java.lang.String _slSwjgSlrMc;

    /**
     * 受理日期
     */
    private java.lang.String _slrq;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbPrivateHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'kjzg'. The field 'kjzg' has the
     * following description: 会计主管
     * 
     * @return the value of field 'Kjzg'.
     */
    public java.lang.String getKjzg(
    ) {
        return this._kjzg;
    }

    /**
     * Returns the value of field 'slSwjgDm'. The field 'slSwjgDm'
     * has the following description: 受理税务机关代码
     * 
     * @return the value of field 'SlSwjgDm'.
     */
    public java.lang.String getSlSwjgDm(
    ) {
        return this._slSwjgDm;
    }

    /**
     * Returns the value of field 'slSwjgMc'. The field 'slSwjgMc'
     * has the following description: 受理税务机关名称
     * 
     * @return the value of field 'SlSwjgMc'.
     */
    public java.lang.String getSlSwjgMc(
    ) {
        return this._slSwjgMc;
    }

    /**
     * Returns the value of field 'slSwjgSlrDm'. The field
     * 'slSwjgSlrDm' has the following description: 受理税务机关受理人代码
     * 
     * @return the value of field 'SlSwjgSlrDm'.
     */
    public java.lang.String getSlSwjgSlrDm(
    ) {
        return this._slSwjgSlrDm;
    }

    /**
     * Returns the value of field 'slSwjgSlrMc'. The field
     * 'slSwjgSlrMc' has the following description: 受理税务机关受理人名称
     * 
     * @return the value of field 'SlSwjgSlrMc'.
     */
    public java.lang.String getSlSwjgSlrMc(
    ) {
        return this._slSwjgSlrMc;
    }

    /**
     * Returns the value of field 'slrq'. The field 'slrq' has the
     * following description: 受理日期
     * 
     * @return the value of field 'Slrq'.
     */
    public java.lang.String getSlrq(
    ) {
        return this._slrq;
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
     * Sets the value of field 'kjzg'. The field 'kjzg' has the
     * following description: 会计主管
     * 
     * @param kjzg the value of field 'kjzg'.
     */
    public void setKjzg(
            final java.lang.String kjzg) {
        this._kjzg = kjzg;
    }

    /**
     * Sets the value of field 'slSwjgDm'. The field 'slSwjgDm' has
     * the following description: 受理税务机关代码
     * 
     * @param slSwjgDm the value of field 'slSwjgDm'.
     */
    public void setSlSwjgDm(
            final java.lang.String slSwjgDm) {
        this._slSwjgDm = slSwjgDm;
    }

    /**
     * Sets the value of field 'slSwjgMc'. The field 'slSwjgMc' has
     * the following description: 受理税务机关名称
     * 
     * @param slSwjgMc the value of field 'slSwjgMc'.
     */
    public void setSlSwjgMc(
            final java.lang.String slSwjgMc) {
        this._slSwjgMc = slSwjgMc;
    }

    /**
     * Sets the value of field 'slSwjgSlrDm'. The field
     * 'slSwjgSlrDm' has the following description: 受理税务机关受理人代码
     * 
     * @param slSwjgSlrDm the value of field 'slSwjgSlrDm'.
     */
    public void setSlSwjgSlrDm(
            final java.lang.String slSwjgSlrDm) {
        this._slSwjgSlrDm = slSwjgSlrDm;
    }

    /**
     * Sets the value of field 'slSwjgSlrMc'. The field
     * 'slSwjgSlrMc' has the following description: 受理税务机关受理人名称
     * 
     * @param slSwjgSlrMc the value of field 'slSwjgSlrMc'.
     */
    public void setSlSwjgSlrMc(
            final java.lang.String slSwjgSlrMc) {
        this._slSwjgSlrMc = slSwjgSlrMc;
    }

    /**
     * Sets the value of field 'slrq'. The field 'slrq' has the
     * following description: 受理日期
     * 
     * @param slrq the value of field 'slrq'.
     */
    public void setSlrq(
            final java.lang.String slrq) {
        this._slrq = slrq;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbPrivateHead
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbPrivateHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbPrivateHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbPrivateHead.class, reader);
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
