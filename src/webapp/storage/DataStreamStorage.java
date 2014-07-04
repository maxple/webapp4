package webapp.storage;

import webapp.model.ContactType;
import webapp.model.Resume;

import java.io.*;
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
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(fos);
            dos.writeUTF(resume.getFullName());
            dos.writeUTF(resume.getLocation());
            Map<ContactType, String> contacts = resume.getContacts();
        } finally {
            if (dos != null) {
                dos.close();
            }
        }
    }

    @Override
    protected Resume doRead(FileInputStream fis) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(fis)) {
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
        }
        return r;
    }
}
