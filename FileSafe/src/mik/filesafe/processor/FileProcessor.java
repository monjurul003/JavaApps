/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.filesafe.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import mik.filesafe.encryption.StringEncryptorDecryptor;
import mik.filesafe.entity.User;
import mik.filesafe.utility.Blowfish;
import org.apache.log4j.Logger;

/**
 *
 * @author Daffodil PC
 */
public class FileProcessor {

    static final Logger logger = Logger.getLogger(FileProcessor.class);
    private String myDocumentPath, userDirPath, logsPath, historyDirPath;
    private final String applicationDirectoryName = "FileSafe";
    private Blowfish bf;

    public FileProcessor() {
        this.bf = new Blowfish("@&12X3abc#");
        myDocumentPath = this.getMyDocumentsPath();
        userDirPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "users";
        logsPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "logs";
        historyDirPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "history";
    }

    public List<User> prepareSystem() {
        //create directories path

        StringEncryptorDecryptor strEncryptor = new StringEncryptorDecryptor();
        List<User> userList;

        boolean firstLaunch = this.checkFirstLaunch();

        if (firstLaunch == false) {
            createDirectoryInMyDocuments(applicationDirectoryName);
            createDirectoryInSpecifiedpath(userDirPath);
            createFileInSpecifiedDir(userDirPath, "info.dat");
            createDirectoryInSpecifiedpath(logsPath);
            createDirectoryInSpecifiedpath(historyDirPath);
            return null;
        } else {
            String[] encryptedUserList = readFileAsAStringArray(userDirPath, "info.dat");
            String[] decryptedUserList = strEncryptor.decryptStringArray(encryptedUserList);
            UserProcessor up = new UserProcessor();

            userList = up.getUserListFromStringArray(decryptedUserList);
            return userList;
        }

    }

    public boolean checkFirstLaunch() {
        String myDocumentPath = this.getMyDocumentsPath();
        File dir = new File(myDocumentPath + File.separator + this.applicationDirectoryName);

        // Tests whether the directory denoted by this abstract pathname exists.
        boolean exists = dir.exists();
        System.out.println("Application is first lauch??--" + exists);
        return exists;
    }
    /* This function will create a file in the in specified folder*/

    public void createFileInSpecifiedDir(String dirName, String fileName) {
        try {

            File file = new File(dirName + File.separator + fileName);

            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will create the directory with the name of the argument
     * name
     */
    public void createDirectoryInMyDocuments(String dirName) {
        String myDocuments = this.getMyDocumentsPath();

        File file = new File(myDocuments + File.separator + dirName);
//        File file = new File(myDocuments + "\\MyPhonebook\\Users");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    /**
     * *****************************This function will create the directory
     * with the name of the argument name
     * ******************************************
     */
    public void createDirectoryInSpecifiedpath(String dirName) {

        File file = new File(dirName);
//        File file = new File(myDocuments + "\\MyPhonebook\\Users");
        if (!file.exists()) {
            if (file.mkdirs()) {
                logger.info("Directory is created! -- " + dirName);
            } else {
                logger.info("Failed to create directory! -- " + dirName);
            }
        }
    }

    /**
     * *****************This function will return the path of the Documents
     * folder for Default User******************
     */
    public String getMyDocumentsPath() {
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        String dir = fw.getDefaultDirectory().toString();
//        System.out.println(dir);
        return dir;
    }

    public List<User> getExistingUser() {
        List<User> userList;
        StringEncryptorDecryptor strEncryptor = new StringEncryptorDecryptor();
        String[] encryptedUserList = readFileAsAStringArray(userDirPath, "info.dat");
        String[] decryptedUserList = strEncryptor.decryptStringArray(encryptedUserList);
        UserProcessor up = new UserProcessor();

        userList = up.getUserListFromStringArray(decryptedUserList);
        return userList;
    }

    public void updateUserLogInFile(List<User> userList) {
        StringEncryptorDecryptor strEncryptor = new StringEncryptorDecryptor();
        UserProcessor up = new UserProcessor(userList);
        String[] strArray = up.getUserListAsStringArray();
        String[] output = strEncryptor.encryptStringArray(strArray);
        this.writeStringArrayInFile(userDirPath, "info.dat", strArray);
    }

    public String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        return sdf.format(today.getTime());
    }

    /**
     * ****************Get number of line in a file *******************
     */
    public int getLineCountInFile(String fileLocation, String fileName) {

        try {

            File file = new File(fileLocation + File.separator + fileName);

            if (file.exists()) {

                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);

                int linenumber = 0;

                while (lnr.readLine() != null) {
                    linenumber++;
                }

                System.out.println("Total number of lines : " + linenumber);

                lnr.close();
                return linenumber;

            } else {
                System.out.println("File does not exists!");
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * return file in a String Array
     */
    public String[] readFileAsAStringArray(String fileLocation, String fileName) {
        BufferedReader br = null;
        int lineCount = getLineCountInFile(fileLocation, fileName);
        String[] fileInString = new String[lineCount];
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileLocation + File.separator + fileName));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {

                fileInString[i] = sCurrentLine;
                i++;
            }

            return fileInString;
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

    /**
     * Read file as byte stream
     */
    public byte[] readFileAsByteStream(String fileLocation, String fileName) {
        try {
            RandomAccessFile f = new RandomAccessFile(fileLocation + File.separator + fileName, "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (FileNotFoundException ex) {
            logger.fatal(ex);
        } catch (IOException ex) {
            logger.fatal(ex);
        }
        return null;
    }

    /**
     * Read file as byte stream
     */
    public byte[] readFileAsByteStream(String absolutFilepath) {
        try {
            RandomAccessFile f = new RandomAccessFile(absolutFilepath, "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (FileNotFoundException ex) {
            logger.fatal(ex);
        } catch (IOException ex) {
            logger.fatal(ex);
        }
        return null;
    }

    /**
     * byte[] array Write in a File
     */
    public void writeByteStreamAsAFile(String dirName, String fileName, byte[] output) {

        FileOutputStream fop = null;
        File file;
//		String content = "This is the text content";

        try {

            file = new File(dirName + File.separator + fileName);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

//			// get the content in bytes
//			byte[] contentInBytes = content.getBytes();
            fop.write(output);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> listFileWithSpecificExtension(String folder, String ext) {

//		GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);
        ArrayList<String> fileList = new ArrayList<>();

        if (dir.isDirectory() == false) {
            logger.info("FileProcessor:: listFile() -- Directory does not exists : " + folder);
            System.out.println("FileProcessor:: listFile() -- Directory does not exists : " + folder);
            return null;
        }

        // list out all the file name and filter by the extension
        String[] temp_list = dir.list();
        String[] list;

        if (temp_list.length == 0) {
            logger.info("FileProcessor:: listFile() -- no files end with : " + ext);
            System.out.println("FileProcessor:: listFile() -- no files end with : " + ext);
            return null;
        }
        int i = 0;
        for (String file : temp_list) {
            String temp = new StringBuffer(folder).append(File.separator)
                    .append(file).toString();

            if (temp.endsWith(ext.toLowerCase()) || temp.endsWith(ext.toUpperCase())) {
                i++;
                fileList.add(temp);
                System.out.println("FileProcessor:: listFile() -- file : " + temp);
            }
//			System.out.println("file : " + temp);
        }
        System.out.println("FileProcessor:: listFile() -- file number: " + i);
        logger.info("FileProcessor:: listFile() -- file number: " + i);

        return fileList;
    }

    public ArrayList<String> listFile(String folder) {

//		GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);
        ArrayList<String> fileList = new ArrayList<>();

        if (dir.isDirectory() == false) {
            logger.info("FileProcessor:: listFile() -- Directory does not exists : " + folder);
            System.out.println("FileProcessor:: listFile() -- Directory does not exists : " + folder);
            return null;
        }

        // list out all the file name and filter by the extension
        String[] temp_list = dir.list();
      

        if (temp_list.length == 0) {
            logger.info("FileProcessor:: listFile() -- no files in the directory ");
            System.out.println("FileProcessor:: listFile() -- no files in the directory: ");
            return null;
        }
        int i = 0;
        for (String file : temp_list) {
            
            String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
            i++;
            fileList.add(temp);
            System.out.println("FileProcessor:: listFile() -- file : " + temp);
        }
        
        System.out.println("FileProcessor:: listFile() -- file number: " + i);
        logger.info("FileProcessor:: listFile() -- file number: " + i);

        return fileList;
    }
}
