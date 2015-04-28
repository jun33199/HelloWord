/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 汇总申报税务登记信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbDwnsrYwbw extends com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxDoc 
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

    public HzsbDwnsrYwbw() {
        super();
        this._items = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vHzsbDwnsrYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHzsbDwnsrYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem vHzsbDwnsrYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.addElement(vHzsbDwnsrYwbwItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vHzsbDwnsrYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHzsbDwnsrYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem vHzsbDwnsrYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vHzsbDwnsrYwbwItem);
    }

    /**
     * Method enumerateHzsbDwnsrYwbwItem.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem
     * elements
     */
    public java.util.Enumeration enumerateHzsbDwnsrYwbwItem(
    ) {
        return this._items.elements();
    }

    /**
     * Method getHzsbDwnsrYwbwItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem at
     * the given index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem getHzsbDwnsrYwbwItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getHzsbDwnsrYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem) _items.get(index);
    }

    /**
     * Method getHzsbDwnsrYwbwItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem[] getHzsbDwnsrYwbwItem(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem[]) this._items.toArray(array);
    }

    /**
     * Method getHzsbDwnsrYwbwItemCount.
     * 
     * @return the size of this collection
     */
    public int getHzsbDwnsrYwbwItemCount(
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
    public void removeAllHzsbDwnsrYwbwItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeHzsbDwnsrYwbwItem.
     * 
     * @param vHzsbDwnsrYwbwItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeHzsbDwnsrYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem vHzsbDwnsrYwbwItem) {
        boolean removed = _items.remove(vHzsbDwnsrYwbwItem);
        return removed;
    }

    /**
     * Method removeHzsbDwnsrYwbwItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem removeHzsbDwnsrYwbwItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vHzsbDwnsrYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setHzsbDwnsrYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem vHzsbDwnsrYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setHzsbDwnsrYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        this._items.set(index, vHzsbDwnsrYwbwItem);
    }

    /**
     * 
     * 
     * @param vHzsbDwnsrYwbwItemArray
     */
    public void setHzsbDwnsrYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem[] vHzsbDwnsrYwbwItemArray) {
        //-- copy array
        _items.clear();
        
        for (int i = 0; i < vHzsbDwnsrYwbwItemArray.length; i++) {
                this._items.add(vHzsbDwnsrYwbwItemArray[i]);
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxDoc
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxDoc unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxDoc) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbw.class, reader);
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
