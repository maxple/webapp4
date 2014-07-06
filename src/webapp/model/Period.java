package webapp.model;

import java.util.Date;

/**
 * User: gkislin
 * Date: 31.01.14
 */
public class Period {
    public static final Period EMPTY = new Period();

    private int startYear;
    private int startMonth;
    private int endYear;
    private int endMonth;
    private String position;
    private String content;

    public Period() {
    }

    public Period(int startYear, int startMonth, int endYear, int endMonth, String position, String content) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.position = position;
        this.content = content;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public String getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startYear=" + startYear +
                ", startMonth=" + startMonth +
                ", endYear=" + endYear +
                ", endMonth=" + endMonth +
                ", position='" + position + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
