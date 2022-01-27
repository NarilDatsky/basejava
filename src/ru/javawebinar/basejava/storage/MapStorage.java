package ru.javawebinar.basejava.storage;

import java.util.HashMap;
import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

  protected HashMap<String, Resume> storage = new HashMap<>();

  @Override
  public void clear() {
    storage.clear();
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return storage.values().toArray(new Resume[size]);
  }

  @Override
  protected void doDelete(Object index) {
    storage.remove((String) index);
  }

  @Override
  protected Resume doGet(Object index) {
    return storage.get((String) index);
  }

  @Override
  protected void doSet(Resume r, Object index) {
    doAdd(r, index);
  }

  @Override
  protected Object getKey(String uuid) {
    return uuid;
  }

  @Override
  protected boolean validate(Object index) {
    return storage.containsKey((String) index);
  }

  @Override
  protected void doAdd(Resume r, Object index) {
    storage.put(r.getUuid(), r);
  }
}
