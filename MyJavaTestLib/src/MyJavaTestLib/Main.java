/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyJavaTestLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 *
 * @author monjurul.k
 */
public class Main {

    /**
     * @param args the command line arguments
     */
      static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        
        DOMConfigurator.configure(userDir+"/conf/MyJavaTestLib_log4jConfig.xml");
        

//        try {
//            // The while checks to see if the data is null. If it is, we've hit
//            //  the end of the file. If not, process the data.
//            // TODO code application logic here
////            readCsv();
//
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }


//        String test2 = test.substring(0, 459);
//            System.out.println(test2);
//        System.out.println(test2.length());

        //******************************Checking contacts type************************************//
//        String test = "00447872308984";
//        System.out.println(getContactsType(test));

        //******************************Checking palin drom************************************//
        logger.info("hello");
        if (isPlalindrom(121)) {
            System.out.println("palindrom");
        } else {
            System.out.println("not palindrom");
        }

        String email_id = "fahimsiddeque@aci-bd.com";
  if (email_id.matches("^[_a-z0-9-]+(\\.[\\_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
            System.out.println("valid");
        }else{
            System.out.println("Not valid");
        }

        String t="prePaid";
        if(t.toLowerCase().contains("pre")){
            System.out.println("Found pre");
        }

//        EmailAddressValidityChecker ec = new EmailAddressValidityChecker();
//        if(ec.checkValidity("fahimsiddeque@aci-bd.com")){
//            System.out.println("valid");
//        }else{
//            System.out.println("Not valid");
//        }

    }

    /**************************************Check Integer Palindrom********************************************/
    static boolean isPlalindrom(int x) {

        int z = x, y = 0, i = 10;
        while (z != 0) {

            y = y * 10;
            y = y + z % 10;
            System.out.println(y);
            i = i * 10;
            z = z / 10;
        }
        return (x == y) ? true : false;
    }

    /**************************************How to read a CSV file********************************************/
    public static void readCsv() throws IOException {
        BufferedReader CSVFile = null;
        try {
            CSVFile = new BufferedReader(new FileReader("C:\\contacts.csv"));
            String dataRow = CSVFile.readLine(); // Read the first line of data.
            // The while checks to see if the data is null. If it is, we've hit
            //  the end of the file. If not, process the data.
            int i = 0;
            while (dataRow != null) {
                String[] dataArray = dataRow.split(",");
                for (String item : dataArray) {
                    if (i == 0) {
                        break;
                    }
                    System.out.print(item + "\t");
                }
                System.out.println(); // Print the data line.
                dataRow = CSVFile.readLine(); // Read next line of data.
                i++;
            }
            // Close the file once all data has been read.
            CSVFile.close();
            // End the printout with a blank line.
            System.out.println();

        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            logger.fatal(ex);
        } finally {
            try {
                CSVFile.close();
            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                logger.fatal(ex);
            }
        }
    }

    /**********************************How to remove duplicate String from a String[]*****************************************/
    public void removeDuplicateStringFromStringArray() {
        String test = "imam; zaman; imam; imam";
        String[] tok = test.split("[;\\s]+");
        List<String> list = Arrays.asList(tok);
        Set<String> set = new HashSet<String>(list);

        System.out.println("Remove duplicate result: " + set.size());

//        Create an array to convert the Set back to array.The Set.toArray() method copythe value  in the set to the defined array.

        String[] result = new String[set.size()];
        set.toArray(result);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }
        for (int i = 0; i < tok.length; i++) {
            System.out.println(tok[i] + ", ");
        }
    }

    /*************************************Read image as byte stream*********************************************/
    public byte[] readImageAsByteStream() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("c:\\Documents and Settings\\monjurul.k\\Desktop\\140KB.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            logger.fatal(ex);
        }
        return null;
    }

    /*************************************Convert a byte[] to Hex String [mostly used for image]*********************************************/
    public String ByteArrrayToHexString(byte[] b) {
        String s = javax.xml.bind.DatatypeConverter.printHexBinary(b);
        return s;
    }

    /*******************How to convert a double value to a  desired format ******************/
    public void convertDoubleValue() {

        double costBasis = Double.valueOf("8801711083848").doubleValue();    // Assume the string "12345.123456789088888" as teh one read from the txtfield
        BigDecimal d = new BigDecimal(costBasis);
        System.out.println("BigDeci =" + d);       // losing the precision and datatype

        NumberFormat formater = new DecimalFormat("0000000000000");
        String cB = formater.format(costBasis);
        System.out.println("costbasis =" + cB);     // If i again convert this string into double the story repeats......
    }

    /*******************************Read xlsx files******************************************/
    public void readXlsxFile() throws IOException {
        // sheet can be used as common for XSSF and HSSF WorkBook
        // or Test.xls
        InputStream input = null;
        try {
            String filename = "C:\\contacts.xlsx";
            input = new FileInputStream(new File(filename));
            String fileExtn = GetFileExtension(filename);
            Workbook wb_xssf; // Declare XSSF WorkBook
            Workbook wb_hssf; // Declare HSSF WorkBook
            // sheet can be used as common for XSSF and HSSF WorkBook
            Sheet sheet = null;
            if (fileExtn.equalsIgnoreCase("xlsx")) {
                wb_xssf = new XSSFWorkbook(input);
//                        printvalues("xlsx=" + wb_xssf.getSheetName(0));
                sheet = wb_xssf.getSheetAt(0);
            }
            if (fileExtn.equalsIgnoreCase("xls")) {
                POIFSFileSystem fs = new POIFSFileSystem(input);
                wb_hssf = new HSSFWorkbook(fs);
//                        printvalues("xls=" + wb_hssf.getSheetName(0));
                sheet = wb_hssf.getSheetAt(0);
            }
            // Now we have rows ready from the sheet
            Iterator rows = sheet.rowIterator();
            System.out.println(sheet.getLastRowNum());
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
//                        printvalues("Row=" + row.getRowNum() + "");
//                        printvalues("**********************");
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
//                                 printvalues(cell.getRichStringCellValue().getString());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            printvalues(cell.getRichStringCellValue().getString());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                printvalues(cell.getDateCellValue() + "");
                            } else {
//                                                System.out.println(cell.getNumericCellValue());
                                double d = cell.getNumericCellValue();
                                NumberFormat formater = new DecimalFormat("000000000000000");
                                String cB = formater.format(d);
                                System.out.println(cB.replaceFirst("^0+(?!$)", ""));
//                                                System.out.println(String.valueOf(d));
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            printvalues(cell.getBooleanCellValue() + "");
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            printvalues(cell.getCellFormula());
                            break;
                        default:
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            logger.fatal(ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                logger.fatal(ex);
            }
        }
    }

    private static String GetFileExtension(String filename) {

        String ext = "";
        int mid = filename.lastIndexOf(".");
        ext = filename.substring(mid + 1, filename.length());
        return ext;

    }

    private static void printvalues(String content) {
        System.out.println(content);
    }

    public static String getContactsType(String mobNo) {
        String conType = "";
        if (mobNo.startsWith("+88017") || mobNo.startsWith("88017") || mobNo.startsWith("017")) {
            conType = conType + "onnet";
        } else {
            if (mobNo.startsWith("+8801") || mobNo.startsWith("8801") || mobNo.startsWith("019") || mobNo.startsWith("016") || mobNo.startsWith("018") || mobNo.startsWith("015") || mobNo.startsWith("011")) {
                conType = conType + "offnet";
            } else {
                conType = conType + "International";
            }
        }

//        Logger.getLogger(WscPhonebook.class.getName()).log(Level.INFO, "WscPhonebook:: getContactTypes()--" + conType);
        return conType;
    }
}





