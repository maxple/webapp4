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

    public Collection<T> getContent() {
        return values;
    }

    public void setContent(Collection<T> content) {
        this.values = content;
    }
}
