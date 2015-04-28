/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.data;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;


/**
 * Class REPORTType.
 * @version $Revision$ $Date$
 */
public class REPORTType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = -6317984469527809968L;

	/**
     * Field _VERSION
     */
    private java.lang.String _VERSION;

    /**
     * Field _BBQLX
     */
    private java.lang.String _BBQLX;

    /**
     * Field _QH
     */
    private java.lang.String _QH;

    /**
     * Field _ND
     */
    private java.lang.String _ND;

    /**
     * Field _SKSSKSRQ
     */
    private java.lang.String _SKSSKSRQ;

    /**
     * Field _SKSSJSRQ
     */
    private java.lang.String _SKSSJSRQ;

    /**
     * Field _TABLES
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES _TABLES;


      //----------------/
     //- Constructors -/
    //----------------/

    public REPORTType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORTType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'BBQLX'.
     * 
     * @return String
     * @return the value of field 'BBQLX'.
     */
    public java.lang.String getBBQLX()
    {
        return this._BBQLX;
    } //-- java.lang.String getBBQLX() 

    /**
     * Returns the value of field 'ND'.
     * 
     * @return String
     * @return the value of field 'ND'.
     */
    public java.lang.String getND()
    {
        return this._ND;
    } //-- java.lang.String getND() 

    /**
     * Returns the value of field 'QH'.
     * 
     * @return String
     * @return the value of field 'QH'.
     */
    public java.lang.String getQH()
    {
        return this._QH;
    } //-- java.lang.String getQH() 

    /**
     * Returns the value of field 'SKSSJSRQ'.
     * 
     * @return String
     * @return the value of field 'SKSSJSRQ'.
     */
    public java.lang.String getSKSSJSRQ()
    {
        return this._SKSSJSRQ;
    } //-- java.lang.String getSKSSJSRQ() 

    /**
     * Returns the value of field 'SKSSKSRQ'.
     * 
     * @return String
     * @return the value of field 'SKSSKSRQ'.
     */
    public java.lang.String getSKSSKSRQ()
    {
        return this._SKSSKSRQ;
    } //-- java.lang.String getSKSSKSRQ() 

    /**
     * Returns the value of field 'TABLES'.
     * 
     * @return TABLES
     * @return the value of field 'TABLES'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES getTABLES()
    {
        return this._TABLES;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES getTABLES() 

    /**
     * Returns the value of field 'VERSION'.
     * 
     * @return String
     * @return the value of field 'VERSION'.
     */
    public java.lang.String getVERSION()
    {
        return this._VERSION;
    } //-- java.lang.String getVERSION() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'BBQLX'.
     * 
     * @param BBQLX the value of field 'BBQLX'.
     */
    public void setBBQLX(java.lang.String BBQLX)
    {
        this._BBQLX = BBQLX;
    } //-- void setBBQLX(java.lang.String) 

    /**
     * Sets the value of field 'ND'.
     * 
     * @param ND the value of field 'ND'.
     */
    public void setND(java.lang.String ND)
    {
        this._ND = ND;
    } //-- void setND(java.lang.String) 

    /**
     * Sets the value of field 'QH'.
     * 
     * @param QH the value of field 'QH'.
     */
    public void setQH(java.lang.String QH)
    {
        this._QH = QH;
    } //-- void setQH(java.lang.String) 

    /**
     * Sets the value of field 'SKSSJSRQ'.
     * 
     * @param SKSSJSRQ the value of field 'SKSSJSRQ'.
     */
    public void setSKSSJSRQ(java.lang.String SKSSJSRQ)
    {
        this._SKSSJSRQ = SKSSJSRQ;
    } //-- void setSKSSJSRQ(java.lang.String) 

    /**
     * Sets the value of field 'SKSSKSRQ'.
     * 
     * @param SKSSKSRQ the value of field 'SKSSKSRQ'.
     */
    public void setSKSSKSRQ(java.lang.String SKSSKSRQ)
    {
        this._SKSSKSRQ = SKSSKSRQ;
    } //-- void setSKSSKSRQ(java.lang.String) 

    /**
     * Sets the value of field 'TABLES'.
     * 
     * @param TABLES the value of field 'TABLES'.
     */
    public void setTABLES(com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES TABLES)
    {
        this._TABLES = TABLES;
    } //-- void setTABLES(com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES) 

    /**
     * Sets the value of field 'VERSION'.
     * 
     * @param VERSION the value of field 'VERSION'.
     */
    public void setVERSION(java.lang.String VERSION)
    {
        this._VERSION = VERSION;
    } //-- void setVERSION(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Object
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORTType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORTType.class, reader);
    } //-- java.lang.Object unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
