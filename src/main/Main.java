package main;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gkislin
 * Date: 16.06.2014
 */
abstract public class Main {
    public static void main(String[] args) {
        Map<Name, Integer> map = new HashMap<>();
        Name name = new Name("Ivan");
        map.put(name, 5);
        System.out.println(map.get(name));
        name.name  = "Dmitry";
        System.out.println(map.get(name));
    }

    static void print(int i) {
        System.out.println(i);
    }
}

