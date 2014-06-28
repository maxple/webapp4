package main;

/**
 * User: gkislin
 * Date: 27.06.2014
 */
public class Name {
    String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name1 = (Name) o;

        if (!name.equals(name1.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
