package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

  private Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final Resume resume1 = new Resume(UUID_1);
  private static final Resume resume2 = new Resume(UUID_2);
  private static final Resume resume3 = new Resume(UUID_3);
  private static final String UUID_4 = "uuid4";
  private static final Resume newOneResume = new Resume(UUID_4);

  public AbstractArrayStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() throws Exception {
    storage.clear();
    storage.save(resume1);
    storage.save(resume2);
    storage.save(resume3);
  }

  @Test
  public void size() throws Exception {
    Assert.assertEquals(3, storage.size());
  }

  @Test
  public void clear() throws Exception {
    storage.clear();
    Assert.assertEquals(0, storage.size());
  }

  @Test
  public void update() throws Exception {
    storage.update(resume1);
    Assert.assertEquals(resume1, storage.get(UUID_1));
  }

  @Test(expected = NotExistStorageException.class)
  public void updateException() throws Exception {
    storage.update(newOneResume);
  }

  @Test()
  public void getAll() throws Exception {
    Resume[] array = storage.getAll();
    Assert.assertEquals(3, array.length);
    Assert.assertEquals(resume1, array[0]);
    Assert.assertEquals(resume2, array[1]);
    Assert.assertEquals(resume3, array[2]);
  }

  @Test
  public void save() throws Exception {
    Assert.assertEquals(3, storage.size());
    storage.save(newOneResume);
    Assert.assertEquals(4, storage.size());
    Assert.assertEquals(newOneResume, storage.get(UUID_4));
  }

  @Test(expected = ExistStorageException.class)
  public void saveWhenFill() throws Exception {
    storage.save(resume1);
  }

  @Test
  public void delete() throws Exception {
    Assert.assertEquals(3, storage.size());
    storage.delete(UUID_1);
    Assert.assertEquals(2, storage.size());
  }

  @Test(expected = NotExistStorageException.class)
  public void deleteNonExists() throws Exception {
    storage.delete(UUID_1);
    storage.get(UUID_1);
  }

  @Test
  public void get() throws Exception {
    Assert.assertEquals(resume1, storage.get(UUID_1));
    Assert.assertEquals(resume2, storage.get(UUID_2));
    Assert.assertEquals(resume3, storage.get(UUID_3));
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() throws Exception {
    storage.get("dummy");
  }

  @Test(expected = StorageException.class)
  public void saveOverflow() throws Exception {
    for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
      try {
        storage.save(new Resume());
      } catch (StorageException e) {
        Assert.fail();
      }
    }
    storage.save(new Resume());
  }
}