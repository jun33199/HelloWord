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
 * 总分支机构填写内容明细信息
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdAZfzjgMxxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 18 总机构应分摊的所得税额( 9行或14行或16行%)
     */
    private java.lang.String _zjgYftSdse;

    /**
     * 19 中央财政集中分配税款的所得税额(9行或14行或16行 %)
     */
    private java.lang.String _zjgZyjzfpSdse;

    /**
     * 20 分支机构分摊的所得税额(9行或14行或16行 %)
     */
    private java.lang.String _zjgFzjgftSdse;

    /**
     * 21 分支机构分配比例
     */
    private java.lang.String _fzjgFpbl;

    /**
     * 22 分支机构分配的所得税额(20行21行)
     */
    private java.lang.String _fzjgFpSdse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdAZfzjgMxxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fzjgFpSdse'. The field
     * 'fzjgFpSdse' has the following description: 22
     * 分支机构分配的所得税额(20行21行)
     * 
     * @return the value of field 'FzjgFpSdse'.
     */
    public java.lang.String getFzjgFpSdse(
    ) {
        return this._fzjgFpSdse;
    }

    /**
     * Returns the value of field 'fzjgFpbl'. The field 'fzjgFpbl'
     * has the following description: 21 分支机构分配比例
     * 
     * @return the value of field 'FzjgFpbl'.
     */
    public java.lang.String getFzjgFpbl(
    ) {
        return this._fzjgFpbl;
    }

    /**
     * Returns the value of field 'zjgFzjgftSdse'. The field
     * 'zjgFzjgftSdse' has the following description: 20
     * 分支机构分摊的所得税额(9行或14行或16行 %)
     * 
     * @return the value of field 'ZjgFzjgftSdse'.
     */
    public java.lang.String getZjgFzjgftSdse(
    ) {
        return this._zjgFzjgftSdse;
    }

    /**
     * Returns the value of field 'zjgYftSdse'. The field
     * 'zjgYftSdse' has the following description: 18 总机构应分摊的所得税额(
     * 9行或14行或16行%)
     * 
     * @return the value of field 'ZjgYftSdse'.
     */
    public java.lang.String getZjgYftSdse(
    ) {
        return this._zjgYftSdse;
    }

    /**
     * Returns the value of field 'zjgZyjzfpSdse'. The field
     * 'zjgZyjzfpSdse' has the following description: 19
     * 中央财政集中分配税款的所得税额(9行或14行或16行 %)
     * 
     * @return the value of field 'ZjgZyjzfpSdse'.
     */
    public java.lang.String getZjgZyjzfpSdse(
    ) {
        return this._zjgZyjzfpSdse;
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
     * Sets the value of field 'fzjgFpSdse'. The field 'fzjgFpSdse'
     * has the following description: 22 分支机构分配的所得税额(20行21行)
     * 
     * @param fzjgFpSdse the value of field 'fzjgFpSdse'.
     */
    public void setFzjgFpSdse(
            final java.lang.String fzjgFpSdse) {
        this._fzjgFpSdse = fzjgFpSdse;
    }

    /**
     * Sets the value of field 'fzjgFpbl'. The field 'fzjgFpbl' has
     * the following description: 21 分支机构分配比例
     * 
     * @param fzjgFpbl the value of field 'fzjgFpbl'.
     */
    public void setFzjgFpbl(
            final java.lang.String fzjgFpbl) {
        this._fzjgFpbl = fzjgFpbl;
    }

    /**
     * Sets the value of field 'zjgFzjgftSdse'. The field
     * 'zjgFzjgftSdse' has the following description: 20
     * 分支机构分摊的所得税额(9行或14行或16行 %)
     * 
     * @param zjgFzjgftSdse the value of field 'zjgFzjgftSdse'.
     */
    public void setZjgFzjgftSdse(
            final java.lang.String zjgFzjgftSdse) {
        this._zjgFzjgftSdse = zjgFzjgftSdse;
    }

    /**
     * Sets the value of field 'zjgYftSdse'. The field 'zjgYftSdse'
     * has the following description: 18 总机构应分摊的所得税额( 9行或14行或16行%)
     * 
     * @param zjgYftSdse the value of field 'zjgYftSdse'.
     */
    public void setZjgYftSdse(
            final java.lang.String zjgYftSdse) {
        this._zjgYftSdse = zjgYftSdse;
    }

    /**
     * Sets the value of field 'zjgZyjzfpSdse'. The field
     * 'zjgZyjzfpSdse' has the following description: 19
     * 中央财政集中分配税款的所得税额(9行或14行或16行 %)
     * 
     * @param zjgZyjzfpSdse the value of field 'zjgZyjzfpSdse'.
     */
    public void setZjgZyjzfpSdse(
            final java.lang.String zjgZyjzfpSdse) {
        this._zjgZyjzfpSdse = zjgZyjzfpSdse;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjgMxxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjgMxxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjgMxxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAZfzjgMxxx.class, reader);
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
