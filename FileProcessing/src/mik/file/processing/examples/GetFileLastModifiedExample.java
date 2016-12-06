/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.file.processing.examples;

import java.io.File;
import java.text.SimpleDateFormat;

public class GetFileLastModifiedExample {

    public static void main(String[] args) {
        File file = new File("D:\\Time Schedul_Spring.docx");

        System.out.println("Before Format : " + file.lastModified());

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        System.out.println("After Format : " + sdf.format(file.lastModified()));
    }
}
