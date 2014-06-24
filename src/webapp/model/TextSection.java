package webapp.model;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public class TextSection extends Section {
    public TextSection(String type, Collection<String> content) {
        super(type);
        this.content = content;
    }

    private Collection<String> content;

    public Collection<String> getContent() {
        return content;
    }

    public void setContent(Collection<String> content) {
        this.content = content;
    }
}