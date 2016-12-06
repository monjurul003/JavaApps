/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.filesafe.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Monjurul Islam Khan Rasel
 */
public class EncryptorDecryptor {

    Cipher cipher;

    // password for encryption
    String strPassword; //= "Monjuru1I$1@mKh@"; //password length must be 16 charater long
    // put this as key in AES
    SecretKeySpec key;

    public EncryptorDecryptor(String strPassword) {
        this.strPassword = strPassword;
        this.key = new SecretKeySpec(strPassword.getBytes(), "AES");
    }

    public byte[] getEncryptedByteStream(byte[] input) {
        try {
            byte[] encrypted;

            // Parameter specific algorithm
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
            //Whatever you want to encrypt/decrypt
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            // encrypt data
            encrypted = cipher.doFinal(input);

            return encrypted;

        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(EncryptorDecryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public byte[] getDecryptedByteStream(byte[] input) {
        try {
            // Parameter specific algorithm
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
            //Whatever you want to encrypt/decrypt
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            // encrypt data
            byte[] decrypted = cipher.doFinal(input);
            return decrypted;

        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(EncryptorDecryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
