package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * User: gkislin
 * Date: 04.07.2014
 */
abstract public class FileStorage implements IStorage {
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writable");
        }
    }

    protected void write(File file, Resume resume){
        try {
            doWrite(new FileOutputStream(file), resume);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), resume, e);
        }
    }

    protected abstract void doWrite(FileOutputStream fos, Resume resume) throws IOException;
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
    public void clear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (!file.delete()) {
                throw new WebAppException("File " + file.getAbsolutePath() + " cannot be deleted");
            }
        }
    }

    @Override
    public void save(Resume r) {
        File file = getFile(r.getUuid());
        if (file.exists()) {
            //TODO
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
        write(file, r);
    }

    private File getFile(String uuid) {
        return new File(dir, uuid);
    }

    @Override
    public void update(Resume r) {
        File file = getFile(r.getUuid());
        //TODO
        write(file, r);
    }

    @Override
    public Resume load(String uuid) {
        File file = getFile(uuid);
        //TODO
        return read(file);
    }

    @Override
    public void delete(String uuid) {
        File file = getFile(uuid);
        //TODO
        if(file.delete()){
            throw new WebAppException("File " + file.getAbsolutePath() + " cannot be deleted");
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        // TODO
        return null;
    }

    @Override
    public int size() {
        // TODO;
        return 0;
    }
}
