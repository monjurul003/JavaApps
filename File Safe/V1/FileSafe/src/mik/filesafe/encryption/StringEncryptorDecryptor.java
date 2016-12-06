/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.filesafe.encryption;

import mik.filesafe.utility.Blowfish;


/**
 *
 * @author Daffodil PC
 */
public class StringEncryptorDecryptor {

    Blowfish bf;

    public StringEncryptorDecryptor() {
        this.bf = new Blowfish("@&12X3abc#");
    }

    public String[] encryptStringArray(String[] inputList) {

        for (int i = 0; i < inputList.length; i++) {
           inputList[i]= bf.encryptString(inputList[i].trim());
        }
        return inputList;
    }

    public String[] decryptStringArray(String[] inputList) {
        String[] decryptedStringList = new String[inputList.length];

        for (int i = 0; i < inputList.length; i++) {
            decryptedStringList[i] = bf.decryptString(inputList[i].trim());
        }
        return decryptedStringList;
    }
}
