package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section<String> {
    public static final TextSection EMPTY = new TextSection("");

    public TextSection(String... content) {
        super(content);
    }
}
