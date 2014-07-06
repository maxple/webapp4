package webapp.storage;

import webapp.model.*;

import java.io.*;
import java.util.*;

/**
 * User: gkislin
 * Date: 04.07.2014
 */
public class DataStreamStorage extends FileStorage {

    public DataStreamStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(FileOutputStream fos, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(fos)) {
            writeStr(dos, resume.getFullName());
            writeStr(dos, resume.getLocation());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                writeStr(dos, entry.getKey().name());
                writeStr(dos, entry.getValue());
            }

            Section section;
            String sectionClassSimpleName;
            Collection sectionValues;
            Collection<Period> periods;

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {

                section = entry.getValue();
                sectionClassSimpleName = section.getClass().getSimpleName();

                writeStr(dos, sectionClassSimpleName);
                writeStr(dos, entry.getKey().name());

                sectionValues = section.getValues();

                dos.writeInt(sectionValues.size());

                if (TextSection.class.getSimpleName().equals(sectionClassSimpleName)) {
                    for (String val : (Collection<String>) sectionValues) {
                        writeStr(dos, val);
                    }
                } else {
                    for (Organization val : (Collection<Organization>) sectionValues) {
                        writeStr(dos, val.getLink().getName());
                        writeStr(dos, val.getLink().getUrl());

                        periods = val.getPeriods();

                        dos.writeInt(periods.size());

                        for (Period p : periods) {
                            dos.writeInt(p.getStartYear());
                            dos.writeInt(p.getStartMonth());
                            dos.writeInt(p.getEndYear());
                            dos.writeInt(p.getEndMonth());
                            writeStr(dos, p.getPosition());
                            writeStr(dos, p.getContent());
                        }

                    }
                }
            }
        }
    }

    @Override
    protected Resume doRead(FileInputStream fis) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(fis)) {
            r.setFullName(readStr(dis));
            r.setLocation(readStr(dis));

            Collection<String> sectionStringValues;
            Collection<Organization> sectionOrganizationValues;
            Collection<Period> periods;

            boolean isTextSection;
            ContactType contactType;
            SectionType sectionType;
            String sectionClassSimpleName, organizationName, organizationUrl, position, content;
            int sectionValuesSize, periodsSize, startYear, startMonth, endYear, endMonth;

            final int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                contactType = ContactType.valueOf(readStr(dis));
                r.addContact(contactType, readStr(dis));
            }

            final int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {

                sectionClassSimpleName = readStr(dis);

                isTextSection = TextSection.class.getSimpleName().equals(sectionClassSimpleName);

                sectionStringValues = new ArrayList<>();
                sectionOrganizationValues = new ArrayList<>();

                sectionType = SectionType.valueOf(readStr(dis));

                sectionValuesSize = dis.readInt();

                for (int j = 0; j < sectionValuesSize; j++) {

                    if (isTextSection) {
                        sectionStringValues.add(readStr(dis));
                    } else {

                        organizationName = readStr(dis);
                        organizationUrl = readStr(dis);

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
                    }
                }

                if (isTextSection) {
                    r.addSection(sectionType, sectionStringValues.toArray(new String[sectionStringValues.size()]));
                } else {
                    r.addSection(sectionType, sectionOrganizationValues.toArray(new Organization[sectionOrganizationValues.size()]));
                }
            }
        }
        return r;
    }

    private void writeStr(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? "null" : str);
    }

    private String readStr(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals("null") ? null : str;
    }
}
