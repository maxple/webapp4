package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class ArrayStorage extends AbstractStorage<Integer> {

    private static final int NUMBER = 100;
    private final Resume[] ARRAY = new Resume[NUMBER];

    public ArrayStorage() {
        super(Logger.getLogger(ArrayStorage.class.getName()));
    }

    @Override
    protected Integer getCtx(String uuid) {

        Integer uuidIndex = -1, nullIndex = -1;

        for (int i = 0; i < NUMBER; i++) {
            if (ARRAY[i] != null) {
                if (ARRAY[i].getUuid().equals(uuid)) {
                    uuidIndex = i;
                    break;
                }
            } else nullIndex = i;
        }

        if (uuidIndex == -1) {
            if (nullIndex == -1) throw new WebAppException("Index is not found");
            return nullIndex;
        }

        return uuidIndex;
    }

    @Override
    protected boolean exist(Integer index) {
        return ARRAY[index] != null;
    }

    @Override
    public void doClear() {
        Arrays.fill(ARRAY, null);
    }

    @Override
    public void doSave(Integer index, Resume r) {
        ARRAY[index] = r;
    }

    @Override
    public void doUpdate(Integer index, Resume r) {
        ARRAY[index] = r;
    }

    @Override
    public Resume doLoad(Integer index) {
        return ARRAY[index];
    }

    @Override
    public void doDelete(Integer index) {
        ARRAY[index] = null;
    }

    @Override
    // return all not null elements
    public List<Resume> doGetAll() {
        List<Resume> list = new LinkedList<>();
        for (Resume r : ARRAY) if (r != null) list.add(r);
        return list;
    }

    @Override
    public int size() {
        return doGetAll().size();
    }
}
