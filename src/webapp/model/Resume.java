package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Resume {

    public enum DataType {

        UUID(0),
        FULL_NAME(1),
        LOCATION(2),
        HOME_PAGE(3),
        CONTACT(4),
        SECTION(5);

        private final int n;

        DataType(int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }
    }

    private Object[] data;

    public Resume() {

        for (DataType dt : DataType.values())
            switch (dt) {

                case UUID:
                case FULL_NAME:
                case LOCATION:
                case HOME_PAGE:

                    data[dt.getN()] = new String();
                    break;

                case CONTACT:

                    data[dt.getN()] = new ArrayList<Contact>();
                    break;

                case SECTION:

                    data[dt.getN()] = new ArrayList<Section>();
                    break;
            }
    }

    public void setData(DataType dt, String data) {

        switch (dt) {

            case UUID:
            case FULL_NAME:
            case LOCATION:
            case HOME_PAGE:

                this.data[dt.getN()] = data;
                break;
        }
    }

    public String getData(DataType dt) {

        switch (dt) {

            case UUID:
            case FULL_NAME:
            case LOCATION:
            case HOME_PAGE:

                return (String)this.data[dt.getN()];
        }

        return null;
    }

    public void add(DataType dt, Object data) {

        switch (dt) {

            case CONTACT:

                ((ArrayList<Contact>)(this.data[dt.getN()])).add((Contact)data);
                break;

            case SECTION:

                ((ArrayList<Section>)(this.data[dt.getN()])).add((Section)data);
                break;
        }
    }

    @Override
    public String toString() {
        return "Resume{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
