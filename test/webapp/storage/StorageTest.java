package webapp.storage;

import org.junit.Before;
import org.junit.Test;
import webapp.WebAppException;
import webapp.model.Resume;

public class StorageTest {
    static final Resume RESUME = new Resume("Иван Иваныч", "СПб");
    static IStorage storage;

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.create(RESUME);
    }

    @Test
    public void testUpdate() throws Exception {
        Resume r = new Resume("Иван Иваный", "СПб");
//        Assert.assertEquals(RESUME, r);
    }

    @Test
    public void testRead() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test(expected = WebAppException.class)
    public void testNotExistDelete() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void testGetAll() throws Exception {

    }
}