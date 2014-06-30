package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * User: gkislin
 * Date: 28.02.14
 */
public class OrganizationSection extends Section {
    private Collection<Organization> organizations;

    public OrganizationSection(Organization... organizations) {
        this.organizations = new ArrayList<>(Arrays.asList(organizations));
    }
}
