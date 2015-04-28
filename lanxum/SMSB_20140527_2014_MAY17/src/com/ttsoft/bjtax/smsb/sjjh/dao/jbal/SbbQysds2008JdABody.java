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
 * 2008版企业所得税月(季)度申报表A类表体
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdABody implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 实行据实预缴的纳税人填写部分
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj _nsrJsYj;

    /**
     * 实行按上年度平均额预缴的纳税人填写内容
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj _sndPjeYj;

    /**
     * 实行税务机关确定方式预缴的纳税人填写部分
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj _swjgQdfsYj;

    /**
     * 总分支机构填写部分
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg _zfzjg;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdABody() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'nsrJsYj'. The field 'nsrJsYj'
     * has the following description: 实行据实预缴的纳税人填写部分
     * 
     * @return the value of field 'NsrJsYj'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj getNsrJsYj(
    ) {
        return this._nsrJsYj;
    }

    /**
     * Returns the value of field 'sndPjeYj'. The field 'sndPjeYj'
     * has the following description: 实行按上年度平均额预缴的纳税人填写内容
     * 
     * @return the value of field 'SndPjeYj'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj getSndPjeYj(
    ) {
        return this._sndPjeYj;
    }

    /**
     * Returns the value of field 'swjgQdfsYj'. The field
     * 'swjgQdfsYj' has the following description:
     * 实行税务机关确定方式预缴的纳税人填写部分
     * 
     * @return the value of field 'SwjgQdfsYj'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj getSwjgQdfsYj(
    ) {
        return this._swjgQdfsYj;
    }

    /**
     * Returns the value of field 'zfzjg'. The field 'zfzjg' has
     * the following description: 总分支机构填写部分
     * 
     * @return the value of field 'Zfzjg'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg getZfzjg(
    ) {
        return this._zfzjg;
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
     * Sets the value of field 'nsrJsYj'. The field 'nsrJsYj' has
     * the following description: 实行据实预缴的纳税人填写部分
     * 
     * @param nsrJsYj the value of field 'nsrJsYj'.
     */
    public void setNsrJsYj(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj nsrJsYj) {
        this._nsrJsYj = nsrJsYj;
    }

    /**
     * Sets the value of field 'sndPjeYj'. The field 'sndPjeYj' has
     * the following description: 实行按上年度平均额预缴的纳税人填写内容
     * 
     * @param sndPjeYj the value of field 'sndPjeYj'.
     */
    public void setSndPjeYj(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj sndPjeYj) {
        this._sndPjeYj = sndPjeYj;
    }

    /**
     * Sets the value of field 'swjgQdfsYj'. The field 'swjgQdfsYj'
     * has the following description: 实行税务机关确定方式预缴的纳税人填写部分
     * 
     * @param swjgQdfsYj the value of field 'swjgQdfsYj'.
     */
    public void setSwjgQdfsYj(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj swjgQdfsYj) {
        this._swjgQdfsYj = swjgQdfsYj;
    }

    /**
     * Sets the value of field 'zfzjg'. The field 'zfzjg' has the
     * following description: 总分支机构填写部分
     * 
     * @param zfzjg the value of field 'zfzjg'.
     */
    public void setZfzjg(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg zfzjg) {
        this._zfzjg = zfzjg;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody.class, reader);
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
