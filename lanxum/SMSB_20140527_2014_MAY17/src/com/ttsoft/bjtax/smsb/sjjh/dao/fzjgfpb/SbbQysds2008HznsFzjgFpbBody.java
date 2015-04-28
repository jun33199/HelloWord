/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 2008版企业所得税汇总纳税分支机构分配表表体
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbBody implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 总机构信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Zjgxx _zjgxx;

    /**
     * 分支机构信息
     */
    private java.util.Vector _fzjgxxList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbBody() {
        super();
        this._fzjgxxList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFzjgxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx vFzjgxx)
    throws java.lang.IndexOutOfBoundsException {
        this._fzjgxxList.addElement(vFzjgxx);
    }

    /**
     * 
     * 
     * @param index
     * @param vFzjgxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFzjgxx(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx vFzjgxx)
    throws java.lang.IndexOutOfBoundsException {
        this._fzjgxxList.add(index, vFzjgxx);
    }

    /**
     * Method enumerateFzjgxx.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx elements
     */
    public java.util.Enumeration enumerateFzjgxx(
    ) {
        return this._fzjgxxList.elements();
    }

    /**
     * Method getFzjgxx.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx at the given
     * index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx getFzjgxx(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fzjgxxList.size()) {
            throw new IndexOutOfBoundsException("getFzjgxx: Index value '" + index + "' not in range [0.." + (this._fzjgxxList.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx) _fzjgxxList.get(index);
    }

    /**
     * Method getFzjgxx.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx[] getFzjgxx(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx[]) this._fzjgxxList.toArray(array);
    }

    /**
     * Method getFzjgxxCount.
     * 
     * @return the size of this collection
     */
    public int getFzjgxxCount(
    ) {
        return this._fzjgxxList.size();
    }

    /**
     * Returns the value of field 'zjgxx'. The field 'zjgxx' has
     * the following description: 总机构信息
     * 
     * @return the value of field 'Zjgxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Zjgxx getZjgxx(
    ) {
        return this._zjgxx;
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllFzjgxx(
    ) {
        this._fzjgxxList.clear();
    }

    /**
     * Method removeFzjgxx.
     * 
     * @param vFzjgxx
     * @return true if the object was removed from the collection.
     */
    public boolean removeFzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx vFzjgxx) {
        boolean removed = _fzjgxxList.remove(vFzjgxx);
        return removed;
    }

    /**
     * Method removeFzjgxxAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx removeFzjgxxAt(
            final int index) {
        java.lang.Object obj = this._fzjgxxList.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFzjgxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFzjgxx(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx vFzjgxx)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fzjgxxList.size()) {
            throw new IndexOutOfBoundsException("setFzjgxx: Index value '" + index + "' not in range [0.." + (this._fzjgxxList.size() - 1) + "]");
        }
        
        this._fzjgxxList.set(index, vFzjgxx);
    }

    /**
     * 
     * 
     * @param vFzjgxxArray
     */
    public void setFzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx[] vFzjgxxArray) {
        //-- copy array
        _fzjgxxList.clear();
        
        for (int i = 0; i < vFzjgxxArray.length; i++) {
                this._fzjgxxList.add(vFzjgxxArray[i]);
        }
    }

    /**
     * Sets the value of field 'zjgxx'. The field 'zjgxx' has the
     * following description: 总机构信息
     * 
     * @param zjgxx the value of field 'zjgxx'.
     */
    public void setZjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Zjgxx zjgxx) {
        this._zjgxx = zjgxx;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbBody
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbBody unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbBody) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbBody.class, reader);
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
