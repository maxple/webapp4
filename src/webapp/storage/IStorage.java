package webapp.storage;

import webapp.model.Resume;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 23.06.2014
 */
public interface IStorage {

    String create(Resume r);

    void update(Resume r);

    Resume read(String uuid);

    void delete(String uuid);

    Collection<Resume> getAll();
}
