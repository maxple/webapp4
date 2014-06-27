package webapp.model;

/**
 * User: gkislin
 * Date: 23.06.2014
 */
public class Contact {

    private ContactType type;
    private String value;

    public Contact() {
    }

    public Contact(ContactType type, String value) {
          this.type = type;
          this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
