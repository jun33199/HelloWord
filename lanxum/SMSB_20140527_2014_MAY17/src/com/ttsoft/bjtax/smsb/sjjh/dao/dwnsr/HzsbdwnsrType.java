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
 * Class HzsbdwnsrType.
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 纳税人识别号
     */
    private java.lang.String _nsrsbh;

    /**
     * 税务机关代码
     */
    private java.lang.String _swjgDm;

    /**
     * 录入日期
     */
    private java.lang.String _lrrq;

    /**
     * 修改日期
     */
    private java.lang.String _xgrq;

    /**
     * 纳税人基本信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrxx _nsrxx;

    /**
     * 纳税人扩展信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrkzxx _nsrkzxx;

    /**
     * 注册资本投资总额
     */
    private java.util.Vector _zczbtzzeList;

    /**
     * 投资方信息
     */
    private java.util.Vector _tzfxxList;

    /**
     * 总机构信息
     */
    private com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zjgxx _zjgxx;

    /**
     * 分支机构信息
     */
    private java.util.Vector _fzjgxxList;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrType() {
        super();
        this._zczbtzzeList = new java.util.Vector();
        this._tzfxxList = new java.util.Vector();
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
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx vFzjgxx)
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
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx vFzjgxx)
    throws java.lang.IndexOutOfBoundsException {
        this._fzjgxxList.add(index, vFzjgxx);
    }

    /**
     * 
     * 
     * @param vTzfxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTzfxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx vTzfxx)
    throws java.lang.IndexOutOfBoundsException {
        this._tzfxxList.addElement(vTzfxx);
    }

    /**
     * 
     * 
     * @param index
     * @param vTzfxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTzfxx(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx vTzfxx)
    throws java.lang.IndexOutOfBoundsException {
        this._tzfxxList.add(index, vTzfxx);
    }

    /**
     * 
     * 
     * @param vZczbtzze
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZczbtzze(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze vZczbtzze)
    throws java.lang.IndexOutOfBoundsException {
        this._zczbtzzeList.addElement(vZczbtzze);
    }

    /**
     * 
     * 
     * @param index
     * @param vZczbtzze
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZczbtzze(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze vZczbtzze)
    throws java.lang.IndexOutOfBoundsException {
        this._zczbtzzeList.add(index, vZczbtzze);
    }

    /**
     * Method enumerateFzjgxx.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx elements
     */
    public java.util.Enumeration enumerateFzjgxx(
    ) {
        return this._fzjgxxList.elements();
    }

    /**
     * Method enumerateTzfxx.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx elements
     */
    public java.util.Enumeration enumerateTzfxx(
    ) {
        return this._tzfxxList.elements();
    }

    /**
     * Method enumerateZczbtzze.
     * 
     * @return an Enumeration over all
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze elements
     */
    public java.util.Enumeration enumerateZczbtzze(
    ) {
        return this._zczbtzzeList.elements();
    }

    /**
     * Method getFzjgxx.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx at the given inde
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx getFzjgxx(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fzjgxxList.size()) {
            throw new IndexOutOfBoundsException("getFzjgxx: Index value '" + index + "' not in range [0.." + (this._fzjgxxList.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx) _fzjgxxList.get(index);
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
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx[] getFzjgxx(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx[]) this._fzjgxxList.toArray(array);
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
     * Returns the value of field 'lrrq'. The field 'lrrq' has the
     * following description: 录入日期
     * 
     * @return the value of field 'Lrrq'.
     */
    public java.lang.String getLrrq(
    ) {
        return this._lrrq;
    }

    /**
     * Returns the value of field 'nsrkzxx'. The field 'nsrkzxx'
     * has the following description: 纳税人扩展信息
     * 
     * @return the value of field 'Nsrkzxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrkzxx getNsrkzxx(
    ) {
        return this._nsrkzxx;
    }

    /**
     * Returns the value of field 'nsrsbh'. The field 'nsrsbh' has
     * the following description: 纳税人识别号
     * 
     * @return the value of field 'Nsrsbh'.
     */
    public java.lang.String getNsrsbh(
    ) {
        return this._nsrsbh;
    }

    /**
     * Returns the value of field 'nsrxx'. The field 'nsrxx' has
     * the following description: 纳税人基本信息
     * 
     * @return the value of field 'Nsrxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrxx getNsrxx(
    ) {
        return this._nsrxx;
    }

    /**
     * Returns the value of field 'swjgDm'. The field 'swjgDm' has
     * the following description: 税务机关代码
     * 
     * @return the value of field 'SwjgDm'.
     */
    public java.lang.String getSwjgDm(
    ) {
        return this._swjgDm;
    }

    /**
     * Method getTzfxx.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx at the given index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx getTzfxx(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tzfxxList.size()) {
            throw new IndexOutOfBoundsException("getTzfxx: Index value '" + index + "' not in range [0.." + (this._tzfxxList.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx) _tzfxxList.get(index);
    }

    /**
     * Method getTzfxx.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx[] getTzfxx(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx[]) this._tzfxxList.toArray(array);
    }

    /**
     * Method getTzfxxCount.
     * 
     * @return the size of this collection
     */
    public int getTzfxxCount(
    ) {
        return this._tzfxxList.size();
    }

    /**
     * Returns the value of field 'xgrq'. The field 'xgrq' has the
     * following description: 修改日期
     * 
     * @return the value of field 'Xgrq'.
     */
    public java.lang.String getXgrq(
    ) {
        return this._xgrq;
    }

    /**
     * Method getZczbtzze.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze at the given
     * index
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze getZczbtzze(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._zczbtzzeList.size()) {
            throw new IndexOutOfBoundsException("getZczbtzze: Index value '" + index + "' not in range [0.." + (this._zczbtzzeList.size() - 1) + "]");
        }
        
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze) _zczbtzzeList.get(index);
    }

    /**
     * Method getZczbtzze.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze[] getZczbtzze(
    ) {
        com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze[] array = new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze[0];
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze[]) this._zczbtzzeList.toArray(array);
    }

    /**
     * Method getZczbtzzeCount.
     * 
     * @return the size of this collection
     */
    public int getZczbtzzeCount(
    ) {
        return this._zczbtzzeList.size();
    }

    /**
     * Returns the value of field 'zjgxx'. The field 'zjgxx' has
     * the following description: 总机构信息
     * 
     * @return the value of field 'Zjgxx'.
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zjgxx getZjgxx(
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
     */
    public void removeAllTzfxx(
    ) {
        this._tzfxxList.clear();
    }

    /**
     */
    public void removeAllZczbtzze(
    ) {
        this._zczbtzzeList.clear();
    }

    /**
     * Method removeFzjgxx.
     * 
     * @param vFzjgxx
     * @return true if the object was removed from the collection.
     */
    public boolean removeFzjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx vFzjgxx) {
        boolean removed = _fzjgxxList.remove(vFzjgxx);
        return removed;
    }

    /**
     * Method removeFzjgxxAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx removeFzjgxxAt(
            final int index) {
        java.lang.Object obj = this._fzjgxxList.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx) obj;
    }

    /**
     * Method removeTzfxx.
     * 
     * @param vTzfxx
     * @return true if the object was removed from the collection.
     */
    public boolean removeTzfxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx vTzfxx) {
        boolean removed = _tzfxxList.remove(vTzfxx);
        return removed;
    }

    /**
     * Method removeTzfxxAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx removeTzfxxAt(
            final int index) {
        java.lang.Object obj = this._tzfxxList.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx) obj;
    }

    /**
     * Method removeZczbtzze.
     * 
     * @param vZczbtzze
     * @return true if the object was removed from the collection.
     */
    public boolean removeZczbtzze(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze vZczbtzze) {
        boolean removed = _zczbtzzeList.remove(vZczbtzze);
        return removed;
    }

    /**
     * Method removeZczbtzzeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze removeZczbtzzeAt(
            final int index) {
        java.lang.Object obj = this._zczbtzzeList.remove(index);
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze) obj;
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
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx vFzjgxx)
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
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx[] vFzjgxxArray) {
        //-- copy array
        _fzjgxxList.clear();
        
        for (int i = 0; i < vFzjgxxArray.length; i++) {
                this._fzjgxxList.add(vFzjgxxArray[i]);
        }
    }

    /**
     * Sets the value of field 'lrrq'. The field 'lrrq' has the
     * following description: 录入日期
     * 
     * @param lrrq the value of field 'lrrq'.
     */
    public void setLrrq(
            final java.lang.String lrrq) {
        this._lrrq = lrrq;
    }

    /**
     * Sets the value of field 'nsrkzxx'. The field 'nsrkzxx' has
     * the following description: 纳税人扩展信息
     * 
     * @param nsrkzxx the value of field 'nsrkzxx'.
     */
    public void setNsrkzxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrkzxx nsrkzxx) {
        this._nsrkzxx = nsrkzxx;
    }

    /**
     * Sets the value of field 'nsrsbh'. The field 'nsrsbh' has the
     * following description: 纳税人识别号
     * 
     * @param nsrsbh the value of field 'nsrsbh'.
     */
    public void setNsrsbh(
            final java.lang.String nsrsbh) {
        this._nsrsbh = nsrsbh;
    }

    /**
     * Sets the value of field 'nsrxx'. The field 'nsrxx' has the
     * following description: 纳税人基本信息
     * 
     * @param nsrxx the value of field 'nsrxx'.
     */
    public void setNsrxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrxx nsrxx) {
        this._nsrxx = nsrxx;
    }

    /**
     * Sets the value of field 'swjgDm'. The field 'swjgDm' has the
     * following description: 税务机关代码
     * 
     * @param swjgDm the value of field 'swjgDm'.
     */
    public void setSwjgDm(
            final java.lang.String swjgDm) {
        this._swjgDm = swjgDm;
    }

    /**
     * 
     * 
     * @param index
     * @param vTzfxx
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTzfxx(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx vTzfxx)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tzfxxList.size()) {
            throw new IndexOutOfBoundsException("setTzfxx: Index value '" + index + "' not in range [0.." + (this._tzfxxList.size() - 1) + "]");
        }
        
        this._tzfxxList.set(index, vTzfxx);
    }

    /**
     * 
     * 
     * @param vTzfxxArray
     */
    public void setTzfxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx[] vTzfxxArray) {
        //-- copy array
        _tzfxxList.clear();
        
        for (int i = 0; i < vTzfxxArray.length; i++) {
                this._tzfxxList.add(vTzfxxArray[i]);
        }
    }

    /**
     * Sets the value of field 'xgrq'. The field 'xgrq' has the
     * following description: 修改日期
     * 
     * @param xgrq the value of field 'xgrq'.
     */
    public void setXgrq(
            final java.lang.String xgrq) {
        this._xgrq = xgrq;
    }

    /**
     * 
     * 
     * @param index
     * @param vZczbtzze
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setZczbtzze(
            final int index,
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze vZczbtzze)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._zczbtzzeList.size()) {
            throw new IndexOutOfBoundsException("setZczbtzze: Index value '" + index + "' not in range [0.." + (this._zczbtzzeList.size() - 1) + "]");
        }
        
        this._zczbtzzeList.set(index, vZczbtzze);
    }

    /**
     * 
     * 
     * @param vZczbtzzeArray
     */
    public void setZczbtzze(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze[] vZczbtzzeArray) {
        //-- copy array
        _zczbtzzeList.clear();
        
        for (int i = 0; i < vZczbtzzeArray.length; i++) {
                this._zczbtzzeList.add(vZczbtzzeArray[i]);
        }
    }

    /**
     * Sets the value of field 'zjgxx'. The field 'zjgxx' has the
     * following description: 总机构信息
     * 
     * @param zjgxx the value of field 'zjgxx'.
     */
    public void setZjgxx(
            final com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zjgxx zjgxx) {
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
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrType
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrType) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrType.class, reader);
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
