package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * User: gkislin
 * Date: 28.02.14
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section<Organization> {
    static final long serialVersionUID = 1L;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        super(organizations);
    }
}
