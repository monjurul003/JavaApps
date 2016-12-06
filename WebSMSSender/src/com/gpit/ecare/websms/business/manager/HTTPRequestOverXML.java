/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.ecare.websms.business.manager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 *
 * @author mdmizan
 */
public class HTTPRequestOverXML {

    /**
     * @param args
     */
    public String[] executeHTTPRequest(String strXMLMsg) throws Exception {

        // Get target URL
        //	    String strURL = "http://10.10.15.208:5995/services?";

        String strURL = "http://10.10.15.208:6991/unirouter";

        String[] response = new String[3];

        // Prepare HTTP post
        PostMethod post = new PostMethod(strURL);

        // Request content will be retrieved directly
        // from the input stream
        // Per default, the request content needs to be buffered
        // in order to determine its length.
        // Request body buffering can be avoided when
        // content length is explicitly specified

        //post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));
        //post.setRequestEntity(new StringRequestEntity(utility.convertXMLFileToString(strXMLFilename)));
        post.setRequestEntity(new StringRequestEntity(strXMLMsg));

        // Specify content type and encoding
        // If content encoding is not explicitly specified
        // ISO-8859-1 is assumed
        //post.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");
        post.setRequestHeader("Content-type", "text/xml; utf-8");

        // Get HTTP client
        HttpClient httpclient = new HttpClient();

//        HttpClientParams param = new HttpClientParams();
//        param.setSoTimeout(7000);
//        httpclient.setParams(param);
        // Execute request
        try {

            int result = httpclient.executeMethod(post);
            // Display status code
            //System.out.println("Response status code: " + result);

            // Display response
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
            System.out.println("Response status code: " + result);
            response[0] = String.valueOf(result);
            if (post.getStatusCode() == 503) {
                response[1] = "ERROR";
            } else if (post.getResponseBodyAsString().indexOf("ERROR") > -1) {
                response[1] = "ERROR";
            } else {
                response[1] = post.getStatusText();
            }
            response[2] = post.getResponseBodyAsString();
            System.out.println("Response status text: " + post.getStatusText());

        //System.out.println(convertXMLFileToString(strXMLFilename));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // Release current connection to the connection pool
            // once you are done
            post.releaseConnection();
        }
        return response;
    }
}