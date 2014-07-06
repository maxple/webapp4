package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class ArrayStorage extends AbstractStorage {

    private static final int NUMBER = 100;
    private final Resume[] ARRAY = new Resume[NUMBER];

    public ArrayStorage() {
        super(Logger.getLogger(ArrayStorage.class.getName()));
    }

    @Override
    public void doClear() {
        Arrays.fill(ARRAY, null);
    }

    @Override
    public void doSave(Resume r) {
        // insert in first not null array element
        String uuid = r.getUuid();
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null && ARRAY[i].getUuid().equals(uuid)) {
                throw new WebAppException("Resume " + uuid + "already exist", r);
            }
        }

        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] == null) {
                ARRAY[i] = r;
                return;
            }
        }
        throw new WebAppException("Array is full");
    }

    @Override
    public void doUpdate(Resume r) {
        String uuid = r.getUuid();
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null && ARRAY[i].getUuid().equals(uuid)) {
                ARRAY[i] = r;
                return;
            }
        }
        throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
    }

    @Override
    public Resume doLoad(String uuid) {
        for (Resume r : ARRAY) {
            if (r != null && r.getUuid().equals(uuid)) return r;
        }
        throw new WebAppException("Resume " + uuid + "not exist", uuid);
    }

    @Override
    public void doDelete(String uuid) {
        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null && ARRAY[i].getUuid().equals(uuid)) {
                ARRAY[i] = null;
                return;
            }
        }
        throw new WebAppException("Resume " + uuid + " not exist", uuid);
    }

    @Override
    // return all not null elements
    public Collection<Resume> doGetAll() {
        List<Resume> list = new LinkedList<>();
        for (Resume r : ARRAY) {
            if (r != null) {
                list.add(r);
            }
        }
        return list;
    }

    @Override
    public int doGetSize() {
        return getAllSorted().size();
    }
}
