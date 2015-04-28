/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbw;

/**
 * Class SbbQysds2008HznsFzjgFpbYwbwDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008HznsFzjgFpbYwbwDescriptor extends com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.descriptors.TaxDocDescriptor {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public SbbQysds2008HznsFzjgFpbYwbwDescriptor() {
        super();
        setExtendsWithoutFlatten(new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.descriptors.TaxDocDescriptor());
        _xmlName = "sbbQysds2008HznsFzjgFpbYwbw";
        _elementDefinition = false;
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _items
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem.class, "_items", (java.lang.String) null, org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                SbbQysds2008HznsFzjgFpbYwbw target = (SbbQysds2008HznsFzjgFpbYwbw) object;
                return target.getSbbQysds2008HznsFzjgFpbYwbwItem();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    SbbQysds2008HznsFzjgFpbYwbw target = (SbbQysds2008HznsFzjgFpbYwbw) object;
                    target.addSbbQysds2008HznsFzjgFpbYwbwItem( (com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    SbbQysds2008HznsFzjgFpbYwbw target = (SbbQysds2008HznsFzjgFpbYwbw) object;
                    target.removeAllSbbQysds2008HznsFzjgFpbYwbwItem();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem();
            }
        };
        desc.setSchemaType("list");
        desc.setComponentType("com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem");
        desc.setHandler(handler);
        desc.setContainer(true);
        desc.setClassDescriptor(new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.descriptors.SbbQysds2008HznsFzjgFpbYwbwItemDescriptor());
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        //-- validation code for: _items
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        if (_identity == null) {
            return super.getIdentity();
        }
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    public java.lang.Class getJavaClass(
    ) {
        return com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbw.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    public java.lang.String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    public java.lang.String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    public java.lang.String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
