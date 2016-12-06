/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.imageencryptor;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Rasel
 */
public class EncryptionTest {

    public static void main(String[] args) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            Key key = keyGen.generateKey();
            String str = "HelloWorld";
            byte[] inputStream = str.getBytes();
            System.out.println(str);
            
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedStream = cipher.doFinal(inputStream);
            System.out.println(encryptedStream);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decryptedStream = cipher.doFinal(encryptedStream);
            str = decryptedStream.toString();
            System.out.println(str);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(EncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(EncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(EncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(EncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
