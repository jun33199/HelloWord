/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.jbal;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 2008版企业所得税月(季)度申报表A类业务报文
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdAYwbw extends com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxDoc 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.Vector _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008JdAYwbw() {
        super();
        this._items = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSbbQysds2008JdAYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSbbQysds2008JdAYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem vSbbQysds2008JdAYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.addElement(vSbbQysds2008JdAYwbwItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vSbbQysds2008JdAYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSbbQysds2008JdAYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem vSbbQysds2008JdAYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vSbbQysds2008JdAYwbwItem);
    }

    /**
     * Method enumerateSbbQysds2008JdAYwbwItem.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem
     * elements
     */
    public java.util.Enumeration enumerateSbbQysds2008JdAYwbwItem(
    ) {
        return this._items.elements();
    }

    /**
     * Method getSbbQysds2008JdAYwbwItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem
     * at the given index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem getSbbQysds2008JdAYwbwItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getSbbQysds2008JdAYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem) _items.get(index);
    }

    /**
     * Method getSbbQysds2008JdAYwbwItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem[] getSbbQysds2008JdAYwbwItem(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem[]) this._items.toArray(array);
    }

    /**
     * Method getSbbQysds2008JdAYwbwItemCount.
     * 
     * @return the size of this collection
     */
    public int getSbbQysds2008JdAYwbwItemCount(
    ) {
        return this._items.size();
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
    public void removeAllSbbQysds2008JdAYwbwItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeSbbQysds2008JdAYwbwItem.
     * 
     * @param vSbbQysds2008JdAYwbwItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeSbbQysds2008JdAYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem vSbbQysds2008JdAYwbwItem) {
        boolean removed = _items.remove(vSbbQysds2008JdAYwbwItem);
        return removed;
    }

    /**
     * Method removeSbbQysds2008JdAYwbwItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem removeSbbQysds2008JdAYwbwItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSbbQysds2008JdAYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSbbQysds2008JdAYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem vSbbQysds2008JdAYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setSbbQysds2008JdAYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        this._items.set(index, vSbbQysds2008JdAYwbwItem);
    }

    /**
     * 
     * 
     * @param vSbbQysds2008JdAYwbwItemArray
     */
    public void setSbbQysds2008JdAYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem[] vSbbQysds2008JdAYwbwItemArray) {
        //-- copy array
        _items.clear();
        
        for (int i = 0; i < vSbbQysds2008JdAYwbwItemArray.length; i++) {
                this._items.add(vSbbQysds2008JdAYwbwItemArray[i]);
        }
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxDoc
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxDoc unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxDoc) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbw.class, reader);
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
