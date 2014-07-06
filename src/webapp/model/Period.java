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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (endMonth != period.endMonth) return false;
        if (endYear != period.endYear) return false;
        if (startMonth != period.startMonth) return false;
        if (startYear != period.startYear) return false;
        if (content != null ? !content.equals(period.content) : period.content != null) return false;
        if (position != null ? !position.equals(period.position) : period.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startYear;
        result = 31 * result + startMonth;
        result = 31 * result + endYear;
        result = 31 * result + endMonth;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
