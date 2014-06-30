package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public class TextSection extends Section {
    public TextSection(String... content) {
        this.content = new ArrayList<>(Arrays.asList(content));
    }

    private List<String> content;

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public void add(String value) {
        content.add(value);
    }

    @Override
    public String toString() {
        return "Section( " + content +" )";
    }
}
