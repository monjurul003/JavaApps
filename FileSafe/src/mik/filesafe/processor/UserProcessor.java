/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.filesafe.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mik.filesafe.entity.User;

/**
 *
 * @author Daffodil PC
 */
public class UserProcessor {

    private List<User> userList;

    public UserProcessor() {
        userList = new ArrayList<User>();
    }

    public UserProcessor(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserListFromStringArray(String[] inputArray) {
        StringProcessor sp = new StringProcessor();

        for (int i = 0; i < inputArray.length; i++) {
            String[] list = inputArray[i].split(";");
            User user = new User(list[0], list[1],list[2]);
            user.setLastModifiedDate(list[3]);
            userList.add(user);
            System.out.println(user.getUserName());
        }
        return userList;
    }

    public Date getCurrentTimeAndDate() {
        Date date = new Date();
        return date;
    }

    public String[] getUserListAsStringArray() {
        String[] strArray = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            strArray[i] = userList.get(i).getUserName() + ";" + userList.get(i).getPassword() + ";" + userList.get(i).getCreationDate().toString() + ";" + userList.get(i).getLastModifiedDate().toString();
        }
        return strArray;
    }
    public String[] getUserListAsStringArray(List<User> usersList) {
        String[] strArray = new String[usersList.size()];
        for (int i = 0; i < usersList.size(); i++) {
            strArray[i] = usersList.get(i).getUserName() + ";" + usersList.get(i).getPassword() + ";" + usersList.get(i).getCreationDate().toString() + ";" + usersList.get(i).getLastModifiedDate().toString();
        }
        return strArray;
    }
}
