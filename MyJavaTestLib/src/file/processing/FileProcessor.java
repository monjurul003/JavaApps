/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.processing;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

/**
 *
 * @author Daffodil PC
 */
public class FileProcessor {

    static final Logger logger = Logger.getLogger(FileProcessor.class);
    private final String applicationDirectoryName = "FileSavior";


    private String myDocumentPath, userDirPath, logsPath, historyDirPath;

    public FileProcessor() {
        myDocumentPath = this.getMyDocumentsPath();
        userDirPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "users";
        logsPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "logs";
        historyDirPath = myDocumentPath + File.separator + this.applicationDirectoryName + File.separator + "history";
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


    /* This function will create a document in the user directory and in specified folder*/
    public void createFileInMyDoumentUnderSpecifiedDir(String dirName, String fileName) {
        try {
            this.createDirectoryInMyDocuments(dirName);
            String myDocuments = this.getMyDocumentsPath();

            File file = new File(myDocuments + dirName + File.separator + fileName);

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
     * *****************************This function will create the directory
     * with the name of the argument name
     * ******************************************
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
     * *************************Create directory in application directory
     * **************************
     */
    public void createDirectoryInApplicationDirectory(String dirName) {
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir + File.separator + dirName);
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

    }

    /**
     * ******************************* return file in a String Array
     * *********************************
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
     * ****************************** byte[] array Write in a JpgImage
     * ********************
     */
    public void writeByteStreamAsJpgImage(String dirName, String fileName, byte[] output) {

        FileOutputStream fop = null;
        File file;
//		String content = "This is the text content";

        try {

            file = new File(dirName + File.separator + fileName + ".jpg");
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

    /**
     * ****************************** byte[] array Write in a File
     * ********************
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

    /**
     * ***********************************Read image as byte
     * stream********************************************
     */
    public static byte[] readImageAsByteStream(String fileLocation, String fileName) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(fileLocation + File.separator + fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
            logger.fatal("IOException: " + ex);

        }
        return null;
    }

    /**
     * Read file as byte stream
     */
    public static byte[] readFileAsByteStream(String fileLocation, String fileName) {
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
    public static byte[] readFileAsByteStream(String absolutFilepath) {
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
     * Change the file to a readOnly file
     */
    public void changeFileToReadOnly(String fileLocation, String fileName) {
        logger.info("FileProcessor:: changeFileToReadOnly() -- started");
        File file = new File(fileLocation + File.separator + fileName);
        if (file.exists()) {
            logger.info("FileProcessor:: changeFileToReadOnly()-- file exists in the designated location");
            if (file.canWrite()) {
                logger.info("FileProcessor:: changeFileToReadOnly()-- This file is writeable");
                file.setReadOnly();
//              file.setWritable(false);  // This also works.
            } else {
                logger.info("FileProcessor:: changeFileToReadOnly()-- This file is read only");
            }

            logger.info("FileProcessor:: changeFileToReadOnly()-- make the file read only");
        }

        logger.info("FileProcessor:: changeFileToReadOnly() completed");
    }

    /**
     * Create a file to a specified directory with some initial String content
     */
    public void createAndInitFileWithStringContent(String fileLocation, String fileName, String content) {

        try {

            String content1 = "This is the content to write into file";

            File file = new File(fileLocation + File.separator + fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                logger.info("FileProcessor:: createAndInitFileWithStringContent()-- file created");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            logger.info("FileProcessor:: createAndInitFileWithStringContent() done");

        } catch (IOException e) {
            logger.fatal("FileProcessor:: createAndInitFileWithStringContent() completed");
            e.printStackTrace();
        }

    }

    /**
     * Append file with String content
     */
    public void appendFileWithStringContent(String fileLocation, String fileName, String content) {

        try {

            String content1 = "This is the content to write into file";

            File file = new File(fileLocation + File.separator + fileName);
            logger.info("FileProcessor:: appendFileWithStringContent()-- file location" + fileLocation + File.separator + fileName);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                logger.info("FileProcessor:: appendFileWithStringContent() file created");
            }
            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(content);
            bufferWritter.close();

        } catch (IOException e) {
            logger.fatal("FileProcessor:: appendFileWithStringContent() completed");
        }

    }

    /**
     * Delete file
     */
    public void deleteFile(String fileLocation, String fileName) {
        try {

            File file = new File(fileLocation + File.separator + fileName);

            if (file.delete()) {
                logger.info("FileProcessor:: deleteFile() -- " + file.getName() + " is deleted!");
            } else {

            }

        } catch (Exception e) {
            logger.fatal("FileProcessor:: deleteFile() error--" + e.toString());
        }
        logger.info("FileProcessor:: deleteFile() -- complete");
    }

    public void listFile(String folder, String ext) {

//		GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);

        if (dir.isDirectory() == false) {
            logger.info("FileProcessor:: listFile() -- Directory does not exists : " + folder);
            return;
        }

        // list out all the file name and filter by the extension
        String[] temp_list = dir.list();
        String[] list;

        if (temp_list.length == 0) {
            logger.info("FileProcessor:: listFile() -- no files end with : " + ext);
            return;
        }
        int i = 0;
        for (String file : temp_list) {
            String temp = new StringBuffer(folder).append(File.separator)
                    .append(file).toString();

            if (temp.endsWith(ext.toLowerCase()) || temp.endsWith(ext.toUpperCase())) {
                i++;
            }
//			System.out.println("file : " + temp);
        }
        logger.info("FileProcessor:: listFile() -- file number: " + i);
        list = new String[i];
        i = 0;
        for (String file : temp_list) {
            String temp = new StringBuffer(folder).append(File.separator)
                    .append(file).toString();

            if (temp.endsWith(ext.toLowerCase()) || temp.endsWith(ext.toUpperCase())) {
                list[i] = temp;
                i++;
                System.out.println("FileProcessor:: listFile() -- file : " + temp);
            }

        }

    }

    //Check a directory exists or not
    public boolean checkExistanceOfDirectory(String dirPath) {
        File dir = new File(dirPath);

        // Tests whether the directory denoted by this abstract pathname exists.
        boolean exists = dir.exists();
        return exists;
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

    //Check a directory exists or not
    public boolean checkApplicationsFirstLaunchOrNot() {
        String myDocumentPath = this.getMyDocumentsPath();
        File dir = new File(myDocumentPath + File.separator + this.applicationDirectoryName);

        // Tests whether the directory denoted by this abstract pathname exists.
        boolean exists = dir.exists();
        System.out.println("Application is first lauch??--" + exists);
        return exists;
    }

    public String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        return sdf.format(today.getTime());
    }

}
