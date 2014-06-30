package main;

import webapp.model.Resume;
import webapp.model.SectionType;
import webapp.model.TextSection;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
abstract public class Main {
    public static void main(String[] args) {
        Resume r = new Resume();
        r.addSection(SectionType.QUALIFICATIONS, "Java", "PHP");
        TextSection ts = (TextSection) r.getSections(SectionType.QUALIFICATIONS);
        ts.add("Python");
        System.out.println(r.toString());
    }

    static void print(int i) {
        System.out.println(i);
    }
}

