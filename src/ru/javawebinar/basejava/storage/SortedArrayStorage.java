package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected void fillDeletedElement(Integer index) {
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(storage, index + 1, storage, index, numMoved);
    }
  }

  @Override
  protected void insertElement(Resume r, Integer index) {
    int insertIdx = -index - 1;
    System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
    storage[insertIdx] = r;
  }

  @Override
  protected Object getKey(String uuid) {
    Resume searchKey = new Resume(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }
}