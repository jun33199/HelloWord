/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 纳税人基本信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwNsrxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 纳税人名称
     */
    private java.lang.String _nsrmc;

    /**
     * 法定代表人
     */
    private java.lang.String _fddbrmc;

    /**
     * 法人证件类型代码
     */
    private java.lang.String _frzjlxDm;

    /**
     * 证件号码
     */
    private java.lang.String _zjhm;

    /**
     * 生产经营地址
     */
    private java.lang.String _scjydz;

    /**
     * 办税员名称
     */
    private java.lang.String _bsrmc;

    /**
     * 电话号码
     */
    private java.lang.String _dhhm;

    /**
     * 隶属关系
     */
    private java.lang.String _lsgxDm;

    /**
     * 行业
     */
    private java.lang.String _hyDm;

    /**
     * 登记注册类型
     */
    private java.lang.String _djzclxDm;

    /**
     * 核定登记日期
     */
    private java.lang.String _hzdjrq;

    /**
     * 企业会计制度
     */
    private java.lang.String _qykjzdDm;

    /**
     * 税务登记表类型代码
     */
    private java.lang.String _swdjblxDm;

    /**
     * 税务登记证类型代码
     */
    private java.lang.String _swdjzlxDm;

    /**
     * 纳税人状态代码
     */
    private java.lang.String _nsrztDm;

    /**
     * 主管税务人员代码
     */
    private java.lang.String _zgswryDm;

    /**
     * 街道乡镇代码
     */
    private java.lang.String _jdxzDm;

    /**
     * 增值税企业类型代码
     */
    private java.lang.String _zzsnsrlxDm;

    /**
     * 工商业类别代码
     */
    private java.lang.String _gsylbDm;

    /**
     * 纳税人信誉等级代码
     */
    private java.lang.String _nsrxydjDm;

    /**
     * 企业规模代码
     */
    private java.lang.String _qygmDm;

    /**
     * 产业类型代码
     */
    private java.lang.String _cylxDm;

    /**
     * 行政区划预算分配比例代码
     */
    private java.lang.String _xzqhYsfpblDm;

    /**
     * 海关代码
     */
    private java.lang.String _hgdm;

    /**
     * 总机构标志Y：总机构，N：非总机构
     */
    private java.lang.String _zjgBz;

    /**
     * 国税主管税务机关（局）
     */
    private java.lang.String _gszgswjgJDm;

    /**
     * 临时税务登记类型代码
     */
    private java.lang.String _lsswdjlxDm;

    /**
     * 单位性质代码
     */
    private java.lang.String _dwxzDm;

    /**
     * 共管户标志。Y：共管户，N：非共管户
     */
    private java.lang.String _gghbz;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwNsrxx() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bsrmc'. The field 'bsrmc' has
     * the following description: 办税员名称
     * 
     * @return the value of field 'Bsrmc'.
     */
    public java.lang.String getBsrmc(
    ) {
        return this._bsrmc;
    }

    /**
     * Returns the value of field 'cylxDm'. The field 'cylxDm' has
     * the following description: 产业类型代码
     * 
     * @return the value of field 'CylxDm'.
     */
    public java.lang.String getCylxDm(
    ) {
        return this._cylxDm;
    }

    /**
     * Returns the value of field 'dhhm'. The field 'dhhm' has the
     * following description: 电话号码
     * 
     * @return the value of field 'Dhhm'.
     */
    public java.lang.String getDhhm(
    ) {
        return this._dhhm;
    }

    /**
     * Returns the value of field 'djzclxDm'. The field 'djzclxDm'
     * has the following description: 登记注册类型
     * 
     * @return the value of field 'DjzclxDm'.
     */
    public java.lang.String getDjzclxDm(
    ) {
        return this._djzclxDm;
    }

    /**
     * Returns the value of field 'dwxzDm'. The field 'dwxzDm' has
     * the following description: 单位性质代码
     * 
     * @return the value of field 'DwxzDm'.
     */
    public java.lang.String getDwxzDm(
    ) {
        return this._dwxzDm;
    }

    /**
     * Returns the value of field 'fddbrmc'. The field 'fddbrmc'
     * has the following description: 法定代表人
     * 
     * @return the value of field 'Fddbrmc'.
     */
    public java.lang.String getFddbrmc(
    ) {
        return this._fddbrmc;
    }

    /**
     * Returns the value of field 'frzjlxDm'. The field 'frzjlxDm'
     * has the following description: 法人证件类型代码
     * 
     * @return the value of field 'FrzjlxDm'.
     */
    public java.lang.String getFrzjlxDm(
    ) {
        return this._frzjlxDm;
    }

    /**
     * Returns the value of field 'gghbz'. The field 'gghbz' has
     * the following description: 共管户标志。Y：共管户，N：非共管户
     * 
     * @return the value of field 'Gghbz'.
     */
    public java.lang.String getGghbz(
    ) {
        return this._gghbz;
    }

    /**
     * Returns the value of field 'gsylbDm'. The field 'gsylbDm'
     * has the following description: 工商业类别代码
     * 
     * @return the value of field 'GsylbDm'.
     */
    public java.lang.String getGsylbDm(
    ) {
        return this._gsylbDm;
    }

    /**
     * Returns the value of field 'gszgswjgJDm'. The field
     * 'gszgswjgJDm' has the following description: 国税主管税务机关（局）
     * 
     * @return the value of field 'GszgswjgJDm'.
     */
    public java.lang.String getGszgswjgJDm(
    ) {
        return this._gszgswjgJDm;
    }

    /**
     * Returns the value of field 'hgdm'. The field 'hgdm' has the
     * following description: 海关代码
     * 
     * @return the value of field 'Hgdm'.
     */
    public java.lang.String getHgdm(
    ) {
        return this._hgdm;
    }

    /**
     * Returns the value of field 'hyDm'. The field 'hyDm' has the
     * following description: 行业
     * 
     * @return the value of field 'HyDm'.
     */
    public java.lang.String getHyDm(
    ) {
        return this._hyDm;
    }

    /**
     * Returns the value of field 'hzdjrq'. The field 'hzdjrq' has
     * the following description: 核定登记日期
     * 
     * @return the value of field 'Hzdjrq'.
     */
    public java.lang.String getHzdjrq(
    ) {
        return this._hzdjrq;
    }

    /**
     * Returns the value of field 'jdxzDm'. The field 'jdxzDm' has
     * the following description: 街道乡镇代码
     * 
     * @return the value of field 'JdxzDm'.
     */
    public java.lang.String getJdxzDm(
    ) {
        return this._jdxzDm;
    }

    /**
     * Returns the value of field 'lsgxDm'. The field 'lsgxDm' has
     * the following description: 隶属关系
     * 
     * @return the value of field 'LsgxDm'.
     */
    public java.lang.String getLsgxDm(
    ) {
        return this._lsgxDm;
    }

    /**
     * Returns the value of field 'lsswdjlxDm'. The field
     * 'lsswdjlxDm' has the following description: 临时税务登记类型代码
     * 
     * @return the value of field 'LsswdjlxDm'.
     */
    public java.lang.String getLsswdjlxDm(
    ) {
        return this._lsswdjlxDm;
    }

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
     * Returns the value of field 'nsrxydjDm'. The field
     * 'nsrxydjDm' has the following description: 纳税人信誉等级代码
     * 
     * @return the value of field 'NsrxydjDm'.
     */
    public java.lang.String getNsrxydjDm(
    ) {
        return this._nsrxydjDm;
    }

    /**
     * Returns the value of field 'nsrztDm'. The field 'nsrztDm'
     * has the following description: 纳税人状态代码
     * 
     * @return the value of field 'NsrztDm'.
     */
    public java.lang.String getNsrztDm(
    ) {
        return this._nsrztDm;
    }

    /**
     * Returns the value of field 'qygmDm'. The field 'qygmDm' has
     * the following description: 企业规模代码
     * 
     * @return the value of field 'QygmDm'.
     */
    public java.lang.String getQygmDm(
    ) {
        return this._qygmDm;
    }

    /**
     * Returns the value of field 'qykjzdDm'. The field 'qykjzdDm'
     * has the following description: 企业会计制度
     * 
     * @return the value of field 'QykjzdDm'.
     */
    public java.lang.String getQykjzdDm(
    ) {
        return this._qykjzdDm;
    }

    /**
     * Returns the value of field 'scjydz'. The field 'scjydz' has
     * the following description: 生产经营地址
     * 
     * @return the value of field 'Scjydz'.
     */
    public java.lang.String getScjydz(
    ) {
        return this._scjydz;
    }

    /**
     * Returns the value of field 'swdjblxDm'. The field
     * 'swdjblxDm' has the following description: 税务登记表类型代码
     * 
     * @return the value of field 'SwdjblxDm'.
     */
    public java.lang.String getSwdjblxDm(
    ) {
        return this._swdjblxDm;
    }

    /**
     * Returns the value of field 'swdjzlxDm'. The field
     * 'swdjzlxDm' has the following description: 税务登记证类型代码
     * 
     * @return the value of field 'SwdjzlxDm'.
     */
    public java.lang.String getSwdjzlxDm(
    ) {
        return this._swdjzlxDm;
    }

    /**
     * Returns the value of field 'xzqhYsfpblDm'. The field
     * 'xzqhYsfpblDm' has the following description: 行政区划预算分配比例代码
     * 
     * @return the value of field 'XzqhYsfpblDm'.
     */
    public java.lang.String getXzqhYsfpblDm(
    ) {
        return this._xzqhYsfpblDm;
    }

    /**
     * Returns the value of field 'zgswryDm'. The field 'zgswryDm'
     * has the following description: 主管税务人员代码
     * 
     * @return the value of field 'ZgswryDm'.
     */
    public java.lang.String getZgswryDm(
    ) {
        return this._zgswryDm;
    }

    /**
     * Returns the value of field 'zjgBz'. The field 'zjgBz' has
     * the following description: 总机构标志Y：总机构，N：非总机构
     * 
     * @return the value of field 'ZjgBz'.
     */
    public java.lang.String getZjgBz(
    ) {
        return this._zjgBz;
    }

    /**
     * Returns the value of field 'zjhm'. The field 'zjhm' has the
     * following description: 证件号码
     * 
     * @return the value of field 'Zjhm'.
     */
    public java.lang.String getZjhm(
    ) {
        return this._zjhm;
    }

    /**
     * Returns the value of field 'zzsnsrlxDm'. The field
     * 'zzsnsrlxDm' has the following description: 增值税企业类型代码
     * 
     * @return the value of field 'ZzsnsrlxDm'.
     */
    public java.lang.String getZzsnsrlxDm(
    ) {
        return this._zzsnsrlxDm;
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
     * Sets the value of field 'bsrmc'. The field 'bsrmc' has the
     * following description: 办税员名称
     * 
     * @param bsrmc the value of field 'bsrmc'.
     */
    public void setBsrmc(
            final java.lang.String bsrmc) {
        this._bsrmc = bsrmc;
    }

    /**
     * Sets the value of field 'cylxDm'. The field 'cylxDm' has the
     * following description: 产业类型代码
     * 
     * @param cylxDm the value of field 'cylxDm'.
     */
    public void setCylxDm(
            final java.lang.String cylxDm) {
        this._cylxDm = cylxDm;
    }

    /**
     * Sets the value of field 'dhhm'. The field 'dhhm' has the
     * following description: 电话号码
     * 
     * @param dhhm the value of field 'dhhm'.
     */
    public void setDhhm(
            final java.lang.String dhhm) {
        this._dhhm = dhhm;
    }

    /**
     * Sets the value of field 'djzclxDm'. The field 'djzclxDm' has
     * the following description: 登记注册类型
     * 
     * @param djzclxDm the value of field 'djzclxDm'.
     */
    public void setDjzclxDm(
            final java.lang.String djzclxDm) {
        this._djzclxDm = djzclxDm;
    }

    /**
     * Sets the value of field 'dwxzDm'. The field 'dwxzDm' has the
     * following description: 单位性质代码
     * 
     * @param dwxzDm the value of field 'dwxzDm'.
     */
    public void setDwxzDm(
            final java.lang.String dwxzDm) {
        this._dwxzDm = dwxzDm;
    }

    /**
     * Sets the value of field 'fddbrmc'. The field 'fddbrmc' has
     * the following description: 法定代表人
     * 
     * @param fddbrmc the value of field 'fddbrmc'.
     */
    public void setFddbrmc(
            final java.lang.String fddbrmc) {
        this._fddbrmc = fddbrmc;
    }

    /**
     * Sets the value of field 'frzjlxDm'. The field 'frzjlxDm' has
     * the following description: 法人证件类型代码
     * 
     * @param frzjlxDm the value of field 'frzjlxDm'.
     */
    public void setFrzjlxDm(
            final java.lang.String frzjlxDm) {
        this._frzjlxDm = frzjlxDm;
    }

    /**
     * Sets the value of field 'gghbz'. The field 'gghbz' has the
     * following description: 共管户标志。Y：共管户，N：非共管户
     * 
     * @param gghbz the value of field 'gghbz'.
     */
    public void setGghbz(
            final java.lang.String gghbz) {
        this._gghbz = gghbz;
    }

    /**
     * Sets the value of field 'gsylbDm'. The field 'gsylbDm' has
     * the following description: 工商业类别代码
     * 
     * @param gsylbDm the value of field 'gsylbDm'.
     */
    public void setGsylbDm(
            final java.lang.String gsylbDm) {
        this._gsylbDm = gsylbDm;
    }

    /**
     * Sets the value of field 'gszgswjgJDm'. The field
     * 'gszgswjgJDm' has the following description: 国税主管税务机关（局）
     * 
     * @param gszgswjgJDm the value of field 'gszgswjgJDm'.
     */
    public void setGszgswjgJDm(
            final java.lang.String gszgswjgJDm) {
        this._gszgswjgJDm = gszgswjgJDm;
    }

    /**
     * Sets the value of field 'hgdm'. The field 'hgdm' has the
     * following description: 海关代码
     * 
     * @param hgdm the value of field 'hgdm'.
     */
    public void setHgdm(
            final java.lang.String hgdm) {
        this._hgdm = hgdm;
    }

    /**
     * Sets the value of field 'hyDm'. The field 'hyDm' has the
     * following description: 行业
     * 
     * @param hyDm the value of field 'hyDm'.
     */
    public void setHyDm(
            final java.lang.String hyDm) {
        this._hyDm = hyDm;
    }

    /**
     * Sets the value of field 'hzdjrq'. The field 'hzdjrq' has the
     * following description: 核定登记日期
     * 
     * @param hzdjrq the value of field 'hzdjrq'.
     */
    public void setHzdjrq(
            final java.lang.String hzdjrq) {
        this._hzdjrq = hzdjrq;
    }

    /**
     * Sets the value of field 'jdxzDm'. The field 'jdxzDm' has the
     * following description: 街道乡镇代码
     * 
     * @param jdxzDm the value of field 'jdxzDm'.
     */
    public void setJdxzDm(
            final java.lang.String jdxzDm) {
        this._jdxzDm = jdxzDm;
    }

    /**
     * Sets the value of field 'lsgxDm'. The field 'lsgxDm' has the
     * following description: 隶属关系
     * 
     * @param lsgxDm the value of field 'lsgxDm'.
     */
    public void setLsgxDm(
            final java.lang.String lsgxDm) {
        this._lsgxDm = lsgxDm;
    }

    /**
     * Sets the value of field 'lsswdjlxDm'. The field 'lsswdjlxDm'
     * has the following description: 临时税务登记类型代码
     * 
     * @param lsswdjlxDm the value of field 'lsswdjlxDm'.
     */
    public void setLsswdjlxDm(
            final java.lang.String lsswdjlxDm) {
        this._lsswdjlxDm = lsswdjlxDm;
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
     * Sets the value of field 'nsrxydjDm'. The field 'nsrxydjDm'
     * has the following description: 纳税人信誉等级代码
     * 
     * @param nsrxydjDm the value of field 'nsrxydjDm'.
     */
    public void setNsrxydjDm(
            final java.lang.String nsrxydjDm) {
        this._nsrxydjDm = nsrxydjDm;
    }

    /**
     * Sets the value of field 'nsrztDm'. The field 'nsrztDm' has
     * the following description: 纳税人状态代码
     * 
     * @param nsrztDm the value of field 'nsrztDm'.
     */
    public void setNsrztDm(
            final java.lang.String nsrztDm) {
        this._nsrztDm = nsrztDm;
    }

    /**
     * Sets the value of field 'qygmDm'. The field 'qygmDm' has the
     * following description: 企业规模代码
     * 
     * @param qygmDm the value of field 'qygmDm'.
     */
    public void setQygmDm(
            final java.lang.String qygmDm) {
        this._qygmDm = qygmDm;
    }

    /**
     * Sets the value of field 'qykjzdDm'. The field 'qykjzdDm' has
     * the following description: 企业会计制度
     * 
     * @param qykjzdDm the value of field 'qykjzdDm'.
     */
    public void setQykjzdDm(
            final java.lang.String qykjzdDm) {
        this._qykjzdDm = qykjzdDm;
    }

    /**
     * Sets the value of field 'scjydz'. The field 'scjydz' has the
     * following description: 生产经营地址
     * 
     * @param scjydz the value of field 'scjydz'.
     */
    public void setScjydz(
            final java.lang.String scjydz) {
        this._scjydz = scjydz;
    }

    /**
     * Sets the value of field 'swdjblxDm'. The field 'swdjblxDm'
     * has the following description: 税务登记表类型代码
     * 
     * @param swdjblxDm the value of field 'swdjblxDm'.
     */
    public void setSwdjblxDm(
            final java.lang.String swdjblxDm) {
        this._swdjblxDm = swdjblxDm;
    }

    /**
     * Sets the value of field 'swdjzlxDm'. The field 'swdjzlxDm'
     * has the following description: 税务登记证类型代码
     * 
     * @param swdjzlxDm the value of field 'swdjzlxDm'.
     */
    public void setSwdjzlxDm(
            final java.lang.String swdjzlxDm) {
        this._swdjzlxDm = swdjzlxDm;
    }

    /**
     * Sets the value of field 'xzqhYsfpblDm'. The field
     * 'xzqhYsfpblDm' has the following description: 行政区划预算分配比例代码
     * 
     * @param xzqhYsfpblDm the value of field 'xzqhYsfpblDm'.
     */
    public void setXzqhYsfpblDm(
            final java.lang.String xzqhYsfpblDm) {
        this._xzqhYsfpblDm = xzqhYsfpblDm;
    }

    /**
     * Sets the value of field 'zgswryDm'. The field 'zgswryDm' has
     * the following description: 主管税务人员代码
     * 
     * @param zgswryDm the value of field 'zgswryDm'.
     */
    public void setZgswryDm(
            final java.lang.String zgswryDm) {
        this._zgswryDm = zgswryDm;
    }

    /**
     * Sets the value of field 'zjgBz'. The field 'zjgBz' has the
     * following description: 总机构标志Y：总机构，N：非总机构
     * 
     * @param zjgBz the value of field 'zjgBz'.
     */
    public void setZjgBz(
            final java.lang.String zjgBz) {
        this._zjgBz = zjgBz;
    }

    /**
     * Sets the value of field 'zjhm'. The field 'zjhm' has the
     * following description: 证件号码
     * 
     * @param zjhm the value of field 'zjhm'.
     */
    public void setZjhm(
            final java.lang.String zjhm) {
        this._zjhm = zjhm;
    }

    /**
     * Sets the value of field 'zzsnsrlxDm'. The field 'zzsnsrlxDm'
     * has the following description: 增值税企业类型代码
     * 
     * @param zzsnsrlxDm the value of field 'zzsnsrlxDm'.
     */
    public void setZzsnsrlxDm(
            final java.lang.String zzsnsrlxDm) {
        this._zzsnsrlxDm = zzsnsrlxDm;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwNsrxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwNsrxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwNsrxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwNsrxx.class, reader);
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
