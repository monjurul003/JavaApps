/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acm_problemset;

import java.util.Scanner;

/**
 *
 * @author MIK
 */
public class Acm_problemset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("test");
        Scanner scn = new Scanner(System.in);
        int n=0;
        while((n = scn.nextInt()) != -1){
            int m=0, f=1, t=1, nm=0, nf = 0;
            for (int i = 1; i <= n; i++) {
                nf = m+1;
                nm = f + m;
                t  =  nm + nf; 
                m = nm; 
                f = nf; 
            }
            System.out.println(m +", "+t);
        }
        
    }
    
}
