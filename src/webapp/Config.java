package webapp;

import webapp.storage.DataStreamStorage;
import webapp.storage.IStorage;
import webapp.storage.SerializeStorage;
import webapp.storage.XmlStorage;


/**
 * User: gkislin
 * Date: 26.02.14
 */
public class Config {
    public static final String DIR_STORAGE = "G:\\Maxim\\Workspaces\\Java\\Source\\webapp4\\file_storage";

    public static final IStorage DS_STORAGE = new DataStreamStorage(DIR_STORAGE);

    public static final IStorage SER_STORAGE = new SerializeStorage(DIR_STORAGE);

    public static final IStorage XML_STORAGE = new XmlStorage(DIR_STORAGE);

    public static IStorage getStorage() {
        return XML_STORAGE;
    }
}
