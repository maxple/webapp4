package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class ArrayStorage implements IStorage {

    private static final int NUMBER = 100;
    private final Resume[] ARRAY = new Resume[NUMBER];

    @Override
    public void clear() {
       Arrays.fill(ARRAY, null);
    }

    @Override
    public void create(Resume r) {
        // insert in first not null array element
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] == null) {
                ARRAY[i] = r;
                return;
            }
        }
        // TODO exception
    }

    @Override
    public void update(Resume r) {
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null && ARRAY[i].getUuid().equals(r.getUuid())) {
                ARRAY[i] = r;
                return;
            }
        }
        // TODO exception
    }

    @Override
    public Resume read(String uuid) {
        for (Resume r : ARRAY) {
            if (r != null && r.getUuid().equals(uuid)) return r;
        }
        // TODO exception
        return null;
    }

    @Override
    public void delete(String uuid) {
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null && ARRAY[i].getUuid().equals(uuid)) {
                ARRAY[i] = null;
                return;
            }
        }
        // TODO exception
    }

    @Override
    // return all not null elements
    public Collection<Resume> getAll() {
        Collection<Resume> col = Arrays.asList(ARRAY);
        Iterator<Resume> it = col.iterator();
        while (it.hasNext()) {
            Resume r = it.next();
            if (r == null) {
                it.remove();
            }
        }
        return col;
    }
}
