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
public class Player {
    private String name, country;

    public Player(String name, String country) {
        this.name = name;
        this.country = country;
        System.out.println("In Player constructor player information is name--"+this.name+" and plays for -- "+this.country );
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
    
    
}
