/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.jbal;

/**
 * Class TaxDoc.
 * 
 * @version $Revision$ $Date$
 */
public abstract class TaxDoc implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version.
     */
    private java.lang.String _version = "SW5001-2005";

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _cnName.
     */
    private java.lang.String _cnName;


      //----------------/
     //- Constructors -/
    //----------------/

    public TaxDoc() {
        super();
        setVersion("SW5001-2005");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cnName'.
     * 
     * @return the value of field 'CnName'.
     */
    public java.lang.String getCnName(
    ) {
        return this._cnName;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'version'.
     * 
     * @return the value of field 'Version'.
     */
    public java.lang.String getVersion(
    ) {
        return this._version;
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
     * Sets the value of field 'cnName'.
     * 
     * @param cnName the value of field 'cnName'.
     */
    public void setCnName(
            final java.lang.String cnName) {
        this._cnName = cnName;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(
            final java.lang.String version) {
        this._version = version;
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
