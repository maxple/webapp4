package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * User: gkislin
 * Date: 05.02.14
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization();

    private Link link;
    private Collection<Period> periods;

    public Organization() {
        link = Link.EMPTY;
        periods = new LinkedList<>();
    }

    public Organization(String name, String url, Period... periods) {
        this(name, url, new LinkedList<>(Arrays.asList(periods)));
    }

    public Organization(String name, String url, Collection<Period> periods) {
        link = new Link(name, url);
        this.periods = periods;
    }

    public void add(Period p) {
        periods.add(p);
    }

    public Link getLink() {
        return link;
    }

    public Collection<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!link.equals(that.link)) return false;
        if (!periods.equals(that.periods)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", periods=" + periods +
                '}';
    }
}
