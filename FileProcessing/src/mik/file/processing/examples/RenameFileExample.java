/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.file.processing.examples;

import java.io.File;

public class RenameFileExample {

    public static void main(String[] args) {

        File oldfile = new File("oldfile.txt");
        File newfile = new File("newfile.txt");

        if (oldfile.renameTo(newfile)) {
            System.out.println("Rename succesful");
        } else {
            System.out.println("Rename failed");
        }

    }
}
