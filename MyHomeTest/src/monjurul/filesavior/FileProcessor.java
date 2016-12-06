/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.filesavior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import monjurul.imageencryptor.ImageEncryptDecrypt;

/**
 *
 * @author Rasel
 */
public class FileProcessor {

    /*************************************Read image as byte stream*********************************************/
    public byte[] readFileAsByteStream(String path) throws IOException {
        try {
//            RandomAccessFile f = new RandomAccessFile("C:\\newfile.sys", "r");
            RandomAccessFile f = new RandomAccessFile(path, "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /******************************** byte[] array Write in a File *********************/
    public void writeInAFile(byte[] output) {

        FileOutputStream fop = null;
        File file;

        try {

            file = new File("c:/newfile.sys");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fop.write(output);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
            }
        }
    }
/*********************************Get file names as a list in a directory**************************************************/
    public List<String> getFileListInDirectory(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<String> fileList = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileList.add(listOfFiles[i].getName());
            }
        }
        return fileList;
    }
/*********************************Create directory**************************************************/
    public void createSingleDirectory(String path, String directoryName) {
//        File file = new File("C:\\Directory1");
        File file = new File(path + directoryName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }


    }

    public void createDirectoryWithSubDirectory(String path, String directoryName, String subDirName) {
        //File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");

        File files = new File(path + directoryName + "\\" + subDirName);

        if (files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
    }
/*********************************Get file extension**************************************************/
    public String getExtension(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//        System.out.println(ext);
        return ext;
    }
/*********************************Delete a particular file**************************************************/
    public void deleteFile(String path) {
        try {

            File file = new File(path);

            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
