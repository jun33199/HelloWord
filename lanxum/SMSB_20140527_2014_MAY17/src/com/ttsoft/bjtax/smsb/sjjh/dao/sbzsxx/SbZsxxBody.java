/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class SbZsxxBody.
 * 
 * @version $Revision$ $Date$
 */
public class SbZsxxBody implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 调账类型代码
     */
    private java.lang.String _tzlxDm;

    /**
     * 调账日期
     */
    private java.lang.String _tzrq;

    /**
     * 税额
     */
    private java.lang.String _se;

    /**
     * 应征发生日期
     */
    private java.lang.String _yzfsrq;

    /**
     * 缴款期限
     */
    private java.lang.String _jkqx;

    /**
     * 开票日期
     */
    private java.lang.String _kprq;

    /**
     * 入库日期
     */
    private java.lang.String _rkrq;

    /**
     * 入库销号日期
     */
    private java.lang.String _rkxhrq;

    /**
     * 预算科目代码
     */
    private java.lang.String _yskm_dm;

    /**
     * 预算分配比例中央
     */
    private java.lang.String _ysfpblZy;

    /**
     * 预算分配比例省市
     */
    private java.lang.String _ysfpblSs;

    /**
     * 预算分配比例地市
     */
    private java.lang.String _ysfpblDs;

    /**
     * 预算分配比例县区
     */
    private java.lang.String _ysfpblXq;

    /**
     * 预算分配比例乡镇
     */
    private java.lang.String _ysfpblXz;

    /**
     * 预算分配比例乡村
     */
    private java.lang.String _ysfpblXc;

    /**
     * 征收机关代码
     */
    private java.lang.String _zsjgDm;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbZsxxBody() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'jkqx'. The field 'jkqx' has the
     * following description: 缴款期限
     * 
     * @return the value of field 'Jkqx'.
     */
    public java.lang.String getJkqx(
    ) {
        return this._jkqx;
    }

    /**
     * Returns the value of field 'kprq'. The field 'kprq' has the
     * following description: 开票日期
     * 
     * @return the value of field 'Kprq'.
     */
    public java.lang.String getKprq(
    ) {
        return this._kprq;
    }

    /**
     * Returns the value of field 'rkrq'. The field 'rkrq' has the
     * following description: 入库日期
     * 
     * @return the value of field 'Rkrq'.
     */
    public java.lang.String getRkrq(
    ) {
        return this._rkrq;
    }

    /**
     * Returns the value of field 'rkxhrq'. The field 'rkxhrq' has
     * the following description: 入库销号日期
     * 
     * @return the value of field 'Rkxhrq'.
     */
    public java.lang.String getRkxhrq(
    ) {
        return this._rkxhrq;
    }

    /**
     * Returns the value of field 'se'. The field 'se' has the
     * following description: 税额
     * 
     * @return the value of field 'Se'.
     */
    public java.lang.String getSe(
    ) {
        return this._se;
    }

    /**
     * Returns the value of field 'tzlxDm'. The field 'tzlxDm' has
     * the following description: 调账类型代码
     * 
     * @return the value of field 'TzlxDm'.
     */
    public java.lang.String getTzlxDm(
    ) {
        return this._tzlxDm;
    }

    /**
     * Returns the value of field 'tzrq'. The field 'tzrq' has the
     * following description: 调账日期
     * 
     * @return the value of field 'Tzrq'.
     */
    public java.lang.String getTzrq(
    ) {
        return this._tzrq;
    }

    /**
     * Returns the value of field 'ysfpblDs'. The field 'ysfpblDs'
     * has the following description: 预算分配比例地市
     * 
     * @return the value of field 'YsfpblDs'.
     */
    public java.lang.String getYsfpblDs(
    ) {
        return this._ysfpblDs;
    }

    /**
     * Returns the value of field 'ysfpblSs'. The field 'ysfpblSs'
     * has the following description: 预算分配比例省市
     * 
     * @return the value of field 'YsfpblSs'.
     */
    public java.lang.String getYsfpblSs(
    ) {
        return this._ysfpblSs;
    }

    /**
     * Returns the value of field 'ysfpblXc'. The field 'ysfpblXc'
     * has the following description: 预算分配比例乡村
     * 
     * @return the value of field 'YsfpblXc'.
     */
    public java.lang.String getYsfpblXc(
    ) {
        return this._ysfpblXc;
    }

    /**
     * Returns the value of field 'ysfpblXq'. The field 'ysfpblXq'
     * has the following description: 预算分配比例县区
     * 
     * @return the value of field 'YsfpblXq'.
     */
    public java.lang.String getYsfpblXq(
    ) {
        return this._ysfpblXq;
    }

    /**
     * Returns the value of field 'ysfpblXz'. The field 'ysfpblXz'
     * has the following description: 预算分配比例乡镇
     * 
     * @return the value of field 'YsfpblXz'.
     */
    public java.lang.String getYsfpblXz(
    ) {
        return this._ysfpblXz;
    }

    /**
     * Returns the value of field 'ysfpblZy'. The field 'ysfpblZy'
     * has the following description: 预算分配比例中央
     * 
     * @return the value of field 'YsfpblZy'.
     */
    public java.lang.String getYsfpblZy(
    ) {
        return this._ysfpblZy;
    }

    /**
     * Returns the value of field 'yskm_dm'. The field 'yskm_dm'
     * has the following description: 预算科目代码
     * 
     * @return the value of field 'Yskm_dm'.
     */
    public java.lang.String getYskm_dm(
    ) {
        return this._yskm_dm;
    }

    /**
     * Returns the value of field 'yzfsrq'. The field 'yzfsrq' has
     * the following description: 应征发生日期
     * 
     * @return the value of field 'Yzfsrq'.
     */
    public java.lang.String getYzfsrq(
    ) {
        return this._yzfsrq;
    }

    /**
     * Returns the value of field 'zsjgDm'. The field 'zsjgDm' has
     * the following description: 征收机关代码
     * 
     * @return the value of field 'ZsjgDm'.
     */
    public java.lang.String getZsjgDm(
    ) {
        return this._zsjgDm;
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
     * Sets the value of field 'jkqx'. The field 'jkqx' has the
     * following description: 缴款期限
     * 
     * @param jkqx the value of field 'jkqx'.
     */
    public void setJkqx(
            final java.lang.String jkqx) {
        this._jkqx = jkqx;
    }

    /**
     * Sets the value of field 'kprq'. The field 'kprq' has the
     * following description: 开票日期
     * 
     * @param kprq the value of field 'kprq'.
     */
    public void setKprq(
            final java.lang.String kprq) {
        this._kprq = kprq;
    }

    /**
     * Sets the value of field 'rkrq'. The field 'rkrq' has the
     * following description: 入库日期
     * 
     * @param rkrq the value of field 'rkrq'.
     */
    public void setRkrq(
            final java.lang.String rkrq) {
        this._rkrq = rkrq;
    }

    /**
     * Sets the value of field 'rkxhrq'. The field 'rkxhrq' has the
     * following description: 入库销号日期
     * 
     * @param rkxhrq the value of field 'rkxhrq'.
     */
    public void setRkxhrq(
            final java.lang.String rkxhrq) {
        this._rkxhrq = rkxhrq;
    }

    /**
     * Sets the value of field 'se'. The field 'se' has the
     * following description: 税额
     * 
     * @param se the value of field 'se'.
     */
    public void setSe(
            final java.lang.String se) {
        this._se = se;
    }

    /**
     * Sets the value of field 'tzlxDm'. The field 'tzlxDm' has the
     * following description: 调账类型代码
     * 
     * @param tzlxDm the value of field 'tzlxDm'.
     */
    public void setTzlxDm(
            final java.lang.String tzlxDm) {
        this._tzlxDm = tzlxDm;
    }

    /**
     * Sets the value of field 'tzrq'. The field 'tzrq' has the
     * following description: 调账日期
     * 
     * @param tzrq the value of field 'tzrq'.
     */
    public void setTzrq(
            final java.lang.String tzrq) {
        this._tzrq = tzrq;
    }

    /**
     * Sets the value of field 'ysfpblDs'. The field 'ysfpblDs' has
     * the following description: 预算分配比例地市
     * 
     * @param ysfpblDs the value of field 'ysfpblDs'.
     */
    public void setYsfpblDs(
            final java.lang.String ysfpblDs) {
        this._ysfpblDs = ysfpblDs;
    }

    /**
     * Sets the value of field 'ysfpblSs'. The field 'ysfpblSs' has
     * the following description: 预算分配比例省市
     * 
     * @param ysfpblSs the value of field 'ysfpblSs'.
     */
    public void setYsfpblSs(
            final java.lang.String ysfpblSs) {
        this._ysfpblSs = ysfpblSs;
    }

    /**
     * Sets the value of field 'ysfpblXc'. The field 'ysfpblXc' has
     * the following description: 预算分配比例乡村
     * 
     * @param ysfpblXc the value of field 'ysfpblXc'.
     */
    public void setYsfpblXc(
            final java.lang.String ysfpblXc) {
        this._ysfpblXc = ysfpblXc;
    }

    /**
     * Sets the value of field 'ysfpblXq'. The field 'ysfpblXq' has
     * the following description: 预算分配比例县区
     * 
     * @param ysfpblXq the value of field 'ysfpblXq'.
     */
    public void setYsfpblXq(
            final java.lang.String ysfpblXq) {
        this._ysfpblXq = ysfpblXq;
    }

    /**
     * Sets the value of field 'ysfpblXz'. The field 'ysfpblXz' has
     * the following description: 预算分配比例乡镇
     * 
     * @param ysfpblXz the value of field 'ysfpblXz'.
     */
    public void setYsfpblXz(
            final java.lang.String ysfpblXz) {
        this._ysfpblXz = ysfpblXz;
    }

    /**
     * Sets the value of field 'ysfpblZy'. The field 'ysfpblZy' has
     * the following description: 预算分配比例中央
     * 
     * @param ysfpblZy the value of field 'ysfpblZy'.
     */
    public void setYsfpblZy(
            final java.lang.String ysfpblZy) {
        this._ysfpblZy = ysfpblZy;
    }

    /**
     * Sets the value of field 'yskm_dm'. The field 'yskm_dm' has
     * the following description: 预算科目代码
     * 
     * @param yskm_dm the value of field 'yskm_dm'.
     */
    public void setYskm_dm(
            final java.lang.String yskm_dm) {
        this._yskm_dm = yskm_dm;
    }

    /**
     * Sets the value of field 'yzfsrq'. The field 'yzfsrq' has the
     * following description: 应征发生日期
     * 
     * @param yzfsrq the value of field 'yzfsrq'.
     */
    public void setYzfsrq(
            final java.lang.String yzfsrq) {
        this._yzfsrq = yzfsrq;
    }

    /**
     * Sets the value of field 'zsjgDm'. The field 'zsjgDm' has the
     * following description: 征收机关代码
     * 
     * @param zsjgDm the value of field 'zsjgDm'.
     */
    public void setZsjgDm(
            final java.lang.String zsjgDm) {
        this._zsjgDm = zsjgDm;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxBody
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxBody unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxBody) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxBody.class, reader);
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
