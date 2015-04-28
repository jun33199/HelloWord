/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.config;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class REPORTType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class REPORTType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _BBQLX
     */
    private java.lang.String _BBQLX;

    /**
     * Field _TABLES
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES _TABLES;

    /**
     * Field _CHECKS
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS _CHECKS;


      //----------------/
     //- Constructors -/
    //----------------/

    public REPORTType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORTType()


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
     * Returns the value of field 'CHECKS'.
     * 
     * @return CHECKS
     * @return the value of field 'CHECKS'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS getCHECKS()
    {
        return this._CHECKS;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS getCHECKS() 

    /**
     * Returns the value of field 'NAME'.
     * 
     * @return String
     * @return the value of field 'NAME'.
     */
    public java.lang.String getNAME()
    {
        return this._NAME;
    } //-- java.lang.String getNAME() 

    /**
     * Returns the value of field 'TABLES'.
     * 
     * @return TABLES
     * @return the value of field 'TABLES'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES getTABLES()
    {
        return this._TABLES;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES getTABLES() 

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
     * Sets the value of field 'CHECKS'.
     * 
     * @param CHECKS the value of field 'CHECKS'.
     */
    public void setCHECKS(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS CHECKS)
    {
        this._CHECKS = CHECKS;
    } //-- void setCHECKS(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS) 

    /**
     * Sets the value of field 'NAME'.
     * 
     * @param NAME the value of field 'NAME'.
     */
    public void setNAME(java.lang.String NAME)
    {
        this._NAME = NAME;
    } //-- void setNAME(java.lang.String) 

    /**
     * Sets the value of field 'TABLES'.
     * 
     * @param TABLES the value of field 'TABLES'.
     */
    public void setTABLES(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES TABLES)
    {
        this._TABLES = TABLES;
    } //-- void setTABLES(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORTType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORTType.class, reader);
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
