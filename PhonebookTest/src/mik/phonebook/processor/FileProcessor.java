/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.phonebook.processor;

import mik.phonebook.entity.User;
import mik.phonebook.entity.Contact;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Monjurul Islam Khan
 */
public class FileProcessor {

    private ArrayList<User> userLIst;
    private ArrayList<Contact> conList;

    public FileProcessor() {
        this.userLIst = new ArrayList<>();
        this.conList = new ArrayList<>();
        this.createDirectoryInAppsDir();
    }

    public String getMyDocumentsPath() {
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        String dir = fw.getDefaultDirectory().toString();
//        System.out.println(dir);
        return dir;
    }

    public void createFileInUserDir() {
        try {
            this.createDirectoryInDocuments();
            String myDocuments = this.getMyDocumentsPath();

            File file = new File(myDocuments + "\\MyPhonebook\\Users\\userList.txt");

            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 public void createDirectoryInAppsDir() {
        String userDir = System.getProperty("user.dir");

        File file = new File(userDir + "\\save");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }
    public void createDirectoryInDocuments() {
        String myDocuments = this.getMyDocumentsPath();

        File file = new File(myDocuments + "\\MyPhonebook\\Users");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    public ArrayList<User> getUserList() {

        ArrayList<User> userList = new ArrayList<User>();
//        String myDocuments = this.getMyDocumentsPath() + "\\MyPhonebook\\Users";
        String myDocuments = System.getProperty("user.dir");

        File file = new File(myDocuments + File.separator + "user.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        BufferedReader br = null;
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(myDocuments + File.separator + "user.txt"));
            String name = "";
            String pass = "";
            while ((sCurrentLine = br.readLine()) != null) {

                String[] list = sCurrentLine.split(";");
                for (int i = 0; i < list.length; i++) {
                    System.out.println(list[i]);
                    if (i == 0) {
                        name = list[i];
                    } else {
                        pass = list[i];
                    }
                }

                User user = new User(name, pass);
                userList.add(user);

            }
//            this.printUserList(userList);
            return userList;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void printUserList(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(i + " User name: " + userList.get(i).getUser_name() + " password -- " + userList.get(i).getPaasword());
        }
    }

    public void createUserFileInAppDir() {
        try {
            String userDir = System.getProperty("user.dir");

            File file = new File(userDir + "userList.txt");

            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Contact> getContactArrayListFromFile(String userName) {
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println(userDir);
            FileReader fr = new FileReader(userDir + File.separator + "save" + File.separator + userName + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String str = "";
            System.out.println(str);
            while ((str = br.readLine()) != null) {
                String[] strArr = str.split(";");
                String name = "";
                String num = "";
                for (int i = 0; i < strArr.length; i++) {
                    System.out.println(strArr[i]);
                    if (i == 0) {
                        name = strArr[i];
                    } else {
                        num = strArr[i];
                    }
                }
                Contact obj = new Contact(name, num);
                this.conList.add(obj);

            }
            return this.conList;
        } catch (IOException ex) {
            System.out.println("IOException--" + ex.toString());
        }
        return this.conList;
    }

    public void saveUserListInFile() {
        FileWriter fw = null;
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println(userDir);
            fw = new FileWriter(userDir + File.separator + "user.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.userLIst.size(); i++) {
                bw.write(this.userLIst.get(i).toString());
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveContactListInFile() {
        FileWriter fw = null;
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println(userDir);
            fw = new FileWriter(userDir + File.separator + "input.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.conList.size(); i++) {
                bw.write(this.conList.get(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveContactListInFile(String userName, ArrayList<Contact> conList) {
        FileWriter fw = null;
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println(userDir);
            fw = new FileWriter(userDir + File.separator + "save" + File.separator + userName + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.conList.size(); i++) {
                bw.write(conList.get(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void writeInFile(String userName, ArrayList<Contact> conList) {

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        File file = new File(userDir + File.separator + "save" + File.separator + userName +".txt");
        String content = "";

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            for (int i = 0; i < conList.size(); i++) {
                content = conList.get(i).toString();
                // get the content in bytes
                byte[] contentInBytes = content.getBytes();

                fop.write(contentInBytes);
                fop.flush();
            }

            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
