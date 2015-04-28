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
 * 总机构信息
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbZjgxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 总机构纳税人识别号
     */
    private java.lang.String _zjgNsrsbh;

    /**
     * 总机构名称
     */
    private java.lang.String _zjgMc;

    /**
     * 三项因素—收入总额
     */
    private java.lang.String _sxysSrze;

    /**
     * 三项因素—工资总额
     */
    private java.lang.String _sxysGzze;

    /**
     * 三项因素—资产总额
     */
    private java.lang.String _sxysZcze;

    /**
     * 三项因素—合计
     */
    private java.lang.String _sxysHj;

    /**
     * 分支机构分摊所得税额
     */
    private java.lang.String _fzjgFtSdse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbZjgxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fzjgFtSdse'. The field
     * 'fzjgFtSdse' has the following description: 分支机构分摊所得税额
     * 
     * @return the value of field 'FzjgFtSdse'.
     */
    public java.lang.String getFzjgFtSdse(
    ) {
        return this._fzjgFtSdse;
    }

    /**
     * Returns the value of field 'sxysGzze'. The field 'sxysGzze'
     * has the following description: 三项因素—工资总额
     * 
     * @return the value of field 'SxysGzze'.
     */
    public java.lang.String getSxysGzze(
    ) {
        return this._sxysGzze;
    }

    /**
     * Returns the value of field 'sxysHj'. The field 'sxysHj' has
     * the following description: 三项因素—合计
     * 
     * @return the value of field 'SxysHj'.
     */
    public java.lang.String getSxysHj(
    ) {
        return this._sxysHj;
    }

    /**
     * Returns the value of field 'sxysSrze'. The field 'sxysSrze'
     * has the following description: 三项因素—收入总额
     * 
     * @return the value of field 'SxysSrze'.
     */
    public java.lang.String getSxysSrze(
    ) {
        return this._sxysSrze;
    }

    /**
     * Returns the value of field 'sxysZcze'. The field 'sxysZcze'
     * has the following description: 三项因素—资产总额
     * 
     * @return the value of field 'SxysZcze'.
     */
    public java.lang.String getSxysZcze(
    ) {
        return this._sxysZcze;
    }

    /**
     * Returns the value of field 'zjgMc'. The field 'zjgMc' has
     * the following description: 总机构名称
     * 
     * @return the value of field 'ZjgMc'.
     */
    public java.lang.String getZjgMc(
    ) {
        return this._zjgMc;
    }

    /**
     * Returns the value of field 'zjgNsrsbh'. The field
     * 'zjgNsrsbh' has the following description: 总机构纳税人识别号
     * 
     * @return the value of field 'ZjgNsrsbh'.
     */
    public java.lang.String getZjgNsrsbh(
    ) {
        return this._zjgNsrsbh;
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
     * Sets the value of field 'fzjgFtSdse'. The field 'fzjgFtSdse'
     * has the following description: 分支机构分摊所得税额
     * 
     * @param fzjgFtSdse the value of field 'fzjgFtSdse'.
     */
    public void setFzjgFtSdse(
            final java.lang.String fzjgFtSdse) {
        this._fzjgFtSdse = fzjgFtSdse;
    }

    /**
     * Sets the value of field 'sxysGzze'. The field 'sxysGzze' has
     * the following description: 三项因素—工资总额
     * 
     * @param sxysGzze the value of field 'sxysGzze'.
     */
    public void setSxysGzze(
            final java.lang.String sxysGzze) {
        this._sxysGzze = sxysGzze;
    }

    /**
     * Sets the value of field 'sxysHj'. The field 'sxysHj' has the
     * following description: 三项因素—合计
     * 
     * @param sxysHj the value of field 'sxysHj'.
     */
    public void setSxysHj(
            final java.lang.String sxysHj) {
        this._sxysHj = sxysHj;
    }

    /**
     * Sets the value of field 'sxysSrze'. The field 'sxysSrze' has
     * the following description: 三项因素—收入总额
     * 
     * @param sxysSrze the value of field 'sxysSrze'.
     */
    public void setSxysSrze(
            final java.lang.String sxysSrze) {
        this._sxysSrze = sxysSrze;
    }

    /**
     * Sets the value of field 'sxysZcze'. The field 'sxysZcze' has
     * the following description: 三项因素—资产总额
     * 
     * @param sxysZcze the value of field 'sxysZcze'.
     */
    public void setSxysZcze(
            final java.lang.String sxysZcze) {
        this._sxysZcze = sxysZcze;
    }

    /**
     * Sets the value of field 'zjgMc'. The field 'zjgMc' has the
     * following description: 总机构名称
     * 
     * @param zjgMc the value of field 'zjgMc'.
     */
    public void setZjgMc(
            final java.lang.String zjgMc) {
        this._zjgMc = zjgMc;
    }

    /**
     * Sets the value of field 'zjgNsrsbh'. The field 'zjgNsrsbh'
     * has the following description: 总机构纳税人识别号
     * 
     * @param zjgNsrsbh the value of field 'zjgNsrsbh'.
     */
    public void setZjgNsrsbh(
            final java.lang.String zjgNsrsbh) {
        this._zjgNsrsbh = zjgNsrsbh;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbZjgxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbZjgxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbZjgxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbZjgxx.class, reader);
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
