package webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public abstract class Section<T> implements Serializable {
    private Collection<T> values;

    protected Section() {
        this.values = new LinkedList<>();
    }

    public Section(T[] values) {
        this.values = new LinkedList<>(Arrays.asList(values));
    }

    public void add(T value) {
        values.add(value);
    }

    @Override
    public String toString() {
        return "Section( " + values +" )";
    }

    public Collection<T> getContent() {
        return values;
    }

    public Collection<T> getValues() {
        return values;
    }

    public void setContent(Collection<T> content) {
        this.values = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (!values.equals(section.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }
}
