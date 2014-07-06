package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public abstract class Section<T> {

    private Collection<T> values;

    public Section(T[] values) {
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    public void add(T value) {
        values.add(value);
    }

    @Override
    public String toString() {
        return "Section( " + values +" )";
    }

    public Collection<T> getValues() {
        return values;
    }

    public void setValues(Collection<T> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (values != null ? !values.equals(section.values) : section.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
