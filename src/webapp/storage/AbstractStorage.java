package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 04.07.2014
 */
abstract public class AbstractStorage implements IStorage {
    public static final Logger LOGGER = Logger.getLogger(MapStorage.class.getName());

    @Override
    public void clear() {
        LOGGER.info("Delete all resumes.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean exist(String uuid);

    @Override
    public void save(Resume r) {
        LOGGER.info("Save resume with uuid=" + r.getUuid());
        if (exist(r.getUuid())) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        }
        doSave(r);
    }

    protected abstract void doSave(Resume r);

    @Override
    public void update(Resume r) {
        LOGGER.info("Update resume with " + r.getUuid());
        if (!exist(r.getUuid())) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        }
        doUpdate(r);
    }

    protected abstract void doUpdate(Resume r);

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with uuid=" + uuid);
        if (!exist(uuid)) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid=" + uuid);
        if (!exist(uuid)) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        LOGGER.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    public abstract int size();
}
