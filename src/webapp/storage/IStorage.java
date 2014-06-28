package webapp.storage;

import webapp.model.Resume;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 23.06.2014
 */
public interface IStorage {

    void create(Resume r);

    boolean update(String uuid, Resume r);

    Resume read(String uuid);

    boolean delete(String uuid);

    Collection<Resume> getAll();
}
