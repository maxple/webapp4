package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 04.07.2014
 */
abstract public class FileStorage extends AbstractStorage {
    private File dir;

    public FileStorage(String path) {
        super(Logger.getLogger(FileStorage.class.getName()));
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writable");
        }
    }

    protected abstract void doWrite(FileOutputStream fos, Resume resume) throws IOException;

    protected void write(File file, Resume resume){
        try {
            doWrite(new FileOutputStream(file), resume);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), resume, e);
        }
    }

    protected abstract Resume doRead(FileInputStream fis) throws IOException;

    protected Resume read(File file){
        try {
            Resume r = doRead(new FileInputStream(file));
            r.setUuid(file.getName());
            return r;
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public void doClear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (!file.delete()) {
                throw new WebAppException("File " + file.getAbsolutePath() + " cannot be deleted");
            }
        }
    }

    private File getFile(String uuid) {
        return new File(dir, uuid);
    }

    @Override
    public void doSave(Resume r) {
        File file = getFile(r.getUuid());
        try {
            if (!file.createNewFile()) {
                throw new WebAppException("File " + file.getAbsolutePath() + " exists", r);
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
        write(file, r);
    }

    @Override
    public void doUpdate(Resume r) {
        File file = getFile(r.getUuid());
        if (!file.exists()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " does not exist", r);
        }
        write(file, r);
    }

    @Override
    public Resume doLoad(String uuid) {
        File file = getFile(uuid);
        if (!file.exists()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " does not exist", uuid);
        }
        return read(file);
    }

    @Override
    public void doDelete(String uuid) {
        File file = getFile(uuid);
        if (!file.exists()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " does not exist", uuid);
        }
        if(!file.delete()){
            throw new WebAppException("File " + file.getAbsolutePath() + " cannot be deleted");
        }
    }

    @Override
    public Collection<Resume> doGetAll() {
        List<Resume> list = new LinkedList<>();
        File[] files = dir.listFiles();
        if (files == null) return null;
        for(File file: files) {
            if (!file.exists()) {
                throw new WebAppException("File " + file.getAbsolutePath() + " does not exist");
            }
            list.add(read(file));
        }
        return list;
    }

    @Override
    public int doGetSize() {
        return getAllSorted().size();
    }
}
