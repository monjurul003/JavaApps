/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.filesavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static monjurul.imageencryptor.ImageEncryptDecrypt.readImageAsByteStream;
import static monjurul.imageencryptor.ImageEncryptDecrypt.writeInAFile;

/**
 *
 * @author Rasel
 */
public class EncryptDecryptProcessor {

    //***********************************Phase 2 encryption with the built in encryption methodology*****************************************//
    public void encryptWithPassword(String filePath, String password) {
        try {
            FileProcessor fProcessor = new FileProcessor();
            byte[] input = null;
            // password for encryption
            String strPassword = "M!$1@mKh@nR@sel$"; //password length must be 16 charater long
            // put this as key in AES
            SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");
            
            input = fProcessor.readFileAsByteStream(filePath);

            // Parameter specific algorithm
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
            //Whatever you want to encrypt/decrypt
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            // encrypt data
            byte[] ecrypted = cipher.doFinal(input);
            fProcessor.writeInAFile(ecrypted);
            
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(EncryptDecryptProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    //***********************************Phase 2 encryption with the built in encryption methodology*****************************************//

    public void decryptWithPassword(String filePath,String password) {
        try {
            FileProcessor fProcessor = new FileProcessor();
            byte[] input = null;
            // password for encryption
            String strPassword = "M!$1@mKh@nR@sel$"; //password length must be 16 charater long
            // put this as key in AES
            SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");
            
            input = fProcessor.readFileAsByteStream(filePath);

            // Parameter specific algorithm
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
            //Whatever you want to encrypt/decrypt
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
            // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            // encrypt data
            byte[] decrypted = cipher.doFinal(input);
            fProcessor.writeInAFile(decrypted);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(EncryptDecryptProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    //***********************************Phase 1 encryption with the built in encryption methodology*****************************************//

    public void encryptWithAlgorithm(String filePath) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            Key key = kg.generateKey();
            
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            CipherInputStream cipt = new CipherInputStream(new FileInputStream(new File(filePath)), cipher);
            
            FileOutputStream fip = new FileOutputStream(new File("C:\\FileSavior\\Temp\\temp.sys"));
            
            int i;
            while ((i = cipt.read()) != -1) {
                fip.write(i);
                
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //***********************************Phase 2 decryption with the built in decryption methodology*****************************************//
    public void decryptWithAlgorithm(String filePath) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            Key key = kg.generateKey();
            
            
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            CipherInputStream ciptt = new CipherInputStream(new FileInputStream(new File("D:\\ja.sys")), cipher);
            
            FileOutputStream fop = new FileOutputStream(new File("D:\\OTest.java"));
            
            int j;
            while ((j = ciptt.read()) != -1) {
                fop.write(j);
            }
            
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
