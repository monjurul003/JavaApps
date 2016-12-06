/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpit.excel;

import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author kamal.hossen
 */
public class getData {

    private String MSISDN;

    public getData()
    {

    }

    public void setMsisdn(String Mobile)
    {
        MSISDN=Mobile;
    }

    public String getMsisdn()
    {
        return MSISDN;
    }


    public ResultSet getDatafromDB(String MSISDN)
    {
        Connection dbCon=null;
        ResultSet rs=null;

    try{
        dbConnection con= new dbConnection();
        dbCon=con.getDBConnection();
        Statement st= dbCon.createStatement();
        
        String qry="select * from jsp_upload where msisdn='"+MSISDN+"'";
        rs=st.executeQuery(qry);


        }

    catch(Exception e)
        {
        System.out.println("Eror in Connection: "+e.getMessage());
        }

        return rs;
    }

}
