/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Rasel
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        Calculator calc = new Calculator(10, 20);
//        
//        System.out.println("Summation is -- "+ calc.add());
        String userDir = System.getProperty("user.dir");
        System.out.println("Summation is -- " + userDir);
        int n = 5;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 3) {
                    break;
                }

                System.out.println(i);
            }
            System.out.println("Outside the inner for loop");
        }
        System.out.println("Outside the outer for loop");

        ExplorerFrame exFrame = new ExplorerFrame();
        exFrame.initExplorer();

    }

}
