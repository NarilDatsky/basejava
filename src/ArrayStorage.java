public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        storage = new Resume[10000];
    }

    private int update(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        if (update(r.toString()) > -1) {
            System.out.println("This resume is present in AS");
        } else {
            storage[size()] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int indexOfUuid = update(uuid);
        if (indexOfUuid > -1) {
            return storage[indexOfUuid];
        }
        return null;
    }

    public void delete(String uuid) {
        int indexOfUuid = update(uuid);
        if (indexOfUuid > -1) {
            System.arraycopy(storage, indexOfUuid + 1, storage, indexOfUuid, storage.length - 1);
            storage[9999] = null;
            size--;
        } else {
            System.out.println("There is no such uuid to delete it");
        }
    }

    public Resume[] getAll() {
        Resume[] result = new Resume[this.size()];
        System.arraycopy(storage, 0, result, 0, result.length);
        return result;
    }

    public int size() {
        return size;
    }
}