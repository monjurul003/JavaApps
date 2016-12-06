/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.file.processing.examples;

import java.io.File;

public class DeleteFileExample {

    public static void main(String[] args) {
        try {

            File file = new File("D:" + File.separator + "hahaha.txt");

            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
