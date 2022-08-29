import java.util.Random;

public class QPHashTable extends OAHashTable {

	private ModHash hashFunc;
	public QPHashTable(int m, long p) {
		super(m);
		this.hashFunc = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {

		int initialHash = this.hashFunc.Hash(x);
		int m = this.table.length;
		int hashValue = (initialHash + i * i) % m;
		return hashValue;
	}
}
