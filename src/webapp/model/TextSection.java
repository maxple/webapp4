package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section<String> {
    static final long serialVersionUID = 1L;

    public static final TextSection EMPTY = new TextSection("");

    public TextSection() {
    }

    public TextSection(String... content) {
        super(content);
    }
}
