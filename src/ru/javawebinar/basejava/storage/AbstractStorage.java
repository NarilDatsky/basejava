package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.List;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

  protected int size = 0;

  protected abstract Object getSearchKey(String searchKey);

  protected abstract void doUpdate(Resume r, Object searchKey);

  protected abstract boolean isExist(Object searchKey);

  protected abstract void doSave(Resume r, Object searchKey);

  protected abstract Resume doGet(Object searchKey);

  protected abstract void doDelete(Object searchKey);

  protected abstract Resume[] getAll();

  public int size() {
    return size;
  }

  public void update(Resume r) {
    Object searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  public void save(Resume r) {
    Object searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
    size++;
  }

  public void delete(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
    size--;
  }

  public Resume get(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  @Override
  public List<Resume> getAllSorted() {
    var getAll = getAll();
    Resume save;
    for (int j = 0; j < getAll.length; j++) {
      for (int i = j + 1; i < getAll.length; i++) {
        if (getAll[i].getFullName().compareTo(getAll[j].getFullName()) < 0) {
          save = getAll[j];
          getAll[j] = getAll[i];
          getAll[i] = save;
        }
        else if (getAll[i].getFullName().equals(getAll[j].getFullName())) {
          if (getAll[i].getUuid().compareTo(getAll[j].getUuid()) < 0) {
            save = getAll[j];
            getAll[j] = getAll[i];
            getAll[i] = save;
          }
        }
      }
    }
    return Arrays.asList(getAll);
  }

  private Object getExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }

  private Object getNotExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      throw new ExistStorageException(uuid);
    }
    return searchKey;
  }
}