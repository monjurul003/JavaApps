/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpit.excel;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author kamal.hossen
 */
public class insertExcelData {


    public insertExcelData()
    {

    }

public void insertingData()
    {
       String fileName="c:/hhh.xls";
       Vector dataHolder=read(fileName);
       saveToDatabase(dataHolder);
    }

    public static Vector read(String fileName)
       {
        Vector cellVectorHolder = new Vector();
       try{
           System.out.println("try is executing");
           FileInputStream myInput = new FileInputStream(fileName);
           System.out.println("try is executing1");
           POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
           System.out.println("try is executing2");
           HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
           System.out.println("try is executing3");
           HSSFSheet mySheet = myWorkBook.getSheetAt(0);
           System.out.println("try is executing4");
           Iterator rowIter = mySheet.rowIterator();

           while(rowIter.hasNext()){
               HSSFRow myRow = (HSSFRow) rowIter.next();
               Iterator cellIter = myRow.cellIterator();
               Vector cellStoreVector=new Vector();
               System.out.println("try is executing5");

               while(cellIter.hasNext()){
                   HSSFCell myCell = (HSSFCell) cellIter.next();
                   cellStoreVector.addElement(myCell);
                   }
               cellVectorHolder.addElement(cellStoreVector);
                 }
            }
               catch (Exception e)
               {
                   e.printStackTrace();
                }
                    return cellVectorHolder;
            }

    private static void saveToDatabase(Vector dataHolder)
    {
                    System.out.println("try is executing6");
                    String username="";
                    String password="";
                     for (int i=0;i<dataHolder.size(); i++)
                     {
                         Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
                         
                         for (int j=0; j < cellStoreVector.size();j++)
                         {
                             HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
                             String st = myCell.toString();
                             username=st.substring(0,1);
                             password=st.substring(0);
                         }
                         try{
                             System.out.println("try is executing7");
                             //ConnectionToCustomDatabase dba;
                             Connection conn = null;
                             dbConnection con=new dbConnection();
                            // PreparedStatement pstmt = null;

                             ResultSet rs = null;
                             //String sqlquery ="";
                             //dba = new ConnectionToCustomDatabase();
                             conn=con.getDBConnection();
                             Statement st=conn.createStatement();

                             String stmt="insert into JSP_UPLOAD(msisdn,user_name,remark,email) values"
                                     + "('8801711081691','"+username+"','"+password+"','abc')";
                            

                        //PreparedStatement stat=conn.prepareStatement("insert into login(username,password1) values('"+username+"','"+password+"')");

                         //System.out.println(username);
                         //System.out.println(password);
                         //stat.execute();
                         //pstmt = con.prepareStatement(qry);

                         //rs = pstmt.executeQuery();
                         //rs=st.executeQuery(stmt);
                         //System.out.println("try is executing8");
                         
                         //System.out.println("Data is inserted");
                         // stat.executeQuery()
                   rs=st.executeQuery(stmt);
                    if(rs.next())
                    {
                        String uu=rs.getString("username");
                        String pp1=rs.getString("password1");
                        System.out.println(uu);
                        System.out.println(pp1);

                    }
                         rs.close();
                         conn.close();
                     }
                     catch(Exception e)
                     {
                         System.out.print(e);
                     }
                   }
                 }
}
 