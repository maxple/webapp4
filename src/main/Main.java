package main;

import webapp.model.*;

import java.util.ArrayList;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
public class Main {
    public static void main(String[] args) {

        Resume r1 = new Resume();

        r1.setData(DataType.UUID, "1");
        r1.setData(DataType.FULL_NAME, "Ivan Ivanov");
        r1.setData(DataType.HOME_PAGE, "www.ivan.ivanov.com");
        r1.setData(DataType.CONTACTS, (ArrayList<Contact>) );

    }
}
