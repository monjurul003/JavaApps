/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.imageencryptor;



/**
 *
 * @author Rasel
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author hi
 */
public class MyImageEncryptor {

    public static void encryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            Key key = kg.generateKey();
            
//            Blowfish B = new Blowfish("@&12X3abc#");
             
          
          
           
            cipher.init(Cipher.ENCRYPT_MODE, key);

            CipherInputStream cipt = new CipherInputStream(new FileInputStream(new File("D:\\Ubuntu_guide@projonmo.pdf")), cipher);

            FileOutputStream fip = new FileOutputStream(new File("D:\\ki.sys"));

            int i;
            while ((i = cipt.read()) != -1) {
                fip.write(i);

            }



            cipher.init(Cipher.DECRYPT_MODE, key);

            CipherInputStream ciptt = new CipherInputStream(new FileInputStream(new File("D:\\ki.sys")), cipher);

            FileOutputStream fop = new FileOutputStream(new File("D:\\OTest.pdf"));

            int j;
            while ((j = ciptt.read()) != -1) {
                fop.write(j);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        try {
            MyImageEncryptor.encryption();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
