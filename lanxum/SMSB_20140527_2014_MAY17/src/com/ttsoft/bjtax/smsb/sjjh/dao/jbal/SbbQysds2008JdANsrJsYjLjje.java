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
 * ʵ�о�ʵԤ�ɵ���˰����д����-�ۼƽ��
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdANsrJsYjLjje implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 2 Ӫҵ����
     */
    private java.lang.String _yysr;

    /**
     * 3 Ӫҵ�ɱ�
     */
    private java.lang.String _yycb;

    /**
     * 4 �����ܶ�
     */
    private java.lang.String _lrze;

    /**
     * 6 Ӧ������˰�4��5�У�
     */
    private java.lang.String _ynsdse;

    /**
     * 7 ��������˰��
     */
    private java.lang.String _jmsdse;

    /**
     * 8 ʵ���ѽ�����˰��
     */
    private java.lang.String _sjyjSdse;

    /**
     * 9 Ӧ�����ˣ�������˰�6��-7��-8�У�
     */
    private java.lang.String _ybtSdse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdANsrJsYjLjje() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'jmsdse'. The field 'jmsdse' has
     * the following description: 7 ��������˰��
     * 
     * @return the value of field 'Jmsdse'.
     */
    public java.lang.String getJmsdse(
    ) {
        return this._jmsdse;
    }

    /**
     * Returns the value of field 'lrze'. The field 'lrze' has the
     * following description: 4 �����ܶ�
     * 
     * @return the value of field 'Lrze'.
     */
    public java.lang.String getLrze(
    ) {
        return this._lrze;
    }

    /**
     * Returns the value of field 'sjyjSdse'. The field 'sjyjSdse'
     * has the following description: 8 ʵ���ѽ�����˰��
     * 
     * @return the value of field 'SjyjSdse'.
     */
    public java.lang.String getSjyjSdse(
    ) {
        return this._sjyjSdse;
    }

    /**
     * Returns the value of field 'ybtSdse'. The field 'ybtSdse'
     * has the following description: 9 Ӧ�����ˣ�������˰�6��-7��-8�У�
     * 
     * @return the value of field 'YbtSdse'.
     */
    public java.lang.String getYbtSdse(
    ) {
        return this._ybtSdse;
    }

    /**
     * Returns the value of field 'ynsdse'. The field 'ynsdse' has
     * the following description: 6 Ӧ������˰�4��5�У�
     * 
     * @return the value of field 'Ynsdse'.
     */
    public java.lang.String getYnsdse(
    ) {
        return this._ynsdse;
    }

    /**
     * Returns the value of field 'yycb'. The field 'yycb' has the
     * following description: 3 Ӫҵ�ɱ�
     * 
     * @return the value of field 'Yycb'.
     */
    public java.lang.String getYycb(
    ) {
        return this._yycb;
    }

    /**
     * Returns the value of field 'yysr'. The field 'yysr' has the
     * following description: 2 Ӫҵ����
     * 
     * @return the value of field 'Yysr'.
     */
    public java.lang.String getYysr(
    ) {
        return this._yysr;
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
     * Sets the value of field 'jmsdse'. The field 'jmsdse' has the
     * following description: 7 ��������˰��
     * 
     * @param jmsdse the value of field 'jmsdse'.
     */
    public void setJmsdse(
            final java.lang.String jmsdse) {
        this._jmsdse = jmsdse;
    }

    /**
     * Sets the value of field 'lrze'. The field 'lrze' has the
     * following description: 4 �����ܶ�
     * 
     * @param lrze the value of field 'lrze'.
     */
    public void setLrze(
            final java.lang.String lrze) {
        this._lrze = lrze;
    }

    /**
     * Sets the value of field 'sjyjSdse'. The field 'sjyjSdse' has
     * the following description: 8 ʵ���ѽ�����˰��
     * 
     * @param sjyjSdse the value of field 'sjyjSdse'.
     */
    public void setSjyjSdse(
            final java.lang.String sjyjSdse) {
        this._sjyjSdse = sjyjSdse;
    }

    /**
     * Sets the value of field 'ybtSdse'. The field 'ybtSdse' has
     * the following description: 9 Ӧ�����ˣ�������˰�6��-7��-8�У�
     * 
     * @param ybtSdse the value of field 'ybtSdse'.
     */
    public void setYbtSdse(
            final java.lang.String ybtSdse) {
        this._ybtSdse = ybtSdse;
    }

    /**
     * Sets the value of field 'ynsdse'. The field 'ynsdse' has the
     * following description: 6 Ӧ������˰�4��5�У�
     * 
     * @param ynsdse the value of field 'ynsdse'.
     */
    public void setYnsdse(
            final java.lang.String ynsdse) {
        this._ynsdse = ynsdse;
    }

    /**
     * Sets the value of field 'yycb'. The field 'yycb' has the
     * following description: 3 Ӫҵ�ɱ�
     * 
     * @param yycb the value of field 'yycb'.
     */
    public void setYycb(
            final java.lang.String yycb) {
        this._yycb = yycb;
    }

    /**
     * Sets the value of field 'yysr'. The field 'yysr' has the
     * following description: 2 Ӫҵ����
     * 
     * @param yysr the value of field 'yysr'.
     */
    public void setYysr(
            final java.lang.String yysr) {
        this._yysr = yysr;
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYjLjj
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYjLjje unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYjLjje) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdANsrJsYjLjje.class, reader);
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
