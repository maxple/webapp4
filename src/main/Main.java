package main;

import webapp.model.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Contact c1 = new Contact();

        c1.setData(Contact.DataType.TYPE, "Mobile phone");
        c1.setData(Contact.DataType.VALUE, "+79111234567");

        Resume r1 = new Resume();

        r1.setData(Resume.DataType.UUID, "1");
        r1.setData(Resume.DataType.FULL_NAME, "Ivan Ivanov");
        r1.setData(Resume.DataType.LOCATION, "SPb");
        r1.setData(Resume.DataType.HOME_PAGE, "www.ivan.ivanov.com");
        r1.add(Resume.DataType.CONTACT, c1);
    }
}
