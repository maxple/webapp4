package webapp.model;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public class Resume {

    public static String CONST = "CONSTANT";

    private String uuid;
    private String fullName;
    private String location;
    private Collection<Contact> contacts;
    private Collection<Section> sections;

    public Resume() {
    }

    public Resume(String uuid, String fullName, String location, Collection<Contact> contacts, Collection<Section> sections) {
        this.fullName = fullName;
        this.location = location;
        this.contacts = contacts;
        this.sections = sections;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void addSection(Section s) {
        this.sections.add(s);
    }

    public Collection<Section> getSections() {
        return sections;
    }

    public void addContact(Contact c) {
        this.contacts.add(c);
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!contacts.equals(resume.contacts)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (location != null ? !location.equals(resume.location) : resume.location != null) return false;
        if (!sections.equals(resume.sections)) return false;
        if (!uuid.equals(resume.uuid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + contacts.hashCode();
        result = 31 * result + sections.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}
