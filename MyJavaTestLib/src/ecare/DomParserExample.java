/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare;

/**
 *
 * @author monjurul.k
 */
import ecare.Employee;
import ecare.CampaignHistory;
import ecare.CampaignStatus;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DomParserExample {

    //No generics
    List myEmpls;
    List<CampaignStatus> campaignStatusList;
    Document dom;

    public DomParserExample() {
        //create a list to hold the employee objects
        myEmpls = new ArrayList();
    }

    public void runExample() {

//        parseXmlForCampaignStatus();
//        parseXmlForCampaignHistory();
        parseXmlForImsi();

//        //parse the xml file and get the dom object
//        parseXmlFile();
//
//        //get each employee element and create a Employee object
//        parseDocument();
//
//        //Iterate through the list and print the data
//        printData();

    }

    private void parseXmlFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            String xmlStr = "<Office><Employee>		<Name>Imam</Name>		<Type>Parmanent</Type>		<Id>3895</Id>		<Age>32</Age>	</Employee>	<Employee>		<Name>Mizan</Name>" + "<Type>Parmanent</Type><Id>3001</Id>	<Age>32</Age>	</Employee>	<Employee>		<Name>Monowar</Name>		<Type>Parmanent</Type>" + "<Id>2012</Id>	<Age>42</Age>	</Employee></Office>";



            //parse using builder to get DOM representation of the XML file
//			dom = db.parse("C:\\MyJavaTestLib\\Input\\input.xml");
//             InputSource is = new InputSource(new StringReader(xmlStr));

            InputSource is = new InputSource(new StringReader(xmlStr));
            dom = db.parse(is);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseDocument() {
        //get the root elememt
        Element docEle = dom.getDocumentElement();

        //get a nodelist of <employee> elements
        NodeList nl = docEle.getElementsByTagName("Employee");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                //get the employee element
                Element el = (Element) nl.item(i);

                //get the Employee object
                Employee e = getEmployee(el);

                //add it to list
                myEmpls.add(e);
            }
        }
    }

    /**
     * I take an employee element and read the values in, create
     * an Employee object and return it
     * @param empEl
     * @return
     */
    private Employee getEmployee(Element empEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        String name = getTextValue(empEl, "Name");
        int id = getIntValue(empEl, "Id");
        int age = getIntValue(empEl, "Age");

        String type = empEl.getAttribute("type");

        //Create a new Employee with the value read from the xml nodes
        Employee e = new Employee(name, id, age, type);
        System.out.println(" Employee name -- " + e.getName());
        System.out.println(" Employee age -- " + e.getAge());
        return e;
    }

    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is name I will return John
     * @param ele
     * @param tagName
     * @return
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            try {
                textVal = el.getFirstChild().getNodeValue();
            } catch (NullPointerException nex) {
                System.out.println("MyOfferServiceMng:: getTextValue() --No data in the element");
                textVal = "-";
            }

        }
        return textVal;
    }

    /**
     * Calls getTextValue and returns a int value
     * @param ele
     * @param tagName
     * @return
     */
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    /**
     * Iterate through the list and print the
     * content to console
     */
    private void printData() {

        System.out.println("No of Employees '" + myEmpls.size() + "'.");

        Iterator it = myEmpls.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public static void main(String[] args) {
        //create an instance
        DomParserExample dpe = new DomParserExample();

        //call run example
        dpe.runExample();
    }

    public void parseXmlForCampaignStatus() {
        try {
//            String xmlRecords = "<data><Employee><Name>A</Name>" + "<title>Manager</title></Employee><Employee>	<Name>Imam</Name><title>S,Eng</title></Employee>	<Employee>		<Name>Mizan</Name>" + "<title>Manager</title></Employee>	<Employee>		<Name>Monowar</Name>	<title>Spc</title>	</Employee></data>";
            String responseXml = "  <GetFusionCampaignStatusResponse> <FusionCampaignStatusResponseItem><Campaign_name>1304TE01</Campaign_name><Campaign_id>1304TE01</Campaign_id>" +
                    "<Campaign_Type>BTL</Campaign_Type><Campaign_Short_Description>short desc</Campaign_Short_Description>" +
                    "<Opt_in>Yes</Opt_in><Opt_in_Date>optinDate</Opt_in_Date><Status>Eligible</Status><Campaign_Start_Date>startDate</Campaign_Start_Date>" +
                    "<Campaign_Expiry_Date>endDate</Campaign_Expiry_Date><Charge_Amount>charge</Charge_Amount></FusionCampaignStatusResponseItem>" +
                    "<FusionCampaignStatusResponseItem><Campaign_name>1304TE01</Campaign_name><Campaign_id>1304TE01</Campaign_id><Campaign_Type>BTL</Campaign_Type>" +
                    "<Campaign_Short_Description>short desc</Campaign_Short_Description><Opt_in>Yes</Opt_in><Opt_in_Date>optinDate</Opt_in_Date><Status>Eligible</Status>" +
                    "<Campaign_Start_Date>startDate</Campaign_Start_Date><Campaign_Expiry_Date>endDate</Campaign_Expiry_Date><Charge_Amount>charge</Charge_Amount></FusionCampaignStatusResponseItem></GetFusionCampaignStatusResponse>";
            List<CampaignStatus> campaignStatusList = new ArrayList<CampaignStatus>();

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();

//            is.setCharacterStream(new StringReader(xmlRecords));
            is.setCharacterStream(new StringReader(responseXml));

            Document doc = db.parse(is);


            NodeList nodes = doc.getElementsByTagName("FusionCampaignStatusResponseItem");

            for (int i = 0; i < nodes.getLength(); i++) {
                CampaignStatus tempObj = new CampaignStatus();
                Element element = (Element) nodes.item(i);

                tempObj = getCampaignStatus(element);
                campaignStatusList.add(tempObj);
                System.out.println("new campaign added from xml string");
            }
//            return campaignStatusList;
        } catch (SAXException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return null;

    }

    private CampaignStatus getCampaignStatus(Element campaignEl) {

        //for each <FusionCampaignStatusResponseItem> element get text  values of its component and assign it to the object

        CampaignStatus camStatusObj = new CampaignStatus();

        camStatusObj.setCampaign_name(getTextValue(campaignEl, "Campaign_name"));
        camStatusObj.setCampaign_id(getTextValue(campaignEl, "Campaign_id"));
        camStatusObj.setCampaign_type(getTextValue(campaignEl, "Campaign_Type"));
        camStatusObj.setCampaign_Short_Description(getTextValue(campaignEl, "Campaign_Short_Description"));
        camStatusObj.setOpt_in(getTextValue(campaignEl, "Opt_in"));
        camStatusObj.setOpt_in_date(getTextValue(campaignEl, "Opt_in_Date"));
        camStatusObj.setStatus(getTextValue(campaignEl, "Status"));
        camStatusObj.setStart_date(getTextValue(campaignEl, "Campaign_Start_Date"));
        camStatusObj.setCharged_amount(getTextValue(campaignEl, "Charge_Amount"));


        System.out.println(" Campaign  name -- " + camStatusObj.getCampaign_name());
        System.out.println(" Campaign Desc-- " + camStatusObj.getCampaign_Short_Description());
        System.out.println(" Campaign type-- " + camStatusObj.getCampaign_type());
        return camStatusObj;
    }

    public List<CampaignHistory> parseXmlForCampaignHistory() {
        String responseXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soapenv:Header xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\"/>" +
                "<soapenv:Body xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\"><GetFusionCampaignHistoryResponse>" +
                "<FusionCampaignHistoryResponseItem>" +
                "<Campaign_name>1405TE17</Campaign_name>" +
                "<Campaign_id>1405TE17-A1</Campaign_id>" +
                "<Campaign_Type>BTL</Campaign_Type>" +
                "<Opt_in>Opt-In</Opt_in>" +
                "<Opt_in_Date/>" +
                "<Campaign_Start_Date>2013-05-28 23:57:08</Campaign_Start_Date>" +
                "<Campaign_Expiry_Date>2013-05-29 23:58:00</Campaign_Expiry_Date>" +
                "<Charge_Amount/>" +
                "</FusionCampaignHistoryResponseItem>" +
                "</GetFusionCampaignHistoryResponse>" +
                " </soapenv:Body> </soapenv:Envelope><?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soapenv:Header xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\"/>" +
                "<soapenv:Body xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\">" +
                "<GetFusionCampaignHistoryResponse>" +
                "<FusionCampaignHistoryResponseItem>" +
                "<Campaign_name>1405TE17</Campaign_name>" +
                "<Campaign_id>1405TE17-A1</Campaign_id>" +
                "<Campaign_Type>BTL</Campaign_Type>" +
                "<Opt_in>Opt-In</Opt_in>" +
                "<Opt_in_Date/>" +
                "<Campaign_Start_Date>2013-05-28 23:57:08</Campaign_Start_Date>" +
                "<Campaign_Expiry_Date>2013-05-29 23:58:00</Campaign_Expiry_Date>" +
                "<Charge_Amount/>" +
                "</FusionCampaignHistoryResponseItem>" +
                "</GetFusionCampaignHistoryResponse> </soapenv:Body> </soapenv:Envelope>";

        responseXml = responseXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
        responseXml = "<root>" + responseXml + "</root>";


        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soapenv:Header xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\"/><soapenv:Body xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\">" +
                "<GetFusionCampaignHistoryResponse><FusionCampaignHistoryResponseItem><Campaign_name>1405TE17</Campaign_name>" +
                "<Campaign_id>1405TE17-A1</Campaign_id><Campaign_Type>BTL</Campaign_Type><Opt_in>Opt-In</Opt_in><Opt_in_Date/>" +
                "<Campaign_Start_Date>2013-05-28 23:57:08</Campaign_Start_Date><Campaign_Expiry_Date>2013-05-29 23:58:00</Campaign_Expiry_Date>" +
                "<Charge_Amount/></FusionCampaignHistoryResponseItem></GetFusionCampaignHistoryResponse></soapenv:Body></soapenv:Envelope>" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soapenv:Header xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\"/><soapenv:Body xmlns:ns=\"com/gp/lta/FusionCampaign/1.0\">" +
                "<GetFusionCampaignHistoryResponse><FusionCampaignHistoryResponseItem><Campaign_name>1405TE17</Campaign_name>" +
                "<Campaign_id>1405TE17-A1</Campaign_id><Campaign_Type>BTL</Campaign_Type><Opt_in>Opt-In</Opt_in><Opt_in_Date/>" +
                "<Campaign_Start_Date>2013-05-28 23:57:08</Campaign_Start_Date><Campaign_Expiry_Date>2013-05-29 23:58:00</Campaign_Expiry_Date>" +
                "<Charge_Amount/></FusionCampaignHistoryResponseItem></GetFusionCampaignHistoryResponse></soapenv:Body></soapenv:Envelope>";
        try {
//            String xmlRecords = "<data><Employee><Name>A</Name>" + "<title>Manager</title></Employee><Employee>	<Name>Imam</Name><title>S,Eng</title></Employee>	<Employee>		<Name>Mizan</Name>" + "<title>Manager</title></Employee>	<Employee>		<Name>Monowar</Name>	<title>Spc</title>	</Employee></data>";
//            System.out.println("MyOfferServiceMng:: parseXmlForCampaignHistory started xml--  " + responseXml);
            List<CampaignHistory> campaignHistList = new ArrayList<CampaignHistory>();

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();
//            System.out.println("parseXml:: xml--" + responseXml);
//            is.setCharacterStream(new StringReader(xmlRecords));
            System.out.println(" B4 setCharacterStream ");
            is.setCharacterStream(new StringReader(responseXml));
            System.out.println(" B4 parse ");
            Document doc = db.parse(is);


            NodeList nodes = doc.getElementsByTagName("FusionCampaignHistoryResponseItem");

            for (int i = 0; i < nodes.getLength(); i++) {

                CampaignHistory tempObj = new CampaignHistory();
                Element element = (Element) nodes.item(i);

                tempObj = getCampaignHistory(element);
                campaignHistList.add(tempObj);
                System.out.println("new campaign added from xml string");

            }
            return campaignHistList;
        } catch (SAXException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void parseXmlForImsi() {
        try {
            String responseXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    "<soapenv:Header xmlns:ns=\"com/gp/lta/CustomerManagement/1.0\"/>" +
                    "<soapenv:Body xmlns:ns=\"com/gp/lta/CustomerManagement/1.0\">" +
                    "<ns:GetSimImsiResponse><ns:SIMNumber>898801010630013767</ns:SIMNumber>" +
                    "<ns:IMSINumber>470010630013767</ns:IMSINumber></ns:GetSimImsiResponse></soapenv:Body></soapenv:Envelope>";
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();

//            is.setCharacterStream(new StringReader(xmlRecords));
            is.setCharacterStream(new StringReader(responseXml));

            Document doc = db.parse(is);

         NodeList nodes = doc.getElementsByTagName("ns:GetSimImsiResponse");
         String imsi="";
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                imsi = getTextValue(element, "ns:IMSINumber");

                System.out.println("imsis " +imsi);

            }



        } catch (SAXException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DomParserExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private CampaignHistory getCampaignHistory(Element campaignEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        CampaignHistory camHistObj = new CampaignHistory();

        camHistObj.setCampaign_name(getTextValue(campaignEl, "Campaign_name"));
        camHistObj.setCampaign_id(getTextValue(campaignEl, "Campaign_id"));
        camHistObj.setCampaign_type(getTextValue(campaignEl, "Campaign_Type"));
        camHistObj.setOpt_in(getTextValue(campaignEl, "Opt_in"));
        camHistObj.setOpt_in_date(getTextValue(campaignEl, "Opt_in_Date"));
        camHistObj.setStart_date(getTextValue(campaignEl, "Campaign_Start_Date"));
        camHistObj.setExpiry_date(getTextValue(campaignEl, "Campaign_Expiry_Date"));
        camHistObj.setCharged_amount(getTextValue(campaignEl, "Charge_Amount"));
        camHistObj.setOfferValidity(camHistObj.getStart_date() + " to " + camHistObj.getExpiry_date());
        System.out.println(" Campaign_name -- " + camHistObj.getCampaign_name());
        System.out.println(" Campaign_Type -- " + camHistObj.getCampaign_type());
        return camHistObj;
    }
}