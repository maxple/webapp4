package webapp.model;

import java.util.Date;

/**
 * User: gkislin
 * Date: 31.01.14
 */
public class Period {
    public static final Period EMPTY = new Period();

    private Date startDate;
    private Date endDate;
    private String position;
    private String content;

    public Period() {
    }

    public Period(Date startDate, Date endDate, String position, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

}
