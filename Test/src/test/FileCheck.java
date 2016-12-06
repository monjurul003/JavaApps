/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author Rasel
 */
public class FileCheck {
    
    public static void main(String[] args){
        String str = "hello.txt"+".enc";
        System.out.println(str.substring(0, str.lastIndexOf(".")));
    }
    
}
