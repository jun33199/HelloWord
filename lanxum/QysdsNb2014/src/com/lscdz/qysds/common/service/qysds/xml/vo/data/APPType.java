/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.data;

import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class APPType.
 * 
 * @version $Revision$ $Date$
 */
public class APPType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
	 * 
	 */
	private static final long serialVersionUID = -3359961129557405861L;

	/**
     * Field _AID
     */
    private java.lang.String _AID;

    /**
     * Field _NSRJSJDM
     */
    private java.lang.String _NSRJSJDM;

    /**
     * Field _NSRMC
     */
    private java.lang.String _NSRMC;

    /**
     * Field _REPORTList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _REPORTList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public APPType() 
     {
        super();
        _REPORTList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.APPType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addREPORT
     * 
     * 
     * 
     * @param vREPORT
     */
    @SuppressWarnings("unchecked")
	public void addREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        _REPORTList.addElement(vREPORT);
    } //-- void addREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) 

    /**
     * Method addREPORT
     * 
     * 
     * 
     * @param index
     * @param vREPORT
     */
    @SuppressWarnings("unchecked")
	public void addREPORT(int index, com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        _REPORTList.insertElementAt(vREPORT, index);
    } //-- void addREPORT(int, com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) 

    /**
     * Method enumerateREPORT
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateREPORT()
    {
        return _REPORTList.elements();
    } //-- java.util.Enumeration enumerateREPORT() 

    /**
     * Returns the value of field 'AID'.
     * 
     * @return String
     * @return the value of field 'AID'.
     */
    public java.lang.String getAID()
    {
        return this._AID;
    } //-- java.lang.String getAID() 

    /**
     * Returns the value of field 'NSRJSJDM'.
     * 
     * @return String
     * @return the value of field 'NSRJSJDM'.
     */
    public java.lang.String getNSRJSJDM()
    {
        return this._NSRJSJDM;
    } //-- java.lang.String getNSRJSJDM() 

    /**
     * Returns the value of field 'NSRMC'.
     * 
     * @return String
     * @return the value of field 'NSRMC'.
     */
    public java.lang.String getNSRMC()
    {
        return this._NSRMC;
    } //-- java.lang.String getNSRMC() 

    /**
     * Method getREPORT
     * 
     * 
     * 
     * @param index
     * @return REPORT
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT getREPORT(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _REPORTList.size())) {
            throw new IndexOutOfBoundsException("getREPORT: Index value '"+index+"' not in range [0.."+_REPORTList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) _REPORTList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT getREPORT(int) 

    /**
     * Method getREPORT
     * 
     * 
     * 
     * @return REPORT
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT[] getREPORT()
    {
        int size = _REPORTList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) _REPORTList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT[] getREPORT() 

    /**
     * Method getREPORTCount
     * 
     * 
     * 
     * @return int
     */
    public int getREPORTCount()
    {
        return _REPORTList.size();
    } //-- int getREPORTCount() 

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
     * Method removeAllREPORT
     * 
     */
    public void removeAllREPORT()
    {
        _REPORTList.removeAllElements();
    } //-- void removeAllREPORT() 

    /**
     * Method removeREPORT
     * 
     * 
     * 
     * @param index
     * @return REPORT
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT removeREPORT(int index)
    {
        java.lang.Object obj = _REPORTList.elementAt(index);
        _REPORTList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT removeREPORT(int) 

    /**
     * Sets the value of field 'AID'.
     * 
     * @param AID the value of field 'AID'.
     */
    public void setAID(java.lang.String AID)
    {
        this._AID = AID;
    } //-- void setAID(java.lang.String) 

    /**
     * Sets the value of field 'NSRJSJDM'.
     * 
     * @param NSRJSJDM the value of field 'NSRJSJDM'.
     */
    public void setNSRJSJDM(java.lang.String NSRJSJDM)
    {
        this._NSRJSJDM = NSRJSJDM;
    } //-- void setNSRJSJDM(java.lang.String) 

    /**
     * Sets the value of field 'NSRMC'.
     * 
     * @param NSRMC the value of field 'NSRMC'.
     */
    public void setNSRMC(java.lang.String NSRMC)
    {
        this._NSRMC = NSRMC;
    } //-- void setNSRMC(java.lang.String) 

    /**
     * Method setREPORT
     * 
     * 
     * 
     * @param index
     * @param vREPORT
     */
    @SuppressWarnings("unchecked")
	public void setREPORT(int index, com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _REPORTList.size())) {
            throw new IndexOutOfBoundsException("setREPORT: Index value '"+index+"' not in range [0.."+_REPORTList.size()+ "]");
        }
        _REPORTList.setElementAt(vREPORT, index);
    } //-- void setREPORT(int, com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) 

    /**
     * Method setREPORT
     * 
     * 
     * 
     * @param REPORTArray
     */
    @SuppressWarnings("unchecked")
	public void setREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT[] REPORTArray)
    {
        //-- copy array
        _REPORTList.removeAllElements();
        for (int i = 0; i < REPORTArray.length; i++) {
            _REPORTList.addElement(REPORTArray[i]);
        }
    } //-- void setREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.data.APPType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.data.APPType.class, reader);
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
