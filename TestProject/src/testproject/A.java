/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Daffodil PC
 */
public class A {

    public static void loggerInitialization() {
        String workingDir = System.getProperty("user.dir");
        DOMConfigurator.configure(workingDir + "\\config\\log4jConfig.xml");
    }

    public static void printPyramid(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = n - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void printNumberPyramid(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = n - i; j > 0; j--) {
                System.out.print(" ");
            }

            for (int m = i; m >= 1; m--) {
                System.out.print(m);
            }
            for (int k = 2; k <= i; k++) {
                System.out.print(k);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        //        loggerInitialization();
        //        int n = 5;
        //        String test="Hello";
        //        byte[] bArray = test.getBytes();
        //        
        //        String output = new BASE64Encoder().encode(bArray);
        //         String output1 =DatatypeConverter.printBase64Binary(bArray);
        ////        printPyramid(n);
        //        System.out.println(output);
        //        System.out.println(output1);
        //        System.out.println("dhurrr");
        //       try{
        // 
        //    		File file = new File("G:\\Hello.txt");
        // 
        //    		if(file.delete()){
        //    			System.out.println(file.getName() + " is deleted!");
        //    		}else{
        //    			System.out.println("Delete operation is failed.");
        //    		}
        // 
        //    	}catch(Exception e){
        // 
        //    		e.printStackTrace();
        // 
        //    	}
        //        printNumberPyramid(n);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date1 = new Date();
        System.out.println(formatter.format(date1));
        String dateInString = "2013/03/12 12:10:56";

        try {

            Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (java.text.ParseException ex) {
//            Logger.getLogger(StringProcessor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error--" + ex.toString());
        }
    }
}
