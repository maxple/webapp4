package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * User: gkislin
 * Date: 05.02.14
 */
public class Organization {
    public static final Organization EMPTY = new Organization();

    private Link link;
    private Collection<Period> periods;

    public Organization() {
        link = Link.EMPTY;
    }

    public Organization(String name, String url, Period... periods) {
        link = new Link(name, url);
        this.periods = new ArrayList<>(Arrays.asList(periods));
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
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", periods=" + periods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (periods != null ? !periods.equals(that.periods) : that.periods != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }
}
