/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpit.excel;

/**
 *
 * @author kamal.hossen
 */
import java.sql.*;
import java.io.File;
import jxl.*;
import jxl.write.*;

public class myJexcel {

private String file_path=null;
Connection dbcon=null;
private String string1=null;
private String string2 =null;
private String string3=null;
private String string4=null;
private String string5=null;

    public myJexcel()
    {

    }
public void setF1(String path)
    {
    file_path=path;
}

public String getF1()
    {
    return file_path;
}


public void insertData()
{
try{
    Statement st=dbcon.createStatement();
    ResultSet rs=null;
    
    String qry="insert into jsp_upload(msisdn,user_name,remarks,email) "
            + "values('"+string2+"','"+string3+"','"+string4+"','"+string5+"')";
     
    rs=st.executeQuery(qry);

    st.close();

    }
catch(Exception e)
{
    System.out.println("SQL Error::: "+e.getMessage());
}

}
    public void readExcel(String File_path) //throws Exception
    {
     try{

         Workbook workbook = Workbook.getWorkbook(new File(File_path));
         dbConnection con=new dbConnection();
         dbcon=con.getDBConnection();

         System.out.println("Number of Sheets: "+workbook.getNumberOfSheets());

         //for (int k=0; k<workbook.getNumberOfSheets();k++)
         //{
         Sheet sheet = workbook.getSheet(0);

         System.out.println("Number of Rows: "+sheet.getRows());
         System.out.println("Number of Columns: "+sheet.getColumns());

         
           for (int i=1; i<sheet.getRows();i++)
            {
         
              //for (int j=0;j<sheet.getRows();j++)
              //{
                Cell v1 = sheet.getCell(0,i);
                Cell v2 = sheet.getCell(1,i);
                Cell v3 = sheet.getCell(2,i);
                Cell v4 = sheet.getCell(3,i);
                Cell v5 = sheet.getCell(4,i);

                string1 = v1.getContents();
                string2 = v2.getContents();
                string3 = v3.getContents();
                string4 = v4.getContents();
                string5 = v5.getContents();

                System.out.println("inserting row no.: "+i);
                
                insertData();

              //}
            }
         //}

         workbook.close();
         dbcon.close();
        }

      catch(Exception e)
      {
          System.out.println("Error"+ e.getMessage());
      }

    }



    public void writeExcel(String folder_path)
    {
        try{

            WritableWorkbook workbook = Workbook.createWorkbook(new File(folder_path+ "/output.xls"));

            WritableSheet sheet = workbook.createSheet("Asset List", 0);            

            // ADDING .... Sheet Header............

            Label xyz = new Label(0,0, "Row ID");
            sheet.addCell(xyz);            

            Label msisdn = new Label(1,0, "MSISDN");
            sheet.addCell(msisdn);            

            Label usr = new Label(2,0, "User Name");
            sheet.addCell(usr);

            Label at = new Label(3,0, "alert type");
            sheet.addCell(at);            

            Label email = new Label(4,0, "email");
            sheet.addCell(email);
           // int k=0;
            //jxl.write.Number rowid = new jxl.write.Number(5,k, 134);
            //sheet.addCell(rowid);

            ////// Header is added
            
         dbConnection con=new dbConnection();
         dbcon=con.getDBConnection();
         Statement st=dbcon.createStatement();
         ResultSet rs=null;
         
         String Qry="select row_id,msisdn,user_name,remarks,email from jsp_upload";
         
         rs=st.executeQuery(Qry);

         System.out.println("Qry is executed.......");


         int row_cnt=rs.getRow();

         System.out.println("Total row retrive: "+row_cnt);
         int i=1;

         while(rs.next()){
             
            jxl.write.Number rid = new jxl.write.Number(0,i, rs.getInt("ROW_ID"));
            sheet.addCell(rid);
            
            Label ms = new Label(1,i, rs.getString("msisdn"));
            sheet.addCell(ms);            

            Label uname = new Label(2,i, rs.getString("user_name"));
            sheet.addCell(uname);

            Label alert = new Label(3,i, rs.getString("remarks"));
            sheet.addCell(alert);

            Label e_mail = new Label(4,i, rs.getString("email"));
            sheet.addCell(e_mail);     
             i++;
         }


           // jxl.write.Number abc = new jxl.write.Number(3, 4, 3.1459);
            //sheet.addCell(abc);
            
            workbook.write();
            workbook.close();

            }

        catch(Exception e)
        {
            System.out.println(" New excel error:"+e.getMessage());
        }
    }

}
