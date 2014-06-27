package webapp.model;

/**
 * User: gkislin
 * Date: 20.06.2014
 */
public abstract class Section {
    protected SectionType type;

    public Section(SectionType type) {
        this.type = type;
    }
}
