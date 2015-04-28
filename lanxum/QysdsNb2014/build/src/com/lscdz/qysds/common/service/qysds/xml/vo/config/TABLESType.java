/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.config;


import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TABLESType.
 * 
 * @version $Revision$ $Date$
 */
public class TABLESType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = 2539419128329770852L;
	/**
     * Field _TABLEList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _TABLEList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public TABLESType() 
     {
        super();
        _TABLEList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLESType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addTABLE
     * 
     * 
     * 
     * @param vTABLE
     */
    @SuppressWarnings("unchecked")
	public void addTABLE(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE vTABLE)
        throws java.lang.IndexOutOfBoundsException
    {
        _TABLEList.addElement(vTABLE);
    } //-- void addTABLE(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) 

    /**
     * Method addTABLE
     * 
     * 
     * 
     * @param index
     * @param vTABLE
     */
    @SuppressWarnings("unchecked")
	public void addTABLE(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE vTABLE)
        throws java.lang.IndexOutOfBoundsException
    {
        _TABLEList.insertElementAt(vTABLE, index);
    } //-- void addTABLE(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) 

    /**
     * Method enumerateTABLE
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateTABLE()
    {
        return _TABLEList.elements();
    } //-- java.util.Enumeration enumerateTABLE() 

    /**
     * Method getTABLE
     * 
     * 
     * 
     * @param index
     * @return TABLE
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE getTABLE(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _TABLEList.size())) {
            throw new IndexOutOfBoundsException("getTABLE: Index value '"+index+"' not in range [0.."+_TABLEList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) _TABLEList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE getTABLE(int) 

    /**
     * Method getTABLE
     * 
     * 
     * 
     * @return TABLE
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE[] getTABLE()
    {
        int size = _TABLEList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) _TABLEList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE[] getTABLE() 

    /**
     * Method getTABLECount
     * 
     * 
     * 
     * @return int
     */
    public int getTABLECount()
    {
        return _TABLEList.size();
    } //-- int getTABLECount() 

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
     * Method removeAllTABLE
     * 
     */
    public void removeAllTABLE()
    {
        _TABLEList.removeAllElements();
    } //-- void removeAllTABLE() 

    /**
     * Method removeTABLE
     * 
     * 
     * 
     * @param index
     * @return TABLE
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE removeTABLE(int index)
    {
        java.lang.Object obj = _TABLEList.elementAt(index);
        _TABLEList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE removeTABLE(int) 

    /**
     * Method setTABLE
     * 
     * 
     * 
     * @param index
     * @param vTABLE
     */
    @SuppressWarnings("unchecked")
	public void setTABLE(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE vTABLE)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _TABLEList.size())) {
            throw new IndexOutOfBoundsException("setTABLE: Index value '"+index+"' not in range [0.."+_TABLEList.size()+ "]");
        }
        _TABLEList.setElementAt(vTABLE, index);
    } //-- void setTABLE(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) 

    /**
     * Method setTABLE
     * 
     * 
     * 
     * @param TABLEArray
     */
    @SuppressWarnings("unchecked")
	public void setTABLE(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE[] TABLEArray)
    {
        //-- copy array
        _TABLEList.removeAllElements();
        for (int i = 0; i < TABLEArray.length; i++) {
            _TABLEList.addElement(TABLEArray[i]);
        }
    } //-- void setTABLE(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLESType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLESType.class, reader);
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
