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
 * �걨�������Ϣ��ͷ(������׼ҵ����δ������걨��Ϣ)
 * 
 * @version $Revision$ $Date$
 */
public class SbbJbxxHead implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ƾ֤���(��ʶͬһ���걨�����)
     */
    private java.lang.String _pzxh;

    /**
     * ��Ч��ʶ(���걨���Ƿ���Ч,'Y'��Ч,'N'��Ч������)
     */
    private java.lang.String _yxbz;

    /**
     * ��˰������˰����ش���
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

    /**
     * �����걨����
     */
    private java.lang.String _bcsblx;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbJbxxHead() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bcsblx'. The field 'bcsblx' has
     * the following description: �����걨����
     * 
     * @return the value of field 'Bcsblx'.
     */
    public java.lang.String getBcsblx(
    ) {
        return this._bcsblx;
    }

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
     * 'nsrSwjgDm' has the following description: ��˰������˰����ش���
     * 
     * @return the value of field 'NsrSwjgDm'.
     */
    public java.lang.String getNsrSwjgDm(
    ) {
        return this._nsrSwjgDm;
    }

    /**
     * Returns the value of field 'pzxh'. The field 'pzxh' has the
     * following description: ƾ֤���(��ʶͬһ���걨�����)
     * 
     * @return the value of field 'Pzxh'.
     */
    public java.lang.String getPzxh(
    ) {
        return this._pzxh;
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
     * Returns the value of field 'yxbz'. The field 'yxbz' has the
     * following description: ��Ч��ʶ(���걨���Ƿ���Ч,'Y'��Ч,'N'��Ч������)
     * 
     * @return the value of field 'Yxbz'.
     */
    public java.lang.String getYxbz(
    ) {
        return this._yxbz;
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
     * Sets the value of field 'bcsblx'. The field 'bcsblx' has the
     * following description: �����걨����
     * 
     * @param bcsblx the value of field 'bcsblx'.
     */
    public void setBcsblx(
            final java.lang.String bcsblx) {
        this._bcsblx = bcsblx;
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
     * has the following description: ��˰������˰����ش���
     * 
     * @param nsrSwjgDm the value of field 'nsrSwjgDm'.
     */
    public void setNsrSwjgDm(
            final java.lang.String nsrSwjgDm) {
        this._nsrSwjgDm = nsrSwjgDm;
    }

    /**
     * Sets the value of field 'pzxh'. The field 'pzxh' has the
     * following description: ƾ֤���(��ʶͬһ���걨�����)
     * 
     * @param pzxh the value of field 'pzxh'.
     */
    public void setPzxh(
            final java.lang.String pzxh) {
        this._pzxh = pzxh;
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
     * Sets the value of field 'yxbz'. The field 'yxbz' has the
     * following description: ��Ч��ʶ(���걨���Ƿ���Ч,'Y'��Ч,'N'��Ч������)
     * 
     * @param yxbz the value of field 'yxbz'.
     */
    public void setYxbz(
            final java.lang.String yxbz) {
        this._yxbz = yxbz;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbJbxxHead
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbJbxxHead unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbJbxxHead) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbJbxxHead.class, reader);
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
