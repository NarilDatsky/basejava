import java.util.Arrays;

public class ArrayStorage {
    int storageLength = 10000;
    Resume[] storage = new Resume[storageLength];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        if (getIndex(r.toString()) > -1) {
            System.out.println("This resume is present in AS");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int indexOfUuid = getIndex(uuid);
        if (indexOfUuid > -1) {
            return storage[indexOfUuid];
        }
        return null;
    }

    public void delete(String uuid) {
        int indexOfUuid = getIndex(uuid);
        if (indexOfUuid > -1) {
            System.arraycopy(storage, indexOfUuid + 1, storage, indexOfUuid, size - 1);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("There is no such uuid to delete it");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}