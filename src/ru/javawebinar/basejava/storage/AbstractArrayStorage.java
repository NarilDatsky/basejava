package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
  protected static final int STORAGE_LIMIT = 10000;

  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public int size() {
    return size;
  }

  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index == -1) {
      System.out.println("Resume " + r.getUuid() + " not exist");
    } else {
      storage[index] = r;
    }
  }

  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index == -1) {
      System.out.println("Resume " + uuid + " not exist");
      return null;
    }
    return storage[index];
  }

  public void save(Resume r) {
    int index = getIndex(r.getUuid());
    if (index > 0) {
      System.out.println("Resume " + r.getUuid() + " already exist");
    } else if (size >= STORAGE_LIMIT) {
      System.out.println("Storage overflow");
    } else {
      addResume(r, index);
      size++;
    }
  }

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      System.out.println("Resume " + uuid + " not exist");
    } else {
      offsetAfterDeletion(index);
      size--;
    }
  }

  protected abstract int getIndex(String uuid);
  protected abstract void addResume(Resume r, int positionIndex);
  protected abstract void offsetAfterDeletion(int positionIndex);
}