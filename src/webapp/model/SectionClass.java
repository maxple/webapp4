package webapp.model;

/**
 * User: gkislin
 * Date: 08.07.2014
 */
public enum SectionClass {
    TEXT {
        @Override
        public Section create() {
            return new TextSection();
        }
    },
    ORGANIZATION {
        @Override
        public Section create() {
            return new OrganizationSection();
        }
    };

    public abstract Section create();
}
