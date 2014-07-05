package webapp.model;

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
}
