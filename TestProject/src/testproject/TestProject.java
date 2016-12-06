/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import mik.file.processing.FileProcessor;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Daffodil PC
 */
public class TestProject {

    static Logger logger = Logger.getLogger(TestProject.class);

    public static void loggerInitialization() {
        String workingDir = System.getProperty("user.dir");
        DOMConfigurator.configure(workingDir + "\\config\\log4jConfig.xml");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loggerInitialization();
        Mixed mx = new Mixed();
        mx.whileLoopExample();

        FileProcessor filePro = new FileProcessor();
//        filePro.createAndInitFileWithStringContent("G:", "Hello.txt", "FirstLine");
//        filePro.appendFileWithStringContent("G:", "Hello.txt", "SecondLine");
//        filePro.changeFileToReadOnly("G:", "Hello.txt");
        filePro.listFile("D:\\Pictures\\pic-final exam refreshment", "jpg");
    }

}
