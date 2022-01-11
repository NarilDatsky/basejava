package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected void fillDeletedElement(int index) {

  }

  @Override
  protected void insertElement(Resume r, int index) {

  }

  @Override
  protected int getIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }
}
