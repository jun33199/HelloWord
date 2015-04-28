/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.7</a>, using an XML
 * Schema.
 * $Id$
 */

package com.lscdz.qysds.common.service.qysds.xml.vo.userInfo;

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
     * Field _JBXXList
     */
    @SuppressWarnings("rawtypes")
	private java.util.Vector _JBXXList;


      //----------------/
     //- Constructors -/
    //----------------/

    @SuppressWarnings("rawtypes")
	public APPType() 
     {
        super();
        _JBXXList = new Vector();
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addJBXX
     * 
     * 
     * 
     * @param vJBXX
     */
    @SuppressWarnings("unchecked")
	public void addJBXX(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX vJBXX)
        throws java.lang.IndexOutOfBoundsException
    {
        _JBXXList.addElement(vJBXX);
    } //-- void addJBXX(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) 

    /**
     * Method addJBXX
     * 
     * 
     * 
     * @param index
     * @param vJBXX
     */
    @SuppressWarnings("unchecked")
	public void addJBXX(int index, com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX vJBXX)
        throws java.lang.IndexOutOfBoundsException
    {
        _JBXXList.insertElementAt(vJBXX, index);
    } //-- void addJBXX(int, com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) 

    /**
     * Method enumerateJBXX
     * 
     * 
     * 
     * @return Enumeration
     */
    @SuppressWarnings("rawtypes")
	public java.util.Enumeration enumerateJBXX()
    {
        return _JBXXList.elements();
    } //-- java.util.Enumeration enumerateJBXX() 

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
     * Method getJBXX
     * 
     * 
     * 
     * @param index
     * @return JBXX
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX getJBXX(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _JBXXList.size())) {
            throw new IndexOutOfBoundsException("getJBXX: Index value '"+index+"' not in range [0.."+_JBXXList.size()+ "]");
        }
        
        return (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) _JBXXList.elementAt(index);
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX getJBXX(int) 

    /**
     * Method getJBXX
     * 
     * 
     * 
     * @return JBXX
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX[] getJBXX()
    {
        int size = _JBXXList.size();
        com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX[] mArray = new com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) _JBXXList.elementAt(index);
        }
        return mArray;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX[] getJBXX() 

    /**
     * Method getJBXXCount
     * 
     * 
     * 
     * @return int
     */
    public int getJBXXCount()
    {
        return _JBXXList.size();
    } //-- int getJBXXCount() 

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
     * Method removeAllJBXX
     * 
     */
    public void removeAllJBXX()
    {
        _JBXXList.removeAllElements();
    } //-- void removeAllJBXX() 

    /**
     * Method removeJBXX
     * 
     * 
     * 
     * @param index
     * @return JBXX
     */
    public com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX removeJBXX(int index)
    {
        java.lang.Object obj = _JBXXList.elementAt(index);
        _JBXXList.removeElementAt(index);
        return (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) obj;
    } //-- com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX removeJBXX(int) 

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
     * Method setJBXX
     * 
     * 
     * 
     * @param index
     * @param vJBXX
     */
    @SuppressWarnings("unchecked")
	public void setJBXX(int index, com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX vJBXX)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _JBXXList.size())) {
            throw new IndexOutOfBoundsException("setJBXX: Index value '"+index+"' not in range [0.."+_JBXXList.size()+ "]");
        }
        _JBXXList.setElementAt(vJBXX, index);
    } //-- void setJBXX(int, com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) 

    /**
     * Method setJBXX
     * 
     * 
     * 
     * @param JBXXArray
     */
    @SuppressWarnings("unchecked")
	public void setJBXX(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX[] JBXXArray)
    {
        //-- copy array
        _JBXXList.removeAllElements();
        for (int i = 0; i < JBXXArray.length; i++) {
            _JBXXList.addElement(JBXXArray[i]);
        }
    } //-- void setJBXX(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX) 

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
        return (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPType) Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPType.class, reader);
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
