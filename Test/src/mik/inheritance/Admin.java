/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.inheritance;

/**
 *
 * @author Rasel
 */
public class Admin extends Manager{
    private String section;

    public Admin(String section, String name, String id, String dept) {
        super(name, id, dept);
        this.section = section;
    }


    
    
}
