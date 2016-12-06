/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

/**
 *
 * @author Daffodil PC
 */
public class Mixed {

    public void useOfGoTo() {
        int MAX_I = 5, MAX_J = 3;
      
        loops:
        for (int i = 0; i < MAX_I; i++) {

            for (int j = 0; j < MAX_J; j++) {
                // do stuff
                System.out.print(j + " ");
                if (j == 2) {
                    break loops;
                }
            }
        }
        System.out.println("\n Outside the  loop ");
    }
    public void whileLoopExample(){
        int i=0, sum=0;
        
        while(i<10){
            sum = sum +i;
            i++;
        }
        System.out.println("The sum is-- " + sum);
    }
}
