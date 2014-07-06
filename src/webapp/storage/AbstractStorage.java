package webapp.storage;

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
    protected Logger LOGGER;

    protected AbstractStorage(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    protected abstract void doClear();
    protected abstract void doSave(Resume r);
    protected abstract void doUpdate(Resume r);
    protected abstract Resume doLoad(String uuid);
    protected abstract void doDelete(String uuid);
    protected abstract Collection<Resume> doGetAll();
    protected abstract int doGetSize();

    @Override
    public void clear() {
        LOGGER.info("Delete all resumes.");
        doClear();
    }

    @Override
    public void save(Resume r) {
        LOGGER.info("Save resume with uuid=" + r.getUuid());
        doSave(r);
    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update resume with " + r.getUuid());
        doUpdate(r);
    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with uuid=" + uuid);
        return doLoad(uuid);
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid=" + uuid);
        doDelete(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        LOGGER.info("getAllSorted");
        List<Resume> list = (List<Resume>) doGetAll();
        Collections.sort(list);
        return list;
    }

    @Override
    public int getSize() {
        LOGGER.info("getSize");
        return doGetSize();
    }
}
