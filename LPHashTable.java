import java.util.Random;

public class LPHashTable extends OAHashTable {

	private ModHash hashFunc;
	public LPHashTable(int m, long p) {
		super(m);
		this.hashFunc = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {

		int initialHash = this.hashFunc.Hash(x);
		int m = this.table.length;
		int hashValue = (initialHash + i) % m;
		return hashValue;
	}
	
}
