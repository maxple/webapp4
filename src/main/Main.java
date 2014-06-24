package main;

import webapp.model.Resume;
import webapp.model.Section;
import webapp.model.TextSection;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
abstract public class Main {
    public static void main(String[] args) {
        Resume r1 = new Resume();
        Resume r2 = r1;
        System.out.println(r1.equals(r2));

        Section c = new TextSection("QUALIFICATION", null);
        if (c instanceof TextSection) {
            ((TextSection) c).getContent();
        }

        System.out.println(
                r1.getFullName().equals("Ivan") ? "OK" : "NOK"
        );

        Integer i = 5;
        System.out.println(i.intValue());
        print(i);
    }

    static void print(int i) {
        System.out.println(i);
    }
}

