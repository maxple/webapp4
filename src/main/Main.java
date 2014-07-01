package main;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gkislin
 * Date: 30.06.2014
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<Integer, String>() {
            {
                put(1, "1");
                put(2, "2");
            }
        };

        System.out.println(m.getClass());
//        Class<Resume> c = Resume.class;
//        Class<Resume> c2 = (new Resume()).getClass();
    }
}
