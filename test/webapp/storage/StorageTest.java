package webapp.storage;

import org.junit.*;
import webapp.WebAppException;
import webapp.model.ContactType;
import webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class StorageTest {
    static final String DUMMY_UUID = "dummy";
    static final String FULL_NAME_1 = "Иван Иванов";
    static final String FULL_NAME_2 = "Пётр Петров";
    static final String LOCATION = "СПб";
    static Resume RESUME[] = new Resume[5];

    static IStorage storage;

    @BeforeClass
    public static void before() throws Exception {
        RESUME[0] = new Resume(FULL_NAME_1, LOCATION);
        RESUME[0].addContact(ContactType.HOME_PHONE, "+79111234567");
        RESUME[1] = new Resume(RESUME[0].getUuid(), FULL_NAME_1, LOCATION);
        RESUME[1].addContact(ContactType.HOME_PHONE, "+79111234567");
        RESUME[2] = new Resume(RESUME[0].getUuid(), FULL_NAME_2, LOCATION);
        RESUME[3] = new Resume(DUMMY_UUID, FULL_NAME_2, LOCATION);
        RESUME[4] = new Resume(FULL_NAME_2, LOCATION);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME[0]);
    }

    @After
    public void tearDown() throws Exception {
        storage.clear();
    }

    @Test
    public void testRead() throws Exception {
        Assert.assertEquals(storage.read(RESUME[0].getUuid()), RESUME[1]);
    }

    @Test(expected = WebAppException.class)
    public void testSameUuidSave() throws Exception {
        storage.save(RESUME[2]);
    }

    @Test
    public void testUpdate() throws Exception {
        storage.update(RESUME[2]);
        Assert.assertEquals(storage.read(RESUME[2].getUuid()), RESUME[2]);
    }

    @Test(expected = WebAppException.class)
    public void testNotExistUpdate() throws Exception {
        storage.update(RESUME[3]);
    }

    @Test(expected = WebAppException.class)
    public void testDelete() throws Exception {
        storage.delete(RESUME[0].getUuid());
        storage.read(RESUME[0].getUuid());
    }

    @Test(expected = WebAppException.class)
    public void testNotExistDelete() throws Exception {
        storage.delete(DUMMY_UUID);
    }

    @Test
    public void testGetAll() throws Exception {

        storage.save(RESUME[3]);
        storage.save(RESUME[4]);

        Map<String, Resume> map = new HashMap<>();

        map.put(RESUME[0].getUuid(), RESUME[0]);
        map.put(RESUME[3].getUuid(), RESUME[3]);
        map.put(RESUME[4].getUuid(), RESUME[4]);

        for(Resume r: storage.getAll()) {
            Assert.assertEquals(map.get(r.getUuid()), r);
        }
    }
}