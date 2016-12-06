/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpit.ecare.websms.utility.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author mdmizan
 */
public class Utility {

    public String convertXMLFileToString(String fileName)
    {
       try{
           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
           InputStream inputStream = new FileInputStream(new File(fileName));
           org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
           StringWriter stw = new StringWriter();
           Transformer serializer = TransformerFactory.newInstance().newTransformer();
           serializer.transform(new DOMSource(doc), new StreamResult(stw));
           return stw.toString();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
}
