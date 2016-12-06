/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.inheritance;

import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Rasel
 */
public class Test {

    public static void main(String[] args) {
//    
//        Employee e = new Manager("Himel","123", "CSE");
//        
//        System.out.println(e.getInfo());
//        e = new Admin("rasel", "432","asd","joijo");
//        System.out.println(e.getInfo());
//        
//        Admin obj = (Admin) new Manager("rasel", "asd", "asd");
//          System.out.println(obj.getInfo());
//        
//       
        
        Employee obj1 = new Employee("Ratul", "123");
        Employee obj2 = new Employee("Rasel", "113");
        Employee obj3 = new Employee("Rajib", "133");
        Employee obj4 = new Employee("Rajib", "133");

        ArrayList<Employee> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        System.out.println(list);
        int n = 100_000_000;
        System.out.println(obj1);
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        System.out.println(numberFormat.format(n));
        
        
        
    }

}
