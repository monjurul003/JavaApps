/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MyJavaTestLib;

/**
 *
 * @author monjurul.k
 */
import java.io.FileInputStream;
import java.io.IOException;
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

public class Read_xlsx_xls {

        public static void readXlsx() throws IOException {

                String filename = "C:\\contacts.xlsx"; // or Test.xls
                InputStream input = new FileInputStream(filename);
                String fileExtn = GetFileExtension(filename);
                Workbook wb_xssf; // Declare XSSF WorkBook
                Workbook wb_hssf; // Declare HSSF WorkBook
                // sheet can be used as common for XSSF and HSSF WorkBook
                Sheet sheet = null;

                if (fileExtn.equalsIgnoreCase("xlsx")) {
                        wb_xssf = new XSSFWorkbook(input);
                        printvalues("xlsx=" + wb_xssf.getSheetName(0));
                        sheet = wb_xssf.getSheetAt(0);
                }

                if (fileExtn.equalsIgnoreCase("xls")) {
                        POIFSFileSystem fs = new POIFSFileSystem(input);
                        wb_hssf = new HSSFWorkbook(fs);
                        printvalues("xls=" + wb_hssf.getSheetName(0));
                        sheet = wb_hssf.getSheetAt(0);
                }
                // Now we have rows ready from the sheet
                Iterator rows = sheet.rowIterator();

                while (rows.hasNext()) {

                        Row row = (Row) rows.next();

                        printvalues("Row=" + row.getRowNum() + "");
                        printvalues("**********************");

                        Iterator cells = row.cellIterator();

                        while (cells.hasNext()) {
                                Cell cell = (Cell) cells.next();

                                switch (cell.getCellType()) {

                                case Cell.CELL_TYPE_STRING:
                                        printvalues(cell.getRichStringCellValue().getString());
                                        break;

                                case Cell.CELL_TYPE_NUMERIC:

                                        if (DateUtil.isCellDateFormatted(cell)) {
                                                printvalues(cell.getDateCellValue() + "");
                                        } else {
                                                System.out.println(cell.getNumericCellValue());
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
}
