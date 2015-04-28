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
 * Class APPType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class APPType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _AID
     */
    private java.lang.String _AID;

    /**
     * Field _NAME
     */
    private java.lang.String _NAME;

    /**
     * Field _VERSION
     */
    private java.lang.String _VERSION;

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
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.APPType()


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
	public void addREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        _REPORTList.addElement(vREPORT);
    } //-- void addREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) 

    /**
     * Method addREPORT
     * 
     * 
     * 
     * @param index
     * @param vREPORT
     */
    @SuppressWarnings("unchecked")
	public void addREPORT(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        _REPORTList.insertElementAt(vREPORT, index);
    } //-- void addREPORT(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) 

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
     * Method getREPORT
     * 
     * 
     * 
     * @param index
     * @return REPORT
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT getREPORT(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _REPORTList.size())) {
            throw new IndexOutOfBoundsException("getREPORT: Index value '"+index+"' not in range [0.."+_REPORTList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) _REPORTList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT getREPORT(int) 

    /**
     * Method getREPORT
     * 
     * 
     * 
     * @return REPORT
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT[] getREPORT()
    {
        int size = _REPORTList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) _REPORTList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT[] getREPORT() 

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
     * Returns the value of field 'VERSION'.
     * 
     * @return String
     * @return the value of field 'VERSION'.
     */
    public java.lang.String getVERSION()
    {
        return this._VERSION;
    } //-- java.lang.String getVERSION() 

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
    public com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT removeREPORT(int index)
    {
        java.lang.Object obj = _REPORTList.elementAt(index);
        _REPORTList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT removeREPORT(int) 

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
     * Sets the value of field 'AID'.
     * 
     * @param AID the value of field 'AID'.
     */
    public void setAID(java.lang.String AID)
    {
        this._AID = AID;
    } //-- void setAID(java.lang.String) 

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
     * Method setREPORT
     * 
     * 
     * 
     * @param index
     * @param vREPORT
     */
    @SuppressWarnings("unchecked")
	public void setREPORT(int index, com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT vREPORT)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _REPORTList.size())) {
            throw new IndexOutOfBoundsException("setREPORT: Index value '"+index+"' not in range [0.."+_REPORTList.size()+ "]");
        }
        _REPORTList.setElementAt(vREPORT, index);
    } //-- void setREPORT(int, com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) 

    /**
     * Method setREPORT
     * 
     * 
     * 
     * @param REPORTArray
     */
    @SuppressWarnings("unchecked")
	public void setREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT[] REPORTArray)
    {
        //-- copy array
        _REPORTList.removeAllElements();
        for (int i = 0; i < REPORTArray.length; i++) {
            _REPORTList.addElement(REPORTArray[i]);
        }
    } //-- void setREPORT(com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT) 

    /**
     * Sets the value of field 'VERSION'.
     * 
     * @param VERSION the value of field 'VERSION'.
     */
    public void setVERSION(java.lang.String VERSION)
    {
        this._VERSION = VERSION;
    } //-- void setVERSION(java.lang.String) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPType.class, reader);
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
