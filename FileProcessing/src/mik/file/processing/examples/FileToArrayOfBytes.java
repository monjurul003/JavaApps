/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.file.processing.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileToArrayOfBytes {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;

        File file = new File("D:\\testing.txt");

        byte[] bFile = new byte[(int) file.length()];

        try {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char) bFile[i]);
            }

            System.out.println("\nDone");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
