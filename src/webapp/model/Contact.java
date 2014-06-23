package webapp.model;

/**
 * User: gkislin
 * Date: 23.06.2014
 */
public class Contact {
    String type;
    String value;

    public Contact() {
    }

    public Contact(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
