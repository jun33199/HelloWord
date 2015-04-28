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
 * Class APPS.
 * 
 * @version $Revision$ $Date$
 */
public class APPS implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = 877316251781956281L;
	/**
     * Field _APPList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _APPList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public APPS() 
     {
        super();
        _APPList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAPP
     * 
     * 
     * 
     * @param vAPP
     */
    @SuppressWarnings("unchecked")
	public void addAPP(com.lscdz.qysds.common.service.qysds.xml.vo.config.APP vAPP)
        throws java.lang.IndexOutOfBoundsException
    {
        _APPList.addElement(vAPP);
    } //-- void addAPP(com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) 

    /**
     * Method addAPP
     * 
     * 
     * 
     * @param index
     * @param vAPP
     */
    @SuppressWarnings("unchecked")
	public void addAPP(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.APP vAPP)
        throws java.lang.IndexOutOfBoundsException
    {
        _APPList.insertElementAt(vAPP, index);
    } //-- void addAPP(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) 

    /**
     * Method enumerateAPP
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateAPP()
    {
        return _APPList.elements();
    } //-- java.util.Enumeration enumerateAPP() 

    /**
     * Method getAPP
     * 
     * 
     * 
     * @param index
     * @return APP
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.APP getAPP(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _APPList.size())) {
            throw new IndexOutOfBoundsException("getAPP: Index value '"+index+"' not in range [0.."+_APPList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) _APPList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.APP getAPP(int) 

    /**
     * Method getAPP
     * 
     * 
     * 
     * @return APP
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.APP[] getAPP()
    {
        int size = _APPList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.config.APP[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.config.APP[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) _APPList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.APP[] getAPP() 

    /**
     * Method getAPPCount
     * 
     * 
     * 
     * @return int
     */
    public int getAPPCount()
    {
        return _APPList.size();
    } //-- int getAPPCount() 

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
     * Method removeAPP
     * 
     * 
     * 
     * @param index
     * @return APP
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.APP removeAPP(int index)
    {
        java.lang.Object obj = _APPList.elementAt(index);
        _APPList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.APP removeAPP(int) 

    /**
     * Method removeAllAPP
     * 
     */
    public void removeAllAPP()
    {
        _APPList.removeAllElements();
    } //-- void removeAllAPP() 

    /**
     * Method setAPP
     * 
     * 
     * 
     * @param index
     * @param vAPP
     */
    @SuppressWarnings("unchecked")
	public void setAPP(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.APP vAPP)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _APPList.size())) {
            throw new IndexOutOfBoundsException("setAPP: Index value '"+index+"' not in range [0.."+_APPList.size()+ "]");
        }
        _APPList.setElementAt(vAPP, index);
    } //-- void setAPP(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) 

    /**
     * Method setAPP
     * 
     * 
     * 
     * @param APPArray
     */
    @SuppressWarnings("unchecked")
	public void setAPP(com.lscdz.qysds.common.service.qysds.xml.vo.config.APP[] APPArray)
    {
        //-- copy array
        _APPList.removeAllElements();
        for (int i = 0; i < APPArray.length; i++) {
            _APPList.addElement(APPArray[i]);
        }
    } //-- void setAPP(com.lscdz.qysds.common.service.qysds.xml.vo.config.APP) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS.class, reader);
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
