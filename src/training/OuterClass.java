package training;

/**
 * Created by 1 on 01.07.2014.
 */
public class OuterClass {

    private String text;
    private static String sText;

    public OuterClass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static String getsText() {
        return sText;
    }

    public static class InnerClass {

        private String text;
        private static final String sfText = "asd";


        public InnerClass(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


    }
}
