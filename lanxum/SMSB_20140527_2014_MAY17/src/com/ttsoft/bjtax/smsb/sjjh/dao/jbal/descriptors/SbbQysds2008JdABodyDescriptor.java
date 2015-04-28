/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.jbal.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody;

/**
 * Class SbbQysds2008JdABodyDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class SbbQysds2008JdABodyDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


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

    public SbbQysds2008JdABodyDescriptor() {
        super();
        _nsURI = "http://www.chinatax.gov.cn/dataspec/";
        _xmlName = "sbbQysds2008JdABody";
        _elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _nsrJsYj
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj.class, "_nsrJsYj", "nsrJsYj", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                return target.getNsrJsYj();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                    target.setNsrJsYj( (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj();
            }
        };
        desc.setSchemaType("com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.chinatax.gov.cn/dataspec/");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        //-- validation code for: _nsrJsYj
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _sndPjeYj
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj.class, "_sndPjeYj", "sndPjeYj", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                return target.getSndPjeYj();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                    target.setSndPjeYj( (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj();
            }
        };
        desc.setSchemaType("com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.chinatax.gov.cn/dataspec/");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        //-- validation code for: _sndPjeYj
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _swjgQdfsYj
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj.class, "_swjgQdfsYj", "swjgQdfsYj", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                return target.getSwjgQdfsYj();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                    target.setSwjgQdfsYj( (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj();
            }
        };
        desc.setSchemaType("com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.chinatax.gov.cn/dataspec/");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        //-- validation code for: _swjgQdfsYj
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _zfzjg
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg.class, "_zfzjg", "zfzjg", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                return target.getZfzjg();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    SbbQysds2008JdABody target = (SbbQysds2008JdABody) object;
                    target.setZfzjg( (com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg();
            }
        };
        desc.setSchemaType("com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.chinatax.gov.cn/dataspec/");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        //-- validation code for: _zfzjg
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
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
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    public java.lang.Class getJavaClass(
    ) {
        return com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdABody.class;
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
