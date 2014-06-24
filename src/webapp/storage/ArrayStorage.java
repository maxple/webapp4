package webapp.storage;

import webapp.model.Resume;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class ArrayStorage implements IStorage {
    private static final int NUMBER = 100;

    private Resume[] ARRAY = new Resume[NUMBER];

    @Override
    public String create(Resume r) {
        // insert in first not null array element
        return null;
    }

    @Override
    public void update(Resume r) {
    }

    @Override
    public Resume read(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {
        // assign null
    }

    @Override
    public Collection<Resume> getAll() {
        return null;
    }
}
