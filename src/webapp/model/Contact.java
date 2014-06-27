package webapp.model;

public class Contact {

    public enum DataType {

        TYPE(0),
        VALUE(1);

        private final int n;

        DataType(int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }
    }

    private Object[] data;

    public Contact() {

        for (DataType dt : DataType.values()) data[dt.getN()] = new String();
    }

    public void setData(DataType dt, String data) {

        this.data[dt.getN()] = data;
    }

    public String getData(DataType dt) {

        return (String)data[dt.getN()];
    }
}
