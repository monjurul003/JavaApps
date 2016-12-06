/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.file.processing.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ArrayOfBytesToFile {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;

        File file = new File("D:\\testing.txt");

        byte[] bFile = new byte[(int) file.length()];

        try {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            //convert array of bytes into file
            FileOutputStream fileOuputStream
                    = new FileOutputStream("D:\\testing2.txt");
            fileOuputStream.write(bFile);
            fileOuputStream.close();

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
