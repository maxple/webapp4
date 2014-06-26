package webapp.model;

import java.util.ArrayList;

public class Resume {

    private Object[] data;

    public Resume() {

        for (DataType dt : DataType.values()) {

            switch (dt) {

                case UUID:
                case FULL_NAME:
                case LOCATION:
                case HOME_PAGE:

                    data[dt.getN()] = new String();
                    break;

                case CONTACTS:

                    data[dt.getN()] = new ArrayList<Contact>();
                    break;

                case SECTIONS:

                    data[dt.getN()] = new ArrayList<Section>();
                    break;
            }
        }
    }

    public void setData(DataType dt, Object data) {

        this.data[dt.getN()] = data;
    }

    public Object getData(DataType dt) {

        return data[dt.getN()];
    }
}
