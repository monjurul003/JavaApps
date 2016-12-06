/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.home.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rasel
 */
public class DirectoryProcessor {

    public String currentDir() {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
        return workingDir;
    }

    public ArrayList<File> fileListInADir() {
        File f = new File("C:\\");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        return files;
    }

    public ArrayList<String> getFilesNamesInDir() {
        File f = new File("D:\\");
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        return names;
    }
    
    public void readFile(String fileName){
        BufferedReader br = null;
 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("E:\\"+fileName));
 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
}
