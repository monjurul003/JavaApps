/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageEncryption;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author monjurul.k
 */
import java.io.RandomAccessFile;
import java.security.spec.AlgorithmParameterSpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;
import javax.xml.bind.DatatypeConverter;

/*  Mode = CipherMode.CBC,-( Cipher-block chaining)
Padding = PaddingMode.PKCS7 or PKCS5,
KeySize = 128,
BlockSize = 128,
Key = keyBytes - password,
IV = keyBytes  - password*/
public class ImageEncryptDecrypt {

    Cipher cipher;
    // Input encrypted String
//  static  String input = "Hi This is my String";
    static byte[] input = null;
    // password for encryption
    final static String strPassword = "Monjuru1!$1@mKh@"; //password length must be 16 charater long
    // put this as key in AES
    static SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");

    public static void main(String args[]) throws Exception {

        input = readImageAsByteStream();

        // Parameter specific algorithm
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
        //Whatever you want to encrypt/decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        // encrypt data
        byte[] encrypted = cipher.doFinal(input);

        // encode data using standard encoder
//        String output = new BASE64Encoder().encode(encrypted); // Base64Encoder is replaced by printBase64Binary()
        String output = DatatypeConverter.printBase64Binary(encrypted);
        writeInAFile(encrypted);


      input =readFileAsByteStream();

        // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        // encrypt data
         encrypted = cipher.doFinal(input);
//     System.out.println("Orginal string: " + input);
//     System.out.println("Encripted string: " + output);

    writeAsAimage(encrypted);

    }

    /*************************************Read image as byte stream*********************************************/
    public static byte[] readImageAsByteStream() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("C:\\140KB.jpg"));
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

    /*************************************Read File as byte stream*********************************************/
    public static byte[] readFileAsByteStream() throws IOException {
        try {
            RandomAccessFile f = new RandomAccessFile("C:\\newfile.sys", "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /******************************** byte[] array Write in a File *********************/
    public static void writeInAFile(byte[] output) {

        FileOutputStream fop = null;
        File file;
//		String content = "This is the text content";

        try {

            file = new File("c:/newfile.sys");
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

    /******************************** byte[] array Write in a File *********************/
    public static void writeAsAimage(byte[] output) {

        FileOutputStream fop = null;
        File file;
//		String content = "This is the text content";

        try {

            file = new File("c:/img.jpg");
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