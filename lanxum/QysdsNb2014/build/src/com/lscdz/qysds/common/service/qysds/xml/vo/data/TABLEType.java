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
	private static final long serialVersionUID = 9184305059994390921L;

	/**
     * Field _TID
     */
    private java.lang.String _TID;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _INPUTITEMS
     */
    private com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS _INPUTITEMS;


      //----------------/
     //- Constructors -/
    //----------------/

    public TABLEType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLEType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'INPUTITEMS'.
     * 
     * @return INPUTITEMS
     * @return the value of field 'INPUTITEMS'.
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS getINPUTITEMS()
    {
        return this._INPUTITEMS;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS getINPUTITEMS() 

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
     * Sets the value of field 'INPUTITEMS'.
     * 
     * @param INPUTITEMS the value of field 'INPUTITEMS'.
     */
    public void setINPUTITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS INPUTITEMS)
    {
        this._INPUTITEMS = INPUTITEMS;
    } //-- void setINPUTITEMS(com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS) 

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
     * Sets the value of field 'TID'.
     * 
     * @param TID the value of field 'TID'.
     */
    public void setTID(java.lang.String TID)
    {
        this._TID = TID;
    } //-- void setTID(java.lang.String) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLEType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLEType.class, reader);
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
