package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class MapStorage extends AbstractStorage {
    private Map<String, Resume> MAP = new HashMap<>();

    public MapStorage() {
        super(Logger.getLogger(MapStorage.class.getName()));
    }

    @Override
    public void doClear() {
        MAP.clear();
    }

    @Override
    public void doSave(Resume r) {
        if (MAP.get(r.getUuid()) != null) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public void doUpdate(Resume r) {
        if (MAP.get(r.getUuid()) == null) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public Resume doLoad(String uuid) {
        Resume resume = MAP.get(uuid);
        if (resume == null) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        return resume;
    }

    @Override
    public void doDelete(String uuid) {
        if (MAP.get(uuid) == null) {
            throw new WebAppException("Resume " + uuid + " not exist", uuid);
        }
        MAP.remove(uuid);
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        List<Resume> list = new ArrayList<>(MAP.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int doGetSize() {
        return MAP.size();
    }
}
