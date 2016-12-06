/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package StringProcessing;

import java.text.DateFormatSymbols;

/**
 *
 * @author monjurul.k
 */
public class StringProcessor {

    public static void main(String[] args){

        String str = "20130918164839000";
        System.out.println(Integer.parseInt(str.substring(4, 6)));
        String str1 = str.substring(6, 8) +"-"+ getMonthForInt(Integer.parseInt(str.substring(4, 6)))+"-" +str.substring(0, 4)+" "+str.substring(8, 10)+":"+str.substring(10, 12)+":"+str.substring(12, 14);
        System.out.println(str1);
    }
   public static String getMonthForInt(int num) {
        String month = "wrong";
        num=num-1;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    public static void parseString(){
       String test ="8801711083848,8801711081503,8801760819280,8801720349906,8801717120109";
         String[] testMsisdn = test.split(",");

        for (int i = 0; i < testMsisdn.length; i++) {
            System.out.println(testMsisdn[i]);
        }
    }
    
}
