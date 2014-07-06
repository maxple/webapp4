package webapp.storage;

import webapp.model.ContactType;
import webapp.model.Resume;
import webapp.model.Section;
import webapp.model.SectionType;

import java.io.*;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

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
            dos.writeUTF(resume.getFullName());
            dos.writeUTF(resume.getLocation());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for(Map.Entry<ContactType, String> entry: contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

//            Map<SectionType, Section> sections = resume.getSections();
//            dos.writeInt(sections.size());
//            for(Map.Entry<SectionType, Section> entry: sections.entrySet()) {
//                dos.writeUTF(entry.getKey().getTitle());
//                dos.writeUTF(entry.getValue());
//            }
            // TODO
        }
    }

    @Override
    protected Resume doRead(FileInputStream fis) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(fis)) {
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());

            final int n = dis.readInt();
            for(int i = 0; i < n; i++) {
                ContactType contact = ContactType.valueOf(dis.readUTF());
                r.addContact(contact, dis.readUTF());
            }

            // TODO
        }
        return r;
    }
}
