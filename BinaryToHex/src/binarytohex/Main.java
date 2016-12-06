/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarytohex;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author monjurul.k
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        byte[] b = readImageAsByteStream();
          System.out.println("byte string--" + b.toString());
        String hexString = javax.xml.bind.DatatypeConverter.printHexBinary(b);
        System.out.println("Hex string--" + hexString);


    }

     /*************************************Read image as byte stream*********************************************/
    public static byte[] readImageAsByteStream() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("C:\\140KB.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }


}
