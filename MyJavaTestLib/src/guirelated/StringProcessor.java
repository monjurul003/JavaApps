/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guirelated;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daffodil PC
 */
public class StringProcessor {

    public Date getDateFromString(String inputDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String dateInString = "2013/03/12 12:10:56";
         
        try {

            Date date = formatter.parse(inputDate);
            System.out.println(date);
            System.out.println(formatter.format(date));
            return date;
        } catch (java.text.ParseException ex) {
            Logger.getLogger(StringProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCurrentTimeAndDateAsString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String curDate = formatter.format(date);
        System.out.println(curDate);
        
        return curDate;
    }
    public String getFileNameAfterEncryption(String fileName){
        return fileName+".enc";
    }
    
    public String getFileNameAfterDecryption(String fileName){
        int pos = fileName.lastIndexOf(".");
        String originalFileName = fileName.substring(0, pos);
        return fileName+".enc";
    }
    
}
