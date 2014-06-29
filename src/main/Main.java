package main;

import webapp.model.Resume;
import webapp.storage.MapStorage;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
abstract public class Main {
    public static void main(String[] args) {

        MapStorage ms = new MapStorage();

        ms.create(new Resume("1","Ivan Ivanov","Moscow"));
        ms.create(new Resume("2","Petr Petrov","SPb"));
        ms.create(new Resume("3","Sidor Sidorov","Ufa"));

        for (Resume r : ms.getAll()) {
            System.out.println(r);
        }
    }
}
