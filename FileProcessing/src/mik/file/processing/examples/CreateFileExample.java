/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.file.processing.examples;

import java.io.File;
import java.io.IOException;
 
public class CreateFileExample 
{
    public static void main( String[] args )
    {	
    	try {
 
	      File file = new File("D:\\newfile.txt");
 
	      if (file.createNewFile()){
	        System.out.println("File is created!");
	      }else{
	        System.out.println("File already exists.");
	      }
 
    	} catch (IOException e) {
	      e.printStackTrace();
	}
    }
}