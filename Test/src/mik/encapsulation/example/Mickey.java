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
public class Mickey {

    private int money;

    public Mickey() {
        this.money = 1000;
    }

    public int getMoney() {
        return money-700;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public int lendMoney(int amount){
        if(money - amount <500){
            return 0;
        }else{
            money = money-amount;
            return amount;
        }
    }

}
