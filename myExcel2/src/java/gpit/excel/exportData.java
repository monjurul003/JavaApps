/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpit.excel;

import java.net.ConnectException;
import java.sql.Connection;
import  java.io.*;
import  java.sql.*;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;

/**
 *
 * @author kamal.hossen
 */
public class exportData {

    String file_path;

    Connection conn=null;
    
    public exportData()
    {

    }

    public void setF1( String path)
    {
        file_path=path;
    }

    public String getF1()
    {
        return(file_path);
        
    }
public void GenerateExcel()
    {
        try{
            dbConnection dbCon=new dbConnection();
            conn=dbCon.getDBConnection();

System.out.println("*********Generating file********");

String filename="C:/Activities/NERM-BPM/POC/excelGenerate/Data.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("SNo");
rowhead.createCell((short) 1).setCellValue("MSISDN");
rowhead.createCell((short) 2).setCellValue("Alert Type");
rowhead.createCell((short) 3).setCellValue("Remarks");
rowhead.createCell((short) 4).setCellValue("E-mail");

//Class.forName("com.mysql.jdbc.Driver");
//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
System.out.println("**************Connecting Database file***********");
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select row_id as id,MSISDN,Alert_Type,Remarks,Email from kamal.jsp_test");
System.out.println("******** Database Connection Done ***********");

int i=1;
while(rs.next()){
    System.out.println("Inserting Row: "+ i);

HSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 1).setCellValue(rs.getString("MSISDN"));
row.createCell((short) 2).setCellValue(rs.getString("alert_type"));
row.createCell((short) 3).setCellValue(rs.getString("remarks"));
row.createCell((short) 4).setCellValue(rs.getString("email"));

System.out.println("Inserted MSISDN: "+rs.getString("MSISDN"));

i++;
}

FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
conn.close();

} catch ( Exception ex ) {
    System.out.println(ex);

}
    
}
}
