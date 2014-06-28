package webapp.model;

import java.util.Arrays;
import java.util.Collection;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public class TextSection extends Section {

    private Collection<String> content;

    public TextSection(String... content) {
        this.content = Arrays.asList(content);
    }

    public Collection<String> getContent() {
        return content;
    }

    public void setContent(Collection<String> content) {
        this.content = content;
    }
}
