/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraylistexample;

import java.util.ArrayList;

/**
 *
 * @author MIK
 */
public class ArrayListExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<User> list = new ArrayList<User>();
        String s = "Rasel;01614728542%Noman;01919879879%Tomal;01769987987%Anis;018768768768";
        String[] usr = s.split("%"); //split the string with % sign and got the user information
        //Print the list to see the splitting output
        for (int i = 0; i < usr.length; i++) {
            System.out.println(usr[i]);
        }
        // now we are going to split one more time the user array to assign it to the list
        for (int i = 0; i < usr.length; i++) {
            String[] str = usr[i].split(";");

            User obj = new User(str[0], str[1]);
            list.add(obj);
        }

        System.out.println("Printing the array list");
        //print the list
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Name:: " + list.get(i).getName());
            System.out.println("Number:: " + list.get(i).getNumber());
        }
        //serch for the name Tomal
        String search = "Tomal";
        boolean f = false;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (search.equals(list.get(i).getName())) {
                f = true;
                index = i;
            }
        }
        if (f == true) {
            System.out.println("Found the entry of Tomal");
            System.out.println("Name:: " + list.get(index).getName());
            System.out.println("Number:: " + list.get(index).getNumber());

            //We are going to change the number of Tomal
            list.get(index).setNumber("011243324534"); //arraylist updated 
            //print the list after updating na element
            System.out.println("Printing the updated List");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("Name:: " + list.get(i).getName());
                System.out.println("Number:: " + list.get(i).getNumber());
            }

            //if we want to remove the entry of tomal then we have to perform that command
            list.remove(index);
            //print the list after removing an element
            System.out.println("Print the list after deleting the entry of tomal::");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("Name:: " + list.get(i).getName());
                System.out.println("Number:: " + list.get(i).getNumber());
            }
        } else {
            System.out.println("Not found");
        }
    }

}
