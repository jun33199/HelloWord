/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;


/**
 * Class CHECKType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class CHECKType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _FID
     */
    private java.lang.String _FID;

    /**
     * Field _TYPE
     */
    private java.lang.String _TYPE;

    /**
     * Field _LEVEL
     */
    private java.lang.String _LEVEL;

    /**
     * Field _AUTO_CALCULATE
     */
    private java.lang.String _AUTO_CALCULATE;

    /**
     * Field _CONTENT
     */
    private java.lang.String _CONTENT;

    /**
     * Field _REMARK1
     */
    private java.lang.String _REMARK1;

    /**
     * Field _REMARK2
     */
    private java.lang.String _REMARK2;


      //----------------/
     //- Constructors -/
    //----------------/

    public CHECKType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'AUTO_CALCULATE'.
     * 
     * @return String
     * @return the value of field 'AUTO_CALCULATE'.
     */
    public java.lang.String getAUTO_CALCULATE()
    {
        return this._AUTO_CALCULATE;
    } //-- java.lang.String getAUTO_CALCULATE() 

    /**
     * Returns the value of field 'CONTENT'.
     * 
     * @return String
     * @return the value of field 'CONTENT'.
     */
    public java.lang.String getCONTENT()
    {
        return this._CONTENT;
    } //-- java.lang.String getCONTENT() 

    /**
     * Returns the value of field 'FID'.
     * 
     * @return String
     * @return the value of field 'FID'.
     */
    public java.lang.String getFID()
    {
        return this._FID;
    } //-- java.lang.String getFID() 

    /**
     * Returns the value of field 'LEVEL'.
     * 
     * @return String
     * @return the value of field 'LEVEL'.
     */
    public java.lang.String getLEVEL()
    {
        return this._LEVEL;
    } //-- java.lang.String getLEVEL() 

    /**
     * Returns the value of field 'REMARK1'.
     * 
     * @return String
     * @return the value of field 'REMARK1'.
     */
    public java.lang.String getREMARK1()
    {
        return this._REMARK1;
    } //-- java.lang.String getREMARK1() 

    /**
     * Returns the value of field 'REMARK2'.
     * 
     * @return String
     * @return the value of field 'REMARK2'.
     */
    public java.lang.String getREMARK2()
    {
        return this._REMARK2;
    } //-- java.lang.String getREMARK2() 

    /**
     * Returns the value of field 'TYPE'.
     * 
     * @return String
     * @return the value of field 'TYPE'.
     */
    public java.lang.String getTYPE()
    {
        return this._TYPE;
    } //-- java.lang.String getTYPE() 

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
     * Sets the value of field 'AUTO_CALCULATE'.
     * 
     * @param AUTO_CALCULATE the value of field 'AUTO_CALCULATE'.
     */
    public void setAUTO_CALCULATE(java.lang.String AUTO_CALCULATE)
    {
        this._AUTO_CALCULATE = AUTO_CALCULATE;
    } //-- void setAUTO_CALCULATE(java.lang.String) 

    /**
     * Sets the value of field 'CONTENT'.
     * 
     * @param CONTENT the value of field 'CONTENT'.
     */
    public void setCONTENT(java.lang.String CONTENT)
    {
        this._CONTENT = CONTENT;
    } //-- void setCONTENT(java.lang.String) 

    /**
     * Sets the value of field 'FID'.
     * 
     * @param FID the value of field 'FID'.
     */
    public void setFID(java.lang.String FID)
    {
        this._FID = FID;
    } //-- void setFID(java.lang.String) 

    /**
     * Sets the value of field 'LEVEL'.
     * 
     * @param LEVEL the value of field 'LEVEL'.
     */
    public void setLEVEL(java.lang.String LEVEL)
    {
        this._LEVEL = LEVEL;
    } //-- void setLEVEL(java.lang.String) 

    /**
     * Sets the value of field 'REMARK1'.
     * 
     * @param REMARK1 the value of field 'REMARK1'.
     */
    public void setREMARK1(java.lang.String REMARK1)
    {
        this._REMARK1 = REMARK1;
    } //-- void setREMARK1(java.lang.String) 

    /**
     * Sets the value of field 'REMARK2'.
     * 
     * @param REMARK2 the value of field 'REMARK2'.
     */
    public void setREMARK2(java.lang.String REMARK2)
    {
        this._REMARK2 = REMARK2;
    } //-- void setREMARK2(java.lang.String) 

    /**
     * Sets the value of field 'TYPE'.
     * 
     * @param TYPE the value of field 'TYPE'.
     */
    public void setTYPE(java.lang.String TYPE)
    {
        this._TYPE = TYPE;
    } //-- void setTYPE(java.lang.String) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKType.class, reader);
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
