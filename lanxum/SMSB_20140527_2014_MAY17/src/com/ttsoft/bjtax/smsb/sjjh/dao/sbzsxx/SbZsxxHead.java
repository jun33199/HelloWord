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
 * Class SbZsxxHead.
 * 
 * @version $Revision$ $Date$
 */
public class SbZsxxHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * �������(��ʾ������Ϣ��Ψһ���)
     */
    private java.lang.String _zsxh;

    /**
     * Ӧ��ƾ֤���(��ʾ������Ϣ���걨�����ݶ�Ӧ)
     */
    private java.lang.String _yzpzxh;

    /**
     * ��˰��ʶ���
     */
    private java.lang.String _nsrsbh;

    /**
     * ����ʱ��
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Sssq _sssq;

    /**
     * ��˰������˰���������
     */
    private java.lang.String _nsrSwjgDm;

    /**
     * ¼������
     */
    private java.lang.String _lrrq;

    /**
     * �޸�����
     */
    private java.lang.String _xgrq;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbZsxxHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'lrrq'. The field 'lrrq' has the
     * following description: ¼������
     * 
     * @return the value of field 'Lrrq'.
     */
    public java.lang.String getLrrq(
    ) {
        return this._lrrq;
    }

    /**
     * Returns the value of field 'nsrSwjgDm'. The field
     * 'nsrSwjgDm' has the following description: ��˰������˰���������
     * 
     * @return the value of field 'NsrSwjgDm'.
     */
    public java.lang.String getNsrSwjgDm(
    ) {
        return this._nsrSwjgDm;
    }

    /**
     * Returns the value of field 'nsrsbh'. The field 'nsrsbh' has
     * the following description: ��˰��ʶ���
     * 
     * @return the value of field 'Nsrsbh'.
     */
    public java.lang.String getNsrsbh(
    ) {
        return this._nsrsbh;
    }

    /**
     * Returns the value of field 'sssq'. The field 'sssq' has the
     * following description: ����ʱ��
     * 
     * @return the value of field 'Sssq'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Sssq getSssq(
    ) {
        return this._sssq;
    }

    /**
     * Returns the value of field 'xgrq'. The field 'xgrq' has the
     * following description: �޸�����
     * 
     * @return the value of field 'Xgrq'.
     */
    public java.lang.String getXgrq(
    ) {
        return this._xgrq;
    }

    /**
     * Returns the value of field 'yzpzxh'. The field 'yzpzxh' has
     * the following description: Ӧ��ƾ֤���(��ʾ������Ϣ���걨�����ݶ�Ӧ)
     * 
     * @return the value of field 'Yzpzxh'.
     */
    public java.lang.String getYzpzxh(
    ) {
        return this._yzpzxh;
    }

    /**
     * Returns the value of field 'zsxh'. The field 'zsxh' has the
     * following description: �������(��ʾ������Ϣ��Ψһ���)
     * 
     * @return the value of field 'Zsxh'.
     */
    public java.lang.String getZsxh(
    ) {
        return this._zsxh;
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
     * Sets the value of field 'lrrq'. The field 'lrrq' has the
     * following description: ¼������
     * 
     * @param lrrq the value of field 'lrrq'.
     */
    public void setLrrq(
            final java.lang.String lrrq) {
        this._lrrq = lrrq;
    }

    /**
     * Sets the value of field 'nsrSwjgDm'. The field 'nsrSwjgDm'
     * has the following description: ��˰������˰���������
     * 
     * @param nsrSwjgDm the value of field 'nsrSwjgDm'.
     */
    public void setNsrSwjgDm(
            final java.lang.String nsrSwjgDm) {
        this._nsrSwjgDm = nsrSwjgDm;
    }

    /**
     * Sets the value of field 'nsrsbh'. The field 'nsrsbh' has the
     * following description: ��˰��ʶ���
     * 
     * @param nsrsbh the value of field 'nsrsbh'.
     */
    public void setNsrsbh(
            final java.lang.String nsrsbh) {
        this._nsrsbh = nsrsbh;
    }

    /**
     * Sets the value of field 'sssq'. The field 'sssq' has the
     * following description: ����ʱ��
     * 
     * @param sssq the value of field 'sssq'.
     */
    public void setSssq(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Sssq sssq) {
        this._sssq = sssq;
    }

    /**
     * Sets the value of field 'xgrq'. The field 'xgrq' has the
     * following description: �޸�����
     * 
     * @param xgrq the value of field 'xgrq'.
     */
    public void setXgrq(
            final java.lang.String xgrq) {
        this._xgrq = xgrq;
    }

    /**
     * Sets the value of field 'yzpzxh'. The field 'yzpzxh' has the
     * following description: Ӧ��ƾ֤���(��ʾ������Ϣ���걨�����ݶ�Ӧ)
     * 
     * @param yzpzxh the value of field 'yzpzxh'.
     */
    public void setYzpzxh(
            final java.lang.String yzpzxh) {
        this._yzpzxh = yzpzxh;
    }

    /**
     * Sets the value of field 'zsxh'. The field 'zsxh' has the
     * following description: �������(��ʾ������Ϣ��Ψһ���)
     * 
     * @param zsxh the value of field 'zsxh'.
     */
    public void setZsxh(
            final java.lang.String zsxh) {
        this._zsxh = zsxh;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxHead
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxHead.class, reader);
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
