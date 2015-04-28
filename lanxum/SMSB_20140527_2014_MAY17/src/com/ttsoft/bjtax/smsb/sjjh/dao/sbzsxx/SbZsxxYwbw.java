/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 申报征收信息报文
 * 
 * @version $Revision$ $Date$
 */
public class SbZsxxYwbw extends com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxDoc 
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

    public SbZsxxYwbw() {
        super();
        this._items = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSbZsxxYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSbZsxxYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem vSbZsxxYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.addElement(vSbZsxxYwbwItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vSbZsxxYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSbZsxxYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem vSbZsxxYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vSbZsxxYwbwItem);
    }

    /**
     * Method enumerateSbZsxxYwbwItem.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem elements
     */
    public java.util.Enumeration enumerateSbZsxxYwbwItem(
    ) {
        return this._items.elements();
    }

    /**
     * Method getSbZsxxYwbwItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem at the
     * given index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem getSbZsxxYwbwItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getSbZsxxYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem) _items.get(index);
    }

    /**
     * Method getSbZsxxYwbwItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem[] getSbZsxxYwbwItem(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem[]) this._items.toArray(array);
    }

    /**
     * Method getSbZsxxYwbwItemCount.
     * 
     * @return the size of this collection
     */
    public int getSbZsxxYwbwItemCount(
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
    public void removeAllSbZsxxYwbwItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeSbZsxxYwbwItem.
     * 
     * @param vSbZsxxYwbwItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeSbZsxxYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem vSbZsxxYwbwItem) {
        boolean removed = _items.remove(vSbZsxxYwbwItem);
        return removed;
    }

    /**
     * Method removeSbZsxxYwbwItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem removeSbZsxxYwbwItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSbZsxxYwbwItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSbZsxxYwbwItem(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem vSbZsxxYwbwItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setSbZsxxYwbwItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        this._items.set(index, vSbZsxxYwbwItem);
    }

    /**
     * 
     * 
     * @param vSbZsxxYwbwItemArray
     */
    public void setSbZsxxYwbwItem(
            final com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem[] vSbZsxxYwbwItemArray) {
        //-- copy array
        _items.clear();
        
        for (int i = 0; i < vSbZsxxYwbwItemArray.length; i++) {
                this._items.add(vSbZsxxYwbwItemArray[i]);
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxDoc
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxDoc unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxDoc) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbw.class, reader);
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
