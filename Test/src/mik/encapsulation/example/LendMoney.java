/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.encapsulation.example;

/**
 *
 * @author Rasel
 */
public class LendMoney {
    public static void main(String[] args){
        MickeyFool obj = new MickeyFool();
        int lend = obj.money-100;
        obj.money = obj.money -lend;
        System.out.println(lend);
        System.out.println("Now mickey has only " + obj.money);
        
        Mickey obj2 = new Mickey();
        System.out.println("Mickey dost tor kase koto taka ase??");
        System.out.println("Dosto amar kase to taka ase " + obj2.getMoney());
       
        
        System.out.println("Mickey dost 500 taka dite parbi??");
        System.out.println("Dosto dekhi koto ase? ei ne " + obj2.lendMoney(500));
        
        
        
        
    }
}
