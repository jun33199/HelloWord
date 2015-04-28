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
 * 分支机构信息
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbFzjgxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 分支机构纳税人识别号
     */
    private java.lang.String _fzjgNsrsbh;

    /**
     * 分支机构名称
     */
    private java.lang.String _fzjgMc;

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
     * 分配比例
     */
    private java.lang.String _fpbl;

    /**
     * 分配税额
     */
    private java.lang.String _fpse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbFzjgxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fpbl'. The field 'fpbl' has the
     * following description: 分配比例
     * 
     * @return the value of field 'Fpbl'.
     */
    public java.lang.String getFpbl(
    ) {
        return this._fpbl;
    }

    /**
     * Returns the value of field 'fpse'. The field 'fpse' has the
     * following description: 分配税额
     * 
     * @return the value of field 'Fpse'.
     */
    public java.lang.String getFpse(
    ) {
        return this._fpse;
    }

    /**
     * Returns the value of field 'fzjgMc'. The field 'fzjgMc' has
     * the following description: 分支机构名称
     * 
     * @return the value of field 'FzjgMc'.
     */
    public java.lang.String getFzjgMc(
    ) {
        return this._fzjgMc;
    }

    /**
     * Returns the value of field 'fzjgNsrsbh'. The field
     * 'fzjgNsrsbh' has the following description: 分支机构纳税人识别号
     * 
     * @return the value of field 'FzjgNsrsbh'.
     */
    public java.lang.String getFzjgNsrsbh(
    ) {
        return this._fzjgNsrsbh;
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
     * Sets the value of field 'fpbl'. The field 'fpbl' has the
     * following description: 分配比例
     * 
     * @param fpbl the value of field 'fpbl'.
     */
    public void setFpbl(
            final java.lang.String fpbl) {
        this._fpbl = fpbl;
    }

    /**
     * Sets the value of field 'fpse'. The field 'fpse' has the
     * following description: 分配税额
     * 
     * @param fpse the value of field 'fpse'.
     */
    public void setFpse(
            final java.lang.String fpse) {
        this._fpse = fpse;
    }

    /**
     * Sets the value of field 'fzjgMc'. The field 'fzjgMc' has the
     * following description: 分支机构名称
     * 
     * @param fzjgMc the value of field 'fzjgMc'.
     */
    public void setFzjgMc(
            final java.lang.String fzjgMc) {
        this._fzjgMc = fzjgMc;
    }

    /**
     * Sets the value of field 'fzjgNsrsbh'. The field 'fzjgNsrsbh'
     * has the following description: 分支机构纳税人识别号
     * 
     * @param fzjgNsrsbh the value of field 'fzjgNsrsbh'.
     */
    public void setFzjgNsrsbh(
            final java.lang.String fzjgNsrsbh) {
        this._fzjgNsrsbh = fzjgNsrsbh;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbFzjgxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbFzjgxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbFzjgxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbFzjgxx.class, reader);
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
