package main;

import webapp.model.Resume;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
public class Main {
    /**
     * First java program
     *
     * @param args args[0]: name
     */
    public static void main(String[] args) {
        Resume r = new Resume("Ivan Ivanov");
        Resume r2 = new Resume();
/*
        r.setFullName("Ivan Ivanov");
        System.out.println(r2.getFullName());
*/
        System.out.println(r2.getClass());
        System.out.println(Resume.class);
        System.out.println(Resume.class==r.getClass());
    }
}
