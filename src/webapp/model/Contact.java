package webapp.model;

public class Contact {

    private Object[] data;

    public Contact() {

        for (DataType dt : DataType.values()) {

            switch (dt) {

                case TYPE:
                case VALUE:

                    data[dt.getN()] = new String();
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
