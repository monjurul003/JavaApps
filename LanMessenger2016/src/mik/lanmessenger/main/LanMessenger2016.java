/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.lanmessenger.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mik.lanmessenger.db.SqLiteConnectionManager;
import mik.lanmessenger.entity.User;

/**
 *
 * @author MIK
 */
public class LanMessenger2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //the sole purpose of this check.dat file is to stop inserting data second time running the program. 
        String workingDir = System.getProperty("user.dir");
        File dir = new File(workingDir + File.separator + "check.dat");

        // Tests whether the directory denoted by this abstract pathname exists.
        boolean exists = dir.exists();
        if(!exists){
            try {
                dir.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LanMessenger2016.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

        SqLiteConnectionManager sqlMng = new SqLiteConnectionManager();
        sqlMng.createTableExample();
        sqlMng.selectExample();
        //stop inserting same data multiple times by checking existance of check.dat file
        if (exists == false) {
            System.out.println("insert example");
            sqlMng.insertExample();
        }
        ArrayList<User> list = sqlMng.selectAndReturnAsAList();
        for (int i = 0; i < list.size(); i++) {          
            System.out.println("ID = " + i);
            System.out.println("NAME = " + list.get(i).getName());
            System.out.println("password = " + list.get(i).getPassword());
            System.out.println("IP = " + list.get(i).getIp());
            System.out.println("");
            
        }
        
    }
    
}
