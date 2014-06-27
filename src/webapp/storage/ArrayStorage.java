package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayStorage implements IStorage {

    private int i;

    private static final int NUMBER = 100;

    private Resume[] ARRAY = new Resume[NUMBER];

    @Override
    public String create(Resume r) {

        for (i = 0; i < NUMBER; i++) {

            if (ARRAY[i] == null) {

                ARRAY[i] = r;

                ARRAY[i].setData(Resume.DataType.UUID, Integer.toString(i));

                return Integer.toString(i);
            }
        }

        return null;
    }

    @Override
    public boolean update(String uuid, Resume r) {

        for (i = 0; i < NUMBER; i++) {

            if (ARRAY[i].getData(Resume.DataType.UUID) == uuid) {

                ARRAY[i] = r;

                return true;
            }
        }

        return false;
    }

    @Override
    public Resume read(String uuid) {

        for (i = 0; i < NUMBER; i++) {

            if (ARRAY[i].getData(Resume.DataType.UUID) == uuid) return ARRAY[i];
        }

        return null;
    }

    @Override
    public boolean delete(String uuid) {

        for (i = 0; i < NUMBER; i++) {

            if (ARRAY[i].getData(Resume.DataType.UUID) == uuid) {

                ARRAY[i] = null;

                return true;
            }
        }

        return false;
    }

    @Override
    public Collection<Resume> getAll() {

        ArrayList<Resume> list = new ArrayList<Resume>();

        for (i = 0; i < NUMBER; i++) {

            if (ARRAY[i] == null) break;

            list.add(ARRAY[i]);
        }

        return list;
    }
}
