package webapp.storage;

import webapp.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * User: gkislin
 * Date: 04.07.2014
 */
public class DataStreamStorage extends FileStorage {

    private static final String NULL = "null";

    public DataStreamStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(OutputStream fos, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(fos)) {
            writeStr(dos, resume.getFullName());
            writeStr(dos, resume.getLocation());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                writeStr(dos, entry.getKey().name());
                writeStr(dos, entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                Section section = entry.getValue();
                String sectionClass = section.getClass().getSimpleName();
                writeStr(dos, sectionClass);
                writeStr(dos, entry.getKey().name());
                Collection sectionValues = section.getValues();
                dos.writeInt(sectionValues.size());
                if (TextSection.class.getSimpleName().equals(sectionClass)) {
                    for (String val : (Collection<String>) sectionValues) {
                        writeStr(dos, val);
                    }
                } else {
                    for (Organization val : (Collection<Organization>) sectionValues) {
                        writeStr(dos, val.getLink().getName());
                        writeStr(dos, val.getLink().getUrl());
                        Collection<Period> periods = val.getPeriods();
                        dos.writeInt(periods.size());
                        for (Period p : periods) {
                            dos.writeLong(p.getStartDate().getTime());
                            dos.writeLong(p.getEndDate().getTime());
                            writeStr(dos, p.getPosition());
                            writeStr(dos, p.getContent());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected Resume doRead(InputStream fis) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(fis)) {
            r.setFullName(readStr(dis));
            r.setLocation(readStr(dis));

            final int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                r.addContact(ContactType.valueOf(readStr(dis)), readStr(dis));
            }

            final int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                String sectionClass = readStr(dis);
                boolean isTextSection = TextSection.class.getSimpleName().equals(sectionClass);
                Section section = isTextSection ? new TextSection() : new OrganizationSection();
                SectionType sectionType = SectionType.valueOf(readStr(dis));
                r.addSection(sectionType, section);
                int sectionValuesSize = dis.readInt();

                for (int j = 0; j < sectionValuesSize; j++) {
                    if (isTextSection) {
                        section.add(readStr(dis));
                    } else {
                        // TODO
/*
                        String name = readStr(dis);
                        String url = readStr(dis);
                        periods = new ArrayList<>();

                        periodsSize = dis.readInt();

                        for (int k = 0; k < periodsSize; k++) {

                            startYear = dis.readInt();
                            startMonth = dis.readInt();
                            endYear = dis.readInt();
                            endMonth = dis.readInt();
                            position = readStr(dis);
                            content = readStr(dis);

                            periods.add(new Period(startYear, startMonth, endYear, endMonth, position, content));
                        }

                        sectionOrganizationValues.add(new Organization(organizationName, organizationUrl, periods.toArray(new Period[periods.size()])));
                        new Organization(
                                ,
                                readStr(dis), periods.toArray(new Period[periods.size()]))
*/
                    }
                }
            }
        }
        return r;
    }

    private void writeStr(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readStr(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }
}