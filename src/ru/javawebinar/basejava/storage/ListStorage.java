package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

  protected ArrayList<Resume> storage = new ArrayList<>();

  @Override
  public void clear() {
    storage.removeAll(storage);
    size = 0;
  }

  @Override
  public Resume[] getAll() {
    return storage.toArray(new Resume[size]);
  }

  @Override
  protected void doDelete(Object index) {
    storage.remove(((Integer) index).intValue());
  }

  @Override
  protected Resume doGet(Object index) {
    return storage.get((Integer) index);
  }

  @Override
  protected boolean validate(Object index) {
    return (Integer) index > -1;
  }

  @Override
  protected void doAdd(Resume r, Object index) {
    storage.add(r);
  }

  @Override
  protected void doSet(Resume r, Object index) {
    storage.set((Integer) index, r);
  }

  @Override
  protected Object getKey(String uuid) {
    for (int i = 0; i < size; i++) {
      if(storage.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }
}
