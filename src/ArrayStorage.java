/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
            break;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return null;
            }
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int indexOfUuid = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString() == uuid) {
                indexOfUuid = i;
                break;
            }
        }
        if (indexOfUuid > -1) {
            System.arraycopy(storage, indexOfUuid + 1, storage, indexOfUuid, storage.length - 1);
            storage[9999] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[this.size()];
        System.arraycopy(storage, 0, result, 0, result.length);
        return result;
    }

    int size() {
        int result;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return storage.length;
    }
}
