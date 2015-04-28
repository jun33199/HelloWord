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
 * Class INPUTITEMSType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class INPUTITEMSType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _ITEMList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _ITEMList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public INPUTITEMSType() 
     {
        super();
        _ITEMList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMSType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addITEM
     * 
     * 
     * 
     * @param vITEM
     */
    @SuppressWarnings("unchecked")
	public void addITEM(com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM vITEM)
        throws java.lang.IndexOutOfBoundsException
    {
        _ITEMList.addElement(vITEM);
    } //-- void addITEM(com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) 

    /**
     * Method addITEM
     * 
     * 
     * 
     * @param index
     * @param vITEM
     */
    @SuppressWarnings("unchecked")
	public void addITEM(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM vITEM)
        throws java.lang.IndexOutOfBoundsException
    {
        _ITEMList.insertElementAt(vITEM, index);
    } //-- void addITEM(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) 

    /**
     * Method enumerateITEM
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateITEM()
    {
        return _ITEMList.elements();
    } //-- java.util.Enumeration enumerateITEM() 

    /**
     * Method getITEM
     * 
     * 
     * 
     * @param index
     * @return ITEM
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM getITEM(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ITEMList.size())) {
            throw new IndexOutOfBoundsException("getITEM: Index value '"+index+"' not in range [0.."+_ITEMList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) _ITEMList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM getITEM(int) 

    /**
     * Method getITEM
     * 
     * 
     * 
     * @return ITEM
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM[] getITEM()
    {
        int size = _ITEMList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) _ITEMList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM[] getITEM() 

    /**
     * Method getITEMCount
     * 
     * 
     * 
     * @return int
     */
    public int getITEMCount()
    {
        return _ITEMList.size();
    } //-- int getITEMCount() 

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
     * Method removeAllITEM
     * 
     */
    public void removeAllITEM()
    {
        _ITEMList.removeAllElements();
    } //-- void removeAllITEM() 

    /**
     * Method removeITEM
     * 
     * 
     * 
     * @param index
     * @return ITEM
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM removeITEM(int index)
    {
        java.lang.Object obj = _ITEMList.elementAt(index);
        _ITEMList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM removeITEM(int) 

    /**
     * Method setITEM
     * 
     * 
     * 
     * @param index
     * @param vITEM
     */
    @SuppressWarnings("unchecked")
	public void setITEM(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM vITEM)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ITEMList.size())) {
            throw new IndexOutOfBoundsException("setITEM: Index value '"+index+"' not in range [0.."+_ITEMList.size()+ "]");
        }
        _ITEMList.setElementAt(vITEM, index);
    } //-- void setITEM(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) 

    /**
     * Method setITEM
     * 
     * 
     * 
     * @param ITEMArray
     */
    @SuppressWarnings("unchecked")
	public void setITEM(com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM[] ITEMArray)
    {
        //-- copy array
        _ITEMList.removeAllElements();
        for (int i = 0; i < ITEMArray.length; i++) {
            _ITEMList.addElement(ITEMArray[i]);
        }
    } //-- void setITEM(com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMSType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMSType.class, reader);
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
