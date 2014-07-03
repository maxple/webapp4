package webapp.storage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.WebAppException;
import webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class StorageTest {
    static final String DUMMY_UUID = "dummy";
    static final String FULL_NAME_1 = "Иван Иванов";
    static final String FULL_NAME_2 = "Пётр Петров";
    static final String LOCATION = "СПб";
    static final Resume RESUME = new Resume(FULL_NAME_1, LOCATION);
    static IStorage storage;

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME);
    }

    @After
    public void tearDown() throws Exception {
        storage.clear();
    }

    @Test
    public void testRead() throws Exception {
        Assert.assertEquals(storage.read(RESUME.getUuid()), new Resume(RESUME.getUuid(), FULL_NAME_1, LOCATION));
    }

    @Test(expected = WebAppException.class)
    public void testSameUuidSave() throws Exception {
        storage.save(new Resume(RESUME.getUuid(), FULL_NAME_2, LOCATION));
    }

    @Test
    public void testUpdate() throws Exception {
        final Resume r2 = new Resume(RESUME.getUuid(), FULL_NAME_2, LOCATION);
        storage.update(r2);
        Assert.assertEquals(storage.read(r2.getUuid()), r2);
    }

    @Test(expected = WebAppException.class)
    public void testNotExistUpdate() throws Exception {
        storage.update(new Resume(DUMMY_UUID, FULL_NAME_2, LOCATION));
    }

    @Test(expected = WebAppException.class)
    public void testDelete() throws Exception {
        storage.delete(RESUME.getUuid());
        storage.read(RESUME.getUuid());
    }

    @Test(expected = WebAppException.class)
    public void testNotExistDelete() throws Exception {
        storage.delete(DUMMY_UUID);
    }

    @Test
    public void testGetAll() throws Exception {
        final Resume r2 = new Resume(DUMMY_UUID, FULL_NAME_1, LOCATION);
        final Resume r3 = new Resume(FULL_NAME_2, LOCATION);

        storage.save(r2);
        storage.save(r3);

        Map<String, Resume> map = new HashMap<>();

        map.put(RESUME.getUuid(), RESUME);
        map.put(r2.getUuid(), r2);
        map.put(r3.getUuid(), r3);

        for(Resume r: storage.getAll()) {
            Assert.assertEquals(map.get(r.getUuid()), r);
        }
    }
}