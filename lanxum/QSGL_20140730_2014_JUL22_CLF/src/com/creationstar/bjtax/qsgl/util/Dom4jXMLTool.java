package com.creationstar.bjtax.qsgl.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jXMLTool {
    /** XMLÎÄµµ */
    private Document document = null;
    public Dom4jXMLTool() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void openXML(String XMLString) {
        try {
            this.document = DocumentHelper.parseText(XMLString);
            //System.out.println("openXML() successful ...");
        } catch (Exception e) {
            System.out.println("openXML() Exception:" + e.getMessage());
        }
    }

    public void appendFinalNode(String xpath, String NodeName,
                                String[] attrName, String[] attrValue,
                                String NodeValue) {
        //System.out.println(document.asXML());
        List nodeList = document.selectNodes(xpath);
        Element node = (Element) nodeList.get(0);
        Element newEle = node.addElement(NodeName);
        newEle.setText(NodeValue);
        for (int i = 0; i < attrName.length; i++) {
            newEle.addAttribute(attrName[i], attrValue[i]);
        }
    }

    /**
     * Êä³öXML×Ö·û´®
     *
     * @return
     */
    public String toXMLString() {
        return document.asXML();
    }

}
