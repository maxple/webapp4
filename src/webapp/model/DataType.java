package webapp.model;

/**
 * Created by 1 on 26.06.2014.
 */
public enum DataType {

    UUID(0),
    FULL_NAME(1),
    LOCATION(2),
    HOME_PAGE(3),
    CONTACTS(4),
    SECTIONS(5),
    TYPE(0),
    VALUE(1),
    CONTENT(0),
    ORGANISATIONS(0),
    ORGANISATION(0),
    PERIODS(1),
    POSITION(1),
    START_DATE(2),
    END_DATE(3);

    private final int n;

    DataType(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
}
