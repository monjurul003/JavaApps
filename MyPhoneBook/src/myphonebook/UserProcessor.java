/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myphonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Diu
 */
public class UserProcessor {

    ArrayList<User> list = new ArrayList<User>();

    ;

    public UserProcessor() {
    }

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public String[] arrayListToStringArray() {
        String[] strArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArray[i] = list.get(i).toString();
        }
        return strArray;
    }

    public void initArrayList(String filePath, String fileName) {
        FileReader fr = null;
        try {
            fr = new FileReader(filePath + File.separator + fileName);
//            fr = new FileReader("D:" + File.separator + "testing.txt");
            BufferedReader br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
  
                String[] strArr = sCurrentLine.split(";");
//                for(int i =0; i<strArr.length; i++){
//                    System.out.println(strArr[i]+"  "+i);
//
//                }

                User obj = new User(strArr[0],strArr[1]);
                list.add(obj);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeStringArrayInFile(String filePath, String fileName, String[] strArr) {
        try {
            File file = new File(filePath + File.separator + fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < strArr.length; i++) {
                bw.write(strArr[i].trim() + "\n");
            }
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Name:" + list.get(i).getName());
            System.out.println("Number:" + list.get(i).getNumber());
        }
    }
}
