package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class MapStorage implements IStorage {

    private Map<String, Resume> MAP = new HashMap<>();

    @Override
    public void clear() {
        MAP.clear();
    }

    @Override
    public void save(Resume r) {
        if (MAP.get(r.getUuid()) != null) {
            throw new WebAppException("Resume " + r.getUuid() + " already exists", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public void update(Resume r) {
        if (MAP.get(r.getUuid()) == null) {
            throw new WebAppException("Resume " + r.getUuid() + " does not exist", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public Resume read(String uuid) {
        Resume resume = MAP.get(uuid);
        if (resume == null) {
            throw new WebAppException("Resume " + uuid + " does not exist", uuid);
        }
        return resume;
    }

    @Override
    public void delete(String uuid) {
        if (MAP.get(uuid) == null) {
            throw new WebAppException("Resume " + uuid + " does not exist", uuid);
        }
        MAP.remove(uuid);
    }

    @Override
    public Collection<Resume> getAll() {
        return MAP.values();
    }
}
