package webapp.model;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public class Resume {

    String fullName;
    String location;
    Collection<Section> sections;

    public Resume() {
    }

    public Resume(String fullName, String location) {
        this.fullName = fullName;
        this.location = location;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
