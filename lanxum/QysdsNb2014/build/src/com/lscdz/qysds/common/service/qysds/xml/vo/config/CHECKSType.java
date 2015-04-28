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
 * Class CHECKSType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class CHECKSType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _CHECKList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _CHECKList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public CHECKSType() 
     {
        super();
        _CHECKList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKSType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCHECK
     * 
     * 
     * 
     * @param vCHECK
     */
    @SuppressWarnings("unchecked")
	public void addCHECK(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK vCHECK)
        throws java.lang.IndexOutOfBoundsException
    {
        _CHECKList.addElement(vCHECK);
    } //-- void addCHECK(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) 

    /**
     * Method addCHECK
     * 
     * 
     * 
     * @param index
     * @param vCHECK
     */
    @SuppressWarnings("unchecked")
	public void addCHECK(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK vCHECK)
        throws java.lang.IndexOutOfBoundsException
    {
        _CHECKList.insertElementAt(vCHECK, index);
    } //-- void addCHECK(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) 

    /**
     * Method enumerateCHECK
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateCHECK()
    {
        return _CHECKList.elements();
    } //-- java.util.Enumeration enumerateCHECK() 

    /**
     * Method getCHECK
     * 
     * 
     * 
     * @param index
     * @return CHECK
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK getCHECK(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _CHECKList.size())) {
            throw new IndexOutOfBoundsException("getCHECK: Index value '"+index+"' not in range [0.."+_CHECKList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) _CHECKList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK getCHECK(int) 

    /**
     * Method getCHECK
     * 
     * 
     * 
     * @return CHECK
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK[] getCHECK()
    {
        int size = _CHECKList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) _CHECKList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK[] getCHECK() 

    /**
     * Method getCHECKCount
     * 
     * 
     * 
     * @return int
     */
    public int getCHECKCount()
    {
        return _CHECKList.size();
    } //-- int getCHECKCount() 

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
     * Method removeAllCHECK
     * 
     */
    public void removeAllCHECK()
    {
        _CHECKList.removeAllElements();
    } //-- void removeAllCHECK() 

    /**
     * Method removeCHECK
     * 
     * 
     * 
     * @param index
     * @return CHECK
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK removeCHECK(int index)
    {
        java.lang.Object obj = _CHECKList.elementAt(index);
        _CHECKList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK removeCHECK(int) 

    /**
     * Method setCHECK
     * 
     * 
     * 
     * @param index
     * @param vCHECK
     */
    @SuppressWarnings("unchecked")
	public void setCHECK(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK vCHECK)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _CHECKList.size())) {
            throw new IndexOutOfBoundsException("setCHECK: Index value '"+index+"' not in range [0.."+_CHECKList.size()+ "]");
        }
        _CHECKList.setElementAt(vCHECK, index);
    } //-- void setCHECK(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) 

    /**
     * Method setCHECK
     * 
     * 
     * 
     * @param CHECKArray
     */
    @SuppressWarnings("unchecked")
	public void setCHECK(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK[] CHECKArray)
    {
        //-- copy array
        _CHECKList.removeAllElements();
        for (int i = 0; i < CHECKArray.length; i++) {
            _CHECKList.addElement(CHECKArray[i]);
        }
    } //-- void setCHECK(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKSType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKSType.class, reader);
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
