/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testproject;

import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 *
 * @author Daffodil PC
 */
public class ERP {

    public static void main(String[] args){
        Employee m = new Manager("Smith", "1120", "SWE");
        m.printInfo();
        
        Date date = new Date(10000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        System.out.println(sdf.format(new java.util.Date().getTime() ));
        
        
    }
}
    
