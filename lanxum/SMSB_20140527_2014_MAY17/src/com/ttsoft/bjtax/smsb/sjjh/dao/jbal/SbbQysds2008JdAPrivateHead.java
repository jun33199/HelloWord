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
 * 2008版企业所得税月(季)度申报表A类私有表头
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdAPrivateHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 法定代表人（签字）
     */
    private java.lang.String _fddbr;

    /**
     * 法定代表人签字日期
     */
    private java.lang.String _fddbrQzrq;

    /**
     * 会计主管
     */
    private java.lang.String _kjzg;

    /**
     * 代理申报中介机构
     */
    private java.lang.String _dlsbZjjg;

    /**
     * 代理申报中介机构经办人
     */
    private java.lang.String _dlsbZjjgJbr;

    /**
     * 代理申报中介机构经办人执业证件号码
     */
    private java.lang.String _dlsbZjjgJbrZyzjhm;

    /**
     * 代理申报日期
     */
    private java.lang.String _dlSbrq;

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

    public SbbQysds2008JdAPrivateHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dlSbrq'. The field 'dlSbrq' has
     * the following description: 代理申报日期
     * 
     * @return the value of field 'DlSbrq'.
     */
    public java.lang.String getDlSbrq(
    ) {
        return this._dlSbrq;
    }

    /**
     * Returns the value of field 'dlsbZjjg'. The field 'dlsbZjjg'
     * has the following description: 代理申报中介机构
     * 
     * @return the value of field 'DlsbZjjg'.
     */
    public java.lang.String getDlsbZjjg(
    ) {
        return this._dlsbZjjg;
    }

    /**
     * Returns the value of field 'dlsbZjjgJbr'. The field
     * 'dlsbZjjgJbr' has the following description: 代理申报中介机构经办人
     * 
     * @return the value of field 'DlsbZjjgJbr'.
     */
    public java.lang.String getDlsbZjjgJbr(
    ) {
        return this._dlsbZjjgJbr;
    }

    /**
     * Returns the value of field 'dlsbZjjgJbrZyzjhm'. The field
     * 'dlsbZjjgJbrZyzjhm' has the following description:
     * 代理申报中介机构经办人执业证件号码
     * 
     * @return the value of field 'DlsbZjjgJbrZyzjhm'.
     */
    public java.lang.String getDlsbZjjgJbrZyzjhm(
    ) {
        return this._dlsbZjjgJbrZyzjhm;
    }

    /**
     * Returns the value of field 'fddbr'. The field 'fddbr' has
     * the following description: 法定代表人（签字）
     * 
     * @return the value of field 'Fddbr'.
     */
    public java.lang.String getFddbr(
    ) {
        return this._fddbr;
    }

    /**
     * Returns the value of field 'fddbrQzrq'. The field
     * 'fddbrQzrq' has the following description: 法定代表人签字日期
     * 
     * @return the value of field 'FddbrQzrq'.
     */
    public java.lang.String getFddbrQzrq(
    ) {
        return this._fddbrQzrq;
    }

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
     * Sets the value of field 'dlSbrq'. The field 'dlSbrq' has the
     * following description: 代理申报日期
     * 
     * @param dlSbrq the value of field 'dlSbrq'.
     */
    public void setDlSbrq(
            final java.lang.String dlSbrq) {
        this._dlSbrq = dlSbrq;
    }

    /**
     * Sets the value of field 'dlsbZjjg'. The field 'dlsbZjjg' has
     * the following description: 代理申报中介机构
     * 
     * @param dlsbZjjg the value of field 'dlsbZjjg'.
     */
    public void setDlsbZjjg(
            final java.lang.String dlsbZjjg) {
        this._dlsbZjjg = dlsbZjjg;
    }

    /**
     * Sets the value of field 'dlsbZjjgJbr'. The field
     * 'dlsbZjjgJbr' has the following description: 代理申报中介机构经办人
     * 
     * @param dlsbZjjgJbr the value of field 'dlsbZjjgJbr'.
     */
    public void setDlsbZjjgJbr(
            final java.lang.String dlsbZjjgJbr) {
        this._dlsbZjjgJbr = dlsbZjjgJbr;
    }

    /**
     * Sets the value of field 'dlsbZjjgJbrZyzjhm'. The field
     * 'dlsbZjjgJbrZyzjhm' has the following description:
     * 代理申报中介机构经办人执业证件号码
     * 
     * @param dlsbZjjgJbrZyzjhm the value of field
     * 'dlsbZjjgJbrZyzjhm'.
     */
    public void setDlsbZjjgJbrZyzjhm(
            final java.lang.String dlsbZjjgJbrZyzjhm) {
        this._dlsbZjjgJbrZyzjhm = dlsbZjjgJbrZyzjhm;
    }

    /**
     * Sets the value of field 'fddbr'. The field 'fddbr' has the
     * following description: 法定代表人（签字）
     * 
     * @param fddbr the value of field 'fddbr'.
     */
    public void setFddbr(
            final java.lang.String fddbr) {
        this._fddbr = fddbr;
    }

    /**
     * Sets the value of field 'fddbrQzrq'. The field 'fddbrQzrq'
     * has the following description: 法定代表人签字日期
     * 
     * @param fddbrQzrq the value of field 'fddbrQzrq'.
     */
    public void setFddbrQzrq(
            final java.lang.String fddbrQzrq) {
        this._fddbrQzrq = fddbrQzrq;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAPrivateHea
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAPrivateHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAPrivateHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAPrivateHead.class, reader);
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
