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
 * Class ITEMType.
 * 
 * @version $Revision$ $Date$
 */
public class ITEMType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = -2773747611260593915L;

	/**
     * Field _IID
     */
    private java.lang.String _IID;

    /**
     * Field _REPORTTYPE
     */
    private java.lang.String _REPORTTYPE;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _VALUE
     */
    private java.lang.String _VALUE;


      //----------------/
     //- Constructors -/
    //----------------/

    public ITEMType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.ITEMType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'IID'.
     * 
     * @return String
     * @return the value of field 'IID'.
     */
    public java.lang.String getIID()
    {
        return this._IID;
    } //-- java.lang.String getIID() 

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
     * Returns the value of field 'VALUE'.
     * 
     * @return String
     * @return the value of field 'VALUE'.
     */
    public java.lang.String getVALUE()
    {
        return this._VALUE;
    } //-- java.lang.String getVALUE() 

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
     * Sets the value of field 'IID'.
     * 
     * @param IID the value of field 'IID'.
     */
    public void setIID(java.lang.String IID)
    {
        this._IID = IID;
    } //-- void setIID(java.lang.String) 

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
     * Sets the value of field 'REPORTTYPE'.
     * 
     * @param REPORTTYPE the value of field 'REPORTTYPE'.
     */
    public void setREPORTTYPE(java.lang.String REPORTTYPE)
    {
        this._REPORTTYPE = REPORTTYPE;
    } //-- void setREPORTTYPE(java.lang.String) 

    /**
     * Sets the value of field 'VALUE'.
     * 
     * @param VALUE the value of field 'VALUE'.
     */
    public void setVALUE(java.lang.String VALUE)
    {
        this._VALUE = VALUE;
    } //-- void setVALUE(java.lang.String) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.ITEMType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.data.ITEMType.class, reader);
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
