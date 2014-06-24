package webapp.model;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 05.02.14
 */
public class Organization {

    private Link link;
    private Collection<Period> periods;

    public Organization() {
    }

    public Organization(String name, String url, Collection<Period> periods) {
        this.link = new Link(name, url);
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
