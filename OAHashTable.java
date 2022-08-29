
public abstract class OAHashTable implements IHashTable {

	private static final long DELETED_INFO = -1;
	protected HashTableElement [] table;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		// TODO add to constructor as needed
	}
	
	
	@Override
	public HashTableElement Find(long key) {

		int m = this.table.length;
		int index;

		for (int i = 0; i < m; i++) {

			index = Hash(key, i);
			if (this.table[index] != null) {

				HashTableElement element = this.table[index];
				if (element.GetKey() == key)
					return element;
			}

			else { // this.table[index] == null
				return null;
			}
		}
		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {

		int m = this.table.length;
		HashTableElement otherElement;
		long otherKey;
		int index;

		HashTableElement alreadyExists = Find(hte.GetKey());
		if (alreadyExists != null)
			throw new KeyAlreadyExistsException(hte);

		for (int i = 0; i < m; i++) {
			index = Hash(hte.GetKey(), i);

			if (this.table[index] == null) {
				this.table[index] = hte;
				return;
			}
			else { // this.table[index] != null
				otherElement = this.table[index];
				otherKey = otherElement.GetKey();

				if (otherKey == DELETED_INFO) { // if it is a "deleted" mark
					this.table[index] = hte;
					return;
				}
			}
		}

		throw new TableIsFullException(hte);
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {

		HashTableElement element;
		int m = this.table.length;
		int index;

		for (int i = 0; i < m; i++) {
			index = Hash(key, i);

			if (this.table[index] != null) {
				element = this.table[index];

				if (element.GetKey() == key) {
					HashTableElement deletedElement = new HashTableElement(DELETED_INFO,DELETED_INFO);
					this.table[index] = deletedElement;
					return;
				}
			}

			else { // this.table[index] == null
				throw new KeyDoesntExistException(key);
			}
		}
		throw new KeyDoesntExistException(key);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
