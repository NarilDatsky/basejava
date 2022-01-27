package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage {
  protected static final int STORAGE_LIMIT = 10000;

  protected Resume[] storage;
  { storage = new Resume[STORAGE_LIMIT]; }

  @Override
  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  @Override
  protected boolean validate(Object index) {
    return (Integer) index > -1;
  }

  @Override
  protected void doDelete(Object index) {
    fillDeletedElement((Integer) index);
    storage[size - 1] = null;
  }

  @Override
  protected Resume doGet(Object index) {
    return storage[(Integer) index];
  }

  @Override
  protected void doAdd(Resume r, Object index) {
    if (size == STORAGE_LIMIT) {
      throw new StorageException("Storage overflow", r.getUuid());
    } else {
      insertElement(r, (Integer) index);
    }
  }

  @Override
  protected void doSet(Resume r, Object index) {
    storage[(Integer) index] = r;
  }

  @Override
  protected abstract Object getKey(String uuid);

  protected abstract void fillDeletedElement(Integer index);

  protected abstract void insertElement(Resume r, Integer index);
}