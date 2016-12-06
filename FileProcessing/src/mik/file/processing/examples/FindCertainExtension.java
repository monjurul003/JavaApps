/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.file.processing.examples;

import java.io.*;
 
public class FindCertainExtension {
 
	private static final String FILE_DIR = "D:\\folder";
	private static final String FILE_TEXT_EXT = ".jpg";
 
	public static void main(String args[]) {
		new FindCertainExtension().listFile(FILE_DIR, FILE_TEXT_EXT);
	}
 
	public void listFile(String folder, String ext) {
 
		GenericExtFilter filter = new GenericExtFilter(ext);
 
		File dir = new File(folder);
 
		if(dir.isDirectory()==false){
			System.out.println("Directory does not exists : " + FILE_DIR);
			return;
		}
 
		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);
 
		if (list.length == 0) {
			System.out.println("no files end with : " + ext);
			return;
		}
 
		for (String file : list) {
			String temp = new StringBuffer(FILE_DIR).append(File.separator)
					.append(file).toString();
			System.out.println("file : " + temp);
		}
	}
 
	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter {
 
		private String ext;
 
		public GenericExtFilter(String ext) {
			this.ext = ext;
		}
 
		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}
}