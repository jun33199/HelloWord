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
 * 申报表表头信息，包括主表和附表
 * 
 * @version $Revision$ $Date$
 */
public class SbbPublicHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 纳税人识别号
     */
    private java.lang.String _nsrsbh;

    /**
     * 纳税人名称
     */
    private java.lang.String _nsrmc;

    /**
     * 申报表填表日期
     */
    private java.lang.String _tbrq;

    /**
     * 申报所属时期
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Sssq _sssq;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbPublicHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'nsrmc'. The field 'nsrmc' has
     * the following description: 纳税人名称
     * 
     * @return the value of field 'Nsrmc'.
     */
    public java.lang.String getNsrmc(
    ) {
        return this._nsrmc;
    }

    /**
     * Returns the value of field 'nsrsbh'. The field 'nsrsbh' has
     * the following description: 纳税人识别号
     * 
     * @return the value of field 'Nsrsbh'.
     */
    public java.lang.String getNsrsbh(
    ) {
        return this._nsrsbh;
    }

    /**
     * Returns the value of field 'sssq'. The field 'sssq' has the
     * following description: 申报所属时期
     * 
     * @return the value of field 'Sssq'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Sssq getSssq(
    ) {
        return this._sssq;
    }

    /**
     * Returns the value of field 'tbrq'. The field 'tbrq' has the
     * following description: 申报表填表日期
     * 
     * @return the value of field 'Tbrq'.
     */
    public java.lang.String getTbrq(
    ) {
        return this._tbrq;
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
     * Sets the value of field 'nsrmc'. The field 'nsrmc' has the
     * following description: 纳税人名称
     * 
     * @param nsrmc the value of field 'nsrmc'.
     */
    public void setNsrmc(
            final java.lang.String nsrmc) {
        this._nsrmc = nsrmc;
    }

    /**
     * Sets the value of field 'nsrsbh'. The field 'nsrsbh' has the
     * following description: 纳税人识别号
     * 
     * @param nsrsbh the value of field 'nsrsbh'.
     */
    public void setNsrsbh(
            final java.lang.String nsrsbh) {
        this._nsrsbh = nsrsbh;
    }

    /**
     * Sets the value of field 'sssq'. The field 'sssq' has the
     * following description: 申报所属时期
     * 
     * @param sssq the value of field 'sssq'.
     */
    public void setSssq(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Sssq sssq) {
        this._sssq = sssq;
    }

    /**
     * Sets the value of field 'tbrq'. The field 'tbrq' has the
     * following description: 申报表填表日期
     * 
     * @param tbrq the value of field 'tbrq'.
     */
    public void setTbrq(
            final java.lang.String tbrq) {
        this._tbrq = tbrq;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbPublicHead
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbPublicHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbPublicHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbPublicHead.class, reader);
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
