/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MyJavaTestLib;

/**
 *
 * @author monjurul.k
 */
public class Palindrom {

    public int n;

    boolean isPlalindrom( int x){

        int z=x, y=0;
        while(z !=0){
             y = (y<<1) | (z & 1);
                z = z>>1;
        }
        return (x ==y) ? true: false;
    }

}
