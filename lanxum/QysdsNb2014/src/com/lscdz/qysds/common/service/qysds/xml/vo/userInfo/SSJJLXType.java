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
 * Class SSJJLXType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SSJJLXType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _CODE
     */
    private java.lang.String _CODE;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;


      //----------------/
     //- Constructors -/
    //----------------/

    public SSJJLXType() 
     {
        super();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SSJJLXType()


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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SSJJLXType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SSJJLXType.class, reader);
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
