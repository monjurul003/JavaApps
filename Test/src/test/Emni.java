/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Rasel
 */
public class Emni {

    public void printHello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        Emni obj = new Emni();
        obj.printHello();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        System.out.println(sdf.format(today.getTime()));
        System.out.println(today);
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();
        
        while (list.size() < 10) {
            boolean match = false;
            int j, a;

            a = rand.nextInt() % 10;

            a = a < 0 ? a * -1 : a;

            for (j = 0; j < list.size(); j++) {
                if (a == list.get(j)) {
                    match = true;
                    break;
                }
            }
            if (match == false) {
                list.add(a);
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i)+ ", ");
        }
//        Random rand = new Random();
//        int e;
//        int i;
//        int g = 10;
//        HashSet<Integer> randomNumbers = new HashSet<Integer>();
//
//        for (i = 0; i < g; i++) {
//            e = rand.nextInt(10);
//            System.out.print(e + " ");
//            randomNumbers.add(e);
//            if (randomNumbers.size() <= 10) {
//                if (randomNumbers.size() == 10) {
//                    g = 10;
//                }
//                g++;
//                randomNumbers.add(e);
//            }
//        }
//        System.out.println("Ten Unique random numbers from 1 to 20 are  : " + randomNumbers);
    }
}
