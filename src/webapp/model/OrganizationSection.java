package webapp.model;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 28.02.14
 */
public class OrganizationSection extends Section {
    private Collection<Organization> organizations;

    public OrganizationSection(String type) {
        super(type);
    }

    public OrganizationSection(String type, Collection<Organization> organizations) {
        super(type);
        this.organizations = organizations;
    }
}
