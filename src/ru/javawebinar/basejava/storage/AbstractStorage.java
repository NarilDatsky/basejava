package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

  protected int size = 0;

  @Override
  public abstract void clear();

  @Override
  public abstract Resume[] getAll();

  @Override
  public void update(Resume r) {
    Object index = getKey(r.getUuid());
    if (validate(index)) {
      doSet(r, index);
    } else {
      throw new NotExistStorageException(r.getUuid());
    }
  }

  @Override
  public void save(Resume r) {
    Object index = getKey(r.getUuid());
    if (validate(index)) {
      throw new ExistStorageException(r.getUuid());
    } else {
      doAdd(r, index);
      size++;
    }
  }

  @Override
  public Resume get(String uuid) {
    Object index = getKey(uuid);
    if (validate(index)) {
      return doGet(index);
    } else {
      throw new NotExistStorageException(uuid);
    }
  }


  @Override
  public void delete(String uuid) {
    Object index = getKey(uuid);
    if (validate(index)) {
      doDelete(index);
      size--;
    } else {
      throw new NotExistStorageException(uuid);
    }
  }

  @Override
  public int size() {
    return size;
  }

  protected abstract boolean validate(Object index);

  protected abstract void doDelete(Object index);

  protected abstract Resume doGet(Object index);

  protected abstract void doAdd(Resume r, Object index);

  protected abstract void doSet(Resume r, Object index);

  protected abstract Object getKey(String uuid);
}
