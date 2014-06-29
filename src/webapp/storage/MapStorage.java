package webapp.storage;

import webapp.model.Resume;
import java.util.*;

public class MapStorage implements IStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void create(Resume r) {
        String uuid = r.getUuid();
        if (!map.containsKey(uuid)) map.put(uuid, r);
        // else TODO exception
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        if (map.containsKey(uuid)) map.put(uuid, r);
        // else TODO exception
    }

    @Override
    public Resume read(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (map.remove(uuid) == null); // TODO exception
    }

    @Override
    // return all not null elements
    public Collection<Resume> getAll() {
        return map.values();
    }
}
