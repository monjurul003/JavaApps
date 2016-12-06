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
public class Manager  extends Employee{
    private String dept;

    public Manager( String name, String id,String dept) {
        super(name, id);
        this.dept = dept;
    }

//    @Override
    public String getInfo() {
        return super.getInfo()+ "Department: "+this.dept; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
