package webapp.model;

/**
 * User: gkislin
 * Date: 31.01.14
 */
public enum SectionType {
    OBJECTIVE("Позиция", SectionClass.TEXT),
    ACHIEVEMENT("Достижения", SectionClass.TEXT),
    QUALIFICATIONS("Квалификация", SectionClass.TEXT),
    EXPERIENCE("Опыт работы", SectionClass.ORGANIZATION),
    EDUCATION("Образование", SectionClass.ORGANIZATION);

    private String title;
    private SectionClass sectionClass;

    SectionType(String title, SectionClass sc) {
        this.title = title;
        this.sectionClass = sc;
    }

    public SectionClass getSectionClass() {
        return sectionClass;
    }

    public String getTitle() {
        return title;
    }
}
