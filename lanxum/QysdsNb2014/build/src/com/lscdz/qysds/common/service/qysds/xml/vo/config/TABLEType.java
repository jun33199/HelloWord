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
 * Class TABLEType.
 * 
 * @version $Revision$ $Date$
 */
public class TABLEType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = -6852402767294540088L;

	/**
     * Field _TID
     */
    private java.lang.String _TID;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _REPORTTYPE
     */
    private java.lang.String _REPORTTYPE;

    /**
     * Field _ACTIVITY
     */
    private java.lang.String _ACTIVITY;

    /**
     * Field _REMARK1
     */
    private java.lang.String _REMARK1;

    /**
     * Field _REMARK2
     */
    private java.lang.String _REMARK2;

    /**
     * Field _INPUTITEMS
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS _INPUTITEMS;

    /**
     * Field _VIEWITEMS
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS _VIEWITEMS;


      //----------------/
     //- Constructors -/
    //----------------/

    public TABLEType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLEType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'ACTIVITY'.
     * 
     * @return String
     * @return the value of field 'ACTIVITY'.
     */
    public java.lang.String getACTIVITY()
    {
        return this._ACTIVITY;
    } //-- java.lang.String getACTIVITY() 

    /**
     * Returns the value of field 'INPUTITEMS'.
     * 
     * @return INPUTITEMS
     * @return the value of field 'INPUTITEMS'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS getINPUTITEMS()
    {
        return this._INPUTITEMS;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS getINPUTITEMS() 

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
     * Returns the value of field 'REPORTTYPE'.
     * 
     * @return String
     * @return the value of field 'REPORTTYPE'.
     */
    public java.lang.String getREPORTTYPE()
    {
        return this._REPORTTYPE;
    } //-- java.lang.String getREPORTTYPE() 

    /**
     * Returns the value of field 'TID'.
     * 
     * @return String
     * @return the value of field 'TID'.
     */
    public java.lang.String getTID()
    {
        return this._TID;
    } //-- java.lang.String getTID() 

    /**
     * Returns the value of field 'VIEWITEMS'.
     * 
     * @return VIEWITEMS
     * @return the value of field 'VIEWITEMS'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS getVIEWITEMS()
    {
        return this._VIEWITEMS;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS getVIEWITEMS() 

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
     * Sets the value of field 'ACTIVITY'.
     * 
     * @param ACTIVITY the value of field 'ACTIVITY'.
     */
    public void setACTIVITY(java.lang.String ACTIVITY)
    {
        this._ACTIVITY = ACTIVITY;
    } //-- void setACTIVITY(java.lang.String) 

    /**
     * Sets the value of field 'INPUTITEMS'.
     * 
     * @param INPUTITEMS the value of field 'INPUTITEMS'.
     */
    public void setINPUTITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS INPUTITEMS)
    {
        this._INPUTITEMS = INPUTITEMS;
    } //-- void setINPUTITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS) 

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
     * Sets the value of field 'REPORTTYPE'.
     * 
     * @param REPORTTYPE the value of field 'REPORTTYPE'.
     */
    public void setREPORTTYPE(java.lang.String REPORTTYPE)
    {
        this._REPORTTYPE = REPORTTYPE;
    } //-- void setREPORTTYPE(java.lang.String) 

    /**
     * Sets the value of field 'TID'.
     * 
     * @param TID the value of field 'TID'.
     */
    public void setTID(java.lang.String TID)
    {
        this._TID = TID;
    } //-- void setTID(java.lang.String) 

    /**
     * Sets the value of field 'VIEWITEMS'.
     * 
     * @param VIEWITEMS the value of field 'VIEWITEMS'.
     */
    public void setVIEWITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS VIEWITEMS)
    {
        this._VIEWITEMS = VIEWITEMS;
    } //-- void setVIEWITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLEType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLEType.class, reader);
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
