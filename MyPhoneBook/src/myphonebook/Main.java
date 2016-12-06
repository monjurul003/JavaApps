/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myphonebook;

import java.util.Scanner;

/**
 *
 * @author Diu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scn = new Scanner(System.in);
        UserProcessor up = new UserProcessor();
        up.initArrayList("E:", "phonebook.txt");
        while (true) {
            System.out.println("Please insert your choice:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Search");
            System.out.println("4.Delete");
            System.out.println("5.Save");
            System.out.println("99.Quit!");
            int choice = scn.nextInt();
            if (choice == 99) {
                break;
            } else if (choice == 1) {
                System.out.println("Insert name:");
                String name = scn.next();
                System.out.println("Insert number:");
                String numb = scn.next();
                User obj = new User(name, numb);
                up.getList().add(obj);

            } else if (choice == 2) {
                up.printList();
            } else if (choice == 3) {
                
            } else if (choice == 4) {
                boolean found = false;
                int index = -99;
                System.out.println("Insert name what you want to delete:");
                String name = scn.next();
                for (int i = 0; i < up.getList().size(); i++) {
                    if(name.equalsIgnoreCase(up.getList().get(i).getName())){
                        found = true;
                        index = i;
                        break;
                    }
                }
                if(found){
                    up.getList().remove(index);
                    up.writeStringArrayInFile("E:", "phonebook.txt", up.arrayListToStringArray());
                }
            } else if (choice == 5) {
                up.writeStringArrayInFile("E:", "phonebook.txt", up.arrayListToStringArray());
            } else {
                System.out.println("Insert valid input!!");
            }

        }
        
    }
}
