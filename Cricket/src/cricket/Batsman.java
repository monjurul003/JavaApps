/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cricket;

/**
 *
 * @author Rasel
 */
public class Batsman extends Player{
    private String battingOrder;

    public Batsman( String name, String country, String battingOrder) {
        super(name, country);
        this.battingOrder = battingOrder;
        System.out.println("In Batsman constructor batting order is-- " + battingOrder);
        
    }

    public String getBattingOrder() {
        return battingOrder;
    }

    public void setBattingOrder(String battingOrder) {
        this.battingOrder = battingOrder;
    }
    
}
