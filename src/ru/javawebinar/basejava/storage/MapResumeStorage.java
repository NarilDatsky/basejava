package ru.javawebinar.basejava.storage;

import java.util.HashMap;
import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractStorage {

  protected HashMap<String, Resume> storage = new HashMap<>();

  @Override
  public void clear() {
    storage.clear();
    size = 0;
  }

  @Override
  protected void doDelete(Object searchKey) {
    storage.remove((String) searchKey);
  }

  @Override
  protected Resume doGet(Object searchKey) {
    return storage.get((String) searchKey);
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {
    doUpdate(r, searchKey);
  }

  @Override
  protected Object getSearchKey(String searchKey) {
    return searchKey;
  }

  @Override
  protected boolean isExist(Object searchKey) { return storage.containsKey((String) searchKey); }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {
    storage.put(r.getUuid(), r);
  }

  @Override
  protected Resume[] getAll() {
    return storage.values().toArray(new Resume[size]);
  }
}
