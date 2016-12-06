/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.processing;

import ImageEncryption.ImageEncryptDecrypt;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Rasel
 */
public class FileTest {

    public static void main(String[] args) {
        try {
            //        FileProcessor fp = new FileProcessor();
            
            byte[] obj = readFileAsByteStream();
            
            writeAsAimage(obj);
            System.out.println("Done");
        } catch (IOException ex) {
            Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  /*************************************Read image as byte stream*********************************************/
    public static byte[] readImageAsByteStream() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("F:"+File.separator+"16.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
            Logger.getLogger(ImageEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }
    /**
     * ***********************************Read File as byte stream********************************************
     */
    public static byte[] readFileAsByteStream() throws IOException {
        try {
            RandomAccessFile f = new RandomAccessFile("F:"+File.separator+"16.jpg", "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * ****************************** byte[] array Write in a File ********************
     */
    public static void writeAsAimage(byte[] output) {

        FileOutputStream fop = null;
        File file;
//		String content = "This is the text content";

        try {

            file = new File("F:"+File.separator+"img.jpg");
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
}
