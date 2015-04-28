/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.userInfo;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ZSFSType.
 * 
 * @version $Revision$ $Date$
 */
public class ZSFSType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = -3295830159562395354L;

	/**
     * Field _CODE
     */
    private java.lang.String _CODE;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _DL
     */
    private java.lang.String _DL;

    /**
     * Field _CYL
     */
    private java.lang.String _CYL;

    /**
     * Field _DE
     */
    private java.lang.String _DE;


      //----------------/
     //- Constructors -/
    //----------------/

    public ZSFSType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.ZSFSType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'CODE'.
     * 
     * @return String
     * @return the value of field 'CODE'.
     */
    public java.lang.String getCODE()
    {
        return this._CODE;
    } //-- java.lang.String getCODE() 

    /**
     * Returns the value of field 'CYL'.
     * 
     * @return String
     * @return the value of field 'CYL'.
     */
    public java.lang.String getCYL()
    {
        return this._CYL;
    } //-- java.lang.String getCYL() 

    /**
     * Returns the value of field 'DE'.
     * 
     * @return String
     * @return the value of field 'DE'.
     */
    public java.lang.String getDE()
    {
        return this._DE;
    } //-- java.lang.String getDE() 

    /**
     * Returns the value of field 'DL'.
     * 
     * @return String
     * @return the value of field 'DL'.
     */
    public java.lang.String getDL()
    {
        return this._DL;
    } //-- java.lang.String getDL() 

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
     * Sets the value of field 'CODE'.
     * 
     * @param CODE the value of field 'CODE'.
     */
    public void setCODE(java.lang.String CODE)
    {
        this._CODE = CODE;
    } //-- void setCODE(java.lang.String) 

    /**
     * Sets the value of field 'CYL'.
     * 
     * @param CYL the value of field 'CYL'.
     */
    public void setCYL(java.lang.String CYL)
    {
        this._CYL = CYL;
    } //-- void setCYL(java.lang.String) 

    /**
     * Sets the value of field 'DE'.
     * 
     * @param DE the value of field 'DE'.
     */
    public void setDE(java.lang.String DE)
    {
        this._DE = DE;
    } //-- void setDE(java.lang.String) 

    /**
     * Sets the value of field 'DL'.
     * 
     * @param DL the value of field 'DL'.
     */
    public void setDL(java.lang.String DL)
    {
        this._DL = DL;
    } //-- void setDL(java.lang.String) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.ZSFSType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.ZSFSType.class, reader);
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
