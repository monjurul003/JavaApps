/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package password_encryption;

/**
 *
 * @author monjurul.k
 */
public class Password_encryption {
    public static void main(String[] args){
        Blowfish bf = new Blowfish("R@sel123");  //@&12X3abc#
        String str = bf.encryptString("R@sel123");
        System.out.println("Encrypted password-- "+str);

        System.out.println("Decrypted password-- "+bf.decryptString("c1a4038117dc625566422cbf431fc54cc149d16a3af2c84b38f36b45afc6d653"));
    }
}
