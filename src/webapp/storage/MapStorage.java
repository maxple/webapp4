package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 24.06.2014
 */
public class MapStorage implements IStorage {
    public static final Logger LOGGER = Logger.getLogger(MapStorage.class.getName());

    private Map<String, Resume> MAP = new HashMap<>();

    @Override
    public void clear() {
        LOGGER.info("Delete all resumes.");
        MAP.clear();
    }

    @Override
    public void save(Resume r) {
        LOGGER.info("Save resume with uuid=" + r.getUuid());
        if (MAP.get(r.getUuid()) != null) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update resume with " + r.getUuid());
        if (MAP.get(r.getUuid()) == null) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        }
        MAP.put(r.getUuid(), r);
    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with uuid=" + uuid);
        Resume resume = MAP.get(uuid);
        if (resume == null) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        return resume;
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid=" + uuid);
        if (MAP.get(uuid) == null) {
            throw new WebAppException("Resume " + uuid + " not exist", uuid);
        }
        MAP.remove(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        LOGGER.info("getAllSorted");
        List<Resume> list = new ArrayList<>(MAP.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return MAP.size();
    }
}
